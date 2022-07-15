package utils;

import cube.Rubiks_Cube;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.IntBuffer;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import static org.lwjgl.util.glu.GLU.gluPickMatrix;
import pieces.Cube;
import static utils.Variables.*;

/**
 *
 * @author Obringer
 */
public class Utils {
    int choice = ZERO; //current move choice
    int nearest; //final closest selected object during picking process with mouse
    //ofstream outs; //output stream (method) for writing to file
    //ifstream ins; //input stream (method) for reading from file
    Cube[] cubes = new Cube[POSSIBLE_MOVES]; //array of cube pointers to manipulate the correct cubes during
				//certain moves
    int win_h=WINDOW_SIZE; //window height
    int win_w=WINDOW_SIZE; //window width
    int mouse_x;	// mouse's x position on screen
    int mouse_y;	// mouse's y position on screen
    float ratio=(float)win_w/(float)win_h; //ratio of screen for gluPerspective view
    float fovy=INIT_FOVY; //the view angle for the screen in gluPerspective
    byte shine = NON_METALIC; //shine value for objects in program
    boolean play_sound=false; //play the sound?
    boolean over_ride_play=true; //if play_sound is true, override and cancel out sound?
    boolean win=false; //is the game won?

    float AmbientLight[] = { 1.0f, 1.0f, 1.0f, 1.0f }; //values for ambient light
    float DiffuseLight[] = { 0.1f, 0.1f, 0.1f, 1.0f }; //values for diffuse light
    float SpecularLight[] = { 0.1f, 0.1f, 0.1f, 1.0f }; //values for specular light
    float MaterialSpecular[] = {0.1f,0.1f,0.1f,1.0f}; //values for object specularity
    float MaterialEmission[] = {0.05f, 0.05f, 0.05f, 1.0f}; //values for object emissions
    float LightPos[] = {0.0f,0.0f,0.0f,0.0f}; //values for the position of the light source

    Rubiks_Cube rc = new Rubiks_Cube(); //our Rubik's Cube
    
    public Utils(){
    }
    
    private void unzoom_camera(){
        fovy+=FOVYINCREMENT;
        if(fovy>MAX_FOVY){
            fovy=MAX_FOVY;
        }
    }
    
    private void zoom_camera(){
        fovy-=FOVYINCREMENT;
        if(fovy<MIN_FOVY){
            fovy=MIN_FOVY;
        }
    }
    
    void save_file(){
        try {
            File file = new File("src/res/data.txt");
	    // if file doesnt exists, then create it
	    if (!file.exists()) {
		file.createNewFile();
	    }
 
	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
            
            rc.write_to_file(bw);
	    bw.close();
            System.out.println("Done");
 
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
    
    void load_file(){
        StringBuilder contents = new StringBuilder();
        try {
            
            File file = new File("src/res/data.txt");
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            BufferedReader input =  new BufferedReader(new FileReader(file));
            try {
                
                rc.load_from_file(input);
                /*
                * readLine is a bit quirky :
                * it returns the content of a line MINUS the newline.
                * it returns null only for the END of the stream.
                * it returns an empty String if two newlines appear in a row.
                */
            }finally {
                input.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    //***********************************************************************
    //
    // Function: choose_move_TMR
    //
    // Purpose: set the top move right move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_top_left_right
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_TMR(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_top_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_TMR;
    }

    //***********************************************************************
    //
    // Function: choose_move_MHMR
    //
    // Purpose: set the middle horizontal move right move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_MH_left_right
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_MTMR(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_MT_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_MTMR;
    }
    
    void choose_move_MMR(){
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_middle_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_CMR;
    }
    
    void choose_move_MDMR(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_MD_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_MBMR;
    }

    //***********************************************************************
    //
    // Function: choose_move_BMR
    //
    // Purpose: set the bottom move right move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_bottom_left_right
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_BMR(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_bottom_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_BMR;
    }

    //***********************************************************************
    //
    // Function: choose_move_LMU
    //
    // Purpose: set the left move up move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_left_up_down
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_LMU(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_left_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_LMU;
    }

    //***********************************************************************
    //
    // Function: choose_move_MVMU
    //
    // Purpose: set the middle vertical move up move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_MV_up_down
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_MRMU(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_MR_up_down(cubes); //ok
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_MRMU;
    }
    void choose_move_MMU(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_middle_up_down(cubes); //ok
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_CMU;
    }
    void choose_move_MLMU(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_ML_up_down(cubes); //ok
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_MLMU;
    }

    //***********************************************************************
    //
    // Function: choose_move_RMU
    //
    // Purpose: set the middle vertical move up move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_right_up_down
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_RMU(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_right_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_RMU;
    }

    //***********************************************************************
    //
    // Function: choose_move_TML
    //
    // Purpose: set the top move left move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_top_left_right
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_TML(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_top_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_TML;
    }

    //***********************************************************************
    //
    // Function: choose_move_MHML
    //
    // Purpose: set the middle horizontal move left move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_MH_left_right
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_MTML(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_MT_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_MTML;
    }
    void choose_move_MML(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_middle_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_CML;
    }
    void choose_move_MDML(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_MD_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_MBML;
    }

    //***********************************************************************
    //
    // Function: choose_move_BML
    //
    // Purpose: set the bottom move left move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_bottom_left_right
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_BML(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_bottom_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_BML;
    }

    //***********************************************************************
    //
    // Function: choose_move_LMD
    //
    // Purpose: set the left move down move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_left_up_down
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_LMD(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_left_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_LMD;
    }

    //***********************************************************************
    //
    // Function: choose_move_MVMD
    //
    // Purpose: set the middle vertical move down move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_MV_up_down
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_MRMD(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_MR_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_MRMD;
    }
    void choose_move_MMD(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_middle_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_CMD;
    }
    void choose_move_MLMD(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_ML_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_MLMD;
    }

    //***********************************************************************
    //
    // Function: choose_move_RMD
    //
    // Purpose: set the right move down move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_right_up_down
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_RMD(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_right_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_RMD;
    }

    //***********************************************************************
    //
    // Function: choose_move_FCCW
    //
    // Purpose: set the front move counter_clockwise move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_front_cw_ccw
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_FCCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_front_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_FCCW;
    }

    //***********************************************************************
    //
    // Function: choose_move_MCCW
    //
    // Purpose: set the middle face move counter_clockwise move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_MF_cw_ccw
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_MFCCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_MF_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_MFCCW;
    }
    void choose_move_MCCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_middle_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_CCCW;
    }
    void choose_move_MBCCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_MB_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_MBCCW;
    }

    //***********************************************************************
    //
    // Function: choose_move_BCCW
    //
    // Purpose: set the back move counter_clockwise move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_back_cw_ccw
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_BCCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_back_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_BCCW;
    }

