package manajemen.kas.dao;

import manajemen.kas.model.Pemasukan;
import manajemen.kas.services.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Pemasukan (Income) transactions
 *
 * @author ASPIRESS
 */
public class PemasukanDAO {

    /**
     * Create a new income record
     * @return Boolean
     */
    public boolean create(Pemasukan pemasukan) {
        String sql = "INSERT INTO pemasukan (namaTransaksi, tanggal, nominalMasuk, keterangan) VALUES (?, ?, ?, ?)";

        try (var conn = DBConnection.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pemasukan.getNamaTransaksi());
            stmt.setDate(2, Date.valueOf(pemasukan.getTanggal()));
            stmt.setBigDecimal(3, pemasukan.getNominalMasuk());
            stmt.setString(4, pemasukan.getKeterangan());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error creating pemasukan: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieve all income records
     */
    public List<Pemasukan> getAll() {
        List<Pemasukan> list = new ArrayList<>();
        String sql = "SELECT * FROM pemasukan ORDER BY tanggal DESC, id DESC";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pemasukan pemasukan = new Pemasukan();
                pemasukan.setId(rs.getInt("id"));
                pemasukan.setNamaTransaksi(rs.getString("namaTransaksi"));
                pemasukan.setTanggal(rs.getDate("tanggal").toLocalDate());
                pemasukan.setNominalMasuk(rs.getBigDecimal("nominalMasuk"));
                pemasukan.setKeterangan(rs.getString("keterangan"));
                list.add(pemasukan);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving pemasukan: " + e.getMessage());
        }

        return list;
    }

    /**
     * Update an existing income record
     */
    public boolean update(Pemasukan pemasukan) {
        String sql = "UPDATE pemasukan SET namaTransaksi = ?, tanggal = ?, nominalMasuk = ?, keterangan = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pemasukan.getNamaTransaksi());
            stmt.setDate(2, Date.valueOf(pemasukan.getTanggal()));
            stmt.setBigDecimal(3, pemasukan.getNominalMasuk());
            stmt.setString(4, pemasukan.getKeterangan());
            stmt.setInt(5, pemasukan.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating pemasukan: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete an income record by ID
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM pemasukan WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting pemasukan: " + e.getMessage());
            return false;
        }
    }
}
