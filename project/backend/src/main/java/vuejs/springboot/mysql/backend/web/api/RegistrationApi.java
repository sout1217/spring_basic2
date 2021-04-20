package vuejs.springboot.mysql.backend.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuejs.springboot.mysql.backend.domain.application.service.UserApplicationService;
import vuejs.springboot.mysql.backend.web.request.RegistrationPayload;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RegistrationApi {

    private final UserApplicationService userApplicationServiceImpl;

    @PostMapping("registrations")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationPayload payload) {

        String password = userApplicationServiceImpl.register(payload.toCommand());

        Map<String, String> map = new HashMap<>();;
        map.put("password", password);

        return ResponseEntity.ok(map);
    }
}
