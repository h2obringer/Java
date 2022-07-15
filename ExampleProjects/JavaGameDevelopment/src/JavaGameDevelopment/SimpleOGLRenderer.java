/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JavaGameDevelopment;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Administrator
 */
public class SimpleOGLRenderer {
    public SimpleOGLRenderer(){
        try{
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Hello World");
            Display.create();
        }catch(LWJGLException e){
            e.printStackTrace();
        }
        //Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,640,480,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        
        while(!Display.isCloseRequested()){
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            glBegin(GL_QUADS);
                glVertex2i(400,400);
                glVertex2i(500,400);
                glVertex2i(500,500);
                glVertex2i(400,500);
            glEnd();
            
            glBegin(GL_LINES);
                glVertex2i(100,100);
                glVertex2i(200,200);
            glEnd();
                        
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
}
