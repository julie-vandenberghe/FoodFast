package org.example;

public class FoodFastUtils {
    public static String deliveryPlanner(int n) {
        if (n % 3 == 0 && n % 5 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        } else { // si n n’est pas multiple de 3 ou 5, on retourne la string de n
            return String.valueOf(n);
        }
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static int sumUpTo(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i; // raccourci pour result = result + i
        }
        return result;
    }

    public static String anonymize(String text) {
        String reversed = "";
        for (int i = text.length() - 1;  i >= 0; i--) { // boucle de la fin vers le début du texte
            reversed += text.charAt(i); // renvoie le caractère à l’indice i ds la chaîne
        }
        return reversed;

    }
}
