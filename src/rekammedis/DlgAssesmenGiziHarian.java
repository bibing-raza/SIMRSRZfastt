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
import simrskhanza.DlgCariDiet;
import simrskhanza.DlgCariJumlahPemberianDiet;

public class DlgAssesmenGiziHarian extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private DlgCariDiet diet = new DlgCariDiet(null, false);
    private DlgCariJumlahPemberianDiet jlhberi = new DlgCariJumlahPemberianDiet(null, false);
    private int i = 0, j = 0, cekdiagGZ = 0;
    private double angkaTB = 0, nilaiTB = 0, nilaiIMT = 0;
    private String mual = "", diare = "", odeme = "", anorex = "", nyeri = "", sulit = "", dataDiet = "",
            konsti = "", ganggu = "", alergi = "", kdkamarnya = "", jam = "", tgl = "", norwdiag = "", tglDiag = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgAssesmenGiziHarian(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Tgl. Assesment", "Jam", "BB", "TB", "IMT", "LLA", "Status Gizi",
            "Biokimia Domain", "Mual Muntah", "Diare", "Odeme", "Anorexia", "Nyeri Ulu Hati", "Kesulitan Menelan", "Konstipasi", "Gangguan Gigi Geligi", "Riwayat Makan 1",
            "Riwayat Makan 2", "Alergi Makanan", "Ket. Alergi Makanan", "Rwt. Penykt. Personal", "Diagnosa Medis", "Bentuk Makan", "Jenis Diet", "Ket. Jenis Diet", "Kalori", "Protein",
            "Lemak", "Karbohidrat", "kdkmr", "kddiet"
        };
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbAsesmenGZ.setModel(tabMode);
        tbAsesmenGZ.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmenGZ.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 34; i++) {
            TableColumn column = tbAsesmenGZ.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(80);
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
                column.setPreferredWidth(150);
            } else if (i == 23) {
                column.setPreferredWidth(300);
            } else if (i == 24) {
                column.setPreferredWidth(300);
            } else if (i == 25) {
                column.setPreferredWidth(80);
            } else if (i == 26) {
                column.setPreferredWidth(80);
            } else if (i == 27) {
                column.setPreferredWidth(150);
            } else if (i == 28) {
                column.setPreferredWidth(50);
            } else if (i == 29) {
                column.setPreferredWidth(50);
            } else if (i == 30) {
                column.setPreferredWidth(50);
            } else if (i == 31) {
                column.setPreferredWidth(80);
            } else if (i == 32) {
//                column.setPreferredWidth(120);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
//                column.setPreferredWidth(120);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAsesmenGZ.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"Cek","Kode", "Deskripsi Diagnosa Gizi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbKatalogGZ.setModel(tabMode1);
        tbKatalogGZ.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKatalogGZ.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbKatalogGZ.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(460);
            }
        }
        tbKatalogGZ.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{"Cek","Kode", "Deskripsi Diagnosa Gizi","tgl","jam","norawat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbdiagnosaGZ.setModel(tabMode2);
        tbdiagnosaGZ.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbdiagnosaGZ.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbdiagnosaGZ.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(320);
            } else if (i == 3) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbdiagnosaGZ.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{"kd_diet","#", "Nama Diet", "Jns. Makanan","Jlh. Pemberian"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 1) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbDiet.setModel(tabMode3);
        tbDiet.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbDiet.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(140);
            } else if (i == 4) {
                column.setPreferredWidth(105);
            }
        }
        tbDiet.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        TCari1.setDocument(new batasInput((byte)100).getKata(TCari1));
        kode.setDocument(new batasInput((byte)10).getKata(kode));
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
        
        jlhberi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgAssesmenGiziHarian")) {
                    if (jlhberi.getTable().getSelectedRow() != -1) {
                        kdberi.setText(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 0).toString());
                        jumlahBeri.setText(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 1).toString());
                        cmbSatuan.setSelectedItem(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 2).toString());
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
        
        jlhberi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgAssesmenGiziHarian")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        jlhberi.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        diet.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgAssesmenGiziHarian")) {
                    if (diet.getTable().getSelectedRow() != -1) {
                        kddiet.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 0).toString());
                        nmdiet.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 1).toString());
                        jnsMakanan.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 2).toString());
                        
                        if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                            kdberi.setText("-");
                            jumlahBeri.setText("");
                            jumlahBeri.setEditable(true);
                            cmbSatuan.setEnabled(true);
                            jumlahBeri.requestFocus();
                        } else {
                            jumlahBeri.setEditable(false);
                            cmbSatuan.setEnabled(false);
                            btnJumlahBeri.requestFocus();
                        }
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
        
        diet.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgAssesmenGiziHarian")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        diet.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
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

        WindowDiagnosaGizi = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel4 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        jLabel31 = new widget.Label();
        kode = new widget.TextBox();
        jLabel34 = new widget.Label();
        deskripsiGZ = new widget.TextBox();
        jLabel32 = new widget.Label();
        LCount1 = new widget.Label();
        panelGlass11 = new widget.panelisi();
        jLabel33 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        panelGlass12 = new widget.panelisi();
        BtnBatal1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar1 = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbKatalogGZ = new widget.Table();
        WindowDataDiet = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        BtnCloseIn3 = new widget.Button();
        nmdiet = new widget.TextBox();
        kddiet = new widget.TextBox();
        jLabel46 = new widget.Label();
        jnsMakanan = new widget.TextBox();
        BtnSimpan2 = new widget.Button();
        jLabel47 = new widget.Label();
        kdberi = new widget.TextBox();
        jumlahBeri = new widget.TextBox();
        cmbSatuan = new widget.ComboBox();
        btnDiet = new widget.Button();
        btnJumlahBeri = new widget.Button();
        jLabel48 = new widget.Label();
        TKd = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbAsesmenGZ = new widget.Table();
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
        jLabel11 = new widget.Label();
        BtnDiagnosa = new widget.Button();
        jLabel12 = new widget.Label();
        cmbBentukMakan = new widget.ComboBox();
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
        jLabel20 = new widget.Label();
        ChkAlergiMakan = new widget.CekBox();
        jLabel8 = new widget.Label();
        jLabel13 = new widget.Label();
        ket_jenisDiet = new widget.TextBox();
        jLabel21 = new widget.Label();
        cmbKalori = new widget.ComboBox();
        jLabel22 = new widget.Label();
        cmbProtein = new widget.ComboBox();
        jLabel23 = new widget.Label();
        cmbLemak = new widget.ComboBox();
        jLabel24 = new widget.Label();
        cmbKarbo = new widget.ComboBox();
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
        jLabel36 = new widget.Label();
        diag_medis = new widget.TextBox();
        Scroll4 = new widget.ScrollPane();
        tbdiagnosaGZ = new widget.Table();
        BtnHapus1 = new widget.Button();
        BtnDiet = new widget.Button();
        jLabel30 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        tbDiet = new widget.Table();
        BtnSeek3 = new widget.Button();

        WindowDiagnosaGizi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDiagnosaGizi.setName("WindowDiagnosaGizi"); // NOI18N
        WindowDiagnosaGizi.setUndecorated(true);
        WindowDiagnosaGizi.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Katalog Diagnosa Gizi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(44, 144));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Kode : ");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(jLabel31);

        kode.setForeground(new java.awt.Color(0, 0, 0));
        kode.setName("kode"); // NOI18N
        kode.setPreferredSize(new java.awt.Dimension(60, 23));
        kode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodeKeyPressed(evt);
            }
        });
        panelGlass9.add(kode);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Deskripsi Diagnosa Gizi : ");
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(125, 23));
        panelGlass9.add(jLabel34);

        deskripsiGZ.setForeground(new java.awt.Color(0, 0, 0));
        deskripsiGZ.setName("deskripsiGZ"); // NOI18N
        deskripsiGZ.setPreferredSize(new java.awt.Dimension(300, 23));
        deskripsiGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deskripsiGZKeyPressed(evt);
            }
        });
        panelGlass9.add(deskripsiGZ);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Record :");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass9.add(jLabel32);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 30));
        panelGlass9.add(LCount1);

        jPanel4.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Key Word : ");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel33);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('6');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+6");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass11.add(BtnCari1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnAll1);

        jPanel4.add(panelGlass11, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnBatal1);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnSimpan1);

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
        panelGlass12.add(BtnEdit);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnKeluar1);

        jPanel4.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        internalFrame5.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        Scroll1.setToolTipText("Silahkan pilih salah satu data katalog diagnosa gizi");
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbKatalogGZ.setToolTipText("");
        tbKatalogGZ.setName("tbKatalogGZ"); // NOI18N
        tbKatalogGZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKatalogGZMouseClicked(evt);
            }
        });
        tbKatalogGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKatalogGZKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbKatalogGZ);

        internalFrame5.add(Scroll1, java.awt.BorderLayout.CENTER);

        WindowDiagnosaGizi.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowDataDiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataDiet.setName("WindowDataDiet"); // NOI18N
        WindowDataDiet.setUndecorated(true);
        WindowDataDiet.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Diet ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(null);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnCloseIn3);
        BtnCloseIn3.setBounds(350, 110, 80, 30);

        nmdiet.setEditable(false);
        nmdiet.setForeground(new java.awt.Color(0, 0, 0));
        nmdiet.setName("nmdiet"); // NOI18N
        internalFrame9.add(nmdiet);
        nmdiet.setBounds(207, 20, 200, 23);

        kddiet.setEditable(false);
        kddiet.setForeground(new java.awt.Color(0, 0, 0));
        kddiet.setName("kddiet"); // NOI18N
        internalFrame9.add(kddiet);
        kddiet.setBounds(124, 20, 80, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jenis Makanan :");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame9.add(jLabel46);
        jLabel46.setBounds(10, 47, 110, 23);

        jnsMakanan.setEditable(false);
        jnsMakanan.setForeground(new java.awt.Color(0, 0, 0));
        jnsMakanan.setName("jnsMakanan"); // NOI18N
        internalFrame9.add(jnsMakanan);
        jnsMakanan.setBounds(124, 47, 283, 23);

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnSimpan2);
        BtnSimpan2.setBounds(250, 110, 90, 30);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Jumlah Pemberian :");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame9.add(jLabel47);
        jLabel47.setBounds(10, 74, 110, 23);

        kdberi.setEditable(false);
        kdberi.setForeground(new java.awt.Color(0, 0, 0));
        kdberi.setName("kdberi"); // NOI18N
        internalFrame9.add(kdberi);
        kdberi.setBounds(124, 74, 80, 23);

        jumlahBeri.setEditable(false);
        jumlahBeri.setForeground(new java.awt.Color(0, 0, 0));
        jumlahBeri.setName("jumlahBeri"); // NOI18N
        internalFrame9.add(jumlahBeri);
        jumlahBeri.setBounds(207, 74, 130, 23);

        cmbSatuan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbSatuan.setName("cmbSatuan"); // NOI18N
        cmbSatuan.setPreferredSize(new java.awt.Dimension(105, 23));
        internalFrame9.add(cmbSatuan);
        cmbSatuan.setBounds(342, 74, 65, 23);

        btnDiet.setForeground(new java.awt.Color(0, 0, 0));
        btnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiet.setMnemonic('X');
        btnDiet.setToolTipText("Alt+X");
        btnDiet.setName("btnDiet"); // NOI18N
        btnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDietActionPerformed(evt);
            }
        });
        internalFrame9.add(btnDiet);
        btnDiet.setBounds(410, 20, 28, 23);

        btnJumlahBeri.setForeground(new java.awt.Color(0, 0, 0));
        btnJumlahBeri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnJumlahBeri.setMnemonic('X');
        btnJumlahBeri.setToolTipText("Alt+X");
        btnJumlahBeri.setName("btnJumlahBeri"); // NOI18N
        btnJumlahBeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJumlahBeriActionPerformed(evt);
            }
        });
        internalFrame9.add(btnJumlahBeri);
        btnJumlahBeri.setBounds(410, 74, 28, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Nama Diet : ");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame9.add(jLabel48);
        jLabel48.setBounds(10, 20, 110, 23);

        WindowDataDiet.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        TKd.setEditable(false);
        TKd.setForeground(new java.awt.Color(0, 0, 0));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Input Assesment (Asuhan Gizi) Harian ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAsesmenGZ.setAutoCreateRowSorter(true);
        tbAsesmenGZ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbAsesmenGZ.setToolTipText("Silahkan klik untuk memilih data yang akan diedit atau dihapus");
        tbAsesmenGZ.setName("tbAsesmenGZ"); // NOI18N
        tbAsesmenGZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsesmenGZMouseClicked(evt);
            }
        });
        tbAsesmenGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAsesmenGZKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAsesmenGZ);

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
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-12-2021" }));
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
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-12-2021" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 445));
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

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Bentuk Makan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(630, 216, 100, 23);

        BtnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosa.setToolTipText("");
        BtnDiagnosa.setName("BtnDiagnosa"); // NOI18N
        BtnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosaActionPerformed(evt);
            }
        });
        BtnDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(BtnDiagnosa);
        BtnDiagnosa.setBounds(1240, 66, 28, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Riwayat Penyakit Personal : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(630, 10, 160, 23);

        cmbBentukMakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbBentukMakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "BUBUR", "NASI BIASA", "NASI LUNAK", "CAIR", "SARING" }));
        cmbBentukMakan.setName("cmbBentukMakan"); // NOI18N
        cmbBentukMakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbBentukMakanMouseClicked(evt);
            }
        });
        cmbBentukMakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBentukMakanActionPerformed(evt);
            }
        });
        cmbBentukMakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbBentukMakanKeyPressed(evt);
            }
        });
        FormInput.add(cmbBentukMakan);
        cmbBentukMakan.setBounds(730, 216, 90, 23);

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
        riw_penyakitPersonal.setBounds(790, 10, 445, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
        jLabel5.setBounds(0, 180, 150, 23);

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

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Diagnosa Gizi : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(630, 66, 160, 23);

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

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Intervensi : ");
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(630, 188, 160, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jenis Diet : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(630, 273, 100, 23);

        ket_jenisDiet.setForeground(new java.awt.Color(0, 0, 0));
        ket_jenisDiet.setHighlighter(null);
        ket_jenisDiet.setName("ket_jenisDiet"); // NOI18N
        ket_jenisDiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ket_jenisDietKeyPressed(evt);
            }
        });
        FormInput.add(ket_jenisDiet);
        ket_jenisDiet.setBounds(730, 388, 330, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Kalori : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(822, 216, 50, 23);

        cmbKalori.setForeground(new java.awt.Color(0, 0, 0));
        cmbKalori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "600", "900", "1200", "1500", "1700", "1900", "2100" }));
        cmbKalori.setName("cmbKalori"); // NOI18N
        cmbKalori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbKaloriMouseClicked(evt);
            }
        });
        cmbKalori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKaloriActionPerformed(evt);
            }
        });
        cmbKalori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKaloriKeyPressed(evt);
            }
        });
        FormInput.add(cmbKalori);
        cmbKalori.setBounds(873, 216, 70, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Protein : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(946, 216, 80, 23);

        cmbProtein.setForeground(new java.awt.Color(0, 0, 0));
        cmbProtein.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "10 %", "15 %" }));
        cmbProtein.setName("cmbProtein"); // NOI18N
        cmbProtein.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbProteinMouseClicked(evt);
            }
        });
        cmbProtein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProteinActionPerformed(evt);
            }
        });
        cmbProtein.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbProteinKeyPressed(evt);
            }
        });
        FormInput.add(cmbProtein);
        cmbProtein.setBounds(1027, 216, 60, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Lemak : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(822, 244, 50, 23);

        cmbLemak.setForeground(new java.awt.Color(0, 0, 0));
        cmbLemak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "20 %", "25 %" }));
        cmbLemak.setName("cmbLemak"); // NOI18N
        cmbLemak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbLemakMouseClicked(evt);
            }
        });
        cmbLemak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLemakActionPerformed(evt);
            }
        });
        cmbLemak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLemakKeyPressed(evt);
            }
        });
        FormInput.add(cmbLemak);
        cmbLemak.setBounds(873, 244, 60, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Karbohidrat : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(946, 244, 80, 23);

        cmbKarbo.setForeground(new java.awt.Color(0, 0, 0));
        cmbKarbo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "65 %", "70 %" }));
        cmbKarbo.setName("cmbKarbo"); // NOI18N
        cmbKarbo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbKarboMouseClicked(evt);
            }
        });
        cmbKarbo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKarboActionPerformed(evt);
            }
        });
        cmbKarbo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKarboKeyPressed(evt);
            }
        });
        FormInput.add(cmbKarbo);
        cmbKarbo.setBounds(1027, 244, 60, 23);

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
        tglAsses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-12-2021" }));
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

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Diagnosa Medis : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(630, 38, 160, 23);

        diag_medis.setForeground(new java.awt.Color(0, 0, 0));
        diag_medis.setHighlighter(null);
        diag_medis.setName("diag_medis"); // NOI18N
        diag_medis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diag_medisKeyPressed(evt);
            }
        });
        FormInput.add(diag_medis);
        diag_medis.setBounds(790, 38, 445, 23);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbdiagnosaGZ.setAutoCreateRowSorter(true);
        tbdiagnosaGZ.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbdiagnosaGZ.setName("tbdiagnosaGZ"); // NOI18N
        tbdiagnosaGZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdiagnosaGZMouseClicked(evt);
            }
        });
        tbdiagnosaGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbdiagnosaGZKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbdiagnosaGZ);

        FormInput.add(Scroll4);
        Scroll4.setBounds(790, 66, 445, 118);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setToolTipText("");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
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
        FormInput.add(BtnHapus1);
        BtnHapus1.setBounds(1240, 95, 28, 23);

        BtnDiet.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiet.setToolTipText("");
        BtnDiet.setName("BtnDiet"); // NOI18N
        BtnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDietActionPerformed(evt);
            }
        });
        BtnDiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDietKeyPressed(evt);
            }
        });
        FormInput.add(BtnDiet);
        BtnDiet.setBounds(1180, 273, 28, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Keterangan Diet : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(630, 388, 100, 23);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbDiet.setAutoCreateRowSorter(true);
        tbDiet.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiet.setName("tbDiet"); // NOI18N
        Scroll5.setViewportView(tbDiet);

        FormInput.add(Scroll5);
        Scroll5.setBounds(733, 273, 440, 110);

        BtnSeek3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnSeek3.setMnemonic('X');
        BtnSeek3.setToolTipText("Alt+X");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        BtnSeek3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek3KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek3);
        BtnSeek3.setBounds(1180, 302, 28, 23);

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
        } else if (ChkAlergiMakan.isSelected() == true && ket_alergi.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika alergi makanan dipilih, keterangan alergi harus diisi,...!!");
            ket_alergi.requestFocus();
        } else if (riw_penyakitPersonal.getText().trim().equals("")) {
            Valid.textKosong(riw_penyakitPersonal, "Riwayat Penyakit Personal");
        } else if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diganosa gizi masih kosong...!!!!");
            BtnDiagnosa.requestFocus();
        } else if (cmbBentukMakan.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu bentuk makan dengan benar,...!!");
        } else if (cmbKalori.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Kalori dengan benar,...!!");
        } else if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Jenis diet belum terisi...!!");
        } else if (cmbProtein.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Protein dengan benar,...!!");
        } else if (cmbLemak.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Lemak dengan benar,...!!");
        } else if (cmbKarbo.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Karbohidrat dengan benar,...!!");
        } else {
            dietOK();
            NilaiTrueFalse();
            if (Sequel.menyimpantf2("assesmen_gizi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Assesment Gizi (Asuhan Gizi)", 31,
                    new String[]{norawat.getText(), Valid.SetTgl(tglAsses.getSelectedItem() + ""), bb.getText(), tb.getText(),
                        imt.getText(), lla.getText(), cmbstatusGZ.getSelectedItem().toString(), bio_domain.getText(), mual, diare, odeme, anorex, nyeri, sulit, konsti, ganggu,
                        cmbRiwMakan1.getSelectedItem().toString(), cmbRiwMakan2.getSelectedItem().toString(), alergi, riw_penyakitPersonal.getText(),
                        cmbBentukMakan.getSelectedItem().toString(), TKd.getText(), cmbKalori.getSelectedItem().toString(), cmbProtein.getSelectedItem().toString(),
                        cmbLemak.getSelectedItem().toString(), cmbKarbo.getSelectedItem().toString(), kdkamarnya, Sequel.cariIsi("SELECT TIME(NOW())"),
                        ket_jenisDiet.getText(), ket_alergi.getText(), diag_medis.getText()}) == true) {
                SimpanDiagnosaGZ();
                Sequel.mengedit("status_gizi_inap", "no_rawat='" + norawat.getText() + "'", "status_gizi='" + cmbstatusGZ.getSelectedItem() + "' ");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + norawat.getText() + "','" + kdkamarnya + "','"
                            + Valid.SetTgl(tglAsses.getSelectedItem() + "") + "','Assesmen','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet assesmen");
                }
            }
            emptTeks();
            tampil();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,bb,BtnBatal);
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
        } else if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diganosa gizi masih kosong...!!!!");
            BtnDiagnosa.requestFocus();
        } else if (cmbBentukMakan.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu bentuk makan dengan benar,...!!");
        } else if (cmbKalori.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Kalori dengan benar,...!!");
        } else if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Jenis diet belum terisi...!!");
        } else if (cmbProtein.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Protein dengan benar,...!!");
        } else if (cmbLemak.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Lemak dengan benar,...!!");
        } else if (cmbKarbo.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu Karbohidrat dengan benar,...!!");
        } else {
            dietOK();
            NilaiTrueFalse();
            Sequel.queryu("delete from diet_ranap_daftar_rincian "
                    + "where no_rawat='" + norawat.getText() + "' "
                    + "and tanggal='" + tgl + "' and waktu='Assesmen'");
            
            Sequel.mengedit("assesmen_gizi", "no_rawat='" + norawat.getText() + "' and tgl_assesmen='" + tgl + "' and jam='" + jam + "'",
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
                    + "bentuk_makan='" + cmbBentukMakan.getSelectedItem() + "',"
                    + "kd_diet='" + TKd.getText() + "',"
                    + "kalori='" + cmbKalori.getSelectedItem() + "',"
                    + "protein='" + cmbProtein.getSelectedItem() + "',"
                    + "lemak='" + cmbLemak.getSelectedItem() + "',"
                    + "karbohidrat='" + cmbKarbo.getSelectedItem() + "',"
                    + "kd_kamar='" + kdkamarnya + "',"
                    + "jam='" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                    + "ket_jenis_diet='" + ket_jenisDiet.getText() + "',"
                    + "ket_alergi_makan='" + ket_alergi.getText() + "',"
                    + "diagnosa_medis='" + diag_medis.getText() + "'");
            
            Sequel.mengedit("status_gizi_inap", "no_rawat='" + norawat.getText() + "'", "status_gizi='" + cmbstatusGZ.getSelectedItem() + "' ");

            for (i = 0; i < tbDiet.getRowCount(); i++) {
                Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + norawat.getText() + "','" + kdkamarnya + "','"
                        + Valid.SetTgl(tglAsses.getSelectedItem() + "") + "','Assesmen','"
                        + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet assesmen");
            }
            
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

    private void tbAsesmenGZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsesmenGZMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbAsesmenGZMouseClicked

    private void tbAsesmenGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenGZKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsesmenGZKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
    tglAsses.requestFocus();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("select satuan from diet_master_pemberian group by satuan", cmbSatuan);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosaActionPerformed
        if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data pasien masih belum terpilih...!!!!");
        } else {
            WindowDiagnosaGizi.setSize(704, 473);
            WindowDiagnosaGizi.setLocationRelativeTo(internalFrame1);
            WindowDiagnosaGizi.setAlwaysOnTop(false);
            WindowDiagnosaGizi.setVisible(true); 
            emptKatalog();
            tampilKatalog();
            isCekKatalog();
            TCari1.requestFocus();            
        }
    }//GEN-LAST:event_BtnDiagnosaActionPerformed

    private void BtnDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDiagnosaKeyPressed

    }//GEN-LAST:event_BtnDiagnosaKeyPressed

    private void cmbBentukMakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBentukMakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBentukMakanKeyPressed

    private void cmbBentukMakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbBentukMakanMouseClicked

    }//GEN-LAST:event_cmbBentukMakanMouseClicked

    private void cmbRiwMakan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRiwMakan1MouseClicked
        
    }//GEN-LAST:event_cmbRiwMakan1MouseClicked

    private void cmbRiwMakan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRiwMakan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRiwMakan1KeyPressed

    private void cmbRiwMakan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwMakan1ActionPerformed
       
    }//GEN-LAST:event_cmbRiwMakan1ActionPerformed

    private void cmbBentukMakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBentukMakanActionPerformed

    }//GEN-LAST:event_cmbBentukMakanActionPerformed

    private void riw_penyakitPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_riw_penyakitPersonalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnDiagnosa.requestFocus();
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            lla.requestFocus();
        }
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

    private void ket_jenisDietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ket_jenisDietKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbProtein.requestFocus();
        }
    }//GEN-LAST:event_ket_jenisDietKeyPressed

    private void cmbKaloriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbKaloriMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKaloriMouseClicked

    private void cmbKaloriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKaloriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKaloriActionPerformed

    private void cmbKaloriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKaloriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKaloriKeyPressed

    private void cmbProteinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbProteinMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProteinMouseClicked

    private void cmbProteinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProteinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProteinActionPerformed

    private void cmbProteinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbProteinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProteinKeyPressed

    private void cmbLemakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbLemakMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLemakMouseClicked

    private void cmbLemakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLemakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLemakActionPerformed

    private void cmbLemakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLemakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLemakKeyPressed

    private void cmbKarboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbKarboMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKarboMouseClicked

    private void cmbKarboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKarboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKarboActionPerformed

    private void cmbKarboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKarboKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKarboKeyPressed

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
            tbAsesmenGZ.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else {
            Sequel.queryu("delete from assesmen_gizi where no_rawat='" + norawat.getText() + "' and tgl_assesmen='" + tgl + "' and jam='" + jam + "'");
            Sequel.queryu("delete from diagnosa_gizi_pasien where no_rawat='" + norawat.getText() + "' and tgl_input='" + tgl + "'");
            Sequel.queryu("delete from diet_ranap_daftar_rincian "
                            + "where no_rawat='" + norawat.getText() + "' "
                            + "and kd_kamar='" + kdkamarnya + "' "
                            + "and tanggal='" + tgl + "' "
                            + "and waktu='Assesmen'");
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilKatalog();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnCari1);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        tampilKatalog();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampilKatalog();
            TCari1.setText("");
        } else {
            Valid.pindah(evt, TCari1, BtnKeluar1);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        Dipilihdiagnosa();
        WindowDiagnosaGizi.dispose();
        cmbBentukMakan.requestFocus();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal1, cmbBentukMakan);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void tbKatalogGZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKatalogGZMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataKatalog();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKatalogGZMouseClicked

    private void tbKatalogGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKatalogGZKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKatalog();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbKatalogGZKeyPressed

    private void kodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeKeyPressed

    private void deskripsiGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deskripsiGZKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deskripsiGZKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (kode.getText().trim().equals("")) {
            Valid.textKosong(kode, "Kode Katalog");
        } else if (deskripsiGZ.getText().trim().equals("")) {
            Valid.textKosong(deskripsiGZ, "Deskripsi Diagnosa Gizi");
        } else {
            Sequel.menyimpan("katalog_diagnosa_gizi", "'" + kode.getText() + "','" + deskripsiGZ.getText() + "'", "Katalog Diagnosa Gizi");
            emptKatalog();
            tampilKatalog();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed

    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptKatalog();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (kode.getText().trim().equals("")) {
            Valid.textKosong(kode, "Kode Katalog");
        } else if (deskripsiGZ.getText().trim().equals("")) {
            Valid.textKosong(deskripsiGZ, "Deskripsi Diagnosa Gizi");
        } else {
            Sequel.mengedit("katalog_diagnosa_gizi", "kd_diagnosa_gizi='" + kode.getText() + "'", "deskripsi_diagnosa='" + deskripsiGZ.getText() + "'");
            emptKatalog();
            tampilKatalog();
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

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

    private void tbdiagnosaGZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdiagnosaGZMouseClicked
//        if (tabMode2.getRowCount() != 0) {
//            try {
//                getDataDiagnosa();
//            } catch (java.lang.NullPointerException e) {
//            }
//        }
    }//GEN-LAST:event_tbdiagnosaGZMouseClicked

    private void tbdiagnosaGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbdiagnosaGZKeyPressed
//        if (tabMode2.getRowCount() != 0) {
//            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
//                try {
//                    getDataDiagnosa();
//                } catch (java.lang.NullPointerException e) {
//                }
//            }
//        }
    }//GEN-LAST:event_tbdiagnosaGZKeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        cekdiagGZ = 0;
        
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diagnosa gizi masih kosong...!!!!");
        } else {
            cekdiagGZ = Sequel.cariInteger("select count(-1) from diagnosa_gizi_pasien where "
                    + "no_rawat='" + norawat.getText() + "' and tgl_input='" + Valid.SetTgl(tglAsses.getSelectedItem() + "") + "'");

            if (cekdiagGZ == 0) {
                for (i = 0; i < tbdiagnosaGZ.getRowCount(); i++) {
                    if (tbdiagnosaGZ.getValueAt(i, 0).toString().equals("true")) {
                        tabMode2.removeRow(i);
                    }
                }
            } else if (cekdiagGZ > 0) {
                for (i = 0; i < tbdiagnosaGZ.getRowCount(); i++) {
                    if (tbdiagnosaGZ.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu("delete from diagnosa_gizi_pasien where no_rawat='" + tbdiagnosaGZ.getValueAt(i, 5).toString() + "' and "
                                + "kd_diagnosa_gz='" + tbdiagnosaGZ.getValueAt(i, 1).toString() + "' and "
                                + "tgl_input='" + tbdiagnosaGZ.getValueAt(i, 3).toString() + "' and "
                                + "jam_input='" + tbdiagnosaGZ.getValueAt(i, 4).toString() + "'");
                    }
                }
                tampilDiagnosanya(norawat.getText(), Valid.SetTgl(tglAsses.getSelectedItem() + ""));
            }  
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDietActionPerformed
        kddiet.setText("");
        nmdiet.setText("");
        jnsMakanan.setText("");
        kdberi.setText("");
        jumlahBeri.setText("");
        cmbSatuan.setSelectedIndex(0);
        jumlahBeri.setEditable(false);
        cmbSatuan.setEnabled(false);

        WindowDataDiet.setSize(458, 157);
        WindowDataDiet.setLocationRelativeTo(internalFrame1);
        WindowDataDiet.setVisible(true);
        WindowDataDiet.requestFocus();
    }//GEN-LAST:event_BtnDietActionPerformed

    private void BtnDietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDietKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDietKeyPressed

    private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (tbDiet.getValueAt(i, 1).toString().equals("true")) {
                tabMode3.removeRow(i);
            }
        }
        BtnSeek3.requestFocus();
    }//GEN-LAST:event_BtnSeek3ActionPerformed

    private void BtnSeek3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek3KeyPressed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        WindowDataDiet.dispose();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (kddiet.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis dietnya...!!!!");
        } else if (kdberi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis jlh. pemberian dietnya...!!!!");
        } else {
            if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                if (jumlahBeri.getText().trim().equals("-") || jumlahBeri.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Jlh. pemberian diet harus diisi dg. benar...!!!!");
                } else if (cmbSatuan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Pilih salah satu satuan jlh. pemberian dietnya dg. benar...!!!!");
                } else {
                    if (Sequel.cariInteger("select count(-1) from diet_master_pemberian where nm_pemberian='" + jumlahBeri.getText() + "' and satuan='" + cmbSatuan.getSelectedItem().toString() + "'") == 0) {
                        kdberi.setText("");
                        Valid.autoNomer("diet_master_pemberian", "DP", 4, kdberi);
                        Sequel.menyimpan("diet_master_pemberian", "'" + kdberi.getText() + "','" + jumlahBeri.getText() + "','" + cmbSatuan.getSelectedItem() + "','1'", "Jumlah Pemberian Diet");
                        tabMode3.addRow(new Object[]{kddiet.getText(), false, nmdiet.getText(), jnsMakanan.getText(), jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem()});
                        WindowDataDiet.dispose();
                    } else {
                        tabMode3.addRow(new Object[]{kddiet.getText(), false, nmdiet.getText(), jnsMakanan.getText(), jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem()});
                        WindowDataDiet.dispose();
                    }
                }
            } else {
                tabMode3.addRow(new Object[]{kddiet.getText(), false, nmdiet.getText(), jnsMakanan.getText(), jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem()});
                WindowDataDiet.dispose();
            }
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void btnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDietActionPerformed
        akses.setform("DlgAssesmenGiziHarian");
        diet.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        diet.setLocationRelativeTo(internalFrame1);
        diet.setVisible(true);
        diet.TCari.requestFocus();
    }//GEN-LAST:event_btnDietActionPerformed

    private void btnJumlahBeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJumlahBeriActionPerformed
        akses.setform("DlgAssesmenGiziHarian");
        jlhberi.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        jlhberi.setLocationRelativeTo(internalFrame1);
        jlhberi.tampil();
        jlhberi.setVisible(true);
        jlhberi.TCari.requestFocus();
    }//GEN-LAST:event_btnJumlahBeriActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgAssesmenGiziHarian dialog = new DlgAssesmenGiziHarian(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnDiagnosa;
    private widget.Button BtnDiet;
    private widget.Button BtnEdit;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnSeek3;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
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
    private widget.Label LCount1;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll7;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TKd;
    private javax.swing.JDialog WindowDataDiet;
    private javax.swing.JDialog WindowDiagnosaGizi;
    private widget.TextBox bb;
    private widget.TextArea bio_domain;
    private widget.Button btnDiet;
    private widget.Button btnJumlahBeri;
    private widget.ComboBox cmbBentukMakan;
    private widget.ComboBox cmbKalori;
    private widget.ComboBox cmbKarbo;
    private widget.ComboBox cmbLemak;
    private widget.ComboBox cmbProtein;
    private widget.ComboBox cmbRiwMakan1;
    private widget.ComboBox cmbRiwMakan2;
    private widget.ComboBox cmbSatuan;
    public widget.ComboBox cmbstatusGZ;
    private widget.TextBox deskripsiGZ;
    private widget.TextBox diag_medis;
    private widget.TextBox imt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame9;
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
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel36;
    private widget.Label jLabel4;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private widget.TextBox jnsMakanan;
    private widget.TextBox jumlahBeri;
    private widget.TextBox kdberi;
    private widget.TextBox kddiet;
    private widget.TextBox ket_alergi;
    private widget.TextBox ket_jenisDiet;
    private widget.TextBox kode;
    private widget.TextBox lla;
    private widget.TextBox nmdiet;
    private widget.TextBox nmpasien;
    private widget.TextBox nmruangan;
    private widget.TextBox norawat;
    private widget.TextBox norm;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox riw_penyakitPersonal;
    private widget.TextBox tb;
    private widget.Table tbAsesmenGZ;
    private widget.Table tbDiet;
    private widget.Table tbKatalogGZ;
    private widget.Table tbdiagnosaGZ;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tglAsses;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ag.no_rawat, p.no_rkm_medis, p.nm_pasien, ag.tgl_assesmen, ag.BB, ag.TB, ag.IMT, ag.LLA, ag.status_gizi, ag.biokimia_domain, IF(ag.mual_muntah='true','V','') mual, "
                    + "IF(ag.diare='true','V','') diare, IF(ag.odeme='true','V','') odeme, IF(ag.anorexia='true','V','') anor, IF(ag.nyeri_ulu_hati='true','V','') nyeri, IF(ag.kesulitan_menelan='true','V','') kesul, "
                    + "IF(ag.konstipasi='true','V','') kons, IF(ag.gangguan_gigi_geligi='true','V','') ganggu, ag.riwayat_makan1, ag.riwayat_makan2, IF(ag.alergi_makanan='true','V','') alergi, ag.riwayat_penyakit_personal, "
                    + "ag.bentuk_makan, d.nama_diet, ag.kalori, ag.protein, ag.lemak, ag.karbohidrat, ag.kd_kamar, ag.jam, ag.kd_diet, ag.ket_jenis_diet, ag.ket_alergi_makan, ag.diagnosa_medis FROM assesmen_gizi ag "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=ag.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN diet d on d.kd_diet=ag.kd_diet WHERE "
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
                    + "ag.tgl_assesmen between ? and ? and ag.riwayat_penyakit_personal like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.bentuk_makan like ? or "
                    + "ag.tgl_assesmen between ? and ? and d.nama_diet like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.kalori like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.protein like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.lemak like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.diagnosa_medis like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.ket_alergi_makan like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.ket_jenis_diet like ? or "
                    + "ag.tgl_assesmen between ? and ? and ag.karbohidrat like ? order by ag.tgl_assesmen desc, ag.jam desc");
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
                ps.setString(43, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(44, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(45, "%" + TCari.getText().trim() + "%");
                ps.setString(46, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(47, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(48, "%" + TCari.getText().trim() + "%");
                ps.setString(49, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(50, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(51, "%" + TCari.getText().trim() + "%");
                ps.setString(52, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(53, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(54, "%" + TCari.getText().trim() + "%");                
                ps.setString(55, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(56, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(57, "%" + TCari.getText().trim() + "%");   
                ps.setString(58, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(59, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(60, "%" + TCari.getText().trim() + "%");   
                ps.setString(61, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(62, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(63, "%" + TCari.getText().trim() + "%");   
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
                        rs.getString("bentuk_makan"),
                        rs.getString("nama_diet"),
                        rs.getString("ket_jenis_diet"),
                        rs.getString("kalori"),
                        rs.getString("protein"),
                        rs.getString("lemak"),
                        rs.getString("karbohidrat"),
                        rs.getString("kd_kamar"),
                        rs.getString("kd_diet")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.DlgAssesmenGiziHarian.tampil() : " + e);
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
        Valid.tabelKosong(tabMode2);
        Valid.tabelKosong(tabMode3);
        cmbBentukMakan.setSelectedIndex(0);
        cmbKalori.setSelectedIndex(0);
        TKd.setText("");
        ket_jenisDiet.setText("");
        cmbProtein.setSelectedIndex(0);
        cmbLemak.setSelectedIndex(0);
        cmbKarbo.setSelectedIndex(0);
    }

    private void getData() {
        tgl = "";
        jam = "";
        kdkamarnya = "";
        TKd.setText("");
        mual = ""; 
        diare = "";
        odeme = "";
        anorex = "";
        nyeri = "";
        sulit = "";
        konsti = "";
        ganggu = "";
        alergi = "";
        
        if(tbAsesmenGZ.getSelectedRow()!= -1){  
            norawat.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 0).toString());
            norm.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 1).toString());
            nmpasien.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 2).toString());
            kdkamarnya = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 32).toString();
            nmruangan.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdkamarnya + "'"));
            tgl = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 3).toString();
            jam = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 4).toString();
            Valid.SetTgl(tglAsses, tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 3).toString());
            bb.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 5).toString());
            tb.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 6).toString());
            imt.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 7).toString());
            lla.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 8).toString());
            cmbstatusGZ.setSelectedItem(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 9).toString());
            bio_domain.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 10).toString());
            mual = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 11).toString();
            diare = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 12).toString();
            odeme = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 13).toString();
            anorex = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 14).toString();
            nyeri = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 15).toString();
            sulit = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 16).toString();
            konsti = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 17).toString();
            ganggu = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 18).toString();
            cmbRiwMakan1.setSelectedItem(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 19).toString());
            cmbRiwMakan2.setSelectedItem(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 20).toString());
            alergi = tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 21).toString();
            ket_alergi.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 22).toString());
            riw_penyakitPersonal.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 23).toString());
            diag_medis.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 24).toString());
            cmbBentukMakan.setSelectedItem(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 25).toString());
