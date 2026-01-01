package org.example;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeliveryPlatform {
    private Map<String, Order> orders = new HashMap<>();

    public DeliveryPlatform() {
    }

    public void placeOrder(Order order) {
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.prepare(order);
            orders.put(order.getId(), order);
        }
        catch (OrderPreparationException e) {
            order.setStatus(OrderStatus.CANCELLED);
        }
    };

    public Optional<Order> findOrderById(String orderId) {
        Order order = orders.get(orderId);
        return Optional.ofNullable(order); // Ici, si orders.get(orderId) renvoie null (pas de cmd avec l'id) alors Optional.ofNullable(order) créera un Optional vide
    }

    public List<Order> findOrdersByCustomer(Customer customer) {
        return orders.values().stream().filter(o -> o.getCustomer().equals(customer)).collect(Collectors.toList());
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orders.values().stream().filter(o -> o.getStatus().equals(status)).collect(Collectors.toList());
    }
}
