package utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

/**
 *
 * @author Obringer
 */
public class Variables {
    public static final int SIZE = 512; //size for selection buffer
    public static final int ONESECOND = 1000; //used with fps (frames per second) to get amount of redraws per second
    public static final float DISPLACEMENT_X = 0.0f; //x value to place camera and cube away from origin
    public static final float DISPLACEMENT_Y = 0.5f; //y value to place camera and cube away from origin
    public static final float DISPLACEMENT_Z = 15.0f; //z value to place camera and cube away from origin
    public static final float VIEW_NEAR = 1.0f; //1.0f //near side clipping plane distance for perspective view
    public static final float VIEW_FAR = 40.0f; //far side clipping plane distance for perspective view
    public static final float FOVYINCREMENT = 5f; //increment to increase/decrease field of view in gluPerspective
    public static final float MAX_FOVY = 110f; //max field of view in gluPerspective
    public static final float MIN_FOVY = 65f; //min field of view in gluPerspective
    public static final float INIT_FOVY = 75f; //initial field of view for gluPerspective
    public static final int NON_METALIC = 20; //inital shine value of objects in program to make it not shiny
    public static final int METALIC = 120; //shine value to give metalic look to objects in program.
    public static final int FPS = 15; //frames per second
    public static final int WINDOW_SIZE = 500; //initial window size x and y
    public static final int INIT_WINDOW_POSITION = 100; //initial window position x and y
    public static final int CIRCLE = 360; //degrees in a circle
    public static final int FOURTH_CIRCLE = 90; //degrees in a fourth of a circle
    public static final int EIGHTH_CIRCLE = 45; //degrees in an eighth of a circle
    public static final int THREE_EIGHTHS_CIRCLE = 135; //degrees in three eighths of a circle
    public static final int FIVE_EIGHTHS_CIRCLE = 225; //degrees in five eighths of a circle
    public static final int SEVEN_EIGHTHS_CIRCLE = 315; //degrees in seven eighths of a circle
    public static final int VIEWPORT_SIZE = 16; //size of viewport buffer //was 4
    public static final float MOUSEBOX = 1f;//0.005f; //very small square around mouse for picking.
    public static final int POSSIBLE_MOVES = 25; //# of cubes to manipulate during a move animation/move
    
