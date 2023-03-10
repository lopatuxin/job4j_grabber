package ru.job4j.storage.store;

import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.PercentCalculator;

public class Shop extends AbstractStore {
    private PercentCalculator calculator;

    public Shop(PercentCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        double percent = calculator.getPercent(food.getExpiryDate(), food.getCreateDate());
        boolean res = false;
        if (percent < 75 && percent > 25) {
            res = true;
        } else if (percent >= 75) {
            priceChanger(food);
            res = true;
        }
        return res;
    }

    private void priceChanger(Food food) {
        food.setPrice(food.getPrice() - food.getDiscount());
    }
}
