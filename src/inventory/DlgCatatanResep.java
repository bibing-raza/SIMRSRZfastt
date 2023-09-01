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

/**
 *
 * @author perpustakaan
 */
public class DlgCatatanResep extends javax.swing.JDialog {
    private final DefaultTableModel tabModeResepObat, tabModeFarmasi, tabModeTglBeriObat, tabModeRiwItemObat, tabModeResep2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps1, ps2, psFar, psTglBO, psRiwIO, psR2;
    private ResultSet rs1, rs2, rsFar,rsTglBO, rsRiwIO, rsR2;
    private int i = 0, x = 0, j = 0;
    private String tglPemberianObat = "", resepDipilih = "", cekResep = "", tglResep = "", kodepoli = "";

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
            "P", "No.Rawat", "Tgl.Input", "Jam Input", "Nama Obat", "Status", "Nama Dokter", "Id"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbResepObat.setModel(tabModeResepObat);
        tbResepObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
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
                column.setPreferredWidth(200);
            } else if (i == 7) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbResepObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeFarmasi = new DefaultTableModel(null, new Object[]{"Nama Obat/Alkes", "Satuan", "Stok Apotek IGD (UMUM)","Stok Apotek Sentral (BPJS)"}) {
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
            "tgl", "Tanggal","Jlh. Item", "Poliklinik", "Dokter Peresep"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTglBeriObat.setModel(tabModeTglBeriObat);
        tbTglBeriObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTglBeriObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbTglBeriObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);                
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(56);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } 
        }
        tbTglBeriObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwItemObat = new DefaultTableModel(null, new Object[]{
            "Tanggal","Nama Obat/Alkes/BHP", "Jumlah"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemObat.setModel(tabModeRiwItemObat);
        tbItemObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbItemObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(64);            
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(56);
            } 
        }
        tbItemObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResep2 = new DefaultTableModel(null, new Object[]{
            "Cek", "no_rawat", "Tgl. Resep", "jam_input", "Nama Item Obat", "status", "nm_dokter", "id"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbItemResep.setModel(tabModeResep2);
        tbItemResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
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
            }
        }
        tbItemResep.setDefaultRenderer(Object.class, new WarnaTable());
    }

    //private DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
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
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelGlass16 = new widget.panelisi();
        jLabel53 = new widget.Label();
        TResepObat = new widget.TextBox();
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
        jLabel74 = new widget.Label();
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
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 105));
        panelGlass13.setLayout(null);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Tgl. Resep : ");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel54);
        jLabel54.setBounds(0, 66, 105, 23);

        DTPCariA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-08-2023" }));
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

        DTPCariB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-08-2023" }));
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

        internalFrame1.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

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
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Item Obat/Resep yang diberikan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(660, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

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
        TResepObat.setPreferredSize(new java.awt.Dimension(450, 24));
        TResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TResepObatKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TResepObatKeyPressed(evt);
            }
        });
        panelGlass16.add(TResepObat);

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
        ChkPoli1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkPoli1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPoli1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPoli1.setName("ChkPoli1"); // NOI18N
        ChkPoli1.setOpaque(false);
        ChkPoli1.setPreferredSize(new java.awt.Dimension(127, 23));
        ChkPoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPoli1ActionPerformed(evt);
            }
        });
        panelGlass17.add(ChkPoli1);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("untuk 5 kunjungan terakhir.");
        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        jLabel74.setPreferredSize(new java.awt.Dimension(170, 23));
        panelGlass17.add(jLabel74);

        panelGlass18.add(panelGlass17, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObat.add(panelGlass18);

        Scroll45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Item Obat Sesuai Yang Diresepkan ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll45.setName("Scroll45"); // NOI18N
        Scroll45.setOpaque(true);
        Scroll45.setPreferredSize(new java.awt.Dimension(390, 423));

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
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setBorder(null);
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
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                    + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                    Sequel.cariIsi("select year(now())"), 6, noIdObat);
            try {
                Sequel.menyimpan("catatan_resep", "?,?,?,?,?,?,?", "Data", 7, new String[]{
                    noIdObat.getText(), TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                    TResepObat.getText(), "BELUM", akses.getkode()
                });
                Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                TResepObat.setText("");
                TResepObat.requestFocus();
                tampilResepObat();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan, Hubungi Admin ! " + e);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnSimpanActionPerformed(null);
//        }else{
//            Valid.pindah(evt,TCatatan,BtnKeluar);
//        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
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
        if (ChkPoli1.isSelected() == true) {
            ChkPoli1.setText("Hanya dipoliklinik ini");
            tampilTglBeriObat();
        } else if (ChkPoli1.isSelected() == false) {
            ChkPoli1.setText("Semua Poliklinik");
            tampilTglBeriObat();
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
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.queryu("delete from catatan_resep where no_rawat='" + tbResepObat.getValueAt(i, 1).toString()
                            + "' and tgl_perawatan='" + tbResepObat.getValueAt(i, 2).toString()
                            + "' and jam_perawatan='" + tbResepObat.getValueAt(i, 3).toString()
                            + "' and noId='" + tbResepObat.getValueAt(i, 7).toString() + "'");
                }
            }
            TResepObat.setText("");
            tampilResepObat();
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
            if (tbResepObat.getSelectedRow() > -1) {
                Sequel.mengedit("catatan_resep", "noId='" + TIdObat.getText() + "'",
                        "no_rawat='" + TNoRw.getText() + "',tgl_perawatan='" + Sequel.cariIsi("SELECT date(NOW())") + "',"
                        + "jam_perawatan='" + Sequel.cariIsi("SELECT TIME(NOW())") + "',nama_obat = '" + TResepObat.getText() + "'");
                TResepObat.setText("");
                tampilResepObat();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                TCari.requestFocus();
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
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        if (resepDipilih.equals("")) {
                            resepDipilih = "'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                        } else {
                            resepDipilih = resepDipilih + ",'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                        }
                    }
                }

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
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where c.no_rawat ='" + TNoRw.getText() + "' and c.noId in (" + resepDipilih + ") order by c.noId", param);
                    this.setCursor(Cursor.getDefaultCursor());
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
        cekResep = "";
        tglResep = "";
        cekResep = Sequel.cariIsi("SELECT count(-1) cek FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
            + "WHERE rp.kd_poli='" + kodepoli + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' "
            + "GROUP BY cr.tgl_perawatan, rp.no_rkm_medis ORDER BY cr.tgl_perawatan DESC LIMIT 1");

        if (cekResep.equals("")) {
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
    }//GEN-LAST:event_BtnCopyResepTerakhirActionPerformed

    private void tbItemResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemResepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepMouseClicked

    private void tbItemResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepKeyPressed

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
    private widget.Button BtnCari1;
    private widget.Button BtnCariObat;
    private widget.Button BtnCopyResepTerakhir;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkPoli1;
    private widget.Tanggal DTPCariA;
    private widget.Tanggal DTPCariB;
    private javax.swing.JMenuItem MnDibatalkan;
    private javax.swing.JMenuItem MnSemuanya;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelRiwayatObat;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll35;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll44;
    private widget.ScrollPane Scroll45;
    private widget.TextBox TCari;
    private widget.TextBox TCariObat;
    private widget.TextBox TIdObat;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TResepObat;
    private widget.TextBox Tcara_byr;
    private widget.TextBox Tjk;
    private widget.TextBox TtglLahir;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel5;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel64;
    private widget.Label jLabel74;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
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
    private widget.Table tbTglBeriObat;
    // End of variables declaration//GEN-END:variables

    public void setData(String norw, String kodepl) {
        TNoRw.setText(norw);
        kodepoli = kodepl;
        try {
            ps2 = koneksi.prepareStatement("select rp.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, "
                    + "if(p.jk='L','Laki-laki','Perempuan') jk, rp.tgl_registrasi, pj.png_jawab from reg_periksa rp "
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
                    DTPCariA.setDate(rs2.getDate("tgl_registrasi"));
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
        BtnPrint.setEnabled(akses.getresep_dokter());
        BtnEdit.setEnabled(akses.getresep_dokter());
        BtnCopyResepTerakhir.setEnabled(akses.getresep_dokter());
    }

    private void tampilResepObat() {
        Valid.tabelKosong(tabModeResepObat);
        try {
            ps1 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, "
                    + "c.status, d.nm_dokter, c.noID from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat "
                    + "inner join dokter d on d.kd_dokter = c.kd_dokter where "
                    + "c.tgl_perawatan between ? and ? and c.no_rawat = ? and c.nama_obat like ? "
                    + "order by c.noId");
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
                        rs1.getString(7)
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
    }
    
    private void isFormRiwayatObat() {
        if (ChkInput1.isSelected() == true) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 220));
            PanelRiwayatObat.setVisible(true);
            ChkInput1.setVisible(true);
            ChkPoli1.setSelected(true);
            
            if (ChkPoli1.isSelected() == true) {
                ChkPoli1.setText("Hanya dipoli/inst. ini");
                tampilTglBeriObat();
            } else if (ChkPoli1.isSelected() == false) {
                ChkPoli1.setText("Semua Poli/Inst.");
                tampilTglBeriObat();
            }
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
        if (tbResepObat.getSelectedRow() != -1) {
            TNoRM.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 1).toString());
            TResepObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 4).toString());            
            TIdObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 7).toString());
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
            if (ChkPoli1.isSelected() == true) {
                psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                        + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter from detail_pemberian_obat dpo "
                        + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                        + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                        + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                        + "rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.kd_poli='" + kodepoli + "' and rp.status_lanjut='ralan' "
                        + "group by dpo.tgl_perawatan order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
            } else if (ChkPoli1.isSelected() == false) {
                psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                        + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter from detail_pemberian_obat dpo "
                        + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                        + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                        + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                        + "rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.status_lanjut='ralan' "
                        + "group by dpo.tgl_perawatan order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
            }
            try {
                rsTglBO = psTglBO.executeQuery();
                while (rsTglBO.next()) {
                    tabModeTglBeriObat.addRow(new Object[]{
                        rsTglBO.getString("tglAsli"),
                        rsTglBO.getString("tanggal"),
                        rsTglBO.getString("jlhItem"),
                        rsTglBO.getString("nm_poli"),
                        rsTglBO.getString("nm_dokter")
                    });
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
            if (ChkPoli1.isSelected() == true) {
                psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                        + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                        + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                        + "dpo.tgl_perawatan='" + tglPemberianObat + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' and "
                        + "rp.kd_poli='" + kodepoli + "' and rp.status_lanjut='ralan'");
            } else if (ChkPoli1.isSelected() == false) {
                psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                        + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                        + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                        + "dpo.tgl_perawatan='" + tglPemberianObat + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.status_lanjut='ralan'");
            }
            
            try {
                rsRiwIO = psRiwIO.executeQuery();
                while (rsRiwIO.next()) {
                    tabModeRiwItemObat.addRow(new Object[]{
                        rsRiwIO.getString("tanggal"),
                        rsRiwIO.getString("nama_brng"),
                        rsRiwIO.getString("jlh")
                    });
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
        if (tbTglBeriObat.getSelectedRow() != -1) {
            tglPemberianObat = tbTglBeriObat.getValueAt(tbTglBeriObat.getSelectedRow(), 0).toString();
            tampilRiwItemObat();
        }
    }
    
    private void getDataItemObat() {
        if (tbItemObat.getSelectedRow() != -1) {
            TResepObat.setText(tbItemObat.getValueAt(tbItemObat.getSelectedRow(), 1).toString());
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
    
    private void tampilItemResep(String tglresep, String norm, String kdpoli) {
        Valid.tabelKosong(tabModeResep2);
        try {
            psR2 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, d.nm_dokter, c.noID from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter where "
                    + "c.tgl_perawatan='" + tglresep + "' and r.no_rkm_medis='" + norm + "' and r.kd_poli='" + kdpoli + "' order by c.noId");
            try {
                rsR2 = psR2.executeQuery();
                while (rsR2.next()) {
                    tabModeResep2.addRow(new Object[]{false,
                        rsR2.getString(1),
                        rsR2.getString(2),
                        rsR2.getString(3),
                        rsR2.getString(4),
                        rsR2.getString(5),
                        rsR2.getString(6),
                        rsR2.getString(7)
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
            DTPCariB.setDate(new Date());
            TResepObat.setText("");
            TResepObat.requestFocus();
            tampilResepObat();
            Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
            JOptionPane.showMessageDialog(null, "Resep sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " berhasil tercopy,..!!!");

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
