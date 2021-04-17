package vuejs.springboot.mysql.backend.domain.application.common.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import vuejs.springboot.mysql.backend.domain.model.account.Account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DefaultMailManagerTests {

    private Mailer mailerMock;

    private DefaultMailManager defaultMailManager;

    ArgumentCaptor<Message> messageArgumentCaptor;

    private MustacheResourceTemplateLoader mustacheResourceTemplateLoader;
    private static final String PREFIX = "classpath:/mail-templates/";
    private static final String SUFFIX = ".mustache";

    @BeforeEach
    void setUp() {
        mailerMock = mock(Mailer.class);
        mustacheResourceTemplateLoader = new MustacheResourceTemplateLoader(PREFIX, SUFFIX);
        defaultMailManager = new DefaultMailManager("sout1217@gmail.com", mailerMock, mustacheResourceTemplateLoader);
    }

    @Test
    @DisplayName("이메일이 null 인 경우 실패해야 한다")
    void send_nullEmailAddress_shouldFail() {
        String to = null;
        String subject = "Test subject";
        String templateName = "welcome";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            defaultMailManager.send(to, subject, templateName);
        });

        assertThat(exception).hasMessageContaining("emailAddress");
    }

    @Test
    @DisplayName("이메일이 빈 값인 경우 실패해야하 한다")
    void send_emptyEmailAddress_shouldFail() {
        String to = "";
        String subject = "Test subject";
        String templateName = "welcome";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            defaultMailManager.send(to, subject, templateName);
        });

    }

    @Test
    @DisplayName("제목이 null 인 경우 실패해야 한다")
    void send_nullSubject_shouldFail() {
        String to = "sout1217@gmail.com";
        String subject = null;
        String templateName = "welcome";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            defaultMailManager.send(to, subject, templateName);
        });
    }

    @Test
    @DisplayName("제목이 빈 값이 인경우 실패해야 한다")
    void send_emptySubject_shouldFail() {
        String to = "sout1217@gmail.com";
        String subject = "";
        String templateName = "welcome";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            defaultMailManager.send(to, subject, templateName);
        });
    }

    @Test
    @DisplayName("템플릿명이 null 인 경우 실패해야 한다")
    void send_nullTemplateName_shouldFail() {
        String to = "sout1217@gmail.com";
        String subject = "Test subject";
        String templateName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            defaultMailManager.send(to, subject, templateName);
        });
    }

    @Test
    @DisplayName("템플릿명이 빈 값인 경우 실패해야 한다")
    void send_emptyTemplateName_shouldFail() {
        String to = "sout1217@gmail.com";
        String subject = "Test subject";
        String templateName = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            defaultMailManager.send(to, subject, templateName);
        });
    }

    @Test
    @DisplayName("유효한 파라미터들 인 경우 성공해야 한다")
    void send_validParameters_shouldSucceed() {
        String to = "sout1217@gmail.com";
        String subject = "Test subject";
        String templateName = "welcome";
        String messageVariableKey = "user";
        Account messageVariableValue = Account.builder()
                .username("root")
                .emailAddress("root@gmail.com")
                .build();

        defaultMailManager.send(to, subject, templateName, MessageVariable.from(messageVariableKey, messageVariableValue));

        messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);

        verify(mailerMock, times(1)).send(messageArgumentCaptor.capture());

        Message messageSent = messageArgumentCaptor.getValue();

        System.out.println(messageSent);

        assertThat(to).isEqualTo(messageSent.getTo());
        assertThat(subject).isEqualTo(messageSent.getSubject());
        assertThat("sout1217@gmail.com").isEqualTo(messageSent.getFrom());

        System.out.println(messageSent.getBody());
        System.out.println("---------------------------------------------");

        // username 바인딩 검증
        assertThat(messageSent.getBody()).contains(String.format("<li>Username: %s</li>", messageVariableValue.getUsername()));
    }
}
