package estm.dsic.webby.Models;

public class Account {
    public String email;
    public int id;
    public boolean is_admin;
    public String passhash;

    public Account(String email, int id, boolean is_admin, String passhash) {
        this.email    = email;
        this.id       = id;
        this.is_admin = is_admin;
        this.passhash = passhash;
    }

    public Account() {}
}
