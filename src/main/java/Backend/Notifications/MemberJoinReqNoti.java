package Backend.Notifications;

import java.time.LocalDateTime;

public class MemberJoinReqNoti extends Notification {

    String groupName;
    String requester;

    MemberJoinReqNoti(LocalDateTime timestamp, boolean opened, String groupName, String requester) {
        super(timestamp, opened);
        this.groupName = groupName;
        this.requester = requester;
        super.message = requester + " is requesting to join group: " + groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getRequester() {
        return requester;
    }

}
