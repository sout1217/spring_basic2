package vuejs.springboot.mysql.backend.domain.application.common.mail;

public enum MailTemplate {

    REGISTER("welcome");

    private final String fileName;

    MailTemplate(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
