package estm.dsic.webby.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import estm.dsic.webby.Models.Account;

public class AdminDAO {
    public static List<Account> users() throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("select id, email from account where is_admin = false;");

        List<Account> ret = new LinkedList<>();

        ResultSet res;
        res = stmt.executeQuery();
        while (res.next()) {
            ret.add(new Account(res.getString("email"), res.getInt("id"), false, null));
        }

        return ret;
    }

    public static boolean update_user(int id, String email) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("update account set email = ? where id = ?;");
        stmt.setString(1, email);
        stmt.setInt(2, id);

        return stmt.executeUpdate() >= 1;
    }

    public static boolean delete_user(int id) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("delete from account where id = ?;");
        stmt.setInt(1, id);

        return stmt.executeUpdate() >= 1;
    }
}
