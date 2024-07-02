import java.sql.*;


public class UpdatePlainJdbcExample {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "root";
    static final String PW = "0000";
    static final String QUERY = "UPDATE students SET name=?, age=?, address=? WHERE id=?";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PW);
             PreparedStatement ps = conn.prepareStatement(QUERY)) {
            ps.setString(1, "장이수2");
            ps.setInt(2, 41);
            ps.setString(3, "제주도");
            ps.setInt(4, 3);

            int rowNum = ps.executeUpdate();
            System.out.println("rowNUm = " + rowNum);


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
    }
}
