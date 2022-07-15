/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

import pieces.Block;
import pieces.BlockType;
import pieces.Piece;
import static utils.Variables.*;

/**
 *
 * @author Administrator
 */
public class BlockGrid {
    private Block[][] blocks;
    private int width;
    private int height;
    
    public BlockGrid(int width, int height){
        this.width=width;
        this.height=height;
        blocks = new Block[width][height];
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                if(x>8&&x<29&&y>1&&y<22){ //GAME BOARD
                    blocks[x][y]=new Block(BlockType.GRID_BLOCK,x,y);
                }else if(x==2 && y==21){
                    blocks[x][y]=new Block(BlockType.ACCEPT,x,y);                
                }else if(x==3 && y==21){
                    blocks[x][y]=new Block(BlockType.UNDO,x,y);                
                }else if(x==4 && y==21){
                    blocks[x][y]=new Block(BlockType.FLIP_HORIZONTALLY,x,y); 
                }else if(x==6 && y==21){
                    blocks[x][y]=new Block(BlockType.FLIP_VERTICALLY,x,y); 
                }else if(x>1&&x<7){ //STAGING AREA
                    blocks[x][y]=new Block(BlockType.STAGE,x,y);
                }else if(x==8&&y==1){ //RED
                    blocks[x][y]=new Block(BlockType.RED_BLOCK,x,y);
                }else if(x==29&&y==1){ //GREEN
                    blocks[x][y]=new Block(BlockType.GREEN_BLOCK,x,y);
                }else if(x==29&&y==22){ //BLUE
                    blocks[x][y]=new Block(BlockType.BLUE_BLOCK,x,y);
                }else if(x==8&&y==22){ //YELLOW
                    blocks[x][y]=new Block(BlockType.YELLOW_BLOCK,x,y);
                }else{
                    blocks[x][y]=new Block(BlockType.BLANK,x,y);
                }
            }
        }
    }
    
    //set the texture of a block on the grid
    public void setAt(int x, int y, BlockType type){
        //blocks[x][y] = new Block(type, x*BLOCK_SIZE, y*BLOCK_SIZE); //used so often creates too many objects (slow)
        blocks[x][y].setType(type);
    }
    
    //return the block at the given coordinate
    public Block getAt(int x, int y){
        return blocks[x][y];
    }
    
    //set all blocks within a rectangle to a certain texture
    public void setSection(int x,int x2, int y,int y2, BlockType type){
        for(int i=x;i<x2;i++){
            for(int j=y;j<y2;j++){
                blocks[i][j].setType(type);
            }
        }
    }
    
    public void draw(){
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                blocks[x][y].draw();
            }
        }
    }
    
    public void placePiece(Piece piece){
        for(Block block:piece.getBlocks()){
            //get the coordinates of each block in the piece and set the BlockGrid Coordinates that 
            //match to the color of the current shape.
            blocks[block.getX()][block.getY()].setType(piece.getType());
        }
    }
    
    public boolean validMove(Piece piece){ //TODO: cover array out of bounds conditions for errors!!!
        BlockType type = piece.getType();
        boolean corner_to_corner=false;
        
        for(Block block:piece.getBlocks()){ //for each block in the piece
            //move invalid if off board or on top of current piece
            if(blockOffBoardOrOnPiece(block)==true){
                System.out.println("Block off board or on another piece");
                return false;
            }
            //move invalid if adjacent to same piece
            if(blockAdjacentIsSame(block)==true){
                System.out.println("Block adjacent to same color");
                return false;
            }
            //if current piece is corner to corner it can be a possiblle valid move.
            //still have to evaluate other blocks though first.
            if(isCornerToCorner(block)==true){
                corner_to_corner=true;
            }
        }
        return corner_to_corner; //if not corner to corner then will return false, otherwise will return true.
    }
    
    private boolean blockOffBoardOrOnPiece(Block block){
        //off board or on top of an exisiting piece
        if(blocks[block.getX()][block.getY()].getType()!=BlockType.GRID_BLOCK){
            return true;
        }
        return false;
    }
    
    private boolean blockAdjacentIsSame(Block block){
        if(blocks[block.getX()+1][block.getY()].getType()==block.getType()){ //evaluate position to the right
            return true;
        }
        if(blocks[block.getX()-1][block.getY()].getType()==block.getType()){ //evaluate position to the left
            return true;
        }
        if(blocks[block.getX()][block.getY()+1].getType()==block.getType()){ //evaluate position below
            return true;
        }
        if(blocks[block.getX()][block.getY()-1].getType()==block.getType()){ //evaluate position above
            return true;
        }
        return false;
    }
    
    //evaluate to true
    private boolean isCornerToCorner(Block block){
        if(blocks[block.getX()+1][block.getY()+1].getType()==block.getType()){
            return true;
        }
        if(blocks[block.getX()-1][block.getY()-1].getType()==block.getType()){
            return true;
        }
        if(blocks[block.getX()+1][block.getY()-1].getType()==block.getType()){
            return true;
        }
        if(blocks[block.getX()-1][block.getY()+1].getType()==block.getType()){
            return true;
        }
        return false;
    }
    
}
