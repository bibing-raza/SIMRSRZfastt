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
public class DlgTerapiAntiretroviralHIV extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String tglsimpanTerapi = "", nilaiAlasan = "", alasanya = "", jnsTerapi = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgTerapiAntiretroviralHIV(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Tgl. Terapi", "Jenis Terapi ART", 
            "Alasan", "alasanLainSubstitusi", "alasanRestart", "Nama Rejimen Baru",
            "tglSimpan", "tglTerapi", "enumAlasan", "tgl_lahir"
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
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
//                column.setPreferredWidth(250);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
//                column.setPreferredWidth(250);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jLabel13 = new widget.Label();
        TalasanSub = new widget.TextBox();
        jLabel5 = new widget.Label();
        tglTerapi = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TnmRejimenBaru = new widget.TextBox();
        jLabel10 = new widget.Label();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel15 = new widget.Label();
        TtglLahir = new widget.TextBox();
        Rsubstitusi = new widget.RadioButton();
        cmbAlasanSub = new widget.ComboBox();
        Rswitch = new widget.RadioButton();
        cmbAlasanSwi = new widget.ComboBox();
        Rstop = new widget.RadioButton();
        cmbAlasanSto = new widget.ComboBox();
        Rrestart = new widget.RadioButton();
        TalasanRes = new widget.TextBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ 6. Terapi Antiretroviral (ART) HIV ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
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
        jLabel35.setText("Tanggal : ");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel35);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-03-2022" }));
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
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-03-2022" }));
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
        jLabel4.setText("Jenis Terapi ART : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 40, 115, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Alasan Lain Substitusi : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(400, 100, 130, 23);

        TalasanSub.setForeground(new java.awt.Color(0, 0, 0));
        TalasanSub.setName("TalasanSub"); // NOI18N
        TalasanSub.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanSubKeyPressed(evt);
            }
        });
        FormInput.add(TalasanSub);
        TalasanSub.setBounds(532, 100, 255, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Terapi : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(573, 70, 70, 23);

        tglTerapi.setEditable(false);
        tglTerapi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-03-2022" }));
        tglTerapi.setDisplayFormat("dd-MM-yyyy");
        tglTerapi.setName("tglTerapi"); // NOI18N
        tglTerapi.setOpaque(false);
        FormInput.add(tglTerapi);
        tglTerapi.setBounds(646, 70, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nama Rejimen Baru : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(400, 130, 130, 23);

        TnmRejimenBaru.setForeground(new java.awt.Color(0, 0, 0));
        TnmRejimenBaru.setName("TnmRejimenBaru"); // NOI18N
        TnmRejimenBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmRejimenBaruKeyPressed(evt);
            }
        });
        FormInput.add(TnmRejimenBaru);
        TnmRejimenBaru.setBounds(532, 130, 255, 23);

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
        TPasien.setBounds(210, 10, 576, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jns. Kelamin : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(400, 40, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(492, 40, 80, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Lahir : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(573, 40, 70, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(646, 40, 140, 23);

        Rsubstitusi.setBackground(new java.awt.Color(240, 250, 230));
        Rsubstitusi.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(Rsubstitusi);
        Rsubstitusi.setText("Substitusi");
        Rsubstitusi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Rsubstitusi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Rsubstitusi.setName("Rsubstitusi"); // NOI18N
        Rsubstitusi.setPreferredSize(new java.awt.Dimension(90, 23));
        Rsubstitusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RsubstitusiActionPerformed(evt);
            }
        });
        FormInput.add(Rsubstitusi);
        Rsubstitusi.setBounds(117, 40, 75, 23);

        cmbAlasanSub.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlasanSub.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Toksisitas/efek samping", "2. Hamil", "3. Resiko Hamil", "4. TB baru", "5. Ada obat baru", "6. Stok obat habis", "7. Alasan lain (uraikan)" }));
        cmbAlasanSub.setName("cmbAlasanSub"); // NOI18N
        cmbAlasanSub.setOpaque(false);
        cmbAlasanSub.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbAlasanSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlasanSubActionPerformed(evt);
            }
        });
        FormInput.add(cmbAlasanSub);
        cmbAlasanSub.setBounds(200, 40, 200, 23);

        Rswitch.setBackground(new java.awt.Color(240, 250, 230));
        Rswitch.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(Rswitch);
        Rswitch.setText("Switch");
        Rswitch.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Rswitch.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Rswitch.setName("Rswitch"); // NOI18N
        Rswitch.setPreferredSize(new java.awt.Dimension(90, 23));
        Rswitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RswitchActionPerformed(evt);
            }
        });
        FormInput.add(Rswitch);
        Rswitch.setBounds(117, 70, 75, 23);

        cmbAlasanSwi.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlasanSwi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "8. Gagal pengobatan secara linis", "9. Gagal imunologis", "10. Gagal virologis" }));
        cmbAlasanSwi.setName("cmbAlasanSwi"); // NOI18N
        cmbAlasanSwi.setOpaque(false);
        cmbAlasanSwi.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbAlasanSwi);
        cmbAlasanSwi.setBounds(200, 70, 200, 23);

        Rstop.setBackground(new java.awt.Color(240, 250, 230));
        Rstop.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(Rstop);
        Rstop.setText("Stop");
        Rstop.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Rstop.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Rstop.setName("Rstop"); // NOI18N
        Rstop.setPreferredSize(new java.awt.Dimension(90, 23));
        Rstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RstopActionPerformed(evt);
            }
        });
        FormInput.add(Rstop);
        Rstop.setBounds(117, 100, 75, 23);

        cmbAlasanSto.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlasanSto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Toksisitas/efek samping", "2. Hamil", "3. Gagal pengobatan", "4. Adherene buruk", "5. Sakit/MRS", "6. Stok obat habis", "7. Kekurangan biaya", "8. Keputusan pasien lainnya", "9. Lain-lain" }));
        cmbAlasanSto.setName("cmbAlasanSto"); // NOI18N
        cmbAlasanSto.setOpaque(false);
        cmbAlasanSto.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbAlasanSto);
        cmbAlasanSto.setBounds(200, 100, 200, 23);

        Rrestart.setBackground(new java.awt.Color(240, 250, 230));
        Rrestart.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(Rrestart);
        Rrestart.setText("Restart");
        Rrestart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Rrestart.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Rrestart.setName("Rrestart"); // NOI18N
        Rrestart.setPreferredSize(new java.awt.Dimension(90, 23));
        Rrestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RrestartActionPerformed(evt);
            }
        });
        FormInput.add(Rrestart);
        Rrestart.setBounds(117, 130, 75, 23);

        TalasanRes.setForeground(new java.awt.Color(0, 0, 0));
        TalasanRes.setName("TalasanRes"); // NOI18N
        TalasanRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanResKeyPressed(evt);
            }
        });
        FormInput.add(TalasanRes);
        TalasanRes.setBounds(200, 130, 200, 23);

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
            } else if (Rsubstitusi.isSelected() == false && Rswitch.isSelected() == false
                    && Rstop.isSelected() == false && Rrestart.isSelected() == false) {
                Valid.textKosong(TNoRM, "Jenis Terapi ART");
                Rsubstitusi.requestFocus();
            } else {
                alasanya = "";
                nilaiAlasan = "";
                jnsTerapi = "";
                if (Rsubstitusi.isSelected() == true) {
                    jnsTerapi = Rsubstitusi.getText();
                    nilaiAlasan = cmbAlasanSub.getSelectedItem().toString() + " " + TalasanSub.getText();
                    alasanya = cmbAlasanSub.getSelectedItem().toString();
                } else if (Rswitch.isSelected() == true) {
                    jnsTerapi = Rswitch.getText();
                    nilaiAlasan = cmbAlasanSwi.getSelectedItem().toString();
                    alasanya = cmbAlasanSwi.getSelectedItem().toString();
                } else if (Rstop.isSelected() == true) {
                    jnsTerapi = Rstop.getText();
                    nilaiAlasan = cmbAlasanSto.getSelectedItem().toString();
                    alasanya = cmbAlasanSto.getSelectedItem().toString();
                } else if (Rrestart.isSelected() == true) {
                    jnsTerapi = Rrestart.getText();
                    nilaiAlasan = TalasanRes.getText();
                }
                
                Sequel.menyimpan("hiv_terapi_antiretroviral",
                    "'" + TNoRM.getText() + "','" + Valid.SetTgl(tglTerapi.getSelectedItem() + "") + "',"
                    + "'" + jnsTerapi + "','" + alasanya + "','" + TalasanSub.getText() + "','" + TalasanRes.getText() + "',"
                    + "'" + TnmRejimenBaru.getText() + "',now(),'" + nilaiAlasan + "'", "Data Terapi Antiretroviral (ART)");
                
                tampil();
                emptTeks();
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
            } else if (tglsimpanTerapi.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan diperbaiki...!!!!");
                tbHIV.requestFocus();
            } else if (Rsubstitusi.isSelected() == false && Rswitch.isSelected() == false
                    && Rstop.isSelected() == false && Rrestart.isSelected() == false) {
                Valid.textKosong(TNoRM, "Jenis Terapi ART");
                Rsubstitusi.requestFocus();
            } else {
                alasanya = "";
                nilaiAlasan = "";
                jnsTerapi = "";
                if (Rsubstitusi.isSelected() == true) {
                    jnsTerapi = Rsubstitusi.getText();
                    nilaiAlasan = cmbAlasanSub.getSelectedItem().toString() + " " + TalasanSub.getText();
                    alasanya = cmbAlasanSub.getSelectedItem().toString();
                } else if (Rswitch.isSelected() == true) {
                    jnsTerapi = Rswitch.getText();
                    nilaiAlasan = cmbAlasanSwi.getSelectedItem().toString();
                    alasanya = cmbAlasanSwi.getSelectedItem().toString();
                } else if (Rstop.isSelected() == true) {
                    jnsTerapi = Rstop.getText();
                    nilaiAlasan = cmbAlasanSto.getSelectedItem().toString();
                    alasanya = cmbAlasanSto.getSelectedItem().toString();
                } else if (Rrestart.isSelected() == true) {
                    jnsTerapi = Rrestart.getText();
                    nilaiAlasan = TalasanRes.getText();
                }
                
                Sequel.mengedit("hiv_terapi_antiretroviral", "tgl_simpan='" + tglsimpanTerapi + "'",
                    "tanggal='" + Valid.SetTgl(tglTerapi.getSelectedItem() + "") + "',"
                    + "jns_terapi_art='" + jnsTerapi + "', "
                    + "alasan='" + alasanya + "',"
                    + "alasan_substitusi_lainnya='" + TalasanSub.getText() + "', "
                    + "alasan_restart='" + TalasanRes.getText() + "',"
                    + "nm_rejimen_baru='" + TnmRejimenBaru.getText() + "',"
                    + "alasan_fix='" + nilaiAlasan + "'");
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

    private void TalasanSubKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanSubKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, cmbAlasanSub, TnmRejimenBaru);
        }
    }//GEN-LAST:event_TalasanSubKeyPressed

    private void TnmRejimenBaruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmRejimenBaruKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TnmRejimenBaru, BtnSimpan);
        }
    }//GEN-LAST:event_TnmRejimenBaruKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (TNoRM.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (tglsimpanTerapi.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data Terapi Antiretroviral (ART) pada tabel yg. akan dihapus...!!!!");
            tbHIV.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from hiv_terapi_antiretroviral where tgl_simpan='" + tglsimpanTerapi + "'");
                tampil();
                emptTeks();
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void RsubstitusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RsubstitusiActionPerformed
        if (Rsubstitusi.isSelected() == true) {
            cmbAlasanSub.setSelectedIndex(0);
            cmbAlasanSwi.setSelectedIndex(0);
            cmbAlasanSto.setSelectedIndex(0);
            cmbAlasanSub.setEnabled(true);
            cmbAlasanSwi.setEnabled(false);
            cmbAlasanSto.setEnabled(false);
            TalasanRes.setText("");
            TalasanRes.setEnabled(false);
        }
    }//GEN-LAST:event_RsubstitusiActionPerformed

    private void cmbAlasanSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlasanSubActionPerformed
        if (cmbAlasanSub.getSelectedIndex() == 7 && Rsubstitusi.isSelected() == true) {
            TalasanSub.setEnabled(true);
            TalasanRes.setText("");
            TalasanRes.setEnabled(false);
        } else {
            TalasanSub.setText("");
            TalasanSub.setEnabled(false);
            TalasanRes.setText("");
            TalasanRes.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAlasanSubActionPerformed

    private void RswitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RswitchActionPerformed
        if (Rswitch.isSelected() == true) {
            cmbAlasanSub.setSelectedIndex(0);
            cmbAlasanSwi.setSelectedIndex(0);
            cmbAlasanSto.setSelectedIndex(0);
            cmbAlasanSub.setEnabled(false);
            cmbAlasanSwi.setEnabled(true);
            cmbAlasanSto.setEnabled(false);
            TalasanRes.setText("");
            TalasanRes.setEnabled(false);
        }
    }//GEN-LAST:event_RswitchActionPerformed

    private void RstopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RstopActionPerformed
        if (Rstop.isSelected() == true) {
            cmbAlasanSub.setSelectedIndex(0);
            cmbAlasanSwi.setSelectedIndex(0);
            cmbAlasanSto.setSelectedIndex(0);
            cmbAlasanSub.setEnabled(false);
            cmbAlasanSwi.setEnabled(false);
            cmbAlasanSto.setEnabled(true);
            TalasanRes.setText("");
            TalasanRes.setEnabled(false);
        }
    }//GEN-LAST:event_RstopActionPerformed

    private void RrestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RrestartActionPerformed
        if (Rrestart.isSelected() == true) {
            cmbAlasanSub.setSelectedIndex(0);
            cmbAlasanSwi.setSelectedIndex(0);
            cmbAlasanSto.setSelectedIndex(0);
            cmbAlasanSub.setEnabled(false);
            cmbAlasanSwi.setEnabled(false);
            cmbAlasanSto.setEnabled(false);
            TalasanRes.setText("");
            TalasanRes.setEnabled(true);
        }
    }//GEN-LAST:event_RrestartActionPerformed

    private void TalasanResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanResKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TalasanRes, TnmRejimenBaru);
        }
    }//GEN-LAST:event_TalasanResKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgTerapiAntiretroviralHIV dialog = new DlgTerapiAntiretroviralHIV(new javax.swing.JFrame(), true);
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
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.RadioButton Rrestart;
    private widget.RadioButton Rstop;
    private widget.RadioButton Rsubstitusi;
    private widget.RadioButton Rswitch;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.TextBox TalasanRes;
    private widget.TextBox TalasanSub;
    private widget.TextBox Tjk;
    private widget.TextBox TnmRejimenBaru;
    private widget.TextBox TtglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbAlasanSto;
    private widget.ComboBox cmbAlasanSub;
    private widget.ComboBox cmbAlasanSwi;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
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
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbHIV;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tglTerapi;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLHR, p.jk, "
                    + "DATE_FORMAT(h.tanggal,'%d-%m-%Y') tglTerapi, h.jns_terapi_art, h.alasan, h.alasan_substitusi_lainnya, "
                    + "h.alasan_restart, h.nm_rejimen_baru, h.tgl_simpan, h.tanggal, h.alasan_fix, p.tgl_lahir FROM hiv_terapi_antiretroviral h "
                    + "inner join pasien p on p.no_rkm_medis=h.no_rkm_medis WHERE "
                    + "h.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "h.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "h.tanggal between ? and ? and h.jns_terapi_art like ? or "
                    + "h.tanggal between ? and ? and h.alasan like ? or "
                    + "h.tanggal between ? and ? and h.alasan_substitusi_lainnya like ? or "
                    + "h.tanggal between ? and ? and h.alasan_restart like ? or "
                    + "h.tanggal between ? and ? and h.nm_rejimen_baru like ? order by h.tanggal desc");       
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk").replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan"),
                        rs.getString("tglLHR"),
                        rs.getString("tglTerapi"),
                        rs.getString("jns_terapi_art"),
                        rs.getString("alasan_fix"),
                        rs.getString("alasan_substitusi_lainnya"),
                        rs.getString("alasan_restart"),
                        rs.getString("nm_rejimen_baru"),
                        rs.getString("tgl_simpan"),
                        rs.getString("tanggal"),
                        rs.getString("alasan"),
                        rs.getString("tgl_lahir")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.DlgTerapiAntiretroviralHIV.tampil() : " + e);
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
        tglsimpanTerapi = "";
        nilaiAlasan = "";
        alasanya = "";
        Rsubstitusi.setSelected(false);
        Rswitch.setSelected(false);
        Rstop.setSelected(false);
        Rrestart.setSelected(false);
        tglTerapi.setDate(new Date());
        cmbAlasanSub.setSelectedIndex(0);
        cmbAlasanSwi.setSelectedIndex(0);
        cmbAlasanSto.setSelectedIndex(0);
        cmbAlasanSub.setEnabled(false);
        cmbAlasanSwi.setEnabled(false);
        cmbAlasanSto.setEnabled(false);
        TalasanSub.setText("");
        TalasanSub.setEnabled(false);
        TalasanRes.setText("");
        TalasanRes.setEnabled(false);
        TnmRejimenBaru.setText("");
    }

    private void getData() {
        emptTeks();
        jnsTerapi = "";        
        if (tbHIV.getSelectedRow() != -1) { 
            TNoRM.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 0).toString());
            TPasien.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 1).toString());
            Tjk.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 2).toString());
            TtglLahir.setText(Valid.SetTglINDONESIA(tbHIV.getValueAt(tbHIV.getSelectedRow(), 13).toString()));
            jnsTerapi = tbHIV.getValueAt(tbHIV.getSelectedRow(), 5).toString();
            Valid.SetTgl(tglTerapi, tbHIV.getValueAt(tbHIV.getSelectedRow(), 11).toString());
            TalasanSub.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 7).toString());
            TalasanRes.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 8).toString());
            TnmRejimenBaru.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 9).toString());            
            tglsimpanTerapi = tbHIV.getValueAt(tbHIV.getSelectedRow(), 10).toString();
            
            if (jnsTerapi.equals("Substitusi")) {
                Rsubstitusi.setSelected(true);
                cmbAlasanSub.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 12).toString());
                cmbAlasanSub.setEnabled(true);
                cmbAlasanSwi.setEnabled(false);
                cmbAlasanSto.setEnabled(false);
                TalasanRes.setEnabled(false);
            } else if (jnsTerapi.equals("Switch")) {
                Rswitch.setSelected(true);
                cmbAlasanSwi.setEnabled(true);
                cmbAlasanSwi.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 12).toString());
                cmbAlasanSub.setEnabled(false);
                cmbAlasanSto.setEnabled(false);
                TalasanRes.setEnabled(false);
            } else if (jnsTerapi.equals("Stop")) {
                Rstop.setSelected(true);
                cmbAlasanSto.setEnabled(true);
                cmbAlasanSto.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 12).toString());
                cmbAlasanSwi.setEnabled(false);
                cmbAlasanSub.setEnabled(false);
                TalasanRes.setEnabled(false);
            } else if (jnsTerapi.equals("Restart")) {
                Rrestart.setSelected(true);
                cmbAlasanSub.setEnabled(false);
                cmbAlasanSwi.setEnabled(false);
                cmbAlasanSto.setEnabled(false);
                TalasanRes.setEnabled(true);
            }

            if (cmbAlasanSub.getSelectedIndex() == 7 && Rsubstitusi.isSelected() == true) {
                TalasanSub.setEnabled(true);
                cmbAlasanSwi.setEnabled(false);
                cmbAlasanSto.setEnabled(false);
                TalasanRes.setEnabled(false);
            }
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
