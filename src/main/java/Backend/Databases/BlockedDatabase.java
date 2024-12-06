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
            for (Account account : accounts) {
                account.getFriendsManagement().getBlockedUsers().removeAll(account.getFriendsManagement().getBlockedUsers());
            }
            String jsonstring = new String(Files.readAllBytes(Paths.get("blocked.json")));
            JSONArray fileArray = new JSONArray(jsonstring);
            for (int i = 0; i < fileArray.length(); i++) {
                JSONObject userJson = fileArray.getJSONObject(i);
                String userid = userJson.getString("userid");
                int friendscount = userJson.getInt("blockedcount");
                for (int j = 1; j <= friendscount; j++) {
                    String blockedid = userJson.getString("blocked" + j);
                    getAccountbyID(userid).getFriendsManagement().Block(getAccountbyID(blockedid).getUsername());
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
    protected void save() {        //save blocked users of each user in blocked.json
        JSONArray blockedArray = new JSONArray();
        for (Account acc : super.accounts) {
            if (!acc.getFriendsManagement().getBlockedUsers().isEmpty()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", acc.getUserId());
                obj.put("blockedcount", acc.getFriendsManagement().getBlockedUsers().size());
                int count = 1;
                for (Account blocked : acc.getFriendsManagement().getBlockedUsers()) {
                    obj.put("blocked" + count, blocked.getUserId());
                    count++;
                }
                blockedArray.put(obj);
            }
        }
        try {
            FileWriter file = new FileWriter("blocked.json");
            file.write("");
            file.write(blockedArray.toString(3));
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error in saving blocked.json");
        }
    }
}