    //***********************************************************************
    //
    // Function: choose_move_FCW
    //
    // Purpose: set the front move clockwise move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_front_cw_ccw
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_FCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_front_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_FCW;
    }

    //***********************************************************************
    //
    // Function: choose_move_MCW
    //
    // Purpose: set the middle face move clockwise move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_MF_cw_ccw
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_MFCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_MF_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_MFCW;
    }
    void choose_move_MCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_middle_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_CCW;
    }
    void choose_move_MBCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_MB_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_MBCW;
    }

    //***********************************************************************
    //
    // Function: choose_move_BCW
    //
    // Purpose: set the back move clockwise move into animation and allows the 
    //    inidivual cubes in the action to be set for this movement. Also makes
    //    sure that other moves cannot be made while the animation is taking place.
    //
    // Calls: Rubiks_Cube::mutate_in_progress
    //        Rubiks_Cube::mutate_direction
    //        Rubiks_Cube::choose_cubes_back_cw_ccw
    //        Rubiks_Cube::mutate_axese
    //
    //***********************************************************************
    void choose_move_BCW(){ //OK
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_back_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_BCW;
    }
    
    //***********************************************************************
    //
    // Function: top_cubes
    //
    // Purpose: check if one of the top cubes can be manipulated
    //
    //***********************************************************************
    boolean top_cubes(){ //OK
        if((nearest==ID_TLF)||(nearest==ID_TMLF)||(nearest==ID_TMRF)||(nearest==ID_TRF)||
                (nearest==ID_TLMF)||(nearest==ID_TMLMF)||(nearest==ID_TMRMF)||(nearest==ID_TRMF)||
                (nearest==ID_TLMB)||(nearest==ID_TMLMB)||(nearest==ID_TMRMB)||(nearest==ID_TRMB)||
                (nearest==ID_TLB)||(nearest==ID_TMLB)||(nearest==ID_TMRB)||(nearest==ID_TRB)||
                (nearest==ID_TCF)||(nearest==ID_TCMF)||(nearest==ID_TCC)||(nearest==ID_TCMB)||
                (nearest==ID_TCB)||(nearest==ID_TLC)||(nearest==ID_TMLC)||(nearest==ID_TMRC)||
                (nearest==ID_TRC)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: middle_horizontal_cubes
    //
    // Purpose: check if one of the middle horizontal cubes can be manipulated
    //
    //***********************************************************************
    boolean MT_cubes(){ //OK
        if((nearest==ID_MTLF)||(nearest==ID_MTMLF)||(nearest==ID_MTMRF)||(nearest==ID_MTRF)||
                (nearest==ID_MTLMF)||(nearest==ID_MTMLMF)||(nearest==ID_MTMRMF)||(nearest==ID_MTRMF)||
                (nearest==ID_MTLMB)||(nearest==ID_MTMLMB)||(nearest==ID_MTMRMB)||(nearest==ID_MTRMB)||
                (nearest==ID_MTLB)||(nearest==ID_MTMLB)||(nearest==ID_MTMRB)||(nearest==ID_MTRB)||
                (nearest==ID_MTCF)||(nearest==ID_MTCB)||(nearest==ID_MTLC)||(nearest==ID_MTRC)){
            return true;
        }else{
            return false;
        }
    }
    
    boolean middle_horizontal_cubes(){
        if((nearest==ID_CRF)||(nearest==ID_CMRF)||(nearest==ID_CCF)||(nearest==ID_CMLF)||
                (nearest==ID_CLF)||(nearest==ID_CRB)||(nearest==ID_CMRB)||(nearest==ID_CCB)||
                (nearest==ID_CMLB)||(nearest==ID_CLB)||(nearest==ID_CRMF)||(nearest==ID_CRC)||
                (nearest==ID_CRMB)||(nearest==ID_CLMF)||(nearest==ID_CLC)||(nearest==ID_CLMB)){
            return true;
        }else{
            return false;
        }
    }
    
    boolean MD_cubes(){ //OK
        if((nearest==ID_MDLF)||(nearest==ID_MDMLF)||(nearest==ID_MDMRF)||(nearest==ID_MDRF)||
                (nearest==ID_MDLMF)||(nearest==ID_MDMLMF)||(nearest==ID_MDMRMF)||(nearest==ID_MDRMF)||
                (nearest==ID_MDLMB)||(nearest==ID_MDMLMB)||(nearest==ID_MDMRMB)||(nearest==ID_MDRMB)||
                (nearest==ID_MDLB)||(nearest==ID_MDMLB)||(nearest==ID_MDMRB)||(nearest==ID_MDRB)||
                (nearest==ID_MDCF)||(nearest==ID_MDCB)||(nearest==ID_MDLC)||(nearest==ID_MDRC)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: bottom_cubes
    //
    // Purpose: check if one of the bottom cubes can be manipulated
    //
    //***********************************************************************
    boolean bottom_cubes(){ //OK
        if((nearest==ID_DLF)||(nearest==ID_DMLF)||(nearest==ID_DMRF)||(nearest==ID_DRF)||
                (nearest==ID_DLMF)||(nearest==ID_DMLMF)||(nearest==ID_DMRMF)||(nearest==ID_DRMF)||
                (nearest==ID_DLMB)||(nearest==ID_DMLMB)||(nearest==ID_DMRMB)||(nearest==ID_DRMB)||
                (nearest==ID_DLB)||(nearest==ID_DMLB)||(nearest==ID_DMRB)||(nearest==ID_DRB)||
                (nearest==ID_DCF)||(nearest==ID_DCMF)||(nearest==ID_DCC)||(nearest==ID_DCMB)||
                (nearest==ID_DCB)||(nearest==ID_DLC)||(nearest==ID_DMLC)||(nearest==ID_DMRC)||
                (nearest==ID_DRC)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: left_cubes
    //
    // Purpose: check if one of the left cubes can be manipulated
    //
    //***********************************************************************
    boolean left_cubes(){ //OK
        if((nearest==ID_TLF)||(nearest==ID_TLMF)||(nearest==ID_TLMB)||(nearest==ID_TLB)||
                (nearest==ID_MTLF)||(nearest==ID_MTLMF)||(nearest==ID_MTLMB)||(nearest==ID_MTLB)||
                (nearest==ID_MDLF)||(nearest==ID_MDLMF)||(nearest==ID_MDLMB)||(nearest==ID_MDLB)||
                (nearest==ID_DLF)||(nearest==ID_DLMF)||(nearest==ID_DLMB)||(nearest==ID_DLB)||
                (nearest==ID_TLC)||(nearest==ID_MTLC)||(nearest==ID_CLC)||(nearest==ID_MDLC)||
                (nearest==ID_DLC)||(nearest==ID_CLF)||(nearest==ID_CLMF)||(nearest==ID_CLMB)||
                (nearest==ID_CLB)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: middle_vertical_cubes
    //
    // Purpose: check if one of the middle vertical cubes can be manipulated
    //
    //***********************************************************************
    boolean ML_cubes(){ //OK
        if((nearest==ID_TMLF)||(nearest==ID_TMLMF)||(nearest==ID_TMLMB)||(nearest==ID_TMLB)||
                (nearest==ID_MTMLF)||(nearest==ID_MTMLMF)||(nearest==ID_MTMLMB)||(nearest==ID_MTMLB)||
                (nearest==ID_MDMLF)||(nearest==ID_MDMLMF)||(nearest==ID_MDMLMB)||(nearest==ID_MDMLB)||
                (nearest==ID_DMLF)||(nearest==ID_DMLMF)||(nearest==ID_DMLMB)||(nearest==ID_DMLB)||
                (nearest==ID_TMLC)||(nearest==ID_DMLC)||(nearest==ID_CMLF)||(nearest==ID_CMLB)){
            return true;
        }else{
            return false;
        }
    }
    
    boolean middle_vertical_cubes(){
        if((nearest==ID_TCF)||(nearest==ID_MTCF)||(nearest==ID_CCF)||(nearest==ID_MDCF)||
                (nearest==ID_DCF)||(nearest==ID_TCB)||(nearest==ID_MTCB)||(nearest==ID_CCB)||
                (nearest==ID_MDCB)||(nearest==ID_DCB)||(nearest==ID_TCMF)||(nearest==ID_TCC)||
                (nearest==ID_TCMB)||(nearest==ID_DCMF)||(nearest==ID_DCC)||(nearest==ID_DCB)){
            return true;
        }else{
            return false;
        }
    }
    
    boolean MR_cubes(){ //OK
        if((nearest==ID_TMRF)||(nearest==ID_TMRMF)||(nearest==ID_TMRMB)||(nearest==ID_TMRB)||
                (nearest==ID_MTMRF)||(nearest==ID_MTMRMF)||(nearest==ID_MTMRMB)||(nearest==ID_MTMRB)||
                (nearest==ID_MDMRF)||(nearest==ID_MDMRMF)||(nearest==ID_MDMRMB)||(nearest==ID_MDMRB)||
                (nearest==ID_DMRF)||(nearest==ID_DMRMF)||(nearest==ID_DMRMB)||(nearest==ID_DMRB)||
                (nearest==ID_TMRC)||(nearest==ID_DMRC)||(nearest==ID_CMRF)||(nearest==ID_CMRB)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: right_cubes
    //
    // Purpose: check if one of the right cubes can be manipulated
    //
    //***********************************************************************
    boolean right_cubes(){ //OK
        if((nearest==ID_TRF)||(nearest==ID_TRMF)||(nearest==ID_TRMB)||(nearest==ID_TRB)||
                (nearest==ID_MTRF)||(nearest==ID_MTRMF)||(nearest==ID_MTRMB)||(nearest==ID_MTRB)||
                (nearest==ID_MDRF)||(nearest==ID_MDRMF)||(nearest==ID_MDRMB)||(nearest==ID_MDRB)||
                (nearest==ID_DRF)||(nearest==ID_DRMF)||(nearest==ID_DRMB)||(nearest==ID_DRB)||
                (nearest==ID_TRC)||(nearest==ID_MTRC)||(nearest==ID_CRC)||(nearest==ID_MDRC)||
                (nearest==ID_DRC)||(nearest==ID_CRF)||(nearest==ID_CRMF)||(nearest==ID_CRMB)||
                (nearest==ID_CRB)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: front_cubes
    //
    // Purpose: check if one of the front cubes can be manipulated
    //
    //***********************************************************************
    boolean front_cubes(){ //OK
        if((nearest==ID_TLF)||(nearest==ID_TMLF)||(nearest==ID_TMRF)||(nearest==ID_TRF)||
                (nearest==ID_MTLF)||(nearest==ID_MTMLF)||(nearest==ID_MTMRF)||(nearest==ID_MTRF)||
                (nearest==ID_MDLF)||(nearest==ID_MDMLF)||(nearest==ID_MDMRF)||(nearest==ID_MDRF)||
                (nearest==ID_DLF)||(nearest==ID_DMLF)||(nearest==ID_DMRF)||(nearest==ID_DRF)||
                (nearest==ID_TCF)||(nearest==ID_MTCF)||(nearest==ID_CCF)||(nearest==ID_MDCF)||
                (nearest==ID_DCF)||(nearest==ID_CLF)||(nearest==ID_CMLF)||(nearest==ID_CMRF)||
                (nearest==ID_CRF)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: middle_face_cubes
    //
    // Purpose: check if one of the middle_face cubes can be manipulated
    //
    //***********************************************************************
    boolean MF_cubes(){ //OK
        if((nearest==ID_TLMF)||(nearest==ID_TMLMF)||(nearest==ID_TMRMF)||(nearest==ID_TRMF)||
                (nearest==ID_MTLMF)||(nearest==ID_MTMLMF)||(nearest==ID_MTMRMF)||(nearest==ID_MTRMF)||
                (nearest==ID_MDLMF)||(nearest==ID_MDMLMF)||(nearest==ID_MDMRMF)||(nearest==ID_MDRMF)||
                (nearest==ID_DLMF)||(nearest==ID_DMLMF)||(nearest==ID_DMRMF)||(nearest==ID_DRMF)||
                (nearest==ID_TCMF)||(nearest==ID_DCMF)||(nearest==ID_CLMF)||(nearest==ID_CRMF)){
            return true;
        }else{
            return false;
        }
    }
    
    boolean middle_face_cubes(){
        if((nearest==ID_TRC)||(nearest==ID_TMRC)||(nearest==ID_TCC)||(nearest==ID_TMLC)||
                (nearest==ID_TLC)||(nearest==ID_DRC)||(nearest==ID_DMRC)||(nearest==ID_DCC)||
                (nearest==ID_DMLC)||(nearest==ID_DLC)||(nearest==ID_MTRC)||(nearest==ID_CRC)||
                (nearest==ID_MDRC)||(nearest==ID_MTLC)||(nearest==ID_CLC)||(nearest==ID_MDLC)){
            return true;
        }else{
            return false;
        }
    }
    
    boolean MB_cubes(){ //OK
        if((nearest==ID_TLMB)||(nearest==ID_TMLMB)||(nearest==ID_TMRMB)||(nearest==ID_TRMB)||
                (nearest==ID_MTLMB)||(nearest==ID_MTMLMB)||(nearest==ID_MTMRMB)||(nearest==ID_MTRMB)||
                (nearest==ID_MDLMB)||(nearest==ID_MDMLMB)||(nearest==ID_MDMRMB)||(nearest==ID_MDRMB)||
                (nearest==ID_DLMB)||(nearest==ID_DMLMB)||(nearest==ID_DMRMB)||(nearest==ID_DRMB)||
                (nearest==ID_TCMB)||(nearest==ID_DCMB)||(nearest==ID_CLMB)||(nearest==ID_CRMB)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: back_cubes
    //
    // Purpose: check if one of the back cubes can be manipulated
    //
    //***********************************************************************
    boolean back_cubes(){ //OK
        if((nearest==ID_TLB)||(nearest==ID_TMLB)||(nearest==ID_TMRB)||(nearest==ID_TRB)||
                (nearest==ID_MTLB)||(nearest==ID_MTMLB)||(nearest==ID_MTMRB)||(nearest==ID_MTRB)||
                (nearest==ID_MDLB)||(nearest==ID_MDMLB)||(nearest==ID_MDMRB)||(nearest==ID_MDRB)||
                (nearest==ID_DLB)||(nearest==ID_DMLB)||(nearest==ID_DMRB)||(nearest==ID_DRB)||
                (nearest==ID_TCB)||(nearest==ID_MTCB)||(nearest==ID_CCB)||(nearest==ID_MDCB)||
                (nearest==ID_DCB)||(nearest==ID_CLB)||(nearest==ID_CMLB)||(nearest==ID_CMRB)||
                (nearest==ID_CRB)){
            return true;
        }else{
            return false;
        }
    }


        //***********************************************************************
    //
    // Function: animate_horizontal_cubes_right
    //
    // Purpose: Covers more cases to clean up code. Animates cubes moving right.
    //
    // Calls: Rubiks_Cube::top_cubes
    //        Rubiks_Cube::choose_move_TMR
    //        Rubiks_Cube::middle_horizontal_cubes
    //        Rubiks_Cube::choose_move_MHMR
    //        Rubiks_Cube::bottom_cubes
    //        Rubiks_Cube::choose_move_BMR
    //
    //***********************************************************************
    void animate_horizontal_cubes_right(){ //OK
      if(top_cubes()){
          choose_move_TMR();
      }else if(MT_cubes()){
          choose_move_MTMR();
      }else if(middle_horizontal_cubes()){
          choose_move_MMR();
      }else if(MD_cubes()){
          choose_move_MDMR();
      }else if(bottom_cubes()){
          choose_move_BMR();
      }
    }

    //***********************************************************************
    //
    // Function: animate_horizontal_cubes_left
    //
    // Purpose: Covers more cases to clean up code. Animates cubes moving left.
    //
    // Calls: Rubiks_Cube::top_cubes
    //        Rubiks_Cube::choose_move_TML
    //        Rubiks_Cube::middle_horizontal_cubes
    //        Rubiks_Cube::choose_move_MHML
    //        Rubiks_Cube::bottom_cubes
    //        Rubiks_Cube::choose_move_BML
    //
    //***********************************************************************
    void animate_horizontal_cubes_left(){ //OK
      if(top_cubes()){
          choose_move_TML();
      }else if(MT_cubes()){
          choose_move_MTML();
      }else if(middle_horizontal_cubes()){
          choose_move_MML();
      }else if(MD_cubes()){
          choose_move_MDML();
      }else if(bottom_cubes()){
          choose_move_BML();
      }
    }

    //***********************************************************************
    //
    // Function: animate_vertical_cubes_up
    //
    // Purpose: Covers more cases to clean up code. Animates cubes moving up.
    //
    // Calls: Rubiks_Cube::left_cubes
    //        Rubiks_Cube::choose_move_LMU
    //        Rubiks_Cube::middle_vertical_cubes
    //        Rubiks_Cube::choose_move_MVMU
    //        Rubiks_Cube::right_cubes
    //        Rubiks_Cube::choose_move_RMU
    //
    //***********************************************************************
    void animate_vertical_cubes_up(){ //OK
      if(left_cubes()){
          choose_move_LMU();
      }else if(ML_cubes()){
          choose_move_MLMU();
      }else if(middle_vertical_cubes()){
          choose_move_MMU();
      }else if(MR_cubes()){
          choose_move_MRMU();
      }else if(right_cubes()){
          choose_move_RMU();
      }
    }

    //***********************************************************************
    //
    // Function: animate_vertical_cubes_down
    //
    // Purpose: Covers more cases to clean up code. Animates cubes moving down.
    //
    // Calls: Rubiks_Cube::left_cubes
    //        Rubiks_Cube::choose_move_LMD
    //        Rubiks_Cube::middle_vertical_cubes
    //        Rubiks_Cube::choose_move_MVMD
    //        Rubiks_Cube::right_cubes
    //        Rubiks_Cube::choose_move_RMD
    //
    //***********************************************************************
    void animate_vertical_cubes_down(){ //OK
      if(left_cubes()){
          choose_move_LMD();
      }else if(ML_cubes()){
          choose_move_MLMD();
      }else if(middle_vertical_cubes()){
          choose_move_MMD();
      }else if(MR_cubes()){
          choose_move_MRMD();
      }else if(right_cubes()){
          choose_move_RMD();
      }
    }

    //***********************************************************************
    //
    // Function: animate_faces_ccw
    //
    // Purpose: Covers more cases to clean up code. Animates cubes moving 
    //		counter_clockwise.
    //
    // Calls: Rubiks_Cube::front_cubes
    //        Rubiks_Cube::choose_move_FCCW
    //        Rubiks_Cube::middle_face_cubes
    //        Rubiks_Cube::choose_move_MCCW
    //        Rubiks_Cube::back_cubes
    //        Rubiks_Cube::choose_move_BCCW
    //
    //***********************************************************************
    void animate_faces_ccw(){ //OK
      if(front_cubes()){
          choose_move_FCCW();
      }else if(MF_cubes()){
          choose_move_MFCCW();
      }else if(middle_face_cubes()){
          choose_move_MCCW();
      }else if(MB_cubes()){
          choose_move_MBCCW();
      }else if(back_cubes()){
          choose_move_BCCW();
      }
    }

    //***********************************************************************
    //
    // Function: animate_faces_cw
    //
    // Purpose: Covers more cases to clean up code. Animates cubes moving 
    //		clockwise.
    //
    // Calls: Rubiks_Cube::front_cubes
    //        Rubiks_Cube::choose_move_FCW
    //        Rubiks_Cube::middle_face_cubes
    //        Rubiks_Cube::choose_move_MCW
    //        Rubiks_Cube::back_cubes
    //        Rubiks_Cube::choose_move_BCW
    //
    //***********************************************************************
    void animate_faces_cw(){ //ok
      if(front_cubes()){
          choose_move_FCW();
      }else if(MF_cubes()){
          choose_move_MFCW();
      }else if(middle_face_cubes()){
          choose_move_MCW();
      }else if(MB_cubes()){
          choose_move_MBCW();
      }else if(back_cubes()){
          choose_move_BCW();
      }
    }


    //***********************************************************************
    //
    // Function: quadrant_1
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 1, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_1(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_faces_cw();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_2
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 2, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_2(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_vertical_cubes_down();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_3
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 3, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_3(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_faces_ccw();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_4
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 4, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_4(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_vertical_cubes_up();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_5
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 5, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_5(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_horizontal_cubes_left();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_6
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 6, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_6(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_horizontal_cubes_left();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_7
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 7, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_7(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_horizontal_cubes_left();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_8
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 8, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_8(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_horizontal_cubes_left();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_9
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 9, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_9(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_faces_ccw();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_10
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 10, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_10(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_vertical_cubes_up();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_11
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 11, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_11(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_faces_cw();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_12
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 12, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_12(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_vertical_cubes_down();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_13
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 13, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_13(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_horizontal_cubes_right();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_14
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 14, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_14(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_horizontal_cubes_right();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_15
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 15, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_15(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_horizontal_cubes_right();
        }
    }

    //***********************************************************************
    //
    // Function: quadrant_16
    //
    // Purpose: if the view of the Rubiks Cube is is quadrant 16, correctly
    //    allow proper selection of moves to make when pressing up,down,left,right,
    //    page up, and page down while in this view of the cube.
    //
    // Calls: Rubiks_Cube::animate_horizontal_cubes_left
    //        Rubiks_Cube::animate_horizontal_cubes_right
    //        Rubiks_Cube::animate_vertical_cubes_up
    //        Rubiks_Cube::animate_vertical_cubes_down
    //        Rubiks_Cube::animate_faces_ccw
    //        Rubiks_Cube::animate_faces_cw
    //
    //***********************************************************************
    void quadrant_16(){
        if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT){
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LEFT){
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_UP){
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_DOWN){
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){
            animate_horizontal_cubes_right();
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_1
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 1.
    //	(315<x<45) and (315<y<45)
    //
    //***********************************************************************
    boolean if_quadrant_1(int x,int y){
        if((((x>=SEVEN_EIGHTHS_CIRCLE)&&(x<=CIRCLE))||((x>=ZERO)&&(x<=EIGHTH_CIRCLE)))&&
              (((y>=SEVEN_EIGHTHS_CIRCLE)&&(y<=CIRCLE))||((y>=ZERO)&&(y<=EIGHTH_CIRCLE)))){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_2
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 2.
    //	(315<x<45) and (45<y<135)
    //
    //***********************************************************************
    boolean if_quadrant_2(int x,int y){
        if((((x>=SEVEN_EIGHTHS_CIRCLE)&&(x<=CIRCLE))||((x>=ZERO)&&(x<=EIGHTH_CIRCLE)))&&
              (y>=EIGHTH_CIRCLE)&&(y<=THREE_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_3
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 3.
    //	(315<x<45) and (135<y<225)
    //
    //***********************************************************************
    boolean if_quadrant_3(int x,int y){
        if((((x>=SEVEN_EIGHTHS_CIRCLE)&&(x<=CIRCLE))||((x>=ZERO)&&(x<=EIGHTH_CIRCLE)))
              &&(y>=THREE_EIGHTHS_CIRCLE)&&(y<=FIVE_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_4
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 4.
    //	(315<x<45) and (225<y<315)
    //
    //***********************************************************************
    boolean if_quadrant_4(int x,int y){
        if((((x>=SEVEN_EIGHTHS_CIRCLE)&&(x<=CIRCLE))||((x>=ZERO)&&(x<=EIGHTH_CIRCLE)))&&
              (y>=FIVE_EIGHTHS_CIRCLE)&&(y<=SEVEN_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_5
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 5.
    //	(45<x<135) and (315<y<45)
    //
    //***********************************************************************
    boolean if_quadrant_5(int x,int y){
        if((x>=EIGHTH_CIRCLE)&&(x<=THREE_EIGHTHS_CIRCLE)&&(((y>=SEVEN_EIGHTHS_CIRCLE)&&(y<=CIRCLE))||
              ((y>=ZERO)&&(y<=EIGHTH_CIRCLE)))){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_6
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 6.
    //	(45<x<135) and (45<y<135)
    //
    //***********************************************************************
    boolean if_quadrant_6(int x,int y){
        if((x>=EIGHTH_CIRCLE)&&(x<=THREE_EIGHTHS_CIRCLE)&&(y>=EIGHTH_CIRCLE)&&(y<=THREE_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_7
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 7.
    //	(45<x<135) and (135<y<225)
    //
    //***********************************************************************
    boolean if_quadrant_7(int x,int y){
        if((x>=EIGHTH_CIRCLE)&&(x<=THREE_EIGHTHS_CIRCLE)&&(y>=THREE_EIGHTHS_CIRCLE)&&(y<=FIVE_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_8
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 8.
    //	(45<x<135) and (225<y<315)
    //
    //***********************************************************************
    boolean if_quadrant_8(int x,int y){
        if((x>=EIGHTH_CIRCLE)&&(x<=THREE_EIGHTHS_CIRCLE)&&(y>=FIVE_EIGHTHS_CIRCLE)&&(y<=SEVEN_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_9
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 9.
    //	(135<x<225) and (315<y<45)
    //
    //***********************************************************************
    boolean if_quadrant_9(int x,int y){
        if((x>=THREE_EIGHTHS_CIRCLE)&&(x<=FIVE_EIGHTHS_CIRCLE)&&(((y>=SEVEN_EIGHTHS_CIRCLE)&&(y<=CIRCLE))||
              ((y>=ZERO)&&(y<=EIGHTH_CIRCLE)))){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_10
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 10.
    //	(135<x<225) and (45<y<135)
    //
    //***********************************************************************
    boolean if_quadrant_10(int x,int y){
        if((x>=THREE_EIGHTHS_CIRCLE)&&(x<=FIVE_EIGHTHS_CIRCLE)&&(y>=EIGHTH_CIRCLE)&&(y<=THREE_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_11
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant11.
    //	(135<x<225) and (135<y<225)
    //
    //***********************************************************************
    boolean if_quadrant_11(int x,int y){
        if((x>=THREE_EIGHTHS_CIRCLE)&&(x<=FIVE_EIGHTHS_CIRCLE)&&(y>=THREE_EIGHTHS_CIRCLE)&&(y<=FIVE_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_12
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 9.
    //	(135<x<225) and (225<y<315)
    //
    //***********************************************************************
    boolean if_quadrant_12(int x,int y){
        if((x>=THREE_EIGHTHS_CIRCLE)&&(x<=FIVE_EIGHTHS_CIRCLE)&&(y>=FIVE_EIGHTHS_CIRCLE)&&(y<=SEVEN_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_13
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 13.
    //	(225<x<315) and (315<y<45)
    //
    //***********************************************************************
    boolean if_quadrant_13(int x,int y){
        if((x>=FIVE_EIGHTHS_CIRCLE)&&(x<=SEVEN_EIGHTHS_CIRCLE)&&(((y>=SEVEN_EIGHTHS_CIRCLE)&&(y<=CIRCLE))||
              ((y>=ZERO)&&(y<=EIGHTH_CIRCLE)))){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_14
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 14.
    //	(225<x<315) and (45<y<135)
    //
    //***********************************************************************
    boolean if_quadrant_14(int x,int y){
        if((x>=FIVE_EIGHTHS_CIRCLE)&&(x<=SEVEN_EIGHTHS_CIRCLE)&&(y>=EIGHTH_CIRCLE)&&(y<=THREE_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_15
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 15.
    //	(225<x<315) and (135<y<225)
    //
    //***********************************************************************
    boolean if_quadrant_15(int x,int y){
        if((x>=FIVE_EIGHTHS_CIRCLE)&&(x<=SEVEN_EIGHTHS_CIRCLE)&&(y>=THREE_EIGHTHS_CIRCLE)&&(y<=FIVE_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }

    //***********************************************************************
    //
    // Function: if_quadrant_16
    //
    // Purpose: decide if the view of the Rubik's Cube is in quadrant 16.
    //	(225<x<315) and (225<y<315)
    //
    //***********************************************************************
    boolean if_quadrant_16(int x,int y){
        if((x>=FIVE_EIGHTHS_CIRCLE)&&(x<=SEVEN_EIGHTHS_CIRCLE)&&(y>=FIVE_EIGHTHS_CIRCLE)&&(y<=SEVEN_EIGHTHS_CIRCLE)){
            return true;
        }else{
            return false;
        }
    }
    
    private void draw_rubiks_cube(int mode){
        glPushMatrix();
        glRotatef(rc.get_x_rotation(), 1.0f, 0.0f, 0.0f);
        glRotatef(rc.get_y_rotation(), 0.0f, 1.0f, 0.0f);
        rc.draw(mode);
        glPopMatrix();
    }
    
    private void make_move(){ //OK
        if(choice==MOVE_TMR){
            rc.top_move_right();
        }else if(choice==MOVE_MTMR){
            rc.MT_move_right();
        }else if(choice==MOVE_CMR){
            rc.middle_move_right();
        }else if(choice==MOVE_MBMR){
            rc.MD_move_right();
        }else if(choice==MOVE_BMR){
            rc.bottom_move_right();
        }else if(choice==MOVE_LMU){
            rc.left_move_up();
        }else if(choice==MOVE_MLMU){
            rc.ML_move_up();
        }else if(choice==MOVE_CMU){
            rc.middle_move_up();
        }else if(choice==MOVE_MRMU){
            rc.MR_move_up();
        }else if(choice==MOVE_RMU){
            rc.right_move_up();
        }else if(choice==MOVE_TML){
            rc.top_move_left();
        }else if(choice==MOVE_MTML){
            rc.MT_move_left();
        }else if(choice==MOVE_CML){
            rc.middle_move_left();
        }else if(choice==MOVE_MBML){
            rc.MD_move_left();
        }else if(choice==MOVE_BML){
            rc.bottom_move_left();
        }else if(choice==MOVE_LMD){
            rc.left_move_down();
        }else if(choice==MOVE_MLMD){
            rc.ML_move_down();
        }else if(choice==MOVE_CMD){
            rc.middle_move_down();
        }else if(choice==MOVE_MRMD){
            rc.MR_move_down();
        }else if(choice==MOVE_RMD){
            rc.right_move_down();
        }else if(choice==MOVE_FCCW){
            rc.front_face_ccw();
        }else if(choice==MOVE_MFCCW){
            rc.MF_face_ccw();
        }else if(choice==MOVE_CCCW){
            rc.middle_face_ccw();
        }else if(choice==MOVE_MBCCW){
            rc.MB_face_ccw();
        }else if(choice==MOVE_BCCW){
            rc.back_face_ccw();
        }else if(choice==MOVE_FCW){
            rc.front_face_cw();
        }else if(choice==MOVE_MFCW){
            rc.MF_face_cw();
        }else if(choice==MOVE_CCW){
            rc.middle_face_cw();
        }else if(choice==MOVE_MBCW){
            rc.MB_face_cw();
        }else if(choice==MOVE_BCW){
            rc.back_face_cw();
        }
    }
    
    private void myKeyboardFunction(){
        while(Keyboard.next()){
            if(Keyboard.getEventKey() == Keyboard.KEY_U){
                unzoom_camera();
            }else if(Keyboard.getEventKey()==Keyboard.KEY_Z){
                zoom_camera();
            }else if(Keyboard.getEventKey()==Keyboard.KEY_O){
                over_ride_play=!over_ride_play;
            }else if(Keyboard.getEventKey()==Keyboard.KEY_N){
                rc.scramble_cube();
            }else if(Keyboard.getEventKey()==Keyboard.KEY_L){
                load_file();
            }else if(Keyboard.getEventKey()==Keyboard.KEY_S){
                save_file();
            }else if(Keyboard.getEventKey()==Keyboard.KEY_T){
                if(glIsEnabled(GL_LIGHTING)){
                    glDisable(GL_LIGHTING);
                }else{
                    glEnable(GL_LIGHTING);
                }
            }else if(Keyboard.getEventKey()==Keyboard.KEY_M){
                if(shine==NON_METALIC){
                    shine=METALIC;
                }else{
                    shine=NON_METALIC;
                }
            }else if(Keyboard.getEventKey()==Keyboard.KEY_RSHIFT){
                rc.increase_speed();
                nearest=NOTHING;
            }else if(Keyboard.getEventKey()==Keyboard.KEY_LSHIFT){
                rc.decrease_speed();
                nearest=NOTHING;
            }else if(Keyboard.getEventKey()==Keyboard.KEY_ESCAPE){
                Display.destroy();
                System.exit(0);
            }else if(!rc.get_in_progress()){
                if(if_quadrant_1(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_1();
                }else if(if_quadrant_2(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_2();
                }else if(if_quadrant_3(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_3();
                }else if(if_quadrant_4(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_4();
                }else if(if_quadrant_5(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_5();
                }else if(if_quadrant_6(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_6();
                }else if(if_quadrant_7(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_7();
                }else if(if_quadrant_8(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_8();
                }else if(if_quadrant_9(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_9();
                }else if(if_quadrant_10(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_10();
                }else if(if_quadrant_11(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_11();
                }else if(if_quadrant_12(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_12();
                }else if(if_quadrant_13(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_13();
                }else if(if_quadrant_14(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_14();
                }else if(if_quadrant_15(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_15();
                }else if(if_quadrant_16(rc.get_x_rotation(),rc.get_y_rotation())){
                    quadrant_16();
                }
            }
        }
    }
    
    //***********************************************************************
    //
    // Function: processHits - code from mrl.nyu.edu/~elif/CG/lec9_4pp.pdf
    //
    // Purpose: Allows the user to pick the closest object above the mouse
    //
    // Paramaters: GLint hits - the number of different objects the user has clicked
    //    on at a certain moment in time. 
    //             GLuint buffer[] - the identification data that distinguishes which 
    //    object is being processed
    //
    //***********************************************************************
    void processHits (int hits, IntBuffer buffer){
        if(hits!=0){
            nearest = buffer.get(2);
            int index=3;
            for(int i=1;i<hits;i++){
                if(nearest > buffer.get(4*i+2)){
                    nearest = buffer.get(4*i+2);
                    index = 4*i+3;
                }
            }
            nearest=buffer.get(index);
        }
    }
    
    private void myMouseFunction(){
        if(Mouse.isButtonDown(0)){
            IntBuffer selectBuf = BufferUtils.createIntBuffer(SIZE);
            IntBuffer viewport = BufferUtils.createIntBuffer(VIEWPORT_SIZE);
            glViewport(0,0,win_w,win_h);
            glGetInteger(GL_VIEWPORT,viewport);
            // Must do the following BEFORE glInitNames()!
            glSelectBuffer(selectBuf);
            glRenderMode(GL_SELECT);

            glMatrixMode (GL_PROJECTION);
            glPushMatrix ();
            glLoadIdentity ();
            gluPickMatrix(Mouse.getX(),Mouse.getY(),MOUSEBOX,MOUSEBOX,viewport); 
            
            gluPerspective( fovy, ratio, VIEW_NEAR, VIEW_FAR );
            
            glInitNames();
            glPushName(ZERO);// Dummy first name on stack

            glMatrixMode(GL_MODELVIEW);
            glPushMatrix();
            
            draw_rubiks_cube(GL_SELECT);

            glPopMatrix();

            glMatrixMode (GL_PROJECTION);
            glPopMatrix ();
            glMatrixMode (GL_MODELVIEW);

            int hits = glRenderMode (GL_RENDER);
            processHits (hits, selectBuf);
            
            rc.mutate_x_rotation((rc.get_x_rotation()-Mouse.getDY()));
            //mouse_y = Mouse.getDY();					// refresh our x value

            rc.mutate_y_rotation((rc.get_y_rotation()+Mouse.getDX()));
            //mouse_x = Mouse.getDX();					// refresh our y value

            // Check x,y rotation values to keep 0-355
            if (rc.get_x_rotation() > CIRCLE)
                rc.mutate_x_rotation(rc.get_x_rotation()-CIRCLE);

            if (rc.get_x_rotation() < ZERO)
                rc.mutate_x_rotation(rc.get_x_rotation()+CIRCLE);

            if (rc.get_y_rotation() > CIRCLE)
                rc.mutate_y_rotation(rc.get_y_rotation()-CIRCLE);

            if (rc.get_y_rotation() < ZERO)
                rc.mutate_y_rotation(rc.get_y_rotation()+CIRCLE);
        }
    }

    
    private void myDisplay(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Reset coordinate system
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        // Set the viewing perspective
        gluPerspective(fovy, ratio, VIEW_NEAR, VIEW_FAR);

        // Set viewing translation
        glMatrixMode(GL_MODELVIEW);

        //glMateriali(GL_FRONT, GL_SHININESS, shine);

        if(rc.get_in_progress()){
            if(rc.get_direction()==ONE){
                rc.increase_move_rotation();
                if(rc.get_move_rotation()>=FOURTH_CIRCLE){
                    rc.reset_move_rotation();
                    rc.mutate_in_progress(false);
                    make_move();
                    for(int i=ZERO;i<POSSIBLE_MOVES;i++){
                         cubes[i].reset_axese();
                    }
                    play_sound=true;
                }
            }else if(rc.get_direction()==ZERO){
                rc.decrease_move_rotation();
                if(rc.get_move_rotation()<=-FOURTH_CIRCLE){
                    rc.reset_move_rotation();
                    rc.mutate_in_progress(false);
                    make_move();
                    for(int i=ZERO;i<POSSIBLE_MOVES;i++){
                        cubes[i].reset_axese();
                    }
                    play_sound=true;
                }
            }
            for(int i=ZERO;i<POSSIBLE_MOVES;i++){
                cubes[i].mutate_rotation((int) rc.get_move_rotation());
            }
        }
        
        myKeyboardFunction();
        myMouseFunction();
        
        glPushMatrix(); //addition for the java version
        draw_rubiks_cube(GL_RENDER);
        glPopMatrix(); //addition for the java version

        if(rc.check_win()){
            win=true;
        }

        //glutSwapBuffers();
        if(play_sound==true){
            if(over_ride_play==false){
            //system("aplay BUILD1.wav");
            //system("aplay arrow_hit.wav");
            //system("aplay ARROW.wav");
            //system("aplay AXE.wav");
            }
            play_sound=false;
        }
        if(win==true){
            //system("aplay SCHOOL.wav");
            rc.scramble_cube();
            win=false;
        }
    }
    
    public void myInit(){
        // Set background to black
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        // Enable depth testing
        glEnable(GL_DEPTH_TEST);
        glMatrixMode(GL_MODELVIEW);
        rc.scramble_cube(); 
        rc.mutate_x_rotation(20); //initial display orientation of cube
        rc.mutate_y_rotation(20); //initial display orientation of cube
         
        //load_file();

        // Enable lighting
        /*glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);

        // Enable expensive lighting computations with actual position of
        // light source. 
        glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
        
        new Variables(); //set up ambientLight,diffuseLight,specularLight,lightPos,materialSpecular, and materialEmission
        // Set up and enable light zero
        glLight(GL_LIGHT0, GL_AMBIENT, ambientLight);
        glLight(GL_LIGHT0, GL_DIFFUSE, diffuseLight);
        glLight(GL_LIGHT0, GL_SPECULAR, specularLight);

        // Enable color tracking
        glEnable(GL_COLOR_MATERIAL);
        glLight(GL_LIGHT0, GL_POSITION, lightPos);

        // Set material to folow glColor values
        glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);

        // Set specular reflectivity and shine
        glMaterial(GL_FRONT, GL_SPECULAR, materialSpecular);
        glMaterial(GL_FRONT, GL_EMISSION, materialEmission);*/

        glTranslatef( DISPLACEMENT_X, DISPLACEMENT_Y, -DISPLACEMENT_Z );
    }
    
    public void myCreateDisplay(){
        try{
            Display.setDisplayMode(new DisplayMode(win_w,win_h));
            Display.setTitle("Professor's Cube");
            Display.create();
        }catch(LWJGLException e){
            //do nothing
        }
    }
    
    public void myDisplayLoop(){
        while(!Display.isCloseRequested()){
            myDisplay();
            Display.update();
            Display.sync(FPS);
        }
        Display.destroy();
    }
}
