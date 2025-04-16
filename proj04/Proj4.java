/**
 * Proj4.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * EXTRA CREDIT INCLUDED
 *
 * This program prompts the user to log in, then reads in students' names,
 * Wildcat IDs, and exam scores. It then outputs each student's average score
 * and letter grade, as well as the entire class's letter grade distribution
 * and the average percentage grade.
 */

import java.util.Scanner;

public class Proj4 {
  /**
   * Generates a username from the first four letters of my last name and the
   * last four digits of my Wildcat ID.
   *
   * @return The generated username
   */
  public static String generateUsername() {
    final String NAME = "Jaskowiec";
    final String WID = "852146577";

    // build username
    StringBuilder sb = new StringBuilder();
    sb.append(NAME.substring(0, 4));
    sb.append(WID.substring(WID.length() - 4));
    String username = sb.toString();
    
    // print username for grading purposes
    System.out.printf("Username generated: %s%n", username);
    return username;
  }

  /**
   * Prompts the user to log in with a predetermined password and generated
   * username. Exits after a set number of failed attempts.
   *
   * @param scan - A `Scanner` for user input
   * @return `true` if the login was successful, `false` if it wasn't (e.g.
   *   user ran out of attempts)
   */
  public static boolean login(Scanner scan) {
    final int MAX_ATTEMPTS = 3;
    final String PASSWORD = "CIS200$Spr25";
    String username = generateUsername();
    System.out.println(); // spacing

    String inputUsername, inputPassword;
    boolean reEnter = false;

    int attempt = 0;
    while (true) {
      // if at max attempts, exit (return `false`)
      if (attempt >= MAX_ATTEMPTS) {
        System.out.printf("Invalid Username and/or Password entered %d times - Exiting%n", MAX_ATTEMPTS);
        return false;
      }

      // if this is not the first attempt, the user is re-entering
      if (attempt != 0) {
        reEnter = true;
      }

      // get input
      System.out.printf("Please %senter your Username: ", reEnter ? "re-" : "");
      inputUsername = scan.nextLine().trim();
      System.out.printf("Please %senter your Password: ", reEnter ? "re-" : "");
      inputPassword = scan.nextLine().trim();

      // check input
      if (!(username.equalsIgnoreCase(inputUsername) && PASSWORD.equals(inputPassword))) {
        System.out.println("User and/or Password is/are invalid\n");
        attempt++;
      } else {
        return true;
      }
    }
  }

  /**
   * Reads in a student's Wildcat ID (WID) from user input and validates its format.
   *
   * @param scan - A `Scanner` for user input
   * @param studentIndex - The student index for which to read the WID
   * @return The WID
   */
  public static int getWID(Scanner scan, int studentIndex) {
    boolean reEnter = false;

    while (true) {
      // ask for input
      if (!reEnter) {
        System.out.printf("Please enter the WID of Student %d: ", studentIndex + 1);
      } else {
        System.out.print("        Please re-enter WID: ");
      }

      // validate length and type
      String input = scan.nextLine().trim();
      if (input.length() == 9) {
        try {
          return Integer.parseInt(input); // if valid, return
        } catch (NumberFormatException ignore) {/* do nothing... */}
      }

      // if invalid, retry
      System.out.println("        **Invalid WID...must be 9-digits");
      reEnter = true;
    }
  }

  /**
   * Reads in and validates a student's exam scores from user input and
   * calculates the average percentage grade.
   *
   * @param scan - A `Scanner` for user input
   * @param maxScore - The maximum possible exam score, in points
   * @param prompt - The prompt for user input
   * @return The student's average percentage grade
   */
  public static double getScore(Scanner scan, double maxScore, String prompt) {
    boolean reEnter = false;

    // ask for input
    while (true) {
      if (!reEnter) {
        System.out.print(prompt);
      } else {
        System.out.print("        Please re-enter score: ");
      }

      // validate type
      String input = scan.nextLine().trim();
      try {
        double score = Double.parseDouble(input);
        if (score <= maxScore) {
          return score; // if valid, return
        }
      } catch (NumberFormatException ignore) {/* do nothing... */}

      // if invalid, retry
      System.out.println("        **Invalid score...please enter 0-100 only...");
      reEnter = true;
    }
  }

