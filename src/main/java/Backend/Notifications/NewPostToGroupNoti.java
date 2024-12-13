package Backend.Notifications;

import java.time.LocalDateTime;

public class NewPostToGroupNoti extends Notification {

    String groupName;
    String type;
    String[] temp;

    NewPostToGroupNoti(LocalDateTime timestamp, boolean opened, String groupName, String posttype, String[] temp) {
        super(timestamp, opened);
        if (posttype.equalsIgnoreCase("Post")) {
            super.message = "A new post was added to group: " + groupName;
        } else {
            super.message = "A new story was added to group: " + groupName;
        }
        this.groupName = groupName;
        this.type = posttype;
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
