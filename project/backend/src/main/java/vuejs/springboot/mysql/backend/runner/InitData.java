package vuejs.springboot.mysql.backend.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import vuejs.springboot.mysql.backend.domain.Account;
import vuejs.springboot.mysql.backend.domain.AccountRepository;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account martin = Account.builder()
                .name("martin")
                .build();

        accountRepository.save(martin);
    }
}
