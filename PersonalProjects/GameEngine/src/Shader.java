
import java.util.HashMap;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Shader {
    private int program;
    private HashMap<String, Integer> uniforms;
    
    public Shader(){
        program = glCreateProgram();
        uniforms = new HashMap<String, Integer>();
        
        if(program ==0){
            System.err.println("Shader creation failed: Could not find valid memory location in constructor");
            System.exit(1);
        }
    }
    
    public void bind(){
        glUseProgram(program);
    }
    
    public void addUniform(String uniform){
        int uniformLocation = glGetUniformLocation(program,uniform);
        
        if(uniformLocation ==-1){
            System.err.println("Error: Could not find uniform " + uniform);
            new Exception().printStackTrace();
            System.exit(1);
        }
        
        uniforms.put(uniform,uniformLocation);
        
    }
    
    public void addVertexShader(String text){
        addProgram(text, GL_VERTEX_SHADER);
    }
    
    public void addGeometryShader(String text){
        addProgram(text, GL_GEOMETRY_SHADER);
    }
    
    public void addFragmentShader(String text){
        addProgram(text, GL_FRAGMENT_SHADER);
    }
    
    //This is the implementation code to ensure that our shaders work on version 1.3.0
    //without the "layout" command being supported.
    //This makes the pointer available to the shader.
    //Inside the shader the following code must be present: in vec3 position0. 
    //where 0 is the number of the pointer location parameter
    
    //TODO - find a way to incorporate this
    public void makeAvailableToShader(int location){
        glBindAttribLocation(program, location, "position"+Integer.toString(location));
    }
    
    public void compileShader(){
        //TODO - how do we call this when we create the actual pointer
        glBindAttribLocation(program, 0, "position");
        glLinkProgram(program);
        
        if(glGetProgrami(program, GL_LINK_STATUS)==0){
            System.err.println(glGetProgramInfoLog(program,1024));
            System.exit(1);
        }
        
        glValidateProgram(program);
        
        if(glGetProgrami(program,GL_VALIDATE_STATUS)==0){
            System.err.println(glGetProgramInfoLog(program,1024));
            System.exit(1);
        }
    }
    
    private void addProgram(String text, int type){
        int shader = glCreateShader(type);
        
        if(shader == 0){
            System.err.println("Shader creation failed: Could not find valid memory location when adding shader");
            System.exit(1);
        }
        
        glShaderSource(shader, text);
        glCompileShader(shader);
        
        if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0){
            System.err.println(glGetShaderInfoLog(shader,1024));
            System.exit(1);
        }
        glAttachShader(program, shader);
    }
    
    public void setUniformi(String uniformName, int value){
        glUniform1i(uniforms.get(uniformName),value);
    }
    
    public void setUniformf(String uniformName, float value){
        glUniform1f(uniforms.get(uniformName),value);
    }
    
    public void setUniform(String uniformName, Vector3f value){
        glUniform3f(uniforms.get(uniformName),value.getX(),value.getY(),value.getZ());
    }
    
    //true for rowMajor matrix, false for columnMajor matrix
    public void setUniform(String uniformName, Matrix4f value){
        glUniformMatrix4(uniforms.get(uniformName),true,Util.createFlippedBuffer(value));
    }
}
