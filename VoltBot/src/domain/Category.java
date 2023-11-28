package domain;

import java.util.UUID;

public class Category extends EntityBase {
    public String Name;
    public UUID OwnerId;

    public Category(String name, User owner){
        Name = name;
        OwnerId = owner.ID;
    }

}

