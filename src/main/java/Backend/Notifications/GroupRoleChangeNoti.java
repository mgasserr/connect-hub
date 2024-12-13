package Backend.Notifications;

import Backend.Feed.Group;
import java.time.LocalDateTime;

public class GroupRoleChangeNoti extends Notification {

    String groupName;
    String newRole;

    GroupRoleChangeNoti(LocalDateTime timestamp, boolean opened, String groupName, String newRole) {
        super(timestamp, opened);
        this.groupName = groupName;
        this.newRole = newRole;
        super.message = "Your role in group: " + groupName + ", was changed to " + newRole;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getnewRole() {
        return newRole;
    }
}
