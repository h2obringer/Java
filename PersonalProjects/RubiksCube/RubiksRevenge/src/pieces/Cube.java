package pieces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import static utils.Variables.*;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Obringer
 */
public class Cube implements Icube{
    //give the interface the variables to work with for the subclasses that will be using them
    
    public Cube(int x_axis, int y_axis, int z_axis, int rotation, float x, float y,
            float z, int back, int front, int top, int bottom, int left, int right, int temp){
        this.x_axis=x_axis;
        this.y_axis=y_axis;
        this.z_axis=z_axis;
        this.rotation=rotation;
        this.x=x;
        this.y=y;
        this.z=z;
        this.back=back;
        this.front=front;
        this.top=top;
        this.bottom=bottom;
        this.left=left;
        this.right=right;
        this.temp=temp;
    }    
    
    @Override
    public void face(int a, int b, int c , int d, int color){
        glBegin(GL_POLYGON);
            glColor3f(my_colors[color][X_POS],my_colors[color][Y_POS],my_colors[color][Z_POS]);
            glNormal3f(my_normals[a][X_POS],my_normals[a][Y_POS],my_normals[a][Z_POS]);
            glVertex3f(my_vertices[a][X_POS],my_vertices[a][Y_POS],my_vertices[a][Z_POS]);
            glNormal3f(my_normals[b][X_POS],my_normals[b][Y_POS],my_normals[b][Z_POS]);
            glVertex3f(my_vertices[b][X_POS],my_vertices[b][Y_POS],my_vertices[b][Z_POS]);
            glNormal3f(my_normals[c][X_POS],my_normals[c][Y_POS],my_normals[c][Z_POS]);
            glVertex3f(my_vertices[c][X_POS],my_vertices[c][Y_POS],my_vertices[c][Z_POS]);
            glNormal3f(my_normals[d][X_POS],my_normals[d][Y_POS],my_normals[d][Z_POS]);
            glVertex3f(my_vertices[d][X_POS],my_vertices[d][Y_POS],my_vertices[d][Z_POS]);
        glEnd();
    }; //build the face of a cube
    
    @Override
    public void build_cube(){
        face(1,0,3,2,back);
        face(3,7,6,2,top);
        face(7,3,0,4,left);
        face(0,4,5,1,bottom);
        face(2,6,5,1,right);
        face(4,5,6,7,front);
    }; //build the current cube //parameters are colors for the faces
    
    @Override
    public void draw(int mode,int NAME){
        if(mode==GL_SELECT){
            glLoadName(NAME);
        }
        build_cube();
    }; //draw the cube //mode should be of type GLenum
    
    @Override
    public float get_x(){
        return x;
    } //get position on cube
    
    @Override
    public float get_y(){
        return y;
    } //get position on cube
    
    @Override
    public float get_z(){
        return z;
    } //get position on cube
    
    @Override
    public int get_back(){
        return back;
    } //get color of back face
    
    @Override
    public int get_front(){
        return front;
    } //get color of front face
    
    @Override
    public int get_left(){
        return left;
    } //get color of left face
    
    @Override
    public int get_right(){
        return right;
    } //get color of right face
    
    @Override
    public int get_top(){
        return top;
    } //get color of top face
    
    @Override
    public int get_bottom(){
        return bottom;
    } //get color of bottom face
    
    @Override
    public int get_rotation(){
        return rotation;
    } //get the rotation for animation
    
    @Override
    public int get_x_axis(){
        return x_axis;
    } //get x axis for animation
    
    @Override
    public int get_y_axis(){
        return y_axis;
    } //get y axis for animation
    
    @Override
    public int get_z_axis(){
        return z_axis;
    } //get z axis for animation
    
    @Override
    public void mutate_axese(int new_x_axis,int new_y_axis,int new_z_axis){
        x_axis=new_x_axis;
        y_axis=new_y_axis;
        z_axis=new_z_axis;
    } //change all axese
    
    @Override
    public void mutate_rotation(int new_rotation){
        rotation=new_rotation;
    } //change rotation 
    
    @Override
    public void reset_axese(){
        x_axis=ZERO;
        y_axis=ZERO;
        z_axis=ZERO;
    } //reset all axese to zero   
    
