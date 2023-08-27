/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgPerawatan.java
 *
 * Created on May 23, 2010, 6:36:30 PM
 */
package simrskhanza;

import keuangan.DlgJnsPerawatanRadiologi;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import ipsrs.DlgBarangIPSRS;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import keuangan.Jurnal;

/**
 *
 * @author dosen
 */
public final class DlgPeriksaRadiologi extends javax.swing.JDialog {

    private DefaultTableModel tabMode, tabMode2;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private Jurnal jur = new Jurnal();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private PreparedStatement psset_tarif, pssetpj, pspemeriksaan, pspemeriksaan2, pspemeriksaan3, pspemeriksaan4, psbhp, psrekening;
    private ResultSet rs, rsset_tarif, rssetpj, rsrekening;
    private boolean[] pilih;
    private String[] kode, nama, kodebarang, namabarang, satuan;
    private double[] jumlah, total, bagian_rs, bhp, tarif_perujuk, tarif_tindakan_dokter, tarif_tindakan_petugas, kso, menejemen;
    private int jml = 0, i = 0, index = 0, cekBhp = 0;
    private String noorder = "", kelas_radiologi = "Yes", kelas = "", cara_bayar_radiologi = "Yes", pilihan = "", pemeriksaan = "", status = "";
    private double ttl = 0, item = 0;
    private double ttljmdokter = 0, ttljmpetugas = 0, ttlkso = 0, ttlpendapatan = 0, ttlbhp = 0;
    private String Suspen_Piutang_Radiologi_Ranap = "", Radiologi_Ranap = "", Beban_Jasa_Medik_Dokter_Radiologi_Ranap = "",
            Utang_Jasa_Medik_Dokter_Radiologi_Ranap = "", Beban_Jasa_Medik_Petugas_Radiologi_Ranap = "",
            Utang_Jasa_Medik_Petugas_Radiologi_Ranap = "", Beban_Kso_Radiologi_Ranap = "", Utang_Kso_Radiologi_Ranap = "",
            HPP_Persediaan_Radiologi_Rawat_Inap = "", Persediaan_BHP_Radiologi_Rawat_Inap = "", kdPenjab = "", kdPoli = "";

