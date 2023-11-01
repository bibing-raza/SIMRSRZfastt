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

import com.toedter.calendar.JDateChooser;
import fungsi.WarnaTable;
import fungsi.WarnaTable2;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariBangsal;
import widget.Button;

/**
 *
 * @author dosen
 */
public final class DlgCariObat extends javax.swing.JDialog {
    private final DefaultTableModel tabModeobat, tabModeResepObat;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement psobat, pscarikapasitas, psobatasuransi, psstok;
    private PreparedStatement ps;
    private ResultSet rsobat, carikapasitas, rsstok;
    private ResultSet rs;
    private double x = 0, y = 0, embalase = 0, tuslah = 0, kenaikan = 0, stokbarang = 0, ttl = 0, ppnobat = 0, stokbarang2 = 0;
    private int i = 0, z = 0, cekCat = 0, a = 0, cekResep = 0, urut = 0;
    private boolean[] pilih;
    private double[] jumlah, harga, eb, ts, stok, beli;
    private String[] kodebarang, namabarang, kodesatuan, aturan1, aturan2, aturan3, waktu1, waktu2, keterangan, wktSmpn;
    private String kodedokter = "", namadokter = "", noresep = "", bangsal = "", bangsaldefault = Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1"), tampilkan_ppnobat_ralan = "", status = "";
    private String stat = "", obat = "", nmObat = "", idObat = "";
    private DlgCariBangsal caribangsal = new DlgCariBangsal(null, false);
    public DlgBarang barang = new DlgBarang(null, false);
    public DlgAturanPakai aturanpakai = new DlgAturanPakai(null, false);
    private WarnaTable2 warna = new WarnaTable2();
    private riwayatobat Trackobat = new riwayatobat();
    public DlgCatatanResep dlgCatatanResep = new DlgCatatanResep(null, false);

    /**
     * Creates new form DlgPenyakit /** Creates new form DlgPenyakit Creates new
     * form DlgPenyakit
     *
     * @param parent
     * @param modal
     */
    public DlgCariObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10, 2);
        setSize(656, 250);

        JComboBox comboAt1 = new JComboBox();
        JComboBox comboAt2 = new JComboBox();
        JComboBox comboAt3 = new JComboBox();
        JComboBox comboWk1 = new JComboBox();
        JComboBox comboWk2 = new JComboBox();
        JComboBox comboKet = new JComboBox();
        JComboBox comboMs = new JComboBox();

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 1'", comboAt1);
        AutoCompleteDecorator.decorate(comboAt1);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 2'", comboAt2);
        AutoCompleteDecorator.decorate(comboAt2);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 3'", comboAt3);
        AutoCompleteDecorator.decorate(comboAt3);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'waktu 1'", comboWk1);
        AutoCompleteDecorator.decorate(comboWk1);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'waktu 2'", comboWk2);
        AutoCompleteDecorator.decorate(comboWk2);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'keterangan'", comboKet);
        AutoCompleteDecorator.decorate(comboKet);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'masa simpan'", comboMs);
        AutoCompleteDecorator.decorate(comboMs);

//        Object[] row = {"K", "Jumlah", "Kode Barang", "Nama Barang", "Satuan",
//            "Letak Barang", "Harga(Rp)", "Jenis Obat", "Embalase", "Tuslah",
//            "Stok", "Aturan Pakai", "I.F.", "H.Beli", "Kategori", "Golongan"
        Object[] row = {"K", "Jumlah", "Kode Barang", "Nama Barang", "Satuan",
            "Harga(Rp)", "Embalase", "Tuslah", "Stok", "Aturan Pakai 1",
            "Aturan Pakai 2", "Aturan Pakai 3", "Waktu 1", "Waktu 2", "Keterangan", "Masa Simpan", "H.Beli"
        };
        tabModeobat = new DefaultTableModel(null, row) {
            @Override
//            public boolean isCellEditable(int rowIndex, int colIndex) {
//                boolean a = false;
//                if ((colIndex == 0) || (colIndex == 1) || (colIndex == 8) || (colIndex == 9) || (colIndex == 11)) {
//                    a = true;
//                }
//                return a;
//            }
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = true;
                if ((colIndex == 2) || (colIndex == 3) || (colIndex == 4) || (colIndex == 5) || (colIndex == 7) || (colIndex == 8)) {
                    a = false;
                }
                return a;
            }
