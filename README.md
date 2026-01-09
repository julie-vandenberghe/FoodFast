# FoodFast - TP final Java

## À propos du projet

Application Java simulant la prise, la préparation et la persistance de commandes, réalisé dans le cadre du cours "Programmation par composant 1" du Master 1 CYBER. 


## Prérequis

- Java 21+
- Maven (dépendances : junit-jupiter pour les tests et postgresql pour la base de donnéees)
- Docker (pour la base PostgreSQL avec Docker Compose)
- Variables d'environnement pour la BDD (voir section "Base de données")

## Démarrage rapide
### Compilation
```bash
mvn clean package
```

### Exécution des tests
```bash
mvn test
```

### Démarrer la base de données PostgreSQL
```bash
docker compose up -d
```

### Lancement de l'application
- Point d'entrée principal : `Main`
```bash
java -cp target/classes org.example.Main
```

- Classe `Application` (démonstration avec arguments - question 1) :
```bash
java -cp target/classes org.example.Application [arg1] [arg2] [arg3]
```


## Roadmap

- [x] Question 1 : Hello, FoodFast!
- [x] Question 2 : Utilitaires de Logique Métier (avec TDD)
- [x] Question 3 : Manipulation de données
- [x] Question 4 : Le cœur du système : les objets métier
- [x] Question 5 : La plateforme de livraison
- [x] Question 6 : Recherche avancée de commandes
- [x] Question 7 : Gestion des erreurs de préparation
- [x] Question 8 : Montée en charge (concurrence)
- [x] Question 9 : Persistance en base de données (JDBC) 
- [x] Question 10 : Journalisation des événements (Bonus)


## Organisation du projet

Le projet est structuré comme suit :
```
FoodFast/
├── src/main/java/org/example/    # Code source principal
├── src/test/java/                # Tests unitaires
├── init/                         # Script d'initialisation BDD
├── .env.example                  # Exemple de fichier d'environnement
├── compose.yml                   # Docker Compose (PostgreSQL)
├── pom.xml                       # Configuration Maven
└── README.md                     # Ce fichier
```

### Composants principaux
| Classe | Rôle |
|--------|------|
| `FoodFastUtils` | Utilitaires métier (FizzBuzz, bissextile, anonymisation) |
| `Order` | Représente une commande (id, client, statut, timestamp) |
| `Dish` | Représente un plat (nom, prix, taille) |
| `Customer` | Représente un client (nom, email) |
| `DishSize` / `OrderStatus` | Énums pour valeurs contrôlées |
| `DeliveryPlatform` | Orchestre les commandes (placement, recherche, persistance JDBC) |
| `Restaurant` | Simule la préparation d'une commande (peut échouer) |
| `OrderPreparationException` | Exception personnalisée levée lors d'un échec de préparation |
| `Logger` | Journalisation (pattern Singleton) |


### Tests

- Projet réalisé selon l'approche TDD : les tests JUnit ont été écrits avant l'implémentation du code.
- Tests unitaires fournis : DishTest, FoodFastUtilsTest, DeliveryPlatformTest.
- Exemple de structure d'un test :
  1. Définir les données d'entrée
  2. Appeler la méthode à tester
  3. Vérifier le résultat attendu (avec `assertEquals` ou `assertTrue` / `assertFalse`)

Pour exécuter les tests :
```bash
mvn test
```

### Base de données
La base de données PostgreSQL est utilisée pour persister les commandes.
Les variables d'environnement suivantes doivent être définies pour la connexion JDBC :
- `DB_URL` : URL de connexion JDBC (ex: `jdbc:postgresql://localhost:5432/foodfast`)
- `DB_USER` : Nom d'utilisateur de la base
- `DB_PASSWORD` : Mot de passe de la base
Configuration Base de données

Un fichier .env.example est fourni. Le copier et remplir pour obtenir votre .env local.


### Docker Compose
J’ai utilisé Docker Compose pour lancer la base PostgreSQL facilement.
Il lance automatiquement un script SQL d'initialisation (init-bdd.sql).

Pour démarrer la base de données PostgreSQL avec Docker Compose :
```bash
docker compose up -d
```

Pour vérifier que le conteneur tourne :
```bash
docker ps
```

Pour se connecter à la base et exécuter des requêtes SQL :
```bash
docker exec -it foodfast-db psql -U VOTRE_UTILISATEUR -d VOTRE_BASE_DE_DONNEES
```

Exemple pour lister les commandes :
```sql
SELECT * FROM orders;
```

## Discussions
### Question 8 : Sécurité et TOCTOU (Time-of-check to Time-of-use)
Une *race condition* peut devenir une faille de sécurité de type **TOCTOU** lorsqu'un état vérifié (check) est modifié par un autre thread avant son utilisation (use). Par exemple, si un thread vérifie qu'une commande peut être modifiée (check) puis, avant de la modifier (use), un autre thread change l'état de cette commande, cela peut conduire à des actions non autorisées ou incohérentes. Pour éviter cela, on utilise utiliser des structures de données thread-safe comme `ConcurrentHashMap` et on synchronise les accès aux ressources partagées.

### Question 9 : Sécurité et injections SQL avec PreparedStatement
`PreparedStatement` prévient les **injections SQL** par rapport à une concaténation de `String`.
Exemple de ce qu’il ne faut pas faire : 
```sql
String sql = "INSERT INTO orders (customer_name) VALUES ('" + order.getCustomer().getName() + "')";
```

Si le nom contient des caractères spéciaux ou du code SQL, cela pourrait corrompre la base ou créer une faille. Avec `PreparedStatement`, tout est échappé automatiquement.

### Question 10 : Limites du pattern Singleton et alternatives modernes :
  - état global → difficile à tester
  - rigide → difficile à changer l’implémentation (ex. logger vers un fichier ou un serveur)
  - Alternatives modernes : Injection de dépendances (avec Spring par exemple) pour injecter un logger configurable et testable, Enum Singleton (thread-safe, sérialisation sûre)