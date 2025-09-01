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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class DlgMasterNumdemonINM extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0, n = 0;
    private String kode = "", stts = "", kdINM = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgMasterNumdemonINM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "Kode ND", "No. Urut", "Kode Indikator", "Nama Indikator", "Jenis ND", "Kalimat Deskripsi", 
            "Ruang Perawatan/Gedung", "Status Data ND", "Jenis Indikator"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMutu.setModel(tabMode);
        tbMutu.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMutu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbMutu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(600);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(600);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            }
        }
        tbMutu.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbMutu.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbMutu.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbMutu.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "Cek", "Kode ND", "No. Urut", "Nama Numerator", "Nama Indikator", "Rg./Unit/Inst./Gedung", "Status Data ND", "Jenis Indikator"}) {
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
        
        tbNumerator.setModel(tabMode1);
        tbNumerator.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNumerator.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbNumerator.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(600);
            } else if (i == 4) {
                column.setPreferredWidth(600);
            } else if (i == 5) {
                column.setPreferredWidth(160);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            }
        }
        tbNumerator.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbNumerator.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbNumerator.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "Cek", "Kode ND", "No. Urut", "Nama Denominator", "Nama Indikator", "Rg./Unit/Inst./Gedung", "Status Data ND", "Jenis Indikator"}) {
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
        
        tbDenominator.setModel(tabMode2);
        tbDenominator.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDenominator.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbDenominator.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(600);
            } else if (i == 4) {
                column.setPreferredWidth(600);
            } else if (i == 5) {
                column.setPreferredWidth(160);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            }
        }
        tbDenominator.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbDenominator.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbDenominator.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TnoUrut.setDocument(new batasInput((byte) 3).getOnlyAngka(TnoUrut));
        TnmNumdenom.setDocument(new batasInput((int) 255).getKata(TnmNumdenom));        
        
