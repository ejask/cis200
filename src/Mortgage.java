import java.util.Random;
import java.util.Scanner;

/**
 * Mortgage.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Represents a loan mortgage (i.e. a house loan). TODO: finish description
 */
public class Mortgage {
  private static final double LOAN_MIN = 100000.0;
  private static final double LOAN_MAX = 1000000.0;
  private static final double INTEREST_MIN = 3.5;
  private static final double INTEREST_MAX = 9.0;
  private static final int TERM_MIN = 15;
  private static final int TERM_MAX = 50;

  private static final double PROMO_LOAN_AMOUNT = 300000;
  private static final double PROMO_INTEREST = 5.25;
  private static final int PROMO_TERM = 30;

  /** the principal (in dollars) */
  private double loanAmount;
  /** the yearly interest rate (as a percentage) */
  private double interestRate;
  /** the term (in years) */
  private int term;
  /** the customer's account number */
  private String acctNum;
  /** the customer's last name */
  private String lastName;

  /** Creates a promotional Mortgage with no account name. */
  public Mortgage() {
    this(PROMO_LOAN_AMOUNT, PROMO_INTEREST, PROMO_TERM, null);
  }

  /** Creates a promotional Mortgage with the given account name. */
  public Mortgage(String lastName) {
    this(PROMO_LOAN_AMOUNT, PROMO_INTEREST, PROMO_TERM, lastName);
  }

  /** Creates a Mortgage with the given field values. */
  public Mortgage(double loanAmount, double interestRate, int term, String lastName) {
    this.setLoanAmount(loanAmount);
    this.setInterestRate(interestRate);
    this.setTerm(term);
    this.setLastName(lastName); // also generates account number
  }

