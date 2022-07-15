/*
 * TO DO: 
 *  + error handling input
 *  + add graphics
 */

package euchre;

import domain.Card;
import domain.Deck;
import java.util.Scanner;
import utils.Pair;
import utils.Stack;
import static utils.Variables.*;

/**
 *
 * @author Administrator
 */
public class EuchreGame {
    EuchrePlayer[] players;
    EuchreTeam team1;
    EuchreTeam team2;
    Deck cards;
    int turn;
    int dealer;
    char trump;
    Scanner input; //read user input from the command prompt
    
    public EuchreGame(){
        input = new Scanner(System.in); //read user input from the command prompt
        trump = NOTRUMP;
        turn = 0;
        dealer=0;
        players = new EuchrePlayer[4]; //create the players
        team1 = new EuchreTeam(); //create the teams
        team2 = new EuchreTeam(); 
        for(int i=0;i<4;i++){ //
            players[i]=new EuchrePlayer();
        }
        team1.add(players[0]); //add the players to the teams
        team1.add(players[2]);
        team2.add(players[1]);
        team2.add(players[3]);
        
        cards = new Deck(EUCHRE); //use a euchre deck
        //cards.shuffleDeck(3); //shuffle 3 times
        
        //dealCards();
        //System.out.println(player[0].play(0).getValue());
    }
    
    public void setTrump(Card card){
        trump=card.getSuit();
    }
    
    public char getTrump(){
        return trump;
    }
    
