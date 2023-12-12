package aplication.utils;

import domain.Wish;

public class ViewProvider {
    public static String getWishView(Wish wish) {
        var stb = new StringBuilder()
                .append(wish.getName())
                .append("\n")
                .append(wish.getDescription() == null ? "" : wish.getDescription());
        return stb.toString();
    }
}
