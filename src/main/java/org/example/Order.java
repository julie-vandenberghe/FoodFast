package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private String id;
    private OrderStatus orderStatus;
    private Map<Dish, Integer> dishes;
    private Customer customer;
    private LocalDateTime orderDate;

    public Order(Map<Dish, Integer> dishes, Customer customer) {
        this.id = UUID.randomUUID().toString();
        this.orderStatus = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
        this.dishes = dishes;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public Map<Dish, Integer> getDishes() {
        return dishes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = new BigDecimal("0");
        for (Map.Entry<Dish,Integer> entry : dishes.entrySet()) { // Parcours des paires clé-valeur
            BigDecimal prix = entry.getKey().getPrice();
            BigDecimal quantite = BigDecimal.valueOf(entry.getValue());
            BigDecimal sousTotal = prix.multiply(quantite);
            total = total.add(sousTotal);
        }
        return total;
    }
}
