package vuejs.springboot.mysql.backend.domain.application.common.mail;

public interface Mailer {

    void send(Message message);
}
