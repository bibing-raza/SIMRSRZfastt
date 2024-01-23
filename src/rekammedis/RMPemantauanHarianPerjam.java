    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class RMPemantauanHarianPerjam extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private String urutanJam = "";

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public RMPemantauanHarianPerjam(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();


        Object[] row={"Kode","Nama Kasus Persalinan"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbPantau.setModel(tabMode);
        tbPantau.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPantau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbPantau.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(50);
            } else if (i == 1) {
                column.setPreferredWidth(470);
            }
        }
        tbPantau.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Tanggal", "Jam", "Nama Obat", "Jlh. Beri (cc)", "Kode Pemantauan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbParental.setModel(tabMode1);
        tbParental.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbParental.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbParental.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            }
        }
        tbParental.setDefaultRenderer(Object.class, new WarnaTable());  

        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        Tnadi.setDocument(new batasInput((byte) 3).getOnlyAngka(Tnadi));
        Tsuhu.setDocument(new batasInput((int) 4).getKata(Tsuhu));
        Tbb.setDocument(new batasInput((byte) 4).getKata(Tbb));
        Tgcse.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcse));
        Tgcsm.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcsm));
        Tgcsv.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcsv));
        Ttensi.setDocument(new batasInput((byte) 7).getKata(Ttensi));
        Trr.setDocument(new batasInput((byte) 7).getKata(Trr));
        Tspo.setDocument(new batasInput((byte) 7).getKata(Tspo));
        Tmm.setDocument(new batasInput((byte) 3).getOnlyAngka(Tmm));
        Tngt.setDocument(new batasInput((byte) 3).getOnlyAngka(Tngt));
        Tline.setDocument(new batasInput((byte) 3).getOnlyAngka(Tline));
        Turin.setDocument(new batasInput((byte) 3).getOnlyAngka(Turin));
        TngtDarah.setDocument(new batasInput((byte) 3).getOnlyAngka(TngtDarah));
        Tdrain.setDocument(new batasInput((byte) 3).getOnlyAngka(Tdrain));
        Tmuntah.setDocument(new batasInput((byte) 3).getOnlyAngka(Tmuntah));
        Tbab.setDocument(new batasInput((byte) 3).getOnlyAngka(Tbab));

        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
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

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPantau = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnParental = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass10 = new widget.panelisi();
        panelGlass7 = new widget.panelisi();
        jLabel5 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        tglPantau = new widget.Tanggal();
        jLabel9 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel11 = new widget.Label();
        cmbJam = new widget.ComboBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        Tgcse = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tgcsm = new widget.TextBox();
        jLabel15 = new widget.Label();
        Tgcsv = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tkesadaran = new widget.TextBox();
        jLabel17 = new widget.Label();
        Ttensi = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel18 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel19 = new widget.Label();
        Tspo = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel20 = new widget.Label();
        Tmm = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        Tngt = new widget.TextBox();
        jLabel42 = new widget.Label();
        Tline = new widget.TextBox();
        jLabel44 = new widget.Label();
        Ttotintake = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel46 = new widget.Label();
        Turin = new widget.TextBox();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        TngtDarah = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        Tdrain = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        Tmuntah = new widget.TextBox();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        Tbab = new widget.TextBox();
        jLabel55 = new widget.Label();
        Tiwl = new widget.TextBox();
        jLabel57 = new widget.Label();
        Ttotouput = new widget.TextBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        Tbalance = new widget.TextBox();
        jLabel60 = new widget.Label();
        jLabel24 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        TkdPantau = new widget.TextBox();
        BtnIWL = new widget.Button();
        BtnHitungPerental = new widget.Button();
        BtnTotIntake = new widget.Button();
        BtnTotOutput = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbParental = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemantauan Harian Pasien PerJam ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPantau.setAutoCreateRowSorter(true);
        tbPantau.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPantau.setName("tbPantau"); // NOI18N
        tbPantau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPantauMouseClicked(evt);
            }
        });
        tbPantau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPantauKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPantauKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbPantau);

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

        BtnParental.setForeground(new java.awt.Color(0, 0, 0));
        BtnParental.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnParental.setMnemonic('P');
        BtnParental.setText("Parental/Line/Obat");
        BtnParental.setToolTipText("Alt+P");
        BtnParental.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnParental.setName("BtnParental"); // NOI18N
        BtnParental.setPreferredSize(new java.awt.Dimension(155, 30));
        BtnParental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnParentalActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnParental);

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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCariKeyReleased(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelGlass9.add(BtnAll);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 270));
        panelGlass10.setLayout(new java.awt.GridLayout(1, 2));

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 270));
        panelGlass7.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass7.add(TNoRw);
        TNoRw.setBounds(115, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelGlass7.add(TNoRm);
        TNoRm.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(314, 10, 301, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Pemantauan :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(0, 38, 110, 23);

        tglPantau.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-01-2024" }));
        tglPantau.setDisplayFormat("dd-MM-yyyy");
        tglPantau.setName("tglPantau"); // NOI18N
        tglPantau.setOpaque(false);
        tglPantau.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(tglPantau);
        tglPantau.setBounds(115, 38, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nadi :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(205, 38, 40, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        panelGlass7.add(Tnadi);
        Tnadi.setBounds(250, 38, 50, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Suhu :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(300, 38, 40, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        panelGlass7.add(Tsuhu);
        Tsuhu.setBounds(345, 38, 50, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(395, 38, 35, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "01", "02", "03", "04", "05" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(435, 38, 45, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("GCS :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(0, 66, 110, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("E :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(115, 66, 15, 23);

        Tgcse.setForeground(new java.awt.Color(0, 0, 0));
        Tgcse.setName("Tgcse"); // NOI18N
        Tgcse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcseKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcse);
        Tgcse.setBounds(135, 66, 45, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("M :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(186, 66, 15, 23);

        Tgcsm.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsm.setName("Tgcsm"); // NOI18N
        Tgcsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsmKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcsm);
        Tgcsm.setBounds(206, 66, 45, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("V :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(255, 66, 15, 23);

        Tgcsv.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsv.setName("Tgcsv"); // NOI18N
        Tgcsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsvKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcsv);
        Tgcsv.setBounds(274, 66, 45, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Kesadaran :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(320, 66, 70, 23);

        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        panelGlass7.add(Tkesadaran);
        Tkesadaran.setBounds(395, 66, 220, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tekanan Darah :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(0, 94, 110, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        panelGlass7.add(Ttensi);
        Ttensi.setBounds(115, 94, 70, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("mmHg");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass7.add(jLabel36);
        jLabel36.setBounds(190, 94, 40, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Frek. Nafas / RR :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(230, 94, 100, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        panelGlass7.add(Trr);
        Trr.setBounds(335, 94, 70, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("x/menit");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass7.add(jLabel40);
        jLabel40.setBounds(410, 94, 50, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("SPO2 :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass7.add(jLabel19);
        jLabel19.setBounds(460, 94, 40, 23);

        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        panelGlass7.add(Tspo);
        Tspo.setBounds(505, 94, 50, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("%");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass7.add(jLabel41);
        jLabel41.setBounds(560, 94, 20, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("INTAKE :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(0, 122, 110, 23);

        Tmm.setForeground(new java.awt.Color(0, 0, 0));
        Tmm.setName("Tmm"); // NOI18N
        Tmm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmmKeyPressed(evt);
            }
        });
        panelGlass7.add(Tmm);
        Tmm.setBounds(200, 122, 45, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Makan Minum :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass7.add(jLabel37);
        jLabel37.setBounds(115, 122, 80, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("cc.");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass7.add(jLabel38);
        jLabel38.setBounds(250, 122, 20, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("NGT :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelGlass7.add(jLabel39);
        jLabel39.setBounds(270, 122, 45, 23);

        Tngt.setForeground(new java.awt.Color(0, 0, 0));
        Tngt.setName("Tngt"); // NOI18N
        Tngt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TngtKeyPressed(evt);
            }
        });
        panelGlass7.add(Tngt);
        Tngt.setBounds(320, 122, 45, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("cc.");
        jLabel42.setName("jLabel42"); // NOI18N
        panelGlass7.add(jLabel42);
        jLabel42.setBounds(370, 122, 20, 23);

        Tline.setEditable(false);
        Tline.setForeground(new java.awt.Color(0, 0, 0));
        Tline.setName("Tline"); // NOI18N
        panelGlass7.add(Tline);
        Tline.setBounds(545, 122, 55, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("cc.");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass7.add(jLabel44);
        jLabel44.setBounds(605, 122, 20, 23);

        Ttotintake.setEditable(false);
        Ttotintake.setForeground(new java.awt.Color(0, 0, 0));
        Ttotintake.setName("Ttotintake"); // NOI18N
        panelGlass7.add(Ttotintake);
        Ttotintake.setBounds(160, 150, 60, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("cc.");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass7.add(jLabel45);
        jLabel45.setBounds(225, 150, 20, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("OUTPUT :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(0, 178, 110, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Urine :");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass7.add(jLabel46);
        jLabel46.setBounds(115, 178, 40, 23);

        Turin.setForeground(new java.awt.Color(0, 0, 0));
        Turin.setName("Turin"); // NOI18N
        Turin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TurinKeyPressed(evt);
            }
        });
        panelGlass7.add(Turin);
        Turin.setBounds(160, 178, 45, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("cc.");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass7.add(jLabel47);
        jLabel47.setBounds(210, 178, 20, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("NGT / Darah :");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass7.add(jLabel48);
        jLabel48.setBounds(235, 178, 80, 23);

        TngtDarah.setForeground(new java.awt.Color(0, 0, 0));
        TngtDarah.setName("TngtDarah"); // NOI18N
        TngtDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TngtDarahKeyPressed(evt);
            }
        });
        panelGlass7.add(TngtDarah);
        TngtDarah.setBounds(320, 178, 45, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("cc.");
        jLabel49.setName("jLabel49"); // NOI18N
        panelGlass7.add(jLabel49);
        jLabel49.setBounds(370, 178, 20, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Drain :");
        jLabel50.setName("jLabel50"); // NOI18N
        panelGlass7.add(jLabel50);
        jLabel50.setBounds(390, 178, 50, 23);

        Tdrain.setForeground(new java.awt.Color(0, 0, 0));
        Tdrain.setName("Tdrain"); // NOI18N
        Tdrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdrainKeyPressed(evt);
            }
        });
        panelGlass7.add(Tdrain);
        Tdrain.setBounds(445, 178, 45, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("cc.");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass7.add(jLabel51);
        jLabel51.setBounds(495, 178, 20, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Muntah :");
        jLabel52.setName("jLabel52"); // NOI18N
        panelGlass7.add(jLabel52);
        jLabel52.setBounds(95, 206, 60, 23);

        Tmuntah.setForeground(new java.awt.Color(0, 0, 0));
        Tmuntah.setName("Tmuntah"); // NOI18N
        Tmuntah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmuntahKeyPressed(evt);
            }
        });
        panelGlass7.add(Tmuntah);
        Tmuntah.setBounds(160, 206, 45, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("cc.");
        jLabel53.setName("jLabel53"); // NOI18N
        panelGlass7.add(jLabel53);
        jLabel53.setBounds(210, 206, 20, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("BAB :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass7.add(jLabel54);
        jLabel54.setBounds(235, 206, 80, 23);

        Tbab.setForeground(new java.awt.Color(0, 0, 0));
        Tbab.setName("Tbab"); // NOI18N
        Tbab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbabKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbab);
        Tbab.setBounds(320, 206, 45, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("cc.");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass7.add(jLabel55);
        jLabel55.setBounds(370, 206, 20, 23);

        Tiwl.setEditable(false);
        Tiwl.setForeground(new java.awt.Color(0, 0, 0));
        Tiwl.setName("Tiwl"); // NOI18N
        panelGlass7.add(Tiwl);
        Tiwl.setBounds(465, 206, 45, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("cc.");
        jLabel57.setName("jLabel57"); // NOI18N
        panelGlass7.add(jLabel57);
        jLabel57.setBounds(515, 206, 20, 23);

        Ttotouput.setEditable(false);
        Ttotouput.setForeground(new java.awt.Color(0, 0, 0));
        Ttotouput.setName("Ttotouput"); // NOI18N
        panelGlass7.add(Ttotouput);
        Ttotouput.setBounds(160, 234, 60, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("cc.");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass7.add(jLabel58);
        jLabel58.setBounds(225, 234, 20, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("BALANCE :");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass7.add(jLabel59);
        jLabel59.setBounds(240, 234, 60, 23);

        Tbalance.setEditable(false);
        Tbalance.setForeground(new java.awt.Color(0, 0, 0));
        Tbalance.setName("Tbalance"); // NOI18N
        panelGlass7.add(Tbalance);
        Tbalance.setBounds(305, 234, 60, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("cc.");
        jLabel60.setName("jLabel60"); // NOI18N
        panelGlass7.add(jLabel60);
        jLabel60.setBounds(370, 234, 20, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("BB Masuk RS :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(480, 38, 80, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbb);
        Tbb.setBounds(565, 38, 50, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("Kg.");
        jLabel61.setName("jLabel61"); // NOI18N
        panelGlass7.add(jLabel61);
        jLabel61.setBounds(620, 38, 20, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Kode Pemantauan :");
        jLabel62.setName("jLabel62"); // NOI18N
        panelGlass7.add(jLabel62);
        jLabel62.setBounds(390, 234, 110, 23);

        TkdPantau.setEditable(false);
        TkdPantau.setForeground(new java.awt.Color(0, 0, 0));
        TkdPantau.setName("TkdPantau"); // NOI18N
        panelGlass7.add(TkdPantau);
        TkdPantau.setBounds(505, 234, 130, 23);

        BtnIWL.setForeground(new java.awt.Color(0, 0, 0));
        BtnIWL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnIWL.setText("IWL : ");
        BtnIWL.setToolTipText("");
        BtnIWL.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnIWL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnIWL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnIWL.setName("BtnIWL"); // NOI18N
        BtnIWL.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnIWL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIWLActionPerformed(evt);
            }
        });
        BtnIWL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnIWLKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnIWL);
        BtnIWL.setBounds(390, 206, 70, 23);

        BtnHitungPerental.setForeground(new java.awt.Color(0, 0, 0));
        BtnHitungPerental.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnHitungPerental.setText("Jlh. Parental / Line :");
        BtnHitungPerental.setToolTipText("");
        BtnHitungPerental.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnHitungPerental.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnHitungPerental.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnHitungPerental.setName("BtnHitungPerental"); // NOI18N
        BtnHitungPerental.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHitungPerental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHitungPerentalActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHitungPerental);
        BtnHitungPerental.setBounds(390, 122, 150, 23);

        BtnTotIntake.setForeground(new java.awt.Color(0, 0, 0));
        BtnTotIntake.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnTotIntake.setText("TOTAL INTAKE :");
        BtnTotIntake.setToolTipText("");
        BtnTotIntake.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnTotIntake.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnTotIntake.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnTotIntake.setName("BtnTotIntake"); // NOI18N
        BtnTotIntake.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTotIntake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTotIntakeActionPerformed(evt);
            }
        });
        BtnTotIntake.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnTotIntakeKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnTotIntake);
        BtnTotIntake.setBounds(25, 150, 130, 23);

        BtnTotOutput.setForeground(new java.awt.Color(0, 0, 0));
        BtnTotOutput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnTotOutput.setText("TOTAL OUTPUT :");
        BtnTotOutput.setToolTipText("");
        BtnTotOutput.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnTotOutput.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnTotOutput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnTotOutput.setName("BtnTotOutput"); // NOI18N
        BtnTotOutput.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTotOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTotOutputActionPerformed(evt);
            }
        });
        BtnTotOutput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnTotOutputKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnTotOutput);
        BtnTotOutput.setBounds(25, 234, 130, 23);

        panelGlass10.add(panelGlass7);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Parental / Line / Obat-obatan ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbParental.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbParental.setName("tbParental"); // NOI18N
        tbParental.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbParentalMouseClicked(evt);
            }
        });
        tbParental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbParentalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbParentalKeyReleased(evt);
            }
        });
        Scroll1.setViewportView(tbParental);

        panelGlass10.add(Scroll1);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
//        if (kode.getText().trim().equals("")) {
//            Valid.textKosong(kode, "Kode");
//        } else if (nmkasus.getText().trim().equals("")) {
//            Valid.textKosong(nmkasus, "Nama Kasus Persalinan");
//            nmkasus.requestFocus();
//        } else {
//            Sequel.menyimpan("master_kasus_persalinan_dinkes", "'" + kode.getText() + "','" + nmkasus.getText() + "'", "Kode");
//            tampil();
//            emptTeks();
//        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnEdit);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
//        if (kode.getText().trim().equals("")) {
//            Valid.textKosong(kode, "Kode");
//        } else if (nmkasus.getText().trim().equals("")) {
//            Valid.textKosong(nmkasus, "Nama Kasus Persalinan");
//            nmkasus.requestFocus();
//        } else {
//            if (tbPantau.getSelectedRow() > -1) {
//                Sequel.mengedit("master_kasus_persalinan_dinkes", "kode=?", "kode=?,nama_kasus=?", 3, new String[]{
//                    kode.getText(), nmkasus.getText(), tbPantau.getValueAt(tbPantau.getSelectedRow(), 0).toString()
//                });
//                if (tabMode.getRowCount() != 0) {
//                    tampil();
//                }
//                emptTeks();
//            }
//        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        emptTeks();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbPantau.requestFocus();
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

    private void BtnCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_BtnCariKeyReleased

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
       emptTeks();
       tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPantauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPantauMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPantauMouseClicked

    private void tbPantauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPantauKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbPantauKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void tbPantauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPantauKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbPantauKeyReleased

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, tglPantau, Tsuhu);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        Valid.pindah(evt, Tnadi, cmbJam);
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, cmbJam, Tgcse);
    }//GEN-LAST:event_TbbKeyPressed

    private void TgcseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcseKeyPressed
        Valid.pindah(evt, Tbb, Tgcsm);
    }//GEN-LAST:event_TgcseKeyPressed

    private void TgcsmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsmKeyPressed
        Valid.pindah(evt, Tgcse, Tgcsv);
    }//GEN-LAST:event_TgcsmKeyPressed

    private void TgcsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsvKeyPressed
        Valid.pindah(evt, Tgcsm, Tkesadaran);
    }//GEN-LAST:event_TgcsvKeyPressed

    private void TkesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesadaranKeyPressed
        Valid.pindah(evt, Tgcsv, Ttensi);
    }//GEN-LAST:event_TkesadaranKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        Valid.pindah(evt, Tkesadaran, Trr);
    }//GEN-LAST:event_TtensiKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        Valid.pindah(evt, Ttensi, Tspo);
    }//GEN-LAST:event_TrrKeyPressed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        Valid.pindah(evt, Trr, Tmm);
    }//GEN-LAST:event_TspoKeyPressed

    private void TmmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmmKeyPressed
        Valid.pindah(evt, Tspo, Tngt);
    }//GEN-LAST:event_TmmKeyPressed

    private void TngtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TngtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnHitungPerentalActionPerformed(null);
            BtnTotIntakeActionPerformed(null);
            Turin.requestFocus();
        }
    }//GEN-LAST:event_TngtKeyPressed

    private void TurinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TurinKeyPressed
        Valid.pindah(evt, Tngt, TngtDarah);
    }//GEN-LAST:event_TurinKeyPressed

    private void TngtDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TngtDarahKeyPressed
        Valid.pindah(evt, Turin, Tdrain);
    }//GEN-LAST:event_TngtDarahKeyPressed

    private void TdrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdrainKeyPressed
        Valid.pindah(evt, TngtDarah, Tmuntah);
    }//GEN-LAST:event_TdrainKeyPressed

    private void TmuntahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmuntahKeyPressed
        Valid.pindah(evt, Tdrain, Tbab);
    }//GEN-LAST:event_TmuntahKeyPressed

    private void TbabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnIWLActionPerformed(null);
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TbabKeyPressed

    private void tbParentalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbParentalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbParentalMouseClicked

    private void tbParentalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbParentalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbParentalKeyPressed

    private void tbParentalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbParentalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbParentalKeyReleased

    private void BtnIWLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIWLActionPerformed
        hitungIWL();
    }//GEN-LAST:event_BtnIWLActionPerformed

    private void BtnIWLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnIWLKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnIWLActionPerformed(null);
        }
    }//GEN-LAST:event_BtnIWLKeyPressed

    private void BtnHitungPerentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHitungPerentalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHitungPerentalActionPerformed

    private void BtnTotIntakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTotIntakeActionPerformed
        hitungIntake();
    }//GEN-LAST:event_BtnTotIntakeActionPerformed

    private void BtnTotIntakeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnTotIntakeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnTotIntakeActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTotIntakeKeyPressed

    private void BtnTotOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTotOutputActionPerformed
        hitungOutput();
        hitungBalance();
    }//GEN-LAST:event_BtnTotOutputActionPerformed

    private void BtnTotOutputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnTotOutputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnTotOutputActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTotOutputKeyPressed

    private void BtnParentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnParentalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnParentalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
