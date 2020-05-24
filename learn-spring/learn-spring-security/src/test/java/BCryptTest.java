import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptTest {

    @Test
    public void testBCrypt() {
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw);

        boolean checkpw = BCrypt.checkpw("123", hashpw);
        System.out.println(checkpw);
    }
}
