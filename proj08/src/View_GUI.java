import javax.swing.JOptionPane;

/**
 * View_GUI.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * IO GUI for {@link StudentApp}.
 */

public class View_GUI {
  /** Constructs a new {@link View_GUI}. */
  public View_GUI() { }

  /**
   * Displays a message and returns the input.
   * 
   * @param message the prompt message
   * @return the input line
   */
  public String prompt(String message) {
    return JOptionPane.showInputDialog(message).trim();
  }

  /**
   * Displays a message and returns the numerical input.
   *
   * @param message the prompt message
   * @return the input number
   */
  public double promptNum(String message) {
    return Double.parseDouble(JOptionPane.showInputDialog(message));
  }

  /**
   * Displays a message and returns {@code true} if the input is 'y' or 'Y'.
   *
   * @param message the prompt message
   * @return {@code true} if the input is 'y' or 'Y', {@code false} otherwise
   */
  public boolean promptYN(String message) {
    return JOptionPane.showConfirmDialog(
      null,                     // parent component
      message,                  // message
      null,                     // title
      JOptionPane.YES_NO_OPTION // option type
    ) == JOptionPane.YES_OPTION;
  }

  /**
   * Displays a message and returns void when the button is pressed.
   *
   * @param message the prompt message
   */
  public void promptNext(String message) {
    JOptionPane.showOptionDialog(
      null,                          // parent component
      String.format("%s", message),  // message
      null,                          // title
      JOptionPane.OK_OPTION,         // option type
      JOptionPane.PLAIN_MESSAGE,     // message type
      null,                          // icon
      new String[] { "Next" },       // options
      null                           // initial value
    );
  }

  /**
   * Displays a message.
   *
   * @param message the message to display
   */
  public void display(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  /** Separates sections of output. Does nothing, in this case. */
  public void section() { /* do nothing */ }
}
