package vuejs.springboot.mysql.backend.domain.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import vuejs.springboot.mysql.backend.domain.application.service.UserFindService;
import vuejs.springboot.mysql.backend.domain.model.account.Account;
import vuejs.springboot.mysql.backend.domain.model.account.AccountRepository;
import vuejs.springboot.mysql.backend.domain.model.account.SimpleAccount;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFindServiceImpl implements UserFindService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException(String.format("%s 를 찾을 수 없습니다", username));
        }

        Optional<Account> account;

        if (username.contains("@")) {
            account = accountRepository.findByEmailAddress(username);
        } else {
            account = accountRepository.findByUsername(username);
        }

        Account findAccount = account.orElseThrow(() -> new UsernameNotFoundException(String.format("%s 를 찾을 수 없습니다", username)));

        return SimpleAccount.create(findAccount);
    }
}
