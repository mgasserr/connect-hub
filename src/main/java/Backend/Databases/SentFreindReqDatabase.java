package Backend.Databases;

import Backend.Account.Account;
import static Backend.Databases.Database.accounts;
import static Backend.Databases.Database.getAccountbyID;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class SentFreindReqDatabase extends Database {

    private static SentFreindReqDatabase sentreqDatabase;

    private SentFreindReqDatabase() {
        super();
    }

    public static SentFreindReqDatabase getInstance() {
        if (sentreqDatabase == null) {
            sentreqDatabase = new SentFreindReqDatabase();
        }
        return sentreqDatabase;
    }

    @Override
    protected void read() {
        try {
            for (Account account : accounts) {
                account.getFriendsManagement().getSentFriendRequests().removeAll(account.getFriendsManagement().getSentFriendRequests());
            }
            String jsonstring = new String(Files.readAllBytes(Paths.get("sentfriendrequests.json")));
            JSONArray fileArray = new JSONArray(jsonstring);
            for (int i = 0; i < fileArray.length(); i++) {
                JSONObject userJson = fileArray.getJSONObject(i);
                String userid = userJson.getString("userid");
                int friendreqcount = userJson.getInt("friendreqcount");
                for (int j = 1; j <= friendreqcount; j++) {
                    String friendid = userJson.getString("friendreq" + j);
                    getAccountbyID(userid).getFriendsManagement().addSentFriendRequest(getAccountbyID(friendid));
                }
            }
        } catch (IOException ex) {
            try {
                FileWriter file = new FileWriter("sentfriendrequests.json");
                file.write((new JSONArray()).toString(3));
                file.close();
            } catch (IOException ex1) {
                System.out.println("Error in readFriends");
            }
        }
    }

    @Override
    protected void save() {
        JSONArray usersArray = new JSONArray();
        for (Account acc : accounts) {
            if (!acc.getFriendsManagement().getSentFriendRequests().isEmpty()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", acc.getUserId());
                obj.put("friendreqcount", acc.getFriendsManagement().getSentFriendRequests().size());
                int count = 1;
                for (Account friend : acc.getFriendsManagement().getSentFriendRequests()) {
                    obj.put("friendreq" + count, friend.getUserId());
                    count++;
                }
                usersArray.put(obj);
            }
        }
        try {
            FileWriter file = new FileWriter("sentfriendrequests.json");
            file.write("");
            file.write(usersArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error in saving sentfriendrequests.json");
        }
    }
}
