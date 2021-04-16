package vuejs.springboot.mysql.backend.domain.application.common.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePasswordEncryptTests {

    private PasswordEncrypt passwordEncrypt;

    SimplePasswordEncryptTests() {
        this.passwordEncrypt = new SimplePasswordEncrypt(new BCryptPasswordEncoder());
    }

    @Test
    void password_encode_test() {
        String password = "root";

        String encryptPassword = passwordEncrypt.encrypt(password);

        System.out.println(encryptPassword);

    }

}
