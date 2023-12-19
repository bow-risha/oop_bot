package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class WishRepository {

    HashMap<UUID, Wish> wishes = new HashMap<UUID, Wish>();
    public void addWish(Wish wish){
        wishes.put(wish.getId(),wish);
    }

    public Wish getWish(UUID wishId){
        return wishes.get(wishId);
    }

    public ArrayList<Wish> GetWishesByOwner(UUID ownerId){
        ArrayList<Wish> result = new ArrayList<Wish>();
        for (Wish wish: wishes.values()){
            if (ownerId == wish.getOwnerId()){
                result.add(wish);
            }
        }
        return result;
    }
    public ArrayList<Wish> GetWishesByCategory(UUID categoryId){
        ArrayList<Wish> result = new ArrayList<Wish>();
        for (Wish wish: wishes.values()){
            if (categoryId == wish.getCategoryId()){
                result.add(wish);
            }
        }
        return result;
    }

}
