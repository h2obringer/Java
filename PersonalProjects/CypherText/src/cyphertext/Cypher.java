
package cyphertext;

import java.util.Random;

/**
 *
 * @author Randal Obringer
 * Date last Updated: 01/19/2015
 * Description: Cypher class that provides functions to encrypt and decrypt
 *   a string. The results are randomized so the same cypher isn't used every time.
 *   Special characters will not be encrypted, just alphabetical characters. 
 */
public class Cypher {
    //will hold the alphabet
    private char[] alphabet;
    //will hold the map of the alphabet to a random character
    private char[] randomCypher;
    
    //constructor
    public Cypher(){
        //easier way to create the array.
        alphabet="abcdefghijklmnopqrstuvwxyz".toCharArray();
        //randomCypher=alphabet will assign randomCypher the same memory space. 
        //Manipulating one would manipulate the other (not desired).
        randomCypher="abcdefghijklmnopqrstuvwxyz".toCharArray();
        //jumble the letter ordering
        shuffleArray(randomCypher);
    }
    
    //*******************************************************
    // Function: isAlphabet
    // Paramters: character
    // Description: returns true if the character is in the alphabet
    //   and false if otherwise
    //*******************************************************
    private boolean isAlphabet(char character){
        if(character>='a'&&character<='z'){
            return true;
        }else if(character>='A'&&character<='Z'){
            return true;
        }else{
            return false;
        }
    }
    
    //********************************************************
    // Function: charArrayToString
    // Parameters: charArray - an array of characters
    // Description: converts a character array to a String by continually
    //   appending each successive character in the array to the end of the String
    //********************************************************
    private String charArrayToString(char[] charArray){
        String temp="";
        for(int i=0;i<charArray.length;i++){
            temp+=charArray[i];
        }
        return temp;
    }
    
    //********************************************************
    // Function: shuffleArray
    // Parameters: array - an array of characters
    // Description: the array is essentially passed by reference so the function
    //    does not need to return anything. Modifications will be made directly 
    //    to the array parameter. This function will shuffle around the array
    //    elements.
    //********************************************************
    private void shuffleArray(char[] array){
        Random rnd = new Random();
        //for each array element
        for(int i=0;i<array.length;i++){
            //swap it with another random array element
            int index = rnd.nextInt(array.length);
            //the swap code
            char a = array[i];
            array[i] = array[index];
            array[index]=a;
        }
    }
    
    //accessor
    public String getAlphabet(){
        //outputting the raw character array would produce jibberish on the screen
        return charArrayToString(alphabet);
    }
    
    //mutator
    public void setAlphabet(char[] alphabet){
        this.alphabet=alphabet;
    }
    
    //accessor
    public String getRandomCypher(){
        //outputting the raw character array would produce jibberish on the screen
        return charArrayToString(randomCypher);
    }
    
    //mutator
    public void setRandomCypher(char[] randomCypher){
        this.randomCypher=randomCypher;
    }
    
    //************************************************
    // Function: encrypt
    // Parameter: plainText - the unencrypted message to encrypt
    // Description: encrypts the parameter String by swapping each character
    //   with another mapped character. (alphabet and randomCypher).
    //************************************************
    public String encrypt(String plainText){
        //create the string to return
        String encrypted="";
        //turn the string into an array for easier manipulation
        char[] temp=plainText.toCharArray();
        //for each character in the original string
        for(int i=0;i<temp.length;i++){
            //if the character is in the alphabet
            if(isAlphabet(temp[i])){
                //locate the position of the character in the alphabet
                for(int j=0;j<alphabet.length;j++){
                    //swap lowercase characters with its corresponding cypher character
                    if(temp[i]==alphabet[j]){
                        encrypted+=randomCypher[j];
                    //swap uppcase characters with its corresponding cypher character
                    //'A'-'a' finds the difference in the ascii code values between 
                    //uppercase and lowercase characters. Adding that difference back to
                    //the corresponding alphabet character converts the lowercase character
                    //into uppercase. The (char) typecase is required to return the actual
                    //character and not the ascii number value instead. 
                    }else if(temp[i]==alphabet[j]+('A'-'a')){
                        encrypted+=(char)(randomCypher[j]+('A'-'a'));
                    }
                }
            //if the character is not in the alphabet then do no translate it
            }else{
                encrypted+=temp[i];
            }
        }
        
        return encrypted;
    }
    
    //************************************************
    // Function: decrypt
    // Parameter: cypherText - the encrypted message to decrypt
    // Description: decrypts the parameter String by swapping each character
    //   with another mapped character. (alphabet and randomCypher).
    //************************************************
    public String decrypt(String cypherText){
        //create the string to return
        String decrypted="";
        //turn the string into an array for easier manipulation
        char[] temp=cypherText.toCharArray();
        //for each character in the original string
        for(int i=0;i<temp.length;i++){
            //if the character is in the alphabet
            if(isAlphabet(temp[i])){
                //locate the position of the character in the alphabet
                for(int j=0;j<alphabet.length;j++){
                    //swap lowercase characters with its corresponding alphabet character
                    if(temp[i]==randomCypher[j]){
                        decrypted+=alphabet[j];
                    //swap uppcase characters with its corresponding alphabet character
                    }else if(temp[i]==randomCypher[j]+('A'-'a')){
                        decrypted+=(char)(alphabet[j]+('A'-'a'));
                    }
                }
            //if the character is not in the alphabet then do not translate it
            }else{
                decrypted+=temp[i];
            }
        }
        
        return decrypted;
    }
}