  /**
   * Converts a percentage grade to a letter grade.
   *
   * @param grade - The percentage grade
   * @returns - The corresponding letter grade
   */
  public static char getLetterGrade(double grade) {
    if (grade >= 90.0) {
      return 'A';
    } else if (grade >= 80.0) {
      return 'B';
    } else if (grade >= 70.0) {
      return 'C';
    } else if (grade >= 60.0) {
      return 'D';
    } else if (grade >= 0.0) {
      return 'F';
    } else {
      System.out.println("Not a valid grade.");
      return 'X';
    }
  }

  /**
   * Counts the number of instances of each letter grade in an array
   * 
   * @param letters - The array of letter grades
   * @return An array containing the number of instances of each letter grade
   *    ordered from lowest alphabetical index to highest.
   */
  public static int[] countLetterGrades(char[] letters) {
    final int A = 0, B = 1, C = 2, D = 3, F = 4;
    int[] output = {0, 0, 0, 0, 0};
    for (int i = 0; i < letters.length; i++) {
      switch (letters[i]) {
        case 'A':
          output[A]++;
          break;
        case 'B':
          output[B]++;
          break;
        case 'C':
          output[C]++;
          break;
        case 'D':
          output[D]++;
          break;
        case 'F':
          output[F]++;
          break;
        default:
          System.out.println("Invalid letter grade.");
      }
    }
    return output;
  }

  /**
   * Prompts the user with a yes or no question (appended with "(y/n): ") and
   * returns the answer as a `boolean`
   * 
   * @param scan - A `Scanner` for user input
   * @param prompt - The question to ask
   * @return `true` if the answer is yes; `false` if the answer is no
   */
  public static boolean yesOrNo(Scanner scan, String prompt) {
    while (true) {
      System.out.print(prompt + " (y/n): ");
      String input = scan.nextLine().trim();
      if (input.length() != 1) {
        continue;
      } else {
        switch (input.charAt(0)) {
          case 'Y':
          case 'y':
            System.out.println();
            return true;
          default: return false;
        }
      }
    }
  }

  /**
   * Reads in student data (names, Wildcat IDs, and exam scores) from user
   * input and stores it in the given arrays.
   * 
   * @param scan - A `Scanner` for user input
   * @param maxStudents - The maximum number of students that can be entered
   * @param names - An array to store the students' names
   * @param wids - An array to store the students' Wildcat IDs
   * @param examScores - An array to store the students' average exam scores
   */
  public static void getStudents(Scanner scan, int maxStudents, String[] names, int[] wids, double[] examScores) {
    // declare constants
    final int NUM_EXAMS = 2, MAX_EXAM_SCORE = 50, MAX_FINAL_SCORE = 100;

    for (int i = 0; i < maxStudents; i++) {
      // get name and wid
      System.out.printf("Please enter the name of Student %d: ", i + 1);
      names[i] = scan.nextLine().trim().toUpperCase();
      wids[i] = getWID(scan, i);

      // get course exam scores
      examScores[i] = 0.0; // initialize
      for (int j = 0; j < NUM_EXAMS; j++) {
        examScores[i] += getScore(scan, MAX_EXAM_SCORE, "Please enter score for Exam " + (j + 1) + ": ");
      }

      // get final exam score
      examScores[i] += getScore(scan, MAX_FINAL_SCORE, "Please enter score for Final Exam: ");

      // get exam score average
      examScores[i] = examScores[i] / ((NUM_EXAMS * MAX_EXAM_SCORE) + MAX_FINAL_SCORE) * 100;

      // ask to continue
      if (i == maxStudents - 1) {
        System.out.println("\nMaximum number of students entered.");
      } else {
        if (!yesOrNo(scan, "Do you wish to enter another?")) {
          break;
        }
      }
    }
  }

