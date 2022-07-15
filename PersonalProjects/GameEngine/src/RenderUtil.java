/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
/**
 *
 * @author Administrator
 */
public class RenderUtil {
    public static void clearScreen(){
        //TODO Stencil Buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
    public static void initGraphics(){ //put engine in base state
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        
        //glFrontFace(GL_CW); //clockwise
        //glCullFace(GL_BACK); //cull the back face
        //glEnable(GL_CULL_FACE); //enable face culling. Get rid of non seen face drawings for objects
        glEnable(GL_DEPTH_TEST); //important when drawing more than one thing. 
        
        //TODO: Depth clamp for later
        
        glEnable(GL_FRAMEBUFFER_SRGB); //get free gamma correction. Send exponentially instead of linearly.
        
    }
    
    public static String getOpenGLVersion(){
        return glGetString(GL_VERSION);
    }
}
