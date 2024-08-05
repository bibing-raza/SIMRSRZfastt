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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgBayarPemesanan;
import keuangan.Jurnal;
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCariBangsal;

public class DlgCariPemesanan extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private riwayatobat Trackobat = new riwayatobat();
    private Connection koneksi = koneksiDB.condb();
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public DlgSuplier suplier = new DlgSuplier(null, false);
    public DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    public DlgBarang barang = new DlgBarang(null, false);
    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);
    private PreparedStatement ps, ps2, pscaripesan, psdetailpesan;
    private ResultSet rs, rs2;
    private double tagihan = 0;
    private Jurnal jur = new Jurnal();
    private String par, dialog_simpan = "", no_Faktur = "";
    private double stokbarang2;
    private Integer cek;

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgCariPemesanan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"Nama Suplier", "No.Faktur", "Tgl.Faktur", "Petugas", "Nama Barang/Obat",
            "Satuan", "Jml.Beli", "Harga Beli(Rp)", "SubTotal(Rp)",
            "Disk(%)", "Bsr.Disk(Rp)", "Total(Rp)"};

//        Object[] row={"Tgl.Faktur","No.Faktur","Suplier","Petugas","Barang",
//                    "Satuan","Jml.Beli","Harga Beli(Rp)","SubTotal(Rp)",
//                    "Disk(%)","Bsr.Disk(Rp)","Total(Rp)"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPemesanan.setModel(tabMode);

        tbPemesanan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPemesanan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbPemesanan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(180);
            } else if (i == 1) {
                column.setPreferredWidth(140);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(50);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(70);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            }
        }
        tbPemesanan.setDefaultRenderer(Object.class, new WarnaTable());

        NoFaktur.setDocument(new batasInput((byte) 25).getKata(NoFaktur));
        kdsup.setDocument(new batasInput((byte) 5).getKata(kdsup));
        kdptg.setDocument(new batasInput((byte) 25).getKata(kdptg));
        kdbar.setDocument(new batasInput((byte) 15).getKata(kdbar));
        kdsat.setDocument(new batasInput((byte) 3).getKata(kdsat));
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

        suplier.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPemesanan")) {
                    if (suplier.getTable().getSelectedRow() != -1) {
                        kdsup.setText(suplier.getTable().getValueAt(suplier.getTable().getSelectedRow(), 0).toString());
                        nmsup.setText(suplier.getTable().getValueAt(suplier.getTable().getSelectedRow(), 1).toString());
                    }
                    kdsup.requestFocus();
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

        suplier.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgCariPemesanan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        suplier.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        barang.jenis.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPemesanan")) {
                    if (barang.jenis.getTable().getSelectedRow() != -1) {
                        kdsat.setText(barang.jenis.getTable().getValueAt(barang.jenis.getTable().getSelectedRow(), 0).toString());
                        nmsat.setText(barang.jenis.getTable().getValueAt(barang.jenis.getTable().getSelectedRow(), 1).toString());
                    }
                    kdsat.requestFocus();
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

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPemesanan")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        kdptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                    kdptg.requestFocus();
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

        barang.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPemesanan")) {
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
                if (akses.getform().equals("DlgCariPemesanan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        barang.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        barang.industri.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPemesanan")) {
                    if (barang.industri.getTable().getSelectedRow() != -1) {
                        KdIF.setText(barang.industri.getTable().getValueAt(barang.industri.getTable().getSelectedRow(), 0).toString());
                        NmIF.setText(barang.industri.getTable().getValueAt(barang.industri.getTable().getSelectedRow(), 1).toString());
                    }
                    KdIF.requestFocus();
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

        barang.industri.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgCariPemesanan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        barang.industri.dispose();
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
                if (akses.getform().equals("DlgPemesanan")) {
                    if (bangsal.getTable().getSelectedRow() != -1) {
                        kdgudang.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 0).toString());
                        nmgudang.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 1).toString());
                    }
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
            pscaripesan = koneksi.prepareStatement("select no_faktur, tagihan, kd_bangsal,tgl_faktur,status from pemesanan where no_faktur=?");
            psdetailpesan = koneksi.prepareStatement("select kode_brng,jumlah2 from detailpesan where no_faktur=? ");
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        ppHapus = new javax.swing.JMenuItem();
        ppBayar = new javax.swing.JMenuItem();
        ppUbah = new javax.swing.JMenuItem();
        ppCetakPemesanan = new javax.swing.JMenuItem();
        ppCetakSuratPesanan = new javax.swing.JMenuItem();
        ppRekapPemesananExcel = new javax.swing.JMenuItem();
        ppRingkasanRekapPemesananExcel = new javax.swing.JMenuItem();
        ppInOutGudangExcel = new javax.swing.JMenuItem();
        ppCetakPemesananExcel = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbPemesanan = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label9 = new widget.Label();
        LTotal = new widget.Label();
        BtnAll = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi4 = new widget.panelisi();
        label17 = new widget.Label();
        kdbar = new widget.TextBox();
        nmbar = new widget.TextBox();
        btnBarang = new widget.Button();
        label24 = new widget.Label();
        kdsat = new widget.TextBox();
        btnSatuan = new widget.Button();
        nmsat = new widget.TextBox();
        panelisi3 = new widget.panelisi();
        label15 = new widget.Label();
        NoFaktur = new widget.TextBox();
        label11 = new widget.Label();
        TglBeli1 = new widget.Tanggal();
        label16 = new widget.Label();
        label13 = new widget.Label();
        kdsup = new widget.TextBox();
        kdptg = new widget.TextBox();
        nmsup = new widget.TextBox();
        nmptg = new widget.TextBox();
        btnSuplier = new widget.Button();
        btnPetugas = new widget.Button();
        label12 = new widget.Label();
        TglBeli2 = new widget.Tanggal();
        label14 = new widget.Label();
        KdIF = new widget.TextBox();
        NmIF = new widget.TextBox();
        btnIF = new widget.Button();
        label20 = new widget.Label();
        kdgudang = new widget.TextBox();
        nmgudang = new widget.TextBox();
        btnGudang = new widget.Button();
        chkFree = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppHapus.setText("Hapus Data Pemesanan");
        ppHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapus.setIconTextGap(8);
        ppHapus.setName("ppHapus"); // NOI18N
        ppHapus.setPreferredSize(new java.awt.Dimension(260, 25));
        ppHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppHapus);

        ppBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBayar.setText("Bayar Tagihan");
        ppBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBayar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBayar.setIconTextGap(8);
        ppBayar.setName("ppBayar"); // NOI18N
        ppBayar.setPreferredSize(new java.awt.Dimension(260, 25));
        ppBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBayarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppBayar);

        ppUbah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppUbah.setText("Ubah Pemesanan");
        ppUbah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppUbah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppUbah.setIconTextGap(8);
        ppUbah.setName("ppUbah"); // NOI18N
        ppUbah.setPreferredSize(new java.awt.Dimension(260, 25));
        ppUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppUbahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppUbah);

        ppCetakPemesanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCetakPemesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCetakPemesanan.setText("Cetak Rekap Pemesanan");
        ppCetakPemesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCetakPemesanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCetakPemesanan.setIconTextGap(8);
        ppCetakPemesanan.setName("ppCetakPemesanan"); // NOI18N
        ppCetakPemesanan.setPreferredSize(new java.awt.Dimension(260, 25));
        ppCetakPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCetakPemesananActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCetakPemesanan);

        ppCetakSuratPesanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCetakSuratPesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCetakSuratPesanan.setText("Cetak Surat Pesanan");
        ppCetakSuratPesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCetakSuratPesanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCetakSuratPesanan.setIconTextGap(8);
        ppCetakSuratPesanan.setName("ppCetakSuratPesanan"); // NOI18N
        ppCetakSuratPesanan.setPreferredSize(new java.awt.Dimension(260, 25));
        ppCetakSuratPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCetakSuratPesananActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCetakSuratPesanan);

        ppRekapPemesananExcel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRekapPemesananExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        ppRekapPemesananExcel.setText("Kirim Rekap Pemesanan Ke Excel");
        ppRekapPemesananExcel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRekapPemesananExcel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRekapPemesananExcel.setIconTextGap(8);
        ppRekapPemesananExcel.setName("ppRekapPemesananExcel"); // NOI18N
        ppRekapPemesananExcel.setPreferredSize(new java.awt.Dimension(260, 25));
        ppRekapPemesananExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRekapPemesananExcelActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRekapPemesananExcel);

        ppRingkasanRekapPemesananExcel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRingkasanRekapPemesananExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        ppRingkasanRekapPemesananExcel.setText("Kirim Ringkasan Rekap Pemesanan Ke Excel");
        ppRingkasanRekapPemesananExcel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRingkasanRekapPemesananExcel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRingkasanRekapPemesananExcel.setIconTextGap(8);
        ppRingkasanRekapPemesananExcel.setName("ppRingkasanRekapPemesananExcel"); // NOI18N
        ppRingkasanRekapPemesananExcel.setPreferredSize(new java.awt.Dimension(260, 25));
        ppRingkasanRekapPemesananExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRingkasanRekapPemesananExcelActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRingkasanRekapPemesananExcel);

        ppInOutGudangExcel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppInOutGudangExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        ppInOutGudangExcel.setText("Kirim Rekap In Out Gudang Ke Excel");
        ppInOutGudangExcel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppInOutGudangExcel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppInOutGudangExcel.setIconTextGap(8);
        ppInOutGudangExcel.setName("ppInOutGudangExcel"); // NOI18N
        ppInOutGudangExcel.setPreferredSize(new java.awt.Dimension(260, 25));
        ppInOutGudangExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppInOutGudangExcelActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppInOutGudangExcel);

        ppCetakPemesananExcel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCetakPemesananExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        ppCetakPemesananExcel.setText("Cetak Rekap Pemesanan Ke Excel");
        ppCetakPemesananExcel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCetakPemesananExcel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCetakPemesananExcel.setIconTextGap(8);
        ppCetakPemesananExcel.setName("ppCetakPemesananExcel"); // NOI18N
        ppCetakPemesananExcel.setPreferredSize(new java.awt.Dimension(260, 25));
        ppCetakPemesananExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCetakPemesananExcelActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCetakPemesananExcel);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Cari Pemesanan Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setComponentPopupMenu(jPopupMenu1);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPemesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPemesanan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemesanan.setComponentPopupMenu(jPopupMenu1);
        tbPemesanan.setName("tbPemesanan"); // NOI18N
        tbPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemesananMouseClicked(evt);
            }
        });
        tbPemesanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemesananKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbPemesanan);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

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
        TCari.setPreferredSize(new java.awt.Dimension(170, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+5");
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

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Total :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(45, 30));
        panelisi1.add(label9);

        LTotal.setForeground(new java.awt.Color(0, 0, 0));
        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(120, 30));
        panelisi1.add(LTotal);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(130, 30));
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

        jPanel1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(null);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Barang :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(label17);
        label17.setBounds(295, 10, 90, 23);

        kdbar.setForeground(new java.awt.Color(0, 0, 0));
        kdbar.setName("kdbar"); // NOI18N
        kdbar.setPreferredSize(new java.awt.Dimension(80, 23));
        kdbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbarKeyPressed(evt);
            }
        });
        panelisi4.add(kdbar);
        kdbar.setBounds(389, 10, 110, 23);

        nmbar.setEditable(false);
        nmbar.setForeground(new java.awt.Color(0, 0, 0));
        nmbar.setName("nmbar"); // NOI18N
        nmbar.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi4.add(nmbar);
        nmbar.setBounds(501, 10, 230, 23);

        btnBarang.setForeground(new java.awt.Color(0, 0, 0));
        btnBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBarang.setMnemonic('4');
        btnBarang.setToolTipText("Alt+4");
        btnBarang.setName("btnBarang"); // NOI18N
        btnBarang.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });
        panelisi4.add(btnBarang);
        btnBarang.setBounds(734, 10, 28, 23);

        label24.setForeground(new java.awt.Color(0, 0, 0));
        label24.setText("Jenis :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi4.add(label24);
        label24.setBounds(0, 10, 75, 23);

        kdsat.setForeground(new java.awt.Color(0, 0, 0));
        kdsat.setName("kdsat"); // NOI18N
        kdsat.setPreferredSize(new java.awt.Dimension(80, 23));
        kdsat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdsatKeyPressed(evt);
            }
        });
        panelisi4.add(kdsat);
        kdsat.setBounds(80, 10, 53, 23);

        btnSatuan.setForeground(new java.awt.Color(0, 0, 0));
        btnSatuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnSatuan.setMnemonic('3');
        btnSatuan.setToolTipText("Alt+3");
        btnSatuan.setName("btnSatuan"); // NOI18N
        btnSatuan.setPreferredSize(new java.awt.Dimension(28, 23));
        btnSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSatuanActionPerformed(evt);
            }
        });
        panelisi4.add(btnSatuan);
        btnSatuan.setBounds(253, 10, 28, 23);

        nmsat.setForeground(new java.awt.Color(0, 0, 0));
        nmsat.setName("nmsat"); // NOI18N
        nmsat.setPreferredSize(new java.awt.Dimension(80, 23));
        nmsat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmsatKeyPressed(evt);
            }
        });
        panelisi4.add(nmsat);
        nmsat.setBounds(135, 10, 115, 23);

        jPanel1.add(panelisi4, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 130));
        panelisi3.setLayout(null);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("No.Faktur :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label15);
        label15.setBounds(0, 10, 80, 23);

        NoFaktur.setForeground(new java.awt.Color(0, 0, 0));
        NoFaktur.setName("NoFaktur"); // NOI18N
        NoFaktur.setPreferredSize(new java.awt.Dimension(207, 23));
        NoFaktur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoFakturKeyPressed(evt);
            }
        });
        panelisi3.add(NoFaktur);
        NoFaktur.setBounds(84, 10, 219, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl.Datang :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label11);
        label11.setBounds(0, 40, 80, 23);

        TglBeli1.setDisplayFormat("dd-MM-yyyy");
        TglBeli1.setName("TglBeli1"); // NOI18N
        TglBeli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglBeli1KeyPressed(evt);
            }
        });
        panelisi3.add(TglBeli1);
        TglBeli1.setBounds(84, 40, 95, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Supplier :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label16);
        label16.setBounds(305, 10, 80, 23);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setText("Petugas :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label13);
        label13.setBounds(305, 40, 80, 23);

        kdsup.setForeground(new java.awt.Color(0, 0, 0));
        kdsup.setName("kdsup"); // NOI18N
        kdsup.setPreferredSize(new java.awt.Dimension(80, 23));
        kdsup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdsupKeyPressed(evt);
            }
        });
        panelisi3.add(kdsup);
        kdsup.setBounds(389, 10, 80, 23);

        kdptg.setEditable(false);
        kdptg.setForeground(new java.awt.Color(0, 0, 0));
        kdptg.setName("kdptg"); // NOI18N
        kdptg.setPreferredSize(new java.awt.Dimension(80, 23));
        kdptg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptgKeyPressed(evt);
            }
        });
        panelisi3.add(kdptg);
        kdptg.setBounds(389, 40, 80, 23);

        nmsup.setEditable(false);
        nmsup.setForeground(new java.awt.Color(0, 0, 0));
        nmsup.setName("nmsup"); // NOI18N
        nmsup.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmsup);
        nmsup.setBounds(471, 10, 260, 23);

        nmptg.setEditable(false);
        nmptg.setForeground(new java.awt.Color(0, 0, 0));
        nmptg.setName("nmptg"); // NOI18N
        nmptg.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmptg);
        nmptg.setBounds(471, 40, 260, 23);

        btnSuplier.setForeground(new java.awt.Color(0, 0, 0));
        btnSuplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnSuplier.setMnemonic('1');
        btnSuplier.setToolTipText("Alt+1");
        btnSuplier.setName("btnSuplier"); // NOI18N
        btnSuplier.setPreferredSize(new java.awt.Dimension(28, 23));
        btnSuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuplierActionPerformed(evt);
            }
        });
        panelisi3.add(btnSuplier);
        btnSuplier.setBounds(734, 10, 28, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("Alt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        panelisi3.add(btnPetugas);
        btnPetugas.setBounds(734, 40, 28, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label12.setText("s.d.");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label12);
        label12.setBounds(179, 40, 27, 23);

        TglBeli2.setDisplayFormat("dd-MM-yyyy");
        TglBeli2.setName("TglBeli2"); // NOI18N
        TglBeli2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglBeli2KeyPressed(evt);
            }
        });
        panelisi3.add(TglBeli2);
        TglBeli2.setBounds(208, 40, 95, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Ind. Farmasi :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label14);
        label14.setBounds(265, 70, 120, 23);

        KdIF.setEditable(false);
        KdIF.setForeground(new java.awt.Color(0, 0, 0));
        KdIF.setName("KdIF"); // NOI18N
        KdIF.setPreferredSize(new java.awt.Dimension(80, 23));
        KdIF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdIFKeyPressed(evt);
            }
        });
        panelisi3.add(KdIF);
        KdIF.setBounds(389, 70, 80, 23);

        NmIF.setEditable(false);
        NmIF.setForeground(new java.awt.Color(0, 0, 0));
        NmIF.setName("NmIF"); // NOI18N
        NmIF.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(NmIF);
        NmIF.setBounds(471, 70, 260, 23);

        btnIF.setForeground(new java.awt.Color(0, 0, 0));
        btnIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnIF.setMnemonic('2');
        btnIF.setToolTipText("Alt+2");
        btnIF.setName("btnIF"); // NOI18N
        btnIF.setPreferredSize(new java.awt.Dimension(28, 23));
        btnIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIFActionPerformed(evt);
            }
        });
        panelisi3.add(btnIF);
        btnIF.setBounds(734, 70, 28, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Lokasi :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label20);
        label20.setBounds(305, 100, 80, 23);

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
        kdgudang.setBounds(389, 100, 80, 23);

        nmgudang.setEditable(false);
        nmgudang.setForeground(new java.awt.Color(0, 0, 0));
        nmgudang.setName("nmgudang"); // NOI18N
        nmgudang.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmgudang);
        nmgudang.setBounds(471, 100, 260, 23);

        btnGudang.setForeground(new java.awt.Color(0, 0, 0));
        btnGudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnGudang.setMnemonic('2');
        btnGudang.setToolTipText("Alt+2");
        btnGudang.setName("btnGudang"); // NOI18N
        btnGudang.setPreferredSize(new java.awt.Dimension(28, 23));
        btnGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGudangActionPerformed(evt);
            }
        });
        panelisi3.add(btnGudang);
        btnGudang.setBounds(734, 100, 28, 23);

        chkFree.setForeground(new java.awt.Color(0, 0, 0));
        chkFree.setText("Lokasi Dipilih");
        chkFree.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFree.setName("chkFree"); // NOI18N
        chkFree.setOpaque(false);
        chkFree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFreeActionPerformed(evt);
            }
        });
        panelisi3.add(chkFree);
        chkFree.setBounds(770, 100, 100, 23);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, kdbar);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed
    /*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void btnSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuplierActionPerformed
        akses.setform("DlgCariPemesanan");
        suplier.emptTeks();
        suplier.isCek();
        suplier.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        suplier.setLocationRelativeTo(internalFrame1);
        suplier.setAlwaysOnTop(false);
        suplier.setVisible(true);
    }//GEN-LAST:event_btnSuplierActionPerformed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgCariPemesanan");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void TglBeli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglBeli1KeyPressed
        Valid.pindah(evt, NoFaktur, kdsup);
    }//GEN-LAST:event_TglBeli1KeyPressed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        akses.setform("DlgCariPemesanan");
        barang.emptTeks();
        barang.isCek();
        barang.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        barang.setLocationRelativeTo(internalFrame1);
        barang.setAlwaysOnTop(false);
        barang.setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void btnSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSatuanActionPerformed
        akses.setform("DlgCariPemesanan");
        barang.jenis.emptTeks();
        barang.jenis.isCek();
        barang.jenis.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        barang.jenis.setLocationRelativeTo(internalFrame1);
        barang.jenis.setAlwaysOnTop(false);
        barang.jenis.setVisible(true);
    }//GEN-LAST:event_btnSatuanActionPerformed

    private void kdsupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdsupKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama_suplier from datasuplier where kode_suplier=?", nmsup, kdsup.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nama_suplier from datasuplier where kode_suplier=?", nmsup, kdsup.getText());
            NoFaktur.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama_suplier from datasuplier where kode_suplier=?", nmsup, kdsup.getText());
            kdptg.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnSuplierActionPerformed(null);
        }
    }//GEN-LAST:event_kdsupKeyPressed

    private void NoFakturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoFakturKeyPressed
        Valid.pindah(evt, BtnKeluar, kdsup);
    }//GEN-LAST:event_NoFakturKeyPressed

    private void kdptgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
            kdsup.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
            kdbar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_kdptgKeyPressed

    private void kdbarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama_brng from databarang where kode_brng=?", nmbar, kdbar.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nama_brng from databarang where kode_brng=?", nmbar, kdbar.getText());
            kdsat.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama_brng from databarang where kode_brng=?", nmbar, kdbar.getText());
            TCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnBarangActionPerformed(null);
        }
    }//GEN-LAST:event_kdbarKeyPressed

    private void kdsatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdsatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select satuan from kodesatuan where kode_sat=?", nmsat, kdsat.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select satuan from kodesatuan where kode_sat=?", nmsat, kdsat.getText());
            kdptg.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select satuan from kodesatuan where kode_sat=?", nmsat, kdsat.getText());
            kdbar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnSatuanActionPerformed(null);
        }
    }//GEN-LAST:event_kdsatKeyPressed

    private void TglBeli2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglBeli2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglBeli2KeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbPemesanan.requestFocus();
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
        NoFaktur.setText("");
        kdbar.setText("");
        nmbar.setText("");
        kdsat.setText("");
        nmsat.setText("");
        kdsup.setText("");
        nmsup.setText("");
        kdptg.setText("");
        nmptg.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode.getRowCount();
            for (int i = 0; i < row; i++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode.getValueAt(i, 0).toString() + "','"
                        + tabMode.getValueAt(i, 1).toString() + "','"
                        + tabMode.getValueAt(i, 2).toString() + "','"
                        + tabMode.getValueAt(i, 3).toString() + "','"
                        + tabMode.getValueAt(i, 4).toString() + "','"
                        + tabMode.getValueAt(i, 5).toString() + "','"
                        + tabMode.getValueAt(i, 6).toString() + "','"
                        + tabMode.getValueAt(i, 9).toString() + "','"
                        + tabMode.getValueAt(i, 11).toString() + "','" + tabMode.getValueAt(i, 7).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Pemesanan");
            }
            Sequel.menyimpan("temporary", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Pemesanan");
            Sequel.menyimpan("temporary", "'0','Jml.Total :','','','','','','','','" + LTotal.getText() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Pemesanan");
            Sequel.AutoComitFalse();

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptPemesanan.jasper", "report", "::[ Transaksi Pemesanan Barang ]::",
                    "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14 from temporary order by no asc", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnAll, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void nmsatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmsatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmsatKeyPressed

private void ppHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusActionPerformed
    if (tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString().trim().equals("")) {
        Valid.textKosong(TCari, tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString());
    } else {
        int reply = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data akan dihapus ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                cek = 0;
                Sequel.AutoComitFalse();
                pscaripesan.setString(1, tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString());
                rs = pscaripesan.executeQuery();
                while (rs.next()) {
                    psdetailpesan.setString(1, rs.getString(1));
                    rs2 = psdetailpesan.executeQuery();
                    while (rs2.next()) {
                        isStok(rs2.getString("kode_brng"), rs.getString("kd_bangsal"));
                        if (stokbarang2 == 0 || stokbarang2 < Double.parseDouble(rs2.getString("jumlah2"))) {
                            cek++;
                        }

//                 Trackobat.catatRiwayat2(rs2.getString("kode_brng"),0,rs2.getDouble("jumlah2"),"Pemesanan",var.getkode(),rs.getString("kd_bangsal"),"Hapus");
//                 Sequel.menyimpan("gudangbarang","'"+rs2.getString("kode_brng") +"','"+rs.getString("kd_bangsal") +"','-"+rs2.getString("jumlah2") +"'", 
//                                        "stok=stok-'"+rs2.getString("jumlah2") +"'","kode_brng='"+rs2.getString("kode_brng")+"' and kd_bangsal='"+rs.getString("kd_bangsal") +"'");
                    }

                    if (cek > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Maaf,ada stok yang tidak mencukupi..!!, silakan hapus transaksi lain terlebih dahulu");
                    } else {
                        rs2 = psdetailpesan.executeQuery();
                        while (rs2.next()) {
                            Trackobat.catatRiwayat2(rs2.getString("kode_brng"), 0, rs2.getDouble("jumlah2"), "Pemesanan", akses.getkode(), rs.getString("kd_bangsal"), "Hapus");
                            Sequel.menyimpan("gudangbarang", "'" + rs2.getString("kode_brng") + "','" + rs.getString("kd_bangsal") + "','-" + rs2.getString("jumlah2") + "'",
                                    "stok=stok-'" + rs2.getString("jumlah2") + "'", "kode_brng='" + rs2.getString("kode_brng") + "' and kd_bangsal='" + rs.getString("kd_bangsal") + "'");
                        }
                        Sequel.queryu("delete from tampjurnal");
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", "Rekening", 4, new String[]{
                            Sequel.cariIsi("select Pemesanan_Obat from set_akun"), "PERSEDIAAN BARANG", "0", rs.getString("tagihan")
                        });
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", "Rekening", 4, new String[]{
                            Sequel.cariIsi("select Kontra_Pemesanan_Obat from set_akun"), "HUTANG USAHA", rs.getString("tagihan"), "0"
                        });
                        jur.simpanJurnal(rs.getString("no_faktur"), Sequel.cariIsi("select current_date()"), "U", "BATAL TRANSAKSI PEMESANAN BARANG DI " + Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal=?", rs.getString("kd_bangsal")).toUpperCase());
                        Sequel.queryu2("delete from pemesanan where no_faktur=?", 1, new String[]{tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString()});

                    }
                }
                Sequel.menyimpan("history_user", "Now(),'" + tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString() + "','" + akses.getkode() + "','Hapus Data Pemesanan','Hapus'");
                Sequel.AutoComitTrue();
                tampil();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

}//GEN-LAST:event_ppHapusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void ppBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBayarActionPerformed
        if (tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString().trim().equals("")) {
            Valid.textKosong(TCari, tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString());
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgBayarPemesanan bayarpesan = new DlgBayarPemesanan(null, false);
            bayarpesan.setData(tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString());
            bayarpesan.tampil();
            bayarpesan.isCek();
            bayarpesan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            bayarpesan.setLocationRelativeTo(internalFrame1);
            bayarpesan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppBayarActionPerformed

    private void ppUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppUbahActionPerformed
        if (tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString().trim().equals("")) {
            Valid.textKosong(TCari, tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString());
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgUbahPemesanan ubahpesan = new DlgUbahPemesanan(null, false);
            //bayarpesan.setData(tbDokter.getValueAt(tbDokter.getSelectedRow(),1).toString());
            //bayarpesan.tampil();
            ubahpesan.isCek(tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString());
            ubahpesan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            ubahpesan.setLocationRelativeTo(internalFrame1);
            ubahpesan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppUbahActionPerformed

    private void KdIFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdIFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama_industri from industrifarmasi where nip=?", NmIF, KdIF.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nama_industri from industrifarmasi where nip=?", NmIF, KdIF.getText());
            kdptg.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama_industri from industrifarmasi where nip=?", NmIF, KdIF.getText());
            kdbar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnIFActionPerformed(null);
        }
    }//GEN-LAST:event_KdIFKeyPressed

    private void btnIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIFActionPerformed
        akses.setform("DlgCariPemesanan");
        barang.industri.emptTeks();
        barang.industri.isCek();
        barang.industri.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        barang.industri.setLocationRelativeTo(internalFrame1);
        barang.industri.setAlwaysOnTop(false);
        barang.industri.setVisible(true);
    }//GEN-LAST:event_btnIFActionPerformed

    private void ppCetakPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCetakPemesananActionPerformed
        if (chkFree.isSelected() == true) {

            par = "";
            if (kdsup.getText().equals("") && kdbar.getText().equals("")) {
                par = "";
            } else if (!kdsup.getText().equals("") && !kdbar.getText().equals("")) {
                par = " and (s.kode_suplier='" + kdsup.getText() + "' AND b.kode_brng='" + kdbar.getText() + "')";
            } else if (kdsup.getText().equals("") && !kdbar.getText().equals("")) {
                par = " and (b.kode_brng='" + kdbar.getText() + "')";
            } else if (!kdsup.getText().equals("") && kdbar.getText().equals("")) {
                par = " and (s.kode_suplier='" + kdsup.getText() + "')";
            }

            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. " + TglBeli1.getSelectedItem() + " S.D " + TglBeli2.getSelectedItem());
            param.put("bangsal", "TEMPAT : " + nmgudang.getText());
            param.put("tgllaporan", "Martapura, " + Sequel.cariIsi("select date_format(now(),'%d')") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select date_format(now(),'%Y')"));
            Valid.MyReport("rptRekapPemesananObatPerBangsal.jasper", "report", "::[ Rekap Laporan Item Data Pemesanan Obat ]::",
                    "SELECT p.no_faktur, DATE_FORMAT(p.tgl_faktur,'%d-%m-%Y') tgl_faktur, b.nama_brng, s.nama_suplier, d.kode_sat, sum( d.jumlah ) qty, "
                    + "round(d.h_pesan) hrg_satuan, round(sum(d.subtotal)) subtotal, d.besardis, round(sum(d.total)) total,if(p.ppn = 0,0,(round((sum(d.total)))*0.1)) ppn, "
                    + "if(p.ppn = 0,round(sum(d.total)), round(sum(d.total))+(round(round((sum(d.total)))*0.1))) grand_total, DATE_FORMAT(p.tgl_pesan,'%d-%m-%Y') tgl_pesan "
                    + "FROM detailpesan d INNER JOIN pemesanan p ON p.no_faktur = d.no_faktur INNER JOIN databarang b ON b.kode_brng = d.kode_brng "
                    + "INNER JOIN datasuplier s ON s.kode_suplier = p.kode_suplier "
                    + "INNER JOIN bangsal g ON g.kd_bangsal = p.kd_bangsal "
                    + "WHERE g.kd_bangsal = '" + kdgudang.getText() + "' and p.tgl_pesan BETWEEN '" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(TglBeli2.getSelectedItem() + "") + "'" + par + " "
                    + "GROUP BY p.no_faktur, d.kode_brng, s.kode_suplier, d.h_pesan ORDER BY b.nama_brng", param);
            this.setCursor(Cursor.getDefaultCursor());

        } else {
            par = "";
            if (kdsup.getText().equals("") && kdbar.getText().equals("")) {
                par = "";
            } else if (!kdsup.getText().equals("") && !kdbar.getText().equals("")) {
                par = " and (s.kode_suplier='" + kdsup.getText() + "' AND b.kode_brng='" + kdbar.getText() + "')";
            } else if (kdsup.getText().equals("") && !kdbar.getText().equals("")) {
                par = " and (b.kode_brng='" + kdbar.getText() + "')";
            } else if (!kdsup.getText().equals("") && kdbar.getText().equals("")) {
                par = " and (s.kode_suplier='" + kdsup.getText() + "')";
            }

            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. " + TglBeli1.getSelectedItem() + " S.D " + TglBeli2.getSelectedItem());
            param.put("tgllaporan", "Martapura, " + Sequel.cariIsi("select date_format(now(),'%d')") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select date_format(now(),'%Y')"));
            Valid.MyReport("rptRekapPemesananObat.jasper", "report", "::[ Rekap Laporan Item Data Pemesanan Obat ]::",
                    "SELECT p.no_faktur, DATE_FORMAT(p.tgl_faktur,'%d-%m-%Y') tgl_faktur, b.nama_brng, s.nama_suplier, d.kode_sat, sum( d.jumlah ) qty, "
                    + "round(d.h_pesan) hrg_satuan, round(sum(d.subtotal)) subtotal, d.besardis, round(sum(d.total)) total,if(p.ppn = 0,0,(round((sum(d.total)))*0.1)) ppn, "
                    + "if(p.ppn = 0,round(sum(d.total)), round(sum(d.total))+(round(round((sum(d.total)))*0.1))) grand_total, DATE_FORMAT(p.tgl_pesan,'%d-%m-%Y') tgl_pesan "
                    + "FROM detailpesan d INNER JOIN pemesanan p ON p.no_faktur = d.no_faktur INNER JOIN databarang b ON b.kode_brng = d.kode_brng "
                    + "INNER JOIN datasuplier s ON s.kode_suplier = p.kode_suplier "
                    + "WHERE p.tgl_pesan BETWEEN '" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(TglBeli2.getSelectedItem() + "") + "'" + par + " "
                    + "GROUP BY p.no_faktur, d.kode_brng, s.kode_suplier, d.h_pesan ORDER BY b.nama_brng", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCetakPemesananActionPerformed

    private void kdgudangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdgudangKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmgudang, kdgudang.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmgudang, kdgudang.getText());
            kdptg.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmgudang, kdgudang.getText());
            TCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnGudangActionPerformed(null);
        }
    }//GEN-LAST:event_kdgudangKeyPressed

    private void btnGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGudangActionPerformed
        akses.setform("DlgPemesanan");
        bangsal.isCek();
        bangsal.emptTeks();
        bangsal.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setAlwaysOnTop(false);
        bangsal.setVisible(true);
    }//GEN-LAST:event_btnGudangActionPerformed

    private void chkFreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFreeActionPerformed
        // TODO add your handling code here:
        //        isRawat();
    }//GEN-LAST:event_chkFreeActionPerformed

    private void ppRekapPemesananExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRekapPemesananExcelActionPerformed
        dialog_simpan = "";
        if ((Sequel.cariInteger("select count(-1) jml from pemesanan p inner join detailpesan d on d.no_faktur = p.no_faktur inner join databarang g on g.kode_brng = d.kode_brng "
                + "where p.tgl_pesan between '" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(TglBeli2.getSelectedItem() + "") + "'")) > 0) {
            dialog_simpan = Valid.openDialog();
            if (!dialog_simpan.equals("the user cancelled the operation")) {
                if (Valid.MyReportToExcelBoolean("select a.nama_brng 'Nama Barang',a.kode_sat 'Satuan',a.tipe_brg 'Tipe',ifnull( b.Total, 0 ) 'QTY',IFNULL(b.h_beli,0) 'Harga',ifnull( b.Total, 0 ) * IFNULL(b.h_beli,0) 'Total' "
                        + "from ( (select kode_brng,nama_brng, tipe_brg,kode_sat, h_beli from databarang where `status` = '1' ) as a left join (select g.kode_brng, g.nama_brng,round(d.h_pesan,0) h_beli, sum(d.jumlah2) 'Total' "
                        + "from pemesanan p inner join detailpesan d on d.no_faktur = p.no_faktur inner join databarang g on g.kode_brng = d.kode_brng where p.tgl_pesan "
                        + "between '" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(TglBeli2.getSelectedItem() + "") + "' group by g.kode_brng,g.h_beli) "
                        + "as b on b.kode_brng = a.kode_brng ) order by a.nama_brng", dialog_simpan) == true) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diexport menjadi file excel,..!!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal diexport menjadi file excel,..!!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
        }
    }//GEN-LAST:event_ppRekapPemesananExcelActionPerformed

    private void ppRingkasanRekapPemesananExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRingkasanRekapPemesananExcelActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode.getRowCount();
            for (int i = 0; i < row; i++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode.getValueAt(i, 0).toString() + "','"
                        + tabMode.getValueAt(i, 1).toString() + "','"
                        + tabMode.getValueAt(i, 2).toString() + "','"
                        + tabMode.getValueAt(i, 3).toString() + "','"
                        + tabMode.getValueAt(i, 4).toString() + "','"
                        + tabMode.getValueAt(i, 5).toString() + "','"
                        + tabMode.getValueAt(i, 6).toString() + "','"
                        + tabMode.getValueAt(i, 9).toString() + "','"
                        + tabMode.getValueAt(i, 11).toString() + "','" + tabMode.getValueAt(i, 7).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Pemesanan");
            }
            Sequel.menyimpan("temporary", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Pemesanan");
            Sequel.menyimpan("temporary", "'0','Jml.Total :','','','','','','','','" + LTotal.getText() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Pemesanan");
            Sequel.AutoComitFalse();

            dialog_simpan = "";
            dialog_simpan = Valid.openDialog();
            if (!dialog_simpan.equals("the user cancelled the operation")) {
                if (Valid.MyReportToExcelBoolean("select temp1 'Nama Supplier', temp2 'No.Faktur', temp3 'Tgl.Faktur',temp4 'Petugas RS',"
                        + "temp5 'Nama Barang/Obat/Alkes', temp6 'Satuan', CAST(REPLACE(temp10,',','') as FLOAT) 'Hrg. Beli (Rp.)',"
                        + "temp7 'Jml Beli', temp8 'Diskon', CAST(REPLACE(temp9,',','') as FLOAT) 'Total (Rp)' from `temporary`", dialog_simpan) == true) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diexport menjadi file excel,..!!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal diexport menjadi file excel,..!!!");
                }
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppRingkasanRekapPemesananExcelActionPerformed

    private void ppInOutGudangExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppInOutGudangExcelActionPerformed
        // TODO add your handling code here:
        if (kdgudang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan Pilih Gudang Terlebih Dahulu");
        } else {
            dialog_simpan = "";
            dialog_simpan = Valid.openDialog();
            if (!dialog_simpan.equals("the user cancelled the operation")) {
                if (Valid.MyReportToExcelBoolean("select a.nama_brng 'Nama Barang',a.kode_sat 'Satuan', a.tipe_brg 'Tipe',round(a.h_beli) 'HARGA',IFNULL(e.stok,0) 'STOK AWAL', "
                        + "ifnull(b.Total,0) 'PEMBELIAN', IFNULL(c.jml,0) + IFNULL(d.jml,0) + IFNULL(f.jml,0) 'PENGELUARAN', "
                        + "IFNULL(e.stok,0)+ifnull(b.Total,0)-(IFNULL(c.jml,0) + IFNULL(d.jml,0) + IFNULL(f.jml,0)) 'TOTAL' from ( "
                        + "(select kode_brng,nama_brng, tipe_brg, kode_sat, h_beli from databarang where `status` = '1' "
                        + ") as a "
                        + "left join "
                        + "(select g.kode_brng, g.nama_brng, sum(d.jumlah2) 'Total' from pemesanan p "
                        + "inner join detailpesan d on d.no_faktur = p.no_faktur "
                        + "inner join databarang g on g.kode_brng = d.kode_brng "
                        + "where p.tgl_pesan like concat(date_format('" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "','%Y-%m'),'%') "
                        + "group by g.kode_brng) as b on b.kode_brng = a.kode_brng "
                        + "left join "
                        + "(select kode_brng,sum(jml) jml from mutasibarang where kd_bangsaldari =  '" + kdgudang.getText() + "' and tanggal like concat(date_format('" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "','%Y-%m'),'%') group by kode_brng) as c on c.kode_brng = a.kode_brng "
                        + "left join  "
                        + "(select kode_brng, sum(jml) jml from utd_pengambilan_medis where kd_bangsal_dr =  '" + kdgudang.getText() + "' and tanggal like concat(date_format('" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "','%Y-%m'),'%') group by kode_brng) as d on d.kode_brng = a.kode_brng "
                        + "left join "
                        + "(select kode_brng, stok_awal 'stok' from stok_bulanan where kd_bangsal =  '" + kdgudang.getText() + "' and periode = date_format('" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "','%Y-%m')) as e on e.kode_brng = a.kode_brng "
                        + "left join "
                        + "(select d.kode_brng,d.jml_retur jml from returbeli r "
                        + "inner join detreturbeli d on d.no_retur_beli = r.no_retur_beli "
                        + "where r.tgl_retur like concat(date_format('" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "','%Y-%m'),'%') and r.kd_bangsal =  '" + kdgudang.getText() + "' group by d.kode_brng "
                        + ") as f on f.kode_brng = a.kode_brng "
                        + ") order by a.nama_brng", dialog_simpan) == true) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diexport menjadi file excel,..!!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal diexport menjadi file excel,..!!!");
                }
            }

        }
    }//GEN-LAST:event_ppInOutGudangExcelActionPerformed

    private void ppCetakSuratPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCetakSuratPesananActionPerformed
        if (tbPemesanan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pemesanan pada tabel masih kosong...!!!!");
        } else if (no_Faktur.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu no. faktur nya pada tabel..!!!");
            tbPemesanan.requestFocus();
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tglsurat", "Martapura, " + Sequel.cariIsi("select date_format(tgl_faktur,'%d') from pemesanan where no_faktur='" + no_Faktur + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_faktur) from pemesanan where no_faktur='" + no_Faktur + "'") + " "
                    + Sequel.cariIsi("select date_format(tgl_faktur,'%Y') from pemesanan where no_faktur='" + no_Faktur + "'"));
            Valid.MyReport("rptSuratPesananObat.jasper", "report", "::[ Surat Pesanan Obat ]::",
                    "SELECT s.nama_suplier, g.nama_brng, d.kode_sat, d.jumlah FROM pemesanan p "
                    + "INNER JOIN detailpesan d ON d.no_faktur = p.no_faktur INNER JOIN datasuplier s ON s.kode_suplier = p.kode_suplier "
                    + "INNER JOIN databarang g ON g.kode_brng = d.kode_brng WHERE p.no_faktur='" + no_Faktur + "'", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCetakSuratPesananActionPerformed

    private void tbPemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemesananMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemesananMouseClicked

    private void tbPemesananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemesananKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemesananKeyPressed

    private void ppCetakPemesananExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCetakPemesananExcelActionPerformed
        // TODO add your handling code here:
        if (chkFree.isSelected() == true) {

            par = "";
            if (kdsup.getText().equals("") && kdbar.getText().equals("")) {
                par = "";
            } else if (!kdsup.getText().equals("") && !kdbar.getText().equals("")) {
                par = " and (s.kode_suplier='" + kdsup.getText() + "' AND b.kode_brng='" + kdbar.getText() + "')";
            } else if (kdsup.getText().equals("") && !kdbar.getText().equals("")) {
                par = " and (b.kode_brng='" + kdbar.getText() + "')";
            } else if (!kdsup.getText().equals("") && kdbar.getText().equals("")) {
                par = " and (s.kode_suplier='" + kdsup.getText() + "')";
            }

            dialog_simpan = "";
            dialog_simpan = Valid.openDialog();
            if (!dialog_simpan.equals("the user cancelled the operation")) {
                if (Valid.MyReportToExcelBoolean("SELECT p.no_faktur 'No Faktur', DATE_FORMAT(p.tgl_faktur,'%d-%m-%Y') 'Tgl. Faktur', "
                        + "b.nama_brng 'Nama Obat/Alkes', s.nama_suplier 'Nama Supplier', d.kode_sat 'Satuan', sum( d.jumlah ) 'Qty', round(d.h_pesan) 'Hrg. Satuan', "
                        + "round(sum(d.subtotal)) 'Sub Total', d.besardis 'Diskon', round(sum(d.total)) 'Total',if(p.ppn = 0,0,(round((sum(d.total)))*0.1)) 'PPN', "
                        + "if(p.ppn = 0,round(sum(d.total)), round(sum(d.total))+(round(round((sum(d.total)))*0.1))) 'Grand Total', DATE_FORMAT(p.tgl_pesan,'%d-%m-%Y') 'Tgl. Pesan' "
                        + "FROM detailpesan d INNER JOIN pemesanan p ON p.no_faktur = d.no_faktur INNER JOIN databarang b ON b.kode_brng = d.kode_brng "
                        + "INNER JOIN datasuplier s ON s.kode_suplier = p.kode_suplier "
                        + "INNER JOIN bangsal g ON g.kd_bangsal = p.kd_bangsal "
                        + "WHERE g.kd_bangsal = '" + kdgudang.getText() + "' and p.tgl_pesan BETWEEN '" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(TglBeli2.getSelectedItem() + "") + "'" + par + " "
                        + "GROUP BY p.no_faktur, d.kode_brng, s.kode_suplier, d.h_pesan ORDER BY b.nama_brng", dialog_simpan) == true) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diexport menjadi file excel,..!!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal diexport menjadi file excel,..!!!");
                }
            }

        } else {
            par = "";
            if (kdsup.getText().equals("") && kdbar.getText().equals("")) {
                par = "";
            } else if (!kdsup.getText().equals("") && !kdbar.getText().equals("")) {
                par = " and (s.kode_suplier='" + kdsup.getText() + "' AND b.kode_brng='" + kdbar.getText() + "')";
            } else if (kdsup.getText().equals("") && !kdbar.getText().equals("")) {
                par = " and (b.kode_brng='" + kdbar.getText() + "')";
            } else if (!kdsup.getText().equals("") && kdbar.getText().equals("")) {
                par = " and (s.kode_suplier='" + kdsup.getText() + "')";
            }

            dialog_simpan = "";
            dialog_simpan = Valid.openDialog();
            if (!dialog_simpan.equals("the user cancelled the operation")) {
                if (Valid.MyReportToExcelBoolean("SELECT p.no_faktur 'No Faktur', DATE_FORMAT(p.tgl_faktur,'%d-%m-%Y') 'Tgl. Faktur', "
                        + "b.nama_brng 'Nama Obat/Alkes', s.nama_suplier 'Nama Supplier', d.kode_sat 'Satuan', sum( d.jumlah ) 'Qty', round(d.h_pesan) 'Hrg. Satuan', "
                        + "round(sum(d.subtotal)) 'Sub Total', d.besardis 'Diskon', round(sum(d.total)) 'Total',if(p.ppn = 0,0,(round((sum(d.total)))*0.1)) 'PPN', "
                        + "if(p.ppn = 0,round(sum(d.total)), round(sum(d.total))+(round(round((sum(d.total)))*0.1))) 'Grand Total', DATE_FORMAT(p.tgl_pesan,'%d-%m-%Y') 'Tgl. Pesan' "
                        + "FROM detailpesan d INNER JOIN pemesanan p ON p.no_faktur = d.no_faktur INNER JOIN databarang b ON b.kode_brng = d.kode_brng "
                        + "INNER JOIN datasuplier s ON s.kode_suplier = p.kode_suplier "
                        + "WHERE p.tgl_pesan BETWEEN '" + Valid.SetTgl(TglBeli1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(TglBeli2.getSelectedItem() + "") + "'" + par + " "
                        + "GROUP BY p.no_faktur, d.kode_brng, s.kode_suplier, d.h_pesan ORDER BY b.nama_brng", dialog_simpan) == true) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diexport menjadi file excel,..!!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal diexport menjadi file excel,..!!!");
                }
            }
        }
    }//GEN-LAST:event_ppCetakPemesananExcelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPemesanan dialog = new DlgCariPemesanan(new javax.swing.JFrame(), true);
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
    private widget.TextBox KdIF;
    private widget.Label LTotal;
    private widget.TextBox NmIF;
    private widget.TextBox NoFaktur;
    private widget.TextBox TCari;
    private widget.Tanggal TglBeli1;
    private widget.Tanggal TglBeli2;
    private widget.Button btnBarang;
    private widget.Button btnGudang;
    private widget.Button btnIF;
    private widget.Button btnPetugas;
    private widget.Button btnSatuan;
    private widget.Button btnSuplier;
    private widget.CekBox chkFree;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdbar;
    private widget.TextBox kdgudang;
    private widget.TextBox kdptg;
    private widget.TextBox kdsat;
    private widget.TextBox kdsup;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label20;
    private widget.Label label24;
    private widget.Label label9;
    private widget.TextBox nmbar;
    private widget.TextBox nmgudang;
    private widget.TextBox nmptg;
    private widget.TextBox nmsat;
    private widget.TextBox nmsup;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppBayar;
    private javax.swing.JMenuItem ppCetakPemesanan;
    private javax.swing.JMenuItem ppCetakPemesananExcel;
    private javax.swing.JMenuItem ppCetakSuratPesanan;
    private javax.swing.JMenuItem ppHapus;
    private javax.swing.JMenuItem ppInOutGudangExcel;
    private javax.swing.JMenuItem ppRekapPemesananExcel;
    private javax.swing.JMenuItem ppRingkasanRekapPemesananExcel;
    private javax.swing.JMenuItem ppUbah;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbPemesanan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select pemesanan.tgl_pesan,pemesanan.no_faktur, "
                    + "pemesanan.kode_suplier,datasuplier.nama_suplier, "
                    + "pemesanan.nip,petugas.nama,bangsal.nm_bangsal,pemesanan.tgl_faktur, "
                    + "pemesanan.tgl_tempo,pemesanan.status "
                    + " from pemesanan inner join datasuplier inner join petugas inner join bangsal  "
                    + " inner join detailpesan inner join databarang inner join kodesatuan "
                    + " inner join jenis inner join industrifarmasi "
                    + " on detailpesan.kode_brng=databarang.kode_brng "
                    + " and detailpesan.kode_sat=kodesatuan.kode_sat "
                    + " and pemesanan.kd_bangsal=bangsal.kd_bangsal "
                    + " and pemesanan.no_faktur=detailpesan.no_faktur "
                    + " and pemesanan.kode_suplier=datasuplier.kode_suplier "
                    + " and databarang.kode_industri=industrifarmasi.kode_industri "
                    + " and pemesanan.nip=petugas.nip and databarang.kdjns=jenis.kdjns"
                    + " where pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and pemesanan.no_faktur like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and pemesanan.kode_suplier like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and datasuplier.nama_suplier like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and pemesanan.nip like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and petugas.nama like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and bangsal.nm_bangsal like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and detailpesan.kode_brng like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and databarang.nama_brng like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and detailpesan.kode_sat like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and detailpesan.no_batch like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and industrifarmasi.nama_industri like ? or "
                    + " pemesanan.tgl_pesan between ? and ? and pemesanan.no_faktur like ? and datasuplier.nama_suplier like ? and petugas.nama like ?  and jenis.nama like ? and databarang.nama_brng like ? and industrifarmasi.nama_industri like ? and jenis.nama like ? "
                    + " group by pemesanan.no_faktur order by datasuplier.nama_suplier asc, pemesanan.tgl_faktur asc");
            try {
                ps.setString(1, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(3, "%" + NoFaktur.getText() + "%");
                ps.setString(4, "%" + nmsup.getText() + "%");
                ps.setString(5, "%" + nmptg.getText() + "%");
                ps.setString(6, "%" + nmsat.getText() + "%");
                ps.setString(7, "%" + nmbar.getText() + "%");
                ps.setString(8, "%" + NmIF.getText() + "%");
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(12, "%" + NoFaktur.getText() + "%");
                ps.setString(13, "%" + nmsup.getText() + "%");
                ps.setString(14, "%" + nmptg.getText() + "%");
                ps.setString(15, "%" + nmsat.getText() + "%");
                ps.setString(16, "%" + nmbar.getText() + "%");
                ps.setString(17, "%" + NmIF.getText() + "%");
                ps.setString(18, "%" + TCari.getText() + "%");
                ps.setString(19, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(21, "%" + NoFaktur.getText() + "%");
                ps.setString(22, "%" + nmsup.getText() + "%");
                ps.setString(23, "%" + nmptg.getText() + "%");
                ps.setString(24, "%" + nmsat.getText() + "%");
                ps.setString(25, "%" + nmbar.getText() + "%");
                ps.setString(26, "%" + NmIF.getText() + "%");
                ps.setString(27, "%" + TCari.getText() + "%");
                ps.setString(28, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(30, "%" + NoFaktur.getText() + "%");
                ps.setString(31, "%" + nmsup.getText() + "%");
                ps.setString(32, "%" + nmptg.getText() + "%");
                ps.setString(33, "%" + nmsat.getText() + "%");
                ps.setString(34, "%" + nmbar.getText() + "%");
                ps.setString(35, "%" + NmIF.getText() + "%");
                ps.setString(36, "%" + TCari.getText() + "%");
                ps.setString(37, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(38, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(39, "%" + NoFaktur.getText() + "%");
                ps.setString(40, "%" + nmsup.getText() + "%");
                ps.setString(41, "%" + nmptg.getText() + "%");
                ps.setString(42, "%" + nmsat.getText() + "%");
                ps.setString(43, "%" + nmbar.getText() + "%");
                ps.setString(44, "%" + NmIF.getText() + "%");
                ps.setString(45, "%" + TCari.getText() + "%");
                ps.setString(46, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(47, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(48, "%" + NoFaktur.getText() + "%");
                ps.setString(49, "%" + nmsup.getText() + "%");
                ps.setString(50, "%" + nmptg.getText() + "%");
                ps.setString(51, "%" + nmsat.getText() + "%");
                ps.setString(52, "%" + nmbar.getText() + "%");
                ps.setString(53, "%" + NmIF.getText() + "%");
                ps.setString(54, "%" + TCari.getText() + "%");
                ps.setString(55, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(56, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(57, "%" + NoFaktur.getText() + "%");
                ps.setString(58, "%" + nmsup.getText() + "%");
                ps.setString(59, "%" + nmptg.getText() + "%");
                ps.setString(60, "%" + nmsat.getText() + "%");
                ps.setString(61, "%" + nmbar.getText() + "%");
                ps.setString(62, "%" + NmIF.getText() + "%");
                ps.setString(63, "%" + TCari.getText() + "%");
                ps.setString(64, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(65, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(66, "%" + NoFaktur.getText() + "%");
                ps.setString(67, "%" + nmsup.getText() + "%");
                ps.setString(68, "%" + nmptg.getText() + "%");
                ps.setString(69, "%" + nmsat.getText() + "%");
                ps.setString(70, "%" + nmbar.getText() + "%");
                ps.setString(71, "%" + NmIF.getText() + "%");
                ps.setString(72, "%" + TCari.getText() + "%");
                ps.setString(73, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(74, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(75, "%" + NoFaktur.getText() + "%");
                ps.setString(76, "%" + nmsup.getText() + "%");
                ps.setString(77, "%" + nmptg.getText() + "%");
                ps.setString(78, "%" + nmsat.getText() + "%");
                ps.setString(79, "%" + nmbar.getText() + "%");
                ps.setString(80, "%" + NmIF.getText() + "%");
                ps.setString(81, "%" + TCari.getText() + "%");
                ps.setString(82, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(83, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(84, "%" + NoFaktur.getText() + "%");
                ps.setString(85, "%" + nmsup.getText() + "%");
                ps.setString(86, "%" + nmptg.getText() + "%");
                ps.setString(87, "%" + nmsat.getText() + "%");
                ps.setString(88, "%" + nmbar.getText() + "%");
                ps.setString(89, "%" + NmIF.getText() + "%");
                ps.setString(90, "%" + TCari.getText() + "%");
                ps.setString(91, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(92, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(93, "%" + NoFaktur.getText() + "%");
                ps.setString(94, "%" + nmsup.getText() + "%");
                ps.setString(95, "%" + nmptg.getText() + "%");
                ps.setString(96, "%" + nmsat.getText() + "%");
                ps.setString(97, "%" + nmbar.getText() + "%");
                ps.setString(98, "%" + NmIF.getText() + "%");
                ps.setString(99, "%" + TCari.getText() + "%");
                ps.setString(100, Valid.SetTgl(TglBeli1.getSelectedItem() + ""));
                ps.setString(101, Valid.SetTgl(TglBeli2.getSelectedItem() + ""));
                ps.setString(102, "%" + NoFaktur.getText() + "%");
                ps.setString(103, "%" + nmsup.getText() + "%");
                ps.setString(104, "%" + nmptg.getText() + "%");
                ps.setString(105, "%" + nmsat.getText() + "%");
                ps.setString(106, "%" + nmbar.getText() + "%");
                ps.setString(107, "%" + NmIF.getText() + "%");
                ps.setString(108, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                tagihan = 0;
                while (rs.next()) {
                    tabMode.addRow(new Object[]{rs.getString(4), rs.getString(2), rs.getString("tgl_faktur"),
                        //tabMode.addRow(new Object[]{rs.getString("tgl_faktur"),rs.getString(2),rs.getString(3)+", "+rs.getString(4),
                        //  rs.getString(5)+", "+rs.getString(6),"Pengadaan di "+rs.getString(7) +" :","","","","","","",""
                        rs.getString(6), "Pengadaan di " + rs.getString(7) + " :", "", "", "", "", "", "", ""
                    });

                    ps2 = koneksi.prepareStatement("select detailpesan.kode_brng,databarang.nama_brng, "
                            + "detailpesan.kode_sat,kodesatuan.satuan,detailpesan.jumlah,detailpesan.h_pesan, "
                            + "detailpesan.subtotal,detailpesan.dis,detailpesan.besardis,detailpesan.total,"
                            + "detailpesan.no_batch,datasuplier.nama_suplier "
                            + "from detailpesan inner join databarang inner join kodesatuan inner join jenis inner join datasuplier inner join pemesanan inner join bangsal"
                            + " on detailpesan.kode_brng=databarang.kode_brng and databarang.kdjns=jenis.kdjns"
                            + " and detailpesan.no_faktur = pemesanan.no_faktur "
                            + " and pemesanan.kode_suplier = datasuplier.kode_suplier and detailpesan.kode_sat=kodesatuan.kode_sat and bangsal.kd_bangsal = pemesanan.kd_bangsal where "
                            + " detailpesan.no_faktur=? and jenis.nama like ? and databarang.nama_brng like ? and datasuplier.nama_suplier like ? and detailpesan.kode_brng like ? or "
                            + " detailpesan.no_faktur=? and jenis.nama like ? and databarang.nama_brng like ? and datasuplier.nama_suplier like ? and databarang.nama_brng like ? or "
                            + " detailpesan.no_faktur=? and jenis.nama like ? and databarang.nama_brng like ? and datasuplier.nama_suplier like ? and detailpesan.kode_sat like ? or "
                            + " detailpesan.no_faktur=? and jenis.nama like ? and databarang.nama_brng like ? and datasuplier.nama_suplier like ? and detailpesan.no_batch like ? or "
                            + " detailpesan.no_faktur=? and jenis.nama like ? and databarang.nama_brng like ? and datasuplier.nama_suplier like ? and datasuplier.nama_suplier like ? or "
                            + " detailpesan.no_faktur=? and jenis.nama like ? and databarang.nama_brng like ? and datasuplier.nama_suplier like ? and jenis.nama like ? or "
                            + " detailpesan.no_faktur=? and jenis.nama like ? and databarang.nama_brng like ? and datasuplier.nama_suplier like ? and detailpesan.no_faktur like ? or "
                            + " detailpesan.no_faktur=? and jenis.nama like ? and databarang.nama_brng like ? and datasuplier.nama_suplier like ? and bangsal.nm_bangsal like ? "
                            + " order by detailpesan.kode_brng");

                    try {
                        ps2.setString(1, rs.getString(2));
                        ps2.setString(2, "%" + nmsat.getText() + "%");
                        ps2.setString(3, "%" + nmbar.getText() + "%");
                        ps2.setString(4, "%" + NmIF.getText() + "%");
                        ps2.setString(5, "%" + TCari.getText() + "%");
                        ps2.setString(6, rs.getString(2));
                        ps2.setString(7, "%" + nmsat.getText() + "%");
                        ps2.setString(8, "%" + nmbar.getText() + "%");
                        ps2.setString(9, "%" + NmIF.getText() + "%");
                        ps2.setString(10, "%" + TCari.getText() + "%");
                        ps2.setString(11, rs.getString(2));
                        ps2.setString(12, "%" + nmsat.getText() + "%");
                        ps2.setString(13, "%" + nmbar.getText() + "%");
                        ps2.setString(14, "%" + NmIF.getText() + "%");
                        ps2.setString(15, "%" + TCari.getText() + "%");
                        ps2.setString(16, rs.getString(2));
                        ps2.setString(17, "%" + nmsat.getText() + "%");
                        ps2.setString(18, "%" + nmbar.getText() + "%");
                        ps2.setString(19, "%" + NmIF.getText() + "%");
                        ps2.setString(20, "%" + TCari.getText() + "%");
                        ps2.setString(21, rs.getString(2));
                        ps2.setString(22, "%" + nmsat.getText() + "%");
                        ps2.setString(23, "%" + nmbar.getText() + "%");
                        ps2.setString(24, "%" + NmIF.getText() + "%");
                        ps2.setString(25, "%" + TCari.getText() + "%");
                        ps2.setString(26, rs.getString(2));
                        ps2.setString(27, "%" + nmsat.getText() + "%");
                        ps2.setString(28, "%" + nmbar.getText() + "%");
                        ps2.setString(29, "%" + NmIF.getText() + "%");
                        ps2.setString(30, "%" + TCari.getText() + "%");
                        ps2.setString(31, rs.getString(2));
                        ps2.setString(32, "%" + nmsat.getText() + "%");
                        ps2.setString(33, "%" + nmbar.getText() + "%");
                        ps2.setString(34, "%" + NmIF.getText() + "%");
                        ps2.setString(35, "%" + TCari.getText() + "%");
                        ps2.setString(36, rs.getString(2));
                        ps2.setString(37, "%" + nmsat.getText() + "%");
                        ps2.setString(38, "%" + nmbar.getText() + "%");
                        ps2.setString(39, "%" + NmIF.getText() + "%");
                        ps2.setString(40, "%" + TCari.getText() + "%");
                        rs2 = ps2.executeQuery();
                        int no = 1;
                        while (rs2.next()) {
                            tabMode.addRow(new Object[]{"", "", no + ". No.Batch : " + rs2.getString("no_batch"), "-", rs2.getString(2),
                                //    tabMode.addRow(new Object[]{"","",no+". No.Batch : "+rs2.getString("no_batch"),"Ind.Farm : "+rs2.getString("nama_suplier"),rs2.getString(1)+", "+rs2.getString(2),
                                rs2.getString(3),
                                //    rs2.getString(3)+", "+rs2.getString(4),
                                rs2.getString(5), Valid.SetAngka(rs2.getDouble(6)), Valid.SetAngka(rs2.getDouble(7)),
                                Valid.SetAngka(rs2.getDouble(8)), Valid.SetAngka(rs2.getDouble(9)), Valid.SetAngka(rs2.getDouble(10))});
                            no++;
                        }
                        tabMode.addRow(new Object[]{"", "", "Tgl.Datang", ": " + rs.getString("tgl_pesan"), "", "", "", "", "", "Total", ":", Valid.SetAngka(Sequel.cariIsiAngka("select total2 from pemesanan where no_faktur=?", rs.getString(2)))});
                        tabMode.addRow(new Object[]{"", "", "Jth.Tempo", ": " + rs.getString("tgl_tempo"), "", "", "", "", "", "PPN", ":", Valid.SetAngka(Sequel.cariIsiAngka("select ppn from pemesanan where no_faktur=?", rs.getString(2)))});
                        tabMode.addRow(new Object[]{"", "", "Status Bayar", ": " + rs.getString("status"), "", "", "", "", "", "Tagihan", ":", Valid.SetAngka(Sequel.cariIsiAngka("select tagihan from pemesanan where no_faktur=?", rs.getString(2)))});
                        tagihan = tagihan + Sequel.cariIsiAngka("select tagihan from pemesanan where no_faktur=?", rs.getString(2));
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
            LTotal.setText(Valid.SetAngka(tagihan));
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeks() {
        kdbar.setText("");
        nmbar.setText("");
        kdsat.setText("");
        kdbar.requestFocus();
    }

    public void isCek() {
        BtnPrint.setEnabled(akses.getpemesanan_obat());
//        if (var.getkode().equals("Admin Utama")) {
//            ppHapus.setEnabled(true);
//        } else {
//            ppHapus.setEnabled(false);
//        }
        ppBayar.setEnabled(akses.getpemesanan_obat());
    }

    private void isStok(String a, String b) {
        stokbarang2 = Sequel.cariIsiAngka("select ifnull(stok,'0') from gudangbarang where kd_bangsal='" + b + "' and kode_brng='" + a + "'");
    }

    private void getData() {
        no_Faktur = "";
        if (tbPemesanan.getSelectedRow() != -1) {
            no_Faktur = tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString();
        }
    }

}
