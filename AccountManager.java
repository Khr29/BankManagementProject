import java.util.ArrayList;

// Manages a list of bank accounts
public class AccountManager {
    private ArrayList<Account> accounts;

    // start with an empty list
    public AccountManager() {
        accounts = new ArrayList<>();
    }

    // add a new account (password handled in Account)
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // get all accounts
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // find account by number
    public Account findAccount(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null; 
    }
}
