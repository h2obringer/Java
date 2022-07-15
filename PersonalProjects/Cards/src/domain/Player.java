/*
 * TO DO: create a EuchrePlayer class that extends the Player class for euchre specific stuff. 
 */

package domain;

import utils.Vector;

/**
 *
 * @author Administrator
 */
public class Player {
    protected Vector<Card> hand;
    //has their own deck of cards
    
    
    public Player(){
        hand = new Vector();
    }
    
    public Card play(int i){
        return hand.pop(i);
    }
    
    public Card peek(int i){
        return hand.itemAt(i);
    }
    
    public void reorder(int i,int j){
        hand.reorder(i, j);
    }
    
    public void add(Card card){
        hand.insert(card);
    }
    
    public String displayHand(){
        String output="";
        for(int i=0;i<hand.getSize();i++){
            output+=hand.itemAt(i).toString();
            if(i+1!=hand.getSize()){
                output+=", ";
            }
        }
        return output;
    }
    //need to be able to rearrange the order of the cards to the players liking. 
    
    //need to be able to play 1 or multiple cards at a time. 
}
