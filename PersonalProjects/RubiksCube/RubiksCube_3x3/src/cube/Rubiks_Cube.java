package cube;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import pieces.Cube;
import static utils.Variables.*;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Obringer
 */
public class Rubiks_Cube {
    
    int front,back,top,bottom,left,right,move_rotation,x_axis,y_axis,z_axis,
	direction,speed,x_rotation,y_rotation;
    Boolean in_progress;
    Cube temp,tlf,tlm,tlb,tmf,tmm,tmb,trf,trm,trb,mlf,mlm,mlb,mmf,mmm,mmb,mrf,mrm,
            mrb,dlf,dlm,dlb,dmf,dmm,dmb,drf,drm,drb;
    
    public Rubiks_Cube(){
        tlf = new Cube(ZERO,ONE,ZERO,ZERO,-PLACEMENT,PLACEMENT,PLACEMENT,
                    BLACK,GREEN,YELLOW,BLACK,RED,BLACK,BLACK);
        tlm = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,PLACEMENT,ZERO,BLACK,
                BLACK,YELLOW,BLACK,RED,BLACK,BLACK);
        tlb= new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,PLACEMENT,-PLACEMENT,
                BLUE,BLACK,YELLOW,BLACK,RED,BLACK,BLACK);
        tmf = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,PLACEMENT,PLACEMENT,BLACK,
                GREEN,YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmm = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,PLACEMENT,ZERO,BLACK,BLACK,
                YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmb = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,PLACEMENT,-PLACEMENT,BLUE,
                BLACK,YELLOW,BLACK,BLACK,BLACK,BLACK);
        trf = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,PLACEMENT,PLACEMENT,
                BLACK,GREEN,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        trm = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,PLACEMENT,ZERO,BLACK,
                BLACK,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        trb = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,PLACEMENT,-PLACEMENT,
                BLUE,BLACK,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        mlf = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,ZERO,PLACEMENT,BLACK,
                GREEN,BLACK,BLACK,RED,BLACK,BLACK);
        mlm = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,ZERO,ZERO,BLACK,BLACK,
                BLACK,BLACK,RED,BLACK,BLACK);
        mlb = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,ZERO,-PLACEMENT,BLUE,
                BLACK,BLACK,BLACK,RED,BLACK,BLACK);
        mmf = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,ZERO,PLACEMENT,BLACK,GREEN,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mmm = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,ZERO,ZERO,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mmb = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,ZERO,-PLACEMENT,BLUE,BLACK,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mrf = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,ZERO,PLACEMENT,BLACK,
                GREEN,BLACK,BLACK,BLACK,ORANGE,BLACK);
        mrm = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,ZERO,ZERO,BLACK,BLACK,
                BLACK,BLACK,BLACK,ORANGE,BLACK);
        mrb = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,ZERO,-PLACEMENT,BLUE,
                BLACK,BLACK,BLACK,BLACK,ORANGE,BLACK);
        dlf = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,-PLACEMENT,PLACEMENT,
                BLACK,GREEN,BLACK,WHITE,RED,BLACK,BLACK);
        dlm = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,-PLACEMENT,ZERO,BLACK,
                BLACK,BLACK,WHITE,RED,BLACK,BLACK);
        dlb = new Cube(ZERO,ZERO,ZERO,ZERO,-PLACEMENT,-PLACEMENT,-PLACEMENT,
                BLUE,BLACK,BLACK,WHITE,RED,BLACK,BLACK);
        dmf = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,-PLACEMENT,PLACEMENT,BLACK,
                GREEN,BLACK,WHITE,BLACK,BLACK,BLACK);
        dmm = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,-PLACEMENT,ZERO,BLACK,BLACK,
                BLACK,WHITE,BLACK,BLACK,BLACK);
        dmb = new Cube(ZERO,ZERO,ZERO,ZERO,ZERO,-PLACEMENT,-PLACEMENT,BLUE,
                BLACK,BLACK,WHITE,BLACK,BLACK,BLACK);
        drf = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,-PLACEMENT,PLACEMENT,
                BLACK,GREEN,BLACK,WHITE,BLACK,ORANGE,BLACK);
        drm = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,-PLACEMENT,ZERO,BLACK,
                BLACK,BLACK,WHITE,BLACK,ORANGE,BLACK);
        drb = new Cube(ZERO,ZERO,ZERO,ZERO,PLACEMENT,-PLACEMENT,-PLACEMENT,
                BLUE,BLACK,BLACK,WHITE,BLACK,ORANGE,BLACK);
        front=back=left=right=top=bottom=move_rotation=x_axis=y_axis=z_axis=
              direction=x_rotation=y_rotation=ZERO;
        speed=MAX_SPEED;
        in_progress=false;
    }
    
    public void draw(int mode){
        glPushMatrix();
        glRotatef(tlf.get_rotation(),tlf.get_x_axis(),tlf.get_y_axis(),tlf.get_z_axis());
        glTranslatef(tlf.get_x(),tlf.get_y(),tlf.get_z());
        tlf.draw(mode,ID_TLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tlm.get_rotation(),tlm.get_x_axis(),tlm.get_y_axis(),tlm.get_z_axis());
        glTranslatef(tlm.get_x(),tlm.get_y(),tlm.get_z()); 
        tlm.draw(mode,ID_TLM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tlb.get_rotation(),tlb.get_x_axis(),tlb.get_y_axis(),tlb.get_z_axis());
        glTranslatef(tlb.get_x(),tlb.get_y(),tlb.get_z()); 
        tlb.draw(mode,ID_TLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmf.get_rotation(),tmf.get_x_axis(),tmf.get_y_axis(),tmf.get_z_axis());
        glTranslatef(tmf.get_x(),tmf.get_y(),tmf.get_z()); 
        tmf.draw(mode,ID_TMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmm.get_rotation(),tmm.get_x_axis(),tmm.get_y_axis(),tmm.get_z_axis());
        glTranslatef(tmm.get_x(),tmm.get_y(),tmm.get_z()); 
        tmm.draw(mode,ID_TMM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmb.get_rotation(),tmb.get_x_axis(),tmb.get_y_axis(),tmb.get_z_axis());
        glTranslatef(tmb.get_x(),tmb.get_y(),tmb.get_z()); 
        tmb.draw(mode,ID_TMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trf.get_rotation(),trf.get_x_axis(),trf.get_y_axis(),trf.get_z_axis());
        glTranslatef(trf.get_x(),trf.get_y(),trf.get_z()); 
        trf.draw(mode,ID_TRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trm.get_rotation(),trm.get_x_axis(),trm.get_y_axis(),trm.get_z_axis());
        glTranslatef(trm.get_x(),trm.get_y(),trm.get_z()); 
        trm.draw(mode,ID_TRM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trb.get_rotation(),trb.get_x_axis(),trb.get_y_axis(),trb.get_z_axis());
        glTranslatef(trb.get_x(),trb.get_y(),trb.get_z()); 
        trb.draw(mode,ID_TRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mlf.get_rotation(),mlf.get_x_axis(),mlf.get_y_axis(),mlf.get_z_axis());
        glTranslatef(mlf.get_x(),mlf.get_y(),mlf.get_z()); 
        mlf.draw(mode,ID_MLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mlm.get_rotation(),mlm.get_x_axis(),mlm.get_y_axis(),mlm.get_z_axis());
        glTranslatef(mlm.get_x(),mlm.get_y(),mlm.get_z()); 
        mlm.draw(mode,ID_MLM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mlb.get_rotation(),mlb.get_x_axis(),mlb.get_y_axis(),mlb.get_z_axis());
        glTranslatef(mlb.get_x(),mlb.get_y(),mlb.get_z()); 
        mlb.draw(mode,ID_MLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mmf.get_rotation(),mmf.get_x_axis(),mmf.get_y_axis(),mmf.get_z_axis());
        glTranslatef(mmf.get_x(),mmf.get_y(),mmf.get_z()); 
        mmf.draw(mode,ID_MMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mmm.get_rotation(),mmm.get_x_axis(),mmm.get_y_axis(),mmm.get_z_axis());
        glTranslatef(mmm.get_x(),mmm.get_y(),mmm.get_z()); 
        mmm.draw(mode,ID_MMM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mmb.get_rotation(),mmb.get_x_axis(),mmb.get_y_axis(),mmb.get_z_axis());
        glTranslatef(mmb.get_x(),mmb.get_y(),mmb.get_z()); 
        mmb.draw(mode,ID_MMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mrf.get_rotation(),mrf.get_x_axis(),mrf.get_y_axis(),mrf.get_z_axis());
        glTranslatef(mrf.get_x(),mrf.get_y(),mrf.get_z()); 
        mrf.draw(mode,ID_MRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mrm.get_rotation(),mrm.get_x_axis(),mrm.get_y_axis(),mrm.get_z_axis());
        glTranslatef(mrm.get_x(),mrm.get_y(),mrm.get_z()); 
        mrm.draw(mode,ID_MRM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mrb.get_rotation(),mrb.get_x_axis(),mrb.get_y_axis(),mrb.get_z_axis());
        glTranslatef(mrb.get_x(),mrb.get_y(),mrb.get_z()); 
        mrb.draw(mode,ID_MRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlf.get_rotation(),dlf.get_x_axis(),dlf.get_y_axis(),dlf.get_z_axis());
        glTranslatef(dlf.get_x(),dlf.get_y(),dlf.get_z()); 
        dlf.draw(mode,ID_DLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlm.get_rotation(),dlm.get_x_axis(),dlm.get_y_axis(),dlm.get_z_axis());
        glTranslatef(dlm.get_x(),dlm.get_y(),dlm.get_z()); 
        dlm.draw(mode,ID_DLM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlb.get_rotation(),dlb.get_x_axis(),dlb.get_y_axis(),dlb.get_z_axis());
        glTranslatef(dlb.get_x(),dlb.get_y(),dlb.get_z()); 
        dlb.draw(mode,ID_DLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmf.get_rotation(),dmf.get_x_axis(),dmf.get_y_axis(),dmf.get_z_axis());
        glTranslatef(dmf.get_x(),dmf.get_y(),dmf.get_z()); 
        dmf.draw(mode,ID_DMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmm.get_rotation(),dmm.get_x_axis(),dmm.get_y_axis(),dmm.get_z_axis());
        glTranslatef(dmm.get_x(),dmm.get_y(),dmm.get_z()); 
        dmm.draw(mode,ID_DMM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmb.get_rotation(),dmb.get_x_axis(),dmb.get_y_axis(),dmb.get_z_axis());
        glTranslatef(dmb.get_x(),dmb.get_y(),dmb.get_z()); 
        dmb.draw(mode,ID_DMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drf.get_rotation(),drf.get_x_axis(),drf.get_y_axis(),drf.get_z_axis());
        glTranslatef(drf.get_x(),drf.get_y(),drf.get_z()); 
        drf.draw(mode,ID_DRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drm.get_rotation(),drm.get_x_axis(),drm.get_y_axis(),drm.get_z_axis());
        glTranslatef(drm.get_x(),drm.get_y(),drm.get_z()); 
        drm.draw(mode,ID_DRM); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drb.get_rotation(),drb.get_x_axis(),drb.get_y_axis(),drb.get_z_axis());
        glTranslatef(drb.get_x(),drb.get_y(),drb.get_z()); 
        drb.draw(mode,ID_DRB); 
        glPopMatrix();
    }//draw all elements of rubiks cube
    
    public Boolean check_win(){
        if(check_front()&&check_back()&&check_top()&&check_bottom()&&check_left()&&check_right()){
            return true;
        }else{
            return false;
        }
    }//check all faces for same color to check if game is won
    
    private Boolean check_front(){
        front=mmf.get_front();
        if((front==tlf.get_front())&&(front==tmf.get_front())&&(front==trf.get_front())&&
              (front==mlf.get_front())&&(front==mrf.get_front())&&
              (front==dlf.get_front())&&(front==dmf.get_front())&&(front==drf.get_front())){
             return true;
        }else{
             return false;
        }
    }
    
    private Boolean check_back(){
        back=mmb.get_back();
        if((back==tlb.get_back())&&(back==tmb.get_back())&&(back==trb.get_back())&&
              (back==mlb.get_back())&&(back==mrb.get_back())&&
              (back==dlb.get_back())&&(back==dmb.get_back())&&(back==drb.get_back())){
            return true;
        }else{
            return false;
        }
    }//check back face for all same color
    
    private Boolean check_top(){
        top=tmm.get_top();
        if((top==tlf.get_top())&&(top==tmf.get_top())&&(top==trf.get_top())&&
              (top==tlm.get_top())&&(top==trm.get_top())&&
              (top==tlb.get_top())&&(top==tmb.get_top())&&(top==trb.get_top())){
            return true;
        }else{
            return false;
        }
    }//check top face for all same color
    
    private Boolean check_bottom(){
        bottom=dmm.get_bottom();
        if((bottom==dlf.get_bottom())&&(bottom==dmf.get_bottom())&&(bottom==drf.get_bottom())&&
              (bottom==dlm.get_bottom())&&(bottom==drm.get_bottom())&&
              (bottom==dlb.get_bottom())&&(bottom==dmb.get_bottom())&&(bottom==drb.get_bottom())){
            return true;
        }else{
            return false;
        }
    }//check bottom face for all same color
    
    private Boolean check_left(){
        left=mlm.get_left();
        if((left==tlf.get_left())&&(left==tlm.get_left())&&(left==tlb.get_left())&&
              (left==mlf.get_left())&&(left==mlb.get_left())&&
              (left==dlf.get_left())&&(left==dlm.get_left())&&(left==dlb.get_left())){
            return true;
        }else{
            return false;
        }
    }//check left face for all same color
    
    private Boolean check_right(){
        right=mrm.get_right();
        if((right==trf.get_right())&&(right==trm.get_right())&&(right==trb.get_right())&&
              (right==mrf.get_right())&&(right==mrb.get_right())&&
              (right==drf.get_right())&&(right==drm.get_right())&&(right==drb.get_right())){
            return true;
        }else{
            return false;
        }
    }//check right face for all same color
    
    public Boolean get_in_progress(){
        return in_progress;
    } //get status of in_progress
    public int get_move_rotation(){
        return move_rotation;
    } //get animation rotation
    public int get_x_rotation(){
        return x_rotation;
    } //get x rotation
    public int get_y_rotation(){
        return y_rotation;
    } //get y rotation
    public void mutate_x_rotation(int x){
        x_rotation=x;
    } //change x rotation view angle
    public void mutate_y_rotation(int y){
        y_rotation=y;
    } //change y rotation view angle
    public int get_x_axis(){
        return x_axis;
    }//get x axis
    public int get_y_axis(){
        return y_axis;
    }//get y axis
    public int get_z_axis(){
        return z_axis;
    }//get z axis
    public int get_direction(){
        return direction;
    }//get the direction of animation
    public void increase_speed(){
        speed+=ONE;
        if(speed>MAX_SPEED){
            speed=MAX_SPEED;
        }
    }//raise animation speed
    public void decrease_speed(){
        speed-=ONE;
        if(speed<MIN_SPEED){
            speed=MIN_SPEED;
        }
    }//lower animation speed
    public void mutate_direction(int d){
        direction=d;
    }//change the direction of the animation
    public void mutate_in_progress(Boolean progress){
        in_progress=progress;
    }//change status of move_in_progress
    public void increase_move_rotation(){
        move_rotation+=speed;
    }//increase animation rotation
    public void decrease_move_rotation(){
        move_rotation-=speed;
    }//decrease animation rotation
    public void reset_move_rotation(){
        move_rotation=ZERO;
    } //reset animation rotation to zero
    
    public void write_to_file(BufferedWriter bw) throws IOException{ //write all rubik cube data to file
        bw.write(Integer.toString(move_rotation)); 
        bw.newLine();
        bw.write(Integer.toString(x_rotation));
        bw.newLine();
        bw.write(Integer.toString(y_rotation));
        bw.newLine();
        tlf.write_cube(bw);
        tlm.write_cube(bw);
        tlb.write_cube(bw);
        tmf.write_cube(bw);
        tmm.write_cube(bw);
        tmb.write_cube(bw);
        trf.write_cube(bw);
        trm.write_cube(bw);
        trb.write_cube(bw);
        mlf.write_cube(bw);
        mlm.write_cube(bw);
        mlb.write_cube(bw);
        mmf.write_cube(bw);
        mmm.write_cube(bw);
        mmb.write_cube(bw);
        mrf.write_cube(bw);
        mrm.write_cube(bw);
        mrb.write_cube(bw);
        dlf.write_cube(bw);
        dlm.write_cube(bw);
        dlb.write_cube(bw);
        dmf.write_cube(bw);
        dmm.write_cube(bw);
        dmb.write_cube(bw);
        drf.write_cube(bw);
        drm.write_cube(bw);
        drb.write_cube(bw);
    }
    
    public void load_from_file(BufferedReader input) throws IOException{ //load all rubik cube data from file
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
        tlm.load_cube(input);
        tlb.load_cube(input);
        tmf.load_cube(input);
        tmm.load_cube(input);
        tmb.load_cube(input);
        trf.load_cube(input);
        trm.load_cube(input);
        trb.load_cube(input);
        mlf.load_cube(input);
        mlm.load_cube(input);
        mlb.load_cube(input);
        mmf.load_cube(input);
        mmm.load_cube(input);
        mmb.load_cube(input);
        mrf.load_cube(input);
        mrm.load_cube(input);
        mrb.load_cube(input);
        dlf.load_cube(input);
        dlm.load_cube(input);
        dlb.load_cube(input);
        dmf.load_cube(input);
        dmm.load_cube(input);
        dmb.load_cube(input);
        drf.load_cube(input);
        drm.load_cube(input);
        drb.load_cube(input);
    }
    
    /*choose the cubes to manipulate*/
    public void choose_cubes_top_left_right(Cube[] blocks){
        blocks[0]=trf;
        blocks[1]=trm;
        blocks[2]=trb;
        blocks[3]=tmb;
        blocks[4]=tlb;
        blocks[5]=tlm;
        blocks[6]=tlf;
        blocks[7]=tmf;
        blocks[8]=tmm;
    }
    public void choose_cubes_MH_left_right(Cube[] blocks){
        blocks[0]=mrf;
        blocks[1]=mrm;
        blocks[2]=mrb;
        blocks[3]=mmb;
        blocks[4]=mlb;
        blocks[5]=mlm;
        blocks[6]=mlf;
        blocks[7]=mmf;
        blocks[8]=mmm;
    }
    public void choose_cubes_bottom_left_right(Cube[] blocks){
        blocks[0]=drf;
        blocks[1]=drm;
        blocks[2]=drb;
        blocks[3]=dmb;
        blocks[4]=dlb;
        blocks[5]=dlm;
        blocks[6]=dlf;
        blocks[7]=dmf;
        blocks[8]=dmm;
    }
    public void choose_cubes_left_up_down(Cube[] blocks){
        blocks[0]=tlf;
        blocks[1]=tlm;
        blocks[2]=tlb;
        blocks[3]=mlb;
        blocks[4]=dlb;
        blocks[5]=dlm;
        blocks[6]=dlf;
        blocks[7]=mlf;
        blocks[8]=mlm;
    }
    public void choose_cubes_MV_up_down(Cube[] blocks){
        blocks[0]=tmf;
        blocks[1]=tmm;
        blocks[2]=tmb;
        blocks[3]=mmb;
        blocks[4]=dmb;
        blocks[5]=dmm;
        blocks[6]=dmf;
        blocks[7]=mmf;
        blocks[8]=mmm;
    }
    public void choose_cubes_right_up_down(Cube[] blocks){
        blocks[0]=trf;
        blocks[1]=trm;
        blocks[2]=trb;
        blocks[3]=mrb;
        blocks[4]=drb;
        blocks[5]=drm;
        blocks[6]=drf;
        blocks[7]=mrf;
        blocks[8]=mrm;
    }
    public void choose_cubes_front_cw_ccw(Cube[] blocks){
        blocks[0]=trf;
        blocks[1]=mrf;
        blocks[2]=drf;
        blocks[3]=dmf;
        blocks[4]=dlf;
        blocks[5]=mlf;
        blocks[6]=tlf;
        blocks[7]=tmf;
        blocks[8]=mmf;
    }
    public void choose_cubes_MF_cw_ccw(Cube[] blocks){
        blocks[0]=trm;
        blocks[1]=mrm;
        blocks[2]=drm;
        blocks[3]=dmm;
        blocks[4]=dlm;
        blocks[5]=mlm;
        blocks[6]=tlm;
        blocks[7]=tmm;
        blocks[8]=mmm;
    }
    public void choose_cubes_back_cw_ccw(Cube[] blocks){
        blocks[0]=trb;
        blocks[1]=mrb;
        blocks[2]=drb;
        blocks[3]=dmb;
        blocks[4]=dlb;
        blocks[5]=mlb;
        blocks[6]=tlb;
        blocks[7]=tmb;
        blocks[8]=mmb;
    }
    /*moves to manipulate orientation of cube*/
    public void top_move_right(){
        trf.top_move_right();
        trm.top_move_right();
        trb.top_move_right();
        tmb.top_move_right();
        tlb.top_move_right();
        tlm.top_move_right();
        tlf.top_move_right();
        tmf.top_move_right();
        tmm.top_move_right();

        temp=trf;
        trf=tlf;
        tlf=tlb;
        tlb=trb;
        trb=temp;
        temp=tmf;
        tmf=tlm;
        tlm=tmb;
        tmb=trm;
        trm=temp;
    }
    public void top_move_left(){
        trf.top_move_left();
        trm.top_move_left();
        trb.top_move_left();
        tmb.top_move_left();
        tlb.top_move_left();
        tlm.top_move_left();
        tlf.top_move_left();
        tmf.top_move_left();
        tmm.top_move_left();

        temp=trf;
        trf=trb;
        trb=tlb;
        tlb=tlf;
        tlf=temp;
        temp=tmf;
        tmf=trm;
        trm=tmb;
        tmb=tlm;
        tlm=temp;
    }
    public void middle_horizontal_move_right(){
        mrf.middle_horizontal_move_right();
        mrm.middle_horizontal_move_right();
        mrb.middle_horizontal_move_right();
        mmb.middle_horizontal_move_right();
        mlb.middle_horizontal_move_right();
        mlm.middle_horizontal_move_right();
        mlf.middle_horizontal_move_right();
        mmf.middle_horizontal_move_right();
        mmm.middle_horizontal_move_right();

        temp=mrf;
        mrf=mlf;
        mlf=mlb;
        mlb=mrb;
        mrb=temp;
        temp=mmf;
        mmf=mlm;
        mlm=mmb;
        mmb=mrm;
        mrm=temp;
    }
    public void middle_horizontal_move_left(){
        mrf.middle_horizontal_move_left();
        mrm.middle_horizontal_move_left();
        mrb.middle_horizontal_move_left();
        mmb.middle_horizontal_move_left();
        mlb.middle_horizontal_move_left();
        mlm.middle_horizontal_move_left();
        mlf.middle_horizontal_move_left();
        mmf.middle_horizontal_move_left();
        mmm.middle_horizontal_move_left();

        temp=mrf;
        mrf=mrb;
        mrb=mlb;
        mlb=mlf;
        mlf=temp;
        temp=mmf;
        mmf=mrm;
        mrm=mmb;
        mmb=mlm;
        mlm=temp;
    }
    public void bottom_move_right(){
        drf.bottom_move_right();
        drm.bottom_move_right();
        drb.bottom_move_right();
        dmb.bottom_move_right();
        dlb.bottom_move_right();
        dlm.bottom_move_right();
        dlf.bottom_move_right();
        dmf.bottom_move_right();
        dmm.bottom_move_right();

        temp=drf;
        drf=dlf;
        dlf=dlb;
        dlb=drb;
        drb=temp;
        temp=dmf;
        dmf=dlm;
        dlm=dmb;
        dmb=drm;
        drm=temp;
    }
    public void bottom_move_left(){
        drf.bottom_move_left();
        drm.bottom_move_left();
        drb.bottom_move_left();
        dmb.bottom_move_left();
        dlb.bottom_move_left();
        dlm.bottom_move_left();
        dlf.bottom_move_left();
        dmf.bottom_move_left();
        dmm.bottom_move_left();

        temp=drf;
        drf=drb;
        drb=dlb;
        dlb=dlf;
        dlf=temp;
        temp=dmf;
        dmf=drm;
        drm=dmb;
        dmb=dlm;
        dlm=temp;
    }
    public void left_move_down(){
        tlf.left_move_down();
        tlm.left_move_down();
        tlb.left_move_down();
        mlb.left_move_down();
        dlb.left_move_down();
        dlm.left_move_down();
        dlf.left_move_down();
        mlf.left_move_down();
        mlm.left_move_down();

        temp=tlf;
        tlf=tlb;
        tlb=dlb;
        dlb=dlf;
        dlf=temp;
        temp=tlm;
        tlm=mlb;
        mlb=dlm;
        dlm=mlf;
        mlf=temp;
    }
    public void left_move_up(){
        tlf.left_move_up();
        tlm.left_move_up();
        tlb.left_move_up();
        mlb.left_move_up();
        dlb.left_move_up();
        dlm.left_move_up();
        dlf.left_move_up();
        mlf.left_move_up(); 
        mlm.left_move_up();

        temp=tlf;
        tlf=dlf;
        dlf=dlb;
        dlb=tlb;
        tlb=temp;
        temp=tlm;
        tlm=mlf;
        mlf=dlm;
        dlm=mlb;
        mlb=temp;
    }
    public void middle_vertical_move_down(){
        tmf.middle_vertical_move_down();
        tmm.middle_vertical_move_down();
        tmb.middle_vertical_move_down();
        mmb.middle_vertical_move_down();
        dmb.middle_vertical_move_down();
        dmm.middle_vertical_move_down();
        dmf.middle_vertical_move_down();
        mmf.middle_vertical_move_down(); 
        mmm.middle_vertical_move_down();

        temp=tmf;
        tmf=tmb;
        tmb=dmb;
        dmb=dmf;
        dmf=temp;
        temp=tmm;
        tmm=mmb;
        mmb=dmm;
        dmm=mmf;
        mmf=temp;
    }
    public void middle_vertical_move_up(){
        tmf.middle_vertical_move_up();
        tmm.middle_vertical_move_up();
        tmb.middle_vertical_move_up();
        mmb.middle_vertical_move_up();
        dmb.middle_vertical_move_up();
        dmm.middle_vertical_move_up();
        dmf.middle_vertical_move_up();
        mmf.middle_vertical_move_up();
        mmm.middle_vertical_move_up();

        temp=tmf;
        tmf=dmf;
        dmf=dmb;
        dmb=tmb;
        tmb=temp;
        temp=tmm;
        tmm=mmf;
        mmf=dmm;
        dmm=mmb;
        mmb=temp;
    }
    public void right_move_down(){
        trf.right_move_down();
        trm.right_move_down();
        trb.right_move_down();
        mrb.right_move_down();
        drb.right_move_down();
        drm.right_move_down();
        drf.right_move_down();
        mrf.right_move_down();
        mrm.right_move_down();

        temp=trf;
        trf=trb;
        trb=drb;
        drb=drf;
        drf=temp;
        temp=trm;
        trm=mrb;
        mrb=drm;
        drm=mrf;
        mrf=temp;
    }
    public void right_move_up(){
        trf.right_move_up();
        trm.right_move_up();
        trb.right_move_up();
        mrb.right_move_up();
        drb.right_move_up();
        drm.right_move_up();
        drf.right_move_up();
        mrf.right_move_up();
        mrm.right_move_up(); 

        temp=trf;
        trf=drf;
        drf=drb;
        drb=trb;
        trb=temp;
        temp=trm;
        trm=mrf;
        mrf=drm;
        drm=mrb;
        mrb=temp;
    }
    public void front_face_cw(){
        trf.front_face_cw();
        mrf.front_face_cw();
        drf.front_face_cw();
        dmf.front_face_cw();
        dlf.front_face_cw();
        mlf.front_face_cw();
        tlf.front_face_cw();
        tmf.front_face_cw();
        mmf.front_face_cw();

        temp=trf;
        trf=tlf;
        tlf=dlf;
        dlf=drf;
        drf=temp;
        temp=tmf;
        tmf=mlf;
        mlf=dmf;
        dmf=mrf;
        mrf=temp;
    }
    public void front_face_ccw(){
        trf.front_face_ccw();
        mrf.front_face_ccw();
        drf.front_face_ccw();
        dmf.front_face_ccw();
        dlf.front_face_ccw();
        mlf.front_face_ccw();
        tlf.front_face_ccw();
        tmf.front_face_ccw();
        mmf.front_face_ccw();

        temp=trf;
        trf=drf;
        drf=dlf;
        dlf=tlf;
        tlf=temp;
        temp=tmf;
        tmf=mrf;
        mrf=dmf;
        dmf=mlf;
        mlf=temp;
    }
    public void middle_face_cw(){
        trm.middle_face_cw();
        mrm.middle_face_cw();
        drm.middle_face_cw();
        dmm.middle_face_cw();
        dlm.middle_face_cw();
        mlm.middle_face_cw();
        tlm.middle_face_cw();
        tmm.middle_face_cw();
        mmm.middle_face_cw();

        temp=trm;
        trm=tlm;
        tlm=dlm;
        dlm=drm;
        drm=temp;
        temp=tmm;
        tmm=mlm;
        mlm=dmm;
        dmm=mrm;
        mrm=temp;
    }
    public void middle_face_ccw(){
        trm.middle_face_ccw();
        mrm.middle_face_ccw();
        drm.middle_face_ccw();
        dmm.middle_face_ccw();
        dlm.middle_face_ccw();
        mlm.middle_face_ccw();
        tlm.middle_face_ccw();
        tmm.middle_face_ccw();
        mmm.middle_face_ccw(); 

        temp=trm;
        trm=drm;
        drm=dlm;
        dlm=tlm;
        tlm=temp;
        temp=tmm;
        tmm=mrm;
        mrm=dmm;
        dmm=mlm;
        mlm=temp;
    }
    public void back_face_cw(){
        trb.back_face_cw();
        mrb.back_face_cw();
        drb.back_face_cw();
        dmb.back_face_cw();
        dlb.back_face_cw();
        mlb.back_face_cw();
        tlb.back_face_cw();
        tmb.back_face_cw();
        mmb.back_face_cw();

        temp=trb;
        trb=tlb;
        tlb=dlb;
        dlb=drb;
        drb=temp;
        temp=tmb;
        tmb=mlb;
        mlb=dmb;
        dmb=mrb;
        mrb=temp;
    }
    public void back_face_ccw(){
        trb.back_face_ccw();
        mrb.back_face_ccw();
        drb.back_face_ccw();
        dmb.back_face_ccw();
        dlb.back_face_ccw();
        mlb.back_face_ccw();
        tlb.back_face_ccw();
        tmb.back_face_ccw();
        mmb.back_face_ccw();

        temp=trb;
        trb=drb;
        drb=dlb;
        dlb=tlb;
        tlb=temp;
        temp=tmb;
        tmb=mrb;
        mrb=dmb;
        dmb=mlb;
        mlb=temp;
    }
    
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
                case MOVE_MHMR:
                    middle_horizontal_move_right();
                    break;
                case MOVE_BMR:
                    bottom_move_right();
                    break;
                case MOVE_LMU:
                    left_move_up();
                    break;
                case MOVE_MVMU:
                    middle_vertical_move_up();
                    break;
                case MOVE_RMU:
                    right_move_up();
                    break;
                case MOVE_TML:
                    top_move_left();
                    break;
                case MOVE_MHML:
                    middle_horizontal_move_left();
                    break;
                case MOVE_BML:
                    bottom_move_left();
                    break;
                case MOVE_LMD:
                    left_move_down();
                    break;
                case MOVE_MVMD:
                    middle_vertical_move_down();
                    break;
                case MOVE_RMD:
                    right_move_down();
                    break;
                case MOVE_FCCW:
                    front_face_ccw();
                    break;
                case MOVE_MCCW:
                    middle_face_ccw();
                    break;
                case MOVE_BCCW:
                    back_face_ccw();
                    break;
                case MOVE_FCW:
                    front_face_cw();
                    break;
                case MOVE_MCW:
                    middle_face_cw();
                    break;
                case MOVE_BCW:
                    back_face_cw();
                    break;
            }
        }
    } //jumble up the cube for a new game
}
