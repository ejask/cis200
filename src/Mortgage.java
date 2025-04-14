import java.util.Random;

public class Mortgage {
  private String accountName;
  private String accountNumber;
  private double principal;
  private double interest;
  private byte term;

  final public double MIN_PRINCIPAL = 100000.0;
  final public double MAX_PRINCIPAL = 1000000.0;
  final public double MIN_INTEREST = 3.5;
  final public double MAX_INTEREST = 9.0;
  final public byte MIN_TERM = 15;
  final public byte MAX_TERM = 50;

  public Mortgage() {}

  public Mortgage(
    String name,
    double principal,
    byte term,
    double interest
  ) throws IllegalArgumentException {
    this.setAccountName(name);
    this.setPrincipal(principal);
    this.setTerm(term);
    this.setInterest(interest);
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public double getPrincipal() {
    return this.principal;
  }

  public double getTerm() {
    return this.term;
  }

  public double getInterest() {
    return this.interest;
  }

  // TODO: handle names with spaces?
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

  public void setTerm(byte years) throws IllegalArgumentException {
    if (years < MIN_TERM || years > MAX_TERM) {
      throw new IllegalArgumentException(String.format(
        "Term must be in range %d (inclusive) and %d (inclusive).",
        MIN_TERM,
        MAX_TERM
      ));
    }
    this.term = years;
  }

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

  private double getMonthlyPayment() {
    double monthlyInterest = this.interest / 12.0;
    double compound = Math.pow(1 + monthlyInterest, this.term * 12.0);
    return this.principal * ((monthlyInterest * compound) / compound - 1);
  }

  private double getTotalPayment() {
    return this.getMonthlyPayment() * (this.term * 12.0);
  }

  public String toString() {
    return String.format(
      "Account number: %s\nThe monthly payment is $%.,2f\nThe total payment is $%.,2f",
      this.getAccountNumber(),
      this.getMonthlyPayment(),
      this.getTotalPayment()
    );
  }
}
