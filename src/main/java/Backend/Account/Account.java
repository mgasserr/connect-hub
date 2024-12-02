package Backend.Account;

import Backend.Account.Activity.Status;
import java.time.LocalDate;
import java.util.ArrayList;

public class Account {

    private final String accountId;
    private String Email;
    private String Username;
    private String Password;
    private LocalDate DOB;
    private Status Status;
    private ArrayList<Account> Friends;
    static int accountsCount;
    private String ProfileImg; 
    private String CoverImg;

    public Account(String Email, String Username, String Password, LocalDate DOB) {
        accountsCount++;
        this.accountId = String.format("%04d", accountsCount);
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
        this.DOB = DOB;
        this.Status = Status.ONLINE;
        Friends = new ArrayList<>();

    }

    public String getProfileImg() {
        return ProfileImg;
    }

    public void setProfileImg(String ProfileImg) {
        this.ProfileImg = ProfileImg;
    }

    public String getCoverImg() {
        return CoverImg;
    }

    public void setCoverImg(String CoverImg) {
        this.CoverImg = CoverImg;
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
