package simrskhanza;

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
import java.time.LocalDate;
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
public class DlgSpirometri extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String kodedok = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgSpirometri(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Usia", "Tinggi Bdn.",
            "Brt. Bdn.", "Tmpt. Periksa", "Keluhan", "Kebiasaan Merokok", "Riw. Asma",
            "Pengukuran VC", "Pengukuran FVC", "Pengukuran FEV1", "Pengukuran FEV1/FVC",
            "Prediksi VC", "Prediksi FVC", "Prediksi FEV1", "Prediksi FEV1/FVC",
            "VC (%)", "FVC (%)", "FEV1 (%)", "FEV1/FVC (%)", "Kesimpulan", "Tgl. Periksa",
            "Berlaku S.D", "Nama Dokter", "kddokter", "tglperiksa"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbSpirometri.setModel(tabMode);
        tbSpirometri.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSpirometri.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 29; i++) {
            TableColumn column = tbSpirometri.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(200);
            } else if (i == 10) {
                column.setPreferredWidth(200);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(140);
            } else if (i == 15) {
                column.setPreferredWidth(85);
            } else if (i == 16) {
                column.setPreferredWidth(85);
            } else if (i == 17) {
                column.setPreferredWidth(100);
            } else if (i == 18) {
                column.setPreferredWidth(100);
            } else if (i == 19) {
                column.setPreferredWidth(50);
            } else if (i == 20) {
                column.setPreferredWidth(50);
            } else if (i == 21) {
                column.setPreferredWidth(65);
            } else if (i == 22) {
                column.setPreferredWidth(75);
            } else if (i == 23) {
                column.setPreferredWidth(250);
            } else if (i == 24) {
                column.setPreferredWidth(75);
            } else if (i == 25) {
                column.setPreferredWidth(100);
            } else if (i == 26) {
                column.setPreferredWidth(250);
            } else if (i == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbSpirometri.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Ttb.setDocument(new batasInput((int) 3).getKata(Ttb));
        Tbb.setDocument(new batasInput((int) 3).getKata(Tbb));
        TkebiasaanRokok.setDocument(new batasInput((int) 150).getKata(TkebiasaanRokok));
        TukurVC.setDocument(new batasInput((byte) 10).getKata(TukurVC));
        TukurFVC.setDocument(new batasInput((byte) 10).getKata(TukurFVC));
        TukurFEV1.setDocument(new batasInput((byte) 10).getKata(TukurFEV1));
        TukurFEV1_FVC.setDocument(new batasInput((byte) 10).getKata(TukurFEV1_FVC));
        TpredVC.setDocument(new batasInput((byte) 10).getKata(TpredVC));
        TpredFVC.setDocument(new batasInput((byte) 10).getKata(TpredFVC));
        TpredFEV1.setDocument(new batasInput((byte) 10).getKata(TpredFEV1));
        TpredFEV1_FVC.setDocument(new batasInput((byte) 10).getKata(TpredFEV1_FVC));        
        TpersenVC.setDocument(new batasInput((byte) 8).getKata(TpersenVC));
        TpersenFVC.setDocument(new batasInput((byte) 8).getKata(TpersenFVC));
        TpersenFEV1.setDocument(new batasInput((byte) 8).getKata(TpersenFEV1));
        TpersenFEV1_FVC.setDocument(new batasInput((byte) 8).getKata(TpersenFEV1_FVC));
        
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

        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel29 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel30 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        TTmptPeriksa = new widget.TextBox();
        Tjk = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tusia = new widget.TextBox();
        jLabel10 = new widget.Label();
        Ttb = new widget.TextBox();
        jLabel11 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        Tkeluhan = new widget.TextArea();
        jLabel15 = new widget.Label();
        TkebiasaanRokok = new widget.TextBox();
        jLabel16 = new widget.Label();
        TriwAsma = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        jLabel19 = new widget.Label();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        jLabel22 = new widget.Label();
        TukurVC = new widget.TextBox();
        TukurFVC = new widget.TextBox();
        TukurFEV1 = new widget.TextBox();
        TukurFEV1_FVC = new widget.TextBox();
        jLabel23 = new widget.Label();
        TpredVC = new widget.TextBox();
        TpredFVC = new widget.TextBox();
        TpredFEV1 = new widget.TextBox();
        TpredFEV1_FVC = new widget.TextBox();
        jLabel24 = new widget.Label();
        TpersenVC = new widget.TextBox();
        TpersenFVC = new widget.TextBox();
        TpersenFEV1 = new widget.TextBox();
        TpersenFEV1_FVC = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        tglPeriksa = new widget.Tanggal();
        TtglBerlaku = new widget.TextBox();
        jLabel27 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        Tkesimpulan = new widget.TextArea();
        jLabel28 = new widget.Label();
        cmbDokter = new widget.ComboBox();
        jLabel31 = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSpirometri = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Hasil Pemeriksaan Spirometri Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        panelGlass8.add(BtnHapus);

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

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tgl. Periksa :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(jLabel29);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-09-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("s.d.");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel30);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-09-2023" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 360));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 140, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(145, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        FormInput.add(TNoRm);
        TNoRm.setBounds(272, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(347, 10, 350, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jenis Kelamin :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 38, 140, 23);

        TTmptPeriksa.setEditable(false);
        TTmptPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        TTmptPeriksa.setName("TTmptPeriksa"); // NOI18N
        FormInput.add(TTmptPeriksa);
        TTmptPeriksa.setBounds(145, 66, 350, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(145, 38, 80, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Usia :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(227, 38, 40, 23);

        Tusia.setEditable(false);
        Tusia.setForeground(new java.awt.Color(0, 0, 0));
        Tusia.setName("Tusia"); // NOI18N
        FormInput.add(Tusia);
        Tusia.setBounds(272, 38, 60, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tinggi Badan :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(337, 38, 80, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(423, 38, 60, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Cm.    Berat Badan :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(487, 38, 100, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(590, 38, 60, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Kg.");
        jLabel12.setToolTipText("");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(654, 38, 30, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tempat Pemeriksaan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 66, 140, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Anamnesis : Keluhan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 94, 140, 23);

        scrollPane2.setName("scrollPane2"); // NOI18N

        Tkeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkeluhan.setColumns(20);
        Tkeluhan.setRows(5);
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.setPreferredSize(new java.awt.Dimension(162, 140));
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Tkeluhan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(145, 94, 550, 70);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Kebiasaan Merokok :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 170, 140, 23);

        TkebiasaanRokok.setForeground(new java.awt.Color(0, 0, 0));
        TkebiasaanRokok.setName("TkebiasaanRokok"); // NOI18N
        TkebiasaanRokok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkebiasaanRokokKeyPressed(evt);
            }
        });
        FormInput.add(TkebiasaanRokok);
        TkebiasaanRokok.setBounds(145, 170, 550, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Riwayat Asma :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 198, 140, 23);

        TriwAsma.setForeground(new java.awt.Color(0, 0, 0));
        TriwAsma.setName("TriwAsma"); // NOI18N
        TriwAsma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwAsmaKeyPressed(evt);
            }
        });
        FormInput.add(TriwAsma);
        TriwAsma.setBounds(145, 198, 550, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Parameter :");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(710, 10, 80, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("VC :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(710, 38, 80, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("FVC :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(710, 66, 80, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("FEV1 :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(710, 94, 80, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("FEV1 / FVC :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(710, 122, 80, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Pengukuran");
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(795, 10, 80, 23);

        TukurVC.setForeground(new java.awt.Color(0, 0, 0));
        TukurVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TukurVC.setName("TukurVC"); // NOI18N
        TukurVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TukurVCKeyPressed(evt);
            }
        });
        FormInput.add(TukurVC);
        TukurVC.setBounds(795, 38, 80, 23);

        TukurFVC.setForeground(new java.awt.Color(0, 0, 0));
        TukurFVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TukurFVC.setName("TukurFVC"); // NOI18N
        TukurFVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TukurFVCKeyPressed(evt);
            }
        });
        FormInput.add(TukurFVC);
        TukurFVC.setBounds(795, 66, 80, 23);

        TukurFEV1.setForeground(new java.awt.Color(0, 0, 0));
        TukurFEV1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TukurFEV1.setName("TukurFEV1"); // NOI18N
        TukurFEV1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TukurFEV1KeyPressed(evt);
            }
        });
        FormInput.add(TukurFEV1);
        TukurFEV1.setBounds(795, 94, 80, 23);

        TukurFEV1_FVC.setForeground(new java.awt.Color(0, 0, 0));
        TukurFEV1_FVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TukurFEV1_FVC.setName("TukurFEV1_FVC"); // NOI18N
        TukurFEV1_FVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TukurFEV1_FVCKeyPressed(evt);
            }
        });
        FormInput.add(TukurFEV1_FVC);
        TukurFEV1_FVC.setBounds(795, 122, 80, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Prediksi");
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(880, 10, 80, 23);

        TpredVC.setForeground(new java.awt.Color(0, 0, 0));
        TpredVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpredVC.setName("TpredVC"); // NOI18N
        TpredVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpredVCKeyPressed(evt);
            }
        });
        FormInput.add(TpredVC);
        TpredVC.setBounds(882, 38, 80, 23);

        TpredFVC.setForeground(new java.awt.Color(0, 0, 0));
        TpredFVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpredFVC.setName("TpredFVC"); // NOI18N
        TpredFVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpredFVCKeyPressed(evt);
            }
        });
        FormInput.add(TpredFVC);
        TpredFVC.setBounds(882, 66, 80, 23);

        TpredFEV1.setForeground(new java.awt.Color(0, 0, 0));
        TpredFEV1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpredFEV1.setName("TpredFEV1"); // NOI18N
        TpredFEV1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpredFEV1KeyPressed(evt);
            }
        });
        FormInput.add(TpredFEV1);
        TpredFEV1.setBounds(882, 94, 80, 23);

        TpredFEV1_FVC.setForeground(new java.awt.Color(0, 0, 0));
        TpredFEV1_FVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpredFEV1_FVC.setName("TpredFEV1_FVC"); // NOI18N
        TpredFEV1_FVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpredFEV1_FVCKeyPressed(evt);
            }
        });
        FormInput.add(TpredFEV1_FVC);
        TpredFEV1_FVC.setBounds(882, 122, 80, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Prediksi (%)");
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(966, 10, 80, 23);

        TpersenVC.setForeground(new java.awt.Color(0, 0, 0));
        TpersenVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpersenVC.setName("TpersenVC"); // NOI18N
        TpersenVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpersenVCKeyPressed(evt);
            }
        });
        FormInput.add(TpersenVC);
        TpersenVC.setBounds(976, 38, 60, 23);

        TpersenFVC.setForeground(new java.awt.Color(0, 0, 0));
        TpersenFVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpersenFVC.setName("TpersenFVC"); // NOI18N
        TpersenFVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpersenFVCKeyPressed(evt);
            }
        });
        FormInput.add(TpersenFVC);
        TpersenFVC.setBounds(976, 66, 60, 23);

        TpersenFEV1.setForeground(new java.awt.Color(0, 0, 0));
        TpersenFEV1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpersenFEV1.setName("TpersenFEV1"); // NOI18N
        TpersenFEV1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpersenFEV1KeyPressed(evt);
            }
        });
        FormInput.add(TpersenFEV1);
        TpersenFEV1.setBounds(976, 94, 60, 23);

        TpersenFEV1_FVC.setForeground(new java.awt.Color(0, 0, 0));
        TpersenFEV1_FVC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpersenFEV1_FVC.setName("TpersenFEV1_FVC"); // NOI18N
        TpersenFEV1_FVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpersenFEV1_FVCKeyPressed(evt);
            }
        });
        FormInput.add(TpersenFEV1_FVC);
        TpersenFEV1_FVC.setBounds(976, 122, 60, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tgl. Periksa :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(710, 150, 80, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Berlaku s.d Tgl. :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(700, 178, 90, 23);

        tglPeriksa.setEditable(false);
        tglPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-09-2023" }));
        tglPeriksa.setDisplayFormat("dd-MM-yyyy");
        tglPeriksa.setName("tglPeriksa"); // NOI18N
        tglPeriksa.setOpaque(false);
        tglPeriksa.setPreferredSize(new java.awt.Dimension(90, 23));
        tglPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglPeriksaActionPerformed(evt);
            }
        });
        FormInput.add(tglPeriksa);
        tglPeriksa.setBounds(795, 150, 90, 23);

        TtglBerlaku.setEditable(false);
        TtglBerlaku.setForeground(new java.awt.Color(0, 0, 0));
        TtglBerlaku.setName("TtglBerlaku"); // NOI18N
        FormInput.add(TtglBerlaku);
        TtglBerlaku.setBounds(795, 178, 210, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Kesimpulan :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 226, 140, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        Tkesimpulan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkesimpulan.setColumns(20);
        Tkesimpulan.setRows(5);
        Tkesimpulan.setName("Tkesimpulan"); // NOI18N
        Tkesimpulan.setPreferredSize(new java.awt.Dimension(162, 140));
        Tkesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesimpulanKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Tkesimpulan);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(145, 226, 550, 70);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Nama Dokter :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 302, 140, 23);

        cmbDokter.setForeground(new java.awt.Color(0, 0, 0));
        cmbDokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbDokter.setName("cmbDokter"); // NOI18N
        cmbDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDokterActionPerformed(evt);
            }
        });
        FormInput.add(cmbDokter);
        cmbDokter.setBounds(145, 302, 290, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Terhitung utk. 6 bulan kedepan");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(890, 150, 160, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSpirometri.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbSpirometri.setName("tbSpirometri"); // NOI18N
        tbSpirometri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSpirometriMouseClicked(evt);
            }
        });
        tbSpirometri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSpirometriKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSpirometri);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {            
            Sequel.menyimpan("spirometri", "'" + TNoRw.getText() + "','" + TTmptPeriksa.getText() + "','" + Ttb.getText() + "',"
                    + "'" + Tbb.getText() + "','" + Tkeluhan.getText() + "','" + TkebiasaanRokok.getText() + "','" + TriwAsma.getText() + "',"
                    + "'" + TukurVC.getText() + "','" + TukurFVC.getText() + "','" + TukurFEV1.getText() + "','" + TukurFEV1_FVC.getText() + "',"
                    + "'" + TpredVC.getText() + "','" + TpredFVC.getText() + "','" + TpredFEV1.getText() + "','" + TpredFEV1_FVC.getText() + "',"
                    + "'" + TpersenVC.getText() + "','" + TpersenFVC.getText() + "','" + TpersenFEV1.getText() + "','" + TpersenFEV1_FVC.getText() + "',"
                    + "'" + Tkesimpulan.getText() + "','" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "',"
                    + "'" + Sequel.cariIsi("select DATE_ADD('" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "',interval 181 day)") + "',"
                    + "'" + kodedok + "'", "Spirometri Pasien");
            emptTeks();
            tampil();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {          
            if (tbSpirometri.getSelectedRow() > -1) {
                Sequel.mengedit("spirometri", "no_rawat=?", "tmpt_pemeriksaan=?, tb=?, bb=?, "
                        + "keluhan=?, kebiasaan_merokok=?, riwayat_asma=?, pengukuran_vc=?, pengukuran_fvc=?, "
                        + "pengukuran_fev1=?, pengukuran_fev1_fvc=?, prediksi_vc=?, prediksi_fvc=?, prediksi_fev1=?, "
                        + "prediksi_fev1_fvc=?, persen_vc=?, persen_fvc=?, persen_fev1=?, persen_fev1_fvc=?, "
                        + "kesimpulan=?, tgl_periksa=?, tgl_habis_berlaku=?, kd_dokter=?", 23, new String[]{
                            TTmptPeriksa.getText(), Ttb.getText(), Tbb.getText(), Tkeluhan.getText(), TkebiasaanRokok.getText(),
                            TriwAsma.getText(), TukurVC.getText(), TukurFVC.getText(), TukurFEV1.getText(), TukurFEV1_FVC.getText(),
                            TpredVC.getText(), TpredFVC.getText(), TpredFEV1.getText(), TpredFEV1_FVC.getText(), TpersenVC.getText(),
                            TpersenFVC.getText(), TpersenFEV1.getText(), TpersenFEV1_FVC.getText(), Tkesimpulan.getText(),
                            Valid.SetTgl(tglPeriksa.getSelectedItem() + ""), Sequel.cariIsi("select DATE_ADD('" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "',interval 181 day)"), kodedok,
                            tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 0).toString()
                        });
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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

    private void tbSpirometriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSpirometriMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSpirometriMouseClicked

    private void tbSpirometriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSpirometriKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbSpirometriKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TkebiasaanRokok.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void TkesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesimpulanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbDokter.requestFocus();
        }
    }//GEN-LAST:event_TkesimpulanKeyPressed

    private void tglPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglPeriksaActionPerformed
        TtglBerlaku.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select DATE_ADD('" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "',interval 181 day)")) + "");
    }//GEN-LAST:event_tglPeriksaActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSpirometri.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from spirometri where no_rawat=?", 1, new String[]{
                    tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        Valid.pindah(evt, Ttb, Tbb);
    }//GEN-LAST:event_TtbKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, Ttb, Tkeluhan);
    }//GEN-LAST:event_TbbKeyPressed

    private void TkebiasaanRokokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkebiasaanRokokKeyPressed
        Valid.pindah(evt, Tkeluhan, TriwAsma);
    }//GEN-LAST:event_TkebiasaanRokokKeyPressed

    private void TriwAsmaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwAsmaKeyPressed
        Valid.pindah(evt, TkebiasaanRokok, TukurVC);
    }//GEN-LAST:event_TriwAsmaKeyPressed

    private void TukurVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TukurVCKeyPressed
        Valid.pindah(evt, TriwAsma, TpredVC);
    }//GEN-LAST:event_TukurVCKeyPressed

    private void TpredVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpredVCKeyPressed
        Valid.pindah(evt, TukurVC, TpersenVC);
    }//GEN-LAST:event_TpredVCKeyPressed

    private void TpersenVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpersenVCKeyPressed
        Valid.pindah(evt, TpredVC, TukurFVC);
    }//GEN-LAST:event_TpersenVCKeyPressed

    private void TukurFVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TukurFVCKeyPressed
        Valid.pindah(evt, TpersenVC, TpredFVC);
    }//GEN-LAST:event_TukurFVCKeyPressed

    private void TpredFVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpredFVCKeyPressed
        Valid.pindah(evt, TukurFVC, TpersenFVC);
    }//GEN-LAST:event_TpredFVCKeyPressed

    private void TpersenFVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpersenFVCKeyPressed
        Valid.pindah(evt, TpredFEV1, TukurFEV1);
    }//GEN-LAST:event_TpersenFVCKeyPressed

    private void TukurFEV1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TukurFEV1KeyPressed
        Valid.pindah(evt, TpersenFVC, TpredFEV1);
    }//GEN-LAST:event_TukurFEV1KeyPressed

    private void TpredFEV1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpredFEV1KeyPressed
        Valid.pindah(evt, TukurFEV1, TpersenFEV1);
    }//GEN-LAST:event_TpredFEV1KeyPressed

    private void TpersenFEV1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpersenFEV1KeyPressed
        Valid.pindah(evt, TpredFEV1, TukurFEV1_FVC);
    }//GEN-LAST:event_TpersenFEV1KeyPressed

    private void TukurFEV1_FVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TukurFEV1_FVCKeyPressed
        Valid.pindah(evt, TpersenFEV1, TpredFEV1_FVC);
    }//GEN-LAST:event_TukurFEV1_FVCKeyPressed

    private void TpredFEV1_FVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpredFEV1_FVCKeyPressed
        Valid.pindah(evt, TukurFEV1_FVC, TpersenFEV1_FVC);
    }//GEN-LAST:event_TpredFEV1_FVCKeyPressed

    private void TpersenFEV1_FVCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpersenFEV1_FVCKeyPressed
        Valid.pindah(evt, TpredFEV1_FVC, Tkesimpulan);
    }//GEN-LAST:event_TpersenFEV1_FVCKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("select nm_dokter from dokter where kd_sps='S0014' and status='1' order by nm_dokter", cmbDokter);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya sesuai tgl. cppt...!!!!");
        } else {
//            Map<String, Object> param = new HashMap<>();
//            param.put("namars", akses.getnamars());
//            param.put("logo", Sequel.cariGambar("select logo from setting"));
//
//            if (status.equals("Ralan")) {
//                param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (IGD)");
//            } else {
//                param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (RAWAT INAP)");
//            }
//
//            Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT IGD ]::",
//                "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir, '%d-%m-%Y') tgllhr, "
//                + "if(c.cek_jam='ya',concat(date_format(c.tgl_cppt, '%d-%m-%Y'),', ',date_format(c.jam_cppt, '%H:%i')),date_format(c.tgl_cppt, '%d-%m-%Y')) tglcppt, "
//                + "c.bagian, c.hasil_pemeriksaan, c.instruksi_nakes, concat('(',c.verifikasi,') - ',pg.nama) verif "
//                + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
//                + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp WHERE c.no_rawat = '" + TNoRw.getText() + "' "
//                + "AND c. STATUS = 'Ralan' ORDER BY c.waktu_simpan", param);
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void cmbDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDokterActionPerformed
        kodedok = Sequel.cariIsi("select nik from pegawai where nama like '%" + cmbDokter.getSelectedItem().toString() + "%'");
    }//GEN-LAST:event_cmbDokterActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSpirometri dialog = new DlgSpirometri(new javax.swing.JFrame(), true);
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
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TTmptPeriksa;
    private widget.TextBox Tbb;
    private widget.TextBox Tjk;
    private widget.TextBox TkebiasaanRokok;
    private widget.TextArea Tkeluhan;
    private widget.TextArea Tkesimpulan;
    private widget.TextBox TpersenFEV1;
    private widget.TextBox TpersenFEV1_FVC;
    private widget.TextBox TpersenFVC;
    private widget.TextBox TpersenVC;
    private widget.TextBox TpredFEV1;
    private widget.TextBox TpredFEV1_FVC;
    private widget.TextBox TpredFVC;
    private widget.TextBox TpredVC;
    private widget.TextBox TriwAsma;
    private widget.TextBox Ttb;
    private widget.TextBox TtglBerlaku;
    private widget.TextBox TukurFEV1;
    private widget.TextBox TukurFEV1_FVC;
    private widget.TextBox TukurFVC;
    private widget.TextBox TukurVC;
    private widget.TextBox Tusia;
    private widget.ComboBox cmbDokter;
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
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbSpirometri;
    private widget.Tanggal tglPeriksa;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT s.no_rawat, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jk, concat(rp.umurdaftar,' ',sttsumur) usia, "
                    + "s.tb, s.bb, s.tmpt_pemeriksaan, s.keluhan, s.kebiasaan_merokok, s.riwayat_asma, s.pengukuran_vc, s.pengukuran_fvc, s.pengukuran_fev1, "
                    + "s.pengukuran_fev1_fvc, s.prediksi_vc, s.prediksi_fvc, s.prediksi_fev1, s.prediksi_fev1_fvc, s.persen_vc, s.persen_fvc, s.persen_fev1, "
                    + "s.persen_fev1_fvc, s.kesimpulan, date_format(s.tgl_periksa,'%d-%m-%Y') TglPeriksa, s.tgl_habis_berlaku, pg.nama nmdokter, "
                    + "s.kd_dokter, s.tgl_periksa from spirometri s inner join reg_periksa rp on rp.no_rawat=s.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=s.kd_dokter where "
                    + "s.tgl_periksa between ? and ? and s.no_rawat like ? or "
                    + "s.tgl_periksa between ? and ? and p.no_rkm_medis like ? or "
                    + "s.tgl_periksa between ? and ? and p.nm_pasien like ? or "
                    + "s.tgl_periksa between ? and ? and if(p.jk='L','Laki-laki','Perempuan') like ? or "
                    + "s.tgl_periksa between ? and ? and concat(rp.umurdaftar,' ',sttsumur) like ? or "
                    + "s.tgl_periksa between ? and ? and s.tmpt_pemeriksaan like ? or "
                    + "s.tgl_periksa between ? and ? and s.keluhan like ? or "
                    + "s.tgl_periksa between ? and ? and s.kebiasaan_merokok like ? or "
                    + "s.tgl_periksa between ? and ? and s.riwayat_asma like ? or "
                    + "s.tgl_periksa between ? and ? and s.kesimpulan like ? or "
                    + "s.tgl_periksa between ? and ? and pg.nama like ? order by s.tgl_periksa desc");
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
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new Object[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk"),
                        rs.getString("usia"),
                        rs.getString("tb"),
                        rs.getString("bb"),
                        rs.getString("tmpt_pemeriksaan"),
                        rs.getString("keluhan"),
                        rs.getString("kebiasaan_merokok"),
                        rs.getString("riwayat_asma"),
                        rs.getString("pengukuran_vc"),
                        rs.getString("pengukuran_fvc"),
                        rs.getString("pengukuran_fev1"),
                        rs.getString("pengukuran_fev1_fvc"),
                        rs.getString("prediksi_vc"),
                        rs.getString("prediksi_fvc"),
                        rs.getString("prediksi_fev1"),
                        rs.getString("prediksi_fev1_fvc"),
                        rs.getString("persen_vc"),
                        rs.getString("persen_fvc"),
                        rs.getString("persen_fev1"),
                        rs.getString("persen_fev1_fvc"),
                        rs.getString("kesimpulan"),
                        rs.getString("TglPeriksa"),
                        Valid.SetTglINDONESIA(rs.getString("tgl_habis_berlaku")),
                        rs.getString("nmdokter"),
                        rs.getString("kd_dokter"),
                        rs.getString("tgl_periksa")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgSpirometri.tampil() : " + e);
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
        TNoRm.setText("");
        TPasien.setText("");
        Tjk.setText("");
        Tusia.setText("");
        Tbb.setText("");
        Ttb.setText("");
        TTmptPeriksa.setText("");
        Tkeluhan.setText("");
        TkebiasaanRokok.setText("");
        TriwAsma.setText("");
        
        TukurVC.setText("");
        TukurFVC.setText("");
        TukurFEV1.setText("");
        TukurFEV1_FVC.setText("");
        
        TpredVC.setText("");
        TpredFVC.setText("");
        TpredFEV1.setText("");
        TpredFEV1_FVC.setText("");
        
        TpersenVC.setText("");
        TpersenFVC.setText("");
        TpersenFEV1.setText("");
        TpersenFEV1_FVC.setText("");
        
        tglPeriksa.setDate(new Date());
        Tkesimpulan.setText("");
        cmbDokter.setSelectedIndex(0);
        Ttb.requestFocus();
        TtglBerlaku.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select DATE_ADD('" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "',interval 181 day)")) + "");
    }

    private void getData() {
        kodedok = "";
        if (tbSpirometri.getSelectedRow() != -1) {
            TNoRw.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 0).toString());
            TNoRm.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 1).toString());
            TPasien.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 2).toString());
            Tjk.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 3).toString());
            Tusia.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 4).toString());
            Ttb.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 5).toString());
            Tbb.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 6).toString());
            TTmptPeriksa.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 7).toString());
            Tkeluhan.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 8).toString());
            TkebiasaanRokok.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 9).toString());
            TriwAsma.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 10).toString());
            TukurVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 11).toString());
            TukurFVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 12).toString());
            TukurFEV1.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 13).toString());
            TukurFEV1_FVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 14).toString());            
            TpredVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 15).toString());
            TpredFVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 16).toString());
            TpredFEV1.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 17).toString());
            TpredFEV1_FVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 18).toString());            
            TpersenVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 19).toString());
            TpersenFVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 20).toString());
            TpersenFEV1.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 21).toString());
            TpersenFEV1_FVC.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 22).toString());
            Tkesimpulan.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 23).toString());            
            TtglBerlaku.setText(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 25).toString());
            cmbDokter.setSelectedItem(tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 26).toString());
            kodedok = tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 27).toString();
            Valid.SetTgl(tglPeriksa, tbSpirometri.getValueAt(tbSpirometri.getSelectedRow(), 28).toString());
        }
    }
    
    public void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 360));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek() {
        if (akses.getkemenkes_sitt() == true) {
            BtnSimpan.setEnabled(true);
            BtnHapus.setEnabled(true);
            BtnGanti.setEnabled(true);
        } else {
            BtnSimpan.setEnabled(false);
            BtnHapus.setEnabled(false);
            BtnGanti.setEnabled(false);
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String rawat, String kdunit) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        TPasien.setText(nmpasien);
        Tjk.setText(Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + norm + "'"));
        Tusia.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rawat='" + norw + "'"));

        if (rawat.equals("Ralan")) {
            Ttb.setText(Sequel.cariIsi("select ifnull(tinggi,'') from pemeriksaan_ralan_petugas where no_rawat='" + norw + "'"));
            Tbb.setText(Sequel.cariIsi("select ifnull(berat,'') from pemeriksaan_ralan_petugas where no_rawat='" + norw + "'"));
            TTmptPeriksa.setText(Sequel.cariIsi("select concat('Poliklinik ',nm_poli) from poliklinik where kd_poli='" + kdunit + "'"));
            Tkeluhan.setText(Sequel.cariIsi("select ifnull(keluhan,'') from pemeriksaan_ralan where no_rawat='" + norw + "'"));
        } else {
            Ttb.setText("");
            Tbb.setText("");
            TTmptPeriksa.setText(Sequel.cariIsi("SELECT concat('Rg. Perawatan Inap ',b.nm_gedung) FROM kamar k "
                    + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdunit + "'"));
            Tkeluhan.setText("");
        }
        
        TkebiasaanRokok.setText("");
        TriwAsma.setText("");
        TukurVC.setText("");
        TukurFVC.setText("");
        TukurFEV1.setText("");
        TukurFEV1_FVC.setText("");
        
        TpredVC.setText("");
        TpredFVC.setText("");
        TpredFEV1.setText("");
        TpredFEV1_FVC.setText("");
        
        TpersenVC.setText("");
        TpersenFVC.setText("");
        TpersenFEV1.setText("");
        TpersenFEV1_FVC.setText("");
        
        tglPeriksa.setDate(new Date());
        Tkesimpulan.setText("");
        cmbDokter.setSelectedIndex(0);
        Ttb.requestFocus();
        TtglBerlaku.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select DATE_ADD('" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "',interval 181 day)")) + "");
    }
}
