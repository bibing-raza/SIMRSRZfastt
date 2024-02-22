package laporan;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgHasilPenunjangMedis extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4;
    private ResultSet rs, rs1, rs2, rs3, rs4;
    private int i = 0, x = 0, cekRujukan = 0;
    private String norawat = "", noLIS = "", cekLIS = "", ketLIS = "", tglLIS = "", jamLIS = "",
            drpengirim = "", tglPeriksaLIS = "", jamPeriksaLIS = "", nmpas = "", nomorrm = "", hasilDipilih = "",
            kdItem = "", tglhasil = "", jamhasil = "", nmpemeriksaan = "", kamar = "", kodeRujukan = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgHasilPenunjangMedis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

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
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Jns. Rawat", "Pemeriksaan Rad.", "Dokter Perujuk", "Tgl. Periksa", "Jam Periksa",
            "no_rawat", "kd_jenis_prw", "tgl_periksa"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRadiologi.setModel(tabMode2);
        tbRadiologi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 10; i++) {
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
                    + "lhp.nilai_rujukan, DATE_FORMAT(lhdp.waktu_insert,'%d/%m/%Y - %h:%i') wkt_selesai, lhp.metode "
                    + "FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp ON lhp.no_lab = lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.sub_kategori_pemeriksaan_nama=? and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
        } catch (Exception e) {
            System.out.println(e);
        }
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
        Scroll1 = new widget.ScrollPane();
        tbLIS = new widget.Table();
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
        internalFrame21 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        HasilPeriksa = new widget.TextArea();
        jPanel3 = new javax.swing.JPanel();
        panelGlass10 = new widget.panelisi();
        BtnCopy1 = new widget.Button();
        BtnPrinRadiologi = new widget.Button();
        BtnKeluar1 = new widget.Button();
        PanelInput2 = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        Scroll3 = new widget.ScrollPane();
        tbRadiologi = new widget.Table();
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

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Nomor Pemeriksaan Lab. :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbLIS.setAutoCreateRowSorter(true);
        tbLIS.setToolTipText("");
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

        PanelInput.add(Scroll1, java.awt.BorderLayout.CENTER);

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

        BtnPrinRadiologi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrinRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrinRadiologi.setMnemonic('P');
        BtnPrinRadiologi.setText("Print Expertise Rad.");
        BtnPrinRadiologi.setToolTipText("Alt+P");
        BtnPrinRadiologi.setName("BtnPrinRadiologi"); // NOI18N
        BtnPrinRadiologi.setPreferredSize(new java.awt.Dimension(180, 30));
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

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pemeriksaan Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbRadiologi.setAutoCreateRowSorter(true);
        tbRadiologi.setToolTipText("");
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

        FormInput.add(Scroll3, java.awt.BorderLayout.CENTER);

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
                param.put("noLab", noLIS);
                param.put("norawat", norawat);
                param.put("norm", nomorrm);
                param.put("jkCaraByr", Sequel.cariIsi("SELECT concat(IF (p.jk = 'L','Laki-laki','Perempuan'),' / ',pj.png_jawab) FROM reg_periksa rp "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN penjab pj ON pj.kd_pj=rp.kd_pj WHERE rp.no_rawat = '" + norawat + "'"));
                param.put("tglLhr_umur", Sequel.cariIsi("SELECT concat(DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE rp.no_rawat = '" + norawat + "'"));
                param.put("tglPeriksa", Valid.SetTglINDONESIA(tglPeriksaLIS) + " - " + jamLIS);
                param.put("drPengirim", drpengirim);
                param.put("drLAB", Sequel.cariIsi("SELECT d.nm_dokter FROM set_pjlab s INNER JOIN dokter d ON d.kd_dokter=s.kd_dokterlab"));
                param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("SELECT DATE(waktu_insert) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'")));
                param.put("labelUnit", "Poliklinik/Inst.");
                param.put("nmUnit", Sequel.cariIsi("select pl.nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + norawat + "'"));

                Valid.MyReport("rptHasilLIS.jasper", "report", "::[ Lembar Hasil Pemeriksaan Laboratorium (LIS) ]::", "SELECT * FROM temporary_lis", param);
                this.setCursor(Cursor.getDefaultCursor());
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
            TCari1.setText("");            
            tampilItem();
        }
    }//GEN-LAST:event_TabPemeriksaanMouseClicked

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
                param.put("tglSurat", "Martapura, " + Sequel.cariIsi("select date_format(now(),'%d')") + " " + Sequel.bulanINDONESIA("select MONTH(now())") + " " + Sequel.cariIsi("select year(now())"));
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

                Valid.MyReport("rptPeriksaRadiologi.jasper", "report", "::[ Lembar Hasil Pemeriksaan Radiologi ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, concat(IF(p.jk='L','Laki-laki','Perempuan'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') jk_umur, p.alamat, pr.no_rawat, "
                        + "jpr.nm_perawatan, hr.diag_klinis_radiologi, d1.nm_dokter dr_pengirim, hr.jam, hr.hasil, d2.nm_dokter dr_rad FROM periksa_radiologi pr "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                        + "INNER JOIN dokter d1 on d1.kd_dokter=pr.dokter_perujuk INNER JOIN dokter d2 on d2.kd_dokter=pr.kd_dokter "
                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.tgl_periksa=pr.tgl_periksa and hr.jam=pr.jam and hr.kd_jenis_prw=pr.kd_jenis_prw "
                        + "WHERE hr.no_rawat='" + norawat + "' and hr.tgl_periksa='" + tglhasil + "' and hr.jam='" + jamhasil + "' and hr.kd_jenis_prw='" + kdItem + "'", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrinRadiologiActionPerformed

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
    private widget.Button BtnConteng;
    private widget.Button BtnCopy;
    private widget.Button BtnCopy1;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPrinLab;
    private widget.Button BtnPrinRadiologi;
    private widget.PanelBiasa FormInput;
    private widget.TextArea HasilPeriksa;
    private javax.swing.JMenuItem MnHapusDipilih;
    private javax.swing.JMenuItem MnHapusSemua;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput2;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private javax.swing.JTabbedPane TabPemeriksaan;
    private widget.ComboBox cmbHlm;
    private widget.ComboBox cmbHlm1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbHasil;
    private widget.Table tbHasilCopy;
    private widget.Table tbLIS;
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
            tampilHasil();
        }
    }
    
    private void tampilHasil() {
        try {
            Sequel.queryu("delete from temporary_lis");
            Valid.tabelKosong(tabMode1);
            ps1.setString(1, noLIS);
            rs1 = ps1.executeQuery();
            while (rs1.next()) {    
                Sequel.menyimpan("temporary_lis", "'" + rs1.getString("kategori_pemeriksaan_nama") + "','','','',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Kategori Pemeriksaan");
                tabMode1.addRow(new Object[]{false, rs1.getString("kategori_pemeriksaan_nama"), "", "", "", "", "", ""});

                ps2.setString(1, noLIS);
                ps2.setString(2, rs1.getString("kategori_pemeriksaan_nama"));
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    Sequel.menyimpan("temporary_lis", "'   " + rs2.getString("sub_kategori_pemeriksaan_nama") + "','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Sub Kategori Pemeriksaan");
                    tabMode1.addRow(new Object[]{false, "   "+rs2.getString("sub_kategori_pemeriksaan_nama"), "", "", "", "", "", ""});
                    
                    ps3.setString(1, noLIS);
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
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void tampilItem() {
    Valid.tabelKosong(tabMode2);
        try {
            ps4 = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, if(rp.status_lanjut='ralan','R. Jalan','R. Inap') jns_rwt, j.nm_perawatan, "
                    + "date_format(pr.tgl_periksa,'%d-%m-%Y') tglnya, pr.jam, pr.no_rawat, pr.kd_jenis_prw, pr.tgl_periksa, pg.nama dr_perujuk FROM periksa_radiologi pr "
                    + "inner join jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw inner join reg_periksa rp on rp.no_rawat=pr.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=pr.dokter_perujuk where "
                    + "rp.no_rkm_medis like ? and if(rp.status_lanjut='ralan','R. Jalan','R. Inap') like ? or "
                    + "rp.no_rkm_medis like ? and j.nm_perawatan like ? or "
                    + "rp.no_rkm_medis like ? and pg.nama like ? or "
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
                        rs4.getString("tgl_periksa")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
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
        }
    }
    
    private void deskripsiHasil() {
        if (Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + norawat + "' and "
                + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") == 0) {
            HasilPeriksa.setText("Hasil expertise radiologi belum dikirim ke SIMRS..!!");
        } else {
            HasilPeriksa.setText(Sequel.cariIsi("select hasil from hasil_radiologi where "
                    + "no_rawat like '%" + norawat + "%' and "
                    + "tgl_periksa like '%" + tglhasil + "%' and "
                    + "jam like '%" + jamhasil + "%' and "
                    + "kd_jenis_prw like '%" + kdItem + "%'"));
        }
    }
}
