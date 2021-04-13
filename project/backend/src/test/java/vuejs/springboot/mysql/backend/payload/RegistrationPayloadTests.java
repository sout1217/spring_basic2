package vuejs.springboot.mysql.backend.payload;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vuejs.springboot.mysql.backend.web.request.RegistrationPayload;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class RegistrationPayloadTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();
    }

    @Test
    @DisplayName("성공")
    void validate_shouldSuccess() {
        RegistrationPayload payload = RegistrationPayload.builder()
                .emailAddress("root@gmail.com")
                .username("rootroot")
                .password("rootroot")
                .build();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);

        assertThat(violations.size()).isEqualTo(0);
    }


    @Test
    @DisplayName("요청이 빈 값인 경우 실패한다")
    void validate_blankPayload_shouldFail() {
        RegistrationPayload payload = RegistrationPayload.builder().build();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);

        assertThat(violations.size()).isEqualTo(3);

    }

    @Test
    @DisplayName("유효하지 않은 이메일로 페이로드 검증이 실패해야 함")
    void validate_payloadWithInvalidEmail_shouldFail() {
        RegistrationPayload payload = RegistrationPayload.builder()
                .emailAddress("root!@#!@")
                .username("rootroot")
                .password("rootroot")
                .build();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);

        violations.forEach(val -> {
            assertThat(val.getPropertyPath().toString()).isEqualTo("emailAddress"); // 필드 값

            log.info("invalid getInvalidValue -> {}\n", val.getInvalidValue());

            log.info("invalid getConstraintDescriptor getAnnotation -> {}\n", val.getConstraintDescriptor().getAnnotation());
            log.info("invalid annotation name -> {}\n", val.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());

            log.info("invalid getMessage -> {}\n", val.getMessage());
            log.info("invalid getMessageTemplate -> {}\n", val.getMessageTemplate());

            log.info("invalid getLeafBean -> {}\n", val.getLeafBean());
            log.info("invalid getRootBean -> {}\n", val.getRootBean());

            log.info(val.getRootBeanClass().getSimpleName());

            log.info(String.valueOf(val));
        });

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("100보다 긴 이메일 주소로 페이로드 유효성 검사 실패")
    void validate_payloadWithEmailAddressLongerThan100_shouldFail() {

        // string builder 는 synchronized 사용안함, 안정성이 좋지 않음, 속도 빠름
        // string buffer 는 synchronized 사용함, 안정성 좋음(멀티쓰레드환경에서), 속도는 builder 보다 느림

        StringBuilder str = new StringBuilder();;
        str.append("root@gmail.com");

        for (int i = 0; i < 87; i++) {
            str.append("m");
        }

        String emailAddress = str.toString();

        log.info("emailAddress len -> {}", emailAddress.length());

        RegistrationPayload payload = RegistrationPayload.builder()
                .emailAddress(emailAddress)
                .username("rootroot")
                .password("rootroot")
                .build();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);

        violations.forEach(val -> {
            log.info(val.getMessage());
        });

        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("사용자 이름이 2보다 짧은 페이로드 유효성 검사는 실패해야합니다.")
    void validate_payloadWithUsernameShorterThan2_shouldFail() {
        RegistrationPayload payload = RegistrationPayload.builder()
                .emailAddress("root@gmail.com")
                .username("r")
                .password("rootroot")
                .build();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);

        violations.forEach(val -> {
            log.info(val.getMessage());
        });

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("사용자 이름이 50보다 긴 페이로드 유효성 검사는 실패해야합니다.")
    void validate_payloadWithUsernameLongerThan50_shouldFail() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 51; i++) {
            str.append("r");
        }
        String username = str.toString();

        log.info("username len -> {}", username.length());

        RegistrationPayload payload = RegistrationPayload.builder()
                .emailAddress("root@gmail.com")
                .username(username)
                .password("rootroot")
                .build();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);

        violations.forEach(val -> {
            log.info(val.getMessage());
        });

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("암호가 6보다 짧은 페이로드 유효성 검사는 실패해야합니다.")
    void validate_payloadWithPasswordShorterThan6_shouldFail() {

        RegistrationPayload payload = RegistrationPayload.builder()
                .emailAddress("root@gmail.com")
                .username("root")
                .password("r12")
                .build();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);

        violations.forEach(val -> {
            log.info(val.getMessage());
        });

        assertThat(violations.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("30보다 긴 암호로 페이로드 유효성 검사는 실패해야합니다")
    void validate_payloadWithPasswordLongerThan30_shouldFail() {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < 31; i++) {
            str.append("r");
        }

        String password = str.toString();

        log.info("password len -> {}", str.length());

        RegistrationPayload payload = RegistrationPayload.builder()
                .emailAddress("root@gmail.com")
                .username("root")
                .password(password)
                .build();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);

        violations.forEach(val -> {
            log.info(val.getMessage());
        });

        assertThat(violations.size()).isEqualTo(1);
    }
}
