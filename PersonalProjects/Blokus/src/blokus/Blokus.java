
package blokus;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import board.BlockGrid;
import pieces.Block;
import pieces.BlockType;
import player.Deck;
import pieces.Piece;
import utils.Variables;
import static utils.Variables.*;

    
/**
 *
 * @author Obringer
 * 
 * TODO: clean up and document all code
 * TODO: change player variable to be of type Deck so you can change things without 4 separate codes. 
 * TODO: display text to screen
 * TODO: allow screen scaling
 * TODO: fix texture mapping
 * TODO: find out who wins the game (created getWinner(), need to apply)
 * TODO: account for if player cannot play anymore (small solution, just allow to switch player)
 * 
 */

//requires the use of the -XX:+UseConcMarkSweepGC (VM option for running the program)
//causes the garbage collector for java to run concurrently to make the program run more smoothly
public class Blokus {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int FPS = 60; //frames per second
    private BlockGrid grid;
    private Piece current_piece;
    private Deck red_player;
    private Deck blue_player;
    private Deck green_player;
    private Deck yellow_player;
    private Deck current_player;
    private int player=RED;
    private boolean place_piece; //true = player is in middle of placing piece on board. TODO: COULD MOVE TO DECK CLASS
    private boolean mouse_drag=false; //tells us whether we are currently dragging a piece with the mouse or not
    
    public Blokus(){
        initWindow("Blokus");
        initGraphics();
        
        grid = new BlockGrid(32,24); //create a 32X24 grid
        
        red_player = new Deck(BlockType.RED_BLOCK); 
        blue_player = new Deck(BlockType.BLUE_BLOCK);
        green_player = new Deck(BlockType.GREEN_BLOCK);
        yellow_player = new Deck(BlockType.YELLOW_BLOCK);
        current_player = red_player; //RED player goes first //TODO ************************************************
        player=RED;
        place_piece=false;
        current_piece=new Piece(_1,BlockType.NULLED,1,-1,-1); //prevent null pointer exceptions in mouse click tests
        
        play();
    }
    
    public void initWindow(String title){
        try{
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle(title);
            Display.create();
        }catch(LWJGLException e){
            e.printStackTrace();
        }
    }
    
    public void initGraphics(){
        //Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,WIDTH,HEIGHT,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_CLAMP);
    }
    
    public void play(){
        while(!Display.isCloseRequested()){
            glClear(GL_COLOR_BUFFER_BIT);
            
            //DRAW game board
            glPushMatrix();
            grid.draw();
            //DRAW CURRENT PLAYER'S DECK IN STAGING AREA
            if(player==RED){
                red_player.draw();   
            }else if(player==YELLOW){
                yellow_player.draw();
            }else if(player==BLUE){
                blue_player.draw();
            }else if(player==GREEN){
                green_player.draw();
            }
            //DRAW PIECE SELECTED FOR PLACEMENT ON BOARD
            if(place_piece==true){
                current_piece.draw();
            }
            
            glPopMatrix();
            //END DRAW
            
            input();
            
            Display.update();
            Display.sync(FPS);
        }
        
        Display.destroy();
        System.exit(0);
    }
    
    private BlockType getWinner(){
        int redCount=0;
        int blueCount=0;
        int greenCount=0;
        int yellowCount=0;
        for(int i=0;i<red_player.getPieces().getSize();i++){
            redCount+=red_player.getPieces().itemAt(i).getNumBlocks();
        }
        for(int i=0;i<blue_player.getPieces().getSize();i++){
            blueCount+=blue_player.getPieces().itemAt(i).getNumBlocks();
        }
        for(int i=0;i<green_player.getPieces().getSize();i++){
            greenCount+=green_player.getPieces().itemAt(i).getNumBlocks();
        }
        for(int i=0;i<yellow_player.getPieces().getSize();i++){
            yellowCount+=yellow_player.getPieces().itemAt(i).getNumBlocks();
        }
        
        if((redCount<blueCount)&&(redCount<greenCount)&&(redCount<yellowCount)){
            return red_player.getCurrentPiece().getType();
        }else if((blueCount<redCount)&&(blueCount<greenCount)&&(blueCount<yellowCount)){
            return blue_player.getCurrentPiece().getType();
        }else if((greenCount<redCount)&&(greenCount<blueCount)&&(greenCount<yellowCount)){
            return green_player.getCurrentPiece().getType();
        }else if((yellowCount<redCount)&&(yellowCount<blueCount)&&(yellowCount<greenCount)){
            return yellow_player.getCurrentPiece().getType();
        }else{
            return BlockType.NULLED; //Tie?
        }
    }
        