    /**
     * Creates new form DlgPerawatan
     *
     * @param parent
     * @param modal
     */
    public DlgPeriksaRadiologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"P", "Kode Periksa", "Nama Pemeriksaan", "Tarif", "Bagian RS", "BHP", "Tarif Perujuk", "Tarif Dokter", "Tarif Petugas", "Kso", "Menejemen"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPemeriksaan.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(310);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{"Jumlah", "Kode", "Nama BHP", "Satuan", "Harga","Stok"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbBHPRadiologi.setModel(tabMode2);
        tbBHPRadiologi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbBHPRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbBHPRadiologi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            }
        }

        tbBHPRadiologi.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        KdPtg.setDocument(new batasInput((byte) 20).getKata(KdPtg));
        KodePerujuk.setDocument(new batasInput((byte) 20).getKata(KodePerujuk));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCariPeriksa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCariBhp.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil2();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil2();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil2();
                }
            });
        }
        ChkJln.setSelected(true);
        jam();

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPeriksaRadiologi")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        KdPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                    tbPemeriksaan.requestFocus();
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

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPeriksaRadiologi")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        if (pilihan.equals("perujuk")) {
                            KodePerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            NmPerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            KodePerujuk.requestFocus();
                        } else if (pilihan.equals("penjab")) {
                            KodePj.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            NmDokterPj.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            KodePj.requestFocus();
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

        try {
            psrekening = koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening = psrekening.executeQuery();
                while (rsrekening.next()) {
                    Suspen_Piutang_Radiologi_Ranap = rsrekening.getString("Suspen_Piutang_Radiologi_Ranap");
                    Radiologi_Ranap = rsrekening.getString("Radiologi_Ranap");
                    Beban_Jasa_Medik_Dokter_Radiologi_Ranap = rsrekening.getString("Beban_Jasa_Medik_Dokter_Radiologi_Ranap");
                    Utang_Jasa_Medik_Dokter_Radiologi_Ranap = rsrekening.getString("Utang_Jasa_Medik_Dokter_Radiologi_Ranap");
                    Beban_Jasa_Medik_Petugas_Radiologi_Ranap = rsrekening.getString("Beban_Jasa_Medik_Petugas_Radiologi_Ranap");
                    Utang_Jasa_Medik_Petugas_Radiologi_Ranap = rsrekening.getString("Utang_Jasa_Medik_Petugas_Radiologi_Ranap");
                    Beban_Kso_Radiologi_Ranap = rsrekening.getString("Beban_Kso_Radiologi_Ranap");
                    Utang_Kso_Radiologi_Ranap = rsrekening.getString("Utang_Kso_Radiologi_Ranap");
                    HPP_Persediaan_Radiologi_Rawat_Inap = rsrekening.getString("HPP_Persediaan_Radiologi_Rawat_Inap");
                    Persediaan_BHP_Radiologi_Rawat_Inap = rsrekening.getString("Persediaan_BHP_Radiologi_Rawat_Inap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : " + e);
            } finally {
                if (rsrekening != null) {
                    rsrekening.close();
                }
                if (psrekening != null) {
                    psrekening.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            psset_tarif = koneksi.prepareStatement("select * from set_tarif");
            try {
                rsset_tarif = psset_tarif.executeQuery();
                if (rsset_tarif.next()) {
                    cara_bayar_radiologi = rsset_tarif.getString("cara_bayar_radiologi");
                    kelas_radiologi = rsset_tarif.getString("kelas_radiologi");
                } else {
                    cara_bayar_radiologi = "Yes";
                    kelas_radiologi = "Yes";
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsset_tarif != null) {
                    rsset_tarif.close();
                }
                if (psset_tarif != null) {
                    psset_tarif.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
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

        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        jLabel10 = new widget.Label();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        FormInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        PanelInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel7 = new widget.Label();
        jLabel9 = new widget.Label();
        jLabel12 = new widget.Label();
        KdPtg = new widget.TextBox();
        btnPetugas = new widget.Button();
        NmPtg = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        jLabel16 = new widget.Label();
        NmDokterPj = new widget.TextBox();
        KodePj = new widget.TextBox();
        KodePerujuk = new widget.TextBox();
        NmPerujuk = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel15 = new widget.Label();
        btnDokterPj = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        panelisi5 = new widget.panelisi();
        label10 = new widget.Label();
        TCariPeriksa = new widget.TextBox();
        btnCariPeriksa = new widget.Button();
        BtnAllPeriksa = new widget.Button();
        BtnTambahPeriksa = new widget.Button();
        Scroll2 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        label9 = new widget.Label();
        TCariBhp = new widget.TextBox();
        btnCariBhp = new widget.Button();
        BtnAllBhp = new widget.Button();
        BtnTambahBhp = new widget.Button();
        Scroll = new widget.ScrollPane();
        tbBHPRadiologi = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Input Data Hasil Periksa Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 30));
        panelGlass8.add(jLabel10);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari.setMnemonic('C');
        BtnCari.setText("Cari");
        BtnCari.setToolTipText("Alt+C");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(100, 30));
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setOpaque(false);
        FormInput.setPreferredSize(new java.awt.Dimension(560, 130));
        FormInput.setLayout(new java.awt.BorderLayout(1, 1));

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
        FormInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 108));
        PanelInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. Rawat : ");
        jLabel3.setName("jLabel3"); // NOI18N
        PanelInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 92, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(95, 12, 148, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(245, 12, 80, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(328, 12, 445, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Dokter Rad. : ");
        jLabel7.setName("jLabel7"); // NOI18N
        PanelInput.add(jLabel7);
        jLabel7.setBounds(0, 42, 92, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dokter Perujuk : ");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 72, 92, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Petugas :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelInput.add(jLabel12);
        jLabel12.setBounds(392, 42, 70, 23);

        KdPtg.setEditable(false);
        KdPtg.setForeground(new java.awt.Color(0, 0, 0));
        KdPtg.setName("KdPtg"); // NOI18N
        KdPtg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPtgKeyPressed(evt);
            }
        });
        PanelInput.add(KdPtg);
        KdPtg.setBounds(464, 42, 80, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("Alt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        PanelInput.add(btnPetugas);
        btnPetugas.setBounds(744, 42, 28, 23);

        NmPtg.setEditable(false);
        NmPtg.setForeground(new java.awt.Color(0, 0, 0));
        NmPtg.setName("NmPtg"); // NOI18N
        PanelInput.add(NmPtg);
        NmPtg.setBounds(546, 42, 195, 23);

        Tanggal.setEditable(false);
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-01-2021" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        PanelInput.add(Tanggal);
        Tanggal.setBounds(464, 72, 90, 23);

        CmbJam.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.setOpaque(false);
        PanelInput.add(CmbJam);
        CmbJam.setBounds(614, 72, 42, 23);

        CmbMenit.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.setOpaque(false);
        PanelInput.add(CmbMenit);
        CmbMenit.setBounds(659, 72, 42, 23);

        CmbDetik.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.setOpaque(false);
        PanelInput.add(CmbDetik);
        CmbDetik.setBounds(704, 72, 42, 23);

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
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        PanelInput.add(ChkJln);
        ChkJln.setBounds(749, 72, 23, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Jam :");
        jLabel16.setName("jLabel16"); // NOI18N
        PanelInput.add(jLabel16);
        jLabel16.setBounds(533, 72, 78, 23);

        NmDokterPj.setEditable(false);
        NmDokterPj.setForeground(new java.awt.Color(0, 0, 0));
        NmDokterPj.setHighlighter(null);
        NmDokterPj.setName("NmDokterPj"); // NOI18N
        PanelInput.add(NmDokterPj);
        NmDokterPj.setBounds(177, 42, 180, 23);

        KodePj.setEditable(false);
        KodePj.setForeground(new java.awt.Color(0, 0, 0));
        KodePj.setName("KodePj"); // NOI18N
        PanelInput.add(KodePj);
        KodePj.setBounds(95, 42, 80, 23);

        KodePerujuk.setEditable(false);
        KodePerujuk.setForeground(new java.awt.Color(0, 0, 0));
        KodePerujuk.setName("KodePerujuk"); // NOI18N
        KodePerujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodePerujukKeyPressed(evt);
            }
        });
        PanelInput.add(KodePerujuk);
        KodePerujuk.setBounds(95, 72, 80, 23);

        NmPerujuk.setEditable(false);
        NmPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        NmPerujuk.setHighlighter(null);
        NmPerujuk.setName("NmPerujuk"); // NOI18N
        PanelInput.add(NmPerujuk);
        NmPerujuk.setBounds(177, 72, 180, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('4');
        btnDokter.setToolTipText("ALt+4");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        PanelInput.add(btnDokter);
        btnDokter.setBounds(359, 72, 28, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Periksa :");
        jLabel15.setName("jLabel15"); // NOI18N
        PanelInput.add(jLabel15);
        jLabel15.setBounds(375, 72, 87, 23);

        btnDokterPj.setForeground(new java.awt.Color(0, 0, 0));
        btnDokterPj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokterPj.setMnemonic('4');
        btnDokterPj.setToolTipText("ALt+4");
        btnDokterPj.setName("btnDokterPj"); // NOI18N
        btnDokterPj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterPjActionPerformed(evt);
            }
        });
        PanelInput.add(btnDokterPj);
        btnDokterPj.setBounds(359, 42, 28, 23);

        FormInput.add(PanelInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Pemeriksaan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 102));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi5.setBorder(null);
        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi5.add(label10);

        TCariPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        TCariPeriksa.setToolTipText("Alt+C");
        TCariPeriksa.setName("TCariPeriksa"); // NOI18N
        TCariPeriksa.setPreferredSize(new java.awt.Dimension(280, 23));
        TCariPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCariPeriksaActionPerformed(evt);
            }
        });
        TCariPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(TCariPeriksa);

        btnCariPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        btnCariPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCariPeriksa.setMnemonic('1');
        btnCariPeriksa.setToolTipText("Alt+1");
        btnCariPeriksa.setName("btnCariPeriksa"); // NOI18N
        btnCariPeriksa.setPreferredSize(new java.awt.Dimension(28, 23));
        btnCariPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPeriksaActionPerformed(evt);
            }
        });
        btnCariPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(btnCariPeriksa);

        BtnAllPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        BtnAllPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllPeriksa.setMnemonic('2');
        BtnAllPeriksa.setToolTipText("Alt+2");
        BtnAllPeriksa.setName("BtnAllPeriksa"); // NOI18N
        BtnAllPeriksa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllPeriksaActionPerformed(evt);
            }
        });
        BtnAllPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(BtnAllPeriksa);

        BtnTambahPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahPeriksa.setMnemonic('3');
        BtnTambahPeriksa.setToolTipText("Alt+3");
        BtnTambahPeriksa.setName("BtnTambahPeriksa"); // NOI18N
        BtnTambahPeriksa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahPeriksaActionPerformed(evt);
            }
        });
        BtnTambahPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnTambahPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(BtnTambahPeriksa);

        jPanel3.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 255, 235)));
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanMouseClicked(evt);
            }
        });
        tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemeriksaanKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbPemeriksaan);

        jPanel3.add(Scroll2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: BHP Radiologi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setBorder(null);
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi4.add(label9);

        TCariBhp.setForeground(new java.awt.Color(0, 0, 0));
        TCariBhp.setToolTipText("Alt+C");
        TCariBhp.setName("TCariBhp"); // NOI18N
        TCariBhp.setPreferredSize(new java.awt.Dimension(280, 23));
        TCariBhp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariBhpKeyPressed(evt);
            }
        });
        panelisi4.add(TCariBhp);

        btnCariBhp.setForeground(new java.awt.Color(0, 0, 0));
        btnCariBhp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCariBhp.setMnemonic('1');
        btnCariBhp.setToolTipText("Alt+1");
        btnCariBhp.setName("btnCariBhp"); // NOI18N
        btnCariBhp.setPreferredSize(new java.awt.Dimension(28, 23));
        btnCariBhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBhpActionPerformed(evt);
            }
        });
        btnCariBhp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariBhpKeyPressed(evt);
            }
        });
        panelisi4.add(btnCariBhp);

        BtnAllBhp.setForeground(new java.awt.Color(0, 0, 0));
        BtnAllBhp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllBhp.setMnemonic('2');
        BtnAllBhp.setToolTipText("Alt+2");
        BtnAllBhp.setName("BtnAllBhp"); // NOI18N
        BtnAllBhp.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllBhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllBhpActionPerformed(evt);
            }
        });
        BtnAllBhp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllBhpKeyPressed(evt);
            }
        });
        panelisi4.add(BtnAllBhp);

        BtnTambahBhp.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahBhp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahBhp.setMnemonic('3');
        BtnTambahBhp.setToolTipText("Alt+3");
        BtnTambahBhp.setName("BtnTambahBhp"); // NOI18N
        BtnTambahBhp.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahBhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahBhpActionPerformed(evt);
            }
        });
        BtnTambahBhp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnTambahBhpKeyPressed(evt);
            }
        });
        panelisi4.add(BtnTambahBhp);

        jPanel2.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 255, 235)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBHPRadiologi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBHPRadiologi.setName("tbBHPRadiologi"); // NOI18N
        tbBHPRadiologi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBHPRadiologiMouseClicked(evt);
            }
        });
        tbBHPRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBHPRadiologiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBHPRadiologi);

        jPanel2.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnCari, KdPtg);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