//        if(koneksiDB.cariCepat().equals("aktif")){
//            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
//                @Override
//                public void insertUpdate(DocumentEvent e) {tampil();}
//                @Override
//                public void removeUpdate(DocumentEvent e) {tampil();}
//                @Override
//                public void changedUpdate(DocumentEvent e) {tampil();}
//            });
//        }
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
        MnNumerator = new javax.swing.JMenuItem();
        MnDenominator = new javax.swing.JMenuItem();
        MnRefresKode = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnContengNum = new javax.swing.JMenuItem();
        MnHapusContengNum = new javax.swing.JMenuItem();
        MnRefresKodeNum = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnContengDen = new javax.swing.JMenuItem();
        MnHapusContengDen = new javax.swing.JMenuItem();
        MnRefresKodeDen = new javax.swing.JMenuItem();
        WindowNumerator = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel16 = new widget.Label();
        cmbSttsNumerator = new widget.ComboBox();
        Scroll1 = new widget.ScrollPane();
        tbNumerator = new widget.Table();
        panelisi4 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel18 = new widget.Label();
        LCount1 = new widget.Label();
        BtnAll1 = new widget.Button();
        BtnGanti1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowDenominator = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelisi5 = new widget.panelisi();
        jLabel17 = new widget.Label();
        cmbSttsDenominator = new widget.ComboBox();
        Scroll2 = new widget.ScrollPane();
        tbDenominator = new widget.Table();
        panelisi6 = new widget.panelisi();
        jLabel15 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel19 = new widget.Label();
        LCount2 = new widget.Label();
        BtnAll2 = new widget.Button();
        BtnGanti2 = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel13 = new widget.Label();
        cmbGedung1 = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        jLabel4 = new widget.Label();
        kdNumdenom = new widget.TextBox();
        jLabel9 = new widget.Label();
        TnmNumdenom = new widget.TextBox();
        jLabel5 = new widget.Label();
        TnoUrut = new widget.TextBox();
        jLabel10 = new widget.Label();
        cmbIndikator = new widget.ComboBox();
        jLabel11 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel12 = new widget.Label();
        cmbGedung = new widget.ComboBox();
        jLabel14 = new widget.Label();
        cmbJnsIndikator = new widget.ComboBox();
        TsttsIndikator = new widget.TextBox();
        BtnSttsIndikator = new widget.Button();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbMutu = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnNumerator.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNumerator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNumerator.setText("Semua Numerator Mutu");
        MnNumerator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNumerator.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNumerator.setIconTextGap(5);
        MnNumerator.setName("MnNumerator"); // NOI18N
        MnNumerator.setPreferredSize(new java.awt.Dimension(175, 26));
        MnNumerator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNumeratorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnNumerator);

        MnDenominator.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDenominator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDenominator.setText("Semua Denominator Mutu");
        MnDenominator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDenominator.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDenominator.setIconTextGap(5);
        MnDenominator.setName("MnDenominator"); // NOI18N
        MnDenominator.setPreferredSize(new java.awt.Dimension(175, 26));
        MnDenominator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDenominatorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDenominator);

        MnRefresKode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRefresKode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnRefresKode.setText("Refresh Kode ND");
        MnRefresKode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRefresKode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRefresKode.setIconTextGap(5);
        MnRefresKode.setName("MnRefresKode"); // NOI18N
        MnRefresKode.setPreferredSize(new java.awt.Dimension(175, 26));
        MnRefresKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRefresKodeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRefresKode);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnContengNum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengNum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengNum.setText("Conteng Semua");
        MnContengNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengNum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengNum.setIconTextGap(5);
        MnContengNum.setName("MnContengNum"); // NOI18N
        MnContengNum.setPreferredSize(new java.awt.Dimension(160, 26));
        MnContengNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengNumActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnContengNum);

        MnHapusContengNum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusContengNum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusContengNum.setText("Hapus Semua Conteng");
        MnHapusContengNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusContengNum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusContengNum.setIconTextGap(5);
        MnHapusContengNum.setName("MnHapusContengNum"); // NOI18N
        MnHapusContengNum.setPreferredSize(new java.awt.Dimension(160, 26));
        MnHapusContengNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengNumActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusContengNum);

        MnRefresKodeNum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRefresKodeNum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnRefresKodeNum.setText("Refresh Kode ND");
        MnRefresKodeNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRefresKodeNum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRefresKodeNum.setIconTextGap(5);
        MnRefresKodeNum.setName("MnRefresKodeNum"); // NOI18N
        MnRefresKodeNum.setPreferredSize(new java.awt.Dimension(160, 26));
        MnRefresKodeNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRefresKodeNumActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnRefresKodeNum);

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnContengDen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengDen.setText("Conteng Semua");
        MnContengDen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengDen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengDen.setIconTextGap(5);
        MnContengDen.setName("MnContengDen"); // NOI18N
        MnContengDen.setPreferredSize(new java.awt.Dimension(160, 26));
        MnContengDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengDenActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnContengDen);

        MnHapusContengDen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusContengDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusContengDen.setText("Hapus Semua Conteng");
        MnHapusContengDen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusContengDen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusContengDen.setIconTextGap(5);
        MnHapusContengDen.setName("MnHapusContengDen"); // NOI18N
        MnHapusContengDen.setPreferredSize(new java.awt.Dimension(160, 26));
        MnHapusContengDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengDenActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnHapusContengDen);

        MnRefresKodeDen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRefresKodeDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnRefresKodeDen.setText("Refresh Kode ND");
        MnRefresKodeDen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRefresKodeDen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRefresKodeDen.setIconTextGap(5);
        MnRefresKodeDen.setName("MnRefresKodeDen"); // NOI18N
        MnRefresKodeDen.setPreferredSize(new java.awt.Dimension(160, 26));
        MnRefresKodeDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRefresKodeDenActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnRefresKodeDen);

        WindowNumerator.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowNumerator.setName("WindowNumerator"); // NOI18N
        WindowNumerator.setUndecorated(true);
        WindowNumerator.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Semua Numerator Mutu Layanan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 45));
        panelisi3.setLayout(null);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Status Numerator : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelisi3.add(jLabel16);
        jLabel16.setBounds(0, 10, 130, 23);

        cmbSttsNumerator.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsNumerator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Non Aktif" }));
        cmbSttsNumerator.setName("cmbSttsNumerator"); // NOI18N
        cmbSttsNumerator.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi3.add(cmbSttsNumerator);
        cmbSttsNumerator.setBounds(133, 10, 80, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbNumerator.setAutoCreateRowSorter(true);
        tbNumerator.setToolTipText("Silahkan klik conteng untuk memilih data yang akan diupdate");
        tbNumerator.setComponentPopupMenu(jPopupMenu2);
        tbNumerator.setName("tbNumerator"); // NOI18N
        Scroll1.setViewportView(tbNumerator);

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

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Record :");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(jLabel18);

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

        WindowNumerator.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowDenominator.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDenominator.setName("WindowDenominator"); // NOI18N
        WindowDenominator.setUndecorated(true);
        WindowDenominator.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Semua Denominator Mutu Layanan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(new java.awt.BorderLayout());

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 45));
        panelisi5.setLayout(null);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Status Denominator : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi5.add(jLabel17);
        jLabel17.setBounds(0, 10, 130, 23);

        cmbSttsDenominator.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsDenominator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Non Aktif" }));
        cmbSttsDenominator.setName("cmbSttsDenominator"); // NOI18N
        cmbSttsDenominator.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi5.add(cmbSttsDenominator);
        cmbSttsDenominator.setBounds(133, 10, 80, 23);

        internalFrame4.add(panelisi5, java.awt.BorderLayout.PAGE_START);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbDenominator.setAutoCreateRowSorter(true);
        tbDenominator.setToolTipText("Silahkan klik conteng untuk memilih data yang akan diupdate");
        tbDenominator.setComponentPopupMenu(jPopupMenu3);
        tbDenominator.setName("tbDenominator"); // NOI18N
        Scroll2.setViewportView(tbDenominator);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Key Word :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi6.add(jLabel15);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelisi6.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setText("Tampilkan Data");
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
        panelisi6.add(BtnCari2);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Record :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi6.add(jLabel19);

        LCount2.setForeground(new java.awt.Color(0, 0, 0));
        LCount2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount2.setText("0");
        LCount2.setName("LCount2"); // NOI18N
        LCount2.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi6.add(LCount2);

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('M');
        BtnAll2.setText("Semua");
        BtnAll2.setToolTipText("Alt+M");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelisi6.add(BtnAll2);

        BtnGanti2.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti2.setMnemonic('G');
        BtnGanti2.setText("Ganti");
        BtnGanti2.setToolTipText("Alt+G");
        BtnGanti2.setName("BtnGanti2"); // NOI18N
        BtnGanti2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGanti2ActionPerformed(evt);
            }
        });
        BtnGanti2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGanti2KeyPressed(evt);
            }
        });
        panelisi6.add(BtnGanti2);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setMnemonic('U');
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        panelisi6.add(BtnCloseIn2);

        internalFrame4.add(panelisi6, java.awt.BorderLayout.PAGE_END);

        WindowDenominator.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Master Numerator Denominator Indikator Nasional Mutu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Rg. Perawatan/Unit/Inst./Bidang/Sub :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(210, 23));
        panelGlass10.add(jLabel13);

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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 156));
        PanelInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Kode ND : ");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 115, 23);

        kdNumdenom.setForeground(new java.awt.Color(0, 0, 0));
        kdNumdenom.setName("kdNumdenom"); // NOI18N
        kdNumdenom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdNumdenomKeyPressed(evt);
            }
        });
        PanelInput.add(kdNumdenom);
        kdNumdenom.setBounds(118, 10, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Deskripsi Kalimat : ");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 94, 115, 23);

        TnmNumdenom.setForeground(new java.awt.Color(0, 0, 0));
        TnmNumdenom.setName("TnmNumdenom"); // NOI18N
        TnmNumdenom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmNumdenomKeyPressed(evt);
            }
        });
        PanelInput.add(TnmNumdenom);
        TnmNumdenom.setBounds(118, 94, 800, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Urut :");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(210, 10, 60, 23);

        TnoUrut.setForeground(new java.awt.Color(0, 0, 0));
        TnoUrut.setName("TnoUrut"); // NOI18N
        TnoUrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoUrutKeyPressed(evt);
            }
        });
        PanelInput.add(TnoUrut);
        TnoUrut.setBounds(275, 10, 50, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Nama Indikator : ");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(0, 66, 115, 23);

        cmbIndikator.setForeground(new java.awt.Color(0, 0, 0));
        cmbIndikator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbIndikator.setName("cmbIndikator"); // NOI18N
        cmbIndikator.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbIndikator);
        cmbIndikator.setBounds(118, 66, 800, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Status ND :");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(215, 122, 80, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbStatus);
        cmbStatus.setBounds(300, 122, 80, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Rg. Perawatan/Unit/Inst./Bidang/Sub :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 215, 23);

        cmbGedung.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "RAWAT JALAN", "IBS", "AR RAUDAH", "HEMODIALISA" }));
        cmbGedung.setName("cmbGedung"); // NOI18N
        cmbGedung.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbGedung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbGedungMouseReleased(evt);
            }
        });
        cmbGedung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGedungActionPerformed(evt);
            }
        });
        PanelInput.add(cmbGedung);
        cmbGedung.setBounds(222, 38, 190, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jenis ND : ");
        jLabel14.setName("jLabel14"); // NOI18N
        PanelInput.add(jLabel14);
        jLabel14.setBounds(0, 122, 115, 23);

        cmbJnsIndikator.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsIndikator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Numerator", "Denominator" }));
        cmbJnsIndikator.setName("cmbJnsIndikator"); // NOI18N
        cmbJnsIndikator.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbJnsIndikator);
        cmbJnsIndikator.setBounds(118, 122, 95, 23);

        TsttsIndikator.setEditable(false);
        TsttsIndikator.setForeground(new java.awt.Color(0, 0, 0));
        TsttsIndikator.setName("TsttsIndikator"); // NOI18N
        PanelInput.add(TsttsIndikator);
        TsttsIndikator.setBounds(1075, 66, 100, 23);

        BtnSttsIndikator.setForeground(new java.awt.Color(0, 0, 0));
        BtnSttsIndikator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnSttsIndikator.setText("Status Indikator");
        BtnSttsIndikator.setName("BtnSttsIndikator"); // NOI18N
        BtnSttsIndikator.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSttsIndikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSttsIndikatorActionPerformed(evt);
            }
        });
        PanelInput.add(BtnSttsIndikator);
        BtnSttsIndikator.setBounds(925, 64, 140, 26);

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
        if (kdNumdenom.getText().trim().equals("")) {
            Valid.textKosong(kdNumdenom, "kode numerator denominator/numdenom");
            kdNumdenom.requestFocus();
        } else if (TnoUrut.getText().trim().equals("")) {
            Valid.textKosong(TnoUrut, "no. urut");
            TnoUrut.requestFocus();
        } else if (TnmNumdenom.getText().trim().equals("")) {
            Valid.textKosong(TnmNumdenom, "nama numerator denominator/numdenom");
            TnmNumdenom.requestFocus();
        } else if (cmbGedung.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu gedung perawatanya...!!");
            cmbGedung.requestFocus();
        } else {
            if (cmbStatus.getSelectedIndex() == 0) {
                stts = "aktif";
            } else {
                stts = "non aktif";
            }

            kdINM = Sequel.cariIsi("select kd_indikator from master_indikator_nasional_mutu where gedung='" + cmbGedung.getSelectedItem().toString() + "' and "
                    + "nm_indikator='" + cmbIndikator.getSelectedItem().toString() + "'");

            if (Sequel.menyimpantf("master_numdemon_indikator_nasional_mutu", "?,?,?,?,?,?", "Numerator Demonimator Indikator Mutu", 6, new String[]{
                kdNumdenom.getText(), kdINM, TnoUrut.getText(), TnmNumdenom.getText(), stts, cmbJnsIndikator.getSelectedItem().toString()
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
            Valid.pindah(evt, kdNumdenom, BtnBatal);
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
        if (kdNumdenom.getText().trim().equals("")) {
            Valid.textKosong(kdNumdenom, "kode numerator denominator/numdenom");
            kdNumdenom.requestFocus();
        } else if (TnoUrut.getText().trim().equals("")) {
            Valid.textKosong(TnoUrut, "no. urut");
            TnoUrut.requestFocus();
        } else if (TnmNumdenom.getText().trim().equals("")) {
            Valid.textKosong(TnmNumdenom, "nama numerator denominator/numdenom");
            TnmNumdenom.requestFocus();
        } else if (cmbGedung.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu gedung perawatanya...!!");
            cmbGedung.requestFocus();
        } else {
            if (tbMutu.getSelectedRow() > -1) {
                if (cmbStatus.getSelectedIndex() == 0) {
                    stts = "aktif";
                } else {
                    stts = "non aktif";
                }

                kdINM = Sequel.cariIsi("select kd_indikator from master_indikator_nasional_mutu where gedung='" + cmbGedung.getSelectedItem().toString() + "' and "
                        + "nm_indikator='" + cmbIndikator.getSelectedItem().toString() + "'");

                if (Sequel.mengedittf("master_numdemon_indikator_nasional_mutu", "kd_numdemon=?", "kd_numdemon=?, kd_indikator=?, no_urut=?, "
                        + "nm_numdemon=?, status_data=?, jenis_numdemon=?", 7, new String[]{
                            kdNumdenom.getText(), kdINM, TnoUrut.getText(), TnmNumdenom.getText(), stts, cmbJnsIndikator.getSelectedItem().toString(),
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowNumerator.dispose();
        WindowDenominator.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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
            Valid.pindah(evt, BtnCari, kdNumdenom);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbMutuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMutuMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMutuMouseClicked

    private void tbMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMutuKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbMutuKeyPressed

    private void TnoUrutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoUrutKeyPressed
        Valid.pindah(evt, TnoUrut, cmbGedung);
    }//GEN-LAST:event_TnoUrutKeyPressed

    private void cmbGedungMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbGedungMouseReleased
        AutoCompleteDecorator.decorate(cmbGedung);
    }//GEN-LAST:event_cmbGedungMouseReleased

    private void cmbGedungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGedungActionPerformed
        cmbIndikator.removeAllItems();
        Sequel.cariIsiComboDB("SELECT nm_indikator from master_indikator_nasional_mutu "
            + "WHERE gedung='" + cmbGedung.getSelectedItem().toString() + "' order by no_urut", cmbIndikator);
    }//GEN-LAST:event_cmbGedungActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and nm_gedung not like '%ar-rau%' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung);
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and nm_gedung not like '%ar-rau%' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung1);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TnmNumdenomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmNumdenomKeyPressed
        Valid.pindah(evt, cmbIndikator, cmbJnsIndikator);
    }//GEN-LAST:event_TnmNumdenomKeyPressed

    private void BtnSttsIndikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSttsIndikatorActionPerformed
        TsttsIndikator.setText(Sequel.cariIsi("select ifnull(status_data,'') from master_indikator_nasional_mutu where "
            + "gedung like '%" + cmbGedung.getSelectedItem() + "%' and nm_indikator like '%" + cmbIndikator.getSelectedItem() + "%'").toUpperCase());
    }//GEN-LAST:event_BtnSttsIndikatorActionPerformed

    private void kdNumdenomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdNumdenomKeyPressed
        Valid.pindah(evt, kdNumdenom, TnoUrut);
    }//GEN-LAST:event_kdNumdenomKeyPressed

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
        tampilNumerator();
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
        emptTeksNum();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
            TCari1.setText("");
        } 
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnGanti1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGanti1ActionPerformed
        n = 0;
        for (i = 0; i < tbNumerator.getRowCount(); i++) {
            if (tbNumerator.getValueAt(i, 0).toString().equals("true")) {
                n++;
            }
        }

        if (n == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan conteng dulu pada tabel nama numerator mutu layanan yang dipilih..!!!!");
            tbNumerator.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin nama numerator mutu layanan yang dipilih mau diupdate status data..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (cmbSttsNumerator.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu status numeratornya dulu dengan benar..!!!!");
                    cmbSttsNumerator.requestFocus();
                } else {
                    if (cmbSttsNumerator.getSelectedIndex() == 1) {
                        stts = "aktif";
                    } else if (cmbSttsNumerator.getSelectedIndex() == 2) {
                        stts = "non aktif";
                    }

                    try {
                        for (i = 0; i < tbNumerator.getRowCount(); i++) {
                            if (tbNumerator.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.mengedit("master_numdemon_indikator_nasional_mutu", "kd_numdemon='" + tbNumerator.getValueAt(i, 1).toString() + "'",
                                        "status_data='" + stts + "'");
                            }
                        }
                        emptTeksNum();
                        BtnCari1ActionPerformed(null);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            } else {
                emptTeksNum();
                BtnCari1ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnGanti1ActionPerformed

    private void BtnGanti1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGanti1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGanti1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnGanti1KeyPressed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowNumerator.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void MnNumeratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNumeratorActionPerformed
        WindowNumerator.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        WindowNumerator.setLocationRelativeTo(internalFrame1);
        WindowNumerator.setVisible(true);
        emptTeksNum();
        tampilNumerator();
    }//GEN-LAST:event_MnNumeratorActionPerformed

    private void MnContengNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengNumActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data numerator mutu masih kosong...!!!!");
            tbNumerator.requestFocus();
        } else {
            tampilNumerator();
            for (i = 0; i < tbNumerator.getRowCount(); i++) {
                tbNumerator.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengNumActionPerformed

    private void MnHapusContengNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengNumActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data numerator mutu masih kosong...!!!!");
            tbNumerator.requestFocus();
        } else {
            tampilNumerator();

            for (i = 0; i < tbNumerator.getRowCount(); i++) {
                tbNumerator.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengNumActionPerformed

    private void MnDenominatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDenominatorActionPerformed
        WindowDenominator.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        WindowDenominator.setLocationRelativeTo(internalFrame1);
        WindowDenominator.setVisible(true);
        emptTeksDen();
        tampilDenominator();
    }//GEN-LAST:event_MnDenominatorActionPerformed

    private void MnContengDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengDenActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data denominator mutu masih kosong...!!!!");
            tbDenominator.requestFocus();
        } else {
            tampilDenominator();
            for (i = 0; i < tbDenominator.getRowCount(); i++) {
                tbDenominator.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengDenActionPerformed

    private void MnHapusContengDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengDenActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data denominator mutu masih kosong...!!!!");
            tbDenominator.requestFocus();
        } else {
            tampilDenominator();

            for (i = 0; i < tbDenominator.getRowCount(); i++) {
                tbDenominator.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengDenActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn2.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilDenominator();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll2);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari2.setText("");
        BtnCari2ActionPerformed(null);
        emptTeksDen();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
            TCari2.setText("");
        } 
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnGanti2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGanti2ActionPerformed
        n = 0;
        for (i = 0; i < tbDenominator.getRowCount(); i++) {
            if (tbDenominator.getValueAt(i, 0).toString().equals("true")) {
                n++;
            }
        }

        if (n == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan conteng dulu pada tabel nama denominator mutu layanan yang dipilih..!!!!");
            tbDenominator.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin nama denominator mutu layanan yang dipilih mau diupdate status data..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (cmbSttsDenominator.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu status denominatornya dulu dengan benar..!!!!");
                    cmbSttsDenominator.requestFocus();
                } else {
                    if (cmbSttsDenominator.getSelectedIndex() == 1) {
                        stts = "aktif";
                    } else if (cmbSttsDenominator.getSelectedIndex() == 2) {
                        stts = "non aktif";
                    }

                    try {
                        for (i = 0; i < tbDenominator.getRowCount(); i++) {
                            if (tbDenominator.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.mengedit("master_numdemon_indikator_nasional_mutu", "kd_numdemon='" + tbDenominator.getValueAt(i, 1).toString() + "'",
                                        "status_data='" + stts + "'");
                            }
                        }
                        emptTeksDen();
                        BtnCari2ActionPerformed(null);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            } else {
                emptTeksDen();
                BtnCari2ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnGanti2ActionPerformed

    private void BtnGanti2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGanti2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGanti2ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnGanti2KeyPressed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        WindowDenominator.dispose();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void MnRefresKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRefresKodeActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin kode numerator & denominator akan direfresh..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            Sequel.queryuBuilder("SET @urutan := 0;", "UPDATE master_numdemon_indikator_nasional_mutu JOIN (SELECT kd_numdemon, (@urutan := @urutan + 1) AS new_no FROM master_numdemon_indikator_nasional_mutu ORDER BY no_urut) "
                    + "t ON master_numdemon_indikator_nasional_mutu.kd_numdemon = t.kd_numdemon "
                    + "SET master_numdemon_indikator_nasional_mutu.kd_numdemon = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urutan := 0;", "UPDATE master_numdemon_indikator_nasional_mutu JOIN (SELECT kd_numdemon, (@urutan := @urutan + 1) AS new_no FROM master_numdemon_indikator_nasional_mutu WHERE kd_numdemon LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_numdemon_indikator_nasional_mutu.kd_numdemon = t.kd_numdemon "
                    + "SET master_numdemon_indikator_nasional_mutu.kd_numdemon = CONCAT('ND', LPAD(t.new_no, 6, '0'));", "Kode Numerator & Denominator");
            
            JOptionPane.showMessageDialog(null, "Kode Numerator & Denominator sudah berhasil direfresh & terurut kembali..!!!!");
            tampil();
        }
    }//GEN-LAST:event_MnRefresKodeActionPerformed

    private void MnRefresKodeNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRefresKodeNumActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin kode numerator & denominator akan direfresh..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            Sequel.queryuBuilder("SET @urutan := 0;", "UPDATE master_numdemon_indikator_nasional_mutu JOIN (SELECT kd_numdemon, (@urutan := @urutan + 1) AS new_no FROM master_numdemon_indikator_nasional_mutu ORDER BY no_urut) "
                    + "t ON master_numdemon_indikator_nasional_mutu.kd_numdemon = t.kd_numdemon "
                    + "SET master_numdemon_indikator_nasional_mutu.kd_numdemon = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urutan := 0;", "UPDATE master_numdemon_indikator_nasional_mutu JOIN (SELECT kd_numdemon, (@urutan := @urutan + 1) AS new_no FROM master_numdemon_indikator_nasional_mutu WHERE kd_numdemon LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_numdemon_indikator_nasional_mutu.kd_numdemon = t.kd_numdemon "
                    + "SET master_numdemon_indikator_nasional_mutu.kd_numdemon = CONCAT('ND', LPAD(t.new_no, 6, '0'));", "Kode Numerator & Denominator");

            JOptionPane.showMessageDialog(null, "Kode Numerator & Denominator sudah berhasil direfresh & terurut kembali..!!!!");
            tampilNumerator();
        }
    }//GEN-LAST:event_MnRefresKodeNumActionPerformed

    private void MnRefresKodeDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRefresKodeDenActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin kode numerator & denominator akan direfresh..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            Sequel.queryuBuilder("SET @urutan := 0;", "UPDATE master_numdemon_indikator_nasional_mutu JOIN (SELECT kd_numdemon, (@urutan := @urutan + 1) AS new_no FROM master_numdemon_indikator_nasional_mutu ORDER BY no_urut) "
                    + "t ON master_numdemon_indikator_nasional_mutu.kd_numdemon = t.kd_numdemon "
                    + "SET master_numdemon_indikator_nasional_mutu.kd_numdemon = CONCAT('TMP', LPAD(t.new_no, 6, '0'));",
                    "SET @urutan := 0;", "UPDATE master_numdemon_indikator_nasional_mutu JOIN (SELECT kd_numdemon, (@urutan := @urutan + 1) AS new_no FROM master_numdemon_indikator_nasional_mutu WHERE kd_numdemon LIKE 'TMP%' ORDER BY no_urut) "
                    + "t ON master_numdemon_indikator_nasional_mutu.kd_numdemon = t.kd_numdemon "
                    + "SET master_numdemon_indikator_nasional_mutu.kd_numdemon = CONCAT('ND', LPAD(t.new_no, 6, '0'));", "Kode Numerator & Denominator");

            JOptionPane.showMessageDialog(null, "Kode Numerator & Denominator sudah berhasil direfresh & terurut kembali..!!!!");
            tampilDenominator();
        }
    }//GEN-LAST:event_MnRefresKodeDenActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgMasterNumdemonINM dialog = new DlgMasterNumdemonINM(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll2;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnGanti;
    private widget.Button BtnGanti1;
    private widget.Button BtnGanti2;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button BtnSttsIndikator;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Label LCount2;
    private javax.swing.JMenuItem MnContengDen;
    private javax.swing.JMenuItem MnContengNum;
    private javax.swing.JMenuItem MnDenominator;
    private javax.swing.JMenuItem MnHapusContengDen;
    private javax.swing.JMenuItem MnHapusContengNum;
    private javax.swing.JMenuItem MnNumerator;
    private javax.swing.JMenuItem MnRefresKode;
    private javax.swing.JMenuItem MnRefresKodeDen;
    private javax.swing.JMenuItem MnRefresKodeNum;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    public widget.TextBox TCari1;
    public widget.TextBox TCari2;
    private widget.TextBox TnmNumdenom;
    private widget.TextBox TnoUrut;
    private widget.TextBox TsttsIndikator;
    private javax.swing.JDialog WindowDenominator;
    private javax.swing.JDialog WindowNumerator;
    private widget.ComboBox cmbGedung;
    private widget.ComboBox cmbGedung1;
    private widget.ComboBox cmbIndikator;
    private widget.ComboBox cmbJnsIndikator;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbSttsDenominator;
    private widget.ComboBox cmbSttsNumerator;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private widget.TextBox kdNumdenom;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.Table tbDenominator;
    private widget.Table tbMutu;
    private widget.Table tbNumerator;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT m1.*, m2.kd_indikator, m2.nm_indikator, m2.gedung, m2.jenis_indikator FROM master_numdemon_indikator_nasional_mutu m1 "
                    +"inner join master_indikator_nasional_mutu m2 on m1.kd_indikator=m2.kd_indikator WHERE "
                    + "m2.gedung='" + cmbGedung1.getSelectedItem().toString() + "' and m2.kd_indikator like ? or "
                    + "m2.gedung='" + cmbGedung1.getSelectedItem().toString() + "' and m2.nm_indikator like ? or "
                    + "m2.gedung='" + cmbGedung1.getSelectedItem().toString() + "' and m1.kd_numdemon like ? or "
                    + "m2.gedung='" + cmbGedung1.getSelectedItem().toString() + "' and m1.nm_numdemon like ? or "
                    + "m2.gedung='" + cmbGedung1.getSelectedItem().toString() + "' and m1.jenis_numdemon like ? or "
                    + "m2.gedung='" + cmbGedung1.getSelectedItem().toString() + "' and m1.status_data like ? ORDER BY m1.no_urut, m2.gedung");

            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("kd_numdemon"),
                        rs.getString("no_urut"),
                        rs.getString("kd_indikator"),
                        rs.getString("nm_indikator"),
                        rs.getString("jenis_numdemon"),
                        rs.getString("nm_numdemon"),
                        rs.getString("gedung"),
                        rs.getString("status_data").toUpperCase(),
                        rs.getString("jenis_indikator")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.DlgMasterNumdemonINM.tampil() : " + e);
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
        kdNumdenom.setText(Valid.autoNomer("master_numdemon_indikator_nasional_mutu", "ND", 6));
        TnoUrut.setText("");
        TnoUrut.requestFocus();
        TnmNumdenom.setText("");
        cmbGedung.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0); 
        cmbJnsIndikator.setSelectedIndex(0);
        kdINM = "";
        stts = "";
        TsttsIndikator.setText("");
    }

    private void getData() {
        kode = "";
        stts = "";
        kdINM = "";
        
        if (tbMutu.getSelectedRow() != -1) {
            kode = tbMutu.getValueAt(tbMutu.getSelectedRow(), 0).toString();
            kdNumdenom.setText(tbMutu.getValueAt(tbMutu.getSelectedRow(), 0).toString());
            TnoUrut.setText(tbMutu.getValueAt(tbMutu.getSelectedRow(), 1).toString());
            kdINM = tbMutu.getValueAt(tbMutu.getSelectedRow(), 2).toString();            
            TnmNumdenom.setText(tbMutu.getValueAt(tbMutu.getSelectedRow(), 5).toString());
            cmbGedung.setSelectedItem(tbMutu.getValueAt(tbMutu.getSelectedRow(), 6).toString());
            cmbIndikator.setSelectedItem(tbMutu.getValueAt(tbMutu.getSelectedRow(), 3).toString());
            cmbJnsIndikator.setSelectedItem(tbMutu.getValueAt(tbMutu.getSelectedRow(), 4).toString());
            TsttsIndikator.setText(Sequel.cariIsi("select status_data from master_indikator_nasional_mutu where kd_indikator='" + kdINM + "'").toUpperCase());
            
            if (tbMutu.getValueAt(tbMutu.getSelectedRow(), 7).toString().equals("AKTIF")) {
                cmbStatus.setSelectedIndex(0);
            } else {
                cmbStatus.setSelectedIndex(1);
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getpic_kmkp());
        BtnGanti.setEnabled(akses.getpic_kmkp());
    }
    
    private void tampilNumerator() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT mn.*, mi.*, mn.status_data sttsDataNum FROM master_numdemon_indikator_nasional_mutu mn "
                    + "inner join master_indikator_nasional_mutu mi on mi.kd_indikator=mn.kd_indikator WHERE "
                    + "mn.jenis_numdemon='Numerator' and mn.kd_numdemon LIKE ? or "
                    + "mn.jenis_numdemon='Numerator' and mn.kd_indikator like ? or "
                    + "mn.jenis_numdemon='Numerator' and mn.nm_numdemon like ? or "                    
                    + "mn.jenis_numdemon='Numerator' and mn.status_data like ? or "
                    + "mn.jenis_numdemon='Numerator' and mi.nm_indikator like ? or "
                    + "mn.jenis_numdemon='Numerator' and mi.gedung like ? ORDER BY mn.no_urut, mi.gedung");

            try {
                ps1.setString(1, "%" + TCari1.getText().trim() + "%");
                ps1.setString(2, "%" + TCari1.getText().trim() + "%");
                ps1.setString(3, "%" + TCari1.getText().trim() + "%");
                ps1.setString(4, "%" + TCari1.getText().trim() + "%");
                ps1.setString(5, "%" + TCari1.getText().trim() + "%");
                ps1.setString(6, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();                
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        false,
                        rs1.getString("kd_numdemon"),
                        rs1.getString("no_urut"),
                        rs1.getString("nm_numdemon"),
                        rs1.getString("nm_indikator"),
                        rs1.getString("gedung"),
                        rs1.getString("sttsDataNum").toUpperCase(),
                        rs1.getString("jenis_indikator")
                    });
                }                
            } catch (Exception e) {
                System.out.println("tampilNumerator() : " + e);
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
    
    private void tampilDenominator() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT mn.*, mi.*, mn.status_data sttsDataNum FROM master_numdemon_indikator_nasional_mutu mn "
                    + "inner join master_indikator_nasional_mutu mi on mi.kd_indikator=mn.kd_indikator WHERE "
                    + "mn.jenis_numdemon='Denominator' and mn.kd_numdemon LIKE ? or "
                    + "mn.jenis_numdemon='Denominator' and mn.kd_indikator like ? or "
                    + "mn.jenis_numdemon='Denominator' and mn.nm_numdemon like ? or "                    
                    + "mn.jenis_numdemon='Denominator' and mn.status_data like ? or "
                    + "mn.jenis_numdemon='Denominator' and mi.nm_indikator like ? or "
                    + "mn.jenis_numdemon='Denominator' and mi.gedung like ? ORDER BY mn.no_urut, mi.gedung");

            try {
                ps2.setString(1, "%" + TCari2.getText().trim() + "%");
                ps2.setString(2, "%" + TCari2.getText().trim() + "%");
                ps2.setString(3, "%" + TCari2.getText().trim() + "%");
                ps2.setString(4, "%" + TCari2.getText().trim() + "%");
                ps2.setString(5, "%" + TCari2.getText().trim() + "%");
                ps2.setString(6, "%" + TCari2.getText().trim() + "%");
                rs2 = ps2.executeQuery();                
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rs2.getString("kd_numdemon"),
                        rs2.getString("no_urut"),
                        rs2.getString("nm_numdemon"),
                        rs2.getString("nm_indikator"),
                        rs2.getString("gedung"),
                        rs2.getString("sttsDataNum").toUpperCase(),
                        rs2.getString("jenis_indikator")
                    });
                }                
            } catch (Exception e) {
                System.out.println("tampilDenominator() : " + e);
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
        LCount2.setText("" + tabMode2.getRowCount());
    }
    
    private void emptTeksNum() {
        cmbSttsNumerator.setSelectedIndex(0);
        cmbSttsNumerator.requestFocus();
    }
    
    private void emptTeksDen() {
        cmbSttsDenominator.setSelectedIndex(0);
        cmbSttsDenominator.requestFocus();
    }
}
