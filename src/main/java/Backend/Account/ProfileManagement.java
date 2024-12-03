package Backend.Account;

import Backend.Feed.Content;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author LEGION
 */
public class ProfileManagement {

    private ImageIcon ProfileImg;
    private ImageIcon CoverImg;
    private Content Content;
    private String Bio;
    private ArrayList<Account> Friends;

    public ImageIcon getProfileImg() {
        return ProfileImg;
    }

    public void setProfileImg(String Imgpath) {

        this.ProfileImg = new ImageIcon(Imgpath);

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

    public void addFriends(Account friend) {
        this.Friends.add(friend);
    }

    public void removeFriends(Account friend) {
        this.Friends.remove(friend);
    }
}
