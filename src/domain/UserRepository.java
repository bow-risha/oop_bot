package domain;

import java.util.HashMap;

public class UserRepository {
    HashMap<Long, User> users = new HashMap<Long, User>();
    public void addUser(User user){
        users.put(user.getChatID(), user);
    }
    public User getUser(Long chatID){
        return users.getOrDefault(chatID,null);
    }
}
