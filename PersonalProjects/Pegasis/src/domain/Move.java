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
public class Move implements Serializable{
    private String move;
    private String category;
    private String movetype;
    private int power;
    private int accuracy;
    private int pp;
    
    public void setMove(String move){
        this.move=move;
    }
    
    public void setCategory(String category){
        this.category=category;
    }
    
    public void setMoveType(String movetype){
        this.movetype=movetype;
    }
    
    public void setPower(int power){
        this.power=power;
    }
    
    public void setAccuracy(int accuracy){
        this.accuracy=accuracy;
    }
    
    public void setPP(int pp){
        this.pp=pp;
    }
    
    public String getMove(){
        return move;
    }
    
    public String getCategory(){
        return category;
    }
    
    public String getMoveType(){
        return movetype;
    }
    
    public int getPower(){
        return power;
    }
    
    public int getAccuracy(){
        return accuracy;
    }
    
    public int getPP(){
        return pp;
    }
}
