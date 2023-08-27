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
import rekammedis.MasterDataDinkes;

/**
 *
 * @author dosen
 */
public class DlgDinkesPersalinan extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4;
    private ResultSet rs, rs1, rs2, rs3, rs4;
    private int i = 0, x = 0, data = 0;
    private MasterDataDinkes dinkes = new MasterDataDinkes(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String datakasus = "", dialog_simpan = "", cJL = "", cJADV = "", cJACC = "", jamlap = "", jamad = "", jamac = "";

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgDinkesPersalinan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "norawat", "Tgl. MRS/No.RM", "Nama Pasien/HP/Nama Suami", "Umur Pasien/Umur Suami", "Cara Bayar", "Alamat/NIK",
            "Rujukan", "DPJP", "Jam MRS", "Jam Lapor", "Jam ADV", "Jam ACC", "Kasus Persalinan",
            "norm", "nmpasien", "tglreg", "notelp", "nik", "alamatnya", "rujukan", "ket_rujukan",
            "kd_dokter", "nm_dokter", "cekjamlap", "cekjamad", "cekjamac", "umurpasien"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbDinkes.setModel(tabMode);
        tbDinkes.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDinkes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 27; i++) {
            TableColumn column = tbDinkes.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(270);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(160);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(75);
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
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDinkes.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Kode", "Nama Kasus Persalinan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbKasus.setModel(tabMode1);
        tbKasus.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKasus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbKasus.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(50);
            } else if (i == 1) {
                column.setPreferredWidth(326);
            }
        }
        tbKasus.setDefaultRenderer(Object.class, new WarnaTable());

        dinkes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDinkesPersalinan")) {
                    if (dinkes.getTable().getSelectedRow() != -1) {
                        kdkasus.setText(dinkes.getTable().getValueAt(dinkes.getTable().getSelectedRow(), 0).toString());
                        nmkasus.setText(dinkes.getTable().getValueAt(dinkes.getTable().getSelectedRow(), 1).toString());
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

        dinkes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgDinkesPersalinan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        dinkes.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

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
                    kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
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

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        nohp.setDocument(new batasInput((byte) 13).getOnlyAngka(nohp));
        nik.setDocument(new batasInput((byte) 17).getOnlyAngka(nik));
        umursuami.setDocument(new batasInput((byte) 3).getOnlyAngka(umursuami));

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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WindowDataKasus = new javax.swing.JDialog();
        internalFrame14 = new widget.InternalFrame();
        panelGlass7 = new widget.panelisi();
        jLabel59 = new widget.Label();
        kdkasus = new widget.TextBox();
        nmkasus = new widget.TextBox();
        btnKasus = new widget.Button();
        panelGlass13 = new widget.panelisi();
        BtnSimpan2 = new widget.Button();
        BtnEdit1 = new widget.Button();
        BtnCloseIn3 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDinkes = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnExcel = new widget.Button();
        BtnAll = new widget.Button();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel63 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel64 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        cmbLimit = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        norawat = new widget.TextBox();
        norm = new widget.TextBox();
        namapasien = new widget.TextBox();
        jLabel9 = new widget.Label();
        tglmrs = new widget.TextBox();
        jLabel10 = new widget.Label();
        nohp = new widget.TextBox();
        jLabel11 = new widget.Label();
        umur = new widget.TextBox();
        jLabel12 = new widget.Label();
        crbayar = new widget.TextBox();
        jLabel13 = new widget.Label();
        nik = new widget.TextBox();
        jLabel14 = new widget.Label();
        alamat = new widget.TextBox();
        jLabel15 = new widget.Label();
        cmbrujukan = new widget.ComboBox();
        jLabel16 = new widget.Label();
        ketrujukan = new widget.TextBox();
        jLabel17 = new widget.Label();
        kddpjp = new widget.TextBox();
        jLabel18 = new widget.Label();
        jammrs = new widget.TextBox();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel22 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbKasus = new widget.Table();
        BtnPilihKasus = new widget.Button();
        BtnHapusKasus = new widget.Button();
        BtnPerbaikiKasus = new widget.Button();
        nmdpjp = new widget.TextBox();
        btnDokter = new widget.Button();
        Chkjamadv = new widget.CekBox();
        Chkjamlapor = new widget.CekBox();
        Chkjamacc = new widget.CekBox();
        jLabel21 = new widget.Label();
        jLabel19 = new widget.Label();
        nmsuami = new widget.TextBox();
        jLabel20 = new widget.Label();
        umursuami = new widget.TextBox();
        jLabel23 = new widget.Label();

        WindowDataKasus.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataKasus.setName("WindowDataKasus"); // NOI18N
        WindowDataKasus.setUndecorated(true);
        WindowDataKasus.setResizable(false);

        internalFrame14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Kasus Persalinan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame14.setLayout(new java.awt.BorderLayout());

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 45));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Kasus Persalinan : ");
        jLabel59.setName("jLabel59"); // NOI18N
        jLabel59.setPreferredSize(new java.awt.Dimension(110, 24));
        panelGlass7.add(jLabel59);

        kdkasus.setEditable(false);
        kdkasus.setForeground(new java.awt.Color(0, 0, 0));
        kdkasus.setName("kdkasus"); // NOI18N
        kdkasus.setPreferredSize(new java.awt.Dimension(60, 24));
        panelGlass7.add(kdkasus);

        nmkasus.setEditable(false);
        nmkasus.setForeground(new java.awt.Color(0, 0, 0));
        nmkasus.setName("nmkasus"); // NOI18N
        nmkasus.setPreferredSize(new java.awt.Dimension(300, 24));
        panelGlass7.add(nmkasus);

        btnKasus.setForeground(new java.awt.Color(0, 0, 0));
        btnKasus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKasus.setMnemonic('X');
        btnKasus.setToolTipText("Alt+X");
        btnKasus.setName("btnKasus"); // NOI18N
        btnKasus.setPreferredSize(new java.awt.Dimension(28, 26));
        btnKasus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKasusActionPerformed(evt);
            }
        });
        panelGlass7.add(btnKasus);

        internalFrame14.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 150));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 8));

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
        panelGlass13.add(BtnSimpan2);

        BtnEdit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnEdit1);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.setPreferredSize(new java.awt.Dimension(73, 30));
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCloseIn3);

        internalFrame14.add(panelGlass13, java.awt.BorderLayout.CENTER);

        WindowDataKasus.getContentPane().add(internalFrame14, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Persalinan Pasien (DINKES) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDinkes.setAutoCreateRowSorter(true);
        tbDinkes.setToolTipText("Silahkan klik untuk memilih data yang akan diperbaiki ataupun dihapus");
        tbDinkes.setName("tbDinkes"); // NOI18N
        tbDinkes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDinkesMouseClicked(evt);
            }
        });
        tbDinkes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDinkesKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDinkes);

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

        BtnExcel.setForeground(new java.awt.Color(0, 0, 0));
        BtnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        BtnExcel.setMnemonic('X');
        BtnExcel.setText("Export File Excel");
        BtnExcel.setToolTipText("Alt+X");
        BtnExcel.setName("BtnExcel"); // NOI18N
        BtnExcel.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcelActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnExcel);

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
        panelGlass8.add(BtnCari);

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

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Tanggal : ");
        jLabel63.setName("jLabel63"); // NOI18N
        jLabel63.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel63);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-11-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari1);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("s.d");
        jLabel64.setName("jLabel64"); // NOI18N
        panelGlass10.add(jLabel64);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-11-2022" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Limit Data :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        cmbLimit.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100", "200", "300", "400", "500", "1000", "Semua" }));
        cmbLimit.setName("cmbLimit"); // NOI18N
        cmbLimit.setOpaque(false);
        cmbLimit.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(cmbLimit);

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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(55, 23));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(280, 286));
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
        jLabel5.setText("Pasien : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 6, 100, 23);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setName("norawat"); // NOI18N
        FormInput.add(norawat);
        norawat.setBounds(101, 6, 130, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setName("norm"); // NOI18N
        FormInput.add(norm);
        norm.setBounds(234, 6, 70, 23);

        namapasien.setEditable(false);
        namapasien.setForeground(new java.awt.Color(0, 0, 0));
        namapasien.setHighlighter(null);
        namapasien.setName("namapasien"); // NOI18N
        FormInput.add(namapasien);
        namapasien.setBounds(306, 6, 330, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Tgl. MRS : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 34, 100, 23);

        tglmrs.setEditable(false);
        tglmrs.setForeground(new java.awt.Color(0, 0, 0));
        tglmrs.setHighlighter(null);
        tglmrs.setName("tglmrs"); // NOI18N
        FormInput.add(tglmrs);
        tglmrs.setBounds(101, 34, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. HP : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(336, 34, 50, 23);

        nohp.setForeground(new java.awt.Color(0, 0, 0));
        nohp.setHighlighter(null);
        nohp.setName("nohp"); // NOI18N
        FormInput.add(nohp);
        nohp.setBounds(386, 34, 138, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Umur : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(525, 34, 50, 23);

        umur.setEditable(false);
        umur.setForeground(new java.awt.Color(0, 0, 0));
        umur.setHighlighter(null);
        umur.setName("umur"); // NOI18N
        FormInput.add(umur);
        umur.setBounds(576, 34, 60, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Cara Bayar : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 62, 100, 23);

        crbayar.setEditable(false);
        crbayar.setForeground(new java.awt.Color(0, 0, 0));
        crbayar.setHighlighter(null);
        crbayar.setName("crbayar"); // NOI18N
        FormInput.add(crbayar);
        crbayar.setBounds(101, 62, 350, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("NIK : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(456, 62, 40, 23);

        nik.setForeground(new java.awt.Color(0, 0, 0));
        nik.setHighlighter(null);
        nik.setName("nik"); // NOI18N
        FormInput.add(nik);
        nik.setBounds(496, 62, 140, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Alamat : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 90, 100, 23);

        alamat.setEditable(false);
        alamat.setForeground(new java.awt.Color(0, 0, 0));
        alamat.setHighlighter(null);
        alamat.setName("alamat"); // NOI18N
        FormInput.add(alamat);
        alamat.setBounds(101, 90, 535, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Rujukan : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 118, 100, 23);

        cmbrujukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbrujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Desa", "PKM", "Bidan", "Spesialis OG", "RS Lain", "Faskes Lain" }));
        cmbrujukan.setMinimumSize(new java.awt.Dimension(150, 23));
        cmbrujukan.setName("cmbrujukan"); // NOI18N
        cmbrujukan.setPreferredSize(new java.awt.Dimension(150, 23));
        FormInput.add(cmbrujukan);
        cmbrujukan.setBounds(101, 118, 100, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Ket. Rujukan : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(201, 118, 80, 23);

        ketrujukan.setForeground(new java.awt.Color(0, 0, 0));
        ketrujukan.setHighlighter(null);
        ketrujukan.setName("ketrujukan"); // NOI18N
        FormInput.add(ketrujukan);
        ketrujukan.setBounds(284, 118, 351, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("DPJP : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 146, 100, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        FormInput.add(kddpjp);
        kddpjp.setBounds(101, 146, 100, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jam MRS : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(191, 34, 65, 23);

        jammrs.setEditable(false);
        jammrs.setForeground(new java.awt.Color(0, 0, 0));
        jammrs.setHighlighter(null);
        jammrs.setName("jammrs"); // NOI18N
        FormInput.add(jammrs);
        jammrs.setBounds(257, 34, 78, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setOpaque(false);
        cmbJam.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(101, 174, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setOpaque(false);
        cmbMnt.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(152, 174, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setOpaque(false);
        cmbDtk.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(203, 174, 45, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.setOpaque(false);
        cmbJam1.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam1KeyPressed(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(101, 202, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.setOpaque(false);
        cmbMnt1.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt1KeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(152, 202, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.setOpaque(false);
        cmbDtk1.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk1KeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(203, 202, 45, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.setOpaque(false);
        cmbJam2.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam2KeyPressed(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(101, 230, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.setOpaque(false);
        cmbMnt2.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt2KeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(152, 230, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.setOpaque(false);
        cmbDtk2.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk2KeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(203, 230, 45, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Kasus : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(640, 6, 50, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Data Kasus Persalinan (Dinkes) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbKasus.setToolTipText("Silahkan klik utk. memilih data yg. akan diperbaiki atau dihapus");
        tbKasus.setName("tbKasus"); // NOI18N
        tbKasus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKasusMouseClicked(evt);
            }
        });
        tbKasus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKasusKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbKasus);

        FormInput.add(Scroll1);
        Scroll1.setBounds(692, 6, 410, 210);

        BtnPilihKasus.setForeground(new java.awt.Color(0, 0, 0));
        BtnPilihKasus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPilihKasus.setMnemonic('P');
        BtnPilihKasus.setText("Pilihan Kasus");
        BtnPilihKasus.setToolTipText("Alt+P");
        BtnPilihKasus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPilihKasus.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPilihKasus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPilihKasus.setName("BtnPilihKasus"); // NOI18N
        BtnPilihKasus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPilihKasusActionPerformed(evt);
            }
        });
        FormInput.add(BtnPilihKasus);
        BtnPilihKasus.setBounds(692, 225, 115, 27);

        BtnHapusKasus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusKasus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusKasus.setMnemonic('H');
        BtnHapusKasus.setText("Hapus Kasus");
        BtnHapusKasus.setToolTipText("Alt+H");
        BtnHapusKasus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnHapusKasus.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnHapusKasus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHapusKasus.setName("BtnHapusKasus"); // NOI18N
        BtnHapusKasus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKasusActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusKasus);
        BtnHapusKasus.setBounds(814, 225, 115, 27);

        BtnPerbaikiKasus.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerbaikiKasus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnPerbaikiKasus.setMnemonic('G');
        BtnPerbaikiKasus.setText("Perbaiki Kasus");
        BtnPerbaikiKasus.setToolTipText("Alt+G");
        BtnPerbaikiKasus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPerbaikiKasus.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPerbaikiKasus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPerbaikiKasus.setName("BtnPerbaikiKasus"); // NOI18N
        BtnPerbaikiKasus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerbaikiKasusActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerbaikiKasus);
        BtnPerbaikiKasus.setBounds(937, 225, 115, 27);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setHighlighter(null);
        nmdpjp.setName("nmdpjp"); // NOI18N
        FormInput.add(nmdpjp);
        nmdpjp.setBounds(206, 146, 400, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('2');
        btnDokter.setToolTipText("Alt+2");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        FormInput.add(btnDokter);
        btnDokter.setBounds(607, 146, 28, 23);

        Chkjamadv.setBackground(new java.awt.Color(255, 255, 250));
        Chkjamadv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        Chkjamadv.setForeground(new java.awt.Color(0, 0, 0));
        Chkjamadv.setText("Jam ADV : ");
        Chkjamadv.setBorderPainted(true);
        Chkjamadv.setBorderPaintedFlat(true);
        Chkjamadv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Chkjamadv.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkjamadv.setName("Chkjamadv"); // NOI18N
        FormInput.add(Chkjamadv);
        Chkjamadv.setBounds(0, 202, 100, 23);

        Chkjamlapor.setBackground(new java.awt.Color(255, 255, 250));
        Chkjamlapor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        Chkjamlapor.setForeground(new java.awt.Color(0, 0, 0));
        Chkjamlapor.setText("Jam Lapor : ");
        Chkjamlapor.setBorderPainted(true);
        Chkjamlapor.setBorderPaintedFlat(true);
        Chkjamlapor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Chkjamlapor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkjamlapor.setName("Chkjamlapor"); // NOI18N
        FormInput.add(Chkjamlapor);
        Chkjamlapor.setBounds(0, 174, 100, 23);

        Chkjamacc.setBackground(new java.awt.Color(255, 255, 250));
        Chkjamacc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        Chkjamacc.setForeground(new java.awt.Color(0, 0, 0));
        Chkjamacc.setText("Jam ACC : ");
        Chkjamacc.setBorderPainted(true);
        Chkjamacc.setBorderPaintedFlat(true);
        Chkjamacc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Chkjamacc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkjamacc.setName("Chkjamacc"); // NOI18N
        FormInput.add(Chkjamacc);
        Chkjamacc.setBounds(0, 230, 100, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("( Silahkan diconteng dulu Jam Lapor / Jam ADV / Jam ACC jika datanya ada )");
        jLabel21.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(255, 230, 380, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Nama Suami : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(249, 174, 85, 23);

        nmsuami.setForeground(new java.awt.Color(0, 0, 0));
        nmsuami.setHighlighter(null);
        nmsuami.setName("nmsuami"); // NOI18N
        nmsuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmsuamiKeyPressed(evt);
            }
        });
        FormInput.add(nmsuami);
        nmsuami.setBounds(336, 174, 300, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Umur Suami : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(249, 202, 85, 23);

        umursuami.setForeground(new java.awt.Color(0, 0, 0));
        umursuami.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        umursuami.setHighlighter(null);
        umursuami.setName("umursuami"); // NOI18N
        umursuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                umursuamiKeyPressed(evt);
            }
        });
        FormInput.add(umursuami);
        umursuami.setBounds(336, 202, 50, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("tahun.");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(392, 202, 40, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data pasien yang dipilih...!!!!");
        } else if (kddpjp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Isilah DPJP nya dulu dengan benar...!!!!");
            btnDokter.requestFocus();
        } else if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item kasus persalinan masih kosong...!!!!");
            BtnPilihKasus.requestFocus();
        } else if (nmsuami.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, nama suami masih kosong...!!!!");
            nmsuami.requestFocus();
        } else if (umursuami.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, umur suami masih kosong...!!!!");
            umursuami.requestFocus();
        } else {
            cekjam();
            datakasus = "";
            for (i = 0; i < tbKasus.getRowCount(); i++) {
                if (datakasus.equals("")) {
                    datakasus = tbKasus.getValueAt(i, 1).toString();
                } else {
                    datakasus = datakasus + ", " + tbKasus.getValueAt(i, 1).toString();
                }
            }

            Sequel.AutoComitFalse();
            if (Sequel.menyimpantf3("persalinan_dinkes", "?,?,?,?,?,?,?,?,?,?", "Data Kasus Persalinan", 10, new String[]{
                norawat.getText(), cmbrujukan.getSelectedItem().toString(), ketrujukan.getText(), jamlap, jamad, jamac,
                datakasus, cJL, cJADV, cJACC
            }) == true) {
                for (i = 0; i < tbKasus.getRowCount(); i++) {
                    Sequel.menyimpan("detail_persalinan_dinkes", "'" + norawat.getText() + "',"
                            + "'" + tbKasus.getValueAt(i, 0).toString() + "'", "Detail Kasus Persalinan");
                }

                Sequel.simpanReplaceInto("dpjp_ranap", "'" + norawat.getText() + "','" + kddpjp.getText() + "'", "DPJP Rawat Inap");
                Sequel.mengedit("pasien", "no_rkm_medis='" + norm.getText() + "'",
                        "no_tlp='" + nohp.getText() + "', "
                        + "no_ktp='" + nik.getText() + "', "
                        + "keluarga='SUAMI', "
                        + "namakeluarga='" + nmsuami.getText() + "',"
                        + "umur_pj='" + umursuami.getText() + "'");
                tampil();
                emptteks();
            }
            Sequel.AutoComitTrue();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptteks();
        ChkInput.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptteks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik salah satu dulu nama pasien pada tabel...!!!!");
            tbDinkes.requestFocus();
        } else if (nmsuami.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, nama suami masih kosong...!!!!");
            nmsuami.requestFocus();
        } else if (umursuami.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, umur suami masih kosong...!!!!");
            umursuami.requestFocus();
        } else {
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            } else if (kddpjp.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Isilah DPJP nya dulu dengan benar...!!!!");
                btnDokter.requestFocus();
            } else if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Item kasus persalinan masih kosong...!!!!");
                BtnPilihKasus.requestFocus();
            } else {
                Sequel.queryu("delete from detail_persalinan_dinkes where no_rawat='" + norawat.getText() + "'");
                Sequel.queryu("delete from persalinan_dinkes where no_rawat='" + norawat.getText() + "'");

                BtnSimpanActionPerformed(null);
            }
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
        emptteks();
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
        emptteks();
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
        emptteks();
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbDinkesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDinkesMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbDinkesMouseClicked

    private void tbDinkesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDinkesKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDinkesKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pada tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data pasien yang dipilih...!!!!");
            tbDinkes.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from detail_persalinan_dinkes where no_rawat='" + norawat.getText() + "'");
                Sequel.queryu("delete from persalinan_dinkes where no_rawat='" + norawat.getText() + "'");

                emptteks();
                tampil();
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnGanti);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, ketrujukan, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, cmbJam1);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void cmbJam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam1KeyPressed
        Valid.pindah(evt, cmbDtk, cmbMnt1);
    }//GEN-LAST:event_cmbJam1KeyPressed

    private void cmbMnt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt1KeyPressed
        Valid.pindah(evt, cmbJam1, cmbDtk1);
    }//GEN-LAST:event_cmbMnt1KeyPressed

    private void cmbDtk1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk1KeyPressed
        Valid.pindah(evt, cmbMnt1, cmbJam2);
    }//GEN-LAST:event_cmbDtk1KeyPressed

    private void cmbJam2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam2KeyPressed
        Valid.pindah(evt, cmbDtk1, cmbMnt2);
    }//GEN-LAST:event_cmbJam2KeyPressed

    private void cmbMnt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt2KeyPressed
        Valid.pindah(evt, cmbJam2, cmbDtk2);
    }//GEN-LAST:event_cmbMnt2KeyPressed

    private void cmbDtk2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk2KeyPressed
        Valid.pindah(evt, cmbMnt2, BtnSimpan);
    }//GEN-LAST:event_cmbDtk2KeyPressed

    private void BtnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcelActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pada tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = "";
            insertDataKeTabelTemporary3();
            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT temp2 'No.', temp3 'Tanggal / CM', temp4 'Nama Pasien / HP', temp5 'Umur Pasien', temp6 'Nama Suami', "
                    + "temp7 'Umur Suami', temp8 'Pemb. Umum/BPJS', temp9 'Alamat / NIK', temp10 'Rujukan', temp11 'DPJP', temp12 'Jam Msk', "
                    +"temp13 'Jam Lapor', temp14 'Jam Adv', temp15 'Jam ACC', "
                    + "temp16 '1. Gravida', "
                    + "temp17 '2. Para', "
                    + "temp18 '3. Abortus', "
                    + "temp19 '4. Tunggal', "
                    + "temp20 '5. Gamely', "
                    + "temp21 '6. Hamil (mgg)', "
                    + "temp22 '7. Eklamsia', "
                    + "temp23 '8. Preeklamsia', "
                    + "temp24 '9. HT dlm khml', "
                    + "temp25 '10. Plasenta previa', "
                    + "temp26 '11. Plas ltk rndah', "
                    + "temp27 '12. Sol plasenta', "
                    + "temp28 '13. Rupture uteri', "
                    + "temp29 '14. TP mnmbung', "
                    + "temp30 '15. Retensio plst', "
                    + "temp31 '16. Atonia uteri', "
                    + "temp32 '17. Laserasi jln lhr', "
                    + "temp33 '18. Retensi ss plst', "
                    + "temp34 '19. Inversio uteri', "
                    + "temp35 '20. Oedema vulva', "
                    + "temp36 '21. Kala II lama', "
                    + "temp37 '22. Distosia bahu', "
                    + "temp38 '23. Partus tak maju', "
                    + "temp39 '24. Kala I memanjang', "
                    + "temp40 '25. KDP', "
                    + "temp41 '26. Oligohidramnion', "
                    + "temp42 '27. Riwayat SC', "
                    + "temp43 '28. Sungsang', "
                    + "temp44 '29. Kelainan Letak', "
                    + "temp45 '30. CPD', "
                    + "temp46 '31. PPI', "
                    + "temp47 '32. Postterm', "
                    + "temp48 '33. Fetal distress', "
                    + "temp49 '34. Bayi besar', "
                    + "temp50 '35. IUFD', "
                    + "temp51 '36. TBJ<2500gr', "
                    + "temp52 '37. Inersia Uteri', "
                    + "temp53 '38. Induksi', "
                    + "temp54 '39. Gagal Induksi', "
                    + "temp55 '40. ILO', "
                    + "temp56 '41. HB', "
                    + "temp57 '42. Infeksi/Sepsis', "
                    + "temp58 '43. TB', "
                    + "temp59 '44. Malaria', "
                    + "temp60 '45. Jantung', "
                    + "temp61 '46. DM', "
                    + "temp62 '47. Gangguan darah', "
                    + "temp63 '48. Asma', "
                    + "temp64 '49. KEK', "
                    + "temp65 '50. Febris', "
                    + "temp66 '51. Herpes', "
                    + "temp67 '52. Diare', "
                    + "temp68 '53. HIV (B20)', "
                    + "temp69 '54. Hepatitis B', "
                    + "temp70 '55. Covid 19', "
                    + "temp71 '56. Konf Cov-19', "
                    + "temp72 '57. Perawatan', "
                    + "temp73 '58. Partus Spt BK', "
                    + "temp74 '59. Pspt Sungsang', "
                    + "temp75 '60. VE', "
                    + "temp76 '61. Forceps', "
                    + "temp77 '62. SC', "
                    + "temp78 '63. Manual Plasenta', "
                    + "temp79 '64. Di tlng Bidan', "
                    + "temp80 '65. Di tlng Sp.OG', "
                    + "temp81 '66. Di tlng Nakes', "
                    + "temp82 '67. Kematian perdarahan', "
                    + "temp83 '68. Kematian PE/', "
                    + "temp84 '69. Kematian Sepsis', "
                    + "temp85 '70. Kematian DLL', "
                    + "temp86 '71. Kematian Hamil', "
                    + "temp87 '72. Kematian Persalinan', "
                    + "temp88 '73. Kematian Nifas', "
                    + "temp89 '74. Pasien Jatuh', "
                    + "temp90 '75. Asbid Lengkap', "
                    + "temp91 '76. Px perdarahan', "
                    + "temp92 '77. Px Tranfusi darah', "
                    + "temp93 '78. Reaksi Transfusi', "
                    + "temp94 '79. Px Pasang Infus', "
                    + "temp95 '80. Px Plebhitis', "
                    + "temp96 '81. Px pilang APS', "
                    + "temp97 '82. Px Pulang Sembuh', "
                    + "temp98 '83. di Rujuk ke RS', "
                    + "temp99 '84. Dalam Wilayah', "
                    + "temp100 '85. Luar Wilayah', "
                    + "temp101 '86. Keterangan' FROM temporary3", dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data kasus persalinan permintaan Dinkes Kab. Banjar berhasil diexport menjadi file excel,..!!!");
            tampil();
            emptteks();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnExcelActionPerformed

    private void tbKasusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKasusMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getItemKasus();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKasusMouseClicked

    private void tbKasusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKasusKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getItemKasus();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbKasusKeyPressed

    private void BtnPilihKasusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPilihKasusActionPerformed
        kdkasus.setText("");
        nmkasus.setText("");
        BtnSimpan2.setEnabled(true);
        BtnEdit1.setEnabled(false);

        WindowDataKasus.setSize(549, 115);
        WindowDataKasus.setLocationRelativeTo(internalFrame1);
        WindowDataKasus.setVisible(true);
        WindowDataKasus.requestFocus();
    }//GEN-LAST:event_BtnPilihKasusActionPerformed

    private void BtnHapusKasusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKasusActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Kasus persalinan yang dipilih/ditentukan belum ada...!!");
            BtnPilihKasus.requestFocus();
        } else if (tbKasus.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data kasus persalinannya pada tabel...!!!!");
            tbKasus.requestFocus();
        } else {
            tabMode1.removeRow(tbKasus.getSelectedRow());
            kdkasus.setText("");
            nmkasus.setText("");
            BtnHapusKasus.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusKasusActionPerformed

    private void BtnPerbaikiKasusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerbaikiKasusActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Kasus persalinan yang dipilih/ditentukan belum ada...!!");
            BtnPilihKasus.requestFocus();
        } else if (tbKasus.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data kasus persalinannya pada tabel...!!!!");
            tbKasus.requestFocus();
        } else {
            BtnSimpan2.setEnabled(false);
            BtnEdit1.setEnabled(true);

            WindowDataKasus.setSize(549, 115);
            WindowDataKasus.setLocationRelativeTo(internalFrame1);
            WindowDataKasus.setVisible(true);
            WindowDataKasus.requestFocus();
        }
    }//GEN-LAST:event_BtnPerbaikiKasusActionPerformed

    private void btnKasusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKasusActionPerformed
        akses.setform("DlgDinkesPersalinan");
        dinkes.setSize(739, internalFrame1.getHeight() - 40);
        dinkes.setLocationRelativeTo(internalFrame1);
        dinkes.isCek();
        dinkes.setVisible(true);
        dinkes.TCari.requestFocus();
    }//GEN-LAST:event_btnKasusActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (kdkasus.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data kasus persalinannya...!!!!");
        } else {
            data = 0;
            for (i = 0; i < tbKasus.getRowCount(); i++) {
                if (tbKasus.getValueAt(i, 0).toString().equals(kdkasus.getText())) {
                    data++;
                }
            }

            if (data == 0) {
                tabMode1.addRow(new Object[]{kdkasus.getText(), nmkasus.getText()});
                kdkasus.setText("");
                nmkasus.setText("");
                btnKasus.requestFocus();
            } else if (data >= 1) {
                JOptionPane.showMessageDialog(null, "Kasus persalinan tersebut sudah ada dipilih...!!!!");
                btnKasus.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if (kdkasus.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data kasus persalinannya...!!!!");
        } else {
            data = 0;
            for (i = 0; i < tbKasus.getRowCount(); i++) {
                if (tbKasus.getValueAt(i, 0).toString().equals(kdkasus.getText())) {
                    data++;
                }
            }

            if (data == 0) {
                tabMode1.removeRow(tbKasus.getSelectedRow());
                tabMode1.addRow(new Object[]{kdkasus.getText(), nmkasus.getText()});
                BtnCloseIn3ActionPerformed(null);
            } else if (data >= 1) {
                JOptionPane.showMessageDialog(null, "Kasus persalinan tersebut sudah ada dipilih...!!!!");
                btnKasus.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        kdkasus.setText("");
        nmkasus.setText("");
        WindowDataKasus.dispose();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        akses.setform("DlgDinkesPersalinan");
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void nmsuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmsuamiKeyPressed
        Valid.pindah(evt, btnDokter, umursuami);
    }//GEN-LAST:event_nmsuamiKeyPressed

    private void umursuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_umursuamiKeyPressed
        Valid.pindah(evt, nmsuami, BtnSimpan);
    }//GEN-LAST:event_umursuamiKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDinkesPersalinan dialog = new DlgDinkesPersalinan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn3;
    private widget.Button BtnEdit1;
    private widget.Button BtnExcel;
    public widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusKasus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerbaikiKasus;
    private widget.Button BtnPilihKasus;
    public widget.Button BtnSimpan;
    private widget.Button BtnSimpan2;
    public widget.CekBox ChkInput;
    private widget.CekBox Chkjamacc;
    private widget.CekBox Chkjamadv;
    private widget.CekBox Chkjamlapor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    private javax.swing.JDialog WindowDataKasus;
    public widget.TextBox alamat;
    private widget.Button btnDokter;
    private widget.Button btnKasus;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbLimit;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbrujukan;
    public widget.TextBox crbayar;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame14;
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
    private widget.Label jLabel5;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    public widget.TextBox jammrs;
    public widget.TextBox kddpjp;
    private widget.TextBox kdkasus;
    public widget.TextBox ketrujukan;
    private widget.TextBox namapasien;
    public widget.TextBox nik;
    public widget.TextBox nmdpjp;
    private widget.TextBox nmkasus;
    public widget.TextBox nmsuami;
    public widget.TextBox nohp;
    public widget.TextBox norawat;
    private widget.TextBox norm;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.Table tbDinkes;
    private widget.Table tbKasus;
    public widget.TextBox tglmrs;
    public widget.TextBox umur;
    public widget.TextBox umursuami;
    // End of variables declaration//GEN-END:variables

    private void getData() {
        cJL = "";
        cJADV = "";
        cJACC = "";
        if (tbDinkes.getSelectedRow() != -1) {
            norawat.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 0).toString());
            norm.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 13).toString());
            namapasien.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 14).toString());
            tglmrs.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 15).toString());
            jammrs.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 8).toString());
            nohp.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 16).toString());
            umur.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 26).toString());
            crbayar.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 4).toString());
            nik.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 17).toString());
            alamat.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 18).toString());
            cmbrujukan.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 19).toString());
            ketrujukan.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 20).toString());
            kddpjp.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 21).toString());
            nmdpjp.setText(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 22).toString());            
            cJL = tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 23).toString();
            cJADV = tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 24).toString();
            cJACC = tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 25).toString();
            nmsuami.setText(Sequel.cariIsi("select namakeluarga from pasien where no_rkm_medis='" + norm.getText() + "'"));
            umursuami.setText(Sequel.cariIsi("select umur_pj from pasien where no_rkm_medis='" + norm.getText() + "'"));
            
            if (cJL.equals("1")) {
                Chkjamlapor.setSelected(true);
                cmbJam.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 9).toString().substring(0, 2));
                cmbMnt.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 9).toString().substring(3, 5));
                cmbDtk.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 9).toString().substring(6, 8));
            } else {
                Chkjamlapor.setSelected(false);
                cmbJam.setSelectedIndex(0);
                cmbMnt.setSelectedIndex(0);
                cmbDtk.setSelectedIndex(0);
            }

            if (cJADV.equals("1")) {
                Chkjamadv.setSelected(true);
                cmbJam1.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 10).toString().substring(0, 2));
                cmbMnt1.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 10).toString().substring(3, 5));
                cmbDtk1.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 10).toString().substring(6, 8));
            } else {
                Chkjamadv.setSelected(false);
                cmbJam1.setSelectedIndex(0);
                cmbMnt1.setSelectedIndex(0);
                cmbDtk1.setSelectedIndex(0);
            }
          
            if (cJACC.equals("1")) {
                Chkjamacc.setSelected(true);
                cmbJam2.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 11).toString().substring(0, 2));
                cmbMnt2.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 11).toString().substring(3, 5));
                cmbDtk2.setSelectedItem(tbDinkes.getValueAt(tbDinkes.getSelectedRow(), 11).toString().substring(6, 8));
            } else {
                Chkjamacc.setSelected(false);
                cmbJam2.setSelectedIndex(0);
                cmbMnt2.setSelectedIndex(0);
                cmbDtk2.setSelectedIndex(0);
            }
            tampilKasus();
        }
    }

    public void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 286));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void emptteks() {
        norawat.setText("");
        norm.setText("");
        namapasien.setText("");
        tglmrs.setText("");
        jammrs.setText("");
        nohp.setText("");
        umur.setText("");
        crbayar.setText("");
        nik.setText("");
        alamat.setText("");
        cmbrujukan.setSelectedIndex(0);
        ketrujukan.setText("");
        kddpjp.setText("");
        nmdpjp.setText("");
        nmsuami.setText("");
        umursuami.setText("");

        Chkjamlapor.setSelected(false);
        Chkjamadv.setSelected(false);
        Chkjamacc.setSelected(false);
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        Valid.tabelKosong(tabMode1);
    }

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (cmbLimit.getSelectedIndex() == 7) {
                ps = koneksi.prepareStatement("select pd.no_rawat, concat(date_format(rp.tgl_registrasi,'%d-%m-%Y'),' / ',p.no_rkm_medis) tglrm, "
                        + "concat(p.nm_pasien,' / ',p.no_tlp,' / ',if(p.keluarga='SUAMI',ifnull(p.namakeluarga,''),'')) nama, "
                        + "concat(rp.umurdaftar,' ',rp.sttsumur,' / ',if(p.keluarga='SUAMI',ifnull(p.umur_pj,'-'),''),' Th') umur, pj.png_jawab, "
                        + "concat(concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab),' / ',p.no_ktp) alamat, "
                        + "concat(pd.rujukan,' - ',pd.ket_rujukan) rujukan, ifnull(d.nm_dokter,'') dpjp, rp.jam_reg, if(cek_jam_lapor='1',pd.jam_lapor,'') jamlap, "
                        + "if(cek_jam_adv='1',pd.jam_adv,'') jamad, if(cek_jam_acc='1',pd.jam_acc,'') jamac, pd.kasus_persalinan, "
                        + "p.no_rkm_medis, p.nm_pasien, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglreg, p.no_tlp, p.no_ktp, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamatnya, "
                        + "pd.rujukan rujuknya, pd.ket_rujukan, d.kd_dokter, d.nm_dokter, pd.cek_jam_lapor, pd.cek_jam_adv, pd.cek_jam_acc, "
                        + "concat(rp.umurdaftar,' ',rp.sttsumur) umurpasien FROM persalinan_dinkes pd "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = pd.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel "
                        + "INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab "
                        + "INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj LEFT JOIN dpjp_ranap dr ON dr.no_rawat = pd.no_rawat LEFT JOIN dokter d ON d.kd_dokter = dr.kd_dokter WHERE "
                        + "rp.tgl_registrasi between ? and ? and pd.no_rawat like ? or "
                        + "rp.tgl_registrasi between ? and ? and p.no_rkm_medis like ? or "
                        + "rp.tgl_registrasi between ? and ? and p.nm_pasien like ? or "
                        + "rp.tgl_registrasi between ? and ? and p.no_tlp like ? or "
                        + "rp.tgl_registrasi between ? and ? and pj.png_jawab like ? or "
                        + "rp.tgl_registrasi between ? and ? and concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) like ? or "
                        + "rp.tgl_registrasi between ? and ? and p.no_ktp like ? or "
                        + "rp.tgl_registrasi between ? and ? and concat(pd.rujukan,' - ',pd.ket_rujukan) like ? or "
                        + "rp.tgl_registrasi between ? and ? and d.nm_dokter like ? or "
                        + "rp.tgl_registrasi between ? and ? and pd.kasus_persalinan like ? ORDER BY pd.no_rawat DESC");
            } else {
                ps = koneksi.prepareStatement("select pd.no_rawat, concat(date_format(rp.tgl_registrasi,'%d-%m-%Y'),' / ',p.no_rkm_medis) tglrm, "
                        + "concat(p.nm_pasien,' / ',p.no_tlp,' / ',if(p.keluarga='SUAMI',ifnull(p.namakeluarga,''),'')) nama, "
                        + "concat(rp.umurdaftar,' ',rp.sttsumur,' / ',if(p.keluarga='SUAMI',ifnull(p.umur_pj,'-'),''),' Th') umur, pj.png_jawab, "
                        + "concat(concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab),' / ',p.no_ktp) alamat, "
                        + "concat(pd.rujukan,' - ',pd.ket_rujukan) rujukan, ifnull(d.nm_dokter,'') dpjp, rp.jam_reg, if(cek_jam_lapor='1',pd.jam_lapor,'') jamlap, "
                        + "if(cek_jam_adv='1',pd.jam_adv,'') jamad, if(cek_jam_acc='1',pd.jam_acc,'') jamac, pd.kasus_persalinan, "
                        + "p.no_rkm_medis, p.nm_pasien, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglreg, p.no_tlp, p.no_ktp, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamatnya, "
                        + "pd.rujukan rujuknya, pd.ket_rujukan, d.kd_dokter, d.nm_dokter, pd.cek_jam_lapor, pd.cek_jam_adv, pd.cek_jam_acc, "
                        + "concat(rp.umurdaftar,' ',rp.sttsumur) umurpasien FROM persalinan_dinkes pd "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = pd.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel "
                        + "INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab "
                        + "INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj LEFT JOIN dpjp_ranap dr ON dr.no_rawat = pd.no_rawat LEFT JOIN dokter d ON d.kd_dokter = dr.kd_dokter WHERE "
                        + "rp.tgl_registrasi between ? and ? and pd.no_rawat like ? or "
                        + "rp.tgl_registrasi between ? and ? and p.no_rkm_medis like ? or "
                        + "rp.tgl_registrasi between ? and ? and p.nm_pasien like ? or "
                        + "rp.tgl_registrasi between ? and ? and p.no_tlp like ? or "
                        + "rp.tgl_registrasi between ? and ? and pj.png_jawab like ? or "
                        + "rp.tgl_registrasi between ? and ? and concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) like ? or "
                        + "rp.tgl_registrasi between ? and ? and p.no_ktp like ? or "
                        + "rp.tgl_registrasi between ? and ? and concat(pd.rujukan,' - ',pd.ket_rujukan) like ? or "
                        + "rp.tgl_registrasi between ? and ? and d.nm_dokter like ? or "
                        + "rp.tgl_registrasi between ? and ? and pd.kasus_persalinan like ? ORDER BY pd.no_rawat DESC limit " + cmbLimit.getSelectedItem().toString() + "");
            }

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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("tglrm"),
                        rs.getString("nama"),
                        rs.getString("umur"),
                        rs.getString("png_jawab"),
                        rs.getString("alamat"),
                        rs.getString("rujukan"),
                        rs.getString("dpjp"),
                        rs.getString("jam_reg"),
                        rs.getString("jamlap"),
                        rs.getString("jamad"),
                        rs.getString("jamac"),
                        rs.getString("kasus_persalinan"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglreg"),
                        rs.getString("no_tlp"),
                        rs.getString("no_ktp"),
                        rs.getString("alamatnya"),
                        rs.getString("rujuknya"),
                        rs.getString("ket_rujukan"),
                        rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"),                        
                        rs.getString("cek_jam_lapor"),
                        rs.getString("cek_jam_adv"),
                        rs.getString("cek_jam_acc"),
                        rs.getString("umurpasien")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgDinkesPersalinan.tampil() : " + e);
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

    public void isCek() {
        BtnSimpan.setEnabled(akses.getdata_persalinan());
        BtnGanti.setEnabled(akses.getdata_persalinan());
        BtnHapus.setEnabled(akses.getdata_persalinan());
        BtnExcel.setEnabled(akses.getdata_persalinan());
    }

    public void setPasien(String norw, Date tglawal) {
        norawat.setText(norw);
        isPasien();
        DTPCari1.setDate(tglawal);
        DTPCari2.setDate(new Date());
    }

    private void getItemKasus() {
        if (tbKasus.getSelectedRow() != -1) {
            kdkasus.setText(tbKasus.getValueAt(tbKasus.getSelectedRow(), 0).toString());
            nmkasus.setText(tbKasus.getValueAt(tbKasus.getSelectedRow(), 1).toString());
        }
    }

    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglreg, rp.jam_reg, "
                    + "p.no_tlp, concat(rp.umurdaftar,' ',rp.sttsumur) umur, pj.png_jawab, p.no_ktp, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "if(p.keluarga='SUAMI',p.namakeluarga,'') nmsuami, if(p.keluarga='SUAMI',ifnull(p.umur_pj,''),'') umursuami "
                    + "from reg_periksa rp inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb ON kb.kd_kab=p.kd_kab where rp.no_rawat='" + norawat.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    norm.setText(rs1.getString("no_rkm_medis"));
                    namapasien.setText(rs1.getString("nm_pasien"));
                    tglmrs.setText(rs1.getString("tglreg"));
                    jammrs.setText(rs1.getString("jam_reg"));
                    nohp.setText(rs1.getString("no_tlp"));
                    umur.setText(rs1.getString("umur"));
                    crbayar.setText(rs1.getString("png_jawab"));
                    nik.setText(rs1.getString("no_ktp"));
                    alamat.setText(rs1.getString("alamat"));
                    kddpjp.setText(Sequel.cariIsi("select ifnull(kd_dokter,'') from dpjp_ranap where no_rawat='" + norawat.getText() + "'"));
                    nmdpjp.setText(Sequel.cariIsi("select ifnull(nm_dokter,'') from dokter where kd_dokter='" + kddpjp.getText() + "'"));
                    nmsuami.setText(rs1.getString("nmsuami"));
                    umursuami.setText(rs1.getString("umursuami"));
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

    private void tampilKasus() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select m.kode, m.nama_kasus from detail_persalinan_dinkes d "
                    + "inner join master_kasus_persalinan_dinkes m on m.kode=d.kode where d.no_rawat='" + norawat.getText() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new Object[]{
                        rs2.getString("kode"),
                        rs2.getString("nama_kasus")
                    });
                }
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void insertDataKeTabelTemporary3() {
        try {
            Sequel.queryu("delete from temporary3");
            ps3 = koneksi.prepareStatement("select pd.no_rawat, concat(date_format(rp.tgl_registrasi,'%d-%m-%Y'),' / ',p.no_rkm_medis) tgl_rm, "
                    + "concat(p.nm_pasien,' / ',p.no_tlp) nama, concat(rp.umurdaftar,' ',rp.sttsumur,'.') umur, "
                    + "pj.png_jawab, concat(concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab),' / ',p.no_ktp) alamat, "
                    + "concat(pd.rujukan,' ',pd.ket_rujukan) rujukan, ifnull(d.nm_dokter,'') dpjp, rp.jam_reg, if(cek_jam_lapor='1',pd.jam_lapor,'') jamlap, "
                    + "if(cek_jam_adv='1',pd.jam_adv,'') jamad, if(cek_jam_acc='1',pd.jam_acc,'') jamac, if(p.keluarga='SUAMI',p.namakeluarga,'') nmsuami, "
                    + "if(p.keluarga='SUAMI',concat(ifnull(p.umur_pj,'-'),' ','Th.'),'') umursuami FROM persalinan_dinkes pd "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pd.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj "
                    + "LEFT JOIN dpjp_ranap dr ON dr.no_rawat = pd.no_rawat LEFT JOIN dokter d ON d.kd_dokter = dr.kd_dokter WHERE "
                    + "rp.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ORDER BY pd.no_rawat");
            try {
                rs3 = ps3.executeQuery();
                x = 1;
                while (rs3.next()) {
                    Sequel.AutoComitFalse();
                    if (Sequel.menyimpantf2("temporary3", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                            + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                            + "?,?,?,?,?,?,?,?,?,?,?,?,?", "Data Pasien", 101, new String[]{
                                rs3.getString("no_rawat"),
                                x + "",
                                rs3.getString("tgl_rm"),
                                rs3.getString("nama"),
                                rs3.getString("umur"),
                                rs3.getString("nmsuami"),
                                rs3.getString("umursuami"),
                                rs3.getString("png_jawab"),
                                rs3.getString("alamat"),
                                rs3.getString("rujukan"),
                                rs3.getString("dpjp"),
                                rs3.getString("jam_reg"),
                                rs3.getString("jamlap"),
                                rs3.getString("jamad"),
                                rs3.getString("jamac"),
                                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", "", ""
                            }) == true) {
                        //ngecek kasus persalinan
                        ps4 = koneksi.prepareStatement("select ifnull(d.kode,'') kode from detail_persalinan_dinkes d "
                                + "inner join master_kasus_persalinan_dinkes m on m.kode=d.kode where d.no_rawat='" + rs3.getString("no_rawat") + "'");
                        try {
                            rs4 = ps4.executeQuery();
                            while (rs4.next()) {
                                if (rs4.getString("kode").equals("001")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp16='V'");
                                } else if (rs4.getString("kode").equals("002")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp17='V'");
                                } else if (rs4.getString("kode").equals("003")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp18='V'");
                                } else if (rs4.getString("kode").equals("004")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp19='V'");
                                } else if (rs4.getString("kode").equals("005")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp20='V'");
                                } else if (rs4.getString("kode").equals("006")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp21='V'");
                                } else if (rs4.getString("kode").equals("007")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp22='V'");
                                } else if (rs4.getString("kode").equals("008")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp23='V'");
                                } else if (rs4.getString("kode").equals("009")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp24='V'");
                                } else if (rs4.getString("kode").equals("010")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp25='V'");
                                } else if (rs4.getString("kode").equals("011")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp26='V'");
                                } else if (rs4.getString("kode").equals("012")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp27='V'");
                                } else if (rs4.getString("kode").equals("013")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp28='V'");
                                } else if (rs4.getString("kode").equals("014")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp29='V'");
                                } else if (rs4.getString("kode").equals("015")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp30='V'");
                                } else if (rs4.getString("kode").equals("016")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp31='V'");
                                } else if (rs4.getString("kode").equals("017")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp32='V'");
                                } else if (rs4.getString("kode").equals("018")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp33='V'");
                                } else if (rs4.getString("kode").equals("019")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp34='V'");
                                } else if (rs4.getString("kode").equals("020")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp35='V'");
                                } else if (rs4.getString("kode").equals("021")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp36='V'");
                                } else if (rs4.getString("kode").equals("022")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp37='V'");
                                } else if (rs4.getString("kode").equals("023")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp38='V'");
                                } else if (rs4.getString("kode").equals("024")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp39='V'");
                                } else if (rs4.getString("kode").equals("025")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp40='V'");
                                } else if (rs4.getString("kode").equals("026")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp41='V'");
                                } else if (rs4.getString("kode").equals("027")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp42='V'");
                                } else if (rs4.getString("kode").equals("028")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp43='V'");
                                } else if (rs4.getString("kode").equals("029")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp44='V'");
                                } else if (rs4.getString("kode").equals("030")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp45='V'");
                                } else if (rs4.getString("kode").equals("031")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp46='V'");
                                } else if (rs4.getString("kode").equals("032")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp47='V'");
                                } else if (rs4.getString("kode").equals("033")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp48='V'");
                                } else if (rs4.getString("kode").equals("034")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp49='V'");
                                } else if (rs4.getString("kode").equals("035")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp50='V'");
                                } else if (rs4.getString("kode").equals("036")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp51='V'");
                                } else if (rs4.getString("kode").equals("037")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp52='V'");
                                } else if (rs4.getString("kode").equals("038")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp53='V'");
                                } else if (rs4.getString("kode").equals("039")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp54='V'");
                                } else if (rs4.getString("kode").equals("040")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp55='V'");
                                } else if (rs4.getString("kode").equals("041")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp56='V'");
                                } else if (rs4.getString("kode").equals("042")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp57='V'");
                                } else if (rs4.getString("kode").equals("043")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp58='V'");
                                } else if (rs4.getString("kode").equals("044")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp59='V'");
                                } else if (rs4.getString("kode").equals("045")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp60='V'");
                                } else if (rs4.getString("kode").equals("046")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp61='V'");
                                } else if (rs4.getString("kode").equals("047")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp62='V'");
                                } else if (rs4.getString("kode").equals("048")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp63='V'");
                                } else if (rs4.getString("kode").equals("049")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp64='V'");
                                } else if (rs4.getString("kode").equals("050")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp65='V'");
                                } else if (rs4.getString("kode").equals("051")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp66='V'");
                                } else if (rs4.getString("kode").equals("052")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp67='V'");
                                } else if (rs4.getString("kode").equals("053")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp68='V'");
                                } else if (rs4.getString("kode").equals("054")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp69='V'");
                                } else if (rs4.getString("kode").equals("055")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp70='V'");
                                } else if (rs4.getString("kode").equals("056")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp71='V'");
                                } else if (rs4.getString("kode").equals("057")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp72='V'");
                                } else if (rs4.getString("kode").equals("058")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp73='V'");
                                } else if (rs4.getString("kode").equals("059")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp74='V'");
                                } else if (rs4.getString("kode").equals("060")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp75='V'");
                                } else if (rs4.getString("kode").equals("061")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp76='V'");
                                } else if (rs4.getString("kode").equals("062")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp77='V'");
                                } else if (rs4.getString("kode").equals("063")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp78='V'");
                                } else if (rs4.getString("kode").equals("064")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp79='V'");
                                } else if (rs4.getString("kode").equals("065")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp80='V'");
                                } else if (rs4.getString("kode").equals("066")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp81='V'");
                                } else if (rs4.getString("kode").equals("067")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp82='V'");
                                } else if (rs4.getString("kode").equals("068")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp83='V'");
                                } else if (rs4.getString("kode").equals("069")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp84='V'");
                                } else if (rs4.getString("kode").equals("070")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp85='V'");
                                } else if (rs4.getString("kode").equals("071")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp86='V'");
                                } else if (rs4.getString("kode").equals("072")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp87='V'");
                                } else if (rs4.getString("kode").equals("073")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp88='V'");
                                } else if (rs4.getString("kode").equals("074")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp89='V'");
                                } else if (rs4.getString("kode").equals("075")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp90='V'");
                                } else if (rs4.getString("kode").equals("076")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp91='V'");
                                } else if (rs4.getString("kode").equals("077")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp92='V'");
                                } else if (rs4.getString("kode").equals("078")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp93='V'");
                                } else if (rs4.getString("kode").equals("079")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp94='V'");
                                } else if (rs4.getString("kode").equals("080")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp95='V'");
                                } else if (rs4.getString("kode").equals("081")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp96='V'");
                                } else if (rs4.getString("kode").equals("082")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp97='V'");
                                } else if (rs4.getString("kode").equals("083")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp98='V'");
                                } else if (rs4.getString("kode").equals("084")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp99='V'");
                                } else if (rs4.getString("kode").equals("085")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp100='V'");
                                } else if (rs4.getString("kode").equals("086")) {
                                    Sequel.mengedit("temporary3", "temp1='" + rs3.getString("no_rawat") + "'", "temp101='V'");
                                } else if (rs4.getString("kode").equals("")) {
                                    System.out.println("Pesan : Item kasus persalinan gagal tersimpan ditabel detail_persalinan_dinkes");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Penghitungan Visit : " + e);
                        } finally {
                            if (rs4 != null) {
                                rs4.close();
                            }
                            if (ps4 != null) {
                                ps4.close();
                            }
                        }
                    }
                    x++;
                    Sequel.AutoComitTrue();
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Pasien : " + e);
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
    
    private void cekjam() {
        cJL = "";
        cJADV = "";
        cJACC = "";
        jamlap = "";
        jamad = "";
        jamac = "";

        if (Chkjamlapor.isSelected() == true) {
            cJL = "1";
            jamlap = cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem();
        } else {
            cJL = "0";
            jamlap = "00:00:00";
        }

        if (Chkjamadv.isSelected() == true) {
            cJADV = "1";
            jamad = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
        } else {
            cJADV = "0";
            jamad = "00:00:00";
        }

        if (Chkjamacc.isSelected() == true) {
            cJACC = "1";
            jamac = cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem();
        } else {
            cJACC = "0";
            jamac = "00:00:00";
        }
    }
}
