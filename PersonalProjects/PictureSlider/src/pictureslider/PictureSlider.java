package pictureslider;

import java.io.File;
import utils.SliderSolver;
import utils.Utils;
/**
 * @author Obringer
 */
public class PictureSlider {

    static Utils myUtils = new Utils();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
       myUtils.myCreateDisplay();
       myUtils.myInit();
       myUtils.myDisplayLoop();
    }
}
