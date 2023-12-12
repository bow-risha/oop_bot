package domain;

import java.util.UUID;

public class EntityBase {
    private UUID id;
    public EntityBase(){
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
