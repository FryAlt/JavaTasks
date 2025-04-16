public class FixedDepositAccount extends Account implements InterestBearingAccount {
    private final double interestRate;

    public FixedDepositAccount(String accountId, String clientId, double interestRate) {
        super(accountId, clientId);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals are not allowed from fixed deposit accounts before maturity");
    }

    @Override
    public void applyInterest() {
        balance += balance * interestRate / 100;
    }
}