package ru.job4j.storage.store;

import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.PercentCalculator;

public class Warehouse extends AbstractStore {
    private PercentCalculator calculator;

    public Warehouse(PercentCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        double percent = calculator.getPercent(food.getExpiryDate(), food.getCreateDate());
        boolean res = false;
        if (percent < 25 && percent >= 0) {
            res = true;
        }
        return res;
    }
}
