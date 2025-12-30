import org.example.Dish;
import org.example.DishSize;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DishTest {

    @Test
    void getNameTest() {
        // 1) définir new Dish
        Dish d = new Dish("Pizza", new BigDecimal("12.50"), DishSize.SMALL);
        // 2) appeler getName sur d
        String result = d.getName();
        // 3) assertEquals("Pizza", résultat)
        assertEquals("Pizza", result);
    }

    @Test
    void equalsTest() {
        // 1) définir plusieurs Dish (2 pareils et 1 différent)
        Dish d1 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.SMALL);
        Dish d2 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.SMALL);
        Dish d3 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        // 2) assertTrue/False
        assertEquals(d1, d2);
        assertNotEquals(d1, d3);
    }

    @Test
    void hashCodeTest() {
        // 1) définir 2 Dish pareils
        Dish d1 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.SMALL);
        Dish d2 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.SMALL);
        // définir leur hashcode
        int h1 = d1.hashCode();
        int h2 = d2.hashCode();
        // 2) Vérifier qu'ils sont identiques
        assertEquals(h1, h2);
    }

    @Test
    void toStringTest() {
        Dish d = new Dish("Pizza", new BigDecimal("12.50"), DishSize.SMALL);
        String result = d.toString();
        assertTrue(result.contains("Pizza"));
        assertFalse(result.contains("Tomates"));
    }




}
