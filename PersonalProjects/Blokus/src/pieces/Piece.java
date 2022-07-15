/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pieces;

import static utils.Variables.*;

/**
 *
 * @author Administrator
 */
public class Piece {
    
    private BlockType type=BlockType.GRID_BLOCK;
    private Block[] blocks;//5 blocks at most
    private int x=0;
    private int y=0;
    private int numBlocks=0;
    
    
    //blocks[0] will always be the center block of the piece
    public Piece(int ID,BlockType type,int numBlocks,int x_coord,int y_coord){
        this.type=type;
        this.numBlocks=numBlocks;
        x=x_coord; //*BLOCK_SIZE
        y=y_coord;
        blocks=new Block[numBlocks];
        switch(ID){
            //blocks[0] will always be (x,y)
            case _1:
                blocks[0]=new Block(type,x,y);
                break;
            case _2:
                blocks[0]=new Block(type,x,y);
                blocks[1]=new Block(type,x+1,y);
                break;
            case _3L:
                blocks[0]=new Block(type,x,y);
                blocks[1]=new Block(type,x+1,y);
                blocks[2]=new Block(type,x,y+1);
                break;
            case _3STRAIGHT:
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[2]=new Block(type,x+1,y);
                break;
            case _4STRAIGHT:
                blocks[2]=new Block(type,x-2,y);
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[3]=new Block(type,x+1,y);
                break;
            case _4BOX:
                blocks[0]=new Block(type,x,y);
                blocks[1]=new Block(type,x+1,y);
                blocks[2]=new Block(type,x+1,y+1);
                blocks[3]=new Block(type,x,y+1);
                break;
            case _4L:
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[2]=new Block(type,x+1,y);
                blocks[3]=new Block(type,x+1,y+1);
                break;
            case _4ZIGZAG:
                blocks[1]=new Block(type,x,y-1);
                blocks[0]=new Block(type,x,y);
                blocks[2]=new Block(type,x+1,y);
                blocks[3]=new Block(type,x+1,y+1);
                break;
            case _5ZIGZAG:
                blocks[2]=new Block(type,x-1,y-1);
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[3]=new Block(type,x,y+1);
                blocks[4]=new Block(type,x+1,y+1);
                break;
            case _5U:
                blocks[2]=new Block(type,x-1,y-1);
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[3]=new Block(type,x+1,y);
                blocks[4]=new Block(type,x+1,y-1);
                break;
            case _5S:
                blocks[2]=new Block(type,x-1,y-1);
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[3]=new Block(type,x+1,y);
                blocks[4]=new Block(type,x+1,y+1);
                break;
            case _5LONGL:
                blocks[2]=new Block(type,x-2,y);
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[3]=new Block(type,x+1,y);
                blocks[4]=new Block(type,x+1,y+1);
                break;
            case _5HORN:
                blocks[2]=new Block(type,x-1,y+1);
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[3]=new Block(type,x+1,y);
                blocks[4]=new Block(type,x,y-1);
                break;
            case _5CROSS:
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[2]=new Block(type,x+1,y);
                blocks[3]=new Block(type,x,y+1);
                blocks[4]=new Block(type,x,y-1);
                break;
            case _5RAMP:
                blocks[2]=new Block(type,x-2,y);
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[3]=new Block(type,x,y+1);
                blocks[4]=new Block(type,x+1,y+1);
                break;
            case _5HOUSE:
                blocks[1]=new Block(type,x,y+1);
                blocks[0]=new Block(type,x,y);
                blocks[2]=new Block(type,x,y-1);
                blocks[3]=new Block(type,x+1,y);
                blocks[4]=new Block(type,x+1,y-1);
                break;
            case _5SHORTL:
                blocks[1]=new Block(type,x,y+1);
                blocks[0]=new Block(type,x,y);
                blocks[2]=new Block(type,x,y-1);
                blocks[3]=new Block(type,x+1,y-1);
                blocks[4]=new Block(type,x+2,y-1);
                break;
            case _5STRAIGHT:
                blocks[2]=new Block(type,x-2,y);
                blocks[1]=new Block(type,x-1,y);
                blocks[0]=new Block(type,x,y);
                blocks[3]=new Block(type,x+1,y);
                blocks[4]=new Block(type,x+2,y);
                break;
        }
        //this.grid=grid;
    }
    
    //get the texture type (RED,BLUE,etc.)
    public BlockType getType(){
        return type;
    }
    
