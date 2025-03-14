import java.io.IOException;

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
  public Mortgage(double loanAmount, double interestRate, double term,
      String lastName) {
  }

  /**
   * Reads in from user, validates, and stores the amount of the loan.
   *
   * @throws IOException
   */
  public void storeLoanAmount() throws IOException {
  }

  /**
   * Reads in from user, validates, and stores the term of the loan.
   *
   * @throws IOException
   */
  public void storeTerm() throws IOException {
  }

  /**
   * Reads in from user, validates, and stores the yearly interest rate of the
   * loan.
   *
   * @throws IOException
   */
  public void storeInterestRate() throws IOException {
  }

  /**
   * Reads in from user and stores the last name of the customer.
   *
   * @throws IOException
   */
  public void storeLastName() throws IOException {
  }

  /**
   * Generates a random number, creates an account number, and stores the account
   * number of the customer.
   */
  public void storeAcctNum() {
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
