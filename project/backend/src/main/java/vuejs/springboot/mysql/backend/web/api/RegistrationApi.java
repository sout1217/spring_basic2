package vuejs.springboot.mysql.backend.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuejs.springboot.mysql.backend.domain.application.service.UserService;
import vuejs.springboot.mysql.backend.web.request.RegistrationPayload;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegistrationApi {

    private final UserService userService;

    @PostMapping("registrations")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationPayload payload) {

        userService.register(payload.toCommand());

        return ResponseEntity.ok("result");
    }
}
