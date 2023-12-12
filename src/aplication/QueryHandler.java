package aplication;

import aplication.responces.Key;
import aplication.responces.Response;
import domain.Wish;
import domain.WishRepository;

import java.util.ArrayList;


public class QueryHandler {
    public WishRepository WishRepository;
    public QueryHandler (WishRepository wishRepository){
        WishRepository = wishRepository;
    }
    public ArrayList<Response> handle(GetWishesQuery query) {
        ArrayList<Wish> result = WishRepository.GetWishesByOwner(query.UserID);

        ArrayList<Response> responses=new ArrayList<>();
        for (Wish wish:result) {
            Response response=new Response(wish.getName());
            Key key =new Key("amazing 1","Добавить описание");
            response.addKey(key);
            responses.add(response);
        }
        return responses;
    }
}
