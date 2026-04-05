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

/**
 *
 * @author dosen
 */
public class RMSamplingPemanfaatanRM extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabMode7;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7;
    private int i = 0, x = 0;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMSamplingPemanfaatanRM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Perawat", "Jml. Askep IGD PerPasien"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerawatIgd.setModel(tabMode);
        tbPerawatIgd.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerawatIgd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPerawatIgd.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } 
        }
        tbPerawatIgd.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPerawatIgd.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPerawatIgd.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabMode1=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "Jml. Triase Ponek PerPasien"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBidanPonek.setModel(tabMode1);
        tbBidanPonek.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBidanPonek.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbBidanPonek.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } 
        }
        tbBidanPonek.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbBidanPonek.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbBidanPonek.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabMode2=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "CPPT", "Assesmen Keperawatan Dewasa", "Unit Kerja"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerawatDewasa.setModel(tabMode2);
        tbPerawatDewasa.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerawatDewasa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbPerawatDewasa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(190);
            } else if (i == 5) {
                column.setPreferredWidth(270);
            } 
        }
        tbPerawatDewasa.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPerawatDewasa.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPerawatDewasa.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPerawatDewasa.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode3=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "Jml. CPPT PerPasien"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFarmasi.setModel(tabMode3);
        tbFarmasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbFarmasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbFarmasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } 
        }
        tbFarmasi.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbFarmasi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbFarmasi.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabMode4=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "CPPT", "Asuhan Gizi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbNutrisionis.setModel(tabMode4);
        tbNutrisionis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNutrisionis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbNutrisionis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } 
        }
        tbNutrisionis.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbNutrisionis.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbNutrisionis.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbNutrisionis.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode5=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "CPPT", "Asesmen Keperawatan Anak", "Unit Kerja"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerawatAnak.setModel(tabMode5);
        tbPerawatAnak.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerawatAnak.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbPerawatAnak.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(160);
            } else if (i == 5) {
                column.setPreferredWidth(240);
            } 
        }
        tbPerawatAnak.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPerawatAnak.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPerawatAnak.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPerawatAnak.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode6=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "CPPT", "Asesmen Keperawatan Anak", "Unit Kerja"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerawatAnak1.setModel(tabMode6);
        tbPerawatAnak1.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerawatAnak1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbPerawatAnak1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(160);
            } else if (i == 5) {
                column.setPreferredWidth(240);
            } 
        }
        tbPerawatAnak1.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPerawatAnak1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPerawatAnak1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPerawatAnak1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode7=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "Jml. CPPT PerPasien", "Ruang Perawatan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBidan.setModel(tabMode7);
        tbBidan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBidan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbBidan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            }            
        }
        tbBidan.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbBidan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbBidan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
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
        internalFrame2 = new widget.InternalFrame();
        TabRM = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbPerawatIgd = new widget.Table();
        Scroll1 = new widget.ScrollPane();
        tbBidanPonek = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbPerawatDewasa = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        tbFarmasi = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbNutrisionis = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbPerawatAnak = new widget.Table();
        Scroll9 = new widget.ScrollPane();
        tbPerawatAnak1 = new widget.Table();
        Scroll6 = new widget.ScrollPane();
        tbBidan = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbPerawatIgd7 = new widget.Table();
        Scroll8 = new widget.ScrollPane();
        tbPerawatIgd8 = new widget.Table();
        FormInput = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        panelGlass8 = new widget.panelisi();
        jLabel6 = new widget.Label();
        label_key = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnDownload = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Sampling Pemanfaatan e-Rekam Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        TabRM.setBackground(new java.awt.Color(250, 255, 245));
        TabRM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRM.setName("TabRM"); // NOI18N
        TabRM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRMMouseClicked(evt);
            }
        });

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPerawatIgd.setName("tbPerawatIgd"); // NOI18N
        tbPerawatIgd.getTableHeader().setReorderingAllowed(false);
        Scroll.setViewportView(tbPerawatIgd);

        TabRM.addTab("Perawat IGD", Scroll);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbBidanPonek.setName("tbBidanPonek"); // NOI18N
        tbBidanPonek.getTableHeader().setReorderingAllowed(false);
        Scroll1.setViewportView(tbBidanPonek);

        TabRM.addTab("Bidan Ponek", Scroll1);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPerawatDewasa.setName("tbPerawatDewasa"); // NOI18N
        tbPerawatDewasa.getTableHeader().setReorderingAllowed(false);
        Scroll2.setViewportView(tbPerawatDewasa);

        TabRM.addTab("Perawat Rg. Inap (Dewasa)", Scroll2);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbFarmasi.setName("tbFarmasi"); // NOI18N
        tbFarmasi.getTableHeader().setReorderingAllowed(false);
        Scroll3.setViewportView(tbFarmasi);

        TabRM.addTab("Petugas Farmasi", Scroll3);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbNutrisionis.setName("tbNutrisionis"); // NOI18N
        tbNutrisionis.getTableHeader().setReorderingAllowed(false);
        Scroll4.setViewportView(tbNutrisionis);

        TabRM.addTab("Petugas Nutrisionis/Gizi", Scroll4);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbPerawatAnak.setName("tbPerawatAnak"); // NOI18N
        tbPerawatAnak.getTableHeader().setReorderingAllowed(false);
        Scroll5.setViewportView(tbPerawatAnak);

        TabRM.addTab("Perawat Rg. Anak", Scroll5);

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbPerawatAnak1.setName("tbPerawatAnak1"); // NOI18N
        tbPerawatAnak1.getTableHeader().setReorderingAllowed(false);
        Scroll9.setViewportView(tbPerawatAnak1);

        TabRM.addTab("Perawat R. Inap (Pasien Anak)", Scroll9);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbBidan.setName("tbBidan"); // NOI18N
        tbBidan.getTableHeader().setReorderingAllowed(false);
        Scroll6.setViewportView(tbBidan);

        TabRM.addTab("Bidan Rg. Inap", Scroll6);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbPerawatIgd7.setName("tbPerawatIgd7"); // NOI18N
        tbPerawatIgd7.getTableHeader().setReorderingAllowed(false);
        Scroll7.setViewportView(tbPerawatIgd7);

        TabRM.addTab("Dokter Spesialis (DPJP)", Scroll7);

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbPerawatIgd8.setName("tbPerawatIgd8"); // NOI18N
        tbPerawatIgd8.getTableHeader().setReorderingAllowed(false);
        Scroll8.setViewportView(tbPerawatIgd8);

        TabRM.addTab("Dokter Umum", Scroll8);

        internalFrame2.add(TabRM, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 45));
        FormInput.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Periode Tgl. :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 100, 23);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(DTPCari1);
        DTPCari1.setBounds(107, 10, 95, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(23, 23));
        FormInput.add(jLabel17);
        jLabel17.setBounds(205, 10, 23, 23);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(DTPCari2);
        DTPCari2.setBounds(234, 10, 95, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Catatan : Angka dalam setiap rekam medis adalah jumlah pasien");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(370, 30));
        panelGlass8.add(jLabel6);

        label_key.setForeground(new java.awt.Color(0, 0, 0));
        label_key.setText("Key Word :");
        label_key.setName("label_key"); // NOI18N
        label_key.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(label_key);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCari);

        BtnDownload.setForeground(new java.awt.Color(0, 0, 0));
        BtnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        BtnDownload.setMnemonic('2');
        BtnDownload.setText("Download Data");
        BtnDownload.setName("BtnDownload"); // NOI18N
        BtnDownload.setPreferredSize(new java.awt.Dimension(130, 30));
        panelGlass8.add(BtnDownload);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TabRMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRMMouseClicked
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_TabRMMouseClicked

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabRM.getSelectedIndex() == 0) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPerawatIgd();
        } else if (TabRM.getSelectedIndex() == 1) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilBidanPonek();
        } else if (TabRM.getSelectedIndex() == 2) {
            label_key.setVisible(true);
            TCari.setVisible(true);
            tampilPerawatDewasa();
        } else if (TabRM.getSelectedIndex() == 3) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPetugasFarmasi();
        } else if (TabRM.getSelectedIndex() == 4) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPetugasNutrisionis();
        } else if (TabRM.getSelectedIndex() == 5) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPerawatAnak();
        } else if (TabRM.getSelectedIndex() == 6) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPerawatPasienAnak();
        } else if (TabRM.getSelectedIndex() == 7) {
            label_key.setVisible(true);
            TCari.setVisible(true);
            tampilBidanRanap();
        }
    }//GEN-LAST:event_BtnCariActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        label_key.setVisible(false);
        TCari.setVisible(false);
        TCari.setText("");
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSamplingPemanfaatanRM dialog = new RMSamplingPemanfaatanRM(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDownload;
    private widget.Button BtnKeluar;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    public widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRM;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel17;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label label_key;
    private widget.panelisi panelGlass8;
    private widget.Table tbBidan;
    private widget.Table tbBidanPonek;
    private widget.Table tbFarmasi;
    private widget.Table tbNutrisionis;
    private widget.Table tbPerawatAnak;
    private widget.Table tbPerawatAnak1;
    private widget.Table tbPerawatDewasa;
    private widget.Table tbPerawatIgd;
    private widget.Table tbPerawatIgd7;
    private widget.Table tbPerawatIgd8;
    // End of variables declaration//GEN-END:variables

    private void tampilPerawatIgd() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pg.nik `NIP/NR`, pg.nama `Nama Perawat`, COUNT(DISTINCT pa.no_rawat) `Jml. Askep IGD PerPasien` "
                    + "FROM pegawai pg INNER JOIN penilaian_awal_keperawatan_igdrz pa ON pa.nip_perawat = pg.nik WHERE pg.nik NOT IN ('-', '--') AND "
                    + "DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "GROUP BY pg.nik, pg.nama HAVING COUNT(DISTINCT pa.no_rawat) > 0 ORDER BY COUNT(DISTINCT pa.no_rawat) desc, pg.nama");
            try {
                rs = ps.executeQuery();
                x = 1;
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        x + ".",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPerawatIgd() : " + e);
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
    }
    
    private void tampilBidanPonek() {     
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT pg.nik `NIP/NR`, pg.nama `Nama Petugas`, COUNT(DISTINCT tp.no_rawat) `Jml. Triase Ponek PerPasien` "
                    + "FROM pegawai pg INNER JOIN triase_ponek tp ON tp.nip_petugas  = pg.nik WHERE pg.nik NOT IN ('-', '--') AND "
                    + "DATE(tp.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "GROUP BY pg.nik, pg.nama HAVING COUNT(DISTINCT tp.no_rawat) > 0 ORDER BY COUNT(DISTINCT tp.no_rawat) desc, pg.nama");
            try {
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{                        
                        x + ".",
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilBidanPonek() : " + e);
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
    }
    
    private void tampilPerawatDewasa() {     
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Perawat`, z.`CPPT`, z.`Assesmen Keperawatan Dewasa`, z.`Unit Kerja` "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Perawat`, y.cppt `CPPT`, y.askep `Assesmen Keperawatan Dewasa`, y.nm_dep `Unit Kerja` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, d.nama nm_dep, "
                    + "/* CPPT */ "
                    + "(SELECT COUNT(DISTINCT c.no_rawat) FROM cppt c "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                    + "INNER JOIN penilaian_awal_keperawatan_dewasa_ranap pa1 on pa1.no_rawat=c.no_rawat WHERE "
                    + "c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' "
                    + "AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') cppt, "
                    + "/* Askep */ "
                    + "(SELECT COUNT(DISTINCT pa.no_rawat) FROM penilaian_awal_keperawatan_dewasa_ranap pa "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pa.no_rawat WHERE "
                    + "pa.nip_perawat = pg.nik AND DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') askep "
                    + "FROM pegawai pg INNER JOIN departemen d ON d.dep_id=pg.departemen "
                    + "WHERE pg.nik NOT IN ('-', '--') and pg.nama not like '%dr.%') x WHERE x.askep > 0) y) z where "
                    + "z.`NIP/NR` like ? or z.`Nama Perawat` like ? or z.`Unit Kerja` like ? "
                    + "ORDER BY z.`Unit Kerja`, z.`Nama Perawat`");
            try {
                ps2.setString(1, "%" + TCari.getText() + "%");
                ps2.setString(2, "%" + TCari.getText() + "%");
                ps2.setString(3, "%" + TCari.getText() + "%");
                rs2 = ps2.executeQuery();
                x = 1;
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{                        
                        x + ".",
                        rs2.getString(1),
                        rs2.getString(2),
                        rs2.getString(3),
                        rs2.getString(4),
                        rs2.getString(5)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPerawatDewasa() : " + e);
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
    }
    
    private void tampilPetugasFarmasi() {     
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("SELECT pg.nik `NIP/NR`, pg.nama `Nama Petugas`, COUNT(DISTINCT c.no_rawat) `Jml. CPPT PerPasien` "
                    + "FROM pegawai pg INNER JOIN cppt c ON c.nip_ppa  = pg.nik WHERE "
                    + "pg.nik NOT IN ('-', '--') AND c.nip_ppa = pg.nik AND c.jenis_ppa = 'Apoteker' and c.flag_hapus='tidak' and "
                    + "DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "GROUP BY pg.nik, pg.nama HAVING COUNT(DISTINCT c.no_rawat) > 0 ORDER BY COUNT(DISTINCT c.no_rawat) desc, pg.nama");
            try {
                rs3 = ps3.executeQuery();
                x = 1;
                while (rs3.next()) {
                    tabMode3.addRow(new String[]{                        
                        x + ".",
                        rs3.getString(1),
                        rs3.getString(2),
                        rs3.getString(3)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPetugasFarmasi() : " + e);
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
    
    private void tampilPetugasNutrisionis() {     
        Valid.tabelKosong(tabMode4);
        try {
            ps4 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Petugas`, z.`CPPT`, z.`Asuhan Gizi`  "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Petugas`, y.cppt `CPPT`, y.asuhan `Asuhan Gizi` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, "
                    + "/* CPPT */ "
                    + "(SELECT COUNT(DISTINCT c.no_rawat) FROM cppt c "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat WHERE "
                    + "c.nip_ppa = pg.nik AND c.jenis_ppa = 'Nutrisionis' and c.flag_hapus='tidak' "
                    + "AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') cppt, "
                    + "/* Asuhan Gizi*/ "
                    + "(SELECT COUNT(DISTINCT ag.no_rawat) FROM asuhan_gizi_ranap ag "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat WHERE "
                    + "ag.nip_petugas = pg.nik AND ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') asuhan "
                    + "FROM pegawai pg WHERE pg.nik NOT IN ('-', '--')) x WHERE x.asuhan > 0) y) z ORDER BY z.`Nama Petugas`");
            try {
                rs4 = ps4.executeQuery();
                x = 1;
                while (rs4.next()) {
                    tabMode4.addRow(new String[]{                        
                        x + ".",
                        rs4.getString(1),
                        rs4.getString(2),
                        rs4.getString(3),
                        rs4.getString(4)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPetugasNutrisionis() : " + e);
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
    
    private void tampilPerawatAnak() {     
        Valid.tabelKosong(tabMode5);
        try {
            ps5 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Perawat`, z.`CPPT`, z.`Asesmen Keperawatan Anak`, z.`Unit Kerja` "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Perawat`, y.cppt `CPPT`, y.askep `Asesmen Keperawatan Anak`, y.nm_dep `Unit Kerja` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, d.nama nm_dep, "
                    + "/* CPPT */ "
                    + "(SELECT COUNT(DISTINCT c.no_rawat) FROM cppt c "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                    + "inner join penilaian_awal_keperawatan_anak_ranap pa1 on pa1.no_rawat=c.no_rawat WHERE "
                    + "c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' "
                    + "AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') cppt, "
                    + "/* Askep */ "
                    + "(SELECT COUNT(DISTINCT pa.no_rawat) FROM penilaian_awal_keperawatan_anak_ranap pa "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pa.no_rawat WHERE "
                    + "pa.nip_perawat = pg.nik AND DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') askep "
                    + "FROM pegawai pg INNER JOIN departemen d ON d.dep_id=pg.departemen "
                    + "WHERE pg.nik NOT IN ('-', '--') and pg.nama not like '%dr.%') x WHERE x.askep > 0 and x.nm_dep like '%anak%') y) z "
                    + "ORDER BY z.`Unit Kerja`, z.`Nama Perawat`");
            try {
                rs5 = ps5.executeQuery();
                x = 1;
                while (rs5.next()) {
                    tabMode5.addRow(new String[]{                        
                        x + ".",
                        rs5.getString(1),
                        rs5.getString(2),
                        rs5.getString(3),
                        rs5.getString(4),
                        rs5.getString(5)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPerawatAnak() : " + e);
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
    }
    
    private void tampilPerawatPasienAnak() {     
        Valid.tabelKosong(tabMode6);
        try {
            ps6 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Perawat`, z.`CPPT`, z.`Asesmen Keperawatan Anak`, z.`Unit Kerja` "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Perawat`, y.cppt `CPPT`, y.askep `Asesmen Keperawatan Anak`, y.nm_dep `Unit Kerja` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, d.nama nm_dep, "
                    + "/* CPPT */ "
                    + "(SELECT COUNT(DISTINCT c.no_rawat) FROM cppt c "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                    + "inner join penilaian_awal_keperawatan_anak_ranap pa1 on pa1.no_rawat=c.no_rawat WHERE "
                    + "c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' "
                    + "AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') cppt, "
                    + "/* Askep */ "
                    + "(SELECT COUNT(DISTINCT pa.no_rawat) FROM penilaian_awal_keperawatan_anak_ranap pa "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pa.no_rawat WHERE "
                    + "pa.nip_perawat = pg.nik AND DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') askep "
                    + "FROM pegawai pg INNER JOIN departemen d ON d.dep_id=pg.departemen "
                    + "WHERE pg.nik NOT IN ('-', '--') and pg.nama not like '%dr.%') x WHERE x.askep > 0 and x.nm_dep not like '%anak%') y) z "
                    + "ORDER BY z.`Unit Kerja`, z.`Nama Perawat`");
            try {
                rs6 = ps6.executeQuery();
                x = 1;
                while (rs6.next()) {
                    tabMode6.addRow(new String[]{                        
                        x + ".",
                        rs6.getString(1),
                        rs6.getString(2),
                        rs6.getString(3),
                        rs6.getString(4),
                        rs6.getString(5)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPerawatPasienAnak() : " + e);
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
    }
    
    private void tampilBidanRanap() {     
        Valid.tabelKosong(tabMode7);
        try {
            ps7 = koneksi.prepareStatement("SELECT pg.nik `NIP/NR`, pg.nama `Nama Petugas`, COUNT(DISTINCT c.no_rawat) `Jml. CPPT PerPasien`, if(b.nm_gedung is null,'-',b.nm_gedung) 'Ruang Perawatan' "
                    + "FROM pegawai pg INNER JOIN cppt c ON c.nip_ppa  = pg.nik left JOIN kamar k on k.kd_kamar=c.bagian "
                    + "left JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE "
                    + "pg.nik NOT IN ('-', '--') AND pg.nama not like '%dr.%' and c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Bidan' and c.flag_hapus='tidak' and "
                    + "DATE(c.waktu_simpan) BETWEEN ? AND ? and pg.nik like ? or "
                    + "pg.nik NOT IN ('-', '--') AND pg.nama not like '%dr.%' and c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Bidan' and c.flag_hapus='tidak' and "
                    + "DATE(c.waktu_simpan) BETWEEN ? AND ? and pg.nama like ? "
                    + "GROUP BY pg.nik, pg.nama HAVING COUNT(DISTINCT c.no_rawat) > 0 ORDER BY COUNT(DISTINCT c.no_rawat) desc, pg.nama");
            try {
                ps7.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps7.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps7.setString(3, "%" + TCari.getText().trim() + "%");
                ps7.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps7.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps7.setString(6, "%" + TCari.getText().trim() + "%");                
                rs7 = ps7.executeQuery();
                x = 1;
                while (rs7.next()) {
                    tabMode7.addRow(new String[]{                        
                        x + ".",
                        rs7.getString(1),
                        rs7.getString(2),
                        rs7.getString(3),
                        rs7.getString(4)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilBidanRanap() : " + e);
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
}
