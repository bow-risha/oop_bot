package domain;

import java.util.UUID;

public class EntityBase {
    public UUID ID;
    public EntityBase(){
        ID = UUID.randomUUID();
    }

}
