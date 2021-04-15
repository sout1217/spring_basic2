package vuejs.springboot.mysql.backend.domain.application.common.security;

import org.springframework.stereotype.Component;

@Component
public class SimplePasswordEncrypt implements PasswordEncrypt {

    @Override
    public String encrypt(String password) {
        // todo spring security password encode
        return "EncryptPassword";
    }
}
