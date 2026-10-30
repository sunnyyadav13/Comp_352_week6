/**
 * A bank account that earns interest and must keep a minimum balance after withdrawals.
 */
public class SavingsAccount extends BankAccount {

    /** The balance that must remain after every withdrawal. */
    public static final double MINIMUM_BALANCE = 100.0;

    private final double interestRate;

    /**
     * Opens a savings account.
     *
     * <p>The opening balance can be below {@link #MINIMUM_BALANCE}; the minimum is only
     * applied at each withdrawal.
     *
     * @param owner          the owner's name
     * @param openingBalance the starting balance; must not be negative
     * @param interestRate   the yearly interest rate in percent, for example 2.5 for 2.5%
     * @throws IllegalArgumentException if {@code openingBalance} is negative
     */
    public SavingsAccount(String owner, double openingBalance, double interestRate) {
        super(owner, openingBalance);
        if (openingBalance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException("opening balance must be at least the minimum balance");
        }
        this.interestRate = interestRate;
    }

    /**
     * Returns the yearly interest rate.
     *
     * @return the rate in percent, for example 2.5 for 2.5%
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Takes money out, but only if at least {@link #MINIMUM_BALANCE} stays in the account.
     *
     * <p>Overrides {@link BankAccount#withdraw(double)}: it first compares the result with the minimum balance,
     * then applies the normal rules.
     *
     * @param amount the amount to take out
     * @return {@code true} if the money was withdrawn; {@code false} if the amount is invalid
     *         or the balance would drop below the minimum
     */
    @Override
    public boolean withdraw(double amount) {
        if (balance - amount < MINIMUM_BALANCE) {
            return false;
        }
        return super.withdraw(amount);
    }

    /**
     * Adds one year of interest to the balance.
     *
     * <p>Example: a balance of 100.00 at 2.5% becomes 102.50.
     */
    public void addInterest() {
        balance += balance * interestRate / 100 / 12;
    }
}
