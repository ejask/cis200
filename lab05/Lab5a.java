/*******************************************************************
 * Lab5a.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 * 
 * Asks the user to create a password, then encrypts it and writes
 * it to a file along with several WIDs and associated test scores.
 * Then, asks the user for the password, and if it matches, prints
 * the file data. 
 ******************************************************************/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab5a {
	public static void main(final String[] args) throws IOException {
		final Scanner SCAN = new Scanner(System.in);
		System.out.println(
			"Valid passwords include a minimum of 8 characters that\nincludes at least one digit and at least one of the following\nspecial characters: ! @ # $ *"
		);

		System.out.print("\nPlease enter a Password: ");
		String password = SCAN.nextLine();
		boolean flag = false;	// assume the PW is invalid

		while (!flag) {
			if (password.length() < 8) { // check for valid PW length
				System.out.println("Invalid password - must be at least 8 characters");
			} else {
				flag = true;
			}

			// PW length ok; check for a digit
			if (flag) {
				flag = false;	// assume the PW is invalid, turn 'on' if a char is found
				for (int i = 0; i < password.length(); i++) {
					if (Character.isDigit(password.charAt(i))) {
						flag = true;
						break;
					}
				}

				if (!flag) {
					System.out.println(
						"Invalid password - must contain at least one digit"
					);
				} // end if - digit
			}

			if (flag) { // PW length and digit check ok, check for a special char
				flag = false;	// assume the PW is invalid, turn 'on' if special char is found
				for (int i = 0; i < password.length(); i++) {
					char c = password.charAt(i);
					if (c == '!' || c == '@' || c == '#' || c == '$' || c == '*') {
						flag = true;
						break;
					}
				}

				if (!flag) {
					System.out.println(
						"Invalid password - must contain at least one of the following characters: ! @ # $ *"
					);
				} // end if - special char
			}

			if (!flag) { // PW not valid
				System.out.print("Please enter a valid password: ");
				password = SCAN.nextLine();
			} // end if - re-enter password
		} // end while - validation

		while (true) { // verify password by re-entering
			System.out.print("To validate, please re-enter your password: ");
			String reEnteredPassword = SCAN.nextLine();
			if (reEnteredPassword.equals(password)) {
				break;
			} else {
				System.out.println("\t**Passwords must match**");
			}
		}

		// Encrypt/Write PW to text file here using StringBuilder
		final PrintWriter OUTPUT = new PrintWriter("Lab5a.txt"); 
		final StringBuilder SB = new StringBuilder();
		for (int i = 0; i < password.length(); i++) {
			SB.append((int) password.charAt(i));
			if (i < password.length() - 1) {
				SB.append(',');
			}
		}
		OUTPUT.write(SB.toString());
		OUTPUT.write('\n');

		// Output WID and Test Scores to the Textfile        
		OUTPUT.write(
			"87656786 98\n89273641 67\n82839485 78\n89127364 87\n84567456 78\n87456767 99"
		);
		OUTPUT.close(); // Close the output file        
		System.out.println("File created");

		// -----------------------------------------------------------------------
		System.out.println("\n...\n");

		// Read in password and decrypt
		final Scanner IN_FILE = new Scanner(new File("Lab5a.txt"));
		final String LINE = IN_FILE.nextLine(); // read in line containing the PW
		final String[] PIECES = LINE.split(","); // parse
		final StringBuilder SB2 = new StringBuilder();

		for (int i = 0; i < PIECES.length; i++) {
			SB2.append((char) Integer.parseInt(PIECES[i]));
		}
		final String PASSWORD_2 = new String(SB2);

		final boolean DEBUG = true;
		if (DEBUG) {
			System.out.println("Password = " + PASSWORD_2);
		}

		// Verify user knows the password (one chance)
		// If so, Read in WID and Scores and display, otherwise it exits
		System.out.print("Please enter your password: ");
		final String RE_PASSWORD_2 = SCAN.nextLine();
		if (!PASSWORD_2.equals(RE_PASSWORD_2)) {
			System.out.println(
				"\n**Password not entered correctly. Locking you out of your account.\nPlease call the Help Desk at 785-555-1212 for assistance"
			);
		} else { // Read in WID and Scores and display 
			System.out.println("WID          Score");
			while (IN_FILE.hasNext()) {
				String[] line = IN_FILE.nextLine().split(" ");
				System.out.printf("%s     %s%n", line[0], line[1]);
			}
		} // end else - PW valid   
		SCAN.close();
		IN_FILE.close();
	} // end main
} // end class	
	
