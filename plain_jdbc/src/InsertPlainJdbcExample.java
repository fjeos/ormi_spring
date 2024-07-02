import java.sql.*;

public class InsertPlainJdbcExample {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "root";
    static final String PW = "0000";
    static final String QUERY = "INSERT INTO students (name, age, address) VALUES (?, ?, ?)";

    public static void main(String[] args) {
        String insertName = "장이수";
        int insertAge = 40;
        String insertAddress = "서울특별시";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PW);
             PreparedStatement pstmt = conn.prepareStatement(QUERY)) {
            pstmt.setString(1, insertName);
            pstmt.setInt(2, insertAge);
            pstmt.setString(3, insertAddress);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + "row(s) updated.");

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
    }
}
