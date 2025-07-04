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

import bridging.BPJSCekNoKartu;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private final DefaultTableModel tabModeobat, tabModeResepObat, tabModeIter;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement psobat, pscarikapasitas, psobatasuransi, psstok, psIter;
    private PreparedStatement ps, ps1;
    private ResultSet rsobat, carikapasitas, rsstok, rsIter;
    private ResultSet rs, rs1;
    private double x = 0, y = 0, embalase = 0, tuslah = 0, kenaikan = 0, stokbarang = 0, ttl = 0, ppnobat = 0, stokbarang2 = 0;
    private int i = 0, z = 0, cekCat = 0, a = 0, cekResep = 0, urut = 0;
    private boolean[] pilih;
    private double[] jumlah, harga, eb, ts, stok, beli;
    private String[] kodebarang, namabarang, kodesatuan, aturan1, aturan2, aturan3, waktu1, waktu2, keterangan, wktSmpn;
    private String kodedokter = "", namadokter = "", noresep = "", bangsal = "", bangsaldefault = Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1"), tampilkan_ppnobat_ralan = "", status = "";
    private String stat = "", obat = "", nmObat = "", idObat = "", kdUnit = "", programPRB = "", resepObatKronis = "", 
            tglHabisRujukan = "", noRM = "", noSEP = "", noKARTU = "", noRAWATiter = "", pengambilan = "", sttsAmbil = "", poliKe = "", 
            tglhabisRujukan = "", kdpoliIter = "", tglAmbilObat = "";
    private DlgCariBangsal caribangsal = new DlgCariBangsal(null, false);
    public DlgBarang barang = new DlgBarang(null, false);
    public DlgAturanPakai aturanpakai = new DlgAturanPakai(null, false);
    private WarnaTable2 warna = new WarnaTable2();
    private riwayatobat Trackobat = new riwayatobat();
    public DlgCatatanResep dlgCatatanResep = new DlgCatatanResep(null, false);
    private BPJSCekNoKartu cekViaBPJSKartu = new BPJSCekNoKartu();
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date tglSekarang = new Date();
    private Date tglExpRujukan = new Date();
    private String now = dateFormat.format(tglSekarang);

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

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 1' and status='Aktif'", comboAt1);
        AutoCompleteDecorator.decorate(comboAt1);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 2' and status='Aktif'", comboAt2);
        AutoCompleteDecorator.decorate(comboAt2);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'aturan pakai 3' and status='Aktif'", comboAt3);
        AutoCompleteDecorator.decorate(comboAt3);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'waktu 1' and status='Aktif'", comboWk1);
        AutoCompleteDecorator.decorate(comboWk1);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'waktu 2' and status='Aktif'", comboWk2);
        AutoCompleteDecorator.decorate(comboWk2);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'keterangan' and status='Aktif'", comboKet);
        AutoCompleteDecorator.decorate(comboKet);

        Sequel.cariIsiComboDB("select nama from master_aturan_pakai where opsi = 'masa simpan' and status='Aktif'", comboMs);
        AutoCompleteDecorator.decorate(comboMs);

        Object[] row = {"K", "Jumlah", "Kode Barang", "Nama Barang", "Satuan",
            "Harga(Rp)", "Embalase", "Tuslah", "Stok", "Aturan Pakai 1",
            "Aturan Pakai 2", "Aturan Pakai 3", "Waktu 1", "Waktu 2", "Keterangan", "Masa Simpan", "H.Beli"
        };
        tabModeobat = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = true;
                if ((colIndex == 2) || (colIndex == 3) || (colIndex == 4) || (colIndex == 5) || (colIndex == 8)) {
                    a = false;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbObat.setModel(tabModeobat);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

        tabModeIter = new DefaultTableModel(null, new String[]{
            "Kode Iter", "no_sep", "no_kartu", "No. RM", "Nama Pasien", "no_rawat", "Poliklinik", "Pengambilan Ke",
            "Tgl. Ambil Obat", "Status Pengambilan", "poli_ke", "tgl_exp_rujukan", "kd_poli", "tgl_ambil_obat", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbResepIter.setModel(tabModeIter);
        tbResepIter.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepIter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbResepIter.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(110);
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
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbResepIter.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
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
            "P", "No.Rawat", "Nama Obat", "Tgl. Resep", "Jam Input", "Status", "ID", "Nama Dokter", "Program PRB"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbResepObat.setModel(tabModeResepObat);
        tbResepObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
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
            } else if (i == 8) {
                column.setPreferredWidth(80);
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
        Popup1 = new javax.swing.JPopupMenu();
        ppResepIterSelesai = new javax.swing.JMenuItem();
        ppResepIterProses = new javax.swing.JMenuItem();
        ppResepIterMenunggu = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        Tanggal = new widget.TextBox();
        Jam = new widget.TextBox();
        KdPj = new widget.TextBox();
        TStok = new widget.TextBox();
        TNoRm = new widget.TextBox();
        kode_iter = new widget.TextBox();
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
        ChkResepKronis = new widget.CekBox();
        BtnCekIter = new widget.Button();
        FormInput2 = new widget.PanelBiasa();
        Scroll3 = new widget.ScrollPane();
        tbResepObat = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbResepIter = new widget.Table();
        panelisi4 = new widget.panelisi();
        chkResepObat = new widget.CekBox();
        BtnVerif = new widget.Button();
        label13 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnCekResep = new widget.Button();
        label14 = new widget.Label();
        cmbKertas = new widget.ComboBox();
        BtnCetak = new widget.Button();
        BtnCetak1 = new widget.Button();
        jLabel9 = new widget.Label();
        LCountRalan = new widget.Label();
        BtnResepIter = new widget.Button();
        BtnResepIterBatal = new widget.Button();

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

        Popup1.setName("Popup1"); // NOI18N

        ppResepIterSelesai.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppResepIterSelesai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppResepIterSelesai.setText("Kode Resep Iter Selesai");
        ppResepIterSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppResepIterSelesai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppResepIterSelesai.setIconTextGap(8);
        ppResepIterSelesai.setName("ppResepIterSelesai"); // NOI18N
        ppResepIterSelesai.setPreferredSize(new java.awt.Dimension(220, 25));
        ppResepIterSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppResepIterSelesaiActionPerformed(evt);
            }
        });
        Popup1.add(ppResepIterSelesai);

        ppResepIterProses.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppResepIterProses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppResepIterProses.setText("Kode Resep Iter Proses Pelayanan");
        ppResepIterProses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppResepIterProses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppResepIterProses.setIconTextGap(8);
        ppResepIterProses.setName("ppResepIterProses"); // NOI18N
        ppResepIterProses.setPreferredSize(new java.awt.Dimension(220, 25));
        ppResepIterProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppResepIterProsesActionPerformed(evt);
            }
        });
        Popup1.add(ppResepIterProses);

        ppResepIterMenunggu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppResepIterMenunggu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppResepIterMenunggu.setText("Kode Resep Iter Menunggu");
        ppResepIterMenunggu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppResepIterMenunggu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppResepIterMenunggu.setIconTextGap(8);
        ppResepIterMenunggu.setName("ppResepIterMenunggu"); // NOI18N
        ppResepIterMenunggu.setPreferredSize(new java.awt.Dimension(220, 25));
        ppResepIterMenunggu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppResepIterMenungguActionPerformed(evt);
            }
        });
        Popup1.add(ppResepIterMenunggu);

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

        kode_iter.setEditable(false);
        kode_iter.setForeground(new java.awt.Color(0, 0, 0));
        kode_iter.setName("kode_iter"); // NOI18N

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
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-07-2025" }));
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

        ChkResepKronis.setBorder(null);
        ChkResepKronis.setForeground(new java.awt.Color(0, 0, 0));
        ChkResepKronis.setText("Diantara Item Resep Terdapat Jenis Obat Kronis");
        ChkResepKronis.setBorderPainted(true);
        ChkResepKronis.setBorderPaintedFlat(true);
        ChkResepKronis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkResepKronis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkResepKronis.setName("ChkResepKronis"); // NOI18N
        ChkResepKronis.setOpaque(false);
        ChkResepKronis.setPreferredSize(new java.awt.Dimension(85, 23));
        FormInput1.add(ChkResepKronis);
        ChkResepKronis.setBounds(635, 57, 270, 23);

        BtnCekIter.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekIter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCekIter.setMnemonic('1');
        BtnCekIter.setText("Cek Resep Iter");
        BtnCekIter.setToolTipText("Alt+1");
        BtnCekIter.setName("BtnCekIter"); // NOI18N
        BtnCekIter.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCekIter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekIterActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCekIter);
        BtnCekIter.setBounds(920, 57, 130, 23);

        FormInput.add(FormInput1, java.awt.BorderLayout.PAGE_START);

        FormInput2.setBackground(new java.awt.Color(215, 225, 215));
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(100, 92));
        FormInput2.setLayout(new java.awt.GridLayout(1, 2));

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

        FormInput2.add(Scroll3);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Kode Resep Obat Iter ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setPreferredSize(new java.awt.Dimension(660, 422));

        tbResepIter.setAutoCreateRowSorter(true);
        tbResepIter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbResepIter.setToolTipText("Silahkan klik/pilih salah satu datanya untuk cek resep iter");
        tbResepIter.setComponentPopupMenu(Popup1);
        tbResepIter.setName("tbResepIter"); // NOI18N
        tbResepIter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResepIterMouseClicked(evt);
            }
        });
        tbResepIter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbResepIterKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbResepIter);

        FormInput2.add(Scroll4);

        FormInput.add(FormInput2, java.awt.BorderLayout.CENTER);

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
        BtnCetak.setPreferredSize(new java.awt.Dimension(125, 23));
        BtnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCetak);

        BtnCetak1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetak1.setMnemonic('C');
        BtnCetak1.setText("Cetak Kode Iter");
        BtnCetak1.setToolTipText("Alt+C");
        BtnCetak1.setIconTextGap(7);
        BtnCetak1.setName("BtnCetak1"); // NOI18N
        BtnCetak1.setPreferredSize(new java.awt.Dimension(145, 23));
        BtnCetak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetak1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCetak1);

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

        BtnResepIter.setForeground(new java.awt.Color(0, 0, 0));
        BtnResepIter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/FirstAidKit.png"))); // NOI18N
        BtnResepIter.setMnemonic('9');
        BtnResepIter.setText("Simpan Resep Iter BPJS");
        BtnResepIter.setToolTipText("Alt+9");
        BtnResepIter.setName("BtnResepIter"); // NOI18N
        BtnResepIter.setPreferredSize(new java.awt.Dimension(190, 23));
        BtnResepIter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepIterActionPerformed(evt);
            }
        });
        panelisi4.add(BtnResepIter);

        BtnResepIterBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnResepIterBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnResepIterBatal.setText("Batal Resep Iter");
        BtnResepIterBatal.setName("BtnResepIterBatal"); // NOI18N
        BtnResepIterBatal.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnResepIterBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepIterBatalActionPerformed(evt);
            }
        });
        panelisi4.add(BtnResepIterBatal);

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
        barang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
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
                                            cekResepObatKronisIter();
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
                                            cekResepObatKronisIter();
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
                                    cekResepObatKronisIter();
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
                    resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate(), cmbJam.getSelectedItem().toString(),
                            cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), kdUnit);
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
        tampil_resep();
        tampilResepIter();        
        
        if (noresep.equals("")) {
            tampilobat();
            isPsien();            
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkNoResepItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkNoResepItemStateChanged
        if (ChkNoResep.isSelected() == true) {
            DlgResepObat resep = new DlgResepObat(null, false);
            resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            resep.setLocationRelativeTo(internalFrame1);
            resep.emptTeks();
            resep.isCek();
            resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate(),
                    cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), kdUnit);
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
            
            if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'") == 0) {
                param.put("nosep", "-");
            } else {
                param.put("nosep", Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2' order by tglsep desc limit 1"));
            }

            Valid.MyReport("rptResepRalan.jasper", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                    " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, c.nama_obat, "
                    + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp,'-') noHP from catatan_resep c "
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
                programPRB = "";
                resepObatKronis = "";
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        if (idObat.equals("")) {
                            idObat = "'" + tbResepObat.getValueAt(i, 6).toString() + "'";
                        } else {
                            idObat = idObat + ",'" + tbResepObat.getValueAt(i, 6).toString() + "'";
                        }
                    }
                }
                
                //cek program prb
                if (Sequel.cariInteger("select count(-1) from bridging_srb_bpjs where no_srb='" + TNoRw.getText() + "'") > 0) {
                    programPRB = " (" + Sequel.cariIsi("select if(count(-1)>0,'Program PRB','-') from bridging_srb_bpjs where no_srb='" + TNoRw.getText() + "'") + ")";
                } else {
                    programPRB = "";
                }
                
                //cek resep obat kronis
                if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2' and sep_resep_obat_kronis='ya'") > 0) {
                    resepObatKronis = "Resep dalam kategori obat kronis";
                } else {
                    resepObatKronis = "-";
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
                    param.put("carabyr", Sequel.cariIsi("select pj.png_jawab from reg_periksa rp inner join penjab pj on pj.kd_pj=rp.kd_pj "
                            + "where rp.no_rawat='" + TNoRw.getText() + "'"));
                    param.put("nosep", Sequel.cariIsi("select ifnull(no_sep,'-') from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'") + "" + programPRB);
                    param.put("ketResep", resepObatKronis);
                    
                    Valid.MyReport("rptCatatanResepRalan.jasper", "report", "::[ Cetak e-Resep ]::",
                            "SELECT pl.nm_poli, date_format(cr.tgl_perawatan,'%d-%m-%Y') tgl, d.nm_dokter, cr.no_rawat, p.no_rkm_medis, "
                            + "p.nm_pasien, ifnull(p.no_tlp,'-') no_hp, cr.nama_obat, concat(date_format(p.tgl_lahir,'%d/%m/%Y'),' (Usia : ',rp.umurdaftar,' ',rp.sttsumur,'.)') tgllahir "
                            + "FROM catatan_resep cr INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN dokter d ON d.kd_dokter=cr.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                            + "WHERE cr.noId in (" + idObat + ") ORDER BY cr.tgl_perawatan DESC, cr.jam_perawatan DESC, cr.noId DESC", param);

                } else if (cmbKertas.getSelectedIndex() == 1) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("nosep", Sequel.cariIsi("select ifnull(no_sep,'-') from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'") + "" + programPRB);
                    param.put("tglcetak", Sequel.cariIsi("select concat(date_format(date(now()),'%d/%m/%Y'),', Jam : ',time(now()),' Wita')"));
                    param.put("ketResep", resepObatKronis);

                    Valid.MyReport("rptStrukResepRalan.jasper", "report", "::[ Struk Resep Dokter Poliklinik/Unit Rawat Jalan Kertas Thermal ]::",
                            " SELECT pl.nm_poli, date_format(cr.tgl_perawatan,'%d-%m-%Y') tgl, d.nm_dokter, cr.no_rawat, p.no_rkm_medis, "
                            + "p.nm_pasien, ifnull(p.no_tlp,'-') no_hp, cr.nama_obat, concat(date_format(p.tgl_lahir,'%d/%m/%Y'),' (Usia : ',rp.umurdaftar,' ',rp.sttsumur,'.)') tgllahir "
                            + "FROM catatan_resep cr INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN dokter d ON d.kd_dokter=cr.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                            + "WHERE cr.noId in (" + idObat + ") ORDER BY cr.tgl_perawatan DESC, cr.jam_perawatan DESC, cr.noId DESC", param);
                    
                }
            }
        }
    }//GEN-LAST:event_BtnCetakActionPerformed

    private void BtnResepIterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepIterActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            if (KdPj.getText().equals("B01") || KdPj.getText().equals("A03")) {
                if (Sequel.cariInteger("select count(-1) from catatan_resep where no_rawat='" + TNoRw.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, belum ada resep yang disimpan utk. kunjungan dipoli " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdUnit + "'") + " ...!!!!");
                } else if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs i inner join reg_periksa rp on rp.no_rawat=i.no_rawat where "
                        + "i.no_rkm_medis='" + noRM + "' and rp.kd_poli='" + kdUnit + "' and convert(i.kunjungan,int)<=3") == 0) {
                    if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where no_rawat='" + TNoRw.getText() + "' and (stts_pengambilan='Proses pelayanan' or stts_pengambilan='Menunggu')") > 0) {
                        JOptionPane.showMessageDialog(null, "Kode resep obat iter yang masih dalam proses pelayanan, harus diselesaikan dulu...!!!!");
                        tampilResepIter();
                    } else if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2' limit 1") == 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, pasien belum dibuatkan SEP rawat jalan utk. kunjungan dipoli ini...!!!!");
                        tampilResepIter();
                    } else {
                        JOptionPane.showMessageDialog(null, "Kode resep iter pertama dari poli " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdUnit + "'") + " "
                                + "hanya bisa dibuatkan oleh dokter dari poliklinik...!!!!");
                        tampilResepIter();
                        //menunggu arahan berikutnya jika diaktifkan
