package domain;

import java.util.UUID;

public class Category extends EntityBase {
    private String name;
    private UUID ownerId;

    public Category(String name, User owner){
        this.name = name;
        ownerId = owner.getId();
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