//            nama_diet.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 26).toString());
            ket_jenisDiet.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 27).toString());
            cmbKalori.setSelectedItem(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 28).toString());
            cmbProtein.setSelectedItem(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 29).toString());
            cmbLemak.setSelectedItem(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 30).toString());
            cmbKarbo.setSelectedItem(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 31).toString());
            TKd.setText(tbAsesmenGZ.getValueAt(tbAsesmenGZ.getSelectedRow(), 33).toString());
            
            tampilDiet();
            NilaiBalik();
            tampilDiagnosanya(norawat.getText(), tgl);
        }
    }
    
    private void getDataKatalog() {
        if(tbKatalogGZ.getSelectedRow()!= -1){            
            kode.setText(tbKatalogGZ.getValueAt(tbKatalogGZ.getSelectedRow(),1).toString());
            deskripsiGZ.setText(tbKatalogGZ.getValueAt(tbKatalogGZ.getSelectedRow(),2).toString());
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 445));
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
        BtnSimpan.setEnabled(akses.getassesmen_gizi_harian());
        BtnGanti.setEnabled(akses.getassesmen_gizi_harian());
        BtnHapus.setEnabled(akses.getassesmen_gizi_harian());
    }
    
    private void emptKatalog() {
        kode.setText("");
        kode.requestFocus();
        deskripsiGZ.setText("");
    }
    
    private void tampilKatalog() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT kd_diagnosa_gizi, deskripsi_diagnosa from katalog_diagnosa_gizi where "
                    + "kd_diagnosa_gizi like ? or deskripsi_diagnosa like ? ORDER BY kd_diagnosa_gizi");
            try {
                ps1.setString(1, "%" + TCari1.getText().trim() + "%");
                ps1.setString(2, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{false,
                        rs1.getString("kd_diagnosa_gizi"),
                        rs1.getString("deskripsi_diagnosa")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.DlgAssesmenGiziHarian.tampilKatalog() : " + e);
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
        LCount1.setText("" + tabMode1.getRowCount());
    }
   
    private void isCekKatalog() {
        BtnSimpan1.setEnabled(akses.getassesmen_gizi_harian());
        BtnEdit.setEnabled(akses.getassesmen_gizi_harian());
    }
    
    private void tampilDiagnosanya(String norw, String tgldiagnosa) {
        norwdiag = norw;
        tglDiag = tgldiagnosa;
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT d.kd_diagnosa_gz, k.deskripsi_diagnosa, d.tgl_input, d.jam_input, d.no_rawat FROM diagnosa_gizi_pasien d "
                    + "inner join katalog_diagnosa_gizi k on k.kd_diagnosa_gizi=d.kd_diagnosa_gz where "
                    + "d.no_rawat='" + norwdiag + "' and d.tgl_input='" + tglDiag + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{false,
                        rs2.getString("kd_diagnosa_gz"),
                        rs2.getString("deskripsi_diagnosa"),
                        rs2.getString("tgl_input"),
                        rs2.getString("jam_input"),
                        rs2.getString("no_rawat")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.DlgAssesmenGiziHarian.tampilDiagnosanya() : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
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
    
    private void Dipilihdiagnosa() {
        cekdiagGZ = 0;
        
        if (norawat.getText().trim().equals("")) {
            Valid.textKosong(norawat, "Data Pasien");
        } else {
            try {
                j = 0;
                for (i = 0; i < tbKatalogGZ.getRowCount(); i++) {
                    if (tbKatalogGZ.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                if (j == 0) {
                    JOptionPane.showMessageDialog(null, "Belum ada diagnosa gizi yang dipilih,.!!!");
                    tampilKatalog();
                } else {
                    cekdiagGZ = Sequel.cariInteger("select count(-1) from diagnosa_gizi_pasien where "
                            + "no_rawat='" + norawat.getText() + "' and tgl_input='" + Valid.SetTgl(tglAsses.getSelectedItem() + "") + "'");

                    if (cekdiagGZ == 0) {
                        for (i = 0; i < tbKatalogGZ.getRowCount(); i++) {
                            if (tbKatalogGZ.getValueAt(i, 0).toString().equals("true")) {
                                tabMode2.addRow(new Object[]{false,
                                    tbKatalogGZ.getValueAt(i, 1).toString(),
                                    tbKatalogGZ.getValueAt(i, 2).toString()
                                });
                            }
                        }
                        emptKatalog();
                    } else if (cekdiagGZ > 0) {
                        for (i = 0; i < tbKatalogGZ.getRowCount(); i++) {
                            if (tbKatalogGZ.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.menyimpan("diagnosa_gizi_pasien", "'" + norawat.getText() + "','" + tbKatalogGZ.getValueAt(i, 1).toString() + "', "
                                        + "'" + Sequel.cariIsi("SELECT DATE(NOW())") + "','" + Sequel.cariIsi("SELECT TIME(NOW())") + "'", "Diagnosa Gizi");
                            }
                        }
                        tampilDiagnosanya(norawat.getText(), Valid.SetTgl(tglAsses.getSelectedItem() + ""));
                        emptKatalog();
                    }

                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
   
    private void SimpanDiagnosaGZ() {
        for (i = 0; i < tbdiagnosaGZ.getRowCount(); i++) {
            if (tbdiagnosaGZ.getValueAt(i, 0).toString().equals("true") || tbdiagnosaGZ.getValueAt(i, 0).toString().equals("false")) {
                Sequel.menyimpan("diagnosa_gizi_pasien", "'" + norawat.getText() + "','" + tbdiagnosaGZ.getValueAt(i, 1).toString() + "', "
                        + "'" + Sequel.cariIsi("SELECT DATE(NOW())") + "','" + Sequel.cariIsi("SELECT TIME(NOW())") + "'", "Diagnosa Gizi");
            }
        }
    }
    
    private void dietOK() {
        dataDiet = "";
        TKd.setText("");
        
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (dataDiet.equals("")) {
                dataDiet = tbDiet.getValueAt(i, 2).toString() + " " + tbDiet.getValueAt(i, 4).toString();
            } else {
                dataDiet = dataDiet + " + " + tbDiet.getValueAt(i, 2).toString() + " " + tbDiet.getValueAt(i, 4).toString();
            }
        }
        
        if (Sequel.cariInteger("select count(-1) from diet where nama_diet='" + dataDiet + "'") == 0) {
            Valid.autoNomer("diet", "DB", 4, TKd);
            Sequel.menyimpan("diet", "'" + TKd.getText() + "','" + dataDiet + "','UMUM','1'", "Data diet pasien");
        } else {
            TKd.setText(Sequel.cariIsi("select kd_diet from diet where nama_diet='" + dataDiet + "'"));
        }
    }
    
    private void tampilDiet() {
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("select d.kd_diet, dm.nama_diet, dm.jns_makanan, d.jlh_pemberian from diet_ranap_daftar_rincian d "
                    + "inner join diet_master dm on dm.kd_diet=d.kd_diet where d.no_rawat='" + norawat.getText() + "' "
                    + "and kd_kamar='" + kdkamarnya + "' and tanggal='" + tgl + "' and waktu='Assesmen'");

            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode3.addRow(new Object[]{
                        rs3.getString("kd_diet"),
                        false,
                        rs3.getString("nama_diet"),
                        rs3.getString("jns_makanan"),
                        rs3.getString("jlh_pemberian")
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
    }
}
