package Backend.Feed;

import java.time.LocalDateTime;
import java.util.Map;

public class ContentFactory {

    public Content Feed(String type, String authorId, Map<String, String> Content, LocalDateTime timestamp) {

        if (type.equals("Post")) {
            return new Posts(authorId, Content, timestamp);
        }
        if (type.equals("Story")) {
            return new Stories(authorId, Content, timestamp);
        }
        return null;
    }
}
