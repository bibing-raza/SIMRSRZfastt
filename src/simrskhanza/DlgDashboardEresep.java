package simrskhanza;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCariObat;
import inventory.DlgCariObat2;
import inventory.DlgPemberianObat;
import inventory.DlgResepObat;
import inventory.DlgReturJual;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.codehaus.groovy.syntax.Numbers;
import setting.DlgSetAplikasi;

public class DlgDashboardEresep extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    public DlgPasien pasien = new DlgPasien(null, false);
    public DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
    public DlgCariObat dlgobtjalan = new DlgCariObat(null, false);
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0;
    private String norawat = "", norm = "", status = "";
    public Timer tEresep;

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgDashboardEresep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "No. Telp/HP.", "Poliklinik",
            "Cara Bayar", "Dokter Yang Meresepkan", "Jlh. Item Obat", "tgl_resep"
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbPasien.setModel(tabMode);
        tbPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
//                column.setPreferredWidth(130);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new Object[]{
            "no_id", "no_rawat", "No.", "Tgl. Resep", "Jam Peresepan", "Deskripsi Obat/Alkes"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=false;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbdaftarResep.setModel(tabMode1);
        tbdaftarResep.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbdaftarResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbdaftarResep.getColumnModel().getColumn(i);
            if (i == 0) {
//                column.setPreferredWidth(160);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
//                column.setPreferredWidth(380);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(30);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(95);
            } else if (i == 5) {
                column.setPreferredWidth(450);
            }
        }
        tbdaftarResep.setDefaultRenderer(Object.class, new WarnaTable());

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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDashboardEresep")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        BtnPoli.requestFocus();
                    }
                }
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
        
        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgDashboardEresep")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        poli.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDashboardEresep")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                        btnPenjab.requestFocus();
                    }                    
                }
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

        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgDashboardEresep")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        OtomatisRefresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        MnCetakResepThermal = new javax.swing.JMenuItem();
        MnCetakResepA5 = new javax.swing.JMenuItem();
        MnDataPemberianObat = new javax.swing.JMenuItem();
        MnInputPemberianObat = new javax.swing.JMenuItem();
        TdataQRresep = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        jLabel23 = new widget.Label();
        tglCari1 = new widget.Tanggal();
        jLabel24 = new widget.Label();
        tglCari2 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel22 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        panelGlass8 = new widget.panelisi();
        ChkAutoRefres = new widget.CekBox();
        jLabel13 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAllCari = new widget.Button();
        BtnKeluar = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbPasien = new widget.Table();
        jPanel5 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbdaftarResep = new widget.Table();
        jPanel6 = new javax.swing.JPanel();
        panelGlass11 = new widget.panelisi();
        scrollPane3 = new widget.ScrollPane();
        gambarQR = new Painter();

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnCetakResepThermal.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakResepThermal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakResepThermal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakResepThermal.setText("Resep Kertas Thermal");
        MnCetakResepThermal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakResepThermal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakResepThermal.setIconTextGap(5);
        MnCetakResepThermal.setName("MnCetakResepThermal"); // NOI18N
        MnCetakResepThermal.setPreferredSize(new java.awt.Dimension(180, 26));
        MnCetakResepThermal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakResepThermalActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCetakResepThermal);

        MnCetakResepA5.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakResepA5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakResepA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakResepA5.setText("Resep Kertas HVS/A5");
        MnCetakResepA5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakResepA5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakResepA5.setIconTextGap(5);
        MnCetakResepA5.setName("MnCetakResepA5"); // NOI18N
        MnCetakResepA5.setPreferredSize(new java.awt.Dimension(180, 26));
        MnCetakResepA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakResepA5ActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCetakResepA5);

        MnDataPemberianObat.setBackground(new java.awt.Color(255, 255, 255));
        MnDataPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataPemberianObat.setText("Data Pemberian Obat");
        MnDataPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataPemberianObat.setIconTextGap(5);
        MnDataPemberianObat.setName("MnDataPemberianObat"); // NOI18N
        MnDataPemberianObat.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDataPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPemberianObatActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnDataPemberianObat);

        MnInputPemberianObat.setBackground(new java.awt.Color(255, 255, 255));
        MnInputPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputPemberianObat.setText("Input Pemberian Obat");
        MnInputPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputPemberianObat.setIconTextGap(5);
        MnInputPemberianObat.setName("MnInputPemberianObat"); // NOI18N
        MnInputPemberianObat.setPreferredSize(new java.awt.Dimension(180, 26));
        MnInputPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputPemberianObatActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnInputPemberianObat);

        TdataQRresep.setColumns(20);
        TdataQRresep.setRows(5);
        TdataQRresep.setName("TdataQRresep"); // NOI18N
        TdataQRresep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdataQRresepKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dashboard e-Resep Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 105));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 16));

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tanggal : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel23);

        tglCari1.setEditable(false);
        tglCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-08-2022" }));
        tglCari1.setDisplayFormat("dd-MM-yyyy");
        tglCari1.setName("tglCari1"); // NOI18N
        tglCari1.setOpaque(false);
        tglCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(tglCari1);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("s.d.");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel24);

        tglCari2.setEditable(false);
        tglCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-08-2022" }));
        tglCari2.setDisplayFormat("dd-MM-yyyy");
        tglCari2.setName("tglCari2"); // NOI18N
        tglCari2.setOpaque(false);
        tglCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(tglCari2);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Poliklinik : ");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel21);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setEnabled(false);
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(kdpoli);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setEnabled(false);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(250, 23));
        panelGlass9.add(TPoli);

        BtnPoli.setForeground(new java.awt.Color(0, 0, 0));
        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('4');
        BtnPoli.setToolTipText("ALt+4");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnPoli);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Cara Bayar : ");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel22);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpnj.setEnabled(false);
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(kdpnj);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setEnabled(false);
        nmpnj.setName("nmpnj"); // NOI18N
        nmpnj.setPreferredSize(new java.awt.Dimension(210, 23));
        panelGlass9.add(nmpnj);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        panelGlass9.add(btnPenjab);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 16));

        ChkAutoRefres.setBackground(new java.awt.Color(255, 255, 250));
        ChkAutoRefres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAutoRefres.setForeground(new java.awt.Color(0, 0, 0));
        ChkAutoRefres.setText("Aktifkan Auto Refresh Data");
        ChkAutoRefres.setBorderPainted(true);
        ChkAutoRefres.setBorderPaintedFlat(true);
        ChkAutoRefres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkAutoRefres.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAutoRefres.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAutoRefres.setName("ChkAutoRefres"); // NOI18N
        ChkAutoRefres.setPreferredSize(new java.awt.Dimension(190, 23));
        ChkAutoRefres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAutoRefresActionPerformed(evt);
            }
        });
        panelGlass8.add(ChkAutoRefres);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Key Word : ");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass8.add(jLabel13);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setHighlighter(null);
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

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
        panelGlass8.add(BtnCari);

        BtnAllCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnAllCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllCari.setMnemonic('D');
        BtnAllCari.setText("Semua Data");
        BtnAllCari.setToolTipText("Alt+D");
        BtnAllCari.setName("BtnAllCari"); // NOI18N
        BtnAllCari.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAllCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllCariActionPerformed(evt);
            }
        });
        BtnAllCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllCariKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAllCari);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Daftar Pasien Yang Telah Diresepkan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPasien.setAutoCreateRowSorter(true);
        tbPasien.setToolTipText("");
        tbPasien.setComponentPopupMenu(jPopupMenu);
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

        jPanel4.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Resep Obat ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbdaftarResep.setAutoCreateRowSorter(true);
        tbdaftarResep.setToolTipText("");
        tbdaftarResep.setName("tbdaftarResep"); // NOI18N
        Scroll1.setViewportView(tbdaftarResep);

        jPanel5.add(Scroll1, java.awt.BorderLayout.CENTER);

        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(44, 270));
        jPanel6.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Untuk Membantu Proses Pencarian Ke Rak Obat Scan QR Code Ini ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setPreferredSize(new java.awt.Dimension(230, 230));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        gambarQR.setPreferredSize(new java.awt.Dimension(85, 90));
        scrollPane3.setViewportView(gambarQR);

        panelGlass11.add(scrollPane3);

        jPanel6.add(panelGlass11, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel5);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPasienMouseClicked

    private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPasienKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCariKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllCariActionPerformed
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnAllCariActionPerformed

    private void BtnAllCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAllCariKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        akses.setform("DlgDashboardEresep");
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgDashboardEresep");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void MnCetakResepThermalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakResepThermalActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbPasien.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            Valid.MyReport("rptStrukResepRalan.jasper", "report", "::[ Struk Resep Dokter Poliklinik/Unit Rawat Jalan Kertas Thermal ]::",
                    " SELECT pl.nm_poli, date_format(cr.tgl_perawatan,'%d-%m-%Y') tgl, d.nm_dokter, cr.no_rawat, p.no_rkm_medis, "
                    + "p.nm_pasien, ifnull(p.no_tlp,'-') no_hp, cr.nama_obat FROM catatan_resep cr "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                    + "INNER JOIN dokter d ON d.kd_dokter=cr.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "WHERE cr.no_rawat='" + norawat + "' ORDER BY cr.tgl_perawatan DESC, cr.jam_perawatan DESC", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakResepThermalActionPerformed

    private void MnDataPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPemberianObatActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            tbPasien.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", norawat) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgPemberianObat formobat = new DlgPemberianObat(null, false);
                formobat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                formobat.setLocationRelativeTo(internalFrame1);
                formobat.isCek();
                formobat.setNoRm(norawat, tglCari1.getDate(), tglCari2.getDate(), "ralan");
                formobat.tampilPO();
                formobat.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnDataPemberianObatActionPerformed

    private void MnInputPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputPemberianObatActionPerformed
        if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel..!!!");
        } else {
            akses.setform("DlgDashboardEresep");
            if (akses.getkode().equals("Admin Utama")) {
                if (status.equals("ranap")) {
                    dlgobt.setNoRm(norawat, tglCari1.getDate(), Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%H')"), Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%i')"), Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%s')"), false);
                    dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobt.isCek();
                    dlgobt.tampil();
                    dlgobt.setLocationRelativeTo(internalFrame1);
                    dlgobt.setVisible(true);
                } else if (status.equals("ralan")) {
                    dlgobtjalan.setNoRm(norawat, norm, Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + norm + "'"),
                            Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norawat + "'"),
                            Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + norawat + "'"));
                    dlgobtjalan.isCek();
