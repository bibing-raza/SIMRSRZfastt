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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgTransaksiPanjar extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String nipSimpan = "", nipGanti = "", totReal = "", totSelisih = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgTransaksiPanjar(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Panjar", "No. Rawat", "No. RM", "Nama Pasien", "Ruang Rawat", "Tgl. Panjar", "Jam",
            "No. Hp/Telp. Konfirmasi", "Alternatif No. Hp.Telp.", "Nominal Panjar (Rp.)", "Status Panjar",
            "Nominal Status (Rp.)", "Petugas Penerima", "Perbaikan Panjar Oleh", "Keterangan", "Telah Terima Dari",
            "tgl_panjar", "nip_petugas_simpan", "waktu_simpan", "nip_petugas_ganti", "waktu_ganti", "nominal_panjar",
            "nominal_balik", "realcost", "selisihtarif"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPanjar.setModel(tabMode);
        tbPanjar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPanjar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbPanjar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(50);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(130);
            } else if (i == 10) {
                column.setPreferredWidth(120);
            } else if (i == 11) {
                column.setPreferredWidth(130);
            } else if (i == 12) {
                column.setPreferredWidth(220);
            } else if (i == 13) {
                column.setPreferredWidth(220);
            } else if (i == 14) {
                column.setPreferredWidth(220);
            } else if (i == 15) {
                column.setPreferredWidth(220);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPanjar.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TnoTelp1.setDocument(new batasInput((byte) 16).getOnlyAngka(TnoTelp1));
        TnoTelp2.setDocument(new batasInput((byte) 16).getOnlyAngka(TnoTelp2));
        TnominalPanjar.setDocument(new batasInput((byte) 9).getOnlyAngka(TnominalPanjar));
        TnominalStatus.setDocument(new batasInput((byte) 9).getOnlyAngka(TnominalStatus));
        TtelahTerima.setDocument(new batasInput((int) 150).getKata(TtelahTerima));
        
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
        MnKuitansi = new javax.swing.JMenuItem();
        MnLembarPanjar = new javax.swing.JMenuItem();
        MnHapus = new javax.swing.JMenuItem();
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
        jLabel22 = new widget.Label();
        cmbStatus1 = new widget.ComboBox();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        jLabel9 = new widget.Label();
        TnoPanjar = new widget.TextBox();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel11 = new widget.Label();
        TtglPanjar = new widget.Tanggal();
        jLabel12 = new widget.Label();
        TnoTelp1 = new widget.TextBox();
        jLabel13 = new widget.Label();
        TnoTelp2 = new widget.TextBox();
        jLabel14 = new widget.Label();
        TnominalPanjar = new widget.TextBox();
        jLabel15 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel16 = new widget.Label();
        TnominalStatus = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel17 = new widget.Label();
        TtglMrs = new widget.TextBox();
        labelnom_panjar = new widget.Label();
        labelnom_status = new widget.Label();
        jLabel18 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tketerangan = new widget.TextArea();
        jLabel20 = new widget.Label();
        TtelahTerima = new widget.TextBox();
        label_tot_tagihan = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        labelnom_real = new widget.Label();
        labelnom_selisih = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPanjar = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnKuitansi.setBackground(new java.awt.Color(242, 242, 242));
        MnKuitansi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKuitansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnKuitansi.setText("Kuitansi Pembayaran");
        MnKuitansi.setToolTipText("");
        MnKuitansi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKuitansi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKuitansi.setIconTextGap(5);
        MnKuitansi.setName("MnKuitansi"); // NOI18N
        MnKuitansi.setPreferredSize(new java.awt.Dimension(170, 26));
        MnKuitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKuitansiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKuitansi);

        MnLembarPanjar.setBackground(new java.awt.Color(242, 242, 242));
        MnLembarPanjar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarPanjar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnLembarPanjar.setText("Lembar Bukti Panjar");
        MnLembarPanjar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarPanjar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarPanjar.setIconTextGap(5);
        MnLembarPanjar.setName("MnLembarPanjar"); // NOI18N
        MnLembarPanjar.setPreferredSize(new java.awt.Dimension(170, 26));
        MnLembarPanjar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarPanjarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLembarPanjar);

        MnHapus.setBackground(new java.awt.Color(242, 242, 242));
        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapus.setText("Hapus Panjar");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Transaksi Panjar Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        jLabel19.setText("Tgl. Panjar :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(78, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-08-2024" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-08-2024" }));
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

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Status Panjar :");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel22);

        cmbStatus1.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Selesai", "Kurang Bayar", "Lebih Bayar", "Lanjut Piutang" }));
        cmbStatus1.setName("cmbStatus1"); // NOI18N
        cmbStatus1.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass10.add(cmbStatus1);

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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 320));
        PanelInput.setLayout(null);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No. Panjar :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 66, 110, 23);

        TnoPanjar.setEditable(false);
        TnoPanjar.setForeground(new java.awt.Color(0, 0, 0));
        TnoPanjar.setName("TnoPanjar"); // NOI18N
        PanelInput.add(TnoPanjar);
        TnoPanjar.setBounds(114, 66, 131, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(319, 10, 410, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Panjar :");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(525, 94, 70, 23);

        TtglPanjar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-08-2024" }));
        TtglPanjar.setDisplayFormat("dd-MM-yyyy");
        TtglPanjar.setName("TtglPanjar"); // NOI18N
        TtglPanjar.setOpaque(false);
        TtglPanjar.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglPanjar);
        TtglPanjar.setBounds(600, 94, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("No. Hp/Telp. Konfirmasi :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelInput.add(jLabel12);
        jLabel12.setBounds(0, 122, 140, 23);

        TnoTelp1.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp1.setName("TnoTelp1"); // NOI18N
        TnoTelp1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelp1KeyPressed(evt);
            }
        });
        PanelInput.add(TnoTelp1);
        TnoTelp1.setBounds(144, 122, 131, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Alternatif No. Hp/Telp. Konfirmasi :");
        jLabel13.setName("jLabel13"); // NOI18N
        PanelInput.add(jLabel13);
        jLabel13.setBounds(355, 122, 240, 23);

        TnoTelp2.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp2.setName("TnoTelp2"); // NOI18N
        TnoTelp2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelp2KeyPressed(evt);
            }
        });
        PanelInput.add(TnoTelp2);
        TnoTelp2.setBounds(600, 122, 131, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nominal Panjar :");
        jLabel14.setName("jLabel14"); // NOI18N
        PanelInput.add(jLabel14);
        jLabel14.setBounds(0, 150, 140, 23);

        TnominalPanjar.setForeground(new java.awt.Color(0, 0, 0));
        TnominalPanjar.setName("TnominalPanjar"); // NOI18N
        TnominalPanjar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TnominalPanjarActionPerformed(evt);
            }
        });
        TnominalPanjar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnominalPanjarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TnominalPanjarKeyReleased(evt);
            }
        });
        PanelInput.add(TnominalPanjar);
        TnominalPanjar.setBounds(144, 150, 131, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Status Panjar :");
        jLabel15.setName("jLabel15"); // NOI18N
        PanelInput.add(jLabel15);
        jLabel15.setBounds(0, 178, 140, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Selesai", "Kurang Bayar", "Lebih Bayar", "Lanjut Piutang" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        PanelInput.add(cmbStatus);
        cmbStatus.setBounds(144, 178, 105, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nominal Status :");
        jLabel16.setName("jLabel16"); // NOI18N
        PanelInput.add(jLabel16);
        jLabel16.setBounds(0, 206, 140, 23);

        TnominalStatus.setForeground(new java.awt.Color(0, 0, 0));
        TnominalStatus.setName("TnominalStatus"); // NOI18N
        TnominalStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TnominalStatusActionPerformed(evt);
            }
        });
        TnominalStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnominalStatusKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TnominalStatusKeyReleased(evt);
            }
        });
        PanelInput.add(TnominalStatus);
        TnominalStatus.setBounds(144, 206, 131, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        PanelInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        PanelInput.add(TrgRawat);
        TrgRawat.setBounds(115, 38, 420, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tgl. MRS :");
        jLabel17.setName("jLabel17"); // NOI18N
        PanelInput.add(jLabel17);
        jLabel17.setBounds(535, 38, 60, 23);

        TtglMrs.setEditable(false);
        TtglMrs.setForeground(new java.awt.Color(0, 0, 0));
        TtglMrs.setName("TtglMrs"); // NOI18N
        PanelInput.add(TtglMrs);
        TtglMrs.setBounds(600, 38, 131, 23);

        labelnom_panjar.setForeground(new java.awt.Color(0, 0, 0));
        labelnom_panjar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelnom_panjar.setText("0");
        labelnom_panjar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelnom_panjar.setName("labelnom_panjar"); // NOI18N
        PanelInput.add(labelnom_panjar);
        labelnom_panjar.setBounds(280, 150, 320, 23);

        labelnom_status.setForeground(new java.awt.Color(0, 0, 0));
        labelnom_status.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelnom_status.setText("0");
        labelnom_status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelnom_status.setName("labelnom_status"); // NOI18N
        PanelInput.add(labelnom_status);
        labelnom_status.setBounds(280, 206, 320, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Keterangan :");
        jLabel18.setToolTipText("");
        jLabel18.setName("jLabel18"); // NOI18N
        PanelInput.add(jLabel18);
        jLabel18.setBounds(0, 234, 140, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N
        scrollPane13.setPreferredSize(new java.awt.Dimension(174, 100));

        Tketerangan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tketerangan.setColumns(20);
        Tketerangan.setRows(5);
        Tketerangan.setName("Tketerangan"); // NOI18N
        Tketerangan.setPreferredSize(new java.awt.Dimension(162, 700));
        Tketerangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketeranganKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tketerangan);

        PanelInput.add(scrollPane13);
        scrollPane13.setBounds(144, 234, 590, 72);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Telah Terima Dari :");
        jLabel20.setName("jLabel20"); // NOI18N
        PanelInput.add(jLabel20);
        jLabel20.setBounds(0, 94, 110, 23);

        TtelahTerima.setForeground(new java.awt.Color(0, 0, 0));
        TtelahTerima.setName("TtelahTerima"); // NOI18N
        TtelahTerima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtelahTerimaKeyPressed(evt);
            }
        });
        PanelInput.add(TtelahTerima);
        TtelahTerima.setBounds(115, 94, 410, 23);

        label_tot_tagihan.setForeground(new java.awt.Color(0, 0, 0));
        label_tot_tagihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_tot_tagihan.setText("Dari Total Tagihan Real Cost/Selisih Tarif");
        label_tot_tagihan.setName("label_tot_tagihan"); // NOI18N
        PanelInput.add(label_tot_tagihan);
        label_tot_tagihan.setBounds(255, 178, 220, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Total Biaya Real Cost :");
        jLabel23.setName("jLabel23"); // NOI18N
        PanelInput.add(jLabel23);
        jLabel23.setBounds(740, 10, 170, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Total Biaya Selisih Tarif INACBG :");
        jLabel24.setName("jLabel24"); // NOI18N
        PanelInput.add(jLabel24);
        jLabel24.setBounds(740, 38, 170, 23);

        labelnom_real.setForeground(new java.awt.Color(0, 0, 0));
        labelnom_real.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelnom_real.setText("0");
        labelnom_real.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelnom_real.setName("labelnom_real"); // NOI18N
        PanelInput.add(labelnom_real);
        labelnom_real.setBounds(915, 10, 320, 23);

        labelnom_selisih.setForeground(new java.awt.Color(0, 0, 0));
        labelnom_selisih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelnom_selisih.setText("0");
        labelnom_selisih.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelnom_selisih.setName("labelnom_selisih"); // NOI18N
        PanelInput.add(labelnom_selisih);
        labelnom_selisih.setBounds(915, 38, 320, 23);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPanjar.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbPanjar.setComponentPopupMenu(jPopupMenu1);
        tbPanjar.setName("tbPanjar"); // NOI18N
        tbPanjar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPanjarMouseClicked(evt);
            }
        });
        tbPanjar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPanjarKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPanjar);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPanjarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPanjarMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPanjarMouseClicked

    private void tbPanjarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPanjarKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPanjarKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void TnoTelp1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelp1KeyPressed
        Valid.pindah(evt, TtglPanjar, TnoTelp2);
    }//GEN-LAST:event_TnoTelp1KeyPressed

    private void TnoTelp2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelp2KeyPressed
        Valid.pindah(evt, TnoTelp1, TnominalPanjar);
    }//GEN-LAST:event_TnoTelp2KeyPressed

    private void TnominalPanjarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnominalPanjarKeyPressed
        Valid.pindah(evt, TnoTelp2, cmbStatus);
    }//GEN-LAST:event_TnominalPanjarKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (TnoTelp1.getText().trim().equals("")) {
            Valid.textKosong(TnoTelp1, "No. Hp/Telp. Konfirmasi");
            TnoTelp1.requestFocus();
        } else if (TnominalPanjar.getText().equals("") || TnominalPanjar.getText().length() <= 5) {
            Valid.textKosong(TnominalPanjar, "Nominal Panjar");
            TnominalPanjar.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from transaksi_panjar where no_rawat='" + TNoRw.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data transaksi panjar pasien ini sudah tersimpan, hanya bisa diperbaiki..!!");
            tbPanjar.requestFocus();
        } else {
            if (akses.getadmin() == true) {
                nipSimpan = "-";
            } else {
                nipSimpan = akses.getkode();
            }
            AutoNomorPanjar();
            
            if (Sequel.menyimpantf("transaksi_panjar", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Transaksi Panjar", 17, new String[]{
                TnoPanjar.getText(), Valid.SetTgl(TtglPanjar.getSelectedItem() + ""), TrgRawat.getText(), TNoRw.getText(),
                TnoTelp1.getText(), TnoTelp2.getText(), TnominalPanjar.getText(), cmbStatus.getSelectedItem().toString(),
                TnominalStatus.getText(), nipSimpan, Sequel.cariIsi("select now()"), "-", "0000-00-00 00:00:00", Tketerangan.getText(),
                TtelahTerima.getText(), totReal, totSelisih
            }) == true) {

                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (TnoTelp1.getText().trim().equals("")) {
            Valid.textKosong(TnoTelp1, "No. Hp/Telp. Konfirmasi");
            TnoTelp1.requestFocus();
        } else if (TnominalPanjar.getText().equals("") || TnominalPanjar.getText().length() <= 5) {
            Valid.textKosong(TnominalPanjar, "Nominal Panjar");
            TnominalPanjar.requestFocus();
        } else {
            if (tbPanjar.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    nipGanti = "-";
                } else {
                    nipGanti = akses.getkode();
                }

                if (Sequel.mengedittf("transaksi_panjar", "no_panjar=?", "tgl_panjar=?, telp_1=?, telp_2=?, nominal_panjar=?, "
                        + "status_panjar=?, nominal_balik=?, nip_petugas_ganti=?, waktu_ganti=?, keterangan=?, telah_terima=?, "
                        + "jumlah_tagihan=?, selisih_tarif_bpjs=?", 13, new String[]{
                            Valid.SetTgl(TtglPanjar.getSelectedItem() + ""), TnoTelp1.getText(), TnoTelp2.getText(),
                            TnominalPanjar.getText(), cmbStatus.getSelectedItem().toString(), TnominalStatus.getText(),
                            nipGanti, Sequel.cariIsi("select now()"), Tketerangan.getText(), TtelahTerima.getText(),
                            totReal, totSelisih,
                            tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 0).toString()
                        }) == true) {

                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbPanjar.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        }
    }//GEN-LAST:event_BtnGantiKeyPressed

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

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbPanjar.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data yang ditampilkan pada tabel masih kosong..!!!!");
            DTPCari1.requestFocus();
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

            if (cmbStatus1.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from transaksi_panjar where "
                        + "tgl_panjar BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan..!!!!");
                } else {
                    param.put("judul", "LAPORAN PENERIMAAN PEMBAYARAN PANJAR");
                    Valid.MyReport("rptLaporanPanjar.jasper", "report", "::[ Laporan Penerimaan Pembayaran Panjar ]::",
                            "SELECT tp.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(tp.tgl_panjar,'%d/%m/%Y') tglpanjar, "
                            + "TIME_FORMAT(tp.waktu_simpan,'%H:%i') jam, format(tp.nominal_panjar,0) nomPanjar, format(tp.nominal_balik,0) nomStatus, "
                            + "pg1.nama petugas1, pg2.nama petugas2 from transaksi_panjar tp INNER JOIN reg_periksa rp on rp.no_rawat=tp.no_rawat "
                            + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg1 on pg1.nik=tp.nip_petugas_simpan "
                            + "INNER JOIN pegawai pg2 on pg2.nik=tp.nip_petugas_ganti where "
                            + "tp.tgl_panjar BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "order by tp.waktu_simpan", param);
                }
            } else {
                if (Sequel.cariInteger("select count(-1) from transaksi_panjar where "
                        + "tgl_panjar BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "and status_panjar='" + cmbStatus1.getSelectedItem().toString() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan..!!!!");
                } else {
                    param.put("judul", "LAPORAN PENERIMAAN PEMBAYARAN PANJAR (" + cmbStatus1.getSelectedItem().toString().toUpperCase() + ")");
                    Valid.MyReport("rptLaporanPanjar.jasper", "report", "::[ Laporan Penerimaan Pembayaran Panjar ]::",
                            "SELECT tp.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(tp.tgl_panjar,'%d/%m/%Y') tglpanjar, "
                            + "TIME_FORMAT(tp.waktu_simpan,'%H:%i') jam, format(tp.nominal_panjar,0) nomPanjar, format(tp.nominal_balik,0) nomStatus, "
                            + "pg1.nama petugas1, pg2.nama petugas2 from transaksi_panjar tp INNER JOIN reg_periksa rp on rp.no_rawat=tp.no_rawat "
                            + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg1 on pg1.nik=tp.nip_petugas_simpan "
                            + "INNER JOIN pegawai pg2 on pg2.nik=tp.nip_petugas_ganti where "
                            + "tp.tgl_panjar BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "and tp.status_panjar='" + cmbStatus1.getSelectedItem().toString() + "' order by tp.waktu_simpan", param);
                }
            }

            emptTeks();
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, TCari);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void TketeranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketeranganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TketeranganKeyPressed

    private void TnominalStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnominalStatusKeyPressed
        Valid.pindah(evt, cmbStatus, Tketerangan);
    }//GEN-LAST:event_TnominalStatusKeyPressed

    private void TnominalPanjarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TnominalPanjarActionPerformed
        if (TnominalPanjar.getText().trim().equals("")) {
            labelnom_panjar.setText("Rp. 0");
        } else {
            labelnom_panjar.setText("Rp. " + Valid.SetAngka(Double.parseDouble(TnominalPanjar.getText())));
        }
    }//GEN-LAST:event_TnominalPanjarActionPerformed

    private void TnominalStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TnominalStatusActionPerformed
        if (TnominalStatus.getText().trim().equals("")) {
            labelnom_status.setText("Rp. 0");
        } else {
            labelnom_status.setText("Rp. " + Valid.SetAngka(Double.parseDouble(TnominalStatus.getText())));
        }
    }//GEN-LAST:event_TnominalStatusActionPerformed

    private void TnominalPanjarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnominalPanjarKeyReleased
        if (TnominalPanjar.getText().trim().equals("")) {
            labelnom_panjar.setText("Rp. 0");
        } else {
            labelnom_panjar.setText("Rp. " + Valid.SetAngka(Double.parseDouble(TnominalPanjar.getText())));
        }
    }//GEN-LAST:event_TnominalPanjarKeyReleased

    private void TnominalStatusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnominalStatusKeyReleased
        if (TnominalStatus.getText().trim().equals("")) {
            labelnom_status.setText("Rp. 0");
        } else {
            labelnom_status.setText("Rp. " + Valid.SetAngka(Double.parseDouble(TnominalStatus.getText())));
        }
    }//GEN-LAST:event_TnominalStatusKeyReleased

    private void MnKuitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKuitansiActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            if (tbPanjar.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("emailrs", akses.getemailrs());
                param.put("norm", TNoRM.getText());
                param.put("nmpasien", TPasien.getText());
                param.put("nopanjar", TnoPanjar.getText());
                param.put("telahTerima", TtelahTerima.getText());                
                param.put("keterangan", Tketerangan.getText() + "\nNo. Hp/Telp. yang bisa dihubungi " + TnoTelp1.getText() + " / " + TnoTelp2.getText());
                param.put("tanggal", "Martapura, " + Valid.SetTglINDONESIA(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 16).toString()));
                
                if (cmbStatus.getSelectedIndex() == 2 || cmbStatus.getSelectedIndex() == 3) {
                    param.put("terbilang", Sequel.Terbilang(Double.parseDouble(TnominalStatus.getText())).toUpperCase() + " RUPIAH");
                    param.put("nominal", labelnom_status.getText().replaceAll(",", "."));
                    param.put("petugas", "(" + tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 13).toString() + ")");
                } else {
                    param.put("terbilang", Sequel.Terbilang(Double.parseDouble(TnominalPanjar.getText())).toUpperCase() + " RUPIAH");
                    param.put("nominal", labelnom_panjar.getText().replaceAll(",", "."));
                    param.put("petugas", "(" + tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 12).toString() + ")");
                }

                Valid.MyReport("rptKwitansiPanjar.jasper", "report", "::[ Kuitansi Transaksi Panjar ]::",
                        "SELECT now() tanggal", param);

                emptTeks();
                tampil();
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbPanjar.requestFocus();
            }
        }
    }//GEN-LAST:event_MnKuitansiActionPerformed

    private void MnLembarPanjarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarPanjarActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else {
            if (tbPanjar.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptLembarPanjar.jasper", "report", "::[ Lembar Transaksi Panjar ]::",
                        "SELECT tp.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(tp.tgl_panjar,'%d-%m-%Y') tglpanjar, "
                        + "TIME_FORMAT(tp.waktu_simpan,'%H:%i') jam, format(tp.nominal_panjar,0) nomPanjar, format(tp.nominal_balik,0) nomStatus, "
                        + "pg1.nama petugas1, pg2.nama petugas2, format(tp.jumlah_tagihan,0) nomReal, format(tp.selisih_tarif_bpjs,0) nomSelisih "
                        + "from transaksi_panjar tp INNER JOIN reg_periksa rp on rp.no_rawat=tp.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg1 on pg1.nik=tp.nip_petugas_simpan "
                        + "INNER JOIN pegawai pg2 on pg2.nik=tp.nip_petugas_ganti where tp.no_rawat='" + TNoRw.getText() + "'", param);

                emptTeks();
                tampil();
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbPanjar.requestFocus();
            }
        }
    }//GEN-LAST:event_MnLembarPanjarActionPerformed

    private void TtelahTerimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtelahTerimaKeyPressed
        Valid.pindah(evt, TtelahTerima, TtglPanjar);
    }//GEN-LAST:event_TtelahTerimaKeyPressed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbPanjar.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from transaksi_panjar where no_panjar=?", 1, new String[]{
                    tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbPanjar.requestFocus();
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        if (cmbStatus.getSelectedIndex() == 4) {            
            if (TnominalPanjar.getText().equals("") || TnominalPanjar.getText().equals("0")) {
                TnominalStatus.setText("0");
            } else {
                TnominalStatus.setText(TnominalPanjar.getText());
            }
            label_tot_tagihan.setVisible(false);
        } else {
            TnominalStatus.setText("0");
            if (cmbStatus.getSelectedIndex() == 2 || cmbStatus.getSelectedIndex() == 3) {
                label_tot_tagihan.setVisible(true);
            } else {
                label_tot_tagihan.setVisible(false);
            }
        }
        TnominalStatusActionPerformed(null);
    }//GEN-LAST:event_cmbStatusActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgTransaksiPanjar dialog = new DlgTransaksiPanjar(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGanti;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnKuitansi;
    private javax.swing.JMenuItem MnLembarPanjar;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Tketerangan;
    private widget.TextBox TnoPanjar;
    private widget.TextBox TnoTelp1;
    private widget.TextBox TnoTelp2;
    private widget.TextBox TnominalPanjar;
    private widget.TextBox TnominalStatus;
    private widget.TextBox TrgRawat;
    private widget.TextBox TtelahTerima;
    private widget.TextBox TtglMrs;
    private widget.Tanggal TtglPanjar;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbStatus1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label_tot_tagihan;
    private widget.Label labelnom_panjar;
    private widget.Label labelnom_real;
    private widget.Label labelnom_selisih;
    private widget.Label labelnom_status;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane13;
    private widget.Table tbPanjar;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT tp.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(tp.tgl_panjar,'%d-%m-%Y') tglpanjar, "
                    + "TIME_FORMAT(tp.waktu_simpan,'%H:%i') jam, format(tp.nominal_panjar,0) nomPanjar, format(tp.nominal_balik,0) nomStatus, "
                    + "pg1.nama petugas1, pg2.nama petugas2 from transaksi_panjar tp INNER JOIN reg_periksa rp on rp.no_rawat=tp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg1 on pg1.nik=tp.nip_petugas_simpan "
                    + "INNER JOIN pegawai pg2 on pg2.nik=tp.nip_petugas_ganti where "
                    + "tp.tgl_panjar between ? and ? and tp.no_panjar like ? or "
                    + "tp.tgl_panjar between ? and ? and tp.ruang_rawat like ? or "
                    + "tp.tgl_panjar between ? and ? and tp.no_rawat like ? or "
                    + "tp.tgl_panjar between ? and ? and p.no_rkm_medis like ? or "
                    + "tp.tgl_panjar between ? and ? and p.nm_pasien like ? or "
                    + "tp.tgl_panjar between ? and ? and tp.telp_1 like ? or "
                    + "tp.tgl_panjar between ? and ? and tp.telp_2 like ? or "
                    + "tp.tgl_panjar between ? and ? and tp.status_panjar like ? or "
                    + "tp.tgl_panjar between ? and ? and pg1.nama like ? order by tp.waktu_simpan desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_panjar"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglpanjar"),
                        rs.getString("jam"),
                        rs.getString("telp_1"),
                        rs.getString("telp_2"),
                        rs.getString("nomPanjar"),
                        rs.getString("status_panjar"),
                        rs.getString("nomStatus"),
                        rs.getString("petugas1"),
                        rs.getString("petugas2"),
                        rs.getString("keterangan"),
                        rs.getString("telah_terima"),
                        rs.getString("tgl_panjar"),
                        rs.getString("nip_petugas_simpan"),
                        rs.getString("waktu_simpan"),
                        rs.getString("nip_petugas_ganti"),
                        rs.getString("waktu_ganti"),
                        rs.getString("nominal_panjar"),
                        rs.getString("nominal_balik"),
                        rs.getString("jumlah_tagihan"),
                        rs.getString("selisih_tarif_bpjs")
                    });
                }
            } catch (Exception e) {
                System.out.println("keuangan.DlgTransaksiPanjar.tampil() : " + e);
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
        TtglMrs.setText("");
        TtglPanjar.setDate(new Date());
        TtelahTerima.setText("");
        TnoTelp1.setText("0");
        TnoTelp2.setText("0");
        TnominalPanjar.setText("0");
        TnominalStatus.setText("0");
        cmbStatus.setSelectedIndex(0);
        cmbStatus1.setSelectedIndex(0);
        Tketerangan.setText("");
        labelnom_panjar.setText("Rp. 0");
        labelnom_status.setText("Rp. 0");
        label_tot_tagihan.setVisible(false);
        totReal = "0";
        totSelisih = "0";
        labelnom_real.setText("Rp. 0");
        labelnom_selisih.setText("Rp. 0");
        AutoNomorPanjar();
    }

    private void getData() {
        nipSimpan = "";
        nipGanti = "";
        totReal = "";
        totSelisih = "";
        if (tbPanjar.getSelectedRow() != -1) {
            TnoPanjar.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 0).toString());
            TNoRw.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 1).toString());
            TNoRM.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 2).toString());
            TPasien.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 3).toString());
            TrgRawat.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 4).toString());
            TtglMrs.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'")));
            Valid.SetTgl(TtglPanjar, tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 16).toString());
            TnoTelp1.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 7).toString());
            TnoTelp2.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 8).toString());
            TnominalPanjar.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 21).toString());
            cmbStatus.setSelectedItem(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 10).toString());            
            TnominalStatus.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 22).toString());
            Tketerangan.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 14).toString());
            TtelahTerima.setText(tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 15).toString());
            nipSimpan = tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 17).toString();
            nipGanti = tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 19).toString();
            totReal = tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 23).toString();
            totSelisih = tbPanjar.getValueAt(tbPanjar.getSelectedRow(), 24).toString();
            cekCurency();
            
            if (cmbStatus.getSelectedIndex() == 2 || cmbStatus.getSelectedIndex() == 3) {
                label_tot_tagihan.setVisible(true);
            } else {
                label_tot_tagihan.setVisible(false);
            }
            
            TnominalPanjarKeyReleased(null);
            TnominalStatusKeyReleased(null);
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getbilling_ranap());
        BtnGanti.setEnabled(akses.getbilling_ranap());
        MnHapus.setEnabled(akses.getadmin());
    }
    
    private void AutoNomorPanjar() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(no_panjar,4),signed)),0) from transaksi_panjar where "
                + "tgl_panjar like '%" + Valid.SetTgl(TtglPanjar.getSelectedItem() + "").substring(0, 7) + "%' ", "/TPJ/" + Valid.SetTgl(TtglPanjar.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(TtglPanjar.getSelectedItem() + "").substring(0, 4), 4, TnoPanjar);
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan, String tglmrs, String nomSelisih, String nomRealCost) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        TtglMrs.setText(Valid.SetTglINDONESIA(tglmrs));
        TCari.setText(norw);
        totReal = nomRealCost.replaceAll(",", "");
        totSelisih = nomSelisih.replaceAll(",", "");
        cekCurency();
        
        if (Sequel.cariInteger("select count(-1) from transaksi_panjar where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_panjar from transaksi_panjar where no_rawat='" + norw + "'"));
        } else {
            DTPCari1.setDate(new Date());
        }
    }

    private void cekCurency() {
        if (totReal.equals("0")) {
            labelnom_real.setText("Rp. 0");
        } else {
            labelnom_real.setText("Rp. " + Valid.SetAngka(Double.parseDouble(totReal)));
        }

        if (totSelisih.equals("0")) {
            labelnom_selisih.setText("Rp. 0");
        } else {
            labelnom_selisih.setText("Rp. " + Valid.SetAngka(Double.parseDouble(totSelisih)));
        }
    }
}
