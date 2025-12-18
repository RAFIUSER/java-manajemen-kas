package manajemen.kas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import manajemen.kas.model.User;
import manajemen.kas.services.DBConnection;

/**
 *
 * @author ASPIRESS
 */
public class UserDAO {

    /**
     * @param username Input username dari form login.
     * @param password Input password dari form login.
     * @return Objek User jika login berhasil, atau null jika gagal.
     */
    public User authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setNamaLengkap(rs.getString("namaLengkap"));
                    user.setJabatan(rs.getString("jabatan"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error saat otentikasi user: " + e.getMessage());
        }

        return user;
    }

    /**
     * Menambahkan User baru
     */
    public boolean create(User user) {
        String sql = "INSERT INTO users (username, password, namaLengkap, jabatan) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNamaLengkap());
            stmt.setString(4, user.getJabatan());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Mendapatkan User berdasarkan ID
     */
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setNamaLengkap(rs.getString("namaLengkap"));
                    user.setJabatan(rs.getString("jabatan"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving user by ID: " + e.getMessage());
        }
        return user;
    }

    /**
     * Mengupdate data User
     */
    public boolean update(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, namaLengkap = ?, jabatan = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNamaLengkap());
            stmt.setString(4, user.getJabatan());
            stmt.setInt(5, user.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieve all user records
     */
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNamaLengkap(rs.getString("namaLengkap"));
                user.setUsername(rs.getString("username"));
                user.setJabatan(rs.getString("jabatan"));

                list.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving pengeluaran: " + e.getMessage());
        }

        return list;
    }

    /**
     * Delete an expense record by ID
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting pengeluaran: " + e.getMessage());
            return false;
        }
    }
}
