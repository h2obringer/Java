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
public class StackNode<T> {
    private T value;
    private StackNode next;
    
    public StackNode(T value){
        this.value=value;
        next=null;
    }
    
    public void setNext(StackNode next){
        this.next=next;
    }
    
    public T getValue(){
        return value;
    }
    
    public StackNode getNext(){
        return next;
    }
}
