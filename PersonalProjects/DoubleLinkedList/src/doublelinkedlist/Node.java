/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package doublelinkedlist;

/**
 *
 * @author Randy Obringer
 */
public class Node {
    private int value;
    private Node previous;
    private Node next;
    
    public Node(){
        value=0;
        previous=null;
        next=null;
    }
    
    public Node(int value){
        this.value=value;
        previous=next=null;
    }
    
    public void setValue(int value){
        this.value=value;
    }
    
    public int getValue(){
        return value;
    }
    
    public void setPrevious(Node previous){
        this.previous=previous;
    }
    
    public Node getPrevious(){
        return previous;
    }
    
    public void setNext(Node next){
        this.next=next;
    }
    
    public Node getNext(){
        return next;
    }
    
}
