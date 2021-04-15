package vuejs.springboot.mysql.backend.domain.model.account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmailAddress(String emailAddress);

    Account save(Account account);
}
