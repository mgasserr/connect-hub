package Backend.Feed;

import java.time.LocalDate;
import java.util.Map;

public abstract class Content {

    private String contentId;
    private static int contentCount = 0;
    private String authorId;
    private Map<String, String> Content;
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
    }

    public LocalDate getTime() {
        return Time;
    }

}
