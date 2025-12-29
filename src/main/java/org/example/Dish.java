package org.example;

import java.math.BigDecimal;
import java.util.Objects;

public class Dish {
    private String name;
    private BigDecimal price;
    private DishSize size;

    public Dish(String name, BigDecimal price, DishSize size){
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price.toString();
    }

    public String getSize() {
        return size.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(name, dish.name) && Objects.equals(price, dish.price) && size == dish.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, size);
    }

    public String toString() {
        return getName() + " (" + getSize() + ") : " + getPrice();
    }
}
