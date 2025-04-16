public abstract class Account {
    protected final String accountId;
    protected final String clientId;
    protected double balance;

    public Account(String accountId, String clientId) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.balance = 0.0;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public abstract void withdraw(double amount);
}