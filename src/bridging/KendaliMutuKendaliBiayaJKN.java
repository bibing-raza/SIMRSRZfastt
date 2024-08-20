package bridging;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPoli;

public class KendaliMutuKendaliBiayaJKN extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private final Properties prop = new Properties();
    private int i = 0;
    private Date tgl = new Date();
    private String unitnya = "", dialog_simpan = "";
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public KendaliMutuKendaliBiayaJKN(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. SEP", "No. RM", "Nama Pasien", "Poliklinik/Inst.", "Nama Dokter", "Biaya Real Obat", "Status Klaim", "Deskripsi CBG",
            "Tarif CBG", "Deskripsi TopUp", "TopUp Tarif", "Tot. Trf. Grouping", "Pemakaian Obat (%)", "stts_lanjut"
        }) {
              @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbKendaliKlaimRalan.setModel(tabMode);
        tbKendaliKlaimRalan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKendaliKlaimRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tbKendaliKlaimRalan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(160);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(300);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(115);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(115);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbKendaliKlaimRalan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "No. SEP", "No. RM", "Nama Pasien", "Rg. Perawatan Inap", "Nama DPJP", "Biaya Real Obat", "Status Klaim", "Deskripsi CBG",
            "Tarif CBG", "Deskripsi TopUp", "TopUp Tarif", "Tot. Trf. Grouping", "Pemakaian Obat (%)", "stts_lanjut"
        }) {
              @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbKendaliKlaimRanap.setModel(tabMode1);
        tbKendaliKlaimRanap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKendaliKlaimRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tbKendaliKlaimRanap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(160);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(300);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(115);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(115);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbKendaliKlaimRanap.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));    
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilRalan();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilRalan();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilRalan();}
            });
        }
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("KendaliMutuKendaliBiayaJKN")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        btnDokter.requestFocus();
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("KendaliMutuKendaliBiayaJKN")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        poli.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("KendaliMutuKendaliBiayaJKN")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        BtnCari.requestFocus();
                    }
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {dokter.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        dokter.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("KendaliMutuKendaliBiayaJKN")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        dokter.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnExportkeExcelRalan = new javax.swing.JMenuItem();
        MnExportkeExcelRekapRalan = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnExportkeExcelRanap = new javax.swing.JMenuItem();
        MnExportkeExcelRekapRanap = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        label11 = new widget.Label();
        TglSEP1 = new widget.Tanggal();
        label13 = new widget.Label();
        kdDokter = new widget.TextBox();
        TDokter = new widget.TextBox();
        btnDokter = new widget.Button();
        label12 = new widget.Label();
        TglSEP2 = new widget.Tanggal();
        label14 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        btnPoli = new widget.Button();
        jLabel13 = new widget.Label();
        jnsRawat = new widget.ComboBox();
        jLabel14 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbKendaliKlaimRalan = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKendaliKlaimRanap = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnExportkeExcelRalan.setBackground(new java.awt.Color(242, 242, 242));
        MnExportkeExcelRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportkeExcelRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportkeExcelRalan.setText("Export Ke Ms. Excel Data Ralan");
        MnExportkeExcelRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportkeExcelRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportkeExcelRalan.setIconTextGap(5);
        MnExportkeExcelRalan.setName("MnExportkeExcelRalan"); // NOI18N
        MnExportkeExcelRalan.setPreferredSize(new java.awt.Dimension(300, 26));
        MnExportkeExcelRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportkeExcelRalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnExportkeExcelRalan);

        MnExportkeExcelRekapRalan.setBackground(new java.awt.Color(242, 242, 242));
        MnExportkeExcelRekapRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportkeExcelRekapRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportkeExcelRekapRalan.setText("Export Ke Ms. Excel Rekap Persentase Data Ralan");
        MnExportkeExcelRekapRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportkeExcelRekapRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportkeExcelRekapRalan.setIconTextGap(5);
        MnExportkeExcelRekapRalan.setName("MnExportkeExcelRekapRalan"); // NOI18N
        MnExportkeExcelRekapRalan.setPreferredSize(new java.awt.Dimension(300, 26));
        MnExportkeExcelRekapRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportkeExcelRekapRalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnExportkeExcelRekapRalan);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnExportkeExcelRanap.setBackground(new java.awt.Color(242, 242, 242));
        MnExportkeExcelRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportkeExcelRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportkeExcelRanap.setText("Export Ke Ms. Excel Data Ranap");
        MnExportkeExcelRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportkeExcelRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportkeExcelRanap.setIconTextGap(5);
        MnExportkeExcelRanap.setName("MnExportkeExcelRanap"); // NOI18N
        MnExportkeExcelRanap.setPreferredSize(new java.awt.Dimension(300, 26));
        MnExportkeExcelRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportkeExcelRanapActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnExportkeExcelRanap);

        MnExportkeExcelRekapRanap.setBackground(new java.awt.Color(242, 242, 242));
        MnExportkeExcelRekapRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportkeExcelRekapRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportkeExcelRekapRanap.setText("Export Ke Ms. Excel Rekap Persentase Data Ranap");
        MnExportkeExcelRekapRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportkeExcelRekapRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportkeExcelRekapRanap.setIconTextGap(5);
        MnExportkeExcelRekapRanap.setName("MnExportkeExcelRekapRanap"); // NOI18N
        MnExportkeExcelRekapRanap.setPreferredSize(new java.awt.Dimension(300, 26));
        MnExportkeExcelRekapRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportkeExcelRekapRanapActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnExportkeExcelRekapRanap);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Kendali Mutu Kendali Biaya Klaim INACBG ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 103));
        panelisi3.setLayout(null);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. SEP : ");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label11);
        label11.setBounds(0, 10, 90, 23);

        TglSEP1.setEditable(false);
        TglSEP1.setDisplayFormat("dd-MM-yyyy");
        TglSEP1.setName("TglSEP1"); // NOI18N
        panelisi3.add(TglSEP1);
        TglSEP1.setBounds(93, 10, 90, 23);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setText("Nama DPJP : ");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label13);
        label13.setBounds(351, 70, 99, 23);

        kdDokter.setEditable(false);
        kdDokter.setForeground(new java.awt.Color(0, 0, 0));
        kdDokter.setName("kdDokter"); // NOI18N
        kdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi3.add(kdDokter);
        kdDokter.setBounds(453, 70, 75, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        TDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(TDokter);
        TDokter.setBounds(530, 70, 270, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('2');
        btnDokter.setToolTipText("Alt+2");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        panelisi3.add(btnDokter);
        btnDokter.setBounds(800, 70, 28, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label12.setText("s.d.");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label12);
        label12.setBounds(183, 10, 27, 23);

        TglSEP2.setEditable(false);
        TglSEP2.setDisplayFormat("dd-MM-yyyy");
        TglSEP2.setName("TglSEP2"); // NOI18N
        panelisi3.add(TglSEP2);
        TglSEP2.setBounds(210, 10, 90, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Poliklinik/Inst. : ");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label14);
        label14.setBounds(351, 10, 99, 23);

        KdPoli.setEditable(false);
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setName("KdPoli"); // NOI18N
        KdPoli.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi3.add(KdPoli);
        KdPoli.setBounds(453, 10, 75, 23);

        NmPoli.setEditable(false);
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setName("NmPoli"); // NOI18N
        NmPoli.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(NmPoli);
        NmPoli.setBounds(530, 10, 270, 23);

        btnPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli.setMnemonic('2');
        btnPoli.setToolTipText("Alt+2");
        btnPoli.setName("btnPoli"); // NOI18N
        btnPoli.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoliActionPerformed(evt);
            }
        });
        panelisi3.add(btnPoli);
        btnPoli.setBounds(800, 10, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jns. Rawat : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi3.add(jLabel13);
        jLabel13.setBounds(0, 40, 90, 23);

        jnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        jnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "INAP", "JALAN" }));
        jnsRawat.setName("jnsRawat"); // NOI18N
        jnsRawat.setOpaque(false);
        jnsRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnsRawatActionPerformed(evt);
            }
        });
        panelisi3.add(jnsRawat);
        jnsRawat.setBounds(93, 40, 90, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Ruangan / Gedung Inap : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi3.add(jLabel14);
        jLabel14.setBounds(300, 40, 150, 23);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setPreferredSize(new java.awt.Dimension(120, 20));
        panelisi3.add(cmbRuangan);
        cmbRuangan.setBounds(453, 40, 180, 23);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(label10);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(290, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi1.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi1.add(LCount);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+5");
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
        panelisi1.add(BtnCari);

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
        panelisi1.add(BtnAll);

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

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setForeground(new java.awt.Color(0, 0, 0));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbKendaliKlaimRalan.setAutoCreateRowSorter(true);
        tbKendaliKlaimRalan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbKendaliKlaimRalan.setToolTipText("");
        tbKendaliKlaimRalan.setComponentPopupMenu(jPopupMenu1);
        tbKendaliKlaimRalan.setName("tbKendaliKlaimRalan"); // NOI18N
        scrollPane1.setViewportView(tbKendaliKlaimRalan);

        internalFrame2.add(scrollPane1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Rawat Jalan", internalFrame2);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKendaliKlaimRanap.setAutoCreateRowSorter(true);
        tbKendaliKlaimRanap.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKendaliKlaimRanap.setComponentPopupMenu(jPopupMenu2);
        tbKendaliKlaimRanap.setName("tbKendaliKlaimRanap"); // NOI18N
        Scroll.setViewportView(tbKendaliKlaimRanap);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Rawat Inap", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
            dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        kdDokter.setText("");
        TDokter.setText("");
        akses.setform("KendaliMutuKendaliBiayaJKN");
        dokter.isCek();
        dokter.setSize(1039,390);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbKendaliKlaimRalan.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            tampilRalan();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampilRanap();
        }
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
        kdDokter.setText("");
        TDokter.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        cmbRuangan.setSelectedIndex(0);
        
        if (jnsRawat.getSelectedIndex() == 1) {
            cmbRuangan.setEnabled(true);
            btnPoli.setEnabled(false);
        } else if (jnsRawat.getSelectedIndex() == 2) {            
            cmbRuangan.setEnabled(false);
            btnPoli.setEnabled(true);
        } else {
            cmbRuangan.setEnabled(false);
            btnPoli.setEnabled(false);
        }
        
        if (TabRawat.getSelectedIndex() == 0) {
            tampilRalan();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampilRanap();
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void btnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoliActionPerformed
        KdPoli.setText("");
        NmPoli.setText("");
        akses.setform("KendaliMutuKendaliBiayaJKN");
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_btnPoliActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'igd' and nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);
        
        tgl.setDate(1);
        TglSEP1.setDate(tgl);
        TglSEP2.setDate(new Date());
        kdDokter.setText("");
        TDokter.setText("");
        TCari.setText("");
        TabRawat.setSelectedIndex(0);        
    }//GEN-LAST:event_formWindowOpened

    private void MnExportkeExcelRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportkeExcelRalanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            unitnya = "";
            Valid.tabelKosong(tabMode);

            if (jnsRawat.getSelectedIndex() == 2) {
                unitnya = "p.nm_poli like '%" + NmPoli.getText() + "%'";
            } else {
                unitnya = "p.nm_poli like '%%'";
            }

            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT enc.no_sep 'No. SEP', enc.no_rm 'No. RM',enc.nm_pasien 'Nama Pasien', p.nm_poli 'Poliklinik/Inst.', "
                    + "d.nm_dokter 'Nama Dokter',s.nm_sps 'Spesialis', ROUND(esc.tarif_obat * 0.8) 'Biaya Real Obat', "
                    + "concat('   ',enc.klaim_final) 'Status Klaim',dp.kd_penyakit'Code ICD',pk.nm_penyakit 'Diagnosa Akhir',eg.cbg_desc 'Deskripsi CBG',eg.cbg_tarif 'Tarif CBG', "
                    + "IFNULL(egsc.desc,'-') 'Deskripsi TopUp', IFNULL(egsc.tarif,0) 'TopUp Tarif', "
                    + "IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif) 'Tot. Trf. Grouping', "
                    + "CONCAT('   ',FORMAT((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') 'Pemakaian Obat (%)' FROM eklaim_new_claim enc "
                    + "INNER JOIN eklaim_set_claim esc ON esc.no_sep = enc.no_sep "
                    + "INNER JOIN eklaim_grouping eg ON eg.no_sep = enc.no_sep "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = enc.no_rawat "
                    + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli "
                    + "INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter "
                    + "inner join spesialis s on s.kd_sps = d.kd_sps "
                    + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep = enc.no_sep "
                    + "LEFT join diagnosa_pasien dp on dp.no_rawat = enc.no_rawat and dp.prioritas = '1' "
                    + "LEFT join penyakit pk on pk.kd_penyakit = dp.kd_penyakit WHERE "
                    + "rp.status_lanjut='ralan' and " + unitnya + " and d.nm_dokter like '%" + TDokter.getText() + "%' and "
                    + "enc.tglsep BETWEEN '" + Valid.SetTgl(TglSEP1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(TglSEP2.getSelectedItem() + "") + "' "
                    + "ORDER BY enc.tglsep", dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data telah berhasil diexport menjadi file excel,..!!!");
            BtnCariActionPerformed(null);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportkeExcelRalanActionPerformed

    private void MnExportkeExcelRekapRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportkeExcelRekapRalanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT pl.nm_poli 'Poliklinik/Inst.', ROUND(sum(esc.tarif_obat * 0.8)) 'Total Biaya Real Obat', "
                    + "sum(IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif)) 'Total Trf. Grouping', "
                    + "CONCAT(format((ROUND(sum(esc.tarif_obat * 0.8))/sum(IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100),2),' ','%') 'Pemakaian Obat (%)' "
                    + "FROM eklaim_new_claim enc "
                    + "INNER JOIN eklaim_set_claim esc ON esc.no_sep = enc.no_sep "
                    + "INNER JOIN eklaim_grouping eg ON eg.no_sep = enc.no_sep "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = enc.no_rawat "
                    + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                    + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep = enc.no_sep "
                    + "WHERE rp.status_lanjut='ralan' and enc.tglsep BETWEEN '" + Valid.SetTgl(TglSEP1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(TglSEP2.getSelectedItem() + "") + "' "
                    + "group by pl.nm_poli ORDER BY pl.nm_poli", dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data telah berhasil diexport menjadi file excel,..!!!");
            BtnCariActionPerformed(null);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportkeExcelRekapRalanActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampilRalan();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampilRanap();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void jnsRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnsRawatActionPerformed
        KdPoli.setText("");
        NmPoli.setText("");
        cmbRuangan.setSelectedIndex(0);

        if (jnsRawat.getSelectedIndex() == 1) {
            cmbRuangan.setEnabled(true);
            btnPoli.setEnabled(false);
        } else if (jnsRawat.getSelectedIndex() == 2) {
            cmbRuangan.setEnabled(false);
            btnPoli.setEnabled(true);
        } else {
            cmbRuangan.setEnabled(false);
            btnPoli.setEnabled(false);
        }
    }//GEN-LAST:event_jnsRawatActionPerformed

    private void MnExportkeExcelRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportkeExcelRanapActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            unitnya = "";
            Valid.tabelKosong(tabMode1);

            if (jnsRawat.getSelectedIndex() == 1) {
                if ((cmbRuangan.getSelectedItem().equals("-"))) {
                    unitnya = "b.nm_gedung like '%%'";
                } else {
                    unitnya = "b.nm_gedung like '%" + cmbRuangan.getSelectedItem().toString() + "%'";
                }
            } else {
                unitnya = "b.nm_gedung like '%%'";
            }

            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT enc.no_sep 'No. SEP',enc.no_rm 'No. RM', enc.nm_pasien 'Nama Pasien', b.nm_gedung 'Rg. Perawatan Inap', d.nm_dokter 'Nama DPJP',s.nm_sps 'Spesialis', "
                    + "ROUND(esc.tarif_obat * 0.8) 'Biaya Real Obat', concat('   ',enc.klaim_final) 'Status Klaim',dp.kd_penyakit'Code ICD',pk.nm_penyakit 'Diagnosa Akhir',eg.cbg_desc 'Deskripsi CBG',eg.cbg_tarif 'Tarif CBG', "
                    + "IFNULL(egsc.desc,'-') 'Deskripsi TopUp', IFNULL(egsc.tarif,0) 'TopUp Tarif', IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif) 'Tot. Trf. Grouping', "
                    + "CONCAT('   ',FORMAT((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') 'Pemakaian Obat (%)' "
                    + "FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep = enc.no_sep "
                    + "INNER JOIN eklaim_grouping eg ON eg.no_sep = enc.no_sep "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = enc.no_rawat "
                    + "inner join kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "inner join diagnosa_pasien dp on dp.no_rawat = enc.no_rawat and dp.prioritas = '1' "
                    + "inner join penyakit pk on pk.kd_penyakit = dp.kd_penyakit "
                    + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep = enc.no_sep "
                    + "left join dpjp_ranap dr on dr.no_rawat=ki.no_rawat "
                    + "left join dokter d on d.kd_dokter=dr.kd_dokter "
                    + "left join spesialis s on s.kd_sps = d.kd_sps WHERE "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung<>'igd' and b.nm_gedung<>'-' and b.status='1' and " + unitnya + " and rp.status_lanjut='ranap' and "
                    + "d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN '" + Valid.SetTgl(TglSEP1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(TglSEP2.getSelectedItem() + "") + "' "
                    + "ORDER BY enc.tglsep", dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data telah berhasil diexport menjadi file excel,..!!!");
            BtnCariActionPerformed(null);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportkeExcelRanapActionPerformed

    private void MnExportkeExcelRekapRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportkeExcelRekapRanapActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT b.nm_gedung 'Rg. Rawat Inap', ROUND(sum(esc.tarif_obat * 0.8)) 'Total Biaya Real Obat', "
                    + "sum(IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif)) 'Total Trf. Grouping', "
                    + "CONCAT(format((ROUND(sum(esc.tarif_obat * 0.8))/sum(IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100),2),' ','%') 'Pemakaian Obat (%)' "
                    + "FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep = enc.no_sep "
                    + "INNER JOIN eklaim_grouping eg ON eg.no_sep = enc.no_sep "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = enc.no_rawat "
                    + "inner join kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep = enc.no_sep "
                    + "WHERE rp.status_lanjut='ranap' and enc.tglsep BETWEEN '" + Valid.SetTgl(TglSEP1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(TglSEP2.getSelectedItem() + "") + "' "
                    + "group by b.nm_gedung ORDER BY b.nm_gedung", dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data telah berhasil diexport menjadi file excel,..!!!");
            BtnCariActionPerformed(null);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportkeExcelRekapRanapActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            KendaliMutuKendaliBiayaJKN dialog = new KendaliMutuKendaliBiayaJKN(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.TextBox KdPoli;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnExportkeExcelRalan;
    private javax.swing.JMenuItem MnExportkeExcelRanap;
    private javax.swing.JMenuItem MnExportkeExcelRekapRalan;
    private javax.swing.JMenuItem MnExportkeExcelRekapRanap;
    private widget.TextBox NmPoli;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TDokter;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglSEP1;
    private widget.Tanggal TglSEP2;
    private widget.Button btnDokter;
    private widget.Button btnPoli;
    private widget.ComboBox cmbRuangan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.ComboBox jnsRawat;
    private widget.TextBox kdDokter;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbKendaliKlaimRalan;
    private widget.Table tbKendaliKlaimRanap;
    // End of variables declaration//GEN-END:variables

    public void tampilRanap() {
        unitnya = "";        
        Valid.tabelKosong(tabMode1);

        if (jnsRawat.getSelectedIndex() == 1) {            
            if ((cmbRuangan.getSelectedItem().equals("-"))) {
                unitnya = "b.nm_gedung like '%%'";
            } else {
                unitnya = "b.nm_gedung like '%" + cmbRuangan.getSelectedItem().toString() + "%'";
            }
        } else {            
            unitnya = "b.nm_gedung like '%%'";
        }

        try {
            //tarif obat yg dari tabel eklaim_set_claim dikali 0.8 atau 80%
            //0.8 adalah 80% sudah dipotong 20% dari harga tarif obat
            ps1 = koneksi.prepareStatement("SELECT enc.no_sep,enc.no_rm,enc.nm_pasien, b.nm_gedung unit, d.nm_dokter dpjp, "
                    + "ROUND(esc.tarif_obat * 0.8) by_obat_real, concat('   ',enc.klaim_final) klaim_final,eg.cbg_desc,eg.cbg_tarif, "
                    + "IFNULL(egsc.desc,'-') topup_desc, IFNULL(egsc.tarif,0) topup_tarif, IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif) total_trf_grp, "
                    + "CONCAT('   ',FORMAT((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, rp.status_lanjut "
                    + "FROM eklaim_new_claim enc "
                    + "INNER JOIN eklaim_set_claim esc ON esc.no_sep = enc.no_sep "
                    + "INNER JOIN eklaim_grouping eg ON eg.no_sep = enc.no_sep "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = enc.no_rawat "
                    + "inner join kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep = enc.no_sep "
                    + "left join dpjp_ranap dr on dr.no_rawat=ki.no_rawat "
                    + "left join dokter d on d.kd_dokter=dr.kd_dokter WHERE "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung<>'igd' and b.nm_gedung<>'-' and b.status='1' and " + unitnya + " and rp.status_lanjut='ranap' and "
                    + "d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and enc.no_sep like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung<>'igd' and b.nm_gedung<>'-' and b.status='1' and " + unitnya + " and rp.status_lanjut='ranap' and "
                    + "d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and enc.no_rm like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung<>'igd' and b.nm_gedung<>'-' and b.status='1' and " + unitnya + " and rp.status_lanjut='ranap' and "
                    + "d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and enc.nm_pasien like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung<>'igd' and b.nm_gedung<>'-' and b.status='1' and " + unitnya + " and rp.status_lanjut='ranap' and "
                    + "d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and enc.klaim_final like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung<>'igd' and b.nm_gedung<>'-' and b.status='1' and " + unitnya + " and rp.status_lanjut='ranap' and "
                    + "d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and eg.cbg_desc like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung<>'igd' and b.nm_gedung<>'-' and b.status='1' and " + unitnya + " and rp.status_lanjut='ranap' and "
                    + "d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and CONCAT(FORMAT((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') like ? "
                    + "ORDER BY enc.tglsep");
            try {
                ps1.setString(1, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps1.setString(2, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps1.setString(3, "%" + TCari.getText().trim() + "%");
                ps1.setString(4, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps1.setString(5, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps1.setString(6, "%" + TCari.getText().trim() + "%");
                ps1.setString(7, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps1.setString(8, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps1.setString(9, "%" + TCari.getText().trim() + "%");
                ps1.setString(10, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps1.setString(11, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps1.setString(12, "%" + TCari.getText().trim() + "%");
                ps1.setString(13, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps1.setString(14, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps1.setString(15, "%" + TCari.getText().trim() + "%");
                ps1.setString(16, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps1.setString(17, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps1.setString(18, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("no_sep"),
                        rs1.getString("no_rm"),
                        rs1.getString("nm_pasien"),
                        rs1.getString("unit"),
                        rs1.getString("dpjp"),
                        rs1.getDouble("by_obat_real"),
                        rs1.getString("klaim_final"),
                        rs1.getString("cbg_desc"),
                        rs1.getDouble("cbg_tarif"),
                        rs1.getString("topup_desc"),
                        rs1.getDouble("topup_tarif"),
                        rs1.getDouble("total_trf_grp"),
                        rs1.getString("perc_pakai_obat"),
                        rs1.getString("status_lanjut")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("bridging.KendaliMutuKendaliBiayaJKN.tampilRanap() : " + e);
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
    
    public void tampilRalan() {
        unitnya = "";        
        Valid.tabelKosong(tabMode);

        if (jnsRawat.getSelectedIndex() == 2) {            
            unitnya = "p.nm_poli like '%" + NmPoli.getText() + "%'";
        } else {            
            unitnya = "p.nm_poli like '%%'";
        }

        try {
            //tarif obat yg dari tabel eklaim_set_claim dikali 0.8 atau 80%
            //0.8 adalah 80% sudah dipotong 20% dari harga tarif obat
            ps = koneksi.prepareStatement("SELECT enc.no_sep,enc.no_rm,enc.nm_pasien, p.nm_poli, d.nm_dokter, ROUND(esc.tarif_obat * 0.8) by_obat_real, "
                    + "concat('   ',enc.klaim_final) klaim_final,eg.cbg_desc,eg.cbg_tarif, IFNULL(egsc.desc,'-') topup_desc, IFNULL(egsc.tarif,0) topup_tarif, "
                    + "IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif) total_trf_grp, CONCAT('   ',FORMAT((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, "
                    + "rp.status_lanjut FROM eklaim_new_claim enc "
                    + "INNER JOIN eklaim_set_claim esc ON esc.no_sep = enc.no_sep "
                    + "INNER JOIN eklaim_grouping eg ON eg.no_sep = enc.no_sep "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = enc.no_rawat "
                    + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli "
                    + "INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter "
                    + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep = enc.no_sep WHERE "
                    + "rp.status_lanjut='ralan' and " + unitnya + " and d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and enc.no_sep like ? or "
                    + "rp.status_lanjut='ralan' and " + unitnya + " and d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and enc.no_rm like ? or "
                    + "rp.status_lanjut='ralan' and " + unitnya + " and d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and enc.nm_pasien like ? or "
                    + "rp.status_lanjut='ralan' and " + unitnya + " and d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and enc.klaim_final like ? or "
                    + "rp.status_lanjut='ralan' and " + unitnya + " and d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and eg.cbg_desc like ? or "
                    + "rp.status_lanjut='ralan' and " + unitnya + " and d.nm_dokter like '%" + TDokter.getText() + "%' and enc.tglsep BETWEEN ? AND ? and CONCAT(FORMAT((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') like ? "
                    + "ORDER BY enc.tglsep");
            try {
                ps.setString(1, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(TglSEP1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(TglSEP2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_sep"),
                        rs.getString("no_rm"),
                        rs.getString("nm_pasien"),
                        rs.getString("nm_poli"),
                        rs.getString("nm_dokter"),
                        rs.getDouble("by_obat_real"),
                        rs.getString("klaim_final"),
                        rs.getString("cbg_desc"),
                        rs.getDouble("cbg_tarif"),
                        rs.getString("topup_desc"),
                        rs.getDouble("topup_tarif"),
                        rs.getDouble("total_trf_grp"),
                        rs.getString("perc_pakai_obat"),
                        rs.getString("status_lanjut")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("bridging.KendaliMutuKendaliBiayaJKN.tampilRalan() : " + e);
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
        jnsRawat.setSelectedIndex(0);
        cmbRuangan.setSelectedIndex(0);
        cmbRuangan.setEnabled(false);
        btnPoli.setEnabled(false);
        tgl.setDate(1);
        TglSEP1.setDate(tgl);
        TglSEP2.setDate(new Date());
        KdPoli.setText("");
        NmPoli.setText("");
        kdDokter.setText("");
        TDokter.setText("");
        TCari.setText("");
        unitnya = "";
    }
}
