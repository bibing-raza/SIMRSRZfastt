/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package rekammedis;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPenyakit;

/**
 *
 * @author dosen
 */
public final class DlgRekamPsikologisDewasa extends javax.swing.JDialog {
    private final DefaultTableModel tabMode1, tabMode2, tabMode3, tabMode4, 
            tabMode5, tabMode6, tabMode7, tabMode8, tabMode9, tabMode10, 
            tabMode11, tabMode12, tabMode13, tabMode14, tabMode15;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12, ps13, ps14, ps15;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rs12, rs13, rs14, rs15,
            rsc1, rsc2, rsc3, rsc4, rsc5, rsc6, rsc7, rsc8, rsc9, rsc10, rsc11, rsc12, rsc13;
    private DlgMasterKeluhanPsikologis keluhan = new DlgMasterKeluhanPsikologis(null, false);
    private DlgMasterRencanaTritmenPsikologi tritmen = new DlgMasterRencanaTritmenPsikologi(null, false);
    private DlgCariPenyakit penyakit = new DlgCariPenyakit(null, false);
    private DlgCariDokter petugas = new DlgCariDokter(null, false);
    private int i = 0, x = 0, cekRPD = 0, pilihan = 0, data = 0;
    private final Properties prop = new Properties();
    private String tglKedatangan = "", wktSimpan = "", jkel = "", dataMasalah = "", nilaiMasalah = "", dataTritmen = "";

