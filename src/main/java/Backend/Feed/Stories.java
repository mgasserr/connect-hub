package Backend.Feed;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 *
 * @author LEGION
 */
public class Stories extends Content {

    public Stories(String authorId, Map Content) {
        super(authorId, Content);
    }

   public boolean isExpired() {
        LocalDate now = LocalDate.now();
        return ChronoUnit.DAYS.between(this.getTime(), now) >= 1;
    }

}
