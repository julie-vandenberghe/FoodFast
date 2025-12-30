import org.example.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;
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

}
