package Backend.Databases;

import Backend.Account.*;
import Backend.Account.Activity.Status;
import Backend.Feed.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mohamed
 */
public class Databases {

    private static Databases database;
    private ArrayList<Account> accounts = new ArrayList<>();

    private Databases() {
        super();
    }

    public static Databases getInstance() {
        if (database == null) {
            database = new Databases();
        }
        return database;
    }

    public void refresh() {
        save();
        read();
    }

    public void save() {
        JSONArray Arrayusers = new JSONArray();
        for (Account acc : accounts) {
            JSONObject userobj = new JSONObject();

            JSONArray Arrayinfo = new JSONArray();
            JSONArray Arrayposts = new JSONArray();
            JSONArray Arraystories = new JSONArray();

            // INFO ARRAY
            JSONObject infoobj = new JSONObject();
            infoobj.put("username", acc.getUsername());
            infoobj.put("password", acc.getPassword());
            infoobj.put("email", acc.getEmail());
            infoobj.put("dob", acc.getDob());
            infoobj.put("status", acc.getStatus());
            infoobj.put("bio", acc.getProfile().getBio());
            infoobj.put("coverimg", acc.getProfile().getCoverImg());
            infoobj.put("profileimg", acc.getProfile().getProfileImg());
            Arrayinfo.put(infoobj);
            userobj.put("info", Arrayinfo);

            // FRIENDS ARRAY
            ArrayList<String> friendsusernames = new ArrayList<>();
            for (Account friend : acc.getFriendsManagement().getFriends()) {
                friendsusernames.add(friend.getUsername());
            }
            userobj.put("friends", friendsusernames);

            // RECEIVED REQ ARRAY
            ArrayList<String> receivedreqarray = new ArrayList<>();
            for (Account receivedfriend : acc.getFriendsManagement().getReceivedFriendRequests()) {
                receivedreqarray.add(receivedfriend.getUsername());
            }
            userobj.put("receivedfriends", receivedreqarray);

            // SENT REQ ARRAY
            ArrayList<String> sentreqarray = new ArrayList<>();
            for (Account sentfriend : acc.getFriendsManagement().getSentFriendRequests()) {
                sentreqarray.add(sentfriend.getUsername());
            }
            userobj.put("sentfriends", sentreqarray);

            // BLOCKED ARRAY
            ArrayList<String> blockedarray = new ArrayList<>();
            for (Account blocked : acc.getFriendsManagement().getBlockedUsers()) {
                blockedarray.add(blocked.getUsername());
            }
            userobj.put("blocked", blockedarray);

            // BLOCKED BY ARRAY
            ArrayList<String> blockedBYarray = new ArrayList<>();
            for (Account blockedBY : acc.getFriendsManagement().getBlockedBy()) {
                blockedBYarray.add(blockedBY.getUsername());
            }
            userobj.put("blockedBY", blockedBYarray);

            // CONTENT
            for (Content content : acc.getContentManagement().getContent()) {
                if (content instanceof Posts) {
                    JSONObject postsobj = new JSONObject();
                    postsobj.put("caption", content.getContentMap().get("Text"));
                    postsobj.put("path", content.getContentMap().get("Path"));
                    postsobj.put("timestamp", content.getTime().toString()); // Ensure timestamp is serialized as string
                    Arrayposts.put(postsobj);
                } else if (content instanceof Stories) {
                    JSONObject storyobj = new JSONObject();
                    storyobj.put("caption", content.getContentMap().get("Text"));
                    storyobj.put("path", content.getContentMap().get("Path"));
                    storyobj.put("timestamp", content.getTime().toString()); // Ensure timestamp is serialized as string
                    Arraystories.put(storyobj);
                }
            }

            userobj.put("posts", Arrayposts);
            userobj.put("userid", acc.getUserId());
            userobj.put("stories", Arraystories);
            Arrayusers.put(userobj);
        }
        try (FileWriter file = new FileWriter("database.json")) {
            file.write(Arrayusers.toString(3));
        } catch (IOException e) {
            System.out.println("Error in saving in database.json");
        }
    }

