package Backend.Feed;

import java.time.LocalDate;
import java.util.Map;


/**
 *
 * @author LEGION
 */
public class ContentFactory {
        public Content Feed (String type, String authorId, Map Content){

        if(type.equals("Post")){
            return new Posts(authorId, Content);
        }
        if(type.equals("Story")){
            return new Stories(authorId, Content);
        }
        return null;
    }
}
