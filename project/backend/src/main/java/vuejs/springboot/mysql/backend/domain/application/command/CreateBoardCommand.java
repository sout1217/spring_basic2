package vuejs.springboot.mysql.backend.domain.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vuejs.springboot.mysql.backend.domain.model.board.Board;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardCommand {

    private long accountId;
    private Long teamId;
    private String name;
    private String description;

    public Board toEntity() {
        return Board.builder()
                .userId(accountId)
                .name(name)
                .description(description)
                .teamId(teamId > 0 ? teamId : null)
                .build();
    }
}
