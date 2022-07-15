/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package player;

import pieces.BlockType;
import pieces.Piece;
import static org.lwjgl.opengl.GL11.*;
import static utils.Variables.*;
import utils.Vector;

/**
 *
 * @author Administrator
 */
public class Deck {
    private BlockType type = BlockType.GRID_BLOCK;
    private Vector<Piece> pieces = new Vector<>();
    private int scrollValue=0; //TODO GET RID OF
    private int above_selection; //piece above selection for viewing 
    private int selection; //the current piece in the staged deck that is selected to be played. Rendered in center of stage
    private int below_selection; //piece below selection for viewing
    public static final int DEFAULT_X = 4;
    public static final int DEFAULT_Y = 10;
    
    //staging area: 0<y<24, 1<x<7
    public Deck(BlockType type){
        selection=1; //second element in the vector will be up for play staged at the center of the staging area
        above_selection=selection-1;
        below_selection=selection+1;
        this.type=type;
        pieces.insert(new Piece(_1,type,1,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_2,type,2,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_3L,type,3,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_3STRAIGHT,type,3,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_4STRAIGHT,type,4,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_4BOX,type,4,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_4L,type,4,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_4ZIGZAG,type,4,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5ZIGZAG,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5U,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5S,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5LONGL,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5HORN,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5CROSS,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5RAMP,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5HOUSE,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5SHORTL,type,5,DEFAULT_X,DEFAULT_Y));
        pieces.insert(new Piece(_5STRAIGHT,type,5,DEFAULT_X,DEFAULT_Y));
    }
    
    public void draw(){
        glPushMatrix();
        if(above_selection>=0){
            glTranslatef(0,-5*BLOCK_SIZE,0); //translate to center the piece at (4,5) //starts at (4,10)
            pieces.itemAt(above_selection).draw();
        }
        glPopMatrix();
        
        glPushMatrix();
        //element position is checked for errors already in scrollUp and scrollDown. No further error checking needed
        pieces.itemAt(selection).draw(); //draw selection piece at (4,10) //already initiated to (4,10)
        glPopMatrix();
        
        glPushMatrix();
        if(below_selection<=pieces.getSize()-1){
            glTranslatef(0,5*BLOCK_SIZE,0); //translate to center the piece at (4,15) //starts at (4,10)
            pieces.itemAt(below_selection).draw();
        }
        glPopMatrix();
    }
    
    public void scrollDown(){
        selection++; 
        
        if(selection>pieces.getSize()-1){
            selection=pieces.getSize()-1;
        }
        above_selection=selection-1;
        below_selection=selection+1;
    }
    
    public void scrollUp(){
        selection--;
        if(selection<0){
            selection=0;
        }
        above_selection=selection-1;
        below_selection=selection+1;
    }
    
    //used for mouse testing. Dont remove until mouse actually clicked on piece.
    public Piece getCurrentPiece(){
        return pieces.itemAt(selection);
    }
    
    //essentially remove piece from deck and play it.
    public Piece popCurrentPiece(){
        Piece piece = pieces.pop(selection);
        
        if(selection>pieces.getSize()-1){ //if last piece selected, it would be popped off but 
            //selection wouldn't update, leaving it out of array bounds
            selection=pieces.getSize()-1;
        }
        above_selection=selection-1;
        below_selection=selection+1;
        
        //piece.changeCoordinates(19, 11); //change coordinate of piece to place at center of board
        return piece;
    }
    
    public void insertPiece(Piece piece){
        piece.changeCoordinates(DEFAULT_X, DEFAULT_Y);
        pieces.insert(piece);
    }
    
    public void rotateSelection(){
        pieces.itemAt(selection).rotateClockwise();
    }
    
    public void flipSelectionVertically(){
        pieces.itemAt(selection).flipVertically();
    }
    
    public void flipSelectionHorizontally(){
        pieces.itemAt(selection).flipHorizontally();
    }
    
    public Vector<Piece> getPieces(){
        return pieces;
    }
}