    public void draw(){
        for(Block block:blocks){
            block.draw();
        }
    }
    
    
    // TO DO: TEST THIS FUNCTION!!!
    public void changeCoordinates(int x, int y){
        int dx=x-blocks[0].getX();
        int dy=y-blocks[0].getY();
        for(Block block:blocks){
            block.setX(block.getX()+dx);
            block.setY(block.getY()+dy);
        }
    }
    
    
    //TODO: fix movement off of visible grid back onto grid?
    public void moveDown(){
        //boolean undo=false; //if piece goes below edge of board (true) then undo the move
        for(Block block:blocks){
            if(block.getY()+1>23){
                return;
            }
        }
        for(Block block:blocks){
            //if(block.getType()!=BlockType.NULLED){ //don't do anything with the uninitialized block in the piece array
                block.setY(block.getY()+1);
            //}
        }
    }
    
    public void moveUp(){
        for(Block block:blocks){
                if(block.getY()-1<0){
                    return;
                }
        }
        for(Block block:blocks){
            //if(block.getType()!=BlockType.NULLED){
                    block.setY(block.getY()-1);
            //}
        }
    }
    
    public void moveRight(){
        for(Block block:blocks){
            if(block.getX()+1>31){
                return;
            }
        }
        for(Block block:blocks){
            //if(block.getType()!=BlockType.NULLED){
                    block.setX(block.getX()+1);
            //}
        }
    }
    
    public void moveLeft(){
        for(Block block:blocks){
            if(block.getX()-1<0){
                return;
            }
        }
        for(Block block:blocks){
            //if(block.getType()!=BlockType.NULLED){
                block.setX(block.getX()-1);
            //}
        }
    }
    
    private int absoluteValue(int x){
        if(x<0){
            return -x;
        }else{
            return x;
        }
    }
    
    //precondition: blocks[0] must be the center block for all pieces
    public void rotateClockwise(){ //clockwise
        int tempx;
        int tempy;
        int dx; //difference in x coordinates between center piece and current block
        int dy; //difference in y coordinates between center piece and current block
        if(!safeRotateFlipStandoff()){
            System.out.println("Not a guaranteed safe rotation. Too close to edge of board");
            return;
        }
        for(Block block:blocks){
            //save the block coordinates
            tempx=block.getX(); //current x
            tempy=block.getY(); //current y
            //calculate the distance and direction away from the center
            dx=blocks[0].getX()-tempx; //a negative dx value means the current piece is to the right of center, positive=left of center
            dy=blocks[0].getY()-tempy; //a negative dy value means the current piece is above center, positive=below center
            block.setX(blocks[0].getX()+dy);
            block.setY(blocks[0].getY()-dx);
        }
    }
    
    //precondition: blocks[0] must be the center block for all pieces
    public void rotateCounterClockwise(){ //clockwise
        int tempx;
        int tempy;
        int dx; //difference in x coordinates between center piece and current block
        int dy; //difference in y coordinates between center piece and current block
        if(!safeRotateFlipStandoff()){
            System.out.println("Not a guaranteed safe rotation. Too close to edge of board");
            return;
        }
        for(Block block:blocks){
            //save the block coordinates
            tempx=block.getX(); //current x
            tempy=block.getY(); //current y
            //calculate the distance and direction away from the center
            dx=blocks[0].getX()-tempx; //a negative dx value means the current piece is to the right of center, positive=left of center
            dy=blocks[0].getY()-tempy; //a negative dy value means the current piece is above center, positive=below center
            block.setX(blocks[0].getX()-dy);
            block.setY(blocks[0].getY()+dx);
        }
    } 

    //precondition: blocks[0] must be the center block for all pieces
    public void flipHorizontally(){
        int tempx;
        int dx; //difference in x coordinates between center piece and current block
        if(!safeRotateFlipStandoff()){
            System.out.println("Not a guaranteed safe flip. Too close to edge of board");
            return;
        }
        for(Block block:blocks){
            //save the block coordinates
            tempx=block.getX(); //current x
            //calculate the distance and direction away from the center
            dx=blocks[0].getX()-tempx; //a negative dx value means the current piece is to the right of center, positive=left of center
            block.setX(blocks[0].getX()+dx);
        }
    }
    //precondition: blocks[0] must be the center block for all pieces
    public void flipVertically(){
        int tempy;
        int dy; //difference in y coordinates between center piece and current block
        if(!safeRotateFlipStandoff()){
            System.out.println("Not a guaranteed safe flip. Too close to edge of board");
            return;
        }
        for(Block block:blocks){
            //save the block coordinates
            tempy=block.getY(); //current y
            //calculate the distance and direction away from the center
            dy=blocks[0].getY()-tempy; //a negative dy value means the current piece is above center, positive=below center
            block.setY(blocks[0].getY()+dy);
        }
    }
    
    public Block[] getBlocks(){
        return blocks;
    }
    
    public int getNumBlocks(){
        return numBlocks;
    }
    
    private boolean safeRotateFlipStandoff(){
        if(blocks[0].getX()<2||blocks[0].getX()>29||blocks[0].getY()<2||blocks[0].getY()>23){
            return false;
        }
        return true;
    }
}
