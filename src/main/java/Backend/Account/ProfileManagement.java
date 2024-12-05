package Backend.Account;

import Backend.Feed.Content;
import Backend.Feed.Posts;
import Backend.Feed.Stories;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ProfileManagement {

    private Account acc;
    private ImageIcon ProfileImg;
    private ImageIcon CoverImg;
    private String Bio;
    private ArrayList<Content> content = new ArrayList<>();

    public ProfileManagement(Account acc, ImageIcon ProfileImg, ImageIcon CoverImg, String Bio) {
        this.acc = acc;
        this.ProfileImg = ProfileImg;
        this.CoverImg = CoverImg;
        this.Bio = Bio;
    }

<<<<<<< HEAD
    public ProfileManagement(Account acc) {
        this.acc = acc;
        friendsManagement = new FriendsManagement(acc);
=======
    public ArrayList<Content> getContent() {
        return content;
>>>>>>> b583627d3e231ca630fa809f5ae5a96e89770c5f
    }

    public void setContent(ArrayList<Content> content) {
        this.content = content;
    }

    public void addContent(Content content) {
        this.content.add(content);
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

    public ImageIcon getProfileImg() {
        return ProfileImg;
    }

    public void setProfileImg(String Imgpath) throws IOException {
        this.ProfileImg = new ImageIcon(Imgpath);
        Path Src = Path.of(Imgpath);
        Path dest = Path.of("ImagesDatabase/ProfilePicture//" + acc.getUserId() + ".png");
        Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    public ImageIcon getCoverImg() {
        return CoverImg;
    }

    public void setCoverImg(String Imgpath) throws IOException {
        this.CoverImg = new ImageIcon(Imgpath);
        Path Src = Path.of(Imgpath);
        Path dest = Path.of("ImagesDatabase/CoverPicture//" + acc.getUserId() + ".png");
        Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

}
