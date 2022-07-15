package cyphertext;

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class CypherText {

    public static void main(String[] args) {
        String userInput;
        String encryptedInput;
        String decryptedInput;
        Cypher cypher = new Cypher();
        
        System.out.println("The current alphabet is: " + cypher.getAlphabet());
        System.out.println("The current random cypher is: " + cypher.getRandomCypher());
        
        Scanner input = new Scanner(System.in);
        System.out.println("Input a sentence");
        userInput = input.nextLine();
        encryptedInput=cypher.encrypt(userInput);
        decryptedInput=cypher.decrypt(encryptedInput);
        System.out.println("The encrypted message: " + encryptedInput);
        System.out.println("The decrypted message: " + decryptedInput);
        
        
    }
    
}
