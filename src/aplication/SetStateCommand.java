package aplication;

import java.util.UUID;

public class SetStateCommand {
    public Long userId;
    public String state;

    public SetStateCommand(Long userId, String state) {
        this.userId = userId;
        this.state = state;
    }
}
