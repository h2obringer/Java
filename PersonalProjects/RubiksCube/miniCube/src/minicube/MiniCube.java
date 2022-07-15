/**
 * @author Randal Obringer
 * Description: The main class to run the program. Creates and runs the display
 *      loop.
 * Date Last Updated: 3 March 2014
 */

package minicube;

import utils.Utils;

public class MiniCube {

    static Utils myUtils = new Utils();
    
    public static void main(String[] args) {
       myUtils.myCreateDisplay();
       myUtils.myInit();
       myUtils.myDisplayLoop();
    }
}
