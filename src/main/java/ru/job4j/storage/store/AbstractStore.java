package ru.job4j.storage.store;

import ru.job4j.storage.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return this.foods;
    }
}
