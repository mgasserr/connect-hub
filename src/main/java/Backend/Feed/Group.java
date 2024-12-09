package Backend.Feed;

import Backend.Account.GroupManagement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Group {

    private String Name;
    private String Description;
    private ImageIcon Picture;
    private static int count = 0;

    public Group(String Name, String Description, ImageIcon Picture) {
        this.Name = Name;
        if (Picture == null) {
            this.Picture = new ImageIcon("ImagesDatabase//Default//group.png");
        } else {
            this.Picture = Picture;
        }
        if (Description == null) {
            this.Description = "";
        } else {
            this.Description = Description;
        }
        count++;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public ImageIcon getPicture() {
        return Picture;
    }

    public void setPicture(String Imgpath) {
        Path Src = Path.of(Imgpath);
        Path dest = Path.of("ImagesDatabase//ProfilePicture//" + count + ".png");
        try {
            // Ensure the destination directory exists
            Files.createDirectories(dest.getParent());
            // Copy the file
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(GroupManagement.class.getName()).log(Level.SEVERE, "Error copying profile image", ex);
        }
        //D.getAccount(acc.getUsername()).getProfile().ProfileImg = new ImageIcon(dest.toString());
    }

    public static void resetCount() {
        count = 0;
    }
}
