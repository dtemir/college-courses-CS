import java.util.Scanner;
public class Cost {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		System.out.print("Enter weight and price for package 1: ");
		double weight1 = input.nextDouble();
		double price1 = input.nextDouble(); 
		
		System.out.print("Enter weight and price for package 2: ");
		double weight2 = input.nextDouble();
		double price2 = input.nextDouble();
		
		double rice1 = weight1/price1;
		double rice2 = weight2/price2;
		
		if (rice1 > rice2)
			System.out.println("Package 1 has a better price.");
		else if (rice1 < rice2)
			System.out.println("Package 2 has a better price.");
		else
			System.out.println("Two packages have the same price.");
	}

}
