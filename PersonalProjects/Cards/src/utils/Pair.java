/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author Administrator
 */
public class Pair <T,U>{
    T first;
    U second;
    
    public Pair(T first,U second){
        this.first=first;
        this.second=second;
    }
    
    public T getFirst(){
        return first;
    }
    
    public U getSecond(){
        return second;
    }
}
