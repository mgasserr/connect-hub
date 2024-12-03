package Backend.Authentication;

import Backend.Account.Account;
import Backend.Account.Database;
import Backend.Account.Activity;
import java.security.NoSuchAlgorithmException;

public class Log {

    private Database database = Database.getInstance();

    public boolean login(String UserName, String Password) throws NoSuchAlgorithmException {
        String Hashpass = PasswordHash.hashPassword(Password); //Hashing the entered password
        database.readFromFile();
        return database.loginCheck(UserName, Hashpass);

    }

    public void logout(Account acc) {
        acc.setStatus(Activity.Status.OFFLINE);
        database.saveAllAccounts();
    }
}
