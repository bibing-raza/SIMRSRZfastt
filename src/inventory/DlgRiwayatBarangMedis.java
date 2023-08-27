package inventory;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import simrskhanza.DlgCariBangsal;

public class DlgRiwayatBarangMedis extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private DlgBarang barang = new DlgBarang(null, false);
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);
    private String where = "";

    /**
     * @param parent
     * @param modal
     */
    public DlgRiwayatBarangMedis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "Barang", "Awal", "Masuk", "Keluar", "Akhir", "Posisi",
            "Tanggal", "Jam", "Petugas", "Lokasi", "Status"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 11; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(230);
            } else if (i == 1) {
                column.setPreferredWidth(45);
            } else if (i == 2) {
                column.setPreferredWidth(45);
            } else if (i == 3) {
                column.setPreferredWidth(45);
            } else if (i == 4) {
                column.setPreferredWidth(45);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(110);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(60);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    prosesCari();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    prosesCari();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    prosesCari();
                }
            });
        }

        barang.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRiwayatBarangMedis")) {
                    if (barang.getTable().getSelectedRow() != -1) {
                        kdbar.setText(barang.getTable().getValueAt(barang.getTable().getSelectedRow(), 1).toString());
                        nmbar.setText(barang.getTable().getValueAt(barang.getTable().getSelectedRow(), 2).toString());
                    }
                    kdbar.requestFocus();
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

        barang.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRiwayatBarangMedis")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        barang.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRiwayatBarangMedis")) {
                    if (bangsal.getTable().getSelectedRow() != -1) {
                        KdGudang.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 0).toString());
                        NmGudang.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 1).toString());
                    }
                    KdGudang.requestFocus();
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

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnLapRiwayat = new javax.swing.JMenu();
        MnRiwayatPerDepo = new javax.swing.JMenuItem();
        MnRiwayatSemuaDepo = new javax.swing.JMenuItem();
        MnTIRiwayatPerDepo = new javax.swing.JMenuItem();
        MnTIRiwayatSemuaDepo = new javax.swing.JMenuItem();
        MnLapRiwayat1 = new javax.swing.JMenu();
        MnRiwayatPerDepo0 = new javax.swing.JMenuItem();
        MnRiwayatPerDepo1 = new javax.swing.JMenuItem();
        MnRiwayatPerDepo2 = new javax.swing.JMenuItem();
        MnRiwayatPerDepo3 = new javax.swing.JMenuItem();
        MnTIRiwayatSemuaDepo0 = new javax.swing.JMenuItem();
        MnTIRiwayatSemuaDepo1 = new javax.swing.JMenuItem();
        MnTIRiwayatSemuaDepo2 = new javax.swing.JMenuItem();
        MnTIRiwayatSemuaDepo3 = new javax.swing.JMenuItem();
        MnLapPakaiBekalFarmasi = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi4 = new widget.panelisi();
        label17 = new widget.Label();
        kdbar = new widget.TextBox();
        nmbar = new widget.TextBox();
        btnBarang = new widget.Button();
        label19 = new widget.Label();
        KdGudang = new widget.TextBox();
        NmGudang = new widget.TextBox();
        btnBarang1 = new widget.Button();
        panelisi1 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label9 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        Kd2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(230, 85));

        MnLapRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnLapRiwayat.setText("Lap. Rekap Total Riwayat");
        MnLapRiwayat.setToolTipText("");
        MnLapRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLapRiwayat.setName("MnLapRiwayat"); // NOI18N
        MnLapRiwayat.setPreferredSize(new java.awt.Dimension(60, 30));

        MnRiwayatPerDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatPerDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRiwayatPerDepo.setText("Obat/Alkes/BHP Per Depo");
        MnRiwayatPerDepo.setToolTipText("");
        MnRiwayatPerDepo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MnRiwayatPerDepo.setName("MnRiwayatPerDepo"); // NOI18N
        MnRiwayatPerDepo.setPreferredSize(new java.awt.Dimension(230, 26));
        MnRiwayatPerDepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerDepoActionPerformed(evt);
            }
        });
        MnLapRiwayat.add(MnRiwayatPerDepo);

        MnRiwayatSemuaDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatSemuaDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRiwayatSemuaDepo.setText("Obat/Alkes/BHP Semua Depo");
        MnRiwayatSemuaDepo.setName("MnRiwayatSemuaDepo"); // NOI18N
        MnRiwayatSemuaDepo.setPreferredSize(new java.awt.Dimension(230, 26));
        MnRiwayatSemuaDepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatSemuaDepoActionPerformed(evt);
            }
        });
        MnLapRiwayat.add(MnRiwayatSemuaDepo);

        MnTIRiwayatPerDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTIRiwayatPerDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTIRiwayatPerDepo.setText("Per Item Obat/Alkes/BHP Per Depo");
        MnTIRiwayatPerDepo.setToolTipText("");
        MnTIRiwayatPerDepo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MnTIRiwayatPerDepo.setName("MnTIRiwayatPerDepo"); // NOI18N
        MnTIRiwayatPerDepo.setPreferredSize(new java.awt.Dimension(230, 26));
        MnTIRiwayatPerDepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTIRiwayatPerDepoActionPerformed(evt);
            }
        });
        MnLapRiwayat.add(MnTIRiwayatPerDepo);

        MnTIRiwayatSemuaDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTIRiwayatSemuaDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTIRiwayatSemuaDepo.setText("Per Item Obat/Alkes/BHP Semua Depo");
        MnTIRiwayatSemuaDepo.setName("MnTIRiwayatSemuaDepo"); // NOI18N
        MnTIRiwayatSemuaDepo.setPreferredSize(new java.awt.Dimension(230, 26));
        MnTIRiwayatSemuaDepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTIRiwayatSemuaDepoActionPerformed(evt);
            }
        });
        MnLapRiwayat.add(MnTIRiwayatSemuaDepo);

        jPopupMenu1.add(MnLapRiwayat);

        MnLapRiwayat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnLapRiwayat1.setText("Lap. Rekap Riwayat Pemakaian");
        MnLapRiwayat1.setToolTipText("");
        MnLapRiwayat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLapRiwayat1.setName("MnLapRiwayat1"); // NOI18N
        MnLapRiwayat1.setPreferredSize(new java.awt.Dimension(60, 30));

        MnRiwayatPerDepo0.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatPerDepo0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRiwayatPerDepo0.setText("Obat/Alkes/BHP Per Depo Jual Bebas");
        MnRiwayatPerDepo0.setToolTipText("");
        MnRiwayatPerDepo0.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MnRiwayatPerDepo0.setName("MnRiwayatPerDepo0"); // NOI18N
        MnRiwayatPerDepo0.setPreferredSize(new java.awt.Dimension(260, 26));
        MnRiwayatPerDepo0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerDepo0ActionPerformed(evt);
            }
        });
        MnLapRiwayat1.add(MnRiwayatPerDepo0);

        MnRiwayatPerDepo1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatPerDepo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRiwayatPerDepo1.setText("Obat/Alkes/BHP Per Depo Rawat Jalan");
        MnRiwayatPerDepo1.setToolTipText("");
        MnRiwayatPerDepo1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MnRiwayatPerDepo1.setName("MnRiwayatPerDepo1"); // NOI18N
        MnRiwayatPerDepo1.setPreferredSize(new java.awt.Dimension(260, 26));
        MnRiwayatPerDepo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerDepo1ActionPerformed(evt);
            }
        });
        MnLapRiwayat1.add(MnRiwayatPerDepo1);

        MnRiwayatPerDepo2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatPerDepo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRiwayatPerDepo2.setText("Obat/Alkes/BHP Per Depo Rawat Inap");
        MnRiwayatPerDepo2.setName("MnRiwayatPerDepo2"); // NOI18N
        MnRiwayatPerDepo2.setPreferredSize(new java.awt.Dimension(260, 26));
        MnRiwayatPerDepo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerDepo2ActionPerformed(evt);
            }
        });
        MnLapRiwayat1.add(MnRiwayatPerDepo2);

        MnRiwayatPerDepo3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatPerDepo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRiwayatPerDepo3.setText("Obat/Alkes/BHP Per Depo Semua Rawat");
        MnRiwayatPerDepo3.setToolTipText("");
        MnRiwayatPerDepo3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MnRiwayatPerDepo3.setName("MnRiwayatPerDepo3"); // NOI18N
        MnRiwayatPerDepo3.setPreferredSize(new java.awt.Dimension(260, 26));
        MnRiwayatPerDepo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerDepo3ActionPerformed(evt);
            }
        });
        MnLapRiwayat1.add(MnRiwayatPerDepo3);

        MnTIRiwayatSemuaDepo0.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTIRiwayatSemuaDepo0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTIRiwayatSemuaDepo0.setText("Obat/Alkes/BHP Semua Depo Jual Bebas");
        MnTIRiwayatSemuaDepo0.setName("MnTIRiwayatSemuaDepo0"); // NOI18N
        MnTIRiwayatSemuaDepo0.setPreferredSize(new java.awt.Dimension(260, 26));
        MnTIRiwayatSemuaDepo0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTIRiwayatSemuaDepo0ActionPerformed(evt);
            }
        });
        MnLapRiwayat1.add(MnTIRiwayatSemuaDepo0);

        MnTIRiwayatSemuaDepo1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTIRiwayatSemuaDepo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTIRiwayatSemuaDepo1.setText("Obat/Alkes/BHP Semua Depo Rawat Jalan");
        MnTIRiwayatSemuaDepo1.setName("MnTIRiwayatSemuaDepo1"); // NOI18N
        MnTIRiwayatSemuaDepo1.setPreferredSize(new java.awt.Dimension(260, 26));
        MnTIRiwayatSemuaDepo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTIRiwayatSemuaDepo1ActionPerformed(evt);
            }
        });
        MnLapRiwayat1.add(MnTIRiwayatSemuaDepo1);

        MnTIRiwayatSemuaDepo2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTIRiwayatSemuaDepo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTIRiwayatSemuaDepo2.setText("Obat/Alkes/BHP Semua Depo Rawat Inap");
        MnTIRiwayatSemuaDepo2.setName("MnTIRiwayatSemuaDepo2"); // NOI18N
        MnTIRiwayatSemuaDepo2.setPreferredSize(new java.awt.Dimension(260, 26));
        MnTIRiwayatSemuaDepo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTIRiwayatSemuaDepo2ActionPerformed(evt);
            }
        });
        MnLapRiwayat1.add(MnTIRiwayatSemuaDepo2);

        MnTIRiwayatSemuaDepo3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTIRiwayatSemuaDepo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTIRiwayatSemuaDepo3.setText("Obat/Alkes/BHP Semua Depo Semua Rawat");
        MnTIRiwayatSemuaDepo3.setName("MnTIRiwayatSemuaDepo3"); // NOI18N
        MnTIRiwayatSemuaDepo3.setPreferredSize(new java.awt.Dimension(260, 26));
        MnTIRiwayatSemuaDepo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTIRiwayatSemuaDepo3ActionPerformed(evt);
            }
        });
        MnLapRiwayat1.add(MnTIRiwayatSemuaDepo3);

        jPopupMenu1.add(MnLapRiwayat1);

        MnLapPakaiBekalFarmasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLapPakaiBekalFarmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLapPakaiBekalFarmasi.setText("Lap. Pemakaian Perbekalan Farmasi");
        MnLapPakaiBekalFarmasi.setName("MnLapPakaiBekalFarmasi"); // NOI18N
        MnLapPakaiBekalFarmasi.setPreferredSize(new java.awt.Dimension(60, 30));
        MnLapPakaiBekalFarmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapPakaiBekalFarmasiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLapPakaiBekalFarmasi);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Riwayat Obat, Alkes & BHP ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setComponentPopupMenu(jPopupMenu1);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDokter.setAutoCreateRowSorter(true);
        tbDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter.setToolTipText("Silahkan klik kanan untuk memilih laporan yang akan dicetak");
        tbDokter.setComponentPopupMenu(jPopupMenu1);
        tbDokter.setName("tbDokter"); // NOI18N
        scrollPane1.setViewportView(tbDokter);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Barang :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi4.add(label17);

        kdbar.setForeground(new java.awt.Color(0, 0, 0));
        kdbar.setName("kdbar"); // NOI18N
        kdbar.setPreferredSize(new java.awt.Dimension(80, 23));
        kdbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbarKeyPressed(evt);
            }
        });
        panelisi4.add(kdbar);

        nmbar.setEditable(false);
        nmbar.setForeground(new java.awt.Color(0, 0, 0));
        nmbar.setName("nmbar"); // NOI18N
        nmbar.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi4.add(nmbar);

        btnBarang.setForeground(new java.awt.Color(0, 0, 0));
        btnBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBarang.setMnemonic('1');
        btnBarang.setToolTipText("Alt+1");
        btnBarang.setName("btnBarang"); // NOI18N
        btnBarang.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });
        panelisi4.add(btnBarang);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Lokasi :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(75, 23));
        panelisi4.add(label19);

        KdGudang.setEditable(false);
        KdGudang.setForeground(new java.awt.Color(0, 0, 0));
        KdGudang.setName("KdGudang"); // NOI18N
        KdGudang.setPreferredSize(new java.awt.Dimension(80, 23));
        KdGudang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdGudangKeyPressed(evt);
            }
        });
        panelisi4.add(KdGudang);

        NmGudang.setEditable(false);
        NmGudang.setForeground(new java.awt.Color(0, 0, 0));
        NmGudang.setName("NmGudang"); // NOI18N
        NmGudang.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi4.add(NmGudang);

        btnBarang1.setForeground(new java.awt.Color(0, 0, 0));
        btnBarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBarang1.setMnemonic('1');
        btnBarang1.setToolTipText("Alt+1");
        btnBarang1.setName("btnBarang1"); // NOI18N
        btnBarang1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarang1ActionPerformed(evt);
            }
        });
        panelisi4.add(btnBarang1);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(53, 23));
        panelisi1.add(label11);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi1.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelisi1.add(label18);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi1.add(Tgl2);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(69, 23));
        panelisi1.add(label10);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(170, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        panelisi1.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('A');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+A");
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
        panelisi1.add(BtnAll);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(19, 30));
        panelisi1.add(label9);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('P');
        BtnPrint.setText("Cetak Rekap Detail");
        BtnPrint.setToolTipText("Alt+P");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(160, 30));
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

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
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
            Valid.MyReport("rptRiwayatBarangMedis.jasper", "report", "::[ Riwayat Obat, Alkes & BHP ]::",
                    "select riwayat_barang_medis.kode_brng,databarang.nama_brng,"
                    + "riwayat_barang_medis.stok_awal,riwayat_barang_medis.masuk,"
                    + "riwayat_barang_medis.keluar,riwayat_barang_medis.stok_akhir,"
                    + "riwayat_barang_medis.posisi,riwayat_barang_medis.tanggal,"
                    + "riwayat_barang_medis.jam,riwayat_barang_medis.petugas,"
                    + "riwayat_barang_medis.kd_bangsal,bangsal.nm_bangsal,"
                    + "riwayat_barang_medis.status from riwayat_barang_medis "
                    + "inner join bangsal inner join databarang on "
                    + "riwayat_barang_medis.kode_brng=databarang.kode_brng and "
                    + "riwayat_barang_medis.kd_bangsal=bangsal.kd_bangsal where "
                    + "riwayat_barang_medis.tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and databarang.nama_brng like '%" + nmbar.getText() + "%' and bangsal.nm_bangsal like '%" + NmGudang.getText() + "%' and riwayat_barang_medis.kode_brng like '%" + TCari.getText().trim() + "%' or "
                    + "riwayat_barang_medis.tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and databarang.nama_brng like '%" + nmbar.getText() + "%' and bangsal.nm_bangsal like '%" + NmGudang.getText() + "%' and databarang.nama_brng like '%" + TCari.getText().trim() + "%' or "
                    + "riwayat_barang_medis.tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and databarang.nama_brng like '%" + nmbar.getText() + "%' and bangsal.nm_bangsal like '%" + NmGudang.getText() + "%' and riwayat_barang_medis.petugas like '%" + TCari.getText().trim() + "%' or "
                    + "riwayat_barang_medis.tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and databarang.nama_brng like '%" + nmbar.getText() + "%' and bangsal.nm_bangsal like '%" + NmGudang.getText() + "%' and bangsal.nm_bangsal like '%" + TCari.getText().trim() + "%' or "
                    + "riwayat_barang_medis.tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and databarang.nama_brng like '%" + nmbar.getText() + "%' and bangsal.nm_bangsal like '%" + NmGudang.getText() + "%' and riwayat_barang_medis.kd_bangsal like '%" + TCari.getText().trim() + "%' or "
                    + "riwayat_barang_medis.tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and databarang.nama_brng like '%" + nmbar.getText() + "%' and bangsal.nm_bangsal like '%" + NmGudang.getText() + "%' and riwayat_barang_medis.status like '%" + TCari.getText().trim() + "%' order by riwayat_barang_medis.tanggal,riwayat_barang_medis.jam ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }

    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, Tgl2, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, Tgl1);
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
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        prosesCari();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void kdbarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama_brng from databarang where kode_brng=?", nmbar, kdbar.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nama_brng from databarang where kode_brng=?", nmbar, kdbar.getText());
            TCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama_brng from databarang where kode_brng=?", nmbar, kdbar.getText());
            TCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnBarangActionPerformed(null);
        }
    }//GEN-LAST:event_kdbarKeyPressed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        akses.setform("DlgRiwayatBarangMedis");
        barang.emptTeks();
        barang.isCek();
        barang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        barang.setLocationRelativeTo(internalFrame1);
        barang.setAlwaysOnTop(false);
        barang.setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        kdbar.setText("");
        nmbar.setText("");
        KdGudang.setText("");
        NmGudang.setText("");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        prosesCari();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        prosesCari();
    }//GEN-LAST:event_formWindowOpened

    private void btnBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarang1ActionPerformed
        akses.setform("DlgRiwayatBarangMedis");
        bangsal.emptTeks();
        bangsal.isCek();
        bangsal.TCari.setText("apt");
        bangsal.setSize(1046, 350);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setAlwaysOnTop(false);
        bangsal.setVisible(true);
    }//GEN-LAST:event_btnBarang1ActionPerformed

    private void KdGudangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdGudangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdGudangKeyPressed

    private void MnRiwayatPerDepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerDepoActionPerformed
        if (KdGudang.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih/isi dulu salah satu lokasi depo farmasi nya..!!!");
            btnBarang1.requestFocus();
        } else {
            
            where = "";
            if(TCari.getText().trim().equals("")){
                where = "";
            }else{
                where = "where a.nama_brng like '%"+ TCari.getText().trim() +"%'";
            }
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_awal", Tgl1.getSelectedItem());
            param.put("tgl_akhir", Tgl2.getSelectedItem());
            param.put("lokasi", "Lokasi : " + NmGudang.getText());
            Valid.MyReport("rptRiwayatTotalBarangMedisPerDepoNew.jasper", "report", "::[ Laporan Rekap Total Riwayat Obat/Alkes/BHP Per Depo ]::",
                    " select * from( "
                    + "(select r.kode_brng,d.nama_brng,d.kode_sat,d.ralan as hrg_jual, round(sum(masuk)) stok_masuk, round(sum(keluar)) stok_keluar, r.posisi as 'keterangan', b.nm_bangsal as unit_yg_mengeluarkan, (d.ralan*round(sum(keluar))) as total_nominal "
                    + "from riwayat_barang_medis r "
                    + "inner join databarang d on d. kode_brng = r.kode_brng "
                    + "inner join bangsal b on b.kd_bangsal = r.kd_bangsal "
                    + "where r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and r.kd_bangsal = '" + KdGudang.getText() + "' "
                    + "and r.posisi <> 'OPNAME' "
                    + "group by r.kode_brng,r.posisi "
                    + "union all "
                    + "select r.kode_brng,d.nama_brng,d.kode_sat,d.ralan as hrg_jual, round(sum(masuk)) stok_masuk, round(sum(keluar)) stok_keluar, r.posisi as 'keterangan', b.nm_bangsal as unit_yg_mengeluarkan, (d.ralan*round(sum(keluar))) as total_nominal "
                    + "from riwayat_barang_medis r "
                    + "inner join databarang d on d. kode_brng = r.kode_brng "
                    + "inner join bangsal b on b.kd_bangsal = r.kd_bangsal "
                    + "where r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and r.kd_bangsal = '" + KdGudang.getText() + "' "
                    + "and r.posisi = 'OPNAME' "
                    + "group by r.kode_brng,r.posisi) as a "
                    + ") "+ where +" ORDER BY a.keterangan,a.nama_brng",
                    param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRiwayatPerDepoActionPerformed

    private void MnRiwayatSemuaDepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatSemuaDepoActionPerformed
        where = "";
            if(TCari.getText().trim().equals("")){
                where = "";
            }else{
                where = "where a.nama_brng like '%"+ TCari.getText().trim() +"%'";
            }        
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Tgl1.getSelectedItem());
        param.put("tgl_akhir", Tgl2.getSelectedItem());
        Valid.MyReport("rptRiwayatTotalBarangMedisSemuaDepoNew.jasper", "report", "::[ Laporan Rekap Total Riwayat Obat/Alkes/BHP Semua Depo ]::",
                " select * from( "
                + "(select r.kode_brng,d.nama_brng,d.kode_sat,d.ralan as hrg_jual, round(sum(masuk)) stok_masuk, round(sum(keluar)) stok_keluar, r.posisi as 'keterangan', (d.ralan*round(sum(keluar))) as total_nominal "
                + "from riwayat_barang_medis r "
                + "inner join databarang d on d. kode_brng = r.kode_brng "
                + "inner join bangsal b on b.kd_bangsal = r.kd_bangsal "
                + "where r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and r.posisi <> 'OPNAME' "
                + "group by r.kode_brng,r.posisi "
                + "union all "
                + "select r.kode_brng,d.nama_brng,d.kode_sat,d.ralan as hrg_jual, round(sum(masuk)) stok_masuk, round(sum(keluar)) stok_keluar, r.posisi as 'keterangan',  (d.ralan*round(sum(keluar))) as total_nominal "
                + "from riwayat_barang_medis r "
                + "inner join databarang d on d. kode_brng = r.kode_brng "
                + "inner join bangsal b on b.kd_bangsal = r.kd_bangsal "
                + "where r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and r.posisi = 'OPNAME' "
                + "group by r.kode_brng,r.posisi) as a "
                + ") "+ where +" ORDER BY a.keterangan,a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRiwayatSemuaDepoActionPerformed

    private void MnTIRiwayatPerDepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTIRiwayatPerDepoActionPerformed
        if (KdGudang.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih/isi dulu salah satu lokasi depo farmasi nya..!!!");
            btnBarang1.requestFocus();
        } else {
            where = "";
            if(TCari.getText().trim().equals("")){
                where = "";
            }else{
                where = "where a.nama_brng like '%"+ TCari.getText().trim() +"%'";
            }  
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_awal", Tgl1.getSelectedItem());
            param.put("tgl_akhir", Tgl2.getSelectedItem());
            Valid.MyReport("rptRiwayatTotalItemBarangMedisPerDepoNew.jasper", "report", "::[ Laporan Rekap Riwayat Total Item Obat/Alkes/BHP Per Depo ]::",
                    " select a.nama_brng,a.kode_sat,a.ralan as 'hrg_jual', d.stok_awal as 'stok_awal', ifnull(b.Masuk,0)+ifnull(c.Masuk,0) as 'stok_masuk', ifnull(b.keluar,0)+ifnull(c.keluar,0) as 'stok_keluar', "
                    + " d.stok_awal + (ifnull(b.Masuk,0)+ifnull(c.Masuk,0)) - (ifnull(b.keluar,0)+ifnull(c.keluar,0))as 'stok_akhir', (ifnull(b.keluar,0)+ifnull(c.keluar,0)) * a.ralan as 'total_nominal','-' as 'keterangan', a.nm_bangsal as 'unit_yg_mengeluarkan' from"
                    + " ((select r.kode_brng, d.nama_brng, d.kode_sat, d.ralan, b.nm_bangsal from riwayat_barang_medis r"
                    + " inner join databarang d on d.kode_brng = r.kode_brng"
                    + " inner join bangsal b on b.kd_bangsal = r.kd_bangsal"
                    + " where r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                    + " and r.kd_bangsal = '" + KdGudang.getText() + "' "
                    + " group by r.kode_brng"
                    + " ) as a"
                    + " left join"
                    + " (select r.kode_brng, round(sum(masuk)) Masuk, round(sum(keluar)) keluar from riwayat_barang_medis r"
                    + " where r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                    + " and r.kd_bangsal = '" + KdGudang.getText() + "' "
                    + " and r.posisi <> 'OPNAME' group by r.kode_brng"
                    + " ) as b on b.kode_brng = a.kode_brng"
                    + " left join"
                    + " (select r.kode_brng, sum(masuk) Masuk, round(sum(stok_awal)) keluar from riwayat_barang_medis r"
                    + " where r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                    + " and r.kd_bangsal = '" + KdGudang.getText() + "' "
                    + " and r.posisi = 'OPNAME' group by r.kode_brng"
                    + " ) as c on c.kode_brng = a.kode_brng"
                    + " left join"
                    + " (select r.kode_brng,round(r.stok_awal) stok_awal from riwayat_barang_medis r"
                    + " where r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                    + " and r.kd_bangsal = '" + KdGudang.getText() + "' "
                    + " GROUP BY r.kode_brng"
                    + " order by tanggal,jam"
                    + " ) as d on d.kode_brng = a.kode_brng"
                    + " ) "+ where +"  order by a.nama_brng", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnTIRiwayatPerDepoActionPerformed

    private void MnTIRiwayatSemuaDepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTIRiwayatSemuaDepoActionPerformed
        where = "";
            if(TCari.getText().trim().equals("")){
                where = "";
            }else{
                where = "where a.nama_brng like '%"+ TCari.getText().trim() +"%'";
            }
            
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Tgl1.getSelectedItem());
        param.put("tgl_akhir", Tgl2.getSelectedItem());
        Valid.MyReport("rptRiwayatTotalItemBarangMedisSemuaDepoNew.jasper", "report", "::[ Laporan Rekap Riwayat Total Item Obat/Alkes/BHP Semua Depo ]::",
                "SELECT a.nama_brng, "
                + "a.kode_sat, "
                + "a.ralan AS 'hrg_jual', "
                + "d.stok_awal AS 'stok_awal', "
                + "ifnull( b.Masuk, 0 )+ ifnull( c.Masuk, 0 ) AS 'stok_masuk', "
                + "ifnull( b.keluar, 0 )+ ifnull( c.keluar, 0 ) AS 'stok_keluar', "
                + "d.stok_awal + ( "
                + "	ifnull( b.Masuk, 0 )+ ifnull( c.Masuk, 0 )) - ( "
                + "ifnull( b.keluar, 0 )+ ifnull( c.keluar, 0 )) AS 'stok_akhir', "
                + "( "
                + "ifnull( b.keluar, 0 )+ ifnull( c.keluar, 0 )) * a.ralan AS 'total_nominal', "
                + "'-' AS 'keterangan', "
                + "a.nm_bangsal AS 'unit_yg_mengeluarkan'  "
                + "FROM "
                + "(( "
                + "	SELECT "
                + "		r.kode_brng, "
                + "		d.nama_brng, "
                + "		d.kode_sat, "
                + "		d.ralan, "
                + "		b.nm_bangsal,b.kd_bangsal  "
                + "	FROM "
                + "		riwayat_barang_medis r "
                + "		INNER JOIN databarang d ON d.kode_brng = r.kode_brng "
                + "		INNER JOIN bangsal b ON b.kd_bangsal = r.kd_bangsal  "
                + "	WHERE "
                + "		r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'  "
                + "		AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'  "
                + "	GROUP BY "
                + "		r.kode_brng,r.kd_bangsal  "
                + "		) AS a "
                + "	LEFT JOIN ( "
                + "	SELECT "
                + "		r.kode_brng, "
                + "		round( "
                + "		sum( masuk )) Masuk, "
                + "		round( "
                + "		sum( keluar )) keluar, r.kd_bangsal  "
                + "	FROM "
                + "		riwayat_barang_medis r "
                + "	WHERE "
                + "		r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'  "
                + "		AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'  "
                + "		AND r.posisi <> 'OPNAME'  "
                + "	GROUP BY "
                + "		r.kode_brng,r.kd_bangsal   "
                + "	) AS b ON b.kode_brng = a.kode_brng and b.kd_bangsal = a.kd_bangsal "
                + "	LEFT JOIN ( "
                + "	SELECT "
                + "		r.kode_brng, "
                + "		sum( masuk ) Masuk, "
                + "		round( "
                + "		sum( stok_awal )) keluar , r.kd_bangsal  "
                + "	FROM "
                + "		riwayat_barang_medis r  "
                + "	WHERE "
                + "		r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'  "
                + "		AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'  "
                + "		AND r.posisi = 'OPNAME'  "
                + "	GROUP BY "
                + "		r.kode_brng,r.kd_bangsal "
                + "	) AS c ON c.kode_brng = a.kode_brng and c.kd_bangsal = a.kd_bangsal "
                + "	LEFT JOIN ( "
                + "	SELECT "
                + "		r.kode_brng, "
                + "		round( r.stok_awal ) stok_awal , r.kd_bangsal  "
                + "	FROM "
                + "		riwayat_barang_medis r  "
                + "	WHERE "
                + "		r.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'  "
                + "		AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'  "
                + "	GROUP BY "
                + "		r.kode_brng , r.kd_bangsal  "
                + "	ORDER BY "
                + "		tanggal, "
                + "		jam  "
                + "	) AS d ON d.kode_brng = a.kode_brng and d.kd_bangsal = a.kd_bangsal "
                + ")  "
                + " "+ where +" ORDER BY "
                + "a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTIRiwayatSemuaDepoActionPerformed

    private void MnRiwayatPerDepo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerDepo1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl1.getSelectedItem() + ""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl2.getSelectedItem() + ""));
        Valid.MyReport("rptRekapRiwayatPerDepoJalan.jasper", "report", "::[ Laporan Rekap Total Riwayat Pemakaian Obat/Alkes/BHP Pasien Per Depo Rawat Jalan ]::",
                " select a.kode_brng, a.nama_brng, a.kode_sat, sum(a.jumlah) jumlah, a.harga, sum(a.total) total, a.nm_bangsal from "
                + " (select g.kode_brng, g.nama_brng, g.kode_sat, sum(d.jml) jumlah,d.biaya_obat harga,(sum(d.jml)*d.biaya_obat) total, d.`status`, b.nm_bangsal,b.kd_bangsal from detail_pemberian_obat d "
                + " inner join databarang g on g.kode_brng = d.kode_brng inner join bangsal b on b.kd_bangsal = d.kd_bangsal "
                + " where d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by g.nama_brng, d.biaya_obat, b.nm_bangsal, d.`status` "
                + " union ALL "
                + " select b.kode_brng, b.nama_brng, d.kode_sat,sum(d.jumlah) jumlah,d.h_jual,(d.h_jual*sum(d.jumlah)) as total, 'BEBAS' as status, 'APOTEK IGD' as nm_bangsal, 'APT01' as kd_bangsal from penjualan p "
                + " inner join detailjual d on d.nota_jual = p.nota_jual inner join databarang b on b.kode_brng = d.kode_brng "
                + " where p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by d.kode_brng) as a "
                + " where a.`status`='Ralan' and a.kd_bangsal ='" + KdGudang.getText() + "' GROUP BY a.kode_brng order by a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRiwayatPerDepo1ActionPerformed

    private void MnRiwayatPerDepo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerDepo2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl1.getSelectedItem() + ""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl2.getSelectedItem() + ""));
        Valid.MyReport("rptRekapRiwayatPerDepoInap.jasper", "report", "::[ Laporan Rekap Total Riwayat Pemakaian Obat/Alkes/BHP Pasien Per Depo Rawat Inap ]::",
                " select a.kode_brng, a.nama_brng, a.kode_sat, sum(a.jumlah) jumlah, a.harga, sum(a.total) total, a.nm_bangsal from "
                + " (select g.kode_brng, g.nama_brng, g.kode_sat, sum(d.jml) jumlah,d.biaya_obat harga,(sum(d.jml)*d.biaya_obat) total, d.`status`, b.nm_bangsal,b.kd_bangsal from detail_pemberian_obat d "
                + " inner join databarang g on g.kode_brng = d.kode_brng inner join bangsal b on b.kd_bangsal = d.kd_bangsal "
                + " where d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by g.nama_brng, d.biaya_obat, b.nm_bangsal, d.`status` "
                + " union ALL "
                + " select b.kode_brng, b.nama_brng, d.kode_sat,sum(d.jumlah) jumlah,d.h_jual,(d.h_jual*sum(d.jumlah)) as total, 'BEBAS' as status, 'APOTEK IGD' as nm_bangsal, 'APT01' as kd_bangsal from penjualan p "
                + " inner join detailjual d on d.nota_jual = p.nota_jual inner join databarang b on b.kode_brng = d.kode_brng "
                + " where p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by d.kode_brng) as a "
                + " where a.`status`='Ranap' and a.kd_bangsal ='" + KdGudang.getText() + "' GROUP BY a.kode_brng order by a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRiwayatPerDepo2ActionPerformed

    private void MnRiwayatPerDepo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerDepo3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl1.getSelectedItem() + ""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl2.getSelectedItem() + ""));
        Valid.MyReport("rptRekapRiwayatPerDepoSemua.jasper", "report", "::[ Laporan Rekap Total Riwayat Pemakaian Obat/Alkes/BHP Pasien Per Depo Semua Rawat ]::",
                " select a.kode_brng, a.nama_brng, a.kode_sat, sum(a.jumlah) jumlah, a.harga, sum(a.total) total, a.nm_bangsal from "
                + " (select g.kode_brng, g.nama_brng, g.kode_sat, sum(d.jml) jumlah,d.biaya_obat harga,(sum(d.jml)*d.biaya_obat) total, d.`status`, b.nm_bangsal,b.kd_bangsal from detail_pemberian_obat d "
                + " inner join databarang g on g.kode_brng = d.kode_brng inner join bangsal b on b.kd_bangsal = d.kd_bangsal "
                + " where d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by g.nama_brng, d.biaya_obat, b.nm_bangsal, d.`status` "
                + " union ALL "
                + " select b.kode_brng, b.nama_brng, d.kode_sat,sum(d.jumlah) jumlah,d.h_jual,(d.h_jual*sum(d.jumlah)) as total, 'BEBAS' as status, 'APOTEK IGD' as nm_bangsal, 'APT01' as kd_bangsal from penjualan p "
                + " inner join detailjual d on d.nota_jual = p.nota_jual inner join databarang b on b.kode_brng = d.kode_brng "
                + " where p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by d.kode_brng) as a "
                + " where a.`status` in ('Ranap','Ralan') and a.kd_bangsal ='" + KdGudang.getText() + "' GROUP BY a.kode_brng order by a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRiwayatPerDepo3ActionPerformed

    private void MnTIRiwayatSemuaDepo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTIRiwayatSemuaDepo1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl1.getSelectedItem() + ""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl2.getSelectedItem() + ""));
        Valid.MyReport("rptRekapRiwayatSemuaDepoJalan.jasper", "report", "::[ Laporan Rekap Total Riwayat Pemakaian Obat/Alkes/BHP Pasien Semua Depo Rawat Jalan ]::",
                " select a.kode_brng, a.nama_brng, a.kode_sat, sum(a.jumlah) jumlah, a.harga, sum(a.total) total, a.nm_bangsal from "
                + " (select g.kode_brng, g.nama_brng, g.kode_sat, sum(d.jml) jumlah,d.biaya_obat harga,(sum(d.jml)*d.biaya_obat) total, d.`status`, b.nm_bangsal,b.kd_bangsal from detail_pemberian_obat d "
                + " inner join databarang g on g.kode_brng = d.kode_brng inner join bangsal b on b.kd_bangsal = d.kd_bangsal "
                + " where d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by g.nama_brng, d.biaya_obat, b.nm_bangsal, d.`status` "
                + " union ALL "
                + " select b.kode_brng, b.nama_brng, d.kode_sat,sum(d.jumlah) jumlah,d.h_jual,(d.h_jual*sum(d.jumlah)) as total, 'BEBAS' as status, 'APOTEK IGD' as nm_bangsal, 'APT01' as kd_bangsal from penjualan p "
                + " inner join detailjual d on d.nota_jual = p.nota_jual inner join databarang b on b.kode_brng = d.kode_brng "
                + " where p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by d.kode_brng) as a "
                + " where a.`status`='Ralan' GROUP BY a.kode_brng order by a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTIRiwayatSemuaDepo1ActionPerformed

    private void MnTIRiwayatSemuaDepo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTIRiwayatSemuaDepo2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl1.getSelectedItem() + ""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl2.getSelectedItem() + ""));
        Valid.MyReport("rptRekapRiwayatSemuaDepoInap.jasper", "report", "::[ Laporan Rekap Total Riwayat Pemakaian Obat/Alkes/BHP Pasien Semua Depo Rawat Inap ]::",
                " select a.kode_brng, a.nama_brng, a.kode_sat, sum(a.jumlah) jumlah, a.harga, sum(a.total) total, a.nm_bangsal from "
                + " (select g.kode_brng, g.nama_brng, g.kode_sat, sum(d.jml) jumlah,d.biaya_obat harga,(sum(d.jml)*d.biaya_obat) total, d.`status`, b.nm_bangsal,b.kd_bangsal from detail_pemberian_obat d "
                + " inner join databarang g on g.kode_brng = d.kode_brng inner join bangsal b on b.kd_bangsal = d.kd_bangsal "
                + " where d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by g.nama_brng, d.biaya_obat, b.nm_bangsal, d.`status` "
                + " union ALL "
                + " select b.kode_brng, b.nama_brng, d.kode_sat,sum(d.jumlah) jumlah,d.h_jual,(d.h_jual*sum(d.jumlah)) as total, 'BEBAS' as status, 'APOTEK IGD' as nm_bangsal, 'APT01' as kd_bangsal from penjualan p "
                + " inner join detailjual d on d.nota_jual = p.nota_jual inner join databarang b on b.kode_brng = d.kode_brng "
                + " where p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by d.kode_brng) as a "
                + " where a.`status`='Ranap' GROUP BY a.kode_brng order by a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTIRiwayatSemuaDepo2ActionPerformed

    private void MnTIRiwayatSemuaDepo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTIRiwayatSemuaDepo3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl1.getSelectedItem() + ""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl2.getSelectedItem() + ""));
        Valid.MyReport("rptRekapRiwayatSemuaDepoSemuaRawat.jasper", "report", "::[ Laporan Rekap Total Riwayat Pemakaian Obat/Alkes/BHP Pasien Semua Depo Semua Rawat ]::",
                " select a.kode_brng, a.nama_brng, a.kode_sat, sum(a.jumlah) jumlah, a.harga, sum(a.total) total, a.nm_bangsal from "
                + " (select g.kode_brng, g.nama_brng, g.kode_sat, sum(d.jml) jumlah,d.biaya_obat harga,(sum(d.jml)*d.biaya_obat) total, d.`status`, b.nm_bangsal,b.kd_bangsal from detail_pemberian_obat d "
                + " inner join databarang g on g.kode_brng = d.kode_brng inner join bangsal b on b.kd_bangsal = d.kd_bangsal "
                + " where d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by g.nama_brng, d.biaya_obat, b.nm_bangsal, d.`status` "
                + " union ALL "
                + " select b.kode_brng, b.nama_brng, d.kode_sat,sum(d.jumlah) jumlah,d.h_jual,(d.h_jual*sum(d.jumlah)) as total, 'BEBAS' as status, 'APOTEK IGD' as nm_bangsal, 'APT01' as kd_bangsal from penjualan p "
                + " inner join detailjual d on d.nota_jual = p.nota_jual inner join databarang b on b.kode_brng = d.kode_brng "
                + " where p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by d.kode_brng) as a "
                + " where a.`status` in ('Ranap','Ralan') GROUP BY a.kode_brng order by a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTIRiwayatSemuaDepo3ActionPerformed

    private void MnRiwayatPerDepo0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerDepo0ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl1.getSelectedItem() + ""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl2.getSelectedItem() + ""));
        Valid.MyReport("rptRekapRiwayatPerDepoBebas.jasper", "report", "::[ Laporan Rekap Total Riwayat Pemakaian Obat/Alkes/BHP Pasien Per Depo Penjualan Bebas ]::",
                " select a.kode_brng, a.nama_brng, a.kode_sat, sum(a.jumlah) jumlah, a.harga, sum(a.total) total, a.nm_bangsal from "
                + " (select g.kode_brng, g.nama_brng, g.kode_sat, sum(d.jml) jumlah,d.biaya_obat harga,(sum(d.jml)*d.biaya_obat) total, d.`status`, b.nm_bangsal,b.kd_bangsal from detail_pemberian_obat d "
                + " inner join databarang g on g.kode_brng = d.kode_brng inner join bangsal b on b.kd_bangsal = d.kd_bangsal "
                + " where d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by g.nama_brng, d.biaya_obat, b.nm_bangsal, d.`status` "
                + " union ALL "
                + " select b.kode_brng, b.nama_brng, d.kode_sat,sum(d.jumlah) jumlah,d.h_jual,(d.h_jual*sum(d.jumlah)) as total, 'BEBAS' as status, 'APOTEK IGD' as nm_bangsal, 'APT01' as kd_bangsal from penjualan p "
                + " inner join detailjual d on d.nota_jual = p.nota_jual inner join databarang b on b.kode_brng = d.kode_brng "
                + " where p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by d.kode_brng) as a "
                + " where a.`status`='BEBAS' and a.kd_bangsal ='" + KdGudang.getText() + "' GROUP BY a.kode_brng order by a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRiwayatPerDepo0ActionPerformed

    private void MnTIRiwayatSemuaDepo0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTIRiwayatSemuaDepo0ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl1.getSelectedItem() + ""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl2.getSelectedItem() + ""));
        Valid.MyReport("rptRekapRiwayatSemuaDepoBebas.jasper", "report", "::[ Laporan Rekap Total Riwayat Pemakaian Obat/Alkes/BHP Pasien Semua Depo Penjualan Bebas ]::",
                " select a.kode_brng, a.nama_brng, a.kode_sat, sum(a.jumlah) jumlah, a.harga, sum(a.total) total, a.nm_bangsal from "
                + " (select g.kode_brng, g.nama_brng, g.kode_sat, sum(d.jml) jumlah,d.biaya_obat harga,(sum(d.jml)*d.biaya_obat) total, d.`status`, b.nm_bangsal,b.kd_bangsal from detail_pemberian_obat d "
                + " inner join databarang g on g.kode_brng = d.kode_brng inner join bangsal b on b.kd_bangsal = d.kd_bangsal "
                + " where d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by g.nama_brng, d.biaya_obat, b.nm_bangsal, d.`status` "
                + " union ALL "
                + " select b.kode_brng, b.nama_brng, d.kode_sat,sum(d.jumlah) jumlah,d.h_jual,(d.h_jual*sum(d.jumlah)) as total, 'BEBAS' as status, 'APOTEK IGD' as nm_bangsal, 'APT01' as kd_bangsal from penjualan p "
                + " inner join detailjual d on d.nota_jual = p.nota_jual inner join databarang b on b.kode_brng = d.kode_brng "
                + " where p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' group by d.kode_brng) as a "
                + " where a.`status`='BEBAS' GROUP BY a.kode_brng order by a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTIRiwayatSemuaDepo0ActionPerformed

    private void MnLapPakaiBekalFarmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapPakaiBekalFarmasiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Pemakaian Tgl. " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        Valid.MyReport("rptRencanaFarmasi.jasper", "report", "::[ Laporan Rekap Pemakaian Perbekalan Farmasi ]::",
                " SELECT a.kode_brng, a.nama_brng, IFNULL(b.h_beli,ifnull(c.h_beli,IFNULL(d.h_beli,IFNULL(e.h_beli,IFNULL(f.h_beli,IFNULL(g.h_beli, 0)))))) AS 'Harga Beli', "
                + "IFNULL(b.jumlah, 0) AS 'R_INAP SENTRAL', "
                + "IFNULL(c.jumlah, 0) AS 'R_INAP IGD', "
                + "IFNULL(d.jumlah, 0) AS 'IBS', "
                + "IFNULL(e.jumlah, 0) AS 'R_JALAN SENTRAL', "
                + "IFNULL(f.jumlah, 0) AS 'R_JALAN IGD', "
                + "IFNULL(g.jumlah, 0) AS 'IGD JUAL BEBAS', "
                + "IFNULL(b.jumlah, 0) + IFNULL(c.jumlah, 0) + IFNULL(d.jumlah, 0) AS 'TOTAL R_INAP', "
                + "IFNULL(e.jumlah, 0) + IFNULL(f.jumlah, 0) + IFNULL(g.jumlah, 0) AS 'TOTAL R_JALAN', "
                + "IFNULL(b.jumlah, 0) + IFNULL(c.jumlah, 0) + IFNULL(d.jumlah, 0) + IFNULL(e.jumlah, 0) + IFNULL(f.jumlah, 0) + IFNULL(g.jumlah, 0) AS 'JUMLAH RI_RJ' "
                + "FROM ((SELECT kode_brng, nama_brng FROM databarang) AS a LEFT JOIN (SELECT d.kode_brng, d.h_beli, sum(d.jml) jumlah FROM detail_pemberian_obat d "
                + "WHERE d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND d. STATUS = 'RANAP' AND d.kd_bangsal = 'APT02' GROUP BY d.kode_brng) AS b ON b.kode_brng = a.kode_brng "
                + "LEFT JOIN (SELECT d.kode_brng, d.h_beli, sum(d.jml) jumlah FROM detail_pemberian_obat d WHERE d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND d. STATUS = 'RANAP' "
                + "AND d.kd_bangsal = 'APT01' GROUP BY d.kode_brng) AS c ON c.kode_brng = a.kode_brng LEFT JOIN (SELECT d.kode_brng, d.h_beli, sum(d.jml) jumlah FROM detail_pemberian_obat d "
                + "WHERE d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND d.kd_bangsal = 'APT07' GROUP BY d.kode_brng) AS d ON d.kode_brng = a.kode_brng "
                + "LEFT JOIN (SELECT d.kode_brng, d.h_beli, sum(d.jml) jumlah FROM detail_pemberian_obat d WHERE d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND d. STATUS = 'RALAN' "
                + "AND d.kd_bangsal = 'APT02' GROUP BY d.kode_brng) AS e ON e.kode_brng = a.kode_brng LEFT JOIN (SELECT d.kode_brng, d.h_beli, sum(d.jml) jumlah FROM detail_pemberian_obat d "
                + "WHERE d.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND d. STATUS = 'RALAN' AND d.kd_bangsal = 'APT01' GROUP BY d.kode_brng) AS f ON f.kode_brng = a.kode_brng "
                + "LEFT JOIN (SELECT b.kode_brng, b.nama_brng, sum(d.jumlah) jumlah, d.h_beli FROM penjualan p INNER JOIN detailjual d ON d.nota_jual = p.nota_jual "
                + "INNER JOIN databarang b ON b.kode_brng = d.kode_brng WHERE p.tgl_jual BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY d.kode_brng) AS g ON g.kode_brng = a.kode_brng) "
//                + "Where IFNULL(b.jumlah, 0) > 0 OR IFNULL(c.jumlah, 0) > 0 OR IFNULL(d.jumlah, 0) > 0 OR IFNULL(e.jumlah, 0) > 0 OR IFNULL(f.jumlah, 0) > 0 OR IFNULL(g.jumlah, 0) > 0 "
                + "ORDER BY a.nama_brng", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapPakaiBekalFarmasiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRiwayatBarangMedis dialog = new DlgRiwayatBarangMedis(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private widget.TextBox Kd2;
    private widget.TextBox KdGudang;
    private javax.swing.JMenuItem MnLapPakaiBekalFarmasi;
    private javax.swing.JMenu MnLapRiwayat;
    private javax.swing.JMenu MnLapRiwayat1;
    private javax.swing.JMenuItem MnRiwayatPerDepo;
    private javax.swing.JMenuItem MnRiwayatPerDepo0;
    private javax.swing.JMenuItem MnRiwayatPerDepo1;
    private javax.swing.JMenuItem MnRiwayatPerDepo2;
    private javax.swing.JMenuItem MnRiwayatPerDepo3;
    private javax.swing.JMenuItem MnRiwayatSemuaDepo;
    private javax.swing.JMenuItem MnTIRiwayatPerDepo;
    private javax.swing.JMenuItem MnTIRiwayatSemuaDepo;
    private javax.swing.JMenuItem MnTIRiwayatSemuaDepo0;
    private javax.swing.JMenuItem MnTIRiwayatSemuaDepo1;
    private javax.swing.JMenuItem MnTIRiwayatSemuaDepo2;
    private javax.swing.JMenuItem MnTIRiwayatSemuaDepo3;
    private widget.TextBox NmGudang;
    private widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnBarang;
    private widget.Button btnBarang1;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdbar;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label9;
    private widget.TextBox nmbar;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select riwayat_barang_medis.kode_brng,databarang.nama_brng,"
                    + "riwayat_barang_medis.stok_awal,riwayat_barang_medis.masuk,"
                    + "riwayat_barang_medis.keluar,riwayat_barang_medis.stok_akhir,"
                    + "riwayat_barang_medis.posisi,riwayat_barang_medis.tanggal,"
                    + "riwayat_barang_medis.jam,riwayat_barang_medis.petugas,"
                    + "riwayat_barang_medis.kd_bangsal,bangsal.nm_bangsal,"
                    + "riwayat_barang_medis.status from riwayat_barang_medis "
                    + "inner join bangsal inner join databarang on "
                    + "riwayat_barang_medis.kode_brng=databarang.kode_brng and "
                    + "riwayat_barang_medis.kd_bangsal=bangsal.kd_bangsal where "
                    + "riwayat_barang_medis.tanggal between ? and ? and databarang.nama_brng like ? and bangsal.nm_bangsal like ? and riwayat_barang_medis.kode_brng like ? or "
                    + "riwayat_barang_medis.tanggal between ? and ? and databarang.nama_brng like ? and bangsal.nm_bangsal like ? and databarang.nama_brng like ? or "
                    + "riwayat_barang_medis.tanggal between ? and ? and databarang.nama_brng like ? and bangsal.nm_bangsal like ? and riwayat_barang_medis.petugas like ? or "
                    + "riwayat_barang_medis.tanggal between ? and ? and databarang.nama_brng like ? and bangsal.nm_bangsal like ? and bangsal.nm_bangsal like ? or "
                    + "riwayat_barang_medis.tanggal between ? and ? and databarang.nama_brng like ? and bangsal.nm_bangsal like ? and riwayat_barang_medis.kd_bangsal like ? or "
                    + "riwayat_barang_medis.tanggal between ? and ? and databarang.nama_brng like ? and bangsal.nm_bangsal like ? and riwayat_barang_medis.status like ? order by riwayat_barang_medis.tanggal,riwayat_barang_medis.jam ");
            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + nmbar.getText() + "%");
                ps.setString(4, "%" + NmGudang.getText() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(7, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(8, "%" + nmbar.getText() + "%");
                ps.setString(9, "%" + NmGudang.getText() + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");
                ps.setString(11, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(12, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(13, "%" + nmbar.getText() + "%");
                ps.setString(14, "%" + NmGudang.getText() + "%");
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(18, "%" + nmbar.getText() + "%");
                ps.setString(19, "%" + NmGudang.getText() + "%");
                ps.setString(20, "%" + TCari.getText().trim() + "%");
                ps.setString(21, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(22, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(23, "%" + nmbar.getText() + "%");
                ps.setString(24, "%" + NmGudang.getText() + "%");
                ps.setString(25, "%" + TCari.getText().trim() + "%");
                ps.setString(26, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(27, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(28, "%" + nmbar.getText() + "%");
                ps.setString(29, "%" + NmGudang.getText() + "%");
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("kode_brng") + " " + rs.getString("nama_brng"),
                        rs.getString("stok_awal"), rs.getString("masuk"),
                        rs.getString("keluar"), rs.getString("stok_akhir"),
                        rs.getString("posisi"), rs.getString("tanggal"),
                        rs.getString("jam"), rs.getString("petugas"),
                        rs.getString("kd_bangsal") + " " + rs.getString("nm_bangsal"),
                        rs.getString("status")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Data Barang : " + e);
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

    }

    public void isCek() {
        BtnPrint.setEnabled(akses.getsirkulasi_obat());
    }

}
