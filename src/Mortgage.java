import java.io.IOException;

/**
 * Mortgage.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Represents a loan mortgage (i.e. a house loan). TODO: finish description
 */
public class Mortgage {
  /** The minimum total loan amount (inclusive). */
  private static final double LOAN_MIN = 100000.0;
  /** The maximum total loan amount (exclusive). */
  private static final double LOAN_MAX = 1000000.0;
  /** The minimum interest rate (inclusive). */
  private static final double INTEREST_MIN = 3.5;
  /** The maximum interest rate (inclusive). */
  private static final double INTEREST_MAX = 9.0;
  /** The minimum mortage term (inclusive). */
  private static final double TERM_MIN = 15.0;
  /** The maximum mortgage term (inclusive). */
  private static final double TERM_MAX = 50.0;

  /** The yearly interest rate as a percentage. */
  private double interestRate;
  /** The term of the Mortgage in years. */
  private double term;
  /** The total loan amount in dollars. */
  private double loanAmount;
  /** The customer's account number. */
  private int accountNum;
  /** The customer's last name. */
  private String lastName;

  /**
   * Default constructor
   */
  public Mortgage() {
  }

  /**
   * Constructor with customer last name
   */
  public Mortgage(double interestRate, double term, double loanAmount,
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
   * Displays formatted output.
   *
   * @return A String representing the Mortgage.
   */
  @Override
  public String toString() {
  }

  /**
   * Calculates the monthly loan payment.
   *
   * @return The monthly loan payment.
   */
  private double calcMonthlyPayment() {
  }

  /**
   * Calculates the total payment for the loan.
   *
   * @return The total loan payment.
   */
  private double calcTotalPayment() {
  }
}
