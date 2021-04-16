package vuejs.springboot.mysql.backend.domain.application.common.mail;

import lombok.*;

@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"to", "subject", "body"})
public class SimpleMessage implements Message {

    private final String to;
    private final String subject;
    private final String body;
    private final String from;

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getFrom() {
        return from;
    }
}
