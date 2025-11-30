package manajemen.kas.services;

/**
 *
 * @author ASPIRESS
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_manajemen-kas";
                String user = "root";
                String pass = "";

                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi Database Berhasil!");
            } catch (SQLException e) {
                System.out.println("Koneksi Gagal: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Koneksi Database Gagal: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return conn;
    }
}
