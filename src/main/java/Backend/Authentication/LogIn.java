package Backend.Authentication;

import java.util.*;
import static Backend.Authentication.PasswordHash.hashPassword;
import Backend.Database.LoadedAccounts;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author LEGION
 */
public class LogIn {
    public boolean login(String UserName, String Password) throws NoSuchAlgorithmException{
        
        String Hashpass=PasswordHash.hashPassword(Password);
        LoadedAccounts.readFromFile();
        return LoadedAccounts.loginCheck(UserName, Hashpass);
        
    }
}
