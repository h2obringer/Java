/**
 * @author Obringer
 * Description: This is the implementation of a 2x2 Rubik's Mini Cube. It relies 
 *      on 8 of the defined Cube class objects to make it's manipulations.
 */

package cube;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import pieces.Cube;
import static utils.Variables.*;
import static org.lwjgl.opengl.GL11.*;

public class Rubiks_Cube {
    
    int front,back,top,bottom,left,right; //hold color values to check for a solved cube.
    int x_axis,y_axis,z_axis; //Specify the axis to rotate the cube around. Value of 1 will be the axis to rotate against.
    int move_rotation; //rotation in degrees for an animation in progress.
    int direction; //identify the direction to animate cube pieces.
    int speed; //the speed to animate cube pieces during moves.
    int x_rotation,y_rotation; //rotation to render the entire cube in degrees.
    boolean in_progress; //is a move animation in progress?
    Cube temp,tlf,tlb,trf,trb,dlf,dlb,drf,drb; //The individual cube pieces making up the entire cube.
    
    //Constructor
    public Rubiks_Cube(){
        tlf = new Cube(ZERO,ONE,ZERO,ZERO,-PLACEMENT,PLACEMENT,PLACEMENT,
                    BLACK,GREEN,YELLOW,BLACK,RED,BLACK,BLACK);
        trf = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,PLACEMENT,PLACEMENT,
                BLACK,GREEN,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        tlb = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,PLACEMENT,-PLACEMENT,
                BLUE,BLACK,YELLOW,BLACK,RED,BLACK,BLACK);
        trb = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,PLACEMENT,-PLACEMENT,
                BLUE,BLACK,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        dlf = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,-PLACEMENT,PLACEMENT,
                BLACK,GREEN,BLACK,WHITE,RED,BLACK,BLACK);
        dlb = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,-PLACEMENT,-PLACEMENT,
                BLUE,BLACK,BLACK,WHITE,RED,BLACK,BLACK);
        drf = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,-PLACEMENT,PLACEMENT,
                BLACK,GREEN,BLACK,WHITE,BLACK,ORANGE,BLACK);
        drb = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,-PLACEMENT,-PLACEMENT,
                BLUE,BLACK,BLACK,WHITE,BLACK,ORANGE,BLACK);
        front=back=left=right=top=bottom=move_rotation=x_axis=y_axis=z_axis=
              direction=x_rotation=y_rotation=ZERO;
        speed=MAX_SPEED;
        in_progress=false;
    }
    
    //********************************************************************
    // Function: draw
    // Purpose: Renders the entire mini cube. Assigns an ID to the individual objects
    //      for selection purposes. 
    // Parameters: 
    //      mode - GL_RENDER or GL_SELECT. Render the object or assign an ID
    //          to identify the object with a mouse click?
    // Member/Global Variables: tlf,tlb,trf,trb,dlf,dlb,drf,drb,ID_TLF,ID_TLB,
    //      ID_TRF,ID_TRB,ID_DLF,ID_DLB,ID_DRF,ID_DRB
    // Pre Conditions: Use within mouse function for selection purposes.
    // Post Conditions: None
    // Calls: Cube::draw
    //********************************************************************
    public void draw(int mode){
        glPushMatrix();
        glRotatef(tlf.get_rotation(),tlf.get_x_axis(),tlf.get_y_axis(),tlf.get_z_axis());
        glTranslatef(tlf.get_x(),tlf.get_y(),tlf.get_z());
        tlf.draw(mode,ID_TLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tlb.get_rotation(),tlb.get_x_axis(),tlb.get_y_axis(),tlb.get_z_axis());
        glTranslatef(tlb.get_x(),tlb.get_y(),tlb.get_z()); 
        tlb.draw(mode,ID_TLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trf.get_rotation(),trf.get_x_axis(),trf.get_y_axis(),trf.get_z_axis());
        glTranslatef(trf.get_x(),trf.get_y(),trf.get_z()); 
        trf.draw(mode,ID_TRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trb.get_rotation(),trb.get_x_axis(),trb.get_y_axis(),trb.get_z_axis());
        glTranslatef(trb.get_x(),trb.get_y(),trb.get_z()); 
        trb.draw(mode,ID_TRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlf.get_rotation(),dlf.get_x_axis(),dlf.get_y_axis(),dlf.get_z_axis());
        glTranslatef(dlf.get_x(),dlf.get_y(),dlf.get_z()); 
        dlf.draw(mode,ID_DLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlb.get_rotation(),dlb.get_x_axis(),dlb.get_y_axis(),dlb.get_z_axis());
        glTranslatef(dlb.get_x(),dlb.get_y(),dlb.get_z()); 
        dlb.draw(mode,ID_DLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drf.get_rotation(),drf.get_x_axis(),drf.get_y_axis(),drf.get_z_axis());
        glTranslatef(drf.get_x(),drf.get_y(),drf.get_z()); 
        drf.draw(mode,ID_DRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drb.get_rotation(),drb.get_x_axis(),drb.get_y_axis(),drb.get_z_axis());
        glTranslatef(drb.get_x(),drb.get_y(),drb.get_z()); 
        drb.draw(mode,ID_DRB); 
        glPopMatrix();
    }
    
    //********************************************************************
    // Function: check_win
    // Purpose: Check colors of all faces of the overall Rubik's Cube. If faces
    //      of individual cubes are all the same then true is returned.
    // Parameters: None
    // Member/Global Variables: None
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: check_front,check_back,check_top,check_bottom,check_left,check_right
    //********************************************************************
    public boolean check_win(){
        if(check_front()&&check_back()&&check_top()&&check_bottom()&&check_left()&&check_right()){
            return true;
        }else{
            return false;
        }
    }
    
    //********************************************************************
    // Function: check_front
    // Purpose: Check colors of all faces of the front faced cubes. Returns true if
    //      color of all individual cubes' front faces are the same.
    // Parameters: None
    // Member/Global Variables: None
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::get_front
    //********************************************************************
    private boolean check_front(){
        front=tlf.get_front();
        if((front==tlf.get_front())&&(front==trf.get_front())&&
              (front==dlf.get_front())&&(front==drf.get_front())){
             return true;
        }else{
             return false;
        }
    }
    
    //********************************************************************
    // Function: check_back
    // Purpose: Check colors of all faces of the back faced cubes. Returns true if
    //      color of all individual cubes' back faces are the same.
    // Parameters: None
    // Member/Global Variables: None
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::get_back
    //********************************************************************
    private boolean check_back(){
        back=tlb.get_back();
        if((back==tlb.get_back())&&(back==trb.get_back())&&
              (back==dlb.get_back())&&(back==drb.get_back())){
            return true;
        }else{
            return false;
        }
    }
    
    //********************************************************************
    // Function: check_top
    // Purpose: Check colors of all faces of the top faced cubes. Returns true if
    //      color of all individual cubes' top faces are the same.
    // Parameters: None
    // Member/Global Variables: None
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::get_top
    //********************************************************************
    private boolean check_top(){
        top=tlf.get_top();
        if((top==tlf.get_top())&&(top==trf.get_top())&&
              (top==tlb.get_top())&&(top==trb.get_top())){
            return true;
        }else{
            return false;
        }
    }
    
    //********************************************************************
    // Function: check_bottom
    // Purpose: Check colors of all faces of the bottom faced cubes. Returns true if
    //      color of all individual cubes' bottom faces are the same.
    // Parameters: None
    // Member/Global Variables: None
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::get_bottom
    //********************************************************************
    private boolean check_bottom(){
        bottom=dlf.get_bottom();
        if((bottom==dlf.get_bottom())&&(bottom==drf.get_bottom())&&
              (bottom==dlb.get_bottom())&&(bottom==drb.get_bottom())){
            return true;
        }else{
            return false;
        }
    }
    
    //********************************************************************
    // Function: check_left
    // Purpose: Check colors of all faces of the left faced cubes. Returns true if
    //      color of all individual cubes' left faces are the same.
    // Parameters: None
    // Member/Global Variables: None
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::get_left
    //********************************************************************
    private boolean check_left(){
        left=tlf.get_left();
        if((left==tlf.get_left())&&(left==tlb.get_left())&&
              (left==dlf.get_left())&&(left==dlb.get_left())){
            return true;
        }else{
            return false;
        }
    }
    
    //********************************************************************
    // Function: check_right
    // Purpose: Check colors of all faces of the right faced cubes. Returns true if
    //      color of all individual cubes' right faces are the same.
    // Parameters: None
    // Member/Global Variables: None
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::get_right
    //********************************************************************
    private boolean check_right(){
        right=trf.get_right();
        if((right==trf.get_right())&&(right==trb.get_right())&&
              (right==drf.get_right())&&(right==drb.get_right())){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean get_in_progress(){
        return in_progress;
    }
    
    public int get_move_rotation(){
        return move_rotation;
    }
    
    public int get_x_rotation(){
        return x_rotation;
    }
    
    public int get_y_rotation(){
        return y_rotation;
    }
    
    public void mutate_x_rotation(int x){
        x_rotation=x;
    }
    
    public void mutate_y_rotation(int y){
        y_rotation=y;
    }
    
    public int get_x_axis(){
        return x_axis;
    }
    
    public int get_y_axis(){
        return y_axis;
    }
    
    public int get_z_axis(){
        return z_axis;
    }
    
    public int get_direction(){
        return direction;
    }
    
    //********************************************************************
    // Function: increase_speed
    // Purpose: Increases the speed of animation of a move.
    // Parameters: None
    // Member/Global Variables: speed, ONE, MAX_SPEED
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    public void increase_speed(){
        speed+=ONE;
        if(speed>MAX_SPEED){
            speed=MAX_SPEED;
        }
    }
    
    //********************************************************************
    // Function: decrease_speed
    // Purpose: Decreases the speed of animation of a move.
    // Parameters: None
    // Member/Global Variables: speed, ONE, MIN_SPEED
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    public void decrease_speed(){
        speed-=ONE;
        if(speed<MIN_SPEED){
            speed=MIN_SPEED;
        }
    }
    
    public void mutate_direction(int d){
        direction=d;
    }
    
    public void mutate_in_progress(Boolean progress){
        in_progress=progress;
    }
    
    public void increase_move_rotation(){
        move_rotation+=speed;
    }
    
    public void decrease_move_rotation(){
        move_rotation-=speed;
    }
    
    public void reset_move_rotation(){
        move_rotation=ZERO;
    }
    
    //********************************************************************
    // Function: write_to_file
    // Purpose: Save information for all pieces of the cube for use later.
    // Parameters: bw - the buffered writer to write the data to.
    // Member/Global Variables: move_rotation,x_rotation,y_rotation,
    //      tlf,tlb,trf,trb,dlf,dlb,drf,drb
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::write_cube
    //********************************************************************
    public void write_to_file(BufferedWriter bw) throws IOException{
        bw.write(Integer.toString(move_rotation)); 
        bw.newLine();
        bw.write(Integer.toString(x_rotation));
        bw.newLine();
        bw.write(Integer.toString(y_rotation));
        bw.newLine();
        tlf.write_cube(bw);
        tlb.write_cube(bw);
        trf.write_cube(bw);
        trb.write_cube(bw);
        dlf.write_cube(bw);
        dlb.write_cube(bw);
        drf.write_cube(bw);
        drb.write_cube(bw);
    }
    
    //********************************************************************
    // Function: load_from_file
    // Purpose: Load the data for all cube pieces to allow rebuilding of the entire
    //      Rubik's Mini Cube.
    // Parameters: None
    // Member/Global Variables: move_rotation,x_rotation,y_rotation,tlf,tlb,
    //      trf,trb,dlf,dlb,drf,drb
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::load_cube
    //********************************************************************
    public void load_from_file(BufferedReader input) throws IOException{
        String line = null;
        if((line = input.readLine()) != null){
            move_rotation=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            x_rotation=Integer.parseInt(line);
        }
        if((line = input.readLine()) != null){
            y_rotation=Integer.parseInt(line);
        }
        
        tlf.load_cube(input);
        tlb.load_cube(input);
        trf.load_cube(input);
        trb.load_cube(input);
        dlf.load_cube(input);
        dlb.load_cube(input);
        drf.load_cube(input);
        drb.load_cube(input);
    }
    
    //********************************************************************
    // Function: choose_cubes_top_left_right
    // Purpose: Loads the top face cubes into an array for easier manipulation
    //      of the correct cubes for move animation and cube translation left and right.
    // Parameters: blocks - a Cube array defined for selection of the proper cubes in 
    //      move translation and manipulation.
    // Member/Global Variables: trf,trb,tlb,tlf
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    public void choose_cubes_top_left_right(Cube[] blocks){
        blocks[0]=trf;
        blocks[1]=trb;
        blocks[2]=tlb;
        blocks[3]=tlf;
    }
    
    //********************************************************************
    // Function: choose_cubes_bottom_left_right
    // Purpose: Loads the bottom face cubes into an array for easier manipulation
    //      of the correct cubes for move animation and cube translation left and right.
    // Parameters: blocks - a Cube array defined for selection of the proper cubes in 
    //      move translation and manipulation.
    // Member/Global Variables: drf,drb,dlb,dlf
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    public void choose_cubes_bottom_left_right(Cube[] blocks){
        blocks[0]=drf;
        blocks[1]=drb;
        blocks[2]=dlb;
        blocks[3]=dlf;
    }
    
    //********************************************************************
    // Function: choose_cubes_left_up_down
    // Purpose: Loads the left face cubes into an array for easier manipulation
    //      of the correct cubes for move animation and cube translation up and down.
    // Parameters: blocks - a Cube array defined for selection of the proper cubes in 
    //      move translation and manipulation.
    // Member/Global Variables: tlf,tlb,dlb,dlf
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    public void choose_cubes_left_up_down(Cube[] blocks){
        blocks[0]=tlf;
        blocks[1]=tlb;
        blocks[2]=dlb;
        blocks[3]=dlf;
    }
    
    //********************************************************************
    // Function: choose_cubes_right_up_down
    // Purpose: Loads the right face cubes into an array for easier manipulation
    //      of the correct cubes for move animation and cube translation up and down.
    // Parameters: blocks - a Cube array defined for selection of the proper cubes in 
    //      move translation and manipulation.
    // Member/Global Variables: trf,trb,drb,drf
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    public void choose_cubes_right_up_down(Cube[] blocks){
        blocks[0]=trf;
        blocks[1]=trb;
        blocks[2]=drb;
        blocks[3]=drf;
    }
    
    //********************************************************************
    // Function: choose_cubes_front_cw_ccw
    // Purpose: Loads the front face cubes into an array for easier manipulation
    //      of the correct cubes for move animation and cube translation clockwise and
    //      counter-clockwise.
    // Parameters: blocks - a Cube array defined for selection of the proper cubes in 
    //      move translation and manipulation.
    // Member/Global Variables: trf,drf,dlf,tlf
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    public void choose_cubes_front_cw_ccw(Cube[] blocks){
        blocks[0]=trf;
        blocks[1]=drf;
        blocks[2]=dlf;
        blocks[3]=tlf;
    }
    
    //********************************************************************
    // Function: choose_cubes_back_cw_ccw
    // Purpose: Loads the back face cubes into an array for easier manipulation
    //      of the correct cubes for move animation and cube translation clockwise and
    //      counter-clockwise.
    // Parameters: blocks - a Cube array defined for selection of the proper cubes in 
    //      move translation and manipulation.
    // Member/Global Variables: trb,drb,dlb,tlb
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: None
    //********************************************************************
    public void choose_cubes_back_cw_ccw(Cube[] blocks){
        blocks[0]=trb;
        blocks[1]=drb;
        blocks[2]=dlb;
        blocks[3]=tlb;
    }
    
    //********************************************************************
    // Function: top_move_right
    // Purpose: Translate all top faced cubes to replace the cubes and their face
    //      colors into their new positions in a right turn of the top cubes.
    // Parameters: None
    // Member/Global Variables: trf,trb,tlb,tlf,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::top_move_right
    //********************************************************************
    public void top_move_right(){
        trf.top_move_right();
        trb.top_move_right();
        tlb.top_move_right();
        tlf.top_move_right();

        temp=trf;
        trf=tlf;
        tlf=tlb;
        tlb=trb;
        trb=temp;
    }
    
    //********************************************************************
    // Function: top_move_left
    // Purpose: Translate all top faced cubes to replace the cubes and their face
    //      colors into their new positions in a left turn of the top cubes.
    // Parameters: None
    // Member/Global Variables: trf,trb,tlb,tlf,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::top_move_left
    //********************************************************************
    public void top_move_left(){
        trf.top_move_left();
        trb.top_move_left();
        tlb.top_move_left();
        tlf.top_move_left();

        temp=trf;
        trf=trb;
        trb=tlb;
        tlb=tlf;
        tlf=temp;
    }
    
    //********************************************************************
    // Function: bottom_move_right
    // Purpose: Translate all bottom faced cubes to replace the cubes and their face
    //      colors into their new positions in a right turn of the bottom cubes.
    // Parameters: None
    // Member/Global Variables: drf,drb,dlb,dlf,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::bottom_move_right
    //********************************************************************
    public void bottom_move_right(){
        drf.bottom_move_right();
        drb.bottom_move_right();
        dlb.bottom_move_right();
        dlf.bottom_move_right();

        temp=drf;
        drf=dlf;
        dlf=dlb;
        dlb=drb;
        drb=temp;
    }
    
    //********************************************************************
    // Function: bottom_move_left
    // Purpose: Translate all bottom faced cubes to replace the cubes and their face
    //      colors into their new positions in a left turn of the bottom cubes.
    // Parameters: None
    // Member/Global Variables: drf,drb,dlb,dlf,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::bottom_move_left
    //********************************************************************
    public void bottom_move_left(){
        drf.bottom_move_left();
        drb.bottom_move_left();
        dlb.bottom_move_left();
        dlf.bottom_move_left();

        temp=drf;
        drf=drb;
        drb=dlb;
        dlb=dlf;
        dlf=temp;
    }
    
    //********************************************************************
    // Function: left_move_down
    // Purpose: Translate all left faced cubes to replace the cubes and their face
    //      colors into their new positions in a downward turn of the left cubes.
    // Parameters: None
    // Member/Global Variables: tlf,tlb,dlb,dlf,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::left_move_down
    //********************************************************************
    public void left_move_down(){
        tlf.left_move_down();
        tlb.left_move_down();
        dlb.left_move_down();
        dlf.left_move_down();

        temp=tlf;
        tlf=tlb;
        tlb=dlb;
        dlb=dlf;
        dlf=temp;
    }
    
    //********************************************************************
    // Function: left_move_up
    // Purpose: Translate all left faced cubes to replace the cubes and their face
    //      colors into their new positions in a upward turn of the left cubes.
    // Parameters: None
    // Member/Global Variables: tlf,dlf,dlb,tlb,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::left_move_up
    //********************************************************************
    public void left_move_up(){
        tlf.left_move_up();
        tlb.left_move_up();
        dlb.left_move_up();
        dlf.left_move_up();

        temp=tlf;
        tlf=dlf;
        dlf=dlb;
        dlb=tlb;
        tlb=temp;
    }
    
    //********************************************************************
    // Function: right_move_down
    // Purpose: Translate all right faced cubes to replace the cubes and their face
    //      colors into their new positions in a downward turn of the right cubes.
    // Parameters: None
    // Member/Global Variables: trf,drf,drb,trb,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::right_move_down
    //********************************************************************
    public void right_move_down(){
        trf.right_move_down();
        trb.right_move_down();
        drb.right_move_down();
        drf.right_move_down();

        temp=trf;
        trf=trb;
        trb=drb;
        drb=drf;
        drf=temp;
    }
    
    //********************************************************************
    // Function: right_move_up
    // Purpose: Translate all right faced cubes to replace the cubes and their face
    //      colors into their new positions in a upward turn of the right cubes.
    // Parameters: None
    // Member/Global Variables: trf,drf,drb,trb,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::right_move_up
    //********************************************************************
    public void right_move_up(){
        trf.right_move_up();
        trb.right_move_up();
        drb.right_move_up();
        drf.right_move_up();

        temp=trf;
        trf=drf;
        drf=drb;
        drb=trb;
        trb=temp;
    }
    
    //********************************************************************
    // Function: front_face_cw
    // Purpose: Translate all front faced cubes to replace the cubes and their face
    //      colors into their new positions in a clockwise turn of the front cubes.
    // Parameters: None
    // Member/Global Variables: trf,tlf,dlf,drf,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::front_face_cw
    //********************************************************************
    public void front_face_cw(){
        trf.front_face_cw();
        drf.front_face_cw();
        dlf.front_face_cw();
        tlf.front_face_cw();

        temp=trf;
        trf=tlf;
        tlf=dlf;
        dlf=drf;
        drf=temp;
    }
    
    //********************************************************************
    // Function: front_face_ccw
    // Purpose: Translate all front faced cubes to replace the cubes and their face
    //      colors into their new positions in a counter-clockwise turn of the front cubes.
    // Parameters: None
    // Member/Global Variables: trf,tlf,dlf,drf,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::front_face_ccw
    //********************************************************************
    public void front_face_ccw(){
        trf.front_face_ccw();
        drf.front_face_ccw();
        dlf.front_face_ccw();
        tlf.front_face_ccw();

        temp=trf;
        trf=drf;
        drf=dlf;
        dlf=tlf;
        tlf=temp;
    }
    
    //********************************************************************
    // Function: back_face_cw
    // Purpose: Translate all back faced cubes to replace the cubes and their face
    //      colors into their new positions in a clockwise turn of the back cubes.
    // Parameters: None
    // Member/Global Variables: trb,tlb,dlb,drb,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::back_face_cw
    //********************************************************************
    public void back_face_cw(){
        trb.back_face_cw();
        drb.back_face_cw();
        dlb.back_face_cw();
        tlb.back_face_cw();

        temp=trb;
        trb=tlb;
        tlb=dlb;
        dlb=drb;
        drb=temp;
    }
    
    //********************************************************************
    // Function: back_face_ccw
    // Purpose: Translate all back faced cubes to replace the cubes and their face
    //      colors into their new positions in a counter-clockwise turn of the back cubes.
    // Parameters: None
    // Member/Global Variables: trb,tlb,dlb,drb,temp
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::back_face_ccw
    //********************************************************************
    public void back_face_ccw(){
        trb.back_face_ccw();
        drb.back_face_ccw();
        dlb.back_face_ccw();
        tlb.back_face_ccw();

        temp=trb;
        trb=drb;
        drb=dlb;
        dlb=tlb;
        tlb=temp;
    }
    
    //********************************************************************
    // Function: scramble_cube
    // Purpose: Randomly selects moves to apply to the Rubik's Mini Cube in order
    //      to scramble it.
    // Parameters: None
    // Member/Global Variables: move_rotation,x_rotation,y_rotation,ZERO,SCRAMBLE,
    //      TOTAL_MOVES,MOVE_TRM,MOVE_BMR,MOVE_LMU,MOVE_RMU,MOVE_TML,MOVE_BML,
    //      MOVE_LMD,MOVE_RMD,MOVE_FCCW,MOVE_BCCW,MOVE_FCW,MOVE_BCW
    // Pre Conditions: None
    // Post Conditions: None
    // Calls: Cube::top_move_right,Cube::bottom_move_right,Cube::left_move_up,
    //      Cube::right_move_up,Cube::top_move_left,Cube::top_move_left,
    //      Cube::left_move_down,Cube::right_move_down,Cube::front_move_ccw,
    //      Cube::back_move_ccw,Cube::front_move_cw,Cube::back_move_cw
    //********************************************************************
    public void scramble_cube(){
        move_rotation=ZERO;
        x_rotation=ZERO;
        y_rotation=ZERO;
        int[] moves = new int[SCRAMBLE];
        Random generator = new Random();
        for(int i=ZERO;i<SCRAMBLE;i++){
          moves[i]=generator.nextInt()%TOTAL_MOVES;
        }
        for(int i=ZERO;i<SCRAMBLE;i++){
            switch(moves[i]){
                case MOVE_TMR:
                    top_move_right();
                    break;
                case MOVE_BMR:
                    bottom_move_right();
                    break;
                case MOVE_LMU:
                    left_move_up();
                    break;
                case MOVE_RMU:
                    right_move_up();
                    break;
                case MOVE_TML:
                    top_move_left();
                    break;
                case MOVE_BML:
                    bottom_move_left();
                    break;
                case MOVE_LMD:
                    left_move_down();
                    break;
                case MOVE_RMD:
                    right_move_down();
                    break;
                case MOVE_FCCW:
                    front_face_ccw();
                    break;
                case MOVE_BCCW:
                    back_face_ccw();
                    break;
                case MOVE_FCW:
                    front_face_cw();
                    break;
                case MOVE_BCW:
                    back_face_cw();
                    break;
            }
        }
    }
}
