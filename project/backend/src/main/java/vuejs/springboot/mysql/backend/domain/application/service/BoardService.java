package vuejs.springboot.mysql.backend.domain.application.service;

import vuejs.springboot.mysql.backend.domain.application.command.CreateBoardCommand;
import vuejs.springboot.mysql.backend.domain.model.board.Board;

public interface BoardService {

    Board create(CreateBoardCommand toCommand);
}