private void KdPtgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPtgKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select nama from petugas where nip=?", NmPtg, KdPtg.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnPetugasActionPerformed(null);
    } else {
        Valid.pindah(evt, KodePj, KodePerujuk);
    }
}//GEN-LAST:event_KdPtgKeyPressed

private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
    akses.setform("DlgPeriksaRadiologi");
    petugas.emptTeks();
    petugas.tampil();
    petugas.isCek();
    petugas.setSize(983, 291);
    petugas.setLocationRelativeTo(internalFrame1);
    petugas.setVisible(true);
}//GEN-LAST:event_btnPetugasActionPerformed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    DlgCariPeriksaRadiologi form = new DlgCariPeriksaRadiologi(null, false);
    form.setPasien(TNoRw.getText());
    form.setSize(this.getWidth(), this.getHeight());
    form.setLocationRelativeTo(this);
    form.setVisible(true);
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnCariActionPerformed

private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_ChkJlnActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt, KodePerujuk, TCariPeriksa);
    }//GEN-LAST:event_TanggalKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil2();
    }//GEN-LAST:event_formWindowOpened

    private void TCariPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPeriksaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCariPeriksaActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            btnCariPeriksa.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnTambahPeriksa.requestFocus();
        }
    }//GEN-LAST:event_TCariPeriksaKeyPressed

    private void btnCariPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPeriksaActionPerformed
        tampil();
    }//GEN-LAST:event_btnCariPeriksaActionPerformed

    private void btnCariPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariPeriksaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
        } else {
            Valid.pindah(evt, TCariPeriksa, BtnAllPeriksa);
        }
    }//GEN-LAST:event_btnCariPeriksaKeyPressed

    private void BtnAllPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllPeriksaActionPerformed
        TCariPeriksa.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllPeriksaActionPerformed

    private void BtnAllPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllPeriksaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllPeriksaActionPerformed(null);
        } else {
            Valid.pindah(evt, btnCariPeriksa, BtnTambahPeriksa);
        }
    }//GEN-LAST:event_BtnAllPeriksaKeyPressed

    private void BtnTambahPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahPeriksaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanRadiologi produsen = new DlgJnsPerawatanRadiologi(null, false);
        produsen.emptTeks();
        produsen.isCek();
        produsen.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        produsen.setLocationRelativeTo(internalFrame1);
        produsen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahPeriksaActionPerformed

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                // getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void tbPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyPressed
        if (tbPemeriksaan.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    int row = tbPemeriksaan.getSelectedColumn();
                    if ((row != 0) || (row != 20)) {
                        if (tbPemeriksaan.getSelectedRow() > -1) {
                            tbPemeriksaan.setValueAt(true, tbPemeriksaan.getSelectedRow(), 0);
                        }
                        TCariPeriksa.setText("");
                        TCariPeriksa.requestFocus();
                    }
                    //getData2();
                } catch (java.lang.NullPointerException e) {
                }
            } else if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    // getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemeriksaanKeyPressed

    private void TCariBhpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariBhpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampil2();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            btnCariBhp.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnTambahBhp.requestFocus();
        }
    }//GEN-LAST:event_TCariBhpKeyPressed

    private void btnCariBhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBhpActionPerformed
        tampil2();
    }//GEN-LAST:event_btnCariBhpActionPerformed

    private void btnCariBhpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariBhpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil2();
        } else {
            Valid.pindah(evt, TCariBhp, BtnAllBhp);
        }
    }//GEN-LAST:event_btnCariBhpKeyPressed

    private void BtnAllBhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllBhpActionPerformed
        TCariBhp.setText("");
        tampil2();
    }//GEN-LAST:event_BtnAllBhpActionPerformed

    private void BtnAllBhpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllBhpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllBhpActionPerformed(null);
        } else {
            Valid.pindah(evt, btnCariBhp, BtnTambahBhp);
        }
    }//GEN-LAST:event_BtnAllBhpKeyPressed

    private void BtnTambahBhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahBhpActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBarangIPSRS produsen = new DlgBarangIPSRS(null, false);
        produsen.isCek();
        produsen.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        produsen.setLocationRelativeTo(internalFrame1);
        produsen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahBhpActionPerformed

    private void tbBHPRadiologiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBHPRadiologiMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                //getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbBHPRadiologiMouseClicked

    private void tbBHPRadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBHPRadiologiKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    //getData();
                    int row = tbBHPRadiologi.getSelectedColumn();
                    if (row == 1) {
                        TCariBhp.setText("");
                        TCariBhp.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    //getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                int row = tbBHPRadiologi.getSelectedRow();
                if (row != -1) {
                    tabMode.setValueAt("", row, 0);
                }
            }
        }
    }//GEN-LAST:event_tbBHPRadiologiKeyPressed

    private void BtnTambahPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnTambahPeriksaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnTambahPeriksaActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnAllPeriksa, TCariPeriksa);
        }
    }//GEN-LAST:event_BtnTambahPeriksaKeyPressed

    private void BtnTambahBhpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnTambahBhpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllBhpActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnAllBhp, TCariBhp);
        }
    }//GEN-LAST:event_BtnTambahBhpKeyPressed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TCariPeriksa, BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("") || TNoRM.getText().equals("") || TPasien.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (KdPtg.getText().equals("") || NmPtg.getText().equals("")) {
            Valid.textKosong(KdPtg, "Petugas");
        } else if (KodePerujuk.getText().equals("") || NmPerujuk.getText().equals("")) {
            Valid.textKosong(KodePerujuk, "Dokter Perujuk/Pengirim");
        } else if (KodePj.getText().equals("") || NmDokterPj.getText().equals("") || KodePj.getText().equals("-") || KodePj.getText().equals("--")) {
            Valid.textKosong(KodePj, "Dokter Pemeriksa Radiologi");
        } else if (tabMode.getRowCount() == 0) {
            Valid.textKosong(TCariPeriksa, "Data Pemeriksaan");
        } else {
            for (i = 0; i < tbBHPRadiologi.getRowCount(); i++) {
                if (Valid.SetAngka(tbBHPRadiologi.getValueAt(i, 0).toString()) > 0) {
                    cekBhp++;
                }
            }
            if (cekBhp == 0) {
                JOptionPane.showMessageDialog(null, "Silakan Isi Pemakaian BHP Radiologi");
            } else {
                if (akses.getkode().equals("Admin Utama")) {
                    int reply = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data akan disimpan ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        ChkJln.setSelected(false);
                        ttljmdokter = 0;
                        ttljmpetugas = 0;
                        ttlkso = 0;
                        ttlpendapatan = 0;
                        Sequel.AutoComitFalse();
                        for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                            if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                                if (Sequel.menyimpantf2("periksa_radiologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Pemeriksaan", 18, new String[]{
                                    TNoRw.getText(), KdPtg.getText(), tbPemeriksaan.getValueAt(i, 1).toString(), Valid.SetTgl(Tanggal.getSelectedItem() + ""),
                                    CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(), KodePerujuk.getText(),
                                    tbPemeriksaan.getValueAt(i, 4).toString(), tbPemeriksaan.getValueAt(i, 5).toString(), tbPemeriksaan.getValueAt(i, 6).toString(),
                                    tbPemeriksaan.getValueAt(i, 7).toString(), tbPemeriksaan.getValueAt(i, 8).toString(), tbPemeriksaan.getValueAt(i, 9).toString(),
                                    tbPemeriksaan.getValueAt(i, 10).toString(), tbPemeriksaan.getValueAt(i, 3).toString(),
                                    KodePj.getText(), status, "Belum", "-"
                                }) == true) {
                                    ttljmdokter = ttljmdokter + Double.parseDouble(tbPemeriksaan.getValueAt(i, 6).toString()) + Double.parseDouble(tbPemeriksaan.getValueAt(i, 7).toString());
                                    ttljmpetugas = ttljmpetugas + Double.parseDouble(tbPemeriksaan.getValueAt(i, 8).toString());
                                    ttlkso = ttlkso + Double.parseDouble(tbPemeriksaan.getValueAt(i, 9).toString());
                                    ttlpendapatan = ttlpendapatan + Double.parseDouble(tbPemeriksaan.getValueAt(i, 3).toString());
                                }
                            }
                        }

                        ttlbhp = 0;
                        for (i = 0; i < tbBHPRadiologi.getRowCount(); i++) {
                            if (Valid.SetAngka(tbBHPRadiologi.getValueAt(i, 0).toString()) > 0) {
                                if (Sequel.menyimpantf2("beri_bhp_radiologi", "?,?,?,?,?,?,?,?", "BHP", 8, new String[]{
                                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                                    tbBHPRadiologi.getValueAt(i, 1).toString(), tbBHPRadiologi.getValueAt(i, 3).toString(), tbBHPRadiologi.getValueAt(i, 0).toString(),
                                    tbBHPRadiologi.getValueAt(i, 4).toString(), Double.toString(Double.parseDouble(tbBHPRadiologi.getValueAt(i, 4).toString()) * Double.parseDouble(tbBHPRadiologi.getValueAt(i, 0).toString()))
                                }) == true) {
                                    ttlbhp = ttlbhp + (Double.parseDouble(tbBHPRadiologi.getValueAt(i, 4).toString()) * Double.parseDouble(tbBHPRadiologi.getValueAt(i, 0).toString()));
                                    Sequel.mengedit("ipsrsbarang", "kode_brng=?", "stok=stok-?", 2, new String[]{
                                        tbBHPRadiologi.getValueAt(i, 0).toString(), tbBHPRadiologi.getValueAt(i, 1).toString()
                                    });
                                }
                            }
                        }

                        if (status.equals("Ranap")) {
                            Sequel.queryu("delete from tampjurnal");
                            if (ttlpendapatan > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Radiologi_Ranap + "','Suspen Piutang Radiologi Ranap','" + ttlpendapatan + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Radiologi_Ranap + "','Pendapatan Radiologi Rawat Inap','0','" + ttlpendapatan + "'", "Rekening");
                            }
                            if (ttljmdokter > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Radiologi_Ranap + "','Beban Jasa Medik Dokter Radiologi Ranap','" + ttljmdokter + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Radiologi_Ranap + "','Utang Jasa Medik Dokter Radiologi Ranap','0','" + ttljmdokter + "'", "Rekening");
                            }
                            if (ttljmpetugas > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Radiologi_Ranap + "','Beban Jasa Medik Petugas Radiologi Ranap','" + ttljmpetugas + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Radiologi_Ranap + "','Utang Jasa Medik Petugas Radiologi Ranap','0','" + ttljmpetugas + "'", "Rekening");
                            }
                            if (ttlbhp > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Radiologi_Rawat_Inap + "','HPP Persediaan Radiologi Rawat Inap','" + ttlbhp + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Radiologi_Rawat_Inap + "','Persediaan BHP Radiologi Rawat Inap','0','" + ttlbhp + "'", "Rekening");
                            }
                            if (ttlkso > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Radiologi_Ranap + "','Beban KSO Radiologi Ranap','" + ttlkso + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Radiologi_Ranap + "','Utang KSO Radiologi Ranap','0','" + ttlkso + "'", "Rekening");
                            }
                            jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "PEMERIKSAAN RADIOLOGI RAWAT INAP PASIEN " + TPasien.getText() + " DIPOSTING OLEH " + akses.getkode());
                        }
                        Sequel.AutoComitTrue();
                        ChkJln.setSelected(true);
                        JOptionPane.showMessageDialog(null, "Proses simpan selesai...!");
                        isReset();
                    }
                } else {
//                if(Sequel.cariRegistrasi(TNoRw.getText())>0){
//                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
//                    TCariPeriksa.requestFocus();
//                }else{
                    int reply = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data akan disimpan ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        ChkJln.setSelected(false);
                        ttljmdokter = 0;
                        ttljmpetugas = 0;
                        ttlkso = 0;
                        ttlpendapatan = 0;
                        Sequel.AutoComitFalse();
                        for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                            if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                                if (Sequel.menyimpantf2("periksa_radiologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Pemeriksaan", 18, new String[]{
                                    TNoRw.getText(), KdPtg.getText(), tbPemeriksaan.getValueAt(i, 1).toString(), Valid.SetTgl(Tanggal.getSelectedItem() + ""),
                                    CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(), KodePerujuk.getText(),
                                    tbPemeriksaan.getValueAt(i, 4).toString(), tbPemeriksaan.getValueAt(i, 5).toString(), tbPemeriksaan.getValueAt(i, 6).toString(),
                                    tbPemeriksaan.getValueAt(i, 7).toString(), tbPemeriksaan.getValueAt(i, 8).toString(), tbPemeriksaan.getValueAt(i, 9).toString(),
                                    tbPemeriksaan.getValueAt(i, 10).toString(), tbPemeriksaan.getValueAt(i, 3).toString(),
                                    KodePj.getText(), status, "Belum", "-"
                                }) == true) {
                                    ttljmdokter = ttljmdokter + Double.parseDouble(tbPemeriksaan.getValueAt(i, 6).toString()) + Double.parseDouble(tbPemeriksaan.getValueAt(i, 7).toString());
                                    ttljmpetugas = ttljmpetugas + Double.parseDouble(tbPemeriksaan.getValueAt(i, 8).toString());
                                    ttlkso = ttlkso + Double.parseDouble(tbPemeriksaan.getValueAt(i, 9).toString());
                                    ttlpendapatan = ttlpendapatan + Double.parseDouble(tbPemeriksaan.getValueAt(i, 3).toString());
                                }
                            }
                        }
                        ttlbhp = 0;
                        for (i = 0; i < tbBHPRadiologi.getRowCount(); i++) {
                            if (Valid.SetAngka(tbBHPRadiologi.getValueAt(i, 0).toString()) > 0) {
                                if (Sequel.menyimpantf2("beri_bhp_radiologi", "?,?,?,?,?,?,?,?", "BHP", 8, new String[]{
                                    TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                                    tbBHPRadiologi.getValueAt(i, 1).toString(), tbBHPRadiologi.getValueAt(i, 3).toString(), tbBHPRadiologi.getValueAt(i, 0).toString(),
                                    tbBHPRadiologi.getValueAt(i, 4).toString(), Double.toString(Double.parseDouble(tbBHPRadiologi.getValueAt(i, 4).toString()) * Double.parseDouble(tbBHPRadiologi.getValueAt(i, 0).toString()))
                                }) == true) {
                                    ttlbhp = ttlbhp + (Double.parseDouble(tbBHPRadiologi.getValueAt(i, 4).toString()) * Double.parseDouble(tbBHPRadiologi.getValueAt(i, 0).toString()));
                                    Sequel.mengedit("ipsrsbarang", "kode_brng=?", "stok=stok-?", 2, new String[]{
                                        tbBHPRadiologi.getValueAt(i, 0).toString(), tbBHPRadiologi.getValueAt(i, 1).toString()
                                    });
                                }
                            }
                        }

                        if (status.equals("Ranap")) {
                            Sequel.queryu("delete from tampjurnal");
                            if (ttlpendapatan > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Radiologi_Ranap + "','Suspen Piutang Radiologi Ranap','" + ttlpendapatan + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Radiologi_Ranap + "','Pendapatan Radiologi Rawat Inap','0','" + ttlpendapatan + "'", "Rekening");
                            }
                            if (ttljmdokter > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Radiologi_Ranap + "','Beban Jasa Medik Dokter Radiologi Ranap','" + ttljmdokter + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Radiologi_Ranap + "','Utang Jasa Medik Dokter Radiologi Ranap','0','" + ttljmdokter + "'", "Rekening");
                            }
                            if (ttljmpetugas > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Radiologi_Ranap + "','Beban Jasa Medik Petugas Radiologi Ranap','" + ttljmpetugas + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Radiologi_Ranap + "','Utang Jasa Medik Petugas Radiologi Ranap','0','" + ttljmpetugas + "'", "Rekening");
                            }
                            if (ttlbhp > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Radiologi_Rawat_Inap + "','HPP Persediaan Radiologi Rawat Inap','" + ttlbhp + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Radiologi_Rawat_Inap + "','Persediaan BHP Radiologi Rawat Inap','0','" + ttlbhp + "'", "Rekening");
                            }
                            if (ttlkso > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Radiologi_Ranap + "','Beban KSO Radiologi Ranap','" + ttlkso + "','0'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Radiologi_Ranap + "','Utang KSO Radiologi Ranap','0','" + ttlkso + "'", "Rekening");
                            }
                            jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "PEMERIKSAAN RADIOLOGI RAWAT INAP PASIEN " + TPasien.getText() + " DIPOSTING OLEH " + akses.getkode());
                        }
                        Sequel.AutoComitTrue();
                        ChkJln.setSelected(true);
                        JOptionPane.showMessageDialog(null, "Proses simpan selesai...!");
                        isReset();
                    }
