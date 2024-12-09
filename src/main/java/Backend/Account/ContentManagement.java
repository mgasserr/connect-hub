package Backend.Account;

import Backend.Databases.Databases;
import Backend.Feed.Content;
import Backend.Feed.Posts;
import Backend.Feed.Stories;
import java.util.ArrayList;

public class ContentManagement {

    private Account acc;
    private ArrayList<Content> content = new ArrayList<>();
    private Databases D = Databases.getInstance();

    public ContentManagement(Account acc) {
        this.acc = acc;
    }

    public ArrayList<Content> getContent() {
        return content;
    }

    public void setContent(ArrayList<Content> content) {
        this.content = content;
    }

    public void addContent(Content content) {
        if (content instanceof Stories) {
            if (!((Stories) content).isExpired()) {
                D.getAccount(acc.getUsername()).getContentManagement().getContent().add(content);
            }
        } else {
            D.getAccount(acc.getUsername()).getContentManagement().getContent().add(content);
        }
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
}
