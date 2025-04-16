import java.util.ArrayList;
import java.util.List;

public class Client {
    private final String clientId;
    private final String name;
    private final List<String> accounts;

    public Client(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public List<String> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public void addAccount(String accountId) {
        accounts.add(accountId);
    }
}