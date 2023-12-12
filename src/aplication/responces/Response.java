package aplication.responces;

import java.util.ArrayList;

public class Response {
    private String text;
    private ArrayList<Key> keys;

    public Response(String text) {
        this.text = text;
        keys = new ArrayList<>();
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public void addKey(Key key) {
        keys.add(key);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
