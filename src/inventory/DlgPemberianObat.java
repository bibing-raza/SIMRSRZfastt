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
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import inventory.DlgCariKonversi;
import inventory.DlgCariObat;
import inventory.DlgCariObat2;
import inventory.DlgCariObat3;
import inventory.riwayatobat;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import keuangan.Jurnal;
import laporan.DlgLaporanFarmasi;
import simrskhanza.DlgCariObatPenyakit;
import simrskhanza.DlgPasien;

/**
 *
 * @author perpustakaan
 */
public class DlgPemberianObat extends javax.swing.JDialog {

    private final DefaultTableModel tabModePO;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    public DlgCariObat dlgobtjalan = new DlgCariObat(null, false);
    public DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
    public DlgCariObat3 dlgobt2 = new DlgCariObat3(null, false);
    private riwayatobat Trackobat = new riwayatobat();
    private DlgPasien pasien = new DlgPasien(null, false);
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();
    private String now = dateFormat.format(date), bangsal = "", tgl = "", pas = "", sql = "", status = "", statussimpan = "", kdptg = "", nmptg = "", cariDetailObat = "";
    private PreparedStatement ps, psrekening;
    private ResultSet rs, rsrekening;
    private double embalase = Sequel.cariIsiAngka("select embalase_per_obat from set_embalase"), ttljual, ttlhpp;
    private double tuslah = Sequel.cariIsiAngka("select tuslah_per_obat from set_embalase");
    private double stokbarang2, cekDataObat;
    private String statusberi = "", Suspen_Piutang_Obat_Ranap = "", Obat_Ranap = "", HPP_Obat_Rawat_Inap = "", Persediaan_Obat_Rawat_Inap = "";
    private Jurnal jur = new Jurnal();
    private int i;

