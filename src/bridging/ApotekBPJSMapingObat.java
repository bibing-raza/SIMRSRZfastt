/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJnsPerawatanRalan.java
 *
 * Created on May 22, 2010, 11:58:21 PM
 */

package bridging;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public final class ApotekBPJSMapingObat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private int i = 0;
    private ApotekBPJSCekReferensiDPHO barangbpjs = new ApotekBPJSCekReferensiDPHO(null, false);
    
    /** Creates new form DlgJnsPerawatanRalan
     * @param parent
     * @param modal */
    public ApotekBPJSMapingObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode = new DefaultTableModel(null, new String[]{
            "Kode Obat RS", "Nama Obat RS", "Kode Obat BPJS", "Nama Obat Apotek BPJS"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMapping.setModel(tabMode);
        tbMapping.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMapping.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbMapping.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(360);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(360);
            }
        }
        tbMapping.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "Kode Obat", "Nama Obat", "PRB", "Kronis", "Kemo", "Harga", "Restriksi", "Generik", "Aktif"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbDpho.setModel(tabMode1);
        tbDpho.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDpho.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbDpho.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(350);
            } else if (i == 7) {
                column.setPreferredWidth(120);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDpho.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "KFA Code", "KFA System", "Kode Barang", "Nama Obat/Alkes/BHP", "KFA Display", "Form Code",
            "Form System", "Form Display", "Numerator Code", "Numerator System", "Denominator Code",
            "Denominator System"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbKFA.setModel(tabMode2);
        tbKFA.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKFA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbKFA.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(140);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbKFA.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3= new DefaultTableModel(null, new Object[]{
            "Kode Barang", "Nama Barang", "Kode Satuan", "Nama Satuan", "Letak Barang",
            "Hrg.Beli(Rp)", "Ralan(Rp)", "Ranap K1(Rp)", "Ranap K2(Rp)", "Ranap K3(Rp)",
            "Kelas Utama/BPJS(Rp)", "Ranap VIP(Rp)", "Ranap VVIP(Rp)", "Beli Luar(Rp)",
            "Jual Bebas(Rp)", "Karyawan(Rp)", "Stok Minimal", "Kode Jenis", "Nama Jenis", "Kapasitas",
            "Kadaluwarsa", "Kode I.F.", "Industri Farmasi", "Kode Kategori", "Kategori", "Kode Golongan", 
            "Golongan", "Tipe Barang", "High Alert"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObatRs.setModel(tabMode3);
        tbObatRs.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObatRs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 29; i++) {
            TableColumn column = tbObatRs.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(73);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(140);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(85);
            } else if (i == 7) {
                column.setPreferredWidth(85);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(85);
            } else if (i == 12) {
                column.setPreferredWidth(85);
            } else if (i == 13) {
                column.setPreferredWidth(85);
            } else if (i == 14) {
                column.setPreferredWidth(85);
            } else if (i == 15) {
                column.setPreferredWidth(85);
            } else if (i == 16) {
                column.setPreferredWidth(95);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setPreferredWidth(120);
            } else if (i == 19) {
                column.setPreferredWidth(70);
            } else if (i == 20) {
                column.setPreferredWidth(70);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setPreferredWidth(120);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setPreferredWidth(120);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setPreferredWidth(80);
            } else if (i == 27) {
                column.setPreferredWidth(80);
            } else if (i == 28) {
                column.setPreferredWidth(80);
            }
        }
        tbObatRs.setDefaultRenderer(Object.class, new WarnaTable());

        kdobat.setDocument(new batasInput((byte) 5).getKata(kdobat));
        KdObatBPJS.setDocument(new batasInput((byte) 15).getKata(KdObatBPJS));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TCari1.setDocument(new batasInput((byte) 100).getKata(TCari1));
        TCari2.setDocument(new batasInput((byte) 100).getKata(TCari2));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
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

        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        btnReferensi = new widget.Button();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        kdobat = new widget.TextBox();
        TObat = new widget.TextBox();
        jLabel19 = new widget.Label();
        KdObatBPJS = new widget.TextBox();
        NmObatBPJS = new widget.TextBox();
        panelGlass10 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbMapping = new widget.Table();
        panelGlass11 = new widget.panelisi();
        panelGlass12 = new widget.panelisi();
        panelGlass13 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel9 = new widget.Label();
        LCount1 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbDpho = new widget.Table();
        TabObat = new javax.swing.JTabbedPane();
        panelGlass14 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbKFA = new widget.Table();
        panelGlass15 = new widget.panelisi();
        jLabel10 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel11 = new widget.Label();
        LCount2 = new widget.Label();
        panelGlass16 = new widget.panelisi();
        Scroll3 = new widget.ScrollPane();
        tbObatRs = new widget.Table();
        panelGlass17 = new widget.panelisi();
        jLabel12 = new widget.Label();
        TCari3 = new widget.TextBox();
        BtnCari3 = new widget.Button();
        jLabel13 = new widget.Label();
        LCount3 = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Mapping Obat Apotek BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
        panelGlass9.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(LCount);

        btnReferensi.setForeground(new java.awt.Color(0, 0, 0));
        btnReferensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        btnReferensi.setText("Referensi Obat DPHO Apotek BPJS");
        btnReferensi.setName("btnReferensi"); // NOI18N
        btnReferensi.setPreferredSize(new java.awt.Dimension(245, 23));
        btnReferensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReferensiActionPerformed(evt);
            }
        });
        panelGlass9.add(btnReferensi);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 44));
        FormInput.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 9));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Obat RS :");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(70, 24));
        FormInput.add(jLabel4);

        kdobat.setEditable(false);
        kdobat.setForeground(new java.awt.Color(0, 0, 0));
        kdobat.setName("kdobat"); // NOI18N
        kdobat.setPreferredSize(new java.awt.Dimension(140, 24));
        FormInput.add(kdobat);

        TObat.setEditable(false);
        TObat.setForeground(new java.awt.Color(0, 0, 0));
        TObat.setName("TObat"); // NOI18N
        TObat.setPreferredSize(new java.awt.Dimension(350, 24));
        FormInput.add(TObat);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Obat BPJS :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 24));
        FormInput.add(jLabel19);

        KdObatBPJS.setEditable(false);
        KdObatBPJS.setForeground(new java.awt.Color(0, 0, 0));
        KdObatBPJS.setName("KdObatBPJS"); // NOI18N
        KdObatBPJS.setPreferredSize(new java.awt.Dimension(140, 24));
        FormInput.add(KdObatBPJS);

        NmObatBPJS.setEditable(false);
        NmObatBPJS.setForeground(new java.awt.Color(0, 0, 0));
        NmObatBPJS.setName("NmObatBPJS"); // NOI18N
        NmObatBPJS.setPreferredSize(new java.awt.Dimension(350, 24));
        FormInput.add(NmObatBPJS);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Mapping Obat RS Dengan DPHO BPJS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbMapping.setAutoCreateRowSorter(true);
        tbMapping.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMapping.setName("tbMapping"); // NOI18N
        tbMapping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMappingMouseClicked(evt);
            }
        });
        tbMapping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMappingKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbMapping);

        panelGlass10.add(Scroll);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.GridLayout(2, 1));

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass13.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCari1);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass13.add(jLabel9);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass13.add(LCount1);

        panelGlass12.add(panelGlass13, java.awt.BorderLayout.PAGE_END);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Referensi DPHO Apotek BPJS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbDpho.setAutoCreateRowSorter(true);
        tbDpho.setName("tbDpho"); // NOI18N
        tbDpho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDphoMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbDpho);

        panelGlass12.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass11.add(panelGlass12);

        TabObat.setBackground(new java.awt.Color(254, 255, 254));
        TabObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabObat.setName("TabObat"); // NOI18N
        TabObat.setPreferredSize(new java.awt.Dimension(0, 2000));

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbKFA.setAutoCreateRowSorter(true);
        tbKFA.setName("tbKFA"); // NOI18N
        tbKFA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKFAMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbKFA);

        panelGlass14.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Key Word :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass15.add(jLabel10);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelGlass15.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+2");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnCari2);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Record :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass15.add(jLabel11);

        LCount2.setForeground(new java.awt.Color(0, 0, 0));
        LCount2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount2.setText("0");
        LCount2.setName("LCount2"); // NOI18N
        LCount2.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass15.add(LCount2);

        panelGlass14.add(panelGlass15, java.awt.BorderLayout.PAGE_END);

        TabObat.addTab("Daftar Obat/Alkes Sudah Mapping Dengan KFA Satu Sehat", panelGlass14);

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass16.setLayout(new java.awt.BorderLayout());

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbObatRs.setAutoCreateRowSorter(true);
        tbObatRs.setName("tbObatRs"); // NOI18N
        tbObatRs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatRsMouseClicked(evt);
            }
        });
        Scroll3.setViewportView(tbObatRs);

        panelGlass16.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Key Word :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass17.add(jLabel12);

        TCari3.setForeground(new java.awt.Color(0, 0, 0));
        TCari3.setName("TCari3"); // NOI18N
        TCari3.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari3KeyPressed(evt);
            }
        });
        panelGlass17.add(TCari3);

        BtnCari3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('2');
        BtnCari3.setText("Tampilkan Data");
        BtnCari3.setToolTipText("Alt+2");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnCari3);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Record :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass17.add(jLabel13);

        LCount3.setForeground(new java.awt.Color(0, 0, 0));
        LCount3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount3.setText("0");
        LCount3.setName("LCount3"); // NOI18N
        LCount3.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass17.add(LCount3);

        panelGlass16.add(panelGlass17, java.awt.BorderLayout.PAGE_END);

        TabObat.addTab("Daftar Obat/Alkes Farmasi RS", panelGlass16);

        panelGlass11.add(TabObat);

        panelGlass10.add(panelGlass11);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (kdobat.getText().trim().equals("") || TObat.getText().trim().equals("")) {
            Valid.textKosong(kdobat, "Obat RS");
        } else if (KdObatBPJS.getText().trim().equals("") || NmObatBPJS.getText().trim().equals("")) {
            Valid.textKosong(KdObatBPJS, "Obat Apotek BPJS");
        } else {
            if (Sequel.menyimpantf("maping_obat_apotek_bpjs", "?,?,?", "Mapping Obat", 3, new String[]{
                kdobat.getText(), KdObatBPJS.getText(), NmObatBPJS.getText()
            }) == true) {
                tampil();
                emptTeks();
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
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        Valid.hapusTable(tabMode,kdobat,"maping_obat_apotek_bpjs","kode_brng");
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(kdobat.getText().trim().equals("")||TObat.getText().trim().equals("")){
            Valid.textKosong(kdobat,"Obat RS");
        }else if(KdObatBPJS.getText().trim().equals("")||NmObatBPJS.getText().trim().equals("")){
            Valid.textKosong(KdObatBPJS,"Obat Apotek BPJS");
        }else{
            if(tbMapping.getSelectedRow()>-1){
                if(Sequel.mengedittf("maping_obat_apotek_bpjs","kode_brng=?","kode_brng=?,kode_brng_apotek_bpjs=?,nama_brng_apotek_bpjs=?",4,new String[]{
                        kdobat.getText(),KdObatBPJS.getText(),NmObatBPJS.getText(),tbMapping.getValueAt(tbMapping.getSelectedRow(),0).toString()
                    })==true){
                    emptTeks();
                    tampil();
                }
            }                
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbMappingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMappingMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMappingMouseClicked

    private void tbMappingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMappingKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbMappingKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void tbDphoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDphoMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataMaster();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDphoMouseClicked

    private void tbKFAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKFAMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataFarmasi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKFAMouseClicked

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilMining();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilObatRSKfa();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void btnReferensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReferensiActionPerformed
        barangbpjs.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        barangbpjs.setLocationRelativeTo(internalFrame1);
        barangbpjs.setVisible(true);
    }//GEN-LAST:event_btnReferensiActionPerformed

    private void tbObatRsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatRsMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                kdobat.setText(tbObatRs.getValueAt(tbObatRs.getSelectedRow(),0).toString());
                TObat.setText(tbObatRs.getValueAt(tbObatRs.getSelectedRow(),1).toString());
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatRsMouseClicked

    private void TCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari3ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari3KeyPressed

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        tampilObatRS();
    }//GEN-LAST:event_BtnCari3ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ApotekBPJSMapingObat dialog = new ApotekBPJSMapingObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCari3;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdObatBPJS;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Label LCount2;
    private widget.Label LCount3;
    private widget.TextBox NmObatBPJS;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TCari3;
    private widget.TextBox TObat;
    private javax.swing.JTabbedPane TabObat;
    private widget.Button btnReferensi;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel19;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdobat;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDpho;
    private widget.Table tbKFA;
    private widget.Table tbMapping;
    private widget.Table tbObatRs;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select mo.kode_brng, db.nama_brng, mo.kode_brng_apotek_bpjs, mo.nama_brng_apotek_bpjs from maping_obat_apotek_bpjs mo "
                    + "inner join databarang db on mo.kode_brng=db.kode_brng where "
                    + "mo.kode_brng like ? or db.nama_brng like ? or mo.kode_brng_apotek_bpjs like ? or mo.nama_brng_apotek_bpjs like ? order by db.nama_brng");
            try {
                ps.setString(1, "%" + TCari.getText() + "%");
                ps.setString(2, "%" + TCari.getText() + "%");
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("kode_brng"), 
                        rs.getString("nama_brng"), 
                        rs.getString("kode_brng_apotek_bpjs"), 
                        rs.getString("nama_brng_apotek_bpjs")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif Ketersediaan : " + e);
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
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        kdobat.setText("");
        TObat.setText("");
        KdObatBPJS.setText("");
        NmObatBPJS.setText("");
        kdobat.requestFocus();
    }

    private void getData() {
        if (tbMapping.getSelectedRow() != -1) {
            kdobat.setText(tbMapping.getValueAt(tbMapping.getSelectedRow(), 0).toString());
            TObat.setText(tbMapping.getValueAt(tbMapping.getSelectedRow(), 1).toString());
            KdObatBPJS.setText(tbMapping.getValueAt(tbMapping.getSelectedRow(), 2).toString());
            NmObatBPJS.setText(tbMapping.getValueAt(tbMapping.getSelectedRow(), 3).toString());
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getstok_obat_pasien());
        BtnHapus.setEnabled(akses.getstok_obat_pasien());
        BtnEdit.setEnabled(akses.getstok_obat_pasien());
        btnReferensi.setEnabled(akses.getadmin());
    }
    
    public JTable getTable(){
        return tbMapping;
    }
    
    public void tampilMining() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select * from dpho_master_bpjs where "
                    + "kodeobat like ? or namaobat like ? or restriksi like ? or generik like ? order by namaobat");
            try {
                ps1.setString(1, "%" + TCari1.getText().trim() + "%");
                ps1.setString(2, "%" + TCari1.getText().trim() + "%");
                ps1.setString(3, "%" + TCari1.getText().trim() + "%");
                ps1.setString(4, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("kodeobat"),
                        rs1.getString("namaobat"),
                        rs1.getString("prb"),
                        rs1.getString("kronis"),
                        rs1.getString("kemo"),
                        rs1.getString("harga"),
                        rs1.getString("restriksi"),
                        rs1.getString("generik"),
                        rs1.getString("aktif")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif tampilMining() : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi tampilMining() : " + e);
        }
        LCount1.setText("" + tabMode1.getRowCount());
    }
    
    private void tampilObatRSKfa() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("select ss.kode_brng,db.nama_brng,ss.obat_code, ss.obat_system,ss.obat_display,ss.form_code,ss.form_system, "
                    + "ss.form_display,ss.numerator_code,ss.numerator_system, ss.denominator_code,ss.denominator_system "
                    + "from satu_sehat_mapping_obat ss inner join databarang db on ss.kode_brng=db.kode_brng where "
                    + "ss.kode_brng like ? or db.nama_brng like ? or ss.obat_code like ? or ss.obat_display like ? or ss.form_display like ? "
                    + "order by ss.obat_code");
            try {
                ps2.setString(1, "%" + TCari2.getText() + "%");
                ps2.setString(2, "%" + TCari2.getText() + "%");
                ps2.setString(3, "%" + TCari2.getText() + "%");
                ps2.setString(4, "%" + TCari2.getText() + "%");
                ps2.setString(5, "%" + TCari2.getText() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        rs2.getString("obat_code"),
                        rs2.getString("obat_system"),
                        rs2.getString("kode_brng"),
                        rs2.getString("nama_brng"),
                        rs2.getString("obat_display"),
                        rs2.getString("form_code"),
                        rs2.getString("form_system"),
                        rs2.getString("form_display"),
                        rs2.getString("numerator_code"),
                        rs2.getString("numerator_system"),
                        rs2.getString("denominator_code"),
                        rs2.getString("denominator_system")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif Ketersediaan : " + e);
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
        LCount2.setText("" + tabMode2.getRowCount());
    }
    
    private void getDataMaster() {
        if (tbDpho.getSelectedRow() != -1) {
            KdObatBPJS.setText(tbDpho.getValueAt(tbDpho.getSelectedRow(), 0).toString());
            NmObatBPJS.setText(tbDpho.getValueAt(tbDpho.getSelectedRow(), 1).toString());
        }
    }
    
    private void getDataFarmasi() {
        if (tbKFA.getSelectedRow() != -1) {
            kdobat.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 2).toString());
            TObat.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 3).toString());
        }
    }
    
    private void tampilObatRS() {
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("SELECT db.kode_brng, db.nama_brng, db.kode_sat, ks.satuan, db.letak_barang, db.h_beli, db.ralan, "
                    + "db.kelas1, db.kelas2, db.kelas3, db.utama, db.vip, db.vvip, db.beliluar, db.jualbebas, db.karyawan, "
                    + "db.stokminimal, db.kdjns, j.nama, kapasitas, db.expire, db.kode_industri, ifm.nama_industri, "
                    + "db.kode_kategori, kb.nama kategori, db.kode_golongan, gb.nama golongan, db.tipe_brg, high_alert "
                    + "FROM databarang db INNER JOIN kodesatuan ks on db.kode_sat = ks.kode_sat "
                    + "INNER JOIN jenis j on db.kdjns = j.kdjns INNER JOIN industrifarmasi ifm on db.kode_industri = ifm.kode_industri "
                    + "INNER JOIN golongan_barang gb on db.kode_golongan = gb.kode INNER JOIN kategori_barang kb ON db.kode_kategori = kb.kode where "
                    + "db.status='1' and db.kode_brng not in (select kode_brng from satu_sehat_mapping_obat) and ("
                    + "db.kode_brng like ? or "
                    + "db.nama_brng like ? or "
                    + "db.kode_sat like ? or "
                    + "ks.satuan like ? or "
                    + "db.letak_barang like ? or "
                    + "db.kdjns like ? or "
                    + "kb.nama like ? or "
                    + "gb.nama like ? or "
                    + "j.nama like ? or "
                    + "db.kode_industri like ? or "
                    + "db.tipe_brg like ? or "
                    + "db.high_alert like ? or "
                    + "ifm.nama_industri like ?) order by db.nama_brng");
            try {
                ps3.setString(1, "%" + TCari3.getText().trim() + "%");
                ps3.setString(2, "%" + TCari3.getText().trim() + "%");
                ps3.setString(3, "%" + TCari3.getText().trim() + "%");
                ps3.setString(4, "%" + TCari3.getText().trim() + "%");
                ps3.setString(5, "%" + TCari3.getText().trim() + "%");
                ps3.setString(6, "%" + TCari3.getText().trim() + "%");
                ps3.setString(7, "%" + TCari3.getText().trim() + "%");
                ps3.setString(8, "%" + TCari3.getText().trim() + "%");
                ps3.setString(9, "%" + TCari3.getText().trim() + "%");
                ps3.setString(10, "%" + TCari3.getText().trim() + "%");
                ps3.setString(11, "%" + TCari3.getText().trim() + "%");
                ps3.setString(12, "%" + TCari3.getText().trim() + "%");
                ps3.setString(13, "%" + TCari3.getText().trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode3.addRow(new Object[]{
                        rs3.getString("kode_brng"),
                        rs3.getString("nama_brng"),
                        rs3.getString("kode_sat"),
                        rs3.getString("satuan"),
                        rs3.getString("letak_barang"),
                        rs3.getDouble("h_beli"),
                        rs3.getDouble("ralan"),
                        rs3.getDouble("kelas1"),
                        rs3.getDouble("kelas2"),
                        rs3.getDouble("kelas3"),
                        rs3.getDouble("utama"),
                        rs3.getDouble("vip"),
                        rs3.getDouble("vvip"),
                        rs3.getDouble("beliluar"),
                        rs3.getDouble("jualbebas"),
                        rs3.getDouble("karyawan"),
                        rs3.getString("stokminimal"),
                        rs3.getString("kdjns"),
                        rs3.getString("nama"),
                        rs3.getDouble("kapasitas"),
                        rs3.getString("expire"),
                        rs3.getString("kode_industri"),
                        rs3.getString("nama_industri"),
                        rs3.getString("kode_kategori"),
                        rs3.getString("kategori"),
                        rs3.getString("kode_golongan"),
                        rs3.getString("golongan"),
                        rs3.getString("tipe_brg"),
                        rs3.getString("high_alert")
                    });
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount3.setText("" + tabMode3.getRowCount());
    }
}
