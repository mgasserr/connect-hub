package Backend.Databases;

import Backend.Account.Account;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReceivedFreindReqDatabase extends Database {

    private static ReceivedFreindReqDatabase receivedreqDatabase;

    private ReceivedFreindReqDatabase() {
        super();
    }

    public static ReceivedFreindReqDatabase getInstance() {
        if (receivedreqDatabase == null) {
            receivedreqDatabase = new ReceivedFreindReqDatabase();
        }
        return receivedreqDatabase;
    }

    @Override
    protected void read() { //read friends from friends.json
        try {
            String jsonstring = new String(Files.readAllBytes(Paths.get("friendrequests.json")));
            JSONArray fileArray = new JSONArray(jsonstring);
            for (int i = 0; i < fileArray.length(); i++) {
                JSONObject userJson = fileArray.getJSONObject(i);
                String userid = userJson.getString("userid");
                int friendreqcount = userJson.getInt("friendreqcount");
                for (int j = 1; j <= friendreqcount; j++) {
                    String friendid = userJson.getString("friendreq" + j);
                    getAccountbyID(userid).getFriendsManagement().addReceivedFriendRequest(getAccountbyID(friendid));
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

    @Override
    protected void save() {        //save friends of each user in friends.json
        JSONArray usersArray = new JSONArray();
        for (Account acc : accounts) {
            if (!acc.getFriendsManagement().getReceivedFriendRequests().isEmpty()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", acc.getUserId());
                obj.put("friendreqcount", acc.getFriendsManagement().getReceivedFriendRequests().size());
                int count = 1;
                for (Account friend : acc.getFriendsManagement().getReceivedFriendRequests()) {
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
}
