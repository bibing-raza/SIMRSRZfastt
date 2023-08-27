/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgRujuk.java
 *
 * Created on 31 Mei 10, 20:19:56
 */
package simrskhanza;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Timer;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author perpustakaan
 */
public final class DlgRujuk extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private String tgl = "", sql = "", kode_rujukanya = "";
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    public DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private int i = 0;
    private Date date = new Date();

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public DlgRujuk(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        Object[] row = {
            "No.Rujuk", "No.Rawat", "No.R.M.", "Nama Pasien", "Tempat Rujuk", "Tgl.Rujuk", "Jam Rujuk",
            "Keterangan Diagnosa", "Kode Dokter", "Dokter Perujuk", "Kategori Rujuk", "Ambulance", 
            "Keterangan","kd_rujukanya","tgl_rujuk","poliklinik_tujuan"
        };
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRujukanNon.setModel(tabMode);
        tbRujukanNon.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRujukanNon.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRujukanNon.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(125);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRujukanNon.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "No.Rujuk", "No.Rawat", "No.R.M.", "Nama Pasien", "Tempat Rujuk", "Tgl.Rujuk", "Jam Rujuk",
            "Keterangan Diagnosa", "Kode Dokter", "Dokter Perujuk", "Kategori Rujuk", "Ambulance", 
            "Keterangan","kd_rujukanya","tgl_rujuk","poliklinik_tujuan"}) {
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
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRujukanBPJS.setModel(tabMode1);
        tbRujukanBPJS.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRujukanBPJS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRujukanBPJS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(125);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRujukanBPJS.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRj.setDocument(new batasInput((byte) 10).getKata(TNoRj));
        diagnosanya.setDocument(new batasInput((int) 500).getKata(diagnosanya));
        ket.setDocument(new batasInput((int) 500).getKata(ket));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilLain();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilLain();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilLain();
                }
            });
        }

        jam();

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    kodeDok.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    nmDok.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    btnDokter.requestFocus();
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
        
        rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRujuk")) {
                    if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                        kode_rujukanya = "";
                        kdfaskes.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 1).toString());
                        TTmpRujuk.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                        kode_rujukanya = rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 4).toString();
                        TPTujuan.requestFocus();
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

        ChkInput.setSelected(false);
        isForm();        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSuratRujukan = new javax.swing.JMenuItem();
        cek_rujukan = new widget.TextBox();
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
        jLabel3 = new widget.Label();
        TNoRj = new widget.TextBox();
        jLabel8 = new widget.Label();
        TTmpRujuk = new widget.TextBox();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        jLabel9 = new widget.Label();
        TPasien = new widget.TextBox();
        DTPRujuk = new widget.Tanggal();
        jLabel12 = new widget.Label();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        btnDokter = new widget.Button();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        jLabel11 = new widget.Label();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        ambulance = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        CmbDetik = new widget.ComboBox();
        ktrujuk = new widget.ComboBox();
        kdfaskes = new widget.TextBox();
        btnFaskes = new widget.Button();
        Scroll21 = new widget.ScrollPane();
        ket = new widget.TextArea();
        jLabel16 = new widget.Label();
        TPTujuan = new widget.TextBox();
        kodeDok = new widget.TextBox();
        nmDok = new widget.TextBox();
        diagnosanya = new widget.TextBox();
        ChkInput = new widget.CekBox();
        TabRujuk = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbRujukanNon = new widget.Table();
        Scroll1 = new widget.ScrollPane();
        tbRujukanBPJS = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSuratRujukan.setBackground(new java.awt.Color(255, 255, 255));
        MnSuratRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratRujukan.setForeground(new java.awt.Color(0, 0, 0));
        MnSuratRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratRujukan.setText("Surat Rujukan");
        MnSuratRujukan.setName("MnSuratRujukan"); // NOI18N
        MnSuratRujukan.setPreferredSize(new java.awt.Dimension(170, 28));
        MnSuratRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratRujukanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratRujukan);

        cek_rujukan.setEditable(false);
        cek_rujukan.setForeground(new java.awt.Color(0, 0, 0));
        cek_rujukan.setHighlighter(null);
        cek_rujukan.setName("cek_rujukan"); // NOI18N
        cek_rujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cek_rujukanKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Rujukan Keluar ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
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
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
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

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl.Rujuk :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-03-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari1MouseClicked(evt);
            }
        });
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-03-2022" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari2MouseClicked(evt);
            }
        });
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
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
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 165));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. Rujuk :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 40, 72, 23);

        TNoRj.setEditable(false);
        TNoRj.setForeground(new java.awt.Color(0, 0, 0));
        TNoRj.setName("TNoRj"); // NOI18N
        FormInput.add(TNoRj);
        TNoRj.setBounds(75, 40, 190, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Rujuk :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(267, 40, 60, 23);

        TTmpRujuk.setEditable(false);
        TTmpRujuk.setForeground(new java.awt.Color(0, 0, 0));
        TTmpRujuk.setHighlighter(null);
        TTmpRujuk.setName("TTmpRujuk"); // NOI18N
        FormInput.add(TTmpRujuk);
        TTmpRujuk.setBounds(167, 70, 220, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 72, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(75, 10, 141, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Diagnosa :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(416, 130, 90, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(340, 10, 340, 23);

        DTPRujuk.setEditable(false);
        DTPRujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-03-2022" }));
        DTPRujuk.setDisplayFormat("dd-MM-yyyy");
        DTPRujuk.setName("DTPRujuk"); // NOI18N
        DTPRujuk.setOpaque(false);
        FormInput.add(DTPRujuk);
        DTPRujuk.setBounds(330, 40, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Di Rujuk Ke :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 70, 72, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(218, 10, 120, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Dokter :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 130, 72, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnDokter.setMnemonic('2');
        btnDokter.setToolTipText("Alt+2");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        FormInput.add(btnDokter);
        btnDokter.setBounds(390, 130, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText(" Kategori Rujuk :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(685, 10, 90, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText(" Ambulance :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(685, 40, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Keterangan :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(416, 70, 90, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(437, 40, 40, 23);

        CmbJam.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.setOpaque(false);
        FormInput.add(CmbJam);
        CmbJam.setBounds(480, 40, 55, 23);

        CmbMenit.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.setOpaque(false);
        FormInput.add(CmbMenit);
        CmbMenit.setBounds(540, 40, 55, 23);

        ambulance.setForeground(new java.awt.Color(0, 0, 0));
        ambulance.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "AGD", "SENDIRI", "SWASTA" }));
        ambulance.setName("ambulance"); // NOI18N
        ambulance.setOpaque(false);
        FormInput.add(ambulance);
        ambulance.setBounds(780, 40, 170, 23);

        ChkJln.setBackground(new java.awt.Color(235, 255, 235));
        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        FormInput.add(ChkJln);
        ChkJln.setBounds(657, 40, 23, 23);

        CmbDetik.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.setOpaque(false);
        FormInput.add(CmbDetik);
        CmbDetik.setBounds(600, 40, 55, 23);

        ktrujuk.setForeground(new java.awt.Color(0, 0, 0));
        ktrujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bedah", "Non Bedah", "Kebidanan", "Anak" }));
        ktrujuk.setName("ktrujuk"); // NOI18N
        ktrujuk.setOpaque(false);
        FormInput.add(ktrujuk);
        ktrujuk.setBounds(780, 10, 170, 23);

        kdfaskes.setEditable(false);
        kdfaskes.setForeground(new java.awt.Color(0, 0, 0));
        kdfaskes.setName("kdfaskes"); // NOI18N
        FormInput.add(kdfaskes);
        kdfaskes.setBounds(75, 70, 90, 23);

        btnFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnFaskes.setMnemonic('2');
        btnFaskes.setToolTipText("Alt+2");
        btnFaskes.setName("btnFaskes"); // NOI18N
        btnFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFaskesActionPerformed(evt);
            }
        });
        FormInput.add(btnFaskes);
        btnFaskes.setBounds(390, 70, 28, 23);

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        ket.setColumns(20);
        ket.setRows(5);
        ket.setName("ket"); // NOI18N
        Scroll21.setViewportView(ket);

        FormInput.add(Scroll21);
        Scroll21.setBounds(513, 70, 320, 53);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Poli Tujuan :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 100, 72, 23);

        TPTujuan.setForeground(new java.awt.Color(0, 0, 0));
        TPTujuan.setName("TPTujuan"); // NOI18N
        TPTujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPTujuanKeyPressed(evt);
            }
        });
        FormInput.add(TPTujuan);
        TPTujuan.setBounds(75, 100, 312, 23);

        kodeDok.setEditable(false);
        kodeDok.setForeground(new java.awt.Color(0, 0, 0));
        kodeDok.setName("kodeDok"); // NOI18N
        FormInput.add(kodeDok);
        kodeDok.setBounds(75, 130, 90, 23);

        nmDok.setEditable(false);
        nmDok.setForeground(new java.awt.Color(0, 0, 0));
        nmDok.setHighlighter(null);
        nmDok.setName("nmDok"); // NOI18N
        FormInput.add(nmDok);
        nmDok.setBounds(167, 130, 220, 23);

        diagnosanya.setForeground(new java.awt.Color(0, 0, 0));
        diagnosanya.setHighlighter(null);
        diagnosanya.setName("diagnosanya"); // NOI18N
        FormInput.add(diagnosanya);
        diagnosanya.setBounds(513, 130, 320, 23);

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

        TabRujuk.setBackground(new java.awt.Color(250, 255, 245));
        TabRujuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRujuk.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TabRujuk.setName("TabRujuk"); // NOI18N
        TabRujuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRujukMouseClicked(evt);
            }
        });

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbRujukanNon.setAutoCreateRowSorter(true);
        tbRujukanNon.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRujukanNon.setComponentPopupMenu(jPopupMenu1);
        tbRujukanNon.setName("tbRujukanNon"); // NOI18N
        tbRujukanNon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRujukanNonMouseClicked(evt);
            }
        });
        tbRujukanNon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRujukanNonKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRujukanNon);

        TabRujuk.addTab(".: Rujukan Keluar RS", Scroll);

        Scroll1.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbRujukanBPJS.setAutoCreateRowSorter(true);
        tbRujukanBPJS.setToolTipText("");
        tbRujukanBPJS.setComponentPopupMenu(jPopupMenu1);
        tbRujukanBPJS.setName("tbRujukanBPJS"); // NOI18N
        tbRujukanBPJS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRujukanBPJSMouseClicked(evt);
            }
        });
        tbRujukanBPJS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRujukanBPJSKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbRujukanBPJS);

        TabRujuk.addTab(".: Rujukan Keluar BPJS", Scroll1);

        internalFrame1.add(TabRujuk, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        Sequel.cariIsi("select no_rawat from rujuk where no_rawat=? ", cek_rujukan, TNoRw.getText());

        if (TNoRj.getText().trim().equals("")) {
            Valid.textKosong(TNoRj, "No.Rujuk");
        } else if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (TTmpRujuk.getText().trim().equals("") || (kdfaskes.getText().equals(""))) {
            Valid.textKosong(TTmpRujuk, "tempat rujuk");
            btnFaskes.requestFocus();
        } else if (TPTujuan.getText().trim().equals("")) {
            Valid.textKosong(TPTujuan, "nama poliklinik tujuan");
            TPTujuan.requestFocus();
        } else if (kodeDok.getText().trim().equals("")) {
            Valid.textKosong(kodeDok, "dokter yang bertugas");
            btnDokter.requestFocus();
        } else if (!cek_rujukan.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah dirujuk pada tgl. " + DTPRujuk.getSelectedItem() + " ...!!!");
            emptTeks();
        } else {
            Sequel.menyimpan("rujuk", "'" + TNoRj.getText() + "','"
                    + TNoRw.getText() + "','"
                    + TTmpRujuk.getText() + "','"
                    + Valid.SetTgl(DTPRujuk.getSelectedItem() + "") + "','"
                    + diagnosanya.getText() + "','"
                    + kodeDok.getText() + "','"
                    + ktrujuk.getSelectedItem() + "','"
                    + ambulance.getSelectedItem() + "','"
                    + ket.getText() + "','"
                    + CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem() + "','"
                    + kode_rujukanya + "','"
                    + TPTujuan.getText() + "'", "No.Rujuk");

            BtnCariActionPerformed(null);
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, ambulance, BtnBatal);
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
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        Valid.hapusTable(tabMode, TNoRj, "rujuk", "no_rujuk");
        BtnCariActionPerformed(null);
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRj.getText().trim().equals("")) {
            Valid.textKosong(TNoRj, "No.Rujuk");
        } else if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (TTmpRujuk.getText().trim().equals("") || (kdfaskes.getText().equals(""))) {
            Valid.textKosong(TTmpRujuk, "tempat rujuk");
            btnFaskes.requestFocus();
        } else if (TPTujuan.getText().trim().equals("")) {
            Valid.textKosong(TPTujuan, "nama poliklinik tujuan");
            TPTujuan.requestFocus();
        } else if (kodeDok.getText().trim().equals("")) {
            Valid.textKosong(kodeDok, "dokter yang bertugas");
            btnDokter.requestFocus();
        } else {
            Valid.editTable(tabMode, "rujuk", "no_rujuk", TNoRj, "no_rawat='" + TNoRw.getText()
                    + "',rujuk_ke='" + TTmpRujuk.getText()
                    + "',tgl_rujuk='" + Valid.SetTgl(DTPRujuk.getSelectedItem() + "")
                    + "',jam='" + CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem()
                    + "',keterangan_diagnosa='" + diagnosanya.getText()
                    + "',kd_dokter='" + kodeDok.getText()
                    + "',kat_rujuk='" + ktrujuk.getSelectedItem().toString()
                    + "',ambulance='" + ambulance.getSelectedItem().toString()
                    + "',keterangan='" + ket.getText() 
                    + "',kd_rujukan='" + kode_rujukanya
                    + "',poliklinik_tujuan='" + TPTujuan.getText() + "'");
            
            BtnCariActionPerformed(null);
            emptTeks();
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
        if (TabRujuk.getSelectedIndex() == 0) {
            tampilLain();
        } else if (TabRujuk.getSelectedIndex() == 1) {
            tampilBPJS();
        }        
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
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbRujukanNonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRujukanNonMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
                System.out.println("Notifikasi : " + e);
            }
        }
}//GEN-LAST:event_tbRujukanNonMouseClicked

    private void tbRujukanNonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRujukanNonKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
}//GEN-LAST:event_tbRujukanNonKeyPressed