    /**
     * Creates new form DlgPemberianObat
     *
     * @param parent
     * @param modal
     */
    public DlgPemberianObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"Tgl.Beri", "Jam Beri", "No.Rawat", "No.R.M.",
            "Nama Pasien", "Kode Obat", "Nama Obat/Alkes", "Embalase",
            "Tuslah", "Jml", "Biaya Obat", "Total", "Harga Beli", "Gudang"};
        tabModePO = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPemberianObat.setModel(tabModePO);
        //tampilPO("");

        tbPemberianObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemberianObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 14; i++) {
            TableColumn column = tbPemberianObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {//sembunyi
                //column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(40);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setPreferredWidth(150);
            }
        }
        tbPemberianObat.setDefaultRenderer(Object.class, new WarnaTable());

        this.setLocation(8, 1);
        setSize(885, 674);

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TKdOb.setDocument(new batasInput((byte) 15).getKata(TKdOb));
        TJumlah.setDocument(new batasInput((byte) 25).getKata(TJumlah));
        TEmbalase.setDocument(new batasInput((byte) 15).getKata(TEmbalase));
        TTuslah.setDocument(new batasInput((byte) 15).getKata(TTuslah));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TCariPasien.setDocument(new batasInput((byte) 20).getKata(TCariPasien));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilPO();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilPO();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilPO();
                }
            });
        }

        dlgobt.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (dlgobt.getTable().getSelectedRow() != -1) {
                        TKdOb.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(), 2).toString());
                        TNmOb.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(), 3).toString());
                        TSatuan.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(), 4).toString());
                        TBiayaObat.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(), 6).toString());
                        THBeli.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(), 12).toString());
                    }
                    TKdOb.requestFocus();
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

        dlgobt.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        dlgobt.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dlgobtjalan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (dlgobtjalan.getTable().getSelectedRow() != -1) {
                        TKdOb.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(), 2).toString());
                        TNmOb.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(), 3).toString());
                        TSatuan.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(), 4).toString());
                        TBiayaObat.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(), 6).toString());
                        THBeli.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(), 13).toString());
                    }
                    TKdOb.requestFocus();
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

        dlgobtjalan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        dlgobtjalan.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dlgobt2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (dlgobt2.getTable().getSelectedRow() != -1) {
                        TKdOb.setText(dlgobt2.getTable().getValueAt(dlgobt2.getTable().getSelectedRow(), 1).toString());
                        TNmOb.setText(dlgobt2.getTable().getValueAt(dlgobt2.getTable().getSelectedRow(), 2).toString());
                    }
                    TKdOb.requestFocus();
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

        dlgobt2.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        dlgobt2.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                    }
                    TCariPasien.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        ChkInput.setSelected(false);
        isForm();
        jam();
        isSetBangsal();

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

    //private DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup2 = new javax.swing.JPopupMenu();
        ppResepObat = new javax.swing.JMenuItem();
        ppNoRawat = new javax.swing.JMenuItem();
        ppHapusSemua = new javax.swing.JMenuItem();
        ppLaporanFarmasi = new javax.swing.JMenuItem();
        THBeli = new widget.TextBox();
        TStok = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPemberianObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel14 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel19 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCariPasien = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel7 = new widget.Label();
        TJumlah = new widget.TextBox();
        jLabel9 = new widget.Label();
        DTPBeri = new widget.Tanggal();
        jLabel13 = new widget.Label();
        TKdOb = new widget.TextBox();
        btnObat1 = new widget.Button();
        TNmOb = new widget.TextBox();
        TEmbalase = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel11 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        TBiayaObat = new widget.TextBox();
        jLabel17 = new widget.Label();
        TSatuan = new widget.TextBox();
        TTotal = new widget.TextBox();
        ChkJln = new widget.CekBox();
        btnKonversi = new widget.Button();
        BtnObat2 = new widget.Button();
        BtnObat3 = new widget.Button();
        jLabel20 = new widget.Label();
        TTuslah = new widget.TextBox();
        jLabel21 = new widget.Label();

        Popup2.setName("Popup2"); // NOI18N

        ppResepObat.setBackground(new java.awt.Color(255, 255, 255));
        ppResepObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppResepObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppResepObat.setText("Buat Nomor Resep Obat");
        ppResepObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppResepObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppResepObat.setIconTextGap(8);
        ppResepObat.setName("ppResepObat"); // NOI18N
        ppResepObat.setPreferredSize(new java.awt.Dimension(200, 25));
        ppResepObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppResepObatActionPerformed(evt);
            }
        });
        Popup2.add(ppResepObat);

        ppNoRawat.setBackground(new java.awt.Color(255, 255, 255));
        ppNoRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppNoRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppNoRawat.setText("Rekap Per No.Rawat");
        ppNoRawat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppNoRawat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppNoRawat.setIconTextGap(8);
        ppNoRawat.setName("ppNoRawat"); // NOI18N
        ppNoRawat.setPreferredSize(new java.awt.Dimension(200, 25));
        ppNoRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppNoRawatActionPerformed(evt);
            }
        });
        Popup2.add(ppNoRawat);

        ppHapusSemua.setBackground(new java.awt.Color(255, 255, 255));
        ppHapusSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapusSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppHapusSemua.setText("Hapus Semua");
        ppHapusSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapusSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapusSemua.setIconTextGap(8);
        ppHapusSemua.setName("ppHapusSemua"); // NOI18N
        ppHapusSemua.setPreferredSize(new java.awt.Dimension(200, 25));
        ppHapusSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusSemuaActionPerformed(evt);
            }
        });
        Popup2.add(ppHapusSemua);

        ppLaporanFarmasi.setBackground(new java.awt.Color(255, 255, 255));
        ppLaporanFarmasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLaporanFarmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppLaporanFarmasi.setText("Laporan Farmasi");
        ppLaporanFarmasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppLaporanFarmasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppLaporanFarmasi.setIconTextGap(8);
        ppLaporanFarmasi.setName("ppLaporanFarmasi"); // NOI18N
        ppLaporanFarmasi.setPreferredSize(new java.awt.Dimension(200, 25));
        ppLaporanFarmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLaporanFarmasiActionPerformed(evt);
            }
        });
        Popup2.add(ppLaporanFarmasi);

        THBeli.setText("0");
        THBeli.setHighlighter(null);
        THBeli.setName("THBeli"); // NOI18N

        TStok.setText("0");
        TStok.setHighlighter(null);
        TStok.setName("TStok"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Detail Pemberian Obat/Barang/Alkes/Perlengkapan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup2);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPemberianObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemberianObat.setComponentPopupMenu(Popup2);
        tbPemberianObat.setName("tbPemberianObat"); // NOI18N
        tbPemberianObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemberianObatMouseClicked(evt);
            }
        });
        tbPemberianObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemberianObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPemberianObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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
        BtnSimpan.setEnabled(false);
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
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

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

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnPrint);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
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
        panelGlass8.add(BtnAll);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(45, 23));
        panelGlass8.add(LCount);

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

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl.Beri :");
        jLabel14.setToolTipText("");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(58, 23));
        panelGlass9.add(jLabel14);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-11-2022" }));
        DTPCari1.setToolTipText("");
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari1);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("s.d");
        jLabel19.setToolTipText("");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(18, 23));
        panelGlass9.add(jLabel19);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-11-2022" }));
        DTPCari2.setToolTipText("");
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari2);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("No.RM :");
        jLabel16.setToolTipText("");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(jLabel16);

        TCariPasien.setForeground(new java.awt.Color(0, 0, 0));
        TCariPasien.setToolTipText("");
        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(130, 23));
        TCariPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPasienKeyPressed(evt);
            }
        });
        panelGlass9.add(TCariPasien);

        BtnSeek4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        BtnSeek4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek4KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnSeek4);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setToolTipText("");
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator5);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setToolTipText("");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setToolTipText("");
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("");
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
        panelGlass9.add(BtnCari);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 160));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 170));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(-2, 12, 80, 23);

        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.setPreferredSize(new java.awt.Dimension(175, 23));
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(81, 12, 160, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(143, 23));
        FormInput.add(TNoRM);
        TNoRM.setBounds(244, 12, 100, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(425, 23));
        FormInput.add(TPasien);
        TPasien.setBounds(347, 12, 480, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tgl.Beri :");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(-2, 42, 80, 23);

        TJumlah.setForeground(new java.awt.Color(0, 0, 0));
        TJumlah.setText("0");
        TJumlah.setHighlighter(null);
        TJumlah.setName("TJumlah"); // NOI18N
        TJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJumlahKeyPressed(evt);
            }
        });
        FormInput.add(TJumlah);
        TJumlah.setBounds(81, 102, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jumlah :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(-2, 102, 80, 23);

        DTPBeri.setEditable(false);
        DTPBeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-11-2022" }));
        DTPBeri.setDisplayFormat("dd-MM-yyyy");
        DTPBeri.setName("DTPBeri"); // NOI18N
        DTPBeri.setOpaque(false);
        DTPBeri.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPBeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPBeriKeyPressed(evt);
            }
        });
        FormInput.add(DTPBeri);
        DTPBeri.setBounds(81, 42, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Obt/Alkes :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(-2, 72, 80, 23);

        TKdOb.setEditable(false);
        TKdOb.setForeground(new java.awt.Color(0, 0, 0));
        TKdOb.setName("TKdOb"); // NOI18N
        TKdOb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdObKeyPressed(evt);
            }
        });
        FormInput.add(TKdOb);
        TKdOb.setBounds(81, 72, 90, 23);

        btnObat1.setForeground(new java.awt.Color(0, 0, 0));
        btnObat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnObat1.setMnemonic('4');
        btnObat1.setToolTipText("Alt+4");
        btnObat1.setName("btnObat1"); // NOI18N
        btnObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObat1ActionPerformed(evt);
            }
        });
        FormInput.add(btnObat1);
        btnObat1.setBounds(545, 72, 28, 23);

        TNmOb.setEditable(false);
        TNmOb.setForeground(new java.awt.Color(0, 0, 0));
        TNmOb.setName("TNmOb"); // NOI18N
        FormInput.add(TNmOb);
        TNmOb.setBounds(173, 72, 288, 23);

        TEmbalase.setForeground(new java.awt.Color(0, 0, 0));
        TEmbalase.setText("0");
        TEmbalase.setHighlighter(null);
        TEmbalase.setName("TEmbalase"); // NOI18N
        TEmbalase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEmbalaseKeyPressed(evt);
            }
        });
        FormInput.add(TEmbalase);
        TEmbalase.setBounds(303, 102, 100, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Embalase : Rp.");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(210, 102, 90, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(174, 42, 37, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setOpaque(false);
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbJamMouseClicked(evt);
            }
        });
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(215, 42, 55, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setOpaque(false);
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbMntMouseClicked(evt);
            }
        });
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(275, 42, 55, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setOpaque(false);
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbDtkMouseClicked(evt);
            }
        });
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(335, 42, 55, 23);

        TBiayaObat.setForeground(new java.awt.Color(0, 0, 0));
        TBiayaObat.setText("0");
        TBiayaObat.setHighlighter(null);
        TBiayaObat.setName("TBiayaObat"); // NOI18N
        FormInput.add(TBiayaObat);
        TBiayaObat.setBounds(710, 72, 118, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Harga : Rp.");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(637, 72, 70, 23);

        TSatuan.setEditable(false);
        TSatuan.setForeground(new java.awt.Color(0, 0, 0));
        TSatuan.setName("TSatuan"); // NOI18N
        FormInput.add(TSatuan);
        TSatuan.setBounds(463, 72, 80, 23);

        TTotal.setEditable(false);
        TTotal.setForeground(new java.awt.Color(0, 0, 0));
        TTotal.setText("0");
        TTotal.setHighlighter(null);
        TTotal.setName("TTotal"); // NOI18N
        FormInput.add(TTotal);
        TTotal.setBounds(698, 102, 130, 23);

        ChkJln.setBackground(new java.awt.Color(235, 255, 235));
        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        FormInput.add(ChkJln);
        ChkJln.setBounds(395, 42, 23, 23);

        btnKonversi.setForeground(new java.awt.Color(0, 0, 0));
        btnKonversi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        btnKonversi.setMnemonic('2');
        btnKonversi.setToolTipText("Alt+2");
        btnKonversi.setName("btnKonversi"); // NOI18N
        btnKonversi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonversiActionPerformed(evt);
            }
        });
        FormInput.add(btnKonversi);
        btnKonversi.setBounds(173, 102, 28, 23);

        BtnObat2.setForeground(new java.awt.Color(0, 0, 0));
        BtnObat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat2.setMnemonic('4');
        BtnObat2.setToolTipText("Alt+4");
        BtnObat2.setName("BtnObat2"); // NOI18N
        BtnObat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObat2ActionPerformed(evt);
            }
        });
        FormInput.add(BtnObat2);
        BtnObat2.setBounds(575, 72, 28, 23);

        BtnObat3.setForeground(new java.awt.Color(0, 0, 0));
        BtnObat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat3.setMnemonic('4');
        BtnObat3.setToolTipText("Alt+4");
        BtnObat3.setName("BtnObat3"); // NOI18N
        BtnObat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObat3ActionPerformed(evt);
            }
        });
        FormInput.add(BtnObat3);
        BtnObat3.setBounds(604, 72, 28, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tuslah : Rp.");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(417, 102, 70, 23);

        TTuslah.setForeground(new java.awt.Color(0, 0, 0));
        TTuslah.setText("0");
        TTuslah.setHighlighter(null);
        TTuslah.setName("TTuslah"); // NOI18N
        TTuslah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTuslahKeyPressed(evt);
            }
        });
        FormInput.add(TTuslah);
        TTuslah.setBounds(490, 102, 100, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Total Biaya : Rp.");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(605, 102, 90, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

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
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampilPO();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        Valid.pindah(evt, TCari, DTPBeri);
}//GEN-LAST:event_TNoRwKeyPressed

    private void TJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJumlahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            TEmbalase.setText(Double.toString(embalase));
            TTuslah.setText(Double.toString(tuslah));
            isjml();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TEmbalase.setText(Double.toString(embalase));
            TTuslah.setText(Double.toString(tuslah));
            isjml();
            TKdOb.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TEmbalase.setText(Double.toString(embalase));
            TTuslah.setText(Double.toString(tuslah));
            isjml();
            TEmbalase.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKonversiActionPerformed(null);
        }
}//GEN-LAST:event_TJumlahKeyPressed

    private void DTPBeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPBeriKeyPressed
        Valid.pindah(evt, TNoRw, cmbJam);
}//GEN-LAST:event_DTPBeriKeyPressed

    private void TKdObKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdObKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnObat1ActionPerformed(null);
        } else {
            Valid.pindah(evt, cmbDtk, TJumlah);
        }
    }//GEN-LAST:event_TKdObKeyPressed

    private void btnObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObat1ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            akses.setform("DlgPemberianObat");
            if (akses.getkode().equals("Admin Utama")) {
                if (status.equals("ranap")) {
                    dlgobt.setNoRm(TNoRw.getText(), DTPBeri.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false);
                    dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobt.isCek();
                    dlgobt.tampil();
                    dlgobt.setLocationRelativeTo(internalFrame1);
                    dlgobt.setVisible(true);
                } else if (status.equals("ralan")) {
                    dlgobtjalan.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
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
//                if(Sequel.cariRegistrasi(TNoRw.getText())>0){
////                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
////                    TCari.requestFocus();
//                    int jawab=JOptionPane.showConfirmDialog(null, "Data billing sudah terverifikasi, apa anda mau input pada penjualan langsung...????","Konfirmasi",JOptionPane.YES_NO_OPTION);
//                    if(jawab==JOptionPane.YES_OPTION){
//                        kdptg = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText());
//                        nmptg = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", kdptg);
//                        jual.setPasien(Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
//                        jual.isCek();
//                        jual.isRw(TNoRw.getText(),kdptg,nmptg);
//                        jual.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
//                        jual.setLocationRelativeTo(internalFrame1);                        
//                        jual.setVisible(true);
//                    }else{
//                        TCari.requestFocus();
//                    }              
//                }else{ 
                if (status.equals("ranap")) {
                    dlgobt.setNoRm(TNoRw.getText(), DTPBeri.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false);
                    dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobt.isCek();
                    dlgobt.tampil();
                    dlgobt.setLocationRelativeTo(internalFrame1);
                    dlgobt.setVisible(true);
                } else if (status.equals("ralan")) {
                    dlgobtjalan.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
                    dlgobtjalan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobtjalan.isCek();
//                        if(!namadokter.equals("")){
//                            dlgobtjalan.setDokter(kodedokter, namadokter);
//                        }
                    dlgobtjalan.tampilobat();
                    dlgobtjalan.tampil_resep();
                    dlgobtjalan.setLocationRelativeTo(internalFrame1);
                    dlgobtjalan.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Hanya bisa lewat Kasir Ralan atau Kamar Inap");
                }
//                }
            }
        }
    }//GEN-LAST:event_btnObat1ActionPerformed

    private void TEmbalaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEmbalaseKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isjml();
            TTuslah.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            isjml();
            TJumlah.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isjml();
        }
    }//GEN-LAST:event_TEmbalaseKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
