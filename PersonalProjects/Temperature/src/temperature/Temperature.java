package temperature;

import java.util.Scanner;

/**
 *
 * @author Randal Obringer
 */
public class Temperature {

    private static final double F_TO_C = 5.0/9.0;
    private static final double C_TO_F = 9.0/5.0;
    private static final int OFFSET=32;
    
    private static double fToC(double f){
        double c = F_TO_C*(f-OFFSET);
        return c;
    }
    
    private static double cToF(double c){
        double f = (C_TO_F*c)+OFFSET;
        return f;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the temp in Fahrenheit: ");
        double f = input.nextDouble();
        System.out.println("Enter the temp in Celcius: ");
        double c = input.nextDouble();
        
        System.out.println("Converting celcius gives: " + (int) cToF(c));
        System.out.println("Converting fahrenheit gives: " + (int) fToC(f));
    }
    
}
