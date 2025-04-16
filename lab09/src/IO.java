import java.util.Scanner;

/**
 * IO.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02b)
 *
 * Acts as a CLI for an English to Spanish dictionary.
 */

public class IO {
	private Scanner s;

	public IO() {
		s = new Scanner(System.in);
	}

	/**
	 * Prompts the user for an English word.
	 *
	 * @return the English word entered by the user
	 */
	public String getWord() {
		System.out.print("Enter an English word (press enter to quit): ");
		String word = s.nextLine();
		return word;
	}

	/**
	 * Prints an English word and its Spanish translation.
	 *
	 * @param english the English word
	 * @param spanish the Spanish word
	 */
	public void printTranslation(String english, String spanish) {
		System.out.println(english + ": " + spanish + "\n");
	}
}
