package aplication.utils;

import java.util.UUID;

public class States {
    public static final String AddWish = "wish/add";
    public static final String Empty = null;
    public static final String AddWishDescription = "wish/des/";
    public static final String Setstate = "ss/";

    public static String addWishDescription(UUID wishId) {
        return String.format("ss/%s%s", AddWishDescription, wishId.toString());
    }
}
