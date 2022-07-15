/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package res;

import java.io.File;

/**
 *
 * @author Administrator
 */
public class ResourceLoader {
    static ResourceLoader rl = new ResourceLoader();
    public static File getFile(String filename){
        return new File(rl.getClass().getResource(filename).getFile());
    }
}
