package Backend.Account;

import Backend.Databases.Database;
import java.util.ArrayList;

public class FriendsManagement {

    private Account acc;
    private ArrayList<Account> Friends = new ArrayList<>();
    private ArrayList<Account> ReceivedFriendRequests = new ArrayList<>();
    private ArrayList<Account> SentFriendRequests = new ArrayList<>();
    private ArrayList<Account> BlockedUsers = new ArrayList<>();
    private ArrayList<Account> BlockedBy = new ArrayList<>();

    public FriendsManagement(Account acc) {
        this.acc = acc;
    }

    public ArrayList<Account> getSuggestedFriendsCLONED() {
        ArrayList<Account> clone = new ArrayList<>(Database.getAllAccounts());
        clone.remove(acc);
        clone.removeAll(BlockedUsers);
        clone.removeAll(Friends);
        clone.removeAll(SentFriendRequests);
        clone.removeAll(ReceivedFriendRequests);
        clone.removeAll(BlockedBy);
        return clone;
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
        Friends.remove(friend);
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

    public void addBlockedBy(Account friend) {
        this.BlockedBy.add(friend);
        if (Friends.contains(friend)) {
            removeFriend(friend);
        }
    }

    public void removeBlockedBy(Account friend) {
        this.BlockedBy.remove(friend);
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
        addSentFriendRequest(receiver);
    }

    public void acceptFriendRequest(String username) {
        Account sender = Database.getAccount(username);
        removeReceivedFriendRequest(sender);
        addFriend(sender);
        sender.getFriendsManagement().addFriend(acc);
        sender.getFriendsManagement().removeSentFriendRequest(acc);
    }

    public void declineFriendRequest(String username) {
        Account sender = Database.getAccount(username);
        removeReceivedFriendRequest(sender);
        sender.getFriendsManagement().removeSentFriendRequest(acc);
    }

    public void Block(String username) {
        if (!Database.getAccount(username).getFriendsManagement().getBlockedBy().contains(acc)) {
            Account usertobeblocked = Database.getAccount(username);
            addBlockedUser(usertobeblocked);
            usertobeblocked.getFriendsManagement().addBlockedBy(acc);
            if (Friends.contains(usertobeblocked)) {
                removeFriend(usertobeblocked);
            }
        }
    }

    public void Unblock(String username) {
        Account usertobeunblocked = Database.getAccount(username);
        removeBlockedUser(usertobeunblocked);
        usertobeunblocked.getFriendsManagement().removeBlockedBy(acc);
    }
}
