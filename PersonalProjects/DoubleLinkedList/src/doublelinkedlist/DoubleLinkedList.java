/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package doublelinkedlist;

/**
 *
 * @author Administrator
 */
public class DoubleLinkedList {
    private int size; //tells how many items are in the list. Not necessary for real function of this class
    private Node head; //start of the list
    private Node curPtr; //pointer to the current Node
    
    public DoubleLinkedList(){
        size=0;
        head=curPtr=null;
    }
    
    public int getSize(){
        return size;
    }
    
    public int getCurrentValue(){
        return curPtr.getValue();
    }
    
    public void add(int value){
        curPtr=head; //start at the beginning of the list
        size++; //we are adding something
        if(head==null){ //if nothing in list
            head=new Node(value);
            head.setPrevious(null);
            head.setNext(null);
        }else{
            Node temp = new Node(value); //create the new node
            do{
                if(value<=curPtr.getValue()){ //if the new node is less than the current node
                    if(curPtr.getPrevious()!=null){
                        curPtr.getPrevious().setNext(temp);
                    }
                    temp.setPrevious(curPtr.getPrevious());
                    temp.setNext(curPtr);
                    curPtr.setPrevious(temp);
                    
                    //test if the current pointer was the head or not. 
                    if(head==curPtr){ //if it is
                        head=temp; //then update the head Node
                        head.setPrevious(null); //ensure it is null;
                    }
                    temp=null; //since we are done using temp now we can do this so it can better be picked up by the Java garbage collector
                    return; //return from the function so nothing else is computed
                }else{ //otherwise the value is bigger than  the current node
                    if(curPtr.getNext()!=null){ //if there is a next node
                        curPtr=curPtr.getNext(); //then move along in the list
                    }else{ //this means it will become the last item in the list
                        temp.setPrevious(curPtr);//set new node previous
                        temp.setNext(null); //set new node next
                        curPtr.setNext(temp); //update the current node next
                        temp=null; //since we are done using temp now we can do this so it can better be picked up by the Java garbage collector
                        return; //break out of the loop
                    }
                }
            }while(curPtr!=null); //if we have reached the last node then our new Node will become the new last node
        }
    }
    
    public void delete(int value){
        curPtr=head; //start at the beginning of the list
        if(head==null){ //if there is nothing in the list then there is nothing to delete
            return;
        }
        do{
            if(curPtr.getValue()==value){ //we have found the node to delete
                if(curPtr==head){ //if it is the head
                    head=curPtr.getNext(); //update the head
                    head.setPrevious(null); //update the previous ptr
                    curPtr=null;
                    size--;
                    return;
                }else{
                    //if we are here then we know there is something before the current node that needs updated
                    curPtr.getPrevious().setNext(curPtr.getNext());//so update the next ptr for the previous node
                    if(curPtr.getNext()!=null){ //check if there is a next node that needs updated
                        curPtr.getNext().setPrevious(curPtr.getPrevious()); //if there is then update the previous ptr for the next node
                    }
                }
                curPtr=null; //set the deleted node to null so it can get picked up by the garbage collector faster.
                size--;
                return;
            }else{
                if(curPtr.getNext()!=null){ //if there is another node
                    curPtr=curPtr.getNext(); //then move to it
                }
            }
        }while(curPtr!=null);//we have reached the end of the list
        System.out.println("Value did not exist to delete");
        return; //not needed will work either way if we get this far
    }
    
    public void showAll(){
        curPtr=head; //start at the beginning
        if(head==null){ //this means there is nothing in the list
            System.out.println("There is nothing in this list");
        }else{
            do{ //we know there is atleast one value to print if we reach here
                System.out.print(curPtr.getValue()+" ");
                curPtr=curPtr.getNext(); //update to the next item in the list
            }while(curPtr!=null); //if the next item in the list is null then terminate the loop
            System.out.println(); //ensure the console starts new output on the next line
        }
    }
    //demonstrate that our list is properly "doubly" linked by use of the previous pointers
    public void showAllReversed(){
        curPtr=head;
        if(head==null){
            System.out.println("There is nothing in this list");
        }else{
            while(curPtr.getNext()!=null){
                curPtr=curPtr.getNext();
            }
            //now curPtr should be the last node
            do{
                System.out.print(curPtr.getValue()+" ");
                curPtr=curPtr.getPrevious();
            }while(curPtr!=null);
            System.out.println(); //ensure the console starts new output on the next line
        }
    }
    
}
