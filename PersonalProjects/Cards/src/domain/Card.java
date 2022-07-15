/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import static utils.Variables.*;

/**
 *
 * @author Administrator
 */
public class Card {
    private char suit = ' ';
    private int value = -1;
    
    public Card(int value, char suit){
        this.suit=suit;
        this.value=value;
    }
    
    public char getSuit(){
        return suit;
    }
    
    public int getValue(){
        return value;
    }
    
    public void setValue(int value){
        this.value=value;
    }
    
    public String toString(){
        String temp="";
        switch(value){
            case ACE:
                temp+="A";
                break;
            case JACK:
                temp+="J";
                break;
            case QUEEN:
                temp+="Q";
                break;
            case KING:
                temp+="K";
                break;
            default:
                temp+=Integer.toString(value);
        }
        temp+=suit;
        return temp;
    }
    
}
