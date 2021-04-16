package vuejs.springboot.mysql.backend.domain.application.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimplePasswordEncrypt implements PasswordEncrypt {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
