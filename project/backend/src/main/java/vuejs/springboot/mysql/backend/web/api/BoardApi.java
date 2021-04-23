package vuejs.springboot.mysql.backend.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuejs.springboot.mysql.backend.domain.application.service.BoardService;
import vuejs.springboot.mysql.backend.domain.model.account.SimpleAccount;
import vuejs.springboot.mysql.backend.domain.model.board.Board;
import vuejs.springboot.mysql.backend.web.request.CreateBoardPayload;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class BoardApi {

    private final BoardService boardService;


    @PostMapping("/boards")
    public ResponseEntity<?> create(@RequestBody CreateBoardPayload payload, @AuthenticationPrincipal SimpleAccount account) {

        log.info("payload = {}", payload);
        log.info("account = {}", account);

        Board newBoard = boardService.create(payload.toCommand(account.getId()));

        return ResponseEntity.ok(newBoard);
    }

}
