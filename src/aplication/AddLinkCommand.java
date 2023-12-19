package aplication;

import java.util.UUID;

public class AddLinkCommand {
    public UUID wishID;
    public String link;
    public AddLinkCommand( String link,UUID wishID){
        this.wishID = wishID;
        this.link = link;
    }
}

