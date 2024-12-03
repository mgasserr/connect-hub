package Backend.Account;

import Backend.Account.Activity.Status;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class Account {

    private final String accountId;
    private String Email;
    private String Username;
    private String Password;
    private LocalDate DOB;
    private Status Status;
    static int accountsCount;
    private ProfileManagement Profile;

    public Account(String Email, String Username, String Password, LocalDate DOB) {
        accountsCount++;
        this.accountId = String.format("%04d", accountsCount);
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
        this.DOB = DOB;
        this.Status = Status.ONLINE;
        this.Profile = new ProfileManagement();
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

    public ProfileManagement getProfile() {
        return Profile;
    }

    public void setProfile(ProfileManagement Profile) {
        this.Profile = Profile;
    }

}
