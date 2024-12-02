package Backend.Authentication;

import Backend.Account;
import Backend.Activity;
import Backend.Database.SaveAccount;

/**
 *
 * @author LEGION
 */
public class LogOut {

    public static void logout(Account user) {
        user.setStatus(Activity.Status.OFFLINE);
        SaveAccount.saveAccount(user);
    }
}
