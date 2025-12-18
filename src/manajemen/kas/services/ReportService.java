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
import com.itextpdf.layout.properties.UnitValue;

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
     * @param startDate Tanggal mulai laporan.
     * @param endDate Tanggal akhir laporan.
     * @param totalPengeluaran Total Pemasukan
     * @param totalPemasukan Total Pengeluaran
     * @param filePath Lokasi file output PDF.
     * @return true jika PDF berhasil dibuat, false jika gagal.
     */
    public boolean generatePdfReport(
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal totalPengeluaran,
            BigDecimal totalPemasukan,
            String filePath
    ) {
        
        PdfFont boldFont = null;

        try {
            boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            PdfFont regularFont = PdfFontFactory.createFont(StandardFonts.HELVETICA); // Tambahkan font reguler

            List<Pemasukan> detailPemasukan = pemasukanDAO.getDetailPemasukanByDate(startDate, endDate);
            List<Pengeluaran> detailPengeluaran = pengeluaranDAO.getDetailPengeluaranByDate(startDate, endDate);

            PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf);

            document.add(new Paragraph("Laporan Keuangan Kas").setFont(boldFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Periode: " + startDate.toString() + " s/d " + endDate.toString()).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Rincian Pemasukan:").setFont(boldFont));

            float[] columnWidths = {10, 20, 20, 20, 30};
            Table tablePemasukan = new Table(UnitValue.createPercentArray(columnWidths));
            tablePemasukan.addHeaderCell(new Paragraph("No.").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));
            tablePemasukan.addHeaderCell(new Paragraph("Tanggal").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));
            tablePemasukan.addHeaderCell(new Paragraph("Nama Transaksi").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));
            tablePemasukan.addHeaderCell(new Paragraph("Nominal").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));
            tablePemasukan.addHeaderCell(new Paragraph("Keterangan").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));

            int no = 1;
            for (Pemasukan p : detailPemasukan) {
                // Kolom No.
                tablePemasukan.addCell(new Paragraph(String.valueOf(no++)).setFont(regularFont).setTextAlignment(TextAlignment.CENTER));
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
            
            document.add(new Paragraph("Total Pemasukan: " + formatRupiah(totalPemasukan))
                    .setFont(boldFont).setFontSize(14).setTextAlignment(TextAlignment.RIGHT));
            
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Rincian Pengeluaran:").setFont(boldFont));

            Table tablePengeluaran = new Table(UnitValue.createPercentArray(columnWidths));
            tablePengeluaran.addHeaderCell(new Paragraph("No.").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));
            tablePengeluaran.addHeaderCell(new Paragraph("Tanggal").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));
            tablePengeluaran.addHeaderCell(new Paragraph("Nama Transaksi").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));
            tablePengeluaran.addHeaderCell(new Paragraph("Nominal").setFont(boldFont).setTextAlignment(TextAlignment.RIGHT));
            tablePengeluaran.addHeaderCell(new Paragraph("Keterangan").setFont(boldFont).setTextAlignment(TextAlignment.CENTER));

            int noPengeluaran = 1;
            for (Pengeluaran p : detailPengeluaran) {
                // Kolom No.
                tablePengeluaran.addCell(new Paragraph(String.valueOf(noPengeluaran++)).setFont(regularFont).setTextAlignment(TextAlignment.CENTER));
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

            document.add(new Paragraph("Total Pengeluaran: " + formatRupiah(totalPengeluaran))
                    .setFont(boldFont).setFontSize(14).setTextAlignment(TextAlignment.RIGHT));

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
