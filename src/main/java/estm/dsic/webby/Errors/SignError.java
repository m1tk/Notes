package estm.dsic.webby.Errors;

import jakarta.servlet.ServletException;

public class SignError extends ServletException {
    public SignError(String err) {
        super(err);
    }
}
