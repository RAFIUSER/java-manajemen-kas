package manajemen.kas.services;

/**
 *
 * @author ASPIRESS
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_manajemen-kas";
        String user = "root";
        String pass = "";

        Connection newConn = DriverManager.getConnection(url, user, pass);

        System.out.println("Koneksi dibuat.");

        return newConn;
    }
}
