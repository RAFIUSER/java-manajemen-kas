package manajemen.kas.dao;

import manajemen.kas.model.Pengeluaran;
import manajemen.kas.services.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Pengeluaran (Expense) transactions
 *
 * @author ASPIRESS
 */
public class PengeluaranDAO {

    /**
     * Create a new expense record
     */
    public boolean create(Pengeluaran pengeluaran) {
        String sql = "INSERT INTO pengeluaran (namaTransaksi, tanggal, nominalKeluar, keterangan) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pengeluaran.getNamaTransaksi());
            stmt.setDate(2, Date.valueOf(pengeluaran.getTanggal()));
            stmt.setBigDecimal(3, pengeluaran.getNominalKeluar());
            stmt.setString(4, pengeluaran.getKeterangan());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error creating pengeluaran: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieve all expense records
     */
    public List<Pengeluaran> getAll() {
        List<Pengeluaran> list = new ArrayList<>();
        String sql = "SELECT * FROM pengeluaran ORDER BY tanggal DESC, id DESC";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pengeluaran pengeluaran = new Pengeluaran();
                pengeluaran.setId(rs.getInt("id"));
                pengeluaran.setNamaTransaksi(rs.getString("namaTransaksi"));
                pengeluaran.setTanggal(rs.getDate("tanggal").toLocalDate());
                pengeluaran.setNominalKeluar(rs.getBigDecimal("nominalKeluar"));
                pengeluaran.setKeterangan(rs.getString("keterangan"));
                list.add(pengeluaran);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving pengeluaran: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Update an existing expense record
     */
    public boolean update(Pengeluaran pengeluaran) {
        String sql = "UPDATE pengeluaran SET namaTransaksi = ?, tanggal = ?, nominalKeluar = ?, keterangan = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pengeluaran.getNamaTransaksi());
            stmt.setDate(2, Date.valueOf(pengeluaran.getTanggal()));
            stmt.setBigDecimal(3, pengeluaran.getNominalKeluar());
            stmt.setString(4, pengeluaran.getKeterangan());
            stmt.setInt(5, pengeluaran.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating pengeluaran: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete an expense record by ID
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM pengeluaran WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting pengeluaran: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
