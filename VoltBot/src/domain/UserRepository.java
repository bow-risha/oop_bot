package domain;

import java.util.HashMap;
import java.util.UUID;

public class UserRepository {
    HashMap<Long, User> users = new HashMap<Long, User>();
    public void AddUser(User user){
        users.put(user.ChatID, user);
    }
    public User GetUser(Long chatID){
        return users.getOrDefault(chatID,null);
    }
}
