/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Time {
    private static double delta;
    
    public static final long SECOND=1000000000L;
    
    public static long getTime(){
        return System.nanoTime();
    }
    
    public static double getDelta(){
        return delta;
    }
    
    public static void setDelta(double delta){
        Time.delta=delta;
    }
}
