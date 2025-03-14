import java.util.Scanner;

/**
 * Mortgage.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Represents a loan mortgage (i.e. a house loan). TODO: finish description
 */
public class Mortgage {
  /** The minimum total principal (inclusive). */
  private static final double LOAN_MIN = 100000.0;
  /** The maximum total principal (exclusive). */
  private static final double LOAN_MAX = 1000000.0;
  /** The minimum interest rate (inclusive). */
  private static final double INTEREST_MIN = 3.5;
  /** The maximum interest rate (inclusive). */
  private static final double INTEREST_MAX = 9.0;
  /** The minimum mortage term (inclusive). */
  private static final int TERM_MIN = 15;
  /** The maximum mortgage term (inclusive). */
  private static final int TERM_MAX = 50;

  /** The promotional pricipal amount. */
  private static final double PROMO_LOAN_AMOUNT = 300000;
  /** The promotional interest rate. */
  private static final double PROMO_INTEREST = 5.25;
  /** The promotional mortgage term. */
  private static final int PROMO_TERM = 30;

  /** The loan amount (principal) in dollars. */
  private double loanAmount;
  /** The yearly interest rate as a percentage. */
  private double interestRate;
  /** The term of the Mortgage in years. */
  private int term;
  /** The customer's account number. */
  private String accountNum;
  /** The customer's last name. */
  private String lastName;

  /**
   * Creates a promotional Mortgage with no account name.
   */
  public Mortgage() {
    this(PROMO_LOAN_AMOUNT, PROMO_INTEREST, PROMO_TERM, null);
  }

  /**
   * Creates a promotional Mortgage with the given account name.
   */
  public Mortgage(String lastName) {
    this(PROMO_LOAN_AMOUNT, PROMO_INTEREST, PROMO_TERM, lastName);
  }

  /**
   * Creates a Mortgage with the given fields.
   */
  public Mortgage(double loanAmount, double interestRate, int term,
      String lastName) {
    setLoanAmount(loanAmount);
    setInterestRate(interestRate);
    setTerm(term);
    this.lastName = formatLastName(lastName);
  }

  /**
   * Reads in from user, validates, and stores the amount of the loan.
   */
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
      // TODO: remove extra decimal points

      // parse number
      try {
        amount = Double.parseDouble(input);
      } catch (NumberFormatException e) {
        System.out.printf("\tValid Loan Amounts are $%.0f - $%.0f (non-inclusive)\n", LOAN_MIN, LOAN_MAX);
        reEnter = true;
        continue; // retry input
      }

      try {
        setLoanAmount(amount);
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }

    s.close();
  }

  /**
   * Reads in from user, validates, and stores the yearly interest rate of the
   * loan.
   */
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
      // TODO: remove extra decimal points

      // parse number
      try {
        interest = Double.parseDouble(input);
      } catch (NumberFormatException e) {
        System.out.printf("\tValid Interest Rates are %.1f%% - %.1f%%\n", INTEREST_MIN, INTEREST_MAX);
        reEnter = true;
        continue; // retry input
      }

      try {
        setInterestRate(interest);
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }

    s.close();
  }

  /**
   * Reads in from user, validates, and stores the term of the loan.
   */
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
        setTerm(term);
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }

    s.close();
  }

  /**
   * Generates a random number, creates an account number, and stores the
   * account number of the customer.
   */
  public void storeAcctNum() {
  }

  /**
   * Reads in from user and stores the last name of the customer.
   */
  public void storeLastName() {
  }

  public String formatLastName(String name) {
    name = name.trim();
    return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
  }

  /**
   * Calculates the monthly loan payment.
   *
   * @return The monthly loan payment.
   */
  private double calcMonthlyPayment() {
    double monthlyInterest = this.interestRate / 12.0;
    double n = Math.pow(1 + monthlyInterest, this.term * 12.0);
    return this.loanAmount * (monthlyInterest * n / (n - 1));
  }

  /**
   * Calculates the total payment for the loan.
   *
   * @return The total loan payment.
   */
  private double calcTotalPayment() {
    return this.calcMonthlyPayment() * this.term * 12;
  }

  public void setLoanAmount(double amount) throws IllegalArgumentException {
    if (amount < LOAN_MIN || amount >= LOAN_MAX) {
      throw new IllegalArgumentException(
          String.format("\tValid Loan Amounts are $%.0f - $%.0f (non-inclusive)\n", LOAN_MIN, LOAN_MAX));
    }
    this.loanAmount = amount;
  }

  public void setInterestRate(double interestRate) throws IllegalArgumentException {
    if (interestRate < INTEREST_MIN || interestRate > INTEREST_MAX) {
      throw new IllegalArgumentException(
          String.format("\tValid Interest Rates are %.1f%% - %.1f%%\n", INTEREST_MIN, INTEREST_MAX));
    }
    this.interestRate = interestRate;
  }

  public void setTerm(int term) throws IllegalArgumentException {
    if (term < TERM_MIN || term > TERM_MAX) {
      throw new IllegalArgumentException(
          String.format("\tValid Loan Terms are %d-%d\n", TERM_MIN, TERM_MAX));
    }
    this.term = term;
  }

  public double getLoanAmount() {
    return this.loanAmount;
  }

  public double getInterestRate() {
    return this.interestRate;
  }

  public int getTerm() {
    return this.term;
  }

  /**
   * Displays formatted output.
   *
   * @return A String representing the Mortgage.
   */
  @Override
  public String toString() {
    return String.format(
        "Account Number: %s\nThe monthly payment is $%,.2f\nThe total payment is $%,.2f\n",
        this.accountNum,
        this.calcMonthlyPayment(),
        this.calcTotalPayment());
  }
}
