package keuangan;

import rekammedis.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author dosen
 */
public class DlgJaminanTransaksi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int i = 0, x = 0;
    private String cekBatal = "", jumlahNom = "", kodePJ = "", tte = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgJaminanTransaksi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Perawatan/Inst.", "Cara Bayar", "Jenis Jaminan", "Nama Pemberi Jaminan", "No. Telp.", 
            "Tgl. Terima", "Petugas Menerima", "Keterangan", "Jml. Nominal", 
            "tgl_terima", "nip_penerima", "jumlah_nominal", "Jaminan Batal", "Alasan Pembatalan", "tglTer", "waktu_simpan", "kd_pj"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbJaminan.setModel(tabMode);
        tbJaminan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJaminan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 20; i++) {
            TableColumn column = tbJaminan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(230);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(110);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setPreferredWidth(80);
            } else if (i == 16) {
                column.setPreferredWidth(220);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbJaminan.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbJaminan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbJaminan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbJaminan.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbJaminan.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Hapus", "Jam Hapus", "Ket./Alasan Hapus", "waktu_hapus"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode1);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(220);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRiwayat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbRiwayat.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);        
        tbRiwayat.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbRiwayat.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TnmPemberi.setDocument(new batasInput((int) 150).getKata(TnmPemberi));
        TnoTelp.setDocument(new batasInput((byte) 17).getOnlyAngka(TnoTelp));
        TJmlNominal.setDocument(new batasInput((byte) 9).getOnlyAngka(TJmlNominal));
        TketHapus.setDocument(new batasInput((int) 200).getKata(TketHapus));
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    TnipMenerima.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    TnmMenerima.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    btnPetugas.requestFocus();
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
        MnCetakKuitansi = new javax.swing.JMenu();
        MnTTDkuitansi = new javax.swing.JMenuItem();
        MnTTEkuitansi = new javax.swing.JMenuItem();
        MnPanjar = new javax.swing.JMenuItem();
        MnHapus = new javax.swing.JMenuItem();
        MnDataSampah = new javax.swing.JMenuItem();
        WindowHapus = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel26 = new widget.Label();
        TketHapus = new widget.TextBox();
        panelisi4 = new widget.panelisi();
        BtnHapus1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowRiwayat = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel101 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel102 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel103 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel104 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        BtnAll2 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn12 = new widget.Button();
        Scroll6 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel65 = new widget.Label();
        cmbJnsJaminan = new widget.ComboBox();
        jLabel66 = new widget.Label();
        TnmPemberi = new widget.TextBox();
        jLabel67 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel68 = new widget.Label();
        TtglTerima = new widget.Tanggal();
        jLabel69 = new widget.Label();
        TnipMenerima = new widget.TextBox();
        TnmMenerima = new widget.TextBox();
        jLabel70 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        Tketerangan = new widget.TextArea();
        jLabel71 = new widget.Label();
        TJmlNominal = new widget.TextBox();
        labelNominal = new widget.Label();
        jLabel72 = new widget.Label();
        btnPetugas = new widget.Button();
        ChkJaminanBatal = new widget.CekBox();
        scrollPane10 = new widget.ScrollPane();
        TalasanBatal = new widget.TextArea();
        jLabel64 = new widget.Label();
        TcaraBayar = new widget.TextBox();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJaminan = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakKuitansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakKuitansi.setText("Cetak Kuitansi Jaminan");
        MnCetakKuitansi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKuitansi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakKuitansi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakKuitansi.setIconTextGap(5);
        MnCetakKuitansi.setName("MnCetakKuitansi"); // NOI18N
        MnCetakKuitansi.setOpaque(true);
        MnCetakKuitansi.setPreferredSize(new java.awt.Dimension(170, 26));

        MnTTDkuitansi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTDkuitansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnTTDkuitansi.setText("TTD Basah");
        MnTTDkuitansi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTDkuitansi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTDkuitansi.setIconTextGap(5);
        MnTTDkuitansi.setName("MnTTDkuitansi"); // NOI18N
        MnTTDkuitansi.setPreferredSize(new java.awt.Dimension(120, 26));
        MnTTDkuitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTDkuitansiActionPerformed(evt);
            }
        });
        MnCetakKuitansi.add(MnTTDkuitansi);

        MnTTEkuitansi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTEkuitansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnTTEkuitansi.setText("TTE (QRCode)");
        MnTTEkuitansi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTEkuitansi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTEkuitansi.setIconTextGap(5);
        MnTTEkuitansi.setName("MnTTEkuitansi"); // NOI18N
        MnTTEkuitansi.setPreferredSize(new java.awt.Dimension(120, 26));
        MnTTEkuitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTEkuitansiActionPerformed(evt);
            }
        });
        MnCetakKuitansi.add(MnTTEkuitansi);

        jPopupMenu1.add(MnCetakKuitansi);

        MnPanjar.setBackground(new java.awt.Color(242, 242, 242));
        MnPanjar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPanjar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        MnPanjar.setText("Jaminan Ke Panjar");
        MnPanjar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPanjar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPanjar.setIconTextGap(5);
        MnPanjar.setName("MnPanjar"); // NOI18N
        MnPanjar.setPreferredSize(new java.awt.Dimension(170, 26));
        MnPanjar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPanjarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPanjar);

        MnHapus.setBackground(new java.awt.Color(242, 242, 242));
        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapus.setText("Hapus Jaminan");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(170, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapus);

        MnDataSampah.setBackground(new java.awt.Color(242, 242, 242));
        MnDataSampah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataSampah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataSampah.setText("Data Sampah");
        MnDataSampah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataSampah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataSampah.setIconTextGap(5);
        MnDataSampah.setName("MnDataSampah"); // NOI18N
        MnDataSampah.setPreferredSize(new java.awt.Dimension(170, 26));
        MnDataSampah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataSampahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataSampah);

        WindowHapus.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowHapus.setName("WindowHapus"); // NOI18N
        WindowHapus.setUndecorated(true);
        WindowHapus.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Hapus Jaminan Transaksi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi3.setLayout(null);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Keterangan / Alasan :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelisi3.add(jLabel26);
        jLabel26.setBounds(0, 10, 130, 23);

        TketHapus.setForeground(new java.awt.Color(0, 0, 0));
        TketHapus.setName("TketHapus"); // NOI18N
        TketHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketHapusKeyPressed(evt);
            }
        });
        panelisi3.add(TketHapus);
        TketHapus.setBounds(135, 10, 500, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        panelisi4.add(BtnHapus1);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCloseIn1);

        internalFrame3.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowHapus.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Jaminan Transaksi Dihapus ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        internalFrame18.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame17.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Tgl. Dihapus :");
        jLabel101.setName("jLabel101"); // NOI18N
        jLabel101.setPreferredSize(new java.awt.Dimension(80, 23));
        internalFrame17.add(jLabel101);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2025" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("s.d.");
        jLabel102.setName("jLabel102"); // NOI18N
        jLabel102.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel102);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2025" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Key Word :");
        jLabel103.setName("jLabel103"); // NOI18N
        jLabel103.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel103);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+1");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(BtnCari2);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Record :");
        jLabel104.setName("jLabel104"); // NOI18N
        jLabel104.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame17.add(jLabel104);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame17.add(LCount1);

        internalFrame18.add(internalFrame17, java.awt.BorderLayout.CENTER);

        internalFrame19.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('2');
        BtnAll2.setText("Semua Data");
        BtnAll2.setToolTipText("Alt+2");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnAll2);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor.setMnemonic('U');
        BtnRestor.setText("Restore");
        BtnRestor.setToolTipText("Alt+U");
        BtnRestor.setName("BtnRestor"); // NOI18N
        BtnRestor.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnRestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestorActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnRestor);

        BtnCloseIn12.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn12.setMnemonic('U');
        BtnCloseIn12.setText("Tutup");
        BtnCloseIn12.setToolTipText("Alt+U");
        BtnCloseIn12.setName("BtnCloseIn12"); // NOI18N
        BtnCloseIn12.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn12ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn12);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        tbRiwayat.getTableHeader().setReorderingAllowed(false);
        Scroll6.setViewportView(tbRiwayat);

        internalFrame13.add(Scroll6, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Jaminan Transaksi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

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
        panelGlass8.add(BtnAll);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Terima :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
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
        panelGlass10.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 363));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 120, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(125, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(334, 10, 410, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat/Inst. :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 120, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(125, 38, 619, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Jenis Jaminan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(535, 66, 85, 23);

        cmbJnsJaminan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsJaminan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kartu Identitas", "Nominal Uang", "Surat Berharga", "Barang Elektronik", "Lainnya" }));
        cmbJnsJaminan.setName("cmbJnsJaminan"); // NOI18N
        cmbJnsJaminan.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbJnsJaminan);
        cmbJnsJaminan.setBounds(625, 66, 115, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Nama Pemberi Jaminan :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 94, 140, 23);

        TnmPemberi.setForeground(new java.awt.Color(0, 0, 0));
        TnmPemberi.setName("TnmPemberi"); // NOI18N
        TnmPemberi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmPemberiKeyPressed(evt);
            }
        });
        FormInput.add(TnmPemberi);
        TnmPemberi.setBounds(145, 94, 390, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("No. Telp./HP :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(535, 94, 85, 23);

        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        TnoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelpKeyPressed(evt);
            }
        });
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(625, 94, 115, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tgl. Terima :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 122, 120, 23);

        TtglTerima.setEditable(false);
        TtglTerima.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2025" }));
        TtglTerima.setDisplayFormat("dd-MM-yyyy");
        TtglTerima.setName("TtglTerima"); // NOI18N
        TtglTerima.setOpaque(false);
        TtglTerima.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTerima);
        TtglTerima.setBounds(125, 122, 90, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Petugas Menerima :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 150, 120, 23);

        TnipMenerima.setEditable(false);
        TnipMenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnipMenerima.setName("TnipMenerima"); // NOI18N
        FormInput.add(TnipMenerima);
        TnipMenerima.setBounds(125, 150, 170, 23);

        TnmMenerima.setEditable(false);
        TnmMenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnmMenerima.setName("TnmMenerima"); // NOI18N
        FormInput.add(TnmMenerima);
        TnmMenerima.setBounds(298, 150, 445, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Keterangan :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 178, 120, 23);

        scrollPane9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane9.setName("scrollPane9"); // NOI18N

        Tketerangan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tketerangan.setColumns(20);
        Tketerangan.setRows(5);
        Tketerangan.setName("Tketerangan"); // NOI18N
        Tketerangan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tketerangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketeranganKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(Tketerangan);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(125, 178, 615, 70);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Jumlah Nominal :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(215, 122, 100, 23);

        TJmlNominal.setForeground(new java.awt.Color(0, 0, 0));
        TJmlNominal.setName("TJmlNominal"); // NOI18N
        TJmlNominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TJmlNominalActionPerformed(evt);
            }
        });
        TJmlNominal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJmlNominalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TJmlNominalKeyReleased(evt);
            }
        });
        FormInput.add(TJmlNominal);
        TJmlNominal.setBounds(320, 122, 131, 23);

        labelNominal.setForeground(new java.awt.Color(0, 0, 0));
        labelNominal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNominal.setText("0");
        labelNominal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNominal.setName("labelNominal"); // NOI18N
        FormInput.add(labelNominal);
        labelNominal.setBounds(457, 122, 390, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Alasan Pembatalan :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 281, 120, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('1');
        btnPetugas.setToolTipText("Alt+1");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(745, 150, 28, 23);

        ChkJaminanBatal.setBackground(new java.awt.Color(255, 255, 250));
        ChkJaminanBatal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJaminanBatal.setForeground(new java.awt.Color(0, 0, 0));
        ChkJaminanBatal.setText("Jaminan Dibatalkan");
        ChkJaminanBatal.setBorderPainted(true);
        ChkJaminanBatal.setBorderPaintedFlat(true);
        ChkJaminanBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJaminanBatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJaminanBatal.setName("ChkJaminanBatal"); // NOI18N
        ChkJaminanBatal.setOpaque(false);
        ChkJaminanBatal.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkJaminanBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJaminanBatalActionPerformed(evt);
            }
        });
        FormInput.add(ChkJaminanBatal);
        ChkJaminanBatal.setBounds(125, 253, 130, 23);

        scrollPane10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane10.setName("scrollPane10"); // NOI18N

        TalasanBatal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TalasanBatal.setColumns(20);
        TalasanBatal.setRows(5);
        TalasanBatal.setName("TalasanBatal"); // NOI18N
        TalasanBatal.setPreferredSize(new java.awt.Dimension(162, 2000));
        TalasanBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanBatalKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(TalasanBatal);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(125, 281, 615, 70);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Cara Bayar :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 120, 23);

        TcaraBayar.setEditable(false);
        TcaraBayar.setForeground(new java.awt.Color(0, 0, 0));
        TcaraBayar.setName("TcaraBayar"); // NOI18N
        FormInput.add(TcaraBayar);
        TcaraBayar.setBounds(125, 66, 410, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJaminan.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbJaminan.setComponentPopupMenu(jPopupMenu1);
        tbJaminan.setName("tbJaminan"); // NOI18N
        tbJaminan.getTableHeader().setReorderingAllowed(false);
        tbJaminan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJaminanMouseClicked(evt);
            }
        });
        tbJaminan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJaminanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbJaminan);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (cmbJnsJaminan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu jenis jaminannya..!!");
            cmbJnsJaminan.requestFocus();
        } else if (TnipMenerima.getText().equals("") || TnipMenerima.getText().equals("-") || TnipMenerima.getText().equals("--")) {
            JOptionPane.showMessageDialog(null, "Petugas yang menerima jaminan transaksi harus diisi dulu..!!");
            btnPetugas.requestFocus();
        } else if (TJmlNominal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jumlah nominal jika tidak ada isi dengan 0 ..!!");
            TJmlNominal.requestFocus();
        } else {
            cekBatal = "";
            if (ChkJaminanBatal.isSelected() == true) {
                cekBatal = "Ya";
            } else {
                cekBatal = "Tidak";
            }
            
            if (Sequel.menyimpantf("jaminan_transaksi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 15, new String[]{
                TNoRw.getText(), TrgRawat.getText(), TnmPemberi.getText(), TnoTelp.getText(), cmbJnsJaminan.getSelectedItem().toString(),
                Valid.SetTgl(TtglTerima.getSelectedItem() + "") + " " + Sequel.cariIsi("select time(now())"), TnipMenerima.getText(),
                Tketerangan.getText(), TJmlNominal.getText(), "0000-00-00 00:00:00", "-", "-", cekBatal, TalasanBatal.getText(),
                Sequel.cariIsi("select now()")
            }) == true) {
                Valid.SetTgl(DTPCari1, Valid.SetTgl(TtglTerima.getSelectedItem() + ""));
                BtnBatalActionPerformed(null);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tbJaminan.getSelectedRow() > -1) {
            if (cmbJnsJaminan.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Pilih dulu salah satu jenis jaminannya..!!");
                cmbJnsJaminan.requestFocus();
            } else if (TnipMenerima.getText().equals("") || TnipMenerima.getText().equals("-") || TnipMenerima.getText().equals("--")) {
                JOptionPane.showMessageDialog(null, "Petugas yang menerima jaminan transaksi harus diisi dulu..!!");
                btnPetugas.requestFocus();
            } else if (TJmlNominal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Jumlah nominal jika tidak ada isi dengan 0 ..!!");
                TJmlNominal.requestFocus();
            } else if (ChkJaminanBatal.isSelected() == true && TalasanBatal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Alasan pembatalan jaminan harus diisi dulu..!!");
                TalasanBatal.requestFocus();
            } else if (Sequel.cariInteger("select count(-1) from jaminan_transaksi where no_rawat='" + TNoRw.getText() + "' and date(tgl_dikembalikan)<>'0000-00-00'") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, jaminan transaksi sudah dilakukan pengembalian, data tidak bisa diperbaiki..!!");
                BtnBatalActionPerformed(null);
            } else {
                cekBatal = "";
                if (ChkJaminanBatal.isSelected() == true) {
                    cekBatal = "Ya";
                } else {
                    cekBatal = "Tidak";
                }

                if (tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 13).toString().equals(akses.getkode()) || akses.getadmin() == true) {
                    if (Sequel.mengedittf("jaminan_transaksi", "no_rawat=?", "nm_pemberi_jaminan=?, no_telp=?, jenis_jaminan=?, tgl_terima=?, "
                            + "nip_penerima=?, keterangan=?, jumlah_nominal=?, jaminan_batal=?, alasan_pembatalan=?", 10, new String[]{
                                TnmPemberi.getText(), TnoTelp.getText(), cmbJnsJaminan.getSelectedItem().toString(),
                                Valid.SetTgl(TtglTerima.getSelectedItem() + "") + " " + Sequel.cariIsi("select time(now())"), TnipMenerima.getText(),
                                Tketerangan.getText(), TJmlNominal.getText(), cekBatal, TalasanBatal.getText(),
                                tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 0).toString()
                            }) == true) {
                        Valid.SetTgl(DTPCari1, Valid.SetTgl(TtglTerima.getSelectedItem() + ""));
                        BtnBatalActionPerformed(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Data jaminan ini cuma bisa diperbaiki oleh " + tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 9).toString() + ", karena dia yang menerima..!!");
                    tampil();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tampil();
            tbJaminan.requestFocus();
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowHapus.dispose();
        WindowRiwayat.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
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
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbJaminanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJaminanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbJaminanMouseClicked

    private void tbJaminanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJaminanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbJaminanKeyPressed

    private void TketeranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketeranganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkJaminanBatal.requestFocus();
        }
    }//GEN-LAST:event_TketeranganKeyPressed

    private void TJmlNominalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TJmlNominalActionPerformed
        if (TJmlNominal.getText().trim().equals("")) {
            labelNominal.setText("Rp. 0");
        } else {
            labelNominal.setText("Rp. " + Valid.SetAngka(Double.parseDouble(TJmlNominal.getText())));
        }
    }//GEN-LAST:event_TJmlNominalActionPerformed

    private void TJmlNominalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJmlNominalKeyReleased
        if (TJmlNominal.getText().trim().equals("")) {
            labelNominal.setText("Rp. 0");
        } else {
            labelNominal.setText("Rp. " + Valid.SetAngka(Double.parseDouble(TJmlNominal.getText())));
        }
    }//GEN-LAST:event_TJmlNominalKeyReleased

    private void TnmPemberiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmPemberiKeyPressed
        Valid.pindah(evt, TnmPemberi, TnoTelp);
    }//GEN-LAST:event_TnmPemberiKeyPressed

    private void TnoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelpKeyPressed
        Valid.pindah(evt, TnmPemberi, TtglTerima);
    }//GEN-LAST:event_TnoTelpKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgJaminanTransaksi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void TalasanBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnGanti.requestFocus();
        }
    }//GEN-LAST:event_TalasanBatalKeyPressed

    private void ChkJaminanBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJaminanBatalActionPerformed
        TalasanBatal.setText("");
        if (Sequel.cariInteger("select count(-1) from jaminan_transaksi where no_rawat='" + TNoRw.getText() + "' and tgl_dikembalikan<>'0000-00-00 00:00:00'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, jaminan transaksi sudah dikembalikan, jaminan tidak bisa dibatalkan..!!");
            BtnBatalActionPerformed(null);
        } else {
            if (ChkJaminanBatal.isSelected() == true) {
                TalasanBatal.setEnabled(true);
                TalasanBatal.requestFocus();
            } else {
                TalasanBatal.setEnabled(false);
            }
        }
    }//GEN-LAST:event_ChkJaminanBatalActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbJaminan.getSelectedRow() > -1) {
            WindowHapus.setSize(692, 121);
            WindowHapus.setLocationRelativeTo(internalFrame1);
            WindowHapus.setVisible(true);
            TketHapus.setText("");
            TketHapus.requestFocus();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbJaminan.requestFocus();
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (Sequel.cariInteger("select count(-1) from jaminan_transaksi where "
                + "date(tgl_terima) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data jaminan transaksi tidak ditemukan..!!!!");
            BtnBatalActionPerformed(null);
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TANGGAL " + DTPCari1.getSelectedItem() + " S.D " + DTPCari2.getSelectedItem());
            param.put("judul", "LAPORAN JAMINAN TRANSAKSI YANG DITERIMA");
            Valid.MyReport("rptLaporanJaminanDiterima.jasper", "report", "::[ Laporan Penerimaan Jaminan Transaksi ]::",
                    "SELECT jt.*, p.no_rkm_medis, p.nm_pasien, date_format(jt.tgl_terima,'%d-%m-%Y\n%H:%i Wita') tglTerima, pg.nama petugasMenerima, "
                    + "if(jt.jumlah_nominal='0','-',format(jt.jumlah_nominal,0)) jmlNominal, date_format(jt.tgl_dikembalikan,'%d-%m-%Y, %H:%i') tglDikembalikan, date(jt.tgl_terima) tglTer "
                    + "FROM jaminan_transaksi jt inner join reg_periksa rp on rp.no_rawat =jt.no_rawat inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=jt.nip_penerima where "
                    + "date(jt.tgl_terima) between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "order by jt.tgl_terima", param);

            BtnBatalActionPerformed(null);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void MnPanjarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPanjarActionPerformed
        if (tbJaminan.getSelectedRow() > -1) {
            if (kodePJ.equals("B01")) {
                if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ranap'") > 0) {
                    if (Sequel.cariSelisihTarifInacbg(TNoRw.getText()) > 0) {
                        keTransaksiPanjar();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Hitungan biaya selisih tarif INACBG belum tersimpan..!!");
                        tampil();
                    }
                } else {
                    if (Sequel.cariRealCostPiutang(TNoRw.getText()) > 0) {
                        keTransaksiPanjar();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Hitungan biaya tagihan sesuai Real Cost piutang belum tersimpan..!!");
                        tampil();
                    }
                }
            } else {
                if (Sequel.cariRealCostPiutang(TNoRw.getText()) > 0 || Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                    keTransaksiPanjar();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Hitungan biaya tagihan sesuai Real Cost belum tersimpan..!!");
                    tampil();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tampil();
            tbJaminan.requestFocus();
        }
    }//GEN-LAST:event_MnPanjarActionPerformed

    private void TJmlNominalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJmlNominalKeyPressed
        Valid.pindah(evt, TtglTerima, btnPetugas);
    }//GEN-LAST:event_TJmlNominalKeyPressed

    private void TketHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnHapus1.requestFocus();
        }
    }//GEN-LAST:event_TketHapusKeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (TketHapus.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Keterangan/alasan menghapus harus diisi dengan benar..!!");
            TketHapus.requestFocus();
        } else if (TketHapus.getText().trim().length() < 3) {
            JOptionPane.showMessageDialog(null, "Keterangan/alasan menghapus harus diisi dengan keadaan yang sebenarnya..!!");
            TketHapus.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                simpanHistoriHapus();
            } else {
                emptTeks();
                tampil();
                WindowHapus.dispose();
            }
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnHapus1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowHapus.dispose();
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void MnDataSampahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataSampahActionPerformed
        DTPCari3.setDate(new Date());
        DTPCari4.setDate(new Date());
        TCari2.setText(TNoRM.getText());
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(949, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnDataSampahActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn12.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilRiwayat();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll2);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari2.setText("");
        tampilRiwayat();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll2ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih & telah dihapus akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.cariInteger("select count(-1) from jaminan_transaksi where "
                    + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya dg. no. rawat yg. sama..!!");
                tampilRiwayat();
            } else {
                kembalikanData();
                TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                tampil();
                emptTeks();
            }
        } else {
            tampilRiwayat();
        }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            tbRiwayat.requestFocus();
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn12ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn12ActionPerformed

    private void MnTTDkuitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTDkuitansiActionPerformed
        if (tbJaminan.getSelectedRow() > -1) {
            tte = "";
            tte = "tidak";
            cetakKuitansiJaminan();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tampil();
            tbJaminan.requestFocus();
        }
    }//GEN-LAST:event_MnTTDkuitansiActionPerformed

    private void MnTTEkuitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTEkuitansiActionPerformed
        if (tbJaminan.getSelectedRow() > -1) {
            tte = "";
            tte = "ya";
            cetakKuitansiJaminan();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tampil();
            tbJaminan.requestFocus();
        }
    }//GEN-LAST:event_MnTTEkuitansiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgJaminanTransaksi dialog = new DlgJaminanTransaksi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll2;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn12;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkJaminanBatal;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenu MnCetakKuitansi;
    private javax.swing.JMenuItem MnDataSampah;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnPanjar;
    private javax.swing.JMenuItem MnTTDkuitansi;
    private javax.swing.JMenuItem MnTTEkuitansi;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    public widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TJmlNominal;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TalasanBatal;
    private widget.TextBox TcaraBayar;
    private widget.TextBox TketHapus;
    private widget.TextArea Tketerangan;
    private widget.TextBox TnipMenerima;
    private widget.TextBox TnmMenerima;
    private widget.TextBox TnmPemberi;
    private widget.TextBox TnoTelp;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglTerima;
    private javax.swing.JDialog WindowHapus;
    private javax.swing.JDialog WindowRiwayat;
    private widget.Button btnPetugas;
    private widget.ComboBox cmbJnsJaminan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel26;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label labelNominal;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbJaminan;
    private widget.Table tbRiwayat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        jumlahNom = "";
        try {
            ps = koneksi.prepareStatement("SELECT jt.*, p.no_rkm_medis, p.nm_pasien, date_format(jt.tgl_terima,'%d-%m-%Y, %H:%i') tglTerima, pg.nama petugasMenerima, "
                    + "format(jt.jumlah_nominal,0) jmlNominal, date_format(jt.tgl_dikembalikan,'%d-%m-%Y, %H:%i') tglDikembalikan, date(jt.tgl_terima) tglTer, "
                    + "pj.png_jawab, rp.kd_pj FROM jaminan_transaksi jt inner join reg_periksa rp on rp.no_rawat=jt.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis inner join pegawai pg on pg.nik=jt.nip_penerima inner join penjab pj on pj.kd_pj=rp.kd_pj where "
                    + "date(jt.tgl_terima) between ? and ? and jt.no_rawat like ? or "
                    + "date(jt.tgl_terima) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(jt.tgl_terima) between ? and ? and p.nm_pasien like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.ruang_rawat like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.nm_pemberi_jaminan like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.telah_terima like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jenis_jaminan like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.keterangan like ? or "
                    + "date(jt.tgl_terima) between ? and ? and pg.nama like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal like ? or "
                    + "date(jt.tgl_terima) between ? and ? and pj.png_jawab like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jumlah_nominal like ? order by jt.tgl_terima");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%");
                ps.setString(34, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(35, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(36, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("jmlNominal").equals("0")) {
                        jumlahNom = "-";
                    } else {
                        jumlahNom = rs.getString("jmlNominal");
                    }

                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruang_rawat"),
                        rs.getString("png_jawab"),
                        rs.getString("jenis_jaminan"),
                        rs.getString("nm_pemberi_jaminan"),
                        rs.getString("no_telp"),
                        rs.getString("tglTerima"),
                        rs.getString("petugasMenerima"),
                        rs.getString("keterangan"),
                        jumlahNom,
                        rs.getString("tgl_terima"),
                        rs.getString("nip_penerima"),
                        rs.getString("jumlah_nominal"),
                        rs.getString("jaminan_batal"),
                        rs.getString("alasan_pembatalan"),
                        rs.getString("tglTer"),
                        rs.getString("waktu_simpan"),
                        rs.getString("kd_pj")
                    });
                }                
            } catch (Exception e) {
                System.out.println("tampil() : " + e);
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
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TrgRawat.setText("");
        TcaraBayar.setText("");
        cmbJnsJaminan.setSelectedIndex(0);        
        TnmPemberi.setText("");
        TnoTelp.setText("");
        TtglTerima.setDate(new Date());
        TnipMenerima.setText("-");
        TnmMenerima.setText("-");
        Tketerangan.setText("");
        TJmlNominal.setText("0");
        labelNominal.setText("Rp. 0");
        ChkJaminanBatal.setSelected(false);
        TalasanBatal.setText("");
        ChkJaminanBatal.setEnabled(false);
        TalasanBatal.setEnabled(false);
        cmbJnsJaminan.requestFocus();
    }

    private void getData() {
        cekBatal = "";
        kodePJ = "";
        if (tbJaminan.getSelectedRow() != -1) {
            ChkJaminanBatal.setEnabled(true);
            
            TNoRw.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 1).toString());
            TPasien.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 3).toString());
            TcaraBayar.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 4).toString());            
            cmbJnsJaminan.setSelectedItem(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 5).toString());
            TnmPemberi.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 6).toString());
            TnoTelp.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 7).toString());
            Valid.SetTgl(TtglTerima, tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 17).toString());
            TnipMenerima.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 13).toString());
            TnmMenerima.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 9).toString());
            Tketerangan.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 10).toString());
            TJmlNominal.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 14).toString());
            cekBatal = tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 15).toString();
            TalasanBatal.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 16).toString());
            kodePJ = tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 19).toString();

            if (cekBatal.equals("Ya")) {
                ChkJaminanBatal.setSelected(true);
                TalasanBatal.setEnabled(true);
            } else {
                ChkJaminanBatal.setSelected(false);
                TalasanBatal.setEnabled(false);
            }
            
            if (TJmlNominal.getText().trim().equals("")) {
                labelNominal.setText("Rp. 0");
            } else {
                labelNominal.setText("Rp. " + Valid.SetAngka(Double.parseDouble(TJmlNominal.getText())));
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getbilling_ranap());
        BtnGanti.setEnabled(akses.getbilling_ranap());        
        MnHapus.setEnabled(akses.getpic_keuangan());
        MnDataSampah.setEnabled(akses.getadmin());
    }
    
    public void setData(String norwt, String norm, String nmPasien, String ruangrwt) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nmPasien);
        TrgRawat.setText(ruangrwt);
        TcaraBayar.setText(Sequel.cariIsi("select pj.png_jawab from reg_periksa rp inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + norwt + "'"));
        
        if (akses.getadmin() == true) {
            TnipMenerima.setText("-");
            TnmMenerima.setText("-");
        } else {
            TnipMenerima.setText(akses.getkode());
            TnmMenerima.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipMenerima.getText() + "'"));
        }        
        TCari.setText(norwt);
    }
    
    private void keTransaksiPanjar() {
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah jaminan transaksi pasien ini akan dilanjutkan menjadi panjar..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.mengedittf("jaminan_transaksi", "no_rawat=?", "nm_pemberi_jaminan=?, no_telp=?, jenis_jaminan=?, tgl_terima=?, "
                    + "nip_penerima=?, keterangan=?, jumlah_nominal=?, jaminan_batal=?, alasan_pembatalan=?", 10, new String[]{
                        TnmPemberi.getText(), TnoTelp.getText(), cmbJnsJaminan.getSelectedItem().toString(),
                        Valid.SetTgl(TtglTerima.getSelectedItem() + "") + " " + Sequel.cariIsi("select time(now())"), TnipMenerima.getText(),
                        Tketerangan.getText(), TJmlNominal.getText(), "Ya", "Jaminan transaksi dilanjutkan menjadi panjar",
                        tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 0).toString()
                    }) == true) {

                String cekSelisih = "", cekTagihan = "", nmUnit = "";
                if (Sequel.cariInteger("select count(-1) from piutang_pasien where no_rawat='" + TNoRw.getText() + "'") > 0) {
                    cekTagihan = Sequel.cariIsi("select totalpiutang from piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
                } else {
                    if (!Sequel.cariIsi("select jumlah_bayar from tagihan_sadewa where no_nota='" + TNoRw.getText() + "'").equals("")) {
                        cekTagihan = Sequel.cariIsi("select jumlah_bayar from tagihan_sadewa where no_nota='" + TNoRw.getText() + "' order by tgl_bayar desc limit 1");
                    } else {
                        cekTagihan = "0";
                    }
                }

                if (kodePJ.equals("B01")) {
                    if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ranap'") > 0) {
                        nmUnit = Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");
                        if (!Sequel.cariIsi("select total_tagihan from biaya_naik_kelas_bpjs where no_rawat='" + TNoRw.getText() + "'").equals("")) {
                            cekSelisih = Sequel.cariIsi("select total_tagihan from biaya_naik_kelas_bpjs where no_rawat='" + TNoRw.getText() + "'");
                        } else {
                            cekSelisih = "0";
                        }
                    } else {
                        nmUnit = Sequel.cariIsi("select pl.nm_poli from reg_periksa rp inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + TNoRw.getText() + "'");
                        cekSelisih = "0";
                    }
                } else {
                    if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ranap'") > 0) {
                        nmUnit = Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");
                    } else {
                        nmUnit = Sequel.cariIsi("select pl.nm_poli from reg_periksa rp inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + TNoRw.getText() + "'");
                    }
                    cekSelisih = "0";
                }

                akses.setform("DlgJaminanTransaksi");
                DlgTransaksiPanjar panjar = new DlgTransaksiPanjar(null, false);
                panjar.emptTeks();
                panjar.isCek();
                panjar.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), nmUnit,
                        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"),
                        cekSelisih, cekTagihan, TJmlNominal.getText());
                panjar.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                panjar.setLocationRelativeTo(internalFrame1);
                panjar.setVisible(true);
                panjar.toFront();
                panjar.requestFocus();
