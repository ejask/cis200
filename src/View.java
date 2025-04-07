import java.util.Scanner;

/**
 * View.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Implements a command line view API for {@link StudentApp}.
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
   * @return the next line of user input
   */
  public String prompt(String message) {
    System.out.printf("%s: ", message);
    return scan.nextLine().trim();
  }

  /**
   * Prints a "yes or no" prompt message to output and returns the following
   * user input.
   *
   * @param message the prompt message
   * @return the next line of user input
   */
  public String promptYN(String message) {
    System.out.printf("%s (Y/N): ", message);
    return scan.nextLine().trim();
  }

  /**
   * Prints a prompt message to output and continues when the user inputs a
   * newline.
   *
   * @param message the prompt message
   */
  public void promptContinue(String message) {
    System.out.printf("%s... ", message);
    scan.nextLine();
  }

  /**
   * Prints a {@link String} to stdout.
   *
   * @param message the {@link String} to display
   */
  public void display(String message) {
    System.out.println(message);
  }

  /** Prints a newline to stdout. */
  public void nextSection() {
    System.out.println();
  }
}