    public void dealCards(){
        for(int i=0;i<players.length;i++){
            for(int j=0;j<5;j++){ //deal each player 5 cards
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
    
    public void updateTeamScores(){
        if(team1.getTricks()>team2.getTricks()){
            if(team2.namedTrump()==true){ //Team2 got EUCHRED!
                team1.addToScore(2);
            }else{
                team1.addToScore(1);
            }
        }else{
            if(team1.namedTrump()==true){ //Team1 got EUCHRED!
                team2.addToScore(2);
            }else{
                team2.addToScore(1);
            }
        }
        team1.resetTricks();
        team2.resetTricks();
        team1.resetNamedTrump();
        team2.resetNamedTrump();
    }
    
    public boolean isGameOver(){
        if((team1.getScore()>=10)||(team2.getScore()>=10)){
            return true;
        }else{
            return false;
        }
    }
    
    public void gameLoop(){
        while(!isGameOver()){
            playRound();
        }
    }    
    
    public char decideTrump(){
        char nameTrump = cards.peek().getSuit();
        String temp;
        System.out.println("The card to be picked up is the " + cards.peek().toString());
        for(int i=0;i<players.length;i++){
            System.out.println("Player" + (turn+1) + " pick up?");
            temp=input.nextLine();
            if((temp.charAt(0)=='y')||(temp.charAt(0)=='Y')){
                
                players[dealer].add(cards.getNextCard()); //only the dealer can pick up the card
                System.out.println("Player" + (dealer+1) + " cards: " + players[dealer].displayHand());
                System.out.println("Which card would you like to get rid of? (0-4)");
                players[dealer].play(input.nextInt());
                resetTurn();
                return nameTrump;
            }
            updateTurn();
        }
        //nobody wanted to pick the top card as trump so loop through again
        for(int i=0;i<players.length;i++){
            System.out.println("Player" + (turn+1) + " name trump or pass?");
            temp=input.nextLine();
            switch(temp.charAt(0)){
                case DIAMONDS:
                case 'd':
                    return DIAMONDS;
                case HEARTS:
                case 'h':
                    return HEARTS;
                case CLUBS:
                case 'c':
                    return CLUBS;
                case SPADES:
                case 's':
                    return SPADES;
                default:
                    break;
            }
        }
        cards = new Deck(EUCHRE);
        cards.shuffleDeck(3);
        dealCards();
        trump=NOTRUMP;
        return NOTRUMP;
    }
    
    public void recalculateValue(Card card){ //will this mess up display of the cards?
        //yes if we change the cards actual value just for calculations for best card
        //no if we recalculate immediately before the calculation
        switch(card.getSuit()){
            case SPADES:
                switch(trump){
                    case SPADES:
                        if(card.getValue()==JACK){
                            card.setValue(RIGHT);
                        }
                        break;
                    case DIAMONDS:
                        break;
                    case CLUBS:
                        if(card.getValue()==JACK){
                            card.setValue(LEFT);
                        }
                        break;
                    case HEARTS:
                        break;
                }
                break;
            case DIAMONDS:
                switch(trump){
                    case SPADES:
                        break;
                    case DIAMONDS:
                        if(card.getValue()==JACK){
                            card.setValue(RIGHT);
                        }
                        break;
                    case CLUBS:
                        break;
                    case HEARTS:
                        if(card.getValue()==JACK){
                            card.setValue(LEFT);
                        }
                        break;
                }
                break;
            case CLUBS:
                switch(trump){
                    case SPADES:
                        if(card.getValue()==JACK){
                            card.setValue(LEFT);
                        }
                        break;
                    case DIAMONDS:
                        break;
                    case CLUBS:
                        if(card.getValue()==JACK){
                            card.setValue(RIGHT);
                        }
                        break;
                    case HEARTS:
                        break;
                }
                break;
            case HEARTS:
                switch(trump){
                    case SPADES:
                        break;
                    case DIAMONDS:
                        if(card.getValue()==JACK){
                            card.setValue(LEFT);
                        }
                        break;
                    case CLUBS:
                        break;
                    case HEARTS:
                        if(card.getValue()==JACK){
                            card.setValue(RIGHT);
                        }
                        break;
                }
                break;
        }
    }
    
    public void decideTrick(Stack<Pair<Card,Integer>> cardPlayerPairs){
        Pair<Card,Integer> best = cardPlayerPairs.pop();
        Pair<Card,Integer> temp;
        char leadingSuit = best.getFirst().getSuit();        
        recalculateValue(best.getFirst()); //recalculate card values now that trump has been decided.
        while(!cardPlayerPairs.isEmpty()){
            temp=cardPlayerPairs.pop();
            if(leadingSuit==trump){
                if(temp.getFirst().getSuit()==trump){
                    recalculateValue(temp.getFirst());
                    if(temp.getFirst().getValue()>best.getFirst().getValue()){
                        best=temp;
                    }
                }
                //otherwise nothing beats trump so no need to do anything further
            }else{
                if(best.getFirst().getSuit()==trump){
                    if(temp.getFirst().getSuit()==trump){
                        recalculateValue(temp.getFirst());
                        if(temp.getFirst().getValue()>best.getFirst().getValue()){
                            best=temp;
                        }
                        //following suit only higher cards win so do nothing here
                    }
                }else if(temp.getFirst().getSuit()==trump){
                    recalculateValue(temp.getFirst()); //needed for future comparisons
                    best=temp;
                }else if(temp.getFirst().getSuit()==leadingSuit){ //we must follow suit
                   if(temp.getFirst().getValue()>best.getFirst().getValue()){
                        best=temp;
                    }
                }
            }
        }   
        //everything is computed now
        players[best.getSecond()].addTrick();
        turn=best.getSecond();
        System.out.println("Player" + (best.getSecond()+1) + " won the trick");
        System.out.println("with the " + best.getFirst().toString());
    }
    
    public void playRound(){
        cards = new Deck(EUCHRE);
        cards.shuffleDeck(3);
        dealCards();
        trump=NOTRUMP;
        while(trump==NOTRUMP){
            trump=decideTrump();
        } 
        Stack<Pair<Card,Integer>> cardPlayerPair = new Stack();
        for(int j=0;j<5;j++){ //5 tricks per round
            for(int i=0;i<4;i++){ //4 players to play
                //dealer (and first player to play) should already be decided for the first round by the constructor
                System.out.println("Player" + (turn+1) + " cards: ");
                System.out.println(players[turn].displayHand());
                System.out.println("Which card would you like to play?");
                cardPlayerPair.insert(new Pair(players[turn].play(input.nextInt()),turn)); //VERY DANGEROUS, CHANGE
                //System.out.println("Player" + (turn+1) + " used the " + players[turn].play(0).toString() + ".");
                updateTurn();
            }
            decideTrick(cardPlayerPair);
            //team1.addTrick();// for winning team. Change to add trick to the winning team.
            //update who goes first based off of who won the last trick.
            System.out.println("End trick" + (j+1));
        }
        updateTeamScores();
        updateDealer(); //update the dealer (and first player to play) for the next round
        System.out.println("Team1 score: " + team1.getScore() + ". Team2 score: " + team2.getScore());
        System.out.println("Next round?"); //TO DO: FIX COMING UP AT END OF THE GAME
        input.nextLine();
    }
}