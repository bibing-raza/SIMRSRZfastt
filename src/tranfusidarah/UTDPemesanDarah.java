package tranfusidarah;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class UTDPemesanDarah extends javax.swing.JDialog {
    private final DefaultTableModel tabMode1, tabMode2;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps1, ps2;
    private ResultSet rs1, rs2;
    private int i = 0, jml = 0, s = 0, index = 0, x = 0;
    private boolean[] pilih;
    private String[] nokantong, komponen, gd, resus, aftap, kadaluarsa, asaldarah, status, biaya;
    private String sttsrawat = "", user = "";

    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public UTDPemesanDarah(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        Object[] row = {"Cek", "No. Kantong", "Komponen", "G.D", "Rhesus", "Tgl. Aftap", "Tgl. Kadaluarsa",
            "Asal Darah", "Status", "Biaya"};
        tabMode1 = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbDarah.setModel(tabMode1);
        tbDarah.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDarah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbDarah.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(35);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(95);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            }
        }
        tbDarah.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbDarah.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbDarah.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbDarah.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbDarah.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbDarah.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Cek", "No. Rawat", "No. Kantong", "No. RM", "Nama Pasien", "Cara Bayar", "Jns. Rawat", "Rg. Rawat/Poli/Inst.", "Jns. Darah",
            "G.D", "Rhesus", "Tgl. Aftap", "Tgl. Kadaluarsa", "Tgl. Pesan", "Asal Darah", "Status", "Biaya", "tgl_pesan", "waktu_simpan",
            "nip_petugas", "nmpetugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbPemesan.setModel(tabMode2);
        tbPemesan.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPemesan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 21; i++) {
            TableColumn column = tbPemesan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(125);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(50);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(75);
            } else if (i == 14) {
                column.setPreferredWidth(80);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(80);
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
            }
        }
        tbPemesan.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPemesan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPemesan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPemesan.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbPemesan.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbPemesan.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
        tbPemesan.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        tbPemesan.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
        tbPemesan.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
        tbPemesan.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
          
        TCariDarah.setDocument(new batasInput((byte) 100).getKata(TCariDarah));
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
        
        ChkInput.setSelected(false);
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

        Popup = new javax.swing.JPopupMenu();
        ppCetak = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelisi2 = new widget.panelisi();
        jLabel8 = new widget.Label();
        Ttgl1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        Ttgl2 = new widget.Tanggal();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnKeluar = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        tbPemesan = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel25 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel12 = new widget.Label();
        TtglPesan = new widget.Tanggal();
        jLabel13 = new widget.Label();
        Tunit = new widget.TextBox();
        scrollPane2 = new widget.ScrollPane();
        tbDarah = new widget.Table();
        label11 = new widget.Label();
        TCariDarah = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnLihat = new widget.Button();
        ChkInput = new widget.CekBox();

        Popup.setName("Popup"); // NOI18N

        ppCetak.setBackground(new java.awt.Color(242, 242, 242));
        ppCetak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppCetak.setText("Cetak Pesanan");
        ppCetak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCetak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCetak.setIconTextGap(8);
        ppCetak.setName("ppCetak"); // NOI18N
        ppCetak.setPreferredSize(new java.awt.Dimension(150, 25));
        ppCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppCetak);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Pemesan Kantong Darah ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi2.setBackground(new java.awt.Color(255, 150, 255));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Pesan :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel8);

        Ttgl1.setEditable(false);
        Ttgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-11-2025" }));
        Ttgl1.setDisplayFormat("dd-MM-yyyy");
        Ttgl1.setName("Ttgl1"); // NOI18N
        Ttgl1.setOpaque(false);
        Ttgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi2.add(Ttgl1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelisi2.add(jLabel21);

        Ttgl2.setEditable(false);
        Ttgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-11-2025" }));
        Ttgl2.setDisplayFormat("dd-MM-yyyy");
        Ttgl2.setName("Ttgl2"); // NOI18N
        Ttgl2.setOpaque(false);
        Ttgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi2.add(Ttgl2);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label9);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
        panelisi2.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelisi2.add(BtnAll);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi2.add(LCount);

        jPanel2.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16i.png"))); // NOI18N
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
        panelisi1.add(BtnSimpan);

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
        panelisi1.add(BtnBatal);

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
        panelisi1.add(BtnHapus);

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

        jPanel2.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        scrollPane1.setComponentPopupMenu(Popup);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPemesan.setAutoCreateRowSorter(true);
        tbPemesan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPemesan.setComponentPopupMenu(Popup);
        tbPemesan.setName("tbPemesan"); // NOI18N
        tbPemesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemesanMouseClicked(evt);
            }
        });
        tbPemesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemesanKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbPemesan);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(660, 330));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(660, 160));
        FormInput.setLayout(null);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Pasien :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 415, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Pesan Darah :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        TtglPesan.setEditable(false);
        TtglPesan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-11-2025" }));
        TtglPesan.setDisplayFormat("dd-MM-yyyy");
        TtglPesan.setName("TtglPesan"); // NOI18N
        TtglPesan.setOpaque(false);
        TtglPesan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPesan);
        TtglPesan.setBounds(115, 38, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Rg. Rawat/Poli/Inst. :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(206, 38, 130, 23);

        Tunit.setEditable(false);
        Tunit.setForeground(new java.awt.Color(0, 0, 0));
        Tunit.setName("Tunit"); // NOI18N
        FormInput.add(Tunit);
        Tunit.setBounds(342, 38, 392, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Stok Darah Tersedia ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbDarah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDarah.setToolTipText("Silahkan conteng untuk memilih jenis darah");
        tbDarah.setName("tbDarah"); // NOI18N
        scrollPane2.setViewportView(tbDarah);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(30, 66, 700, 200);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Cari Jenis Darah :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(0, 270, 110, 23);

        TCariDarah.setForeground(new java.awt.Color(0, 0, 0));
        TCariDarah.setName("TCariDarah"); // NOI18N
        TCariDarah.setPreferredSize(new java.awt.Dimension(350, 23));
        TCariDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariDarahKeyPressed(evt);
            }
        });
        FormInput.add(TCariDarah);
        TCariDarah.setBounds(114, 270, 250, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        FormInput.add(BtnCari1);
        BtnCari1.setBounds(365, 270, 130, 23);

        BtnLihat.setForeground(new java.awt.Color(0, 0, 0));
        BtnLihat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnLihat.setMnemonic('M');
        BtnLihat.setText("Lihat Semua Stok");
        BtnLihat.setToolTipText("Alt+M");
        BtnLihat.setName("BtnLihat"); // NOI18N
        BtnLihat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLihatActionPerformed(evt);
            }
        });
        FormInput.add(BtnLihat);
        BtnLihat.setBounds(505, 270, 150, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbPemesan.requestFocus();
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

    private void tbPemesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemesanMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPemesanMouseClicked

    private void tbPemesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemesanKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPemesanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        x = 0;
        for (i = 0; i < tbPemesan.getRowCount(); i++) {
            if (tbPemesan.getValueAt(i, 0).toString().equals("true")) {
                x++;
            }
        }

        if (x == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan conteng dulu datanya pada tabel..!!!!");
            emptTeks();
            tampil();
            tampilStok();
            tbPemesan.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data ini akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                for (i = 0; i < tbPemesan.getRowCount(); i++) {
                    if (Sequel.cariRegistrasi(tbPemesan.getValueAt(i, 1).toString()) > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                        for (i = 0; i < tbPemesan.getRowCount(); i++) {
                            tbPemesan.setValueAt(Boolean.FALSE, i, 0);
                        }

                        tampil();
                        tampilStok();
                    } else {
                        if (tbPemesan.getValueAt(i, 19).toString().equals(akses.getkode()) || akses.getadmin() == true) {
                            if (tbPemesan.getValueAt(i, 0).toString().equals("true")) {
                                if (Sequel.queryu2tf("delete from utd_pemesan_darah where waktu_simpan=?", 1, new String[]{
                                    tbPemesan.getValueAt(i, 18).toString()}) == true) {
                                    Sequel.mengedit("utd_stok_darah", "no_kantong='" + tbPemesan.getValueAt(i, 2).toString() + "'", "status='Ada'");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Maaf, darah yang dipesan hanya bisa dihapus oleh " + tbPemesan.getValueAt(i, 20).toString() + " ...");
                            for (i = 0; i < tbPemesan.getRowCount(); i++) {
                                tbPemesan.setValueAt(Boolean.FALSE, i, 0);
                            }

                            tampil();
                            tampilStok();
                        }
                    }
                }
                                
                for (i = 0; i < tbPemesan.getRowCount(); i++) {
                    tbPemesan.setValueAt(Boolean.FALSE, i, 0);
                }
                
                tampil();
                tampilStok();
            } else {
                for (i = 0; i < tbPemesan.getRowCount(); i++) {
                    tbPemesan.setValueAt(Boolean.FALSE, i, 0);
                }

                emptTeks();
                tampil();
                tampilStok();
            }
        }       
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else {
            if (tbPemesan.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
//            Valid.MyReport("rptKomponenDarah.jasper", "report", "::[ Data Komponen Darah ]::",
//                    "select * from utd_komponen_darah where kode like '%" + TCari.getText().trim() + "%' or nama like '%" + TCari.getText().trim() + "%' order by nama", param);
//            
//            emptTeks();
//            tampil();
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!!!");
                emptTeks();
                tampil();
                tampilStok();
                tbPemesan.requestFocus();
            }
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
            dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (akses.getadmin() == true) {
                user = "-";
            } else {
                user = akses.getkode();
            }
            
            x = 0;
            for (i = 0; i < tbDarah.getRowCount(); i++) {
                if (tbDarah.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }
            
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu jenis darah yang dipilih...");
                tbDarah.requestFocus();
            } else {
                try {
                    if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi. Silahkan hubungi bagian kasir/keuangan ..!!");
                        for (i = 0; i < tbDarah.getRowCount(); i++) {
                            tbDarah.setValueAt(Boolean.FALSE, i, 0);
                        }
                        tampil();
                        tampilStok();
                    } else {
                        for (i = 0; i < tbDarah.getRowCount(); i++) {
                            if (tbDarah.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.menyimpanIgnore("utd_pemesan_darah",
                                        "'" + TNoRw.getText() + "',"
                                        + "'" + tbDarah.getValueAt(i, 1).toString() + "',"
                                        + "'" + Valid.SetTgl(TtglPesan.getSelectedItem() + "") + "',"
                                        + "'" + Tunit.getText() + "',"
                                        + "'" + sttsrawat + "',"
                                        + "'" + user + "',"
                                        + "'" + Sequel.cariIsi("select now()") + "'", "Jenis Darah");

                                Sequel.mengedit("utd_stok_darah", "no_kantong='" + tbDarah.getValueAt(i, 1).toString() + "'", "status='Dipesan'");
                            }

                            //jeda 1 detik
                            Thread.sleep(1000);
                        }
                    }

                    for (i = 0; i < tbDarah.getRowCount(); i++) {
                        tbDarah.setValueAt(Boolean.FALSE, i, 0);
                    }
                    
                    emptTeks();
                    tampil();
                    tampilStok();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm(); 
        emptTeks();
        tampil();
        tampilStok();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
            tampil();
            tampilStok();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        tampilStok();
    }//GEN-LAST:event_formWindowOpened

    private void TCariDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariDarahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCariDarahKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilStok();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLihatActionPerformed
        for (i = 0; i < tbDarah.getRowCount(); i++) {
            tbDarah.setValueAt(Boolean.FALSE, i, 0);
        }
        
        TCariDarah.setText("");
        tampilStok();
    }//GEN-LAST:event_BtnLihatActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UTDPemesanDarah dialog = new UTDPemesanDarah(new javax.swing.JFrame(), true);
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
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnLihat;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox TCari;
    private widget.TextBox TCariDarah;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Ttgl1;
    private widget.Tanggal Ttgl2;
    private widget.Tanggal TtglPesan;
    private widget.TextBox Tunit;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel21;
    private widget.Label jLabel25;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel2;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private javax.swing.JMenuItem ppCetak;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbDarah;
    private widget.Table tbPemesan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("select upd.no_rawat, us.no_kantong, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, if(upd.status_lanjut='Ranap','R. Inap','R. Jalan') jnsrawat, "
                    + "upd.nm_unit, uk.nama darah, us.golongan_darah, us.resus, date_format(us.tanggal_aftap,'%d-%m-%Y') tglAftap, "
                    + "date_format(us.tanggal_kadaluarsa,'%d-%m-%Y') tglkada, date_format(upd.tgl_pesan,'%d-%m-%Y') tglpesan, upd.nip_petugas, pg.nama nmpetugas, "
                    + "us.asal_darah, us.status, format(uk.total,0) totalFormat, upd.tgl_pesan, date_format(upd.waktu_simpan,'%Y-%m-%d %H:%i:%s') wktsimpan "
                    + "from utd_komponen_darah uk inner join utd_stok_darah us on us.kode_komponen=uk.kode inner join utd_pemesan_darah upd on upd.no_kantong=us.no_kantong "
                    + "inner join reg_periksa rp on rp.no_rawat=upd.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                    + "inner join pegawai pg on pg.nik=upd.nip_petugas where "
                    + "upd.tgl_pesan between ? and ? and upd.no_rawat like ? or "
                    + "upd.tgl_pesan between ? and ? and us.no_kantong like ? or "
                    + "upd.tgl_pesan between ? and ? and p.no_rkm_medis like ? or "
                    + "upd.tgl_pesan between ? and ? and p.nm_pasien like ? or "
                    + "upd.tgl_pesan between ? and ? and pj.png_jawab like ? or "
                    + "upd.tgl_pesan between ? and ? and upd.nm_unit like ? or "
                    + "upd.tgl_pesan between ? and ? and uk.nama like ? or "
                    + "upd.tgl_pesan between ? and ? and us.golongan_darah like ? or "
                    + "upd.tgl_pesan between ? and ? and us.asal_darah like ? order by upd.waktu_simpan");
            try {
                ps2.setString(1, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(2, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(3, "%" + TCari.getText().trim() + "%");
                ps2.setString(4, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(5, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(6, "%" + TCari.getText().trim() + "%");
                ps2.setString(7, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(8, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(9, "%" + TCari.getText().trim() + "%");
                ps2.setString(10, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(11, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(12, "%" + TCari.getText().trim() + "%");
                ps2.setString(13, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(14, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(15, "%" + TCari.getText().trim() + "%");
                ps2.setString(16, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(17, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(18, "%" + TCari.getText().trim() + "%");
                ps2.setString(19, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(20, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(21, "%" + TCari.getText().trim() + "%");
                ps2.setString(22, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(23, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(24, "%" + TCari.getText().trim() + "%");
                ps2.setString(25, Valid.SetTgl(Ttgl1.getSelectedItem() + ""));
                ps2.setString(26, Valid.SetTgl(Ttgl2.getSelectedItem() + ""));
                ps2.setString(27, "%" + TCari.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rs2.getString("no_rawat"),
                        rs2.getString("no_kantong"),
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),
                        rs2.getString("png_jawab"),
                        rs2.getString("jnsrawat"),
                        rs2.getString("nm_unit"),
                        rs2.getString("darah"),
                        rs2.getString("golongan_darah"),
                        rs2.getString("resus"),
                        rs2.getString("tglAftap"),
                        rs2.getString("tglkada"),
                        rs2.getString("tglpesan"),
                        rs2.getString("asal_darah"),
                        rs2.getString("status"),
                        rs2.getString("totalFormat"),
                        rs2.getString("tgl_pesan"),
                        rs2.getString("wktsimpan"),
                        rs2.getString("nip_petugas"),
                        rs2.getString("nmpetugas")
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
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
        LCount.setText("" + tabMode2.getRowCount());
    }

    public void emptTeks() {
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        Tunit.setText("");
        TtglPesan.setDate(new Date());
        TtglPesan.requestFocus();
        TCariDarah.setText("");
    }

    private void getData() {
        if (tbPemesan.getSelectedRow() != -1) {
            TNoRw.setText(tbPemesan.getValueAt(tbPemesan.getSelectedRow(), 1).toString());
            TNoRM.setText(tbPemesan.getValueAt(tbPemesan.getSelectedRow(), 3).toString());
            TPasien.setText(tbPemesan.getValueAt(tbPemesan.getSelectedRow(), 4).toString());
            Tunit.setText(tbPemesan.getValueAt(tbPemesan.getSelectedRow(), 7).toString());
            Valid.SetTgl(TtglPesan, tbPemesan.getValueAt(tbPemesan.getSelectedRow(), 17).toString());
            TCariDarah.setText("");
            tampilStok();
        }
    }
    
    public void isCek() {
        if (akses.getkamar_inap() == true || akses.getkasir_ralan() == true) {
            BtnSimpan.setEnabled(true);
            BtnHapus.setEnabled(true);
            ppCetak.setEnabled(true);
        } else {
            BtnSimpan.setEnabled(false);
            BtnHapus.setEnabled(false);
            ppCetak.setEnabled(false);
        }
    }
    
    public void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 330));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }    
    
    public void setData(String norw, String norm, String nmpasien, String unit, String stts) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        Tunit.setText(unit);
        sttsrawat = stts;
    }
    
    private void tampilStok() {
        try {
            jml = 0;
            for (s = 0; s < tbDarah.getRowCount(); s++) {
                if (tbDarah.getValueAt(s, 0).toString().equals("true")) {
                    jml++;
                }
            }
            
            pilih = null;
            pilih = new boolean[jml];
            nokantong = null;
            nokantong = new String[jml];
            komponen = null;
            komponen = new String[jml];
            gd = null;
            gd = new String[jml];
            resus = null;
            resus = new String[jml];
            aftap = null;
            aftap = new String[jml];
            kadaluarsa = null;
            kadaluarsa = new String[jml];
            asaldarah = null;
            asaldarah = new String[jml];
            status = null;
            status = new String[jml];
            biaya = null;
            biaya = new String[jml];
            
            index = 0;
            for (s = 0; s < tbDarah.getRowCount(); s++) {
                if (tbDarah.getValueAt(s, 0).toString().equals("true")) {
                    pilih[index] = true;
                    nokantong[index] = tbDarah.getValueAt(s, 1).toString();
                    komponen[index] = tbDarah.getValueAt(s, 2).toString();
                    gd[index] = tbDarah.getValueAt(s, 3).toString();
                    resus[index] = tbDarah.getValueAt(s, 4).toString();
                    aftap[index] = tbDarah.getValueAt(s, 5).toString();
                    kadaluarsa[index] = tbDarah.getValueAt(s, 6).toString();
                    asaldarah[index] = tbDarah.getValueAt(s, 7).toString();
                    status[index] = tbDarah.getValueAt(s, 8).toString();
                    biaya[index] = tbDarah.getValueAt(s, 9).toString();
                    index++;
                }
            }
            
            Valid.tabelKosong(tabMode1);
            for (s = 0; s < jml; s++) {
                tabMode1.addRow(new Object[]{pilih[s], nokantong[s], komponen[s], gd[s], resus[s], aftap[s], kadaluarsa[s], asaldarah[s], status[s], biaya[s]});
            }
            
            ps1 = koneksi.prepareStatement("select us.no_kantong, uk.nama darah, us.golongan_darah, us.resus, date_format(us.tanggal_aftap,'%d-%m-%Y') tglaftap, "
                    + "date_format(us.tanggal_kadaluarsa,'%d-%m-%Y') tglkadal, us.asal_darah, us.status, format(uk.total,0) totalFormat from utd_komponen_darah uk "
                    + "inner join utd_stok_darah us on us.kode_komponen=uk.kode where "
                    + "us.status='Ada' and us.golongan_darah like ? or "
                    + "us.status='Ada' and us.resus like ? or "
                    + "us.status='Ada' and us.no_kantong like ? or "
                    + "us.status='Ada' and uk.nama like ? or "
                    + "us.status='Ada' and us.asal_darah like ? order by us.tanggal_kadaluarsa");
            try {
                ps1.setString(1, "%" + TCariDarah.getText().trim() + "%");
                ps1.setString(2, "%" + TCariDarah.getText().trim() + "%");
                ps1.setString(3, "%" + TCariDarah.getText().trim() + "%");
                ps1.setString(4, "%" + TCariDarah.getText().trim() + "%");
                ps1.setString(5, "%" + TCariDarah.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        false,
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6),
                        rs1.getString(7),
                        rs1.getString(8),
                        rs1.getString(9).replaceAll(",", ".")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilStok : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
