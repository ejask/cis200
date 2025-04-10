import java.util.Scanner;

/**
 * View.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Command line IO for {@link StudentApp}.
 */

public class View {
  private Scanner scan;

  /** Constructs a new {@link View}. */
  public View() {
    scan = new Scanner(System.in);
  }

  /**
   * Prints a prompt message to output and returns the following user input.
   * 
   * @param message the prompt message
   * @return the input line
   */
  public String prompt(String message) {
    System.out.printf("%s: ", message);
    return scan.nextLine().trim();
  }

  /**
   * Prints a message to output and returns the following numerical input.
   *
   * @param message the prompt message
   * @return the input number
   */
  public double promptNum(String message) {
    System.out.printf("%s: ", message);
    return Double.parseDouble(scan.nextLine());
  }

  /**
   * Prints a message to output and returns {@code true} if the following
   * character input is 'y' or 'Y'.
   *
   * @param message the prompt message
   * @return {@code true} if the input is 'y' or 'Y', {@code false} otherwise
   */
  public boolean promptYN(String message) {
    System.out.printf("%s (Y/N): ", message);
    return scan.nextLine().trim().equalsIgnoreCase("y") ? true : false;
  }

  /**
   * Prints a message to output and returns void when any input is received.
   *
   * @param message the prompt message
   */
  public void promptNext(String message) {
    System.out.printf(
      "%s\n\tPress enter to display next student...\n",
      message
    );
    scan.nextLine();
  }

  /**
   * Prints a message to output.
   *
   * @param message the message to print
   */
  public void display(String message) {
    System.out.println(message);
  }

  /**
   * Separates sections of output.
   */
  public void section() {
    System.out.println();
  }
}
