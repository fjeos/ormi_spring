import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeletePlainJdbcExample {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "root";
    static final String PW = "0000";
    static final String QUERY = "DELETE FROM students WHERE id=?";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PW);
             PreparedStatement ps = conn.prepareStatement(QUERY)) {
            ps.setInt(1, 3);

            int rowNum = ps.executeUpdate();
            System.out.println("rowNUm = " + rowNum);


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
    }
}
