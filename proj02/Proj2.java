/**
 * Proj2.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * Prompts the user to purchase 2025 season tickets for the KC Royals. The user
 * selects from various ticket plans and seating options, chooses a number of
 * seats, and can optionally purchase a single parking pass. 
 * 
 * EXTRA CREDIT INCLUDED (all of it)
 */

import java.util.Scanner;

public class Proj2 {
  /**
   * Main
   */
  public static void main(String[] args) {
    boolean running = true;
    while (running) {
      printSplash();
      order();

      // Ask to re-run program
      System.out.println(); // spacing
      running = ask("Would you like to order another pass? (Y or N):");
    }
  }

  /**
   * Prints a splash banner for the application to the console.
   */
  public static void printSplash() {
    System.out.println("*** Welcome to the Kansas City Royals 2025 Season Ticketing Application***");
    System.out.println("            (Application developed by Emma Jaskowiec)");
    System.out.println("                     ----CROWNED KC!----\n");
  }

  /**
   * Prompts for the user's ticket order.
   */
  public static void order() {
    /* Since programmer-defined objects aren't allowed, data is stored in
     * related arrays and referenced by index. Semantically, it might make more
     * sense to group related arrays within arrays, but that feels excessive, to
     * me. */
    final String[] TICKET_PLANS = {
      "Full Season Plan",
      "Half Season Plan",
      "Quarter Season Plan"
    };
    final int[] TICKET_GAMES = {81, 40, 20};
    final String[] TICKET_DESCRIPTIONS = {
      "tickets to all " + TICKET_GAMES[0] + " regular season games!",
      "tickets to " + TICKET_GAMES[1] + " regular season games",
      "tickets to " + TICKET_GAMES[2] + " regular season games"
    };
    final String[] SEATING_OPTIONS = {
      "BATS Crown Club Seats",
      "Diamond Club Box",
      "Dugout Box",
      "Dugout Plaza",
      "Field Box",
      "Field Plaza",
      "Outfield Box",
      "View Box"
    };
    final int[] SEATING_PRICES = {300, 125, 75, 50, 40, 30, 28, 20};
    final double TAX = 6.25;

    // make order selections
    int ticketPlanIndex = getTicketPlan(
      TICKET_PLANS,
      TICKET_DESCRIPTIONS
    );
    System.out.println(); // spacing
    int seatingOptionIndex = getSeatingOption(
      SEATING_OPTIONS,
      SEATING_PRICES
    );
    int numSeats = getNumSeats();
    int parkingRate = getParkingRate(ticketPlanIndex);
    System.out.println(); // spacing

    // print order summary
    System.out.printf(
      "You purchased the %s %d-game / %d %s tickets %s Parking%n",
      TICKET_PLANS[ticketPlanIndex],
      TICKET_GAMES[ticketPlanIndex],
      numSeats,
      SEATING_OPTIONS[seatingOptionIndex],
      parkingRate > 0 ? "with" : "without"
    );

    // calculate totals
    double ticketTotal = TICKET_GAMES[ticketPlanIndex] * SEATING_PRICES[seatingOptionIndex] * numSeats;
    double ticketTax = (TAX / 100.0) * ticketTotal;
    double parkingCost = parkingRate * TICKET_GAMES[ticketPlanIndex]; 
    double grandTotal = ticketTotal + ticketTax + parkingCost; 

    // print totals
    System.out.printf("   Ticket Total: $%,.2f%n", ticketTotal);
    System.out.printf("   Tax: $%,.2f%n", ticketTax);
    if (parkingRate > 0) {
      System.out.printf("Parking: $%,.2f%n", parkingCost);
    }
    System.out.printf("   Grand Total: $%,.2f%n%n", grandTotal);

    // confirm order
    if (ask("   Confirm Order? (Y or N)")) {
      System.out.printf("   Order was confirmed. $%,.2f will be charged to the account on file%n", grandTotal);
    } else {
      System.out.println("  Purchase cancelled.");
    }
  }

