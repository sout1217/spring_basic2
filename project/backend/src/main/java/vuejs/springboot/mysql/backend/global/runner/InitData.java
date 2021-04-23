package vuejs.springboot.mysql.backend.global.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vuejs.springboot.mysql.backend.domain.application.common.security.PasswordEncrypt;
import vuejs.springboot.mysql.backend.domain.model.account.Account;
import vuejs.springboot.mysql.backend.domain.model.account.AccountRepository;

@Component
@RequiredArgsConstructor
@Profile("beta")
public class InitData implements ApplicationRunner {

    private final AccountRepository accountRepository;
    private final PasswordEncrypt passwordEncrypt;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        Account root = Account.builder()
                .username("root")
                .emailAddress("root@gmail.com")
                .password(passwordEncrypt.encrypt("rootroot"))
                .build();

        accountRepository.save(root);
    }
}
