package aplication;

import domain.Wish;
import domain.WishRepository;

public class CommandHandler {
    public WishRepository WishRepository;
    public CommandHandler (WishRepository wishRepository){
        WishRepository = wishRepository;
    }
    public void handle(CreateWishCommand command){
        Wish wish = new Wish(command.Name, command.Owner);
        WishRepository.AddWish(wish);
    }
    public void handle(AddLinkCommand command){
        Wish wish = WishRepository.GetWish(command.WishID);
        wish.setLink(command.Link);
        WishRepository.AddWish(wish);
    }
    public void handle(AddDescriptionCommand command){
        Wish wish = WishRepository.GetWish(command.WishID);
        wish.setDescription(command.Description);
        WishRepository.AddWish(wish);
    }

}
