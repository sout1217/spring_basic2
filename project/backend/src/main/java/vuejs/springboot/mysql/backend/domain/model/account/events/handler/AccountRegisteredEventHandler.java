package vuejs.springboot.mysql.backend.domain.model.account.events.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import vuejs.springboot.mysql.backend.domain.model.account.events.event.AccountRegisteredEvent;

@Slf4j
@Component
public class AccountRegisteredEventHandler {

    @EventListener(AccountRegisteredEvent.class)
    public void handleEvent(AccountRegisteredEvent event) {
        log.debug("Handling `{}` registration event", event.getUser().getEmailAddress());


    }
}
