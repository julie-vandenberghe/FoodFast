import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.example.FoodFastUtils;

public class FoodFastUtilsTest {
    @Test
    void fizzBuzzTest() {
        // 1) définir n
        var n3 = 3;
        // 2) appeler deliveryPlanner avec n
        String resultat3 = FoodFastUtils.deliveryPlanner(n3);
        // 3) assertEquals("Fizz", résultat)
        assertEquals("Fizz", resultat3);

        // 1) définir n
        var n5 = 5;
        // 2) appeler deliveryPlanner avec n
        String resultat5 = FoodFastUtils.deliveryPlanner(n5);
        // 3) assertEquals("Fizz", résultat)
        assertEquals("Buzz", resultat5);
    }

    @Test
    void isLeapYearTest() {
        assertTrue(FoodFastUtils.isLeapYear(2000));
        assertTrue(FoodFastUtils.isLeapYear(2024));
        assertFalse(FoodFastUtils.isLeapYear(2025));
    }

}
