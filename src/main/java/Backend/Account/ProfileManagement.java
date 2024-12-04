package Backend.Account;

import Backend.Feed.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author LEGION
 */
public class ProfileManagement {

    private Database database = Database.getInstance();
    private ImageIcon ProfileImg;
    private ImageIcon CoverImg;
    private ArrayList<Content> content = new ArrayList<>();
    private String Bio;
    private ArrayList<Account> Friends = new ArrayList<>();
    private ArrayList<Account> FriendRequests = new ArrayList<>();

    public ImageIcon getProfileImg() {
        return ProfileImg;
    }

    public void setProfileImg(String Imgpath) {

        this.ProfileImg = new ImageIcon(Imgpath);

    }

    public void addContent(Content content) {
        this.content.add(content);
    }

    public ArrayList<Content> getContent() {
        return content;
    }

    public int getPostsCount() {
        int count = 0;
        for (Content c : content) {
            if (c instanceof Posts) {
                count++;
            }
        }
        return count;
    }

    public int getStoriesCount() {
        int count = 0;
        for (Content c : content) {
            if (c instanceof Stories) {
                count++;
            }
        }
        return count;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

    public ImageIcon getCoverImg() {

        return CoverImg;
    }

    public void setCoverImg(String Imgpath) {
        this.CoverImg = new ImageIcon(Imgpath);
    }

    public ArrayList<Account> getFriends() {
        return Friends;
    }

    public void addFriend(Account friend) {
        Friends.add(friend);
    }

    public void removeFriend(Account friend) {
        Friends.remove(friend);
    }

    public void addFriendRequest(String username) { //which means that "username" has sent a friend request to this instance of the user
        Account user = database.getAccount(username);
        FriendRequests.add(user);
    }

    public void acceptFriendRequest(String username) {
        Account user = database.getAccount(username);
        FriendRequests.remove(user);
        Friends.add(user);
        addFriend(user);
    }

    public void declineFriendRequest(String username) {
        Account user = database.getAccount(username);
        removeFriend(user);
    }

    public ArrayList<Account> getFriendRequests() {
        return FriendRequests;
    }

}
