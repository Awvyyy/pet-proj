package main;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    public static String hash(String password){
        int logRounds = 10;
        String salt = BCrypt.gensalt(logRounds);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean verify(String password, String storedHash){
        if (storedHash == null || storedHash.isBlank()) return false;
        try {
            return (BCrypt.checkpw(password, storedHash));
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
}
