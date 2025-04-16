/*******************************************************************
 * Lab3.java 
 * Emma Jaskowiec / Thurs 4:30 
 * 
 * Reads in class exam scores (by section) from the user, then
 * outputs the highest score, lowest score, and average score of the
 * section. Then, once all sections are entered, outputs the highest
 * score and average score of the entire class.
 *
 * EXTRA CREDIT INCLUDED
 *******************************************************************/ 

import java.util.Scanner;
import java.util.ArrayList;

public class Lab3 {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    char input;
    ArrayList<Double> classScores = new ArrayList<>();

    while (true) {
      System.out.print("Enter section letter: ");
      input = Character.toUpperCase(s.nextLine().trim().charAt(0));
      System.out.println(); // spacing
      ArrayList<Double> sectionScores = getSectionScores(input);
      classScores.addAll(sectionScores);
      System.out.println(); // spacing
      processSectionScores(input, sectionScores); // Print section data

      // Ask to run again
      System.out.print("\nEnter score for another section? (Y or N): ");
      input = Character.toUpperCase(s.nextLine().trim().charAt(0));
      if (input == 'Y') {
        continue;
      } else if (input == 'N') {
        break;
      } else {
        System.out.println("Invalid input");
      }
    }

    System.out.println(); // spacing
    processClassScores(classScores); // Print class data
  }

  public static ArrayList<Double> getSectionScores(char section) {
    Scanner s = new Scanner(System.in);
    ArrayList<Double> scores = new ArrayList<>();

    while (true) {
      System.out.print("Exam score (Enter 999 to end): ");
      String input = s.nextLine().trim();

      try { // Type checking
        double score = Double.parseDouble(input);
        if (score == 999.0) { // Check for sentinal
          break; // Sentinal detected, end loop
        } else if (score >= 0.0 && score <= 100.0) { // Range checking
          scores.add(score);
          continue;
        } // Invalid range
      } catch (NumberFormatException ignore) {
        // Invalid type
      }

      // This line only executes if the input is invalid
      System.out.println("Invalid input. 0-100 or 999 only");
    }

    return scores;
  }

  public static void processSectionScores(char section, ArrayList<Double> scores) {
    double highScore = 0.0, lowScore = 100.0, average = 0.0;
    int numScores = scores.size();
    for (int i = 0; i < numScores; i++) {
      double currentScore = scores.get(i);
      average += currentScore;
      if (currentScore > highScore) {
        highScore = currentScore;
      }
      if (currentScore < lowScore) {
        lowScore = currentScore;
      }
    }
    average /= numScores;

    System.out.println("Section: " + section);
    System.out.println("Number of exams taken: " + numScores);
    System.out.printf("High score: %.2f%n", highScore);
    System.out.printf("Low score: %.2f%n", lowScore);
    System.out.printf("Exam average: %.2f%n", average);
  }

  public static void processClassScores(ArrayList<Double> scores) {
    double highScore = 0.0, average = 0.0;
    int numScores = scores.size();
    for (int i = 0; i < numScores; i++) {
      double currentScore = scores.get(i);
      average += currentScore;
      if (currentScore > highScore) {
        highScore = currentScore;
      }
    }
    average /= numScores;

    System.out.printf("High score: %.2f%n", highScore);
    System.out.printf("Exam average: %.2f%n", average);
  }
}
