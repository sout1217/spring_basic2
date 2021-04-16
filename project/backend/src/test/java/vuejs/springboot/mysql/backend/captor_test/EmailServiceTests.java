package vuejs.springboot.mysql.backend.captor_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

//@ExtendWith(MockitoExtension.class)
class EmailServiceTests {

//    @Mock
    private DeliveryPlatform deliveryPlatform;

//    @InjectMocks
    private EmailService emailService;

//    @Captor
    ArgumentCaptor<Email> emailArgumentCaptor;

    // beforeEach 방법
    @BeforeEach
    void setUp() {
        deliveryPlatform = mock(DeliveryPlatform.class);
        emailService = new EmailService(deliveryPlatform, new Something());
    }

    @Test
    public void whenDoesSupportHtml_expectHTMLEmailFormat() {
        String to = "info@baeldung.com";
        String subject = "Using ArgumentCaptor";
        String body = "Hey, let'use ArgumentCaptor";

        emailService.send(to, subject, body, true);

        emailArgumentCaptor =  ArgumentCaptor.forClass(Email.class);

        Mockito.verify(deliveryPlatform).deliver(emailArgumentCaptor.capture());
        Email value = emailArgumentCaptor.getValue();
        assertEquals(Format.NEW, value.getFormat());
    }
}
