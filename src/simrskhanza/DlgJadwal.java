/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJadwal.java
 *
 * Created on May 22, 2010, 10:25:16 PM
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgJadwal extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;

    /** Creates new form DlgJadwal
     * @param parent
     * @param modal */
    public DlgJadwal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        Object[] row = {"#", "Kode Dokter", "Nama Dokter", "Hari Kerja", "Jam Mulai", "Jam Selesai", "Poliklinik", "kdpoli"};
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbJadwal.setModel(tabMode);
        tbJadwal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJadwal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbJadwal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
//                column.setPreferredWidth(250);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbJadwal.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        kddokter.setDocument(new batasInput((byte)20).getKata(kddokter));
        KdPoli.setDocument(new batasInput((byte)5).getKata(KdPoli));
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    nmdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                } 
                kddokter.requestFocus();
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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                    TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                } 
                KdPoli.requestFocus();
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
        
    }
    DlgCariDokter dokter=new DlgCariDokter(null,false);
    DlgCariPoli poli=new DlgCariPoli(null,false);

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
        tbJadwal = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelBiasa1 = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        nmdokter = new widget.TextBox();
        jLabel4 = new widget.Label();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        TPoli = new widget.TextBox();
        cmbHari = new widget.ComboBox();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel11 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        btnDokter = new widget.Button();
        kddokter = new widget.TextBox();
        KdPoli = new widget.TextBox();
        BtnPoli = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Jadwal Praktek ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJadwal.setAutoCreateRowSorter(true);
        tbJadwal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJadwal.setName("tbJadwal"); // NOI18N
        tbJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJadwalMouseClicked(evt);
            }
        });
        tbJadwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJadwalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbJadwal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(null);

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
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
        BtnSimpan.setBounds(6, 10, 100, 30);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
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
        BtnBatal.setBounds(108, 10, 100, 30);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
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
        BtnHapus.setBounds(210, 10, 100, 30);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
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
        BtnEdit.setBounds(312, 10, 100, 30);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
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
        BtnPrint.setBounds(414, 10, 100, 30);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
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
        BtnKeluar.setBounds(518, 10, 100, 30);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(340, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('4');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+4");
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

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setPreferredSize(new java.awt.Dimension(1023, 107));
        panelBiasa1.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Dokter :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelBiasa1.add(jLabel3);
        jLabel3.setBounds(0, 12, 80, 23);

        nmdokter.setEditable(false);
        nmdokter.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter.setHighlighter(null);
        nmdokter.setName("nmdokter"); // NOI18N
        nmdokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmdokterKeyPressed(evt);
            }
        });
        panelBiasa1.add(nmdokter);
        nmdokter.setBounds(186, 12, 389, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Hari Kerja :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelBiasa1.add(jLabel4);
        jLabel4.setBounds(0, 42, 80, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jam :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelBiasa1.add(jLabel9);
        jLabel9.setBounds(196, 42, 50, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Poliklinik :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelBiasa1.add(jLabel10);
        jLabel10.setBounds(0, 72, 80, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setHighlighter(null);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPoliKeyPressed(evt);
            }
        });
        panelBiasa1.add(TPoli);
        TPoli.setBounds(186, 72, 389, 23);

        cmbHari.setForeground(new java.awt.Color(0, 0, 0));
        cmbHari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SENIN", "SELASA", "RABU", "KAMIS", "JUMAT", "SABTU", "AKHAD" }));
        cmbHari.setName("cmbHari"); // NOI18N
        cmbHari.setOpaque(false);
        cmbHari.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbHariItemStateChanged(evt);
            }
        });
        cmbHari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbHariMouseClicked(evt);
            }
        });
        cmbHari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbHariKeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbHari);
        cmbHari.setBounds(84, 42, 100, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.setOpaque(false);
        cmbJam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam1KeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbJam1);
        cmbJam1.setBounds(250, 42, 50, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.setOpaque(false);
        cmbMnt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt1KeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbMnt1);
        cmbMnt1.setBounds(304, 42, 50, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.setOpaque(false);
        cmbDtk1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk1KeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbDtk1);
        cmbDtk1.setBounds(358, 42, 50, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("s.d.");
        jLabel11.setName("jLabel11"); // NOI18N
        panelBiasa1.add(jLabel11);
        jLabel11.setBounds(410, 42, 35, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.setOpaque(false);
        cmbJam2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam2KeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbJam2);
        cmbJam2.setBounds(447, 42, 50, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.setOpaque(false);
        cmbMnt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt2KeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbMnt2);
        cmbMnt2.setBounds(501, 42, 50, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.setOpaque(false);
        cmbDtk2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk2KeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbDtk2);
        cmbDtk2.setBounds(555, 42, 50, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('1');
        btnDokter.setToolTipText("ALt+1");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        panelBiasa1.add(btnDokter);
        btnDokter.setBounds(577, 12, 28, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setHighlighter(null);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        panelBiasa1.add(kddokter);
        kddokter.setBounds(84, 12, 100, 23);

        KdPoli.setEditable(false);
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        KdPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPoliKeyPressed(evt);
            }
        });
        panelBiasa1.add(KdPoli);
        KdPoli.setBounds(84, 72, 100, 23);

        BtnPoli.setForeground(new java.awt.Color(0, 0, 0));
        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('2');
        BtnPoli.setToolTipText("ALt+2");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        panelBiasa1.add(BtnPoli);
        BtnPoli.setBounds(577, 72, 28, 23);

        internalFrame1.add(panelBiasa1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nmdokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmdokterKeyPressed
        //Valid.pindah(evt,TKd,TSpek);
}//GEN-LAST:event_nmdokterKeyPressed

    private void TPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPoliKeyPressed
        //Valid.pindah(evt,TJns,BtnSimpan);
}//GEN-LAST:event_TPoliKeyPressed

    private void cmbHariItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbHariItemStateChanged
        // TODO add your handling code here:
}//GEN-LAST:event_cmbHariItemStateChanged

    private void cmbHariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbHariKeyPressed
        Valid.pindah(evt,kddokter,cmbJam1);
}//GEN-LAST:event_cmbHariKeyPressed

    private void cmbJam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam1KeyPressed
        Valid.pindah(evt,cmbHari,cmbMnt1);
}//GEN-LAST:event_cmbJam1KeyPressed

    private void cmbMnt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt1KeyPressed
        Valid.pindah(evt,cmbJam1,cmbDtk1);
}//GEN-LAST:event_cmbMnt1KeyPressed

    private void cmbDtk1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk1KeyPressed
        Valid.pindah(evt,cmbMnt1,cmbJam2);
}//GEN-LAST:event_cmbDtk1KeyPressed

    private void cmbJam2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam2KeyPressed
        Valid.pindah(evt,cmbDtk1,cmbMnt2);
}//GEN-LAST:event_cmbJam2KeyPressed

    private void cmbMnt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt2KeyPressed
        Valid.pindah(evt,cmbJam2,cmbDtk2);
}//GEN-LAST:event_cmbMnt2KeyPressed

    private void cmbDtk2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk2KeyPressed
        Valid.pindah(evt,cmbMnt2,KdPoli);
}//GEN-LAST:event_cmbDtk2KeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (nmdokter.getText().trim().equals("")) {
            Valid.textKosong(kddokter, "Dokter");
        } else if (TPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poliklinik");
        } else {
            Sequel.menyimpan("jadwal", "'" + kddokter.getText() + "','" + cmbHari.getSelectedItem() + "','"
                    + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "','"
                    + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "','"
                    + KdPoli.getText() + "'", "Kode Dokter");
            
            tampil();
            emptTeks();
            kddokter.requestFocus();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,KdPoli,BtnBatal);
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
        for (int i = 0; i < tbJadwal.getRowCount(); i++) {
            if (tbJadwal.getValueAt(i, 0).toString().equals("true")) {
                Sequel.queryu("delete from jadwal where kd_dokter='" + tbJadwal.getValueAt(i, 1).toString() + "' "
                        + "and hari_kerja='" + tbJadwal.getValueAt(i, 3).toString() + "' "
                        + "and kd_poli='" + tbJadwal.getValueAt(i, 7).toString() + "'");
            }
        }
        tampil();
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
        if (nmdokter.getText().trim().equals("")) {
            Valid.textKosong(kddokter, "Dokter");
        } else if (TPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poliklinik");
        } else {
            if (tbJadwal.getSelectedRow() != -1) {
                Sequel.queryu("update jadwal set jam_mulai='" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "',"
                        + "jam_selesai='" + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "',"
                        + "kd_poli='" + KdPoli.getText() + "',kd_dokter='" + kddokter.getText() + "',hari_kerja='" + cmbHari.getSelectedItem() + "' where "
                        + "kd_dokter='" + tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 1).toString() + "' "
                        + "and hari_kerja='" + tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 3).toString() + "' "
                        + "and jam_mulai='" + tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 4).toString() + "' "
                        + "and jam_selesai='" + tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 5).toString() + "'");
                
                tampil();
                emptTeks();
            }
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
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (!TCari.getText().trim().equals("")) {
            BtnCariActionPerformed(evt);
        }
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            
            if (TCari.getText().trim().equals("")) {
                Valid.MyReport("rptJadwal.jasper", "report", "::[ Jadwal Praktek Dokter ]::",
                        "select j.kd_dokter, d.nm_dokter, j.hari_kerja, j.jam_mulai, j.jam_selesai, pl.nm_poli from jadwal j "
                        + "inner join poliklinik pl on pl.kd_poli=j.kd_poli inner join dokter d on d.kd_dokter=j.kd_dokter order by j.kd_dokter", param);

            } else if (!TCari.getText().trim().equals("")) {
                Valid.MyReport("rptJadwal.jasper", "report", "::[ Jadwal Praktek Dokter ]::",
                        "select j.kd_dokter, d.nm_dokter, j.hari_kerja, j.jam_mulai, j.jam_selesai, pl.nm_poli from jadwal j "
                        + "inner join poliklinik pl on pl.kd_poli=j.kd_poli inner join dokter d on d.kd_dokter=j.kd_dokter where "
                        + "j.kd_dokter like '%" + TCari.getText() + "%' or "
                        + "d.nm_dokter like '%" + TCari.getText() + "%' or "
                        + "j.hari_kerja like '%" + TCari.getText() + "%' or "
                        + "j.jam_mulai like '%" + TCari.getText() + "%' or "
                        + "j.jam_selesai like '%" + TCari.getText() + "%' or "
                        + "pl.nm_poli like '%" + TCari.getText() + "%' order by j.kd_dokter", param);
            }
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
        tampil();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari,kddokter);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJadwalMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbJadwalMouseClicked

    private void tbJadwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJadwalKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbJadwalKeyPressed

