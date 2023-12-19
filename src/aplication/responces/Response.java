package aplication.responces;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private String text;
    private List<Key> keys;

    public Response(String text) {
        this.text = text;
        keys = new ArrayList<>();
    }

    public List<Key> getKeys() {
        return keys;
    }
    public void setKeys(List<Key> keys) {
        this.keys=keys;
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
