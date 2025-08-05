public class Account {
    private int accountNumber;
    private String name;
    private double balance;
    private String password;

    public Account(int accountNumber, String name, String password, double balance){
        this.accountNumber = accountNumber;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
        }
    }
    
    public boolean withdraw(double amount){
        if (amount > 0 && balance >= amount ){
            balance -= amount;
            return true;
        }
        return false;
    }

    public int getAccountNumber() {return accountNumber;}
    public String getName() {return name;}
    public String getPassword() {return password;}
    public double getBalance() {return balance;}


    public String toFileString() {
        return accountNumber + "," + name + "," + password + "," + balance;
    }

    public void printInfo() {
        System.out.println("Account #" + accountNumber);
        System.out.println("Name :" + name);
        System.out.println("Balance: $" + balance);
    }



}