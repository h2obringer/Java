/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package palindrome;

/**
 *
 * @author Administrator
 */
public class Palindrome {

    public static boolean isPalindrome(String word){
        System.out.println("The current word is: " + word);
        if(word.length()==1){ //odd base case
            return true;
        }else if(word.length()==2){ //even base case
            if(word.charAt(0)==word.charAt(1)){
                return true;
            }else{
                return false;
            }
        }else{
            if(word.charAt(0)==word.charAt(word.length()-1)){
                return isPalindrome(word.substring(1, word.length()-1));
            }else{
                return false;
            }
        }
    }
    
    public static void printResult(String word){
        if(isPalindrome(word)){
            System.out.println("This word is a palindrome!");
        }else{
            System.out.println("This word is NOT a palindrome");
        }
    }
    
    public static void main(String[] args) {
        printResult("racecar"); //test on a valid palindrome with odd # of characters
        printResult("boob"); //test on a valid palindrome with even # of characters
        printResult("randy"); //test on an invalid palindrome with odd # of characters
        printResult("abcdefghijklmnopqrstuvwxyz"); //test on an invalid palindrome with even # of characters
        printResult("abcdefgfedcba"); //test on a long valid palindrome with odd # of characters
        printResult("racelerecar");
    }
    
}
