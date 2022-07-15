/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rummi;

import domain.Card;
import domain.Deck;
import domain.Player;
import java.util.Scanner;
import utils.Stack;
import static utils.Variables.*;

/**
 *
 * @author Administrator
 */
public class RummiGame {
    Deck cards;
    Stack<Card> discardPile;
    Player[] players;
    int turn;
    int dealer;
    Scanner input; //read user input from the command prompt
    
    public RummiGame(){
        input = new Scanner(System.in); //read user input from the command prompt
        turn = 0;
        dealer=0;
        players = new Player[2]; //create the players 
        for(int i=0;i<2;i++){ //
            players[i]=new Player();
        }
        
        cards = new Deck(RUMMI); //use a euchre deck
        cards.shuffleDeck(3);
        discardPile = new Stack();
        //cards.shuffleDeck(3); //shuffle 3 times
        
        //dealCards();
        //System.out.println(player[0].play(0).getValue());
    }
    
    public void dealCards(){
        for(int i=0;i<players.length;i++){
            for(int j=0;j<7;j++){ //deal each player 5 cards
                players[i].add(cards.getNextCard());
            }
        }
    }
    
    public void updateTurn(){
        ++turn;
        if(turn==players.length){
            turn=0;
        }
    }
    
    public void setTurn(int turn){
        this.turn=turn;
    }
    
    public void resetTurn(){
        turn=dealer;
        updateTurn();
    }
    
    public void updateDealer(){
        ++dealer;
        if(dealer==players.length){
            dealer=0;
        }
        turn=dealer;
        updateTurn();//when updating the dealer you base the next player's turn off of the dealer
    }
    
    //TO DO
    public boolean isGameOver(){
        return true;
    }
    
    public void decidePickup(){
        String temp="";
        int tempInt=-1; //will hold value of how many cards to pick up.
        System.out.println("Player " + (turn+1) + "'s turn ->" );
        System.out.println("Cards: " + players[turn].displayHand());
        do{
            System.out.println("Discard Pile:");
            System.out.println(discardPile.getContents());
            System.out.println("Top card on deck:");
            System.out.println(cards.peek());
            System.out.println("Enter \"d\" to pick up from the deck.");
            System.out.println("Enter \"p\" to pick up from the discard pile.");
            temp=input.nextLine();
            if(temp.charAt(0)=='d'){
                if(discardPile.isEmpty()){
                    System.out.println("Nothing to pickup");
                    temp="NOTHING"; //effectively allows the loop to execute again
                }else{
                    players[turn].add(cards.getNextCard());
                }
            }else if(temp.charAt(0)=='p'){
                System.out.println("Pick up to which card?");
                tempInt=input.nextInt();
                for(int i=0;i<tempInt;i++){
                    players[turn].add(discardPile.pop());
                }
            }
        }while((temp.charAt(0)!='d')&&(temp.charAt(0)!='p'));
    }
    
    public void playRound(){
        dealCards();
        decidePickup();
        System.out.println("Player" + (turn+1) + " has: " + players[turn].displayHand());
    }
    
    public void gameLoop(){
        //while(!isGameOver()){
            playRound();
            
        //}
    }    
}
