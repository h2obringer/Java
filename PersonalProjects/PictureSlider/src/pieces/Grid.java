/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import java.util.Random;
import static utils.Variables.*;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Obringer
 */
public class Grid {
    Piece _1,_2,_3,_4,_5,_6,_7,_8,_9,temp;
    Piece[] grid = new Piece[9];
    float move_displacement;
    float speed;
    boolean in_progress;
    int num=-1; //temporary number
    int blank = 6; //grid index for the blank block
    
    public Grid(){
        _1 = new Piece(-PLACEMENT,PLACEMENT,0);
        _2 = new Piece(ZERO,PLACEMENT,1);
        _3 = new Piece(PLACEMENT,PLACEMENT,2);
        _4 = new Piece(-PLACEMENT,ZERO,3);
        _5 = new Piece(ZERO,ZERO,4);
        _6 = new Piece(PLACEMENT,ZERO,5);
        _7 = new Piece(-PLACEMENT,-PLACEMENT,6);
        _8 = new Piece(ZERO,-PLACEMENT,7);
        _9 = new Piece(PLACEMENT,-PLACEMENT,8);
        grid[0]=_1;
        grid[1]=_2;
        grid[2]=_3;
        grid[3]=_4;
        grid[4]=_5;
        grid[5]=_6;
        grid[6]=_7;
        grid[7]=_8;
        grid[8]=_9;
        speed=MAX_SPEED;
    }
    
    public void draw(){
        for(int i=0;i<9;++i){
            glPushMatrix();
            glTranslatef(grid[i].get_x_displacement(),grid[i].get_y_displacement(),0);
            glTranslatef(grid[i].get_x(),grid[i].get_y(),grid[i].get_z());
            grid[i].draw();
            glPopMatrix();
        }
    }
    
    public boolean check_win(){
        if((_1.getCurrentGridPosition()==_1.getStartGridPosition())&&
                (_2.getCurrentGridPosition()==_2.getStartGridPosition())&&
                (_3.getCurrentGridPosition()==_3.getStartGridPosition())&&
                (_4.getCurrentGridPosition()==_4.getStartGridPosition())&&
                (_5.getCurrentGridPosition()==_5.getStartGridPosition())&&
                (_6.getCurrentGridPosition()==_6.getStartGridPosition())&&
                (_7.getCurrentGridPosition()==_7.getStartGridPosition())&&
                (_8.getCurrentGridPosition()==_8.getStartGridPosition())&&
                (_9.getCurrentGridPosition()==_9.getStartGridPosition())){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean get_in_progress(){
        return in_progress;
    }
    
    public void mutate_in_progress(boolean progress){
        in_progress=progress;
    }
    
    public void scramble(){
        int[] moves = new int[SCRAMBLE];
        Random generator = new Random();
        for(int i=ZERO;i<SCRAMBLE;i++){
          moves[i]=generator.nextInt()%TOTAL_MOVES;
        }
        for(int i=ZERO;i<SCRAMBLE;i++){
            switch(moves[i]){
                case 0:
                    move_right();
                    break;
                case 1:
                    move_left();
                    break;
                case 2:
                    move_up();
                    break;
                case 3:
                    move_down();
                    break;
            }
        }
    }
    
    //CHANGE THESE!
    public void increase_move_displacement(int direction){
        move_displacement+=speed;
        if(direction==RIGHT){ 
            if((blank!=0)&&(blank!=3)&&(blank!=6)){
                grid[blank-1].set_x_displacement(move_displacement);
            }
        }else if(direction==LEFT){
            if((blank!=2)&&(blank!=5)&&(blank!=8)){
                grid[blank+1].set_x_displacement(-move_displacement);
            }
        }else if(direction==UP){
            if((blank!=6)&&(blank!=7)&&(blank!=8)){
                grid[blank+3].set_y_displacement(move_displacement);
            }
        }else if(direction==DOWN){
            if((blank!=0)&&(blank!=1)&&(blank!=2)){ 
                grid[blank-3].set_y_displacement(-move_displacement);
            }
        }
    }

    public void reset_move_displacement() {
        move_displacement=ZERO;
        for(int i=0;i<9;++i){
            grid[i].reset_displacement();
        }
    }

    public float get_move_displacement() {
        return move_displacement;
    }

    public void move_right() {
        if((blank!=0)&&(blank!=3)&&(blank!=6)){ //if the blank block isn't on the left edge of the grid then...
            //swap the x values of the blank block and the one left of the blank block.
            num=grid[blank].getCurrentGridPosition(); 
            grid[blank].mutateCurrentGridPosition(grid[blank-1].getCurrentGridPosition());
            grid[blank-1].mutateCurrentGridPosition(num);
            blank-=1; //update the new position of the blank block.
        }
    }

    public void move_left() {
        if((blank!=2)&&(blank!=5)&&(blank!=8)){ //if the blank block isn't on the right edge of the grid then...
            //swap the x values of the blank block and the one right of the blank block.
            num=grid[blank].getCurrentGridPosition();
            grid[blank].mutateCurrentGridPosition(grid[blank+1].getCurrentGridPosition());
            grid[blank+1].mutateCurrentGridPosition(num);
            blank+=1; //update the new postion of the blank block.
        }
    }

    public void move_up() {
        if((blank!=6)&&(blank!=7)&&(blank!=8)){ //if the blank block isn't on the bottom edge of the grid then...
            //swap the y values of the blank block and the one below the blank block.
            num=grid[blank].getCurrentGridPosition();
            grid[blank].mutateCurrentGridPosition(grid[blank+3].getCurrentGridPosition());
            grid[blank+3].mutateCurrentGridPosition(num);
            blank+=3;
        }
    }

    public void move_down() {
        if((blank!=0)&&(blank!=1)&&(blank!=2)){ //if the blank block isn't on the bottom edge of the grid then...
            //swap the y values of the blank block and the one below the blank block.
            num=grid[blank].getCurrentGridPosition();
            grid[blank].mutateCurrentGridPosition(grid[blank-3].getCurrentGridPosition()); //this assignment is also changing temp!!!!
            grid[blank-3].mutateCurrentGridPosition(num);
            blank-=3;
        }
        
        //problem with startgridposition and currentgridpositions and how they are drawn and being swapped????
    }
    
    public String getPiecePositions(){
        String temp = "";
        for(Piece p:grid){
            temp+=Integer.toString(p.getCurrentGridPosition());
        }
        return temp;
    }
}
