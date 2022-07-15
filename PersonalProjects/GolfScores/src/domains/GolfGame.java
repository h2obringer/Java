package domains;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static utils.Variables.*;

/**
 *
 * @author Randal Obringer
 */
public class GolfGame {
    private GolfHole[] holes;
    
    //default constructor
    //if no file is given then
    public GolfGame(){
        holes = new GolfHole[_18_HOLES];//instantiate each element
        for(int i=0;i<_18_HOLES;i++){ //for each element
            holes[i] = new GolfHole(PAR5,PAR5); //instantiate each element to be par 5 with a score of 5
        }
    }
    
    //constructor with an argument
    //allows us to construct this object from a file
    public GolfGame(File file){
        holes = new GolfHole[_18_HOLES]; //instantiate each element
        loadGolfGame(file);//call the load function
    }
    
    //get the score for the hole you input into the function. Used for the combo box answer.
    public String getSingleHoleScore(int i){
        return Integer.toString(holes[i].getScore());
    }
    
    //creates an array of strings. Each string will hold the hole number. Used for the JComboBox (it only takes a String array).
    public String[] getHoleNumbers(){
        String[] holeNumbers = new String[holes.length+1]; //create a string array with the same number of elements as the holes array.Add 1 so we can add additional default menu item
        holeNumbers[0]="-"; //initiate first element as "-"
        for(int i=1;i<holeNumbers.length;i++){ //start at 1 because we already initiated element 0
            holeNumbers[i]=Integer.toString(i); //output the hole number to the string array
        }
        return holeNumbers;
    }
    
    //link this to the graphical button to determine under par, over par, or par performance.
    public int getGameFinal(){
        int totalPar=0; //counts all pars from each hole
        int totalScore=0; //counts scores from each hole
        for(GolfHole hole : holes){ //for each hole (fancy syntax), it reads for each GolfHole in the holes array
            totalPar+=hole.getPar(); //call the hole objects accessor function to get its par value
            totalScore+=hole.getScore(); //and to get its score value as well
        }
        //determine the outcome of all holes based off the calculated totals
        if(totalPar>totalScore){
            return UNDER_PAR; //refer to Variables.java
        }else if(totalPar<totalScore){
            return OVER_PAR; //refer to Variables.java
        }else{
            return PAR; //refer to Variables.java
        }
    }
    
    //self explanatory, link to graphical button
    public int countBirdies(){
        int totalBirdies=0;
        for(GolfHole hole : holes){ //for each hole
            if(hole.getHoleFinal()==BIRDIE){ //see Variables.java, call accessor to get hole performance
                totalBirdies++;
            }
        }
        return totalBirdies;
    }
    
    //self explanatory, link to graphical button
    public int countPars(){
        int totalPars=0;
        for(GolfHole hole : holes){ //for each hole
            if(hole.getHoleFinal()==PAR){ //see Variables.java, call accessor to get hole performance
                totalPars++;
            }
        }
        return totalPars;
    }
    
    //loads all holes and saves them in this class's array
    public void loadGolfGame(File file){
        try {
            BufferedReader input =  new BufferedReader(new FileReader(file)); //create a reader from the file 
            try { //file input can cause errors, we do not want the errors to stop the program. See catch statement below to catch the error (known as an exception)
                for(int i=0;i<_18_HOLES;i++){ //for each hole (the fancy syntax doesn't seem to work here because the hole is nulled before this point)
                    holes[i] = new GolfHole(); //instantiatet he hole
                    holes[i].load_hole(input); //give it values read from the file
                }
            }finally { //if our try was successful, close the BufferedReader
                input.close();
            }
        }catch (IOException e){ //if error occurred
            e.printStackTrace(); //print the line errors and error messages to the command prompt
        }
    }
    
    //save the golf game to a file
    public void saveGolfGame(File file){
        try { //file manipulation can cause errors, see catch statement below to catch those errors
	    if (!file.exists()) { //if the file doesn't already exist
		file.createNewFile(); //then physically create it
	    }
 
	    FileWriter fw = new FileWriter(file.getAbsoluteFile()); //create a means of writing to the file
	    BufferedWriter bw = new BufferedWriter(fw); //same as above
            for(int i=0;i<_18_HOLES;i++){ //for each hole
                holes[i].save_hole(bw); //save the hole to the file
            }
            
	    bw.close(); //close the writer
            System.out.println("Done"); //let us know we are done
 
	} catch (IOException e) { //catch the errors
            e.printStackTrace(); //print error messages to command prompt
	}
    }
    
    
}
