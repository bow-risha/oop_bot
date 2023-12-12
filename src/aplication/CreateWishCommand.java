package aplication;

import domain.User;

public class CreateWishCommand {
    public String Name;
    public User Owner;

    public CreateWishCommand (String name, User owner){
        Name = name;
        Owner = owner;
    }
}