    @Override
    public void write_cube(BufferedWriter bw) throws IOException{
        bw.write(Integer.toString(x_axis));
        bw.newLine();
        bw.write(Integer.toString(y_axis));
        bw.newLine();
        bw.write(Integer.toString(z_axis));
        bw.newLine();
        bw.write(Integer.toString(rotation));
        bw.newLine();
        bw.write(Float.toString(x));
        bw.newLine();
        bw.write(Float.toString(y));
        bw.newLine();
        bw.write(Float.toString(z));
        bw.newLine();
        bw.write(Integer.toString(back));
        bw.newLine();
        bw.write(Integer.toString(front));
        bw.newLine();
        bw.write(Integer.toString(top));
        bw.newLine();
        bw.write(Integer.toString(bottom));
        bw.newLine();
        bw.write(Integer.toString(left));
        bw.newLine();
        bw.write(Integer.toString(right));
        bw.newLine();
    } //write cube data to file
    
    @Override
    public void load_cube(BufferedReader input) throws IOException{
        String line = null;
        if((line = input.readLine()) != null){
            x_axis=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            y_axis=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            z_axis=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            rotation=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            x=Float.parseFloat(line);
        }
        if((line = input.readLine()) != null){
            y=Float.parseFloat(line);
        }
        if((line = input.readLine()) != null){
            z=Float.parseFloat(line);
        }
        if((line = input.readLine()) != null){
            back=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            front=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            top=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            bottom=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            left=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            right=Integer.parseInt(line);
        }
    } //load cube data from file*/
    
