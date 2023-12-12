package aplication;

import java.util.UUID;

public class AddLinkCommand {
    public UUID wishID;
    public String link;
    public AddLinkCommand(UUID wishID, String link){
        this.wishID = wishID;
        this.link = link;
    }
}