//                panjar.setAlwaysOnTop(true);
                BtnBatalActionPerformed(null);
            }
        } else {
            BtnBatalActionPerformed(null);
        }
    }
    
    private void simpanHistoriHapus() {
        try {
            ps1 = koneksi.prepareStatement("select * from jaminan_transaksi where no_rawat='" + tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 0).toString() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    String nipHapus = "";
                    if (akses.getadmin() == true) {
                        nipHapus = "-";
                    } else {
                        nipHapus = akses.getkode();
                    }

                    if (Sequel.menyimpantf("jaminan_transaksi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Jaminan Transaksi Histori", 18, new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("ruang_rawat"),
                        rs1.getString("nm_pemberi_jaminan"),
                        rs1.getString("no_telp"),
                        rs1.getString("jenis_jaminan"),
                        rs1.getString("tgl_terima"),
                        rs1.getString("nip_penerima"),
                        rs1.getString("keterangan"),
                        rs1.getString("jumlah_nominal"),
                        rs1.getString("tgl_dikembalikan"),
                        rs1.getString("nip_mengembalikan"),
                        rs1.getString("telah_terima"),
                        rs1.getString("jaminan_batal"),
                        rs1.getString("alasan_pembatalan"),
                        rs1.getString("waktu_simpan"),
                        TketHapus.getText(), nipHapus, Sequel.cariIsi("select now()")
                    }) == true) {
                        if (Sequel.queryu2tf("delete from jaminan_transaksi where no_rawat=?", 1, new String[]{
                            tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 0).toString()
                        }) == true) {
                            Valid.SetTgl(DTPCari1, Valid.SetTgl(TtglTerima.getSelectedItem() + ""));
                            BtnBatalActionPerformed(null);
                            WindowHapus.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
                    }
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
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, j.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "date_format(j.waktu_hapus,'%d-%m-%Y') tglHapus, time(j.waktu_hapus) jamHapus, j.ket_hapus, j.waktu_hapus FROM jaminan_transaksi_histori j "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = j.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = j.nip_penghapus WHERE "
                    + "date(j.waktu_hapus) between ? and ? and pg.nama like ? or "
                    + "date(j.waktu_hapus) between ? and ? and j.no_rawat like ? or "
                    + "date(j.waktu_hapus) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(j.waktu_hapus) between ? and ? and p.nm_pasien like ? order by j.waktu_hapus desc");
            try {
                ps2.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps2.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps2.setString(3, "%" + TCari2.getText().trim() + "%");
                ps2.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps2.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps2.setString(6, "%" + TCari2.getText().trim() + "%");
                ps2.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps2.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps2.setString(9, "%" + TCari2.getText().trim() + "%");
                ps2.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps2.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps2.setString(12, "%" + TCari2.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new String[]{
                        rs2.getString("pelaku"),
                        rs2.getString("no_rawat"),
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),
                        rs2.getString("tglHapus"),
                        rs2.getString("jamHapus"),
                        rs2.getString("ket_hapus"),
                        rs2.getString("waktu_hapus")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode1.getRowCount());
    }
    
    private void kembalikanData() {
        try {
            ps3 = koneksi.prepareStatement("select * from jaminan_transaksi_histori where "
                    + "waktu_hapus='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString() + "'");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    if (Sequel.menyimpantf("jaminan_transaksi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 15, new String[]{
                        rs3.getString("no_rawat"),
                        rs3.getString("ruang_rawat"),
                        rs3.getString("nm_pemberi_jaminan"),
                        rs3.getString("no_telp"),
                        rs3.getString("jenis_jaminan"),
                        rs3.getString("tgl_terima"),
                        rs3.getString("nip_penerima"),
                        rs3.getString("keterangan"),
                        rs3.getString("jumlah_nominal"),
                        rs3.getString("tgl_dikembalikan"),
                        rs3.getString("nip_mengembalikan"),
                        rs3.getString("telah_terima"),
                        rs3.getString("jaminan_batal"),
                        rs3.getString("alasan_pembatalan"),
                        rs3.getString("waktu_simpan")
                    }) == true) {
                        System.out.println("Proses mengembalikan/restore data berhasil..!!");
                        JOptionPane.showMessageDialog(rootPane, "Proses mengembalikan/restore data berhasil..!!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cetakKuitansiJaminan() {
        String user = "";

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("no_kwitansi", TNoRw.getText());
        param.put("telah_terima", TnmPemberi.getText() + " (No. Telp./HP. " + TnoTelp.getText() + ")");
        param.put("uang_sebanyak", Sequel.Terbilang(Double.parseDouble(TJmlNominal.getText())) + " Rupiah.");
        param.put("untuk_byr", "Jaminan transaksi pelayanan Kesehatan Rawat Inap di " + Sequel.cariIsi("select nama_instansi from setting") + " ruang perawatan\n"
                + Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal where "
                        + "ki.no_rawat='" + TNoRw.getText() + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1") + " a.n " + TPasien.getText() + " (No. RM : " + TNoRM.getText() + ")");
        param.put("terbilang", "Terbilang Rp. " + Valid.SetAngka(Double.parseDouble(TJmlNominal.getText())).replaceAll(",", "."));
        param.put("tglNota", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglTerima.getSelectedItem() + "")));

        if (akses.getadmin() == true || BtnSimpan.isEnabled() == false) {
            user = "____________________";
        } else if (akses.getbilling_ranap()) {
            user = Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'");
        }

        param.put("petugas_ksr", "( " + user + " )");

        if (tte.equals("tidak")) {
            Valid.MyReport("rptKwitansiJaminan.jasper", "report", "::[ Kwitansi Jaminan Transaksi (Rawat Inap) ]::",
                    "SELECT now() tgl", param);
        } else if (tte.equals("ya")) {
            if (akses.getadmin() == true) {
                Valid.MyReport("rptKwitansiJaminan.jasper", "report", "::[ Kwitansi Jaminan Transaksi (Rawat Inap) ]::",
                        "SELECT now() tgl", param);
            } else {
                String isi = "";
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                                "Kuitansi Jaminan", user,
                                Sequel.cariIsi("select date_format('" + Valid.SetTgl(TtglTerima.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");

                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Kuitansi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));

                Valid.MyReport("rptKwitansiJaminanQr.jasper", "report", "::[ Kwitansi Jaminan Transaksi (Rawat Inap) ]::",
                        "SELECT now() tgl", param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            }
        }

        BtnBatalActionPerformed(null);
        this.setCursor(Cursor.getDefaultCursor());
    }    
 }
