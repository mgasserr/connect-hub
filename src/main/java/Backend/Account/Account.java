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
    private ArrayList<Account> Friends;
    static int accountsCount;
    JFileChooser fileChooser = new JFileChooser();
    private ImageIcon ProfileImg;
    private ImageIcon CoverImg;

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

    public ImageIcon getProfileImg() {
        return ProfileImg;
    }

    public void setProfileImg() {
        fileChooser.setDialogTitle("Choose Profile Picture");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            ImageIcon profileImage = new ImageIcon(imagePath);
            this.ProfileImg = profileImage;

        }
    }

    public ImageIcon getCoverImg() {

        return CoverImg;
    }

    public void setCoverImg() {
        fileChooser.setDialogTitle("Choose cover Picture");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            ImageIcon CoverImg = new ImageIcon(imagePath);
            this.CoverImg = CoverImg;

        }

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
