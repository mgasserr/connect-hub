package Backend.Account;


import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author LEGION
 */
public class ProfileManagement {

    private ImageIcon ProfileImg;
    private ImageIcon CoverImg;
    //private Content Content;
    private String Bio;
    private ArrayList<Account> Friends= new ArrayList<>();
    private ArrayList<Account> FriendRequests= new ArrayList<>();

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
    public void friendrequests(String username){
        Account user=Database.getuser(username);
        FriendRequests.add(user);
        
    }
    public void acceptfriendrequests(String username){
        Account user=Database.getuser(username);
        FriendRequests.remove(user);
        addFriends(user);
        
    }
     public void declinefriendrequests(String username){
        Account user=Database.getuser(username);
        FriendRequests.remove(user);
        
        
        
    }

    public ArrayList<Account> getFriendRequests() {
        return FriendRequests;
    }
    
}