//            Class[] types = new Class[]{
//                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
//                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class,
//                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class,
//                java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
//            };

            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            /*Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };*/
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbObat.setModel(tabModeobat);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        for (i = 0; i < 16; i++) {
//            TableColumn column = tbObat.getColumnModel().getColumn(i);
//            if (i == 0) {
//                column.setPreferredWidth(20);
//            } else if (i == 1) {
//                column.setPreferredWidth(45);
//            } else if (i == 2) {
//                column.setPreferredWidth(75);
//            } else if (i == 3) {
//                column.setPreferredWidth(200);
//            } else if (i == 4) {
//                column.setPreferredWidth(75);
//            } else if (i == 5) {
//                column.setPreferredWidth(80);
//            } else if (i == 6) {
//                column.setPreferredWidth(85);
//            } else if (i == 7) {
//                column.setPreferredWidth(75);
//            } else if (i == 8) {
//                column.setPreferredWidth(60);
//            } else if (i == 9) {
//                column.setPreferredWidth(60);
//            } else if (i == 10) {
//                column.setPreferredWidth(40);
//            } else if (i == 11) {
//                column.setPreferredWidth(130);
//                column.setCellEditor(new DefaultCellEditor(combo));
//            } else if (i == 12) {
//                column.setPreferredWidth(100);
//            } else if (i == 13) {
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
//            } else if (i == 14) {
//                column.setPreferredWidth(100);
//            } else if (i == 15) {
//                column.setPreferredWidth(100);
//            }
//        }
        for (i = 0; i < 17; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(45);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(85);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setPreferredWidth(130);
                column.setCellEditor(new DefaultCellEditor(comboAt1));
            } else if (i == 10) {
                column.setPreferredWidth(80);
                column.setCellEditor(new DefaultCellEditor(comboAt2));
            } else if (i == 11) {
                column.setPreferredWidth(140);
                column.setCellEditor(new DefaultCellEditor(comboAt3));
            } else if (i == 12) {
                column.setPreferredWidth(230);
                column.setCellEditor(new DefaultCellEditor(comboWk1));
            } else if (i == 13) {
                column.setPreferredWidth(220);
                column.setCellEditor(new DefaultCellEditor(comboWk2));
            } else if (i == 14) {
                column.setPreferredWidth(450);
                column.setCellEditor(new DefaultCellEditor(comboKet));
            } else if (i == 15) {
                column.setPreferredWidth(400);
                column.setCellEditor(new DefaultCellEditor(comboMs));
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        warna.kolom = 1;
        tbObat.setDefaultRenderer(Object.class, warna);
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilobat();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilobat();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilobat();
                }
            });
        }

        aturanpakai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (aturanpakai.getTable().getSelectedRow() != -1) {
                    tbObat.setValueAt(aturanpakai.getTable().getValueAt(aturanpakai.getTable().getSelectedRow(), 0).toString(), tbObat.getSelectedRow(), 11);
                }
                tbObat.requestFocus();
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

        tampilkan_ppnobat_ralan = Sequel.cariIsi("select tampilkan_ppnobat_ralan from set_nota");
        jam();

        tabModeResepObat = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "Nama Obat", "Tgl. Resep", "Jam Input", "Status", "ID", "Nama Dokter"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbResepObat.setModel(tabModeResepObat);
        tbResepObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbResepObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(400);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            }
        }
        tbResepObat.setDefaultRenderer(Object.class, new WarnaTable());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppStok = new javax.swing.JMenuItem();
        MnCetakResepDokter = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        Tanggal = new widget.TextBox();
        Jam = new widget.TextBox();
        KdPj = new widget.TextBox();
        TStok = new widget.TextBox();
        TNoRm = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnTambah = new widget.Button();
        BtnSeek5 = new widget.Button();
        BtnSimpan = new widget.Button();
        BtnKeluar = new widget.Button();
        FormInput = new widget.PanelBiasa();
        FormInput1 = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        LTotal = new widget.Label();
        jLabel6 = new widget.Label();
        LPpn = new widget.Label();
        jLabel7 = new widget.Label();
        LTotalTagihan = new widget.Label();
        label12 = new widget.Label();
        Jeniskelas = new widget.ComboBox();
        ChkNoResep = new widget.CekBox();
        jLabel8 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel4 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        tbResepObat = new widget.Table();
        panelisi4 = new widget.panelisi();
        chkResepObat = new widget.CekBox();
        BtnVerif = new widget.Button();
        label13 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnCekResep = new widget.Button();
        label14 = new widget.Label();
        cmbKertas = new widget.ComboBox();
        BtnCetak = new widget.Button();
        jLabel9 = new widget.Label();
        LCountRalan = new widget.Label();

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
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

        ppStok.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
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

        MnCetakResepDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakResepDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnCetakResepDokter.setText("Cetak Resep Dokter");
        MnCetakResepDokter.setActionCommand("Catatan Resep");
        MnCetakResepDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakResepDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakResepDokter.setIconTextGap(8);
        MnCetakResepDokter.setName("MnCetakResepDokter"); // NOI18N
        MnCetakResepDokter.setPreferredSize(new java.awt.Dimension(200, 25));
        MnCetakResepDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakResepDokterActionPerformed(evt);
            }
        });
        Popup.add(MnCetakResepDokter);

        Kd2.setHighlighter(null);
        Kd2.setName("Kd2"); // NOI18N

        Tanggal.setHighlighter(null);
        Tanggal.setName("Tanggal"); // NOI18N

        Jam.setHighlighter(null);
        Jam.setName("Jam"); // NOI18N

        KdPj.setHighlighter(null);
        KdPj.setName("KdPj"); // NOI18N

        TStok.setHighlighter(null);
        TStok.setName("TStok"); // NOI18N

        TNoRm.setHighlighter(null);
        TNoRm.setName("TNoRm"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(Popup);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setToolTipText("Alt+C");
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(315, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
        panelisi3.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(110, 23));
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
        panelisi3.add(BtnAll);

        BtnTambah.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setMnemonic('3');
        BtnTambah.setText("Tambah Data");
        BtnTambah.setToolTipText("Alt+3");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi3.add(BtnTambah);

        BtnSeek5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        BtnSeek5.setMnemonic('4');
        BtnSeek5.setText("Konversi Satuan");
        BtnSeek5.setToolTipText("Alt+4");
        BtnSeek5.setName("BtnSeek5"); // NOI18N
        BtnSeek5.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnSeek5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek5ActionPerformed(evt);
            }
        });
        BtnSeek5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek5KeyPressed(evt);
            }
        });
        panelisi3.add(BtnSeek5);

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        panelisi3.add(BtnSimpan);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('5');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+5");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 350));
        FormInput.setLayout(new java.awt.BorderLayout());

        FormInput1.setBackground(new java.awt.Color(215, 225, 215));
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(100, 92));
        FormInput1.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Total :");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(45, 23));
        FormInput1.add(jLabel5);
        jLabel5.setBounds(4, 35, 55, 23);

        LTotal.setForeground(new java.awt.Color(0, 0, 0));
        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput1.add(LTotal);
        LTotal.setBounds(62, 35, 80, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("PPN :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(35, 23));
        FormInput1.add(jLabel6);
        jLabel6.setBounds(135, 35, 35, 23);

        LPpn.setForeground(new java.awt.Color(0, 0, 0));
        LPpn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LPpn.setText("0");
        LPpn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LPpn.setName("LPpn"); // NOI18N
        LPpn.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput1.add(LPpn);
        LPpn.setBounds(173, 35, 65, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Total + PPN :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput1.add(jLabel7);
        jLabel7.setBounds(241, 35, 65, 23);

        LTotalTagihan.setForeground(new java.awt.Color(0, 0, 0));
        LTotalTagihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotalTagihan.setText("0");
        LTotalTagihan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LTotalTagihan.setName("LTotalTagihan"); // NOI18N
        LTotalTagihan.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput1.add(LTotalTagihan);
        LTotalTagihan.setBounds(309, 35, 80, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Tarif :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(50, 23));
        FormInput1.add(label12);
        label12.setBounds(392, 10, 50, 23);

        Jeniskelas.setForeground(new java.awt.Color(0, 0, 0));
        Jeniskelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rawat Jalan", "Beli Luar", "Karyawan", "Utama/BPJS" }));
        Jeniskelas.setName("Jeniskelas"); // NOI18N
        Jeniskelas.setPreferredSize(new java.awt.Dimension(100, 23));
        Jeniskelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JeniskelasItemStateChanged(evt);
            }
        });
        Jeniskelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JeniskelasKeyPressed(evt);
            }
        });
        FormInput1.add(Jeniskelas);
        Jeniskelas.setBounds(445, 10, 100, 23);

        ChkNoResep.setBorder(null);
        ChkNoResep.setForeground(new java.awt.Color(0, 0, 0));
        ChkNoResep.setSelected(true);
        ChkNoResep.setText("No.Resep   ");
        ChkNoResep.setBorderPainted(true);
        ChkNoResep.setBorderPaintedFlat(true);
        ChkNoResep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkNoResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNoResep.setName("ChkNoResep"); // NOI18N
        ChkNoResep.setOpaque(false);
        ChkNoResep.setPreferredSize(new java.awt.Dimension(85, 23));
        ChkNoResep.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkNoResepItemStateChanged(evt);
            }
        });
        FormInput1.add(ChkNoResep);
        ChkNoResep.setBounds(548, 10, 85, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tanggal :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput1.add(jLabel8);
        jLabel8.setBounds(4, 10, 55, 23);

        DTPTgl.setEditable(false);
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-10-2023" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput1.add(DTPTgl);
        DTPTgl.setBounds(62, 10, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput1.add(cmbJam);
        cmbJam.setBounds(165, 10, 50, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput1.add(cmbMnt);
        cmbMnt.setBounds(218, 10, 50, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput1.add(cmbDtk);
        cmbDtk.setBounds(271, 10, 50, 23);

        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.setPreferredSize(new java.awt.Dimension(22, 23));
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput1.add(ChkJln);
        ChkJln.setBounds(324, 10, 22, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput1.add(TNoRw);
        TNoRw.setBounds(78, 57, 130, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        FormInput1.add(TPasien);
        TPasien.setBounds(210, 57, 420, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput1.add(jLabel4);
        jLabel4.setBounds(10, 57, 65, 23);

        FormInput.add(FormInput1, java.awt.BorderLayout.PAGE_START);

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Catatan Resep Obat Dokter ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N

        tbResepObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbResepObat.setName("tbResepObat"); // NOI18N
        Scroll3.setViewportView(tbResepObat);

        FormInput.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        chkResepObat.setForeground(new java.awt.Color(0, 0, 0));
        chkResepObat.setText("Conteng Semua");
        chkResepObat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkResepObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkResepObat.setName("chkResepObat"); // NOI18N
        chkResepObat.setPreferredSize(new java.awt.Dimension(120, 23));
        chkResepObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkResepObatActionPerformed(evt);
            }
        });
        panelisi4.add(chkResepObat);

        BtnVerif.setForeground(new java.awt.Color(0, 0, 0));
        BtnVerif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnVerif.setMnemonic('U');
        BtnVerif.setText("Verifikasi Resep");
        BtnVerif.setToolTipText("Alt+U");
        BtnVerif.setIconTextGap(7);
        BtnVerif.setName("BtnVerif"); // NOI18N
        BtnVerif.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnVerif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifActionPerformed(evt);
            }
        });
        panelisi4.add(BtnVerif);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setText("Status Resep :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label13);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM", "SUDAH", "DILUAR", "Semua" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(cmbStatus);

        BtnCekResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCekResep.setMnemonic('C');
        BtnCekResep.setText("Tampilkan Resep");
        BtnCekResep.setToolTipText("Alt+C");
        BtnCekResep.setName("BtnCekResep"); // NOI18N
        BtnCekResep.setPreferredSize(new java.awt.Dimension(160, 23));
        BtnCekResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekResepActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCekResep);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Pilihan Kertas Print :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(108, 23));
        panelisi4.add(label14);

        cmbKertas.setForeground(new java.awt.Color(0, 0, 0));
        cmbKertas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BILLING", "THERMAL" }));
        cmbKertas.setName("cmbKertas"); // NOI18N
        cmbKertas.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(cmbKertas);

        BtnCetak.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetak.setMnemonic('C');
        BtnCetak.setText("Cetak Resep");
        BtnCetak.setToolTipText("Alt+C");
        BtnCetak.setIconTextGap(7);
        BtnCetak.setName("BtnCetak"); // NOI18N
        BtnCetak.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCetak);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(jLabel9);

        LCountRalan.setForeground(new java.awt.Color(0, 0, 0));
        LCountRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountRalan.setText("0");
        LCountRalan.setName("LCountRalan"); // NOI18N
        LCountRalan.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi4.add(LCountRalan);

        FormInput.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbObat.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampilobat();
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
        tampilobat();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tbObat.getRowCount() != 0) {
            try {
                getDataobat();
            } catch (java.lang.NullPointerException e) {
            }

            if (evt.getClickCount() == 2) {
                dispose();
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tbObat.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    getDataobat();
                    i = tbObat.getSelectedColumn();

                    try {
                        stokbarang = 0;
                        psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                        try {
//                            tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 6);
                            tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 7);
                            psstok.setString(1, bangsal);
                            psstok.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
                            rsstok = psstok.executeQuery();
                            if (rsstok.next()) {
                                stokbarang = rsstok.getDouble(1);
                            }
                        } catch (Exception e) {
                            stokbarang = 0;
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsstok != null) {
                                rsstok.close();
                            }
                            if (psstok != null) {
                                psstok.close();
                            }
                        }

                        tbObat.setValueAt(stokbarang, tbObat.getSelectedRow(), 8);
                        y = 0;
                        try {
                            y = Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
                            y = Math.round(Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString()));
                            tbObat.setValueAt(y, tbObat.getSelectedRow(), 1);
                        } catch (Exception e) {
                            y = 0;
                        }
                        if (stokbarang < y) {
                            JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
                            tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                        }

                        ttl = 0;
                        y = 0;
                        int row2 = tabModeobat.getRowCount();
                        for (int r = 0; r < row2; r++) {
                            try {
                                if (Double.parseDouble(tabModeobat.getValueAt(r, 1).toString()) > 0) {
                                    y = Double.parseDouble(tabModeobat.getValueAt(r, 1).toString())
                                            * Double.parseDouble(tabModeobat.getValueAt(r, 5).toString())
                                            + Double.parseDouble(tabModeobat.getValueAt(r, 7).toString())
                                            + Double.parseDouble(tabModeobat.getValueAt(r, 8).toString());
                                } else {
                                    y = 0;
                                }
                            } catch (Exception e) {
                                y = 0;
                            }
                            ttl = ttl + y;
                        }
                        LTotal.setText(Valid.SetAngka(ttl));
                        ppnobat = 0;
                        if (tampilkan_ppnobat_ralan.equals("Yes")) {
                            ppnobat = ttl * 0.1;
                            ttl = ttl + ppnobat;
                            LPpn.setText(Valid.SetAngka(ppnobat));
                            LTotalTagihan.setText(Valid.SetAngka(ttl));
                        }
                        TCari.setText("");
                        TCari.requestFocus();

                    } catch (Exception e) {
                        tbObat.setValueAt(0, tbObat.getSelectedRow(), 8);
                    }
                    TCari.setText("");
                    TCari.requestFocus();

