package vuejs.springboot.mysql.backend.web.request;

import lombok.*;
import vuejs.springboot.mysql.backend.domain.application.command.RegistrationCommand;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationPayload {

    @NotNull
    @Size(min = 2, max = 50, message = "사용자 이름은 2 ~ 50 자 사이 여야합니다.")
    private String username;

    @NotNull
    @Email(message = "이메일 주소가 유효하지 않습니다.")
    @Size(max = 100, message = "이메일 주소는 6 ~ 30 자 이하 여야합니다.")
    private String emailAddress;

    @NotNull
    @Size(min = 6, max = 30, message = "비밀번호는 6 ~ 30 자 사이 여야합니다.")
    private String password;

    public RegistrationCommand toCommand() {
        return RegistrationCommand.builder()
                    .username(username)
                    .emailAddress(emailAddress)
                    .password(password)
                .build()
                ;
    }
}
