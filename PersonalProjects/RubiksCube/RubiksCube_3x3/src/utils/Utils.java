package utils;

import cube.Rubiks_Cube;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
    int fps=FPS; //frames per second
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
    void choose_move_TMR(){
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
    void choose_move_MHMR(){
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_MH_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_MHMR;
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
    void choose_move_BMR(){
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
    void choose_move_LMU(){
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
    void choose_move_MVMU(){
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_MV_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_MVMU;
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
    void choose_move_RMU(){
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
    void choose_move_TML(){
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
    void choose_move_MHML(){
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_MH_left_right(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ONE,ZERO);
        }
        choice=MOVE_MHML;
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
    void choose_move_BML(){
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
    void choose_move_LMD(){
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
    void choose_move_MVMD(){
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_MV_up_down(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ONE,ZERO,ZERO);
        }
        choice=MOVE_MVMD;
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
    void choose_move_RMD(){
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
    void choose_move_FCCW(){
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
    void choose_move_MCCW(){
        rc.mutate_in_progress(true);
        rc.mutate_direction(ONE);
        rc.choose_cubes_MF_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_MCCW;
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
    void choose_move_BCCW(){
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
    void choose_move_FCW(){
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
    void choose_move_MCW(){
        rc.mutate_in_progress(true);
        rc.mutate_direction(ZERO);
        rc.choose_cubes_MF_cw_ccw(cubes);
        for(int i=ZERO;i<POSSIBLE_MOVES;i++){
            cubes[i].mutate_axese(ZERO,ZERO,ONE);
        }
        choice=MOVE_MCW;
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
    void choose_move_BCW(){
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
    boolean top_cubes(){
        if((nearest==ID_TLF)||(nearest==ID_TMF)||(nearest==ID_TRF)||(nearest==ID_TLM)||(nearest==ID_TMM)||
            (nearest==ID_TRM)||(nearest==ID_TLB)||(nearest==ID_TMB)||(nearest==ID_TRB)){
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
    boolean middle_horizontal_cubes(){
        if((nearest==ID_MLF)||(nearest==ID_MMF)||(nearest==ID_MRF)||(nearest==ID_MLM)||(nearest==ID_MMM)||
            (nearest==ID_MRM)||(nearest==ID_MLB)||(nearest==ID_MMB)||(nearest==ID_MRB)){
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
    boolean bottom_cubes(){
        if((nearest==ID_DLF)||(nearest==ID_DMF)||(nearest==ID_DRF)||(nearest==ID_DLM)||(nearest==ID_DMM)||
            (nearest==ID_DRM)||(nearest==ID_DLB)||(nearest==ID_DMB)||(nearest==ID_DRB)){
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
    boolean left_cubes(){
        if((nearest==ID_TLF)||(nearest==ID_MLF)||(nearest==ID_DLF)||(nearest==ID_DLM)||(nearest==ID_DLB)||
            (nearest==ID_MLB)||(nearest==ID_TLB)||(nearest==ID_TLM)||(nearest==ID_MLM)){
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
    boolean middle_vertical_cubes(){
        if((nearest==ID_TMF)||(nearest==ID_MMF)||(nearest==ID_DMF)||(nearest==ID_DMM)||(nearest==ID_DMB)||
            (nearest==ID_MMB)||(nearest==ID_TMB)||(nearest==ID_TMM)||(nearest==ID_MMM)){
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
    boolean right_cubes(){
        if((nearest==ID_TRF)||(nearest==ID_MRF)||(nearest==ID_DRF)||(nearest==ID_DRM)||(nearest==ID_DRB)||
            (nearest==ID_MRB)||(nearest==ID_TRB)||(nearest==ID_TRM)||(nearest==ID_MRM)){
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
    boolean front_cubes(){
        if((nearest==ID_TLF)||(nearest==ID_TMF)||(nearest==ID_TRF)||(nearest==ID_MRF)||(nearest==ID_MMF)||
            (nearest==ID_MLF)||(nearest==ID_DLF)||(nearest==ID_DMF)||(nearest==ID_DRF)){
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
    boolean middle_face_cubes(){
        if((nearest==ID_TLM)||(nearest==ID_TMM)||(nearest==ID_TRM)||(nearest==ID_MRM)||(nearest==ID_MMM)||
            (nearest==ID_MLM)||(nearest==ID_DLM)||(nearest==ID_DMM)||(nearest==ID_DRM)){
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
    boolean back_cubes(){
        if((nearest==ID_TLB)||(nearest==ID_TMB)||(nearest==ID_TRB)||(nearest==ID_MRB)||(nearest==ID_MMB)||
            (nearest==ID_MLB)||(nearest==ID_DLB)||(nearest==ID_DMB)||(nearest==ID_DRB)){
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
    void animate_horizontal_cubes_right(){
      if(top_cubes()){
          choose_move_TMR();
      }else if(middle_horizontal_cubes()){
          choose_move_MHMR();
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
    void animate_horizontal_cubes_left(){
      if(top_cubes()){
          choose_move_TML();
      }else if(middle_horizontal_cubes()){
          choose_move_MHML();
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
    void animate_vertical_cubes_up(){
      if(left_cubes()){
          choose_move_LMU();
      }else if(middle_vertical_cubes()){
          choose_move_MVMU();
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
    void animate_vertical_cubes_down(){
      if(left_cubes()){
          choose_move_LMD();
      }else if(middle_vertical_cubes()){
          choose_move_MVMD();
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
    void animate_faces_ccw(){
      if(front_cubes()){
          choose_move_FCCW();
      }else if(middle_face_cubes()){
          choose_move_MCCW();
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
    void animate_faces_cw(){
      if(front_cubes()){
          choose_move_FCW();
      }else if(middle_face_cubes()){
          choose_move_MCW();
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_horizontal_cubes_right();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_faces_cw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_vertical_cubes_down();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_faces_ccw();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_vertical_cubes_up();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        }else if(Keyboard.getEventKey()==Keyboard.KEY_LCONTROL){ //WAS R
            animate_horizontal_cubes_left();
        }else if(Keyboard.getEventKey()==Keyboard.KEY_RCONTROL){ //WAS L
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
        //glTranslatef(0f,0f,-10f); //addition for the java version
        glRotatef(rc.get_x_rotation(), 1.0f, 0.0f, 0.0f);
        glRotatef(rc.get_y_rotation(), 0.0f, 1.0f, 0.0f);
        rc.draw(mode);
        glPopMatrix();
    }
    
    private void make_move(){
        if(choice==MOVE_TMR){
            rc.top_move_right();
        }else if(choice==MOVE_MHMR){
            rc.middle_horizontal_move_right();
        }else if(choice==MOVE_BMR){
            rc.bottom_move_right();
        }else if(choice==MOVE_LMU){
            rc.left_move_up();
        }else if(choice==MOVE_MVMU){
            rc.middle_vertical_move_up();
        }else if(choice==MOVE_RMU){
            rc.right_move_up();
        }else if(choice==MOVE_TML){
            rc.top_move_left();
        }else if(choice==MOVE_MHML){
            rc.middle_horizontal_move_left();
        }else if(choice==MOVE_BML){
            rc.bottom_move_left();
        }else if(choice==MOVE_LMD){
            rc.left_move_down();
        }else if(choice==MOVE_MVMD){
            rc.middle_vertical_move_down();
        }else if(choice==MOVE_RMD){
            rc.right_move_down();
        }else if(choice==MOVE_FCCW){
            rc.front_face_ccw();
        }else if(choice==MOVE_MCCW){
            rc.middle_face_ccw();
        }else if(choice==MOVE_BCCW){
            rc.back_face_ccw();
        }else if(choice==MOVE_FCW){
            rc.front_face_cw();
        }else if(choice==MOVE_MCW){
            rc.middle_face_cw();
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
            //System.getProperty("aplay BUILD1.wav");
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
            Display.setTitle("Rubiks Cube");
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
