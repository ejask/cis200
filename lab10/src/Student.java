/**
 * Student.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Models a student with a name, user name, credit hours, and total gpa points.
 */
public class Student {
  private String name;
  private String username;
  private int creditHours;
  private int totalPoints;

  public Student(String name, String username) {
    this.name = name;
    this.username = username;
  }

  public Student(
    String name,
    String username,
    int creditHours,
    int totalPoints
  ) {
    this.name = name;
    this.username = username;
    this.creditHours = creditHours;
    this.totalPoints = totalPoints;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.username + "@ksu.edu";
  }

  public double calcGPA() {
    return (double) this.totalPoints / this.creditHours;
  }

  public double calcGPA(int creditHours, int totalPoints) {
    this.creditHours = creditHours;
    this.totalPoints = totalPoints;
    return this.calcGPA();
  }

  @Override
  public String toString() {
    return String.format(
      "%s\n%s\nGPA: %.2f",
      this.getName(),
      this.getEmail(),
      this.calcGPA()
    );
  }
}