//                    if(!namadokter.equals("")){
//                        dlgobtjalan.setDokter(kodedokter, namadokter);
//                    }
                    dlgobtjalan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobtjalan.tampilobat();
                    dlgobtjalan.tampil_resep();
                    dlgobtjalan.setLocationRelativeTo(internalFrame1);
                    dlgobtjalan.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Hanya bisa lewat Kasir Ralan atau Kamar Inap");
                }
            } else {
                if (status.equals("ranap")) {
                    dlgobt.setNoRm(norawat, tglCari1.getDate(), Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%H')"), Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%i')"), Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%s')"), false);
                    dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobt.isCek();
                    dlgobt.tampil();
                    dlgobt.setLocationRelativeTo(internalFrame1);
                    dlgobt.setVisible(true);
                } else if (status.equals("ralan")) {
                    dlgobtjalan.setNoRm(norawat, norm, Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + norm + "'"),
                            Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norawat + "'"),
                            Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + norawat + "'"));
                    dlgobtjalan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobtjalan.isCek();
//                        if(!namadokter.equals("")){
//                            dlgobtjalan.setDokter(kodedokter, namadokter);
//                        }
                    dlgobtjalan.tampilobat();
                    dlgobtjalan.setLocationRelativeTo(internalFrame1);
                    dlgobtjalan.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Hanya bisa lewat Kasir Ralan atau Kamar Inap");
                }
            }
        }
    }//GEN-LAST:event_MnInputPemberianObatActionPerformed

    private void ChkAutoRefresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAutoRefresActionPerformed
        if (ChkAutoRefres.isSelected() == true) {            
            tEresep.start();
            JOptionPane.showMessageDialog(rootPane, "Data pasien akan di refresh otomatis per 5 menit sekali,..!!");
            tampil();
        } else {
            tEresep.stop();
            tampil();
        }
    }//GEN-LAST:event_ChkAutoRefresActionPerformed

    private void TdataQRresepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdataQRresepKeyPressed

    }//GEN-LAST:event_TdataQRresepKeyPressed

    private void MnCetakResepA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakResepA5ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan klik salah satu daftar pasien yang telah diresepkan pada tabel...!!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptResepRalan.jasper", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan Kertas HVS/A5 ]::",
                    " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, c.nama_obat, "
                    + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp,'-') noHP from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                    + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where c.no_rawat ='" + norawat + "' ORDER BY c.tgl_perawatan DESC, c.jam_perawatan DESC", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakResepA5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDashboardEresep dialog = new DlgDashboardEresep(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAllCari;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPoli;
    public widget.CekBox ChkAutoRefres;
    private javax.swing.JMenuItem MnCetakResepA5;
    private javax.swing.JMenuItem MnCetakResepThermal;
    private javax.swing.JMenuItem MnDataPemberianObat;
    private javax.swing.JMenuItem MnInputPemberianObat;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TPoli;
    private widget.TextArea TdataQRresep;
    private widget.Button btnPenjab;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel13;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.TextBox nmpnj;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbPasien;
    private widget.Table tbdaftarResep;
    private widget.Tanggal tglCari1;
    private widget.Tanggal tglCari2;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode1);
        ((Painter) gambarQR).setImage("");
        try {
            ps = koneksi.prepareStatement("SELECT cr.no_rawat, p.no_rkm_medis, concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') pasienya, p.no_tlp, "
                    + "pl.nm_poli, pj.png_jawab, d.nm_dokter, COUNT(cr.no_rawat) jlh_item_obat, cr.tgl_perawatan FROM catatan_resep cr "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN dokter d on d.kd_dokter=cr.kd_dokter WHERE "
                    + "cr.tgl_perawatan between ? and ? and cr.status='belum' and rp.kd_poli like ? and rp.kd_pj like ? and cr.no_rawat like ? or "
                    + "cr.tgl_perawatan between ? and ? and cr.status='belum' and rp.kd_poli like ? and rp.kd_pj like ? and p.no_rkm_medis like ? or "
                    + "cr.tgl_perawatan between ? and ? and cr.status='belum' and rp.kd_poli like ? and rp.kd_pj like ? and concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') like ? or "
                    + "cr.tgl_perawatan between ? and ? and cr.status='belum' and rp.kd_poli like ? and rp.kd_pj like ? and p.no_tlp like ? or "
                    + "cr.tgl_perawatan between ? and ? and cr.status='belum' and rp.kd_poli like ? and rp.kd_pj like ? and d.nm_dokter like ? "
                    + "GROUP BY cr.no_rawat ORDER BY cr.tgl_perawatan, cr.jam_perawatan");

            try {
                ps.setString(1, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + kdpoli.getText().trim() + "%");
                ps.setString(4, "%" + kdpnj.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");                
                ps.setString(6, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(7, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(8, "%" + kdpoli.getText().trim() + "%");
                ps.setString(9, "%" + kdpnj.getText().trim() + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");                
                ps.setString(11, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(12, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(13, "%" + kdpoli.getText().trim() + "%");
                ps.setString(14, "%" + kdpnj.getText().trim() + "%");
                ps.setString(15, "%" + TCari.getText().trim() + "%");                
                ps.setString(16, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + kdpoli.getText().trim() + "%");
                ps.setString(19, "%" + kdpnj.getText().trim() + "%");
                ps.setString(20, "%" + TCari.getText().trim() + "%");                
                ps.setString(21, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(22, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(23, "%" + kdpoli.getText().trim() + "%");
                ps.setString(24, "%" + kdpnj.getText().trim() + "%");
                ps.setString(25, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("pasienya"),
                        rs.getString("no_tlp"),
                        rs.getString("nm_poli"),
                        rs.getString("png_jawab"),
                        rs.getString("nm_dokter"),
                        rs.getString("jlh_item_obat"),
                        rs.getString("tgl_perawatan")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgDashboardEresep.tampil() : " + e);
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
    }

    public void emptTeks() {
        kdpoli.setText("");
        TPoli.setText("");
        kdpnj.setText("");
        nmpnj.setText("");
        TCari.setText("");
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode1);
    }

    private void getData() {
        norawat = "";
        norm = "";
        status = "";
        TdataQRresep.setText("");
        
        if (tbPasien.getSelectedRow() != -1) {
            norawat = tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString();
            norm = tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString();
            status = "ralan";
            tampilResep();
            tampilQR();
        }
    }

    public JTable getTable() {
        return tbPasien;
    }

    private void tampilResep() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT cr.noId, cr.no_rawat, DATE_FORMAT(cr.tgl_perawatan,'%d-%m-%Y') tgl, cr.jam_perawatan, cr.nama_obat "
                    + "FROM catatan_resep cr INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat "
                    + "WHERE cr.no_rawat='" + norawat + "' ORDER BY cr.tgl_perawatan DESC, cr.jam_perawatan DESC");
            try {
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("noId"),
                        rs1.getString("no_rawat"),
                        x + ".",
                        rs1.getString("tgl"),
                        rs1.getString("jam_perawatan"),
                        rs1.getString("nama_obat")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgDashboardEresep.tampilResep() : " + e);
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
    }
    
    public void isCek() {
        MnDataPemberianObat.setEnabled(akses.getberi_obat());
        MnInputPemberianObat.setEnabled(akses.getberi_obat());
        TCari.requestFocus();
    }
    
    private void OtomatisRefresh() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ChkAutoRefres.isSelected() == true) {
                    tampil();                    
                }
            }
        };
        
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        //interval 300000 ms = 5 menit
        tEresep = new Timer(300000, taskPerformer);
    }
    
//    private void OtomatisRefresStop() {
//        ActionListener taskPerformer = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (ChkAutoRefres.isSelected() == true) {
//                    tampil();
//                }
//            }
//        };
//        
//        // Timer
//        //interval 1000 ms = 1 detik
//        //interval 30000 ms = 30 detik atau setngah menit
//        //interval 300000 ms = 5 menit
//        new Timer(300000, taskPerformer).stop();
//    }
    
    private void tampilQR() {
        ((Painter) gambarQR).setImage("");
        try {
            ps2 = koneksi.prepareStatement(
                    " SELECT CONCAT('Poliklinik : ',pl.nm_poli) datanya FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                    + "INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli WHERE cr.no_rawat='" + norawat + "' GROUP BY cr.no_rawat "
                    + "UNION ALL "
                    + "SELECT CONCAT('Tgl. Resep : ',DATE_FORMAT(tgl_perawatan,'%d "
                    + Sequel.bulanINDONESIA("SELECT DATE_FORMAT(tgl_perawatan,'%m') FROM catatan_resep "
                    + "WHERE no_rawat='" + norawat + "' GROUP BY no_rawat") + " %Y')) FROM catatan_resep WHERE no_rawat='" + norawat + "' GROUP BY no_rawat "
                    + "UNION ALL "
                    + "SELECT CONCAT('Nama Dokter : ',d.nm_dokter) FROM catatan_resep cr INNER JOIN dokter d ON d.kd_dokter=cr.kd_dokter "
                    + "WHERE cr.no_rawat='" + norawat + "' GROUP BY cr.no_rawat "
                    + "UNION ALL "
                    + "SELECT CONCAT('No. RM : ',rp.no_rkm_medis) FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                    + "WHERE cr.no_rawat='" + norawat + "' GROUP BY cr.no_rawat "
                    + "UNION ALL "
                    + "SELECT CONCAT('Pasien : ',p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,'.)') FROM catatan_resep cr "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "WHERE cr.no_rawat='" + norawat + "' GROUP BY cr.no_rawat "
                    + "UNION ALL "
                    + "SELECT '------------------------------' "
                    + "UNION ALL "
                    + "SELECT 'Item Resep Obat/Alkes : ' "
                    + "UNION ALL "
                    + "SELECT CONCAT('~ ',nama_obat) FROM catatan_resep WHERE no_rawat='" + norawat + "' "
                    + "UNION ALL "
                    + "SELECT '------------------------------' "
                    + "UNION ALL "
                    + "SELECT 'Totalnya Ada : " + Sequel.cariInteger("SELECT count(no_rawat) FROM catatan_resep WHERE no_rawat='" + norawat + "'") + " Item Resep.' ");

            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    TdataQRresep.append(rs2.getString("datanya"));
                    TdataQRresep.append("\n");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
       
        Valid.cetakQr(TdataQRresep.getText(), Sequel.FolderQRresep(), "QRresep.jpg");
        Sequel.queryu("delete from setting_qr where judul = 'QRresep'");
        Sequel.menyimpanQr("setting_qr", "'QRresep'", "file QRCode Resep", Sequel.cariFolderPrintResep());

        try {            
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select gambar from setting_qr where judul = 'QRresep'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((DlgDashboardEresep.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }
        } catch (Exception ex) {
            cetak(ex.toString());
        }        
    }
    
    private String gambar(String id) {
        return folder + File.separator + id.trim() + ".jpg";
    }

    private String folder;

    public class Painter extends Canvas {

        Image image;

        public void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (MalformedURLException ex) {
                cetak(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }
        public void setImageIcon(ImageIcon file) {
            image = file.getImage();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            try {
                double d = image.getHeight(this) / this.getHeight();
                double w = image.getWidth(this) / d;
                double x = this.getWidth() / 2 - w / 2;
                g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
            } catch (Exception e) {
            }            
        }
    }
    
    private void cetak(String str) {
        System.out.println(str);
    }
}
