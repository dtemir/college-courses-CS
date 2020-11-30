import java.util.Scanner;
public class SortedNumbers {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	
	System.out.print("Enter three numbers: ");
	
	double n1 = input.nextDouble();
	double n2 = input.nextDouble();
	double n3 = input.nextDouble();
	
	displaySortedNumbers(n1, n2, n3);

	}
	 public static void displaySortedNumbers (double num1, double num2, double num3) {
			if (num1 >= num2) {
				if (num2 >= num3)
					System.out.print (num1 + " " + num2 + " " + num3);
				else if (num1 < num3)
					System.out.print (num3 + " " + num1 + " " + num2);
				else 
					System.out.print (num1 + " " + num3 + " " + num2);
			} else {
				if (num1 >= num3)
					System.out.print (num2 + " " + num1 + " " + num3);
				else if (num2 < num3)
					System.out.print (num3 + " " + num2 + " " + num1);
				else 
					System.out.print (num2 + " " + num3 + " " + num1);
			}
	 }
}
