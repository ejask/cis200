import java.util.Random;

/**
 * Mortgage.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * EXTRA CREDIT INCLUDED
 *
 * Represents a loan mortgage (i.e. a house loan) with Big12 Americana Bank.
 */
public class Mortgage {
  /** The mimimum loan amount/principal. */
  public static final double LOAN_MIN = 100000.0;

  /** The maximum loan amount/principal. */
  public static final double LOAN_MAX = 1000000.0;
  
  /** The minimum yearly interest rate (as a percentage). */
  public static final double INTEREST_MIN = 3.5;
  
  /** The maximum yearly interest rate (as a percentage). */
  public static final double INTEREST_MAX = 9.0;
  
  /** The minimum loan term (in years). */
  public static final int TERM_MIN = 15;
  
  /** The maximum loan term (in years). */
  public static final int TERM_MAX = 50;

  private double loanAmount;
  private double interestRate;
  private int term;
  private String acctNum;
  private String lastName;

  /**
   * Creates an uninitialized {@link Mortgage}, usually to be populated by user
   * input.
   */
  public Mortgage() { }

  /**
   * Creates a {@link Mortgage} with the given field values.
   *
   * @param loanAmount the principal (in dollars)
   * @param interestRate the yearly interest rate (as a percentage)
   * @param term the term (in years)
   */
  public Mortgage(double loanAmount, double interestRate, int term) {
    this.storeLoanAmount(loanAmount);
    this.storeInterestRate(interestRate);
    this.storeTerm(term);
  }

  /**
   * Validates and stores the loan amount (i.e. principal) in dollars.
   *
   * @param amount the new loan amount of this {@link Mortgage}
   * @throws IllegalArgumentException if the amount is not between
   *     {@value #LOAN_MIN} (inclusive) and {@value #LOAN_MAX} (exclusive)
   */
  public void storeLoanAmount(double amount) throws IllegalArgumentException {
    if (amount < LOAN_MIN || amount >= LOAN_MAX) {
      throw new IllegalArgumentException(String.format(
          "\tValid Loan Amounts are $%.0f - $%.0f (non-inclusive)\n",
          LOAN_MIN,
          LOAN_MAX));
    }
    this.loanAmount = amount;
  }

  /**
   * Validates and stores the yearly interest rate (as a percentage) of the
   * loan.
   *
   * @param interestRate the new interest rate of this {@link Mortgage}
   * @throws IllegalArgumentException if the interest rate is not between
   *     {@value #INTEREST_MIN} and {@value #INTEREST_MAX} (inclusive)
   */
  public void storeInterestRate(double interestRate) throws IllegalArgumentException {
    if (interestRate < INTEREST_MIN || interestRate > INTEREST_MAX) {
      throw new IllegalArgumentException(String.format(
          "\tValid Interest Rates are %.1f%% - %.1f%%\n",
          INTEREST_MIN,
          INTEREST_MAX));
    }
    this.interestRate = interestRate;
  }

  /**
   * Validates and stores the term (in years) of the loan.
   *
   * @param term the new term of this {@link Mortgage}
   * @throws IllegalArgumentException if the term is not between
   *     {@value #TERM_MIN} and {@value #TERM_MAX} (inclusive)
   */
  public void storeTerm(int term) throws IllegalArgumentException {
    if (term < TERM_MIN || term > TERM_MAX) {
      throw new IllegalArgumentException(String.format(
          "\tValid Loan Terms are %d-%d\n",
          TERM_MIN,
          TERM_MAX));
    }
    this.term = term;
  }

  /**
   * Validates and stores the last name of the customer.
   *
   * @param name the new last name to associate with the {@link Mortgage}
   */
  public void storeLastName(String name) {
    name = name.trim();
    name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    this.lastName = name;
    this.storeAcctNum(); // regenerate account number when last name changes
  }

  /**
   * Creates and stores an account number using the first four letters of the
   * customer's last name and a random {@code int} between 100 (inclusive) to
   * 10000 (exclusive).
   */
  public void storeAcctNum() {
    String subStr = this.lastName.length() >= 4 ? this.lastName.substring(0, 4) : this.lastName;
    this.acctNum = subStr + new Random().nextInt(100, 10000);
  }

  /** @return a {@link String} representation of this {@link Mortgage} */
  @Override
  public String toString() {
    return String.format(
        "Account Number: %s\nThe monthly payment is $%,.2f\nThe total payment is $%,.2f",
        this.acctNum,
        this.calcMonthlyPayment(),
        this.calcTotalPayment());
  }

  /**
   * Calculates and returns the monthly loan payment.
   *
   * @return the monthly loan payment in dollars
   */
  private double calcMonthlyPayment() {
    double monthlyInterest = this.interestRate / 1200.0;
    double n = Math.pow(1 + monthlyInterest, this.term * 12.0);
    return this.loanAmount * ((monthlyInterest * n) / (n - 1));
  }

  /**
   * Calculates and returns the total payment for the loan.
   *
   * @return the total loan payment in dollars
   */
  private double calcTotalPayment() {
    return this.calcMonthlyPayment() * this.term * 12;
  }
}