    public static final float NEAR_PLACEMENT = 2.1f; //placement of cubes away from the origin
    public static final float FAR_PLACEMENT = 4.2f;
    public static final int ZERO = 0; //zero
    public static final int ONE = 1; //one
    public static final int RED = 2; //color reference to red in array
    public static final int WHITE = 1; //color reference to white in array
    public static final int BLUE = 4; //color reference to blue in array
    public static final int YELLOW = 5; //color reference to yellow in array
    public static final int GREEN = 3; //color reference to green in array
    public static final int ORANGE = 6; //color reference to orange in array
    public static final int BLACK = 0; //color reference to black in array
    public static final int X_POS = 0;
    public static final int Y_POS = 1;
    public static final int Z_POS = 2;
    public static final int SCRAMBLE = 100; //number of moves to scramble the cube
    public static final int MAX_SPEED = 15; //max speed cube animations will appear
    public static final int MIN_SPEED = 5; //min speed cube animations will appear
    public static final int TOTAL_MOVES = 18; //total amount of possible moves used to manipulate the cube
    public static final int ID_TLF = 1; //top left front
    public static final int ID_TMLF = 2; //top middle-left front
    public static final int ID_TMRF = 3; //top middle-right front
    public static final int ID_TRF = 4; //top right front
    public static final int ID_TLMF = 5; //top left middle-front
    public static final int ID_TMLMF = 6; //top middle-left middle-front
    public static final int ID_TMRMF = 7; //top middle-right middle-front
    public static final int ID_TRMF = 8; //top right middle-front
    public static final int ID_TLMB = 9; //top left middle-back
    public static final int ID_TMLMB = 10; //top middle-left middle-back
    public static final int ID_TMRMB = 11; //top middle-right middle-back
    public static final int ID_TRMB = 12; //top right middle-back
    public static final int ID_TLB = 13; //top left back
    public static final int ID_TMLB = 14; //top middle-left back
    public static final int ID_TMRB = 15; //top middle-right back
    public static final int ID_TRB = 16; //top right back
    public static final int ID_MTLF = 17; //middle-top left front
    public static final int ID_MTMLF = 18; //middle-top middle-left front
    public static final int ID_MTMRF = 19; //middle-top middle-right front
    public static final int ID_MTRF = 20; //middle-top right front
    public static final int ID_MTLMF = 21; //middle-top left middle-front
    public static final int ID_MTMLMF = 22; //middle-top middle-left middle-front
    public static final int ID_MTMRMF = 23; //middle-top middle-right middle-front
    public static final int ID_MTRMF = 24; //middle-top right middle-front
    public static final int ID_MTLMB = 25; //middle-top left middle-back
    public static final int ID_MTMLMB = 26; //middle-top middle-left middle-back
    public static final int ID_MTMRMB = 27; //middle-top middle-right middle-back
    public static final int ID_MTRMB = 28; //middle-top right middle-back
    public static final int ID_MTLB = 29; //middle-top left back
    public static final int ID_MTMLB = 30; //middle-top middle-left back
    public static final int ID_MTMRB = 31; //middle-top middle-right back
    public static final int ID_MTRB = 32; //middle-top right back
    public static final int ID_MDLF = 33; //middle-top left front
    public static final int ID_MDMLF = 34; //middle-top middle-left front
    public static final int ID_MDMRF = 35; //middle-top middle-right front
    public static final int ID_MDRF = 36; //middle-top right front
    public static final int ID_MDLMF = 37; //middle-top left middle-front
    public static final int ID_MDMLMF = 38; //middle-top middle-left middle-front
    public static final int ID_MDMRMF = 39; //middle-top middle-right middle-front
    public static final int ID_MDRMF = 40; //middle-top right middle-front
    public static final int ID_MDLMB = 41; //middle-top left middle-back
    public static final int ID_MDMLMB = 42; //middle-top middle-left middle-back
    public static final int ID_MDMRMB = 43; //middle-top middle-right middle-back
    public static final int ID_MDRMB = 44; //middle-top right middle-back
    public static final int ID_MDLB = 45; //middle-top left back
    public static final int ID_MDMLB = 46; //middle-top middle-left back
    public static final int ID_MDMRB = 47; //middle-top middle-right back
    public static final int ID_MDRB = 48; //middle-top right back
    public static final int ID_DLF = 49; //middle-top left front
    public static final int ID_DMLF = 50; //middle-top middle-left front
    public static final int ID_DMRF = 51; //middle-top middle-right front
    public static final int ID_DRF = 52; //middle-top right front
    public static final int ID_DLMF = 53; //middle-top left middle-front
    public static final int ID_DMLMF = 54; //middle-top middle-left middle-front
    public static final int ID_DMRMF = 55; //middle-top middle-right middle-front
    public static final int ID_DRMF = 56; //middle-top right middle-front
    public static final int ID_DLMB = 57; //middle-top left middle-back
    public static final int ID_DMLMB = 58; //middle-top middle-left middle-back
    public static final int ID_DMRMB = 59; //middle-top middle-right middle-back
    public static final int ID_DRMB = 60; //middle-top right middle-back
    public static final int ID_DLB = 61; //middle-top left back
    public static final int ID_DMLB = 62; //middle-top middle-left back
    public static final int ID_DMRB = 63; //middle-top middle-right back
    public static final int ID_DRB = 64; //middle-top right back
    public static final int ID_TCF = 65;
    public static final int ID_MTCF = 66;
    public static final int ID_CCF = 67;
    public static final int ID_MDCF = 68;
    public static final int ID_DCF = 69;
    public static final int ID_CLF = 70;
    public static final int ID_CMLF = 71;
    public static final int ID_CMRF = 72;
    public static final int ID_CRF = 73;
    public static final int ID_TCB = 74;
    public static final int ID_MTCB = 75;
    public static final int ID_CCB = 76;
    public static final int ID_MDCB = 77;
    public static final int ID_DCB = 78;
    public static final int ID_CLB = 79;
    public static final int ID_CMLB = 80;
    public static final int ID_CMRB = 81;
    public static final int ID_CRB = 82;
    public static final int ID_TLC = 83;
    public static final int ID_MTLC = 84;
    public static final int ID_CLC = 85;
    public static final int ID_MDLC = 86;
    public static final int ID_DLC = 87;
    public static final int ID_TRC = 88;
    public static final int ID_MTRC = 89;
    public static final int ID_CRC = 90;
    public static final int ID_MDRC = 91;
    public static final int ID_DRC = 92;
    public static final int ID_CLMF = 93;
    public static final int ID_CLMB = 94;
    public static final int ID_CRMF = 95;
    public static final int ID_CRMB = 96;
    public static final int ID_TCMF = 97;
    public static final int ID_TCC = 98;
    public static final int ID_TCMB = 99;
    public static final int ID_TMLC = 100;
    public static final int ID_TMRC = 101;
    public static final int ID_DCMF = 102;
    public static final int ID_DCC = 103;
    public static final int ID_DCMB = 104;
    public static final int ID_DMLC = 105;
    public static final int ID_DMRC = 106;
    public static final int NOTHING = 107;
    
