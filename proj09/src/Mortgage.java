import java.util.Random;

/**
 * Mortgage.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Represents a mortgage (i.e. a house loan) with Big12 Americana Bank. Keeps
 * track of the principal amount, yearly interest rate, term length, customer
 * name, and account number (generated from the customer name and four random
 * digits).
 */
public class Mortgage {
  private String accountName;
  private String accountNumber;
  private double principal;
  private double interest;
  private int term;

  /** The minimum principal in dollars (inclusive). */
  final public static double MIN_PRINCIPAL = 100000.0;

  /** The maximum principal in dollars (exclusive). */
  final public static double MAX_PRINCIPAL = 1000000.0;

  /** The minimum yearly interest rate as a percentage (inclusive). */
  final public static double MIN_INTEREST = 3.5;

  /** The maximum yearly interest rate as a percentage (inclusive). */
  final public static double MAX_INTEREST = 9.0;

  /** The minimum term length in years (inclusive). */
  final public static int MIN_TERM = 15;

  /** The maximum term length in years (inclusive). */
  final public static int MAX_TERM = 50;

  /** Constructs a new uninitialized {@link Mortgage} instance. */
  public Mortgage() {}

  /**
   * Constructs a new {@link Mortgage} instance with the given field values.
   *
   * @param name  the account name
   * @param principal  the principal amount in dollars
   * @param term  the term length in years
   * @param interest  the yearly interest rate as a percentage
   */
  public Mortgage(String name, double principal, int term, double interest) {
    this.setAccountName(name);
    this.setPrincipal(principal);
    this.setInterest(interest);
    this.setTerm(term);
  }

  /**
   * Returns the account number of this {@link Mortgage} instance.
   * 
   * @return the account number of this {@link Mortgage}
   */
  public String getAccountNumber() {
    return this.accountNumber;
  }

  /**
   * Returns the principal amount (in dollars) of this {@link Mortgage} instance.
   * 
   * @return the principal of this {@link Mortgage}
   */
  public double getPrincipal() {
    return this.principal;
  }

  /**
   * Returns the yearly interest rate (as a percentage) of this {@link Mortgage}
   * instance.
   * 
   * @return the yearly interest rate of this {@link Mortgage}
   */
  public double getInterest() {
    return this.interest;
  }

  /**
   * Returns the term length (in years) of this {@link Mortgage} instance.
   * 
   * @return the term of this {@link Mortgage}
   */
  public int getTerm() {
    return this.term;
  }

  /**
   * @param name  the account name of this {@link Mortgage}
   * @throws IllegalArgumentException  if the name is not at least one character
   *   in length.
   */
  public void setAccountName(String name) throws IllegalArgumentException {
    name = name.trim();
    if (name.length() == 0) {
      throw new IllegalArgumentException(
        "Account name must be at least one character."
      );
    }
    if (name.length() == 1) {
      this.accountName = name.toUpperCase();
    } else {
      this.accountName = Character.toUpperCase(name.charAt(0))
        + name.toLowerCase().substring(1);
    }
    this.setAccountNumber();
  }

  /**
   * Generates the account number from the first four letters of the account
   * name and four randomly generated digits.
   */
  private void setAccountNumber() {
    final byte NUM_DIGITS = 4;
    StringBuilder sb = new StringBuilder();
    sb.append(
      this.accountName.substring(0, Math.min(4, this.accountName.length()))
    );
    Random r = new Random();
    for (byte i = 0; i <= NUM_DIGITS; i++) {
      sb.append(r.nextInt(10));
    }
    this.accountNumber = sb.toString();
  }

  /**
   * @param dollars  the principal amount of this {@link Mortgage}
   * @throws IllegalArgumentException  if the principal is not between
   *   ${@value #MIN_PRINCIPAL} (inclusive) and ${@value #MAX_PRINCIPAL}
   *   (exclusive).
   */
  public void setPrincipal(double dollars) throws IllegalArgumentException {
    if (dollars < MIN_PRINCIPAL || dollars >= MAX_PRINCIPAL) {
      throw new IllegalArgumentException(String.format(
        "Principal must be in range $%.,2f (inclusive) and $%.,2f (exclusive).",
        MIN_PRINCIPAL,
        MAX_PRINCIPAL
      ));
    }
    this.principal = dollars;
  }

  /**
   * @param interest  the yearly interest rate of this {@link Mortgage}
   * @throws IllegalArgumentException  if the interest is not between
   *   {@value #MIN_INTEREST}% (inclusive) and {@value #MAX_INTEREST}%
   *   (inclusive).
   */
  public void setInterest(double interest) throws IllegalArgumentException {
    if (interest < MIN_INTEREST || interest > MAX_INTEREST) {
      throw new IllegalArgumentException(String.format(
        "Interest must be in range %.2f%% (inclusive) and %.2f%% (inclusive).",
        MIN_INTEREST,
        MAX_INTEREST
      ));
    }
    this.interest = interest;
  }

  /**
   * @param years  the term length of this {@link Mortgage}
   * @throws IllegalArgumentException  if the term is not between
   *   {@value #MIN_TERM} years (inclusive) and {@value #MAX_TERM} years
   *   (inclusive).
   */
  public void setTerm(int years) throws IllegalArgumentException {
    if (years < MIN_TERM || years > MAX_TERM) {
      throw new IllegalArgumentException(String.format(
        "Term must be in range %d (inclusive) and %d (inclusive).",
        MIN_TERM,
        MAX_TERM
      ));
    }
    this.term = years;
  }

  /**
   * Returns the monthly payment amount (in dollars) of this {@link Mortgage}
   * instance.
   *
   * @return the monthly payment for this {@link Mortgage}
   */
  private double getMonthlyPayment() {
    double monthlyInterest = this.interest / 1200.0;
    double compound = Math.pow(1 + monthlyInterest, this.term * 12.0);
    return this.principal * ((monthlyInterest * compound) / compound - 1);
  }

  /**
   * Returns the total payment amount (in dollars) of this {@link Mortgage}
   * instance.
   *
   * @return the total payment for this {@link Mortgage}
   */
  private double getTotalPayment() {
    return this.getMonthlyPayment() * (this.term * 12.0);
  }

  /**
   * Returns a {@link String} representation of this {@link Mortgage} instance
   * with the account number, monthly payment, and total payment.
   * 
   * @return a {@link String} representing this {@link Mortgage}
   */
  @Override
  public String toString() {
    return String.format(
      "Account number: %s\nThe monthly payment is $%.,2f\nThe total payment is $%.,2f",
      this.getAccountNumber(),
      this.getMonthlyPayment(),
      this.getTotalPayment()
    );
  }
}
