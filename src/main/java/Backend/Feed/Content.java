package Backend.Feed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public abstract class Content {

    private String contentId;
    private static int contentCount = 0;
    private String authorId;
    private Map<String, String> Content; //<"Text", "Caption"> and <"Path", "Image path">
    private LocalDate Time;

    public Content(String authorId, Map Content) {
        contentCount++;
        this.contentId = String.format("%04d", contentCount) + "-" + authorId;
        this.authorId = authorId;
        this.Content = Content;
        this.Time = LocalDate.now();
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Map getContent() {
        return Content;
    }

    public void setContent(Map Content) {
        this.Content = Content;
        Path Src = Path.of(this.Content.get("Path"));
        Path dest = Path.of("ImagesDatabase/Content//" + contentId + ".png");
        try {
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(Content.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LocalDate getTime() {
        return Time;
    }

}