    public static final int MOVE_TMR = 0; //top move right
    public static final int MOVE_MTMR = 1; //middle-top move right
    public static final int MOVE_CMR = 2; //center move right
    public static final int MOVE_MBMR = 3; //middle-bottom move right
    public static final int MOVE_BMR = 4; //bottom move right
    public static final int MOVE_LMU = 5; //left move up
    public static final int MOVE_MLMU = 6; //middle-left vertical move up
    public static final int MOVE_CMU = 7; //center move up
    public static final int MOVE_MRMU = 8; //middle-right vertical move up
    public static final int MOVE_RMU = 9; //right move up
    public static final int MOVE_TML = 10; //top move right
    public static final int MOVE_MTML = 11; //middle-top move left
    public static final int MOVE_CML = 12; //center move left
    public static final int MOVE_MBML = 13; //middle-bottom move left
    public static final int MOVE_BML = 14; //bottom move left
    public static final int MOVE_LMD = 15; //left move down
    public static final int MOVE_MLMD = 16; //middle-left vertical move down
    public static final int MOVE_CMD = 17; //center move down
    public static final int MOVE_MRMD= 18; //middle-right vertical move down
    public static final int MOVE_RMD = 19; //right move down
    public static final int MOVE_FCCW = 20; //front counter-clockwise
    public static final int MOVE_MFCCW = 21; //middle-front counter-clockwise
    public static final int MOVE_CCCW = 22; //center counter_clockwise
    public static final int MOVE_MBCCW = 23; //middle-back counter-clockwise
    public static final int MOVE_BCCW = 24; //back counter-clockwise
    public static final int MOVE_FCW = 25; //front clockwise
    public static final int MOVE_MFCW = 26; //middle-front clockwise
    public static final int MOVE_CCW =27; //center clockwise
    public static final int MOVE_MBCW = 28; //middle-back clockwise
    public static final int MOVE_BCW = 29; //back clockwise
    public static final float[][] my_vertices = {{-1.0f,-1.0f,-1.0f},{1.0f,-1.0f,-1.0f},
			 {1.0f,1.0f,-1.0f}, {-1.0f,1.0f,-1.0f}, {-1.0f,-1.0f,1.0f}, 
			 {1.0f,-1.0f,1.0f}, {1.0f,1.0f,1.0f}, {-1.0f,1.0f,1.0f}};

    public static final float[][] my_normals = {{-1.0f,-1.0f,-1.0f},{1.0f,-1.0f,-1.0f},
			{1.0f,1.0f,-1.0f}, {-1.0f,1.0f,-1.0f}, {-1.0f,-1.0f,1.0f}, 
			{1.0f,-1.0f,1.0f}, {1.0f,1.0f,1.0f}, {-1.0f,1.0f,1.0f}};

    public static final float[][] my_colors = {{0.0f,0.0f,0.0f}/*black*/,{1.0f,1.0f,1.0f}/*white*/,
		       {1.0f,0.0f,0.0f}/*red*/, {0.0f,1.0f,0.0f}/*green*/, {0.0f,0.0f,1.0f}/*blue*/, 
		       {1.0f,1.0f,0.0f}/*yellow*/, {1.0f,0.5f,0.0f}/*orange*/};
    
    private static ByteBuffer temp = ByteBuffer.allocateDirect( 4 * 4 * 3);
    public static final float AMBIENTLIGHTSRC[] = { 1.0f, 1.0f, 1.0f, 1.0f }; //values for ambient light
    public static final float DIFFUSELIGHTSRC[] = { 0.1f, 0.1f, 0.1f, 1.0f }; //values for diffuse light
    public static final float SPECULARLIGHTSRC[] = { 0.1f, 0.1f, 0.1f, 1.0f }; //values for specular light
    public static final float MATERIALSPECULARSRC[] = {0.1f,0.1f,0.1f,1.0f}; //values for object specularity
    public static final float MATERIALEMISSIONSRC[] = {0.05f, 0.05f, 0.05f, 1.0f}; //values for object emissions
    public static final float LIGHTPOSSRC[] = {0.0f,0.0f,0.0f,0.0f}; //values for the position 
    
    public static FloatBuffer ambientLight;// = temp.asFloatBuffer();
    public static FloatBuffer diffuseLight;// = FloatBuffer.wrap(diffuseLightSrc);
    public static FloatBuffer specularLight;// = FloatBuffer.wrap(specularLightSrc);
    public static FloatBuffer materialSpecular;// = FloatBuffer.wrap(materialSpecularSrc);
    public static FloatBuffer materialEmission;// = FloatBuffer.wrap(materialEmissionSrc);
    public static FloatBuffer lightPos;// = FloatBuffer.wrap(lightPosSrc);
    
            
    public Variables(){
        temp.order(ByteOrder.nativeOrder());
        ambientLight = temp.asFloatBuffer();
        ambientLight.put(AMBIENTLIGHTSRC);
        ambientLight.position(ZERO);
        diffuseLight = temp.asFloatBuffer();
        diffuseLight.put(DIFFUSELIGHTSRC);
        diffuseLight.position(ZERO);
        specularLight = temp.asFloatBuffer();
        specularLight.put(SPECULARLIGHTSRC);
        specularLight.position(ZERO);
        materialSpecular = temp.asFloatBuffer();
        materialSpecular.put(MATERIALSPECULARSRC);
        materialSpecular.position(ZERO);
        materialEmission = temp.asFloatBuffer();
        materialEmission.put(MATERIALEMISSIONSRC);
        materialEmission.position(ZERO);
        lightPos = temp.asFloatBuffer();
        lightPos.put(LIGHTPOSSRC);
        lightPos.position(ZERO);
    }
}

//PROBLEMS WITH ML AND MR MOVE UP