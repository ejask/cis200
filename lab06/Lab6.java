import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Lab 6d - Student Starter File
 * 	...Add YOUR Documentation Info Here
 *
 */
public class Lab6 {
  /**
   * main outline for method execution
   *
   * @param args A string array from the command line
   * @throws IOException if file doesn't exist
   */
  public static void main(String[] args) throws IOException {
    // Open input stream from a file of numbers
    final String INPUT_PATH = "Lab6_nums2.txt";
    final Scanner SCAN_FILE = new Scanner(new File(INPUT_PATH)); 

    int numValues = 0;
    int overallSum = 0;
    int line = 0;
    
    while (SCAN_FILE.hasNext()) { // Read in all values from the file
      line++; // increment line number
      
      // call method to convert line of numbers into an int array
      final int[] VALUES = buildArray(SCAN_FILE.nextLine());
      numValues += VALUES.length;
      
      // call method to sum all of the numbers in the array
      final int SUM = sumArray(VALUES);
      overallSum += SUM;
      
      // Display the sum of the line of numbers
      System.out.printf(
        "Line %d - Number of Values: %d\t Sum: %d\n",
        line,
        VALUES.length,
        SUM
      );
    } // end while
    System.out.println(); // spacing
    SCAN_FILE.close(); // close the file
    writeToScreen(numValues, overallSum); // Display info to the screen
    System.out.println(); // spacing
    
    // Write the stats to a file
    final Scanner SCAN_IN = new Scanner(System.in);
    System.out.print("Enter filename to write to: ");
    final String OUTPUT_PATH = SCAN_IN.nextLine().trim();
    SCAN_IN.close();
    writeToFile(OUTPUT_PATH, numValues, overallSum);
  } // end main

  /**
   * buildArray creates an array out of numbers in a string
   *
   * @param line A String of whole numbers separated by spaces
   * @return An int array holding all the numbers from line
   */
  public static int[] buildArray(String line) {
    String[] pieces = line.split(" ");
    int[] output = new int[pieces.length];
    for (int i = 0; i < pieces.length; i++) {
      output[i] = Integer.parseInt(pieces[i]);
    }
    return output;
  } // end buildArray

  /**
   * sumArray returns the sum of all numbers in an array
   *
   * @param nums An int array
   * @return The sum of all numbers in nums
   */
  public static int sumArray(int[] nums) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    return sum;
  } // end sumArray

  /**
   * writeToScreen writes stats info to the screen
   *
   * @param numValues An integer holding the number of values in the file
   * @param overallSum An integer holding the sum of all numbers in the file
   */
  public static void writeToScreen(int numValues, int overallSum) {
    System.out.printf(
      "Number of values in the file: %d\nAverage of all values: %.2f\n",
      numValues,
      overallSum / (double) numValues
    );
  } // end writeToScreen

  /**
   * writeToFile writes stats info to a file
   *
   * @param fileName String holding the name of the file to write to
   * @param numValues An integer holding the number of values in the file
   * @param overallSum An integer holding the sum of all numbers in the file
   * @throws IOException if file doesn't exist
   */
  public static void writeToFile(
    String fileName,
    int numValues,
    int overallSum
  ) throws IOException {
    final FileWriter WRITE = new FileWriter(fileName);
    System.out.printf("%s created...", fileName);
    WRITE.write(String.format(
      "Number of values in the file: %d\nAverage of all values: %.2f\n",
      numValues,
      overallSum / (double) numValues
    ));
    WRITE.close();
  } // end writeToFile

} // end class
