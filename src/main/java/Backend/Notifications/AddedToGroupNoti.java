package Backend.Notifications;

import java.time.LocalDateTime;

public class AddedToGroupNoti extends Notification {

    String groupID;

    public AddedToGroupNoti(LocalDateTime timestamp, boolean opened, String groupObject) {
        super(timestamp, opened);
        super.message = "You were added to group: " + groupObject;
        this.groupID = groupObject;
    }

    public String getGroupID() {
        return groupID;
    }

}
