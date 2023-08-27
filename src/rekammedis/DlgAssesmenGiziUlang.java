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

public class DlgAssesmenGiziUlang extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private double angkaTB = 0, nilaiTB = 0, nilaiIMT = 0;
    private String mual = "", diare = "", odeme = "", anorex = "", nyeri = "", sulit = "",
            konsti = "", ganggu = "", alergi = "", kdkamarnya = "", jam = "", tgl = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgAssesmenGiziUlang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Assesment", "Jam", "BB", "TB", "IMT", "LLA", "Status Gizi",
            "Biokimia Domain", "Mual Muntah", "Diare", "Odeme", "Anorexia", "Nyeri Ulu Hati", "Kesulitan Menelan", "Konstipasi", "Gangguan Gigi Geligi", "Riwayat Makan 1",
            "Riwayat Makan 2", "Alergi Makanan", "Keterangan Alergi","Rwt. Penykt. Personal", "Diagnosa Medis","kdkmr"
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbAsesmenGZUlang.setModel(tabMode);
        tbAsesmenGZUlang.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmenGZUlang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 26; i++) {
            TableColumn column = tbAsesmenGZUlang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(280);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(50);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setPreferredWidth(60);
            } else if (i == 13) {
                column.setPreferredWidth(60);
            } else if (i == 14) {
                column.setPreferredWidth(80);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(80);
            } else if (i == 18) {
                column.setPreferredWidth(120);
            } else if (i == 19) {
                column.setPreferredWidth(120);
            } else if (i == 20) {
                column.setPreferredWidth(120);
            } else if (i == 21) {
                column.setPreferredWidth(90);
            } else if (i == 22) {
                column.setPreferredWidth(250);
            } else if (i == 23) {
                column.setPreferredWidth(300);
            } else if (i == 24) {
                column.setPreferredWidth(250);
            } else if (i == 25) {
//                column.setPreferredWidth(120);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAsesmenGZUlang.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        bb.setDocument(new batasInput((byte) 3).getOnlyAngka(bb));
        tb.setDocument(new batasInput((byte) 3).getOnlyAngka(tb));
        
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
        tbAsesmenGZUlang = new widget.Table();
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
        jLabel4 = new widget.Label();
        bb = new widget.TextBox();
        jLabel9 = new widget.Label();
        jLabel12 = new widget.Label();
        cmbRiwMakan1 = new widget.ComboBox();
        jLabel10 = new widget.Label();
        riw_penyakitPersonal = new widget.TextBox();
        jLabel14 = new widget.Label();
        tb = new widget.TextBox();
        jLabel15 = new widget.Label();
        imt = new widget.TextBox();
        jLabel16 = new widget.Label();
        lla = new widget.TextBox();
        cmbstatusGZ = new widget.ComboBox();
        jLabel17 = new widget.Label();
        jLabel5 = new widget.Label();
        ChkmualMuntah = new widget.CekBox();
        ChkDiare = new widget.CekBox();
        ChkOdeme = new widget.CekBox();
        ChkAnorexia = new widget.CekBox();
        ChkKesulitan = new widget.CekBox();
        ChkNyeriUlu = new widget.CekBox();
        ChkKonstipasi = new widget.CekBox();
        ChkGangguan = new widget.CekBox();
        jLabel18 = new widget.Label();
        jLabel19 = new widget.Label();
        cmbRiwMakan2 = new widget.ComboBox();
        ChkAlergiMakan = new widget.CekBox();
        jLabel25 = new widget.Label();
        norawat = new widget.TextBox();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel26 = new widget.Label();
        nmruangan = new widget.TextBox();
        jLabel27 = new widget.Label();
        tglAsses = new widget.Tanggal();
        Scroll7 = new widget.ScrollPane();
        bio_domain = new widget.TextArea();
        ket_alergi = new widget.TextBox();
        jLabel13 = new widget.Label();
        diag_medis = new widget.TextBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Input Assesment (Asuhan Gizi) Ulang ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAsesmenGZUlang.setAutoCreateRowSorter(true);
        tbAsesmenGZUlang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbAsesmenGZUlang.setToolTipText("Silahkan klik untuk memilih data yang akan diedit atau dihapus");
        tbAsesmenGZUlang.setName("tbAsesmenGZUlang"); // NOI18N
        tbAsesmenGZUlang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsesmenGZUlangMouseClicked(evt);
            }
        });
        tbAsesmenGZUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAsesmenGZUlangKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAsesmenGZUlang);

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
        jLabel28.setText("Tgl. Assesment : ");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(jLabel28);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2021" }));
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
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2021" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(380, 410));
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

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Antropometri Domain : ");
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 66, 150, 23);

        bb.setForeground(new java.awt.Color(0, 0, 0));
        bb.setHighlighter(null);
        bb.setName("bb"); // NOI18N
        bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbKeyPressed(evt);
            }
        });
        FormInput.add(bb);
        bb.setBounds(65, 94, 50, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("BB : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 94, 65, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Riwayat Penyakit Personal : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 320, 160, 23);

        cmbRiwMakan1.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwMakan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "MAKAN > 3X SEHARI", "MAKAN < 3X SEHARI" }));
        cmbRiwMakan1.setName("cmbRiwMakan1"); // NOI18N
        cmbRiwMakan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRiwMakan1MouseClicked(evt);
            }
        });
        cmbRiwMakan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwMakan1ActionPerformed(evt);
            }
        });
        cmbRiwMakan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRiwMakan1KeyPressed(evt);
            }
        });
        FormInput.add(cmbRiwMakan1);
        cmbRiwMakan1.setBounds(127, 264, 133, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Status Gizi : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(435, 94, 70, 23);

        riw_penyakitPersonal.setForeground(new java.awt.Color(0, 0, 0));
        riw_penyakitPersonal.setHighlighter(null);
        riw_penyakitPersonal.setName("riw_penyakitPersonal"); // NOI18N
        riw_penyakitPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                riw_penyakitPersonalKeyPressed(evt);
            }
        });
        FormInput.add(riw_penyakitPersonal);
        riw_penyakitPersonal.setBounds(162, 320, 445, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Kg.    TB : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(120, 94, 50, 23);

        tb.setForeground(new java.awt.Color(0, 0, 0));
        tb.setHighlighter(null);
        tb.setName("tb"); // NOI18N
        tb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKeyPressed(evt);
            }
        });
        FormInput.add(tb);
        tb.setBounds(171, 94, 50, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Cm.    IMT : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(226, 94, 60, 23);

        imt.setEditable(false);
        imt.setForeground(new java.awt.Color(0, 0, 0));
        imt.setHighlighter(null);
        imt.setName("imt"); // NOI18N
        imt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                imtKeyPressed(evt);
            }
        });
        FormInput.add(imt);
        imt.setBounds(287, 94, 50, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("LLA : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(340, 94, 40, 23);

        lla.setForeground(new java.awt.Color(0, 0, 0));
        lla.setHighlighter(null);
        lla.setName("lla"); // NOI18N
        lla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                llaKeyPressed(evt);
            }
        });
        FormInput.add(lla);
        lla.setBounds(382, 94, 50, 23);

        cmbstatusGZ.setForeground(new java.awt.Color(0, 0, 0));
        cmbstatusGZ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "BURUK", "KURANG", "NORMAL", "LEBIH", "OBESITAS" }));
        cmbstatusGZ.setName("cmbstatusGZ"); // NOI18N
        cmbstatusGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbstatusGZKeyPressed(evt);
            }
        });
        FormInput.add(cmbstatusGZ);
        cmbstatusGZ.setBounds(505, 94, 100, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Biokimia Domain : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 122, 100, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Fisik Klinik Domain : ");
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 178, 150, 23);

        ChkmualMuntah.setBackground(new java.awt.Color(255, 255, 250));
        ChkmualMuntah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkmualMuntah.setForeground(new java.awt.Color(0, 0, 0));
        ChkmualMuntah.setText("Mual Muntah");
        ChkmualMuntah.setBorderPainted(true);
        ChkmualMuntah.setBorderPaintedFlat(true);
        ChkmualMuntah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkmualMuntah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkmualMuntah.setName("ChkmualMuntah"); // NOI18N
        ChkmualMuntah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkmualMuntahActionPerformed(evt);
            }
        });
        FormInput.add(ChkmualMuntah);
        ChkmualMuntah.setBounds(102, 206, 90, 23);

        ChkDiare.setBackground(new java.awt.Color(255, 255, 250));
        ChkDiare.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDiare.setForeground(new java.awt.Color(0, 0, 0));
        ChkDiare.setText("Diare");
        ChkDiare.setBorderPainted(true);
        ChkDiare.setBorderPaintedFlat(true);
        ChkDiare.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDiare.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDiare.setName("ChkDiare"); // NOI18N
        ChkDiare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDiareActionPerformed(evt);
            }
        });
        FormInput.add(ChkDiare);
        ChkDiare.setBounds(102, 234, 90, 23);

        ChkOdeme.setBackground(new java.awt.Color(255, 255, 250));
        ChkOdeme.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkOdeme.setForeground(new java.awt.Color(0, 0, 0));
        ChkOdeme.setText("Odeme");
        ChkOdeme.setBorderPainted(true);
        ChkOdeme.setBorderPaintedFlat(true);
        ChkOdeme.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkOdeme.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkOdeme.setName("ChkOdeme"); // NOI18N
        ChkOdeme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkOdemeActionPerformed(evt);
            }
        });
        FormInput.add(ChkOdeme);
        ChkOdeme.setBounds(200, 206, 90, 23);

        ChkAnorexia.setBackground(new java.awt.Color(255, 255, 250));
        ChkAnorexia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAnorexia.setForeground(new java.awt.Color(0, 0, 0));
        ChkAnorexia.setText("Anorexia");
        ChkAnorexia.setBorderPainted(true);
        ChkAnorexia.setBorderPaintedFlat(true);
        ChkAnorexia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAnorexia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAnorexia.setName("ChkAnorexia"); // NOI18N
        ChkAnorexia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAnorexiaActionPerformed(evt);
            }
        });
        FormInput.add(ChkAnorexia);
        ChkAnorexia.setBounds(200, 234, 90, 23);

        ChkKesulitan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKesulitan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKesulitan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKesulitan.setText("Kesulitan Menelan");
        ChkKesulitan.setBorderPainted(true);
        ChkKesulitan.setBorderPaintedFlat(true);
        ChkKesulitan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKesulitan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKesulitan.setName("ChkKesulitan"); // NOI18N
        ChkKesulitan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKesulitanActionPerformed(evt);
            }
        });
        FormInput.add(ChkKesulitan);
        ChkKesulitan.setBounds(295, 234, 110, 23);

        ChkNyeriUlu.setBackground(new java.awt.Color(255, 255, 250));
        ChkNyeriUlu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNyeriUlu.setForeground(new java.awt.Color(0, 0, 0));
        ChkNyeriUlu.setText("Nyeri Ulu Hati");
        ChkNyeriUlu.setBorderPainted(true);
        ChkNyeriUlu.setBorderPaintedFlat(true);
        ChkNyeriUlu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNyeriUlu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNyeriUlu.setName("ChkNyeriUlu"); // NOI18N
        ChkNyeriUlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkNyeriUluActionPerformed(evt);
            }
        });
        FormInput.add(ChkNyeriUlu);
        ChkNyeriUlu.setBounds(295, 206, 110, 23);

        ChkKonstipasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkKonstipasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKonstipasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkKonstipasi.setText("Konstipasi");
        ChkKonstipasi.setBorderPainted(true);
        ChkKonstipasi.setBorderPaintedFlat(true);
        ChkKonstipasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKonstipasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKonstipasi.setName("ChkKonstipasi"); // NOI18N
        ChkKonstipasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKonstipasiActionPerformed(evt);
            }
        });
        FormInput.add(ChkKonstipasi);
        ChkKonstipasi.setBounds(410, 206, 120, 23);

        ChkGangguan.setBackground(new java.awt.Color(255, 255, 250));
        ChkGangguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGangguan.setForeground(new java.awt.Color(0, 0, 0));
        ChkGangguan.setText("Gangguan Gigi Geligi");
        ChkGangguan.setBorderPainted(true);
        ChkGangguan.setBorderPaintedFlat(true);
        ChkGangguan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGangguan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGangguan.setName("ChkGangguan"); // NOI18N
        ChkGangguan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGangguanActionPerformed(evt);
            }
        });
        FormInput.add(ChkGangguan);
        ChkGangguan.setBounds(410, 234, 120, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Riwayat Makan 1 : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 264, 125, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Riwayat Makan 2 : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 292, 125, 23);

        cmbRiwMakan2.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwMakan2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "KEKURANGAN INTAKE", "KELEBIHAN INTAKE", "INTAKE NORMAL" }));
        cmbRiwMakan2.setName("cmbRiwMakan2"); // NOI18N
        cmbRiwMakan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRiwMakan2MouseClicked(evt);
            }
        });
        cmbRiwMakan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwMakan2ActionPerformed(evt);
            }
        });
        cmbRiwMakan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRiwMakan2KeyPressed(evt);
            }
        });
        FormInput.add(cmbRiwMakan2);
        cmbRiwMakan2.setBounds(127, 292, 145, 23);

        ChkAlergiMakan.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergiMakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergiMakan.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergiMakan.setText("Alergi Makanan : ");
        ChkAlergiMakan.setBorderPainted(true);
        ChkAlergiMakan.setBorderPaintedFlat(true);
        ChkAlergiMakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAlergiMakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAlergiMakan.setName("ChkAlergiMakan"); // NOI18N
        ChkAlergiMakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiMakanActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergiMakan);
        ChkAlergiMakan.setBounds(280, 264, 102, 23);

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
        jLabel27.setText("Tgl. Assesment : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(150, 66, 90, 23);

        tglAsses.setEditable(false);
        tglAsses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2021" }));
        tglAsses.setDisplayFormat("dd-MM-yyyy");
        tglAsses.setName("tglAsses"); // NOI18N
        tglAsses.setOpaque(false);
        tglAsses.setPreferredSize(new java.awt.Dimension(95, 23));
        tglAsses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tglAssesMouseClicked(evt);
            }
        });
        FormInput.add(tglAsses);
        tglAsses.setBounds(240, 66, 95, 23);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        bio_domain.setColumns(20);
        bio_domain.setRows(5);
        bio_domain.setName("bio_domain"); // NOI18N
        bio_domain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bio_domainKeyPressed(evt);
            }
        });
        Scroll7.setViewportView(bio_domain);

        FormInput.add(Scroll7);
        Scroll7.setBounds(102, 122, 505, 55);

        ket_alergi.setForeground(new java.awt.Color(0, 0, 0));
        ket_alergi.setHighlighter(null);
        ket_alergi.setName("ket_alergi"); // NOI18N
        ket_alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ket_alergiKeyPressed(evt);
            }
        });
        FormInput.add(ket_alergi);
        ket_alergi.setBounds(382, 264, 226, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosa Medis : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 348, 160, 23);

        diag_medis.setForeground(new java.awt.Color(0, 0, 0));
        diag_medis.setHighlighter(null);
        diag_medis.setName("diag_medis"); // NOI18N
        diag_medis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diag_medisKeyPressed(evt);
            }
        });
        FormInput.add(diag_medis);
        diag_medis.setBounds(162, 348, 445, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (bb.getText().trim().equals("") || tb.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Isilah BB & TB dengan benar tanpa menggunakan koma utk. mendapatkan nilai IMT,...!!");
            } else {
                hitungIMT();
                lla.requestFocus();
            }
        }
}//GEN-LAST:event_bbKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (norawat.getText().trim().equals("")) {
            Valid.textKosong(norawat, "Data Pasien");
        } else if (bb.getText().trim().equals("")) {
            Valid.textKosong(bb, "Berat Badan");
        } else if (tb.getText().trim().equals("")) {
            Valid.textKosong(tb, "Tinggi Badan");
        } else if (imt.getText().trim().equals("")) {
            Valid.textKosong(imt, "IMT");
        } else if (lla.getText().trim().equals("")) {
            Valid.textKosong(lla, "LLA");
        } else if (cmbstatusGZ.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Status Gizinya dengan benar,...!!");
        } else if (bio_domain.getText().trim().equals("")) {
            Valid.textKosong(bio_domain, "Biokimia Domain");
        } else if (riw_penyakitPersonal.getText().trim().equals("")) {
            Valid.textKosong(riw_penyakitPersonal, "Riwayat Penyakit Personal");
        } else if (ChkAlergiMakan.isSelected() == true && ket_alergi.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika alergi makanan dipilih, keterangan alergi harus diisi,...!!");
            ket_alergi.requestFocus();
        } else {
            NilaiTrueFalse();
            if (Sequel.menyimpantf2("assesmen_gizi_ulang", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Assesment Gizi (Asuhan Gizi Ulang)", 24,
                    new String[]{norawat.getText(), Valid.SetTgl(tglAsses.getSelectedItem() + ""), bb.getText(), tb.getText(), imt.getText(), lla.getText(),
                        cmbstatusGZ.getSelectedItem().toString(), bio_domain.getText(), mual, diare, odeme, anorex, nyeri, sulit, konsti, ganggu,
                        cmbRiwMakan1.getSelectedItem().toString(), cmbRiwMakan2.getSelectedItem().toString(), alergi, riw_penyakitPersonal.getText(),
                        kdkamarnya, Sequel.cariIsi("SELECT TIME(NOW())"), ket_alergi.getText(), diag_medis.getText()}) == true) {

                Sequel.mengedit("status_gizi_inap", "no_rawat='" + norawat.getText() + "'", "status_gizi='" + cmbstatusGZ.getSelectedItem() + "' ");
            }
            emptTeks();
            tampil();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, bb, BtnBatal);
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
        } else if (bb.getText().trim().equals("")) {
            Valid.textKosong(bb, "Berat Badan");
        } else if (tb.getText().trim().equals("")) {
            Valid.textKosong(tb, "Tinggi Badan");
        } else if (imt.getText().trim().equals("")) {
            Valid.textKosong(imt, "IMT");
        } else if (lla.getText().trim().equals("")) {
            Valid.textKosong(lla, "LLA");
        } else if (cmbstatusGZ.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Status Gizinya dengan benar,...!!");
        } else if (bio_domain.getText().trim().equals("")) {
            Valid.textKosong(bio_domain, "Biokimia Domain");
        } else if (ChkAlergiMakan.isSelected() == true && ket_alergi.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika alergi makanan dipilih, keterangan alergi harus diisi,...!!");
            ket_alergi.requestFocus();
        } else if (riw_penyakitPersonal.getText().trim().equals("")) {
            Valid.textKosong(riw_penyakitPersonal, "Riwayat Penyakit Personal");
        } else {
            NilaiTrueFalse();
            Sequel.mengedit("assesmen_gizi_ulang", "no_rawat='" + norawat.getText() + "' and tgl_assesmen='" + tgl + "' and jam='" + jam + "'",
                    "tgl_assesmen='" + Valid.SetTgl(tglAsses.getSelectedItem() + "") + "',"
                    + "BB='" + bb.getText() + "',"
                    + "TB='" + tb.getText() + "',"
                    + "IMT='" + imt.getText() + "',"
                    + "LLA='" + lla.getText() + "',"
                    + "status_gizi='" + cmbstatusGZ.getSelectedItem() + "',"
                    + "biokimia_domain='" + bio_domain.getText() + "',"
                    + "mual_muntah='" + mual + "',"
                    + "diare='" + diare + "',"
                    + "odeme='" + odeme + "',"
                    + "anorexia='" + anorex + "',"
                    + "nyeri_ulu_hati='" + nyeri + "',"
                    + "kesulitan_menelan='" + sulit + "',"
                    + "konstipasi='" + konsti + "',"
                    + "gangguan_gigi_geligi='" + ganggu + "',"
                    + "riwayat_makan1='" + cmbRiwMakan1.getSelectedItem() + "',"
                    + "riwayat_makan2='" + cmbRiwMakan2.getSelectedItem() + "',"
                    + "alergi_makanan='" + alergi + "',"
                    + "riwayat_penyakit_personal='" + riw_penyakitPersonal.getText() + "',"
                    + "kd_kamar='" + kdkamarnya + "', "
                    + "jam='" + Sequel.cariIsi("SELECT TIME(NOW())") + "', "
                    + "ket_alergi_makan='" + ket_alergi.getText() + "', "
                    + "diagnosa_medis='" + diag_medis.getText() + "'");
            
            Sequel.mengedit("status_gizi_inap", "no_rawat='" + norawat.getText() + "'", "status_gizi='" + cmbstatusGZ.getSelectedItem() + "' ");
            emptTeks();
            tampil();
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
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
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
            Valid.pindah(evt, BtnCari, bb);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbAsesmenGZUlangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsesmenGZUlangMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbAsesmenGZUlangMouseClicked

    private void tbAsesmenGZUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenGZUlangKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsesmenGZUlangKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
    tglAsses.requestFocus();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void cmbRiwMakan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRiwMakan1MouseClicked
        
    }//GEN-LAST:event_cmbRiwMakan1MouseClicked

    private void cmbRiwMakan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRiwMakan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRiwMakan1KeyPressed

    private void cmbRiwMakan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwMakan1ActionPerformed
       
    }//GEN-LAST:event_cmbRiwMakan1ActionPerformed

    private void riw_penyakitPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_riw_penyakitPersonalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            diag_medis.requestFocus();
        }        
    }//GEN-LAST:event_riw_penyakitPersonalKeyPressed

    private void tbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (bb.getText().trim().equals("") || tb.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Isilah BB & TB dengan benar tanpa menggunakan koma utk. mendapatkan nilai IMT,...!!");
            } else {
                hitungIMT();
                lla.requestFocus();
            }       
        }
    }//GEN-LAST:event_tbKeyPressed

    private void imtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imtKeyPressed

    }//GEN-LAST:event_imtKeyPressed

    private void llaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_llaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbstatusGZ.requestFocus();
        }
    }//GEN-LAST:event_llaKeyPressed

    private void cmbstatusGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbstatusGZKeyPressed

    }//GEN-LAST:event_cmbstatusGZKeyPressed

    private void ChkmualMuntahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkmualMuntahActionPerformed

    }//GEN-LAST:event_ChkmualMuntahActionPerformed

    private void ChkDiareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDiareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkDiareActionPerformed

    private void ChkOdemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkOdemeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkOdemeActionPerformed

    private void ChkAnorexiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAnorexiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnorexiaActionPerformed

    private void ChkKesulitanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKesulitanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkKesulitanActionPerformed

    private void ChkNyeriUluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkNyeriUluActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkNyeriUluActionPerformed

    private void ChkKonstipasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKonstipasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkKonstipasiActionPerformed

    private void ChkGangguanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGangguanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkGangguanActionPerformed

    private void cmbRiwMakan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRiwMakan2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRiwMakan2MouseClicked

    private void cmbRiwMakan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwMakan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRiwMakan2ActionPerformed

    private void cmbRiwMakan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRiwMakan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRiwMakan2KeyPressed

    private void ChkAlergiMakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiMakanActionPerformed
        if (ChkAlergiMakan.isSelected() == false) {
            ket_alergi.setText("");
        } else {
            ket_alergi.requestFocus();
        }
    }//GEN-LAST:event_ChkAlergiMakanActionPerformed

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

    private void tglAssesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tglAssesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tglAssesMouseClicked

    private void tgl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl1MouseClicked

    private void tgl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl2MouseClicked

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            tbAsesmenGZUlang.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else {
            Sequel.queryu("delete from assesmen_gizi_ulang where no_rawat='" + norawat.getText() + "' and tgl_assesmen='" + tgl + "' and jam='" + jam + "'");
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void bio_domainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bio_domainKeyPressed

    }//GEN-LAST:event_bio_domainKeyPressed

    private void ket_alergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ket_alergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            riw_penyakitPersonal.requestFocus();
        }
    }//GEN-LAST:event_ket_alergiKeyPressed

    private void diag_medisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diag_medisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_diag_medisKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgAssesmenGiziUlang dialog = new DlgAssesmenGiziUlang(new javax.swing.JFrame(), true);
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
    private widget.CekBox ChkAlergiMakan;
    private widget.CekBox ChkAnorexia;
    private widget.CekBox ChkDiare;
    private widget.CekBox ChkGangguan;
    public widget.CekBox ChkInput;
    private widget.CekBox ChkKesulitan;
    private widget.CekBox ChkKonstipasi;
    private widget.CekBox ChkNyeriUlu;
    private widget.CekBox ChkOdeme;
    private widget.CekBox ChkmualMuntah;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll7;
    public widget.TextBox TCari;
    private widget.TextBox bb;
    private widget.TextArea bio_domain;
    private widget.ComboBox cmbRiwMakan1;
    private widget.ComboBox cmbRiwMakan2;
    public widget.ComboBox cmbstatusGZ;
    private widget.TextBox diag_medis;
    private widget.TextBox imt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox ket_alergi;
    private widget.TextBox lla;
    private widget.TextBox nmpasien;
    private widget.TextBox nmruangan;
    private widget.TextBox norawat;
    private widget.TextBox norm;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.TextBox riw_penyakitPersonal;
    private widget.TextBox tb;
    private widget.Table tbAsesmenGZUlang;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tglAsses;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ag.no_rawat, p.no_rkm_medis, p.nm_pasien, ag.tgl_assesmen, ag.BB, ag.TB, ag.IMT, ag.LLA, ag.status_gizi, ag.biokimia_domain, IF(ag.mual_muntah='true','V','') mual, "
                    + "IF(ag.diare='true','V','') diare, IF(ag.odeme='true','V','') odeme, IF(ag.anorexia='true','V','') anor, IF(ag.nyeri_ulu_hati='true','V','') nyeri, IF(ag.kesulitan_menelan='true','V','') kesul, "
                    + "IF(ag.konstipasi='true','V','') kons, IF(ag.gangguan_gigi_geligi='true','V','') ganggu, ag.riwayat_makan1, ag.riwayat_makan2, IF(ag.alergi_makanan='true','V','') alergi, "
                    + "ag.riwayat_penyakit_personal, ag.kd_kamar, ag.jam, ag.ket_alergi_makan, ag.diagnosa_medis FROM assesmen_gizi_ulang ag "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=ag.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                    + "ag.tgl_assesmen between ? and ? and ag.no_rawat like ? or "
                    + "ag.tgl_assesmen between ? and ? and p.no_rkm_medis like ? or "
                    + "ag.tgl_assesmen between ? and ? and p.nm_pasien like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.BB like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.TB like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.IMT like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.LLA like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.status_gizi like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.biokimia_domain like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.riwayat_makan1 like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.riwayat_makan2 like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.ket_alergi_makan like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.diagnosa_medis like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.riwayat_penyakit_personal like ? order by ag.tgl_assesmen desc, ag.jam desc");
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
                        rs.getString("tgl_assesmen"),
                        rs.getString("jam"),
                        rs.getString("BB"),
                        rs.getString("TB"),                        
                        rs.getString("IMT"),
                        rs.getString("LLA"),
                        rs.getString("status_gizi"),
                        rs.getString("biokimia_domain"),
                        rs.getString("mual"),
                        rs.getString("diare"),
                        rs.getString("odeme"),
                        rs.getString("anor"),
                        rs.getString("nyeri"),
                        rs.getString("kesul"),
                        rs.getString("kons"),
                        rs.getString("ganggu"),
                        rs.getString("riwayat_makan1"),
                        rs.getString("riwayat_makan2"),
                        rs.getString("alergi"),
                        rs.getString("ket_alergi_makan"),
                        rs.getString("riwayat_penyakit_personal"),
                        rs.getString("diagnosa_medis"),
                        rs.getString("kd_kamar")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.DlgAssesmenGiziHarianUlang.tampil() : " + e);
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
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {   
        tglAsses.setDate(new Date());   
        jam = "";
        bb.setText("0");
        tb.setText("0");
        imt.setText("");
        lla.setText("");
        cmbstatusGZ.setSelectedIndex(0);        
        bio_domain.setText("");
        ChkmualMuntah.setSelected(false);
        ChkDiare.setSelected(false);
        ChkOdeme.setSelected(false);
        ChkAnorexia.setSelected(false);
        ChkNyeriUlu.setSelected(false);
        ChkKesulitan.setSelected(false);
        ChkKonstipasi.setSelected(false);
        ChkGangguan.setSelected(false);
        cmbRiwMakan1.setSelectedIndex(0);
        cmbRiwMakan2.setSelectedIndex(0);
        ChkAlergiMakan.setSelected(false);
        ket_alergi.setText("");
        riw_penyakitPersonal.setText("");
        diag_medis.setText("");
    }

    private void getData() {
        tgl = "";
        jam = "";
        kdkamarnya = "";
        mual = ""; 
        diare = "";
        odeme = "";
        anorex = "";
        nyeri = "";
        sulit = "";
        konsti = "";
        ganggu = "";
        alergi = "";
        
        if(tbAsesmenGZUlang.getSelectedRow()!= -1){  
            norawat.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 0).toString());
            norm.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 1).toString());
            nmpasien.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 2).toString());
            kdkamarnya = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 25).toString();
            nmruangan.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdkamarnya + "'"));
            tgl = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 3).toString();
            jam = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 4).toString();
            Valid.SetTgl(tglAsses, tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 3).toString());
            bb.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 5).toString());
            tb.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 6).toString());
            imt.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 7).toString());
            lla.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 8).toString());
            cmbstatusGZ.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 9).toString());
            bio_domain.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 10).toString());
            mual = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 11).toString();
            diare = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 12).toString();
            odeme = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 13).toString();
            anorex = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 14).toString();
            nyeri = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 15).toString();
            sulit = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 16).toString();
            konsti = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 17).toString();
            ganggu = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 18).toString();
            cmbRiwMakan1.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 19).toString());
            cmbRiwMakan2.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 20).toString());
            alergi = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 21).toString();
            ket_alergi.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 22).toString());
            riw_penyakitPersonal.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 22).toString());
            diag_medis.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 24).toString());
            NilaiBalik();
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 410));
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
        if (ChkmualMuntah.isSelected() == true) {
            mual = "true";
        } else {
            mual = "false";
        }
        
        if (ChkDiare.isSelected() == true) {
            diare = "true";
        } else {
            diare = "false";
        }
        
        if (ChkOdeme.isSelected() == true) {
            odeme = "true";
        } else {
            odeme = "false";
        }
        
        if (ChkAnorexia.isSelected() == true) {
            anorex = "true";
        } else {
            anorex = "false";
        }
        
        if (ChkNyeriUlu.isSelected() == true) {
            nyeri = "true";
        } else {
            nyeri = "false";
        }
        
        if (ChkKesulitan.isSelected() == true) {
            sulit = "true";
        } else {
            sulit = "false";
        }
        
        if (ChkKonstipasi.isSelected() == true) {
            konsti = "true";
        } else {
            konsti = "false";
        }
        
        if (ChkGangguan.isSelected() == true) {
            ganggu = "true";
        } else {
            ganggu = "false";
        }
        
        if (ChkAlergiMakan.isSelected() == true) {
            alergi = "true";
        } else {
            alergi = "false";
        }
    }
    
    private void NilaiBalik() {
        if (mual.equals("V")) {
            ChkmualMuntah.setSelected(true);
        } else {
            ChkmualMuntah.setSelected(false);
        }

        if (diare.equals("V")) {
            ChkDiare.setSelected(true);
        } else {
            ChkDiare.setSelected(false);
        }

        if (odeme.equals("V")) {
            ChkOdeme.setSelected(true);
        } else {
            ChkOdeme.setSelected(false);
        }

        if (anorex.equals("V")) {
            ChkAnorexia.setSelected(true);
        } else {
            ChkAnorexia.setSelected(false);
        }

        if (nyeri.equals("V")) {
            ChkNyeriUlu.setSelected(true);
        } else {
            ChkNyeriUlu.setSelected(false);
        }

        if (sulit.equals("V")) {
            ChkKesulitan.setSelected(true);
        } else {
            ChkKesulitan.setSelected(false);
        }

        if (konsti.equals("V")) {
            ChkKonstipasi.setSelected(true);
        } else {
            ChkKonstipasi.setSelected(false);
        }

        if (ganggu.equals("V")) {
            ChkGangguan.setSelected(true);
        } else {
            ChkGangguan.setSelected(false);
        }

        if (alergi.equals("V")) {
            ChkAlergiMakan.setSelected(true);
        } else {
            ChkAlergiMakan.setSelected(false);
        }
    }
    
    public void setPasien(String norw, String ruangInap, String kdKamar, String diagnosa) {
        norawat.setText(norw);
        norm.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norawat.getText() + "'"));
        nmpasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + norm.getText() + "'"));
        diag_medis.setText(diagnosa);
        nmruangan.setText(ruangInap);
        kdkamarnya = kdKamar;
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getassesmen_gizi_ulang());
        BtnGanti.setEnabled(akses.getassesmen_gizi_ulang());
        BtnHapus.setEnabled(akses.getassesmen_gizi_ulang());
    }
    
    private void hitungIMT() {
        //diubah menjadi satuan meter dulu
        angkaTB = Double.parseDouble(tb.getText()) / 100;
        //mendapatkan nilai tinggi badan untuk dihitung
        nilaiTB = angkaTB * angkaTB;
        //mendapatkan nilai IMT
        nilaiIMT = Double.parseDouble(bb.getText()) / nilaiTB;
        imt.setText(Valid.SetAngka4(nilaiIMT));
    }
}
