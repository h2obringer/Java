package pieces;

import static org.lwjgl.opengl.GL11.*;
import static utils.Variables.*;
/**
 *
 * @author Obringer
 */
public class Piece {
    
    protected float x,y,z;
    private int startGridPosition; //determines the picture on the face of the block
    private int currentGridPosition;
    private float x_displacement,y_displacement;
    
    
    public Piece(float x,float y,int sp){
        this.x=x;
        this.y=y;
        z=ZERO;
        currentGridPosition=startGridPosition=sp;
        x_displacement=y_displacement=ZERO;
    }
    
    public float get_x_displacement(){
        return x_displacement;
    }
    
    public float get_y_displacement(){
        return y_displacement;
    }
    
    public void set_x_displacement(float x){
        x_displacement=x;
    }
    
    public void set_y_displacement(float y){
        y_displacement=y;
    }
    
    public void reset_displacement(){
        x_displacement=y_displacement=ZERO;
    }
    
    public int getStartGridPosition(){
        return startGridPosition;
    }
    
    public int getCurrentGridPosition(){
        return currentGridPosition;
    }
    
    public void mutateCurrentGridPosition(int newPosition){
        currentGridPosition=newPosition;
    }
    
    public void draw(){
        if(currentGridPosition==6){ //calling Grid.checkwin???
            //do nothing
        }else{
            glBegin(GL_POLYGON); //0.85 reaches the bottom of the picture and 0.65 reaches the left of the picture????
                //glColor3f(0f,0f,0f);
                //glTexCoord2f(0.65f,0.85f);
                glTexCoord2f(pic_coords[currentGridPosition][0],pic_coords[currentGridPosition][1]);
                glVertex3f(my_vertices[0][X_POS],my_vertices[0][Y_POS],my_vertices[0][Z_POS]);
                glNormal3f(my_normals[0][X_POS],my_normals[0][Y_POS],my_normals[0][Z_POS]);
                //glTexCoord2f(0,0.85f);
                glTexCoord2f(pic_coords[currentGridPosition][2],pic_coords[currentGridPosition][3]);
                glVertex3f(my_vertices[1][X_POS],my_vertices[1][Y_POS],my_vertices[1][Z_POS]);
                glNormal3f(my_normals[1][X_POS],my_normals[1][Y_POS],my_normals[1][Z_POS]);
                //glTexCoord2f(0,0);
                glTexCoord2f(pic_coords[currentGridPosition][4],pic_coords[currentGridPosition][5]);
                glVertex3f(my_vertices[2][X_POS],my_vertices[2][Y_POS],my_vertices[2][Z_POS]);
                glNormal3f(my_normals[2][X_POS],my_normals[2][Y_POS],my_normals[2][Z_POS]);
                //glTexCoord2f(0.65f,0);
                glTexCoord2f(pic_coords[currentGridPosition][6],pic_coords[currentGridPosition][7]);
                glVertex3f(my_vertices[3][X_POS],my_vertices[3][Y_POS],my_vertices[3][Z_POS]);
                glNormal3f(my_normals[3][X_POS],my_normals[3][Y_POS],my_normals[3][Z_POS]);
            glEnd();
        }
    }; //build the face of a cube
    
    public float get_x(){
        return x;
    } //get position on cube
    
    public void mutate_x(float x){
        this.x=x;
    }
    
    public float get_y(){
        return y;
    } //get position on cube
    
    public void mutate_y(float y){
        this.y=y;
    }
    
    public float get_z(){
        return z;
    } //get position on cube
    
    public void mutate_z(float z){
        this.z=z;
    }
    
}
