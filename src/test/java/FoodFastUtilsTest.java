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

    @Test
    void sumUpToTest() {
        // 1) définir n
        var n3 = 3;
        // 2) appeler sumUpTo avec n
        int resultat3 = FoodFastUtils.sumUpTo(n3);
        // 3) assertEquals(6, résultat3)
        assertEquals(6, resultat3);

        // 1) définir n
        var n5 = 5;
        // 2) appeler sumUpTo avec n
        int resultat5 = FoodFastUtils.sumUpTo(n5);
        // 3) assertEquals(6, résultat3)
        assertEquals(15, resultat5);
    }

    @Test
    void anonymizeTest() {
        var name = "Julie";
        String result = FoodFastUtils.anonymize(name);
        assertEquals("eiluJ", result);

        var name2 = "12345";
        String result2 = FoodFastUtils.anonymize(name2);
        assertEquals("54321", result2);
    }

}
