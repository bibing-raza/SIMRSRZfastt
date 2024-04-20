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
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import simrskhanza.DlgCariBangsal;

/**
 *
 * @author dosen
 */
public final class DlgCariObat2 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeResepObat, tabModeResepObat1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private riwayatobat Trackobat = new riwayatobat();
    private PreparedStatement psobat, pscarikapasitas, psobatasuransi, psstok, psrekening, ps, ps1;
    private ResultSet rsobat, carikapasitas, rsstok, rsrekening, rs, rs1;
    private Jurnal jur = new Jurnal();
    private double x = 0, y = 0, embalase, kenaikan, tuslah, stokbarang, ttlhpp, ttljual, stokbarang2;
    private int jml = 0, i = 0, urut = 0, cekCatRanap = 0, cekCatRalan = 0, conteng = 0;
    private boolean[] pilih;
    private double[] jumlah, harga, eb, ts, stok, beli;
    private String[] kodebarang, namabarang, kodesatuan, letakbarang, namajenis, industri, aturan, kategori, golongan;
    private DlgBarang barang = new DlgBarang(null, false);
    private String Suspen_Piutang_Obat_Ranap = "", Obat_Ranap = "", HPP_Obat_Rawat_Inap = "", Persediaan_Obat_Rawat_Inap = "",
            bangsal = "", status = "", stat = "", idObat = "";
    private WarnaTable2 warna = new WarnaTable2();
    private DlgCariBangsal caribangsal = new DlgCariBangsal(null, false);
    public DlgAturanPakai aturanpakai = new DlgAturanPakai(null, false);

    /**
     * Creates new form DlgPenyakit Creates new form DlgPenyakit
     *
     * @param parent
     * @param modal
     */
    public DlgCariObat2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10, 2);
        setSize(656, 250);

        Object[] row = {"K", "Jumlah", "Kode", "Nama Barang", "Satuan", "Letak Barang",
            "Harga(Rp)", "Jenis Obat", "Embalase", "Tuslah", "Stok", "I.F.", "H.Beli",
            "Aturan Pakai", "Kategori", "Golongan"
        };
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0) || (colIndex == 1) || (colIndex == 8) || (colIndex == 9) || (colIndex == 13)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Object.class, java.lang.Double.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 16; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(45);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(55);
            } else if (i == 9) {
                column.setPreferredWidth(55);
            } else if (i == 10) {
                column.setPreferredWidth(35);
            } else if (i == 11) {
                column.setPreferredWidth(110);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            }
        }
        warna.kolom = 1;
        tbObat.setDefaultRenderer(Object.class, warna);
        
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

        tbResepRanap.setModel(tabModeResepObat);
        tbResepRanap.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbResepRanap.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(200);
            }
        }
        tbResepRanap.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResepObat1 = new DefaultTableModel(null, new Object[]{
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

        tbResepRalan.setModel(tabModeResepObat1);
        tbResepRalan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbResepRalan.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(200);
            }
        }
        tbResepRalan.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        jam();

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
                    tbObat.setValueAt(aturanpakai.getTable().getValueAt(aturanpakai.getTable().getSelectedRow(), 0).toString(), tbObat.getSelectedRow(), 13);
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

        try {
            psrekening = koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening = psrekening.executeQuery();
                while (rsrekening.next()) {
                    Suspen_Piutang_Obat_Ranap = rsrekening.getString("Suspen_Piutang_Obat_Ranap");
                    Obat_Ranap = rsrekening.getString("Obat_Ranap");
                    HPP_Obat_Rawat_Inap = rsrekening.getString("HPP_Obat_Rawat_Inap");
                    Persediaan_Obat_Rawat_Inap = rsrekening.getString("Persediaan_Obat_Rawat_Inap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : " + e);
            } finally {
                if (rsrekening != null) {
                    rsrekening.close();
                }
                if (psrekening != null) {
                    psrekening.close();
                }
            }
        } catch (Exception e) {
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

        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppStok = new javax.swing.JMenuItem();
        KdPj = new widget.TextBox();
        kelas = new widget.TextBox();
        TStok = new widget.TextBox();
        TNoRm = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Form = new widget.PanelBiasa();
        FormInput = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        label12 = new widget.Label();
        Jeniskelas = new widget.ComboBox();
        ChkNoResep = new widget.CekBox();
        jLabel6 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        panelisi5 = new widget.panelisi();
        panelisi6 = new widget.panelisi();
        Scroll3 = new widget.ScrollPane();
        tbResepRanap = new widget.Table();
        panelisi4 = new widget.panelisi();
        chkResepObat = new widget.CekBox();
        BtnVerifRanap = new widget.Button();
        label13 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnCekResepRanap = new widget.Button();
        label14 = new widget.Label();
        cmbKertas = new widget.ComboBox();
        BtnCetak = new widget.Button();
        jLabel7 = new widget.Label();
        LCountRanap = new widget.Label();
        ChkTgl = new widget.CekBox();
        DTPTgl1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPTgl2 = new widget.Tanggal();
        panelisi7 = new widget.panelisi();
        Scroll4 = new widget.ScrollPane();
        tbResepRalan = new widget.Table();
        panelisi8 = new widget.panelisi();
        chkResepObat1 = new widget.CekBox();
        BtnVerifRalan = new widget.Button();
        label15 = new widget.Label();
        cmbStatus1 = new widget.ComboBox();
        BtnCekResepRalan = new widget.Button();
        label16 = new widget.Label();
        cmbKertas1 = new widget.ComboBox();
        BtnCetak1 = new widget.Button();
        jLabel8 = new widget.Label();
        LCountRalan = new widget.Label();
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

        KdPj.setHighlighter(null);
        KdPj.setName("KdPj"); // NOI18N
        KdPj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPjKeyPressed(evt);
            }
        });

        kelas.setHighlighter(null);
        kelas.setName("kelas"); // NOI18N
        kelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kelasKeyPressed(evt);
            }
        });

        TStok.setHighlighter(null);
        TStok.setName("TStok"); // NOI18N
        TStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TStokKeyPressed(evt);
            }
        });

        TNoRm.setHighlighter(null);
        TNoRm.setName("TNoRm"); // NOI18N
        TNoRm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRmKeyPressed(evt);
            }
        });

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Form.setBackground(new java.awt.Color(215, 225, 215));
        Form.setName("Form"); // NOI18N
        Form.setPreferredSize(new java.awt.Dimension(100, 400));
        Form.setLayout(new java.awt.BorderLayout());

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 74));
        FormInput.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tanggal :");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(jLabel5);
        jLabel5.setBounds(4, 10, 68, 23);

        DTPTgl.setEditable(false);
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-11-2023" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(75, 10, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(178, 10, 50, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(231, 10, 50, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(284, 10, 50, 23);

        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
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
        FormInput.add(ChkJln);
        ChkJln.setBounds(337, 10, 22, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Tarif :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(50, 23));
        FormInput.add(label12);
        label12.setBounds(362, 10, 50, 23);

        Jeniskelas.setForeground(new java.awt.Color(0, 0, 0));
        Jeniskelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kelas 1", "Kelas 2", "Kelas 3", "Utama/BPJS", "VIP", "VVIP", "Beli Luar", "Karyawan" }));
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
        FormInput.add(Jeniskelas);
        Jeniskelas.setBounds(415, 10, 100, 23);

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
        FormInput.add(ChkNoResep);
        ChkNoResep.setBounds(518, 10, 85, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Pasien :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(jLabel6);
        jLabel6.setBounds(4, 38, 68, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(75, 38, 130, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        FormInput.add(TPasien);
        TPasien.setBounds(210, 38, 420, 23);

        Form.add(FormInput, java.awt.BorderLayout.PAGE_START);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi5.setLayout(new java.awt.GridLayout(1, 0));

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi6.setLayout(new java.awt.BorderLayout());

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Catatan Resep (RAWAT INAP) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N

        tbResepRanap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbResepRanap.setName("tbResepRanap"); // NOI18N
        Scroll3.setViewportView(tbResepRanap);

        panelisi6.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 72));
        panelisi4.setLayout(null);

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
        chkResepObat.setBounds(0, 10, 120, 23);

        BtnVerifRanap.setForeground(new java.awt.Color(0, 0, 0));
        BtnVerifRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnVerifRanap.setMnemonic('V');
        BtnVerifRanap.setText("Verifikasi Resep");
        BtnVerifRanap.setToolTipText("Alt+V");
        BtnVerifRanap.setIconTextGap(7);
        BtnVerifRanap.setName("BtnVerifRanap"); // NOI18N
        BtnVerifRanap.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnVerifRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifRanapActionPerformed(evt);
            }
        });
        panelisi4.add(BtnVerifRanap);
        BtnVerifRanap.setBounds(125, 10, 140, 23);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setText("Status Resep :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label13);
        label13.setBounds(0, 38, 90, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM", "SUDAH", "DILUAR", "Semua" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(cmbStatus);
        cmbStatus.setBounds(95, 38, 70, 23);

        BtnCekResepRanap.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekResepRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCekResepRanap.setMnemonic('R');
        BtnCekResepRanap.setText("Tampilkan Resep");
        BtnCekResepRanap.setToolTipText("Alt+R");
        BtnCekResepRanap.setName("BtnCekResepRanap"); // NOI18N
        BtnCekResepRanap.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnCekResepRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekResepRanapActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCekResepRanap);
        BtnCekResepRanap.setBounds(470, 38, 140, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Pilihan Kertas Print :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(108, 23));
        panelisi4.add(label14);
        label14.setBounds(365, 10, 108, 23);

        cmbKertas.setForeground(new java.awt.Color(0, 0, 0));
        cmbKertas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BILLING", "THERMAL" }));
        cmbKertas.setName("cmbKertas"); // NOI18N
        cmbKertas.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(cmbKertas);
        cmbKertas.setBounds(480, 10, 80, 23);

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
        BtnCetak.setBounds(565, 10, 130, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(jLabel7);
        jLabel7.setBounds(275, 10, 50, 23);

        LCountRanap.setForeground(new java.awt.Color(0, 0, 0));
        LCountRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountRanap.setText("0");
        LCountRanap.setName("LCountRanap"); // NOI18N
        LCountRanap.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi4.add(LCountRanap);
        LCountRanap.setBounds(330, 10, 30, 23);

        ChkTgl.setBorder(null);
        ChkTgl.setForeground(new java.awt.Color(0, 0, 0));
        ChkTgl.setText("Tgl. Resep : ");
        ChkTgl.setBorderPainted(true);
        ChkTgl.setBorderPaintedFlat(true);
        ChkTgl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTgl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTgl.setName("ChkTgl"); // NOI18N
        ChkTgl.setPreferredSize(new java.awt.Dimension(85, 23));
        ChkTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTglActionPerformed(evt);
            }
        });
        panelisi4.add(ChkTgl);
        ChkTgl.setBounds(170, 38, 85, 23);

        DTPTgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-11-2023" }));
        DTPTgl1.setDisplayFormat("dd-MM-yyyy");
        DTPTgl1.setName("DTPTgl1"); // NOI18N
        DTPTgl1.setOpaque(false);
        DTPTgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPTgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTgl1KeyPressed(evt);
            }
        });
        panelisi4.add(DTPTgl1);
        DTPTgl1.setBounds(258, 38, 90, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi4.add(jLabel25);
        jLabel25.setBounds(350, 38, 20, 23);

        DTPTgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-11-2023" }));
        DTPTgl2.setDisplayFormat("dd-MM-yyyy");
        DTPTgl2.setName("DTPTgl2"); // NOI18N
        DTPTgl2.setOpaque(false);
        DTPTgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPTgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTgl2KeyPressed(evt);
            }
        });
        panelisi4.add(DTPTgl2);
        DTPTgl2.setBounds(375, 38, 90, 23);

        panelisi6.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        panelisi5.add(panelisi6);

        panelisi7.setName("panelisi7"); // NOI18N
        panelisi7.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi7.setLayout(new java.awt.BorderLayout());

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Catatan Resep (RAWAT JALAN) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N

        tbResepRalan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbResepRalan.setName("tbResepRalan"); // NOI18N
        Scroll4.setViewportView(tbResepRalan);

        panelisi7.add(Scroll4, java.awt.BorderLayout.CENTER);

        panelisi8.setName("panelisi8"); // NOI18N
        panelisi8.setPreferredSize(new java.awt.Dimension(100, 72));
        panelisi8.setLayout(null);

        chkResepObat1.setForeground(new java.awt.Color(0, 0, 0));
        chkResepObat1.setText("Conteng Semua");
        chkResepObat1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkResepObat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkResepObat1.setName("chkResepObat1"); // NOI18N
        chkResepObat1.setPreferredSize(new java.awt.Dimension(120, 23));
        chkResepObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkResepObat1ActionPerformed(evt);
            }
        });
        panelisi8.add(chkResepObat1);
        chkResepObat1.setBounds(0, 10, 120, 23);

        BtnVerifRalan.setForeground(new java.awt.Color(0, 0, 0));
        BtnVerifRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnVerifRalan.setMnemonic('V');
        BtnVerifRalan.setText("Verifikasi Resep");
        BtnVerifRalan.setToolTipText("Alt+V");
        BtnVerifRalan.setIconTextGap(7);
        BtnVerifRalan.setName("BtnVerifRalan"); // NOI18N
        BtnVerifRalan.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnVerifRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifRalanActionPerformed(evt);
            }
        });
        panelisi8.add(BtnVerifRalan);
        BtnVerifRalan.setBounds(180, 10, 140, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Status Resep :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi8.add(label15);
        label15.setBounds(0, 38, 90, 23);

        cmbStatus1.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM", "SUDAH", "DILUAR", "Semua" }));
        cmbStatus1.setName("cmbStatus1"); // NOI18N
        cmbStatus1.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi8.add(cmbStatus1);
        cmbStatus1.setBounds(95, 38, 70, 23);

        BtnCekResepRalan.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekResepRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCekResepRalan.setMnemonic('R');
        BtnCekResepRalan.setText("Tampilkan Resep");
        BtnCekResepRalan.setToolTipText("Alt+R");
        BtnCekResepRalan.setName("BtnCekResepRalan"); // NOI18N
        BtnCekResepRalan.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnCekResepRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekResepRalanActionPerformed(evt);
            }
        });
        panelisi8.add(BtnCekResepRalan);
        BtnCekResepRalan.setBounds(180, 38, 140, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Pilihan Kertas Print :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(108, 23));
        panelisi8.add(label16);
        label16.setBounds(330, 38, 108, 23);

        cmbKertas1.setForeground(new java.awt.Color(0, 0, 0));
        cmbKertas1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BILLING", "THERMAL" }));
        cmbKertas1.setName("cmbKertas1"); // NOI18N
        cmbKertas1.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi8.add(cmbKertas1);
        cmbKertas1.setBounds(443, 38, 80, 23);

        BtnCetak1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetak1.setMnemonic('C');
        BtnCetak1.setText("Cetak Resep");
        BtnCetak1.setToolTipText("Alt+C");
        BtnCetak1.setIconTextGap(7);
        BtnCetak1.setName("BtnCetak1"); // NOI18N
        BtnCetak1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCetak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetak1ActionPerformed(evt);
            }
        });
        panelisi8.add(BtnCetak1);
        BtnCetak1.setBounds(530, 38, 130, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Record :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi8.add(jLabel8);
        jLabel8.setBounds(330, 10, 65, 23);

        LCountRalan.setForeground(new java.awt.Color(0, 0, 0));
        LCountRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountRalan.setText("0");
        LCountRalan.setName("LCountRalan"); // NOI18N
        LCountRalan.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi8.add(LCountRalan);
        LCountRalan.setBounds(400, 10, 50, 23);

        panelisi7.add(panelisi8, java.awt.BorderLayout.PAGE_END);

        panelisi5.add(panelisi7);

        Form.add(panelisi5, java.awt.BorderLayout.CENTER);

        internalFrame1.add(Form, java.awt.BorderLayout.PAGE_START);

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
        TCari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(285, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        BtnAll.setMnemonic('3');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+3");
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
        BtnTambah.setMnemonic('4');
        BtnTambah.setText("Tambah Data");
        BtnTambah.setToolTipText("Alt+4");
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
        BtnSeek5.setMnemonic('5');
        BtnSeek5.setText("Konversi Satuan");
        BtnSeek5.setToolTipText("Alt+5");
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
        BtnKeluar.setMnemonic('6');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+6");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

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
        tampil();
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
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            tbObat.requestFocus();
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (akses.getkdbangsal().equals("")) {
            akses.setkdbangsal(bangsal);
        }

        if (akses.getkdbangsal().equals("")) {
            Valid.textKosong(TCari, "Lokasi");
        } else if (tbObat.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    getData();
                    i = tbObat.getSelectedColumn();
                    if (i == 2) {
                        try {
                            stokbarang = 0;
                            psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                            try {
                                psstok.setString(1, akses.getkdbangsal());
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
                                y = Math.round(Double.parseDouble(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString()));
                                tbObat.setValueAt(y,tbObat.getSelectedRow(), 1);
                            } catch (Exception e) {
                                y = 0;
                            }
                            if (stokbarang < y) {
                                JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
                                tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                            }
                            tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
                            tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 10);
                        }
                        TCari.setText("");
                        TCari.requestFocus();
                    } else if (i == 8) {
                        try {
                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString().equals("0,0")) {
                                tbObat.setValueAt(embalase, tbObat.getSelectedRow(), 8);
                            }
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 8);
                        }
                    } else if (i == 9) {
                        try {
                            if (tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0.0") || tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("0,0")) {
                                tbObat.setValueAt(tuslah, tbObat.getSelectedRow(), 9);
                            }
                        } catch (Exception e) {
                            tbObat.setValueAt(0, tbObat.getSelectedRow(), 9);
                        }

                        TCari.setText("");
                        TCari.requestFocus();
                    } else if ((i == 10) || (i == 3)) {
                        TCari.setText("");
                        TCari.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                if (tbObat.getSelectedRow() != -1) {
                    tbObat.setValueAt("", tbObat.getSelectedRow(), 1);
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.requestFocus();
            } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                i = tbObat.getSelectedColumn();
                if (i == 2) {
                    try {
                        stokbarang = 0;
                        psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                        try {
                            psstok.setString(1, akses.getkdbangsal());
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
                            System.out.println(e);
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
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        cmbStatus.setSelectedIndex(0);
        cmbKertas.setSelectedIndex(0);
        cmbStatus1.setSelectedIndex(0);
        cmbKertas1.setSelectedIndex(0);
        TNoRw.setText("");
        TPasien.setText("");
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        //barang.setModal(true);
        barang.emptTeks();
        barang.isCek();
        barang.setSize(internalFrame1.getWidth() + 40, internalFrame1.getHeight() + 40);
        barang.setLocationRelativeTo(internalFrame1);
        barang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahActionPerformed

private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    if (akses.getkdbangsal().equals("")) {
        akses.setkdbangsal(bangsal);
    }
    
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TCari, "Data");
    } else if (akses.getkdbangsal().equals("")) {
        Valid.textKosong(TCari, "Lokasi");
    } else {
        isSetBangsal();
        cekCatRanap = 0;
        cekCatRalan = 0;
        cekCatRanap = Sequel.cariInteger("Select count(-1) from catatan_resep_ranap where no_rawat = '" + TNoRw.getText() + "' and status = 'BELUM'");
        cekCatRalan = Sequel.cariInteger("Select count(-1) from catatan_resep where no_rawat = '" + TNoRw.getText() + "' and status = 'BELUM'");

        //APOTEK SENTRAL atau APOTEK IBS
        if (akses.getkdbangsal().equals("APT02") || akses.getkdbangsal().equals("APT07")) {
            if (cekCatRanap > 0) {
                JOptionPane.showMessageDialog(null, "Ada catatan resep dari dokter, Silakan verifikasi resepnya dulu..!!");
            } else {
                Simpan();
                cmbStatus.setSelectedIndex(0);
                cmbKertas.setSelectedIndex(0);
                cmbStatus1.setSelectedIndex(0);
                cmbKertas1.setSelectedIndex(0);
                TNoRw.setText("");
                TPasien.setText("");
            }
        //APOTEK IGD    
        } else if (akses.getkdbangsal().equals("APT01")) {
            if (cekCatRanap > 0 || cekCatRalan > 0) {
                JOptionPane.showMessageDialog(null, "Ada catatan resep dari dokter, Silakan verifikasi resepnya dulu..!!");
            } else {
                Simpan();
                cmbStatus.setSelectedIndex(0);
                cmbKertas.setSelectedIndex(0);
                cmbStatus1.setSelectedIndex(0);
                cmbKertas1.setSelectedIndex(0);
                TNoRw.setText("");
                TPasien.setText("");
            }
        } else {
            Simpan();
            cmbStatus.setSelectedIndex(0);
            cmbKertas.setSelectedIndex(0);
            cmbStatus1.setSelectedIndex(0);
            cmbKertas1.setSelectedIndex(0);
            TNoRw.setText("");
            TPasien.setText("");
        }
    }
}//GEN-LAST:event_BtnSimpanActionPerformed

private void BtnSeek5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek5ActionPerformed
    DlgCariKonversi carikonversi = new DlgCariKonversi(null, false);
    carikonversi.setLocationRelativeTo(internalFrame1);
    carikonversi.setVisible(true);
}//GEN-LAST:event_BtnSeek5ActionPerformed

