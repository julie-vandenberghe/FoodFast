package org.example;

public class Logger {

    // instance unique
    private static Logger instance = new Logger();

    // constructeur privé pour empêcher d'autres instances
    private Logger() {}

    // récupération de l'instance
    public static Logger getInstance() {
        return instance;
    }

    // méthode pour logguer un message
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}