//        if (tbAsesmen.getSelectedRow() > -1) {
//            if (akses.getkode().equals("Admin Utama")) {
//                hapus();
//            } else {
//                if (nip.equals(akses.getkode())) {
//                    hapus();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString() + " ..!!");
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
//        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPemantauanHarianPerjam dialog = new RMPemantauanHarianPerjam(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHitungPerental;
    private widget.Button BtnIWL;
    private widget.Button BtnKeluar;
    private widget.Button BtnParental;
    private widget.Button BtnSimpan;
    private widget.Button BtnTotIntake;
    private widget.Button BtnTotOutput;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tbab;
    private widget.TextBox Tbalance;
    private widget.TextBox Tbb;
    private widget.TextBox Tdrain;
    private widget.TextBox Tgcse;
    private widget.TextBox Tgcsm;
    private widget.TextBox Tgcsv;
    private widget.TextBox Tiwl;
    private widget.TextBox TkdPantau;
    private widget.TextBox Tkesadaran;
    private widget.TextBox Tline;
    private widget.TextBox Tmm;
    private widget.TextBox Tmuntah;
    private widget.TextBox Tnadi;
    private widget.TextBox Tngt;
    private widget.TextBox TngtDarah;
    private widget.TextBox Trr;
    private widget.TextBox Tspo;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttensi;
    private widget.TextBox Ttotintake;
    private widget.TextBox Ttotouput;
    private widget.TextBox Turin;
    private widget.ComboBox cmbJam;
    private widget.InternalFrame internalFrame1;
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
    private widget.Label jLabel22;
    private widget.Label jLabel24;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbPantau;
    private widget.Table tbParental;
    private widget.Tanggal tglPantau;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select * from master_kasus_persalinan_dinkes where kode like ? or nama_kasus like ? order by kode");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString(1), 
                        rs.getString(2)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
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
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        tglPantau.setDate(new Date());
        tglPantau.requestFocus();
        Tnadi.setText("");        
        Tsuhu.setText("");
        cmbJam.setSelectedIndex(0);
        Tbb.setText("");
        Tgcse.setText("");
        Tgcsm.setText("");
        Tgcsv.setText("");
        Tkesadaran.setText("");
        Ttensi.setText("");
        Trr.setText("");
        Tspo.setText("");
        Tmm.setText("");
        Tngt.setText("");
        Tline.setText("");
        Ttotintake.setText("");
        Turin.setText("");
        TngtDarah.setText("");
        Tdrain.setText("");
        Tmuntah.setText("");
        Tbab.setText("");
        Tiwl.setText("");
        Ttotouput.setText("");
        Tbalance.setText("");
        urutanJam = "";
        autoNomorPerJam();
    }

    private void getData() {
        if(tbPantau.getSelectedRow()!= -1){
//            kode.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),0).toString());
//            nmkasus.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),1).toString());
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());       
       BtnEdit.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
    }
    
    private void hitungIWL() {
        try {
            double bb, Total_kali, Total_iwl;
            Total_kali = 0;
            Total_iwl = 0;
            if (Tbb.getText().equals("")) {
                Tbb.setText("0");
            }

            bb = Double.parseDouble(Tbb.getText());
            Total_kali = bb * 15;
            Total_iwl = Total_kali / 24;

            if (Valid.SetAngka(Total_iwl).equals("NaN")) {
                Tiwl.setText("0");
            } else {
                Tiwl.setText(Valid.SetAngka(Total_iwl));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka BB masuk RS,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
        }
    }
    
    private void hitungIntake() {
        try {
            double A, B, C, Total;
            Total = 0;
            A = 0;
            B = 0;
            C = 0;
            if (Tmm.getText().equals("")) {
                Tmm.setText("0");
            }
            
            if (Tngt.getText().equals("")) {
                Tngt.setText("0");
            }
            
            if (Tline.getText().equals("")) {
                Tline.setText("0");
            }

            A = Double.parseDouble(Tmm.getText());
            B = Double.parseDouble(Tngt.getText());
            C = Double.parseDouble(Tline.getText());
            Total = A + B + C;

            if (Valid.SetAngka2(Total).equals("NaN")) {
                Ttotintake.setText("0");
            } else {
                Ttotintake.setText(Valid.SetAngka2(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungOutput() {
        try {
            double A, B, C, D, E, F, Total;
            Total = 0;
            A = 0;
            B = 0;
            C = 0;
            D = 0;
            E = 0;
            F = 0;
            if (Turin.getText().equals("")) {
                Turin.setText("0");
            }
            
            if (TngtDarah.getText().equals("")) {
                TngtDarah.setText("0");
            }
            
            if (Tdrain.getText().equals("")) {
                Tdrain.setText("0");
            }
            
            if (Tmuntah.getText().equals("")) {
                Tmuntah.setText("0");
            }
            
            if (Tbab.getText().equals("")) {
                Tbab.setText("0");
            }
            
            if (Tiwl.getText().equals("")) {
                Tiwl.setText("0");
            }

            A = Double.parseDouble(Turin.getText());
            B = Double.parseDouble(TngtDarah.getText());
            C = Double.parseDouble(Tdrain.getText());            
            D = Double.parseDouble(Tmuntah.getText());
            E = Double.parseDouble(Tbab.getText());
            F = Double.parseDouble(Tiwl.getText());
            Total = A + B + C + D + E + F;

            if (Valid.SetAngka(Total).equals("NaN")) {
                Ttotouput.setText("0");
            } else {
                Ttotouput.setText(Valid.SetAngka(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungBalance() {
        try {
            double A, B, Total;
            Total = 0;
            A = 0;
            B = 0;
            if (Ttotintake.getText().equals("")) {
                Ttotintake.setText("0");
            }
            
            if (Ttotouput.getText().equals("")) {
                Ttotouput.setText("0");
            }

            A = Double.parseDouble(Ttotintake.getText());
            B = Double.parseDouble(Ttotouput.getText());
            Total = A - B;

            if (Valid.SetAngka(Total).equals("NaN")) {
                Tbalance.setText("0");
            } else {
                Tbalance.setText(Valid.SetAngka(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void autoNomorPerJam() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_pantau,4),signed)),0) from pemantauan_harian_perjam where "
                + "tgl_pantau like '%" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(0, 7) + "%' ", "/PHJ/" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(0, 4), 4, TkdPantau);
    }
    
    private void jamDiurutkan() {
        urutanJam = "";
        if (cmbJam.getSelectedIndex() == 0) {
            urutanJam = "1";
        } else if (cmbJam.getSelectedIndex() == 1) {
            urutanJam = "2";
        } else if (cmbJam.getSelectedIndex() == 2) {
            urutanJam = "3";
        } else if (cmbJam.getSelectedIndex() == 3) {
            urutanJam = "4";
        } else if (cmbJam.getSelectedIndex() == 4) {
            urutanJam = "5";
        } else if (cmbJam.getSelectedIndex() == 5) {
            urutanJam = "6";
        } else if (cmbJam.getSelectedIndex() == 6) {
            urutanJam = "7";
        } else if (cmbJam.getSelectedIndex() == 7) {
            urutanJam = "8";
        } else if (cmbJam.getSelectedIndex() == 8) {
            urutanJam = "9";
        } else if (cmbJam.getSelectedIndex() == 9) {
            urutanJam = "10";
        } else if (cmbJam.getSelectedIndex() == 10) {
            urutanJam = "11";
        } else if (cmbJam.getSelectedIndex() == 11) {
            urutanJam = "12";
        } else if (cmbJam.getSelectedIndex() == 12) {
            urutanJam = "13";
        } else if (cmbJam.getSelectedIndex() == 13) {
            urutanJam = "14";
        } else if (cmbJam.getSelectedIndex() == 14) {
            urutanJam = "15";
        } else if (cmbJam.getSelectedIndex() == 15) {
            urutanJam = "16";
        } else if (cmbJam.getSelectedIndex() == 16) {
            urutanJam = "17";
        } else if (cmbJam.getSelectedIndex() == 17) {
            urutanJam = "18";
        } else if (cmbJam.getSelectedIndex() == 18) {
            urutanJam = "19";
        } else if (cmbJam.getSelectedIndex() == 19) {
            urutanJam = "20";
        } else if (cmbJam.getSelectedIndex() == 20) {
            urutanJam = "21";
        } else if (cmbJam.getSelectedIndex() == 21) {
            urutanJam = "22";
        } else if (cmbJam.getSelectedIndex() == 22) {
            urutanJam = "23";
        } else if (cmbJam.getSelectedIndex() == 23) {
            urutanJam = "24";
        } 
    }
}
