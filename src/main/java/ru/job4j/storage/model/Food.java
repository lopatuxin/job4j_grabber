package ru.job4j.storage.model;

import java.util.Date;

public class Food {
    private String name;
    private float price;
    private float discount;
    private final Date expiryDate;
    private final Date createDate;

    public Food(String name, float price, float discount, Date expiryDate, Date createDate) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (Float.compare(food.price, price) != 0) return false;
        if (Float.compare(food.discount, discount) != 0) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (expiryDate != null ? !expiryDate.equals(food.expiryDate) : food.expiryDate != null) return false;
        return createDate != null ? createDate.equals(food.createDate) : food.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (discount != +0.0f ? Float.floatToIntBits(discount) : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
