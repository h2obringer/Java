
package graphics;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Randal Obringer
 */
public class GolfMain extends JFrame{
    public static void main (String[] args){
        JFrame frame = new JFrame ("Graphics Panel Example"); //create the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allow us to close the window when clicking on the "X" 
        GolfGUI panel = new GolfGUI(); //create and instantiate the panel with our graphics
        frame.getContentPane().add(panel); //add it to the frame
        frame.pack(); //needed for some reason
        frame.setVisible(true); //make it visible
        //gridBagLayout https://www.youtube.com/watch?v=FB_wJpIdA8k for better layout of elements in our window.
    }
}
