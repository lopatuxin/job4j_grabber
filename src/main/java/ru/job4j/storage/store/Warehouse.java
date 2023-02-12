package ru.job4j.storage.store;

import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.GetPercent;

public class Warehouse extends AbstractStore {
    private GetPercent getPercent;

    public Warehouse(GetPercent getPercent) {
        this.getPercent = getPercent;
    }

    @Override
    protected boolean isSuitable(Food food) {
        double percent = getPercent.getPercent(food.getExpiryDate(), food.getCreateDate());
        boolean res = false;
        if (percent < 25 && percent >= 0) {
            res = true;
        }
        return res;
    }
}
