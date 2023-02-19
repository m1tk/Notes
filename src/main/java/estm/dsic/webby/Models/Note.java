package estm.dsic.webby.Models;

import java.util.Date;

public class Note {
    public long id;
    public Date date;
    public String title;
    public String body;

    public Note(long id, Date date, String title, String body) {
        this.id    = id;
        this.date  = date;
        this.title = title;
        this.body  = body;
    }

    public Note() {}
}
