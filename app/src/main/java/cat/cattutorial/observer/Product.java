package cat.cattutorial.observer;

import java.io.Serializable;
import java.util.Random;

public class Product implements Serializable {
    private String name;
    private String img;
    private String description;
    private int id = new Random().nextInt();

    public Product(String name, String img, String description) {
        this.name = name;
        this.img = img;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
