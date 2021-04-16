package vuejs.springboot.mysql.backend.infrastructure.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import vuejs.springboot.mysql.backend.domain.application.common.mail.Mailer;
import vuejs.springboot.mysql.backend.domain.application.common.mail.Message;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsyncMailer implements Mailer {

    private final JavaMailSender mailSender;

    @Async
    @Override
    public void send(Message message) {
        Assert.notNull(message,"message 는 반드시 있어야 한다");

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            if (StringUtils.hasText(message.getFrom())) {
                mailMessage.setFrom(message.getFrom());
            }
            if (StringUtils.hasText(message.getSubject())) {
                mailMessage.setSubject(message.getSubject());
            }
            if (StringUtils.hasText(message.getBody())) {
                mailMessage.setText(message.getBody());
            }
            if (StringUtils.hasText(message.getTo())) {
                mailMessage.setTo(message.getTo());
            }

            mailSender.send(mailMessage);

        } catch (MailException e) {
            log.error("메일 전송 실패", e);
        }
    }
}
