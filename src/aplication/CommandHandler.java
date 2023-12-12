package aplication;

import domain.Wish;
import domain.WishRepository;

public class CommandHandler {
    public WishRepository wishRepository;
    public CommandHandler (WishRepository wishRepository){
        this.wishRepository = wishRepository;
    }
    public void handle(CreateWishCommand command){
        Wish wish = new Wish(command.Name, command.Owner);
        wishRepository.AddWish(wish);
    }
    public void handle(AddLinkCommand command){
        Wish wish = wishRepository.getWish(command.wishID);
        wish.setLink(command.link);
        wishRepository.AddWish(wish);
    }
    public void handle(AddDescriptionCommand command){
        Wish wish = wishRepository.getWish(command.wishID);
        wish.setDescription(command.description);
        wishRepository.AddWish(wish);
    }
}
