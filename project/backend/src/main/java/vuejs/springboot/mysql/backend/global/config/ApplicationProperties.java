package vuejs.springboot.mysql.backend.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    @Email
    @NotBlank
    private String mailFrom;

}
