public class SavingsAccount extends Account implements InterestBearingAccount {
    private final double interestRate;

    public SavingsAccount(String accountId, String clientId, double interestRate) {
        super(accountId, clientId);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    @Override
    public void applyInterest() {
        balance += balance * interestRate / 100;
    }
}