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
package simrskhanza;
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
import java.io.FileInputStream;
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
import kepegawaian.DlgCariPetugas;
import rekammedis.DlgMasterRejimenHIV;

/**
 *
 * @author dosen
 */
public final class DlgIkhtisarPerawatanHIVart extends javax.swing.JDialog {
    private final DefaultTableModel tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabMode7, tabMode8;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement psCek, pspas, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, psc12, psc51, psc52,
            psc53, psc54, psc55, psc56, psc3, psc4, psc5, psc6, psc7;
    private ResultSet rsCek, rspas, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rsc12, rsc51, rsc52, rsc53,
            rsc54, rsc55, rsc56, rsc3, rsc4, rsc5, rsc6, rsc7;
    private int i = 0, x = 0;
    private final Properties prop = new Properties();
    public DlgCariPetugas pengawas = new DlgCariPetugas(null, false);
    public DlgMasterRejimenHIV rejimen = new DlgMasterRejimenHIV(null, false);
    private String noUrut = "", sttsPekerjaan = "", pasienDirujuk = "", jnsTerapi = "", ppk = "", cekEntriPoin = "", cekRujukMsk = "",
            tglsimpan = "", nilaiAlasan = "", alasanya = "", tglmeninggal = "", tglkunjungan = "", tglrujukkeluar = "", tglrujukmasuk = "",
            faktorRes = "", artYA = "", klasTB = "";

