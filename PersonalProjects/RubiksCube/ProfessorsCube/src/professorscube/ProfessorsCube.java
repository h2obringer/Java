/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package professorscube;

import utils.Utils;
/**
 *
 * @author Obringer
 */
public class ProfessorsCube {

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
