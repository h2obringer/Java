
package domains;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Randal Obringer
 */
public class GolfHole {
    private int par;
    private int score;
    
    //constructor
    public GolfHole(){
        par=0;
        score=0;
    }
    
    //constructor with arguments
    public GolfHole(int par,int score){
        this.par=par;
        this.score=score;
    }
    
    //mutator
    public void setScore(int score){
        this.score=score;
    }
    
    //accessor
    public int getScore(){
        return score;
    }
    
    //mutator
    public void setPar(int par){
        this.par=par;
    }
    
    //accessor
    public int getPar(){
        return par;
    }
    
    //Returns the overall score of the hole (-1=Birdie,0=par,1=bogey,etc...) See Variables.java
    public int getHoleFinal(){
        return score-par;
    }
    
    //saves a hole to the file
    //if the BufferedWriter errors out, throw an exception to handle it at a higher level in the program.
    public void save_hole(BufferedWriter bw) throws IOException{
        //
        String temp = Integer.toString(par) + " " + Integer.toString(score); //format our variables how we want it to appear in the text file
        bw.write(temp); //write it to the text file
        bw.newLine(); //write a new line to the file
    }
    
    //loads a hole from a file (the file will be created at a higher level. The bufferedReader takes the file as a paramater.
    public void load_hole(BufferedReader input) throws IOException{
        String line = null;
        String[] temp = null;
        if((line = input.readLine()) != null){ //save the line from the file to the line variable and do comparison in same statement
            temp=line.split(" "); //cut apart the string and seperate it into two strings. (needed since two numbers are stored on the same line.
            //temp should now only hold 2 values as 2 seperate array elements. 
            par=Integer.parseInt(temp[0]); //we know that par is first in our file so will be saved as the first element in the array
            score=Integer.parseInt(temp[1]); //we know that score is second in our file so will be save as the second element in the array
        }
    }
    
    
}
