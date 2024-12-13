package Backend.Notifications;

import java.time.LocalDateTime;

public class NotiFactory {

    public Notification CreateNoti(String type, LocalDateTime timestamp, boolean opened, String groupNameORsender, String posttypeORnewroleORrequester, String[] contentinfo) {

        if (type.equals("FriendRequest")) {
            return new FriendReqNoti(timestamp, opened, groupNameORsender);
        }
        if (type.equals("AddedToGroup")) {
            return new AddedToGroupNoti(timestamp, opened, groupNameORsender);
        }
        if (type.equals("NewPost")) {
            return new NewPostToGroupNoti(timestamp, opened, groupNameORsender, posttypeORnewroleORrequester, contentinfo);
        }
        if (type.equals("RoleChange")) {
            return new GroupRoleChangeNoti(timestamp, opened, groupNameORsender, posttypeORnewroleORrequester);
        }
        if (type.equals("MemberRequest")) {
            return new MemberJoinReqNoti(timestamp, opened, groupNameORsender, posttypeORnewroleORrequester);
        }
        System.out.println("wrong notification factory input");
        return null;
    }
}
