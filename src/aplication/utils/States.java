package aplication.utils;

import java.util.UUID;

public class States {
    public static final String AddWish = "wish/add";
    public static final String Empty = null;
    public static final String AddWishDescription = "wish/des/";
    public static final String AddWishLink = "wish/li/";

    public static final String Setstate = "ss/";

    public static String addWishDescription(UUID wishId) {
        return Setstate + AddWishDescription + wishId.toString();
    }
    public static String addWishLink(UUID wishId) {
        return Setstate + AddWishLink + wishId.toString();
    }
}
