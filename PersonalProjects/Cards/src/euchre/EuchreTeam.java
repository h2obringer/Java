/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package euchre;

import domain.Player;
import domain.Team;
import utils.Vector;

/**
 *
 * @author Administrator
 */
public class EuchreTeam extends Team<EuchrePlayer>{
    boolean alone; //is a player on the team going to play alone?
    
    public EuchreTeam(){
        super();
        alone=false;
    }
    public EuchreTeam(int numOfPlayers){
        super();
        alone=false;
    }
    
    public boolean namedTrump(){
        for(int i=0;i<team.getSize();i++){
            if(team.itemAt(i).namedTrump()==true){
                return true;
            }
        }
        return false;
    }
    
    public void resetNamedTrump(){
        for(int i=0;i<team.getSize();i++){
            team.itemAt(i).resetNamedTrump();
        }
    }
    
    public void alone(boolean decision){
        alone=decision;
    }
    
    public boolean getAlone(){
        return alone;
    }
    
    public void resetTricks(){
        for(int i=0;i<team.getSize();i++){
            team.itemAt(i).resetTricks();
        }
    }
    
    public int getTricks(){
        int total=0;
        for(int i=0;i<team.getSize();i++){
            total+=team.itemAt(i).getTricks();
        }
        return total;
    }
}
