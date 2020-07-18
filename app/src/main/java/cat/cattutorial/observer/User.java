package cat.cattutorial.observer;

import java.util.ArrayList;

public class User {
    String name;
    String email;
    String number;
    ArrayList<Product> favorites;
    private Observable favoritesObservable = new Observable();

    private User(String name, String email, String number, ArrayList<Product> favorites) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.favorites = favorites;
    }

    private static User user;

    static User newInstance(String name, String email, String number, ArrayList<Product> favorites) {
        if (user == null) {
            user = new User(name, email, number, favorites);
        }
        return user;

    }

    public void setFavorites(ArrayList<Product> favorites) {
        this.favorites = favorites;
        favoritesObservable.setValue(favorites);
    }

    public Observable getFavoritesObservable() {
        return favoritesObservable;
    }
}
