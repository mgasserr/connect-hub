package Backend.Notifications;

import java.time.LocalDateTime;

public class NewPostToGroupNoti extends Notification {

    String groupID;

    public NewPostToGroupNoti(LocalDateTime timestamp, boolean opened, String groupObject) {
        super(timestamp, opened);
        super.message = "New post added to group: " + groupObject;
        this.groupID = groupObject;
    }

    public String getGroupID() {
        return groupID;
    }
}
