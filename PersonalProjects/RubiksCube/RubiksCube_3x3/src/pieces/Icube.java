package pieces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Obringer
 */
public interface Icube {
    
    void face(int a, int b, int c , int d, int color); //build the face of a cube
    void build_cube(); //build the current cube
    void draw(int mode,int NAME); //draw the cube //MAY HAVE ISSUES mode should be of type GLenum
    float get_x(); //get position on cube
    float get_y(); //get position on cube
    float get_z(); //get position on cube
    int get_back(); //get color of back face
    int get_front(); //get color of front face
    int get_left(); //get color of left face
    int get_right(); //get color of right face
    int get_top(); //get color of top face
    int get_bottom(); //get color of bottom face
    int get_rotation(); //get the rotation for animation
    int get_x_axis(); //get x axis for animation
    int get_y_axis(); //get y axis for animation
    int get_z_axis(); //get z axis for animation
    void mutate_rotation(int new_rotation); //change rotation
    void mutate_axese(int new_x_axis,int new_y_axis,int new_z_axis); //change all axese
    void reset_axese(); //reset all axese to zero    
    void write_cube(BufferedWriter bw) throws IOException; //write cube data to file
    void load_cube(BufferedReader input) throws IOException; //load cube data from file
    
    /*moves to manipulate orientation of cube*/
    void top_move_right(); 
    void top_move_left();
    void middle_horizontal_move_right();
    void middle_horizontal_move_left();
    void bottom_move_right();
    void bottom_move_left();
    void left_move_down();
    void left_move_up();
    void middle_vertical_move_down();
    void middle_vertical_move_up();
    void right_move_down();
    void right_move_up();
    void front_face_cw();
    void front_face_ccw();
    void middle_face_cw();
    void middle_face_ccw();
    void back_face_cw();
    void back_face_ccw();
}
