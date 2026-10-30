public class BankAccount {

    private static int nextNumber = 1001;

    private final int number;
    private final String owner;
    protected double balance;

    public BankAccount(String owner, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("opening balance cannot be negative");
        }
        this.number = nextNumber;
        nextNumber++;
        this.owner = owner;
        this.balance = openingBalance;
    }

    public int getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("deposit must be positive");
        }
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

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

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }

    @Override
    public String toString() {
        return "Account " + number + " (" + owner + "): " + String.format("%.2f", balance);
    }
}
