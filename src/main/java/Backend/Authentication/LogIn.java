package Backend.Authentication;

import Backend.Database.AccountsFileManagement;
import java.security.NoSuchAlgorithmException;

public class LogIn {

    public boolean login(String UserName, String Password) throws NoSuchAlgorithmException {
        String Hashpass = PasswordHash.hashPassword(Password); //Hashing the entered password
        AccountsFileManagement.readFromFile();
        return AccountsFileManagement.loginCheck(UserName, Hashpass);

    }
}
