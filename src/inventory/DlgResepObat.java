/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgResepObat.java
 *
 * Created on 31 Mei 10, 11:27:40
 */
package inventory;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgPemberianObat;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author perpustakaan
 */
public final class DlgResepObat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private final Properties prop = new Properties();
    private PreparedStatement ps, ps2, ps3;
    private ResultSet rs, rs2, rs3;
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date = new Date();
    private String now = dateFormat.format(date), status = "", penjab = "", nmPrinter1 = "", nmPrinter2 = "", 
            kodeobat = "", tglrsp = "", jamrsp = "";
    private double total = 0, jumlahtotal = 0;
    private int i = 0, conteng = 0;

    /**
     * Creates new form DlgResepObat
     *
     * @param parent
     * @param modal
     */
    public DlgResepObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        Object[] row = {"No.Resep", "Tgl.Resep", "Pasien", "Dokter Peresep"};
        tabMode1 = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbResep.setModel(tabMode1);
        tbResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(500);
            }
        }
        tbResep.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "#", "no_rawat", "kd_brng", "pasien", "Tgl. Resep", "Nama Obat", "Aturan 1", "Aturan 2", "Aturan 3",
            "Waktu 1", "Waktu 2", "Keterangan", "Masa Simpan", "tglresep", "jamresep"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbItemObat.setModel(tabMode2);
        tbItemObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbItemObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(130);
            } else if (i == 7) {
                column.setPreferredWidth(130);
            } else if (i == 8) {
                column.setPreferredWidth(130);
            } else if (i == 9) {
                column.setPreferredWidth(130);
            } else if (i == 10) {
                column.setPreferredWidth(130);
            } else if (i == 11) {
                column.setPreferredWidth(160);
            } else if (i == 12) {
                column.setPreferredWidth(75);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbItemObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        KdDokter.setDocument(new batasInput((byte) 20).getKata(KdDokter));
        NoResep.setDocument(new batasInput((byte) 10).getKata(NoResep));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
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
        ChkInput.setSelected(false);
        isForm();

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
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                }
                KdDokter.requestFocus();
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
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            nmPrinter1 = koneksiDB.NAMAPRINTER1();
            nmPrinter2 = koneksiDB.NAMAPRINTER2();
        } catch (Exception e) {
            System.out.println("NAMA PRINTER : "+e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        Popup1 = new javax.swing.JPopupMenu();
        ppRekapResep = new javax.swing.JMenuItem();
        ppBilResep = new javax.swing.JMenuItem();
        Popup2 = new javax.swing.JPopupMenu();
        MnSemuanya = new javax.swing.JMenuItem();
        MnHilangkan = new javax.swing.JMenuItem();
        MnAturanPakai = new javax.swing.JMenuItem();
        ppLabelObatMinum = new javax.swing.JMenuItem();
        ppLabelObatLuar = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        jLabel3 = new widget.Label();
        jLabel13 = new widget.Label();
        btnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        NoResep = new widget.TextBox();
        jLabel8 = new widget.Label();
        DTPBeri = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkRM = new widget.CekBox();
        TKamarInap = new widget.TextBox();
        ChkInput = new widget.CekBox();
        PanelInput1 = new javax.swing.JPanel();
        PanelRiwayatObat = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbResep = new widget.Table();
        Scroll45 = new widget.ScrollPane();
        tbItemObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel10 = new widget.Label();
        TTotalResep = new widget.TextBox();

        Popup1.setName("Popup1"); // NOI18N

        ppRekapResep.setBackground(new java.awt.Color(255, 255, 255));
        ppRekapResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRekapResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppRekapResep.setText("Cetak Rekap Resep Obat");
        ppRekapResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRekapResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRekapResep.setIconTextGap(8);
        ppRekapResep.setName("ppRekapResep"); // NOI18N
        ppRekapResep.setPreferredSize(new java.awt.Dimension(200, 25));
        ppRekapResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRekapResepActionPerformed(evt);
            }
        });
        Popup1.add(ppRekapResep);

        ppBilResep.setBackground(new java.awt.Color(255, 255, 255));
        ppBilResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBilResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppBilResep.setText("Cetak Bil. Pembayaran Resep");
        ppBilResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBilResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBilResep.setIconTextGap(8);
        ppBilResep.setName("ppBilResep"); // NOI18N
        ppBilResep.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBilResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBilResepActionPerformed(evt);
            }
        });
        Popup1.add(ppBilResep);

        Popup2.setName("Popup2"); // NOI18N

        MnSemuanya.setBackground(new java.awt.Color(255, 255, 255));
        MnSemuanya.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuanya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuanya.setText("Conteng Semua Item");
        MnSemuanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuanya.setIconTextGap(8);
        MnSemuanya.setName("MnSemuanya"); // NOI18N
        MnSemuanya.setPreferredSize(new java.awt.Dimension(200, 25));
        MnSemuanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuanyaActionPerformed(evt);
            }
        });
        Popup2.add(MnSemuanya);

        MnHilangkan.setBackground(new java.awt.Color(255, 255, 255));
        MnHilangkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHilangkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHilangkan.setText("Hilangkan Semua Conteng");
        MnHilangkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHilangkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHilangkan.setIconTextGap(8);
        MnHilangkan.setName("MnHilangkan"); // NOI18N
        MnHilangkan.setPreferredSize(new java.awt.Dimension(200, 25));
        MnHilangkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHilangkanActionPerformed(evt);
            }
        });
        Popup2.add(MnHilangkan);

        MnAturanPakai.setBackground(new java.awt.Color(255, 255, 255));
        MnAturanPakai.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAturanPakai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAturanPakai.setText("Aturan Pakai Obat");
        MnAturanPakai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAturanPakai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAturanPakai.setIconTextGap(8);
        MnAturanPakai.setName("MnAturanPakai"); // NOI18N
        MnAturanPakai.setPreferredSize(new java.awt.Dimension(200, 25));
        MnAturanPakai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAturanPakaiActionPerformed(evt);
            }
        });
        Popup2.add(MnAturanPakai);

        ppLabelObatMinum.setBackground(new java.awt.Color(255, 255, 255));
        ppLabelObatMinum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLabelObatMinum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppLabelObatMinum.setText("Cetak Aturan Pakai Obat Minum");
        ppLabelObatMinum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppLabelObatMinum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppLabelObatMinum.setIconTextGap(8);
        ppLabelObatMinum.setName("ppLabelObatMinum"); // NOI18N
        ppLabelObatMinum.setPreferredSize(new java.awt.Dimension(200, 25));
        ppLabelObatMinum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLabelObatMinumActionPerformed(evt);
            }
        });
        Popup2.add(ppLabelObatMinum);

        ppLabelObatLuar.setBackground(new java.awt.Color(255, 255, 255));
        ppLabelObatLuar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLabelObatLuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppLabelObatLuar.setText("Cetak Aturan Pakai Obat Luar");
        ppLabelObatLuar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppLabelObatLuar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppLabelObatLuar.setIconTextGap(8);
        ppLabelObatLuar.setName("ppLabelObatLuar"); // NOI18N
        ppLabelObatLuar.setPreferredSize(new java.awt.Dimension(200, 25));
        ppLabelObatLuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLabelObatLuarActionPerformed(evt);
            }
        });
        Popup2.add(ppLabelObatLuar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Resep Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(440, 107));
        FormInput.setLayout(null);

        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(98, 12, 120, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(220, 12, 503, 23);

        KdDokter.setForeground(new java.awt.Color(0, 0, 0));
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokterKeyPressed(evt);
            }
        });
        FormInput.add(KdDokter);
        KdDokter.setBounds(98, 72, 120, 23);

        NmDokter.setEditable(false);
        NmDokter.setForeground(new java.awt.Color(0, 0, 0));
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        FormInput.add(NmDokter);
        NmDokter.setBounds(220, 72, 471, 23);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 95, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dokter Peresep :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 72, 95, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('3');
        btnDokter.setToolTipText("Alt+3");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        btnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokterKeyPressed(evt);
            }
        });
        FormInput.add(btnDokter);
        btnDokter.setBounds(695, 72, 28, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("No.Resep :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(457, 42, 100, 23);

        NoResep.setForeground(new java.awt.Color(0, 0, 0));
        NoResep.setHighlighter(null);
        NoResep.setName("NoResep"); // NOI18N
        NoResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoResepKeyPressed(evt);
            }
        });
        FormInput.add(NoResep);
        NoResep.setBounds(560, 42, 138, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl.Resep :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 42, 95, 23);

        DTPBeri.setEditable(false);
        DTPBeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-11-2022" }));
        DTPBeri.setDisplayFormat("dd-MM-yyyy");
        DTPBeri.setName("DTPBeri"); // NOI18N
        DTPBeri.setOpaque(false);
        DTPBeri.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPBeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPBeriKeyPressed(evt);
            }
        });
        FormInput.add(DTPBeri);
        DTPBeri.setBounds(98, 42, 120, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setOpaque(false);
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(220, 42, 50, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setOpaque(false);
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(272, 42, 50, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setOpaque(false);
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(324, 42, 50, 23);

        ChkRM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 180, 170)));
        ChkRM.setForeground(new java.awt.Color(0, 0, 0));
        ChkRM.setSelected(true);
        ChkRM.setBorderPainted(true);
        ChkRM.setBorderPaintedFlat(true);
        ChkRM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRM.setName("ChkRM"); // NOI18N
        ChkRM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRMItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRM);
        ChkRM.setBounds(701, 42, 23, 23);

        TKamarInap.setForeground(new java.awt.Color(0, 0, 0));
        TKamarInap.setHighlighter(null);
        TKamarInap.setName("TKamarInap"); // NOI18N
        TKamarInap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKamarInapKeyPressed(evt);
            }
        });
        FormInput.add(TKamarInap);
        TKamarInap.setBounds(98, 12, 120, 23);

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

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(816, 220));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelRiwayatObat.setName("PanelRiwayatObat"); // NOI18N
        PanelRiwayatObat.setOpaque(false);
        PanelRiwayatObat.setPreferredSize(new java.awt.Dimension(816, 150));
        PanelRiwayatObat.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Tagihan Biaya Resep Obat ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbResep.setToolTipText("Silahkan klik untuk memilih data yang mau dihapus");
        tbResep.setComponentPopupMenu(Popup1);
        tbResep.setName("tbResep"); // NOI18N
        tbResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResepMouseClicked(evt);
            }
        });
        tbResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbResepKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbResep);

        PanelRiwayatObat.add(Scroll);

        Scroll45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Labeling Aturan Pakai Item Obat Yang Diresepkan ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll45.setName("Scroll45"); // NOI18N
        Scroll45.setOpaque(true);
        Scroll45.setPreferredSize(new java.awt.Dimension(390, 423));

        tbItemObat.setToolTipText("Silahkan klik untuk memilih data obatnya");
        tbItemObat.setComponentPopupMenu(Popup2);
        tbItemObat.setName("tbItemObat"); // NOI18N
        tbItemObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemObatMouseClicked(evt);
            }
        });
        tbItemObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemObatKeyPressed(evt);
            }
        });
        Scroll45.setViewportView(tbItemObat);

        PanelRiwayatObat.add(Scroll45);

        PanelInput1.add(PanelRiwayatObat, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput1, java.awt.BorderLayout.CENTER);

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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass8.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(52, 30));
        panelGlass8.add(LCount);

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
        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-11-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-11-2022" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('4');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+4");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(140, 23));
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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Total Resep :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 23));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, TTotalResep, org.jdesktop.beansbinding.ObjectProperty.create(), jLabel10, org.jdesktop.beansbinding.BeanProperty.create("labelFor"));
        bindingGroup.addBinding(binding);

        panelGlass9.add(jLabel10);

        TTotalResep.setEditable(false);
        TTotalResep.setForeground(new java.awt.Color(0, 0, 0));
        TTotalResep.setName("TTotalResep"); // NOI18N
        TTotalResep.setPreferredSize(new java.awt.Dimension(50, 23));
        TTotalResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTotalResepKeyPressed(evt);
            }
        });
        panelGlass9.add(TTotalResep);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            setAlwaysOnTop(false);
            Valid.textKosong(TNoRw, "pasien");
        } else if (KdDokter.getText().trim().equals("") || NmDokter.getText().trim().equals("")) {
            setAlwaysOnTop(false);
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NoResep.getText().trim().equals("")) {
            setAlwaysOnTop(false);
            Valid.textKosong(NoResep, "No.Resep");
        } else {
            if (ChkRM.isSelected() == true) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_resep,6),signed)),0) from resep_obat where tgl_perawatan like '%" + DTPBeri.getSelectedItem().toString().substring(6, 10) + "%' ", DTPBeri.getSelectedItem().toString().substring(6, 10), 6, NoResep);
            }
            Sequel.menyimpan("resep_obat", "?,?,?,?,?,?,?", "Nomer Resep", 7, new String[]{
                NoResep.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""),
                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                TNoRw.getText(), KdDokter.getText(), Valid.SetTgl(DTPBeri.getSelectedItem() + ""),
                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem()
            });
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, NmDokter, BtnBatal);
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
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada table untuk memilih...!!!!");
        } else if (!(TPasien.getText().trim().equals(""))) {
            Sequel.meghapus("resep_obat", "no_resep", NoResep.getText());
            tampil();
        }

        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setAlwaysOnTop(false);
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        BtnCariActionPerformed(evt);
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            TCari.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//            Sequel.queryu("delete from temporary");
//            Sequel.AutoComitFalse();
//            for(int i=0;i<tabMode.getRowCount();i++){  
//                Sequel.menyimpan("temporary","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",38,new String[]{
//                    "0",tabMode.getValueAt(i,0).toString(),tabMode.getValueAt(i,1).toString(),tabMode.getValueAt(i,2).toString(),
//                    tabMode.getValueAt(i,3).toString(),"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""
//                });
//            }
//            Sequel.AutoComitTrue();
//            Map<String, Object> param = new HashMap<>();  
//                param.put("namars",var.getnamars());
//                param.put("alamatrs",var.getalamatrs());
//                param.put("kotars",var.getkabupatenrs());
//                param.put("propinsirs",var.getpropinsirs());
//                param.put("kontakrs",var.getkontakrs());
//                param.put("emailrs",var.getemailrs());   
//                param.put("logo",Sequel.cariGambar("select logo from setting")); 
//            Valid.MyReport2("rptResep.jasper","report","::[ Transaksi Pembelian Barang ]::",
//                "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14 from temporary order by no asc",param);
//        }
//        this.setCursor(Cursor.getDefaultCursor());
        //dispose(); tidak dipakai
        JOptionPane.showMessageDialog(null, "Gunakan klik kanan pada tabel untuk mencetak rekap atau bil. pembayaran resep obat pasien...!!!!");
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, NmDokter);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResepMouseClicked
        if (tbResep.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbResepMouseClicked

    private void tbResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResepKeyPressed
        if (tbResep.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbResepKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, NoResep);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void DTPBeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPBeriKeyPressed
        Valid.pindah(evt, TNoRw, cmbJam);
    }//GEN-LAST:event_DTPBeriKeyPressed

    private void NoResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoResepKeyPressed
        Valid.pindah(evt, cmbDtk, KdDokter);
    }//GEN-LAST:event_NoResepKeyPressed

    private void btnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokterKeyPressed
        Valid.pindah(evt, KdDokter, BtnSimpan);
    }//GEN-LAST:event_btnDokterKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.isCek();
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.setAlwaysOnTop(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", NmDokter, KdDokter.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnDokterActionPerformed(null);
        } else {
            Valid.pindah(evt, NoResep, BtnSimpan);
        }
    }//GEN-LAST:event_KdDokterKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select concat(pasien.no_rkm_medis,' ',pasien.nm_pasien) from reg_periksa inner join pasien "
                    + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where no_rawat=? ", TPasien, TNoRw.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            TCari.requestFocus();
        } else {
            Valid.pindah(evt, KdDokter, DTPBeri);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPBeri, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void ChkRMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRMItemStateChanged
        if (ChkRM.isSelected() == true) {
            NoResep.setEditable(false);
            NoResep.setBackground(new Color(245, 250, 240));
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_resep,6),signed)),0) from resep_obat where tgl_perawatan like '%" + DTPBeri.getSelectedItem().toString().substring(6, 10) + "%' ", DTPBeri.getSelectedItem().toString().substring(6, 10), 6, NoResep);
        } else if (ChkRM.isSelected() == false) {
            NoResep.setEditable(true);
            NoResep.setBackground(new Color(250, 255, 245));
            NoResep.setText("");
        }
    }//GEN-LAST:event_ChkRMItemStateChanged

    private void ppRekapResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRekapResepActionPerformed
        this.setAlwaysOnTop(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode1.getRowCount() != 0) {
            Sequel.queryu("delete from temporary");
            Sequel.AutoComitFalse();
            for (int i = 0; i < tabMode1.getRowCount(); i++) {
                Sequel.menyimpan("temporary", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", 38, new String[]{
                    "0", tabMode1.getValueAt(i, 0).toString(), tabMode1.getValueAt(i, 1).toString(), tabMode1.getValueAt(i, 2).toString(),
                    tabMode1.getValueAt(i, 3).toString(), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                });
            }
            Sequel.AutoComitTrue();
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_surat", Sequel.cariIsi("select day(now())") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select year(now())"));
            Valid.MyReport("rptResep.jasper", "report", "::[ Transaksi Pembelian Barang ]::",
                    "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14 from temporary order by no asc", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppRekapResepActionPerformed

    private void ppBilResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBilResepActionPerformed
        this.setAlwaysOnTop(false);
        status = Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat = ?", TNoRw.getText());
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode1.getRowCount() != 0) {
            Sequel.queryu("delete from temporary");
            Sequel.AutoComitFalse();
            for (int i = 0; i < tabMode1.getRowCount(); i++) {
                Sequel.menyimpan("temporary", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", 38, new String[]{
                    "0", tabMode1.getValueAt(i, 0).toString(), tabMode1.getValueAt(i, 1).toString(), tabMode1.getValueAt(i, 2).toString(),
                    tabMode1.getValueAt(i, 3).toString(), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                });
            }
            Sequel.AutoComitTrue();
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));            
            Valid.MyReport("rptResepJalan.jasper", "report", "::[ Kwitansi Resep Obat ]::", " "
                    + "SELECT a.tgl_perawatan, a.jam, a.no_rawat, a.no_resep, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.nm_dokter, a.nama_brng, "
                    + "a.jml, a.hrg_jual, a.embalase, a.tuslah, a.total, b.total Total_Semua, c.nm_bangsal Asal_apotek, a.status_lanjut, a.Ruangan "
                    + "FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.jam, detail_pemberian_obat.no_rawat, resep_obat.no_resep, "
                    + "reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, dokter.nm_dokter, databarang.nama_brng, detail_pemberian_obat.jml, "
                    + "detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, "
                    + "mid( detail_pemberian_obat.jam, 1, 5 ) jam2, reg_periksa.status_lanjut, "
                    + "IFNULL(IF(reg_periksa.status_lanjut='RANAP', b2.nm_bangsal, poliklinik.nm_poli),'Belum Dapat Kamar') Ruangan "
                    + "FROM detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + "INNER JOIN penjab ON penjab.kd_pj = reg_periksa.kd_pj INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + "INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "AND detail_pemberian_obat.kode_brng = databarang.kode_brng INNER JOIN resep_obat ON resep_obat.no_rawat = reg_periksa.no_rawat "
                    + "AND resep_obat.tgl_perawatan = detail_pemberian_obat.tgl_perawatan AND resep_obat.jam = detail_pemberian_obat.jam "
                    + "INNER JOIN dokter ON dokter.kd_dokter = resep_obat.kd_dokter LEFT JOIN kamar_inap ON kamar_inap.no_rawat = reg_periksa.no_rawat "
                    + "AND kamar_inap.stts_pulang = '-' LEFT JOIN kamar ON kamar.kd_kamar = kamar_inap.kd_kamar "
                    + "LEFT JOIN bangsal b2 ON b2.kd_bangsal = kamar.kd_bangsal LEFT JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli WHERE "
                    + "resep_obat.no_resep = '" + NoResep.getText() + "') AS a LEFT JOIN (SELECT sum(d.total) total, "
                    + "r.no_resep FROM detail_pemberian_obat d INNER JOIN resep_obat r ON r.no_rawat = d.no_rawat "
                    + "AND d.tgl_perawatan = r.tgl_perawatan AND d.jam = r.jam WHERE r.no_resep = '" + NoResep.getText() + "') AS b ON a.no_resep = b.no_resep "
                    + "left join (select DISTINCT mid(r.jam, 1, 5 ) jam,b.nm_bangsal, r.no_rawat, r.tanggal from riwayat_obat_pasien r "
                    + "inner join bangsal b on b.kd_bangsal = r.kd_bangsal where r.no_rawat = '" + TNoRw.getText() + "') as c on c.no_rawat = a.no_rawat "
                    + "and c.jam = a.jam2 and a.tgl_perawatan = c.tanggal)", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppBilResepActionPerformed

    private void TKamarInapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKamarInapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKamarInapKeyPressed

    private void TTotalResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTotalResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTotalResepKeyPressed

    private void ppLabelObatLuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLabelObatLuarActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item obat yang diresepkan belum ada...!!!!");
            tbResep.requestFocus();
        } else {
            for (i = 0; i < tbItemObat.getRowCount(); i++) {
                if (tbItemObat.getValueAt(i, 0).toString().equals("true")) {
                    cetakLabelLuar();
                }
            }

            tampilObat();
            for (i = 0; i < tbItemObat.getRowCount(); i++) {
                if (tbItemObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                    tbItemObat.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_ppLabelObatLuarActionPerformed

    private void ppLabelObatMinumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLabelObatMinumActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item obat yang diresepkan belum ada...!!!!");
            tbResep.requestFocus();
        } else {
            for (i = 0; i < tbItemObat.getRowCount(); i++) {
                if (tbItemObat.getValueAt(i, 0).toString().equals("true")) {
                    cetakLabelMinum();
                }
            }

            tampilObat();
            for (i = 0; i < tbItemObat.getRowCount(); i++) {
                if (tbItemObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                    tbItemObat.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_ppLabelObatMinumActionPerformed

    private void MnSemuanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuanyaActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item obat yang diresepkan belum ada...!!!!");
            tbItemObat.requestFocus();
        } else {
            tampilObat();
            for (i = 0; i < tbItemObat.getRowCount(); i++) {
                if (tbItemObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                    tbItemObat.setValueAt(Boolean.TRUE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnSemuanyaActionPerformed

    private void MnHilangkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHilangkanActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item obat yang diresepkan belum ada...!!!!");
            tbItemObat.requestFocus();
        } else {
            tampilObat();
            for (i = 0; i < tbItemObat.getRowCount(); i++) {
                if (tbItemObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                    tbItemObat.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnHilangkanActionPerformed

    private void MnAturanPakaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAturanPakaiActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item obat yang diresepkan belum ada...!!!!");
            tbItemObat.requestFocus();
        } else {
            conteng = 0;
            for (i = 0; i < tbItemObat.getRowCount(); i++) {
                if (tbItemObat.getValueAt(i, 0).toString().equals("true")) {
                    conteng++;
                }
            }
            
            if (conteng == 0) {
                JOptionPane.showMessageDialog(null, "Utk. memperbaiki aturan pakai obat silahkan conteng salah satu obat yg. dipilih...!!!!");
                tampilObat();
            } else if (conteng > 1) {
                JOptionPane.showMessageDialog(null, "Utk. memperbaiki aturan pakai obat silahkan conteng salah satu obat yg. dipilih...!!!!");
                for (i = 0; i < tbItemObat.getRowCount(); i++) {
                    if (tbItemObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                        tbItemObat.setValueAt(Boolean.FALSE, i, 0);
                    }
                }
                tampilObat();
            } else if (conteng == 1) {
                DlgGantiAturanPakai ganti = new DlgGantiAturanPakai(null, false);
                ganti.setSize(644, 316);
                ganti.setLocationRelativeTo(internalFrame1);
                ganti.setData(TNoRw.getText(), kodeobat, tglrsp, jamrsp, "transaksi_rs");
                ganti.setVisible(true);

                tampilObat();
                for (i = 0; i < tbItemObat.getRowCount(); i++) {
                    if (tbItemObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                        tbItemObat.setValueAt(Boolean.FALSE, i, 0);
                    }
                }
            }
        }        
    }//GEN-LAST:event_MnAturanPakaiActionPerformed

    private void tbItemObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemObatKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataObat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemObatKeyPressed

    private void tbItemObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemObatMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemObatMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgResepObat dialog = new DlgResepObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkRM;
    private widget.Tanggal DTPBeri;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnAturanPakai;
    private javax.swing.JMenuItem MnHilangkan;
    private javax.swing.JMenuItem MnSemuanya;
    private widget.TextBox NmDokter;
    private widget.TextBox NoResep;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelRiwayatObat;
    private javax.swing.JPopupMenu Popup1;
    private javax.swing.JPopupMenu Popup2;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll45;
    private widget.TextBox TCari;
    private widget.TextBox TKamarInap;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TTotalResep;
    private widget.Button btnDokter;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel13;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBilResep;
    private javax.swing.JMenuItem ppLabelObatLuar;
    private javax.swing.JMenuItem ppLabelObatMinum;
    private javax.swing.JMenuItem ppRekapResep;
    private widget.Table tbItemObat;
    private widget.Table tbResep;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode1);
        try {
            penjab = Sequel.cariIsi("select b.png_jawab from reg_periksa p inner join penjab b on b.kd_pj = p.kd_pj where p.no_rawat = ?", TNoRw.getText());
            ps = koneksi.prepareStatement("select resep_obat.no_resep,resep_obat.tgl_perawatan,resep_obat.jam,"
                    + " resep_obat.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,resep_obat.kd_dokter,dokter.nm_dokter "
                    + " from resep_obat inner join reg_periksa inner join pasien inner join dokter on resep_obat.no_rawat=reg_periksa.no_rawat  "
                    + " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and resep_obat.kd_dokter=dokter.kd_dokter where "
                    + " resep_obat.tgl_perawatan between ? and ? and resep_obat.no_resep like ? or "
                    + " resep_obat.tgl_perawatan between ? and ? and resep_obat.no_rawat like ? or "
                    + " resep_obat.tgl_perawatan between ? and ? and pasien.no_rkm_medis like ? or "
                    + " resep_obat.tgl_perawatan between ? and ? and pasien.nm_pasien like ? or "
                    + " resep_obat.tgl_perawatan between ? and ? and dokter.nm_dokter like ? order by resep_obat.tgl_perawatan,resep_obat.jam");

            Sequel.cariIsiResep("select count(-1) total from resep_obat where resep_obat.tgl_perawatan between ? and ? and resep_obat.no_resep like ? or "
                    + "resep_obat.tgl_perawatan between ? and ? and resep_obat.no_rawat like ? ", TTotalResep, Valid.SetTgl(DTPCari1.getSelectedItem() + ""), 
                    Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "%" + TCari.getText().trim() + "%");
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
                rs = ps.executeQuery();

                jumlahtotal = 0;
                while (rs.next()) {
                    tabMode1.addRow(new String[]{
                        "", "",
                        penjab,
                        ""
                    });
                    tabMode1.addRow(new String[]{
                        rs.getString("no_resep"), rs.getString("tgl_perawatan") + " " + rs.getString("jam"),
                        rs.getString("no_rawat") + " " + rs.getString("no_rkm_medis"),
                        rs.getString("nm_dokter")
                    });
                    tabMode1.addRow(new String[]{
                        "", "",
                        rs.getString("nm_pasien"),
                        ""
                    });
                    tabMode1.addRow(new String[]{"", "Nama Obat", "Jumlah x Harga + Embalase + Tuslah = Total", "Aturan Pakai"});
                    ps2 = koneksi.prepareStatement("select databarang.kode_brng,databarang.nama_brng,detail_pemberian_obat.jml,"
                            + "detail_pemberian_obat.biaya_obat,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,detail_pemberian_obat.total from "
                            + "detail_pemberian_obat inner join databarang on detail_pemberian_obat.kode_brng=databarang.kode_brng "
                            + "where detail_pemberian_obat.tgl_perawatan=? and detail_pemberian_obat.jam=? and detail_pemberian_obat.no_rawat=? "
                            + "order by databarang.kode_brng");
                    try {
                        ps2.setString(1, rs.getString("tgl_perawatan"));
                        ps2.setString(2, rs.getString("jam"));
                        ps2.setString(3, rs.getString("no_rawat"));
                        rs2 = ps2.executeQuery();
                        total = 0;
                        while (rs2.next()) {
                            tabMode1.addRow(new String[]{
                                "", rs2.getString("nama_brng"), rs2.getString("jml") + "  x  " + Valid.SetAngka(rs2.getDouble("biaya_obat"))
                                + " + " + Valid.SetAngka(rs2.getDouble("embalase")) + " + " + Valid.SetAngka(rs2.getDouble("tuslah")) + " = " + Valid.SetAngka(rs2.getDouble("total")),
                                Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                + "from aturan_pakai where tgl_perawatan='" + rs.getString("tgl_perawatan") + "' and jam='" + rs.getString("jam") + "' "
                                + "and no_rawat='" + rs.getString("no_rawat") + "' and kode_brng='" + rs2.getString("kode_brng") + "'")
                            });
                            total = total + rs2.getDouble("total");
                            jumlahtotal = jumlahtotal + rs2.getDouble("total");
                        }
                        tabMode1.addRow(new String[]{"", "", "Total Biaya Resep = " + Valid.SetAngka(total), ""});
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                }
                rs.last();
                if (rs.getRow() > 0) {
                    tabMode1.addRow(new String[]{">>", "Jumlah Total Biaya Resep", Valid.SetAngka(jumlahtotal), ""});
                }
                LCount.setText("" + rs.getRow());

            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
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
        tampilObat();
    }

    public void emptTeks() {
        KdDokter.setText("");
        NmDokter.setText("");
        NoResep.setText("");
        DTPBeri.setDate(new Date());
        cmbJam.setSelectedItem(now.substring(11, 13));
        cmbMnt.setSelectedItem(now.substring(14, 16));
        cmbDtk.setSelectedItem(now.substring(17, 19));
        NoResep.requestFocus();
        if (ChkRM.isSelected() == true) {
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_resep,6),signed)),0) from resep_obat where "
                    + "tgl_perawatan like '%" + DTPBeri.getSelectedItem().toString().substring(6, 10) + "%' ",
                    DTPBeri.getSelectedItem().toString().substring(6, 10), 6, NoResep);
        }
    }

    private void getData() {
        if (tbResep.getSelectedRow() != -1) {
            NoResep.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 0).toString());
            Sequel.cariIsi("select no_rawat from resep_obat where no_resep=?", TNoRw, NoResep.getText());
            TPasien.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 2).toString().replaceAll(TNoRw.getText() + " ", ""));            
        }
    }
    
    private void getDataObat() {
        kodeobat = "";
        tglrsp = "";
        jamrsp = "";
        if (tbItemObat.getSelectedRow() != -1) {
            kodeobat = tbItemObat.getValueAt(tbItemObat.getSelectedRow(), 2).toString();
            tglrsp = tbItemObat.getValueAt(tbItemObat.getSelectedRow(), 13).toString();
            jamrsp = tbItemObat.getValueAt(tbItemObat.getSelectedRow(), 14).toString();
        }
    }
    
    public void setNoRm(String norwt, Date tgl1, Date tgl2, String jam, String menit, String detik) {
        TNoRw.setText(norwt);
        Sequel.cariIsi("select concat(pasien.no_rkm_medis,' ',pasien.nm_pasien) from reg_periksa inner join pasien "
                + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where no_rawat=? ", TPasien, TNoRw.getText());
        TCari.setText(norwt);
        DTPBeri.setDate(tgl1);
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        cmbJam.setSelectedItem(jam);
        cmbMnt.setSelectedItem(menit);
        cmbDtk.setSelectedItem(detik);
        ChkInput.setSelected(true);
        isForm();
    }

    public void setDokterRalan() {
        Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", KdDokter, TNoRw.getText());
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", NmDokter, KdDokter.getText());
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 128));
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
        BtnSimpan.setEnabled(akses.getresep_obat());
        BtnHapus.setEnabled(akses.getresep_obat());
        BtnPrint.setEnabled(akses.getresep_obat());
    }

    public void setStatus(String stat) {
        status = stat;
    }
    
    private void tampilObat() {
        Valid.tabelKosong(tabMode2);
        try {
            ps3 = koneksi.prepareStatement("select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
                    + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
                    + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
                    + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "ap.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and ap.no_rawat='" + TNoRw.getText() + "' order by tgl_perawatan,jam,urutan");
            try {                
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rs3.getString("no_rawat"),
                        rs3.getString("kode_brng"),
                        rs3.getString("pasien"),
                        rs3.getString("tgl"),
                        rs3.getString("nama_brng"),
                        rs3.getString("aturan1"),
                        rs3.getString("aturan2"),
                        rs3.getString("aturan3"),
                        rs3.getString("waktu1"),
                        rs3.getString("waktu2"),
                        rs3.getString("keterangan"),
                        rs3.getString("waktu_simpan"),
                        rs3.getString("tgl_perawatan"),
                        rs3.getString("jam")
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
    
    private void cetakLabelMinum() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("logo", Sequel.cariGambar("select logo_hitam_putih from setting"));
        param.put("jns_label", "");
//        Valid.MyReport("rptAturanPakai.jasper", "report", "::[ Labeling Obat Minum ]::",
//                "select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
//                + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
//                + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
//                + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
//                + "ap.no_rawat='" + tbItemObat.getValueAt(i, 1).toString() + "' and "
//                + "ap.kode_brng='" + tbItemObat.getValueAt(i, 2).toString() + "' and "
//                + "ap.tgl_perawatan='" + tbItemObat.getValueAt(i, 13).toString() + "' and "
//                + "ap.jam='" + tbItemObat.getValueAt(i, 14).toString() + "'", param);
        
        Valid.AutoPrintMulti("rptAturanPakai.jasper", "report", "::[ Labeling Obat Minum ]::",
                "select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
                + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
                + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
                + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                + "ap.no_rawat='" + tbItemObat.getValueAt(i, 1).toString() + "' and "
                + "ap.kode_brng='" + tbItemObat.getValueAt(i, 2).toString() + "' and "
                + "ap.tgl_perawatan='" + tbItemObat.getValueAt(i, 13).toString() + "' and "
                + "ap.jam='" + tbItemObat.getValueAt(i, 14).toString() + "'", param, nmPrinter1);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void cetakLabelLuar() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("logo", Sequel.cariGambar("select logo_hitam_putih from setting"));
        param.put("jns_label", "OBAT LUAR");
//        Valid.MyReport("rptAturanPakai.jasper", "report", "::[ Labeling Obat Luar ]::",
//                "select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
//                + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
//                + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
//                + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
//                + "ap.no_rawat='" + tbItemObat.getValueAt(i, 1).toString() + "' and "
//                + "ap.kode_brng='" + tbItemObat.getValueAt(i, 2).toString() + "' and "
//                + "ap.tgl_perawatan='" + tbItemObat.getValueAt(i, 13).toString() + "' and "
//                + "ap.jam='" + tbItemObat.getValueAt(i, 14).toString() + "'", param);
        
        Valid.AutoPrintMulti("rptAturanPakai.jasper", "report", "::[ Labeling Obat Luar ]::",
                "select ap.no_rawat, ap.kode_brng, concat(p.no_rkm_medis,' - ',p.nm_pasien) pasien, date_format(ap.tgl_perawatan,'%d/%m/%Y') tgl, "
                + "d.nama_brng, ap.aturan1, ap.aturan2, ap.aturan3, ap.waktu1, ap.waktu2, ap.keterangan, ap.waktu_simpan, ap.tgl_perawatan, "
                + "ap.jam from aturan_pakai ap inner join databarang d on d.kode_brng=ap.kode_brng "
                + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                + "ap.no_rawat='" + tbItemObat.getValueAt(i, 1).toString() + "' and "
                + "ap.kode_brng='" + tbItemObat.getValueAt(i, 2).toString() + "' and "
                + "ap.tgl_perawatan='" + tbItemObat.getValueAt(i, 13).toString() + "' and "
                + "ap.jam='" + tbItemObat.getValueAt(i, 14).toString() + "'", param, nmPrinter2);
        this.setCursor(Cursor.getDefaultCursor());
    }
}
