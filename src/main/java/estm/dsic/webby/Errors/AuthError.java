package estm.dsic.webby.Errors;

import jakarta.servlet.ServletException;

public class AuthError extends ServletException {
    public AuthError(String err) {
        super(err);
    }
}
