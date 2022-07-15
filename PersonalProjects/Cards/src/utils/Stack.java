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
public class Stack<T> {
    private int size=0;
    private StackNode<T> top;
    
    public Stack(){
        size=0;
        top=null;
    }
    
    public void insert(T value){
        if(top==null){
            top = new StackNode(value);
        }else{
            StackNode temp = new StackNode(value);
            temp.setNext(top);
            top=temp;
        }
        ++size;
    }
    
    //allows the return of a null value
    public T pop(){
        T temp = top.getValue();
        top=top.getNext();
        --size;
        return temp;
    }
    
    public T peek(){
        return top.getValue();
    }
    
    public StackNode getNext(){
        return top.getNext();
    }
    
    public int getSize(){
        return size;
    }
    
    public boolean isEmpty(){
        if(size==0){
            return true;
        }else{
            return false;
        }
    }
    
    public String getContents(){
        String output="";
        StackNode<T> temp=top;
        if(size==0){
            return "NOTHING";
        }
        for(int i=0;i<size;i++){
            output+=temp.getValue().toString();
            if(i!=size-1){
                output+=", ";
            }
        }
        return output;
    }
}
