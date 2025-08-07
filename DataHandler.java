import java.io.*;
import java.util.ArrayList;

public class DataHandler {

    private static final String FILE_NAME ="accounts.txt";

    public static void saveAccount(ArrayList<Account> accounts){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for(Account acc : accounts){
                writer.write(acc.toFileString());
                writer.newLine();
            }
            System.out.println("Accounts Saved To File.");
        } catch(IOException e){
            System.out.println("Error saving accounts: "+ e.getMessage());
        }
    }

    public static ArrayList<Account> loadAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        File file = new File(FILE_NAME);
        if(!file.exists()){
        return accounts;
        
    }
    
    try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int accNum = Integer.parseInt(parts[0]);
            String name = parts[1];
            String password = parts[2];
            double balance = Double.parseDouble(parts[3]);
            Account account =new Account(accNum, name, password, balance);
            accounts.add(account);
        }
    } catch (IOException e){
        System.out.println("Error Loading Accounts: "+ e.getMessage());
    }
    return accounts;

    }
}