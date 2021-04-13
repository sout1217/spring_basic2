package vuejs.springboot.mysql.backend.domain.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vuejs.springboot.mysql.backend.domain.application.command.RegistrationCommand;
import vuejs.springboot.mysql.backend.global.error.RegistrationException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService  {

    public void register(RegistrationCommand registrationCommand) throws RegistrationException {

        System.out.println(registrationCommand);

    }
}
