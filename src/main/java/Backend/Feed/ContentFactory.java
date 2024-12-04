package Backend.Feed;

import java.time.LocalDate;

/**
 *
 * @author LEGION
 */
public class ContentFactory {
        public Content Feed (String type, String authorId, String Content){
        if(type.equals("Post")){
            return new Posts(authorId, Content);
        }
        if(type.equals("Story")){
            return new Stories(authorId, Content);
        }
        return null;
    }
}
