import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lab10.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Repeatedly takes in student info (name, username, credit hours, and total gpa
 * points). Then, prints each student's name, email, and gpa (and a separate
 * list of students on the dean's list).
 */
public class Lab10 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Student> students = new ArrayList<>();
		
		String reply = "Y";
		do {
			String name, userName;
			int creditHours, totalPoints;
			try {
				System.out.print("\nEnter the student's name: ");
				name = scan.nextLine().trim();
				if (name == "") {
					throw new IllegalArgumentException("Name is required (Please re-enter)");
				}

				System.out.print("Enter student's USER name: ");
				userName = scan.nextLine();
				if (userName == "") {
					throw new IllegalArgumentException("User name is required (Please re-enter name and user name)");
				}

				System.out.print("Enter student's credit hours for the semester: ");
				while (true) {
					creditHours = Integer.parseInt(scan.nextLine());
					if (creditHours >= 1 && creditHours <= 20) {
						break;
					}
					System.out.print("Credit Hours must be 1-20 (inclusive). Please re-enter: ");
				}

				System.out.print("Enter student's total grade points for the semester: ");
				while (true) {
					totalPoints = Integer.parseInt(scan.nextLine());
					if (totalPoints >= 1 && totalPoints <= 99) {
						break;
					}
					System.out.print("Total Points must be 1-99 (inclusive). Please re-enter: ");
				}

				students.add(new Student(name, userName, creditHours, totalPoints));
				System.out.println("Student added to the arraylist...");
				System.out.print("Add another student? ('Y' or 'N'): ");
				reply = scan.nextLine().trim();
			} catch (NumberFormatException e) {
				System.out.println("Integers only. No chars or doubles...Please re-enter all info");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while (reply.equalsIgnoreCase("Y"));

		for (Student student : students) {
			System.out.println("\n" + student);
		}
		
		System.out.println("\nStudents on the Dean's list");
		for (Student student : students) {
			if (student.calcGPA() > 3.0) {
				System.out.println(student.getName() + "\n");
			}
		}

		Student me = new Student("Emma Jaskowiec", "ejaskowiec");
		students.add(me);
		System.out.printf(
			"YOU have been added to be arraylist...\n%s\nGPA: %.2f",
			me.getEmail(),
			me.calcGPA(18, 54)
		);
	}
}
