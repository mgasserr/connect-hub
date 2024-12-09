/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Account;

import Backend.Databases.Databases;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class GroupManagement {
    private ArrayList<Account> members;
    private Account primaryadmin;
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

    public Databases getDatabase() {
        return Database;
    }

    public void setDatabase(Databases Database) {
        this.Database = Database;
    }
    
    
    
    
    public void addmember(Account acc){
        this.members.add(acc);
    }
    
    public void removemember(Account acc){
        this.members.remove(acc);
    }
    
    public boolean ismember(Account acc){
        return this.members.contains(acc);
    }
    
    
    public void addadmin(Account acc){
        this.admins.add(acc);
    }
    
    public void removeadmin(Account acc){
        this.admins.remove(acc);
    }
    
    public boolean isadmin(Account acc){
        return this.admins.contains(acc);
    }
    
}
