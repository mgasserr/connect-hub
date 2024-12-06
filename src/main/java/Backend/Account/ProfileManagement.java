package Backend.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class ProfileManagement {

    private Account acc;
    private ImageIcon ProfileImg;
    private ImageIcon CoverImg;
    private String Bio;

    public ProfileManagement(Account acc, ImageIcon ProfileImg, ImageIcon CoverImg, String Bio) {
        this.acc = acc;
        this.ProfileImg = new ImageIcon("ImagesDatabase/Default/profile.png");
        this.CoverImg = new ImageIcon("ImagesDatabase/Default/cover.jpg");
        this.Bio = Bio;
    }

    public ImageIcon getProfileImg() {
        return ProfileImg;
    }

    public void setProfileImg(String Imgpath) {
        this.ProfileImg = new ImageIcon(Imgpath);
        Path Src = Path.of(Imgpath);
        Path dest = Path.of("ImagesDatabase/ProfilePicture/" + acc.getUserId() + ".png");
        try {
            // Ensure the destination directory exists
            Files.createDirectories(dest.getParent());
            // Copy the file
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ProfileManagement.class.getName()).log(Level.SEVERE, "Error copying profile image", ex);
        }

    }

    public ImageIcon getCoverImg() {
        return CoverImg;
    }

    public void setCoverImg(String Imgpath) {
        this.CoverImg = new ImageIcon(Imgpath);
        Path Src = Path.of(Imgpath);
        Path dest = Path.of("ImagesDatabase/CoverPicture/" + acc.getUserId() + ".png");
        try {
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ProfileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

}
