
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Input {
    
    public static final int NUM_KEYCODES = 256;
    public static final int NUM_MOUSEBUTTONS = 5;
    private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> upKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> currentMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> downMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> upMouse = new ArrayList<Integer>();

    public static void update(){ //determine we are at the next frame, if key is down don't say it is down anymore
        upMouse.clear();
        for(int i=0;i<NUM_MOUSEBUTTONS; i++){
            if(!getMouse(i) && currentMouse.contains(i)){
                upMouse.add(i);
            }
        }
        downMouse.clear();
        for(int i=0;i<NUM_MOUSEBUTTONS; i++){
            if(getMouse(i) && !currentMouse.contains(i)){
                downMouse.add(i);
            }
        }
        
        upKeys.clear();
        for(int i=0;i<NUM_KEYCODES; i++){
            if(!getKey(i) && currentKeys.contains(i)){
                upKeys.add(i);
            }
        }
        
        downKeys.clear();
        
        for(int i=0;i<NUM_KEYCODES; i++){//detect every key that is pressed in a single frame
            if(getKey(i) && !currentKeys.contains(i)){ //key wasn't pressed during previous frame
                downKeys.add(i);
            }
        }
        
        //rely on code from previous frame by doing this last
        currentKeys.clear();
        for(int i=0;i<NUM_KEYCODES; i++){
            if(getKey(i)){
                currentKeys.add(i);
            }
        }
        
        currentMouse.clear();
        for(int i=0;i < NUM_MOUSEBUTTONS; i++){
            if(getMouse(i)){
                currentMouse.add(i);
            }
        }
        
    }
    
    public static boolean getKey(int keyCode){
        return Keyboard.isKeyDown(keyCode);
    }
    
    //allows us to tell when the user pushed down the key
    public static boolean GetKeyDown(int keyCode){ //only happens for one frame
        return downKeys.contains(keyCode);
    }
    
    //allows us to tell when the user released a key
    public static boolean GetKeyUp(int keyCode){ //only happens for one frame
        return upKeys.contains(keyCode);
    }
    
    public static boolean getMouse(int mouseButton){
        return Mouse.isButtonDown(mouseButton);
    }
    
    public static boolean getMouseDown(int mouseButton){
        return downMouse.contains(mouseButton);
    }
    
    public static boolean getMouseUp(int mouseButton){
        return upMouse.contains(mouseButton);
    }
}