    /* Creates new form DlgPerawatan
     *
     * @param parent
     * @param modal
     */
    public DlgRekamPsikologisDewasa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabMode1 = new DefaultTableModel(null, new Object[]{
            "norawat", "tglreg", "No. RM", "Tgl. Kedatangan", "Nama Pasien/Klien", "Jns. Kelamin", "Tempat & Tgl. Lahir",
            "Pekerjaan", "Pendidikan", "Status Perkawinan", "Alamat", "No. Hp/Telpn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbdatadiri.setModel(tabMode1);
        tbdatadiri.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbdatadiri.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbdatadiri.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(120);
            } else if (i == 10) {
                column.setPreferredWidth(350);
            } else if (i == 11) {
                column.setPreferredWidth(110);
            } 
        }
        tbdatadiri.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien/Klien", "Nama Anggota Keluarga", "Umur", "Jns. Kelamin", "Posisi", "Keterangan", "waktusimpan", "usia", "sttsUmur"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbAnggotaKel.setModel(tabMode2);
        tbAnggotaKel.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnggotaKel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbAnggotaKel.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbAnggotaKel.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien/Klien", "Nama Suami/Istri", "Umur", "Pendidikan", "Pekerjaan", "Agama", "Anak Ke",
            "Perkawinan Ke", "Keluhan", "Alamat", "No. HP", "Keterangan",
            "anakke", "darianak", "kawinke", "darikawin", "wktsimpan", "umur"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbIdentitasPas.setModel(tabMode3);
        tbIdentitasPas.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbIdentitasPas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbIdentitasPas.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(190);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(110);                
            } else if (i == 7) {
                column.setPreferredWidth(120);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbIdentitasPas.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null, new Object[]{
            "kode", "Keluhan Permasalahan", "Keterangan Lainnya", "wktsimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemMasalah.setModel(tabMode4);
        tbItemMasalah.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbItemMasalah.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbItemMasalah.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new Object[]{
            "norawat", "waktusimpan", "No. RM", "Nama Pasien/Klien", "Keluhan Permasalahan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbKeluhan.setModel(tabMode5);
        tbKeluhan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKeluhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbKeluhan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(450);
            } 
        }
        tbKeluhan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null, new Object[]{
            "norawat","No. RM", "Nama Pasien", "Permasalahan Saat Ini", "Alasan Yang Membuat Pasien Mencari Bantuan Kali Ini", 
            "Permasalahan Dinilai", "Alasan", "Harapan", "Perubahan Yang Diinginkan Dari Diri Sendiri", "Perubahan Yang Diinginkan Dari Keluarga"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPsikologiKlinis.setModel(tabMode6);
        tbPsikologiKlinis.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPsikologiKlinis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbPsikologiKlinis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(350);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(350);
            } else if (i == 7) {
                column.setPreferredWidth(350);
            } else if (i == 8) {
                column.setPreferredWidth(350);
            } else if (i == 9) {
                column.setPreferredWidth(350);
            } 
        }
        tbPsikologiKlinis.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode7 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Masa Kanak-kanak", "Masa Remaja", "Masa Dewasa",
            "Riwayat Penyakit Fisik / Psikologis", "Riwayat Pengobatan / Konsultasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbRiwayatHidup.setModel(tabMode7);
        tbRiwayatHidup.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayatHidup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbRiwayatHidup.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(350);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
                column.setPreferredWidth(350);
            } else if (i == 6) {
                column.setPreferredWidth(350);
            } else if (i == 7) {
                column.setPreferredWidth(350);
            }
        }
        tbRiwayatHidup.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode8 = new DefaultTableModel(null, new Object[]{
            "norawat","No. RM", "Nama Pasien", "Penampilan", "Ekspresi Wajah", "Perasaan/Suasana Hati", 
            "Tingkah Laku", "Fungsi Umum", "Fungsi Intelektual", "Pengalaman Kerja", "Lain-lain"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObservasi.setModel(tabMode8);
        tbObservasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObservasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbObservasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } else if (i == 6) {
                column.setPreferredWidth(300);
            } else if (i == 7) {
                column.setPreferredWidth(300);
            } else if (i == 8) {
                column.setPreferredWidth(300);
            } else if (i == 9) {
                column.setPreferredWidth(300);
            } else if (i == 10) {
                column.setPreferredWidth(300);
            } 
        }
        tbObservasi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode9 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Delusi / Waham", "Proses Pikiran", "Halusinasi",
            "Afek", "Insight", "Kesadaran", "Orientasi Waktu", "Orientasi Tempat", "Orientasi Ruang", 
            "Perhatian", "Penilaian", "Kontrol Terhadap Implus"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbKondisiPsiko.setModel(tabMode9);
        tbKondisiPsiko.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKondisiPsiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbKondisiPsiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } 
        }
        tbKondisiPsiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode10 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Rencana Tes", "Tes Psikologis", "Waktu", "Tester", "tglrencanates"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTesPsiko.setModel(tabMode10);
        tbTesPsiko.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTesPsiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbTesPsiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbTesPsiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode11 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Manifestasi Fungsi Psikologis"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbManifestasi.setModel(tabMode11);
        tbManifestasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbManifestasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbManifestasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(450);
            } 
        }
        tbManifestasi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode12 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Kesan Awal", "Diagnosis Utama (dx)", "Diagnosis Banding (dd)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDiagnosis.setModel(tabMode12);
        tbDiagnosis.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbDiagnosis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } 
        }
        tbDiagnosis.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode13 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Prognosis"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPrognosis.setModel(tabMode13);
        tbPrognosis.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPrognosis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPrognosis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(450);
            }
        }
        tbPrognosis.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode14 = new DefaultTableModel(null, new Object[]{
            "kdrencana", "Deskrpisi Rencana Tritmen", "Keterangan Lainnya", "waktusimpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemTritmen.setModel(tabMode14);
        tbItemTritmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemTritmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbItemTritmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(260);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbItemTritmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode15 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Rencana Tritmen", "Pertemuan Selanjutnya", "Catatan Tambahan", "waktusimpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTritmen.setModel(tabMode15);
        tbTritmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTritmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbTritmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(350);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
                column.setPreferredWidth(350);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbTritmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        keluhan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisDewasa")) {
                    if (keluhan.getTable().getSelectedRow() != -1) {
                        kdkeluhan.setText(keluhan.getTable().getValueAt(keluhan.getTable().getSelectedRow(), 0).toString());
                        nmkeluhan.setText(keluhan.getTable().getValueAt(keluhan.getTable().getSelectedRow(), 1).toString());
                        
                        if (kdkeluhan.getText().equals("KP0053")) {
                            masalahLain.setEnabled(true);
                            masalahLain.setText("");
                            masalahLain.requestFocus();
                        } else {
                            masalahLain.setEnabled(false);
                            masalahLain.setText("");
                        }
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        keluhan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisDewasa")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        keluhan.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        tritmen.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisDewasa")) {
                    if (tritmen.getTable().getSelectedRow() != -1) {
                        kdtritmen.setText(tritmen.getTable().getValueAt(tritmen.getTable().getSelectedRow(), 0).toString());
                        nmtritmen.setText(tritmen.getTable().getValueAt(tritmen.getTable().getSelectedRow(), 1).toString());
                        
                        if (kdtritmen.getText().equals("RT0025")) {
                            tritmenLain.setEnabled(true);
                            tritmenLain.setText("");
                            tritmenLain.requestFocus();
                        } else {
                            tritmenLain.setEnabled(false);
                            tritmenLain.setText("");
                        }
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        tritmen.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisDewasa")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        tritmen.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisDewasa")) {
                    if (penyakit.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            TDiagUtama.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString() + " - " + penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                            TDiagUtama.requestFocus();
                        } else if (pilihan == 2) {
                            TDiagBanding.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString() + " - " + penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                            TDiagBanding.requestFocus();
                        }
                    }                
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisDewasa")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penyakit.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });  
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisDewasa")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
//                        kdptg1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        TnmPsikolog.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TumurKel.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurKel));
        TumurPas.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurPas));
        TanakKe.setDocument(new batasInput((byte) 3).getOnlyAngka(TanakKe));
        TdariKe.setDocument(new batasInput((byte) 3).getOnlyAngka(TdariKe));
        TkawinKe.setDocument(new batasInput((byte) 3).getOnlyAngka(TkawinKe));
        TtahunKe.setDocument(new batasInput((byte) 3).getOnlyAngka(TtahunKe));
        TnoHpPas.setDocument(new batasInput((byte) 13).getKata(TnoHpPas));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TabPsikologis.getSelectedIndex() == 0) {
                        tampilDataDiri();
                    } 
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TabPsikologis.getSelectedIndex() == 0) {
                        tampilDataDiri();
                    } 
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TabPsikologis.getSelectedIndex() == 0) {
                        tampilDataDiri();
                    } 
                }
            });
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("rekammedis.DlgRekamPsikologisDewasa : " + e);
        }
        
        LoadHTML.setEditable(true);
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("table { font: 11px tahoma;}"
                + ".adaborder {"
                + "    width: 100%;"
                + "  border-collapse: collapse;"
                + "}"
                + ""
                + ".adaborder th{"
                + "  border-bottom: 1px solid #edf2e8;"
                + "  border-right: 1px solid #edf2e8;"
                + "  padding: 5px;"
                + "}"
                + ""
                + ".adaborder td{"
                + "  border-bottom: 1px solid #edf2e8;"
                + "  border-right: 1px solid #edf2e8;"
                + "  padding: 5px;"
                + "}"
                + ""
                + ".noborder th{"
                + "  border: 0px;"
                + "  padding: 5px;"
                + "}"
                + ""
                + ".noborder td{"
                + "  border: 0px;"
                + "  padding: 5px;"
                + "}"); 
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML.setEditable(false);
        
        LoadHTML.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        WindowDataKeluhan = new javax.swing.JDialog();
        internalFrame14 = new widget.InternalFrame();
        panelGlass7 = new widget.panelisi();
        nmkeluhan = new widget.TextBox();
        kdkeluhan = new widget.TextBox();
        jLabel55 = new widget.Label();
        masalahLain = new widget.TextBox();
        btnKeluhan = new widget.Button();
        jLabel59 = new widget.Label();
        panelGlass13 = new widget.panelisi();
        BtnSimpan2 = new widget.Button();
        BtnEdit1 = new widget.Button();
        BtnCloseIn3 = new widget.Button();
        WindowDataTritmen = new javax.swing.JDialog();
        internalFrame20 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        nmtritmen = new widget.TextBox();
        kdtritmen = new widget.TextBox();
        jLabel99 = new widget.Label();
        tritmenLain = new widget.TextBox();
        btnTritmen = new widget.Button();
        jLabel100 = new widget.Label();
        panelGlass17 = new widget.panelisi();
        BtnSimpan3 = new widget.Button();
        BtnEdit2 = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        TabPsikologis = new javax.swing.JTabbedPane();
        internalFrame10 = new widget.InternalFrame();
        panelGlass16 = new widget.panelisi();
        jLabel72 = new widget.Label();
        TPekerjaan = new widget.TextBox();
        jLabel73 = new widget.Label();
        TPendidikan = new widget.TextBox();
        jLabel74 = new widget.Label();
        TSttsKawin = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel71 = new widget.Label();
        Ttl = new widget.TextBox();
        Scroll7 = new widget.ScrollPane();
        TAlamat = new widget.TextArea();
        Scroll3 = new widget.ScrollPane();
        tbdatadiri = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbAnggotaKel = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel12 = new widget.Label();
        jLabel14 = new widget.Label();
        TposisiKel = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbJK = new widget.ComboBox();
        jLabel21 = new widget.Label();
        TketKel = new widget.TextBox();
        TumurKel = new widget.TextBox();
        jLabel15 = new widget.Label();
        TnmKel = new widget.TextBox();
        cmbUmur = new widget.ComboBox();
        internalFrame5 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbIdentitasPas = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel5 = new widget.Label();
        TumurPas = new widget.TextBox();
        jLabel7 = new widget.Label();
        jLabel9 = new widget.Label();
        TnmPas = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel10 = new widget.Label();
        TpekerjaanPas = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel20 = new widget.Label();
        cmbPnd = new widget.ComboBox();
        cmbAgama = new widget.ComboBox();
        TanakKe = new widget.TextBox();
        jLabel22 = new widget.Label();
        TdariKe = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        TkawinKe = new widget.TextBox();
        jLabel27 = new widget.Label();
        TtahunKe = new widget.TextBox();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        TnoHpPas = new widget.TextBox();
        Scroll8 = new widget.ScrollPane();
        TKeluhan = new widget.TextArea();
        jLabel54 = new widget.Label();
        Scroll9 = new widget.ScrollPane();
        TketPas = new widget.TextArea();
        jLabel56 = new widget.Label();
        Scroll11 = new widget.ScrollPane();
        TalamatPas = new widget.TextArea();
        jLabel57 = new widget.Label();
        internalFrame8 = new widget.InternalFrame();
        Scroll10 = new widget.ScrollPane();
        tbKeluhan = new widget.Table();
        panelGlass14 = new widget.panelisi();
        jLabel43 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        tbItemMasalah = new widget.Table();
        BtnPilihKeluhan = new widget.Button();
        BtnHapusItem = new widget.Button();
        BtnPerbaikiItem = new widget.Button();
        jLabel58 = new widget.Label();
        internalFrame9 = new widget.InternalFrame();
        Scroll15 = new widget.ScrollPane();
        tbPsikologiKlinis = new widget.Table();
        panelGlass15 = new widget.panelisi();
        Scroll37 = new widget.ScrollPane();
        FormInput3 = new widget.PanelBiasa();
        jLabel25 = new widget.Label();
        jLabel29 = new widget.Label();
        Scroll12 = new widget.ScrollPane();
        TPermasalahansaatIni = new widget.TextArea();
        Scroll13 = new widget.ScrollPane();
        TAlasanMencariBantuan = new widget.TextArea();
        Scroll14 = new widget.ScrollPane();
        TAlasan = new widget.TextArea();
        jLabel26 = new widget.Label();
        RSangatSerius = new widget.RadioButton();
        RSerius = new widget.RadioButton();
        RKurangSerius = new widget.RadioButton();
        jLabel28 = new widget.Label();
        Scroll19 = new widget.ScrollPane();
        THarapan = new widget.TextArea();
        jLabel31 = new widget.Label();
        Scroll20 = new widget.ScrollPane();
        TPerubahanDiriSendiri = new widget.TextArea();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        Scroll21 = new widget.ScrollPane();
        TPerubahanKeluarga = new widget.TextArea();
        jLabel34 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel60 = new widget.Label();
        internalFrame11 = new widget.InternalFrame();
        Scroll16 = new widget.ScrollPane();
        tbRiwayatHidup = new widget.Table();
        panelGlass26 = new widget.panelisi();
        Scroll39 = new widget.ScrollPane();
        FormInput5 = new widget.PanelBiasa();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        Scroll22 = new widget.ScrollPane();
        TMasaKanak = new widget.TextArea();
        Scroll23 = new widget.ScrollPane();
        TMasaRemaja = new widget.TextArea();
        Scroll24 = new widget.ScrollPane();
        TMasaDewasa = new widget.TextArea();
        jLabel63 = new widget.Label();
        Scroll25 = new widget.ScrollPane();
        TRiwayatFisik = new widget.TextArea();
        jLabel65 = new widget.Label();
        Scroll26 = new widget.ScrollPane();
        TRiwayatPengobatan = new widget.TextArea();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel75 = new widget.Label();
        internalFrame12 = new widget.InternalFrame();
        Scroll17 = new widget.ScrollPane();
        tbObservasi = new widget.Table();
        panelGlass27 = new widget.panelisi();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        jLabel78 = new widget.Label();
        TPenampilan = new widget.TextBox();
        TEkspresi = new widget.TextBox();
        TPerasaan = new widget.TextBox();
        TTingkahLaku = new widget.TextBox();
        TFungsiUmum = new widget.TextBox();
        TFungsiIntelek = new widget.TextBox();
        TPengalaman = new widget.TextBox();
        TLain = new widget.TextBox();
        internalFrame13 = new widget.InternalFrame();
        Scroll18 = new widget.ScrollPane();
        tbKondisiPsiko = new widget.Table();
        panelGlass28 = new widget.panelisi();
        Scroll40 = new widget.ScrollPane();
        FormInput6 = new widget.PanelBiasa();
        jLabel51 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        TDelusi = new widget.TextBox();
        TProses = new widget.TextBox();
        THalusinasi = new widget.TextBox();
        TAfek = new widget.TextBox();
        TInsight = new widget.TextBox();
        TKesadaran = new widget.TextBox();
        TOrenWaktu = new widget.TextBox();
        TOrenTempat = new widget.TextBox();
        jLabel86 = new widget.Label();
        TOrenRuang = new widget.TextBox();
        jLabel87 = new widget.Label();
        TPerhatian = new widget.TextBox();
        jLabel88 = new widget.Label();
        TPenilaian = new widget.TextBox();
        jLabel89 = new widget.Label();
        TKontrol = new widget.TextBox();
        internalFrame15 = new widget.InternalFrame();
        Scroll27 = new widget.ScrollPane();
        tbTesPsiko = new widget.Table();
        panelGlass29 = new widget.panelisi();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        TTesPsiko = new widget.TextBox();
        TWaktu = new widget.TextBox();
        TTester = new widget.TextBox();
        tgl_rencana = new widget.Tanggal();
        internalFrame16 = new widget.InternalFrame();
        Scroll28 = new widget.ScrollPane();
        tbManifestasi = new widget.Table();
        panelGlass30 = new widget.panelisi();
        jLabel94 = new widget.Label();
        Scroll29 = new widget.ScrollPane();
        TManifes = new widget.TextArea();
        internalFrame17 = new widget.InternalFrame();
        Scroll30 = new widget.ScrollPane();
        tbDiagnosis = new widget.Table();
        panelGlass31 = new widget.panelisi();
        jLabel95 = new widget.Label();
        Scroll31 = new widget.ScrollPane();
        TKesan = new widget.TextArea();
        jLabel96 = new widget.Label();
        TDiagUtama = new widget.TextBox();
        TDiagBanding = new widget.TextBox();
        jLabel97 = new widget.Label();
        btnPenyakit = new widget.Button();
        btnPenyakit1 = new widget.Button();
        internalFrame18 = new widget.InternalFrame();
        Scroll32 = new widget.ScrollPane();
        tbPrognosis = new widget.Table();
        panelGlass32 = new widget.panelisi();
        jLabel98 = new widget.Label();
        Scroll33 = new widget.ScrollPane();
        Tprognosis = new widget.TextArea();
        internalFrame19 = new widget.InternalFrame();
        Scroll34 = new widget.ScrollPane();
        tbTritmen = new widget.Table();
        panelGlass33 = new widget.panelisi();
        Scroll6 = new widget.ScrollPane();
        tbItemTritmen = new widget.Table();
        jLabel44 = new widget.Label();
        BtnPilihTritmen = new widget.Button();
        BtnHapusItem1 = new widget.Button();
        BtnPerbaikiItem1 = new widget.Button();
        jLabel45 = new widget.Label();
        TPertemuan = new widget.TextBox();
        jLabel46 = new widget.Label();
        TCatatan = new widget.TextBox();
        internalFrame21 = new widget.InternalFrame();
        Scroll35 = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();
        panelGlass22 = new widget.panelisi();
        panelGlass9 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass23 = new widget.panelisi();
        jLabel41 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel42 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        jLabel8 = new widget.Label();
        cmbLimit = new widget.ComboBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel19 = new widget.Label();
        LCount = new widget.Label();
        panelGlass24 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel11 = new widget.Label();
        tgl_datang = new widget.TextBox();
        jLabel70 = new widget.Label();
        Tjk = new widget.TextBox();
        TNoRW = new widget.TextBox();
        jLabel101 = new widget.Label();
        TnmPsikolog = new widget.TextBox();
        btnPetugas = new widget.Button();

        WindowDataKeluhan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataKeluhan.setName("WindowDataKeluhan"); // NOI18N
        WindowDataKeluhan.setUndecorated(true);
        WindowDataKeluhan.setResizable(false);

        internalFrame14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Keluhan Permasalahan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame14.setLayout(new java.awt.BorderLayout());

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 70));
        panelGlass7.setLayout(null);

        nmkeluhan.setEditable(false);
        nmkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        nmkeluhan.setName("nmkeluhan"); // NOI18N
        panelGlass7.add(nmkeluhan);
        nmkeluhan.setBounds(266, 10, 326, 23);

        kdkeluhan.setEditable(false);
        kdkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        kdkeluhan.setName("kdkeluhan"); // NOI18N
        panelGlass7.add(kdkeluhan);
        kdkeluhan.setBounds(182, 10, 80, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Permasalahan Lainnya : ");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass7.add(jLabel55);
        jLabel55.setBounds(0, 37, 180, 23);

        masalahLain.setForeground(new java.awt.Color(0, 0, 0));
        masalahLain.setName("masalahLain"); // NOI18N
        panelGlass7.add(masalahLain);
        masalahLain.setBounds(182, 37, 410, 23);

        btnKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        btnKeluhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKeluhan.setMnemonic('X');
        btnKeluhan.setToolTipText("Alt+X");
        btnKeluhan.setName("btnKeluhan"); // NOI18N
        btnKeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluhanActionPerformed(evt);
            }
        });
        panelGlass7.add(btnKeluhan);
        btnKeluhan.setBounds(595, 10, 28, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Nama Keluhan Permasalahan : ");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass7.add(jLabel59);
        jLabel59.setBounds(0, 10, 180, 23);

        internalFrame14.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 150));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 5));

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnSimpan2);

        BtnEdit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        BtnEdit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit1KeyPressed(evt);
            }
        });
        panelGlass13.add(BtnEdit1);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.setPreferredSize(new java.awt.Dimension(73, 30));
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCloseIn3);

        internalFrame14.add(panelGlass13, java.awt.BorderLayout.CENTER);

        WindowDataKeluhan.getContentPane().add(internalFrame14, java.awt.BorderLayout.CENTER);

        WindowDataTritmen.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataTritmen.setName("WindowDataTritmen"); // NOI18N
        WindowDataTritmen.setUndecorated(true);
        WindowDataTritmen.setResizable(false);

        internalFrame20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Rencana Tritmen ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame20.setLayout(new java.awt.BorderLayout());

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 70));
        panelGlass8.setLayout(null);

        nmtritmen.setEditable(false);
        nmtritmen.setForeground(new java.awt.Color(0, 0, 0));
        nmtritmen.setName("nmtritmen"); // NOI18N
        panelGlass8.add(nmtritmen);
        nmtritmen.setBounds(227, 10, 370, 23);

        kdtritmen.setEditable(false);
        kdtritmen.setForeground(new java.awt.Color(0, 0, 0));
        kdtritmen.setName("kdtritmen"); // NOI18N
        panelGlass8.add(kdtritmen);
        kdtritmen.setBounds(143, 10, 80, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Tritmen Lainnya : ");
        jLabel99.setName("jLabel99"); // NOI18N
        panelGlass8.add(jLabel99);
        jLabel99.setBounds(0, 37, 140, 23);

        tritmenLain.setForeground(new java.awt.Color(0, 0, 0));
        tritmenLain.setName("tritmenLain"); // NOI18N
        panelGlass8.add(tritmenLain);
        tritmenLain.setBounds(143, 37, 454, 23);

        btnTritmen.setForeground(new java.awt.Color(0, 0, 0));
        btnTritmen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnTritmen.setMnemonic('X');
        btnTritmen.setToolTipText("Alt+X");
        btnTritmen.setName("btnTritmen"); // NOI18N
        btnTritmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTritmenActionPerformed(evt);
            }
        });
        panelGlass8.add(btnTritmen);
        btnTritmen.setBounds(600, 10, 28, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Nama Rencana Tritmen : ");
        jLabel100.setName("jLabel100"); // NOI18N
        panelGlass8.add(jLabel100);
        jLabel100.setBounds(0, 10, 140, 23);

        internalFrame20.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(44, 150));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 5));

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setMnemonic('S');
        BtnSimpan3.setText("Simpan");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan3ActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnSimpan3);

        BtnEdit2.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit2.setMnemonic('G');
        BtnEdit2.setText("Ganti");
        BtnEdit2.setToolTipText("Alt+G");
        BtnEdit2.setName("BtnEdit2"); // NOI18N
        BtnEdit2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit2ActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnEdit2);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.setPreferredSize(new java.awt.Dimension(73, 30));
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnCloseIn4);

        internalFrame20.add(panelGlass17, java.awt.BorderLayout.CENTER);

        WindowDataTritmen.getContentPane().add(internalFrame20, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Rekam Psikologis Umum / Dewasa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabPsikologis.setBackground(new java.awt.Color(169, 169, 250));
        TabPsikologis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        TabPsikologis.setForeground(new java.awt.Color(0, 0, 0));
        TabPsikologis.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        TabPsikologis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabPsikologis.setName("TabPsikologis"); // NOI18N
        TabPsikologis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPsikologisMouseClicked(evt);
            }
        });

        internalFrame10.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame10.setBorder(null);
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 212));
        panelGlass16.setLayout(null);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Pekerjaan : ");
        jLabel72.setName("jLabel72"); // NOI18N
        panelGlass16.add(jLabel72);
        jLabel72.setBounds(0, 38, 130, 23);

        TPekerjaan.setEditable(false);
        TPekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        TPekerjaan.setName("TPekerjaan"); // NOI18N
        panelGlass16.add(TPekerjaan);
        TPekerjaan.setBounds(133, 38, 592, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Pendidikan : ");
        jLabel73.setName("jLabel73"); // NOI18N
        panelGlass16.add(jLabel73);
        jLabel73.setBounds(0, 66, 130, 23);

        TPendidikan.setEditable(false);
        TPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        TPendidikan.setName("TPendidikan"); // NOI18N
        panelGlass16.add(TPendidikan);
        TPendidikan.setBounds(133, 66, 210, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Status Perkawinan : ");
        jLabel74.setName("jLabel74"); // NOI18N
        panelGlass16.add(jLabel74);
        jLabel74.setBounds(0, 94, 130, 23);

        TSttsKawin.setEditable(false);
        TSttsKawin.setForeground(new java.awt.Color(0, 0, 0));
        TSttsKawin.setName("TSttsKawin"); // NOI18N
        panelGlass16.add(TSttsKawin);
        TSttsKawin.setBounds(133, 94, 210, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Alamat : ");
        jLabel76.setName("jLabel76"); // NOI18N
        panelGlass16.add(jLabel76);
        jLabel76.setBounds(0, 122, 130, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("No. HP/Telpn. : ");
        jLabel77.setName("jLabel77"); // NOI18N
        panelGlass16.add(jLabel77);
        jLabel77.setBounds(0, 178, 130, 23);

        TnoTelp.setEditable(false);
        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        panelGlass16.add(TnoTelp);
        TnoTelp.setBounds(133, 178, 210, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Tempat & Tgl. Lahir : ");
        jLabel71.setName("jLabel71"); // NOI18N
        panelGlass16.add(jLabel71);
        jLabel71.setBounds(0, 10, 130, 23);

        Ttl.setEditable(false);
        Ttl.setForeground(new java.awt.Color(0, 0, 0));
        Ttl.setName("Ttl"); // NOI18N
        panelGlass16.add(Ttl);
        Ttl.setBounds(133, 10, 592, 23);

        Scroll7.setName("Scroll7"); // NOI18N

        TAlamat.setEditable(false);
        TAlamat.setColumns(20);
        TAlamat.setRows(5);
        TAlamat.setName("TAlamat"); // NOI18N
        Scroll7.setViewportView(TAlamat);

        panelGlass16.add(Scroll7);
        Scroll7.setBounds(133, 122, 592, 50);

        internalFrame10.add(panelGlass16, java.awt.BorderLayout.PAGE_START);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 402));

        tbdatadiri.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbdatadiri.setName("tbdatadiri"); // NOI18N
        tbdatadiri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdatadiriMouseClicked(evt);
            }
        });
        tbdatadiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbdatadiriKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbdatadiri);

        internalFrame10.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabPsikologis.addTab("Data Diri Pasien / Klien", internalFrame10);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 401));
        Scroll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll2KeyPressed(evt);
            }
        });

        tbAnggotaKel.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnggotaKel.setName("tbAnggotaKel"); // NOI18N
        tbAnggotaKel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnggotaKelMouseClicked(evt);
            }
        });
        tbAnggotaKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAnggotaKelKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbAnggotaKel);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass11.setLayout(null);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Umur : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass11.add(jLabel12);
        jLabel12.setBounds(0, 38, 84, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Posisi : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(0, 66, 84, 23);

        TposisiKel.setForeground(new java.awt.Color(0, 0, 0));
        TposisiKel.setName("TposisiKel"); // NOI18N
        TposisiKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TposisiKelKeyPressed(evt);
            }
        });
        panelGlass11.add(TposisiKel);
        TposisiKel.setBounds(85, 66, 292, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jns. Kelamin : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass11.add(jLabel13);
        jLabel13.setBounds(189, 38, 90, 23);

        cmbJK.setForeground(new java.awt.Color(0, 0, 0));
        cmbJK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Laki-laki", "Perempuan" }));
        cmbJK.setName("cmbJK"); // NOI18N
        cmbJK.setOpaque(false);
        cmbJK.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbJK);
        cmbJK.setBounds(280, 38, 90, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Keterangan : ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelGlass11.add(jLabel21);
        jLabel21.setBounds(0, 94, 84, 23);

        TketKel.setForeground(new java.awt.Color(0, 0, 0));
        TketKel.setName("TketKel"); // NOI18N
        TketKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketKelKeyPressed(evt);
            }
        });
        panelGlass11.add(TketKel);
        TketKel.setBounds(85, 94, 430, 23);

        TumurKel.setForeground(new java.awt.Color(0, 0, 0));
        TumurKel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurKel.setName("TumurKel"); // NOI18N
        TumurKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurKelKeyPressed(evt);
            }
        });
        panelGlass11.add(TumurKel);
        TumurKel.setBounds(85, 38, 50, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nama : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass11.add(jLabel15);
        jLabel15.setBounds(0, 10, 84, 23);

        TnmKel.setForeground(new java.awt.Color(0, 0, 0));
        TnmKel.setName("TnmKel"); // NOI18N
        TnmKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKelKeyPressed(evt);
            }
        });
        panelGlass11.add(TnmKel);
        TnmKel.setBounds(85, 10, 430, 23);

        cmbUmur.setForeground(new java.awt.Color(0, 0, 0));
        cmbUmur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Th", "Bl", "Hr" }));
        cmbUmur.setName("cmbUmur"); // NOI18N
        cmbUmur.setOpaque(false);
        cmbUmur.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbUmur);
        cmbUmur.setBounds(140, 38, 50, 23);

        internalFrame4.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Komposisi Anggota Keluarga", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(452, 311));

        tbIdentitasPas.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbIdentitasPas.setName("tbIdentitasPas"); // NOI18N
        tbIdentitasPas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIdentitasPasMouseClicked(evt);
            }
        });
        tbIdentitasPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIdentitasPasKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbIdentitasPas);

        internalFrame5.add(Scroll4, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 244));
        panelGlass12.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Umur : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass12.add(jLabel5);
        jLabel5.setBounds(0, 38, 130, 23);

        TumurPas.setForeground(new java.awt.Color(0, 0, 0));
        TumurPas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurPas.setName("TumurPas"); // NOI18N
        TumurPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurPasKeyPressed(evt);
            }
        });
        panelGlass12.add(TumurPas);
        TumurPas.setBounds(133, 38, 50, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Pendidikan : ");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass12.add(jLabel7);
        jLabel7.setBounds(0, 66, 130, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass12.add(jLabel9);
        jLabel9.setBounds(0, 10, 130, 23);

        TnmPas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPas.setName("TnmPas"); // NOI18N
        TnmPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmPasKeyPressed(evt);
            }
        });
        panelGlass12.add(TnmPas);
        TnmPas.setBounds(133, 10, 250, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Anak Ke : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass12.add(jLabel18);
        jLabel18.setBounds(0, 150, 130, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pekerjaan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass12.add(jLabel10);
        jLabel10.setBounds(0, 94, 130, 23);

        TpekerjaanPas.setForeground(new java.awt.Color(0, 0, 0));
        TpekerjaanPas.setName("TpekerjaanPas"); // NOI18N
        TpekerjaanPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanPasKeyPressed(evt);
            }
        });
        panelGlass12.add(TpekerjaanPas);
        TpekerjaanPas.setBounds(133, 94, 190, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Agama : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass12.add(jLabel16);
        jLabel16.setBounds(0, 122, 130, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Tahun.");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass12.add(jLabel20);
        jLabel20.setBounds(190, 38, 50, 23);

        cmbPnd.setForeground(new java.awt.Color(0, 0, 0));
        cmbPnd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TIDAK SEKOLAH", "TK", "SD/MI", "SMP/SLTP/MTS", "SMA/SLTA/SMK/MAN", "PESANTREN", "D1", "D3", "D4", "S1", "S2", "S3" }));
        cmbPnd.setName("cmbPnd"); // NOI18N
        cmbPnd.setOpaque(false);
        cmbPnd.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass12.add(cmbPnd);
        cmbPnd.setBounds(133, 66, 140, 23);

        cmbAgama.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Hindu", "Budha", "Kristen Protestan", "Kristen Katolik", "Konghucu", "Kepercayaan Lain" }));
        cmbAgama.setName("cmbAgama"); // NOI18N
        cmbAgama.setOpaque(false);
        cmbAgama.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass12.add(cmbAgama);
        cmbAgama.setBounds(133, 122, 120, 23);

        TanakKe.setForeground(new java.awt.Color(0, 0, 0));
        TanakKe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TanakKe.setName("TanakKe"); // NOI18N
        TanakKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanakKeKeyPressed(evt);
            }
        });
        panelGlass12.add(TanakKe);
        TanakKe.setBounds(133, 150, 50, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("dari");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass12.add(jLabel22);
        jLabel22.setBounds(183, 150, 30, 23);

        TdariKe.setForeground(new java.awt.Color(0, 0, 0));
        TdariKe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TdariKe.setName("TdariKe"); // NOI18N
        TdariKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdariKeKeyPressed(evt);
            }
        });
        panelGlass12.add(TdariKe);
        TdariKe.setBounds(215, 150, 50, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("bersaudara");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass12.add(jLabel23);
        jLabel23.setBounds(271, 150, 70, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Perkawinan Ke : ");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass12.add(jLabel24);
        jLabel24.setBounds(0, 178, 130, 23);

        TkawinKe.setForeground(new java.awt.Color(0, 0, 0));
        TkawinKe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TkawinKe.setName("TkawinKe"); // NOI18N
        TkawinKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkawinKeKeyPressed(evt);
            }
        });
        panelGlass12.add(TkawinKe);
        TkawinKe.setBounds(133, 178, 50, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("dari");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass12.add(jLabel27);
        jLabel27.setBounds(183, 178, 30, 23);

        TtahunKe.setForeground(new java.awt.Color(0, 0, 0));
        TtahunKe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtahunKe.setName("TtahunKe"); // NOI18N
        TtahunKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunKeKeyPressed(evt);
            }
        });
        panelGlass12.add(TtahunKe);
        TtahunKe.setBounds(215, 178, 50, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("tahun");
        jLabel52.setName("jLabel52"); // NOI18N
        panelGlass12.add(jLabel52);
        jLabel52.setBounds(271, 178, 70, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("No. HP/Telpn. : ");
        jLabel53.setName("jLabel53"); // NOI18N
        panelGlass12.add(jLabel53);
        jLabel53.setBounds(0, 206, 130, 23);

        TnoHpPas.setForeground(new java.awt.Color(0, 0, 0));
        TnoHpPas.setName("TnoHpPas"); // NOI18N
        TnoHpPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoHpPasKeyPressed(evt);
            }
        });
        panelGlass12.add(TnoHpPas);
        TnoHpPas.setBounds(133, 206, 250, 23);

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        TKeluhan.setColumns(20);
        TKeluhan.setRows(5);
        TKeluhan.setName("TKeluhan"); // NOI18N
        TKeluhan.setPreferredSize(new java.awt.Dimension(170, 110));
        TKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeluhanKeyPressed(evt);
            }
        });
        Scroll8.setViewportView(TKeluhan);

        panelGlass12.add(Scroll8);
        Scroll8.setBounds(477, 10, 310, 70);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Keluhan : ");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass12.add(jLabel54);
        jLabel54.setBounds(384, 10, 90, 23);

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        TketPas.setColumns(20);
        TketPas.setRows(5);
        TketPas.setName("TketPas"); // NOI18N
        TketPas.setPreferredSize(new java.awt.Dimension(170, 110));
        TketPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketPasKeyPressed(evt);
            }
        });
        Scroll9.setViewportView(TketPas);

        panelGlass12.add(Scroll9);
        Scroll9.setBounds(477, 86, 310, 70);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Keterangan : ");
        jLabel56.setName("jLabel56"); // NOI18N
        panelGlass12.add(jLabel56);
        jLabel56.setBounds(384, 86, 90, 23);

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        TalamatPas.setColumns(20);
        TalamatPas.setRows(5);
        TalamatPas.setName("TalamatPas"); // NOI18N
        TalamatPas.setPreferredSize(new java.awt.Dimension(170, 110));
        TalamatPas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatPasKeyPressed(evt);
            }
        });
        Scroll11.setViewportView(TalamatPas);

        panelGlass12.add(Scroll11);
        Scroll11.setBounds(477, 162, 310, 70);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Alamat : ");
        jLabel57.setName("jLabel57"); // NOI18N
        panelGlass12.add(jLabel57);
        jLabel57.setBounds(384, 162, 90, 23);

        internalFrame5.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Identitas Pasangan", internalFrame5);

        internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbKeluhan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKeluhan.setName("tbKeluhan"); // NOI18N
        tbKeluhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKeluhanMouseClicked(evt);
            }
        });
        tbKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKeluhanKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(tbKeluhan);

        internalFrame8.add(Scroll10, java.awt.BorderLayout.CENTER);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 160));
        panelGlass14.setLayout(null);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Keluhan Permasalahan");
        jLabel43.setName("jLabel43"); // NOI18N
        panelGlass14.add(jLabel43);
        jLabel43.setBounds(0, 10, 120, 23);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Item/Jenis Keluhan Permasalahan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbItemMasalah.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tbItemMasalah.setName("tbItemMasalah"); // NOI18N
        tbItemMasalah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemMasalahMouseClicked(evt);
            }
        });
        tbItemMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemMasalahKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbItemMasalah);

        panelGlass14.add(Scroll5);
        Scroll5.setBounds(133, 10, 590, 140);

        BtnPilihKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        BtnPilihKeluhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPilihKeluhan.setMnemonic('P');
        BtnPilihKeluhan.setText("Pilihan Keluhan");
        BtnPilihKeluhan.setToolTipText("Alt+P");
        BtnPilihKeluhan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPilihKeluhan.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPilihKeluhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPilihKeluhan.setName("BtnPilihKeluhan"); // NOI18N
        BtnPilihKeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPilihKeluhanActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnPilihKeluhan);
        BtnPilihKeluhan.setBounds(730, 17, 115, 27);

        BtnHapusItem.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusItem.setMnemonic('H');
        BtnHapusItem.setText("Hapus Item");
        BtnHapusItem.setToolTipText("Alt+H");
        BtnHapusItem.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnHapusItem.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnHapusItem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHapusItem.setName("BtnHapusItem"); // NOI18N
        BtnHapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusItemActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnHapusItem);
        BtnHapusItem.setBounds(730, 50, 115, 27);

        BtnPerbaikiItem.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerbaikiItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnPerbaikiItem.setMnemonic('G');
        BtnPerbaikiItem.setText("Perbaiki Item");
        BtnPerbaikiItem.setToolTipText("Alt+G");
        BtnPerbaikiItem.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPerbaikiItem.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPerbaikiItem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPerbaikiItem.setName("BtnPerbaikiItem"); // NOI18N
        BtnPerbaikiItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerbaikiItemActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnPerbaikiItem);
        BtnPerbaikiItem.setBounds(730, 83, 115, 27);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Yang Muncul : ");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass14.add(jLabel58);
        jLabel58.setBounds(0, 25, 130, 23);

        internalFrame8.add(panelGlass14, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Keluhan Permasalahan", internalFrame8);

        internalFrame9.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll15.setName("Scroll15"); // NOI18N
        Scroll15.setOpaque(true);
        Scroll15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll15KeyPressed(evt);
            }
        });

        tbPsikologiKlinis.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPsikologiKlinis.setName("tbPsikologiKlinis"); // NOI18N
        tbPsikologiKlinis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPsikologiKlinisMouseClicked(evt);
            }
        });
        tbPsikologiKlinis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPsikologiKlinisKeyPressed(evt);
            }
        });
        Scroll15.setViewportView(tbPsikologiKlinis);

        internalFrame9.add(Scroll15, java.awt.BorderLayout.CENTER);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 275));
        panelGlass15.setLayout(new java.awt.BorderLayout());

        Scroll37.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Wawancara ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll37.setName("Scroll37"); // NOI18N
        Scroll37.setOpaque(true);

        FormInput3.setBackground(new java.awt.Color(255, 255, 255));
        FormInput3.setBorder(null);
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(900, 480));
        FormInput3.setLayout(null);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Alasan Yang Membuat Pasien  ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput3.add(jLabel25);
        jLabel25.setBounds(0, 80, 160, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Permasalahan Saat Ini : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput3.add(jLabel29);
        jLabel29.setBounds(0, 4, 160, 23);

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        TPermasalahansaatIni.setColumns(20);
        TPermasalahansaatIni.setRows(5);
        TPermasalahansaatIni.setName("TPermasalahansaatIni"); // NOI18N
        TPermasalahansaatIni.setPreferredSize(new java.awt.Dimension(170, 230));
        TPermasalahansaatIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPermasalahansaatIniKeyPressed(evt);
            }
        });
        Scroll12.setViewportView(TPermasalahansaatIni);

        FormInput3.add(Scroll12);
        Scroll12.setBounds(162, 4, 830, 70);

        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);

        TAlasanMencariBantuan.setColumns(20);
        TAlasanMencariBantuan.setRows(5);
        TAlasanMencariBantuan.setName("TAlasanMencariBantuan"); // NOI18N
        TAlasanMencariBantuan.setPreferredSize(new java.awt.Dimension(170, 230));
        TAlasanMencariBantuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanMencariBantuanKeyPressed(evt);
            }
        });
        Scroll13.setViewportView(TAlasanMencariBantuan);

        FormInput3.add(Scroll13);
        Scroll13.setBounds(162, 80, 830, 70);

        Scroll14.setName("Scroll14"); // NOI18N
        Scroll14.setOpaque(true);

        TAlasan.setColumns(20);
        TAlasan.setRows(5);
        TAlasan.setName("TAlasan"); // NOI18N
        TAlasan.setPreferredSize(new java.awt.Dimension(170, 230));
        TAlasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanKeyPressed(evt);
            }
        });
        Scroll14.setViewportView(TAlasan);

        FormInput3.add(Scroll14);
        Scroll14.setBounds(272, 178, 720, 70);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Permasalahan Dinilai : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput3.add(jLabel26);
        jLabel26.setBounds(0, 157, 160, 23);

        RSangatSerius.setBackground(new java.awt.Color(240, 250, 230));
        RSangatSerius.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RSangatSerius);
        RSangatSerius.setText("Sangat Serius");
        RSangatSerius.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RSangatSerius.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RSangatSerius.setName("RSangatSerius"); // NOI18N
        RSangatSerius.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput3.add(RSangatSerius);
        RSangatSerius.setBounds(162, 157, 100, 23);

        RSerius.setBackground(new java.awt.Color(240, 250, 230));
        RSerius.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RSerius);
        RSerius.setText("Serius");
        RSerius.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RSerius.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RSerius.setName("RSerius"); // NOI18N
        RSerius.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput3.add(RSerius);
        RSerius.setBounds(162, 185, 100, 23);

        RKurangSerius.setBackground(new java.awt.Color(240, 250, 230));
        RKurangSerius.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RKurangSerius);
        RKurangSerius.setText("Kurang Serius");
        RKurangSerius.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RKurangSerius.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RKurangSerius.setName("RKurangSerius"); // NOI18N
        RKurangSerius.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput3.add(RKurangSerius);
        RKurangSerius.setBounds(162, 213, 100, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Alasan : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput3.add(jLabel28);
        jLabel28.setBounds(272, 157, 50, 23);

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        THarapan.setColumns(20);
        THarapan.setRows(5);
        THarapan.setName("THarapan"); // NOI18N
        THarapan.setPreferredSize(new java.awt.Dimension(170, 230));
        THarapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THarapanKeyPressed(evt);
            }
        });
        Scroll19.setViewportView(THarapan);

        FormInput3.add(Scroll19);
        Scroll19.setBounds(162, 253, 830, 70);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Harapan : ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput3.add(jLabel31);
        jLabel31.setBounds(0, 253, 160, 23);

        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        TPerubahanDiriSendiri.setColumns(20);
        TPerubahanDiriSendiri.setRows(5);
        TPerubahanDiriSendiri.setName("TPerubahanDiriSendiri"); // NOI18N
        TPerubahanDiriSendiri.setPreferredSize(new java.awt.Dimension(170, 230));
        TPerubahanDiriSendiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPerubahanDiriSendiriKeyPressed(evt);
            }
        });
        Scroll20.setViewportView(TPerubahanDiriSendiri);

        FormInput3.add(Scroll20);
        Scroll20.setBounds(162, 328, 830, 70);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Perubahan Yang Diinginkan ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput3.add(jLabel32);
        jLabel32.setBounds(0, 328, 160, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Dari Diri Sendiri : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput3.add(jLabel33);
        jLabel33.setBounds(0, 342, 160, 23);

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        TPerubahanKeluarga.setColumns(20);
        TPerubahanKeluarga.setRows(5);
        TPerubahanKeluarga.setName("TPerubahanKeluarga"); // NOI18N
        TPerubahanKeluarga.setPreferredSize(new java.awt.Dimension(170, 230));
        TPerubahanKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPerubahanKeluargaKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(TPerubahanKeluarga);

        FormInput3.add(Scroll21);
        Scroll21.setBounds(162, 404, 830, 70);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Perubahan Yang Diinginkan ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput3.add(jLabel34);
        jLabel34.setBounds(0, 404, 160, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Dari Keluarga : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput3.add(jLabel39);
        jLabel39.setBounds(0, 418, 160, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Mencari Bantuan Kali Ini : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput3.add(jLabel60);
        jLabel60.setBounds(0, 94, 160, 23);

        Scroll37.setViewportView(FormInput3);

        panelGlass15.add(Scroll37, java.awt.BorderLayout.CENTER);

        internalFrame9.add(panelGlass15, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Pemeriksaan Psikologi Klinis", internalFrame9);

        internalFrame11.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll16.setName("Scroll16"); // NOI18N
        Scroll16.setOpaque(true);
        Scroll16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll16KeyPressed(evt);
            }
        });

        tbRiwayatHidup.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRiwayatHidup.setName("tbRiwayatHidup"); // NOI18N
        tbRiwayatHidup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatHidupMouseClicked(evt);
            }
        });
        tbRiwayatHidup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatHidupKeyPressed(evt);
            }
        });
        Scroll16.setViewportView(tbRiwayatHidup);

        internalFrame11.add(Scroll16, java.awt.BorderLayout.CENTER);

        panelGlass26.setName("panelGlass26"); // NOI18N
        panelGlass26.setPreferredSize(new java.awt.Dimension(44, 278));
        panelGlass26.setLayout(new java.awt.BorderLayout());

        Scroll39.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " (Masa kanak-kanak, Remaja, Dewasa) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll39.setName("Scroll39"); // NOI18N
        Scroll39.setOpaque(true);

        FormInput5.setBackground(new java.awt.Color(255, 255, 255));
        FormInput5.setBorder(null);
        FormInput5.setName("FormInput5"); // NOI18N
        FormInput5.setPreferredSize(new java.awt.Dimension(900, 388));
        FormInput5.setLayout(null);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Masa Remaja : ");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput5.add(jLabel61);
        jLabel61.setBounds(0, 80, 130, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Masa Kanak-kanak : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput5.add(jLabel62);
        jLabel62.setBounds(0, 4, 130, 23);

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        TMasaKanak.setColumns(20);
        TMasaKanak.setRows(5);
        TMasaKanak.setName("TMasaKanak"); // NOI18N
        TMasaKanak.setPreferredSize(new java.awt.Dimension(170, 230));
        TMasaKanak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMasaKanakKeyPressed(evt);
            }
        });
        Scroll22.setViewportView(TMasaKanak);

        FormInput5.add(Scroll22);
        Scroll22.setBounds(133, 4, 860, 70);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        TMasaRemaja.setColumns(20);
        TMasaRemaja.setRows(5);
        TMasaRemaja.setName("TMasaRemaja"); // NOI18N
        TMasaRemaja.setPreferredSize(new java.awt.Dimension(170, 230));
        TMasaRemaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMasaRemajaKeyPressed(evt);
            }
        });
        Scroll23.setViewportView(TMasaRemaja);

        FormInput5.add(Scroll23);
        Scroll23.setBounds(133, 80, 860, 70);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        TMasaDewasa.setColumns(20);
        TMasaDewasa.setRows(5);
        TMasaDewasa.setName("TMasaDewasa"); // NOI18N
        TMasaDewasa.setPreferredSize(new java.awt.Dimension(170, 230));
        TMasaDewasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMasaDewasaKeyPressed(evt);
            }
        });
        Scroll24.setViewportView(TMasaDewasa);

        FormInput5.add(Scroll24);
        Scroll24.setBounds(133, 157, 860, 70);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Masa Dewasa : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput5.add(jLabel63);
        jLabel63.setBounds(0, 157, 130, 23);

        Scroll25.setName("Scroll25"); // NOI18N
        Scroll25.setOpaque(true);

        TRiwayatFisik.setColumns(20);
        TRiwayatFisik.setRows(5);
        TRiwayatFisik.setName("TRiwayatFisik"); // NOI18N
        TRiwayatFisik.setPreferredSize(new java.awt.Dimension(170, 230));
        TRiwayatFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRiwayatFisikKeyPressed(evt);
            }
        });
        Scroll25.setViewportView(TRiwayatFisik);

        FormInput5.add(Scroll25);
        Scroll25.setBounds(133, 234, 860, 70);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Riwayat Penyakit Fisik ");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput5.add(jLabel65);
        jLabel65.setBounds(0, 234, 130, 23);

        Scroll26.setName("Scroll26"); // NOI18N
        Scroll26.setOpaque(true);

        TRiwayatPengobatan.setColumns(20);
        TRiwayatPengobatan.setRows(5);
        TRiwayatPengobatan.setName("TRiwayatPengobatan"); // NOI18N
        TRiwayatPengobatan.setPreferredSize(new java.awt.Dimension(170, 230));
        TRiwayatPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRiwayatPengobatanKeyPressed(evt);
            }
        });
        Scroll26.setViewportView(TRiwayatPengobatan);

        FormInput5.add(Scroll26);
        Scroll26.setBounds(133, 310, 860, 70);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Riwayat Pengobatan ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput5.add(jLabel66);
        jLabel66.setBounds(0, 310, 130, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("/ Konsultasi : ");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput5.add(jLabel67);
        jLabel67.setBounds(0, 324, 130, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("/ Psikologis : ");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput5.add(jLabel75);
        jLabel75.setBounds(0, 248, 130, 23);

        Scroll39.setViewportView(FormInput5);

        panelGlass26.add(Scroll39, java.awt.BorderLayout.CENTER);

        internalFrame11.add(panelGlass26, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Riwayat Hidup Singkat", internalFrame11);

        internalFrame12.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame12.setBorder(null);
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);
        Scroll17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll17KeyPressed(evt);
            }
        });

        tbObservasi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObservasi.setName("tbObservasi"); // NOI18N
        tbObservasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObservasiMouseClicked(evt);
            }
        });
        tbObservasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObservasiKeyPressed(evt);
            }
        });
        Scroll17.setViewportView(tbObservasi);

        internalFrame12.add(Scroll17, java.awt.BorderLayout.CENTER);

        panelGlass27.setName("panelGlass27"); // NOI18N
        panelGlass27.setPreferredSize(new java.awt.Dimension(44, 240));
        panelGlass27.setLayout(null);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Penampilan : ");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass27.add(jLabel47);
        jLabel47.setBounds(0, 10, 150, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Ekspresi Wajah : ");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass27.add(jLabel48);
        jLabel48.setBounds(0, 38, 150, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Perasaan/Suasana Hati : ");
        jLabel49.setName("jLabel49"); // NOI18N
        panelGlass27.add(jLabel49);
        jLabel49.setBounds(0, 66, 150, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Tingkah Laku : ");
        jLabel50.setName("jLabel50"); // NOI18N
        panelGlass27.add(jLabel50);
        jLabel50.setBounds(0, 94, 150, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Fungsi Umum : ");
        jLabel64.setName("jLabel64"); // NOI18N
        panelGlass27.add(jLabel64);
        jLabel64.setBounds(0, 122, 150, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Fungsi Intelektual : ");
        jLabel68.setName("jLabel68"); // NOI18N
        panelGlass27.add(jLabel68);
        jLabel68.setBounds(0, 150, 150, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Pengalaman Kerja : ");
        jLabel69.setName("jLabel69"); // NOI18N
        panelGlass27.add(jLabel69);
        jLabel69.setBounds(0, 178, 150, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Lain-lain : ");
        jLabel78.setName("jLabel78"); // NOI18N
        panelGlass27.add(jLabel78);
        jLabel78.setBounds(0, 206, 150, 23);

        TPenampilan.setForeground(new java.awt.Color(0, 0, 0));
        TPenampilan.setName("TPenampilan"); // NOI18N
        TPenampilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPenampilanKeyPressed(evt);
            }
        });
        panelGlass27.add(TPenampilan);
        TPenampilan.setBounds(152, 10, 860, 23);

        TEkspresi.setForeground(new java.awt.Color(0, 0, 0));
        TEkspresi.setName("TEkspresi"); // NOI18N
        TEkspresi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEkspresiKeyPressed(evt);
            }
        });
        panelGlass27.add(TEkspresi);
        TEkspresi.setBounds(152, 38, 860, 23);

        TPerasaan.setForeground(new java.awt.Color(0, 0, 0));
        TPerasaan.setName("TPerasaan"); // NOI18N
        TPerasaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPerasaanKeyPressed(evt);
            }
        });
        panelGlass27.add(TPerasaan);
        TPerasaan.setBounds(152, 66, 860, 23);

        TTingkahLaku.setForeground(new java.awt.Color(0, 0, 0));
        TTingkahLaku.setName("TTingkahLaku"); // NOI18N
        TTingkahLaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTingkahLakuKeyPressed(evt);
            }
        });
        panelGlass27.add(TTingkahLaku);
        TTingkahLaku.setBounds(152, 94, 860, 23);

        TFungsiUmum.setForeground(new java.awt.Color(0, 0, 0));
        TFungsiUmum.setName("TFungsiUmum"); // NOI18N
        TFungsiUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFungsiUmumKeyPressed(evt);
            }
        });
        panelGlass27.add(TFungsiUmum);
        TFungsiUmum.setBounds(152, 122, 860, 23);

        TFungsiIntelek.setForeground(new java.awt.Color(0, 0, 0));
        TFungsiIntelek.setName("TFungsiIntelek"); // NOI18N
        TFungsiIntelek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFungsiIntelekKeyPressed(evt);
            }
        });
        panelGlass27.add(TFungsiIntelek);
        TFungsiIntelek.setBounds(152, 150, 860, 23);

        TPengalaman.setForeground(new java.awt.Color(0, 0, 0));
        TPengalaman.setName("TPengalaman"); // NOI18N
        TPengalaman.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPengalamanKeyPressed(evt);
            }
        });
        panelGlass27.add(TPengalaman);
        TPengalaman.setBounds(152, 178, 860, 23);

        TLain.setForeground(new java.awt.Color(0, 0, 0));
        TLain.setName("TLain"); // NOI18N
        TLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainKeyPressed(evt);
            }
        });
        panelGlass27.add(TLain);
        TLain.setBounds(152, 206, 860, 23);

        internalFrame12.add(panelGlass27, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Observasi Psikologis Secara Umum", internalFrame12);

        internalFrame13.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame13.setBorder(null);
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll18.setName("Scroll18"); // NOI18N
        Scroll18.setOpaque(true);

        tbKondisiPsiko.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKondisiPsiko.setName("tbKondisiPsiko"); // NOI18N
        tbKondisiPsiko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKondisiPsikoMouseClicked(evt);
            }
        });
        tbKondisiPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKondisiPsikoKeyPressed(evt);
            }
        });
        Scroll18.setViewportView(tbKondisiPsiko);

        internalFrame13.add(Scroll18, java.awt.BorderLayout.CENTER);

        panelGlass28.setName("panelGlass28"); // NOI18N
        panelGlass28.setPreferredSize(new java.awt.Dimension(44, 278));
        panelGlass28.setLayout(new java.awt.BorderLayout());

        Scroll40.setBorder(null);
        Scroll40.setName("Scroll40"); // NOI18N
        Scroll40.setOpaque(true);

        FormInput6.setBackground(new java.awt.Color(255, 255, 255));
        FormInput6.setBorder(null);
        FormInput6.setName("FormInput6"); // NOI18N
        FormInput6.setPreferredSize(new java.awt.Dimension(900, 350));
        FormInput6.setLayout(null);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Delusi / Waham : ");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput6.add(jLabel51);
        jLabel51.setBounds(0, 10, 150, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Proses Pikiran : ");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput6.add(jLabel79);
        jLabel79.setBounds(0, 38, 150, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Halusinasi : ");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput6.add(jLabel80);
        jLabel80.setBounds(0, 66, 150, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Afek : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput6.add(jLabel81);
        jLabel81.setBounds(0, 94, 150, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Insight : ");
        jLabel82.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput6.add(jLabel82);
        jLabel82.setBounds(0, 122, 150, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Kesadaran : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput6.add(jLabel83);
        jLabel83.setBounds(0, 150, 150, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Orientasi Waktu : ");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput6.add(jLabel84);
        jLabel84.setBounds(0, 178, 150, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Orientasi Tempat : ");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput6.add(jLabel85);
        jLabel85.setBounds(0, 206, 150, 23);

        TDelusi.setForeground(new java.awt.Color(0, 0, 0));
        TDelusi.setName("TDelusi"); // NOI18N
        TDelusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDelusiKeyPressed(evt);
            }
        });
        FormInput6.add(TDelusi);
        TDelusi.setBounds(152, 10, 860, 23);

        TProses.setForeground(new java.awt.Color(0, 0, 0));
        TProses.setName("TProses"); // NOI18N
        TProses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TProsesKeyPressed(evt);
            }
        });
        FormInput6.add(TProses);
        TProses.setBounds(152, 38, 860, 23);

        THalusinasi.setForeground(new java.awt.Color(0, 0, 0));
        THalusinasi.setName("THalusinasi"); // NOI18N
        THalusinasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THalusinasiKeyPressed(evt);
            }
        });
        FormInput6.add(THalusinasi);
        THalusinasi.setBounds(152, 66, 860, 23);

        TAfek.setForeground(new java.awt.Color(0, 0, 0));
        TAfek.setName("TAfek"); // NOI18N
        TAfek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAfekKeyPressed(evt);
            }
        });
        FormInput6.add(TAfek);
        TAfek.setBounds(152, 94, 860, 23);

        TInsight.setForeground(new java.awt.Color(0, 0, 0));
        TInsight.setName("TInsight"); // NOI18N
        TInsight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInsightKeyPressed(evt);
            }
        });
        FormInput6.add(TInsight);
        TInsight.setBounds(152, 122, 860, 23);

        TKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        TKesadaran.setName("TKesadaran"); // NOI18N
        TKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesadaranKeyPressed(evt);
            }
        });
        FormInput6.add(TKesadaran);
        TKesadaran.setBounds(152, 150, 860, 23);

        TOrenWaktu.setForeground(new java.awt.Color(0, 0, 0));
        TOrenWaktu.setName("TOrenWaktu"); // NOI18N
        TOrenWaktu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TOrenWaktuKeyPressed(evt);
            }
        });
        FormInput6.add(TOrenWaktu);
        TOrenWaktu.setBounds(152, 178, 860, 23);

        TOrenTempat.setForeground(new java.awt.Color(0, 0, 0));
        TOrenTempat.setName("TOrenTempat"); // NOI18N
        TOrenTempat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TOrenTempatKeyPressed(evt);
            }
        });
        FormInput6.add(TOrenTempat);
        TOrenTempat.setBounds(152, 206, 860, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Orientasi Ruang : ");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput6.add(jLabel86);
        jLabel86.setBounds(0, 234, 150, 23);

        TOrenRuang.setForeground(new java.awt.Color(0, 0, 0));
        TOrenRuang.setName("TOrenRuang"); // NOI18N
        TOrenRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TOrenRuangKeyPressed(evt);
            }
        });
        FormInput6.add(TOrenRuang);
        TOrenRuang.setBounds(152, 234, 860, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Perhatian : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput6.add(jLabel87);
        jLabel87.setBounds(0, 262, 150, 23);

        TPerhatian.setForeground(new java.awt.Color(0, 0, 0));
        TPerhatian.setName("TPerhatian"); // NOI18N
        TPerhatian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPerhatianKeyPressed(evt);
            }
        });
        FormInput6.add(TPerhatian);
        TPerhatian.setBounds(152, 262, 860, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Penilaian : ");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput6.add(jLabel88);
        jLabel88.setBounds(0, 290, 150, 23);

        TPenilaian.setForeground(new java.awt.Color(0, 0, 0));
        TPenilaian.setName("TPenilaian"); // NOI18N
        TPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPenilaianKeyPressed(evt);
            }
        });
        FormInput6.add(TPenilaian);
        TPenilaian.setBounds(152, 290, 860, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Kontrol Terhadap Impuls : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput6.add(jLabel89);
        jLabel89.setBounds(0, 318, 150, 23);

        TKontrol.setForeground(new java.awt.Color(0, 0, 0));
        TKontrol.setName("TKontrol"); // NOI18N
        TKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKontrolKeyPressed(evt);
            }
        });
        FormInput6.add(TKontrol);
        TKontrol.setBounds(152, 318, 860, 23);

        Scroll40.setViewportView(FormInput6);

        panelGlass28.add(Scroll40, java.awt.BorderLayout.CENTER);

        internalFrame13.add(panelGlass28, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Kondisi Psikopatologis", internalFrame13);

        internalFrame15.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame15.setBorder(null);
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll27.setName("Scroll27"); // NOI18N
        Scroll27.setOpaque(true);

        tbTesPsiko.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTesPsiko.setName("tbTesPsiko"); // NOI18N
        tbTesPsiko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTesPsikoMouseClicked(evt);
            }
        });
        tbTesPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTesPsikoKeyPressed(evt);
            }
        });
        Scroll27.setViewportView(tbTesPsiko);

        internalFrame15.add(Scroll27, java.awt.BorderLayout.CENTER);

        panelGlass29.setName("panelGlass29"); // NOI18N
        panelGlass29.setPreferredSize(new java.awt.Dimension(44, 125));
        panelGlass29.setLayout(null);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Rencana Tes : ");
        jLabel90.setName("jLabel90"); // NOI18N
        panelGlass29.add(jLabel90);
        jLabel90.setBounds(0, 10, 130, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Tes Psikologi : ");
        jLabel91.setName("jLabel91"); // NOI18N
        panelGlass29.add(jLabel91);
        jLabel91.setBounds(0, 38, 130, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Waktu : ");
        jLabel92.setName("jLabel92"); // NOI18N
        panelGlass29.add(jLabel92);
        jLabel92.setBounds(0, 66, 130, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Tester : ");
        jLabel93.setName("jLabel93"); // NOI18N
        panelGlass29.add(jLabel93);
        jLabel93.setBounds(0, 94, 130, 23);

        TTesPsiko.setForeground(new java.awt.Color(0, 0, 0));
        TTesPsiko.setName("TTesPsiko"); // NOI18N
        TTesPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTesPsikoKeyPressed(evt);
            }
        });
        panelGlass29.add(TTesPsiko);
        TTesPsiko.setBounds(133, 38, 390, 23);

        TWaktu.setForeground(new java.awt.Color(0, 0, 0));
        TWaktu.setName("TWaktu"); // NOI18N
        TWaktu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TWaktuKeyPressed(evt);
            }
        });
        panelGlass29.add(TWaktu);
        TWaktu.setBounds(133, 66, 390, 23);

        TTester.setForeground(new java.awt.Color(0, 0, 0));
        TTester.setName("TTester"); // NOI18N
        TTester.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTesterKeyPressed(evt);
            }
        });
        panelGlass29.add(TTester);
        TTester.setBounds(133, 94, 390, 23);

        tgl_rencana.setEditable(false);
        tgl_rencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-10-2022" }));
        tgl_rencana.setDisplayFormat("dd-MM-yyyy");
        tgl_rencana.setName("tgl_rencana"); // NOI18N
        tgl_rencana.setOpaque(false);
        tgl_rencana.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass29.add(tgl_rencana);
        tgl_rencana.setBounds(133, 10, 90, 23);

        internalFrame15.add(panelGlass29, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Tes Psikologi", internalFrame15);

        internalFrame16.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame16.setBorder(null);
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll28.setName("Scroll28"); // NOI18N
        Scroll28.setOpaque(true);

        tbManifestasi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbManifestasi.setName("tbManifestasi"); // NOI18N
        tbManifestasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbManifestasiMouseClicked(evt);
            }
        });
        tbManifestasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbManifestasiKeyPressed(evt);
            }
        });
        Scroll28.setViewportView(tbManifestasi);

        internalFrame16.add(Scroll28, java.awt.BorderLayout.CENTER);

        panelGlass30.setName("panelGlass30"); // NOI18N
        panelGlass30.setPreferredSize(new java.awt.Dimension(44, 125));
        panelGlass30.setLayout(null);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Manifestasi Fungsi : ");
        jLabel94.setName("jLabel94"); // NOI18N
        panelGlass30.add(jLabel94);
        jLabel94.setBounds(0, 10, 130, 23);

        Scroll29.setName("Scroll29"); // NOI18N
        Scroll29.setOpaque(true);

        TManifes.setColumns(20);
        TManifes.setRows(5);
        TManifes.setName("TManifes"); // NOI18N
        TManifes.setPreferredSize(new java.awt.Dimension(170, 230));
        Scroll29.setViewportView(TManifes);

        panelGlass30.add(Scroll29);
        Scroll29.setBounds(133, 10, 860, 106);

        internalFrame16.add(panelGlass30, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Manifestasi Fungsi Psikologis", internalFrame16);

        internalFrame17.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame17.setBorder(null);
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll30.setName("Scroll30"); // NOI18N
        Scroll30.setOpaque(true);

        tbDiagnosis.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosis.setName("tbDiagnosis"); // NOI18N
        tbDiagnosis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosisMouseClicked(evt);
            }
        });
        tbDiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosisKeyPressed(evt);
            }
        });
        Scroll30.setViewportView(tbDiagnosis);

        internalFrame17.add(Scroll30, java.awt.BorderLayout.CENTER);

        panelGlass31.setName("panelGlass31"); // NOI18N
        panelGlass31.setPreferredSize(new java.awt.Dimension(44, 145));
        panelGlass31.setLayout(null);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Kesan Awal : ");
        jLabel95.setName("jLabel95"); // NOI18N
        panelGlass31.add(jLabel95);
        jLabel95.setBounds(0, 10, 130, 23);

        Scroll31.setName("Scroll31"); // NOI18N
        Scroll31.setOpaque(true);

        TKesan.setColumns(20);
        TKesan.setRows(5);
        TKesan.setName("TKesan"); // NOI18N
        TKesan.setPreferredSize(new java.awt.Dimension(170, 230));
        TKesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesanKeyPressed(evt);
            }
        });
        Scroll31.setViewportView(TKesan);

        panelGlass31.add(Scroll31);
        Scroll31.setBounds(133, 10, 410, 70);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Diagnosis Utama (dx) : ");
        jLabel96.setName("jLabel96"); // NOI18N
        panelGlass31.add(jLabel96);
        jLabel96.setBounds(0, 85, 130, 23);

        TDiagUtama.setForeground(new java.awt.Color(0, 0, 0));
        TDiagUtama.setName("TDiagUtama"); // NOI18N
        TDiagUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagUtamaKeyPressed(evt);
            }
        });
        panelGlass31.add(TDiagUtama);
        TDiagUtama.setBounds(133, 85, 710, 23);

        TDiagBanding.setForeground(new java.awt.Color(0, 0, 0));
        TDiagBanding.setName("TDiagBanding"); // NOI18N
        TDiagBanding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagBandingKeyPressed(evt);
            }
        });
        panelGlass31.add(TDiagBanding);
        TDiagBanding.setBounds(133, 113, 710, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Diagnosis Banding (dd) : ");
        jLabel97.setName("jLabel97"); // NOI18N
        panelGlass31.add(jLabel97);
        jLabel97.setBounds(0, 113, 130, 23);

        btnPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        btnPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenyakit.setMnemonic('3');
        btnPenyakit.setToolTipText("Alt+3");
        btnPenyakit.setName("btnPenyakit"); // NOI18N
        btnPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenyakitActionPerformed(evt);
            }
        });
        panelGlass31.add(btnPenyakit);
        btnPenyakit.setBounds(845, 85, 28, 23);

        btnPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        btnPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenyakit1.setMnemonic('3');
        btnPenyakit1.setToolTipText("Alt+3");
        btnPenyakit1.setName("btnPenyakit1"); // NOI18N
        btnPenyakit1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenyakit1ActionPerformed(evt);
            }
        });
        panelGlass31.add(btnPenyakit1);
        btnPenyakit1.setBounds(845, 113, 28, 23);

        internalFrame17.add(panelGlass31, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Diagnosis (DSM / ICD / PPDGJ)", internalFrame17);

        internalFrame18.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame18.setBorder(null);
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll32.setName("Scroll32"); // NOI18N
        Scroll32.setOpaque(true);

        tbPrognosis.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPrognosis.setName("tbPrognosis"); // NOI18N
        tbPrognosis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPrognosisMouseClicked(evt);
            }
        });
        tbPrognosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPrognosisKeyPressed(evt);
            }
        });
        Scroll32.setViewportView(tbPrognosis);

        internalFrame18.add(Scroll32, java.awt.BorderLayout.CENTER);

        panelGlass32.setName("panelGlass32"); // NOI18N
        panelGlass32.setPreferredSize(new java.awt.Dimension(44, 145));
        panelGlass32.setLayout(null);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Prognosis : ");
        jLabel98.setName("jLabel98"); // NOI18N
        panelGlass32.add(jLabel98);
        jLabel98.setBounds(0, 10, 130, 23);

        Scroll33.setName("Scroll33"); // NOI18N
        Scroll33.setOpaque(true);

        Tprognosis.setColumns(20);
        Tprognosis.setRows(5);
        Tprognosis.setName("Tprognosis"); // NOI18N
        Tprognosis.setPreferredSize(new java.awt.Dimension(170, 230));
        Scroll33.setViewportView(Tprognosis);

        panelGlass32.add(Scroll33);
        Scroll33.setBounds(133, 10, 820, 123);

        internalFrame18.add(panelGlass32, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Prognosis", internalFrame18);

        internalFrame19.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame19.setBorder(null);
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll34.setName("Scroll34"); // NOI18N
        Scroll34.setOpaque(true);

        tbTritmen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTritmen.setName("tbTritmen"); // NOI18N
        tbTritmen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTritmenMouseClicked(evt);
            }
        });
        tbTritmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTritmenKeyPressed(evt);
            }
        });
        Scroll34.setViewportView(tbTritmen);

        internalFrame19.add(Scroll34, java.awt.BorderLayout.CENTER);

        panelGlass33.setName("panelGlass33"); // NOI18N
        panelGlass33.setPreferredSize(new java.awt.Dimension(44, 215));
        panelGlass33.setLayout(null);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Item Rencana Tritmen ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbItemTritmen.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tbItemTritmen.setName("tbItemTritmen"); // NOI18N
        tbItemTritmen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemTritmenMouseClicked(evt);
            }
        });
        tbItemTritmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemTritmenKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbItemTritmen);

        panelGlass33.add(Scroll6);
        Scroll6.setBounds(153, 10, 590, 140);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Rencana Tritmen : ");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass33.add(jLabel44);
        jLabel44.setBounds(0, 10, 150, 23);

        BtnPilihTritmen.setForeground(new java.awt.Color(0, 0, 0));
        BtnPilihTritmen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPilihTritmen.setMnemonic('P');
        BtnPilihTritmen.setText("Pilihan Tritmen");
        BtnPilihTritmen.setToolTipText("Alt+P");
        BtnPilihTritmen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPilihTritmen.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPilihTritmen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPilihTritmen.setName("BtnPilihTritmen"); // NOI18N
        BtnPilihTritmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPilihTritmenActionPerformed(evt);
            }
        });
        panelGlass33.add(BtnPilihTritmen);
        BtnPilihTritmen.setBounds(750, 17, 115, 27);

        BtnHapusItem1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusItem1.setMnemonic('H');
        BtnHapusItem1.setText("Hapus Item");
        BtnHapusItem1.setToolTipText("Alt+H");
        BtnHapusItem1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnHapusItem1.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnHapusItem1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHapusItem1.setName("BtnHapusItem1"); // NOI18N
        BtnHapusItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusItem1ActionPerformed(evt);
            }
        });
        panelGlass33.add(BtnHapusItem1);
        BtnHapusItem1.setBounds(750, 50, 115, 27);

        BtnPerbaikiItem1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerbaikiItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnPerbaikiItem1.setMnemonic('G');
        BtnPerbaikiItem1.setText("Perbaiki Item");
        BtnPerbaikiItem1.setToolTipText("Alt+G");
        BtnPerbaikiItem1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPerbaikiItem1.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPerbaikiItem1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPerbaikiItem1.setName("BtnPerbaikiItem1"); // NOI18N
        BtnPerbaikiItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerbaikiItem1ActionPerformed(evt);
            }
        });
        panelGlass33.add(BtnPerbaikiItem1);
        BtnPerbaikiItem1.setBounds(750, 83, 115, 27);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Pertemuan Selanjutnya : ");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass33.add(jLabel45);
        jLabel45.setBounds(0, 153, 150, 23);

        TPertemuan.setForeground(new java.awt.Color(0, 0, 0));
        TPertemuan.setName("TPertemuan"); // NOI18N
        TPertemuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPertemuanKeyPressed(evt);
            }
        });
        panelGlass33.add(TPertemuan);
        TPertemuan.setBounds(153, 153, 590, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Catatan Tambahan : ");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass33.add(jLabel46);
        jLabel46.setBounds(0, 181, 150, 23);

        TCatatan.setForeground(new java.awt.Color(0, 0, 0));
        TCatatan.setName("TCatatan"); // NOI18N
        TCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCatatanKeyPressed(evt);
            }
        });
        panelGlass33.add(TCatatan);
        TCatatan.setBounds(153, 181, 590, 23);

        internalFrame19.add(panelGlass33, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Rencana Tritmen", internalFrame19);

        internalFrame21.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame21.setBorder(null);
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll35.setName("Scroll35"); // NOI18N
        Scroll35.setOpaque(true);

        LoadHTML.setEditable(false);
        LoadHTML.setBorder(null);
        LoadHTML.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll35.setViewportView(LoadHTML);

        internalFrame21.add(Scroll35, java.awt.BorderLayout.CENTER);

        TabPsikologis.addTab("Preview Rekam Psikologis", internalFrame21);

        internalFrame1.add(TabPsikologis, java.awt.BorderLayout.CENTER);
        TabPsikologis.getAccessibleContext().setAccessibleName("Data Diri Klien");
        TabPsikologis.getAccessibleContext().setAccessibleDescription("");

        panelGlass22.setName("panelGlass22"); // NOI18N
        panelGlass22.setPreferredSize(new java.awt.Dimension(44, 100));
        panelGlass22.setLayout(new java.awt.BorderLayout());

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 50));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnSimpan);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnBatal);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnEdit);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnPrint);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnKeluar);

        panelGlass22.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        panelGlass23.setName("panelGlass23"); // NOI18N
        panelGlass23.setPreferredSize(new java.awt.Dimension(44, 50));
        panelGlass23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Tanggal : ");
        jLabel41.setName("jLabel41"); // NOI18N
        jLabel41.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass23.add(jLabel41);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-10-2022" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass23.add(tgl1);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("s.d");
        jLabel42.setName("jLabel42"); // NOI18N
        jLabel42.setPreferredSize(new java.awt.Dimension(20, 23));
        panelGlass23.add(jLabel42);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-10-2022" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass23.add(tgl2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass23.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(240, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass23.add(TCari);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Limit Data :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass23.add(jLabel8);

        cmbLimit.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100", "200", "300", "400", "500", "1000", "Semua" }));
        cmbLimit.setName("cmbLimit"); // NOI18N
        cmbLimit.setOpaque(false);
        cmbLimit.setPreferredSize(new java.awt.Dimension(66, 23));
        panelGlass23.add(cmbLimit);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass23.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass23.add(BtnAll);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Record :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 30));
        panelGlass23.add(jLabel19);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(100, 30));
        panelGlass23.add(LCount);

        panelGlass22.add(panelGlass23, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(panelGlass22, java.awt.BorderLayout.PAGE_END);

        panelGlass24.setName("panelGlass24"); // NOI18N
        panelGlass24.setPreferredSize(new java.awt.Dimension(44, 75));
        panelGlass24.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien : ");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass24.add(jLabel3);
        jLabel3.setBounds(0, 10, 130, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass24.add(TNoRM);
        TNoRM.setBounds(276, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass24.add(TPasien);
        TPasien.setBounds(350, 10, 430, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Kedatangan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass24.add(jLabel11);
        jLabel11.setBounds(0, 38, 130, 23);

        tgl_datang.setEditable(false);
        tgl_datang.setForeground(new java.awt.Color(0, 0, 0));
        tgl_datang.setName("tgl_datang"); // NOI18N
        panelGlass24.add(tgl_datang);
        tgl_datang.setBounds(133, 38, 100, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Jenis Kelamin : ");
        jLabel70.setName("jLabel70"); // NOI18N
        panelGlass24.add(jLabel70);
        jLabel70.setBounds(235, 38, 80, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        panelGlass24.add(Tjk);
        Tjk.setBounds(317, 38, 90, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass24.add(TNoRW);
        TNoRW.setBounds(133, 10, 140, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Psikolog Pemeriksa : ");
        jLabel101.setName("jLabel101"); // NOI18N
        panelGlass24.add(jLabel101);
        jLabel101.setBounds(408, 38, 110, 23);

        TnmPsikolog.setEditable(false);
        TnmPsikolog.setForeground(new java.awt.Color(0, 0, 0));
        TnmPsikolog.setName("TnmPsikolog"); // NOI18N
        panelGlass24.add(TnmPsikolog);
        TnmPsikolog.setBounds(520, 38, 260, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('3');
        btnPetugas.setToolTipText("Alt+3");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        panelGlass24.add(btnPetugas);
        btnPetugas.setBounds(783, 38, 28, 23);

        internalFrame1.add(panelGlass24, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        try {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                cekRPD = 0;
                cekRPD = Sequel.cariInteger("SELECT COUNT(-1) cek FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat WHERE rp.no_rkm_medis='" + TNoRM.getText() + "' and pd.rekam_psikologis='umum_dewasa'");
                if (TabPsikologis.getSelectedIndex() == 0) {                    
                    simpanDD();
                } else if (TabPsikologis.getSelectedIndex() == 1) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanKAK();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 2) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanIP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 3) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanKP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 4) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanPPK();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 5) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanRHS();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 6) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanOP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 7) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanKPsi();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 8) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanTP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 9) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanMF();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 10) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanD();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 11) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 12) {
                    if (cekRPD == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanRT();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal proses menyimpan data,..!!");
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        cmbLimit.setSelectedIndex(0);
        if (TabPsikologis.getSelectedIndex() == 0) {
            emptTextDD();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            emptTextKAK();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            emptTextIP();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            emptTextKP();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            emptTextPPK();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            emptTextRHS();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            emptTextOP();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            emptTextKPsi();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            emptTextTP();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            emptTextMF();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            emptTextD();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            emptTextP();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            emptTextRT();
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (TabPsikologis.getSelectedIndex() == 0) {
                    hapusDD();
                } else if (TabPsikologis.getSelectedIndex() == 1) {
                    hapusKAK();
                } else if (TabPsikologis.getSelectedIndex() == 2) {
                    hapusIP();
                } else if (TabPsikologis.getSelectedIndex() == 3) {
                    hapusKP();
                } else if (TabPsikologis.getSelectedIndex() == 4) {
                    hapusPPK();
                } else if (TabPsikologis.getSelectedIndex() == 5) {
                    hapusRHS();
                } else if (TabPsikologis.getSelectedIndex() == 6) {
                    hapusOP();
                } else if (TabPsikologis.getSelectedIndex() == 7) {
                    hapusKPsi();
                } else if (TabPsikologis.getSelectedIndex() == 8) {
                    hapusTP();
                } else if (TabPsikologis.getSelectedIndex() == 9) {
                    hapusMF();
                } else if (TabPsikologis.getSelectedIndex() == 10) {
                    hapusD();
                } else if (TabPsikologis.getSelectedIndex() == 11) {
                    hapusP();;
                } else if (TabPsikologis.getSelectedIndex() == 12) {
                    hapusRT();
                }
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void TRespirasiActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        emptText();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnKeluarActionPerformed(null);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabPsikologis.getSelectedIndex() == 0) {
            tampilDataDiri();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            tampilAnggotaKeluarga();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasPasangan();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            tampilKeluhanMasalah();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilPsikologiKlinis();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilRiwayatHidup();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilObservasi();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilKondisiPsi();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilTesPsikologi();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilManifestasiFungsi();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilDiagnosis();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilPrognosis();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            tampilRencanaTritmen();
        } else if (TabPsikologis.getSelectedIndex() == 13) {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                tampilDataDiri();
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data diri klien/pasien sesuai tgl. kedatangan masih kosong...!!!!");
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    tampilPreview();
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnCari);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TabPsikologisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPsikologisMouseClicked
        if (TabPsikologis.getSelectedIndex() == 0) {            
            tampilDataDiri();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            tampilAnggotaKeluarga();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasPasangan();
        } else if (TabPsikologis.getSelectedIndex() == 3) {  
            tampilKeluhanMasalah();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilPsikologiKlinis();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilRiwayatHidup();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilObservasi();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilKondisiPsi();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilTesPsikologi();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilManifestasiFungsi();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilDiagnosis();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilPrognosis();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            tampilRencanaTritmen();
        } else if (TabPsikologis.getSelectedIndex() == 13) {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                tampilDataDiri();
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data diri klien/pasien sesuai tgl. kedatangan masih kosong...!!!!");
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    tampilPreview();
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_TabPsikologisMouseClicked

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
    try {
        if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (TabPsikologis.getSelectedIndex() == 0) {
                gantiDD();
            } else if (TabPsikologis.getSelectedIndex() == 1) {
                gantiKAK();
            } else if (TabPsikologis.getSelectedIndex() == 2) {
                gantiIP();
            } else if (TabPsikologis.getSelectedIndex() == 3) {
                gantiKP();
            } else if (TabPsikologis.getSelectedIndex() == 4) {
                gantiPPK();                               
            } else if (TabPsikologis.getSelectedIndex() == 5) {
                gantiRHS();
            } else if (TabPsikologis.getSelectedIndex() == 6) {
                gantiOP();
            } else if (TabPsikologis.getSelectedIndex() == 7) {
                gantiKPsi();
            } else if (TabPsikologis.getSelectedIndex() == 8) {
                gantiTP();
            } else if (TabPsikologis.getSelectedIndex() == 9) {
                gantiMF();
            } else if (TabPsikologis.getSelectedIndex() == 10) {
                gantiD();
            } else if (TabPsikologis.getSelectedIndex() == 11) {
                gantiP();
            } else if (TabPsikologis.getSelectedIndex() == 12) {
                gantiRT();
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal Tersimpan, hubungi Admin");
    }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnEditActionPerformed(null);
    }
}//GEN-LAST:event_BtnEditKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void TposisiKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TposisiKelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TposisiKel, TketKel);
        }
    }//GEN-LAST:event_TposisiKelKeyPressed

    private void TketKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketKelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TposisiKel, BtnSimpan);
        }
    }//GEN-LAST:event_TketKelKeyPressed

    private void TumurKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurKelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TumurKel, cmbUmur);
        }
    }//GEN-LAST:event_TumurKelKeyPressed

    private void TnmPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnmPas, TumurPas);
        }
    }//GEN-LAST:event_TnmPasKeyPressed

    private void TumurPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TumurPas, cmbPnd);
        }
    }//GEN-LAST:event_TumurPasKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabPsikologis.setSelectedIndex(0);
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_formWindowOpened

    private void tbdatadiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbdatadiriKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDD();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbdatadiriKeyPressed

    private void tbdatadiriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatadiriMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataDD();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbdatadiriMouseClicked

    private void tbAnggotaKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnggotaKelKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKAK();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbAnggotaKelKeyPressed

    private void tbAnggotaKelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnggotaKelMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataKAK();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbAnggotaKelMouseClicked

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        if (TabPsikologis.getSelectedIndex() == 0) {
            tampilDataDiri();
            emptTextDD();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            tampilAnggotaKeluarga();
            emptTextKAK();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasPasangan();
            emptTextIP();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            tampilKeluhanMasalah();
            emptTextKP();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilPsikologiKlinis();
            emptTextPPK();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilRiwayatHidup();
            emptTextRHS();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilObservasi();
            emptTextOP();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilKondisiPsi();
            emptTextKPsi();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilTesPsikologi();
            emptTextTP();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilManifestasiFungsi();
            emptTextMF();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilDiagnosis();
            emptTextD();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilPrognosis();
            emptTextP();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            tampilRencanaTritmen();
            emptTextRT();
        } else if (TabPsikologis.getSelectedIndex() == 13) {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                tampilDataDiri();
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data diri klien/pasien sesuai tgl. kedatangan masih kosong...!!!!");
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    tampilPreview();
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            tampil12();
            TCari.setText("");
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void tbIdentitasPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIdentitasPasKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIP();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbIdentitasPasKeyPressed

    private void tbIdentitasPasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIdentitasPasMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataIP();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbIdentitasPasMouseClicked

    private void tbKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKeluhanKeyPressed
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKeluhan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbKeluhanKeyPressed

    private void tbKeluhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKeluhanMouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getDataKeluhan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKeluhanMouseClicked

    private void Scroll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll2KeyPressed

    private void tbPsikologiKlinisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPsikologiKlinisKeyPressed
        if (tabMode6.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPsikologisKlinis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPsikologiKlinisKeyPressed

    private void tbPsikologiKlinisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPsikologiKlinisMouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getDataPsikologisKlinis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPsikologiKlinisMouseClicked

    private void tbRiwayatHidupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatHidupKeyPressed
        if (tabMode7.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwayatHidup();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatHidupKeyPressed

    private void tbRiwayatHidupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatHidupMouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getDataRiwayatHidup();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatHidupMouseClicked

    private void tbObservasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObservasiMouseClicked
        if (tabMode8.getRowCount() != 0) {
            try {
                getDataObservasi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObservasiMouseClicked

    private void tbObservasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObservasiKeyPressed
        if (tabMode8.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataObservasi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObservasiKeyPressed

    private void tbKondisiPsikoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKondisiPsikoMouseClicked
        if (tabMode9.getRowCount() != 0) {
            try {
                getDataKondisiPsi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKondisiPsikoMouseClicked

    private void tbKondisiPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKondisiPsikoKeyPressed
        if (tabMode9.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKondisiPsi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbKondisiPsikoKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            tampilDataDiri();
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data diri klien/pasien sesuai tgl. kedatangan masih kosong...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                tampilPreview();
                cetakRPD();
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void TnmKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TNoRW, TumurKel);
        }
    }//GEN-LAST:event_TnmKelKeyPressed

    private void TanakKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanakKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, cmbAgama, TdariKe);
        }
    }//GEN-LAST:event_TanakKeKeyPressed

    private void TdariKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdariKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TanakKe, TkawinKe);
        }
    }//GEN-LAST:event_TdariKeKeyPressed

    private void TkawinKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkawinKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TdariKe, TtahunKe);
        }
    }//GEN-LAST:event_TkawinKeKeyPressed

    private void TtahunKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TkawinKe, TnoHpPas);
        }
    }//GEN-LAST:event_TtahunKeKeyPressed

    private void TnoHpPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoHpPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TtahunKe, TKeluhan);
        }
    }//GEN-LAST:event_TnoHpPasKeyPressed

    private void TpekerjaanPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, cmbPnd, cmbAgama);
        }
    }//GEN-LAST:event_TpekerjaanPasKeyPressed

    private void tbItemMasalahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemMasalahMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getItemKeluhan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemMasalahMouseClicked

    private void tbItemMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemMasalahKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getItemKeluhan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemMasalahKeyPressed

    private void BtnPilihKeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPilihKeluhanActionPerformed
        kdkeluhan.setText("");
        nmkeluhan.setText("");
        masalahLain.setText("");
        BtnSimpan2.setEnabled(true);
        BtnEdit1.setEnabled(false);

        WindowDataKeluhan.setSize(655, 135);
        WindowDataKeluhan.setLocationRelativeTo(internalFrame1);
        WindowDataKeluhan.setVisible(true);
        WindowDataKeluhan.requestFocus();
    }//GEN-LAST:event_BtnPilihKeluhanActionPerformed

    private void BtnHapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusItemActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Jenis keluhan permasalahan yang dipilih/ditentukan belum ada...!!");
            BtnPilihKeluhan.requestFocus();
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data keluhan permasalahan yang dipilih pada tabel...!!!!");
            tbItemMasalah.requestFocus();
        } else if (tbItemMasalah.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data keluhan permasalahannya pada tabel...!!!!");
            tbItemMasalah.requestFocus();
        } else {
            tabMode4.removeRow(tbItemMasalah.getSelectedRow());
            kdkeluhan.setText("");
            nmkeluhan.setText("");
            masalahLain.setText("");
            BtnHapusItem.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusItemActionPerformed

    private void BtnPerbaikiItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerbaikiItemActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Keluhan permasalahan yang dipilih/ditentukan belum ada...!!");
            BtnPilihKeluhan.requestFocus();
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data keluhan permasalahan yang dipilih pada tabel...!!!!");
            tbItemMasalah.requestFocus();
        } else if (tbItemMasalah.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data keluhan permasalahannya pada tabel...!!!!");
            tbItemMasalah.requestFocus();
        } else {
            BtnSimpan2.setEnabled(false);
            BtnEdit1.setEnabled(true);

            WindowDataKeluhan.setSize(655, 135);
            WindowDataKeluhan.setLocationRelativeTo(internalFrame1);
            WindowDataKeluhan.setVisible(true);
            WindowDataKeluhan.requestFocus();
        }
    }//GEN-LAST:event_BtnPerbaikiItemActionPerformed

    private void btnKeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluhanActionPerformed
        akses.setform("DlgRekamPsikologisDewasa");
        keluhan.setSize(707, internalFrame1.getHeight() - 40);
        keluhan.setLocationRelativeTo(internalFrame1);
        keluhan.cekKategori.setText("umum_dewasa");
        keluhan.isCek();
        keluhan.emptTeks(); 
        keluhan.setVisible(true);
        keluhan.TCari.requestFocus();
    }//GEN-LAST:event_btnKeluhanActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (kdkeluhan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis keluhan permasalahanya...!!!!");
        } else {
            data = 0;
            for (i = 0; i < tbItemMasalah.getRowCount(); i++) {
                if (tbItemMasalah.getValueAt(i, 0).toString().equals(kdkeluhan.getText())) {
                    data++;
                }
            }
       
            if (data == 0) {
                if (kdkeluhan.getText().equals("KP0053")) {
                    if (masalahLain.getText().length() < 2) {
                        JOptionPane.showMessageDialog(null, "Nama keluhan permasalahan lainnya harus diisi dulu...!!!!");
                        masalahLain.requestFocus();
                    } else {
                        tabMode4.addRow(new Object[]{kdkeluhan.getText(), nmkeluhan.getText(), masalahLain.getText(), Sequel.cariIsi("select now()")});
                        kdkeluhan.setText("");
                        nmkeluhan.setText("");
                        masalahLain.setText("");
                        btnKeluhan.requestFocus();
                    }

                } else {
                    tabMode4.addRow(new Object[]{kdkeluhan.getText(), nmkeluhan.getText(), masalahLain.getText(), Sequel.cariIsi("select now()")});
                    kdkeluhan.setText("");
                    nmkeluhan.setText("");
                    masalahLain.setText("");
                    btnKeluhan.requestFocus();
                }
            } else if (data >= 1) {
                JOptionPane.showMessageDialog(null, "Nama keluhan permasalahan tersebut sudah ada dipilih...!!!!");
                btnKeluhan.requestFocus();
            }            
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if (kdkeluhan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis keluhan permasalahanya...!!!!");
        } else {
            data = 0;
            for (i = 0; i < tbItemMasalah.getRowCount(); i++) {
                if (tbItemMasalah.getValueAt(i, 0).toString().equals(kdkeluhan.getText())) {
                    data++;
                }
            }
       
            if (data == 0) {
                if (kdkeluhan.getText().equals("KP0053")) {
                    if (masalahLain.getText().length() < 2) {
                        JOptionPane.showMessageDialog(null, "Nama keluhan permasalahan lainnya harus diisi dulu...!!!!");
                        masalahLain.requestFocus();
                    } else {
                        tabMode4.removeRow(tbItemMasalah.getSelectedRow());
                        tabMode4.addRow(new Object[]{kdkeluhan.getText(), nmkeluhan.getText(), masalahLain.getText(), Sequel.cariIsi("select now()")});
                        BtnCloseIn3ActionPerformed(null);
                    }
                } else {
                    tabMode4.removeRow(tbItemMasalah.getSelectedRow());
                    tabMode4.addRow(new Object[]{kdkeluhan.getText(), nmkeluhan.getText(), masalahLain.getText(), Sequel.cariIsi("select now()")});
                    BtnCloseIn3ActionPerformed(null);
                }
            } else if (data >= 1) {
                JOptionPane.showMessageDialog(null, "Nama keluhan permasalahan tersebut sudah ada dipilih...!!!!");
                btnKeluhan.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnEditActionPerformed(null);
        }
    }//GEN-LAST:event_BtnEdit1KeyPressed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        kdkeluhan.setText("");
        nmkeluhan.setText("");
        masalahLain.setText("");        
        WindowDataKeluhan.dispose();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void Scroll16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll16KeyPressed

    private void Scroll15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll15KeyPressed

    private void TPenampilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPenampilanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TPenampilan, TEkspresi);
        }
    }//GEN-LAST:event_TPenampilanKeyPressed

    private void TEkspresiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEkspresiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TPenampilan, TPerasaan);
        }
    }//GEN-LAST:event_TEkspresiKeyPressed

    private void TPerasaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPerasaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TEkspresi, TTingkahLaku);
        }
    }//GEN-LAST:event_TPerasaanKeyPressed

    private void TTingkahLakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTingkahLakuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TPerasaan, TFungsiUmum);
        }
    }//GEN-LAST:event_TTingkahLakuKeyPressed

    private void TFungsiUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFungsiUmumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TTingkahLaku, TFungsiIntelek);
        }
    }//GEN-LAST:event_TFungsiUmumKeyPressed

    private void TFungsiIntelekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFungsiIntelekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TFungsiUmum, TPengalaman);
        }
    }//GEN-LAST:event_TFungsiIntelekKeyPressed

    private void TPengalamanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPengalamanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TFungsiIntelek, TLain);
        }
    }//GEN-LAST:event_TPengalamanKeyPressed

    private void TLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TPengalaman, TPenampilan);
        }
    }//GEN-LAST:event_TLainKeyPressed

    private void Scroll17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll17KeyPressed

    private void TDelusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDelusiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TDelusi, TProses);
        }
    }//GEN-LAST:event_TDelusiKeyPressed

    private void TProsesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TProsesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TDelusi, THalusinasi);
        }
    }//GEN-LAST:event_TProsesKeyPressed

    private void THalusinasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THalusinasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TProses, TAfek);
        }
    }//GEN-LAST:event_THalusinasiKeyPressed

    private void TAfekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAfekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, THalusinasi, TInsight);
        }
    }//GEN-LAST:event_TAfekKeyPressed

    private void TInsightKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInsightKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TAfek, TKesadaran);
        }
    }//GEN-LAST:event_TInsightKeyPressed

    private void TKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesadaranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TInsight, TOrenWaktu);
        }
    }//GEN-LAST:event_TKesadaranKeyPressed

    private void TOrenWaktuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TOrenWaktuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TKesadaran, TOrenTempat);
        }
    }//GEN-LAST:event_TOrenWaktuKeyPressed

    private void TOrenTempatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TOrenTempatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TOrenWaktu, TOrenRuang);
        }
    }//GEN-LAST:event_TOrenTempatKeyPressed

    private void TOrenRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TOrenRuangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TOrenTempat, TPerhatian);
        }
    }//GEN-LAST:event_TOrenRuangKeyPressed

    private void TPerhatianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPerhatianKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TOrenRuang, TPenilaian);
        }
    }//GEN-LAST:event_TPerhatianKeyPressed

    private void TPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPenilaianKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TPerhatian, TKontrol);
        }
    }//GEN-LAST:event_TPenilaianKeyPressed

    private void TKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKontrolKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TPenilaian, TDelusi);
        }
    }//GEN-LAST:event_TKontrolKeyPressed

    private void tbTesPsikoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTesPsikoMouseClicked
        if (tabMode10.getRowCount() != 0) {
            try {
                getDataTesPsiko();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTesPsikoMouseClicked

    private void tbTesPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTesPsikoKeyPressed
        if (tabMode10.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTesPsiko();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTesPsikoKeyPressed

    private void TTesPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTesPsikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, tgl_rencana, TWaktu);
        }
    }//GEN-LAST:event_TTesPsikoKeyPressed

    private void TWaktuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TWaktuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TTesPsiko, TTester);
        }
    }//GEN-LAST:event_TWaktuKeyPressed

    private void TTesterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTesterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TWaktu, tgl_rencana);
        }
    }//GEN-LAST:event_TTesterKeyPressed

    private void tbManifestasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbManifestasiMouseClicked
        if (tabMode11.getRowCount() != 0) {
            try {
                getDataManifes();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbManifestasiMouseClicked

    private void tbManifestasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbManifestasiKeyPressed
        if (tabMode11.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataManifes();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbManifestasiKeyPressed

    private void tbDiagnosisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosisMouseClicked
        if (tabMode12.getRowCount() != 0) {
            try {
                getDataDiagnosis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDiagnosisMouseClicked

    private void tbDiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosisKeyPressed
        if (tabMode12.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDiagnosis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagnosisKeyPressed

    private void TDiagUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagUtamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TKesan, TDiagBanding);
        }
    }//GEN-LAST:event_TDiagUtamaKeyPressed

    private void TDiagBandingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagBandingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TDiagBanding, TKesan);
        }
    }//GEN-LAST:event_TDiagBandingKeyPressed

    private void btnPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyakitActionPerformed
        pilihan = 1;
        akses.setform("DlgRekamPsikologisDewasa");
        penyakit.isCek();
        penyakit.TCari.requestFocus();
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnPenyakitActionPerformed

    private void btnPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyakit1ActionPerformed
        pilihan = 2;
        akses.setform("DlgRekamPsikologisDewasa");
        penyakit.isCek();
        penyakit.TCari.requestFocus();
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnPenyakit1ActionPerformed

    private void tbPrognosisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPrognosisMouseClicked
        if (tabMode13.getRowCount() != 0) {
            try {
                getDataPrognosis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPrognosisMouseClicked

    private void tbPrognosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPrognosisKeyPressed
        if (tabMode13.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPrognosis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPrognosisKeyPressed

    private void tbTritmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTritmenMouseClicked
        if (tabMode15.getRowCount() != 0) {
            try {
                getDataTritmen();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTritmenMouseClicked

    private void tbTritmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTritmenKeyPressed
        if (tabMode15.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTritmen();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTritmenKeyPressed

    private void tbItemTritmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemTritmenMouseClicked
        if (tabMode14.getRowCount() != 0) {
            try {
                getItemTritmen();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemTritmenMouseClicked

    private void tbItemTritmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemTritmenKeyPressed
        if (tabMode14.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getItemTritmen();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemTritmenKeyPressed

    private void BtnPilihTritmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPilihTritmenActionPerformed
        kdtritmen.setText("");
        nmtritmen.setText("");
        tritmenLain.setText("");
        BtnSimpan3.setEnabled(true);
        BtnEdit2.setEnabled(false);

        WindowDataTritmen.setSize(660, 137);
        WindowDataTritmen.setLocationRelativeTo(internalFrame1);
        WindowDataTritmen.setVisible(true);
        WindowDataTritmen.requestFocus();
    }//GEN-LAST:event_BtnPilihTritmenActionPerformed

    private void BtnHapusItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusItem1ActionPerformed
        if (tabMode14.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Jenis rencana tritmen yang dipilih/ditentukan belum ada...!!");
            BtnPilihTritmen.requestFocus();
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data rencana tritmen yang dipilih pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else if (tbItemTritmen.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rencana tritmennya pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else {
            tabMode14.removeRow(tbItemTritmen.getSelectedRow());
            kdtritmen.setText("");
            nmtritmen.setText("");
            tritmenLain.setText("");
            BtnHapusItem1.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusItem1ActionPerformed

    private void BtnPerbaikiItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerbaikiItem1ActionPerformed
        if (tabMode14.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Rencana tritmen yang dipilih/ditentukan belum ada...!!");
            BtnPilihTritmen.requestFocus();
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data rencana tritmen yang dipilih pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else if (tbItemTritmen.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rencana tritmennya pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else {
            BtnSimpan3.setEnabled(false);
            BtnEdit2.setEnabled(true);

            WindowDataTritmen.setSize(660, 137);
            WindowDataTritmen.setLocationRelativeTo(internalFrame1);
            WindowDataTritmen.setVisible(true);
            WindowDataTritmen.requestFocus();
        }
    }//GEN-LAST:event_BtnPerbaikiItem1ActionPerformed

    private void btnTritmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTritmenActionPerformed
        akses.setform("DlgRekamPsikologisDewasa");
        tritmen.setSize(622, internalFrame1.getHeight() - 40);
        tritmen.setLocationRelativeTo(internalFrame1);
        tritmen.isCek();
        tritmen.emptTeks();
        tritmen.setVisible(true);
        tritmen.TCari.requestFocus();
    }//GEN-LAST:event_btnTritmenActionPerformed

    private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
        if (kdtritmen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis rencana tritmennya...!!!!");
        } else {
            data = 0;
            for (i = 0; i < tbItemTritmen.getRowCount(); i++) {
                if (tbItemTritmen.getValueAt(i, 0).toString().equals(kdtritmen.getText())) {
                    data++;
                }
            }
           
            if (data == 0) {
                if (kdtritmen.getText().equals("RT0025")) {
                    if (tritmenLain.getText().length() < 2) {
                        JOptionPane.showMessageDialog(null, "Nama rencana tritmen lainnya harus diisi dulu...!!!!");
                        tritmenLain.requestFocus();
                    } else {
                        tabMode14.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                        kdtritmen.setText("");
                        nmtritmen.setText("");
                        tritmenLain.setText("");
                        btnTritmen.requestFocus();
                    }

                } else {
                    tabMode14.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                    kdtritmen.setText("");
                    nmtritmen.setText("");
                    tritmenLain.setText("");
                    btnTritmen.requestFocus();
                }
            } else if (data >= 1) {
                JOptionPane.showMessageDialog(null, "Nama rencana tritmen tersebut sudah ada dipilih...!!!!");
                btnTritmen.requestFocus();
            }            
        }
    }//GEN-LAST:event_BtnSimpan3ActionPerformed

    private void BtnEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit2ActionPerformed
        if (kdtritmen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis rencana tritmennya...!!!!");
        } else {
            data = 0;
            for (i = 0; i < tbItemTritmen.getRowCount(); i++) {
                if (tbItemTritmen.getValueAt(i, 0).toString().equals(kdtritmen.getText())) {
                    data++;
                }
            }
          
            if (data == 0) {
                if (kdtritmen.getText().equals("RT0025")) {
                    if (tritmenLain.getText().length() < 2) {
                        JOptionPane.showMessageDialog(null, "Nama rencana tritmen lainnya harus diisi dulu...!!!!");
                        tritmenLain.requestFocus();
                    } else {
                        tabMode14.removeRow(tbItemTritmen.getSelectedRow());
                        tabMode14.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                        BtnCloseIn4ActionPerformed(null);
                    }
                } else {
                    tabMode14.removeRow(tbItemTritmen.getSelectedRow());
                    tabMode14.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                    BtnCloseIn4ActionPerformed(null);
                }
            } else if (data >= 1) {
                JOptionPane.showMessageDialog(null, "Nama rencana tritmen tersebut sudah ada dipilih...!!!!");
                btnTritmen.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnEdit2ActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        kdtritmen.setText("");
        nmtritmen.setText("");
        tritmenLain.setText("");
        WindowDataTritmen.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void TPertemuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPertemuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, BtnPilihTritmen, TCatatan);
        }
    }//GEN-LAST:event_TPertemuanKeyPressed

    private void TCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TPertemuan, BtnPilihTritmen);
        }
    }//GEN-LAST:event_TCatatanKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgRekamPsikologisDewasa");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void TKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TketPas.requestFocus();
        }
    }//GEN-LAST:event_TKeluhanKeyPressed

    private void TketPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TalamatPas.requestFocus();
        }
    }//GEN-LAST:event_TketPasKeyPressed

    private void TalamatPasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatPasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TnmPas.requestFocus();
        }
    }//GEN-LAST:event_TalamatPasKeyPressed

    private void TPermasalahansaatIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPermasalahansaatIniKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TAlasanMencariBantuan.requestFocus();
        }
    }//GEN-LAST:event_TPermasalahansaatIniKeyPressed

    private void TAlasanMencariBantuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanMencariBantuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TAlasan.requestFocus();
        }
    }//GEN-LAST:event_TAlasanMencariBantuanKeyPressed

    private void TAlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            THarapan.requestFocus();
        }
    }//GEN-LAST:event_TAlasanKeyPressed

    private void THarapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THarapanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPerubahanDiriSendiri.requestFocus();
        }
    }//GEN-LAST:event_THarapanKeyPressed

    private void TPerubahanDiriSendiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPerubahanDiriSendiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPerubahanKeluarga.requestFocus();
        }
    }//GEN-LAST:event_TPerubahanDiriSendiriKeyPressed

    private void TPerubahanKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPerubahanKeluargaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPermasalahansaatIni.requestFocus();
        }
    }//GEN-LAST:event_TPerubahanKeluargaKeyPressed

    private void TMasaKanakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMasaKanakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TMasaRemaja.requestFocus();
        }
    }//GEN-LAST:event_TMasaKanakKeyPressed

    private void TMasaRemajaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMasaRemajaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TMasaDewasa.requestFocus();
        }
    }//GEN-LAST:event_TMasaRemajaKeyPressed

    private void TMasaDewasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMasaDewasaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRiwayatFisik.requestFocus();
        }
    }//GEN-LAST:event_TMasaDewasaKeyPressed

    private void TRiwayatFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRiwayatFisikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRiwayatPengobatan.requestFocus();
        }
    }//GEN-LAST:event_TRiwayatFisikKeyPressed

    private void TRiwayatPengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRiwayatPengobatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TMasaKanak.requestFocus();
        }
    }//GEN-LAST:event_TRiwayatPengobatanKeyPressed

    private void TKesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagUtama.requestFocus();
        }
    }//GEN-LAST:event_TKesanKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRekamPsikologisDewasa dialog = new DlgRekamPsikologisDewasa(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnEdit2;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusItem;
    private widget.Button BtnHapusItem1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerbaikiItem;
    private widget.Button BtnPerbaikiItem1;
    private widget.Button BtnPilihKeluhan;
    private widget.Button BtnPilihTritmen;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpan3;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput5;
    private widget.PanelBiasa FormInput6;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.RadioButton RKurangSerius;
    private widget.RadioButton RSangatSerius;
    private widget.RadioButton RSerius;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll14;
    private widget.ScrollPane Scroll15;
    private widget.ScrollPane Scroll16;
    private widget.ScrollPane Scroll17;
    private widget.ScrollPane Scroll18;
    private widget.ScrollPane Scroll19;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll20;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll22;
    private widget.ScrollPane Scroll23;
    private widget.ScrollPane Scroll24;
    private widget.ScrollPane Scroll25;
    private widget.ScrollPane Scroll26;
    private widget.ScrollPane Scroll27;
    private widget.ScrollPane Scroll28;
    private widget.ScrollPane Scroll29;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll30;
    private widget.ScrollPane Scroll31;
    private widget.ScrollPane Scroll32;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll34;
    private widget.ScrollPane Scroll35;
    private widget.ScrollPane Scroll37;
    private widget.ScrollPane Scroll39;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll40;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextBox TAfek;
    private widget.TextArea TAlamat;
    private widget.TextArea TAlasan;
    private widget.TextArea TAlasanMencariBantuan;
    private widget.TextBox TCari;
    private widget.TextBox TCatatan;
    private widget.TextBox TDelusi;
    private widget.TextBox TDiagBanding;
    private widget.TextBox TDiagUtama;
    private widget.TextBox TEkspresi;
    private widget.TextBox TFungsiIntelek;
    private widget.TextBox TFungsiUmum;
    private widget.TextBox THalusinasi;
    private widget.TextArea THarapan;
    private widget.TextBox TInsight;
    private widget.TextArea TKeluhan;
    private widget.TextBox TKesadaran;
    private widget.TextArea TKesan;
    private widget.TextBox TKontrol;
    private widget.TextBox TLain;
    private widget.TextArea TManifes;
    private widget.TextArea TMasaDewasa;
    private widget.TextArea TMasaKanak;
    private widget.TextArea TMasaRemaja;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TOrenRuang;
    private widget.TextBox TOrenTempat;
    private widget.TextBox TOrenWaktu;
    private widget.TextBox TPasien;
    private widget.TextBox TPekerjaan;
    private widget.TextBox TPenampilan;
    private widget.TextBox TPendidikan;
    private widget.TextBox TPengalaman;
    private widget.TextBox TPenilaian;
    private widget.TextBox TPerasaan;
    private widget.TextBox TPerhatian;
    private widget.TextArea TPermasalahansaatIni;
    private widget.TextBox TPertemuan;
    private widget.TextArea TPerubahanDiriSendiri;
    private widget.TextArea TPerubahanKeluarga;
    private widget.TextBox TProses;
    private widget.TextArea TRiwayatFisik;
    private widget.TextArea TRiwayatPengobatan;
    private widget.TextBox TSttsKawin;
    private widget.TextBox TTesPsiko;
    private widget.TextBox TTester;
    private widget.TextBox TTingkahLaku;
    private widget.TextBox TWaktu;
    private javax.swing.JTabbedPane TabPsikologis;
    private widget.TextArea TalamatPas;
    private widget.TextBox TanakKe;
    private widget.TextBox TdariKe;
    private widget.TextBox Tjk;
    private widget.TextBox TkawinKe;
    private widget.TextBox TketKel;
    private widget.TextArea TketPas;
    private widget.TextBox TnmKel;
    private widget.TextBox TnmPas;
    private widget.TextBox TnmPsikolog;
    private widget.TextBox TnoHpPas;
    private widget.TextBox TnoTelp;
    private widget.TextBox TpekerjaanPas;
    private widget.TextBox TposisiKel;
    private widget.TextArea Tprognosis;
    private widget.TextBox TtahunKe;
    private widget.TextBox Ttl;
    private widget.TextBox TumurKel;
    private widget.TextBox TumurPas;
    private javax.swing.JDialog WindowDataKeluhan;
    private javax.swing.JDialog WindowDataTritmen;
    private widget.Button btnKeluhan;
    private widget.Button btnPenyakit;
    private widget.Button btnPenyakit1;
    private widget.Button btnPetugas;
    private widget.Button btnTritmen;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbAgama;
    private widget.ComboBox cmbJK;
    private widget.ComboBox cmbLimit;
    private widget.ComboBox cmbPnd;
    private widget.ComboBox cmbUmur;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel39;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private widget.TextBox kdkeluhan;
    private widget.TextBox kdtritmen;
    private widget.TextBox masalahLain;
    private widget.TextBox nmkeluhan;
    private widget.TextBox nmtritmen;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass22;
    private widget.panelisi panelGlass23;
    private widget.panelisi panelGlass24;
    private widget.panelisi panelGlass26;
    private widget.panelisi panelGlass27;
    private widget.panelisi panelGlass28;
    private widget.panelisi panelGlass29;
    private widget.panelisi panelGlass30;
    private widget.panelisi panelGlass31;
    private widget.panelisi panelGlass32;
    private widget.panelisi panelGlass33;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbAnggotaKel;
    private widget.Table tbDiagnosis;
    private widget.Table tbIdentitasPas;
    private widget.Table tbItemMasalah;
    private widget.Table tbItemTritmen;
    private widget.Table tbKeluhan;
    private widget.Table tbKondisiPsiko;
    private widget.Table tbManifestasi;
    private widget.Table tbObservasi;
    private widget.Table tbPrognosis;
    private widget.Table tbPsikologiKlinis;
    private widget.Table tbRiwayatHidup;
    private widget.Table tbTesPsiko;
    private widget.Table tbTritmen;
    private widget.Table tbdatadiri;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.TextBox tgl_datang;
    private widget.Tanggal tgl_rencana;
    private widget.TextBox tritmenLain;
    // End of variables declaration//GEN-END:variables

    public void isCek() {
        BtnSimpan.setEnabled(akses.getrekam_psikologis());
        BtnHapus.setEnabled(akses.getrekam_psikologis());
        BtnEdit.setEnabled(akses.getrekam_psikologis());
        BtnPrint.setEnabled(akses.getrekam_psikologis());
     }

    public void setNoRm(String norw, Date tanggal) {
        TNoRW.setText(norw);
        tgl1.setDate(tanggal);        
        isDataDiri();
        TabPsikologis.setSelectedIndex(0);
    }
    
    private void isDataDiri() {
        tglKedatangan = "";
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                    + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                    + "p.pekerjaan, p.pnd, p.stts_nikah, p.agama, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, p.no_tlp, "
                    + "date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, d.nm_dokter FROM reg_periksa rp "
                    + "INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel "
                    + "INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab WHERE rp.no_rawat = '" + TNoRW.getText() + "'");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    tglKedatangan = rs.getString("tgl_registrasi");
                    tgl_datang.setText(rs.getString("tgl_dtg"));
                    Tjk.setText(rs.getString("jk"));
                    TnmPsikolog.setText(rs.getString("nm_dokter"));
                    Ttl.setText(rs.getString("ttl"));
                    TPekerjaan.setText(rs.getString("pekerjaan"));
                    TPendidikan.setText(rs.getString("pnd"));
                    TSttsKawin.setText(rs.getString("stts_nikah"));
                    TAlamat.setText(rs.getString("almt"));
                    TnoTelp.setText(rs.getString("no_tlp"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilDataDiri() {
        Valid.tabelKosong(tabMode1);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                        + "p.pekerjaan, p.pnd, p.stts_nikah, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, p.no_tlp FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab WHERE "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and rp.no_rawat like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and if(p.jk = 'L','Laki-laki','Perempuan') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.pekerjaan like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.pnd like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.stts_nikah like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.no_tlp like ? order by rp.tgl_registrasi desc");
            } else {
                ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                        + "p.pekerjaan, p.pnd, p.stts_nikah, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, p.no_tlp FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab WHERE "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and rp.no_rawat like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and if(p.jk = 'L','Laki-laki','Perempuan') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.pekerjaan like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.pnd like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.stts_nikah like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar>=18 and rp.sttsumur='Th' "
                        + "and pd.rekam_psikologis='umum_dewasa' and p.no_tlp like ? order by rp.tgl_registrasi desc LIMIT " + cmbLimit.getSelectedItem().toString() + "");
            }
            try {
                ps1.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(3, "%" + TCari.getText().trim() + "%");                
                ps1.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(6, "%" + TCari.getText().trim() + "%");                
                ps1.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(9, "%" + TCari.getText().trim() + "%");                
                ps1.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(12, "%" + TCari.getText().trim() + "%");                
                ps1.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(15, "%" + TCari.getText().trim() + "%");                
                ps1.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(18, "%" + TCari.getText().trim() + "%");                
                ps1.setString(19, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(20, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(21, "%" + TCari.getText().trim() + "%");                
                ps1.setString(22, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(23, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(24, "%" + TCari.getText().trim() + "%");                
                ps1.setString(25, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(26, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(27, "%" + TCari.getText().trim() + "%");                
                ps1.setString(28, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(29, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(30, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("tgl_registrasi"),
                        rs1.getString("no_rkm_medis"),                        
                        rs1.getString("tgl_dtg"),
                        rs1.getString("nm_pasien"),
                        rs1.getString("jk"),
                        rs1.getString("ttl"),
                        rs1.getString("pekerjaan"),
                        rs1.getString("pnd"),
                        rs1.getString("stts_nikah"),
                        rs1.getString("almt"),
                        rs1.getString("no_tlp")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode1.getRowCount());
    }
    
    private void tampilAnggotaKeluarga() {
        Valid.tabelKosong(tabMode2);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pak.nama, concat(pak.umur,' ',pak.sttsumur) umur, "
                        + "if(pak.jk='L','Laki-laki','Perempuan') jk, pak.posisi, pak.keterangan, pak.waktu_simpan, pak.umur usia, "
                        + "pak.sttsumur from psikolog_anggota_keluarga pak inner join pasien p on p.no_rkm_medis =pak.no_rkm_medis WHERE "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='umum_dewasa' and pak.nama like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='umum_dewasa' and if(pak.jk='L','Laki-laki','Perempuan') like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='umum_dewasa' and pak.posisi like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='umum_dewasa' and pak.keterangan like ? order by pak.waktu_simpan desc");
            } else {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pak.nama, concat(pak.umur,' ',pak.sttsumur) umur, "
                        + "if(pak.jk='L','Laki-laki','Perempuan') jk, pak.posisi, pak.keterangan, pak.waktu_simpan, pak.umur usia, "
                        + "pak.sttsumur from psikolog_anggota_keluarga pak inner join pasien p on p.no_rkm_medis =pak.no_rkm_medis WHERE "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='umum_dewasa' and pak.nama like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='umum_dewasa' and if(pak.jk='L','Laki-laki','Perempuan') like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='umum_dewasa' and pak.posisi like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='umum_dewasa' and pak.keterangan like ? order by pak.waktu_simpan desc "
                        + "LIMIT " + cmbLimit.getSelectedItem().toString() + "");
            }
            try {
                ps2.setString(1, "%" + TCari.getText().trim() + "%");
                ps2.setString(2, "%" + TCari.getText().trim() + "%");
                ps2.setString(3, "%" + TCari.getText().trim() + "%");
                ps2.setString(4, "%" + TCari.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),
                        rs2.getString("nama"),
                        rs2.getString("umur"),
                        rs2.getString("jk"),
                        rs2.getString("posisi"),
                        rs2.getString("keterangan"),
                        rs2.getString("waktu_simpan"),
                        rs2.getString("usia"),
                        rs2.getString("sttsumur")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode2.getRowCount());
    }
    
    private void tampilIdentitasPasangan() {
        Valid.tabelKosong(tabMode3);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pu.nama, concat(pu.umur,' tahun') usia, pu.pendidikan, pu.pekerjaan, pu.agama, "
                        + "CONCAT(pu.anak_ke,' dari ',pu.dari_anak,' bersaudara') anak, CONCAT(pu.perkawinan_ke,' dari ',pu.dari_kawin,' tahun') kawin, "
                        + "pu.keluhan, pu.alamat, pu.no_hp, pu.keterangan, pu.anak_ke, pu.dari_anak, pu.perkawinan_ke, pu.dari_kawin, "
                        + "pu.waktu_simpan, pu.umur FROM psikolog_umum_dewasa_identitas_pas pu INNER JOIN pasien p ON p.no_rkm_medis = pu.no_rkm_medis WHERE "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.nm_pasien like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.nama like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.umur like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.pendidikan like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.pekerjaan like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.agama like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and CONCAT(pu.anak_ke,' dari ',pu.dari_anak,' bersaudara') like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and CONCAT(pu.perkawinan_ke,' dari ',pu.dari_kawin,' tahun') like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.keluhan like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.alamat like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.no_hp like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.keterangan like ? order by pu.waktu_simpan desc");
            } else {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pu.nama, concat(pu.umur,' tahun') usia, pu.pendidikan, pu.pekerjaan, pu.agama, "
                        + "CONCAT(pu.anak_ke,' dari ',pu.dari_anak,' bersaudara') anak, CONCAT(pu.perkawinan_ke,' dari ',pu.dari_kawin,' tahun') kawin, "
                        + "pu.keluhan, pu.alamat, pu.no_hp, pu.keterangan, pu.anak_ke, pu.dari_anak, pu.perkawinan_ke, pu.dari_kawin, "
                        + "pu.waktu_simpan, pu.umur FROM psikolog_umum_dewasa_identitas_pas pu INNER JOIN pasien p ON p.no_rkm_medis = pu.no_rkm_medis WHERE "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.nm_pasien like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.nama like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.umur like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.pendidikan like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.pekerjaan like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.agama like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and CONCAT(pu.anak_ke,' dari ',pu.dari_anak,' bersaudara') like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and CONCAT(pu.perkawinan_ke,' dari ',pu.dari_kawin,' tahun') like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.keluhan like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.alamat like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.no_hp like ? or "
                        + "pu.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and pu.keterangan like ? order by pu.waktu_simpan desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps3.setString(1, "%" + TCari.getText().trim() + "%");
                ps3.setString(2, "%" + TCari.getText().trim() + "%");
                ps3.setString(3, "%" + TCari.getText().trim() + "%");
                ps3.setString(4, "%" + TCari.getText().trim() + "%");
                ps3.setString(5, "%" + TCari.getText().trim() + "%");
                ps3.setString(6, "%" + TCari.getText().trim() + "%");
                ps3.setString(7, "%" + TCari.getText().trim() + "%");
                ps3.setString(8, "%" + TCari.getText().trim() + "%");
                ps3.setString(9, "%" + TCari.getText().trim() + "%");
                ps3.setString(10, "%" + TCari.getText().trim() + "%");
                ps3.setString(11, "%" + TCari.getText().trim() + "%");
                ps3.setString(12, "%" + TCari.getText().trim() + "%");
                ps3.setString(13, "%" + TCari.getText().trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode3.addRow(new Object[]{
                        rs3.getString("no_rkm_medis"),
                        rs3.getString("nm_pasien"),
                        rs3.getString("nama"),
                        rs3.getString("usia"),
                        rs3.getString("pendidikan"),
                        rs3.getString("pekerjaan"),
                        rs3.getString("agama"),
                        rs3.getString("anak"),
                        rs3.getString("kawin"),
                        rs3.getString("keluhan"),
                        rs3.getString("alamat"),
                        rs3.getString("no_hp"),
                        rs3.getString("keterangan"),
                        rs3.getString("anak_ke"),
                        rs3.getString("dari_anak"),
                        rs3.getString("perkawinan_ke"),
                        rs3.getString("dari_kawin"),
                        rs3.getString("waktu_simpan"),
                        rs3.getString("umur")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode3.getRowCount());
    }
    
    private void tampilKeluhanMasalah() {
        Valid.tabelKosong(tabMode5);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps4 = koneksi.prepareStatement("SELECT pk.no_rawat, pk.waktu_simpan, p.no_rkm_medis, p.nm_pasien, "
                        + "pk.permasalahan FROM psikolog_keluhan pk INNER JOIN reg_periksa rp ON rp.no_rawat=pk.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pk.no_rawat WHERE "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pk.no_rawat like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pk.permasalahan like ? "
                        + "order by pk.waktu_simpan desc");
            } else {
                ps4 = koneksi.prepareStatement("SELECT pk.no_rawat, pk.waktu_simpan, p.no_rkm_medis, p.nm_pasien, "
                        + "pk.permasalahan FROM psikolog_keluhan pk INNER JOIN reg_periksa rp ON rp.no_rawat=pk.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pk.no_rawat WHERE "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pk.no_rawat like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pk.permasalahan like ? "
                        + "order by pk.waktu_simpan desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps4.setString(1, "%" + TCari.getText().trim() + "%");
                ps4.setString(2, "%" + TCari.getText().trim() + "%");
                ps4.setString(3, "%" + TCari.getText().trim() + "%");
                ps4.setString(4, "%" + TCari.getText().trim() + "%");
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode5.addRow(new Object[]{
                        rs4.getString("no_rawat"),
                        rs4.getString("waktu_simpan"),
                        rs4.getString("no_rkm_medis"),
                        rs4.getString("nm_pasien"),
                        rs4.getString("permasalahan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode5.getRowCount());
    }
    
    private void tampilPsikologiKlinis() {
        Valid.tabelKosong(tabMode6);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps6 = koneksi.prepareStatement("SELECT pw.no_rawat, p.no_rkm_medis, p.nm_pasien, pw.permasalahan_saat_ini, pw.alasan_mencari_bantuan, "
                        + "pw.permasalahan_dinilai, pw.alasan_permasalahan_dinilai, pw.harapan, pw.perubahan_diinginkan_diri_sendiri, "
                        + "pw.perubahan_diinginkan_keluarga FROM psikolog_wawancara_klinis pw "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pw.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.no_rawat like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and p.no_rkm_medis like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and p.nm_pasien like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.permasalahan_saat_ini like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.alasan_mencari_bantuan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.permasalahan_dinilai like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.alasan_permasalahan_dinilai like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.harapan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.perubahan_diinginkan_diri_sendiri like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.perubahan_diinginkan_keluarga like ? order by pw.no_rawat desc");
            } else {
                ps6 = koneksi.prepareStatement("SELECT pw.no_rawat, p.no_rkm_medis, p.nm_pasien, pw.permasalahan_saat_ini, pw.alasan_mencari_bantuan, "
                        + "pw.permasalahan_dinilai, pw.alasan_permasalahan_dinilai, pw.harapan, pw.perubahan_diinginkan_diri_sendiri, "
                        + "pw.perubahan_diinginkan_keluarga FROM psikolog_wawancara_klinis pw "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pw.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.no_rawat like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and p.no_rkm_medis like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and p.nm_pasien like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.permasalahan_saat_ini like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.alasan_mencari_bantuan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.permasalahan_dinilai like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.alasan_permasalahan_dinilai like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.harapan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.perubahan_diinginkan_diri_sendiri like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Umum/Dewasa' and pw.perubahan_diinginkan_keluarga like ? "
                        + "order by pw.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps6.setString(1, "%" + TCari.getText().trim() + "%");
                ps6.setString(2, "%" + TCari.getText().trim() + "%");
                ps6.setString(3, "%" + TCari.getText().trim() + "%");
                ps6.setString(4, "%" + TCari.getText().trim() + "%");                
                ps6.setString(5, "%" + TCari.getText().trim() + "%");
                ps6.setString(6, "%" + TCari.getText().trim() + "%");
                ps6.setString(7, "%" + TCari.getText().trim() + "%");
                ps6.setString(8, "%" + TCari.getText().trim() + "%");
                ps6.setString(9, "%" + TCari.getText().trim() + "%");
                ps6.setString(10, "%" + TCari.getText().trim() + "%");
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode6.addRow(new Object[]{
                        rs6.getString("no_rawat"),
                        rs6.getString("no_rkm_medis"),
                        rs6.getString("nm_pasien"),
                        rs6.getString("permasalahan_saat_ini"),
                        rs6.getString("alasan_mencari_bantuan"),
                        rs6.getString("permasalahan_dinilai"),
                        rs6.getString("alasan_permasalahan_dinilai"),
                        rs6.getString("harapan"),
                        rs6.getString("perubahan_diinginkan_diri_sendiri"),
                        rs6.getString("perubahan_diinginkan_keluarga")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode6.getRowCount());
    }
    
    private void tampilRiwayatHidup() {
        Valid.tabelKosong(tabMode7);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps7 = koneksi.prepareStatement("SELECT pu.no_rawat, p.no_rkm_medis, p.nm_pasien, pu.masa_kanak, pu.masa_remaja, pu.masa_dewasa, "
                        + "pu.riw_penyakit_fisik, pu.riw_pengobatan FROM psikolog_umum_dewasa_riw_hidup pu "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pu.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.no_rawat like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.masa_kanak like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.masa_remaja like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.masa_dewasa like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.riw_penyakit_fisik like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.riw_pengobatan like ? order by pu.no_rawat desc");
            } else {
                ps7 = koneksi.prepareStatement("SELECT pu.no_rawat, p.no_rkm_medis, p.nm_pasien, pu.masa_kanak, pu.masa_remaja, pu.masa_dewasa, "
                        + "pu.riw_penyakit_fisik, pu.riw_pengobatan FROM psikolog_umum_dewasa_riw_hidup pu "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pu.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.no_rawat like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.masa_kanak like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.masa_remaja like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.masa_dewasa like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.riw_penyakit_fisik like ? or "
                        + "pu.no_rawat LIKE '%" + TNoRW.getText() + "%' and pu.riw_pengobatan like ? order by pu.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps7.setString(1, "%" + TCari.getText().trim() + "%");
                ps7.setString(2, "%" + TCari.getText().trim() + "%");
                ps7.setString(3, "%" + TCari.getText().trim() + "%");
                ps7.setString(4, "%" + TCari.getText().trim() + "%");                
                ps7.setString(5, "%" + TCari.getText().trim() + "%");
                ps7.setString(6, "%" + TCari.getText().trim() + "%");
                ps7.setString(7, "%" + TCari.getText().trim() + "%");
                ps7.setString(8, "%" + TCari.getText().trim() + "%");
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    tabMode7.addRow(new Object[]{
                        rs7.getString("no_rawat"),
                        rs7.getString("no_rkm_medis"),
                        rs7.getString("nm_pasien"),
                        rs7.getString("masa_kanak"),
                        rs7.getString("masa_remaja"),
                        rs7.getString("masa_dewasa"),
                        rs7.getString("riw_penyakit_fisik"),
                        rs7.getString("riw_pengobatan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode7.getRowCount());
    }
    
    private void tampilObservasi() {
        Valid.tabelKosong(tabMode8);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps8 = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien, po.penampilan, po.ekspresi_wajah, po.perasaan_suasana_hati, "
                        + "po.tingkah_laku, po.fungsi_umum, po.fungsi_intelektual, po.pengalaman_kerja, po.lainlain FROM psikolog_observasi po "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.no_rawat like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.penampilan like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.ekspresi_wajah like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.perasaan_suasana_hati like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.tingkah_laku like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.fungsi_umum like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.fungsi_intelektual like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.pengalaman_kerja like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.lainlain like ? order by po.no_rawat desc");
            } else {
                ps8 = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien, po.penampilan, po.ekspresi_wajah, po.perasaan_suasana_hati, "
                        + "po.tingkah_laku, po.fungsi_umum, po.fungsi_intelektual, po.pengalaman_kerja, po.lainlain FROM psikolog_observasi po "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.no_rawat like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.penampilan like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.ekspresi_wajah like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.perasaan_suasana_hati like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.tingkah_laku like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.fungsi_umum like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.fungsi_intelektual like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.pengalaman_kerja like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='umum_dewasa' and po.lainlain like ? "
                        + "order by po.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps8.setString(1, "%" + TCari.getText().trim() + "%");
                ps8.setString(2, "%" + TCari.getText().trim() + "%");
                ps8.setString(3, "%" + TCari.getText().trim() + "%");
                ps8.setString(4, "%" + TCari.getText().trim() + "%");                
                ps8.setString(5, "%" + TCari.getText().trim() + "%");
                ps8.setString(6, "%" + TCari.getText().trim() + "%");
                ps8.setString(7, "%" + TCari.getText().trim() + "%");
                ps8.setString(8, "%" + TCari.getText().trim() + "%");                
                ps8.setString(9, "%" + TCari.getText().trim() + "%");
                ps8.setString(10, "%" + TCari.getText().trim() + "%");
                ps8.setString(11, "%" + TCari.getText().trim() + "%");
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    tabMode8.addRow(new Object[]{
                        rs8.getString("no_rawat"),
                        rs8.getString("no_rkm_medis"),
                        rs8.getString("nm_pasien"),
                        rs8.getString("penampilan"),
                        rs8.getString("ekspresi_wajah"),
                        rs8.getString("perasaan_suasana_hati"),
                        rs8.getString("tingkah_laku"),
                        rs8.getString("fungsi_umum"),                        
                        rs8.getString("fungsi_intelektual"),
                        rs8.getString("pengalaman_kerja"),
                        rs8.getString("lainlain")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode8.getRowCount());
    }
    
    private void tampilKondisiPsi() {
        Valid.tabelKosong(tabMode9);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps9 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.delusi_waham, pp.proses_pikiran, pp.halusinasi, "
                        + "pp.afek, pp.insight, pp.kesadaran, pp.orientasi_waktu, pp.orientasi_tempat, pp.orientasi_ruang, "
                        + "pp.perhatian, pp.penilaian, pp.kontrol_thdp_impuls FROM psikolog_umum_dewasa_psikopatologis pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.delusi_waham like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.proses_pikiran like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.halusinasi like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.afek like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.insight like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.kesadaran like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.orientasi_waktu like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.orientasi_tempat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.orientasi_ruang like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.perhatian like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.penilaian like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.kontrol_thdp_impuls like ? order by pp.no_rawat desc");
            } else {
                ps9 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.delusi_waham, pp.proses_pikiran, pp.halusinasi, "
                        + "pp.afek, pp.insight, pp.kesadaran, pp.orientasi_waktu, pp.orientasi_tempat, pp.orientasi_ruang, "
                        + "pp.perhatian, pp.penilaian, pp.kontrol_thdp_impuls FROM psikolog_umum_dewasa_psikopatologis pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.delusi_waham like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.proses_pikiran like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.halusinasi like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.afek like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.insight like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.kesadaran like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.orientasi_waktu like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.orientasi_tempat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.orientasi_ruang like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.perhatian like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.penilaian like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.kontrol_thdp_impuls like ? order by pp.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps9.setString(1, "%" + TCari.getText().trim() + "%");
                ps9.setString(2, "%" + TCari.getText().trim() + "%");
                ps9.setString(3, "%" + TCari.getText().trim() + "%");
                ps9.setString(4, "%" + TCari.getText().trim() + "%");                
                ps9.setString(5, "%" + TCari.getText().trim() + "%");
                ps9.setString(6, "%" + TCari.getText().trim() + "%");
                ps9.setString(7, "%" + TCari.getText().trim() + "%");
                ps9.setString(8, "%" + TCari.getText().trim() + "%");                
                ps9.setString(9, "%" + TCari.getText().trim() + "%");
                ps9.setString(10, "%" + TCari.getText().trim() + "%");
                ps9.setString(11, "%" + TCari.getText().trim() + "%");                
                ps9.setString(12, "%" + TCari.getText().trim() + "%");
                ps9.setString(13, "%" + TCari.getText().trim() + "%");
                ps9.setString(14, "%" + TCari.getText().trim() + "%");
                ps9.setString(15, "%" + TCari.getText().trim() + "%");
                rs9 = ps9.executeQuery();
                while (rs9.next()) {
                    tabMode9.addRow(new Object[]{
                        rs9.getString("no_rawat"),
                        rs9.getString("no_rkm_medis"),
                        rs9.getString("nm_pasien"),                        
                        rs9.getString("delusi_waham"),
                        rs9.getString("proses_pikiran"),
                        rs9.getString("halusinasi"),
                        rs9.getString("afek"),
                        rs9.getString("insight"),                        
                        rs9.getString("kesadaran"),
                        rs9.getString("orientasi_waktu"),
                        rs9.getString("orientasi_tempat"),                        
                        rs9.getString("orientasi_ruang"),
                        rs9.getString("perhatian"),
                        rs9.getString("penilaian"),
                        rs9.getString("kontrol_thdp_impuls")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs9 != null) {
                    rs9.close();
                }
                if (ps9 != null) {
                    ps9.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode9.getRowCount());
    }
    
    private void tampilTesPsikologi() {
        Valid.tabelKosong(tabMode10);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps10 = koneksi.prepareStatement("SELECT pt.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(pt.rencana_tes,'%d-%m-%Y') tglRencana, "
                        + "pt.tes_psikologis, pt.waktu, pt.tester, pt.rencana_tes FROM psikolog_tes_psikologi pt "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pt.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and pt.no_rawat like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and pt.tes_psikologis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and pt.waktu like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and pt.tester like ? "
                        + "order by pt.no_rawat desc");
            } else {
                ps10 = koneksi.prepareStatement("SELECT pt.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(pt.rencana_tes,'%d-%m-%Y') tglRencana, "
                        + "pt.tes_psikologis, pt.waktu, pt.tester, pt.rencana_tes FROM psikolog_tes_psikologi pt "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pt.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and pt.no_rawat like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and pt.tes_psikologis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and pt.waktu like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='umum_dewasa' and pt.tester like ? "
                        + "order by pt.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps10.setString(1, "%" + TCari.getText().trim() + "%");
                ps10.setString(2, "%" + TCari.getText().trim() + "%");
                ps10.setString(3, "%" + TCari.getText().trim() + "%");
                ps10.setString(4, "%" + TCari.getText().trim() + "%");                
                ps10.setString(5, "%" + TCari.getText().trim() + "%");
                ps10.setString(6, "%" + TCari.getText().trim() + "%");
                rs10 = ps10.executeQuery();
                while (rs10.next()) {
                    tabMode10.addRow(new Object[]{
                        rs10.getString("no_rawat"),
                        rs10.getString("no_rkm_medis"),
                        rs10.getString("nm_pasien"),                        
                        rs10.getString("tglRencana"),
                        rs10.getString("tes_psikologis"),
                        rs10.getString("waktu"),
                        rs10.getString("tester"),
                        rs10.getString("rencana_tes")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs10 != null) {
                    rs10.close();
                }
                if (ps10 != null) {
                    ps10.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode10.getRowCount());
    }
    
    private void tampilManifestasiFungsi() {
        Valid.tabelKosong(tabMode11);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps11 = koneksi.prepareStatement("SELECT pm.no_rawat, p.no_rkm_medis, p.nm_pasien, pm.manifestasi FROM psikolog_manifestasi_fungsi pm "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pm.no_rawat WHERE "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pm.no_rawat like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pm.manifestasi like ? "
                        + "order by pm.no_rawat desc");
            } else {
                ps11 = koneksi.prepareStatement("SELECT pm.no_rawat, p.no_rkm_medis, p.nm_pasien, pm.manifestasi FROM psikolog_manifestasi_fungsi pm "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pm.no_rawat WHERE "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pm.no_rawat like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pm.manifestasi like ? "
                        + "order by pm.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps11.setString(1, "%" + TCari.getText().trim() + "%");
                ps11.setString(2, "%" + TCari.getText().trim() + "%");
                ps11.setString(3, "%" + TCari.getText().trim() + "%");
                ps11.setString(4, "%" + TCari.getText().trim() + "%");
                rs11 = ps11.executeQuery();
                while (rs11.next()) {
                    tabMode11.addRow(new Object[]{
                        rs11.getString("no_rawat"),
                        rs11.getString("no_rkm_medis"),
                        rs11.getString("nm_pasien"),                        
                        rs11.getString("manifestasi")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs11 != null) {
                    rs11.close();
                }
                if (ps11 != null) {
                    ps11.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode11.getRowCount());
    }
    
    private void tampilDiagnosis() {
        Valid.tabelKosong(tabMode12);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps12 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.kesan_awal, pd.diagnosa_utama, "
                        + "pd.diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat WHERE "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and pd.no_rawat like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and pd.kesan_awal like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and pd.diagnosa_utama like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and pd.diagnosa_banding like ? "
                        + "order by pd.no_rawat desc");
            } else {
                ps12 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.kesan_awal, pd.diagnosa_utama, "
                        + "pd.diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat WHERE "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and pd.no_rawat like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and pd.kesan_awal like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and pd.diagnosa_utama like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa' and pd.diagnosa_banding like ? "
                        + "order by pd.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps12.setString(1, "%" + TCari.getText().trim() + "%");
                ps12.setString(2, "%" + TCari.getText().trim() + "%");
                ps12.setString(3, "%" + TCari.getText().trim() + "%");
                ps12.setString(4, "%" + TCari.getText().trim() + "%");
                ps12.setString(5, "%" + TCari.getText().trim() + "%");
                ps12.setString(6, "%" + TCari.getText().trim() + "%");
                rs12 = ps12.executeQuery();
                while (rs12.next()) {
                    tabMode12.addRow(new Object[]{
                        rs12.getString("no_rawat"),
                        rs12.getString("no_rkm_medis"),
                        rs12.getString("nm_pasien"),                        
                        rs12.getString("kesan_awal"),
                        rs12.getString("diagnosa_utama"),
                        rs12.getString("diagnosa_banding")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs12 != null) {
                    rs12.close();
                }
                if (ps12 != null) {
                    ps12.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode12.getRowCount());
    }
    
    private void tampilPrognosis() {
        Valid.tabelKosong(tabMode13);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps13 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.prognosis FROM psikolog_prognosis pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pp.no_rawat WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pp.prognosis like ? "
                        + "order by pp.no_rawat desc");
            } else {
                ps13 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.prognosis FROM psikolog_prognosis pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pp.no_rawat WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pp.prognosis like ? "
                        + "order by pp.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps13.setString(1, "%" + TCari.getText().trim() + "%");
                ps13.setString(2, "%" + TCari.getText().trim() + "%");
                ps13.setString(3, "%" + TCari.getText().trim() + "%");
                ps13.setString(4, "%" + TCari.getText().trim() + "%");
                rs13 = ps13.executeQuery();
                while (rs13.next()) {
                    tabMode13.addRow(new Object[]{
                        rs13.getString("no_rawat"),
                        rs13.getString("no_rkm_medis"),
                        rs13.getString("nm_pasien"),                        
                        rs13.getString("prognosis")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs13 != null) {
                    rs13.close();
                }
                if (ps13 != null) {
                    ps13.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode13.getRowCount());
    }
    
    private void tampilRencanaTritmen() {
        Valid.tabelKosong(tabMode15);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps14 = koneksi.prepareStatement("SELECT pr.no_rawat, p.no_rkm_medis, p.nm_pasien, pr.tritmen, pr.pertemuan_selanjutnya, "
                        + "pr.cttn_tambahan, pr.waktu_simpan FROM psikolog_rencana_tritmen pr "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pr.no_rawat WHERE "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pr.no_rawat like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pr.tritmen like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pr.pertemuan_selanjutnya like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pr.cttn_tambahan like ? "
                        + "order by pr.no_rawat desc");
            } else {
                ps14 = koneksi.prepareStatement("SELECT pr.no_rawat, p.no_rkm_medis, p.nm_pasien, pr.tritmen, pr.pertemuan_selanjutnya, "
                        + "pr.cttn_tambahan, pr.waktu_simpan FROM psikolog_rencana_tritmen pr "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pr.no_rawat WHERE "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pr.no_rawat like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.no_rkm_medis like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and p.nm_pasien like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pr.tritmen like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pr.pertemuan_selanjutnya like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' and pr.cttn_tambahan like ? "
                        + "order by pr.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps14.setString(1, "%" + TCari.getText().trim() + "%");
                ps14.setString(2, "%" + TCari.getText().trim() + "%");
                ps14.setString(3, "%" + TCari.getText().trim() + "%");
                ps14.setString(4, "%" + TCari.getText().trim() + "%");
                ps14.setString(5, "%" + TCari.getText().trim() + "%");
                ps14.setString(6, "%" + TCari.getText().trim() + "%");
                rs14 = ps14.executeQuery();
                while (rs14.next()) {
                    tabMode15.addRow(new Object[]{
                        rs14.getString("no_rawat"),
                        rs14.getString("no_rkm_medis"),
                        rs14.getString("nm_pasien"),                        
                        rs14.getString("tritmen"),
                        rs14.getString("pertemuan_selanjutnya"),
                        rs14.getString("cttn_tambahan"),
                        rs14.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs14 != null) {
                    rs14.close();
                }
                if (ps14 != null) {
                    ps14.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode15.getRowCount());
    }
    
    public void emptText() {
        TNoRW.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        tgl_datang.setText("");
        Tjk.setText("");
        emptTextDD();
        emptTextKAK();
        emptTextIP();
        emptTextKP();
        emptTextPPK();
        emptTextRHS();
        emptTextOP();
        emptTextKPsi();
        emptTextTP();
        emptTextMF();
        emptTextD();
        emptTextP();
    }
    
    private void emptTextDD() {
        Ttl.setText("");
        TPekerjaan.setText("");
        TPendidikan.setText("");
        TSttsKawin.setText("");
        TAlamat.setText("");
        TnoTelp.setText("");
    }
    
    private void emptTextKAK() {
        TnmKel.setText("");
        TnmKel.requestFocus();
        TumurKel.setText("");
        cmbUmur.setSelectedIndex(0);
        cmbJK.setSelectedIndex(0);
        TposisiKel.setText("");
        TketKel.setText("");
    }
    
    private void emptTextIP() {
        TnmPas.setText("");
        TnmPas.requestFocus();
        TumurPas.setText("");
        cmbPnd.setSelectedIndex(0);
        TpekerjaanPas.setText("");
        cmbAgama.setSelectedIndex(0);        
        TanakKe.setText("");
        TdariKe.setText("");
        TkawinKe.setText("");
        TtahunKe.setText("");
        TnoHpPas.setText("");
        TKeluhan.setText("");
        TketPas.setText("");
        TalamatPas.setText("");
    }
    
    private void emptTextKP() {
        Valid.tabelKosong(tabMode4);
        wktSimpan = "";
        BtnPilihKeluhan.requestFocus();
    }   
    
    private void emptTextPPK() {
        TPermasalahansaatIni.setText("Sudah berapa lama permasalahan ini terjadi? (kronologis, frekuensi, intensitas, dampak)\n");
        TPermasalahansaatIni.requestFocus();
        TAlasanMencariBantuan.setText("");
        RSangatSerius.setSelected(false);
        RSerius.setSelected(false);
        RKurangSerius.setSelected(false);
        TAlasan.setText("");
        THarapan.setText("");
        TPerubahanDiriSendiri.setText("");
        TPerubahanKeluarga.setText("");
    }

    private void emptTextRHS() {
        TMasaKanak.setText("");
        TMasaKanak.requestFocus();
        TMasaRemaja.setText("");
        TMasaDewasa.setText("");
        TRiwayatFisik.setText("");
        TRiwayatPengobatan.setText("");
    }

    private void emptTextOP() {
        TPenampilan.setText("");
        TPenampilan.requestFocus();
        TEkspresi.setText("");
        TPerasaan.setText("");
        TTingkahLaku.setText("");
        TFungsiUmum.setText("");
        TFungsiIntelek.setText("");
        TPengalaman.setText("");
        TLain.setText("");
    }
    
    private void emptTextKPsi() {
        TDelusi.setText("");
        TDelusi.requestFocus();
        TProses.setText("");
        THalusinasi.setText("");
        TAfek.setText("");
        TInsight.setText("");
        TKesadaran.setText("");
        TOrenWaktu.setText("");
        TOrenTempat.setText("");
        TOrenRuang.setText("");
        TPerhatian.setText("");
        TPenilaian.setText("");
        TKontrol.setText("");
    }
    
    private void emptTextTP() {
        tgl_rencana.setDate(new Date());
        tgl_rencana.requestFocus();
        TTesPsiko.setText("");
        TWaktu.setText("");
        TTester.setText("");
    }
    
    private void emptTextMF() {
        TManifes.setText("");
        TManifes.requestFocus();
    }
    
    private void emptTextD() {
        TKesan.setText("");
        TKesan.requestFocus();
        TDiagUtama.setText("");
        TDiagBanding.setText("");
    }
    
    private void emptTextP() {
        Tprognosis.setText("Faktor Penghambat : \n\nFaktor Pendukung : \n");
        Tprognosis.requestFocus();
    }
    
    private void emptTextRT() {
        Valid.tabelKosong(tabMode14);
        BtnPilihTritmen.requestFocus();
        wktSimpan = "";
        TPertemuan.setText("");
        TCatatan.setText("");
    }

    private void getDataDD() {
        tglKedatangan = "";
        if (tbdatadiri.getSelectedRow() != -1) {            
            TNoRW.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 0).toString());
            tglKedatangan = tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 1).toString();
            TNoRM.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 2).toString());            
            tgl_datang.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 3).toString());
            TPasien.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 4).toString());
            Tjk.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 5).toString());
            Ttl.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 6).toString());
            TPekerjaan.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 7).toString());
            TPendidikan.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 8).toString());            
            TSttsKawin.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 9).toString());
            TAlamat.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 10).toString());
            TnoTelp.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 11).toString());
        }
    }
    
    private void getDataKAK() {
        wktSimpan = "";
        if (tbAnggotaKel.getSelectedRow() != -1) {            
            wktSimpan = tbAnggotaKel.getValueAt(tbAnggotaKel.getSelectedRow(), 7).toString();
            TnmKel.setText(tbAnggotaKel.getValueAt(tbAnggotaKel.getSelectedRow(), 2).toString());
            TumurKel.setText(tbAnggotaKel.getValueAt(tbAnggotaKel.getSelectedRow(), 8).toString());
            cmbUmur.setSelectedItem(tbAnggotaKel.getValueAt(tbAnggotaKel.getSelectedRow(), 9).toString());
            cmbJK.setSelectedItem(tbAnggotaKel.getValueAt(tbAnggotaKel.getSelectedRow(), 4).toString());
            TposisiKel.setText(tbAnggotaKel.getValueAt(tbAnggotaKel.getSelectedRow(), 5).toString());
            TketKel.setText(tbAnggotaKel.getValueAt(tbAnggotaKel.getSelectedRow(), 6).toString());
        }
    }
    
    private void getDataIP() {
        wktSimpan = "";
        if (tbIdentitasPas.getSelectedRow() != -1) {            
            wktSimpan = tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 17).toString();
            TnmPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 2).toString());
            TumurPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 18).toString());
            cmbPnd.setSelectedItem(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 4).toString());
            TpekerjaanPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 5).toString());
            cmbAgama.setSelectedItem(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 6).toString());
            TanakKe.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 13).toString());
            TdariKe.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 14).toString());
            TkawinKe.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 15).toString());
            TtahunKe.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 16).toString());
            TnoHpPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 11).toString());
            TKeluhan.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 9).toString());
            TketPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 12).toString());
            TalamatPas.setText(tbIdentitasPas.getValueAt(tbIdentitasPas.getSelectedRow(), 10).toString());
        }
    }
    
    private void getDataKeluhan() {
        wktSimpan = "";
        if (tbKeluhan.getSelectedRow() != -1) {            
            wktSimpan = tbKeluhan.getValueAt(tbKeluhan.getSelectedRow(), 1).toString();
            tampilItemKeluhan();
        }
    }
    
    private void getDataPsikologisKlinis() {
        wktSimpan = "";
        nilaiMasalah = "";
        if (tbPsikologiKlinis.getSelectedRow() != -1) {            
            wktSimpan = tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 0).toString();
            TPermasalahansaatIni.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 3).toString());
            TAlasanMencariBantuan.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 4).toString());
            nilaiMasalah = tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 5).toString();
            TAlasan.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 6).toString());
            THarapan.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 7).toString());
            TPerubahanDiriSendiri.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 8).toString());
            TPerubahanKeluarga.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 9).toString());
            
            if (nilaiMasalah.equals("Sangat serius")) {
                RSangatSerius.setSelected(true);
                RSerius.setSelected(false);
                RKurangSerius.setSelected(false);
            } else if (nilaiMasalah.equals("Serius")) {
                RSangatSerius.setSelected(false);
                RSerius.setSelected(true);
                RKurangSerius.setSelected(false);
            } else if (nilaiMasalah.equals("Kurang serius")) {
                RSangatSerius.setSelected(false);
                RSerius.setSelected(false);
                RKurangSerius.setSelected(true);
            } 
        }
    }
    
    private void getDataRiwayatHidup() {
        wktSimpan = "";
        if (tbRiwayatHidup.getSelectedRow() != -1) {            
            wktSimpan = tbRiwayatHidup.getValueAt(tbRiwayatHidup.getSelectedRow(), 0).toString();
            TMasaKanak.setText(tbRiwayatHidup.getValueAt(tbRiwayatHidup.getSelectedRow(), 3).toString());
            TMasaRemaja.setText(tbRiwayatHidup.getValueAt(tbRiwayatHidup.getSelectedRow(), 4).toString());
            TMasaDewasa.setText(tbRiwayatHidup.getValueAt(tbRiwayatHidup.getSelectedRow(), 5).toString());
            TRiwayatFisik.setText(tbRiwayatHidup.getValueAt(tbRiwayatHidup.getSelectedRow(), 6).toString());
            TRiwayatPengobatan.setText(tbRiwayatHidup.getValueAt(tbRiwayatHidup.getSelectedRow(), 7).toString());
        }
    }
    
    private void getDataObservasi() {
        wktSimpan = "";
        if (tbObservasi.getSelectedRow() != -1) {            
            wktSimpan = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 0).toString();
            TPenampilan.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 3).toString());
            TEkspresi.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 4).toString());
            TPerasaan.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 5).toString());
            TTingkahLaku.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 6).toString());
            TFungsiUmum.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 7).toString());
            TFungsiIntelek.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 8).toString());
            TPengalaman.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 9).toString());
            TLain.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 10).toString());
        }
    }
    
    private void getDataKondisiPsi() {
        wktSimpan = "";
        if (tbKondisiPsiko.getSelectedRow() != -1) {            
            wktSimpan = tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 0).toString();            
            TDelusi.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 3).toString());
            TProses.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 4).toString());
            THalusinasi.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 5).toString());
            TAfek.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 6).toString());
            TInsight.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 7).toString());
            TKesadaran.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 8).toString());
            TOrenWaktu.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 9).toString());
            TOrenTempat.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 10).toString());            
            TOrenRuang.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 11).toString());
            TPerhatian.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 12).toString());
            TPenilaian.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 13).toString());
            TKontrol.setText(tbKondisiPsiko.getValueAt(tbKondisiPsiko.getSelectedRow(), 14).toString());
        }
    }
    
    private void getDataTesPsiko() {
        wktSimpan = "";
        if (tbTesPsiko.getSelectedRow() != -1) {            
            wktSimpan = tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 0).toString();            
            TTesPsiko.setText(tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 4).toString());
            TWaktu.setText(tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 5).toString());
            TTester.setText(tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 6).toString());
            Valid.SetTgl(tgl_rencana, tbTesPsiko.getValueAt(tbTesPsiko.getSelectedRow(), 7).toString());
        }
    }
    
    private void getDataManifes() {
        wktSimpan = "";
        if (tbManifestasi.getSelectedRow() != -1) {            
            wktSimpan = tbManifestasi.getValueAt(tbManifestasi.getSelectedRow(), 0).toString();            
            TManifes.setText(tbManifestasi.getValueAt(tbManifestasi.getSelectedRow(), 3).toString());
        }
    }
    
    private void getDataDiagnosis() {
        wktSimpan = "";
        if (tbDiagnosis.getSelectedRow() != -1) {            
            wktSimpan = tbDiagnosis.getValueAt(tbDiagnosis.getSelectedRow(), 0).toString();            
            TKesan.setText(tbDiagnosis.getValueAt(tbDiagnosis.getSelectedRow(), 3).toString());
            TDiagUtama.setText(tbDiagnosis.getValueAt(tbDiagnosis.getSelectedRow(), 4).toString());
            TDiagBanding.setText(tbDiagnosis.getValueAt(tbDiagnosis.getSelectedRow(), 5).toString());
        }
    }
    
    private void getDataPrognosis() {
        wktSimpan = "";
        if (tbPrognosis.getSelectedRow() != -1) {            
            wktSimpan = tbPrognosis.getValueAt(tbPrognosis.getSelectedRow(), 0).toString();            
            Tprognosis.setText(tbPrognosis.getValueAt(tbPrognosis.getSelectedRow(), 3).toString());
        }
    }
    
    private void getDataTritmen() {
        wktSimpan = "";
        if (tbTritmen.getSelectedRow() != -1) {
            TPertemuan.setText(tbTritmen.getValueAt(tbTritmen.getSelectedRow(), 4).toString());
            TCatatan.setText(tbTritmen.getValueAt(tbTritmen.getSelectedRow(), 5).toString());
            wktSimpan = tbTritmen.getValueAt(tbTritmen.getSelectedRow(), 6).toString();
            tampilItemTritmen();
        }
    }

    private void simpanDD() {
        if (Ttl.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data diri pasien/klien masih belum lengkap...!!!!");
        } else {
            Sequel.menyimpan("psikolog_data_diri_klien", "'" + TNoRW.getText() + "',"
                    + "'" + tglKedatangan + "','-','-','-','umum_dewasa'", "Data Diri Pasien/Klien");
            tampilDataDiri();
            emptTextDD();
        }
    }
    
    private void simpanKAK() {
        if (TnmKel.getText().trim().equals("")) {
            Valid.textKosong(TnmKel, "Nama anggota keluarga");
            TnmKel.requestFocus();
        } else if (TumurKel.getText().trim().equals("")) {
            Valid.textKosong(TumurKel, "Umur anggota keluarga");
            TumurKel.requestFocus();
        } else if (cmbJK.getSelectedIndex() == 0) {
            Valid.textKosong(cmbJK, "Jenis kelamin anggota keluarga");
            cmbJK.requestFocus();
        } else if (TposisiKel.getText().trim().equals("")) {
            Valid.textKosong(TposisiKel, "Posisi anggota keluarga");
            TposisiKel.requestFocus();
        } else if (TketKel.getText().trim().equals("")) {
            Valid.textKosong(TketKel, "Keterangan anggota keluarga");
            TketKel.requestFocus();
        } else {
            jkel = "";
            if (cmbJK.getSelectedIndex() == 1) {
                jkel = "L";
            } else if (cmbJK.getSelectedIndex() == 2) {
                jkel = "P";
            }
            
            Sequel.menyimpan("psikolog_anggota_keluarga", 
                    "'" + TNoRM.getText() + "','" + TnmKel.getText() + "','" + TumurKel.getText() + "',"
                    + "'" + cmbUmur.getSelectedItem().toString() + "','" + jkel + "','" + TposisiKel.getText() + "',"
                    + "'" + TketKel.getText() + "','anggota_keluarga','" + Sequel.cariIsi("select now()") + "',"
                    + "'umum_dewasa'", "Komposisi Anggota Keluarga Pasien/Klien");
            tampilAnggotaKeluarga();
            emptTextKAK();
        }
    }
    
    private void simpanIP() {
        if (TnmPas.getText().trim().equals("")) {
            Valid.textKosong(TnmPas, "Nama pasangan SUAMI/ISTRI");
            TnmPas.requestFocus();
        } else if (TumurPas.getText().trim().equals("")) {
            Valid.textKosong(TumurPas, "Umur pasangan");
            TumurPas.requestFocus();
        } else if (TPekerjaan.getText().trim().equals("")) {
            Valid.textKosong(TPekerjaan, "pekerjaan pasangan");
            TPekerjaan.requestFocus();
        } else if (TanakKe.getText().trim().equals("")) {
            Valid.textKosong(TanakKe, "anak ke");
            TanakKe.requestFocus();
        } else if (TdariKe.getText().trim().equals("")) {
            Valid.textKosong(TdariKe, "dari bersaudara");
            TdariKe.requestFocus();
        } else if (TkawinKe.getText().trim().equals("")) {
            Valid.textKosong(TkawinKe, "perkawinan ke");
            TkawinKe.requestFocus();
        } else if (TtahunKe.getText().trim().equals("")) {
            Valid.textKosong(TtahunKe, "dari tahun perkawinan");
            TtahunKe.requestFocus();
        } else if (TnoHpPas.getText().trim().equals("")) {
            Valid.textKosong(TnoHpPas, "No. HP/Telpn. pasangan");
            TnoHpPas.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_umum_dewasa_identitas_pas", 
                    "'" + TNoRM.getText() + "','" + TnmPas.getText() + "','" + TumurPas.getText() + "',"
                    + "'" + cmbPnd.getSelectedItem().toString() + "','" + TpekerjaanPas.getText() + "','" + cmbAgama.getSelectedItem().toString() + "',"
                    + "'" + TanakKe.getText() + "','" + TdariKe.getText() + "','" + TkawinKe.getText() + "','" + TtahunKe.getText() + "',"
                    + "'" + TKeluhan.getText() + "','" + TalamatPas.getText() + "','" + TnoHpPas.getText() + "','" + TketPas.getText() + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Identitas Pasangan Pasien/Klien");
            tampilIdentitasPasangan();
            emptTextIP();
        }
    }
    
    private void simpanKP() {
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item/jenis keluhan permasalahan masih kosong...!!!!");
            BtnPilihKeluhan.requestFocus();        
        } else {
            dataMasalah = "";
            for (i = 0; i < tbItemMasalah.getRowCount(); i++) {
                if (dataMasalah.equals("")) {
                    dataMasalah = tbItemMasalah.getValueAt(i, 1).toString() + " " + tbItemMasalah.getValueAt(i, 2).toString();
                } else {
                    dataMasalah = dataMasalah + " , " + tbItemMasalah.getValueAt(i, 1).toString() + " " + tbItemMasalah.getValueAt(i, 2).toString();
                }
            }

            Sequel.menyimpan("psikolog_keluhan", "'" + TNoRW.getText() + "','" + dataMasalah + "','"
                    + Sequel.cariIsi("SELECT NOW()") + "'", "Keluhan Permasalahan");

            for (i = 0; i < tbItemMasalah.getRowCount(); i++) {
                Sequel.menyimpan("psikolog_detail_keluhan", "'" + TNoRW.getText() + "',"
                        + "'" + tbItemMasalah.getValueAt(i, 0).toString() + "',"
                        + "'" + tbItemMasalah.getValueAt(i, 2).toString() + "',"
                        + "'" + Sequel.cariIsi("SELECT NOW()") + "'", "Detail Keluhan Permasalahan");
            }
            
            tampilKeluhanMasalah();
            emptTextKP();
        }
    }
    
    private void simpanPPK() {
        if (RSangatSerius.isSelected() == false && RSerius.isSelected() == false && RKurangSerius.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu pilihan permasalahan dinilai...!!!!");
            RSangatSerius.requestFocus();        
        } else {
            nilaiMasalah = "";
            if (RSangatSerius.isSelected() == true) {
                nilaiMasalah = "Sangat serius";
            } else if (RSerius.isSelected() == true) {
                nilaiMasalah = "Serius";
            } else if (RKurangSerius.isSelected() == true) {
                nilaiMasalah = "Kurang serius";
            }
            
            Sequel.menyimpan("psikolog_wawancara_klinis", 
                    "'" + TNoRW.getText() + "','Umum/Dewasa','" + TPermasalahansaatIni.getText() + "',"
                    + "'" + TAlasanMencariBantuan.getText() + "','" + nilaiMasalah + "','" + TAlasan.getText() + "',"
                    + "'" + THarapan.getText() + "','','" + TPerubahanDiriSendiri.getText() + "',"
                    + "'" + TPerubahanKeluarga.getText() + "'", "Pemeriksaan Psikologis Klinis Pasien/Klien");
            tampilPsikologiKlinis();
            emptTextPPK();
        }
    }
    
    private void simpanRHS() {
        Sequel.menyimpan("psikolog_umum_dewasa_riw_hidup",
                "'" + TNoRW.getText() + "','" + TMasaKanak.getText() + "',"
                + "'" + TMasaRemaja.getText() + "','" + TMasaDewasa.getText() + "','" + TRiwayatFisik.getText() + "',"
                + "'" + TRiwayatPengobatan.getText() + "'", "Riwayat Hidup Singkat Pasien/Klien");
        tampilRiwayatHidup();
        emptTextRHS();
    }
    
    private void simpanOP() {
        if (TPenampilan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika observasi penampilan kosong, isi dengan strip (-)...!!!!");
            TPenampilan.requestFocus();
        } else if (TEkspresi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika observasi ekspresi wajah kosong, isi dengan strip (-)...!!!!");
            TEkspresi.requestFocus();
        } else if (TPerasaan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika observasi Perasaan/Suasana Hati kosong, isi dengan strip (-)...!!!!");
            TPerasaan.requestFocus();
        } else if (TTingkahLaku.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika observasi Tingkah Laku kosong, isi dengan strip (-)...!!!!");
            TTingkahLaku.requestFocus();
        } else if (TFungsiUmum.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika observasi Fungsi Umum kosong, isi dengan strip (-)...!!!!");
            TFungsiUmum.requestFocus();
        } else if (TFungsiIntelek.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika observasi Fungsi Intelektual kosong, isi dengan strip (-)...!!!!");
            TFungsiIntelek.requestFocus();
        } else if (TPengalaman.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika observasi Pengalaman Kerja kosong, isi dengan strip (-)...!!!!");
            TPengalaman.requestFocus();
        } else if (TLain.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika observasi Lain-lain kosong, isi dengan strip (-)...!!!!");
            TLain.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_observasi",
                    "'" + TNoRW.getText() + "','umum_dewasa',"
                    + "'" + TPenampilan.getText() + "','" + TEkspresi.getText() + "','" + TPerasaan.getText() + "',"
                    + "'" + TTingkahLaku.getText() + "','" + TFungsiUmum.getText() + "','" + TFungsiIntelek.getText() + "',"
                    + "'" + TPengalaman.getText() + "','" + TLain.getText() + "'", "Observasi Psikologi Secara Umum Pasien/Klien");
            tampilObservasi();
            emptTextOP();
        }
    }
    
    private void simpanKPsi() {
        if (TDelusi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Delusi / Waham kosong, isi dengan strip (-)...!!!!");
            TDelusi.requestFocus();
        } else if (TProses.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Proses Pikiran kosong, isi dengan strip (-)...!!!!");
            TProses.requestFocus();
        } else if (THalusinasi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Halusinasi kosong, isi dengan strip (-)...!!!!");
            THalusinasi.requestFocus();
        } else if (TAfek.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Afek kosong, isi dengan strip (-)...!!!!");
            TAfek.requestFocus();
        } else if (TInsight.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Insight kosong, isi dengan strip (-)...!!!!");
            TInsight.requestFocus();
        } else if (TKesadaran.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Kesadaran kosong, isi dengan strip (-)...!!!!");
            TKesadaran.requestFocus();
        } else if (TOrenWaktu.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Orientasi Waktu kosong, isi dengan strip (-)...!!!!");
            TOrenWaktu.requestFocus();
        } else if (TOrenTempat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Orientasi Tempat kosong, isi dengan strip (-)...!!!!");
            TOrenTempat.requestFocus();
        } else if (TOrenRuang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Orientasi Ruang kosong, isi dengan strip (-)...!!!!");
            TOrenRuang.requestFocus();
        } else if (TPerhatian.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Perhatian kosong, isi dengan strip (-)...!!!!");
            TPerhatian.requestFocus();
        } else if (TPenilaian.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Penilaian kosong, isi dengan strip (-)...!!!!");
            TPenilaian.requestFocus();
        } else if (TKontrol.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Kontrol Terhadap Impuls kosong, isi dengan strip (-)...!!!!");
            TKontrol.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_umum_dewasa_psikopatologis",
                    "'" + TNoRW.getText() + "','" + TDelusi.getText() + "','" + TProses.getText() + "',"
                    + "'" + THalusinasi.getText() + "','" + TAfek.getText() + "','" + TInsight.getText() + "',"
                    + "'" + TKesadaran.getText() + "','" + TOrenWaktu.getText() + "','" + TOrenTempat.getText() + "',"
                    + "'" + TOrenRuang.getText() + "','" + TPerhatian.getText() + "','" + TPenilaian.getText() + "',"
                    + "'" + TKontrol.getText() + "'", "Kondisi Psikopatologis Pasien/Klien");
            tampilKondisiPsi();
            emptTextKPsi();
        }
    }
    
    private void simpanTP() {
        if (TTesPsiko.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Tes Psikologis kosong, isi dengan strip (-)...!!!!");
            TTesPsiko.requestFocus();
        } else if (TWaktu.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Waktu kosong, isi dengan strip (-)...!!!!");
            TWaktu.requestFocus();
        } else if (TTester.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Tester kosong, isi dengan strip (-)...!!!!");
            TTester.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_tes_psikologi",
                    "'" + TNoRW.getText() + "','umum_dewasa','" + Valid.SetTgl(tgl_rencana.getSelectedItem() + "") + "',"
                    + "'" + TTesPsiko.getText() + "','" + TWaktu.getText() + "','" + TTester.getText() + "'", "Tes Psikologi Pasien/Klien");
            tampilTesPsikologi();
            emptTextTP();
        }
    }
    
    private void simpanMF() {
        Sequel.menyimpan("psikolog_manifestasi_fungsi",
                "'" + TNoRW.getText() + "','" + TManifes.getText() + "'", "Manifestasi Fungsi Psikologis Pasien/Klien");
        tampilManifestasiFungsi();
        emptTextMF();
    }
    
    private void simpanD() {
        if (TDiagUtama.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika diagnosis utama (dx) kosong, isi dengan strip (-)...!!!!");
            TDiagUtama.requestFocus();
        } else if (TDiagBanding.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika diagnosis banding (dd) kosong, isi dengan strip (-)...!!!!");
            TDiagBanding.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_diagnosis",
                    "'" + TNoRW.getText() + "','" + TKesan.getText() + "','" + TDiagUtama.getText() + "',"
                    + "'" + TDiagBanding.getText() + "'", "Diagnosis (berdasarkan DSM / ICD / PPDGJ) Pasien/Klien");
            tampilDiagnosis();
            emptTextD();
        }
    }
    
    private void simpanP() {
        Sequel.menyimpan("psikolog_prognosis",
                "'" + TNoRW.getText() + "','" + Tprognosis.getText() + "'", "Prognosis Pasien/Klien");
        tampilPrognosis();
        emptTextP();
    }
    
    private void simpanRT() {
        if (tabMode14.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item rencana tritmen masih kosong...!!!!");
            BtnPilihTritmen.requestFocus();        
        } else if (TPertemuan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Pertemuan Selanjutnya kosong, isi dengan strip (-)...!!!!");
            TPertemuan.requestFocus();
        } else if (TCatatan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Catatan Tambahan kosong, isi dengan strip (-)...!!!!");
            TCatatan.requestFocus();
        } else {
            dataTritmen = "";
            for (i = 0; i < tbItemTritmen.getRowCount(); i++) {
                if (dataTritmen.equals("")) {
                    dataTritmen = tbItemTritmen.getValueAt(i, 1).toString() + " " + tbItemTritmen.getValueAt(i, 2).toString();
                } else {
                    dataTritmen = dataTritmen + " , " + tbItemTritmen.getValueAt(i, 1).toString() + " " + tbItemTritmen.getValueAt(i, 2).toString();
                }
            }

            Sequel.menyimpan("psikolog_rencana_tritmen", "'" + TNoRW.getText() + "','" + dataTritmen + "',"
                    + "'" + TPertemuan.getText() + "','" + TCatatan.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "'", "Rencana Tritmen");

            for (i = 0; i < tbItemTritmen.getRowCount(); i++) {
                Sequel.menyimpan("psikolog_detail_rencana_tritmen", "'" + TNoRW.getText() + "',"
                        + "'" + Sequel.cariIsi("SELECT NOW()") + "',"
                        + "'" + tbItemTritmen.getValueAt(i, 0).toString() + "',"
                        + "'" + tbItemTritmen.getValueAt(i, 2).toString() + "'", "Detail Rencana Tritmen");
            }
            
            tampilRencanaTritmen();
            emptTextRT();
        }
    }
    
    private void hapusDD() {
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            Sequel.queryu("delete from psikolog_data_diri_klien where no_rawat='" + TNoRW.getText() + "'");
            tampilDataDiri();
            emptTextDD();
            tglKedatangan = "";
        }
    }
    
    private void hapusKAK() {
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data anggota keluarga pada tabel...!!!!");
            tbAnggotaKel.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anggota_keluarga where waktu_simpan='" + wktSimpan + "'");
            tampilAnggotaKeluarga();
            emptTextKAK();
            wktSimpan = "";
        }
    }
    
    private void hapusIP() {
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas pasangan pada tabel...!!!!");
            tbIdentitasPas.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_umum_dewasa_identitas_pas where waktu_simpan='" + wktSimpan + "'");
            tampilIdentitasPasangan();
            emptTextIP();
            wktSimpan = "";
        }
    }
    
    private void hapusKP() {
        if (tabMode5.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data keluhan permasalahan pada tabel...!!!!");
            tbKeluhan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_detail_keluhan "
                    + "where no_rawat='" + TNoRW.getText() + "' and waktu_simpan='" + wktSimpan + "'");

            Sequel.queryu("delete from psikolog_keluhan "
                    + "where no_rawat='" + TNoRW.getText() + "' and waktu_simpan='" + wktSimpan + "'");
            
            tampilKeluhanMasalah();
            emptTextKP();
        }
    }
    
    private void hapusPPK() {
        if (tabMode6.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pemeriksaan psikologi klinis pada tabel...!!!!");
            tbPsikologiKlinis.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_wawancara_klinis where no_rawat='" + wktSimpan + "'");
            
            tampilPsikologiKlinis();
            emptTextPPK();
            wktSimpan = "";
        }
    }
    
    private void hapusRHS() {
        if (tabMode7.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat hidup singkat pada tabel...!!!!");
            tbRiwayatHidup.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_umum_dewasa_riw_hidup where no_rawat='" + wktSimpan + "'");
            
            tampilRiwayatHidup();
            emptTextRHS();
            wktSimpan = "";
        }
    }
    
    private void hapusOP() {
        if (tabMode8.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data observasi psikologis secara umum pada tabel...!!!!");
            tbObservasi.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_observasi where no_rawat='" + wktSimpan + "'");
            
            tampilObservasi();
            emptTextOP();
            wktSimpan = "";
        }
    }
    
    private void hapusKPsi() {
        if (tabMode9.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data kondisi psikopatologis pasien pada tabel...!!!!");
            tbKondisiPsiko.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_umum_dewasa_psikopatologis where no_rawat='" + wktSimpan + "'");
            
            tampilKondisiPsi();
            emptTextKPsi();
            wktSimpan = "";
        }
    }
    
    private void hapusTP() {
        if (tabMode10.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data tes psikologi pasien pada tabel...!!!!");
            tbTesPsiko.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_tes_psikologi where no_rawat='" + wktSimpan + "'");
            
            tampilTesPsikologi();
            emptTextTP();
            wktSimpan = "";
        }
    }
    
    private void hapusMF() {
        if (tabMode11.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data manifestasi fungsi psikologis pasien pada tabel...!!!!");
            tbManifestasi.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_manifestasi_fungsi where no_rawat='" + wktSimpan + "'");
            
            tampilManifestasiFungsi();
            emptTextMF();
            wktSimpan = "";
        }
    }
    
    private void hapusD() {
        if (tabMode12.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data diagnosis pasien pada tabel...!!!!");
            tbDiagnosis.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_diagnosis where no_rawat='" + wktSimpan + "'");
            
            tampilDiagnosis();
            emptTextD();
            wktSimpan = "";
        }
    }
    
    private void hapusP() {
        if (tabMode13.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data prognosis pasien pada tabel...!!!!");
            tbPrognosis.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_prognosis where no_rawat='" + wktSimpan + "'");
            
            tampilPrognosis();
            emptTextP();
            wktSimpan = "";
        }
    }
    
    private void hapusRT() {
        if (tabMode15.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data rencana tritmen pada tabel...!!!!");
            tbTritmen.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_detail_rencana_tritmen "
                    + "where no_rawat='" + TNoRW.getText() + "' and waktu_simpan='" + wktSimpan + "'");

            Sequel.queryu("delete from psikolog_rencana_tritmen "
                    + "where no_rawat='" + TNoRW.getText() + "' and waktu_simpan='" + wktSimpan + "'");
            
            tampilRencanaTritmen();
            emptTextRT();
        }
    }
    
    private void gantiDD() {
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (tglKedatangan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbdatadiri.requestFocus();
        } else {
            Sequel.mengedit("psikolog_data_diri_klien", "no_rawat='" + TNoRW.getText() + "'", "tgl_kedatangan='" + tglKedatangan + "'");
            tampilDataDiri();
            emptTextDD();
        }
    }
    
    private void gantiKAK() {
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data anggota keluarga pada tabel...!!!!");
            tbAnggotaKel.requestFocus();
        } else if (TnmKel.getText().trim().equals("")) {
            Valid.textKosong(TnmKel, "Nama anggota keluarga");
            TnmKel.requestFocus();
        } else if (TumurKel.getText().trim().equals("")) {
            Valid.textKosong(TumurKel, "Umur anggota keluarga");
            TumurKel.requestFocus();
        } else if (cmbJK.getSelectedIndex() == 0) {
            Valid.textKosong(cmbJK, "Jenis kelamin anggota keluarga");
            cmbJK.requestFocus();
        } else if (TposisiKel.getText().trim().equals("")) {
            Valid.textKosong(TposisiKel, "Posisi anggota keluarga");
            TposisiKel.requestFocus();
        } else if (TketKel.getText().trim().equals("")) {
            Valid.textKosong(TketKel, "Keterangan anggota keluarga");
            TketKel.requestFocus();
        } else {
            jkel = "";
            if (cmbJK.getSelectedIndex() == 1) {
                jkel = "L";
            } else if (cmbJK.getSelectedIndex() == 2) {
                jkel = "P";
            }

            Sequel.mengedit("psikolog_anggota_keluarga", "waktu_simpan='" + wktSimpan + "'",
                    "no_rkm_medis='" + TNoRM.getText() + "',nama='" + TnmKel.getText() + "',umur='" + TumurKel.getText() + "',"
                    + "sttsumur='" + cmbUmur.getSelectedItem().toString() + "',jk='" + jkel + "',"
                    + "posisi='" + TposisiKel.getText() + "',keterangan='" + TketKel.getText() + "'");
            tampilAnggotaKeluarga();
            emptTextKAK();
        }
    }
    
    private void gantiIP() {
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas pasangan pada tabel...!!!!");
            tbIdentitasPas.requestFocus();
        } else if (TnmPas.getText().trim().equals("")) {
            Valid.textKosong(TnmPas, "Nama pasangan SUAMI/ISTRI");
            TnmPas.requestFocus();
        } else if (TumurPas.getText().trim().equals("")) {
            Valid.textKosong(TumurPas, "Umur pasangan");
            TumurPas.requestFocus();
        } else if (TPekerjaan.getText().trim().equals("")) {
            Valid.textKosong(TPekerjaan, "pekerjaan pasangan");
            TPekerjaan.requestFocus();
        } else if (TanakKe.getText().trim().equals("")) {
            Valid.textKosong(TanakKe, "anak ke");
            TanakKe.requestFocus();
        } else if (TdariKe.getText().trim().equals("")) {
            Valid.textKosong(TdariKe, "dari bersaudara");
            TdariKe.requestFocus();
        } else if (TkawinKe.getText().trim().equals("")) {
            Valid.textKosong(TkawinKe, "perkawinan ke");
            TkawinKe.requestFocus();
        } else if (TtahunKe.getText().trim().equals("")) {
            Valid.textKosong(TtahunKe, "dari tahun perkawinan");
            TtahunKe.requestFocus();
        } else if (TnoHpPas.getText().trim().equals("")) {
            Valid.textKosong(TnoHpPas, "No. HP/Telpn. pasangan");
            TnoHpPas.requestFocus();
        } else {
            Sequel.mengedit("psikolog_umum_dewasa_identitas_pas", "waktu_simpan='" + wktSimpan + "'",
                    "no_rkm_medis='" + TNoRM.getText() + "',nama='" + TnmPas.getText() + "', umur='" + TumurPas.getText() + "',"
                    + "pendidikan='" + cmbPnd.getSelectedItem().toString() + "', pekerjaan='" + TpekerjaanPas.getText() + "',"
                    + "agama='" + cmbAgama.getSelectedItem().toString() + "', anak_ke='" + TanakKe.getText() + "',dari_anak='" + TdariKe.getText() + "',"
                    + "perkawinan_ke='" + TkawinKe.getText() + "', dari_kawin='" + TtahunKe.getText() + "', keluhan='" + TKeluhan.getText() + "', "
                    + "alamat='" + TalamatPas.getText() + "', no_hp='" + TnoHpPas.getText() + "', keterangan='" + TketPas.getText() + "'");
            tampilIdentitasPasangan();
            emptTextIP();
        }
    }
    
    private void gantiKP() {
        if (tabMode5.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data keluhan permasalahan pada tabel...!!!!");
            tbKeluhan.requestFocus();
        } else if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, item/jenis keluhan permasalahan belum terisi...!!!!");
            BtnPilihKeluhan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_detail_keluhan "
                    + "where no_rawat='" + TNoRW.getText() + "' and waktu_simpan='" + wktSimpan + "'");

            Sequel.queryu("delete from psikolog_keluhan "
                    + "where no_rawat='" + TNoRW.getText() + "' and waktu_simpan='" + wktSimpan + "'");
            simpanKP();
        }
    }
    
    private void gantiPPK() {
        if (tabMode6.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pemeriksaan psikologi klinis pada tabel...!!!!");
            tbPsikologiKlinis.requestFocus();
        } else if (RSangatSerius.isSelected() == false && RSerius.isSelected() == false && RKurangSerius.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu pilihan permasalahan dinilai...!!!!");
            RSangatSerius.requestFocus();        
        } else {
            nilaiMasalah = "";
            if (RSangatSerius.isSelected() == true) {
                nilaiMasalah = "Sangat serius";
            } else if (RSerius.isSelected() == true) {
                nilaiMasalah = "Serius";
            } else if (RKurangSerius.isSelected() == true) {
                nilaiMasalah = "Kurang serius";
            }
            
            Sequel.mengedit("psikolog_wawancara_klinis", "no_rawat='" + wktSimpan + "'",
                    "permasalahan_saat_ini='" + TPermasalahansaatIni.getText() + "',alasan_mencari_bantuan='" + TAlasanMencariBantuan.getText() + "', "
                    + "permasalahan_dinilai='" + nilaiMasalah + "',alasan_permasalahan_dinilai='" + TAlasan.getText() + "', harapan='" + THarapan.getText() + "',"
                    + "perubahan_diinginkan_diri_sendiri='" + TPerubahanDiriSendiri.getText() + "', perubahan_diinginkan_keluarga='" + TPerubahanKeluarga.getText() + "'");
            tampilPsikologiKlinis();
            emptTextPPK();
        }
    }
    
    private void gantiRHS() {
        if (tabMode7.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat hidup singkat pada tabel...!!!!");
            tbRiwayatHidup.requestFocus();
        } else {
            Sequel.mengedit("psikolog_umum_dewasa_riw_hidup", "no_rawat='" + wktSimpan + "'",
                    "masa_kanak='" + TMasaKanak.getText() + "',masa_remaja='" + TMasaRemaja.getText() + "', "
                    + "masa_dewasa='" + TMasaDewasa.getText() + "',riw_penyakit_fisik='" + TRiwayatFisik.getText() + "', "
                    + "riw_pengobatan='" + TRiwayatPengobatan.getText() + "'");
            tampilRiwayatHidup();
            emptTextRHS();
        }
    }
    
    private void gantiOP() {
        if (tabMode8.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data observasi psikologis secara umum pada tabel...!!!!");
            tbObservasi.requestFocus();
        } else {
            Sequel.mengedit("psikolog_observasi", "no_rawat='" + wktSimpan + "'",
                    "penampilan='" + TPenampilan.getText() + "',ekspresi_wajah='" + TEkspresi.getText() + "', "
                    + "perasaan_suasana_hati='" + TPerasaan.getText() + "',tingkah_laku='" + TTingkahLaku.getText() + "', "
                    + "fungsi_umum='" + TFungsiUmum.getText() + "',fungsi_intelektual='" + TFungsiIntelek.getText() + "',"
                    + "pengalaman_kerja='" + TPengalaman.getText() + "',lainlain='" + TLain.getText() + "'");
            tampilObservasi();
            emptTextOP();
        }
    }
    
    private void gantiKPsi() {
        if (tabMode9.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data kondisi psikopatologis pasien pada tabel...!!!!");
            tbKondisiPsiko.requestFocus();
        } else {
            Sequel.mengedit("psikolog_umum_dewasa_psikopatologis", "no_rawat='" + wktSimpan + "'",
                    "delusi_waham='" + TDelusi.getText() + "',proses_pikiran='" + TProses.getText() + "', "
                    + "halusinasi='" + THalusinasi.getText() + "',afek='" + TAfek.getText() + "', "
                    + "insight='" + TInsight.getText() + "',kesadaran='" + TKesadaran.getText() + "',"
                    + "orientasi_waktu='" + TOrenWaktu.getText() + "',orientasi_tempat='" + TOrenTempat.getText() + "',"
                    + "orientasi_ruang='" + TOrenRuang.getText() + "',perhatian='" + TPerhatian.getText() + "',"
                    + "penilaian='" + TPenilaian.getText() + "',kontrol_thdp_impuls='" + TKontrol.getText() + "'");
            tampilKondisiPsi();
            emptTextKPsi();
        }
    }
    
    private void gantiTP() {
        if (tabMode10.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data tes psikologi pasien pada tabel...!!!!");
            tbTesPsiko.requestFocus();
        } else {
            Sequel.mengedit("psikolog_tes_psikologi", "no_rawat='" + wktSimpan + "'",
                    "rencana_tes='" + Valid.SetTgl(tgl_rencana.getSelectedItem() + "") + "',"
                    + "tes_psikologis='" + TTesPsiko.getText() + "', "
                    + "waktu='" + TWaktu.getText() + "',tester='" + TTester.getText() + "'");
            tampilTesPsikologi();
            emptTextTP();
        }
    }
    
    private void gantiMF() {
        if (tabMode11.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data manifestasi fungsi psikologis pasien pada tabel...!!!!");
            tbManifestasi.requestFocus();
        } else {
            Sequel.mengedit("psikolog_manifestasi_fungsi", "no_rawat='" + wktSimpan + "'", "manifestasi='" + TManifes.getText() + "'");
            tampilManifestasiFungsi();
            emptTextMF();
        }
    }
    
    private void gantiD() {
        if (tabMode12.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data diagnosis pasien pada tabel...!!!!");
            tbDiagnosis.requestFocus();
        } else if (TDiagUtama.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika diagnosis utama (dx) kosong, isi dengan strip (-)...!!!!");
            TDiagUtama.requestFocus();
        } else if (TDiagBanding.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika diagnosis banding (dd) kosong, isi dengan strip (-)...!!!!");
            TDiagBanding.requestFocus();
        } else {
            Sequel.mengedit("psikolog_diagnosis", "no_rawat='" + wktSimpan + "'", 
                    "kesan_awal='" + TKesan.getText() + "',diagnosa_utama='" + TDiagUtama.getText() + "',diagnosa_banding='" + TDiagBanding.getText() + "'");
            tampilDiagnosis();
            emptTextD();
        }
    }
    
    private void gantiP() {
        if (tabMode13.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data prognosis pasien pada tabel...!!!!");
            tbPrognosis.requestFocus();
        } else {
            Sequel.mengedit("psikolog_prognosis", "no_rawat='" + wktSimpan + "'", "prognosis='" + Tprognosis.getText() + "'");
            tampilPrognosis();
            emptTextP();
        }
    }
    
    private void gantiRT() {
        if (tabMode15.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data rencana tritmen pada tabel...!!!!");
            tbTritmen.requestFocus();
        } else if (tabMode14.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, item rencana tritmen belum terisi...!!!!");
            BtnPilihTritmen.requestFocus();
        } else if (TPertemuan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Pertemuan Selanjutnya kosong, isi dengan strip (-)...!!!!");
            TPertemuan.requestFocus();
        } else if (TCatatan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Catatan Tambahan kosong, isi dengan strip (-)...!!!!");
            TCatatan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_detail_rencana_tritmen "
                    + "where no_rawat='" + TNoRW.getText() + "' and waktu_simpan='" + wktSimpan + "'");

            Sequel.queryu("delete from psikolog_rencana_tritmen "
                    + "where no_rawat='" + TNoRW.getText() + "' and waktu_simpan='" + wktSimpan + "'");
            simpanRT();
        }
    }
    
    private void getItemKeluhan() {
        wktSimpan = "";
        if (tbItemMasalah.getSelectedRow() != -1) {
            kdkeluhan.setText(tbItemMasalah.getValueAt(tbItemMasalah.getSelectedRow(), 0).toString());
            nmkeluhan.setText(tbItemMasalah.getValueAt(tbItemMasalah.getSelectedRow(), 1).toString());
            masalahLain.setText(tbItemMasalah.getValueAt(tbItemMasalah.getSelectedRow(), 2).toString());
            wktSimpan = tbItemMasalah.getValueAt(tbItemMasalah.getSelectedRow(), 3).toString();
        }
    }

    private void getItemTritmen() {
        wktSimpan = "";
        if (tbItemTritmen.getSelectedRow() != -1) {
            kdtritmen.setText(tbItemTritmen.getValueAt(tbItemTritmen.getSelectedRow(), 0).toString());
            nmtritmen.setText(tbItemTritmen.getValueAt(tbItemTritmen.getSelectedRow(), 1).toString());
            tritmenLain.setText(tbItemTritmen.getValueAt(tbItemTritmen.getSelectedRow(), 2).toString());
            wktSimpan = tbItemTritmen.getValueAt(tbItemTritmen.getSelectedRow(), 3).toString();
        }
    }
    
    private void tampilItemKeluhan() {
        Valid.tabelKosong(tabMode4);
        try {
            ps5 = koneksi.prepareStatement("SELECT pd.kd_permasalahan, pm.deskripsi_keluhan, pd.keterangan, "
                    + "pd.waktu_simpan FROM psikolog_detail_keluhan pd "
                    + "INNER JOIN psikolog_master_keluhan pm ON pm.kd_permasalahan=pd.kd_permasalahan "
                    + "WHERE pd.no_rawat LIKE '%" + TNoRW.getText() + "%'");
            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    tabMode4.addRow(new Object[]{
                        rs5.getString("kd_permasalahan"),
                        rs5.getString("deskripsi_keluhan"), 
                        rs5.getString("keterangan"), 
                        rs5.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilItemTritmen() {
        Valid.tabelKosong(tabMode14);
        try {
            ps15 = koneksi.prepareStatement("SELECT pd.kd_rencana, pm.deskripsi, pd.ket_lainnya, "
                    + "pd.waktu_simpan FROM psikolog_detail_rencana_tritmen pd "
                    + "INNER JOIN psikolog_master_rencana_tritmen pm ON pm.kd_rencana=pd.kd_rencana "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                    + "INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat "
                    + "WHERE pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa'");
            try {
                rs15 = ps15.executeQuery();
                while (rs15.next()) {
                    tabMode14.addRow(new Object[]{
                        rs15.getString("kd_rencana"),
                        rs15.getString("deskripsi"), 
                        rs15.getString("ket_lainnya"), 
                        rs15.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs15 != null) {
                    rs15.close();
                }
                if (ps15 != null) {
                    ps15.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilPreview() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsc1 = koneksi.prepareStatement("SELECT rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                        + "p.pekerjaan, p.pnd, p.stts_nikah, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, p.no_tlp FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab WHERE "
                        + "p.no_rkm_medis='" + TNoRM.getText() + "' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa' "
                        + "and rp.no_rawat ='" + TNoRW.getText() + "'").executeQuery();
                
                while (rsc1.next()) {
                    //data diri klien
                    htmlContent.append(
                            "<table width='100%' class='adaborder'>"
                            + "<thead>"
                            + "  <tr>"
                            + "    <th colspan='2'><b>Data Diri Klien</b></th>"
                            + "  </tr>"
                            + "</thead>"
                            + "<tbody>"
                            + "  <tr>"
                            + "    <td valign='top' width='210px'>Nomor Registrasi Klien</td>"
                            + "    <td valign='top'>: " + rsc1.getString("no_rkm_medis") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Tanggal Kedatangan</td>"
                            + "    <td valign='top'>: " + Sequel.cariIsi("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%d')") + " "
                            + Sequel.bulanINDONESIA("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%m')") + " "
                            + Sequel.cariIsi("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%Y')") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Nama Klien</td>"
                            + "    <td valign='top'>: " + rsc1.getString("nm_pasien") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Jenis Kelamin</td>"
                            + "    <td valign='top'>: " + rsc1.getString("jk") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Tempat & Tanggal Lahir</td>"
                            + "    <td valign='top'>: " + rsc1.getString("ttl") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Pekerjaan</td>"
                            + "    <td valign='top'>: " + rsc1.getString("pekerjaan") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Pendidikan</td>"
                            + "    <td valign='top'>: " + rsc1.getString("pnd") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Status Perkawinan</td>"
                            + "    <td valign='top'>: " + rsc1.getString("stts_nikah") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Alamat</td>"
                            + "    <td valign='top'>: " + rsc1.getString("almt") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>No. HP</td>"
                            + "    <td valign='top'>: " + rsc1.getString("no_tlp") + "</td>"
                            + "  </tr>"
                            + "</tbody>"
                            + "</table>"
                            + "<br>"
                    );
                    
                    //komposisi anggota keluarga pasien
                    try {
                        rsc2 = koneksi.prepareStatement("SELECT ifnull(nama,'') nama, concat(ifnull(umur,''),' ',ifnull(sttsumur,'')) umur, "
                                + "ifnull(jk,'') jk, ifnull(posisi,'') posisi, ifnull(keterangan,'') keterangan from psikolog_anggota_keluarga WHERE "
                                + "no_rkm_medis ='" + TNoRM.getText() + "' and rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc2.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='5'><b>Komposisi Anggota Keluarga Klien</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td width='50px'><b>Nama</b></td>"
                                    + "    <td width='15px'><b>Umur</b></td>"
                                    + "    <td width='30px'><b>Jenis Kelamin</b></td>"
                                    + "    <td width='75px'><b>Posisi</b></td>"
                                    + "    <td width='75px'><b>Keterangan</b></td>"
                                    + "  </tr>"                                 
                            );
                            rsc2.beforeFirst();
                            while (rsc2.next()) {
                                htmlContent.append(
                                        "<tr>"
                                        + "<td valign='top' width='50px'>" + rsc2.getString("nama") + "</td>"
                                        + "<td valign='top' width='15px'>" + rsc2.getString("umur") + "</td>"
                                        + "<td valign='top' width='30px'>" + rsc2.getString("jk").replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc2.getString("posisi") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc2.getString("keterangan") + "</td>"
                                        + "</tr>");
                            }
                            htmlContent.append(
                                    "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc2 != null) {
                            rsc2.close();
                        }
                    }
                    
                    //identitas pasangan
                    try {
                        rsc3 = koneksi.prepareStatement("SELECT ifnull(nama,'') nama, concat(ifnull(umur,''),' tahun') usia, ifnull(pendidikan,'') pendidikan, "
                                + "ifnull(pekerjaan,'') pekerjaan, ifnull(agama,'') agama, CONCAT(ifnull(anak_ke,''),' dari ',ifnull(dari_anak,''),' bersaudara') anak, "
                                + "CONCAT(ifnull(perkawinan_ke,''),' dari ',ifnull(dari_kawin,''),' tahun') kawin, "
                                + "ifnull(keluhan,'') keluhan, ifnull(alamat,'') alamat, ifnull(no_hp,'') no_hp, "
                                + "ifnull(keterangan,'') keterangan FROM psikolog_umum_dewasa_identitas_pas WHERE no_rkm_medis ='" + TNoRM.getText() + "'").executeQuery();

                        if (rsc3.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='3'><b>Identitas Pasangan</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td colspan='2' align='center'>SUAMI / ISTRI</td>"
                                    + "    <td width='170px' align='center'>KELUHAN</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='100px'>Nama</td>"
                                    + "    <td valign='top' width='360px'>: " + rsc3.getString("nama") + "</td>"
                                    + "    <td rowspan='10' valign='top'>" + rsc3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Umur</td>"
                                    + "    <td valign='top'>: " + rsc3.getString("usia") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pendidikan</td>"
                                    + "    <td valign='top'>: " + rsc3.getString("pendidikan") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pekerjaan</td>"
                                    + "    <td valign='top'>: " + rsc3.getString("pekerjaan") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Agama</td>"
                                    + "    <td valign='top'>: "+ rsc3.getString("agama") +"</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Anak Ke -</td>"
                                    + "    <td valign='top'>: "+ rsc3.getString("anak") +"</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Perkawinan</td>"
                                    + "    <td valign='top'>: "+ rsc3.getString("kawin") +"</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Alamat</td>"
                                    + "    <td valign='top'>: "+ rsc3.getString("alamat").replaceAll("(\r\n|\r|\n|\n\r)","<br>") +"</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>No. HP</td>"
                                    + "    <td valign='top'>: "+ rsc3.getString("no_hp") +"</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Keterangan</td>"
                                    + "    <td valign='top'>: "+ rsc3.getString("keterangan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") +"</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"  
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc3 != null) {
                            rsc3.close();
                        }
                    } 
                    
                    //keluhan permasalahan yang muncul
                    try {
                        rsc4 = koneksi.prepareStatement("SELECT ifnull(pm.deskripsi_keluhan,'') deskripsi_keluhan, "
                                + "if(pd.keterangan='','-',pd.keterangan) keterangan FROM psikolog_detail_keluhan pd "
                                + "INNER JOIN psikolog_master_keluhan pm ON pm.kd_permasalahan=pd.kd_permasalahan "
                                + "INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat "
                                + "WHERE pd.no_rawat ='" + TNoRW.getText() + "' and pdd.rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc4.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Keluhan Permasalahan Yang Muncul</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td width='370px'><b>Item Keluhan Permasalahan</b></td>"
                                    + "    <td valign='top'><b>Keterangan Lainnya</b></td>"
                                    + "  </tr>"                                 
                            );
                            rsc4.beforeFirst();
                            while (rsc4.next()) {
                                htmlContent.append(
                                        "<tr>"
                                        + "<td valign='top'>" + rsc4.getString("deskripsi_keluhan") + "</td>"
                                        + "<td valign='top'>" + rsc4.getString("keterangan") + "</td>"
                                        + "</tr>");
                            }
                            htmlContent.append(
                                    "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc4 != null) {
                            rsc4.close();
                        }
                    }
                    
                    //pemeriksaan psikologis klinis
                    try {
                        rsc5 = koneksi.prepareStatement("SELECT ifnull(permasalahan_saat_ini,'') permasalahan_saat_ini, ifnull(alasan_mencari_bantuan,'') alasan_mencari_bantuan, "
                                + "ifnull(permasalahan_dinilai,'') permasalahan_dinilai, ifnull(alasan_permasalahan_dinilai,'') alasan_permasalahan_dinilai, "
                                + "ifnull(harapan,'') harapan, ifnull(perubahan_diinginkan_diri_sendiri,'') perubahan_diinginkan_diri_sendiri, "
                                + "ifnull(perubahan_diinginkan_keluarga,'') perubahan_diinginkan_keluarga FROM psikolog_wawancara_klinis WHERE "
                                + "no_rawat ='" + TNoRW.getText() + "' and rekam_psikologis='Umum/Dewasa'").executeQuery();

                        if (rsc5.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Pemeriksaan Psikologi Klinis</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td align='center'><b>Wawancara</b></td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Permasalahan Saat Ini<br><br>"
                                    + "" + rsc5.getString("permasalahan_saat_ini").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Alasan Yang Membuat Pasien Mencari Bantuan Saat Ini<br><br>"
                                    + "" + rsc5.getString("alasan_mencari_bantuan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Permasalahan Dinilai<br><br>"
                                    + "" + rsc5.getString("permasalahan_dinilai") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Alasan<br><br>"
                                    + "" + rsc5.getString("alasan_permasalahan_dinilai").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Harapan<br><br>"
                                    + "" + rsc5.getString("harapan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Perubahan Yang Diinginkan Dari Diri Sendiri<br><br>"
                                    + "" + rsc5.getString("perubahan_diinginkan_diri_sendiri").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Perubahan Yang Diinginkan Dari Keluarga<br><br>"
                                    + "" + rsc5.getString("perubahan_diinginkan_keluarga").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc5 != null) {
                            rsc5.close();
                        }
                    } 
                    
                    //riwayat hidup singkat
                    try {
                        rsc6 = koneksi.prepareStatement("SELECT ifnull(masa_kanak,'-') masa_kanak, ifnull(masa_remaja,'-') masa_remaja, "
                                + "ifnull(masa_dewasa,'-') masa_dewasa, ifnull(riw_penyakit_fisik,'-') riw_penyakit_fisik, ifnull(riw_pengobatan,'-') riw_pengobatan "
                                + "FROM psikolog_umum_dewasa_riw_hidup WHERE no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                        if (rsc6.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Riwayat Hidup Singkat<br>(Masa Kanak-kanak, Remaja, Dewasa)</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"                                    
                                    + "  <tr>"
                                    + "    <td>&#10004 Masa Kanak-kanak :<br><br>"
                                    + "" + rsc6.getString("masa_kanak").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Masa Remaja :<br><br>"
                                    + "" + rsc6.getString("masa_remaja").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Masa Dewasa :<br><br>"
                                    + "" + rsc6.getString("masa_dewasa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Riwayat Penyakit Fisik / Psikologis :<br><br>"
                                    + "" + rsc6.getString("riw_penyakit_fisik").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Riwayat Pengobatan / Konsultasi :<br><br>"
                                    + "" + rsc6.getString("riw_pengobatan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc6 != null) {
                            rsc6.close();
                        }
                    } 
                    
                    //observasi kondisi psikologis pasien secara umum
                    try {
                        rsc7 = koneksi.prepareStatement("SELECT ifnull(penampilan,'') penampilan, ifnull(ekspresi_wajah,'') ekspresi_wajah, "
                                + "ifnull(perasaan_suasana_hati,'') perasaan_suasana_hati, ifnull(tingkah_laku,'') tingkah_laku, ifnull(fungsi_umum,'') fungsi_umum, "
                                + "ifnull(fungsi_intelektual,'') fungsi_intelektual, ifnull(pengalaman_kerja,'') pengalaman_kerja, ifnull(lainlain,'') lainlain "
                                + "FROM psikolog_observasi WHERE no_rawat ='" + TNoRW.getText() + "' and rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc7.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Observasi<br>Kondisi Psikologis Pasien Secara Umum</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='180px'>Penampilan</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("penampilan") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Ekspresi Wajah</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("ekspresi_wajah") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Perasaan / Suasana Hati</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("perasaan_suasana_hati") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Tingkah Laku</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("tingkah_laku") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Fungsi Umum</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("fungsi_umum") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Fungsi Intelektual</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("fungsi_intelektual") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pengalaman Kerja</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("pengalaman_kerja") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Lain-lain</td>"
                                    + "    <td valign='top'>: " + rsc7.getString("lainlain") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc7 != null) {
                            rsc7.close();
                        }
                    } 
                    
                    //kondisi psikopatologis pasien
                    try {
                        rsc8 = koneksi.prepareStatement("SELECT ifnull(delusi_waham,'') delusi_waham, ifnull(proses_pikiran,'') proses_pikiran, ifnull(halusinasi,'') halusinasi, "
                                + "ifnull(afek,'') afek, ifnull(insight,'') insight, ifnull(kesadaran,'') kesadaran, ifnull(orientasi_waktu,'') orientasi_waktu, "
                                + "ifnull(orientasi_tempat,'') orientasi_tempat, ifnull(orientasi_ruang,'') orientasi_ruang, ifnull(perhatian,'') perhatian, "
                                + "ifnull(penilaian,'') penilaian, ifnull(kontrol_thdp_impuls,'') kontrol_thdp_impuls FROM psikolog_umum_dewasa_psikopatologis WHERE "
                                + "no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                        if (rsc8.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Kondisi Psikopatologis Pasien</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='180px'>Delusi / Waham</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("delusi_waham") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Proses Pikiran</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("proses_pikiran") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Halusinasi</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("halusinasi") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Afek</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("afek") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'><i>Insight</i></td>"
                                    + "    <td valign='top'>: " + rsc8.getString("insight") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Kesadaran</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("kesadaran") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Orientasi Waktu</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("orientasi_waktu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Orientasi Tempat</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("orientasi_tempat") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Orientasi Ruang</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("orientasi_ruang") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Perhatian</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("perhatian") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Penilaian</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("penilaian") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Kontrol Terhadap Impuls</td>"
                                    + "    <td valign='top'>: " + rsc8.getString("kontrol_thdp_impuls") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc8 != null) {
                            rsc8.close();
                        }
                    } 
                    
                    //tes psikologi
                    try {
                        rsc9 = koneksi.prepareStatement("SELECT DATE_FORMAT(ifnull(rencana_tes,date(now())),'%d-%m-%Y') tglRencana, "
                                + "ifnull(tes_psikologis,'') tes_psikologis, ifnull(waktu,'') waktu, ifnull(tester,'') tester FROM psikolog_tes_psikologi WHERE "
                                + "no_rawat ='" + TNoRW.getText() + "' and rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc9.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='5'><b>Tes Psikologi</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td align='center' rowspan='2'><b>Rencana Tes</b></td>"
                                    + "    <td align='center' colspan='3'><b>Pelaksanaan Tes</b></td>"
                                    + "    <td align='center' rowspan='2'><b>Hasil</b></td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td align='center'><b>Tes Psikologi</b></td>"
                                    + "    <td align='center'><b>Waktu</b></td>"
                                    + "    <td align='center'><b>Tester</b></td>"
                                    + "  </tr>"         
                            );
                            rsc9.beforeFirst();
                            while (rsc9.next()) {
                                htmlContent.append(
                                        "<tr>"
                                        + "<td align='center'>" + rsc9.getString("tglRencana") + "</td>"
                                        + "<td>" + rsc9.getString("tes_psikologis") + "</td>"
                                        + "<td>" + rsc9.getString("waktu") + "</td>"
                                        + "<td>" + rsc9.getString("tester") + "</td>"
                                        + "<td></td>"
                                        + "</tr>");
                            }
                            htmlContent.append(
                                    "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc9 != null) {
                            rsc9.close();
                        }
                    }
                    
                    //manifestasi fungsi psikologis
                    try {
                        rsc10 = koneksi.prepareStatement("SELECT ifnull(pm.manifestasi,'') manifestasi FROM psikolog_manifestasi_fungsi pm "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                                + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pm.no_rawat WHERE "
                                + "pm.no_rawat ='" + TNoRW.getText() + "' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc10.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Manifestasi Fungsi Psikologis</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top'>" + rsc10.getString("manifestasi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc10 != null) {
                            rsc10.close();
                        }
                    } 
                    
                    //diagnosis (berdasarkan DSM/ICD/PPDGJ)
                    try {
                        rsc11 = koneksi.prepareStatement("SELECT IFNULL(pd.kesan_awal,'') kesan_awal, ifnull(pd.diagnosa_utama,'') diagnosa_utama, "
                                + "ifnull(pd.diagnosa_banding,'') diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                                + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat WHERE "
                                + "pd.no_rawat ='" + TNoRW.getText() + "' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pdd.rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc11.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Diagnosis (Berdasarkan DSM / ICD / PPDGJ)</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='230px'>Kesan Awal</td>"
                                    + "    <td>: " + rsc11.getString("kesan_awal").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Diagnosis Utama (dx)</td>"
                                    + "    <td valign='top'>: " + rsc11.getString("diagnosa_utama") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Diagnosis Banding (dd)</td>"
                                    + "    <td valign='top'>: " + rsc11.getString("diagnosa_banding") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc11 != null) {
                            rsc11.close();
                        }
                    } 
                    
                    //prognosis
                    try {
                        rsc12 = koneksi.prepareStatement("SELECT ifnull(pp.prognosis,'') prognosis FROM psikolog_prognosis pp "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                                + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pp.no_rawat WHERE "
                                + "pp.no_rawat ='" + TNoRW.getText() + "' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc12.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Prognosis</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top'>" + rsc12.getString("prognosis").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc12 != null) {
                            rsc12.close();
                        }
                    } 
                    
                    //rencana tritmen
                    try {
                        rsc13 = koneksi.prepareStatement("SELECT ifnull(pr.tritmen,'') tritmen, ifnull(pr.pertemuan_selanjutnya,'') pertemuan_selanjutnya, "
                                + "ifnull(pr.cttn_tambahan,'') cttn_tambahan FROM psikolog_rencana_tritmen pr "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                                + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pr.no_rawat WHERE "
                                + "pr.no_rawat ='" + TNoRW.getText() + "' and rp.umurdaftar>=18 and rp.sttsumur='Th' and pd.rekam_psikologis='umum_dewasa'").executeQuery();

                        if (rsc13.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Rencana Tritmen</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='230px'>Deskripsi Tritmen</td>"
                                    + "    <td valign='top'>: " + rsc13.getString("tritmen") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pertemuan Selanjutnya</td>"
                                    + "    <td valign='top'>: " + rsc13.getString("pertemuan_selanjutnya") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Catatan Tambahan</td>"
                                    + "    <td valign='top'>: " + rsc13.getString("cttn_tambahan") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                                    + "<br>"
                            );
                            
                        }                            
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc13 != null) {
                            rsc13.close();
                        }
                    }
                    
                    //tertanda psikolog pemeriksa                    
                    htmlContent.append("<table width='100%' class='noborder'"
                            + "<tbody>"
                            + "  <tr>"
                            + "    <td colspan='4'></td>"
                            + "    <td width='350px' colspan='1'>" + akses.getkabupatenrs() + ", "
                            + Sequel.cariIsi("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%d')") + " "
                            + Sequel.bulanINDONESIA("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%m')") + " "
                            + Sequel.cariIsi("select DATE_FORMAT('" + rsc1.getString("tgl_registrasi") + "','%Y')") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td colspan='4'></td>"
                            + "    <td colspan='1'><b>Psikolog Pemeriksa,</b><br><br><br><br><br></td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td colspan='4'></td>"
                            + "    <td colspan='1'>( " + TnmPsikolog.getText() + " )</td>"
                            + "  </tr>"
                            + "</tbody>"
                            + "</table>"
                            + "<br>"
                    );

                }
                LoadHTML.setText(
                        "<html>"                        
                        + "<body>"
                        + "<head></head>"     
                        + htmlContent.toString()
                        + "</body>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsc1 != null) {
                    rsc1.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void cetakRPD() {
        try {
            File f = new File("rekam_psikologis_umum_dewasa.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(LoadHTML.getText().replaceAll("<head>", "<head>"
                    + "<style>"
                    + "table { font: 14px tahoma;}"
                    + ".adaborder {"
                    + "    width: 100%;"
                    + "  border-collapse: collapse;"
                    + "}"
                    + ""
                    + ".adaborder th{"
                    + "  border: 1px solid black;"
                    + "  padding: 5px;"
                    + "}"
                    + ""
                    + ".adaborder td{"
                    + "  border: 1px solid black;"
                    + "  padding: 5px;"
                    + "}"
                    + ""
                    + ".noborder th{"
                    + "  border: 0px;"
                    + "  padding: 5px;"
                    + "}"
                    + ""
                    + ".noborder td{"
                    + "  border: 0px;"
                    + "  padding: 5px;"
                    + "}"
                    + "</style>"
                            
                    + "<table width='100%' class='noborder'"
                            +"<tr>"
                            +"<td></td>"
                            +"<td></td>"
                            +"<td align='right'><font size='4' face='Tahoma'><b>RAHASIA</font></b></td>"
                            +"</tr>"
                    + "<tr>"
                    + "<td width='30%'>"
                    + "<img src='setting/logo.jpg' height=60px align='right'>"
                    + "</td>"
                    + "<td valign='top' align='center' width='45%'>"
                    + "<font size='4' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                    + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                    + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "<br></font>"
                    + "</td>"
                    + "<td width='40%'>"
                    + "</td>"
                    + "</tr>"
                    + "<tr class='isi2'>"
                    + "<td colspan=3>"
                    + "<hr><center><font size='3' face='Tahoma'><b>REKAM PSIKOLOGIS (Umum / Dewasa)<br>Poliklinik Psikologi<br><br></font>"
                    + "</td>"
                    + "</tr>"
                    + "</table>")
            );
            bw.close();
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
