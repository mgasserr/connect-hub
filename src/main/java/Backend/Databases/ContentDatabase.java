package Backend.Databases;

import Backend.Account.Account;
import static Backend.Databases.Database.accounts;
import Backend.Feed.Content;
import Backend.Feed.ContentFactory;
import Backend.Feed.Posts;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ContentDatabase extends Database {

    private static ContentDatabase contentDatabase;

    private ContentDatabase() {
        super();
    }

    public static ContentDatabase getInstance() {
        if (contentDatabase == null) {
            contentDatabase = new ContentDatabase();
        }
        return contentDatabase;
    }

    @Override
    protected void read() {         //NOT IMPLEMENTED
        try {
            String jsonstring = new String(Files.readAllBytes(Paths.get("content.json")));
            JSONArray usersArray = new JSONArray(jsonstring);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            ContentFactory ContentFactory = new ContentFactory();
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                String userid = userJson.getString("userid");
                int postscount = Integer.parseInt(userJson.getString("postscount"));
                int storiescount = Integer.parseInt(userJson.getString("storiescount"));
                JSONArray contentArray = userJson.getJSONArray("content");
                for (int j = 0; j < contentArray.length(); j++) {
                    for (int k = 1; k <= postscount; k++) {
                        String imagepath = userJson.getString("postnum" + k);
                        String caption = userJson.getString("caption");
                        LocalDateTime timestamp = LocalDateTime.parse(userJson.getString("timestamp"), formatter);
                        Map contentmap = new HashMap();
                        if (caption.equals("NULL")) {
                            contentmap.put("Text", null);
                        } else {
                            contentmap.put("Text", caption);
                        }
                        if (imagepath.equals("NULL")) {
                            contentmap.put("Path", null);
                        } else {
                            contentmap.put("Path", imagepath);
                        }
                        Database.getAccountbyID(userid).getContentManagement().addContent(ContentFactory.Feed("Post", userid, contentmap, timestamp));
                    }
                    for (int k = 1; k <= storiescount; k++) {
                        String imagepath = userJson.getString("storynum" + k);
                        String caption = userJson.getString("caption");
                        LocalDateTime timestamp = LocalDateTime.parse(userJson.getString("timestamp"), formatter);
                        Map contentmap = new HashMap();
                        if (caption.equals("NULL")) {
                            contentmap.put("Text", null);
                        } else {
                            contentmap.put("Text", caption);
                        }
                        if (imagepath.equals("NULL")) {
                            contentmap.put("Path", null);
                        } else {
                            contentmap.put("Path", imagepath);
                        }
                        Database.getAccountbyID(userid).getContentManagement().addContent(ContentFactory.Feed("Story", userid, contentmap, timestamp));
                    }
                }
            }
        } catch (IOException ex) {
            try {
                FileWriter file = new FileWriter("content.json");
                file.write((new JSONArray()).toString(3));
                file.close();
            } catch (IOException ex1) {
                System.out.println("Error in reading content from file");
            }
        }
    }

    @Override
    protected void save() {                                //REQUIRED MAJOR MODIFICATIONS //NOT FINISHED YET
        JSONArray usersArray = new JSONArray();
        for (Account acc : accounts) {
            JSONArray contentArray = new JSONArray();
            int postnum = 0, storynum = 0;
            for (Content content : acc.getContentManagement().getContent()) {
                JSONObject contentobj = new JSONObject();
                String path;
                if (content.getContentMap().get("Path") != null) {
                    path = content.getContentMap().get("Path").toString();
                } else {
                    path = "NULL";
                }
                if (content instanceof Posts) {
                    postnum++;
                    contentobj.put("postnum" + postnum, path);
                } else {
                    storynum++;
                    contentobj.put("storynum" + storynum, path);
                }
                if (content.getContentMap().get("Text") != null) {
                    contentobj.put("caption", content.getContentMap().get("Text"));
                } else {
                    contentobj.put("caption", "NULL");
                }
                contentobj.put("timestamp", content.getTime());
                contentArray.put(contentobj);
            }
            JSONObject userobj = new JSONObject();
            userobj.put("userid", acc.getUserId());
            userobj.put("postscount", acc.getContentManagement().getPostsCount());
            userobj.put("storiescount", acc.getContentManagement().getStoriesCount());
            userobj.put("content", contentArray);
            usersArray.put(userobj);
        }
        try {
            FileWriter file = new FileWriter("content.json");
            file.write("");
            file.write(usersArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error in saving content in file");
        }
    }
}
