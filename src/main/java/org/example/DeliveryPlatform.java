package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DeliveryPlatform {
    private Map<String, Order> orders = new ConcurrentHashMap<>();

    public DeliveryPlatform() {
    }

    public synchronized void placeOrder(Order order) {
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.prepare(order);
            orders.put(order.getId(), order);
            Logger.getInstance().log("Commande créée : " + order.getId()); // on log la création de cmd
        }
        catch (OrderPreparationException e) {
            order.setStatus(OrderStatus.CANCELLED);
            orders.put(order.getId(), order);
            Logger.getInstance().log("Commande annulée : " + e.getMessage() + " pour l'order " + order.getId());
        } 
        finally { 
            saveOrderToDB(order); // On sauvegarde en bdd dans tous les cas (même si annulée)
        }
    };

    public Optional<Order> findOrderById(String orderId) {
        Order order = orders.get(orderId);
        return Optional.ofNullable(order); // Ici, si orders.get(orderId) renvoie null (pas de cmd avec l'id) alors Optional.ofNullable(order) créera un Optional vide
    }

    public List<Order> findOrdersByCustomer(Customer customer) {
        return orders.values().stream().filter(o -> o.getCustomer().equals(customer)).collect(Collectors.toList());
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orders.values().stream().filter(o -> o.getStatus().equals(status)).collect(Collectors.toList());
    }

    public int getOrderCount() {
        return orders.size();
    }

    public void saveOrderToDB(Order order) {
        // 1) Définir l'URL, user, password
        String user = System.getenv("POSTGRES_USER");
        String password = System.getenv("POSTGRES_PASSWORD");
        String host = System.getenv("POSTGRES_HOST");
        String port = System.getenv("POSTGRES_PORT");
        String db = System.getenv("POSTGRES_DB");
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + db;


        // 2) SQL avec PreparedStatement
        String sql = "INSERT INTO orders (id, customer_name, status, order_date, total_price) VALUES (?, ?, ?, ?, ?)";

        // 3) try-with-resources pour Connection et PreparedStatement
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 4) Injecter les valeurs de manière sécurisée
            stmt.setString(1, order.getId());
            stmt.setString(2, order.getCustomer().getName());
            stmt.setString(3, order.getStatus().toString());
            stmt.setObject(4, order.getOrderDate());  // LocalDateTime peut être converti automatiquement selon le driver
            stmt.setBigDecimal(5, order.calculateTotalPrice());

            // 5) Exécuter la requête
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Gérer les erreurs SQL
            System.out.println("Erreur lors de la sauvegarde de la commande : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
