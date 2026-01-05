# README - TP : FoodFast

## Partie 1 : Initialisation du projet et algorithmes de base (Séance 1)

### Question 1 : "Hello, FoodFast!"
Pour exécuter la classe Application avec des arguments : dans IntelliJ, cliquez sur Run > Edit Configurations.
Et dans le champ sous Build and run, mettre par exemple : Julie test 123
Ou alors, directement en ligne de commande depuis un terminal lancé à la racine du projet , taper `java -cp target/classes org.example.Application Julie test 123`.

### Question 2 : "Utilitaires de Logique Métier" (avec TDD)
`FoodFastUtils` contient des méthodes statiques pour la logique métier : FizzBuzz, calcul d'année bissextile, somme d'entiers et anonymisation de chaînes.

Chaque méthode a été développée selon l'approche TDD : les tests JUnit ont été écrits avant l'implémentation, définissant les cas d'entrée et les résultats attendus.

Exemple de structure d'un test :
1. Définir les données d'entrée
2. Appeler la méthode à tester
3. Vérifier le résultat attendu avec `assertEquals` ou `assertTrue` / `assertFalse`.

### Question 3 : "Manipulation de données"
- La méthode `static int sumUpTo(int n)` calcule la somme de tous les entiers de 1 à n.
- La méthode `static String anonymize(String text)` inverse la chaîne de caractères passée en paramètre pour anonymiser un ID.


## Partie 2 : Modélisation orientée objet (Séance 2)

### Question 4 : "Le Cœur du système : les objets métier"
- `Dish`, `Customer` et `Order` sont les objets principaux de FoodFast. Les attributs (par exemple name, price, size pour `Dish`) sont déclarés privés et les getters publics (par exemple getName(), getPrice(), getSize() pour `Dish`) permettent d’y accéder depuis l’extérieur (ce qui permet de protéger les données internes).
- `equals()` permet de comparer deux objets Dish sur leur contenu (nom, prix, taille) plutôt que sur leur référence mémoire. `hashCode()` permet de garantir que les objets égaux (`equals`) seront correctement gérés dans les collections basées sur des tables de hachage (HashMap, HashSet).
- `DishSize` et `OrderStatus` sont des enum pour les tailles de plats et les statuts de commande afin d’éviter les valeurs invalides.
- 
### Question 5 : "La plateforme de livraison"
La classe `DeliveryPlatform` est responsable de l’orchestration des commandes. Elle utilise une Map<String, Order> pour stocker toutes les commandes en cours, avec l’ID de la commande comme clé pour un accès rapide. Méthodes principales :
- `void placeOrder(Order order)` → ajoute une commande à la plateforme. Pour la gestion des erreurs, voir Question 7.
- `Optional<Order> findOrderById(String orderId)` → récupère une commande par son ID, renvoie Optional pour gérer le cas où aucune commande ne correspond à l’ID fourni (sans risque de NullPointerException).


## Partie 3 : Logique applicative et robustesse (Séance 3)

### Question 6 : "Recherche avancée de commandes"
Dans DeliveryPlatform, nous avons ajouté des méthodes pour rechercher facilement les commandes :
- `findOrdersByCustomer(Customer customer)` → Retourne la liste de toutes les commandes passées par un client donné. Utilise l’API Stream pour filtrer la Map des commandes et collecter les résultats dans une List<Order>.
- `findOrdersByStatus(OrderStatus status)` → Retourne la liste de toutes les commandes correspondant à un statut précis (PENDING, IN_PREPARATION, etc.). Même logique avec Stream + filtre + collect.

### Question 7 : "Gestion des erreurs de préparation"
`Restaurant.prepare(order)` peut lancer une exception `OrderPreparationException` (20% de chance).
Si l’exception est levée, son statut passe à CANCELLED et on affiche un message.







