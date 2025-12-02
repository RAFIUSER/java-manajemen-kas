package manajemen.kas.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.constants.StandardFonts;

import manajemen.kas.dao.PemasukanDAO;
import manajemen.kas.dao.PengeluaranDAO;
import manajemen.kas.model.Pemasukan;
import manajemen.kas.model.Pengeluaran;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Service Layer untuk pembuatan Laporan Keuangan dalam format PDF (Menggunakan
 * iText 7).
 *
 * @author ASPIRESS
 */
public class ReportService {

    private final PemasukanDAO pemasukanDAO = new PemasukanDAO();
    private final PengeluaranDAO pengeluaranDAO = new PengeluaranDAO();

    /**
     * Mengubah nilai BigDecimal menjadi format Rupiah Indonesia (Rp
     * 1.000.000,00).
     */
    private String formatRupiah(BigDecimal nominal) {
        if (nominal == null) {
            return "Rp 0,00";
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("Rp #,##0.00", symbols);

        if (nominal.compareTo(BigDecimal.ZERO) < 0) {
            return "Rp -" + formatter.format(nominal.abs());
        }
        return formatter.format(nominal);
    }

    /**
     * Membuat Laporan Keuangan dalam format PDF.
     *
     * * @param startDate Tanggal mulai laporan.
     * @param endDate Tanggal akhir laporan.
     * @param filePath Lokasi file output PDF.
     * @return true jika PDF berhasil dibuat, false jika gagal.
     */
    public boolean generatePdfReport(
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal saldoBersih,
            String filePath) {

        // Deklarasi Font
        PdfFont boldFont = null;

        try {
            // --- 1. MEMUAT FONT ---
            boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            PdfFont regularFont = PdfFontFactory.createFont(StandardFonts.HELVETICA); // Tambahkan font reguler

            // Ambil Detail Transaksi
            List<Pemasukan> detailPemasukan = pemasukanDAO.getDetailPemasukanByDate(startDate, endDate);
            List<Pengeluaran> detailPengeluaran = pengeluaranDAO.getDetailPengeluaranByDate(startDate, endDate);

            // --- 3. INISIALISASI PDF ---
            PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
            PdfDocument pdf = new PdfDocument(writer);
            // Margin 40 di semua sisi
            Document document = new Document(pdf);

            // 4. Judul dan Periode
            document.add(new Paragraph("Laporan Keuangan Kas").setFont(boldFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Periode: " + startDate.toString() + " s/d " + endDate.toString()).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            // 5. Ringkasan Saldo
            document.add(new Paragraph("Saldo Bersih Akhir: " + formatRupiah(saldoBersih))
                    .setFont(boldFont).setFontSize(14).setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("\n"));

            // 6. Detail Pemasukan
            document.add(new Paragraph("Detail Pemasukan:").setFont(boldFont));

            float[] columnWidths = {1, 3, 3, 4, 5};
            Table tablePemasukan = new Table(columnWidths);

            // Header Tabel Pemasukan
            tablePemasukan.addHeaderCell(new Paragraph("No.").setFont(boldFont));
            tablePemasukan.addHeaderCell(new Paragraph("Tanggal").setFont(boldFont));
            tablePemasukan.addHeaderCell(new Paragraph("Nama Transaksi").setFont(boldFont));
            tablePemasukan.addHeaderCell(new Paragraph("Nominal").setFont(boldFont).setTextAlignment(TextAlignment.RIGHT));
            tablePemasukan.addHeaderCell(new Paragraph("Keterangan").setFont(boldFont));

            // Isi Tabel Pemasukan
            int no = 1;
            for (Pemasukan p : detailPemasukan) {
                // Perbaikan: Bungkus String/Objek dengan Paragraph sebelum menerapkan style

                // Kolom No.
                tablePemasukan.addCell(new Paragraph(String.valueOf(no++)).setFont(regularFont));
                // Kolom Tanggal
                tablePemasukan.addCell(new Paragraph(p.getTanggal().toString()).setFont(regularFont));
                // Kolom Nama Transaksi
                tablePemasukan.addCell(new Paragraph(p.getNamaTransaksi()).setFont(regularFont));
                // Kolom Nominal (sudah ada Paragraph yang di-setTextAlignment)
                tablePemasukan.addCell(new Paragraph(formatRupiah(p.getNominalMasuk())).setFont(regularFont).setTextAlignment(TextAlignment.RIGHT));
                // Kolom Keterangan
                tablePemasukan.addCell(new Paragraph(p.getKeterangan()).setFont(regularFont));
            }
            document.add(tablePemasukan);

            // 7. Detail Pengeluaran
            document.add(new Paragraph("\n\n")); // Jeda dua baris
            document.add(new Paragraph("Detail Pengeluaran:").setFont(boldFont));

            Table tablePengeluaran = new Table(columnWidths);

            // Header Tabel Pengeluaran (Sama dengan Pemasukan)
            tablePengeluaran.addHeaderCell(new Paragraph("No.").setFont(boldFont));
            tablePengeluaran.addHeaderCell(new Paragraph("Tanggal").setFont(boldFont));
            tablePengeluaran.addHeaderCell(new Paragraph("Nama Transaksi").setFont(boldFont));
            tablePengeluaran.addHeaderCell(new Paragraph("Nominal").setFont(boldFont).setTextAlignment(TextAlignment.RIGHT));
            tablePengeluaran.addHeaderCell(new Paragraph("Keterangan").setFont(boldFont));

            // Isi Tabel Pengeluaran
            int noPengeluaran = 1;
            for (Pengeluaran p : detailPengeluaran) {
                // Perbaikan: Bungkus String/Objek dengan Paragraph sebelum menerapkan style

                // Kolom No.
                tablePengeluaran.addCell(new Paragraph(String.valueOf(noPengeluaran++)).setFont(regularFont));
                // Kolom Tanggal
                tablePengeluaran.addCell(new Paragraph(p.getTanggal().toString()).setFont(regularFont));
                // Kolom Nama Transaksi
                tablePengeluaran.addCell(new Paragraph(p.getNamaTransaksi()).setFont(regularFont));
                // Kolom Nominal
                tablePengeluaran.addCell(new Paragraph(formatRupiah(p.getNominalKeluar())).setFont(regularFont).setTextAlignment(TextAlignment.RIGHT));
                // Kolom Keterangan
                tablePengeluaran.addCell(new Paragraph(p.getKeterangan()).setFont(regularFont));
            }
            document.add(tablePengeluaran);

            // 8. Tutup Dokumen
            document.close();

            return true;

        } catch (FileNotFoundException e) {
            System.err.println("Gagal membuat file PDF. File sedang digunakan atau path salah: " + filePath + " | Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Gagal membuat file PDF: File sedang digunakan atau path salah.", "Error PDF", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IOException e) {
            System.err.println("Gagal memuat font PDF atau masalah I/O: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memuat font atau I/O.", "Error PDF", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            System.err.println("Error umum saat membuat PDF: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat pemrosesan PDF.", "Error PDF", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
