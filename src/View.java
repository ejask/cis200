import java.util.Scanner;

/**
 * View.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * TODO: class description
 */

public class View {
  private Scanner scan;

  /** Constructs a new {@link View}. */
  public View() {
    scan = new Scanner(System.in);
  }

  /**
   * @param message
   * @return
   */
  public String prompt(String message) {
    System.out.printf("%s: ", message);
    return scan.nextLine().trim();
  }

  /**
   * @param message
   * @return
   */
  public String promptYN(String message) {
    System.out.printf("%s (Y/N): ", message);
    return scan.nextLine().trim();
  }

  /**
   * @param message
   */
  public void promptContinue(String message) {
    System.out.printf("%s ... ", message);
    scan.nextLine();
  }

  /**
   * @param message
   */
  public void display(String message) {
    System.out.println(message);
  }

  /**
   *
   */
  public void nextSection() {
    System.out.println();
  }
}
