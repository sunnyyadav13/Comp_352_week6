public class SavingsAccount extends BankAccount {

    public static final double MINIMUM_BALANCE = 100.0;

    private final double interestRate;

    public SavingsAccount(String owner, double openingBalance, double interestRate) {
        super(owner, openingBalance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount < MINIMUM_BALANCE) {
            return false;
        }
        return super.withdraw(amount);
    }

    public void addInterest() {
        balance += balance * interestRate / 100;
    }
}
