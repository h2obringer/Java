package rubikscube_3x3;

import utils.Utils;

/**
 *
 * @author Obringer
 */
public class RubiksCube_3x3 {
    
    static Utils myUtils = new Utils();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
       myUtils.myCreateDisplay();
       myUtils.myInit();
       myUtils.myDisplayLoop();
    }
}
