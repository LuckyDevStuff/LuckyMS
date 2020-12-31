import de.luckydev.luckyms.MySQLDatabase;
import de.luckydev.luckyms.MySQLException;
import de.luckydev.luckyms.MySQLService;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static MySQLService mySQL;
    public static void main(String[] args) throws MySQLException, SQLException {
        mySQL = new MySQLService("127.0.0.1", 3306, "root", "").connect();
        MySQLDatabase testDB = mySQL.createDBIfNotExists("test").connectToDB("test");
        testDB.createTableIfNotExists("cool", TestTableLayout.class);
    }
}
