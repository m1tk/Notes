package estm.dsic.webby.DAL;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Connector {
    public static Connection get_connection() throws SQLException {
        DataSource mydb;
        try {
            mydb = InitialContext.doLookup("jdbc/mariach");
        } catch (NamingException e) {
            System.err.println("Can't find mariadb driver");
            System.exit(1);
            return null;
        }
        return mydb.getConnection();
    }
}
