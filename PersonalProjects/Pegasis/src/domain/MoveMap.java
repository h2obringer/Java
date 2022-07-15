/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class MoveMap implements Serializable{
    private String pokemon;
    private String move;
    private int level;
    
    public void setMove(String move){
        this.move=move;
    }
    
    public void setPokemon(String pokemon){
        this.pokemon=pokemon;
    }
    
    public void setLevel(int level){
        this.level=level;
    }
    
    public String getMove(){
        return move;
    }
    
    public String getPokemon(){
        return pokemon;
    }
    
    public int getLevel(){
        return level;
    }
}
