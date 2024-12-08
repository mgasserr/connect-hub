package Backend.Account;

import Backend.Databases.Databases;
import java.util.ArrayList;

public class FriendsManagement {

    private Account acc;
    private ArrayList<Account> Friends = new ArrayList<>();
    private ArrayList<Account> ReceivedFriendRequests = new ArrayList<>();
    private ArrayList<Account> SentFriendRequests = new ArrayList<>();
    private ArrayList<Account> BlockedUsers = new ArrayList<>();
    private ArrayList<Account> BlockedBy = new ArrayList<>();
    Databases Database = Databases.getInstance();

    public FriendsManagement(Account acc) {
        this.acc = acc;
    }

    public ArrayList<Account> getBlockedBy() {
        return BlockedBy;
    }

    public ArrayList<Account> getSentFriendRequests() {
        return SentFriendRequests;
    }

    public ArrayList<Account> getBlockedUsers() {
        return BlockedUsers;
    }

    public ArrayList<Account> getFriends() {
        return Friends;
    }

    public ArrayList<Account> getReceivedFriendRequests() {
        return ReceivedFriendRequests;
    }

    public void addFriend(Account friend) {
        this.Friends.add(friend);
    }

    private void removeFriend(Account friend) {
        this.Friends.remove(friend);
    }

    public void addReceivedFriendRequest(Account sender) {
        this.ReceivedFriendRequests.add(sender);
    }

    private void removeReceivedFriendRequest(Account sender) {
        this.ReceivedFriendRequests.remove(sender);
    }

    public void addSentFriendRequest(Account receiver) {
        this.SentFriendRequests.add(receiver);
    }

    private void removeSentFriendRequest(Account receiver) {
        this.SentFriendRequests.remove(receiver);
    }

    public void addBlockedUser(Account user) {
        this.BlockedUsers.add(user);
    }

    public void addBlockedBy(Account friend, Account myacc) {
        this.BlockedBy.add(friend);
        if (myacc.getFriendsManagement().getFriends().contains(friend)) {
            myacc.getFriendsManagement().removeFriend(friend);
        }
    }

    public void removeBlockedBy(Account friend) {
        this.BlockedBy.remove(friend);
    }

    private void removeBlockedUser(Account user) {
        this.BlockedUsers.remove(user);
    }

    public void deleteFriend(String friend, String myacc) {
        Database.getAccount(myacc).getFriendsManagement().removeFriend(Database.getAccount(friend));
        Database.getAccount(friend).getFriendsManagement().removeFriend(Database.getAccount(myacc));
    }

    public void sendFriendRequest(String receiver, String sender) {
        Account receiveracc = Database.getAccount(receiver);
        Account senderacc = Database.getAccount(sender);
        receiveracc.getFriendsManagement().addReceivedFriendRequest(senderacc);
        senderacc.getFriendsManagement().addSentFriendRequest(receiveracc);
    }

    public void unsendFriendRequest(String username) {
        removeSentFriendRequest(Database.getAccount(username));
        Database.getAccount(username).getFriendsManagement().removeReceivedFriendRequest(acc);
    }

    public void acceptFriendRequest(String sender, String receiver) {
        Account senderacc = Database.getAccount(sender);
        Account receiveracc = Database.getAccount(receiver);
        receiveracc.getFriendsManagement().removeReceivedFriendRequest(senderacc);
        receiveracc.getFriendsManagement().addFriend(senderacc);
        senderacc.getFriendsManagement().addFriend(receiveracc);
        senderacc.getFriendsManagement().removeSentFriendRequest(receiveracc);
    }

    public void declineFriendRequest(String sender, String receiver) {
        Account senderacc = Database.getAccount(sender);
        Account receiveracc = Database.getAccount(receiver);
        receiveracc.getFriendsManagement().removeReceivedFriendRequest(senderacc);
        senderacc.getFriendsManagement().removeSentFriendRequest(receiveracc);
    }

    public void Block(String username, String blocker) {
        Account usertobeblocked = Database.getAccount(username);
        Account blockeracc = Database.getAccount(blocker);
        blockeracc.getFriendsManagement().addBlockedUser(usertobeblocked);
        usertobeblocked.getFriendsManagement().addBlockedBy(blockeracc, usertobeblocked);
    }

    public void Unblock(String usertobeunblocked, String unblocker) {
        Account usertobeunblockedacc = Database.getAccount(usertobeunblocked);
        Account unblockeracc = Database.getAccount(unblocker);
        unblockeracc.getFriendsManagement().removeBlockedUser(usertobeunblockedacc);
        usertobeunblockedacc.getFriendsManagement().removeBlockedBy(unblockeracc);
    }
}
