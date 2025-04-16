import java.util.Scanner;

class Proj1 {
  /**
   * Proj1.java
   * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
   *
   * Takes input from the user to calculate a student's overall grade in a
   * course, then to calculate the number of pizzas required for a party.
   */

  public static void main(String[] args) {
    overallGrade(); // Part 1
    System.out.println();
    pizzaParty(); // Part 2
  }

  /**
   * Calculates a student's overall percentage grade in a course with two exams
   * and 3 projects, then prints the result rounded to two decimal places.
   */
  public static void overallGrade() {
    // Declare constants
    final int NUM_PROJECTS = 3;
    final int MAX_POINTS = 200 + 30 * NUM_PROJECTS; // 200 + (30 * 3) = 290

    Scanner input = new Scanner(System.in);
    float averageScore = 0;
    float[] projectScores = new float[NUM_PROJECTS];

    // Get scores for each project
    for (int i = 0; i < NUM_PROJECTS; i++) {
      System.out.print("Enter in Project score #" + i + " (0-30): ");
      projectScores[i] = input.nextFloat();
      averageScore += projectScores[i];
    }
    
    // Get midterm score
    System.out.print("\nEnter the midterm exam score (0-100): ");
    float midtermScore = input.nextFloat();
    averageScore += midtermScore;

    // Get final exam score
    System.out.print("Enter the final exam score (0-100): ");
    float finalScore = input.nextFloat();
    averageScore += finalScore;

    /* I should close the scanner here, but doing so seems to cause a
     * NoSuchElementException in pizzaParty(). So I'll just let it fall out of
     * scope instead. */

    // Calculate average score and print as a percentage
    averageScore = averageScore / MAX_POINTS;
    System.out.printf("Overall grade percentage: %.2f%%%n", averageScore * 100);
  }

  /**
   * Calculates the number of pizzas to be ordered for a Computer Science
   * Gaming Club party given 2-20 attendees, 2 slices per person, and 20 slices
   * per extra large pizza ordered. Then, prints the results.
   */
  public static void pizzaParty() {
    Scanner input = new Scanner(System.in);

    // Declare constants
    final int SLICES_PER_PIZZA = 20, SLICES_PER_PERSON = 2;

    // Get num of people attending, 2-20
    int numPeople = 0;
    while (true) {
      System.out.print("What is the number of people expected at the pizza party? ");
      numPeople = input.nextInt();
      if (numPeople >= 2 && numPeople <= 20) break;
      System.out.println("Input must be between 2 and 20 (inclusive).");
    }

    // Close scanner
    input.close();

    // Do pizza math
    int numSlices = numPeople * SLICES_PER_PERSON;
    int numPizzas = (int) Math.ceil((float) numSlices / SLICES_PER_PIZZA);
    int slicesLeft = numPizzas * SLICES_PER_PIZZA - numSlices;

    // Print results
    System.out.println("\nFor " + numPeople + " people, that would be " + numPizzas + " pizza(s) with each having " + SLICES_PER_PERSON + " slices each. There would be " + slicesLeft + " slice(s) leftover"); 
  }
}
