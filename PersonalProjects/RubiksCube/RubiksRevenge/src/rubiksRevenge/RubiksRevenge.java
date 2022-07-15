package rubiksRevenge;

import utils.Utils;

/**
 *
 * @author Obringer
 */
public class RubiksRevenge {
    
    static Utils myUtils = new Utils();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       myUtils.myCreateDisplay();
       myUtils.myInit();
       myUtils.myDisplayLoop();
    }
}
