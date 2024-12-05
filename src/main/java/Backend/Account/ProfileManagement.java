package Backend.Account;

import Backend.Feed.Content;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
    private String Bio;

    public ProfileManagement(Account acc, ImageIcon ProfileImg, ImageIcon CoverImg, String Bio) {
        this.acc = acc;
        this.ProfileImg = ProfileImg;
        this.CoverImg = CoverImg;
        this.Bio = Bio;
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
        Path dest = Path.of("ImagesDatabase/ProfilePicture//" + acc.getUserId() + ".png");
        Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

}
