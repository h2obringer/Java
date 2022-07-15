/*
 * TO DO: create a EuchreTeam class that extends the Team class for euchre specific functions.
 */

package domain;

import utils.Vector;

/**
 *
 * @author Administrator
 */
public class Team <T>{
    //count score based off of the players individual scoers? and tricks?
    protected Vector<T> team;
    protected int score;
    protected int current;
    
    public Team(){
        score=0;
        team = new Vector();
    }
    
    public Team(int numOfPlayers){
        score=0;
        team = new Vector();
        for(int i=0;i<numOfPlayers;i++){
            team.insert((T)new Object()); //will this work?
        }
    }
    
    public void add(T player){
        team.insert(player);
    }
    
    public void addToScore(int points){
        score+=points;
    }
    
    public int getScore(){
        return score;
    }
    
}


