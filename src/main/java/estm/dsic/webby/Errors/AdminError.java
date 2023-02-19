package estm.dsic.webby.Errors;

import jakarta.servlet.ServletException;

public class AdminError extends ServletException {
    public AdminError(String err) {
        super(err);
    }
}
