package vuejs.springboot.mysql.backend.domain.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vuejs.springboot.mysql.backend.domain.application.command.RegistrationCommand;
import vuejs.springboot.mysql.backend.domain.application.common.event.DomainEventPublisher;
import vuejs.springboot.mysql.backend.domain.application.common.mail.MailManager;
import vuejs.springboot.mysql.backend.domain.application.common.mail.MessageVariable;
import vuejs.springboot.mysql.backend.domain.model.account.Account;
import vuejs.springboot.mysql.backend.domain.model.account.domainService.RegistrationManagement;
import vuejs.springboot.mysql.backend.domain.model.account.events.event.AccountRegisteredEvent;
import vuejs.springboot.mysql.backend.global.error.EmailAddressExistsException;
import vuejs.springboot.mysql.backend.global.error.RegistrationException;
import vuejs.springboot.mysql.backend.global.error.UsernameExistsException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private MailManager mailManager;

    @Mock
    private DomainEventPublisher eventPublisher;

    @Mock
    private RegistrationManagement registrationManagement;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("register 메소드의 null 인자가 들어가는 경우 실패해야합니다.")
    void register_nullCommand_shouldFail() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.register(null);
        });
    }

    @Test
    @DisplayName("기존 사용자 이름 등록이 실패해야합니다.")
    public void register_existingUsername_shouldFail() throws RegistrationException {
        String username = "existing";
        String emailAddress = "sunny@taskagile.com";
        String password = "MyPassword!";

        doThrow(UsernameExistsException.class).when(registrationManagement)
                .register(username, emailAddress, password);

        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

        Assertions.assertThrows(RegistrationException.class, () -> {
            userService.register(command);
        });

    }

    @Test
    @DisplayName("기존 이메일 주소 등록이 실패해야합니다")
    public void register_existingEmailAddress_shouldFail() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "existing@taskagile.com";
        String password = "MyPassword!";
        doThrow(EmailAddressExistsException.class).when(registrationManagement)
                .register(username, emailAddress, password);

        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

        Assertions.assertThrows(RegistrationException.class, () -> {
            userService.register(command);
        });
    }

    @Test
    @DisplayName("유요한 커맨드 명령은 회원가입에 성공합니다")
    public void register_validCommand_shouldSucceed() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "sunny@taskagile.com";
        String password = "MyPassword!";

        // given
        Account newAccount = Account.builder()
                .username(username)
                .emailAddress(emailAddress)
                .password(password)
                .build();
        when(registrationManagement.register(username, emailAddress, password))
                .thenReturn(newAccount);

        // when
        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
        userService.register(command);

        // then
        verify(mailManager).send(
                emailAddress,
                "subject",
                "template",
                MessageVariable.from("user", newAccount)
        );
        verify(eventPublisher).publish(new AccountRegisteredEvent(newAccount));
    }
}
