package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.printf("Hello, FoodFast!");

        DeliveryPlatform platform = new DeliveryPlatform();
        Dish d1 = new Dish("Pizza", new BigDecimal("10"), DishSize.SMALL);
        HashMap<Dish, Integer> dishes = new HashMap<>();
        dishes.put(d1, 1);
        Customer c1 = new Customer("1", "Julie", "5 rue Victor Hugo, Marquette-lez-Lille");
        Order order1 = new Order(dishes, c1);
        Order order2 = new Order(dishes, c1);
        Order order3 = new Order(dishes, c1);
        Order order4 = new Order(dishes, c1);
        Order order5 = new Order(dishes, c1);
        Order order6 = new Order(dishes, c1);
        Order order7 = new Order(dishes, c1);
        Order order8 = new Order(dishes, c1);
        Order order9 = new Order(dishes, c1);
        Order order10 = new Order(dishes, c1);
        List<Order> orders = List.of(order1, order2, order3, order4, order5, order6, order7, order8, order9, order10);

        ExecutorService executor = Executors.newFixedThreadPool(4); // 4 threads = 4 “restaurants” qui passent des cmd en même temps
        for (int i = 0; i < orders.size(); i++) {
            Order currentOrder = orders.get(i);
            executor.submit(() -> platform.placeOrder(currentOrder));
        }

        executor.shutdown(); // plus de nouvelles tâches acceptées
        executor.awaitTermination(5, TimeUnit.SECONDS);

    }
}