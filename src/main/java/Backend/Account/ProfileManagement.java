package Backend.Account;

import Backend.Databases.Databases;
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
    private Databases D = Databases.getInstance();

    public ProfileManagement(Account acc, ImageIcon ProfileImg, ImageIcon CoverImg, String Bio) {
        this.acc = acc;
        if (ProfileImg == null) {
            this.ProfileImg = new ImageIcon("ImagesDatabase//Default//profile.png");
        } else {
            this.ProfileImg = ProfileImg;
        }
        if (CoverImg == null) {
            this.CoverImg = new ImageIcon("ImagesDatabase//Default//cover.jpg");
        } else {
            this.CoverImg = CoverImg;
        }
        if (Bio == null) {
            this.Bio = "";
        } else {
            this.Bio = Bio;
        }
    }

    public ImageIcon getProfileImg() {
        return D.getAccount(acc.getUsername()).getProfile().ProfileImg;
    }

    public void setProfileImg(String Imgpath) {
        Path Src = Path.of(Imgpath);
        Path dest = Path.of("ImagesDatabase//ProfilePicture//" + acc.getUserId() + ".png");
        try {
            // Ensure the destination directory exists
            Files.createDirectories(dest.getParent());
            // Copy the file
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ProfileManagement.class.getName()).log(Level.SEVERE, "Error copying profile image", ex);
        }
        D.getAccount(acc.getUsername()).getProfile().ProfileImg = new ImageIcon(dest.toString());
    }

    public ImageIcon getCoverImg() {
        return D.getAccount(acc.getUsername()).getProfile().CoverImg;
    }

    public void setCoverImg(String Imgpath) {
        Path Src = Path.of(Imgpath);
        Path dest = Path.of("ImagesDatabase//CoverPicture//" + acc.getUserId() + ".png");
        try {
            Files.createDirectories(dest.getParent());
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ProfileManagement.class.getName()).log(Level.SEVERE, "Error copying profile image", ex);
        }
        D.getAccount(acc.getUsername()).getProfile().CoverImg = new ImageIcon(dest.toString());
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

}
