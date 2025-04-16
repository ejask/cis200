/**
 * Entry.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02b)
 *
 * Models an entry in a English to Spanish dictionary, consisting of an English
 * word and a corresponding Spanish word.
 */

public class Entry {
	private String engl;
	private String span;

	/**
	 * Sets up a single English-Spanish entry.
	 *
	 * @param e an English word
	 * @param s the Spanish translation
	 */
	public Entry(String e, String s) {
		this.engl = e;
		this.span = s;
	}

	/**
	 * Returns this entry's English word.
	 *
	 * @return this entry's English word
	 */
	public String getEnglish() {
		return this.engl;
	}

	/**
	 * Returns this entry's Spanish word.
	 *
	 * @return this entry's Spanish word
	 */
	public String getSpanish() {
		return this.span;
	}
}
