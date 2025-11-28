package manajemen.kas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Model class for income (Pemasukan) transactions
 * @author ASPIRESS
 */
public class Pemasukan {
    private int id;
    private String namaTransaksi;
    private LocalDate tanggal;
    private BigDecimal nominalMasuk;
    private String keterangan;

    public Pemasukan() {
    }

    public Pemasukan(int id, String namaTransaksi, LocalDate tanggal, BigDecimal nominalMasuk, String keterangan) {
        this.id = id;
        this.namaTransaksi = namaTransaksi;
        this.tanggal = tanggal;
        this.nominalMasuk = nominalMasuk;
        this.keterangan = keterangan;
    }

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

    public BigDecimal getNominalMasuk() {
        return nominalMasuk;
    }

    public void setNominalMasuk(BigDecimal nominalMasuk) {
        this.nominalMasuk = nominalMasuk;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String toString() {
        return "Pemasukan{" +
                "id=" + id +
                ", namaTransaksi='" + namaTransaksi + '\'' +
                ", tanggal=" + tanggal +
                ", nominalMasuk=" + nominalMasuk +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}
