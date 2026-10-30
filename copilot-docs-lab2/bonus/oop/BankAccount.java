/**
 * A simple bank account with an owner and a balance.
 *
 * <p>Every new account gets the next account number automatically, starting at 1001.
 */
public class BankAccount {

    private static int nextNumber = 1001;

    private final int number;
    private final String owner;
    /** The current balance. Protected so that subclasses such as {@link SavingsAccount} can change it. */
    protected double balance;

    /**
     * Opens an account and gives it the next account number (1001, 1002, ...).
     *
     * @param owner          the owner's name
     * @param openingBalance the starting balance; must not be negative
     * @throws IllegalArgumentException if {@code openingBalance} is negative
     */
    public BankAccount(String owner, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("opening balance cannot be negative");
        }
        this.number = nextNumber;
        nextNumber++;
        this.owner = owner;
        this.balance = openingBalance;
    }

    /**
     * Returns the account number.
     *
     * @return the number that the constructor gave to the account
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the owner's name.
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the current balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Adds money to the account.
     *
     * @param amount the amount to add; must be greater than zero
     * @throws IllegalArgumentException if {@code amount} is zero or negative
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("deposit must be positive");
        }
        balance += amount;
    }

    /**
     * Takes money out of the account, if possible.
     *
     * <p>Unlike {@link #deposit(double)}, this method does not throw an exception. It returns
     * {@code false} and leaves the balance unchanged when {@code amount} is zero, negative,
     * or more than the balance.
     *
     * @param amount the amount to take out
     * @return {@code true} if the money was withdrawn, {@code false} otherwise
     */
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    /**
     * Two accounts are equal when they have the same account number.
     *
     * <p>The owner and balance are not compared.
     *
     * @param other the object to compare with; can be {@code null}
     * @return {@code true} if {@code other} is an account with the same number
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BankAccount)) {
            return false;
        }
        BankAccount that = (BankAccount) other;
        return number == that.number;
    }

    /**
     * Returns a hash code based on the account number, consistent with {@link #equals(Object)}.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }

    /**
     * Describes the account, for example {@code "Account 1001 (Ada): 50.00"}.
     *
     * @return the number, owner and balance rounded to two decimal places
     */
    @Override
    public String toString() {
        return "Account " + number + " (" + owner + "): " + String.format("%.2f", balance);
    }
}
