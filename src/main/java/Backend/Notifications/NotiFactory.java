package Backend.Notifications;

import java.time.LocalDateTime;

public class NotiFactory {

    public Notification CreateNoti(String type, LocalDateTime timestamp, boolean opened, String groupNameORsender, String posttypeORnewrole, String[] contentinfo) {

        if (type.equals("FriendRequest")) {
            return new FriendReqNoti(timestamp, opened, groupNameORsender);
        }
        if (type.equals("AddedToGroup")) {
            return new AddedToGroupNoti(timestamp, opened, groupNameORsender);
        }
        if (type.equals("NewPost")) {
            return new NewPostToGroupNoti(timestamp, opened, groupNameORsender, posttypeORnewrole, contentinfo);
        }
        if (type.equals("RoleChange")) {
            return new GroupRoleChangeNoti(timestamp, opened, groupNameORsender, posttypeORnewrole);
        }
        System.out.println("wrong notification factory input");
        return null;
    }
}
