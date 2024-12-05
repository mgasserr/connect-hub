/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Databases;

/**
 *
 * @author Mohamed
 */
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
