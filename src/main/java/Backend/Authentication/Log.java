package Backend.Authentication;

import Backend.Account.Account;
import Backend.Account.Database;
import Backend.Account.Activity;
import java.security.NoSuchAlgorithmException;

public class Log {

    private Database database = Database.getInstance();

    public Account login(String UserName, String Password) {
        String Hashpass = null;
        try {
            Hashpass = PasswordHash.hashPassword(Password); //Hashing the entered password
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error in login method");
        }
        if (database.loginCheck(UserName, Hashpass)) {
            return database.getAccount(UserName);
        }
        return null;
    }

    public void logout(Account acc) {
        acc.setStatus(Activity.Status.OFFLINE);
        database.saveAllAccounts();
    }
}