private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
    dokter.isCek();
    dokter.setSize(1060, 384);
    dokter.setLocationRelativeTo(internalFrame1);
    dokter.setVisible(true);
}//GEN-LAST:event_btnDokterActionPerformed

private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter.getText() + "'", nmdokter);
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnDokterActionPerformed(null);
    } else {
        Valid.pindah(evt, TCari, cmbHari);
    }
}//GEN-LAST:event_kddokterKeyPressed

private void KdPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPoliKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, KdPoli.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnPoliActionPerformed(null);
    } else {
        Valid.pindah(evt, cmbDtk2, BtnSimpan);
    }
}//GEN-LAST:event_KdPoliKeyPressed

private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
    poli.isCek();
    poli.setSize(1055, 350);
    poli.setLocationRelativeTo(internalFrame1);
    poli.setVisible(true);
}//GEN-LAST:event_BtnPoliActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void cmbHariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbHariMouseClicked
        cmbHari.setEditable(false);
    }//GEN-LAST:event_cmbHariMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgJadwal dialog = new DlgJadwal(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox KdPoli;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TPoli;
    private widget.Button btnDokter;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbHari;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kddokter;
    private widget.TextBox nmdokter;
    private widget.PanelBiasa panelBiasa1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbJadwal;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select j.kd_dokter, d.nm_dokter, j.hari_kerja, j.jam_mulai, j.jam_selesai, pl.nm_poli, j.kd_poli from jadwal j "
                    + "inner join poliklinik pl on pl.kd_poli=j.kd_poli inner join dokter d on d.kd_dokter=j.kd_dokter where "
                    + "j.kd_dokter like ? or "
                    + "d.nm_dokter like ? or "
                    + "j.hari_kerja like ? or "
                    + "j.jam_mulai like ? or "
                    + "j.jam_selesai like ? or "
                    + "pl.nm_poli like ? order by j.kd_dokter");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{false,
                        rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"),
                        rs.getString("hari_kerja"),
                        rs.getString("jam_mulai"),
                        rs.getString("jam_selesai"),
                        rs.getString("nm_poli"),
                        rs.getString("kd_poli")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
        kddokter.setText("");
        nmdokter.setText("");
        KdPoli.setText("");
        TPoli.setText("");

        cmbHari.setSelectedItem("SENIN");
        cmbJam1.setSelectedItem("00");
        cmbJam2.setSelectedItem("00");
        cmbMnt1.setSelectedItem("00");
        cmbMnt2.setSelectedItem("00");
        cmbDtk1.setSelectedItem("00");
        cmbDtk2.setSelectedItem("00");

        kddokter.requestFocus();
    }

    private void getData() {
        int row=tbJadwal.getSelectedRow();
        if(row!= -1){
            kddokter.setText(tabMode.getValueAt(row,1).toString());
            nmdokter.setText(tabMode.getValueAt(row,2).toString());
            cmbHari.setSelectedItem(tabMode.getValueAt(row,3).toString());
            cmbJam1.setSelectedItem(tabMode.getValueAt(row,4).toString().substring(0,2));
            cmbMnt1.setSelectedItem(tabMode.getValueAt(row,4).toString().substring(3,5));
            cmbDtk1.setSelectedItem(tabMode.getValueAt(row,4).toString().substring(6,8));
            cmbJam2.setSelectedItem(tabMode.getValueAt(row,5).toString().substring(0,2));
            cmbMnt2.setSelectedItem(tabMode.getValueAt(row,5).toString().substring(3,5));
            cmbDtk2.setSelectedItem(tabMode.getValueAt(row,5).toString().substring(6,8));            
            TPoli.setText(tabMode.getValueAt(row,6).toString());
            KdPoli.setText(tabMode.getValueAt(row,7).toString());
        }
    }  
    
}
