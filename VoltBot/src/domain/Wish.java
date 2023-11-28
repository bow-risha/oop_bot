package domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


public class Wish extends EntityBase{
    public String Name;
    public String Description;
    public URL Link;
    public UUID CategoryId;
    public UUID OwnerId;

    public Wish (String name, User owner){
        Name = name;
        OwnerId = owner.ID;
    }

    public void setCategory(Category category) {
        CategoryId = category.ID;
    }

    public void setLink(String link)  {
        try {
            Link = new URL(link);
        } catch (MalformedURLException e) {
            return;
        }
    }
    public void setDescription(String description){
        Description = description;
    }

    public void print(){
        System.out.println(ID);
        System.out.println(Description);
        System.out.println(Name);
        System.out.println(Link);
        System.out.println(CategoryId);
        System.out.println(OwnerId);
    }
}