private void BtnSeek5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek5KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnSeek5KeyPressed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
    for (i = 0; i < tbObat.getRowCount(); i++) {
        tbObat.setValueAt("", i, 1);
        tbObat.setValueAt(0, i, 10);
        tbObat.setValueAt(0, i, 9);
        tbObat.setValueAt(0, i, 8);
    }
}//GEN-LAST:event_ppBersihkanActionPerformed

private void JeniskelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JeniskelasItemStateChanged
    tampil();
}//GEN-LAST:event_JeniskelasItemStateChanged

private void JeniskelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JeniskelasKeyPressed
    Valid.pindah(evt, cmbDtk, TCari);
}//GEN-LAST:event_JeniskelasKeyPressed

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

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Sequel.insertClosingStok();
        emptTeks();
        embalase = Sequel.cariIsiAngka("select embalase_per_obat from set_embalase");
        tuslah = Sequel.cariIsiAngka("select tuslah_per_obat from set_embalase");
        isSetBangsal();
    }//GEN-LAST:event_formWindowActivated

    private void ChkNoResepItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkNoResepItemStateChanged
        if (ChkNoResep.isSelected() == true) {
            DlgResepObat resep = new DlgResepObat(null, false);
            resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            resep.setLocationRelativeTo(internalFrame1);
            resep.emptTeks();
            resep.isCek();
            resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString());
            resep.tampil();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_ChkNoResepItemStateChanged

    private void KdPjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPjKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPjKeyPressed

    private void kelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kelasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kelasKeyPressed

    private void ppStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppStokActionPerformed
        if (akses.getkdbangsal().equals("")) {
            akses.setkdbangsal(bangsal);
        }
        if (akses.getkdbangsal().equals("")) {
            Valid.textKosong(TCari, "Lokasi");
        } else {
            isSetBangsal();
            for (i = 0; i < tbObat.getRowCount(); i++) {
                try {
                    stokbarang = 0;
                    psstok = koneksi.prepareStatement("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?");
                    try {
                        psstok.setString(1, akses.getkdbangsal());
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
                    tbObat.setValueAt(stokbarang, i, 10);
                } catch (Exception e) {
                    tbObat.setValueAt(0, i, 10);
                }
            }
        }
    }//GEN-LAST:event_ppStokActionPerformed

    private void TStokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TStokKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TStokKeyPressed

    private void TNoRmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRmKeyPressed

    private void chkResepObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkResepObatActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data yang bisa diconteng...!!!!");
            chkResepObat.setSelected(false);
        } else {
            for (i = 0; i < tbResepRanap.getRowCount(); i++) {
                if (chkResepObat.isSelected() == true) {
                    tbResepRanap.setValueAt(Boolean.TRUE, i, 0);
                } else if (chkResepObat.isSelected() == false) {
                    tbResepRanap.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_chkResepObatActionPerformed

    private void BtnVerifRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifRanapActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada resep rawat inap yang diverifikasi...!!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where no_rawat='" + TNoRw.getText() + "' and status='BELUM'") > 0) {
                x = 0;
                for (i = 0; i < tbResepRanap.getRowCount(); i++) {
                    if (tbResepRanap.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Conteng dulu untuk verifikasi resepnya..!!!!");
                    tbResepRanap.requestFocus();
                } else {
                    try {
                        for (i = 0; i < tbResepRanap.getRowCount(); i++) {
                            if (tbResepRanap.getValueAt(i, 0).toString().equals("true")) {
                                stat = "SUDAH";
                            } else {
                                stat = "DILUAR";
                            }
                            Sequel.queryu("update catatan_resep_ranap set status = '" + stat + "' where no_rawat='" + tbResepRanap.getValueAt(i, 1).toString() + "' "
                                    + "and noId='" + tbResepRanap.getValueAt(i, 6).toString() + "'");
                        }
                        isPsien();
                        tampil_resepRanap();
                        chkResepObat.setSelected(false);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Resep rawat inap sudah terverifikasi semua..!!!!");
            }
        }
    }//GEN-LAST:event_BtnVerifRanapActionPerformed

    private void BtnCekResepRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekResepRanapActionPerformed
        isPsien();
        tampil_resepRanap();
    }//GEN-LAST:event_BtnCekResepRanapActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cmbStatus.setSelectedIndex(0);
        cmbKertas.setSelectedIndex(0);
        cmbStatus1.setSelectedIndex(0);
        cmbKertas1.setSelectedIndex(0);
        isSetBangsal();
        if (akses.getkdbangsal().equals("APT02") || akses.getkdbangsal().equals("APT07")) {
            Valid.tabelKosong(tabModeResepObat1);
        } else if (akses.getkdbangsal().equals("APT01")) {
            tampil_resepRalan();
        }
        
        tampil_resepRanap();
        isPsien();        
        Sequel.insertClosingStok();  
        
        ChkTgl.setSelected(false);
        DTPTgl1.setEnabled(false);
        DTPTgl2.setEnabled(false);
        Valid.SetTgl(DTPTgl1, Sequel.cariIsi("select tgl_perawatan from catatan_resep_ranap where no_rawat='" + TNoRw.getText() + "' order by noId limit 1"));
        DTPTgl2.setDate(new Date());
    }//GEN-LAST:event_formWindowOpened

    private void BtnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data resep rawat inap yang bisa anda print...!!!!");
        } else {
            conteng = 0;
            for (i = 0; i < tbResepRanap.getRowCount(); i++) {
                if (tbResepRanap.getValueAt(i, 0).toString().equals("true")) {
                    conteng++;
                }
            }
           
            if (conteng == 0) {
                JOptionPane.showMessageDialog(null, "Utk. mencetak resep rawat inap silahkan conteng item yg. dipilih...!!!!");
                tbResepRanap.requestFocus();
                tampil_resepRanap();
            } else if (conteng > 0) {
                idObat = "";
                for (i = 0; i < tbResepRanap.getRowCount(); i++) {
                    if (tbResepRanap.getValueAt(i, 0).toString().equals("true")) {
                        if (idObat.equals("")) {
                            idObat = "'" + tbResepRanap.getValueAt(i, 6).toString() + "'";
                        } else {
                            idObat = idObat + ",'" + tbResepRanap.getValueAt(i, 6).toString() + "'";
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
                    param.put("norawat", TNoRw.getText());
                    param.put("pasien", TPasien.getText());
                    param.put("carabyr", Sequel.cariIsi("select pj.png_jawab from reg_periksa rp inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + TNoRw.getText() + "'"));
                    param.put("ruangan", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                            + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));

                    Valid.MyReport("rptCatatanResep.jasper", "report", "::[ Cetak e-Resep ]::",
                            "SELECT *, concat(DATE_FORMAT(tgl_perawatan,'%d-%m-%Y'),' / ',TIME_FORMAT(jam_perawatan,'%H:%i')) tgl "
                            + "from catatan_resep_ranap where noId in (" + idObat + ") order by status, noId desc", param);
                    
                } else if (cmbKertas.getSelectedIndex() == 1) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("norawat", TNoRw.getText());
                    param.put("pasien", Sequel.cariIsi("select concat(p.no_rkm_medis,' - ',p.nm_pasien) from reg_periksa rp "
                            + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + TNoRw.getText() + "'"));
                    param.put("carabyr", Sequel.cariIsi("select pj.png_jawab from reg_periksa rp "
                            + "inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + TNoRw.getText() + "'"));
                    param.put("ruangan", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                            + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                    param.put("tglcetak", Sequel.cariIsi("select concat(date_format(now(),'%d-%m-%Y'),', Jam : ',time_format(now(),'%H:%i'))"));

                    Valid.MyReport("rptStrukResepRanap.jasper", "report", "::[ Struk Resep Dokter Rawat Inap Kertas Thermal ]::",
                            " SELECT *, concat(DATE_FORMAT(tgl_perawatan,'%d-%m-%Y'),' / ',TIME_FORMAT(jam_perawatan,'%H:%i')) tgl "
                            + "from catatan_resep_ranap where noId in (" + idObat + ") order by status, noId desc", param);
                }
            }
        }
    }//GEN-LAST:event_BtnCetakActionPerformed

    private void chkResepObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkResepObat1ActionPerformed
        if (tabModeResepObat1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data yang bisa diconteng...!!!!");
            chkResepObat1.setSelected(false);
        } else {
            for (i = 0; i < tbResepRalan.getRowCount(); i++) {
                if (chkResepObat1.isSelected() == true) {
                    tbResepRalan.setValueAt(Boolean.TRUE, i, 0);
                } else if (chkResepObat1.isSelected() == false) {
                    tbResepRalan.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_chkResepObat1ActionPerformed

    private void BtnVerifRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifRalanActionPerformed
        if (tabModeResepObat1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada resep rawat jalan yang diverifikasi...!!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from catatan_resep where no_rawat='" + TNoRw.getText() + "' and status='BELUM'") > 0) {
                x = 0;
                for (i = 0; i < tbResepRalan.getRowCount(); i++) {
                    if (tbResepRalan.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Conteng dulu untuk verifikasi resepnya..!!!!");
                    tbResepRalan.requestFocus();
                } else {
                    try {
                        for (i = 0; i < tbResepRalan.getRowCount(); i++) {
                            if (tbResepRalan.getValueAt(i, 0).toString().equals("true")) {
                                stat = "SUDAH";
                            } else {
                                stat = "DILUAR";
                            }
                            Sequel.queryu("update catatan_resep set status = '" + stat + "' where no_rawat='" + tbResepRalan.getValueAt(i, 1).toString() + "' "
                                    + "and noId='" + tbResepRalan.getValueAt(i, 6).toString() + "'");
                        }
                        isPsien();
                        tampil_resepRalan();
                        chkResepObat1.setSelected(false);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Resep rawat jalan sudah terverifikasi semua..!!!!");
            }
        }
    }//GEN-LAST:event_BtnVerifRalanActionPerformed

    private void BtnCekResepRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekResepRalanActionPerformed
        isPsien();
        tampil_resepRalan();
    }//GEN-LAST:event_BtnCekResepRalanActionPerformed

    private void BtnCetak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetak1ActionPerformed
        if (tabModeResepObat1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data resep rawat jalan yang bisa anda print...!!!!");
        } else {
            conteng = 0;
            for (i = 0; i < tbResepRalan.getRowCount(); i++) {
                if (tbResepRalan.getValueAt(i, 0).toString().equals("true")) {
                    conteng++;
                }
            }
           
            if (conteng == 0) {
                JOptionPane.showMessageDialog(null, "Utk. mencetak resep rawat jalan silahkan conteng item yg. dipilih...!!!!");
                tbResepRalan.requestFocus();
                tampil_resepRalan();
            } else if (conteng > 0) {
                idObat = "";
                for (i = 0; i < tbResepRalan.getRowCount(); i++) {
                    if (tbResepRalan.getValueAt(i, 0).toString().equals("true")) {
                        if (idObat.equals("")) {
                            idObat = "'" + tbResepRalan.getValueAt(i, 6).toString() + "'";
                        } else {
                            idObat = idObat + ",'" + tbResepRalan.getValueAt(i, 6).toString() + "'";
                        }
                    }
                }

                if (cmbKertas1.getSelectedIndex() == 0) {
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
                    param.put("nosep", Sequel.cariIsi("select ifnull(no_sep,'-') from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'"));
                    
                    Valid.MyReport("rptCatatanResepRalan.jasper", "report", "::[ Cetak e-Resep ]::",
                            "SELECT pl.nm_poli, date_format(cr.tgl_perawatan,'%d-%m-%Y') tgl, d.nm_dokter, cr.no_rawat, p.no_rkm_medis, "
                            + "p.nm_pasien, ifnull(p.no_tlp,'-') no_hp, cr.nama_obat, concat(date_format(p.tgl_lahir,'%d/%m/%Y'),' (Usia : ',rp.umurdaftar,' ',rp.sttsumur,'.)') tgllahir "
                            + "FROM catatan_resep cr INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN dokter d ON d.kd_dokter=cr.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                            + "WHERE cr.noId in (" + idObat + ") ORDER BY cr.tgl_perawatan DESC, cr.jam_perawatan DESC, cr.noId DESC", param);
                    
                } else if (cmbKertas1.getSelectedIndex() == 1) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("nosep", Sequel.cariIsi("select ifnull(no_sep,'-') from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'"));
                    param.put("tglcetak", Sequel.cariIsi("select concat(date_format(date(now()),'%d/%m/%Y'),', Jam : ',time(now()),' Wita')"));
                    
                    Valid.MyReport("rptStrukResepRalan.jasper", "report", "::[ Struk Resep Dokter Poliklinik/Unit Rawat Jalan Kertas Thermal ]::",
                            " SELECT pl.nm_poli, date_format(cr.tgl_perawatan,'%d-%m-%Y') tgl, d.nm_dokter, cr.no_rawat, p.no_rkm_medis, "
                            + "p.nm_pasien, ifnull(p.no_tlp,'-') no_hp, cr.nama_obat, concat(date_format(p.tgl_lahir,'%d/%m/%Y'),' (Usia : ',rp.umurdaftar,' ',rp.sttsumur,'.)') tgllahir "
                            + "FROM catatan_resep cr INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN dokter d ON d.kd_dokter=cr.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                            + "WHERE cr.noId in (" + idObat + ") ORDER BY cr.tgl_perawatan DESC, cr.jam_perawatan DESC, cr.noId DESC", param);
                }
            }
        }
    }//GEN-LAST:event_BtnCetak1ActionPerformed

    private void DTPTgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTgl1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTgl1KeyPressed

    private void DTPTgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTgl2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTgl2KeyPressed

    private void ChkTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTglActionPerformed
        if (ChkTgl.isSelected() == true) {
            DTPTgl1.setEnabled(true);
            DTPTgl2.setEnabled(true);
            Valid.SetTgl(DTPTgl1, Sequel.cariIsi("select tgl_perawatan from catatan_resep_ranap where no_rawat='" + TNoRw.getText() + "' order by noId limit 1"));
            DTPTgl2.setDate(new Date());
        } else {
            DTPTgl1.setEnabled(false);
            DTPTgl2.setEnabled(false);
        }
    }//GEN-LAST:event_ChkTglActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariObat2 dialog = new DlgCariObat2(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCekResepRalan;
    private widget.Button BtnCekResepRanap;
    private widget.Button BtnCetak;
    private widget.Button BtnCetak1;
    private widget.Button BtnKeluar;
    private widget.Button BtnSeek5;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah;
    private widget.Button BtnVerifRalan;
    private widget.Button BtnVerifRanap;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkNoResep;
    private widget.CekBox ChkTgl;
    private widget.Tanggal DTPTgl;
    private widget.Tanggal DTPTgl1;
    private widget.Tanggal DTPTgl2;
    private widget.PanelBiasa Form;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox Jeniskelas;
    private widget.TextBox KdPj;
    private widget.Label LCountRalan;
    private widget.Label LCountRanap;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TStok;
    private widget.CekBox chkResepObat;
    private widget.CekBox chkResepObat1;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKertas;
    private widget.ComboBox cmbKertas1;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbStatus1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel25;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.TextBox kelas;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.panelisi panelisi7;
    private widget.panelisi panelisi8;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppStok;
    private widget.Table tbObat;
    private widget.Table tbResepRalan;
    private widget.Table tbResepRanap;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        isSetBangsal();
        jml = 0;
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (!tbObat.getValueAt(i, 0).toString().equals("")) {
                jml++;
            }
        }

        pilih = null;
        pilih = new boolean[jml];
        jumlah = null;
        jumlah = new double[jml];
        eb = null;
        eb = new double[jml];
        ts = null;
        ts = new double[jml];
        stok = null;
        stok = new double[jml];
        harga = null;
        harga = new double[jml];
        kodebarang = null;
        kodebarang = new String[jml];
        namabarang = null;
        namabarang = new String[jml];
        kodesatuan = null;
        kodesatuan = new String[jml];
        letakbarang = null;
        letakbarang = new String[jml];
        namajenis = null;
        namajenis = new String[jml];
        industri = null;
        industri = new String[jml];
        beli = null;
        beli = new double[jml];
        aturan = null;
        aturan = new String[jml];
        kategori = null;
        kategori = new String[jml];
        golongan = null;
        golongan = new String[jml];

        jml = 0;
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (!tbObat.getValueAt(i, 1).toString().equals("")) {
                pilih[jml] = Boolean.parseBoolean(tbObat.getValueAt(i, 0).toString());
                try {
                    jumlah[jml] = Double.parseDouble(tbObat.getValueAt(i, 1).toString());
                } catch (Exception e) {
                    jumlah[jml] = 0;
                }
                kodebarang[jml] = tbObat.getValueAt(i, 2).toString();
                namabarang[jml] = tbObat.getValueAt(i, 3).toString();
                kodesatuan[jml] = tbObat.getValueAt(i, 4).toString();
                letakbarang[jml] = tbObat.getValueAt(i, 5).toString();
                try {
                    harga[jml] = Double.parseDouble(tbObat.getValueAt(i, 6).toString());
                } catch (Exception e) {
                    harga[jml] = 0;
                }
                namajenis[jml] = tbObat.getValueAt(i, 7).toString();
                try {
                    eb[jml] = Double.parseDouble(tbObat.getValueAt(i, 8).toString());
                } catch (Exception e) {
                    eb[jml] = 0;
                }
                try {
                    ts[jml] = Double.parseDouble(tbObat.getValueAt(i, 9).toString());
                } catch (Exception e) {
                    ts[jml] = 0;
                }
                try {
                    stok[jml] = Double.parseDouble(tbObat.getValueAt(i, 10).toString());
                } catch (Exception e) {
                    stok[jml] = 0;
                }
                industri[jml] = tbObat.getValueAt(i, 11).toString();
                try {
                    beli[jml] = Double.parseDouble(tbObat.getValueAt(i, 12).toString());
                } catch (Exception e) {
                    beli[jml] = 0;
                }
                aturan[jml] = tbObat.getValueAt(i, 13).toString();
                kategori[jml] = tbObat.getValueAt(i, 14).toString();
                golongan[jml] = tbObat.getValueAt(i, 15).toString();
                jml++;
            }
        }

        Valid.tabelKosong(tabMode);

        for (i = 0; i < jml; i++) {
            tabMode.addRow(new Object[]{pilih[i], jumlah[i], kodebarang[i], namabarang[i],
                kodesatuan[i], letakbarang[i], harga[i], namajenis[i], eb[i], ts[i], stok[i], industri[i],
                beli[i], aturan[i], kategori[i], golongan[i]
            });
        }

        try {
            if (kenaikan > 0) {
//                if (var.getkdbangsal().equals("APT01") || var.getkdbangsal().equals("APT02")) {
//                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
//                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.nama_brng not like '(FR)%' and (databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ?) order by databarang.nama_brng");
//                } else {
//                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
//                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '(FR)%' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");
//                }
                
//                if (var.getkdbangsal().equals("APT01") || var.getkdbangsal().equals("APT02")) {
//                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
//                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where (databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
//                } else {
//                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
//                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
//                }
                if (akses.getkdbangsal().equals("APT07")) {
                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.nama_brng not like '(FR)%' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and databarang.nama_brng not like '%(FR)%' and jenis.nama like ? order by databarang.nama_brng");
                } else {
                    psobatasuransi = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,(databarang.h_beli+(databarang.h_beli*?)) as harga,"
                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi on databarang.kdjns=jenis.kdjns "
                            + " and industrifarmasi.kode_industri=databarang.kode_industri inner join golongan_barang inner join kategori_barang on databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode where databarang.status='1' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
                }

                try {
                    psobatasuransi.setDouble(1, kenaikan);
                    psobatasuransi.setString(2, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(3, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(4, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(5, "%" + TCari.getText().trim() + "%");
                    psobatasuransi.setString(6, "%" + TCari.getText().trim() + "%");
                    rsobat = psobatasuransi.executeQuery();
                    while (rsobat.next()) {
                        tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                            rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("harga"), 100),
                            rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                            rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsobat != null) {
                        rsobat.close();
                    }
                    if (psobatasuransi != null) {
                        psobatasuransi.close();
                    }
                }
            } else {
//                if (var.getkdbangsal().equals("APT01") || var.getkdbangsal().equals("APT02")) {
//                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
//                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
//                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
//                            + " where MID(databarang.nama_brng,1,4) <> '(FR)' and (databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
//                } else {
//                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
//                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang"
//                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
//                            + " where databarang.status='1' and databarang.kode_brng like ? or"
//                            + " databarang.status='1' and databarang.nama_brng like ? or"
//                            + " databarang.status='1' and kategori_barang.nama like ? or"
//                            + " databarang.status='1' and golongan_barang.nama like ? or"
//                            + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
//                }

//                if (var.getkdbangsal().equals("APT01") || var.getkdbangsal().equals("APT02")) {
//                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
//                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
//                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
//                            + " where (databarang.status='1' and databarang.kode_brng like ? or "
//                            + " databarang.status='1' and databarang.nama_brng like ? or "
//                            + " databarang.status='1' and kategori_barang.nama like ? or "
//                            + " databarang.status='1' and golongan_barang.nama like ? or "
//                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
//                } else {
//                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
//                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
//                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang"
//                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
//                            + " where databarang.status='1' and databarang.kode_brng like ? or"
//                            + " databarang.status='1' and databarang.nama_brng like ? or"
//                            + " databarang.status='1' and kategori_barang.nama like ? or"
//                            + " databarang.status='1' and golongan_barang.nama like ? or"
//                            + " databarang.status='1' and jenis.nama like ? order by databarang.nama_brng");
//                }
                if (akses.getkdbangsal().equals("APT07")) {
                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
                            + " where (databarang.status='1' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
                } else {
                    psobat = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,databarang.kelas1,"
                            + " databarang.kelas2,databarang.kelas3,databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.karyawan,"
                            + " databarang.letak_barang,industrifarmasi.nama_industri,databarang.h_beli,kategori_barang.nama as kategori,golongan_barang.nama as golongan from databarang inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
                            + " on databarang.kdjns=jenis.kdjns and industrifarmasi.kode_industri=databarang.kode_industri and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
                            + " where MID(databarang.nama_brng,1,4) <> '(FR)' and (databarang.status='1' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and jenis.nama like ?) order by databarang.nama_brng");
                }

                try {
                    psobat.setString(1, "%" + TCari.getText().trim() + "%");
                    psobat.setString(2, "%" + TCari.getText().trim() + "%");
                    psobat.setString(3, "%" + TCari.getText().trim() + "%");
                    psobat.setString(4, "%" + TCari.getText().trim() + "%");
                    psobat.setString(5, "%" + TCari.getText().trim() + "%");
                    rsobat = psobat.executeQuery();
                    while (rsobat.next()) {
                        if (Jeniskelas.getSelectedItem().equals("Kelas 1")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("kelas1"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Kelas 2")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("kelas2"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Kelas 3")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("kelas3"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Utama/BPJS")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("utama"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("VIP")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("vip"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("VVIP")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("vvip"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Beli Luar")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("beliluar"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
                        } else if (Jeniskelas.getSelectedItem().equals("Karyawan")) {
                            tabMode.addRow(new Object[]{false, "", rsobat.getString("kode_brng"), rsobat.getString("nama_brng"),
                                rsobat.getString("kode_sat"), rsobat.getString("letak_barang"), Valid.roundUp(rsobat.getDouble("karyawan"), 100),
                                rsobat.getString("nama"), 0, 0, 0, rsobat.getString("nama_industri"),
                                rsobat.getDouble("h_beli"), "", rsobat.getString("kategori"), rsobat.getString("golongan")
                            });
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
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeks() {
        TCari.setText("");
        TCari.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            /*if(!tabMode.getValueAt(tbKamar.getSelectedRow(),0).toString().equals("")){
                if(Double.parseDouble(tbKamar.getValueAt(tbKamar.getSelectedRow(),0).toString())>Double.parseDouble(tbKamar.getValueAt(tbKamar.getSelectedRow(),6).toString())){
                        JOptionPane.showMessageDialog(null,"Maaf, Stok tidak cukup....!!!");
                        TCari.requestFocus();
                        tbKamar.setValueAt("", tbKamar.getSelectedRow(),0);  
                }                
            }*/

        }
    }

    public JTable getTable() {
        return tbObat;
    }

    public void isCek() {
        BtnTambah.setEnabled(akses.getobat());
        TCari.requestFocus();
        isSetBangsal();
        if (akses.getkdbangsal().equals("APT02") || akses.getkdbangsal().equals("APT07")) {
            BtnVerifRalan.setEnabled(false);
            BtnCekResepRalan.setEnabled(false);
        } else if (akses.getkdbangsal().equals("APT01")) {
            BtnVerifRalan.setEnabled(true);
            BtnCekResepRalan.setEnabled(true);
        }
    }

    public void setNoRm(String norwt, Date tanggal, String jam, String menit, String detik, boolean status) {
        TNoRw.setText(norwt);
        cmbStatus.setSelectedIndex(0);
        cmbKertas.setSelectedIndex(0);
        cmbStatus1.setSelectedIndex(0);
        cmbKertas1.setSelectedIndex(0);
        tampil_resepRanap();
        isSetBangsal();
        
        if (akses.getkdbangsal().equals("APT02") || akses.getkdbangsal().equals("APT07")) {
            Valid.tabelKosong(tabModeResepObat1);
        } else if (akses.getkdbangsal().equals("APT01")) {
            tampil_resepRalan();
        }
        isPsien();
        DTPTgl.setDate(tanggal);
        cmbJam.setSelectedItem(jam);
        cmbMnt.setSelectedItem(menit);
        cmbDtk.setSelectedItem(detik);
        ChkJln.setSelected(status);
        KdPj.setText(Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norwt));
        kelas.setText(Sequel.cariIsi(
                "select kamar.kelas from kamar inner join kamar_inap on kamar.kd_kamar=kamar_inap.kd_kamar "
                + "where no_rawat=? and stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1", norwt));
        if (kelas.getText().equals("Kelas 1")) {
            Jeniskelas.setSelectedItem("Kelas 1");
        } else if (kelas.getText().equals("Kelas 2")) {
            Jeniskelas.setSelectedItem("Kelas 2");
        } else if (kelas.getText().equals("Kelas 3")) {
            Jeniskelas.setSelectedItem("Kelas 3");
        } else if (kelas.getText().equals("Kelas Utama")) {
            Jeniskelas.setSelectedItem("Utama/BPJS");
        } else if (kelas.getText().equals("Kelas VIP")) {
            Jeniskelas.setSelectedItem("VIP");
        } else if (kelas.getText().equals("Kelas VVIP")) {
            Jeniskelas.setSelectedItem("VVIP");
        }
        kenaikan = Sequel.cariIsiAngka("select (hargajual/100) from set_harga_obat_ranap where kd_pj='" + KdPj.getText() + "' and kelas='" + kelas.getText() + "'");
        TCari.requestFocus();
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

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRm, TNoRw.getText());

    }

    private void isStok(String a) {
        Sequel.cariIsi("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?", TStok, akses.getkdbangsal(), a);
    }

    private void isSetBangsal() {
//        if (LocalTime.now().isAfter(LocalTime.parse("08:00:00")) && LocalTime.now().isBefore(LocalTime.parse("21:00:00"))) {
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
    
    public void tampil_resepRanap() {
        Valid.tabelKosong(tabModeResepObat);
        try {
            if (ChkTgl.isSelected() == true) {
                if (cmbStatus.getSelectedIndex() == 3) {
                    ps = koneksi.prepareStatement("SELECT c.no_rawat, c.nama_obat, c.status, c.noId, "
                            + "date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, c.jam_perawatan, d.nm_dokter "
                            + "FROM catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter "
                            + "WHERE c.tgl_perawatan between '" + Valid.SetTgl(DTPTgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPTgl2.getSelectedItem() + "") + "' "
                            + "and c.no_rawat LIKE '%" + TNoRw.getText().trim() + "%' ORDER BY c.status, c.noId");
                } else {
                    ps = koneksi.prepareStatement("SELECT c.no_rawat, c.nama_obat, c.STATUS, c.noId, "
                            + "date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, c.jam_perawatan, d.nm_dokter "
                            + "FROM catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter "
                            + "where c.tgl_perawatan between '" + Valid.SetTgl(DTPTgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPTgl2.getSelectedItem() + "") + "' "
                            + "and c.no_rawat like '%" + TNoRw.getText().trim() + "%' and c.status like '%" + cmbStatus.getSelectedItem().toString() + "%' "
                            + "order by c.status, c.noId");
                }
            } else {
                if (cmbStatus.getSelectedIndex() == 3) {
                    ps = koneksi.prepareStatement("SELECT c.no_rawat, c.nama_obat, c.status, c.noId, "
                            + "date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, c.jam_perawatan, d.nm_dokter "
                            + "FROM catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter "
                            + "WHERE c.no_rawat LIKE '%" + TNoRw.getText().trim() + "%' "
                            + "ORDER BY c.status, c.noId");
                } else {
                    ps = koneksi.prepareStatement("SELECT c.no_rawat, c.nama_obat, c.STATUS, c.noId, "
                            + "date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, c.jam_perawatan, d.nm_dokter "
                            + "FROM catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter "
                            + "where c.no_rawat like '%" + TNoRw.getText().trim() + "%' "
                            + "and c.status like '%" + cmbStatus.getSelectedItem().toString() + "%' "
                            + "order by c.status, c.noId");
                }
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
        LCountRanap.setText("" + tabModeResepObat.getRowCount());
    }
    
    public void tampil_resepRalan() {
        Valid.tabelKosong(tabModeResepObat1);
        try {
            if (cmbStatus1.getSelectedIndex() == 3) {
                ps1 = koneksi.prepareStatement("SELECT c.no_rawat, c.nama_obat, c.status, c.noId, "
                        + "date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, c.jam_perawatan, d.nm_dokter "
                        + "FROM catatan_resep c inner join dokter d on d.kd_dokter=c.kd_dokter "
                        + "WHERE c.no_rawat LIKE '%" + TNoRw.getText().trim() + "%' "
                        + "ORDER BY c.status, c.noId");
            } else {
                ps1 = koneksi.prepareStatement("SELECT c.no_rawat, c.nama_obat, c.STATUS, c.noId, "
                        + "date_format(c.tgl_perawatan,'%d-%m-%Y') tgl, c.jam_perawatan, d.nm_dokter "
                        + "FROM catatan_resep c inner join dokter d on d.kd_dokter=c.kd_dokter "
                        + "where c.no_rawat like '%" + TNoRw.getText().trim() + "%' "
                        + "and c.status like '%" + cmbStatus1.getSelectedItem().toString() + "%' "
                        + "order by c.status, c.noId");
            }            
            chkResepObat1.setSelected(false);
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabModeResepObat1.addRow(new Object[]{
                        false, 
                        rs1.getString("no_rawat"),
                        rs1.getString("nama_obat"),
                        rs1.getString("tgl"), 
                        rs1.getString("jam_perawatan"),
                        rs1.getString("status"),
                        rs1.getString("noId"),
                        rs1.getString("nm_dokter")
                    });
                }
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            chkResepObat.setSelected(false);
        }
        LCountRalan.setText("" + tabModeResepObat1.getRowCount());
    }
    
    private void Simpan() {
        try {
            isSetBangsal();
            koneksi.setAutoCommit(false);
            ttlhpp = 0;
            ttljual = 0;
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
                        ppBersihkanActionPerformed(null);
                    } else {
                        urut++;
                        if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                            pscarikapasitas = koneksi.prepareStatement("select IFNULL(kapasitas,1) from databarang where kode_brng=?");
                            try {
                                pscarikapasitas.setString(1, tbObat.getValueAt(i, 2).toString());
                                carikapasitas = pscarikapasitas.executeQuery();
                                if (carikapasitas.next()) {
//                                    if (Sequel.menyimpantf2("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?", "data", 12, new String[]{
//                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
//                                        tbObat.getValueAt(i, 6).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)),
//                                        tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + Valid.SetAngka2(Double.parseDouble(tbObat.getValueAt(i, 8).toString())
//                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
//                                        * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)))), "Ranap", bangsal
                                    if (Sequel.menyimpantf2("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
                                        tbObat.getValueAt(i, 6).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)),
                                        tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                        * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)))), "Ranap", bangsal, "Belum", "-", String.valueOf(urut)
                                    }) == true) {
                                        isRawat();
                                        ttljual = ttljual + Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                                + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                                * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)));
                                        ttlhpp = ttlhpp + (Double.parseDouble(tbObat.getValueAt(i, 12).toString())
                                                * (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)));
                                        Trackobat.catatRiwayat(tbObat.getValueAt(i, 2).toString(), 0, (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                        Trackobat.catatRiwayatObat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRm.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                        Sequel.menyimpan("gudangbarang", "'" + tbObat.getValueAt(i, 2).toString() + "','" + bangsal + "','-" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)) + "'",
                                                "stok=stok-'" + (Double.parseDouble(tbObat.getValueAt(i, 1).toString()) / carikapasitas.getDouble(1)) + "'", "kode_brng='" + tbObat.getValueAt(i, 2).toString() + "' and kd_bangsal='" + bangsal + "'");
                                    } else {
                                        //JOptionPane.showMessageDialog(null,"Gagal Menyimpan, Kemungkinan ada data sama/kapasitas tidak ditemukan..ATAS = !!" + tbObat.getValueAt(i,2).toString());
                                        JOptionPane.showMessageDialog(null, "Gagal Menyimpan, Kemungkinan ada data sama/kapasitas tidak ditemukan..!!");
                                    }
                                } else {
//                                    if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?", "data", 12, new String[]{
//                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
//                                        tbObat.getValueAt(i, 6).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
//                                        tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
//                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
//                                        * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ranap", bangsal
                                    if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                        Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
                                        tbObat.getValueAt(i, 6).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
                                        tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                        * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ranap", bangsal, "Belum", "-", String.valueOf(urut)
                                    }) == true) {
                                        isRawat();
                                        Sequel.menyimpan("aturan_pakai", "?,?,?,?,?,?,?,?,?,?,?,?", 12, new String[]{
                                            Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                            TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), "", "", "", "", "", "", "", String.valueOf(urut)
                                        });
                                        
                                        ttljual = ttljual + Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                                + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                                * Double.parseDouble(tbObat.getValueAt(i, 1).toString()));
                                        ttlhpp = ttlhpp + (Double.parseDouble(tbObat.getValueAt(i, 12).toString())
                                                * Double.parseDouble(tbObat.getValueAt(i, 1).toString()));
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
//                            if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?", "data", 12, new String[]{
//                                Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
//                                tbObat.getValueAt(i, 6).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
//                                tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
//                                + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
//                                * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ranap", bangsal
                            if (Sequel.menyimpantf("detail_pemberian_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 15, new String[]{
                                Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), tbObat.getValueAt(i, 12).toString(),
                                tbObat.getValueAt(i, 6).toString(), "" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()),
                                tbObat.getValueAt(i, 8).toString(), tbObat.getValueAt(i, 9).toString(), "" + (Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                * Double.parseDouble(tbObat.getValueAt(i, 1).toString()))), "Ranap", bangsal, "Belum", "-", String.valueOf(urut)
                            }) == true) {
                                isRawat();
                                Sequel.menyimpan("aturan_pakai", "?,?,?,?,?,?,?,?,?,?,?,?", 12, new String[]{
                                    Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                    TNoRw.getText(), tbObat.getValueAt(i, 2).toString(), "", "", "", "", "", "", "", String.valueOf(urut)
                                });

                                ttljual = ttljual + Valid.roundUp(Double.parseDouble(tbObat.getValueAt(i, 8).toString())
                                        + Double.parseDouble(tbObat.getValueAt(i, 9).toString()) + (Double.parseDouble(tbObat.getValueAt(i, 6).toString())
                                        * Double.parseDouble(tbObat.getValueAt(i, 1).toString())), 100);
                                ttlhpp = ttlhpp + (Double.parseDouble(tbObat.getValueAt(i, 12).toString())
                                        * Double.parseDouble(tbObat.getValueAt(i, 1).toString()));
                                Trackobat.catatRiwayat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                Trackobat.catatRiwayatObat(tbObat.getValueAt(i, 2).toString(), 0, Double.parseDouble(tbObat.getValueAt(i, 1).toString()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRm.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                Sequel.menyimpan("gudangbarang", "'" + tbObat.getValueAt(i, 2).toString() + "','" + bangsal + "','-" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'",
                                        "stok=stok-'" + Double.parseDouble(tbObat.getValueAt(i, 1).toString()) + "'", "kode_brng='" + tbObat.getValueAt(i, 2).toString() + "' and kd_bangsal='" + bangsal + "'");
                            }
                        }
                    }
                    tbObat.setValueAt("", i, 1);
                }

            }
            Sequel.queryu("delete from tampjurnal");
            if (ttljual > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','" + ttljual + "','0'", "Rekening");
                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','0','" + ttljual + "'", "Rekening");
            }
            if (ttlhpp > 0) {
                Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
                Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
            }
            jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBERIAN OBAT RAWAT INAP PASIEN, DIPOSTING OLEH " + akses.getkode());

            koneksi.setAutoCommit(true);
            if (ChkNoResep.isSelected() == true) {
                DlgResepObat resep = new DlgResepObat(null, false);
                resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                resep.setLocationRelativeTo(internalFrame1);
                resep.emptTeks();
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString());
                resep.tampil();
                resep.setAlwaysOnTop(true);
                resep.dokter.setAlwaysOnTop(true);
                resep.setVisible(true);
//                resep.setStatus(status);
            } else {
                dispose();
            }
            dispose();
        } catch (Exception ex) {
            System.out.println(ex);
            //JOptionPane.showMessageDialog(null,"Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya?\nKapasitas belum dimasukkan...!BAWAH");
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya?\nKapasitas belum dimasukkan...!");
        }
    }
}
