package Backend.Account;

import Backend.Databases.Database;
import java.util.ArrayList;

public class FriendsManagement {

    private Account acc;
    private ArrayList<Account> Friends = new ArrayList<>();
    private ArrayList<Account> ReceivedFriendRequests = new ArrayList<>();
    private ArrayList<Account> SentFriendRequests = new ArrayList<>();
    private ArrayList<Account> BlockedUsers = new ArrayList<>();

    public FriendsManagement(Account acc) {
        this.acc = acc;
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

    public Account getSentFriendRequestByIndex(int i) {
        return SentFriendRequests.get(i);
    }

    public Account getBlockedUsersByIndex(int i) {
        return BlockedUsers.get(i);
    }

    public Account getFriendByIndex(int i) {
        return SentFriendRequests.get(i);
    }

    public Account getReceivedFriendRequestByIndex(int i) {
        return SentFriendRequests.get(i);
    }

    public void addFriend(Account friend) {
        this.Friends.add(friend);
    }

    private void removeFriend(Account friend) {
        Friends.remove(friend);
    }

    public void addReceivedFriendRequest(Account sender) {
        this.ReceivedFriendRequests.add(sender);
    }

    private void removeReceivedFriendRequest(Account sender) {
        this.ReceivedFriendRequests.remove(sender);
    }

    void addSentFriendRequests(Account receiver) {
        this.SentFriendRequests.add(receiver);
    }

    private void removeSentFriendRequests(Account receiver) {
        this.SentFriendRequests.remove(receiver);
    }

    void addBlockedUser(Account user) {
        this.BlockedUsers.add(user);
    }

    private void removeBlockedUser(Account user) {
        this.BlockedUsers.remove(user);
    }

    public void deleteFriend(Account friend) {
        removeFriend(friend);
        friend.getFriendsManagement().removeFriend(acc);
    }

    public void sendFriendRequest(String username) {
        Account receiver = Database.getAccount(username);
        receiver.getFriendsManagement().addReceivedFriendRequest(acc);
        addSentFriendRequests(receiver);
    }

    public void acceptFriendRequest(String username) {
        Account sender = Database.getAccount(username);
        removeReceivedFriendRequest(sender);
        addFriend(sender);
        sender.getFriendsManagement().addFriend(acc);
        sender.getFriendsManagement().removeSentFriendRequests(acc);
    }

    public void declineFriendRequest(String username) {
        Account sender = Database.getAccount(username);
        removeReceivedFriendRequest(sender);
        sender.getFriendsManagement().removeSentFriendRequests(acc);
    }

    public void Block(String username) {
        Account usertobeblocked = Database.getAccount(username);
        addBlockedUser(usertobeblocked);
        if (Friends.contains(usertobeblocked)) {
            removeFriend(usertobeblocked);
        }
    }

    public void Unblock(String username) {
        Account usertobeunblocked = Database.getAccount(username);
        removeBlockedUser(usertobeunblocked);
    }
}
