/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JavaGameDevelopment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Administrator
 */
public class InputDemo {
    private List<Box> shapes = new ArrayList<Box>(16);
    private boolean somethingIsSelected = false;
    private volatile boolean randomColorCooldown = false;

    public InputDemo(){
        
        
        try{
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Hello World");
            Display.create();
        }catch(LWJGLException e){
            e.printStackTrace();
        }
        
        shapes.add(new Box(15,15));
        shapes.add(new Box(100,150));
        //Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,640,480,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        
        while(!Display.isCloseRequested()){
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            while(Keyboard.next()){
                if(Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()){
                    shapes.add(new Box(15,15));
                }
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
                Display.destroy();
                System.exit(0);
            }
            
            /*int mousey = 480 - Mouse.getY();
            int mousex = Mouse.getX();
            int dx = Mouse.getDX();
            int dy = -Mouse.getDY();
            System.out.println(mousey);*/
            
            for(Box box : shapes){
                if(Mouse.isButtonDown(0)&&box.inBounds(Mouse.getX(),480-Mouse.getY()) && !somethingIsSelected){
                    somethingIsSelected=true;
                    box.selected = true;
                    System.out.println("You clicked me!");
                }
                if(Mouse.isButtonDown(2)&&box.inBounds(Mouse.getX(),480-Mouse.getY()) && !somethingIsSelected){
                    box.randomizeColors();
                    randomColorCooldown = true;
                    new Thread(new Runnable(){

                        @Override
                        public void run() {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }finally{
                                randomColorCooldown = false;
                            }
                        }
                        
                    }).run();
                }
                if(Mouse.isButtonDown(1)){
                    box.selected=false;
                    somethingIsSelected=false;
                }
                if(box.selected){
                    box.update(Mouse.getDX(), -Mouse.getDY());
                }
                box.draw();
            }
                        
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
    
    private static class Box{
        public int x,y;
        private float colorRed, colorBlue, colorGreen;
        public boolean selected = false;
        
        Box(int x, int y){
            this.x=x;
            this.y=y;
            
            Random randomGenerator = new Random();
            colorRed = randomGenerator.nextFloat();
            colorBlue = randomGenerator.nextFloat();
            colorGreen = randomGenerator.nextFloat();
        }
        
        boolean inBounds(int mousex, int mousey){
            if(mousex>x && mousex < x+50 && mousey > y && mousey < y+50){
                return true;
            }else{
                return false;
            }
        }
        
        void update(int dx, int dy){
            x += dx;
            y += dy;
        }
        
        void randomizeColors(){
            Random randomGenerator = new Random();
            colorRed = randomGenerator.nextFloat();
            colorBlue = randomGenerator.nextFloat();
            colorGreen = randomGenerator.nextFloat();
        }
        
        void draw(){
            glColor3f(colorRed,colorGreen,colorBlue);
            glBegin(GL_QUADS);
                glVertex2f(x,y);
                glVertex2f(x+50, y);
                glVertex2f(x+50,y+50);
                glVertex2f(x,y+50);
            glEnd();                
        }
    }  
    
    /*public static void main(String[] args){
        new InputDemo();
    }*/
}
