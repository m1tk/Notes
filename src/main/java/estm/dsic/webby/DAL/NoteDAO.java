package estm.dsic.webby.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import estm.dsic.webby.Models.Account;
import estm.dsic.webby.Models.Note;

public class NoteDAO {
    public static List<Note> notes(Account user) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("select id, title, date from note where account_id = ? order by date desc;");
        stmt.setInt(1, user.id);

        List<Note> ret = new LinkedList<>();

        ResultSet res;
        res = stmt.executeQuery();
        while (res.next()) {
            ret.add(new Note(res.getLong("id"), res.getTimestamp("date"), res.getString("title"), null));
        }

        return ret;
    }

    public static Note note(Account user, long id) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("select id, title, body, date from note where account_id = ? and id = ?;");
        stmt.setInt(1, user.id);
        stmt.setLong(2, id);

        Note ret = null;
        ResultSet res;
        res = stmt.executeQuery();
        if (res.next()) {
            ret = new Note(res.getLong("id"), res.getTimestamp("date"), res.getString("title"), res.getString("body"));
        }

        return ret;
    }

    public static long add_note(Account user, Note note) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("insert into note (date, title, body, account_id) values (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        stmt.setTimestamp(1, new java.sql.Timestamp(note.date.getTime()));
        stmt.setString(2, note.title);
        stmt.setString(3, note.body);
        stmt.setInt(4, user.id);

        stmt.execute();
        ResultSet rs = stmt.getGeneratedKeys();
        
        if (rs.next()) {
            return rs.getLong(1);
        }

        return -1;
    }

    public static boolean update_note(Account user, Note note) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("update note set date = ?, title = ?, body = ? where account_id = ? and id = ?;");
        stmt.setTimestamp(1, new java.sql.Timestamp(note.date.getTime()));
        stmt.setString(2, note.title);
        stmt.setString(3, note.body);
        stmt.setInt(4, user.id);
        stmt.setLong(5, note.id);

        return stmt.executeUpdate() >= 1;
    }

    public static boolean delete_note(Account user, long note_id) throws SQLException {
        Connection conn        = Connector.get_connection();
        PreparedStatement stmt = conn.prepareStatement("delete from note where account_id = ? and id = ?;");
        stmt.setInt(1, user.id);
        stmt.setLong(2, note_id);

        return stmt.executeUpdate() >= 1;
    }
}
