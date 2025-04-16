import java.util.Scanner;

/**
 * CircleApp.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 * 
 * EXTRA CREDIT INCLUDED
 * 
 * Creates three Circles using various methods of construction (and
 * initialization) and prints them to the console. Then, asks for user input to
 * create one more circle and checks if the new circle is equal to any of the
 * previously created Circles.
 */

public class CircleApp {
  public static void main(String[] args) {
    Circle[] circleArray = { new Circle(), new Circle(), new Circle() };
    circleArray[2].setRadius("3");
    circleArray[2].setColor("Wildcat Purple");

    Scanner scan = new Scanner(System.in);
    System.out.print("Enter in the Color for Circle 2: ");
    circleArray[1].setColor(scan.nextLine().trim());
    System.out.print("Enter in the Radius for Circle 2: ");
    circleArray[1].setRadius(scan.nextLine().trim());

    for (int i = 0; i < circleArray.length; i++) {
      System.out.printf("\nCircle %d:\n%s\n", i + 1, circleArray[i]);
    }

    Circle anotherCircle = new Circle();
    System.out.print("\nEnter in the Color for another Circle: ");
    anotherCircle.setColor(scan.nextLine().trim());
    System.out.print("Enter in the Radius for another Circle: ");
    anotherCircle.setRadius(scan.nextLine().trim());
    scan.close();

    boolean circleHasMatch = false;
    for (int i = 0; i < circleArray.length; i++) {
      if (circleArray[i].equals(anotherCircle)) {
        circleHasMatch = true;
        break;
      }
    }
    if (circleHasMatch) {
      System.out.println("\nEntered circle equals a created object in the array.");
    } else {
      System.out.println("\nEntered circle does NOT equal a created object in the array.");
    }
  }
}
