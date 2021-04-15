package vuejs.springboot.mysql.backend.domain.model.account.domainService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vuejs.springboot.mysql.backend.domain.model.account.Account;
import vuejs.springboot.mysql.backend.domain.model.account.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> getAccounts() {
        return null;
    }
}
