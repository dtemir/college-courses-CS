import java.util.Scanner;
public class Order {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);

	System.out.print("Enter the first city: ");
	String city1 = input.nextLine();
	
	System.out.print("Enter the second city: ");
	String city2 = input.nextLine();
	
	System.out.print("Enter the third city: ");
	String city3 = input.nextLine();
	
	System.out.print(cityOrder(city1, city2, city3));
	
}
	public static String cityOrder(String ct1, String ct2, String ct3) {
		if (ct1.length() >= ct2.length()) {
			if (ct2.length() >= ct3.length())
				return ct1 + " " + ct2 + " " + ct3;
			else if (ct1.length() < ct3.length())
				return ct3 + " " + ct1 + " " + ct2;
			else 
				return ct1 + " " + ct3 + " " + ct2;
		} else {
			if (ct1.length() >= ct3.length())
				return ct2 + " " + ct1 + " " + ct3;
			else if (ct2.length() < ct3.length())
				return ct3 + " " + ct2 + " " + ct1;
			else 
				return ct2 + " " + ct3 + " " + ct1;
		}
	}
	

}
