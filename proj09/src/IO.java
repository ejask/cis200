import java.util.Scanner;

public class IO {
  private Scanner scan;

  public IO() {
    this.scan = new Scanner(System.in);
  }

  public void displayError(String errorMessage) {
    System.out.println("[ERROR] " + errorMessage);
  }

  public void displayLoan(String loanStr) {
    System.out.println(loanStr + '\n');
  }

  public void displayLoan(String loanStr, String typeStr) {
    System.out.printf("\n%s LOAN...:\n%s\n\n", typeStr.toUpperCase(), loanStr);
  }

  public void displayMenu(String programmerName) {
    System.out.printf(
      "Welcome to the Big12 Americana Bank program\ncreated by %s\n\n",
      programmerName
    );
  }

  public byte displayMenu() {
    System.out.print(
      "Please choose from the following choices below:\n\t1) Promotional Loan (preset loan amount, rate, term)\n\t2) Unique Loan (enter in loan values)\n\t3) Quit (Exit the program)\n\tPlease enter your selection (1-3): "
    );
    while (true) {
      String input = scan.nextLine().trim();
      if (input.length() == 1) {
        try {
          byte num = Byte.parseByte(input);
          if (num >= 1 && num <= 3) {
            System.out.println();
            return num;
          }
        } catch (NumberFormatException _ignore) { /* do nothing */ }
      }
      System.out.print("\t\tInvalid choice. Please select 1, 2, or 3: ");
    }
  }

  public void displaySuccess() {
    System.out.println("PROGRAM COMPLETE\n\nContents of ArrayList...");
  }

  public String readName() {
    System.out.print("Enter customer's Last Name Only: ");
    return scan.nextLine().trim();
  }

  public double readPrincipal(double min, double max) {
    double num;
    System.out.print("Enter the amount of the loan (Ex:120000): ");
    while (true) {
      String input = scan.nextLine();
      try {
        num = Double.parseDouble(input);
        if (num >= min && num < max) {
          System.out.println();
          return num;
        }
        System.out.printf(
          "\tValid Loan Amounts are $%.0f - $%.0f (non-inclusive)\n",
          min,
          max
        );
      } catch (NumberFormatException e) {
        System.out.println("\tInput must be numeric only.  Try again...");
      }
      System.out.print("Please re-enter loan amount without $ or commas (Ex:120000): ");
    }
  }

  public double readInterest(double min, double max) {
    double num;
    System.out.print("Enter yearly interest rate (Ex: 8.25): ");
    while (true) {
      String input = scan.nextLine();
      try {
        num = Double.parseDouble(input);
        if (num >= min && num <= max) {
          System.out.println();
          return num;
        }
        System.out.printf(
          "\tValid Interest Rates are %.1f%% - %.1f%%\n",
          min,
          max
        );
      } catch (NumberFormatException e) {
        System.out.println("\tInput must be numeric only.  Try again...");
      }
      System.out.print("Please re-enter valid yearly interest rate (Ex: 8.25): ");
    }
  }

  public byte readTerm(byte min, byte max) {
    byte num;
    System.out.print("Enter number of years for the loan: ");
    while (true) {
      String input = scan.nextLine();
      try {
        num = Byte.parseByte(input);
        if (num >= min && num <= max) {
          return num;
        }
        System.out.printf("\tValid Loan Terms are %d-%d\n", min, max);
      } catch (NumberFormatException e) {
        System.out.println("\tInput must be an integer.  Try again...");
      }
      System.out.print("Please re-enter valid number of years: ");
    }
  }
}
