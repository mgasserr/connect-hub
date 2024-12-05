package Backend.Databases;

import Backend.Account.Account;
import Backend.Feed.Content;
import Backend.Feed.Posts;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ContentDatabse extends Database {

    private static ContentDatabse contentDatabase;

    private ContentDatabse() {
        super();
    }

    public static ContentDatabse getInstance() {
        if (contentDatabase == null) {
            contentDatabase = new ContentDatabse();
        }
        return contentDatabase;
    }

    @Override
    protected void save() {
        JSONArray usersArray = new JSONArray();
        for (Account acc : accounts) {
            JSONObject obj = new JSONObject();
            obj.put("userid", acc.getUserId());
            obj.put("postscount", acc.getProfile().getPostsCount());
            obj.put("storiescount", acc.getProfile().getStoriesCount());
            for (Content content : acc.getProfile().getContent()) {
                if (content instanceof Posts) {
                    obj.put("type", "post");
                } else {
                    obj.put("type", "story");
                }
                obj.put("contentid", content.getContentId());
                obj.put("imagepath", content.getContent());
                obj.put("timestamp", content.getTime());

            }
            usersArray.put(obj);
        }
        try {
            FileWriter file = new FileWriter("content.json");
            file.write("");
            file.write(usersArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error in saving content.json");
        }
    }

    @Override
    protected void read() {
    }
}