//                    if (i == 2) {
//                        try {
//                            stokbarang = 0;
//                            psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
//                            try {
//                                tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
//                                tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
//                                psstok.setString(1, bangsal);
//                                psstok.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
//                                rsstok = psstok.executeQuery();
//                                if (rsstok.next()) {
//                                    stokbarang = rsstok.getDouble(1);
//                                }
//                            } catch (Exception e) {
//                                stokbarang = 0;
//                                System.out.println("Notifikasi : " + e);
//                            } finally {
//                                if (rsstok != null) {
//                                    rsstok.close();
//                                }
//                                if (psstok != null) {
//                                    psstok.close();
//                                }
//                            }
//
//                            tbObat.setValueAt(stokbarang, tbObat.getSelectedRow(), 10);
//                            y = 0;
//                            try {
//                                y = Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
//                                y = Math.round(Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString()));
//                                tbObat.setValueAt(y,tbObat.getSelectedRow(), 1);
//                            } catch (Exception e) {
//                                y = 0;
//                            }
//                            if (stokbarang < y) {
//                                JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
//                                tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
//                            }
//
//                            ttl = 0;
//                            y = 0;
//                            int row2 = tabModeobat.getRowCount();
//                            for (int r = 0; r < row2; r++) {
//                                try {
//                                    if (Double.parseDouble(tabModeobat.getValueAt(r, 1).toString()) > 0) {
//                                        y = Double.parseDouble(tabModeobat.getValueAt(r, 1).toString())
//                                                * Double.parseDouble(tabModeobat.getValueAt(r, 6).toString())
//                                                + Double.parseDouble(tabModeobat.getValueAt(r, 8).toString())
//                                                + Double.parseDouble(tabModeobat.getValueAt(r, 9).toString());
//                                    }else{
//                                        y = 0;
//                                    }
//                                } catch (Exception e) {
//                                    y = 0;
//                                }
//                                ttl = ttl + y;
//                            }
//                            LTotal.setText(Valid.SetAngka(ttl));
//                            ppnobat = 0;
//                            if (tampilkan_ppnobat_ralan.equals("Yes")) {
//                                ppnobat = ttl * 0.1;
//                                ttl = ttl + ppnobat;
//                                LPpn.setText(Valid.SetAngka(ppnobat));
//                                LTotalTagihan.setText(Valid.SetAngka(ttl));
//                            }
//                            TCari.setText("");
//                            TCari.requestFocus();
//
//                        } catch (Exception e) {
//                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 10);
//                        }
//                        TCari.setText("");
//                        TCari.requestFocus();
//                    } else if (i == 8) {
//                        try {
//                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0,0")) {
//                                tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
//                            }
//                        } catch (Exception e) {
//                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 8);
//                        }
//                    } else if (i == 9) {
//                        try {
//                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0,0")) {
//                                tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
//                            }
//                        } catch (Exception e) {
//                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 9);
//                        }
//
//                        TCari.setText("");
//                        TCari.requestFocus();
//                    } else if ((i == 10) || (i == 3)) {
//                        ttl = 0;
//                        y = 0;
//                        int row2 = tabModeobat.getRowCount();
//                        for (int r = 0; r < row2; r++) {
//                            try {
//                                if (Double.parseDouble(tabModeobat.getValueAt(r, 1).toString()) > 0) {
//                                    y = Double.parseDouble(tabModeobat.getValueAt(r, 1).toString())
//                                            * Double.parseDouble(tabModeobat.getValueAt(r, 6).toString())
//                                            + Double.parseDouble(tabModeobat.getValueAt(r, 8).toString())
//                                            + Double.parseDouble(tabModeobat.getValueAt(r, 9).toString());
//                                }
//                            } catch (Exception e) {
//                                y = 0;
//                            }
//                            ttl = ttl + y;
//                        }
//                        LTotal.setText(Valid.SetAngka(ttl));
//                        ppnobat = 0;
//                        if (tampilkan_ppnobat_ralan.equals("Yes")) {
//                            ppnobat = ttl * 0.1;
//                            ttl = ttl + ppnobat;
//                            LPpn.setText(Valid.SetAngka(ppnobat));
//                            LTotalTagihan.setText(Valid.SetAngka(ttl));
//                        }
//                        TCari.setText("");
//                        TCari.requestFocus();
//                    } else if (i == 11) {
//                        TCari.setText("");
//                        TCari.requestFocus();
//                    } else {
//                        try {
//                            stokbarang = 0;
//                            psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
//                            try {
//                                tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
//                                tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
//                                psstok.setString(1, bangsal);
//                                psstok.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
//                                rsstok = psstok.executeQuery();
//                                if (rsstok.next()) {
//                                    stokbarang = rsstok.getDouble(1);
//                                }
//                            } catch (Exception e) {
//                                stokbarang = 0;
//                                System.out.println("Notifikasi : " + e);
//                            } finally {
//                                if (rsstok != null) {
//                                    rsstok.close();
//                                }
//                                if (psstok != null) {
//                                    psstok.close();
//                                }
//                            }
//
//                            tbObat.setValueAt(stokbarang, tbObat.getSelectedRow(), 10);
//                            y = 0;
//                            try {
//                                y = Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
//                                y = Math.round(Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString()));
//                                tbObat.setValueAt(y,tbObat.getSelectedRow(), 1);
//                            } catch (Exception e) {
//                                y = 0;
//                            }
//                            if (stokbarang < y) {
//                                JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
//                                tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
//                            }
//
//                            ttl = 0;
//                            y = 0;
//                            int row2 = tabModeobat.getRowCount();
//                            for (int r = 0; r < row2; r++) {
//                                try {
//                                    if (Double.parseDouble(tabModeobat.getValueAt(r, 1).toString()) > 0) {
//                                        y = Double.parseDouble(tabModeobat.getValueAt(r, 1).toString())
//                                                * Double.parseDouble(tabModeobat.getValueAt(r, 6).toString())
//                                                + Double.parseDouble(tabModeobat.getValueAt(r, 8).toString())
//                                                + Double.parseDouble(tabModeobat.getValueAt(r, 9).toString());
//                                    }
//                                } catch (Exception e) {
//                                    y = 0;
//                                }
//                                ttl = ttl + y;
//                            }
//                            LTotal.setText(Valid.SetAngka(ttl));
//                            ppnobat = 0;
//                            if (tampilkan_ppnobat_ralan.equals("Yes")) {
//                                ppnobat = ttl * 0.1;
//                                ttl = ttl + ppnobat;
//                                LPpn.setText(Valid.SetAngka(ppnobat));
//                                LTotalTagihan.setText(Valid.SetAngka(ttl));
//                            }
//                            TCari.setText("");
//                            TCari.requestFocus();
//
//                        } catch (Exception e) {
//                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 10);
//                        }
//                        TCari.setText("");
//                        TCari.requestFocus();
//                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataobat();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                i = tbObat.getSelectedColumn();
                if ((i == 1) || (i == 11) || (i == 8) || (i == 9)) {
                    if (tbObat.getSelectedRow() != -1) {
                        tbObat.setValueAt("", tbObat.getSelectedRow(), i);
                    }
                }

            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                i = tbObat.getSelectedColumn();
                if (i != 11) {
                    TCari.requestFocus();
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                i = tbObat.getSelectedColumn();
                if (i == 2) {
                    try {
                        stokbarang = 0;
                        psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                        try {
                            psstok.setString(1, bangsal);
                            psstok.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
                            rsstok = psstok.executeQuery();
                            if (rsstok.next()) {
                                stokbarang = rsstok.getDouble(1);
                            }
                        } catch (Exception e) {
                            stokbarang = 0;
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsstok != null) {
                                rsstok.close();
                            }
                            if (psstok != null) {
                                psstok.close();
                            }
                        }
                        tbObat.setValueAt(stokbarang, tbObat.getSelectedRow(), 10);

                        y = 0;
                        try {
                            y = Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
                        } catch (Exception e) {
                            y = 0;
                        }
                        if (stokbarang < y) {
                            JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
                            tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                        }

                        try {
                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0,0")) {
                                tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
                            }
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 8);
                        }

                        try {
                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0,0")) {
                                tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
                            }
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 9);
                        }
                    } catch (Exception e) {
                        tbObat.setValueAt(0, tbObat.getSelectedRow(), 10);
                    }
                } else if (i == 11) {
                    akses.setform("DlgCariObat");
                    aturanpakai.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    aturanpakai.setLocationRelativeTo(internalFrame1);
                    aturanpakai.setVisible(true);
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        ChkJln.setSelected(true);
        DTPTgl.setDate(new Date());
        cmbStatus.setSelectedIndex(0);
        cmbKertas.setSelectedIndex(0);
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        barang.emptTeks();
        barang.isCek();
        barang.setSize(internalFrame1.getWidth() + 40, internalFrame1.getHeight() + 40);
        barang.setLocationRelativeTo(internalFrame1);
        barang.setAlwaysOnTop(false);
        barang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahActionPerformed

private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TCari, "Data");
    } else if (bangsal.equals("")) {
        Valid.textKosong(TCari, "Lokasi");
    } else {
        cekCat = 0;
        cekCat = Sequel.cariInteger("Select count(-1) from catatan_resep where no_rawat = '" + TNoRw.getText() + "' and status = 'BELUM'");
        if (cekCat > 0) {
            JOptionPane.showMessageDialog(null, "Ada catatan resep dari dokter, Silakan verifikasi resepnya dulu..!!");
            BtnVerif.requestFocus();
        } else {
            try {
                isSetBangsal();
                urut = 0;
                for (i = 0; i < tbObat.getRowCount(); i++) {
                    if (Valid.SetAngka(tbObat.getValueAt(i, 1).toString()) > 0) {
                        if ((tbObat.getValueAt(i, 1).toString()) == null || (tbObat.getValueAt(i, 1).toString()).equals("")) {
                            y = 0;
                        } else {
                            y = Double.parseDouble(tbObat.getValueAt(i, 1).toString());
                        }

                        isStok(tbObat.getValueAt(i, 2).toString());
                        if (TStok.getText().equals("")) {
                            stokbarang2 = 0;
                        } else {
                            stokbarang2 = Double.parseDouble(TStok.getText());
                        }

                        if (stokbarang2 < y || TStok.getText().equals("")) {
                            JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
                            tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                            ppBersihkanActionPerformed(evt);
                        } else {
                            urut++;
                            if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                                pscarikapasitas = koneksi.prepareStatement("select IFNULL(kapasitas,1) from databarang where kode_brng=?");
                                try {
                                    pscarikapasitas.setString(1, tbObat.getValueAt(i, 2).toString());
                                    carikapasitas = pscarikapasitas.executeQuery();
                                    if (carikapasitas.next()) {
                                        if (Sequel.menyimpantf2("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                            Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 13).toString(),
                                            tbObat.getValueAt(i, 6).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)),
                                            tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                            + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                            * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)))), "Ralan", bangsal, "Belum", "-", String.valueOf(urut)
                                        }) == true) {
                                            isRawat();
                                            Sequel.menyimpan("88", "?,?,?,?,?", 5, new String[]{
                                                Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 11).toString()
                                            });
                                            Trackobat.catatRiwayat(tbObat.getValueAt(i, 2).toString(), 0, (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                            Trackobat.catatRiwayatObat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRm.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                            Sequel.menyimpan("gudangbarang", "'" + tbObat.getValueAt(i, 2).toString() + "','" + bangsal + "','-" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)) + "'",
                                                    "stok=stok-'" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)) + "'", "kode_brng='" + tbObat.getValueAt(i, 2).toString() + "' and kd_bangsal='" + bangsal + "'");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Gagal Menyimpan, Kemungkinan ada data sama/kapasitas tidak ditemukan..!!");
                                        }
                                    } else {
                                        if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                            Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 16).toString(),
                                            tbObat.getValueAt(i, 5).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
                                            tbObat.getValueAt(i, 6).toString(), tbObat.getValueAt(i, 7).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                            + Double.parseDouble(tbObat.getValueAt(i, 7).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 5).toString())
                                            * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ralan", bangsal, "Belum", "-", String.valueOf(urut)
                                        }) == true) {
                                            isRawat();
                                            Sequel.menyimpan("aturan_pakai", "?,?,?,?,?,?,?,?,?,?,?,?", 12, new String[]{
                                                Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                                TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 9).toString(), tbObat.getValueAt(i, 10).toString(), tbObat.getValueAt(i, 11).toString(),
                                                tbObat.getValueAt(i, 12).toString(), tbObat.getValueAt(i, 13).toString(), tbObat.getValueAt(i, 14).toString(), tbObat.getValueAt(i, 15).toString(), String.valueOf(urut)
                                            });
                                            Trackobat.catatRiwayat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                            Trackobat.catatRiwayatObat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRm.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                            Sequel.menyimpan("gudangbarang", "'" + tbObat.getValueAt(i, 2).toString() + "','" + bangsal + "','-" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'",
                                                    "stok=stok-'" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'", "kode_brng='" + tbObat.getValueAt(i, 2).toString() + "' and kd_bangsal='" + bangsal + "'");
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi Kapasitas : " + e);
                                } finally {
                                    if (carikapasitas != null) {
                                        carikapasitas.close();
                                    }
                                    if (pscarikapasitas != null) {
                                        pscarikapasitas.close();
                                    }
                                }
                            } else {
                                if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                    Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 16).toString(),
                                    tbObat.getValueAt(i, 5).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
                                    tbObat.getValueAt(i, 6).toString(), tbObat.getValueAt(i, 7).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                    + Double.parseDouble(tbObat.getValueAt(i, 7).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 5).toString())
                                    * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ralan", bangsal, "Belum", "-", String.valueOf(urut)
                                }) == true) {
                                    isRawat();
                                    Sequel.menyimpan("aturan_pakai", "?,?,?,?,?,?,?,?,?,?,?,?", 12, new String[]{
                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                        TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 9).toString(), tbObat.getValueAt(i, 10).toString(), tbObat.getValueAt(i, 11).toString(),
                                        tbObat.getValueAt(i, 12).toString(), tbObat.getValueAt(i, 13).toString(), tbObat.getValueAt(i, 14).toString(), tbObat.getValueAt(i, 15).toString(), String.valueOf(urut)
                                    });
                                    Trackobat.catatRiwayat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                    Trackobat.catatRiwayatObat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRm.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                    Sequel.menyimpan("gudangbarang", "'" + tbObat.getValueAt(i, 2).toString() + "','" + bangsal + "','-" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'",
                                            "stok=stok-'" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'", "kode_brng='" + tbObat.getValueAt(i, 2).toString() + "' and kd_bangsal='" + bangsal + "'");
                                }
                            }
                        }
                        tbObat.setValueAt("", i, 1);

                        if (!noresep.equals("")) {
                            Sequel.mengedit("resep_obat", "no_resep='" + noresep + "'", "tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',jam='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "'");
                        }
                    }
                }
                LTotal.setText("0");
                LPpn.setText("0");
                LTotalTagihan.setText("0");
                if (ChkNoResep.isSelected() == true) {
                    DlgResepObat resep = new DlgResepObat(null, false);
                    resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    resep.setLocationRelativeTo(internalFrame1);
                    resep.emptTeks();
                    resep.isCek();
                    resep.setAlwaysOnTop(true);
                    resep.dokter.setAlwaysOnTop(true);
                    resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString());
                    resep.tampil();
                    resep.setDokterRalan();
                    resep.setVisible(true);
                    dispose();
                } else {
                    dispose();
                }
                dispose();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            ChkJln.setSelected(true);
            DTPTgl.setDate(new Date());
            cmbStatus.setSelectedIndex(0);
            cmbKertas.setSelectedIndex(0);
        }
    }
}//GEN-LAST:event_BtnSimpanActionPerformed

