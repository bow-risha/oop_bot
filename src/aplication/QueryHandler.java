package aplication;

import aplication.responces.Key;
import aplication.responces.Response;
import aplication.utils.CallbacksProvider;
import aplication.utils.ViewProvider;
import domain.Wish;
import domain.WishRepository;

import java.util.ArrayList;
import java.util.List;


public class QueryHandler {
    public WishRepository WishRepository;
    public QueryHandler (WishRepository wishRepository){
        WishRepository = wishRepository;
    }
    public ArrayList<Response> handle(GetWishesQuery query) {
        ArrayList<Wish> result = WishRepository.GetWishesByOwner(query.UserID);

        ArrayList<Response> responses=new ArrayList<>();
        for (Wish wish:result) {
            Response response=new Response(ViewProvider.getWishView(wish));
            List<Key> keys=CallbacksProvider.getCallbacks(wish);
            response.setKeys(keys);
            responses.add(response);
        }
        return responses;
    }
}
