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
public final class DlgRekamPsikologisAnak extends javax.swing.JDialog {
    private final DefaultTableModel tabMode1, tabMode2, tabMode3, tabMode4, 
            tabMode5, tabMode6, tabMode7, tabMode8, tabMode9, tabMode10, 
            tabMode11, tabMode12, tabMode13, tabMode14, tabMode15, tabMode16, tabMode17, tabMode18, tabMode19;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12, ps13, ps14, ps15, ps16, ps17, ps18, ps19;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rs12, rs13, rs14, rs15, rs16, rs17, rs18, rs19,
            rsc1, rsc2, rsc3, rsc4, rsc5, rsc6, rsc7, rsc8, rsc9, rsc10, rsc11, rsc12, rsc13, rsc14, rsc15, rsc16, rsc17;
    private DlgMasterKeluhanPsikologis keluhan = new DlgMasterKeluhanPsikologis(null, false);
    private DlgMasterRencanaTritmenPsikologi tritmen = new DlgMasterRencanaTritmenPsikologi(null, false);
    private DlgCariPenyakit penyakit = new DlgCariPenyakit(null, false);
    private DlgCariDokter petugas = new DlgCariDokter(null, false);
    private int i = 0, x = 0, cekRPA = 0, pilihan = 0, data = 0;
    private final Properties prop = new Properties();
    private String tglKedatangan = "", wktSimpan = "", jkel = "", dataMasalah = "", nilaiMasalah = "", dataTritmen = "";