  /** Reads in from the user, validates, and stores the amount of the loan. */
  public void storeLoanAmount() {
    Scanner s = new Scanner(System.in);
    boolean reEnter = false;

    while (true) {
      String input;
      double amount;

      // get input
      if (!reEnter) {
        System.out.print("Enter the amount of the loan (Ex:120000): ");
      } else {
        System.out.print("Please re-enter loan amount without $ or commas (Ex:120000): ");
      }
      // remove characters that aren't digits or a decimal points
      input = s.nextLine().trim().replaceAll("[^0-9.]", " ");

      // parse number
      try {
        amount = Double.parseDouble(input);
      } catch (NumberFormatException e) {
        System.out.printf("\tValid Loan Amounts are $%.0f - $%.0f (non-inclusive)\n", LOAN_MIN, LOAN_MAX);
        reEnter = true;
        continue; // retry input
      }

      try {
        this.setLoanAmount(amount);
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }

    s.close();
  }

  /** Reads in from the user, validates, and stores the yearly interest rate of the loan. */
  public void storeInterestRate() {
    Scanner s = new Scanner(System.in);
    boolean reEnter = false;

    while (true) {
      String input;
      double interest;

      // get input
      if (!reEnter) {
        System.out.print("Enter yearly interest rate (Ex: 8.25): ");
      } else {
        System.out.print("Please re-enter valid yearly interest rate (Ex: 8.25): ");
      }
      // remove characters that aren't digits or a decimal points
      input = s.nextLine().trim().replaceAll("[^0-9.]", " ");

      // parse number
      try {
        interest = Double.parseDouble(input);
      } catch (NumberFormatException e) {
        System.out.printf("\tValid Interest Rates are %.1f%% - %.1f%%\n", INTEREST_MIN, INTEREST_MAX);
        reEnter = true;
        continue; // retry input
      }

      try {
        this.setInterestRate(interest);
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }

    s.close();
  }

  /** Reads in from the user, validates, and stores the term of the loan. */
  public void storeTerm() {
    Scanner s = new Scanner(System.in);
    boolean reEnter = false;

    while (true) {
      String input;
      int term;

      // get input
      if (!reEnter) {
        System.out.print("Enter number of years for the loan: ");
      } else {
        System.out.print("Please re-enter valid number of years: ");
      }
      // remove characters that aren't digits
      input = s.nextLine().trim().replaceAll("[^0-9]", " ");

      // parse number
      try {
        term = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.printf("\tValid Loan Terms are %d-%d\n", TERM_MIN, TERM_MAX);
        reEnter = true;
        continue; // retry input
      }

      try {
        this.setTerm(term);
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }

    s.close();
  }

  /** Creates and stores an account number using the first four letters of the customer's last name and a random {@code int} between 100 (inclusive) to 10000 (exclusive). */
  public void storeAcctNum() {
    this.acctNum = this.lastName.substring(0, 4) + new Random().nextInt(100, 10000);
  }

  /** Reads in from the user and stores the last name of the customer. */
  public void storeLastName() {
    Scanner scan = new Scanner(System.in);
    while (true) {
      System.out.print("Enter customer's Last Name Only: ");
      try {
        this.setLastName(scan.nextLine());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }
    scan.close();
  }

  /**
   * Calculates and returns the monthly loan payment.
   * @return the monthly loan payment in dollars
   */
  private double calcMonthlyPayment() {
    double monthlyInterest = this.interestRate / 12.0;
    double n = Math.pow(1 + monthlyInterest, this.term * 12.0);
    return this.loanAmount * (monthlyInterest * n / (n - 1));
  }

  /**
   * Calculates and returns the total payment for the loan.
   * @return the total loan payment in dollars
   */
  private double calcTotalPayment() {
    return this.calcMonthlyPayment() * this.term * 12;
  }

  /**
   * @param amount the new loan amount (i.e. principal) of this {@code Mortgage} (in dollars)
   * @throws IllegalArgumentException if the amount is not between {@value #LOAN_MIN} (inclusive) and {@value #LOAN_MAX} (exclusive)
   */
  public void setLoanAmount(double amount) throws IllegalArgumentException {
    if (amount < LOAN_MIN || amount >= LOAN_MAX) {
      throw new IllegalArgumentException(
          String.format("\tValid Loan Amounts are $%.0f - $%.0f (non-inclusive)\n", LOAN_MIN, LOAN_MAX)
      );
    }
    this.loanAmount = amount;
  }

  /**
   * @param term the new interest rate of this {@code Mortgage} (as a percentage)
   * @throws IllegalArgumentException if the interest rate is not between {@value #INTEREST_MIN} and {@value #INTEREST_MAX} (inclusive)
   */
  public void setInterestRate(double interestRate) throws IllegalArgumentException {
    if (interestRate < INTEREST_MIN || interestRate > INTEREST_MAX) {
      throw new IllegalArgumentException(
          String.format("\tValid Interest Rates are %.1f%% - %.1f%%\n", INTEREST_MIN, INTEREST_MAX)
      );
    }
    this.interestRate = interestRate;
  }

  /**
   * @param term the new term of this {@code Mortgage} (in years)
   * @throws IllegalArgumentException if the term is not between {@value #TERM_MIN} and {@value #TERM_MAX} (inclusive)
   */
  public void setTerm(int term) throws IllegalArgumentException {
    if (term < TERM_MIN || term > TERM_MAX) {
      throw new IllegalArgumentException(
          String.format("\tValid Loan Terms are %d-%d\n", TERM_MIN, TERM_MAX)
      );
    }
    this.term = term;
  }

  /**
   * @param name the new last name to associate with the {@code Mortgage}
   * @throws IllegalArgumentException if the name is not at least one character long
   */
  public void setLastName(String name) {
    if (name == "") {
      throw new IllegalArgumentException("Customer last name must be at least one character.");
    }
    name = name.trim();
    name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    this.lastName = name;
    this.storeAcctNum(); // regenerate account number when last name changes
  }

  /** @return the principal (in dollars) of this {@code Mortgage} */
  public double getLoanAmount() {
    return this.loanAmount;
  }

  /** @return the interest rate (as a percentage) of this {@code Mortgage} */
  public double getInterestRate() {
    return this.interestRate;
  }

  /** @return the term (in years) of this {@code Mortgage} */
  public int getTerm() {
    return this.term;
  }

  /** @return the account number associated with this {@code Mortgage} */
  public String getAcctNum() {
    return this.acctNum;
  }

  /** @return the last name associated with this {@code Mortgage} */
  public String getLastName() {
    return this.lastName;
  }

  /** @return a {@code String} representation of this {@code Mortgage} */
  @Override
  public String toString() {
    return String.format(
        "Account Number: %s\nThe monthly payment is $%,.2f\nThe total payment is $%,.2f\n",
        this.getAcctNum(),
        this.calcMonthlyPayment(),
        this.calcTotalPayment()
    );
  }
}
