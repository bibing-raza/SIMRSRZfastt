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
package inventory;
import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public class DlgCatatanResep extends javax.swing.JDialog {
    private final DefaultTableModel tabMode2, tabModeResepObat, tabModeFarmasi, tabModeTglBeriObat, tabModeRiwItemObat, tabModeResep2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps1, ps2, ps3, psFar, psTglBO, psRiwIO, psR2, psrestor;
    private ResultSet rs1, rs2, rs3, rsFar, rsTglBO, rsRiwIO, rsR2, rsrestor;
    private int i = 0, x = 0, j = 0, cekResep = 0;
    private String tglPemberianObat = "", resepDipilih = "", tglResep = "", kodepoli = "", status = "",
            jnsKunjungan = "", jamberiobat = "", user = "", riwayatData = "", jenisResep = "";

    /**
     * Creates new form DlgPemberianObat
     *
     * @param parent
     * @param modal
     */
    public DlgCatatanResep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        tabModeResepObat = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "Tgl.Input", "Jam Input", "Nama Obat", "Status", "Nama Dokter", "Id", "kddokter", "Jns. Resep"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbResepObat.setModel(tabModeResepObat);
        tbResepObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbResepObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(240);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(260);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            }
        }
        tbResepObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeFarmasi = new DefaultTableModel(null, new Object[]{
            "Nama Obat/Alkes", "Satuan", "Stok Apotek IGD (UMUM)", "Stok Apotek Sentral (BPJS)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObat.setModel(tabModeFarmasi);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(350);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeTglBeriObat = new DefaultTableModel(null, new Object[]{
            "tgl", "Tanggal", "Jam", "Jlh. Item", "Poli/Inst./Rg. Rawat", "Dokter Peresep"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTglBeriObat.setModel(tabModeTglBeriObat);
        tbTglBeriObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTglBeriObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbTglBeriObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);                
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(56);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } 
        }
        tbTglBeriObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwItemObat = new DefaultTableModel(null, new Object[]{
            "No.","Tanggal","Nama Obat/Alkes/BHP", "Jumlah"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemObat.setModel(tabModeRiwItemObat);
        tbItemObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbItemObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);            
            } else if (i == 1) {
                column.setPreferredWidth(64);            
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(56);
            } 
        }
        tbItemObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResep2 = new DefaultTableModel(null, new Object[]{
            "Cek", "no_rawat", "Tgl. Resep", "jam_input", "Nama Item Obat", "status", "nm_dokter", "id", "Jns. Resep"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbItemResep.setModel(tabModeResep2);
        tbItemResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbItemResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbItemResep.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Resep", "Jam Resep",
            "Tgl. Eksekusi", "Status Data", "noId", "Nama Obat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode2);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 10; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(220);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(140);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(350);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSemuanya = new javax.swing.JMenuItem();
        MnDibatalkan = new javax.swing.JMenuItem();
        MnDiCopy = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnRiwayatData = new javax.swing.JMenuItem();
        WindowRiwayat = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel30 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel33 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        BtnAll1 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn10 = new widget.Button();
        Scroll6 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        noIdObat = new widget.TextBox();
        TIdObat = new widget.TextBox();
        Scroll35 = new widget.ScrollPane();
        tbItemResep = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel54 = new widget.Label();
        DTPCariA = new widget.Tanggal();
        jLabel55 = new widget.Label();
        DTPCariB = new widget.Tanggal();
        jLabel56 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCariObat = new widget.Button();
        jLabel57 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tcara_byr = new widget.TextBox();
        jLabel11 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel12 = new widget.Label();
        cmbJnsResep = new widget.ComboBox();
        BtnSetuju = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelGlass16 = new widget.panelisi();
        jLabel53 = new widget.Label();
        TResepObat = new widget.TextBox();
        ChkCito = new widget.CekBox();
        BtnCopyResepTerakhir = new widget.Button();
        Scroll4 = new widget.ScrollPane();
        tbResepObat = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput1 = new widget.CekBox();
        PanelRiwayatObat = new javax.swing.JPanel();
        panelGlass18 = new widget.panelisi();
        Scroll44 = new widget.ScrollPane();
        tbTglBeriObat = new widget.Table();
        panelGlass17 = new widget.panelisi();
        ChkPoli1 = new widget.CekBox();
        Scroll45 = new widget.ScrollPane();
        tbItemObat = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        jLabel64 = new widget.Label();
        TCariObat = new widget.TextBox();
        BtnCari1 = new widget.Button();
        Scroll33 = new widget.ScrollPane();
        tbObat = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSemuanya.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuanya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuanya.setText("Conteng Semua Item");
        MnSemuanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuanya.setIconTextGap(5);
        MnSemuanya.setName("MnSemuanya"); // NOI18N
        MnSemuanya.setPreferredSize(new java.awt.Dimension(185, 26));
        MnSemuanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuanyaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSemuanya);

        MnDibatalkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibatalkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDibatalkan.setText("Hilangkan Semua Conteng");
        MnDibatalkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibatalkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDibatalkan.setIconTextGap(5);
        MnDibatalkan.setName("MnDibatalkan"); // NOI18N
        MnDibatalkan.setPreferredSize(new java.awt.Dimension(185, 26));
        MnDibatalkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibatalkanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDibatalkan);

        MnDiCopy.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        MnDiCopy.setText("Resep Di Copy");
        MnDiCopy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiCopy.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiCopy.setIconTextGap(5);
        MnDiCopy.setName("MnDiCopy"); // NOI18N
        MnDiCopy.setPreferredSize(new java.awt.Dimension(185, 26));
        MnDiCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiCopyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDiCopy);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnRiwayatData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData.setText("Riwayat Data Terhapus");
        MnRiwayatData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData.setIconTextGap(5);
        MnRiwayatData.setName("MnRiwayatData"); // NOI18N
        MnRiwayatData.setPreferredSize(new java.awt.Dimension(185, 26));
        MnRiwayatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatDataActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnRiwayatData);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Catatan Resep Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        internalFrame18.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame17.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanggal :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel30);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-05-2024" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("s.d.");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel31);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-05-2024" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Key Word :");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel32);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+1");
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
        internalFrame17.add(BtnCari2);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Record :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame17.add(jLabel33);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame17.add(LCount1);

        internalFrame18.add(internalFrame17, java.awt.BorderLayout.CENTER);

        internalFrame19.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

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
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnAll1);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor.setMnemonic('U');
        BtnRestor.setText("Restore");
        BtnRestor.setToolTipText("Alt+U");
        BtnRestor.setName("BtnRestor"); // NOI18N
        BtnRestor.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnRestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestorActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnRestor);

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setMnemonic('U');
        BtnCloseIn10.setText("Tutup");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn10);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        Scroll6.setViewportView(tbRiwayat);

        internalFrame13.add(Scroll6, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        noIdObat.setForeground(new java.awt.Color(0, 0, 0));
        noIdObat.setHighlighter(null);
        noIdObat.setName("noIdObat"); // NOI18N

        TIdObat.setEnabled(false);
        TIdObat.setHighlighter(null);
        TIdObat.setName("TIdObat"); // NOI18N
        TIdObat.setPreferredSize(new java.awt.Dimension(1, 1));

        Scroll35.setName("Scroll35"); // NOI18N
        Scroll35.setOpaque(true);

        tbItemResep.setToolTipText("Silahkan conteng item resep obat yg. dipilih / gunakan fitur conteng..!!");
        tbItemResep.setName("tbItemResep"); // NOI18N
        tbItemResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemResepMouseClicked(evt);
            }
        });
        tbItemResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemResepKeyPressed(evt);
            }
        });
        Scroll35.setViewportView(tbItemResep);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian / Catatan Resep Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setComponentPopupMenu(jPopupMenu2);
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setComponentPopupMenu(jPopupMenu2);
        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 105));
        panelGlass13.setLayout(null);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Tgl. Resep : ");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel54);
        jLabel54.setBounds(0, 66, 105, 23);

        DTPCariA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-05-2024" }));
        DTPCariA.setDisplayFormat("dd-MM-yyyy");
        DTPCariA.setName("DTPCariA"); // NOI18N
        DTPCariA.setOpaque(false);
        DTPCariA.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass13.add(DTPCariA);
        DTPCariA.setBounds(105, 66, 90, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("s.d.");
        jLabel55.setName("jLabel55"); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass13.add(jLabel55);
        jLabel55.setBounds(200, 66, 23, 23);

        DTPCariB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-05-2024" }));
        DTPCariB.setDisplayFormat("dd-MM-yyyy");
        DTPCariB.setName("DTPCariB"); // NOI18N
        DTPCariB.setOpaque(false);
        DTPCariB.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass13.add(DTPCariB);
        DTPCariB.setBounds(230, 66, 90, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Key Word : ");
        jLabel56.setName("jLabel56"); // NOI18N
        jLabel56.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(jLabel56);
        jLabel56.setBounds(320, 66, 80, 23);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass13.add(TCari);
        TCari.setBounds(404, 66, 270, 23);

        BtnCariObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariObat.setMnemonic('3');
        BtnCariObat.setText("Tampilkan Data");
        BtnCariObat.setToolTipText("Alt+3");
        BtnCariObat.setName("BtnCariObat"); // NOI18N
        BtnCariObat.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCariObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariObatActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCariObat);
        BtnCariObat.setBounds(680, 66, 130, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Pasien : ");
        jLabel57.setName("jLabel57"); // NOI18N
        jLabel57.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel57);
        jLabel57.setBounds(6, 10, 97, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass13.add(TNoRw);
        TNoRw.setBounds(105, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass13.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass13.add(TPasien);
        TPasien.setBounds(315, 10, 360, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Lahir : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass13.add(jLabel5);
        jLabel5.setBounds(0, 38, 105, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        panelGlass13.add(TtglLahir);
        TtglLahir.setBounds(105, 38, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jenis Kelamin : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass13.add(jLabel9);
        jLabel9.setBounds(204, 38, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        panelGlass13.add(Tjk);
        Tjk.setBounds(297, 38, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Cara Bayar : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass13.add(jLabel10);
        jLabel10.setBounds(387, 38, 76, 23);

        Tcara_byr.setEditable(false);
        Tcara_byr.setForeground(new java.awt.Color(0, 0, 0));
        Tcara_byr.setName("Tcara_byr"); // NOI18N
        panelGlass13.add(Tcara_byr);
        Tcara_byr.setBounds(465, 38, 210, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Record :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel11);
        jLabel11.setBounds(820, 66, 65, 23);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass13.add(LCount);
        LCount.setBounds(889, 66, 50, 23);

        internalFrame1.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

        panelGlass8.setComponentPopupMenu(jPopupMenu2);
        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(100, 56));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass8.add(BtnSimpan);

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
        panelGlass8.add(BtnHapus);

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
        panelGlass8.add(BtnEdit);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak Resep");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(125, 30));
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
        panelGlass8.add(BtnPrint);

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad.setMnemonic('N');
        BtnNotepad.setText("Notepad");
        BtnNotepad.setToolTipText("Alt+N");
        BtnNotepad.setName("BtnNotepad"); // NOI18N
        BtnNotepad.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNotepad);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar.setMnemonic('T');
        BtnKeluar.setText("Tutup");
        BtnKeluar.setToolTipText("Alt+T");
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
        panelGlass8.add(BtnKeluar);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Ganti Semua Resep Menjadi :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(160, 23));
        panelGlass8.add(jLabel12);

        cmbJnsResep.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsResep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "CITO", "BIASA" }));
        cmbJnsResep.setName("cmbJnsResep"); // NOI18N
        cmbJnsResep.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(cmbJnsResep);

        BtnSetuju.setForeground(new java.awt.Color(0, 0, 0));
        BtnSetuju.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked.png"))); // NOI18N
        BtnSetuju.setMnemonic('U');
        BtnSetuju.setText("Setuju");
        BtnSetuju.setToolTipText("Alt+U");
        BtnSetuju.setGlassColor(new java.awt.Color(0, 153, 0));
        BtnSetuju.setName("BtnSetuju"); // NOI18N
        BtnSetuju.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnSetuju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSetujuActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnSetuju);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Item Obat/Resep yang diberikan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setComponentPopupMenu(jPopupMenu2);
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(660, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setComponentPopupMenu(jPopupMenu2);
        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 40));
        panelGlass16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Nama Obat :");
        jLabel53.setName("jLabel53"); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass16.add(jLabel53);

        TResepObat.setForeground(new java.awt.Color(0, 0, 0));
        TResepObat.setName("TResepObat"); // NOI18N
        TResepObat.setPreferredSize(new java.awt.Dimension(420, 24));
        TResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TResepObatKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TResepObatKeyTyped(evt);
            }
        });
        panelGlass16.add(TResepObat);

        ChkCito.setBackground(new java.awt.Color(255, 255, 250));
        ChkCito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCito.setForeground(new java.awt.Color(0, 0, 0));
        ChkCito.setText("Resep CITO");
        ChkCito.setBorderPainted(true);
        ChkCito.setBorderPaintedFlat(true);
        ChkCito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCito.setName("ChkCito"); // NOI18N
        ChkCito.setOpaque(false);
        ChkCito.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass16.add(ChkCito);

        BtnCopyResepTerakhir.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopyResepTerakhir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopyResepTerakhir.setMnemonic('P');
        BtnCopyResepTerakhir.setText("Copy Resep Terakhir");
        BtnCopyResepTerakhir.setToolTipText("");
        BtnCopyResepTerakhir.setName("BtnCopyResepTerakhir"); // NOI18N
        BtnCopyResepTerakhir.setPreferredSize(new java.awt.Dimension(170, 23));
        BtnCopyResepTerakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepTerakhirActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnCopyResepTerakhir);

        jPanel4.add(panelGlass16, java.awt.BorderLayout.PAGE_START);

        Scroll4.setComponentPopupMenu(jPopupMenu2);
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbResepObat.setAutoCreateRowSorter(true);
        tbResepObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbResepObat.setComponentPopupMenu(jPopupMenu1);
        tbResepObat.setName("tbResepObat"); // NOI18N
        tbResepObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResepObatMouseClicked(evt);
            }
        });
        tbResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbResepObatKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbResepObat);

        jPanel4.add(Scroll4, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(816, 260));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput1.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setText(".: Riwayat Obat Farmasi");
        ChkInput1.setToolTipText("");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setComponentPopupMenu(jPopupMenu2);
        ChkInput1.setFocusable(false);
        ChkInput1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObat.setName("PanelRiwayatObat"); // NOI18N
        PanelRiwayatObat.setOpaque(false);
        PanelRiwayatObat.setPreferredSize(new java.awt.Dimension(816, 150));
        PanelRiwayatObat.setLayout(new javax.swing.BoxLayout(PanelRiwayatObat, javax.swing.BoxLayout.LINE_AXIS));

        panelGlass18.setName("panelGlass18"); // NOI18N
        panelGlass18.setPreferredSize(new java.awt.Dimension(340, 40));
        panelGlass18.setLayout(new java.awt.BorderLayout());

        Scroll44.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Tgl. Pemberian Obat ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll44.setComponentPopupMenu(jPopupMenu2);
        Scroll44.setName("Scroll44"); // NOI18N
        Scroll44.setOpaque(true);
        Scroll44.setPreferredSize(new java.awt.Dimension(150, 130));

        tbTglBeriObat.setAutoCreateRowSorter(true);
        tbTglBeriObat.setToolTipText("Silahkan klik salah satu tgl. utk melihat obatnya");
        tbTglBeriObat.setName("tbTglBeriObat"); // NOI18N
        tbTglBeriObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTglBeriObatMouseClicked(evt);
            }
        });
        tbTglBeriObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTglBeriObatKeyPressed(evt);
            }
        });
        Scroll44.setViewportView(tbTglBeriObat);

        panelGlass18.add(Scroll44, java.awt.BorderLayout.CENTER);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(44, 33));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));

        ChkPoli1.setBorder(null);
        ChkPoli1.setForeground(new java.awt.Color(0, 0, 0));
        ChkPoli1.setSelected(true);
        ChkPoli1.setText("Hanya dipoli/inst. ini");
        ChkPoli1.setBorderPainted(true);
        ChkPoli1.setBorderPaintedFlat(true);
        ChkPoli1.setComponentPopupMenu(jPopupMenu2);
        ChkPoli1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkPoli1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPoli1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPoli1.setName("ChkPoli1"); // NOI18N
        ChkPoli1.setOpaque(false);
        ChkPoli1.setPreferredSize(new java.awt.Dimension(400, 23));
        ChkPoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPoli1ActionPerformed(evt);
            }
        });
        panelGlass17.add(ChkPoli1);

        panelGlass18.add(panelGlass17, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObat.add(panelGlass18);

        Scroll45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Item Obat Sesuai Yang Diresepkan ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll45.setComponentPopupMenu(jPopupMenu2);
        Scroll45.setName("Scroll45"); // NOI18N
        Scroll45.setOpaque(true);
        Scroll45.setPreferredSize(new java.awt.Dimension(450, 423));

        tbItemObat.setAutoCreateRowSorter(true);
        tbItemObat.setToolTipText("Silahkan klik untuk memilih data obatnya");
        tbItemObat.setName("tbItemObat"); // NOI18N
        tbItemObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemObatMouseClicked(evt);
            }
        });
        tbItemObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemObatKeyPressed(evt);
            }
        });
        Scroll45.setViewportView(tbItemObat);

        PanelRiwayatObat.add(Scroll45);

        PanelInput1.add(PanelRiwayatObat, java.awt.BorderLayout.CENTER);

        jPanel4.add(PanelInput1, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel4);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Daftar Obat/Alkes Farmasi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setComponentPopupMenu(jPopupMenu2);
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setBorder(null);
        panelisi4.setComponentPopupMenu(jPopupMenu2);
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 40));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Cari Obat/Alkes : ");
        jLabel64.setName("jLabel64"); // NOI18N
        jLabel64.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi4.add(jLabel64);

        TCariObat.setForeground(new java.awt.Color(0, 0, 0));
        TCariObat.setName("TCariObat"); // NOI18N
        TCariObat.setPreferredSize(new java.awt.Dimension(180, 23));
        TCariObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariObatKeyPressed(evt);
            }
        });
        panelisi4.add(TCariObat);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari1.setMnemonic('C');
        BtnCari1.setText("Cek");
        BtnCari1.setToolTipText("Alt+C");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(65, 30));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCari1);

        jPanel2.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        Scroll33.setComponentPopupMenu(jPopupMenu2);
        Scroll33.setName("Scroll33"); // NOI18N
        Scroll33.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll33.setViewportView(tbObat);

        jPanel2.add(Scroll33, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya dulu...!!!!");
        } else if (TResepObat.getText().trim().equals("")) {
            Valid.textKosong(TResepObat, "nama obat");
            TResepObat.requestFocus();
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...!!!!");
        } else {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("ralan")) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                        Sequel.cariIsi("select year(now())"), 6, noIdObat);
                try {
                    Sequel.menyimpan("catatan_resep", "?,?,?,?,?,?,?", "Data", 7, new String[]{
                        noIdObat.getText(), TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                        TResepObat.getText(), "BELUM", akses.getkode()
                    });

                    Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                    ChkCito.setEnabled(false);
                    cmbJnsResep.setEnabled(false);
                    BtnSetuju.setEnabled(false);
                    TResepObat.setText("");
                    TResepObat.requestFocus();
                    tampilResepObat();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan, Hubungi Admin ! " + e);
                    ChkCito.setEnabled(false);
                    cmbJnsResep.setEnabled(false);
                    BtnSetuju.setEnabled(false);
                }
            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                jenisResep = "";
                if (ChkCito.isSelected() == true) {
                    jenisResep = "CITO";
                } else {
                    jenisResep = "BIASA";
                }
                
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap where "
                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                        Sequel.cariIsi("select year(now())"), 6, noIdObat);
                try {
                    Sequel.menyimpan("catatan_resep_ranap", "?,?,?,?,?,?,?,?", "Data", 8, new String[]{
                        noIdObat.getText(), TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                        TResepObat.getText(), "BELUM", akses.getkode(), jenisResep
                    });

                    ChkCito.setEnabled(true);
                    cmbJnsResep.setEnabled(true);
                    BtnSetuju.setEnabled(true);
                    TResepObat.setText("");
                    TResepObat.requestFocus();
                    tampilResepObat();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan, Hubungi Admin ! " + e);
                    ChkCito.setEnabled(true);
                    cmbJnsResep.setEnabled(true);
                    BtnSetuju.setEnabled(true);
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowRiwayat.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariObatActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariObatActionPerformed
        tampilResepObat();
        ChkInput1.setSelected(false);
        isFormRiwayatObat();
    }//GEN-LAST:event_BtnCariObatActionPerformed

    private void TResepObatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TResepObatKeyTyped

    private void TResepObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_TResepObatKeyPressed

    private void tbResepObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResepObatMouseClicked
        if (tabModeResepObat.getRowCount() != 0) {
            try {
                getDataCatatanResep();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbResepObatMouseClicked

    private void tbResepObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResepObatKeyPressed
        if (tabModeResepObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCatatanResep();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbResepObatKeyPressed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        isFormRiwayatObat();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void tbTglBeriObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTglBeriObatMouseClicked
        if (tabModeTglBeriObat.getRowCount() != 0) {
            try {
                getDataRiwObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTglBeriObatMouseClicked

    private void tbTglBeriObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTglBeriObatKeyPressed
        if (tabModeTglBeriObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwObat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTglBeriObatKeyPressed

    private void ChkPoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPoli1ActionPerformed
        Valid.tabelKosong(tabModeRiwItemObat);
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
            if (ChkPoli1.isSelected() == true) {
                ChkPoli1.setText("Hanya dipoli/inst. ini untuk 5 kunjungan terakhir");
                tampilTglBeriObat();
            } else if (ChkPoli1.isSelected() == false) {
                ChkPoli1.setText("Semua Poli/Inst. untuk 5 kunjungan terakhir");
                tampilTglBeriObat();
            }
        } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
            if (ChkPoli1.isSelected() == true) {
                ChkPoli1.setText("Semua resep ditampilkan selama perawatan saat ini");
                tampilTglBeriObat();
            } else if (ChkPoli1.isSelected() == false) {
                ChkPoli1.setText("Semua resep ditampilkan selama perawatan saat ini");
                tampilTglBeriObat();
            }
        }
    }//GEN-LAST:event_ChkPoli1ActionPerformed

    private void tbItemObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemObatMouseClicked
        if (tabModeRiwItemObat.getRowCount() != 0) {
            try {
                getDataItemObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemObatMouseClicked

    private void tbItemObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemObatKeyPressed
        if (tabModeRiwItemObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataItemObat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemObatKeyPressed

    private void TCariObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        }
    }//GEN-LAST:event_TCariObatKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if (TCariObat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Obat/Alkes yang akan dicari harus diisi dulu...!!!");
            TCariObat.requestFocus();
        } else {
            tampilFarmasi();
            tbObat.requestFocus();
        }
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabModeFarmasi.getRowCount() != 0) {
            try {
                getDataFarmasi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabModeFarmasi.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFarmasi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyPressed

    private void MnSemuanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuanyaActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...!!!!");
            tbResepObat.requestFocus();
        } else {
            tampilResepObat();
            contengResep();
        }
    }//GEN-LAST:event_MnSemuanyaActionPerformed

    private void MnDibatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDibatalkanActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...!!!!");
            tbResepObat.requestFocus();
        } else {
            tampilResepObat();

            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                    tbResepObat.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnDibatalkanActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh menghapus resepnya...!!!!");
        } else if (!TPasien.getText().trim().equals("")) {
            riwayatData = "";
            riwayatData = "hapus";
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya..!!!!");
                    tbResepObat.requestFocus();
                } else {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")
                                && (tbResepObat.getValueAt(i, 5).toString().equals("SUDAH") || tbResepObat.getValueAt(i, 5).toString().equals("DILUAR"))) {
                            JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep yang sudah diverifikasi apotek tdk. bisa dihapus,     \n"
                                    + "Silakan input lagi sbg. resep baru/lanjutan...!!!!");
                        } else {
                            simpanHistoriResepRalan();
                        }
                    }

                    ChkCito.setEnabled(false);
                    cmbJnsResep.setEnabled(false);
                    BtnSetuju.setEnabled(false);
                    TResepObat.setText("");
                    tampilResepObat();
                }

            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya..!!!!");
                    tbResepObat.requestFocus();
                } else {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")
                                && (tbResepObat.getValueAt(i, 5).toString().equals("SUDAH") || tbResepObat.getValueAt(i, 5).toString().equals("DILUAR"))) {
                            JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep yang sudah diverifikasi apotek tdk. bisa dihapus,     \n"
                                    + "Silakan input lagi sbg. resep baru/lanjutan...!!!!");
                        } else {
                            simpanHistoriResepRanap();
                        }
                    }

                    ChkCito.setEnabled(true);
                    cmbJnsResep.setEnabled(true);
                    BtnSetuju.setEnabled(true);
                    TResepObat.setText("");
                    tampilResepObat();
                }
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (tbResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..!!!!");
        } else if (TResepObat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silakan pilih dulu salah satu datanya pada tabel..!!");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh merubah resepnya...!!!!");
        } else {            
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("ralan")) {
                if (tbResepObat.getSelectedRow() > -1) {
                    if (tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 5).toString().equals("SUDAH")
                            || tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 5).toString().equals("DILUAR")) {
                        JOptionPane.showMessageDialog(null, "Untuk resep yang sudah diverifikasi apotek tdk. bisa diperbaiki,     \n"
                                + "Silakan klik tombol simpan sbg. resep baru/lanjutan...!!!!");
                    } else {
                        Sequel.mengedit("catatan_resep", "noId='" + TIdObat.getText() + "'",
                                "no_rawat='" + TNoRw.getText() + "',tgl_perawatan='" + Sequel.cariIsi("SELECT date(NOW())") + "',"
                                + "jam_perawatan='" + Sequel.cariIsi("SELECT TIME(NOW())") + "',nama_obat = '" + TResepObat.getText() + "'");
                        ChkCito.setEnabled(false);
                        cmbJnsResep.setEnabled(false);
                        BtnSetuju.setEnabled(false);
                        TResepObat.setText("");
                        tampilResepObat();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                    TCari.requestFocus();
                    ChkCito.setEnabled(false);
                    cmbJnsResep.setEnabled(false);
                    BtnSetuju.setEnabled(false);
                }
            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                if (tbResepObat.getSelectedRow() > -1) {
                    if (tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 5).toString().equals("SUDAH")
                            || tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 5).toString().equals("DILUAR")) {
                        JOptionPane.showMessageDialog(null, "Untuk resep yang sudah diverifikasi apotek tdk. bisa diperbaiki,     \n"
                                + "Silakan klik tombol simpan sbg. resep baru/lanjutan...!!!!");
                    } else {
                        jenisResep = "";
                        if (ChkCito.isSelected() == true) {
                            jenisResep = "CITO";
                        } else {
                            jenisResep = "BIASA";
                        }
                        Sequel.mengedit("catatan_resep_ranap", "noId='" + TIdObat.getText() + "'",
                                "no_rawat='" + TNoRw.getText() + "',tgl_perawatan='" + Sequel.cariIsi("SELECT date(NOW())") + "',"
                                + "jam_perawatan='" + Sequel.cariIsi("SELECT TIME(NOW())") + "',nama_obat = '" + TResepObat.getText() + "',"
                                + "jenis_resep='" + jenisResep + "'");
                        
                        ChkCito.setEnabled(true);
                        cmbJnsResep.setEnabled(true);
                        BtnSetuju.setEnabled(true);
                        TResepObat.setText("");
                        tampilResepObat();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                    TCari.requestFocus();
                    ChkCito.setEnabled(true);
                    cmbJnsResep.setEnabled(true);
                    BtnSetuju.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Resep obat untuk pasien tersebut belum ada ditabel...!!!!");
            BtnCariObat.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No. rawat pasien tidak ditemukan...!!!!");
            tbResepObat.requestFocus();
        } else {
            //cek contengnya
            x = 0;
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Item resep yg. akan dicetak masih belum diconteng...!!");
                contengResep();
                BtnPrint.requestFocus();
            } else {
                resepDipilih = "";
                jenisResep = "";
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        if (resepDipilih.equals("")) {
                            resepDipilih = "'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                        } else {
                            resepDipilih = resepDipilih + ",'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                        }
                        jenisResep = "(RESEP : " + tbResepObat.getValueAt(i, 9).toString() + ")";
                    }
                }

                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                        || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                    if (Sequel.cariInteger("SELECT COUNT(-1) FROM catatan_resep WHERE no_rawat ='" + TNoRw.getText() + "' AND noId IN (" + resepDipilih + ")") == 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, hanya resep utk. hari ini yg. bisa dicetak, krn. pasien sdh. dilayani/diresepkan obatnya...!!!");
                        tbResepObat.requestFocus();
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
                        Valid.MyReport("rptResepRalan.jasper", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                                " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, c.nama_obat, "
                                + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp,'-') noHP from catatan_resep c "
                                + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                                + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                                + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                                + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where c.no_rawat ='" + TNoRw.getText() + "' and "
                                + "c.noId in (" + resepDipilih + ") order by c.noId", param);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                    if (Sequel.cariInteger("SELECT COUNT(-1) FROM catatan_resep_ranap WHERE no_rawat ='" + TNoRw.getText() + "' AND noId IN (" + resepDipilih + ")") == 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, hanya resep utk. hari ini yg. bisa dicetak, krn. pasien sdh. dilayani/diresepkan obatnya...!!!");
                        tbResepObat.requestFocus();
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
                        param.put("ruangan", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1") + " " + jenisResep);
                        Valid.MyReport("rptResepRanap.jasper", "report", "::[ Resep Dokter Rawat Inap ]::",
                                "SELECT c.no_rawat, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan, '%d/%m/%Y')) tgl_resep, "
                                + "c.nama_obat, r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp, '-') noHP "
                                + "FROM catatan_resep_ranap c INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                                + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis = r.no_rkm_medis "
                                + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                                + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab WHERE "
                                + "c.no_rawat = '" + TNoRw.getText() +"' AND c.noId IN ("+ resepDipilih +") ORDER BY c.noId", param);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TCari.setText("");
        TResepObat.setText("");
        TCariObat.setText("");
        ChkInput1.setSelected(false);
        isFormRiwayatObat();
        tampilResepObat();
    }//GEN-LAST:event_formWindowOpened

    private void BtnCopyResepTerakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepTerakhirActionPerformed
        cekResep = 0 ;
        tglResep = "";

        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
            cekResep = Sequel.cariInteger("SELECT count(-1) cek FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                    + "WHERE rp.kd_poli='" + kodepoli + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                    + "GROUP BY cr.tgl_perawatan, rp.no_rkm_medis ORDER BY cr.tgl_perawatan DESC LIMIT 1");

            if (cekResep == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, tidak ada resep terakhir sesuai kunjungan yg. tersimpan didalam sistem...!!!!");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...!!!!");
            } else {
                tglResep = Sequel.cariIsi("SELECT cr.tgl_perawatan FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                        + "WHERE rp.kd_poli='" + kodepoli + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                        + "GROUP BY cr.tgl_perawatan, rp.no_rkm_medis ORDER BY cr.tgl_perawatan DESC LIMIT 1");

                x = JOptionPane.showConfirmDialog(null, "Resep terakhir pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " apakah akan dicopy...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    tampilItemResep(tglResep, TNoRM.getText(), kodepoli);

                    for (i = 0; i < tbItemResep.getRowCount(); i++) {
                        tbItemResep.setValueAt(Boolean.TRUE, i, 0);
                    }
                    copyResepnya();
                }
            }
        } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
            cekResep = Sequel.cariInteger("SELECT count(-1) FROM catatan_resep_ranap WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekResep == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, tidak ada resep terakhir sesuai kunjungan yg. tersimpan didalam sistem...!!!!");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...!!!!");
            } else {
                tglResep = Sequel.cariIsi("SELECT tgl_perawatan FROM catatan_resep_ranap where no_rawat='" + TNoRw.getText() + "' ORDER BY noId DESC LIMIT 1");

                x = JOptionPane.showConfirmDialog(null, "Resep terakhir pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " apakah akan dicopy...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    tampilItemResepRanap(tglResep, TNoRw.getText());

                    for (i = 0; i < tbItemResep.getRowCount(); i++) {
                        tbItemResep.setValueAt(Boolean.TRUE, i, 0);
                    }
                    copyResepnya();
                }
            }
        }
    }//GEN-LAST:event_BtnCopyResepTerakhirActionPerformed

    private void tbItemResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemResepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepMouseClicked

    private void tbItemResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepKeyPressed

    private void MnDiCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiCopyActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...!!!!");
            tbResepObat.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu resep yang dipilih utk. di copy..!!!!");
                tbResepObat.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                            if (resepDipilih.equals("")) {
                                resepDipilih = tbResepObat.getValueAt(i, 4).toString();
                            } else {
                                resepDipilih = resepDipilih + "\n" + tbResepObat.getValueAt(i, 4).toString();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                akses.setCopyData(resepDipilih);
                JOptionPane.showMessageDialog(null, "Resep yang dipilih berhasil di copy..!!!!");
                BtnKeluarActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnDiCopyActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgCatatanResep");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn10.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilRiwayat();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari2.setText("");
        tampilRiwayat();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                kembalikanData();
                TCari.setText("");
                Valid.SetTgl(DTPCariA, tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
                BtnCloseIn10ActionPerformed(null);
                tampilResepObat();
                TResepObat.setText("");
                TResepObat.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        Valid.SetTgl(DTPCari3, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
        DTPCari4.setDate(new Date());
        TCari2.setText(TNoRM.getText());
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(985, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnRiwayatDataActionPerformed

    private void BtnSetujuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSetujuActionPerformed
        if (tbResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, belum ada item/resep obat yang diberikan, simpan dulu resepnya..!!");
        } else if (cmbJnsResep.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis resepnya..!!");
            cmbJnsResep.requestFocus();
        } else {
            resepDipilih = "";
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (resepDipilih.equals("")) {
                    resepDipilih = "'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                } else {
                    resepDipilih = resepDipilih + ",'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                }
            }
            
            if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where noId in (" + resepDipilih + ") and (status='SUDAH' or status='DILUAR')") > 0) {
                JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep yang sudah diverifikasi apotek tdk. bisa diganti,     \n"
                            + "Silakan input lagi sbg. resep baru/lanjutan...!!!!");
            } else {
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    Sequel.mengedit("catatan_resep_ranap", "noId='" + tbResepObat.getValueAt(i, 7).toString() + "'",
                            "jenis_resep='" + cmbJnsResep.getSelectedItem().toString() + "'");
                }
            }

            ChkCito.setEnabled(true);
            ChkCito.setSelected(false);
            cmbJnsResep.setEnabled(true);
            BtnSetuju.setEnabled(true);
            cmbJnsResep.setSelectedIndex(0);
            TResepObat.setText("");
            tampilResepObat();
        }
    }//GEN-LAST:event_BtnSetujuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCatatanResep dialog = new DlgCatatanResep(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCariObat;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCopyResepTerakhir;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSetuju;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkCito;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkPoli1;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.Tanggal DTPCariA;
    private widget.Tanggal DTPCariB;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnDiCopy;
    private javax.swing.JMenuItem MnDibatalkan;
    private javax.swing.JMenuItem MnRiwayatData;
    private javax.swing.JMenuItem MnSemuanya;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelRiwayatObat;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll35;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll44;
    private widget.ScrollPane Scroll45;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TCariObat;
    private widget.TextBox TIdObat;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TResepObat;
    private widget.TextBox Tcara_byr;
    private widget.TextBox Tjk;
    private widget.TextBox TtglLahir;
    private javax.swing.JDialog WindowRiwayat;
    private widget.ComboBox cmbJnsObat;
    private widget.ComboBox cmbJnsResep;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel5;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel64;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.TextBox noIdObat;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass18;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.Table tbItemObat;
    private widget.Table tbItemResep;
    private widget.Table tbObat;
    private widget.Table tbResepObat;
    private widget.Table tbRiwayat;
    private widget.Table tbTglBeriObat;
    // End of variables declaration//GEN-END:variables

    public void setData(String norw, String sttsrwt) {
        TNoRw.setText(norw);
        status = sttsrwt;
        ChkCito.setSelected(false);
        cmbJnsResep.setSelectedIndex(0);
        
        try {
            ps2 = koneksi.prepareStatement("select rp.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, "
                    + "if(p.jk='L','Laki-laki','Perempuan') jk, rp.tgl_registrasi, pj.png_jawab, rp.status_lanjut from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                    + "where rp.no_rawat='" + norw + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    TNoRM.setText(rs2.getString("no_rkm_medis"));
                    TPasien.setText(rs2.getString("nm_pasien"));
                    TtglLahir.setText(rs2.getString("tgllhr"));
                    Tjk.setText(rs2.getString("jk"));
                    Tcara_byr.setText(rs2.getString("png_jawab"));
                    jnsKunjungan = rs2.getString("status_lanjut");
                    
                    if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                            || jnsKunjungan.equals("Ralan") || status.equals("ralan")) {
                        DTPCariA.setDate(rs2.getDate("tgl_registrasi"));
                        ChkCito.setEnabled(false);
                        cmbJnsResep.setEnabled(false);
                        BtnSetuju.setEnabled(false);
                    } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                        DTPCariA.setDate(new Date());
                        ChkCito.setEnabled(true);
                        cmbJnsResep.setEnabled(true);
                        BtnSetuju.setEnabled(true);
                    }
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
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getresep_dokter());
        BtnHapus.setEnabled(akses.getresep_dokter());
        BtnEdit.setEnabled(akses.getresep_dokter());
        BtnCopyResepTerakhir.setEnabled(akses.getresep_dokter());
        MnRiwayatData.setEnabled(akses.getadmin());
    }

    private void tampilResepObat() {
        Valid.tabelKosong(tabModeResepObat);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                ps1 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, "
                        + "c.status, d.nm_dokter, c.noID, c.kd_dokter, '-' jenis_resep from catatan_resep c "
                        + "inner join reg_periksa r on r.no_rawat = c.no_rawat "
                        + "inner join dokter d on d.kd_dokter = c.kd_dokter where "
                        + "c.tgl_perawatan between ? and ? and c.no_rawat = ? and c.nama_obat like ? order by c.noId");
            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                ps1 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, "
                        + "c.status, d.nm_dokter, c.noID, c.kd_dokter, c.jenis_resep from catatan_resep_ranap c "
                        + "inner join reg_periksa r on r.no_rawat = c.no_rawat "
                        + "inner join dokter d on d.kd_dokter = c.kd_dokter where "
                        + "c.tgl_perawatan between ? and ? and c.no_rawat = ? and c.nama_obat like ? "
                        + "order by c.noId");
            }
            try {
                ps1.setString(1, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps1.setString(2, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps1.setString(3, TNoRw.getText().trim());
                ps1.setString(4, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabModeResepObat.addRow(new Object[]{
                        false,
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6),
                        rs1.getString(7),
                        rs1.getString(8),
                        rs1.getString(9)
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
        LCount.setText("" + tabModeResepObat.getRowCount());
    }
    
    private void isFormRiwayatObat() {
        if (ChkInput1.isSelected() == true) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 220));
            PanelRiwayatObat.setVisible(true);
            ChkInput1.setVisible(true);
            ChkPoli1.setSelected(true);
            
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                if (ChkPoli1.isSelected() == true) {
                    ChkPoli1.setText("Hanya dipoli/inst. ini untuk 5 kunjungan terakhir");
                } else if (ChkPoli1.isSelected() == false) {
                    ChkPoli1.setText("Semua Poli/Inst. untuk 5 kunjungan terakhir");
                }
            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                if (ChkPoli1.isSelected() == true) {
                    ChkPoli1.setText("Semua resep ditampilkan selama perawatan saat ini");
                } else if (ChkPoli1.isSelected() == false) {
                    ChkPoli1.setText("Semua resep ditampilkan selama perawatan saat ini");                    
                }
            }
            tampilTglBeriObat();
        } else if (ChkInput1.isSelected() == false) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 20));
            PanelRiwayatObat.setVisible(false);
            ChkInput1.setVisible(true);
            Valid.tabelKosong(tabModeTglBeriObat);
            Valid.tabelKosong(tabModeRiwItemObat);
        }
    }
    
    private void tampilFarmasi() {
        Valid.tabelKosong(tabModeFarmasi);
        try {
            psFar = koneksi.prepareStatement("SELECT db.nama_brng, db.kode_sat, sum(case when gd.kd_bangsal = 'APT01' then ifnull(format(gd.stok,0),0) END) apotek_igd, "
                    + "sum(case when gd.kd_bangsal = 'APT02' then ifnull(format(gd.stok,0),0) END) apotek_sentral FROM gudangbarang gd "
                    + "INNER JOIN databarang db on db.kode_brng=gd.kode_brng where "
                    + "db.nama_brng like ? and gd.kd_bangsal in ('APT01','APT02') and db.nama_brng not like '(FR)%' group by gd.kode_brng "
                    + "order by db.nama_brng");
            try {
                psFar.setString(1, "%" + TCariObat.getText().trim() + "%");
                rsFar = psFar.executeQuery();
                while (rsFar.next()) {
                    tabModeFarmasi.addRow(new Object[]{
                        rsFar.getString("nama_brng"),
                        rsFar.getString("kode_sat"),
                        rsFar.getString("apotek_igd"),
                        rsFar.getString("apotek_sentral")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsFar != null) {
                    rsFar.close();
                }
                if (psFar != null) {
                    psFar.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCatatanResep() {
        jenisResep = "";
        if (tbResepObat.getSelectedRow() != -1) {
            TNoRM.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 1).toString());
            TResepObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 4).toString());            
            TIdObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 7).toString());
            jenisResep = tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 9).toString();
          
            if (jenisResep.equals("CITO")) {
                ChkCito.setSelected(true);
                ChkCito.setEnabled(true);
            } else if (jenisResep.equals("BIASA")) {
                ChkCito.setSelected(false);
                ChkCito.setEnabled(true);
            } else {
                ChkCito.setSelected(false);
                ChkCito.setEnabled(false);
            }
        }
    }
    
    private void getDataFarmasi() {
        if (tbObat.getSelectedRow() != -1) {
            if (TResepObat.getText().equals("")) {
                TResepObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + " ");
            } else {
                TResepObat.setText(TResepObat.getText() + " " + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            }
            TResepObat.requestFocus();
        }        
    }
    
    private void tampilTglBeriObat() {
        Valid.tabelKosong(tabModeTglBeriObat);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                if (ChkPoli1.isSelected() == true) {
                    psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                            + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter, dpo.jam from detail_pemberian_obat dpo "
                            + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                            + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                            + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                            + "rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.kd_poli='" + kodepoli + "' and dpo.status='ralan' "
                            + "group by dpo.tgl_perawatan, dpo.jam order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
                } else if (ChkPoli1.isSelected() == false) {
                    psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                            + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter, dpo.jam from detail_pemberian_obat dpo "
                            + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                            + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                            + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                            + "rp.no_rkm_medis='" + TNoRM.getText() + "' and dpo.status='ralan' "
                            + "group by dpo.tgl_perawatan, dpo.jam order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
                }
            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                psTglBO = koneksi.prepareStatement("SELECT dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                        + "count(dpo.kode_brng) jlhItem, IF(dpo.STATUS='Ralan','IGD','R. Inap') nm_unit, d.nm_dokter, dpo.jam "
                        + "FROM detail_pemberian_obat dpo INNER JOIN reg_periksa rp ON rp.no_rawat = dpo.no_rawat "
                        + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                        + "INNER JOIN dokter d ON d.kd_dokter = ro.kd_dokter WHERE dpo.no_rawat='" + TNoRw.getText() + "' "
                        + "GROUP BY dpo.tgl_perawatan, dpo.jam, dpo.STATUS, ro.kd_dokter ORDER BY dpo.tgl_perawatan, dpo.jam");
            }

            try {
                rsTglBO = psTglBO.executeQuery();
                while (rsTglBO.next()) {
                    if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                            || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                        tabModeTglBeriObat.addRow(new Object[]{
                            rsTglBO.getString("tglAsli"),
                            rsTglBO.getString("tanggal"),
                            rsTglBO.getString("jam"),
                            rsTglBO.getString("jlhItem"),
                            rsTglBO.getString("nm_poli"),
                            rsTglBO.getString("nm_dokter")                            
                        });
                    } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                        tabModeTglBeriObat.addRow(new Object[]{
                            rsTglBO.getString("tglAsli"),
                            rsTglBO.getString("tanggal"),
                            rsTglBO.getString("jam"),
                            rsTglBO.getString("jlhItem"),
                            rsTglBO.getString("nm_unit"),
                            rsTglBO.getString("nm_dokter")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsTglBO != null) {
                    rsTglBO.close();
                }
                if (psTglBO != null) {
                    psTglBO.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwItemObat() {
        Valid.tabelKosong(tabModeRiwItemObat);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                if (ChkPoli1.isSelected() == true) {
                    psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                            + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObat + "' and dpo.jam='" + jamberiobat + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' and "
                            + "rp.kd_poli='" + kodepoli + "' and dpo.status='ralan'");
                } else if (ChkPoli1.isSelected() == false) {
                    psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                            + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObat + "' and dpo.jam='" + jamberiobat + "'and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                            + "and dpo.status='ralan'");
                }
            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                if (ChkPoli1.isSelected() == true) {
                    psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObat + "' and dpo.jam='" + jamberiobat + "' and dpo.no_rawat='" + TNoRw.getText() + "'");
                } else if (ChkPoli1.isSelected() == false) {
                    psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObat + "' and dpo.jam='" + jamberiobat + "' and dpo.no_rawat='" + TNoRw.getText() + "'");
                }
            }
            
            try {
                rsRiwIO = psRiwIO.executeQuery();
                x = 1;
                while (rsRiwIO.next()) {
                    tabModeRiwItemObat.addRow(new Object[]{
                        x + ".",
                        rsRiwIO.getString("tanggal"),
                        rsRiwIO.getString("nama_brng"),
                        rsRiwIO.getString("jlh")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsRiwIO != null) {
                    rsRiwIO.close();
                }
                if (psRiwIO != null) {
                    psRiwIO.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataRiwObat() {
        tglPemberianObat = "";
        jamberiobat = "";
        if (tbTglBeriObat.getSelectedRow() != -1) {
            tglPemberianObat = tbTglBeriObat.getValueAt(tbTglBeriObat.getSelectedRow(), 0).toString();
            jamberiobat = tbTglBeriObat.getValueAt(tbTglBeriObat.getSelectedRow(), 2).toString();
            tampilRiwItemObat();
        }
    }
    
    private void getDataItemObat() {
        if (tbItemObat.getSelectedRow() != -1) {
            TResepObat.setText(tbItemObat.getValueAt(tbItemObat.getSelectedRow(), 2).toString());
            TResepObat.requestFocus();
        }
    }
    
    private void contengResep() {
        for (i = 0; i < tbResepObat.getRowCount(); i++) {
            if (tbResepObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                tbResepObat.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }
    
    private void tampilItemResepRanap(String tglresep, String norw) {
        Valid.tabelKosong(tabModeResep2);
        try {
            psR2 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, "
                    + "d.nm_dokter, c.noID, c.jenis_resep FROM catatan_resep_ranap c "
                    + "INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                    + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter	WHERE "
                    + "r.status_lanjut='ranap' AND c.tgl_perawatan='" + tglresep + "' AND c.no_rawat = '" + norw + "' ORDER BY c.noId");
            try {
                rsR2 = psR2.executeQuery();
                while (rsR2.next()) {
                    tabModeResep2.addRow(new Object[]{
                        false,
                        rsR2.getString(1),
                        rsR2.getString(2),
                        rsR2.getString(3),
                        rsR2.getString(4),
                        rsR2.getString(5),
                        rsR2.getString(6),
                        rsR2.getString(7),
                        rsR2.getString(8)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR2 != null) {
                    rsR2.close();
                }
                if (psR2 != null) {
                    psR2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilItemResep(String tglresep, String norm, String kdpoli) {
        Valid.tabelKosong(tabModeResep2);
        try {
            psR2 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, d.nm_dokter, "
                    + "c.noID, '-' jenis_resep from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter where "
                    + "c.tgl_perawatan='" + tglresep + "' and r.no_rkm_medis='" + norm + "' and r.kd_poli='" + kdpoli + "' order by c.noId");
            try {
                rsR2 = psR2.executeQuery();
                while (rsR2.next()) {
                    tabModeResep2.addRow(new Object[]{
                        false,
                        rsR2.getString(1),
                        rsR2.getString(2),
                        rsR2.getString(3),
                        rsR2.getString(4),
                        rsR2.getString(5),
                        rsR2.getString(6),
                        rsR2.getString(7),
                        rsR2.getString(8)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR2 != null) {
                    rsR2.close();
                }
                if (psR2 != null) {
                    psR2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copyResepnya() {
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
            try {
                j = 0;
                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                                + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                Sequel.cariIsi("select year(now())"), 6, noIdObat);

                        Sequel.menyimpan("catatan_resep", "'" + noIdObat.getText() + "','" + TNoRw.getText() + "', "
                                + "'" + Sequel.cariIsi("select date(now())") + "',"
                                + "'" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                + "'" + tbItemResep.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "'", "Copy Resep Sebelumnya");
                    }
                }

                Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='ralan'", DTPCariA);
                ChkCito.setEnabled(false);
                cmbJnsResep.setEnabled(false);
                BtnSetuju.setEnabled(false);
                DTPCariB.setDate(new Date());
                TResepObat.setText("");
                TResepObat.requestFocus();
                tampilResepObat();
                Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                JOptionPane.showMessageDialog(null, "Resep sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " berhasil tercopy,..!!!");

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
        } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
            try {
                j = 0;
                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap where "
                                + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                Sequel.cariIsi("select year(now())"), 6, noIdObat);

                        Sequel.menyimpan("catatan_resep_ranap", "'" + noIdObat.getText() + "','" + TNoRw.getText() + "', "
                                + "'" + Sequel.cariIsi("select date(now())") + "',"
                                + "'" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                + "'" + tbItemResep.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "',"
                                + "'" + tbItemResep.getValueAt(i, 8).toString() + "'", "Copy Resep Sebelumnya");
                    }
                }

                ChkCito.setEnabled(true);
                cmbJnsResep.setEnabled(true);
                BtnSetuju.setEnabled(true);
                DTPCariA.setDate(new Date());
                DTPCariB.setDate(new Date());
                TResepObat.setText("");
                TResepObat.requestFocus();
                tampilResepObat();
                JOptionPane.showMessageDialog(null, "Resep sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " berhasil tercopy,..!!!");

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
    
    private void simpanHistoriResepRalan() {
        user = "";        
        if (akses.getadmin() == true) {
            user = "-";
        } else {
            user = akses.getkode();
        }

        try {
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("catatan_resep_histori", "?,?,?,?,?,?,?,?,?,?", "Data", 10, new String[]{
                        tbResepObat.getValueAt(i, 7).toString(),
                        tbResepObat.getValueAt(i, 1).toString(),
                        tbResepObat.getValueAt(i, 2).toString(),
                        tbResepObat.getValueAt(i, 3).toString(),
                        tbResepObat.getValueAt(i, 4).toString(),
                        tbResepObat.getValueAt(i, 5).toString(),
                        tbResepObat.getValueAt(i, 8).toString(),
                        riwayatData,
                        user,
                        Sequel.cariIsi("select now()")
                    });

                    //jeda 1 detik
                    Thread.sleep(1000);

                    Sequel.queryu("delete from catatan_resep where no_rawat='" + tbResepObat.getValueAt(i, 1).toString()
                            + "' and tgl_perawatan='" + tbResepObat.getValueAt(i, 2).toString()
                            + "' and jam_perawatan='" + tbResepObat.getValueAt(i, 3).toString()
                            + "' and noId='" + tbResepObat.getValueAt(i, 7).toString() + "'");
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void simpanHistoriResepRanap() {
        user = "";        
        if (akses.getadmin() == true) {
            user = "-";
        } else {
            user = akses.getkode();
        }

        try {
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("catatan_resep_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?", "Data", 11, new String[]{
                        tbResepObat.getValueAt(i, 7).toString(),
                        tbResepObat.getValueAt(i, 1).toString(),
                        tbResepObat.getValueAt(i, 2).toString(),
                        tbResepObat.getValueAt(i, 3).toString(),
                        tbResepObat.getValueAt(i, 4).toString(),
                        tbResepObat.getValueAt(i, 5).toString(),
                        tbResepObat.getValueAt(i, 8).toString(),
                        riwayatData,
                        user,
                        Sequel.cariIsi("select now()"),
                        tbResepObat.getValueAt(i, 9).toString()
                    });

                    //jeda 1 detik
                    Thread.sleep(1000);

                    Sequel.queryu("delete from catatan_resep_ranap where no_rawat='" + tbResepObat.getValueAt(i, 1).toString()
                            + "' and tgl_perawatan='" + tbResepObat.getValueAt(i, 2).toString()
                            + "' and jam_perawatan='" + tbResepObat.getValueAt(i, 3).toString()
                            + "' and noId='" + tbResepObat.getValueAt(i, 7).toString() + "'");
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode2);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                psrestor = koneksi.prepareStatement("SELECT IF(pg.nama = '-', 'Admin Utama', pg.nama ) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                        + "a.tgl_perawatan, a.jam_perawatan, a.waktu_eksekusi, upper(concat( 'DI', a.status_data )) sttsdata, a.noId, a.nama_obat "
                        + "FROM catatan_resep_histori a INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                        + "a.tgl_perawatan between ? and ? and pg.nama like ? or "
                        + "a.tgl_perawatan between ? and ? and a.no_rawat like ? or "
                        + "a.tgl_perawatan between ? and ? and p.no_rkm_medis like ? or "
                        + "a.tgl_perawatan between ? and ? and a.status_data like ? or "
                        + "a.tgl_perawatan between ? and ? and p.nm_pasien like ? order by a.tgl_perawatan desc");
            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                psrestor = koneksi.prepareStatement("SELECT IF(pg.nama = '-', 'Admin Utama', pg.nama ) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                        + "a.tgl_perawatan, a.jam_perawatan, a.waktu_eksekusi, upper(concat( 'DI', a.status_data )) sttsdata, a.noId, a.nama_obat "
                        + "FROM catatan_resep_ranap_histori a INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                        + "a.tgl_perawatan between ? and ? and pg.nama like ? or "
                        + "a.tgl_perawatan between ? and ? and a.no_rawat like ? or "
                        + "a.tgl_perawatan between ? and ? and p.no_rkm_medis like ? or "
                        + "a.tgl_perawatan between ? and ? and a.status_data like ? or "
                        + "a.tgl_perawatan between ? and ? and p.nm_pasien like ? order by a.tgl_perawatan desc");
            }            
            
            try {
                psrestor.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(3, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(6, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(9, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(12, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(13, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(14, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(15, "%" + TCari2.getText().trim() + "%");
                rsrestor = psrestor.executeQuery();
                while (rsrestor.next()) {
                    tabMode2.addRow(new String[]{
                        rsrestor.getString("pelaku"),
                        rsrestor.getString("no_rawat"),
                        rsrestor.getString("no_rkm_medis"),
                        rsrestor.getString("nm_pasien"),
                        rsrestor.getString("tgl_perawatan"),
                        rsrestor.getString("jam_perawatan"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata"),                        
                        rsrestor.getString("noId"),
                        rsrestor.getString("nama_obat")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsrestor != null) {
                    rsrestor.close();
                }
                if (psrestor != null) {
                    psrestor.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode2.getRowCount());
    }
    
    private void kembalikanData() {
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                    || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                ps3 = koneksi.prepareStatement("select * from catatan_resep_histori where "
                        + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'");
            } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                ps3 = koneksi.prepareStatement("select * from catatan_resep_ranap_histori where "
                        + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'");
            }
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")
                            || jnsKunjungan.equals("Ralan") || status.equals("Ralan")) {
                        try {
                            Sequel.menyimpanIgnore("catatan_resep",
                                    "'" + rs3.getString("noId") + "',"
                                    + "'" + TNoRw.getText() + "',"
                                    + "'" + rs3.getString("tgl_perawatan") + "',"
                                    + "'" + rs3.getString("jam_perawatan") + "',"
                                    + "'" + rs3.getString("nama_obat") + "',"
                                    + "'" + rs3.getString("status") + "',"
                                    + "'" + rs3.getString("kd_dokter") + "'", "Catatan Resep Rawat Jalan");
                        } catch (Exception e) {
                            System.out.println("Simpan : " + e);
                        }
                        
                    } else if (status.equals("ranap") || jnsKunjungan.equals("Ranap")) {
                        try {
                            Sequel.menyimpanIgnore("catatan_resep_ranap",
                                    "'" + rs3.getString("noId") + "',"
                                    + "'" + TNoRw.getText() + "',"
                                    + "'" + rs3.getString("tgl_perawatan") + "',"
                                    + "'" + rs3.getString("jam_perawatan") + "',"
                                    + "'" + rs3.getString("nama_obat") + "',"
                                    + "'" + rs3.getString("status") + "',"
                                    + "'" + rs3.getString("kd_dokter") + "',"
                                    + "'" + rs3.getString("jenis_resep") + "'", "Catatan Resep Rawat Inap");
                        } catch (Exception e) {
                            System.out.println("Simpan : " + e);
                        }
                    }
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