    /* Creates new form DlgPerawatan
     *
     * @param parent
     * @param modal
     */
    public DlgRekamPsikologisAnak(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabMode1 = new DefaultTableModel(null, new Object[]{
            "norawat", "tglreg", "No. RM", "Tgl. Kedatangan", "Nama Pasien/Klien", "Jns. Kelamin", "Tempat & Tgl. Lahir",
            "Agama", "Pendidikan", "Urutan Kelahiran", "Alamat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbdatadiri.setModel(tabMode1);
        tbdatadiri.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbdatadiri.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
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
                column.setPreferredWidth(170);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(350);
            }
        }
        tbdatadiri.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien/Klien", "Nama Saudara", "Umur", "Jns. Kelamin", "Posisi", "Keterangan", "waktusimpan", "usia", "sttsUmur"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbIdentitasSaudara.setModel(tabMode2);
        tbIdentitasSaudara.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbIdentitasSaudara.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbIdentitasSaudara.getColumnModel().getColumn(i);
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
        tbIdentitasSaudara.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien/Klien", 
            "Nama Ayah", "Umur Ayah", "Pendidikan Ayah", "Pekerjaan Ayah", "Agama Ayah", "Anak Ke Ayah",
            "Perkawinan Ke Ayah", "Alamat Ayah", "No. HP Ayah", "Keterangan Ayah",
            "Nama Ibu", "Umur Ibu", "Pendidikan Ibu", "Pekerjaan Ibu", "Agama Ibu", "Anak Ke Ibu",
            "Perkawinan Ke Ibu", "Alamat Ibu", "No. HP Ibu", "Keterangan Ibu", "wktsimpan","umurAyah","umurIbu"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbIdentitasOrtu.setModel(tabMode3);
        tbIdentitasOrtu.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbIdentitasOrtu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbIdentitasOrtu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(190);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(110);                
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(130);
            } else if (i == 9) {
                column.setPreferredWidth(300);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(200);
            } else if (i == 12) {
                column.setPreferredWidth(200);
            } else if (i == 13) {
                column.setPreferredWidth(75);
            } else if (i == 14) {
                column.setPreferredWidth(110);
            } else if (i == 15) {
                column.setPreferredWidth(110);
            } else if (i == 16) {
                column.setPreferredWidth(110);
            } else if (i == 17) {
                column.setPreferredWidth(90);
            } else if (i == 18) {
                column.setPreferredWidth(130);
            } else if (i == 19) {
                column.setPreferredWidth(300);
            } else if (i == 20) {
                column.setPreferredWidth(90);
            } else if (i == 21) {
                column.setPreferredWidth(200);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbIdentitasOrtu.setDefaultRenderer(Object.class, new WarnaTable());
        
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
            "norawat", "No. RM", "Nama Pasien", "Permasalahan Saat Ini", "Alasan Yang Membuat Pasien Mencari Bantuan Kali Ini",
            "Permasalahan Dinilai", "Alasan", "Harapan", "Perubahan Yang Diinginkan Dari Anak", "Perubahan Yang Diinginkan Dari Diri Sendiri",
            "Perubahan Yang Diinginkan Dari Keluarga"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPsikologiKlinis.setModel(tabMode6);
        tbPsikologiKlinis.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPsikologiKlinis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
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
            } else if (i == 10) {
                column.setPreferredWidth(350);
            } 
        }
        tbPsikologiKlinis.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode7 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Demam Tinggi", "Pneumonia", "Flu", "Ensefalitis", "Meningitis", 
            "Kejang", "Tidak Sadarkan Diri", "Geger Otak", "Pingsan", "Pusing", "Permasalahan Terkait Amandel", 
            "Permasalahan Penglihatan", "Permasalahan Pendengaran", "Permasalahan Terkait Gigi", "Permasalahan Berat Badan",
            "Alergi", "Permasalahan Kulit", "Asma", "Sakit Kepala", "Permasalahan Terkait Perut", "Rawan Kecelakaan", 
            "Anemia", "Tekanan Darah Tinggi/Rendah", "Permasalahan Terkait Sinus", "Permasalahan Jantung", "Hiperaktif",
            "Penyakit Lain", "Pernah Dirawat Di Rumah Sakit"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbRiwayatKesehatan.setModel(tabMode7);
        tbRiwayatKesehatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayatKesehatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 31; i++) {
            TableColumn column = tbRiwayatKesehatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(110);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setPreferredWidth(80);
            } else if (i == 13) {
                column.setPreferredWidth(180);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            } else if (i == 15) {
                column.setPreferredWidth(150);
            } else if (i == 16) {
                column.setPreferredWidth(150);
            } else if (i == 17) {
                column.setPreferredWidth(150);
            } else if (i == 18) {
                column.setPreferredWidth(80);
            } else if (i == 19) {
                column.setPreferredWidth(150);
            } else if (i == 20) {
                column.setPreferredWidth(80);
            } else if (i == 21) {
                column.setPreferredWidth(90);
            } else if (i == 22) {
                column.setPreferredWidth(150);
            } else if (i == 23) {
                column.setPreferredWidth(150);
            } else if (i == 24) {
                column.setPreferredWidth(80);
            } else if (i == 25) {
                column.setPreferredWidth(200);
            } else if (i == 26) {
                column.setPreferredWidth(150);
            } else if (i == 27) {
                column.setPreferredWidth(150);
            } else if (i == 28) {
                column.setPreferredWidth(90);
            } else if (i == 29) {
                column.setPreferredWidth(250);
            } else if (i == 30) {
                column.setPreferredWidth(250);
            } 
        }
        tbRiwayatKesehatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode8 = new DefaultTableModel(null, new Object[]{
            "norawat","No. RM", "Nama Pasien", "Diinginkan", "Direncanakan", "Proses Kehamilan Normal", 
            "Jika Ibu Sakit/Tertekan", "Dukungan & Penerimaan Suami", "Lama Proses Lahir", "Jika Prematur", "Jika Terlambat","Proses Melahirkan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbRiwayatPerkembangan.setModel(tabMode8);
        tbRiwayatPerkembangan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayatPerkembangan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbRiwayatPerkembangan.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(135);
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
            } else if (i == 11) {
                column.setPreferredWidth(300);
            } 
        }
        tbRiwayatPerkembangan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode9 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Duduk", "Merangkak", "Berjalan", "Bicara 1 Kata", "Bicara Dengan Kalimat", 
            "Latihan BAK Pada Tempatnya", "Latihan BAB Pada Tempatnya", "Relasi Anak", "Kebiasaan Anak"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTahapPerkembangan.setModel(tabMode9);
        tbTahapPerkembangan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTahapPerkembangan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbTahapPerkembangan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(200);
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
            } 
        }
        tbTahapPerkembangan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode10 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Nama Sekolah", "Kota", "Tahun Masuk", "Tahun Selesai", "Lama Bersekolah", "waktuSimpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbRiwayatSekolah.setModel(tabMode10);
        tbRiwayatSekolah.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayatSekolah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbRiwayatSekolah.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(190);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbRiwayatSekolah.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode16 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Jenis Sekolah", "Program Akselerasi", "Pernah Tinggal Kelas",
            "Kesulitan Belajar Spesifik", "Semangat Pergi Sekolah", "Pernah Diskor/Dikeluarkan Dari Sekolah", "Prestasi Disekolah/Tempat Kursus",
            "Pelajaran Favorit", "Pelajaran Dirasa Sulit", "Minat, Hobi Keterampilan Dikuasai"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDisekolah.setModel(tabMode16);
        tbDisekolah.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDisekolah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbDisekolah.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(135);
            } else if (i == 7) {
                column.setPreferredWidth(135);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            }
        }
        tbDisekolah.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode17 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Prognosis"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPrognosis.setModel(tabMode17);
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
        
        tabMode18 = new DefaultTableModel(null, new Object[]{
            "kdrencana", "Deskrpisi Rencana Tritmen", "Keterangan Lainnya", "waktusimpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemTritmen.setModel(tabMode18);
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
        
        tabMode19 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Rencana Tritmen", "Pertemuan Selanjutnya", "Catatan Tambahan", "waktusimpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTritmen.setModel(tabMode19);
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
        
        tabMode11 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Penampilan", "Ekspresi Wajah", "Perasaan/Suasana Hati",
            "Tingkah Laku", "Fungsi Umum", "Fungsi Intelektual", "Lain-lain"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObservasi.setModel(tabMode11);
        tbObservasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObservasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbObservasi.getColumnModel().getColumn(i);
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
            } 
        }
        tbObservasi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode12 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Rencana Tes", "Tes Psikologis", "Waktu", "Tester", "tglrencanates"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTesPsikolog.setModel(tabMode12);
        tbTesPsikolog.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTesPsikolog.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbTesPsikolog.getColumnModel().getColumn(i);
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
        tbTesPsikolog.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode13 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Dinamika Psikologi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDinamika.setModel(tabMode13);
        tbDinamika.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDinamika.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbDinamika.getColumnModel().getColumn(i);
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
        tbDinamika.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode14 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Manifestasi Fungsi Psikologis"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbManifestasi.setModel(tabMode14);
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
        
        tabMode15 = new DefaultTableModel(null, new Object[]{
            "norawat", "No. RM", "Nama Pasien", "Kesan Awal", "Diagnosis Utama (dx)", "Diagnosis Banding (dd)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDiagnosis.setModel(tabMode15);
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
        
        keluhan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekamPsikologisAnak")) {
                    if (keluhan.getTable().getSelectedRow() != -1) {
                        kdkeluhan.setText(keluhan.getTable().getValueAt(keluhan.getTable().getSelectedRow(), 0).toString());
                        nmkeluhan.setText(keluhan.getTable().getValueAt(keluhan.getTable().getSelectedRow(), 1).toString());
                        
                        if (kdkeluhan.getText().equals("KP0096")) {
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
                if (akses.getform().equals("DlgRekamPsikologisAnak")) {
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
                if (akses.getform().equals("DlgRekamPsikologisAnak")) {
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
                if (akses.getform().equals("DlgRekamPsikologisAnak")) {
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
                if (akses.getform().equals("DlgRekamPsikologisAnak")) {
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
                if (akses.getform().equals("DlgRekamPsikologisAnak")) {
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
                if (akses.getform().equals("DlgRekamPsikologisAnak")) {
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
        TumurSau.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurSau));
        TumurAyah.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurAyah));
        TumurIbu.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurIbu));
        TnoHpAyah.setDocument(new batasInput((byte) 13).getKata(TnoHpAyah));
        TnoHpIbu.setDocument(new batasInput((byte) 13).getKata(TnoHpIbu));
        TthnMsk.setDocument(new batasInput((byte) 4).getOnlyAngka(TthnMsk));
        TthnSelesai.setDocument(new batasInput((byte) 4).getOnlyAngka(TthnSelesai));
        TlamaSekolah.setDocument(new batasInput((byte) 100).getKata(TlamaSekolah));
        
        TDemam.setDocument(new batasInput((byte) 10).getKata(TDemam));
        TPneum.setDocument(new batasInput((byte) 10).getKata(TPneum));
        TFlu.setDocument(new batasInput((byte) 10).getKata(TFlu));
        TEnse.setDocument(new batasInput((byte) 10).getKata(TEnse));
        TMeni.setDocument(new batasInput((byte) 10).getKata(TMeni));
        TKejang.setDocument(new batasInput((byte) 10).getKata(TKejang));
        TTidak.setDocument(new batasInput((byte) 10).getKata(TTidak));
        TGeger.setDocument(new batasInput((byte) 10).getKata(TGeger));
        TPingsan.setDocument(new batasInput((byte) 10).getKata(TPingsan));
        TPusing.setDocument(new batasInput((byte) 10).getKata(TPusing));
        TAmandel.setDocument(new batasInput((byte) 10).getKata(TAmandel));
        TLihat.setDocument(new batasInput((byte) 10).getKata(TLihat));
        TDengar.setDocument(new batasInput((byte) 10).getKata(TDengar));
        TGigi.setDocument(new batasInput((byte) 10).getKata(TGigi));
        TBerat.setDocument(new batasInput((byte) 10).getKata(TBerat));
        TAlergi.setDocument(new batasInput((byte) 10).getKata(TAlergi));
        TKulit.setDocument(new batasInput((byte) 10).getKata(TKulit));
        TAsma.setDocument(new batasInput((byte) 10).getKata(TAsma));
        TKepala.setDocument(new batasInput((byte) 10).getKata(TKepala));
        TPerut.setDocument(new batasInput((byte) 10).getKata(TPerut));
        TRawan.setDocument(new batasInput((byte) 10).getKata(TRawan));
        TAnemia.setDocument(new batasInput((byte) 10).getKata(TAnemia));
        TDarah.setDocument(new batasInput((byte) 10).getKata(TDarah));
        TSinus.setDocument(new batasInput((byte) 10).getKata(TSinus));
        TJantung.setDocument(new batasInput((byte) 10).getKata(TJantung));
        THiper.setDocument(new batasInput((byte) 10).getKata(THiper));
        
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
            System.out.println("rekammedis.DlgRekamPsikologisAnak : " + e);
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
        jLabel150 = new widget.Label();
        tritmenLain = new widget.TextBox();
        btnTritmen = new widget.Button();
        jLabel151 = new widget.Label();
        panelGlass17 = new widget.panelisi();
        BtnSimpan3 = new widget.Button();
        BtnEdit2 = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        internalFrameUtama = new widget.InternalFrame();
        TabPsikologis = new javax.swing.JTabbedPane();
        internalFrame0 = new widget.InternalFrame();
        panelGlass16 = new widget.panelisi();
        jLabel72 = new widget.Label();
        TAgama = new widget.TextBox();
        jLabel73 = new widget.Label();
        TPendidikan = new widget.TextBox();
        jLabel74 = new widget.Label();
        TUrutanLahir = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel71 = new widget.Label();
        Ttl = new widget.TextBox();
        Scroll7 = new widget.ScrollPane();
        TAlamat = new widget.TextArea();
        Scroll3 = new widget.ScrollPane();
        tbdatadiri = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbIdentitasSaudara = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel12 = new widget.Label();
        jLabel14 = new widget.Label();
        TposisiSau = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbJK = new widget.ComboBox();
        jLabel21 = new widget.Label();
        TketSau = new widget.TextBox();
        TumurSau = new widget.TextBox();
        jLabel15 = new widget.Label();
        TnmSau = new widget.TextBox();
        cmbUmur = new widget.ComboBox();
        internalFrame2 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbIdentitasOrtu = new widget.Table();
        panelGlass12 = new widget.panelisi();
        Scroll41 = new widget.ScrollPane();
        FormInput4 = new widget.PanelBiasa();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new widget.Label();
        TumurAyah = new widget.TextBox();
        jLabel7 = new widget.Label();
        jLabel9 = new widget.Label();
        TnmAyah = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel10 = new widget.Label();
        TpekerjaanAyah = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel20 = new widget.Label();
        cmbPndAyah = new widget.ComboBox();
        cmbAgamaAyah = new widget.ComboBox();
        TanakKeAyah = new widget.TextBox();
        jLabel24 = new widget.Label();
        TkawinKeAyah = new widget.TextBox();
        jLabel53 = new widget.Label();
        TnoHpAyah = new widget.TextBox();
        Scroll11 = new widget.ScrollPane();
        TalamatAyah = new widget.TextArea();
        jLabel27 = new widget.Label();
        jLabel56 = new widget.Label();
        TKetAyah = new widget.TextBox();
        ChkcopasAyah = new widget.CekBox();
        jPanel2 = new javax.swing.JPanel();
        TnmIbu = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel30 = new widget.Label();
        TumurIbu = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        cmbPndIbu = new widget.ComboBox();
        jLabel37 = new widget.Label();
        TpekerjaanIbu = new widget.TextBox();
        jLabel38 = new widget.Label();
        cmbAgamaIbu = new widget.ComboBox();
        jLabel40 = new widget.Label();
        TanakKeIbu = new widget.TextBox();
        jLabel103 = new widget.Label();
        TkawinKeIbu = new widget.TextBox();
        Scroll36 = new widget.ScrollPane();
        TalamatIbu = new widget.TextArea();
        jLabel52 = new widget.Label();
        jLabel54 = new widget.Label();
        TnoHpIbu = new widget.TextBox();
        jLabel57 = new widget.Label();
        TKetIbu = new widget.TextBox();
        ChkcopasIbu = new widget.CekBox();
        internalFrame3 = new widget.InternalFrame();
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
        internalFrame4 = new widget.InternalFrame();
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
        Scroll38 = new widget.ScrollPane();
        TPerubahanAnak = new widget.TextArea();
        jLabel77 = new widget.Label();
        jLabel102 = new widget.Label();
        internalFrame5 = new widget.InternalFrame();
        Scroll16 = new widget.ScrollPane();
        tbRiwayatKesehatan = new widget.Table();
        panelGlass26 = new widget.panelisi();
        Scroll39 = new widget.ScrollPane();
        FormInput5 = new widget.PanelBiasa();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        TDemam = new widget.TextBox();
        TPneum = new widget.TextBox();
        TFlu = new widget.TextBox();
        TEnse = new widget.TextBox();
        TMeni = new widget.TextBox();
        TKejang = new widget.TextBox();
        TTidak = new widget.TextBox();
        TGeger = new widget.TextBox();
        TPingsan = new widget.TextBox();
        TPusing = new widget.TextBox();
        TAmandel = new widget.TextBox();
        TLihat = new widget.TextBox();
        TDengar = new widget.TextBox();
        TGigi = new widget.TextBox();
        TBerat = new widget.TextBox();
        TAlergi = new widget.TextBox();
        TKulit = new widget.TextBox();
        TAsma = new widget.TextBox();
        TKepala = new widget.TextBox();
        TPerut = new widget.TextBox();
        TRawan = new widget.TextBox();
        TAnemia = new widget.TextBox();
        TDarah = new widget.TextBox();
        TSinus = new widget.TextBox();
        TJantung = new widget.TextBox();
        THiper = new widget.TextBox();
        Scroll22 = new widget.ScrollPane();
        TPenyakit = new widget.TextArea();
        jLabel124 = new widget.Label();
        Scroll23 = new widget.ScrollPane();
        TRumahSakit = new widget.TextArea();
        internalFrame6 = new widget.InternalFrame();
        Scroll17 = new widget.ScrollPane();
        tbRiwayatPerkembangan = new widget.Table();
        panelGlass27 = new widget.panelisi();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel68 = new widget.Label();
        cmbDinginkan = new widget.ComboBox();
        cmbDirencanakan = new widget.ComboBox();
        cmbProses = new widget.ComboBox();
        jLabel125 = new widget.Label();
        Scroll24 = new widget.ScrollPane();
        TIbuTertekan = new widget.TextArea();
        jLabel69 = new widget.Label();
        Scroll25 = new widget.ScrollPane();
        Tdukungan = new widget.TextArea();
        jLabel78 = new widget.Label();
        jLabel126 = new widget.Label();
        jLabel127 = new widget.Label();
        TLamaProses = new widget.TextBox();
        TPrematur = new widget.TextBox();
        TTerlambat = new widget.TextBox();
        jLabel128 = new widget.Label();
        TProsesLahir = new widget.TextBox();
        internalFrame7 = new widget.InternalFrame();
        Scroll18 = new widget.ScrollPane();
        tbTahapPerkembangan = new widget.Table();
        panelGlass28 = new widget.panelisi();
        jLabel51 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        Tduduk = new widget.TextBox();
        Tmerangkak = new widget.TextBox();
        Tberjalan = new widget.TextBox();
        Tbicara1 = new widget.TextBox();
        TbicaraDengan = new widget.TextBox();
        jLabel86 = new widget.Label();
        jLabel87 = new widget.Label();
        TlatihanBAK = new widget.TextBox();
        TlatihanBAB = new widget.TextBox();
        jLabel129 = new widget.Label();
        Scroll26 = new widget.ScrollPane();
        Trelasi = new widget.TextArea();
        jLabel130 = new widget.Label();
        Scroll42 = new widget.ScrollPane();
        Tkebiasaan = new widget.TextArea();
        internalFrame8 = new widget.InternalFrame();
        panelGlass34 = new widget.panelisi();
        panelGlass29 = new widget.panelisi();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel90 = new widget.Label();
        cmbSekolah = new widget.ComboBox();
        jLabel91 = new widget.Label();
        Tkota = new widget.TextBox();
        jLabel92 = new widget.Label();
        TthnMsk = new widget.TextBox();
        jLabel131 = new widget.Label();
        TthnSelesai = new widget.TextBox();
        jLabel132 = new widget.Label();
        TlamaSekolah = new widget.TextBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel93 = new widget.Label();
        jLabel133 = new widget.Label();
        jLabel134 = new widget.Label();
        jLabel135 = new widget.Label();
        jLabel136 = new widget.Label();
        jLabel137 = new widget.Label();
        TjnsSekolah = new widget.TextBox();
        Tprogram = new widget.TextBox();
        cmbBelajar = new widget.ComboBox();
        cmbBersemangat = new widget.ComboBox();
        cmbPernah = new widget.ComboBox();
        TtglKelas = new widget.TextBox();
        jLabel138 = new widget.Label();
        Tprestasi = new widget.TextBox();
        jLabel139 = new widget.Label();
        Tmata = new widget.TextBox();
        jLabel140 = new widget.Label();
        Tsulit = new widget.TextBox();
        Tminat = new widget.TextBox();
        jLabel141 = new widget.Label();
        jPanel7 = new javax.swing.JPanel();
        panelGlass35 = new widget.panelisi();
        panelGlass36 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnEdit3 = new widget.Button();
        Scroll27 = new widget.ScrollPane();
        tbRiwayatSekolah = new widget.Table();
        Scroll40 = new widget.ScrollPane();
        tbDisekolah = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        Scroll28 = new widget.ScrollPane();
        tbObservasi = new widget.Table();
        panelGlass30 = new widget.panelisi();
        jLabel88 = new widget.Label();
        Tpenampilan = new widget.TextBox();
        jLabel89 = new widget.Label();
        Tekspresi = new widget.TextBox();
        jLabel94 = new widget.Label();
        Tperasaan = new widget.TextBox();
        jLabel142 = new widget.Label();
        Ttingkah = new widget.TextBox();
        jLabel143 = new widget.Label();
        Tumum = new widget.TextBox();
        jLabel144 = new widget.Label();
        Tintelektual = new widget.TextBox();
        Tlain = new widget.TextBox();
        jLabel145 = new widget.Label();
        internalFrame10 = new widget.InternalFrame();
        Scroll30 = new widget.ScrollPane();
        tbTesPsikolog = new widget.Table();
        panelGlass31 = new widget.panelisi();
        jLabel146 = new widget.Label();
        jLabel147 = new widget.Label();
        jLabel148 = new widget.Label();
        jLabel149 = new widget.Label();
        TTesPsiko = new widget.TextBox();
        TWaktu = new widget.TextBox();
        TTester = new widget.TextBox();
        tgl_rencana = new widget.Tanggal();
        internalFrame11 = new widget.InternalFrame();
        Scroll32 = new widget.ScrollPane();
        tbDinamika = new widget.Table();
        panelGlass32 = new widget.panelisi();
        jLabel98 = new widget.Label();
        Scroll33 = new widget.ScrollPane();
        Tdinamika = new widget.TextArea();
        internalFrame12 = new widget.InternalFrame();
        Scroll34 = new widget.ScrollPane();
        tbDiagnosis = new widget.Table();
        panelGlass33 = new widget.panelisi();
        jLabel95 = new widget.Label();
        Scroll31 = new widget.ScrollPane();
        TKesan = new widget.TextArea();
        jLabel96 = new widget.Label();
        TDiagUtama = new widget.TextBox();
        TDiagBanding = new widget.TextBox();
        jLabel97 = new widget.Label();
        btnPenyakit = new widget.Button();
        btnPenyakit1 = new widget.Button();
        internalFrame13 = new widget.InternalFrame();
        Scroll43 = new widget.ScrollPane();
        tbManifestasi = new widget.Table();
        panelGlass37 = new widget.panelisi();
        jLabel99 = new widget.Label();
        Scroll44 = new widget.ScrollPane();
        TManifes = new widget.TextArea();
        internalFrame16 = new widget.InternalFrame();
        Scroll45 = new widget.ScrollPane();
        tbPrognosis = new widget.Table();
        panelGlass38 = new widget.panelisi();
        jLabel100 = new widget.Label();
        Scroll46 = new widget.ScrollPane();
        Tprognosis = new widget.TextArea();
        internalFrame17 = new widget.InternalFrame();
        Scroll47 = new widget.ScrollPane();
        tbTritmen = new widget.Table();
        panelGlass39 = new widget.panelisi();
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
        internalFrame15 = new widget.InternalFrame();
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

        jLabel150.setForeground(new java.awt.Color(0, 0, 0));
        jLabel150.setText("Tritmen Lainnya : ");
        jLabel150.setName("jLabel150"); // NOI18N
        panelGlass8.add(jLabel150);
        jLabel150.setBounds(0, 37, 140, 23);

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

        jLabel151.setForeground(new java.awt.Color(0, 0, 0));
        jLabel151.setText("Nama Rencana Tritmen : ");
        jLabel151.setName("jLabel151"); // NOI18N
        panelGlass8.add(jLabel151);
        jLabel151.setBounds(0, 10, 140, 23);

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

        internalFrameUtama.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Rekam Psikologis Anak / Remaja ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrameUtama.setName("internalFrameUtama"); // NOI18N
        internalFrameUtama.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame0.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame0.setBorder(null);
        internalFrame0.setName("internalFrame0"); // NOI18N
        internalFrame0.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 180));
        panelGlass16.setLayout(null);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Agama : ");
        jLabel72.setName("jLabel72"); // NOI18N
        panelGlass16.add(jLabel72);
        jLabel72.setBounds(0, 38, 130, 23);

        TAgama.setEditable(false);
        TAgama.setForeground(new java.awt.Color(0, 0, 0));
        TAgama.setName("TAgama"); // NOI18N
        panelGlass16.add(TAgama);
        TAgama.setBounds(133, 38, 210, 23);

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
        jLabel74.setText("Urutan Kelahiran : ");
        jLabel74.setName("jLabel74"); // NOI18N
        panelGlass16.add(jLabel74);
        jLabel74.setBounds(0, 94, 130, 23);

        TUrutanLahir.setForeground(new java.awt.Color(0, 0, 0));
        TUrutanLahir.setName("TUrutanLahir"); // NOI18N
        panelGlass16.add(TUrutanLahir);
        TUrutanLahir.setBounds(133, 94, 210, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Alamat : ");
        jLabel76.setName("jLabel76"); // NOI18N
        panelGlass16.add(jLabel76);
        jLabel76.setBounds(0, 122, 130, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Tempat & Tgl. Lahir : ");
        jLabel71.setName("jLabel71"); // NOI18N
        panelGlass16.add(jLabel71);
        jLabel71.setBounds(0, 10, 130, 23);

        Ttl.setEditable(false);
        Ttl.setForeground(new java.awt.Color(0, 0, 0));
        Ttl.setName("Ttl"); // NOI18N
        panelGlass16.add(Ttl);
        Ttl.setBounds(133, 10, 260, 23);

        Scroll7.setName("Scroll7"); // NOI18N

        TAlamat.setEditable(false);
        TAlamat.setColumns(20);
        TAlamat.setRows(5);
        TAlamat.setName("TAlamat"); // NOI18N
        Scroll7.setViewportView(TAlamat);

        panelGlass16.add(Scroll7);
        Scroll7.setBounds(133, 122, 592, 50);

        internalFrame0.add(panelGlass16, java.awt.BorderLayout.PAGE_START);

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

        internalFrame0.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabPsikologis.addTab("Data Diri Pasien / Klien", internalFrame0);

        internalFrame1.setBorder(null);
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 401));
        Scroll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll2KeyPressed(evt);
            }
        });

        tbIdentitasSaudara.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbIdentitasSaudara.setName("tbIdentitasSaudara"); // NOI18N
        tbIdentitasSaudara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIdentitasSaudaraMouseClicked(evt);
            }
        });
        tbIdentitasSaudara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIdentitasSaudaraKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbIdentitasSaudara);

        internalFrame1.add(Scroll2, java.awt.BorderLayout.CENTER);

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

        TposisiSau.setForeground(new java.awt.Color(0, 0, 0));
        TposisiSau.setName("TposisiSau"); // NOI18N
        TposisiSau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TposisiSauKeyPressed(evt);
            }
        });
        panelGlass11.add(TposisiSau);
        TposisiSau.setBounds(85, 66, 292, 23);

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

        TketSau.setForeground(new java.awt.Color(0, 0, 0));
        TketSau.setName("TketSau"); // NOI18N
        TketSau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketSauKeyPressed(evt);
            }
        });
        panelGlass11.add(TketSau);
        TketSau.setBounds(85, 94, 430, 23);

        TumurSau.setForeground(new java.awt.Color(0, 0, 0));
        TumurSau.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurSau.setName("TumurSau"); // NOI18N
        TumurSau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurSauKeyPressed(evt);
            }
        });
        panelGlass11.add(TumurSau);
        TumurSau.setBounds(85, 38, 50, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nama : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass11.add(jLabel15);
        jLabel15.setBounds(0, 10, 84, 23);

        TnmSau.setForeground(new java.awt.Color(0, 0, 0));
        TnmSau.setName("TnmSau"); // NOI18N
        TnmSau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmSauKeyPressed(evt);
            }
        });
        panelGlass11.add(TnmSau);
        TnmSau.setBounds(85, 10, 430, 23);

        cmbUmur.setForeground(new java.awt.Color(0, 0, 0));
        cmbUmur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Th", "Bl", "Hr" }));
        cmbUmur.setName("cmbUmur"); // NOI18N
        cmbUmur.setOpaque(false);
        cmbUmur.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbUmur);
        cmbUmur.setBounds(140, 38, 50, 23);

        internalFrame1.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Identitas Saudara", internalFrame1);

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(452, 311));

        tbIdentitasOrtu.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbIdentitasOrtu.setName("tbIdentitasOrtu"); // NOI18N
        tbIdentitasOrtu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIdentitasOrtuMouseClicked(evt);
            }
        });
        tbIdentitasOrtu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIdentitasOrtuKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbIdentitasOrtu);

        internalFrame2.add(Scroll4, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 320));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        Scroll41.setName("Scroll41"); // NOI18N
        Scroll41.setOpaque(true);

        FormInput4.setBackground(new java.awt.Color(255, 255, 255));
        FormInput4.setBorder(null);
        FormInput4.setName("FormInput4"); // NOI18N
        FormInput4.setPreferredSize(new java.awt.Dimension(900, 387));
        FormInput4.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Identitas Ayah ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel3.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Umur : ");
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel3.add(jLabel5);
        jLabel5.setBounds(0, 48, 110, 23);

        TumurAyah.setForeground(new java.awt.Color(0, 0, 0));
        TumurAyah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurAyah.setName("TumurAyah"); // NOI18N
        TumurAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurAyahKeyPressed(evt);
            }
        });
        jPanel3.add(TumurAyah);
        TumurAyah.setBounds(113, 48, 50, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Pendidikan : ");
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel3.add(jLabel7);
        jLabel7.setBounds(0, 76, 110, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama : ");
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel3.add(jLabel9);
        jLabel9.setBounds(0, 20, 110, 23);

        TnmAyah.setForeground(new java.awt.Color(0, 0, 0));
        TnmAyah.setName("TnmAyah"); // NOI18N
        TnmAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAyahKeyPressed(evt);
            }
        });
        jPanel3.add(TnmAyah);
        TnmAyah.setBounds(113, 20, 250, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Anak Ke : ");
        jLabel18.setName("jLabel18"); // NOI18N
        jPanel3.add(jLabel18);
        jLabel18.setBounds(0, 160, 110, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pekerjaan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        jPanel3.add(jLabel10);
        jLabel10.setBounds(0, 104, 110, 23);

        TpekerjaanAyah.setForeground(new java.awt.Color(0, 0, 0));
        TpekerjaanAyah.setName("TpekerjaanAyah"); // NOI18N
        TpekerjaanAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanAyahKeyPressed(evt);
            }
        });
        jPanel3.add(TpekerjaanAyah);
        TpekerjaanAyah.setBounds(113, 104, 350, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Agama : ");
        jLabel16.setName("jLabel16"); // NOI18N
        jPanel3.add(jLabel16);
        jLabel16.setBounds(0, 132, 110, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Tahun.");
        jLabel20.setName("jLabel20"); // NOI18N
        jPanel3.add(jLabel20);
        jLabel20.setBounds(170, 48, 50, 23);

        cmbPndAyah.setForeground(new java.awt.Color(0, 0, 0));
        cmbPndAyah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TIDAK SEKOLAH", "TK", "SD/MI", "SMP/SLTP/MTS", "SMA/SLTA/SMK/MAN", "PESANTREN", "D1", "D3", "D4", "S1", "S2", "S3" }));
        cmbPndAyah.setName("cmbPndAyah"); // NOI18N
        cmbPndAyah.setOpaque(false);
        cmbPndAyah.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbPndAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbPndAyahKeyPressed(evt);
            }
        });
        jPanel3.add(cmbPndAyah);
        cmbPndAyah.setBounds(113, 76, 140, 23);

        cmbAgamaAyah.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgamaAyah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Hindu", "Budha", "Kristen Protestan", "Kristen Katolik", "Konghucu", "Kepercayaan Lain" }));
        cmbAgamaAyah.setName("cmbAgamaAyah"); // NOI18N
        cmbAgamaAyah.setOpaque(false);
        cmbAgamaAyah.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbAgamaAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbAgamaAyahKeyPressed(evt);
            }
        });
        jPanel3.add(cmbAgamaAyah);
        cmbAgamaAyah.setBounds(113, 132, 120, 23);

        TanakKeAyah.setForeground(new java.awt.Color(0, 0, 0));
        TanakKeAyah.setName("TanakKeAyah"); // NOI18N
        TanakKeAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanakKeAyahKeyPressed(evt);
            }
        });
        jPanel3.add(TanakKeAyah);
        TanakKeAyah.setBounds(113, 160, 190, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Perkawinan Ke : ");
        jLabel24.setName("jLabel24"); // NOI18N
        jPanel3.add(jLabel24);
        jLabel24.setBounds(0, 188, 110, 23);

        TkawinKeAyah.setForeground(new java.awt.Color(0, 0, 0));
        TkawinKeAyah.setName("TkawinKeAyah"); // NOI18N
        TkawinKeAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkawinKeAyahKeyPressed(evt);
            }
        });
        jPanel3.add(TkawinKeAyah);
        TkawinKeAyah.setBounds(113, 188, 190, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("No. HP/Telpn. : ");
        jLabel53.setName("jLabel53"); // NOI18N
        jPanel3.add(jLabel53);
        jLabel53.setBounds(0, 320, 110, 23);

        TnoHpAyah.setForeground(new java.awt.Color(0, 0, 0));
        TnoHpAyah.setName("TnoHpAyah"); // NOI18N
        TnoHpAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoHpAyahKeyPressed(evt);
            }
        });
        jPanel3.add(TnoHpAyah);
        TnoHpAyah.setBounds(113, 320, 250, 23);

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        TalamatAyah.setColumns(20);
        TalamatAyah.setRows(5);
        TalamatAyah.setName("TalamatAyah"); // NOI18N
        TalamatAyah.setPreferredSize(new java.awt.Dimension(170, 110));
        TalamatAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatAyahKeyPressed(evt);
            }
        });
        Scroll11.setViewportView(TalamatAyah);

        jPanel3.add(Scroll11);
        Scroll11.setBounds(113, 216, 390, 70);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Alamat : ");
        jLabel27.setName("jLabel27"); // NOI18N
        jPanel3.add(jLabel27);
        jLabel27.setBounds(0, 216, 110, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Keterangan : ");
        jLabel56.setName("jLabel56"); // NOI18N
        jPanel3.add(jLabel56);
        jLabel56.setBounds(0, 348, 110, 23);

        TKetAyah.setForeground(new java.awt.Color(0, 0, 0));
        TKetAyah.setName("TKetAyah"); // NOI18N
        TKetAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetAyahKeyPressed(evt);
            }
        });
        jPanel3.add(TKetAyah);
        TKetAyah.setBounds(113, 348, 400, 23);

        ChkcopasAyah.setBackground(new java.awt.Color(255, 255, 250));
        ChkcopasAyah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkcopasAyah.setForeground(new java.awt.Color(0, 0, 0));
        ChkcopasAyah.setText("Copy Alamat Ayah Ke Alamat Ibu -->>>");
        ChkcopasAyah.setBorderPainted(true);
        ChkcopasAyah.setBorderPaintedFlat(true);
        ChkcopasAyah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkcopasAyah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkcopasAyah.setName("ChkcopasAyah"); // NOI18N
        ChkcopasAyah.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkcopasAyah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkcopasAyahActionPerformed(evt);
            }
        });
        jPanel3.add(ChkcopasAyah);
        ChkcopasAyah.setBounds(113, 287, 310, 23);

        jPanel1.add(jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Identitas Ibu ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(null);

        TnmIbu.setForeground(new java.awt.Color(0, 0, 0));
        TnmIbu.setName("TnmIbu"); // NOI18N
        TnmIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmIbuKeyPressed(evt);
            }
        });
        jPanel2.add(TnmIbu);
        TnmIbu.setBounds(113, 20, 250, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Nama : ");
        jLabel17.setName("jLabel17"); // NOI18N
        jPanel2.add(jLabel17);
        jLabel17.setBounds(0, 20, 110, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Umur : ");
        jLabel30.setName("jLabel30"); // NOI18N
        jPanel2.add(jLabel30);
        jLabel30.setBounds(0, 48, 110, 23);

        TumurIbu.setForeground(new java.awt.Color(0, 0, 0));
        TumurIbu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurIbu.setName("TumurIbu"); // NOI18N
        TumurIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurIbuKeyPressed(evt);
            }
        });
        jPanel2.add(TumurIbu);
        TumurIbu.setBounds(113, 48, 50, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Tahun.");
        jLabel35.setName("jLabel35"); // NOI18N
        jPanel2.add(jLabel35);
        jLabel35.setBounds(170, 48, 50, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Pendidikan : ");
        jLabel36.setName("jLabel36"); // NOI18N
        jPanel2.add(jLabel36);
        jLabel36.setBounds(0, 76, 110, 23);

        cmbPndIbu.setForeground(new java.awt.Color(0, 0, 0));
        cmbPndIbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TIDAK SEKOLAH", "TK", "SD/MI", "SMP/SLTP/MTS", "SMA/SLTA/SMK/MAN", "PESANTREN", "D1", "D3", "D4", "S1", "S2", "S3" }));
        cmbPndIbu.setName("cmbPndIbu"); // NOI18N
        cmbPndIbu.setOpaque(false);
        cmbPndIbu.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbPndIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbPndIbuKeyPressed(evt);
            }
        });
        jPanel2.add(cmbPndIbu);
        cmbPndIbu.setBounds(113, 76, 140, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Pekerjaan : ");
        jLabel37.setName("jLabel37"); // NOI18N
        jPanel2.add(jLabel37);
        jLabel37.setBounds(0, 104, 110, 23);

        TpekerjaanIbu.setForeground(new java.awt.Color(0, 0, 0));
        TpekerjaanIbu.setName("TpekerjaanIbu"); // NOI18N
        TpekerjaanIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanIbuKeyPressed(evt);
            }
        });
        jPanel2.add(TpekerjaanIbu);
        TpekerjaanIbu.setBounds(113, 104, 350, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Agama : ");
        jLabel38.setName("jLabel38"); // NOI18N
        jPanel2.add(jLabel38);
        jLabel38.setBounds(0, 132, 110, 23);

        cmbAgamaIbu.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgamaIbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Hindu", "Budha", "Kristen Protestan", "Kristen Katolik", "Konghucu", "Kepercayaan Lain" }));
        cmbAgamaIbu.setName("cmbAgamaIbu"); // NOI18N
        cmbAgamaIbu.setOpaque(false);
        cmbAgamaIbu.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbAgamaIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbAgamaIbuKeyPressed(evt);
            }
        });
        jPanel2.add(cmbAgamaIbu);
        cmbAgamaIbu.setBounds(113, 132, 120, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Anak Ke : ");
        jLabel40.setName("jLabel40"); // NOI18N
        jPanel2.add(jLabel40);
        jLabel40.setBounds(0, 160, 110, 23);

        TanakKeIbu.setForeground(new java.awt.Color(0, 0, 0));
        TanakKeIbu.setName("TanakKeIbu"); // NOI18N
        TanakKeIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanakKeIbuKeyPressed(evt);
            }
        });
        jPanel2.add(TanakKeIbu);
        TanakKeIbu.setBounds(113, 160, 190, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Perkawinan Ke : ");
        jLabel103.setName("jLabel103"); // NOI18N
        jPanel2.add(jLabel103);
        jLabel103.setBounds(0, 188, 110, 23);

        TkawinKeIbu.setForeground(new java.awt.Color(0, 0, 0));
        TkawinKeIbu.setName("TkawinKeIbu"); // NOI18N
        TkawinKeIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkawinKeIbuKeyPressed(evt);
            }
        });
        jPanel2.add(TkawinKeIbu);
        TkawinKeIbu.setBounds(113, 188, 190, 23);

        Scroll36.setName("Scroll36"); // NOI18N
        Scroll36.setOpaque(true);

        TalamatIbu.setColumns(20);
        TalamatIbu.setRows(5);
        TalamatIbu.setName("TalamatIbu"); // NOI18N
        TalamatIbu.setPreferredSize(new java.awt.Dimension(170, 110));
        TalamatIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatIbuKeyPressed(evt);
            }
        });
        Scroll36.setViewportView(TalamatIbu);

        jPanel2.add(Scroll36);
        Scroll36.setBounds(113, 216, 390, 70);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Alamat : ");
        jLabel52.setName("jLabel52"); // NOI18N
        jPanel2.add(jLabel52);
        jLabel52.setBounds(0, 216, 110, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("No. HP/Telpn. : ");
        jLabel54.setName("jLabel54"); // NOI18N
        jPanel2.add(jLabel54);
        jLabel54.setBounds(0, 320, 110, 23);

        TnoHpIbu.setForeground(new java.awt.Color(0, 0, 0));
        TnoHpIbu.setName("TnoHpIbu"); // NOI18N
        TnoHpIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoHpIbuKeyPressed(evt);
            }
        });
        jPanel2.add(TnoHpIbu);
        TnoHpIbu.setBounds(113, 320, 250, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Keterangan : ");
        jLabel57.setName("jLabel57"); // NOI18N
        jPanel2.add(jLabel57);
        jLabel57.setBounds(0, 348, 110, 23);

        TKetIbu.setForeground(new java.awt.Color(0, 0, 0));
        TKetIbu.setName("TKetIbu"); // NOI18N
        TKetIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetIbuKeyPressed(evt);
            }
        });
        jPanel2.add(TKetIbu);
        TKetIbu.setBounds(113, 348, 400, 23);

        ChkcopasIbu.setBackground(new java.awt.Color(255, 255, 250));
        ChkcopasIbu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkcopasIbu.setForeground(new java.awt.Color(0, 0, 0));
        ChkcopasIbu.setText("<<<-- Copy Alamat Ayah Ke Alamat Ibu");
        ChkcopasIbu.setBorderPainted(true);
        ChkcopasIbu.setBorderPaintedFlat(true);
        ChkcopasIbu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkcopasIbu.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkcopasIbu.setName("ChkcopasIbu"); // NOI18N
        ChkcopasIbu.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkcopasIbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkcopasIbuActionPerformed(evt);
            }
        });
        jPanel2.add(ChkcopasIbu);
        ChkcopasIbu.setBounds(113, 287, 220, 23);

        jPanel1.add(jPanel2);

        FormInput4.add(jPanel1, java.awt.BorderLayout.CENTER);

        Scroll41.setViewportView(FormInput4);

        panelGlass12.add(Scroll41, java.awt.BorderLayout.CENTER);

        internalFrame2.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Identitas Orang Tua", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame3.add(Scroll10, java.awt.BorderLayout.CENTER);

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

        internalFrame3.add(panelGlass14, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Keluhan Permasalahan", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame4.add(Scroll15, java.awt.BorderLayout.CENTER);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 275));
        panelGlass15.setLayout(new java.awt.BorderLayout());

        Scroll37.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Wawancara ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll37.setName("Scroll37"); // NOI18N
        Scroll37.setOpaque(true);

        FormInput3.setBackground(new java.awt.Color(255, 255, 255));
        FormInput3.setBorder(null);
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(900, 560));
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
        jLabel31.setText("Harapan Terhadap Anak : ");
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
        Scroll20.setBounds(162, 404, 830, 70);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Perubahan Yang Diinginkan ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput3.add(jLabel32);
        jLabel32.setBounds(0, 404, 160, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Dari Diri Sendiri : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput3.add(jLabel33);
        jLabel33.setBounds(0, 418, 160, 23);

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
        Scroll21.setBounds(162, 480, 830, 70);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Perubahan Yang Diinginkan ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput3.add(jLabel34);
        jLabel34.setBounds(0, 480, 160, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Dari Keluarga : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput3.add(jLabel39);
        jLabel39.setBounds(0, 494, 160, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Mencari Bantuan Kali Ini : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput3.add(jLabel60);
        jLabel60.setBounds(0, 94, 160, 23);

        Scroll38.setName("Scroll38"); // NOI18N
        Scroll38.setOpaque(true);

        TPerubahanAnak.setColumns(20);
        TPerubahanAnak.setRows(5);
        TPerubahanAnak.setName("TPerubahanAnak"); // NOI18N
        TPerubahanAnak.setPreferredSize(new java.awt.Dimension(170, 230));
        TPerubahanAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPerubahanAnakKeyPressed(evt);
            }
        });
        Scroll38.setViewportView(TPerubahanAnak);

        FormInput3.add(Scroll38);
        Scroll38.setBounds(162, 328, 830, 70);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Perubahan Yang Diinginkan ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput3.add(jLabel77);
        jLabel77.setBounds(0, 328, 160, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Dari Anak : ");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput3.add(jLabel102);
        jLabel102.setBounds(0, 342, 160, 23);

        Scroll37.setViewportView(FormInput3);

        panelGlass15.add(Scroll37, java.awt.BorderLayout.CENTER);

        internalFrame4.add(panelGlass15, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Pemeriksaan Psikologi Klinis", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll16.setName("Scroll16"); // NOI18N
        Scroll16.setOpaque(true);
        Scroll16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll16KeyPressed(evt);
            }
        });

        tbRiwayatKesehatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRiwayatKesehatan.setName("tbRiwayatKesehatan"); // NOI18N
        tbRiwayatKesehatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatKesehatanMouseClicked(evt);
            }
        });
        tbRiwayatKesehatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKesehatanKeyPressed(evt);
            }
        });
        Scroll16.setViewportView(tbRiwayatKesehatan);

        internalFrame5.add(Scroll16, java.awt.BorderLayout.CENTER);

        panelGlass26.setName("panelGlass26"); // NOI18N
        panelGlass26.setPreferredSize(new java.awt.Dimension(44, 278));
        panelGlass26.setLayout(new java.awt.BorderLayout());

        Scroll39.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Dialami Pada Saat Usia ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll39.setName("Scroll39"); // NOI18N
        Scroll39.setOpaque(true);

        FormInput5.setBackground(new java.awt.Color(255, 255, 255));
        FormInput5.setBorder(null);
        FormInput5.setName("FormInput5"); // NOI18N
        FormInput5.setPreferredSize(new java.awt.Dimension(900, 373));
        FormInput5.setLayout(null);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Pneumonia : ");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput5.add(jLabel61);
        jLabel61.setBounds(0, 32, 180, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Demam Tinggi : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput5.add(jLabel62);
        jLabel62.setBounds(0, 4, 180, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Flu : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput5.add(jLabel63);
        jLabel63.setBounds(0, 60, 180, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Ensefalitis : ");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput5.add(jLabel65);
        jLabel65.setBounds(0, 88, 180, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Kejang : ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput5.add(jLabel66);
        jLabel66.setBounds(0, 144, 180, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tidak Sadarkan Diri : ");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput5.add(jLabel67);
        jLabel67.setBounds(0, 172, 180, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Meningitis : ");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput5.add(jLabel75);
        jLabel75.setBounds(0, 116, 180, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Geger Otak : ");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput5.add(jLabel104);
        jLabel104.setBounds(0, 200, 180, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Pingsan : ");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput5.add(jLabel105);
        jLabel105.setBounds(0, 228, 180, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Pusing : ");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput5.add(jLabel106);
        jLabel106.setBounds(0, 256, 180, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Permasalahan Terkait Amandel : ");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput5.add(jLabel107);
        jLabel107.setBounds(0, 284, 180, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Permasalahan Penglihatan : ");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput5.add(jLabel108);
        jLabel108.setBounds(0, 312, 180, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Permasalahan Pendengaran : ");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput5.add(jLabel109);
        jLabel109.setBounds(0, 340, 180, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Permasalahan Berat Badan : ");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput5.add(jLabel110);
        jLabel110.setBounds(280, 32, 170, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Permasalahan Terkait Gigi : ");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput5.add(jLabel111);
        jLabel111.setBounds(280, 4, 170, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Alergi : ");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput5.add(jLabel112);
        jLabel112.setBounds(280, 60, 170, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Permasalahan Kulit : ");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput5.add(jLabel113);
        jLabel113.setBounds(280, 88, 170, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Sakit Kepala : ");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput5.add(jLabel114);
        jLabel114.setBounds(280, 144, 170, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Permasalahan Terkait Perut : ");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput5.add(jLabel115);
        jLabel115.setBounds(280, 172, 170, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Asma : ");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput5.add(jLabel116);
        jLabel116.setBounds(280, 116, 170, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Rawan Kecelakaan : ");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput5.add(jLabel117);
        jLabel117.setBounds(280, 200, 170, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Anemia : ");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput5.add(jLabel118);
        jLabel118.setBounds(280, 228, 170, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Tekanan Darah Tinggi/Rendah : ");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput5.add(jLabel119);
        jLabel119.setBounds(280, 256, 170, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Permasalahan Terkait Sinus : ");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput5.add(jLabel120);
        jLabel120.setBounds(280, 284, 170, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Permasalahan Jantung : ");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput5.add(jLabel121);
        jLabel121.setBounds(280, 312, 170, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Hiperaktif : ");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput5.add(jLabel122);
        jLabel122.setBounds(280, 340, 170, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel123.setText("Penyakit Lain (Jelaskan) : ");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput5.add(jLabel123);
        jLabel123.setBounds(560, 4, 150, 23);

        TDemam.setForeground(new java.awt.Color(0, 0, 0));
        TDemam.setName("TDemam"); // NOI18N
        TDemam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDemamKeyPressed(evt);
            }
        });
        FormInput5.add(TDemam);
        TDemam.setBounds(182, 4, 90, 23);

        TPneum.setForeground(new java.awt.Color(0, 0, 0));
        TPneum.setName("TPneum"); // NOI18N
        TPneum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPneumKeyPressed(evt);
            }
        });
        FormInput5.add(TPneum);
        TPneum.setBounds(182, 32, 90, 23);

        TFlu.setForeground(new java.awt.Color(0, 0, 0));
        TFlu.setName("TFlu"); // NOI18N
        TFlu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFluKeyPressed(evt);
            }
        });
        FormInput5.add(TFlu);
        TFlu.setBounds(182, 60, 90, 23);

        TEnse.setForeground(new java.awt.Color(0, 0, 0));
        TEnse.setName("TEnse"); // NOI18N
        TEnse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEnseKeyPressed(evt);
            }
        });
        FormInput5.add(TEnse);
        TEnse.setBounds(182, 88, 90, 23);

        TMeni.setForeground(new java.awt.Color(0, 0, 0));
        TMeni.setName("TMeni"); // NOI18N
        TMeni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMeniKeyPressed(evt);
            }
        });
        FormInput5.add(TMeni);
        TMeni.setBounds(182, 116, 90, 23);

        TKejang.setForeground(new java.awt.Color(0, 0, 0));
        TKejang.setName("TKejang"); // NOI18N
        TKejang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKejangKeyPressed(evt);
            }
        });
        FormInput5.add(TKejang);
        TKejang.setBounds(182, 144, 90, 23);

        TTidak.setForeground(new java.awt.Color(0, 0, 0));
        TTidak.setName("TTidak"); // NOI18N
        TTidak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTidakKeyPressed(evt);
            }
        });
        FormInput5.add(TTidak);
        TTidak.setBounds(182, 172, 90, 23);

        TGeger.setForeground(new java.awt.Color(0, 0, 0));
        TGeger.setName("TGeger"); // NOI18N
        TGeger.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGegerKeyPressed(evt);
            }
        });
        FormInput5.add(TGeger);
        TGeger.setBounds(182, 200, 90, 23);

        TPingsan.setForeground(new java.awt.Color(0, 0, 0));
        TPingsan.setName("TPingsan"); // NOI18N
        TPingsan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPingsanKeyPressed(evt);
            }
        });
        FormInput5.add(TPingsan);
        TPingsan.setBounds(182, 228, 90, 23);

        TPusing.setForeground(new java.awt.Color(0, 0, 0));
        TPusing.setName("TPusing"); // NOI18N
        TPusing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPusingKeyPressed(evt);
            }
        });
        FormInput5.add(TPusing);
        TPusing.setBounds(182, 256, 90, 23);

        TAmandel.setForeground(new java.awt.Color(0, 0, 0));
        TAmandel.setName("TAmandel"); // NOI18N
        TAmandel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAmandelKeyPressed(evt);
            }
        });
        FormInput5.add(TAmandel);
        TAmandel.setBounds(182, 284, 90, 23);

        TLihat.setForeground(new java.awt.Color(0, 0, 0));
        TLihat.setName("TLihat"); // NOI18N
        TLihat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLihatKeyPressed(evt);
            }
        });
        FormInput5.add(TLihat);
        TLihat.setBounds(182, 312, 90, 23);

        TDengar.setForeground(new java.awt.Color(0, 0, 0));
        TDengar.setName("TDengar"); // NOI18N
        TDengar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDengarKeyPressed(evt);
            }
        });
        FormInput5.add(TDengar);
        TDengar.setBounds(182, 340, 90, 23);

        TGigi.setForeground(new java.awt.Color(0, 0, 0));
        TGigi.setName("TGigi"); // NOI18N
        TGigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGigiKeyPressed(evt);
            }
        });
        FormInput5.add(TGigi);
        TGigi.setBounds(452, 4, 90, 23);

        TBerat.setForeground(new java.awt.Color(0, 0, 0));
        TBerat.setName("TBerat"); // NOI18N
        TBerat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBeratKeyPressed(evt);
            }
        });
        FormInput5.add(TBerat);
        TBerat.setBounds(452, 32, 90, 23);

        TAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        FormInput5.add(TAlergi);
        TAlergi.setBounds(452, 60, 90, 23);

        TKulit.setForeground(new java.awt.Color(0, 0, 0));
        TKulit.setName("TKulit"); // NOI18N
        TKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKulitKeyPressed(evt);
            }
        });
        FormInput5.add(TKulit);
        TKulit.setBounds(452, 88, 90, 23);

        TAsma.setForeground(new java.awt.Color(0, 0, 0));
        TAsma.setName("TAsma"); // NOI18N
        TAsma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAsmaKeyPressed(evt);
            }
        });
        FormInput5.add(TAsma);
        TAsma.setBounds(452, 116, 90, 23);

        TKepala.setForeground(new java.awt.Color(0, 0, 0));
        TKepala.setName("TKepala"); // NOI18N
        TKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKepalaKeyPressed(evt);
            }
        });
        FormInput5.add(TKepala);
        TKepala.setBounds(452, 144, 90, 23);

        TPerut.setForeground(new java.awt.Color(0, 0, 0));
        TPerut.setName("TPerut"); // NOI18N
        TPerut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPerutKeyPressed(evt);
            }
        });
        FormInput5.add(TPerut);
        TPerut.setBounds(452, 172, 90, 23);

        TRawan.setForeground(new java.awt.Color(0, 0, 0));
        TRawan.setName("TRawan"); // NOI18N
        TRawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRawanKeyPressed(evt);
            }
        });
        FormInput5.add(TRawan);
        TRawan.setBounds(452, 200, 90, 23);

        TAnemia.setForeground(new java.awt.Color(0, 0, 0));
        TAnemia.setName("TAnemia"); // NOI18N
        TAnemia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAnemiaKeyPressed(evt);
            }
        });
        FormInput5.add(TAnemia);
        TAnemia.setBounds(452, 228, 90, 23);

        TDarah.setForeground(new java.awt.Color(0, 0, 0));
        TDarah.setName("TDarah"); // NOI18N
        TDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDarahKeyPressed(evt);
            }
        });
        FormInput5.add(TDarah);
        TDarah.setBounds(452, 256, 90, 23);

        TSinus.setForeground(new java.awt.Color(0, 0, 0));
        TSinus.setName("TSinus"); // NOI18N
        TSinus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSinusKeyPressed(evt);
            }
        });
        FormInput5.add(TSinus);
        TSinus.setBounds(452, 284, 90, 23);

        TJantung.setForeground(new java.awt.Color(0, 0, 0));
        TJantung.setName("TJantung"); // NOI18N
        TJantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJantungKeyPressed(evt);
            }
        });
        FormInput5.add(TJantung);
        TJantung.setBounds(452, 312, 90, 23);

        THiper.setForeground(new java.awt.Color(0, 0, 0));
        THiper.setName("THiper"); // NOI18N
        THiper.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THiperKeyPressed(evt);
            }
        });
        FormInput5.add(THiper);
        THiper.setBounds(452, 340, 90, 23);

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        TPenyakit.setColumns(20);
        TPenyakit.setRows(5);
        TPenyakit.setName("TPenyakit"); // NOI18N
        TPenyakit.setPreferredSize(new java.awt.Dimension(170, 230));
        TPenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPenyakitKeyPressed(evt);
            }
        });
        Scroll22.setViewportView(TPenyakit);

        FormInput5.add(Scroll22);
        Scroll22.setBounds(560, 26, 450, 70);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("Apakah Anak Pernah Dirawat Di Rumah Sakit ? (Jika YA, Jelaskan)");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput5.add(jLabel124);
        jLabel124.setBounds(560, 100, 450, 23);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        TRumahSakit.setColumns(20);
        TRumahSakit.setRows(5);
        TRumahSakit.setName("TRumahSakit"); // NOI18N
        TRumahSakit.setPreferredSize(new java.awt.Dimension(170, 230));
        TRumahSakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRumahSakitKeyPressed(evt);
            }
        });
        Scroll23.setViewportView(TRumahSakit);

        FormInput5.add(Scroll23);
        Scroll23.setBounds(560, 123, 450, 70);

        Scroll39.setViewportView(FormInput5);

        panelGlass26.add(Scroll39, java.awt.BorderLayout.CENTER);

        internalFrame5.add(panelGlass26, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Riwayat Kesehatan", internalFrame5);

        internalFrame6.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);
        Scroll17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll17KeyPressed(evt);
            }
        });

        tbRiwayatPerkembangan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRiwayatPerkembangan.setName("tbRiwayatPerkembangan"); // NOI18N
        tbRiwayatPerkembangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatPerkembanganMouseClicked(evt);
            }
        });
        tbRiwayatPerkembangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatPerkembanganKeyPressed(evt);
            }
        });
        Scroll17.setViewportView(tbRiwayatPerkembangan);

        internalFrame6.add(Scroll17, java.awt.BorderLayout.CENTER);

        panelGlass27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Masa Kehamilan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        panelGlass27.setName("panelGlass27"); // NOI18N
        panelGlass27.setPreferredSize(new java.awt.Dimension(44, 287));
        panelGlass27.setLayout(null);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Diinginkan : ");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass27.add(jLabel47);
        jLabel47.setBounds(0, 18, 150, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Direncanakan : ");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass27.add(jLabel48);
        jLabel48.setBounds(0, 46, 150, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Proses Kehamilan Normal : ");
        jLabel49.setName("jLabel49"); // NOI18N
        panelGlass27.add(jLabel49);
        jLabel49.setBounds(0, 74, 150, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Jika Ibu Sakit / Tertekan  ");
        jLabel50.setName("jLabel50"); // NOI18N
        panelGlass27.add(jLabel50);
        jLabel50.setBounds(0, 130, 150, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Selama Masa Kehamilan,  ");
        jLabel64.setName("jLabel64"); // NOI18N
        panelGlass27.add(jLabel64);
        jLabel64.setBounds(0, 144, 150, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Dukungan & Penerimaan  ");
        jLabel68.setName("jLabel68"); // NOI18N
        panelGlass27.add(jLabel68);
        jLabel68.setBounds(0, 205, 150, 23);

        cmbDinginkan.setForeground(new java.awt.Color(0, 0, 0));
        cmbDinginkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDinginkan.setName("cmbDinginkan"); // NOI18N
        cmbDinginkan.setOpaque(false);
        cmbDinginkan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDinginkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDinginkanKeyPressed(evt);
            }
        });
        panelGlass27.add(cmbDinginkan);
        cmbDinginkan.setBounds(152, 18, 60, 23);

        cmbDirencanakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbDirencanakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDirencanakan.setName("cmbDirencanakan"); // NOI18N
        cmbDirencanakan.setOpaque(false);
        cmbDirencanakan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDirencanakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDirencanakanKeyPressed(evt);
            }
        });
        panelGlass27.add(cmbDirencanakan);
        cmbDirencanakan.setBounds(152, 46, 60, 23);

        cmbProses.setForeground(new java.awt.Color(0, 0, 0));
        cmbProses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbProses.setName("cmbProses"); // NOI18N
        cmbProses.setOpaque(false);
        cmbProses.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbProses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbProsesKeyPressed(evt);
            }
        });
        panelGlass27.add(cmbProses);
        cmbProses.setBounds(152, 74, 60, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Jelaskan : ");
        jLabel125.setName("jLabel125"); // NOI18N
        panelGlass27.add(jLabel125);
        jLabel125.setBounds(0, 158, 150, 23);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        TIbuTertekan.setColumns(20);
        TIbuTertekan.setRows(5);
        TIbuTertekan.setName("TIbuTertekan"); // NOI18N
        TIbuTertekan.setPreferredSize(new java.awt.Dimension(170, 230));
        TIbuTertekan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TIbuTertekanKeyPressed(evt);
            }
        });
        Scroll24.setViewportView(TIbuTertekan);

        panelGlass27.add(Scroll24);
        Scroll24.setBounds(152, 130, 824, 70);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Suami (Jelaskan) : ");
        jLabel69.setName("jLabel69"); // NOI18N
        panelGlass27.add(jLabel69);
        jLabel69.setBounds(0, 219, 150, 23);

        Scroll25.setName("Scroll25"); // NOI18N
        Scroll25.setOpaque(true);

        Tdukungan.setColumns(20);
        Tdukungan.setRows(5);
        Tdukungan.setName("Tdukungan"); // NOI18N
        Tdukungan.setPreferredSize(new java.awt.Dimension(170, 230));
        Tdukungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdukunganKeyPressed(evt);
            }
        });
        Scroll25.setViewportView(Tdukungan);

        panelGlass27.add(Scroll25);
        Scroll25.setBounds(152, 205, 824, 70);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Lama Proses Melahirkan : ");
        jLabel78.setName("jLabel78"); // NOI18N
        panelGlass27.add(jLabel78);
        jLabel78.setBounds(214, 18, 170, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("Jika Prematur, Seberapa Cepat : ");
        jLabel126.setName("jLabel126"); // NOI18N
        panelGlass27.add(jLabel126);
        jLabel126.setBounds(214, 46, 170, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Jika Terlambat, Seberapa Lama : ");
        jLabel127.setName("jLabel127"); // NOI18N
        panelGlass27.add(jLabel127);
        jLabel127.setBounds(214, 74, 170, 23);

        TLamaProses.setForeground(new java.awt.Color(0, 0, 0));
        TLamaProses.setName("TLamaProses"); // NOI18N
        TLamaProses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLamaProsesKeyPressed(evt);
            }
        });
        panelGlass27.add(TLamaProses);
        TLamaProses.setBounds(386, 18, 590, 23);

        TPrematur.setForeground(new java.awt.Color(0, 0, 0));
        TPrematur.setName("TPrematur"); // NOI18N
        TPrematur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPrematurKeyPressed(evt);
            }
        });
        panelGlass27.add(TPrematur);
        TPrematur.setBounds(386, 46, 590, 23);

        TTerlambat.setForeground(new java.awt.Color(0, 0, 0));
        TTerlambat.setName("TTerlambat"); // NOI18N
        TTerlambat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerlambatKeyPressed(evt);
            }
        });
        panelGlass27.add(TTerlambat);
        TTerlambat.setBounds(386, 74, 590, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Proses Melahirkan (Spontan/Caesar) : ");
        jLabel128.setName("jLabel128"); // NOI18N
        panelGlass27.add(jLabel128);
        jLabel128.setBounds(174, 102, 210, 23);

        TProsesLahir.setForeground(new java.awt.Color(0, 0, 0));
        TProsesLahir.setName("TProsesLahir"); // NOI18N
        TProsesLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TProsesLahirKeyPressed(evt);
            }
        });
        panelGlass27.add(TProsesLahir);
        TProsesLahir.setBounds(386, 102, 590, 23);

        internalFrame6.add(panelGlass27, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Riwayat Perkembangan", internalFrame6);

        internalFrame7.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame7.setBorder(null);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll18.setName("Scroll18"); // NOI18N
        Scroll18.setOpaque(true);

        tbTahapPerkembangan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTahapPerkembangan.setName("tbTahapPerkembangan"); // NOI18N
        tbTahapPerkembangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTahapPerkembanganMouseClicked(evt);
            }
        });
        tbTahapPerkembangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTahapPerkembanganKeyPressed(evt);
            }
        });
        Scroll18.setViewportView(tbTahapPerkembangan);

        internalFrame7.add(Scroll18, java.awt.BorderLayout.CENTER);

        panelGlass28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Tuliskan Usia Dimana Anak Mencapai Tahapan Perkembangan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        panelGlass28.setName("panelGlass28"); // NOI18N
        panelGlass28.setPreferredSize(new java.awt.Dimension(44, 260));
        panelGlass28.setLayout(null);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Duduk : ");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass28.add(jLabel51);
        jLabel51.setBounds(0, 18, 150, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Merangkak : ");
        jLabel79.setName("jLabel79"); // NOI18N
        panelGlass28.add(jLabel79);
        jLabel79.setBounds(0, 46, 150, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Berjalan : ");
        jLabel80.setName("jLabel80"); // NOI18N
        panelGlass28.add(jLabel80);
        jLabel80.setBounds(0, 74, 150, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Bicara 1 Kata : ");
        jLabel81.setName("jLabel81"); // NOI18N
        panelGlass28.add(jLabel81);
        jLabel81.setBounds(0, 102, 150, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Bicara Dengan Kalimat -  ");
        jLabel82.setName("jLabel82"); // NOI18N
        panelGlass28.add(jLabel82);
        jLabel82.setBounds(0, 130, 150, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("kalimat : ");
        jLabel83.setName("jLabel83"); // NOI18N
        panelGlass28.add(jLabel83);
        jLabel83.setBounds(0, 144, 150, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Latihan BAK Pada  ");
        jLabel84.setName("jLabel84"); // NOI18N
        panelGlass28.add(jLabel84);
        jLabel84.setBounds(0, 172, 150, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Tempatnya : ");
        jLabel85.setName("jLabel85"); // NOI18N
        panelGlass28.add(jLabel85);
        jLabel85.setBounds(0, 186, 150, 23);

        Tduduk.setForeground(new java.awt.Color(0, 0, 0));
        Tduduk.setName("Tduduk"); // NOI18N
        Tduduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdudukKeyPressed(evt);
            }
        });
        panelGlass28.add(Tduduk);
        Tduduk.setBounds(152, 18, 350, 23);

        Tmerangkak.setForeground(new java.awt.Color(0, 0, 0));
        Tmerangkak.setName("Tmerangkak"); // NOI18N
        Tmerangkak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmerangkakKeyPressed(evt);
            }
        });
        panelGlass28.add(Tmerangkak);
        Tmerangkak.setBounds(152, 46, 350, 23);

        Tberjalan.setForeground(new java.awt.Color(0, 0, 0));
        Tberjalan.setName("Tberjalan"); // NOI18N
        Tberjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TberjalanKeyPressed(evt);
            }
        });
        panelGlass28.add(Tberjalan);
        Tberjalan.setBounds(152, 74, 350, 23);

        Tbicara1.setForeground(new java.awt.Color(0, 0, 0));
        Tbicara1.setName("Tbicara1"); // NOI18N
        Tbicara1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tbicara1KeyPressed(evt);
            }
        });
        panelGlass28.add(Tbicara1);
        Tbicara1.setBounds(152, 102, 350, 23);

        TbicaraDengan.setForeground(new java.awt.Color(0, 0, 0));
        TbicaraDengan.setName("TbicaraDengan"); // NOI18N
        TbicaraDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbicaraDenganKeyPressed(evt);
            }
        });
        panelGlass28.add(TbicaraDengan);
        TbicaraDengan.setBounds(152, 130, 350, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Latihan BAB Pada  ");
        jLabel86.setName("jLabel86"); // NOI18N
        panelGlass28.add(jLabel86);
        jLabel86.setBounds(0, 214, 150, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Tempatnya : ");
        jLabel87.setName("jLabel87"); // NOI18N
        panelGlass28.add(jLabel87);
        jLabel87.setBounds(0, 228, 150, 23);

        TlatihanBAK.setForeground(new java.awt.Color(0, 0, 0));
        TlatihanBAK.setName("TlatihanBAK"); // NOI18N
        TlatihanBAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlatihanBAKKeyPressed(evt);
            }
        });
        panelGlass28.add(TlatihanBAK);
        TlatihanBAK.setBounds(152, 172, 350, 23);

        TlatihanBAB.setForeground(new java.awt.Color(0, 0, 0));
        TlatihanBAB.setName("TlatihanBAB"); // NOI18N
        TlatihanBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlatihanBABKeyPressed(evt);
            }
        });
        panelGlass28.add(TlatihanBAB);
        TlatihanBAB.setBounds(152, 214, 350, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel129.setText("Relasi Anak Dengan saudara & Sebayanya : ");
        jLabel129.setName("jLabel129"); // NOI18N
        panelGlass28.add(jLabel129);
        jLabel129.setBounds(526, 18, 470, 23);

        Scroll26.setName("Scroll26"); // NOI18N
        Scroll26.setOpaque(true);

        Trelasi.setColumns(20);
        Trelasi.setRows(5);
        Trelasi.setName("Trelasi"); // NOI18N
        Trelasi.setPreferredSize(new java.awt.Dimension(170, 230));
        Trelasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrelasiKeyPressed(evt);
            }
        });
        Scroll26.setViewportView(Trelasi);

        panelGlass28.add(Scroll26);
        Scroll26.setBounds(526, 40, 470, 70);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("Kebiasaan - Kebiasaan Anak :");
        jLabel130.setName("jLabel130"); // NOI18N
        panelGlass28.add(jLabel130);
        jLabel130.setBounds(526, 114, 470, 23);

        Scroll42.setName("Scroll42"); // NOI18N
        Scroll42.setOpaque(true);

        Tkebiasaan.setColumns(20);
        Tkebiasaan.setRows(5);
        Tkebiasaan.setName("Tkebiasaan"); // NOI18N
        Tkebiasaan.setPreferredSize(new java.awt.Dimension(170, 230));
        Tkebiasaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkebiasaanKeyPressed(evt);
            }
        });
        Scroll42.setViewportView(Tkebiasaan);

        panelGlass28.add(Scroll42);
        Scroll42.setBounds(526, 136, 470, 70);

        internalFrame7.add(panelGlass28, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Tahap Perkembangan", internalFrame7);

        internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass34.setName("panelGlass34"); // NOI18N
        panelGlass34.setPreferredSize(new java.awt.Dimension(44, 125));
        panelGlass34.setLayout(new java.awt.BorderLayout());

        panelGlass29.setName("panelGlass29"); // NOI18N
        panelGlass29.setPreferredSize(new java.awt.Dimension(44, 313));
        panelGlass29.setLayout(new java.awt.BorderLayout());

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Riwayat Sekolah ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel5.setLayout(null);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Nama Sekolah : ");
        jLabel90.setName("jLabel90"); // NOI18N
        jPanel5.add(jLabel90);
        jLabel90.setBounds(0, 20, 130, 23);

        cmbSekolah.setForeground(new java.awt.Color(0, 0, 0));
        cmbSekolah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TK", "SD", "SMP/MTS", "SMA/MA" }));
        cmbSekolah.setName("cmbSekolah"); // NOI18N
        cmbSekolah.setOpaque(false);
        cmbSekolah.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbSekolah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbSekolahKeyPressed(evt);
            }
        });
        jPanel5.add(cmbSekolah);
        cmbSekolah.setBounds(133, 20, 80, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Kota : ");
        jLabel91.setName("jLabel91"); // NOI18N
        jPanel5.add(jLabel91);
        jLabel91.setBounds(0, 48, 130, 23);

        Tkota.setForeground(new java.awt.Color(0, 0, 0));
        Tkota.setName("Tkota"); // NOI18N
        Tkota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkotaKeyPressed(evt);
            }
        });
        jPanel5.add(Tkota);
        Tkota.setBounds(133, 48, 390, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Tahun Masuk : ");
        jLabel92.setName("jLabel92"); // NOI18N
        jPanel5.add(jLabel92);
        jLabel92.setBounds(0, 76, 130, 23);

        TthnMsk.setForeground(new java.awt.Color(0, 0, 0));
        TthnMsk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TthnMsk.setName("TthnMsk"); // NOI18N
        TthnMsk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TthnMskKeyPressed(evt);
            }
        });
        jPanel5.add(TthnMsk);
        TthnMsk.setBounds(133, 76, 50, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Tahun Selesai : ");
        jLabel131.setName("jLabel131"); // NOI18N
        jPanel5.add(jLabel131);
        jLabel131.setBounds(0, 104, 130, 23);

        TthnSelesai.setForeground(new java.awt.Color(0, 0, 0));
        TthnSelesai.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TthnSelesai.setName("TthnSelesai"); // NOI18N
        TthnSelesai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TthnSelesaiKeyPressed(evt);
            }
        });
        jPanel5.add(TthnSelesai);
        TthnSelesai.setBounds(133, 104, 50, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Lama Sekolah : ");
        jLabel132.setName("jLabel132"); // NOI18N
        jPanel5.add(jLabel132);
        jLabel132.setBounds(0, 132, 130, 23);

        TlamaSekolah.setForeground(new java.awt.Color(0, 0, 0));
        TlamaSekolah.setName("TlamaSekolah"); // NOI18N
        TlamaSekolah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaSekolahKeyPressed(evt);
            }
        });
        jPanel5.add(TlamaSekolah);
        TlamaSekolah.setBounds(133, 132, 390, 23);

        jPanel4.add(jPanel5);
        jPanel5.getAccessibleContext().setAccessibleName("");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Di Sekolah ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel6.setLayout(null);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Jenis Sekolah : ");
        jLabel93.setName("jLabel93"); // NOI18N
        jPanel6.add(jLabel93);
        jLabel93.setBounds(0, 20, 130, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Program Akselerasi : ");
        jLabel133.setName("jLabel133"); // NOI18N
        jPanel6.add(jLabel133);
        jLabel133.setBounds(0, 48, 130, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("Pernah Tinggal Kelas : ");
        jLabel134.setName("jLabel134"); // NOI18N
        jPanel6.add(jLabel134);
        jLabel134.setBounds(0, 76, 130, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Kesulitan Belajar Spesifik : ");
        jLabel135.setName("jLabel135"); // NOI18N
        jPanel6.add(jLabel135);
        jLabel135.setBounds(0, 104, 170, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("Bersemangat Pergi Sekolah : ");
        jLabel136.setName("jLabel136"); // NOI18N
        jPanel6.add(jLabel136);
        jLabel136.setBounds(0, 132, 170, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("Pernah Diskor / Dikeluarkan : ");
        jLabel137.setName("jLabel137"); // NOI18N
        jPanel6.add(jLabel137);
        jLabel137.setBounds(0, 160, 170, 23);

        TjnsSekolah.setForeground(new java.awt.Color(0, 0, 0));
        TjnsSekolah.setName("TjnsSekolah"); // NOI18N
        TjnsSekolah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjnsSekolahKeyPressed(evt);
            }
        });
        jPanel6.add(TjnsSekolah);
        TjnsSekolah.setBounds(133, 20, 390, 23);

        Tprogram.setForeground(new java.awt.Color(0, 0, 0));
        Tprogram.setName("Tprogram"); // NOI18N
        Tprogram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprogramKeyPressed(evt);
            }
        });
        jPanel6.add(Tprogram);
        Tprogram.setBounds(133, 48, 390, 23);

        cmbBelajar.setForeground(new java.awt.Color(0, 0, 0));
        cmbBelajar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbBelajar.setName("cmbBelajar"); // NOI18N
        cmbBelajar.setOpaque(false);
        cmbBelajar.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbBelajar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbBelajarKeyPressed(evt);
            }
        });
        jPanel6.add(cmbBelajar);
        cmbBelajar.setBounds(174, 104, 60, 23);

        cmbBersemangat.setForeground(new java.awt.Color(0, 0, 0));
        cmbBersemangat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbBersemangat.setName("cmbBersemangat"); // NOI18N
        cmbBersemangat.setOpaque(false);
        cmbBersemangat.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbBersemangat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbBersemangatKeyPressed(evt);
            }
        });
        jPanel6.add(cmbBersemangat);
        cmbBersemangat.setBounds(174, 132, 60, 23);

        cmbPernah.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbPernah.setName("cmbPernah"); // NOI18N
        cmbPernah.setOpaque(false);
        cmbPernah.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbPernah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbPernahKeyPressed(evt);
            }
        });
        jPanel6.add(cmbPernah);
        cmbPernah.setBounds(174, 160, 60, 23);

        TtglKelas.setForeground(new java.awt.Color(0, 0, 0));
        TtglKelas.setName("TtglKelas"); // NOI18N
        TtglKelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtglKelasKeyPressed(evt);
            }
        });
        jPanel6.add(TtglKelas);
        TtglKelas.setBounds(133, 76, 390, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Prestasi Akademik Disekolah / Tmpt. Kursus : ");
        jLabel138.setName("jLabel138"); // NOI18N
        jPanel6.add(jLabel138);
        jLabel138.setBounds(0, 188, 240, 23);

        Tprestasi.setForeground(new java.awt.Color(0, 0, 0));
        Tprestasi.setName("Tprestasi"); // NOI18N
        Tprestasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprestasiKeyPressed(evt);
            }
        });
        jPanel6.add(Tprestasi);
        Tprestasi.setBounds(243, 188, 390, 23);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setText("Mata Pelajaran Favorit : ");
        jLabel139.setName("jLabel139"); // NOI18N
        jPanel6.add(jLabel139);
        jLabel139.setBounds(0, 216, 240, 23);

        Tmata.setForeground(new java.awt.Color(0, 0, 0));
        Tmata.setName("Tmata"); // NOI18N
        Tmata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmataKeyPressed(evt);
            }
        });
        jPanel6.add(Tmata);
        Tmata.setBounds(243, 216, 390, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Mata Pelajaran Yang Dirasa Sulit : ");
        jLabel140.setName("jLabel140"); // NOI18N
        jPanel6.add(jLabel140);
        jLabel140.setBounds(0, 244, 240, 23);

        Tsulit.setForeground(new java.awt.Color(0, 0, 0));
        Tsulit.setName("Tsulit"); // NOI18N
        Tsulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsulitKeyPressed(evt);
            }
        });
        jPanel6.add(Tsulit);
        Tsulit.setBounds(243, 244, 390, 23);

        Tminat.setForeground(new java.awt.Color(0, 0, 0));
        Tminat.setName("Tminat"); // NOI18N
        Tminat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TminatKeyPressed(evt);
            }
        });
        jPanel6.add(Tminat);
        Tminat.setBounds(243, 272, 390, 23);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setText("Minat, Hobi, Keterampilan Yang Dikuasai : ");
        jLabel141.setName("jLabel141"); // NOI18N
        jPanel6.add(jLabel141);
        jLabel141.setBounds(0, 272, 240, 23);

        jPanel4.add(jPanel6);
        jPanel6.getAccessibleContext().setAccessibleName("");

        panelGlass29.add(jPanel4, java.awt.BorderLayout.CENTER);

        panelGlass34.add(panelGlass29, java.awt.BorderLayout.PAGE_START);

        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        panelGlass35.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Riwayat Sekolah ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        panelGlass35.setName("panelGlass35"); // NOI18N
        panelGlass35.setPreferredSize(new java.awt.Dimension(44, 125));
        panelGlass35.setLayout(new java.awt.BorderLayout());

        panelGlass36.setName("panelGlass36"); // NOI18N
        panelGlass36.setPreferredSize(new java.awt.Dimension(44, 50));
        panelGlass36.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('P');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+P");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        panelGlass36.add(BtnSimpan1);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('A');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+A");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        panelGlass36.add(BtnBatal1);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('U');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+U");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        panelGlass36.add(BtnHapus1);

        BtnEdit3.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit3.setMnemonic('N');
        BtnEdit3.setText("Ganti");
        BtnEdit3.setToolTipText("Alt+N");
        BtnEdit3.setName("BtnEdit3"); // NOI18N
        BtnEdit3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit3ActionPerformed(evt);
            }
        });
        BtnEdit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit3KeyPressed(evt);
            }
        });
        panelGlass36.add(BtnEdit3);

        panelGlass35.add(panelGlass36, java.awt.BorderLayout.PAGE_START);

        Scroll27.setName("Scroll27"); // NOI18N
        Scroll27.setOpaque(true);

        tbRiwayatSekolah.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRiwayatSekolah.setName("tbRiwayatSekolah"); // NOI18N
        tbRiwayatSekolah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatSekolahMouseClicked(evt);
            }
        });
        tbRiwayatSekolah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatSekolahKeyPressed(evt);
            }
        });
        Scroll27.setViewportView(tbRiwayatSekolah);

        panelGlass35.add(Scroll27, java.awt.BorderLayout.CENTER);

        jPanel7.add(panelGlass35);

        Scroll40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Di Sekolah ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll40.setName("Scroll40"); // NOI18N
        Scroll40.setOpaque(true);

        tbDisekolah.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDisekolah.setName("tbDisekolah"); // NOI18N
        tbDisekolah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDisekolahMouseClicked(evt);
            }
        });
        tbDisekolah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDisekolahKeyPressed(evt);
            }
        });
        Scroll40.setViewportView(tbDisekolah);

        jPanel7.add(Scroll40);

        panelGlass34.add(jPanel7, java.awt.BorderLayout.CENTER);

        internalFrame8.add(panelGlass34, java.awt.BorderLayout.CENTER);

        TabPsikologis.addTab("Pendidikan / Sekolah", internalFrame8);

        internalFrame9.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll28.setName("Scroll28"); // NOI18N
        Scroll28.setOpaque(true);

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
        Scroll28.setViewportView(tbObservasi);

        internalFrame9.add(Scroll28, java.awt.BorderLayout.CENTER);

        panelGlass30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Kondisi Psikologis Pasien Secara Umum ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass30.setName("panelGlass30"); // NOI18N
        panelGlass30.setPreferredSize(new java.awt.Dimension(44, 227));
        panelGlass30.setLayout(null);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Penampilan : ");
        jLabel88.setName("jLabel88"); // NOI18N
        panelGlass30.add(jLabel88);
        jLabel88.setBounds(0, 20, 150, 23);

        Tpenampilan.setForeground(new java.awt.Color(0, 0, 0));
        Tpenampilan.setName("Tpenampilan"); // NOI18N
        Tpenampilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenampilanKeyPressed(evt);
            }
        });
        panelGlass30.add(Tpenampilan);
        Tpenampilan.setBounds(152, 20, 860, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Ekspresi Wajah : ");
        jLabel89.setName("jLabel89"); // NOI18N
        panelGlass30.add(jLabel89);
        jLabel89.setBounds(0, 48, 150, 23);

        Tekspresi.setForeground(new java.awt.Color(0, 0, 0));
        Tekspresi.setName("Tekspresi"); // NOI18N
        Tekspresi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TekspresiKeyPressed(evt);
            }
        });
        panelGlass30.add(Tekspresi);
        Tekspresi.setBounds(152, 48, 860, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Perasaan/Suasana Hati : ");
        jLabel94.setName("jLabel94"); // NOI18N
        panelGlass30.add(jLabel94);
        jLabel94.setBounds(0, 76, 150, 23);

        Tperasaan.setForeground(new java.awt.Color(0, 0, 0));
        Tperasaan.setName("Tperasaan"); // NOI18N
        Tperasaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperasaanKeyPressed(evt);
            }
        });
        panelGlass30.add(Tperasaan);
        Tperasaan.setBounds(152, 76, 860, 23);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setText("Tingkah Laku : ");
        jLabel142.setName("jLabel142"); // NOI18N
        panelGlass30.add(jLabel142);
        jLabel142.setBounds(0, 104, 150, 23);

        Ttingkah.setForeground(new java.awt.Color(0, 0, 0));
        Ttingkah.setName("Ttingkah"); // NOI18N
        Ttingkah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtingkahKeyPressed(evt);
            }
        });
        panelGlass30.add(Ttingkah);
        Ttingkah.setBounds(152, 104, 860, 23);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setText("Fungsi Umum : ");
        jLabel143.setName("jLabel143"); // NOI18N
        panelGlass30.add(jLabel143);
        jLabel143.setBounds(0, 132, 150, 23);

        Tumum.setForeground(new java.awt.Color(0, 0, 0));
        Tumum.setName("Tumum"); // NOI18N
        Tumum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumumKeyPressed(evt);
            }
        });
        panelGlass30.add(Tumum);
        Tumum.setBounds(152, 132, 860, 23);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Fungsi Intelektual : ");
        jLabel144.setName("jLabel144"); // NOI18N
        panelGlass30.add(jLabel144);
        jLabel144.setBounds(0, 160, 150, 23);

        Tintelektual.setForeground(new java.awt.Color(0, 0, 0));
        Tintelektual.setName("Tintelektual"); // NOI18N
        Tintelektual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TintelektualKeyPressed(evt);
            }
        });
        panelGlass30.add(Tintelektual);
        Tintelektual.setBounds(152, 160, 860, 23);

        Tlain.setForeground(new java.awt.Color(0, 0, 0));
        Tlain.setName("Tlain"); // NOI18N
        Tlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKeyPressed(evt);
            }
        });
        panelGlass30.add(Tlain);
        Tlain.setBounds(152, 188, 860, 23);

        jLabel145.setForeground(new java.awt.Color(0, 0, 0));
        jLabel145.setText("Lain-lain : ");
        jLabel145.setName("jLabel145"); // NOI18N
        panelGlass30.add(jLabel145);
        jLabel145.setBounds(0, 188, 150, 23);

        internalFrame9.add(panelGlass30, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Observasi", internalFrame9);

        internalFrame10.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame10.setBorder(null);
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll30.setName("Scroll30"); // NOI18N
        Scroll30.setOpaque(true);

        tbTesPsikolog.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTesPsikolog.setName("tbTesPsikolog"); // NOI18N
        tbTesPsikolog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTesPsikologMouseClicked(evt);
            }
        });
        tbTesPsikolog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTesPsikologKeyPressed(evt);
            }
        });
        Scroll30.setViewportView(tbTesPsikolog);

        internalFrame10.add(Scroll30, java.awt.BorderLayout.CENTER);

        panelGlass31.setName("panelGlass31"); // NOI18N
        panelGlass31.setPreferredSize(new java.awt.Dimension(44, 125));
        panelGlass31.setLayout(null);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Rencana Tes : ");
        jLabel146.setName("jLabel146"); // NOI18N
        panelGlass31.add(jLabel146);
        jLabel146.setBounds(0, 10, 130, 23);

        jLabel147.setForeground(new java.awt.Color(0, 0, 0));
        jLabel147.setText("Tes Psikologi : ");
        jLabel147.setName("jLabel147"); // NOI18N
        panelGlass31.add(jLabel147);
        jLabel147.setBounds(0, 38, 130, 23);

        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("Waktu : ");
        jLabel148.setName("jLabel148"); // NOI18N
        panelGlass31.add(jLabel148);
        jLabel148.setBounds(0, 66, 130, 23);

        jLabel149.setForeground(new java.awt.Color(0, 0, 0));
        jLabel149.setText("Tester : ");
        jLabel149.setName("jLabel149"); // NOI18N
        panelGlass31.add(jLabel149);
        jLabel149.setBounds(0, 94, 130, 23);

        TTesPsiko.setForeground(new java.awt.Color(0, 0, 0));
        TTesPsiko.setName("TTesPsiko"); // NOI18N
        TTesPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTesPsikoKeyPressed(evt);
            }
        });
        panelGlass31.add(TTesPsiko);
        TTesPsiko.setBounds(133, 38, 390, 23);

        TWaktu.setForeground(new java.awt.Color(0, 0, 0));
        TWaktu.setName("TWaktu"); // NOI18N
        TWaktu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TWaktuKeyPressed(evt);
            }
        });
        panelGlass31.add(TWaktu);
        TWaktu.setBounds(133, 66, 390, 23);

        TTester.setForeground(new java.awt.Color(0, 0, 0));
        TTester.setName("TTester"); // NOI18N
        TTester.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTesterKeyPressed(evt);
            }
        });
        panelGlass31.add(TTester);
        TTester.setBounds(133, 94, 390, 23);

        tgl_rencana.setEditable(false);
        tgl_rencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2022" }));
        tgl_rencana.setDisplayFormat("dd-MM-yyyy");
        tgl_rencana.setName("tgl_rencana"); // NOI18N
        tgl_rencana.setOpaque(false);
        tgl_rencana.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass31.add(tgl_rencana);
        tgl_rencana.setBounds(133, 10, 90, 23);

        internalFrame10.add(panelGlass31, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Tes Psikologi", internalFrame10);

        internalFrame11.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll32.setName("Scroll32"); // NOI18N
        Scroll32.setOpaque(true);

        tbDinamika.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDinamika.setName("tbDinamika"); // NOI18N
        tbDinamika.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDinamikaMouseClicked(evt);
            }
        });
        tbDinamika.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDinamikaKeyPressed(evt);
            }
        });
        Scroll32.setViewportView(tbDinamika);

        internalFrame11.add(Scroll32, java.awt.BorderLayout.CENTER);

        panelGlass32.setName("panelGlass32"); // NOI18N
        panelGlass32.setPreferredSize(new java.awt.Dimension(44, 145));
        panelGlass32.setLayout(null);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Dinamika Psikologi : ");
        jLabel98.setName("jLabel98"); // NOI18N
        panelGlass32.add(jLabel98);
        jLabel98.setBounds(0, 10, 130, 23);

        Scroll33.setName("Scroll33"); // NOI18N
        Scroll33.setOpaque(true);

        Tdinamika.setColumns(20);
        Tdinamika.setRows(5);
        Tdinamika.setName("Tdinamika"); // NOI18N
        Tdinamika.setPreferredSize(new java.awt.Dimension(170, 230));
        Scroll33.setViewportView(Tdinamika);

        panelGlass32.add(Scroll33);
        Scroll33.setBounds(133, 10, 820, 123);

        internalFrame11.add(panelGlass32, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Dinamika Psikologi", internalFrame11);

        internalFrame12.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame12.setBorder(null);
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll34.setName("Scroll34"); // NOI18N
        Scroll34.setOpaque(true);

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
        Scroll34.setViewportView(tbDiagnosis);

        internalFrame12.add(Scroll34, java.awt.BorderLayout.CENTER);

        panelGlass33.setName("panelGlass33"); // NOI18N
        panelGlass33.setPreferredSize(new java.awt.Dimension(44, 145));
        panelGlass33.setLayout(null);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Kesan Awal : ");
        jLabel95.setName("jLabel95"); // NOI18N
        panelGlass33.add(jLabel95);
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

        panelGlass33.add(Scroll31);
        Scroll31.setBounds(133, 10, 410, 70);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Diagnosis Utama (dx) : ");
        jLabel96.setName("jLabel96"); // NOI18N
        panelGlass33.add(jLabel96);
        jLabel96.setBounds(0, 85, 130, 23);

        TDiagUtama.setForeground(new java.awt.Color(0, 0, 0));
        TDiagUtama.setName("TDiagUtama"); // NOI18N
        TDiagUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagUtamaKeyPressed(evt);
            }
        });
        panelGlass33.add(TDiagUtama);
        TDiagUtama.setBounds(133, 85, 710, 23);

        TDiagBanding.setForeground(new java.awt.Color(0, 0, 0));
        TDiagBanding.setName("TDiagBanding"); // NOI18N
        TDiagBanding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagBandingKeyPressed(evt);
            }
        });
        panelGlass33.add(TDiagBanding);
        TDiagBanding.setBounds(133, 113, 710, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Diagnosis Banding (dd) : ");
        jLabel97.setName("jLabel97"); // NOI18N
        panelGlass33.add(jLabel97);
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
        panelGlass33.add(btnPenyakit);
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
        panelGlass33.add(btnPenyakit1);
        btnPenyakit1.setBounds(845, 113, 28, 23);

        internalFrame12.add(panelGlass33, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Diagnosis (DSM / ICD / PPDGJ)", internalFrame12);

        internalFrame13.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame13.setBorder(null);
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll43.setName("Scroll43"); // NOI18N
        Scroll43.setOpaque(true);

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
        Scroll43.setViewportView(tbManifestasi);

        internalFrame13.add(Scroll43, java.awt.BorderLayout.CENTER);

        panelGlass37.setName("panelGlass37"); // NOI18N
        panelGlass37.setPreferredSize(new java.awt.Dimension(44, 145));
        panelGlass37.setLayout(null);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Manifestasi Fungsi : ");
        jLabel99.setName("jLabel99"); // NOI18N
        panelGlass37.add(jLabel99);
        jLabel99.setBounds(0, 10, 130, 23);

        Scroll44.setName("Scroll44"); // NOI18N
        Scroll44.setOpaque(true);

        TManifes.setColumns(20);
        TManifes.setRows(5);
        TManifes.setName("TManifes"); // NOI18N
        TManifes.setPreferredSize(new java.awt.Dimension(170, 230));
        Scroll44.setViewportView(TManifes);

        panelGlass37.add(Scroll44);
        Scroll44.setBounds(133, 10, 820, 123);

        internalFrame13.add(panelGlass37, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Manifestasi Fungsi Psikologis", internalFrame13);

        internalFrame16.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame16.setBorder(null);
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll45.setName("Scroll45"); // NOI18N
        Scroll45.setOpaque(true);

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
        Scroll45.setViewportView(tbPrognosis);

        internalFrame16.add(Scroll45, java.awt.BorderLayout.CENTER);

        panelGlass38.setName("panelGlass38"); // NOI18N
        panelGlass38.setPreferredSize(new java.awt.Dimension(44, 145));
        panelGlass38.setLayout(null);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Prognosis : ");
        jLabel100.setName("jLabel100"); // NOI18N
        panelGlass38.add(jLabel100);
        jLabel100.setBounds(0, 10, 130, 23);

        Scroll46.setName("Scroll46"); // NOI18N
        Scroll46.setOpaque(true);

        Tprognosis.setColumns(20);
        Tprognosis.setRows(5);
        Tprognosis.setName("Tprognosis"); // NOI18N
        Tprognosis.setPreferredSize(new java.awt.Dimension(170, 230));
        Scroll46.setViewportView(Tprognosis);

        panelGlass38.add(Scroll46);
        Scroll46.setBounds(133, 10, 820, 123);

        internalFrame16.add(panelGlass38, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Prognosis", internalFrame16);

        internalFrame17.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame17.setBorder(null);
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll47.setName("Scroll47"); // NOI18N
        Scroll47.setOpaque(true);

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
        Scroll47.setViewportView(tbTritmen);

        internalFrame17.add(Scroll47, java.awt.BorderLayout.CENTER);

        panelGlass39.setName("panelGlass39"); // NOI18N
        panelGlass39.setPreferredSize(new java.awt.Dimension(44, 215));
        panelGlass39.setLayout(null);

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

        panelGlass39.add(Scroll6);
        Scroll6.setBounds(153, 10, 590, 140);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Rencana Tritmen : ");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass39.add(jLabel44);
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
        panelGlass39.add(BtnPilihTritmen);
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
        panelGlass39.add(BtnHapusItem1);
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
        panelGlass39.add(BtnPerbaikiItem1);
        BtnPerbaikiItem1.setBounds(750, 83, 115, 27);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Pertemuan Selanjutnya : ");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass39.add(jLabel45);
        jLabel45.setBounds(0, 153, 150, 23);

        TPertemuan.setForeground(new java.awt.Color(0, 0, 0));
        TPertemuan.setName("TPertemuan"); // NOI18N
        TPertemuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPertemuanKeyPressed(evt);
            }
        });
        panelGlass39.add(TPertemuan);
        TPertemuan.setBounds(153, 153, 590, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Catatan Tambahan : ");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass39.add(jLabel46);
        jLabel46.setBounds(0, 181, 150, 23);

        TCatatan.setForeground(new java.awt.Color(0, 0, 0));
        TCatatan.setName("TCatatan"); // NOI18N
        TCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCatatanKeyPressed(evt);
            }
        });
        panelGlass39.add(TCatatan);
        TCatatan.setBounds(153, 181, 590, 23);

        internalFrame17.add(panelGlass39, java.awt.BorderLayout.PAGE_START);

        TabPsikologis.addTab("Rencana Tritmen", internalFrame17);

        internalFrame15.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame15.setBorder(null);
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll35.setName("Scroll35"); // NOI18N
        Scroll35.setOpaque(true);

        LoadHTML.setEditable(false);
        LoadHTML.setBorder(null);
        LoadHTML.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll35.setViewportView(LoadHTML);

        internalFrame15.add(Scroll35, java.awt.BorderLayout.CENTER);

        TabPsikologis.addTab("Preview Rekam Psikologis", internalFrame15);

        internalFrameUtama.add(TabPsikologis, java.awt.BorderLayout.CENTER);
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
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2022" }));
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
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2022" }));
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

        internalFrameUtama.add(panelGlass22, java.awt.BorderLayout.PAGE_END);

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

        internalFrameUtama.add(panelGlass24, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrameUtama, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        try {
            if (TNoRW.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRW, "Pasien");
            } else {
                cekRPA = 0;
                cekRPA = Sequel.cariInteger("SELECT COUNT(-1) cek FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat WHERE rp.no_rkm_medis='" + TNoRM.getText() + "' and pd.rekam_psikologis='anak_remaja'");
                if (TabPsikologis.getSelectedIndex() == 0) {                    
                    simpanDD();
                } else if (TabPsikologis.getSelectedIndex() == 1) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanIS();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 2) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanIOT();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 3) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanKP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 4) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanPPK();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 5) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanRK();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 6) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanRP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 7) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanTPer();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 8) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanPS();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 9) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanO();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 10) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanTP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 11) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanDP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 12) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanD();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 13) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanMF();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 14) {
                    if (cekRPA == 0) {
                        JOptionPane.showMessageDialog(null, "Simpan dulu data diri pasien/klien pd. tgl. kedatangan " + tgl_datang.getText() + " utk. proses berikutnya..!!");
                    } else {
                        simpanP();
                    }
                } else if (TabPsikologis.getSelectedIndex() == 15) {
                    if (cekRPA == 0) {
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
            emptTextIS();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            emptTextIOT();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            emptTextKP();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            emptTextPPK();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            emptTextRK();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            emptTextRP();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            emptTextTPer();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            emptTextPS();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            emptTextO();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            emptTextTP();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            emptTextDP();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            emptTextD();
        } else if (TabPsikologis.getSelectedIndex() == 13) {
            emptTextMF();
        } else if (TabPsikologis.getSelectedIndex() == 14) {
            emptTextP();
        } else if (TabPsikologis.getSelectedIndex() == 15) {
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
                    hapusIS();
                } else if (TabPsikologis.getSelectedIndex() == 2) {
                    hapusIOT();
                } else if (TabPsikologis.getSelectedIndex() == 3) {
                    hapusKP();
                } else if (TabPsikologis.getSelectedIndex() == 4) {
                    hapusPPK();
                } else if (TabPsikologis.getSelectedIndex() == 5) {
                    hapusRK();
                } else if (TabPsikologis.getSelectedIndex() == 6) {
                    hapusRP();
                } else if (TabPsikologis.getSelectedIndex() == 7) {
                    hapusTPer();
                } else if (TabPsikologis.getSelectedIndex() == 8) {
                    hapusPS();
                } else if (TabPsikologis.getSelectedIndex() == 9) {
                    hapusO();
                } else if (TabPsikologis.getSelectedIndex() == 10) {
                    hapusTP();
                } else if (TabPsikologis.getSelectedIndex() == 11) {
                    hapusDP();;
                } else if (TabPsikologis.getSelectedIndex() == 12) {
                    hapusD();
                } else if (TabPsikologis.getSelectedIndex() == 13) {
                    hapusMF();
                } else if (TabPsikologis.getSelectedIndex() == 14) {
                    hapusP();
                } else if (TabPsikologis.getSelectedIndex() == 15) {
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
            tampilIdentitasSaudara();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasORTU();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            tampilKeluhanMasalah();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilPsikologiKlinis();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilRiwayatKesehatan();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilRiwayatPerkembangan();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilTahapPerkembangan();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilDisekolah();
            tampilRiwayatSekolah();            
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilObservasi();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilTesPsikologi();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilDinamikaPsikologi();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            tampilDiagnosis();
        } else if (TabPsikologis.getSelectedIndex() == 13) {
            tampilManifestasiFungsi();
        } else if (TabPsikologis.getSelectedIndex() == 14) {
            tampilPrognosis();
        } else if (TabPsikologis.getSelectedIndex() == 15) {
            tampilRencanaTritmen();
        } else if (TabPsikologis.getSelectedIndex() == 16) {
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
            tampilIdentitasSaudara();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasORTU();
        } else if (TabPsikologis.getSelectedIndex() == 3) {  
            tampilKeluhanMasalah();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilPsikologiKlinis();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilRiwayatKesehatan();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilRiwayatPerkembangan();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilTahapPerkembangan();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilRiwayatSekolah();
            tampilDisekolah();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilObservasi();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilTesPsikologi();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilDinamikaPsikologi();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            tampilDiagnosis();
        } else if (TabPsikologis.getSelectedIndex() == 13) {
            tampilManifestasiFungsi();
        } else if (TabPsikologis.getSelectedIndex() == 14) {
            tampilPrognosis();
        } else if (TabPsikologis.getSelectedIndex() == 15) {
            tampilRencanaTritmen();
        } else if (TabPsikologis.getSelectedIndex() == 16) {
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
                gantiIS();
            } else if (TabPsikologis.getSelectedIndex() == 2) {
                gantiIOT();
            } else if (TabPsikologis.getSelectedIndex() == 3) {
                gantiKP();
            } else if (TabPsikologis.getSelectedIndex() == 4) {
                gantiPPK();                               
            } else if (TabPsikologis.getSelectedIndex() == 5) {
                gantiRK();
            } else if (TabPsikologis.getSelectedIndex() == 6) {
                gantiRP();
            } else if (TabPsikologis.getSelectedIndex() == 7) {
                gantiTPer();
            } else if (TabPsikologis.getSelectedIndex() == 8) {
                gantiPS();
            } else if (TabPsikologis.getSelectedIndex() == 9) {
                gantiO();
            } else if (TabPsikologis.getSelectedIndex() == 10) {
                gantiTP();
            } else if (TabPsikologis.getSelectedIndex() == 11) {
                gantiDP();
            } else if (TabPsikologis.getSelectedIndex() == 12) {
                gantiD();
            } else if (TabPsikologis.getSelectedIndex() == 13) {
                gantiMF();
            } else if (TabPsikologis.getSelectedIndex() == 14) {
                gantiP();
            } else if (TabPsikologis.getSelectedIndex() == 15) {
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

    private void TposisiSauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TposisiSauKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TposisiSau, TketSau);
        }
    }//GEN-LAST:event_TposisiSauKeyPressed

    private void TketSauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketSauKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TposisiSau, BtnSimpan);
        }
    }//GEN-LAST:event_TketSauKeyPressed

    private void TumurSauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurSauKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TumurSau, cmbUmur);
        }
    }//GEN-LAST:event_TumurSauKeyPressed

    private void TnmAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnmAyah, TumurAyah);
        }
    }//GEN-LAST:event_TnmAyahKeyPressed

    private void TumurAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TumurAyah, cmbPndAyah);
        }
    }//GEN-LAST:event_TumurAyahKeyPressed

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

    private void tbIdentitasSaudaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIdentitasSaudaraKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIS();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbIdentitasSaudaraKeyPressed

    private void tbIdentitasSaudaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIdentitasSaudaraMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataIS();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbIdentitasSaudaraMouseClicked

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        if (TabPsikologis.getSelectedIndex() == 0) {
            tampilDataDiri();
            emptTextDD();
        } else if (TabPsikologis.getSelectedIndex() == 1) {
            tampilIdentitasSaudara();
            emptTextIS();
        } else if (TabPsikologis.getSelectedIndex() == 2) {
            tampilIdentitasORTU();
            emptTextIOT();
        } else if (TabPsikologis.getSelectedIndex() == 3) {
            tampilKeluhanMasalah();
            emptTextKP();
        } else if (TabPsikologis.getSelectedIndex() == 4) {
            tampilPsikologiKlinis();
            emptTextPPK();
        } else if (TabPsikologis.getSelectedIndex() == 5) {
            tampilRiwayatKesehatan();
            emptTextRK();
        } else if (TabPsikologis.getSelectedIndex() == 6) {
            tampilRiwayatPerkembangan();
            emptTextRP();
        } else if (TabPsikologis.getSelectedIndex() == 7) {
            tampilTahapPerkembangan();
            emptTextTPer();
        } else if (TabPsikologis.getSelectedIndex() == 8) {
            tampilRiwayatSekolah();
            tampilDisekolah();
            emptTextPS();
        } else if (TabPsikologis.getSelectedIndex() == 9) {
            tampilObservasi();
            emptTextO();
        } else if (TabPsikologis.getSelectedIndex() == 10) {
            tampilTesPsikologi();
            emptTextTP();
        } else if (TabPsikologis.getSelectedIndex() == 11) {
            tampilDinamikaPsikologi();
            emptTextDP();
        } else if (TabPsikologis.getSelectedIndex() == 12) {
            tampilDiagnosis();
            emptTextD();
        } else if (TabPsikologis.getSelectedIndex() == 13) {
            tampilManifestasiFungsi();
            emptTextMF();
        } else if (TabPsikologis.getSelectedIndex() == 14) {
            tampilPrognosis();
            emptTextP();
        } else if (TabPsikologis.getSelectedIndex() == 15) {
            tampilRencanaTritmen();
            emptTextRT();
        } else if (TabPsikologis.getSelectedIndex() == 16) {
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

    private void tbIdentitasOrtuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIdentitasOrtuKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIOT();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbIdentitasOrtuKeyPressed

    private void tbIdentitasOrtuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIdentitasOrtuMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataIOT();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbIdentitasOrtuMouseClicked

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

    private void tbRiwayatKesehatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKesehatanKeyPressed
        if (tabMode7.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwayatKesehatan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKesehatanKeyPressed

    private void tbRiwayatKesehatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatKesehatanMouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getDataRiwayatKesehatan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatKesehatanMouseClicked

    private void tbRiwayatPerkembanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatPerkembanganMouseClicked
        if (tabMode8.getRowCount() != 0) {
            try {
                getDataRiwayatPerkembangan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatPerkembanganMouseClicked

    private void tbRiwayatPerkembanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatPerkembanganKeyPressed
        if (tabMode8.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwayatPerkembangan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatPerkembanganKeyPressed

    private void tbTahapPerkembanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTahapPerkembanganMouseClicked
        if (tabMode9.getRowCount() != 0) {
            try {
                getDataTahapPerkembangan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTahapPerkembanganMouseClicked

    private void tbTahapPerkembanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTahapPerkembanganKeyPressed
        if (tabMode9.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTahapPerkembangan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTahapPerkembanganKeyPressed

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
                cetakRPA();
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

    private void TnmSauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmSauKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TNoRW, TumurSau);
        }
    }//GEN-LAST:event_TnmSauKeyPressed

    private void TanakKeAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanakKeAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, cmbAgamaAyah, TkawinKeAyah);
        }
    }//GEN-LAST:event_TanakKeAyahKeyPressed

    private void TkawinKeAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkawinKeAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TanakKeAyah, TalamatAyah);
        }
    }//GEN-LAST:event_TkawinKeAyahKeyPressed

    private void TnoHpAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoHpAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TalamatAyah, TKetAyah);
        }
    }//GEN-LAST:event_TnoHpAyahKeyPressed

    private void TpekerjaanAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, cmbPndAyah, cmbAgamaAyah);
        }
    }//GEN-LAST:event_TpekerjaanAyahKeyPressed

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
        WindowDataKeluhan.setLocationRelativeTo(internalFrameUtama);
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
            WindowDataKeluhan.setLocationRelativeTo(internalFrameUtama);
            WindowDataKeluhan.setVisible(true);
            WindowDataKeluhan.requestFocus();
        }
    }//GEN-LAST:event_BtnPerbaikiItemActionPerformed

    private void btnKeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluhanActionPerformed
        akses.setform("DlgRekamPsikologisAnak");
        keluhan.setSize(707, internalFrameUtama.getHeight() - 40);
        keluhan.setLocationRelativeTo(internalFrameUtama);
        keluhan.cekKategori.setText("anak_remaja");
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
                if (kdkeluhan.getText().equals("KP0096")) {
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
                if (kdkeluhan.getText().equals("KP0096")) {
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

    private void Scroll17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll17KeyPressed

    private void TdudukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdudukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, Tduduk, Tmerangkak);
        }
    }//GEN-LAST:event_TdudukKeyPressed

    private void TmerangkakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmerangkakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, Tduduk, Tberjalan);
        }
    }//GEN-LAST:event_TmerangkakKeyPressed

    private void TberjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TberjalanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, Tmerangkak, Tbicara1);
        }
    }//GEN-LAST:event_TberjalanKeyPressed

    private void Tbicara1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tbicara1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, Tberjalan, TbicaraDengan);
        }
    }//GEN-LAST:event_Tbicara1KeyPressed

    private void TbicaraDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbicaraDenganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, Tbicara1, TlatihanBAK);
        }
    }//GEN-LAST:event_TbicaraDenganKeyPressed

    private void tbRiwayatSekolahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatSekolahMouseClicked
        if (tabMode10.getRowCount() != 0) {
            try {
                getDataRiwayatSekolah();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatSekolahMouseClicked

    private void tbRiwayatSekolahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatSekolahKeyPressed
        if (tabMode10.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwayatSekolah();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatSekolahKeyPressed

    private void TkotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkotaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            TthnMsk.requestFocus();
        }
    }//GEN-LAST:event_TkotaKeyPressed

    private void TthnMskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TthnMskKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            TthnSelesai.requestFocus();
        }
    }//GEN-LAST:event_TthnMskKeyPressed

    private void TjnsSekolahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjnsSekolahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tprogram.requestFocus();
        }
    }//GEN-LAST:event_TjnsSekolahKeyPressed

    private void tbObservasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObservasiMouseClicked
        if (tabMode11.getRowCount() != 0) {
            try {
                getDataObservasi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObservasiMouseClicked

    private void tbObservasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObservasiKeyPressed
        if (tabMode11.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataObservasi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObservasiKeyPressed

    private void tbTesPsikologMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTesPsikologMouseClicked
        if (tabMode12.getRowCount() != 0) {
            try {
                getDataTesPsiko();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTesPsikologMouseClicked

    private void tbTesPsikologKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTesPsikologKeyPressed
        if (tabMode12.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTesPsiko();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTesPsikologKeyPressed

    private void tbDinamikaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDinamikaMouseClicked
        if (tabMode13.getRowCount() != 0) {
            try {
                getDataDinamikaPsikologi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDinamikaMouseClicked

    private void tbDinamikaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDinamikaKeyPressed
        if (tabMode13.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDinamikaPsikologi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDinamikaKeyPressed

    private void tbDiagnosisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosisMouseClicked
        if (tabMode15.getRowCount() != 0) {
            try {
                getDataDiagnosis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDiagnosisMouseClicked

    private void tbDiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosisKeyPressed
        if (tabMode15.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDiagnosis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagnosisKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgRekamPsikologisAnak");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrameUtama.getWidth() - 40, internalFrameUtama.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrameUtama);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void TalamatAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TnoHpAyah.requestFocus();
        }
    }//GEN-LAST:event_TalamatAyahKeyPressed

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
            TPerubahanAnak.requestFocus();
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

    private void TnmIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TKetAyah, TumurIbu);
        }
    }//GEN-LAST:event_TnmIbuKeyPressed

    private void TumurIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnmIbu, cmbPndIbu);
        }
    }//GEN-LAST:event_TumurIbuKeyPressed

    private void TpekerjaanIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, cmbPndIbu, cmbAgamaIbu);
        }
    }//GEN-LAST:event_TpekerjaanIbuKeyPressed

    private void TanakKeIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanakKeIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, cmbAgamaIbu, TkawinKeIbu);
        }
    }//GEN-LAST:event_TanakKeIbuKeyPressed

    private void TkawinKeIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkawinKeIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TanakKeIbu, TalamatIbu);
        }
    }//GEN-LAST:event_TkawinKeIbuKeyPressed

    private void TalamatIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {            
            TnoHpIbu.requestFocus();
        }
    }//GEN-LAST:event_TalamatIbuKeyPressed

    private void TnoHpIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoHpIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TalamatIbu, TKetIbu);
        }
    }//GEN-LAST:event_TnoHpIbuKeyPressed

    private void TKetAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnoHpAyah, TnmIbu);
        }
    }//GEN-LAST:event_TKetAyahKeyPressed

    private void TKetIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnoHpIbu, TnmAyah);
        }
    }//GEN-LAST:event_TKetIbuKeyPressed

    private void cmbPndAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPndAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {            
            TpekerjaanAyah.requestFocus();
        }
    }//GEN-LAST:event_cmbPndAyahKeyPressed

    private void cmbAgamaAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAgamaAyahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {            
            TanakKeAyah.requestFocus();
        }
    }//GEN-LAST:event_cmbAgamaAyahKeyPressed

    private void cmbPndIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPndIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {            
            TpekerjaanIbu.requestFocus();
        }
    }//GEN-LAST:event_cmbPndIbuKeyPressed

    private void cmbAgamaIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAgamaIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {            
            TanakKeIbu.requestFocus();
        }
    }//GEN-LAST:event_cmbAgamaIbuKeyPressed

    private void ChkcopasAyahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkcopasAyahActionPerformed
        if (ChkcopasAyah.isSelected() == true) {
            if (TalamatAyah.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan isi dulu dengan benar alamat Ayah...!!!!");
                ChkcopasAyah.setSelected(false);
                TalamatAyah.requestFocus();
            } else {
                TalamatIbu.setText(TalamatAyah.getText());
            }
        } else if (ChkcopasAyah.isSelected() == false) {
            TalamatIbu.setText("");
        }
    }//GEN-LAST:event_ChkcopasAyahActionPerformed

    private void ChkcopasIbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkcopasIbuActionPerformed
        if (ChkcopasIbu.isSelected() == true) {
            if (TalamatIbu.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan isi dulu dengan benar alamat Ibu...!!!!");
                ChkcopasIbu.setSelected(false);
                TalamatIbu.requestFocus();
            } else {
                TalamatAyah.setText(TalamatIbu.getText());
            }
        } else if (ChkcopasIbu.isSelected() == false) {
            TalamatAyah.setText("");
        }
    }//GEN-LAST:event_ChkcopasIbuActionPerformed

    private void TPerubahanAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPerubahanAnakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPerubahanDiriSendiri.requestFocus();
        }
    }//GEN-LAST:event_TPerubahanAnakKeyPressed

    private void TPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPenyakitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRumahSakit.requestFocus();
        }
    }//GEN-LAST:event_TPenyakitKeyPressed

    private void TRumahSakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRumahSakitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDemam.requestFocus();
        }
    }//GEN-LAST:event_TRumahSakitKeyPressed

    private void TDemamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDemamKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TPneum.requestFocus();
        }
    }//GEN-LAST:event_TDemamKeyPressed

    private void TPneumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPneumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TFlu.requestFocus();
        }
    }//GEN-LAST:event_TPneumKeyPressed

    private void TFluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFluKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TEnse.requestFocus();
        }
    }//GEN-LAST:event_TFluKeyPressed

    private void TEnseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEnseKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TMeni.requestFocus();
        }
    }//GEN-LAST:event_TEnseKeyPressed

    private void TMeniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMeniKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TKejang.requestFocus();
        }
    }//GEN-LAST:event_TMeniKeyPressed

    private void TKejangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKejangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TTidak.requestFocus();
        }
    }//GEN-LAST:event_TKejangKeyPressed

    private void TTidakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTidakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TGeger.requestFocus();
        }
    }//GEN-LAST:event_TTidakKeyPressed

    private void TGegerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGegerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TPingsan.requestFocus();
        }
    }//GEN-LAST:event_TGegerKeyPressed

    private void TPingsanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPingsanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TPusing.requestFocus();
        }
    }//GEN-LAST:event_TPingsanKeyPressed

    private void TPusingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPusingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TAmandel.requestFocus();
        }
    }//GEN-LAST:event_TPusingKeyPressed

    private void TAmandelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAmandelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TLihat.requestFocus();
        }
    }//GEN-LAST:event_TAmandelKeyPressed

    private void TLihatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLihatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TDengar.requestFocus();
        }
    }//GEN-LAST:event_TLihatKeyPressed

    private void TDengarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDengarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TGigi.requestFocus();
        }
    }//GEN-LAST:event_TDengarKeyPressed

    private void TGigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGigiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TBerat.requestFocus();
        }
    }//GEN-LAST:event_TGigiKeyPressed

    private void TBeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBeratKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TAlergi.requestFocus();
        }
    }//GEN-LAST:event_TBeratKeyPressed

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TKulit.requestFocus();
        }
    }//GEN-LAST:event_TAlergiKeyPressed

    private void TKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKulitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TAsma.requestFocus();
        }
    }//GEN-LAST:event_TKulitKeyPressed

    private void TAsmaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAsmaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TKepala.requestFocus();
        }
    }//GEN-LAST:event_TAsmaKeyPressed

    private void TKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKepalaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TPerut.requestFocus();
        }
    }//GEN-LAST:event_TKepalaKeyPressed

    private void TPerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPerutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TRawan.requestFocus();
        }
    }//GEN-LAST:event_TPerutKeyPressed

    private void TRawanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRawanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TAnemia.requestFocus();
        }
    }//GEN-LAST:event_TRawanKeyPressed

    private void TAnemiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAnemiaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TDarah.requestFocus();
        }
    }//GEN-LAST:event_TAnemiaKeyPressed

    private void TDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDarahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TSinus.requestFocus();
        }
    }//GEN-LAST:event_TDarahKeyPressed

    private void TSinusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSinusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TJantung.requestFocus();
        }
    }//GEN-LAST:event_TSinusKeyPressed

    private void TJantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJantungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            THiper.requestFocus();
        }
    }//GEN-LAST:event_TJantungKeyPressed

    private void THiperKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THiperKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TPenyakit.requestFocus();
        }
    }//GEN-LAST:event_THiperKeyPressed

    private void cmbDinginkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDinginkanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbDirencanakan.requestFocus();
        }
    }//GEN-LAST:event_cmbDinginkanKeyPressed

    private void cmbDirencanakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDirencanakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbProses.requestFocus();
        }
    }//GEN-LAST:event_cmbDirencanakanKeyPressed

    private void cmbProsesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbProsesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TIbuTertekan.requestFocus();
        }
    }//GEN-LAST:event_cmbProsesKeyPressed

    private void TIbuTertekanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TIbuTertekanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tdukungan.requestFocus();
        }
    }//GEN-LAST:event_TIbuTertekanKeyPressed

    private void TdukunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdukunganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TLamaProses.requestFocus();
        }
    }//GEN-LAST:event_TdukunganKeyPressed

    private void TLamaProsesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLamaProsesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TPrematur.requestFocus();
        }
    }//GEN-LAST:event_TLamaProsesKeyPressed

    private void TPrematurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPrematurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TTerlambat.requestFocus();
        }
    }//GEN-LAST:event_TPrematurKeyPressed

    private void TTerlambatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerlambatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TProsesLahir.requestFocus();
        }
    }//GEN-LAST:event_TTerlambatKeyPressed

    private void TProsesLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TProsesLahirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDinginkan.requestFocus();
        }
    }//GEN-LAST:event_TProsesLahirKeyPressed

    private void TlatihanBAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlatihanBAKKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlatihanBAB.requestFocus();
        }
    }//GEN-LAST:event_TlatihanBAKKeyPressed

    private void TlatihanBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlatihanBABKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trelasi.requestFocus();
        }
    }//GEN-LAST:event_TlatihanBABKeyPressed

    private void TrelasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrelasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkebiasaan.requestFocus();
        }
    }//GEN-LAST:event_TrelasiKeyPressed

    private void TkebiasaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkebiasaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tduduk.requestFocus();
        }
    }//GEN-LAST:event_TkebiasaanKeyPressed

    private void cmbSekolahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSekolahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkota.requestFocus();
        }
    }//GEN-LAST:event_cmbSekolahKeyPressed

    private void TthnSelesaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TthnSelesaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            TlamaSekolah.requestFocus();
        }
    }//GEN-LAST:event_TthnSelesaiKeyPressed

    private void TlamaSekolahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaSekolahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            cmbSekolah.requestFocus();
        }
    }//GEN-LAST:event_TlamaSekolahKeyPressed

    private void TprogramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprogramKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            TtglKelas.requestFocus();
        }
    }//GEN-LAST:event_TprogramKeyPressed

    private void TtglKelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtglKelasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            cmbBelajar.requestFocus();
        }
    }//GEN-LAST:event_TtglKelasKeyPressed

    private void cmbBelajarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBelajarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {            
            cmbBersemangat.requestFocus();
        }
    }//GEN-LAST:event_cmbBelajarKeyPressed

    private void cmbBersemangatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBersemangatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {            
            cmbPernah.requestFocus();
        }
    }//GEN-LAST:event_cmbBersemangatKeyPressed

    private void cmbPernahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPernahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {            
            Tprestasi.requestFocus();
        }
    }//GEN-LAST:event_cmbPernahKeyPressed

    private void TprestasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprestasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tmata.requestFocus();
        }
    }//GEN-LAST:event_TprestasiKeyPressed

    private void TmataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tsulit.requestFocus();
        }
    }//GEN-LAST:event_TmataKeyPressed

    private void TsulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsulitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tminat.requestFocus();
        }
    }//GEN-LAST:event_TsulitKeyPressed

    private void TminatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TminatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            TjnsSekolah.requestFocus();
        }
    }//GEN-LAST:event_TminatKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (Tkota.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Nama kota tempat sekolah harus diisi..!!");
            Tkota.requestFocus();
        } else if (TthnMsk.getText().equals("") || TthnMsk.getText().length() < 4) {
            JOptionPane.showMessageDialog(null, "Tahun masuk sekolah tidak valid..!!");
            TthnMsk.requestFocus();
        } else if (TthnSelesai.getText().equals("") || TthnSelesai.getText().length() < 4) {
            JOptionPane.showMessageDialog(null, "Tahun selesai sekolah tidak valid..!!");
            TthnSelesai.requestFocus();
        } else if (TthnSelesai.getText().equals("")) {
            Valid.textKosong(TthnSelesai, "Tahun Selesai Sekolah");
            TthnSelesai.requestFocus();
        } else if (TlamaSekolah.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Lama sekolah harus diisi..!!");
            TlamaSekolah.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from psikolog_anak_remaja_riw_sekolah where no_rawat='" + TNoRW.getText() + "'") > 0
                    && Sequel.cariInteger("select count(-1) from psikolog_anak_remaja_riw_sekolah where no_rawat='" + TNoRW.getText() + "' and nm_sekolah='" + cmbSekolah.getSelectedItem().toString() + "'") > 0) {
                JOptionPane.showMessageDialog(null, "Nama sekolah " + cmbSekolah.getSelectedItem().toString() + " utk. " + TPasien.getText() + " sudah tersimpan..!!");
            } else if (Sequel.cariInteger("select count(-1) from psikolog_anak_remaja_riw_sekolah where no_rawat='" + TNoRW.getText() + "'") == 4) {
                JOptionPane.showMessageDialog(null, "Data riwayat sekolah sudah lengkap sesuai ketentuan..!!");
            } else {
                Sequel.menyimpan("psikolog_anak_remaja_riw_sekolah", "'" + TNoRW.getText() + "','" + cmbSekolah.getSelectedItem().toString() + "',"
                        + "'" + Tkota.getText() + "','" + TthnMsk.getText() + "','" + TthnSelesai.getText() + "',"
                        + "'" + TlamaSekolah.getText() + "','" + Sequel.cariIsi("select now()") + "'", "Data Riwayat Sekolah Pasien/Klien");
                tampilRiwayatSekolah();
                emptTextRS();
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptTextRS();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tabMode10.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat sekolah pada tabel...!!!!");
            tbRiwayatSekolah.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anak_remaja_riw_sekolah where waktu_simpan='" + wktSimpan + "'");
            tampilRiwayatSekolah();
            emptTextRS();
            wktSimpan = "";
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnEdit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit3ActionPerformed
        if (tabMode10.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih satu datanya pada tabel riwayat sekolah...!!!!");
            tbRiwayatSekolah.requestFocus();
        } else if (Tkota.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Nama kota tempat sekolah harus diisi..!!");
            Tkota.requestFocus();
        } else if (TthnMsk.getText().equals("") || TthnMsk.getText().length() < 4) {
            JOptionPane.showMessageDialog(null, "Tahun masuk sekolah tidak valid..!!");
            TthnMsk.requestFocus();
        } else if (TthnSelesai.getText().equals("") || TthnSelesai.getText().length() < 4) {
            JOptionPane.showMessageDialog(null, "Tahun selesai sekolah tidak valid..!!");
            TthnSelesai.requestFocus();
        } else if (TthnSelesai.getText().equals("")) {
            Valid.textKosong(TthnSelesai, "Tahun Selesai Sekolah");
            TthnSelesai.requestFocus();
        } else if (TlamaSekolah.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Lama sekolah harus diisi..!!");
            TlamaSekolah.requestFocus();
        } else {
            Sequel.mengedit("psikolog_anak_remaja_riw_sekolah", "waktu_simpan='" + wktSimpan + "'",
                    "nm_sekolah='" + cmbSekolah.getSelectedItem().toString() + "',kota='" + Tkota.getText() + "',"
                    + "thn_masuk='" + TthnMsk.getText() + "',thn_selesai='" + TthnSelesai.getText() + "',lama_sekolah='" + TlamaSekolah.getText() + "'");
            tampilRiwayatSekolah();
            emptTextRS();
        }
    }//GEN-LAST:event_BtnEdit3ActionPerformed

    private void BtnEdit3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEdit3KeyPressed

    private void tbDisekolahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDisekolahMouseClicked
        if (tabMode16.getRowCount() != 0) {
            try {
                getDataDisekolah();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDisekolahMouseClicked

    private void tbDisekolahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDisekolahKeyPressed
        if (tabMode16.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDisekolah();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDisekolahKeyPressed

    private void TpenampilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenampilanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tekspresi.requestFocus();
        }
    }//GEN-LAST:event_TpenampilanKeyPressed

    private void TekspresiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TekspresiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tperasaan.requestFocus();
        }
    }//GEN-LAST:event_TekspresiKeyPressed

    private void TperasaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperasaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Ttingkah.requestFocus();
        }
    }//GEN-LAST:event_TperasaanKeyPressed

    private void TtingkahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtingkahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tumum.requestFocus();
        }
    }//GEN-LAST:event_TtingkahKeyPressed

    private void TumumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tintelektual.requestFocus();
        }
    }//GEN-LAST:event_TumumKeyPressed

    private void TintelektualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TintelektualKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tlain.requestFocus();
        }
    }//GEN-LAST:event_TintelektualKeyPressed

    private void TlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Tpenampilan.requestFocus();
        }
    }//GEN-LAST:event_TlainKeyPressed

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

    private void TKesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagUtama.requestFocus();
        }
    }//GEN-LAST:event_TKesanKeyPressed

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
        akses.setform("DlgRekamPsikologisAnak");
        penyakit.isCek();
        penyakit.TCari.requestFocus();
        penyakit.setSize(internalFrameUtama.getWidth()-40,internalFrameUtama.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrameUtama);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnPenyakitActionPerformed

    private void btnPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyakit1ActionPerformed
        pilihan = 2;
        akses.setform("DlgRekamPsikologisAnak");
        penyakit.isCek();
        penyakit.TCari.requestFocus();
        penyakit.setSize(internalFrameUtama.getWidth()-40,internalFrameUtama.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrameUtama);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnPenyakit1ActionPerformed

    private void tbManifestasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbManifestasiMouseClicked
        if (tabMode14.getRowCount() != 0) {
            try {
                getDataManifes();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbManifestasiMouseClicked

    private void tbManifestasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbManifestasiKeyPressed
        if (tabMode14.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataManifes();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbManifestasiKeyPressed

    private void tbPrognosisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPrognosisMouseClicked
        if (tabMode17.getRowCount() != 0) {
            try {
                getDataPrognosis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPrognosisMouseClicked

    private void tbPrognosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPrognosisKeyPressed
        if (tabMode17.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPrognosis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPrognosisKeyPressed

    private void tbTritmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTritmenMouseClicked
        if (tabMode19.getRowCount() != 0) {
            try {
                getDataTritmen();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTritmenMouseClicked

    private void tbTritmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTritmenKeyPressed
        if (tabMode19.getRowCount() != 0) {
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
        if (tabMode18.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Jenis rencana tritmen yang dipilih/ditentukan belum ada...!!");
            BtnPilihTritmen.requestFocus();
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data rencana tritmen yang dipilih pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else if (tbItemTritmen.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rencana tritmennya pada tabel...!!!!");
            tbItemTritmen.requestFocus();
        } else {
            tabMode18.removeRow(tbItemTritmen.getSelectedRow());
            kdtritmen.setText("");
            nmtritmen.setText("");
            tritmenLain.setText("");
            BtnHapusItem1.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusItem1ActionPerformed

    private void BtnPerbaikiItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerbaikiItem1ActionPerformed
        if (tabMode18.getRowCount() == 0) {
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

    private void btnTritmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTritmenActionPerformed
        akses.setform("DlgRekamPsikologisAnak");
        tritmen.setSize(622, internalFrameUtama.getHeight() - 40);
        tritmen.setLocationRelativeTo(internalFrameUtama);
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
                        tabMode18.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                        kdtritmen.setText("");
                        nmtritmen.setText("");
                        tritmenLain.setText("");
                        btnTritmen.requestFocus();
                    }

                } else {
                    tabMode18.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
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
                        tabMode18.removeRow(tbItemTritmen.getSelectedRow());
                        tabMode18.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
                        BtnCloseIn4ActionPerformed(null);
                    }
                } else {
                    tabMode18.removeRow(tbItemTritmen.getSelectedRow());
                    tabMode18.addRow(new Object[]{kdtritmen.getText(), nmtritmen.getText(), tritmenLain.getText(), Sequel.cariIsi("select now()")});
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRekamPsikologisAnak dialog = new DlgRekamPsikologisAnak(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnEdit2;
    private widget.Button BtnEdit3;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapusItem;
    private widget.Button BtnHapusItem1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerbaikiItem;
    private widget.Button BtnPerbaikiItem1;
    private widget.Button BtnPilihKeluhan;
    private widget.Button BtnPilihTritmen;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpan3;
    public widget.CekBox ChkcopasAyah;
    public widget.CekBox ChkcopasIbu;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput4;
    private widget.PanelBiasa FormInput5;
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
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll30;
    private widget.ScrollPane Scroll31;
    private widget.ScrollPane Scroll32;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll34;
    private widget.ScrollPane Scroll35;
    private widget.ScrollPane Scroll36;
    private widget.ScrollPane Scroll37;
    private widget.ScrollPane Scroll38;
    private widget.ScrollPane Scroll39;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll40;
    private widget.ScrollPane Scroll41;
    private widget.ScrollPane Scroll42;
    private widget.ScrollPane Scroll43;
    private widget.ScrollPane Scroll44;
    private widget.ScrollPane Scroll45;
    private widget.ScrollPane Scroll46;
    private widget.ScrollPane Scroll47;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.TextBox TAgama;
    private widget.TextArea TAlamat;
    private widget.TextArea TAlasan;
    private widget.TextArea TAlasanMencariBantuan;
    private widget.TextBox TAlergi;
    private widget.TextBox TAmandel;
    private widget.TextBox TAnemia;
    private widget.TextBox TAsma;
    private widget.TextBox TBerat;
    private widget.TextBox TCari;
    private widget.TextBox TCatatan;
    private widget.TextBox TDarah;
    private widget.TextBox TDemam;
    private widget.TextBox TDengar;
    private widget.TextBox TDiagBanding;
    private widget.TextBox TDiagUtama;
    private widget.TextBox TEnse;
    private widget.TextBox TFlu;
    private widget.TextBox TGeger;
    private widget.TextBox TGigi;
    private widget.TextArea THarapan;
    private widget.TextBox THiper;
    private widget.TextArea TIbuTertekan;
    private widget.TextBox TJantung;
    private widget.TextBox TKejang;
    private widget.TextBox TKepala;
    private widget.TextArea TKesan;
    private widget.TextBox TKetAyah;
    private widget.TextBox TKetIbu;
    private widget.TextBox TKulit;
    private widget.TextBox TLamaProses;
    private widget.TextBox TLihat;
    private widget.TextArea TManifes;
    private widget.TextBox TMeni;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TPasien;
    private widget.TextBox TPendidikan;
    private widget.TextArea TPenyakit;
    private widget.TextArea TPermasalahansaatIni;
    private widget.TextBox TPertemuan;
    private widget.TextArea TPerubahanAnak;
    private widget.TextArea TPerubahanDiriSendiri;
    private widget.TextArea TPerubahanKeluarga;
    private widget.TextBox TPerut;
    private widget.TextBox TPingsan;
    private widget.TextBox TPneum;
    private widget.TextBox TPrematur;
    private widget.TextBox TProsesLahir;
    private widget.TextBox TPusing;
    private widget.TextBox TRawan;
    private widget.TextArea TRumahSakit;
    private widget.TextBox TSinus;
    private widget.TextBox TTerlambat;
    private widget.TextBox TTesPsiko;
    private widget.TextBox TTester;
    private widget.TextBox TTidak;
    private widget.TextBox TUrutanLahir;
    private widget.TextBox TWaktu;
    private javax.swing.JTabbedPane TabPsikologis;
    private widget.TextArea TalamatAyah;
    private widget.TextArea TalamatIbu;
    private widget.TextBox TanakKeAyah;
    private widget.TextBox TanakKeIbu;
    private widget.TextBox Tberjalan;
    private widget.TextBox Tbicara1;
    private widget.TextBox TbicaraDengan;
    private widget.TextArea Tdinamika;
    private widget.TextBox Tduduk;
    private widget.TextArea Tdukungan;
    private widget.TextBox Tekspresi;
    private widget.TextBox Tintelektual;
    private widget.TextBox Tjk;
    private widget.TextBox TjnsSekolah;
    private widget.TextBox TkawinKeAyah;
    private widget.TextBox TkawinKeIbu;
    private widget.TextArea Tkebiasaan;
    private widget.TextBox TketSau;
    private widget.TextBox Tkota;
    private widget.TextBox Tlain;
    private widget.TextBox TlamaSekolah;
    private widget.TextBox TlatihanBAB;
    private widget.TextBox TlatihanBAK;
    private widget.TextBox Tmata;
    private widget.TextBox Tmerangkak;
    private widget.TextBox Tminat;
    private widget.TextBox TnmAyah;
    private widget.TextBox TnmIbu;
    private widget.TextBox TnmPsikolog;
    private widget.TextBox TnmSau;
    private widget.TextBox TnoHpAyah;
    private widget.TextBox TnoHpIbu;
    private widget.TextBox TpekerjaanAyah;
    private widget.TextBox TpekerjaanIbu;
    private widget.TextBox Tpenampilan;
    private widget.TextBox Tperasaan;
    private widget.TextBox TposisiSau;
    private widget.TextBox Tprestasi;
    private widget.TextArea Tprognosis;
    private widget.TextBox Tprogram;
    private widget.TextArea Trelasi;
    private widget.TextBox Tsulit;
    private widget.TextBox TtglKelas;
    private widget.TextBox TthnMsk;
    private widget.TextBox TthnSelesai;
    private widget.TextBox Ttingkah;
    private widget.TextBox Ttl;
    private widget.TextBox Tumum;
    private widget.TextBox TumurAyah;
    private widget.TextBox TumurIbu;
    private widget.TextBox TumurSau;
    private javax.swing.JDialog WindowDataKeluhan;
    private javax.swing.JDialog WindowDataTritmen;
    private widget.Button btnKeluhan;
    private widget.Button btnPenyakit;
    private widget.Button btnPenyakit1;
    private widget.Button btnPetugas;
    private widget.Button btnTritmen;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbAgamaAyah;
    private widget.ComboBox cmbAgamaIbu;
    private widget.ComboBox cmbBelajar;
    private widget.ComboBox cmbBersemangat;
    private widget.ComboBox cmbDinginkan;
    private widget.ComboBox cmbDirencanakan;
    private widget.ComboBox cmbJK;
    private widget.ComboBox cmbLimit;
    private widget.ComboBox cmbPernah;
    private widget.ComboBox cmbPndAyah;
    private widget.ComboBox cmbPndIbu;
    private widget.ComboBox cmbProses;
    private widget.ComboBox cmbSekolah;
    private widget.ComboBox cmbUmur;
    private widget.InternalFrame internalFrame0;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.InternalFrame internalFrameUtama;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel14;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel15;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
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
    private widget.panelisi panelGlass34;
    private widget.panelisi panelGlass35;
    private widget.panelisi panelGlass36;
    private widget.panelisi panelGlass37;
    private widget.panelisi panelGlass38;
    private widget.panelisi panelGlass39;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDiagnosis;
    private widget.Table tbDinamika;
    private widget.Table tbDisekolah;
    private widget.Table tbIdentitasOrtu;
    private widget.Table tbIdentitasSaudara;
    private widget.Table tbItemMasalah;
    private widget.Table tbItemTritmen;
    private widget.Table tbKeluhan;
    private widget.Table tbManifestasi;
    private widget.Table tbObservasi;
    private widget.Table tbPrognosis;
    private widget.Table tbPsikologiKlinis;
    private widget.Table tbRiwayatKesehatan;
    private widget.Table tbRiwayatPerkembangan;
    private widget.Table tbRiwayatSekolah;
    private widget.Table tbTahapPerkembangan;
    private widget.Table tbTesPsikolog;
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
        
        cekRPA = 0;
        cekRPA = Sequel.cariInteger("SELECT COUNT(-1) cek FROM psikolog_data_diri_klien pd "
                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat WHERE rp.no_rkm_medis='" + TNoRM.getText() + "' and pd.rekam_psikologis='anak_remaja'");
        
        if (cekRPA > 0) {
            TUrutanLahir.setText(Sequel.cariIsi("select urutan_lahir from psikolog_data_diri_klien where no_rawat='" + norw + "'"));
        } else if (cekRPA == 0) {
            TUrutanLahir.setText("");
        }
        TabPsikologis.setSelectedIndex(0);
    }
    
    private void isDataDiri() {
        tglKedatangan = "";
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                    + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y')) ttl, "
                    + "p.pnd, p.agama, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, "
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
                    TAgama.setText(rs.getString("agama"));
                    TPendidikan.setText(rs.getString("pnd"));
                    TAlamat.setText(rs.getString("almt"));
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
    
    private void tampilRiwayatSekolah() {
        Valid.tabelKosong(tabMode10);
        try {
            ps16 = koneksi.prepareStatement("SELECT rs.no_rawat, p.no_rkm_medis, p.nm_pasien, rs.nm_sekolah, rs.kota, rs.thn_masuk, rs.thn_selesai, "
                    + "rs.lama_sekolah, rs.waktu_simpan FROM psikolog_anak_remaja_riw_sekolah rs INNER JOIN reg_periksa rp on rp.no_rawat = rs.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis WHERE "
                    + "rs.no_rawat like '%" + TNoRW.getText() + "%' order by rs.waktu_simpan limit 4");
            try {
                rs16 = ps16.executeQuery();
                while (rs16.next()) {
                    tabMode10.addRow(new Object[]{
                        rs16.getString("no_rawat"),
                        rs16.getString("no_rkm_medis"),
                        rs16.getString("nm_pasien"),                        
                        rs16.getString("nm_sekolah"),
                        rs16.getString("kota"),
                        rs16.getString("thn_masuk"),
                        rs16.getString("thn_selesai"),
                        rs16.getString("lama_sekolah"),
                        rs16.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs16 != null) {
                    rs16.close();
                }
                if (ps16 != null) {
                    ps16.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilDisekolah() {
        Valid.tabelKosong(tabMode16);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps15 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.jns_sekolah, pd.program_akselerasi, pd.pernah_tinggal_kelas, "
                        + "pd.kesulitan_bljr_spesifik, pd.semangat_pergi_sekolah, pd.pernah_diskor_dikeluarkan_dr_sekolah, pd.prestasi_disekolah_tmp_kursus, "
                        + "pd.mata_pljrn_favorit, pd.mata_pljrn_dirasa_sulit, pd.minat_hobi_keterampilan_dikuasai FROM psikolog_anak_remaja_disekolah pd "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis WHERE "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and rp.no_rawat like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.jns_sekolah like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.program_akselerasi like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.pernah_tinggal_kelas like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.kesulitan_bljr_spesifik like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.semangat_pergi_sekolah like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.pernah_diskor_dikeluarkan_dr_sekolah like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.prestasi_disekolah_tmp_kursus like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.mata_pljrn_favorit like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.mata_pljrn_dirasa_sulit like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.minat_hobi_keterampilan_dikuasai like ? order by rp.tgl_registrasi desc");
            } else {
                ps15 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.jns_sekolah, pd.program_akselerasi, pd.pernah_tinggal_kelas, "
                        + "pd.kesulitan_bljr_spesifik, pd.semangat_pergi_sekolah, pd.pernah_diskor_dikeluarkan_dr_sekolah, pd.prestasi_disekolah_tmp_kursus, "
                        + "pd.mata_pljrn_favorit, pd.mata_pljrn_dirasa_sulit, pd.minat_hobi_keterampilan_dikuasai FROM psikolog_anak_remaja_disekolah pd "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis WHERE "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and rp.no_rawat like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.jns_sekolah like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.program_akselerasi like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.pernah_tinggal_kelas like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.kesulitan_bljr_spesifik like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.semangat_pergi_sekolah like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.pernah_diskor_dikeluarkan_dr_sekolah like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.prestasi_disekolah_tmp_kursus like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.mata_pljrn_favorit like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.mata_pljrn_dirasa_sulit like ? or "
                        + "pd.no_rawat like '%" + TNoRW.getText() + "%' and pd.minat_hobi_keterampilan_dikuasai like ? "
                        + "order by rp.tgl_registrasi desc LIMIT " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps15.setString(1, "%" + TCari.getText().trim() + "%");                
                ps15.setString(2, "%" + TCari.getText().trim() + "%");                
                ps15.setString(3, "%" + TCari.getText().trim() + "%");                
                ps15.setString(4, "%" + TCari.getText().trim() + "%");                
                ps15.setString(5, "%" + TCari.getText().trim() + "%");                
                ps15.setString(6, "%" + TCari.getText().trim() + "%");                
                ps15.setString(7, "%" + TCari.getText().trim() + "%");                
                ps15.setString(8, "%" + TCari.getText().trim() + "%");
                ps15.setString(9, "%" + TCari.getText().trim() + "%");
                ps15.setString(10, "%" + TCari.getText().trim() + "%");
                ps15.setString(11, "%" + TCari.getText().trim() + "%");
                ps15.setString(12, "%" + TCari.getText().trim() + "%");
                ps15.setString(13, "%" + TCari.getText().trim() + "%");
                rs15 = ps15.executeQuery();
                while (rs15.next()) {
                    tabMode16.addRow(new Object[]{
                        rs15.getString("no_rawat"),
                        rs15.getString("no_rkm_medis"),
                        rs15.getString("nm_pasien"),                         
                        rs15.getString("jns_sekolah"),
                        rs15.getString("program_akselerasi"),
                        rs15.getString("pernah_tinggal_kelas"),
                        rs15.getString("kesulitan_bljr_spesifik"),
                        rs15.getString("semangat_pergi_sekolah"),
                        rs15.getString("pernah_diskor_dikeluarkan_dr_sekolah"),
                        rs15.getString("prestasi_disekolah_tmp_kursus"),
                        rs15.getString("mata_pljrn_favorit"),
                        rs15.getString("mata_pljrn_dirasa_sulit"),
                        rs15.getString("minat_hobi_keterampilan_dikuasai")
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
        LCount.setText("" + tabMode16.getRowCount());
    }
    
    private void tampilDataDiri() {
        Valid.tabelKosong(tabMode1);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y')) ttl, "
                        + "p.agama, p.pnd, pd.urutan_lahir, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab WHERE "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and rp.no_rawat like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and if(p.jk = 'L','Laki-laki','Perempuan') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y')) like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.agama like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.pnd like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pd.urutan_lahir like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) like ? order by rp.tgl_registrasi desc");
            } else {
                ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y')) ttl, "
                        + "p.agama, p.pnd, pd.urutan_lahir, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab WHERE "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and rp.no_rawat like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and if(p.jk = 'L','Laki-laki','Perempuan') like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y')) like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.agama like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.pnd like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pd.urutan_lahir like ? or "
                        + "p.no_rkm_medis like '%" + TNoRM.getText() + "%' and rp.tgl_registrasi between ? and ? and rp.umurdaftar<=17 and "
                        + "rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) like ? "
                        + "order by rp.tgl_registrasi desc LIMIT " + cmbLimit.getSelectedItem().toString() + "");
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
                        rs1.getString("agama"),
                        rs1.getString("pnd"),
                        rs1.getString("urutan_lahir"),
                        rs1.getString("almt")
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
    
    private void tampilIdentitasSaudara() {
        Valid.tabelKosong(tabMode2);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pak.nama, concat(pak.umur,' ',pak.sttsumur) umur, "
                        + "if(pak.jk='L','Laki-laki','Perempuan') jk, pak.posisi, pak.keterangan, pak.waktu_simpan, pak.umur usia, "
                        + "pak.sttsumur from psikolog_anggota_keluarga pak inner join pasien p on p.no_rkm_medis =pak.no_rkm_medis WHERE "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='anak_remaja' and pak.nama like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='anak_remaja' and if(pak.jk='L','Laki-laki','Perempuan') like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='anak_remaja' and pak.posisi like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='anak_remaja' and pak.keterangan like ? order by pak.waktu_simpan desc");
            } else {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pak.nama, concat(pak.umur,' ',pak.sttsumur) umur, "
                        + "if(pak.jk='L','Laki-laki','Perempuan') jk, pak.posisi, pak.keterangan, pak.waktu_simpan, pak.umur usia, "
                        + "pak.sttsumur from psikolog_anggota_keluarga pak inner join pasien p on p.no_rkm_medis =pak.no_rkm_medis WHERE "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='anak_remaja' and pak.nama like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='anak_remaja' and if(pak.jk='L','Laki-laki','Perempuan') like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='anak_remaja' and pak.posisi like ? or "
                        + "pak.no_rkm_medis like '%" + TNoRM.getText() + "%' and pak.rekam_psikologis='anak_remaja' and pak.keterangan like ? order by pak.waktu_simpan desc "
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
    
    private void tampilIdentitasORTU() {
        Valid.tabelKosong(tabMode3);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, po.nm_ayah, concat(po.umur_ayah,' tahun') usiaAyah, po.pnddkn_ayah, po.pkrjaan_ayah, "
                        + "po.agama_ayah, po.anak_ke_ayah, po.perkawinan_ke_ayah, po.alamat_ayah, po.no_hp_ayah, po.ket_ayah, po.nm_ibu, "
                        + "concat(po.umur_ibu,' tahun') usiaIbu, po.pnddkn_ibu, po.pkrjaan_ibu, po.agama_ibu, po.anak_ke_ibu, po.perkawinan_ke_ibu, "
                        + "po.alamat_ibu, po.no_hp_ibu, po.ket_ibu, po.waktu_simpan, po.umur_ayah, po.umur_ibu FROM psikolog_anak_remaja_identitas_ortu po "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = po.no_rkm_medis WHERE "
                        + "po.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.no_rkm_medis like ? or "
                        + "po.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.nm_pasien like ? or "
                        + "po.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and po.nm_ayah like ? or "
                        + "po.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and po.nm_ibu like ? order by po.waktu_simpan desc");
            } else {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, po.nm_ayah, concat(po.umur_ayah,' tahun') usiaAyah, po.pnddkn_ayah, po.pkrjaan_ayah, "
                        + "po.agama_ayah, po.anak_ke_ayah, po.perkawinan_ke_ayah, po.alamat_ayah, po.no_hp_ayah, po.ket_ayah, po.nm_ibu, "
                        + "concat(po.umur_ibu,' tahun') usiaIbu, po.pnddkn_ibu, po.pkrjaan_ibu, po.agama_ibu, po.anak_ke_ibu, po.perkawinan_ke_ibu, "
                        + "po.alamat_ibu, po.no_hp_ibu, po.ket_ibu, po.waktu_simpan, po.umur_ayah, po.umur_ibu FROM psikolog_anak_remaja_identitas_ortu po "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = po.no_rkm_medis WHERE "
                        + "po.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.no_rkm_medis like ? or "
                        + "po.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and p.nm_pasien like ? or "
                        + "po.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and po.nm_ayah like ? or "
                        + "po.no_rkm_medis LIKE '%" + TNoRM.getText() + "%' and po.nm_ibu like ? order by po.waktu_simpan desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps3.setString(1, "%" + TCari.getText().trim() + "%");
                ps3.setString(2, "%" + TCari.getText().trim() + "%");
                ps3.setString(3, "%" + TCari.getText().trim() + "%");
                ps3.setString(4, "%" + TCari.getText().trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode3.addRow(new Object[]{
                        rs3.getString("no_rkm_medis"),
                        rs3.getString("nm_pasien"),
                        rs3.getString("nm_ayah"),
                        rs3.getString("usiaAyah"),
                        rs3.getString("pnddkn_ayah"),
                        rs3.getString("pkrjaan_ayah"),
                        rs3.getString("agama_ayah"),
                        rs3.getString("anak_ke_ayah"),
                        rs3.getString("perkawinan_ke_ayah"),
                        rs3.getString("alamat_ayah"),
                        rs3.getString("no_hp_ayah"),
                        rs3.getString("ket_ayah"),
                        
                        rs3.getString("nm_ibu"),
                        rs3.getString("usiaIbu"),
                        rs3.getString("pnddkn_ibu"),
                        rs3.getString("pkrjaan_ibu"),
                        rs3.getString("agama_ibu"),
                        rs3.getString("anak_ke_ibu"),
                        rs3.getString("perkawinan_ke_ibu"),
                        rs3.getString("alamat_ibu"),
                        rs3.getString("no_hp_ibu"),
                        rs3.getString("ket_ibu"),
                        rs3.getString("waktu_simpan"),
                        rs3.getString("umur_ayah"),
                        rs3.getString("umur_ibu")
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
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<= 17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pk.no_rawat like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<= 17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<= 17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<= 17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pk.permasalahan like ? "
                        + "order by pk.waktu_simpan desc");
            } else {
                ps4 = koneksi.prepareStatement("SELECT pk.no_rawat, pk.waktu_simpan, p.no_rkm_medis, p.nm_pasien, "
                        + "pk.permasalahan FROM psikolog_keluhan pk INNER JOIN reg_periksa rp ON rp.no_rawat=pk.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pk.no_rawat WHERE "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<= 17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pk.no_rawat like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<= 17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<= 17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<= 17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pk.permasalahan like ? "
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
                        + "pw.permasalahan_dinilai, pw.alasan_permasalahan_dinilai, pw.harapan, pw.perubahan_diinginkan_anak, pw.perubahan_diinginkan_diri_sendiri, "
                        + "pw.perubahan_diinginkan_keluarga FROM psikolog_wawancara_klinis pw "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pw.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.no_rawat like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and p.no_rkm_medis like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and p.nm_pasien like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.permasalahan_saat_ini like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.alasan_mencari_bantuan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.permasalahan_dinilai like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.alasan_permasalahan_dinilai like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.harapan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.perubahan_diinginkan_anak like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.perubahan_diinginkan_diri_sendiri like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.perubahan_diinginkan_keluarga like ? order by pw.no_rawat desc");
            } else {
                ps6 = koneksi.prepareStatement("SELECT pw.no_rawat, p.no_rkm_medis, p.nm_pasien, pw.permasalahan_saat_ini, pw.alasan_mencari_bantuan, "
                        + "pw.permasalahan_dinilai, pw.alasan_permasalahan_dinilai, pw.harapan, pw.perubahan_diinginkan_anak, pw.perubahan_diinginkan_diri_sendiri, "
                        + "pw.perubahan_diinginkan_keluarga FROM psikolog_wawancara_klinis pw "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pw.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.no_rawat like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and p.no_rkm_medis like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and p.nm_pasien like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.permasalahan_saat_ini like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.alasan_mencari_bantuan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.permasalahan_dinilai like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.alasan_permasalahan_dinilai like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.harapan like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.perubahan_diinginkan_anak like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.perubahan_diinginkan_diri_sendiri like ? or "
                        + "pw.no_rawat LIKE '%" + TNoRW.getText() + "%' and pw.rekam_psikologis='Anak/Remaja' and pw.perubahan_diinginkan_keluarga like ? "
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
                ps6.setString(11, "%" + TCari.getText().trim() + "%");
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
                        rs6.getString("perubahan_diinginkan_anak"),
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
    
    private void tampilRiwayatKesehatan() {
        Valid.tabelKosong(tabMode7);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps7 = koneksi.prepareStatement("SELECT pk.no_rawat, p.no_rkm_medis, p.nm_pasien, pk.demm_tinggi, pk.pneumonia, pk.flu, pk.ensefalitis, "
                        + "pk.meningitis, pk.kejang, pk.tdk_sadarkan_diri, pk.geger_otak, pk.pingsan, pk.pusing, pk.permslhn_terkait_amandel, "
                        + "pk.permslhn_penglihatan, pk.permslhn_pendengaran, pk.permslhn_terkait_gigi, pk.permslhn_bb, pk.alergi, "
                        + "pk.permslhn_kulit, pk.asma, pk.sakit_kepala, pk.permslhn_terkait_perut, pk.rawan_kecelakaan, pk.anemia, "
                        + "pk.tknn_drh_tinggi_rendah, pk.permslhn_terkait_sinus, pk.permslhn_jantung, pk.hiperaktif, pk.ket_penyakit_lain, "
                        + "pk.ket_dirawat_dirumah_skt FROM psikolog_anak_remaja_riw_kes pk INNER JOIN reg_periksa rp ON rp.no_rawat=pk.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.no_rawat like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? order by pk.no_rawat desc");
            } else {
                ps7 = koneksi.prepareStatement("SELECT pk.no_rawat, p.no_rkm_medis, p.nm_pasien, pk.demm_tinggi, pk.pneumonia, pk.flu, pk.ensefalitis, "
                        + "pk.meningitis, pk.kejang, pk.tdk_sadarkan_diri, pk.geger_otak, pk.pingsan, pk.pusing, pk.permslhn_terkait_amandel, "
                        + "pk.permslhn_penglihatan, pk.permslhn_pendengaran, pk.permslhn_terkait_gigi, pk.permslhn_bb, pk.alergi, "
                        + "pk.permslhn_kulit, pk.asma, pk.sakit_kepala, pk.permslhn_terkait_perut, pk.rawan_kecelakaan, pk.anemia, "
                        + "pk.tknn_drh_tinggi_rendah, pk.permslhn_terkait_sinus, pk.permslhn_jantung, pk.hiperaktif, pk.ket_penyakit_lain, "
                        + "pk.ket_dirawat_dirumah_skt FROM psikolog_anak_remaja_riw_kes pk INNER JOIN reg_periksa rp ON rp.no_rawat=pk.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and pk.no_rawat like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pk.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? order by pk.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps7.setString(1, "%" + TCari.getText().trim() + "%");
                ps7.setString(2, "%" + TCari.getText().trim() + "%");
                ps7.setString(3, "%" + TCari.getText().trim() + "%");
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    tabMode7.addRow(new Object[]{
                        rs7.getString("no_rawat"),
                        rs7.getString("no_rkm_medis"),
                        rs7.getString("nm_pasien"),                        
                        rs7.getString("demm_tinggi"),
                        rs7.getString("pneumonia"),
                        rs7.getString("flu"),
                        rs7.getString("ensefalitis"),
                        rs7.getString("meningitis"),
                        rs7.getString("kejang"),
                        rs7.getString("tdk_sadarkan_diri"),
                        rs7.getString("geger_otak"),
                        rs7.getString("pingsan"),
                        rs7.getString("pusing"),
                        rs7.getString("permslhn_terkait_amandel"),
                        rs7.getString("permslhn_penglihatan"),
                        rs7.getString("permslhn_pendengaran"),
                        rs7.getString("permslhn_terkait_gigi"),
                        rs7.getString("permslhn_bb"),
                        rs7.getString("alergi"),
                        rs7.getString("permslhn_kulit"),
                        rs7.getString("asma"),
                        rs7.getString("sakit_kepala"),
                        rs7.getString("permslhn_terkait_perut"),
                        rs7.getString("rawan_kecelakaan"),
                        rs7.getString("anemia"),
                        rs7.getString("tknn_drh_tinggi_rendah"),
                        rs7.getString("permslhn_terkait_sinus"),
                        rs7.getString("permslhn_jantung"),
                        rs7.getString("hiperaktif"),
                        rs7.getString("ket_penyakit_lain"),
                        rs7.getString("ket_dirawat_dirumah_skt")
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
    
    private void tampilRiwayatPerkembangan() {
        Valid.tabelKosong(tabMode8);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps8 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.diinginkan, pp.direncanakan, pp.proses_hml_normal, "
                        + "pp.ibu_sakit_tertekan_saat_hamil, pp.dukungan_penerimaan_suami, pp.lama_proses_lahiran, pp.cepat_prematur, "
                        + "pp.cepat_terlambat, pp.proses_melahirkan FROM psikolog_anak_remaja_riw_perkembangan pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "                                
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.diinginkan like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.direncanakan like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.proses_hml_normal like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.ibu_sakit_tertekan_saat_hamil like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.dukungan_penerimaan_suami like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.lama_proses_lahiran like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.cepat_prematur like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.cepat_prematur like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.proses_melahirkan like ? order by pp.no_rawat desc");
            } else {
                ps8 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.diinginkan, pp.direncanakan, pp.proses_hml_normal, "
                        + "pp.ibu_sakit_tertekan_saat_hamil, pp.dukungan_penerimaan_suami, pp.lama_proses_lahiran, pp.cepat_prematur, "
                        + "pp.cepat_terlambat, pp.proses_melahirkan FROM psikolog_anak_remaja_riw_perkembangan pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.diinginkan like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.direncanakan like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.proses_hml_normal like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.ibu_sakit_tertekan_saat_hamil like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.dukungan_penerimaan_suami like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.lama_proses_lahiran like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.cepat_prematur like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.cepat_prematur like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and pp.proses_melahirkan like ? order by pp.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
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
                ps8.setString(12, "%" + TCari.getText().trim() + "%");
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    tabMode8.addRow(new Object[]{
                        rs8.getString("no_rawat"),
                        rs8.getString("no_rkm_medis"),
                        rs8.getString("nm_pasien"),                        
                        rs8.getString("diinginkan"),
                        rs8.getString("direncanakan"),
                        rs8.getString("proses_hml_normal"),
                        rs8.getString("ibu_sakit_tertekan_saat_hamil"),
                        rs8.getString("dukungan_penerimaan_suami"),                        
                        rs8.getString("lama_proses_lahiran"),
                        rs8.getString("cepat_prematur"),
                        rs8.getString("cepat_terlambat"),
                        rs8.getString("proses_melahirkan")
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
    
    private void tampilTahapPerkembangan() {
        Valid.tabelKosong(tabMode9);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps9 = koneksi.prepareStatement("SELECT tp.no_rawat, p.no_rkm_medis, p.nm_pasien, tp.duduk, tp.merangkak, tp.berjalan, tp.bicara_1_kata, "
                        + "tp.bicara_dg_kalimat, tp.latihan_bak_pd_tempat, tp.latihan_bab_pd_tempat, tp.relasi_anak_dg_saudara, "
                        + "tp.kebiasaan_anak FROM psikolog_anak_remaja_thp_perkembangan tp INNER JOIN reg_periksa rp ON rp.no_rawat=tp.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.no_rawat like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "                                
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.duduk like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.merangkak like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.berjalan like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.bicara_1_kata like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.bicara_dg_kalimat like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.latihan_bak_pd_tempat like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.latihan_bab_pd_tempat like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.relasi_anak_dg_saudara like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.kebiasaan_anak like ? order by tp.no_rawat desc");
            } else {
                ps9 = koneksi.prepareStatement("SELECT tp.no_rawat, p.no_rkm_medis, p.nm_pasien, tp.duduk, tp.merangkak, tp.berjalan, tp.bicara_1_kata, "
                        + "tp.bicara_dg_kalimat, tp.latihan_bak_pd_tempat, tp.latihan_bab_pd_tempat, tp.relasi_anak_dg_saudara, "
                        + "tp.kebiasaan_anak FROM psikolog_anak_remaja_thp_perkembangan tp INNER JOIN reg_periksa rp ON rp.no_rawat=tp.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.no_rawat like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "                                
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.duduk like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.merangkak like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.berjalan like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.bicara_1_kata like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.bicara_dg_kalimat like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.latihan_bak_pd_tempat like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.latihan_bab_pd_tempat like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.relasi_anak_dg_saudara like ? or "
                        + "tp.no_rawat LIKE '%" + TNoRW.getText() + "%' and tp.kebiasaan_anak like ? "
                        + "order by tp.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
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
                rs9 = ps9.executeQuery();
                while (rs9.next()) {
                    tabMode9.addRow(new Object[]{
                        rs9.getString("no_rawat"),
                        rs9.getString("no_rkm_medis"),
                        rs9.getString("nm_pasien"),                        
                        rs9.getString("duduk"),
                        rs9.getString("merangkak"),
                        rs9.getString("berjalan"),
                        rs9.getString("bicara_1_kata"),
                        rs9.getString("bicara_dg_kalimat"),                        
                        rs9.getString("latihan_bak_pd_tempat"),
                        rs9.getString("latihan_bab_pd_tempat"),
                        rs9.getString("relasi_anak_dg_saudara"),                        
                        rs9.getString("kebiasaan_anak")
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
        Valid.tabelKosong(tabMode12);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps10 = koneksi.prepareStatement("SELECT pt.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(pt.rencana_tes,'%d-%m-%Y') tglRencana, "
                        + "pt.tes_psikologis, pt.waktu, pt.tester, pt.rencana_tes FROM psikolog_tes_psikologi pt "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pt.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and pt.no_rawat like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and pt.tes_psikologis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and pt.waktu like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and pt.tester like ? "
                        + "order by pt.no_rawat desc");
            } else {
                ps10 = koneksi.prepareStatement("SELECT pt.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(pt.rencana_tes,'%d-%m-%Y') tglRencana, "
                        + "pt.tes_psikologis, pt.waktu, pt.tester, pt.rencana_tes FROM psikolog_tes_psikologi pt "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pt.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and pt.no_rawat like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and pt.tes_psikologis like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and pt.waktu like ? or "
                        + "pt.no_rawat LIKE '%" + TNoRW.getText() + "%' and pt.rekam_psikologis='anak_remaja' and pt.tester like ? "
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
                    tabMode12.addRow(new Object[]{
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
        LCount.setText("" + tabMode12.getRowCount());
    }
    
    private void tampilObservasi() {
        Valid.tabelKosong(tabMode11);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps11 = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien, po.penampilan, po.ekspresi_wajah, po.perasaan_suasana_hati, "
                        + "po.tingkah_laku, po.fungsi_umum, po.fungsi_intelektual, po.lainlain FROM psikolog_observasi po "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.no_rawat like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "                                
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.penampilan like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.ekspresi_wajah like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.perasaan_suasana_hati like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.tingkah_laku like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.fungsi_umum like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.fungsi_intelektual like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.lainlain like ? "
                        + "order by po.no_rawat desc");
            } else {
                ps11 = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien, po.penampilan, po.ekspresi_wajah, po.perasaan_suasana_hati, "
                        + "po.tingkah_laku, po.fungsi_umum, po.fungsi_intelektual, po.lainlain FROM psikolog_observasi po "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.no_rawat like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "                                
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.penampilan like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.ekspresi_wajah like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.perasaan_suasana_hati like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.tingkah_laku like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.fungsi_umum like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.fungsi_intelektual like ? or "
                        + "po.no_rawat LIKE '%" + TNoRW.getText() + "%' and po.rekam_psikologis='anak_remaja' and po.lainlain like ? "
                        + "order by po.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps11.setString(1, "%" + TCari.getText().trim() + "%");
                ps11.setString(2, "%" + TCari.getText().trim() + "%");
                ps11.setString(3, "%" + TCari.getText().trim() + "%");
                ps11.setString(4, "%" + TCari.getText().trim() + "%");                
                ps11.setString(5, "%" + TCari.getText().trim() + "%");
                ps11.setString(6, "%" + TCari.getText().trim() + "%");
                ps11.setString(7, "%" + TCari.getText().trim() + "%");
                ps11.setString(8, "%" + TCari.getText().trim() + "%");                
                ps11.setString(9, "%" + TCari.getText().trim() + "%");
                ps11.setString(10, "%" + TCari.getText().trim() + "%");
                rs11 = ps11.executeQuery();
                while (rs11.next()) {
                    tabMode11.addRow(new Object[]{
                        rs11.getString("no_rawat"),
                        rs11.getString("no_rkm_medis"),
                        rs11.getString("nm_pasien"),                        
                        rs11.getString("penampilan"),                        
                        rs11.getString("ekspresi_wajah"),
                        rs11.getString("perasaan_suasana_hati"),
                        rs11.getString("tingkah_laku"),
                        rs11.getString("fungsi_umum"),
                        rs11.getString("fungsi_intelektual"),
                        rs11.getString("lainlain")
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
    
    private void tampilManifestasiFungsi() {
        Valid.tabelKosong(tabMode14);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps14 = koneksi.prepareStatement("SELECT pm.no_rawat, p.no_rkm_medis, p.nm_pasien, pm.manifestasi FROM psikolog_manifestasi_fungsi pm "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pm.no_rawat WHERE "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pm.no_rawat like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pm.manifestasi like ? "
                        + "order by pm.no_rawat desc");
            } else {
                ps14 = koneksi.prepareStatement("SELECT pm.no_rawat, p.no_rkm_medis, p.nm_pasien, pm.manifestasi FROM psikolog_manifestasi_fungsi pm "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pm.no_rawat WHERE "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pm.no_rawat like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pm.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pm.manifestasi like ? "
                        + "order by pm.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps14.setString(1, "%" + TCari.getText().trim() + "%");
                ps14.setString(2, "%" + TCari.getText().trim() + "%");
                ps14.setString(3, "%" + TCari.getText().trim() + "%");
                ps14.setString(4, "%" + TCari.getText().trim() + "%");
                rs14 = ps14.executeQuery();
                while (rs14.next()) {
                    tabMode14.addRow(new Object[]{
                        rs14.getString("no_rawat"),
                        rs14.getString("no_rkm_medis"),
                        rs14.getString("nm_pasien"),                        
                        rs14.getString("manifestasi")
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
        LCount.setText("" + tabMode14.getRowCount());
    }
    
    private void tampilDiagnosis() {
        Valid.tabelKosong(tabMode15);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps12 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.kesan_awal, pd.diagnosa_utama, "
                        + "pd.diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat WHERE "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and pd.no_rawat like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and pd.kesan_awal like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and pd.diagnosa_utama like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and pd.diagnosa_banding like ? "
                        + "order by pd.no_rawat desc");
            } else {
                ps12 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.kesan_awal, pd.diagnosa_utama, "
                        + "pd.diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat WHERE "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and pd.no_rawat like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and pd.kesan_awal like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and pd.diagnosa_utama like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja' and pd.diagnosa_banding like ? "
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
                    tabMode15.addRow(new Object[]{
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
        LCount.setText("" + tabMode15.getRowCount());
    }
    
    private void tampilPrognosis() {
        Valid.tabelKosong(tabMode17);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps17 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.prognosis FROM psikolog_prognosis pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pp.no_rawat WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pp.prognosis like ? "
                        + "order by pp.no_rawat desc");
            } else {
                ps17 = koneksi.prepareStatement("SELECT pp.no_rawat, p.no_rkm_medis, p.nm_pasien, pp.prognosis FROM psikolog_prognosis pp "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pp.no_rawat WHERE "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pp.no_rawat like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pp.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pp.prognosis like ? "
                        + "order by pp.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps17.setString(1, "%" + TCari.getText().trim() + "%");
                ps17.setString(2, "%" + TCari.getText().trim() + "%");
                ps17.setString(3, "%" + TCari.getText().trim() + "%");
                ps17.setString(4, "%" + TCari.getText().trim() + "%");
                rs17 = ps17.executeQuery();
                while (rs17.next()) {
                    tabMode17.addRow(new Object[]{
                        rs17.getString("no_rawat"),
                        rs17.getString("no_rkm_medis"),
                        rs17.getString("nm_pasien"),                        
                        rs17.getString("prognosis")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs17 != null) {
                    rs17.close();
                }
                if (ps17 != null) {
                    ps17.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode17.getRowCount());
    }
    
    private void tampilItemTritmen() {
        Valid.tabelKosong(tabMode18);
        try {
            ps19 = koneksi.prepareStatement("SELECT pd.kd_rencana, pm.deskripsi, pd.ket_lainnya, "
                    + "pd.waktu_simpan FROM psikolog_detail_rencana_tritmen pd "
                    + "INNER JOIN psikolog_master_rencana_tritmen pm ON pm.kd_rencana=pd.kd_rencana "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                    + "INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat "
                    + "WHERE pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja'");
            try {
                rs19 = ps19.executeQuery();
                while (rs19.next()) {
                    tabMode18.addRow(new Object[]{
                        rs19.getString("kd_rencana"),
                        rs19.getString("deskripsi"), 
                        rs19.getString("ket_lainnya"), 
                        rs19.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs19 != null) {
                    rs19.close();
                }
                if (ps19 != null) {
                    ps19.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRencanaTritmen() {
        Valid.tabelKosong(tabMode19);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps18 = koneksi.prepareStatement("SELECT pr.no_rawat, p.no_rkm_medis, p.nm_pasien, pr.tritmen, pr.pertemuan_selanjutnya, "
                        + "pr.cttn_tambahan, pr.waktu_simpan FROM psikolog_rencana_tritmen pr "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pr.no_rawat WHERE "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pr.no_rawat like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pr.tritmen like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pr.pertemuan_selanjutnya like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pr.cttn_tambahan like ? "
                        + "order by pr.no_rawat desc");
            } else {
                ps18 = koneksi.prepareStatement("SELECT pr.no_rawat, p.no_rkm_medis, p.nm_pasien, pr.tritmen, pr.pertemuan_selanjutnya, "
                        + "pr.cttn_tambahan, pr.waktu_simpan FROM psikolog_rencana_tritmen pr "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pr.no_rawat WHERE "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pr.no_rawat like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.no_rkm_medis like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and p.nm_pasien like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pr.tritmen like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pr.pertemuan_selanjutnya like ? or "
                        + "pr.no_rawat LIKE '%" + TNoRW.getText() + "%' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja' and pr.cttn_tambahan like ? "
                        + "order by pr.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }

            try {
                ps18.setString(1, "%" + TCari.getText().trim() + "%");
                ps18.setString(2, "%" + TCari.getText().trim() + "%");
                ps18.setString(3, "%" + TCari.getText().trim() + "%");
                ps18.setString(4, "%" + TCari.getText().trim() + "%");
                ps18.setString(5, "%" + TCari.getText().trim() + "%");
                ps18.setString(6, "%" + TCari.getText().trim() + "%");
                rs18 = ps18.executeQuery();
                while (rs18.next()) {
                    tabMode19.addRow(new Object[]{
                        rs18.getString("no_rawat"),
                        rs18.getString("no_rkm_medis"),
                        rs18.getString("nm_pasien"),                        
                        rs18.getString("tritmen"),
                        rs18.getString("pertemuan_selanjutnya"),
                        rs18.getString("cttn_tambahan"),
                        rs18.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs18 != null) {
                    rs18.close();
                }
                if (ps18 != null) {
                    ps18.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode19.getRowCount());
    }
    
    private void tampilDinamikaPsikologi() {
        Valid.tabelKosong(tabMode13);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps13 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.dinamika_psikologi FROM psikolog_anak_remaja_dinamika pd "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and pd.no_rawat like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and pd.dinamika_psikologi like ? "
                        + "order by pd.no_rawat desc");
            } else {
                ps13 = koneksi.prepareStatement("SELECT pd.no_rawat, p.no_rkm_medis, p.nm_pasien, pd.dinamika_psikologi FROM psikolog_anak_remaja_dinamika pd "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and pd.no_rawat like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.no_rkm_medis like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and p.nm_pasien like ? or "
                        + "pd.no_rawat LIKE '%" + TNoRW.getText() + "%' and pd.dinamika_psikologi like ? "
                        + "order by pd.no_rawat desc limit " + cmbLimit.getSelectedItem().toString() + "");
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
                        rs13.getString("dinamika_psikologi")
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
    
    public void emptText() {
        TNoRW.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        tgl_datang.setText("");
        Tjk.setText("");
        emptTextDD();
        emptTextIS();
        emptTextIOT();
        emptTextKP();
        emptTextPPK();
        emptTextRK();
        emptTextRP();
        emptTextTPer();
        emptTextPS();
        emptTextO();
        emptTextTP();
        emptTextDP();
        emptTextD();
        emptTextMF();
        emptTextP();
        emptTextRT();
    }
    
    private void emptTextDD() {
        Ttl.setText("");
        TAgama.setText("");
        TPendidikan.setText("");
        TUrutanLahir.setText("");
        TAlamat.setText("");
    }
    
    private void emptTextIS() {
        TnmSau.setText("");
        TnmSau.requestFocus();
        TumurSau.setText("");
        cmbUmur.setSelectedIndex(0);
        cmbJK.setSelectedIndex(0);
        TposisiSau.setText("");
        TketSau.setText("");
    }
    
    private void emptTextIOT() {
        TnmAyah.setText("");
        TnmAyah.requestFocus();
        TumurAyah.setText("");
        cmbPndAyah.setSelectedIndex(0);
        TpekerjaanAyah.setText("");
        cmbAgamaAyah.setSelectedIndex(0);        
        TanakKeAyah.setText("");
        TkawinKeAyah.setText("");
        TalamatAyah.setText("");
        ChkcopasAyah.setSelected(false);
        TnoHpAyah.setText("");
        TKetAyah.setText("");
        
        TnmIbu.setText("");
        TumurIbu.setText("");
        cmbPndIbu.setSelectedIndex(0);
        TpekerjaanIbu.setText("");
        cmbAgamaIbu.setSelectedIndex(0);        
        TanakKeIbu.setText("");
        TkawinKeIbu.setText("");
        TalamatIbu.setText("");
        ChkcopasIbu.setSelected(false);
        TnoHpIbu.setText("");
        TKetIbu.setText("");
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
        TPerubahanAnak.setText("");
        TPerubahanDiriSendiri.setText("");
        TPerubahanKeluarga.setText("");
    }

    private void emptTextRK() {
        TDemam.setText("");
        TDemam.requestFocus();
        TPneum.setText("");
        TFlu.setText("");
        TEnse.setText("");
        TMeni.setText("");
        TKejang.setText("");
        TTidak.setText("");
        TGeger.setText("");
        TPingsan.setText("");
        TPusing.setText("");
        TAmandel.setText("");
        TLihat.setText("");
        TDengar.setText("");
        
        TGigi.setText("");
        TBerat.setText("");
        TAlergi.setText("");
        TKulit.setText("");
        TAsma.setText("");
        TKepala.setText("");
        TPerut.setText("");
        TRawan.setText("");
        TAnemia.setText("");
        TDarah.setText("");
        TSinus.setText("");
        TJantung.setText("");
        THiper.setText("");
        
        TPenyakit.setText("");
        TRumahSakit.setText("");
    }

    private void emptTextRP() {
        cmbDinginkan.setSelectedIndex(0);
        cmbDinginkan.requestFocus();
        cmbDirencanakan.setSelectedIndex(0);
        cmbProses.setSelectedIndex(0);
        TIbuTertekan.setText("");
        Tdukungan.setText("");
        TLamaProses.setText("");
        TPrematur.setText("");
        TTerlambat.setText("");
        TProsesLahir.setText("");
    }
    
    private void emptTextTPer() {
        Tduduk.setText("");
        Tduduk.requestFocus();
        Tmerangkak.setText("");
        Tberjalan.setText("");
        Tbicara1.setText("");
        TbicaraDengan.setText("");
        TlatihanBAK.setText("");
        TlatihanBAB.setText("");
        Trelasi.setText("");
        Tkebiasaan.setText("");
    }
    
    private void emptTextPS() {
        emptTextRS();
        TjnsSekolah.setText("");
        Tprogram.setText("");
        TtglKelas.setText("");
        cmbBelajar.setSelectedIndex(0);
        cmbBersemangat.setSelectedIndex(0);
        cmbPernah.setSelectedIndex(0);
        Tprestasi.setText("");
        Tmata.setText("");
        Tsulit.setText("");
        Tminat.setText("");
    }
    
    private void emptTextO() {
        Tpenampilan.setText("");
        Tpenampilan.requestFocus();
        Tekspresi.setText("");
        Tperasaan.setText("");
        Ttingkah.setText("");
        Tumum.setText("");
        Tintelektual.setText("");
        Tlain.setText("");
    }
    
    private void emptTextTP() {        
        tgl_rencana.setDate(new Date());
        tgl_rencana.requestFocus();
        TTesPsiko.setText("");
        TWaktu.setText("");
        TTester.setText("");
    }
    
    private void emptTextDP() {
        Tdinamika.setText("");
        Tdinamika.requestFocus();
    }
    
    private void emptTextD() {
        TKesan.setText("");
        TKesan.requestFocus();
        TDiagUtama.setText("");
        TDiagBanding.setText("");
    }
    
    private void emptTextMF() {
        TManifes.setText("Kognitif : \n\nSosial dan Emosi : \n\nBahasa dan Bicara : \n\nMotorik : \n\nKecenderungan Perilaku : \n");
        TManifes.requestFocus();
    }
    
    private void emptTextP() {
        Tprognosis.setText("Faktor Penghambat : \n\nFaktor Pendukung : \n");
        Tprognosis.requestFocus();
    }
    
    private void emptTextRT() {
        Valid.tabelKosong(tabMode18);
        BtnPilihTritmen.requestFocus();
        wktSimpan = "";
        TPertemuan.setText("");
        TCatatan.setText("");
    }
    
    private void emptTextRS() {
        cmbSekolah.setSelectedIndex(0);
        cmbSekolah.requestFocus();
        Tkota.setText("");
        TthnMsk.setText("");
        TthnSelesai.setText("");
        TlamaSekolah.setText("");
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
            TAgama.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 7).toString());
            TPendidikan.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 8).toString());            
            TUrutanLahir.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 9).toString());
            TAlamat.setText(tbdatadiri.getValueAt(tbdatadiri.getSelectedRow(), 10).toString());
        }
    }
    
    private void getDataIS() {
        wktSimpan = "";
        if (tbIdentitasSaudara.getSelectedRow() != -1) {            
            wktSimpan = tbIdentitasSaudara.getValueAt(tbIdentitasSaudara.getSelectedRow(), 7).toString();
            TnmSau.setText(tbIdentitasSaudara.getValueAt(tbIdentitasSaudara.getSelectedRow(), 2).toString());
            TumurSau.setText(tbIdentitasSaudara.getValueAt(tbIdentitasSaudara.getSelectedRow(), 8).toString());
            cmbUmur.setSelectedItem(tbIdentitasSaudara.getValueAt(tbIdentitasSaudara.getSelectedRow(), 9).toString());
            cmbJK.setSelectedItem(tbIdentitasSaudara.getValueAt(tbIdentitasSaudara.getSelectedRow(), 4).toString());
            TposisiSau.setText(tbIdentitasSaudara.getValueAt(tbIdentitasSaudara.getSelectedRow(), 5).toString());
            TketSau.setText(tbIdentitasSaudara.getValueAt(tbIdentitasSaudara.getSelectedRow(), 6).toString());
        }
    }
    
    private void getDataIOT() {
        wktSimpan = "";
        if (tbIdentitasOrtu.getSelectedRow() != -1) {            
            wktSimpan = tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 22).toString();
            TnmAyah.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 2).toString());
            TumurAyah.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 23).toString());
            cmbPndAyah.setSelectedItem(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 4).toString());
            TpekerjaanAyah.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 5).toString());
            cmbAgamaAyah.setSelectedItem(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 6).toString());
            TanakKeAyah.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 7).toString());
            TkawinKeAyah.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 8).toString());
            TalamatAyah.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 9).toString());
            TnoHpAyah.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 10).toString());
            TKetAyah.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 11).toString());
            
            TnmIbu.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 12).toString());
            TumurIbu.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 24).toString());
            cmbPndIbu.setSelectedItem(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 14).toString());
            TpekerjaanIbu.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 15).toString());
            cmbAgamaIbu.setSelectedItem(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 16).toString());
            TanakKeIbu.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 17).toString());
            TkawinKeIbu.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 18).toString());
            TalamatIbu.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 19).toString());
            TnoHpIbu.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 20).toString());
            TKetIbu.setText(tbIdentitasOrtu.getValueAt(tbIdentitasOrtu.getSelectedRow(), 21).toString());
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
            TPerubahanAnak.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 8).toString());
            TPerubahanDiriSendiri.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 9).toString());
            TPerubahanKeluarga.setText(tbPsikologiKlinis.getValueAt(tbPsikologiKlinis.getSelectedRow(), 10).toString());
            
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
    
    private void getDataRiwayatKesehatan() {
        wktSimpan = "";
        if (tbRiwayatKesehatan.getSelectedRow() != -1) {            
            wktSimpan = tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 0).toString();
            TDemam.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 3).toString());
            TPneum.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 4).toString());
            TFlu.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 5).toString());
            TEnse.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 6).toString());
            TMeni.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 7).toString());            
            TKejang.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 8).toString());
            TTidak.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 9).toString());
            TGeger.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 10).toString());
            TPingsan.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 11).toString());
            TPusing.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 12).toString());
            TAmandel.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 13).toString());
            TLihat.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 14).toString());
            TDengar.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 15).toString());
            TGigi.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 16).toString());
            TBerat.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 17).toString());
            TAlergi.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 18).toString());
            TKulit.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 19).toString());
            TAsma.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 20).toString());
            TKepala.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 21).toString());
            TPerut.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 22).toString());
            TRawan.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 23).toString());
            TAnemia.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 24).toString());
            TDarah.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 25).toString());
            TSinus.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 26).toString());
            TJantung.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 27).toString());
            THiper.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 28).toString());
            TPenyakit.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 29).toString());
            TRumahSakit.setText(tbRiwayatKesehatan.getValueAt(tbRiwayatKesehatan.getSelectedRow(), 30).toString());
        }
    }
    
    private void getDataRiwayatPerkembangan() {
        wktSimpan = "";
        if (tbRiwayatPerkembangan.getSelectedRow() != -1) {            
            wktSimpan = tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 0).toString();
            cmbDinginkan.setSelectedItem(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 3).toString());
            cmbDirencanakan.setSelectedItem(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 4).toString());
            cmbProses.setSelectedItem(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 5).toString());
            TIbuTertekan.setText(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 6).toString());
            Tdukungan.setText(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 7).toString());
            TLamaProses.setText(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 8).toString());
            TPrematur.setText(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 9).toString());
            TTerlambat.setText(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 10).toString());
            TProsesLahir.setText(tbRiwayatPerkembangan.getValueAt(tbRiwayatPerkembangan.getSelectedRow(), 11).toString());
        }
    }
    
    private void getDataTahapPerkembangan() {
        wktSimpan = "";
        if (tbTahapPerkembangan.getSelectedRow() != -1) {            
            wktSimpan = tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 0).toString();
            Tduduk.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 3).toString());            
            Tmerangkak.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 4).toString());
            Tberjalan.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 5).toString());
            Tbicara1.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 6).toString());
            TbicaraDengan.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 7).toString());
            TlatihanBAK.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 8).toString());
            TlatihanBAB.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 9).toString());
            Trelasi.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 10).toString());            
            Tkebiasaan.setText(tbTahapPerkembangan.getValueAt(tbTahapPerkembangan.getSelectedRow(), 11).toString());
        }
    }
    
    private void getDataRiwayatSekolah() {
        wktSimpan = "";
        if (tbRiwayatSekolah.getSelectedRow() != -1) {            
            cmbSekolah.setSelectedItem(tbRiwayatSekolah.getValueAt(tbRiwayatSekolah.getSelectedRow(), 3).toString());
            Tkota.setText(tbRiwayatSekolah.getValueAt(tbRiwayatSekolah.getSelectedRow(), 4).toString());            
            TthnMsk.setText(tbRiwayatSekolah.getValueAt(tbRiwayatSekolah.getSelectedRow(), 5).toString());
            TthnSelesai.setText(tbRiwayatSekolah.getValueAt(tbRiwayatSekolah.getSelectedRow(), 6).toString());
            TlamaSekolah.setText(tbRiwayatSekolah.getValueAt(tbRiwayatSekolah.getSelectedRow(), 7).toString());
            wktSimpan = tbRiwayatSekolah.getValueAt(tbRiwayatSekolah.getSelectedRow(), 8).toString();
        }
    }
    
    private void getDataDisekolah() {
        if (tbDisekolah.getSelectedRow() != -1) {   
            TNoRW.setText(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 0).toString());
            TjnsSekolah.setText(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 3).toString());
            Tprogram.setText(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 4).toString());
            TtglKelas.setText(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 5).toString());
            cmbBelajar.setSelectedItem(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 6).toString());
            cmbBersemangat.setSelectedItem(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 7).toString());
            cmbPernah.setSelectedItem(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 8).toString());
            Tprestasi.setText(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 9).toString());
            Tmata.setText(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 10).toString());
            Tsulit.setText(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 11).toString());
            Tminat.setText(tbDisekolah.getValueAt(tbDisekolah.getSelectedRow(), 12).toString());
        }
    }
    
    private void getDataObservasi() {
        wktSimpan = "";
        if (tbObservasi.getSelectedRow() != -1) {            
            wktSimpan = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 0).toString();            
            Tpenampilan.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 3).toString());            
            Tekspresi.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 4).toString());
            Tperasaan.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 5).toString());
            Ttingkah.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 6).toString());
            Tumum.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 7).toString());
            Tintelektual.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 8).toString());
            Tlain.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 9).toString());
        }
    }
    
    private void getDataTesPsiko() {
        wktSimpan = "";
        if (tbTesPsikolog.getSelectedRow() != -1) {            
            wktSimpan = tbTesPsikolog.getValueAt(tbTesPsikolog.getSelectedRow(), 0).toString();            
            TTesPsiko.setText(tbTesPsikolog.getValueAt(tbTesPsikolog.getSelectedRow(), 4).toString());
            TWaktu.setText(tbTesPsikolog.getValueAt(tbTesPsikolog.getSelectedRow(), 5).toString());
            TTester.setText(tbTesPsikolog.getValueAt(tbTesPsikolog.getSelectedRow(), 6).toString());
            Valid.SetTgl(tgl_rencana, tbTesPsikolog.getValueAt(tbTesPsikolog.getSelectedRow(), 7).toString());
        }
    }
    
    private void getDataDinamikaPsikologi() {
        wktSimpan = "";
        if (tbDinamika.getSelectedRow() != -1) {            
            wktSimpan = tbDinamika.getValueAt(tbDinamika.getSelectedRow(), 0).toString();            
            Tdinamika.setText(tbDinamika.getValueAt(tbDinamika.getSelectedRow(), 3).toString());
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
    
    private void getDataManifes() {
        wktSimpan = "";
        if (tbManifestasi.getSelectedRow() != -1) {            
            wktSimpan = tbManifestasi.getValueAt(tbManifestasi.getSelectedRow(), 0).toString();            
            TManifes.setText(tbManifestasi.getValueAt(tbManifestasi.getSelectedRow(), 3).toString());
        }
    }
    
    private void getDataPrognosis() {
        wktSimpan = "";
        if (tbPrognosis.getSelectedRow() != -1) {            
            wktSimpan = tbPrognosis.getValueAt(tbPrognosis.getSelectedRow(), 0).toString();            
            Tprognosis.setText(tbPrognosis.getValueAt(tbPrognosis.getSelectedRow(), 3).toString());
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
        } else if (TUrutanLahir.getText().equals("")) {
            Valid.textKosong(TUrutanLahir, "Urutan Kelahiran");
            TUrutanLahir.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_data_diri_klien", "'" + TNoRW.getText() + "',"
                    + "'" + tglKedatangan + "','" + TUrutanLahir.getText() + "','-','-','anak_remaja'", "Data Diri Pasien/Klien");
            tampilDataDiri();
            emptTextDD();
        }
    }
    
    private void simpanIS() {
        if (TnmSau.getText().trim().equals("")) {
            Valid.textKosong(TnmSau, "Nama saudara");
            TnmSau.requestFocus();
        } else if (TumurSau.getText().trim().equals("")) {
            Valid.textKosong(TumurSau, "Umur saudara");
            TumurSau.requestFocus();
        } else if (cmbJK.getSelectedIndex() == 0) {
            Valid.textKosong(cmbJK, "Jenis kelamin saudara");
            cmbJK.requestFocus();
        } else if (TposisiSau.getText().trim().equals("")) {
            Valid.textKosong(TposisiSau, "Posisi saudara");
            TposisiSau.requestFocus();
        } else if (TketSau.getText().trim().equals("")) {
            Valid.textKosong(TketSau, "Keterangan saudara");
            TketSau.requestFocus();
        } else {
            jkel = "";
            if (cmbJK.getSelectedIndex() == 1) {
                jkel = "L";
            } else if (cmbJK.getSelectedIndex() == 2) {
                jkel = "P";
            }
            
            Sequel.menyimpan("psikolog_anggota_keluarga", 
                    "'" + TNoRM.getText() + "','" + TnmSau.getText() + "','" + TumurSau.getText() + "',"
                    + "'" + cmbUmur.getSelectedItem().toString() + "','" + jkel + "','" + TposisiSau.getText() + "',"
                    + "'" + TketSau.getText() + "','saudara','" + Sequel.cariIsi("select now()") + "',"
                    + "'anak_remaja'", "Identitas Saudara");
            tampilIdentitasSaudara();
            emptTextIS();
        }
    }
    
    private void simpanIOT() {
        if (TnmAyah.getText().trim().equals("")) {
            Valid.textKosong(TnmAyah, "Nama ayah");
            TnmAyah.requestFocus();
        } else if (TumurAyah.getText().trim().equals("")) {
            Valid.textKosong(TumurAyah, "Umur ayah");
            TumurAyah.requestFocus();
        } else if (TpekerjaanAyah.getText().trim().equals("")) {
            Valid.textKosong(TpekerjaanAyah, "pekerjaan ayah");
            TpekerjaanAyah.requestFocus();
        } else if (TanakKeAyah.getText().trim().equals("")) {
            Valid.textKosong(TanakKeAyah, "anak ke ayah");
            TanakKeAyah.requestFocus();
        } else if (TkawinKeAyah.getText().trim().equals("")) {
            Valid.textKosong(TkawinKeAyah, "perkawinan ke ayah");
            TkawinKeAyah.requestFocus();
        } else if (TalamatAyah.getText().trim().equals("")) {
            Valid.textKosong(TalamatAyah, "alamat ayah");
            TalamatAyah.requestFocus();
        } else if (TnoHpAyah.getText().trim().equals("")) {
            Valid.textKosong(TnoHpAyah, "no. hp/telp. ayah");
            TnoHpAyah.requestFocus();
        } else if (TKetAyah.getText().trim().equals("")) {
            Valid.textKosong(TKetAyah, "keterangan ayah");
            TKetAyah.requestFocus();
        } else {            
            if (Sequel.menyimpantf("psikolog_anak_remaja_identitas_ortu", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Identitas Orang Tua", 22, new String[]{
                TNoRM.getText(), TnmAyah.getText(), TnmIbu.getText(), TumurAyah.getText(), TumurIbu.getText(), cmbPndAyah.getSelectedItem().toString(),
                cmbPndIbu.getSelectedItem().toString(), TpekerjaanAyah.getText(), TpekerjaanIbu.getText(), cmbAgamaAyah.getSelectedItem().toString(), 
                cmbAgamaIbu.getSelectedItem().toString(), TanakKeAyah.getText(), TanakKeIbu.getText(), TkawinKeAyah.getText(), TkawinKeIbu.getText(), 
                TalamatAyah.getText(), TalamatIbu.getText(), TnoHpAyah.getText(), TnoHpIbu.getText(), TKetAyah.getText(), TKetIbu.getText(), Sequel.cariIsi("select now()")
            }) == true) {
                tampilIdentitasORTU();
                emptTextIOT();
            }         
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
                    "'" + TNoRW.getText() + "','Anak/Remaja','" + TPermasalahansaatIni.getText() + "',"
                    + "'" + TAlasanMencariBantuan.getText() + "','" + nilaiMasalah + "','" + TAlasan.getText() + "',"
                    + "'" + THarapan.getText() + "','" + TPerubahanAnak.getText() + "','" + TPerubahanDiriSendiri.getText() + "',"
                    + "'" + TPerubahanKeluarga.getText() + "'", "Pemeriksaan Psikologis Klinis Pasien/Klien");
            tampilPsikologiKlinis();
            emptTextPPK();
        }
    }
    
    private void simpanRK() {
        Sequel.menyimpan("psikolog_anak_remaja_riw_kes",
                "'" + TNoRW.getText() + "','" + TDemam.getText() + "','" + TPneum.getText() + "','" + TFlu.getText() + "',"
                + "'" + TEnse.getText() + "','" + TMeni.getText() + "','" + TKejang.getText() + "','" + TTidak.getText() + "',"
                + "'" + TGeger.getText() + "','" + TPingsan.getText() + "','" + TPusing.getText() + "','" + TAmandel.getText() + "',"
                + "'" + TLihat.getText() + "','" + TDengar.getText() + "','" + TGigi.getText() + "','" + TBerat.getText() + "',"
                + "'" + TAlergi.getText() + "','" + TKulit.getText() + "','" + TAsma.getText() + "','" + TKepala.getText() + "',"
                + "'" + TPerut.getText() + "','" + TRawan.getText() + "','" + TAnemia.getText() + "','" + TDarah.getText() + "',"
                + "'" + TSinus.getText() + "','" + TJantung.getText() + "','" + THiper.getText() + "','" + TPenyakit.getText() + "',"
                + "'" + TRumahSakit.getText() + "'", "Riwayat Kesehatan Pasien/Klien");
        tampilRiwayatKesehatan();
        emptTextRK();
    }
    
    private void simpanRP() {
        if (cmbDinginkan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dg. benar masa kehamilan yg. diinginkan...!!!!");
            cmbDinginkan.requestFocus();
        } else if (cmbDirencanakan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dg. benar masa kehamilan yg. direncanakan...!!!!");
            cmbDirencanakan.requestFocus();
        } else if (cmbProses.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dg. benar proses kehamilan normal...!!!!");
            cmbProses.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_anak_remaja_riw_perkembangan",
                    "'" + TNoRW.getText() + "','" + cmbDinginkan.getSelectedItem().toString() + "','" + cmbDirencanakan.getSelectedItem().toString() + "',"
                    + "'" + cmbProses.getSelectedItem().toString() + "','" + TIbuTertekan.getText() + "','" + Tdukungan.getText() + "',"
                    + "'" + TLamaProses.getText() + "','" + TPrematur.getText() + "','" + TTerlambat.getText() + "','" + TProsesLahir.getText() + "'", "Riwayat Perkembangan");
            tampilRiwayatPerkembangan();
            emptTextRP();
        }
    }
    
    private void simpanTPer() {
        Sequel.menyimpan("psikolog_anak_remaja_thp_perkembangan",
                "'" + TNoRW.getText() + "','" + Tduduk.getText() + "','" + Tmerangkak.getText() + "',"
                + "'" + Tberjalan.getText() + "','" + Tbicara1.getText() + "','" + TbicaraDengan.getText() + "',"
                + "'" + TlatihanBAK.getText() + "','" + TlatihanBAB.getText() + "','" + Trelasi.getText() + "',"
                + "'" + Tkebiasaan.getText() + "'", "Tahap Perkembangan Pasien/Klien");
        tampilTahapPerkembangan();
        emptTextTPer();
    }
    
    private void simpanPS() {
        if (tabMode10.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan isi dulu data riwayat sekolahnya...!!!!");
            emptTextRS();
        } else if (TjnsSekolah.getText().equals("")) {
            Valid.textKosong(TjnsSekolah, "Jenis Sekolah");
            TjnsSekolah.requestFocus();
        } else {
            Sequel.menyimpan("psikolog_anak_remaja_disekolah",
                    "'" + TNoRW.getText() + "','" + TjnsSekolah.getText() + "','" + Tprogram.getText() + "',"
                    + "'" + TtglKelas.getText() + "','" + cmbBelajar.getSelectedItem().toString() + "',"
                    + "'" + cmbBersemangat.getSelectedItem().toString() + "','" + cmbPernah.getSelectedItem().toString() + "',"
                    + "'" + Tprestasi.getText() + "','" + Tmata.getText() + "','" + Tsulit.getText() + "','" + Tminat.getText() + "'", "Data Disekolah Pasien/Klien");
            
            tampilDisekolah();
            tampilRiwayatSekolah();            
            emptTextPS();
        }
    }
    
    private void simpanO() {
        Sequel.menyimpan("psikolog_observasi",
                "'" + TNoRW.getText() + "','anak_remaja','" + Tpenampilan.getText() + "','" + Tekspresi.getText() + "',"
                + "'" + Tperasaan.getText() + "','" + Ttingkah.getText() + "','" + Tumum.getText() + "',"
                + "'" + Tintelektual.getText() + "','-','" + Tlain.getText() + "'", "Observasi Kondisi Psikologis Pasien/Klien");
        tampilObservasi();
        emptTextO();
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
                    "'" + TNoRW.getText() + "','anak_remaja','" + Valid.SetTgl(tgl_rencana.getSelectedItem() + "") + "',"
                    + "'" + TTesPsiko.getText() + "','" + TWaktu.getText() + "','" + TTester.getText() + "'", "Tes Psikologi Pasien/Klien");
            tampilTesPsikologi();
            emptTextTP();
        }
    }
    
    private void simpanDP() {
        Sequel.menyimpan("psikolog_anak_remaja_dinamika",
                "'" + TNoRW.getText() + "','" + Tdinamika.getText() + "'", "Dinamika Psikologi Pasien/Klien");
        tampilDinamikaPsikologi();
        emptTextDP();
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
    
    private void simpanMF() {
        Sequel.menyimpan("psikolog_manifestasi_fungsi",
                "'" + TNoRW.getText() + "','" + TManifes.getText() + "'", "Manifestasi Fungsi Psikologis Pasien/Klien");
        tampilManifestasiFungsi();
        emptTextMF();
    }
    
    private void simpanP() {
        Sequel.menyimpan("psikolog_prognosis",
                "'" + TNoRW.getText() + "','" + Tprognosis.getText() + "'", "Prognosis Pasien/Klien");
        tampilPrognosis();
        emptTextP();
    }
    
    private void simpanRT() {
        if (tabMode18.getRowCount() == 0) {
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
    
    private void hapusIS() {
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas saudara pada tabel...!!!!");
            tbIdentitasSaudara.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anggota_keluarga where waktu_simpan='" + wktSimpan + "'");
            tampilIdentitasSaudara();
            emptTextIS();
            wktSimpan = "";
        }
    }
    
    private void hapusIOT() {
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas orang tua pada tabel...!!!!");
            tbIdentitasOrtu.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anak_remaja_identitas_ortu where waktu_simpan='" + wktSimpan + "'");
            tampilIdentitasORTU();
            emptTextIOT();
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
    
    private void hapusRK() {
        if (tabMode7.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat kesehatan pada tabel...!!!!");
            tbRiwayatKesehatan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anak_remaja_riw_kes where no_rawat='" + wktSimpan + "'");
            
            tampilRiwayatKesehatan();
            emptTextRK();
            wktSimpan = "";
        }
    }
    
    private void hapusRP() {
        if (tabMode8.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat perkembangan pada tabel...!!!!");
            tbRiwayatPerkembangan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anak_remaja_riw_perkembangan where no_rawat='" + wktSimpan + "'");
            
            tampilRiwayatPerkembangan();
            emptTextRP();
            wktSimpan = "";
        }
    }
    
    private void hapusTPer() {
        if (tabMode9.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data kondisi psikopatologis pasien pada tabel...!!!!");
            tbTahapPerkembangan.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anak_remaja_thp_perkembangan where no_rawat='" + wktSimpan + "'");
            
            tampilTahapPerkembangan();
            emptTextTPer();
            wktSimpan = "";
        }
    }
    
    private void hapusPS() {
        if (tabMode16.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data disekolah pasien pada tabel...!!!!");
            tbDisekolah.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anak_remaja_disekolah where no_rawat='" + TNoRW.getText() + "'");
            tampilDisekolah();
            tampilRiwayatSekolah();
            emptTextPS();
            wktSimpan = "";
        }
    }
    
    private void hapusO() {
        if (tabMode11.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data observasi kondisi psikologis pasien pada tabel...!!!!");
            tbObservasi.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_observasi where no_rawat='" + wktSimpan + "'");
            
            tampilObservasi();
            emptTextO();
            wktSimpan = "";
        }
    }
    
    private void hapusTP() {
        if (tabMode12.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data tes psikologi pasien pada tabel...!!!!");
            tbTesPsikolog.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_tes_psikologi where no_rawat='" + wktSimpan + "'");
            
            tampilTesPsikologi();
            emptTextTP();
            wktSimpan = "";
        }
    }
    
    private void hapusDP() {
        if (tabMode13.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data dinamika psikologi pasien pada tabel...!!!!");
            tbDinamika.requestFocus();
        } else {
            Sequel.queryu("delete from psikolog_anak_remaja_dinamika where no_rawat='" + wktSimpan + "'");
            
            tampilDinamikaPsikologi();
            emptTextDP();
            wktSimpan = "";
        }
    }
    
    private void hapusD() {
        if (tabMode15.getRowCount() == 0) {
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
    
    private void hapusMF() {
        if (tabMode14.getRowCount() == 0) {
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
    
    private void hapusP() {
        if (tabMode17.getRowCount() == 0) {
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
        if (tabMode19.getRowCount() == 0) {
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
        } else if (TUrutanLahir.getText().equals("")) {
            Valid.textKosong(TUrutanLahir, "Urutan Kelahiran");
            TUrutanLahir.requestFocus();
        } else {
            Sequel.mengedit("psikolog_data_diri_klien", "no_rawat='" + TNoRW.getText() + "'",
                    "tgl_kedatangan='" + tglKedatangan + "',urutan_lahir='" + TUrutanLahir.getText() + "'");
            tampilDataDiri();
            emptTextDD();
        }
    }
    
    private void gantiIS() {
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas saudara pada tabel...!!!!");
            tbIdentitasSaudara.requestFocus();
        } else if (TnmSau.getText().trim().equals("")) {
            Valid.textKosong(TnmSau, "Nama saudara");
            TnmSau.requestFocus();
        } else if (TumurSau.getText().trim().equals("")) {
            Valid.textKosong(TumurSau, "Umur saudara");
            TumurSau.requestFocus();
        } else if (cmbJK.getSelectedIndex() == 0) {
            Valid.textKosong(cmbJK, "Jenis kelamin saudara");
            cmbJK.requestFocus();
        } else if (TposisiSau.getText().trim().equals("")) {
            Valid.textKosong(TposisiSau, "Posisi saudara");
            TposisiSau.requestFocus();
        } else if (TketSau.getText().trim().equals("")) {
            Valid.textKosong(TketSau, "Keterangan saudara");
            TketSau.requestFocus();
        } else {
            jkel = "";
            if (cmbJK.getSelectedIndex() == 1) {
                jkel = "L";
            } else if (cmbJK.getSelectedIndex() == 2) {
                jkel = "P";
            }

            Sequel.mengedit("psikolog_anggota_keluarga", "waktu_simpan='" + wktSimpan + "'",
                    "no_rkm_medis='" + TNoRM.getText() + "',nama='" + TnmSau.getText() + "',umur='" + TumurSau.getText() + "',"
                    + "sttsumur='" + cmbUmur.getSelectedItem().toString() + "',jk='" + jkel + "',"
                    + "posisi='" + TposisiSau.getText() + "',keterangan='" + TketSau.getText() + "'");
            tampilIdentitasSaudara();
            emptTextIS();
        }
    }
    
    private void gantiIOT() {
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data identitas orang tua pada tabel...!!!!");
            tbIdentitasOrtu.requestFocus();
        } else if (TnmAyah.getText().trim().equals("")) {
            Valid.textKosong(TnmAyah, "Nama ayah");
            TnmAyah.requestFocus();
        } else if (TumurAyah.getText().trim().equals("")) {
            Valid.textKosong(TumurAyah, "Umur ayah");
            TumurAyah.requestFocus();
        } else if (TpekerjaanAyah.getText().trim().equals("")) {
            Valid.textKosong(TpekerjaanAyah, "pekerjaan ayah");
            TpekerjaanAyah.requestFocus();
        } else if (TanakKeAyah.getText().trim().equals("")) {
            Valid.textKosong(TanakKeAyah, "anak ke ayah");
            TanakKeAyah.requestFocus();
        } else if (TkawinKeAyah.getText().trim().equals("")) {
            Valid.textKosong(TkawinKeAyah, "perkawinan ke ayah");
            TkawinKeAyah.requestFocus();
        } else if (TalamatAyah.getText().trim().equals("")) {
            Valid.textKosong(TalamatAyah, "alamat ayah");
            TalamatAyah.requestFocus();
        } else if (TnoHpAyah.getText().trim().equals("")) {
            Valid.textKosong(TnoHpAyah, "no. hp/telp. ayah");
            TnoHpAyah.requestFocus();
        } else if (TKetAyah.getText().trim().equals("")) {
            Valid.textKosong(TKetAyah, "keterangan ayah");
            TKetAyah.requestFocus();
        } else {          
            if (Sequel.mengedittf("psikolog_anak_remaja_identitas_ortu", "waktu_simpan=?",
                    "no_rkm_medis=?,nm_ayah=?, nm_ibu=?,umur_ayah=?, umur_ibu=?,pnddkn_ayah=?, pnddkn_ibu=?, pkrjaan_ayah=?,pkrjaan_ibu=?, agama_ayah=?, "
                    + "agama_ibu=?, anak_ke_ayah=?, anak_ke_ibu=?, perkawinan_ke_ayah=?, perkawinan_ke_ibu=?,alamat_ayah=?,alamat_ibu=?,no_hp_ayah=?, no_hp_ibu=?, "
                    + "ket_ayah=?,ket_ibu=?", 22, new String[]{
                        TNoRM.getText(), TnmAyah.getText(), TnmIbu.getText(), TumurAyah.getText(), TumurIbu.getText(), cmbPndAyah.getSelectedItem().toString(),
                        cmbPndIbu.getSelectedItem().toString(), TpekerjaanAyah.getText(), TpekerjaanIbu.getText(), cmbAgamaAyah.getSelectedItem().toString(),
                        cmbAgamaIbu.getSelectedItem().toString(), TanakKeAyah.getText(), TanakKeIbu.getText(), TkawinKeAyah.getText(), TkawinKeIbu.getText(),
                        TalamatAyah.getText(), TalamatIbu.getText(), TnoHpAyah.getText(), TnoHpIbu.getText(), TKetAyah.getText(), TKetIbu.getText(), wktSimpan
                    }) == true) {
                tampilIdentitasORTU();
                emptTextIOT();
            }
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
                    + "permasalahan_dinilai='" + nilaiMasalah + "',alasan_permasalahan_dinilai='" + TAlasan.getText() + "', harapan='" + THarapan.getText() + "', "
                    + "perubahan_diinginkan_anak='" + TPerubahanAnak.getText() + "', perubahan_diinginkan_diri_sendiri='" + TPerubahanDiriSendiri.getText() + "', "
                    + "perubahan_diinginkan_keluarga='" + TPerubahanKeluarga.getText() + "'");
            tampilPsikologiKlinis();
            emptTextPPK();
        }
    }
    
    private void gantiRK() {
        if (tabMode7.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat kesehatan pada tabel...!!!!");
            tbRiwayatKesehatan.requestFocus();
        } else {
            Sequel.mengedit("psikolog_anak_remaja_riw_kes", "no_rawat='" + wktSimpan + "'",
                    "demm_tinggi='" + TDemam.getText() + "',pneumonia='" + TPneum.getText() + "', "
                    + "flu='" + TFlu.getText() + "',ensefalitis='" + TEnse.getText() + "', "
                    + "meningitis='" + TMeni.getText() + "',kejang='" + TKejang.getText() + "',"
                    + "tdk_sadarkan_diri='" + TTidak.getText() + "',geger_otak='" + TGeger.getText() + "',"
                    + "pingsan='" + TPingsan.getText() + "',pusing='" + TPusing.getText() + "',"
                    + "permslhn_terkait_amandel='" + TAmandel.getText() + "',permslhn_penglihatan='" + TLihat.getText() + "',"
                    + "permslhn_pendengaran='" + TDengar.getText() + "',permslhn_terkait_gigi='" + TGigi.getText() + "',"
                    + "permslhn_bb='" + TBerat.getText() + "',alergi='" + TAlergi.getText() + "',"
                    + "permslhn_kulit='" + TKulit.getText() + "',asma='" + TAsma.getText() + "',"
                    + "sakit_kepala='" + TKepala.getText() + "',permslhn_terkait_perut='" + TPerut.getText() + "',"
                    + "rawan_kecelakaan='" + TRawan.getText() + "',anemia='" + TAnemia.getText() + "',"
                    + "tknn_drh_tinggi_rendah='" + TDarah.getText() + "',permslhn_terkait_sinus='" + TSinus.getText() + "',"
                    + "permslhn_jantung='" + TJantung.getText() + "',hiperaktif='" + THiper.getText() + "',"
                    + "ket_penyakit_lain='" + TPenyakit.getText() + "',ket_dirawat_dirumah_skt='" + TRumahSakit.getText() + "'");
            tampilRiwayatKesehatan();
            emptTextRK();
        }
    }
    
    private void gantiRP() {
        if (tabMode8.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data observasi psikologis secara umum pada tabel...!!!!");
            tbRiwayatPerkembangan.requestFocus();
        } else if (cmbDinginkan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dg. benar masa kehamilan yg. diinginkan...!!!!");
            cmbDinginkan.requestFocus();
        } else if (cmbDirencanakan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dg. benar masa kehamilan yg. direncanakan...!!!!");
            cmbDirencanakan.requestFocus();
        } else if (cmbProses.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dg. benar proses kehamilan normal...!!!!");
            cmbProses.requestFocus();
        } else {
            Sequel.mengedit("psikolog_anak_remaja_riw_perkembangan", "no_rawat='" + wktSimpan + "'",
                    "diinginkan='" + cmbDinginkan.getSelectedItem().toString() + "',direncanakan='" + cmbDirencanakan.getSelectedItem().toString() + "', "
                    + "proses_hml_normal='" + cmbProses.getSelectedItem().toString() + "',ibu_sakit_tertekan_saat_hamil='" + TIbuTertekan.getText() + "', "
                    + "dukungan_penerimaan_suami='" + Tdukungan.getText() + "',lama_proses_lahiran='" + TLamaProses.getText() + "',"
                    + "cepat_prematur='" + TPrematur.getText() + "',cepat_terlambat='" + TTerlambat.getText() + "',proses_melahirkan='" + TProsesLahir.getText() + "'");
            tampilRiwayatPerkembangan();
            emptTextRP();
        }
    }
    
    private void gantiTPer() {
        if (tabMode9.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data kondisi psikopatologis pasien pada tabel...!!!!");
            tbTahapPerkembangan.requestFocus();
        } else {
            Sequel.mengedit("psikolog_anak_remaja_thp_perkembangan", "no_rawat='" + wktSimpan + "'",
                    "duduk='" + Tduduk.getText() + "',merangkak='" + Tmerangkak.getText() + "', "
                    + "berjalan='" + Tberjalan.getText() + "',bicara_1_kata='" + Tbicara1.getText() + "', "
                    + "bicara_dg_kalimat='" + TbicaraDengan.getText() + "',latihan_bak_pd_tempat='" + TlatihanBAK.getText() + "',"
                    + "latihan_bab_pd_tempat='" + TlatihanBAB.getText() + "',relasi_anak_dg_saudara='" + Trelasi.getText() + "',"
                    + "kebiasaan_anak='" + Tkebiasaan.getText() + "'");
            tampilTahapPerkembangan();
            emptTextTPer();
        }
    }
    
    private void gantiPS() {
        if (tabMode16.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data disekolah pasien pada tabel...!!!!");
            tbDisekolah.requestFocus();
        } else {
            Sequel.mengedit("psikolog_anak_remaja_disekolah", "no_rawat='" + TNoRW.getText() + "'",
                    "jns_sekolah='" + TjnsSekolah.getText() + "', program_akselerasi='" + Tprogram.getText() + "', "
                    + "pernah_tinggal_kelas='" + TtglKelas.getText() + "',kesulitan_bljr_spesifik='" + cmbBelajar.getSelectedItem().toString() + "',"
                    + "semangat_pergi_sekolah='" + cmbBersemangat.getSelectedItem().toString() + "',pernah_diskor_dikeluarkan_dr_sekolah='" + cmbPernah.getSelectedItem().toString() + "',"
                    + "prestasi_disekolah_tmp_kursus='" + Tprestasi.getText() + "',mata_pljrn_favorit='" + Tmata.getText() + "',"
                    + "mata_pljrn_dirasa_sulit='" + Tsulit.getText() + "',minat_hobi_keterampilan_dikuasai='" + Tminat.getText() + "'");
            
            tampilDisekolah();
            tampilRiwayatSekolah();
            emptTextPS();
        }
    }
    
    private void gantiO() {
        if (tabMode11.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data observasi kondisi psikologis pasien pada tabel...!!!!");
            tbObservasi.requestFocus();
        } else {
            Sequel.mengedit("psikolog_observasi", "no_rawat='" + wktSimpan + "'", 
                    "penampilan='" + Tpenampilan.getText() + "',ekspresi_wajah='" + Tekspresi.getText() + "',perasaan_suasana_hati='" + Tperasaan.getText() + "',"
                    + "tingkah_laku='" + Ttingkah.getText() + "',fungsi_umum='" + Tumum.getText() + "',fungsi_intelektual='" + Tintelektual.getText() + "',"
                    + "lainlain='" + Tlain.getText() + "'");
            tampilObservasi();
            emptTextO();
        }
    }
    
    private void gantiTP() {
        if (tabMode12.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data tes psikologi pasien pada tabel...!!!!");
            tbTesPsikolog.requestFocus();
        } else {
            Sequel.mengedit("psikolog_tes_psikologi", "no_rawat='" + wktSimpan + "'",
                    "rencana_tes='" + Valid.SetTgl(tgl_rencana.getSelectedItem() + "") + "',"
                    + "tes_psikologis='" + TTesPsiko.getText() + "', "
                    + "waktu='" + TWaktu.getText() + "',tester='" + TTester.getText() + "'");
            tampilTesPsikologi();
            emptTextTP();
        }
    }
    
    private void gantiDP() {
        if (tabMode13.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data dinamika psikologi pasien pada tabel...!!!!");
            tbDinamika.requestFocus();
        } else {
            Sequel.mengedit("psikolog_anak_remaja_dinamika", "no_rawat='" + wktSimpan + "'", "dinamika_psikologi='" + Tdinamika.getText() + "'");
            tampilDinamikaPsikologi();
            emptTextDP();
        }
    }
    
    private void gantiD() {
        if (tabMode15.getRowCount() == 0) {
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
    
    private void gantiMF() {
        if (tabMode14.getRowCount() == 0) {
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
    
    private void gantiP() {
        if (tabMode17.getRowCount() == 0) {
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
        if (tabMode19.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data rencana tritmen pada tabel...!!!!");
            tbTritmen.requestFocus();
        } else if (tabMode18.getRowCount() == 0) {
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
    
    private void tampilPreview() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsc1 = koneksi.prepareStatement("SELECT rp.tgl_registrasi, p.no_rkm_medis, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_dtg, pd.urutan_lahir, "
                        + "p.nm_pasien, if(p.jk = 'L','Laki-laki','Perempuan') jk, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' tahun') ttl, "
                        + "p.agama, p.pnd, CONCAT(p.alamat,', ', kl.nm_kel,', ', kc.nm_kec,', ',kb.nm_kab) almt, p.no_tlp FROM psikolog_data_diri_klien pd "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat = pd.no_rawat INNER JOIN pasien p on p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p.kd_kab WHERE "
                        + "p.no_rkm_medis='" + TNoRM.getText() + "' and rp.umurdaftar<=17 and rp.sttsumur='Th' and "
                        + "pd.rekam_psikologis='anak_remaja' and rp.no_rawat ='" + TNoRW.getText() + "'").executeQuery();
                
                while (rsc1.next()) {
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
                            + "    <td valign='top'>Agama</td>"
                            + "    <td valign='top'>: " + rsc1.getString("agama") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Pendidikan</td>"
                            + "    <td valign='top'>: " + rsc1.getString("pnd") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Urutan Kelahiran</td>"
                            + "    <td valign='top'>: " + rsc1.getString("urutan_lahir") + "</td>"
                            + "  </tr>"
                            + "  <tr>"
                            + "    <td valign='top'>Alamat</td>"
                            + "    <td valign='top'>: " + rsc1.getString("almt") + "</td>"
                            + "  </tr>"                            
                            + "</tbody>"
                            + "</table>"
                            + "<br>"
                    );
                    
                    //identitas saudara
                    try {
                        rsc2 = koneksi.prepareStatement("SELECT ifnull(nama,'') nama, concat(ifnull(umur,''),' ',ifnull(sttsumur,'')) umur, "
                                + "ifnull(jk,'') jk, ifnull(posisi,'') posisi, ifnull(keterangan,'') keterangan from psikolog_anggota_keluarga WHERE "
                                + "no_rkm_medis ='" + TNoRM.getText() + "' and rekam_psikologis='anak_remaja'").executeQuery();

                        if (rsc2.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='5'><b>Identitas Saudara</b></th>"
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
                    
                    //identitas orang tua
                    try {
                        rsc3 = koneksi.prepareStatement("SELECT ifnull(nm_ayah,'') nm_ayah, concat(ifnull(umur_ayah,''),' tahun') usiaAyah, "
                                + "ifnull(pnddkn_ayah,'') pnddkn_ayah, ifnull(pkrjaan_ayah,'') pkrjaan_ayah, "
                                + "ifnull(agama_ayah,'') agama_ayah, ifnull(anak_ke_ayah,'') anak_ke_ayah, ifnull(perkawinan_ke_ayah,'') perkawinan_ke_ayah, "
                                + "ifnull(alamat_ayah,'') alamat_ayah, ifnull(no_hp_ayah,'') no_hp_ayah, ifnull(ket_ayah,'') ket_ayah, ifnull(nm_ibu,'') nm_ibu, "
                                + "concat(ifnull(umur_ibu,''),' tahun') usiaIbu, ifnull(pnddkn_ibu,'') pnddkn_ibu, ifnull(pkrjaan_ibu,'') pkrjaan_ibu, "
                                + "ifnull(agama_ibu,'') agama_ibu, ifnull(anak_ke_ibu,'') anak_ke_ibu, ifnull(perkawinan_ke_ibu,'') perkawinan_ke_ibu, "
                                + "ifnull(alamat_ibu,'') alamat_ibu, ifnull(no_hp_ibu,'') no_hp_ibu, ifnull(ket_ibu,'') ket_ibu "
                                + "FROM psikolog_anak_remaja_identitas_ortu WHERE no_rkm_medis ='" + TNoRM.getText() + "'").executeQuery();

                        if (rsc3.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='3'><b>Identitas Orang Tua</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"                                    
                                    + "<tr>"
                                    + "    <th width='120px'></th>"
                                    + "    <th>Ayah</th>"
                                    + "    <th>Ibu</th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td>Nama</td>"
                                    + "    <td>" + rsc3.getString("nm_ayah") + "</td>"
                                    + "    <td>" + rsc3.getString("nm_ibu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Umur</td>"
                                    + "    <td>" + rsc3.getString("usiaAyah") + "</td>"
                                    + "    <td>" + rsc3.getString("usiaIbu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Pendidikan</td>"
                                    + "    <td>" + rsc3.getString("pnddkn_ayah") + "</td>"
                                    + "    <td>" + rsc3.getString("pnddkn_ibu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Pekerjaan</td>"
                                    + "    <td>" + rsc3.getString("pkrjaan_ayah") + "</td>"
                                    + "    <td>" + rsc3.getString("pkrjaan_ibu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Agama</td>"
                                    + "    <td>" + rsc3.getString("agama_ayah") + "</td>"
                                    + "    <td>" + rsc3.getString("agama_ibu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Anak Ke -</td>"
                                    + "    <td>" + rsc3.getString("anak_ke_ayah") + "</td>"
                                    + "    <td>" + rsc3.getString("anak_ke_ibu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Perkawinan Ke -</td>"
                                    + "    <td>" + rsc3.getString("perkawinan_ke_ayah") + "</td>"
                                    + "    <td>" + rsc3.getString("perkawinan_ke_ibu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Alamat</td>"
                                    + "    <td valign='top'>" + rsc3.getString("alamat_ayah").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "    <td valign='top'>" + rsc3.getString("alamat_ibu").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>No. HP</td>"
                                    + "    <td>" + rsc3.getString("no_hp_ayah") + "</td>"
                                    + "    <td>" + rsc3.getString("no_hp_ibu") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Keterangan</td>"
                                    + "    <td valign='top'>" + rsc3.getString("ket_ayah") + "</td>"
                                    + "    <td valign='top'>" + rsc3.getString("ket_ibu") + "</td>"
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
                                + "WHERE pd.no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                        if (rsc4.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
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
                                + "ifnull(harapan,'') harapan, ifnull(perubahan_diinginkan_diri_sendiri,'') perubahan_diinginkan_diri_sendiri, ifnull(perubahan_diinginkan_anak,'') perubahan_diinginkan_anak, "
                                + "ifnull(perubahan_diinginkan_keluarga,'') perubahan_diinginkan_keluarga FROM psikolog_wawancara_klinis WHERE "
                                + "no_rawat ='" + TNoRW.getText() + "' and rekam_psikologis='Anak/Remaja'").executeQuery();

                        if (rsc5.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
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
                                    + "    <td>&#10004 Alasan Yang Membuat Pasien Mencari Bantuan Kali Ini<br><br>"
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
                                    + "    <td>&#10004 Harapan Terhadap Anak<br><br>"
                                    + "" + rsc5.getString("harapan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Perubahan Yang Diinginkan Dari Anak<br><br>"
                                    + "" + rsc5.getString("perubahan_diinginkan_anak").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
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
                    
                    //riwayat kesehatan
                    try {
                        rsc6 = koneksi.prepareStatement("SELECT ifnull(demm_tinggi,'') demm_tinggi, ifnull(pneumonia,'') pneumonia, ifnull(flu,'') flu, ifnull(ensefalitis,'') ensefalitis, "
                                + "ifnull(meningitis,'') meningitis, ifnull(kejang,'') kejang, ifnull(tdk_sadarkan_diri,'') tdk_sadarkan_diri, ifnull(geger_otak,'') geger_otak, "
                                + "ifnull(pingsan,'') pingsan, ifnull(pusing,'') pusing, ifnull(permslhn_terkait_amandel,'') permslhn_terkait_amandel, "
                                + "ifnull(permslhn_penglihatan,'') permslhn_penglihatan, ifnull(permslhn_pendengaran,'') permslhn_pendengaran, ifnull(permslhn_terkait_gigi,'') permslhn_terkait_gigi, "
                                + "ifnull(permslhn_bb,'') permslhn_bb, ifnull(alergi,'') alergi, ifnull(permslhn_kulit,'') permslhn_kulit, ifnull(asma,'') asma, "
                                + "ifnull(sakit_kepala,'') sakit_kepala, ifnull(permslhn_terkait_perut,'') permslhn_terkait_perut, ifnull(rawan_kecelakaan,'') rawan_kecelakaan, ifnull(anemia,'') anemia, "
                                + "ifnull(tknn_drh_tinggi_rendah,'') tknn_drh_tinggi_rendah, ifnull(permslhn_terkait_sinus,'') permslhn_terkait_sinus, ifnull(permslhn_jantung,'') permslhn_jantung, "
                                + "ifnull(hiperaktif,'') hiperaktif, ifnull(ket_penyakit_lain,'') ket_penyakit_lain, ifnull(ket_dirawat_dirumah_skt,'') ket_dirawat_dirumah_skt "
                                + "FROM psikolog_anak_remaja_riw_kes WHERE no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                        if (rsc6.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='4'><b>Riwayat Kesehatan<br>Yang Pernah Dialami Atau Sedang Dialami Anak</b></th>"
                                    + "  </tr>"
                                    + "</thead>"                                            
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <th width='180px'></th>"
                                    + "    <th align='left'>  Usia</th>"
                                    + "    <th width='250px'></th>"
                                    + "    <th align='left'>  Usia</th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td>Demam Tinggi</td>"
                                    + "    <td>: " + rsc6.getString("demm_tinggi") + "</td>"
                                    + "    <td>Permasalahan Terkait Gigi</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_terkait_gigi") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Pneumonia</td>"
                                    + "    <td>: " + rsc6.getString("pneumonia") + "</td>"
                                    + "    <td>Permasalahan Berat Badan</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_bb") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Flu</td>"
                                    + "    <td>: " + rsc6.getString("flu") + "</td>"
                                    + "    <td>Alergi</td>"
                                    + "    <td>: " + rsc6.getString("alergi") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Ensefalitis</td>"
                                    + "    <td>: " + rsc6.getString("ensefalitis") + "</td>"
                                    + "    <td>Permasalahan Kulit</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_kulit") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Meningitis</td>"
                                    + "    <td>: " + rsc6.getString("meningitis") + "</td>"
                                    + "    <td>Asma</td>"
                                    + "    <td>: " + rsc6.getString("asma") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Kejang</td>"
                                    + "    <td>: " + rsc6.getString("kejang") + "</td>"
                                    + "    <td>Sakit Kepala</td>"
                                    + "    <td>: " + rsc6.getString("sakit_kepala") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Tidak Sadarkan Diri</td>"
                                    + "    <td>: " + rsc6.getString("tdk_sadarkan_diri") + "</td>"
                                    + "    <td>Permasalahan Terkait Perut</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_terkait_perut") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Geger Otak</td>"
                                    + "    <td>: " + rsc6.getString("geger_otak") + "</td>"
                                    + "    <td>Rawan Kecelakaan</td>"
                                    + "    <td>: " + rsc6.getString("rawan_kecelakaan") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Pingsan</td>"
                                    + "    <td>: " + rsc6.getString("pingsan") + "</td>"
                                    + "    <td>Anemia</td>"
                                    + "    <td>: " + rsc6.getString("anemia") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Pusing</td>"
                                    + "    <td>: " + rsc6.getString("pusing") + "</td>"
                                    + "    <td>Tekanan Darah Tinggi/Rendah</td>"
                                    + "    <td>: " + rsc6.getString("tknn_drh_tinggi_rendah") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Permasalahan Terkait Amandel</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_terkait_amandel") + "</td>"
                                    + "    <td>Permasalahan Terkait Sinus</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_terkait_sinus") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Permasalahan Penglihatan</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_penglihatan") + "</td>"
                                    + "    <td>Permasalahan Jantung</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_jantung") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Permasalahan Pendengaran</td>"
                                    + "    <td>: " + rsc6.getString("permslhn_pendengaran") + "</td>"
                                    + "    <td>Hiperaktif</td>"
                                    + "    <td>: " + rsc6.getString("hiperaktif") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top' colspan='4'>Penyakit Lain (Jelaskan) : <br>" + rsc6.getString("ket_penyakit_lain").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top' colspan='4'>Apa Anak Pernah Dirawat Di Rumah Sakit (Jelaskan) : <br>" + rsc6.getString("ket_dirawat_dirumah_skt").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
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
                    
                    //riwayat perkembangan
                    try {
                        rsc7 = koneksi.prepareStatement("SELECT ifnull(diinginkan,'') diinginkan, ifnull(direncanakan,'') direncanakan, ifnull(proses_hml_normal,'') proses_hml_normal, "
                                + "ifnull(ibu_sakit_tertekan_saat_hamil,'') ibu_sakit_tertekan_saat_hamil, ifnull(dukungan_penerimaan_suami,'') dukungan_penerimaan_suami, "
                                + "ifnull(lama_proses_lahiran,'') lama_proses_lahiran, ifnull(cepat_prematur,'') cepat_prematur, ifnull(cepat_terlambat,'') cepat_terlambat, "
                                + "ifnull(proses_melahirkan,'') proses_melahirkan FROM psikolog_anak_remaja_riw_perkembangan WHERE no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                        if (rsc7.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Riwayat Perkembangan</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Masa Kehamilan</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td width='180px'>Diinginkan</td>"
                                    + "    <td>: " + rsc7.getString("diinginkan") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Direncanakan</td>"
                                    + "    <td>: " + rsc7.getString("direncanakan") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Proses Kehamilan Normal</td>"
                                    + "    <td>: " + rsc7.getString("proses_hml_normal") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td colspan='2'>Jika Ibu Sakit Atau Tertekan Selama Masa Kehamilan, Jelaskan : <br>" + rsc7.getString("ibu_sakit_tertekan_saat_hamil").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td colspan='2'>Dukungan Dan Penerimaan Suami (Jelaskan) : <br>" + rsc7.getString("dukungan_penerimaan_suami").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Masa Kelahiran</b></th>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td colspan='2'>Lama Proses Melahirkan : <br>" + rsc7.getString("lama_proses_lahiran") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td colspan='2'>Jika Prematur, Seberapa Cepat : <br>" + rsc7.getString("cepat_prematur") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td colspan='2'>Jika Terlambat, Seberapa Lama : <br>" + rsc7.getString("cepat_terlambat") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td colspan='2'>Proses Melahirkan (Spontan / Caesar) : <br>" + rsc7.getString("proses_melahirkan") + "</td>"
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
                    
                    //tahap perkembangan
                    try {
                        rsc8 = koneksi.prepareStatement("SELECT ifnull(duduk,'') duduk, ifnull(merangkak,'') merangkak, ifnull(berjalan,'') berjalan, "
                                + "ifnull(bicara_1_kata,'') bicara_1_kata, ifnull(bicara_dg_kalimat,'') bicara_dg_kalimat, ifnull(latihan_bak_pd_tempat,'') latihan_bak_pd_tempat, "
                                + "ifnull(latihan_bab_pd_tempat,'') latihan_bab_pd_tempat, ifnull(relasi_anak_dg_saudara,'') relasi_anak_dg_saudara, ifnull(kebiasaan_anak,'') kebiasaan_anak "
                                + "FROM psikolog_anak_remaja_thp_perkembangan WHERE no_rawat = '" + TNoRW.getText() + "'").executeQuery();

                        if (rsc8.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Tahap Perkembangan</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td width='230px'>&#10004 Duduk</td>"
                                    + "    <td>: " + rsc8.getString("duduk") + "</td>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Merangkak</td>"
                                    + "    <td>: " + rsc8.getString("merangkak") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Berjalan</td>"
                                    + "    <td>: " + rsc8.getString("berjalan") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Bicara 1 Kata</td>"
                                    + "    <td>: " + rsc8.getString("bicara_1_kata") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Bicara dengan kalimat-kalimat</td>"
                                    + "    <td>: " + rsc8.getString("bicara_dg_kalimat") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Latihan BAK pada tempatnya</td>"
                                    + "    <td>: " + rsc8.getString("latihan_bak_pd_tempat") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>&#10004 Latihan BAB pada tempatnya</td>"
                                    + "    <td>: " + rsc8.getString("latihan_bab_pd_tempat") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td colspan='2'>Relasi anak dengan saudara dan sabayanya :<br>" + rsc8.getString("relasi_anak_dg_saudara").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td colspan='2'>Kebiasaan-kebiasaan anak :<br>" + rsc8.getString("kebiasaan_anak").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
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
                    
                    //pendidikan sekolah (data sekolah)
                    try {
                        rsc9 = koneksi.prepareStatement("SELECT ifnull(nm_sekolah,'') nm_sekolah, ifnull(kota,'') kota, ifnull(thn_masuk,'') thn_masuk, "
                                + "ifnull(thn_selesai,'') thn_selesai, ifnull(lama_sekolah,'') lama_sekolah "
                                + "FROM psikolog_anak_remaja_riw_sekolah WHERE no_rawat ='" + TNoRW.getText() + "' order by waktu_simpan").executeQuery();

                        if (rsc9.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='5'><b>Pendidikan / Sekolah</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td width='50px'><b>Nama Sekolah</b></td>"
                                    + "    <td width='15px'><b>Kota</b></td>"
                                    + "    <td width='30px'><b>Tahun Masuk</b></td>"
                                    + "    <td width='75px'><b>Tahun Selesai</b></td>"
                                    + "    <td width='75px'><b>Lama Bersekolah</b></td>"
                                    + "  </tr>"
                            );
                      
                            rsc9.beforeFirst();
                            while (rsc9.next()) {
                                htmlContent.append(
                                        "<tr>"
                                        + "<td valign='top' width='20px'>" + rsc9.getString("nm_sekolah") + "</td>"
                                        + "<td valign='top' width='15px'>" + rsc9.getString("kota") + "</td>"
                                        + "<td valign='top' width='30px'>" + rsc9.getString("thn_masuk") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc9.getString("thn_selesai") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc9.getString("lama_sekolah") + "</td>"
                                        + "</tr>");
                            }
                            htmlContent.append(
                                    "</tbody>"
                                    + "</table>"
                            );

                            //pendidikan sekolah (data pendidikan)
                            try {
                                rsc10 = koneksi.prepareStatement("SELECT ifnull(jns_sekolah,'') jns_sekolah, ifnull(program_akselerasi,'') program_akselerasi, "
                                        + "ifnull(pernah_tinggal_kelas,'') pernah_tinggal_kelas, ifnull(kesulitan_bljr_spesifik,'') kesulitan_bljr_spesifik, "
                                        + "ifnull(semangat_pergi_sekolah,'') semangat_pergi_sekolah, ifnull(pernah_diskor_dikeluarkan_dr_sekolah,'') pernah_diskor_dikeluarkan_dr_sekolah, "
                                        + "ifnull(prestasi_disekolah_tmp_kursus,'') prestasi_disekolah_tmp_kursus, ifnull(mata_pljrn_favorit,'') mata_pljrn_favorit, "
                                        + "ifnull(mata_pljrn_dirasa_sulit,'') mata_pljrn_dirasa_sulit, ifnull(minat_hobi_keterampilan_dikuasai,'') minat_hobi_keterampilan_dikuasai "
                                        + "FROM psikolog_anak_remaja_disekolah WHERE no_rawat ='" + TNoRW.getText() + "'").executeQuery();

                                if (rsc10.next()) {
                                    htmlContent.append(
                                            "<table width='100%' class='adaborder'>"                                            
                                            + "<tbody>"
                                            + "  <tr>"
                                            + "    <td width='280px'>Jenis Sekolah</td>"
                                            + "    <td valign='top'>: " + rsc10.getString("jns_sekolah") + "</td>"
                                            + "  </tr>"
                                            + "</thead>"
                                            + "<tbody>"
                                            + "  <tr>"
                                            + "    <td colspan='2'>Apa anak mengikuti program akselerasi ? (jika Ya, kapan dan berapa lama ?)<br> " + rsc10.getString("program_akselerasi") + "</td>"
                                            + "  </tr>"
                                            + "  <tr>"
                                            + "    <td colspan='2'>Apa anak pernah tinggal kelas ? (jika Ya, kapan dan berapa lama ?)<br> " + rsc10.getString("pernah_tinggal_kelas") + "</td>"
                                            + "  </tr>"
                                            + "  <tr>"
                                            + "    <td>Apa anak mengalami kesulitan belajar spesifik ?</td>"
                                            + "    <td valign='top'>: " + rsc10.getString("kesulitan_bljr_spesifik") + "</td>"
                                            + "  </tr>"
                                            + "  <tr>"
                                            + "    <td>Apa anak bersemangat pergi ke sekolah ?</td>"
                                            + "    <td valign='top'>: " + rsc10.getString("semangat_pergi_sekolah") + "</td>"
                                            + "  </tr>"
                                            + "  <tr>"
                                            + "    <td>Apa anak pernah di skor atau dikeluarkan dari sekolah ?</td>"
                                            + "    <td valign='top'>: " + rsc10.getString("pernah_diskor_dikeluarkan_dr_sekolah") + "</td>"
                                            + "  </tr>"
                                            + "  <tr>"
                                            + "    <td colspan='2'>Prestasi akademik anak disekolah / tempat kursus ?<br> " + rsc10.getString("prestasi_disekolah_tmp_kursus") + "</td>"
                                            + "  </tr>"
                                            + "  <tr>"
                                            + "    <td colspan='2'>Mata pelajaran favorit anak :<br> " + rsc10.getString("mata_pljrn_favorit") + "</td>"
                                            + "  </tr>"
                                            + "  <tr>"
                                            + "    <td colspan='2'>Mata pelajaran yang dirasa sulit bagi anak :<br> " + rsc10.getString("mata_pljrn_dirasa_sulit") + "</td>"
                                            + "  </tr>"
                                            + "  <tr>"
                                            + "    <td colspan='2'>Minat, hobi, keterampilan yang dikuasai anak :<br> " + rsc10.getString("minat_hobi_keterampilan_dikuasai") + "</td>"
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
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc9 != null) {
                            rsc9.close();
                        }
                    }
                    
                    //observasi
                    try {
                        rsc11 = koneksi.prepareStatement("SELECT ifnull(penampilan,'') penampilan, ifnull(ekspresi_wajah,'') ekspresi_wajah, ifnull(perasaan_suasana_hati,'') perasaan_suasana_hati, "
                                + "ifnull(tingkah_laku,'') tingkah_laku, ifnull(fungsi_umum,'') fungsi_umum, ifnull(fungsi_intelektual,'') fungsi_intelektual, ifnull(lainlain,'') lainlain "
                                + "FROM psikolog_observasi WHERE no_rawat = '" + TNoRW.getText() + "' and rekam_psikologis='anak_remaja'").executeQuery();

                        if (rsc11.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Observasi<br>Kondisi Psikologis Pasien Secara Umum</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td width='180px'>Penampilan</td>"
                                    + "    <td>: " + rsc11.getString("penampilan") + "</td>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td>Ekspresi Wajah</td>"
                                    + "    <td>: " + rsc11.getString("ekspresi_wajah") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Perasaan / Suasana Hati</td>"
                                    + "    <td>: " + rsc11.getString("perasaan_suasana_hati") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Tingkah Laku</td>"
                                    + "    <td>: " + rsc11.getString("tingkah_laku") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Fungsi Umum</td>"
                                    + "    <td>: " + rsc11.getString("fungsi_umum") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Fungsi Intelektual</td>"
                                    + "    <td>: " + rsc11.getString("fungsi_intelektual") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Lain-lain</td>"
                                    + "    <td>: " + rsc11.getString("lainlain") + "</td>"
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
                    
                    //tes psikologis
                    try {
                        rsc12 = koneksi.prepareStatement("SELECT DATE_FORMAT(rencana_tes,'%d-%m-%Y') tglRencana, ifnull(tes_psikologis,'') tes_psikologis, "
                                + "ifnull(waktu,'') waktu, ifnull(tester,'') tester FROM psikolog_tes_psikologi WHERE no_rawat = '" + TNoRW.getText() + "' "
                                + "and rekam_psikologis='anak_remaja'").executeQuery();

                        if (rsc12.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'>"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='5'><b>Tes Psikologi<br>Pelaksanaan Tes</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td width='50px'><b>Rencana Tes</b></td>"
                                    + "    <td width='15px'><b>Tes Psikologi</b></td>"
                                    + "    <td width='30px'><b>Waktu</b></td>"
                                    + "    <td width='75px'><b>Tester</b></td>"
                                    + "    <td width='75px'><b>Hasil</b></td>"
                                    + "  </tr>"
                            );
                      
                            rsc12.beforeFirst();
                            while (rsc12.next()) {
                                htmlContent.append(
                                        "<tr>"
                                        + "<td valign='top' width='20px'>" + rsc12.getString("tglRencana") + "</td>"
                                        + "<td valign='top' width='15px'>" + rsc12.getString("tes_psikologis") + "</td>"
                                        + "<td valign='top' width='30px'>" + rsc12.getString("waktu") + "</td>"
                                        + "<td valign='top' width='75px'>" + rsc12.getString("tester") + "</td>"
                                        + "<td valign='top' width='75px'>-</td>"
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
                        if (rsc12 != null) {
                            rsc12.close();
                        }
                    } 
                    
                    //dinamika psikologi
                    try {
                        rsc13 = koneksi.prepareStatement("SELECT ifnull(dinamika_psikologi,'') dinamika_psikologi "
                                + "FROM psikolog_anak_remaja_dinamika WHERE no_rawat = '" + TNoRW.getText() + "'").executeQuery();

                        if (rsc13.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Dinamika Psikologi</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top'>" + rsc13.getString("dinamika_psikologi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
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
                    
                    //diagnosis
                    try {
                        rsc14 = koneksi.prepareStatement("SELECT ifnull(pd.kesan_awal,'') kesan_awal, ifnull(pd.diagnosa_utama,'') diagnosa_utama, "
                                + "ifnull(pd.diagnosa_banding,'') diagnosa_banding FROM psikolog_diagnosis pd INNER JOIN reg_periksa rp ON rp.no_rawat=pd.no_rawat "
                                + "INNER JOIN psikolog_data_diri_klien pdd on pdd.no_rawat=pd.no_rawat WHERE "
                                + "pd.no_rawat = '" + TNoRW.getText() + "' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pdd.rekam_psikologis='anak_remaja'").executeQuery();

                        if (rsc14.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th colspan='2'><b>Diagnosis<br>(Berdasarkan DSM / ICD / PPDGJ)</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top' width='180px'>Kesan Awal</td>"
                                    + "    <td valign='top' >: " + rsc14.getString("kesan_awal").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td>Diagnosis Utama (dx)</td>"
                                    + "    <td>: " + rsc14.getString("diagnosa_utama") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td>Diagnosis Banding</td>"
                                    + "    <td>: " + rsc14.getString("diagnosa_banding") + "</td>"
                                    + "  </tr>"
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc14 != null) {
                            rsc14.close();
                        }
                    }
                    
                    //manifestasi fungsi
                    try {
                        rsc15 = koneksi.prepareStatement("SELECT ifnull(pm.manifestasi,'') manifestasi FROM psikolog_manifestasi_fungsi pm "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pm.no_rawat INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pm.no_rawat WHERE "
                                + "pm.no_rawat = '" + TNoRW.getText() + "' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja'").executeQuery();

                        if (rsc15.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Manifestasi Fungsi Psikologis</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top'>" + rsc15.getString("manifestasi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc15 != null) {
                            rsc15.close();
                        }
                    }
                    
                    //prognosis
                    try {
                        rsc16 = koneksi.prepareStatement("SELECT ifnull(pp.prognosis,'') prognosis FROM psikolog_prognosis pp "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pp.no_rawat INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pp.no_rawat WHERE "
                                + "pp.no_rawat = '" + TNoRW.getText() + "' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja'").executeQuery();

                        if (rsc16.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='adaborder'"
                                    + "<thead>"
                                    + "  <tr>"
                                    + "    <th><b>Prognosis</b></th>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                                    + "  <tr>"
                                    + "    <td valign='top'>" + rsc16.getString("prognosis").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                    + "  </tr>"                                    
                                    + "</tbody>"
                                    + "</table>"
                                    + "<br>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsc16 != null) {
                            rsc16.close();
                        }
                    }
                    
                    //rencana tritmen
                    try {
                        rsc17 = koneksi.prepareStatement("SELECT ifnull(pr.tritmen,'') tritmen, ifnull(pr.pertemuan_selanjutnya,'') pertemuan_selanjutnya, "
                                + "ifnull(pr.cttn_tambahan,'') cttn_tambahan FROM psikolog_rencana_tritmen pr "
                                + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN psikolog_data_diri_klien pd on pd.no_rawat=pr.no_rawat WHERE "
                                + "pr.no_rawat = '" + TNoRW.getText() + "' and rp.umurdaftar<=17 and rp.sttsumur='Th' and pd.rekam_psikologis='anak_remaja'").executeQuery();

                        if (rsc17.next()) {
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
                                    + "    <td valign='top'>: " + rsc17.getString("tritmen") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Pertemuan Selanjutnya</td>"
                                    + "    <td valign='top'>: " + rsc17.getString("pertemuan_selanjutnya") + "</td>"
                                    + "  </tr>"
                                    + "  <tr>"
                                    + "    <td valign='top'>Catatan Tambahan</td>"
                                    + "    <td valign='top'>: " + rsc17.getString("cttn_tambahan") + "</td>"
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
                        if (rsc17 != null) {
                            rsc17.close();
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
    
    private void cetakRPA() {
        try {
            File f = new File("rekam_psikologis_anak_remaja.html");
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
                            
                    + "<table width='100%' class='noborder'>"
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
                    + "<hr><center><font size='3' face='Tahoma'><b>REKAM PSIKOLOGIS (Anak / Remaja)<br>Poliklinik Psikologi<br><br></font>"
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
