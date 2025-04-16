import java.util.Scanner;

/**
 * MortgageApp.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * EXTRA CREDIT INCLUDED
 *
 * A mortgage filing application for Big12 Americana Bank. It prompts the user
 * to enter information for up to 10 promotional (with preset values) and/or
 * unique mortgages (with user-defined values). It automatically calculates the
 * monthly and total payments and generates an account number for the mortgage.
 * Mortgage information is displayed once upon entry and once again upon
 * program completion.
 */
public class MortgageApp {
  /**
   * Calls a setter of the {@link Mortgage} class.
   *
   * @param <MortgageObject>
   * @param <MortgageValue>
   */
  @FunctionalInterface
  interface MortgageFieldSetter<MortgageObject, MortgageValue> {
    public void set(MortgageObject loan, MortgageValue val);
  }

  /**
   * Represents fields of the {@link Mortgage} class.
   */
  private static enum MortgageField {
    LAST_NAME,
    AMOUNT,
    INTEREST,
    TERM,
  }

  /**
   * @param field the field of the {@link Mortgage} to set, as a
   *     {@link MortgageField}
   * @param loan the {@link Mortgage} object to modify
   * @param scan a {@link Scanner} for user input
   */
  private static void enterMortgageField(MortgageField field, Mortgage loan, Scanner scan) {
    boolean isFirstTry = true;
    String input, prompt, reprompt, error = "", ignoredRegex = "";
    MortgageFieldSetter<Mortgage, String> setter;

    switch (field) {
      case AMOUNT:
        prompt = "Enter the amount of the loan (Ex:120000)";
        reprompt = "Please re-enter loan amount without $ or commas (Ex:120000)";
        ignoredRegex = "[$,]";
        error = String.format(
            "\tValid Loan Amounts are $%.0f - $%.0f (non-inclusive)",
            Mortgage.LOAN_MIN,
            Mortgage.LOAN_MAX);
        setter = (l, v) -> l.storeLoanAmount(Double.parseDouble(v));
        break;
      case INTEREST:
        prompt = "Enter yearly interest rate (Ex: 8.25)";
        reprompt = "Please re-enter valid yearly interest rate (Ex: 8.25)";
        ignoredRegex = "[%]";
        error = String.format(
            "\tValid Interest Rates are %.1f%% - %.1f%%",
            Mortgage.INTEREST_MIN,
            Mortgage.INTEREST_MAX);
        setter = (l, v) -> l.storeInterestRate(Double.parseDouble(v));
        break;
      case TERM:
        prompt = "Enter number of years for the loan";
        reprompt = "Please re-enter valid number of years";
        ignoredRegex = "years";
        error = String.format(
            "\tValid Loan Terms are %d-%d",
            Mortgage.TERM_MIN,
            Mortgage.TERM_MAX);
        setter = (l, v) -> l.storeTerm(Integer.parseInt(v));
        break;
      case LAST_NAME:
        prompt = "Enter customer's Last Name Only";
        reprompt = null;
        setter = (l, v) -> l.storeLastName(v);
        break;
      default:
        System.out.println("Invalid field.");
        return;
    }

    // input loop
    while (true) {
      System.out.printf("%s: ", isFirstTry ? prompt : reprompt);
      input = scan.nextLine().trim().replaceAll(ignoredRegex, "").trim();

      try {
        setter.set(loan, input);
        return; // if there are no exceptions by this point, assume the field has been set properly
      } catch (IllegalArgumentException ignore) {
        // IllegalArgumentException also catches NumberFormatException
        System.out.println(error);
        isFirstTry = false;
      }
    }
  }

  public static void main(String[] args) {
    // constants for promotional loans
    final double PROMO_LOAN_AMOUNT = 300000;
    final double PROMO_INTEREST = 5.25;
    final int PROMO_TERM = 30;
    final int MAX_LOANS = 10;

    // if true, prints extra information about the data stored
    final boolean DEBUG = true;

    // intro message (only prints on launch)
    System.out.println("Welcome to the Big12 Americana Bank program\ncreated by Emma Jaskowiec");

    // set up array with indexer/counter variable
    Mortgage[] loans = new Mortgage[MAX_LOANS];
    int numLoans = 0;

    Scanner scan = new Scanner(System.in);

    while (true) { // main application loop
      // end the program if the max number of loans has been reached
      if (numLoans == MAX_LOANS) {
        System.out.println("\nMaximum number of objects reached");
        break;
      }

      // print menu of choices for user
      System.out.print(
          "\nPlease choose from the following choices below:\n\t1) Promotional Loan (preset loan amount, rate, term)\n\t2) Unique Loan (enter in loan values)\n\t3) Quit (Exit the program)\n\tPlease enter your selection (1-3): ");

      // make sure user input is one character + exists as an option
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
      System.out.println(); // spacing

      // choice 1: create a promotional loan with default values
      if (choice == '1') {
        loans[numLoans] = new Mortgage(
            PROMO_LOAN_AMOUNT,
            PROMO_INTEREST,
            PROMO_TERM);
        enterMortgageField(MortgageField.LAST_NAME, loans[numLoans], scan);
        System.out.printf("\nPROMOTIONAL LOAN:\n%s\n", loans[numLoans]);
      }

      // choice 2: create a custom loan with user-defined values
      else if (choice == '2') {
        loans[numLoans] = new Mortgage();
        for (MortgageField field : MortgageField.values()) {
          enterMortgageField(field, loans[numLoans], scan);
          System.out.println(); // spacing
        }
        System.out.printf("UNIQUE LOAN:\n%s\n", loans[numLoans]);
      }

      // choice 3: quit the program
      else if (choice == '3') {
        break;
      }

      numLoans++;
    } // end of main application loop

    // exit message
    System.out.println("PROGRAM COMPLETE\n");
    scan.close();

    if (DEBUG) {
      System.out.println("Contents of Array:");
      for (Mortgage loan : loans) {
        if (loan == null) {
          break;
        }
        System.out.printf("\n%s\n", loan);
      }
    }
  }
}
