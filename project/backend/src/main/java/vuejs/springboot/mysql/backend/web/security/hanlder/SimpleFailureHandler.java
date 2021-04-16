package vuejs.springboot.mysql.backend.web.security.hanlder;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import vuejs.springboot.mysql.backend.global.utils.SimpleMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String failure;

        if (e instanceof BadCredentialsException) {
            failure = "Invalid credentials";
        } else if (e instanceof InsufficientAuthenticationException) {
            failure = "Invalid authentication request";
        } else {
            failure = "Authentication failure";
        }

        SimpleMapper.write(response.getWriter(), failure);
    }
}
