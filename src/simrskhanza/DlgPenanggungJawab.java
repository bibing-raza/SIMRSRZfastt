/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public final class DlgPenanggungJawab extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel=new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps,ps1;
    private ResultSet rs,rs1;
    private int i = 0;
    private String cekstatus = "";
    
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public DlgPenanggungJawab(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(628,674);

        Object[] row={"P","Kode Cara Bayar","Nama Penanggung/Asuransi","Status"};
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPenjab.setModel(tabMode);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbPenjab.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPenjab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPenjab.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(450);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            }
        }
        tbPenjab.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"P", "Kode Cara Bayar", "Nama Penanggung/Asuransi", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPenjab1.setModel(tabMode1);
        tbPenjab1.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPenjab1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPenjab1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(450);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            }
        }
        tbPenjab1.setDefaultRenderer(Object.class, new WarnaTable());
        
        Kd.setDocument(new batasInput((byte)3).getKata(Kd));
        Nm.setDocument(new batasInput((byte)30).getKata(Nm));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
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
        
        try {
            ps = koneksi.prepareStatement("select kd_pj, png_jawab, if(status='1','Aktif','Non Aktif') status "
                    + " from penjab where status='1' and  kd_pj like ? or "
                    + " status='1' and png_jawab like ? order by png_jawab ");
        } catch (Exception ex) {
            System.out.println(ex);
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

        Popup = new javax.swing.JPopupMenu();
        ppSimpan = new javax.swing.JMenuItem();
        ppGanti = new javax.swing.JMenuItem();
        ppHapus = new javax.swing.JMenuItem();
        ppCetak = new javax.swing.JMenuItem();
        ppOrder = new javax.swing.JMenu();
        ppOrderKode = new javax.swing.JMenuItem();
        ppOrderNama = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        label34 = new widget.Label();
        Kd = new widget.TextBox();
        label36 = new widget.Label();
        Nm = new widget.TextBox();
        label37 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jPanel1 = new javax.swing.JPanel();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        TabPenjab = new javax.swing.JTabbedPane();
        scrollPane1 = new widget.ScrollPane();
        tbPenjab = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbPenjab1 = new widget.Table();

        Popup.setName("Popup"); // NOI18N

        ppSimpan.setBackground(new java.awt.Color(242, 242, 242));
        ppSimpan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppSimpan.setForeground(new java.awt.Color(102, 51, 0));
        ppSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        ppSimpan.setText("Simpan");
        ppSimpan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSimpan.setIconTextGap(8);
        ppSimpan.setName("ppSimpan"); // NOI18N
        ppSimpan.setPreferredSize(new java.awt.Dimension(150, 25));
        ppSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        Popup.add(ppSimpan);

        ppGanti.setBackground(new java.awt.Color(242, 242, 242));
        ppGanti.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppGanti.setForeground(new java.awt.Color(102, 51, 0));
        ppGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        ppGanti.setText("Ganti");
        ppGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGanti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGanti.setIconTextGap(8);
        ppGanti.setName("ppGanti"); // NOI18N
        ppGanti.setPreferredSize(new java.awt.Dimension(150, 25));
        ppGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        Popup.add(ppGanti);

        ppHapus.setBackground(new java.awt.Color(242, 242, 242));
        ppHapus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppHapus.setForeground(new java.awt.Color(102, 51, 0));
        ppHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        ppHapus.setText("Hapus");
        ppHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapus.setIconTextGap(8);
        ppHapus.setName("ppHapus"); // NOI18N
        ppHapus.setPreferredSize(new java.awt.Dimension(150, 25));
        ppHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        Popup.add(ppHapus);

        ppCetak.setBackground(new java.awt.Color(242, 242, 242));
        ppCetak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppCetak.setForeground(new java.awt.Color(102, 51, 0));
        ppCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppCetak.setText("Cetak");
        ppCetak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCetak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCetak.setIconTextGap(8);
        ppCetak.setName("ppCetak"); // NOI18N
        ppCetak.setPreferredSize(new java.awt.Dimension(150, 25));
        ppCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppCetak);

        ppOrder.setBackground(new java.awt.Color(242, 242, 242));
        ppOrder.setForeground(new java.awt.Color(102, 51, 0));
        ppOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        ppOrder.setText("Urutkan Berdasar");
        ppOrder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppOrder.setIconTextGap(8);
        ppOrder.setName("ppOrder"); // NOI18N

        ppOrderKode.setBackground(new java.awt.Color(242, 242, 242));
        ppOrderKode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppOrderKode.setForeground(new java.awt.Color(102, 51, 0));
        ppOrderKode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        ppOrderKode.setText("Kode Penanggung/Askes/Asuransi");
        ppOrderKode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppOrderKode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppOrderKode.setIconTextGap(8);
        ppOrderKode.setName("ppOrderKode"); // NOI18N
        ppOrderKode.setPreferredSize(new java.awt.Dimension(300, 25));
        ppOrderKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppOrderKodeActionPerformed(evt);
            }
        });
        ppOrder.add(ppOrderKode);

        ppOrderNama.setBackground(new java.awt.Color(242, 242, 242));
        ppOrderNama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppOrderNama.setForeground(new java.awt.Color(102, 51, 0));
        ppOrderNama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        ppOrderNama.setText("Nama Penanggung/Askes/Asuransi");
        ppOrderNama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppOrderNama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppOrderNama.setIconTextGap(8);
        ppOrderNama.setName("ppOrderNama"); // NOI18N
        ppOrderNama.setPreferredSize(new java.awt.Dimension(300, 25));
        ppOrderNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppOrderNamaActionPerformed(evt);
            }
        });
        ppOrder.add(ppOrderNama);

        Popup.add(ppOrder);

        Kd2.setHighlighter(null);
        Kd2.setName("Kd2"); // NOI18N
        Kd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kd2KeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Penanggung Pasien/Asuransi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 47));
        panelisi4.setLayout(null);

        label34.setForeground(new java.awt.Color(0, 0, 0));
        label34.setText("Kode Cara Bayar :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label34);
        label34.setBounds(0, 12, 137, 23);

        Kd.setForeground(new java.awt.Color(0, 0, 0));
        Kd.setHighlighter(null);
        Kd.setName("Kd"); // NOI18N
        Kd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKeyPressed(evt);
            }
        });
        panelisi4.add(Kd);
        Kd.setBounds(140, 12, 65, 23);

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setText("Penanggung/Asuransi :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label36);
        label36.setBounds(215, 12, 120, 23);

        Nm.setForeground(new java.awt.Color(0, 0, 0));
        Nm.setHighlighter(null);
        Nm.setName("Nm"); // NOI18N
        Nm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmKeyPressed(evt);
            }
        });
        panelisi4.add(Nm);
        Nm.setBounds(338, 12, 280, 23);

        label37.setForeground(new java.awt.Color(0, 0, 0));
        label37.setText("Status :");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label37);
        label37.setBounds(625, 12, 50, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AKTIF", "NON AKTIF" }));
        cmbStatus.setLightWeightPopupEnabled(false);
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setOpaque(false);
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        cmbStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStatusKeyPressed(evt);
            }
        });
        panelisi4.add(cmbStatus);
        cmbStatus.setBounds(679, 12, 100, 23);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label9);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
        panelisi3.add(BtnCari);

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
        panelisi3.add(BtnAll);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(LCount);

        jPanel1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi1.add(BtnSimpan);

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
        panelisi1.add(BtnBatal);

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
        panelisi1.add(BtnHapus);

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
        panelisi1.add(BtnEdit);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnKeluar);

        jPanel1.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        TabPenjab.setBackground(new java.awt.Color(250, 255, 245));
        TabPenjab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabPenjab.setForeground(new java.awt.Color(0, 0, 0));
        TabPenjab.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabPenjab.setName("TabPenjab"); // NOI18N
        TabPenjab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPenjabMouseClicked(evt);
            }
        });

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPenjab.setAutoCreateRowSorter(true);
        tbPenjab.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPenjab.setName("tbPenjab"); // NOI18N
        tbPenjab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPenjabMouseClicked(evt);
            }
        });
        tbPenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPenjabKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbPenjab);

        TabPenjab.addTab("Cara Bayar AKTIF", scrollPane1);

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbPenjab1.setAutoCreateRowSorter(true);
        tbPenjab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPenjab1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPenjab1.setName("tbPenjab1"); // NOI18N
        tbPenjab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPenjab1MouseClicked(evt);
            }
        });
        tbPenjab1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPenjab1KeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(tbPenjab1);

        TabPenjab.addTab("Cara Bayar NON AKTIF", scrollPane2);

        internalFrame1.add(TabPenjab, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKeyPressed
        Valid.pindah(evt,TCari,Nm,TCari);
}//GEN-LAST:event_KdKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (Kd.getText().trim().equals("")) {
            Valid.textKosong(Kd, "Kode Penanggung/Asuransi");
        } else if (Nm.getText().trim().equals("")) {
            Valid.textKosong(Nm, "Nama Penanggung/Asuransi");
        } else {
            cekstatus = "";
            if (cmbStatus.getSelectedItem().equals("AKTIF")) {
                cekstatus = "1";
            } else if (cmbStatus.getSelectedItem().equals("NON AKTIF")) {
                cekstatus = "0";
            }
            Sequel.menyimpan("penjab", "'" + Kd.getText() + "','" + Nm.getText() + "','" + cekstatus + "'", "Kode Penanggung/Asuransi");
            BtnCariActionPerformed(evt);
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Nm,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabPenjab.getSelectedIndex() == 0) {
            for (i = 0; i < tbPenjab.getRowCount(); i++) {
                if (tbPenjab.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.meghapus("penjab", "kd_pj", tbPenjab.getValueAt(i, 1).toString());
                }
            }
        } else if (TabPenjab.getSelectedIndex() == 1) {
            for (i = 0; i < tbPenjab1.getRowCount(); i++) {
                if (tbPenjab1.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.meghapus("penjab", "kd_pj", tbPenjab1.getValueAt(i, 1).toString());
                }
            }
        }
        
        BtnCariActionPerformed(evt);
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (Kd.getText().trim().equals("")) {
            Valid.textKosong(Kd, "Kode Penanggung/Asuransi");
        } else if (Nm.getText().trim().equals("")) {
            Valid.textKosong(Nm, "Nama Penanggung/Asuransi");
        } else {
            Valid.editTable(tabMode, "penjab", "kd_pj", Kd2, "png_jawab='" + Nm.getText() + "',status='" + cekstatus + "',kd_pj='" + Kd.getText() + "'");
            if (tabMode.getRowCount() != 0) {
                BtnCariActionPerformed(evt);
            }
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        BtnCariActionPerformed(evt);
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptPenjab.jasper", "report", "::[ Daftar Nama Jenis Cara Bayar Pasien (Penanggung Jawab/Asuransi) ]::", 
                    "select kd_pj, png_jawab, if(status='1','Aktif','Non Aktif') status "
                    + " from penjab where kd_pj like '%" + TCari.getText().trim() + "%' or "
                    + " png_jawab like '%" + TCari.getText().trim() + "%' order by kd_pj");
            this.setCursor(Cursor.getDefaultCursor());
        }        
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbPenjab.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabPenjab.getSelectedIndex() == 0) {
            tampil();
        } else if (TabPenjab.getSelectedIndex() == 1) {
            tampil1();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        if (TabPenjab.getSelectedIndex() == 0) {
            tampil();
        } else if (TabPenjab.getSelectedIndex() == 1) {
            tampil1();
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPenjabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenjabMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPenjabMouseClicked

    private void tbPenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPenjabKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbPenjabKeyPressed

private void NmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmKeyPressed
   Valid.pindah(evt,Kd,BtnSimpan);
}//GEN-LAST:event_NmKeyPressed

    private void ppOrderKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppOrderKodeActionPerformed
        tampil();
    }//GEN-LAST:event_ppOrderKodeActionPerformed

    private void ppOrderNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppOrderNamaActionPerformed
        tampil();
    }//GEN-LAST:event_ppOrderNamaActionPerformed

    private void Kd2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kd2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kd2KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (TabPenjab.getSelectedIndex() == 0) {
            tampil();
        } else if (TabPenjab.getSelectedIndex() == 1) {
            tampil1();
        }
    }//GEN-LAST:event_formWindowOpened

    private void cmbStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStatusKeyPressed

    }//GEN-LAST:event_cmbStatusKeyPressed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        cekstatus = "";
        if (cmbStatus.getSelectedItem().equals("AKTIF")) {
            cekstatus = "1";
        } else if (cmbStatus.getSelectedItem().equals("NON AKTIF")) {
            cekstatus = "0";
        }
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void tbPenjab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenjab1MouseClicked
        if(tabMode1.getRowCount()!=0){
            try {
                getData1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPenjab1MouseClicked

    private void tbPenjab1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPenjab1KeyPressed
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData1();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPenjab1KeyPressed

    private void TabPenjabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPenjabMouseClicked
        if (TabPenjab.getSelectedIndex() == 0) {
            tampil();
        } else if (TabPenjab.getSelectedIndex() == 1) {
            tampil1();
        }
    }//GEN-LAST:event_TabPenjabMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPenanggungJawab dialog = new DlgPenanggungJawab(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox Kd;
    private widget.TextBox Kd2;
    private widget.Label LCount;
    private widget.TextBox Nm;
    private javax.swing.JPopupMenu Popup;
    public widget.TextBox TCari;
    private javax.swing.JTabbedPane TabPenjab;
    private widget.ComboBox cmbStatus;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel1;
    private widget.Label label10;
    private widget.Label label34;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppCetak;
    private javax.swing.JMenuItem ppGanti;
    private javax.swing.JMenuItem ppHapus;
    private javax.swing.JMenu ppOrder;
    private javax.swing.JMenuItem ppOrderKode;
    private javax.swing.JMenuItem ppOrderNama;
    private javax.swing.JMenuItem ppSimpan;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbPenjab;
    private widget.Table tbPenjab1;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps.setString(1, "%" + TCari.getText().trim() + "%");
            ps.setString(2, "%" + TCari.getText().trim() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode.addRow(new Object[]{false, rs.getString(1), rs.getString(2), rs.getString(3)});
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        Kd.setText("");
        Kd2.setText("");
        Nm.setText("");
        cmbStatus.setSelectedIndex(0);
        Kd.requestFocus();        
        Valid.autoNomer("penjab","A",2,Kd);
    }

    private void getData() {
        cekstatus = "";
        
        if (tbPenjab.getSelectedRow() != -1) {
            Kd.setText(tbPenjab.getValueAt(tbPenjab.getSelectedRow(), 1).toString());
            Kd2.setText(tbPenjab.getValueAt(tbPenjab.getSelectedRow(), 1).toString());
            Nm.setText(tbPenjab.getValueAt(tbPenjab.getSelectedRow(), 2).toString());
            cekstatus = Sequel.cariIsi("select status from penjab where kd_pj='" + Kd.getText() + "'");
            
            if (cekstatus.equals("1")) {
                cmbStatus.setSelectedItem("AKTIF");
            } else {
                cmbStatus.setSelectedItem("NON AKTIF");
            }
        }
    }
    
    private void getData1() {
        cekstatus = "";
        
        if (tbPenjab1.getSelectedRow() != -1) {
            Kd.setText(tbPenjab1.getValueAt(tbPenjab1.getSelectedRow(), 1).toString());
            Kd2.setText(tbPenjab1.getValueAt(tbPenjab1.getSelectedRow(), 1).toString());
            Nm.setText(tbPenjab1.getValueAt(tbPenjab1.getSelectedRow(), 2).toString());
            cekstatus = Sequel.cariIsi("select status from penjab where kd_pj='" + Kd.getText() + "'");
            
            if (cekstatus.equals("1")) {
                cmbStatus.setSelectedItem("AKTIF");
            } else {
                cmbStatus.setSelectedItem("NON AKTIF");
            }
        }
    }

    public JTable getTable(){
        return tbPenjab;
    }
    
    public void onCari(){        
        TCari.requestFocus();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getadmin());
        BtnHapus.setEnabled(akses.getadmin());
        BtnEdit.setEnabled(akses.getadmin());
        BtnPrint.setEnabled(akses.getadmin());
        
        if (akses.getkode().equals("Admin Utama")) {
            cmbStatus.setEnabled(true);
            TabPenjab.setEnabledAt(1, true);
        } else {
            cmbStatus.setEnabled(false);
            TabPenjab.setEnabledAt(1, false);
        }
    }
    
    private void tampil1() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select kd_pj, png_jawab, if(status='1','Aktif','Non Aktif') status "
                    + " from penjab where status='0' and  kd_pj like ? or "
                    + " status='0' and png_jawab like ? order by png_jawab");
            try {
                ps1.setString(1, "%" + TCari.getText().trim() + "%");
                ps1.setString(2, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{false,
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3)});
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
        LCount.setText("" + tabMode1.getRowCount());
    }
}
