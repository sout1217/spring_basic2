package vuejs.springboot.mysql.backend.domain.model.account.domainService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import vuejs.springboot.mysql.backend.domain.application.common.security.PasswordEncrypt;
import vuejs.springboot.mysql.backend.domain.model.account.Account;
import vuejs.springboot.mysql.backend.domain.model.account.AccountRepository;
import vuejs.springboot.mysql.backend.global.error.EmailAddressExistsException;
import vuejs.springboot.mysql.backend.global.error.RegistrationException;
import vuejs.springboot.mysql.backend.global.error.UsernameExistsException;

@Component
@RequiredArgsConstructor
public class RegistrationManagement {

    private final AccountRepository accountRepository;

    // 암호화 기법은 여러 종류가 있기 때문에 인터페이스로 설정한 것 이다 (확장성을 고려하여)
    private final PasswordEncrypt passwordEncrypt;

    public Account register(String username, String emailAddress, String password) throws RegistrationException {
        Assert.hasText(username, "반드시 username 은 존재해야 한다");
        Assert.hasText(emailAddress, "반드시 emailAddress 는 존재해야 한다");
        Assert.hasText(password, "반드시 password 는 존재해야 한다");

        accountRepository.findByUsername(username).ifPresent(account -> {
            throw new UsernameExistsException();
        });

        accountRepository.findByEmailAddress(emailAddress).ifPresent(account -> {
            throw new EmailAddressExistsException();
        });

        String encryptPassword = passwordEncrypt.encrypt(password);

        final Account newAccount = Account.builder()
                .username(username)
                .emailAddress(emailAddress)
                .password(encryptPassword)
                .build();

        return accountRepository.save(newAccount);
    }
}
