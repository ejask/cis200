import java.util.ArrayList;

public class MortgageApp {
  public static void main(String[] args) {
    final String PROGRAMMER_NAME = "Emma Jaskowiec";

    // input states
    final byte STATE_PROMO_LOAN = 1;
    final byte STATE_UNIQUE_LOAN = 2;
    final byte STATE_QUIT = 3;

    // promotional loan values
    final double PROMO_PRINCIPAL = 300000.0;
    final double PROMO_INTEREST = 5.25;
    final int PROMO_TERM = 30;

    ArrayList<Mortgage> loans = new ArrayList<>();
    IO view = new IO();

    // initial menu and state (i.e. menu choice)
    view.displayMenu(PROGRAMMER_NAME);
    byte state = view.displayMenu();

    // main input loop
    while (state != STATE_QUIT) {
      Mortgage loan = new Mortgage();
      loan.setAccountName(view.readName());

      /*
       * It hurts me to put the input validation in the view instead of the
       * controller. It's not any simpler, and it gives the view too many
       * responsibilities. Just wanted to get that off my chest.
       */
      try { // try block prevents data loss on illegal state
        String loanTypeStr;
        switch (state) {
          case STATE_PROMO_LOAN: // choice one
            loanTypeStr = "Promotional";
            loan.setPrincipal(PROMO_PRINCIPAL);
            loan.setInterest(PROMO_INTEREST);
            loan.setTerm(PROMO_TERM);
            break;
          case STATE_UNIQUE_LOAN: // choice two
            loanTypeStr = "Unique";
            loan.setPrincipal(view.readPrincipal(
              Mortgage.MIN_PRINCIPAL,
              Mortgage.MAX_PRINCIPAL
            ));
            loan.setInterest(view.readInterest(
              Mortgage.MIN_INTEREST,
              Mortgage.MAX_INTEREST
            ));
            loan.setTerm(view.readTerm(
              Mortgage.MIN_TERM,
              Mortgage.MAX_TERM
            ));
            break;
          default: throw new IllegalStateException("Illegal state: " + state);
        } // end switch
        loans.add(loan);
        view.displayLoan(loan.toString(), loanTypeStr);
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      } finally { // get new state
        state = view.displayMenu();
      }
    } // end input loop

    // display results
    view.displaySuccess();
    for (Mortgage loan : loans) {
      view.displayLoan(loan.toString());
    }
  } // end main
}
