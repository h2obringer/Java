/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import domain.Card;
import domain.Deck;
import euchre.EuchreGame;
import rummi.RummiGame;
import utils.Stack;
import static utils.Variables.ACE;
import static utils.Variables.SPADES;

/**
 *
 * @author Administrator
 */
public class Main {
    public static void main(String[] args){
        RummiGame game = new RummiGame();
        //EuchreGame game = new EuchreGame();
        game.gameLoop();
    }
}
