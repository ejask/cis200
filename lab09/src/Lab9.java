import java.io.IOException;

/**
 * Lab9.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02b)
 *
 * Implements an English to Spanish translator that takes in one word at a time
 * from user input and outputs its Spanish translation. It continues to take in
 * words until the user input is empty.
 */

public class Lab9 {
	public static void main(String[] args) throws IOException {
		// IO view = new IO();
		IO_GUI view = new IO_GUI();
		Converter converter = new Converter("engl2span2.txt");
		String englishWord;
		
		do {
			englishWord = view.getWord();
			view.printTranslation(englishWord, converter.translate(englishWord));
		} while (englishWord.length() != 0);
	}
}
