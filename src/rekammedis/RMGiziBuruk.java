package rekammedis;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import laporan.DlgHasilPenunjangMedis;
import simrskhanza.DlgRujukMasuk;

/**
 *
 * @author dosen
 */
public class RMGiziBuruk extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    private String diagnosaAwal = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMGiziBuruk(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.", "No. Rawat", "No. RM", "Nama Pasien", "Alamat",
            "Umur", "Tgl. Input", "Diagnosa Awal", "BB-Awal", "BB-Akhir",
            "PB/TB", "BB/U", "BB/PB", "PB/U", "Perhitng. Keb. Zat Gizi",
            "Diagn. dr. Sp.GZ/Ahli GZ", "Pemberian Nutrisi", "Lab. Albumin", "Lab. Hb", "Lab. Leukosit",
            "Lab. PLT", "Puskesmas Asal"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbGizi.setModel(tabMode);
        tbGizi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbGizi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 22; i++) {
            TableColumn column = tbGizi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(170);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(45);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(50);
            } else if (i == 11) {
                column.setPreferredWidth(50);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(50);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setPreferredWidth(250);
            } else if (i == 16) {
                column.setPreferredWidth(250);
            } else if (i == 17) {
                column.setPreferredWidth(130);
            } else if (i == 18) {
                column.setPreferredWidth(130);
            } else if (i == 19) {
                column.setPreferredWidth(130);
            } else if (i == 20) {
                column.setPreferredWidth(130);
            } else if (i == 21) {
                column.setPreferredWidth(200);
            }
        }
        tbGizi.setDefaultRenderer(Object.class, new WarnaTable());
        
        rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMGiziBuruk")) {
                    if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                        Tfaskes.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                    }
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

        TbbAwal.setDocument(new batasInput((int) 10).getKata(TbbAwal));
        TbbAkhir.setDocument(new batasInput((int) 10).getKata(TbbAkhir));
        Tpbtb.setDocument(new batasInput((int) 10).getKata(Tpbtb));
        TdiagnosaAwal.setDocument(new batasInput((int) 200).getKata(TdiagnosaAwal));        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
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
        
        ChkInput.setSelected(true);
        isForm();
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
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnIndividuPx = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnCetakGB = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel91 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel92 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel71 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel72 = new widget.Label();
        Tumur = new widget.TextBox();
        jLabel73 = new widget.Label();
        TdiagnosaAwal = new widget.TextBox();
        jLabel74 = new widget.Label();
        TbbAwal = new widget.TextBox();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        Tbbu = new widget.TextBox();
        jLabel75 = new widget.Label();
        TbbAkhir = new widget.TextBox();
        jLabel79 = new widget.Label();
        Tbbpb = new widget.TextBox();
        jLabel76 = new widget.Label();
        Tpbtb = new widget.TextBox();
        jLabel80 = new widget.Label();
        Tpbu = new widget.TextBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        Tpemberian = new widget.TextBox();
        TdiagnosaDokter = new widget.TextBox();
        ThitungZat = new widget.TextBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        Talbumin = new widget.TextBox();
        jLabel87 = new widget.Label();
        Tleukosit = new widget.TextBox();
        Tplt = new widget.TextBox();
        jLabel88 = new widget.Label();
        Thb = new widget.TextBox();
        jLabel86 = new widget.Label();
        jLabel89 = new widget.Label();
        Tfaskes = new widget.TextBox();
        btnFaskes = new widget.Button();
        ChkInput = new widget.CekBox();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbGizi = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(195, 26));
        MnHasilPemeriksaanPenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanPenunjangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanPenunjang);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnIndividuPx.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIndividuPx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIndividuPx.setText("Cetak Data Individu Pasien");
        MnIndividuPx.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnIndividuPx.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnIndividuPx.setIconTextGap(5);
        MnIndividuPx.setName("MnIndividuPx"); // NOI18N
        MnIndividuPx.setPreferredSize(new java.awt.Dimension(190, 26));
        MnIndividuPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIndividuPxActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnIndividuPx);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Input Data Gizi Buruk Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
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

        BtnCetakGB.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakGB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCetakGB.setMnemonic('L');
        BtnCetakGB.setText("Cetak Lap. Rekap");
        BtnCetakGB.setToolTipText("Alt+L");
        BtnCetakGB.setName("BtnCetakGB"); // NOI18N
        BtnCetakGB.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnCetakGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakGBActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCetakGB);

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

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Tanggal :");
        jLabel91.setName("jLabel91"); // NOI18N
        jLabel91.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel91);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-07-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari1MouseClicked(evt);
            }
        });
        panelGlass10.add(DTPCari1);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("s.d");
        jLabel92.setName("jLabel92"); // NOI18N
        jLabel92.setPreferredSize(new java.awt.Dimension(20, 23));
        panelGlass10.add(jLabel92);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-07-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari2MouseClicked(evt);
            }
        });
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
        PanelInput.setPreferredSize(new java.awt.Dimension(440, 290));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Pasien : ");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 10, 77, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        FormInput.add(TNoRW);
        TNoRW.setBounds(80, 10, 140, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(224, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(298, 10, 423, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Umur : ");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 37, 77, 23);

        Tumur.setEditable(false);
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        FormInput.add(Tumur);
        Tumur.setBounds(80, 37, 60, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Diagnosa Awal : ");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(144, 37, 90, 23);

        TdiagnosaAwal.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaAwal.setName("TdiagnosaAwal"); // NOI18N
        TdiagnosaAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaAwalKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaAwal);
        TdiagnosaAwal.setBounds(235, 37, 485, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("BB - Awal : ");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 64, 77, 23);

        TbbAwal.setForeground(new java.awt.Color(0, 0, 0));
        TbbAwal.setMaxLenth(10);
        TbbAwal.setName("TbbAwal"); // NOI18N
        TbbAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbAwalKeyPressed(evt);
            }
        });
        FormInput.add(TbbAwal);
        TbbAwal.setBounds(80, 64, 60, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Perhitungan status gizi WHONCHS : ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(144, 64, 180, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("BB / U : ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(327, 64, 50, 23);

        Tbbu.setForeground(new java.awt.Color(0, 0, 0));
        Tbbu.setName("Tbbu"); // NOI18N
        Tbbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbuKeyPressed(evt);
            }
        });
        FormInput.add(Tbbu);
        Tbbu.setBounds(380, 64, 340, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("BB - Akhir : ");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 92, 77, 23);

        TbbAkhir.setForeground(new java.awt.Color(0, 0, 0));
        TbbAkhir.setMaxLenth(10);
        TbbAkhir.setName("TbbAkhir"); // NOI18N
        TbbAkhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbAkhirKeyPressed(evt);
            }
        });
        FormInput.add(TbbAkhir);
        TbbAkhir.setBounds(80, 92, 60, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("BB / PB : ");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(327, 92, 50, 23);

        Tbbpb.setForeground(new java.awt.Color(0, 0, 0));
        Tbbpb.setName("Tbbpb"); // NOI18N
        Tbbpb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbpbKeyPressed(evt);
            }
        });
        FormInput.add(Tbbpb);
        Tbbpb.setBounds(380, 92, 340, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("PB / TB : ");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 119, 77, 23);

        Tpbtb.setForeground(new java.awt.Color(0, 0, 0));
        Tpbtb.setMaxLenth(10);
        Tpbtb.setName("Tpbtb"); // NOI18N
        Tpbtb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpbtbKeyPressed(evt);
            }
        });
        FormInput.add(Tpbtb);
        Tpbtb.setBounds(80, 119, 60, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("PB / U : ");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(327, 119, 50, 23);

        Tpbu.setForeground(new java.awt.Color(0, 0, 0));
        Tpbu.setName("Tpbu"); // NOI18N
        Tpbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpbuKeyPressed(evt);
            }
        });
        FormInput.add(Tpbu);
        Tpbu.setBounds(380, 119, 340, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Perhitungan Keb. Zat Gizi : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 147, 170, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Diagnosa dr. Sp. Gz/Ahli Gizi : ");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 175, 170, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Pemberian Nutrisi : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 203, 170, 23);

        Tpemberian.setForeground(new java.awt.Color(0, 0, 0));
        Tpemberian.setName("Tpemberian"); // NOI18N
        Tpemberian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpemberianKeyPressed(evt);
            }
        });
        FormInput.add(Tpemberian);
        Tpemberian.setBounds(172, 203, 548, 23);

        TdiagnosaDokter.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaDokter.setName("TdiagnosaDokter"); // NOI18N
        TdiagnosaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaDokterKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaDokter);
        TdiagnosaDokter.setBounds(172, 175, 548, 23);

        ThitungZat.setForeground(new java.awt.Color(0, 0, 0));
        ThitungZat.setName("ThitungZat"); // NOI18N
        ThitungZat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThitungZatKeyPressed(evt);
            }
        });
        FormInput.add(ThitungZat);
        ThitungZat.setBounds(172, 147, 548, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Data Pendukung (Hasil Lab.) : ");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(730, 10, 160, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Albumin : ");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(730, 37, 60, 23);

        Talbumin.setForeground(new java.awt.Color(0, 0, 0));
        Talbumin.setName("Talbumin"); // NOI18N
        Talbumin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalbuminKeyPressed(evt);
            }
        });
        FormInput.add(Talbumin);
        Talbumin.setBounds(790, 37, 160, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Leukosit : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(730, 92, 60, 23);

        Tleukosit.setForeground(new java.awt.Color(0, 0, 0));
        Tleukosit.setName("Tleukosit"); // NOI18N
        Tleukosit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TleukositKeyPressed(evt);
            }
        });
        FormInput.add(Tleukosit);
        Tleukosit.setBounds(790, 92, 160, 23);

        Tplt.setForeground(new java.awt.Color(0, 0, 0));
        Tplt.setName("Tplt"); // NOI18N
        Tplt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpltKeyPressed(evt);
            }
        });
        FormInput.add(Tplt);
        Tplt.setBounds(790, 119, 160, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("PLT : ");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(730, 119, 60, 23);

        Thb.setForeground(new java.awt.Color(0, 0, 0));
        Thb.setName("Thb"); // NOI18N
        Thb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThbKeyPressed(evt);
            }
        });
        FormInput.add(Thb);
        Thb.setBounds(790, 64, 160, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Hb : ");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(730, 64, 60, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Puskesmas Daerah Asal : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 230, 170, 23);

        Tfaskes.setEditable(false);
        Tfaskes.setForeground(new java.awt.Color(0, 0, 0));
        Tfaskes.setName("Tfaskes"); // NOI18N
        FormInput.add(Tfaskes);
        Tfaskes.setBounds(172, 230, 352, 23);

        btnFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnFaskes.setMnemonic('3');
        btnFaskes.setToolTipText("Alt+3");
        btnFaskes.setName("btnFaskes"); // NOI18N
        btnFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFaskesActionPerformed(evt);
            }
        });
        btnFaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFaskesKeyPressed(evt);
            }
        });
        FormInput.add(btnFaskes);
        btnFaskes.setBounds(528, 230, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbGizi.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbGizi.setComponentPopupMenu(jPopupMenu2);
        tbGizi.setName("tbGizi"); // NOI18N
        tbGizi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGiziMouseClicked(evt);
            }
        });
        tbGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbGiziKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbGizi);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu pasiennya....!!!!");
        } else if (TbbAwal.getText().equals("") || TbbAkhir.getText().equals("") || Tpbtb.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Berat Badan Awal, Berat Badan Akhir, Panjang Badan/Tinggi Badan harus diisi dulu....!!!!");
            TbbAwal.requestFocus();
        } else {
            Sequel.menyimpan("gizi_buruk", "'" + TNoRW.getText() + "','" + TbbAwal.getText() + "',"
                    + "'" + TbbAkhir.getText() + "','" + Tpbtb.getText() + "','" + Tbbu.getText() + "','" + Tbbpb.getText() + "',"
                    + "'" + Tpbu.getText() + "','" + ThitungZat.getText() + "','" + TdiagnosaDokter.getText() + "',"
                    + "'" + Tpemberian.getText() + "','" + Talbumin.getText() + "','" + Thb.getText() + "','" + Tleukosit.getText() + "',"
                    + "'" + Tplt.getText() + "','" + Tfaskes.getText() + "','" + Sequel.cariIsi("select date(now())") + "' ", "Data gizi buruk");

            Sequel.mengedit("kamar_inap", "no_rawat='" + TNoRW.getText() + "'", "diagnosa_awal='" + TdiagnosaAwal.getText() + "' ");

            tampil();
            emptTeks();
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
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu pasiennya....!!!!");
        } else if (TbbAwal.getText().equals("") || TbbAkhir.getText().equals("") || Tpbtb.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Berat Badan Awal, Berat Badan Akhir, Panjang Badan/Tinggi Badan harus diisi dulu....!!!!");
            TbbAwal.requestFocus();
        } else {     
            Sequel.mengedit("gizi_buruk", "no_rawat='" + TNoRW.getText() + "'", "bb_awal='" + TbbAwal.getText() + "', bb_akhir='" + TbbAkhir.getText() + "',"
                    + "pb_tb='" + Tpbtb.getText() + "', bb_u='" + Tbbu.getText() + "', bb_pb='" + Tbbpb.getText() + "', pb_u='" + Tpbu.getText() + "', "
                    + "penghitungan_zat_gizi='" + ThitungZat.getText() + "', diagnosa_dr_gizi='" + TdiagnosaDokter.getText() + "', pemberian_nutrisi='" + Tpemberian.getText() + "', "
                    + "data_albumin='" + Talbumin.getText() + "', data_hb='" + Thb.getText() + "', data_leukosit='" + Tleukosit.getText() + "', "
                    + "data_plt='" + Tplt.getText() + "', asal_rujukan='" + Tfaskes.getText() + "'");

            Sequel.mengedit("kamar_inap", "no_rawat='" + TNoRW.getText() + "'", "diagnosa_awal='" + TdiagnosaAwal.getText() + "'");

            tampil();
            emptTeks();
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

    private void tbGiziMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGiziMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbGiziMouseClicked

    private void tbGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbGiziKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbGiziKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TdiagnosaAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaAwalKeyPressed
        Valid.pindah(evt, Tumur, TbbAwal);
    }//GEN-LAST:event_TdiagnosaAwalKeyPressed

    private void TbbAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbAwalKeyPressed
        Valid.pindah(evt, TbbAwal, TbbAkhir);
    }//GEN-LAST:event_TbbAwalKeyPressed

    private void TbbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbuKeyPressed
        Valid.pindah(evt, Tpbtb, Tbbpb);
    }//GEN-LAST:event_TbbuKeyPressed

    private void TbbAkhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbAkhirKeyPressed
        Valid.pindah(evt, TbbAwal, Tpbtb);
    }//GEN-LAST:event_TbbAkhirKeyPressed

    private void TbbpbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbpbKeyPressed
        Valid.pindah(evt, Tbbu, Tpbu);
    }//GEN-LAST:event_TbbpbKeyPressed

    private void TpbtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpbtbKeyPressed
        Valid.pindah(evt, TbbAkhir, Tbbu);
    }//GEN-LAST:event_TpbtbKeyPressed

    private void TpbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpbuKeyPressed
        Valid.pindah(evt, Tbbpb, ThitungZat);
    }//GEN-LAST:event_TpbuKeyPressed

    private void TpemberianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpemberianKeyPressed
        Valid.pindah(evt, TdiagnosaDokter, btnFaskes);
    }//GEN-LAST:event_TpemberianKeyPressed

    private void TdiagnosaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaDokterKeyPressed
        Valid.pindah(evt, ThitungZat, Tpemberian);
    }//GEN-LAST:event_TdiagnosaDokterKeyPressed

    private void ThitungZatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThitungZatKeyPressed
        Valid.pindah(evt, Tpbu, TdiagnosaDokter);
    }//GEN-LAST:event_ThitungZatKeyPressed

    private void TalbuminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalbuminKeyPressed
        Valid.pindah(evt, btnFaskes, Thb);
    }//GEN-LAST:event_TalbuminKeyPressed

    private void TleukositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TleukositKeyPressed
        Valid.pindah(evt, Thb, Tplt);
    }//GEN-LAST:event_TleukositKeyPressed

    private void TpltKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpltKeyPressed
        Valid.pindah(evt, Tleukosit, BtnSimpan);
    }//GEN-LAST:event_TpltKeyPressed

    private void ThbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThbKeyPressed
        Valid.pindah(evt, Talbumin, Tleukosit);
    }//GEN-LAST:event_ThbKeyPressed

    private void btnFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFaskesActionPerformed
        akses.setform("RMGiziBuruk");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, internalFrame1.getHeight() - 40);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();
    }//GEN-LAST:event_btnFaskesActionPerformed

    private void btnFaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFaskesKeyPressed
        Valid.pindah(evt, Tpemberian, Talbumin);
    }//GEN-LAST:event_btnFaskesKeyPressed

    private void DTPCari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari1MouseClicked

    private void DTPCari2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari2MouseClicked

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbGizi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from gizi_buruk where no_rawat=?", 1, new String[]{
                    tbGizi.getValueAt(tbGizi.getSelectedRow(), 1).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnCetakGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakGBActionPerformed
        if (tbGizi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tampilkan datanya terlebih dulu pada tabel sesuai tgl. menyimpan data..!!");
        } else if (Sequel.cariInteger("SELECT COUNT(-1) FROM gizi_buruk WHERE tgl_input BETWEEN "
                + " '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data gizi buruk pasien utk periode tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem() + " tidak ditemukan,...!!!");
            DTPCari1.requestFocus();
        } else {
            diagnosaAwal = "";
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("periode", "PERIODE TGL. " + DTPCari1.getSelectedItem() + " S.D " + DTPCari2.getSelectedItem());
            diagnosaAwal = Sequel.cariIsi("select ki.diagnosa_awal from kamar_inap ki inner join gizi_buruk gb on gb.no_rawat=ki.no_rawat where "
                    + "gb.tgl_input BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'");

            Valid.MyReport("rptRekapGiziBuruk.jasper", "report", "::[ Data Rekapitulasi Pasien Gizi Buruk ]::",
                    "SELECT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "CONCAT(rp.umurdaftar,' ',sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, '" + diagnosaAwal + "' diagnosa_awal, gb.bb_awal, "
                    + "gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                    + "gb.data_albumin, gb.data_hb, gb.data_leukosit, gb.data_plt, gb.asal_rujukan, (SELECT COUNT(*) FROM gizi_buruk where "
                    + "tgl_input BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') total_px "
                    + "FROM gizi_buruk gb INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + "where gb.tgl_input BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnCetakGBActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRW.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            akses.setform("RMGiziBuruk");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRW.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnIndividuPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIndividuPxActionPerformed
        if (tbGizi.getSelectedRow() > -1) {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("diagnosa", TdiagnosaAwal.getText());
            param.put("tglsurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
            Valid.MyReport("rptDataGiziBurukPX.jasper", "report", "::[ Data Individu Pasien Gizi Buruk ]::",
                " SELECT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                + " CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                + " CONCAT(rp.umurdaftar,' ',sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, gb.bb_awal, "
                + " gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                + " gb.data_albumin, gb.data_hb, gb.data_leukosit, gb.data_plt, gb.asal_rujukan FROM gizi_buruk gb "
                + " INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                + " INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                + " INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                + " where gb.no_rawat='" + TNoRW.getText() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbGizi.requestFocus();
        }
    }//GEN-LAST:event_MnIndividuPxActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMGiziBuruk dialog = new RMGiziBuruk(new javax.swing.JFrame(), true);
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
    public widget.Button BtnCetakGB;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnIndividuPx;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TPasien;
    private widget.TextBox Talbumin;
    private widget.TextBox TbbAkhir;
    private widget.TextBox TbbAwal;
    private widget.TextBox Tbbpb;
    private widget.TextBox Tbbu;
    private widget.TextBox TdiagnosaAwal;
    private widget.TextBox TdiagnosaDokter;
    private widget.TextBox Tfaskes;
    private widget.TextBox Thb;
    private widget.TextBox ThitungZat;
    private widget.TextBox Tleukosit;
    private widget.TextBox Tpbtb;
    private widget.TextBox Tpbu;
    private widget.TextBox Tpemberian;
    private widget.TextBox Tplt;
    private widget.TextBox Tumur;
    private widget.Button btnFaskes;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbGizi;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT DISTINCT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + " CONCAT(rp.umurdaftar,' ',rp.sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, gb.bb_awal, "
                    + " gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                    + " gb.data_albumin, gb.data_hb, gb.data_leukosit, gb.data_plt, gb.asal_rujukan FROM gizi_buruk gb "
                    + " INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                    + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "                    
                    + " INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + " INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab where "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.no_rawat like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.no_rkm_medis like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.nm_pasien like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.alamat like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and rp.umurdaftar like ? or "                    
                    + " gb.tgl_input BETWEEN ? AND ? and gb.bb_awal like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.bb_akhir like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.pb_tb like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.penghitungan_zat_gizi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.diagnosa_dr_gizi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.pemberian_nutrisi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.asal_rujukan like ? ORDER BY gb.tgl_input DESC");
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
                x = 1;
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        x + ".",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + rs.getString(1) + "' limit 1"),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20)
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
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
        TNoRW.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        Tumur.setText("");
        TdiagnosaAwal.setText("");
        TbbAwal.setText("");
        TbbAkhir.setText("");
        Tpbtb.setText("");
        Tbbu.setText("");
        Tbbpb.setText("");
        Tpbu.setText("");
        ThitungZat.setText("");
        TdiagnosaDokter.setText("");
        Tpemberian.setText("");
        Talbumin.setText("");
        Thb.setText("");
        Tleukosit.setText("");
        Tplt.setText("");
        Tfaskes.setText("");
        TCari.setText("");
    }

    private void getData() {
        if (tbGizi.getSelectedRow() != -1) {
            TNoRW.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 1).toString());
            TNoRM.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 2).toString());
            TPasien.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 3).toString());
            Tumur.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 5).toString());
            TdiagnosaAwal.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 7).toString());
            TbbAwal.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 8).toString());
            TbbAkhir.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 9).toString());
            Tpbtb.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 10).toString());
            Tbbu.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 11).toString());
            Tbbpb.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 12).toString());
            Tpbu.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 13).toString());
            ThitungZat.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 14).toString());
            TdiagnosaDokter.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 15).toString());
            Tpemberian.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 16).toString());
            Talbumin.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 17).toString());
            Thb.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 18).toString());
            Tleukosit.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 19).toString());
            Tplt.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 20).toString());
            Tfaskes.setText(tbGizi.getValueAt(tbGizi.getSelectedRow(), 21).toString());
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 290));
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
        BtnSimpan.setEnabled(akses.getgizi_buruk());
        BtnGanti.setEnabled(akses.getgizi_buruk());
        BtnHapus.setEnabled(akses.getgizi_buruk());
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {            
            if (Sequel.queryu2tf("delete from gizi_buruk where no_rawat=?", 1, new String[]{
                tbGizi.getValueAt(tbGizi.getSelectedRow(), 0).toString()
            }) == true) {
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void tampilGiziBuruk(String norw) {
        try {
            ps1 = koneksi.prepareStatement("select * from gizi_buruk where no_rawat='" + norw + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TbbAwal.setText(rs1.getString("bb_awal"));
                    TbbAkhir.setText(rs1.getString("bb_akhir"));
                    Tpbtb.setText(rs1.getString("pb_tb"));
                    Tbbu.setText(rs1.getString("bb_u"));
                    Tbbpb.setText(rs1.getString("bb_pb"));
                    Tpbu.setText(rs1.getString("pb_u"));
                    ThitungZat.setText(rs1.getString("penghitungan_zat_gizi"));
                    TdiagnosaDokter.setText(rs1.getString("diagnosa_dr_gizi"));
                    Tpemberian.setText(rs1.getString("pemberian_nutrisi"));
                    Talbumin.setText(rs1.getString("data_albumin"));
                    Thb.setText(rs1.getString("data_hb"));
                    Tleukosit.setText(rs1.getString("data_leukosit"));
                    Tplt.setText(rs1.getString("data_plt"));
                    Tfaskes.setText(rs1.getString("asal_rujukan"));
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
    
    public void setData(String norw, String norm, String nmpasien) {
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        Tumur.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rawat='" + norw + "'"));
        TdiagnosaAwal.setText(Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + norw + "' limit 1"));
        Tfaskes.setText(Sequel.cariIsi("select ifnull(perujuk,'') from rujuk_masuk where no_rawat='" + norw + "'"));
        
        if (Sequel.cariInteger("SELECT count(-1) FROM gizi_buruk where no_rawat='" + norw + "'") > 0) {
            tampilGiziBuruk(norw);
        }
    }
}
