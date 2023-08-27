/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * @author perpustakaan
 */
public final class DlgKunjunganRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2, ps3;
    private ResultSet rs, rs2;
    private int i = 0, lama = 0, baru = 0, laki = 0, per = 0, pilihan = 0;
    private String setbaru = "", setlama = "", umurlk = "", umurpr = "", diagnosa = "";
    private String host = "";
    private final Properties prop = new Properties();
    
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    
    public DlgKunjunganRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);
        tabMode=new DefaultTableModel(null,new String[]{"No.","Lama","Baru","Nama Pasien","L","P","Alamat","Diagnosa","Ruang","Stts.Pulang","Tgl.Masuk","DPJP"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(190);
            }else if(i==4){
                column.setPreferredWidth(40);
            }else if(i==5){
                column.setPreferredWidth(40);
            }else if(i==6){
                column.setPreferredWidth(190);
            }else if(i==7){
                column.setPreferredWidth(160);
            }else if(i==8){
                column.setPreferredWidth(190);
            }else if(i==9){
                column.setPreferredWidth(85);
            }else if(i==10){
                column.setPreferredWidth(75);
            }else if(i==11){
                column.setPreferredWidth(135);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new String[]{"No.","Lama","Baru","Nama Pasien","L","P","Alamat","Diagnosa","Ruang","Stts.Pulang","Tgl.Pulang","DPJP"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal2.setModel(tabMode2);
        //tbBangsal2.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal2.getBackground()));
        tbBangsal2.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbBangsal2.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(190);
            }else if(i==4){
                column.setPreferredWidth(40);
            }else if(i==5){
                column.setPreferredWidth(40);
            }else if(i==6){
                column.setPreferredWidth(190);
            }else if(i==7){
                column.setPreferredWidth(160);
            }else if(i==8){
                column.setPreferredWidth(190);
            }else if(i==9){
                column.setPreferredWidth(85);
            }else if(i==10){
                column.setPreferredWidth(75);
            }else if(i==11){
                column.setPreferredWidth(135);
            }
        }
        tbBangsal2.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TabRawat.getSelectedIndex()==0){
                        tampil();
                    }else if(TabRawat.getSelectedIndex()==1){
                        tampil2();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TabRawat.getSelectedIndex()==0){
                        tampil();
                    }else if(TabRawat.getSelectedIndex()==1){
                        tampil2();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TabRawat.getSelectedIndex()==0){
                        tampil();
                    }else if(TabRawat.getSelectedIndex()==1){
                        tampil2();
                    }
                }
            });
        }
        emptText();
    }    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        userBerizin = new widget.TextBox();
        kdAkses = new widget.TextBox();
        ruangDicetak = new widget.TextBox();
        PopupMnTglMasuk = new javax.swing.JPopupMenu();
        MnKunTglMasuk = new javax.swing.JMenuItem();
        MnKunCB = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbBangsal2 = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel7 = new widget.Label();
        panelisi1 = new widget.panelisi();
        jLabel8 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        cmbRuangKhusus1 = new widget.ComboBox();
        cmbRuangKhusus2 = new widget.ComboBox();
        cmbRuangKhusus3 = new widget.ComboBox();
        cmbRuangKhusus4 = new widget.ComboBox();
        BtnIndikatorPelayanan = new widget.Button();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        userBerizin.setForeground(new java.awt.Color(255, 255, 255));
        userBerizin.setName("userBerizin"); // NOI18N

        kdAkses.setForeground(new java.awt.Color(0, 0, 0));
        kdAkses.setName("kdAkses"); // NOI18N
        kdAkses.setPreferredSize(new java.awt.Dimension(120, 24));

        ruangDicetak.setForeground(new java.awt.Color(0, 0, 0));
        ruangDicetak.setName("ruangDicetak"); // NOI18N
        ruangDicetak.setPreferredSize(new java.awt.Dimension(120, 24));

        PopupMnTglMasuk.setName("PopupMnTglMasuk"); // NOI18N

        MnKunTglMasuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKunTglMasuk.setForeground(new java.awt.Color(0, 0, 0));
        MnKunTglMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnKunTglMasuk.setText("Lap. Rekap Kunjungan");
        MnKunTglMasuk.setName("MnKunTglMasuk"); // NOI18N
        MnKunTglMasuk.setPreferredSize(new java.awt.Dimension(250, 26));
        MnKunTglMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKunTglMasukActionPerformed(evt);
            }
        });
        PopupMnTglMasuk.add(MnKunTglMasuk);

        MnKunCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKunCB.setForeground(new java.awt.Color(0, 0, 0));
        MnKunCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnKunCB.setText("Lap. Rekap Total Kunjgn. Cara Bayar");
        MnKunCB.setName("MnKunCB"); // NOI18N
        MnKunCB.setPreferredSize(new java.awt.Dimension(250, 26));
        MnKunCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKunCBActionPerformed(evt);
            }
        });
        PopupMnTglMasuk.add(MnKunCB);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Kunjungan Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(PopupMnTglMasuk);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setToolTipText("");
        tbBangsal.setComponentPopupMenu(PopupMnTglMasuk);
        tbBangsal.setName("tbBangsal"); // NOI18N
        tbBangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsalMouseClicked(evt);
            }
        });
        tbBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBangsal);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab(".: Berdasar Tanggal Masuk  ", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbBangsal2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBangsal2.setName("tbBangsal2"); // NOI18N
        tbBangsal2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsal2MouseClicked(evt);
            }
        });
        tbBangsal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsal2KeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbBangsal2);

        internalFrame3.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab(".: Berdasar Tanggal Keluar  ", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass5.add(label11);

        Tgl1.setBackground(new java.awt.Color(245, 250, 240));
        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(label18);

        Tgl2.setBackground(new java.awt.Color(245, 250, 240));
        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(155, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('D');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+D");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(150, 23));
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
        panelGlass5.add(BtnCari);

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
        panelGlass5.add(BtnKeluar);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel7);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(45, 45));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Ruangan Inap :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 30));
        panelisi1.add(jLabel8);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEMUA RUANG", "AR-RAUDAH", "AS-SAMI/1", "AS-SAMI/2", "INTERNIST", "PERINATOLOGI & BAYI SEHAT" }));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setPreferredSize(new java.awt.Dimension(180, 23));
        cmbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuanganMouseClicked(evt);
            }
        });
        cmbRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuanganActionPerformed(evt);
            }
        });
        panelisi1.add(cmbRuangan);

        cmbRuangKhusus1.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "AS-SAMI", "AS-SAMI/1", "AS-SAMI/2" }));
        cmbRuangKhusus1.setName("cmbRuangKhusus1"); // NOI18N
        cmbRuangKhusus1.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus1MouseClicked(evt);
            }
        });
        cmbRuangKhusus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus1ActionPerformed(evt);
            }
        });
        panelisi1.add(cmbRuangKhusus1);

        cmbRuangKhusus2.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "ZAAL", "RKPD", "INTERNIST" }));
        cmbRuangKhusus2.setName("cmbRuangKhusus2"); // NOI18N
        cmbRuangKhusus2.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus2MouseClicked(evt);
            }
        });
        cmbRuangKhusus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus2ActionPerformed(evt);
            }
        });
        panelisi1.add(cmbRuangKhusus2);

        cmbRuangKhusus3.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "PERINATOLOGI", "BAYI SEHAT" }));
        cmbRuangKhusus3.setName("cmbRuangKhusus3"); // NOI18N
        cmbRuangKhusus3.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus3MouseClicked(evt);
            }
        });
        cmbRuangKhusus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus3ActionPerformed(evt);
            }
        });
        panelisi1.add(cmbRuangKhusus3);

        cmbRuangKhusus4.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "AR-RAUDAH", "AR-RAUDAH ATAS", "AR-RAUDAH BAWAH" }));
        cmbRuangKhusus4.setName("cmbRuangKhusus4"); // NOI18N
        cmbRuangKhusus4.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus4MouseClicked(evt);
            }
        });
        cmbRuangKhusus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus4ActionPerformed(evt);
            }
        });
        panelisi1.add(cmbRuangKhusus4);

        BtnIndikatorPelayanan.setForeground(new java.awt.Color(0, 0, 0));
        BtnIndikatorPelayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        BtnIndikatorPelayanan.setMnemonic('I');
        BtnIndikatorPelayanan.setText("Indikator Pelayanan");
        BtnIndikatorPelayanan.setToolTipText("Alt+I");
        BtnIndikatorPelayanan.setName("BtnIndikatorPelayanan"); // NOI18N
        BtnIndikatorPelayanan.setPreferredSize(new java.awt.Dimension(170, 23));
        BtnIndikatorPelayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIndikatorPelayananActionPerformed(evt);
            }
        });
        BtnIndikatorPelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnIndikatorPelayananKeyPressed(evt);
            }
        });
        panelisi1.add(BtnIndikatorPelayanan);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TKd);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbBangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsalMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBangsalMouseClicked

    private void tbBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsalKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBangsalKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    if ((!cmbRuangan.getSelectedItem().equals("- pilih salah satu -"))
            || (!cmbRuangKhusus1.getSelectedItem().equals("- pilih salah satu -"))
            || (!cmbRuangKhusus2.getSelectedItem().equals("- pilih salah satu -"))
            || (!cmbRuangKhusus3.getSelectedItem().equals("- pilih salah satu -"))
            || (!cmbRuangKhusus4.getSelectedItem().equals("- pilih salah satu -"))) {
        if (TabRawat.getSelectedIndex() == 0) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil2();
        }

    } else if ((cmbRuangan.getSelectedItem().equals("- pilih salah satu -"))
            || (cmbRuangKhusus1.getSelectedItem().equals("- pilih salah satu -"))
            || (cmbRuangKhusus2.getSelectedItem().equals("- pilih salah satu -"))
            || (cmbRuangKhusus3.getSelectedItem().equals("- pilih salah satu -"))
            || (cmbRuangKhusus4.getSelectedItem().equals("- pilih salah satu -"))) {
        JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu nama ruangannya dulu...!!!");
        Tgl1.requestFocus();
    }
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            Valid.pindah(evt, TKd, Tgl1);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'igd' and nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);

        if (akses.getregistrasi() == true || akses.getpenyakit() == true || akses.getkode().equals("PR13")) {
            BtnCari.setEnabled(true);
            cmbRuangan.setSelectedIndex(0);
            cmbRuangan.setEnabled(true);
            cmbRuangan.setVisible(true);
            cmbRuangKhusus1.setVisible(false);
            cmbRuangKhusus2.setVisible(false);
            cmbRuangKhusus3.setVisible(false);
            cmbRuangKhusus4.setVisible(false);
            tampil();
        }
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        tampil();
    }//GEN-LAST:event_formWindowActivated

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil2();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbBangsal2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsal2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal2MouseClicked

    private void tbBangsal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsal2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal2KeyPressed

    private void MnKunTglMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKunTglMasukActionPerformed
        if ((!cmbRuangKhusus1.getSelectedItem().equals("- pilih salah satu -"))
                || (!cmbRuangKhusus2.getSelectedItem().equals("- pilih salah satu -"))
                || (!cmbRuangKhusus3.getSelectedItem().equals("- pilih salah satu -"))
                || (!cmbRuangKhusus4.getSelectedItem().equals("- pilih salah satu -"))) {
            if (ruangDicetak.getText().equals("SEMUA RUANG")) {
                ctkSemuaRuang();
            } else if (ruangDicetak.getText().equals("AR-RAUDAH") || ruangDicetak.getText().equals("AR-RAUDAH ATAS") || ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                ctkRuangArRaudah();
            } else if (ruangDicetak.getText().equals("AS-SAMI") || ruangDicetak.getText().equals("AS-SAMI/1") || ruangDicetak.getText().equals("AS-SAMI/2")) {
                ctkRuangAsSami();
            } else if (ruangDicetak.getText().equals("INTERNIST")) {
                ctkRuangRKPDzaal();
            } else if (ruangDicetak.getText().equals("PERINATOLOGI & BAYI SEHAT")) {
                ctkRuangPeriBayi();
            } else {
                ctkPerRuangan();
            }

        } else if ((cmbRuangKhusus1.getSelectedItem().equals("- pilih salah satu -"))
                || (cmbRuangKhusus2.getSelectedItem().equals("- pilih salah satu -"))
                || (cmbRuangKhusus3.getSelectedItem().equals("- pilih salah satu -"))
                || (cmbRuangKhusus4.getSelectedItem().equals("- pilih salah satu -"))) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu nama ruangannya dulu...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_MnKunTglMasukActionPerformed

    private void MnKunCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKunCBActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());      
        Valid.MyReport("rptRekapKunCBTglMsk.jasper", "report", "::[ Laporan Rekap Total Kunjungan Pasien Rawat Inap Berdasarkan Cara Bayar & Tanggal Masuk ]::",
                " select a.png_jawab, ifnull(b.total,0) Pasien_Lama, ifnull(c.total,0) Pasien_Baru, (ifnull(b.total,0)+ifnull(c.total,0)) as total_LB from "
                + " ((select png_jawab, kd_pj from penjab) as a left join (SELECT penjab.png_jawab,penjab.kd_pj, COUNT(penjab.kd_pj) as total "
                + " FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.stts_daftar = 'Lama' "
                + " GROUP BY penjab.kd_pj) as b on a.kd_pj = b.kd_pj left join (SELECT penjab.png_jawab,penjab.kd_pj, COUNT(penjab.kd_pj) as total "
                + " FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.stts_daftar = 'Baru' "
                + " GROUP BY penjab.kd_pj) as c on c.kd_pj = a.kd_pj) WHERE (ifnull(b.total,0)+ifnull(c.total,0))  > 0 ORDER BY a.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKunCBActionPerformed

    private void BtnIndikatorPelayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIndikatorPelayananActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            host = Sequel.decXML(prop.getProperty("HOSTraza"), prop.getProperty("KEY"));
            if (akses.getpegawai_admin() == true) {
                Valid.panggilUrlRAZA("/rzid/");
            } else if (akses.getpegawai_user() == true) {
                Valid.panggilUrlRAZA("/rzid/");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnIndikatorPelayananActionPerformed

    private void BtnIndikatorPelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnIndikatorPelayananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnIndikatorPelayananKeyPressed

    private void cmbRuanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuanganMouseClicked
        cmbRuanganActionPerformed(null);
    }//GEN-LAST:event_cmbRuanganMouseClicked

    private void cmbRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuanganActionPerformed
        ruangDicetak.setText(cmbRuangan.getSelectedItem().toString());
    }//GEN-LAST:event_cmbRuanganActionPerformed

    private void cmbRuangKhusus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus1MouseClicked
        cmbRuangKhusus1ActionPerformed(null);
    }//GEN-LAST:event_cmbRuangKhusus1MouseClicked

    private void cmbRuangKhusus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus1ActionPerformed
        ruangDicetak.setText(cmbRuangKhusus1.getSelectedItem().toString());
    }//GEN-LAST:event_cmbRuangKhusus1ActionPerformed

    private void cmbRuangKhusus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus2MouseClicked
        cmbRuangKhusus2ActionPerformed(null);        
    }//GEN-LAST:event_cmbRuangKhusus2MouseClicked

    private void cmbRuangKhusus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus2ActionPerformed
        ruangDicetak.setText(cmbRuangKhusus2.getSelectedItem().toString());
    }//GEN-LAST:event_cmbRuangKhusus2ActionPerformed

    private void cmbRuangKhusus3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus3MouseClicked
        cmbRuangKhusus3ActionPerformed(null);
    }//GEN-LAST:event_cmbRuangKhusus3MouseClicked

    private void cmbRuangKhusus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus3ActionPerformed
        ruangDicetak.setText(cmbRuangKhusus3.getSelectedItem().toString());
    }//GEN-LAST:event_cmbRuangKhusus3ActionPerformed

    private void cmbRuangKhusus4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus4MouseClicked
        cmbRuangKhusus4ActionPerformed(null);
    }//GEN-LAST:event_cmbRuangKhusus4MouseClicked

    private void cmbRuangKhusus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus4ActionPerformed
        ruangDicetak.setText(cmbRuangKhusus4.getSelectedItem().toString());
    }//GEN-LAST:event_cmbRuangKhusus4ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgKunjunganRanap dialog = new DlgKunjunganRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnIndikatorPelayanan;
    private widget.Button BtnKeluar;
    private javax.swing.JMenuItem MnKunCB;
    private javax.swing.JMenuItem MnKunTglMasuk;
    private javax.swing.JPopupMenu PopupMnTglMasuk;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.ComboBox cmbRuangKhusus1;
    private widget.ComboBox cmbRuangKhusus2;
    private widget.ComboBox cmbRuangKhusus3;
    private widget.ComboBox cmbRuangKhusus4;
    private widget.ComboBox cmbRuangan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.TextBox kdAkses;
    private widget.Label label11;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelisi1;
    private widget.TextBox ruangDicetak;
    private widget.Table tbBangsal;
    private widget.Table tbBangsal2;
    private widget.TextBox userBerizin;
    // End of variables declaration//GEN-END:variables

    public void tampil(){        
        try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode);   
            
            if (ruangDicetak.getText().equals("SEMUA RUANG")) {
            ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.alamat,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,pasien.tgl_daftar,"
                        + "kamar_inap.kd_kamar,bangsal.nm_bangsal,reg_periksa.almt_pj,kamar_inap.stts_pulang,kamar_inap.tgl_masuk,dokter.nm_dokter "
                        + "from reg_periksa inner join pasien inner join kamar_inap inner join kamar inner join bangsal inner join dokter "
                        + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat and kamar_inap.kd_kamar=kamar.kd_kamar "
                        + "and kamar.kd_bangsal=bangsal.kd_bangsal and reg_periksa.kd_dokter=dokter.kd_dokter WHERE "
                        + "reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') and "
                        + "reg_periksa.tgl_registrasi between ? and ? and bangsal.nm_bangsal like ? or "
                        + "(reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and pasien.alamat like ? or "
                        + "reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                        + "reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                        + "reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and kamar_inap.kd_kamar like ?) "
                        + "group by reg_periksa.no_rawat order by reg_periksa.tgl_registrasi");            
            }
            
            if (!ruangDicetak.getText().equals("- pilih salah satu -") || !ruangDicetak.getText().equals("SEMUA RUANG")) {
            ps = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.alamat,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,pasien.tgl_daftar,"
                        + "kamar_inap.kd_kamar,bangsal.nm_bangsal,reg_periksa.almt_pj,kamar_inap.stts_pulang,kamar_inap.tgl_masuk,dokter.nm_dokter "
                        + "from reg_periksa inner join pasien inner join kamar_inap inner join kamar inner join bangsal inner join dokter "
                        + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat and kamar_inap.kd_kamar=kamar.kd_kamar "
                        + "and kamar.kd_bangsal=bangsal.kd_bangsal and reg_periksa.kd_dokter=dokter.kd_dokter WHERE "
                        + "reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') and "
                        + "reg_periksa.tgl_registrasi between ? and ? and bangsal.nm_bangsal like ? and "
                        + "(reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and pasien.alamat like ? or "
                        + "reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                        + "reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                        + "reg_periksa.status_lanjut='Ranap' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and kamar_inap.kd_kamar like ?) "
                        + "group by reg_periksa.no_rawat order by reg_periksa.tgl_registrasi");            
            }             
            
            try {               
                
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                
                if (ruangDicetak.getText().equals("SEMUA RUANG")) {
                    ps.setString(3, "%%");
                }
                
                if (!ruangDicetak.getText().equals("- pilih salah satu -") || !ruangDicetak.getText().equals("SEMUA RUANG")) {
                    ps.setString(3, "%" + ruangDicetak.getText() + "%");
                }
                                
                ps.setString(4, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                rs=ps.executeQuery();
                i=1;   
                lama=0;baru=0;laki=0;per=0;
                while(rs.next()){
                    setbaru="";
                    setlama="";
                    if(rs.getString("tgl_registrasi").equals(rs.getString("tgl_daftar"))){
                        setbaru=rs.getString("no_rkm_medis");
                        baru++;
                    }else if(!rs.getString("tgl_registrasi").equals(rs.getString("tgl_daftar"))){
                        setlama=rs.getString("no_rkm_medis");
                        lama++;
                    }
                    umurlk="";
                    umurpr="";
                    switch (rs.getString("jk")) {
                        case "L":
                            umurlk=rs.getString("umur");
                            laki++;
                            break;
                        case "P":
                            umurpr=rs.getString("umur");
                            per++;
                            break;
                    }
                    diagnosa="";
                    ps2=koneksi.prepareStatement(
                            "select penyakit.nm_penyakit from penyakit inner join diagnosa_pasien " +
                            "on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit " +
                            "where diagnosa_pasien.no_rawat=? order by prioritas asc limit 1");
                    try {                                    
                        ps2.setString(1,rs.getString("no_rawat"));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){
                            diagnosa=rs2.getString(1);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    tabMode.addRow(new Object[]{
                        i,
                        setlama,
                        setbaru,
                        rs.getString("nm_pasien"),
                        umurlk,
                        umurpr,
                        rs.getString("almt_pj"),
                        diagnosa,
                        rs.getString("kd_kamar") + " " + rs.getString("nm_bangsal"),
                        rs.getString("stts_pulang"),
                        rs.getString("tgl_masuk"),
                        rs.getString("nm_dokter")
                    });
                    i++;
                }
                if(i>=2){
                    tabMode.addRow(new Object[]{
                        ">>",lama,baru,"",laki,per,"","",""
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }    
            this.setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }

    public void tampil2(){        
        try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode2);   
            
            if (ruangDicetak.getText().equals("SEMUA RUANG")) {
            ps3=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.alamat,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,pasien.tgl_daftar,"
                        + "kamar_inap.kd_kamar,bangsal.nm_bangsal,reg_periksa.almt_pj,kamar_inap.stts_pulang,kamar_inap.tgl_keluar,dokter.nm_dokter "
                        + "from reg_periksa inner join pasien inner join kamar_inap inner join kamar inner join bangsal inner join dokter "
                        + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat and kamar_inap.kd_kamar=kamar.kd_kamar "
                        + "and kamar.kd_bangsal=bangsal.kd_bangsal and reg_periksa.kd_dokter=dokter.kd_dokter WHERE "
                        + "kamar_inap.stts_pulang not in ('-','Pindah Kamar') and kamar_inap.tgl_keluar between ? and ? and bangsal.nm_bangsal like ? or "
                        + "(kamar_inap.stts_pulang<>'Pindah Kamar' and kamar_inap.tgl_keluar between ? and ? and pasien.alamat like ? or "
                        + "kamar_inap.stts_pulang<>'Pindah Kamar' and kamar_inap.tgl_keluar between ? and ? and pasien.nm_pasien like ? or "
                        + "kamar_inap.stts_pulang<>'Pindah Kamar' and kamar_inap.tgl_keluar between ? and ? and reg_periksa.no_rkm_medis like ? or "
                        + "kamar_inap.stts_pulang<>'Pindah Kamar' and kamar_inap.tgl_keluar between ? and ? and kamar_inap.kd_kamar like ?) "
                        + "group by reg_periksa.no_rawat order by kamar_inap.tgl_keluar");
            }
            
            if (!ruangDicetak.getText().equals("- pilih salah satu -") || !ruangDicetak.getText().equals("SEMUA RUANG")) {
                ps3 = koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.alamat,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,pasien.tgl_daftar,"
                        + "kamar_inap.kd_kamar,bangsal.nm_bangsal,reg_periksa.almt_pj,kamar_inap.stts_pulang,kamar_inap.tgl_keluar,dokter.nm_dokter "
                        + "from reg_periksa inner join pasien inner join kamar_inap inner join kamar inner join bangsal inner join dokter "
                        + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat and kamar_inap.kd_kamar=kamar.kd_kamar "
                        + "and kamar.kd_bangsal=bangsal.kd_bangsal and reg_periksa.kd_dokter=dokter.kd_dokter WHERE "
                        + "kamar_inap.stts_pulang not in ('-','Pindah Kamar') and kamar_inap.tgl_keluar between ? and ? and bangsal.nm_bangsal like ? and "
                        + "(kamar_inap.stts_pulang<>'Pindah Kamar' and kamar_inap.tgl_keluar between ? and ? and pasien.alamat like ? or "
                        + "kamar_inap.stts_pulang<>'Pindah Kamar' and kamar_inap.tgl_keluar between ? and ? and pasien.nm_pasien like ? or "
                        + "kamar_inap.stts_pulang<>'Pindah Kamar' and kamar_inap.tgl_keluar between ? and ? and reg_periksa.no_rkm_medis like ? or "
                        + "kamar_inap.stts_pulang<>'Pindah Kamar' and kamar_inap.tgl_keluar between ? and ? and kamar_inap.kd_kamar like ?) "
                        + "group by reg_periksa.no_rawat order by kamar_inap.tgl_keluar");
            }
            
            try {
                ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                
                if (ruangDicetak.getText().equals("SEMUA RUANG")) {
                    ps3.setString(3, "%%");
                }

                if (!ruangDicetak.getText().equals("- pilih salah satu -") || !ruangDicetak.getText().equals("SEMUA RUANG")) {
                    ps3.setString(3, "%" + ruangDicetak.getText() + "%");
                }
                                
                ps3.setString(4,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps3.setString(5,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps3.setString(6,"%"+TCari.getText().trim()+"%");
                ps3.setString(7,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps3.setString(8,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps3.setString(9,"%"+TCari.getText().trim()+"%");
                ps3.setString(10,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps3.setString(11,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps3.setString(12,"%"+TCari.getText().trim()+"%");
                ps3.setString(13,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps3.setString(14,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps3.setString(15,"%"+TCari.getText().trim()+"%");
                rs=ps3.executeQuery();
                i=1;   
                lama=0;baru=0;laki=0;per=0;
                while(rs.next()){
                    setbaru="";
                    setlama="";
                    if(rs.getString("tgl_registrasi").equals(rs.getString("tgl_daftar"))){
                        setbaru=rs.getString("no_rkm_medis");
                        baru++;
                    }else if(!rs.getString("tgl_registrasi").equals(rs.getString("tgl_daftar"))){
                        setlama=rs.getString("no_rkm_medis");
                        lama++;
                    }
                    umurlk="";
                    umurpr="";
                    switch (rs.getString("jk")) {
                        case "L":
                            umurlk=rs.getString("umur");
                            laki++;
                            break;
                        case "P":
                            umurpr=rs.getString("umur");
                            per++;
                            break;
                    }
                    diagnosa="";
                    ps2=koneksi.prepareStatement(
                            "select penyakit.nm_penyakit from penyakit inner join diagnosa_pasien " +
                            "on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit " +
                            "where diagnosa_pasien.no_rawat=? order by prioritas asc limit 1");
                    try {                                    
                        ps2.setString(1,rs.getString("no_rawat"));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){
                            diagnosa=rs2.getString(1);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    tabMode2.addRow(new Object[]{
                        i,
                        setlama,
                        setbaru,
                        rs.getString("nm_pasien"),
                        umurlk,
                        umurpr,
                        rs.getString("almt_pj"),
                        diagnosa,
                        rs.getString("kd_kamar") + " " + rs.getString("nm_bangsal"),
                        rs.getString("stts_pulang"),
                        rs.getString("tgl_keluar"),
                        rs.getString("nm_dokter")
                    });    
                    i++;
                }
                if(i>=2){
                    tabMode2.addRow(new Object[]{
                        ">>",lama,baru,"",laki,per,"","",""
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps3!=null){
                    ps3.close();
                }
            }    
            this.setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void getData() {
        int row=tbBangsal.getSelectedRow();
        if(row!= -1){
            TKd.setText(tabMode.getValueAt(row,0).toString());
        }
    }
    
    public void emptText() {
        userBerizin.setText("");
        kdAkses.setText("");
        ruangDicetak.setText("");
    }
    
    public void UserValid() {
        userBerizin.setText(Sequel.cariIsi("SELECT nip FROM hak_akses_unit WHERE nip='" + akses.getkode() + "' "));

        if (akses.getkode().equals("Admin Utama")) {
            BtnCari.setEnabled(true);            
            cmbRuangan.setSelectedIndex(0);
            cmbRuangan.setEnabled(true);
            cmbRuangan.setVisible(true);
            cmbRuangKhusus1.setVisible(false);
            cmbRuangKhusus2.setVisible(false);
            cmbRuangKhusus3.setVisible(false);
            cmbRuangKhusus4.setVisible(false);
            tampil();
            
        } else if (!userBerizin.getText().equals("") || !akses.getkode().equals("Admin Utama") || !userBerizin.getText().equals("PR13")) {
            
            if (userBerizin.getText().equals("PR04")) {
                BtnCari.setEnabled(true);
                cmbRuangKhusus1.setVisible(true);
                cmbRuangKhusus1.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                kdAkses.setText("");
            
            } else if (userBerizin.getText().equals("PR06")) {
                BtnCari.setEnabled(true);
                cmbRuangKhusus2.setVisible(true);
                cmbRuangKhusus2.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                kdAkses.setText("");
            
            } else if (userBerizin.getText().equals("PR10")) {
                BtnCari.setEnabled(true);
                cmbRuangKhusus3.setVisible(true);
                cmbRuangKhusus3.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                kdAkses.setText("");
            
            } else if (userBerizin.getText().equals("PR07")) {
                BtnCari.setEnabled(true);
                cmbRuangKhusus4.setVisible(true);
                cmbRuangKhusus4.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                kdAkses.setText("");

            } else {
                BtnCari.setEnabled(true);
                cmbRuangan.setEnabled(false);
                cmbRuangan.setVisible(true);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);

                formWindowOpened(null);
                kdAkses.setText(Sequel.cariIsi("SELECT kode_unit FROM hak_akses_unit WHERE nip='" + akses.getkode() + "' "));
                cmbRuangan.setSelectedItem(kdAkses.getText());
                tampil();
            }
        }
    }
                   
    public void ctkRuangArRaudah() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        if (ruangDicetak.getText().equals("AR-RAUDAH")) {
            param.put("nm_ruangan", "DI RUANGAN AR-RAUDAH");
        } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
            param.put("nm_ruangan", "DI RUANGAN AR-RAUDAH ATAS (MATA, THT, KULKEL)");
        } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
            param.put("nm_ruangan", "DI RUANGAN AR-RAUDAH BAWAH (SARAF)");
        }
        
        if (ruangDicetak.getText().equals("AR-RAUDAH")) {
            Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                    " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                    + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                    + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND reg_periksa.stts_daftar = 'Lama' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                    + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                    + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND reg_periksa.stts_daftar = 'Baru' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                    + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                    + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' And pasien.jk = 'L' GROUP BY reg_periksa.no_rawat "
                    + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                    + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                    + " And pasien.jk = 'P' GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                    + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                    + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                    + "  GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);

        } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
            Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                    " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                    + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                    + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Atas%' AND reg_periksa.stts_daftar = 'Lama' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                    + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                    + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Atas%' AND reg_periksa.stts_daftar = 'Baru' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                    + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                    + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Atas%' And pasien.jk = 'L' GROUP BY reg_periksa.no_rawat "
                    + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                    + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Atas%' "
                    + " And pasien.jk = 'P' GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                    + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                    + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Atas%' "
                    + "  GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);

        } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
            Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                    " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                    + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                    + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' AND reg_periksa.stts_daftar = 'Lama' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                    + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                    + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' AND reg_periksa.stts_daftar = 'Baru' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                    + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                    + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' And pasien.jk = 'L' GROUP BY reg_periksa.no_rawat "
                    + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                    + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' "
                    + " And pasien.jk = 'P' GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                    + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                    + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' "
                    + "  GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void ctkRuangAsSami() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        if (ruangDicetak.getText().equals("AS-SAMI")) {
            param.put("nm_ruangan", "DI RUANGAN " + ruangDicetak.getText());
        } else if (ruangDicetak.getText().equals("AS-SAMI/1")) {
            param.put("nm_ruangan", "DI RUANGAN AS-SAMI/JANTUNG DAN LAINNYA");
        } else if (ruangDicetak.getText().equals("AS-SAMI/2")) {
            param.put("nm_ruangan", "DI RUANGAN AS-SAMI/KEMOTERAPI");
        }

        if (ruangDicetak.getText().equals("AS-SAMI")) {
            Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                    " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                    + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                    + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND reg_periksa.stts_daftar = 'Lama' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                    + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                    + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND reg_periksa.stts_daftar = 'Baru' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                    + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                    + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' And pasien.jk = 'L' GROUP BY reg_periksa.no_rawat "
                    + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                    + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                    + " And pasien.jk = 'P' GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                    + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                    + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                    + "  GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);

        } else if (ruangDicetak.getText().equals("AS-SAMI/1") || ruangDicetak.getText().equals("AS-SAMI/2")) {
            Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                    " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                    + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                    + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' AND reg_periksa.stts_daftar = 'Lama' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                    + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                    + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' AND reg_periksa.stts_daftar = 'Baru' "
                    + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                    + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                    + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' And pasien.jk = 'L' GROUP BY reg_periksa.no_rawat "
                    + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                    + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                    + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' "
                    + " And pasien.jk = 'P' GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                    + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                    + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                    + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                    + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                    + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' "
                    + "  GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void ctkRuangPeriBayi() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("nm_ruangan", "DI RUANGAN " + ruangDicetak.getText());

        Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung in ('PERINATOLOGI','BAYI SEHAT') AND reg_periksa.stts_daftar = 'Lama' "
                + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung in ('PERINATOLOGI','BAYI SEHAT') AND reg_periksa.stts_daftar = 'Baru' "
                + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung in ('PERINATOLOGI','BAYI SEHAT') And pasien.jk = 'L' GROUP BY reg_periksa.no_rawat "
                + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung in ('PERINATOLOGI','BAYI SEHAT') "
                + " And pasien.jk = 'P' GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung in ('PERINATOLOGI','BAYI SEHAT') "
                + "  GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void ctkPerRuangan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("nm_ruangan", "DI RUANGAN " + ruangDicetak.getText());
       
        Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '"+ ruangDicetak.getText() +"' AND reg_periksa.stts_daftar = 'Lama' "
                + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '"+ ruangDicetak.getText() +"' AND reg_periksa.stts_daftar = 'Baru' "
                + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '"+ ruangDicetak.getText() +"' And pasien.jk = 'L' GROUP BY reg_periksa.no_rawat "
                + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '"+ ruangDicetak.getText() +"' "
                + " And pasien.jk = 'P' GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and bangsal.nm_gedung = '"+ ruangDicetak.getText() +"' "
                +"  GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void ctkSemuaRuang() {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("nm_ruangan", "DI " + ruangDicetak.getText() + "AN");
        Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND reg_periksa.stts_daftar = 'Lama' "
                + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND reg_periksa.stts_daftar = 'Baru' "
                + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' And pasien.jk = 'L' GROUP BY reg_periksa.no_rawat "
                + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " And pasien.jk = 'P' GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                +"  GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void ctkRuangRKPDzaal() {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("nm_ruangan", "DI RUANGAN INTERNIST");        
        
        Valid.MyReport("rptKunjunganRanap.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Inap Per Ruangan ]::",
                " SELECT a.tgl_registrasi, (SELECT count(x.stts_daftar) Total FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, "
                + " pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, "
                + " reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal "
                + " AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " AND reg_periksa.stts_daftar = 'Lama' and bangsal.nm_gedung in ('RKPD','ZAAL') "
                + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Lama, (SELECT count(x.stts_daftar) Total "
                + " FROM (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat "
                + " AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                +"  AND reg_periksa.stts_daftar = 'Baru' and bangsal.nm_gedung in ('RKPD','ZAAL') "
                + " GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS x ) AS Total_Baru, IF (a.stts_daftar = 'Lama',a.no_rkm_medis,'') AS Lama, "
                + " IF (a.stts_daftar = 'Baru',a.no_rkm_medis,'') AS Baru, a.nm_pasien, a.alamat, (select count(x.stts_daftar) Tota "
                + " from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " And pasien.jk = 'L' and bangsal.nm_gedung in ('RKPD','ZAAL') GROUP BY reg_periksa.no_rawat "
                + " ORDER BY kamar_inap.tgl_masuk) as x) AS Total_LK, (select count(x.stts_daftar) Total from (SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, "
                + " bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa "
                + " INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " And pasien.jk = 'P' and bangsal.nm_gedung in ('RKPD','ZAAL') GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) as x) AS Total_PR, "
                + " IF (a.jk = 'L', a.umur, '') AS LK, IF (a.jk = 'P', a.umur, '') AS PR, a.tgl_daftar, a.nm_bangsal, a.diagnosa_awal, a.almt_pj, a.stts_pulang, a.tgl_masuk, a.nm_dokter "
                + " FROM ((SELECT reg_periksa.tgl_registrasi, reg_periksa.stts_daftar, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.alamat, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, pasien.tgl_daftar, bangsal.nm_bangsal, reg_periksa.almt_pj, kamar_inap.stts_pulang, kamar_inap.diagnosa_awal, "
                + " kamar_inap.tgl_masuk, dokter.nm_dokter FROM reg_periksa INNER JOIN pasien INNER JOIN kamar_inap INNER JOIN kamar INNER JOIN bangsal "
                + " INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.no_rawat = kamar_inap.no_rawat AND kamar_inap.kd_kamar = kamar.kd_kamar "
                + " AND kamar.kd_bangsal = bangsal.kd_bangsal AND reg_periksa.kd_dokter = dokter.kd_dokter WHERE reg_periksa.status_lanjut = 'Ranap' AND reg_periksa.stts <> 'Batal' AND kamar_inap.stts_pulang not in ('-','Pindah Kamar') "
                + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                +"  and bangsal.nm_gedung in ('RKPD','ZAAL') GROUP BY reg_periksa.no_rawat ORDER BY kamar_inap.tgl_masuk) AS a)", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
}