    public void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader("database.json"))) {
            accounts.clear();
            Account.resetAccountsCount();
            Content.resetContentCount();

            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            JSONArray Arrayusers = new JSONArray(jsonContent.toString());

            // Create all accounts without setting relationships
            for (int i = 0; i < Arrayusers.length(); i++) {
                JSONObject userobj = Arrayusers.getJSONObject(i);
                JSONArray Arrayinfo = userobj.getJSONArray("info");
                JSONObject infoobj = Arrayinfo.getJSONObject(0);

                String username = infoobj.getString("username");
                String password = infoobj.getString("password");
                String email = infoobj.getString("email");
                LocalDate dob = LocalDate.parse(infoobj.getString("dob"));
                String status = infoobj.getString("status");
                String bio = infoobj.getString("bio");
                ImageIcon coverImg = new ImageIcon(infoobj.getString("coverimg"));
                ImageIcon profileImg = new ImageIcon(infoobj.getString("profileimg"));

                Account account = new Account(email, username, password, dob);

                ProfileManagement userprofile = new ProfileManagement(account, profileImg, coverImg, bio);
                account.setProfile(userprofile);

                if (status.equals("OFFLINE")) {
                    account.setStatus(Status.OFFLINE);
                } else {
                    account.setStatus(Status.ONLINE);
                }

                accounts.add(account);
            }

            // Set relationships and content
            for (int i = 0; i < Arrayusers.length(); i++) {
                JSONObject userobj = Arrayusers.getJSONObject(i);
                Account account = accounts.get(i);

                JSONArray friendsArray = userobj.getJSONArray("friends");
                for (int j = 0; j < friendsArray.length(); j++) {
                    Account friend = getAccount(friendsArray.getString(j));
                    if (friend != null) {
                        account.getFriendsManagement().addFriend(friend);
                    }
                }

                JSONArray receivedArray = userobj.getJSONArray("receivedfriends");
                for (int j = 0; j < receivedArray.length(); j++) {
                    Account receivedFriend = getAccount(receivedArray.getString(j));
                    if (receivedFriend != null) {
                        account.getFriendsManagement().addReceivedFriendRequest(receivedFriend);
                    }
                }

                JSONArray sentArray = userobj.getJSONArray("sentfriends");
                for (int j = 0; j < sentArray.length(); j++) {
                    Account sentFriend = getAccount(sentArray.getString(j));
                    if (sentFriend != null) {
                        account.getFriendsManagement().addSentFriendRequest(sentFriend);
                    }
                }

                JSONArray blockedArray = userobj.getJSONArray("blocked");
                for (int j = 0; j < blockedArray.length(); j++) {
                    Account blocked = getAccount(blockedArray.getString(j));
                    if (blocked != null) {
                        account.getFriendsManagement().addBlockedUser(blocked);
                    }
                }

                JSONArray blockedByArray = userobj.getJSONArray("blockedBY");
                for (int j = 0; j < blockedByArray.length(); j++) {
                    Account blockedBy = getAccount(blockedByArray.getString(j));
                    if (blockedBy != null) {
                        account.getFriendsManagement().addBlockedBy(blockedBy, account);
                    }
                }

                JSONArray postsArray = userobj.getJSONArray("posts");
                ContentFactory factory = new ContentFactory();
                for (int j = 0; j < postsArray.length(); j++) {
                    JSONObject postObj = postsArray.getJSONObject(j);
                    Map<String, String> contentmap = new HashMap<>();
                    contentmap.put("Text", postObj.getString("caption"));
                    contentmap.put("Path", postObj.getString("path"));
                    LocalDateTime timestamp = LocalDateTime.parse(postObj.getString("timestamp"), DateTimeFormatter.ISO_DATE_TIME);
                    Content c = factory.Feed("Post", postObj.getString("caption"), contentmap, timestamp);
                    account.getContentManagement().addContent(c);
                }

                JSONArray storiesArray = userobj.getJSONArray("stories");
                for (int j = 0; j < storiesArray.length(); j++) {
                    JSONObject storyObj = storiesArray.getJSONObject(j);
                    Map<String, String> contentmap = new HashMap<>();
                    contentmap.put("Text", storyObj.getString("caption"));
                    contentmap.put("Path", storyObj.getString("path"));
                    LocalDateTime timestamp = LocalDateTime.parse(storyObj.getString("timestamp"), DateTimeFormatter.ISO_DATE_TIME);
                    Content c = factory.Feed("Story", storyObj.getString("caption"), contentmap, timestamp);
                    if (!((Stories) c).isExpired()) {
                        account.getContentManagement().addContent(c);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            try (FileWriter file = new FileWriter("database.json")) {
                file.write((new JSONArray()).toString(3));
            } catch (IOException ex) {
                Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            System.out.println("Error in reading from database.json");
        }
    }

    public ArrayList<Account> getAllAccounts() {
        return accounts;
    }

    public Account getAccount(String username) {
        for (Account acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }

    public Account getAccountbyID(String userid) {
        for (Account acc : accounts) {
            if (acc.getUserId().equals(userid)) {
                return acc;
            }
        }
        return null;
    }

    public void addNewAccount(Account account) {        //gets called when a user signs up
        accounts.add(account);

    }

    //VALIDATION TO CHECK IF THE USERNAME ALREADY USED 
    public boolean containsUsername(String string) {
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    //VALIDATION TO CHECK IF THE EMAIL ALREADY USED 
    public boolean containsEmail(String string) {
        for (Account account : accounts) {
            if (account.getEmail().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    //VALIDATION TO CHECK IF THE ENTERED USERNAME AND PASSWORD ARE CORRECT
    public boolean loginCheck(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                if (account.getPassword().equals(password)) {
                    account.setStatus(Activity.Status.ONLINE);
                    return true;
                }
            }
        }
        return false;
    }
}
