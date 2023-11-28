package aplication;

import java.util.UUID;

public class AddDescriptionCommand {
    public String Description;
    public UUID WishID;
    public AddDescriptionCommand(String description, UUID wishID){
        Description = description;
        WishID = wishID;
    }
}
