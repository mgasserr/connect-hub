package Backend.Databases;

public class SentFreindReqDatabase extends Database {

    private static SentFreindReqDatabase sentreqDatabase;

    private SentFreindReqDatabase() {
        super();
    }

    public static SentFreindReqDatabase getInstance() {
        if (sentreqDatabase == null) {
            sentreqDatabase = new SentFreindReqDatabase();
        }
        return sentreqDatabase;
    }

    @Override
    protected void read() {
    }

    @Override
    protected void save() {
    }

}