//        if(Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")){
//            bangsal=Sequel.cariIsi("select kamar.kd_bangsal from kamar inner join kamar_inap "+
//                    "on kamar.kd_kamar=kamar_inap.kd_kamar where kamar_inap.no_rawat=? "+
//                    "order by tgl_masuk desc limit 1",TNoRw.getText());
//            if(bangsal.equals("")){
//                bangsal=Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1");
//            }
//        }else{
//            bangsal=Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1");
//        }

        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else if (TKdOb.getText().trim().equals("") || TNmOb.getText().trim().equals("")) {
            Valid.textKosong(TKdOb, "databarang penyakit");
        } else if (TJumlah.getText().trim().equals("")) {
            Valid.textKosong(TJumlah, "Jumlah Obat");
        } else if (TEmbalase.getText().trim().equals("")) {
            Valid.textKosong(TEmbalase, "Embalase");
        } else if (TTuslah.getText().trim().equals("")) {
            Valid.textKosong(TEmbalase, "Tuslah");
        } else {
            isSetBangsal();
            isStok(TKdOb.getText());
            stokbarang2 = Double.parseDouble(TStok.getText());
            if (stokbarang2 < Double.parseDouble(TJumlah.getText()) || TStok.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
            } else {
                if (akses.getkode().equals("Admin Utama")) {
                    ttlhpp = 0;
                    ttljual = 0;
                    Sequel.AutoComitFalse();
                    if (Sequel.menyimpantf("detail_pemberian_obat", "'" + Valid.SetTgl(DTPBeri.getSelectedItem() + "") + "','"
                            + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "','"
                            + TNoRw.getText() + "','" + TKdOb.getText() + "','" + THBeli.getText() + "','" + TBiayaObat.getText() + "','"
                            + TJumlah.getText() + "','" + TEmbalase.getText() + "','" + TTuslah.getText() + "',"
                            + "((" + TBiayaObat.getText() + "*" + TJumlah.getText() + ")+" + TEmbalase.getText() + "+"
                            + TTuslah.getText() + "),'" + statussimpan + "','" + bangsal + "','Belum','-'", "data") == true) {
                        if (statussimpan.equals("Ranap")) {
                            ttlhpp = Double.parseDouble(THBeli.getText()) * Double.parseDouble(TJumlah.getText());
                            ttljual = (Double.parseDouble(TBiayaObat.getText()) * Double.parseDouble(TJumlah.getText())) + Double.parseDouble(TEmbalase.getText()) + Double.parseDouble(TTuslah.getText());
                            Sequel.queryu("delete from tampjurnal");
                            if (ttljual > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','" + ttljual + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','0','" + ttljual + "'", "Rekening");
                            }
                            if (ttlhpp > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
                            }
                            jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), "U", "PEMBERIAN OBAT RAWAT INAP PASIEN, DIPOSTING OLEH " + akses.getkode());
                        }

                        if (btnObat1.isEnabled() == true) {
                            Trackobat.catatRiwayat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                            Trackobat.catatRiwayatObat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                            Sequel.menyimpan("gudangbarang", "'" + TKdOb.getText() + "','" + bangsal + "','-" + TJumlah.getText() + "'",
                                    "stok=stok-'" + TJumlah.getText() + "'", "kode_brng='" + TKdOb.getText() + "' and kd_bangsal='" + bangsal + "'");
                        }
                    }
                    Sequel.AutoComitTrue();
                    tampilPO();
                    BtnBatalActionPerformed(evt);
                } else {
//                    if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
//                        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
//                        TCari.requestFocus();
//                    } else {
                    ttlhpp = 0;
                    ttljual = 0;
                    Sequel.AutoComitFalse();
                    if (Sequel.menyimpantf("detail_pemberian_obat", "'" + Valid.SetTgl(DTPBeri.getSelectedItem() + "") + "','"
                            + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "','"
                            + TNoRw.getText() + "','" + TKdOb.getText() + "','" + THBeli.getText() + "','" + TBiayaObat.getText() + "','"
                            + TJumlah.getText() + "','" + TEmbalase.getText() + "','" + TTuslah.getText() + "',"
                            + "((" + TBiayaObat.getText() + "*" + TJumlah.getText() + ")+" + TEmbalase.getText() + "+"
                            + TTuslah.getText() + "),'" + statussimpan + "','" + bangsal + "','Belum','-'", "data") == true) {
                        if (statussimpan.equals("Ranap")) {
                            ttlhpp = Double.parseDouble(THBeli.getText()) * Double.parseDouble(TJumlah.getText());
                            ttljual = (Double.parseDouble(TBiayaObat.getText()) * Double.parseDouble(TJumlah.getText())) + Double.parseDouble(TEmbalase.getText()) + Double.parseDouble(TTuslah.getText());
                            Sequel.queryu("delete from tampjurnal");
                            if (ttljual > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','" + ttljual + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','0','" + ttljual + "'", "Rekening");
                            }
                            if (ttlhpp > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
                            }
                            jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), "U", "PEMBERIAN OBAT RAWAT INAP PASIEN, DIPOSTING OLEH " + akses.getkode());
                        }

                        if (btnObat1.isEnabled() == true) {
                            Trackobat.catatRiwayat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                            Trackobat.catatRiwayatObat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", akses.getkode(), bangsal, "Simpan", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                            Sequel.menyimpan("gudangbarang", "'" + TKdOb.getText() + "','" + bangsal + "','-" + TJumlah.getText() + "'",
                                    "stok=stok-'" + TJumlah.getText() + "'", "kode_brng='" + TKdOb.getText() + "' and kd_bangsal='" + bangsal + "'");
                        }
                    }
                    Sequel.AutoComitTrue();
                    tampilPO();
                    BtnBatalActionPerformed(evt);
