package Backend.Feed;

import Backend.Account.Account;
import Backend.Databases.Databases;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Group {

    private Account Creator;
    private String Name;
    private String Description;
    private ImageIcon Picture;
    private static int count = 0;
    private ArrayList<Account> admins = new ArrayList<>();
    private ArrayList<Account> members = new ArrayList<>();
    private ArrayList<Content> content = new ArrayList<>();
    private ArrayList<Account> requests = new ArrayList<>();
    private static ArrayList<Group> groups = new ArrayList<>();
    private Databases D = Databases.getInstance();

    public Group(Account Creator, String Name, String Description, ImageIcon Picture) {
        this.Creator = Creator;
        this.Name = Name;
        if (Picture == null) {
            this.Picture = new ImageIcon("ImagesDatabase//Default//group.png");
        } else {
            this.Picture = Picture;
        }
        if (Description == null) {
            this.Description = "";
        } else {
            this.Description = Description;
        }
        count++;
    
    }

    //MAIN METHODS FOR GROUPS DETAILS
    public Account getCreator() {
        return Creator;
    }

    public void setCreator(Account Creator) {
        this.Creator = Creator;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public ImageIcon getPicture() {
        return Picture;
    }

    public void setPicture(String Imgpath) {
        Path Src = Path.of(Imgpath);
        Path dest = Path.of("ImagesDatabase//Group//" + count + ".png");
        try {
            // Ensure the destination directory exists
            Files.createDirectories(dest.getParent());
            // Copy the file
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        //D.getAccount(acc.getUsername()).getProfile().ProfileImg = new ImageIcon(dest.toString());
    }

    public static void resetGroupCount() {
        count = 0;
    }

    //SECONDARY METHODS FOR GROUPS
    public ArrayList<Content> getContent() {
        return content;
    }

    public void setContent(ArrayList<Content> content) {
        this.content = content;
    }

    public ArrayList<Account> getMembers() {
        return members;
    }
    
    public ArrayList<String> getMembersUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (Account account : members) {
            usernames.add(account.getUsername());  // Get username of each account
        }
        return usernames;
    }

    public void addMember(String Name) {
        this.requests.remove(D.getAccount(Name));
        this.members.add(D.getAccount(Name));
    }

    public void removeMember(String Name) {
        this.members.remove(D.getAccount(Name));
    }

    public boolean isMember(String Name) {
        return this.members.contains(D.getAccount(Name));
    }

    public ArrayList<Account> getAdmins() {
        return admins;
    }
    
    public ArrayList<String> getAdminsUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (Account account : admins) {
            usernames.add(account.getUsername());  // Get username of each account
        }
        return usernames;
    }
    
    public void addAdmin(String Name) {
        this.admins.add(D.getAccount(Name));
    }

    public void removeAdmin(String Name) {
        this.admins.remove(D.getAccount(Name));
    }

    public boolean isAdmin(String Name) {
        return this.admins.contains(D.getAccount(Name));
    }

    public void addRequest(String Name) {
        D.getGroup(this.Name).getRequests().add(D.getAccount(Name));
    }

    public void removeRequest(String Name) {
        this.requests.remove(D.getAccount(Name));
    }

    public ArrayList<Account> getRequests() {
        return requests;
    }
    public boolean isRequest(String Name) {
        return this.requests.contains(D.getAccount(Name));
    }
    
    public ArrayList<String> getRequestUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (Account account : requests) {
            usernames.add(account.getUsername());  // Get username of each account
        }
        return usernames;
    }

    public static ArrayList<Group> getGroups() {
        return groups;
    }

    public static void removeGroup(Group group) {
        groups.remove(group);
    }

    //ADD GROUPS TO USER ACCOUNT
    public void addGroup(Group Group, String Name) {
        D.getAccount(Name).getGroups().add(Group);
        groups.add(Group);
    }

    //MAYBE WRONG!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void addContent(Content content) {
        this.content.add(content);
    }

    //MAYBE WRONG!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void removeContent(Content content) {
        this.content.remove(content);
    }

    
    
    
    
}
