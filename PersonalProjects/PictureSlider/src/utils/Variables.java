/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Obringer
 */
public class Variables {
    
    public static final int SIZE = 512; //size for selection buffer
    public static final int ONESECOND = 1000; //used with fps (frames per second) to get amount of redraws per second
    public static final float DISPLACEMENT_X = 0.0f; //x value to place camera and ship away from origin
    public static final float DISPLACEMENT_Y = 0.0f; //y value to place camera and ship away from origin
    public static final float DISPLACEMENT_Z = 5.0f; //z value to place camera and ship away from origin
    public static final float VIEW_NEAR = 1.0f; //1.0f //near side clipping plane distance for perspective view
    public static final float VIEW_FAR = 40.0f; //far side clipping plane distance for perspective view
    public static final float FOVYINCREMENT = 5f; //increment to increase/decrease field of view in gluPerspective
    public static final float MAX_FOVY = 100f; //max field of view in gluPerspective
    public static final float MIN_FOVY = 55f; //min field of view in gluPerspective
    public static final float INIT_FOVY = 65f; //initial field of view for gluPerspective
    public static final int FPS = 15; //frames per second
    public static final int WINDOW_SIZE = 500; //initial window size x and y
    public static final int INIT_WINDOW_POSITION = 100; //initial window position x and y
    public static final int TOTAL_MOVES = 4; //# of cubes to manipulate during a move animation/move
    
    //public static final (by interface default)
    public static final int ZERO = 0;
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final char BLANK = '6';
    public static final float PLACEMENT = 2.02f; //placement of cubes away from the origin
    public static final int X_POS = 0;
    public static final int Y_POS = 1;
    public static final int Z_POS = 2;
    public static final int SCRAMBLE = 100; //number of moves to scramble the cube
    public static final float MAX_SPEED = 0.202f;
    public static final float[][] my_vertices = {{-1.0f,-1.0f,0.0f}, 
			 {1.0f,-1.0f,0.0f}, {1.0f,1.0f,0.0f}, {-1.0f,1.0f,0.0f}};
    
    public static final float[][] my_normals = {{-1.0f,-1.0f,0.0f}, 
			 {1.0f,-1.0f,0.0f}, {1.0f,1.0f,0.0f}, {-1.0f,1.0f,0.0f}};
    
    //{0.65,0.85}{0,0.85}{0,0}{0.65,0}
    public static final float[][] pic_coords = {{0.65f,0.28333f, 0.43333f,0.28333f, 0.43333f,0.0f, 0.65f,0f}, //1
        {0.43333f,0.28333f, 0.21666f,0.28333f, 0.21666f,0.0f, 0.43333f,0.0f},//2
        {0.21666f,0.28333f, 0.0f,0.28333f, 0.0f,0.0f, 0.21666f,0.0f},//3
        {0.65f,0.56666f, 0.43333f,0.56666f, 0.43333f,0.28333f, 0.65f,0.28333f},//4
        {0.43333f,0.56666f, 0.21666f,0.56666f, 0.21666f,0.28333f, 0.43333f,0.28333f},//5
        {0.21666f,0.56666f, 0.0f,0.56666f, 0.0f,0.28333f, 0.21666f,0.28333f},//6
        {0.65f,0.85f, 0.43333f,0.85f, 0.43333f,0.56666f, 0.65f,0.56666f},//7
        {0.43333f,0.85f, 0.21666f,0.85f, 0.21666f,0.56666f, 0.43333f,0.56666f},//8
        {0.21666f,0.85f, 0.0f,0.85f, 0.0f,0.56666f, 0.21666f,0.56666f}}; //9
}
