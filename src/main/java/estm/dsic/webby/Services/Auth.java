package estm.dsic.webby.Services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import estm.dsic.webby.DAL.AuthDAO;
import estm.dsic.webby.Errors.AuthError;
import estm.dsic.webby.Errors.SignError;
import estm.dsic.webby.Models.Account;
import estm.dsic.webby.utils.AccountHelper;

public class Auth {
    public static Account log_email_pass(String email, String pass) throws AuthError {
        if (email == "" || pass == "") {
            throw new AuthError("All fields must filled");
        }

        if (!AccountHelper.verify_email(email)) {
            throw new AuthError("Invalid email: "+email);
        }

        Account acc;
        try {
            acc = AuthDAO.auth(email);
        } catch (SQLException e) {
            System.err.println("Error contacting database: "+e);
            throw new AuthError("Service unavailable. Try later.");
        }

        if (acc == null || !AccountHelper.verify_pass(pass, acc.passhash)) {
            throw new AuthError("Invalid user or password");
        }

        acc.passhash = null;
        
        return acc;
    }

    public static void signup_email_pass(String email, String pass, String pass1) throws SignError {
        if (email == "" || pass == "" || pass1 == "") {
            throw new SignError("All fields must filled");
        }

        if (!AccountHelper.verify_email(email)) {
            throw new SignError("Invalid email: "+email);
        }

        if (pass.length() < 8) {
            throw new SignError("Password must have at least 8 characters");
        }

        if (!pass.equals(pass1)) {
            throw new SignError("Password and verification not matching");
        }

        String passhash = AccountHelper.hash_pass(pass);

        try {
            if (!AuthDAO.signup(email, passhash)) {
                throw new SignError("Sign up failed. Try later.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SignError("Email "+email+" already registred");
        } catch (SQLException e) {
            System.err.println("Error contacting database: "+e);
            throw new SignError("Service unavailable. Try later.");
        }

    }
}
