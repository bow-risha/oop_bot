package domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


public class Wish extends EntityBase {
    private String name;
    private String description;
    private URL link;
    private UUID categoryId;
    private UUID ownerId;

    public Wish(String name, User owner) {
        this.name = name;
        ownerId = owner.getId();
    }

    public void setCategory(Category category) {
        categoryId = category.getId();
    }

    public void setLink(URL link) {
      this.link=link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }
}

