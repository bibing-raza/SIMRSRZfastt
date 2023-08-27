/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package inventory;

import fungsi.WarnaTable;
import fungsi.WarnaTable2;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgStokOpname;
import inventory.riwayatobat;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import simrskhanza.DlgCariBangsal;

public class DlgInputStok extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private riwayatobat Trackobat = new riwayatobat();
    private Connection koneksi = koneksiDB.condb();
    private Jurnal jur = new Jurnal();
    private PreparedStatement pstampil, psstok, psinfo;
    private ResultSet rstampil, rsstok, rsinfo, rsprint;
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);
    private double ttl = 0, y = 0, stokbarang = 0, kurang = 0;
    private int jml = 0, i = 0, index = 0, cek = 0;
    private String[] real, kodebarang, namabarang, kategori, satuan;
    private double[] hargabeli, stok, selisih, nomihilang;
    private WarnaTable2 warna = new WarnaTable2();
    private String dialog_simpan = "";

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgInputStok(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"Real",
            "Kode Barang",
            "Nama Barang",
            "Kategori",
            "Satuan",
            "Harga",
            "Stok",
            "Selisih",
            "Nominal Hilang(Rp)"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Double.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbStokOpname.setModel(tabMode);

        tbStokOpname.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbStokOpname.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbStokOpname.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(42);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(42);
            } else if (i == 7) {
                column.setPreferredWidth(42);
            } else if (i == 8) {
                column.setPreferredWidth(105);
            }
        }
        warna.kolom = 0;
        tbStokOpname.setDefaultRenderer(Object.class, warna);

        tabMode1 = new DefaultTableModel(null, new String[]{
            "Kode Barang", "Nama Barang/Obat", "Satuan", "Harga (Rp.)", "Nama Gudang", "Stok (Qty.)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbInfoStok.setModel(tabMode1);
        tbInfoStok.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbInfoStok.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbInfoStok.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(200);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            }
        }
        tbInfoStok.setDefaultRenderer(Object.class, new WarnaTable());

        kdgudang.setDocument(new batasInput((byte) 5).getKata(kdgudang));
        catatan.setDocument(new batasInput((byte) 60).getKata(catatan));
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

        TCari.requestFocus();

        bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (bangsal.getTable().getSelectedRow() != -1) {
                    kdgudang.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 0).toString());
                    nmgudang.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 1).toString());
                    tampil();
                }
                kdgudang.requestFocus();
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
        try {
            pstampil = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat, "
                    + "databarang.h_beli from databarang inner join jenis on databarang.kdjns=jenis.kdjns "
                    + " where databarang.status='1' and databarang.kode_brng like ? or "
                    + " databarang.status='1' and databarang.nama_brng like ? or "
                    + " databarang.status='1' and databarang.kode_sat like ? or "
                    + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
            psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
        } catch (SQLException e) {
            System.out.println(e);
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

        Kd2 = new widget.TextBox();
        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppStok = new javax.swing.JMenuItem();
        Popup1 = new javax.swing.JPopupMenu();
        ppExportFile = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        label18 = new widget.Label();
        catatan = new widget.TextBox();
        label11 = new widget.Label();
        Tgl = new widget.Tanggal();
        label21 = new widget.Label();
        kdgudang = new widget.TextBox();
        nmgudang = new widget.TextBox();
        BtnGudang = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        scrollPane1 = new widget.ScrollPane();
        tbStokOpname = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbInfoStok = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        label13 = new widget.Label();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi5 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LTotal = new widget.Label();

        Kd2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 255));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(0, 0, 0));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Jumlah");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setIconTextGap(8);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        ppStok.setBackground(new java.awt.Color(255, 255, 255));
        ppStok.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppStok.setForeground(new java.awt.Color(0, 0, 0));
        ppStok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppStok.setText("Tampilkan Semua Stok");
        ppStok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppStok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppStok.setIconTextGap(8);
        ppStok.setName("ppStok"); // NOI18N
        ppStok.setPreferredSize(new java.awt.Dimension(200, 25));
        ppStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppStokActionPerformed(evt);
            }
        });
        Popup.add(ppStok);

        Popup1.setName("Popup1"); // NOI18N

        ppExportFile.setBackground(new java.awt.Color(255, 255, 255));
        ppExportFile.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppExportFile.setForeground(new java.awt.Color(0, 0, 0));
        ppExportFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        ppExportFile.setText("Export Data Ke Excel");
        ppExportFile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppExportFile.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppExportFile.setIconTextGap(8);
        ppExportFile.setName("ppExportFile"); // NOI18N
        ppExportFile.setPreferredSize(new java.awt.Dimension(200, 25));
        ppExportFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppExportFileActionPerformed(evt);
            }
        });
        Popup1.add(ppExportFile);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Stok Opname Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(89, 74));
        panelisi3.setLayout(null);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("Keterangan :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label18);
        label18.setBounds(0, 40, 110, 23);

        catatan.setForeground(new java.awt.Color(0, 0, 0));
        catatan.setName("catatan"); // NOI18N
        catatan.setPreferredSize(new java.awt.Dimension(207, 23));
        catatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                catatanKeyPressed(evt);
            }
        });
        panelisi3.add(catatan);
        catatan.setBounds(114, 40, 528, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal Opname :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label11);
        label11.setBounds(0, 10, 110, 23);

        Tgl.setEditable(false);
        Tgl.setDisplayFormat("dd-MM-yyyy");
        Tgl.setName("Tgl"); // NOI18N
        Tgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKeyPressed(evt);
            }
        });
        panelisi3.add(Tgl);
        Tgl.setBounds(114, 10, 95, 23);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Lokasi :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label21);
        label21.setBounds(236, 10, 90, 23);

        kdgudang.setEditable(false);
        kdgudang.setForeground(new java.awt.Color(0, 0, 0));
        kdgudang.setName("kdgudang"); // NOI18N
        kdgudang.setPreferredSize(new java.awt.Dimension(80, 23));
        kdgudang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdgudangKeyPressed(evt);
            }
        });
        panelisi3.add(kdgudang);
        kdgudang.setBounds(329, 10, 80, 23);

        nmgudang.setEditable(false);
        nmgudang.setForeground(new java.awt.Color(0, 0, 0));
        nmgudang.setName("nmgudang"); // NOI18N
        nmgudang.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmgudang);
        nmgudang.setBounds(412, 10, 200, 23);

        BtnGudang.setForeground(new java.awt.Color(0, 0, 0));
        BtnGudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnGudang.setMnemonic('2');
        BtnGudang.setToolTipText("Alt+2");
        BtnGudang.setName("BtnGudang"); // NOI18N
        BtnGudang.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGudangActionPerformed(evt);
            }
        });
        panelisi3.add(BtnGudang);
        BtnGudang.setBounds(614, 10, 28, 23);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        scrollPane1.setComponentPopupMenu(Popup);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbStokOpname.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbStokOpname.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbStokOpname.setComponentPopupMenu(Popup);
        tbStokOpname.setName("tbStokOpname"); // NOI18N
        tbStokOpname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStokOpnameMouseClicked(evt);
            }
        });
        tbStokOpname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbStokOpnameKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbStokOpname);

        TabRawat.addTab("Stok Opname Obat, Alkes & BHP Medis", scrollPane1);

        scrollPane2.setComponentPopupMenu(Popup1);
        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbInfoStok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbInfoStok.setToolTipText("");
        tbInfoStok.setComponentPopupMenu(Popup1);
        tbInfoStok.setName("tbInfoStok"); // NOI18N
        scrollPane2.setViewportView(tbInfoStok);

        TabRawat.addTab("Informasi Stok Obat", scrollPane2);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
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

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(327, 26));
        panelisi1.add(label13);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari.setMnemonic('C');
        BtnCari.setText("Cari");
        BtnCari.setToolTipText("Alt+C");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(100, 30));
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

        jPanel1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 54));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi5.add(label9);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi5.add(TCari);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('T');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+T");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
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
        panelisi5.add(BtnCari1);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
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
        panelisi5.add(BtnAll);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Total :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi5.add(label10);

        LTotal.setForeground(new java.awt.Color(0, 0, 0));
        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(125, 23));
        panelisi5.add(LTotal);

        jPanel1.add(panelisi5, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbStokOpnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStokOpnameMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbStokOpnameMouseClicked

    private void tbStokOpnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbStokOpnameKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    getData();
                    TCari.setText("");
                    TCari.requestFocus();
                } catch (java.lang.NullPointerException e) {
                }
            } else if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN) || (evt.getKeyCode() == KeyEvent.VK_RIGHT)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                try {
                    if (tbStokOpname.getSelectedColumn() == 0) {
                        tbStokOpname.setValueAt("", tbStokOpname.getSelectedRow(), 0);
                        tbStokOpname.setValueAt(0, tbStokOpname.getSelectedRow(), 6);
                        tbStokOpname.setValueAt(0, tbStokOpname.getSelectedRow(), 7);
                        tbStokOpname.setValueAt(0, tbStokOpname.getSelectedRow(), 8);
                    }
                } catch (Exception e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbStokOpnameKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgStokOpname opname = new DlgStokOpname(null, true);
        opname.isCek();
        opname.emptTeks();
        opname.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        opname.setLocationRelativeTo(internalFrame1);
        opname.setAlwaysOnTop(false);
        opname.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed
    /*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (nmgudang.getText().trim().equals("") || kdgudang.getText().trim().equals("")) {
            Valid.textKosong(kdgudang, "Gudang");
        } else if (catatan.getText().trim().equals("")) {
            Valid.textKosong(catatan, "Keterangan");
        } else if (tbStokOpname.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data kosong..!!!!");
            tbStokOpname.requestFocus();
        } else {
            i = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data akan disimpan ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                Sequel.AutoComitFalse();
                for (i = 0; i < tbStokOpname.getRowCount(); i++) {
                    if (!tbStokOpname.getValueAt(i, 0).toString().equals("")) {
                        try {
                            if (Valid.SetAngka(tbStokOpname.getValueAt(i, 0).toString()) >= 0) {
                                if (Sequel.menyimpantf("opname", "?,?,?,?,?,?,?,?,?", "Stok Opname", 9, new String[]{
                                    tbStokOpname.getValueAt(i, 1).toString(), tbStokOpname.getValueAt(i, 5).toString(), Valid.SetTgl(Tgl.getSelectedItem() + ""), tbStokOpname.getValueAt(i, 6).toString(),
                                    tbStokOpname.getValueAt(i, 0).toString(), tbStokOpname.getValueAt(i, 7).toString(), tbStokOpname.getValueAt(i, 8).toString(), catatan.getText(), kdgudang.getText()}) == true) {
                                    Trackobat.catatRiwayat2(tbStokOpname.getValueAt(i, 1).toString(), Valid.SetAngka(tbStokOpname.getValueAt(i, 0).toString()), 0, "Opname", akses.getkode(), kdgudang.getText(), "Simpan");
                                    Sequel.menyimpan("gudangbarang", "'" + tbStokOpname.getValueAt(i, 1).toString() + "','" + kdgudang.getText() + "','" + tbStokOpname.getValueAt(i, 0).toString() + "'",
                                            "stok='" + tbStokOpname.getValueAt(i, 0).toString() + "'", "kode_brng='" + tbStokOpname.getValueAt(i, 1).toString() + "' and kd_bangsal='" + kdgudang.getText() + "'");
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                Sequel.AutoComitTrue();
                for (index = 0; index < tbStokOpname.getRowCount(); index++) {
                    tbStokOpname.setValueAt("", index, 0);
                }
                tampil();
            }

        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, kdgudang, BtnCari);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        BtnCari1ActionPerformed(null);
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnCari1.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        BtnKeluar.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        tbStokOpname.requestFocus();
    }
}//GEN-LAST:event_TCariKeyPressed

private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
    if (TabRawat.getSelectedIndex() == 0) {
        tampil();
    } else {
        tampilStok();
    }
}//GEN-LAST:event_BtnCari1ActionPerformed

private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        tampil();
    } else {
        Valid.pindah(evt, TCari, BtnSimpan);
    }
}//GEN-LAST:event_BtnCari1KeyPressed

private void catatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_catatanKeyPressed
    Valid.pindah(evt, kdgudang, BtnSimpan);
}//GEN-LAST:event_catatanKeyPressed

private void TglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKeyPressed
    Valid.pindah(evt, TCari, kdgudang);
}//GEN-LAST:event_TglKeyPressed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
    int row2 = tbStokOpname.getRowCount();
    for (int r = 0; r < row2; r++) {
        tbStokOpname.setValueAt("", r, 0);
        tbStokOpname.setValueAt(0, r, 6);
        tbStokOpname.setValueAt(0, r, 7);
        tbStokOpname.setValueAt(0, r, 8);
    }
}//GEN-LAST:event_ppBersihkanActionPerformed

private void kdgudangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdgudangKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmgudang, kdgudang.getText());
        tampil();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmgudang, kdgudang.getText());
        tampil();
        Tgl.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmgudang, kdgudang.getText());
        tampil();
        BtnSimpan.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnGudangActionPerformed(null);
    }
}//GEN-LAST:event_kdgudangKeyPressed

private void BtnGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGudangActionPerformed
    bangsal.isCek();
    bangsal.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    bangsal.setLocationRelativeTo(internalFrame1);
    bangsal.setAlwaysOnTop(false);
    bangsal.setVisible(true);
}//GEN-LAST:event_BtnGudangActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.insertClosingStok();
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void ppStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppStokActionPerformed
        for (i = 0; i < tbStokOpname.getRowCount(); i++) {
            try {
                stokbarang = 0;
                psstok.setString(1, kdgudang.getText());
                psstok.setString(2, tbStokOpname.getValueAt(i, 1).toString());
                rsstok = psstok.executeQuery();
                if (rsstok.next()) {
                    stokbarang = rsstok.getDouble(1);
                }
                tbStokOpname.setValueAt(stokbarang, i, 6);
            } catch (Exception e) {
                tbStokOpname.setValueAt(0, i, 6);
            }
        }
    }//GEN-LAST:event_ppStokActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        BtnCari1ActionPerformed(null);
    }//GEN-LAST:event_TabRawatMouseClicked

    private void ppExportFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppExportFileActionPerformed
        try {
            cek = Sequel.cariInteger("select DATE_FORMAT(NOW(),'%Y-%m-%d') = '" + Valid.SetTgl(Tgl.getSelectedItem() + "") + "'");
            if (nmgudang.getText().trim().equals("") || kdgudang.getText().trim().equals("")) {
                Valid.textKosong(kdgudang, "Gudang");
            } else {
                if (cek == 1) {
                    psinfo = koneksi.prepareStatement("select d.kode_brng as 'Kode', d.nama_brng as 'Nama',d.kode_sat as 'Satuan', d.h_beli 'Harga', ifnull(b.nm_bangsal,'" + nmgudang.getText() + "') as 'Gudang', "
                            + "ifnull(g.stok,0) as 'Stok' from databarang d left join gudangbarang g on g.kode_brng = d.kode_brng and g.kd_bangsal = '" + kdgudang.getText() + "' "
                            + "left join bangsal b on b.kd_bangsal = g.kd_bangsal "
                            + "where d.status = '1' and g.kd_bangsal like ? or "
                            + "d.status = '1' and d.nama_brng like ? or	"
                            + "d.status = '1' and b.nm_bangsal like ? order by d.nama_brng ");
                } else {
                    psinfo = koneksi.prepareStatement("select d.kode_brng as 'Kode', d.nama_brng as 'Nama',d.kode_sat as 'Satuan', d.h_beli 'Harga', ifnull(b.nm_bangsal,'" + nmgudang.getText() + "') as 'Gudang', "
                            + "ifnull(g.stok_akhir,0) as 'Stok' from databarang d left join stok_harian g on g.kode_brng = d.kode_brng and g.kd_bangsal = '" + kdgudang.getText() + "' and g.tanggal = '" + Valid.SetTgl(Tgl.getSelectedItem() + "") + "' "
                            + "left join bangsal b on b.kd_bangsal = g.kd_bangsal "
                            + "where d.status = '1' and g.kd_bangsal like ? or "
                            + "d.status = '1' and d.nama_brng like ? or	"
                            + "d.status = '1' and b.nm_bangsal like ? order by d.nama_brng ");
                }
            }
            psinfo.setString(1, "%" + TCari.getText().trim() + "%");
            psinfo.setString(2, "%" + TCari.getText().trim() + "%");
            psinfo.setString(3, "%" + TCari.getText().trim() + "%");
            
            rsprint = psinfo.executeQuery();
            dialog_simpan = "";
            dialog_simpan = Valid.openDialog();
            if (!dialog_simpan.equals("the user cancelled the operation")) {
                if (Valid.MyReportToExcelBooleanRs(rsprint, dialog_simpan) == true) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diexport menjadi file excel,..!!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal diexport menjadi file excel,..!!!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }//GEN-LAST:event_ppExportFileActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        BtnCari1ActionPerformed(null);
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgInputStok dialog = new DlgInputStok(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnGudang;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.TextBox Kd2;
    private widget.Label LTotal;
    private javax.swing.JPopupMenu Popup;
    private javax.swing.JPopupMenu Popup1;
    private widget.TextBox TCari;
    public javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl;
    private widget.TextBox catatan;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel1;
    private widget.TextBox kdgudang;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label13;
    private widget.Label label18;
    private widget.Label label21;
    private widget.Label label9;
    private widget.TextBox nmgudang;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi5;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppExportFile;
    private javax.swing.JMenuItem ppStok;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbInfoStok;
    private widget.Table tbStokOpname;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        jml = 0;
        for (i = 0; i < tbStokOpname.getRowCount(); i++) {
            if (!tbStokOpname.getValueAt(i, 0).toString().equals("")) {
                jml++;
            }
        }
        real = new String[jml];
        kodebarang = new String[jml];
        namabarang = new String[jml];
        kategori = new String[jml];
        satuan = new String[jml];
        hargabeli = new double[jml];
        stok = new double[jml];
        selisih = new double[jml];
        nomihilang = new double[jml];

        index = 0;
        for (i = 0; i < tbStokOpname.getRowCount(); i++) {
            if (!tbStokOpname.getValueAt(i, 0).toString().equals("")) {
                real[index] = tbStokOpname.getValueAt(i, 0).toString();
                kodebarang[index] = tbStokOpname.getValueAt(i, 1).toString();
                namabarang[index] = tbStokOpname.getValueAt(i, 2).toString();
                kategori[index] = tbStokOpname.getValueAt(i, 3).toString();
                satuan[index] = tbStokOpname.getValueAt(i, 4).toString();
                hargabeli[index] = Double.parseDouble(tbStokOpname.getValueAt(i, 5).toString());
                stok[index] = Double.parseDouble(tbStokOpname.getValueAt(i, 6).toString());
                selisih[index] = Double.parseDouble(tbStokOpname.getValueAt(i, 7).toString());
                nomihilang[index] = Double.parseDouble(tbStokOpname.getValueAt(i, 8).toString());
                index++;
            }
        }

        Valid.tabelKosong(tabMode);
        for (i = 0; i < jml; i++) {
            tabMode.addRow(new Object[]{
                real[i], kodebarang[i], namabarang[i], kategori[i], satuan[i],
                hargabeli[i], stok[i], selisih[i], nomihilang[i]
            });
        }
        try {
            pstampil.setString(1, "%" + TCari.getText().trim() + "%");
            pstampil.setString(2, "%" + TCari.getText().trim() + "%");
            pstampil.setString(3, "%" + TCari.getText().trim() + "%");
            pstampil.setString(4, "%" + TCari.getText().trim() + "%");
            rstampil = pstampil.executeQuery();
            while (rstampil.next()) {
                tabMode.addRow(new Object[]{"", rstampil.getString("kode_brng"),
                    rstampil.getString("nama_brng"),
                    rstampil.getString("nama"),
                    rstampil.getString("kode_sat"),
                    rstampil.getDouble("h_beli"), 0, 0, 0});
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
            LTotal.setText("" + tabMode.getRowCount());
    }

    private void tampilStok() {
        Valid.tabelKosong(tabMode1);
        try {
            cek = Sequel.cariInteger("select DATE_FORMAT(NOW(),'%Y-%m-%d') = '" + Valid.SetTgl(Tgl.getSelectedItem() + "") + "'");
            if (nmgudang.getText().trim().equals("") || kdgudang.getText().trim().equals("")) {
                Valid.textKosong(kdgudang, "Gudang");
            } else {
                if (cek == 1) {
                    psinfo = koneksi.prepareStatement("select d.kode_brng, d.nama_brng,d.kode_sat, format(d.h_beli,0) h_beli, ifnull(b.nm_bangsal,'" + nmgudang.getText() + "') nm_bangsal, "
                            + "format(ifnull(g.stok,0),0) stok from databarang d left join gudangbarang g on g.kode_brng = d.kode_brng and g.kd_bangsal = '" + kdgudang.getText() + "' "
                            + "left join bangsal b on b.kd_bangsal = g.kd_bangsal "
                            + "where d.status = '1' and g.kd_bangsal like ? or "
                            + "d.status = '1' and d.nama_brng like ? or	"
                            + "d.status = '1' and b.nm_bangsal like ? order by d.nama_brng ");
                } else {
                    psinfo = koneksi.prepareStatement("select d.kode_brng, d.nama_brng,d.kode_sat, format(d.h_beli,0) h_beli, ifnull(b.nm_bangsal,'" + nmgudang.getText() + "') nm_bangsal, "
                            + "format(ifnull(g.stok_akhir,0),0) stok from databarang d left join stok_harian g on g.kode_brng = d.kode_brng and g.kd_bangsal = '" + kdgudang.getText() + "' and g.tanggal = '" + Valid.SetTgl(Tgl.getSelectedItem() + "") + "' "
                            + "left join bangsal b on b.kd_bangsal = g.kd_bangsal "
                            + "where d.status = '1' and g.kd_bangsal like ? or "
                            + "d.status = '1' and d.nama_brng like ? or	"
                            + "d.status = '1' and b.nm_bangsal like ? order by d.nama_brng ");
                }
            }
            psinfo.setString(1, "%" + TCari.getText().trim() + "%");
            psinfo.setString(2, "%" + TCari.getText().trim() + "%");
            psinfo.setString(3, "%" + TCari.getText().trim() + "%");
            rsinfo = psinfo.executeQuery();

            while (rsinfo.next()) {
                tabMode1.addRow(new Object[]{rsinfo.getString("kode_brng"),
                    rsinfo.getString("nama_brng"),
                    rsinfo.getString("kode_sat"),
                    rsinfo.getString("h_beli"),
                    rsinfo.getString("nm_bangsal"),
                    rsinfo.getString("stok")});
            }

        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
            LTotal.setText("" + tabMode1.getRowCount());
    }

    private void getData() {
        i = tbStokOpname.getSelectedRow();
        if (nmgudang.getText().trim().equals("")) {
            Valid.textKosong(kdgudang, "Lokasi");
            for (index = 0; index < tbStokOpname.getRowCount(); index++) {
                tbStokOpname.setValueAt("", index, 0);
            }
        } else if (i != -1) {
            if (tbStokOpname.getSelectedColumn() == 1) {
                try {
                    if (!tbStokOpname.getValueAt(i, 0).toString().equals("")) {
                        stokbarang = 0;
                        psstok.setString(1, kdgudang.getText());
                        psstok.setString(2, tbStokOpname.getValueAt(i, 1).toString());
                        rsstok = psstok.executeQuery();
                        if (rsstok.next()) {
                            stokbarang = rsstok.getDouble(1);
                        }
                        tbStokOpname.setValueAt(stokbarang, i, 6);
                        try {
                            kurang = Double.parseDouble(tbStokOpname.getValueAt(tbStokOpname.getSelectedRow(), 6).toString())
                                    - Double.parseDouble(tbStokOpname.getValueAt(tbStokOpname.getSelectedRow(), 0).toString());
                        } catch (Exception e) {
                            kurang = 0;
                        }

                        if (kurang > 0) {
                            tbStokOpname.setValueAt(kurang, tbStokOpname.getSelectedRow(), 7);
                        } else {
                            tbStokOpname.setValueAt(0, tbStokOpname.getSelectedRow(), 7);
                        }

                        if (kurang > 0) {
                            tbStokOpname.setValueAt(kurang * Double.parseDouble(tbStokOpname.getValueAt(tbStokOpname.getSelectedRow(), 5).toString()), tbStokOpname.getSelectedRow(), 8);
                        } else {
                            tbStokOpname.setValueAt(0, tbStokOpname.getSelectedRow(), 8);
                        }
                    }
                } catch (Exception e) {
                }
            }

            ttl = 0;
            y = 0;
            for (i = 0; i < tbStokOpname.getRowCount(); i++) {
                try {
                    y = Double.parseDouble(tbStokOpname.getValueAt(i, 8).toString());
                } catch (Exception e) {
                    y = 0;
                }
                ttl = ttl + y;
            }
            LTotal.setText(Valid.SetAngka(ttl));
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getstok_opname_obat());
    }

}
