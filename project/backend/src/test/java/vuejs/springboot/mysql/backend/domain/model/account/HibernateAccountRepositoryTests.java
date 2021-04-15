package vuejs.springboot.mysql.backend.domain.model.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import vuejs.springboot.mysql.backend.infrastructure.repository.HibernateAccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.assertThat;

//@ActiveProfiles("test")
@DataJpaTest // h2 인메모리 방식을 사용한다
class HibernateAccountRepositoryTests {

    @TestConfiguration
    public static class Config {
        @Bean
        public HibernateAccountRepository hibernateAccountRepository(EntityManager entityManager) {
            return new HibernateAccountRepository(entityManager);
        }
    }

    @Autowired
    private HibernateAccountRepository hibernateAccountRepository;

    @Test
    @DisplayName("username 이 null 인 경우 저장할 수 없다")
    void save_nullUsernameUser_shouldFail() {

        Account account = Account.builder()
                .username(null)
                .emailAddress("root@gmail.com")
                .password("root")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> {
            hibernateAccountRepository.save(account);
        });

    }

    @Test
    @DisplayName("emailAddress 가 null 인 경우 저장할 수 없다")
    void save_nullEmailAddressUser_shouldFail() {
        Account account = Account.builder()
                .username("root")
                .emailAddress(null)
                .password("root")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> {
            hibernateAccountRepository.save(account);
        });

    }

    @Test
    @DisplayName("password 가 null 인 경우 저장할 수 없다")
    void save_nullPasswordUser_shouldFail() {
        Account account = Account.builder()
                .username("root")
                .emailAddress("root@gmail.com")
                .password(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> {
            hibernateAccountRepository.save(account);
        });

    }

    @Test
    @DisplayName("유효한 account 는 저장할 수 있다")
    void save_validUser_shouldSuccess() {
        Account account = Account.builder()
                .username("root")
                .emailAddress("root@gmail.com")
                .password("root")
                .build();

        hibernateAccountRepository.save(account);

        assertThat(account.getId()).isNotNull();
        assertThat(account.getCreatedAt()).isNotNull();

        assertThat(account.getUsername()).isEqualTo("root");
        assertThat(account.getEmailAddress()).isEqualTo("root@gmail.com");
        assertThat(account.getPassword()).isEqualTo("root");
        assertThat(account.getFirstName()).isEqualTo("");
        assertThat(account.getLastName()).isEqualTo("");

        System.out.println(account);
    }

    @Test
    @DisplayName("username 이 이미 존재하는 경우 저장할 수 없다")
    void save_usernameAlreadyExist_shouldFail() {
        String username = "root";

        Account alreadyExistAccount = Account.builder()
                .username(username)
                .emailAddress("root@gmail.com")
                .password("root")
                .build();

        hibernateAccountRepository.save(alreadyExistAccount);

        assertThat(alreadyExistAccount.getId()).isNotNull();
        assertThat(alreadyExistAccount.getCreatedAt()).isNotNull();

        Account newAccount = Account.builder()
                .username(username)
                .emailAddress("martin@gmail.com")
                .password("root")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> {
            hibernateAccountRepository.save(newAccount);
        });
    }

    @Test
    @DisplayName("emailAddress 이 이미 존재하는 경우 저장할 수 없다")
    void save_emailAddressAlreadyExist_shouldFail() {
        String emailAddress = "root@gmail.com";

        Account alreadyExistAccount = Account.builder()
                .username("root")
                .emailAddress(emailAddress)
                .password("root")
                .build();

        hibernateAccountRepository.save(alreadyExistAccount);

        assertThat(alreadyExistAccount.getId()).isNotNull();
        assertThat(alreadyExistAccount.getCreatedAt()).isNotNull();

        Account newAccount = Account.builder()
                .username("martin")
                .emailAddress(emailAddress)
                .password("root")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> {
            hibernateAccountRepository.save(newAccount);
        });
    }

    @Test
    @DisplayName("emailAddress 가 존재하지 않는 경우 null 을 리턴한다")
    void save_emailAddress_notExist_shouldReturnEmptyResult() {
        Account account = hibernateAccountRepository.findByEmailAddress("root@gmail.com").orElse(null);

        assertThat(account).isNull();
    }

    @Test
    @DisplayName("emailAddress 가 존재하는 경우 값을 리턴한다")
    void findByEmailAddress_exist_shouldReturnResult() {
        Account newAccount = Account.builder()
                .username("root")
                .emailAddress("root@gmail.com")
                .password("root")
                .build();
        hibernateAccountRepository.save(newAccount);

        Account account = hibernateAccountRepository.findByEmailAddress("root@gmail.com").get();
        assertThat(account).isNotNull();
    }

    @Test
    @DisplayName("username 이 존재하지 않는 경우 빈값을 리턴한다")
    void findByUsername_notExist_shouldReturnEmptyResult() {
        Account account = hibernateAccountRepository.findByUsername("root").orElse(null);
        assertThat(account).isNull();
    }

    @Test
    @DisplayName("username 이 존재하는 경우 값을 리턴한다")
    void findByUsername_exist_shouldReturnResult() {
        Account newAccount = Account.builder()
                .username("root")
                .emailAddress("root@gmail.com")
                .password("root")
                .build();
        hibernateAccountRepository.save(newAccount);

        Account account = hibernateAccountRepository.findByUsername("root").get();
        assertThat(account).isNotNull();
    }

}

