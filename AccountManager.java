import java.util.ArrayList;

public class AccountManager {

    private ArrayList<Account> accounts;

    public AccountManager(){
        accounts = new ArrayList<>();
    }
    public void addAccount(int accountNumber, String name, String password, double balance){
    Account newAccount = new Account(accountNumber, name, password, balance);
    accounts.add(newAccount);
}

public Account findAccount(int accoutNumber) {
    for(Account acc : accounts){
        if(acc.getAccountNumber()== accoutNumber) {
            return acc;
        }
    }
    return null;
}

public boolean depositToAccount(int accountNumber, double amount) {
    Account acc =findAccount(accountNumber);
    if(acc != null){
        acc.deposit(amount);
        return true;
    }
    return false;
}

public boolean withdrawFromAccount(int accountNumber, double amount){
    Account acc = findAccount(accountNumber);
    if(acc != null){
        return acc.withdraw(amount);
    }
    return false;
}

public void displayAllAccounts(){
    for(Account acc : accounts){
        acc.printInfo();
        System.out.println("----------------------");
    }
}
public ArrayList<Account> getAllAccounts(){
    return accounts;
}
}