private void BtnSeek5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek5ActionPerformed
    DlgCariKonversi carikonversi = new DlgCariKonversi(null, false);
    carikonversi.setLocationRelativeTo(internalFrame1);
    carikonversi.setAlwaysOnTop(false);
    carikonversi.setVisible(true);
}//GEN-LAST:event_BtnSeek5ActionPerformed

private void BtnSeek5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek5KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnSeek5KeyPressed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
    for (i = 0; i < tbObat.getRowCount(); i++) {
        tbObat.setValueAt("", i, 1);
        tbObat.setValueAt(0, i, 6);
        tbObat.setValueAt(0, i, 7);
        tbObat.setValueAt(0, i, 8);
    }
    LTotal.setText("0");
}//GEN-LAST:event_ppBersihkanActionPerformed

private void JeniskelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JeniskelasItemStateChanged
    tampilobat();
}//GEN-LAST:event_JeniskelasItemStateChanged

private void JeniskelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JeniskelasKeyPressed
    Valid.pindah(evt, TCari, BtnKeluar);
}//GEN-LAST:event_JeniskelasKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        emptTeksobat();
        embalase = Sequel.cariIsiAngka("select embalase_per_obat from set_embalase");
        tuslah = Sequel.cariIsiAngka("select tuslah_per_obat from set_embalase");
        isSetBangsal();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        ChkJln.setSelected(true);
        DTPTgl.setDate(new Date());
        cmbStatus.setSelectedIndex(0);
        cmbKertas.setSelectedIndex(0);
        Sequel.insertClosingStok();
        if (noresep.equals("")) {
            tampilobat();
            isPsien();
            tampil_resep();
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkNoResepItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkNoResepItemStateChanged
        if (ChkNoResep.isSelected() == true) {
            DlgResepObat resep = new DlgResepObat(null, false);
            resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            resep.setLocationRelativeTo(internalFrame1);
            resep.emptTeks();
            resep.isCek();
            resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString());
            resep.tampil();
            resep.setDokterRalan();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_ChkNoResepItemStateChanged

    private void ppStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppStokActionPerformed
        isSetBangsal();
        for (i = 0; i < tbObat.getRowCount(); i++) {
            try {
                stokbarang = 0;
                psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                try {
                    psstok.setString(1, bangsal);
                    psstok.setString(2, tbObat.getValueAt(i, 2).toString());
                    rsstok = psstok.executeQuery();
                    if (rsstok.next()) {
                        stokbarang = rsstok.getDouble(1);
                    }
                } catch (Exception e) {
                    stokbarang = 0;
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsstok != null) {
                        rsstok.close();
                    }

                    if (psstok != null) {
                        psstok.close();
                    }
                }

                tbObat.setValueAt(stokbarang, i, 8);
            } catch (Exception e) {
                tbObat.setValueAt(0, i, 8);
            }
        }
    }//GEN-LAST:event_ppStokActionPerformed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt, BtnKeluar, cmbJam);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPTgl, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, Jeniskelas);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void BtnVerifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifActionPerformed
        x = 0;
        for (i = 0; i < tbResepObat.getRowCount(); i++) {
            if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                x++;
            }
        }

        if (x == 0) {
            JOptionPane.showMessageDialog(null, "Conteng dulu untuk verifikasi resepnya..!!!!");
            tbResepObat.requestFocus();
        } else {
            try {
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        stat = "SUDAH";
                    } else {
                        stat = "DILUAR";
                    }
                    Sequel.queryu("update catatan_resep set status = '" + stat + "' where no_rawat='" + tbResepObat.getValueAt(i, 1).toString() + "' "
                            + "and noId='" + tbResepObat.getValueAt(i, 6).toString() + "'");
                }
                isPsien();
                tampil_resep();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnVerifActionPerformed

    private void BtnCekResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekResepActionPerformed
        isPsien();
        tampil_resep();
    }//GEN-LAST:event_BtnCekResepActionPerformed

    private void MnCetakResepDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakResepDokterActionPerformed
        cekResep = 0;
        cekResep = Sequel.cariInteger("select COUNT(1) cek from catatan_resep where no_rawat ='" + TNoRw.getText() + "'");

        if (cekResep == 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini tidak/belum diberi e-Resep oleh dokter pemeriksanya...!!!!");
            isPsien();
            tampil_resep();
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
            Valid.MyReport("rptResepRalan.jrxml", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                    " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, c.nama_obat, "
                    + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                    + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where c.no_rawat ='" + TNoRw.getText() + "' order by c.noId", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakResepDokterActionPerformed

    private void chkResepObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkResepObatActionPerformed
        for (i = 0; i < tbResepObat.getRowCount(); i++) {
            if (chkResepObat.isSelected() == true) {
                tbResepObat.setValueAt(Boolean.TRUE, i, 0);
            } else if (chkResepObat.isSelected() == false) {
                tbResepObat.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_chkResepObatActionPerformed

    private void BtnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data yang bisa anda print...!!!!");
        } else {
            x = 0;
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Utk. mencetak resep obat silahkan conteng item yg. dipilih...!!!!");
                tbResepObat.requestFocus();
                tampil_resep();
            } else if (x > 0) {
                idObat = "";
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        if (idObat.equals("")) {
                            idObat = "'" + tbResepObat.getValueAt(i, 6).toString() + "'";
                        } else {
                            idObat = idObat + ",'" + tbResepObat.getValueAt(i, 6).toString() + "'";
                        }
                    }
                }

                if (cmbKertas.getSelectedIndex() == 0) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("carabyr", Sequel.cariIsi("select pj.png_jawab from reg_periksa rp inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + TNoRw.getText() + "'"));
                    Valid.MyReport("rptCatatanResepRalan.jasper", "report", "::[ Cetak e-Resep ]::",
                            "SELECT pl.nm_poli, date_format(cr.tgl_perawatan,'%d-%m-%Y') tgl, d.nm_dokter, cr.no_rawat, p.no_rkm_medis, "
                            + "p.nm_pasien, ifnull(p.no_tlp,'-') no_hp, cr.nama_obat FROM catatan_resep cr "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN dokter d ON d.kd_dokter=cr.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                            + "WHERE cr.noId in (" + idObat + ") ORDER BY cr.tgl_perawatan DESC, cr.jam_perawatan DESC, cr.noId DESC", param);

                } else if (cmbKertas.getSelectedIndex() == 1) {
                    Map<String, Object> param = new HashMap<>();
                    Valid.MyReport("rptStrukResepRalan.jasper", "report", "::[ Struk Resep Dokter Poliklinik/Unit Rawat Jalan Kertas Thermal ]::",
                            " SELECT pl.nm_poli, date_format(cr.tgl_perawatan,'%d-%m-%Y') tgl, d.nm_dokter, cr.no_rawat, p.no_rkm_medis, "
                            + "p.nm_pasien, ifnull(p.no_tlp,'-') no_hp, cr.nama_obat FROM catatan_resep cr "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN dokter d ON d.kd_dokter=cr.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                            + "WHERE cr.noId in (" + idObat + ") ORDER BY cr.tgl_perawatan DESC, cr.jam_perawatan DESC, cr.noId DESC", param);
                }
            }
        }
    }//GEN-LAST:event_BtnCetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariObat dialog = new DlgCariObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCekResep;
    private widget.Button BtnCetak;
    private widget.Button BtnKeluar;
    private widget.Button BtnSeek5;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah;
    private widget.Button BtnVerif;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkNoResep;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.TextBox Jam;
    private widget.ComboBox Jeniskelas;
    private widget.TextBox Kd2;
    private widget.TextBox KdPj;
    private widget.Label LCountRalan;
    private widget.Label LPpn;
    private widget.Label LTotal;
    private widget.Label LTotalTagihan;
    private javax.swing.JMenuItem MnCetakResepDokter;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TStok;
    private widget.TextBox Tanggal;
    private widget.CekBox chkResepObat;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKertas;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbStatus;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppStok;
    private widget.Table tbObat;
    private widget.Table tbResepObat;
    // End of variables declaration//GEN-END:variables

    public void tampilobat() {
//        z = 0;
//        for (i = 0; i < tbObat.getRowCount(); i++) {
//            if (!tbObat.getValueAt(i, 0).toString().equals("")) {
//                z++;
//            }
//        }
//
//        pilih = null;
//        pilih = new boolean[z];
//        jumlah = null;
//        jumlah = new double[z];
//        harga = null;
//        harga = new double[z];
//        eb = null;
//        eb = new double[z];
//        ts = null;
//        ts = new double[z];
//        stok = null;
//        stok = new double[z];
//        kodebarang = null;
//        kodebarang = new String[z];
//        namabarang = null;
//        namabarang = new String[z];
//        kodesatuan = null;
//        kodesatuan = new String[z];
//        letakbarang = null;
//        letakbarang = new String[z];
//        namajenis = null;
//        namajenis = new String[z];
//        aturan = null;
//        aturan = new String[z];
//        industri = null;
//        industri = new String[z];
//        beli = null;
//        beli = new double[z];
//        kategori = null;
//        kategori = new String[z];
//        golongan = null;
//        golongan = new String[z];
//        z = 0;
//        for (i = 0; i < tbObat.getRowCount(); i++) {
//            if (!tbObat.getValueAt(i, 1).toString().equals("")) {
//                pilih[z] = Boolean.parseBoolean(tbObat.getValueAt(i, 0).toString());
//                try {
//                    jumlah[z] = Double.parseDouble(tbObat.getValueAt(i, 1).toString());
//                } catch (Exception e) {
//                    jumlah[z] = 0;
//                }
//                kodebarang[z] = tbObat.getValueAt(i, 2).toString();
//                namabarang[z] = tbObat.getValueAt(i, 3).toString();
//                kodesatuan[z] = tbObat.getValueAt(i, 4).toString();
//                letakbarang[z] = tbObat.getValueAt(i, 5).toString();
//                try {
//                    harga[z] = Double.parseDouble(tbObat.getValueAt(i, 6).toString());
//                } catch (Exception e) {
//                    harga[z] = 0;
//                }
//                namajenis[z] = tbObat.getValueAt(i, 7).toString();
//                try {
//                    eb[z] = Double.parseDouble(tbObat.getValueAt(i, 8).toString());
//                } catch (Exception e) {
//                    eb[z] = 0;
//                }
//                try {
//                    ts[z] = Double.parseDouble(tbObat.getValueAt(i, 9).toString());
//                } catch (Exception e) {
//                    ts[z] = 0;
//                }
//                try {
//                    stok[z] = Double.parseDouble(tbObat.getValueAt(i, 10).toString());
//                } catch (Exception e) {
//                    stok[z] = 0;
//                }
//                aturan[z] = tbObat.getValueAt(i, 11).toString();
//                industri[z] = tbObat.getValueAt(i, 12).toString();
//                try {
//                    beli[z] = Double.parseDouble(tbObat.getValueAt(i, 13).toString());
//                } catch (Exception e) {
//                    beli[z] = 0;
//                }
//                kategori[z] = tbObat.getValueAt(i, 14).toString();
//                golongan[z] = tbObat.getValueAt(i, 15).toString();
//                z++;
//            }
//        }

        z = 0;
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (!tbObat.getValueAt(i, 0).toString().equals("")) {
                z++;
            }
        }

        pilih = null;
        pilih = new boolean[z];
        jumlah = null;
        jumlah = new double[z];
        harga = null;
        harga = new double[z];
        eb = null;
        eb = new double[z];
        ts = null;
        ts = new double[z];
        stok = null;
        stok = new double[z];
        kodebarang = null;
        kodebarang = new String[z];
        namabarang = null;
        namabarang = new String[z];
        kodesatuan = null;
        kodesatuan = new String[z];
        aturan1 = null;
        aturan1 = new String[z];
        aturan2 = null;
        aturan2 = new String[z];
        aturan3 = null;
        aturan3 = new String[z];
        waktu1 = null;
        waktu1 = new String[z];
        waktu2 = null;
        waktu2 = new String[z];
        keterangan = null;
        keterangan = new String[z];
        wktSmpn = null;
        wktSmpn = new String[z];
        beli = null;
        beli = new double[z];
        z = 0;
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (!tbObat.getValueAt(i, 1).toString().equals("")) {
                pilih[z] = Boolean.parseBoolean(tbObat.getValueAt(i, 0).toString());
                try {
                    jumlah[z] = Double.parseDouble(tbObat.getValueAt(i, 1).toString());
                } catch (Exception e) {
                    jumlah[z] = 0;
                }
                kodebarang[z] = tbObat.getValueAt(i, 2).toString();
                namabarang[z] = tbObat.getValueAt(i, 3).toString();
                kodesatuan[z] = tbObat.getValueAt(i, 4).toString();
                try {
                    harga[z] = Double.parseDouble(tbObat.getValueAt(i, 5).toString());
                } catch (Exception e) {
                    harga[z] = 0;
                }

                try {
                    eb[z] = Double.parseDouble(tbObat.getValueAt(i, 6).toString());
                } catch (Exception e) {
                    eb[z] = 0;
                }
                try {
                    ts[z] = Double.parseDouble(tbObat.getValueAt(i, 7).toString());
                } catch (Exception e) {
                    ts[z] = 0;
                }
                try {
                    stok[z] = Double.parseDouble(tbObat.getValueAt(i, 8).toString());
                } catch (Exception e) {
                    stok[z] = 0;
                }
                aturan1[z] = tbObat.getValueAt(i, 9).toString();
                aturan2[z] = tbObat.getValueAt(i, 10).toString();
                aturan3[z] = tbObat.getValueAt(i, 11).toString();
                waktu1[z] = tbObat.getValueAt(i, 12).toString();
                waktu2[z] = tbObat.getValueAt(i, 13).toString();
                keterangan[z] = tbObat.getValueAt(i, 14).toString();
                wktSmpn[z] = tbObat.getValueAt(i, 15).toString();
                try {
                    beli[z] = Double.parseDouble(tbObat.getValueAt(i, 16).toString());
                } catch (Exception e) {
                    beli[z] = 0;
                }

                z++;
            }
        }

        Valid.tabelKosong(tabModeobat);

