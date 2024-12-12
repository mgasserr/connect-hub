package Backend.Databases;

import Backend.Account.*;
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

    public void save() {
        JSONArray Arrayusers = new JSONArray();
        for (Account acc : accounts) {
            JSONObject userobj = new JSONObject();

            JSONArray Arrayinfo = new JSONArray();
            JSONArray Arrayposts = new JSONArray();
            JSONArray Arraystories = new JSONArray();
            JSONArray Arraygroups = new JSONArray();
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
                    postsobj.put("AuthorID", content.getAuthorId());
                    postsobj.put("timestamp", content.getTime().toString()); // Ensure timestamp is serialized as string
                    Arrayposts.put(postsobj);
                } else if (content instanceof Stories) {
                    JSONObject storyobj = new JSONObject();
                    storyobj.put("caption", content.getContentMap().get("Text"));
                    storyobj.put("path", content.getContentMap().get("Path"));
                    storyobj.put("AuthorID", content.getAuthorId());
                    storyobj.put("timestamp", content.getTime().toString()); // Ensure timestamp is serialized as string
                    Arraystories.put(storyobj);
                }
            }

            // GROUPS
            for (Group group : getAccount(acc.getUsername()).getGroups()) {
                JSONObject groupsobj = new JSONObject();
                Group g = getGroup(group.getName());
                groupsobj.put("name", g.getName());
                groupsobj.put("description", g.getDescription());
                groupsobj.put("picture", g.getPicture());
                groupsobj.put("creator", g.getCreator().getUsername());
                groupsobj.put("admins", g.getAdminsUsernames());
                groupsobj.put("members", g.getMembersUsernames());
                groupsobj.put("requests", g.getRequestUsernames());
                JSONArray Arraygposts = new JSONArray();
                JSONArray Arraygstories = new JSONArray();
                for (Content content : g.getContent()) {
                    if (content instanceof Posts) {
                        JSONObject postsobj = new JSONObject();
                        postsobj.put("caption", content.getContentMap().get("Text"));
                        postsobj.put("path", content.getContentMap().get("Path"));
                        postsobj.put("AuthorID", content.getAuthorId());
                        postsobj.put("timestamp", content.getTime().toString()); // Ensure timestamp is serialized as string
                        Arraygposts.put(postsobj);
                    } else if (content instanceof Stories) {
                        JSONObject storyobj = new JSONObject();
                        storyobj.put("caption", content.getContentMap().get("Text"));
                        storyobj.put("path", content.getContentMap().get("Path"));
                        storyobj.put("AuthorID", content.getAuthorId());
                        storyobj.put("timestamp", content.getTime().toString()); // Ensure timestamp is serialized as string
                        Arraygstories.put(storyobj);
                    }

                }
                groupsobj.put("posts", Arraygposts);
                groupsobj.put("stories", Arraygstories);
                Arraygroups.put(groupsobj);
            }

            userobj.put("friends", friendsusernames);
            userobj.put("posts", Arrayposts);
            userobj.put("stories", Arraystories);
            userobj.put("groups", Arraygroups);
            userobj.put("userid", acc.getUserId());
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
            Account.resetAccountsCount();
            Content.resetContentCount();
            Group.resetGroupCount();
            Group.getGroups().clear();

            accounts.clear();
            for (Group group : Group.getGroups()) {
                getGroup(group.getName()).getAdmins().clear();
                getGroup(group.getName()).getMembers().clear();
                getGroup(group.getName()).getRequests().clear();
                getGroup(group.getName()).getContent().clear();
            }

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
                    account.setStatus(Activity.Status.OFFLINE);
                } else {
                    account.setStatus(Activity.Status.ONLINE);
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
                    String AuthorID = postObj.getString("AuthorID");
                    LocalDateTime timestamp = LocalDateTime.parse(postObj.getString("timestamp"), DateTimeFormatter.ISO_DATE_TIME);
                    Content c = factory.Feed("Post", AuthorID, contentmap, timestamp);
                    account.getContentManagement().addContent(c);
                }

                JSONArray storiesArray = userobj.getJSONArray("stories");
                for (int j = 0; j < storiesArray.length(); j++) {
                    JSONObject storyObj = storiesArray.getJSONObject(j);
                    Map<String, String> contentmap = new HashMap<>();
                    contentmap.put("Text", storyObj.getString("caption"));
                    contentmap.put("Path", storyObj.getString("path"));
                    String AuthorID = storyObj.getString("AuthorID");
                    LocalDateTime timestamp = LocalDateTime.parse(storyObj.getString("timestamp"), DateTimeFormatter.ISO_DATE_TIME);
                    Content c = factory.Feed("Story", AuthorID, contentmap, timestamp);
                    if (!((Stories) c).isExpired()) {
                        account.getContentManagement().addContent(c);
                    }
                }

                JSONArray groupsArray = userobj.getJSONArray("groups");
                for (int j = 0; j < groupsArray.length(); j++) {
                    JSONObject groupObj = groupsArray.getJSONObject(j);
                    String creator = groupObj.getString("creator");
                    String name = groupObj.getString("name");
                    String description = groupObj.getString("description");
                    ImageIcon picture = new ImageIcon(groupObj.getString("picture"));
                    Group group = new Group(getAccount(creator), name, description, picture);
                    group.addGroup(group, account.getUsername());

                    JSONArray adminsArray = groupObj.getJSONArray("admins");
                    for (int k = 0; k < adminsArray.length(); k++) {
                        Account Admin = getAccount(adminsArray.getString(k));
                        if (adminsArray != null) {
                            getGroup(name).addAdmin(Admin.getUsername());
                        }
                    }

                    JSONArray membersArray = groupObj.getJSONArray("members");
                    for (int k = 0; k < membersArray.length(); k++) {
                        Account Member = getAccount(membersArray.getString(k));
                        if (membersArray != null) {
                            getGroup(name).addMember(Member.getUsername());
                        }
                    }

                    JSONArray requestsArray = groupObj.getJSONArray("requests");
                    for (int k = 0; k < requestsArray.length(); k++) {
                        Account Request = getAccount(requestsArray.getString(k));
                        if (requestsArray != null) {
                            getGroup(name).addRequest(Request.getUsername());
                        }
                    }

                    JSONArray gpostsArray = groupObj.getJSONArray("posts");
                    ContentFactory gfactory = new ContentFactory();
                    for (int k = 0; k < gpostsArray.length(); k++) {
                        JSONObject gpostObj = gpostsArray.getJSONObject(k);
                        Map<String, String> contentmap = new HashMap<>();
                        contentmap.put("Text", gpostObj.getString("caption"));
                        contentmap.put("Path", gpostObj.getString("path"));
                        String AuthorID = gpostObj.getString("AuthorID");
                        LocalDateTime timestamp = LocalDateTime.parse(gpostObj.getString("timestamp"), DateTimeFormatter.ISO_DATE_TIME);
                        Content c = factory.Feed("Post", AuthorID, contentmap, timestamp);
                        getGroup(name).addContent(c);
                    }

                    JSONArray gstoriesArray = groupObj.getJSONArray("stories");

                    for (int k = 0; k < gstoriesArray.length(); k++) {
                        JSONObject gstoryObj = gstoriesArray.getJSONObject(k);
                        Map<String, String> contentmap = new HashMap<>();
                        contentmap.put("Text", gstoryObj.getString("caption"));
                        contentmap.put("Path", gstoryObj.getString("path"));
                        String AuthorID = gstoryObj.getString("AuthorID");
                        LocalDateTime timestamp = LocalDateTime.parse(gstoryObj.getString("timestamp"), DateTimeFormatter.ISO_DATE_TIME);
                        Content c = factory.Feed("Story", AuthorID, contentmap, timestamp);
                        if (!((Stories) c).isExpired()) {
                            getGroup(name).addContent(c);
                        }

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

    public ImageIcon getPfpImgDatabase(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account.getProfile().getProfileImg();
            }
        }
        return null;
    }

    public ArrayList<Account> getSuggestedAccountsDATABASE(String username) {
        ArrayList<Account> suggestedAccounts = new ArrayList<>();
        for (Account potentialFriend : accounts) {
            if (!potentialFriend.getUsername().equals(getAccount(username).getUsername()) && !getAccount(username).getFriendsManagement().getFriends().contains(potentialFriend) && !getAccount(username).getFriendsManagement().getBlockedUsers().contains(potentialFriend) && !getAccount(username).getFriendsManagement().getBlockedBy().contains(potentialFriend) && !getAccount(username).getFriendsManagement().getReceivedFriendRequests().contains(potentialFriend) && !getAccount(username).getFriendsManagement().getSentFriendRequests().contains(potentialFriend)) {
                suggestedAccounts.add(potentialFriend);
            }
        }
        return suggestedAccounts;
    }

    public ArrayList<Account> getFriendsDATABASE(String username) {
        ArrayList<Account> suggestedAccounts = new ArrayList<>();
        for (Account friend : accounts) {
            if (getAccount(username).getFriendsManagement().getFriends().contains(friend)) {
                suggestedAccounts.add(friend);
            }
        }
        return suggestedAccounts;
    }

    public ArrayList<Account> getReceivedReqsDATABASE(String username) {
        ArrayList<Account> suggestedAccounts = new ArrayList<>();
        for (Account friend : accounts) {
            if (getAccount(username).getFriendsManagement().getReceivedFriendRequests().contains(friend)) {
                suggestedAccounts.add(friend);
            }
        }
        return suggestedAccounts;
    }

    public ArrayList<Account> getSentReqsDATABASE(String username) {
        ArrayList<Account> suggestedAccounts = new ArrayList<>();
        for (Account friend : accounts) {
            if (getAccount(username).getFriendsManagement().getSentFriendRequests().contains(friend)) {
                suggestedAccounts.add(friend);
            }
        }
        return suggestedAccounts;
    }

    public ArrayList<Account> getBlockedDATABASE(String username) {
        ArrayList<Account> suggestedAccounts = new ArrayList<>();
        for (Account friend : accounts) {
            if (getAccount(username).getFriendsManagement().getBlockedUsers().contains(friend)) {
                suggestedAccounts.add(friend);
            }
        }
        return suggestedAccounts;
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

    //gets called when a user signs up
    public void addNewAccount(Account account) {
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

    ///////////////////////////////////////////////////////////////////////////
    public Group getGroupToCreator(String groupName, String accountName) {
        for (Group group : getAccount(accountName).getGroups()) {
            if (group.getName().equalsIgnoreCase(groupName)) {
                return group;
            }
        }
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////

    public Group getGroup(String groupName) {
        for (Group group : Group.getGroups()) {
            if (group.getName().equalsIgnoreCase(groupName)) {
                return group;
            }
        }
        return null;
    }

    public Account getGroupCreator(String groupName, String accountName) {
        for (Group group : getAccount(accountName).getGroups()) {
            if (group.getName().equalsIgnoreCase(groupName)) {
                return group.getCreator();
            }
        }
        return null;
    }

    public ArrayList<Account> getGroupAdmins(String groupName, String accountName) {
        for (Group group : getAccount(accountName).getGroups()) {
            if (group.getName().equalsIgnoreCase(groupName)) {
                return group.getAdmins();
            }
        }
        return null;
    }

    public ArrayList<Account> getGroupMembers(String groupName, String accountName) {
        for (Group group : getAccount(accountName).getGroups()) {
            if (group.getName().equalsIgnoreCase(groupName)) {
                return group.getMembers();
            }
        }
        return null;
    }

    public ArrayList<Content> getGroupContent(String groupName, String accountName) {
        for (Group group : getAccount(accountName).getGroups()) {
            if (group.getName().equalsIgnoreCase(groupName)) {
                return group.getContent();
            }
        }
        return null;
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

    public void logoutDatabase(String username) {
        read();
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                account.setStatus(Activity.Status.OFFLINE);
                save();
                return;
            }
        }
    }
}
