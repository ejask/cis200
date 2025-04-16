/******************************************************************* 
* Lab2.java 
* Emma Jaskowiec / Thursday 4:30 (Lab 02B)
* 
* Generates a student ID from the first 4 letters of the developer's
* last name and a randomly generated integer from 1-100. Then, given
* user input for the letter grades and credit hours of 3 courses,
* calculates the student's total GPA. Only A, B, C, D, and F are
* accepted as letter grade inputs (case insensitive).
*******************************************************************/

import java.util.Random;
import java.util.Scanner;

public class Lab2 {
	public static void main(String[] args) {
		// Generate student ID
		Random r = new Random();
		String studentID = "Jask" + r.nextInt(1, 101);
		System.out.println("ID: " + studentID);
		
		// Declare/instantiate variables
		int creditHours, totalHours = 0;
		double gradePoints = -1, totalGradePoints = 0;
		Scanner s = new Scanner(System.in);
		final int A = 4, B = 3, C = 2, D = 1, F = 0;
		
		// Class 1 letter grade
		while (gradePoints == -1) {
			System.out.print("Enter the letter grade for Class 1: ");
			char letterGrade = Character.toLowerCase(s.nextLine().trim().charAt(0));
			switch (letterGrade) {
				case 'a': 
					gradePoints = A;
					break;
				case 'b':
					gradePoints = B;
					break;
				case 'c':
					gradePoints = C;
					break;
				case 'd':
					gradePoints = D;
					break;
				case 'f':
					gradePoints = F;
					break;
				default:
					System.out.println("Invalid input; please re-enter grade (A, B, C, D, or F)");
			}
		}
		
		// Class 1 credit hours
		System.out.print("Enter the credit hours for Class 1: ");
		creditHours = Integer.parseInt(s.nextLine().trim());
		totalHours += creditHours;
		
		// Add Class 1 to total
		totalGradePoints += gradePoints * creditHours;
		gradePoints = -1;
		System.out.println();
		
		// Class 2 letter grade
		while (gradePoints == -1) {
			System.out.print("Enter the letter grade for Class 2: ");
			char letterGrade = Character.toLowerCase(s.nextLine().trim().charAt(0));
			switch (letterGrade) {
				case 'a': 
					gradePoints = A;
					break;
				case 'b':
					gradePoints = B;
					break;
				case 'c':
					gradePoints = C;
					break;
				case 'd':
					gradePoints = D;
					break;
				case 'f':
					gradePoints = F;
					break;
				default:
					System.out.println("Invalid input; please re-enter grade (A, B, C, D, or F)");
			}
		}
		
		// Class 2 credit hours
		System.out.print("Enter the credit hours for Class 2: ");
		creditHours = Integer.parseInt(s.nextLine().trim());
		totalHours += creditHours;
		
		// Add Class 2 to total
		totalGradePoints += gradePoints * creditHours;
		gradePoints = -1;
		System.out.println();
		
		// Class 3 letter grade
		while (gradePoints == -1) {
			System.out.print("Enter the letter grade for Class 3: ");
			char letterGrade = Character.toLowerCase(s.nextLine().trim().charAt(0));
			switch (letterGrade) {
				case 'a': 
					gradePoints = A;
					break;
				case 'b':
					gradePoints = B;
					break;
				case 'c':
					gradePoints = C;
					break;
				case 'd':
					gradePoints = D;
					break;
				case 'f':
					gradePoints = F;
					break;
				default:
					System.out.println("Invalid input; please re-enter grade (A, B, C, D, or F)");
			}
		}
		
		// Class 3 credit hours
		System.out.print("Enter the credit hours for Class 3: ");
		creditHours = Integer.parseInt(s.nextLine().trim());
		totalHours += creditHours;
		
		// Add Class 3 to total
		totalGradePoints += gradePoints * creditHours;
		gradePoints = -1;
		System.out.println();
		
		// Print result
		totalGradePoints /= totalHours;
		System.out.printf("GPA is: %.2f", totalGradePoints);
	}
}