//                    }
                }

            }

        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TEmbalase, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        TKdOb.setText("");
        TNmOb.setText("");
        TJumlah.setText("0");
        TTotal.setText("0");
        TEmbalase.setText("0");
        TTuslah.setText("0");
        TBiayaObat.setText("0");
        THBeli.setText("0");
        TNoRw.requestFocus();
        DTPBeri.setDate(new Date());
        TNoRw.requestFocus();
        ChkInput.setSelected(true);
        ChkJln.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
//        if(Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")){
//            bangsal=Sequel.cariIsi("select kamar.kd_bangsal from kamar inner join kamar_inap "+
//                    "on kamar.kd_kamar=kamar_inap.kd_kamar where kamar_inap.no_rawat=? "+
//                    "order by tgl_masuk desc limit 1",TNoRw.getText());
//            if(bangsal.equals("")){
//                bangsal=Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1");
//            }
//        }else{
//            bangsal=Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1");
//        }

        if (tabModePO.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada table untuk memilih...!!!!");
        } else if (ChkJln.isSelected() == true) {
            JOptionPane.showMessageDialog(rootPane, "Matikan dulu jam otomatis sebelum menghapus data..!!");
            ChkJln.requestFocus();
        } else if (!(TPasien.getText().trim().equals(""))) {
            try {
                isSetBangsal();
                ChkJln.setSelected(true);
                if (akses.getkode().equals("Admin Utama")) {
                    Sequel.AutoComitFalse();
                    statusberi = Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                            + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
                    ttlhpp = Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                            + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
                    ttljual = Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                            + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
                    if (Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                            + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'") == true) {
                        if (statusberi.equals("Ranap")) {
                            Sequel.queryu("delete from tampjurnal");
                            if (ttljual > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','0','" + ttljual + "'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','" + ttljual + "','0'", "Rekening");
                            }
                            if (ttlhpp > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
                            }
                            jur.simpanJurnal(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString(), tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString(), "U", "PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH " + akses.getkode());
                        }

                        Sequel.queryu("delete from aturan_pakai where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
                        if (btnObat1.isEnabled() == true) {
                            Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString(),
                                    Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString()),
                                    0, "Pemberian Obat", akses.getkode(), bangsal, "Hapus", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                            Trackobat.catatRiwayatObat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString(), Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString()), 0, "Pemberian Obat", akses.getkode(), bangsal, "Hapus", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                            Sequel.menyimpan("gudangbarang", "'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "','" + bangsal + "',"
                                    + "'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString() + "'",
                                    "stok=stok+'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString() + "'",
                                    "kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' and kd_bangsal='" + bangsal + "'");
                        }
                    }

                    Sequel.AutoComitTrue();
                    tampilPO();
                    BtnBatalActionPerformed(evt);
                } else {
                    cekDataObat = 0;
                    cariDetailObat = "select count(1) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                            + "and stts_bayar = 'Belum' and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'";

                    if (Sequel.cariIsiAngka(cariDetailObat) == 0) {
                        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                        TCari.requestFocus();
                    } else {
                        Sequel.AutoComitFalse();
                        statusberi = Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
                        ttlhpp = Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
                        ttljual = Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
                        if (Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'") == true) {
                            if (statusberi.equals("Ranap")) {
                                Sequel.queryu("delete from tampjurnal");
                                if (ttljual > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','0','" + ttljual + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','" + ttljual + "','0'", "Rekening");
                                }
                                if (ttlhpp > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
                                }
                                jur.simpanJurnal(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString(), tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString(), "U", "PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH " + akses.getkode());
                            }

                            Sequel.queryu("delete from aturan_pakai where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
                                    + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
                                    + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
                                    + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
                            if (btnObat1.isEnabled() == true) {
                                Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString(),
                                        Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString()),
                                        0, "Pemberian Obat", akses.getkode(), bangsal, "Hapus", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                Trackobat.catatRiwayatObat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString(), Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString()), 0, "Pemberian Obat", akses.getkode(), bangsal, "Hapus", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                Sequel.menyimpan("gudangbarang", "'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "','" + bangsal + "',"
                                        + "'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString() + "'",
                                        "stok=stok+'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString() + "'",
                                        "kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' and kd_bangsal='" + bangsal + "'");
                            }
                        }

                        Sequel.AutoComitTrue();
                        tampilPO();
                        BtnBatalActionPerformed(evt);
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...  Klik data pada table untuk memilih data...!!!!");
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampilPO();
        if (tabModePO.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabModePO.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            if (!TCariPasien.getText().equals("")) {
                pas = " and reg_periksa.no_rkm_medis='" + TCariPasien.getText() + "' ";
            }
            tgl = " detail_pemberian_obat.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
            Valid.MyReport("rptBrObt.jasper", "report", "::[ Rekam Data Pemberian Obat (UMUM) ]::", "select detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,"
                    + "detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "detail_pemberian_obat.kode_brng,databarang.nama_brng,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,"
                    + "detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total "
                    + "from detail_pemberian_obat inner join reg_periksa inner join pasien inner join databarang "
                    + "on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and detail_pemberian_obat.kode_brng=databarang.kode_brng "
                    + "where  " + tgl + "and tgl_perawatan like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and detail_pemberian_obat.no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and detail_pemberian_obat.kode_brng like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and databarang.nama_brng like '%" + TCari.getText().trim() + "%' "
                    + "order by detail_pemberian_obat.tgl_perawatan", param);
        }
        this.setCursor(Cursor.getDefaultCursor());

}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TCariPasien.setText("");
        tampilPO();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TCariPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilPO();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSeek4.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            DTPCari2.requestFocus();
        }
    }//GEN-LAST:event_TCariPasienKeyPressed

    private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        akses.setform("DlgPemberianObat");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnSeek4ActionPerformed

    private void BtnSeek4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek4KeyPressed
        Valid.pindah(evt, TCariPasien, DTPCari1);
    }//GEN-LAST:event_BtnSeek4KeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPBeri, cmbMnt);
}//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
}//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, TKdOb);
}//GEN-LAST:event_cmbDtkKeyPressed

    private void tbPemberianObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemberianObatMouseClicked
        if (tabModePO.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPemberianObatMouseClicked

    private void tbPemberianObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemberianObatKeyPressed
        if (tabModePO.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPemberianObatKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void btnKonversiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonversiActionPerformed
    DlgCariKonversi carikonversi = new DlgCariKonversi(null, false);
    carikonversi.setLocationRelativeTo(internalFrame1);
    carikonversi.setVisible(true);
}//GEN-LAST:event_btnKonversiActionPerformed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
//        if(Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")){
//            bangsal=Sequel.cariIsi("select kamar.kd_bangsal from kamar inner join kamar_inap "+
//                    "on kamar.kd_kamar=kamar_inap.kd_kamar where kamar_inap.no_rawat=? "+
//                    "order by tgl_masuk desc limit 1",TNoRw.getText());
//            if(bangsal.equals("")){
//                bangsal=Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1");
//            }
//        }else{
//            bangsal=Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1");
//        }

//    if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
//        Valid.textKosong(TNoRw, "No.Rawat");
//    } else if (TKdOb.getText().trim().equals("") || TNmOb.getText().trim().equals("")) {
//        Valid.textKosong(TKdOb, "databarang penyakit");
//    } else if (bangsal.equals("")) {
//        Valid.textKosong(TCari, "Lokasi");
//    } else if (TJumlah.getText().trim().equals("")) {
//        Valid.textKosong(TJumlah, "Jumlah Obat");
//    } else if (TEmbalase.getText().trim().equals("")) {
//        Valid.textKosong(TEmbalase, "Embalase");
//    } else if (TTuslah.getText().trim().equals("")) {
//        Valid.textKosong(TEmbalase, "Tuslah");
//    } else if (ChkJln.isSelected() == true) {
//        JOptionPane.showMessageDialog(rootPane, "Matikan dulu jam otomatis sebelum mengedit data..!!");
//        ChkJln.requestFocus();
//    } else {
//        isSetBangsal();
//        isStok(TKdOb.getText());
//        stokbarang2 = Double.parseDouble(TStok.getText());
//        if (stokbarang2 < Double.parseDouble(TJumlah.getText()) || TStok.getText().equals("")) {
//            JOptionPane.showMessageDialog(rootPane, "Maaf stok tidak mencukupi..!!");
//        } else {
//            if (var.getkode().equals("Admin Utama")) {
//                try {
//                    Sequel.AutoComitFalse();
//                    statusberi = Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                            + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
//                    ttlhpp = Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                            + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
//                    ttljual = Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                            + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
//
//                    if (Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                            + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                            + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                            + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'") == true) {
//                        if (statusberi.equals("Ranap")) {
//                            Sequel.queryu("delete from tampjurnal");
//                            if (ttljual > 0) {
//                                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','0','" + ttljual + "'", "Rekening");
//                                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','" + ttljual + "','0'", "Rekening");
//                            }
//                            if (ttlhpp > 0) {
//                                Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
//                                Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
//                            }
//                            jur.simpanJurnal(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString(), tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString(), "U", "PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH " + var.getkode());
//                        }
//
//                        Sequel.queryu("delete from aturan_pakai where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
//                        if (btnObat1.isEnabled() == true) {
//                            Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString(),
//                                    Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString()),
//                                    0, "Pemberian Obat", var.getkode(), bangsal, "Hapus", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
//                            Trackobat.catatRiwayatObat(TKdOb.getText(), Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString()), 0, "Pemberian Obat", var.getkode(), bangsal, "Ganti Hapus", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
//                            Sequel.menyimpan("gudangbarang", "'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "','" + bangsal + "',"
//                                    + "'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString() + "'",
//                                    "stok=stok+'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString() + "'",
//                                    "kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' and kd_bangsal='" + bangsal + "'");
//                        }
//                    }
//
//                    ttlhpp = 0;
//                    ttljual = 0;
//                    if (Sequel.menyimpantf("detail_pemberian_obat", "'" + Valid.SetTgl(DTPBeri.getSelectedItem() + "") + "','"
//                            + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "','"
//                            + TNoRw.getText() + "','" + TKdOb.getText() + "','" + THBeli.getText() + "','" + TBiayaObat.getText() + "','"
//                            + TJumlah.getText() + "','" + TEmbalase.getText() + "','" + TTuslah.getText() + "',((" + TBiayaObat.getText() + "*" + TJumlah.getText() + ")+" + TEmbalase.getText() + "+" + TTuslah.getText() + "),'" + statussimpan + "','" + bangsal + "','Belum','-'", "data") == true) {
//                        if (statussimpan.equals("Ranap")) {
//                            ttlhpp = Double.parseDouble(THBeli.getText()) * Double.parseDouble(TJumlah.getText());
//                            ttljual = (Double.parseDouble(TBiayaObat.getText()) * Double.parseDouble(TJumlah.getText())) + Double.parseDouble(TEmbalase.getText()) + Double.parseDouble(TTuslah.getText());
//                            Sequel.queryu("delete from tampjurnal");
//                            if (ttljual > 0) {
//                                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','" + ttljual + "','0'", "Rekening");
//                                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','0','" + ttljual + "'", "Rekening");
//                            }
//                            if (ttlhpp > 0) {
//                                Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
//                                Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
//                            }
//                            jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), "U", "PEMBERIAN OBAT RAWAT INAP PASIEN, DIPOSTING OLEH " + var.getkode());
//                        }
//
//                        if (btnObat1.isEnabled() == true) {
//                            Trackobat.catatRiwayat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", var.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
//                            Trackobat.catatRiwayatObat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", var.getkode(), bangsal, "Ganti Simpan", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
//                            Sequel.menyimpan("gudangbarang", "'" + TKdOb.getText() + "','" + bangsal + "','-" + TJumlah.getText() + "'",
//                                    "stok=stok-'" + TJumlah.getText() + "'", "kode_brng='" + TKdOb.getText() + "' and kd_bangsal='" + bangsal + "'");
//                        }
//                    }
//                    Sequel.AutoComitTrue();
//                    tampilPO();
//                    BtnBatalActionPerformed(evt);
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya...!");
//                }
//            } else {
////                if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
////                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
////                    TCari.requestFocus();
////                } else {
//                    try {
//                        Sequel.AutoComitFalse();
//                        statusberi = Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
//                        ttlhpp = Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
//                        ttljual = Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
//
//                        if (Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                                + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                                + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'") == true) {
//                            if (statusberi.equals("Ranap")) {
//                                Sequel.queryu("delete from tampjurnal");
//                                if (ttljual > 0) {
//                                    Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','0','" + ttljual + "'", "Rekening");
//                                    Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','" + ttljual + "','0'", "Rekening");
//                                }
//                                if (ttlhpp > 0) {
//                                    Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
//                                    Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
//                                }
//                                jur.simpanJurnal(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString(), tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString(), "U", "PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH " + var.getkode());
//                            }
//
//                            Sequel.queryu("delete from aturan_pakai where no_rawat='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString() + "' "
//                                    + "and kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' "
//                                    + "and tgl_perawatan='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString() + "' "
//                                    + "and jam='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString() + "'");
//                            if (btnObat1.isEnabled() == true) {
//                                Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString(),
//                                        Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString()),
//                                        0, "Pemberian Obat", var.getkode(), bangsal, "Hapus", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
//                                Trackobat.catatRiwayatObat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", var.getkode(), bangsal, "Ganti Hapus", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
//                                Sequel.menyimpan("gudangbarang", "'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "','" + bangsal + "',"
//                                        + "'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString() + "'",
//                                        "stok=stok+'" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString() + "'",
//                                        "kode_brng='" + tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString() + "' and kd_bangsal='" + bangsal + "'");
//                            }
//                        }
//
//                        ttlhpp = 0;
//                        ttljual = 0;
//                        if (Sequel.menyimpantf("detail_pemberian_obat", "'" + Valid.SetTgl(DTPBeri.getSelectedItem() + "") + "','"
//                                + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "','"
//                                + TNoRw.getText() + "','" + TKdOb.getText() + "','" + THBeli.getText() + "','" + TBiayaObat.getText() + "','"
//                                + TJumlah.getText() + "','" + TEmbalase.getText() + "','" + TTuslah.getText() + "',((" + TBiayaObat.getText() + "*" + TJumlah.getText() + ")+" + TEmbalase.getText() + "+" + TTuslah.getText() + "),'" + statussimpan + "','" + bangsal + "','Belum','-'", "data") == true) {
//                            if (statussimpan.equals("Ranap")) {
//                                ttlhpp = Double.parseDouble(THBeli.getText()) * Double.parseDouble(TJumlah.getText());
//                                ttljual = (Double.parseDouble(TBiayaObat.getText()) * Double.parseDouble(TJumlah.getText())) + Double.parseDouble(TEmbalase.getText()) + Double.parseDouble(TTuslah.getText());
//                                Sequel.queryu("delete from tampjurnal");
//                                if (ttljual > 0) {
//                                    Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','" + ttljual + "','0'", "Rekening");
//                                    Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','0','" + ttljual + "'", "Rekening");
//                                }
//                                if (ttlhpp > 0) {
//                                    Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
//                                    Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
//                                }
//                                jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), "U", "PEMBERIAN OBAT RAWAT INAP PASIEN, DIPOSTING OLEH " + var.getkode());
//                            }
//
//                            if (btnObat1.isEnabled() == true) {
//                                Trackobat.catatRiwayat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", var.getkode(), bangsal, "Simpan", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
//                                Trackobat.catatRiwayatObat(TKdOb.getText(), 0, Valid.SetAngka(TJumlah.getText()), "Pemberian Obat", var.getkode(), bangsal, "Ganti Simpan", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
//                                Sequel.menyimpan("gudangbarang", "'" + TKdOb.getText() + "','" + bangsal + "','-" + TJumlah.getText() + "'",
//                                        "stok=stok-'" + TJumlah.getText() + "'", "kode_brng='" + TKdOb.getText() + "' and kd_bangsal='" + bangsal + "'");
//                            }
//                        }
//                        Sequel.AutoComitTrue();
//                        tampilPO();
//                        BtnBatalActionPerformed(evt);
//                    } catch (Exception ex) {
//                        JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya...!");
//                    }
////                }
//            }
//
//        }
//
//    }
    JOptionPane.showMessageDialog(null, "Silakan Hapus dan Input Ulang Obat yang akan diganti !!!!");
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnEditActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnHapus, BtnPrint);
    }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnObat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObat2ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (akses.getkode().equals("Admin Utama")) {
                akses.setform("DlgPemberianObat");
                dlgobt2.setNoRm(TNoRw.getText(), DTPBeri.getDate());
                dlgobt2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                dlgobt2.setLocationRelativeTo(internalFrame1);
                dlgobt2.setVisible(true);
            } else {
                if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                    TCari.requestFocus();
                } else {
                    akses.setform("DlgPemberianObat");
                    dlgobt2.setNoRm(TNoRw.getText(), DTPBeri.getDate());
                    dlgobt2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobt2.setLocationRelativeTo(internalFrame1);
                    dlgobt2.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_BtnObat2ActionPerformed

    private void BtnObat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObat3ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            DlgCariObatPenyakit dlgobtpny = new DlgCariObatPenyakit(null, false);
            if (akses.getkode().equals("Admin Utama")) {
                if (status.equals("ralan")) {
                    akses.setform("DlgPemberianObat");
                    dlgobtpny.setNoRm(TNoRw.getText(), Sequel.cariIsi("select kd_penyakit from diagnosa_pasien where no_rawat=? order by prioritas limit 1", TNoRw.getText()));
                    dlgobtpny.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgobtpny.setLocationRelativeTo(internalFrame1);
                    dlgobtpny.setVisible(true);
                }
            } else {
                if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                    TCari.requestFocus();
                } else {
                    if (status.equals("ralan")) {
                        akses.setform("DlgPemberianObat");
                        dlgobtpny.setNoRm(TNoRw.getText(), Sequel.cariIsi("select kd_penyakit from diagnosa_pasien where no_rawat=? order by prioritas limit 1", TNoRw.getText()));
                        dlgobtpny.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        dlgobtpny.setLocationRelativeTo(internalFrame1);
                        dlgobtpny.setVisible(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_BtnObat3ActionPerformed

    private void TTuslahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTuslahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isjml();
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            isjml();
            TEmbalase.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isjml();
        }
    }//GEN-LAST:event_TTuslahKeyPressed

    private void ppResepObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppResepObatActionPerformed
        if (tabModePO.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, pilih dulu data yang mau dibuatkan Nomor Resep..!! Klik data pada table untuk memilih...!!!!");
        } else if (!(TPasien.getText().trim().equals(""))) {
            DlgResepObat resep = new DlgResepObat(null, false);
            resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            resep.setLocationRelativeTo(internalFrame1);
            resep.emptTeks();
            resep.isCek();
            resep.setNoRm(TNoRw.getText(), Valid.SetTgl2(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString()), Valid.SetTgl2(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString()), tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString().substring(0, 2), tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString().substring(3, 5), tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString().substring(6, 8));
            resep.tampil();
            resep.setVisible(true);
            resep.setStatus(status);
        }
    }//GEN-LAST:event_ppResepObatActionPerformed

    private void ppNoRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppNoRawatActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampilPO();
        if (tabModePO.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabModePO.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            if (!TCariPasien.getText().equals("")) {
                pas = " and reg_periksa.no_rkm_medis='" + TCariPasien.getText() + "' ";
            }
            tgl = " detail_pemberian_obat.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
            Valid.MyReport("rptBrObt2.jasper", "report", "::[ Rekam Data Pemberian Obat (UMUM) ]::", "select detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,"
                    + "detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "detail_pemberian_obat.kode_brng,databarang.nama_brng,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,"
                    + "detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total "
                    + "from detail_pemberian_obat inner join reg_periksa inner join pasien inner join databarang "
                    + "on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and detail_pemberian_obat.kode_brng=databarang.kode_brng "
                    + "where  " + tgl + "and tgl_perawatan like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and detail_pemberian_obat.no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and detail_pemberian_obat.kode_brng like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and databarang.nama_brng like '%" + TCari.getText().trim() + "%' "
                    + "order by detail_pemberian_obat.tgl_perawatan", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppNoRawatActionPerformed

    private void ppHapusSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusSemuaActionPerformed
        if (tabModePO.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (!(TPasien.getText().trim().equals(""))) {
            try {
                isSetBangsal();
                ChkJln.setSelected(true);
                for (i = 0; i < tbPemberianObat.getRowCount(); i++) {
                    if (akses.getkode().equals("Admin Utama")) {
                        Sequel.AutoComitFalse();
                        statusberi = Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'");
                        ttlhpp = Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'");
                        ttljual = Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'");
                        if (Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'") == true) {
                            if (statusberi.equals("Ranap")) {
                                Sequel.queryu("delete from tampjurnal");
                                if (ttljual > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','0','" + ttljual + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','" + ttljual + "','0'", "Rekening");
                                }
                                if (ttlhpp > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
                                }
                                jur.simpanJurnal(tbPemberianObat.getValueAt(i, 2).toString(), tbPemberianObat.getValueAt(i, 0).toString(), "U", "PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH " + akses.getkode());
                            }

                            Sequel.queryu("delete from aturan_pakai where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                    + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                    + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                    + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'");
                            if (btnObat1.isEnabled() == true) {
                                Trackobat.catatRiwayat(tbPemberianObat.getValueAt(i, 5).toString(),
                                        Valid.SetAngka(tbPemberianObat.getValueAt(i, 9).toString()),
                                        0, "Pemberian Obat", akses.getkode(), bangsal, "Hapus", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                Trackobat.catatRiwayatObat(tbPemberianObat.getValueAt(i, 5).toString(), Valid.SetAngka(tbPemberianObat.getValueAt(i, 9).toString()), 0, "Pemberian Obat", akses.getkode(), bangsal, "Hapus", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                Sequel.menyimpan("gudangbarang", "'" + tbPemberianObat.getValueAt(i, 5).toString() + "','" + bangsal + "',"
                                        + "'" + tbPemberianObat.getValueAt(i, 9).toString() + "'",
                                        "stok=stok+'" + tbPemberianObat.getValueAt(i, 9).toString() + "'",
                                        "kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' and kd_bangsal='" + bangsal + "'");
                            }
                        }

                        //Sequel.AutoComitTrue();
                        //tampilPO();
                        //BtnBatalActionPerformed(evt);
                    } else {
                        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        } else {
                            Sequel.AutoComitFalse();
                            statusberi = Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                    + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                    + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                    + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'");
                            ttlhpp = Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                    + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                    + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                    + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'");
                            ttljual = Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                    + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                    + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                    + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'");
                            if (Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                    + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                    + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                    + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'") == true) {
                                if (statusberi.equals("Ranap")) {
                                    Sequel.queryu("delete from tampjurnal");
                                    if (ttljual > 0) {
                                        Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Obat_Ranap + "','Suspen Piutang Obat Ranap','0','" + ttljual + "'", "Rekening");
                                        Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Pendapatan Obat Rawat Inap','" + ttljual + "','0'", "Rekening");
                                    }
                                    if (ttlhpp > 0) {
                                        Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Inap + "','HPP Persediaan Obat Rawat Inap','0','" + ttlhpp + "'", "Rekening");
                                        Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Inap + "','Persediaan Obat Rawat Inap','" + ttlhpp + "','0'", "Rekening");
                                    }
                                    jur.simpanJurnal(tbPemberianObat.getValueAt(i, 2).toString(), tbPemberianObat.getValueAt(i, 0).toString(), "U", "PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH " + akses.getkode());
                                }

                                Sequel.queryu("delete from aturan_pakai where no_rawat='" + tbPemberianObat.getValueAt(i, 2).toString() + "' "
                                        + "and kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' "
                                        + "and tgl_perawatan='" + tbPemberianObat.getValueAt(i, 0).toString() + "' "
                                        + "and jam='" + tbPemberianObat.getValueAt(i, 1).toString() + "'");
                                if (btnObat1.isEnabled() == true) {
                                    Trackobat.catatRiwayat(tbPemberianObat.getValueAt(i, 5).toString(),
                                            Valid.SetAngka(tbPemberianObat.getValueAt(i, 9).toString()),
                                            0, "Pemberian Obat", akses.getkode(), bangsal, "Hapus", Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                    Trackobat.catatRiwayatObat(tbPemberianObat.getValueAt(i, 5).toString(), Valid.SetAngka(tbPemberianObat.getValueAt(i, 9).toString()), 0, "Pemberian Obat", akses.getkode(), bangsal, "Hapus", TNoRM.getText(), TNoRw.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
                                    Sequel.menyimpan("gudangbarang", "'" + tbPemberianObat.getValueAt(i, 5).toString() + "','" + bangsal + "',"
                                            + "'" + tbPemberianObat.getValueAt(i, 9).toString() + "'",
                                            "stok=stok+'" + tbPemberianObat.getValueAt(i, 9).toString() + "'",
                                            "kode_brng='" + tbPemberianObat.getValueAt(i, 5).toString() + "' and kd_bangsal='" + bangsal + "'");
                                }
                            }

                            //Sequel.AutoComitTrue();
                            //tampilPO();
                            //BtnBatalActionPerformed(evt);
                        }
                    }

                }

                Sequel.AutoComitTrue();
                tampilPO();
                BtnBatalActionPerformed(evt);

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...  Klik data pada table untuk memilih data...!!!!");
            }
        }
    }//GEN-LAST:event_ppHapusSemuaActionPerformed

    private void cmbJamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseClicked
        cmbJam.setEditable(false);
    }//GEN-LAST:event_cmbJamMouseClicked

    private void cmbMntMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseClicked
        cmbMnt.setEditable(false);
    }//GEN-LAST:event_cmbMntMouseClicked

    private void cmbDtkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseClicked
        cmbDtk.setEditable(false);
    }//GEN-LAST:event_cmbDtkMouseClicked

    private void ppLaporanFarmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLaporanFarmasiActionPerformed
        DlgLaporanFarmasi laporan = new DlgLaporanFarmasi(null, false);
        laporan.setSize(986, 441);
        laporan.setLocationRelativeTo(internalFrame1);
        laporan.setVisible(true);
        laporan.tampil();
        laporan.ChkInput.setSelected(true);
        laporan.isForm();
    }//GEN-LAST:event_ppLaporanFarmasiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPemberianObat dialog = new DlgPemberianObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnObat2;
    private widget.Button BtnObat3;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPBeri;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup2;
    private widget.ScrollPane Scroll;
    private widget.TextBox TBiayaObat;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextBox TEmbalase;
    private widget.TextBox THBeli;
    private widget.TextBox TJumlah;
    private widget.TextBox TKdOb;
    private widget.TextBox TNmOb;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TSatuan;
    private widget.TextBox TStok;
    private widget.TextBox TTotal;
    private widget.TextBox TTuslah;
    private widget.Button btnKonversi;
    private widget.Button btnObat1;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator5;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppHapusSemua;
    private javax.swing.JMenuItem ppLaporanFarmasi;
    private javax.swing.JMenuItem ppNoRawat;
    private javax.swing.JMenuItem ppResepObat;
    private widget.Table tbPemberianObat;
    // End of variables declaration//GEN-END:variables

    public void tampilPO() {
        pas = "";
        if (!TCariPasien.getText().equals("")) {
            pas = " and reg_periksa.no_rkm_medis='" + TCariPasien.getText() + "' ";
        }
        tgl = " detail_pemberian_obat.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
        sql = "select detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,"
                + "detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                + "detail_pemberian_obat.kode_brng,databarang.nama_brng,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,"
                + "detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total,detail_pemberian_obat.h_beli,bangsal.nm_bangsal,detail_pemberian_obat.urutan  "
                + "from detail_pemberian_obat inner join reg_periksa inner join pasien inner join databarang "
                + "on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                + "and detail_pemberian_obat.kode_brng=databarang.kode_brng "
                + "left JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + "AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan and riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + "AND Mid(riwayat_obat_pasien.jam,1,5) = Mid(detail_pemberian_obat.jam,1,5) and riwayat_obat_pasien.status = 'simpan' "
                + "left join bangsal on bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + "where " + tgl + " and tgl_perawatan like ? or "
                + tgl + "and detail_pemberian_obat.no_rawat like ? or "
                + tgl + "and reg_periksa.no_rkm_medis like ? or "
                + tgl + "and pasien.nm_pasien like ? or "
                + tgl + "and detail_pemberian_obat.kode_brng like ? or "
                + tgl + "and databarang.nama_brng like ? or "
                + tgl + "and riwayat_obat_pasien.kd_bangsal like ? or "
                + tgl + "and bangsal.nm_bangsal like ? "
                + " union all "
                + "select p.tgl_jual tgl_perawatan,'00:00:00' jam,'Penjualan Langsung' no_rawat,p.no_rkm_medis, p.nm_pasien,j.kode_brng, d.nama_brng, "
                + "0 embalase, j.tambahan tuslah,j.jumlah jml,j.h_jual biaya_obat, j.total, j.h_beli, b.nm_bangsal,'' as urutan "
                + "from penjualan p "
                + "inner join detailjual j on j.nota_jual = p.nota_jual "
                + "inner join databarang d on d.kode_brng = j.kode_brng "
                + "inner join bangsal b on b.kd_bangsal = p.kd_bangsal "
                + "where p.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and d.nama_brng like ? or "
                + "p.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and p.no_rkm_medis like ? or "
                + "p.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and p.nm_pasien like ? or "
                + "p.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and d.nama_brng like ? or "
                + "p.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and b.nm_bangsal like ? "
                + "order by tgl_perawatan,jam,urutan";

//        sql = "select detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,"
//                + "detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
//                + "detail_pemberian_obat.kode_brng,databarang.nama_brng,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,"
//                + "detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total,detail_pemberian_obat.h_beli "
//                + "from detail_pemberian_obat inner join reg_periksa inner join pasien inner join databarang "
//                + "on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
//                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
//                + "and detail_pemberian_obat.kode_brng=databarang.kode_brng "
//                + "where " + tgl + " and tgl_perawatan like ? or "
//                + tgl + "and detail_pemberian_obat.no_rawat like ? or "
//                + tgl + "and reg_periksa.no_rkm_medis like ? or "
//                + tgl + "and pasien.nm_pasien like ? or "
//                + tgl + "and detail_pemberian_obat.kode_brng like ? or "
//                + tgl + "and databarang.nama_brng like ? "
//                + "order by detail_pemberian_obat.tgl_perawatan";
        Valid.tabelKosong(tabModePO);
        try {
            ps = koneksi.prepareStatement(sql);
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
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] data = {rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getDouble(8),
                    rs.getDouble(9),
                    rs.getDouble(10),
                    rs.getDouble(11),
                    rs.getDouble(12),
                    rs.getDouble(13),
                    rs.getString(14)};
                tabModePO.addRow(data);
            }
