package vuejs.springboot.mysql.backend.infrastructure.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import vuejs.springboot.mysql.backend.domain.application.common.mail.Mailer;
import vuejs.springboot.mysql.backend.domain.application.common.mail.Message;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
            // hmtl 태그를 사용하여 전송하기 위해 이런 방식으로 진행하였음
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            if (StringUtils.hasText(message.getFrom())) {
                helper.setFrom(message.getFrom());
            }
            if (StringUtils.hasText(message.getSubject())) {
                helper.setSubject(message.getSubject());
            }
            if (StringUtils.hasText(message.getBody())) {
                helper.setText(message.getBody(), true);
            }
            if (StringUtils.hasText(message.getTo())) {
                helper.setTo(message.getTo());
            }

            mailSender.send(helper.getMimeMessage());

        } catch (MessagingException e) {
            log.error("메일 전송 실패", e);
        }
    }
}
