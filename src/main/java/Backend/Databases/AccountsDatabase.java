package Backend.Databases;

import Backend.Account.Account;
import Backend.Account.Activity;
import static Backend.Databases.Database.accounts;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

public class AccountsDatabase extends Database {

    private static AccountsDatabase accDatabase;

    private AccountsDatabase() {
        super();
    }

    public static AccountsDatabase getInstance() {
        if (accDatabase == null) {
            accDatabase = new AccountsDatabase();
        }
        return accDatabase;
    }

    @Override
    protected void read() {
        try {
            String jsonstring = new String(Files.readAllBytes(Paths.get("accounts.json")));
            JSONArray usersArray = new JSONArray(jsonstring);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
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
                System.out.println("Error in reading accounts from file");
            }
        }
    }

    @Override
    protected void save() {
        JSONArray usersArray = new JSONArray();
        for (Account acc : accounts) {
            JSONObject obj = new JSONObject();
            obj.put("userid", acc.getUserId());
            obj.put("email", acc.getEmail());
            obj.put("username", acc.getUsername());
            obj.put("password", acc.getPassword());
            obj.put("dob", acc.getDob());
            obj.put("status", acc.getStatus());
            if (acc.getProfile().getBio() == null) {
                obj.put("bio", "");
            } else {
                obj.put("bio", acc.getProfile().getBio());
            }
            obj.put("pfppath", acc.getProfile().getProfileImg());
            obj.put("coverpath", acc.getProfile().getCoverImg());
            usersArray.put(obj);
        }
        try {
            FileWriter file = new FileWriter("accounts.json");
            file.write("");
            file.write(usersArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
                System.out.println("Error in saving content in file");
        }
    }

}
