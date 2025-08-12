public class Account {

    private String name;
    private int accountNumber;
    private double balance;

    // make a new account
    public Account(String name, int accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // read details
    public String getName() {
        return name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // add or take money
    public void deposit(double amount) {
        balance += amount;
    }
    

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // text version for saving
    @Override
    public String toString() {
        return name + "," + accountNumber + "," + balance;
    }
}
