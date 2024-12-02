package Backend.Authentication;

import Backend.Database.Account;
import static Backend.Database.AccountsFileManagement.*;
import static Backend.Database.Validations.*;
import java.time.LocalDate;
import java.security.NoSuchAlgorithmException;

public class SignUp {

    public static String signUp(String Email, String Username, String Password, LocalDate DOB) throws NoSuchAlgorithmException {
        String Hashpass = PasswordHash.hashPassword(Password);
        if (!(isValidEmail(Email))) {
            return "INVALIDEMAIL";
            //INVALID EMAIL ---> FRONTEND
        }
        if (!(isValidDate(DOB))) {
            return "INVALIDDOB";
            //BELOW 13 YEARS OLD ---> FRONTEND
        }
        readFromFile();
        if (containsEmail(Email)) {
            return "EMAILUSED";
            //ALREADY USED EMAIL ---> FRONTEND
        }
        if (containsUsername(Username)) {
            return "USERUSED";
            //ALREADY USED USERNAME ---> FRONTEND
        }

        Account A = new Account(Email, Username, Hashpass, DOB);
        saveNewAccount(A);
        return "SIGNUPDONE";
    }
}
