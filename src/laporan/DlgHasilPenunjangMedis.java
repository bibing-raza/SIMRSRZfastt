package laporan;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author dosen
 */
public class DlgHasilPenunjangMedis extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabModePembaca, tabModePembaca1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, psPrev;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rsPrev;
    private int i = 0, x = 0, cekRujukan = 0;
    private String norawat = "", noLIS = "", cekLIS = "", ketLIS = "", tglLIS = "", jamLIS = "", dokterBaca = "",
            drpengirim = "", tglPeriksaLIS = "", jamPeriksaLIS = "", nmpas = "", nomorrm = "", hasilDipilih = "",
            kdItem = "", tglhasil = "", jamhasil = "", nmpemeriksaan = "", kamar = "", kodeRujukan = "", isi = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgHasilPenunjangMedis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new Object[]{
            "Pasien", "No. LIS", "Keterangan Hasil Lab.", "cekok", "norawat", "Tgl. Periksa",
            "Jam Periksa", "Dokter Pengirim", "Jns. Rawat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbLIS.setModel(tabMode);
        tbLIS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLIS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 9; i++) {
            TableColumn column = tbLIS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(195);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(180);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(210);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            }
        }
        tbLIS.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Cek", "Jenis Pemeriksaan/Item", "Nilai Hasil", "Satuan", "Flag Kode", "Nilai Rujukan", "Waktu Selesai", "Metode Pemeriksaan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbHasil.setModel(tabMode1);
        tbHasil.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbHasil.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbHasil.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModePembaca = new DefaultTableModel(null, new String[]{
            "No. LIS", "Dokter Pembaca", "Tgl. Periksa", "Jam Periksa", "Tgl. Baca", "Jam Baca"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPembacaLIS.setModel(tabModePembaca);
        tbPembacaLIS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPembacaLIS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 6; i++) {
            TableColumn column = tbPembacaLIS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            }
        }
        tbPembacaLIS.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPembacaLIS.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPembacaLIS.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbPembacaLIS.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPembacaLIS.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbPembacaLIS.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        tabMode4 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. PA", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Dokter Pengirim", "Rg. Rawat/Poli/Inst.",
            "Tgl. Periksa", "Tgl. Hasil", "lokasi", "makroskopik", "mikroskopik", "kesimpulan", "anjuran",
            "kd_gambar", "nip_perujuk", "tgl_periksa", "tgl_lahir", "tgl_hasil", "waktu_simpan",
            "italic_makroskopik", "italic_mikroskopik", "italic_kesimpulan", "italic_anjuran", "nmDokterPA"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPA.setModel(tabMode4);
        tbPA.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 26; i++) {
            TableColumn column = tbPA.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(115);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
        tbPA.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPA.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        
        tabModePembaca1 = new DefaultTableModel(null, new String[]{
            "kd_jenis_prw", "Nama Pemeriksaaan Rad.", "Dokter Pembaca", "Tgl. Periksa", "Jam Periksa", "Tgl. Baca", "Jam Baca"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPembacaRad.setModel(tabModePembaca1);
        tbPembacaRad.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPembacaRad.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPembacaRad.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(220);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            }
        }
        tbPembacaRad.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPembacaRad.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPembacaRad.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbPembacaRad.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbPembacaRad.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Jns. Rawat", "Pemeriksaan Rad.", "Dokter Perujuk", "Tgl. Periksa", "Jam Periksa",
            "no_rawat", "kd_jenis_prw", "tgl_periksa", "dokter_radiologi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRadiologi.setModel(tabMode2);
        tbRadiologi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 11; i++) {
            TableColumn column = tbRadiologi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(210);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(210);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
        tbRadiologi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "Jenis Pemeriksaan/Item", "Nilai Hasil", "Satuan",}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbHasilCopy.setModel(tabMode3);
        tbHasilCopy.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbHasilCopy.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 3; i++) {
            TableColumn column = tbHasilCopy.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } 
        }
        tbHasilCopy.setDefaultRenderer(Object.class, new WarnaTable());
        
        try {
            ps1 = koneksi.prepareStatement("SELECT lhp.kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "GROUP BY lhp.kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
            
            ps2 = koneksi.prepareStatement("SELECT lhp.sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.sub_kategori_pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.sub_kategori_pemeriksaan_nama desc,lhp.pemeriksaan_no_urut");
            
            ps3 = koneksi.prepareStatement("SELECT lhp.pemeriksaan_nama, lhp.nilai_hasil, lhp.satuan, lhp.flag_kode, "
                    + "lhp.nilai_rujukan, DATE_FORMAT(lhdp.waktu_insert,'%d/%m/%Y - %H:%i:%s') wkt_selesai, lhp.metode "
                    + "FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp ON lhp.no_lab = lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.sub_kategori_pemeriksaan_nama=? and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        
        LoadHTML1.setEditable(true);
        LoadHTML1.setEditorKit(kit);
        LoadHTML1.setDocument(doc);
        LoadHTML1.setEditable(false);
        LoadHTML1.addHyperlinkListener(e -> {
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
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHapusDipilih = new javax.swing.JMenuItem();
        MnHapusSemua = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabPemeriksaan = new javax.swing.JTabbedPane();
        internalFrame20 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        panelGlass13 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        tbLIS = new widget.Table();
        Scroll20 = new widget.ScrollPane();
        tbPembacaLIS = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        jLabel7 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        BtnCari = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        panelGlass12 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbHasil = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbHasilCopy = new widget.Table();
        panelGlass8 = new widget.panelisi();
        BtnConteng = new widget.Button();
        BtnHapus = new widget.Button();
        BtnCopy = new widget.Button();
        BtnPrinLab = new widget.Button();
        BtnKeluar = new widget.Button();
        internalFrame22 = new widget.InternalFrame();
        PanelInput3 = new javax.swing.JPanel();
        FormInput1 = new widget.PanelBiasa();
        Scroll6 = new widget.ScrollPane();
        tbPA = new widget.Table();
        panelGlass17 = new widget.panelisi();
        jLabel12 = new widget.Label();
        TCari2 = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbHlm2 = new widget.ComboBox();
        BtnCari2 = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        jPanel4 = new javax.swing.JPanel();
        panelGlass15 = new widget.panelisi();
        jLabel11 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrinLab1 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        internalFrame21 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        HasilPeriksa = new widget.TextArea();
        jPanel3 = new javax.swing.JPanel();
        panelGlass10 = new widget.panelisi();
        BtnCopy1 = new widget.Button();
        jLabel10 = new widget.Label();
        cmbCetak = new widget.ComboBox();
        BtnPrinRadiologi = new widget.Button();
        BtnKeluar1 = new widget.Button();
        PanelInput2 = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        panelGlass14 = new widget.panelisi();
        Scroll3 = new widget.ScrollPane();
        tbRadiologi = new widget.Table();
        Scroll21 = new widget.ScrollPane();
        tbPembacaRad = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        jLabel9 = new widget.Label();
        cmbHlm1 = new widget.ComboBox();
        BtnCari1 = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapusDipilih.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusDipilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusDipilih.setText("Hapus Pemeriksaan Dipilih");
        MnHapusDipilih.setName("MnHapusDipilih"); // NOI18N
        MnHapusDipilih.setPreferredSize(new java.awt.Dimension(208, 26));
        MnHapusDipilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusDipilihActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusDipilih);

        MnHapusSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        MnHapusSemua.setText("Hapus Semua Pemeriksaan Dipilih");
        MnHapusSemua.setName("MnHapusSemua"); // NOI18N
        MnHapusSemua.setPreferredSize(new java.awt.Dimension(208, 26));
        MnHapusSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusSemuaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusSemua);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Hasil Pemeriksaan Penunjang Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabPemeriksaan.setBackground(new java.awt.Color(254, 255, 254));
        TabPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPemeriksaan.setName("TabPemeriksaan"); // NOI18N
        TabPemeriksaan.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPemeriksaanMouseClicked(evt);
            }
        });

        internalFrame20.setBorder(null);
        internalFrame20.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(900, 900));
        internalFrame20.setLayout(new java.awt.BorderLayout());

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 280));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass13.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Nomor Pemeriksaan Lab. :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbLIS.setAutoCreateRowSorter(true);
        tbLIS.setName("tbLIS"); // NOI18N
        tbLIS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLISMouseClicked(evt);
            }
        });
        tbLIS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbLISKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbLIS);

        panelGlass13.add(Scroll1);

        Scroll20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pembaca Hasil Pemeriksaan Lab. :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        tbPembacaLIS.setName("tbPembacaLIS"); // NOI18N
        Scroll20.setViewportView(tbPembacaLIS);

        panelGlass13.add(Scroll20);

        PanelInput.add(panelGlass13, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Limit Data :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel7);

        cmbHlm.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(cmbHlm);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
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
        panelGlass9.add(BtnCari);

        PanelInput.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        internalFrame20.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 280));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass12.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Pemeriksaan Laboratorium :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbHasil.setToolTipText("Silahkan conteng item hasil pemeriksaan yang akan di copy");
        tbHasil.setName("tbHasil"); // NOI18N
        tbHasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHasilMouseClicked(evt);
            }
        });
        Scroll.setViewportView(tbHasil);

        panelGlass12.add(Scroll);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pemeriksaan Laboratorium Dipilih/Dicopy :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbHasilCopy.setToolTipText("Silahkan klik kanan utk. menghapus pemeriksaan lab. yg. sdh. dipilih");
        tbHasilCopy.setComponentPopupMenu(jPopupMenu1);
        tbHasilCopy.setName("tbHasilCopy"); // NOI18N
        Scroll4.setViewportView(tbHasilCopy);

        panelGlass12.add(Scroll4);

        PanelInput1.add(panelGlass12, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnConteng.setForeground(new java.awt.Color(0, 0, 0));
        BtnConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnConteng.setMnemonic('G');
        BtnConteng.setText("Conteng Semua");
        BtnConteng.setToolTipText("Alt+G");
        BtnConteng.setName("BtnConteng"); // NOI18N
        BtnConteng.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnContengActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnConteng);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnHapus.setMnemonic('M');
        BtnHapus.setText("Hapus Conteng");
        BtnHapus.setToolTipText("Alt+M");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnCopy.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopy.setMnemonic('U');
        BtnCopy.setText("Copy Hasil");
        BtnCopy.setToolTipText("Alt+U");
        BtnCopy.setName("BtnCopy"); // NOI18N
        BtnCopy.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCopy);

        BtnPrinLab.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrinLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrinLab.setMnemonic('P');
        BtnPrinLab.setText("Print Hasil Lab.");
        BtnPrinLab.setToolTipText("Alt+P");
        BtnPrinLab.setName("BtnPrinLab"); // NOI18N
        BtnPrinLab.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnPrinLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrinLabActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnPrinLab);

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
        panelGlass8.add(BtnKeluar);

        PanelInput1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        internalFrame20.add(PanelInput1, java.awt.BorderLayout.CENTER);

        TabPemeriksaan.addTab("Hasil Pemeriksaan LIS (Laboratory Information System)", internalFrame20);

        internalFrame22.setBorder(null);
        internalFrame22.setName("internalFrame22"); // NOI18N
        internalFrame22.setLayout(new java.awt.BorderLayout());

        PanelInput3.setName("PanelInput3"); // NOI18N
        PanelInput3.setOpaque(false);
        PanelInput3.setPreferredSize(new java.awt.Dimension(192, 200));
        PanelInput3.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(190, 250));
        FormInput1.setLayout(new java.awt.BorderLayout());

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pemeriksaan Patologi Anatomi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbPA.setAutoCreateRowSorter(true);
        tbPA.setName("tbPA"); // NOI18N
        tbPA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPAMouseClicked(evt);
            }
        });
        tbPA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPAKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbPA);

        FormInput1.add(Scroll6, java.awt.BorderLayout.CENTER);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Key Word :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(jLabel12);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelGlass17.add(TCari2);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Limit Data :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass17.add(jLabel13);

        cmbHlm2.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm2.setName("cmbHlm2"); // NOI18N
        cmbHlm2.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass17.add(cmbHlm2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('6');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+6");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelGlass17.add(BtnCari2);

        FormInput1.add(panelGlass17, java.awt.BorderLayout.PAGE_END);

        PanelInput3.add(FormInput1, java.awt.BorderLayout.CENTER);

        internalFrame22.add(PanelInput3, java.awt.BorderLayout.PAGE_START);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Preview Hasil Pemeriksaan :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        internalFrame22.add(Scroll5, java.awt.BorderLayout.CENTER);

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(44, 57));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Cetak Dalam Bentuk :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass15.add(jLabel11);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass15.add(cmbPilihCetak);

        BtnPrinLab1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrinLab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrinLab1.setMnemonic('P');
        BtnPrinLab1.setText("Print Hasil Lab. PA");
        BtnPrinLab1.setToolTipText("Alt+P");
        BtnPrinLab1.setName("BtnPrinLab1"); // NOI18N
        BtnPrinLab1.setPreferredSize(new java.awt.Dimension(160, 30));
        BtnPrinLab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrinLab1ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnPrinLab1);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnKeluar2);

        jPanel4.add(panelGlass15, java.awt.BorderLayout.PAGE_END);

        internalFrame22.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        TabPemeriksaan.addTab("Hasil Pemeriksaan Patologi Anatomi", internalFrame22);

        internalFrame21.setBorder(null);
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setLayout(new java.awt.BorderLayout());

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Expertise Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        HasilPeriksa.setEditable(false);
        HasilPeriksa.setColumns(20);
        HasilPeriksa.setRows(5);
        HasilPeriksa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HasilPeriksa.setName("HasilPeriksa"); // NOI18N
        Scroll2.setViewportView(HasilPeriksa);

        internalFrame21.add(Scroll2, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 57));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnCopy1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopy1.setMnemonic('U');
        BtnCopy1.setText("Copy Hasil");
        BtnCopy1.setToolTipText("Alt+U");
        BtnCopy1.setName("BtnCopy1"); // NOI18N
        BtnCopy1.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopy1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnCopy1);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Expertise Rad. :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(jLabel10);

        cmbCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biasa", "Dengan QRCode" }));
        cmbCetak.setName("cmbCetak"); // NOI18N
        cmbCetak.setPreferredSize(new java.awt.Dimension(117, 23));
        panelGlass10.add(cmbCetak);

        BtnPrinRadiologi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrinRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrinRadiologi.setMnemonic('P');
        BtnPrinRadiologi.setText("Print Expertise");
        BtnPrinRadiologi.setToolTipText("Alt+P");
        BtnPrinRadiologi.setName("BtnPrinRadiologi"); // NOI18N
        BtnPrinRadiologi.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnPrinRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrinRadiologiActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnPrinRadiologi);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnKeluar1);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        internalFrame21.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput2.setName("PanelInput2"); // NOI18N
        PanelInput2.setOpaque(false);
        PanelInput2.setPreferredSize(new java.awt.Dimension(192, 280));
        PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 250));
        FormInput.setLayout(new java.awt.BorderLayout());

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass14.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pemeriksaan Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbRadiologi.setAutoCreateRowSorter(true);
        tbRadiologi.setName("tbRadiologi"); // NOI18N
        tbRadiologi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRadiologiMouseClicked(evt);
            }
        });
        tbRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRadiologiKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbRadiologi);

        panelGlass14.add(Scroll3);

        Scroll21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pembaca Hasil Pemeriksaan Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        tbPembacaRad.setName("tbPembacaRad"); // NOI18N
        Scroll21.setViewportView(tbPembacaRad);

        panelGlass14.add(Scroll21);

        FormInput.add(panelGlass14, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass11.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari1);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Limit Data :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel9);

        cmbHlm1.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm1.setName("cmbHlm1"); // NOI18N
        cmbHlm1.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass11.add(cmbHlm1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('6');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+6");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnCari1);

        FormInput.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        PanelInput2.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame21.add(PanelInput2, java.awt.BorderLayout.PAGE_START);

        TabPemeriksaan.addTab("Hasil Pemeriksaan Radiologi (Expertise)", internalFrame21);

        internalFrame1.add(TabPemeriksaan, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnPrinLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrinLabActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnKeluar.requestFocus();
        } else if (noLIS.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel");
            tbLIS.requestFocus();
        } else if (tabMode1.getRowCount() != 0) {
            if (cekLIS.equals("")) {
                JOptionPane.showMessageDialog(null, ketLIS + " pemeriksaan dengan No. Lab. " + noLIS + ".");
            } else {
                if (tbLIS.getSelectedRow() != -1) {
                    kamar = "";
                    tampilHasil(tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString());
                    
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    param.put("nmPasien", nmpas);
                    param.put("noLab", tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString());
                    param.put("norawat", norawat);
                    param.put("norm", nomorrm);
                    param.put("jkCaraByr", Sequel.cariIsi("SELECT concat(IF (p.jk = 'L','Laki-laki','Perempuan'),' / ',pj.png_jawab) FROM reg_periksa rp "
                            + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN penjab pj ON pj.kd_pj=rp.kd_pj WHERE rp.no_rawat = '" + norawat + "'"));
                    param.put("tglLhr_umur", Sequel.cariIsi("SELECT concat(DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') "
                            + "FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE rp.no_rawat = '" + norawat + "'"));
                    param.put("tglPeriksa", Valid.SetTglINDONESIA(tglPeriksaLIS) + " - " + jamLIS);
                    param.put("drPengirim", drpengirim);
                    param.put("drLAB", Sequel.cariIsi("SELECT d.nm_dokter FROM set_pjlab s INNER JOIN dokter d ON d.kd_dokter=s.kd_dokterlab"));
                    param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("SELECT DATE(waktu_insert) FROM lis_hasil_data_pasien WHERE no_lab='" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString() + "'")));
                    
                    if (tbPembacaLIS.getRowCount() == 0) {
                        param.put("judulDokterBaca", "");
                        param.put("dokterBaca", "");
                    } else {
                        param.put("judulDokterBaca", "Hasil pemeriksaan Lab. telah dibaca oleh dokter :");
                        param.put("dokterBaca", dokterBaca);
                    }
                    
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + norawat + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        param.put("labelUnit", "Rg. Perawatan");
                        param.put("nmUnit", Sequel.cariIsi("select b.nm_bangsal from bangsal b inner join kamar k on b.kd_bangsal=k.kd_bangsal where k.kd_kamar='" + kamar + "' "));
                    } else if (kamar.equals("")) {
                        param.put("labelUnit", "Poliklinik/Inst.");
                        param.put("nmUnit", Sequel.cariIsi("select pl.nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + norawat + "'"));
                    }

                    Valid.MyReport("rptHasilLIS.jasper", "report", "::[ Lembar Hasil Pemeriksaan Laboratorium (LIS) ]::", "SELECT * FROM temporary_lis", param);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel");
                    tbLIS.requestFocus();
                }
            }
        }
}//GEN-LAST:event_BtnPrinLabActionPerformed

    private void tbLISMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLISMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbLISMouseClicked

    private void tbLISKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLISKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbLISKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        Valid.tabelKosong(tabMode1);
        tampilLIS();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnPrinLab);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnContengActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium belum dipilih...!!!!");
        } else {
            tampilLIS();
            for (i = 0; i < tbHasil.getRowCount(); i++) {
                tbHasil.setValueAt(Boolean.TRUE, i, 0);
            }

            try {
                for (i = 0; i < tbHasil.getRowCount(); i++) {
                    if (tbHasil.getValueAt(i, 0).toString().equals("true")) {
                        tabMode3.addRow(new Object[]{
                            tbHasil.getValueAt(i, 1).toString(),
                            tbHasil.getValueAt(i, 2).toString(),
                            tbHasil.getValueAt(i, 3).toString()
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnContengActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium belum dipilih...!!!!");
        } else {
            tampilLIS();
            for (i = 0; i < tbHasil.getRowCount(); i++) {
                tbHasil.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium yg. dipilih utk. dicopy belum ada...!!!!");
        } else {
            try {
                for (i = 0; i < tbHasilCopy.getRowCount(); i++) {
                    if (hasilDipilih.equals("")) {
                        hasilDipilih = tbHasilCopy.getValueAt(i, 0).toString() + " "
                                + tbHasilCopy.getValueAt(i, 1).toString() + " "
                                + tbHasilCopy.getValueAt(i, 2).toString();
                    } else {
                        hasilDipilih = hasilDipilih + "\n" + tbHasilCopy.getValueAt(i, 0).toString() + " "
                                + tbHasilCopy.getValueAt(i, 1).toString() + " "
                                + tbHasilCopy.getValueAt(i, 2).toString();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

            akses.setCopyData(hasilDipilih);
            BtnKeluarActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCopyActionPerformed

    private void TabPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPemeriksaanMouseClicked
        if (TabPemeriksaan.getSelectedIndex() == 0) {
            Valid.tabelKosong(tabMode1);            
            TCari.setText("");
            tampilLIS();
        } else if (TabPemeriksaan.getSelectedIndex() == 1) {
            TCari2.setText("");
            tampilPeriksaPA();
            tampilPreviewPA("");
        } else if (TabPemeriksaan.getSelectedIndex() == 2) {
            TCari1.setText("");
            tampilItem();
        }
    }//GEN-LAST:event_TabPemeriksaanMouseClicked

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void tbRadiologiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRadiologiMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataRadiologi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRadiologiMouseClicked

    private void tbRadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRadiologiKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRadiologi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRadiologiKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar1.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        HasilPeriksa.setText("");
        tampilItem();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnPrinRadiologi);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCopy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopy1ActionPerformed
        if (HasilPeriksa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu tanggal hasil pemeriksaan radiologinya...!!!!");
            tbRadiologi.requestFocus();
        } else {
            akses.setCopyData(HasilPeriksa.getText());
            BtnKeluar1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCopy1ActionPerformed

    private void MnHapusDipilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusDipilihActionPerformed
        if (tbHasilCopy.getSelectedRow() > -1) {
            tabMode3.removeRow(tbHasilCopy.getSelectedRow());
            BtnHapusActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik pilih salah satu datanya dulu..!!!!");
            tbHasilCopy.requestFocus();
        }
    }//GEN-LAST:event_MnHapusDipilihActionPerformed

    private void MnHapusSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusSemuaActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data pemeriksaan lab. yang dipilih utk. dicopy..!!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah semua data pemeriksaan lab. yang dipilih akan dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Valid.tabelKosong(tabMode3);
                BtnHapusActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnHapusSemuaActionPerformed

    private void tbHasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHasilMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {

                if (tbHasil.getValueAt(tbHasil.getSelectedRow(), 0).toString().equals("true")) {
                    tabMode3.addRow(new Object[]{
                        tbHasil.getValueAt(tbHasil.getSelectedRow(), 1).toString(),
                        tbHasil.getValueAt(tbHasil.getSelectedRow(), 2).toString(),
                        tbHasil.getValueAt(tbHasil.getSelectedRow(), 3).toString()
                    });
                }

            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbHasilMouseClicked

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void tbPAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPAMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                if (tbPA.getSelectedRow() != -1) {
                    tampilPreviewPA(tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPAMouseClicked

    private void tbPAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPAKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    if (tbPA.getSelectedRow() != -1) {
                        tampilPreviewPA(tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString());
                    }
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPAKeyPressed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar2.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilPeriksaPA();
        tampilPreviewPA("");
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnPrinLab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrinLab1ActionPerformed
        if (tbPA.getSelectedRow() > -1) {
            String nipdokterpa = Sequel.cariIsi("select nip_dokter_pa from hasil_patologi_anatomi where waktu_simpan='" + tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString() + "'");

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));

            param.put("noPA", tbPA.getValueAt(tbPA.getSelectedRow(), 1).toString());
            param.put("norm", tbPA.getValueAt(tbPA.getSelectedRow(), 2).toString());
            param.put("nmpasien", tbPA.getValueAt(tbPA.getSelectedRow(), 3).toString());
            param.put("tgllahir", Valid.SetTglINDONESIA(tbPA.getValueAt(tbPA.getSelectedRow(), 18).toString() + ""));
            param.put("jenkel", tbPA.getValueAt(tbPA.getSelectedRow(), 4).toString());
            param.put("drPengirim", tbPA.getValueAt(tbPA.getSelectedRow(), 6).toString());
            param.put("unit", tbPA.getValueAt(tbPA.getSelectedRow(), 7).toString());
            param.put("tglperiksa", Valid.SetTglINDONESIA(tbPA.getValueAt(tbPA.getSelectedRow(), 17).toString() + ""));
            param.put("tglhasil", Valid.SetTglINDONESIA(tbPA.getValueAt(tbPA.getSelectedRow(), 19).toString() + ""));
            param.put("lokasi", tbPA.getValueAt(tbPA.getSelectedRow(), 10).toString() + "\n");
            param.put("makros", tbPA.getValueAt(tbPA.getSelectedRow(), 11).toString().replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>"));
            param.put("italicmakros", tbPA.getValueAt(tbPA.getSelectedRow(), 21).toString());
            param.put("mikros", tbPA.getValueAt(tbPA.getSelectedRow(), 12).toString().replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>"));
            param.put("italicmikros", tbPA.getValueAt(tbPA.getSelectedRow(), 22).toString());
            param.put("kesimpulan", tbPA.getValueAt(tbPA.getSelectedRow(), 13).toString().replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>"));
            param.put("italickesimpulan", tbPA.getValueAt(tbPA.getSelectedRow(), 23).toString());
            param.put("anjuran", tbPA.getValueAt(tbPA.getSelectedRow(), 14).toString().replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>"));
            param.put("italicanjuran", tbPA.getValueAt(tbPA.getSelectedRow(), 24).toString());
            param.put("sip", Sequel.cariIsi("select no_ijn_praktek from dokter where kd_dokter='" + nipdokterpa + "'"));
            param.put("nmDokterpa", Sequel.cariIsi("select nama from pegawai where nik='" + nipdokterpa + "'"));

            try {
                String gambarnya = "", ipGambarnya = "";
                try {
                    //cek atau ping ip addres
                    ipGambarnya = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambarnya);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (tbPA.getValueAt(tbPA.getSelectedRow(), 15).toString().equals("")) {
                            gambarnya = "http://192.168.0.230:7183/img-rme/gambar_tidak_ditemukan.jpg";
                        } else {
                            gambarnya = "http://192.168.0.230:7183/rme/download.php?id=" + tbPA.getValueAt(tbPA.getSelectedRow(), 15).toString();
                        }
                        //ping gagal
                    } else {
                        gambarnya = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                    gambarnya = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                }

                param.put("gambarPA", gambarnya);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                isi = "";
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='006'"),
                                "Hasil Pemeriksaan Lab. (Patologi Anatomi)", tbPA.getValueAt(tbPA.getSelectedRow(), 25).toString(),
                                Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from hasil_patologi_anatomi where "
                                        + "waktu_simpan='" + tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString() + "'"),
                                Sequel.cariIsi("select time(waktu_simpan) from hasil_patologi_anatomi where "
                                        + "waktu_simpan='" + tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString() + "'")) + "') from kalimat_tte where kode='006'");

                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Lab. Patologi Anatomi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='006'"));
                Valid.MyReport("rptPeriksaPatologiAnatomiQr.jasper", "report", "::[ Lembar Hasil Pemeriksaan Patologi Anatomi ]::", "SELECT now() tgl", param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {
                Valid.MyReport("rptPeriksaPatologiAnatomi.jasper", "report", "::[ Lembar Hasil Pemeriksaan Patologi Anatomi ]::", "SELECT now() tgl", param);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            BtnCari2ActionPerformed(null);
            tbPA.requestFocus();
        }
    }//GEN-LAST:event_BtnPrinLab1ActionPerformed

    private void BtnPrinRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrinRadiologiActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnKeluar1.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau dibaca expertisenya...!!!!");
        } else if (kdItem.equals("") || tglhasil.equals("") || jamhasil.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih salah satu dulu item pemeriksaan yang akan diprint pada tabel...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (cmbCetak.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + norawat + "' and "
                        + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, untuk hasil expertise pemeriksaan radiologi " + nmpemeriksaan + " belum tersimpan...!!!!");
                } else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select date(waktu_simpan) from hasil_radiologi where "
                            + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'")));
                    param.put("tglperiksa", Sequel.cariIsi("select date_format(tgl_periksa,'%d') from hasil_radiologi where "
                            + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_periksa) from hasil_radiologi where "
                                    + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") + " "
                            + Sequel.cariIsi("select year(tgl_periksa) from hasil_radiologi where "
                                    + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'"));

                    if (Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat='" + norawat + "'").equals("Ralan")) {
                        param.put("kamar", "Poliklinik/Inst.");
                        param.put("namakamar", Sequel.cariIsi("select nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + norawat + "'"));
                    } else {
                        kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + norawat + "' order by tgl_masuk desc limit 1");
                        param.put("kamar", "Ruang Rawat");
                        param.put("namakamar", Sequel.cariIsi("select nm_bangsal from bangsal b inner join kamar k on b.kd_bangsal=k.kd_bangsal where k.kd_kamar='" + kamar + "'"));
                    }

                    cekRujukan = Sequel.cariInteger("select count(-1) from rujuk_masuk where no_rawat='" + norawat + "'");
                    if (cekRujukan == 0) {
                        param.put("namaFaskes", "-");
                    } else {
                        if (kodeRujukan.equals("784")) {
                            param.put("namaFaskes", "-");
                        } else {
                            param.put("namaFaskes", Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
                        }
                    }

                    if (tbPembacaRad.getRowCount() == 0) {
                        param.put("judulPembaca", "");
                        param.put("dokterBaca", "");
                    } else {
                        param.put("judulPembaca", "Hasil pemeriksaan Radiologi telah dibaca oleh dokter : ");
                        param.put("dokterBaca", dokterBaca);
                    }

                    Valid.MyReport("rptPeriksaRadiologi.jasper", "report", "::[ Lembar Hasil Pemeriksaan Radiologi ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, concat(IF(p.jk='L','Laki-laki','Perempuan'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') jk_umur, p.alamat, pr.no_rawat, "
                            + "jpr.nm_perawatan, hr.diag_klinis_radiologi, d1.nm_dokter dr_pengirim, hr.jam, hr.hasil, d2.nm_dokter dr_rad FROM periksa_radiologi pr "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                            + "INNER JOIN dokter d1 on d1.kd_dokter=pr.dokter_perujuk INNER JOIN dokter d2 on d2.kd_dokter=pr.kd_dokter "
                            + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.tgl_periksa=pr.tgl_periksa and hr.jam=pr.jam and hr.kd_jenis_prw=pr.kd_jenis_prw "
                            + "WHERE hr.no_rawat='" + norawat + "' and hr.tgl_periksa='" + tglhasil + "' and hr.jam='" + jamhasil + "' and hr.kd_jenis_prw='" + kdItem + "'", param);
                }

            } else if (cmbCetak.getSelectedIndex() == 1) {
                isi = "";
                if (Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + norawat + "' and "
                        + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, untuk hasil expertise pemeriksaan radiologi " + nmpemeriksaan + " belum tersimpan...!!!!");
                } else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select date(waktu_simpan) from hasil_radiologi where "
                            + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'")));
                    param.put("tglperiksa", Sequel.cariIsi("select date_format(tgl_periksa,'%d') from hasil_radiologi where "
                            + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_periksa) from hasil_radiologi where "
                                    + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") + " "
                            + Sequel.cariIsi("select year(tgl_periksa) from hasil_radiologi where "
                                    + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'"));

                    if (Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat='" + norawat + "'").equals("Ralan")) {
                        param.put("kamar", "Poliklinik/Inst.");
                        param.put("namakamar", Sequel.cariIsi("select nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + norawat + "'"));
                    } else {
                        kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + norawat + "' order by tgl_masuk desc limit 1");
                        param.put("kamar", "Ruang Rawat");
                        param.put("namakamar", Sequel.cariIsi("select nm_bangsal from bangsal b inner join kamar k on b.kd_bangsal=k.kd_bangsal where k.kd_kamar='" + kamar + "'"));
                    }

                    cekRujukan = Sequel.cariInteger("select count(-1) from rujuk_masuk where no_rawat='" + norawat + "'");
                    if (cekRujukan == 0) {
                        param.put("namaFaskes", "-");
                    } else {
                        if (kodeRujukan.equals("784")) {
                            param.put("namaFaskes", "-");
                        } else {
                            param.put("namaFaskes", Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
                        }
                    }

                    if (tbPembacaRad.getRowCount() == 0) {
                        param.put("judulPembaca", "");
                        param.put("dokterBaca", "");
                    } else {
                        param.put("judulPembaca", "Hasil pemeriksaan Radiologi telah dibaca oleh dokter : ");
                        param.put("dokterBaca", dokterBaca);
                    }

                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='006'"),
                                    "Hasil Pemeriksaan Radiologi (Expertise)", tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 10).toString(),
                                    Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from hasil_radiologi where "
                                            + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'"),
                                    Sequel.cariIsi("select time(waktu_simpan) from hasil_radiologi where "
                                            + "no_rawat='" + norawat + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'")) + "') from kalimat_tte where kode='006'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Radiologi", Sequel.cariFolderPrintTte());
                    param.put("lokasi", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='006'"));

                    Valid.MyReport("rptPeriksaRadiologiQr.jasper", "report", "::[ Lembar Hasil Pemeriksaan Radiologi ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, concat(IF(p.jk='L','Laki-laki','Perempuan'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') jk_umur, p.alamat, pr.no_rawat, "
                            + "jpr.nm_perawatan, hr.diag_klinis_radiologi, d1.nm_dokter dr_pengirim, hr.jam, hr.hasil, d2.nm_dokter dr_rad FROM periksa_radiologi pr "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                            + "INNER JOIN dokter d1 on d1.kd_dokter=pr.dokter_perujuk INNER JOIN dokter d2 on d2.kd_dokter=pr.kd_dokter "
                            + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.tgl_periksa=pr.tgl_periksa and hr.jam=pr.jam and hr.kd_jenis_prw=pr.kd_jenis_prw "
                            + "WHERE hr.no_rawat='" + norawat + "' and hr.tgl_periksa='" + tglhasil + "' and hr.jam='" + jamhasil + "' and hr.kd_jenis_prw='" + kdItem + "'", param);
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrinRadiologiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgHasilPenunjangMedis dialog = new DlgHasilPenunjangMedis(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnConteng;
    private widget.Button BtnCopy;
    private widget.Button BtnCopy1;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnPrinLab;
    private widget.Button BtnPrinLab1;
    private widget.Button BtnPrinRadiologi;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.TextArea HasilPeriksa;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnHapusDipilih;
    private javax.swing.JMenuItem MnHapusSemua;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput2;
    private javax.swing.JPanel PanelInput3;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll20;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private javax.swing.JTabbedPane TabPemeriksaan;
    private widget.ComboBox cmbCetak;
    private widget.ComboBox cmbHlm;
    private widget.ComboBox cmbHlm1;
    private widget.ComboBox cmbHlm2;
    private widget.ComboBox cmbPilihCetak;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.InternalFrame internalFrame22;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbHasil;
    private widget.Table tbHasilCopy;
    private widget.Table tbLIS;
    private widget.Table tbPA;
    private widget.Table tbPembacaLIS;
    private widget.Table tbPembacaRad;
    private widget.Table tbRadiologi;
    // End of variables declaration//GEN-END:variables
 
    private void tampilLIS() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT CONCAT(p.no_rkm_medis,' - ',p.nm_pasien) pasien, lr.no_lab nolis, "
                    + "IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil Lab. bisa dicetak') hasil_lab, "
                    + "IFNULL(lh.no_lab,'') cekOK, pl.no_rawat, IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) tgl, "
                    + "IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) jam, "
                    + "IFNULL(lh.dokter_pengirim,'') dokter_pengirim, if(rp.status_lanjut='Ralan','R. Jalan','R. Inap') stts_lnjut FROM lis_reg lr "
                    + "LEFT JOIN reg_periksa rp ON rp.no_rawat=lr.no_rawat LEFT JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "LEFT JOIN periksa_lab pl ON pl.no_rawat=lr.no_rawat LEFT JOIN lis_hasil_data_pasien lh ON lh.no_lab=lr.no_lab WHERE "
                    + "rp.no_rkm_medis like ? and lr.no_lab like ? or "
                    + "rp.no_rkm_medis like ? and IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil Lab. bisa dicetak') like ? or "
                    + "rp.no_rkm_medis like ? and pl.no_rawat like ? or "
                    + "rp.no_rkm_medis like ? and IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) like ? or "
                    + "rp.no_rkm_medis like ? and IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) like ? or "
                    + "rp.no_rkm_medis like ? and lh.dokter_pengirim like ? or "
                    + "rp.no_rkm_medis like ? and if(rp.status_lanjut='Ralan','R. Jalan','R. Inap') like ? "
                    + "GROUP BY lr.no_lab ORDER BY rp.no_rawat desc, tgl DESC, jam DESC, hasil_lab limit " + cmbHlm.getSelectedItem().toString() + "");
            try {
                ps.setString(1, "%" + nomorrm + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + nomorrm + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + nomorrm + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, "%" + nomorrm + "%");
                ps.setString(8, "%" + TCari.getText().trim() + "%");
                ps.setString(9, "%" + nomorrm + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");                
                ps.setString(11, "%" + nomorrm + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, "%" + nomorrm + "%");
                ps.setString(14, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("pasien"),
                        rs.getString("nolis"),
                        rs.getString("hasil_lab"),
                        rs.getString("cekOK"),
                        rs.getString("no_rawat"),
                        rs.getString("tgl"),
                        rs.getString("jam"),
                        rs.getString("dokter_pengirim"),
                        rs.getString("stts_lnjut")
                    });
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
    
    public void setData(String norw, String nmpasien, String norm) {
        TabPemeriksaan.setSelectedIndex(0);
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode3);
        norawat = norw;
        nmpas = nmpasien;
        nomorrm = norm;
        TCari.setText("");
        tampilLIS();
    }

    private void getData() {
        Valid.tabelKosong(tabMode1);
        noLIS = "";
        cekLIS = "";
        ketLIS = "";
        tglLIS = "";
        jamLIS = "";
        drpengirim = "";
        tglPeriksaLIS = "";
        jamPeriksaLIS = "";

        if (tbLIS.getSelectedRow() != -1) {
            noLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString();
            ketLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 2).toString();
            cekLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 3).toString();
            tglLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString();
            jamLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 6).toString();
            drpengirim = tbLIS.getValueAt(tbLIS.getSelectedRow(), 7).toString();
            tglPeriksaLIS = Sequel.cariIsi("SELECT DATE(waktu_reg_lab) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'");
            jamPeriksaLIS = Sequel.cariIsi("SELECT TIME(waktu_reg_lab) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'");
            tampilHasil(noLIS);
            tampilPembaca(tbLIS.getValueAt(tbLIS.getSelectedRow(), 4).toString(), noLIS, 
                    Valid.SetTgl(tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString() + ""), jamLIS);
        }
    }
    
    private void tampilHasil(String nolisDipilih) {
        try {
            Sequel.queryu("delete from temporary_lis");
            Valid.tabelKosong(tabMode1);
            ps1.setString(1, nolisDipilih);
            rs1 = ps1.executeQuery();
            while (rs1.next()) {    
                Sequel.menyimpan("temporary_lis", "'" + rs1.getString("kategori_pemeriksaan_nama") + "','','','',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Kategori Pemeriksaan");
                tabMode1.addRow(new Object[]{false, rs1.getString("kategori_pemeriksaan_nama"), "", "", "", "", "", ""});

                ps2.setString(1, nolisDipilih);
                ps2.setString(2, rs1.getString("kategori_pemeriksaan_nama"));
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    Sequel.menyimpan("temporary_lis", "'   " + rs2.getString("sub_kategori_pemeriksaan_nama") + "','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Sub Kategori Pemeriksaan");
                    tabMode1.addRow(new Object[]{false, "   "+rs2.getString("sub_kategori_pemeriksaan_nama"), "", "", "", "", "", ""});
                    
                    ps3.setString(1, nolisDipilih);
                    ps3.setString(2, rs2.getString("sub_kategori_pemeriksaan_nama"));
                    ps3.setString(3, rs1.getString("kategori_pemeriksaan_nama"));
                    
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        Sequel.menyimpan("temporary_lis", "'     " + Valid.mysql_real_escape_string(rs3.getString("pemeriksaan_nama")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rs3.getString("nilai_hasil")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rs3.getString("satuan")) + "',"
                                + "'" + rs3.getString("flag_kode") + "',"
                                + "'" + Valid.mysql_real_escape_string(rs3.getString("nilai_rujukan")) + "',"
                                + "'" + rs3.getString("wkt_selesai") + "',"
                                + "'" + Valid.mysql_real_escape_string(rs3.getString("metode")) + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Hasil Pemeriksaan");
                        tabMode1.addRow(new Object[]{
                            false,
                            "     " + rs3.getString("pemeriksaan_nama"),
                            rs3.getString("nilai_hasil"),
                            rs3.getString("satuan"),
                            rs3.getString("flag_kode"),
                            rs3.getString("nilai_rujukan"),
                            rs3.getString("wkt_selesai"),
                            rs3.getString("metode")
                        });
                        
                        if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and status='1' "
                                + "and (nm_dokter like '%dr.%' or nm_dokter like '%drg.%')") > 0) {
                            Sequel.menyimpanIgnore("pembaca_hasil_lab",
                                    "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 4).toString() + "',"
                                    + "'" + akses.getkode() + "',"
                                    + "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString() + "',"
                                    + "'" + Valid.SetTgl(tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString() + "") + "',"
                                    + "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 6).toString() + "',"
                                    + "'" + Sequel.cariIsi("select now()") + "'", "Pembaca Hasil Lab.");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void tampilPeriksaPA() {
        Valid.tabelKosong(tabMode4);
        try {
            ps7 = koneksi.prepareStatement("SELECT hpa.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "p2.nama drPengirim, date_format(hpa.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(hpa.tgl_hasil,'%d-%m-%Y') tglHasil, p3.nama nmDokterPA, "
                    + "p.tgl_lahir FROM hasil_patologi_anatomi hpa inner join reg_periksa rp on rp.no_rawat=hpa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai p2 on p2.nik=hpa.nip_perujuk inner join pegawai p3 on p3.nik=hpa.nip_dokter_pa where "
                    + "rp.no_rkm_medis like ? and hpa.no_rawat like ? or "
                    + "rp.no_rkm_medis like ? and hpa.no_pa like ? or "
                    + "rp.no_rkm_medis like ? and hpa.lokasi_organ like ? or "
                    + "rp.no_rkm_medis like ? and hpa.makroskopik like ? or "
                    + "rp.no_rkm_medis like ? and hpa.mikroskopik like ? or "
                    + "rp.no_rkm_medis like ? and hpa.kesimpulan like ? or "
                    + "rp.no_rkm_medis like ? and hpa.anjuran like ? or "
                    + "rp.no_rkm_medis like ? and p2.nama like ? or "
                    + "rp.no_rkm_medis like ? and hpa.nm_unit like ? order by hpa.waktu_simpan desc limit " + cmbHlm2.getSelectedItem().toString() + "");
            try {
                ps7.setString(1, "%" + nomorrm + "%");
                ps7.setString(2, "%" + TCari2.getText().trim() + "%");
                ps7.setString(3, "%" + nomorrm + "%");
                ps7.setString(4, "%" + TCari2.getText().trim() + "%");
                ps7.setString(5, "%" + nomorrm + "%");
                ps7.setString(6, "%" + TCari2.getText().trim() + "%");
                ps7.setString(7, "%" + nomorrm + "%");
                ps7.setString(8, "%" + TCari2.getText().trim() + "%");
                ps7.setString(9, "%" + nomorrm + "%");
                ps7.setString(10, "%" + TCari2.getText().trim() + "%");
                ps7.setString(11, "%" + nomorrm + "%");
                ps7.setString(12, "%" + TCari2.getText().trim() + "%");
                ps7.setString(13, "%" + nomorrm + "%");
                ps7.setString(14, "%" + TCari2.getText().trim() + "%");
                ps7.setString(15, "%" + nomorrm + "%");
                ps7.setString(16, "%" + TCari2.getText().trim() + "%");
                ps7.setString(17, "%" + nomorrm + "%");
                ps7.setString(18, "%" + TCari2.getText().trim() + "%");
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    tabMode4.addRow(new String[]{
                        rs7.getString("no_rawat"),
                        rs7.getString("no_pa"),
                        rs7.getString("no_rkm_medis"),
                        rs7.getString("nm_pasien"),
                        rs7.getString("jenkel"),
                        rs7.getString("tglLahir"),                        
                        rs7.getString("drPengirim"),                        
                        rs7.getString("nm_unit"),                        
                        rs7.getString("tglPeriksa"),                        
                        rs7.getString("tglHasil"),                        
                        rs7.getString("lokasi_organ"),
                        rs7.getString("makroskopik"),
                        rs7.getString("mikroskopik"),
                        rs7.getString("kesimpulan"),
                        rs7.getString("anjuran"),
                        rs7.getString("kd_gambar"),
                        rs7.getString("nip_perujuk"),
                        rs7.getString("tgl_periksa"),
                        rs7.getString("tgl_lahir"),
                        rs7.getString("tgl_hasil"),
                        rs7.getString("waktu_simpan"),                        
                        rs7.getString("italic_makroskopik"),
                        rs7.getString("italic_mikroskopik"),
                        rs7.getString("italic_kesimpulan"),
                        rs7.getString("italic_anjuran"),
                        rs7.getString("nmDokterPA")
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilItem() {
    Valid.tabelKosong(tabMode2);
        try {
            ps4 = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, if(rp.status_lanjut='ralan','R. Jalan','R. Inap') jns_rwt, j.nm_perawatan, "
                    + "date_format(pr.tgl_periksa,'%d-%m-%Y') tglnya, pr.jam, pr.no_rawat, pr.kd_jenis_prw, pr.tgl_periksa, pg1.nama dr_perujuk, pg2.nama dr_radiologi FROM periksa_radiologi pr "
                    + "inner join jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw inner join reg_periksa rp on rp.no_rawat=pr.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=pr.dokter_perujuk "
                    + "inner join pegawai pg2 on pg2.nik=pr.kd_dokter where "
                    + "rp.no_rkm_medis like ? and if(rp.status_lanjut='ralan','R. Jalan','R. Inap') like ? or "
                    + "rp.no_rkm_medis like ? and j.nm_perawatan like ? or "
                    + "rp.no_rkm_medis like ? and pg1.nama like ? or "
                    + "rp.no_rkm_medis like ? and date_format(pr.tgl_periksa,'%d-%m-%Y') like ? or "
                    + "rp.no_rkm_medis like ? and pr.no_rawat like ? "
                    + "ORDER BY rp.no_rawat desc, pr.tgl_periksa desc, pr.jam desc limit " + cmbHlm1.getSelectedItem().toString() + "");

            try {
                ps4.setString(1, "%" + nomorrm + "%");
                ps4.setString(2, "%" + TCari1.getText().trim() + "%");
                ps4.setString(3, "%" + nomorrm + "%");
                ps4.setString(4, "%" + TCari1.getText().trim() + "%");
                ps4.setString(5, "%" + nomorrm + "%");
                ps4.setString(6, "%" + TCari1.getText().trim() + "%");
                ps4.setString(7, "%" + nomorrm + "%");
                ps4.setString(8, "%" + TCari1.getText().trim() + "%");
                ps4.setString(9, "%" + nomorrm + "%");
                ps4.setString(10, "%" + TCari1.getText().trim() + "%");
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode2.addRow(new Object[]{                        
                        rs4.getString("no_rkm_medis"),
                        rs4.getString("nm_pasien"),
                        rs4.getString("jns_rwt"),                        
                        rs4.getString("nm_perawatan"),
                        rs4.getString("dr_perujuk"),
                        rs4.getString("tglnya"),
                        rs4.getString("jam"),
                        rs4.getString("no_rawat"),
                        rs4.getString("kd_jenis_prw"),
                        rs4.getString("tgl_periksa"),
                        rs4.getString("dr_radiologi")
                    });
                }                
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataRadiologi() {
        kdItem = "";
        norawat = "";
        tglhasil = "";
        jamhasil = "";
        nmpemeriksaan = "";

        if (tbRadiologi.getSelectedRow() != -1) {
            nmpemeriksaan = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 3).toString();
            jamhasil = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 6).toString();
            norawat = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 7).toString();
            kdItem = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 8).toString();
            tglhasil = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 9).toString();            
            deskripsiHasil();
            tampilPembacaRad(norawat, kdItem, tglhasil, jamhasil);
        }
    }
    
    private void deskripsiHasil() {
        if (Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + norawat + "' and "
                + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") == 0) {
            HasilPeriksa.setText("Hasil expertise radiologi belum dikirim ke SIMRS..!!");
        } else {
            Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Expertise Radiologi [ " + nmpemeriksaan + " ] :.",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
            
            HasilPeriksa.setText(Sequel.cariIsi("select hasil from hasil_radiologi where "
                    + "no_rawat like '%" + norawat + "%' and "
                    + "tgl_periksa like '%" + tglhasil + "%' and "
                    + "jam like '%" + jamhasil + "%' and "
                    + "kd_jenis_prw like '%" + kdItem + "%'"));
            
            if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and status='1' "
                                + "and (nm_dokter like '%dr.%' or nm_dokter like '%drg.%')") > 0) {
                Sequel.menyimpanIgnore("pembaca_hasil_radiologi",
                        "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 7).toString() + "',"
                        + "'" + akses.getkode() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 8).toString() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 9).toString() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 6).toString() + "',"
                        + "'" + Sequel.cariIsi("select now()") + "'", "Pembaca hasil radiologi");
            }
        }
    }
    
    private void tampilPembacaRad(String norw, String kdPemriksaan, String tgl, String jam) {
        dokterBaca = "";
        Valid.tabelKosong(tabModePembaca1);
        try {
            ps6 = koneksi.prepareStatement("SELECT ph.*, p.nama, date_format(ph.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(ph.waktu_simpan,'%d-%m-%Y') tglBaca, "
                    + "time_format(ph.waktu_simpan,'%H:%i:%s') jamBaca, jp.nm_perawatan FROM pembaca_hasil_radiologi ph inner join pegawai p on p.nik=ph.kd_dokter "
                    + "inner join jns_perawatan_radiologi jp on jp.kd_jenis_prw=ph.kd_jenis_prw where "
                    + "ph.no_rawat='" + norw + "' and ph.kd_jenis_prw='" + kdPemriksaan + "' and ph.tgl_periksa='" + tgl + "' "
                    + "and ph.jam_periksa='" + jam + "' order by ph.waktu_simpan");
            try {
                rs6 = ps6.executeQuery();
                x = 0;
                while (rs6.next()) {
                    tabModePembaca1.addRow(new String[]{
                        rs6.getString("kd_jenis_prw"),
                        rs6.getString("nm_perawatan"),
                        rs6.getString("nama"),
                        rs6.getString("tglPeriksa"),
                        rs6.getString("jam_periksa"),
                        rs6.getString("tglBaca"),
                        rs6.getString("jamBaca")
                    });
                    x++;
                    if (dokterBaca.equals("")) {
                        dokterBaca = x + ". " + rs6.getString("nama") + " (Tgl. " + rs6.getString("tglBaca") + ", Jam " + rs6.getString("jamBaca").substring(0, 5) + " Wita)";
                    } else {
                        dokterBaca = dokterBaca + "\n" + x + ". " + rs6.getString("nama") + " (Tgl. " + rs6.getString("tglBaca") + ", Jam " + rs6.getString("jamBaca").substring(0, 5) + " Wita)";
                    }
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
    }
    
    private void tampilPembaca(String norw, String nolab, String tgl, String jam) {
        dokterBaca = "";
        Valid.tabelKosong(tabModePembaca);
        try {
            ps5 = koneksi.prepareStatement("SELECT ph.*, p.nama, date_format(ph.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(ph.waktu_simpan,'%d-%m-%Y') tglBaca, "
                    + "time_format(ph.waktu_simpan,'%H:%i:%s') jamBaca FROM pembaca_hasil_lab ph inner join pegawai p on p.nik=ph.kd_dokter where "
                    + "ph.no_rawat='" + norw + "' and ph.no_lab='" + nolab + "' and ph.tgl_periksa='" + tgl + "' "
                    + "and ph.jam_periksa='" + jam + "' order by ph.waktu_simpan");
            try {
                rs5 = ps5.executeQuery();
                x = 0;
                while (rs5.next()) {
                    tabModePembaca.addRow(new String[]{
                        rs5.getString("no_lab"),
                        rs5.getString("nama"),
                        rs5.getString("tglPeriksa"),
                        rs5.getString("jam_periksa"),
                        rs5.getString("tglBaca"),
                        rs5.getString("jamBaca")
                    });
                    x++;
                    if (dokterBaca.equals("")) {
                        dokterBaca = x + ". " + rs5.getString("nama") + " (Tgl. " + rs5.getString("tglBaca") + ", Jam " + rs5.getString("jamBaca").substring(0, 5) + " Wita)";
                    } else {
                        dokterBaca = dokterBaca + "\n" + x + ". " + rs5.getString("nama") + " (Tgl. " + rs5.getString("tglBaca") + ", Jam " + rs5.getString("jamBaca").substring(0, 5) + " Wita)";
                    }
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
    
    private void tampilPreviewPA(String wktsimpan) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsPrev = koneksi.prepareStatement("SELECT hpa.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                        + "p2.nama drPengirim, date_format(hpa.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(hpa.tgl_hasil,'%d-%m-%Y') tglHasil, p.tgl_lahir "
                        + "FROM hasil_patologi_anatomi hpa inner join reg_periksa rp on rp.no_rawat=hpa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai p2 on p2.nik=hpa.nip_perujuk where "
                        + "hpa.waktu_simpan='" + wktsimpan + "' order by hpa.waktu_simpan desc").executeQuery();
                if (rsPrev.next()) {
                    rsPrev.beforeFirst();
                    while (rsPrev.next()) {
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' align='center' colspan='8' bgcolor='#f8fdf3'><b>HASIL PEMERIKSAAN PATOLOGI ANATOMI</b></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Lokasi / Organ</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("lokasi_organ")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;") + "<br></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Makroskopik</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("makroskopik")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;")
                                + " <i>" + rsPrev.getString("italic_makroskopik") + "</i><br></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Mikroskopik</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("mikroskopik")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;")
                                + " <i>" + rsPrev.getString("italic_mikroskopik") + "</i><br></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Kesimpulan</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("kesimpulan")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;")
                                + " <i>" + rsPrev.getString("italic_kesimpulan") + "</i><br></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Anjuran</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("anjuran")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;")
                                + " <i>" + rsPrev.getString("italic_anjuran") + "</i><br></td>"
                                + "</tr>");
                        
                        String gambar = "", ipGambar = "";
                        isi = "";
                        try {
                            //cek atau ping ip addres
                            ipGambar = "192.168.0.230";
                            InetAddress inet = InetAddress.getByName(ipGambar);

                            //ping sukses timeout 100 ms (0.1 detik)
                            if (inet.isReachable(100)) {
                                if (rsPrev.getString("kd_gambar").equals("")) {
                                    gambar = "http://192.168.0.230:7183/img-rme/gambar_tidak_ditemukan.jpg";
                                } else {
                                    gambar = "http://192.168.0.230:7183/rme/download.php?id=" + rsPrev.getString("kd_gambar");
                                }
                            //ping gagal
                            } else {
                                gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                            gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                        }
                        
                        isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='006'"),
                                        "Hasil Pemeriksaan Lab. (Patologi Anatomi)", tbPA.getValueAt(tbPA.getSelectedRow(), 25).toString(),
                                        Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from hasil_patologi_anatomi where "
                                                + "waktu_simpan='" + tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString() + "'"),
                                        Sequel.cariIsi("select time(waktu_simpan) from hasil_patologi_anatomi where "
                                                + "waktu_simpan='" + tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString() + "'")) + "') from kalimat_tte where kode='006'");
                        Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");                        

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='middle' colspan='5' rowspan='5' align='center'><br><img src='" + gambar + "' width='500' alt='Patologi Anatomi'></td>"
                                + "<td valign='top' colspan='3' align='center'><br><br><br>Pemeriksa,<br><img src='file:///" + Sequel.cariFolderTte() + File.separator + "QRTte.jpg" + "' width='150' alt='TTE Dokter Patologi Anatomi'><br>"
                                + Sequel.cariIsi("select nama from pegawai where nik='" + rsPrev.getString("nip_dokter_pa") + "'") + "</b><br>SIP : "
                                + Sequel.cariIsi("select no_ijn_praktek from dokter where kd_dokter='" + rsPrev.getString("nip_dokter_pa") + "'") + "</td>"
                                + "</tr>");
                    }
                    htmlContent.append(
                            "</tbody>"
                            + "</table>");
                    
                    htmlContent.append("<tr><td><br></td></tr>");
                }

                LoadHTML1.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' colspan='8' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPrev != null) {
                    rsPrev.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
}
