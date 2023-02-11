package ru.job4j.storage.handler;

import ru.job4j.storage.model.Food;
import ru.job4j.storage.store.Store;
import ru.job4j.storage.tools.GetPercent;

import java.util.Map;

public class ControlQuality {
    private GetPercent getPercent;
    private Map<String, Store> storeMap;

    public ControlQuality(GetPercent getPercent, Map<String, Store> storeMap) {
        this.getPercent = getPercent;
        this.storeMap = storeMap;
    }

    public void distribution(Food food) {
        double percent = getPercent.getPercent(food.getExpiryDate(), food.getCreateDate());
        if (percent < 25 && percent >= 0) {
            storeMap.get("Warehouse").add(food);
        } else if (percent < 75 && percent > 25) {
            storeMap.get("Shop").add(food);
        } else if (percent > 75) {
            food.setPrice(food.getPrice() - food.getDiscount());
            storeMap.get("Shop").add(food);
        } else if (percent == -1.0){
            storeMap.get("Trash").add(food);
        }
    }

}
