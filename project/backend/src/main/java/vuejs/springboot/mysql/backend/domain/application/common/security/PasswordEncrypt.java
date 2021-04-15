package vuejs.springboot.mysql.backend.domain.application.common.security;

public interface PasswordEncrypt {

    String encrypt(String password);
}
