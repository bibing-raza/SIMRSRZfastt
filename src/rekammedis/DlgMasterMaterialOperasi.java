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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class DlgMasterMaterialOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6;
    private int i = 0;
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgMasterMaterialOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new String[]{
            "Kode Infus", "Nama Infus & Alat", "Satuan", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbInfus.setModel(tabMode);
        tbInfus.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbInfus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbInfus.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(450);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            }
        }
        tbInfus.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new String[]{
            "Kode Obat", "Nama Obat Anestesi", "Satuan", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObat.setModel(tabMode1);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(450);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new String[]{
            "Kode Psiko", "Nama Psikotropika dan Narkotika", "Satuan", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPsiko.setModel(tabMode2);
        tbPsiko.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPsiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPsiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(450);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            }
        }
        tbPsiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3=new DefaultTableModel(null,new String[]{
            "Kode Antibiotik", "Nama Antibiotik", "Satuan", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbAntibiotik.setModel(tabMode3);
        tbAntibiotik.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAntibiotik.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbAntibiotik.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(450);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            }
        }
        tbAntibiotik.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4=new DefaultTableModel(null,new String[]{
            "Kode Bahan", "Nama Bahan Habis Pakai", "Satuan", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBahan.setModel(tabMode4);
        tbBahan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBahan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbBahan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(450);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            }
        }
        tbBahan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5=new DefaultTableModel(null,new String[]{
            "Kode Benang", "Nama Benang", "Satuan", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBenang.setModel(tabMode5);
        tbBenang.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBenang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbBenang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(450);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            }
        }
        tbBenang.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6=new DefaultTableModel(null,new String[]{
            "Kode Lain-lain", "Nama Lain-lain", "Satuan", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbLain.setModel(tabMode6);
        tbLain.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbLain.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(450);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            }
        }
        tbLain.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TnmInfus.setDocument(new batasInput((int) 200).getKata(TnmInfus));
        TnmObat.setDocument(new batasInput((int) 200).getKata(TnmObat));
        TnmPsiko.setDocument(new batasInput((int) 200).getKata(TnmPsiko));
        TnmAnti.setDocument(new batasInput((int) 200).getKata(TnmAnti));
        TnmBahan.setDocument(new batasInput((int) 200).getKata(TnmBahan));
        TnmBenang.setDocument(new batasInput((int) 200).getKata(TnmBenang));
        TnmLain.setDocument(new batasInput((int) 200).getKata(TnmLain));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilInfus();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilInfus();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilInfus();}
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

        internalFrame1 = new widget.InternalFrame();
        TabMaterial = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        panelGlass9 = new widget.panelisi();
        jLabel4 = new widget.Label();
        TkdInfus = new widget.TextBox();
        jLabel9 = new widget.Label();
        TnmInfus = new widget.TextBox();
        jLabel10 = new widget.Label();
        cmbSatuanInfus = new widget.ComboBox();
        jLabel27 = new widget.Label();
        cmbStatusInfus = new widget.ComboBox();
        Scroll = new widget.ScrollPane();
        tbInfus = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        panelGlass11 = new widget.panelisi();
        jLabel5 = new widget.Label();
        TkdObat = new widget.TextBox();
        jLabel11 = new widget.Label();
        TnmObat = new widget.TextBox();
        jLabel12 = new widget.Label();
        cmbSatuanObat = new widget.ComboBox();
        jLabel28 = new widget.Label();
        cmbStatusObat = new widget.ComboBox();
        Scroll1 = new widget.ScrollPane();
        tbObat = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        panelGlass12 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TkdPsiko = new widget.TextBox();
        jLabel13 = new widget.Label();
        TnmPsiko = new widget.TextBox();
        jLabel14 = new widget.Label();
        cmbSatuanPsiko = new widget.ComboBox();
        jLabel29 = new widget.Label();
        cmbStatusPsiko = new widget.ComboBox();
        Scroll2 = new widget.ScrollPane();
        tbPsiko = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel15 = new widget.Label();
        TkdAnti = new widget.TextBox();
        jLabel16 = new widget.Label();
        TnmAnti = new widget.TextBox();
        jLabel17 = new widget.Label();
        cmbSatuanAnti = new widget.ComboBox();
        jLabel30 = new widget.Label();
        cmbStatusAnti = new widget.ComboBox();
        Scroll3 = new widget.ScrollPane();
        tbAntibiotik = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        panelGlass14 = new widget.panelisi();
        jLabel18 = new widget.Label();
        TkdBahan = new widget.TextBox();
        jLabel19 = new widget.Label();
        TnmBahan = new widget.TextBox();
        jLabel20 = new widget.Label();
        cmbSatuanBahan = new widget.ComboBox();
        jLabel31 = new widget.Label();
        cmbStatusBahan = new widget.ComboBox();
        Scroll4 = new widget.ScrollPane();
        tbBahan = new widget.Table();
        internalFrame7 = new widget.InternalFrame();
        panelGlass15 = new widget.panelisi();
        jLabel21 = new widget.Label();
        TkdBenang = new widget.TextBox();
        jLabel22 = new widget.Label();
        TnmBenang = new widget.TextBox();
        jLabel23 = new widget.Label();
        cmbSatuanBenang = new widget.ComboBox();
        jLabel32 = new widget.Label();
        cmbStatusBenang = new widget.ComboBox();
        Scroll5 = new widget.ScrollPane();
        tbBenang = new widget.Table();
        internalFrame8 = new widget.InternalFrame();
        panelGlass16 = new widget.panelisi();
        jLabel24 = new widget.Label();
        TkdLain = new widget.TextBox();
        jLabel25 = new widget.Label();
        TnmLain = new widget.TextBox();
        jLabel26 = new widget.Label();
        cmbSatuanLain = new widget.ComboBox();
        jLabel33 = new widget.Label();
        cmbStatusLain = new widget.ComboBox();
        Scroll6 = new widget.ScrollPane();
        tbLain = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Master Material Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabMaterial.setBackground(new java.awt.Color(255, 255, 254));
        TabMaterial.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabMaterial.setName("TabMaterial"); // NOI18N
        TabMaterial.setPreferredSize(new java.awt.Dimension(270, 106));
        TabMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabMaterialMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(452, 802));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass9.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Kode Infus : ");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass9.add(jLabel4);
        jLabel4.setBounds(0, 10, 80, 23);

        TkdInfus.setEditable(false);
        TkdInfus.setForeground(new java.awt.Color(0, 0, 0));
        TkdInfus.setName("TkdInfus"); // NOI18N
        panelGlass9.add(TkdInfus);
        TkdInfus.setBounds(83, 10, 70, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Infus & Alat : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass9.add(jLabel9);
        jLabel9.setBounds(155, 10, 120, 23);

        TnmInfus.setForeground(new java.awt.Color(0, 0, 0));
        TnmInfus.setName("TnmInfus"); // NOI18N
        TnmInfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmInfusKeyPressed(evt);
            }
        });
        panelGlass9.add(TnmInfus);
        TnmInfus.setBounds(275, 10, 400, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Satuan :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass9.add(jLabel10);
        jLabel10.setBounds(675, 10, 65, 23);

        cmbSatuanInfus.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanInfus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bh", "Flab" }));
        cmbSatuanInfus.setName("cmbSatuanInfus"); // NOI18N
        cmbSatuanInfus.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass9.add(cmbSatuanInfus);
        cmbSatuanInfus.setBounds(745, 10, 55, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Status :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass9.add(jLabel27);
        jLabel27.setBounds(803, 10, 60, 23);

        cmbStatusInfus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusInfus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatusInfus.setName("cmbStatusInfus"); // NOI18N
        cmbStatusInfus.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass9.add(cmbStatusInfus);
        cmbStatusInfus.setBounds(868, 10, 78, 23);

        internalFrame2.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbInfus.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbInfus.setName("tbInfus"); // NOI18N
        tbInfus.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 450));
        tbInfus.getTableHeader().setReorderingAllowed(false);
        tbInfus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInfusMouseClicked(evt);
            }
        });
        tbInfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbInfusKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbInfus);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabMaterial.addTab("Infus Dan Alat", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setPreferredSize(new java.awt.Dimension(452, 802));
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass11.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Kode Obat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass11.add(jLabel5);
        jLabel5.setBounds(0, 10, 80, 23);

        TkdObat.setEditable(false);
        TkdObat.setForeground(new java.awt.Color(0, 0, 0));
        TkdObat.setName("TkdObat"); // NOI18N
        panelGlass11.add(TkdObat);
        TkdObat.setBounds(83, 10, 70, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nama Obat Anestesi : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass11.add(jLabel11);
        jLabel11.setBounds(155, 10, 120, 23);

        TnmObat.setForeground(new java.awt.Color(0, 0, 0));
        TnmObat.setName("TnmObat"); // NOI18N
        TnmObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmObatKeyPressed(evt);
            }
        });
        panelGlass11.add(TnmObat);
        TnmObat.setBounds(275, 10, 400, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Satuan :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass11.add(jLabel12);
        jLabel12.setBounds(675, 10, 65, 23);

        cmbSatuanObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Amp", "Flac", "ml", "ltr" }));
        cmbSatuanObat.setName("cmbSatuanObat"); // NOI18N
        cmbSatuanObat.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbSatuanObat);
        cmbSatuanObat.setBounds(745, 10, 55, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Status :");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass11.add(jLabel28);
        jLabel28.setBounds(803, 10, 60, 23);

        cmbStatusObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatusObat.setName("cmbStatusObat"); // NOI18N
        cmbStatusObat.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass11.add(cmbStatusObat);
        cmbStatusObat.setBounds(868, 10, 78, 23);

        internalFrame3.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 450));
        tbObat.getTableHeader().setReorderingAllowed(false);
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
        Scroll1.setViewportView(tbObat);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabMaterial.addTab("Obat Anestesi", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setPreferredSize(new java.awt.Dimension(452, 802));
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass12.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Kode Psiko : ");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass12.add(jLabel8);
        jLabel8.setBounds(0, 10, 80, 23);

        TkdPsiko.setEditable(false);
        TkdPsiko.setForeground(new java.awt.Color(0, 0, 0));
        TkdPsiko.setName("TkdPsiko"); // NOI18N
        panelGlass12.add(TkdPsiko);
        TkdPsiko.setBounds(83, 10, 70, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Nama Psiko & Narkotika : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass12.add(jLabel13);
        jLabel13.setBounds(155, 10, 140, 23);

        TnmPsiko.setForeground(new java.awt.Color(0, 0, 0));
        TnmPsiko.setName("TnmPsiko"); // NOI18N
        TnmPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmPsikoKeyPressed(evt);
            }
        });
        panelGlass12.add(TnmPsiko);
        TnmPsiko.setBounds(295, 10, 400, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Satuan :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass12.add(jLabel14);
        jLabel14.setBounds(695, 10, 65, 23);

        cmbSatuanPsiko.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanPsiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Amp", "ml", " " }));
        cmbSatuanPsiko.setName("cmbSatuanPsiko"); // NOI18N
        cmbSatuanPsiko.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass12.add(cmbSatuanPsiko);
        cmbSatuanPsiko.setBounds(765, 10, 55, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Status :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass12.add(jLabel29);
        jLabel29.setBounds(825, 10, 60, 23);

        cmbStatusPsiko.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusPsiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatusPsiko.setName("cmbStatusPsiko"); // NOI18N
        cmbStatusPsiko.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass12.add(cmbStatusPsiko);
        cmbStatusPsiko.setBounds(890, 10, 78, 23);

        internalFrame4.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPsiko.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbPsiko.setName("tbPsiko"); // NOI18N
        tbPsiko.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 450));
        tbPsiko.getTableHeader().setReorderingAllowed(false);
        tbPsiko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPsikoMouseClicked(evt);
            }
        });
        tbPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPsikoKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbPsiko);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabMaterial.addTab("Gol. Psikotropika & Narkotika", internalFrame4);

        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setPreferredSize(new java.awt.Dimension(452, 802));
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass13.setLayout(null);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Kode Antibiotik : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass13.add(jLabel15);
        jLabel15.setBounds(0, 10, 100, 23);

        TkdAnti.setEditable(false);
        TkdAnti.setForeground(new java.awt.Color(0, 0, 0));
        TkdAnti.setName("TkdAnti"); // NOI18N
        panelGlass13.add(TkdAnti);
        TkdAnti.setBounds(103, 10, 70, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama Antibiotik : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass13.add(jLabel16);
        jLabel16.setBounds(175, 10, 100, 23);

        TnmAnti.setForeground(new java.awt.Color(0, 0, 0));
        TnmAnti.setName("TnmAnti"); // NOI18N
        TnmAnti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAntiKeyPressed(evt);
            }
        });
        panelGlass13.add(TnmAnti);
        TnmAnti.setBounds(275, 10, 400, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Satuan :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass13.add(jLabel17);
        jLabel17.setBounds(675, 10, 65, 23);

        cmbSatuanAnti.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanAnti.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Flac", "Amp" }));
        cmbSatuanAnti.setName("cmbSatuanAnti"); // NOI18N
        cmbSatuanAnti.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass13.add(cmbSatuanAnti);
        cmbSatuanAnti.setBounds(745, 10, 55, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Status :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass13.add(jLabel30);
        jLabel30.setBounds(803, 10, 60, 23);

        cmbStatusAnti.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusAnti.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatusAnti.setName("cmbStatusAnti"); // NOI18N
        cmbStatusAnti.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass13.add(cmbStatusAnti);
        cmbStatusAnti.setBounds(870, 10, 78, 23);

        internalFrame5.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbAntibiotik.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbAntibiotik.setName("tbAntibiotik"); // NOI18N
        tbAntibiotik.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 450));
        tbAntibiotik.getTableHeader().setReorderingAllowed(false);
        tbAntibiotik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAntibiotikMouseClicked(evt);
            }
        });
        tbAntibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAntibiotikKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbAntibiotik);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabMaterial.addTab("Antibiotik", internalFrame5);

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setPreferredSize(new java.awt.Dimension(452, 802));
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass14.setLayout(null);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Kode Bahan : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass14.add(jLabel18);
        jLabel18.setBounds(0, 10, 90, 23);

        TkdBahan.setEditable(false);
        TkdBahan.setForeground(new java.awt.Color(0, 0, 0));
        TkdBahan.setName("TkdBahan"); // NOI18N
        panelGlass14.add(TkdBahan);
        TkdBahan.setBounds(93, 10, 70, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Nama Bahan Habis Pakai : ");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass14.add(jLabel19);
        jLabel19.setBounds(165, 10, 140, 23);

        TnmBahan.setForeground(new java.awt.Color(0, 0, 0));
        TnmBahan.setName("TnmBahan"); // NOI18N
        TnmBahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmBahanKeyPressed(evt);
            }
        });
        panelGlass14.add(TnmBahan);
        TnmBahan.setBounds(305, 10, 400, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Satuan :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass14.add(jLabel20);
        jLabel20.setBounds(705, 10, 65, 23);

        cmbSatuanBahan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanBahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ml", "Bh" }));
        cmbSatuanBahan.setName("cmbSatuanBahan"); // NOI18N
        cmbSatuanBahan.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass14.add(cmbSatuanBahan);
        cmbSatuanBahan.setBounds(775, 10, 55, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Status :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass14.add(jLabel31);
        jLabel31.setBounds(833, 10, 60, 23);

        cmbStatusBahan.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusBahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatusBahan.setName("cmbStatusBahan"); // NOI18N
        cmbStatusBahan.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass14.add(cmbStatusBahan);
        cmbStatusBahan.setBounds(900, 10, 78, 23);

        internalFrame6.add(panelGlass14, java.awt.BorderLayout.PAGE_START);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbBahan.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbBahan.setName("tbBahan"); // NOI18N
        tbBahan.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 450));
        tbBahan.getTableHeader().setReorderingAllowed(false);
        tbBahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBahanMouseClicked(evt);
            }
        });
        tbBahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBahanKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbBahan);

        internalFrame6.add(Scroll4, java.awt.BorderLayout.CENTER);

        TabMaterial.addTab("Bahan Habis Pakai", internalFrame6);

        internalFrame7.setBorder(null);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setPreferredSize(new java.awt.Dimension(452, 802));
        internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass15.setLayout(null);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Kode Benang : ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelGlass15.add(jLabel21);
        jLabel21.setBounds(0, 10, 90, 23);

        TkdBenang.setEditable(false);
        TkdBenang.setForeground(new java.awt.Color(0, 0, 0));
        TkdBenang.setName("TkdBenang"); // NOI18N
        panelGlass15.add(TkdBenang);
        TkdBenang.setBounds(93, 10, 70, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Nama Benang : ");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass15.add(jLabel22);
        jLabel22.setBounds(165, 10, 100, 23);

        TnmBenang.setForeground(new java.awt.Color(0, 0, 0));
        TnmBenang.setName("TnmBenang"); // NOI18N
        TnmBenang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmBenangKeyPressed(evt);
            }
        });
        panelGlass15.add(TnmBenang);
        TnmBenang.setBounds(265, 10, 400, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Satuan :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass15.add(jLabel23);
        jLabel23.setBounds(665, 10, 65, 23);

        cmbSatuanBenang.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanBenang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bh" }));
        cmbSatuanBenang.setName("cmbSatuanBenang"); // NOI18N
        cmbSatuanBenang.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass15.add(cmbSatuanBenang);
        cmbSatuanBenang.setBounds(735, 10, 55, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Status :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass15.add(jLabel32);
        jLabel32.setBounds(792, 10, 60, 23);

        cmbStatusBenang.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusBenang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatusBenang.setName("cmbStatusBenang"); // NOI18N
        cmbStatusBenang.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass15.add(cmbStatusBenang);
        cmbStatusBenang.setBounds(858, 10, 78, 23);

        internalFrame7.add(panelGlass15, java.awt.BorderLayout.PAGE_START);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbBenang.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbBenang.setName("tbBenang"); // NOI18N
        tbBenang.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 450));
        tbBenang.getTableHeader().setReorderingAllowed(false);
        tbBenang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBenangMouseClicked(evt);
            }
        });
        tbBenang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBenangKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbBenang);

        internalFrame7.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabMaterial.addTab("Benang", internalFrame7);

        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setPreferredSize(new java.awt.Dimension(452, 802));
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass16.setLayout(null);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Kode Lain-lain : ");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass16.add(jLabel24);
        jLabel24.setBounds(0, 10, 90, 23);

        TkdLain.setEditable(false);
        TkdLain.setForeground(new java.awt.Color(0, 0, 0));
        TkdLain.setName("TkdLain"); // NOI18N
        panelGlass16.add(TkdLain);
        TkdLain.setBounds(93, 10, 70, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Nama Lain-lain : ");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass16.add(jLabel25);
        jLabel25.setBounds(165, 10, 100, 23);

        TnmLain.setForeground(new java.awt.Color(0, 0, 0));
        TnmLain.setName("TnmLain"); // NOI18N
        TnmLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmLainKeyPressed(evt);
            }
        });
        panelGlass16.add(TnmLain);
        TnmLain.setBounds(265, 10, 400, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Satuan :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass16.add(jLabel26);
        jLabel26.setBounds(665, 10, 65, 23);

        cmbSatuanLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bh", "ml", "Amp", "Flac" }));
        cmbSatuanLain.setName("cmbSatuanLain"); // NOI18N
        cmbSatuanLain.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass16.add(cmbSatuanLain);
        cmbSatuanLain.setBounds(735, 10, 55, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Status :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass16.add(jLabel33);
        jLabel33.setBounds(792, 10, 60, 23);

        cmbStatusLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatusLain.setName("cmbStatusLain"); // NOI18N
        cmbStatusLain.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass16.add(cmbStatusLain);
        cmbStatusLain.setBounds(858, 10, 78, 23);

        internalFrame8.add(panelGlass16, java.awt.BorderLayout.PAGE_START);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbLain.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbLain.setName("tbLain"); // NOI18N
        tbLain.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 450));
        tbLain.getTableHeader().setReorderingAllowed(false);
        tbLain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLainMouseClicked(evt);
            }
        });
        tbLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbLainKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbLain);

        internalFrame8.add(Scroll6, java.awt.BorderLayout.CENTER);

        TabMaterial.addTab("Lain - Lain", internalFrame8);

        internalFrame1.add(TabMaterial, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
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

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TabMaterial.getSelectedIndex() == 0) {
            if (TnmInfus.getText().equals("")) {
                Valid.textKosong(TnmInfus, "Infus dan Alat");
                TnmInfus.requestFocus();
            } else if (cmbSatuanInfus.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanInfus, "Satuan");
                cmbSatuanInfus.requestFocus();
            } else {
                simpanInfus();
            }
        } else if (TabMaterial.getSelectedIndex() == 1) {
            if (TnmObat.getText().equals("")) {
                Valid.textKosong(TnmObat, "Obat Anestesi");
                TnmObat.requestFocus();
            } else if (cmbSatuanObat.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanObat, "Satuan");
                cmbSatuanObat.requestFocus();
            } else {
                simpanObat();
            }
        } else if (TabMaterial.getSelectedIndex() == 2) {
            if (TnmPsiko.getText().equals("")) {
                Valid.textKosong(TnmPsiko, "Gol. Psikotropika & Narkotika");
                TnmPsiko.requestFocus();
            } else if (cmbSatuanPsiko.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanPsiko, "Satuan");
                cmbSatuanPsiko.requestFocus();
            } else {
                simpanPsiko();
            }
        } else if (TabMaterial.getSelectedIndex() == 3) {
            if (TnmAnti.getText().equals("")) {
                Valid.textKosong(TnmAnti, "Antibiotik");
                TnmAnti.requestFocus();
            } else if (cmbSatuanAnti.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanAnti, "Satuan");
                cmbSatuanAnti.requestFocus();
            } else {
                simpanAntibiotik();
            }
        } else if (TabMaterial.getSelectedIndex() == 4) {
            if (TnmBahan.getText().equals("")) {
                Valid.textKosong(TnmBahan, "Bahan Habis Pakai");
                TnmBahan.requestFocus();
            } else if (cmbSatuanBahan.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanBahan, "Satuan");
                cmbSatuanBahan.requestFocus();
            } else {
                simpanBahan();
            }
        } else if (TabMaterial.getSelectedIndex() == 5) {
            if (TnmBenang.getText().equals("")) {
                Valid.textKosong(TnmBenang, "Benang");
                TnmBenang.requestFocus();
            } else if (cmbSatuanBenang.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanBenang, "Satuan");
                cmbSatuanBenang.requestFocus();
            } else {
                simpanBenang();
            }
        } else if (TabMaterial.getSelectedIndex() == 6) {
            if (TnmLain.getText().equals("")) {
                Valid.textKosong(TnmLain, "Lain-lain");
                TnmLain.requestFocus();
            } else if (cmbSatuanLain.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanLain, "Satuan");
                cmbSatuanLain.requestFocus();
            } else {
                simpanLainlain();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,TkdInfus,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TabMaterial.getSelectedIndex() == 0) {
            if (TnmInfus.getText().equals("")) {
                Valid.textKosong(TnmInfus, "Infus dan Alat");
                TnmInfus.requestFocus();
            } else if (cmbSatuanInfus.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanInfus, "Satuan");
                cmbSatuanInfus.requestFocus();
            } else {
                gantiInfus();
            }
        } else if (TabMaterial.getSelectedIndex() == 1) {
            if (TnmObat.getText().equals("")) {
                Valid.textKosong(TnmObat, "Obat Anestesi");
                TnmObat.requestFocus();
            } else if (cmbSatuanObat.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanObat, "Satuan");
                cmbSatuanObat.requestFocus();
            } else {
                gantiObat();
            }
        } else if (TabMaterial.getSelectedIndex() == 2) {
            if (TnmPsiko.getText().equals("")) {
                Valid.textKosong(TnmPsiko, "Gol. Psikotropika & Narkotika");
                TnmPsiko.requestFocus();
            } else if (cmbSatuanPsiko.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanPsiko, "Satuan");
                cmbSatuanPsiko.requestFocus();
            } else {
                gantiPsiko();
            }
        } else if (TabMaterial.getSelectedIndex() == 3) {
            if (TnmAnti.getText().equals("")) {
                Valid.textKosong(TnmAnti, "Antibiotik");
                TnmAnti.requestFocus();
            } else if (cmbSatuanAnti.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanAnti, "Satuan");
                cmbSatuanAnti.requestFocus();
            } else {
               gantiAntibiotik();
            }
        } else if (TabMaterial.getSelectedIndex() == 4) {
            if (TnmBahan.getText().equals("")) {
                Valid.textKosong(TnmBahan, "Bahan Habis Pakai");
                TnmBahan.requestFocus();
            } else if (cmbSatuanBahan.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanBahan, "Satuan");
                cmbSatuanBahan.requestFocus();
            } else {
                gantiBahan();
            }
        } else if (TabMaterial.getSelectedIndex() == 5) {
            if (TnmBenang.getText().equals("")) {
                Valid.textKosong(TnmBenang, "Benang");
                TnmBenang.requestFocus();
            } else if (cmbSatuanBenang.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanBenang, "Satuan");
                cmbSatuanBenang.requestFocus();
            } else {
                gantiBenang();
            }
        } else if (TabMaterial.getSelectedIndex() == 6) {
            if (TnmLain.getText().equals("")) {
                Valid.textKosong(TnmLain, "Lain-lain");
                TnmLain.requestFocus();
            } else if (cmbSatuanLain.getSelectedIndex() == 0) {
                Valid.textKosong(cmbSatuanLain, "Satuan");
                cmbSatuanLain.requestFocus();
            } else {
                gantiLainlain();
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
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
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
        if (TabMaterial.getSelectedIndex() == 0) {
            tampilInfus();
        } else if (TabMaterial.getSelectedIndex() == 1) {
            tampilObat();
        } else if (TabMaterial.getSelectedIndex() == 2) {
            tampilPsikotropika();
        } else if (TabMaterial.getSelectedIndex() == 3) {
            tampilAntibiotik();
        } else if (TabMaterial.getSelectedIndex() == 4) {
            tampilBahan();
        } else if (TabMaterial.getSelectedIndex() == 5) {
            tampilBenang();
        } else if (TabMaterial.getSelectedIndex() == 6) {
            tampilLainlain();
        }
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
            Valid.pindah(evt, BtnCari, TkdInfus);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbInfusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInfusMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbInfusMouseClicked

    private void tbInfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbInfusKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbInfusKeyPressed

    private void TnmInfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmInfusKeyPressed
        Valid.pindah(evt, TnmInfus, cmbSatuanInfus);
    }//GEN-LAST:event_TnmInfusKeyPressed

    private void TnmObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmObatKeyPressed
        Valid.pindah(evt, TnmObat, cmbSatuanObat);
    }//GEN-LAST:event_TnmObatKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode1.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyPressed

    private void TnmPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmPsikoKeyPressed
        Valid.pindah(evt, TnmPsiko, cmbSatuanPsiko);
    }//GEN-LAST:event_TnmPsikoKeyPressed

    private void tbPsikoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPsikoMouseClicked
        if(tabMode2.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPsikoMouseClicked

    private void tbPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPsikoKeyPressed
        if(tabMode2.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPsikoKeyPressed

    private void TnmAntiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAntiKeyPressed
        Valid.pindah(evt, TnmAnti, cmbSatuanAnti);
    }//GEN-LAST:event_TnmAntiKeyPressed

    private void tbAntibiotikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAntibiotikMouseClicked
        if(tabMode3.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbAntibiotikMouseClicked

    private void tbAntibiotikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAntibiotikKeyPressed
        if(tabMode3.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbAntibiotikKeyPressed

    private void TnmBahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmBahanKeyPressed
        Valid.pindah(evt, TnmBahan, cmbSatuanBahan);
    }//GEN-LAST:event_TnmBahanKeyPressed

    private void tbBahanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBahanMouseClicked
        if(tabMode4.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbBahanMouseClicked

    private void tbBahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBahanKeyPressed
        if(tabMode4.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbBahanKeyPressed

    private void TnmBenangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmBenangKeyPressed
        Valid.pindah(evt, TnmBenang, cmbSatuanBenang);
    }//GEN-LAST:event_TnmBenangKeyPressed

    private void tbBenangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBenangMouseClicked
        if(tabMode5.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbBenangMouseClicked

    private void tbBenangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBenangKeyPressed
        if(tabMode5.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbBenangKeyPressed

    private void TnmLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmLainKeyPressed
        Valid.pindah(evt, TnmLain, cmbSatuanLain);
    }//GEN-LAST:event_TnmLainKeyPressed

    private void tbLainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLainMouseClicked
        if(tabMode6.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbLainMouseClicked

    private void tbLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLainKeyPressed
        if(tabMode6.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbLainKeyPressed

    private void TabMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabMaterialMouseClicked
        emptTeks();
        BtnCariActionPerformed(null);        
    }//GEN-LAST:event_TabMaterialMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgMasterMaterialOperasi dialog = new DlgMasterMaterialOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGanti;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    public widget.TextBox TCari;
    private javax.swing.JTabbedPane TabMaterial;
    private widget.TextBox TkdAnti;
    private widget.TextBox TkdBahan;
    private widget.TextBox TkdBenang;
    private widget.TextBox TkdInfus;
    private widget.TextBox TkdLain;
    private widget.TextBox TkdObat;
    private widget.TextBox TkdPsiko;
    private widget.TextBox TnmAnti;
    private widget.TextBox TnmBahan;
    private widget.TextBox TnmBenang;
    private widget.TextBox TnmInfus;
    private widget.TextBox TnmLain;
    private widget.TextBox TnmObat;
    private widget.TextBox TnmPsiko;
    private widget.ComboBox cmbSatuanAnti;
    private widget.ComboBox cmbSatuanBahan;
    private widget.ComboBox cmbSatuanBenang;
    private widget.ComboBox cmbSatuanInfus;
    private widget.ComboBox cmbSatuanLain;
    private widget.ComboBox cmbSatuanObat;
    private widget.ComboBox cmbSatuanPsiko;
    private widget.ComboBox cmbStatusAnti;
    private widget.ComboBox cmbStatusBahan;
    private widget.ComboBox cmbStatusBenang;
    private widget.ComboBox cmbStatusInfus;
    private widget.ComboBox cmbStatusLain;
    private widget.ComboBox cmbStatusObat;
    private widget.ComboBox cmbStatusPsiko;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
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
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbAntibiotik;
    private widget.Table tbBahan;
    private widget.Table tbBenang;
    private widget.Table tbInfus;
    private widget.Table tbLain;
    private widget.Table tbObat;
    private widget.Table tbPsiko;
    // End of variables declaration//GEN-END:variables

    public void tampilInfus() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT * FROM master_infus_operasi WHERE "
                    + "kd_infus LIKE ? or "
                    + "nama_infus like ? ORDER BY kd_infus");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("kd_infus"),
                        rs.getString("nama_infus"),
                        rs.getString("satuan"),
                        rs.getString("status")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilInfus() : " + e);
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
    
    public void tampilObat() {     
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT * FROM master_obat_operasi WHERE "
                    + "kd_obat LIKE ? or "
                    + "nama_obat like ? ORDER BY kd_obat");
            try {
                ps1.setString(1, "%" + TCari.getText().trim() + "%");
                ps1.setString(2, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();                
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{                        
                        rs1.getString("kd_obat"),
                        rs1.getString("nama_obat"),
                        rs1.getString("satuan"),
                        rs1.getString("status")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilObat() : " + e);
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
        LCount.setText("" + tabMode1.getRowCount());
    }
    
    public void tampilPsikotropika() {     
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT * FROM master_narkotika_operasi WHERE "
                    + "kd_narkotika LIKE ? or "
                    + "nama_narkotika like ? ORDER BY kd_narkotika");
            try {
                ps2.setString(1, "%" + TCari.getText().trim() + "%");
                ps2.setString(2, "%" + TCari.getText().trim() + "%");
                rs2 = ps2.executeQuery();                
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{                        
                        rs2.getString("kd_narkotika"),
                        rs2.getString("nama_narkotika"),
                        rs2.getString("satuan"),
                        rs2.getString("status")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilPsikotropika() : " + e);
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
        LCount.setText("" + tabMode2.getRowCount());
    }
    
    public void tampilAntibiotik() {     
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("SELECT * FROM master_antibiotik_operasi WHERE "
                    + "kd_antibiotik LIKE ? or "
                    + "nama_antibiotik like ? ORDER BY kd_antibiotik");
            try {
                ps3.setString(1, "%" + TCari.getText().trim() + "%");
                ps3.setString(2, "%" + TCari.getText().trim() + "%");
                rs3 = ps3.executeQuery();                
                while (rs3.next()) {
                    tabMode3.addRow(new String[]{                        
                        rs3.getString("kd_antibiotik"),
                        rs3.getString("nama_antibiotik"),
                        rs3.getString("satuan"),
                        rs3.getString("status")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilAntibiotik() : " + e);
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
        LCount.setText("" + tabMode3.getRowCount());
    }
    
    public void tampilBahan() {     
        Valid.tabelKosong(tabMode4);
        try {
            ps4 = koneksi.prepareStatement("SELECT * FROM master_bahan_operasi WHERE "
                    + "kd_bahan LIKE ? or "
                    + "nama_bahan like ? ORDER BY kd_bahan");
            try {
                ps4.setString(1, "%" + TCari.getText().trim() + "%");
                ps4.setString(2, "%" + TCari.getText().trim() + "%");
                rs4 = ps4.executeQuery();                
                while (rs4.next()) {
                    tabMode4.addRow(new String[]{                        
                        rs4.getString("kd_bahan"),
                        rs4.getString("nama_bahan"),
                        rs4.getString("satuan"),
                        rs4.getString("status")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilBahan() : " + e);
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
        LCount.setText("" + tabMode4.getRowCount());
    }
    
    public void tampilBenang() {     
        Valid.tabelKosong(tabMode5);
        try {
            ps5 = koneksi.prepareStatement("SELECT * FROM master_benang_operasi WHERE "
                    + "kd_benang LIKE ? or "
                    + "nama_benang like ? ORDER BY kd_benang");
            try {
                ps5.setString(1, "%" + TCari.getText().trim() + "%");
                ps5.setString(2, "%" + TCari.getText().trim() + "%");
                rs5 = ps5.executeQuery();                
                while (rs5.next()) {
                    tabMode5.addRow(new String[]{                        
                        rs5.getString("kd_benang"),
                        rs5.getString("nama_benang"),
                        rs5.getString("satuan"),
                        rs5.getString("status")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilBenang() : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode5.getRowCount());
    }
    
    public void tampilLainlain() {     
        Valid.tabelKosong(tabMode6);
        try {
            ps6 = koneksi.prepareStatement("SELECT * FROM master_lainlain_operasi WHERE "
                    + "kd_lainlain LIKE ? or "
                    + "nama_lainlain like ? ORDER BY kd_lainlain");
            try {
                ps6.setString(1, "%" + TCari.getText().trim() + "%");
                ps6.setString(2, "%" + TCari.getText().trim() + "%");
                rs6 = ps6.executeQuery();                
                while (rs6.next()) {
                    tabMode6.addRow(new String[]{                        
                        rs6.getString("kd_lainlain"),
                        rs6.getString("nama_lainlain"),
                        rs6.getString("satuan"),
                        rs6.getString("status")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilLainlain() : " + e);
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
        LCount.setText("" + tabMode6.getRowCount());
    }
    
    public void emptTeks() {
        if (TabMaterial.getSelectedIndex() == 0) {
            TkdInfus.setText(Valid.autoNomer("master_infus_operasi", "IN", 3));
            TnmInfus.setText("");
            TnmInfus.requestFocus();
            cmbSatuanInfus.setSelectedIndex(0);
            cmbStatusInfus.setSelectedIndex(0);
        } else if (TabMaterial.getSelectedIndex() == 1) {
            TkdObat.setText(Valid.autoNomer("master_obat_operasi", "OB", 3));
            TnmObat.setText("");
            TnmObat.requestFocus();
            cmbSatuanObat.setSelectedIndex(0);
            cmbStatusObat.setSelectedIndex(0);
        } else if (TabMaterial.getSelectedIndex() == 2) {
            TkdPsiko.setText(Valid.autoNomer("master_narkotika_operasi", "PS", 3));
            TnmPsiko.setText("");
            TnmPsiko.requestFocus();
            cmbSatuanPsiko.setSelectedIndex(0);
            cmbStatusPsiko.setSelectedIndex(0);
        } else if (TabMaterial.getSelectedIndex() == 3) {
            TkdAnti.setText(Valid.autoNomer("master_antibiotik_operasi", "AN", 3));
            TnmAnti.setText("");
            TnmAnti.requestFocus();
            cmbSatuanAnti.setSelectedIndex(0);
            cmbStatusAnti.setSelectedIndex(0);
        } else if (TabMaterial.getSelectedIndex() == 4) {
            TkdBahan.setText(Valid.autoNomer("master_bahan_operasi", "BH", 3));
            TnmBahan.setText("");
            TnmBahan.requestFocus();
            cmbSatuanBahan.setSelectedIndex(0);
            cmbStatusBahan.setSelectedIndex(0);
        } else if (TabMaterial.getSelectedIndex() == 5) {
            TkdBenang.setText(Valid.autoNomer("master_benang_operasi", "BN", 3));
            TnmBenang.setText("");
            TnmBenang.requestFocus();
            cmbSatuanBenang.setSelectedIndex(1);
            cmbStatusBenang.setSelectedIndex(0);
        } else if (TabMaterial.getSelectedIndex() == 6) {
            TkdLain.setText(Valid.autoNomer("master_lainlain_operasi", "LN", 3));
            TnmLain.setText("");
            TnmLain.requestFocus();
            cmbSatuanLain.setSelectedIndex(0);
            cmbStatusLain.setSelectedIndex(0);
        }
    }

    private void getData() {
        if (TabMaterial.getSelectedIndex() == 0) {
            if (tbInfus.getSelectedRow() != -1) {
                TkdInfus.setText(tbInfus.getValueAt(tbInfus.getSelectedRow(), 0).toString());
                TnmInfus.setText(tbInfus.getValueAt(tbInfus.getSelectedRow(), 1).toString());
                cmbSatuanInfus.setSelectedItem(tbInfus.getValueAt(tbInfus.getSelectedRow(), 2).toString());
                cmbStatusInfus.setSelectedItem(tbInfus.getValueAt(tbInfus.getSelectedRow(), 3).toString());
            }
        } else if (TabMaterial.getSelectedIndex() == 1) {
            if (tbObat.getSelectedRow() != -1) {
                TkdObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                TnmObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
                cmbSatuanObat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
                cmbStatusObat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            }
        } else if (TabMaterial.getSelectedIndex() == 2) {
            if (tbPsiko.getSelectedRow() != -1) {
                TkdPsiko.setText(tbPsiko.getValueAt(tbPsiko.getSelectedRow(), 0).toString());
                TnmPsiko.setText(tbPsiko.getValueAt(tbPsiko.getSelectedRow(), 1).toString());
                cmbSatuanPsiko.setSelectedItem(tbPsiko.getValueAt(tbPsiko.getSelectedRow(), 2).toString());
                cmbStatusPsiko.setSelectedItem(tbPsiko.getValueAt(tbPsiko.getSelectedRow(), 3).toString());
            }
        } else if (TabMaterial.getSelectedIndex() == 3) {
            if (tbAntibiotik.getSelectedRow() != -1) {
                TkdAnti.setText(tbAntibiotik.getValueAt(tbAntibiotik.getSelectedRow(), 0).toString());
                TnmAnti.setText(tbAntibiotik.getValueAt(tbAntibiotik.getSelectedRow(), 1).toString());
                cmbSatuanAnti.setSelectedItem(tbAntibiotik.getValueAt(tbAntibiotik.getSelectedRow(), 2).toString());
                cmbStatusAnti.setSelectedItem(tbAntibiotik.getValueAt(tbAntibiotik.getSelectedRow(), 3).toString());
            }
        } else if (TabMaterial.getSelectedIndex() == 4) {
            if (tbBahan.getSelectedRow() != -1) {
                TkdBahan.setText(tbBahan.getValueAt(tbBahan.getSelectedRow(), 0).toString());
                TnmBahan.setText(tbBahan.getValueAt(tbBahan.getSelectedRow(), 1).toString());
                cmbSatuanBahan.setSelectedItem(tbBahan.getValueAt(tbBahan.getSelectedRow(), 2).toString());
                cmbStatusBahan.setSelectedItem(tbBahan.getValueAt(tbBahan.getSelectedRow(), 3).toString());
            }
        } else if (TabMaterial.getSelectedIndex() == 5) {
            if (tbBenang.getSelectedRow() != -1) {
                TkdBenang.setText(tbBenang.getValueAt(tbBenang.getSelectedRow(), 0).toString());
                TnmBenang.setText(tbBenang.getValueAt(tbBenang.getSelectedRow(), 1).toString());
                cmbSatuanBenang.setSelectedItem(tbBenang.getValueAt(tbBenang.getSelectedRow(), 2).toString());
                cmbStatusBenang.setSelectedItem(tbBenang.getValueAt(tbBenang.getSelectedRow(), 3).toString());
            }
        } else if (TabMaterial.getSelectedIndex() == 6) {
            if (tbLain.getSelectedRow() != -1) {
                TkdLain.setText(tbLain.getValueAt(tbLain.getSelectedRow(), 0).toString());
                TnmLain.setText(tbLain.getValueAt(tbLain.getSelectedRow(), 1).toString());
                cmbSatuanLain.setSelectedItem(tbLain.getValueAt(tbLain.getSelectedRow(), 2).toString());
                cmbStatusLain.setSelectedItem(tbLain.getValueAt(tbLain.getSelectedRow(), 3).toString());
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getadmin());
        BtnGanti.setEnabled(akses.getadmin());        
    }
    
    private void simpanInfus() {
        if (Sequel.menyimpantf("master_infus_operasi", "?,?,?,?", "Kode Infus dan Alat", 4, new String[]{
            TkdInfus.getText(), TnmInfus.getText(), cmbSatuanInfus.getSelectedItem().toString(), cmbStatusInfus.getSelectedItem().toString()
        }) == true) {
            emptTeks();
            BtnCariActionPerformed(null);
        }
    }
    
    private void simpanObat() {
        if (Sequel.menyimpantf("master_obat_operasi", "?,?,?,?", "Kode Obat Anestesi", 4, new String[]{
            TkdObat.getText(), TnmObat.getText(), cmbSatuanObat.getSelectedItem().toString(), cmbStatusObat.getSelectedItem().toString()
        }) == true) {
            emptTeks();
            BtnCariActionPerformed(null);
        }
    }
    
    private void simpanPsiko() {
        if (Sequel.menyimpantf("master_narkotika_operasi", "?,?,?,?", "Kode Psikotropika & Narkotika", 4, new String[]{
            TkdPsiko.getText(), TnmPsiko.getText(), cmbSatuanPsiko.getSelectedItem().toString(), cmbStatusPsiko.getSelectedItem().toString()
        }) == true) {
            emptTeks();
            BtnCariActionPerformed(null);
        }
    }
    
    private void simpanAntibiotik() {
        if (Sequel.menyimpantf("master_antibiotik_operasi", "?,?,?,?", "Kode Antibiotik", 4, new String[]{
            TkdAnti.getText(), TnmAnti.getText(), cmbSatuanAnti.getSelectedItem().toString(), cmbStatusAnti.getSelectedItem().toString()
        }) == true) {
            emptTeks();
            BtnCariActionPerformed(null);
        }
    }
    
    private void simpanBahan() {
        if (Sequel.menyimpantf("master_bahan_operasi", "?,?,?,?", "Kode Bahan Habis Pakai", 4, new String[]{
            TkdBahan.getText(), TnmBahan.getText(), cmbSatuanBahan.getSelectedItem().toString(), cmbStatusBahan.getSelectedItem().toString()
        }) == true) {
            emptTeks();
            BtnCariActionPerformed(null);
        }
    }
    
    private void simpanBenang() {
        if (Sequel.menyimpantf("master_benang_operasi", "?,?,?,?", "Kode Benang", 4, new String[]{
            TkdBenang.getText(), TnmBenang.getText(), cmbSatuanBenang.getSelectedItem().toString(), cmbStatusBenang.getSelectedItem().toString()
        }) == true) {
            emptTeks();
            BtnCariActionPerformed(null);
        }
    }
    
    private void simpanLainlain() {
        if (Sequel.menyimpantf("master_lainlain_operasi", "?,?,?,?", "Kode Lain-lain", 4, new String[]{
            TkdLain.getText(), TnmLain.getText(), cmbSatuanLain.getSelectedItem().toString(), cmbStatusLain.getSelectedItem().toString()
        }) == true) {
            emptTeks();
            BtnCariActionPerformed(null);
        }
    }
    
    private void gantiInfus() {
        if (tbInfus.getSelectedRow() > -1) {
            if (Sequel.mengedittf("master_infus_operasi", "kd_infus=?", "nama_infus=?, satuan=?, status=?", 4, new String[]{
                TnmInfus.getText(), cmbSatuanInfus.getSelectedItem().toString(), cmbStatusInfus.getSelectedItem().toString(),
                tbInfus.getValueAt(tbInfus.getSelectedRow(), 0).toString()
            }) == true) {
                emptTeks();
                BtnCariActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    private void gantiObat() {
        if (tbObat.getSelectedRow() > -1) {
            if (Sequel.mengedittf("master_obat_operasi", "kd_obat=?", "nama_obat=?, satuan=?, status=?", 4, new String[]{
                TnmObat.getText(), cmbSatuanObat.getSelectedItem().toString(), cmbStatusObat.getSelectedItem().toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
            }) == true) {
                emptTeks();
                BtnCariActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    private void gantiPsiko() {
        if (tbPsiko.getSelectedRow() > -1) {
            if (Sequel.mengedittf("master_narkotika_operasi", "kd_narkotika=?", "nama_narkotika=?, satuan=?, status=?", 4, new String[]{
                TnmPsiko.getText(), cmbSatuanPsiko.getSelectedItem().toString(), cmbStatusPsiko.getSelectedItem().toString(),
                tbPsiko.getValueAt(tbPsiko.getSelectedRow(), 0).toString()
            }) == true) {
                emptTeks();
                BtnCariActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    private void gantiAntibiotik() {
        if (tbAntibiotik.getSelectedRow() > -1) {
            if (Sequel.mengedittf("master_antibiotik_operasi", "kd_antibiotik=?", "nama_antibiotik=?, satuan=?, status=?", 4, new String[]{
                TnmAnti.getText(), cmbSatuanAnti.getSelectedItem().toString(), cmbStatusAnti.getSelectedItem().toString(),
                tbAntibiotik.getValueAt(tbAntibiotik.getSelectedRow(), 0).toString()
            }) == true) {
                emptTeks();
                BtnCariActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    private void gantiBahan() {
        if (tbBahan.getSelectedRow() > -1) {
            if (Sequel.mengedittf("master_bahan_operasi", "kd_bahan=?", "nama_bahan=?, satuan=?, status=?", 4, new String[]{
                TnmBahan.getText(), cmbSatuanBahan.getSelectedItem().toString(), cmbStatusBahan.getSelectedItem().toString(),
                tbBahan.getValueAt(tbBahan.getSelectedRow(), 0).toString()
            }) == true) {
                emptTeks();
                BtnCariActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    private void gantiBenang() {
        if (tbBenang.getSelectedRow() > -1) {
            if (Sequel.mengedittf("master_benang_operasi", "kd_benang=?", "nama_benang=?, satuan=?, status=?", 4, new String[]{
                TnmBenang.getText(), cmbSatuanBenang.getSelectedItem().toString(), cmbStatusBenang.getSelectedItem().toString(),
                tbBenang.getValueAt(tbBenang.getSelectedRow(), 0).toString()
            }) == true) {
                emptTeks();
                BtnCariActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    private void gantiLainlain() {
        if (tbLain.getSelectedRow() > -1) {
            if (Sequel.mengedittf("master_lainlain_operasi", "kd_lainlain=?", "nama_lainlain=?, satuan=?, status=?", 4, new String[]{
                TnmLain.getText(), cmbSatuanLain.getSelectedItem().toString(), cmbStatusLain.getSelectedItem().toString(),
                tbLain.getValueAt(tbLain.getSelectedRow(), 0).toString()
            }) == true) {
                emptTeks();
                BtnCariActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    public void awalData() {
        BtnCariActionPerformed(null);
    }
}