    private boolean clickedOnPiece(int mouse_x, int mouse_y, Piece piece){
        boolean clicked=false;
        for(Block block:piece.getBlocks()){
            if(block.getType()!=BlockType.NULLED){
                if(block.getX()==mouse_x && block.getY()==mouse_y){
                  clicked=true;
                }
            }
        }
        return clicked;
    }
    
    private boolean clickedAccept(int mouse_x,int mouse_y){
        if(mouse_x==2&&mouse_y==21){
            return true;
        }
        return false;
    }
    
    private boolean clickedUndo(int mouse_x,int mouse_y){
        if(mouse_x==3&&mouse_y==21){
            return true;
        }
        return false;
    }
    
    private boolean clickedHorizontalFlip(int mouse_x,int mouse_y){
        if(mouse_x==4&&mouse_y==21){
            return true;
        }
        return false;
    }
    private boolean clickedVerticalFlip(int mouse_x,int mouse_y){
        if(mouse_x==6&&mouse_y==21){
            return true;
        }
        return false;
    }
    
    private void input(){
        int mousex = Mouse.getX();
        int mousey = HEIGHT - Mouse.getY() - 1;
        //boolean mouseClicked = Mouse.isButtonDown(0);
        int grid_x = Math.round(mousex/Variables.BLOCK_SIZE); //get the Mouse X coordinate in relation to our grid
        int grid_y = Math.round(mousey/Variables.BLOCK_SIZE); //get the Mouse Y coordinate in relation to our grid
     
        while(Mouse.next()){
            Piece mouse_piece = null;
            if(player==RED){
                mouse_piece = red_player.getCurrentPiece();
            }else if(player==BLUE){
                mouse_piece = blue_player.getCurrentPiece();
            }else if(player==GREEN){
                mouse_piece = green_player.getCurrentPiece();
            }else if(player==YELLOW){
                mouse_piece = yellow_player.getCurrentPiece();
            }
            if(Mouse.getEventButtonState()){
                if(Mouse.getEventButton()==0){
                    if(clickedOnPiece(grid_x,grid_y,mouse_piece)&&place_piece==false){
                        if(player==RED){
                            current_piece=red_player.popCurrentPiece();
                        }else if(player==BLUE){
                            current_piece=blue_player.popCurrentPiece();
                        }else if(player==GREEN){
                            current_piece=green_player.popCurrentPiece();
                        }else if(player==YELLOW){
                            current_piece=yellow_player.popCurrentPiece();
                        }
                        place_piece=true;
                        mouse_drag=true;
                    }else if(clickedOnPiece(grid_x,grid_y,current_piece)){
                        mouse_drag=true;
                    }else if(clickedAccept(grid_x,grid_y)){
                        if(place_piece==true){
                            if(grid.validMove(current_piece)){
                                grid.placePiece(current_piece);
                                //SWITCH PLAYER TURN
                                if(player==RED){
                                    player=YELLOW;
                                }else if(player==YELLOW){
                                    player=BLUE;
                                }else if(player==BLUE){
                                    player=GREEN;
                                }else if(player==GREEN){
                                    player=RED;
                                }
                                place_piece=false;
                            }else{
                                System.out.println("Not a valid placement!");
                            }
                        }else{
                            if(player==RED){
                                current_piece=red_player.popCurrentPiece(); //pop piece out of deck
                            }else if(player==YELLOW){
                                current_piece=yellow_player.popCurrentPiece(); //pop piece out of deck
                            }else if(player==BLUE){
                                current_piece=blue_player.popCurrentPiece(); //pop piece out of deck
                            }else if(player==GREEN){
                                current_piece=green_player.popCurrentPiece(); //pop piece out of deck
                            }
                            place_piece=true; //piece must be placed on board now, can't switch
                        }
                    }else if(clickedUndo(grid_x,grid_y)){
                        //undo choosing a piece to place
                        if(place_piece==true){
                            if(player==RED){
                                red_player.insertPiece(current_piece);
                            }else if(player==YELLOW){
                                yellow_player.insertPiece(current_piece);
                            }else if(player==BLUE){
                                blue_player.insertPiece(current_piece);
                            }else if(player==GREEN){
                                green_player.insertPiece(current_piece);
                            }
                            current_piece=new Piece(_1,BlockType.NULLED,1,-1,-1); //prevent null pointer exception in mouse click tests
                            place_piece=false; 
                        }
                    }else if(clickedHorizontalFlip(grid_x,grid_y)){
                        if(place_piece==true){
                            current_piece.flipHorizontally();
                        }else{
                            if(player==RED){
                                red_player.flipSelectionHorizontally();
                            }else if(player==YELLOW){
                                yellow_player.flipSelectionHorizontally();
                            }else if(player==BLUE){
                                blue_player.flipSelectionHorizontally();
                            }else if(player==GREEN){
                                green_player.flipSelectionHorizontally();
                            }
                        }
                    }else if(clickedVerticalFlip(grid_x,grid_y)){
                        if(place_piece==true){
                            current_piece.flipVertically();
                        }else{
                            if(player==RED){
                                red_player.flipSelectionVertically();
                            }else if(player==YELLOW){
                                yellow_player.flipSelectionVertically();
                            }else if(player==BLUE){
                                blue_player.flipSelectionVertically();
                            }else if(player==GREEN){
                                green_player.flipSelectionVertically();
                            }
                        }
                    }
                }
            }else{
                if(Mouse.getEventButton()==0){
                    mouse_drag=false; //false whenever you let go of the mouse
                }
            }
            
            if(Mouse.isButtonDown(0)&&place_piece==true&&mouse_drag==true){
                current_piece.changeCoordinates(grid_x,grid_y);
            }
        }
        
        while(Keyboard.next()){
            if(Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.getEventKeyState()){
                if(place_piece==true){ 
                    current_piece.moveUp();
                }else{ //move deck up
                    if(player==RED){
                        red_player.scrollUp();
                    }else if(player==YELLOW){
                        yellow_player.scrollUp();
                    }else if(player==BLUE){
                        blue_player.scrollUp();
                    }else if(player==GREEN){
                        green_player.scrollUp();
                    }
                }
            }else if(Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.getEventKeyState()){
                if(place_piece==true){
                    current_piece.moveDown();
                }else{ //move deck down
                    if(player==RED){
                        red_player.scrollDown();
                    }else if(player==YELLOW){
                        yellow_player.scrollDown();
                    }else if(player==BLUE){
                        blue_player.scrollDown();
                    }else if(player==GREEN){
                        green_player.scrollDown();
                    }
                }
            }else if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){ 
                if(place_piece==true){
                    current_piece.moveLeft();
                }else{
                    if(player==RED){
                        red_player.scrollUp();
                    }else if(player==YELLOW){
                        yellow_player.scrollUp();
                    }else if(player==BLUE){
                        blue_player.scrollUp();
                    }else if(player==GREEN){
                        green_player.scrollUp();
                    }
                }
            }else if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
                if(place_piece==true){
                    current_piece.moveRight();
                }else{
                    if(player==RED){
                        red_player.scrollDown();
                    }else if(player==YELLOW){
                        yellow_player.scrollDown();
                    }else if(player==BLUE){
                        blue_player.scrollDown();
                    }else if(player==GREEN){
                        green_player.scrollDown();
                    }
                }
            }else if(Keyboard.getEventKey() == Keyboard.KEY_RCONTROL && Keyboard.getEventKeyState()){
                if(place_piece==true){
                    current_piece.rotateClockwise();
                }else{
                    if(player==RED){
                        red_player.rotateSelection();
                    }else if(player==YELLOW){
                        yellow_player.rotateSelection();
                    }else if(player==BLUE){
                        blue_player.rotateSelection();
                    }else if(player==GREEN){
                        green_player.rotateSelection();
                    }
                }
            }else if(Keyboard.getEventKey() == Keyboard.KEY_H && Keyboard.getEventKeyState()){
                if(place_piece==true){
                    current_piece.flipHorizontally();
                }else{
                    if(player==RED){
                        red_player.flipSelectionHorizontally();
                    }else if(player==YELLOW){
                        yellow_player.flipSelectionHorizontally();
                    }else if(player==BLUE){
                        blue_player.flipSelectionHorizontally();
                    }else if(player==GREEN){
                        green_player.flipSelectionHorizontally();
                    }
                }
            }else if(Keyboard.getEventKey() == Keyboard.KEY_V && Keyboard.getEventKeyState()){
                if(place_piece==true){
                    current_piece.flipVertically();
                }else{
                    if(player==RED){
                        red_player.flipSelectionVertically();
                    }else if(player==YELLOW){
                        yellow_player.flipSelectionVertically();
                    }else if(player==BLUE){
                        blue_player.flipSelectionVertically();
                    }else if(player==GREEN){
                        green_player.flipSelectionVertically();
                    }
                }
            }else if(Keyboard.getEventKey() == Keyboard.KEY_D && Keyboard.getEventKeyState()){
                if(player==RED){
                    player=YELLOW;
                }else if(player==YELLOW){
                    player=BLUE;
                }else if(player==BLUE){
                    player=GREEN;
                }else if(player==GREEN){
                    player=RED;
                }
            }else if(Keyboard.getEventKey() == Keyboard.KEY_RETURN && Keyboard.getEventKeyState()){
                if(place_piece==false){
                    if(player==RED){
                        current_piece=red_player.popCurrentPiece(); //pop piece out of deck
                    }else if(player==YELLOW){
                        current_piece=yellow_player.popCurrentPiece(); //pop piece out of deck
                    }else if(player==BLUE){
                        current_piece=blue_player.popCurrentPiece(); //pop piece out of deck
                    }else if(player==GREEN){
                        current_piece=green_player.popCurrentPiece(); //pop piece out of deck
                    }
                    place_piece=true; //piece must be placed on board now, can't switch
                }else{ //THEN PLAYER CHOOSES TO FINALIZE PIECE MOVEMENT TO BOARD
                    if(grid.validMove(current_piece)){
                        grid.placePiece(current_piece);
                        //SWITCH PLAYER TURN
                        if(player==RED){
                            player=YELLOW;
                        }else if(player==YELLOW){
                            player=BLUE;
                        }else if(player==BLUE){
                            player=GREEN;
                        }else if(player==GREEN){
                            player=RED;
                        }
                        place_piece=false;
                    }else{
                        System.out.println("Not a valid placement!");
                    }
                }
            }else if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){ 
                //undo choosing a piece to place
                if(place_piece==true){
                    if(player==RED){
                        red_player.insertPiece(current_piece);
                    }else if(player==YELLOW){
                        yellow_player.insertPiece(current_piece);
                    }else if(player==BLUE){
                        blue_player.insertPiece(current_piece);
                    }else if(player==GREEN){
                        green_player.insertPiece(current_piece);
                    }
                    current_piece=new Piece(_1,BlockType.NULLED,1,-1,-1); //prevent null pointer exception in mouse click tests
                    place_piece=false; 
                }else{
                    Display.destroy();
                    System.exit(0);
                }
            }
        }  
    }
    
    public static void main(String[] args){
        new Blokus();
    }
}
