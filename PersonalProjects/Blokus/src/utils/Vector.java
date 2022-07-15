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
public class Vector<T> {
    private T[] vector;
    private int size;
    private int capacity;
    
    public Vector(){
        size=0;
        capacity=18;
        vector = (T[])new Object[capacity]; //required syntax for generic Arrays
    }
    
    public T itemAt(int i){
        return vector[i];
    }
    
    public T pop(int i){
        T temp = vector[i];
        for(int j=i;j<size-1;j++){//shift rest of vector to not leave any gaps in array
            vector[j]=vector[j+1];
        }
        --size;
        return temp;
    }
    
    public void insert(T item){
        if(size==capacity){
            resize();
        }
        vector[size]=item;
        ++size;
    }
    
    private void resize(){
        capacity*=2;//double the capacity
        T[] temp = (T[])new Object[capacity];
        for(int i=0;i<size;i++){ //copy all of the elements over
            temp[i]=vector[i];
        }
        vector=temp; //officially resizes the vector
    }
    
    public int getSize(){
        return size;
    }
    
    public int getCapacity(){
        return capacity;
    }
}
