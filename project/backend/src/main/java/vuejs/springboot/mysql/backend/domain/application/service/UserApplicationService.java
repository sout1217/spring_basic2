package vuejs.springboot.mysql.backend.domain.application.service;

import vuejs.springboot.mysql.backend.domain.application.command.RegistrationCommand;

public interface UserApplicationService {

    String register(RegistrationCommand command);
}
