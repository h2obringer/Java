/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.util.Random;
import utils.Stack;
import static utils.Variables.*;

/**
 *
 * @author Administrator
 */
public class Deck {
    Stack<Card> deck = new Stack();
    
    public Deck(){
        deck.insert(new Card(ACE,SPADES));
        deck.insert(new Card(TWO,SPADES));
        deck.insert(new Card(THREE,SPADES));
        deck.insert(new Card(FOUR,SPADES));
        deck.insert(new Card(FIVE,SPADES));
        deck.insert(new Card(SIX,SPADES));
        deck.insert(new Card(SEVEN,SPADES));
        deck.insert(new Card(EIGHT,SPADES));
        deck.insert(new Card(NINE,SPADES));
        deck.insert(new Card(TEN,SPADES));
        deck.insert(new Card(JACK,SPADES));
        deck.insert(new Card(QUEEN,SPADES));
        deck.insert(new Card(KING,SPADES));
        deck.insert(new Card(ACE,CLUBS));
        deck.insert(new Card(TWO,CLUBS));
        deck.insert(new Card(THREE,CLUBS));
        deck.insert(new Card(FOUR,CLUBS));
        deck.insert(new Card(FIVE,CLUBS));
        deck.insert(new Card(SIX,CLUBS));
        deck.insert(new Card(SEVEN,CLUBS));
        deck.insert(new Card(EIGHT,CLUBS));
        deck.insert(new Card(NINE,CLUBS));
        deck.insert(new Card(TEN,CLUBS));
        deck.insert(new Card(JACK,CLUBS));
        deck.insert(new Card(QUEEN,CLUBS));
        deck.insert(new Card(KING,CLUBS));
        deck.insert(new Card(ACE,HEARTS));
        deck.insert(new Card(TWO,HEARTS));
        deck.insert(new Card(THREE,HEARTS));
        deck.insert(new Card(FOUR,HEARTS));
        deck.insert(new Card(FIVE,HEARTS));
        deck.insert(new Card(SIX,HEARTS));
        deck.insert(new Card(SEVEN,HEARTS));
        deck.insert(new Card(EIGHT,HEARTS));
        deck.insert(new Card(NINE,HEARTS));
        deck.insert(new Card(TEN,HEARTS));
        deck.insert(new Card(JACK,HEARTS));
        deck.insert(new Card(QUEEN,HEARTS));
        deck.insert(new Card(KING,HEARTS));
        deck.insert(new Card(ACE,DIAMONDS));
        deck.insert(new Card(TWO,DIAMONDS));
        deck.insert(new Card(THREE,DIAMONDS));
        deck.insert(new Card(FOUR,DIAMONDS));
        deck.insert(new Card(FIVE,DIAMONDS));
        deck.insert(new Card(SIX,DIAMONDS));
        deck.insert(new Card(SEVEN,DIAMONDS));
        deck.insert(new Card(EIGHT,DIAMONDS));
        deck.insert(new Card(NINE,DIAMONDS));
        deck.insert(new Card(TEN,DIAMONDS));
        deck.insert(new Card(JACK,DIAMONDS));
        deck.insert(new Card(QUEEN,DIAMONDS));
        deck.insert(new Card(KING,DIAMONDS));
    }
    
