/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manajemen.kas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); 

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setNamaLengkap(rs.getString("namaLengkap"));
                    user.setEmail(rs.getString("email"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error saat otentikasi user: " + e.getMessage());
        }

        return user;
    }
}
