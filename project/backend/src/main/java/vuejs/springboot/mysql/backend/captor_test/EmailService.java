package vuejs.springboot.mysql.backend.captor_test;

public class EmailService {
    private DeliveryPlatform platform;
    private Something something;

    public EmailService(DeliveryPlatform platform, Something something) {
        this.platform = platform;
        this.something = something;
    }

    public void send(String to, String subject, String body, boolean html) {
        Format format = Format.TEXT_ONLY;
        if (html) {
            format = Format.HTML;
        }
        Email email = new Email(to, subject, body);
        email.setFormat(format);
        platform.deliver(email);
    }

}
