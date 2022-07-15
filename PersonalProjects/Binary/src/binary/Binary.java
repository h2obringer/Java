

package binary;

import java.util.Scanner;

/**
 *
 * @author Randal Obringer
 * Date Last Modified: 13/1/2015
 * Description: The user must enter a binary number. The program will tell the user if a binary number was not entered.
 * If the input is accepted then the user will be told how many 1s and 0s were in the number.
 * The user then has the choice to continue the program and enter a different number
 */
public class Binary {
    public static void main(String[] args) {
        boolean binary = true; //is the input a binary number?
        boolean quit = false; //continue the program or quit?
        String temp; //stores user input
        int count0s = 0; //count # of 0s in user input
        int count1s = 0; //count # of 1s in user input
        
        Scanner input = new Scanner(System.in); //read user input from the command prompt
        
        while(quit!=true){ //run the loop until the user wants to end the program
            System.out.println("Please insert a binary number:");
            temp=input.nextLine(); //receive user input
            for(int i=0;i<temp.length();i++){ //for every character within the user input string
                if(temp.charAt(i)=='0'){ //if 0 add to count
                    count0s++;
                }else if(temp.charAt(i)=='1'){ //if 1 add to count
                    count1s++;
                }else{ //otherwise
                    binary=false; //not a binary number
                    break;        //no need to continue if we know the input isn't a binary number
                }
            }
            if(binary==true){ //if user input was a binary number
                //Then print the # of 0s and 1s in the number.
                System.out.println("The user input the binary number: " + temp);
                System.out.println("There are " + count0s + " zeros in this number");
                System.out.println("There are " + count1s + " ones in this number");
            }else{ //otherwise output error message
                System.out.println("This is not a binary number");
            }
            
            do{ //enter a loop that forces the user to input a correct response
                System.out.println("");
                System.out.println("Try again? (Y/N)");
                temp=input.nextLine(); //recieve user input
                if(temp.charAt(0)=='Y' || temp.charAt(0)=='y'){ //allows the user to input "Yes" or "yes" or "Y" or "y" (which are all viable answers that we would expect
                    quit=false; //we do not want to end the program because the user wants to continue
                    //if we are going to continue running this program then we must reset the 1's and 0's counts for the next binary number that is input
                    count1s=0;
                    count0s=0;
                }else if(temp.charAt(0)=='N'|| temp.charAt(0)=='n'){ //allows the user to input "No" or "no" or "N" or "n" (which are all viable answers that we would expect
                    quit=true; //we want to end the program because the user does not want to continue
                }else{//otherwise the user entered jibberish. 
                    System.out.println("Huh?"); //let the user know they entered jibberish
                    temp="?";//set a flag to continue the loop
                }
            }while(temp.charAt(0)=='?'); //we will continue this loop as long as we keep getting jibberish
        }
    }
    
}
