package Backend.Notifications;

import java.time.LocalDateTime;

public class FriendReqNoti extends Notification {

    String sender;

    public FriendReqNoti(LocalDateTime timestamp, boolean opened, String sender) {
        super(timestamp, opened);
        super.message = sender + " sent you a friend request!";
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

}
