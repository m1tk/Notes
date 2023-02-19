package estm.dsic.webby.Errors;

import jakarta.servlet.ServletException;

public class UserError extends ServletException {
    public UserError(String err) {
        super(err);
    }
}
