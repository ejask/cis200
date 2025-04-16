/**
 * Proj3.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * This program asks for two points between (0, 0) and (9, 9) from the user,
 * expresses the line that passes through them in point-slope form, and
 * plots the points on a 9x9 plane.
 */

import java.util.Scanner;

public class Proj3 {
  public static void main(String[] args) {
    final Scanner s = new Scanner(System.in);
    do {
      int[][] points = getPoints(s, 2);
      plot9x9(points[0], points[1]);
    } while (yesOrNo(s, "Run program again?"));
    s.close();
  }

  public static int[][] getPoints(Scanner s, int numPoints) {
    final char[] DIMENSIONS = {'x', 'y'};
    int[][] output = new int[numPoints][DIMENSIONS.length];
    boolean reEnter = false;

    // Get inputs
    while (true) {
      String prefix = reEnter ? "Re-e" : "E";
      for (int i = 0; i < numPoints; i++) {
        for (int j = 0; j < DIMENSIONS.length; j++) {
          output[i][j] = getNonNegativeInt(
            s, String.format("%snter %c%d", prefix, DIMENSIONS[j], i + 1)
          );
        }
      }
      if (validatePoints(output)) {
        System.out.println();
        break;
      } else {
        reEnter = true;
        System.out.println();
      }
    }

    return output;
  }

  public static int getNonNegativeInt(Scanner s, String name) {
    System.out.print(name + ": ");
    int output;
    while (true) {
      String inputString = s.nextLine().trim();
      try {
        output = Integer.parseInt(inputString);
        if (output >= 0) {
          return output;
        } else {
          System.out.println("Enter a positive integer");
        }
      } catch (NumberFormatException ignore) {
        System.out.println("Enter an integer");
      }
    }
  }

  public static boolean yesOrNo(Scanner s, String prompt) {
    while (true) {
      System.out.print(prompt + " (y/n) ");
      String input = s.nextLine().trim();
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

  public static boolean validatePoints(int[][] points) {
    // "Type" checking, because Java doesn't have tuples :(
    if (points.length != 2 || points[0].length != 2 || points[1].length != 2) {
      System.out.println("");
      return false;
    }

    // Deconstruct "tuples"
    int x1 = points[0][0], y1 = points[0][1],
        x2 = points[1][0], y2 = points[1][1];

    // Range
    if (
      x1 < 0 || x1 > 9 || y1 < 0 || y1 > 9 ||
      x2 < 0 || x2 > 9 || y2 < 0 || y2 > 9
    ) {
      System.out.println("All points must be between (0,0) and (9,9).");
      return false;
    }

    // Existence of slope and uniqueness
    if (x1 == x2) {
      if (y1 == y2) {
        System.out.println("Point values must be different.");
      } else {
        System.out.println(
          "Line between points must have a slope\n(i.e. x1 and x2 must differ)"
        );
      }
      return false;
    } 

    // If all conditions are met, the points are valid
    return true;
  }

  public static void plot9x9(int[] p1, int[] p2) {
    // Print equation
    System.out.println(pointSlopeForm(p1, p2));

    // Deconstruct "tuples"
    double x1 = p1[0], y1 = p1[1],
           x2 = p2[0], y2 = p2[1];

    // Plot !!!
    for (int i = 9; i >= 0; i--) {
      String row;
      if (i == 0) {
        row = "-------------------";
      } else {
        row = "|                  ";
      }
      for (int j = 0; j <= 9; j++) {
        if ((y1 == i && x1 == j) || (y2 == i && x2 == j)) {
          // if the current index is a point, change the character to '*'
          row = row.substring(0, j * 2) + '*' + row.substring(j * 2 + 1);
        }
      }
      System.out.println(i + " " + row);
    }
    System.out.println("  0 1 2 3 4 5 6 7 8 9\n");
  }

  public static String pointSlopeForm(int[] p1, int[] p2) {
    // Deconstruct "tuples"
    double x1 = p1[0], y1 = p1[1],
           x2 = p2[0], y2 = p2[1];

    double slope = (y2 - y1) / (x2 - x1);
    double yIntercept = y1 - slope * x1;

    // y = slope * x + yIntercept
    return String.format("y = %.2fx + %.2f", slope, yIntercept);
  }
}
