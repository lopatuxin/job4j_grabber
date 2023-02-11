package ru.job4j.storage.store;

import ru.job4j.storage.model.Food;

import java.util.List;

public interface Store {
    void add(Food food);
    List<Food> getFoods();
}
