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
public class DlgFollowUpPerawatanTerapiARThiv extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String tglsimpan = "",ppk = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgFollowUpPerawatanTerapiARThiv(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Tgl. Follow-UP",
            "Rencana Tgl. Kunj.", "BB (Kg.)", "TB (Cm.)", "Status Fungsional", "Stad WHO",
            "Hamil", "Metode KB", "Infeksi Oportunistik", "Infeksi Oportunistik (Lainnya)", "Obat Untuk IO ",
            "Stts. TB (Skerining TB)", "PPK", "Obat ARV & Dosis yg. Diberikan", "Adherance ART", "Efek Samping ART",
            "Efek Samping ART (Lainnya)", "Jumlah Cd4", "Hasil Lab.", "Diberikan Kondom", "Rujuk Ke Spesialis / MRS",
            "tgl_simpan", "tgl_follow_up", "rencana_tgl_kunjungan", "tgl_lahir"
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
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

        for (i = 0; i < 29; i++) {
            TableColumn column = tbHIV.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(95);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(110);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(60);
            } else if (i == 11) {
                column.setPreferredWidth(200);
            } else if (i == 12) {
                column.setPreferredWidth(120);
            } else if (i == 13) {
                column.setPreferredWidth(200);
            } else if (i == 14) {
                column.setPreferredWidth(200);
            } else if (i == 15) {
                column.setPreferredWidth(250);
            } else if (i == 16) {
                column.setPreferredWidth(50);
            } else if (i == 17) {
                column.setPreferredWidth(200);
            } else if (i == 18) {
                column.setPreferredWidth(110);
            } else if (i == 19) {
                column.setPreferredWidth(100);
            } else if (i == 20) {
                column.setPreferredWidth(200);
            } else if (i == 21) {
                column.setPreferredWidth(120);
            } else if (i == 22) {
                column.setPreferredWidth(200);
            } else if (i == 23) {
                column.setPreferredWidth(100);
            } else if (i == 24) {
                column.setPreferredWidth(200);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbHIV.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        TBB.setDocument(new batasInput((byte) 3).getOnlyAngka(TBB));
        TTB.setDocument(new batasInput((byte) 3).getOnlyAngka(TTB));
        
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
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel15 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel82 = new widget.Label();
        TglFollowup = new widget.Tanggal();
        jLabel92 = new widget.Label();
        TglRencKun = new widget.Tanggal();
        jLabel93 = new widget.Label();
        TBB = new widget.TextBox();
        jLabel110 = new widget.Label();
        TTB = new widget.TextBox();
        jLabel95 = new widget.Label();
        cmbSttsFung = new widget.ComboBox();
        TstadWHO = new widget.TextBox();
        jLabel102 = new widget.Label();
        cmbHamil = new widget.ComboBox();
        jLabel111 = new widget.Label();
        TmetodeKB = new widget.TextBox();
        jLabel98 = new widget.Label();
        cmbInfeksiOpor = new widget.ComboBox();
        jLabel112 = new widget.Label();
        TInfeksiLain = new widget.TextBox();
        jLabel100 = new widget.Label();
        TobatUtkIO = new widget.TextBox();
        jLabel101 = new widget.Label();
        cmbSttsTB = new widget.ComboBox();
        jLabel105 = new widget.Label();
        TobatARV = new widget.TextBox();
        jLabel113 = new widget.Label();
        cmbAdherance = new widget.ComboBox();
        jLabel114 = new widget.Label();
        cmbEfekSamping = new widget.ComboBox();
        jLabel115 = new widget.Label();
        TEfekSampingLain = new widget.TextBox();
        jLabel116 = new widget.Label();
        TJumlahCD4 = new widget.TextBox();
        jLabel117 = new widget.Label();
        THasilLab = new widget.TextBox();
        jLabel118 = new widget.Label();
        cmbDiberiKondom = new widget.ComboBox();
        jLabel119 = new widget.Label();
        TRujukKeSpesialis = new widget.TextBox();
        jLabel120 = new widget.Label();
        RY = new widget.RadioButton();
        RT = new widget.RadioButton();
        jLabel97 = new widget.Label();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ 9. Follow-Up Perawatan Pasien & Terapi ART (HIV) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
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
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
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
        panelGlass10.add(BtnAll);

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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 310));
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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pasien : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 130, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(133, 10, 90, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(226, 10, 307, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jns. Kelamin : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(292, 40, 100, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(393, 40, 140, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Lahir : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(292, 70, 100, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(393, 70, 140, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Tgl. Follow-UP : ");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 40, 130, 23);

        TglFollowup.setEditable(false);
        TglFollowup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-03-2022" }));
        TglFollowup.setDisplayFormat("dd-MM-yyyy");
        TglFollowup.setName("TglFollowup"); // NOI18N
        TglFollowup.setOpaque(false);
        FormInput.add(TglFollowup);
        TglFollowup.setBounds(133, 40, 90, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Rencana Tgl. Kunjgn. : ");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 70, 130, 23);

        TglRencKun.setEditable(false);
        TglRencKun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-03-2022" }));
        TglRencKun.setDisplayFormat("dd-MM-yyyy");
        TglRencKun.setName("TglRencKun"); // NOI18N
        TglRencKun.setOpaque(false);
        FormInput.add(TglRencKun);
        TglRencKun.setBounds(133, 70, 90, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("BB (Kg.) : ");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 100, 130, 23);

        TBB.setForeground(new java.awt.Color(0, 0, 0));
        TBB.setName("TBB"); // NOI18N
        TBB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBBKeyPressed(evt);
            }
        });
        FormInput.add(TBB);
        TBB.setBounds(133, 100, 60, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("TB Untuk Anak (Cm.) : ");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(282, 100, 190, 23);

        TTB.setForeground(new java.awt.Color(0, 0, 0));
        TTB.setName("TTB"); // NOI18N
        TTB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTBKeyPressed(evt);
            }
        });
        FormInput.add(TTB);
        TTB.setBounds(473, 100, 60, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Status Fungsional : ");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 130, 130, 23);

        cmbSttsFung.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsFung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Kerja", "2. Ambulatori", "3. Baring" }));
        cmbSttsFung.setName("cmbSttsFung"); // NOI18N
        cmbSttsFung.setOpaque(false);
        cmbSttsFung.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbSttsFung);
        cmbSttsFung.setBounds(133, 130, 100, 23);

        TstadWHO.setForeground(new java.awt.Color(0, 0, 0));
        TstadWHO.setName("TstadWHO"); // NOI18N
        TstadWHO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TstadWHOKeyPressed(evt);
            }
        });
        FormInput.add(TstadWHO);
        TstadWHO.setBounds(313, 130, 220, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Hamil : ");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(0, 160, 130, 23);

        cmbHamil.setForeground(new java.awt.Color(0, 0, 0));
        cmbHamil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbHamil.setName("cmbHamil"); // NOI18N
        cmbHamil.setOpaque(false);
        cmbHamil.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbHamil);
        cmbHamil.setBounds(133, 160, 60, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Atau Metode KB : ");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(203, 160, 110, 23);

        TmetodeKB.setForeground(new java.awt.Color(0, 0, 0));
        TmetodeKB.setName("TmetodeKB"); // NOI18N
        TmetodeKB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmetodeKBKeyPressed(evt);
            }
        });
        FormInput.add(TmetodeKB);
        TmetodeKB.setBounds(313, 160, 220, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Infeksi Oportunistik : ");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 190, 130, 23);

        cmbInfeksiOpor.setForeground(new java.awt.Color(0, 0, 0));
        cmbInfeksiOpor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "K", "D", "Cr", "PCP", "CMV", "P", "Z", "S", "T", "H", "Lain-lain" }));
        cmbInfeksiOpor.setName("cmbInfeksiOpor"); // NOI18N
        cmbInfeksiOpor.setOpaque(false);
        cmbInfeksiOpor.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbInfeksiOpor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInfeksiOporActionPerformed(evt);
            }
        });
        FormInput.add(cmbInfeksiOpor);
        cmbInfeksiOpor.setBounds(133, 190, 70, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Lain-lain, Uraikan : ");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(203, 190, 110, 23);

        TInfeksiLain.setForeground(new java.awt.Color(0, 0, 0));
        TInfeksiLain.setName("TInfeksiLain"); // NOI18N
        TInfeksiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInfeksiLainKeyPressed(evt);
            }
        });
        FormInput.add(TInfeksiLain);
        TInfeksiLain.setBounds(314, 190, 218, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Obat Untuk IO : ");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(0, 220, 130, 23);

        TobatUtkIO.setForeground(new java.awt.Color(0, 0, 0));
        TobatUtkIO.setName("TobatUtkIO"); // NOI18N
        TobatUtkIO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatUtkIOKeyPressed(evt);
            }
        });
        FormInput.add(TobatUtkIO);
        TobatUtkIO.setBounds(133, 220, 399, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Stts. TB (Skerining TB) : ");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 250, 130, 23);

        cmbSttsTB.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsTB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Tidak ada gejala/tanda TB", "2. Suspek TB (rujuk ke klinik DOTS atau pemeriksaan sputum)", "3. Dalam terapi TB" }));
        cmbSttsTB.setName("cmbSttsTB"); // NOI18N
        cmbSttsTB.setOpaque(false);
        cmbSttsTB.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbSttsTB);
        cmbSttsTB.setBounds(133, 250, 330, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Obat ARV & Dosis yg. Diberikan : ");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(534, 10, 200, 23);

        TobatARV.setForeground(new java.awt.Color(0, 0, 0));
        TobatARV.setName("TobatARV"); // NOI18N
        TobatARV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatARVKeyPressed(evt);
            }
        });
        FormInput.add(TobatARV);
        TobatARV.setBounds(737, 10, 240, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Adherance ART : ");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(534, 40, 200, 23);

        cmbAdherance.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdherance.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. (>95%)", "2. (80-95%)", "3. (<80%)" }));
        cmbAdherance.setName("cmbAdherance"); // NOI18N
        cmbAdherance.setOpaque(false);
        cmbAdherance.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbAdherance);
        cmbAdherance.setBounds(737, 40, 100, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Efek Samping ART : ");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(534, 70, 200, 23);

        cmbEfekSamping.setForeground(new java.awt.Color(0, 0, 0));
        cmbEfekSamping.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "R", "Mua", "Mun", "D", "N", "Ikt", "An", "Ll", "SK", "Dem", "Hip", "Dep", "P", "Lip", "Ngan", "Ln" }));
        cmbEfekSamping.setName("cmbEfekSamping"); // NOI18N
        cmbEfekSamping.setOpaque(false);
        cmbEfekSamping.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbEfekSamping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEfekSampingActionPerformed(evt);
            }
        });
        FormInput.add(cmbEfekSamping);
        cmbEfekSamping.setBounds(737, 70, 100, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Efek Samping ART (Lain, Uraikan) : ");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(534, 100, 200, 23);

        TEfekSampingLain.setForeground(new java.awt.Color(0, 0, 0));
        TEfekSampingLain.setName("TEfekSampingLain"); // NOI18N
        TEfekSampingLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEfekSampingLainKeyPressed(evt);
            }
        });
        FormInput.add(TEfekSampingLain);
        TEfekSampingLain.setBounds(737, 100, 240, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Jumlah Cd4 : ");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(534, 130, 200, 23);

        TJumlahCD4.setForeground(new java.awt.Color(0, 0, 0));
        TJumlahCD4.setName("TJumlahCD4"); // NOI18N
        TJumlahCD4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJumlahCD4KeyPressed(evt);
            }
        });
        FormInput.add(TJumlahCD4);
        TJumlahCD4.setBounds(737, 130, 240, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Hasil Laboratorium : ");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(534, 160, 200, 23);

        THasilLab.setForeground(new java.awt.Color(0, 0, 0));
        THasilLab.setName("THasilLab"); // NOI18N
        THasilLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THasilLabKeyPressed(evt);
            }
        });
        FormInput.add(THasilLab);
        THasilLab.setBounds(737, 160, 240, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Diberikan Kondom : ");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(534, 190, 200, 23);

        cmbDiberiKondom.setForeground(new java.awt.Color(0, 0, 0));
        cmbDiberiKondom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak", "Tidak Ada" }));
        cmbDiberiKondom.setName("cmbDiberiKondom"); // NOI18N
        cmbDiberiKondom.setOpaque(false);
        cmbDiberiKondom.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbDiberiKondom);
        cmbDiberiKondom.setBounds(737, 190, 100, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Rujuk Ke Spesialis / MRS : ");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(534, 220, 200, 23);

        TRujukKeSpesialis.setForeground(new java.awt.Color(0, 0, 0));
        TRujukKeSpesialis.setName("TRujukKeSpesialis"); // NOI18N
        TRujukKeSpesialis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRujukKeSpesialisKeyPressed(evt);
            }
        });
        FormInput.add(TRujukKeSpesialis);
        TRujukKeSpesialis.setBounds(737, 220, 240, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("PPK : ");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(534, 250, 200, 23);

        RY.setBackground(new java.awt.Color(240, 250, 230));
        RY.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RY);
        RY.setSelected(true);
        RY.setText("Y");
        RY.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RY.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RY.setName("RY"); // NOI18N
        RY.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(RY);
        RY.setBounds(737, 250, 40, 23);

        RT.setBackground(new java.awt.Color(240, 250, 230));
        RT.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(RT);
        RT.setText("T");
        RT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RT.setName("RT"); // NOI18N
        RT.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(RT);
        RT.setBounds(790, 250, 40, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Stad WHO : ");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(233, 130, 80, 23);

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
            } else if (TBB.getText().trim().equals("")) {
                Valid.textKosong(TBB, "Berat Badan (Kg.)");
                TBB.requestFocus();
            } else if (cmbInfeksiOpor.getSelectedIndex() == 11 && TInfeksiLain.getText().trim().equals("")) {
                Valid.textKosong(TInfeksiLain, "Infeksi Oportunistik (Lain-lain, Uraikan)");
                TInfeksiLain.requestFocus();
            } else if (cmbEfekSamping.getSelectedIndex() == 16 && TEfekSampingLain.getText().trim().equals("")) {
                Valid.textKosong(TEfekSampingLain, "Efek Samping ART (Lain, Uraikan)");
                TEfekSampingLain.requestFocus();
            } else {
                ppk = "";
                if (RY.isSelected() == true) {
                    ppk = RY.getText();
                } else if (RT.isSelected() == true) {
                    ppk = RT.getText();
                }
                
                Sequel.menyimpan("hiv_follow_up_perawatan_dan_terapi",
                    "'" + TNoRM.getText() + "','" + Valid.SetTgl(TglFollowup.getSelectedItem() + "") + "',"
                    + "'" + Valid.SetTgl(TglRencKun.getSelectedItem() + "") + "','" + TBB.getText() + "',"
                    + "'" + TTB.getText() + "','" + cmbSttsFung.getSelectedItem().toString() + "','" + TstadWHO.getText() + "',"
                    + "'" + cmbHamil.getSelectedItem().toString() + "','" + TmetodeKB.getText() + "',"
                    + "'" + cmbInfeksiOpor.getSelectedItem().toString() + "','" + TInfeksiLain.getText() + "',"
                    + "'" + TobatUtkIO.getText() + "','" + cmbSttsTB.getSelectedItem().toString() + "','" + ppk + "',"
                    + "'" + TobatARV.getText() + "','" + cmbAdherance.getSelectedItem().toString() + "',"
                    + "'" + cmbEfekSamping.getSelectedItem().toString() + "','" + TEfekSampingLain.getText() + "',"
                    + "'" + TJumlahCD4.getText() + "','" + THasilLab.getText() + "','" + cmbDiberiKondom.getSelectedItem().toString() + "',"
                    + "'" + TRujukKeSpesialis.getText() + "',now()", "Data Follow-Up Perawatan Pasien & Terapi ART");
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
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        try {
            if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRM, "Pasien");
            } else if (tglsimpan.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel yg. akan diperbaiki...!!!!");
                tbHIV.requestFocus();
            } else if (TBB.getText().trim().equals("")) {
                Valid.textKosong(TBB, "Berat Badan (Kg.)");
                TBB.requestFocus();
            } else if (cmbInfeksiOpor.getSelectedIndex() == 11 && TInfeksiLain.getText().trim().equals("")) {
                Valid.textKosong(TInfeksiLain, "Infeksi Oportunistik (Lain-lain, Uraikan)");
                TInfeksiLain.requestFocus();
            } else if (cmbEfekSamping.getSelectedIndex() == 16 && TEfekSampingLain.getText().trim().equals("")) {
                Valid.textKosong(TEfekSampingLain, "Efek Samping ART (Lain, Uraikan)");
                TEfekSampingLain.requestFocus();
            } else {
                ppk = "";
                if (RY.isSelected() == true) {
                    ppk = RY.getText();
                } else if (RT.isSelected() == true) {
                    ppk = RT.getText();
                }

                Sequel.mengedit("hiv_follow_up_perawatan_dan_terapi", "tgl_simpan='" + tglsimpan + "'",
                    "tgl_follow_up='" + Valid.SetTgl(TglFollowup.getSelectedItem() + "") + "',"
                    + "rencana_tgl_kunjungan='" + Valid.SetTgl(TglRencKun.getSelectedItem() + "") + "', "
                    + "bb='" + TBB.getText() + "',"
                    + "tb='" + TTB.getText() + "', "
                    + "stts_fungsional='" + cmbSttsFung.getSelectedItem().toString() + "',"
                    + "stad_who='" + TstadWHO.getText() + "',"
                    + "hamil='" + cmbHamil.getSelectedItem().toString() + "',"
                    + "metode_kb='" + TmetodeKB.getText() + "',"
                    + "infeksi_oportunistik='" + cmbInfeksiOpor.getSelectedItem().toString() + "',"
                    + "infeksi_oportunistik_lainya='" + TInfeksiLain.getText() + "',"
                    + "obat_utk_io='" + TobatUtkIO.getText() + "',"
                    + "status_tb='" + cmbSttsTB.getSelectedItem().toString() + "',"
                    + "ppk='" + ppk + "',"
                    + "obat_arv_dan_dosis_yg_diberikan='" + TobatARV.getText() + "',"
                    + "adherance_art='" + cmbAdherance.getSelectedItem().toString() + "',"
                    + "efek_samping_art='" + cmbEfekSamping.getSelectedItem().toString() + "',"
                    + "efek_samping_art_lain='" + TEfekSampingLain.getText() + "',"
                    + "jumlah_cd4='" + TJumlahCD4.getText() + "',"
                    + "hasil_lab='" + THasilLab.getText() + "',"
                    + "diberikan_kondom='" + cmbDiberiKondom.getSelectedItem().toString() + "',"
                    + "rujuk_ke_spesialis_atau_mrs='" + TRujukKeSpesialis.getText() + "'");

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

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (TNoRM.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (tglsimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data Follow-Up Perawatan Pasien & Terapi ART pada tabel yg. akan dihapus...!!!!");
            tbHIV.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from hiv_follow_up_perawatan_dan_terapi where tgl_simpan='" + tglsimpan + "'");
                tampil();
                emptTeks();
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void cmbInfeksiOporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInfeksiOporActionPerformed
        TInfeksiLain.setText("");
        if (cmbInfeksiOpor.getSelectedIndex() == 11) {
            TInfeksiLain.setEnabled(true);
        } else {
            TInfeksiLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbInfeksiOporActionPerformed

    private void cmbEfekSampingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEfekSampingActionPerformed
        TEfekSampingLain.setText("");
        if (cmbEfekSamping.getSelectedIndex() == 16) {
            TEfekSampingLain.setEnabled(true);
        } else {
            TEfekSampingLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbEfekSampingActionPerformed

    private void TBBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TBB, TTB);
        }
    }//GEN-LAST:event_TBBKeyPressed

    private void TTBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TTB, cmbSttsFung);
        }
    }//GEN-LAST:event_TTBKeyPressed

    private void TstadWHOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TstadWHOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TstadWHO, cmbHamil);
        }
    }//GEN-LAST:event_TstadWHOKeyPressed

    private void TmetodeKBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmetodeKBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TmetodeKB, cmbInfeksiOpor);
        }
    }//GEN-LAST:event_TmetodeKBKeyPressed

    private void TInfeksiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInfeksiLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TInfeksiLain, TobatUtkIO);
        }
    }//GEN-LAST:event_TInfeksiLainKeyPressed

    private void TobatUtkIOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatUtkIOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TobatUtkIO, cmbSttsTB);
        }
    }//GEN-LAST:event_TobatUtkIOKeyPressed

    private void TobatARVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatARVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TobatARV, cmbAdherance);
        }
    }//GEN-LAST:event_TobatARVKeyPressed

    private void TEfekSampingLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEfekSampingLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TEfekSampingLain, TJumlahCD4);
        }
    }//GEN-LAST:event_TEfekSampingLainKeyPressed

    private void TJumlahCD4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJumlahCD4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TJumlahCD4, THasilLab);
        }
    }//GEN-LAST:event_TJumlahCD4KeyPressed

    private void THasilLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THasilLabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, THasilLab, cmbDiberiKondom);
        }
    }//GEN-LAST:event_THasilLabKeyPressed

    private void TRujukKeSpesialisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRujukKeSpesialisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Valid.pindah(evt, TRujukKeSpesialis, BtnSimpan);
        }
    }//GEN-LAST:event_TRujukKeSpesialisKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgFollowUpPerawatanTerapiARThiv dialog = new DlgFollowUpPerawatanTerapiARThiv(new javax.swing.JFrame(), true);
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
    private widget.RadioButton RT;
    private widget.RadioButton RY;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TBB;
    public widget.TextBox TCari;
    private widget.TextBox TEfekSampingLain;
    private widget.TextBox THasilLab;
    private widget.TextBox TInfeksiLain;
    private widget.TextBox TJumlahCD4;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.TextBox TRujukKeSpesialis;
    private widget.TextBox TTB;
    private widget.Tanggal TglFollowup;
    private widget.Tanggal TglRencKun;
    private widget.TextBox Tjk;
    private widget.TextBox TmetodeKB;
    private widget.TextBox TobatARV;
    private widget.TextBox TobatUtkIO;
    private widget.TextBox TstadWHO;
    private widget.TextBox TtglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbAdherance;
    private widget.ComboBox cmbDiberiKondom;
    private widget.ComboBox cmbEfekSamping;
    private widget.ComboBox cmbHamil;
    private widget.ComboBox cmbInfeksiOpor;
    private widget.ComboBox cmbSttsFung;
    private widget.ComboBox cmbSttsTB;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel105;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel120;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel82;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel95;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbHIV;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLHR, p.jk, "
                    + "DATE_FORMAT(h.tgl_follow_up,'%d-%m-%Y') tglfollow, DATE_FORMAT(h.rencana_tgl_kunjungan,'%d-%m-%Y') tglRencana, "
                    + "h.bb, h.tb, h.stts_fungsional, h.stad_who, h.hamil, h.metode_kb, h.infeksi_oportunistik, h.infeksi_oportunistik_lainya, "
                    + "h.obat_utk_io, h.status_tb, h.ppk, h.obat_arv_dan_dosis_yg_diberikan, h.adherance_art, h.efek_samping_art, "
                    + "h.efek_samping_art_lain, h.jumlah_cd4, h.hasil_lab, h.diberikan_kondom, h.rujuk_ke_spesialis_atau_mrs, h.tgl_simpan, "
                    + "h.tgl_follow_up, h.rencana_tgl_kunjungan, p.tgl_lahir FROM hiv_follow_up_perawatan_dan_terapi h "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = h.no_rkm_medis WHERE "
                    + "p.no_rkm_medis LIKE ? OR "
                    + "p.nm_pasien LIKE ? OR "
                    + "h.bb LIKE ? OR "
                    + "h.tb LIKE ? OR "
                    + "h.stts_fungsional LIKE ? OR "
                    + "h.stad_who LIKE ? OR "
                    + "h.hamil LIKE ? OR "
                    + "h.metode_kb LIKE ? OR "
                    + "h.infeksi_oportunistik LIKE ? OR "
                    + "h.infeksi_oportunistik_lainya LIKE ? OR "
                    + "h.obat_utk_io LIKE ? OR "
                    + "h.status_tb LIKE ? OR "
                    + "h.ppk LIKE ? OR "
                    + "h.obat_arv_dan_dosis_yg_diberikan LIKE ? OR "
                    + "h.adherance_art LIKE ? OR "
                    + "h.efek_samping_art LIKE ? OR "
                    + "h.efek_samping_art_lain LIKE ? OR "
                    + "h.jumlah_cd4 LIKE ? OR "
                    + "h.hasil_lab LIKE ? OR "
                    + "h.diberikan_kondom LIKE ? OR "
                    + "h.rujuk_ke_spesialis_atau_mrs LIKE ? ORDER BY h.no_rkm_medis DESC LIMIT 50");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk").replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan"),
                        rs.getString("tglLHR"),
                        rs.getString("tglfollow"),
                        rs.getString("tglRencana"),
                        rs.getString("bb"),
                        rs.getString("tb"),
                        rs.getString("stts_fungsional"),
                        rs.getString("stad_who"),
                        rs.getString("hamil"),
                        rs.getString("metode_kb"),                        
                        rs.getString("infeksi_oportunistik"),
                        rs.getString("infeksi_oportunistik_lainya"),
                        rs.getString("obat_utk_io"),
                        rs.getString("status_tb"),
                        rs.getString("ppk"),
                        rs.getString("obat_arv_dan_dosis_yg_diberikan"),
                        rs.getString("adherance_art"),
                        rs.getString("efek_samping_art"),
                        rs.getString("efek_samping_art_lain"),
                        rs.getString("jumlah_cd4"),
                        rs.getString("hasil_lab"),
                        rs.getString("diberikan_kondom"),                        
                        rs.getString("rujuk_ke_spesialis_atau_mrs"),
                        rs.getString("tgl_simpan"),
                        rs.getString("tgl_follow_up"),
                        rs.getString("rencana_tgl_kunjungan"),
                        rs.getString("tgl_lahir")
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
        tglsimpan = "";
        TglFollowup.setDate(new Date());
        TglRencKun.setDate(new Date());
        TBB.setText("");
        TTB.setText("");
        cmbSttsFung.setSelectedIndex(0);
        TstadWHO.setText("");
        cmbHamil.setSelectedIndex(0);
        TmetodeKB.setText("");
        cmbInfeksiOpor.setSelectedIndex(0);
        TInfeksiLain.setText("");
        TobatUtkIO.setText("");
        cmbSttsTB.setSelectedIndex(0);
        TobatARV.setText("");
        cmbAdherance.setSelectedIndex(0);
        cmbEfekSamping.setSelectedIndex(0);
        TEfekSampingLain.setText("");
        TJumlahCD4.setText("");
        THasilLab.setText("");
        cmbDiberiKondom.setSelectedIndex(0);
        TRujukKeSpesialis.setText("");
        RY.setSelected(true);
        RT.setSelected(false);
        
        TInfeksiLain.setEnabled(false);
        TEfekSampingLain.setEnabled(false);
    }

    private void getData() {
        emptTeks();
        ppk = "";
        if (tbHIV.getSelectedRow() != -1) {
            TNoRM.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 0).toString());
            TPasien.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 1).toString());
            Tjk.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 2).toString());
            TtglLahir.setText(Valid.SetTglINDONESIA(tbHIV.getValueAt(tbHIV.getSelectedRow(), 28).toString()));
            TBB.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 6).toString());
            TTB.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 7).toString());
            cmbSttsFung.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 8).toString());
            TstadWHO.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 9).toString());
            cmbHamil.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 10).toString());
            TmetodeKB.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 11).toString());
            cmbInfeksiOpor.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 12).toString());
            TInfeksiLain.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 13).toString());
            TobatUtkIO.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 14).toString());
            cmbSttsTB.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 15).toString());
            ppk = tbHIV.getValueAt(tbHIV.getSelectedRow(), 16).toString();
            TobatARV.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 17).toString());
            cmbAdherance.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 18).toString());
            cmbEfekSamping.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 19).toString());
            TEfekSampingLain.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 20).toString());
            TJumlahCD4.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 21).toString());
            THasilLab.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 22).toString());
            cmbDiberiKondom.setSelectedItem(tbHIV.getValueAt(tbHIV.getSelectedRow(), 23).toString());
            TRujukKeSpesialis.setText(tbHIV.getValueAt(tbHIV.getSelectedRow(), 24).toString());
            tglsimpan = tbHIV.getValueAt(tbHIV.getSelectedRow(), 25).toString();
            Valid.SetTgl(TglFollowup, tbHIV.getValueAt(tbHIV.getSelectedRow(), 26).toString());
            Valid.SetTgl(TglRencKun, tbHIV.getValueAt(tbHIV.getSelectedRow(), 27).toString());
            
            if (ppk.equals("Y")) {
                RY.setSelected(true);
                RT.setSelected(false);
            } else if (ppk.equals("T")) {
                RY.setSelected(false);
                RT.setSelected(true);
            }
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 310));
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
        TCari.setText(norm);
    }
}
