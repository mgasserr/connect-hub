package Backend.Notifications;

import java.time.LocalDateTime;

public class NewPostToGroupNoti extends Notification {

    String groupName;
    String type;
    String[] temp;

    public NewPostToGroupNoti(LocalDateTime timestamp, boolean opened, String groupName, String type, String[] temp) {
        super(timestamp, opened);
        if (type.equalsIgnoreCase("Post")) {
            super.message = "A new post was added to group: " + groupName;
        } else {
            super.message = "A new story was added to group: " + groupName;
        }
        this.groupName = groupName;
        this.type = type;
        this.temp = temp;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getType() {
        return type;
    }

    public String[] getTemp() {
        return temp;
    }
}
