import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Proj6.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * EXTRA CREDIT INCLUDED
 *
 * Reads in a given file, sorts the lines alphabetically, and determines if each
 * line is a palindrome, printing the results to the console and writing each
 * palindrome to an output file "Results.txt" (along with some stats about the
 * input file).
 */

public class Proj6 {
  /**
   * Repeatedly asks the user for a <code>File</code> path until the
   * <code>File</code> exists.
   *
   * @param scan A <code>Scanner</code> for user input.
   * @return The <code>File</code> at the given path.
   */
  public static File getFileInfo(Scanner scan) {
    while (true) {
      System.out.print("Enter a text file name (include .txt): ");
      File file = new File(scan.nextLine().trim());
      if (file.exists()) {
        return file;
      } else {
        System.out.println("Invalid file name. Try again.");
      }
    }
  }

  /**
   * Reads in lines from the input File into an array of `String`s.
   *
   * @param scan A <code>Scanner</code> for user input.
   * @return A <code>String[]</code> containing the lines of the input
   *         <code>File</code>.
   * @throws IOException If the output <code>File</code> cannot be written to.
   */
  public static String[] readFile(Scanner scan) throws IOException {
    File inFile = getFileInfo(scan);

    // determine the length of the file
    int numLines = 0;
    BufferedReader lineNumReader = new BufferedReader(new FileReader(inFile));
    while (lineNumReader.readLine() != null) {
      numLines++;
    }
    lineNumReader.close();

    // read in the lines
    String[] lines = new String[numLines];
    BufferedReader reader = new BufferedReader(new FileReader(inFile));
    for (int i = 0; i < numLines; i++) {
      lines[i] = reader.readLine();
    }
    reader.close();

    return lines;
  }

  /**
   * Sorts an array of palindromes in alphabetical order in place using bubble
   * sort. It works even if they aren't palindromes :]
   *
   * @param input The array of palindromes to sort.
   */
  public static void sortPalindromes(String[] input) {
    String temp;
    boolean swapped;
    for (int i = 0; i < input.length - 1; i++) {
      swapped = false;
      for (int j = 0; j < input.length - i - 1; j++) {
        if (input[j].toLowerCase().compareTo(input[j + 1].toLowerCase()) > 0) {
          temp = input[j];
          input[j] = input[j + 1];
          input[j + 1] = temp;
          swapped = true;
        }
      }
      if (!swapped)
        break;
    }
  }

  /**
   * Checks if a <code>String</code> is a palindrome, ignoring whitespace,
   * capitalization, and punctuation.
   *
   * @param string The <code>String</code> to check.
   * @return <code>true</code> if the input <code>String</code> is a palindrome,
   *         <code>false</code> otherwise.
   */
  public static boolean isPalindrome(String string) {
    // remove whitespace/punctuation and change to lowercase
    String cleanedString = string.replaceAll("[,.:;!? ]", "").toLowerCase();
    final int LENGTH = cleanedString.length();

    // check if the string is a palindrome
    for (int i = 0; i <= LENGTH / 2 - 1; i++) {
      if (cleanedString.charAt(i) != cleanedString.charAt(LENGTH - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Creates and writes individual <code>Strings</code> to an output
   * <code>File</code>.
   *
   * @param outputFile The output <code>File</code>.
   * @param line       The line to write.
   * @throws IOException If the output <code>File</code> cannot be written to.
   */
  public static void saveLine(File outputFile, String line) throws IOException {
    FileWriter writer = new FileWriter(outputFile, true);
    writer.write(line + "\n");
    writer.close();
  }

  /**
   * Appends final summary stats to the output File.
   *
   * @param outputFile The output <code>File</code>.
   * @param lines      The lines of the input <code>File</code>.
   * @throws IOException If the output <code>File</code> cannot be written to.
   */
  public static void saveResults(File outputFile, String[] lines) throws IOException {
    final int NUM_LINES = lines.length;
    int numChars = 0;
    float averageWords = 0;

    // determine the number of characters and average number of words per line
    for (String line : lines) {
      numChars += line.length();
      averageWords += line.split(" ").length;
    }
    averageWords /= NUM_LINES;

    // append to the output file
    FileWriter writer = new FileWriter(outputFile, true);
    String prefix = "Input file contained ";
    writer.write((String.format(
        "\n%sa total of %d characters\n%sa total of %d lines\n%san average of %.2f words per line\n",
        prefix,
        numChars,
        prefix,
        NUM_LINES,
        prefix,
        averageWords)));
    writer.close();
  }

  /**
   * Displays the required output and writes the results to an output
   * <code>File</code>.
   *
   * @param lines The lines of the input <code>File</code>.
   * @throws IOException If the output <code>File</code> cannot be written to.
   */
  public static void displayResults(String[] lines) throws IOException {
    final String OUTPUT_PATH = "Results.txt";
    File outputFile = new File(OUTPUT_PATH);

    // clear the file
    FileWriter tempWriter = new FileWriter(outputFile);
    tempWriter.write("");
    tempWriter.close();

    // write output
    for (String line : lines) {
      boolean palindrome = isPalindrome(line);
      String output = String.format("%s %s a palindrome.\n",
          line,
          palindrome ? "IS" : "is NOT");
      System.out.print(output);
      if (palindrome)
        saveLine(outputFile, line);
    }
    saveResults(outputFile, lines);
  }

  /**
   * Handles the main flow of the program.
   *
   * @param args The command line arguments (optional).
   * @throws IOException If the output <code>File</code> cannot be written to.
   */
  public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    String[] inputWords = readFile(scan);
    scan.close();
    sortPalindromes(inputWords); // extra credit
    displayResults(inputWords);
  }
}
