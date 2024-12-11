package Backend.Account;

import Backend.Account.Activity.Status;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import java.time.LocalDate;
import java.util.ArrayList;

public class Account {

    private final String userId;
    private String Email;
    private String Username;
    private String Password;
    private LocalDate DOB;
    private Status status;
    private static int accountsCount;
    private ProfileManagement Profile;
    private FriendsManagement friendsManagement;
    private ContentManagement contentManagement;
    private ArrayList<Group> Groups;

    public Account(String Email, String Username, String Password, LocalDate DOB) {
        accountsCount++;
        this.userId = String.format("%04d", accountsCount);
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
        this.DOB = DOB;
        this.status = status.OFFLINE;
        this.Profile = new ProfileManagement(this, null, null, null);
        this.friendsManagement = new FriendsManagement(this);
        this.contentManagement = new ContentManagement(this);
        this.Groups = new ArrayList<>();
    }

    public static void resetAccountsCount() {
        accountsCount = 0;
    }

    public ArrayList<Group> getGroups() {
        return Groups;
    }
    
    public Group getGroup(String groupName){
        for (Group group : Group.getGroups()){
            if(group.getName().equalsIgnoreCase(groupName)){
                return group;
            }
        }
        return null;
    }

    public void addGroup(Group Group) {
        this.Groups.add(Group);
    }
    
    public void removeGroup(Group Group) {
        this.Groups.remove(Group);
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
        return status;
    }

    public void setStatus(Status Status) {
        this.status = Status;
    }

    public ProfileManagement getProfile() {
        return Profile;
    }

    public void setProfile(ProfileManagement Profile) {
        this.Profile = Profile;
    }

    public void logout() {
        this.status = Activity.Status.OFFLINE;
        Databases Database = Databases.getInstance();
        Database.save();
    }
}
