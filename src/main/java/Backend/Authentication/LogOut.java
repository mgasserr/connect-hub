package Backend.Authentication;

import Backend.Database.Account;
import Backend.Database.Activity;
import static Backend.Database.AccountsFileManagement.saveAllAccounts;

public class LogOut {

    public static void logout(Account acc) {
        acc.setStatus(Activity.Status.OFFLINE);
        saveAllAccounts();
    }
}
