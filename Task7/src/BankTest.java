import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private Bank bank;
    private String clientId;
    private String savingsAccountId;
    private String checkingAccountId;
    private String fixedDepositAccountId;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        clientId = "client1";
        bank.createClient(clientId, "John Doe");
        
        savingsAccountId = "acc1";
        checkingAccountId = "acc2";
        fixedDepositAccountId = "acc3";
        
        bank.createAccount(savingsAccountId, clientId, AccountType.SAVINGS, 5.0);
        bank.createAccount(checkingAccountId, clientId, AccountType.CHECKING, 0);
        bank.createAccount(fixedDepositAccountId, clientId, AccountType.FIXED_DEPOSIT, 7.0);
    }

    @Test
    public void testCreateClient() {
        assertThrows(IllegalArgumentException.class, () -> bank.createClient(clientId, "Duplicate"));
    }

    @Test
    public void testCreateAccountForNonExistentClient() {
        assertThrows(IllegalArgumentException.class, 
            () -> bank.createAccount("acc4", "nonexistent", AccountType.SAVINGS, 5.0));
    }

    @Test
    public void testDeposit() {
        bank.deposit(savingsAccountId, 1000);
        assertEquals(1000, bank.getBalance(savingsAccountId));
        
        assertThrows(IllegalArgumentException.class, 
            () -> bank.deposit(savingsAccountId, -100));
    }

    @Test
    public void testWithdraw() {
        bank.deposit(checkingAccountId, 1000);
        bank.withdraw(checkingAccountId, 500);
        assertEquals(500, bank.getBalance(checkingAccountId));
        
        assertThrows(IllegalArgumentException.class, 
            () -> bank.withdraw(checkingAccountId, 600));
    }

    @Test
    public void testWithdrawFromFixedDeposit() {
        bank.deposit(fixedDepositAccountId, 1000);
        assertThrows(UnsupportedOperationException.class, 
            () -> bank.withdraw(fixedDepositAccountId, 500));
    }

    @Test
    public void testTransfer() {
        bank.deposit(savingsAccountId, 1000);
        bank.transfer(savingsAccountId, checkingAccountId, 500);
        
        assertEquals(500, bank.getBalance(savingsAccountId));
        assertEquals(500, bank.getBalance(checkingAccountId));
        
        assertThrows(IllegalArgumentException.class, 
            () -> bank.transfer(savingsAccountId, checkingAccountId, 600));
    }

    @Test
    public void testApplyInterest() {
        bank.deposit(savingsAccountId, 1000);
        bank.applyInterest();
        assertEquals(1050, bank.getBalance(savingsAccountId), 0.001);
        
        bank.deposit(fixedDepositAccountId, 1000);
        bank.applyInterest();
        assertEquals(1070, bank.getBalance(fixedDepositAccountId), 0.001);
        
        // Checking account should not earn interest
        bank.deposit(checkingAccountId, 1000);
        bank.applyInterest();
        assertEquals(1000, bank.getBalance(checkingAccountId), 0.001);
    }

    @Test
    public void testGetClientAccounts() {
        assertEquals(3, bank.getClientAccounts(clientId).size());
        assertThrows(IllegalArgumentException.class, 
            () -> bank.getClientAccounts("nonexistent"));
    }
}