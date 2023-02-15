package ru.job4j.storage.handler;

import ru.job4j.storage.model.Food;
import ru.job4j.storage.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public Store distribution(Food food) {
        Store store = null;
        for (Store s : stores) {
            if (s.add(food)) {
                store = s;
            }
        }
        return store;
    }

    public void restore() {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            foods.addAll(store.getFoods());
            store.clear();
        }
        for (Food food : foods) {
            distribution(food);
        }
    }

}
