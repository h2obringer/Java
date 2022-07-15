/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author Administrator
 */
public class Variables {
    //self explanatory permanent variables
    
    public static final int PANEL_WIDTH = 800; //window width used in GolfPanel.java
    public static final int PANEL_HEIGHT = 500; //window height used in GolfPanel.java
    public static final int TRIPLE_BOGEY = 3;//score-par gives performance on the hole
    public static final int DOUBLE_BOGEY = 2;//score-par gives performance on the hole
    public static final int BOGEY = 1;//score-par gives performance on the hole
    public static final int UNDER_PAR = 1; //and ID. Value can be anything as long as it doesn't equal the value of OVER_PAR or PAR
    public static final int OVER_PAR = 2; //an ID. Value can be anything as long as it does't equal the value of UNDER_PAR or PAR
    public static final int PAR = 0; //has 2 purposes. Provides an ID to state the user scored PAR overall. Also is the calculated score for the hole when score-par is used.
    public static final int BIRDIE = -1;//score-par gives performance on the holere-par gives performance on the hole
    public static final int EAGLE = -2;//score-par gives performance on the holere-par gives performance on the hole
    public static final int ALBATROSS = -3;//score-par gives performance on the hole
    public static final int CONDOR = -4; //score-par gives performance on the hole
    public static final int PAR3 = 3; //par 3 is 3 strokes
    public static final int PAR4 = 4; //par 4 is 4 strokes
    public static final int PAR5 = 5; //par 5 is 5 strokes
    public static final int BIRDIE_BTN = 0; //assign each button an ID so we can identify them more easily
    public static final int PAR_BTN = 1; //assign each button an ID so we can identify them more easily
    public static final int SCORE_BTN = 2; //assign each button an ID so we can identify them more easily
    public static final int _18_HOLES = 18; //a golf game has 18 holes
}
