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
public class LWJGLhelloWorld {
    public LWJGLhelloWorld(){
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
            glClear(GL_COLOR_BUFFER_BIT);
            
                        
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
    
}
