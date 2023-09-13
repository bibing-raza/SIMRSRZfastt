package bridging;
import com.fasterxml.jackson.databind.JsonNode;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class INACBGDaftarKlaim extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps4, ps5, ps6;
    private ResultSet rs, rs1, rs2, rs4, rs5, rs6;
    private Date tgl = new Date();
    private String cekSEP = "", jnsKlaim = "", nik = "", noka = "", tglKunj = "", jnsrwt = "", norm = "", dialog_simpan = "",
            nmpas = "", tgllahir = "", jk = "", tglSep = "", nilaiRWT = "", kd_payor = "", nmibu = "", cekData = "", jnsRawat = "";
    private JsonNode root;
    private int cekKlaim = 0, x = 0, i = 0;
    private ApiEKLAIM_inacbg mbak_eka = new ApiEKLAIM_inacbg();
    private PengajuanKlaimINACBGrz ajukan = new PengajuanKlaimINACBGrz(null, false);

    /**
     * Creates new form DlgSpesialis
     *
     * @param parent
     * @param modal
     */
    public INACBGDaftarKlaim(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 10);
        setSize(459, 539);

        Object[] row = {"No. Rawat", "Jns. Jaminan", "No. SEP/No.Klaim", "No. RM", "Nama Pasien", "Jns. Rawat", "Nama Unit",
            "Hak Kelas", "Naik Kelas", "Kode INACBG", "Tarif Grouping", "Sub. Tarif Grouping", "Kronik Tarif", "TopUp Tarif",
            "Tot. Tarif Grouping", "Tarif Naik Kls.", "TopUp Covid-19", "Tarif Real Cost", "Status Final", "Petugas Coder", "Tgl. Input",
            "Kirim Online", "tglSEP", "kodepayor"};
        tabMode = new DefaultTableModel(null, row) {
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
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbINACBG.setModel(tabMode);
        tbINACBG.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbINACBG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 24; i++) {
            TableColumn column = tbINACBG.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(125);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(85);
            } else if (i == 17) {
                column.setPreferredWidth(85);
            } else if (i == 18) {
                column.setPreferredWidth(80);
            } else if (i == 19) {
                column.setPreferredWidth(150);
            } else if (i == 20) {
                column.setPreferredWidth(80);
            } else if (i == 21) {
                column.setPreferredWidth(160);
            } else if (i == 22) {
//                column.setPreferredWidth(160);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
//                column.setPreferredWidth(80);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbINACBG.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. Rawat", "No. Kartu", "No. SEP", "Tgl. SEP", "No. RM",
            "Nama Pasien", "J.K.", "Jns. Rawat", "tgl_lhr", "jknya", "tglsep"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbSEP.setModel(tabMode1);
        tbSEP.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbSEP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 11; i++) {
            TableColumn column = tbSEP.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(125);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(30);
            } else if (i == 7) {
                column.setPreferredWidth(75);
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
        tbSEP.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{"Jns. Rawat","No. Rawat", "Tgl. Kunjungan", "Ruangan/Poliklinik/Inst.","NIK", 
            "No. Kartu BPJS", "No. RM", "Nama Pasien", "J.K.", "tglreg", "jnsrwt", "tgllhr", "jk"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbKunjungan.setModel(tabMode2);
        tbKunjungan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKunjungan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 13; i++) {
            TableColumn column = tbKunjungan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(115);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(215);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbKunjungan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No. Rawat Bayi", "Tgl. Msk Bayi", "Tgl. Plg. Bayi", "No. RM Bayi", "Nama Bayi",
            "Umur Bayi", "No. Rawat Ibu", "No. RM Ibu", "Nama Ibunya", "Umur Ibu",
            "No. Klaim Ibu","tglReg", "jnsrwt", "tglLhr", "jk"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbIbuBayi.setModel(tabMode3);
        tbIbuBayi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbIbuBayi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbIbuBayi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(85);
            } else if (i == 2) {
                column.setPreferredWidth(85);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(210);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(60);
            } else if (i == 10) {
                column.setPreferredWidth(120);
            } else if (i == 11) {
//                column.setPreferredWidth(120);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
//                column.setPreferredWidth(120);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
//                column.setPreferredWidth(120);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
//                column.setPreferredWidth(120);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbIbuBayi.setDefaultRenderer(Object.class, new WarnaTable());

        TCari1.setDocument(new batasInput((byte) 100).getKata(TCari1));
        TCarinorm.setDocument(new batasInput((byte) 6).getOnlyAngka(TCarinorm));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }
            });
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

        WindowSEPbpjs = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel15 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel10 = new widget.Label();
        LCount2 = new widget.Label();
        jLabel12 = new widget.Label();
        cmbLimit1 = new widget.ComboBox();
        panelGlass11 = new widget.panelisi();
        jLabel16 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnAll2 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbSEP = new widget.Table();
        WindowRegistrasi = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        jPanel4 = new javax.swing.JPanel();
        panelGlass12 = new widget.panelisi();
        jLabel19 = new widget.Label();
        tgl3 = new widget.Tanggal();
        jLabel20 = new widget.Label();
        tgl4 = new widget.Tanggal();
        jLabel14 = new widget.Label();
        LCount3 = new widget.Label();
        jLabel21 = new widget.Label();
        cmbLimit2 = new widget.ComboBox();
        jLabel29 = new widget.Label();
        cmbJnsRawat1 = new widget.ComboBox();
        panelGlass13 = new widget.panelisi();
        jLabel22 = new widget.Label();
        TCarinorm = new widget.TextBox();
        BtnCari3 = new widget.Button();
        BtnAll3 = new widget.Button();
        BtnKeluar3 = new widget.Button();
        jns_klaim = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        tbKunjungan = new widget.Table();
        WindowIBUBAYI = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        jPanel5 = new javax.swing.JPanel();
        panelGlass14 = new widget.panelisi();
        jLabel24 = new widget.Label();
        tgl5 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        tgl6 = new widget.Tanggal();
        jLabel26 = new widget.Label();
        LCount4 = new widget.Label();
        jLabel27 = new widget.Label();
        cmbLimit3 = new widget.ComboBox();
        panelGlass15 = new widget.panelisi();
        jLabel28 = new widget.Label();
        TCari4 = new widget.TextBox();
        BtnCari4 = new widget.Button();
        BtnAll4 = new widget.Button();
        BtnKeluar4 = new widget.Button();
        Scroll3 = new widget.ScrollPane();
        tbIbuBayi = new widget.Table();
        Popup1 = new javax.swing.JPopupMenu();
        ppPengajuanKlaim = new javax.swing.JMenuItem();
        ppAmbilDataKlaim = new javax.swing.JMenuItem();
        ppExportKlaimFinal = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbINACBG = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        nosep_klaim = new widget.TextBox();
        jLabel7 = new widget.Label();
        noRawat = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbJnsKlaim = new widget.ComboBox();
        BtnData = new widget.Button();
        jLabel23 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();
        BtnProses = new widget.Button();
        panelGlass10 = new widget.panelisi();
        Chktgl = new widget.CekBox();
        tglA = new widget.Tanggal();
        jLabel18 = new widget.Label();
        tglB = new widget.Tanggal();
        jLabel11 = new widget.Label();
        cmbLimit = new widget.ComboBox();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        jLabel9 = new widget.Label();
        LCount1 = new widget.Label();
        BtnKeluar1 = new widget.Button();

        WindowSEPbpjs.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowSEPbpjs.setName("WindowSEPbpjs"); // NOI18N
        WindowSEPbpjs.setUndecorated(true);
        WindowSEPbpjs.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar SEP BPJS Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 95));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. SEP : ");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel15);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2022" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl1KeyPressed(evt);
            }
        });
        panelGlass8.add(tgl1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel17);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2022" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl2KeyPressed(evt);
            }
        });
        panelGlass8.add(tgl2);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass8.add(jLabel10);

        LCount2.setForeground(new java.awt.Color(0, 0, 0));
        LCount2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount2.setText("0");
        LCount2.setName("LCount2"); // NOI18N
        LCount2.setPreferredSize(new java.awt.Dimension(75, 30));
        panelGlass8.add(LCount2);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Limit Data : ");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel12);

        cmbLimit1.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "200", "1000", "Semuanya" }));
        cmbLimit1.setName("cmbLimit1"); // NOI18N
        cmbLimit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLimit1KeyPressed(evt);
            }
        });
        panelGlass8.add(cmbLimit1);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Key Word : ");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel16);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('6');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+6");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass11.add(BtnCari2);

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('M');
        BtnAll2.setText("Semua Data");
        BtnAll2.setToolTipText("Alt+M");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnAll2);

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
        BtnKeluar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar2KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnKeluar2);

        jPanel3.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        internalFrame5.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        Scroll1.setToolTipText("Silahkan pilih salah satu data pasien ibu bersalin");
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbSEP.setToolTipText("");
        tbSEP.setName("tbSEP"); // NOI18N
        tbSEP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSEPMouseClicked(evt);
            }
        });
        tbSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSEPKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbSEP);

        internalFrame5.add(Scroll1, java.awt.BorderLayout.CENTER);

        WindowSEPbpjs.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowRegistrasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRegistrasi.setName("WindowRegistrasi"); // NOI18N
        WindowRegistrasi.setUndecorated(true);
        WindowRegistrasi.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Kunjungan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(44, 95));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Kunj. : ");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(jLabel19);

        tgl3.setEditable(false);
        tgl3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2022" }));
        tgl3.setDisplayFormat("dd-MM-yyyy");
        tgl3.setName("tgl3"); // NOI18N
        tgl3.setOpaque(false);
        tgl3.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass12.add(tgl3);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("s.d");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass12.add(jLabel20);

        tgl4.setEditable(false);
        tgl4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2022" }));
        tgl4.setDisplayFormat("dd-MM-yyyy");
        tgl4.setName("tgl4"); // NOI18N
        tgl4.setOpaque(false);
        tgl4.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass12.add(tgl4);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Record :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass12.add(jLabel14);

        LCount3.setForeground(new java.awt.Color(0, 0, 0));
        LCount3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount3.setText("0");
        LCount3.setName("LCount3"); // NOI18N
        LCount3.setPreferredSize(new java.awt.Dimension(75, 30));
        panelGlass12.add(LCount3);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Limit Data : ");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(jLabel21);

        cmbLimit2.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "200", "1000", "Semuanya" }));
        cmbLimit2.setName("cmbLimit2"); // NOI18N
        cmbLimit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLimit2KeyPressed(evt);
            }
        });
        panelGlass12.add(cmbLimit2);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Jns. Rawat : ");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(jLabel29);

        cmbJnsRawat1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "JALAN", "INAP" }));
        cmbJnsRawat1.setName("cmbJnsRawat1"); // NOI18N
        cmbJnsRawat1.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(cmbJnsRawat1);

        jPanel4.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Cari No. RM :");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(jLabel22);

        TCarinorm.setForeground(new java.awt.Color(0, 0, 0));
        TCarinorm.setName("TCarinorm"); // NOI18N
        TCarinorm.setPreferredSize(new java.awt.Dimension(90, 23));
        TCarinorm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCarinormKeyPressed(evt);
            }
        });
        panelGlass13.add(TCarinorm);

        BtnCari3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('6');
        BtnCari3.setText("Tampilkan Data");
        BtnCari3.setToolTipText("Alt+6");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCari3);

        BtnAll3.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll3.setMnemonic('M');
        BtnAll3.setText("Semua Data");
        BtnAll3.setToolTipText("Alt+M");
        BtnAll3.setName("BtnAll3"); // NOI18N
        BtnAll3.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll3ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnAll3);

        BtnKeluar3.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnKeluar3);

        jns_klaim.setForeground(new java.awt.Color(0, 0, 0));
        jns_klaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jns_klaim.setText("jns_klaim");
        jns_klaim.setName("jns_klaim"); // NOI18N
        jns_klaim.setPreferredSize(new java.awt.Dimension(250, 23));
        panelGlass13.add(jns_klaim);

        jPanel4.add(panelGlass13, java.awt.BorderLayout.PAGE_END);

        internalFrame6.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        Scroll2.setToolTipText("Silahkan pilih salah satu data pasien ibu bersalin");
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbKunjungan.setToolTipText("");
        tbKunjungan.setName("tbKunjungan"); // NOI18N
        tbKunjungan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKunjunganMouseClicked(evt);
            }
        });
        tbKunjungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKunjunganKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbKunjungan);

        internalFrame6.add(Scroll2, java.awt.BorderLayout.CENTER);

        WindowRegistrasi.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowIBUBAYI.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowIBUBAYI.setName("WindowIBUBAYI"); // NOI18N
        WindowIBUBAYI.setUndecorated(true);
        WindowIBUBAYI.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Pasien Ibu dan Bayinya ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame7.setLayout(new java.awt.BorderLayout());

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(44, 95));
        jPanel5.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tgl. Kunj. : ");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass14.add(jLabel24);

        tgl5.setEditable(false);
        tgl5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2022" }));
        tgl5.setDisplayFormat("dd-MM-yyyy");
        tgl5.setName("tgl5"); // NOI18N
        tgl5.setOpaque(false);
        tgl5.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass14.add(tgl5);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass14.add(jLabel25);

        tgl6.setEditable(false);
        tgl6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2022" }));
        tgl6.setDisplayFormat("dd-MM-yyyy");
        tgl6.setName("tgl6"); // NOI18N
        tgl6.setOpaque(false);
        tgl6.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass14.add(tgl6);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Record :");
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass14.add(jLabel26);

        LCount4.setForeground(new java.awt.Color(0, 0, 0));
        LCount4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount4.setText("0");
        LCount4.setName("LCount4"); // NOI18N
        LCount4.setPreferredSize(new java.awt.Dimension(75, 30));
        panelGlass14.add(LCount4);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Limit Data : ");
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass14.add(jLabel27);

        cmbLimit3.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "200", "1000", "Semuanya" }));
        cmbLimit3.setName("cmbLimit3"); // NOI18N
        cmbLimit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLimit3KeyPressed(evt);
            }
        });
        panelGlass14.add(cmbLimit3);

        jPanel5.add(panelGlass14, java.awt.BorderLayout.PAGE_START);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Key Word : ");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass15.add(jLabel28);

        TCari4.setForeground(new java.awt.Color(0, 0, 0));
        TCari4.setName("TCari4"); // NOI18N
        TCari4.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari4KeyPressed(evt);
            }
        });
        panelGlass15.add(TCari4);

        BtnCari4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari4.setMnemonic('6');
        BtnCari4.setText("Tampilkan Data");
        BtnCari4.setToolTipText("Alt+6");
        BtnCari4.setName("BtnCari4"); // NOI18N
        BtnCari4.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari4ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnCari4);

        BtnAll4.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll4.setMnemonic('M');
        BtnAll4.setText("Semua Data");
        BtnAll4.setToolTipText("Alt+M");
        BtnAll4.setName("BtnAll4"); // NOI18N
        BtnAll4.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll4ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnAll4);

        BtnKeluar4.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar4.setMnemonic('K');
        BtnKeluar4.setText("Keluar");
        BtnKeluar4.setToolTipText("Alt+K");
        BtnKeluar4.setName("BtnKeluar4"); // NOI18N
        BtnKeluar4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar4ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnKeluar4);

        jPanel5.add(panelGlass15, java.awt.BorderLayout.PAGE_END);

        internalFrame7.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        Scroll3.setToolTipText("Silahkan pilih salah satu data pasien ibu bersalin");
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbIbuBayi.setToolTipText("");
        tbIbuBayi.setName("tbIbuBayi"); // NOI18N
        tbIbuBayi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIbuBayiMouseClicked(evt);
            }
        });
        tbIbuBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIbuBayiKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbIbuBayi);

        internalFrame7.add(Scroll3, java.awt.BorderLayout.CENTER);

        WindowIBUBAYI.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        Popup1.setName("Popup1"); // NOI18N

        ppPengajuanKlaim.setBackground(new java.awt.Color(242, 242, 242));
        ppPengajuanKlaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPengajuanKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPengajuanKlaim.setText("Proses Pengajuan Klaim");
        ppPengajuanKlaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPengajuanKlaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPengajuanKlaim.setIconTextGap(8);
        ppPengajuanKlaim.setName("ppPengajuanKlaim"); // NOI18N
        ppPengajuanKlaim.setPreferredSize(new java.awt.Dimension(240, 25));
        ppPengajuanKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPengajuanKlaimBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppPengajuanKlaim);

        ppAmbilDataKlaim.setBackground(new java.awt.Color(242, 242, 242));
        ppAmbilDataKlaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppAmbilDataKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppAmbilDataKlaim.setText("Ambil Data Klaim INACBG");
        ppAmbilDataKlaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppAmbilDataKlaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppAmbilDataKlaim.setIconTextGap(8);
        ppAmbilDataKlaim.setName("ppAmbilDataKlaim"); // NOI18N
        ppAmbilDataKlaim.setPreferredSize(new java.awt.Dimension(240, 25));
        ppAmbilDataKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppAmbilDataKlaimBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppAmbilDataKlaim);

        ppExportKlaimFinal.setBackground(new java.awt.Color(242, 242, 242));
        ppExportKlaimFinal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppExportKlaimFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        ppExportKlaimFinal.setText("Klaim JKN Final Diexport Ke File Excel ");
        ppExportKlaimFinal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppExportKlaimFinal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppExportKlaimFinal.setIconTextGap(8);
        ppExportKlaimFinal.setName("ppExportKlaimFinal"); // NOI18N
        ppExportKlaimFinal.setPreferredSize(new java.awt.Dimension(240, 25));
        ppExportKlaimFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppExportKlaimFinalBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppExportKlaimFinal);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Klaim INACBG ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbINACBG.setAutoCreateRowSorter(true);
        tbINACBG.setToolTipText("");
        tbINACBG.setComponentPopupMenu(Popup1);
        tbINACBG.setName("tbINACBG"); // NOI18N
        tbINACBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbINACBGMouseClicked(evt);
            }
        });
        tbINACBG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbINACBGKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbINACBG);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("No. SEP/No. Klaim : ");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass9.add(jLabel6);

        nosep_klaim.setEditable(false);
        nosep_klaim.setForeground(new java.awt.Color(0, 0, 0));
        nosep_klaim.setName("nosep_klaim"); // NOI18N
        nosep_klaim.setPreferredSize(new java.awt.Dimension(143, 23));
        panelGlass9.add(nosep_klaim);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("No. Rawat : ");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel7);

        noRawat.setEditable(false);
        noRawat.setForeground(new java.awt.Color(0, 0, 0));
        noRawat.setName("noRawat"); // NOI18N
        noRawat.setPreferredSize(new java.awt.Dimension(135, 23));
        panelGlass9.add(noRawat);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jns. Klaim : ");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel13);

        cmbJnsKlaim.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsKlaim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "JKN", "JAMINAN COVID-19", "JAMINAN KIPI", "JAMINAN CO-INSIDENSE", "JAMINAN BAYI BARU LAHIR" }));
        cmbJnsKlaim.setName("cmbJnsKlaim"); // NOI18N
        cmbJnsKlaim.setPreferredSize(new java.awt.Dimension(165, 23));
        cmbJnsKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJnsKlaimActionPerformed(evt);
            }
        });
        panelGlass9.add(cmbJnsKlaim);

        BtnData.setForeground(new java.awt.Color(0, 0, 0));
        BtnData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnData.setMnemonic('1');
        BtnData.setToolTipText("Alt+1");
        BtnData.setName("BtnData"); // NOI18N
        BtnData.setPreferredSize(new java.awt.Dimension(28, 26));
        BtnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataActionPerformed(evt);
            }
        });
        BtnData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDataKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnData);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Jns. Rawat : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "JALAN", "INAP" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(cmbJnsRawat);

        BtnProses.setForeground(new java.awt.Color(0, 0, 0));
        BtnProses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/New.png"))); // NOI18N
        BtnProses.setMnemonic('1');
        BtnProses.setText("Proses Klaim");
        BtnProses.setToolTipText("Alt+1");
        BtnProses.setName("BtnProses"); // NOI18N
        BtnProses.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProsesActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnProses);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        Chktgl.setBackground(new java.awt.Color(255, 255, 250));
        Chktgl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        Chktgl.setForeground(new java.awt.Color(0, 0, 0));
        Chktgl.setSelected(true);
        Chktgl.setText("Tgl. Klaim : ");
        Chktgl.setBorderPainted(true);
        Chktgl.setBorderPaintedFlat(true);
        Chktgl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Chktgl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chktgl.setName("Chktgl"); // NOI18N
        Chktgl.setPreferredSize(new java.awt.Dimension(95, 23));
        Chktgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChktglActionPerformed(evt);
            }
        });
        panelGlass10.add(Chktgl);

        tglA.setEditable(false);
        tglA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2022" }));
        tglA.setDisplayFormat("dd-MM-yyyy");
        tglA.setName("tglA"); // NOI18N
        tglA.setOpaque(false);
        tglA.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(tglA);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("s.d");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel18);

        tglB.setEditable(false);
        tglB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2022" }));
        tglB.setDisplayFormat("dd-MM-yyyy");
        tglB.setName("tglB"); // NOI18N
        tglB.setOpaque(false);
        tglB.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(tglB);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Limit Data : ");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel11);

        cmbLimit.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "200", "1000", "Semuanya" }));
        cmbLimit.setName("cmbLimit"); // NOI18N
        panelGlass10.add(cmbLimit);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass10.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnCari1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnAll1);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('4');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+4");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnKeluar1);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProsesActionPerformed
        if (noRawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data pasien berdasarkan No. Rawat belum dipilih,...!!");
        } else {
            if (kd_payor.equals("3")) {
                if (jnsRawat.equals("Rawat Jalan")) {
                    if (Sequel.cariInteger("SELECT COUNT(-1) FROM bridging_sep WHERE nomr='" + Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + noRawat.getText() + "'") + "' "
                            + "AND tglsep='" + tglSep + "' AND jnspelayanan='1'") > 0) {
                        i = JOptionPane.showConfirmDialog(null, "Pasien telah memiliki SEP Rawat Jalan & Rawat Inap dihari yang sama, Apakah proses klaim akan tetap dilanjutkan,...!!", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (i == JOptionPane.YES_OPTION) {
                            klaimBaruJKN();
                        }
                    } else {
                        klaimBaruJKN();
                    }
                } else {
                    klaimBaruJKN();
                }
                
            } else {
                if (nosep_klaim.getText().equals("-")) {
                    JOptionPane.showMessageDialog(null, "Lakukan terlebih dulu proses pengajuan klaim Ibunya utk. pasien bayi An. " + nmpas + " ...!!");
                } else if (kd_payor.equals("75") && jnsrwt.equals("2")) {
                    JOptionPane.showMessageDialog(null, "Untuk " + cmbJnsKlaim.getSelectedItem().toString() + " proses pengajuan klaim hanya bisa pada kasus rawat inap saja...!!");
                    BtnData.requestFocus();
                } else {
                    klaimBaruLAINYA();
                }
            }
        }
}//GEN-LAST:event_BtnProsesActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Chktgl.isSelected() == true) {
            tampilKLAIM();
        } else {
            tampilSEP();
        }        
    }//GEN-LAST:event_formWindowOpened

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if (Chktgl.isSelected() == true) {
            tampilKLAIM();
        } else {
            tampilSEP();
        }        
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        nosep_klaim.setText("");
        noRawat.setText("");
        cmbJnsKlaim.setSelectedIndex(0);
        cmbJnsRawat.setSelectedIndex(0);
        TCari1.setText("");
        
        if (Chktgl.isSelected() == true) {
            tampilKLAIM();
        } else {
            tampilSEP();
        } 
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataActionPerformed
        if (cmbJnsKlaim.getSelectedItem().equals("JKN")) {
            tgl1.setDate(new Date());
            Calendar kalender = Calendar.getInstance();
            kalender.add(Calendar.DATE, -30);
            tgl1.setDate(kalender.getTime());
            tgl2.setDate(new Date());
            cmbLimit1.setSelectedIndex(0);
            tglSep = "";
            TCari2.setText("");
            TCari2.requestFocus();
            tampil();
            WindowSEPbpjs.setSize(870, 512);
            WindowSEPbpjs.setLocationRelativeTo(internalFrame1);
            WindowSEPbpjs.setVisible(true);            
        } else if (cmbJnsKlaim.getSelectedItem().equals("JAMINAN COVID-19")
                || cmbJnsKlaim.getSelectedItem().equals("JAMINAN KIPI")                
                || cmbJnsKlaim.getSelectedItem().equals("JAMINAN CO-INSIDENSE")) {
            tgl3.setDate(new Date());
            Calendar kalender = Calendar.getInstance();
            kalender.add(Calendar.DATE, -30);
            tgl3.setDate(kalender.getTime());
            tgl4.setDate(new Date());
            jns_klaim.setText("Jns. Klaim : " + cmbJnsKlaim.getSelectedItem());
            cmbLimit2.setSelectedIndex(0);            
            TCarinorm.setText("");
            TCarinorm.requestFocus();
            if (cmbJnsKlaim.getSelectedItem().equals("JAMINAN COVID-19") || cmbJnsKlaim.getSelectedItem().equals("JAMINAN CO-INSIDENSE")) {
                cmbJnsRawat1.setSelectedIndex(2);
            } else {
                cmbJnsRawat1.setSelectedIndex(0);
            }
            tampilReg();
            WindowRegistrasi.setSize(1120, 600);
            WindowRegistrasi.setLocationRelativeTo(internalFrame1);
            WindowRegistrasi.setVisible(true);
        } else if (cmbJnsKlaim.getSelectedItem().equals("JAMINAN BAYI BARU LAHIR")) {
            tgl5.setDate(new Date());
            Calendar kalender = Calendar.getInstance();
            kalender.add(Calendar.DATE, -30);
            tgl5.setDate(kalender.getTime());
            tgl6.setDate(new Date());
            cmbLimit3.setSelectedIndex(0);
            TCari4.setText("");
            TCari4.requestFocus();
            tampilIbuBayi();
            WindowIBUBAYI.setSize(886, 512);
            WindowIBUBAYI.setLocationRelativeTo(internalFrame1);
            WindowIBUBAYI.setVisible(true);
        } else if (cmbJnsKlaim.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu pilihan jenis klaim yang akan diproses,...!!");
            cmbJnsKlaim.requestFocus();
        }
    }//GEN-LAST:event_BtnDataActionPerformed

    private void BtnDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDataKeyPressed
        Valid.pindah(evt, nosep_klaim, BtnProses);
    }//GEN-LAST:event_BtnDataKeyPressed

    private void tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl1KeyPressed

    private void tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl2KeyPressed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnCari1);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari2.setText("");
        tampil();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//            tampilPersalinan();
            TCari1.setText("");
        } else {
            Valid.pindah(evt, TCari1, BtnKeluar1);
        }
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        WindowSEPbpjs.dispose();
        tbINACBG.requestFocus();
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            WindowSEPbpjs.dispose();
        }
    }//GEN-LAST:event_BtnKeluar2KeyPressed

    private void tbSEPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSEPMouseClicked
        if (tabMode1.getRowCount() != 0) {
//            try {
//                getDataSEP();
//            } catch (java.lang.NullPointerException e) {
//            }

            if (evt.getClickCount() == 2) {
                tglSep = "";
                kd_payor = "3";
                jnsRawat = "";
                noRawat.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString());
                nosep_klaim.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString());
                tglSep = tbSEP.getValueAt(tbSEP.getSelectedRow(), 10).toString();
                jnsRawat = tbSEP.getValueAt(tbSEP.getSelectedRow(), 7).toString();
                WindowSEPbpjs.dispose();
                BtnProses.requestFocus();
            }
        }
    }//GEN-LAST:event_tbSEPMouseClicked

    private void tbSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSEPKeyPressed
        if (tabMode1.getRowCount() != 0) {
//            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
//                try {
//                    getDataSEP();
//                } catch (java.lang.NullPointerException e) {
//                }
//            }

            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                tglSep = "";
                kd_payor = "3";
                jnsRawat = "";
                noRawat.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString());
                nosep_klaim.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString());
                tglSep = tbSEP.getValueAt(tbSEP.getSelectedRow(), 10).toString();
                jnsRawat = tbSEP.getValueAt(tbSEP.getSelectedRow(), 7).toString();
                WindowSEPbpjs.dispose();
                BtnProses.requestFocus();
            }
        }
    }//GEN-LAST:event_tbSEPKeyPressed

    private void ChktglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChktglActionPerformed
        if (Chktgl.isSelected() == true) {
            Chktgl.setText("Tgl. Klaim : ");
        } else {
            Chktgl.setText("Tgl. SEP : ");
        }
    }//GEN-LAST:event_ChktglActionPerformed

    private void ppPengajuanKlaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPengajuanKlaimBtnPrintActionPerformed
        if (noRawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data pasien berdasarkan No. SEP/No. Klaim belum dipilih,...!!");
        } else {
            if (kd_payor.equals("3")) {
                klaimLamaJKN();
            } else {
                klaimLamaLAINYA();
            }
        }
    }//GEN-LAST:event_ppPengajuanKlaimBtnPrintActionPerformed

    private void tbINACBGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbINACBGKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbINACBGKeyPressed

    private void tbINACBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbINACBGMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbINACBGMouseClicked

    private void cmbLimit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLimit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLimit1KeyPressed

    private void cmbLimit2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLimit2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLimit2KeyPressed

    private void TCarinormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCarinormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari3ActionPerformed(null);
        }
    }//GEN-LAST:event_TCarinormKeyPressed

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        tampilReg();
    }//GEN-LAST:event_BtnCari3ActionPerformed

    private void BtnAll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll3ActionPerformed
        TCarinorm.setText("");
        tampilReg();
    }//GEN-LAST:event_BtnAll3ActionPerformed

    private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
        WindowRegistrasi.dispose();
        tbINACBG.requestFocus();
    }//GEN-LAST:event_BtnKeluar3ActionPerformed

    private void tbKunjunganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKunjunganMouseClicked
        if (tabMode2.getRowCount() != 0) {
            if (evt.getClickCount() == 2) {
                noka = "";
                nik = "";
                nosep_klaim.setText("");                
                tglKunj = "";
                jnsrwt = "";
                norm = "";
                nmpas = "";
                tgllahir = "";
                jk = "";
                kd_payor = "";
                noRawat.setText(tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 1).toString());
                nik = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 4).toString();
                noka = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 5).toString();
                tglKunj = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 9).toString();
                jnsrwt = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 10).toString();
                norm = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 6).toString();
                nmpas = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 7).toString();
                tgllahir = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 11).toString();
                jk = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 12).toString();
                
                if (jnsKlaim.equals("JAMINAN COVID-19")) {
                    kd_payor = "71";
                } else if (jnsKlaim.equals("JAMINAN KIPI")) {
                    kd_payor = "72";
                } else if (jnsKlaim.equals("JAMINAN CO-INSIDENSE")) {
                    kd_payor = "75";
                }

                WindowRegistrasi.dispose();
                BtnProses.requestFocus();
            }
        }
    }//GEN-LAST:event_tbKunjunganMouseClicked

    private void tbKunjunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKunjunganKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                noka = "";
                nik = "";
                nosep_klaim.setText("");                
                tglKunj = "";
                jnsrwt = "";
                norm = "";
                nmpas = "";
                tgllahir = "";
                jk = "";
                kd_payor = "";
                noRawat.setText(tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 1).toString());
                nik = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 4).toString();
                noka = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 5).toString();
                tglKunj = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 9).toString();
                jnsrwt = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 10).toString();
                norm = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 6).toString();
                nmpas = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 7).toString();
                tgllahir = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 11).toString();
                jk = tbKunjungan.getValueAt(tbKunjungan.getSelectedRow(), 12).toString();
                
                if (jnsKlaim.equals("JAMINAN COVID-19")) {
                    kd_payor = "71";
                } else if (jnsKlaim.equals("JAMINAN KIPI")) {
                    kd_payor = "72";
                } else if (jnsKlaim.equals("JAMINAN CO-INSIDENSE")) {
                    kd_payor = "75";
                }
                
                WindowRegistrasi.dispose();
                BtnProses.requestFocus();
            }
        }
    }//GEN-LAST:event_tbKunjunganKeyPressed

    private void ppAmbilDataKlaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppAmbilDataKlaimBtnPrintActionPerformed
        if (noRawat.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Data pasien berdasarkan No. SEP/No. Klaim belum dipilih,...!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah data diagnosa pada Eklaim sudah sama dengan diagnosa di SIMRS ?...", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                mbak_eka.mengambilData(noRawat.getText(), nosep_klaim.getText(), tglSep);
                if (Chktgl.isSelected() == true) {
                    tampilKLAIM();
                } else {
                    tampilSEP();
                }
            } 
        }
    }//GEN-LAST:event_ppAmbilDataKlaimBtnPrintActionPerformed

    private void cmbJnsKlaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJnsKlaimActionPerformed
        if (cekData.equals("")) {
            setData("didalam");
        } else {
            setData("dari luar");
        }
        
        if (cmbJnsKlaim.getSelectedIndex() == 0) {
            jnsKlaim = "";
        } else if (cmbJnsKlaim.getSelectedIndex() == 1) {
            jnsKlaim = "JKN";
        } else if (cmbJnsKlaim.getSelectedIndex() == 2) {
            jnsKlaim = "JAMINAN COVID-19";
        } else if (cmbJnsKlaim.getSelectedIndex() == 3) {
            jnsKlaim = "JAMINAN KIPI";
        } else if (cmbJnsKlaim.getSelectedIndex() == 4) {
            jnsKlaim = "JAMINAN CO-INSIDENSE";
        } else if (cmbJnsKlaim.getSelectedIndex() == 5) {
            jnsKlaim = "JAMINAN BAYI BARU LAHIR";
        }
    }//GEN-LAST:event_cmbJnsKlaimActionPerformed

    private void cmbLimit3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLimit3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLimit3KeyPressed

    private void TCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari4ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari4KeyPressed

    private void BtnCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari4ActionPerformed
        tampilIbuBayi();
    }//GEN-LAST:event_BtnCari4ActionPerformed

    private void BtnAll4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll4ActionPerformed
        TCari4.setText("");
        tampilIbuBayi();
    }//GEN-LAST:event_BtnAll4ActionPerformed

    private void BtnKeluar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar4ActionPerformed
        WindowIBUBAYI.dispose();
        tbINACBG.requestFocus();
    }//GEN-LAST:event_BtnKeluar4ActionPerformed

    private void tbIbuBayiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIbuBayiMouseClicked
        if (tabMode3.getRowCount() != 0) {
            if (evt.getClickCount() == 2) {
                noka = "";                
                nosep_klaim.setText("");                
                nik = "";
                tglKunj = "";
                jnsrwt = "";
                norm = "";
                nmpas = "";
                tgllahir = "";
                jk = "";
                nmibu = "";
                kd_payor = "73";
                noRawat.setText(tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 0).toString());
                nik = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 10).toString();
                tglKunj = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 11).toString();
                jnsrwt = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 12).toString();
                norm = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 3).toString();
                nmpas = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 4).toString();
                tgllahir = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 13).toString();
                jk = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 14).toString();
                nmibu = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 8).toString();
                nosep_klaim.setText(nik);
                
                WindowIBUBAYI.dispose();
                BtnProses.requestFocus();
            }
        }
    }//GEN-LAST:event_tbIbuBayiMouseClicked

    private void tbIbuBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIbuBayiKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                noka = "";
                nosep_klaim.setText("");
                nik = "";
                tglKunj = "";
                jnsrwt = "";
                norm = "";
                nmpas = "";
                tgllahir = "";
                jk = "";
                nmibu = "";
                kd_payor = "73";
                noRawat.setText(tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 0).toString());
                nik = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 10).toString();
                tglKunj = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 11).toString();
                jnsrwt = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 12).toString();
                norm = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 3).toString();
                nmpas = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 4).toString();
                tgllahir = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 13).toString();
                jk = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 14).toString();
                nmibu = tbIbuBayi.getValueAt(tbIbuBayi.getSelectedRow(), 8).toString();
                nosep_klaim.setText(nik);

                WindowIBUBAYI.dispose();
                BtnProses.requestFocus();
            }
        }
    }//GEN-LAST:event_tbIbuBayiKeyPressed

    private void ppExportKlaimFinalBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppExportKlaimFinalBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            tglA.requestFocus();
        } else if (Chktgl.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Contengan tgl. klaim harus diganti dulu menjadi tgl. sep...!!!!");
            Chktgl.requestFocus();
        } else if (cmbJnsRawat.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis rawatnya dengan benar...!!!!");
            cmbJnsRawat.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (cmbJnsRawat.getSelectedIndex() == 2) {
                dialog_simpan = Valid.openDialog();
                Valid.MyReportToExcel("SELECT e.no_sep 'No. SEP', DATE_FORMAT(bs.tglsep,'%d/%m/%Y') 'Tgl. SEP/MRS', IF(DATE_FORMAT(bs.tglpulang,'%d/%m/%Y')='00/00/0000','-',DATE_FORMAT(bs.tglpulang,'%d/%m/%Y')) 'Tgl. Pulang', "
                        + "p.no_rkm_medis 'No. RM', p.nm_pasien 'Nama Pasien', b.nm_bangsal 'Rg. Perawatan Inap', d.nm_dokter 'DPJP', e.klaim_final 'Status Klaim' FROM eklaim_new_claim e "
                        + "INNER JOIN bridging_sep bs ON bs.no_rawat=e.no_rawat INNER JOIN reg_periksa rp ON rp.no_rawat=bs.no_rawat AND rp.status_lanjut='Ranap' "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki ON ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "INNER JOIN dpjp_ranap dr ON dr.no_rawat=ki.no_rawat INNER JOIN dokter d ON d.kd_dokter=dr.kd_dokter WHERE "
                        + "bs.tglsep BETWEEN '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' AND e.klaim_final='final' "
                        + "AND ki.stts_pulang NOT IN ('-','pindah kamar') ORDER BY bs.tglsep, b.nm_bangsal", dialog_simpan);
                JOptionPane.showMessageDialog(null, "Data Klaim JKN berdasarkan SEP Rawat Inap berhasil diexport menjadi file excel,..!!!");
            } else if (cmbJnsRawat.getSelectedIndex() == 1) {
                dialog_simpan = Valid.openDialog();
                Valid.MyReportToExcel("SELECT e.no_sep 'No. SEP', DATE_FORMAT(bs.tglsep,'%d/%m/%Y') 'Tgl. SEP/Kunjungan', p.no_rkm_medis 'No. RM', "
                        + "p.nm_pasien 'Nama Pasien', pl.nm_poli 'Poliklinik/Unit/Inst.', d.nm_dokter 'Nama Dokter', e.klaim_final 'Status Klaim' FROM eklaim_new_claim e "
                        + "INNER JOIN bridging_sep bs ON bs.no_rawat=e.no_rawat INNER JOIN reg_periksa rp ON rp.no_rawat=bs.no_rawat AND rp.status_lanjut='Ralan' "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                        + "INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter WHERE "
                        + "bs.tglsep BETWEEN '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' AND e.klaim_final='final' "
                        + "ORDER BY bs.tglsep, pl.nm_poli", dialog_simpan);
                JOptionPane.showMessageDialog(null, "Data Klaim JKN berdasarkan SEP Rawat Jalan berhasil diexport menjadi file excel,..!!!");
            }
            this.setCursor(Cursor.getDefaultCursor());            
        }
    }//GEN-LAST:event_ppExportKlaimFinalBtnPrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                INACBGDaftarKlaim dialog = new INACBGDaftarKlaim(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll1;
    private widget.Button BtnAll2;
    private widget.Button BtnAll3;
    private widget.Button BtnAll4;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCari3;
    private widget.Button BtnCari4;
    private widget.Button BtnData;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar3;
    private widget.Button BtnKeluar4;
    public widget.Button BtnProses;
    public widget.CekBox Chktgl;
    private widget.Label LCount1;
    private widget.Label LCount2;
    private widget.Label LCount3;
    private widget.Label LCount4;
    private javax.swing.JPopupMenu Popup1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TCari4;
    private widget.TextBox TCarinorm;
    private javax.swing.JDialog WindowIBUBAYI;
    private javax.swing.JDialog WindowRegistrasi;
    private javax.swing.JDialog WindowSEPbpjs;
    public widget.ComboBox cmbJnsKlaim;
    public widget.ComboBox cmbJnsRawat;
    public widget.ComboBox cmbJnsRawat1;
    public widget.ComboBox cmbLimit;
    public widget.ComboBox cmbLimit1;
    public widget.ComboBox cmbLimit2;
    public widget.ComboBox cmbLimit3;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
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
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private widget.Label jns_klaim;
    private widget.TextBox noRawat;
    private widget.TextBox nosep_klaim;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppAmbilDataKlaim;
    private javax.swing.JMenuItem ppExportKlaimFinal;
    private javax.swing.JMenuItem ppPengajuanKlaim;
    private widget.Table tbINACBG;
    private widget.Table tbIbuBayi;
    private widget.Table tbKunjungan;
    private widget.Table tbSEP;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tgl3;
    private widget.Tanggal tgl4;
    private widget.Tanggal tgl5;
    private widget.Tanggal tgl6;
    private widget.Tanggal tglA;
    private widget.Tanggal tglB;
    // End of variables declaration//GEN-END:variables

    private void tampilSEP() {
        nilaiRWT = "";
        Valid.tabelKosong(tabMode);
        if (cmbJnsRawat.getSelectedItem().equals("INAP")) {
            nilaiRWT = "%ranap%";
        } else if (cmbJnsRawat.getSelectedItem().equals("JALAN")) {
            nilaiRWT = "%ralan%";
        } else {
            nilaiRWT = "%%";
        }
        
        try {
            if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                ps = koneksi.prepareStatement("SELECT enc.no_rawat,esc.payor_cd,enc.no_sep,if(enc.jnspelayanan='1','Inap','Jalan') Rawat,if(esc.jenis_rawat='1',eg.kelas,'') Kelas, "
                        + "if(esc.upgrade_class_ind='1',esc.upgrade_class_class,'') naik_kelas,eg.cbg_code,eg.cbg_tarif,eg.sub_acute_tarif,eg.chronic_tarif, "
                        + "(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) special_cmg_tarif, "
                        + "eg.cbg_tarif+eg.sub_acute_tarif+eg.chronic_tarif+(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) total_tarif_grouping, "
                        + "if(esc.upgrade_class_ind='1',eg.add_payment_amt,0) naik_kelas_tarif, round(esc.real_tarif,0) real_tarif,enc.klaim_final,if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') kirim_online, "
                        + "enc.nm_pasien, date_format(enc.tgl_input,'%d-%m-%Y') tglInput, enc.tglsep, p.nama nm_petugas, IFNULL(ecd.top_up_rawat,'0') trf_covid, enc.no_rm, "
                        + "IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',b.nm_bangsal)) unit, "
                        + "IFNULL(egc.payor_id,'3') payor_id FROM eklaim_new_claim enc LEFT JOIN eklaim_generate_claim egc ON egc.claim_number=enc.no_sep "
                        + "LEFT JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep LEFT JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep LEFT JOIN eklaim_online_status eos ON eos.no_sep=enc.no_sep "
                        + "LEFT JOIN inacbg_coder_nik koder ON koder.no_ik=esc.coder_nik LEFT JOIN pegawai p ON p.nik=koder.nik LEFT JOIN eklaim_covid19_data ecd ON ecd.no_sep=enc.no_sep "
                        + "LEFT JOIN reg_periksa rp on rp.no_rawat=enc.no_rawat LEFT JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                        + "left join kamar_inap ki on ki.no_rawat = rp.no_rawat left JOIN kamar k ON k.kd_kamar=ki.kd_kamar left JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_rawat like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and esc.payor_cd like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_sep like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.klaim_final like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and p.nama like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_rm like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',b.nm_bangsal)) like ? "
                        + "order by enc.tglsep desc limit " + cmbLimit.getSelectedItem().toString() + "");
            } else {
                ps = koneksi.prepareStatement("SELECT enc.no_rawat,esc.payor_cd,enc.no_sep,if(enc.jnspelayanan='1','Inap','Jalan') Rawat,if(esc.jenis_rawat='1',eg.kelas,'') Kelas, "
                        + "if(esc.upgrade_class_ind='1',esc.upgrade_class_class,'') naik_kelas,eg.cbg_code,eg.cbg_tarif,eg.sub_acute_tarif,eg.chronic_tarif, "
                        + "(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) special_cmg_tarif, "
                        + "eg.cbg_tarif+eg.sub_acute_tarif+eg.chronic_tarif+(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) total_tarif_grouping, "
                        + "if(esc.upgrade_class_ind='1',eg.add_payment_amt,0) naik_kelas_tarif, round(esc.real_tarif,0) real_tarif,enc.klaim_final,if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') kirim_online, "
                        + "enc.nm_pasien, date_format(enc.tgl_input,'%d-%m-%Y') tglInput, enc.tglsep, p.nama nm_petugas, IFNULL(ecd.top_up_rawat,'0') trf_covid, enc.no_rm, "
                        + "IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',b.nm_bangsal)) unit, "
                        + "IFNULL(egc.payor_id,'3') payor_id FROM eklaim_new_claim enc LEFT JOIN eklaim_generate_claim egc ON egc.claim_number=enc.no_sep "
                        + "LEFT JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep LEFT JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep LEFT JOIN eklaim_online_status eos ON eos.no_sep=enc.no_sep "
                        + "LEFT JOIN inacbg_coder_nik koder ON koder.no_ik=esc.coder_nik LEFT JOIN pegawai p ON p.nik=koder.nik LEFT JOIN eklaim_covid19_data ecd ON ecd.no_sep=enc.no_sep "
                        + "LEFT JOIN reg_periksa rp on rp.no_rawat=enc.no_rawat LEFT JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                        + "left join kamar_inap ki on ki.no_rawat = rp.no_rawat left JOIN kamar k ON k.kd_kamar=ki.kd_kamar left JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_rawat like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and esc.payor_cd like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_sep like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.klaim_final like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and p.nama like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_rm like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',b.nm_bangsal)) like ? "
                        + "order by enc.tglsep desc");
            }
            try {
                 if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%");
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");   
                } else {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%");
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), 
                        rs.getString("payor_cd"), 
                        rs.getString("no_sep"),
                        rs.getString("no_rm"),
                        rs.getString("nm_pasien"),
                        rs.getString("Rawat"),
                        rs.getString("unit"),
                        rs.getString("Kelas"),
                        rs.getString("naik_kelas"),
                        rs.getString("cbg_code"),
                        rs.getDouble("cbg_tarif"),
                        rs.getDouble("sub_acute_tarif"),
                        rs.getDouble("chronic_tarif"),
                        rs.getDouble("special_cmg_tarif"),
                        rs.getDouble("total_tarif_grouping"),
                        rs.getDouble("naik_kelas_tarif"),
                        rs.getDouble("trf_covid"),
                        rs.getDouble("real_tarif"),
                        rs.getString("klaim_final"),
                        rs.getString("nm_petugas"),
                        rs.getString("tglInput"),
                        rs.getString("kirim_online"),
                        rs.getString("tglsep"),
                        rs.getString("payor_id")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode.getRowCount());
    }

    public JTable getTable() {
        return tbINACBG;
    }
    
    private void tampil() {
        Valid.tabelKosong(tabMode1);
        try {
            if (cmbLimit1.getSelectedItem().equals("Semuanya")) {
                ps1 = koneksi.prepareStatement("SELECT no_rawat, no_kartu, no_sep, DATE_FORMAT(tglsep,'%d-%m-%Y') tgl_SEP, nomr, nama_pasien, jkel,  "
                        + "IF(jnspelayanan='1','Rawat Inap','Rawat Jalan') jns_rwt, concat(tanggal_lahir,' ','00:00:00') tgl_lhr, IF(jkel='L','1','2') jknya, tglsep FROM bridging_sep WHERE "
                        + "tglsep BETWEEN ? AND ? and no_rawat like ? or "
                        + "tglsep BETWEEN ? AND ? and no_kartu like ? or "
                        + "tglsep BETWEEN ? AND ? and no_sep like ? or "
                        + "tglsep BETWEEN ? AND ? and nomr like ? or "
                        + "tglsep BETWEEN ? AND ? and nama_pasien like ? or "
                        + "tglsep BETWEEN ? AND ? and jkel like ? or "
                        + "tglsep BETWEEN ? AND ? and IF(jnspelayanan='1','Rawat Inap','Rawat Jalan') like ? ORDER BY no_sep DESC");
            } else {
                ps1 = koneksi.prepareStatement("SELECT no_rawat, no_kartu, no_sep, DATE_FORMAT(tglsep,'%d-%m-%Y') tgl_SEP, nomr, nama_pasien, jkel,  "
                        + "IF(jnspelayanan='1','Rawat Inap','Rawat Jalan') jns_rwt, concat(tanggal_lahir,' ','00:00:00') tgl_lhr, IF(jkel='L','1','2') jknya, tglsep FROM bridging_sep WHERE "
                        + "tglsep BETWEEN ? AND ? and no_rawat like ? or "
                        + "tglsep BETWEEN ? AND ? and no_kartu like ? or "
                        + "tglsep BETWEEN ? AND ? and no_sep like ? or "
                        + "tglsep BETWEEN ? AND ? and nomr like ? or "
                        + "tglsep BETWEEN ? AND ? and nama_pasien like ? or "
                        + "tglsep BETWEEN ? AND ? and jkel like ? or "
                        + "tglsep BETWEEN ? AND ? and IF(jnspelayanan='1','Rawat Inap','Rawat Jalan') like ? "
                        + "ORDER BY no_sep DESC limit " + cmbLimit1.getSelectedItem().toString() + "");
            }            
            try {
                ps1.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(3, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(6, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(9, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(12, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(15, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(18, "%" + TCari2.getText().trim() + "%");
                ps1.setString(19, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(20, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(21, "%" + TCari2.getText().trim() + "%");                
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("no_kartu"),
                        rs1.getString("no_sep"),
                        rs1.getString("tgl_SEP"),
                        rs1.getString("nomr"),
                        rs1.getString("nama_pasien"),                        
                        rs1.getString("jkel"),
                        rs1.getString("jns_rwt"),
                        rs1.getString("tgl_lhr"),
                        rs1.getString("jknya"),
                        rs1.getString("tglsep")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount2.setText("" + tabMode1.getRowCount());
    }
    
    private void tampilReg() {
        nilaiRWT = "";
        Valid.tabelKosong(tabMode2);
        if (cmbJnsRawat1.getSelectedItem().equals("INAP")) {
            nilaiRWT = "%ranap%";
        } else if (cmbJnsRawat1.getSelectedItem().equals("JALAN")) {
            nilaiRWT = "%ralan%";
        } else {
            nilaiRWT = "%%";
        }
        
        try {
            if (cmbLimit2.getSelectedItem().equals("Semuanya")) {
                ps2 = koneksi.prepareStatement("SELECT rp.no_rawat, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_kunjungan, "
                        + "IF(rp.status_lanjut='Ralan',concat('Poli/Inst. ',poli.nm_poli),(SELECT concat('Rg. ',b.nm_bangsal) FROM kamar_inap ki "
                        + "LEFT JOIN kamar k ON k.kd_kamar=ki.kd_kamar LEFT JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE ki.no_rawat=rp.no_rawat AND ki.stts_pulang<>'Pindah Kamar')) nm_unit, "
                        + "p.no_ktp nik, p.no_peserta noka_bpjs, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, IF(rp.status_lanjut='Ralan','R. JALAN','R. INAP') jns_rwt, "
                        + "rp.tgl_registrasi, IF(rp.status_lanjut='Ralan','2','1') jRawat, CONCAT(p.tgl_lahir,' ','00:00:00') tgllhr, IF(p.jk='L','1','2') jkel FROM reg_periksa rp "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN poliklinik poli ON poli.kd_poli=rp.kd_poli WHERE "
                        + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl3.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl4.getSelectedItem() + "") + "' "
                        + "AND rp.status_lanjut like '" + nilaiRWT + "' and p.no_rkm_medis LIKE '%" + TCarinorm.getText().trim() + "%' ORDER BY rp.tgl_registrasi DESC, rp.no_rawat DESC");
            } else {
                ps2 = koneksi.prepareStatement("SELECT rp.no_rawat, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_kunjungan, "
                        + "IF(rp.status_lanjut='Ralan',concat('Poli/Inst. ',poli.nm_poli),(SELECT concat('Rg. ',b.nm_bangsal) FROM kamar_inap ki "
                        + "LEFT JOIN kamar k ON k.kd_kamar=ki.kd_kamar LEFT JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE ki.no_rawat=rp.no_rawat AND ki.stts_pulang<>'Pindah Kamar')) nm_unit, "
                        + "p.no_ktp nik, p.no_peserta noka_bpjs, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, IF(rp.status_lanjut='Ralan','R. JALAN','R. INAP') jns_rwt, "
                        + "rp.tgl_registrasi, IF(rp.status_lanjut='Ralan','2','1') jRawat, CONCAT(p.tgl_lahir,' ','00:00:00') tgllhr, IF(p.jk='L','1','2') jkel FROM reg_periksa rp "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN poliklinik poli ON poli.kd_poli=rp.kd_poli WHERE "
                        + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl3.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl4.getSelectedItem() + "") + "' "
                        + "AND rp.status_lanjut like '" + nilaiRWT + "' and p.no_rkm_medis LIKE '%" + TCarinorm.getText().trim() + "%' "
                        + "ORDER BY rp.tgl_registrasi DESC, rp.no_rawat DESC limit " + cmbLimit2.getSelectedItem().toString() + "");
            }
            try {                
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        rs2.getString("jns_rwt"),
                        rs2.getString("no_rawat"),
                        rs2.getString("tgl_kunjungan"),
                        rs2.getString("nm_unit"),
                        rs2.getString("nik"),
                        rs2.getString("noka_bpjs"),
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),                        
                        rs2.getString("jk"),                                                
                        rs2.getString("tgl_registrasi"),
                        rs2.getString("jRawat"),
                        rs2.getString("tgllhr"),
                        rs2.getString("jkel")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount3.setText("" + tabMode2.getRowCount());
    }
    
    private void tampilIbuBayi() {
        Valid.tabelKosong(tabMode3);
        try {
            if (cmbLimit3.getSelectedItem().equals("Semuanya")) {
                ps6 = koneksi.prepareStatement("SELECT rp1.no_rawat norwt_by, DATE_FORMAT(rp1.tgl_registrasi,'%d-%m-%Y') tglmsk_by, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tglplg_by, p1.no_rkm_medis rm_by, "
                        + "p1.nm_pasien nm_bayi, CONCAT(rp1.umurdaftar,' ',rp1.sttsumur) umur_by, pb.no_rawat_ibu, p2.no_rkm_medis, p2.nm_pasien nm_ibunya, "
                        + "CONCAT(rp2.umurdaftar,' ',rp2.sttsumur) umur_ibu, IFNULL(egc.claim_number,'-') no_klaim_ibu, rp1.tgl_registrasi, IF(rp1.status_lanjut='Ralan','2','1') jRawat, "
                        + "concat(p1.tgl_lahir,' ','00:00:00') tgllhr, if(p1.jk='L','1','2') jkel FROM reg_periksa rp1 "
                        + "INNER JOIN pasien_bayi pb ON pb.no_rkm_medis=rp1.no_rkm_medis INNER JOIN pasien p1 ON p1.no_rkm_medis=pb.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki ON ki.no_rawat=pb.no_rawat_ibu LEFT JOIN reg_periksa rp2 ON rp2.no_rawat=pb.no_rawat_ibu "
                        + "LEFT JOIN pasien p2 ON p2.no_rkm_medis=rp2.no_rkm_medis LEFT JOIN eklaim_generate_claim egc ON egc.no_rawat=pb.no_rawat_ibu WHERE "
                        + "rp1.tgl_registrasi BETWEEN '"+Valid.SetTgl(tgl5.getSelectedItem() + "")+"' AND '"+Valid.SetTgl(tgl6.getSelectedItem() + "")+"' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and rp1.no_rawat like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and p1.no_rkm_medis like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and p1.nm_pasien like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and pb.no_rawat_ibu like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and p2.no_rkm_medis like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and p2.nm_pasien like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and IFNULL(egc.claim_number,'-') like ? "
                        + "ORDER BY rp1.no_rawat");
            } else {
                ps6 = koneksi.prepareStatement("SELECT rp1.no_rawat norwt_by, DATE_FORMAT(rp1.tgl_registrasi,'%d-%m-%Y') tglmsk_by, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tglplg_by, p1.no_rkm_medis rm_by, "
                        + "p1.nm_pasien nm_bayi, CONCAT(rp1.umurdaftar,' ',rp1.sttsumur) umur_by, pb.no_rawat_ibu, p2.no_rkm_medis, p2.nm_pasien nm_ibunya, "
                        + "CONCAT(rp2.umurdaftar,' ',rp2.sttsumur) umur_ibu, IFNULL(egc.claim_number,'-') no_klaim_ibu, rp1.tgl_registrasi, IF(rp1.status_lanjut='Ralan','2','1') jRawat, "
                        + "concat(p1.tgl_lahir,' ','00:00:00') tgllhr, if(p1.jk='L','1','2') jkel FROM reg_periksa rp1 "
                        + "INNER JOIN pasien_bayi pb ON pb.no_rkm_medis=rp1.no_rkm_medis INNER JOIN pasien p1 ON p1.no_rkm_medis=pb.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki ON ki.no_rawat=pb.no_rawat_ibu LEFT JOIN reg_periksa rp2 ON rp2.no_rawat=pb.no_rawat_ibu "
                        + "LEFT JOIN pasien p2 ON p2.no_rkm_medis=rp2.no_rkm_medis LEFT JOIN eklaim_generate_claim egc ON egc.no_rawat=pb.no_rawat_ibu WHERE "
                        + "rp1.tgl_registrasi BETWEEN '"+Valid.SetTgl(tgl5.getSelectedItem() + "")+"' AND '"+Valid.SetTgl(tgl6.getSelectedItem() + "")+"' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and rp1.no_rawat like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and p1.no_rkm_medis like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and p1.nm_pasien like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and pb.no_rawat_ibu like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and p2.no_rkm_medis like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and p2.nm_pasien like ? or "
                        + "rp1.tgl_registrasi BETWEEN '" + Valid.SetTgl(tgl5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl6.getSelectedItem() + "") + "' and "
                        + "rp1.umurdaftar<'28' and rp1.sttsumur in ('hr', 'bl') and rp1.status_lanjut='ranap' and ki.stts_pulang not in ('-','pindah kamar') and IFNULL(egc.claim_number,'-') like ? "
                        + "ORDER BY rp1.no_rawat limit " + cmbLimit3.getSelectedItem().toString() + "");
            }
            try {
                ps6.setString(1, "%" + TCari4.getText().trim() + "%");
                ps6.setString(2, "%" + TCari4.getText().trim() + "%");
                ps6.setString(3, "%" + TCari4.getText().trim() + "%");
                ps6.setString(4, "%" + TCari4.getText().trim() + "%");
                ps6.setString(5, "%" + TCari4.getText().trim() + "%");
                ps6.setString(6, "%" + TCari4.getText().trim() + "%");
                ps6.setString(7, "%" + TCari4.getText().trim() + "%");
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode3.addRow(new Object[]{
                        rs6.getString("norwt_by"),
                        rs6.getString("tglmsk_by"),
                        rs6.getString("tglplg_by"),
                        rs6.getString("rm_by"),
                        rs6.getString("nm_bayi"),
                        rs6.getString("umur_by"),
                        rs6.getString("no_rawat_ibu"),                        
                        rs6.getString("no_rkm_medis"),                                                
                        rs6.getString("nm_ibunya"),
                        rs6.getString("umur_ibu"),
                        rs6.getString("no_klaim_ibu"),
                        rs6.getString("tgl_registrasi"),
                        rs6.getString("jRawat"),
                        rs6.getString("tgllhr"),
                        rs6.getString("jkel")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount4.setText("" + tabMode3.getRowCount());
    }
    
    public void isCek() {
        BtnData.setEnabled(akses.getinacbg_klaim_raza());
        BtnProses.setEnabled(akses.getinacbg_klaim_raza());
    }
    
    public void emptTeks() {
        noRawat.setText("");
        nosep_klaim.setText("");
        TCari1.setText("");
        tgl.setDate(1);
        tglA.setDate(tgl);
        tglB.setDate(new Date());
        cmbJnsKlaim.setSelectedIndex(0);
        cmbJnsRawat.setSelectedIndex(0);
    }
    
    private void tampilKLAIM() {
        nilaiRWT = "";
        Valid.tabelKosong(tabMode);
        if (cmbJnsRawat.getSelectedItem().equals("INAP")) {
            nilaiRWT = "%ranap%";
        } else if (cmbJnsRawat.getSelectedItem().equals("JALAN")) {
            nilaiRWT = "%ralan%";
        } else {
            nilaiRWT = "%%";
        }
        
        try {
            if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                ps = koneksi.prepareStatement("SELECT enc.no_rawat,esc.payor_cd,enc.no_sep,if(enc.jnspelayanan='1','Inap','Jalan') Rawat,if(esc.jenis_rawat='1',eg.kelas,'') Kelas, "
                        + "if(esc.upgrade_class_ind='1',esc.upgrade_class_class,'') naik_kelas,eg.cbg_code,eg.cbg_tarif,eg.sub_acute_tarif,eg.chronic_tarif, "
                        + "(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) special_cmg_tarif, "
                        + "eg.cbg_tarif+eg.sub_acute_tarif+eg.chronic_tarif+(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) total_tarif_grouping, "
                        + "if(esc.upgrade_class_ind='1',eg.add_payment_amt,0) naik_kelas_tarif, round(esc.real_tarif,0) real_tarif,enc.klaim_final,if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') kirim_online, "
                        + "enc.nm_pasien, date_format(enc.tgl_input,'%d-%m-%Y') tglInput, enc.tglsep, p.nama nm_petugas, IFNULL(ecd.top_up_rawat,'0') trf_covid, enc.no_rm, "
                        + "IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',b.nm_bangsal)) unit, "
                        + "IFNULL(egc.payor_id,'3') payor_id FROM eklaim_new_claim enc LEFT JOIN eklaim_generate_claim egc ON egc.claim_number=enc.no_sep "
                        + "LEFT JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep LEFT JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep LEFT JOIN eklaim_online_status eos ON eos.no_sep=enc.no_sep "
                        + "LEFT JOIN inacbg_coder_nik koder ON koder.no_ik=esc.coder_nik LEFT JOIN pegawai p ON p.nik=koder.nik LEFT JOIN eklaim_covid19_data ecd ON ecd.no_sep=enc.no_sep "
                        + "LEFT JOIN reg_periksa rp on rp.no_rawat=enc.no_rawat LEFT JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                        + "left join kamar_inap ki on ki.no_rawat = rp.no_rawat left JOIN kamar k ON k.kd_kamar=ki.kd_kamar left JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_rawat like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and esc.payor_cd like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_sep like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.klaim_final like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and p.nama like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_rm like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',b.nm_bangsal)) like ? "
                        + "order by enc.tgl_input desc limit " + cmbLimit.getSelectedItem().toString() + "");

            } else {
                ps = koneksi.prepareStatement("SELECT enc.no_rawat,esc.payor_cd,enc.no_sep,if(enc.jnspelayanan='1','Inap','Jalan') Rawat,if(esc.jenis_rawat='1',eg.kelas,'') Kelas, "
                        + "if(esc.upgrade_class_ind='1',esc.upgrade_class_class,'') naik_kelas,eg.cbg_code,eg.cbg_tarif,eg.sub_acute_tarif,eg.chronic_tarif, "
                        + "(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) special_cmg_tarif, "
                        + "eg.cbg_tarif+eg.sub_acute_tarif+eg.chronic_tarif+(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) total_tarif_grouping, "
                        + "if(esc.upgrade_class_ind='1',eg.add_payment_amt,0) naik_kelas_tarif, round(esc.real_tarif,0) real_tarif,enc.klaim_final,if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') kirim_online, "
                        + "enc.nm_pasien, date_format(enc.tgl_input,'%d-%m-%Y') tglInput, enc.tglsep, p.nama nm_petugas, IFNULL(ecd.top_up_rawat,'0') trf_covid, enc.no_rm, "
                        + "IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',b.nm_bangsal)) unit, "
                        + "IFNULL(egc.payor_id,'3') payor_id FROM eklaim_new_claim enc LEFT JOIN eklaim_generate_claim egc ON egc.claim_number=enc.no_sep "
                        + "LEFT JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep LEFT JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep LEFT JOIN eklaim_online_status eos ON eos.no_sep=enc.no_sep "
                        + "LEFT JOIN inacbg_coder_nik koder ON koder.no_ik=esc.coder_nik LEFT JOIN pegawai p ON p.nik=koder.nik LEFT JOIN eklaim_covid19_data ecd ON ecd.no_sep=enc.no_sep "
                        + "LEFT JOIN reg_periksa rp on rp.no_rawat=enc.no_rawat LEFT JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                        + "left join kamar_inap ki on ki.no_rawat = rp.no_rawat left JOIN kamar k ON k.kd_kamar=ki.kd_kamar left JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_rawat like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and esc.payor_cd like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_sep like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.klaim_final like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and p.nama like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_rm like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',b.nm_bangsal)) like ? "
                        + "order by enc.tgl_input desc");
            }
            try {
                if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%"); 
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");  
                } else {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%");
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");                    
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), 
                        rs.getString("payor_cd"), 
                        rs.getString("no_sep"),
                        rs.getString("no_rm"),
                        rs.getString("nm_pasien"),
                        rs.getString("Rawat"),
                        rs.getString("unit"),
                        rs.getString("Kelas"),
                        rs.getString("naik_kelas"),
                        rs.getString("cbg_code"),
                        rs.getDouble("cbg_tarif"),
                        rs.getDouble("sub_acute_tarif"),
                        rs.getDouble("chronic_tarif"),
                        rs.getDouble("special_cmg_tarif"),
                        rs.getDouble("total_tarif_grouping"),
                        rs.getDouble("naik_kelas_tarif"),
                        rs.getDouble("trf_covid"),
                        rs.getDouble("real_tarif"),
                        rs.getString("klaim_final"),
                        rs.getString("nm_petugas"),
                        rs.getString("tglInput"),
                        rs.getString("kirim_online"),
                        rs.getString("tglsep"),
                        rs.getString("payor_id")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode.getRowCount());
    }
    
    private void getData() {
        cekData = "";
        tglSep = "";
        kd_payor = "";
        nmibu = "";
        if (tbINACBG.getSelectedRow() != -1) {
            kd_payor = tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 23).toString();
            if (kd_payor.equals("3")) {
                cmbJnsKlaim.setSelectedIndex(1);
            } else if (kd_payor.equals("71")) {
                cmbJnsKlaim.setSelectedIndex(2);
            } else if (kd_payor.equals("72")) {
                cmbJnsKlaim.setSelectedIndex(3);
            } else if (kd_payor.equals("75")) {
                cmbJnsKlaim.setSelectedIndex(4);
            } else if (kd_payor.equals("73")) {
                cmbJnsKlaim.setSelectedIndex(5);
            }

            noRawat.setText(tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 0).toString());
            nosep_klaim.setText(tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 2).toString());
            tglSep = tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 22).toString();
            jnsKlaim = tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 1).toString();
            
            if (kd_payor.equals("73")) {
                nmibu = Sequel.cariIsi("SELECT p2.nm_pasien FROM reg_periksa rp1 INNER JOIN pasien_bayi pb ON pb.no_rkm_medis = rp1.no_rkm_medis "
                        + "INNER JOIN pasien p1 ON p1.no_rkm_medis = pb.no_rkm_medis LEFT JOIN reg_periksa rp2 ON rp2.no_rawat = pb.no_rawat_ibu "
                        + "LEFT JOIN pasien p2 ON p2.no_rkm_medis = rp2.no_rkm_medis WHERE rp1.no_rawat='" + noRawat.getText() + "'");
            } else {
                nmibu = "-";
            }

            try {
                ps4 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_ktp, p.no_peserta, p.no_rkm_medis, "
                        + "p.nm_pasien, if(p.jk='L','1','2') jkel, IF(rp.status_lanjut='Ralan','2','1') jRawat, "
                        + "concat(p.tgl_lahir,' ','00:00:00') tgllhr FROM reg_periksa rp INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "WHERE rp.no_rawat='" + noRawat.getText() + "'");
                try {
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        nik = rs4.getString("no_ktp");
                        tglKunj = rs4.getString("tgl_registrasi");
                        jnsrwt = rs4.getString("jRawat");
                        norm = rs4.getString("no_rkm_medis");
                        nmpas = rs4.getString("nm_pasien");
                        tgllahir = rs4.getString("tgllhr");
                        jk = rs4.getString("jkel");
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
        }
    }
    
    public void KlaimRAZA(String norw, String noSyaratKlaim, String nmKlaim, String kodepay, String tglsep) {
        if (nmKlaim.equals("JKN")) {
            jnsKlaim = nmKlaim;
            noRawat.setText(norw);
            nosep_klaim.setText(noSyaratKlaim);
            cmbJnsKlaim.setSelectedIndex(1);
            TCari1.setText("");
            tgl.setDate(1);
            tglA.setDate(tgl);
            tglB.setDate(new Date());
            kd_payor = kodepay;
            tglSep = tglsep;
            
            //untuk jaminan kipi belum selesai
        } else if (nmKlaim.equals("JAMINAN COVID-19") || nmKlaim.equals("JAMINAN KIPI")) {            
            try {
                ps5 = koneksi.prepareStatement("SELECT p.no_ktp nik, p.no_peserta noka_bpjs, p.no_rkm_medis, p.nm_pasien, "
                        + "if(p.jk='L','Laki-laki','Perempuan') jk, rp.tgl_registrasi, IF(rp.status_lanjut='Ralan','2','1') jRawat, "
                        + "concat(p.tgl_lahir,' ','00:00:00') tgllhr, if(p.jk='L','1','2') jkel FROM reg_periksa rp "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE rp.no_rawat ='" + norw + "'");
                try {
                    rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        nik = rs5.getString("nik");
                        noka = rs5.getString("noka_bpjs");
                        tglKunj = rs5.getString("tgl_registrasi");
                        jnsrwt = rs5.getString("jRawat");
                        norm = rs5.getString("no_rkm_medis");
                        nmpas = rs5.getString("nm_pasien");
                        tgllahir = rs5.getString("tgllhr");
                        jk = rs5.getString("jkel");
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
            
            if (nmKlaim.equals("JAMINAN COVID-19")) {
                cmbJnsKlaim.setSelectedIndex(2);
            } else if (nmKlaim.equals("JAMINAN KIPI")) {
                cmbJnsKlaim.setSelectedIndex(3);
            }
            jnsKlaim = nmKlaim;
            noRawat.setText(norw);
            nosep_klaim.setText(noSyaratKlaim);            
            TCari1.setText("");            
            tgl.setDate(1);
            tglA.setDate(tgl);
            tglB.setDate(new Date());
            kd_payor = kodepay;
        }
    }
    
    private void klaimBaruJKN() {
        cekSEP = "";
        cekKlaim = 0;
        cekSEP = Sequel.cariIsi("SELECT no_sep FROM eklaim_new_claim WHERE no_sep='" + nosep_klaim.getText() + "' and tglsep='" + tglSep + "'");
        cekKlaim = Sequel.cariInteger("select count(-1) from eklaim_set_claim where no_sep='" + nosep_klaim.getText() + "' and date(tgl_masuk)='" + tglSep + "'");

        if (cekSEP.equals("")) {
            if (mbak_eka.ngirimJKN(noRawat.getText()) == true) {
                if (cekKlaim == 0) {
                    if (akses.getkode().equals("Admin Utama")) {
                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        ajukan.setLocationRelativeTo(internalFrame1);
                        ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                        ajukan.tarifRS(noRawat.getText());
                        ajukan.TabRawat.setEnabled(true);
                        ajukan.TabRawat.setSelectedIndex(0);
                        ajukan.TabDiagnosa1.setSelectedIndex(0);
                        ajukan.TabProsedur1.setSelectedIndex(0);
                        ajukan.emptTeksLAINNYA();
                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    } else {
                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        ajukan.setLocationRelativeTo(internalFrame1);
                        ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                        ajukan.tarifRS(noRawat.getText());
                        ajukan.TabRawat.setEnabledAt(1, false);
                        ajukan.TabRawat.setEnabledAt(0, true);
                        ajukan.TabRawat.setSelectedIndex(0);
                        ajukan.TabDiagnosa1.setSelectedIndex(0);
                        ajukan.TabProsedur1.setSelectedIndex(0);
                        ajukan.emptTeksLAINNYA();
                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                } else if (cekKlaim > 0) {
                    if (akses.getkode().equals("Admin Utama")) {
                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        ajukan.setLocationRelativeTo(internalFrame1);
                        ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                        ajukan.tarifRS(noRawat.getText());
                        ajukan.TabRawat.setEnabled(true);
                        ajukan.TabRawat.setSelectedIndex(0);
                        ajukan.TabDiagnosa1.setSelectedIndex(0);
                        ajukan.TabProsedur1.setSelectedIndex(0);
                        ajukan.emptTeksLAINNYA();
                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    } else {
                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        ajukan.setLocationRelativeTo(internalFrame1);
                        ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                        ajukan.tarifRS(noRawat.getText());
                        ajukan.TabRawat.setEnabledAt(1, false);
                        ajukan.TabRawat.setEnabledAt(0, true);
                        ajukan.TabRawat.setSelectedIndex(0);
                        ajukan.TabDiagnosa1.setSelectedIndex(0);
                        ajukan.TabProsedur1.setSelectedIndex(0);
                        ajukan.emptTeksLAINNYA();
                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }
            }
        } else {
            if (cekKlaim == 0) {
                if (akses.getkode().equals("Admin Utama")) {
                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    ajukan.setLocationRelativeTo(internalFrame1);
                    ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                    ajukan.tarifRS(noRawat.getText());
                    ajukan.TabRawat.setEnabled(true);
                    ajukan.TabRawat.setSelectedIndex(0);
                    ajukan.TabDiagnosa1.setSelectedIndex(0);
                    ajukan.TabProsedur1.setSelectedIndex(0);
                    ajukan.emptTeksLAINNYA();
                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                        tampilKLAIM();
                    } else {
                        tampilSEP();
                    }
                } else {
                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    ajukan.setLocationRelativeTo(internalFrame1);
                    ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                    ajukan.tarifRS(noRawat.getText());
                    ajukan.TabRawat.setEnabledAt(1, false);
                    ajukan.TabRawat.setEnabledAt(0, true);
                    ajukan.TabRawat.setSelectedIndex(0);
                    ajukan.TabDiagnosa1.setSelectedIndex(0);
                    ajukan.TabProsedur1.setSelectedIndex(0);
                    ajukan.emptTeksLAINNYA();
                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                        tampilKLAIM();
                    } else {
                        tampilSEP();
                    }
                }
            } else if (cekKlaim > 0) {
                if (akses.getkode().equals("Admin Utama")) {
                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    ajukan.setLocationRelativeTo(internalFrame1);
                    ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                    ajukan.tarifRS(noRawat.getText());
                    ajukan.TabRawat.setEnabled(true);
                    ajukan.TabRawat.setSelectedIndex(0);
                    ajukan.TabDiagnosa1.setSelectedIndex(0);
                    ajukan.TabProsedur1.setSelectedIndex(0);
                    ajukan.emptTeksLAINNYA();
                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                        tampilKLAIM();
                    } else {
                        tampilSEP();
                    }
                } else {
                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    ajukan.setLocationRelativeTo(internalFrame1);
                    ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                    ajukan.tarifRS(noRawat.getText());
                    ajukan.TabRawat.setEnabledAt(1, false);
                    ajukan.TabRawat.setEnabledAt(0, true);
                    ajukan.TabRawat.setSelectedIndex(0);
                    ajukan.TabDiagnosa1.setSelectedIndex(0);
                    ajukan.TabProsedur1.setSelectedIndex(0);
                    ajukan.emptTeksLAINNYA();
                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                        tampilKLAIM();
                    } else {
                        tampilSEP();
                    }
                }
            }
        }
    }
    
    private void klaimBaruLAINYA() {
//        PengajuanKlaimINACBGrz ajukan = new PengajuanKlaimINACBGrz(null, false);
        cekSEP = "";
        cekKlaim = 0;
        cekSEP = Sequel.cariIsi("SELECT no_rawat FROM eklaim_generate_claim WHERE no_rawat='" + noRawat.getText() + "' and payor_id='" + kd_payor + "'");
        cekKlaim = Sequel.cariInteger("select count(-1) from eklaim_set_claim where no_rawat='" + noRawat.getText() + "'");

        if (cekSEP.equals("")) {
            if (mbak_eka.ngirimLAINYA(noRawat.getText(), nik, tglKunj, jnsrwt, norm, nmpas, tgllahir, jk, kd_payor) == true) {
                if (cekKlaim == 0) {
                    if (akses.getkode().equals("Admin Utama")) {
                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        ajukan.setLocationRelativeTo(internalFrame1);
                        if (kd_payor.equals("73")) {
                            ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                        } else {
                            ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                        }
                        ajukan.tarifRS(noRawat.getText());
                        ajukan.TabRawat.setEnabled(true);
                        ajukan.TabRawat.setSelectedIndex(1);
                        ajukan.TabDiagnosa2.setSelectedIndex(0);
                        ajukan.emptTeksJKN();
                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    } else {
                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        ajukan.setLocationRelativeTo(internalFrame1);
                        if (kd_payor.equals("73")) {
                            ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                        } else {
                            ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                        }
                        ajukan.tarifRS(noRawat.getText());
                        ajukan.TabRawat.setEnabledAt(0, false);
                        ajukan.TabRawat.setEnabledAt(1, true);
                        ajukan.TabRawat.setSelectedIndex(1);
                        ajukan.TabDiagnosa2.setSelectedIndex(0);
                        ajukan.emptTeksJKN();
                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                } else if (cekKlaim > 0) {
                    if (akses.getkode().equals("Admin Utama")) {
                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        ajukan.setLocationRelativeTo(internalFrame1);
                        if (kd_payor.equals("73")) {
                            ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                        } else {
                            ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                        }
                        ajukan.tarifRS(noRawat.getText());
                        ajukan.TabRawat.setEnabled(true);
                        ajukan.TabRawat.setSelectedIndex(1);
                        ajukan.TabDiagnosa2.setSelectedIndex(0);
                        ajukan.emptTeksJKN();
                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    } else {
                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        ajukan.setLocationRelativeTo(internalFrame1);
                        if (kd_payor.equals("73")) {
                            ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                        } else {
                            ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                        }
                        ajukan.tarifRS(noRawat.getText());
                        ajukan.TabRawat.setEnabledAt(0, false);
                        ajukan.TabRawat.setEnabledAt(1, true);
                        ajukan.TabRawat.setSelectedIndex(1);
                        ajukan.TabDiagnosa2.setSelectedIndex(0);
                        ajukan.emptTeksJKN();
                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }
            }
        } else {
            if (cekKlaim == 0) {
                if (akses.getkode().equals("Admin Utama")) {
                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    ajukan.setLocationRelativeTo(internalFrame1);
                    if (kd_payor.equals("73")) {
                        ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                    } else {
                        ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                    }
                    ajukan.tarifRS(noRawat.getText());
                    ajukan.TabRawat.setEnabled(true);
                    ajukan.TabRawat.setSelectedIndex(1);
                    ajukan.TabDiagnosa2.setSelectedIndex(0);
                    ajukan.emptTeksJKN();
                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                        tampilKLAIM();
                    } else {
                        tampilSEP();
                    }
                } else {
                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    ajukan.setLocationRelativeTo(internalFrame1);
                    if (kd_payor.equals("73")) {
                        ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                    } else {
                        ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                    }
                    ajukan.tarifRS(noRawat.getText());
                    ajukan.TabRawat.setEnabledAt(0, false);
                    ajukan.TabRawat.setEnabledAt(1, true);
                    ajukan.TabRawat.setSelectedIndex(1);
                    ajukan.TabDiagnosa2.setSelectedIndex(0);
                    ajukan.emptTeksJKN();
                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                        tampilKLAIM();
                    } else {
                        tampilSEP();
                    }
                }
            } else if (cekKlaim > 0) {
                if (akses.getkode().equals("Admin Utama")) {
                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    ajukan.setLocationRelativeTo(internalFrame1);
                    if (kd_payor.equals("73")) {
                        ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                    } else {
                        ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                    }
                    ajukan.tarifRS(noRawat.getText());
                    ajukan.TabRawat.setEnabled(true);
                    ajukan.TabRawat.setSelectedIndex(1);
                    ajukan.TabDiagnosa2.setSelectedIndex(0);
                    ajukan.emptTeksJKN();
                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                        tampilKLAIM();
                    } else {
                        tampilSEP();
                    }
                } else {
                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    ajukan.setLocationRelativeTo(internalFrame1);
                    if (kd_payor.equals("73")) {
                        ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                    } else {
                        ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                    }
                    ajukan.tarifRS(noRawat.getText());
                    ajukan.TabRawat.setEnabledAt(0, false);
                    ajukan.TabRawat.setEnabledAt(1, true);
                    ajukan.TabRawat.setSelectedIndex(1);
                    ajukan.TabDiagnosa2.setSelectedIndex(0);
                    ajukan.emptTeksJKN();
                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                        tampilKLAIM();
                    } else {
                        tampilSEP();
                    }
                }
            }
        }
    }
    
    private void klaimLamaJKN() {
        cekKlaim = 0;
        cekKlaim = Sequel.cariInteger("select count(-1) from eklaim_set_claim where no_sep='" + nosep_klaim.getText() + "' and date(tgl_masuk)='" + tglSep + "'");

        if (cekKlaim == 0) {
            if (akses.getkode().equals("Admin Utama")) {
                ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                ajukan.setLocationRelativeTo(internalFrame1);
                ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                ajukan.tarifRS(noRawat.getText());
                ajukan.TabRawat.setEnabled(true);
                ajukan.TabRawat.setSelectedIndex(0);
                ajukan.TabDiagnosa1.setSelectedIndex(0);
                ajukan.TabProsedur1.setSelectedIndex(0);
                ajukan.emptTeksLAINNYA();
                ajukan.setVisible(true);
            } else {
                ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                ajukan.setLocationRelativeTo(internalFrame1);
                ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-",tglSep);
                ajukan.tarifRS(noRawat.getText());
                ajukan.TabRawat.setEnabledAt(1, false);
                ajukan.TabRawat.setEnabledAt(0, true);
                ajukan.TabRawat.setSelectedIndex(0);
                ajukan.TabDiagnosa1.setSelectedIndex(0);
                ajukan.TabProsedur1.setSelectedIndex(0);
                ajukan.emptTeksLAINNYA();
                ajukan.setVisible(true);
            }
        } else if (cekKlaim > 0) {
            if (akses.getkode().equals("Admin Utama")) {
                ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                ajukan.setLocationRelativeTo(internalFrame1);
                ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                ajukan.tarifRS(noRawat.getText());
                ajukan.TabRawat.setEnabled(true);
                ajukan.TabRawat.setSelectedIndex(0);
                ajukan.TabDiagnosa1.setSelectedIndex(0);
                ajukan.TabProsedur1.setSelectedIndex(0);
                ajukan.emptTeksLAINNYA();
                ajukan.setVisible(true);
            } else {
                ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                ajukan.setLocationRelativeTo(internalFrame1);
                ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
                ajukan.tarifRS(noRawat.getText());
                ajukan.TabRawat.setEnabledAt(1, false);
                ajukan.TabRawat.setEnabledAt(0, true);
                ajukan.TabRawat.setSelectedIndex(0);
                ajukan.TabDiagnosa1.setSelectedIndex(0);
                ajukan.TabProsedur1.setSelectedIndex(0);
                ajukan.emptTeksLAINNYA();
                ajukan.setVisible(true);
            }
        }
    }

    private void klaimLamaLAINYA() {
        cekKlaim = 0;
        cekKlaim = Sequel.cariInteger("select count(-1) from eklaim_set_claim where no_sep='" + nosep_klaim.getText() + "'");

        if (cekKlaim == 0) {
            if (akses.getkode().equals("Admin Utama")) {
                ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                ajukan.setLocationRelativeTo(internalFrame1);
                if (kd_payor.equals("73")) {
                    ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                } else {
                    ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                }
                ajukan.tarifRS(noRawat.getText());
                ajukan.TabRawat.setEnabled(true);
                ajukan.TabRawat.setSelectedIndex(1);
                ajukan.TabDiagnosa2.setSelectedIndex(0);
                ajukan.emptTeksJKN();
                ajukan.setVisible(true);
            } else {
                ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                ajukan.setLocationRelativeTo(internalFrame1);
                if (kd_payor.equals("73")) {
                    ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                } else {
                    ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                }
                ajukan.tarifRS(noRawat.getText());
                ajukan.TabRawat.setEnabledAt(0, false);
                ajukan.TabRawat.setEnabledAt(1, true);
                ajukan.TabRawat.setSelectedIndex(1);
                ajukan.TabDiagnosa2.setSelectedIndex(0);
                ajukan.emptTeksJKN();
                ajukan.setVisible(true);
            }
        } else if (cekKlaim > 0) {
            if (akses.getkode().equals("Admin Utama")) {
                ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                ajukan.setLocationRelativeTo(internalFrame1);
                if (kd_payor.equals("73")) {
                    ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                } else {
                    ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-",tglKunj);
                }
                ajukan.tarifRS(noRawat.getText());
                ajukan.TabRawat.setEnabled(true);
                ajukan.TabRawat.setSelectedIndex(1);
                ajukan.TabDiagnosa2.setSelectedIndex(0);
                ajukan.emptTeksJKN();
                ajukan.setVisible(true);
            } else {
                ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                ajukan.setLocationRelativeTo(internalFrame1);
                if (kd_payor.equals("73")) {
                    ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, nmibu, tglKunj);
                } else {
                    ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, "-", tglKunj);
                }
                ajukan.tarifRS(noRawat.getText());
                ajukan.TabRawat.setEnabledAt(0, false);
                ajukan.TabRawat.setEnabledAt(1, true);
                ajukan.TabRawat.setSelectedIndex(1);
                ajukan.TabDiagnosa2.setSelectedIndex(0);
                ajukan.emptTeksJKN();
                ajukan.setVisible(true);
            }
        }
    }
    
    private void setData(String datanya) {
        if (datanya.equals("dari luar")) {
            BtnProses.requestFocus();
        } else if (datanya.equals("didalam")) {
            noRawat.setText("");
            nosep_klaim.setText("");
            jnsKlaim = "";
        }
    }
    
    public void verifData() {
        cekData = "dari luar";
    }
}
