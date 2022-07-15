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
    public static final float DISPLACEMENT_X = 0.0f; //x value to place camera and ship away from origin
    public static final float DISPLACEMENT_Y = 0.5f; //y value to place camera and ship away from origin
    public static final float DISPLACEMENT_Z = 15.0f; //z value to place camera and ship away from origin
    public static final float VIEW_NEAR = 1.0f; //1.0f //near side clipping plane distance for perspective view
    public static final float VIEW_FAR = 40.0f; //far side clipping plane distance for perspective view
    public static final float FOVYINCREMENT = 5f; //increment to increase/decrease field of view in gluPerspective
    public static final float MAX_FOVY = 100f; //max field of view in gluPerspective
    public static final float MIN_FOVY = 55f; //min field of view in gluPerspective
    public static final float INIT_FOVY = 65f; //initial field of view for gluPerspective
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
    public static final int POSSIBLE_MOVES = 9; //# of cubes to manipulate during a move animation/move
    
    //public static final (by interface default)
    public static final float PLACEMENT = 2.1f; //placement of cubes away from the origin
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
    public static final int SCRAMBLE = 50; //number of moves to scramble the cube
    public static final int MAX_SPEED = 15; //max speed cube animations will appear
    public static final int MIN_SPEED = 5; //min speed cube animations will appear
    public static final int TOTAL_MOVES = 18; //total amount of possible moves used to manipulate the cube
    public static final int ID_TLF = 1;
    public static final int ID_TMF = 2;
    public static final int ID_TRF = 3;
    public static final int ID_TLM = 4;
    public static final int ID_TMM = 5;
    public static final int ID_TRM = 6;
    public static final int ID_TLB = 7;
    public static final int ID_TMB = 8;
    public static final int ID_TRB = 9;
    public static final int ID_MLF = 10;
    public static final int ID_MMF = 11;
    public static final int ID_MRF = 12;
    public static final int ID_MLM = 13;
    public static final int ID_MMM = 14;
    public static final int ID_MRM = 15;
    public static final int ID_MLB = 16;
    public static final int ID_MMB = 17;
    public static final int ID_MRB = 18;
    public static final int ID_DLF = 19;
    public static final int ID_DMF = 20;
    public static final int ID_DRF = 21;
    public static final int ID_DLM = 22;
    public static final int ID_DMM = 23;
    public static final int ID_DRM = 24;
    public static final int ID_DLB = 25;
    public static final int ID_DMB = 26;
    public static final int ID_DRB = 27;
    public static final int NOTHING = 28;
    public static final int MOVE_TMR = 0;
    public static final int MOVE_MHMR = 1;
    public static final int MOVE_BMR = 2;
    public static final int MOVE_LMU = 3;
    public static final int MOVE_MVMU = 4;
    public static final int MOVE_RMU = 5;
    public static final int MOVE_TML = 6;
    public static final int MOVE_MHML = 7;
    public static final int MOVE_BML = 8;
    public static final int MOVE_LMD = 9;
    public static final int MOVE_MVMD = 10;
    public static final int MOVE_RMD = 11;
    public static final int MOVE_FCCW = 12;
    public static final int MOVE_MCCW = 13;
    public static final int MOVE_BCCW = 14;
    public static final int MOVE_FCW = 15;
    public static final int MOVE_MCW = 16; 
    public static final int MOVE_BCW = 17;
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
    
    public static int source;
    public static int buffer;
    
            
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
