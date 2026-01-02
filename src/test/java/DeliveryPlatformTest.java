import org.example.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryPlatformTest {

    @Test
    // après avoir passé une order, la méthode findOrderById permet de la retrouver par son id
    void findOrderByIdTest() {
        // Définir new deliveryplatform + new order
        DeliveryPlatform platform = new DeliveryPlatform();
        Dish d1 = new Dish("Pizza", new BigDecimal("10"), DishSize.SMALL);
        Dish d2 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        HashMap<Dish, Integer> dishes = new HashMap<>();
        dishes.put(d1, 1);
        dishes.put(d2, 2);
        Customer c1 = new Customer("1", "Julie", "5 rue Victor Hugo, Marquette-lez-Lille");
        Order order = new Order(dishes, c1);
        platform.placeOrder(order);
        Optional<Order> resultId = platform.findOrderById(order.getId());
        assertTrue(resultId.isPresent()); // on vérifie que l'order existe

        Order foundOrder = resultId.get();
        assertEquals(order.getId(), foundOrder.getId()); // on vérifie que la cmd retrouvée est bien celle placée
    }

    @Test
    void findOrdersByCustomer() {
        DeliveryPlatform platform = new DeliveryPlatform();

        Dish d1 = new Dish("Pizza", new BigDecimal("10"), DishSize.SMALL);
        Dish d2 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);

        HashMap<Dish, Integer> dishes1 = new HashMap<>();
        dishes1.put(d1, 1);
        dishes1.put(d2, 2);
        Customer c1 = new Customer("1", "Julie", "5 rue Victor Hugo, Marquette-lez-Lille");
        Order order1 = new Order(dishes1, c1);


        HashMap<Dish, Integer> dishes2 = new HashMap<>();
        dishes2.put(d1, 1);
        Customer c2 = new Customer("1", "Paul", "1 rue de Béthune, Lille");
        Order order2 = new Order(dishes2, c2);

        platform.placeOrder(order1);
        platform.placeOrder(order2);

        List<Order> result = platform.findOrdersByCustomer(c1);
        assertEquals(1, result.size()); // on vérifie qu'on a bien 1 seule cmd pour le client 1 (c1)
        assertEquals("Julie", result.get(0).getCustomer().getName()); // on vérifie que le client de la commande est bien Julie

    }

    @Test
    void prepareOrderTest() {
        DeliveryPlatform platform = new DeliveryPlatform();
        Dish d1 = new Dish("Pizza", new BigDecimal("10"), DishSize.SMALL);
        HashMap<Dish, Integer> dishes = new HashMap<>();
        dishes.put(d1, 1);
        Customer c1 = new Customer("1", "Julie", "5 rue Victor Hugo, Marquette-lez-Lille");
        Order order = new Order(dishes, c1);
        platform.placeOrder(order);
        List authorizedStatus = List.of(OrderStatus.PENDING, OrderStatus.IN_PREPARATION, OrderStatus.COMPLETED, OrderStatus.CANCELLED);
        assertTrue(authorizedStatus.contains(order.getStatus()));
    }

}