  /**
   * Prompts the user to select a ticket plan.
   * @param plans - The available ticket plans.
   * @param descriptions - The descriptions of the available ticket plans.
   * @return The index of the selected plan. 
   */
  public static int getTicketPlan(String[] plans, String[] descriptions) {
    Scanner s = new Scanner(System.in);

    // print options
    System.out.println("Please select one of the season Ticket Plans Listed below:");
    for (int i = 1; i <= plans.length; i++) {
      System.out.printf("        %d) %s (%s)%n", i, plans[i - 1], descriptions[i - 1]);
    }
    System.out.println(); // spacing

    int planIndex; // set to invalid index to indicate no selection
    while (true) {
      // validate the type
      try {
        System.out.print("Selection: ");
        planIndex = Integer.parseInt(s.nextLine().trim());
      } catch (NumberFormatException ignore) {
        System.out.println("Not a valid Plan selected. Enter 1 to " + plans.length + " only");
        continue;
      }

      // validate the range
      if (planIndex < 1 || planIndex > plans.length) {
        System.out.println("Input is out of range; please enter an integer from 1 to " + plans.length + "(inclusive).");
        continue;
      }

      break; // if the selection is valid, exit the loop
    }

    return planIndex - 1; // shift for zero indexing
  }

  /**
   * Prompts the user to select a seating option.
   * @param options - The available seating options.
   * @param prices - The prices of the available seating options.
   * @return The index of the selected option. 
   */
  public static int getSeatingOption(String[] options, int[] prices) {
    Scanner s = new Scanner(System.in);

    // print options
    System.out.println("Please select one of the Seating Options listed below:\n\t   Seating              Cost Per Game");
    for (int i = 1; i <= options.length; i++) {
      System.out.printf("        %d) %-28s $%d%n", i, options[i - 1], prices[i - 1]);
    }

    int optionIndex;
    while (true) {
      // validate the type
      try {
        System.out.print("   Selection: ");
        optionIndex = Integer.parseInt(s.nextLine().trim());
      } catch (NumberFormatException ignore) {
        System.out.println(
          "Not a valid Plan selected. Enter 1-" + options.length + " only"
        );
        continue;
      }

      // validate the range
      if (optionIndex < 1 || optionIndex > options.length) {
        System.out.println(
          "Not a valid Plan selected. Enter 1-" + options.length + " only"
        );
        continue;
      }

      break; // if the selection is valid, exit the loop
    }

    return optionIndex - 1; // shift for zero indexing
  }
  /**
   * Prompts the user to select an integer number of stadium seats.
   * @return The number of seats to purchase.
   */
  public static int getNumSeats() {
    Scanner s = new Scanner(System.in);

    int numSeats;
    while (true) {
      try {
        System.out.print("        How many seats would you like to purchase? ");
        numSeats = Integer.parseInt(s.nextLine().trim());
      } catch (NumberFormatException ignore) {
        System.out.println("Not a valid input. Enter a positive integer.");
        continue;
      }
      break;
    }

    return numSeats;
  }

  /**
   * Prompts the user to purchase (or decline to purchase) a parking pass.
   * @param ticketPlanIndex - The index of the user's selected ticket plan.
   * @return The cost of parking (per game).
   */
  public static int getParkingRate(int ticketPlanIndex) {
    // parking is cheaper if the first ticket plan is purchased
    int price = ticketPlanIndex == 0 ? 18 : 19;
    System.out.println("   A single parking pass is available for purchase at a discounted rate of $" + price + " per game (regularly $20).");
    return ask("   Would you like to include parking? (Y or N):") ? price : 0;
  }

  /**
   * Asks a yes or no question and prompts the user for a response.
   * @param prompt - The question to ask.
   * @return A boolean representing the user's answer (true for yes, false for
   *         no).
   */
  public static boolean ask(String prompt) {
    Scanner s = new Scanner(System.in);

    while (true) {
      System.out.print(prompt + " ");
      String input = s.nextLine().trim();
      if (!input.equals("")) {
        switch (Character.toUpperCase(input.charAt(0))) {
          case 'Y': return true;
          case 'N': return false;
        }
      }
      System.out.println("Please enter Y or N.");
    }
  }
}
