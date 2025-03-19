import java.util.Scanner;

/**
 * MortgageApp.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * TODO: description
 */
public class MortgageApp {
  public static void main(String[] args) {
    final boolean DEBUG = true;
    final int MAX_LOANS = 10;
    Mortgage[] loans = new Mortgage[MAX_LOANS];
    Scanner scan = new Scanner(System.in);
    System.out.println(
      "Welcome to the Big12 Americana Bank program\ncreated by Emma Jaskowiec"
    );
    int index = 0;

    // main application loop
    while (true) {
      if (index == MAX_LOANS) {
        return; // max number of mortgages reached
      }

      System.out.print(
        "\nPlease choose from the following choices below:\n\t1) Promotional Loan (preset loan amount, rate, term)\n\t2) Unique Loan (enter in loan values)\n\t3) Quit (Exit the program)\n\tPlease enter your selection (1-3): "
      );

      // input loop
      char choice;
      while (true) {
        String input = scan.nextLine().trim();
        if (input.length() == 1) {
          choice = input.charAt(0);
          if (choice == '1' || choice == '2' || choice == '3') {
            break;
          }
        }
        System.out.print("\t\tInvalid Choice. Please select 1, 2, or 3: ");
      }
      System.out.println();

      switch (choice) {
        case '1': // promotional loan
          loans[index] = new Mortgage(
            Mortgage.PROMO_LOAN_AMOUNT,
            Mortgage.PROMO_INTEREST,
            Mortgage.PROMO_TERM
          );
          System.out.println("\nPROMOTIONAL LOAN:");
          System.out.println(loans[index]);
          break;
        case '2': // unique loan
          loans[index] = new Mortgage();
          System.out.println("\nUNIQUE LOAN:");
          System.out.println(loans[index]);
          break;
        case '3': // quit
          System.out.println("PROGRAM COMPLETE\n");
          if (DEBUG) printLoans(loans);
          return;
      }

      index++;
    }
  }

  public static void printLoans(Mortgage[] loans) {
    System.out.println("Contents of Array:");
    for (Mortgage loan : loans) {
      System.out.println();
      System.out.println(loan);
    }
  }
}