//                }
                }
            }

        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllBhpActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void KodePerujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodePerujukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", NmPerujuk, KodePerujuk.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnDokterActionPerformed(null);
        } else {
            Valid.pindah(evt, KdPtg, Tanggal);
        }
    }//GEN-LAST:event_KodePerujukKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        pilihan = "perujuk";
        akses.setform("DlgPeriksaRadiologi");
        dokter.emptTeks();
        dokter.isCek();
        dokter.TCari.setText("");
        dokter.tampil();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void btnDokterPjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterPjActionPerformed
        pilihan = "penjab";
        akses.setform("DlgPeriksaRadiologi");
        dokter.emptTeks();
        dokter.isCek();
        dokter.TCari.setText("rad");
        dokter.tampil();
        dokter.setSize(1046, 341);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterPjActionPerformed

    private void TCariPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCariPeriksaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCariPeriksaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPeriksaRadiologi dialog = new DlgPeriksaRadiologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAllBhp;
    private widget.Button BtnAllPeriksa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahBhp;
    private widget.Button BtnTambahPeriksa;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private javax.swing.JPanel FormInput;
    private widget.TextBox KdPtg;
    private widget.TextBox KodePerujuk;
    private widget.TextBox KodePj;
    private widget.TextBox NmDokterPj;
    private widget.TextBox NmPerujuk;
    private widget.TextBox NmPtg;
    private widget.PanelBiasa PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCariBhp;
    private widget.TextBox TCariPeriksa;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.Button btnCariBhp;
    private widget.Button btnCariPeriksa;
    private widget.Button btnDokter;
    private widget.Button btnDokterPj;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel3;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private widget.Label label10;
    private widget.Label label9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.Table tbBHPRadiologi;
    private widget.Table tbPemeriksaan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        try {
            jml = 0;
            for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            nama = null;
            nama = new String[jml];
            total = null;
            total = new double[jml];
            bagian_rs = null;
            bagian_rs = new double[jml];
            bhp = null;
            bhp = new double[jml];
            tarif_perujuk = null;
            tarif_perujuk = new double[jml];
            tarif_tindakan_dokter = null;
            tarif_tindakan_dokter = new double[jml];
            tarif_tindakan_petugas = null;
            tarif_tindakan_petugas = new double[jml];
            kso = null;
            kso = new double[jml];
            menejemen = null;
            menejemen = new double[jml];

            index = 0;
            for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbPemeriksaan.getValueAt(i, 1).toString();
                    nama[index] = tbPemeriksaan.getValueAt(i, 2).toString();
                    total[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 3).toString());
                    bagian_rs[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 4).toString());
                    bhp[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 5).toString());
                    tarif_perujuk[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 6).toString());
                    tarif_tindakan_dokter[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 7).toString());
                    tarif_tindakan_petugas[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 8).toString());
                    kso[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 9).toString());
                    menejemen[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 10).toString());
                    index++;
                }
            }

            Valid.tabelKosong(tabMode);
            for (i = 0; i < jml; i++) {
                tabMode.addRow(new Object[]{pilih[i], kode[i], nama[i], total[i], bagian_rs[i], bhp[i], tarif_perujuk[i], tarif_tindakan_dokter[i], tarif_tindakan_petugas[i], kso[i], menejemen[i]});
            }

            pspemeriksaan = koneksi.prepareStatement(
                    "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
                    + "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
                    + "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
                    + "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
                    + "from jns_perawatan_radiologi inner join penjab on penjab.kd_pj=jns_perawatan_radiologi.kd_pj where "
                    + " jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kd_pj=? or jns_perawatan_radiologi.kd_pj='-') and jns_perawatan_radiologi.kd_jenis_prw like ? or "
                    + " jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kd_pj=? or jns_perawatan_radiologi.kd_pj='-') and jns_perawatan_radiologi.nm_perawatan like ? "
                    + "order by jns_perawatan_radiologi.kd_jenis_prw");
            pspemeriksaan2 = koneksi.prepareStatement(
                    "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
                    + "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
                    + "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
                    + "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
                    + "from jns_perawatan_radiologi inner join penjab on penjab.kd_pj=jns_perawatan_radiologi.kd_pj where "
                    + " jns_perawatan_radiologi.status='1' and jns_perawatan_radiologi.kd_jenis_prw like ? or "
                    + " jns_perawatan_radiologi.status='1' and jns_perawatan_radiologi.nm_perawatan like ?  "
                    + "order by jns_perawatan_radiologi.kd_jenis_prw");
            pspemeriksaan3 = koneksi.prepareStatement(
                    "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
                    + "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
                    + "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
                    + "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
                    + "from jns_perawatan_radiologi inner join penjab on penjab.kd_pj=jns_perawatan_radiologi.kd_pj where "
                    + " jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kd_pj=? or jns_perawatan_radiologi.kd_pj='-') and (jns_perawatan_radiologi.kelas=? or jns_perawatan_radiologi.kelas='-') and jns_perawatan_radiologi.kd_jenis_prw like ? or "
                    + " jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kd_pj=? or jns_perawatan_radiologi.kd_pj='-') and (jns_perawatan_radiologi.kelas=? or jns_perawatan_radiologi.kelas='-') and jns_perawatan_radiologi.nm_perawatan like ? "
                    + "order by jns_perawatan_radiologi.kd_jenis_prw");
            pspemeriksaan4 = koneksi.prepareStatement(
                    "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
                    + "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
                    + "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
                    + "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
                    + "from jns_perawatan_radiologi inner join penjab on penjab.kd_pj=jns_perawatan_radiologi.kd_pj where "
                    + " jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kelas=? or jns_perawatan_radiologi.kelas='-') and jns_perawatan_radiologi.kd_jenis_prw like ? or "
                    + " jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kelas=? or jns_perawatan_radiologi.kelas='-') and jns_perawatan_radiologi.nm_perawatan like ?  "
                    + "order by jns_perawatan_radiologi.kd_jenis_prw");
            try {
                if (cara_bayar_radiologi.equals("Yes") && kelas_radiologi.equals("No")) {
                    pspemeriksaan.setString(1, kdPenjab);
                    pspemeriksaan.setString(2, "%" + TCariPeriksa.getText().trim() + "%");
                    pspemeriksaan.setString(3, kdPenjab);
                    pspemeriksaan.setString(4, "%" + TCariPeriksa.getText().trim() + "%");
                    rs = pspemeriksaan.executeQuery();
                } else if (cara_bayar_radiologi.equals("No") && kelas_radiologi.equals("No")) {
                    pspemeriksaan2.setString(1, "%" + TCariPeriksa.getText().trim() + "%");
                    pspemeriksaan2.setString(2, "%" + TCariPeriksa.getText().trim() + "%");
                    rs = pspemeriksaan2.executeQuery();
                } else if (cara_bayar_radiologi.equals("Yes") && kelas_radiologi.equals("Yes")) {
                    pspemeriksaan3.setString(1, kdPenjab);
                    pspemeriksaan3.setString(2, kelas.trim());
                    pspemeriksaan3.setString(3, "%" + TCariPeriksa.getText().trim() + "%");
                    pspemeriksaan3.setString(4, kdPenjab);
                    pspemeriksaan3.setString(5, kelas.trim());
                    pspemeriksaan3.setString(6, "%" + TCariPeriksa.getText().trim() + "%");
                    rs = pspemeriksaan3.executeQuery();
                } else if (cara_bayar_radiologi.equals("No") && kelas_radiologi.equals("Yes")) {
                    pspemeriksaan4.setString(1, kelas.trim());
                    pspemeriksaan4.setString(2, "%" + TCariPeriksa.getText().trim() + "%");
                    pspemeriksaan4.setString(3, kelas.trim());
                    pspemeriksaan4.setString(4, "%" + TCariPeriksa.getText().trim() + "%");
                    rs = pspemeriksaan4.executeQuery();
                }

                while (rs.next()) {
                    tabMode.addRow(new Object[]{false, rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi 1 : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pspemeriksaan != null) {
                    pspemeriksaan.close();
                }
                if (pspemeriksaan2 != null) {
                    pspemeriksaan2.close();
                }
                if (pspemeriksaan3 != null) {
                    pspemeriksaan3.close();
                }
                if (pspemeriksaan4 != null) {
                    pspemeriksaan4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi 2 : " + e);
        }
    }

    public void isReset() {
        jml = tbPemeriksaan.getRowCount();
        for (i = 0; i < jml; i++) {
            tbPemeriksaan.setValueAt(false, i, 0);
        }
        jml = tbBHPRadiologi.getRowCount();
        for (i = 0; i < jml; i++) {
            tbBHPRadiologi.setValueAt(0, i, 0);
        }
    }

    private void tampil2() {
        jml = 0;
        for (i = 0; i < tbBHPRadiologi.getRowCount(); i++) {
            //System.out.println(tbObat.getValueAt(i,0).toString());
            if (!tbBHPRadiologi.getValueAt(i, 0).toString().equals("")) {
                jml++;
            }
        }

        jumlah = new double[jml];
        kodebarang = new String[jml];
        namabarang = new String[jml];
        satuan = new String[jml];

        index = 0;
        for (i = 0; i < tabMode2.getRowCount(); i++) {
            if (!tabMode2.getValueAt(i, 0).toString().equals("")) {
                jumlah[index] = Double.parseDouble(tabMode2.getValueAt(i, 0).toString());
                kodebarang[index] = tabMode2.getValueAt(i, 1).toString();
                namabarang[index] = tabMode2.getValueAt(i, 2).toString();
                satuan[index] = tabMode2.getValueAt(i, 3).toString();
                index++;
            }
        }

        Valid.tabelKosong(tabMode2);

        for (i = 0; i < jml; i++) {
            Object[] data = {jumlah[i], kodebarang[i], namabarang[i], satuan[i]};
            tabMode2.addRow(data);
        }

        try {
            psbhp = koneksi.prepareStatement("select ipsrsbarang.kode_brng, ipsrsbarang.nama_brng, ipsrsbarang.kode_sat,ipsrsbarang.harga, format(ipsrsbarang.stok,0) stok "
                    + "from ipsrsbarang inner join ipsrsjenisbarang on ipsrsbarang.jenis=ipsrsjenisbarang.kd_jenis "
                    + "where ipsrsjenisbarang.nm_jenis like '%Rad%' and ipsrsbarang.kode_brng like ? or "
                    + "ipsrsjenisbarang.nm_jenis like '%Rad%' and ipsrsbarang.nama_brng like ? order by ipsrsbarang.kode_brng");
            try {
                psbhp.setString(1, "%" + TCariBhp.getText() + "%");
                psbhp.setString(2, "%" + TCariBhp.getText() + "%");
                rs = psbhp.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new Object[]{"",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psbhp != null) {
                    psbhp.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeks() {
        KodePerujuk.setText("");
        NmPerujuk.setText("");
        KdPtg.setText("");
        NmPtg.setText("");
        KodePj.requestFocus();
        isReset();
    }

    public void onCari() {
        KodePj.requestFocus();
    }

    private void isRawat() {
        kdPoli = "";
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
        Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=? ", kdPenjab, TNoRw.getText());
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());

        if (status.equals("Ranap")) {
            kelas = Sequel.cariIsi("select kamar.kelas from kamar inner join kamar_inap "
                    + "on kamar.kd_kamar=kamar_inap.kd_kamar where no_rawat=? "
                    + "and stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1", TNoRw.getText());

            KodePerujuk.setText(Sequel.cariIsi("select ifnull(kd_dokter,'') from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'"));
            if (KodePerujuk.getText().equals("")) {
                NmPerujuk.setText("");
            } else {
                NmPerujuk.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + KodePerujuk.getText() + "'"));
            }
        } else if (status.equals("Ralan")) {
            kelas = "Rawat Jalan";
            kdPoli = Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            if (kdPoli.equals("RAD")) {
                KodePerujuk.setText("");
                NmPerujuk.setText("");
            } else {
                KodePerujuk.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
                NmPerujuk.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + KodePerujuk.getText() + "'"));
            }
        }
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
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

    public void setNoRm(String norwt, String posisi) {
        TNoRw.setText(norwt);
        this.status = posisi;
        try {
            pssetpj = koneksi.prepareStatement("select * from set_pjlab");
            try {
                rssetpj = pssetpj.executeQuery();
                while (rssetpj.next()) {
                    KodePj.setText(rssetpj.getString(2));
                    NmDokterPj.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rssetpj.getString(2)));
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rssetpj != null) {
                    rssetpj.close();
                }
                if (pssetpj != null) {
                    pssetpj.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        isRawat();
        isReset();
    }

    public void isCek() {
        if (akses.getjml2() >= 1) {
            KdPtg.setText(akses.getkode());
            Sequel.cariIsi("select nama from petugas where nip=?", NmPtg, KdPtg.getText());
        } else {
            KdPtg.setText("");
            NmPtg.setText("");
        }
        BtnSimpan.setEnabled(akses.getperiksa_radiologi());
        BtnTambahPeriksa.setEnabled(akses.gettarif_radiologi());
        BtnTambahBhp.setEnabled(akses.getipsrs_barang());
        ChkJln.setSelected(true);
        Tanggal.setDate(new Date());
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH, 130));
            PanelInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH, 20));
            PanelInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void setDokterPerujuk(String kodeperujuk, String namaperujuk) {
        KodePerujuk.setText(kodeperujuk);
        NmPerujuk.setText(namaperujuk);
    }

    void fokus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void fokus_kursor() {
        TCariPeriksa.requestFocus();
    }

    public void setOrder(String order) {
        noorder = order;
        tampil(order);
    }

    public void tampil(String order) {
        try {
            jml = 0;
            for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            nama = null;
            nama = new String[jml];
            total = null;
            total = new double[jml];
            bagian_rs = null;
            bagian_rs = new double[jml];
            bhp = null;
            bhp = new double[jml];
            tarif_perujuk = null;
            tarif_perujuk = new double[jml];
            tarif_tindakan_dokter = null;
            tarif_tindakan_dokter = new double[jml];
            tarif_tindakan_petugas = null;
            tarif_tindakan_petugas = new double[jml];
            kso = null;
            kso = new double[jml];
            menejemen = null;
            menejemen = new double[jml];

            index = 0;
            for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbPemeriksaan.getValueAt(i, 1).toString();
                    nama[index] = tbPemeriksaan.getValueAt(i, 2).toString();
                    total[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 3).toString());
                    bagian_rs[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 4).toString());
                    bhp[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 5).toString());
                    tarif_perujuk[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 6).toString());
                    tarif_tindakan_dokter[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 7).toString());
                    tarif_tindakan_petugas[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 8).toString());
                    kso[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 9).toString());
                    menejemen[index] = Double.parseDouble(tbPemeriksaan.getValueAt(i, 10).toString());
                    index++;
                }
            }

            Valid.tabelKosong(tabMode);
            for (i = 0; i < jml; i++) {
                tabMode.addRow(new Object[]{pilih[i], kode[i], nama[i], total[i], bagian_rs[i], bhp[i], tarif_perujuk[i], tarif_tindakan_dokter[i], tarif_tindakan_petugas[i], kso[i], menejemen[i]});
            }

            if (cara_bayar_radiologi.equals("Yes") && kelas_radiologi.equals("No")) {
                pspemeriksaan = koneksi.prepareStatement(
                        "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
                        + "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
                        + "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
                        + "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
                        + "from jns_perawatan_radiologi inner join penjab inner join permintaan_pemeriksaan_radiologi on penjab.kd_pj=jns_perawatan_radiologi.kd_pj and jns_perawatan_radiologi.kd_jenis_prw=permintaan_pemeriksaan_radiologi.kd_jenis_prw where "
                        + " permintaan_pemeriksaan_radiologi.noorder=? and jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kd_pj=? or jns_perawatan_radiologi.kd_pj='-') and jns_perawatan_radiologi.kd_jenis_prw like ? or "
                        + " permintaan_pemeriksaan_radiologi.noorder=? and jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kd_pj=? or jns_perawatan_radiologi.kd_pj='-') and jns_perawatan_radiologi.nm_perawatan like ? "
                        + "order by jns_perawatan_radiologi.kd_jenis_prw");
            } else if (cara_bayar_radiologi.equals("No") && kelas_radiologi.equals("No")) {
                pspemeriksaan2 = koneksi.prepareStatement(
                        "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
                        + "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
                        + "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
                        + "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
                        + "from jns_perawatan_radiologi inner join penjab inner join permintaan_pemeriksaan_radiologi on penjab.kd_pj=jns_perawatan_radiologi.kd_pj and jns_perawatan_radiologi.kd_jenis_prw=permintaan_pemeriksaan_radiologi.kd_jenis_prw where "
                        + " permintaan_pemeriksaan_radiologi.noorder=? and jns_perawatan_radiologi.status='1' and jns_perawatan_radiologi.kd_jenis_prw like ? or "
                        + " permintaan_pemeriksaan_radiologi.noorder=? and jns_perawatan_radiologi.status='1' and jns_perawatan_radiologi.nm_perawatan like ?  "
                        + "order by jns_perawatan_radiologi.kd_jenis_prw");
            } else if (cara_bayar_radiologi.equals("Yes") && kelas_radiologi.equals("Yes")) {
                pspemeriksaan3 = koneksi.prepareStatement(
                        "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
                        + "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
                        + "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
                        + "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
                        + "from jns_perawatan_radiologi inner join penjab inner join permintaan_pemeriksaan_radiologi on penjab.kd_pj=jns_perawatan_radiologi.kd_pj and jns_perawatan_radiologi.kd_jenis_prw=permintaan_pemeriksaan_radiologi.kd_jenis_prw where "
                        + " permintaan_pemeriksaan_radiologi.noorder=? and jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kd_pj=? or jns_perawatan_radiologi.kd_pj='-') and (jns_perawatan_radiologi.kelas=? or jns_perawatan_radiologi.kelas='-') and jns_perawatan_radiologi.kd_jenis_prw like ? or "
                        + " permintaan_pemeriksaan_radiologi.noorder=? and jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kd_pj=? or jns_perawatan_radiologi.kd_pj='-') and (jns_perawatan_radiologi.kelas=? or jns_perawatan_radiologi.kelas='-') and jns_perawatan_radiologi.nm_perawatan like ? "
                        + "order by jns_perawatan_radiologi.kd_jenis_prw");
            } else if (cara_bayar_radiologi.equals("No") && kelas_radiologi.equals("Yes")) {
                pspemeriksaan4 = koneksi.prepareStatement(
                        "select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,jns_perawatan_radiologi.total_byr,"
                        + "jns_perawatan_radiologi.bagian_rs,jns_perawatan_radiologi.bhp,jns_perawatan_radiologi.tarif_perujuk,"
                        + "jns_perawatan_radiologi.tarif_tindakan_dokter,jns_perawatan_radiologi.tarif_tindakan_petugas,"
                        + "jns_perawatan_radiologi.kso,jns_perawatan_radiologi.menejemen,penjab.png_jawab "
                        + "from jns_perawatan_radiologi inner join penjab inner join permintaan_pemeriksaan_radiologi on penjab.kd_pj=jns_perawatan_radiologi.kd_pj and jns_perawatan_radiologi.kd_jenis_prw=permintaan_pemeriksaan_radiologi.kd_jenis_prw where "
                        + " permintaan_pemeriksaan_radiologi.noorder=? and jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kelas=? or jns_perawatan_radiologi.kelas='-') and jns_perawatan_radiologi.kd_jenis_prw like ? or "
                        + " permintaan_pemeriksaan_radiologi.noorder=? and jns_perawatan_radiologi.status='1' and (jns_perawatan_radiologi.kelas=? or jns_perawatan_radiologi.kelas='-') and jns_perawatan_radiologi.nm_perawatan like ?  "
                        + "order by jns_perawatan_radiologi.kd_jenis_prw");
            }
            try {
                if (cara_bayar_radiologi.equals("Yes") && kelas_radiologi.equals("No")) {
                    pspemeriksaan.setString(1, order);
                    pspemeriksaan.setString(2, kdPenjab);
                    pspemeriksaan.setString(3, "%" + TCariPeriksa.getText().trim() + "%");
                    pspemeriksaan.setString(4, order);
                    pspemeriksaan.setString(5, kdPenjab);
                    pspemeriksaan.setString(6, "%" + TCariPeriksa.getText().trim() + "%");
                    rs = pspemeriksaan.executeQuery();
                } else if (cara_bayar_radiologi.equals("No") && kelas_radiologi.equals("No")) {
                    pspemeriksaan2.setString(1, order);
                    pspemeriksaan2.setString(2, "%" + TCariPeriksa.getText().trim() + "%");
                    pspemeriksaan2.setString(3, order);
                    pspemeriksaan2.setString(4, "%" + TCariPeriksa.getText().trim() + "%");
                    rs = pspemeriksaan2.executeQuery();
                } else if (cara_bayar_radiologi.equals("Yes") && kelas_radiologi.equals("Yes")) {
                    pspemeriksaan3.setString(1, order);
                    pspemeriksaan3.setString(2, kdPenjab);
                    pspemeriksaan3.setString(3, kelas.trim());
                    pspemeriksaan3.setString(4, "%" + TCariPeriksa.getText().trim() + "%");
                    pspemeriksaan3.setString(5, order);
                    pspemeriksaan3.setString(6, kdPenjab);
                    pspemeriksaan3.setString(7, kelas.trim());
                    pspemeriksaan3.setString(8, "%" + TCariPeriksa.getText().trim() + "%");
                    rs = pspemeriksaan3.executeQuery();
                } else if (cara_bayar_radiologi.equals("No") && kelas_radiologi.equals("Yes")) {
                    pspemeriksaan4.setString(1, order);
                    pspemeriksaan4.setString(2, kelas.trim());
                    pspemeriksaan4.setString(3, "%" + TCariPeriksa.getText().trim() + "%");
                    pspemeriksaan4.setString(4, order);
                    pspemeriksaan4.setString(5, kelas.trim());
                    pspemeriksaan4.setString(6, "%" + TCariPeriksa.getText().trim() + "%");
                    rs = pspemeriksaan4.executeQuery();
                }

                while (rs.next()) {
                    tabMode.addRow(new Object[]{false, rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi 1 : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pspemeriksaan != null) {
                    pspemeriksaan.close();
                }
                if (pspemeriksaan2 != null) {
                    pspemeriksaan2.close();
                }
                if (pspemeriksaan3 != null) {
                    pspemeriksaan3.close();
                }
                if (pspemeriksaan4 != null) {
                    pspemeriksaan4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi 2 : " + e);
        }
    }

}
