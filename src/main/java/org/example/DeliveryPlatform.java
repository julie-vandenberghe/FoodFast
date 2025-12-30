package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DeliveryPlatform {
    private Map<String, Order> orders = new HashMap<>();

    public DeliveryPlatform() {
    }

    public void placeOrder(Order order) {
        orders.put(order.getId(), order);
    };

    public Optional<Order> findOrderById(String orderId) {
        Order order = orders.get(orderId);
        return Optional.ofNullable(order); // Ici, si orders.get(orderId) renvoie null (pas de cmd avec l'id) alors Optional.ofNullable(order) créera un Optional vide
    }
}
