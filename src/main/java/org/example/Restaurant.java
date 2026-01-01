package org.example;

import java.util.Random;

public class Restaurant {

    public void prepare(Order order) {
        Random random = new Random();
        double chance = random.nextDouble(); // entre 0.0 et 1.0
        if (chance < 0.2) {
            throw new OrderPreparationException("La préparation a échoué !");
        } else {
            order.setStatus(OrderStatus.IN_PREPARATION);
        }
    };
}