//            ps = koneksi.prepareStatement(sql);
//            ps.setString(1, "%" + TCari.getText().trim() + "%");
//            ps.setString(2, "%" + TCari.getText().trim() + "%");
//            ps.setString(3, "%" + TCari.getText().trim() + "%");
//            ps.setString(4, "%" + TCari.getText().trim() + "%");
//            ps.setString(5, "%" + TCari.getText().trim() + "%");
//            ps.setString(6, "%" + TCari.getText().trim() + "%");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Object[] data = {rs.getString(1),
//                    rs.getString(2),
//                    rs.getString(3),
//                    rs.getString(4),
//                    rs.getString(5),
//                    rs.getString(6),
//                    rs.getString(7),
//                    rs.getDouble(8),
//                    rs.getDouble(9),
//                    rs.getDouble(10),
//                    rs.getDouble(11),
//                    rs.getDouble(12),
//                    rs.getDouble(13)};
//                tabModePO.addRow(data);
//            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModePO.getRowCount());
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
    }

    private void getData() {
        if (tbPemberianObat.getSelectedRow() != -1) {
            cmbJam.setSelectedItem(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 1).toString().substring(6, 8));
            TNoRw.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            TKdOb.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 5).toString());
            TNmOb.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 6).toString());
            TEmbalase.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 7).toString());
            TTuslah.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 8).toString());
            TJumlah.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 9).toString());
            TBiayaObat.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 10).toString());
            THBeli.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 12).toString());
            Valid.SetTgl(DTPBeri, tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(), 0).toString());
            isjml();
        }
    }

    public void setNoRm(String norwt, Date tgl1, Date tgl2, String statuspasien) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        isRawat();
        isPsien();
        status = statuspasien;
        if (statuspasien.equals("ranap")) {
            statussimpan = "Ranap";
            BtnObat2.setEnabled(true);
            BtnObat3.setEnabled(false);
            if (Sequel.cariInteger("select count(no_rawat) from stok_obat_pasien where no_rawat=? ", norwt) > 0) {
                btnObat1.setEnabled(false);
            } else {
                btnObat1.setEnabled(true);
            }
        } else if (statuspasien.equals("ralan")) {
            statussimpan = "Ralan";
            btnObat1.setEnabled(true);
            BtnObat2.setEnabled(false);
            BtnObat3.setEnabled(true);
        }
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        ChkInput.setSelected(true);
        BtnBatalActionPerformed(null);
        isForm();
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 160));
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
//        BtnSimpan.setEnabled(akses.getberi_obat());
        BtnHapus.setEnabled(akses.getberi_obat());
        BtnEdit.setEnabled(akses.getberi_obat());
        BtnPrint.setEnabled(akses.getberi_obat());
        ppResepObat.setEnabled(akses.getresep_obat());
        ppLaporanFarmasi.setEnabled(akses.getlaporanfarmasi());
        ChkJln.setSelected(true);
    }

    private void isjml() {
        if ((!TBiayaObat.getText().equals("")) && (!TEmbalase.getText().equals("")) && (!TTuslah.getText().equals("")) && (!TJumlah.getText().equals(""))) {
            TTotal.setText(Double.toString((Double.parseDouble(TBiayaObat.getText()) * Double.parseDouble(TJumlah.getText())) + Double.parseDouble(TEmbalase.getText()) + Double.parseDouble(TTuslah.getText())));
        }
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

    public void setDokter(String kodedokter, String namadokter) {
//        this.kodedokter=kodedokter;
//        this.namadokter=namadokter;
    }

    private void isStok(String a) {
        Sequel.cariIsi("select ifnull(stok,'0') from gudangbarang where kd_bangsal=? and kode_brng=?", TStok, akses.getkdbangsal(), a);
    }

    private void isSetBangsal() {
        if (LocalTime.now().isAfter(LocalTime.parse("08:00:00")) && LocalTime.now().isBefore(LocalTime.parse("21:00:00"))) {
            //JOptionPane.showMessageDialog(rootPane, "Apotek Sentral");
            bangsal = Sequel.cariApotek();
        } else {
            //JOptionPane.showMessageDialog(rootPane, "Apotek IGD");
            bangsal = Sequel.cariApotek2();
        }
        akses.setkdbangsal(bangsal);
    }

}
