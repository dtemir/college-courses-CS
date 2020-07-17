import java.util.Scanner;
public class Pyramid {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);

	System.out.print("Enter the number of lines: ");
	int NumberLines = input.nextInt();

	for (int lines = 1; lines <= NumberLines; lines++) {
		for (int spaces = NumberLines - lines; spaces >= 1; spaces--) {
			System.out.print("\t");
		}
		for (int FirstHalf = lines; FirstHalf >=2; FirstHalf--) {
			System.out.print(FirstHalf + "\t");
		}
		for (int SecondHalf = 1; SecondHalf <= lines; SecondHalf++) {
			System.out.print(SecondHalf + "\t");
		}
	System.out.println();
	}
}
}




