package org.example;

public class Application {
    public static void main(String[] args) {
        System.out.println("Bienvenue chez FoodFast !");
        for (int i = 0; i<args.length; i++){
            System.out.println("Argument " + i + ": "+ args[i]);
        }
    }
}
