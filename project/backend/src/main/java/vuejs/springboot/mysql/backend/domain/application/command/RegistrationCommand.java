package vuejs.springboot.mysql.backend.domain.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCommand {

    private String username;
    private String emailAddress;
    private String password;

}
