import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDatabase {
    public static void saveProduct(Product product) {
        String query = "INSERT INTO products (product_id, name, price) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getProductId());
            stmt.setString(2, product.getProductName());
            stmt.setDouble(3, product.getPrice());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
