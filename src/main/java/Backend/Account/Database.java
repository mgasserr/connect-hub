package Backend.Account;

import static Backend.Account.Account.accountsCount;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Database {

    private static ArrayList<Account> accounts = new ArrayList<>();

    //READ DATA FROM JSON
    public static void readFromFile() {
        try {
            accounts.removeAll(accounts);
            accountsCount = 0;
            String jsonstring = new String(Files.readAllBytes(Paths.get("accounts.json")));
            JSONArray usersArray = new JSONArray(jsonstring);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                String id = userJson.getString("userid");
                String email = userJson.getString("email");
                String username = userJson.getString("username");
                String password = userJson.getString("password");
                LocalDate dob = LocalDate.parse(userJson.getString("dob"), formatter);
                Account user = new Account(email, username, password, dob);
                Activity.Status status = Activity.Status.valueOf(userJson.getString("status"));
                user.setStatus(status);
                accounts.add(user);
            }
        } catch (IOException ex) {
            try {
                FileWriter file = new FileWriter("accounts.json");
                file.write((new JSONArray()).toString(3));
                file.close();
            } catch (IOException ex1) {
                System.out.println("Error in readfromFile");
            }
        }
    }

    public static JSONArray saveAllAccounts() {
        JSONArray usersArray = new JSONArray();
        for (Account acc : accounts) {
            JSONObject obj = new JSONObject();
            obj.put("userid", acc.getUserId());
            obj.put("email", acc.getEmail());
            obj.put("username", acc.getUsername());
            obj.put("password", acc.getPassword());
            obj.put("dob", acc.getDob());
            obj.put("status", acc.getStatus());
            usersArray.put(obj);
        }
        return usersArray;
    }

    public static void saveNewAccount(Account account) {
        JSONArray usersArray = saveAllAccounts();
        JSONObject obj = new JSONObject();
        obj.put("userid", account.getUserId());
        obj.put("email", account.getEmail());
        obj.put("username", account.getUsername());
        obj.put("password", account.getPassword());
        obj.put("dob", account.getDob());
        obj.put("status", account.getStatus());
        usersArray.put(obj);
        accounts.add(account);
        try {
            FileWriter file = new FileWriter("accounts.json");
            file.write("");
            file.write(usersArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error in saveAccount");
        }
    }

    //VALIDATION TO CHECK IF THE USERNAME ALREADY USED 
    public static boolean containsUsername(String string) {
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    //VALIDATION TO CHECK IF THE EMAIL ALREADY USED 
    public static boolean containsEmail(String string) {
        for (Account account : accounts) {
            if (account.getEmail().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    //VALIDATION TO CHECK IF THE ENTERED USERNAME AND PASSWORD ARE CORRECT
    public static boolean loginCheck(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                if (account.getPassword().equals(password)) {
                    account.setStatus(Activity.Status.ONLINE);
                    saveAllAccounts();
                    return true;
                }
            }
        }
        return false;
    }

}
