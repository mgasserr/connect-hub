package Backend.Feed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import Backend.Account.ProfileManagement;

public abstract class Content {

    private String contentId;
    private static int contentCount = 0;
    private String authorId;
    private Map<String, String> ContentMap; //<"Text", "Caption"> and <"Path", "Image path">
    private LocalDateTime Time;

    public Content(String authorId, Map<String, String> ContentMap, LocalDateTime timestamp) {
        contentCount++;
        this.contentId = String.format("%04d", contentCount) + "-" + authorId;
        this.authorId = authorId;
        if (timestamp == null) {
            this.Time = LocalDateTime.now();
        } else {
            this.Time = timestamp;
        }
        setContentMap(ContentMap);
        Path Src = Path.of((String) this.ContentMap.get("Path"));
        Path dest = Path.of("ImagesDatabase//Content//" + this.contentId + ".png");
        this.ContentMap.put("Path", dest.toString());
        try {
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ProfileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void resetContentCount() {
        contentCount = 0;
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

    public Map<String, String> getContentMap() {
        return ContentMap;
    }

    public void setContentMap(Map<String, String> ContentMap) {
        this.ContentMap = ContentMap;
        Path Src = Path.of(this.ContentMap.get("Path"));
        Path dest = Path.of("ImagesDatabase/Content//" + contentId + ".png");
        try {
            Files.copy(Src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(Content.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LocalDateTime getTime() {
        return Time;
    }

}
