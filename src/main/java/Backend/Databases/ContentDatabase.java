package Backend.Databases;

import Backend.Account.Account;
import static Backend.Databases.Database.accounts;
import Backend.Feed.Content;
import Backend.Feed.ContentFactory;
import Backend.Feed.Posts;
import Backend.Feed.Stories;
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
    protected void read() {
        try {
            // Read JSON content from file
            String jsonstring = new String(Files.readAllBytes(Paths.get("content.json")));
            JSONArray usersArray = new JSONArray(jsonstring);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            ContentFactory contentFactory = new ContentFactory();

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                String userid = userJson.getString("userid");
                int postscount = userJson.getInt("postscount");
                int storiescount = userJson.getInt("storiescount");

                // Process posts array
                JSONArray postsArray = userJson.getJSONArray("posts");
                for (int j = 0; j < postsArray.length(); j++) {
                    JSONObject postObj = postsArray.getJSONObject(j);

                    String imagepath = postObj.optString("path", "NULL");
                    String caption = postObj.optString("caption", "NULL");
                    LocalDateTime timestamp = LocalDateTime.parse(postObj.getString("timestamp"), formatter);

                    Map<String, Object> contentMap = new HashMap<>();
                    contentMap.put("Text", "NULL".equals(caption) ? null : caption);
                    contentMap.put("Path", "NULL".equals(imagepath) ? null : imagepath);

                    Database.getAccountbyID(userid)
                            .getContentManagement()
                            .addContent(contentFactory.Feed("Post", userid, contentMap, timestamp));
                }

                // Process stories array
                JSONArray storiesArray = userJson.getJSONArray("stories");
                for (int j = 0; j < storiesArray.length(); j++) {
                    JSONObject storyObj = storiesArray.getJSONObject(j);

                    String imagepath = storyObj.optString("path", "NULL");
                    String caption = storyObj.optString("caption", "NULL");
                    LocalDateTime timestamp = LocalDateTime.parse(storyObj.getString("timestamp"), formatter);

                    Map<String, Object> contentMap = new HashMap<>();
                    contentMap.put("Text", "NULL".equals(caption) ? null : caption);
                    contentMap.put("Path", "NULL".equals(imagepath) ? null : imagepath);

                    Database.getAccountbyID(userid)
                            .getContentManagement()
                            .addContent(contentFactory.Feed("Story", userid, contentMap, timestamp));
                }
            }
        } catch (IOException e) {
            // Handle file not found or read error
            System.out.println("Error reading content.json, initializing with empty data.");
            try (FileWriter file = new FileWriter("content.json")) {
                file.write((new JSONArray()).toString(3));
            } catch (IOException ex) {
                System.out.println("Error initializing content.json: " + ex.getMessage());
            }
        } catch (Exception e) {
            // Handle unexpected exceptions
            System.out.println("An error occurred while reading content: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void save() {
        JSONArray usersArray = new JSONArray();

        for (Account acc : accounts) {
            JSONArray postsArray = new JSONArray(); // Separate array for posts
            JSONArray storiesArray = new JSONArray(); // Separate array for stories

            for (Content content : acc.getContentManagement().getContent()) {
                JSONObject contentObj = new JSONObject(); // Single object to hold content details
                String path;

                // Get the path
                if (content.getContentMap().get("Path") != null) {
                    path = content.getContentMap().get("Path").toString();
                } else {
                    path = "NULL";
                }

                // Add common properties
                contentObj.put("caption", content.getContentMap().get("Text") != null
                        ? content.getContentMap().get("Text").toString()
                        : "NULL");
                contentObj.put("timestamp", content.getTime());
                contentObj.put("path", path);

                // Distinguish between posts and stories
                if (content instanceof Posts) {
                    postsArray.put(contentObj); // Add to posts array
                } else if (content instanceof Stories) {
                    storiesArray.put(contentObj); // Add to stories array
                }
            }

            // Create user object with separate arrays for posts and stories
            JSONObject userObj = new JSONObject();
            userObj.put("userid", acc.getUserId());
            userObj.put("postscount", postsArray.length());
            userObj.put("storiescount", storiesArray.length());
            userObj.put("posts", postsArray); // Add posts array
            userObj.put("stories", storiesArray); // Add stories array

            usersArray.put(userObj); // Add user object to users array
        }

        // Save to JSON file
        try (FileWriter file = new FileWriter("content.json")) {
            file.write(usersArray.toString(3)); // Indent with 3 spaces for readability
        } catch (IOException e) {
            System.out.println("Error in saving content to file: " + e.getMessage());
        }
    }

}
