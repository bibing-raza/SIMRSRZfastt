/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPemberianObat.java
 *
 * Created on 27 Mei 10, 14:52:31
 */

package simrskhanza;

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
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;

/**
 *
 * @author perpustakaan
 */
public class DlgDpjp extends javax.swing.JDialog {
    private final DefaultTableModel TabModePasien,tabModeDiagnosa;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private DlgPasien pasien=new DlgPasien(null,false);
    private PreparedStatement ps,ps2;
    private ResultSet rs;
    private int jml = 0, i = 0, index = 0, kamarPasien = 0, jmlDPJP = 0, pilihDokter = 0, d = 0;
    private String[] kode, nama;
    private String kodeDR = "";
    private boolean[] pilih; 

    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
    public DlgDpjp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row={"P","Tgl.Rawat","No.Rawat","No.R.M.","Nama Pasien","Kode Dokter","Nama Dokter"};
        TabModePasien=new DefaultTableModel(null,row){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPasien.setModel(TabModePasien);
        //tampil("");

        tbPasien.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(90);
            }else if(i==2){
                column.setPreferredWidth(110);
            }else if(i==3){
                column.setPreferredWidth(70);
            }else if(i==4){
                column.setPreferredWidth(200);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(200);
            }
        }
        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        Object[] row2={"P","Kode Dokter","Nama Dokter"};
        tabModeDiagnosa=new DefaultTableModel(null,row2){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDokter.setModel(tabModeDiagnosa);
        //tbDokter.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbDokter.getBackground()));
        tbDokter.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i= 0; i < 3; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(300);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());

        this.setLocation(8,1);
        setSize(885,674);

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        TCariPasien.setDocument(new batasInput((byte)20).getKata(TCariPasien));     
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        } 
        if(koneksiDB.cariCepat().equals("aktif")){
            Dokter.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampildokter();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampildokter();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampildokter();}
            });
        } 
        ChkInput.setSelected(false);
        isForm();
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pasien.getTable().getSelectedRow()!= -1){                   
                    TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                } 
                TCariPasien.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });        
    }

    //private DlgCariObatDokter dlgobtpny=new DlgCariObatDokter(null,false);
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPasien = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel16 = new widget.Label();
        TCariPasien = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel13 = new widget.Label();
        Tanggal = new widget.TextBox();
        jLabel17 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        Dokter = new widget.TextBox();
        BtnCari1 = new widget.Button();
        btnTambah = new widget.Button();
        jLabel4 = new widget.Label();
        jLabel5 = new widget.Label();
        ChkInput = new widget.CekBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dokter Penanggung Jawab Pelayanan Pasien Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPasien.setAutoCreateRowSorter(true);
        tbPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPasien.setName("tbPasien"); // NOI18N
        tbPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMouseClicked(evt);
            }
        });
        tbPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPasien);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass8.add(LCount);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("No.RM :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(jLabel16);

        TCariPasien.setForeground(new java.awt.Color(0, 0, 0));
        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(130, 23));
        TCariPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPasienKeyPressed(evt);
            }
        });
        panelGlass9.add(TCariPasien);

        BtnSeek4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("Alt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        BtnSeek4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek4KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnSeek4);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator5);

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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(865, 137));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(-2, 12, 80, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(81, 12, 175, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(81, 42, 143, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        FormInput.add(TPasien);
        TPasien.setBounds(81, 72, 360, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dokter :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(429, 12, 70, 23);

        Tanggal.setEditable(false);
        Tanggal.setForeground(new java.awt.Color(0, 0, 0));
        Tanggal.setHighlighter(null);
        Tanggal.setName("Tanggal"); // NOI18N
        FormInput.add(Tanggal);
        Tanggal.setBounds(81, 102, 140, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tgl.Rawat :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(-2, 102, 80, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll1.setForeground(new java.awt.Color(0, 0, 0));
        Scroll1.setName("Scroll1"); // NOI18N

        tbDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDokter.setName("tbDokter"); // NOI18N
        tbDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDokterMouseClicked(evt);
            }
        });
        tbDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDokterKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbDokter);

        FormInput.add(Scroll1);
        Scroll1.setBounds(458, 38, 420, 90);

        Dokter.setForeground(new java.awt.Color(0, 0, 0));
        Dokter.setHighlighter(null);
        Dokter.setName("Dokter"); // NOI18N
        Dokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DokterKeyPressed(evt);
            }
        });
        FormInput.add(Dokter);
        Dokter.setBounds(502, 12, 274, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnCari1);
        BtnCari1.setBounds(779, 12, 28, 23);

        btnTambah.setForeground(new java.awt.Color(0, 0, 0));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambah.setMnemonic('2');
        btnTambah.setToolTipText("Alt+2");
        btnTambah.setName("btnTambah"); // NOI18N
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        FormInput.add(btnTambah);
        btnTambah.setBounds(808, 12, 28, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nomer RM :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(-2, 42, 80, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(-2, 72, 80, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        Valid.pindah(evt,TCari,Dokter);
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        jml = 0;
        kamarPasien = 0;
        jmlDPJP = 0;
        pilihDokter = 0;
        
        kamarPasien = Sequel.cariInteger("SELECT COUNT(1) hasil FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE b.kd_bangsal LIKE '%bysh%' AND ki.stts_pulang IN ('-','Pindah Kamar') AND "
                + "ki.no_rawat='" + TNoRw.getText() + "'");
        jmlDPJP = Sequel.cariInteger("select count(1) hasil from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'");      
        
        for (i = 0; i < tbDokter.getRowCount(); i++) {
            if (tbDokter.getValueAt(i, 0).toString().equals("true")) {
                jml++;
            }
        }
        
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No. Rawat");
        } else if (jml == 0) {
            Valid.textKosong(Dokter, "Data Dokter");
        } else {
            if (kamarPasien == 0 && (kodeDR.equals("-") || kodeDR.equals("--"))) {
                JOptionPane.showMessageDialog(null, "Pilihlah DPJP pasien dengan benar...!");
                bersihkanDR();
                Dokter.setText("");
                tampildokter();
            } else if (jmlDPJP > 0) {
                JOptionPane.showMessageDialog(null, "DPJP pasien hanya boleh 1 orang dokter saja utk. setiap pasien yg. dirawat...!!");
                bersihkanDR();
                Dokter.setText("");
                tampildokter();
            } else {
                for (i = 0; i < tbDokter.getRowCount(); i++) {
                    if (tbDokter.getValueAt(i, 0).toString().equals("true")) {
                        pilihDokter++;
                    }
                }

                if (pilihDokter > 1) {
                    JOptionPane.showMessageDialog(null, "DPJP pasien hanya boleh 1 orang saja,..!!");
                    bersihkanDR();
                    Dokter.setText("");
                    tampildokter();
                    tampil();
                } else {
                    simpanDokterTerpilih();
                    bersihkanDR();
                    Dokter.setText("");
                    tampildokter();
                    tampil();
                }
                
                if (pilihDokter == 1) {
                    for (i = 0; i < tbDokter.getRowCount(); i++) {
                        if (tbDokter.getValueAt(i, 0).toString().equals("true")) {
                            simpanDokterTerpilih();
                            bersihkanDR();
                            Dokter.setText("");
                            tampildokter();
                            tampil();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan pilih salah satu dokternya terlebih dulu...");
                    bersihkanDR();
                    Dokter.setText("");
                    tampildokter();
                    tampil();
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Dokter,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Dokter.setText("");
        TNoRw.requestFocus();
        for(i=0;i<tbDokter.getRowCount();i++){ 
            tbDokter.setValueAt(false,i,0);
        }
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBatalActionPerformed(null);
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbPasien.getRowCount()==0){
             JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
             TNoRw.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
             JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        }else if(!(TPasien.getText().trim().equals(""))){
            for(i=0;i<tbPasien.getRowCount();i++){ 
                if(tbPasien.getValueAt(i,0).toString().equals("true")){
                    Sequel.queryu2("delete from dpjp_ranap where no_rawat=? and kd_dokter=?",2,new String[]{
                        tbPasien.getValueAt(i,2).toString(),tbPasien.getValueAt(i,5).toString()
                    });
                }
            }
        }

        tampil();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnHapus,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TCariPasien.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TCariPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPasienKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampil();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnSeek4.requestFocus();
        }
    }//GEN-LAST:event_TCariPasienKeyPressed

    private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        pasien.emptTeks();    
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnSeek4ActionPerformed

    private void BtnSeek4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek4KeyPressed
        Valid.pindah(evt,TCariPasien,BtnCari);
    }//GEN-LAST:event_BtnSeek4KeyPressed

    private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
        if(TabModePasien.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            
           
        }
}//GEN-LAST:event_tbPasienMouseClicked

    private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
        if(TabModePasien.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPasienKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void DokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DokterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampildokter();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (akses.getpenyakit() == true) {
                btnTambahActionPerformed(null);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDokter.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_DokterKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if(!TNoRw.getText().equals("")){
            tampildokter();
        }
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDokter tariflab=new DlgDokter(null,false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        tariflab.setLocationRelativeTo(internalFrame1);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahActionPerformed

    private void tbDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDokterKeyPressed
        if (tbDokter.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDokter.getSelectedColumn();
                    if (i == 1) {
                        if (tbDokter.getSelectedRow() > -1) {
                            tbDokter.setValueAt(true, tbDokter.getSelectedRow(), 0);
                            getDatadr();
                        }
                        Dokter.setText("");
                        Dokter.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                Dokter.setText("");
                Dokter.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDokterKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampildokter();
    }//GEN-LAST:event_formWindowOpened

    private void tbDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDokterMouseClicked
        if(tabModeDiagnosa.getRowCount()!=0){
            try {
                getDatadr();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDokterMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDpjp dialog = new DlgDpjp(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.TextBox Dokter;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tanggal;
    private widget.Button btnTambah;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel13;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator5;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDokter;
    private widget.Table tbPasien;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(TabModePasien);
        try {
            ps2 = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,dpjp_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "dpjp_ranap.kd_dokter,dokter.nm_dokter from dpjp_ranap inner join reg_periksa inner join pasien inner join dokter "
                    + "on dpjp_ranap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and dpjp_ranap.kd_dokter=dokter.kd_dokter "
                    + "where reg_periksa.no_rkm_medis like ? and dpjp_ranap.no_rawat like ? or "
                    + "reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.no_rkm_medis like ? and dokter.kd_dokter like ? or "
                    + "reg_periksa.no_rkm_medis like ? and dokter.nm_dokter like ? "
                    + "order by reg_periksa.tgl_registrasi ");
            try {
                ps2.setString(1, "%" + TCariPasien.getText().trim() + "%");
                ps2.setString(2, "%" + TCari.getText().trim() + "%");
                ps2.setString(3, "%" + TCariPasien.getText().trim() + "%");
                ps2.setString(4, "%" + TCari.getText().trim() + "%");
                ps2.setString(5, "%" + TCariPasien.getText().trim() + "%");
                ps2.setString(6, "%" + TCari.getText().trim() + "%");
                ps2.setString(7, "%" + TCariPasien.getText().trim() + "%");
                ps2.setString(8, "%" + TCari.getText().trim() + "%");
                rs = ps2.executeQuery();
                while (rs.next()) {
                    TabModePasien.addRow(new Object[]{false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
            LCount.setText("" + TabModePasien.getRowCount());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ",TNoRM,TNoRw.getText());
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }


    private void getData() {
        if(tbPasien.getSelectedRow()!= -1){
            TNoRw.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),2).toString());
            isRawat();
            isPsien();
            Tanggal.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),1).toString());
        }
    }
    
    public void setNoRm(String norwt, String status) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Tanggal.setText(status);
        isRawat();
        isPsien();   
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,158));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdpjp_ranap());
        BtnHapus.setEnabled(akses.getdpjp_ranap());
        btnTambah.setEnabled(akses.getdokter());        
    }

    private void tampildokter() {
        try{
            jml=0;
            for(i=0;i<tbDokter.getRowCount();i++){
                if(tbDokter.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml];
            kode=null;
            kode=new String[jml];
            nama=null;
            nama=new String[jml];

            index=0; 
            for(i=0;i<tbDokter.getRowCount();i++){
                if(tbDokter.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbDokter.getValueAt(i,1).toString();
                    nama[index]=tbDokter.getValueAt(i,2).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosa);
            for(i=0;i<jml;i++){
                tabModeDiagnosa.addRow(new Object[] {pilih[i],kode[i],nama[i]});
            }   
            
            ps=koneksi.prepareStatement("select dokter.kd_dokter,dokter.nm_dokter from dokter "+
                        "where status='1' and dokter.kd_dokter like ? or status='1' and dokter.nm_dokter like ? order by dokter.nm_dokter");            
            try {
                ps.setString(1,"%"+Dokter.getText().trim()+"%");
                ps.setString(2,"%"+Dokter.getText().trim()+"%");            
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModeDiagnosa.addRow(new Object[]{false,rs.getString(1),rs.getString(2)});
                }                   
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }         
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void getDatadr() {
        kodeDR = "";
        
        i = tbDokter.getSelectedRow();
        if (i != -1) {
            kodeDR = tbDokter.getValueAt(i, 1).toString();
        }
    }
    
    private void bersihkanDR () {
        for (i = 0; i < tbDokter.getRowCount(); i++) {
            tbDokter.setValueAt(false, i, 0);
        }
    }
    
    private void simpanDokterTerpilih() {
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbDokter.getRowCount(); i++) {
                if (tbDokter.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("dpjp_ranap", "?,?", "Dokter", 2, new String[]{
                        TNoRw.getText(), tbDokter.getValueAt(i, 1).toString()
                    });
                }
            }
            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya...!");
        }
    }
}
