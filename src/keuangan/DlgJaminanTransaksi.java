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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author dosen
 */
public class DlgJaminanTransaksi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int i = 0, x = 0;
    private String cekBatal = "", jumlahNom = "", kodePJ = "";
    
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

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TnmPemberi.setDocument(new batasInput((int) 150).getKata(TnmPemberi));
        TnoTelp.setDocument(new batasInput((byte) 17).getOnlyAngka(TnoTelp));
        TJmlNominal.setDocument(new batasInput((byte) 9).getOnlyAngka(TJmlNominal));
        
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
        MnCetakKuitansi = new javax.swing.JMenuItem();
        MnPanjar = new javax.swing.JMenuItem();
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

        MnCetakKuitansi.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakKuitansi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKuitansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnCetakKuitansi.setText("Cetak Kuitansi Jaminan");
        MnCetakKuitansi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakKuitansi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakKuitansi.setIconTextGap(5);
        MnCetakKuitansi.setName("MnCetakKuitansi"); // NOI18N
        MnCetakKuitansi.setPreferredSize(new java.awt.Dimension(170, 26));
        MnCetakKuitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakKuitansiActionPerformed(evt);
            }
        });
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-08-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-08-2025" }));
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
        TtglTerima.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-08-2025" }));
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
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from jaminan_transaksi where no_rawat=?", 1, new String[]{
                    tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 0).toString()
                }) == true) {
                    BtnBatalActionPerformed(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                BtnBatalActionPerformed(null);
            }
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
                if (Sequel.cariSelisihTarifInacbg(TNoRw.getText()) > 0) {
                    keTransaksiPanjar();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Hitungan biaya selisih tarif INACBG belum tersimpan..!!");
                    tampil();
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

    private void MnCetakKuitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakKuitansiActionPerformed
        if (tbJaminan.getSelectedRow() > -1) {
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
                param.put("petugas_ksr", "( ____________________ )");
            } else if (akses.getbilling_ranap()) {
                param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
            }
            Valid.MyReport("rptKwitansiJaminan.jasper", "report", "::[ Kwitansi Jaminan Transaksi (Rawat Inap) ]::",
                    "SELECT now() tgl", param);
            
            BtnBatalActionPerformed(null);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tampil();
            tbJaminan.requestFocus();
        }
    }//GEN-LAST:event_MnCetakKuitansiActionPerformed

    private void TJmlNominalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJmlNominalKeyPressed
        Valid.pindah(evt, TtglTerima, btnPetugas);
    }//GEN-LAST:event_TJmlNominalKeyPressed

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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnGanti;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkJaminanBatal;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetakKuitansi;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnPanjar;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TJmlNominal;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TalasanBatal;
    private widget.TextBox TcaraBayar;
    private widget.TextArea Tketerangan;
    private widget.TextBox TnipMenerima;
    private widget.TextBox TnmMenerima;
    private widget.TextBox TnmPemberi;
    private widget.TextBox TnoTelp;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglTerima;
    private widget.Button btnPetugas;
    private widget.ComboBox cmbJnsJaminan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
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
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbJaminan;
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
        MnHapus.setEnabled(akses.getadmin());
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

                String cekSelisih = "", cekTagihan = "";
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
                    if (!Sequel.cariIsi("select total_tagihan from biaya_naik_kelas_bpjs where no_rawat='" + TNoRw.getText() + "'").equals("")) {
                        cekSelisih = Sequel.cariIsi("select total_tagihan from biaya_naik_kelas_bpjs where no_rawat='" + TNoRw.getText() + "'");
                    } else {
                        cekSelisih = "0";
                    }
                } else {
                    cekSelisih = "0";
                }

                akses.setform("DlgJaminanTransaksi");
                DlgTransaksiPanjar panjar = new DlgTransaksiPanjar(null, false);
                panjar.emptTeks();
                panjar.isCek();
                panjar.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText(),
                        Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal where "
                                + "ki.no_rawat='" + TNoRw.getText() + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"),
                        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"),
                        cekSelisih, cekTagihan, TJmlNominal.getText());
                panjar.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                panjar.setLocationRelativeTo(internalFrame1);
                panjar.setVisible(true);
                panjar.setAlwaysOnTop(true);
                BtnBatalActionPerformed(null);
            }
        } else {
            BtnBatalActionPerformed(null);
        }
    }
}
