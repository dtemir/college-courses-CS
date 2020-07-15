import java.util.Scanner;
public class SSN {

	public static void main(String[] args) { // This code checks the input by three criteria: length, digits, and dashes (-)
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a SSN: ");
		String ssn = input.next();
		int j = 0; // Counter that used in the loop, it is needed to check if there are exactly 9 digits in SSN
		
		for (int i = 0; i < ssn.length(); i++) { // This loop is used to check if the input is digit numbers
			if (Character.isDigit(ssn.charAt(i)))
				j++;	
		}
			
		char f1 = ssn.charAt(3); // Assign symbols 3 and 6 to chars in order to check if they are dashes (-)
		char f2 = ssn.charAt(6);

		if (f1 == '-' && f2 == '-' && ssn.length() == 11 && j==9) { // Check if there are dashes placed right, the length is 11 symbols, and there are exactly 9 digits
			System.out.println(ssn + " is a valid social security number");
		} else
			System.out.println(ssn + " is an invalid social security number");

	}
}