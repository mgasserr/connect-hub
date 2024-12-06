package Backend.Account;

import Backend.Account.Activity.Status;
import java.time.LocalDate;

public class Account {

    private final String userId;
    private String Email;
    private String Username;
    private String Password;
    private LocalDate DOB;
    private Status Status;
    private static int accountsCount;
    private ProfileManagement Profile;
    private FriendsManagement friendsManagement;
    private ContentManagement contentManagement;

    public Account(String Email, String Username, String Password, LocalDate DOB) {
        accountsCount++;
        this.userId = String.format("%04d", accountsCount);
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
        this.DOB = DOB;
        this.Status = Status.ONLINE;
        this.Profile = new ProfileManagement(this, null, null, null);
        this.friendsManagement = new FriendsManagement(this);
        this.contentManagement = new ContentManagement(this);
    }

    public static void resetAccountsCount() {
        accountsCount = 0;
    }

    public FriendsManagement getFriendsManagement() {
        return friendsManagement;
    }

    public ContentManagement getContentManagement() {
        return contentManagement;
    }

    public String getUserId() {
        return userId;
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

    public ProfileManagement getProfile() {
        return Profile;
    }

    public void setProfile(ProfileManagement Profile) {
        this.Profile = Profile;
    }

}
