package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {    
        // 0. Init du logger
        Logger logger = Logger.getInstance();
        logger.log("=== Bienvenue chez FoodFast ! (mode démo) ===\n");

        // 1. Création de la plateforme et quelques clients/plats
        DeliveryPlatform platform = new DeliveryPlatform();
        
        Dish pizza = new Dish("Pizza Chèvre/Miel", new BigDecimal("9.90"), DishSize.SMALL);
        Dish burger = new Dish("Burger Maroilles", new BigDecimal("12.5"), DishSize.MEDIUM);
        Dish curry = new Dish("Curry Tikka Massala", new BigDecimal("14.00"), DishSize.LARGE);

        Customer c1 = new Customer("C001", "Julie Dupont", "5 rue Victor Hugo, Marquette-lez-Lille");
        Customer c2 = new Customer("C002", "Martin Lefevre", "10 avenue de la République, Lille");
        Customer c3 = new Customer("C003", "Sophie Martin", "15 boulevard Pasteur, Wambrechies");


        // 2. Création des commandes
        HashMap<Dish, Integer> dishes1 = new HashMap<>();
        dishes1.put(pizza, 2);
        dishes1.put(burger, 1);
        Order order1 = new Order(dishes1, c1);

        HashMap<Dish, Integer> dishes2 = new HashMap<>();
        dishes2.put(curry, 1);
        Order order2 = new Order(dishes2, c2);

        HashMap<Dish, Integer> dishes3 = new HashMap<>();
        dishes3.put(burger, 3);
        Order order3 = new Order(dishes3, c3);

        List<Order> orders = List.of(order1, order2, order3);

        // 3. Simulation de la concurrence (on place les commandes avec multiple threads)
        logger.log("📦 Placement des commandes (mode concurrence avec 2 threads) :");
        ExecutorService executor = Executors.newFixedThreadPool(2); // 2 threads = 2 “restaurants” qui passent des cmd en même temps
        for (int i = 0; i < orders.size(); i++) {
            Order currentOrder = orders.get(i);
            executor.submit(() -> platform.placeOrder(currentOrder));
            logger.log("  → Commande " + currentOrder.getId() + " placée par " + currentOrder.getCustomer().getName());
        }

        executor.shutdown(); // plus de nouvelles tâches acceptées
        executor.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
        logger.log("✅ Toutes les commandes ont été traitées.\n");

        // 4. Recherche par client
        logger.log("🔍 Recherche des commandes de Julie Dupont :");
        List<Order> julieOrders = platform.findOrdersByCustomer(c1);
        logger.log("  → Nombre de commandes trouvées : " + julieOrders.size() + "\n");

        // 5. Recherche par statut
        logger.log("🔍 Recherche des commandes en cours de préparation (IN_PREPARATION):");
        List<Order> pendingOrders = platform.findOrdersByStatus(OrderStatus.IN_PREPARATION);
        logger.log("  → Nombre de commandes trouvées : " + pendingOrders.size() + "\n");

        // 6. Recherche par ID
        logger.log("🔍 Recherche d'une commande spécifique (ID: " + order1.getId() + "):");
        var foundOrder = platform.findOrderById(order1.getId());
        if (foundOrder.isPresent()) {
            logger.log("  → Commande trouvée : " + foundOrder.get().getId() + " - Statut: " + foundOrder.get().getStatus() + "\n");
        }

        // 7. Test de la préparation de commandes (qui peut échouer)
        logger.log("👨‍🍳 Simulation de préparation des commandes :");
        Restaurant restaurant = new Restaurant();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            try {
                restaurant.prepare(order);
                logger.log("  ✅ Commande " + order.getId() + " préparée avec succès - Statut: " + order.getStatus());
            } catch (OrderPreparationException e) {
               logger.log("  ❌ Commande " + order.getId() + " échouée - Statut: " + order.getStatus() + " (Exception: " + e.getMessage() + ")");
            }
        }

        logger.log("=== À bientôt chez FoodFast ! (fin de démo) ===");


    }
}