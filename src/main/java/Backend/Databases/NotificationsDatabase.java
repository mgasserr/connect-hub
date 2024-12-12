package Backend.Databases;

import Backend.Account.Account;
import Backend.Notifications.*;
import org.json.JSONArray;
import org.json.JSONObject;
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
    private final Databases D = Databases.getInstance();

    private NotificationsDatabase() {
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
                        notiobj.put("groupname", addedToGroupNoti.getGroupName());
                    }
                    case GroupRoleChangeNoti groupRoleChangeNoti -> {
                        notiobj.put("type", "rolechange");
                        notiobj.put("groupname", groupRoleChangeNoti.getGroupName());
                        notiobj.put("newrole", groupRoleChangeNoti.getnewRole());
                    }
                    case NewPostToGroupNoti newPostToGroupNoti -> {
                        notiobj.put("type", "newposttogroup");
                        notiobj.put("groupname", newPostToGroupNoti.getGroupName());
                        notiobj.put("posttype", newPostToGroupNoti.getType());
                        JSONArray temparray = new JSONArray();
                        for (String temp : newPostToGroupNoti.getTemp()) {
                            temparray.put(temp);
                        }
                        notiobj.put("temp", temparray);
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
                Account acc = D.getAccountbyID(userobj.getString("userid"));
                JSONArray Arraynotis = userobj.getJSONArray("notifications");
                for (int j = 0; j < Arraynotis.length(); j++) {
                    JSONObject notiobj = Arraynotis.getJSONObject(j);
                    switch (notiobj.getString("type")) {
                        case "friendreq" -> {
                            Notification newnoti = new FriendReqNoti(LocalDateTime.parse(notiobj.getString("time")), notiobj.getBoolean("opened"), notiobj.getString("sender"));
                            acc.addNotification(newnoti);
                        }
                        case "addedtogroup" -> {
                            Notification newnoti = new AddedToGroupNoti(LocalDateTime.parse(notiobj.getString("time")), notiobj.getBoolean("opened"), notiobj.getString("groupname"));
                            acc.addNotification(newnoti);
                        }
                        case "rolechange" -> {
                            Notification newnoti = new GroupRoleChangeNoti(LocalDateTime.parse(notiobj.getString("time")), notiobj.getBoolean("opened"), notiobj.getString("groupname"), notiobj.getString("newrole"));
                            acc.addNotification(newnoti);
                        }
                        case "newposttogroup" -> {
                            JSONArray temparray = notiobj.getJSONArray("temp");
                            String[] tempstring = new String[temparray.length()];
                            for (int k = 0; k < temparray.length(); k++) {
                                tempstring[k] = temparray.getString(k);
                            }
                            Notification newnoti = new NewPostToGroupNoti(LocalDateTime.parse(notiobj.getString("time")), notiobj.getBoolean("opened"), notiobj.getString("groupname"), notiobj.getString("posttype"), tempstring);
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
