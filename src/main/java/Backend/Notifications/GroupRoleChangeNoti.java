package Backend.Notifications;

import java.time.LocalDateTime;

public class GroupRoleChangeNoti extends Notification {

    String groupID;
    String newGroupRole;

    public GroupRoleChangeNoti(LocalDateTime timestamp, boolean opened, String groupObject) {
        super(timestamp, opened);
        super.message = "Your role in " + groupObject + " was changed to " + groupObject;
        this.groupID = groupObject;
        this.newGroupRole = groupObject;
    }

    public String getGroupID() {
        return groupID;
    }

    public String getNewGroupRole() {
        return newGroupRole;
    }
}
