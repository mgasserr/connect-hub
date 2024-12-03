package Backend.Authentication;

import Backend.Account.Account;
import Backend.Account.Database;
import static Backend.Authentication.Validations.*;
import java.time.LocalDate;
import java.security.NoSuchAlgorithmException;

public abstract class SignUp {
    private Database database = Database.getInstance();

    public String signUp(String Email, String Username, String Password, LocalDate DOB) throws NoSuchAlgorithmException {
        String Hashpass = PasswordHash.hashPassword(Password);
        if (!(isValidEmail(Email))) {
            return "INVALIDEMAIL";
            //INVALID EMAIL ---> FRONTEND
        }
        if (!(isValidDate(DOB))) {
            return "INVALIDDOB";
            //BELOW 13 YEARS OLD ---> FRONTEND
        }
        database.readFromFile();
        if (database.containsEmail(Email)) {
            return "EMAILUSED";
            //ALREADY USED EMAIL ---> FRONTEND
        }
        if (database.containsUsername(Username)) {
            return "USERUSED";
            //ALREADY USED USERNAME ---> FRONTEND
        }

        Account A = new Account(Email, Username, Hashpass, DOB);
        database.addNewAccount(A);
        return "SIGNUPDONE";
    }
}
