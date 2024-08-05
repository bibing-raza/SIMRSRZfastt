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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgPemeriksaanKlinisLabHIV extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String noUrut = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgPemeriksaanKlinisLabHIV(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "urutan", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Pemeriksaan", "Tanggal",
            "Stad WHO", "BB", "Status Fungsional", "Jumlah Cd4", "Lain-lain", "tglPeriksa", "tgl_lahir"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex==0) {
                    a=false;
                }
                return a;
             }
             Class[] types = new Class[]{
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbHIV.setModel(tabMode);
        tbHIV.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbHIV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tbHIV.getColumnModel().getColumn(i);
            if (i == 0) {
//                column.setPreferredWidth(60);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbHIV.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        bb.setDocument(new batasInput((byte) 4).getOnlyAngka(bb));
        
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
        
        ChkInput.setSelected(false);
        isForm();
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
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel35 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel36 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        jLabel9 = new widget.Label();
        stadWHO = new widget.TextBox();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        cmbstsFung = new widget.ComboBox();
        bb = new widget.TextBox();
        jLabel13 = new widget.Label();
        JLHcd4 = new widget.TextBox();
        cmbPemeriksaan = new widget.ComboBox();
        jLabel5 = new widget.Label();
        tglPemeriksaan = new widget.Tanggal();
        jLabel8 = new widget.Label();
        lainLain = new widget.TextBox();
        jLabel10 = new widget.Label();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel15 = new widget.Label();
        TtglLahir = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbHIV = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ 5. Pemeriksaan Klinis dan Laboratorium (HIV) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tgl. Pemeriksaan : ");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass10.add(jLabel35);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-02-2022" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        panelGlass10.add(tgl1);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("s.d");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(20, 23));
        panelGlass10.add(jLabel36);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-02-2022" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        panelGlass10.add(tgl2);

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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 185));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pemeriksaan : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 40, 115, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Stad WHO : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 70, 115, 23);

        stadWHO.setForeground(new java.awt.Color(0, 0, 0));
        stadWHO.setName("stadWHO"); // NOI18N
        stadWHO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stadWHOKeyPressed(evt);
            }
        });
        FormInput.add(stadWHO);
        stadWHO.setBounds(117, 70, 220, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("BB : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 100, 115, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Status Fungsional : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 130, 115, 23);

        cmbstsFung.setForeground(new java.awt.Color(0, 0, 0));
        cmbstsFung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1 = Kerja", "2 = Ambulatori", "3 = Baring" }));
        cmbstsFung.setName("cmbstsFung"); // NOI18N
        FormInput.add(cmbstsFung);
        cmbstsFung.setBounds(117, 130, 105, 23);

        bb.setForeground(new java.awt.Color(0, 0, 0));
        bb.setName("bb"); // NOI18N
        bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbKeyPressed(evt);
            }
        });
        FormInput.add(bb);
        bb.setBounds(117, 100, 80, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jumlah Cd4 : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(340, 100, 70, 23);

        JLHcd4.setForeground(new java.awt.Color(0, 0, 0));
        JLHcd4.setName("JLHcd4"); // NOI18N
        JLHcd4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JLHcd4KeyPressed(evt);
            }
        });
        FormInput.add(JLHcd4);
        JLHcd4.setBounds(413, 100, 220, 23);

        cmbPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPemeriksaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kunjungan pertama", "Memenuhi syarat medis untuk ART", "Saat mulai ART", "Setelah 6 bulan ART", "Setelah 12 bulan ART", "Setelah 24 bulan ART" }));
        cmbPemeriksaan.setName("cmbPemeriksaan"); // NOI18N
        cmbPemeriksaan.setOpaque(false);
        cmbPemeriksaan.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbPemeriksaan);
        cmbPemeriksaan.setBounds(117, 40, 200, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tanggal : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(340, 70, 70, 23);

        tglPemeriksaan.setEditable(false);
        tglPemeriksaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-02-2022" }));
        tglPemeriksaan.setDisplayFormat("dd-MM-yyyy");
        tglPemeriksaan.setName("tglPemeriksaan"); // NOI18N
        tglPemeriksaan.setOpaque(false);
        FormInput.add(tglPemeriksaan);
        tglPemeriksaan.setBounds(413, 70, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Lain-lain : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(340, 130, 70, 23);

        lainLain.setForeground(new java.awt.Color(0, 0, 0));
        lainLain.setName("lainLain"); // NOI18N
        lainLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lainLainKeyPressed(evt);
            }
        });
        FormInput.add(lainLain);
        lainLain.setBounds(413, 130, 220, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pasien : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 115, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(117, 10, 90, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(210, 10, 496, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jns. Kelamin : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(320, 40, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(413, 40, 80, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Lahir : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(495, 40, 70, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(566, 40, 140, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbHIV.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbHIV.setName("tbHIV"); // NOI18N
        tbHIV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHIVMouseClicked(evt);
            }
        });
        tbHIV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbHIVKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbHIV);

        internalFrame1.add(Scroll2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        try {
            if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRM, "Pasien");
            } else if (cmbPemeriksaan.getSelectedIndex() == 0) {
                Valid.textKosong(cmbPemeriksaan, "Item Pemeriksaan");
                cmbPemeriksaan.requestFocus();
            } else if (stadWHO.getText().trim().equals("")) {
                Valid.textKosong(stadWHO, "Stad WHO");
                stadWHO.requestFocus();
            } else if (bb.getText().trim().equals("")) {
                Valid.textKosong(bb, "BB");
                bb.requestFocus();
            } else if (cmbstsFung.getSelectedIndex() == 0) {
                Valid.textKosong(cmbstsFung, "Status Fungsional");
                cmbstsFung.requestFocus();
            } else if (JLHcd4.getText().trim().equals("")) {
                Valid.textKosong(JLHcd4, "Jumlah Cd4");
                JLHcd4.requestFocus();
            } else {
                noUrut = "";
                if (cmbPemeriksaan.getSelectedIndex() == 1) {
                    noUrut = "1";
                } else if (cmbPemeriksaan.getSelectedIndex() == 2) {
                    noUrut = "2";
                } else if (cmbPemeriksaan.getSelectedIndex() == 3) {
                    noUrut = "3";
                } else if (cmbPemeriksaan.getSelectedIndex() == 4) {
                    noUrut = "4";
                } else if (cmbPemeriksaan.getSelectedIndex() == 5) {
                    noUrut = "5";
                } else if (cmbPemeriksaan.getSelectedIndex() == 6) {
                    noUrut = "6";
                }

                if (Sequel.cariInteger("select count(-1) from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' "
                        + "and urutan='" + noUrut + "' and tgl='" + Valid.SetTgl(tglPemeriksaan.getSelectedItem() + "") + "'") > 0) {
                    JOptionPane.showMessageDialog(null, "Jenis pemeriksaan " + cmbPemeriksaan.getSelectedItem() + " sdh. tersimpan utk. tgl. yg. sama...!!");
                } else {
                    Sequel.menyimpan("hiv_pemeriksaan_klinis_lab",
                        "'" + TNoRM.getText() + "','" + noUrut + "','" + cmbPemeriksaan.getSelectedItem().toString() + "',"
                        + "'" + Valid.SetTgl(tglPemeriksaan.getSelectedItem() + "") + "','" + stadWHO.getText() + "',"
                        + "'" + bb.getText() + "','" + cmbstsFung.getSelectedItem().toString() + "',"
                        + "'" + JLHcd4.getText() + "','" + lainLain.getText() + "'", "Data Pemeriksaan Klinis & Laboratorium");
                    tampil();
                    emptTeks();
                }                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal proses menyimpan data,..!!");
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnGanti);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        try {
            if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRM, "Pasien");
            } else if (noUrut.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan diperbaiki...!!!!");
                tbHIV.requestFocus();
            } else if (cmbPemeriksaan.getSelectedIndex() == 0) {
                Valid.textKosong(cmbPemeriksaan, "Item Pemeriksaan");
                cmbPemeriksaan.requestFocus();
            } else if (stadWHO.getText().trim().equals("")) {
                Valid.textKosong(stadWHO, "Stad WHO");
                stadWHO.requestFocus();
            } else if (bb.getText().trim().equals("")) {
                Valid.textKosong(bb, "BB");
                bb.requestFocus();
            } else if (cmbstsFung.getSelectedIndex() == 0) {
                Valid.textKosong(cmbstsFung, "Status Fungsional");
                cmbstsFung.requestFocus();
            } else if (JLHcd4.getText().trim().equals("")) {
                Valid.textKosong(JLHcd4, "Jumlah Cd4");
                JLHcd4.requestFocus();
            } else {
                Sequel.mengedit("hiv_pemeriksaan_klinis_lab", "no_rkm_medis='" + TNoRM.getText() + "' and "
                        + "urutan='" + noUrut + "' and tgl='" + tbHIV.getValueAt(tbHIV.getSelectedRow(), 12).toString() + "'",
                        "tgl='" + Valid.SetTgl(tglPemeriksaan.getSelectedItem() + "") + "', "
                        + "stad_who='" + stadWHO.getText() + "',"
                        + "bb='" + bb.getText() + "', "
                        + "stts_fungsional='" + cmbstsFung.getSelectedItem().toString() + "',"
                        + "jumlah_cd4='" + JLHcd4.getText() + "', "
                        + "lain_lain='" + lainLain.getText() + "'");
                tampil();
                emptTeks();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal proses menyimpan data,..!!");
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
        }
}//GEN-LAST:event_BtnAllKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_formWindowOpened

    private void stadWHOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stadWHOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, stadWHO, bb);
        }
    }//GEN-LAST:event_stadWHOKeyPressed

    private void tbHIVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHIVMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbHIVMouseClicked

    private void tbHIVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbHIVKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbHIVKeyPressed

    private void bbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, bb, cmbstsFung);
        }
    }//GEN-LAST:event_bbKeyPressed

    private void JLHcd4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JLHcd4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, JLHcd4, lainLain);
        }
    }//GEN-LAST:event_JLHcd4KeyPressed

    private void lainLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lainLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, lainLain, BtnSimpan);
        }
    }//GEN-LAST:event_lainLainKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (TNoRM.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (noUrut.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan dihapus...!!!!");
            tbHIV.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from hiv_pemeriksaan_klinis_lab where no_rkm_medis='" + TNoRM.getText() + "' "
                    + "and urutan='" + noUrut + "' and tgl='" + tbHIV.getValueAt(tbHIV.getSelectedRow(), 12).toString() + "'");
                tampil();
                emptTeks();
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPemeriksaanKlinisLabHIV dialog = new DlgPemeriksaanKlinisLabHIV(new javax.swing.JFrame(), true);
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
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JLHcd4;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.TextBox Tjk;
    private widget.TextBox TtglLahir;
    private widget.TextBox bb;
    private widget.ComboBox cmbPemeriksaan;
    private widget.ComboBox cmbstsFung;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox lainLain;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.TextBox stadWHO;
    private widget.Table tbHIV;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tglPemeriksaan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT h.urutan, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tglLHR, "
                    + "h.pemeriksaan, DATE_FORMAT(h.tgl,'%d-%m-%Y') tgl, h.stad_who, h.bb, h.stts_fungsional, h.jumlah_cd4, h.lain_lain, p.jk, "
                    + "h.tgl tglPemeriksaan, p.tgl_lahir FROM hiv_pemeriksaan_klinis_lab h inner join pasien p ON p.no_rkm_medis=h.no_rkm_medis WHERE "
                    + "h.tgl between ? and ? and p.no_rkm_medis like ? or "
                    + "h.tgl between ? and ? and p.nm_pasien like ? or "
                    + "h.tgl between ? and ? and h.pemeriksaan like ? or "
                    + "h.tgl between ? and ? and h.stad_who like ? or "
                    + "h.tgl between ? and ? and h.bb like ? or "
                    + "h.tgl between ? and ? and h.stts_fungsional like ? or "
                    + "h.tgl between ? and ? and h.jumlah_cd4 like ? or "
                    + "h.tgl between ? and ? and h.lain_lain like ? order by h.urutan");            
            try {
                ps.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("urutan"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk").replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan"),
                        rs.getString("tglLHR"),
                        rs.getString("pemeriksaan"),
                        rs.getString("tgl"),
                        rs.getString("stad_who"),
                        rs.getString("bb"),
                        rs.getString("stts_fungsional"),
                        rs.getString("jumlah_cd4"),
                        rs.getString("lain_lain"),                        
                        rs.getString("tglPemeriksaan"),
                        rs.getString("tgl_lahir")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.DlgPemeriksaanKlinisLabHIV.tampil() : " + e);
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
        noUrut = "";
        cmbPemeriksaan.setSelectedIndex(0);
        stadWHO.setText("");
        bb.setText("");
        tglPemeriksaan.setDate(new Date());        
        cmbstsFung.setSelectedIndex(0);
        JLHcd4.setText("");
        lainLain.setText("");
    }

    private void getData() {
        noUrut = "";
        if (tbHIV.getSelectedRow() != -1) {
            noUrut = tbHIV.getValueAt(tbHIV.getSelectedRow(), 0).toString();
            TNoRM.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 1).toString());
            TPasien.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 2).toString());
            Tjk.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 3).toString());
            TtglLahir.setText(Valid.SetTglINDONESIA(tbHIV.getValueAt(tbHIV.getSelectedRow(), 13).toString()));
            cmbPemeriksaan.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 5).toString());
            Valid.SetTgl(tglPemeriksaan, tbHIV.getValueAt(tbHIV.getSelectedRow(), 12).toString());
            stadWHO.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 7).toString());
            bb.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 8).toString());
            cmbstsFung.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 9).toString());
            JLHcd4.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 10).toString());
            lainLain.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 11).toString());
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 185));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getikhtisar_perawatan_hiv());
        BtnHapus.setEnabled(akses.getikhtisar_perawatan_hiv());
        BtnGanti.setEnabled(akses.getikhtisar_perawatan_hiv());
    }
    
    public JTable getTable(){
        return tbHIV;
    }
    
    public void setData(String norm, String nmPasien, String jk, String tgllahir) {
        TNoRM.setText(norm);
        TPasien.setText(nmPasien);
        Tjk.setText(jk);
        TtglLahir.setText(Valid.SetTglINDONESIA(tgllahir));
    }
}
