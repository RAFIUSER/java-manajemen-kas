package manajemen.kas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Model class for expense (Pengeluaran) transactions
 *
 * @author ASPIRESS
 */
public class Pengeluaran {

    private int id;
    private String namaTransaksi;
    private LocalDate tanggal;
    private BigDecimal nominalKeluar;
    private String keterangan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaTransaksi() {
        return namaTransaksi;
    }

    public void setNamaTransaksi(String namaTransaksi) {
        this.namaTransaksi = namaTransaksi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getNominalKeluar() {
        return nominalKeluar;
    }

    public void setNominalKeluar(BigDecimal nominalKeluar) {
        this.nominalKeluar = nominalKeluar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String toString() {
        return "Pengeluaran{"
                + "id=" + id
                + ", namaTransaksi='" + namaTransaksi + '\''
                + ", tanggal=" + tanggal
                + ", nominalKeluar=" + nominalKeluar
                + ", keterangan='" + keterangan + '\''
                + '}';
    }

    public Pengeluaran() {
    }

    public Pengeluaran(int id, String namaTransaksi, LocalDate tanggal, BigDecimal nominalKeluar, String keterangan) {
        this.id = id;
        this.namaTransaksi = namaTransaksi;
        this.tanggal = tanggal;
        this.nominalKeluar = nominalKeluar;
        this.keterangan = keterangan;
    }

}
