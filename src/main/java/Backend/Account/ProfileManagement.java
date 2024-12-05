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
    private Account acc;
    private ImageIcon ProfileImg;
    private ImageIcon CoverImg;
    private ArrayList<Content> content = new ArrayList<>();
    private String Bio;
    private FriendsManagement friendsManagement;

    public ProfileManagement(Account acc) {
        this.acc = acc;
        friendsManagement = new FriendsManagement(acc);
    }

    public FriendsManagement getFriendsManagement() {
        return friendsManagement;
    }

        
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

}
