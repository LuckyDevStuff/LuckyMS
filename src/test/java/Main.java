import de.luckydev.luckyms.MySQLDatabase;
import de.luckydev.luckyms.MySQLException;
import de.luckydev.luckyms.MySQLService;
import de.luckydev.luckyms.MySQLTable;

import java.sql.SQLException;

public class Main {
    public static MySQLService mySQL;
    public static void main(String[] args) throws MySQLException, SQLException {
        mySQL = new MySQLService("127.0.0.1", 3306, "root", "").connect();
        MySQLDatabase testDB = mySQL.createDBIfNotExists("test").connectToDB("test");
        mySQL.disconnect();
        testDB.tryDeleteTable("cool").createTableIfNotExists("cool", TestTableLayout.class);
        MySQLTable table = testDB.getTable("cool");
        table.insert("T35", "5", "This is a test");
        System.out.println(table.sGetWhereIs("id", "uuid", "T35"));
        testDB.disconnect();
    }
}
