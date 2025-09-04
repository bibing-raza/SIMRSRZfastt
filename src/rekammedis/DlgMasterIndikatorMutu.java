package rekammedis;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgMasterIndikatorMutu extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private String kode = "", stts = "", tglnonaktif = "", tujuanAktif = "", tglnonaktif1 = "", tujuanAktif1 = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgMasterIndikatorMutu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "Kode Indikator", "No. Urut", "Nama Indikator", "Gedung Perawatan", "Status Data", "Target", "Jenis Indikator",
            "Tgl. Non Aktif", "Tujuan Aktivasi", "Catatan", "tgl_non_aktif"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMutu.setModel(tabMode);
        tbMutu.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMutu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbMutu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(800);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(95);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbMutu.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbMutu.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbMutu.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbMutu.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "Kode Indikator", "No. Urut", "Nama Indikator", "Gedung Perawatan", "Status Data", "Target", "Jenis Indikator",
            "Tgl. Non Aktif", "Tujuan Aktivasi", "Catatan", "tgl_non_aktif"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbIndikator.setModel(tabMode1);
        tbIndikator.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbIndikator.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbIndikator.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(800);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(95);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbIndikator.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbIndikator.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TCari1.setDocument(new batasInput((byte) 100).getKata(TCari1));
        TnoUrut.setDocument(new batasInput((byte) 3).getOnlyAngka(TnoUrut));
        TnmIndikator.setDocument(new batasInput((int) 255).getKata(TnmIndikator));
        Ttarget.setDocument(new batasInput((int) 10).getKata(Ttarget));
        TtargetSemua.setDocument(new batasInput((int) 10).getKata(TtargetSemua));
        Tcatatan.setDocument(new batasInput((int) 255).getKata(Tcatatan));
        Tcatatan1.setDocument(new batasInput((int) 255).getKata(Tcatatan1));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
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
        MnSemuaIndikator = new javax.swing.JMenuItem();
        MnRefresKodeIndikator = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnRefresKodeIndikator1 = new javax.swing.JMenuItem();
        WindowIndikator = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel14 = new widget.Label();
        TkdIndikator = new widget.TextBox();
        TnmIndi = new widget.TextBox();
        jLabel15 = new widget.Label();
        cmbSttsIndikator = new widget.ComboBox();
        jLabel17 = new widget.Label();
        TtargetSemua = new widget.TextBox();
        jLabel22 = new widget.Label();
        cmbTujuan1 = new widget.ComboBox();
        jLabel23 = new widget.Label();
        TtglNonAktif1 = new widget.Tanggal();
        jLabel24 = new widget.Label();
        Tcatatan1 = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbIndikator = new widget.Table();
        panelisi4 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel16 = new widget.Label();
        LCount1 = new widget.Label();
        BtnAll1 = new widget.Button();
        BtnGanti1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        buttonGroup1 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnNumdenom = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel12 = new widget.Label();
        cmbGedung1 = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        jLabel4 = new widget.Label();
        kdIndikator = new widget.TextBox();
        jLabel9 = new widget.Label();
        TnmIndikator = new widget.TextBox();
        jLabel5 = new widget.Label();
        TnoUrut = new widget.TextBox();
        jLabel10 = new widget.Label();
        cmbGedung = new widget.ComboBox();
        jLabel11 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel13 = new widget.Label();
        Ttarget = new widget.TextBox();
        chkImu = new widget.CekBox();
        chkImp = new widget.CekBox();
        chkInm = new widget.CekBox();
        jLabel18 = new widget.Label();
        cmbJnsIndikator = new widget.ComboBox();
        jLabel19 = new widget.Label();
        TtglNonAktif = new widget.Tanggal();
        jLabel20 = new widget.Label();
        cmbTujuan = new widget.ComboBox();
        jLabel21 = new widget.Label();
        Tcatatan = new widget.TextBox();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbMutu = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSemuaIndikator.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuaIndikator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuaIndikator.setText("Semua Indikator Mutu");
        MnSemuaIndikator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuaIndikator.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuaIndikator.setIconTextGap(5);
        MnSemuaIndikator.setName("MnSemuaIndikator"); // NOI18N
        MnSemuaIndikator.setPreferredSize(new java.awt.Dimension(175, 26));
        MnSemuaIndikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuaIndikatorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSemuaIndikator);

        MnRefresKodeIndikator.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRefresKodeIndikator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnRefresKodeIndikator.setText("Refresh Kode Indikator");
        MnRefresKodeIndikator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRefresKodeIndikator.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRefresKodeIndikator.setIconTextGap(5);
        MnRefresKodeIndikator.setName("MnRefresKodeIndikator"); // NOI18N
        MnRefresKodeIndikator.setPreferredSize(new java.awt.Dimension(175, 26));
        MnRefresKodeIndikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRefresKodeIndikatorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRefresKodeIndikator);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnRefresKodeIndikator1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRefresKodeIndikator1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnRefresKodeIndikator1.setText("Refresh Kode Indikator");
        MnRefresKodeIndikator1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRefresKodeIndikator1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRefresKodeIndikator1.setIconTextGap(5);
        MnRefresKodeIndikator1.setName("MnRefresKodeIndikator1"); // NOI18N
        MnRefresKodeIndikator1.setPreferredSize(new java.awt.Dimension(175, 26));
        MnRefresKodeIndikator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRefresKodeIndikator1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnRefresKodeIndikator1);

        WindowIndikator.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowIndikator.setName("WindowIndikator"); // NOI18N
        WindowIndikator.setUndecorated(true);
        WindowIndikator.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Semua Indikator Mutu Unit ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 98));
        panelisi3.setLayout(null);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama Indikator : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi3.add(jLabel14);
        jLabel14.setBounds(0, 10, 110, 23);

        TkdIndikator.setEditable(false);
        TkdIndikator.setForeground(new java.awt.Color(0, 0, 0));
        TkdIndikator.setName("TkdIndikator"); // NOI18N
        panelisi3.add(TkdIndikator);
        TkdIndikator.setBounds(113, 10, 80, 23);

        TnmIndi.setEditable(false);
        TnmIndi.setForeground(new java.awt.Color(0, 0, 0));
        TnmIndi.setName("TnmIndi"); // NOI18N
        panelisi3.add(TnmIndi);
        TnmIndi.setBounds(197, 10, 730, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Status Indikator : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi3.add(jLabel15);
        jLabel15.setBounds(0, 38, 110, 23);

        cmbSttsIndikator.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsIndikator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif", "Semua" }));
        cmbSttsIndikator.setName("cmbSttsIndikator"); // NOI18N
        cmbSttsIndikator.setPreferredSize(new java.awt.Dimension(80, 23));
        cmbSttsIndikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSttsIndikatorActionPerformed(evt);
            }
        });
        panelisi3.add(cmbSttsIndikator);
        cmbSttsIndikator.setBounds(113, 38, 80, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Target :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi3.add(jLabel17);
        jLabel17.setBounds(200, 38, 50, 23);

        TtargetSemua.setForeground(new java.awt.Color(0, 0, 0));
        TtargetSemua.setName("TtargetSemua"); // NOI18N
        panelisi3.add(TtargetSemua);
        TtargetSemua.setBounds(255, 38, 100, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tujuan Aktivasi :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelisi3.add(jLabel22);
        jLabel22.setBounds(355, 38, 100, 23);

        cmbTujuan1.setForeground(new java.awt.Color(0, 0, 0));
        cmbTujuan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Input Data", "Laporan" }));
        cmbTujuan1.setName("cmbTujuan1"); // NOI18N
        cmbTujuan1.setPreferredSize(new java.awt.Dimension(55, 28));
        panelisi3.add(cmbTujuan1);
        cmbTujuan1.setBounds(460, 38, 85, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Non Aktif :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelisi3.add(jLabel23);
        jLabel23.setBounds(545, 38, 90, 23);

        TtglNonAktif1.setEditable(false);
        TtglNonAktif1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-09-2025" }));
        TtglNonAktif1.setDisplayFormat("dd-MM-yyyy");
        TtglNonAktif1.setName("TtglNonAktif1"); // NOI18N
        TtglNonAktif1.setOpaque(false);
        TtglNonAktif1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi3.add(TtglNonAktif1);
        TtglNonAktif1.setBounds(652, 38, 90, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Catatan : ");
        jLabel24.setName("jLabel24"); // NOI18N
        panelisi3.add(jLabel24);
        jLabel24.setBounds(0, 66, 110, 23);

        Tcatatan1.setForeground(new java.awt.Color(0, 0, 0));
        Tcatatan1.setName("Tcatatan1"); // NOI18N
        panelisi3.add(Tcatatan1);
        Tcatatan1.setBounds(113, 66, 770, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        Scroll1.setComponentPopupMenu(jPopupMenu2);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbIndikator.setAutoCreateRowSorter(true);
        tbIndikator.setToolTipText("Silahkan klik untuk memilih data yang akan diupdate");
        tbIndikator.setComponentPopupMenu(jPopupMenu2);
        tbIndikator.setName("tbIndikator"); // NOI18N
        tbIndikator.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIndikatorMouseClicked(evt);
            }
        });
        tbIndikator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIndikatorKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbIndikator);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi4.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelisi4.add(BtnCari1);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Record :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(jLabel16);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi4.add(LCount1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelisi4.add(BtnAll1);

        BtnGanti1.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti1.setMnemonic('G');
        BtnGanti1.setText("Ganti");
        BtnGanti1.setToolTipText("Alt+G");
        BtnGanti1.setName("BtnGanti1"); // NOI18N
        BtnGanti1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGanti1ActionPerformed(evt);
            }
        });
        BtnGanti1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGanti1KeyPressed(evt);
            }
        });
        panelisi4.add(BtnGanti1);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCloseIn1);

        internalFrame3.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowIndikator.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Master Indikator Nasional Mutu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 124));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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
        panelGlass8.add(BtnBatal);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll);

        BtnNumdenom.setForeground(new java.awt.Color(0, 0, 0));
        BtnNumdenom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-add24.png"))); // NOI18N
        BtnNumdenom.setMnemonic('M');
        BtnNumdenom.setText("Master Numerator Denominator");
        BtnNumdenom.setToolTipText("Alt+M");
        BtnNumdenom.setName("BtnNumdenom"); // NOI18N
        BtnNumdenom.setPreferredSize(new java.awt.Dimension(250, 30));
        BtnNumdenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNumdenomActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNumdenom);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Filter Data ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Rg. Perawatan/Unit/Inst./Bidang/Sub :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 23));
        panelGlass10.add(jLabel12);

        cmbGedung1.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "RAWAT JALAN", "IBS", "AR RAUDAH", "HEMODIALISA" }));
        cmbGedung1.setName("cmbGedung1"); // NOI18N
        cmbGedung1.setPreferredSize(new java.awt.Dimension(200, 23));
        panelGlass10.add(cmbGedung1);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
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
        panelGlass10.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 159));
        PanelInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Kode Indikator : ");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 100, 23);

        kdIndikator.setEditable(false);
        kdIndikator.setForeground(new java.awt.Color(0, 0, 0));
        kdIndikator.setName("kdIndikator"); // NOI18N
        PanelInput.add(kdIndikator);
        kdIndikator.setBounds(500, 10, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No. Urut :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 38, 100, 23);

        TnmIndikator.setForeground(new java.awt.Color(0, 0, 0));
        TnmIndikator.setName("TnmIndikator"); // NOI18N
        TnmIndikator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmIndikatorKeyPressed(evt);
            }
        });
        PanelInput.add(TnmIndikator);
        TnmIndikator.setBounds(258, 38, 830, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nama Indikator :");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(155, 38, 98, 23);

        TnoUrut.setForeground(new java.awt.Color(0, 0, 0));
        TnoUrut.setName("TnoUrut"); // NOI18N
        TnoUrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoUrutKeyPressed(evt);
            }
        });
        PanelInput.add(TnoUrut);
        TnoUrut.setBounds(103, 38, 50, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Rg. Perawatan/Unit/Inst./Bidang/Sub : ");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(0, 66, 255, 23);

        cmbGedung.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "RAWAT JALAN", "IBS", "AR RAUDAH", "HEMODIALISA" }));
        cmbGedung.setName("cmbGedung"); // NOI18N
        cmbGedung.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbGedung);
        cmbGedung.setBounds(258, 66, 230, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Status Data :");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(0, 94, 255, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        PanelInput.add(cmbStatus);
        cmbStatus.setBounds(258, 94, 80, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Target :");
        jLabel13.setName("jLabel13"); // NOI18N
        PanelInput.add(jLabel13);
        jLabel13.setBounds(490, 66, 55, 23);

        Ttarget.setForeground(new java.awt.Color(0, 0, 0));
        Ttarget.setName("Ttarget"); // NOI18N
        Ttarget.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtargetKeyPressed(evt);
            }
        });
        PanelInput.add(Ttarget);
        Ttarget.setBounds(550, 66, 80, 23);

        chkImu.setBackground(new java.awt.Color(255, 255, 250));
        chkImu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkImu);
        chkImu.setForeground(new java.awt.Color(0, 0, 0));
        chkImu.setText("Mutu Unit (IMU)");
        chkImu.setBorderPainted(true);
        chkImu.setBorderPaintedFlat(true);
        chkImu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkImu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkImu.setName("chkImu"); // NOI18N
        chkImu.setOpaque(false);
        chkImu.setPreferredSize(new java.awt.Dimension(175, 23));
        chkImu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkImuActionPerformed(evt);
            }
        });
        PanelInput.add(chkImu);
        chkImu.setBounds(103, 10, 105, 23);

        chkImp.setBackground(new java.awt.Color(255, 255, 250));
        chkImp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkImp);
        chkImp.setForeground(new java.awt.Color(0, 0, 0));
        chkImp.setText("Mutu Prioritas RS (IMP)");
        chkImp.setBorderPainted(true);
        chkImp.setBorderPaintedFlat(true);
        chkImp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkImp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkImp.setName("chkImp"); // NOI18N
        chkImp.setOpaque(false);
        chkImp.setPreferredSize(new java.awt.Dimension(175, 23));
        chkImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkImpActionPerformed(evt);
            }
        });
        PanelInput.add(chkImp);
        chkImp.setBounds(218, 10, 140, 23);

        chkInm.setBackground(new java.awt.Color(255, 255, 250));
        chkInm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkInm);
        chkInm.setForeground(new java.awt.Color(0, 0, 0));
        chkInm.setText("Mutu Nasional (INM) :");
        chkInm.setBorderPainted(true);
        chkInm.setBorderPaintedFlat(true);
        chkInm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInm.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInm.setName("chkInm"); // NOI18N
        chkInm.setOpaque(false);
        chkInm.setPreferredSize(new java.awt.Dimension(175, 23));
        chkInm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInmActionPerformed(evt);
            }
        });
        PanelInput.add(chkInm);
        chkInm.setBounds(368, 10, 125, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jenis Indikator :");
        jLabel18.setName("jLabel18"); // NOI18N
        PanelInput.add(jLabel18);
        jLabel18.setBounds(630, 66, 100, 23);

        cmbJnsIndikator.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsIndikator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mutu Unit", "Mutu Prioritas RS", "Mutu Nasional" }));
        cmbJnsIndikator.setName("cmbJnsIndikator"); // NOI18N
        cmbJnsIndikator.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbJnsIndikator);
        cmbJnsIndikator.setBounds(735, 66, 115, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Non Aktif :");
        jLabel19.setName("jLabel19"); // NOI18N
        PanelInput.add(jLabel19);
        jLabel19.setBounds(530, 94, 90, 23);

        TtglNonAktif.setEditable(false);
        TtglNonAktif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-09-2025" }));
        TtglNonAktif.setDisplayFormat("dd-MM-yyyy");
        TtglNonAktif.setName("TtglNonAktif"); // NOI18N
        TtglNonAktif.setOpaque(false);
        TtglNonAktif.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglNonAktif);
        TtglNonAktif.setBounds(627, 94, 90, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tujuan Aktivasi :");
        jLabel20.setName("jLabel20"); // NOI18N
        PanelInput.add(jLabel20);
        jLabel20.setBounds(340, 94, 100, 23);

        cmbTujuan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTujuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Input Data", "Laporan" }));
        cmbTujuan.setName("cmbTujuan"); // NOI18N
        cmbTujuan.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbTujuan);
        cmbTujuan.setBounds(445, 94, 85, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Catatan :");
        jLabel21.setName("jLabel21"); // NOI18N
        PanelInput.add(jLabel21);
        jLabel21.setBounds(0, 122, 255, 23);

        Tcatatan.setForeground(new java.awt.Color(0, 0, 0));
        Tcatatan.setName("Tcatatan"); // NOI18N
        PanelInput.add(Tcatatan);
        Tcatatan.setBounds(258, 122, 650, 23);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbMutu.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbMutu.setComponentPopupMenu(jPopupMenu1);
        tbMutu.setName("tbMutu"); // NOI18N
        tbMutu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMutuMouseClicked(evt);
            }
        });
        tbMutu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMutuKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbMutu);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (kdIndikator.getText().trim().equals("")) {
            Valid.textKosong(kdIndikator, "kode indikator");
            kdIndikator.requestFocus();
        } else if (TnoUrut.getText().trim().equals("")) {
            Valid.textKosong(TnoUrut, "no. urut");
            TnoUrut.requestFocus();
        } else if (TnmIndikator.getText().trim().equals("")) {
            Valid.textKosong(TnmIndikator, "nama indikator");
            TnmIndikator.requestFocus();
        } else if (cmbGedung.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu gedung perawatanya...!!");
            cmbGedung.requestFocus();
        } else if (chkImu.isSelected() == false && chkImp.isSelected() == false && chkInm.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/conteng dulu salah satu jenis indikatornya...!!");
        } else if (cmbStatus.getSelectedIndex() == 0 && cmbTujuan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Pilih dengan benar tujuan aktivasi data jika status datanya aktif...!!");
            cmbTujuan.requestFocus();
        } else {
            if (cmbStatus.getSelectedIndex() == 0) {
                stts = "aktif";
                tglnonaktif = "0000-00-00";
                tujuanAktif = cmbTujuan.getSelectedItem().toString();
            } else {
                stts = "non aktif";
                tglnonaktif = Valid.SetTgl(TtglNonAktif.getSelectedItem() + "");
                tujuanAktif = "-";
            }

            if (Sequel.menyimpantf("master_indikator_nasional_mutu", "?,?,?,?,?,?,?,?,?,?", "Indikator Mutu", 10, new String[]{
                kdIndikator.getText(), TnoUrut.getText(), TnmIndikator.getText(), cmbGedung.getSelectedItem().toString(), stts,
                Ttarget.getText(), cmbJnsIndikator.getSelectedItem().toString(), tglnonaktif, tujuanAktif, Tcatatan.getText()
            }) == true) {
                emptTeks();
                BtnCariActionPerformed(null);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, kdIndikator, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (kdIndikator.getText().trim().equals("")) {
            Valid.textKosong(kdIndikator, "kode indikator");
            kdIndikator.requestFocus();
        } else if (TnoUrut.getText().trim().equals("")) {
            Valid.textKosong(TnoUrut, "no. urut");
            TnoUrut.requestFocus();
        } else if (TnmIndikator.getText().trim().equals("")) {
            Valid.textKosong(TnmIndikator, "nama indikator");
            TnmIndikator.requestFocus();
        } else if (cmbGedung.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu gedung perawatanya...!!");
            cmbGedung.requestFocus();
        } else if (cmbStatus.getSelectedIndex() == 0 && cmbTujuan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Pilih dengan benar tujuan aktivasi data jika status datanya aktif...!!");
            cmbTujuan.requestFocus();
        } else {
            if (tbMutu.getSelectedRow() > -1) {
                if (cmbStatus.getSelectedIndex() == 0) {
                    stts = "aktif";
                    tglnonaktif = "0000-00-00";
                    tujuanAktif = cmbTujuan.getSelectedItem().toString();
                } else {
                    stts = "non aktif";
                    tglnonaktif = Valid.SetTgl(TtglNonAktif.getSelectedItem() + "");
                    tujuanAktif = "-";
                }

                if (Sequel.mengedittf("master_indikator_nasional_mutu", "kd_indikator=?", "kd_indikator=?, no_urut=?, nm_indikator=?, gedung=?, "
                        + "status_data=?, target=?, jenis_indikator=?, tgl_non_aktif=?, tujuan_aktivasi=?, catatan=?", 11, new String[]{
                            kdIndikator.getText(), TnoUrut.getText(), TnmIndikator.getText(), cmbGedung.getSelectedItem().toString(), stts,
                            Ttarget.getText(), cmbJnsIndikator.getSelectedItem().toString(), tglnonaktif, tujuanAktif, Tcatatan.getText(),
                            kode
                        }) == true) {
                    emptTeks();
                    BtnCariActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbMutu.requestFocus();
            }          
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowIndikator.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
            WindowIndikator.dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

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
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, kdIndikator);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbMutuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMutuMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMutuMouseClicked

    private void tbMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMutuKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbMutuKeyPressed

    private void TnmIndikatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmIndikatorKeyPressed
        Valid.pindah(evt, TnmIndikator, cmbGedung);
    }//GEN-LAST:event_TnmIndikatorKeyPressed

    private void TnoUrutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoUrutKeyPressed
        Valid.pindah(evt, TnoUrut, TnmIndikator);
    }//GEN-LAST:event_TnoUrutKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung);
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung1);
    }//GEN-LAST:event_formWindowOpened

    private void BtnNumdenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNumdenomActionPerformed
        akses.setform("DlgIndikatorNasionalMutu");
        DlgMasterNumdemonINM mutu = new DlgMasterNumdemonINM(null, false);
        mutu.isCek();
        mutu.emptTeks();
        mutu.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        mutu.setLocationRelativeTo(internalFrame1);
        mutu.setVisible(true);
        WindowIndikator.dispose();
    }//GEN-LAST:event_BtnNumdenomActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowIndikator.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbIndikatorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIndikatorMouseClicked
        if(tabMode1.getRowCount()!=0){
            try {
                getDataSemua();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbIndikatorMouseClicked

    private void tbIndikatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIndikatorKeyPressed
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataSemua();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbIndikatorKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn1.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilSemua();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        BtnCari1ActionPerformed(null);
        emptTeksSemua();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
            TCari1.setText("");
        } else {
            Valid.pindah(evt, BtnCari1, TkdIndikator);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnGanti1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGanti1ActionPerformed
        if (TkdIndikator.getText().trim().equals("")) {
            Valid.textKosong(TkdIndikator, "Indikator Mutu");
        } else if (cmbSttsIndikator.getSelectedIndex() == 0 && cmbTujuan1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Pilih dengan benar tujuan aktivasi data jika status indikatornya aktif...!!");
            cmbTujuan1.requestFocus();
        } else {
            if (tbIndikator.getSelectedRow() > -1) {
                if (cmbSttsIndikator.getSelectedIndex() == 0) {
                    stts = "aktif";
                    tglnonaktif1 = "0000-00-00";
                    tujuanAktif1 = cmbTujuan1.getSelectedItem().toString();
                } else {
                    stts = "non aktif";
                    tglnonaktif1 = Valid.SetTgl(TtglNonAktif1.getSelectedItem() + "");
                    tujuanAktif1 = "-";
                }

                x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin semua data indikator mutu ini mau diupdate status data & targetnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    Sequel.mengedit("master_indikator_nasional_mutu", "nm_indikator='" + TnmIndi.getText() + "'", "status_data='" + stts + "', "
                            + "target='" + TtargetSemua.getText() + "', tujuan_aktivasi='" + tujuanAktif1 + "', tgl_non_aktif='" + tglnonaktif1 + "', "
                            + "catatan='" + Tcatatan1.getText() + "'");
                    emptTeksSemua();
                    BtnCari1ActionPerformed(null);
                } else {
                    emptTeksSemua();
                    BtnCari1ActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbIndikator.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGanti1ActionPerformed

    private void BtnGanti1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGanti1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnGanti1KeyPressed

    private void MnSemuaIndikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuaIndikatorActionPerformed
        WindowIndikator.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        WindowIndikator.setLocationRelativeTo(internalFrame1);
        WindowIndikator.setVisible(true);
        emptTeksSemua();
        tampilSemua();
    }//GEN-LAST:event_MnSemuaIndikatorActionPerformed

    private void chkImuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkImuActionPerformed
        if (chkImu.isSelected() == true) {
            kdIndikator.setText(Valid.autoNomer("master_indikator_nasional_mutu where kd_indikator like '%imu%'", "IMU", 6));
            cmbJnsIndikator.setSelectedIndex(1);
        } else {
            kdIndikator.setText("");
        }
    }//GEN-LAST:event_chkImuActionPerformed

    private void chkImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkImpActionPerformed
        if (chkImp.isSelected() == true) {
            kdIndikator.setText(Valid.autoNomer("master_indikator_nasional_mutu where kd_indikator like '%imp%'", "IMP", 6));
            cmbJnsIndikator.setSelectedIndex(2);
        } else {
            kdIndikator.setText("");
        }
    }//GEN-LAST:event_chkImpActionPerformed

    private void chkInmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInmActionPerformed
        if (chkInm.isSelected() == true) {
            kdIndikator.setText(Valid.autoNomer("master_indikator_nasional_mutu where kd_indikator like '%inm%'", "INM", 6));
            cmbJnsIndikator.setSelectedIndex(3);
        } else {
            kdIndikator.setText("");
        }
    }//GEN-LAST:event_chkInmActionPerformed

    private void TtargetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtargetKeyPressed
        Valid.pindah(evt, cmbStatus, cmbJnsIndikator);
    }//GEN-LAST:event_TtargetKeyPressed

    private void MnRefresKodeIndikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRefresKodeIndikatorActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin kode indikator (IMU, IMP, INM) akan direfresh..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            //refres kode indikator IMU
            Sequel.queryuBuilder("SET @urut := 0;",
                    "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'IMU%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urut := 0;", "UPDATE master_indikator_nasional_mutu "
                    + "JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('IMU', LPAD(t.new_no, 6, '0'));", "Kode Indikator IMU");

            //refres kode indikator IMP
            Sequel.queryuBuilder("SET @urut := 0;",
                    "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'IMP%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urut := 0;", "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('IMP', LPAD(t.new_no, 6, '0'));", "Kode Indikator IMP");
            
            //refres kode indikator INM
            Sequel.queryuBuilder("SET @urut := 0;", "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'INM%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urut := 0;", "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('INM', LPAD(t.new_no, 6, '0'));", "Kode Indikator INM");
            
            JOptionPane.showMessageDialog(null, "Kode Indikator IMU, IMP & INM sudah berhasil direfresh & terurut kembali..!!!!");
            tampil();
        }
    }//GEN-LAST:event_MnRefresKodeIndikatorActionPerformed

    private void MnRefresKodeIndikator1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRefresKodeIndikator1ActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin kode indikator (IMU, IMP, INM) akan direfresh..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            //refres kode indikator IMU
            Sequel.queryuBuilder("SET @urut := 0;",
                    "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'IMU%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urut := 0;", "UPDATE master_indikator_nasional_mutu "
                    + "JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('IMU', LPAD(t.new_no, 6, '0'));", "Kode Indikator IMU");

            //refres kode indikator IMP
            Sequel.queryuBuilder("SET @urut := 0;",
                    "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'IMP%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urut := 0;", "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('IMP', LPAD(t.new_no, 6, '0'));", "Kode Indikator IMP");
            
            //refres kode indikator INM
            Sequel.queryuBuilder("SET @urut := 0;", "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'INM%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urut := 0;", "UPDATE master_indikator_nasional_mutu JOIN (SELECT kd_indikator, (@urut := @urut + 1) AS new_no FROM master_indikator_nasional_mutu WHERE kd_indikator LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator "
                    + "SET master_indikator_nasional_mutu.kd_indikator = CONCAT('INM', LPAD(t.new_no, 6, '0'));", "Kode Indikator INM");
            
            JOptionPane.showMessageDialog(null, "Kode Indikator IMU, IMP & INM sudah berhasil direfresh & terurut kembali..!!!!");
            tampilSemua();
        }
    }//GEN-LAST:event_MnRefresKodeIndikator1ActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        if (cmbStatus.getSelectedIndex() == 0) {
            cmbTujuan.setEnabled(true);
            TtglNonAktif.setEnabled(false);
            cmbTujuan.requestFocus();
        } else {
            cmbTujuan.setEnabled(false);
            TtglNonAktif.setEnabled(true);
            TtglNonAktif.requestFocus();
        }
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void cmbSttsIndikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSttsIndikatorActionPerformed
        if (cmbSttsIndikator.getSelectedIndex() == 0) {
            cmbTujuan1.setEnabled(true);
            TtglNonAktif1.setEnabled(false);
            cmbTujuan1.requestFocus();
        } else {
            cmbTujuan1.setEnabled(false);
            TtglNonAktif1.setEnabled(true);
            TtglNonAktif1.requestFocus();
        }
    }//GEN-LAST:event_cmbSttsIndikatorActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgMasterIndikatorMutu dialog = new DlgMasterIndikatorMutu(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnGanti;
    private widget.Button BtnGanti1;
    private widget.Button BtnKeluar;
    private widget.Button BtnNumdenom;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnRefresKodeIndikator;
    private javax.swing.JMenuItem MnRefresKodeIndikator1;
    private javax.swing.JMenuItem MnSemuaIndikator;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    public widget.TextBox TCari1;
    private widget.TextBox Tcatatan;
    private widget.TextBox Tcatatan1;
    private widget.TextBox TkdIndikator;
    private widget.TextBox TnmIndi;
    private widget.TextBox TnmIndikator;
    private widget.TextBox TnoUrut;
    private widget.TextBox Ttarget;
    private widget.TextBox TtargetSemua;
    private widget.Tanggal TtglNonAktif;
    private widget.Tanggal TtglNonAktif1;
    private javax.swing.JDialog WindowIndikator;
    private javax.swing.ButtonGroup buttonGroup1;
    public widget.CekBox chkImp;
    public widget.CekBox chkImu;
    public widget.CekBox chkInm;
    private widget.ComboBox cmbGedung;
    private widget.ComboBox cmbGedung1;
    private widget.ComboBox cmbJnsIndikator;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbSttsIndikator;
    private widget.ComboBox cmbTujuan;
    private widget.ComboBox cmbTujuan1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.TextBox kdIndikator;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.Table tbIndikator;
    private widget.Table tbMutu;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT *, if(status_data='non aktif',date_format(tgl_non_aktif,'%d-%m-%Y'),'-') tglNonAktif FROM master_indikator_nasional_mutu WHERE "
                    + "gedung='" + cmbGedung1.getSelectedItem().toString() + "' and kd_indikator LIKE ? or "
                    + "gedung='" + cmbGedung1.getSelectedItem().toString() + "' and nm_indikator like ? or "
                    + "gedung='" + cmbGedung1.getSelectedItem().toString() + "' and target like ? or "
                    + "gedung='" + cmbGedung1.getSelectedItem().toString() + "' and jenis_indikator like ? or "
                    + "gedung='" + cmbGedung1.getSelectedItem().toString() + "' and status_data like ? ORDER BY no_urut, gedung");

            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("kd_indikator"),
                        rs.getString("no_urut"),
                        rs.getString("nm_indikator"),
                        rs.getString("gedung"),
                        rs.getString("status_data").toUpperCase(),
                        rs.getString("target"),
                        rs.getString("jenis_indikator"),
                        rs.getString("tglNonAktif"),
                        rs.getString("tujuan_aktivasi"),
                        rs.getString("catatan"),
                        rs.getString("tgl_non_aktif")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.DlgMasterIndikatorMutu.tampil() : " + e);
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
        LCount.setText("" + tabMode.getRowCount());
    }
    
    public void emptTeks() {
        kdIndikator.setText("");
        TnoUrut.setText("");
        chkImu.requestFocus();
        buttonGroup1.clearSelection();
        TnmIndikator.setText("");
        cmbGedung.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        cmbJnsIndikator.setSelectedIndex(0);
        Ttarget.setText("");
        cmbTujuan.setSelectedIndex(0);
        TtglNonAktif.setDate(new Date());
        Tcatatan.setText("");
        cmbTujuan.setEnabled(true);
        TtglNonAktif.setEnabled(false);
    }

    private void getData() {
        kode = "";
        buttonGroup1.clearSelection();
        
        if (tbMutu.getSelectedRow() != -1) {
            kode = tbMutu.getValueAt(tbMutu.getSelectedRow(), 0).toString();
            kdIndikator.setText(tbMutu.getValueAt(tbMutu.getSelectedRow(), 0).toString());
            TnoUrut.setText(tbMutu.getValueAt(tbMutu.getSelectedRow(), 1).toString());
            TnmIndikator.setText(tbMutu.getValueAt(tbMutu.getSelectedRow(), 2).toString());
            cmbGedung.setSelectedItem(tbMutu.getValueAt(tbMutu.getSelectedRow(), 3).toString());
            Ttarget.setText(tbMutu.getValueAt(tbMutu.getSelectedRow(), 5).toString());
            cmbJnsIndikator.setSelectedItem(tbMutu.getValueAt(tbMutu.getSelectedRow(), 6).toString());
            cmbTujuan.setSelectedItem(tbMutu.getValueAt(tbMutu.getSelectedRow(), 8).toString());
            Tcatatan.setText(tbMutu.getValueAt(tbMutu.getSelectedRow(), 9).toString());
            
            if (tbMutu.getValueAt(tbMutu.getSelectedRow(), 4).toString().equals("AKTIF")) {
                cmbStatus.setSelectedIndex(0);
                cmbTujuan.setEnabled(true);
                TtglNonAktif.setEnabled(false);
            } else {
                cmbStatus.setSelectedIndex(1);
                cmbTujuan.setEnabled(false);
                TtglNonAktif.setEnabled(true);
                Valid.SetTgl(TtglNonAktif, tbMutu.getValueAt(tbMutu.getSelectedRow(), 10).toString());
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getpic_kmkp());
        BtnGanti.setEnabled(akses.getpic_kmkp());
    }
    
    private void tampilSemua() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT *, if(status_data='non aktif',date_format(tgl_non_aktif,'%d-%m-%Y'),'-') tglNonAktif FROM master_indikator_nasional_mutu WHERE "
                    + "kd_indikator LIKE ? or "
                    + "nm_indikator like ? or "
                    + "target like ? or "
                    + "gedung like ? or "
                    + "jenis_indikator like ? or "
                    + "status_data like ? ORDER BY no_urut, gedung");

            try {
                ps1.setString(1, "%" + TCari1.getText().trim() + "%");
                ps1.setString(2, "%" + TCari1.getText().trim() + "%");
                ps1.setString(3, "%" + TCari1.getText().trim() + "%");
                ps1.setString(4, "%" + TCari1.getText().trim() + "%");
                ps1.setString(5, "%" + TCari1.getText().trim() + "%");
                ps1.setString(6, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("kd_indikator"),
                        rs1.getString("no_urut"),
                        rs1.getString("nm_indikator"),
                        rs1.getString("gedung"),
                        rs1.getString("status_data").toUpperCase(),
                        rs1.getString("target"),
                        rs1.getString("jenis_indikator"),                        
                        rs1.getString("tglNonAktif"),
                        rs1.getString("tujuan_aktivasi"),
                        rs1.getString("catatan"),
                        rs1.getString("tgl_non_aktif")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.DlgMasterIndikatorMutu.tampilSemua() : " + e);
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
        LCount1.setText("" + tabMode1.getRowCount());
    }
    
    private void emptTeksSemua() {
        TkdIndikator.setText("");
        TnmIndi.setText("");
        cmbSttsIndikator.setSelectedIndex(0);
        TtargetSemua.setText("");
        cmbSttsIndikator.requestFocus();
        
        cmbTujuan1.setSelectedIndex(0);
        TtglNonAktif1.setDate(new Date());
        Tcatatan1.setText("");
        cmbTujuan1.setEnabled(true);
        TtglNonAktif1.setEnabled(false);
    }

    private void getDataSemua() {
        stts = "";
        if (tbIndikator.getSelectedRow() != -1) {
            TkdIndikator.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 0).toString());
            TnmIndi.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 2).toString());
            TtargetSemua.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 5).toString());
            cmbTujuan1.setSelectedItem(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 8).toString());
            Tcatatan1.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 9).toString());

            if (tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 4).toString().equals("AKTIF")) {
                cmbSttsIndikator.setSelectedIndex(0);
                cmbTujuan1.setEnabled(true);
                TtglNonAktif1.setEnabled(false);
            } else {
                cmbSttsIndikator.setSelectedIndex(1);
                cmbTujuan1.setEnabled(false);
                TtglNonAktif1.setEnabled(true);
                Valid.SetTgl(TtglNonAktif1, tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 10).toString());
            }
        }
    }
}
