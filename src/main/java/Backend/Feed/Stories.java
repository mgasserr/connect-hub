package Backend.Feed;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class Stories extends Content {

    public Stories(String authorId, Map<String, String> Content, LocalDateTime timestamp) {
        super(authorId, Content, timestamp);
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.DAYS.between(this.getTime(), now) >= 1;
    }

}
