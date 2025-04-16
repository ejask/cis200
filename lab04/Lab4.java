/******************************************************************* 
 * Lab4.java 
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B) 
 * 
 * This program asks the user for a number of students and exams.
 * Then, it asks for each exam score and prints the average score for
 * each student, the average score for the entire class, and the
 * high/low scores for the entire class.
 *******************************************************************/ 

import java.util.Scanner;

public class Lab4 {
  public static void main(String[] args) {
    final double MIN_SCORE = 5, MAX_SCORE = 50;
    final Scanner scan = new Scanner(System.in);

    System.out.print("How many students are in the class? ");
    int numStudents = getValidInt(scan, 0, Integer.MAX_VALUE);
    System.out.print("How many exams were there per student? ");
    int numExams = getValidInt(scan, 0, Integer.MAX_VALUE);
    System.out.println(); // spacing

    printStats(getStudentScores(scan, numStudents, numExams, MIN_SCORE, MAX_SCORE));
  }

  public static double[][] getStudentScores(Scanner scan, int numStudents, int numExams, double min, double max) {
    double students[][] = new double[numStudents][numExams];
    for (int i = 0; i < students.length; i++) {
      System.out.println("Student " + (i + 1));
      double average = 0;
      for (int j = 0; j < students[i].length; j++) {
        System.out.printf("Enter test %d grade: ", j + 1);
        students[i][j] = getValidDouble(scan, "Re-Enter test " + (j + 1) + " grade: ", min, max);
        average += students[i][j];
      }
      average /= students[i].length;
      System.out.printf("Student average is %.1f%n%n", average);
    }
    return students;
  }

  public static void printStats(double[][] students) {
    double average = 0.0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
    int numScores = 0;
    for (int i = 0; i < students.length; i++) {
      for (int j = 0; j < students[i].length; j++) {
        double n = students[i][j];
        average += n;
        numScores++;
        if (n > max) {
          max = n;
        }
        if (n < min) {
          min = n;
        }
      }
    }
    average /= numScores;
    System.out.printf("The class average is %.1f%n", average);
    System.out.printf("The class high score is %.0f and the low score is %.0f%n", max, min);
  }

  public static int getValidInt(Scanner scan, int min, int max) {
    int output;
    while (true) {
      try {
        output = Integer.parseInt(scan.nextLine().trim());
        if (output <= max && output >= min) {
          return output;
        }
      } catch (NumberFormatException ignore) { }
      System.out.println("bad.");
      System.out.print("try again: ");
    }
  }

  public static double getValidDouble(Scanner scan, String retryPrompt, double min, double max) {
    double output;
    while (true) {
      try {
        output = Double.parseDouble(scan.nextLine().trim());
        if (output <= max && output >= min) {
          return output;
        }
      } catch (NumberFormatException ignore) { }
      System.out.printf("ERROR: Score must be between %.0f and %.0f%n", min, max);
      System.out.print(retryPrompt);
    }
  }
}
