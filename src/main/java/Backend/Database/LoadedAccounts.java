package Backend.Database;

import Backend.Account;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class LoadedAccounts {

    private static ArrayList<Account> accounts = new ArrayList<>();

    public static ArrayList<Account> readFromFile() {
        try {
            String jsonstring = new String(Files.readAllBytes(Paths.get("users.json")));
            JSONArray usersArray = new JSONArray(jsonstring);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                String id = userJson.getString("userId");
                String email = userJson.getString("email");
                String username = userJson.getString("username");
                String password = userJson.getString("password");
                LocalDate dob = LocalDate.parse(userJson.getString("dob"), formatter);
                String status = userJson.getString("status");
                accounts.add(new Account(id, email, username, password, dob, status));
            }
        } catch (IOException ex) {
            System.out.println("Can't open/read accounts.json");
        }
        return accounts;
    }

    public static boolean containsUsername(String string) {
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsEmail(String string) {
        for (Account account : accounts) {
            if (account.getEmail().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    public static boolean loginCheck(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                if (account.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

}
