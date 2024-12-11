package Backend.Databases;

import Backend.Account.Account;
import org.json.JSONArray;
import org.json.JSONObject;
import Backend.Notifications.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class NotificationsDatabase {

    private static NotificationsDatabase notidatabase;
    private Databases D = Databases.getInstance();

    private NotificationsDatabase() {
        super();
    }

    public static NotificationsDatabase getInstance() {
        if (notidatabase == null) {
            notidatabase = new NotificationsDatabase();
        }
        return notidatabase;
    }

    public void save() {
        JSONArray Arrayusers = new JSONArray();
        for (Account acc : D.getAllAccounts()) {
            JSONObject userobj = new JSONObject();
            userobj.put("userid", acc.getUserId());
            JSONArray Arraynotis = new JSONArray();
            for (Notification noti : acc.getNotifications()) {
                JSONObject notiobj = new JSONObject();
                switch (noti) {
                    case FriendReqNoti friendReqNoti -> {
                        notiobj.put("type", "friendreq");
                        notiobj.put("sender", friendReqNoti.getSender());
                    }
                    case AddedToGroupNoti addedToGroupNoti -> {
                        notiobj.put("type", "addedtogroup");
                        notiobj.put("groupid", addedToGroupNoti.getGroupID());
                    }
                    case GroupRoleChangeNoti groupRoleChangeNoti -> {
                        notiobj.put("type", "rolechange");
                        notiobj.put("groupid", groupRoleChangeNoti.getGroupID());
                        notiobj.put("newrole", groupRoleChangeNoti.getNewGroupRole());
                    }
                    case NewPostToGroupNoti newPostToGroupNoti -> {
                        notiobj.put("type", "newposttogroup");
                        notiobj.put("groupid", newPostToGroupNoti.getGroupID());
                    }
                    default -> {
                        System.out.println("Error in reading notification type.");
                    }
                }
                notiobj.put("opened", noti.isOpenedString());
                notiobj.put("time", noti.getTimestamp());

                Arraynotis.put(notiobj);
            }
            userobj.put("notifications", Arraynotis);
            Arrayusers.put(userobj);
        }
        try (FileWriter file = new FileWriter("notidatabase.json")) {
            file.write(Arrayusers.toString(3));
        } catch (IOException e) {
            System.out.println("Error in saving in notidatabase.json");
        }
    }

    public void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader("notidatabase.json"))) {
            Notification.resetNotiCount();
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            JSONArray Arrayusers = new JSONArray(jsonContent.toString());
            for (int i = 0; i < Arrayusers.length(); i++) {
                JSONObject userobj = Arrayusers.getJSONObject(i);
                Account acc = D.getAccountbyID(userobj.get("userid").toString());
                JSONArray Arraynotis = userobj.getJSONArray("notifications");
                for (int j = 0; j < Arraynotis.length(); j++) {
                    JSONObject notiobj = Arraynotis.getJSONObject(j);
                    switch (notiobj.getString("type")) {
                        case "friendreq" -> {
                            Notification newnoti = new FriendReqNoti(LocalDateTime.parse(notiobj.getString("time")), notiobj.getBoolean("opened"), notiobj.getString("sender"));
                            acc.addNotification(newnoti);
                        }
                        case "addedtogroup" -> {             //WAITING FOR ACCOUNT.GETGROUPBYID!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            Notification newnoti = new AddedToGroupNoti(LocalDateTime.parse(notiobj.getString("time")), notiobj.getBoolean("opened"), notiobj.getString("groupid"));
                            acc.addNotification(newnoti);
                        }
                        case "rolechange" -> {               //WAITING FOR ACCOUNT.GETGROUPBYID!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            Notification newnoti = new GroupRoleChangeNoti(LocalDateTime.parse(notiobj.getString("time")), notiobj.getBoolean("opened"), notiobj.getString("groupid"));
                            acc.addNotification(newnoti);
                        }
                        case "newposttogroup" -> {            //WAITING FOR ACCOUNT.GETGROUPBYID!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            Notification newnoti = new NewPostToGroupNoti(LocalDateTime.parse(notiobj.getString("time")), notiobj.getBoolean("opened"), notiobj.getString("groupid"));
                            acc.addNotification(newnoti);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            try (FileWriter file = new FileWriter("notidatabase.json")) {
                file.write((new JSONArray()).toString(3));
            } catch (IOException ex) {
                Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            System.out.println("Error in reading from notidatabase.json");
        }
    }
}
