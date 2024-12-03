package Backend.Feed;

import java.time.LocalDate;

/**
 *
 * @author LEGION
 */
public abstract class Content {

    private String contentId;
    private static int contentCount = 0;
    private String authorId;
    private String Content;
    private LocalDate Time;

    public Content(String authorId, String Content) {
        contentCount++;
        this.contentId = String.format("%04d", contentId + authorId);
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

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public LocalDate getTime() {
        return Time;
    }

}
