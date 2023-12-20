package aplication;

import domain.User;
import domain.UserRepository;
import domain.Wish;
import domain.WishRepository;

import java.net.MalformedURLException;
import java.net.URL;

public class CommandHandler {
    private final WishRepository wishRepository;
    private final UserRepository userRepository;

    public CommandHandler(WishRepository wishRepository, UserRepository userRepository) {
        this.wishRepository = wishRepository;
        this.userRepository = userRepository;
    }

    public void handle(CreateWishCommand command) {
        Wish wish = new Wish(command.Name, command.Owner);
        wishRepository.addWish(wish);
    }

    public boolean handle(AddLinkCommand command) {
        Wish wish = wishRepository.getWish(command.wishID);
        try {
            URL link = new URL(command.link);
            wish.setLink(link);
            wishRepository.addWish(wish);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public void handle(AddDescriptionCommand command) {
        Wish wish = wishRepository.getWish(command.wishID);
        wish.setDescription(command.description);
        wishRepository.addWish(wish);
    }

    public void handle(SetStateCommand command) {
        User user = userRepository.getUser(command.userId);
        user.setUserState(command.state);
        userRepository.addUser(user);
    }
}
