import java.util.Scanner;
public class Count {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	
	System.out.print("Enter numbers (enter 0 to finish): ");
	int occur = 1; // used to show occurrence 
	int ip = 1; // used to store the new input and compare it with max
	int max = input.nextInt(); // used to store the highest value number
	
	while (ip > 0) {
		ip = input.nextInt();
			if (ip > max) {
				max = ip;
				occur = 1;
			}
			else if (ip == max)
					occur++;
			}
	System.out.println("The largest number is: " + max);
	System.out.println("The occurence count of the largest number is " + occur);
	}
	
}