    public Deck(final int GAMETYPE){
        /*int numOfDecks=1;
        if(GAMETYPE==HANDANDFOOT){
            numOfDecks=4;
        }*/
        deck.insert(new Card(ACE,SPADES));
        deck.insert(new Card(NINE,SPADES));
        deck.insert(new Card(TEN,SPADES));
        deck.insert(new Card(JACK,SPADES));
        deck.insert(new Card(QUEEN,SPADES));
        deck.insert(new Card(KING,SPADES));
        deck.insert(new Card(ACE,CLUBS));
        deck.insert(new Card(NINE,CLUBS));
        deck.insert(new Card(TEN,CLUBS));
        deck.insert(new Card(JACK,CLUBS));
        deck.insert(new Card(QUEEN,CLUBS));
        deck.insert(new Card(KING,CLUBS));
        deck.insert(new Card(ACE,HEARTS));
        deck.insert(new Card(NINE,HEARTS));
        deck.insert(new Card(TEN,HEARTS));
        deck.insert(new Card(JACK,HEARTS));
        deck.insert(new Card(QUEEN,HEARTS));
        deck.insert(new Card(KING,HEARTS));
        deck.insert(new Card(ACE,DIAMONDS));
        deck.insert(new Card(NINE,DIAMONDS));
        deck.insert(new Card(TEN,DIAMONDS));
        deck.insert(new Card(JACK,DIAMONDS));
        deck.insert(new Card(QUEEN,DIAMONDS));
        deck.insert(new Card(KING,DIAMONDS));
        if(GAMETYPE!=EUCHRE){    
            deck.insert(new Card(TWO,SPADES));
            deck.insert(new Card(THREE,SPADES));
            deck.insert(new Card(FOUR,SPADES));
            deck.insert(new Card(FIVE,SPADES));
            deck.insert(new Card(SIX,SPADES));
            deck.insert(new Card(SEVEN,SPADES));
            deck.insert(new Card(EIGHT,SPADES));
            deck.insert(new Card(TWO,CLUBS));
            deck.insert(new Card(THREE,CLUBS));
            deck.insert(new Card(FOUR,CLUBS));
            deck.insert(new Card(FIVE,CLUBS));
            deck.insert(new Card(SIX,CLUBS));
            deck.insert(new Card(SEVEN,CLUBS));
            deck.insert(new Card(EIGHT,CLUBS));
            deck.insert(new Card(TWO,HEARTS));
            deck.insert(new Card(THREE,HEARTS));
            deck.insert(new Card(FOUR,HEARTS));
            deck.insert(new Card(FIVE,HEARTS));
            deck.insert(new Card(SIX,HEARTS));
            deck.insert(new Card(SEVEN,HEARTS));
            deck.insert(new Card(EIGHT,HEARTS));
            deck.insert(new Card(TWO,DIAMONDS));
            deck.insert(new Card(THREE,DIAMONDS));
            deck.insert(new Card(FOUR,DIAMONDS));
            deck.insert(new Card(FIVE,DIAMONDS));
            deck.insert(new Card(SIX,DIAMONDS));
            deck.insert(new Card(SEVEN,DIAMONDS));
            deck.insert(new Card(EIGHT,DIAMONDS));
        }
    }
    
    public Card peek(){
        return deck.peek();
    }
    
    public Card getNextCard(){
        return deck.pop();
    }
    
    public void shuffleDeck(){
        int size=deck.getSize();
        Card[] cardArray = new Card[size];
        //convert stack to an array
        for(int i=0;i<cardArray.length;i++){
            cardArray[i]=deck.pop();
        }
        Random rnd = new Random(System.currentTimeMillis());
        //shuffle array
        for(int i=0;i<cardArray.length;i++){
            int index = rnd.nextInt(cardArray.length);
            Card temp = cardArray[i];
            cardArray[i] = cardArray[index];
            cardArray[index]=temp;
        }
        //convert array to stack
        for(int i=0;i<cardArray.length;i++){
            deck.insert(cardArray[i]);
        }
    }
   
    public void shuffleDeck(int amount){
        if(amount<=0){ //ensure at least 1 shuffle occurs
            amount=1;
        }
        int size=deck.getSize();
        Card[] cardArray = new Card[size];
        //convert stack to an array
        for(int i=0;i<cardArray.length;i++){
            cardArray[i]=deck.pop();
        }
        Random rnd = new Random();
        
        //shuffle x amount of times
        for(int i=0;i<amount;i++){
            //shuffle array
            for(int j=0;j<cardArray.length;j++){
                int index = rnd.nextInt(cardArray.length);
                Card temp = cardArray[j];
                cardArray[j] = cardArray[index];
                cardArray[index]=temp;
            }
        }
        //convert array to stack
        for(int i=0;i<cardArray.length;i++){
            deck.insert(cardArray[i]);
        }
    }
}
