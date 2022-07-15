/**
 * @author Obringer
 * Description: Global variables for the program are defined here. 
 */

package utils;

public class Variables {
    public static final int SIZE = 512; //size for selection buffer used with mouse clicks.
    public static final float DISPLACEMENT_X = 0.0f; //x value to translate camera position to fit the cube on the screen
    public static final float DISPLACEMENT_Y = 0.5f; //y value to translate camera position to fit the cube on the screen
    public static final float DISPLACEMENT_Z = 15.0f; //z value to translate camera position to fit the cube on the screen
    public static final float VIEW_NEAR = 1.0f; //near side clipping plane distance for perspective view
    public static final float VIEW_FAR = 40.0f; //far side clipping plane distance for perspective view
    public static final float FOVYINCREMENT = 5f; //increment to increase/decrease field of view in gluPerspective
    public static final float MAX_FOVY = 100f; //max field of view in gluPerspective
    public static final float MIN_FOVY = 55f; //min field of view in gluPerspective
    public static final float INIT_FOVY = 65f; //initial field of view for gluPerspective
    //public static final int NON_METALIC = 20; //inital shine value of objects in program to make it not shiny
    //public static final int METALIC = 120; //shine value to give metalic look to objects in program.
    public static final int FPS = 15; //frames per second to run the program at. This will control the speed at which objects move.
    public static final int WINDOW_SIZE = 500; //initial window size x and y
    public static final int INIT_WINDOW_POSITION = 100; //initial window position x and y
    public static final int CIRCLE = 360; //degrees in a circle. Used in the rotation, viewing, and move choices of the cube.
    public static final int FOURTH_CIRCLE = 90; //degrees in a fourth of a circle. Used in the rotation, viewing, and move choices of the cube.
    public static final int EIGHTH_CIRCLE = 45; //degrees in an eighth of a circle. Used in the rotation, viewing, and move choices of the cube.
    public static final int THREE_EIGHTHS_CIRCLE = 135; //degrees in three eighths of a circle. Used in the rotation, viewing, and move choices of the cube.
    public static final int FIVE_EIGHTHS_CIRCLE = 225; //degrees in five eighths of a circle. Used in the rotation, viewing, and move choices of the cube.
    public static final int SEVEN_EIGHTHS_CIRCLE = 315; //degrees in seven eighths of a circle. Used in the rotation, viewing, and move choices of the cube.
    public static final int VIEWPORT_SIZE = 16; //size of viewport buffer. Used in selecting objects on the screen with mouse clicks.
    public static final float MOUSEBOX = 1f; //very small square around mouse for picking.
    public static final int POSSIBLE_MOVES = 4; //# of cubes that will be manipulated during a move animation/move
    public static final float PLACEMENT = 1.05f; //placement of cubes away from the origin (center of the created screen). 
    public static final int ZERO = 0; //zero
    public static final int ONE = 1; //one
    public static final int RED = 2; //color reference to red in my_colors array
    public static final int WHITE = 1; //color reference to white in my_colors array
    public static final int BLUE = 4; //color reference to blue in my_colors array
    public static final int YELLOW = 5; //color reference to yellow in my_colors array
    public static final int GREEN = 3; //color reference to green in my_colors array
    public static final int ORANGE = 6; //color reference to orange in my_colors array
    public static final int BLACK = 0; //color reference to black in my_colors array
    public static final int X_POS = 0; //x vertex position in object arrays below.
    public static final int Y_POS = 1; //y vertex position in object arrays below.
    public static final int Z_POS = 2; //z vertex position in object arrays below.
    public static final int SCRAMBLE = 50; //number of moves to scramble the cube
    public static final int MAX_SPEED = 15; //max speed cube animations will appear
    public static final int MIN_SPEED = 5; //min speed cube animations will appear
    public static final int TOTAL_MOVES = 18; //total amount of possible moves used to manipulate the cube
    public static final int ID_TLF = 1; //ID of the top left front cube. Used in object selection and manipulation.
    public static final int ID_TRF = 2; //ID of the top right front cube. Used in object selection and manipulation. 
    public static final int ID_TLB = 3; //ID of the top left back cube. Used in object selection and manipulation. 
    public static final int ID_TRB = 4; //ID of the top right back cube. Used in object selection and manipulation. 
    public static final int ID_DLF = 5; //ID of the bottom left front cube. Used in object selection and manipulation. 
    public static final int ID_DRF = 6; //ID of the bottom right front cube. Used in object selection and manipulation.
    public static final int ID_DLB = 7; //ID of the bottom left back cube. Used in object selection and manipulation. 
    public static final int ID_DRB = 8; //ID of the bottom right back cube. Used in object selection and manipulation.
    public static final int NOTHING = 9; //ID of nothing. 
    public static final int MOVE_TMR = 0; //ID of move top face move right.
    public static final int MOVE_BMR = 1; //ID of move bottom face move right.
    public static final int MOVE_LMU = 2; //ID of left face move up.
    public static final int MOVE_RMU = 3; //ID of right face move up.
    public static final int MOVE_TML = 4; //ID of top face move left. 
    public static final int MOVE_BML = 5; //ID of bottom face move left. 
    public static final int MOVE_LMD = 6; //ID of left face move down.
    public static final int MOVE_RMD = 7; //ID of right face move down.
    public static final int MOVE_FCCW = 8; //ID of front face move counter clockwise.
    public static final int MOVE_BCCW = 9; //ID of back face move counter clockwise. 
    public static final int MOVE_FCW = 10; //ID of front face move clockwise. 
    public static final int MOVE_BCW = 11; //ID of back face move clockwise. 
    
    //cube vertices
    public static final float[][] my_vertices = {{-1.0f,-1.0f,-1.0f},{1.0f,-1.0f,-1.0f},
			 {1.0f,1.0f,-1.0f}, {-1.0f,1.0f,-1.0f}, {-1.0f,-1.0f,1.0f}, 
			 {1.0f,-1.0f,1.0f}, {1.0f,1.0f,1.0f}, {-1.0f,1.0f,1.0f}};

    //cube normals used for lighting
    public static final float[][] my_normals = {{-1.0f,-1.0f,-1.0f},{1.0f,-1.0f,-1.0f},
			{1.0f,1.0f,-1.0f}, {-1.0f,1.0f,-1.0f}, {-1.0f,-1.0f,1.0f}, 
			{1.0f,-1.0f,1.0f}, {1.0f,1.0f,1.0f}, {-1.0f,1.0f,1.0f}};

    //cube colors
    public static final float[][] my_colors = {{0.0f,0.0f,0.0f}/*black*/,{1.0f,1.0f,1.0f}/*white*/,
		       {1.0f,0.0f,0.0f}/*red*/, {0.0f,1.0f,0.0f}/*green*/, {0.0f,0.0f,1.0f}/*blue*/, 
		       {1.0f,1.0f,0.0f}/*yellow*/, {1.0f,0.5f,0.0f}/*orange*/};
    
    /*private static ByteBuffer temp = ByteBuffer.allocateDirect( 4 * 4 * 3);
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
    public static FloatBuffer lightPos;// = FloatBuffer.wrap(lightPosSrc);*/
}
