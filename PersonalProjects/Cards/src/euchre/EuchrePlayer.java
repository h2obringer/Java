/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package euchre;

import domain.Player;

/**
 *
 * @author Administrator
 */
public class EuchrePlayer extends Player{
    int tricks; //adds to team tricks
    boolean namedTrump;
    
    public EuchrePlayer(){
        super();
        namedTrump=false;
        tricks=0;
    }
    
    public int getTricks(){
        return tricks;
    }
    
    public void addTrick(){
        ++tricks;
    }
    
    public void resetTricks(){
        tricks=0;
    }
    
    public void nameTrump(boolean decision){
        namedTrump=decision;
    }
    
    public boolean namedTrump(){
        return namedTrump;
    }
    
    public void resetNamedTrump(){
        namedTrump=false;
    }
}
