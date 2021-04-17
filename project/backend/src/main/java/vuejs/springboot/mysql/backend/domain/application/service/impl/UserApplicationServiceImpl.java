package vuejs.springboot.mysql.backend.domain.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vuejs.springboot.mysql.backend.domain.application.command.RegistrationCommand;
import vuejs.springboot.mysql.backend.domain.application.common.event.DomainEventPublisher;
import vuejs.springboot.mysql.backend.domain.application.common.mail.MailManager;
import vuejs.springboot.mysql.backend.domain.application.common.mail.MailTemplate;
import vuejs.springboot.mysql.backend.domain.application.common.mail.MessageVariable;
import vuejs.springboot.mysql.backend.domain.application.service.UserApplicationService;
import vuejs.springboot.mysql.backend.domain.model.account.Account;
import vuejs.springboot.mysql.backend.domain.model.account.domainService.RegistrationManagement;
import vuejs.springboot.mysql.backend.domain.model.account.events.event.AccountRegisteredEvent;
import vuejs.springboot.mysql.backend.global.error.RegistrationException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserApplicationServiceImpl implements UserApplicationService {

    private final RegistrationManagement registrationManagement;
    private final MailManager mailManager;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public String register(RegistrationCommand command) throws RegistrationException {
        Assert.notNull(command, "command 는 null 이 될 수 없다");

        System.out.println(command);

        Account newAccount = registrationManagement.register(command.getUsername(), command.getEmailAddress(), command.getPassword());

        // todo : 이메일 발송 방법 공부했던 곳에서 다시 살펴보기
        mailManager.send(command.getEmailAddress(), "subject", MailTemplate.REGISTER.getFileName(), MessageVariable.from("user", newAccount));

        domainEventPublisher.publish(new AccountRegisteredEvent(newAccount));

        // 비밀번호 확인을 위함
        return newAccount.getPassword();
    }
}