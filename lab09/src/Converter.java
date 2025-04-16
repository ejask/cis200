import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Converter.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02b)
 *
 * Translates English words to Spanish using a dictionary.
 */

public class Converter {
	private Entry[] dictionary;

	/**
	 * Loads the English-Spanish dictionary.
	 * 
	 * @param fileName the file name of the dictionary
	 */
	public Converter(String fileName) throws IOException {
		Scanner inFile = new Scanner(new File(fileName));
		dictionary = new Entry[Integer.parseInt(inFile.nextLine())];

		for (int i = 0; i < dictionary.length; i++) {
			String[] line = inFile.nextLine().split("\t");
			dictionary[i] = new Entry(line[0], line[1]);
		}

		inFile.close();
	}

	/**
	 * Returns the Spanish translation of an English word.
	 *
	 * @param english the english word to translate
	 * @return the spanish translation, or a message that it couldn't be found
	 */
	public String translate(String english) {
		if (english == "") {
			return "Good-bye! Thanks for using the translator";
		}
		for (Entry e : dictionary) {
			if (e.getEnglish().equalsIgnoreCase(english)) {
				return e.getSpanish();
			}
		}
		return "Word not in dictionary";
	}
}
