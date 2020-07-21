import java.util.Scanner;

public class Conversion {
	
	public static void main(String[] args) {
		
		// Scanner object
		Scanner input = new Scanner (System.in);
		// Prompt to enter a number
		System.out.print("Enter a number in pounds: ");
		// Assigning the number
		double pounds = input.nextDouble();
		// Calculating and assigning the result
		double killograms = pounds * 0.454;
	
		System.out.println(pounds + " pounds is " + killograms + " killograms.");
		
	}

}
