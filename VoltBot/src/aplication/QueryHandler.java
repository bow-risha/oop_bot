package aplication;

import domain.Wish;
import domain.WishRepository;

import java.util.ArrayList;


public class QueryHandler {
    public WishRepository WishRepository;
    public QueryHandler (WishRepository wishRepository){
        WishRepository = wishRepository;
    }
    public ArrayList<Wish> handle(GetWishesQuery query) {
        ArrayList<Wish> result = WishRepository.GetWishesByOwner(query.UserID);
        return result;
    }
}
