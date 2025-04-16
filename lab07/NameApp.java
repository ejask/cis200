import java.util.Scanner;

/* 
 * NameApp.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 * 
 * Tests the Name class. First asks the user for their name and age, then
 * contructs four Names with the input data (using each contructor once) and
 * displays the full name and age of each. Then, asks the user to change the age
 * of the third Name and displays the result. Finally, displays the initials and
 * formatted full names of the second and fourth Names.
 */
public class NameApp {
  public static void main(String[] args) {
    // user input
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your full name (first middle and last name): ");
    String name = scan.nextLine();
    System.out.print("Enter your age: ");
    int age;
    while (true) {
      age = scan.nextInt();
      if (age >= Name.MIN_AGE && age <= Name.MAX_AGE) {
        break;
      } else {
        System.out.printf("Age should be in range %d-%d (inclusive)\n", Name.MIN_AGE, Name.MAX_AGE);
      }
    }
    System.out.println();

    // construct objects
    Name[] names = {
        new Name(),
        new Name(99),
        new Name(name),
        new Name(name, age)
    };

    // display output
    for (int i = 0; i < names.length; i++) {
      Name n = names[i];
      System.out.printf("Name %d: %s\nAge: %d\n\n", i + 1, n.getFullName(), n.getAge());
    }

    // change age of name 3
    System.out.print("Please enter the new age of Name 3: ");
    while (true) {
      age = scan.nextInt();
      if (age >= Name.MIN_AGE && age <= Name.MAX_AGE) {
        break;
      } else {
        System.out.printf("Age should be in range %d-%d (inclusive)\n", Name.MIN_AGE, Name.MAX_AGE);
      }
    }
    scan.close();
    System.out.println("\nChanging the age of name 3...");
    names[2].setAge(age);
    System.out.printf("Name 3: %s\nAge: %d\n", names[2].getFullName(), names[2].getAge());

    // display initials and rearraged names
    for (int i = names.length - 1; i > 0; i -= 2) {
      Name n = names[i];
      n.setFirstInitial();
      n.setMiddleInitial();
      n.setLastInitial();
      System.out.printf("\nInitials of name %d: %c.%c.%c.\n%s\n", i + 1, n.getFirstInitial(), n.getMiddleInitial(), n.getLastInitial(), n.rearrage() );
    }
  }
}
