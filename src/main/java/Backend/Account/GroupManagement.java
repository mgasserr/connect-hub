package Backend.Account;

import Backend.Databases.Databases;
import java.util.ArrayList;

public class GroupManagement {

    private Account primaryadmin;
    private ArrayList<Account> members;
    private ArrayList<Account> admins;
    Databases Database = Databases.getInstance();

    public GroupManagement(Account primaryadmin) {
        this.primaryadmin = primaryadmin;
    }

    public ArrayList<Account> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Account> members) {
        this.members = members;
    }

    public Account getPrimaryadmin() {
        return primaryadmin;
    }

    public void setPrimaryadmin(Account primaryadmin) {
        this.primaryadmin = primaryadmin;
    }

    public ArrayList<Account> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Account> admins) {
        this.admins = admins;
    }

    public void addMember(Account acc) {
        this.members.add(acc);
    }

    public void removeMember(Account acc) {
        this.members.remove(acc);
    }

    public boolean isMember(Account acc) {
        return this.members.contains(acc);
    }

    public void addAdmin(Account acc) {
        this.admins.add(acc);
    }

    public void removeAdmin(Account acc) {
        this.admins.remove(acc);
    }

    public boolean isAdmin(Account acc) {
        return this.admins.contains(acc);
    }

}