private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
    dokter.isCek();
    dokter.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
    dokter.setLocationRelativeTo(internalFrame1);
    dokter.setVisible(true);
}//GEN-LAST:event_btnDokterActionPerformed

    private void MnSuratRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratRujukanActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            if (TabRujuk.getSelectedIndex() == 0) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("diagnosa", tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 7).toString());
                param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("SELECT DATE(NOW())")));
                param.put("tglReg", Valid.SetTglINDONESIA(Sequel.cariIsi("SELECT tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'")));
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptSuratRujukan.jasper", "report", "::[ Surat Rujukan Keluar RS ]::",
                        "SELECT CONCAT(r.rujuk_ke,' (Poli : ',IFNULL(r.poliklinik_tujuan,'-'),')') rujukKe,r.no_rujuk,rp.no_rawat, "
                        + "CONCAT(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',nm_kec,', ',nm_kab) alamat, d.nm_dokter, "
                        + "rp.no_rkm_medis, IF(p.jk='L','Laki-laki','Perempuan') jk,p.keluarga,p.namakeluarga, "
                        + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLhr,p.nm_pasien, "
                        + "CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') umur, r.keterangan "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                        + "INNER JOIN kelurahan kl ON p.kd_kel = kl.kd_kel INNER JOIN kecamatan kc ON p.kd_kec = kc.kd_kec "
                        + "INNER JOIN kabupaten kb ON p.kd_kab = kb.kd_kab INNER JOIN rujuk r ON rp.no_rawat = r.no_rawat "
                        + "INNER JOIN dokter d ON r.kd_dokter = d.kd_dokter WHERE rp.no_rawat='" + TNoRw.getText() + "' and r.no_rujuk='" + TNoRj.getText() + "'", param);

            } else if (TabRujuk.getSelectedIndex() == 1) {
                LocalDate tgldirujuk = LocalDate.parse(Valid.SetTgl(DTPRujuk.getSelectedItem() + ""));
                LocalDate habisBerlaku = tgldirujuk.plusDays(90);

                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("norujuk", TNoRj.getText());
                param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
                param.put("tglRujukan", Valid.SetTglINDONESIA(Valid.SetTgl(DTPRujuk.getSelectedItem() + "")));
                param.put("tglLahir", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'")));
                param.put("berlakuSampai", Valid.SetTglINDONESIA(habisBerlaku));
                Valid.MyReport("rptBridgingRujukanBPJS.jasper", "report", "::[ Surat Rujukan Keluar VClaim ]::",
                        " SELECT br.no_sep, bs.no_rawat, bs.nomr,bs.nama_pasien,br.tglRujukan, br.no_rujukan,br.ppkDirujuk, br.nm_ppkDirujuk, "
                        + "IF(br.jnsPelayanan='1','Rawat Inap','Rawat Jalan')  jenis, br.tipeRujukan,br.catatan, br.diagRujukan,br.nama_diagRujukan, "
                        + "br.poliRujukan,br.nama_poliRujukan,bs.no_kartu, IF(bs.jkel='L','Laki-Laki','Perempuan') jkel "
                        + "FROM bridging_sep bs LEFT JOIN bridging_rujukan_bpjs br ON br.no_rawat=bs.no_rawat "
                        + "WHERE br.no_rujukan='" + TNoRj.getText() + "'", param);
            }
        }
    }//GEN-LAST:event_MnSuratRujukanActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void DTPCari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari1MouseClicked
        DTPCari1.setEditable(false);
    }//GEN-LAST:event_DTPCari1MouseClicked

    private void DTPCari2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari2MouseClicked
        DTPCari2.setEditable(false);
    }//GEN-LAST:event_DTPCari2MouseClicked

    private void btnFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFaskesActionPerformed
        akses.setform("DlgRujuk");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, 573);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();
    }//GEN-LAST:event_btnFaskesActionPerformed

    private void cek_rujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cek_rujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cek_rujukanKeyPressed

    private void tbRujukanBPJSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRujukanBPJSMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataBPJS();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRujukanBPJSMouseClicked

    private void tbRujukanBPJSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRujukanBPJSKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataBPJS();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRujukanBPJSKeyPressed

    private void TabRujukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRujukMouseClicked
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_TabRujukMouseClicked

    private void TPTujuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPTujuanKeyPressed
        Valid.pindah(evt, btnFaskes, btnDokter);
    }//GEN-LAST:event_TPTujuanKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRujuk dialog = new DlgRujuk(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPRujuk;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnSuratRujukan;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll21;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRj;
    private widget.TextBox TNoRw;
    private widget.TextBox TPTujuan;
    private widget.TextBox TPasien;
    private widget.TextBox TTmpRujuk;
    private javax.swing.JTabbedPane TabRujuk;
    private widget.ComboBox ambulance;
    private widget.Button btnDokter;
    public widget.Button btnFaskes;
    private widget.TextBox cek_rujukan;
    private widget.TextBox diagnosanya;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdfaskes;
    private widget.TextArea ket;
    private widget.TextBox kodeDok;
    private widget.ComboBox ktrujuk;
    private widget.TextBox nmDok;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbRujukanBPJS;
    private widget.Table tbRujukanNon;
    // End of variables declaration//GEN-END:variables

    public void tampilLain() {
        Valid.tabelKosong(tabMode);
        tgl = "";
        sql = "";
        try {
            tgl = " r.tgl_rujuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            sql = "SELECT r.no_rujuk,r.no_rawat,rp.no_rkm_medis,p.nm_pasien, "
                    + "CONCAT(r.rujuk_ke,' (Poli : ',IFNULL(r.poliklinik_tujuan,'-'),')') rujuk_ke,DATE_FORMAT(r.tgl_rujuk,'%d-%m-%Y') tglrujuk, "
                    + "r.jam,r.keterangan_diagnosa,r.kd_dokter, d.nm_dokter,r.kat_rujuk,r.ambulance,r.keterangan, "
                    + "r.kd_rujukan, r.tgl_rujuk, ifnull(r.poliklinik_tujuan,'') poliklinik_tujuan FROM rujuk r "
                    + "INNER JOIN reg_periksa rp ON r.no_rawat = rp.no_rawat AND r.no_rujuk not like '1704R%' "
                    + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN dokter d ON r.kd_dokter = d.kd_dokter WHERE "
                    + tgl + "and r.no_rujuk like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and rp.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and p.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.rujuk_ke like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.tgl_rujuk like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.keterangan_diagnosa like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.kd_dokter like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.poliklinik_tujuan like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and d.nm_dokter like '%" + TCari.getText().trim() + "%' order by r.no_rujuk";
            ps = koneksi.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    String[] data = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16)
                    };
                    tabMode.addRow(data);
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgRujuk.tampilLain() : " + e);
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
        int b = tabMode.getRowCount();
        LCount.setText("" + b);
    }
    
    public void tampilBPJS() {
        Valid.tabelKosong(tabMode1);
        tgl = "";
        sql = "";
        try {
            tgl = " r.tgl_rujuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            sql = "SELECT r.no_rujuk,r.no_rawat,rp.no_rkm_medis,p.nm_pasien, "
                    + "CONCAT(r.rujuk_ke,' (Poli : ',IFNULL(r.poliklinik_tujuan,'-'),')') rujuk_ke,DATE_FORMAT(r.tgl_rujuk,'%d-%m-%Y') tglrujuk, "
                    + "r.jam,r.keterangan_diagnosa,r.kd_dokter, d.nm_dokter,r.kat_rujuk,r.ambulance,r.keterangan, "
                    + "r.kd_rujukan, r.tgl_rujuk, ifnull(r.poliklinik_tujuan,'') poliklinik_tujuan FROM rujuk r "
                    + "INNER JOIN reg_periksa rp ON r.no_rawat = rp.no_rawat AND r.no_rujuk like '1704R%' "
                    + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN dokter d ON r.kd_dokter = d.kd_dokter WHERE "
                    + tgl + "and r.no_rujuk like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and rp.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and p.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.rujuk_ke like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.tgl_rujuk like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.keterangan_diagnosa like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.kd_dokter like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and r.poliklinik_tujuan like '%" + TCari.getText().trim() + "%' or "
                    + tgl + "and d.nm_dokter like '%" + TCari.getText().trim() + "%' order by r.no_rujuk";
            ps1 = koneksi.prepareStatement(sql);
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    String[] data = {
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6),
                        rs1.getString(7),
                        rs1.getString(8),
                        rs1.getString(9),
                        rs1.getString(10),
                        rs1.getString(11),
                        rs1.getString(12),
                        rs1.getString(13),
                        rs1.getString(14),
                        rs1.getString(15),
                        rs1.getString(16)
                    };
                    tabMode1.addRow(data);
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgRujuk.tampilBPJS() : " + e);
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
        int b = tabMode1.getRowCount();
        LCount.setText("" + b);
    }

    public void emptTeks() {
        TNoRj.setText("");
        TTmpRujuk.setText("");
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        kodeDok.setText("");
        nmDok.setText("");
        diagnosanya.setText("");
        DTPRujuk.setDate(new Date());
        Valid.autoNomer("rujuk", "R", 9, TNoRj);
        TNoRj.requestFocus();
        ktrujuk.setSelectedIndex(0);
        ambulance.setSelectedIndex(0);
        ket.setText("");
        kdfaskes.setText("");
        kode_rujukanya = "";
        cek_rujukan.setText("");
        TPTujuan.setText("");
    }

    private void getData() {
        kode_rujukanya = "";
        if (tbRujukanNon.getSelectedRow() != -1) {
            TNoRj.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 0).toString());
            TNoRw.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 1).toString());
            TNoRM.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 2).toString());
            TPasien.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 3).toString());
            kode_rujukanya = tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 13).toString();
            kdfaskes.setText(Sequel.cariIsi("select kode_faskes_bpjs from master_nama_rujukan where kd_rujukan='" + kode_rujukanya + "'"));
            TTmpRujuk.setText(Sequel.cariIsi("SELECT IFNULL(nama_rujukan,'-') FROM master_nama_rujukan WHERE kode_faskes_bpjs='" + kdfaskes.getText() + "'"));
            TPTujuan.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 15).toString());
            Valid.SetTgl(DTPRujuk, tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 14).toString());
            CmbJam.setSelectedItem(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 6).toString().substring(0, 2));
            CmbMenit.setSelectedItem(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 6).toString().substring(3, 5));
            CmbDetik.setSelectedItem(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 6).toString().substring(6, 8));
            diagnosanya.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 7).toString());
            kodeDok.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 8).toString());
            nmDok.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 9).toString());
            ktrujuk.setSelectedItem(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 10).toString());
            ambulance.setSelectedItem(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 11).toString());
            ket.setText(tbRujukanNon.getValueAt(tbRujukanNon.getSelectedRow(), 12).toString());                                    
        }
    }
    
    private void getDataBPJS() {
        kode_rujukanya = "";
        if (tbRujukanBPJS.getSelectedRow() != -1) {
            TNoRj.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 0).toString());
            TNoRw.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 1).toString());
            TNoRM.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 2).toString());
            TPasien.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 3).toString());
            kode_rujukanya = tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 13).toString();
            kdfaskes.setText(Sequel.cariIsi("select kode_faskes_bpjs from master_nama_rujukan where kd_rujukan='" + kode_rujukanya + "'"));
            TTmpRujuk.setText(Sequel.cariIsi("SELECT IFNULL(nama_rujukan,'-') FROM master_nama_rujukan WHERE kode_faskes_bpjs='" + kdfaskes.getText() + "'"));
            TPTujuan.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 15).toString());
            Valid.SetTgl(DTPRujuk, tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 14).toString());
            CmbJam.setSelectedItem(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 6).toString().substring(0, 2));
            CmbMenit.setSelectedItem(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 6).toString().substring(3, 5));
            CmbDetik.setSelectedItem(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 6).toString().substring(6, 8));
            diagnosanya.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 7).toString());
            kodeDok.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 8).toString());
            nmDok.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 9).toString());
            ktrujuk.setSelectedItem(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 10).toString());
            ambulance.setSelectedItem(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 11).toString());
            ket.setText(tbRujukanBPJS.getValueAt(tbRujukanBPJS.getSelectedRow(), 12).toString());                                
        }
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw.getText() + "' ", TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", TPasien);
    }

    public void setNoRm(String norwt, Date tgl1, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        TabRujuk.setSelectedIndex(0);
        isRawat();
        isPsien();
        kodeDok.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + norwt + "'"));
        nmDok.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kodeDok.getText() + "'"));        
        diagnosanya.setText(Sequel.cariIsi("select ifnull(diagnosa,'') from pemeriksaan_ralan where no_rawat='" + norwt + "'"));
        ChkInput.setSelected(true);
        isForm();
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 186));
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
        BtnSimpan.setEnabled(akses.getrujukan_keluar());
        BtnHapus.setEnabled(akses.getrujukan_keluar());
        BtnEdit.setEnabled(akses.getrujukan_keluar());
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";

                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkJln.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJln.isSelected() == false) {
                    nilai_jam = CmbJam.getSelectedIndex();
                    nilai_menit = CmbMenit.getSelectedIndex();
                    nilai_detik = CmbDetik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
}
