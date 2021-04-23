package vuejs.springboot.mysql.backend.web.request;

import lombok.Data;
import vuejs.springboot.mysql.backend.domain.application.command.CreateBoardCommand;
import vuejs.springboot.mysql.backend.domain.model.account.SimpleAccount;

@Data
public class CreateBoardPayload {

    private long teamId;
    private String name;
    private String description;

    public CreateBoardCommand toCommand(Long accountId) {

        return CreateBoardCommand.builder()
                .accountId(accountId)
                .teamId(teamId)
                .name(name)
                .description(description)
                .build();
    }

}
