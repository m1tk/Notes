package estm.dsic.webby.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class AccountHelper {
    public static String hash_pass(String pass) {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
        return encoder.encode(pass);
    }

    public static boolean verify_pass(String pass, String hash) {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);

        return encoder.matches(pass, hash);
    }

    public static boolean verify_email(String email) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }
}