//        for (i = 0; i < z; i++) {
//            tabModeobat.addRow(new Object[]{
//                pilih[i], jumlah[i], kodebarang[i], namabarang[i], kodesatuan[i], letakbarang[i], harga[i], namajenis[i], eb[i], ts[i], stok[i], aturan[i], industri[i], beli[i], kategori[i], golongan[i]
//            });
//        }
        for (i = 0; i < z; i++) {
            tabModeobat.addRow(new Object[]{
                pilih[i], jumlah[i], kodebarang[i], namabarang[i], kodesatuan[i], harga[i], eb[i], ts[i], stok[i], aturan1[i], aturan2[i], aturan3[i], waktu1[i], waktu2[i], keterangan[i], wktSmpn[i], beli[i]
            });
        }

        try {
            if (akses.getkdbangsal().equals("APT07")) {
                psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.karyawan,databarang.ralan,databarang.beliluar,"
                        + " databarang.letak_barang,databarang.utama,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns "
                        + " and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
                        + " databarang.status='1' and databarang.nama_brng like ? or "
                        + " databarang.status='1' and kategori_barang.nama like ? or "
                        + " databarang.status='1' and golongan_barang.nama like ? or "
                        + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
                psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
                        + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns "
                        + " and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
                        + " databarang.status='1' and databarang.nama_brng like ? or "
                        + " databarang.status='1' and kategori_barang.nama like ? or "
                        + " databarang.status='1' and golongan_barang.nama like ? or "
                        + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
            } else {
                psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.karyawan,databarang.ralan,databarang.beliluar,"
                        + " databarang.letak_barang,databarang.utama,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns "
                        + " and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.kode_brng like ? or "
                        + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
                        + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
                        + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
                        + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");

                psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
                        + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns "
                        + " and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.kode_brng like ? or "
                        + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
                        + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
                        + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
                        + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");

            }
////            psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.karyawan,databarang.ralan,databarang.beliluar,"
////                    + " databarang.letak_barang,databarang.utama,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns "
////                    + " and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
////                    + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
////                    + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
////                    + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
////                    + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");
//
//            psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.karyawan,databarang.ralan,databarang.beliluar,"
//                    + " databarang.letak_barang,databarang.utama,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns "
//                    + " and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
//                    + " databarang.status='1' and databarang.nama_brng like ? or "
//                    + " databarang.status='1' and kategori_barang.nama like ? or "
//                    + " databarang.status='1' and golongan_barang.nama like ? or "
//                    + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
////            psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
////                    + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns "
////                    + " and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.kode_brng like ? or "
////                    + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
////                    + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
////                    + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
////                    + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");
//
//            psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                    + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns "
//                    + " and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
//                    + " databarang.status='1' and databarang.nama_brng like ? or "
//                    + " databarang.status='1' and kategori_barang.nama like ? or "
//                    + " databarang.status='1' and golongan_barang.nama like ? or "
//                    + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
            try {
                if (kenaikan > 0) {
                    psobatasuransi.setDouble(1, kenaikan);
                    psobatasuransi.setString(2, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(3, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(4, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(5, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(6, "%" + TCari.getText().trim() + "%");
                    rsobat = psobatasuransi.executeQuery();
                    while (rsobat.next()) {
                        tabModeobat.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                            rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("harga"), 100),
                            rsobat.getString("nama"), 0, 0, 0, "", rsobat.getString("nama_industri"),
                            rsobat.getDouble("h_beli"), rsobat.getString("kategori"), rsobat.getString("golongan")
                        });
                    }
                } else {
                    psobat.setString(1, "%" + TCari.getText().trim() + "%");
                    psobat.setString(2, "%" + TCari.getText().trim() + "%");
                    psobat.setString(3, "%" + TCari.getText().trim() + "%");
                    psobat.setString(4, "%" + TCari.getText().trim() + "%");
                    psobat.setString(5, "%" + TCari.getText().trim() + "%");
                    rsobat = psobat.executeQuery();
                    while (rsobat.next()) {
                        if (Jeniskelas.getSelectedItem().equals("Karyawan")) {
                            tabModeobat.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("karyawan"), 100),
                                rsobat.getString("nama"), 0, 0, 0, "", rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Rawat Jalan")) {
//                            tabModeobat.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
//                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("ralan"), 100),
//                                rsobat.getString("nama"), 0, 0, 0, "", rsobat.getString("nama_industri"),
//                                rsobat.getDouble("h_beli"), rsobat.getString("kategori"), rsobat.getString("golongan")
//                            });
                            tabModeobat.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), Valid.roundUp(rsobat.getDouble("ralan"), 100), 0,
                                0, 0, "", "", "", "",
                                "", "", "", rsobat.getDouble("h_beli")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Beli Luar")) {
                            tabModeobat.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("beliluar"), 100),
                                rsobat.getString("nama"), 0, 0, 0, "", rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Utama/BPJS")) {
                            tabModeobat.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("utama"), 100),
                                rsobat.getString("nama"), 0, 0, 0, "", rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsobat != null) {
                    rsobat.close();
                }

                if (psobat != null) {
                    psobat.close();
                }

                if (psobatasuransi != null) {
                    psobatasuransi.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampilobat2(String no_resep) {
        this.noresep = no_resep;
        ChkNoResep.setSelected(false);
        Valid.tabelKosong(tabModeobat);

        try {
            psobat = koneksi.prepareStatement(
                    "select databarang.kode_brng, databarang.nama_brng,jenis.nama, "
                    + "databarang.kode_sat,databarang.karyawan,databarang.ralan,"
                    + "databarang.beliluar,databarang.letak_barang,databarang.utama,"
                    + "industrifarmasi.nama_industri,databarang.h_beli,resep_dokter.jml, "
                    + "resep_dokter.aturan_pakai from databarang inner join jenis "
                    + " inner join industrifarmasi inner join resep_dokter on databarang.kdjns=jenis.kdjns "
                    + " and industrifarmasi.kode_industri=databarang.kode_industri "
                    + " and resep_dokter.kode_brng=databarang.kode_brng  "
                    + " where resep_dokter.no_resep=? and databarang.status='1' and databarang.kode_brng like ? or "
                    + " resep_dokter.no_resep=? and databarang.status='1' and databarang.nama_brng like ? or "
                    + " resep_dokter.no_resep=? and databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
            psobatasuransi = koneksi.prepareStatement(
                    "select databarang.kode_brng, databarang.nama_brng,jenis.nama, "
                    + "databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
                    + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli, "
                    + " resep_dokter.jml, resep_dokter.aturan_pakai from databarang inner join jenis "
                    + " inner join industrifarmasi inner join resep_dokter on databarang.kdjns=jenis.kdjns "
                    + " and industrifarmasi.kode_industri=databarang.kode_industri "
                    + " and resep_dokter.kode_brng=databarang.kode_brng  "
                    + " where resep_dokter.no_resep=? and databarang.status='1' and databarang.kode_brng like ? or "
                    + " resep_dokter.no_resep=? and databarang.status='1' and databarang.nama_brng like ? or "
                    + " resep_dokter.no_resep=? and databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
            try {
                if (kenaikan > 0) {
                    psobatasuransi.setDouble(1, kenaikan);
                    psobatasuransi.setString(2, no_resep);
                    psobatasuransi.setString(3, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(4, no_resep);
                    psobatasuransi.setString(5, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(6, no_resep);
                    psobatasuransi.setString(7, "%" + TCari.getText().trim() + "%");
                    rsobat = psobatasuransi.executeQuery();
                    while (rsobat.next()) {
                        tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                            rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("harga"), 100),
                            rsobat.getString("nama"), 0, 0, 0, rsobat.getString("aturan_pakai"), rsobat.getString("nama_industri"),
                            rsobat.getDouble("h_beli")
                        });
                    }
                } else {
                    psobat.setString(1, no_resep);
                    psobat.setString(2, "%" + TCari.getText().trim() + "%");
                    psobat.setString(3, no_resep);
                    psobat.setString(4, "%" + TCari.getText().trim() + "%");
                    psobat.setString(5, no_resep);
                    psobat.setString(6, "%" + TCari.getText().trim() + "%");
                    rsobat = psobat.executeQuery();
                    while (rsobat.next()) {
                        if (Jeniskelas.getSelectedItem().equals("Karyawan")) {
                            tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("karyawan"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("aturan_pakai"), rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Rawat Jalan")) {
                            tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("ralan"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("aturan_pakai"), rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Beli Luar")) {
                            tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("beliluar"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("aturan_pakai"), rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Utama/BPJS")) {
                            tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("utama"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("aturan_pakai"), rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli")
                            });
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsobat != null) {
                    rsobat.close();
                }

                if (psobat != null) {
                    psobat.close();
                }

                if (psobatasuransi != null) {
                    psobatasuransi.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeksobat() {
        Kd2.setText("");
        TCari.setText("");
        TCari.requestFocus();
    }

    private void getDataobat() {
        if (tbObat.getSelectedRow() != -1) {
            Kd2.setText("");
            Kd2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
        }
    }

    public JTextField getTextField() {
        return Kd2;
    }

    public JTable getTable() {
        return tbObat;
    }

    public Button getButton() {
        return BtnSimpan;
    }

    public void isCek() {
//        bangsal=Sequel.cariIsi("select kd_bangsal from set_depo_ralan where kd_poli=?",Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?",TNoRw.getText()));
//        if(bangsal.equals("")){
//            bangsal=bangsaldefault;
//            kdgudang.setEditable(true);
//            nmgudang.setEditable(true);
//            BtnGudang.setEnabled(true);
//        }else{
//            kdgudang.setEditable(false);
//            nmgudang.setEditable(false);
//            BtnGudang.setEnabled(false);
//        }            
//        kdgudang.setText(bangsal);
//        Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?",nmgudang,kdgudang.getText());            
//        BtnTambah.setEnabled(var.getobat());
//        TCari.requestFocus();
        BtnTambah.setEnabled(akses.getobat());
        TCari.requestFocus();
    }

    public void setNoRm(String norwt, String norm, String nama, String tanggal, String jam) {
        TNoRw.setText(norwt);
//        LblNoRawat.setText(norwt);
//        LblNoRM.setText(norm);
//        LblNamaPasien.setText(nama);
        noresep = "";
        Tanggal.setText(tanggal);
        Jam.setText(jam);
        KdPj.setText(Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norwt));
        kenaikan = Sequel.cariIsiAngka("select (hargajual/100) from set_harga_obat_ralan where kd_pj=?", KdPj.getText());
        TCari.requestFocus();
        isPsien();
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkJln.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJln.isSelected() == false) {
                    nilai_jam = cmbJam.getSelectedIndex();
                    nilai_menit = cmbMnt.getSelectedIndex();
                    nilai_detik = cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void isStok(String a) {
        Sequel.cariIsi("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?", TStok, bangsal, a);
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRm, TNoRw.getText());

    }

    private void isSetBangsal() {
//        if (Double.parseDouble(cmbJam.getSelectedItem().toString()) >= 8 && (Double.parseDouble(cmbJam.getSelectedItem().toString()) <= 21)) {
//            //JOptionPane.showMessageDialog(rootPane, "Apotek Sentral");
//            bangsal = Sequel.cariApotek();
//        } else {
//            //JOptionPane.showMessageDialog(rootPane, "Apotek IGD");
//            bangsal = Sequel.cariApotek2();
//        }
        bangsal = Sequel.cariApotek();
        akses.setkdbangsal(bangsal);
    }

    public void setStatus(String stat) {
        status = stat;
    }

    public void isPsien() {
        Sequel.cariIsi("select concat(p.no_rkm_medis,' - ',p.nm_pasien) as identitas_pasien from reg_periksa r "
                + "inner join pasien p on p.no_rkm_medis = r.no_rkm_medis where r.no_rawat=? ", TPasien, TNoRw.getText());
    }

    public void setNoRw(String norm) {
        TNoRw.setText(norm);
        isPsien();
        tampil_resep();   
    }

    public void tampil_resep() {
        Valid.tabelKosong(tabModeResepObat);
        try {
            if (cmbStatus.getSelectedIndex() == 3) {
                ps = koneksi.prepareStatement("select c.no_rawat, c.nama_obat, c.status, c.noId, date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, "
                        + "c.jam_perawatan, d.nm_dokter from catatan_resep c inner join dokter d on d.kd_dokter=c.kd_dokter where "
                        + "c.no_rawat like '%" + TNoRw.getText().trim() + "%' order by c.status, c.noId");
            } else {
                ps = koneksi.prepareStatement("select c.no_rawat, c.nama_obat, c.status, c.noId, date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, "
                        + "c.jam_perawatan, d.nm_dokter from catatan_resep c inner join dokter d on d.kd_dokter=c.kd_dokter where "
                        + "c.no_rawat like '%" + TNoRw.getText().trim() + "%' and c.status like '%" + cmbStatus.getSelectedItem().toString() + "%' "
                        + "order by c.status, c.noId");
            }
            chkResepObat.setSelected(false);
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeResepObat.addRow(new Object[]{
                        false, 
                        rs.getString("no_rawat"),
                        rs.getString("nama_obat"),
                        rs.getString("tgl"), 
                        rs.getString("jam_perawatan"),
                        rs.getString("status"),
                        rs.getString("noId"),
                        rs.getString("nm_dokter")
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
            chkResepObat.setSelected(false);
        }
        LCountRalan.setText("" + tabModeResepObat.getRowCount());        
    }
}
