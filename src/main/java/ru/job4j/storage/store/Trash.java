package ru.job4j.storage.store;

import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.GetPercent;

public class Trash extends AbstractStore {
    private GetPercent getPercent;

    public Trash(GetPercent getPercent) {
        this.getPercent = getPercent;
    }

    @Override
    protected boolean isSuitable(Food food) {
        double percent = getPercent.getPercent(food.getExpiryDate(), food.getCreateDate());
        boolean res = false;
        if (percent == -1) {
            res = true;
        }
        return res;
    }
}
