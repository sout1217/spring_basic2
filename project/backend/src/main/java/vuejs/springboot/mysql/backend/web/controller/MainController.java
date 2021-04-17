package vuejs.springboot.mysql.backend.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    // jar 파일로 빌드시 /register 로 요청하면 접근 할 수 없다
    // /register 를 허용하고 index (view) 파일로 이동시키면
    // 히스토리 모드에서 url 를 인식하여 register 로 rotuer 가 렌더링 해준다
    @GetMapping(value = {"/", "/login", "/register"})
    public String entry() {
        log.info("GET entry()");
        return "index";
    }

}
