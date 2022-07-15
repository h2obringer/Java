
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Mesh {
    private int vbo; //use as a handle (pointer)
    private int ibo; //index buffer object
    private int size; //how many items of data
    
    public Mesh(){
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        size = 0;
    }
    
    public void addVertices(Vertex[] vertices, int[] indices){
        size = vertices.length * Vertex.SIZE; //account for each part of vertex
        
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW); //
        
        //start addition for adding indices //get rid of indices paramter for old implementation
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
        //end addition for adding indices
    }
    
    
    public void draw(){
        glEnableVertexAttribArray(0); //tell OpenGL how to divide up the buffered data. Store in pointer 0. This allows us to access it from shader programs.
        glBindBuffer(GL_ARRAY_BUFFER,vbo);
        //interpret data using instructions below. False to normalizing. 4 bytes per float * 3 vertices. Data starts at 0. 
        //0=index; 3=size; GL_FLOAT=type; false=normalized; Vertex.SIZE (3)*4=stride; 0=pointer //size is 12 //4 bytes in a float
        glVertexAttribPointer(0,3, GL_FLOAT, false, Vertex.SIZE*4, 0);
        //size specifies the number of components per generic vertex attribute. 
        //stride specifies the byte offset between consecutive generic vertex attributes. 
        
        
        //start addition to add indices
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);        
        //end addition
        
        //glDrawArrays(GL_TRIANGLES, 0, size); //use when not using vertices
        glDisableVertexAttribArray(0);
    }
}
