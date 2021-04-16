package vuejs.springboot.mysql.backend.domain.application.common.mail;

public interface Message {

    String getTo();

    String getSubject();

    String getBody();

    String getFrom();
}
