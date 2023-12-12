package aplication.utils;

import aplication.responces.Key;
import domain.Wish;

import java.util.ArrayList;
import java.util.List;

public class CallbacksProvider {
    public static List<Key> getCallbacks(Wish wish) {
        ArrayList<Key> callbackKeys = new ArrayList<>();
        Key key = new Key(States.addWishDescription(wish.getId()), "Добавить описание");
        callbackKeys.add(key);
        return callbackKeys;

    }
}
