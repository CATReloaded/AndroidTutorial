package cat.cattutorial.observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    // Value -> Change -> observers
    // Observers
    // setValue -> change data in the observable -> call observers with the new data
    // Click -> Change in value -> Activity

    ArrayList<Observer> observers = new ArrayList<>();
    List<Product> data;

    public void observe(Observer observer) {
        observers.add(observer);
        observer.onNext(data);
    }

    public void setValue(List<Product> products) {
        Log.d("asd", "arrived here, list size : " + observers.size());
        data = products;
        for (Observer observer : observers) {
            observer.onNext(data);
        }
    }
}

interface Observer {
    void onNext(List<Product> product);
}
