import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        ArrayList<Account> loaded = DataHandler.loadAccounts();
        for (Account acc : loaded){
            manager.addAccount(acc.getAccountNumber(),acc.getName() ,acc.getPassword(), acc.getBalance());
        }

        System.out.println("How Many Accounts Do You Want To Create?");
        int count = input.nextInt();
        input.nextLine();

        for (int i=0; i < count ;i++){
            System.out.println("\nCreating Account"+ (i + 1));

            System.out.println("Enter Account Number");
            int accNum = input.nextInt();
            input.nextLine();

            System.out.println("Enter Name");
            String name = input.nextLine();

            System.out.println("Enter Password");
            String password = input.nextLine();

            System.out.println("Enter Initial Balance");
            double balance = input.nextDouble();

            manager.addAccount(accNum, name, password, balance);
            System.out.println("Account Created Successfully");
        }
        System.out.println("\nEnter Account Number To Deposit");
        int depNum = input.nextInt();
        input.nextLine();

        Account depAcc = manager.findAccount(depNum);
        if (depAcc != null){
            System.out.println("Enter Password: ");
            String pass = input.nextLine();

            if (depAcc.getPassword().equals(pass)) {
                System.out.println("Enter Amount To deposit: ");
                double depAmount = input.nextDouble();
                input.nextLine();
                manager.depositToAccount(depNum, depAmount);
                System.out.println("Deposit successful.");

            } else{
                System.out.println("Incorrect Password. Deposit Denied");
            }
        } else {
            System.out.println("Account Not Found");
        }

        System.out.println("\nEnter Account To withdraw from: ");
        int withNum = input.nextInt();
        input.nextLine();
        Account withAcc = manager.findAccount(withNum);
        if(withAcc != null){
            System.out.println("Enter Password: ");
            String pass = input.nextLine();

            if (withAcc.getPassword().equals(pass)) {
                System.out.println("Enter Amount To Withdraw:  ");
                double withAmount = input.nextDouble();
                input.nextLine();

                boolean success = manager.withdrawFromAccount(withNum,withAmount);
                if(success){
                    System.out.println("Withdrawal Successful");
                }else{
                    System.out.println(" Insufficient Balance");
                }
                
            } else {
                System.out.println(" Incorrect Password. Withdrawal Denied.");
            }
        }else {
            System.out.println(" Account Not Found.");
        }
        System.out.println("\nAll Account: ");
        manager.displayAllAccounts();

        DataHandler.saveAccount(manager.getAllAccounts());
        input.close();
    }

}