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
    
    //FIX LATER
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
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            x=-x;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TMB
            x=-PLACEMENT;
            z=ZERO;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TLM
            x=ZERO;
            z=PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            x=-x;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TMF
            x=PLACEMENT;
            z=ZERO;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            z=-z;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TRM
            x=ZERO;
            z=-PLACEMENT;
        }

        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    } 
    
    @Override
    public void top_move_left(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            z=-z;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TMB
            x=PLACEMENT;
            z=ZERO;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TLM
            x=ZERO;
            z=-PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            z=-z;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TMF
            x=-PLACEMENT;
            z=ZERO;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            x=-x;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TRM
            x=ZERO;
            z=PLACEMENT;
        }
        
        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    
    @Override
    public void middle_horizontal_move_right(){
        if((x==PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MRB
            x=-x;
        }else if((x==ZERO)&&(y==ZERO)&&(z==-PLACEMENT)){ //MMB
            x=-PLACEMENT;
            z=ZERO;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MLB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==ZERO)){ //MLM
            x=ZERO;
            z=PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MLF
            x=-x;
        }else if((x==ZERO)&&(y==ZERO)&&(z==PLACEMENT)){ //MMF
            x=PLACEMENT;
            z=ZERO;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MRF
            z=-z;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==ZERO)){ //MRM
            x=ZERO;
            z=-PLACEMENT;
        }
            
        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    }
    
    @Override
    public void middle_horizontal_move_left(){
        if((x==PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MRB
            z=-z;
        }else if((x==ZERO)&&(y==ZERO)&&(z==-PLACEMENT)){ //MMB
            x=PLACEMENT;
            z=ZERO;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MLB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==ZERO)){ //MLM
            x=ZERO;
            z=-PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MLF
            z=-z;
        }else if((x==ZERO)&&(y==ZERO)&&(z==PLACEMENT)){ //MMF
            x=-PLACEMENT;
            z=ZERO;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MRF
            x=-x;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==ZERO)){ //MRM
            x=ZERO;
            z=PLACEMENT;
        }
        
        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    
    @Override
    public void bottom_move_right(){
        if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            x=-x;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DMB
            x=-PLACEMENT;
            z=ZERO;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DLM
            x=ZERO;
            z=PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            x=-x;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DMF
            x=PLACEMENT;
            z=ZERO;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            z=-z;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DRM
            x=ZERO;
            z=-PLACEMENT;
        }

        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    }
    
    @Override
    public void bottom_move_left(){
        if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            z=-z;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DMB
            x=PLACEMENT;
            z=ZERO;
          }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DLM
            x=ZERO;
            z=-PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            z=-z;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DMF
            x=-PLACEMENT;
            z=ZERO;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            x=-x;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DRM
            x=ZERO;
            z=PLACEMENT;
        }

        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    
    @Override
    public void left_move_down(){
        if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            y=-y;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TLM
            y=ZERO;
            z=PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MLB
            y=PLACEMENT;
            z=ZERO;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            y=-y;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DLM
            y=ZERO;
            z=-PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            z=-z;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MLF
            y=-PLACEMENT;
            z=ZERO;
        }
            
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    @Override
    public void left_move_up(){
        if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            z=-z;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TLM
            y=ZERO;
            z=-PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            y=-y;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MLB
            y=-PLACEMENT;
            z=ZERO;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DLM
            y=ZERO;
            z=PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            y=-y;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MLF
            y=PLACEMENT;
            z=ZERO;
        }

        temp=top;
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    @Override
    public void middle_vertical_move_down(){
        if((x==ZERO)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TMF
            y=-y;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==ZERO)){ //TMM
            y=ZERO;
            z=PLACEMENT;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TMB
            z=-z;
        }else if((x==ZERO)&&(y==ZERO)&&(z==-PLACEMENT)){ //MMB
            y=PLACEMENT;
            z=ZERO;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DMB
            y=-y;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==ZERO)){ //DMM
            y=ZERO;
            z=-PLACEMENT;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DMF
            z=-z;
        }else if((x==ZERO)&&(y==ZERO)&&(z==PLACEMENT)){ //MMF
            y=-PLACEMENT;
            z=ZERO;
        }
            
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    @Override
    public void middle_vertical_move_up(){
        if((x==ZERO)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TMF
            z=-z;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==ZERO)){ //TMM
            y=ZERO;
            z=-PLACEMENT;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TMB
            y=-y;
        }else if((x==ZERO)&&(y==ZERO)&&(z==-PLACEMENT)){ //MMB
            y=-PLACEMENT;
            z=ZERO;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DMB
            z=-z;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==ZERO)){ //DMM
            y=ZERO;
            z=PLACEMENT;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DMF
            y=-y;
        }else if((x==ZERO)&&(y==ZERO)&&(z==PLACEMENT)){ //MMF
            y=PLACEMENT;
            z=ZERO;
        }

        temp=top;
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    @Override
    public void right_move_down(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            y=-y;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TRM
            y=ZERO;
            z=PLACEMENT;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            z=-z;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MRB
            y=PLACEMENT;
            z=ZERO;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            y=-y;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DRM
            y=ZERO;
            z=-PLACEMENT;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            z=-z;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MRF
            y=-PLACEMENT;
            z=ZERO;
        }
            
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    @Override
    public void right_move_up(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            z=-z; 
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TRM
            y=ZERO;
            z=-PLACEMENT; 
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            y=-y; 
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MRB
            y=-PLACEMENT;
            z=ZERO; 
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            z=-z; 
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DRM
            y=ZERO;
            z=PLACEMENT; 
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            y=-y;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MRF
            y=PLACEMENT;
            z=ZERO;
        }

        temp=top;
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    @Override
    public void front_face_cw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            y=-y;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MRF
            x=ZERO;
            y=-PLACEMENT;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            x=-x;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DMF
            x=-PLACEMENT;
            y=ZERO; 
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            y=-y;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MLF
            x=ZERO;
            y=PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            x=-x;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TMF
            x=PLACEMENT;
            y=ZERO;
        }
        
        temp=top;
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    @Override
    public void front_face_ccw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            x=-x;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MRF
            x=ZERO;
            y=PLACEMENT;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            y=-y;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DMF
            x=PLACEMENT;
            y=ZERO;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            x=-x;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==PLACEMENT)){ //MLF
            x=ZERO;
            y=-PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            y=-y;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TMF
            x=-PLACEMENT;
            y=ZERO;
        }
        
        temp=top;
        top=right;
        right=bottom;
        bottom=left;
        left=temp;
    }
    
    @Override
    public void middle_face_cw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TRM
            y=-y;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==ZERO)){ //MRM
            x=ZERO;
            y=-PLACEMENT;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DRM
            x=-x;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==ZERO)){ //DMM
            x=-PLACEMENT;
            y=ZERO;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DLM
            y=-y;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==ZERO)){ //MLM
            x=ZERO;
            y=PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TLM
            x=-x;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==ZERO)){ //TMM
            x=PLACEMENT;
            y=ZERO;
        }

        temp=top;
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    @Override
    public void middle_face_ccw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TRM
            x=-x;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==ZERO)){ //MRM
            x=ZERO;
            y=PLACEMENT;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DRM
            y=-y;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==ZERO)){ //DMM
            x=PLACEMENT;
            y=ZERO;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==ZERO)){ //DLM
            x=-x;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==ZERO)){ //MLM
            x=ZERO;
            y=-PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==ZERO)){ //TLM
            y=-y;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==ZERO)){ //TMM
            x=-PLACEMENT;
            y=ZERO;
        }
            
        temp=top;
        top=right;
        right=bottom;
        bottom=left;
        left=temp;
    }
    
    @Override
    public void back_face_cw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            y=-y;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MRB
            x=ZERO;
            y=-PLACEMENT;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            x=-x;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DMB
            x=-PLACEMENT;
            y=ZERO;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            y=-y;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MLB
            x=ZERO;
            y=PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            x=-x;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TMB
            x=PLACEMENT;
            y=ZERO;
        }
        
        temp=top;
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    @Override
    public void back_face_ccw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            x=-x;
        }else if((x==PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MRB
            x=ZERO;
            y=PLACEMENT;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            y=-y;
        }else if((x==ZERO)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DMB
            x=PLACEMENT;
            y=ZERO;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==ZERO)&&(z==-PLACEMENT)){ //MLB
            x=ZERO;
            y=-PLACEMENT;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            y=-y;
        }else if((x==ZERO)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TMB
            x=-PLACEMENT;
            y=ZERO;
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