/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Obringer
 */
public class Pair<_1,_2> {
    private _1 first;
    private _2 second;
    
    public Pair(_1 first, _2 second){
        this.first = first;
        this.second = second;
    }
    
    public _1 getFirst(){
        return first; 
    }
    
    public _2 getSecond(){
        return second; 
    }
    
    public void setFirst(_1 first){
        this.first = first; 
    }    
    
    public void setRight(_2 second){
        this.second = second; 
    }
}
