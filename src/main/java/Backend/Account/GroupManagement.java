package Backend.Account;

import Backend.Databases.Databases;
import Backend.Feed.Content;
import Backend.Feed.Group;
import java.util.ArrayList;

public class GroupManagement {

    private Account primaryAdmin;
    private ArrayList<Account> admins;
    private ArrayList<Account> members;
    private ArrayList<Content> content;
    private ArrayList<Group> groups;
    Databases D = Databases.getInstance();

    public GroupManagement(Account primaryadmin) {
        this.primaryAdmin = primaryadmin;
        this.admins = new ArrayList<>();
        this.members = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    public Account getPrimaryAdmin() {
        return primaryAdmin;
    }

    public void setPrimaryAdmin(Account primaryAdmin) {
        this.primaryAdmin = primaryAdmin;
    }

    public ArrayList<Account> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Account> admins) {
        this.admins = admins;
    }

    public ArrayList<Account> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Account> members) {
        this.members = members;
    }

    public ArrayList<Content> getContent() {
        return content;
    }

    public void setContent(ArrayList<Content> content) {
        this.content = content;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void addMember(String Name) {
        this.members.add(D.getAccount(Name));
    }

    public void removeMember(String Name) {
        this.members.remove(D.getAccount(Name));
    }

    public boolean isMember(String Name) {
        return this.members.contains(D.getAccount(Name));
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

    //CREATES NEW GROUP
    public void addGroup(Group Group, String Name) {
        D.getAccount(Name).getGroupsManagement().getGroups().add(Group);
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
