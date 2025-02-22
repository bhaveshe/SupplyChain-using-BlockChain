import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParticipantDatabase {
    public static void saveParticipant(Participant participant) {
        String query = "INSERT INTO participants (id, name, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, participant.getId());
            stmt.setString(2, participant.getName());
            stmt.setString(3, participant.getRole());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
