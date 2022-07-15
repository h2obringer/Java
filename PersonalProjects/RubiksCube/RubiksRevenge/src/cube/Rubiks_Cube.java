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
    Cube temp,tlf,tmlf,tmrf,trf,tlmf,tmlmf,tmrmf,trmf,tlmb,tmlmb,tmrmb,trmb,tlb,tmlb,tmrb,trb,mtlf,
            mtmlf,mtmrf,mtrf,mtlmf,mtmlmf,mtmrmf,mtrmf,mtlmb,mtmlmb,mtmrmb,mtrmb,mtlb,mtmlb,mtmrb,
            mtrb,mdlf,mdmlf,mdmrf,mdrf,mdlmf,mdmlmf,mdmrmf,mdrmf,mdlmb,mdmlmb,mdmrmb,mdrmb,mdlb,mdmlb,
            mdmrb,mdrb,dlf,dmlf,dmrf,drf,dlmf,dmlmf,dmrmf,drmf,dlmb,dmlmb,dmrmb,drmb,dlb,dmlb,dmrb,drb;
    
    public Rubiks_Cube(){ //OK
        tlf = new Cube(ZERO,ONE,ZERO,ZERO,-FAR_PLACEMENT,FAR_PLACEMENT,FAR_PLACEMENT, //-PLACEMENT,PLACEMENT,PLACEMENT,
                    BLACK,GREEN,YELLOW,BLACK,RED,BLACK,BLACK);
        tlmf = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,FAR_PLACEMENT,NEAR_PLACEMENT,BLACK, //-PLACEMENT,PLACEMENT,ZERO
                BLACK,YELLOW,BLACK,RED,BLACK,BLACK);
        tlmb = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,FAR_PLACEMENT,-NEAR_PLACEMENT,BLACK, //-PLACEMENT,PLACEMENT,ZERO
                BLACK,YELLOW,BLACK,RED,BLACK,BLACK);
        tlb= new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,FAR_PLACEMENT,-FAR_PLACEMENT, //-PLACEMENT,PLACEMENT,-PLACEMENT,
                BLUE,BLACK,YELLOW,BLACK,RED,BLACK,BLACK);
        tmlf = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,FAR_PLACEMENT,FAR_PLACEMENT,BLACK, //ZERO,PLACEMENT,PLACEMENT
                GREEN,YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmrf = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,FAR_PLACEMENT,FAR_PLACEMENT,BLACK, //ZERO,PLACEMENT,PLACEMENT,
                GREEN,YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmlmf = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,FAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK, //ZERO,PLACEMENT,ZERO
                YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmrmf = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,FAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK, //ZERO,PLACEMENT,ZERO
                YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmlmb = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,FAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK, //ZERO,PLACEMENT,ZERO,
                YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmrmb = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,FAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK, //ZERO,PLACEMENT,ZERO,
                YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmlb = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,FAR_PLACEMENT,-FAR_PLACEMENT,BLUE,
                BLACK,YELLOW,BLACK,BLACK,BLACK,BLACK);
        tmrb = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,FAR_PLACEMENT,-FAR_PLACEMENT,BLUE,
                BLACK,YELLOW,BLACK,BLACK,BLACK,BLACK);
        trf = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,FAR_PLACEMENT,FAR_PLACEMENT,
                BLACK,GREEN,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        trmf = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,FAR_PLACEMENT,NEAR_PLACEMENT,BLACK,
                BLACK,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        trmb = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,FAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,
                BLACK,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        trb = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,FAR_PLACEMENT,-FAR_PLACEMENT,
                BLUE,BLACK,YELLOW,BLACK,BLACK,ORANGE,BLACK);
        mtlf = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,NEAR_PLACEMENT,FAR_PLACEMENT,BLACK,
                GREEN,BLACK,BLACK,RED,BLACK,BLACK);
        mdlf = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,-NEAR_PLACEMENT,FAR_PLACEMENT,BLACK,
                GREEN,BLACK,BLACK,RED,BLACK,BLACK);
        mtlmf = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,NEAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,BLACK,RED,BLACK,BLACK);
        mtlmb = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,NEAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,BLACK,RED,BLACK,BLACK);
        mdlmf = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,-NEAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,BLACK,RED,BLACK,BLACK);
        mdlmb = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,-NEAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,BLACK,RED,BLACK,BLACK);
        mtlb = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,NEAR_PLACEMENT,-FAR_PLACEMENT,BLUE,
                BLACK,BLACK,BLACK,RED,BLACK,BLACK);
        mdlb = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,-NEAR_PLACEMENT,-FAR_PLACEMENT,BLUE,
                BLACK,BLACK,BLACK,RED,BLACK,BLACK);
        mtmlf = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,NEAR_PLACEMENT,FAR_PLACEMENT,BLACK,GREEN,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mtmrf = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,NEAR_PLACEMENT,FAR_PLACEMENT,BLACK,GREEN,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mdmlf = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,-NEAR_PLACEMENT,FAR_PLACEMENT,BLACK,GREEN,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mdmrf = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,-NEAR_PLACEMENT,FAR_PLACEMENT,BLACK,GREEN,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mtmlmf = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,NEAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mtmrmf = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,NEAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mtmlmb = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,NEAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mtmrmb = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,NEAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mdmlmf = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,-NEAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mdmrmf = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,-NEAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mdmlmb = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,-NEAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mdmrmb = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,-NEAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,BLACK,
                BLACK,BLACK,BLACK,BLACK);
        mtmlb = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,NEAR_PLACEMENT,-FAR_PLACEMENT,BLUE,BLACK,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mtmrb = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,NEAR_PLACEMENT,-FAR_PLACEMENT,BLUE,BLACK,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mdmlb = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,-NEAR_PLACEMENT,-FAR_PLACEMENT,BLUE,BLACK,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mdmrb = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,-NEAR_PLACEMENT,-FAR_PLACEMENT,BLUE,BLACK,
                BLACK,BLACK,BLACK,BLACK,BLACK);
        mtrf = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,NEAR_PLACEMENT,FAR_PLACEMENT,BLACK,
                GREEN,BLACK,BLACK,BLACK,ORANGE,BLACK);
        mdrf = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,-NEAR_PLACEMENT,FAR_PLACEMENT,BLACK,
                GREEN,BLACK,BLACK,BLACK,ORANGE,BLACK);
        mtrmf = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,NEAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,BLACK,BLACK,ORANGE,BLACK);
        mtrmb = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,NEAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,BLACK,BLACK,ORANGE,BLACK);
        mdrmf = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,-NEAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,BLACK,BLACK,ORANGE,BLACK);
        mdrmb = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,-NEAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,BLACK,BLACK,ORANGE,BLACK);
        mtrb = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,NEAR_PLACEMENT,-FAR_PLACEMENT,BLUE,
                BLACK,BLACK,BLACK,BLACK,ORANGE,BLACK);
        mdrb = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,-NEAR_PLACEMENT,-FAR_PLACEMENT,BLUE,
                BLACK,BLACK,BLACK,BLACK,ORANGE,BLACK);
        dlf = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,-FAR_PLACEMENT,FAR_PLACEMENT,
                BLACK,GREEN,BLACK,WHITE,RED,BLACK,BLACK);
        dlmf = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,-FAR_PLACEMENT,NEAR_PLACEMENT,BLACK,
                BLACK,BLACK,WHITE,RED,BLACK,BLACK);
        dlmb = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,-FAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,
                BLACK,BLACK,WHITE,RED,BLACK,BLACK);
        dlb = new Cube(ZERO,ZERO,ZERO,ZERO,-FAR_PLACEMENT,-FAR_PLACEMENT,-FAR_PLACEMENT,
                BLUE,BLACK,BLACK,WHITE,RED,BLACK,BLACK);
        dmlf = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,-FAR_PLACEMENT,FAR_PLACEMENT,BLACK,
                GREEN,BLACK,WHITE,BLACK,BLACK,BLACK);
        dmrf = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,-FAR_PLACEMENT,FAR_PLACEMENT,BLACK,
                GREEN,BLACK,WHITE,BLACK,BLACK,BLACK);
        dmlmf = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,-FAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,WHITE,BLACK,BLACK,BLACK);
        dmrmf = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,-FAR_PLACEMENT,NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,WHITE,BLACK,BLACK,BLACK);
        dmlmb = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,-FAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,WHITE,BLACK,BLACK,BLACK);
        dmrmb = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,-FAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,BLACK,
                BLACK,WHITE,BLACK,BLACK,BLACK);
        dmlb = new Cube(ZERO,ZERO,ZERO,ZERO,-NEAR_PLACEMENT,-FAR_PLACEMENT,-FAR_PLACEMENT,BLUE,
                BLACK,BLACK,WHITE,BLACK,BLACK,BLACK);
        dmrb = new Cube(ZERO,ZERO,ZERO,ZERO,NEAR_PLACEMENT,-FAR_PLACEMENT,-FAR_PLACEMENT,BLUE,
                BLACK,BLACK,WHITE,BLACK,BLACK,BLACK);
        drf = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,-FAR_PLACEMENT,FAR_PLACEMENT,
                BLACK,GREEN,BLACK,WHITE,BLACK,ORANGE,BLACK);
        drmf = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,-FAR_PLACEMENT,NEAR_PLACEMENT,BLACK,
                BLACK,BLACK,WHITE,BLACK,ORANGE,BLACK);
        drmb = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,-FAR_PLACEMENT,-NEAR_PLACEMENT,BLACK,
                BLACK,BLACK,WHITE,BLACK,ORANGE,BLACK);
        drb = new Cube(ZERO,ZERO,ZERO,ZERO,FAR_PLACEMENT,-FAR_PLACEMENT,-FAR_PLACEMENT,
                BLUE,BLACK,BLACK,WHITE,BLACK,ORANGE,BLACK);
        front=back=left=right=top=bottom=move_rotation=x_axis=y_axis=z_axis=
              direction=x_rotation=y_rotation=ZERO;
        speed=MAX_SPEED;
        in_progress=false;
    }
    
    public void draw(int mode){  //OK          
        glPushMatrix();
        glRotatef(tlf.get_rotation(),tlf.get_x_axis(),tlf.get_y_axis(),tlf.get_z_axis());
        glTranslatef(tlf.get_x(),tlf.get_y(),tlf.get_z());
        tlf.draw(mode,ID_TLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmrf.get_rotation(),tmrf.get_x_axis(),tmrf.get_y_axis(),tmrf.get_z_axis());
        glTranslatef(tmrf.get_x(),tmrf.get_y(),tmrf.get_z()); 
        tmrf.draw(mode,ID_TMRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmlf.get_rotation(),tmlf.get_x_axis(),tmlf.get_y_axis(),tmlf.get_z_axis());
        glTranslatef(tmlf.get_x(),tmlf.get_y(),tmlf.get_z()); 
        tmlf.draw(mode,ID_TMLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trf.get_rotation(),trf.get_x_axis(),trf.get_y_axis(),trf.get_z_axis());
        glTranslatef(trf.get_x(),trf.get_y(),trf.get_z()); 
        trf.draw(mode,ID_TRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tlmf.get_rotation(),tlmf.get_x_axis(),tlmf.get_y_axis(),tlmf.get_z_axis());
        glTranslatef(tlmf.get_x(),tlmf.get_y(),tlmf.get_z()); 
        tlmf.draw(mode,ID_TLMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmlmf.get_rotation(),tmlmf.get_x_axis(),tmlmf.get_y_axis(),tmlmf.get_z_axis());
        glTranslatef(tmlmf.get_x(),tmlmf.get_y(),tmlmf.get_z()); 
        tmlmf.draw(mode,ID_TMLMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmrmf.get_rotation(),tmrmf.get_x_axis(),tmrmf.get_y_axis(),tmrmf.get_z_axis());
        glTranslatef(tmrmf.get_x(),tmrmf.get_y(),tmrmf.get_z()); 
        tmrmf.draw(mode,ID_TMRMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trmf.get_rotation(),trmf.get_x_axis(),trmf.get_y_axis(),trmf.get_z_axis());
        glTranslatef(trmf.get_x(),trmf.get_y(),trmf.get_z()); 
        trmf.draw(mode,ID_TRMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tlmb.get_rotation(),tlmb.get_x_axis(),tlmb.get_y_axis(),tlmb.get_z_axis());
        glTranslatef(tlmb.get_x(),tlmb.get_y(),tlmb.get_z()); 
        tlmb.draw(mode,ID_TLMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmlmb.get_rotation(),tmlmb.get_x_axis(),tmlmb.get_y_axis(),tmlmb.get_z_axis());
        glTranslatef(tmlmb.get_x(),tmlmb.get_y(),tmlmb.get_z()); 
        tmlmb.draw(mode,ID_TMLMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmrmb.get_rotation(),tmrmb.get_x_axis(),tmrmb.get_y_axis(),tmrmb.get_z_axis());
        glTranslatef(tmrmb.get_x(),tmrmb.get_y(),tmrmb.get_z()); 
        tmrmb.draw(mode,ID_TMRMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trmb.get_rotation(),trmb.get_x_axis(),trmb.get_y_axis(),trmb.get_z_axis());
        glTranslatef(trmb.get_x(),trmb.get_y(),trmb.get_z()); 
        trmb.draw(mode,ID_TRMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tlb.get_rotation(),tlb.get_x_axis(),tlb.get_y_axis(),tlb.get_z_axis());
        glTranslatef(tlb.get_x(),tlb.get_y(),tlb.get_z()); 
        tlb.draw(mode,ID_TLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmlb.get_rotation(),tmlb.get_x_axis(),tmlb.get_y_axis(),tmlb.get_z_axis());
        glTranslatef(tmlb.get_x(),tmlb.get_y(),tmlb.get_z()); 
        tmlb.draw(mode,ID_TMLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(tmrb.get_rotation(),tmrb.get_x_axis(),tmrb.get_y_axis(),tmrb.get_z_axis());
        glTranslatef(tmrb.get_x(),tmrb.get_y(),tmrb.get_z()); 
        tmrb.draw(mode,ID_TMRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(trb.get_rotation(),trb.get_x_axis(),trb.get_y_axis(),trb.get_z_axis());
        glTranslatef(trb.get_x(),trb.get_y(),trb.get_z()); 
        trb.draw(mode,ID_TRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtlf.get_rotation(),mtlf.get_x_axis(),mtlf.get_y_axis(),mtlf.get_z_axis());
        glTranslatef(mtlf.get_x(),mtlf.get_y(),mtlf.get_z()); 
        mtlf.draw(mode,ID_MTLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtmlf.get_rotation(),mtmlf.get_x_axis(),mtmlf.get_y_axis(),mtmlf.get_z_axis());
        glTranslatef(mtmlf.get_x(),mtmlf.get_y(),mtmlf.get_z()); 
        mtmlf.draw(mode,ID_MTMLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtmrf.get_rotation(),mtmrf.get_x_axis(),mtmrf.get_y_axis(),mtmrf.get_z_axis());
        glTranslatef(mtmrf.get_x(),mtmrf.get_y(),mtmrf.get_z()); 
        mtmrf.draw(mode,ID_MTMRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtrf.get_rotation(),mtrf.get_x_axis(),mtrf.get_y_axis(),mtrf.get_z_axis());
        glTranslatef(mtrf.get_x(),mtrf.get_y(),mtrf.get_z()); 
        mtrf.draw(mode,ID_MTRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtlmf.get_rotation(),mtlmf.get_x_axis(),mtlmf.get_y_axis(),mtlmf.get_z_axis());
        glTranslatef(mtlmf.get_x(),mtlmf.get_y(),mtlmf.get_z()); 
        mtlmf.draw(mode,ID_MTLMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtmlmf.get_rotation(),mtmlmf.get_x_axis(),mtmlmf.get_y_axis(),mtmlmf.get_z_axis());
        glTranslatef(mtmlmf.get_x(),mtmlmf.get_y(),mtmlmf.get_z()); 
        mtmlmf.draw(mode,ID_MTMLMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtmrmf.get_rotation(),mtmrmf.get_x_axis(),mtmrmf.get_y_axis(),mtmrmf.get_z_axis());
        glTranslatef(mtmrmf.get_x(),mtmrmf.get_y(),mtmrmf.get_z()); 
        mtmrmf.draw(mode,ID_MTMRMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtrmf.get_rotation(),mtrmf.get_x_axis(),mtrmf.get_y_axis(),mtrmf.get_z_axis());
        glTranslatef(mtrmf.get_x(),mtrmf.get_y(),mtrmf.get_z()); 
        mtrmf.draw(mode,ID_MTRMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtlmb.get_rotation(),mtlmb.get_x_axis(),mtlmb.get_y_axis(),mtlmb.get_z_axis());
        glTranslatef(mtlmb.get_x(),mtlmb.get_y(),mtlmb.get_z()); 
        mtlmb.draw(mode,ID_MTLMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtmlmb.get_rotation(),mtmlmb.get_x_axis(),mtmlmb.get_y_axis(),mtmlmb.get_z_axis());
        glTranslatef(mtmlmb.get_x(),mtmlmb.get_y(),mtmlmb.get_z()); 
        mtmlmb.draw(mode,ID_MTMLMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtmrmb.get_rotation(),mtmrmb.get_x_axis(),mtmrmb.get_y_axis(),mtmrmb.get_z_axis());
        glTranslatef(mtmrmb.get_x(),mtmrmb.get_y(),mtmrmb.get_z()); 
        mtmrmb.draw(mode,ID_MTMRMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtrmb.get_rotation(),mtrmb.get_x_axis(),mtrmb.get_y_axis(),mtrmb.get_z_axis());
        glTranslatef(mtrmb.get_x(),mtrmb.get_y(),mtrmb.get_z()); 
        mtrmb.draw(mode,ID_MTRMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtlb.get_rotation(),mtlb.get_x_axis(),mtlb.get_y_axis(),mtlb.get_z_axis());
        glTranslatef(mtlb.get_x(),mtlb.get_y(),mtlb.get_z()); 
        mtlb.draw(mode,ID_MTLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtmlb.get_rotation(),mtmlb.get_x_axis(),mtmlb.get_y_axis(),mtmlb.get_z_axis());
        glTranslatef(mtmlb.get_x(),mtmlb.get_y(),mtmlb.get_z()); 
        mtmlb.draw(mode,ID_MTMLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtmrb.get_rotation(),mtmrb.get_x_axis(),mtmrb.get_y_axis(),mtmrb.get_z_axis());
        glTranslatef(mtmrb.get_x(),mtmrb.get_y(),mtmrb.get_z()); 
        mtmrb.draw(mode,ID_MTMRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mtrb.get_rotation(),mtrb.get_x_axis(),mtrb.get_y_axis(),mtrb.get_z_axis());
        glTranslatef(mtrb.get_x(),mtrb.get_y(),mtrb.get_z()); 
        mtrb.draw(mode,ID_MTRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdlf.get_rotation(),mdlf.get_x_axis(),mdlf.get_y_axis(),mdlf.get_z_axis());
        glTranslatef(mdlf.get_x(),mdlf.get_y(),mdlf.get_z()); 
        mdlf.draw(mode,ID_MDLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdmlf.get_rotation(),mdmlf.get_x_axis(),mdmlf.get_y_axis(),mdmlf.get_z_axis());
        glTranslatef(mdmlf.get_x(),mdmlf.get_y(),mdmlf.get_z()); 
        mdmlf.draw(mode,ID_MDMLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdmrf.get_rotation(),mdmrf.get_x_axis(),mdmrf.get_y_axis(),mdmrf.get_z_axis());
        glTranslatef(mdmrf.get_x(),mdmrf.get_y(),mdmrf.get_z()); 
        mdmrf.draw(mode,ID_MDMRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdrf.get_rotation(),mdrf.get_x_axis(),mdrf.get_y_axis(),mdrf.get_z_axis());
        glTranslatef(mdrf.get_x(),mdrf.get_y(),mdrf.get_z()); 
        mdrf.draw(mode,ID_MDRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdlmf.get_rotation(),mdlmf.get_x_axis(),mdlmf.get_y_axis(),mdlmf.get_z_axis());
        glTranslatef(mdlmf.get_x(),mdlmf.get_y(),mdlmf.get_z()); 
        mdlmf.draw(mode,ID_MDLMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdmlmf.get_rotation(),mdmlmf.get_x_axis(),mdmlmf.get_y_axis(),mdmlmf.get_z_axis());
        glTranslatef(mdmlmf.get_x(),mdmlmf.get_y(),mdmlmf.get_z()); 
        mdmlmf.draw(mode,ID_MDMLMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdmrmf.get_rotation(),mdmrmf.get_x_axis(),mdmrmf.get_y_axis(),mdmrmf.get_z_axis());
        glTranslatef(mdmrmf.get_x(),mdmrmf.get_y(),mdmrmf.get_z()); 
        mdmrmf.draw(mode,ID_MDMRMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdrmf.get_rotation(),mdrmf.get_x_axis(),mdrmf.get_y_axis(),mdrmf.get_z_axis());
        glTranslatef(mdrmf.get_x(),mdrmf.get_y(),mdrmf.get_z()); 
        mdrmf.draw(mode,ID_MDRMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdlmb.get_rotation(),mdlmb.get_x_axis(),mdlmb.get_y_axis(),mdlmb.get_z_axis());
        glTranslatef(mdlmb.get_x(),mdlmb.get_y(),mdlmb.get_z()); 
        mdlmb.draw(mode,ID_MDLMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdmlmb.get_rotation(),mdmlmb.get_x_axis(),mdmlmb.get_y_axis(),mdmlmb.get_z_axis());
        glTranslatef(mdmlmb.get_x(),mdmlmb.get_y(),mdmlmb.get_z()); 
        mdmlmb.draw(mode,ID_MDMLMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdmrmb.get_rotation(),mdmrmb.get_x_axis(),mdmrmb.get_y_axis(),mdmrmb.get_z_axis());
        glTranslatef(mdmrmb.get_x(),mdmrmb.get_y(),mdmrmb.get_z()); 
        mdmrmb.draw(mode,ID_MDMRMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdrmb.get_rotation(),mdrmb.get_x_axis(),mdrmb.get_y_axis(),mdrmb.get_z_axis());
        glTranslatef(mdrmb.get_x(),mdrmb.get_y(),mdrmb.get_z()); 
        mdrmb.draw(mode,ID_MDRMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdlb.get_rotation(),mdlb.get_x_axis(),mdlb.get_y_axis(),mdlb.get_z_axis());
        glTranslatef(mdlb.get_x(),mdlb.get_y(),mdlb.get_z()); 
        mdlb.draw(mode,ID_MDLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdmlb.get_rotation(),mdmlb.get_x_axis(),mdmlb.get_y_axis(),mdmlb.get_z_axis());
        glTranslatef(mdmlb.get_x(),mdmlb.get_y(),mdmlb.get_z()); 
        mdmlb.draw(mode,ID_MDMLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdmrb.get_rotation(),mdmrb.get_x_axis(),mdmrb.get_y_axis(),mdmrb.get_z_axis());
        glTranslatef(mdmrb.get_x(),mdmrb.get_y(),mdmrb.get_z()); 
        mdmrb.draw(mode,ID_MDMRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(mdrb.get_rotation(),mdrb.get_x_axis(),mdrb.get_y_axis(),mdrb.get_z_axis());
        glTranslatef(mdrb.get_x(),mdrb.get_y(),mdrb.get_z()); 
        mdrb.draw(mode,ID_MDRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlf.get_rotation(),dlf.get_x_axis(),dlf.get_y_axis(),dlf.get_z_axis());
        glTranslatef(dlf.get_x(),dlf.get_y(),dlf.get_z()); 
        dlf.draw(mode,ID_DLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmlf.get_rotation(),dmlf.get_x_axis(),dmlf.get_y_axis(),dmlf.get_z_axis());
        glTranslatef(dmlf.get_x(),dmlf.get_y(),dmlf.get_z()); 
        dmlf.draw(mode,ID_DMLF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmrf.get_rotation(),dmrf.get_x_axis(),dmrf.get_y_axis(),dmrf.get_z_axis());
        glTranslatef(dmrf.get_x(),dmrf.get_y(),dmrf.get_z()); 
        dmrf.draw(mode,ID_DMRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drf.get_rotation(),drf.get_x_axis(),drf.get_y_axis(),drf.get_z_axis());
        glTranslatef(drf.get_x(),drf.get_y(),drf.get_z()); 
        drf.draw(mode,ID_DRF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlmf.get_rotation(),dlmf.get_x_axis(),dlmf.get_y_axis(),dlmf.get_z_axis());
        glTranslatef(dlmf.get_x(),dlmf.get_y(),dlmf.get_z()); 
        dlmf.draw(mode,ID_DLMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmlmf.get_rotation(),dmlmf.get_x_axis(),dmlmf.get_y_axis(),dmlmf.get_z_axis());
        glTranslatef(dmlmf.get_x(),dmlmf.get_y(),dmlmf.get_z()); 
        dmlmf.draw(mode,ID_DMLMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmrmf.get_rotation(),dmrmf.get_x_axis(),dmrmf.get_y_axis(),dmrmf.get_z_axis());
        glTranslatef(dmrmf.get_x(),dmrmf.get_y(),dmrmf.get_z()); 
        dmrmf.draw(mode,ID_DMRMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drmf.get_rotation(),drmf.get_x_axis(),drmf.get_y_axis(),drmf.get_z_axis());
        glTranslatef(drmf.get_x(),drmf.get_y(),drmf.get_z()); 
        drmf.draw(mode,ID_DRMF); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlmb.get_rotation(),dlmb.get_x_axis(),dlmb.get_y_axis(),dlmb.get_z_axis());
        glTranslatef(dlmb.get_x(),dlmb.get_y(),dlmb.get_z()); 
        dlmb.draw(mode,ID_DLMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmlmb.get_rotation(),dmlmb.get_x_axis(),dmlmb.get_y_axis(),dmlmb.get_z_axis());
        glTranslatef(dmlmb.get_x(),dmlmb.get_y(),dmlmb.get_z()); 
        dmlmb.draw(mode,ID_DMLMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmrmb.get_rotation(),dmrmb.get_x_axis(),dmrmb.get_y_axis(),dmrmb.get_z_axis());
        glTranslatef(dmrmb.get_x(),dmrmb.get_y(),dmrmb.get_z()); 
        dmrmb.draw(mode,ID_DMRMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drmb.get_rotation(),drmb.get_x_axis(),drmb.get_y_axis(),drmb.get_z_axis());
        glTranslatef(drmb.get_x(),drmb.get_y(),drmb.get_z()); 
        drmb.draw(mode,ID_DRMB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dlb.get_rotation(),dlb.get_x_axis(),dlb.get_y_axis(),dlb.get_z_axis());
        glTranslatef(dlb.get_x(),dlb.get_y(),dlb.get_z()); 
        dlb.draw(mode,ID_DLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmlb.get_rotation(),dmlb.get_x_axis(),dmlb.get_y_axis(),dmlb.get_z_axis());
        glTranslatef(dmlb.get_x(),dmlb.get_y(),dmlb.get_z()); 
        dmlb.draw(mode,ID_DMLB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(dmrb.get_rotation(),dmrb.get_x_axis(),dmrb.get_y_axis(),dmrb.get_z_axis());
        glTranslatef(dmrb.get_x(),dmrb.get_y(),dmrb.get_z()); 
        dmrb.draw(mode,ID_DMRB); 
        glPopMatrix();
        glPushMatrix();
        glRotatef(drb.get_rotation(),drb.get_x_axis(),drb.get_y_axis(),drb.get_z_axis());
        glTranslatef(drb.get_x(),drb.get_y(),drb.get_z()); 
        drb.draw(mode,ID_DRB); 
        glPopMatrix();
    }//draw all elements of rubiks cube
    
    public Boolean check_win(){ //OK
        if(check_front()&&check_back()&&check_top()&&check_bottom()&&check_left()&&check_right()){
            return true;
        }else{
            return false;
        }
    }//check all faces for same color to check if game is won
    
    private Boolean check_front(){ //OK
        front=mtmlf.get_front();
        if((front==tlf.get_front())&&(front==tmlf.get_front())&&(front==tmrf.get_front())&&
                (front==trf.get_front())&&(front==mtlf.get_front())&&(front==mtmlf.get_front())&&
                (front==mtmrf.get_front())&&(front==mtrf.get_front())&&(front==mdlf.get_front())&&
                (front==mdmlf.get_front())&&(front==mdmrf.get_front())&&(front==mdrf.get_front())&&
                (front==dlf.get_front())&&(front==dmlf.get_front())&&(front==dmrf.get_front())&&
                (front==drf.get_front())
                ){
             return true;
        }else{
             return false;
        }
    }
    
    private Boolean check_back(){ //OK
        back=mtmlb.get_back();
        if((back==tlb.get_back())&&(back==tmlb.get_back())&&(back==tmrb.get_back())&&
                (back==trb.get_back())&&(back==mtlb.get_back())&&(back==mtmlb.get_back())&&
                (back==mtmrb.get_back())&&(back==mtrb.get_back())&&(back==mdlb.get_back())&&
                (back==mdmlb.get_back())&&(back==mdmrb.get_back())&&(back==mdrb.get_back())&&
                (back==dlb.get_back())&&(back==dmlb.get_back())&&(back==dmrb.get_back())&&
                (back==drb.get_back())){
             return true;
        }else{
             return false;
        }
    }//check back face for all same color
    
    private Boolean check_top(){ //OK
        top=tmlmf.get_top();
        if((top==tlf.get_top())&&(top==tmlf.get_top())&&(top==tmrf.get_top())&&
               (top==trf.get_top())&&(top==tlmf.get_top())&&(top==tmlmf.get_top())&&
               (top==tmrmf.get_top())&&(top==trmf.get_top())&&(top==tlmb.get_top())&&
               (top==tmlmb.get_top())&&(top==tmrmb.get_top())&&(top==trmb.get_top())&&
               (top==tlb.get_top())&&(top==tmlb.get_top())&&(top==tmrb.get_top())&&
               (top==trb.get_top())){
            return true;
        }else{
            return false;
        }
    }//check top face for all same color
    
    private Boolean check_bottom(){ //OK
        bottom=dmlmf.get_bottom();
        if((bottom==dlf.get_bottom())&&(bottom==dmlf.get_bottom())&&(bottom==dmrf.get_bottom())&&
               (bottom==drf.get_bottom())&&(bottom==dlmf.get_bottom())&&(bottom==dmlmf.get_bottom())&&
               (bottom==dmrmf.get_bottom())&&(bottom==drmf.get_bottom())&&(bottom==dlmb.get_bottom())&&
               (bottom==dmlmb.get_bottom())&&(bottom==dmrmb.get_bottom())&&(bottom==drmb.get_bottom())&&
               (bottom==dlb.get_bottom())&&(bottom==dmlb.get_bottom())&&(bottom==dmrb.get_bottom())&&
               (bottom==drb.get_bottom())){
            return true;
        }else{
            return false;
        }
    }//check bottom face for all same color
    
    private Boolean check_left(){ //OK
        left=mtlmf.get_left();
        if((left==tlf.get_left())&&(left==tlmf.get_left())&&(left==tlmb.get_left())&&
              (left==tlb.get_left())&&(left==mtlf.get_left())&&(left==mtlmf.get_left())&&
              (left==mtlmb.get_left())&&(left==mtlb.get_left())&&(left==mdlf.get_left())&&
              (left==mdlmf.get_left())&&(left==mdlmb.get_left()&&(left==mdlb.get_left())&&
              (left==dlf.get_left())&&(left==dlmf.get_left())&&(left==dlmb.get_left())&&
              (left==dlb.get_left()))){
            return true;
        }else{
            return false;
        }
    }//check left face for all same color
    
    private Boolean check_right(){ //OK
        right=mtrmf.get_right();
        if((right==trf.get_right())&&(right==trmf.get_right())&&(right==trmb.get_right())&&
              (right==trb.get_right())&&(right==mtrf.get_right())&&(right==mtrmf.get_right())&&
              (right==mtrmb.get_right())&&(right==mtrb.get_right())&&(right==mdrf.get_right())&&
              (right==mdrmf.get_right())&&(right==mdrmb.get_right()&&(right==mdrb.get_right())&&
              (right==drf.get_right())&&(right==drmf.get_right())&&(right==drmb.get_right())&&
              (right==drb.get_right()))){
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
    public void mutate_in_progress(boolean progress){
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
    
    public void write_to_file(BufferedWriter bw) throws IOException{ //OK
        bw.write(Integer.toString(move_rotation)); 
        bw.newLine();
        bw.write(Integer.toString(x_rotation));
        bw.newLine();
        bw.write(Integer.toString(y_rotation));
        bw.newLine();
        tlf.write_cube(bw);
        tmlf.write_cube(bw);
        tmrf.write_cube(bw);
        trf.write_cube(bw);
        tlmf.write_cube(bw);
        tmlmf.write_cube(bw);
        tmrmf.write_cube(bw);
        trmf.write_cube(bw);
        tlmb.write_cube(bw);
        tmlmb.write_cube(bw);
        tmrmb.write_cube(bw);
        trmb.write_cube(bw);
        tlb.write_cube(bw);
        tmlb.write_cube(bw);
        tmrb.write_cube(bw);
        trb.write_cube(bw);
        mtlf.write_cube(bw);
        mtmlf.write_cube(bw);
        mtmrf.write_cube(bw);
        mtrf.write_cube(bw);
        mtlmf.write_cube(bw);
        mtmlmf.write_cube(bw);
        mtmrmf.write_cube(bw);
        mtrmf.write_cube(bw);
        mtlmb.write_cube(bw);
        mtmlmb.write_cube(bw);
        mtmrmb.write_cube(bw);
        mtrmb.write_cube(bw);
        mtlb.write_cube(bw);
        mtmlb.write_cube(bw);
        mtmrb.write_cube(bw);
        mtrb.write_cube(bw);
        mdlf.write_cube(bw);
        mdmlf.write_cube(bw);
        mdmrf.write_cube(bw);
        mdrf.write_cube(bw);
        mdlmf.write_cube(bw);
        mdmlmf.write_cube(bw);
        mdmrmf.write_cube(bw);
        mdrmf.write_cube(bw);
        mdlmb.write_cube(bw);
        mdmlmb.write_cube(bw);
        mdmrmb.write_cube(bw);
        mdrmb.write_cube(bw);
        mdlb.write_cube(bw);
        mdmlb.write_cube(bw);
        mdmrb.write_cube(bw);
        mdrb.write_cube(bw);
        dlf.write_cube(bw);
        dmlf.write_cube(bw);
        dmrf.write_cube(bw);
        drf.write_cube(bw);
        dlmf.write_cube(bw);
        dmlmf.write_cube(bw);
        dmrmf.write_cube(bw);
        drmf.write_cube(bw);
        dlmb.write_cube(bw);
        dmlmb.write_cube(bw);
        dmrmb.write_cube(bw);
        drmb.write_cube(bw);
        dlb.write_cube(bw);
        dmlb.write_cube(bw);
        dmrb.write_cube(bw);
        drb.write_cube(bw);
    }
    
    public void load_from_file(BufferedReader input) throws IOException{ //OK
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
        tmlf.load_cube(input);
        tmrf.load_cube(input);
        trf.load_cube(input);
        tlmf.load_cube(input);
        tmlmf.load_cube(input);
        tmrmf.load_cube(input);
        trmf.load_cube(input);
        tlmb.load_cube(input);
        tmlmb.load_cube(input);
        tmrmb.load_cube(input);
        trmb.load_cube(input);
        tlb.load_cube(input);
        tmlb.load_cube(input);
        tmrb.load_cube(input);
        trb.load_cube(input);
        mtlf.load_cube(input);
        mtmlf.load_cube(input);
        mtmrf.load_cube(input);
        mtrf.load_cube(input);
        mtlmf.load_cube(input);
        mtmlmf.load_cube(input);
        mtmrmf.load_cube(input);
        mtrmf.load_cube(input);
        mtlmb.load_cube(input);
        mtmlmb.load_cube(input);
        mtmrmb.load_cube(input);
        mtrmb.load_cube(input);
        mtlb.load_cube(input);
        mtmlb.load_cube(input);
        mtmrb.load_cube(input);
        mtrb.load_cube(input);
        mdlf.load_cube(input);
        mdmlf.load_cube(input);
        mdmrf.load_cube(input);
        mdrf.load_cube(input);
        mdlmf.load_cube(input);
        mdmlmf.load_cube(input);
        mdmrmf.load_cube(input);
        mdrmf.load_cube(input);
        mdlmb.load_cube(input);
        mdmlmb.load_cube(input);
        mdmrmb.load_cube(input);
        mdrmb.load_cube(input);
        mdlb.load_cube(input);
        mdmlb.load_cube(input);
        mdmrb.load_cube(input);
        mdrb.load_cube(input);
        dlf.load_cube(input);
        dmlf.load_cube(input);
        dmrf.load_cube(input);
        drf.load_cube(input);
        dlmf.load_cube(input);
        dmlmf.load_cube(input);
        dmrmf.load_cube(input);
        drmf.load_cube(input);
        dlmb.load_cube(input);
        dmlmb.load_cube(input);
        dmrmb.load_cube(input);
        drmb.load_cube(input);
        dlb.load_cube(input);
        dmlb.load_cube(input);
        dmrb.load_cube(input);
        drb.load_cube(input);
    }
    
    /*choose the cubes to manipulate*/
    public void choose_cubes_top_left_right(Cube[] blocks){ //OK
        blocks[0]=trf;
        blocks[1]=tmrf;
        blocks[2]=tmlf;
        blocks[3]=tlf;
        blocks[4]=trmf;
        blocks[5]=tmrmf;
        blocks[6]=tmlmf;
        blocks[7]=tlmf;
        blocks[8]=trmb;
        blocks[9]=tmrmb;
        blocks[10]=tmlmb;
        blocks[11]=tlmb;
        blocks[12]=trb;
        blocks[13]=tmrb;
        blocks[14]=tmlb;
        blocks[15]=tlb;
    }
    public void choose_cubes_MT_left_right(Cube[] blocks){ //OK
        blocks[0]=mtrf;
        blocks[1]=mtmrf;
        blocks[2]=mtmlf;
        blocks[3]=mtlf;
        blocks[4]=mtrmf;
        blocks[5]=mtmrmf;
        blocks[6]=mtmlmf;
        blocks[7]=mtlmf;
        blocks[8]=mtrmb;
        blocks[9]=mtmrmb;
        blocks[10]=mtmlmb;
        blocks[11]=mtlmb;
        blocks[12]=mtrb;
        blocks[13]=mtmrb;
        blocks[14]=mtmlb;
        blocks[15]=mtlb;
    }
    public void choose_cubes_MD_left_right(Cube[] blocks){ //OK
        blocks[0]=mdrf;
        blocks[1]=mdmrf;
        blocks[2]=mdmlf;
        blocks[3]=mdlf;
        blocks[4]=mdrmf;
        blocks[5]=mdmrmf;
        blocks[6]=mdmlmf;
        blocks[7]=mdlmf;
        blocks[8]=mdrmb;
        blocks[9]=mdmrmb;
        blocks[10]=mdmlmb;
        blocks[11]=mdlmb;
        blocks[12]=mdrb;
        blocks[13]=mdmrb;
        blocks[14]=mdmlb;
        blocks[15]=mdlb;
    }
    public void choose_cubes_bottom_left_right(Cube[] blocks){ //OK
        blocks[0]=drf;
        blocks[1]=dmrf;
        blocks[2]=dmlf;
        blocks[3]=dlf;
        blocks[4]=drmf;
        blocks[5]=dmrmf;
        blocks[6]=dmlmf;
        blocks[7]=dlmf;
        blocks[8]=drmb;
        blocks[9]=dmrmb;
        blocks[10]=dmlmb;
        blocks[11]=dlmb;
        blocks[12]=drb;
        blocks[13]=dmrb;
        blocks[14]=dmlb;
        blocks[15]=dlb;
    }
    public void choose_cubes_left_up_down(Cube[] blocks){ //OK
        blocks[0]=tlf;
        blocks[1]=tlmf;
        blocks[2]=tlmb;
        blocks[3]=tlb;
        blocks[4]=mtlf;
        blocks[5]=mtlmf;
        blocks[6]=mtlmb;
        blocks[7]=mtlb;
        blocks[8]=mdlf;
        blocks[9]=mdlmf;
        blocks[10]=mdlmb;
        blocks[11]=mdlb;
        blocks[12]=dlf;
        blocks[13]=dlmf;
        blocks[14]=dlmb;
        blocks[15]=dlb;
    }
    public void choose_cubes_ML_up_down(Cube[] blocks){ //OK
        blocks[0]=tmlf;
        blocks[1]=tmlmf;
        blocks[2]=tmlmb;
        blocks[3]=tmlb;
        blocks[4]=mtmlf;
        blocks[5]=mtmlmf;
        blocks[6]=mtmlmb;
        blocks[7]=mtmlb;
        blocks[8]=mdmlf;
        blocks[9]=mdmlmf;
        blocks[10]=mdmlmb;
        blocks[11]=mdmlb;
        blocks[12]=dmlf;
        blocks[13]=dmlmf;
        blocks[14]=dmlmb;
        blocks[15]=dmlb;
    }
    public void choose_cubes_MR_up_down(Cube[] blocks){ //OK
        blocks[0]=tmrf;
        blocks[1]=tmrmf;
        blocks[2]=tmrmb;
        blocks[3]=tmrb;
        blocks[4]=mtmrf;
        blocks[5]=mtmrmf;
        blocks[6]=mtmrmb;
        blocks[7]=mtmrb;
        blocks[8]=mdmrf;
        blocks[9]=mdmrmf;
        blocks[10]=mdmrmb;
        blocks[11]=mdmrb;
        blocks[12]=dmrf;
        blocks[13]=dmrmf;
        blocks[14]=dmrmb;
        blocks[15]=dmrb;
    }
    public void choose_cubes_right_up_down(Cube[] blocks){ //OK
        blocks[0]=trf;
        blocks[1]=trmf;
        blocks[2]=trmb;
        blocks[3]=trb;
        blocks[4]=mtrf;
        blocks[5]=mtrmf;
        blocks[6]=mtrmb;
        blocks[7]=mtrb;
        blocks[8]=mdrf;
        blocks[9]=mdrmf;
        blocks[10]=mdrmb;
        blocks[11]=mdrb;
        blocks[12]=drf;
        blocks[13]=drmf;
        blocks[14]=drmb;
        blocks[15]=drb;
    }
    public void choose_cubes_front_cw_ccw(Cube[] blocks){ //OK
        blocks[0]=trf;
        blocks[1]=tmrf;
        blocks[2]=tmlf;
        blocks[3]=tlf;
        blocks[4]=mtrf;
        blocks[5]=mtmrf;
        blocks[6]=mtmlf;
        blocks[7]=mtlf;
        blocks[8]=mdrf;
        blocks[9]=mdmrf;
        blocks[10]=mdmlf;
        blocks[11]=mdlf;
        blocks[12]=drf;
        blocks[13]=dmrf;
        blocks[14]=dmlf;
        blocks[15]=dlf;
    }
    public void choose_cubes_MF_cw_ccw(Cube[] blocks){ //OK
        blocks[0]=trmf;
        blocks[1]=tmrmf;
        blocks[2]=tmlmf;
        blocks[3]=tlmf;
        blocks[4]=mtrmf;
        blocks[5]=mtmrmf;
        blocks[6]=mtmlmf;
        blocks[7]=mtlmf;
        blocks[8]=mdrmf;
        blocks[9]=mdmrmf;
        blocks[10]=mdmlmf;
        blocks[11]=mdlmf;
        blocks[12]=drmf;
        blocks[13]=dmrmf;
        blocks[14]=dmlmf;
        blocks[15]=dlmf;
    }
    public void choose_cubes_MB_cw_ccw(Cube[] blocks){ //OK
        blocks[0]=trmb;
        blocks[1]=tmrmb;
        blocks[2]=tmlmb;
        blocks[3]=tlmb;
        blocks[4]=mtrmb;
        blocks[5]=mtmrmb;
        blocks[6]=mtmlmb;
        blocks[7]=mtlmb;
        blocks[8]=mdrmb;
        blocks[9]=mdmrmb;
        blocks[10]=mdmlmb;
        blocks[11]=mdlmb;
        blocks[12]=drmb;
        blocks[13]=dmrmb;
        blocks[14]=dmlmb;
        blocks[15]=dlmb;
    }
    public void choose_cubes_back_cw_ccw(Cube[] blocks){ //OK
        blocks[0]=trb;
        blocks[1]=tmrb;
        blocks[2]=tmlb;
        blocks[3]=tlb;
        blocks[4]=mtrb;
        blocks[5]=mtmrb;
        blocks[6]=mtmlb;
        blocks[7]=mtlb;
        blocks[8]=mdrb;
        blocks[9]=mdmrb;
        blocks[10]=mdmlb;
        blocks[11]=mdlb;
        blocks[12]=drb;
        blocks[13]=dmrb;
        blocks[14]=dmlb;
        blocks[15]=dlb;
    }
    /*moves to manipulate orientation of cube*/
    public void top_move_right(){ //OK //MAYBE WRONG
        trf.top_move_right();
        tmrf.top_move_right();
        tmlf.top_move_right();
        tlf.top_move_right();
        trmf.top_move_right();
        tmrmf.top_move_right();
        tmlmf.top_move_right();
        tlmf.top_move_right();
        trmb.top_move_right();
        tmrmb.top_move_right();
        tmlmb.top_move_right();
        tlmb.top_move_right();
        trb.top_move_right();
        tmrb.top_move_right();
        tmlb.top_move_right();
        tlb.top_move_right();

        temp=trf; //CORNERS
        trf=tlf;
        tlf=tlb;
        tlb=trb;
        trb=temp;
        
        temp=tmrf; //MIDDLE EDGES
        tmrf=tlmf;
        tlmf=tmlb;
        tmlb=trmb;
        trmb=temp;
        
        temp=tmlf; //MIDDLES EDGES
        tmlf=tlmb;
        tlmb=tmrb;
        tmrb=trmf;
        trmf=temp; 
        
        temp=tmrmf; //MIDDLE
        tmrmf=tmlmf;
        tmlmf=tmlmb;
        tmlmb=tmrmb;
        tmrmb=temp;
    }
    public void top_move_left(){ //OK //MAYBE WRONG
        trf.top_move_left();
        tmrf.top_move_left();
        tmlf.top_move_left();
        tlf.top_move_left();
        trmf.top_move_left();
        tmrmf.top_move_left();
        tmlmf.top_move_left();
        tlmf.top_move_left();
        trmb.top_move_left();
        tmrmb.top_move_left();
        tmlmb.top_move_left();
        tlmb.top_move_left();
        trb.top_move_left();
        tmrb.top_move_left();
        tmlb.top_move_left();
        tlb.top_move_left();

        temp=trf; //CORNERS
        trf=trb;
        trb=tlb;
        tlb=tlf;
        tlf=temp;
        
        temp=tmrf; //MIDDLE EDGES
        tmrf=trmb;
        trmb=tmlb;
        tmlb=tlmf;
        tlmf=temp;
        
        temp=tmlf; //MIDDLE EDGES
        tmlf=trmf;
        trmf=tmrb;
        tmrb=tlmb; 
        tlmb=temp;
        
        temp=tmrmf; //MIDDLE
        tmrmf=tmrmb;
        tmrmb=tmlmb;
        tmlmb=tmlmf;
        tmlmf=temp;
    }
    public void MT_move_right(){ //OK
        mtrf.MT_move_right();
        mtmrf.MT_move_right();
        mtmlf.MT_move_right();
        mtlf.MT_move_right();
        mtrmf.MT_move_right();
        mtmrmf.MT_move_right();
        mtmlmf.MT_move_right();
        mtlmf.MT_move_right();
        mtrmb.MT_move_right();
        mtmrmb.MT_move_right();
        mtmlmb.MT_move_right();
        mtlmb.MT_move_right();
        mtrb.MT_move_right();
        mtmrb.MT_move_right();
        mtmlb.MT_move_right();
        mtlb.MT_move_right();

        temp=mtrf; //CORNERS
        mtrf=mtlf;
        mtlf=mtlb;
        mtlb=mtrb;
        mtrb=temp;
        
        temp=mtmrf; //MIDDLE EDGES
        mtmrf=mtlmf;
        mtlmf=mtmlb;
        mtmlb=mtrmb;
        mtrmb=temp;
        
        temp=mtmlf; //MIDDLE EDGES
        mtmlf=mtlmb;
        mtlmb=mtmrb;
        mtmrb=mtrmf;
        mtrmf=temp;
    }
    public void MT_move_left(){ //OK
        mtrf.MT_move_left();
        mtmrf.MT_move_left();
        mtmlf.MT_move_left();
        mtlf.MT_move_left();
        mtrmf.MT_move_left();
        mtmrmf.MT_move_left();
        mtmlmf.MT_move_left();
        mtlmf.MT_move_left();
        mtrmb.MT_move_left();
        mtmrmb.MT_move_left();
        mtmlmb.MT_move_left();
        mtlmb.MT_move_left();
        mtrb.MT_move_left();
        mtmrb.MT_move_left();
        mtmlb.MT_move_left();
        mtlb.MT_move_left();

        temp=mtrf; //CORNERS
        mtrf=mtrb;
        mtrb=mtlb;
        mtlb=mtlf;
        mtlf=temp;
        
        temp=mtmrf; //MIDDLE EDGES
        mtmrf=mtrmb;
        mtrmb=mtmlb;
        mtmlb=mtlmf;
        mtlmf=temp;
        
        temp=mtmlf; //MIDDLE EDGES
        mtmlf=mtrmf;
        mtrmf=mtmrb;
        mtmrb=mtlmb;
        mtlmb=temp;
    }
    public void MD_move_right(){ //OK
        mdrf.MD_move_right();
        mdmrf.MD_move_right();
        mdmlf.MD_move_right();
        mdlf.MD_move_right();
        mdrmf.MD_move_right();
        mdmrmf.MD_move_right();
        mdmlmf.MD_move_right();
        mdlmf.MD_move_right();
        mdrmb.MD_move_right();
        mdmrmb.MD_move_right();
        mdmlmb.MD_move_right();
        mdlmb.MD_move_right();
        mdrb.MD_move_right();
        mdmrb.MD_move_right();
        mdmlb.MD_move_right();
        mdlb.MD_move_right();

        temp=mdrf; //CORNERS
        mdrf=mdlf;
        mdlf=mdlb;
        mdlb=mdrb;
        mdrb=temp;
        
        temp=mdmrf; //MIDDLE EDGES
        mdmrf=mdlmf;
        mdlmf=mdmlb;
        mdmlb=mdrmb;
        mdrmb=temp;
        
        temp=mdmlf; //MIDDLE EDGES
        mdmlf=mdlmb;
        mdlmb=mdmrb;
        mdmrb=mdrmf;
        mdrmf=temp;
    }
    public void MD_move_left(){ //OK
        mdrf.MD_move_left();
        mdmrf.MD_move_left();
        mdmlf.MD_move_left();
        mdlf.MD_move_left();
        mdrmf.MD_move_left();
        mdmrmf.MD_move_left();
        mdmlmf.MD_move_left();
        mdlmf.MD_move_left();
        mdrmb.MD_move_left();
        mdmrmb.MD_move_left();
        mdmlmb.MD_move_left();
        mdlmb.MD_move_left();
        mdrb.MD_move_left();
        mdmrb.MD_move_left();
        mdmlb.MD_move_left();
        mdlb.MD_move_left();

        temp=mdrf; //CORNERS
        mdrf=mdrb;
        mdrb=mdlb;
        mdlb=mdlf;
        mdlf=temp;
        
        temp=mdmrf; //MIDDLE EDGES
        mdmrf=mdrmb;
        mdrmb=mdmlb;
        mdmlb=mdlmf;
        mdlmf=temp;
        
        temp=mdmlf; //MIDDLE EDGES
        mdmlf=mdrmf;
        mdrmf=mdmrb;
        mdmrb=mdlmb;
        mdlmb=temp;
    }
    public void bottom_move_right(){ //OK //WRONG
        drf.bottom_move_right();
        dmrf.bottom_move_right();
        dmlf.bottom_move_right();
        dlf.bottom_move_right();
        drmf.bottom_move_right();
        dmrmf.bottom_move_right();
        dmlmf.bottom_move_right();
        dlmf.bottom_move_right();
        drmb.bottom_move_right();
        dmrmb.bottom_move_right();
        dmlmb.bottom_move_right();
        dlmb.bottom_move_right();
        drb.bottom_move_right();
        dmrb.bottom_move_right();
        dmlb.bottom_move_right();
        dlb.bottom_move_right();

        temp=drf; //CORNERS
        drf=dlf;
        dlf=dlb;
        dlb=drb;
        drb=temp;
        
        temp=dmrf; //MIDDLE EDGES
        dmrf=dlmf;
        dlmf=dmlb;
        dmlb=drmb;
        drmb=temp;
        
        temp=dmlf; //MIDDLE EDGES
        dmlf=dlmb;
        dlmb=dmrb;
        dmrb=drmf;
        drmf=temp;
        
        temp=dmrmf;
        dmrmf=dmlmf;
        dmlmf=dmlmb;
        dmlmb=dmrmb;
        dmrmb=temp;
    }
    public void bottom_move_left(){ //OK //WRONG
        drf.bottom_move_left();
        dmrf.bottom_move_left();
        dmlf.bottom_move_left();
        dlf.bottom_move_left();
        drmf.bottom_move_left();
        dmrmf.bottom_move_left();
        dmlmf.bottom_move_left();
        dlmf.bottom_move_left();
        drmb.bottom_move_left();
        dmrmb.bottom_move_left();
        dmlmb.bottom_move_left();
        dlmb.bottom_move_left();
        drb.bottom_move_left();
        dmrb.bottom_move_left();
        dmlb.bottom_move_left();
        dlb.bottom_move_left();

        temp=drf; //CORNERS
        drf=drb;
        drb=dlb;
        dlb=dlf;
        dlf=temp;
        
        temp=dmrf; //MIDDLE EDGES
        dmrf=drmb;
        drmb=dmlb;
        dmlb=dlmf;
        dlmf=temp;
        
        temp=dmlf; //MIDDLE EDGES
        dmlf=drmf;
        drmf=dmrb;
        dmrb=dlmb;
        dlmb=temp; 
        
        temp=dmrmf; //MIDDLE
        dmrmf=dmrmb;
        dmrmb=dmlmb;
        dmlmb=dmlmf;
        dmlmf=temp;
    }
    public void left_move_down(){ //OK
        tlf.left_move_down();
        tlmf.left_move_down();
        tlmb.left_move_down();
        tlb.left_move_down();
        mtlf.left_move_down();
        mtlmf.left_move_down();
        mtlmb.left_move_down();
        mtlb.left_move_down();
        mdlf.left_move_down();
        mdlmf.left_move_down();
        mdlmb.left_move_down();
        mdlb.left_move_down();
        dlf.left_move_down();
        dlmf.left_move_down();
        dlmb.left_move_down();
        dlb.left_move_down();

        temp=tlf; //CORNERS
        tlf=tlb;
        tlb=dlb;
        dlb=dlf;
        dlf=temp;
        
        temp=tlmf; //MIDDLE EDGES
        tlmf=mtlb;
        mtlb=dlmb;
        dlmb=mdlf;
        mdlf=temp;
        
        temp=tlmb; //MIDDLE EDGES
        tlmb=mdlb;
        mdlb=dlmf;
        dlmf=mtlf;
        mtlf=temp; 
        
        temp=mtlmf; //MIDDLE
        mtlmf=mtlmb;
        mtlmb=mdlmb;
        mdlmb=mdlmf;
        mdlmf=temp;
    }
    public void left_move_up(){ //OK
        tlf.left_move_up();
        tlmf.left_move_up();
        tlmb.left_move_up();
        tlb.left_move_up();
        mtlf.left_move_up();
        mtlmf.left_move_up();
        mtlmb.left_move_up();
        mtlb.left_move_up();
        mdlf.left_move_up();
        mdlmf.left_move_up();
        mdlmb.left_move_up();
        mdlb.left_move_up();
        dlf.left_move_up();
        dlmf.left_move_up();
        dlmb.left_move_up();
        dlb.left_move_up();

        temp=tlf; //CORNERS
        tlf=dlf;
        dlf=dlb;
        dlb=tlb;
        tlb=temp;
        
        temp=tlmf; //MIDDLE EDGES
        tlmf=mdlf;
        mdlf=dlmb;
        dlmb=mtlb;
        mtlb=temp;
        
        temp=tlmb; //MIDDLE EDGES
        tlmb=mtlf;
        mtlf=dlmf;
        dlmf=mdlb;
        mdlb=temp;
        
        temp=mtlmf; //MIDDLE
        mtlmf=mdlmf;
        mdlmf=mdlmb;
        mdlmb=mtlmb;
        mtlmb=temp;
    }
    public void ML_move_down(){ //OK
        tmlf.ML_move_down();
        tmlmf.ML_move_down();
        tmlmb.ML_move_down();
        tmlb.ML_move_down();
        mtmlf.ML_move_down();
        mtmlmf.ML_move_down();
        mtmlmb.ML_move_down();
        mtmlb.ML_move_down();
        mdmlf.ML_move_down();
        mdmlmf.ML_move_down();
        mdmlmb.ML_move_down();
        mdmlb.ML_move_down();
        dmlf.ML_move_down();
        dmlmf.ML_move_down();
        dmlmb.ML_move_down();
        dmlb.ML_move_down();

        temp=tmlf; //CORNERS
        tmlf=tmlb;
        tmlb=dmlb;
        dmlb=dmlf;
        dmlf=temp;
        
        temp=tmlmf; //MIDDLE EDGES
        tmlmf=mtmlb;
        mtmlb=dmlmb;
        dmlmb=mdmlf;
        mdmlf=temp;
        
        temp=tmlmb; //MIDDLE EDGES
        tmlmb=mdmlb;
        mdmlb=dmlmf;
        dmlmf=mtmlf;
        mtmlf=temp;
    }    
    public void ML_move_up(){ //OK
        tmlf.ML_move_up();
        tmlmf.ML_move_up();
        tmlmb.ML_move_up();
        tmlb.ML_move_up();
        mtmlf.ML_move_up();
        mtmlmf.ML_move_up();
        mtmlmb.ML_move_up();
        mtmlb.ML_move_up();
        mdmlf.ML_move_up();
        mdmlmf.ML_move_up();
        mdmlmb.ML_move_up();
        mdmlb.ML_move_up();
        dmlf.ML_move_up();
        dmlmf.ML_move_up();
        dmlmb.ML_move_up();
        dmlb.ML_move_up();

        temp=tmlf; //CORNERS
        tmlf=dmlf;
        dmlf=dmlb;
        dmlb=tmlb;
        tmlb=temp;
        
        temp=tmlmf; //MIDDLE EDGES
        tmlmf=mdmlf;
        mdmlf=dmlmb;
        dmlmb=mtmlb;
        mtmlb=temp;
        
        temp=tmlmb; //MIDDLE EDGES
        tmlmb=mtmlf;
        mtmlf=dmlmf;
        dmlmf=mdmlb;
        mdmlb=temp;
    }
    public void MR_move_down(){ //OK
        tmrf.MR_move_down();
        tmrmf.MR_move_down();
        tmrmb.MR_move_down();
        tmrb.MR_move_down();
        mtmrf.MR_move_down();
        mtmrmf.MR_move_down();
        mtmrmb.MR_move_down();
        mtmrb.MR_move_down();
        mdmrf.MR_move_down();
        mdmrmf.MR_move_down();
        mdmrmb.MR_move_down();
        mdmrb.MR_move_down();
        dmrf.MR_move_down();
        dmrmf.MR_move_down();
        dmrmb.MR_move_down();
        dmrb.MR_move_down();

        temp=tmrf; //CORNER
        tmrf=tmrb;
        tmrb=dmrb;
        dmrb=dmrf;
        dmrf=temp;
        
        temp=tmrmf; //MIDDLE EDGES
        tmrmf=mtmrb;
        mtmrb=dmrmb;
        dmrmb=mdmrf;
        mdmrf=temp;
        
        temp=tmrmb; //MIDDLE EDGES
        tmrmb=mdmrb;
        mdmrb=dmrmf;
        dmrmf=mtmrf;
        mtmrf=temp;
    }
    public void MR_move_up(){ //OK
        tmrf.MR_move_up();
        tmrmf.MR_move_up();
        tmrmb.MR_move_up();
        tmrb.MR_move_up();
        mtmrf.MR_move_up();
        mtmrmf.MR_move_up();
        mtmrmb.MR_move_up();
        mtmrb.MR_move_up();
        mdmrf.MR_move_up();
        mdmrmf.MR_move_up();
        mdmrmb.MR_move_up();
        mdmrb.MR_move_up();
        dmrf.MR_move_up();
        dmrmf.MR_move_up();
        dmrmb.MR_move_up();
        dmrb.MR_move_up();

        temp=tmrf; //CORNERS
        tmrf=dmrf;
        dmrf=dmrb;
        dmrb=tmrb;
        tmrb=temp;
        
        temp=tmrmf; //MIDDLE EDGES
        tmrmf=mdmrf;
        mdmrf=dmrmb;
        dmrmb=mtmrb;
        mtmrb=temp;
        
        temp=tmrmb; //MIDDLE EDGES
        tmrmb=mtmrf;
        mtmrf=dmrmf;
        dmrmf=mdmrb;
        mdmrb=temp;
    }
    public void right_move_down(){ //OK
        trf.right_move_down();
        trmf.right_move_down();
        trmb.right_move_down();
        trb.right_move_down();
        mtrf.right_move_down();
        mtrmf.right_move_down();
        mtrmb.right_move_down();
        mtrb.right_move_down();
        mdrf.right_move_down();
        mdrmf.right_move_down();
        mdrmb.right_move_down();
        mdrb.right_move_down();
        drf.right_move_down();
        drmf.right_move_down();
        drmb.right_move_down();
        drb.right_move_down();

        temp=trf; //CORNERS
        trf=trb;
        trb=drb;
        drb=drf;
        drf=temp;
        
        temp=trmf; //MIDDLE EDGES
        trmf=mtrb;
        mtrb=drmb;
        drmb=mdrf;
        mdrf=temp;
        
        temp=trmb; //MIDDLE EDGES
        trmb=mdrb;
        mdrb=drmf;
        drmf=mtrf;
        mtrf=temp; 
        
        temp=mtrmf; //MIDDLE
        mtrmf=mtrmb;
        mtrmb=mdrmb;
        mdrmb=mdrmf;
        mdrmf=temp;
    }
    public void right_move_up(){ //OK
        trf.right_move_up();
        trmf.right_move_up();
        trmb.right_move_up();
        trb.right_move_up();
        mtrf.right_move_up();
        mtrmf.right_move_up();
        mtrmb.right_move_up();
        mtrb.right_move_up();
        mdrf.right_move_up();
        mdrmf.right_move_up();
        mdrmb.right_move_up();
        mdrb.right_move_up();
        drf.right_move_up();
        drmf.right_move_up();
        drmb.right_move_up();
        drb.right_move_up();

        temp=trf; //CORNERS
        trf=drf;
        drf=drb;
        drb=trb;
        trb=temp;
        
        temp=trmf; //MIDDLE EDGES
        trmf=mdrf;
        mdrf=drmb;
        drmb=mtrb;
        mtrb=temp;
        
        temp=trmb; //MIDDLE EDGES
        trmb=mtrf;
        mtrf=drmf;
        drmf=mdrb;
        mdrb=temp;
        
        temp=mtrmf; //MIDDLE
        mtrmf=mdrmf;
        mdrmf=mdrmb;
        mdrmb=mtrmb;
        mtrmb=temp;
    }
    public void front_face_cw(){ //OK
        trf.front_face_cw();
        tmrf.front_face_cw();
        tmlf.front_face_cw();
        tlf.front_face_cw();
        mtrf.front_face_cw();
        mtmrf.front_face_cw();
        mtmlf.front_face_cw();
        mtlf.front_face_cw();
        mdrf.front_face_cw();
        mdmrf.front_face_cw();
        mdmlf.front_face_cw();
        mdlf.front_face_cw();
        drf.front_face_cw();
        dmrf.front_face_cw();
        dmlf.front_face_cw();
        dlf.front_face_cw();

        temp=trf; //CORNERS
        trf=tlf;
        tlf=dlf;
        dlf=drf;
        drf=temp;
        
        temp=tmrf; //MIDDLE EDGES
        tmrf=mtlf;
        mtlf=dmlf;
        dmlf=mdrf;
        mdrf=temp;
        
        temp=tmlf; //MIDDLE EDGES
        tmlf=mdlf;
        mdlf=dmrf;
        dmrf=mtrf;
        mtrf=temp;
        
        temp=mtmrf; //MIDDLE
        mtmrf=mtmlf;
        mtmlf=mdmlf;
        mdmlf=mdmrf;
        mdmrf=temp;
    }
    public void front_face_ccw(){ //OK
        trf.front_face_ccw();
        tmrf.front_face_ccw();
        tmlf.front_face_ccw();
        tlf.front_face_ccw();
        mtrf.front_face_ccw();
        mtmrf.front_face_ccw();
        mtmlf.front_face_ccw();
        mtlf.front_face_ccw();
        mdrf.front_face_ccw();
        mdmrf.front_face_ccw();
        mdmlf.front_face_ccw();
        mdlf.front_face_ccw();
        drf.front_face_ccw();
        dmrf.front_face_ccw();
        dmlf.front_face_ccw();
        dlf.front_face_ccw();

        temp=trf; //CORNERS
        trf=drf;
        drf=dlf;
        dlf=tlf;
        tlf=temp;
        
        temp=tmrf; //MIDDLE EDGES
        tmrf=mdrf;
        mdrf=dmlf;
        dmlf=mtlf;
        mtlf=temp;
        
        temp=tmlf; //MIDDLE EDGES
        tmlf=mtrf;
        mtrf=dmrf;
        dmrf=mdlf;
        mdlf=temp;
        
        temp=mtmrf; //MIDDLE
        mtmrf=mdmrf;
        mdmrf=mdmlf;
        mdmlf=mtmlf;
        mtmlf=temp;
    }
    public void MF_face_cw(){ //OK
        trmf.MF_face_cw();
        tmrmf.MF_face_cw();
        tmlmf.MF_face_cw();
        tlmf.MF_face_cw();
        mtrmf.MF_face_cw();
        mtmrmf.MF_face_cw();
        mtmlmf.MF_face_cw();
        mtlmf.MF_face_cw();
        mdrmf.MF_face_cw();
        mdmrmf.MF_face_cw();
        mdmlmf.MF_face_cw();
        mdlmf.MF_face_cw();
        drmf.MF_face_cw();
        dmrmf.MF_face_cw();
        dmlmf.MF_face_cw();
        dlmf.MF_face_cw();

        temp=trmf; //CORNERS
        trmf=tlmf;
        tlmf=dlmf;
        dlmf=drmf;
        drmf=temp;
        
        temp=tmrmf; //MIDDLE EDGES
        tmrmf=mtlmf;
        mtlmf=dmlmf;
        dmlmf=mdrmf;
        mdrmf=temp;
        
        temp=tmlmf; //MIDDLE EDGES
        tmlmf=mdlmf;
        mdlmf=dmrmf;
        dmrmf=mtrmf;
        mtrmf=temp;
    }
    public void MF_face_ccw(){ //OK
        trmf.MF_face_ccw();
        tmrmf.MF_face_ccw();
        tmlmf.MF_face_ccw();
        tlmf.MF_face_ccw();
        mtrmf.MF_face_ccw();
        mtmrmf.MF_face_ccw();
        mtmlmf.MF_face_ccw();
        mtlmf.MF_face_ccw();
        mdrmf.MF_face_ccw();
        mdmrmf.MF_face_ccw();
        mdmlmf.MF_face_ccw();
        mdlmf.MF_face_ccw();
        drmf.MF_face_ccw();
        dmrmf.MF_face_ccw();
        dmlmf.MF_face_ccw();
        dlmf.MF_face_ccw();

        temp=trmf; //CORNERS
        trmf=drmf;
        drmf=dlmf;
        dlmf=tlmf;
        tlmf=temp;
        
        temp=tmrmf; //MIDDLE EDGES
        tmrmf=mdrmf;
        mdrmf=dmlmf;
        dmlmf=mtlmf;
        mtlmf=temp;
        
        temp=tmlmf; //MIDDLE EDGES
        tmlmf=mtrmf;
        mtrmf=dmrmf;
        dmrmf=mdlmf;
        mdlmf=temp;
    }
    public void MB_face_cw(){ //OK
        trmb.MB_face_cw();
        tmrmb.MB_face_cw();
        tmlmb.MB_face_cw();
        tlmb.MB_face_cw();
        mtrmb.MB_face_cw();
        mtmrmb.MB_face_cw();
        mtmlmb.MB_face_cw();
        mtlmb.MB_face_cw();
        mdrmb.MB_face_cw();
        mdmrmb.MB_face_cw();
        mdmlmb.MB_face_cw();
        mdlmb.MB_face_cw();
        drmb.MB_face_cw();
        dmrmb.MB_face_cw();
        dmlmb.MB_face_cw();
        dlmb.MB_face_cw();

        temp=trmb; //CORNERS
        trmb=tlmb;
        tlmb=dlmb;
        dlmb=drmb;
        drmb=temp;
        
        temp=tmrmb; //MIDDLE EDGES
        tmrmb=mtlmb;
        mtlmb=dmlmb;
        dmlmb=mdrmb;
        mdrmb=temp;
        
        temp=tmlmb; //MIDDLE EDGES
        tmlmb=mdlmb;
        mdlmb=dmrmb;
        dmrmb=mtrmb;
        mtrmb=temp;
    }
    public void MB_face_ccw(){ //OK
        trmb.MB_face_ccw();
        tmrmb.MB_face_ccw();
        tmlmb.MB_face_ccw();
        tlmb.MB_face_ccw();
        mtrmb.MB_face_ccw();
        mtmrmb.MB_face_ccw();
        mtmlmb.MB_face_ccw();
        mtlmb.MB_face_ccw();
        mdrmb.MB_face_ccw();
        mdmrmb.MB_face_ccw();
        mdmlmb.MB_face_ccw();
        mdlmb.MB_face_ccw();
        drmb.MB_face_ccw();
        dmrmb.MB_face_ccw();
        dmlmb.MB_face_ccw();
        dlmb.MB_face_ccw();

        temp=trmb; //CORNERS
        trmb=drmb;
        drmb=dlmb;
        dlmb=tlmb;
        tlmb=temp;
        
        temp=tmrmb; //MIDDLE EDGES
        tmrmb=mdrmb;
        mdrmb=dmlmb;
        dmlmb=mtlmb;
        mtlmb=temp;
        
        temp=tmlmb; //MIDDLE EDGES
        tmlmb=mtrmb;
        mtrmb=dmrmb;
        dmrmb=mdlmb;
        mdlmb=temp;
    }
    public void back_face_cw(){ //OK
        trb.back_face_cw();
        tmrb.back_face_cw();
        tmlb.back_face_cw();
        tlb.back_face_cw();
        mtrb.back_face_cw();
        mtmrb.back_face_cw();
        mtmlb.back_face_cw();
        mtlb.back_face_cw();
        mdrb.back_face_cw();
        mdmrb.back_face_cw();
        mdmlb.back_face_cw();
        mdlb.back_face_cw();
        drb.back_face_cw();
        dmrb.back_face_cw();
        dmlb.back_face_cw();
        dlb.back_face_cw();

        temp=trb; //CORNERS
        trb=tlb;
        tlb=dlb;
        dlb=drb;
        drb=temp;
        
        temp=tmrb; //MIDDLE EDGES
        tmrb=mtlb;
        mtlb=dmlb;
        dmlb=mdrb;
        mdrb=temp;
        
        temp=tmlb; //MIDDLE EDGES
        tmlb=mdlb;
        mdlb=dmrb;
        dmrb=mtrb;
        mtrb=temp;
        
        temp=mtmrb; //MIDDLE
        mtmrb=mtmlb;
        mtmlb=mdmlb;
        mdmlb=mdmrb;
        mdmrb=temp;
    }
    public void back_face_ccw(){ //OK
        trb.back_face_ccw();
        tmrb.back_face_ccw();
        tmlb.back_face_ccw();
        tlb.back_face_ccw();
        mtrb.back_face_ccw();
        mtmrb.back_face_ccw();
        mtmlb.back_face_ccw();
        mtlb.back_face_ccw();
        mdrb.back_face_ccw();
        mdmrb.back_face_ccw();
        mdmlb.back_face_ccw();
        mdlb.back_face_ccw();
        drb.back_face_ccw();
        dmrb.back_face_ccw();
        dmlb.back_face_ccw();
        dlb.back_face_ccw();

        temp=trb; //CORNERS
        trb=drb;
        drb=dlb;
        dlb=tlb;
        tlb=temp;
        
        temp=tmrb; //MIDDLE EDGES
        tmrb=mdrb;
        mdrb=dmlb;
        dmlb=mtlb;
        mtlb=temp;
        
        temp=tmlb; //MIDDLE EDGES
        tmlb=mtrb;
        mtrb=dmrb;
        dmrb=mdlb;
        mdlb=temp;
        
        temp=mtmrb; //MIDDLE
        mtmrb=mdmrb;
        mdmrb=mdmlb;
        mdmlb=mtmlb;
        mtmlb=temp;
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
                case MOVE_MTMR:
                    MT_move_right();
                    break;
                case MOVE_MBMR:
                    MD_move_right();
                    break;
                case MOVE_BMR:
                    bottom_move_right();
                    break;
                case MOVE_LMU:
                    left_move_up();
                    break;
                case MOVE_MLMU:
                    ML_move_up();
                    break;
                case MOVE_MRMU:
                    MR_move_up();
                    break;
                case MOVE_RMU:
                    right_move_up();
                    break;
                case MOVE_TML:
                    top_move_left();
                    break;
                case MOVE_MTML:
                    MT_move_left();
                    break;
                case MOVE_MBML:
                    MD_move_left();
                    break;
                case MOVE_BML:
                    bottom_move_left();
                    break;
                case MOVE_LMD:
                    left_move_down();
                    break;
                case MOVE_MLMD:
                    ML_move_down();
                    break;
                case MOVE_MRMD:
                    MR_move_down();
                    break;
                case MOVE_RMD:
                    right_move_down();
                    break;
                case MOVE_FCCW:
                    front_face_ccw();
                    break;
                case MOVE_MFCCW:
                    MF_face_ccw();
                    break;
                case MOVE_MBCCW:
                    MB_face_ccw();
                    break;
                case MOVE_BCCW:
                    back_face_ccw();
                    break;
                case MOVE_FCW:
                    front_face_cw();
                    break;
                case MOVE_MFCW:
                    MF_face_cw();
                    break;
                case MOVE_MBCW:
                    MB_face_cw();
                    break;
                case MOVE_BCW:
                    back_face_cw();
                    break;
            }
        }
    } //jumble up the cube for a new game
}
