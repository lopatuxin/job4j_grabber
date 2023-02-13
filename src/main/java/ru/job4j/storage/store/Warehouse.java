package ru.job4j.storage.store;

import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.SimplePercentCalculator;

public class Warehouse extends AbstractStore {
    private SimplePercentCalculator simplePercentCalculator;

    public Warehouse(SimplePercentCalculator simplePercentCalculator) {
        this.simplePercentCalculator = simplePercentCalculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        double percent = simplePercentCalculator.getPercent(food.getExpiryDate(), food.getCreateDate());
        boolean res = false;
        if (percent < 25 && percent >= 0) {
            res = true;
        }
        return res;
    }
}
