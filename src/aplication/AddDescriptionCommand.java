package aplication;

import java.util.UUID;

public class AddDescriptionCommand {
    public String description;
    public UUID wishID;
    public AddDescriptionCommand(String description, UUID wishID){
        this.description = description;
        this.wishID = wishID;
    }
}
