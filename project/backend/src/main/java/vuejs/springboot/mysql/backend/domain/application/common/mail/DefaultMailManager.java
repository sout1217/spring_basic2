package vuejs.springboot.mysql.backend.domain.application.common.mail;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class DefaultMailManager implements MailManager {

    private String mailFrom;
    private Mailer mailer;
    private MustacheResourceTemplateLoader mustacheResourceTemplateLoader;

    public DefaultMailManager(@Value("{app.mail-from}") String mailFrom, Mailer mailer, MustacheResourceTemplateLoader mustacheResourceTemplateLoader) {
        this.mailFrom = mailFrom;
        this.mailer = mailer;
        this.mustacheResourceTemplateLoader = mustacheResourceTemplateLoader;
    }

    @Override
    public void send(String emailAddress, String subject, String templateName, MessageVariable... variables) {
        Assert.hasText(emailAddress, "emailAddress 는 반드시 있어야 합니다");
        Assert.hasText(subject, "subject 는 반드시 있어야 합니다");
        Assert.hasText(templateName, "templateName 는 반드시 있어야 합니다");

        String messageBody = createMessageBody(templateName, variables);

        Message message = new SimpleMessage(emailAddress, subject, messageBody, mailFrom);

        mailer.send(message);
    }

    private String createMessageBody(String templateName, MessageVariable[] variables) {
        try {
            Reader reader = mustacheResourceTemplateLoader.getTemplate(templateName);
            Template compile = Mustache.compiler().compile(reader);

            Map<String, Object> map = new HashMap<>();

            if (variables != null) {
                for (MessageVariable variable : variables) {
                    map.put(variable.getKey(), variable.getValue());
                }
            }

            return compile.execute(map);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
