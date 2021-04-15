package vuejs.springboot.mysql.backend.global.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import vuejs.springboot.mysql.backend.domain.model.account.AccountRepository;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