    /*moves to manipulate orientation of cube*/
    @Override
    public void top_move_right(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TRB
            x=-x;//ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TMRB
            x=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TMLB
            x=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            z=-z; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            x=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            x=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TMLF
            x=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TMRF
            x=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TRF
            z=-z; //ok
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TRMF
            x=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TRMB
            x=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMLMB
            z=-z; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMLMF
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMRMB
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMRMF
            z=-z; //ok
        }
        
        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    } 
    
    @Override
    public void top_move_left(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TRB
            z=-z; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TMRB
            x=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TMLB
            x=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            x=-x; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            x=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            x=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            z=-z; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TMLF
            x=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TMRF
            x=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TRF
            x=-x; //ok
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TRMF
            x=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TRMB
            x=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMLMB
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMLMF
            z=-z; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMRMB
            z=-z; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMRMF
            x=-x; //ok
        }
        
        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    
    @Override
    public void MT_move_right(){
        if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTRB
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTMRB
            x=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTMLB
            x=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            z=-z; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MTLMB
            x=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MTLMF
            x=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTMLF
            x=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTMRF
            x=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTRF
            z=-z; //ok
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MTRMF
            x=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MTRMB
            x=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }
        
        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    }
    @Override
    public void MD_move_right(){
        if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDRB
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDMRB
            x=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDMLB
            x=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLB
            z=-z; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDLMB
            x=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDLMF
            x=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDMLF
            x=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDMRF
            x=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDRF
            z=-z; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDRMF
            x=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDRMB
            x=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }

        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    }
    
    @Override
    public void MT_move_left(){
        if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTRB
            z=-z;//ok
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTMRB
            x=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTMLB
            x=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            x=-x; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MTLMB
            x=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MTLMF
            x=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            z=-z; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTMLF
            x=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTMRF
            x=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTRF
            x=-x; //ok
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MTRMF
            x=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MTRMB
            x=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }
        
        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    @Override
    public void MD_move_left(){
        if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDRB
            z=-z;//ok
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDMRB
            x=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDMLB
            x=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLB
            x=-x; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDLMB
            x=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDLMF
            x=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            z=-z; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDMLF
            x=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDMRF
            x=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDRF
            x=-x; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDRMF
            x=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDRMB
            x=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }
        
        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    
    @Override
    public void bottom_move_right(){
        if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DRB
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DMRB
            x=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DMLB
            x=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            z=-z; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            x=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            x=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DMLF
            x=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DMRF
            x=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DRF
            z=-z; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DRMF
            x=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DRMB
            x=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMLMB
            z=-z; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMLMF
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMRMB
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMRMF
            z=-z; //ok
        }

        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    }
    
    @Override
    public void bottom_move_left(){
        if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDRB
            z=-z;//ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDMRB
            x=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDMLB
            x=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLB
            x=-x; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDLMB
            x=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDLMF
            x=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            z=-z; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDMLF
            x=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDMRF
            x=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDRF
            x=-x; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDRMF
            x=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDRMB
            x=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMLMB
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMLMF
            z=-z; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMRMB
            z=-z; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMRMF
            x=-x; //ok
        }
        
        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    
    @Override
    public void left_move_down(){
        if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            y=-y; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            y=-NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            y=NEAR_PLACEMENT; //ok
            z=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            z=-z;//ok 
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLB
            y=FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            y=-y; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            y=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            y=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            z=-z; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            y=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            y=-FAR_PLACEMENT; //ok
            z=NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){//MTLMF
            y=-y; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){//MTLMB
            z=-z; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){//MDLMF
            z=-z; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){//MDLMB
            y=-y; //ok
        }
            
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    @Override
    public void left_move_up(){
        if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            z=-z; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            y=NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            y=-NEAR_PLACEMENT; //ok
            z=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            y=-y; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=-FAR_PLACEMENT; //ok
            z=-NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=-FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            z=-z;
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            y=-NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            y=NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            y=-y;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            y=FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            y=FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){//MTLMF
            z=-z;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){//MTLMB
            y=-y;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){//MDLMF
            y=-y;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){//MDLMB
            z=-z;
        }

        temp=top;
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    @Override
    public void ML_move_down(){
        if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            y=-y;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            y=-NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            y=NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            z=-z;
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLB
            y=FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            y=-y;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            y=NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            y=-NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            z=-z;
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            y=-FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            y=-FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }
            
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    @Override
    public void MR_move_down(){
        if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            y=-y;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            y=-NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            y=NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            z=-z;
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLB
            y=FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            y=-y;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            y=NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            y=-NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            z=-z;
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            y=-FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            y=-FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }
            
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    @Override
    public void ML_move_up(){ //ok
        if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            z=-z;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            y=NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            y=-NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            y=-y;
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=-FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=-FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            z=-z;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            y=-NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            y=NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            y=-y;
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            y=FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            y=FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }

        temp=top; //ok
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    @Override
    public void MR_move_up(){
        if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            z=-z;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            y=NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            y=-NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            y=-y;
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=-FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=-FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            z=-z;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            y=-NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            y=NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            y=-y;
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            y=FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            y=FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }

        temp=top;
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    @Override
    public void right_move_down(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            y=-NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            y=NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            z=-z;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLB
            y=FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            y=NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            y=-NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            z=-z;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            y=-FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            y=-FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){//MTRMF
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){//MTRMB
            z=-z;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){//MDRMF
            z=-z;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){//MDRMB
            y=-y;
        }
            
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    @Override
    public void right_move_up(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            z=-z;
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLMF
            y=NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLMB
            y=-NEAR_PLACEMENT;
            z=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=-FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            y=-FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            z=-z;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLMB
            y=-NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLMF
            y=NEAR_PLACEMENT;
            z=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            y=FAR_PLACEMENT;
            z=NEAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            y=FAR_PLACEMENT;
            z=-NEAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){//MTRMF
            z=-z;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){//MTRMB
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){//MDRMF
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){//MDRMB
            z=-z;
        }

        temp=top;
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    @Override
    public void front_face_cw(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TRF
            y=-y; //ok
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTRF
            x=NEAR_PLACEMENT; //ok
            y=-FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDRF
            x=-NEAR_PLACEMENT; //ok
            y=-FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DRF
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DMRF
            x=-FAR_PLACEMENT; //ok
            y=-NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DMLF
            x=-FAR_PLACEMENT; //ok
            y=NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            y=-y; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            x=-NEAR_PLACEMENT; //ok
            y=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            x=NEAR_PLACEMENT; //ok
            y=FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TMLF
            x=FAR_PLACEMENT; //ok
            y=NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TMRF
            x=FAR_PLACEMENT; //ok
            y=-NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){//MDMLF
            y=-y; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){//MTMLF
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){//MDMRF
            x=-x; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){//MTMRF
            y=-y; //ok
        }
        
        temp=top; //ok
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    @Override
    public void front_face_ccw(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TRF
            x=-x; //ok
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTRF
            x=-NEAR_PLACEMENT; //ok
            y=FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDRF
            x=NEAR_PLACEMENT; //ok
            y=FAR_PLACEMENT; //ok
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DRF
            y=-y; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DMRF
            x=FAR_PLACEMENT; //ok
            y=NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DMLF
            x=FAR_PLACEMENT; //ok
            y=-NEAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //DLF
            x=-x; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MDLF
            x=NEAR_PLACEMENT; //ok
            y=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //MTLF
            x=-NEAR_PLACEMENT; //ok
            y=-FAR_PLACEMENT; //ok
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TLF
            y=-y; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TMLF
            x=-FAR_PLACEMENT; //ok
            y=-NEAR_PLACEMENT; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==FAR_PLACEMENT)){ //TMRF
            x=-FAR_PLACEMENT; //ok
            y=NEAR_PLACEMENT; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){//MDMLF
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){//MTMLF
            y=-y; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){//MDMRF
            y=-y; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==FAR_PLACEMENT)){//MTMRF
            x=-x; //ok
        }
        
        temp=top; //ok
        top=right;
        right=bottom;
        bottom=left;
        left=temp;
    }
    
    @Override
    public void MF_face_cw(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TRF
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MTRF
            x=NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDRF
            x=-NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DRF
            x=-x;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DMRF
            x=-FAR_PLACEMENT;
            y=-NEAR_PLACEMENT; 
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DMLF
            x=-FAR_PLACEMENT;
            y=NEAR_PLACEMENT; 
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLF
            y=-y;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDLF
            x=-NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MTLF
            x=NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLF
            x=-x;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMLF
            x=FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMRF
            x=FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }
        
        temp=top;
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    @Override
    public void MB_face_cw(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TRF
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MTRF
            x=NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDRF
            x=-NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DRF
            x=-x;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DMRF
            x=-FAR_PLACEMENT;
            y=-NEAR_PLACEMENT; 
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DMLF
            x=-FAR_PLACEMENT;
            y=NEAR_PLACEMENT; 
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLF
            y=-y;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDLF
            x=-NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MTLF
            x=NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLF
            x=-x;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMLF
            x=FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMRF
            x=FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }
        
        temp=top;
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    @Override
    public void MF_face_ccw(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TRF
            x=-x;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MTRF
            x=-NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDRF
            x=NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DRF
            y=-y;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DMRF
            x=FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DMLF
            x=FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //DLF
            x=-x;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MDLF
            x=NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //MTLF
            x=-NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TLF
            y=-y;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMLF
            x=-FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==NEAR_PLACEMENT)){ //TMRF
            x=-FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }
        
        temp=top;
        top=right;
        right=bottom;
        bottom=left;
        left=temp;
    }
    
    @Override
    public void MB_face_ccw(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TRF
            x=-x;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MTRF
            x=-NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDRF
            x=NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DRF
            y=-y;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DMRF
            x=FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DMLF
            x=FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //DLF
            x=-x;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MDLF
            x=NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //MTLF
            x=-NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TLF
            y=-y;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMLF
            x=-FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-NEAR_PLACEMENT)){ //TMRF
            x=-FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }
        
        temp=top;
        top=right;
        right=bottom;
        bottom=left;
        left=temp;
    }
    
    @Override
    public void back_face_cw(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TRF
            y=-y;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTRF
            x=NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDRF
            x=-NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DRF
            x=-x;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DMRF
            x=-FAR_PLACEMENT;
            y=-NEAR_PLACEMENT; 
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DMLF
            x=-FAR_PLACEMENT;
            y=NEAR_PLACEMENT; 
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLF
            y=-y;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLF
            x=-NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLF
            x=NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLF
            x=-x;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TMLF
            x=FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TMRF
            x=FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){//MDMLB
            y=-y;
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){//MTMLB
            x=-x;
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){//MDMRB
            x=-x;
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){//MTMRB
            y=-y;
        }
        
        temp=top;
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    @Override
    public void back_face_ccw(){
        if((x==FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TRF
            x=-x;
        }else if((x==FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTRF
            x=-NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDRF
            x=NEAR_PLACEMENT;
            y=FAR_PLACEMENT;
        }else if((x==FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DRF
            y=-y;
        }else if((x==NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DMRF
            x=FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DMLB
            x=FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==-FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //DLB
            x=-x;
        }else if((x==-FAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MDLB
            x=NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //MTLB
            x=-NEAR_PLACEMENT;
            y=-FAR_PLACEMENT;
        }else if((x==-FAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TLB
            y=-y;
        }else if((x==-NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TMLB
            x=-FAR_PLACEMENT;
            y=-NEAR_PLACEMENT;
        }else if((x==NEAR_PLACEMENT)&&(y==FAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){ //TMRB
            x=-FAR_PLACEMENT;
            y=NEAR_PLACEMENT;
        }else if((x==-NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){//MDMLB
            x=-x; //ok
        }else if((x==-NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){//MTMLB
            y=-y; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==-NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){//MDMRB
            y=-y; //ok
        }else if((x==NEAR_PLACEMENT)&&(y==NEAR_PLACEMENT)&&(z==-FAR_PLACEMENT)){//MTMRB
            x=-x; //ok
        }
        temp=top;
        top=right;
        right=bottom;
        bottom=left;
        left=temp;
    }
    
    protected float x,y,z;
    protected int back,front,left,right,top,bottom,temp,rotation,x_axis,y_axis,z_axis; 
}