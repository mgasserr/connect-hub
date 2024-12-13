package Backend.Notifications;

import java.time.LocalDateTime;

public abstract class Notification {

    private String notiID;
    String message;
    private LocalDateTime timestamp;
    private boolean opened;
    private static int notiCounter;

    Notification(LocalDateTime timestamp, boolean opened) {
        notiCounter++;
        notiID = notiCounter + "";
        if (timestamp == null) {
            this.timestamp = LocalDateTime.now();
        } else {
            this.timestamp = timestamp;
        }
        this.opened = opened;
    }

    public String getNotiID() {
        return notiID;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isOpenedBoolean() {
        return opened;
    }

    public String isOpenedString() {
        return opened + "";
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public static void resetNotiCount() {
        notiCounter = 0;
    }
}
