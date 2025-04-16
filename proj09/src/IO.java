import java.util.Scanner;

public class IO {
  private Scanner scan;

  public IO() {
    this.scan = new Scanner(System.in);
  }

  public void displayMenu(String programmerName) {
    System.out.printf(
      "Welcome to the Big12 Americana Bank program\ncreated by %s\n",
      programmerName
    );
  }

  public byte displayMenu() {
    System.out.print(
      "Please choose from the following choices below:\n\t1) Promotional Loan (preset loan amount, rate, term)\n\t2)Unique Loan (enter in loan values)\n\t3) Quit (Exit the program)\nPlease enter your selection (1-3): "
    );
    while (true) {
      
    }
  }
}
