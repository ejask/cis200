/**
 * Proj5.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B) 
 * 
 * EXTRA CREDIT INCLUDED
 *
 * A jumble game; repeatedly picks a random word, randomly jumbles it, and asks
 * the user to guess what it is to earn a maximum of 10 points. The user can
 * guess the word, get a hint, get a new word, or quit the game. Once the game
 * is over, the stats are printed to 'Final Stats.txt'.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Proj5 {
  /**
   * Gets a file from the user containing a list of words. The file is assumed
   * to begin with an integer representing the number of words in the file,
   * which is followed by the list of words, each separated by a newline.
   *
   * @param SCAN - Scanner for user input
   */
  public static String[] getWordList(final Scanner SCAN) {
    // get file from user
    File file;
    System.out.print("Enter in text file containing words (Ex: words.txt): ");
    while (true) {
      file = new File(SCAN.nextLine().trim());
      if (file.exists()) {
        break;
      } else {
        System.out.print("File doesn't exist. Please re-enter: ");
      }
    }

    // open file
    BufferedReader read;
    try {
      read = new BufferedReader(new FileReader(file));
    } catch (final FileNotFoundException e) {
      System.out.println(e);
      return null;
    }

    // assume the file is properly formatted
    try {
      final String[] OUTPUT = new String[Integer.parseInt(read.readLine().trim())];
      for (int i = 0; i < OUTPUT.length; i++) {
        OUTPUT[i] = read.readLine().trim();
      }
      read.close(); // close file
      return OUTPUT;
    } catch (final IOException e) {
      System.out.println(e);
      return null;
    }
  }

  /**
   * Jumbles the given word, and if the jumbled word is the same as the
   * original, re-jumbles it.
   * 
   * @param RAND - An instance of `Random`
   * @param WORD - The word to jumble
   * @return The jumbled word
   */
  public static String jumble(final Random RAND, final String WORD) {
    final char CHARS[] = WORD.toCharArray();
    final int WORD_LENGTH = CHARS.length;
    String output;

    do { // swap each letter with a random letter
      for (int i = 0; i < WORD_LENGTH; i++) {
        final int j = RAND.nextInt(WORD_LENGTH);
        final char temp = CHARS[i];
        CHARS[i] = CHARS[j];
        CHARS[j] = temp;
      }
      output = new String(CHARS);
    } while (output.equals(WORD)); // make sure it's actually jumbled

    return output;
  }

  /**
   * Chooses a word from the given list, jumbles it, and asks the user to guess
   * the original word (or, alternatively, get a hint, get a new word, or quit
   * the game). Pushes an array containing the round's statistical information
   * in the format `{points, numGuesses, numHints}` to the given `ArrayList`.
   * 
   * @param WORD_LIST - The list of words to play the round with
   * @param GAME_STATS - The stats for the entire game
   * @return `true` if another round should be played, `false` if not
   */
  public static int[] playRound(
    final Scanner SCAN,
    final Random RAND,
    final String[] WORD_LIST
  ) {
    final boolean DEBUG = false;
    final String WORD = WORD_LIST[RAND.nextInt(WORD_LIST.length)];
    final String JUMBLED_WORD = jumble(RAND, WORD);
    final int[] STATS = new int[]{10, 0, 0}; // {points, numGuesses, numHints}

    if (DEBUG) { // for the graders :]
      System.out.printf("Word = %s%n", WORD);
    }

    while (STATS[0] > 0) { // main game loop
      System.out.printf(
        "Current puzzle: %s%nCurrent points for word: %d%nEnter (g)uess, (n)ew word, (h)int, or (q)uit: ",
        JUMBLED_WORD,
        STATS[0]
      );

      // get a one-character input from the user
      switch (Character.toLowerCase(SCAN.nextLine().trim().charAt(0))) {
        case 'g': // enter a guess
          System.out.print("\nEnter your guess: ");
          STATS[1]++; // increment number of guesses
          if (SCAN.nextLine().trim().equalsIgnoreCase(WORD)) {
            System.out.println("Congratulations! You guessed it!");
            System.out.printf("Score for word: %d%n", STATS[0]);
            return STATS;
          } else {
            System.out.println("Sorry, try again.\n");
            STATS[0]--; // remove one point
          }
          break;
        case 'n': // get a new word
          STATS[0] = 0;
          break;
        case 'h': // get a hint
          // hints are not required to be unique
          final int INDEX = RAND.nextInt(WORD.length());
          System.out.printf(
            "%nThe letter at spot %d is %c%n%n",
            INDEX + 1,
            WORD.charAt(INDEX)
          );
          STATS[0] /= 2; // point penalty
          break;
        case 'q':
          return null; // quit the game
        default:
          System.out.println("Invalid Choice - 'g', 'n' 'h' or 'q' only.\n");
      }
    }
    System.out.println("Word points = 0. Generating a new word");
    return STATS;
  }

  /**
   * Writes the final game stats to a file called "Final Stats.txt".
   * 
   * @param stats - The game stats to write to a file
   */
  public static void writeStatsFile(final ArrayList<int[]> STATS) {
    BufferedWriter write;
    try {
      // create file; if it exists already, delete it and re-create it
      final File FILE = new File("Final Stats.txt");
      if (!FILE.createNewFile()) {
        FILE.delete();
        FILE.createNewFile();
      }
      // open file
      write = new BufferedWriter(new FileWriter(FILE));
    } catch (final IOException e) {
      System.out.println(e);
      return;
    }

    // write to file
    try {
      double averageGuesses = 0;
      for (int i = 0; i < STATS.size(); i++) {
        final int GUESSES = STATS.get(i)[1];
        write.write(String.format(
          "Word %d%nNumber of Guesses: %d%nNumber of Hints: %d%nWord Guessed? %s%n%n",
          i + 1, // word number
          GUESSES,
          STATS.get(i)[2], // num hints
          STATS.get(i)[0] > 0 ? "YES" : "NO" // word guessed?
        ));
        averageGuesses += GUESSES;
      }

      // write average to file
      averageGuesses /= STATS.size();
      write.write(String.format(
        "Avg number of guesses per word: %.2f%n",
        averageGuesses
      ));

      write.close(); // close file
    } catch (final IOException e) {
      System.out.println(e);
      return;
    }
  }

  /**
   * Main
   */
  public static void main(final String[] args) {
    final Scanner SCAN = new Scanner(System.in);
    final Random RAND = new Random();
    final String[] WORD_LIST = getWordList(SCAN); // get word file from user
    final ArrayList<int[]> GAME_STATS = new ArrayList<>();
    int totalPoints = 0;

    System.out.println(); // spacing
    int[] roundStats = playRound(SCAN, RAND, WORD_LIST); // first round
    while (roundStats != null) { // loop until the game is over
      GAME_STATS.add(roundStats);
      totalPoints += roundStats[0];
      System.out.printf("Total score: %d%n%n", totalPoints); // round results
      roundStats = playRound(SCAN, RAND, WORD_LIST); // next round
    }

    // end game
    System.out.printf(
      "%nThanks for Playing! Goodbye!%nFinal score: %d%n",
      totalPoints
    );
    writeStatsFile(GAME_STATS); // extra credit
  }
}
