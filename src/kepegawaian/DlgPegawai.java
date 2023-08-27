package kepegawaian;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public final class DlgPegawai extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private String idDep = "", idSttsWP = "", idSttsKJ = "", idJenjang = "", idIndex = "";
    private int cekNIK = 0;

    /** Creates new form DlgPetugas
     * @param parent
     * @param modal */
    public DlgPegawai(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);

        Object[] row = {"NIP", "Nama Pegawai/Karyawan", "Tmp. Lahir", "Tgl. Lahir", "J.K.", "Alamat", "Pendidikan", "Jabatan", "Jenjang Jabatan", "Status Aktif",
            "NIK KTP", "Departeman", "Bidang", "Status WP", "Status Kerja", "NPWP", "Gaji Pokok (Rp.)", "Mulai Kerja", "Masa Kerja", "Indexing", "Nama Bank", 
            "No. Rekening", "Wajib Masuk", "Pengurang","Indek", "Mulai Kontrak","Cuti Diambil", "Dankes"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbPegawai.setModel(tabMode);
        tbPegawai.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPegawai.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 28; i++) {
            TableColumn column = tbPegawai.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(270);
            } else if (i == 6) {
                column.setPreferredWidth(135);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(140);
            } else if (i == 12) {
                column.setPreferredWidth(140);
            } else if (i == 13) {
                column.setPreferredWidth(200);
            } else if (i == 14) {
                column.setPreferredWidth(140);
            } else if (i == 15) {
                column.setPreferredWidth(140);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(75);
            } else if (i == 18) {
                column.setPreferredWidth(65);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(140);
            } else if (i == 21) {
                column.setPreferredWidth(140);
            } else if (i == 22) {
                column.setPreferredWidth(70);
            } else if (i == 23) {
                column.setPreferredWidth(90);
            } else if (i == 24) {
                column.setPreferredWidth(50);
            } else if (i == 25) {
                column.setPreferredWidth(82);
            } else if (i == 26) {
                column.setPreferredWidth(72);
            } else if (i == 27) {
                column.setPreferredWidth(90);
            }
        }
        tbPegawai.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"NIP", "Nama Pegawai/Karyawan", "Tmp. Lahir", "Tgl. Lahir", "J.K.", "Alamat", "Pendidikan", "Jabatan", "Jenjang Jabatan", "Status Aktif",
            "NIK KTP", "Departeman", "Bidang", "Status WP", "Status Kerja", "NPWP", "Gaji Pokok (Rp.)", "Mulai Kerja", "Masa Kerja", "Indexing", "Nama Bank", 
            "No. Rekening", "Wajib Masuk", "Pengurang","Indek", "Mulai Kontrak","Cuti Diambil", "Dankes"}) {
                @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
//            @Override
//            public boolean isCellEditable(int rowIndex, int colIndex) {
//                boolean a = false;
//                if (colIndex == 0) {
//                    a = true;
//                }
//                return a;
//            }
//            Class[] types = new Class[]{
//                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
//                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
//                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
//                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
//                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
//                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
//                java.lang.Object.class
//            };
//
//            @Override
//            public Class getColumnClass(int columnIndex) {
//                return types[columnIndex];
//            }
        };
        tbPegawai1.setModel(tabMode1);
        tbPegawai1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPegawai1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 28; i++) {
            TableColumn column = tbPegawai1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(270);
            } else if (i == 6) {
                column.setPreferredWidth(135);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(140);
            } else if (i == 12) {
                column.setPreferredWidth(140);
            } else if (i == 13) {
                column.setPreferredWidth(200);
            } else if (i == 14) {
                column.setPreferredWidth(140);
            } else if (i == 15) {
                column.setPreferredWidth(140);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(75);
            } else if (i == 18) {
                column.setPreferredWidth(65);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(140);
            } else if (i == 21) {
                column.setPreferredWidth(140);
            } else if (i == 22) {
                column.setPreferredWidth(70);
            } else if (i == 23) {
                column.setPreferredWidth(90);
            } else if (i == 24) {
                column.setPreferredWidth(50);
            } else if (i == 25) {
                column.setPreferredWidth(82);
            } else if (i == 26) {
                column.setPreferredWidth(72);
            } else if (i == 27) {
                column.setPreferredWidth(90);
            }
        }
        tbPegawai1.setDefaultRenderer(Object.class, new WarnaTable());

        TNip.setDocument(new batasInput((byte)20).getKata(TNip));        
        TNm.setDocument(new batasInput((byte)50).getKata(TNm));
        TTmp.setDocument(new batasInput((byte)50).getKata(TTmp));
        TAlmt.setDocument(new batasInput((byte)60).getKata(TAlmt));
        npwp.setDocument(new batasInput((byte)40).getKata(npwp));
        TnoKTP.setDocument(new batasInput((byte) 20).getKata(TnoKTP));
        TGapok.setDocument(new batasInput((byte)10).getOnlyAngka(TGapok));
        TRek.setDocument(new batasInput((byte)50).getKata(TRek));
        TwajibMsk.setDocument(new batasInput((byte)2).getOnlyAngka(TwajibMsk));
        Tpengurang.setDocument(new batasInput((byte)10).getOnlyAngka(Tpengurang));
        Tindek.setDocument(new batasInput((byte)2).getOnlyAngka(Tindek));
        Tdankes.setDocument(new batasInput((byte)10).getOnlyAngka(Tdankes));
        Tcuti.setDocument(new batasInput((byte)3).getOnlyAngka(Tcuti));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilAktif();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilAktif();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilAktif();}
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
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TTmp = new widget.TextBox();
        CmbJk = new widget.ComboBox();
        TNm = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel13 = new widget.Label();
        DTPLahir = new widget.Tanggal();
        jLabel19 = new widget.Label();
        cmbSttsAktif = new widget.ComboBox();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        jLabel12 = new widget.Label();
        TAlmt = new widget.TextBox();
        TNip = new widget.TextBox();
        TnoKTP = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        npwp = new widget.TextBox();
        cmbDep = new widget.ComboBox();
        cmbBid = new widget.ComboBox();
        cmbsttsWP = new widget.ComboBox();
        cmbsttsKJ = new widget.ComboBox();
        cmbJenjang = new widget.ComboBox();
        jLabel27 = new widget.Label();
        cmbPendidikan = new widget.ComboBox();
        jLabel28 = new widget.Label();
        TGapok = new widget.TextBox();
        jLabel29 = new widget.Label();
        TKota = new widget.TextBox();
        jLabel30 = new widget.Label();
        DTPmulaiKJ = new widget.Tanggal();
        jLabel31 = new widget.Label();
        cmbMasaKJ = new widget.ComboBox();
        jLabel32 = new widget.Label();
        cmbIndex = new widget.ComboBox();
        jLabel33 = new widget.Label();
        cmbBank = new widget.ComboBox();
        jLabel34 = new widget.Label();
        TRek = new widget.TextBox();
        jLabel35 = new widget.Label();
        TwajibMsk = new widget.TextBox();
        jLabel36 = new widget.Label();
        Tpengurang = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tindek = new widget.TextBox();
        jLabel38 = new widget.Label();
        DTPmulaiKontrak = new widget.Tanggal();
        jLabel39 = new widget.Label();
        Tcuti = new widget.TextBox();
        jLabel40 = new widget.Label();
        Tdankes = new widget.TextBox();
        cmbJabatan = new widget.ComboBox();
        ChkInput = new widget.CekBox();
        TabPegawai = new javax.swing.JTabbedPane();
        scrollPane1 = new widget.ScrollPane();
        tbPegawai = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbPegawai1 = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Pegawai/Karyawan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(260, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('T');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
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
        BtnAll.setMnemonic('S');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+S");
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
        panelGlass9.add(BtnAll);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass9.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(850, 340));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(850, 137));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NIP / NR :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 105, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Pegawai :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 42, 105, 23);

        TTmp.setForeground(new java.awt.Color(0, 0, 0));
        TTmp.setName("TTmp"); // NOI18N
        TTmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTmpKeyPressed(evt);
            }
        });
        FormInput.add(TTmp);
        TTmp.setBounds(109, 102, 206, 23);

        CmbJk.setForeground(new java.awt.Color(0, 0, 0));
        CmbJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        CmbJk.setName("CmbJk"); // NOI18N
        CmbJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJkKeyPressed(evt);
            }
        });
        FormInput.add(CmbJk);
        CmbJk.setBounds(109, 72, 100, 23);

        TNm.setForeground(new java.awt.Color(0, 0, 0));
        TNm.setName("TNm"); // NOI18N
        TNm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmKeyPressed(evt);
            }
        });
        FormInput.add(TNm);
        TNm.setBounds(109, 42, 310, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jenis Kelamin :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 72, 105, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tmp/Tgl. Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 102, 105, 23);

        DTPLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-08-2021" }));
        DTPLahir.setDisplayFormat("dd-MM-yyyy");
        DTPLahir.setName("DTPLahir"); // NOI18N
        DTPLahir.setOpaque(false);
        DTPLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPLahirKeyPressed(evt);
            }
        });
        FormInput.add(DTPLahir);
        DTPLahir.setBounds(319, 102, 100, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Status Aktif :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(431, 12, 80, 23);

        cmbSttsAktif.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsAktif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AKTIF", "CUTI", "KELUAR", "TENAGA LUAR", "PENSIUN" }));
        cmbSttsAktif.setLightWeightPopupEnabled(false);
        cmbSttsAktif.setName("cmbSttsAktif"); // NOI18N
        cmbSttsAktif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbSttsAktifKeyPressed(evt);
            }
        });
        FormInput.add(cmbSttsAktif);
        cmbSttsAktif.setBounds(515, 12, 115, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Alamat :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(431, 42, 80, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("NIK KTP :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(211, 72, 55, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jabatan  :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(431, 102, 80, 23);

        TAlmt.setForeground(new java.awt.Color(0, 0, 0));
        TAlmt.setName("TAlmt"); // NOI18N
        TAlmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlmtKeyPressed(evt);
            }
        });
        FormInput.add(TAlmt);
        TAlmt.setBounds(515, 42, 330, 23);

        TNip.setForeground(new java.awt.Color(0, 0, 0));
        TNip.setName("TNip"); // NOI18N
        TNip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNipKeyPressed(evt);
            }
        });
        FormInput.add(TNip);
        TNip.setBounds(109, 12, 270, 23);

        TnoKTP.setForeground(new java.awt.Color(0, 0, 0));
        TnoKTP.setName("TnoKTP"); // NOI18N
        TnoKTP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoKTPKeyPressed(evt);
            }
        });
        FormInput.add(TnoKTP);
        TnoKTP.setBounds(270, 72, 150, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Departemen :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 132, 105, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jenjang Jab. :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(431, 132, 80, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Bidang :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 162, 105, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Status WP :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 192, 105, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Status Kerja :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 222, 105, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("NPWP :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 252, 105, 23);

        npwp.setForeground(new java.awt.Color(0, 0, 0));
        npwp.setName("npwp"); // NOI18N
        npwp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                npwpKeyPressed(evt);
            }
        });
        FormInput.add(npwp);
        npwp.setBounds(109, 252, 310, 23);

        cmbDep.setForeground(new java.awt.Color(0, 0, 0));
        cmbDep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbDep.setName("cmbDep"); // NOI18N
        FormInput.add(cmbDep);
        cmbDep.setBounds(109, 132, 170, 23);

        cmbBid.setForeground(new java.awt.Color(0, 0, 0));
        cmbBid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbBid.setName("cmbBid"); // NOI18N
        FormInput.add(cmbBid);
        cmbBid.setBounds(109, 162, 110, 23);

        cmbsttsWP.setForeground(new java.awt.Color(0, 0, 0));
        cmbsttsWP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbsttsWP.setName("cmbsttsWP"); // NOI18N
        FormInput.add(cmbsttsWP);
        cmbsttsWP.setBounds(109, 192, 211, 23);

        cmbsttsKJ.setForeground(new java.awt.Color(0, 0, 0));
        cmbsttsKJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbsttsKJ.setName("cmbsttsKJ"); // NOI18N
        FormInput.add(cmbsttsKJ);
        cmbsttsKJ.setBounds(109, 222, 130, 23);

        cmbJenjang.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenjang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbJenjang.setName("cmbJenjang"); // NOI18N
        FormInput.add(cmbJenjang);
        cmbJenjang.setBounds(515, 132, 150, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Pendidikan :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(431, 162, 80, 23);

        cmbPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPendidikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbPendidikan.setName("cmbPendidikan"); // NOI18N
        FormInput.add(cmbPendidikan);
        cmbPendidikan.setBounds(515, 162, 165, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Gaji Pokok :");
        jLabel28.setToolTipText("");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(431, 192, 80, 23);

        TGapok.setForeground(new java.awt.Color(0, 0, 0));
        TGapok.setName("TGapok"); // NOI18N
        TGapok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGapokKeyPressed(evt);
            }
        });
        FormInput.add(TGapok);
        TGapok.setBounds(515, 192, 150, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Kota :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(431, 72, 80, 23);

        TKota.setForeground(new java.awt.Color(0, 0, 0));
        TKota.setName("TKota"); // NOI18N
        TKota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKotaKeyPressed(evt);
            }
        });
        FormInput.add(TKota);
        TKota.setBounds(515, 72, 330, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Mulai Kerja :");
        jLabel30.setToolTipText("");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(680, 102, 70, 23);

        DTPmulaiKJ.setEditable(false);
        DTPmulaiKJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-08-2021" }));
        DTPmulaiKJ.setDisplayFormat("dd-MM-yyyy");
        DTPmulaiKJ.setName("DTPmulaiKJ"); // NOI18N
        DTPmulaiKJ.setOpaque(false);
        FormInput.add(DTPmulaiKJ);
        DTPmulaiKJ.setBounds(755, 102, 90, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Masa Kerja :");
        jLabel31.setToolTipText("");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(680, 132, 70, 23);

        cmbMasaKJ.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasaKJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "<1", "PT", "FT>1" }));
        cmbMasaKJ.setName("cmbMasaKJ"); // NOI18N
        FormInput.add(cmbMasaKJ);
        cmbMasaKJ.setBounds(755, 132, 60, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Indexing :");
        jLabel32.setToolTipText("");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(680, 192, 70, 23);

        cmbIndex.setForeground(new java.awt.Color(0, 0, 0));
        cmbIndex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbIndex.setName("cmbIndex"); // NOI18N
        FormInput.add(cmbIndex);
        cmbIndex.setBounds(755, 192, 60, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Bank :");
        jLabel33.setToolTipText("");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(680, 162, 70, 23);

        cmbBank.setForeground(new java.awt.Color(0, 0, 0));
        cmbBank.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbBank.setName("cmbBank"); // NOI18N
        FormInput.add(cmbBank);
        cmbBank.setBounds(755, 162, 120, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Rekening :");
        jLabel34.setToolTipText("");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(431, 222, 80, 23);

        TRek.setForeground(new java.awt.Color(0, 0, 0));
        TRek.setName("TRek"); // NOI18N
        TRek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRekKeyPressed(evt);
            }
        });
        FormInput.add(TRek);
        TRek.setBounds(515, 222, 330, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Wajib Masuk :");
        jLabel35.setToolTipText("");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(431, 252, 80, 23);

        TwajibMsk.setForeground(new java.awt.Color(0, 0, 0));
        TwajibMsk.setName("TwajibMsk"); // NOI18N
        TwajibMsk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TwajibMskKeyPressed(evt);
            }
        });
        FormInput.add(TwajibMsk);
        TwajibMsk.setBounds(515, 252, 50, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Pengurang :");
        jLabel36.setToolTipText("");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(565, 252, 70, 23);

        Tpengurang.setForeground(new java.awt.Color(0, 0, 0));
        Tpengurang.setName("Tpengurang"); // NOI18N
        Tpengurang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpengurangKeyPressed(evt);
            }
        });
        FormInput.add(Tpengurang);
        Tpengurang.setBounds(640, 252, 125, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Indek :");
        jLabel37.setToolTipText("");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(765, 252, 45, 23);

        Tindek.setForeground(new java.awt.Color(0, 0, 0));
        Tindek.setName("Tindek"); // NOI18N
        Tindek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindekKeyPressed(evt);
            }
        });
        FormInput.add(Tindek);
        Tindek.setBounds(815, 252, 50, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Mulai Kontrak :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 282, 105, 23);

        DTPmulaiKontrak.setEditable(false);
        DTPmulaiKontrak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-08-2021" }));
        DTPmulaiKontrak.setDisplayFormat("dd-MM-yyyy");
        DTPmulaiKontrak.setName("DTPmulaiKontrak"); // NOI18N
        DTPmulaiKontrak.setOpaque(false);
        FormInput.add(DTPmulaiKontrak);
        DTPmulaiKontrak.setBounds(109, 282, 90, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Cuti Diambil :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(431, 282, 80, 23);

        Tcuti.setForeground(new java.awt.Color(0, 0, 0));
        Tcuti.setName("Tcuti"); // NOI18N
        Tcuti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcutiKeyPressed(evt);
            }
        });
        FormInput.add(Tcuti);
        Tcuti.setBounds(515, 282, 50, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Dankes :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(201, 282, 50, 23);

        Tdankes.setForeground(new java.awt.Color(0, 0, 0));
        Tdankes.setName("Tdankes"); // NOI18N
        Tdankes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdankesKeyPressed(evt);
            }
        });
        FormInput.add(Tdankes);
        Tdankes.setBounds(255, 282, 90, 23);

        cmbJabatan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJabatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbJabatan.setName("cmbJabatan"); // NOI18N
        FormInput.add(cmbJabatan);
        cmbJabatan.setBounds(515, 102, 165, 23);

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

        TabPegawai.setBackground(new java.awt.Color(250, 255, 245));
        TabPegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabPegawai.setForeground(new java.awt.Color(0, 0, 0));
        TabPegawai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabPegawai.setName("TabPegawai"); // NOI18N
        TabPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPegawaiMouseClicked(evt);
            }
        });

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPegawai.setAutoCreateRowSorter(true);
        tbPegawai.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPegawai.setName("tbPegawai"); // NOI18N
        tbPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPegawaiMouseClicked(evt);
            }
        });
        tbPegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPegawaiKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbPegawai);

        TabPegawai.addTab("Status Aktif", scrollPane1);

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbPegawai1.setAutoCreateRowSorter(true);
        tbPegawai1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPegawai1.setName("tbPegawai1"); // NOI18N
        tbPegawai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPegawai1MouseClicked(evt);
            }
        });
        tbPegawai1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPegawai1KeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(tbPegawai1);

        TabPegawai.addTab("Status Non Aktif", scrollPane2);

        internalFrame1.add(TabPegawai, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TTmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTmpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DTPLahir.requestFocus();
        }
}//GEN-LAST:event_TTmpKeyPressed

    private void CmbJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJkKeyPressed
        Valid.pindah(evt, TNm, TnoKTP);
}//GEN-LAST:event_CmbJkKeyPressed

    private void TNmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CmbJk.requestFocus();
        }
}//GEN-LAST:event_TNmKeyPressed

    private void DTPLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPLahirKeyPressed
        Valid.pindah(evt, TTmp, cmbDep);
}//GEN-LAST:event_DTPLahirKeyPressed

    private void cmbSttsAktifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSttsAktifKeyPressed
        Valid.pindah(evt, DTPmulaiKontrak, TAlmt);
}//GEN-LAST:event_cmbSttsAktifKeyPressed

    private void TAlmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlmtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TKota.requestFocus();
        }
}//GEN-LAST:event_TAlmtKeyPressed

    private void TNipKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNipKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TNm.requestFocus();
        }
}//GEN-LAST:event_TNipKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        cekNIK = 0;
        
        if (TNip.getText().trim().equals("")) {
            Valid.textKosong(TNip, "NIP/NR");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama pegawai");
        } else if (TTmp.getText().trim().equals("")) {
            Valid.textKosong(TTmp, "Tempat lahir");
        } else if (TAlmt.getText().trim().equals("")) {
            Valid.textKosong(TAlmt, "Alamatnya");
        } else if (TnoKTP.getText().trim().equals("")) {
            Valid.textKosong(TnoKTP, "NIK KTP");
        } else if (TKota.getText().trim().equals("")) {
            Valid.textKosong(TKota, "Nama kota untuk alamatnya");
        } else if (cmbMasaKJ.getSelectedIndex() == 0) {
            Valid.textKosong(cmbMasaKJ, "Masa kerja");
        } else {            
            cekNIK = Sequel.cariInteger("select count(-1) from pegawai where nik='" + TNip.getText() + "'");

            if (cekNIK == 0) {
                cekData();
                Sequel.menyimpan("pegawai", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", 30, new String[]{
                    "0", TNip.getText(), TNm.getText(), CmbJk.getSelectedItem().toString().replaceAll("PEREMPUAN", "Wanita").replaceAll("LAKI-LAKI", "Pria"),
                    cmbJabatan.getSelectedItem().toString(), idJenjang, idDep, cmbBid.getSelectedItem().toString(), idSttsWP, idSttsKJ, npwp.getText(),
                    cmbPendidikan.getSelectedItem().toString(), TGapok.getText(), TTmp.getText(), Valid.SetTgl(DTPLahir.getSelectedItem() + ""), TAlmt.getText(),
                    TKota.getText(), Valid.SetTgl(DTPmulaiKJ.getSelectedItem() + ""), cmbMasaKJ.getSelectedItem().toString(), idIndex, cmbBank.getSelectedItem().toString(),
                    TRek.getText(), cmbSttsAktif.getSelectedItem().toString(), TwajibMsk.getText(), Tpengurang.getText(), Tindek.getText(), Valid.SetTgl(DTPmulaiKontrak.getSelectedItem() + ""),
                    Tcuti.getText(), Tdankes.getText(), TnoKTP.getText()
                });

                TabPegawaiMouseClicked(null);
                emptTeks();
            } else if (cekNIK > 0) {
                JOptionPane.showMessageDialog(rootPane, "NIK/NR : " + TNip.getText() + " sudah tersimpan di database, proses simpan data GAGAL...!!!");
                TNip.requestFocus();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Tcuti, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm(); 
        emptTeks();
        TabPegawaiMouseClicked(null);
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnEdit);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TabPegawaiMouseClicked(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNip.getText().trim().equals("")) {
            Valid.textKosong(TNip, "NIP/NR");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama pegawai");
        } else if (TTmp.getText().trim().equals("")) {
            Valid.textKosong(TTmp, "Tempat lahir");
        } else if (TAlmt.getText().trim().equals("")) {
            Valid.textKosong(TAlmt, "Alamatnya");
        } else if (TnoKTP.getText().trim().equals("")) {
            Valid.textKosong(TnoKTP, "NIK KTP");
        } else if (TKota.getText().trim().equals("")) {
            Valid.textKosong(TKota, "Nama kota untuk alamatnya");
        } else if (cmbMasaKJ.getSelectedIndex() == 0) {
            Valid.textKosong(cmbMasaKJ, "Masa kerja");
        } else {
            cekData();
            Sequel.mengedit("pegawai", "nik='" + TNip.getText() + "'",
                    "nama='" + TNm.getText() + "', jk='" + CmbJk.getSelectedItem().toString().replaceAll("PEREMPUAN", "Wanita").replaceAll("LAKI-LAKI", "Pria") + "',"
                    + "jbtn='" + cmbJabatan.getSelectedItem().toString() + "', jnj_jabatan='" + idJenjang + "', departemen='" + idDep + "', bidang='" + cmbBid.getSelectedItem().toString() + "',"
                    + "stts_wp='" + idSttsWP + "', stts_kerja='" + idSttsKJ + "', npwp='" + npwp.getText() + "', pendidikan='" + cmbPendidikan.getSelectedItem().toString() + "', gapok='" + TGapok.getText() + "',"
                    + "tmp_lahir='" + TTmp.getText() + "', tgl_lahir='" + Valid.SetTgl(DTPLahir.getSelectedItem() + "") + "', alamat='" + TAlmt.getText() + "', kota='" + TKota.getText() + "',"
                    + "mulai_kerja='" + Valid.SetTgl(DTPmulaiKJ.getSelectedItem() + "") + "', ms_kerja='" + cmbMasaKJ.getSelectedItem().toString() + "', indexins='" + idIndex + "',"
                    + "bpd='" + cmbBank.getSelectedItem().toString() + "', rekening='" + TRek.getText() + "', stts_aktif='" + cmbSttsAktif.getSelectedItem().toString() + "', wajibmasuk='" + TwajibMsk.getText() + "',"
                    + "pengurang='" + Tpengurang.getText() + "', indek='" + Tindek.getText() + "', mulai_kontrak='" + Valid.SetTgl(DTPmulaiKontrak.getSelectedItem() + "") + "', cuti_diambil='" + Tcuti.getText() + "',"
                    + "dankes='" + Tdankes.getText() + "', no_ktp='" + TnoKTP.getText() + "'");

            TabPegawaiMouseClicked(null);
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar, BtnBatal);
        }
}//GEN-LAST:event_BtnEditKeyPressed

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
        TabPegawaiMouseClicked(null);
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void tbPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPegawaiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getDataAktif();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPegawaiMouseClicked

    private void tbPegawaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPegawaiKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataAktif();
                } catch (java.lang.NullPointerException e) {
                }
            }            
        }
}//GEN-LAST:event_tbPegawaiKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabPegawaiMouseClicked(null);
        Sequel.cariIsiComboDB("select nama from departemen where dep_id <>'-'", cmbDep);
        Sequel.cariIsiComboDB("select nama from bidang where nama <>'-'", cmbBid);
        Sequel.cariIsiComboDB("select ktg from stts_wp WHERE stts <>'-'", cmbsttsWP);
        Sequel.cariIsiComboDB("select ktg from stts_kerja WHERE stts <>'-'", cmbsttsKJ);
        Sequel.cariIsiComboDB("select nm_jbtn from jabatan WHERE kd_jbtn <>'-'", cmbJabatan);
        Sequel.cariIsiComboDB("select nama from jnj_jabatan WHERE kode <>'-'", cmbJenjang);
        Sequel.cariIsiComboDB("select tingkat from pendidikan WHERE tingkat <>'-'", cmbPendidikan);
        Sequel.cariIsiComboDB("select persen from indexins WHERE dep_id <>'-'", cmbIndex);
        Sequel.cariIsiComboDB("select namabank from bank where namabank<>'-'", cmbBank);
    }//GEN-LAST:event_formWindowOpened

    private void TnoKTPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoKTPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TTmp.requestFocus();
        }
    }//GEN-LAST:event_TnoKTPKeyPressed

    private void npwpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_npwpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DTPmulaiKontrak.requestFocus();
        }
    }//GEN-LAST:event_npwpKeyPressed

    private void TGapokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGapokKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TRek.requestFocus();
        }
    }//GEN-LAST:event_TGapokKeyPressed

    private void TKotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKotaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJabatan.requestFocus();
        }
    }//GEN-LAST:event_TKotaKeyPressed

    private void TRekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TwajibMsk.requestFocus();
        }
    }//GEN-LAST:event_TRekKeyPressed

    private void TwajibMskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TwajibMskKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpengurang.requestFocus();
        }
    }//GEN-LAST:event_TwajibMskKeyPressed

    private void TpengurangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpengurangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tindek.requestFocus();
        }
    }//GEN-LAST:event_TpengurangKeyPressed

    private void TindekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tcuti.requestFocus();
        }
    }//GEN-LAST:event_TindekKeyPressed

    private void TcutiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcutiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TcutiKeyPressed

    private void TdankesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdankesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSttsAktif.requestFocus();
        }
    }//GEN-LAST:event_TdankesKeyPressed

    private void tbPegawai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPegawai1MouseClicked
        if(tabMode1.getRowCount()!=0){
            try {
                getDataNonAktif();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPegawai1MouseClicked

    private void tbPegawai1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPegawai1KeyPressed
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataNonAktif();
                } catch (java.lang.NullPointerException e) {
                }
            }            
        }
    }//GEN-LAST:event_tbPegawai1KeyPressed

    private void TabPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPegawaiMouseClicked
        if (TabPegawai.getSelectedIndex() == 0) {
            tampilAktif();
        } else if (TabPegawai.getSelectedIndex() == 1) {
            tampilNonAktif();
        }
    }//GEN-LAST:event_TabPegawaiMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPegawai dialog = new DlgPegawai(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.ComboBox CmbJk;
    private widget.Tanggal DTPLahir;
    private widget.Tanggal DTPmulaiKJ;
    private widget.Tanggal DTPmulaiKontrak;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox TAlmt;
    public widget.TextBox TCari;
    private widget.TextBox TGapok;
    private widget.TextBox TKota;
    private widget.TextBox TNip;
    private widget.TextBox TNm;
    private widget.TextBox TRek;
    private widget.TextBox TTmp;
    private javax.swing.JTabbedPane TabPegawai;
    private widget.TextBox Tcuti;
    private widget.TextBox Tdankes;
    private widget.TextBox Tindek;
    private widget.TextBox TnoKTP;
    private widget.TextBox Tpengurang;
    private widget.TextBox TwajibMsk;
    private widget.ComboBox cmbBank;
    private widget.ComboBox cmbBid;
    private widget.ComboBox cmbDep;
    private widget.ComboBox cmbIndex;
    private widget.ComboBox cmbJabatan;
    private widget.ComboBox cmbJenjang;
    private widget.ComboBox cmbMasaKJ;
    private widget.ComboBox cmbPendidikan;
    private widget.ComboBox cmbSttsAktif;
    private widget.ComboBox cmbsttsKJ;
    private widget.ComboBox cmbsttsWP;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel6;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox npwp;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbPegawai;
    private widget.Table tbPegawai1;
    // End of variables declaration//GEN-END:variables

    private void tampilAktif() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.nik, p.nama, p.tmp_lahir, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, IF(p.jk='Pria','Laki-laki','Perempuan') jk, CONCAT(p.alamat,', ',p.kota) almt,"
                    + "p.pendidikan, p.jbtn, jj.nama nm_jbtn, p.stts_aktif, p.no_ktp, d.nama nm_dep, p.bidang, sw.ktg stwp, sk.ktg stsk, p.npwp, format(p.gapok,0) gapok, date_format(p.mulai_kerja,'%d-%m-%Y') ml_krja,"
                    + "p.ms_kerja, i.persen indeksing, p.bpd nm_bank, p.rekening, p.wajibmasuk, p.pengurang, p.indek, date_format(p.mulai_kontrak,'%d-%m-%Y') ml_kontrak, p.cuti_diambil,"
                    + "p.dankes FROM pegawai p INNER JOIN jnj_jabatan jj ON jj.kode=p.jnj_jabatan INNER JOIN departemen d ON d.dep_id=p.departemen "
                    + "INNER JOIN stts_wp sw ON sw.stts=p.stts_wp INNER JOIN stts_kerja sk ON sk.stts=p.stts_kerja INNER JOIN indexins i ON i.dep_id=p.indexins where "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.nik like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.nama like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.tmp_lahir like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and CONCAT(p.alamat, ', ', p.kota) like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.pendidikan like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.jbtn like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and jj.nama like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.stts_aktif like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.no_ktp like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and d.nama like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.bidang like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and sw.ktg like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and sk.ktg like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.npwp like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.gapok like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.ms_kerja like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and i.persen like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.bpd like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.rekening like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.wajibmasuk like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.pengurang like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.indek like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.cuti_diambil like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.dankes like ? order by p.nik DESC");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, "%" + TCari.getText().trim() + "%");
                ps.setString(8, "%" + TCari.getText().trim() + "%");
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");
                ps.setString(11, "%" + TCari.getText().trim() + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, "%" + TCari.getText().trim() + "%");
                ps.setString(14, "%" + TCari.getText().trim() + "%");
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, "%" + TCari.getText().trim() + "%");
                ps.setString(17, "%" + TCari.getText().trim() + "%");
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, "%" + TCari.getText().trim() + "%");
                ps.setString(20, "%" + TCari.getText().trim() + "%");
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, "%" + TCari.getText().trim() + "%");
                ps.setString(23, "%" + TCari.getText().trim() + "%");
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("nik"),
                        rs.getString("nama"),
                        rs.getString("tmp_lahir"),
                        rs.getString("tgl_lhr"),
                        rs.getString("jk"),
                        rs.getString("almt"),
                        rs.getString("pendidikan"),
                        rs.getString("jbtn"),
                        rs.getString("nm_jbtn"),
                        rs.getString("stts_aktif"),
                        rs.getString("no_ktp"),
                        rs.getString("nm_dep"),
                        rs.getString("bidang"),
                        rs.getString("stwp"),
                        rs.getString("stsk"),
                        rs.getString("npwp"),
                        rs.getString("gapok"),
                        rs.getString("ml_krja"),
                        rs.getString("ms_kerja"),
                        rs.getString("indeksing"),
                        rs.getString("nm_bank"),
                        rs.getString("rekening"),
                        rs.getString("wajibmasuk"),
                        rs.getString("pengurang"),
                        rs.getString("indek"),
                        rs.getString("ml_kontrak"),
                        rs.getString("cuti_diambil"),
                        rs.getString("dankes")
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
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
    
    private void tampilNonAktif() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT p.nik, p.nama, p.tmp_lahir, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, IF(p.jk='Pria','Laki-laki','Perempuan') jk, CONCAT(p.alamat,', ',p.kota) almt,"
                    + "p.pendidikan, p.jbtn, jj.nama nm_jbtn, p.stts_aktif, p.no_ktp, d.nama nm_dep, p.bidang, sw.ktg stwp, sk.ktg stsk, p.npwp, format(p.gapok,0) gapok, date_format(p.mulai_kerja,'%d-%m-%Y') ml_krja,"
                    + "p.ms_kerja, i.persen indeksing, p.bpd nm_bank, p.rekening, p.wajibmasuk, p.pengurang, p.indek, date_format(p.mulai_kontrak,'%d-%m-%Y') ml_kontrak, p.cuti_diambil,"
                    + "p.dankes FROM pegawai p INNER JOIN jnj_jabatan jj ON jj.kode=p.jnj_jabatan INNER JOIN departemen d ON d.dep_id=p.departemen "
                    + "INNER JOIN stts_wp sw ON sw.stts=p.stts_wp INNER JOIN stts_kerja sk ON sk.stts=p.stts_kerja INNER JOIN indexins i ON i.dep_id=p.indexins where "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.nik like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.nama like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.tmp_lahir like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and CONCAT(p.alamat, ', ', p.kota) like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.pendidikan like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.jbtn like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and jj.nama like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.stts_aktif like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.no_ktp like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and d.nama like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.bidang like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and sw.ktg like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and sk.ktg like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.npwp like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.gapok like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.ms_kerja like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and i.persen like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.bpd like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.rekening like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.wajibmasuk like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.pengurang like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.indek like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.cuti_diambil like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.dankes like ? order by p.nik DESC");
            try {
                ps1.setString(1, "%" + TCari.getText().trim() + "%");
                ps1.setString(2, "%" + TCari.getText().trim() + "%");
                ps1.setString(3, "%" + TCari.getText().trim() + "%");
                ps1.setString(4, "%" + TCari.getText().trim() + "%");
                ps1.setString(5, "%" + TCari.getText().trim() + "%");
                ps1.setString(6, "%" + TCari.getText().trim() + "%");
                ps1.setString(7, "%" + TCari.getText().trim() + "%");
                ps1.setString(8, "%" + TCari.getText().trim() + "%");
                ps1.setString(9, "%" + TCari.getText().trim() + "%");
                ps1.setString(10, "%" + TCari.getText().trim() + "%");
                ps1.setString(11, "%" + TCari.getText().trim() + "%");
                ps1.setString(12, "%" + TCari.getText().trim() + "%");
                ps1.setString(13, "%" + TCari.getText().trim() + "%");
                ps1.setString(14, "%" + TCari.getText().trim() + "%");
                ps1.setString(15, "%" + TCari.getText().trim() + "%");
                ps1.setString(16, "%" + TCari.getText().trim() + "%");
                ps1.setString(17, "%" + TCari.getText().trim() + "%");
                ps1.setString(18, "%" + TCari.getText().trim() + "%");
                ps1.setString(19, "%" + TCari.getText().trim() + "%");
                ps1.setString(20, "%" + TCari.getText().trim() + "%");
                ps1.setString(21, "%" + TCari.getText().trim() + "%");
                ps1.setString(22, "%" + TCari.getText().trim() + "%");
                ps1.setString(23, "%" + TCari.getText().trim() + "%");
                ps1.setString(24, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("nik"),
                        rs1.getString("nama"),
                        rs1.getString("tmp_lahir"),
                        rs1.getString("tgl_lhr"),
                        rs1.getString("jk"),
                        rs1.getString("almt"),
                        rs1.getString("pendidikan"),
                        rs1.getString("jbtn"),
                        rs1.getString("nm_jbtn"),
                        rs1.getString("stts_aktif"),
                        rs1.getString("no_ktp"),
                        rs1.getString("nm_dep"),
                        rs1.getString("bidang"),
                        rs1.getString("stwp"),
                        rs1.getString("stsk"),
                        rs1.getString("npwp"),
                        rs1.getString("gapok"),
                        rs1.getString("ml_krja"),
                        rs1.getString("ms_kerja"),
                        rs1.getString("indeksing"),
                        rs1.getString("nm_bank"),
                        rs1.getString("rekening"),
                        rs1.getString("wajibmasuk"),
                        rs1.getString("pengurang"),
                        rs1.getString("indek"),
                        rs1.getString("ml_kontrak"),
                        rs1.getString("cuti_diambil"),
                        rs1.getString("dankes")
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
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

    public void emptTeks() {
        TNip.setText("");
        TNm.setText("");
        CmbJk.setSelectedIndex(0);
        TTmp.setText("");
        DTPLahir.setDate(new Date());
        cmbDep.setSelectedIndex(0);
        cmbBid.setSelectedIndex(0);
        cmbsttsWP.setSelectedIndex(0);
        cmbsttsKJ.setSelectedIndex(0);
        npwp.setText("");
        DTPmulaiKontrak.setDate(new Date());
        cmbSttsAktif.setSelectedIndex(0);        
        TAlmt.setText("");        
        TnoKTP.setText("");
        TKota.setText("");
        cmbJabatan.setSelectedIndex(0);
        cmbJenjang.setSelectedIndex(0);
        cmbPendidikan.setSelectedIndex(0);
        TGapok.setText("0");
        DTPmulaiKJ.setDate(new Date());
        cmbMasaKJ.setSelectedIndex(0);
        cmbIndex.setSelectedIndex(0);
        cmbBank.setSelectedIndex(0);
        TRek.setText("");
        TwajibMsk.setText("0");
        Tpengurang.setText("0");
        Tindek.setText("0");
        Tdankes.setText("0");
        Tcuti.setText("0");
    }

    private void getDataAktif() {
        if (tbPegawai.getSelectedRow() != -1) {
            TNip.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 0).toString());
            TNm.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 1).toString());
            TnoKTP.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 10).toString());
            TTmp.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 2).toString());
            Valid.SetTgl(DTPLahir, Sequel.cariIsi("select tgl_lahir from pegawai where nik='" + TNip.getText() + "'"));
            cmbDep.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 11).toString());
            cmbBid.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 12).toString());
            cmbsttsWP.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 13).toString());
            cmbsttsKJ.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 14).toString());
            npwp.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 15).toString());
            Valid.SetTgl(DTPmulaiKontrak, Sequel.cariIsi("select mulai_kontrak from pegawai where nik='" + TNip.getText() + "'"));
            cmbSttsAktif.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 9).toString());
            TAlmt.setText(Sequel.cariIsi("select alamat from pegawai where nik='" + TNip.getText() + "'"));
            TKota.setText(Sequel.cariIsi("select kota from pegawai where nik='" + TNip.getText() + "'"));
            cmbJabatan.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 7).toString());
            cmbJenjang.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 8).toString());
            cmbPendidikan.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 6).toString());
            TGapok.setText(Sequel.cariIsi("select gapok from pegawai where nik='" + TNip.getText() + "'"));
            Valid.SetTgl(DTPmulaiKJ, Sequel.cariIsi("select mulai_kerja from pegawai where nik='" + TNip.getText() + "'"));
            cmbMasaKJ.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 18).toString());
            cmbIndex.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 19).toString());
            cmbBank.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 20).toString());
            TRek.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 21).toString());
            TwajibMsk.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 22).toString());
            Tpengurang.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 23).toString());
            Tindek.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 24).toString());
            Tdankes.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 27).toString());
            Tcuti.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 26).toString());
            
            switch (tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 4).toString()) {
                case "Laki-laki":
                    CmbJk.setSelectedItem("LAKI-LAKI");
                    break;
                case "Perempuan":
                    CmbJk.setSelectedItem("PEREMPUAN");
                    break;
            }
        }
    }
    
    private void getDataNonAktif() {
        if (tbPegawai1.getSelectedRow() != -1) {
            TNip.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 0).toString());
            TNm.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 1).toString());
            TnoKTP.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 10).toString());
            TTmp.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 2).toString());
            Valid.SetTgl(DTPLahir, Sequel.cariIsi("select tgl_lahir from pegawai where nik='" + TNip.getText() + "'"));
            cmbDep.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 11).toString());
            cmbBid.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 12).toString());
            cmbsttsWP.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 13).toString());
            cmbsttsKJ.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 14).toString());
            npwp.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 15).toString());
            Valid.SetTgl(DTPmulaiKontrak, Sequel.cariIsi("select mulai_kontrak from pegawai where nik='" + TNip.getText() + "'"));
            cmbSttsAktif.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 9).toString());
            TAlmt.setText(Sequel.cariIsi("select alamat from pegawai where nik='" + TNip.getText() + "'"));
            TKota.setText(Sequel.cariIsi("select kota from pegawai where nik='" + TNip.getText() + "'"));
            cmbJabatan.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 7).toString());
            cmbJenjang.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 8).toString());
            cmbPendidikan.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 6).toString());
            TGapok.setText(Sequel.cariIsi("select gapok from pegawai where nik='" + TNip.getText() + "'"));
            Valid.SetTgl(DTPmulaiKJ, Sequel.cariIsi("select mulai_kerja from pegawai where nik='" + TNip.getText() + "'"));
            cmbMasaKJ.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 18).toString());
            cmbIndex.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 19).toString());
            cmbBank.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 20).toString());
            TRek.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 21).toString());
            TwajibMsk.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 22).toString());
            Tpengurang.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 23).toString());
            Tindek.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 24).toString());
            Tdankes.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 27).toString());
            Tcuti.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 26).toString());

            switch (tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 4).toString()) {
                case "Laki-laki":
                    CmbJk.setSelectedItem("LAKI-LAKI");
                    break;
                case "Perempuan":
                    CmbJk.setSelectedItem("PEREMPUAN");
                    break;
            }
        }
    }

    public JTextField getTextField(){
        return TNip;
    }

    public JTable getTable(){
        return tbPegawai;
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 340));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpegawai_admin());
        BtnEdit.setEnabled(akses.getpegawai_admin());        
    }
    
    private void cekData() {
        idDep = "";
        idSttsWP = "";
        idSttsKJ = "";
        idJenjang = "";
        idIndex = "";

        if (cmbDep.getSelectedIndex() == 0) {
            idDep = Sequel.cariIsi("select dep_id from departemen where nama='" + cmbDep.getSelectedItem().toString() + "'");
        } else {
            idDep = Sequel.cariIsi("select dep_id from departemen where nama='" + cmbDep.getSelectedItem().toString() + "'");
        }
        
        if (cmbsttsWP.getSelectedIndex() == 0) {
            idSttsWP = Sequel.cariIsi("select stts from stts_wp where ktg='" + cmbsttsWP.getSelectedItem().toString() + "'");
        } else {
            idSttsWP = Sequel.cariIsi("select stts from stts_wp where ktg='" + cmbsttsWP.getSelectedItem().toString() + "'");
        }
        
        if (cmbsttsKJ.getSelectedIndex() == 0) {
            idSttsKJ = Sequel.cariIsi("select stts from stts_kerja where ktg='" + cmbsttsKJ.getSelectedItem().toString() + "'");
        } else {
            idSttsKJ = Sequel.cariIsi("select stts from stts_kerja where ktg='" + cmbsttsKJ.getSelectedItem().toString() + "'");
        }
        
        if (cmbJenjang.getSelectedIndex() == 0) {
            idJenjang = Sequel.cariIsi("select kode from jnj_jabatan where nama='" + cmbJenjang.getSelectedItem().toString() + "'");
        } else {
            idJenjang = Sequel.cariIsi("select kode from jnj_jabatan where nama='" + cmbJenjang.getSelectedItem().toString() + "'");
        }
        
        if (cmbIndex.getSelectedIndex() == 0) {
            idIndex = Sequel.cariIsi("select dep_id from indexins WHERE persen='" + cmbIndex.getSelectedItem().toString() + "'");
        } else {
            idIndex = Sequel.cariIsi("select dep_id from indexins WHERE persen='" + cmbIndex.getSelectedItem().toString() + "'");
        }
        
        if (TGapok.getText().equals("")) {
            TGapok.setText("0");
        }
        
        if (TRek.getText().equals("")) {
            TRek.setText("-");
        }
        
        if (TwajibMsk.getText().equals("")) {
            TwajibMsk.setText("0");
        }
        
        if (Tpengurang.getText().equals("")) {
            Tpengurang.setText("0");
        }
        
        if (Tindek.getText().equals("")) {
            Tindek.setText("0");
        }
        
        if (Tdankes.getText().equals("")) {
            Tdankes.setText("0");
        }
        
        if (Tcuti.getText().equals("")) {
            Tcuti.setText("0");
        }
        
        if (npwp.getText().equals("")) {
            npwp.setText("-");
        }
    }
}
