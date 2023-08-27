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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DlgMonevAsuhanGizi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private String kdkamarnya = "", jam = "", tgl = "", edukasi = "", menu = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgMonevAsuhanGizi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Monev", "Jam","Perkembangan Hasil Lab.", "Perkembangan Fisik/Klinik", "Perkembangan Diet", "Evaluasi", "Catatan Makanan dari Luar RS", 
            "Edukasi", "Tgl. Edukasi", "Menu Pilihan", "Makanan Pokok", "Lauk", "Sayur", "Buah/Snack", "kdkmr"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex==0) {
                    a=false;
                }
                return a;
             }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,                
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbMonev.setModel(tabMode);
        tbMonev.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMonev.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbMonev.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(280);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setPreferredWidth(80);
            } else if (i == 13) {
                column.setPreferredWidth(150);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            } else if (i == 15) {
                column.setPreferredWidth(150);
            } else if (i == 16) {
                column.setPreferredWidth(150);
            } else if (i == 17) {
//                column.setPreferredWidth(100);                      
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbMonev.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        Scroll = new widget.ScrollPane();
        tbMonev = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel28 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel29 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel17 = new widget.Label();
        ChkEdukasi = new widget.CekBox();
        ChkMenu = new widget.CekBox();
        jLabel18 = new widget.Label();
        jLabel25 = new widget.Label();
        norawat = new widget.TextBox();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel26 = new widget.Label();
        nmruangan = new widget.TextBox();
        jLabel27 = new widget.Label();
        tglMonev = new widget.Tanggal();
        makanan_pokok = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        catatan_makanan = new widget.TextArea();
        tglEdukasi = new widget.Tanggal();
        jLabel24 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        lauk = new widget.TextBox();
        sayur = new widget.TextBox();
        buahSnack = new widget.TextBox();
        hasilLab = new widget.TextBox();
        fisikKlinik = new widget.TextBox();
        diet = new widget.TextBox();
        evaluasi = new widget.TextBox();
        jLabel32 = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Input Monitoring dan Evaluasi Asuhan Gizi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbMonev.setAutoCreateRowSorter(true);
        tbMonev.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbMonev.setToolTipText("Silahkan klik untuk memilih data yang akan diedit atau dihapus");
        tbMonev.setName("tbMonev"); // NOI18N
        tbMonev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMonevMouseClicked(evt);
            }
        });
        tbMonev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMonevKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbMonev);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tgl. Monev : ");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel28);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-04-2021" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        tgl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tgl1MouseClicked(evt);
            }
        });
        panelGlass10.add(tgl1);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("s.d.");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass10.add(jLabel29);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-04-2021" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        tgl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tgl2MouseClicked(evt);
            }
        });
        panelGlass10.add(tgl2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(380, 350));
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

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Hasil Lab. : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 122, 100, 23);

        ChkEdukasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkEdukasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEdukasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkEdukasi.setText("Edukasi Keamanan Pangan : Tgl. Edukasi : ");
        ChkEdukasi.setBorderPainted(true);
        ChkEdukasi.setBorderPaintedFlat(true);
        ChkEdukasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEdukasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEdukasi.setName("ChkEdukasi"); // NOI18N
        ChkEdukasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkEdukasiActionPerformed(evt);
            }
        });
        FormInput.add(ChkEdukasi);
        ChkEdukasi.setBounds(640, 10, 224, 23);

        ChkMenu.setBackground(new java.awt.Color(255, 255, 250));
        ChkMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMenu.setForeground(new java.awt.Color(0, 0, 0));
        ChkMenu.setText("Menu Pilihan : ");
        ChkMenu.setBorderPainted(true);
        ChkMenu.setBorderPaintedFlat(true);
        ChkMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMenu.setName("ChkMenu"); // NOI18N
        ChkMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkMenuActionPerformed(evt);
            }
        });
        FormInput.add(ChkMenu);
        ChkMenu.setBounds(640, 38, 90, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Makanan Pokok : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(640, 66, 125, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Pasien : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 10, 100, 23);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setHighlighter(null);
        norawat.setName("norawat"); // NOI18N
        norawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatKeyPressed(evt);
            }
        });
        FormInput.add(norawat);
        norawat.setBounds(102, 10, 120, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setHighlighter(null);
        norm.setName("norm"); // NOI18N
        norm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                normKeyPressed(evt);
            }
        });
        FormInput.add(norm);
        norm.setBounds(225, 10, 70, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setHighlighter(null);
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpasienKeyPressed(evt);
            }
        });
        FormInput.add(nmpasien);
        nmpasien.setBounds(298, 10, 310, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Ruang Rawat : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 38, 100, 23);

        nmruangan.setEditable(false);
        nmruangan.setForeground(new java.awt.Color(0, 0, 0));
        nmruangan.setHighlighter(null);
        nmruangan.setName("nmruangan"); // NOI18N
        nmruangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmruanganKeyPressed(evt);
            }
        });
        FormInput.add(nmruangan);
        nmruangan.setBounds(102, 38, 505, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tgl. Monev : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 66, 100, 23);

        tglMonev.setEditable(false);
        tglMonev.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-04-2021" }));
        tglMonev.setDisplayFormat("dd-MM-yyyy");
        tglMonev.setName("tglMonev"); // NOI18N
        tglMonev.setOpaque(false);
        tglMonev.setPreferredSize(new java.awt.Dimension(95, 23));
        tglMonev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tglMonevMouseClicked(evt);
            }
        });
        FormInput.add(tglMonev);
        tglMonev.setBounds(102, 66, 95, 23);

        makanan_pokok.setForeground(new java.awt.Color(0, 0, 0));
        makanan_pokok.setHighlighter(null);
        makanan_pokok.setName("makanan_pokok"); // NOI18N
        makanan_pokok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                makanan_pokokKeyPressed(evt);
            }
        });
        FormInput.add(makanan_pokok);
        makanan_pokok.setBounds(767, 66, 370, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Minitoring Perkembangan : ");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 94, 180, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Fisik/Klinik : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(0, 150, 100, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Diet : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 178, 100, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Catatan makanan dari luar rumah sakit yang dibawa oleh Pasien/Keluarga : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 235, 390, 23);

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        catatan_makanan.setColumns(20);
        catatan_makanan.setRows(5);
        catatan_makanan.setName("catatan_makanan"); // NOI18N
        catatan_makanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                catatan_makananKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(catatan_makanan);

        FormInput.add(Scroll10);
        Scroll10.setBounds(102, 263, 505, 55);

        tglEdukasi.setEditable(false);
        tglEdukasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-04-2021" }));
        tglEdukasi.setDisplayFormat("dd-MM-yyyy");
        tglEdukasi.setName("tglEdukasi"); // NOI18N
        tglEdukasi.setOpaque(false);
        tglEdukasi.setPreferredSize(new java.awt.Dimension(95, 23));
        tglEdukasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tglEdukasiMouseClicked(evt);
            }
        });
        FormInput.add(tglEdukasi);
        tglEdukasi.setBounds(865, 10, 95, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Lauk : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(640, 94, 125, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Sayur : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(640, 122, 125, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Buah / Snack : ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(640, 150, 125, 23);

        lauk.setForeground(new java.awt.Color(0, 0, 0));
        lauk.setHighlighter(null);
        lauk.setName("lauk"); // NOI18N
        lauk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                laukKeyPressed(evt);
            }
        });
        FormInput.add(lauk);
        lauk.setBounds(767, 94, 370, 23);

        sayur.setForeground(new java.awt.Color(0, 0, 0));
        sayur.setHighlighter(null);
        sayur.setName("sayur"); // NOI18N
        sayur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sayurKeyPressed(evt);
            }
        });
        FormInput.add(sayur);
        sayur.setBounds(767, 122, 370, 23);

        buahSnack.setForeground(new java.awt.Color(0, 0, 0));
        buahSnack.setHighlighter(null);
        buahSnack.setName("buahSnack"); // NOI18N
        buahSnack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buahSnackKeyPressed(evt);
            }
        });
        FormInput.add(buahSnack);
        buahSnack.setBounds(767, 150, 370, 23);

        hasilLab.setForeground(new java.awt.Color(0, 0, 0));
        hasilLab.setHighlighter(null);
        hasilLab.setName("hasilLab"); // NOI18N
        hasilLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hasilLabKeyPressed(evt);
            }
        });
        FormInput.add(hasilLab);
        hasilLab.setBounds(102, 122, 505, 23);

        fisikKlinik.setForeground(new java.awt.Color(0, 0, 0));
        fisikKlinik.setHighlighter(null);
        fisikKlinik.setName("fisikKlinik"); // NOI18N
        fisikKlinik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fisikKlinikKeyPressed(evt);
            }
        });
        FormInput.add(fisikKlinik);
        fisikKlinik.setBounds(102, 150, 505, 23);

        diet.setForeground(new java.awt.Color(0, 0, 0));
        diet.setHighlighter(null);
        diet.setName("diet"); // NOI18N
        diet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dietKeyPressed(evt);
            }
        });
        FormInput.add(diet);
        diet.setBounds(102, 178, 505, 23);

        evaluasi.setForeground(new java.awt.Color(0, 0, 0));
        evaluasi.setHighlighter(null);
        evaluasi.setName("evaluasi"); // NOI18N
        evaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                evaluasiKeyPressed(evt);
            }
        });
        FormInput.add(evaluasi);
        evaluasi.setBounds(102, 206, 505, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Evaluasi : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 206, 100, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (norawat.getText().trim().equals("")) {
            Valid.textKosong(norawat, "Data Pasien");
        } else if (hasilLab.getText().trim().equals("")) {
            Valid.textKosong(hasilLab, "Perkembangan Hasil Pemeriksaan Lab.");
        } else if (fisikKlinik.getText().trim().equals("")) {
            Valid.textKosong(fisikKlinik, "Perkembangan Fisik / Klinik");
        } else if (diet.getText().trim().equals("")) {
            Valid.textKosong(diet, "Perkembangan Diet");
        } else if (evaluasi.getText().trim().equals("")) {
            Valid.textKosong(evaluasi, "Evaluasi");
        } else {
            if (ChkMenu.isSelected() == true) {
                if (makanan_pokok.getText().trim().equals("")
                        || lauk.getText().trim().equals("")
                        || sayur.getText().trim().equals("")
                        || buahSnack.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Menu pilihan harus diisi sesuai permintaannya,...!!");
                } else {
                    NilaiTrueFalse();
                    Sequel.menyimpan("monev_asuhan_gizi", "'" + norawat.getText() + "','" + Valid.SetTgl(tglMonev.getSelectedItem() + "") + "',"
                            + "'" + Sequel.cariIsi("SELECT TIME(NOW())") + "','" + hasilLab.getText() + "','" + fisikKlinik.getText() + "',"
                            + "'" + diet.getText() + "','" + evaluasi.getText() + "','" + catatan_makanan.getText() + "','" + edukasi + "',"
                            + "'" + Valid.SetTgl(tglEdukasi.getSelectedItem() + "") + "','" + menu + "','" + makanan_pokok.getText() + "',"
                            + "'" + lauk.getText() + "','" + sayur.getText() + "','" + buahSnack.getText() + "','" + kdkamarnya + "'", "Monitoring & Evaluasi Asuhan Gizi");
                    emptTeks();
                    tampil();
                }
            } else if (ChkMenu.isSelected() == false) {
                NilaiTrueFalse();
                Sequel.menyimpan("monev_asuhan_gizi", "'" + norawat.getText() + "','" + Valid.SetTgl(tglMonev.getSelectedItem() + "") + "',"
                        + "'" + Sequel.cariIsi("SELECT TIME(NOW())") + "','" + hasilLab.getText() + "','" + fisikKlinik.getText() + "',"
                        + "'" + diet.getText() + "','" + evaluasi.getText() + "','" + catatan_makanan.getText() + "','" + edukasi + "',"
                        + "'" + Valid.SetTgl(tglEdukasi.getSelectedItem() + "") + "','" + menu + "','" + makanan_pokok.getText() + "',"
                        + "'" + lauk.getText() + "','" + sayur.getText() + "','" + buahSnack.getText() + "','" + kdkamarnya + "'", "Monitoring & Evaluasi Asuhan Gizi");
                emptTeks();
                tampil();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, buahSnack, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnGanti);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (norawat.getText().trim().equals("")) {
            Valid.textKosong(norawat, "Data Pasien");
        } else if (hasilLab.getText().trim().equals("")) {
            Valid.textKosong(hasilLab, "Perkembangan Hasil Pemeriksaan Lab.");
        } else if (fisikKlinik.getText().trim().equals("")) {
            Valid.textKosong(fisikKlinik, "Perkembangan Fisik / Klinik");
        } else if (diet.getText().trim().equals("")) {
            Valid.textKosong(diet, "Perkembangan Diet");
        } else if (evaluasi.getText().trim().equals("")) {
            Valid.textKosong(evaluasi, "Evaluasi");
        } else {
            if (ChkMenu.isSelected() == true) {
                if (makanan_pokok.getText().trim().equals("")
                        || lauk.getText().trim().equals("")
                        || sayur.getText().trim().equals("")
                        || buahSnack.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Menu pilihan harus diisi sesuai permintaannya,...!!");
                } else {
                    NilaiTrueFalse();
                    Sequel.mengedit("monev_asuhan_gizi", "no_rawat='" + norawat.getText() + "' and tgl_monev='" + tgl + "' and jam_monev='" + jam + "'",
                            "tgl_monev='" + Valid.SetTgl(tglMonev.getSelectedItem() + "") + "',"
                            + "perkembangan_hasil_lab='" + hasilLab.getText() + "',"
                            + "perkembangan_fisik_klinik='" + fisikKlinik.getText() + "',"
                            + "perkembangan_diet='" + diet.getText() + "',"
                            + "evaluasi='" + evaluasi.getText() + "',"
                            + "catatan_makanan_dari_luar='" + catatan_makanan.getText() + "',"
                            + "edukasi_keamanan_pangan='" + edukasi + "',"
                            + "tgl_edukasi='" + Valid.SetTgl(tglEdukasi.getSelectedItem() + "") + "',"
                            + "menu_pilihan='" + menu + "',"
                            + "makanan_pokok='" + makanan_pokok.getText() + "',"
                            + "lauk='" + lauk.getText() + "',"
                            + "sayur='" + sayur.getText() + "',"
                            + "buah_snack='" + buahSnack.getText() + "',"
                            + "kd_kamar='" + kdkamarnya + "'");
                    emptTeks();
                    tampil();
                }
            } else if (ChkMenu.isSelected() == false) {
                NilaiTrueFalse();
                Sequel.mengedit("monev_asuhan_gizi", "no_rawat='" + norawat.getText() + "' and tgl_monev='" + tgl + "' and jam_monev='" + jam + "'",
                        "tgl_monev='" + Valid.SetTgl(tglMonev.getSelectedItem() + "") + "',"
                        + "perkembangan_hasil_lab='" + hasilLab.getText() + "',"
                        + "perkembangan_fisik_klinik='" + fisikKlinik.getText() + "',"
                        + "perkembangan_diet='" + diet.getText() + "',"
                        + "evaluasi='" + evaluasi.getText() + "',"
                        + "catatan_makanan_dari_luar='" + catatan_makanan.getText() + "',"
                        + "edukasi_keamanan_pangan='" + edukasi + "',"
                        + "tgl_edukasi='" + Valid.SetTgl(tglEdukasi.getSelectedItem() + "") + "',"
                        + "menu_pilihan='" + menu + "',"
                        + "makanan_pokok='" + makanan_pokok.getText() + "',"
                        + "lauk='" + lauk.getText() + "',"
                        + "sayur='" + sayur.getText() + "',"
                        + "buah_snack='" + buahSnack.getText() + "',"
                        + "kd_kamar='" + kdkamarnya + "'");
                emptTeks();
                tampil();
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, buahSnack);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbMonevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonevMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMonevMouseClicked

    private void tbMonevKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMonevKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbMonevKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
    tglMonev.requestFocus();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void ChkEdukasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkEdukasiActionPerformed

    }//GEN-LAST:event_ChkEdukasiActionPerformed

    private void ChkMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMenuActionPerformed
        if (ChkMenu.isSelected() == false) {
            makanan_pokok.setText("");
            lauk.setText("");
            sayur.setText("");
            buahSnack.setText("");
            makanan_pokok.setEnabled(false);
            lauk.setEnabled(false);
            sayur.setEnabled(false);
            buahSnack.setEnabled(false);
        } else {
            makanan_pokok.setText("-");
            lauk.setText("-");
            sayur.setText("-");
            buahSnack.setText("-");
            makanan_pokok.setEnabled(true);
            lauk.setEnabled(true);
            sayur.setEnabled(true);
            buahSnack.setEnabled(true);
        }
    }//GEN-LAST:event_ChkMenuActionPerformed

    private void norawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatKeyPressed

    private void normKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_normKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_normKeyPressed

    private void nmpasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpasienKeyPressed

    private void nmruanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmruanganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmruanganKeyPressed

    private void tglMonevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tglMonevMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tglMonevMouseClicked

    private void tgl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl1MouseClicked

    private void tgl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl2MouseClicked

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            tbMonev.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else {
            Sequel.queryu("delete from monev_asuhan_gizi where no_rawat='" + norawat.getText() + "' and tgl_monev='" + tgl + "' and jam_monev='" + jam + "'");
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void makanan_pokokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_makanan_pokokKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            lauk.requestFocus();
        }
    }//GEN-LAST:event_makanan_pokokKeyPressed

    private void catatan_makananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_catatan_makananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_catatan_makananKeyPressed

    private void tglEdukasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tglEdukasiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tglEdukasiMouseClicked

    private void laukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_laukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sayur.requestFocus();
        }
    }//GEN-LAST:event_laukKeyPressed

    private void sayurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sayurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buahSnack.requestFocus();
        }
    }//GEN-LAST:event_sayurKeyPressed

    private void buahSnackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buahSnackKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_buahSnackKeyPressed

    private void hasilLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilLabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fisikKlinik.requestFocus();
        }
    }//GEN-LAST:event_hasilLabKeyPressed

    private void fisikKlinikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fisikKlinikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            diet.requestFocus();
        }
    }//GEN-LAST:event_fisikKlinikKeyPressed

    private void dietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dietKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evaluasi.requestFocus();
        }
    }//GEN-LAST:event_dietKeyPressed

    private void evaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evaluasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            catatan_makanan.requestFocus();
        }
    }//GEN-LAST:event_evaluasiKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgMonevAsuhanGizi dialog = new DlgMonevAsuhanGizi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnSimpan;
    private widget.CekBox ChkEdukasi;
    public widget.CekBox ChkInput;
    private widget.CekBox ChkMenu;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    public widget.TextBox TCari;
    private widget.TextBox buahSnack;
    private widget.TextArea catatan_makanan;
    private widget.TextBox diet;
    private widget.TextBox evaluasi;
    private widget.TextBox fisikKlinik;
    private widget.TextBox hasilLab;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
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
    private widget.Label jLabel32;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox lauk;
    private widget.TextBox makanan_pokok;
    private widget.TextBox nmpasien;
    private widget.TextBox nmruangan;
    private widget.TextBox norawat;
    private widget.TextBox norm;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.TextBox sayur;
    private widget.Table tbMonev;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tglEdukasi;
    private widget.Tanggal tglMonev;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ma.no_rawat, p.no_rkm_medis, p.nm_pasien, ma.tgl_monev, ma.jam_monev, ma.perkembangan_hasil_lab, ma.perkembangan_fisik_klinik, "
                    + "ma.perkembangan_diet, ma.evaluasi, ma.catatan_makanan_dari_luar, ma.edukasi_keamanan_pangan, ma.tgl_edukasi, "
                    + "ma.menu_pilihan, ma.makanan_pokok, ma.lauk, ma.sayur, ma.buah_snack, ma.kd_kamar FROM monev_asuhan_gizi ma "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=ma.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.no_rawat like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and p.no_rkm_medis like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and p.nm_pasien like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.jam_monev like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.perkembangan_hasil_lab like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.perkembangan_fisik_klinik like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.perkembangan_diet like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.evaluasi like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.catatan_makanan_dari_luar like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.tgl_edukasi like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.makanan_pokok like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.lauk like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.sayur like ? or "
                    + "ma.tgl_monev BETWEEN ? and ? and ma.buah_snack like ? order by ma.tgl_monev desc, ma.jam_monev desc");
            try {
                ps.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                ps.setString(28, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%");
                ps.setString(34, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(35, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(36, "%" + TCari.getText().trim() + "%");                
                ps.setString(37, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(38, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(39, "%" + TCari.getText().trim() + "%");
                ps.setString(40, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(41, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(42, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_monev"),
                        rs.getString("jam_monev"),
                        rs.getString("perkembangan_hasil_lab"),
                        rs.getString("perkembangan_fisik_klinik"),                        
                        rs.getString("perkembangan_diet"),
                        rs.getString("evaluasi"),
                        rs.getString("catatan_makanan_dari_luar"),
                        rs.getString("edukasi_keamanan_pangan"),
                        rs.getString("tgl_edukasi"),
                        rs.getString("menu_pilihan"),
                        rs.getString("makanan_pokok"),
                        rs.getString("lauk"),
                        rs.getString("sayur"),
                        rs.getString("buah_snack"),
                        rs.getString("kd_kamar")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.DlgMonevAsuhanGizi.tampil() : " + e);
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
        tglMonev.setDate(new Date());   
        jam = "";
        hasilLab.setText("");
        fisikKlinik.setText("");
        diet.setText("");
        evaluasi.setText("");
        catatan_makanan.setText("");        
        ChkEdukasi.setSelected(false);
        tglEdukasi.setDate(new Date());
        ChkMenu.setSelected(false);
        makanan_pokok.setText("");
        lauk.setText("");
        sayur.setText("");
        buahSnack.setText("");
        makanan_pokok.setEnabled(false);
        lauk.setEnabled(false);
        sayur.setEnabled(false);
        buahSnack.setEnabled(false);
    }

    private void getData() {
        tgl = "";
        jam = "";
        kdkamarnya = "";
        edukasi = "";
        menu = "";
        
        if(tbMonev.getSelectedRow()!= -1){  
            norawat.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 0).toString());
            norm.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 1).toString());
            nmpasien.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 2).toString());
            kdkamarnya = tbMonev.getValueAt(tbMonev.getSelectedRow(), 17).toString();
            nmruangan.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdkamarnya + "'"));
            tgl = tbMonev.getValueAt(tbMonev.getSelectedRow(), 3).toString();
            jam = tbMonev.getValueAt(tbMonev.getSelectedRow(), 4).toString();
            Valid.SetTgl(tglMonev, tbMonev.getValueAt(tbMonev.getSelectedRow(), 3).toString());
            hasilLab.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 5).toString());
            fisikKlinik.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 6).toString());
            diet.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 7).toString());
            evaluasi.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 8).toString());
            catatan_makanan.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 9).toString());
            edukasi = tbMonev.getValueAt(tbMonev.getSelectedRow(), 10).toString();
            Valid.SetTgl(tglEdukasi, tbMonev.getValueAt(tbMonev.getSelectedRow(), 11).toString());
            menu = tbMonev.getValueAt(tbMonev.getSelectedRow(), 12).toString();
            makanan_pokok.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 13).toString());
            lauk.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 14).toString());
            sayur.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 15).toString());
            buahSnack.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 16).toString());
            NilaiBalik();
       
            if (ChkMenu.isSelected() == false) {
                makanan_pokok.setEnabled(false);
                lauk.setEnabled(false);
                sayur.setEnabled(false);
                buahSnack.setEnabled(false);
            } else {
                makanan_pokok.setEnabled(true);
                lauk.setEnabled(true);
                sayur.setEnabled(true);
                buahSnack.setEnabled(true);
            }
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 350));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    private void NilaiTrueFalse() {
        if (ChkEdukasi.isSelected() == true) {
            edukasi = "Ya";
        } else {
            edukasi = "Tidak";
        }
        
        if (ChkMenu.isSelected() == true) {
            menu = "Ya";
        } else {
            menu = "Tidak";
        }
    }
    
    private void NilaiBalik() {
        if (edukasi.equals("Ya")) {
            ChkEdukasi.setSelected(true);
        } else {
            ChkEdukasi.setSelected(false);
        }

        if (menu.equals("Ya")) {
            ChkMenu.setSelected(true);
        } else {
            ChkMenu.setSelected(false);
        }
    }
    
    public void setPasien(String norw, String ruangInap, String kdKamar) {
        norawat.setText(norw);
        norm.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norawat.getText() + "'"));
        nmpasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + norm.getText() + "'"));
        nmruangan.setText(ruangInap);
        kdkamarnya = kdKamar;
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getmonev_asuhan_gizi());
        BtnGanti.setEnabled(akses.getmonev_asuhan_gizi());
        BtnHapus.setEnabled(akses.getmonev_asuhan_gizi());
    }
}
