/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import pieces.Grid;
import static utils.Variables.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import static res.ResourceLoader.getFile;

    
/**
 *
 * @author Obringer
 */
public class Utils {
    
    int win_h=WINDOW_SIZE; //window height
    int win_w=WINDOW_SIZE; //window width
    int fps=FPS; //frames per second
    float ratio=(float)win_w/(float)win_h; //ratio of screen for gluPerspective view
    float fovy=INIT_FOVY; //the view angle for the screen in gluPerspective
    boolean win=false; //is the game won?
    
    Grid slider = new Grid();
    public Texture picture;
    int direction = -1;
    boolean isBeingSolved = false; //when the "s" key is pressed the computer will solve the puzzle
    String solution=""; //when the "s" key is pressed the solution will be generated and stored here
    int solutionPosition=0; //log which move in the position is being executed for when an animation ends so we can start the next animation with the correct move
    int solutionLength=0; //log the length of the solution for proper ending of computer solving
    
    public Utils(){
    }
    
    private Texture loadTexture(String key){
        try{
            return TextureLoader.getTexture("PNG",new FileInputStream(new File(Utils.class.getResource("/res/"+key+".png").getFile())));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void animate_right(){
        slider.mutate_in_progress(true);
        direction = RIGHT;
        //choose correct block in relation to the blank block
        //choice==ID;
        //make move function somewhere calls slider.move_right
    }
    
    public void animate_left(){
        slider.mutate_in_progress(true);
        direction = LEFT;
    }
    
    public void animate_down(){
        slider.mutate_in_progress(true);
        direction = DOWN;
    }
    
    public void animate_up(){
        slider.mutate_in_progress(true);
        direction = UP;
    }
    
    private void myKeyboardFunction(){
        while(Keyboard.next()){
            if(Keyboard.getEventKey()==Keyboard.KEY_ESCAPE){
                Display.destroy();
                System.exit(0);
            }else if(Keyboard.getEventKey()==Keyboard.KEY_S){
                SliderSolver solver = new SliderSolver();
                solution = solver.BFS(slider.getPiecePositions());
                solutionLength=solution.length();
                isBeingSolved=true;
            }else if(Keyboard.getEventKey()==Keyboard.KEY_N){
                slider.scramble();
            }else if(!slider.get_in_progress()&&!isBeingSolved){ //disable the keyboard if the computer is solving the puzzle){
                if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
                    animate_right();
                }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
                    animate_left();
                }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
                    animate_up();
                }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
                    animate_down();
                }
            }
        }
    }
    
    private void myDisplay(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Reset coordinate system
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        // Set the viewing perspective
        gluPerspective(fovy, ratio, VIEW_NEAR, VIEW_FAR);

        // Set viewing translation
        glMatrixMode(GL_MODELVIEW);
        
        if(isBeingSolved){
            automate();
        }
        
        if(slider.get_in_progress()){
            slider.increase_move_displacement(direction); //set the object in motion
            if(slider.get_move_displacement()>=PLACEMENT){ //if motion has met its limit of advance
                slider.reset_move_displacement(); //set the object back to where it came from
                slider.mutate_in_progress(false); //stop the animation
                make_move(); 
            }
        }
        
        myKeyboardFunction();
        
        glPushMatrix(); //addition for the java version
        slider.draw();//draw_rubiks_cube(GL_RENDER);
        glPopMatrix(); //addition for the java version
        
        /*if(slider.check_win()){
            slider.scramble();
        }*/
    }
    
    private void automate(){
        if(!slider.get_in_progress()){ //wait till the animation is complete to begin the next one
            if(solutionLength!=solutionPosition){ //ensure we are still within the solution directions
                switch(solution.charAt(solutionPosition)){
                    case 'U':
                        solutionPosition++;
                        animate_up();
                        break;
                    case 'D':
                        solutionPosition++;
                        animate_down();
                        break;
                    case 'L':
                        solutionPosition++;
                        animate_left();
                        break;
                    case 'R':
                        solutionPosition++;
                        animate_right();
                        break;
                }
            }else{
                solutionPosition=0;
                isBeingSolved = false;
                System.out.println("reset!");
            }
        }
    }

    private void make_move() {
        if(direction == RIGHT){
            slider.move_right();
        }else if(direction == LEFT){
            slider.move_left();
        }else if(direction == UP){
            slider.move_up();
        }else if(direction == DOWN){
            slider.move_down();
        }
    }
    
      
    public void myCreateDisplay(){
        try{
            Display.setDisplayMode(new DisplayMode(win_w,win_h));
            Display.setTitle("3x3 Slider");
            Display.create();
        }catch(LWJGLException e){
            //do nothing
        }
    }
    
    public void myInit(){
        // Set background to black
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glEnable(GL_TEXTURE_2D);
        picture = loadTexture("hazelEyes");
        picture.bind();
        // Enable depth testing
        glEnable(GL_DEPTH_TEST);
        glMatrixMode(GL_MODELVIEW);
        slider.scramble(); 
        glTranslatef( DISPLACEMENT_X, DISPLACEMENT_Y, -DISPLACEMENT_Z );
    }
    
    public void myDisplayLoop(){
        while(!Display.isCloseRequested()){
            myDisplay();
            Display.update();
            Display.sync(fps);
        }
        Display.destroy();
    }
}
