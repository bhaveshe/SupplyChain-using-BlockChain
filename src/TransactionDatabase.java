import java.sql.*;

public class TransactionDatabase {

    // Save a transaction to the database
    public static void saveTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions (transaction_id, sender_id, receiver_id, product_id, timestamp, transaction_fee) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

           
            stmt.setString(1, transaction.getTransactionId());
            stmt.setString(2, transaction.getSender().getId()); 
            stmt.setString(3, transaction.getReceiver().getId()); 
            stmt.setString(4, transaction.getProduct().getProductId()); 
            stmt.setString(5, transaction.getTimestamp()); 
            stmt.setDouble(6, transaction.getTransactionFee()); 

           
            stmt.executeUpdate();
            System.out.println("Transaction saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            
            System.out.println("Error executing SQL: " + e.getSQLState() + " - " + e.getMessage());
        }
    }

    public static void getAllTransactions() {
        String query = "SELECT * FROM transactions";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String transactionId = rs.getString("transaction_id");
                String senderId = rs.getString("sender_id");
                String receiverId = rs.getString("receiver_id");
                String productId = rs.getString("product_id");
                String timestamp = rs.getString("timestamp");
                double transactionFee = rs.getDouble("transaction_fee");

               
                System.out.println("Transaction ID: " + transactionId);
                System.out.println("Sender: " + senderId);
                System.out.println("Receiver: " + receiverId);
                System.out.println("Product ID: " + productId);
                System.out.println("Timestamp: " + timestamp);
                System.out.println("Transaction Fee: " + transactionFee);
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving transactions: " + e.getSQLState() + " - " + e.getMessage());
        }
    }
}
