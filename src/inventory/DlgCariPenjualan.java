package inventory;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
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
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgPasien;
import keuangan.Jurnal;
import kepegawaian.DlgCariPetugas;

public class DlgCariPenjualan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private final Properties prop = new Properties();
    private PreparedStatement ps, ps2, pscarijual, psdetailjual;
    private ResultSet rs, rs2;
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private riwayatobat Trackobat = new riwayatobat();
    private int i = 0, no = 1;
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public DlgPasien pasien = new DlgPasien(null, false);
    public DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgBarang barang = new DlgBarang(null, false);
    private DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,###");
    private double ttljual = 0, subttljual = 0, subttldisc = 0, subttlall = 0, subttltambahan = 0;
    private String verifikasi_penjualan_di_kasir = Sequel.cariIsi("select verifikasi_penjualan_di_kasir from set_nota"),
            nofak = "", mem = "", ptg = "", sat = "", bar = "", tanggal = "", no_nota = "", kd_obat = "", nmPrinter1 = "", nmPrinter2 = "";

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgCariPenjualan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No.Nota", "Tanggal", "Petugas", "Pasien", "Keterangan", "Jns.Jual", "PPN", "Barang", "Satuan",
            "Harga(Rp)", "Jml", "Subtotal(Rp)", "Ptg(%)", "Potongan(Rp)", "Tambahan(Rp)", "Total(Rp)", "nonota", 
            "kdbarang", "nama_obat"
        };
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(230);
            } else if (i == 3) {
                column.setPreferredWidth(230);
            } else if (i == 4) {
                column.setPreferredWidth(230);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(230);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(28);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setPreferredWidth(44);
            } else if (i == 13) {
                column.setPreferredWidth(80);
            } else if (i == 14) {
                column.setPreferredWidth(80);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        NoNota.setDocument(new batasInput((byte)25).getKata(NoNota));
        kdmem.setDocument(new batasInput((byte)15).getKata(kdmem));
        nmmem.setDocument(new batasInput((byte)70).getKata(nmmem));
        kdptg.setDocument(new batasInput((byte)25).getKata(kdptg));
        kdbar.setDocument(new batasInput((byte)15).getKata(kdbar));
        kdsat.setDocument(new batasInput((byte)3).getKata(kdsat));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));  
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        }

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgCariPenjualan")){
                    if(pasien.getTable().getSelectedRow()!= -1){                   
                        kdmem.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                        nmmem.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),2).toString());
                    }     
                    kdmem.requestFocus();
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
                if (akses.getform().equals("DlgCariPenjualan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
                if (akses.getform().equals("DlgCariPenjualan")) {
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
                if (akses.getform().equals("DlgCariPenjualan")) {
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
                if (akses.getform().equals("DlgCariPenjualan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        barang.dispose();
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
                if (akses.getform().equals("DlgCariPenjualan")) {
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

        if (verifikasi_penjualan_di_kasir.equals("Yes")) {
            ppVerif.setVisible(true);
        } else {
            ppVerif.setVisible(false);
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            nmPrinter1 = koneksiDB.NAMAPRINTER1();
            nmPrinter2 = koneksiDB.NAMAPRINTER2();
        } catch (Exception e) {
            System.out.println("NAMA PRINTER : "+e);
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
        ppCetakNota = new javax.swing.JMenuItem();
        ppCetakNotaAdminFar = new javax.swing.JMenuItem();
        ppHapus = new javax.swing.JMenuItem();
        ppVerif = new javax.swing.JMenuItem();
        ppVerifAll = new javax.swing.JMenuItem();
        ppAturanPakai = new javax.swing.JMenuItem();
        ppLabelObatMinum = new javax.swing.JMenuItem();
        ppLabelObatLuar = new javax.swing.JMenuItem();
        MnLaporanJual = new javax.swing.JMenu();
        MnRekapTotalPerPasien = new javax.swing.JMenuItem();
        MnRekapTotalDokterPerResep = new javax.swing.JMenuItem();
        MnDetailResepPerPasien = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        label17 = new widget.Label();
        kdbar = new widget.TextBox();
        nmbar = new widget.TextBox();
        btnBarang = new widget.Button();
        label24 = new widget.Label();
        kdsat = new widget.TextBox();
        btnSatuan = new widget.Button();
        nmsat = new widget.TextBox();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label9 = new widget.Label();
        LTotal = new widget.Label();
        BtnAll = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi3 = new widget.panelisi();
        label15 = new widget.Label();
        NoNota = new widget.TextBox();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label16 = new widget.Label();
        label13 = new widget.Label();
        kdmem = new widget.TextBox();
        kdptg = new widget.TextBox();
        nmmem = new widget.TextBox();
        nmptg = new widget.TextBox();
        btnPasien = new widget.Button();
        btnPetugas = new widget.Button();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppCetakNota.setBackground(new java.awt.Color(255, 255, 255));
        ppCetakNota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCetakNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCetakNota.setText("Cetak Ulang Nota");
        ppCetakNota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ppCetakNota.setName("ppCetakNota"); // NOI18N
        ppCetakNota.setPreferredSize(new java.awt.Dimension(200, 25));
        ppCetakNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCetakNotaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCetakNota);

        ppCetakNotaAdminFar.setBackground(new java.awt.Color(255, 255, 255));
        ppCetakNotaAdminFar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCetakNotaAdminFar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppCetakNotaAdminFar.setText("Nota Admin Farmasi");
        ppCetakNotaAdminFar.setName("ppCetakNotaAdminFar"); // NOI18N
        ppCetakNotaAdminFar.setPreferredSize(new java.awt.Dimension(200, 25));
        ppCetakNotaAdminFar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCetakNotaAdminFarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCetakNotaAdminFar);

        ppHapus.setBackground(new java.awt.Color(255, 255, 255));
        ppHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppHapus.setText("Hapus Penjualan");
        ppHapus.setName("ppHapus"); // NOI18N
        ppHapus.setPreferredSize(new java.awt.Dimension(200, 25));
        ppHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppHapus);

        ppVerif.setBackground(new java.awt.Color(255, 255, 255));
        ppVerif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppVerif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppVerif.setText("Verifikasi");
        ppVerif.setName("ppVerif"); // NOI18N
        ppVerif.setPreferredSize(new java.awt.Dimension(200, 25));
        ppVerif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppVerifActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppVerif);

        ppVerifAll.setBackground(new java.awt.Color(255, 255, 255));
        ppVerifAll.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppVerifAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppVerifAll.setText("Verifikasi Semua");
        ppVerifAll.setToolTipText("");
        ppVerifAll.setName("ppVerifAll"); // NOI18N
        ppVerifAll.setPreferredSize(new java.awt.Dimension(200, 25));
        ppVerifAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppVerifAllActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppVerifAll);

        ppAturanPakai.setBackground(new java.awt.Color(255, 255, 255));
        ppAturanPakai.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppAturanPakai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppAturanPakai.setText("Aturan Pakai Obat");
        ppAturanPakai.setName("ppAturanPakai"); // NOI18N
        ppAturanPakai.setPreferredSize(new java.awt.Dimension(200, 25));
        ppAturanPakai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppAturanPakaiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppAturanPakai);

        ppLabelObatMinum.setBackground(new java.awt.Color(255, 255, 255));
        ppLabelObatMinum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLabelObatMinum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppLabelObatMinum.setText("Cetak Aturan Pakai Obat Minum");
        ppLabelObatMinum.setName("ppLabelObatMinum"); // NOI18N
        ppLabelObatMinum.setPreferredSize(new java.awt.Dimension(200, 25));
        ppLabelObatMinum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLabelObatMinumActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppLabelObatMinum);

        ppLabelObatLuar.setBackground(new java.awt.Color(255, 255, 255));
        ppLabelObatLuar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLabelObatLuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppLabelObatLuar.setText("Cetak Aturan Pakai Obat Luar");
        ppLabelObatLuar.setName("ppLabelObatLuar"); // NOI18N
        ppLabelObatLuar.setPreferredSize(new java.awt.Dimension(200, 25));
        ppLabelObatLuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLabelObatLuarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppLabelObatLuar);

        MnLaporanJual.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnLaporanJual.setText("Laporan Rekap");
        MnLaporanJual.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanJual.setName("MnLaporanJual"); // NOI18N
        MnLaporanJual.setPreferredSize(new java.awt.Dimension(200, 25));

        MnRekapTotalPerPasien.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapTotalPerPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapTotalPerPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRekapTotalPerPasien.setText("Total Transaksi Per Pasien Per Resep");
        MnRekapTotalPerPasien.setName("MnRekapTotalPerPasien"); // NOI18N
        MnRekapTotalPerPasien.setPreferredSize(new java.awt.Dimension(230, 25));
        MnRekapTotalPerPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapTotalPerPasienActionPerformed(evt);
            }
        });
        MnLaporanJual.add(MnRekapTotalPerPasien);

        MnRekapTotalDokterPerResep.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapTotalDokterPerResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapTotalDokterPerResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRekapTotalDokterPerResep.setText("Total Transaksi Dokter Per Resep");
        MnRekapTotalDokterPerResep.setName("MnRekapTotalDokterPerResep"); // NOI18N
        MnRekapTotalDokterPerResep.setPreferredSize(new java.awt.Dimension(230, 25));
        MnRekapTotalDokterPerResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapTotalDokterPerResepActionPerformed(evt);
            }
        });
        MnLaporanJual.add(MnRekapTotalDokterPerResep);

        MnDetailResepPerPasien.setBackground(new java.awt.Color(255, 255, 255));
        MnDetailResepPerPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDetailResepPerPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnDetailResepPerPasien.setText("Detail Resep Per Pasien");
        MnDetailResepPerPasien.setName("MnDetailResepPerPasien"); // NOI18N
        MnDetailResepPerPasien.setPreferredSize(new java.awt.Dimension(230, 25));
        MnDetailResepPerPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetailResepPerPasienActionPerformed(evt);
            }
        });
        MnLaporanJual.add(MnDetailResepPerPasien);

        jPopupMenu1.add(MnLaporanJual);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cari Penjualan Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setComponentPopupMenu(jPopupMenu1);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        scrollPane1.setViewportView(tbObat);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

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
        nmbar.setBounds(501, 10, 270, 23);

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
        btnBarang.setBounds(774, 10, 28, 23);

        label24.setForeground(new java.awt.Color(0, 0, 0));
        label24.setText("Jenis :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi4.add(label24);
        label24.setBounds(0, 10, 74, 23);

        kdsat.setForeground(new java.awt.Color(0, 0, 0));
        kdsat.setName("kdsat"); // NOI18N
        kdsat.setPreferredSize(new java.awt.Dimension(80, 23));
        kdsat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdsatKeyPressed(evt);
            }
        });
        panelisi4.add(kdsat);
        kdsat.setBounds(79, 10, 53, 23);

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
        btnSatuan.setBounds(255, 10, 28, 23);

        nmsat.setForeground(new java.awt.Color(0, 0, 0));
        nmsat.setName("nmsat"); // NOI18N
        nmsat.setPreferredSize(new java.awt.Dimension(80, 23));
        nmsat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmsatKeyPressed(evt);
            }
        });
        panelisi4.add(nmsat);
        nmsat.setBounds(134, 10, 116, 23);

        jPanel1.add(panelisi4, java.awt.BorderLayout.CENTER);

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
        BtnCari.setPreferredSize(new java.awt.Dimension(160, 23));
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
        label9.setPreferredSize(new java.awt.Dimension(55, 30));
        panelisi1.add(label9);

        LTotal.setForeground(new java.awt.Color(0, 0, 0));
        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(155, 30));
        panelisi1.add(LTotal);

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

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 73));
        panelisi3.setLayout(null);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("No. Nota :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label15);
        label15.setBounds(0, 10, 70, 23);

        NoNota.setForeground(new java.awt.Color(0, 0, 0));
        NoNota.setName("NoNota"); // NOI18N
        NoNota.setPreferredSize(new java.awt.Dimension(207, 23));
        NoNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoNotaKeyPressed(evt);
            }
        });
        panelisi3.add(NoNota);
        NoNota.setBounds(74, 10, 226, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label11);
        label11.setBounds(0, 40, 70, 23);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelisi3.add(Tgl1);
        Tgl1.setBounds(74, 40, 100, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Pasien :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label16);
        label16.setBounds(385, 10, 60, 23);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setText("Petugas :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label13);
        label13.setBounds(385, 40, 60, 23);

        kdmem.setForeground(new java.awt.Color(0, 0, 0));
        kdmem.setName("kdmem"); // NOI18N
        kdmem.setPreferredSize(new java.awt.Dimension(80, 23));
        kdmem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdmemKeyPressed(evt);
            }
        });
        panelisi3.add(kdmem);
        kdmem.setBounds(449, 10, 80, 23);

        kdptg.setForeground(new java.awt.Color(0, 0, 0));
        kdptg.setName("kdptg"); // NOI18N
        kdptg.setPreferredSize(new java.awt.Dimension(80, 23));
        kdptg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptgKeyPressed(evt);
            }
        });
        panelisi3.add(kdptg);
        kdptg.setBounds(449, 40, 80, 23);

        nmmem.setForeground(new java.awt.Color(0, 0, 0));
        nmmem.setName("nmmem"); // NOI18N
        nmmem.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmmem);
        nmmem.setBounds(531, 10, 240, 23);

        nmptg.setEditable(false);
        nmptg.setForeground(new java.awt.Color(0, 0, 0));
        nmptg.setName("nmptg"); // NOI18N
        nmptg.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmptg);
        nmptg.setBounds(531, 40, 240, 23);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasien.setMnemonic('1');
        btnPasien.setToolTipText("Alt+1");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        panelisi3.add(btnPasien);
        btnPasien.setBounds(774, 10, 28, 23);

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
        btnPetugas.setBounds(774, 40, 28, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label18);
        label18.setBounds(173, 40, 30, 23);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelisi3.add(Tgl2);
        Tgl2.setBounds(200, 40, 100, 23);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        akses.setform("DlgCariPenjualan");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setAlwaysOnTop(false);
        pasien.setVisible(true);
    }//GEN-LAST:event_btnPasienActionPerformed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgCariPenjualan");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt, kdmem, Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void kdmemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdmemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", nmmem, kdmem.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", nmmem, kdmem.getText());
            NoNota.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", nmmem, kdmem.getText());
            Tgl1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPasienActionPerformed(null);
        }
    }//GEN-LAST:event_kdmemKeyPressed

    private void NoNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoNotaKeyPressed
        Valid.pindah(evt, BtnKeluar, kdptg);
    }//GEN-LAST:event_NoNotaKeyPressed

    private void kdptgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
            Tgl2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
            kdbar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_kdptgKeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1, kdptg);
    }//GEN-LAST:event_Tgl2KeyPressed

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

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        akses.setform("DlgCariPenjualan");
        barang.emptTeks();
        barang.isCek();
        barang.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        barang.setLocationRelativeTo(internalFrame1);
        barang.setAlwaysOnTop(false);
        barang.setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

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

    private void btnSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSatuanActionPerformed
        akses.setform("DlgCariPenjualan");
        barang.jenis.emptTeks();
        barang.jenis.isCek();
        barang.jenis.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        barang.jenis.setLocationRelativeTo(internalFrame1);
        barang.jenis.setAlwaysOnTop(false);
        barang.jenis.setVisible(true);
    }//GEN-LAST:event_btnSatuanActionPerformed

    private void nmsatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmsatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmsatKeyPressed

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
        NoNota.setText("");
        kdbar.setText("");
        nmbar.setText("");
        kdsat.setText("");
        nmsat.setText("");
        kdmem.setText("");
        nmmem.setText("");
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
                        + tabMode.getValueAt(i, 7).toString() + "','"
                        + tabMode.getValueAt(i, 8).toString() + "','"
                        + tabMode.getValueAt(i, 9).toString() + "','"
                        + tabMode.getValueAt(i, 10).toString() + "','"
                        + tabMode.getValueAt(i, 11).toString() + "','"
                        + tabMode.getValueAt(i, 12).toString() + "','"
                        + tabMode.getValueAt(i, 13).toString() + "','"
                        + tabMode.getValueAt(i, 14).toString() + "','"
                        + tabMode.getValueAt(i, 15).toString() + "','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Penjualan");
            }
            Sequel.menyimpan("temporary", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Penjualan");
            Sequel.menyimpan("temporary", "'0','Jml.Total :','','','','','','','','','','','','','','','" + LTotal.getText() + "','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Pembelian");
            Sequel.AutoComitTrue();

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptPenjualan.jasper", "report", "::[ Transaksi Penjualan Barang ]::",
                    "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);
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

    private void ppCetakNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCetakNotaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbObat.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
        } else {
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim().equals("")) {
                Valid.textKosong(TCari, "No.Nota");
            } else if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                kdbar.requestFocus();
            } else {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("tgl_nota", Sequel.cariIsi("select day(tgl_jual) from penjualan where nota_jual='" + NoNota.getText() + "'") + " "
                        + Sequel.bulanINDONESIA("select month(tgl_jual) from penjualan where nota_jual='" + NoNota.getText() + "'") + " "
                        + Sequel.cariIsi("select year(tgl_jual) from penjualan where nota_jual='" + NoNota.getText() + "'"));
                Valid.MyReport("rptNotaApotek.jasper", "report", "::[ Nota Transaksi Farmasi (Penjualan Bebas) ]::",
                        " SELECT dj.nota_jual, CONCAT(dj.jumlah, ' ', ks.satuan) jlh, db.nama_brng, dj.total, date_format(p.tgl_jual,'%d-%m-%Y') tgl_jual, pt.nama petgas, p.no_rkm_medis, p.nm_pasien,"
                        + "p.keterangan, (select sum(total) from detailjual WHERE nota_jual='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim() + "') tagihan, p.ongkir ppn, "
                        + "(select sum(a.total)+b.ongkir from detailjual a INNER JOIN penjualan b on b.nota_jual=a.nota_jual WHERE a.nota_jual='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim() + "') 'tagihan+ppn' "
                        + "FROM detailjual dj INNER JOIN databarang db INNER JOIN kodesatuan ks INNER JOIN jenis j INNER JOIN penjualan p INNER JOIN petugas pt ON dj.kode_brng = db.kode_brng "
                        + "AND db.kdjns = j.kdjns AND dj.kode_sat = ks.kode_sat AND dj.nota_jual = p.nota_jual AND pt.nip=p.nip "
                        + "WHERE dj.nota_jual = '" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim() + "' ", param);
                
//                Valid.panggilUrl("billing/NotaApotek2.php?nonota=" + tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString().trim());
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppCetakNotaActionPerformed

private void ppHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        TCari.requestFocus();
    } else if (tbObat.getSelectedRow() <= -1) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
    } else {
        if (tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim().equals("")) {
            Valid.textKosong(TCari, "No.Faktur");
        } else {
            try {
                pscarijual = koneksi.prepareStatement(
                        "select nota_jual, kd_bangsal,status from penjualan where nota_jual=?");
                try {
                    pscarijual.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim());
                    rs = pscarijual.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("status").equals("Sudah Dibayar")) {
                            psdetailjual = koneksi.prepareStatement(
                                    "select kode_brng,jumlah from detailjual where nota_jual=? ");
                            try {
                                psdetailjual.setString(1, rs.getString(1));
                                rs2 = psdetailjual.executeQuery();
                                while (rs2.next()) {
                                    Trackobat.catatRiwayat2(rs2.getString("kode_brng"), rs2.getDouble("jumlah"), 0, "Penjualan", akses.getkode(), rs.getString("kd_bangsal"), "Hapus");
                                    Sequel.menyimpan("gudangbarang", "'" + rs2.getString("kode_brng") + "','" + rs.getString("kd_bangsal") + "','" + rs2.getString("jumlah") + "'",
                                            "stok=stok+'" + rs2.getString("jumlah") + "'", "kode_brng='" + rs2.getString("kode_brng") + "' and kd_bangsal='" + rs.getString("kd_bangsal") + "'");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs2 != null) {
                                    rs2.close();
                                }
                                if (psdetailjual != null) {
                                    psdetailjual.close();
                                }
                            }
                            Sequel.queryu("delete from tampjurnal");
                            Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select Penjualan_Obat from set_akun") + "','PENJUALAN','" + Sequel.cariIsi("select sum(total) from detailjual where nota_jual='" + rs.getString("nota_jual") + "'") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select kd_rek from penjualan where nota_jual=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim()) + "','KAS DI TANGAN','0','" + Sequel.cariIsi("select sum(total) from detailjual where nota_jual='" + rs.getString("nota_jual") + "'") + "'", "Rekening");
                            jur.simpanJurnal(rs.getString("nota_jual"), Sequel.cariIsi("select current_date()"), "U", "BATAL PENJUALAN DI " + Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal='" + rs.getString("kd_bangsal") + "'").toUpperCase());
                            Sequel.queryu("delete from tagihan_sadewa where no_nota='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim() + "'");
                        }
                    }
                    Sequel.queryu("delete from penjualan where nota_jual='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim() + "'");
                    tampil();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pscarijual != null) {
                        pscarijual.close();
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}//GEN-LAST:event_ppHapusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void ppVerifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppVerifActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbObat.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
        } else {
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim().equals("")) {
                Valid.textKosong(TCari, "No.Faktur");
            } else {
                try {
                    pscarijual = koneksi.prepareStatement(
                            "select nota_jual, kd_bangsal,status,no_rkm_medis,nm_pasien,tgl_jual from penjualan where nota_jual=?");
                    try {
                        pscarijual.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim());
                        rs = pscarijual.executeQuery();
                        if (rs.next()) {
                            if (rs.getString("status").equals("Sudah Dibayar")) {
                                JOptionPane.showMessageDialog(rootPane, "Maaf transaksi penjualan sudah diverifikasi..!!");
                            } else {
                                psdetailjual = koneksi.prepareStatement(
                                        "select kode_brng,jumlah from detailjual where nota_jual=? ");
                                try {
                                    psdetailjual.setString(1, rs.getString(1));
                                    rs2 = psdetailjual.executeQuery();
                                    while (rs2.next()) {
                                        Trackobat.catatRiwayat2(rs2.getString("kode_brng"), 0, rs2.getDouble("jumlah"), "Penjualan", akses.getkode(), rs.getString("kd_bangsal"), "Simpan");
                                        Sequel.menyimpan("gudangbarang", "'" + rs2.getString("kode_brng") + "','" + rs.getString("kd_bangsal") + "','" + rs2.getString("jumlah") + "'",
                                                "stok=stok-'" + rs2.getString("jumlah") + "'", "kode_brng='" + rs2.getString("kode_brng") + "' and kd_bangsal='" + rs.getString("kd_bangsal") + "'");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi 1 : " + e);
                                } finally {
                                    if (rs2 != null) {
                                        rs2.close();
                                    }
                                    if (psdetailjual != null) {
                                        psdetailjual.close();
                                    }
                                }
                                ttljual = Sequel.cariIsiAngka("select sum(total) from detailjual where nota_jual=?", rs.getString("nota_jual"));
                                Sequel.queryu("delete from tampjurnal");
                                Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select Penjualan_Obat from set_akun") + "','PENJUALAN OBAT BEBAS','0','" + ttljual + "'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select kd_rek from penjualan where nota_jual=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString().trim()) + "','KAS DI TANGAN','" + ttljual + "','0'", "Rekening");
                                jur.simpanJurnal(rs.getString("nota_jual"), Sequel.cariIsi("select current_date()"), "U", "PENJUALAN DI " + Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal='" + rs.getString("kd_bangsal") + "'").toUpperCase());
                                Sequel.mengedit("penjualan", "nota_jual=?", "status='Sudah Dibayar'", 1, new String[]{rs.getString("nota_jual")});
                                Sequel.menyimpan("tagihan_sadewa", "'" + rs.getString("nota_jual") + "','" + rs.getString("no_rkm_medis") + "','" + rs.getString("nm_pasien") + "','-',concat('" + rs.getString("tgl_jual")
                                        + "',' ',CURTIME()),'Pelunasan','" + ttljual + "','" + ttljual + "','Sudah','" + akses.getkode() + "','-'", "No.Nota");
                            }
                        }
                        tampil();
                    } catch (Exception e) {
                        System.out.println("Notifikasi 2 : " + e);
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                        if (pscarijual != null) {
                            pscarijual.close();
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }//GEN-LAST:event_ppVerifActionPerformed

    private void ppVerifAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppVerifAllActionPerformed
        // TODO add your handling code here:
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else {
            for (int j = 0; j < tbObat.getRowCount(); j++) {
                if (tbObat.getValueAt(j, 0).toString().trim().equals("")) {
                    //Valid.textKosong(TCari,"No.Faktur");
                } else {
                    try {
                        pscarijual = koneksi.prepareStatement(
                                "select nota_jual, kd_bangsal,status,no_rkm_medis,nm_pasien,tgl_jual from penjualan where nota_jual=?");
                        try {
                            pscarijual.setString(1, tbObat.getValueAt(j, 0).toString().trim());
                            rs = pscarijual.executeQuery();
                            if (rs.next()) {
                                if (rs.getString("status").equals("Sudah Dibayar")) {
                                    //JOptionPane.showMessageDialog(rootPane, "Maaf transaksi penjualan sudah diverifikasi..!!");
                                } else {
                                    psdetailjual = koneksi.prepareStatement(
                                            "select kode_brng,jumlah from detailjual where nota_jual=? ");
                                    try {
                                        psdetailjual.setString(1, rs.getString(1));
                                        rs2 = psdetailjual.executeQuery();
                                        while (rs2.next()) {
                                            Trackobat.catatRiwayat2(rs2.getString("kode_brng"), 0, rs2.getDouble("jumlah"), "Penjualan", akses.getkode(), rs.getString("kd_bangsal"), "Simpan");
                                            Sequel.menyimpan("gudangbarang", "'" + rs2.getString("kode_brng") + "','" + rs.getString("kd_bangsal") + "','" + rs2.getString("jumlah") + "'",
                                                    "stok=stok-'" + rs2.getString("jumlah") + "'", "kode_brng='" + rs2.getString("kode_brng") + "' and kd_bangsal='" + rs.getString("kd_bangsal") + "'");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notifikasi 1 : " + e);
                                    } finally {
                                        if (rs2 != null) {
                                            rs2.close();
                                        }
                                        if (psdetailjual != null) {
                                            psdetailjual.close();
                                        }
                                    }
                                    ttljual = Sequel.cariIsiAngka("select sum(total) from detailjual where nota_jual=?", rs.getString("nota_jual"));
                                    Sequel.queryu("delete from tampjurnal");
                                    Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select Penjualan_Obat from set_akun") + "','PENJUALAN OBAT BEBAS','0','" + ttljual + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select kd_rek from penjualan where nota_jual=?", tbObat.getValueAt(j, 0).toString().trim()) + "','KAS DI TANGAN','" + ttljual + "','0'", "Rekening");
                                    jur.simpanJurnal(rs.getString("nota_jual"), Sequel.cariIsi("select current_date()"), "U", "PENJUALAN DI " + Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal='" + rs.getString("kd_bangsal") + "'").toUpperCase());
                                    Sequel.mengedit("penjualan", "nota_jual=?", "status='Sudah Dibayar'", 1, new String[]{rs.getString("nota_jual")});
                                    Sequel.menyimpan("tagihan_sadewa", "'" + rs.getString("nota_jual") + "','" + rs.getString("no_rkm_medis") + "','" + rs.getString("nm_pasien") + "','-',concat('" + rs.getString("tgl_jual")
                                            + "',' ',CURTIME()),'Pelunasan','" + ttljual + "','" + ttljual + "','Sudah','" + akses.getkode() + "','-'", "No.Nota");
                                }
                            }
                            
                        } catch (Exception e) {
                            System.out.println("Notifikasi 2 : " + e);
                        } finally {
                            if (rs != null) {
                                rs.close();
                            }
                            if (pscarijual != null) {
                                pscarijual.close();
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }

            }
            tampil();

        }
    }//GEN-LAST:event_ppVerifAllActionPerformed

    private void MnDetailResepPerPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetailResepPerPasienActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs()); 
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptDRJLangsung.jasper","report","::[ Laporan Rekap Detail Penjualan Langsung Resep Per Pasien ]::",
                        " select distinct p.nota_jual,p.tgl_jual,ifnull(r.no_rkm_medis,'-') no_rkm_medis, ifnull(s.nm_pasien, p.keterangan) nm_pasien, g.nama_brng, "+  
                        " j.h_jual, j.jumlah,j.subtotal, j.tambahan, j.total, ifnull((ifnull(d2.nm_dokter, d.nm_dokter)),'-') nm_dokter, ifnull(k.nm_poli,'-')nm_poli, (SELECT MIN(tgl_jual) FROM penjualan "+ 
                        " WHERE penjualan.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"') AS tgl_awal, (SELECT MAX(tgl_jual) FROM penjualan "+ 
                        " WHERE penjualan.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"') AS tgl_akhir "+
                        " from penjualan p inner join detailjual j on j.nota_jual = p.nota_jual "+
                        " inner join databarang g on g.kode_brng = j.kode_brng "+
                        " left join reg_periksa r on r.no_rkm_medis = p.no_rkm_medis and r.tgl_registrasi = p.tgl_jual "+
                        " left join penjab b on b.kd_pj = r.kd_pj "+
                        " left join dokter d on d.kd_dokter = r.kd_dokter "+
                        " left join poliklinik k on k.kd_poli = r.kd_poli "+
                        " left join pasien s on s.no_rkm_medis = r.no_rkm_medis "+
                        " left join dokter d2 on d2.kd_dokter = p.kd_dokter "+        
                        " where p.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"' "+
                        " order by p.nota_jual, p.tgl_jual, no_rkm_medis, g.nama_brng",param);                 
                this.setCursor(Cursor.getDefaultCursor());
        }    
    }//GEN-LAST:event_MnDetailResepPerPasienActionPerformed

    private void MnRekapTotalPerPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapTotalPerPasienActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs()); 
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptTRJPerPasien.jasper","report","::[ Laporan Rekap Total Transaksi Per Pasien ]::",
                    " select distinct p.nota_jual,p.tgl_jual,ifnull(r.no_rkm_medis,'-') no_rkm_medis, ifnull(s.nm_pasien, p.keterangan) nm_pasien, sum(j.jumlah) as T_jumlah, "+ 
                    " sum(j.subtotal) as T_subtotal, sum(j.tambahan) as T_tambahan, sum(j.total) as T_total, ifnull((ifnull(d2.nm_dokter, d.nm_dokter)),'-') nm_dokter, ifnull(k.nm_poli,'-')nm_poli, (SELECT MIN(tgl_jual) FROM penjualan "+ 
                    " WHERE penjualan.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"') AS tgl_awal, (SELECT MAX(tgl_jual) FROM penjualan "+ 
                    " WHERE penjualan.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"') AS tgl_akhir "+
                    " from penjualan p inner join detailjual j on j.nota_jual = p.nota_jual "+
                    " inner join databarang g on g.kode_brng = j.kode_brng "+
                    " left join reg_periksa r on r.no_rkm_medis = p.no_rkm_medis and r.tgl_registrasi = p.tgl_jual "+
                    " left join penjab b on b.kd_pj = r.kd_pj "+
                    " left join dokter d on d.kd_dokter = r.kd_dokter "+
                    " left join poliklinik k on k.kd_poli = r.kd_poli "+
                    " left join pasien s on s.no_rkm_medis = r.no_rkm_medis "+
                    " left join dokter d2 on d2.kd_dokter = p.kd_dokter "+        
                    " where p.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"' "+
                    " GROUP BY p.nota_jual, p.tgl_jual, no_rkm_medis "+
                    " order by p.nota_jual, p.tgl_jual, no_rkm_medis",param);                
                this.setCursor(Cursor.getDefaultCursor());
        } 
    }//GEN-LAST:event_MnRekapTotalPerPasienActionPerformed

    private void MnRekapTotalDokterPerResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapTotalDokterPerResepActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs()); 
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptTRJPerDokter.jasper","report","::[ Laporan Rekap Total Transaksi Dokter Per Resep ]::",
                    " select distinct ifnull((ifnull(d2.nm_dokter, d.nm_dokter)),'-') nm_dokter, sum(j.subtotal) as T_subtotal, sum(j.tambahan) as T_tambahan, sum(j.total) as T_total, "+  
                    " ifnull(k.nm_poli,'-') nm_poli, (SELECT MIN(tgl_jual) FROM penjualan "+ 
                    " WHERE penjualan.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"') AS tgl_awal, (SELECT MAX(tgl_jual) FROM penjualan "+ 
                    " WHERE penjualan.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"') AS tgl_akhir "+
                    " from penjualan p inner join detailjual j on j.nota_jual = p.nota_jual "+
                    " inner join databarang g on g.kode_brng = j.kode_brng "+
                    " left join reg_periksa r on r.no_rkm_medis = p.no_rkm_medis and r.tgl_registrasi = p.tgl_jual "+
                    " left join penjab b on b.kd_pj = r.kd_pj "+
                    " left join dokter d on d.kd_dokter = r.kd_dokter "+
                    " left join poliklinik k on k.kd_poli = r.kd_poli "+
                    " left join pasien s on s.no_rkm_medis = r.no_rkm_medis "+
                    " left join dokter d2 on d2.kd_dokter = p.kd_dokter "+        
                    " where p.tgl_jual BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"' "+
                    " GROUP BY nm_dokter order by nm_dokter, nm_poli",param);               
                this.setCursor(Cursor.getDefaultCursor());
        } 
    }//GEN-LAST:event_MnRekapTotalDokterPerResepActionPerformed

    private void ppCetakNotaAdminFarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCetakNotaAdminFarActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs()); 
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                param.put("tgl_nota", Sequel.cariIsi("select day(now())") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select year(now())"));
                Valid.MyReport("rptNJLAdminFarmasi.jasper","report","::[ Cetak Nota/Kwitansi Resep Pasien dari Penjualan Langsung (untuk Admin Farmasi) ]::",
                    " select penjualan.nota_jual, penjualan.tgl_jual, databarang.nama_brng, databarang.kode_sat, databarang.ralan as hrg_jual, detailjual.jumlah, "+  
                    " detailjual.subtotal, detailjual.bsr_dis as diskon, detailjual.dis as diskon_persen, detailjual.tambahan as tuslah, "+ 
                    " detailjual.total as total_tran, petugas.nama as nm_petugas, penjualan.no_rkm_medis,penjualan.nm_pasien, "+ 
                    " penjualan.keterangan, penjualan.jns_jual, bangsal.nm_bangsal,penjualan.status, dokter.nm_dokter "+ 
                    " from penjualan inner join petugas inner join bangsal inner join jenis "+ 
                    " inner join detailjual inner join databarang inner join kodesatuan "+ 
                    " on detailjual.kode_brng=databarang.kode_brng "+ 
                    " and detailjual.kode_sat=kodesatuan.kode_sat "+ 
                    " and penjualan.kd_bangsal=bangsal.kd_bangsal "+ 
                    " and penjualan.nota_jual=detailjual.nota_jual "+ 
                    " and penjualan.nip=petugas.nip and databarang.kdjns=jenis.kdjns "+ 
                    " INNER JOIN dokter ON penjualan.kd_dokter = dokter.kd_dokter "+
                    " where penjualan.nota_jual='"+NoNota.getText()+"' "+ 
                    " order by penjualan.tgl_jual,penjualan.nota_jual",param);               
                this.setCursor(Cursor.getDefaultCursor());
        }    
    }//GEN-LAST:event_ppCetakNotaAdminFarActionPerformed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        no_nota = "";
        kd_obat = "";
        if (tbObat.getRowCount() != 0) {
            try {
                if (tbObat.getSelectedRow() != -1) {
                    NoNota.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    kdmem.setText("");
                    nmmem.setText("");
                    kdptg.setText("");
                    nmptg.setText("");
                    no_nota = tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString();
                    kd_obat = tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString();
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void ppAturanPakaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppAturanPakaiActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (no_nota.equals("") && kd_obat.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu nama obatnya pada tabel...!!!!");
            tbObat.requestFocus();
        } else {
            DlgGantiAturanPakai aturan = new DlgGantiAturanPakai(null, false);
            aturan.setSize(644, 316);
            aturan.setLocationRelativeTo(internalFrame1);
            aturan.setData(no_nota, kd_obat, 
                    Sequel.cariIsi("SELECT p.tgl_jual FROM penjualan p INNER JOIN detailjual dj on p.nota_jual = dj.nota_jual WHERE dj.nota_jual='" + no_nota + "'"), 
                    Sequel.cariIsi("select time(now())"), "jual_bebas");
            aturan.setVisible(true);
        }
    }//GEN-LAST:event_ppAturanPakaiActionPerformed

    private void ppLabelObatMinumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLabelObatMinumActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (no_nota.equals("") && kd_obat.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu nama obatnya pada tabel...!!!!");
            tbObat.requestFocus();
        } else if (Sequel.cariInteger("select count(*) from aturan_pakai_jual_bebas where no_nota='" + no_nota + "' and kode_brng='" + kd_obat + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Aturan pakai utk. obat " + tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString() + " belum disimpan...!!!!");
            tbObat.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("logo", Sequel.cariGambar("select logo_hitam_putih from setting"));
            param.put("jns_label", "");
//            Valid.MyReport("rptAturanPakaiBebas.jasper", "report", "::[ Labeling Obat Minum ]::",
//                    "select ap.no_nota, ap.kode_brng, concat(ifnull(pj.no_rkm_medis,''),' - ',pj.nm_pasien) pasien, "
//                    + "date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, "
//                    + "ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, ap.jam "
//                    + "FROM aturan_pakai_jual_bebas ap INNER JOIN databarang d ON d.kode_brng = ap.kode_brng "
//                    + "INNER JOIN penjualan pj ON pj.nota_jual = ap.no_nota where "
//                    + "ap.no_nota='" + no_nota + "' and ap.kode_brng='" + kd_obat + "'", param);

            Valid.AutoPrintMulti("rptAturanPakaiBebas.jasper", "report", "::[ Labeling Obat Minum ]::",
                    "select ap.no_nota, ap.kode_brng, concat(ifnull(pj.no_rkm_medis,''),' - ',pj.nm_pasien) pasien, "
                    + "date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, "
                    + "ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, ap.jam "
                    + "FROM aturan_pakai_jual_bebas ap INNER JOIN databarang d ON d.kode_brng = ap.kode_brng "
                    + "INNER JOIN penjualan pj ON pj.nota_jual = ap.no_nota where "
                    + "ap.no_nota='" + no_nota + "' and ap.kode_brng='" + kd_obat + "'", param, nmPrinter1);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppLabelObatMinumActionPerformed

    private void ppLabelObatLuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLabelObatLuarActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (no_nota.equals("") && kd_obat.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu nama obatnya pada tabel...!!!!");
            tbObat.requestFocus();
        } else if (Sequel.cariInteger("select count(*) from aturan_pakai_jual_bebas where no_nota='" + no_nota + "' and kode_brng='" + kd_obat + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Aturan pakai utk. obat " + tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString() + " belum disimpan...!!!!");
            tbObat.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("logo", Sequel.cariGambar("select logo_hitam_putih from setting"));
            param.put("jns_label", "OBAT LUAR");
//            Valid.MyReport("rptAturanPakaiBebas.jasper", "report", "::[ Labeling Obat Luar ]::",
//                    "select ap.no_nota, ap.kode_brng, concat(ifnull(pj.no_rkm_medis,''),' - ',pj.nm_pasien) pasien, "
//                    + "date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, "
//                    + "ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, ap.jam "
//                    + "FROM aturan_pakai_jual_bebas ap INNER JOIN databarang d ON d.kode_brng = ap.kode_brng "
//                    + "INNER JOIN penjualan pj ON pj.nota_jual = ap.no_nota where "
//                    + "ap.no_nota = '" + no_nota + "' AND ap.kode_brng = '" + kd_obat + "'", param);

            Valid.AutoPrintMulti("rptAturanPakaiBebas.jasper", "report", "::[ Labeling Obat Luar ]::",
                    "select ap.no_nota, ap.kode_brng, concat(ifnull(pj.no_rkm_medis,''),' - ',pj.nm_pasien) pasien, "
                    + "date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, "
                    + "ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, ap.jam "
                    + "FROM aturan_pakai_jual_bebas ap INNER JOIN databarang d ON d.kode_brng = ap.kode_brng "
                    + "INNER JOIN penjualan pj ON pj.nota_jual = ap.no_nota where "
                    + "ap.no_nota = '" + no_nota + "' AND ap.kode_brng = '" + kd_obat + "'", param, nmPrinter2);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppLabelObatLuarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPenjualan dialog = new DlgCariPenjualan(new javax.swing.JFrame(), true);
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
    private widget.Label LTotal;
    private javax.swing.JMenuItem MnDetailResepPerPasien;
    private javax.swing.JMenu MnLaporanJual;
    private javax.swing.JMenuItem MnRekapTotalDokterPerResep;
    private javax.swing.JMenuItem MnRekapTotalPerPasien;
    private widget.TextBox NoNota;
    private widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnBarang;
    private widget.Button btnPasien;
    private widget.Button btnPetugas;
    private widget.Button btnSatuan;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdbar;
    private widget.TextBox kdmem;
    private widget.TextBox kdptg;
    private widget.TextBox kdsat;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label13;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label24;
    private widget.Label label9;
    private widget.TextBox nmbar;
    private widget.TextBox nmmem;
    private widget.TextBox nmptg;
    private widget.TextBox nmsat;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppAturanPakai;
    private javax.swing.JMenuItem ppCetakNota;
    private javax.swing.JMenuItem ppCetakNotaAdminFar;
    private javax.swing.JMenuItem ppHapus;
    private javax.swing.JMenuItem ppLabelObatLuar;
    private javax.swing.JMenuItem ppLabelObatMinum;
    private javax.swing.JMenuItem ppVerif;
    private javax.swing.JMenuItem ppVerifAll;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        tanggal = "  penjualan.tgl_jual between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' ";
        nofak = "";
        mem = "";
        ptg = "";
        sat = "";
        bar = "";
        if (!NoNota.getText().equals("")) {
            nofak = " and penjualan.nota_jual='" + NoNota.getText() + "' ";
        }
        if (!nmmem.getText().equals("")) {
            mem = " and penjualan.nm_pasien='" + nmmem.getText() + "' ";
        }
        if (!nmptg.getText().equals("")) {
            ptg = " and petugas.nama='" + nmptg.getText() + "' ";
        }
        if (!nmsat.getText().equals("")) {
            sat = " and jenis.nama='" + nmsat.getText() + "' ";
        }
        if (!nmbar.getText().equals("")) {
            bar = " and databarang.nama_brng='" + nmbar.getText() + "' ";
        }

        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select penjualan.nota_jual, penjualan.tgl_jual, "
                    + "penjualan.nip,petugas.nama, "
                    + "penjualan.no_rkm_medis,penjualan.nm_pasien, "
                    + "penjualan.keterangan, penjualan.jns_jual, penjualan.ongkir,bangsal.nm_bangsal,penjualan.status "
                    + " from penjualan inner join petugas inner join bangsal inner join jenis  "
                    + " inner join detailjual inner join databarang inner join kodesatuan "
                    + " on detailjual.kode_brng=databarang.kode_brng "
                    + " and detailjual.kode_sat=kodesatuan.kode_sat "
                    + " and penjualan.kd_bangsal=bangsal.kd_bangsal "
                    + " and penjualan.nota_jual=detailjual.nota_jual "
                    + " and penjualan.nip=petugas.nip and databarang.kdjns=jenis.kdjns "
                    + " where " + tanggal + nofak + mem + ptg + sat + bar + " and penjualan.nota_jual like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and penjualan.no_rkm_medis like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and penjualan.nm_pasien like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and penjualan.nip like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and petugas.nama like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and penjualan.keterangan like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and penjualan.jns_jual like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and detailjual.kode_brng like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and bangsal.nm_bangsal like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and databarang.nama_brng like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and detailjual.kode_sat like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and jenis.nama like '%" + TCari.getText() + "%' or "
                    + tanggal + nofak + mem + ptg + sat + bar + " and penjualan.status like '%" + TCari.getText() + "%' "
                    + " group by penjualan.nota_jual order by penjualan.tgl_jual,penjualan.nota_jual ");
            try {
                rs = ps.executeQuery();
                ttljual = 0;
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString(1), rs.getString(2), rs.getString(3) + ", " + rs.getString(4),
                        rs.getString(5) + ", " + rs.getString(6), rs.getString(7), rs.getString(8),
                        df2.format(rs.getDouble(9)), "Penjualan di " + rs.getString(10) + " :", "", "", "", "", "", "", "", "", "", "", ""
                    });
                    ps2 = koneksi.prepareStatement("select detailjual.kode_brng,databarang.nama_brng, detailjual.kode_sat,"
                            + " kodesatuan.satuan,detailjual.h_jual, detailjual.jumlah, "
                            + " detailjual.subtotal, detailjual.dis, detailjual.bsr_dis,detailjual.tambahan,detailjual.total from "
                            + " detailjual inner join databarang inner join kodesatuan inner join jenis "
                            + " on detailjual.kode_brng=databarang.kode_brng and databarang.kdjns=jenis.kdjns "
                            + " and detailjual.kode_sat=kodesatuan.kode_sat where "
                            + " detailjual.nota_jual='" + rs.getString(1) + "' " + sat + bar + " or "
                            + " detailjual.nota_jual='" + rs.getString(1) + "' " + sat + bar + " and detailjual.kode_brng like '%" + TCari.getText() + "%' or "
                            + " detailjual.nota_jual='" + rs.getString(1) + "' " + sat + bar + " and databarang.nama_brng like '%" + TCari.getText() + "%' or "
                            + " detailjual.nota_jual='" + rs.getString(1) + "' " + sat + bar + " and detailjual.kode_sat like '%" + TCari.getText() + "%' or "
                            + " detailjual.nota_jual='" + rs.getString(1) + "' " + sat + bar + " and jenis.nama like '%" + TCari.getText() + "%' order by detailjual.kode_brng");                    
                    
                    try {
                        rs2 = ps2.executeQuery();
                        subttlall = 0;
                        subttldisc = 0;
                        subttljual = 0;
                        subttltambahan = 0;
                        no = 1;
                        while (rs2.next()) {
                            subttlall = subttlall + rs2.getDouble(7);
                            subttldisc = subttldisc + rs2.getDouble(9);
                            subttltambahan = subttltambahan + rs2.getDouble(10);
                            ttljual = ttljual + rs2.getDouble(11);
                            subttljual = subttljual + rs2.getDouble(11);
                            tabMode.addRow(new String[]{
                                "", "", "", "", "", "", "", no + ". " + rs2.getString(2), rs2.getString(3) + ", " + rs2.getString(4),
                                df2.format(rs2.getDouble(5)), rs2.getString(6), df2.format(rs2.getDouble(7)),
                                df2.format(rs2.getDouble(8)), df2.format(rs2.getDouble(9)), df2.format(rs2.getDouble(10)),
                                df2.format(rs2.getDouble(11)), rs.getString(1), rs2.getString(1), rs2.getString(2)
                            });
                            no++;
                        }
                        tabMode.addRow(new String[]{
                            "", "Status : ", rs.getString("status"), "", "", "", "", "Total :", "", "", "", df2.format(subttlall), "",
                            df2.format(subttldisc), df2.format(subttltambahan), df2.format(subttljual), "", "", ""
                        });
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
            LTotal.setText(df2.format(ttljual));
        } catch (Exception e) {
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
        BtnPrint.setEnabled(akses.getpenjualan_obat());
        ppCetakNota.setEnabled(akses.getpenjualan_obat());
        if (akses.getkode().equals("Admin Utama")) {
            ppHapus.setEnabled(true);
        } else {
            ppHapus.setEnabled(false);
        }
    }
}
