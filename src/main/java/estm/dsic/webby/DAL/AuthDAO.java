package estm.dsic.webby.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import estm.dsic.webby.Models.Account;

public class AuthDAO {
    public static Account auth(String email) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("select id, is_admin, pass from account where email = ?;");
        stmt.setString(1, email);

        ResultSet res;
        res = stmt.executeQuery();
        if (res.next()) {
            return new Account(email, res.getInt("id"), res.getBoolean("is_admin"), res.getString("pass"));
        }

        return null;
    }

    public static boolean signup(String email, String passhash) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("insert into account (is_admin, email, pass) values (0, ?, ?);");

        stmt.setString(1, email);
        stmt.setString(2, passhash);

        return stmt.executeUpdate() >= 1;
    }
}
