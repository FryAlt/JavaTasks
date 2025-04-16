import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {
    private static final Logger logger = Logger.getLogger(Bank.class.getName());
    private final Map<String, Client> clients;
    private final Map<String, Account> accounts;

    public Bank() {
        this.clients = new HashMap<>();
        this.accounts = new HashMap<>();
        logger.log(Level.INFO, "Bank initialized");
    }

    public Client createClient(String clientId, String name) {
        if (clients.containsKey(clientId)) {
            logger.log(Level.WARNING, "Client with ID " + clientId + " already exists");
            throw new IllegalArgumentException("Client with ID " + clientId + " already exists");
        }
        Client client = new Client(clientId, name);
        clients.put(clientId, client);
        logger.log(Level.INFO, "Created new client: " + clientId + " - " + name);
        return client;
    }

    public Account createAccount(String accountId, String clientId, AccountType type, double interestRate) {
        if (!clients.containsKey(clientId)) {
            logger.log(Level.WARNING, "Client with ID " + clientId + " does not exist");
            throw new IllegalArgumentException("Client with ID " + clientId + " does not exist");
        }
        if (accounts.containsKey(accountId)) {
            logger.log(Level.WARNING, "Account with ID " + accountId + " already exists");
            throw new IllegalArgumentException("Account with ID " + accountId + " already exists");
        }

        Account account;
        switch (type) {
            case SAVINGS:
                account = new SavingsAccount(accountId, clientId, interestRate);
                break;
            case CHECKING:
                account = new CheckingAccount(accountId, clientId);
                break;
            case FIXED_DEPOSIT:
                account = new FixedDepositAccount(accountId, clientId, interestRate);
                break;
            default:
                throw new IllegalArgumentException("Unknown account type");
        }

        accounts.put(accountId, account);
        clients.get(clientId).addAccount(accountId);
        logger.log(Level.INFO, "Created new " + type + " account: " + accountId + " for client: " + clientId);
        return account;
    }

    public void deposit(String accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            logger.log(Level.WARNING, "Account with ID " + accountId + " does not exist");
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist");
        }
        accounts.get(accountId).deposit(amount);
        logger.log(Level.INFO, "Deposited " + amount + " to account: " + accountId);
    }

    public void withdraw(String accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            logger.log(Level.WARNING, "Account with ID " + accountId + " does not exist");
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist");
        }
        accounts.get(accountId).withdraw(amount);
        logger.log(Level.INFO, "Withdrew " + amount + " from account: " + accountId);
    }

    public void transfer(String fromAccountId, String toAccountId, double amount) {
        if (!accounts.containsKey(fromAccountId)) {
            logger.log(Level.WARNING, "Source account with ID " + fromAccountId + " does not exist");
            throw new IllegalArgumentException("Source account with ID " + fromAccountId + " does not exist");
        }
        if (!accounts.containsKey(toAccountId)) {
            logger.log(Level.WARNING, "Destination account with ID " + toAccountId + " does not exist");
            throw new IllegalArgumentException("Destination account with ID " + toAccountId + " does not exist");
        }

        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        logger.log(Level.INFO, "Transferred " + amount + " from account: " + fromAccountId + " to account: " + toAccountId);
    }

    public double getBalance(String accountId) {
        if (!accounts.containsKey(accountId)) {
            logger.log(Level.WARNING, "Account with ID " + accountId + " does not exist");
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist");
        }
        return accounts.get(accountId).getBalance();
    }

    public void applyInterest() {
        for (Account account : accounts.values()) {
            if (account instanceof InterestBearingAccount) {
                ((InterestBearingAccount) account).applyInterest();
                logger.log(Level.INFO, "Applied interest to account: " + account.getAccountId());
            }
        }
    }

    public List<String> getClientAccounts(String clientId) {
        if (!clients.containsKey(clientId)) {
            logger.log(Level.WARNING, "Client with ID " + clientId + " does not exist");
            throw new IllegalArgumentException("Client with ID " + clientId + " does not exist");
        }
        return new ArrayList<>(clients.get(clientId).getAccounts());
    }
}