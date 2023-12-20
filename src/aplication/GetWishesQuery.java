package aplication;

import domain.User;

import java.util.UUID;

public class GetWishesQuery {
    public UUID UserID;

    public GetWishesQuery (UUID userId){
        UserID=userId;
    }

}
