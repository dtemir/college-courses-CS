import java.util.Scanner;
public class Odd {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	
	System.out.print("Enter a string: ");
	String a = input.nextLine();
	String a1 = "";
	
	for (int i = 0, j = 1; i < a.length(); i++, j++) {
		if (i % 2 == 0)
		a1 += a.substring(i, j);
	
	}
	System.out.print(a1);
	}

}
