public class Account {

    private String name;
    private int accountNumber;
    private double balance;
    private String password; // new field

    // make a new account with password
    public Account(String name, int accountNumber, double balance, String password) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.password = password;
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

    public String getPassword() { // new getter
        return password;
    }

    public void setPassword(String password) { // new setter
        this.password = password;
    }

    // add or take money
    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount, String inputPassword) { // password check
        if (inputPassword.equals(password) && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // transfer to another account (static helper)
    public static boolean transfer(Account from, Account to, double amount, String inputPassword) {
        if (from.withdraw(amount, inputPassword)) {
            to.deposit(amount);
            return true;
        }
        return false;
    }

    // text version for saving
    @Override
    public String toString() {
        return name + "," + accountNumber + "," + balance + "," + password;
    }
}
