package Backend.Feed;

import java.time.LocalDateTime;
import java.util.Map;

public class Posts extends Content {

    public Posts(String authorId, Map Content,LocalDateTime timestamp) {
        super(authorId, Content,timestamp);
    }
}