  /**
   * Evaluates a student's letter grade and prints information on the student
   * (name, WID, average exam score, and letter grade).
   *
   * @param name - The student's name
   * @param wid - The student's Wildcat ID
   * @param examScore - The student's average exam score
   * @return The student's letter grade
   */
  public static char evalStudent(String name, int wid, double examScore) {
    // assume the input is formatted correctly; print reformatted name
    final String[] FIRST_LAST_NAMES = name.split(" ");
    System.out.println(FIRST_LAST_NAMES[1] + ", " + FIRST_LAST_NAMES[0]);

    // print remaining student info
    System.out.println(wid);
    System.out.printf("Exam Percentage: %.1f%%%n", examScore);

    // get the letter grade
    char letterGrade = getLetterGrade(examScore);
    System.out.println("Final Grade: " + letterGrade);
    return letterGrade;
  }

  /**
   * Processes and prints information on each student (name, WID, average exam
   * score, and final letter grade) and on the entire class (total scores, total
   * number/percentages of letter grades, and average exam score).
   *
   * @param scan - A `Scanner` for user input
   * @param names - An array containing the students' names
   * @param wids - An array containing the students' Wildcat IDs
   * @param examScores - An array containing the students' average exam scores
   */
  public static void printResults(Scanner scan, String[] names, int[] wids, double[] examScores) {
    // count valid students
    int numStudents = 0;
    for (int i = 0; i < names.length; i++) {
      if (names[i] != null) {
        numStudents++;
      } else {
        break;
      }
    }

    // print students and get letter grades
    char[] letterGrades = new char[numStudents];
    System.out.println("***Class Results***");
    for (int i = 0; i < numStudents; i++) {
      letterGrades[i] = evalStudent(names[i], wids[i], examScores[i]);
      System.out.println("Press enter to display next student...");
      scan.nextLine();
    }

    System.out.println("***Class Summary***");
    System.out.println("Total number of Scores: " + numStudents);

    // count & display the number of occurences of each letter grade
    final char[] LETTER_GRADES = {'A', 'B', 'C', 'D', 'F'};
    int[] numLetterGrades = countLetterGrades(letterGrades);
    for (int i = 0; i < LETTER_GRADES.length; i++) {
      System.out.printf("               Total number of %c's: %d%n", LETTER_GRADES[i], numLetterGrades[i]);
    }
    System.out.println(); // spacing

    // display percentage of the total of each letter grade
    System.out.println("Individual grade percentages...");
    for (int i = 0; i < LETTER_GRADES.length; i++) {
      System.out.printf("               %c: %.1f%%%n", LETTER_GRADES[i], (numLetterGrades[i] / (double) numStudents) * 100);
    }
    System.out.println(); // spacing

    // display average exam score
    double average = 0.0;
    for (int i = 0; i < numStudents; i++) {
      average += examScores[i];
    }
    average /= numStudents;
    System.out.printf("Average exam percentage = %.1f%%%n", average);
  }

  /**
   * Main
   */
  public static void main(String[] args) {
    final Scanner SCAN = new Scanner(System.in);
    if (!login(SCAN)) {
      return;
    }
    System.out.println(); // spacing
    
    final int MAX_STUDENTS = 60;

    String[] studentNames = new String[MAX_STUDENTS];
    int[] studentWIDs = new int[MAX_STUDENTS];
    double[] examScores = new double[MAX_STUDENTS];

    getStudents(SCAN, MAX_STUDENTS, studentNames, studentWIDs, examScores);
    System.out.println(); // spacing
    printResults(SCAN, studentNames, studentWIDs, examScores);
  }
}
