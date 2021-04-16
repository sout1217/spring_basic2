package vuejs.springboot.mysql.backend.domain.model.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vuejs.springboot.mysql.backend.domain.application.common.security.PasswordEncrypt;
import vuejs.springboot.mysql.backend.domain.model.account.domainService.RegistrationManagement;
import vuejs.springboot.mysql.backend.global.error.EmailAddressExistsException;
import vuejs.springboot.mysql.backend.global.error.UsernameExistsException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationManagementTests {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncrypt passwordEncrypt;

    @InjectMocks
    private RegistrationManagement registrationManagement;

    @Test
    @DisplayName("이미 존재하는 사용자 명을 갖는 사용자를 등록 시도할 때 실패해야 한다")
    void register_existedUsername_shouldFail() {
        String username = "existsUsername";
        String emailAddress = "root@gmail.com";
        String password = "rootroot";

        // null 이 아닌 account 를 반환하여 이미 존재한다고 알려줌
        when(accountRepository.findByUsername(username)).thenReturn(Optional.of(new Account()));

        Assertions.assertThrows(UsernameExistsException.class, () -> {
            registrationManagement.register(username, emailAddress, password);
        });
    }

    @Test
    @DisplayName("이미 존재하는 이메일을 갖는 사용자를 등록 시도할 때 실패해야 한다")
    void register_existedEmailAddress_shouldFail() {
        String username = "existsUsername";
        String emailAddress = "root@gmail.com";
        String password = "rootroot";

        // null 이 아닌 account 를 반환하여 이미 존재한다고 알려줌
        when(accountRepository.findByEmailAddress(emailAddress)).thenReturn(Optional.of(new Account()));

        Assertions.assertThrows(EmailAddressExistsException.class, () -> {
            registrationManagement.register(username, emailAddress, password);
        });
    }

    @Test
    @DisplayName("유효한 데이터를 갖는 사용자를 등록 시도할 때 성공하고 비밀번호가 암호화 되는지 확인하기")
    void register_uppercaseEmailAddress_shouldAndBecomeLowercase() {
        String username = "root";
        String emailAddress = "root@gmail.com";
        String password = "rootroot";

        registrationManagement.register(username, emailAddress, password);

        Account accountToSave = Account.builder()
                .username(username)
                .emailAddress(emailAddress.toLowerCase())
                .password(password)
                .build();

        // save 메소드가 실행되었는지 검증
        verify(accountRepository).save(accountToSave);
    }

    @Test
    @DisplayName("유효한 데이터 저장 성공")
    void register_newUser_shouldSuccess() {
        String username = "root";
        String emailAddress = "root@gmail.com";
        String password = "rootroot";
        String encryptPassword = "EncryptPassword";

        // repository save data
        Account newAccount = Account.builder()
                .username(username)
                .password(encryptPassword)
                .emailAddress(emailAddress)
                .build();

        // repository mock
        when(accountRepository.findByUsername(username)).thenReturn(Optional.ofNullable(null));
        when(accountRepository.findByEmailAddress(emailAddress)).thenReturn(Optional.ofNullable(null));

        // password encode mock
        when(passwordEncrypt.encrypt(password)).thenReturn(encryptPassword);

        // repository save mock
        when(accountRepository.save(Account.builder().username(username).emailAddress(emailAddress).password(encryptPassword).build())).thenReturn(newAccount);

        // test logic
        Account savedAccount = registrationManagement.register(username, emailAddress, password);
        InOrder inOrder = inOrder(accountRepository);
        inOrder.verify(accountRepository).findByUsername(username);
        inOrder.verify(accountRepository).findByEmailAddress(emailAddress);
        inOrder.verify(accountRepository).save(newAccount);

        // password verify
        verify(passwordEncrypt).encrypt(password);

        // then
        Assertions.assertEquals(encryptPassword, savedAccount.getPassword(), "저장된 계정의 비밀번호와 암호화된 비밀번호가 서로 같아야 한다");

    }
}
