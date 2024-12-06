package Backend.Databases;

import Backend.Account.Account;
import static Backend.Databases.Database.getAccountbyID;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class BlockedDatabase extends Database {

    private static BlockedDatabase blockedDatabase;

    private BlockedDatabase() {
        super();
    }

    public static BlockedDatabase getInstance() {
        if (blockedDatabase == null) {
            blockedDatabase = new BlockedDatabase();
        }
        return blockedDatabase;
    }

    @Override
    protected void read() { //read blocked users from blocked.json
        try {
            String jsonstring = new String(Files.readAllBytes(Paths.get("blocked.json")));
            JSONArray fileArray = new JSONArray(jsonstring);
            for (int i = 0; i < fileArray.length(); i++) {
                JSONObject userJson = fileArray.getJSONObject(i);
                String userid = userJson.getString("userid");
                int friendscount = userJson.getInt("blockedcount");
                for (int j = 1; j <= friendscount; j++) {
                    String blockedid = userJson.getString("blocked" + j);
                    getAccountbyID(userid).getFriendsManagement().addBlockedUser(getAccountbyID(blockedid));//asdasdasda
                }
            }
        } catch (IOException ex) {
            try {
                FileWriter file = new FileWriter("blocked.json");
                file.write((new JSONArray()).toString(3));
                file.close();
            } catch (IOException ex1) {
                System.out.println("Error in readBlocked");
            }
        }
    }

    @Override
    protected void save() {        //save friends of each user in friends.json
        JSONArray friendsArray = new JSONArray();
        for (Account acc : super.accounts) {
            if (!acc.getFriendsManagement().getFriends().isEmpty()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", acc.getUserId());
                obj.put("friendscount", acc.getFriendsManagement().getFriends().size());
                int count = 1;
                for (Account friend : acc.getFriendsManagement().getFriends()) {
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
}
