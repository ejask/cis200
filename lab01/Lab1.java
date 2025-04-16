/**
 * Lab1.java
 * Emma Jaskowiec / Thurs 4:30
 *
 * This program gets two numbers as doubles from the user
 * then displays their average to two decimal places
 * OR gets a tip percentage and bill amount from the user
 * and displays the amount the user should tip followed by
 * the total bill amount.
 */

import java.util.Scanner;

public class Lab1 {
  public static void main(String[] args) {
    part2();
  }

  public static void part1() {
    Scanner scan = new Scanner(System.in);

    String input;
    double average;

    System.out.print("Enter first number: ");
    input = scan.nextLine();
    double num1 = Double.parseDouble(input);

    System.out.print("Enter second number: ");
    input = scan.nextLine();
    double num2 = Double.parseDouble(input);

    average = (num1 + num2) / 2;
    System.out.println("The average is " + average);

    String output = String.format("The average is %.2f\n", average);
    System.out.printf(output);
  }

  public static void part2() {
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter the Tip % to be used as a decimal (Ex: 18% would be 0.18): ");
    double percentage = Double.parseDouble(scan.nextLine());
    System.out.print("Enter total bill amount: ");
    double bill = Double.parseDouble(scan.nextLine());

    double tip = bill * percentage;
    double total = bill + tip;

    System.out.printf("Total tip amount = $%.2f%n", tip);
    System.out.printf("Total bill amount = $%.2f%n", total);
  }
}