    /* Creates new form DlgPerawatan
     *
     * @param parent
     * @param modal
     */
    public DlgIkhtisarPerawatanHIVart(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabMode1 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "No. Reg. Nas.", "Jns. Kelamin", "Tgl. Lahir", "Riwayat Alergi Obat", 
            "Nama Pengawas (PMO)", "Hub. dg. Pasien", "No. Telp. PMO", "Alamat (PMO)", "Tgl. Konf. Tes HIV", "Entry Point", "Entry Point (2. R. Jalan)",
            "Entry Point (5. Jangkauan)", "EP. (2. R. Jalan-Lainnya)", "EP. (5. Jangkauan-Lainnya)", "EP. (8. Lainnya-Uraikan)", "Dirujuk msk. dari klinik lain",
            "Pendidikan", "Nama Klinik Sebelumnya", "Status Pekerjaan", "Tgl. Rujuk Msk.", "Faktor Resiko", "FR. (Lainnya-Uraikan)", "kdrejimen", "nmrejimen",
            "rujukMsk", "sttsPernikahan", "menerimaART", "artYA", "tmptARTdulu"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tb12.setModel(tabMode1);
        tb12.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tb12.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 31; i++) {
            TableColumn column = tb12.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(110);
            } else if (i == 12) {
                column.setPreferredWidth(150);
            } else if (i == 13) {
                column.setPreferredWidth(150);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setPreferredWidth(250);
            } else if (i == 16) {
                column.setPreferredWidth(250);
            } else if (i == 17) {
                column.setPreferredWidth(170);
            } else if (i == 18) {
                column.setPreferredWidth(120);
            } else if (i == 19) {
                column.setPreferredWidth(200);
            } else if (i == 20) {
                column.setPreferredWidth(110);
            } else if (i == 21) {
                column.setPreferredWidth(90);
            } else if (i == 22) {
                column.setPreferredWidth(110);
            } else if (i == 23) {
                column.setPreferredWidth(200);
            } else if (i == 24) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 27) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 28) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 29) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tb12.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Nama Anggota Keluarga", "Hubungan", "Umur", "HIV +/-",
            "ART +/tdk", "No. Reg. Nasional","kode_keluarga"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tb3.setModel(tabMode2);
        tb3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tb3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tb3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(40);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tb3.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Nama Dosis", "Dosis ARV", "Lama Penggunaanya", "Dicatat Tgl.", "tglsimpan", "tglcatat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tb4.setModel(tabMode3);
        tb4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tb4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tb4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(140);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tb4.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null, new Object[]{
            "urutan", "No. RM", "Nama Pasien", "Pemeriksaan", "Tanggal", "Stad WHO", "BB", "Status Fungsional",
            "Jumlah Cd4", "Lain-lain", "tglPeriksa"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tb5.setModel(tabMode4);
        tb5.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tb5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tb5.getColumnModel().getColumn(i);
            if (i == 0) {
//                column.setPreferredWidth(60);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
//                column.setPreferredWidth(250);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tb5.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Tgl. Terapi", "Jenis Terapi ART", "Alasan", "alasanLainSubstitusi", 
            "alasanRestart", "Nama Rejimen Baru", "tglSimpan", "tglTerapi","enumAlasan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tb6.setModel(tabMode5);
        tb6.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tb6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tb6.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(110);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
//                column.setPreferredWidth(250);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(250);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tb6.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Klasifikasi TB", "Klasifikasi TB ekstra paru lokasi", "Tipe TB", 
            "Rejimen TB", "Tempat Pengobatan TB", "Kabupaten", "Nama Sarana Kesehatan", "No. Reg. TB Kab./Kota", "Tgl. Mulai Terapi",
            "Tgl. Selesai Terapi", "tglmulai", "tglselesai,"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class            
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tb7.setModel(tabMode6);
        tb7.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tb7.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tb7.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tb7.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode7 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Akhir Follow-UP", "Tgl. Meninggal Dunia", "Tgl. Kunjungan Terakhir", "Tgl. Rujuk Keluar",
            "Nama Klinik Baru", "tglmati", "tglkunjungakhir", "tglrujukkeluar"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tb8.setModel(tabMode7);
        tb8.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tb8.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tb8.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(120);
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
        tb8.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode8 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Tgl. Follow-UP", "Rencana Tgl. Kunj.", "BB (Kg.)", "TB (Cm.)", "Status Fungsional", "Stad WHO",
            "Hamil", "Metode KB", "Infeksi Oportunistik", "Infeksi Oportunistik (Lainnya)", "Obat Untuk IO ",
            "Stts. TB (Skerining TB)", "PPK", "Obat ARV & Dosis yg. Diberikan", "Adherance ART", "Efek Samping ART",
            "Efek Samping ART (Lainnya)", "Jumlah Cd4", "Hasil Lab.", "Diberikan Kondom", "Rujuk Ke Spesialis / MRS",
            "tgl_simpan", "tgl_follow_up", "rencana_tgl_kunjungan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,                
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tb9.setModel(tabMode8);
        tb9.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tb9.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 26; i++) {
            TableColumn column = tb9.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(95);
            } else if (i == 3) {
                column.setPreferredWidth(110);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setPreferredWidth(200);
            } else if (i == 10) {
                column.setPreferredWidth(120);
            } else if (i == 11) {
                column.setPreferredWidth(200);
            } else if (i == 12) {
                column.setPreferredWidth(200);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(50);
            } else if (i == 15) {
                column.setPreferredWidth(200);
            } else if (i == 16) {
                column.setPreferredWidth(110);
            } else if (i == 17) {
                column.setPreferredWidth(100);
            } else if (i == 18) {
                column.setPreferredWidth(200);
            } else if (i == 19) {
                column.setPreferredWidth(70);
            } else if (i == 20) {
                column.setPreferredWidth(200);
            } else if (i == 21) {
                column.setPreferredWidth(100);
            } else if (i == 22) {
                column.setPreferredWidth(200);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tb9.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TumurKlg.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurKlg));
        TnoTelpPMO.setDocument(new batasInput((byte) 13).getOnlyAngka(TnoTelpPMO));
        TnoRegNasKlg.setDocument(new batasInput((byte) 11).getKata(TnoRegNasKlg));
        bb.setDocument(new batasInput((byte) 4).getOnlyAngka(bb));
        TBB.setDocument(new batasInput((byte) 3).getOnlyAngka(TBB));
        TTB.setDocument(new batasInput((byte) 3).getOnlyAngka(TTB));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TabIkhtisar.getSelectedIndex() == 0) {
                        tampil12();
                    } else if (TabIkhtisar.getSelectedIndex() == 1) {
                        tampil12();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TabIkhtisar.getSelectedIndex() == 0) {
                        tampil12();
                    } else if (TabIkhtisar.getSelectedIndex() == 1) {
                        tampil12();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TabIkhtisar.getSelectedIndex() == 0) {
                        tampil12();
                    } else if (TabIkhtisar.getSelectedIndex() == 1) {
                        tampil12();
                    }
                }
            });
        }
        
        pengawas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgIkhtisarPerawatanHIVart")) {
                    if (pengawas.getTable().getSelectedRow() != -1) {
                        TnmPengawas.setText(pengawas.getTable().getValueAt(pengawas.getTable().getSelectedRow(), 1).toString());
                        btnPengawas.requestFocus();                        
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
        
        rejimen.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgIkhtisarPerawatanHIVart")) {
                    if (rejimen.getTable().getSelectedRow() != -1) {
                        Tkd.setText(rejimen.getTable().getValueAt(rejimen.getTable().getSelectedRow(), 0).toString());
                        TnmRejimen.setText(rejimen.getTable().getValueAt(rejimen.getTable().getSelectedRow(), 1).toString());
                        Tkd1.setText(Tkd.getText());
                        TnmRejimen1.setText(TnmRejimen.getText());
                        btnRejimen.requestFocus();                        
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
        
        rejimen.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgIkhtisarPerawatanHIVart")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        rejimen.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("simrskhanza.DlgIkhtisarPerawatanHIVart : " + e);
        }
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        kdKLG = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        TabIkhtisar = new javax.swing.JTabbedPane();
        internalFrame10 = new widget.InternalFrame();
        panelGlass16 = new widget.panelisi();
        Scroll37 = new widget.ScrollPane();
        FormInput3 = new widget.PanelBiasa();
        btnPengawas = new widget.Button();
        TglKonfirmasiTes = new widget.Tanggal();
        cmbHubPasien = new widget.ComboBox();
        jLabel72 = new widget.Label();
        TRiwAlergiObat = new widget.TextBox();
        jLabel73 = new widget.Label();
        TnmPengawas = new widget.TextBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        TnoTelpPMO = new widget.TextBox();
        jLabel76 = new widget.Label();
        TAlamatPMO = new widget.TextBox();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        cmbEntriPoin = new widget.ComboBox();
        jLabel79 = new widget.Label();
        T2ralanlainya = new widget.TextBox();
        jLabel80 = new widget.Label();
        cmbEntriPoin2 = new widget.ComboBox();
        jLabel81 = new widget.Label();
        T5Jangkauan = new widget.TextBox();
        T8lainyaUraikan = new widget.TextBox();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        cmbEntriPoin5 = new widget.ComboBox();
        jLabel85 = new widget.Label();
        RdgART = new widget.RadioButton();
        RtnpART = new widget.RadioButton();
        jLabel86 = new widget.Label();
        cmbPndkn = new widget.ComboBox();
        jLabel87 = new widget.Label();
        TnmKlinikSblmnya = new widget.TextBox();
        jLabel88 = new widget.Label();
        TglRujukMsk = new widget.Tanggal();
        jLabel89 = new widget.Label();
        RtdkBekerja = new widget.RadioButton();
        RBekerja = new widget.RadioButton();
        jLabel90 = new widget.Label();
        T7lainUraikan = new widget.TextBox();
        cmbFaktorResiko = new widget.ComboBox();
        jLabel91 = new widget.Label();
        cmbRujukMasuk = new widget.ComboBox();
        jLabel94 = new widget.Label();
        cmbSttsNikah = new widget.ComboBox();
        jLabel97 = new widget.Label();
        cmbTerimaART = new widget.ComboBox();
        jLabel99 = new widget.Label();
        cmbJikaYA = new widget.ComboBox();
        jLabel103 = new widget.Label();
        cmbTmpART = new widget.ComboBox();
        jLabel104 = new widget.Label();
        Tkd = new widget.TextBox();
        TnmRejimen = new widget.TextBox();
        btnRejimen = new widget.Button();
        Scroll3 = new widget.ScrollPane();
        tb12 = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tb3 = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel12 = new widget.Label();
        jLabel14 = new widget.Label();
        TnmKeluarga = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbHubKeluarga = new widget.ComboBox();
        jLabel21 = new widget.Label();
        TumurKlg = new widget.TextBox();
        cmbIDumurKlg = new widget.ComboBox();
        jLabel23 = new widget.Label();
        cmbHIVklg = new widget.ComboBox();
        jLabel24 = new widget.Label();
        cmbARTklg = new widget.ComboBox();
        TnoRegNasKlg = new widget.TextBox();
        jLabel15 = new widget.Label();
        TsttsPernikahan = new widget.TextBox();
        internalFrame5 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tb4 = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel5 = new widget.Label();
        TdosisARV = new widget.TextBox();
        jLabel7 = new widget.Label();
        TlamaGuna = new widget.TextBox();
        jLabel9 = new widget.Label();
        TnmDosis = new widget.TextBox();
        jLabel18 = new widget.Label();
        TglDicatat = new widget.Tanggal();
        jLabel10 = new widget.Label();
        TpernahTerimaART = new widget.TextBox();
        TjikaYA = new widget.TextBox();
        jLabel16 = new widget.Label();
        TtempatART = new widget.TextBox();
        internalFrame8 = new widget.InternalFrame();
        Scroll10 = new widget.ScrollPane();
        tb5 = new widget.Table();
        panelGlass14 = new widget.panelisi();
        tglPemeriksaan = new widget.Tanggal();
        jLabel17 = new widget.Label();
        stadWHO = new widget.TextBox();
        jLabel26 = new widget.Label();
        bb = new widget.TextBox();
        jLabel33 = new widget.Label();
        cmbstsFung = new widget.ComboBox();
        jLabel39 = new widget.Label();
        JLHcd4 = new widget.TextBox();
        jLabel55 = new widget.Label();
        lainLain = new widget.TextBox();
        jLabel43 = new widget.Label();
        cmbPemeriksaan = new widget.ComboBox();
        jLabel34 = new widget.Label();
        internalFrame9 = new widget.InternalFrame();
        Scroll15 = new widget.ScrollPane();
        tb6 = new widget.Table();
        panelGlass15 = new widget.panelisi();
        jLabel25 = new widget.Label();
        jLabel28 = new widget.Label();
        jLabel31 = new widget.Label();
        Rsubstitusi = new widget.RadioButton();
        Rswitch = new widget.RadioButton();
        Rstop = new widget.RadioButton();
        Rrestart = new widget.RadioButton();
        TalasanSub = new widget.TextBox();
        cmbAlasanSub = new widget.ComboBox();
        TalasanRes = new widget.TextBox();
        TnmRejimenBaru = new widget.TextBox();
        jLabel32 = new widget.Label();
        tglTerapi = new widget.Tanggal();
        cmbAlasanSwi = new widget.ComboBox();
        cmbAlasanSto = new widget.ComboBox();
        jLabel29 = new widget.Label();
        Tkd1 = new widget.TextBox();
        TnmRejimen1 = new widget.TextBox();
        internalFrame11 = new widget.InternalFrame();
        Scroll16 = new widget.ScrollPane();
        tb7 = new widget.Table();
        panelGlass26 = new widget.panelisi();
        jLabel30 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        cmbKlasifikasi = new widget.ComboBox();
        cmbTipe = new widget.ComboBox();
        cmbRejimen = new widget.ComboBox();
        jLabel37 = new widget.Label();
        TtmpPengobatan = new widget.TextBox();
        TklasifikasiLokasi = new widget.TextBox();
        jLabel38 = new widget.Label();
        TKabupaten = new widget.TextBox();
        jLabel40 = new widget.Label();
        Tnmsarana = new widget.TextBox();
        jLabel44 = new widget.Label();
        TnoReg = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        tglMulaitb = new widget.Tanggal();
        tglSelesaitb = new widget.Tanggal();
        internalFrame12 = new widget.InternalFrame();
        Scroll17 = new widget.ScrollPane();
        tb8 = new widget.Table();
        panelGlass27 = new widget.panelisi();
        jLabel47 = new widget.Label();
        cmbAkhir = new widget.ComboBox();
        jLabel48 = new widget.Label();
        tglMati = new widget.Tanggal();
        jLabel49 = new widget.Label();
        tglKunAkhir = new widget.Tanggal();
        jLabel50 = new widget.Label();
        tglRujukKeluar = new widget.Tanggal();
        jLabel51 = new widget.Label();
        TnmKlinikBaru = new widget.TextBox();
        internalFrame13 = new widget.InternalFrame();
        Scroll18 = new widget.ScrollPane();
        tb9 = new widget.Table();
        panelGlass28 = new widget.panelisi();
        Scroll38 = new widget.ScrollPane();
        FormInput4 = new widget.PanelBiasa();
        jLabel82 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        jLabel95 = new widget.Label();
        jLabel96 = new widget.Label();
        jLabel98 = new widget.Label();
        jLabel100 = new widget.Label();
        TInfeksiLain = new widget.TextBox();
        jLabel101 = new widget.Label();
        jLabel102 = new widget.Label();
        RT = new widget.RadioButton();
        RY = new widget.RadioButton();
        jLabel105 = new widget.Label();
        TobatARV = new widget.TextBox();
        TglFollowup = new widget.Tanggal();
        TglRencKun = new widget.Tanggal();
        TBB = new widget.TextBox();
        jLabel110 = new widget.Label();
        TTB = new widget.TextBox();
        cmbSttsFung = new widget.ComboBox();
        TstadWHO = new widget.TextBox();
        cmbHamil = new widget.ComboBox();
        jLabel111 = new widget.Label();
        TmetodeKB = new widget.TextBox();
        cmbInfeksiOpor = new widget.ComboBox();
        jLabel112 = new widget.Label();
        TobatUtkIO = new widget.TextBox();
        cmbSttsTB = new widget.ComboBox();
        jLabel113 = new widget.Label();
        cmbAdherance = new widget.ComboBox();
        jLabel114 = new widget.Label();
        cmbEfekSamping = new widget.ComboBox();
        jLabel115 = new widget.Label();
        TEfekSampingLain = new widget.TextBox();
        jLabel116 = new widget.Label();
        TJumlahCD4 = new widget.TextBox();
        jLabel117 = new widget.Label();
        THasilLab = new widget.TextBox();
        jLabel118 = new widget.Label();
        cmbDiberiKondom = new widget.ComboBox();
        jLabel119 = new widget.Label();
        TRujukKeSpesialis = new widget.TextBox();
        jLabel120 = new widget.Label();
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
        TnoregNas = new widget.TextBox();
        jLabel70 = new widget.Label();
        Tjk = new widget.TextBox();
        TtglLahir = new widget.TextBox();
        jLabel71 = new widget.Label();

        kdKLG.setForeground(new java.awt.Color(0, 0, 0));
        kdKLG.setName("kdKLG"); // NOI18N
        kdKLG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdKLGKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ikhtisar Perawatan HIV dan Terapi Antiretroviral (ART) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabIkhtisar.setBackground(new java.awt.Color(255, 153, 255));
        TabIkhtisar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        TabIkhtisar.setForeground(new java.awt.Color(0, 0, 0));
        TabIkhtisar.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        TabIkhtisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabIkhtisar.setName("TabIkhtisar"); // NOI18N
        TabIkhtisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabIkhtisarMouseClicked(evt);
            }
        });

        internalFrame10.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame10.setBorder(null);
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 236));
        panelGlass16.setLayout(new java.awt.BorderLayout());

        Scroll37.setName("Scroll37"); // NOI18N
        Scroll37.setOpaque(true);

        FormInput3.setBackground(new java.awt.Color(255, 255, 255));
        FormInput3.setBorder(null);
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(900, 440));
        FormInput3.setLayout(null);

        btnPengawas.setForeground(new java.awt.Color(0, 0, 0));
        btnPengawas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnPengawas.setMnemonic('3');
        btnPengawas.setToolTipText("Alt+3");
        btnPengawas.setName("btnPengawas"); // NOI18N
        btnPengawas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengawasActionPerformed(evt);
            }
        });
        FormInput3.add(btnPengawas);
        btnPengawas.setBounds(660, 38, 28, 23);

        TglKonfirmasiTes.setEditable(false);
        TglKonfirmasiTes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        TglKonfirmasiTes.setDisplayFormat("dd-MM-yyyy");
        TglKonfirmasiTes.setName("TglKonfirmasiTes"); // NOI18N
        TglKonfirmasiTes.setOpaque(false);
        TglKonfirmasiTes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKonfirmasiTesKeyPressed(evt);
            }
        });
        FormInput3.add(TglKonfirmasiTes);
        TglKonfirmasiTes.setBounds(133, 122, 90, 23);

        cmbHubPasien.setForeground(new java.awt.Color(0, 0, 0));
        cmbHubPasien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Pendukung", "LSM", "Saudara", "Ayah", "Ibu", "Anak", "Suami", "Istri", "Sepupu", "Pasien Sendiri", "Paman", "Bibi", "Kakek", "Nenek", "Teman", "Tetangga", "Ipar", "Besan", "Menantu", "Mertua", "Keponakan", "Kakak", "Adik" }));
        cmbHubPasien.setName("cmbHubPasien"); // NOI18N
        cmbHubPasien.setOpaque(false);
        cmbHubPasien.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbHubPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbHubPasienKeyPressed(evt);
            }
        });
        FormInput3.add(cmbHubPasien);
        cmbHubPasien.setBounds(133, 66, 100, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Riwayat Alergi Obat : ");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput3.add(jLabel72);
        jLabel72.setBounds(0, 10, 130, 23);

        TRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        TRiwAlergiObat.setName("TRiwAlergiObat"); // NOI18N
        TRiwAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRiwAlergiObatKeyPressed(evt);
            }
        });
        FormInput3.add(TRiwAlergiObat);
        TRiwAlergiObat.setBounds(133, 10, 560, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Pengawas (PMO) : ");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput3.add(jLabel73);
        jLabel73.setBounds(0, 38, 130, 23);

        TnmPengawas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPengawas.setName("TnmPengawas"); // NOI18N
        FormInput3.add(TnmPengawas);
        TnmPengawas.setBounds(133, 38, 525, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Hubungan dg. Pasien : ");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput3.add(jLabel74);
        jLabel74.setBounds(0, 66, 130, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("No. Telpn./HP (PMO) : ");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput3.add(jLabel75);
        jLabel75.setBounds(421, 66, 130, 23);

        TnoTelpPMO.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelpPMO.setName("TnoTelpPMO"); // NOI18N
        TnoTelpPMO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelpPMOKeyPressed(evt);
            }
        });
        FormInput3.add(TnoTelpPMO);
        TnoTelpPMO.setBounds(553, 66, 140, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Alamat (PMO) : ");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput3.add(jLabel76);
        jLabel76.setBounds(0, 94, 130, 23);

        TAlamatPMO.setForeground(new java.awt.Color(0, 0, 0));
        TAlamatPMO.setName("TAlamatPMO"); // NOI18N
        TAlamatPMO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlamatPMOKeyPressed(evt);
            }
        });
        FormInput3.add(TAlamatPMO);
        TAlamatPMO.setBounds(133, 94, 560, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Tgl. Konfirmasi Tes : ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput3.add(jLabel77);
        jLabel77.setBounds(0, 122, 130, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Entry Point : ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput3.add(jLabel78);
        jLabel78.setBounds(223, 122, 80, 23);

        cmbEntriPoin.setForeground(new java.awt.Color(0, 0, 0));
        cmbEntriPoin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. KIA", "2. Rawat Jalan", "3. Rawat Inap", "4. Praktek Swasta", "5. Jangkauan", "6. LSM", "7. Datang Sendiri", "8. Lainnya" }));
        cmbEntriPoin.setName("cmbEntriPoin"); // NOI18N
        cmbEntriPoin.setOpaque(false);
        cmbEntriPoin.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbEntriPoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEntriPoinActionPerformed(evt);
            }
        });
        FormInput3.add(cmbEntriPoin);
        cmbEntriPoin.setBounds(305, 122, 120, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Entry Point 2. Rawat Jalan, Lainnya : ");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput3.add(jLabel79);
        jLabel79.setBounds(0, 178, 230, 23);

        T2ralanlainya.setForeground(new java.awt.Color(0, 0, 0));
        T2ralanlainya.setName("T2ralanlainya"); // NOI18N
        FormInput3.add(T2ralanlainya);
        T2ralanlainya.setBounds(230, 178, 460, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Entry Point 2. Rawat Jalan : ");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput3.add(jLabel80);
        jLabel80.setBounds(425, 122, 150, 23);

        cmbEntriPoin2.setForeground(new java.awt.Color(0, 0, 0));
        cmbEntriPoin2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "TB", "Anak", "Penyakit Dalam", "IMS", "Lainnya" }));
        cmbEntriPoin2.setName("cmbEntriPoin2"); // NOI18N
        cmbEntriPoin2.setOpaque(false);
        cmbEntriPoin2.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbEntriPoin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEntriPoin2ActionPerformed(evt);
            }
        });
        FormInput3.add(cmbEntriPoin2);
        cmbEntriPoin2.setBounds(578, 122, 113, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Entry Point 5. Jangkauan : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput3.add(jLabel81);
        jLabel81.setBounds(0, 206, 230, 23);

        T5Jangkauan.setForeground(new java.awt.Color(0, 0, 0));
        T5Jangkauan.setName("T5Jangkauan"); // NOI18N
        FormInput3.add(T5Jangkauan);
        T5Jangkauan.setBounds(230, 206, 460, 23);

        T8lainyaUraikan.setForeground(new java.awt.Color(0, 0, 0));
        T8lainyaUraikan.setName("T8lainyaUraikan"); // NOI18N
        FormInput3.add(T8lainyaUraikan);
        T8lainyaUraikan.setBounds(230, 234, 460, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Entry Point 8. Lainnya, Uraikan : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput3.add(jLabel83);
        jLabel83.setBounds(0, 234, 230, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Entry Point 5. Jangkauan : ");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput3.add(jLabel84);
        jLabel84.setBounds(425, 150, 150, 23);

        cmbEntriPoin5.setForeground(new java.awt.Color(0, 0, 0));
        cmbEntriPoin5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "IDU", "PSK", "LSL", "Lainnya" }));
        cmbEntriPoin5.setName("cmbEntriPoin5"); // NOI18N
        cmbEntriPoin5.setOpaque(false);
        cmbEntriPoin5.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbEntriPoin5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEntriPoin5ActionPerformed(evt);
            }
        });
        FormInput3.add(cmbEntriPoin5);
        cmbEntriPoin5.setBounds(578, 150, 113, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Pasien dirujuk masuk dari klinik lain : ");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput3.add(jLabel85);
        jLabel85.setBounds(0, 262, 230, 23);

        RdgART.setBackground(new java.awt.Color(240, 250, 230));
        RdgART.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RdgART);
        RdgART.setText("Dengan ART");
        RdgART.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RdgART.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RdgART.setName("RdgART"); // NOI18N
        RdgART.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput3.add(RdgART);
        RdgART.setBounds(390, 262, 85, 23);

        RtnpART.setBackground(new java.awt.Color(240, 250, 230));
        RtnpART.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RtnpART);
        RtnpART.setText("Tanpa ART");
        RtnpART.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RtnpART.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RtnpART.setName("RtnpART"); // NOI18N
        RtnpART.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput3.add(RtnpART);
        RtnpART.setBounds(300, 262, 80, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Pendidikan : ");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput3.add(jLabel86);
        jLabel86.setBounds(487, 262, 70, 23);

        cmbPndkn.setForeground(new java.awt.Color(0, 0, 0));
        cmbPndkn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0. Tidak Sekolah", "1. SD", "2. SMP", "3. SMU", "4. Perguruan Tinggi" }));
        cmbPndkn.setName("cmbPndkn"); // NOI18N
        cmbPndkn.setOpaque(false);
        cmbPndkn.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput3.add(cmbPndkn);
        cmbPndkn.setBounds(560, 262, 130, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Nama Klinik Sebelumnya : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput3.add(jLabel87);
        jLabel87.setBounds(0, 290, 230, 23);

        TnmKlinikSblmnya.setForeground(new java.awt.Color(0, 0, 0));
        TnmKlinikSblmnya.setName("TnmKlinikSblmnya"); // NOI18N
        FormInput3.add(TnmKlinikSblmnya);
        TnmKlinikSblmnya.setBounds(230, 290, 460, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Tgl. Rujuk Msk : ");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput3.add(jLabel88);
        jLabel88.setBounds(510, 318, 90, 23);

        TglRujukMsk.setEditable(false);
        TglRujukMsk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        TglRujukMsk.setDisplayFormat("dd-MM-yyyy");
        TglRujukMsk.setName("TglRujukMsk"); // NOI18N
        TglRujukMsk.setOpaque(false);
        TglRujukMsk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglRujukMskKeyPressed(evt);
            }
        });
        FormInput3.add(TglRujukMsk);
        TglRujukMsk.setBounds(600, 318, 90, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Status Pekerjaan : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput3.add(jLabel89);
        jLabel89.setBounds(0, 318, 230, 23);

        RtdkBekerja.setBackground(new java.awt.Color(240, 250, 230));
        RtdkBekerja.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(RtdkBekerja);
        RtdkBekerja.setText("Tidak Bekerja");
        RtdkBekerja.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RtdkBekerja.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RtdkBekerja.setName("RtdkBekerja"); // NOI18N
        RtdkBekerja.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput3.add(RtdkBekerja);
        RtdkBekerja.setBounds(230, 318, 100, 23);

        RBekerja.setBackground(new java.awt.Color(240, 250, 230));
        RBekerja.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(RBekerja);
        RBekerja.setText("Bekerja");
        RBekerja.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RBekerja.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RBekerja.setName("RBekerja"); // NOI18N
        RBekerja.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput3.add(RBekerja);
        RBekerja.setBounds(340, 318, 70, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Faktor Resiko : ");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput3.add(jLabel90);
        jLabel90.setBounds(0, 346, 130, 23);

        T7lainUraikan.setForeground(new java.awt.Color(0, 0, 0));
        T7lainUraikan.setName("T7lainUraikan"); // NOI18N
        FormInput3.add(T7lainUraikan);
        T7lainUraikan.setBounds(395, 346, 295, 23);

        cmbFaktorResiko.setForeground(new java.awt.Color(0, 0, 0));
        cmbFaktorResiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Heteroseksual", "2. Homoseksual", "3. Biseksual", "4. Perinatal", "5. Transfusi Darah", "6. NAPZA Suntik", "7. Lain-lain" }));
        cmbFaktorResiko.setName("cmbFaktorResiko"); // NOI18N
        cmbFaktorResiko.setOpaque(false);
        cmbFaktorResiko.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbFaktorResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFaktorResikoActionPerformed(evt);
            }
        });
        FormInput3.add(cmbFaktorResiko);
        cmbFaktorResiko.setBounds(133, 346, 120, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Faktor Resiko, 7. Lain2 : ");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput3.add(jLabel91);
        jLabel91.setBounds(253, 346, 140, 23);

        cmbRujukMasuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbRujukMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbRujukMasuk.setName("cmbRujukMasuk"); // NOI18N
        cmbRujukMasuk.setOpaque(false);
        cmbRujukMasuk.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbRujukMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRujukMasukActionPerformed(evt);
            }
        });
        FormInput3.add(cmbRujukMasuk);
        cmbRujukMasuk.setBounds(230, 262, 60, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Status Pernikahan : ");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput3.add(jLabel94);
        jLabel94.setBounds(0, 150, 130, 23);

        cmbSttsNikah.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsNikah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Menikah", "Belum Menikah", "Janda/Duda" }));
        cmbSttsNikah.setName("cmbSttsNikah"); // NOI18N
        cmbSttsNikah.setOpaque(false);
        cmbSttsNikah.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput3.add(cmbSttsNikah);
        cmbSttsNikah.setBounds(133, 150, 120, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Pernah Menerima ART : ");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput3.add(jLabel97);
        jLabel97.setBounds(0, 374, 130, 23);

        cmbTerimaART.setForeground(new java.awt.Color(0, 0, 0));
        cmbTerimaART.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbTerimaART.setSelectedIndex(1);
        cmbTerimaART.setName("cmbTerimaART"); // NOI18N
        cmbTerimaART.setOpaque(false);
        cmbTerimaART.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbTerimaART.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTerimaARTActionPerformed(evt);
            }
        });
        FormInput3.add(cmbTerimaART);
        cmbTerimaART.setBounds(133, 374, 60, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Jika, Ya : ");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput3.add(jLabel99);
        jLabel99.setBounds(194, 374, 60, 23);

        cmbJikaYA.setForeground(new java.awt.Color(0, 0, 0));
        cmbJikaYA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. PMTCT", "2. ART", "3. PPP" }));
        cmbJikaYA.setName("cmbJikaYA"); // NOI18N
        cmbJikaYA.setOpaque(false);
        cmbJikaYA.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput3.add(cmbJikaYA);
        cmbJikaYA.setBounds(256, 374, 80, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Tempat ART dulu : ");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput3.add(jLabel103);
        jLabel103.setBounds(460, 374, 110, 23);

        cmbTmpART.setForeground(new java.awt.Color(0, 0, 0));
        cmbTmpART.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. RS Pemerintah", "2. RS Swasta", "3. PKM" }));
        cmbTmpART.setName("cmbTmpART"); // NOI18N
        cmbTmpART.setOpaque(false);
        cmbTmpART.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput3.add(cmbTmpART);
        cmbTmpART.setBounds(570, 374, 120, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Rejimen ART Orisinal : ");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput3.add(jLabel104);
        jLabel104.setBounds(0, 402, 130, 23);

        Tkd.setEditable(false);
        Tkd.setForeground(new java.awt.Color(0, 0, 0));
        Tkd.setName("Tkd"); // NOI18N
        FormInput3.add(Tkd);
        Tkd.setBounds(133, 402, 58, 23);

        TnmRejimen.setEditable(false);
        TnmRejimen.setForeground(new java.awt.Color(0, 0, 0));
        TnmRejimen.setName("TnmRejimen"); // NOI18N
        FormInput3.add(TnmRejimen);
        TnmRejimen.setBounds(195, 402, 217, 23);

        btnRejimen.setForeground(new java.awt.Color(0, 0, 0));
        btnRejimen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnRejimen.setMnemonic('3');
        btnRejimen.setToolTipText("Alt+3");
        btnRejimen.setName("btnRejimen"); // NOI18N
        btnRejimen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejimenActionPerformed(evt);
            }
        });
        FormInput3.add(btnRejimen);
        btnRejimen.setBounds(413, 402, 28, 23);

        Scroll37.setViewportView(FormInput3);

        panelGlass16.add(Scroll37, java.awt.BorderLayout.CENTER);

        internalFrame10.add(panelGlass16, java.awt.BorderLayout.PAGE_START);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 401));

        tb12.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tb12.setName("tb12"); // NOI18N
        tb12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb12MouseClicked(evt);
            }
        });
        tb12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb12KeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tb12);

        internalFrame10.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabIkhtisar.addTab("1. Data Identitas Pasien & 2. Riwayat Pribadi", internalFrame10);

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

        tb3.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tb3.setName("tb3"); // NOI18N
        tb3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb3MouseClicked(evt);
            }
        });
        tb3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb3KeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tb3);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 108));
        panelGlass11.setLayout(null);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("No. Reg. Nasional : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass11.add(jLabel12);
        jLabel12.setBounds(0, 38, 120, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(0, 66, 120, 23);

        TnmKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        TnmKeluarga.setName("TnmKeluarga"); // NOI18N
        TnmKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKeluargaKeyPressed(evt);
            }
        });
        panelGlass11.add(TnmKeluarga);
        TnmKeluarga.setBounds(123, 66, 292, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Hubungan : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass11.add(jLabel13);
        jLabel13.setBounds(234, 38, 75, 23);

        cmbHubKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        cmbHubKeluarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Saudara", "Ayah", "Ibu", "Anak", "Suami", "Istri", "Sepupu", "Pasien Sendiri", "Paman", "Bibi", "Kakek", "Nenek", "Teman", "Tetangga", "Ipar", "Besan", "Menantu", "Mertua", "Keponakan", "Kakak", "Adik" }));
        cmbHubKeluarga.setName("cmbHubKeluarga"); // NOI18N
        cmbHubKeluarga.setOpaque(false);
        cmbHubKeluarga.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbHubKeluarga);
        cmbHubKeluarga.setBounds(311, 38, 105, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Umur : ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelGlass11.add(jLabel21);
        jLabel21.setBounds(415, 66, 50, 23);

        TumurKlg.setForeground(new java.awt.Color(0, 0, 0));
        TumurKlg.setName("TumurKlg"); // NOI18N
        TumurKlg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurKlgKeyPressed(evt);
            }
        });
        panelGlass11.add(TumurKlg);
        TumurKlg.setBounds(467, 66, 50, 23);

        cmbIDumurKlg.setForeground(new java.awt.Color(0, 0, 0));
        cmbIDumurKlg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Th", "Bl", "Hr" }));
        cmbIDumurKlg.setName("cmbIDumurKlg"); // NOI18N
        cmbIDumurKlg.setOpaque(false);
        cmbIDumurKlg.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbIDumurKlg);
        cmbIDumurKlg.setBounds(523, 66, 50, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("HIV : ");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass11.add(jLabel23);
        jLabel23.setBounds(576, 38, 50, 23);

        cmbHIVklg.setForeground(new java.awt.Color(0, 0, 0));
        cmbHIVklg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
        cmbHIVklg.setName("cmbHIVklg"); // NOI18N
        cmbHIVklg.setOpaque(false);
        cmbHIVklg.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbHIVklg);
        cmbHIVklg.setBounds(628, 38, 45, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("ART : ");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass11.add(jLabel24);
        jLabel24.setBounds(576, 66, 50, 23);

        cmbARTklg.setForeground(new java.awt.Color(0, 0, 0));
        cmbARTklg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "tdk" }));
        cmbARTklg.setName("cmbARTklg"); // NOI18N
        cmbARTklg.setOpaque(false);
        cmbARTklg.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbARTklg);
        cmbARTklg.setBounds(628, 66, 52, 23);

        TnoRegNasKlg.setForeground(new java.awt.Color(0, 0, 0));
        TnoRegNasKlg.setName("TnoRegNasKlg"); // NOI18N
        TnoRegNasKlg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoRegNasKlgKeyPressed(evt);
            }
        });
        panelGlass11.add(TnoRegNasKlg);
        TnoRegNasKlg.setBounds(123, 38, 110, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Status Pernikahan : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass11.add(jLabel15);
        jLabel15.setBounds(0, 10, 120, 23);

        TsttsPernikahan.setEditable(false);
        TsttsPernikahan.setForeground(new java.awt.Color(0, 0, 0));
        TsttsPernikahan.setName("TsttsPernikahan"); // NOI18N
        panelGlass11.add(TsttsPernikahan);
        TsttsPernikahan.setBounds(123, 10, 190, 23);

        internalFrame4.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        TabIkhtisar.addTab("3. Riwayat Keluarga", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(452, 311));

        tb4.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tb4.setName("tb4"); // NOI18N
        tb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb4MouseClicked(evt);
            }
        });
        tb4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb4KeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tb4);

        internalFrame5.add(Scroll4, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 100));
        panelGlass12.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Dosis ARV : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass12.add(jLabel5);
        jLabel5.setBounds(0, 38, 140, 23);

        TdosisARV.setForeground(new java.awt.Color(0, 0, 0));
        TdosisARV.setName("TdosisARV"); // NOI18N
        TdosisARV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisARVKeyPressed(evt);
            }
        });
        panelGlass12.add(TdosisARV);
        TdosisARV.setBounds(143, 38, 230, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Lama Penggunaanya : ");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass12.add(jLabel7);
        jLabel7.setBounds(0, 66, 140, 23);

        TlamaGuna.setForeground(new java.awt.Color(0, 0, 0));
        TlamaGuna.setName("TlamaGuna"); // NOI18N
        panelGlass12.add(TlamaGuna);
        TlamaGuna.setBounds(143, 66, 230, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass12.add(jLabel9);
        jLabel9.setBounds(0, 10, 140, 23);

        TnmDosis.setForeground(new java.awt.Color(0, 0, 0));
        TnmDosis.setName("TnmDosis"); // NOI18N
        TnmDosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmDosisKeyPressed(evt);
            }
        });
        panelGlass12.add(TnmDosis);
        TnmDosis.setBounds(143, 10, 230, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Dicatat Tgl. : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass12.add(jLabel18);
        jLabel18.setBounds(375, 66, 140, 23);

        TglDicatat.setEditable(false);
        TglDicatat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        TglDicatat.setDisplayFormat("dd-MM-yyyy");
        TglDicatat.setName("TglDicatat"); // NOI18N
        TglDicatat.setOpaque(false);
        panelGlass12.add(TglDicatat);
        TglDicatat.setBounds(517, 66, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pernah Menerima ART : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass12.add(jLabel10);
        jLabel10.setBounds(375, 10, 140, 23);

        TpernahTerimaART.setEditable(false);
        TpernahTerimaART.setForeground(new java.awt.Color(0, 0, 0));
        TpernahTerimaART.setName("TpernahTerimaART"); // NOI18N
        panelGlass12.add(TpernahTerimaART);
        TpernahTerimaART.setBounds(517, 10, 60, 23);

        TjikaYA.setEditable(false);
        TjikaYA.setForeground(new java.awt.Color(0, 0, 0));
        TjikaYA.setName("TjikaYA"); // NOI18N
        panelGlass12.add(TjikaYA);
        TjikaYA.setBounds(581, 10, 110, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Tempat ART dulu : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass12.add(jLabel16);
        jLabel16.setBounds(375, 38, 140, 23);

        TtempatART.setEditable(false);
        TtempatART.setForeground(new java.awt.Color(0, 0, 0));
        TtempatART.setName("TtempatART"); // NOI18N
        panelGlass12.add(TtempatART);
        TtempatART.setBounds(517, 38, 173, 23);

        internalFrame5.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        TabIkhtisar.addTab("4. Riwayat Terapi Antiretroviral", internalFrame5);

        internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tb5.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tb5.setName("tb5"); // NOI18N
        tb5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb5MouseClicked(evt);
            }
        });
        tb5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb5KeyPressed(evt);
            }
        });
        Scroll10.setViewportView(tb5);

        internalFrame8.add(Scroll10, java.awt.BorderLayout.CENTER);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 106));
        panelGlass14.setLayout(null);

        tglPemeriksaan.setEditable(false);
        tglPemeriksaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        tglPemeriksaan.setDisplayFormat("dd-MM-yyyy");
        tglPemeriksaan.setName("tglPemeriksaan"); // NOI18N
        tglPemeriksaan.setOpaque(false);
        panelGlass14.add(tglPemeriksaan);
        tglPemeriksaan.setBounds(415, 10, 90, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Stad WHO : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass14.add(jLabel17);
        jLabel17.setBounds(0, 38, 100, 23);

        stadWHO.setForeground(new java.awt.Color(0, 0, 0));
        stadWHO.setName("stadWHO"); // NOI18N
        stadWHO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stadWHOKeyPressed(evt);
            }
        });
        panelGlass14.add(stadWHO);
        stadWHO.setBounds(102, 38, 110, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("BB : ");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass14.add(jLabel26);
        jLabel26.setBounds(0, 66, 100, 23);

        bb.setForeground(new java.awt.Color(0, 0, 0));
        bb.setName("bb"); // NOI18N
        bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbKeyPressed(evt);
            }
        });
        panelGlass14.add(bb);
        bb.setBounds(102, 66, 80, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Status Fungsional : ");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass14.add(jLabel33);
        jLabel33.setBounds(302, 38, 110, 23);

        cmbstsFung.setForeground(new java.awt.Color(0, 0, 0));
        cmbstsFung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1 = Kerja", "2 = Ambulatori", "3 = Baring" }));
        cmbstsFung.setName("cmbstsFung"); // NOI18N
        cmbstsFung.setOpaque(false);
        cmbstsFung.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass14.add(cmbstsFung);
        cmbstsFung.setBounds(415, 38, 105, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Jumlah Cd4 : ");
        jLabel39.setName("jLabel39"); // NOI18N
        panelGlass14.add(jLabel39);
        jLabel39.setBounds(302, 66, 110, 23);

        JLHcd4.setForeground(new java.awt.Color(0, 0, 0));
        JLHcd4.setName("JLHcd4"); // NOI18N
        JLHcd4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JLHcd4KeyPressed(evt);
            }
        });
        panelGlass14.add(JLHcd4);
        JLHcd4.setBounds(415, 66, 80, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Lain-lain : ");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass14.add(jLabel55);
        jLabel55.setBounds(522, 66, 70, 23);

        lainLain.setForeground(new java.awt.Color(0, 0, 0));
        lainLain.setName("lainLain"); // NOI18N
        lainLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lainLainKeyPressed(evt);
            }
        });
        panelGlass14.add(lainLain);
        lainLain.setBounds(595, 66, 290, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Pemeriksaan : ");
        jLabel43.setName("jLabel43"); // NOI18N
        panelGlass14.add(jLabel43);
        jLabel43.setBounds(0, 10, 100, 23);

        cmbPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPemeriksaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kunjungan pertama", "Memenuhi syarat medis untuk ART", "Saat mulai ART", "Setelah 6 bulan ART", "Setelah 12 bulan ART", "Setelah 24 bulan ART" }));
        cmbPemeriksaan.setName("cmbPemeriksaan"); // NOI18N
        cmbPemeriksaan.setOpaque(false);
        cmbPemeriksaan.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass14.add(cmbPemeriksaan);
        cmbPemeriksaan.setBounds(102, 10, 200, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Tanggal : ");
        jLabel34.setName("jLabel34"); // NOI18N
        panelGlass14.add(jLabel34);
        jLabel34.setBounds(302, 10, 110, 23);

        internalFrame8.add(panelGlass14, java.awt.BorderLayout.PAGE_START);

        TabIkhtisar.addTab("5. Pemeriksaan Klinis dan Lab.", internalFrame8);

        internalFrame9.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll15.setName("Scroll15"); // NOI18N
        Scroll15.setOpaque(true);

        tb6.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tb6.setName("tb6"); // NOI18N
        tb6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb6MouseClicked(evt);
            }
        });
        tb6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb6KeyPressed(evt);
            }
        });
        Scroll15.setViewportView(tb6);

        internalFrame9.add(Scroll15, java.awt.BorderLayout.CENTER);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 160));
        panelGlass15.setLayout(null);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Jenis Terapi ART : ");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass15.add(jLabel25);
        jLabel25.setBounds(0, 38, 160, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Alasan Lain Substitusi : ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass15.add(jLabel28);
        jLabel28.setBounds(475, 38, 130, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Nama Rejimen Baru : ");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass15.add(jLabel31);
        jLabel31.setBounds(475, 66, 130, 23);

        Rsubstitusi.setBackground(new java.awt.Color(240, 250, 230));
        Rsubstitusi.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup3.add(Rsubstitusi);
        Rsubstitusi.setText("Substitusi");
        Rsubstitusi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Rsubstitusi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Rsubstitusi.setName("Rsubstitusi"); // NOI18N
        Rsubstitusi.setPreferredSize(new java.awt.Dimension(90, 23));
        Rsubstitusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RsubstitusiActionPerformed(evt);
            }
        });
        panelGlass15.add(Rsubstitusi);
        Rsubstitusi.setBounds(162, 38, 75, 23);

        Rswitch.setBackground(new java.awt.Color(240, 250, 230));
        Rswitch.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup3.add(Rswitch);
        Rswitch.setText("Switch");
        Rswitch.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Rswitch.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Rswitch.setName("Rswitch"); // NOI18N
        Rswitch.setPreferredSize(new java.awt.Dimension(90, 23));
        Rswitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RswitchActionPerformed(evt);
            }
        });
        panelGlass15.add(Rswitch);
        Rswitch.setBounds(162, 66, 75, 23);

        Rstop.setBackground(new java.awt.Color(240, 250, 230));
        Rstop.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup3.add(Rstop);
        Rstop.setText("Stop");
        Rstop.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Rstop.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Rstop.setName("Rstop"); // NOI18N
        Rstop.setPreferredSize(new java.awt.Dimension(90, 23));
        Rstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RstopActionPerformed(evt);
            }
        });
        panelGlass15.add(Rstop);
        Rstop.setBounds(162, 94, 75, 23);

        Rrestart.setBackground(new java.awt.Color(240, 250, 230));
        Rrestart.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup3.add(Rrestart);
        Rrestart.setText("Restart");
        Rrestart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Rrestart.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Rrestart.setName("Rrestart"); // NOI18N
        Rrestart.setPreferredSize(new java.awt.Dimension(90, 23));
        Rrestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RrestartActionPerformed(evt);
            }
        });
        panelGlass15.add(Rrestart);
        Rrestart.setBounds(162, 122, 75, 23);

        TalasanSub.setForeground(new java.awt.Color(0, 0, 0));
        TalasanSub.setName("TalasanSub"); // NOI18N
        TalasanSub.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanSubKeyPressed(evt);
            }
        });
        panelGlass15.add(TalasanSub);
        TalasanSub.setBounds(607, 38, 270, 23);

        cmbAlasanSub.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlasanSub.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Toksisitas/efek samping", "2. Hamil", "3. Resiko Hamil", "4. TB baru", "5. Ada obat baru", "6. Stok obat habis", "7. Alasan lain (uraikan)" }));
        cmbAlasanSub.setName("cmbAlasanSub"); // NOI18N
        cmbAlasanSub.setOpaque(false);
        cmbAlasanSub.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbAlasanSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlasanSubActionPerformed(evt);
            }
        });
        panelGlass15.add(cmbAlasanSub);
        cmbAlasanSub.setBounds(248, 38, 200, 23);

        TalasanRes.setForeground(new java.awt.Color(0, 0, 0));
        TalasanRes.setName("TalasanRes"); // NOI18N
        TalasanRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanResKeyPressed(evt);
            }
        });
        panelGlass15.add(TalasanRes);
        TalasanRes.setBounds(248, 122, 300, 23);

        TnmRejimenBaru.setForeground(new java.awt.Color(0, 0, 0));
        TnmRejimenBaru.setName("TnmRejimenBaru"); // NOI18N
        TnmRejimenBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmRejimenBaruKeyPressed(evt);
            }
        });
        panelGlass15.add(TnmRejimenBaru);
        TnmRejimenBaru.setBounds(607, 66, 270, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Tgl. Terapi : ");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass15.add(jLabel32);
        jLabel32.setBounds(475, 10, 130, 23);

        tglTerapi.setEditable(false);
        tglTerapi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        tglTerapi.setDisplayFormat("dd-MM-yyyy");
        tglTerapi.setName("tglTerapi"); // NOI18N
        tglTerapi.setOpaque(false);
        panelGlass15.add(tglTerapi);
        tglTerapi.setBounds(607, 10, 90, 23);

        cmbAlasanSwi.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlasanSwi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "8. Gagal pengobatan secara linis", "9. Gagal imunologis", "10. Gagal virologis" }));
        cmbAlasanSwi.setName("cmbAlasanSwi"); // NOI18N
        cmbAlasanSwi.setOpaque(false);
        cmbAlasanSwi.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass15.add(cmbAlasanSwi);
        cmbAlasanSwi.setBounds(248, 66, 200, 23);

        cmbAlasanSto.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlasanSto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Toksisitas/efek samping", "2. Hamil", "3. Gagal pengobatan", "4. Adherene buruk", "5. Sakit/MRS", "6. Stok obat habis", "7. Kekurangan biaya", "8. Keputusan pasien lainnya", "9. Lain-lain" }));
        cmbAlasanSto.setName("cmbAlasanSto"); // NOI18N
        cmbAlasanSto.setOpaque(false);
        cmbAlasanSto.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass15.add(cmbAlasanSto);
        cmbAlasanSto.setBounds(248, 94, 200, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Nama Rejimen ART Orisinal : ");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass15.add(jLabel29);
        jLabel29.setBounds(0, 10, 160, 23);

        Tkd1.setEditable(false);
        Tkd1.setForeground(new java.awt.Color(0, 0, 0));
        Tkd1.setName("Tkd1"); // NOI18N
        panelGlass15.add(Tkd1);
        Tkd1.setBounds(162, 10, 58, 23);

        TnmRejimen1.setEditable(false);
        TnmRejimen1.setForeground(new java.awt.Color(0, 0, 0));
        TnmRejimen1.setName("TnmRejimen1"); // NOI18N
        panelGlass15.add(TnmRejimen1);
        TnmRejimen1.setBounds(223, 10, 217, 23);

        internalFrame9.add(panelGlass15, java.awt.BorderLayout.PAGE_START);

        TabIkhtisar.addTab("6. Terapi Antiretroviral (ART)", internalFrame9);

        internalFrame11.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll16.setName("Scroll16"); // NOI18N
        Scroll16.setOpaque(true);

        tb7.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tb7.setName("tb7"); // NOI18N
        tb7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb7MouseClicked(evt);
            }
        });
        tb7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb7KeyPressed(evt);
            }
        });
        Scroll16.setViewportView(tb7);

        internalFrame11.add(Scroll16, java.awt.BorderLayout.CENTER);

        panelGlass26.setName("panelGlass26"); // NOI18N
        panelGlass26.setPreferredSize(new java.awt.Dimension(44, 160));
        panelGlass26.setLayout(null);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Klasifikasi TB : ");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass26.add(jLabel30);
        jLabel30.setBounds(0, 10, 140, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tipe TB : ");
        jLabel35.setName("jLabel35"); // NOI18N
        panelGlass26.add(jLabel35);
        jLabel35.setBounds(0, 38, 140, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Rejimen TB : ");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass26.add(jLabel36);
        jLabel36.setBounds(0, 66, 140, 23);

        cmbKlasifikasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKlasifikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. TB Paru", "2. TB Ekstra paru lokasi" }));
        cmbKlasifikasi.setName("cmbKlasifikasi"); // NOI18N
        cmbKlasifikasi.setOpaque(false);
        cmbKlasifikasi.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbKlasifikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKlasifikasiActionPerformed(evt);
            }
        });
        panelGlass26.add(cmbKlasifikasi);
        cmbKlasifikasi.setBounds(142, 10, 150, 23);

        cmbTipe.setForeground(new java.awt.Color(0, 0, 0));
        cmbTipe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Baru", "2. Kambuh", "3. Default", "4. Gagal" }));
        cmbTipe.setName("cmbTipe"); // NOI18N
        cmbTipe.setOpaque(false);
        cmbTipe.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass26.add(cmbTipe);
        cmbTipe.setBounds(142, 38, 85, 23);

        cmbRejimen.setForeground(new java.awt.Color(0, 0, 0));
        cmbRejimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Kategori I", "2. Kategori II", "3. Kategori anak", "4. OAT lini 2 MDR" }));
        cmbRejimen.setName("cmbRejimen"); // NOI18N
        cmbRejimen.setOpaque(false);
        cmbRejimen.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass26.add(cmbRejimen);
        cmbRejimen.setBounds(142, 66, 120, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Tempat Pengobatan TB : ");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass26.add(jLabel37);
        jLabel37.setBounds(297, 38, 160, 23);

        TtmpPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        TtmpPengobatan.setName("TtmpPengobatan"); // NOI18N
        TtmpPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtmpPengobatanKeyPressed(evt);
            }
        });
        panelGlass26.add(TtmpPengobatan);
        TtmpPengobatan.setBounds(458, 38, 217, 23);

        TklasifikasiLokasi.setForeground(new java.awt.Color(0, 0, 0));
        TklasifikasiLokasi.setName("TklasifikasiLokasi"); // NOI18N
        TklasifikasiLokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TklasifikasiLokasiKeyPressed(evt);
            }
        });
        panelGlass26.add(TklasifikasiLokasi);
        TklasifikasiLokasi.setBounds(297, 10, 378, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Kabupaten : ");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass26.add(jLabel38);
        jLabel38.setBounds(297, 66, 160, 23);

        TKabupaten.setForeground(new java.awt.Color(0, 0, 0));
        TKabupaten.setName("TKabupaten"); // NOI18N
        TKabupaten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKabupatenKeyPressed(evt);
            }
        });
        panelGlass26.add(TKabupaten);
        TKabupaten.setBounds(458, 66, 217, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Nama Sarana Kesehatan : ");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass26.add(jLabel40);
        jLabel40.setBounds(297, 94, 160, 23);

        Tnmsarana.setForeground(new java.awt.Color(0, 0, 0));
        Tnmsarana.setName("Tnmsarana"); // NOI18N
        Tnmsarana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmsaranaKeyPressed(evt);
            }
        });
        panelGlass26.add(Tnmsarana);
        Tnmsarana.setBounds(458, 94, 217, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("No. Reg. TB Kabupaten/Kota : ");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass26.add(jLabel44);
        jLabel44.setBounds(297, 122, 160, 23);

        TnoReg.setForeground(new java.awt.Color(0, 0, 0));
        TnoReg.setName("TnoReg"); // NOI18N
        TnoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoRegKeyPressed(evt);
            }
        });
        panelGlass26.add(TnoReg);
        TnoReg.setBounds(458, 122, 217, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Tgl. Mulai Terapi TB : ");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass26.add(jLabel45);
        jLabel45.setBounds(0, 94, 140, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Tgl. Selesai Terapi TB : ");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass26.add(jLabel46);
        jLabel46.setBounds(0, 122, 140, 23);

        tglMulaitb.setEditable(false);
        tglMulaitb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        tglMulaitb.setDisplayFormat("dd-MM-yyyy");
        tglMulaitb.setName("tglMulaitb"); // NOI18N
        tglMulaitb.setOpaque(false);
        panelGlass26.add(tglMulaitb);
        tglMulaitb.setBounds(142, 94, 90, 23);

        tglSelesaitb.setEditable(false);
        tglSelesaitb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        tglSelesaitb.setDisplayFormat("dd-MM-yyyy");
        tglSelesaitb.setName("tglSelesaitb"); // NOI18N
        tglSelesaitb.setOpaque(false);
        panelGlass26.add(tglSelesaitb);
        tglSelesaitb.setBounds(142, 122, 90, 23);

        internalFrame11.add(panelGlass26, java.awt.BorderLayout.PAGE_START);

        TabIkhtisar.addTab("7. Pengobatan TB selama perawatan HIV", internalFrame11);

        internalFrame12.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame12.setBorder(null);
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);

        tb8.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tb8.setName("tb8"); // NOI18N
        tb8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb8MouseClicked(evt);
            }
        });
        tb8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb8KeyPressed(evt);
            }
        });
        Scroll17.setViewportView(tb8);

        internalFrame12.add(Scroll17, java.awt.BorderLayout.CENTER);

        panelGlass27.setName("panelGlass27"); // NOI18N
        panelGlass27.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass27.setLayout(null);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Akhir Follow-UP : ");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass27.add(jLabel47);
        jLabel47.setBounds(0, 10, 150, 23);

        cmbAkhir.setForeground(new java.awt.Color(0, 0, 0));
        cmbAkhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Meninggal dunia", "Gagal follow-up (>3 bulan)", "Rujuk keluar" }));
        cmbAkhir.setName("cmbAkhir"); // NOI18N
        cmbAkhir.setOpaque(false);
        cmbAkhir.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbAkhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAkhirActionPerformed(evt);
            }
        });
        panelGlass27.add(cmbAkhir);
        cmbAkhir.setBounds(154, 10, 160, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Tgl. Meninggal Dunia : ");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass27.add(jLabel48);
        jLabel48.setBounds(0, 38, 150, 23);

        tglMati.setEditable(false);
        tglMati.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        tglMati.setDisplayFormat("dd-MM-yyyy");
        tglMati.setName("tglMati"); // NOI18N
        tglMati.setOpaque(false);
        panelGlass27.add(tglMati);
        tglMati.setBounds(154, 38, 90, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Tgl. Kunjungan Terakhir : ");
        jLabel49.setName("jLabel49"); // NOI18N
        panelGlass27.add(jLabel49);
        jLabel49.setBounds(0, 66, 150, 23);

        tglKunAkhir.setEditable(false);
        tglKunAkhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        tglKunAkhir.setDisplayFormat("dd-MM-yyyy");
        tglKunAkhir.setName("tglKunAkhir"); // NOI18N
        tglKunAkhir.setOpaque(false);
        panelGlass27.add(tglKunAkhir);
        tglKunAkhir.setBounds(154, 66, 90, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Tgl. Rujuk Keluar : ");
        jLabel50.setName("jLabel50"); // NOI18N
        panelGlass27.add(jLabel50);
        jLabel50.setBounds(0, 94, 150, 23);

        tglRujukKeluar.setEditable(false);
        tglRujukKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        tglRujukKeluar.setDisplayFormat("dd-MM-yyyy");
        tglRujukKeluar.setName("tglRujukKeluar"); // NOI18N
        tglRujukKeluar.setOpaque(false);
        panelGlass27.add(tglRujukKeluar);
        tglRujukKeluar.setBounds(154, 94, 90, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Klinik : baru : ");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass27.add(jLabel51);
        jLabel51.setBounds(245, 94, 90, 23);

        TnmKlinikBaru.setForeground(new java.awt.Color(0, 0, 0));
        TnmKlinikBaru.setName("TnmKlinikBaru"); // NOI18N
        panelGlass27.add(TnmKlinikBaru);
        TnmKlinikBaru.setBounds(337, 94, 217, 23);

        internalFrame12.add(panelGlass27, java.awt.BorderLayout.PAGE_START);

        TabIkhtisar.addTab("8. Akhir Follow-Up", internalFrame12);

        internalFrame13.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame13.setBorder(null);
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll18.setName("Scroll18"); // NOI18N
        Scroll18.setOpaque(true);

        tb9.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tb9.setName("tb9"); // NOI18N
        tb9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb9MouseClicked(evt);
            }
        });
        tb9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb9KeyPressed(evt);
            }
        });
        Scroll18.setViewportView(tb9);

        internalFrame13.add(Scroll18, java.awt.BorderLayout.CENTER);

        panelGlass28.setName("panelGlass28"); // NOI18N
        panelGlass28.setPreferredSize(new java.awt.Dimension(44, 255));
        panelGlass28.setLayout(new java.awt.BorderLayout());

        Scroll38.setName("Scroll38"); // NOI18N
        Scroll38.setOpaque(true);

        FormInput4.setBackground(new java.awt.Color(255, 255, 255));
        FormInput4.setBorder(null);
        FormInput4.setName("FormInput4"); // NOI18N
        FormInput4.setPreferredSize(new java.awt.Dimension(974, 270));
        FormInput4.setLayout(null);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Tgl. Follow-UP : ");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput4.add(jLabel82);
        jLabel82.setBounds(0, 10, 130, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Rencana Tgl. Kunjgn. : ");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput4.add(jLabel92);
        jLabel92.setBounds(0, 38, 130, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("BB (Kg.) : ");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput4.add(jLabel93);
        jLabel93.setBounds(0, 66, 130, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Status Fungsional : ");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput4.add(jLabel95);
        jLabel95.setBounds(0, 94, 130, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Stad WHO : ");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput4.add(jLabel96);
        jLabel96.setBounds(0, 122, 130, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Infeksi Oportunistik : ");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput4.add(jLabel98);
        jLabel98.setBounds(0, 178, 130, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Obat Untuk IO : ");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput4.add(jLabel100);
        jLabel100.setBounds(0, 206, 130, 23);

        TInfeksiLain.setForeground(new java.awt.Color(0, 0, 0));
        TInfeksiLain.setName("TInfeksiLain"); // NOI18N
        TInfeksiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInfeksiLainKeyPressed(evt);
            }
        });
        FormInput4.add(TInfeksiLain);
        TInfeksiLain.setBounds(314, 178, 218, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Stts. TB (Skerining TB) : ");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput4.add(jLabel101);
        jLabel101.setBounds(0, 234, 130, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Hamil : ");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput4.add(jLabel102);
        jLabel102.setBounds(0, 150, 130, 23);

        RT.setBackground(new java.awt.Color(240, 250, 230));
        RT.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup4.add(RT);
        RT.setText("T");
        RT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RT.setName("RT"); // NOI18N
        RT.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput4.add(RT);
        RT.setBounds(768, 234, 40, 23);

        RY.setBackground(new java.awt.Color(240, 250, 230));
        RY.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup4.add(RY);
        RY.setSelected(true);
        RY.setText("Y");
        RY.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RY.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RY.setName("RY"); // NOI18N
        RY.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput4.add(RY);
        RY.setBounds(717, 234, 40, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Obat ARV & Dosis yg. Diberikan : ");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput4.add(jLabel105);
        jLabel105.setBounds(534, 10, 180, 23);

        TobatARV.setForeground(new java.awt.Color(0, 0, 0));
        TobatARV.setName("TobatARV"); // NOI18N
        TobatARV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatARVKeyPressed(evt);
            }
        });
        FormInput4.add(TobatARV);
        TobatARV.setBounds(717, 10, 240, 23);

        TglFollowup.setEditable(false);
        TglFollowup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        TglFollowup.setDisplayFormat("dd-MM-yyyy");
        TglFollowup.setName("TglFollowup"); // NOI18N
        TglFollowup.setOpaque(false);
        FormInput4.add(TglFollowup);
        TglFollowup.setBounds(133, 10, 90, 23);

        TglRencKun.setEditable(false);
        TglRencKun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
        TglRencKun.setDisplayFormat("dd-MM-yyyy");
        TglRencKun.setName("TglRencKun"); // NOI18N
        TglRencKun.setOpaque(false);
        FormInput4.add(TglRencKun);
        TglRencKun.setBounds(133, 38, 90, 23);

        TBB.setForeground(new java.awt.Color(0, 0, 0));
        TBB.setName("TBB"); // NOI18N
        TBB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBBKeyPressed(evt);
            }
        });
        FormInput4.add(TBB);
        TBB.setBounds(133, 66, 60, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("TB Untuk Anak (Cm.) : ");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput4.add(jLabel110);
        jLabel110.setBounds(194, 66, 130, 23);

        TTB.setForeground(new java.awt.Color(0, 0, 0));
        TTB.setName("TTB"); // NOI18N
        TTB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTBKeyPressed(evt);
            }
        });
        FormInput4.add(TTB);
        TTB.setBounds(326, 66, 60, 23);

        cmbSttsFung.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsFung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Kerja", "2. Ambulatori", "3. Baring" }));
        cmbSttsFung.setName("cmbSttsFung"); // NOI18N
        cmbSttsFung.setOpaque(false);
        cmbSttsFung.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput4.add(cmbSttsFung);
        cmbSttsFung.setBounds(133, 94, 100, 23);

        TstadWHO.setForeground(new java.awt.Color(0, 0, 0));
        TstadWHO.setName("TstadWHO"); // NOI18N
        TstadWHO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TstadWHOKeyPressed(evt);
            }
        });
        FormInput4.add(TstadWHO);
        TstadWHO.setBounds(133, 122, 400, 23);

        cmbHamil.setForeground(new java.awt.Color(0, 0, 0));
        cmbHamil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbHamil.setName("cmbHamil"); // NOI18N
        cmbHamil.setOpaque(false);
        cmbHamil.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput4.add(cmbHamil);
        cmbHamil.setBounds(133, 150, 60, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Atau Metode KB : ");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput4.add(jLabel111);
        jLabel111.setBounds(194, 150, 105, 23);

        TmetodeKB.setForeground(new java.awt.Color(0, 0, 0));
        TmetodeKB.setName("TmetodeKB"); // NOI18N
        TmetodeKB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmetodeKBKeyPressed(evt);
            }
        });
        FormInput4.add(TmetodeKB);
        TmetodeKB.setBounds(300, 150, 232, 23);

        cmbInfeksiOpor.setForeground(new java.awt.Color(0, 0, 0));
        cmbInfeksiOpor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "K", "D", "Cr", "PCP", "CMV", "P", "Z", "S", "T", "H", "Lain-lain" }));
        cmbInfeksiOpor.setName("cmbInfeksiOpor"); // NOI18N
        cmbInfeksiOpor.setOpaque(false);
        cmbInfeksiOpor.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbInfeksiOpor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInfeksiOporActionPerformed(evt);
            }
        });
        FormInput4.add(cmbInfeksiOpor);
        cmbInfeksiOpor.setBounds(133, 178, 70, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Lain-lain, Uraikan : ");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput4.add(jLabel112);
        jLabel112.setBounds(202, 178, 110, 23);

        TobatUtkIO.setForeground(new java.awt.Color(0, 0, 0));
        TobatUtkIO.setName("TobatUtkIO"); // NOI18N
        TobatUtkIO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatUtkIOKeyPressed(evt);
            }
        });
        FormInput4.add(TobatUtkIO);
        TobatUtkIO.setBounds(133, 206, 400, 23);

        cmbSttsTB.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsTB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Tidak ada gejala/tanda TB", "2. Suspek TB (rujuk ke klinik DOTS atau pemeriksaan sputum)", "3. Dalam terapi TB" }));
        cmbSttsTB.setName("cmbSttsTB"); // NOI18N
        cmbSttsTB.setOpaque(false);
        cmbSttsTB.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput4.add(cmbSttsTB);
        cmbSttsTB.setBounds(133, 234, 330, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Adherance ART : ");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput4.add(jLabel113);
        jLabel113.setBounds(534, 38, 180, 23);

        cmbAdherance.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdherance.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. (>95%)", "2. (80-95%)", "3. (<80%)" }));
        cmbAdherance.setName("cmbAdherance"); // NOI18N
        cmbAdherance.setOpaque(false);
        cmbAdherance.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput4.add(cmbAdherance);
        cmbAdherance.setBounds(717, 38, 100, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Efek Samping ART : ");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput4.add(jLabel114);
        jLabel114.setBounds(534, 66, 180, 23);

        cmbEfekSamping.setForeground(new java.awt.Color(0, 0, 0));
        cmbEfekSamping.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "R", "Mua", "Mun", "D", "N", "Ikt", "An", "Ll", "SK", "Dem", "Hip", "Dep", "P", "Lip", "Ngan", "Ln" }));
        cmbEfekSamping.setName("cmbEfekSamping"); // NOI18N
        cmbEfekSamping.setOpaque(false);
        cmbEfekSamping.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbEfekSamping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEfekSampingActionPerformed(evt);
            }
        });
        FormInput4.add(cmbEfekSamping);
        cmbEfekSamping.setBounds(717, 66, 100, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Efek Samping ART (Lain, Uraikan) : ");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput4.add(jLabel115);
        jLabel115.setBounds(534, 94, 180, 23);

        TEfekSampingLain.setForeground(new java.awt.Color(0, 0, 0));
        TEfekSampingLain.setName("TEfekSampingLain"); // NOI18N
        TEfekSampingLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEfekSampingLainKeyPressed(evt);
            }
        });
        FormInput4.add(TEfekSampingLain);
        TEfekSampingLain.setBounds(717, 94, 240, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Jumlah Cd4 : ");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput4.add(jLabel116);
        jLabel116.setBounds(534, 122, 180, 23);

        TJumlahCD4.setForeground(new java.awt.Color(0, 0, 0));
        TJumlahCD4.setName("TJumlahCD4"); // NOI18N
        TJumlahCD4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJumlahCD4KeyPressed(evt);
            }
        });
        FormInput4.add(TJumlahCD4);
        TJumlahCD4.setBounds(717, 122, 240, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Hasil Laboratorium : ");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput4.add(jLabel117);
        jLabel117.setBounds(534, 150, 180, 23);

        THasilLab.setForeground(new java.awt.Color(0, 0, 0));
        THasilLab.setName("THasilLab"); // NOI18N
        THasilLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THasilLabKeyPressed(evt);
            }
        });
        FormInput4.add(THasilLab);
        THasilLab.setBounds(717, 150, 240, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Diberikan Kondom : ");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput4.add(jLabel118);
        jLabel118.setBounds(534, 178, 180, 23);

        cmbDiberiKondom.setForeground(new java.awt.Color(0, 0, 0));
        cmbDiberiKondom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak", "Tidak Ada" }));
        cmbDiberiKondom.setName("cmbDiberiKondom"); // NOI18N
        cmbDiberiKondom.setOpaque(false);
        cmbDiberiKondom.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput4.add(cmbDiberiKondom);
        cmbDiberiKondom.setBounds(717, 178, 100, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Rujuk Ke Spesialis / MRS : ");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput4.add(jLabel119);
        jLabel119.setBounds(534, 206, 180, 23);

        TRujukKeSpesialis.setForeground(new java.awt.Color(0, 0, 0));
        TRujukKeSpesialis.setName("TRujukKeSpesialis"); // NOI18N
        TRujukKeSpesialis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRujukKeSpesialisKeyPressed(evt);
            }
        });
        FormInput4.add(TRujukKeSpesialis);
        TRujukKeSpesialis.setBounds(717, 206, 240, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("PPK : ");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput4.add(jLabel120);
        jLabel120.setBounds(534, 234, 180, 23);

        Scroll38.setViewportView(FormInput4);

        panelGlass28.add(Scroll38, java.awt.BorderLayout.CENTER);

        internalFrame13.add(panelGlass28, java.awt.BorderLayout.PAGE_START);

        TabIkhtisar.addTab("9. Follow-Up Perawatan Pasien & Terapi ART", internalFrame13);

        internalFrame1.add(TabIkhtisar, java.awt.BorderLayout.CENTER);
        TabIkhtisar.getAccessibleContext().setAccessibleDescription("");

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
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
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
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2022" }));
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
        TNoRM.setBounds(133, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass24.add(TPasien);
        TPasien.setBounds(206, 10, 430, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("No. Reg. Nasional : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass24.add(jLabel11);
        jLabel11.setBounds(0, 38, 130, 23);

        TnoregNas.setForeground(new java.awt.Color(0, 0, 0));
        TnoregNas.setName("TnoregNas"); // NOI18N
        TnoregNas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoregNasKeyPressed(evt);
            }
        });
        panelGlass24.add(TnoregNas);
        TnoregNas.setBounds(133, 38, 130, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Jenis Kelamin : ");
        jLabel70.setName("jLabel70"); // NOI18N
        panelGlass24.add(jLabel70);
        jLabel70.setBounds(270, 38, 80, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        panelGlass24.add(Tjk);
        Tjk.setBounds(350, 38, 80, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        panelGlass24.add(TtglLahir);
        TtglLahir.setBounds(506, 38, 130, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Tgl. Lahir : ");
        jLabel71.setName("jLabel71"); // NOI18N
        panelGlass24.add(jLabel71);
        jLabel71.setBounds(430, 38, 75, 23);

        internalFrame1.add(panelGlass24, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        try {
            if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRM, "Pasien");
            } else if (TnoregNas.getText().equals("")) {
                Valid.textKosong(TnoregNas, "No. Reg. Nasional");
                TnoregNas.requestFocus();
            } else {
                if (TabIkhtisar.getSelectedIndex() == 0) {
                    simpan12();
                } else if (TabIkhtisar.getSelectedIndex() == 1) {
                    simpan3();
                } else if (TabIkhtisar.getSelectedIndex() == 2) {
                    simpan4();
                } else if (TabIkhtisar.getSelectedIndex() == 3) {
                    simpan5();
                } else if (TabIkhtisar.getSelectedIndex() == 4) {
                    simpan6();
                } else if (TabIkhtisar.getSelectedIndex() == 5) {
                    simpan7();
                } else if (TabIkhtisar.getSelectedIndex() == 6) {
                    simpan8();
                } else if (TabIkhtisar.getSelectedIndex() == 7) {
                    simpan9();
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
        if (TabIkhtisar.getSelectedIndex() == 0) {
            emptText12();
        } else if (TabIkhtisar.getSelectedIndex() == 1) {
            emptText3();
        } else if (TabIkhtisar.getSelectedIndex() == 2) {
            emptText4();
        } else if (TabIkhtisar.getSelectedIndex() == 3) {
            emptText5();
        } else if (TabIkhtisar.getSelectedIndex() == 4) {
            emptText6();
        } else if (TabIkhtisar.getSelectedIndex() == 5) {
            emptText7();
        } else if (TabIkhtisar.getSelectedIndex() == 6) {
            emptText8();
        } else if (TabIkhtisar.getSelectedIndex() == 7) {
            emptText9();
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
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (TabIkhtisar.getSelectedIndex() == 0) {
                    hapus12();
                } else if (TabIkhtisar.getSelectedIndex() == 1) {
                    hapus3();
                } else if (TabIkhtisar.getSelectedIndex() == 2) {
                    hapus4();
                } else if (TabIkhtisar.getSelectedIndex() == 3) {
                    hapus5();
                } else if (TabIkhtisar.getSelectedIndex() == 4) {
                    hapus6();
                } else if (TabIkhtisar.getSelectedIndex() == 5) {
                    hapus7();
                } else if (TabIkhtisar.getSelectedIndex() == 6) {
                    hapus8();
                } else if (TabIkhtisar.getSelectedIndex() == 7) {
                    hapus9();
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
        if (TabIkhtisar.getSelectedIndex() == 0) {
            tampil12();
        } else if (TabIkhtisar.getSelectedIndex() == 1) {
            cekIdentitasRiwayat(TNoRM.getText());
            tampil3();
        } else if (TabIkhtisar.getSelectedIndex() == 2) {
            cekIdentitasRiwayat(TNoRM.getText());
            tampil4();
        } else if (TabIkhtisar.getSelectedIndex() == 3) {
            tampil5();
        } else if (TabIkhtisar.getSelectedIndex() == 4) {
            tampil6();
        } else if (TabIkhtisar.getSelectedIndex() == 5) {
            tampil7();
        } else if (TabIkhtisar.getSelectedIndex() == 6) {
            tampil8();
        } else if (TabIkhtisar.getSelectedIndex() == 7) {
            tampil9();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnCari);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TabIkhtisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabIkhtisarMouseClicked
        if (TabIkhtisar.getSelectedIndex() == 0) {            
            TCari.setText(TNoRM.getText());
            tampil12();
        } else if (TabIkhtisar.getSelectedIndex() == 1) {
            TCari.setText(TNoRM.getText()); 
            cekIdentitasRiwayat(TNoRM.getText());
            tampil3();
        } else if (TabIkhtisar.getSelectedIndex() == 2) {
            TCari.setText(TNoRM.getText());
            cekIdentitasRiwayat(TNoRM.getText());
            tampil4();
        } else if (TabIkhtisar.getSelectedIndex() == 3) {  
            TCari.setText(TNoRM.getText());
            tampil5();
        } else if (TabIkhtisar.getSelectedIndex() == 4) {
            TCari.setText(TNoRM.getText());
            tampil6();
        } else if (TabIkhtisar.getSelectedIndex() == 5) {
            TCari.setText(TNoRM.getText());
            tampil7();
        } else if (TabIkhtisar.getSelectedIndex() == 6) {
            TCari.setText(TNoRM.getText());
            tampil8();
        } else if (TabIkhtisar.getSelectedIndex() == 7) {
            TCari.setText(TNoRM.getText());
            tampil9();
        }
    }//GEN-LAST:event_TabIkhtisarMouseClicked

private void btnPengawasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengawasActionPerformed
    if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
        Valid.textKosong(TNoRM, "Pasien");
    } else {
        akses.setform("DlgIkhtisarPerawatanHIVart");
        pengawas.emptTeks();
        pengawas.isCek();
        pengawas.TCari.requestFocus();
        pengawas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pengawas.setLocationRelativeTo(internalFrame1);
        pengawas.setVisible(true);
    }
}//GEN-LAST:event_btnPengawasActionPerformed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
    try {
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else {
            if (TabIkhtisar.getSelectedIndex() == 0) {
                ganti12();
            } else if (TabIkhtisar.getSelectedIndex() == 1) {
                ganti3();
            } else if (TabIkhtisar.getSelectedIndex() == 2) {
                ganti4();
            } else if (TabIkhtisar.getSelectedIndex() == 3) {
                ganti5();
            } else if (TabIkhtisar.getSelectedIndex() == 4) {
                ganti6();                               
            } else if (TabIkhtisar.getSelectedIndex() == 5) {
                ganti7();
            } else if (TabIkhtisar.getSelectedIndex() == 6) {
                ganti8();
            } else if (TabIkhtisar.getSelectedIndex() == 7) {
                ganti9();
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

    private void TglKonfirmasiTesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKonfirmasiTesKeyPressed
        Valid.pindah(evt, TnoregNas, cmbHubPasien);
    }//GEN-LAST:event_TglKonfirmasiTesKeyPressed

    private void TglRujukMskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglRujukMskKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglRujukMskKeyPressed

    private void cmbEntriPoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEntriPoinActionPerformed
        cmbEntriPoin2.setSelectedIndex(0);
        cmbEntriPoin5.setSelectedIndex(0);
        T8lainyaUraikan.setText("");
        
        if (cmbEntriPoin.getSelectedIndex() == 2) {            
            cmbEntriPoin2.setEnabled(true);
            cmbEntriPoin2.requestFocus();
            cmbEntriPoin5.setEnabled(false);
            T8lainyaUraikan.setEnabled(false);            
        } else if (cmbEntriPoin.getSelectedIndex() == 5) {            
            cmbEntriPoin5.setEnabled(true);
            cmbEntriPoin5.requestFocus();
            cmbEntriPoin2.setEnabled(false);
            T8lainyaUraikan.setEnabled(false);            
        } else if (cmbEntriPoin.getSelectedIndex() == 8) {            
            T8lainyaUraikan.setEnabled(true);
            T8lainyaUraikan.requestFocus();
            cmbEntriPoin2.setEnabled(false);
            cmbEntriPoin5.setEnabled(false);            
        } else {
            cmbEntriPoin2.setEnabled(false);
            cmbEntriPoin5.setEnabled(false);
            T8lainyaUraikan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbEntriPoinActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void cmbEntriPoin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEntriPoin2ActionPerformed
        if (cmbEntriPoin2.getSelectedIndex() == 5) {
            T2ralanlainya.setText("");
            T2ralanlainya.setEnabled(true);
            T2ralanlainya.requestFocus();
        } else {
            T2ralanlainya.setText("");
            T2ralanlainya.setEnabled(false);
        }
    }//GEN-LAST:event_cmbEntriPoin2ActionPerformed

    private void cmbEntriPoin5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEntriPoin5ActionPerformed
        if (cmbEntriPoin5.getSelectedIndex() == 4) {
            T5Jangkauan.setText("");
            T5Jangkauan.setEnabled(true);
            T5Jangkauan.requestFocus();
        } else {
            T5Jangkauan.setText("");
            T5Jangkauan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbEntriPoin5ActionPerformed

    private void TnoregNasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoregNasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnoregNas, TRiwAlergiObat);
        }
    }//GEN-LAST:event_TnoregNasKeyPressed

    private void TRiwAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRiwAlergiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TRiwAlergiObat, btnPengawas);
        }
    }//GEN-LAST:event_TRiwAlergiObatKeyPressed

    private void cmbHubPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbHubPasienKeyPressed
        Valid.pindah(evt, cmbHubPasien, TnoTelpPMO);
    }//GEN-LAST:event_cmbHubPasienKeyPressed

    private void TnoTelpPMOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelpPMOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TnoTelpPMO, TAlamatPMO);
        }
    }//GEN-LAST:event_TnoTelpPMOKeyPressed

    private void TAlamatPMOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlamatPMOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TAlamatPMO, TglKonfirmasiTes);
        }
    }//GEN-LAST:event_TAlamatPMOKeyPressed

    private void TnmKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKeluargaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnmKeluarga, cmbHubKeluarga);
        }
    }//GEN-LAST:event_TnmKeluargaKeyPressed

    private void TumurKlgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurKlgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TumurKlg, cmbIDumurKlg);
        }
    }//GEN-LAST:event_TumurKlgKeyPressed

    private void TnoRegNasKlgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoRegNasKlgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnoRegNasKlg, BtnSimpan);
        }
    }//GEN-LAST:event_TnoRegNasKlgKeyPressed

    private void TnmDosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmDosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            Valid.pindah(evt, TnmDosis, TdosisARV);
        }
    }//GEN-LAST:event_TnmDosisKeyPressed

    private void TdosisARVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisARVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TdosisARV, TlamaGuna);
        }
    }//GEN-LAST:event_TdosisARVKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabIkhtisar.setSelectedIndex(0);
        TRiwAlergiObat.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void cmbFaktorResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFaktorResikoActionPerformed
        T7lainUraikan.setText("");
        if (cmbFaktorResiko.getSelectedIndex() == 7) {
            T7lainUraikan.setEnabled(true);
            T7lainUraikan.setText("");
            T7lainUraikan.requestFocus();
        } else {
            T7lainUraikan.setEnabled(false);
            T7lainUraikan.setText("");
        }
    }//GEN-LAST:event_cmbFaktorResikoActionPerformed

    private void tb12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb12KeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData12();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tb12KeyPressed

    private void tb12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb12MouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getData12();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tb12MouseClicked

    private void tb3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb3KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData3();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tb3KeyPressed

    private void tb3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb3MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData3();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tb3MouseClicked

    private void kdKLGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdKLGKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdKLGKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        if (TabIkhtisar.getSelectedIndex() == 0) {            
            tampil12();
            emptText12();
        } else if (TabIkhtisar.getSelectedIndex() == 1) {
            cekIdentitasRiwayat(TNoRM.getText());
            tampil3();
            emptText3();
        } else if (TabIkhtisar.getSelectedIndex() == 2) {
            cekIdentitasRiwayat(TNoRM.getText());
            tampil4();
            emptText4();
        } else if (TabIkhtisar.getSelectedIndex() == 3) {
            tampil5();
            emptText5();
        } else if (TabIkhtisar.getSelectedIndex() == 4) {
            tampil6();
            emptText6();
        } else if (TabIkhtisar.getSelectedIndex() == 5) {
            tampil7();
            emptText7();
        } else if (TabIkhtisar.getSelectedIndex() == 6) {
            tampil8();
            emptText8();
        } else if (TabIkhtisar.getSelectedIndex() == 7) {
            tampil9();
            emptText9();
        }     
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil12();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TRiwAlergiObat);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void tb4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb4KeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData4();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tb4KeyPressed

    private void tb4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb4MouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getData4();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tb4MouseClicked

    private void cmbTerimaARTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTerimaARTActionPerformed
        cmbJikaYA.setSelectedIndex(0);
        if (cmbTerimaART.getSelectedIndex() == 1) {
            cmbJikaYA.setEnabled(false);
            TnmDosis.requestFocus();
        } else {
            cmbJikaYA.setEnabled(true);
            cmbJikaYA.requestFocus();
        }
    }//GEN-LAST:event_cmbTerimaARTActionPerformed

    private void stadWHOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stadWHOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, stadWHO, bb);
        }
    }//GEN-LAST:event_stadWHOKeyPressed

    private void bbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, bb, cmbstsFung);
        }
    }//GEN-LAST:event_bbKeyPressed

    private void JLHcd4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JLHcd4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, JLHcd4, lainLain);
        }
    }//GEN-LAST:event_JLHcd4KeyPressed

    private void lainLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lainLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, lainLain, BtnSimpan);
        }
    }//GEN-LAST:event_lainLainKeyPressed

    private void tb5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb5KeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData5();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tb5KeyPressed

    private void tb5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb5MouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getData5();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tb5MouseClicked

    private void Scroll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll2KeyPressed

    private void btnRejimenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejimenActionPerformed
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else {
            akses.setform("DlgIkhtisarPerawatanHIVart");
            rejimen.emptTeks();
            rejimen.isCek();
            rejimen.TCari.requestFocus();
            rejimen.ChkInput.setSelected(false);
            rejimen.isForm();
            rejimen.setSize(637, 419);
            rejimen.setLocationRelativeTo(internalFrame1);
            rejimen.setVisible(true);
        }
    }//GEN-LAST:event_btnRejimenActionPerformed

    private void RsubstitusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RsubstitusiActionPerformed
        if (Rsubstitusi.isSelected() == true) {
            cmbAlasanSub.setSelectedIndex(0);
            cmbAlasanSwi.setSelectedIndex(0);
            cmbAlasanSto.setSelectedIndex(0);
            cmbAlasanSub.setEnabled(true);
            cmbAlasanSwi.setEnabled(false);
            cmbAlasanSto.setEnabled(false);
            TalasanRes.setText("");
            TalasanRes.setEnabled(false);
        }
    }//GEN-LAST:event_RsubstitusiActionPerformed

    private void RswitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RswitchActionPerformed
        if (Rswitch.isSelected() == true) {
            cmbAlasanSub.setSelectedIndex(0);
            cmbAlasanSwi.setSelectedIndex(0);
            cmbAlasanSto.setSelectedIndex(0);
            cmbAlasanSub.setEnabled(false);
            cmbAlasanSwi.setEnabled(true);
            cmbAlasanSto.setEnabled(false);
            TalasanRes.setText("");
            TalasanRes.setEnabled(false);
        }
    }//GEN-LAST:event_RswitchActionPerformed

    private void RstopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RstopActionPerformed
        if (Rstop.isSelected() == true) {
            cmbAlasanSub.setSelectedIndex(0);
            cmbAlasanSwi.setSelectedIndex(0);
            cmbAlasanSto.setSelectedIndex(0);
            cmbAlasanSub.setEnabled(false);
            cmbAlasanSwi.setEnabled(false);
            cmbAlasanSto.setEnabled(true);
            TalasanRes.setText("");
            TalasanRes.setEnabled(false);
        }
    }//GEN-LAST:event_RstopActionPerformed

    private void RrestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RrestartActionPerformed
        if (Rrestart.isSelected() == true) {
            cmbAlasanSub.setSelectedIndex(0);
            cmbAlasanSwi.setSelectedIndex(0);
            cmbAlasanSto.setSelectedIndex(0);
            cmbAlasanSub.setEnabled(false);
            cmbAlasanSwi.setEnabled(false);
            cmbAlasanSto.setEnabled(false);
            TalasanRes.setText("");
            TalasanRes.setEnabled(true);
        }        
    }//GEN-LAST:event_RrestartActionPerformed

    private void cmbAlasanSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlasanSubActionPerformed
        TalasanSub.setText("");
        TalasanRes.setText("");
        
        if (cmbAlasanSub.getSelectedIndex() == 7 && Rsubstitusi.isSelected() == true) {
            TalasanSub.setEnabled(true);
            TalasanRes.setEnabled(false);
        } else {
            TalasanSub.setEnabled(false);
            TalasanRes.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAlasanSubActionPerformed

    private void TalasanSubKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanSubKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, cmbAlasanSub, TnmRejimenBaru);
        }
    }//GEN-LAST:event_TalasanSubKeyPressed

    private void TalasanResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanResKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TalasanRes, TnmRejimenBaru);
        }
    }//GEN-LAST:event_TalasanResKeyPressed

    private void TnmRejimenBaruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmRejimenBaruKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TnmRejimenBaru, BtnSimpan);
        }
    }//GEN-LAST:event_TnmRejimenBaruKeyPressed

    private void tb6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb6KeyPressed
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData6();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tb6KeyPressed

    private void tb6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb6MouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getData6();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tb6MouseClicked

    private void cmbKlasifikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKlasifikasiActionPerformed
        TklasifikasiLokasi.setText("");
        if (cmbKlasifikasi.getSelectedIndex() == 1) {
            TklasifikasiLokasi.setEnabled(true);            
        } else {
            TklasifikasiLokasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKlasifikasiActionPerformed

    private void tb7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb7KeyPressed
        if (tabMode6.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData7();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tb7KeyPressed

    private void tb7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb7MouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getData7();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tb7MouseClicked

    private void TklasifikasiLokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TklasifikasiLokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TklasifikasiLokasi, TtmpPengobatan);
        }
    }//GEN-LAST:event_TklasifikasiLokasiKeyPressed

    private void TtmpPengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtmpPengobatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TtmpPengobatan, TKabupaten);
        }
    }//GEN-LAST:event_TtmpPengobatanKeyPressed

    private void TKabupatenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKabupatenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TKabupaten, Tnmsarana);
        }
    }//GEN-LAST:event_TKabupatenKeyPressed

    private void TnmsaranaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmsaranaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, Tnmsarana, TnoReg);
        }
    }//GEN-LAST:event_TnmsaranaKeyPressed

    private void TnoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoRegKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TnoReg, tglSelesaitb);
        }
    }//GEN-LAST:event_TnoRegKeyPressed

    private void tb8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb8MouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getData8();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tb8MouseClicked

    private void tb8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb8KeyPressed
        if (tabMode7.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData8();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tb8KeyPressed

    private void tb9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb9MouseClicked
        if (tabMode8.getRowCount() != 0) {
            try {
                getData9();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tb9MouseClicked

    private void tb9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb9KeyPressed
        if (tabMode8.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData9();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tb9KeyPressed

    private void cmbAkhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAkhirActionPerformed
        TnmKlinikBaru.setText("");
        if (cmbAkhir.getSelectedIndex() == 0) {
            tglMati.setEnabled(true);
            tglKunAkhir.setEnabled(false);
            tglRujukKeluar.setEnabled(false);
            TnmKlinikBaru.setEnabled(false);
        } else if (cmbAkhir.getSelectedIndex() == 1) {
            tglMati.setEnabled(false);
            tglKunAkhir.setEnabled(true);            
            tglRujukKeluar.setEnabled(false);
            TnmKlinikBaru.setEnabled(false);
        } else if (cmbAkhir.getSelectedIndex() == 2) {
            tglMati.setEnabled(false);
            tglKunAkhir.setEnabled(false);
            tglRujukKeluar.setEnabled(true);
            TnmKlinikBaru.setEnabled(true);
        }
    }//GEN-LAST:event_cmbAkhirActionPerformed

    private void cmbInfeksiOporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInfeksiOporActionPerformed
        TInfeksiLain.setText("");
        if (cmbInfeksiOpor.getSelectedIndex() == 11) {
            TInfeksiLain.setEnabled(true);
        } else {
            TInfeksiLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbInfeksiOporActionPerformed

    private void cmbEfekSampingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEfekSampingActionPerformed
        TEfekSampingLain.setText("");
        if (cmbEfekSamping.getSelectedIndex() == 16) {
            TEfekSampingLain.setEnabled(true);
        } else {
            TEfekSampingLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbEfekSampingActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else {
            tampil12();
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data Identitas Pasien & Riwayat Pribadi sudah habis. Tidak ada data yang bisa anda print...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                    
                cetak9FollowUP();
                CetakIkhtisar();
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void cmbRujukMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRujukMasukActionPerformed
        if (cmbRujukMasuk.getSelectedIndex() == 0) {
            RtnpART.setEnabled(false);
            RdgART.setEnabled(false);            
            RtnpART.setSelected(false);
            RdgART.setSelected(false);
        } else if (cmbRujukMasuk.getSelectedIndex() == 1) {
            RtnpART.setEnabled(true);
            RdgART.setEnabled(true);
            RtnpART.setSelected(true);
            RdgART.setSelected(false);
        }
    }//GEN-LAST:event_cmbRujukMasukActionPerformed

    private void TBBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TBB, TTB);
        }
    }//GEN-LAST:event_TBBKeyPressed

    private void TTBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TTB, cmbSttsFung);
        }
    }//GEN-LAST:event_TTBKeyPressed

    private void TstadWHOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TstadWHOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TstadWHO, cmbHamil);
        }
    }//GEN-LAST:event_TstadWHOKeyPressed

    private void TmetodeKBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmetodeKBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TmetodeKB, cmbInfeksiOpor);
        }
    }//GEN-LAST:event_TmetodeKBKeyPressed

    private void TInfeksiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInfeksiLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TInfeksiLain, TobatUtkIO);
        }
    }//GEN-LAST:event_TInfeksiLainKeyPressed

    private void TobatARVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatARVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TobatARV, cmbAdherance);
        }
    }//GEN-LAST:event_TobatARVKeyPressed

    private void TEfekSampingLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEfekSampingLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TEfekSampingLain, TJumlahCD4);
        }
    }//GEN-LAST:event_TEfekSampingLainKeyPressed

    private void TJumlahCD4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJumlahCD4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TJumlahCD4, THasilLab);
        }
    }//GEN-LAST:event_TJumlahCD4KeyPressed

    private void THasilLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THasilLabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, THasilLab, cmbDiberiKondom);
        }
    }//GEN-LAST:event_THasilLabKeyPressed

    private void TRujukKeSpesialisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRujukKeSpesialisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TRujukKeSpesialis, BtnSimpan);
        }
    }//GEN-LAST:event_TRujukKeSpesialisKeyPressed

    private void TobatUtkIOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatUtkIOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TobatUtkIO, cmbSttsTB);
        }
    }//GEN-LAST:event_TobatUtkIOKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgIkhtisarPerawatanHIVart dialog = new DlgIkhtisarPerawatanHIVart(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput4;
    private widget.TextBox JLHcd4;
    private widget.Label LCount;
    private widget.RadioButton RBekerja;
    private widget.RadioButton RT;
    private widget.RadioButton RY;
    private widget.RadioButton RdgART;
    private widget.RadioButton Rrestart;
    private widget.RadioButton Rstop;
    private widget.RadioButton Rsubstitusi;
    private widget.RadioButton Rswitch;
    private widget.RadioButton RtdkBekerja;
    private widget.RadioButton RtnpART;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll15;
    private widget.ScrollPane Scroll16;
    private widget.ScrollPane Scroll17;
    private widget.ScrollPane Scroll18;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll37;
    private widget.ScrollPane Scroll38;
    private widget.ScrollPane Scroll4;
    private widget.TextBox T2ralanlainya;
    private widget.TextBox T5Jangkauan;
    private widget.TextBox T7lainUraikan;
    private widget.TextBox T8lainyaUraikan;
    private widget.TextBox TAlamatPMO;
    private widget.TextBox TBB;
    private widget.TextBox TCari;
    private widget.TextBox TEfekSampingLain;
    private widget.TextBox THasilLab;
    private widget.TextBox TInfeksiLain;
    private widget.TextBox TJumlahCD4;
    private widget.TextBox TKabupaten;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.TextBox TRiwAlergiObat;
    private widget.TextBox TRujukKeSpesialis;
    private widget.TextBox TTB;
    private javax.swing.JTabbedPane TabIkhtisar;
    private widget.TextBox TalasanRes;
    private widget.TextBox TalasanSub;
    private widget.TextBox TdosisARV;
    private widget.Tanggal TglDicatat;
    private widget.Tanggal TglFollowup;
    private widget.Tanggal TglKonfirmasiTes;
    private widget.Tanggal TglRencKun;
    private widget.Tanggal TglRujukMsk;
    private widget.TextBox TjikaYA;
    private widget.TextBox Tjk;
    private widget.TextBox Tkd;
    private widget.TextBox Tkd1;
    private widget.TextBox TklasifikasiLokasi;
    private widget.TextBox TlamaGuna;
    private widget.TextBox TmetodeKB;
    private widget.TextBox TnmDosis;
    private widget.TextBox TnmKeluarga;
    private widget.TextBox TnmKlinikBaru;
    private widget.TextBox TnmKlinikSblmnya;
    private widget.TextBox TnmPengawas;
    private widget.TextBox TnmRejimen;
    private widget.TextBox TnmRejimen1;
    private widget.TextBox TnmRejimenBaru;
    private widget.TextBox Tnmsarana;
    private widget.TextBox TnoReg;
    private widget.TextBox TnoRegNasKlg;
    private widget.TextBox TnoTelpPMO;
    private widget.TextBox TnoregNas;
    private widget.TextBox TobatARV;
    private widget.TextBox TobatUtkIO;
    private widget.TextBox TpernahTerimaART;
    private widget.TextBox TstadWHO;
    private widget.TextBox TsttsPernikahan;
    private widget.TextBox TtempatART;
    private widget.TextBox TtglLahir;
    private widget.TextBox TtmpPengobatan;
    private widget.TextBox TumurKlg;
    private widget.TextBox bb;
    private widget.Button btnPengawas;
    private widget.Button btnRejimen;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private widget.ComboBox cmbARTklg;
    private widget.ComboBox cmbAdherance;
    private widget.ComboBox cmbAkhir;
    private widget.ComboBox cmbAlasanSto;
    private widget.ComboBox cmbAlasanSub;
    private widget.ComboBox cmbAlasanSwi;
    private widget.ComboBox cmbDiberiKondom;
    private widget.ComboBox cmbEfekSamping;
    private widget.ComboBox cmbEntriPoin;
    private widget.ComboBox cmbEntriPoin2;
    private widget.ComboBox cmbEntriPoin5;
    private widget.ComboBox cmbFaktorResiko;
    private widget.ComboBox cmbHIVklg;
    private widget.ComboBox cmbHamil;
    private widget.ComboBox cmbHubKeluarga;
    private widget.ComboBox cmbHubPasien;
    private widget.ComboBox cmbIDumurKlg;
    private widget.ComboBox cmbInfeksiOpor;
    private widget.ComboBox cmbJikaYA;
    private widget.ComboBox cmbKlasifikasi;
    private widget.ComboBox cmbLimit;
    private widget.ComboBox cmbPemeriksaan;
    private widget.ComboBox cmbPndkn;
    private widget.ComboBox cmbRejimen;
    private widget.ComboBox cmbRujukMasuk;
    private widget.ComboBox cmbSttsFung;
    private widget.ComboBox cmbSttsNikah;
    private widget.ComboBox cmbSttsTB;
    private widget.ComboBox cmbTerimaART;
    private widget.ComboBox cmbTipe;
    private widget.ComboBox cmbTmpART;
    private widget.ComboBox cmbstsFung;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
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
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
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
    private widget.Label jLabel55;
    private widget.Label jLabel6;
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
    private widget.TextBox kdKLG;
    private widget.TextBox lainLain;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass22;
    private widget.panelisi panelGlass23;
    private widget.panelisi panelGlass24;
    private widget.panelisi panelGlass26;
    private widget.panelisi panelGlass27;
    private widget.panelisi panelGlass28;
    private widget.panelisi panelGlass9;
    private widget.TextBox stadWHO;
    private widget.Table tb12;
    private widget.Table tb3;
    private widget.Table tb4;
    private widget.Table tb5;
    private widget.Table tb6;
    private widget.Table tb7;
    private widget.Table tb8;
    private widget.Table tb9;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tglKunAkhir;
    private widget.Tanggal tglMati;
    private widget.Tanggal tglMulaitb;
    private widget.Tanggal tglPemeriksaan;
    private widget.Tanggal tglRujukKeluar;
    private widget.Tanggal tglSelesaitb;
    private widget.Tanggal tglTerapi;
    // End of variables declaration//GEN-END:variables

    private void getData12() {
        pasienDirujuk = "";
        sttsPekerjaan = "";
        tglrujukmasuk = "";
        
        if (tb12.getSelectedRow() != -1) {
            TNoRM.setText(tb12.getValueAt(tb12.getSelectedRow(), 0).toString());
            TPasien.setText(tb12.getValueAt(tb12.getSelectedRow(), 1).toString());            
            TnoregNas.setText(tb12.getValueAt(tb12.getSelectedRow(), 2).toString().replaceAll("6303015 ", ""));
            Tjk.setText(tb12.getValueAt(tb12.getSelectedRow(), 3).toString());
            TtglLahir.setText(tb12.getValueAt(tb12.getSelectedRow(), 4).toString());
            TRiwAlergiObat.setText(tb12.getValueAt(tb12.getSelectedRow(), 5).toString());
            TnmPengawas.setText(tb12.getValueAt(tb12.getSelectedRow(), 6).toString());
            cmbHubPasien.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 7).toString());
            TnoTelpPMO.setText(tb12.getValueAt(tb12.getSelectedRow(), 8).toString());
            TAlamatPMO.setText(tb12.getValueAt(tb12.getSelectedRow(), 9).toString());
            Valid.SetTgl(TglKonfirmasiTes, tb12.getValueAt(tb12.getSelectedRow(), 10).toString());
            cmbEntriPoin.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 11).toString());
            cmbEntriPoin2.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 12).toString());
            cmbEntriPoin5.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 13).toString());
            T2ralanlainya.setText(tb12.getValueAt(tb12.getSelectedRow(), 14).toString());
            T5Jangkauan.setText(tb12.getValueAt(tb12.getSelectedRow(), 15).toString());
            T8lainyaUraikan.setText(tb12.getValueAt(tb12.getSelectedRow(), 16).toString());
            pasienDirujuk = tb12.getValueAt(tb12.getSelectedRow(), 17).toString();
            cmbPndkn.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 18).toString());
            TnmKlinikSblmnya.setText(tb12.getValueAt(tb12.getSelectedRow(), 19).toString());
            sttsPekerjaan = tb12.getValueAt(tb12.getSelectedRow(), 20).toString();
            tglrujukmasuk = tb12.getValueAt(tb12.getSelectedRow(), 21).toString();
            cmbFaktorResiko.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 22).toString());
            T7lainUraikan.setText(tb12.getValueAt(tb12.getSelectedRow(), 23).toString());
            Tkd.setText(tb12.getValueAt(tb12.getSelectedRow(), 24).toString());
            TnmRejimen.setText(tb12.getValueAt(tb12.getSelectedRow(), 25).toString());
            Tkd1.setText(tb12.getValueAt(tb12.getSelectedRow(), 24).toString());
            TnmRejimen1.setText(tb12.getValueAt(tb12.getSelectedRow(), 25).toString());
            cmbRujukMasuk.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 26).toString());
            cmbSttsNikah.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 27).toString());            
            cmbTerimaART.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 28).toString());
            cmbJikaYA.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 29).toString());
            cmbTmpART.setSelectedItem(tb12.getValueAt(tb12.getSelectedRow(), 30).toString());
            
            if (tglrujukmasuk.equals("-")) {
                TglRujukMsk.setDate(new Date());
            } else {
                Valid.SetTgl(TglRujukMsk, tb12.getValueAt(tb12.getSelectedRow(), 21).toString());
            }
            
            if (pasienDirujuk.equals("Tanpa ART")) {
                RtnpART.setSelected(true);
                RdgART.setSelected(false);
                RtnpART.setEnabled(true);
                RdgART.setEnabled(true);
            } else if (pasienDirujuk.equals("Dengan ART")) {
                RdgART.setSelected(true);
                RtnpART.setSelected(false);
                RtnpART.setEnabled(true);
                RdgART.setEnabled(true);
            } else if (pasienDirujuk.equals("")) {
                RdgART.setSelected(false);
                RtnpART.setSelected(false);
                RtnpART.setEnabled(false);
                RdgART.setEnabled(false);
            }
            
            if (sttsPekerjaan.equals("0. Tidak Bekerja")) {
                RtdkBekerja.setSelected(true);
                RBekerja.setSelected(false);
            } else if (sttsPekerjaan.equals("1. Bekerja")) {
                RtdkBekerja.setSelected(false);
                RBekerja.setSelected(true);
            }            
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getikhtisar_perawatan_hiv());
        BtnHapus.setEnabled(akses.getikhtisar_perawatan_hiv());
        BtnEdit.setEnabled(akses.getikhtisar_perawatan_hiv());
        BtnPrint.setEnabled(akses.getikhtisar_perawatan_hiv());
     }

    private void getData3() {
        kdKLG.setText("");
        if (tb3.getSelectedRow() != -1) { 
            TnmKeluarga.setText(tb3.getValueAt(tb3.getSelectedRow(), 2).toString());            
            cmbHubKeluarga.setSelectedItem(tb3.getValueAt(tb3.getSelectedRow(), 3).toString());
            TumurKlg.setText(Sequel.cariIsi("select umur from hiv_riwayat_keluarga where no_rkm_medis='" + TNoRM.getText() + "' and nama='" + TnmKeluarga.getText() + "'"));
            cmbIDumurKlg.setSelectedItem(Sequel.cariIsi("select sttsumur from hiv_riwayat_keluarga where no_rkm_medis='" + TNoRM.getText() + "' and nama='" + TnmKeluarga.getText() + "'"));
            cmbHIVklg.setSelectedItem(tb3.getValueAt(tb3.getSelectedRow(), 5).toString());
            cmbARTklg.setSelectedItem(tb3.getValueAt(tb3.getSelectedRow(), 6).toString());    
            TnoRegNasKlg.setText(tb3.getValueAt(tb3.getSelectedRow(), 7).toString());
            kdKLG.setText(tb3.getValueAt(tb3.getSelectedRow(), 8).toString());
        }
    }    
    
    private void getData4() {
        tglsimpan = "";
        if (tb4.getSelectedRow() != -1) {             
            TnmDosis.setText(tb4.getValueAt(tb4.getSelectedRow(), 2).toString());
            TdosisARV.setText(tb4.getValueAt(tb4.getSelectedRow(), 3).toString());
            TlamaGuna.setText(tb4.getValueAt(tb4.getSelectedRow(), 4).toString());
            tglsimpan = tb4.getValueAt(tb4.getSelectedRow(), 6).toString();
            Valid.SetTgl(TglDicatat, tb4.getValueAt(tb4.getSelectedRow(), 7).toString());            
        }
    }    
    
    private void getData5() {
        noUrut = "";
        if (tb5.getSelectedRow() != -1) { 
            noUrut = tb5.getValueAt(tb5.getSelectedRow(), 0).toString();
            cmbPemeriksaan.setSelectedItem(tb5.getValueAt(tb5.getSelectedRow(), 3).toString());
            Valid.SetTgl(tglPemeriksaan, tb5.getValueAt(tb5.getSelectedRow(), 10).toString());      
            stadWHO.setText(tb5.getValueAt(tb5.getSelectedRow(), 5).toString());
            bb.setText(tb5.getValueAt(tb5.getSelectedRow(), 6).toString());            
            cmbstsFung.setSelectedItem(tb5.getValueAt(tb5.getSelectedRow(), 7).toString());
            JLHcd4.setText(tb5.getValueAt(tb5.getSelectedRow(), 8).toString());
            lainLain.setText(tb5.getValueAt(tb5.getSelectedRow(), 9).toString());            
        }
    }  
    
    private void getData6() {
        emptText6();
        jnsTerapi = "";        
        if (tb6.getSelectedRow() != -1) { 
            jnsTerapi = tb6.getValueAt(tb6.getSelectedRow(), 3).toString();
            Valid.SetTgl(tglTerapi, tb6.getValueAt(tb6.getSelectedRow(), 9).toString());
            TalasanSub.setText(tb6.getValueAt(tb6.getSelectedRow(), 5).toString());
            TalasanRes.setText(tb6.getValueAt(tb6.getSelectedRow(), 6).toString());
            TnmRejimenBaru.setText(tb6.getValueAt(tb6.getSelectedRow(), 7).toString());
            TNoRM.setText(tb6.getValueAt(tb6.getSelectedRow(), 0).toString());
            TPasien.setText(tb6.getValueAt(tb6.getSelectedRow(), 1).toString());
            tglsimpan = tb6.getValueAt(tb6.getSelectedRow(), 8).toString();
            
            if (jnsTerapi.equals("Substitusi")) {
                Rsubstitusi.setSelected(true);
                cmbAlasanSub.setSelectedItem(tb6.getValueAt(tb6.getSelectedRow(), 10).toString());
                cmbAlasanSub.setEnabled(true);
                cmbAlasanSwi.setEnabled(false);
                cmbAlasanSto.setEnabled(false);
                TalasanRes.setEnabled(false);
            } else if (jnsTerapi.equals("Switch")) {
                Rswitch.setSelected(true);
                cmbAlasanSwi.setEnabled(true);
                cmbAlasanSwi.setSelectedItem(tb6.getValueAt(tb6.getSelectedRow(), 10).toString());
                cmbAlasanSub.setEnabled(false);
                cmbAlasanSto.setEnabled(false);
                TalasanRes.setEnabled(false);
            } else if (jnsTerapi.equals("Stop")) {
                Rstop.setSelected(true);
                cmbAlasanSto.setEnabled(true);
                cmbAlasanSto.setSelectedItem(tb6.getValueAt(tb6.getSelectedRow(), 10).toString());
                cmbAlasanSwi.setEnabled(false);
                cmbAlasanSub.setEnabled(false);
                TalasanRes.setEnabled(false);
            } else if (jnsTerapi.equals("Restart")) {
                Rrestart.setSelected(true);
                cmbAlasanSub.setEnabled(false);
                cmbAlasanSwi.setEnabled(false);
                cmbAlasanSto.setEnabled(false);
                TalasanRes.setEnabled(true);
            }

            if (cmbAlasanSub.getSelectedIndex() == 7 && Rsubstitusi.isSelected() == true) {
                TalasanSub.setEnabled(true);
                cmbAlasanSwi.setEnabled(false);
                cmbAlasanSto.setEnabled(false);
                TalasanRes.setEnabled(false);
            }
        }
    }  
    
    private void getData7() {
        emptText7();
        if (tb7.getSelectedRow() != -1) { 
            cmbKlasifikasi.setSelectedItem(tb7.getValueAt(tb7.getSelectedRow(), 2).toString());
            TklasifikasiLokasi.setText(tb7.getValueAt(tb7.getSelectedRow(), 3).toString());
            cmbTipe.setSelectedItem(tb7.getValueAt(tb7.getSelectedRow(), 4).toString());
            cmbRejimen.setSelectedItem(tb7.getValueAt(tb7.getSelectedRow(), 5).toString());
            TtmpPengobatan.setText(tb7.getValueAt(tb7.getSelectedRow(), 6).toString());
            TKabupaten.setText(tb7.getValueAt(tb7.getSelectedRow(), 7).toString());
            Tnmsarana.setText(tb7.getValueAt(tb7.getSelectedRow(), 8).toString());
            TnoReg.setText(tb7.getValueAt(tb7.getSelectedRow(), 9).toString());            
            Valid.SetTgl(tglMulaitb, tb7.getValueAt(tb7.getSelectedRow(), 12).toString());
            Valid.SetTgl(tglSelesaitb, tb7.getValueAt(tb7.getSelectedRow(), 13).toString());           
        }
    } 
    
    private void getData8() {
        emptText8();
        if (tb8.getSelectedRow() != -1) { 
            cmbAkhir.setSelectedItem(tb8.getValueAt(tb8.getSelectedRow(), 2).toString());
            TnmKlinikBaru.setText(tb8.getValueAt(tb8.getSelectedRow(), 6).toString());
            tglmeninggal = tb8.getValueAt(tb8.getSelectedRow(), 7).toString();
            tglkunjungan = tb8.getValueAt(tb8.getSelectedRow(), 8).toString();
            tglrujukkeluar = tb8.getValueAt(tb8.getSelectedRow(), 9).toString();
            
            if (!tglmeninggal.equals("")) {
                Valid.SetTgl(tglMati, tb8.getValueAt(tb8.getSelectedRow(), 7).toString());
            }
            
            if (!tglkunjungan.equals("")) {
                Valid.SetTgl(tglKunAkhir, tb8.getValueAt(tb8.getSelectedRow(), 8).toString());
            }

            if (!tglrujukkeluar.equals("")) {
                Valid.SetTgl(tglRujukKeluar, tb8.getValueAt(tb8.getSelectedRow(), 9).toString());
            }
            
            if (cmbAkhir.getSelectedIndex() == 0) {
                tglMati.setEnabled(true);
                tglKunAkhir.setEnabled(false);
                tglRujukKeluar.setEnabled(false);
                TnmKlinikBaru.setEnabled(false);
            } else if (cmbAkhir.getSelectedIndex() == 1) {
                tglMati.setEnabled(false);
                tglKunAkhir.setEnabled(true);
                tglRujukKeluar.setEnabled(false);
                TnmKlinikBaru.setEnabled(false);
            } else if (cmbAkhir.getSelectedIndex() == 2) {
                tglMati.setEnabled(false);
                tglKunAkhir.setEnabled(false);
                tglRujukKeluar.setEnabled(true);
                TnmKlinikBaru.setEnabled(true);
            }
        }
    }
    
    private void getData9() {
        emptText9();
        ppk = "";
        if (tb9.getSelectedRow() != -1) {
            TBB.setText(tb9.getValueAt(tb9.getSelectedRow(), 4).toString());
            TTB.setText(tb9.getValueAt(tb9.getSelectedRow(), 5).toString());
            cmbSttsFung.setSelectedItem(tb9.getValueAt(tb9.getSelectedRow(), 6).toString());
            TstadWHO.setText(tb9.getValueAt(tb9.getSelectedRow(), 7).toString());
            cmbHamil.setSelectedItem(tb9.getValueAt(tb9.getSelectedRow(), 8).toString());
            TmetodeKB.setText(tb9.getValueAt(tb9.getSelectedRow(), 9).toString());
            cmbInfeksiOpor.setSelectedItem(tb9.getValueAt(tb9.getSelectedRow(), 10).toString());
            TInfeksiLain.setText(tb9.getValueAt(tb9.getSelectedRow(), 11).toString());
            TobatUtkIO.setText(tb9.getValueAt(tb9.getSelectedRow(), 12).toString());
            cmbSttsTB.setSelectedItem(tb9.getValueAt(tb9.getSelectedRow(), 13).toString());
            ppk = tb9.getValueAt(tb9.getSelectedRow(), 14).toString();
            TobatARV.setText(tb9.getValueAt(tb9.getSelectedRow(), 15).toString());
            cmbAdherance.setSelectedItem(tb9.getValueAt(tb9.getSelectedRow(), 16).toString());
            cmbEfekSamping.setSelectedItem(tb9.getValueAt(tb9.getSelectedRow(), 17).toString());
            TEfekSampingLain.setText(tb9.getValueAt(tb9.getSelectedRow(), 18).toString());
            TJumlahCD4.setText(tb9.getValueAt(tb9.getSelectedRow(), 19).toString());
            THasilLab.setText(tb9.getValueAt(tb9.getSelectedRow(), 20).toString());
            cmbDiberiKondom.setSelectedItem(tb9.getValueAt(tb9.getSelectedRow(), 21).toString());
            TRujukKeSpesialis.setText(tb9.getValueAt(tb9.getSelectedRow(), 22).toString());
            tglsimpan = tb9.getValueAt(tb9.getSelectedRow(), 23).toString();
            Valid.SetTgl(TglFollowup, tb9.getValueAt(tb9.getSelectedRow(), 24).toString());
            Valid.SetTgl(TglRencKun, tb9.getValueAt(tb9.getSelectedRow(), 25).toString());
            
            if (ppk.equals("Y")) {
                RY.setSelected(true);
                RT.setSelected(false);
            } else if (ppk.equals("T")) {
                RY.setSelected(false);
                RT.setSelected(true);
            }
        }
    }

    public void setNoRm(String norm) {
        TNoRM.setText(norm);
        TCari.setText(norm);
        cekIdentitasRiwayat(norm);
        cekPasien(norm);
        Tkd1.setText(Tkd.getText());
        TnmRejimen1.setText(TnmRejimen.getText());
        TabIkhtisar.setSelectedIndex(0);
        emptText12();
        emptText3();
        emptText4();
        emptText5();
        emptText6();
        emptText7();
        emptText8();
        emptText9();
    }

    public void emptText() {
        TNoRM.setText("");
        TPasien.setText("");
        TnoregNas.setText("");
        Tjk.setText("");
        TtglLahir.setText("");
        TRiwAlergiObat.setText("");
        TnmPengawas.setText("");
        cmbHubPasien.setSelectedIndex(0);
        TnoTelpPMO.setText("");
        TAlamatPMO.setText("");
        TglKonfirmasiTes.setDate(new Date());
        cmbEntriPoin.setSelectedIndex(0);
        cmbEntriPoin2.setSelectedIndex(0);
        cmbEntriPoin5.setSelectedIndex(0);
        T2ralanlainya.setText("");
        T5Jangkauan.setText("");
        T8lainyaUraikan.setText("");
        RtnpART.setSelected(true);
        RdgART.setSelected(false);
        cmbPndkn.setSelectedIndex(0);
        TnmKlinikSblmnya.setText("");
        RtdkBekerja.setSelected(true);
        RBekerja.setSelected(false);
        TglRujukMsk.setDate(new Date());
        cmbFaktorResiko.setSelectedIndex(0);
        T7lainUraikan.setText("");
        Tkd.setText("");
        TnmRejimen.setText("");
        
        cmbEntriPoin2.setEnabled(false);
        cmbEntriPoin5.setEnabled(false);
        T2ralanlainya.setEnabled(false);
        T5Jangkauan.setEnabled(false);
        T8lainyaUraikan.setEnabled(false);
        T7lainUraikan.setEnabled(false);
        emptText3();
        emptText4();
        emptText5();
        emptText6();
        emptText7();
        emptText8();
        emptText9();
    }
    
    private void emptText12() {
        TRiwAlergiObat.setText("");
        TnmPengawas.setText("");
        cmbHubPasien.setSelectedIndex(0);
        TnoTelpPMO.setText("");
        TAlamatPMO.setText("");
        cmbSttsNikah.setSelectedIndex(0);
        TglKonfirmasiTes.setDate(new Date());
        cmbEntriPoin.setSelectedIndex(0);
        cmbEntriPoin2.setSelectedIndex(0);
        cmbEntriPoin5.setSelectedIndex(0);
        T2ralanlainya.setText("");
        T5Jangkauan.setText("");
        T8lainyaUraikan.setText("");
        cmbRujukMasuk.setSelectedIndex(0);
        RtnpART.setSelected(false);
        RdgART.setSelected(false);
        cmbPndkn.setSelectedIndex(0);
        TnmKlinikSblmnya.setText("");
        RtdkBekerja.setSelected(true);
        RBekerja.setSelected(false);
        TglRujukMsk.setDate(new Date());
        cmbFaktorResiko.setSelectedIndex(0);
        T7lainUraikan.setText("");   
        cmbTerimaART.setSelectedIndex(1);
        cmbJikaYA.setSelectedIndex(0);
        cmbJikaYA.setEnabled(false);
        cmbTmpART.setSelectedIndex(0);
        
        cmbEntriPoin2.setEnabled(false);
        cmbEntriPoin5.setEnabled(false);
        T2ralanlainya.setEnabled(false);
        T5Jangkauan.setEnabled(false);
        T8lainyaUraikan.setEnabled(false);
        T7lainUraikan.setEnabled(false);
        RtnpART.setEnabled(false);
        RdgART.setEnabled(false);
    }
    
    private void emptText3() {
        kdKLG.setText("");
        TnmKeluarga.setText("");
        TnoRegNasKlg.setText("");
        cmbHubKeluarga.setSelectedIndex(0);
        TumurKlg.setText("");
        cmbIDumurKlg.setSelectedIndex(0);
        cmbHIVklg.setSelectedIndex(0);
        cmbARTklg.setSelectedIndex(0);   
    }
    
    private void emptText4() {
        tglsimpan = "";        
        TnmDosis.setText("");        
        TdosisARV.setText("");
        TlamaGuna.setText("");
        TglDicatat.setDate(new Date());
    }
    
    private void emptText5() {
        noUrut = "";
        cmbPemeriksaan.setSelectedIndex(0);
        stadWHO.setText("");
        bb.setText("");
        tglPemeriksaan.setDate(new Date());        
        cmbstsFung.setSelectedIndex(0);
        JLHcd4.setText("");
        lainLain.setText("");
    }
    
    private void emptText6() {
        tglsimpan = "";
        nilaiAlasan = "";
        alasanya = "";
        Rsubstitusi.setSelected(false);
        Rswitch.setSelected(false);
        Rstop.setSelected(false);
        Rrestart.setSelected(false);
        tglTerapi.setDate(new Date());
        cmbAlasanSub.setSelectedIndex(0);
        cmbAlasanSwi.setSelectedIndex(0);
        cmbAlasanSto.setSelectedIndex(0);
        cmbAlasanSub.setEnabled(false);
        cmbAlasanSwi.setEnabled(false);
        cmbAlasanSto.setEnabled(false);
        TalasanSub.setText("");
        TalasanSub.setEnabled(false);
        TalasanRes.setText("");
        TalasanRes.setEnabled(false);
        TnmRejimenBaru.setText("");
    }
    
    private void emptText7() {
        cmbKlasifikasi.setSelectedIndex(0);
        TklasifikasiLokasi.setEnabled(false);
        cmbTipe.setSelectedIndex(0);
        cmbRejimen.setSelectedIndex(0);
        tglMulaitb.setDate(new Date());
        tglSelesaitb.setDate(new Date());
        TklasifikasiLokasi.setText("");
        TtmpPengobatan.setText("");
        TKabupaten.setText("");
        Tnmsarana.setText("");
        TnoReg.setText("");
    }
    
    private void emptText8() {
        tglmeninggal = "";
        tglkunjungan = "";
        tglrujukkeluar = "";
        cmbAkhir.setSelectedIndex(0);
        tglMati.setDate(new Date());
        tglKunAkhir.setDate(new Date());
        tglRujukKeluar.setDate(new Date());
        TnmKlinikBaru.setText("");

        tglMati.setEnabled(true);
        tglKunAkhir.setEnabled(false);
        tglRujukKeluar.setEnabled(false);
        TnmKlinikBaru.setEnabled(false);
    }
    
    private void emptText9() {
        tglsimpan = "";
        TglFollowup.setDate(new Date());
        TglRencKun.setDate(new Date());
        TBB.setText("");
        TTB.setText("");
        cmbSttsFung.setSelectedIndex(0);
        TstadWHO.setText("");
        cmbHamil.setSelectedIndex(0);
        TmetodeKB.setText("");
        cmbInfeksiOpor.setSelectedIndex(0);
        TInfeksiLain.setText("");
        TobatUtkIO.setText("");
        cmbSttsTB.setSelectedIndex(0);
        TobatARV.setText("");
        cmbAdherance.setSelectedIndex(0);
        cmbEfekSamping.setSelectedIndex(0);
        TEfekSampingLain.setText("");
        TJumlahCD4.setText("");
        THasilLab.setText("");
        cmbDiberiKondom.setSelectedIndex(0);
        TRujukKeSpesialis.setText("");
        RY.setSelected(true);
        RT.setSelected(false);
        
        TInfeksiLain.setEnabled(false);
        TEfekSampingLain.setEnabled(false);
    }
    
    public void tampil12() {
        Valid.tabelKosong(tabMode1);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, h.no_reg_nasional, p.jk, p.tgl_lahir, h.riwayat_alergi_obat, "
                        + "h.nm_pengawas_pmo, h.hubungan_dg_pasien, h.no_telpon_pmo, "
                        + "h.alamat_pmo, h.tgl_konfirmasi_tes_hiv, h.entry_point, h.rawat_jalan, h.jangkauan, "
                        + "h.lainnya_rawat_jalan, h.lainnya_jangkauan, h.delapan_lainnya_uraikan, h.dirujuk_msk_dari_klinik_lain, "
                        + "h.pendidikan, h.nm_klinik_sebelumnya, h.stts_pekerjaan, if(h.tgl_rujuk_msk='0000-00-00','-',h.tgl_rujuk_msk) tgl_rujuk_msk, "
                        + "h.faktor_resiko, h.lain2_faktor_resiko_uraian, h.kd_rejimen, hm.nm_rejimen, h.dirujuk_masuk, h.stts_pernikahan, h.menerima_art, h.art_ya, "
                        + "h.tmpt_art_dulu FROM hiv_identitas_riwayat_pribadi h INNER JOIN hiv_master_rejimen hm ON hm.kd_rejimen=h.kd_rejimen "
                        + "inner join pasien p on p.no_rkm_medis=h.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? OR "
                        + "p.nm_pasien LIKE ? OR "
                        + "h.no_reg_nasional LIKE ? OR "
                        + "p.jk LIKE ? OR "
                        + "h.riwayat_alergi_obat LIKE ? OR "
                        + "h.no_telpon_pmo LIKE ? OR "
                        + "h.entry_point LIKE ? OR "
                        + "h.dirujuk_msk_dari_klinik_lain LIKE ? OR "
                        + "h.nm_klinik_sebelumnya LIKE ? OR "
                        + "h.stts_pekerjaan LIKE ? OR "
                        + "hm.nm_rejimen like ? or "
                        + "h.faktor_resiko LIKE ? ORDER BY p.no_rkm_medis DESC");
            } else {
                ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, h.no_reg_nasional, p.jk, p.tgl_lahir, h.riwayat_alergi_obat, "
                        + "h.nm_pengawas_pmo, h.hubungan_dg_pasien, h.no_telpon_pmo, "
                        + "h.alamat_pmo, h.tgl_konfirmasi_tes_hiv, h.entry_point, h.rawat_jalan, h.jangkauan, "
                        + "h.lainnya_rawat_jalan, h.lainnya_jangkauan, h.delapan_lainnya_uraikan, h.dirujuk_msk_dari_klinik_lain, "
                        + "h.pendidikan, h.nm_klinik_sebelumnya, h.stts_pekerjaan, if(h.tgl_rujuk_msk='0000-00-00','-',h.tgl_rujuk_msk) tgl_rujuk_msk, "
                        + "h.faktor_resiko, h.lain2_faktor_resiko_uraian, h.kd_rejimen, hm.nm_rejimen, h.dirujuk_masuk, h.stts_pernikahan, h.menerima_art, h.art_ya, "
                        + "h.tmpt_art_dulu FROM hiv_identitas_riwayat_pribadi h INNER JOIN hiv_master_rejimen hm ON hm.kd_rejimen=h.kd_rejimen "
                        + "inner join pasien p on p.no_rkm_medis=h.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? OR "
                        + "p.nm_pasien LIKE ? OR "
                        + "h.no_reg_nasional LIKE ? OR "
                        + "p.jk LIKE ? OR "
                        + "h.riwayat_alergi_obat LIKE ? OR "
                        + "h.no_telpon_pmo LIKE ? OR "
                        + "h.entry_point LIKE ? OR "
                        + "h.dirujuk_msk_dari_klinik_lain LIKE ? OR "
                        + "h.nm_klinik_sebelumnya LIKE ? OR "
                        + "h.stts_pekerjaan LIKE ? OR "
                        + "hm.nm_rejimen like ? or "
                        + "h.faktor_resiko LIKE ? ORDER BY p.no_rkm_medis DESC LIMIT " + cmbLimit.getSelectedItem().toString() + "");
            }
            try {
                ps1.setString(1, "%" + TCari.getText().trim() + "%");
                ps1.setString(2, "%" + TCari.getText().trim() + "%");
                ps1.setString(3, "%" + TCari.getText().trim() + "%");
                ps1.setString(4, "%" + TCari.getText().trim() + "%");
                ps1.setString(5, "%" + TCari.getText().trim() + "%");
                ps1.setString(6, "%" + TCari.getText().trim() + "%");
                ps1.setString(7, "%" + TCari.getText().trim() + "%");
                ps1.setString(8, "%" + TCari.getText().trim() + "%");
                ps1.setString(9, "%" + TCari.getText().trim() + "%");
                ps1.setString(10, "%" + TCari.getText().trim() + "%");
                ps1.setString(11, "%" + TCari.getText().trim() + "%");
                ps1.setString(12, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("no_rkm_medis"),
                        rs1.getString("nm_pasien"),
                        rs1.getString("no_reg_nasional"),
                        rs1.getString("jk").replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan"),
                        Valid.SetTglINDONESIA(rs1.getString("tgl_lahir")),
                        rs1.getString("riwayat_alergi_obat"),
                        rs1.getString("nm_pengawas_pmo"),
                        rs1.getString("hubungan_dg_pasien"),
                        rs1.getString("no_telpon_pmo"),
                        rs1.getString("alamat_pmo"),
                        rs1.getString("tgl_konfirmasi_tes_hiv"),
                        rs1.getString("entry_point"),
                        rs1.getString("rawat_jalan"),
                        rs1.getString("jangkauan"),
                        rs1.getString("lainnya_rawat_jalan"),
                        rs1.getString("lainnya_jangkauan"),
                        rs1.getString("delapan_lainnya_uraikan"),
                        rs1.getString("dirujuk_msk_dari_klinik_lain"),
                        rs1.getString("pendidikan"),
                        rs1.getString("nm_klinik_sebelumnya"),
                        rs1.getString("stts_pekerjaan"),
                        rs1.getString("tgl_rujuk_msk"),
                        rs1.getString("faktor_resiko"),
                        rs1.getString("lain2_faktor_resiko_uraian"),
                        rs1.getString("kd_rejimen"),
                        rs1.getString("nm_rejimen"),
                        rs1.getString("dirujuk_masuk"),
                        rs1.getString("stts_pernikahan"),
                        rs1.getString("menerima_art"),
                        rs1.getString("art_ya"),
                        rs1.getString("tmpt_art_dulu")
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
    
    private void tampil3() {
        Valid.tabelKosong(tabMode2);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, hr.nama, hr.hubungan, "
                        + "CONCAT(hr.umur,' ',hr.sttsumur) umur, hr.hiv, hr.art, hr.no_reg_nasional, hr.kd_keluarga FROM hiv_riwayat_keluarga hr "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=hr.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? OR "
                        + "p.nm_pasien LIKE ? OR "
                        + "hr.nama LIKE ? OR "
                        + "hr.hubungan LIKE ? OR "
                        + "hr.umur LIKE ? OR "
                        + "hr.hiv LIKE ? OR "
                        + "hr.art LIKE ? OR "
                        + "hr.no_reg_nasional LIKE ? ORDER BY hr.nama");
            } else {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, hr.nama, hr.hubungan, "
                        + "CONCAT(hr.umur,' ',hr.sttsumur) umur, hr.hiv, hr.art, hr.no_reg_nasional, hr.kd_keluarga FROM hiv_riwayat_keluarga hr "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=hr.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? OR "
                        + "p.nm_pasien LIKE ? OR "
                        + "hr.nama LIKE ? OR "
                        + "hr.hubungan LIKE ? OR "
                        + "hr.umur LIKE ? OR "
                        + "hr.hiv LIKE ? OR "
                        + "hr.art LIKE ? OR "
                        + "hr.no_reg_nasional LIKE ? ORDER BY hr.nama LIMIT " + cmbLimit.getSelectedItem().toString() + "");
            }
            try {
                ps2.setString(1, "%" + TCari.getText().trim() + "%");
                ps2.setString(2, "%" + TCari.getText().trim() + "%");
                ps2.setString(3, "%" + TCari.getText().trim() + "%");
                ps2.setString(4, "%" + TCari.getText().trim() + "%");
                ps2.setString(5, "%" + TCari.getText().trim() + "%");
                ps2.setString(6, "%" + TCari.getText().trim() + "%");
                ps2.setString(7, "%" + TCari.getText().trim() + "%");
                ps2.setString(8, "%" + TCari.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),
                        rs2.getString("nama"),
                        rs2.getString("hubungan"),
                        rs2.getString("umur"),
                        rs2.getString("hiv"),
                        rs2.getString("art"),
                        rs2.getString("no_reg_nasional"),
                        rs2.getString("kd_keluarga")
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
    
    private void tampil4() {
        Valid.tabelKosong(tabMode3);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ht.nama, ht.dosis_arv, ht.lama_penggunaan,"
                        + "DATE_FORMAT(ht.tgl_catat,'%d-%m-%Y') tgl, ht.tgl_simpan, ht.tgl_catat FROM hiv_riwayat_terapi ht "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=ht.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? OR "
                        + "p.nm_pasien LIKE ? OR "
                        + "ht.nama LIKE ? OR "
                        + "ht.dosis_arv LIKE ? OR "
                        + "ht.lama_penggunaan LIKE ? ORDER BY ht.tgl_simpan desc");
            } else {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ht.nama, ht.dosis_arv, ht.lama_penggunaan,"
                        + "DATE_FORMAT(ht.tgl_catat,'%d-%m-%Y') tgl, ht.tgl_simpan, ht.tgl_catat FROM hiv_riwayat_terapi ht "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=ht.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? OR "
                        + "p.nm_pasien LIKE ? OR "
                        + "ht.nama LIKE ? OR "
                        + "ht.dosis_arv LIKE ? OR "
                        + "ht.lama_penggunaan LIKE ? ORDER BY ht.tgl_simpan desc LIMIT " + cmbLimit.getSelectedItem().toString() + "");
            }
            
            try {
                ps3.setString(1, "%" + TCari.getText().trim() + "%");
                ps3.setString(2, "%" + TCari.getText().trim() + "%");
                ps3.setString(3, "%" + TCari.getText().trim() + "%");
                ps3.setString(4, "%" + TCari.getText().trim() + "%");
                ps3.setString(5, "%" + TCari.getText().trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode3.addRow(new Object[]{                        
                        rs3.getString("no_rkm_medis"),
                        rs3.getString("nm_pasien"),
                        rs3.getString("nama"),
                        rs3.getString("dosis_arv"),
                        rs3.getString("lama_penggunaan"),
                        rs3.getString("tgl"),
                        rs3.getString("tgl_simpan"),
                        rs3.getString("tgl_catat")
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
    
    private void tampil5() {
        Valid.tabelKosong(tabMode4);
        try {           
            ps4 = koneksi.prepareStatement("SELECT h.urutan, p.no_rkm_medis, p.nm_pasien, h.pemeriksaan, DATE_FORMAT(h.tgl,'%d-%m-%Y') tgl, "
                    + "h.stad_who, h.bb, h.stts_fungsional, h.jumlah_cd4, h.lain_lain, h.tgl tglPemeriksaan FROM hiv_pemeriksaan_klinis_lab h "
                    + "inner join pasien p on p.no_rkm_medis=h.no_rkm_medis WHERE "
                    + "h.tgl between ? and ? and p.no_rkm_medis like ? or "
                    + "h.tgl between ? and ? and p.nm_pasien like ? or "
                    + "h.tgl between ? and ? and h.pemeriksaan like ? or "
                    + "h.tgl between ? and ? and h.stad_who like ? or "
                    + "h.tgl between ? and ? and h.bb like ? or "
                    + "h.tgl between ? and ? and h.stts_fungsional like ? or "
                    + "h.tgl between ? and ? and h.jumlah_cd4 like ? or "
                    + "h.tgl between ? and ? and h.lain_lain like ? order by h.urutan"); 
            try {
                ps4.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps4.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps4.setString(3, "%" + TCari.getText().trim() + "%");
                ps4.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps4.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps4.setString(6, "%" + TCari.getText().trim() + "%");
                ps4.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps4.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps4.setString(9, "%" + TCari.getText().trim() + "%");
                ps4.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps4.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps4.setString(12, "%" + TCari.getText().trim() + "%");
                ps4.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps4.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps4.setString(15, "%" + TCari.getText().trim() + "%");
                ps4.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps4.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps4.setString(18, "%" + TCari.getText().trim() + "%");
                ps4.setString(19, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps4.setString(20, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps4.setString(21, "%" + TCari.getText().trim() + "%");
                ps4.setString(22, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps4.setString(23, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps4.setString(24, "%" + TCari.getText().trim() + "%");
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode4.addRow(new Object[]{
                        rs4.getString("urutan"),
                        rs4.getString("no_rkm_medis"),
                        rs4.getString("nm_pasien"),
                        rs4.getString("pemeriksaan"),
                        rs4.getString("tgl"),
                        rs4.getString("stad_who"),
                        rs4.getString("bb"),
                        rs4.getString("stts_fungsional"),
                        rs4.getString("jumlah_cd4"),
                        rs4.getString("lain_lain"),
                        rs4.getString("tglPemeriksaan")
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
        LCount.setText("" + tabMode4.getRowCount());
    }
    
    private void tampil6() {
        Valid.tabelKosong(tabMode5);
        try {
            ps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(h.tanggal,'%d-%m-%Y') tglTerapi, h.jns_terapi_art, "
                    + "h.alasan, h.alasan_substitusi_lainnya, h.alasan_restart, h.nm_rejimen_baru, h.tgl_simpan, h.tanggal, "
                    + "h.alasan_fix FROM hiv_terapi_antiretroviral h inner join pasien p on p.no_rkm_medis=h.no_rkm_medis WHERE "
                    + "h.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "h.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "h.tanggal between ? and ? and h.jns_terapi_art like ? or "
                    + "h.tanggal between ? and ? and h.alasan like ? or "
                    + "h.tanggal between ? and ? and h.alasan_substitusi_lainnya like ? or "
                    + "h.tanggal between ? and ? and h.alasan_restart like ? or "
                    + "h.tanggal between ? and ? and h.nm_rejimen_baru like ? order by h.tanggal desc");
            try {
                ps5.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps5.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps5.setString(3, "%" + TCari.getText().trim() + "%");
                ps5.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps5.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps5.setString(6, "%" + TCari.getText().trim() + "%");
                ps5.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps5.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps5.setString(9, "%" + TCari.getText().trim() + "%");
                ps5.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps5.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps5.setString(12, "%" + TCari.getText().trim() + "%");
                ps5.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps5.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps5.setString(15, "%" + TCari.getText().trim() + "%");
                ps5.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps5.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps5.setString(18, "%" + TCari.getText().trim() + "%");
                ps5.setString(19, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps5.setString(20, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps5.setString(21, "%" + TCari.getText().trim() + "%");
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    tabMode5.addRow(new Object[]{
                        rs5.getString("no_rkm_medis"),
                        rs5.getString("nm_pasien"),
                        rs5.getString("tglTerapi"),
                        rs5.getString("jns_terapi_art"),
                        rs5.getString("alasan_fix"),
                        rs5.getString("alasan_substitusi_lainnya"),
                        rs5.getString("alasan_restart"),
                        rs5.getString("nm_rejimen_baru"),
                        rs5.getString("tgl_simpan"),
                        rs5.getString("tanggal"),
                        rs5.getString("alasan")
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
        LCount.setText("" + tabMode5.getRowCount());
    }
    
    private void tampil7() {
        Valid.tabelKosong(tabMode6);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, "
                        + "h.klasifikasi_tb, h.klasifikasi_tb_lokasi, h.tipe_tb, h.rejimen_tb, h.tmpt_pengobatan_tb, "
                        + "h.kabupaten, h.nm_sarana_kesehatan, h.no_reg_tb_kab_kota, DATE_FORMAT(h.tgl_mulai,'%d-%m-%Y') tglMulai, "
                        + "DATE_FORMAT(h.tgl_selesai,'%d-%m-%Y') tglSelesai, h.tgl_mulai, h.tgl_selesai "
                        + "FROM hiv_pengobatan_tb h INNER JOIN pasien p ON p.no_rkm_medis=h.no_rkm_medis WHERE "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien LIKE ? or "
                        + "h.klasifikasi_tb LIKE ? or "
                        + "h.klasifikasi_tb_lokasi LIKE ? or "
                        + "h.tipe_tb LIKE ? or "
                        + "h.rejimen_tb LIKE ? or "
                        + "h.tmpt_pengobatan_tb LIKE ? or "
                        + "h.kabupaten LIKE ? or "
                        + "h.nm_sarana_kesehatan LIKE ? or "
                        + "h.no_reg_tb_kab_kota LIKE ? ORDER BY h.no_rkm_medis DESC");
            } else {
                ps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, "
                        + "h.klasifikasi_tb, h.klasifikasi_tb_lokasi, h.tipe_tb, h.rejimen_tb, h.tmpt_pengobatan_tb, "
                        + "h.kabupaten, h.nm_sarana_kesehatan, h.no_reg_tb_kab_kota, DATE_FORMAT(h.tgl_mulai,'%d-%m-%Y') tglMulai, "
                        + "DATE_FORMAT(h.tgl_selesai,'%d-%m-%Y') tglSelesai, h.tgl_mulai, h.tgl_selesai "
                        + "FROM hiv_pengobatan_tb h INNER JOIN pasien p ON p.no_rkm_medis=h.no_rkm_medis WHERE "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien LIKE ? or "
                        + "h.klasifikasi_tb LIKE ? or "
                        + "h.klasifikasi_tb_lokasi LIKE ? or "
                        + "h.tipe_tb LIKE ? or "
                        + "h.rejimen_tb LIKE ? or "
                        + "h.tmpt_pengobatan_tb LIKE ? or "
                        + "h.kabupaten LIKE ? or "
                        + "h.nm_sarana_kesehatan LIKE ? or "
                        + "h.no_reg_tb_kab_kota LIKE ? ORDER BY h.no_rkm_medis DESC limit " + cmbLimit.getSelectedItem().toString() + "");
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
                        rs6.getString("no_rkm_medis"),
                        rs6.getString("nm_pasien"),
                        rs6.getString("klasifikasi_tb"),
                        rs6.getString("klasifikasi_tb_lokasi"),
                        rs6.getString("tipe_tb"),
                        rs6.getString("rejimen_tb"),
                        rs6.getString("tmpt_pengobatan_tb"),
                        rs6.getString("kabupaten"),
                        rs6.getString("nm_sarana_kesehatan"),
                        rs6.getString("no_reg_tb_kab_kota"),
                        rs6.getString("tglMulai"),
                        rs6.getString("tglSelesai"),
                        rs6.getString("tgl_mulai"),
                        rs6.getString("tgl_selesai")
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
    
    private void tampil8() {
        Valid.tabelKosong(tabMode7);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps7 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, h.pilihan_follow_up, "
                        + "if(h.tgl_meninggal='0000-00-00','-',DATE_FORMAT(h.tgl_meninggal,'%d-%m-%Y')) tglmati, "
                        + "if(h.tgl_kunjungan_akhir='0000-00-00','-',DATE_FORMAT(h.tgl_kunjungan_akhir,'%d-%m-%Y')) tglkunakhir, "
                        + "if(h.tgl_rujuk='0000-00-00','-',DATE_FORMAT(h.tgl_rujuk,'%d-%m-%Y')) tglrujuk, if(h.nm_klinik_baru='','-',h.nm_klinik_baru) nm_klinik_baru, "
                        + "if(h.tgl_meninggal='0000-00-00','',h.tgl_meninggal) tgl_meninggal, "
                        + "if(h.tgl_kunjungan_akhir='0000-00-00','',h.tgl_kunjungan_akhir) tgl_kunjungan_akhir, "
                        + "if(h.tgl_rujuk='0000-00-00','',h.tgl_rujuk) tgl_rujuk FROM hiv_akhir_follow_up h INNER JOIN pasien p ON p.no_rkm_medis = h.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? or "
                        + "p.nm_pasien LIKE ? or "
                        + "h.pilihan_follow_up LIKE ? or "
                        + "h.nm_klinik_baru LIKE ? ORDER BY h.no_rkm_medis DESC");
            } else {
                ps7 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, h.pilihan_follow_up, "
                        + "if(h.tgl_meninggal='0000-00-00','-',DATE_FORMAT(h.tgl_meninggal,'%d-%m-%Y')) tglmati, "
                        + "if(h.tgl_kunjungan_akhir='0000-00-00','-',DATE_FORMAT(h.tgl_kunjungan_akhir,'%d-%m-%Y')) tglkunakhir, "
                        + "if(h.tgl_rujuk='0000-00-00','-',DATE_FORMAT(h.tgl_rujuk,'%d-%m-%Y')) tglrujuk, if(h.nm_klinik_baru='','-',h.nm_klinik_baru) nm_klinik_baru, "
                        + "if(h.tgl_meninggal='0000-00-00','',h.tgl_meninggal) tgl_meninggal, "
                        + "if(h.tgl_kunjungan_akhir='0000-00-00','',h.tgl_kunjungan_akhir) tgl_kunjungan_akhir, "
                        + "if(h.tgl_rujuk='0000-00-00','',h.tgl_rujuk) tgl_rujuk FROM hiv_akhir_follow_up h INNER JOIN pasien p ON p.no_rkm_medis = h.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? or "
                        + "p.nm_pasien LIKE ? or "
                        + "h.pilihan_follow_up LIKE ? or "
                        + "h.nm_klinik_baru LIKE ? ORDER BY h.no_rkm_medis DESC limit " + cmbLimit.getSelectedItem().toString() + "");
            }
            
            try {
                ps7.setString(1, "%" + TCari.getText().trim() + "%");
                ps7.setString(2, "%" + TCari.getText().trim() + "%");
                ps7.setString(3, "%" + TCari.getText().trim() + "%");
                ps7.setString(4, "%" + TCari.getText().trim() + "%");
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    tabMode7.addRow(new Object[]{
                        rs7.getString("no_rkm_medis"),
                        rs7.getString("nm_pasien"),
                        rs7.getString("pilihan_follow_up"),
                        rs7.getString("tglmati"),
                        rs7.getString("tglkunakhir"),
                        rs7.getString("tglrujuk"),
                        rs7.getString("nm_klinik_baru"),
                        rs7.getString("tgl_meninggal"),
                        rs7.getString("tgl_kunjungan_akhir"),
                        rs7.getString("tgl_rujuk")
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
    
    private void tampil9() {
        Valid.tabelKosong(tabMode8);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps8 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(h.tgl_follow_up,'%d-%m-%Y') tglfollow, "
                        + "DATE_FORMAT(h.rencana_tgl_kunjungan,'%d-%m-%Y') tglRencana, "
                        + "h.bb, h.tb, h.stts_fungsional, h.stad_who, h.hamil, h.metode_kb, h.infeksi_oportunistik, h.infeksi_oportunistik_lainya, "
                        + "h.obat_utk_io, h.status_tb, h.ppk, h.obat_arv_dan_dosis_yg_diberikan, h.adherance_art, h.efek_samping_art, "
                        + "h.efek_samping_art_lain, h.jumlah_cd4, h.hasil_lab, h.diberikan_kondom, h.rujuk_ke_spesialis_atau_mrs, h.tgl_simpan, "
                        + "h.tgl_follow_up, h.rencana_tgl_kunjungan FROM hiv_follow_up_perawatan_dan_terapi h "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = h.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? OR "
                        + "p.nm_pasien LIKE ? OR "
                        + "h.bb LIKE ? OR "
                        + "h.tb LIKE ? OR "
                        + "h.stts_fungsional LIKE ? OR "
                        + "h.stad_who LIKE ? OR "
                        + "h.hamil LIKE ? OR "
                        + "h.metode_kb LIKE ? OR "
                        + "h.infeksi_oportunistik LIKE ? OR "
                        + "h.infeksi_oportunistik_lainya LIKE ? OR "
                        + "h.obat_utk_io LIKE ? OR "
                        + "h.status_tb LIKE ? OR "
                        + "h.ppk LIKE ? OR "
                        + "h.obat_arv_dan_dosis_yg_diberikan LIKE ? OR "
                        + "h.adherance_art LIKE ? OR "
                        + "h.efek_samping_art LIKE ? OR "
                        + "h.efek_samping_art_lain LIKE ? OR "
                        + "h.jumlah_cd4 LIKE ? OR "
                        + "h.hasil_lab LIKE ? OR "
                        + "h.diberikan_kondom LIKE ? OR "
                        + "h.rujuk_ke_spesialis_atau_mrs LIKE ? ORDER BY h.no_rkm_medis DESC");
            } else {
                ps8 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(h.tgl_follow_up,'%d-%m-%Y') tglfollow, "
                        + "DATE_FORMAT(h.rencana_tgl_kunjungan,'%d-%m-%Y') tglRencana, "
                        + "h.bb, h.tb, h.stts_fungsional, h.stad_who, h.hamil, h.metode_kb, h.infeksi_oportunistik, h.infeksi_oportunistik_lainya, "
                        + "h.obat_utk_io, h.status_tb, h.ppk, h.obat_arv_dan_dosis_yg_diberikan, h.adherance_art, h.efek_samping_art, "
                        + "h.efek_samping_art_lain, h.jumlah_cd4, h.hasil_lab, h.diberikan_kondom, h.rujuk_ke_spesialis_atau_mrs, h.tgl_simpan, "
                        + "h.tgl_follow_up, h.rencana_tgl_kunjungan FROM hiv_follow_up_perawatan_dan_terapi h "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = h.no_rkm_medis WHERE "
                        + "p.no_rkm_medis LIKE ? OR "
                        + "p.nm_pasien LIKE ? OR "
                        + "h.bb LIKE ? OR "
                        + "h.tb LIKE ? OR "
                        + "h.stts_fungsional LIKE ? OR "
                        + "h.stad_who LIKE ? OR "
                        + "h.hamil LIKE ? OR "
                        + "h.metode_kb LIKE ? OR "
                        + "h.infeksi_oportunistik LIKE ? OR "
                        + "h.infeksi_oportunistik_lainya LIKE ? OR "
                        + "h.obat_utk_io LIKE ? OR "
                        + "h.status_tb LIKE ? OR "
                        + "h.ppk LIKE ? OR "
                        + "h.obat_arv_dan_dosis_yg_diberikan LIKE ? OR "
                        + "h.adherance_art LIKE ? OR "
                        + "h.efek_samping_art LIKE ? OR "
                        + "h.efek_samping_art_lain LIKE ? OR "
                        + "h.jumlah_cd4 LIKE ? OR "
                        + "h.hasil_lab LIKE ? OR "
                        + "h.diberikan_kondom LIKE ? OR "
                        + "h.rujuk_ke_spesialis_atau_mrs LIKE ? ORDER BY h.no_rkm_medis DESC LIMIT " + cmbLimit.getSelectedItem().toString() + "");
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
                ps8.setString(13, "%" + TCari.getText().trim() + "%");
                ps8.setString(14, "%" + TCari.getText().trim() + "%");
                ps8.setString(15, "%" + TCari.getText().trim() + "%");                
                ps8.setString(16, "%" + TCari.getText().trim() + "%");
                ps8.setString(17, "%" + TCari.getText().trim() + "%");
                ps8.setString(18, "%" + TCari.getText().trim() + "%");
                ps8.setString(19, "%" + TCari.getText().trim() + "%");
                ps8.setString(20, "%" + TCari.getText().trim() + "%");                
                ps8.setString(21, "%" + TCari.getText().trim() + "%");
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    tabMode8.addRow(new Object[]{
                        rs8.getString("no_rkm_medis"),
                        rs8.getString("nm_pasien"),
                        rs8.getString("tglfollow"),
                        rs8.getString("tglRencana"),
                        rs8.getString("bb"),
                        rs8.getString("tb"),
                        rs8.getString("stts_fungsional"),
                        rs8.getString("stad_who"),
                        rs8.getString("hamil"),
                        rs8.getString("metode_kb"),                        
                        rs8.getString("infeksi_oportunistik"),
                        rs8.getString("infeksi_oportunistik_lainya"),
                        rs8.getString("obat_utk_io"),
                        rs8.getString("status_tb"),
                        rs8.getString("ppk"),
                        rs8.getString("obat_arv_dan_dosis_yg_diberikan"),
                        rs8.getString("adherance_art"),
                        rs8.getString("efek_samping_art"),
                        rs8.getString("efek_samping_art_lain"),
                        rs8.getString("jumlah_cd4"),
                        rs8.getString("hasil_lab"),
                        rs8.getString("diberikan_kondom"),                        
                        rs8.getString("rujuk_ke_spesialis_atau_mrs"),
                        rs8.getString("tgl_simpan"),
                        rs8.getString("tgl_follow_up"),
                        rs8.getString("rencana_tgl_kunjungan")                      
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
    
    private void AutoKodeKLG() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_keluarga,2),signed)),0) from hiv_riwayat_keluarga where "
                + "no_rkm_medis like '%" + TNoRM.getText() + "%' ", "-" + TNoRM.getText(), 2, kdKLG);
    }
    
    private void simpan12() {
        if (TnmPengawas.getText().equals("")) {
            Valid.textKosong(TnmPengawas, "Nama Pengawas Minum Obat (PMO)");
            TnmPengawas.requestFocus();
        } else if (cmbEntriPoin.getSelectedIndex() == 0) {
            Valid.textKosong(cmbEntriPoin, "Entry Point");
            cmbEntriPoin.requestFocus();
        } else if (cmbSttsNikah.getSelectedIndex() == 0) {
            Valid.textKosong(cmbSttsNikah, "Status Pernikahan");
            cmbSttsNikah.requestFocus();
        } else if (cmbEntriPoin.getSelectedIndex() == 2 && cmbEntriPoin2.getSelectedIndex() == 0) {
            Valid.textKosong(cmbEntriPoin2, "Entry Point 2. Rawat Jalan");
            cmbEntriPoin2.requestFocus();
        } else if (cmbEntriPoin2.getSelectedIndex() == 5 && T2ralanlainya.getText().trim().equals("")) {
            Valid.textKosong(T2ralanlainya, "Entry Point 2. Rawat Jalan, Lainnya");
            T2ralanlainya.requestFocus();
        } else if (cmbEntriPoin.getSelectedIndex() == 5 && cmbEntriPoin5.getSelectedIndex() == 0) {
            Valid.textKosong(cmbEntriPoin5, "Entry Point 5. Jangkauan");
            cmbEntriPoin5.requestFocus();
        } else if (cmbEntriPoin5.getSelectedIndex() == 4 && T5Jangkauan.getText().trim().equals("")) {
            Valid.textKosong(T5Jangkauan, "Entry Point 5. Jangkauan");
            T5Jangkauan.requestFocus();
        } else if (cmbEntriPoin.getSelectedIndex() == 8 && T8lainyaUraikan.getText().trim().equals("")) {
            Valid.textKosong(T8lainyaUraikan, "Entry Point 8. Lainnya, Uraikan");
            T8lainyaUraikan.requestFocus();
        } else if (cmbPndkn.getSelectedIndex() == 0) {
            Valid.textKosong(cmbPndkn, "Pendidikan");
            cmbPndkn.requestFocus();
        } else if (cmbFaktorResiko.getSelectedIndex() == 0) {
            Valid.textKosong(cmbFaktorResiko, "Faktor Resiko");
            cmbFaktorResiko.requestFocus();
        } else if (cmbFaktorResiko.getSelectedIndex() == 7 && T7lainUraikan.getText().trim().equals("")) {
            Valid.textKosong(T7lainUraikan, "Faktor Resiko, 7. Lain2");
            T7lainUraikan.requestFocus();
        } else if (Tkd.getText().equals("") || TnmRejimen.getText().equals("")) {
            Valid.textKosong(Tkd, "Nama Rejimen ART Orisinal");
            btnRejimen.requestFocus();
        } else if (cmbTerimaART.getSelectedIndex() == 0 && cmbJikaYA.getSelectedIndex() == 0) {
            Valid.textKosong(cmbJikaYA, "Jika Ya pernah menerima ART");
            cmbJikaYA.requestFocus();
        } else {
            pasienDirujuk = "";
            sttsPekerjaan = "";
            tglrujukmasuk = "";
            if (RtnpART.isSelected() == true && cmbRujukMasuk.getSelectedIndex() == 1) {
                pasienDirujuk = "Tanpa ART";
                tglrujukmasuk = Valid.SetTgl(TglRujukMsk.getSelectedItem() + "");
            } else if (RdgART.isSelected() == true && cmbRujukMasuk.getSelectedIndex() == 1) {
                pasienDirujuk = "Dengan ART";
                tglrujukmasuk = Valid.SetTgl(TglRujukMsk.getSelectedItem() + "");
            } else if (cmbRujukMasuk.getSelectedIndex() == 0) {
                pasienDirujuk = "";
                tglrujukmasuk = "0000-00-00";
            }

            if (RtdkBekerja.isSelected() == true) {
                sttsPekerjaan = "0. Tidak Bekerja";
            } else if (RBekerja.isSelected() == true) {
                sttsPekerjaan = "1. Bekerja";
            }

            Sequel.menyimpan("hiv_identitas_riwayat_pribadi",
                    "'" + TNoRM.getText() + "','" + TnoregNas.getText() + "','" + TnmPengawas.getText() + "',"
                    + "'" + cmbHubPasien.getSelectedItem().toString() + "',"
                    + "'" + TAlamatPMO.getText() + "','" + TnoTelpPMO.getText() + "',"
                    + "'" + TRiwAlergiObat.getText() + "','" + Valid.SetTgl(TglKonfirmasiTes.getSelectedItem() + "") + "',"
                    + "'" + cmbEntriPoin.getSelectedItem().toString() + "','" + T8lainyaUraikan.getText() + "',"
                    + "'" + cmbEntriPoin2.getSelectedItem().toString() + "','" + T2ralanlainya.getText() + "',"
                    + "'" + cmbEntriPoin5.getSelectedItem().toString() + "','" + T5Jangkauan.getText() + "',"
                    + "'" + pasienDirujuk + "','" + TnmKlinikSblmnya.getText() + "',"
                    + "'" + tglrujukmasuk + "','" + cmbPndkn.getSelectedItem().toString() + "',"
                    + "'" + sttsPekerjaan + "','" + cmbFaktorResiko.getSelectedItem().toString() + "',"
                    + "'" + T7lainUraikan.getText() + "','" + Tkd.getText() + "','" + cmbRujukMasuk.getSelectedItem().toString() + "',"
                    + "'" + cmbSttsNikah.getSelectedItem().toString() + "','" + cmbTerimaART.getSelectedItem().toString() + "',"
                    + "'" + cmbJikaYA.getSelectedItem().toString() + "','" + cmbTmpART.getSelectedItem().toString() + "'", "Data Identitas Pasien & Riwayat Pribadi");

            tampil12();
            emptText12();
        }
    }
    
    private void simpan3() {
        if (TnmKeluarga.getText().trim().equals("")) {
            Valid.textKosong(TnmKeluarga, "Nama Anggota Keluarga");
            TnmKeluarga.requestFocus();
        } else if (cmbHubKeluarga.getSelectedIndex() == 0) {
            Valid.textKosong(cmbHubKeluarga, "Hubungan Keluarga");
            cmbHubKeluarga.requestFocus();
        } else if (TumurKlg.getText().trim().equals("")) {
            Valid.textKosong(TumurKlg, "Umur");
            TumurKlg.requestFocus();
        } else {
            AutoKodeKLG();
            Sequel.menyimpan("hiv_riwayat_keluarga",
                    "'" + TNoRM.getText() + "','" + TnmKeluarga.getText() + "',"
                    + "'" + cmbHubKeluarga.getSelectedItem().toString() + "','" + TumurKlg.getText() + "',"
                    + "'" + cmbIDumurKlg.getSelectedItem().toString() + "','" + cmbHIVklg.getSelectedItem().toString() + "',"
                    + "'" + cmbARTklg.getSelectedItem().toString() + "','" + TnoRegNasKlg.getText() + "','" + kdKLG.getText() + "'", "Data Riwayat Keluarga");

            tampil3();
            emptText3();
        }
    }
    
    private void simpan4() {
        if (TnmDosis.getText().trim().equals("")) {
            Valid.textKosong(TnmDosis, "Nama");
            TnmDosis.requestFocus();
        } else if (TdosisARV.getText().trim().equals("")) {
            Valid.textKosong(TdosisARV, "Dosis ARV");
            TdosisARV.requestFocus();
        } else if (TlamaGuna.getText().trim().equals("")) {
            Valid.textKosong(TlamaGuna, "Lama Penggunaanya");
            TlamaGuna.requestFocus();
        } else {
            Sequel.menyimpan("hiv_riwayat_terapi",
                    "'" + TNoRM.getText() + "',now(),'" + TnmDosis.getText() + "','" + TdosisARV.getText() + "',"
                    + "'" + TlamaGuna.getText() + "','" + Valid.SetTgl(TglDicatat.getSelectedItem() + "") + "'", "Data Riwayat Terapi Antiretroviral");

            tampil4();
            emptText4();
        }
    }
    
    private void simpan5() {
        if (cmbPemeriksaan.getSelectedIndex() == 0) {
            Valid.textKosong(cmbPemeriksaan, "Item Pemeriksaan");
            cmbPemeriksaan.requestFocus();
        } else if (stadWHO.getText().trim().equals("")) {
            Valid.textKosong(stadWHO, "Stad WHO");
            stadWHO.requestFocus();
        } else if (bb.getText().trim().equals("")) {
            Valid.textKosong(bb, "BB");
            bb.requestFocus();
        } else {
            noUrut = "";
            if (cmbPemeriksaan.getSelectedIndex() == 1) {
                noUrut = "1";
            } else if (cmbPemeriksaan.getSelectedIndex() == 2) {
                noUrut = "2";
            } else if (cmbPemeriksaan.getSelectedIndex() == 3) {
                noUrut = "3";
            } else if (cmbPemeriksaan.getSelectedIndex() == 4) {
                noUrut = "4";
            } else if (cmbPemeriksaan.getSelectedIndex() == 5) {
                noUrut = "5";
            } else if (cmbPemeriksaan.getSelectedIndex() == 6) {
                noUrut = "6";
            }

            if (Sequel.cariInteger("select count(-1) from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' "
                    + "and urutan='" + noUrut + "' and tgl='" + Valid.SetTgl(tglPemeriksaan.getSelectedItem() + "") + "'") > 0) {
                JOptionPane.showMessageDialog(null, "Jenis pemeriksaan " + cmbPemeriksaan.getSelectedItem() + " sdh. tersimpan utk. tgl. yg. sama...!!");
            } else {
                Sequel.menyimpan("hiv_pemeriksaan_klinis_lab",
                        "'" + TNoRM.getText() + "','" + noUrut + "','" + cmbPemeriksaan.getSelectedItem().toString() + "',"
                        + "'" + Valid.SetTgl(tglPemeriksaan.getSelectedItem() + "") + "','" + stadWHO.getText() + "',"
                        + "'" + bb.getText() + "','" + cmbstsFung.getSelectedItem().toString() + "',"
                        + "'" + JLHcd4.getText() + "','" + lainLain.getText() + "'", "Data Pemeriksaan Klinis & Laboratorium");
                tampil5();
                emptText5();
            }
        }
    }
    
    private void simpan6() {
        if (Tkd.getText().equals("") || TnmRejimen.getText().equals("")) {
            Valid.textKosong(Tkd, "Nama Rejimen ART Orisinal");
            btnRejimen.requestFocus();
        } else if (Rsubstitusi.isSelected() == false && Rswitch.isSelected() == false
                && Rstop.isSelected() == false && Rrestart.isSelected() == false) {
            Valid.textKosong(Tkd1, "Jenis Terapi ART");
            Rsubstitusi.requestFocus();
        } else {
            alasanya = "";
            nilaiAlasan = "";
            jnsTerapi = "";
            if (Rsubstitusi.isSelected() == true) {
                jnsTerapi = Rsubstitusi.getText();
                nilaiAlasan = cmbAlasanSub.getSelectedItem().toString() + " " + TalasanSub.getText();
                alasanya = cmbAlasanSub.getSelectedItem().toString();
            } else if (Rswitch.isSelected() == true) {
                jnsTerapi = Rswitch.getText();
                nilaiAlasan = cmbAlasanSwi.getSelectedItem().toString();
                alasanya = cmbAlasanSwi.getSelectedItem().toString();
            } else if (Rstop.isSelected() == true) {
                jnsTerapi = Rstop.getText();
                nilaiAlasan = cmbAlasanSto.getSelectedItem().toString();
                alasanya = cmbAlasanSto.getSelectedItem().toString();
            } else if (Rrestart.isSelected() == true) {
                jnsTerapi = Rrestart.getText();
                nilaiAlasan = TalasanRes.getText();
            }            
            
            Sequel.menyimpan("hiv_terapi_antiretroviral",
                    "'" + TNoRM.getText() + "','" + Valid.SetTgl(tglTerapi.getSelectedItem() + "") + "',"
                    + "'" + jnsTerapi + "','" + alasanya + "','" + TalasanSub.getText() + "','" + TalasanRes.getText() + "',"
                    + "'" + TnmRejimenBaru.getText() + "',now(),'" + nilaiAlasan + "'", "Data Terapi Antiretroviral (ART)");
            tampil6();
            emptText6();
        }
    }
    
    private void simpan7() {
        if (cmbKlasifikasi.getSelectedIndex() == 1 && TklasifikasiLokasi.getText().trim().equals("")) {
            Valid.textKosong(TklasifikasiLokasi, "TB ekstra paru lokasi");
            TklasifikasiLokasi.requestFocus();
        } else {
            Sequel.menyimpan("hiv_pengobatan_tb",
                    "'" + TNoRM.getText() + "','" + cmbKlasifikasi.getSelectedItem().toString() + "',"
                    + "'" + TklasifikasiLokasi.getText() + "','" + cmbTipe.getSelectedItem().toString() + "',"
                    + "'" + cmbRejimen.getSelectedItem().toString() + "','" + TtmpPengobatan.getText() + "',"
                    + "'" + TKabupaten.getText() + "','" + Tnmsarana.getText() + "',"
                    + "'" + TnoReg.getText() + "','" + Valid.SetTgl(tglMulaitb.getSelectedItem() + "") + "',"
                    + "'" + Valid.SetTgl(tglSelesaitb.getSelectedItem() + "") + "'", "Data Pengobatan TB selama perawatan HIV");
            tampil7();
            emptText7();
        }
    }
    
    private void simpan8() {
        if (cmbAkhir.getSelectedIndex() == 2 && TnmKlinikBaru.getText().trim().equals("")) {
            Valid.textKosong(TnmKlinikBaru, "Nama Klinik baru");
            TnmKlinikBaru.requestFocus();
        } else {
            tglmeninggal = "";
            tglkunjungan = "";
            tglrujukkeluar = "";
            if (cmbAkhir.getSelectedIndex() == 0) {
                tglmeninggal = Valid.SetTgl(tglMati.getSelectedItem() + "");
                tglkunjungan = "0000-00-00";
                tglrujukkeluar = "0000-00-00";
            } else if (cmbAkhir.getSelectedIndex() == 1) {                
                tglmeninggal = "0000-00-00";
                tglkunjungan = Valid.SetTgl(tglKunAkhir.getSelectedItem() + "");
                tglrujukkeluar = "0000-00-00";
            } else if (cmbAkhir.getSelectedIndex() == 2) {                
                tglmeninggal = "0000-00-00";
                tglkunjungan = "0000-00-00";
                tglrujukkeluar = Valid.SetTgl(tglRujukKeluar.getSelectedItem() + "");
            }
            
            Sequel.menyimpan("hiv_akhir_follow_up",
                    "'" + TNoRM.getText() + "','" + cmbAkhir.getSelectedItem().toString() + "',"
                    + "'" + tglmeninggal + "','" + tglkunjungan + "',"
                    + "'" + tglrujukkeluar + "','" + TnmKlinikBaru.getText() + "'", "Data Akhir Follow-Up");
            tampil8();
            emptText8();
        }
    }
    
    private void simpan9() {
        if (TBB.getText().trim().equals("")) {
            Valid.textKosong(TBB, "Berat Badan (Kg.)");
            TBB.requestFocus();
        } else if (cmbInfeksiOpor.getSelectedIndex() == 11 && TInfeksiLain.getText().trim().equals("")) {
            Valid.textKosong(TInfeksiLain, "Infeksi Oportunistik (Lain-lain, Uraikan)");
            TInfeksiLain.requestFocus();
        } else if (cmbEfekSamping.getSelectedIndex() == 16 && TEfekSampingLain.getText().trim().equals("")) {
            Valid.textKosong(TEfekSampingLain, "Efek Samping ART (Lain, Uraikan)");
            TEfekSampingLain.requestFocus();
        } else {
            ppk = "";
            if (RY.isSelected() == true) {
                ppk = RY.getText();
            } else if (RT.isSelected() == true) {                
                ppk = RT.getText();
            }
            
            Sequel.menyimpan("hiv_follow_up_perawatan_dan_terapi",
                    "'" + TNoRM.getText() + "','" + Valid.SetTgl(TglFollowup.getSelectedItem() + "") + "',"
                    + "'" + Valid.SetTgl(TglRencKun.getSelectedItem() + "") + "','" + TBB.getText() + "',"
                    + "'" + TTB.getText() + "','" + cmbSttsFung.getSelectedItem().toString() + "','" + TstadWHO.getText() + "',"
                    + "'" + cmbHamil.getSelectedItem().toString() + "','" + TmetodeKB.getText() + "',"
                    + "'" + cmbInfeksiOpor.getSelectedItem().toString() + "','" + TInfeksiLain.getText() + "',"
                    + "'" + TobatUtkIO.getText() + "','" + cmbSttsTB.getSelectedItem().toString() + "','" + ppk + "',"
                    + "'" + TobatARV.getText() + "','" + cmbAdherance.getSelectedItem().toString() + "',"
                    + "'" + cmbEfekSamping.getSelectedItem().toString() + "','" + TEfekSampingLain.getText() + "',"
                    + "'" + TJumlahCD4.getText() + "','" + THasilLab.getText() + "','" + cmbDiberiKondom.getSelectedItem().toString() + "',"
                    + "'" + TRujukKeSpesialis.getText() + "',now()", "Data Follow-Up Perawatan Pasien & Terapi ART");
            tampil9();
            emptText9();
        }
    }
    
    private void hapus12() {
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            Sequel.queryu("delete from hiv_identitas_riwayat_pribadi where no_rkm_medis='" + TNoRM.getText() + "'");
            tampil12();
            emptText12();
            Tkd.setText("");
            TnmRejimen.setText("");
            Tkd1.setText("");
            TnmRejimen1.setText("");
        }
    }
    
    private void hapus3() {
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (kdKLG.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan dihapus...!!!!");
            tb3.requestFocus();
        } else if (TnmKeluarga.getText().equals("")) {
            Valid.textKosong(TnmKeluarga, "Nama Anggota Keluarga");
        } else {
            Sequel.queryu("delete from hiv_riwayat_keluarga where kd_keluarga='" + kdKLG.getText() + "'");
            tampil3();
            emptText3();
        }
    }
    
    private void hapus4() {
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (tglsimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan dihapus...!!!!");
            tb4.requestFocus();
        } else if (TnmDosis.getText().equals("")) {
            Valid.textKosong(TnmDosis, "Nama");
        } else if (TdosisARV.getText().equals("")) {
            Valid.textKosong(TdosisARV, "Dosis ARV");
        } else {
            Sequel.queryu("delete from hiv_riwayat_terapi where tgl_simpan='" + tglsimpan + "'");
            tampil4();
            emptText4();
        }
    }
    
    private void hapus5() {
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (noUrut.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan dihapus...!!!!");
            tb5.requestFocus();
        } else {
            Sequel.queryu("delete from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' "
                    + "and urutan='" + noUrut + "' and tgl='" + tb5.getValueAt(tb5.getSelectedRow(), 10).toString() + "'");
            tampil5();
            emptText5();
        }
    }
    
    private void hapus6() {
        if (tabMode5.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (tglsimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data Terapi Antiretroviral (ART) pada tabel yg. akan dihapus...!!!!");
            tb6.requestFocus();
        } else {
            Sequel.queryu("delete from hiv_terapi_antiretroviral where tgl_simpan='" + tglsimpan + "'");
            tampil6();
            emptText6();
        }
    }
    
    private void hapus7() {
        if (tabMode6.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            Sequel.queryu("delete from hiv_pengobatan_tb where no_rkm_medis='" + TNoRM.getText() + "'");
            tampil7();
            emptText7();
        }
    }
    
    private void hapus8() {
        if (tabMode7.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            Sequel.queryu("delete from hiv_akhir_follow_up where no_rkm_medis='" + TNoRM.getText() + "'");
            tampil8();
            emptText8();
        }
    }
    
    private void hapus9() {
        if (tabMode8.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (tglsimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data Follow-Up Perawatan Pasien & Terapi ART pada tabel yg. akan dihapus...!!!!");
            tb9.requestFocus();
        } else {
            Sequel.queryu("delete from hiv_follow_up_perawatan_dan_terapi where tgl_simpan='" + tglsimpan + "'");
            tampil9();
            emptText9();
        }
    }
    
    private void ganti12() {
        if (TnmPengawas.getText().equals("")) {
            Valid.textKosong(TnmPengawas, "Nama Pengawas Minum Obat (PMO)");
            TnmPengawas.requestFocus();
        } else if (cmbEntriPoin.getSelectedIndex() == 0) {
            Valid.textKosong(cmbEntriPoin, "Entry Point");
            cmbEntriPoin.requestFocus();
        } else if (cmbSttsNikah.getSelectedIndex() == 0) {
            Valid.textKosong(cmbSttsNikah, "Status Pernikahan");
            cmbSttsNikah.requestFocus();
        } else if (cmbEntriPoin.getSelectedIndex() == 2 && cmbEntriPoin2.getSelectedIndex() == 0) {
            Valid.textKosong(cmbEntriPoin2, "Entry Point 2. Rawat Jalan");
            cmbEntriPoin2.requestFocus();
        } else if (cmbEntriPoin2.getSelectedIndex() == 5 && T2ralanlainya.getText().trim().equals("")) {
            Valid.textKosong(T2ralanlainya, "Entry Point 2. Rawat Jalan, Lainnya");
            T2ralanlainya.requestFocus();
        } else if (cmbEntriPoin.getSelectedIndex() == 5 && cmbEntriPoin5.getSelectedIndex() == 0) {
            Valid.textKosong(cmbEntriPoin5, "Entry Point 5. Jangkauan");
            cmbEntriPoin5.requestFocus();
        } else if (cmbEntriPoin5.getSelectedIndex() == 4 && T5Jangkauan.getText().trim().equals("")) {
            Valid.textKosong(T5Jangkauan, "Entry Point 5. Jangkauan");
            T5Jangkauan.requestFocus();
        } else if (cmbEntriPoin.getSelectedIndex() == 8 && T8lainyaUraikan.getText().trim().equals("")) {
            Valid.textKosong(T8lainyaUraikan, "Entry Point 8. Lainnya, Uraikan");
            T8lainyaUraikan.requestFocus();
        } else if (cmbPndkn.getSelectedIndex() == 0) {
            Valid.textKosong(cmbPndkn, "Pendidikan");
            cmbPndkn.requestFocus();
        } else if (cmbFaktorResiko.getSelectedIndex() == 0) {
            Valid.textKosong(cmbFaktorResiko, "Faktor Resiko");
            cmbFaktorResiko.requestFocus();
        } else if (cmbFaktorResiko.getSelectedIndex() == 7 && T7lainUraikan.getText().trim().equals("")) {
            Valid.textKosong(T7lainUraikan, "Faktor Resiko, 7. Lain2");
            T7lainUraikan.requestFocus();
        } else if (Tkd.getText().equals("") || TnmRejimen.getText().equals("")) {
            Valid.textKosong(Tkd, "Nama Rejimen ART Orisinal");
            btnRejimen.requestFocus();
        } else if (cmbTerimaART.getSelectedIndex() == 0 && cmbJikaYA.getSelectedIndex() == 0) {
            Valid.textKosong(cmbJikaYA, "Jika Ya pernah menerima ART");
            cmbJikaYA.requestFocus();
        } else {
            pasienDirujuk = "";
            sttsPekerjaan = "";
            tglrujukmasuk = "";
            if (RtnpART.isSelected() == true && cmbRujukMasuk.getSelectedIndex() == 1) {
                pasienDirujuk = "Tanpa ART";
                tglrujukmasuk = Valid.SetTgl(TglRujukMsk.getSelectedItem() + "");
            } else if (RdgART.isSelected() == true && cmbRujukMasuk.getSelectedIndex() == 1) {
                pasienDirujuk = "Dengan ART";
                tglrujukmasuk = Valid.SetTgl(TglRujukMsk.getSelectedItem() + "");
            } else if (cmbRujukMasuk.getSelectedIndex() == 0) {
                pasienDirujuk = "";
                tglrujukmasuk = "0000-00-00";
            }

            if (RtdkBekerja.isSelected() == true) {
                sttsPekerjaan = "0. Tidak Bekerja";
            } else if (RBekerja.isSelected() == true) {
                sttsPekerjaan = "1. Bekerja";
            }

            Sequel.mengedit("hiv_identitas_riwayat_pribadi", "no_rkm_medis='" + TNoRM.getText() + "'",
                    "no_reg_nasional='" + TnoregNas.getText() + "',"
                    + "nm_pengawas_pmo='" + TnmPengawas.getText() + "', "
                    + "hubungan_dg_pasien='" + cmbHubPasien.getSelectedItem().toString() + "',"
                    + "alamat_pmo='" + TAlamatPMO.getText() + "', "
                    + "no_telpon_pmo='" + TnoTelpPMO.getText() + "',"
                    + "riwayat_alergi_obat='" + TRiwAlergiObat.getText() + "', "
                    + "tgl_konfirmasi_tes_hiv='" + Valid.SetTgl(TglKonfirmasiTes.getSelectedItem() + "") + "',"
                    + "entry_point='" + cmbEntriPoin.getSelectedItem().toString() + "',"
                    + "delapan_lainnya_uraikan='" + T8lainyaUraikan.getText() + "',"
                    + "rawat_jalan='" + cmbEntriPoin2.getSelectedItem().toString() + "',"
                    + "lainnya_rawat_jalan='" + T2ralanlainya.getText() + "',"
                    + "jangkauan='" + cmbEntriPoin5.getSelectedItem().toString() + "',"
                    + "lainnya_jangkauan='" + T5Jangkauan.getText() + "',"
                    + "dirujuk_msk_dari_klinik_lain='" + pasienDirujuk + "',"
                    + "nm_klinik_sebelumnya='" + TnmKlinikSblmnya.getText() + "',"
                    + "tgl_rujuk_msk='" + tglrujukmasuk + "',"
                    + "pendidikan='" + cmbPndkn.getSelectedItem().toString() + "',"
                    + "stts_pekerjaan='" + sttsPekerjaan + "',"
                    + "faktor_resiko='" + cmbFaktorResiko.getSelectedItem().toString() + "',"
                    + "lain2_faktor_resiko_uraian='" + T7lainUraikan.getText() + "',"
                    + "kd_rejimen='" + Tkd.getText() + "',"
                    + "dirujuk_masuk='" + cmbRujukMasuk.getSelectedItem().toString() + "',"
                    + "stts_pernikahan='" + cmbSttsNikah.getSelectedItem().toString() + "',"
                    + "menerima_art='" + cmbTerimaART.getSelectedItem().toString() + "',"
                    + "art_ya='" + cmbJikaYA.getSelectedItem().toString() + "',"
                    + "tmpt_art_dulu='" + cmbTmpART.getSelectedItem().toString() + "'");

            tampil12();
            emptText12();            
        }
    }
    
    private void ganti3() {
        if (kdKLG.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan diperbaiki...!!!!");
            tb3.requestFocus();
        } else if (TnmKeluarga.getText().trim().equals("")) {
            Valid.textKosong(TnmKeluarga, "Nama Anggota Keluarga");
            TnmKeluarga.requestFocus();
        } else if (cmbHubKeluarga.getSelectedIndex() == 0) {
            Valid.textKosong(cmbHubKeluarga, "Hubungan Keluarga");
            cmbHubKeluarga.requestFocus();
        } else if (TumurKlg.getText().trim().equals("")) {
            Valid.textKosong(TumurKlg, "Umur");
            TumurKlg.requestFocus();
        } else {
            Sequel.mengedit("hiv_riwayat_keluarga", "kd_keluarga='" + kdKLG.getText() + "'",
                    "nama='" + TnmKeluarga.getText() + "',"
                    + "hubungan='" + cmbHubKeluarga.getSelectedItem().toString() + "', "
                    + "umur='" + TumurKlg.getText() + "',"
                    + "sttsumur='" + cmbIDumurKlg.getSelectedItem().toString() + "', "
                    + "hiv='" + cmbHIVklg.getSelectedItem().toString() + "',"
                    + "art='" + cmbARTklg.getSelectedItem().toString() + "',"
                    + "no_reg_nasional='" + TnoRegNasKlg.getText() + "'");

            tampil3();
            emptText3();
        }
    }
    
    private void ganti4() {
        if (tglsimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan diperbaiki...!!!!");
            tb4.requestFocus();
        } else if (TnmDosis.getText().trim().equals("")) {
            Valid.textKosong(TnmDosis, "Nama");
            TnmDosis.requestFocus();
        } else if (TdosisARV.getText().trim().equals("")) {
            Valid.textKosong(TdosisARV, "Dosis ARV");
            TdosisARV.requestFocus();
        } else if (TlamaGuna.getText().trim().equals("")) {
            Valid.textKosong(TlamaGuna, "Lama Penggunaanya");
            TlamaGuna.requestFocus();
        } else {
            Sequel.mengedit("hiv_riwayat_terapi", "tgl_simpan='" + tglsimpan + "'",
                    "nama='" + TnmDosis.getText() + "',"
                    + "dosis_arv='" + TdosisARV.getText() + "', "
                    + "lama_penggunaan='" + TlamaGuna.getText() + "',"
                    + "tgl_catat='" + Valid.SetTgl(TglDicatat.getSelectedItem() + "") + "'");

            tampil4();
            emptText4();
        }
    }
    
    private void ganti5() {
        if (noUrut.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan diperbaiki...!!!!");
            tb5.requestFocus();
        } else if (cmbPemeriksaan.getSelectedIndex() == 0) {
            Valid.textKosong(cmbPemeriksaan, "Item Pemeriksaan");
            cmbPemeriksaan.requestFocus();
        } else if (stadWHO.getText().trim().equals("")) {
            Valid.textKosong(stadWHO, "Stad WHO");
            stadWHO.requestFocus();
        } else if (bb.getText().trim().equals("")) {
            Valid.textKosong(bb, "BB");
            bb.requestFocus();
        } else {
            Sequel.mengedit("hiv_pemeriksaan_klinis_lab", "no_rkm_medis='" + TNoRM.getText() + "' and "
                    + "urutan='" + noUrut + "' and tgl='" + tb5.getValueAt(tb5.getSelectedRow(), 10).toString() + "'",
                    "tgl='" + Valid.SetTgl(tglPemeriksaan.getSelectedItem() + "") + "', "
                    + "stad_who='" + stadWHO.getText() + "',"
                    + "bb='" + bb.getText() + "', "
                    + "stts_fungsional='" + cmbstsFung.getSelectedItem().toString() + "',"
                    + "jumlah_cd4='" + JLHcd4.getText() + "', "
                    + "lain_lain='" + lainLain.getText() + "'");
            tampil5();
            emptText5();
        }
    }
    
    private void ganti6() {
        if (tglsimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan diperbaiki...!!!!");
            Tkd.setText(Sequel.cariIsi("select kd_rejimen from hiv_identitas_riwayat_pribadi where no_rkm_medis='" + TNoRM.getText() + "'"));
            TnmRejimen.setText(Sequel.cariIsi("select nm_rejimen from hiv_master_rejimen where kd_rejimen='" + Tkd.getText() + "'"));
            Tkd1.setText(Tkd.getText());
            TnmRejimen1.setText(TnmRejimen.getText());
            tb6.requestFocus();
        } else if (Tkd.getText().equals("") || TnmRejimen.getText().equals("")) {
            Valid.textKosong(Tkd, "Nama Rejimen ART Orisinal");
            btnRejimen.requestFocus();
        } else if (Rsubstitusi.isSelected() == false && Rswitch.isSelected() == false
                && Rstop.isSelected() == false && Rrestart.isSelected() == false) {
            Valid.textKosong(Tkd1, "Jenis Terapi ART");
            Rsubstitusi.requestFocus();
        } else {
            alasanya = "";
            nilaiAlasan = "";
            jnsTerapi = "";
            if (Rsubstitusi.isSelected() == true) {
                jnsTerapi = Rsubstitusi.getText();
                nilaiAlasan = cmbAlasanSub.getSelectedItem().toString() + " " + TalasanSub.getText();
                alasanya = cmbAlasanSub.getSelectedItem().toString();
            } else if (Rswitch.isSelected() == true) {
                jnsTerapi = Rswitch.getText();
                nilaiAlasan = cmbAlasanSwi.getSelectedItem().toString();
                alasanya = cmbAlasanSwi.getSelectedItem().toString();
            } else if (Rstop.isSelected() == true) {
                jnsTerapi = Rstop.getText();
                nilaiAlasan = cmbAlasanSto.getSelectedItem().toString();
                alasanya = cmbAlasanSto.getSelectedItem().toString();
            } else if (Rrestart.isSelected() == true) {
                jnsTerapi = Rrestart.getText();
                nilaiAlasan = TalasanRes.getText();
            } 

            Sequel.mengedit("hiv_terapi_antiretroviral", "tgl_simpan='" + tglsimpan + "'",
                    "tanggal='" + Valid.SetTgl(tglTerapi.getSelectedItem() + "") + "',"
                    + "jns_terapi_art='" + jnsTerapi + "', "
                    + "alasan='" + alasanya + "',"
                    + "alasan_substitusi_lainnya='" + TalasanSub.getText() + "', "
                    + "alasan_restart='" + TalasanRes.getText() + "',"
                    + "nm_rejimen_baru='" + TnmRejimenBaru.getText() + "',"
                    + "alasan_fix='" + nilaiAlasan + "'");
            
            Sequel.mengedit("hiv_identitas_riwayat_pribadi", "no_rkm_medis='" + TNoRM.getText() + "'", "kd_rejimen='" + Tkd.getText() + "'");
            tampil6();
            emptText6();
        }
    }
    
    private void ganti7() {
        if (cmbKlasifikasi.getSelectedIndex() == 1 && TklasifikasiLokasi.getText().trim().equals("")) {
            Valid.textKosong(TklasifikasiLokasi, "TB ekstra paru lokasi");
            TklasifikasiLokasi.requestFocus();
        } else {
            Sequel.mengedit("hiv_pengobatan_tb", "no_rkm_medis='" + TNoRM.getText() + "'",
                    "klasifikasi_tb='" + cmbKlasifikasi.getSelectedItem().toString() + "',"
                    + "klasifikasi_tb_lokasi='" + TklasifikasiLokasi.getText() + "', "
                    + "tipe_tb='" + cmbTipe.getSelectedItem().toString() + "',"
                    + "rejimen_tb='" + cmbRejimen.getSelectedItem().toString() + "', "
                    + "tmpt_pengobatan_tb='" + TtmpPengobatan.getText() + "',"
                    + "kabupaten='" + TKabupaten.getText() + "',"
                    + "nm_sarana_kesehatan='" + Tnmsarana.getText() + "',"
                    + "no_reg_tb_kab_kota='" + TnoReg.getText() + "',"
                    + "tgl_mulai='" + Valid.SetTgl(tglMulaitb.getSelectedItem() + "") + "',"
                    + "tgl_selesai='" + Valid.SetTgl(tglSelesaitb.getSelectedItem() + "") + "'");

            tampil7();
            emptText7();
        }
    }
    
    private void ganti8() {
        if (cmbAkhir.getSelectedIndex() == 2 && TnmKlinikBaru.getText().trim().equals("")) {
            Valid.textKosong(TnmKlinikBaru, "Nama Klinik baru");
            TnmKlinikBaru.requestFocus();
        } else {
            tglmeninggal = "";
            tglkunjungan = "";
            tglrujukkeluar = "";
            if (cmbAkhir.getSelectedIndex() == 0) {
                tglmeninggal = Valid.SetTgl(tglMati.getSelectedItem() + "");
                tglkunjungan = "0000-00-00";
                tglrujukkeluar = "0000-00-00";
            } else if (cmbAkhir.getSelectedIndex() == 1) {                
                tglmeninggal = "0000-00-00";
                tglkunjungan = Valid.SetTgl(tglKunAkhir.getSelectedItem() + "");
                tglrujukkeluar = "0000-00-00";
            } else if (cmbAkhir.getSelectedIndex() == 2) {                
                tglmeninggal = "0000-00-00";
                tglkunjungan = "0000-00-00";
                tglrujukkeluar = Valid.SetTgl(tglRujukKeluar.getSelectedItem() + "");
            }
            
            Sequel.mengedit("hiv_akhir_follow_up", "no_rkm_medis='" + TNoRM.getText() + "'",
                    "pilihan_follow_up='" + cmbAkhir.getSelectedItem().toString() + "',"
                    + "tgl_meninggal='" + tglmeninggal + "', "
                    + "tgl_kunjungan_akhir='" + tglkunjungan + "',"
                    + "tgl_rujuk='" + tglrujukkeluar + "', "
                    + "nm_klinik_baru='" + TnmKlinikBaru.getText() + "'");

            tampil8();
            emptText8();
        }
    }
    
    private void ganti9() {
        if (tglsimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan diperbaiki...!!!!");            
            tb9.requestFocus();
        } else if (TBB.getText().trim().equals("")) {
            Valid.textKosong(TBB, "Berat Badan (Kg.)");
            TBB.requestFocus();
        } else if (cmbInfeksiOpor.getSelectedIndex() == 11 && TInfeksiLain.getText().trim().equals("")) {
            Valid.textKosong(TInfeksiLain, "Infeksi Oportunistik (Lain-lain, Uraikan)");
            TInfeksiLain.requestFocus();
        } else if (cmbEfekSamping.getSelectedIndex() == 16 && TEfekSampingLain.getText().trim().equals("")) {
            Valid.textKosong(TEfekSampingLain, "Efek Samping ART (Lain, Uraikan)");
            TEfekSampingLain.requestFocus();
        } else {
            ppk = "";
            if (RY.isSelected() == true) {
                ppk = RY.getText();
            } else if (RT.isSelected() == true) {                
                ppk = RT.getText();
            }
            
            Sequel.mengedit("hiv_follow_up_perawatan_dan_terapi", "tgl_simpan='" + tglsimpan + "'",
                    "tgl_follow_up='" + Valid.SetTgl(TglFollowup.getSelectedItem() + "") + "',"
                    + "rencana_tgl_kunjungan='" + Valid.SetTgl(TglRencKun.getSelectedItem() + "") + "', "
                    + "bb='" + TBB.getText() + "',"
                    + "tb='" + TTB.getText() + "', "
                    + "stts_fungsional='" + cmbSttsFung.getSelectedItem().toString() + "',"
                    + "stad_who='" + TstadWHO.getText() + "',"
                    + "hamil='" + cmbHamil.getSelectedItem().toString() + "',"
                    + "metode_kb='" + TmetodeKB.getText() + "',"
                    + "infeksi_oportunistik='" + cmbInfeksiOpor.getSelectedItem().toString() + "',"
                    + "infeksi_oportunistik_lainya='" + TInfeksiLain.getText() + "',"
                    + "obat_utk_io='" + TobatUtkIO.getText() + "',"
                    + "status_tb='" + cmbSttsTB.getSelectedItem().toString() + "',"
                    + "ppk='" + ppk + "',"
                    + "obat_arv_dan_dosis_yg_diberikan='" + TobatARV.getText() + "',"
                    + "adherance_art='" + cmbAdherance.getSelectedItem().toString() + "',"
                    + "efek_samping_art='" + cmbEfekSamping.getSelectedItem().toString() + "',"
                    + "efek_samping_art_lain='" + TEfekSampingLain.getText() + "',"
                    + "jumlah_cd4='" + TJumlahCD4.getText() + "',"
                    + "hasil_lab='" + THasilLab.getText() + "',"
                    + "diberikan_kondom='" + cmbDiberiKondom.getSelectedItem().toString() + "',"
                    + "rujuk_ke_spesialis_atau_mrs='" + TRujukKeSpesialis.getText() + "'");

            tampil9();
            emptText9();
        }
    }
    
    private void CetakIkhtisar() {
        cekEntriPoin = "";
        cekRujukMsk = "";
        faktorRes = "";
        tglrujukmasuk = "";
        artYA = "";
        klasTB = "";
        tglmeninggal = "";
        tglkunjungan = "";
        tglrujukkeluar = "";
        try {
            Sequel.queryu("delete from temporary_ikhtisar_hiv");
            psc12 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.tgl_lahir, h.no_reg_nasional, p.jk, h.riwayat_alergi_obat, "
                    + "h.nm_pengawas_pmo, h.hubungan_dg_pasien, h.no_telpon_pmo, "
                    + "h.alamat_pmo, h.tgl_konfirmasi_tes_hiv, h.entry_point, h.rawat_jalan, h.jangkauan, "
                    + "h.lainnya_rawat_jalan, h.lainnya_jangkauan, h.delapan_lainnya_uraikan, "
                    + "if(h.dirujuk_msk_dari_klinik_lain='','-',h.dirujuk_msk_dari_klinik_lain) dirujuk_msk_dari_klinik_lain, "
                    + "h.pendidikan, if(h.nm_klinik_sebelumnya='','-',h.nm_klinik_sebelumnya) nm_klinik_sebelumnya, h.stts_pekerjaan, "
                    + "if(h.tgl_rujuk_msk='0000-00-00','-',h.tgl_rujuk_msk) tgl_rujuk_msk, h.faktor_resiko, h.lain2_faktor_resiko_uraian, "
                    + "h.kd_rejimen, hm.nm_rejimen, h.dirujuk_masuk, h.stts_pernikahan, h.menerima_art, h.art_ya, h.tmpt_art_dulu FROM hiv_identitas_riwayat_pribadi h "
                    + "INNER JOIN hiv_master_rejimen hm ON hm.kd_rejimen=h.kd_rejimen inner join pasien p on p.no_rkm_medis=h.no_rkm_medis "
                    + "WHERE h.no_rkm_medis ='" + TNoRM.getText() + "'");
            try {
                rsc12 = psc12.executeQuery();
                while (rsc12.next()) {
                    validasiCetak();                    
                    riwayatKeluarga();
                    //spaci kosong sebaris
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'','','','','','','','','','','','','','','','','','',"
                            + "'','','','','','','','','','','','','','','','','','',''", "spaci kosong sebaris");
                    riwayatTerapiAntiretroviral();
                    //spaci kosong sebaris
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'','','','','','','','','','','','','','','','','','',"
                            + "'','','','','','','','','','','','','','','','','','',''", "spaci kosong sebaris");
                    pemeriksaanKlinisdanLab();
                    //spaci kosong sebaris
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'','','','','','','','','','','','','','','','','','',"
                            + "'','','','','','','','','','','','','','','','','','',''", "spaci kosong sebaris");
                    terapiAntiretroviral();                    
                    
                    //mencetak lembar ikhtisar perawatan hiv & terapi antiretroviral (arv)
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    
                    param.put("pasien", "Nama : " + rsc12.getString("nm_pasien") + "      No. Rekam Medis : " + rsc12.getString("no_rkm_medis"));
                    param.put("noreg_nasional", rsc12.getString("no_reg_nasional"));
                    param.put("jns_kelamin", rsc12.getString("jk").replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan")
                            + "     Tgl. Lahir : " + Valid.SetTglINDONESIA(rsc12.getString("tgl_lahir")));
                    param.put("riw_alergi_obat", rsc12.getString("riwayat_alergi_obat"));
                    param.put("nm_pengawas_minum_obat", rsc12.getString("nm_pengawas_pmo"));
                    param.put("hub_dg_pasien", rsc12.getString("hubungan_dg_pasien"));
                    param.put("alamat_notel_pmo", rsc12.getString("alamat_pmo") + " (" + rsc12.getString("no_telpon_pmo") + ")");
                    param.put("tgl_konfirmasi_hiv", Valid.SetTglINDONESIA(rsc12.getString("tgl_konfirmasi_tes_hiv")));
                    param.put("entri_poin", rsc12.getString("entry_point") + "" + cekEntriPoin);
                    param.put("ya_dirujuk_msk", cekRujukMsk);
                    param.put("dirujuk_msk_dari_klinik_lain", rsc12.getString("dirujuk_msk_dari_klinik_lain"));
                    param.put("nm_klinik_sebelumnya", rsc12.getString("nm_klinik_sebelumnya"));
                    param.put("tgl_rujuk_masuk", tglrujukmasuk);
                    param.put("pendidikan", rsc12.getString("pendidikan"));
                    param.put("stts_pekerjaan", rsc12.getString("stts_pekerjaan"));
                    param.put("fktr_resiko", rsc12.getString("faktor_resiko") + "" + faktorRes);
                    param.put("stts_pernikahan", rsc12.getString("stts_pernikahan"));

                    //pengobatan tb selama perawatan HIV
                    psc6 = koneksi.prepareStatement("SELECT klasifikasi_tb, klasifikasi_tb_lokasi, tipe_tb, rejimen_tb, tmpt_pengobatan_tb, "
                            + "kabupaten, nm_sarana_kesehatan, no_reg_tb_kab_kota, tgl_mulai, tgl_selesai "
                            + "FROM hiv_pengobatan_tb WHERE no_rkm_medis ='" + TNoRM.getText() + "'");
                    try {
                        rsc6 = psc6.executeQuery();
                        if (Sequel.cariInteger("select count(-1) from hiv_pengobatan_tb WHERE no_rkm_medis ='" + TNoRM.getText() + "'") == 0) {
                            param.put("klasifikasi_tb", "-");
                            param.put("tmp_pengobatan_tb", "-");
                            param.put("tipe_tb", "-");
                            param.put("kabupaten", "-");
                            param.put("rejimen_tb", "-");
                            param.put("nm_sarana_kesehatan", "-");
                            param.put("noreg_tb_kabkota", "-");
                            param.put("tgl_mulai_terapi_tb", "-");
                            param.put("tgl_selesai_terapi_tb", "-");
                        } else if (Sequel.cariInteger("select count(-1) from hiv_pengobatan_tb WHERE no_rkm_medis ='" + TNoRM.getText() + "'") > 0) {
                            while (rsc6.next()) {
                                if (rsc6.getString("klasifikasi_tb").equals("2. TB Ekstra paru lokasi")) {
                                    klasTB = " " + rsc6.getString("klasifikasi_tb_lokasi");
                                } else {
                                    klasTB = "";
                                }

                                param.put("klasifikasi_tb", rsc6.getString("klasifikasi_tb") + "" + klasTB);
                                param.put("tmp_pengobatan_tb", rsc6.getString("tmpt_pengobatan_tb"));
                                param.put("tipe_tb", rsc6.getString("tipe_tb"));
                                param.put("kabupaten", rsc6.getString("kabupaten"));
                                param.put("rejimen_tb", rsc6.getString("rejimen_tb"));
                                param.put("nm_sarana_kesehatan", rsc6.getString("nm_sarana_kesehatan"));
                                param.put("noreg_tb_kabkota", rsc6.getString("no_reg_tb_kab_kota"));
                                param.put("tgl_mulai_terapi_tb", Valid.SetTglINDONESIA(rsc6.getString("tgl_mulai")));
                                param.put("tgl_selesai_terapi_tb", Valid.SetTglINDONESIA(rsc6.getString("tgl_selesai")));
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Pengobatan TB selama perawatan HIV : " + e);
                    } finally {
                        if (rsc6 != null) {
                            rsc6.close();
                        }
                        if (psc6 != null) {
                            psc6.close();
                        }
                    }
                    
                    //Akhir Follow-Up
                    psc7 = koneksi.prepareStatement("SELECT pilihan_follow_up, if(tgl_meninggal='0000-00-00','-',tgl_meninggal) tglmati, "
                            + "if(tgl_kunjungan_akhir='0000-00-00','-',tgl_kunjungan_akhir) tglkunakhir, "
                            + "if(tgl_rujuk='0000-00-00','-',tgl_rujuk) tglrujuk, if(nm_klinik_baru='','-',nm_klinik_baru) nm_klinik_baru "
                            + "FROM hiv_akhir_follow_up WHERE no_rkm_medis ='" + TNoRM.getText() + "'");
                    try {
                        rsc7 = psc7.executeQuery();
                        if (Sequel.cariInteger("select count(-1) from hiv_akhir_follow_up WHERE no_rkm_medis ='" + TNoRM.getText() + "'") == 0) {
                            param.put("tgl_meninggal_dunia", "-");
                            param.put("tgl_kunjungan_terakhir", "-");
                            param.put("tgl_rujuk_keluar", "Tgl. -      Klinik : Baru -");
                        } else if (Sequel.cariInteger("select count(-1) from hiv_akhir_follow_up WHERE no_rkm_medis ='" + TNoRM.getText() + "'") > 0) {
                            while (rsc7.next()) {
                                if (rsc7.getString("tglmati").equals("-")) {
                                    tglmeninggal = "-";
                                } else {
                                    tglmeninggal = Valid.SetTglINDONESIA(rsc7.getString("tglmati"));
                                }

                                if (rsc7.getString("tglkunakhir").equals("-")) {
                                    tglkunjungan = "-";
                                } else {
                                    tglkunjungan = Valid.SetTglINDONESIA(rsc7.getString("tglkunakhir"));
                                }

                                if (rsc7.getString("tglrujuk").equals("-")) {
                                    tglrujukkeluar = "-";
                                } else {
                                    tglrujukkeluar = Valid.SetTglINDONESIA(rsc7.getString("tglrujuk"));
                                }

                                param.put("tgl_meninggal_dunia", tglmeninggal);
                                param.put("tgl_kunjungan_terakhir", tglkunjungan);
                                param.put("tgl_rujuk_keluar", "Tgl. " + tglrujukkeluar + "      Klinik : Baru " + rsc7.getString("nm_klinik_baru"));
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Akhir Follow-Up : " + e);
                    } finally {
                        if (rsc7 != null) {
                            rsc7.close();
                        }
                        if (psc7 != null) {
                            psc7.close();
                        }
                    }

                    Valid.MyReport("rptIkhtisarPerawatanHIV1.jasper", "report", "::[ Ikhtisar Perawatan HIV dan Terapi Antiretroviral (ART) ]::",
                            "select * from temporary_ikhtisar_hiv", param);                    
                }

            } catch (Exception e) {
                System.out.println("Notif Ikhtisar Perawatan HIV dan Terapi Antiretroviral (ART) : " + e);
            } finally {
                if (rsc12 != null) {
                    rsc12.close();
                }
                if (psc12 != null) {
                    psc12.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void pemeriksaanKlinisdanLab() {
        try {
            Sequel.menyimpan("temporary_ikhtisar_hiv", "'5. Pemeriksaan Klinis dan Laboratorium',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Pemeriksaan Klinis dan Laboratorium");
            
            Sequel.menyimpan("temporary_ikhtisar_hiv", "'','Tanggal (hh/bb/tt)','Stad WHO','BB','Status Fungsional','Jumlah Cd4 (Cd4 % pd anak2)','Lain-lain',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Pemeriksaan Klinis dan Laboratorium");

            //5. Pemeriksaan Klinis dan Laboratorium (1)
            psc51 = koneksi.prepareStatement("SELECT urutan, ifnull(pemeriksaan,'') pemeriksaan1, ifnull(DATE_FORMAT(tgl,'%d/%m/%Y'),'') tgl1, "
                    + "ifnull(stad_who,'') stad_who1, ifnull(bb,'') bb1, ifnull(stts_fungsional,'') stts_fungsional1, "
                    + "ifnull(jumlah_cd4,'') jumlah_cd41, ifnull(lain_lain,'') lain_lain1 FROM hiv_pemeriksaan_klinis_lab "
                    + "WHERE no_rkm_medis ='" + TNoRM.getText() + "' and urutan=1");
            try {
                rsc51 = psc51.executeQuery();
                while (rsc51.next()) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Kunjungan pertama',"
                            + "'" + rsc51.getString("tgl1") + "','" + rsc51.getString("stad_who1") + "',"
                            + "'" + rsc51.getString("bb1") + "','" + rsc51.getString("stts_fungsional1") + "',"
                            + "'" + rsc51.getString("jumlah_cd41") + "','" + rsc51.getString("lain_lain1") + "',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (1)");
                }
            } catch (Exception e) {
                System.out.println("Notif 5. Pemeriksaan Klinis dan Laboratorium (1) : " + e);
            } finally {
                if (rsc51 != null) {
                    rsc51.close();
                }
                if (psc51 != null) {
                    psc51.close();
                }
            }

            if (Sequel.cariInteger("select count(-1) from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' and urutan=1") == 0) {
                Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Kunjungan pertama',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (1)");
            } else {
                Sequel.queryu("delete from temporary_ikhtisar_hiv where temp1='    Kunjungan pertama' and temp2=''");
            }

            //5. Pemeriksaan Klinis dan Laboratorium (2)
            psc52 = koneksi.prepareStatement("SELECT urutan, ifnull(pemeriksaan,'') pemeriksaan2, ifnull(DATE_FORMAT(tgl,'%d/%m/%Y'),'') tgl2, "
                    + "ifnull(stad_who,'') stad_who2, ifnull(bb,'') bb2, ifnull(stts_fungsional,'') stts_fungsional2, "
                    + "ifnull(jumlah_cd4,'') jumlah_cd42, ifnull(lain_lain,'') lain_lain2 FROM hiv_pemeriksaan_klinis_lab "
                    + "WHERE no_rkm_medis ='" + TNoRM.getText() + "' and urutan=2");
            try {
                rsc52 = psc52.executeQuery();
                while (rsc52.next()) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Memenuhi syarat medis untuk ART',"
                            + "'" + rsc52.getString("tgl2") + "','" + rsc52.getString("stad_who2") + "',"
                            + "'" + rsc52.getString("bb2") + "','" + rsc52.getString("stts_fungsional2") + "',"
                            + "'" + rsc52.getString("jumlah_cd42") + "','" + rsc52.getString("lain_lain2") + "',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (2)");
                }
            } catch (Exception e) {
                System.out.println("Notif 5. Pemeriksaan Klinis dan Laboratorium (2) : " + e);
            } finally {
                if (rsc52 != null) {
                    rsc52.close();
                }
                if (psc52 != null) {
                    psc52.close();
                }
            }

            if (Sequel.cariInteger("select count(-1) from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' and urutan=2") == 0) {
                Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Memenuhi syarat medis untuk ART',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (2)");
            } else {
                Sequel.queryu("delete from temporary_ikhtisar_hiv where temp1='    Memenuhi syarat medis untuk ART' and temp2=''");
            }

            //5. Pemeriksaan Klinis dan Laboratorium (3)
            psc53 = koneksi.prepareStatement("SELECT urutan, ifnull(pemeriksaan,'') pemeriksaan3, ifnull(DATE_FORMAT(tgl,'%d/%m/%Y'),'') tgl3, "
                    + "ifnull(stad_who,'') stad_who3, ifnull(bb,'') bb3, ifnull(stts_fungsional,'') stts_fungsional3, "
                    + "ifnull(jumlah_cd4,'') jumlah_cd43, ifnull(lain_lain,'') lain_lain3 FROM hiv_pemeriksaan_klinis_lab "
                    + "WHERE no_rkm_medis ='" + TNoRM.getText() + "' and urutan=3");
            try {
                rsc53 = psc53.executeQuery();
                while (rsc53.next()) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Saat mulai ART',"
                            + "'" + rsc53.getString("tgl3") + "','" + rsc53.getString("stad_who3") + "',"
                            + "'" + rsc53.getString("bb3") + "','" + rsc53.getString("stts_fungsional3") + "',"
                            + "'" + rsc53.getString("jumlah_cd43") + "','" + rsc53.getString("lain_lain3") + "',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (3)");
                }
            } catch (Exception e) {
                System.out.println("Notif 5. Pemeriksaan Klinis dan Laboratorium (3) : " + e);
            } finally {
                if (rsc53 != null) {
                    rsc53.close();
                }
                if (psc53 != null) {
                    psc53.close();
                }
            }

            if (Sequel.cariInteger("select count(-1) from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' and urutan=3") == 0) {
                Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Saat mulai ART',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (3)");
            } else {
                Sequel.queryu("delete from temporary_ikhtisar_hiv where temp1='    Saat mulai ART' and temp2=''");
            }

            //5. Pemeriksaan Klinis dan Laboratorium (4)
            psc54 = koneksi.prepareStatement("SELECT urutan, ifnull(pemeriksaan,'') pemeriksaan4, ifnull(DATE_FORMAT(tgl,'%d/%m/%Y'),'') tgl4, "
                    + "ifnull(stad_who,'') stad_who4, ifnull(bb,'') bb4, ifnull(stts_fungsional,'') stts_fungsional4, "
                    + "ifnull(jumlah_cd4,'') jumlah_cd44, ifnull(lain_lain,'') lain_lain4 FROM hiv_pemeriksaan_klinis_lab "
                    + "WHERE no_rkm_medis ='" + TNoRM.getText() + "' and urutan=4");
            try {
                rsc54 = psc54.executeQuery();
                while (rsc54.next()) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Setelah 6 bulan ART',"
                            + "'" + rsc54.getString("tgl4") + "','" + rsc54.getString("stad_who4") + "',"
                            + "'" + rsc54.getString("bb4") + "','" + rsc54.getString("stts_fungsional4") + "',"
                            + "'" + rsc54.getString("jumlah_cd44") + "','" + rsc54.getString("lain_lain4") + "',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (4)");
                }
            } catch (Exception e) {
                System.out.println("Notif 5. Pemeriksaan Klinis dan Laboratorium (4) : " + e);
            } finally {
                if (rsc54 != null) {
                    rsc54.close();
                }
                if (psc54 != null) {
                    psc54.close();
                }
            }

            if (Sequel.cariInteger("select count(-1) from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' and urutan=4") == 0) {
                Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Setelah 6 bulan ART',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (4)");
            } else {
                Sequel.queryu("delete from temporary_ikhtisar_hiv where temp1='    Setelah 6 bulan ART' and temp2=''");
            }

            //5. Pemeriksaan Klinis dan Laboratorium (5)
            psc55 = koneksi.prepareStatement("SELECT urutan, ifnull(pemeriksaan,'') pemeriksaan5, ifnull(DATE_FORMAT(tgl,'%d/%m/%Y'),'') tgl5, "
                    + "ifnull(stad_who,'') stad_who5, ifnull(bb,'') bb5, ifnull(stts_fungsional,'') stts_fungsional5, "
                    + "ifnull(jumlah_cd4,'') jumlah_cd45, ifnull(lain_lain,'') lain_lain5 FROM hiv_pemeriksaan_klinis_lab "
                    + "WHERE no_rkm_medis ='" + TNoRM.getText() + "' and urutan=5");
            try {
                rsc55 = psc55.executeQuery();
                while (rsc55.next()) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Setelah 12 bulan ART',"
                            + "'" + rsc55.getString("tgl5") + "','" + rsc55.getString("stad_who5") + "',"
                            + "'" + rsc55.getString("bb5") + "','" + rsc55.getString("stts_fungsional5") + "',"
                            + "'" + rsc55.getString("jumlah_cd45") + "','" + rsc55.getString("lain_lain5") + "',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (5)");
                }
            } catch (Exception e) {
                System.out.println("Notif 5. Pemeriksaan Klinis dan Laboratorium (5) : " + e);
            } finally {
                if (rsc55 != null) {
                    rsc55.close();
                }
                if (psc55 != null) {
                    psc55.close();
                }
            }

            if (Sequel.cariInteger("select count(-1) from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' and urutan=5") == 0) {
                Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Setelah 12 bulan ART',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (5)");
            } else {
                Sequel.queryu("delete from temporary_ikhtisar_hiv where temp1='    Setelah 12 bulan ART' and temp2=''");
            }

            //5. Pemeriksaan Klinis dan Laboratorium (6)
            psc56 = koneksi.prepareStatement("SELECT urutan, ifnull(pemeriksaan,'') pemeriksaan6, ifnull(DATE_FORMAT(tgl,'%d/%m/%Y'),'') tgl6, "
                    + "ifnull(stad_who,'') stad_who6, ifnull(bb,'') bb6, ifnull(stts_fungsional,'') stts_fungsional6, "
                    + "ifnull(jumlah_cd4,'') jumlah_cd46, ifnull(lain_lain,'') lain_lain6 FROM hiv_pemeriksaan_klinis_lab "
                    + "WHERE no_rkm_medis ='" + TNoRM.getText() + "' and urutan=6");
            try {
                rsc56 = psc56.executeQuery();
                while (rsc56.next()) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Setelah 24 bulan ART',"
                            + "'" + rsc56.getString("tgl6") + "','" + rsc56.getString("stad_who6") + "',"
                            + "'" + rsc56.getString("bb6") + "','" + rsc56.getString("stts_fungsional6") + "',"
                            + "'" + rsc56.getString("jumlah_cd46") + "','" + rsc56.getString("lain_lain6") + "',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (6)");
                }
            } catch (Exception e) {
                System.out.println("Notif 5. Pemeriksaan Klinis dan Laboratorium (6) : " + e);
            } finally {
                if (rsc56 != null) {
                    rsc56.close();
                }
                if (psc56 != null) {
                    psc56.close();
                }
            }

            if (Sequel.cariInteger("select count(-1) from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' and urutan=6") == 0) {
                Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Setelah 24 bulan ART',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "5. Pemeriksaan Klinis dan Laboratorium (6)");
            } else {
                Sequel.queryu("delete from temporary_ikhtisar_hiv where temp1='    Setelah 24 bulan ART' and temp2=''");
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void riwayatKeluarga() {
        try {
            Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Nama','Hubungan','Umur','HIV +/-','ART +/tdk','No. Reg. Nas.',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Riwayat Keluarga");

            psc3 = koneksi.prepareStatement("SELECT nama, hubungan, CONCAT(umur,' ',sttsumur) umur, hiv, art, no_reg_nasional, "
                    + "kd_keluarga FROM hiv_riwayat_keluarga WHERE no_rkm_medis='" + TNoRM.getText() + "' order by kd_keluarga");
            try {
                rsc3 = psc3.executeQuery();
                if (Sequel.cariInteger("select count(-1) from hiv_riwayat_keluarga WHERE no_rkm_medis='" + TNoRM.getText() + "'") == 0) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    -','-','-','-','-','-',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Riwayat Keluarga");
                } else if (Sequel.cariInteger("select count(-1) from hiv_riwayat_keluarga WHERE no_rkm_medis='" + TNoRM.getText() + "'") > 0) {
                    while (rsc3.next()) {
                        Sequel.menyimpan("temporary_ikhtisar_hiv", "'    " + rsc3.getString("nama") + "','" + rsc3.getString("hubungan") + "',"
                                + "'" + rsc3.getString("umur").replaceAll("Th", "tahun").replaceAll("Bl", "bulan").replaceAll("Hr", "hari") + "',"
                                + "'" + rsc3.getString("hiv") + "','" + rsc3.getString("art") + "','" + rsc3.getString("no_reg_nasional") + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Riwayat Keluarga");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif Riwayat Keluarga : " + e);
            } finally {
                if (rsc3 != null) {
                    rsc3.close();
                }
                if (psc3 != null) {
                    psc3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void validasiCetak() {
        try {
            if (rsc12.getString("entry_point").equals("2. Rawat Jalan")) {
                if (rsc12.getString("rawat_jalan").equals("Lainnya")) {
                    cekEntriPoin = ", " + rsc12.getString("rawat_jalan") + " : " + rsc12.getString("lainnya_rawat_jalan");
                } else {
                    cekEntriPoin = " (" + rsc12.getString("rawat_jalan") + ")";
                }
            } else if (rsc12.getString("entry_point").equals("5. Jangkauan")) {
                if (rsc12.getString("jangkauan").equals("Lainnya")) {
                    cekEntriPoin = ", " + rsc12.getString("jangkauan") + " : " + rsc12.getString("lainnya_jangkauan");
                } else {
                    cekEntriPoin = " (" + rsc12.getString("jangkauan") + ")";
                }
            } else if (rsc12.getString("entry_point").equals("8. Lainnya")) {
                cekEntriPoin = ", Uraikan : " + rsc12.getString("delapan_lainnya_uraikan");
            } else {
                cekEntriPoin = "";
            }

            if (rsc12.getString("dirujuk_masuk").equals("Ya")) {
                cekRujukMsk = "V";
            } else {
                cekRujukMsk = "X";
            }

            if (rsc12.getString("faktor_resiko").equals("7. Lain-lain")) {
                faktorRes = ", Uraikan : " + rsc12.getString("lain2_faktor_resiko_uraian");
            } else {
                faktorRes = "";
            }

            if (rsc12.getString("tgl_rujuk_msk").equals("-")) {
                tglrujukmasuk = "-";
            } else {
                tglrujukmasuk = Valid.SetTglINDONESIA(rsc12.getString("tgl_rujuk_msk"));
            }
           
            if (rsc12.getString("menerima_art").equals("Ya")) {
                artYA = " ; " + rsc12.getString("art_ya");
            } else {
                artYA = "";
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void riwayatTerapiAntiretroviral() {
        try {
            Sequel.menyimpan("temporary_ikhtisar_hiv", "'4. Riwayat Terapi Antiretroviral',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Riwayat Terapi Antiretroviral");

            Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Pernah menerima ART ? " + rsc12.getString("menerima_art") + "" + artYA + "',"
                    + "'Tempat ART dulu : " + rsc12.getString("tmpt_art_dulu") + "',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Riwayat Terapi Antiretroviral");

            Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Nama, dosis ARV & Lama Penggunaanya',':',"
                    +"'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Riwayat Terapi Antiretroviral");
            
            psc4 = koneksi.prepareStatement("SELECT nama, dosis_arv, lama_penggunaan, DATE_FORMAT(tgl_catat,'%d-%m-%Y') tgl, tgl_catat "
                    + "FROM hiv_riwayat_terapi WHERE no_rkm_medis ='" + TNoRM.getText() + "' ORDER BY tgl_simpan desc");
            try {
                rsc4 = psc4.executeQuery();
                if (Sequel.cariInteger("select count(-1) from hiv_riwayat_terapi WHERE no_rkm_medis ='" + TNoRM.getText() + "'") == 0) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    -','-','-',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Riwayat Terapi Antiretroviral");
                } else if (Sequel.cariInteger("select count(-1) from hiv_riwayat_terapi WHERE no_rkm_medis ='" + TNoRM.getText() + "'") > 0) {
                    while (rsc4.next()) {
                        Sequel.menyimpan("temporary_ikhtisar_hiv", "'    " + rsc4.getString("nama") + "',"
                                + "'" + rsc4.getString("dosis_arv") + "','" + rsc4.getString("lama_penggunaan") + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Riwayat Terapi Antiretroviral");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif Riwayat Terapi Antiretroviral : " + e);
            } finally {
                if (rsc4 != null) {
                    rsc4.close();
                }
                if (psc4 != null) {
                    psc4.close();
                }
            }
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cekIdentitasRiwayat(String nrm) {
        try {
            psCek = koneksi.prepareStatement("select ifnull(kd_rejimen,'') kdRej, ifnull(stts_pernikahan,'') stsNikah, "
                    + "ifnull(menerima_art,'') nerimaART, ifnull(art_ya,'') artYa, ifnull(tmpt_art_dulu,'') tmpART, "
                    + "ifnull(no_reg_nasional,'') no_reg_nasional from hiv_identitas_riwayat_pribadi where no_rkm_medis='" + nrm + "'");
            try {
                rsCek = psCek.executeQuery();
                while (rsCek.next()) {
                    TnoregNas.setText(rsCek.getString("no_reg_nasional"));
                    Tkd.setText(rsCek.getString("kdRej"));
                    TnmRejimen.setText(Sequel.cariIsi("select nm_rejimen from hiv_master_rejimen where kd_rejimen='" + Tkd.getText() + "'"));
                    TsttsPernikahan.setText(rsCek.getString("stsNikah"));
                    TpernahTerimaART.setText(rsCek.getString("nerimaART"));
                    TjikaYA.setText(rsCek.getString("artYa"));
                    TtempatART.setText(rsCek.getString("tmpART"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsCek != null) {
                    rsCek.close();
                }
                if (psCek != null) {
                    psCek.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cekPasien(String nrm) {
        try {
            pspas = koneksi.prepareStatement("SELECT no_rkm_medis, nm_pasien, jk, tgl_lahir FROM pasien WHERE no_rkm_medis='" + nrm + "'");
            try {
                rspas = pspas.executeQuery();
                while (rspas.next()) {
                    TNoRM.setText(rspas.getString("no_rkm_medis"));
                    TPasien.setText(rspas.getString("nm_pasien"));
                    Tjk.setText(rspas.getString("jk").replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan"));
                    TtglLahir.setText(Valid.SetTglINDONESIA(rspas.getString("tgl_lahir")));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rspas != null) {
                    rspas.close();
                }
                if (pspas != null) {
                    pspas.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void terapiAntiretroviral() {
        try {
            Sequel.menyimpan("temporary_ikhtisar_hiv", "'6. Terapi Antiretroviral (ART)','','','','','','','','','','','','','','','','','',"
                    + "'','','','','','','','','','','','','','','','','','',''", "Terapi Antiretroviral (ART)");

            Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Nama Rejimen ART Orisinal',': " + rsc12.getString("nm_rejimen") + "',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Terapi Antiretroviral (ART)");

            Sequel.menyimpan("temporary_ikhtisar_hiv", "'    SUBSTITUSI dalam lini-1, SWITCH ke lini-2, STOP','','','','','','','','','','','','','','','','','',"
                    + "'','','','','','','','','','','','','','','','','','',''", "Terapi Antiretroviral (ART)");

            Sequel.menyimpan("temporary_ikhtisar_hiv", "'    Tanggal','Substitusi','Switch','Stop','Restart','Alasan','Nama Rejimen Baru',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Terapi Antiretroviral (ART)");

            psc5 = koneksi.prepareStatement("SELECT DATE_FORMAT(tanggal,'%d-%m-%Y') tglTerapi, IF (jns_terapi_art='Substitusi','V','') substitusi, "
                    + "IF (jns_terapi_art='Switch','V','') switch, IF (jns_terapi_art='Stop','V','') stop, IF (jns_terapi_art='Restart','V','') restart, "
                    + "alasan_fix, nm_rejimen_baru FROM hiv_terapi_antiretroviral WHERE no_rkm_medis='" + TNoRM.getText() + "' ORDER BY tanggal");
            try {
                rsc5 = psc5.executeQuery();
                if (Sequel.cariInteger("select count(-1) from hiv_terapi_antiretroviral WHERE no_rkm_medis='" + TNoRM.getText() + "'") == 0) {
                    Sequel.menyimpan("temporary_ikhtisar_hiv", "'    -','-','-','-','-','-','-',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Terapi Antiretroviral (ART)");
                } else if (Sequel.cariInteger("select count(-1) from hiv_terapi_antiretroviral WHERE no_rkm_medis='" + TNoRM.getText() + "'") > 0) {
                    while (rsc5.next()) {
                        Sequel.menyimpan("temporary_ikhtisar_hiv", "'    " + rsc5.getString("tglTerapi") + "','" + rsc5.getString("substitusi") + "',"
                                + "'" + rsc5.getString("switch") + "','" + rsc5.getString("stop") + "','" + rsc5.getString("restart") + "',"
                                + "'" + rsc5.getString("alasan_fix") + "','" + rsc5.getString("nm_rejimen_baru") + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Terapi Antiretroviral (ART)");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif Terapi Antiretroviral (ART) : " + e);
            } finally {
                if (rsc5 != null) {
                    rsc5.close();
                }
                if (psc5 != null) {
                    psc5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cetak9FollowUP() {
        if (Sequel.cariInteger("select count(-1) from hiv_follow_up_perawatan_dan_terapi where no_rkm_medis = '" + TNoRM.getText() + "'") == 0) {
//            JOptionPane.showMessageDialog(rootPane, "Laporan Follow-Up Perawatan Pasien An. " + TPasien.getText() + " & Terapi Antiretroviral belum pernah tersimpan..!!");
            System.out.println("Laporan Follow-Up Perawatan Pasien An. " + TPasien.getText() + " & Terapi Antiretroviral belum pernah tersimpan..!!");
        } else if (Sequel.cariInteger("select count(-1) from hiv_follow_up_perawatan_dan_terapi where no_rkm_medis = '" + TNoRM.getText() + "'") > 0) {
            Map<String, Object> param = new HashMap<>();
            Valid.MyReport("rptIkhtisarPerawatanHIV2.jasper", "report", "::[ 9. Follow-Up Perawatan Pasien & Terapi Antiretroviral ]::",
                    "SELECT CONCAT('Nama : ',p.nm_pasien,'    No. Rekam Medis : ',p.no_rkm_medis) pasien, DATE_FORMAT(h.tgl_follow_up,'%d/%m/%Y') tglfollow, "
                    + "DATE_FORMAT(h.rencana_tgl_kunjungan,'%d/%m/%Y') tglRencana, CONCAT(IF(h.bb='','-',CONCAT(h.bb,' Kg')),', ',IF(h.tb='','-',CONCAT(h.tb,' Cm'))) bbtb, "
                    + "h.stts_fungsional, h.stad_who, CONCAT(h.hamil,' ',h.metode_kb) hamil, CONCAT(h.infeksi_oportunistik,' ',h.infeksi_oportunistik_lainya) infeksi_oportunistik, "
                    + "h.obat_utk_io, h.status_tb, h.ppk, h.obat_arv_dan_dosis_yg_diberikan, h.adherance_art, "
                    + "CONCAT(h.efek_samping_art,' ',h.efek_samping_art_lain) efek_samping_art, h.jumlah_cd4, h.hasil_lab, "
                    + "h.diberikan_kondom, h.rujuk_ke_spesialis_atau_mrs FROM hiv_follow_up_perawatan_dan_terapi h "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = h.no_rkm_medis WHERE p.no_rkm_medis = '" + TNoRM.getText() + "'", param);
        }
    }
}
