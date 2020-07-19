import java.util.Scanner;
public class Sum {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	
	System.out.print("Enter a number between 100 and 1000: ");
	String number = input.nextLine();
	
	System.out.print("The sum of the digits is " + theSum(number));
	
	}
	
	public static int theSum(String sum) {
	int a = 0;
	for (int i = 0; i < 3; i++) {
		char n = sum.charAt(i); // used to get a particular character from string
		a = a + Character.getNumericValue(n); // used to get the numeric value of a character and add it to the variable
	}
	return a;
	}
}


