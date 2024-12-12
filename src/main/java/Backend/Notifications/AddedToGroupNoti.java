package Backend.Notifications;

import java.time.LocalDateTime;

public class AddedToGroupNoti extends Notification {

    String groupName;

    public AddedToGroupNoti(LocalDateTime timestamp, boolean opened, String groupName) {
        super(timestamp, opened);
        this.groupName = groupName;
        super.message = "Your request to join group" + groupName + ", was accepted!";
    }

    public String getGroupName() {
        return groupName;
    }

}
