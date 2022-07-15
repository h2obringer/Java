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
public class Pokedex implements Serializable{
    private int id;
    private String pokemon;
    private String type1;
    private String type2;
    private boolean seen;
    private boolean caught;
    
    public void setId(int id){
        this.id=id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setPokemon(String pokemon){
        this.pokemon=pokemon;
    }
    
    public String getPokemon(){
        return pokemon;
    }
    
    public void setType1(String type1){
        this.type1=type1;
    }
    
    public String getType1(){
        return type1;
    }
    
    public void setType2(String type2){
        this.type2=type2;
    }
    
    public String getType2(){
        return type2;
    }
    
    public void setSeen(boolean seen){
        this.seen=seen;
    }
    
    public boolean getSeen(){
        return seen;
    }
    
    public void setCaught(boolean caught){
        this.caught=caught;
    }
    
    public boolean getCaught(){
        return caught;
    }
}
