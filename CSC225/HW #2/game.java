import java.util.Scanner;
public class Game {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		System.out.print("scissor(0), rock(1), paper(2): ");
		int choice = input.nextInt();
		
		if (choice>2) {
			System.out.println("Please choose between the given range");;
			System.exit(1);
		}
		
		int x = (int) (Math.random() * ((3 - 0) + 0)) + 0; // Generate Computer's response in the range from 0 to 2
		
		if (choice==x) {
		       if (x==0)
		    	   System.out.println("The computer is scissors." + " You are scissors too." + " It is a draw.");
		       else if (x==1)
		    	   System.out.println("The computer is rock." + " You are rock too." + " It is a draw.");
		       else if (x==2)
		    	   System.out.println("The computer is paper." + " You are paper too." + " It is a draw.");
		}
	    else if (choice==1) {
	       if (x==0) 
	          System.out.println("The computer is scissors." + " You are rock." + " You won.");
	    else if (x==2) 
	            System.out.println("The computer is paper." + " You are rock." + " You lost."); 
	    }
	    else if (choice==2) {
	       if (x==0) 
	       System.out.println("The computer is scissors." + " You are paper." + " You lost."); 
	    else if (x==1) 
	            System.out.println("The computer is rock." + " You are paper." + " You won."); 
	    }
	    else if (choice==0) {
	         if (x==2) 
	         System.out.println("The computer is paper." + " You are scissors." + " You won."); 
	    else if (x==1) 
	            System.out.println("The computer is rock" + " You are scissors." + " You lost."); 
	    }
		
		}
		
	}

