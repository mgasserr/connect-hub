package Backend.Account;

import static Backend.Account.Account.accountsCount;
import Backend.Authentication.PasswordHash;
import Backend.Feed.Content;
import Backend.Feed.Posts;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Database {

    private static Database database = null;
    private ArrayList<Account> accounts = new ArrayList<>();

    private Database() {
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
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
    
    //READS ALL FILES AND FILLS ALL ARRAYLISTS RESPECTIVELY 
    public void readAll() {
        readFromFile();
        readFriends();
        readFriendRequests();
        readContent();
    }
    
    //SAVES ALL ARRAYLISTS IN THEIR RESPECTIVE FILES
    public void saveAll() {
        saveAllAccounts();
        saveFriends();
        saveFriendRequests();
        saveContent();
    }

    //READ DATA FROM JSON
    private void readFromFile() {
        try {
            accountsCount = 0;
            String jsonstring = new String(Files.readAllBytes(Paths.get("accounts.json")));
            JSONArray usersArray = new JSONArray(jsonstring);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                String email = userJson.getString("email");
                String username = userJson.getString("username");
                String password = userJson.getString("password");
                LocalDate dob = LocalDate.parse(userJson.getString("dob"), formatter);
                Account user = new Account(email, username, password, dob);
                Activity.Status status = Activity.Status.valueOf(userJson.getString("status"));
                user.setStatus(status);
                accounts.add(user);
            }
        } catch (IOException ex) {
            try {
                FileWriter file = new FileWriter("accounts.json");
                file.write((new JSONArray()).toString(3));
                file.close();
            } catch (IOException ex1) {
                System.out.println("Error in readfromFile");
            }
        }
    }

    private void readFriends() { //read friends from friends.json
        try {
            String jsonstring = new String(Files.readAllBytes(Paths.get("friends.json")));
            JSONArray fileArray = new JSONArray(jsonstring);
            for (int i = 0; i < fileArray.length(); i++) {
                JSONObject userJson = fileArray.getJSONObject(i);
                String userid = userJson.getString("userid");
                int friendscount = userJson.getInt("friendscount");
                for (int j = 1; j <= friendscount; j++) {
                    String friendid = userJson.getString("friend" + j);
                    getAccountbyID(userid).getProfile().addFriend(getAccountbyID(friendid));
                }
            }
        } catch (IOException ex) {
            try {
                FileWriter file = new FileWriter("friends.json");
                file.write((new JSONArray()).toString(3));
                file.close();
            } catch (IOException ex1) {
                System.out.println("Error in readFriends");
            }
        }
    }

    private void readFriendRequests() { //read friend requests from friendrequests.json
        try {
            String jsonstring = new String(Files.readAllBytes(Paths.get("friendrequests.json")));
            JSONArray fileArray = new JSONArray(jsonstring);
            for (int i = 0; i < fileArray.length(); i++) {
                JSONObject userJson = fileArray.getJSONObject(i);
                String userid = userJson.getString("userid");
                int friendreqcount = userJson.getInt("friendreqcount");
                for (int j = 1; j <= friendreqcount; j++) {
                    String friendid = userJson.getString("friendreq" + j);
                    getAccountbyID(userid).getProfile().addFriendRequest(getAccountbyID(friendid).getUsername());
                }
            }
        } catch (IOException ex) {
            try {
                FileWriter file = new FileWriter("friendrequests.json");
                file.write((new JSONArray()).toString(3));
                file.close();
            } catch (IOException ex1) {
                System.out.println("Error in readFriends");
            }
        }
    }

    private void readContent() {                                //awaiting modifications in the "content" class logic
        
    }

    private JSONArray saveAllAccounts() {
        JSONArray usersArray = new JSONArray();
        for (Account acc : accounts) {
            JSONObject obj = new JSONObject();
            obj.put("userid", acc.getUserId());
            obj.put("email", acc.getEmail());
            obj.put("username", acc.getUsername());
            obj.put("password", acc.getPassword());
            obj.put("dob", acc.getDob());
            obj.put("status", acc.getStatus());
            usersArray.put(obj);
        }
        try {
            FileWriter file = new FileWriter("accounts.json");
            file.write("");
            file.write(usersArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error in saveAccount");
        }
        return usersArray;
    }

    private void saveFriends() {        //save friends of each user in friends.json
        JSONArray friendsArray = new JSONArray();
        for (Account acc : accounts) {
            if (!acc.getProfile().getFriends().isEmpty()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", acc.getUserId());
                obj.put("friendscount", acc.getProfile().getFriends().size());
                int count = 1;
                for (Account friend : acc.getProfile().getFriends()) {
                    obj.put("friend" + count, friend.getUserId());
                    count++;
                }
                friendsArray.put(obj);
            }
        }
        try {
            FileWriter file = new FileWriter("friends.json");
            file.write("");
            file.write(friendsArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error in saving friends.json");
        }
    }

    private void saveFriendRequests() {         //save friends requests that each user has in friendrequests.json
        JSONArray usersArray = new JSONArray();
        for (Account acc : accounts) {
            if (!acc.getProfile().getFriendRequests().isEmpty()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", acc.getUserId());
                obj.put("friendreqcount", acc.getProfile().getFriendRequests().size());
                int count = 1;
                for (Account friend : acc.getProfile().getFriendRequests()) {
                    obj.put("friendreq" + count, friend.getUserId());
                    count++;
                }
                usersArray.put(obj);
            }
        }
        try {
            FileWriter file = new FileWriter("friendrequests.json");
            file.write("");
            file.write(usersArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error in saving friendrequests.json");
        }
    }

    private void saveContent() {                            //awaiting modifications in the "content" class logic
//        JSONArray usersArray = new JSONArray();
//        for (Account acc : accounts) {
//            JSONObject obj = new JSONObject();
//            obj.put("userid", acc.getUserId());
//            obj.put("postscount", acc.getProfile().getPostsCount());
//            obj.put("storiescount", acc.getProfile().getStoriesCount());
//            for (Content content : acc.getProfile().getContent()) {
//                if (content instanceof Posts) {
//                    obj.put("type", "post");
//                } else {
//                    obj.put("type", "story");
//                }
//                obj.put("contentid", content.getContentId());
//                obj.put("imagepath", content.getContent());
//                obj.put("timestamp", content.getTime());
//
//            }
//            usersArray.put(obj);
//        }
//        try {
//            FileWriter file = new FileWriter("content.json");
//            file.write("");
//            file.write(usersArray.toString(3));
//            file.flush();
//            file.close();
//        } catch (IOException e) {
//            System.out.println("Error in saving content.json");
//        }
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

    public String changePassword(Account user, String oldpassword, String newpassword, String confirmpassword) {
        String hashpass;
        try {

            hashpass = PasswordHash.hashPassword(oldpassword);
            if (!user.getPassword().equals(hashpass)) {
                return "INVALIDOLDPASS";
            }
            if (!newpassword.equals(confirmpassword)) {
                return "INVALIDCONFIRMPASS";
            }
            String newhashpass = PasswordHash.hashPassword(newpassword);
            user.setPassword(newhashpass);

            System.out.println(newhashpass);
            return "PASSWORDCHANGED";

        } catch (NoSuchAlgorithmException ex) {

        }
        return null;
    }

}
