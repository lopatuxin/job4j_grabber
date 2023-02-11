package ru.job4j.storage.model;

import java.time.LocalDate;

public class Food {
    private String name;
    private double price;
    private double discount;
    private final LocalDate expiryDate;
    private final LocalDate createDate;

    public Food(String name, double price, double discount, LocalDate expiryDate, LocalDate createDate) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (Double.compare(food.price, price) != 0) return false;
        if (Double.compare(food.discount, discount) != 0) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (expiryDate != null ? !expiryDate.equals(food.expiryDate) : food.expiryDate != null) return false;
        return createDate != null ? createDate.equals(food.createDate) : food.createDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
