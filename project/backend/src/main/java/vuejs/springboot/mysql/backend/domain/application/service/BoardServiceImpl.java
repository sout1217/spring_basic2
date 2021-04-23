package vuejs.springboot.mysql.backend.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vuejs.springboot.mysql.backend.domain.application.command.CreateBoardCommand;
import vuejs.springboot.mysql.backend.domain.model.board.Board;
import vuejs.springboot.mysql.backend.domain.model.board.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board create(CreateBoardCommand command) {
        return boardRepository.save(command.toEntity());
    }
}
