import java.util.Scanner;
public class Password {

	public static void main(String[] args) {
		// Prompt the user to enter a password
		System.out.print("Enter a password: ");
		Scanner input = new Scanner (System.in);
		String password = input.nextLine();

			// Display Valid Password if rules are followed, Invalid Password otherwise
			System.out.println(
				(isValidPassword(password) ? "Valid " : "Invalid ") + "Password");
			}

			public static boolean isValidPassword(String password) {
				final int minLength = 8;	// Valid length of password
				final int minDigits = 2;	// Minimum digits it must contain

				boolean validPassword = 
					isLengthValid(password, minLength) && 
					isOnlyLettersAndDigits(password) &&
					has2Digits(password, minDigits);

				return validPassword;
			}

			public static boolean isLengthValid(String password, int validLength) {
				return password.length() >= validLength;
			}

			public static boolean isOnlyLettersAndDigits(String password) {
				int j = 0; // is used to make sure that ALL of the characters are either digits or letters
				for (int i = 0; i < password.length(); i++) {
					if (Character.isLetterOrDigit(password.charAt(i))) {
						j++;
					}
				}
				if (j == password.length()) { //compares with the length of password to make sure that ALL are either digits or letters
					return true;
				}
				return false;
			}


			public static boolean has2Digits(String password, int digits) {
				int numberOfDigits = 0;
				for (int i = 0; i < password.length(); i++) {
					if (Character.isDigit(password.charAt(i))) {
						numberOfDigits++;
					}
					if (numberOfDigits >= digits) {
						return true;
					}
				}
				return false;
		}
		
	}
	
