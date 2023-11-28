package aplication;

import java.util.UUID;

public class AddLinkCommand {
    public UUID WishID;
    public String Link;
    public AddLinkCommand(UUID wishID, String link){
        WishID = wishID;
        Link = link;
    }
}
