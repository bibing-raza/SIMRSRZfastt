package simrskhanza;

import bridging.BPJSCekReferensiFaskes;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPasien;

/**
 *
 * @author dosen
 */
public class DlgInputPonek extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private int i = 0, g = 0, a = 0, b = 0, j = 0;
    private String URUTNOREG = "", status = "", no_rawat = "", umur = "", sttsumur = "";

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgInputPonek(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //tabel ponek
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.", "No. Rawat", "No. RM", "Nama Pasien", "Alamatnya", "Jenis Alamat", "Tgl. Input",
            "Hamil Ke", "Umur Kehamilan", "Cara Persalinan", "Tindakan Lain"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPonek.setModel(tabMode);

        tbPonek.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPonek.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbPonek.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(390);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(120);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            }
        }
        tbPonek.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel diagnosa baru ponek
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "No.", "Kode Diagnosa", "Nama Diagnosa", "Status", "Kode ICD-10"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaBaru.setModel(tabMode1);

        tbDiagnosaBaru.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaBaru.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbDiagnosaBaru.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            }
        }
        tbDiagnosaBaru.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel diagnosa pasien
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No.", "Nama Diagnosa", "Kode ICD-10", "Deskripsi ICD-10", "kode diagnosa"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagPonek.setModel(tabMode3);

        tbDiagPonek.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagPonek.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbDiagPonek.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(180);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagPonek.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel input diagnosa ponek
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "K", "Kode Diagnosa", "Nama Diagnosa", "Kode ICD-10", "Deskripsi ICD-10"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbInputDiagnosa.setModel(tabMode2);

        tbInputDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbInputDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbInputDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(180);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            }
        }
        tbInputDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }

        ChkInput.setSelected(false);
        isForm();

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URUTNOREG = prop.getProperty("URUTNOREG");
        } catch (Exception ex) {
            URUTNOREG = "";
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        norwponek = new widget.TextBox();
        sttsDiagBaru = new widget.TextBox();
        kddiagnosainput = new widget.TextBox();
        cekDataponek = new widget.TextBox();
        cekDiagponek = new widget.TextBox();
        cekKodeDiag = new widget.TextBox();
        Popup = new javax.swing.JPopupMenu();
        MnHapusSalahSatu = new javax.swing.JMenuItem();
        MnHapusSemua = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPonek = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan2 = new widget.Button();
        BtnBatal = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        jLabel4 = new widget.Label();
        jLabel9 = new widget.Label();
        alamat = new widget.TextBox();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        cmbAlamat = new widget.ComboBox();
        cmbHamil = new widget.ComboBox();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel14 = new widget.Label();
        cmbUmurHamil = new widget.ComboBox();
        jLabel15 = new widget.Label();
        cmbTindakan = new widget.ComboBox();
        cmbPersalinan = new widget.ComboBox();
        jLabel10 = new widget.Label();
        TUmur = new widget.TextBox();
        norawat = new widget.TextBox();
        jLabel16 = new widget.Label();
        tglInput = new widget.Tanggal();
        internalFrame3 = new widget.InternalFrame();
        panelisi2 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbInputDiagnosa = new widget.Table();
        BtnCari2 = new widget.Button();
        label9 = new widget.Label();
        TCari1 = new widget.TextBox();
        internalFrame4 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        tbDiagnosaBaru = new widget.Table();
        jLabel5 = new widget.Label();
        kddiag = new widget.TextBox();
        jLabel8 = new widget.Label();
        nmdiag = new widget.TextBox();
        BtnTambah = new widget.Button();
        BtnSimpan1 = new widget.Button();
        BtnGantiDiagBaru = new widget.Button();
        BtnCari1 = new widget.Button();
        jLabel17 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel18 = new widget.Label();
        kdicd = new widget.TextBox();
        internalFrame5 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbDiagPonek = new widget.Table();

        norwponek.setEditable(false);
        norwponek.setForeground(new java.awt.Color(0, 0, 0));
        norwponek.setHighlighter(null);
        norwponek.setName("norwponek"); // NOI18N
        norwponek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norwponekKeyPressed(evt);
            }
        });

        sttsDiagBaru.setEditable(false);
        sttsDiagBaru.setForeground(new java.awt.Color(0, 0, 0));
        sttsDiagBaru.setHighlighter(null);
        sttsDiagBaru.setName("sttsDiagBaru"); // NOI18N
        sttsDiagBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sttsDiagBaruKeyPressed(evt);
            }
        });

        kddiagnosainput.setEditable(false);
        kddiagnosainput.setForeground(new java.awt.Color(0, 0, 0));
        kddiagnosainput.setHighlighter(null);
        kddiagnosainput.setName("kddiagnosainput"); // NOI18N
        kddiagnosainput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddiagnosainputKeyPressed(evt);
            }
        });

        cekDataponek.setEditable(false);
        cekDataponek.setForeground(new java.awt.Color(0, 0, 0));
        cekDataponek.setHighlighter(null);
        cekDataponek.setName("cekDataponek"); // NOI18N
        cekDataponek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekDataponekKeyPressed(evt);
            }
        });

        cekDiagponek.setEditable(false);
        cekDiagponek.setForeground(new java.awt.Color(0, 0, 0));
        cekDiagponek.setHighlighter(null);
        cekDiagponek.setName("cekDiagponek"); // NOI18N
        cekDiagponek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekDiagponekKeyPressed(evt);
            }
        });

        cekKodeDiag.setEditable(false);
        cekKodeDiag.setForeground(new java.awt.Color(0, 0, 0));
        cekKodeDiag.setHighlighter(null);
        cekKodeDiag.setName("cekKodeDiag"); // NOI18N
        cekKodeDiag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekKodeDiagKeyPressed(evt);
            }
        });

        Popup.setName("Popup"); // NOI18N

        MnHapusSalahSatu.setBackground(new java.awt.Color(255, 255, 255));
        MnHapusSalahSatu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusSalahSatu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusSalahSatu.setText("Hapus Salah Satu");
        MnHapusSalahSatu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusSalahSatu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusSalahSatu.setIconTextGap(5);
        MnHapusSalahSatu.setName("MnHapusSalahSatu"); // NOI18N
        MnHapusSalahSatu.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusSalahSatu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusSalahSatuActionPerformed(evt);
            }
        });
        Popup.add(MnHapusSalahSatu);

        MnHapusSemua.setBackground(new java.awt.Color(255, 255, 255));
        MnHapusSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusSemua.setText("Hapus Semuanya");
        MnHapusSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusSemua.setIconTextGap(5);
        MnHapusSemua.setName("MnHapusSemua"); // NOI18N
        MnHapusSemua.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusSemuaActionPerformed(evt);
            }
        });
        Popup.add(MnHapusSemua);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Ponek (Obgyn) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Data Pasien ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPonek.setAutoCreateRowSorter(true);
        tbPonek.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbPonek.setName("tbPonek"); // NOI18N
        tbPonek.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbPonekAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tbPonek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPonekMouseClicked(evt);
            }
        });
        tbPonek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPonekKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPonek);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan / Ganti");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(135, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        BtnSimpan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan2KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan2);

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

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 270));
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

        FormInput.setForeground(new java.awt.Color(0, 0, 0));
        FormInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(400, 210));
        FormInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FormInputMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pasien : ");
        jLabel4.setName("jLabel4"); // NOI18N
        panelisi1.add(jLabel4);
        jLabel4.setBounds(0, 10, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Umur : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelisi1.add(jLabel9);
        jLabel9.setBounds(587, 40, 45, 23);

        alamat.setEditable(false);
        alamat.setForeground(new java.awt.Color(0, 0, 0));
        alamat.setHighlighter(null);
        alamat.setName("alamat"); // NOI18N
        alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alamatKeyPressed(evt);
            }
        });
        panelisi1.add(alamat);
        alamat.setBounds(102, 40, 480, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jenis Alamat : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi1.add(jLabel11);
        jLabel11.setBounds(0, 70, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Hamil Ke : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelisi1.add(jLabel12);
        jLabel12.setBounds(225, 70, 60, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tgl. Input : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi1.add(jLabel13);
        jLabel13.setBounds(0, 130, 100, 23);

        cmbAlamat.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlamat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dalam Wilayah", "Luar Wilayah" }));
        cmbAlamat.setName("cmbAlamat"); // NOI18N
        cmbAlamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbAlamatMouseClicked(evt);
            }
        });
        cmbAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlamatActionPerformed(evt);
            }
        });
        cmbAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbAlamatKeyPressed(evt);
            }
        });
        panelisi1.add(cmbAlamat);
        cmbAlamat.setBounds(102, 70, 120, 23);

        cmbHamil.setForeground(new java.awt.Color(0, 0, 0));
        cmbHamil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1", "2-4", ">=5" }));
        cmbHamil.setName("cmbHamil"); // NOI18N
        cmbHamil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbHamilMouseClicked(evt);
            }
        });
        cmbHamil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHamilActionPerformed(evt);
            }
        });
        cmbHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbHamilKeyPressed(evt);
            }
        });
        panelisi1.add(cmbHamil);
        cmbHamil.setBounds(285, 70, 70, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setHighlighter(null);
        norm.setName("norm"); // NOI18N
        norm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                normKeyPressed(evt);
            }
        });
        panelisi1.add(norm);
        norm.setBounds(235, 10, 70, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setHighlighter(null);
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpasienKeyPressed(evt);
            }
        });
        panelisi1.add(nmpasien);
        nmpasien.setBounds(308, 10, 390, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Umur Kehamilan : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi1.add(jLabel14);
        jLabel14.setBounds(357, 70, 90, 23);

        cmbUmurHamil.setForeground(new java.awt.Color(0, 0, 0));
        cmbUmurHamil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "<37", "38-41", ">=42" }));
        cmbUmurHamil.setName("cmbUmurHamil"); // NOI18N
        cmbUmurHamil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbUmurHamilMouseClicked(evt);
            }
        });
        cmbUmurHamil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUmurHamilActionPerformed(evt);
            }
        });
        cmbUmurHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbUmurHamilKeyPressed(evt);
            }
        });
        panelisi1.add(cmbUmurHamil);
        cmbUmurHamil.setBounds(448, 70, 65, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tindakan Lain : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi1.add(jLabel15);
        jLabel15.setBounds(235, 100, 80, 23);

        cmbTindakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Manual Plac", "Curretase", "Tidak Ada" }));
        cmbTindakan.setName("cmbTindakan"); // NOI18N
        cmbTindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbTindakanMouseClicked(evt);
            }
        });
        cmbTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTindakanActionPerformed(evt);
            }
        });
        cmbTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTindakanKeyPressed(evt);
            }
        });
        panelisi1.add(cmbTindakan);
        cmbTindakan.setBounds(318, 100, 100, 23);

        cmbPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPersalinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Spt Kepala", "Spt Bokong", "Spt dengan Induksi", "VE", "SC Cito", "SC Elektif", "Tidak Ada" }));
        cmbPersalinan.setName("cmbPersalinan"); // NOI18N
        cmbPersalinan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPersalinanMouseClicked(evt);
            }
        });
        cmbPersalinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPersalinanActionPerformed(evt);
            }
        });
        cmbPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbPersalinanKeyPressed(evt);
            }
        });
        panelisi1.add(cmbPersalinan);
        cmbPersalinan.setBounds(102, 100, 130, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Alamat : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi1.add(jLabel10);
        jLabel10.setBounds(0, 40, 100, 23);

        TUmur.setEditable(false);
        TUmur.setForeground(new java.awt.Color(0, 0, 0));
        TUmur.setHighlighter(null);
        TUmur.setName("TUmur"); // NOI18N
        TUmur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurKeyPressed(evt);
            }
        });
        panelisi1.add(TUmur);
        TUmur.setBounds(636, 40, 60, 23);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setHighlighter(null);
        norawat.setName("norawat"); // NOI18N
        norawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatKeyPressed(evt);
            }
        });
        panelisi1.add(norawat);
        norawat.setBounds(102, 10, 130, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Cara Persalinan : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelisi1.add(jLabel16);
        jLabel16.setBounds(0, 100, 100, 23);

        tglInput.setDisplayFormat("dd-MM-yyyy");
        tglInput.setName("tglInput"); // NOI18N
        tglInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglInputKeyPressed(evt);
            }
        });
        panelisi1.add(tglInput);
        tglInput.setBounds(102, 130, 95, 23);

        internalFrame2.add(panelisi1, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Pasien Ponek", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setLayout(null);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbInputDiagnosa.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbInputDiagnosa.setName("tbInputDiagnosa"); // NOI18N
        tbInputDiagnosa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInputDiagnosaMouseClicked(evt);
            }
        });
        tbInputDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbInputDiagnosaKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbInputDiagnosa);

        panelisi2.add(Scroll2);
        Scroll2.setBounds(0, 0, 800, 180);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setText("Tampilkan Diagnosa");
        BtnCari2.setToolTipText("Alt+2");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelisi2.add(BtnCari2);
        BtnCari2.setBounds(290, 188, 160, 25);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi2.add(label9);
        label9.setBounds(0, 188, 68, 23);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setToolTipText("Alt+C");
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(285, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi2.add(TCari1);
        TCari1.setBounds(75, 188, 210, 23);

        internalFrame3.add(panelisi2, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Input Diagnosa", internalFrame3);

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setLayout(null);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbDiagnosaBaru.setAutoCreateRowSorter(true);
        tbDiagnosaBaru.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbDiagnosaBaru.setName("tbDiagnosaBaru"); // NOI18N
        tbDiagnosaBaru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaBaruMouseClicked(evt);
            }
        });
        tbDiagnosaBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaBaruKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbDiagnosaBaru);

        panelisi3.add(Scroll1);
        Scroll1.setBounds(0, 0, 530, 150);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Kode : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelisi3.add(jLabel5);
        jLabel5.setBounds(0, 157, 50, 23);

        kddiag.setForeground(new java.awt.Color(0, 0, 0));
        kddiag.setHighlighter(null);
        kddiag.setName("kddiag"); // NOI18N
        kddiag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddiagKeyPressed(evt);
            }
        });
        panelisi3.add(kddiag);
        kddiag.setBounds(52, 157, 70, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Kode ICD 10 : ");
        jLabel8.setName("jLabel8"); // NOI18N
        panelisi3.add(jLabel8);
        jLabel8.setBounds(140, 185, 75, 23);

        nmdiag.setForeground(new java.awt.Color(0, 0, 0));
        nmdiag.setHighlighter(null);
        nmdiag.setName("nmdiag"); // NOI18N
        nmdiag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmdiagKeyPressed(evt);
            }
        });
        panelisi3.add(nmdiag);
        nmdiag.setBounds(217, 157, 230, 23);

        BtnTambah.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setMnemonic('3');
        BtnTambah.setToolTipText("Tambah Data");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi3.add(BtnTambah);
        BtnTambah.setBounds(365, 185, 28, 23);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setToolTipText("Simpan");
        BtnSimpan1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelisi3.add(BtnSimpan1);
        BtnSimpan1.setBounds(400, 185, 28, 23);

        BtnGantiDiagBaru.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiDiagBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnGantiDiagBaru.setMnemonic('H');
        BtnGantiDiagBaru.setToolTipText("Hapus");
        BtnGantiDiagBaru.setName("BtnGantiDiagBaru"); // NOI18N
        BtnGantiDiagBaru.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnGantiDiagBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiDiagBaruActionPerformed1(evt);
            }
        });
        BtnGantiDiagBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiDiagBaruKeyPressed(evt);
            }
        });
        panelisi3.add(BtnGantiDiagBaru);
        BtnGantiDiagBaru.setBounds(435, 185, 28, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setToolTipText("Tampilkan Data");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelisi3.add(BtnCari1);
        BtnCari1.setBounds(330, 185, 28, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Diagnosa : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi3.add(jLabel17);
        jLabel17.setBounds(140, 157, 75, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStatusMouseClicked(evt);
            }
        });
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
        panelisi3.add(cmbStatus);
        cmbStatus.setBounds(52, 185, 80, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Status : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelisi3.add(jLabel18);
        jLabel18.setBounds(0, 185, 50, 23);

        kdicd.setForeground(new java.awt.Color(0, 0, 0));
        kdicd.setHighlighter(null);
        kdicd.setName("kdicd"); // NOI18N
        kdicd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdicdKeyPressed(evt);
            }
        });
        panelisi3.add(kdicd);
        kdicd.setBounds(217, 185, 100, 23);

        internalFrame4.add(panelisi3, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Diagnosa Baru", internalFrame4);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);
        FormInput.getAccessibleContext().setAccessibleName(".: Ponek");

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setPreferredSize(new java.awt.Dimension(360, 404));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Diagnosa Pasien ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll3.setComponentPopupMenu(Popup);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbDiagPonek.setAutoCreateRowSorter(true);
        tbDiagPonek.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbDiagPonek.setComponentPopupMenu(Popup);
        tbDiagPonek.setName("tbDiagPonek"); // NOI18N
        tbDiagPonek.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbDiagPonekAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tbDiagPonek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagPonekMouseClicked(evt);
            }
        });
        tbDiagPonek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagPonekKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbDiagPonek);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame5, java.awt.BorderLayout.LINE_END);
        internalFrame5.getAccessibleContext().setAccessibleName("");
        internalFrame5.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
        tampil();
        tampilDiagPasien();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        emptTeks();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
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
        tampilDiagPasien();
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
        emptTeks();
        tampil();
        tampilDiagPasien();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, norawat);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPonekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPonekMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPonekMouseClicked

    private void tbPonekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPonekKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPonekKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
    cmbAlamat.requestFocus();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();

        if (akses.getkode().equals("Admin Utama")) {
            FormInput.setEnabledAt(2, true);
        } else {
            FormInput.setEnabledAt(2, false);
        }
    }//GEN-LAST:event_formWindowOpened

    private void cmbAlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAlamatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAlamatKeyPressed

    private void cmbAlamatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbAlamatMouseClicked
        cmbAlamat.setEditable(false);
    }//GEN-LAST:event_cmbAlamatMouseClicked

    private void cmbHamilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbHamilMouseClicked
        cmbHamil.setEditable(false);
    }//GEN-LAST:event_cmbHamilMouseClicked

    private void cmbHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbHamilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHamilKeyPressed

    private void cmbHamilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHamilActionPerformed

    }//GEN-LAST:event_cmbHamilActionPerformed

    private void alamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alamatKeyPressed
        Valid.pindah(evt, norawat, cmbAlamat);
    }//GEN-LAST:event_alamatKeyPressed

    private void cmbAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlamatActionPerformed

    }//GEN-LAST:event_cmbAlamatActionPerformed

    private void normKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_normKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_normKeyPressed

    private void nmpasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpasienKeyPressed

    private void cmbUmurHamilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbUmurHamilMouseClicked
        cmbUmurHamil.setEditable(false);
    }//GEN-LAST:event_cmbUmurHamilMouseClicked

    private void cmbUmurHamilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUmurHamilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUmurHamilActionPerformed

    private void cmbUmurHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbUmurHamilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUmurHamilKeyPressed

    private void cmbTindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbTindakanMouseClicked
        cmbTindakan.setEditable(false);
    }//GEN-LAST:event_cmbTindakanMouseClicked

    private void cmbTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTindakanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTindakanActionPerformed

    private void cmbTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTindakanKeyPressed

    private void cmbPersalinanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPersalinanMouseClicked
        cmbPersalinan.setEditable(false);
    }//GEN-LAST:event_cmbPersalinanMouseClicked

    private void cmbPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPersalinanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPersalinanActionPerformed

    private void cmbPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPersalinanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPersalinanKeyPressed

    private void TUmurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TUmurKeyPressed

    private void norawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatKeyPressed

    private void tglInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglInputKeyPressed
        Valid.pindah(evt, cmbPersalinan, BtnCari);
    }//GEN-LAST:event_tglInputKeyPressed

    private void norwponekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norwponekKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norwponekKeyPressed

    private void tbDiagnosaBaruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaBaruMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataDiagBaru();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDiagnosaBaruMouseClicked

    private void tbDiagnosaBaruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaBaruKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDiagBaru();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagnosaBaruKeyPressed

    private void kddiagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddiagKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kddiagKeyPressed

    private void nmdiagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmdiagKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmdiagKeyPressed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        Valid.autoNomer(tabMode1, "DP", 5, kddiag);
        nmdiag.setText("");
        nmdiag.requestFocus();
        cmbStatus.setSelectedIndex(0);
        kdicd.setText("");
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (kddiag.getText().trim().equals("")) {
            Valid.textKosong(kddiag, "kode diagnosa baru");
        } else if (nmdiag.getText().trim().equals("")) {
            Valid.textKosong(nmdiag, "nama diagnosa baru");
        } else if (kdicd.getText().trim().equals("")) {
            Valid.textKosong(kdicd, "kode ICD 10");
        } else {
            if (cmbStatus.getSelectedItem().equals("Aktif")) {
                sttsDiagBaru.setText("1");
            } else if (cmbStatus.getSelectedItem().equals("Non Aktif")) {
                sttsDiagBaru.setText("0");
            }

            Sequel.menyimpan("master_diagnosa_ponek", "'" + kddiag.getText() + "','" + nmdiag.getText() + "', "
                    + "'" + sttsDiagBaru.getText() + "','" + kdicd.getText() + "'", "Data Diganosa Baru");
            tampilDiagBaru();

            kddiag.setText("");
            nmdiag.setText("");
            cmbStatus.setSelectedIndex(0);
            kdicd.setText("");
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnGantiDiagBaruActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiDiagBaruActionPerformed1
        if (kddiag.getText().trim().equals("")) {
            Valid.textKosong(kddiag, "kode diagnosa baru");
        } else if (nmdiag.getText().trim().equals("")) {
            Valid.textKosong(nmdiag, "nama diagnosa baru");
        } else if (kdicd.getText().trim().equals("")) {
            Valid.textKosong(kdicd, "kode ICD 10");
        } else {
            if (cmbStatus.getSelectedItem().equals("Aktif")) {
                sttsDiagBaru.setText("1");
            } else if (cmbStatus.getSelectedItem().equals("Non Aktif")) {
                sttsDiagBaru.setText("0");
            }

            Sequel.mengedit("master_diagnosa_ponek", "kode_diagnosa='" + kddiag.getText() + "'",
                    "nm_diagnosa='" + nmdiag.getText() + "',status_diagnosa='" + sttsDiagBaru.getText() + "',kode_icd10='" + kdicd.getText() + "' ");
            tampilDiagBaru();

            kddiag.setText("");
            nmdiag.setText("");
            cmbStatus.setSelectedIndex(0);
            kdicd.setText("");
        }
    }//GEN-LAST:event_BtnGantiDiagBaruActionPerformed1

    private void BtnGantiDiagBaruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiDiagBaruKeyPressed

    }//GEN-LAST:event_BtnGantiDiagBaruKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilDiagBaru();

        kddiag.setText("");
        nmdiag.setText("");
        sttsDiagBaru.setText("");
        kdicd.setText("");
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void cmbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStatusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusMouseClicked

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        if (cmbStatus.getSelectedItem().equals("Aktif")) {
            sttsDiagBaru.setText("1");
        } else if (cmbStatus.getSelectedItem().equals("Non Aktif")) {
            sttsDiagBaru.setText("0");
        }
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void cmbStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStatusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusKeyPressed

    private void FormInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormInputMouseClicked
        if (FormInput.getSelectedIndex() == 0) {
            cmbAlamat.requestFocus();
        } else if (FormInput.getSelectedIndex() == 1) {
            TCari1.setText("");
            kddiagnosainput.setText("");
            tampilDiagInput();
        } else if (FormInput.getSelectedIndex() == 2) {
            tampilDiagBaru();

            kddiag.setText("");
            nmdiag.setText("");
            cmbStatus.setSelectedIndex(0);
            kdicd.setText("");

            if (cmbStatus.getSelectedItem().equals("Aktif")) {
                sttsDiagBaru.setText("1");
            } else if (cmbStatus.getSelectedItem().equals("Non Aktif")) {
                sttsDiagBaru.setText("0");
            }
        }
    }//GEN-LAST:event_FormInputMouseClicked

    private void sttsDiagBaruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sttsDiagBaruKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sttsDiagBaruKeyPressed

    private void tbPonekAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbPonekAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPonekAncestorAdded

    private void kdicdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdicdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdicdKeyPressed

    private void tbInputDiagnosaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInputDiagnosaMouseClicked

    }//GEN-LAST:event_tbInputDiagnosaMouseClicked

    private void tbInputDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbInputDiagnosaKeyPressed

    }//GEN-LAST:event_tbInputDiagnosaKeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilDiagInput();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnCari2);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbInputDiagnosa.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void kddiagnosainputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddiagnosainputKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kddiagnosainputKeyPressed

    private void tbDiagPonekAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbDiagPonekAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagPonekAncestorAdded

    private void tbDiagPonekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagPonekMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataDiagPasien();
            } catch (java.lang.NullPointerException e) {
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagPonekMouseClicked

    private void tbDiagPonekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagPonekKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDiagPasien();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagPonekKeyPressed

    private void MnHapusSalahSatuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusSalahSatuActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            tbPonek.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu salah satu pasien kemudian klik...!!!!");
        } else if (cekKodeDiag.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Diagnosa yang akan dihapus belum dipilih...!!!!");
        } else if (!norawat.getText().trim().equals("") && (!cekKodeDiag.getText().equals(""))) {
            Sequel.queryu("delete from detail_diagnosa_ponek where no_rawat='" + norawat.getText() + "' and kode_diagnosa='" + cekKodeDiag.getText() + "'");
            tampilDiagPasien();
            emptTeks();
        }
    }//GEN-LAST:event_MnHapusSalahSatuActionPerformed

    private void cekDataponekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekDataponekKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekDataponekKeyPressed

    private void cekDiagponekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekDiagponekKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekDiagponekKeyPressed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (norawat.getText().equals("")) {
            Valid.textKosong(norm, "pasien");
        } else if (cmbAlamat.getSelectedItem().equals(" ")) {
            Valid.textKosong(cmbAlamat, "jenis alamat");
        } else if (cmbHamil.getSelectedItem().equals(" ")) {
            Valid.textKosong(cmbHamil, "hamil ke");
        } else if (cmbUmurHamil.getSelectedItem().equals(" ")) {
            Valid.textKosong(cmbUmurHamil, "umur kehamilan pasien");
        } else if (cmbTindakan.getSelectedItem().equals(" ")) {
            Valid.textKosong(cmbTindakan, "tindakan lain");
        } else if (cmbPersalinan.getSelectedItem().equals(" ")) {
            Valid.textKosong(cmbPersalinan, "cara persalinan");
        } else {
            try {
                j = 0;
                for (i = 0; i < tbInputDiagnosa.getRowCount(); i++) {
                    if (tbInputDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }
                
                if (j == 0) {
                    JOptionPane.showMessageDialog(null, "Pilih diagnosa poneknya terlebih dulu,.!!!");
                    FormInput.setSelectedIndex(1);
                    tampilDiagInput();
                } else {
                    for (i = 0; i < tbInputDiagnosa.getRowCount(); i++) {
                        if (tbInputDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            kddiagnosainput.setText(tbInputDiagnosa.getValueAt(i, 1).toString());
                            Sequel.menyimpan("detail_diagnosa_ponek", "'" + norawat.getText() + "','" + norm.getText() + "', "
                                    + "'" + kddiagnosainput.getText() + "'", "Diganosa Ponek");
                        }
                    }

                    tampilDiagInput();
                    Sequel.cariIsi("select no_rawat from data_ponek where no_rawat=? ", cekDataponek, norawat.getText());

                    if (cekDataponek.getText().equals("")) {
                        Sequel.menyimpan("data_ponek", "'" + norawat.getText() + "','" + norm.getText() + "','" + cmbAlamat.getSelectedItem() + "',"
                                + "'" + cmbHamil.getSelectedItem() + "','" + cmbUmurHamil.getSelectedItem() + "','" + cmbPersalinan.getSelectedItem() + "',"
                                + "'" + cmbTindakan.getSelectedItem() + "','" + Valid.SetTgl(tglInput.getSelectedItem() + "") + "'", "Data Ponek");
                        
                    } else if (!cekDataponek.getText().equals("")) {
                        Sequel.mengedit("data_ponek", "no_rawat='" + norawat.getText() + "'",
                                "jenis_alamat='" + cmbAlamat.getSelectedItem() + "', "
                                + "hamil_ke='" + cmbHamil.getSelectedItem() + "', "
                                + "umur_kehamilan='" + cmbUmurHamil.getSelectedItem() + "', "
                                + "cara_persalinan='" + cmbPersalinan.getSelectedItem() + "', "
                                + "tindakan_lain='" + cmbTindakan.getSelectedItem() + "', "
                                + "tgl_input='" + Valid.SetTgl(tglInput.getSelectedItem() + "") + "' ");
                    }

                    FormInput.setSelectedIndex(0);
                    emptTeks();
                    tampil();
                    tampilDiagPasien();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan");
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan2KeyPressed

    private void cekKodeDiagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekKodeDiagKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekKodeDiagKeyPressed

    private void MnHapusSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusSemuaActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            tbPonek.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu salah satu pasien kemudian klik...!!!!");
        } else if (!norawat.getText().trim().equals("")) {
            Sequel.queryu("delete from detail_diagnosa_ponek where no_rawat='" + norawat.getText() + "'");
            tampilDiagPasien();
            emptTeks();
        }
    }//GEN-LAST:event_MnHapusSemuaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgInputPonek dialog = new DlgInputPonek(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari2;
    private widget.Button BtnGantiDiagBaru;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan1;
    public widget.Button BtnSimpan2;
    private widget.Button BtnTambah;
    public widget.CekBox ChkInput;
    public widget.TabPane FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHapusSalahSatu;
    private javax.swing.JMenuItem MnHapusSemua;
    public javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TUmur;
    private widget.TextBox alamat;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.TextBox cekDataponek;
    private widget.TextBox cekDiagponek;
    private widget.TextBox cekKodeDiag;
    public widget.ComboBox cmbAlamat;
    private widget.ComboBox cmbHamil;
    private widget.ComboBox cmbPersalinan;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbTindakan;
    private widget.ComboBox cmbUmurHamil;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kddiag;
    private widget.TextBox kddiagnosainput;
    private widget.TextBox kdicd;
    private widget.Label label9;
    private widget.TextBox nmdiag;
    private widget.TextBox nmpasien;
    private widget.TextBox norawat;
    private widget.TextBox norm;
    private widget.TextBox norwponek;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.TextBox sttsDiagBaru;
    private widget.Table tbDiagPonek;
    private widget.Table tbDiagnosaBaru;
    private widget.Table tbInputDiagnosa;
    private widget.Table tbPonek;
    private widget.Tanggal tglInput;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT dp.no_rawat, p.no_rkm_medis, CONCAT(p.nm_pasien,' (',rp.umurdaftar,' ',rp.sttsumur,')') pasien, "
                    + "concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,',',kb.nm_kab) alamat, dp.jenis_alamat,  "
                    + "dp.tgl_input, dp.hamil_ke, dp.umur_kehamilan, dp.cara_persalinan, dp.tindakan_lain "
                    + "FROM data_ponek dp INNER JOIN reg_periksa rp ON rp.no_rawat = dp.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = dp.no_rkm_medis INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where "
                    + "dp.no_rawat like ? or "
                    + "p.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or "
                    + "rp.umurdaftar like ? or "
                    + "dp.tgl_input like ? or "
                    + "dp.jenis_alamat like ? or "
                    + "dp.hamil_ke like ? or "
                    + "dp.umur_kehamilan like ? or "
                    + "dp.cara_persalinan like ? or "
                    + "dp.tindakan_lain like ? or "
                    + "p.alamat like ? or "
                    + "kl.nm_kel like ? or "
                    + "kc.nm_kec like ? or "
                    + "kb.nm_kab like ? "
                    + "ORDER BY dp.no_rawat desc, dp.tgl_input desc limit 100 ");

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

                rs = ps.executeQuery();
                g = 1;
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        g + ".",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                    });
                    g++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgInputPonek.tampil() : " + e);
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
        norawat.setText("");
        norm.setText("");
        nmpasien.setText("");
        alamat.setText("");
        TUmur.setText("");
        norwponek.setText("");
        kddiagnosainput.setText("");
        cekDataponek.setText("");
        cekDiagponek.setText("");
        cekKodeDiag.setText("");
        tglInput.setDate(new Date());
        cmbAlamat.setSelectedIndex(0);
        cmbHamil.setSelectedIndex(0);
        cmbUmurHamil.setSelectedIndex(0);
        cmbTindakan.setSelectedIndex(0);
        cmbPersalinan.setSelectedIndex(0);
    }

    private void getData() {
        if (tbPonek.getSelectedRow() != -1) {
            norawat.setText(tbPonek.getValueAt(tbPonek.getSelectedRow(), 1).toString());
            norm.setText(tbPonek.getValueAt(tbPonek.getSelectedRow(), 2).toString());
            nmpasien.setText(tbPonek.getValueAt(tbPonek.getSelectedRow(), 3).toString());
            alamat.setText(tbPonek.getValueAt(tbPonek.getSelectedRow(), 4).toString());

            cmbAlamat.setSelectedItem(tbPonek.getValueAt(tbPonek.getSelectedRow(), 5).toString());
            Valid.SetTgl(tglInput, tbPonek.getValueAt(tbPonek.getSelectedRow(), 6).toString());
            cmbHamil.setSelectedItem(tbPonek.getValueAt(tbPonek.getSelectedRow(), 7).toString());
            cmbUmurHamil.setSelectedItem(tbPonek.getValueAt(tbPonek.getSelectedRow(), 8).toString());
            cmbTindakan.setSelectedItem(tbPonek.getValueAt(tbPonek.getSelectedRow(), 10).toString());
            cmbPersalinan.setSelectedItem(tbPonek.getValueAt(tbPonek.getSelectedRow(), 9).toString());
            Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) umur from reg_periksa where no_rawat=? ", TUmur, norawat.getText());

            tampilDiagPasien();
        }
    }

    private void getDataDiagBaru() {
        if (tbDiagnosaBaru.getSelectedRow() != -1) {
            kddiag.setText(tbDiagnosaBaru.getValueAt(tbDiagnosaBaru.getSelectedRow(), 1).toString());
            nmdiag.setText(tbDiagnosaBaru.getValueAt(tbDiagnosaBaru.getSelectedRow(), 2).toString());
            cmbStatus.setSelectedItem(tbDiagnosaBaru.getValueAt(tbDiagnosaBaru.getSelectedRow(), 3).toString());
            kdicd.setText(tbDiagnosaBaru.getValueAt(tbDiagnosaBaru.getSelectedRow(), 4).toString());

            if (cmbStatus.getSelectedItem().equals("Aktif")) {
                sttsDiagBaru.setText("1");
            } else if (cmbStatus.getSelectedItem().equals("Non Aktif")) {
                sttsDiagBaru.setText("0");
            }
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 270));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void pasien(String norw, String medrek, String nama, String alamatnya) {
        norawat.setText(norw);
        norm.setText(medrek);
        nmpasien.setText(nama);
        alamat.setText(alamatnya);
        Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) umur from reg_periksa where no_rawat=? ", TUmur, norawat.getText());

        Sequel.cariIsi("select no_rawat from data_ponek where no_rawat=? ", cekDataponek, norawat.getText());

        if (cekDataponek.getText().equals("")) {
            cmbAlamat.setSelectedIndex(0);
            cmbHamil.setSelectedIndex(0);
            cmbUmurHamil.setSelectedIndex(0);
            cmbTindakan.setSelectedIndex(0);
            cmbPersalinan.setSelectedIndex(0);
            norwponek.setText("");
            tglInput.setDate(new Date());

        } else if (!cekDataponek.getText().equals("")) {
            cmbAlamat.setSelectedItem(Sequel.cariIsi("select jenis_alamat from data_ponek where no_rawat='" + norawat.getText() + "'"));
            Valid.SetTgl(tglInput, Sequel.cariIsi("select tgl_input from data_ponek where no_rawat='" + norawat.getText() + "'"));
            cmbHamil.setSelectedItem(Sequel.cariIsi("select hamil_ke from data_ponek where no_rawat='" + norawat.getText() + "'"));
            cmbUmurHamil.setSelectedItem(Sequel.cariIsi("select umur_kehamilan from data_ponek where no_rawat='" + norawat.getText() + "'"));
            cmbTindakan.setSelectedItem(Sequel.cariIsi("select tindakan_lain from data_ponek where no_rawat='" + norawat.getText() + "'"));
            cmbPersalinan.setSelectedItem(Sequel.cariIsi("select cara_persalinan from data_ponek where no_rawat='" + norawat.getText() + "'"));
            norwponek.setText("");
        }

    }

    private void tampilDiagBaru() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT kode_diagnosa, nm_diagnosa, "
                    + "if(status_diagnosa='1','Aktif','Non Aktif') status, kode_icd10 FROM master_diagnosa_ponek ORDER BY kode_diagnosa ");

            try {
                rs1 = ps1.executeQuery();
                a = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        a + ".",
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4)
                    });
                    a++;
                }
                this.setCursor(Cursor.getDefaultCursor());
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
//        LCount.setText(""+tabMode.getRowCount());
    }

    private void tampilDiagInput() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT m.kode_diagnosa, m.nm_diagnosa, m.kode_icd10, p.nm_penyakit "
                    + "FROM master_diagnosa_ponek m INNER JOIN penyakit p on p.kd_penyakit=m.kode_icd10 where "
                    + "m.kode_diagnosa like ? or "
                    + "m.nm_diagnosa like ? or "
                    + "m.kode_icd10 like ? or "
                    + "p.nm_penyakit like ? ORDER BY m.kode_diagnosa ");

            try {
                ps2.setString(1, "%" + TCari1.getText().trim() + "%");
                ps2.setString(2, "%" + TCari1.getText().trim() + "%");
                ps2.setString(3, "%" + TCari1.getText().trim() + "%");
                ps2.setString(4, "%" + TCari1.getText().trim() + "%");

                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{false,
                        rs2.getString(1),
                        rs2.getString(2),
                        rs2.getString(3),
                        rs2.getString(4)
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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

    private void tampilDiagPasien() {
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("SELECT m.nm_diagnosa, m.kode_icd10, p.nm_penyakit, d.kode_diagnosa FROM detail_diagnosa_ponek d "
                    + "INNER JOIN data_ponek dp on dp.no_rawat=d.no_rawat "
                    + "INNER JOIN master_diagnosa_ponek m on m.kode_diagnosa=d.kode_diagnosa "
                    + "INNER JOIN penyakit p on p.kd_penyakit=m.kode_icd10 where "
                    + "d.no_rawat='" + norawat.getText() + "' order by m.kode_diagnosa ");

            try {
                rs3 = ps3.executeQuery();
                b = 1;
                while (rs3.next()) {
                    tabMode3.addRow(new Object[]{
                        b + ".",
                        rs3.getString(1),
                        rs3.getString(2),
                        rs3.getString(3),
                        rs3.getString(4)
                    });
                    b++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
//        LCount.setText(""+tabMode.getRowCount());
    }
  
    private void getDataDiagPasien() {
        if (tbDiagPonek.getSelectedRow() != -1) {
            cekKodeDiag.setText(tbDiagPonek.getValueAt(tbDiagPonek.getSelectedRow(), 4).toString());
        }
    }
}
