/**
 * @author Obringer
 * Description: This is the implementation of the cube's interface. This defines
 *      how a cube is built and translated. 
 */

package pieces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import static utils.Variables.*;
import static org.lwjgl.opengl.GL11.*;

public class Cube implements Icube{
    
    protected float x,y,z; //coordinates of the cube
    protected int back,front,left,right,top,bottom,temp; //color identifiers for the different faces of the cube.
    protected int rotation; //Rotation the cube will be rendered in degrees.
    protected int x_axis,y_axis,z_axis; //Axies that the cube will be rotated around. A value of 1 will rotate around that axis.
    
    //Constructor
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
    
    //********************************************************************
    // Function: face
    // Purpose: Builds the face of a cube. 
    // Parameters: 
    //      a - Vertex in array for the face.
    //      b - Vertex in array for the face.
    //      c - Vertex in array for the face.
    //      d - Vertex in array for the face. 
    //      color - The color identifier to render the face in.
    // Member/Global Variables: X_POS, Y_POS, Z_POS
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
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
    };
    //********************************************************************
    // Function: build_cube
    // Purpose: Build a single cube
    // Parameters: None
    // Member/Global Variables:
    //      back - the color to render the back face with
    //      top - the color to render the top face with
    //      left - the color to render the left face with
    //      bottom - the color to render the bottom face with
    //      right - the color to render the right face with
    //      front - the color to render the front face with
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: face
    //********************************************************************
    @Override
    public void build_cube(){
        face(1,0,3,2,back);
        face(3,7,6,2,top);
        face(7,3,0,4,left);
        face(0,4,5,1,bottom);
        face(2,6,5,1,right);
        face(4,5,6,7,front);
    };
    
    //********************************************************************
    // Function: draw
    // Purpose: Renders the cube. Assigns an ID to the drawing for selection
    //      purposes. 
    // Parameters: 
    //      mode - GL_RENDER or GL_SELECT. Render the object or assign an ID
    //          to identify the object with a mouse click?
    //      NAME - The ID to assign to the object.
    // Member/Global Variables: None
    // Pre Conditions: Use within mouse function for selection purposes.
    // Post Conditions: None
    // Calls: build_cube
    //********************************************************************
    @Override
    public void draw(int mode,int NAME){
        if(mode==GL_SELECT){
            glLoadName(NAME);
        }
        build_cube();
    };
    
    @Override
    public float get_x(){
        return x;
    } //get position of cube
    
    @Override
    public float get_y(){
        return y;
    } //get position of cube
    
    @Override
    public float get_z(){
        return z;
    } //get position of cube
    
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
    
    //********************************************************************
    // Function: write_cube
    // Purpose: Save the data on the current cube for use later.
    // Parameters: bw - the buffered writer to write the data to.
    // Member/Global Variables: 
    //      x_axis - \
    //      y_axis -  \
    //      z_axis -   \
    //      rotation -  \
    //      x -          \
    //      y -           \ Member data variables to save.
    //      z -           /
    //      back -       /
    //      front -     /
    //      top -      /
    //      bottom -  /
    //      left -   /
    //      right - /
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
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
    }
    
    //********************************************************************
    // Function: load_cube
    // Purpose: Load the data for a cube to allow rebuilding of a saved cube.
    // Parameters: input - the buffered reader to read data from
    // Member/Global Variables: 
    //      x_axis - \
    //      y_axis -  \
    //      z_axis -   \
    //      rotation -  \
    //      x -          \
    //      y -           \ Member data variables to load.
    //      z -           /
    //      back -       /
    //      front -     /
    //      top -      /
    //      bottom -  /
    //      left -   /
    //      right - /
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
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
    
    //********************************************************************
    // Function: top_move_right
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a top face move right execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,left,back,right,front 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void top_move_right(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            x=-x;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            z=-z;
        }

        //switch orientation of face colors
        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    } 
    
    //********************************************************************
    // Function: top_move_left
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a top face move left execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,left,back,right,front 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void top_move_left(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            z=-z;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            x=-x;
        }
        
        //switch orientation of face colors
        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    
    //********************************************************************
    // Function: bottom_move_right
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a bottom face move right execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,left,back,right,front 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void bottom_move_right(){
        if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            x=-x;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            z=-z;
        }

        //switch orientation of face colors
        temp=left;
        left=back;
        back=right;
        right=front;
        front=temp;
    }
    
    //********************************************************************
    // Function: top_move_left
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a bottom face move left execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,left,back,right,front 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void bottom_move_left(){
        if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            z=-z;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            x=-x;
        }

        //switch orientation of face colors
        temp=left;
        left=front;
        front=right;
        right=back;
        back=temp;
    }
    
    //********************************************************************
    // Function: left_move_down
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a left face move down execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,top,back,bottom,front 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
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
            
        //switch orientation of face colors
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    //********************************************************************
    // Function: left_move_up
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a left face move up execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,top,back,bottom,front 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void left_move_up(){
        if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            z=-z;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            y=-y;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            z=-z;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            y=-y;
        }

        //switch orientation of face colors
        temp=top;
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    //********************************************************************
    // Function: right_move_down
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a right face move down execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,top,back,bottom,front 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void right_move_down(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            y=-y;
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            z=-z;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            y=-y;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            z=-z;
        }
            
        //switch orientation of face colors.
        temp=top;
        top=back;
        back=bottom;
        bottom=front;
        front=temp;
    }
    
    //********************************************************************
    // Function: right_move_up
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a right face move up execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,top,back,bottom,front 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void right_move_up(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            z=-z; 
        }else if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            y=-y; 
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            z=-z; 
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            y=-y;
        }

        //switch orientation of face colors
        temp=top;
        top=front;
        front=bottom;
        bottom=back;
        back=temp;
    }
    
    //********************************************************************
    // Function: front_face_cw
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a front face move clockwise execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,top,left,bottom,right 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void front_face_cw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            y=-y;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            x=-x;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            y=-y;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            x=-x;
        }
        
        //switch orientation of face colors
        temp=top;
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    //********************************************************************
    // Function: front_face_ccw
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a front face move counter-clockwise execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,top,left,bottom,right 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void front_face_ccw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TRF
            x=-x;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DRF
            y=-y;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==PLACEMENT)){ //DLF
            x=-x;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==PLACEMENT)){ //TLF
            y=-y;
        }
        
        //switch orientation of face colors
        temp=top;
        top=right;
        right=bottom;
        bottom=left;
        left=temp;
    }
    
    //********************************************************************
    // Function: back_face_cw
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a back face move clockwise execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,top,left,bottom,right 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void back_face_cw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            y=-y;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            y=-y;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            x=-x;
        }
        
        //switch orientation of face colors
        temp=top;
        top=left;
        left=bottom;
        bottom=right;
        right=temp;
    }
    
    //********************************************************************
    // Function: back_face_ccw
    // Purpose: Re-assign cube values to render the object where it should appear
    //      after a back face move counter-clockwise execution. Re-orients face colors.
    // Parameters: None
    // Member/Global Variables: x,y,z,PLACEMENT,temp,top,left,bottom,right 
    // Pre Conditions: Cube is in motion prior to function call.
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    @Override
    public void back_face_ccw(){
        if((x==PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TRB
            x=-x;
        }else if((x==PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DRB
            y=-y;
        }else if((x==-PLACEMENT)&&(y==-PLACEMENT)&&(z==-PLACEMENT)){ //DLB
            x=-x;
        }else if((x==-PLACEMENT)&&(y==PLACEMENT)&&(z==-PLACEMENT)){ //TLB
            y=-y;
        }

        //switch orientation of face colors
        temp=top;
        top=right;
        right=bottom;
        bottom=left;
        left=temp;
    }
}