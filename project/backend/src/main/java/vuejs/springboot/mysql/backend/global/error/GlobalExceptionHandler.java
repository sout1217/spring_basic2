package vuejs.springboot.mysql.backend.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<?> registrationException(RegistrationException e) {
      log.error(e.getMessage());

        String errorMessage = "Registration failed";
        if (e instanceof UsernameExistException) {
            errorMessage = "Username already exists";
        } else if (e instanceof EmailAddressExistsException) {
            errorMessage = "Email address already exists";
        }

        Map<String, String> map = new HashMap<>();
        map.put("message", errorMessage);

        return ResponseEntity.badRequest().body(map);
    }
}
