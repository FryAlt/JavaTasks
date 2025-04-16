public class CheckingAccount extends Account {
    public CheckingAccount(String accountId, String clientId) {
        super(accountId, clientId);
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
}
