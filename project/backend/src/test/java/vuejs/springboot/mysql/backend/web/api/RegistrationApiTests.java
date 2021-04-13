package vuejs.springboot.mysql.backend.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import vuejs.springboot.mysql.backend.domain.application.service.UserService;
import vuejs.springboot.mysql.backend.global.error.EmailAddressExistsException;
import vuejs.springboot.mysql.backend.global.error.UsernameExistException;
import vuejs.springboot.mysql.backend.web.request.RegistrationPayload;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegistrationApi.class)
class RegistrationApiTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("빈 페이로드를 등록하면 실패하고 400을 반환해야합니다.")
    void register_blankPayload_shouldFailAndReturn400() throws Exception {

        mvc.perform(post("/api/registrations"))
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("존재하는 사용자 이름 등록이 실패하고 400을 반환해야합니다.")
    void register_existedUsername_shouldFailAndReturn400() throws Exception {

        RegistrationPayload payload = RegistrationPayload.builder()
                .username("rootroot")
                .emailAddress("root@gmail.com")
                .password("rootroot")
                .build();

        // userService.rgister(payload.toCommand()) 가 호출 될 때 Exception 을 발생시킨다
        doThrow(UsernameExistException.class)
                .when(userService)
                .register(payload.toCommand())
        ;

        mvc.perform(
                post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Username already exists"))
        ;
    }

    @Test
    @DisplayName("존재하는 사용자 이름 등록이 실패하고 400을 반환해야합니다.")
    void register_existedEmailAddress_shouldFailAndReturn400() throws Exception {

        RegistrationPayload payload = RegistrationPayload.builder()
                .username("rootroot")
                .emailAddress("root@gmail.com")
                .password("rootroot")
                .build();

        // userService.rgister(payload.toCommand()) 가 호출 될 때 Exception 을 발생시킨다
        doThrow(EmailAddressExistsException.class)
                .when(userService)
                .register(payload.toCommand())
        ;

        mvc.perform(
                post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Email address already exists"))
        ;
    }

    @Test
    @DisplayName("유효한 페이로드를 등록하고 201을 반환해야합니다.")
    void register_validPayload_shouldSucceedAndReturn201() throws Exception {
        RegistrationPayload payload = RegistrationPayload.builder()
                .username("rootroot")
                .emailAddress("root@gmail.com")
                .password("rootroot")
                .build();

        // register 메소드가끝나면 의도한대로 아무일도 일어나지 않는다
        // userService.register 메소드에 return 타입을 주면 실패하고 void 를 주면 성공한다
        doNothing().when(userService).register(payload.toCommand());

        mvc.perform(
                post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload)))
                .andExpect(status().is(200))
        ;
    }
}
