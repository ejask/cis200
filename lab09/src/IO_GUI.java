import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * IO_GUI.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02b)
 *
 * Acts as a GUI for an English to Spanish dictionary.
 */

public class IO_GUI {
	private Scanner s;

	public IO_GUI() {
		s = new Scanner(System.in);
	}

	/**
	 * Prompts the user for an English word.
	 *
	 * @return the English word entered by the user
	 */
	public String getWord() {
		return JOptionPane.showInputDialog("Enter an English word (press enter to quit)");
	}

	/**
	 * Prints an English word and its Spanish translation.
	 *
	 * @param english the English word
	 * @param spanish the Spanish word
	 */
	public void printTranslation(String english, String spanish) {
		JOptionPane.showMessageDialog(null, english + ": " + spanish + "\n");
	}
}
