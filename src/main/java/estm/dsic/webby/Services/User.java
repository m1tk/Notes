package estm.dsic.webby.Services;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import estm.dsic.webby.DAL.NoteDAO;
import estm.dsic.webby.Errors.UserError;
import estm.dsic.webby.Models.Account;
import estm.dsic.webby.Models.Note;
import estm.dsic.webby.Models.Recent;

public class User {
    public static List<Note> notes(Account user) throws UserError {
        List<Note> notes;

        try {
            notes = NoteDAO.notes(user);
        } catch (SQLException e) {
            throw new UserError("Error fetching notes. Try later.");
        }

        return notes;
    }

    public static void add_note(Account user, Recent recent, String title, String body) throws UserError {
        if (title == "" || body == "") {
            throw new UserError("Title or body must not be empty");
        }

        try {
            Date time = Date.from(Instant.now());
            long note_id = -1;
            if ((note_id = NoteDAO.add_note(user, new Note(0, time, title, body))) > -1) {
                recent.r.insertElementAt(new Note(note_id, time, title, null), 0);
                return;
            }
        } catch (SQLException e) {
            System.err.println("Error database: "+e);
        }
        throw new UserError("Error fetching notes. Try later.");
    }

    public static void edit_note(Account user, Recent recent, String id, String title, String body) throws UserError {
        long num_id = User.parse_note_id(id);

        if (title == "" || body == "") {
            throw new UserError("Title or body must not be empty");
        }

        try {
            Date time = Date.from(Instant.now());
            if (NoteDAO.update_note(user, new Note(num_id, time, title, body))) {
                Optional<Note> n = recent.r
                    .stream()
                    .filter(x -> x.id == num_id)
                    .findFirst();

                if (n.isEmpty()) {
                    recent.r.insertElementAt(new Note(num_id, time, title, null), 0);
                } else {
                    Note note  = n.get();
                    recent.r.remove(note);
                    note.date  = time;
                    note.title = title;
                    recent.r.insertElementAt(note, 0);
                }
                return;
            }
        } catch (SQLException e) {
            System.err.println("Error database: "+e);
        }
        throw new UserError("Error fetching notes. Try later.");
    }

    public static void delete_note(Account user, Recent recent, String id) throws UserError {
        long num_id = User.parse_note_id(id);

        try {
            if (NoteDAO.delete_note(user, num_id)) {
                recent.r.removeIf(x -> x.id == num_id);
                return;
            }
        } catch (SQLException e) {
            System.err.println("Error database: "+e);
        }
        throw new UserError("Error deleting note. Try later.");
    }

    public static Note note(Account user, String id) throws UserError {
        long num_id = User.parse_note_id(id);

        Note note;
        try {
            if ((note = NoteDAO.note(user, num_id)) != null) {
                return note;
            }
        } catch (SQLException e) {
            System.err.println("Error database: "+e);
        }
        throw new UserError("Error fetching note. Try later.");
    }

    public static long parse_note_id(String id) throws UserError {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new UserError("Note ID is not a number?");
        }
    }
}
