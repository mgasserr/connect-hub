package Backend;

import Backend.Activity.Status;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Account {

    private final String accountId;
    private String Email;
    private String Username;
    private String Password;
    private LocalDate DOB;
    private Status Status;
    private ArrayList<Account> Friends;
    

    public Account(String userId, String Email, String Username, String Password, LocalDate DOB) {
        this.accountId = userId;
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
        this.DOB = DOB;
        this.Status = Status.ONLINE;
        Friends =new ArrayList<>();
    }

    public String getUserId() {
        return accountId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public LocalDate getDob() {
        return DOB;
    }

    public void setDob(LocalDate DOB) {
        this.DOB = DOB;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status Status) {
        this.Status = Status;
    }

    public ArrayList<Account> getFriends() {
        return Friends;
    }

    public void addFriends(Account friend) {
        this.Friends.add(friend);
    }
    public void removeFriends(Account friend) {
        this.Friends.remove(friend);
    }
    

}