//                        dataIterObatBPJSbaru(TNoRw.getText(), noRM, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
                    }
                } else if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs i inner join reg_periksa rp on rp.no_rawat=i.no_rawat where "
                        + "i.no_rkm_medis='" + noRM + "' and rp.kd_poli='" + kdUnit + "' and convert(i.kunjungan,int)<=3") > 0) {
                    if (tbResepIter.getSelectedRow() > -1) {
                        if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where kode_iter='" + kode_iter.getText() + "'") >= 3) {
                            JOptionPane.showMessageDialog(null, "Kode resep obat iter " + kode_iter.getText() + " sudah tersimpan 3 kali...!!!!");
                            tampilResepIter();
                        } else if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs i inner join reg_periksa rp on rp.no_rawat=i.no_rawat where "
                                + "i.kode_iter='" + kode_iter.getText() + "' and i.kunjungan='3' and rp.kd_poli='" + kdpoliIter + "'") > 0) {
                            JOptionPane.showMessageDialog(null, "Untuk kunjungan dipoli " + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 6).toString() + ",     \n"
                                    + "dengan kode resep obat iter " + kode_iter.getText() + " sudah tersimpan 3 kali...!!!!");
                            tampilResepIter();
                        } else if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where "
                                + "waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "' and stts_pengambilan='Menunggu'") > 0) {
                            JOptionPane.showMessageDialog(null, "Pengambilan obat yg. ke " + pengambilan + ", utk. kunjungan dipoli " + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 6).toString() + ",     \n"
                                    + "dg. kode resep obat iter " + kode_iter.getText() + " dalam status menunggu pengambilan berikutnya...!!!!");
                            tampilResepIter();
                        } else {
                            dataIterObatBPJSlanjut();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Silahkan klik/pilih dulu salah satu kode resep obat iter yg. sudah selesai pengambilanya pada tabel...!!!!");
                        tampilResepIter();
                        tbResepIter.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Resep iter hanya untuk resep pasien BPJS saja...!!!!");
            }
        }
    }//GEN-LAST:event_BtnResepIterActionPerformed

    private void BtnResepIterBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepIterBatalActionPerformed
        if (tbResepIter.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah kode resep iter " + kode_iter.getText() + " utk. pengambilan yg. ke " + pengambilan + " akan dibatalkan..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "' "
                        + "and (stts_pengambilan='Selesai' or stts_pengambilan='Proses pelayanan')") > 0) {
                    JOptionPane.showMessageDialog(null, "Untuk pengambilan obat ke " + pengambilan + " dengan kode resep obat iter " + kode_iter.getText() + " status pengambilanya adalah " + sttsAmbil + "..!!");
                    tampilResepIter();
                } else {
                    if (Sequel.queryu2tf("delete from iter_obat_bpjs where waktu_simpan=?", 1, new String[]{
                        tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString()
                    }) == true) {
                        tampilResepIter();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
            } else {
                tampilResepIter();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih dulu salah satu kode resep obat iter pada tabel...!!!!");
            tampilResepIter();
            tbResepIter.requestFocus();
        }
    }//GEN-LAST:event_BtnResepIterBatalActionPerformed

    private void tbResepIterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResepIterKeyPressed
        if (tabModeIter.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIter();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbResepIterKeyPressed

    private void tbResepIterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResepIterMouseClicked
        if (tabModeIter.getRowCount() != 0) {
            try {
                getDataIter();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbResepIterMouseClicked

    private void BtnCetak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetak1ActionPerformed
        if (tbResepIter.getSelectedRow() > -1) {
            if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "' "
                    + "and (stts_pengambilan='Selesai' or stts_pengambilan='Proses pelayanan')") > 0) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih/klik salah satu yg. status pengambilanya MENUNGGU utk. mencetak bukti pengambilan     \n"
                        + "obat berikutnya, jika belum ada datanya lakukan simpan resep iter dulu...!!!!");
                tampilResepIter();
            } else {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("kotars", akses.getkabupatenrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("poliAwal", Sequel.cariIsi("select concat(pl.nm_poli,' (',date_format(rp.tgl_registrasi,'%d/%m/%Y'),')') from iter_obat_bpjs i "
                        + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat inner join poliklinik pl on pl.kd_poli=rp.kd_poli where "
                        + "i.kode_iter='" + kode_iter.getText() + "' and i.kunjungan='1'"));
                param.put("dokterAwal", Sequel.cariIsi("select pg.nama from iter_obat_bpjs i inner join reg_periksa rp on rp.no_rawat=i.no_rawat "
                        + "inner join pegawai pg on pg.nik=rp.kd_dokter where i.kode_iter='" + kode_iter.getText() + "' and i.kunjungan='1'"));
                Valid.cetakQr(kode_iter.getText(), Sequel.cariFolderKodeResepIter(), "QRkodeIter.jpg");
                Sequel.queryu("delete from setting_qr where judul = 'QRkodeIter'");
                Sequel.menyimpanQr("setting_qr", "'QRkodeIter'", "file QRCode Kode Resep Iter", Sequel.cariFolderPrintKodeIter());
                param.put("lokasi", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRkodeIter'"));
                param.put("wktuCetak", Sequel.cariIsi("select date_format(now(),'%d/%m/%Y %H:%i Wita')"));

                Valid.MyReport("rptKodeIterThermal.jasper", "report", "::[ Cetak Bukti Pengambilan Resep Iter Yang Ke-" + pengambilan + " ]::",
                        "SELECT i.*, p.nm_pasien, pl.nm_poli, CONCAT(DAY(i.tgl_exp_rujukan), ' ', CASE MONTH(i.tgl_exp_rujukan) "
                        + "WHEN 1 THEN 'Januari' "
                        + "WHEN 2 THEN 'Februari' "
                        + "WHEN 3 THEN 'Maret' "
                        + "WHEN 4 THEN 'April' "
                        + "WHEN 5 THEN 'Mei' "
                        + "WHEN 6 THEN 'Juni' "
                        + "WHEN 7 THEN 'Juli' "
                        + "WHEN 8 THEN 'Agustus' "
                        + "WHEN 9 THEN 'September' "
                        + "WHEN 10 THEN 'Oktober' "
                        + "WHEN 11 THEN 'November' "
                        + "WHEN 12 THEN 'Desember' END,' ',YEAR(i.tgl_exp_rujukan)) tglExpRujukan, "
                        + "if(i.kunjungan='1','Pertama (1)',if(i.kunjungan='2','Kedua (2)','Ketiga (3)')) pengambilan FROM iter_obat_bpjs i "
                        + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli where i.waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "'", param);

                tampilResepIter();
            }
        } else {
            if (tbResepIter.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, belum ada kode resep obat iter dari poliklinik..!!");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel kode resep obat iter..!!");                
            }
            tampilResepIter();            
        }
    }//GEN-LAST:event_BtnCetak1ActionPerformed

    private void BtnCekIterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekIterActionPerformed
        tampilResepIter();
    }//GEN-LAST:event_BtnCekIterActionPerformed

    private void ppResepIterSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppResepIterSelesaiActionPerformed
        if (tbResepIter.getSelectedRow() > -1) {
            if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "' "
                    + "and stts_pengambilan='Selesai'") > 0) {
                JOptionPane.showMessageDialog(null, "Kode resep obat iter yang dipilih sudah menyelesaikan pengambilan obatnya...!!!!");
                tampilResepIter();
            } else {
                Sequel.mengedit("iter_obat_bpjs", "waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "'",
                        "tgl_ambil_obat='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "', stts_pengambilan='Selesai'");
                tampilResepIter();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih dulu salah satu kode resep obat iter pada tabel...!!!!");
            tampilResepIter();
            tbResepIter.requestFocus();
        }
    }//GEN-LAST:event_ppResepIterSelesaiActionPerformed

    private void ppResepIterProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppResepIterProsesActionPerformed
        if (tbResepIter.getSelectedRow() > -1) {
            if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "' "
                    + "and stts_pengambilan='Proses pelayanan'") > 0) {
                JOptionPane.showMessageDialog(null, "Kode resep obat iter yang dipilih sudah dalam proses pelayanan...!!!!");
                tampilResepIter();
            } else {
                Sequel.mengedit("iter_obat_bpjs", "waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "'",
                        "tgl_ambil_obat='" + Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + noRAWATiter + "'") + "', "
                        + "stts_pengambilan='Proses pelayanan'");
                tampilResepIter();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih dulu salah satu kode resep obat iter pada tabel...!!!!");
            tampilResepIter();
            tbResepIter.requestFocus();
        }
    }//GEN-LAST:event_ppResepIterProsesActionPerformed

    private void ppResepIterMenungguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppResepIterMenungguActionPerformed
        if (tbResepIter.getSelectedRow() > -1) {
            if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "' "
                    + "and stts_pengambilan='Menunggu'") > 0) {
                JOptionPane.showMessageDialog(null, "Kode resep obat iter yang dipilih sudah dalam status menunggu...!!!!");
                tampilResepIter();
            } else {
                Sequel.mengedit("iter_obat_bpjs", "waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "'",
                        "tgl_ambil_obat='0000-00-00', stts_pengambilan='Menunggu'");
                tampilResepIter();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih dulu salah satu kode resep obat iter pada tabel...!!!!");
            tampilResepIter();
            tbResepIter.requestFocus();
        }
    }//GEN-LAST:event_ppResepIterMenungguActionPerformed

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
    private widget.Button BtnCekIter;
    private widget.Button BtnCekResep;
    private widget.Button BtnCetak;
    private widget.Button BtnCetak1;
    private widget.Button BtnKeluar;
    private widget.Button BtnResepIter;
    private widget.Button BtnResepIterBatal;
    private widget.Button BtnSeek5;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah;
    private widget.Button BtnVerif;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkNoResep;
    private widget.CekBox ChkResepKronis;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
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
    private javax.swing.JPopupMenu Popup1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
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
    private widget.TextBox kode_iter;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppResepIterMenunggu;
    private javax.swing.JMenuItem ppResepIterProses;
    private javax.swing.JMenuItem ppResepIterSelesai;
    private javax.swing.JMenuItem ppStok;
    private widget.Table tbObat;
    private widget.Table tbResepIter;
    private widget.Table tbResepObat;
    // End of variables declaration//GEN-END:variables

    public void tampilobat() {
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

        for (i = 0; i < z; i++) {
            tabModeobat.addRow(new Object[]{
                pilih[i], jumlah[i], kodebarang[i], namabarang[i], kodesatuan[i], harga[i], eb[i], ts[i], stok[i], aturan1[i], aturan2[i], aturan3[i], waktu1[i], waktu2[i], keterangan[i], wktSmpn[i], beli[i]
            });
        }

        try {
            if (akses.getkdbangsal().equals("APT07")) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.karyawan,databarang.ralan,databarang.beliluar,");
                sb1.append(" databarang.letak_barang,databarang.utama,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns ");
                sb1.append(" and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or ");
                sb1.append(" databarang.status='1' and databarang.nama_brng like ? or ");
                sb1.append(" databarang.status='1' and kategori_barang.nama like ? or ");
                sb1.append(" databarang.status='1' and golongan_barang.nama like ? or ");
                sb1.append(" databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
                psobat = koneksi.prepareStatement(sb1.toString());
                
                StringBuilder sb2 = new StringBuilder();
                sb2.append("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,");
                sb2.append(" databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns ");
                sb2.append(" and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or ");
                sb2.append(" databarang.status='1' and databarang.nama_brng like ? or ");
                sb2.append(" databarang.status='1' and kategori_barang.nama like ? or ");
                sb2.append(" databarang.status='1' and golongan_barang.nama like ? or ");
                sb2.append(" databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
                psobatasuransi = koneksi.prepareStatement(sb2.toString());

            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.karyawan,databarang.ralan,databarang.beliluar,");
                sb3.append(" databarang.letak_barang,databarang.utama,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns ");
                sb3.append(" and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.kode_brng like ? or ");
                sb3.append(" databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or ");
                sb3.append(" databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or ");
                sb3.append(" databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or ");
                sb3.append(" databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");
                psobat = koneksi.prepareStatement(sb3.toString());

                StringBuilder sb4 = new StringBuilder();
                sb4.append("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,");
                sb4.append(" databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang on databarang.kdjns=jenis.kdjns ");
                sb4.append(" and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.kode_brng like ? or ");
                sb4.append(" databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or ");
                sb4.append(" databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or ");
                sb4.append(" databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or ");
                sb4.append(" databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");
                psobatasuransi = koneksi.prepareStatement(sb4.toString());
            }

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
            StringBuilder sb5 = new StringBuilder();
            sb5.append("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.karyawan,databarang.ralan, ");
            sb5.append("databarang.beliluar,databarang.letak_barang,databarang.utama, industrifarmasi.nama_industri,databarang.h_beli,resep_dokter.jml, ");
            sb5.append("resep_dokter.aturan_pakai from databarang inner join jenis inner join industrifarmasi inner join resep_dokter on databarang.kdjns=jenis.kdjns ");
            sb5.append("and industrifarmasi.kode_industri=databarang.kode_industri and resep_dokter.kode_brng=databarang.kode_brng ");
            sb5.append("where resep_dokter.no_resep=? and databarang.status='1' and databarang.kode_brng like ? or ");
            sb5.append("resep_dokter.no_resep=? and databarang.status='1' and databarang.nama_brng like ? or ");
            sb5.append("resep_dokter.no_resep=? and databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
            psobat = koneksi.prepareStatement(sb5.toString());
            
            StringBuilder sb6 = new StringBuilder();
            sb6.append("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga, ");
            sb6.append("databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli, resep_dokter.jml, resep_dokter.aturan_pakai from databarang inner join jenis ");
            sb6.append("inner join industrifarmasi inner join resep_dokter on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri ");
            sb6.append("and resep_dokter.kode_brng=databarang.kode_brng where resep_dokter.no_resep=? and databarang.status='1' and databarang.kode_brng like ? or ");
            sb6.append("resep_dokter.no_resep=? and databarang.status='1' and databarang.nama_brng like ? or ");
            sb6.append("resep_dokter.no_resep=? and databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
            psobatasuransi = koneksi.prepareStatement(sb6.toString());

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
        ChkResepKronis.setSelected(false);
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

        ppResepIterProses.setEnabled(akses.getadmin());
        ppResepIterMenunggu.setEnabled(akses.getadmin());
        BtnTambah.setEnabled(akses.getobat());
        TCari.requestFocus();
        
        if (akses.getadmin() == true) {
            BtnResepIter.setVisible(true);
            BtnResepIterBatal.setVisible(true);
            BtnCetak1.setVisible(true);
            BtnCekIter.setVisible(true);
            Scroll4.setVisible(true);
        } else {
            BtnResepIter.setVisible(false);
            BtnResepIterBatal.setVisible(false);
            BtnCetak1.setVisible(false);
            BtnCekIter.setVisible(false);
            Scroll4.setVisible(false);
        }
    }

    public void setNoRm(String norwt, String norm, String nama, String tanggal, String jam, String kodeUnit) {
        noRM = norm;
        TNoRw.setText(norwt);
        noresep = "";
        kdUnit = kodeUnit;
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
        StringBuilder sb = new StringBuilder();
        try {
            if (cmbStatus.getSelectedIndex() == 3) {
                sb.append("select c.no_rawat, c.nama_obat, c.status, c.noId, date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, ");
                sb.append("c.jam_perawatan, d.nm_dokter, if(prb.saran is null,'TIDAK','YA') programPrb from catatan_resep c inner join dokter d on d.kd_dokter=c.kd_dokter ");
                sb.append("left join bridging_srb_bpjs prb on prb.no_srb=c.no_rawat and prb.keterangan=c.noID where ");
                sb.append("c.no_rawat like '%" + TNoRw.getText().trim() + "%' order by c.status, c.noId");
                ps = koneksi.prepareStatement(sb.toString());
                
            } else {
                sb.append("select c.no_rawat, c.nama_obat, c.status, c.noId, date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, ");
                sb.append("c.jam_perawatan, d.nm_dokter, if(prb.saran is null,'TIDAK','YA') programPrb from catatan_resep c inner join dokter d on d.kd_dokter=c.kd_dokter ");
                sb.append("left join bridging_srb_bpjs prb on prb.no_srb=c.no_rawat and prb.keterangan=c.noID where ");
                sb.append("c.no_rawat like '%" + TNoRw.getText().trim() + "%' and c.status like '%" + cmbStatus.getSelectedItem().toString() + "%' ");
                sb.append("order by c.status, c.noId");
                ps = koneksi.prepareStatement(sb.toString());
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
                        rs.getString("nm_dokter"),
                        rs.getString("programPrb")
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
    
    private void cekResepObatKronisIter() {
        if (ChkResepKronis.isSelected() == true) {
            Sequel.mengedit("bridging_sep", "no_rawat='" + TNoRw.getText() + "'", "sep_resep_obat_kronis='ya'");
            Sequel.mengedit("bridging_sep_backup", "no_rawat='" + TNoRw.getText() + "'", "sep_resep_obat_kronis='ya'");
            Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "sep_resep_obat_kronis='ya'");
        } else {
            Sequel.mengedit("bridging_sep", "no_rawat='" + TNoRw.getText() + "'", "sep_resep_obat_kronis='tidak'");
            Sequel.mengedit("bridging_sep_backup", "no_rawat='" + TNoRw.getText() + "'", "sep_resep_obat_kronis='tidak'");
            Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "sep_resep_obat_kronis='tidak'");
        }
        
        //resep iter
        if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where no_rawat='" + TNoRw.getText() + "' and stts_pengambilan='Proses pelayanan'") > 0) {
            Sequel.mengedit("iter_obat_bpjs", "no_rawat='" + TNoRw.getText() + "'",
                    "tgl_ambil_obat='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "', stts_pengambilan='Selesai'");
        }
    }
    
    private void dataIterObatBPJSlanjut() {
        String angkaKun = "";
        tglHabisRujukan = "";
        tglSekarang = new Date();
        tglExpRujukan = new Date();
        now = dateFormat.format(tglSekarang);

        try {
            tglHabisRujukan = tglhabisRujukan;
            angkaKun = Sequel.cariIsi("select ifnull(MAX(convert(i.kunjungan,int))+1,1) from iter_obat_bpjs i "
                    + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat where i.no_sep='" + noSEP + "' and i.kode_iter='" + kode_iter.getText() + "' "
                    + "and rp.kd_poli='" + kdpoliIter + "'");
            
            cekViaBPJSKartu.tampil(noKARTU, Sequel.cariIsi("SELECT DATE(NOW())"));
            if (cekViaBPJSKartu.informasi.equals("OK")) {
                if (cekViaBPJSKartu.statusPesertaketerangan.equals("AKTIF")) {
                    //cek verifikasi tanggal masa berlaku surat rujukan
                    try {
                        tglSekarang = new SimpleDateFormat("yyyy-MM-dd").parse(now);
                        tglExpRujukan = new SimpleDateFormat("yyyy-MM-dd").parse(tglHabisRujukan);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }

                    if (tglSekarang.before(tglExpRujukan)) {
                        Sequel.mengedit("iter_obat_bpjs", "waktu_simpan='" + tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 14).toString() + "'",
                                "tgl_ambil_obat='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "', stts_pengambilan='Selesai'");

                        Sequel.menyimpanIgnore("iter_obat_bpjs", "'" + kode_iter.getText() + "','" + noSEP + "','" + noKARTU + "',"
                                + "'" + noRM + "','" + noRAWATiter + "','" + angkaKun + "','" + tglHabisRujukan + "','Menunggu','" + poliKe + "','0000-00-00',"
                                + "'" + Sequel.cariIsi("select now()") + "'", "Iter Obat BPJS");

                        tampilResepIter();
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, surat rujukan pasien telah berakhir pada tgl. " + Valid.SetTglINDONESIA(tglHabisRujukan) + " ...!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Status kepesertaan pasien ini tidak aktif..!!");
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void dataIterObatBPJSbaru(String nomorrawat, String norekmed, String tglReg) {
        String noSep = "", noKartu = "", iterKe = "", sepSama = "";
        tglHabisRujukan = "";
        kode_iter.setText("");
        tglSekarang = new Date();
        tglExpRujukan = new Date();
        now = dateFormat.format(tglSekarang);

        try {
            if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + nomorrawat + "' and jnspelayanan='2' limit 1") == 0) {
                psIter = koneksi.prepareStatement("select * from bridging_sep where nomr='" + norekmed + "' and tglsep='" + tglReg + "' and jnspelayanan='2' no_limit 1");
                sepSama = "ya";
            } else {
                psIter = koneksi.prepareStatement("select * from bridging_sep where no_rawat='" + nomorrawat + "' and jnspelayanan='2' limit 1");
                sepSama = "tidak";
            }

            try {
                rsIter = psIter.executeQuery();
                while (rsIter.next()) {
                    noSep = rsIter.getString("no_sep");
                    noKartu = rsIter.getString("no_kartu");
                    tglHabisRujukan = Sequel.cariIsi("SELECT DATE_ADD('" + rsIter.getString("tglrujukan") + "', INTERVAL 89 DAY)");
                    iterKe = Sequel.cariIsi("select ifnull(MAX(convert(poli_ke,int))+1,1) from iter_obat_bpjs where no_sep='" + noSep + "'");
                    
                    if (sepSama.equals("tidak")) {
                        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kode_iter,4),signed)),0) from iter_obat_bpjs where "
                                + "date(waktu_simpan) like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%' and poli_ke='" + iterKe + "' ",
                                "/ITER-" + iterKe + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7)
                                + "" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4), 4, kode_iter);
                    } else {
                        if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where no_sep='" + noSep + "'") > 0) {
                            kode_iter.setText(Sequel.cariIsi("select replace(kode_iter,SUBSTRING_INDEX(SUBSTRING_INDEX(kode_iter, '/', 2), '/', -1),'ITER-" + iterKe + "') from iter_obat_bpjs "
                                    + "where no_sep='" + noSep + "' order by waktu_simpan desc limit 1"));
                        } else {
                            Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kode_iter,4),signed)),0) from iter_obat_bpjs where "
                                    + "date(waktu_simpan) like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%' and poli_ke='" + iterKe + "' ",
                                    "/ITER-" + iterKe + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7)
                                    + "" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4), 4, kode_iter);
                        }
                    }

                    cekViaBPJSKartu.tampil(noKartu, Sequel.cariIsi("SELECT DATE(NOW())"));
                    if (cekViaBPJSKartu.informasi.equals("OK")) {
                        if (cekViaBPJSKartu.statusPesertaketerangan.equals("AKTIF")) {
                            //cek verifikasi tanggal masa berlaku surat rujukan
                            try {
                                tglSekarang = new SimpleDateFormat("yyyy-MM-dd").parse(now);
                                tglExpRujukan = new SimpleDateFormat("yyyy-MM-dd").parse(tglHabisRujukan);
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            }

                            if (tglSekarang.before(tglExpRujukan)) {
                                Sequel.menyimpanIgnore("iter_obat_bpjs", "'" + kode_iter.getText() + "','" + noSep + "','" + noKartu + "',"
                                        + "'" + noRM + "','" + nomorrawat + "','1','" + tglHabisRujukan + "','Proses pelayanan','" + iterKe + "','0000-00-00',"
                                        + "'" + Sequel.cariIsi("select now()") + "'", "Iter Obat BPJS");

                                tampilResepIter();
                            } else {
                                JOptionPane.showMessageDialog(null, "Maaf, surat rujukan pasien telah berakhir pada tgl. " + Valid.SetTglINDONESIA(tglHabisRujukan) + " ...!!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Status kepesertaan pasien ini tidak aktif..!!");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsIter != null) {
                    rsIter.close();
                }
                if (psIter != null) {
                    psIter.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilResepIter() {
        Valid.tabelKosong(tabModeIter);
        try {
            ps1 = koneksi.prepareStatement("SELECT i.*, p.nm_pasien, pl.nm_poli, rp.kd_poli, "
                    + "if(i.tgl_ambil_obat='0000-00-00','-',date_format(i.tgl_ambil_obat,'%d-%m-%Y')) tglAmbil FROM iter_obat_bpjs i "
                    + "inner join reg_periksa rp on rp.no_rawat = i.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli WHERE "
                    + "i.no_rkm_medis='" + noRM + "' ORDER BY i.waktu_simpan desc limit 10");
            try {                
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabModeIter.addRow(new String[]{
                        rs1.getString("kode_iter"),
                        rs1.getString("no_sep"),
                        rs1.getString("no_kartu"),
                        rs1.getString("no_rkm_medis"),
                        rs1.getString("nm_pasien"),
                        rs1.getString("no_rawat"),
                        rs1.getString("nm_poli"),
                        rs1.getString("kunjungan"),
                        rs1.getString("tglAmbil"),                        
                        rs1.getString("stts_pengambilan"),
                        rs1.getString("poli_ke"),
                        rs1.getString("tgl_exp_rujukan"),
                        rs1.getString("kd_poli"),
                        rs1.getString("tgl_ambil_obat"),
                        rs1.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataIter() {
        noSEP = "";
        noKARTU = "";
        noRAWATiter = "";
        pengambilan = "";
        sttsAmbil = "";
        poliKe = "";
        tglhabisRujukan = "";
        kdpoliIter = "";
        tglAmbilObat = "";
        kode_iter.setText("");
        
        if (tbResepIter.getSelectedRow() != -1) {
            kode_iter.setText(tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 0).toString());
            noSEP = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 1).toString();
            noKARTU = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 2).toString();
            noRAWATiter = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 5).toString();
            pengambilan = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 7).toString();            
            sttsAmbil = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 9).toString();
            poliKe = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 10).toString();
            tglhabisRujukan = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 11).toString();
            kdpoliIter = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 12).toString();
            tglAmbilObat = tbResepIter.getValueAt(tbResepIter.getSelectedRow(), 13).toString();
        }
    }
} 
