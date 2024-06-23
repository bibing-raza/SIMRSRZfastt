/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */
package keuangan;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPenanggungJawab;

/**
 *
 * @author perpustakaan
 */
public final class DlgPembayaranRalan extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private ResultSet rs, rs2;
    private DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    private double all = 0, Laborat = 0, Radiologi = 0, Obat = 0, Ralan_Dokter = 0, Ralan_Dokter_paramedis = 0, Ralan_Paramedis = 0, Tambahan = 0, Potongan = 0, Registrasi = 0,
            ttlLaborat = 0, ttlRadiologi = 0, ttlObat = 0, ttlRalan_Dokter = 0, ttlRalan_Paramedis = 0, ttlTambahan = 0, ttlPotongan = 0, ttlRegistrasi = 0,
            Operasi = 0, ttlOperasi = 0;
    private String Keterangan = "Belum Lunas", urutanData = "", dialog_simpan = "";

    /**
     * Creates new form DlgLhtBiaya
     *
     * @param parent
     * @param modal
     */
    public DlgPembayaranRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        Object[] rowRwJlDr = {"Tanggal", "No.Nota", "Nama Pasien", "Jenis Bayar", "Perujuk",
            "Registrasi", "Obat+Emb+Tsl", "Paket Tindakan", "Operasi",
            "Laborat", "Radiologi", "Tambahan", "Potongan",
            "Total", "Dokter", "Keterangan"};
        tabMode = new DefaultTableModel(null, rowRwJlDr) {
            @Override public boolean isCellEditable(int rowIndex, int colIndex) {return false;}
        };
        tbBangsal.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 16; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(103);
            } else if (i == 2) {
                column.setPreferredWidth(170);
            } else if (i == 3) {
                column.setPreferredWidth(85);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(85);
            } else if (i == 7) {
                column.setPreferredWidth(85);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(85);
            } else if (i == 12) {
                column.setPreferredWidth(85);
            } else if (i == 13) {
                column.setPreferredWidth(85);
            } else if (i == 14) {
                column.setPreferredWidth(200);
            } else if (i == 15) {
                column.setPreferredWidth(85);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte) 20).getKata(TKd));

        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penjab.getTable().getSelectedRow() != -1) {
                    kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                    nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                    tampil();
                }
                kdpenjab.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                penjab.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    penjab.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, rp.tgl_registrasi, d.nm_dokter, pj.png_jawab "
                    + "FROM reg_periksa rp INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter WHERE "
                    + "rp.status_lanjut = 'Ralan' AND rp.no_rawat NOT IN (SELECT no_rawat FROM piutang_pasien WHERE no_rawat = rp.no_rawat) "
                    + "AND rp.tgl_registrasi BETWEEN ? AND ? AND rp.kd_pj LIKE ? ORDER BY rp.kd_dokter, rp.tgl_registrasi");
            ps2 = koneksi.prepareStatement(
                    "select nm_perawatan, totalbiaya, status from billing where no_nota=? and no_rawat=?");
        } catch (SQLException e) {
            System.out.println(e);
        }

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPembayaranRalan")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        cekSemuaPoli.setSelected(false);
                        tampil();
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

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnBilling = new javax.swing.JMenuItem();
        MnUrutanData = new javax.swing.JMenu();
        MnDokter = new javax.swing.JMenuItem();
        MnTglTagihan = new javax.swing.JMenuItem();
        MnRekapPenerimaanPoliSdhByr = new javax.swing.JMenuItem();
        MnRekapPenerimaanPoliSdhByrPerTgl = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label17 = new widget.Label();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        label19 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        cekSemuaPoli = new widget.CekBox();
        jLabel9 = new widget.Label();
        cmbBayar = new widget.ComboBox();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        panelGlass5 = new widget.panelisi();
        BtnAll = new widget.Button();
        BtnCari1 = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel10 = new javax.swing.JLabel();
        LCount = new javax.swing.JLabel();
        BtnKeluar = new widget.Button();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnBilling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBilling.setText("Billing/Pembayaran Pasien");
        MnBilling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBilling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBilling.setName("MnBilling"); // NOI18N
        MnBilling.setPreferredSize(new java.awt.Dimension(265, 26));
        MnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBillingActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBilling);

        MnUrutanData.setBackground(new java.awt.Color(248, 253, 243));
        MnUrutanData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutanData.setText("Urutkan Data Berdasarkan");
        MnUrutanData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutanData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutanData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutanData.setIconTextGap(5);
        MnUrutanData.setName("MnUrutanData"); // NOI18N
        MnUrutanData.setOpaque(true);
        MnUrutanData.setPreferredSize(new java.awt.Dimension(265, 26));

        MnDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokter.setText("Nama Dokter");
        MnDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokter.setIconTextGap(5);
        MnDokter.setName("MnDokter"); // NOI18N
        MnDokter.setPreferredSize(new java.awt.Dimension(120, 26));
        MnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterActionPerformed(evt);
            }
        });
        MnUrutanData.add(MnDokter);

        MnTglTagihan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTglTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTglTagihan.setText("Tgl. Tagihan");
        MnTglTagihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTglTagihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTglTagihan.setIconTextGap(5);
        MnTglTagihan.setName("MnTglTagihan"); // NOI18N
        MnTglTagihan.setPreferredSize(new java.awt.Dimension(120, 26));
        MnTglTagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTglTagihanActionPerformed(evt);
            }
        });
        MnUrutanData.add(MnTglTagihan);

        jPopupMenu1.add(MnUrutanData);

        MnRekapPenerimaanPoliSdhByr.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapPenerimaanPoliSdhByr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapPenerimaanPoliSdhByr.setText("Rekap Penerimaan Poliklinik (Sudah Bayar)");
        MnRekapPenerimaanPoliSdhByr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapPenerimaanPoliSdhByr.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapPenerimaanPoliSdhByr.setName("MnRekapPenerimaanPoliSdhByr"); // NOI18N
        MnRekapPenerimaanPoliSdhByr.setPreferredSize(new java.awt.Dimension(265, 26));
        MnRekapPenerimaanPoliSdhByr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapPenerimaanPoliSdhByrActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRekapPenerimaanPoliSdhByr);

        MnRekapPenerimaanPoliSdhByrPerTgl.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapPenerimaanPoliSdhByrPerTgl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapPenerimaanPoliSdhByrPerTgl.setText("Rekap Penerimaan Poli PerTgl. (Sudah Bayar)");
        MnRekapPenerimaanPoliSdhByrPerTgl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapPenerimaanPoliSdhByrPerTgl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapPenerimaanPoliSdhByrPerTgl.setName("MnRekapPenerimaanPoliSdhByrPerTgl"); // NOI18N
        MnRekapPenerimaanPoliSdhByrPerTgl.setPreferredSize(new java.awt.Dimension(265, 26));
        MnRekapPenerimaanPoliSdhByrPerTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapPenerimaanPoliSdhByrPerTglActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRekapPenerimaanPoliSdhByrPerTgl);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Pembayaran Pasien Ralan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(420, 100));
        panelisi4.setLayout(null);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl.Tagihan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(label11);
        label11.setBounds(0, 10, 100, 23);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tgl1MouseClicked(evt);
            }
        });
        panelisi4.add(Tgl1);
        Tgl1.setBounds(106, 10, 100, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(27, 23));
        panelisi4.add(label18);
        label18.setBounds(210, 10, 27, 23);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tgl2ActionPerformed(evt);
            }
        });
        panelisi4.add(Tgl2);
        Tgl2.setBounds(240, 10, 100, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Jenis Bayar : ");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(75, 23));
        panelisi4.add(label17);
        label17.setBounds(340, 10, 80, 23);

        kdpenjab.setEditable(false);
        kdpenjab.setForeground(new java.awt.Color(0, 0, 0));
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.setPreferredSize(new java.awt.Dimension(70, 23));
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });
        panelisi4.add(kdpenjab);
        kdpenjab.setBounds(423, 10, 70, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setForeground(new java.awt.Color(0, 0, 0));
        nmpenjab.setName("nmpenjab"); // NOI18N
        nmpenjab.setPreferredSize(new java.awt.Dimension(203, 23));
        panelisi4.add(nmpenjab);
        nmpenjab.setBounds(498, 10, 203, 23);

        BtnSeek2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        panelisi4.add(BtnSeek2);
        BtnSeek2.setBounds(702, 10, 28, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Unit/Poliklinik :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label19);
        label19.setBounds(0, 38, 100, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(55, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        panelisi4.add(kdpoli);
        kdpoli.setBounds(106, 38, 55, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(203, 23));
        panelisi4.add(TPoli);
        TPoli.setBounds(165, 38, 360, 23);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelisi4.add(BtnUnit);
        BtnUnit.setBounds(527, 38, 28, 23);

        cekSemuaPoli.setBackground(new java.awt.Color(255, 255, 250));
        cekSemuaPoli.setBorder(null);
        cekSemuaPoli.setForeground(new java.awt.Color(0, 0, 0));
        cekSemuaPoli.setText("Semua Poli/Unit");
        cekSemuaPoli.setBorderPainted(true);
        cekSemuaPoli.setBorderPaintedFlat(true);
        cekSemuaPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekSemuaPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekSemuaPoli.setName("cekSemuaPoli"); // NOI18N
        cekSemuaPoli.setOpaque(false);
        cekSemuaPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekSemuaPoliActionPerformed(evt);
            }
        });
        panelisi4.add(cekSemuaPoli);
        cekSemuaPoli.setBounds(230, 66, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jenis Transaksi :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(90, 30));
        panelisi4.add(jLabel9);
        jLabel9.setBounds(0, 66, 100, 23);

        cmbBayar.setForeground(new java.awt.Color(0, 0, 0));
        cmbBayar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEMUA", "SUDAH BAYAR", "BELUM BAYAR" }));
        cmbBayar.setName("cmbBayar"); // NOI18N
        cmbBayar.setPreferredSize(new java.awt.Dimension(120, 20));
        cmbBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbBayarMouseClicked(evt);
            }
        });
        cmbBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbBayarKeyPressed(evt);
            }
        });
        panelisi4.add(cmbBayar);
        cmbBayar.setBounds(106, 66, 120, 23);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBangsal.setComponentPopupMenu(jPopupMenu1);
        tbBangsal.setName("tbBangsal"); // NOI18N
        tbBangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsalMouseClicked(evt);
            }
        });
        tbBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBangsal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass5.add(BtnAll);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+2");
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
        panelGlass5.add(BtnCari1);

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
        panelGlass5.add(BtnPrint);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Total Pembayaran : Rp.");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(125, 23));
        panelGlass5.add(jLabel10);

        LCount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LCount.setForeground(new java.awt.Color(0, 0, 153));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(300, 23));
        panelGlass5.add(LCount);

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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            for (int r = 0; r < tabMode.getRowCount(); r++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode.getValueAt(r, 0).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 1).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 2).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 3).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 4).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 5).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 6).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 7).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 8).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 9).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 10).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 11).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 12).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 13).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 14).toString().replaceAll("'", "`") + "','"
                        + tabMode.getValueAt(r, 15).toString().replaceAll("'", "`") + "','','','','','','','','','','','','','','','','','','','','',''", "data");
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
            param.put("periode", "PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
                        
            if (!kdpoli.getText().equals("") && (!nmpenjab.getText().equals(""))) {
                param.put("caraBayarPoli", "UNIT/POLIKLINIK : " + TPoli.getText() + " CARA BAYAR : " + nmpenjab.getText());
            } else if (!kdpoli.getText().equals("") && (nmpenjab.getText().equals(""))) {
                param.put("caraBayarPoli", "UNIT/POLIKLINIK : " + TPoli.getText() + " CARA BAYAR : SEMUANYA");
            } else if (kdpoli.getText().equals("") && (!nmpenjab.getText().equals(""))) {
                param.put("caraBayarPoli", "SEMUA UNIT/POLIKLINIK CARA BAYAR : " + nmpenjab.getText());
            } else if (kdpoli.getText().equals("") && (nmpenjab.getText().equals(""))) {
                param.put("caraBayarPoli", "SEMUA UNIT/POLIKLINIK DAN SEMUA CARA BAYAR");
            }
            Valid.MyReport("rptRTagihanRalan.jasper", "report", "::[ Rekap Tagihan Ralan Masuk ]::",
                    "select * from temporary order by no asc", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, Tgl2, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnKeluar, TKd);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbBangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsalMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBangsalMouseClicked

    private void tbBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsalKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBangsalKeyPressed

private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
    tampil();
}//GEN-LAST:event_BtnCari1ActionPerformed

private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    } else {
        Valid.pindah(evt, TKd, BtnPrint);
    }
}//GEN-LAST:event_BtnCari1KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        urutanData = "";
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());
            BtnAll.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());
            Tgl2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeek2ActionPerformed(null);
        }
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setAlwaysOnTop(false);
        penjab.setVisible(true);
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek2KeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        kdpenjab.setText("");
        nmpenjab.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, kdpenjab, BtnPrint);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void MnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingActionPerformed
        if (TKd.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            DlgBilingRalan billing = new DlgBilingRalan(null, false);
            billing.TNoRw.setText(Sequel.cariIsi("select no_rawat from nota_jalan where no_nota=?", TKd.getText()));
            billing.isRawat();
            billing.isCek();
            if (Sequel.cariInteger("select count(no_rawat) from piutang_pasien where no_rawat=?", billing.TNoRw.getText()) > 0) {
                billing.setPiutang();
            }
            billing.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            billing.setLocationRelativeTo(internalFrame1);
            billing.setVisible(true);
        }
    }//GEN-LAST:event_MnBillingActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            //            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            //            BtnUnitActionPerformed(null);
        } else {
            //            Valid.pindah(evt, kddokter, TNoRM);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgPembayaranRalan");

        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void cekSemuaPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekSemuaPoliActionPerformed
        if (cekSemuaPoli.isSelected() == true) {
            kdpoli.setText("");
            TPoli.setText("");
        }
    }//GEN-LAST:event_cekSemuaPoliActionPerformed

    private void Tgl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tgl1MouseClicked
        Tgl1.setEditable(false);
    }//GEN-LAST:event_Tgl1MouseClicked

    private void Tgl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tgl2ActionPerformed
        Tgl2.setEditable(false);
    }//GEN-LAST:event_Tgl2ActionPerformed

    private void cmbBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbBayarMouseClicked
        cmbBayar.setEditable(false);
    }//GEN-LAST:event_cmbBayarMouseClicked

    private void cmbBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBayarKeyPressed
        cmbBayar.setEditable(false);
    }//GEN-LAST:event_cmbBayarKeyPressed

    private void MnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterActionPerformed
        urutanData = "";
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else {
            urutanData = "order by dokter.nm_dokter";
            tampil();
        }
    }//GEN-LAST:event_MnDokterActionPerformed

    private void MnTglTagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTglTagihanActionPerformed
        urutanData = "";
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else {
            urutanData = "order by reg_periksa.tgl_registrasi";
            tampil();
        }
    }//GEN-LAST:event_MnTglTagihanActionPerformed

    private void MnRekapPenerimaanPoliSdhByrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapPenerimaanPoliSdhByrActionPerformed
        if (kdpenjab.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis bayarnya...!!!!");
            kdpenjab.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT a.blnreg 'Bulan', a.nm_poli 'Poliklinik/Inst.', b.jlh_px 'Jlh. Pasien', a.jlh_penrimaan 'Jlh. Penerimaan' "
                    + "from ((SELECT rp.tgl_registrasi, date_format(rp.tgl_registrasi,'%M') blnreg, pl.nm_poli, rp.kd_poli, "
                    + "sum(dn.besar_bayar) jlh_penrimaan, CONVERT(date_format(rp.tgl_registrasi,'%m'),INT) bln FROM reg_periksa rp "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                    + "INNER JOIN detail_nota_jalan dn ON dn.no_rawat = rp.no_rawat "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and rp.kd_pj='" + kdpenjab.getText() + "' GROUP BY rp.kd_pj, rp.kd_poli, MONTH(rp.tgl_registrasi)) as a "
                    + "left join "
                    + "(SELECT rp.tgl_registrasi, COUNT(rp.no_rkm_medis) jlh_px, rp.kd_poli, date_format(rp.tgl_registrasi,'%M') blnreg FROM reg_periksa rp "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and rp.kd_pj='" + kdpenjab.getText() + "' GROUP BY rp.kd_pj, rp.kd_poli, MONTH(rp.tgl_registrasi)) as b "
                    + "on b.kd_poli = a.kd_poli and a.blnreg = b.blnreg) "
                    + "order by a.bln, a.blnreg, a.nm_poli", dialog_simpan);
            JOptionPane.showMessageDialog(null, "Total Penerimaan Rawat Jalan Perbulan berhasil diexport menjadi file excel,..!!!");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRekapPenerimaanPoliSdhByrActionPerformed

    private void MnRekapPenerimaanPoliSdhByrPerTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapPenerimaanPoliSdhByrPerTglActionPerformed
        if (kdpenjab.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis bayarnya...!!!!");
            kdpenjab.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT a.tglreg 'Tgl. Penerimaan', a.nm_poli 'Poliklinik/Inst.', b.jlh_px 'Jlh. Pasien', a.jlh_penrimaan 'Jlh. Penerimaan' "
                    + "from ((SELECT rp.tgl_registrasi, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglreg, pl.nm_poli, rp.kd_poli, "
                    + "sum(dn.besar_bayar) jlh_penrimaan, CONVERT(date_format(rp.tgl_registrasi,'%m'),INT) bln FROM reg_periksa rp INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                    + "INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli INNER JOIN detail_nota_jalan dn ON dn.no_rawat = rp.no_rawat "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and rp.kd_pj='" + kdpenjab.getText() + "' GROUP BY rp.kd_pj, rp.kd_poli, rp.tgl_registrasi) as a left join "
                    + "(SELECT rp.tgl_registrasi, COUNT(rp.no_rkm_medis) jlh_px, rp.kd_poli FROM reg_periksa rp INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                    + "INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli WHERE rp.status_lanjut = 'Ralan' AND "
                    + "rp.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and rp.kd_pj='" + kdpenjab.getText() + "' GROUP BY rp.kd_pj, rp.kd_poli, rp.tgl_registrasi) as b on b.kd_poli=a.kd_poli and "
                    + "a.tgl_registrasi=b.tgl_registrasi) order by a.bln, a.tglreg, a.nm_poli", dialog_simpan);
            JOptionPane.showMessageDialog(null, "Total Penerimaan Rawat Jalan PerTanggal berhasil diexport menjadi file excel,..!!!");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRekapPenerimaanPoliSdhByrPerTglActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPembayaranRalan dialog = new DlgPembayaranRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek2;
    private widget.Button BtnUnit;
    private javax.swing.JLabel LCount;
    private javax.swing.JMenuItem MnBilling;
    private javax.swing.JMenuItem MnDokter;
    private javax.swing.JMenuItem MnRekapPenerimaanPoliSdhByr;
    private javax.swing.JMenuItem MnRekapPenerimaanPoliSdhByrPerTgl;
    private javax.swing.JMenuItem MnTglTagihan;
    private javax.swing.JMenu MnUrutanData;
    private widget.ScrollPane Scroll;
    private widget.TextBox TKd;
    private widget.TextBox TPoli;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.CekBox cekSemuaPoli;
    private widget.ComboBox cmbBayar;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel10;
    private widget.Label jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdpenjab;
    private widget.TextBox kdpoli;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.TextBox nmpenjab;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelisi4;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.tabelKosong(tabMode);
        try {
            if (cmbBayar.getSelectedItem().equals("SEMUA")) {
                if (kdpoli.getText().equals("")) {
                    ps = koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,reg_periksa.tgl_registrasi,dokter.nm_dokter,penjab.png_jawab,ifnull(nota_jalan.no_nota,'-') no_nota "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                            + "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                            + "left join nota_jalan on nota_jalan.no_rawat = reg_periksa.no_rawat "
                            + "where reg_periksa.status_lanjut='Ralan' and "
                            + "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "
                            + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj like ? " + urutanData + "");
                } else {
                    ps = koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,reg_periksa.tgl_registrasi,dokter.nm_dokter,penjab.png_jawab,ifnull(nota_jalan.no_nota,'-') no_nota "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                            + "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                            + "left join nota_jalan on nota_jalan.no_rawat = reg_periksa.no_rawat "
                            + "where reg_periksa.status_lanjut='Ralan' and "
                            + "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "
                            + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj like ? and reg_periksa.kd_poli=? " + urutanData + "");
                }

                if (kdpoli.getText().equals("")) {
                    ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                    ps.setString(3, "%" + kdpenjab.getText() + "%");
                } else {
                    ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                    ps.setString(3, "%" + kdpenjab.getText() + "%");
                    ps.setString(4, kdpoli.getText());
                }

            } else if (cmbBayar.getSelectedItem().equals("SUDAH BAYAR")) {
                if (kdpoli.getText().equals("")) {
                    ps = koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,reg_periksa.tgl_registrasi,dokter.nm_dokter,penjab.png_jawab,ifnull(nota_jalan.no_nota,'-') no_nota "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                            + "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                            + "left join nota_jalan on nota_jalan.no_rawat = reg_periksa.no_rawat "
                            + "where reg_periksa.status_lanjut='Ralan' and ifnull(nota_jalan.no_nota,'-') <> '-' and "
                            + "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "
                            + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj like ? " + urutanData + "");
                } else {
                    ps = koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,reg_periksa.tgl_registrasi,dokter.nm_dokter,penjab.png_jawab,ifnull(nota_jalan.no_nota,'-') no_nota "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                            + "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                            + "left join nota_jalan on nota_jalan.no_rawat = reg_periksa.no_rawat "
                            + "where reg_periksa.status_lanjut='Ralan' and ifnull(nota_jalan.no_nota,'-') <> '-' and "
                            + "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "
                            + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj like ? and reg_periksa.kd_poli=? " + urutanData + "");
                }

                if (kdpoli.getText().equals("")) {
                    ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                    ps.setString(3, "%" + kdpenjab.getText() + "%");
                } else {
                    ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                    ps.setString(3, "%" + kdpenjab.getText() + "%");
                    ps.setString(4, kdpoli.getText());
                }
            } else if (cmbBayar.getSelectedItem().equals("BELUM BAYAR")) {
                if (kdpoli.getText().equals("")) {
                    ps = koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,reg_periksa.tgl_registrasi,dokter.nm_dokter,penjab.png_jawab,ifnull(nota_jalan.no_nota,'-') no_nota "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                            + "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                            + "left join nota_jalan on nota_jalan.no_rawat = reg_periksa.no_rawat "
                            + "where reg_periksa.status_lanjut='Ralan' and ifnull(nota_jalan.no_nota,'-') = '-' and "
                            + "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "
                            + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj like ? " + urutanData + "");
                } else {
                    ps = koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,reg_periksa.tgl_registrasi,dokter.nm_dokter,penjab.png_jawab,ifnull(nota_jalan.no_nota,'-') no_nota "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                            + "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                            + "left join nota_jalan on nota_jalan.no_rawat = reg_periksa.no_rawat "
                            + "where reg_periksa.status_lanjut='Ralan' and ifnull(nota_jalan.no_nota,'-') = '-' and "
                            + "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "
                            + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj like ? and reg_periksa.kd_poli=? " + urutanData + "");
                }

                if (kdpoli.getText().equals("")) {
                    ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                    ps.setString(3, "%" + kdpenjab.getText() + "%");
                } else {
                    ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                    ps.setString(3, "%" + kdpenjab.getText() + "%");
                    ps.setString(4, kdpoli.getText());
                }
            }

            rs = ps.executeQuery();
            all = 0;
            ttlLaborat = 0;
            ttlRadiologi = 0;
            ttlOperasi = 0;
            ttlObat = 0;
            ttlRalan_Dokter = 0;
            ttlRalan_Paramedis = 0;
            ttlTambahan = 0;
            ttlPotongan = 0;
            ttlRegistrasi = 0;
            while (rs.next()) {
                Operasi = 0;
                Laborat = 0;
                Radiologi = 0;
                Obat = 0;
                Ralan_Dokter = 0;
                Ralan_Dokter_paramedis = 0;
                Ralan_Paramedis = 0;
                Tambahan = 0;
                Potongan = 0;
                Registrasi = 0;
                Keterangan = "Belum Bayar";

                ps2.setString(1, rs.getString("no_nota"));
                ps2.setString(2, rs.getString("no_rawat"));
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    switch (rs2.getString("status")) {
                        case "Laborat":
                            ttlLaborat = ttlLaborat + rs2.getDouble("totalbiaya");
                            Laborat = Laborat + rs2.getDouble("totalbiaya");
                            break;
                        case "Radiologi":
                            ttlRadiologi = ttlRadiologi + rs2.getDouble("totalbiaya");
                            Radiologi = Radiologi + rs2.getDouble("totalbiaya");
                            break;
                        case "Obat":
                            ttlObat = ttlObat + rs2.getDouble("totalbiaya");
                            Obat = Obat + rs2.getDouble("totalbiaya");
                            break;
                        case "Ralan Dokter":
                            ttlRalan_Dokter = ttlRalan_Dokter + rs2.getDouble("totalbiaya");
                            Ralan_Dokter = Ralan_Dokter + rs2.getDouble("totalbiaya");
                            break;
                        case "Ralan Dokter Paramedis":
                            ttlRalan_Dokter = ttlRalan_Dokter + rs2.getDouble("totalbiaya");
                            Ralan_Dokter_paramedis = Ralan_Dokter_paramedis + rs2.getDouble("totalbiaya");
                            break;
                        case "Ralan Paramedis":
                            ttlRalan_Paramedis = ttlRalan_Paramedis + rs2.getDouble("totalbiaya");
                            Ralan_Paramedis = Ralan_Paramedis + rs2.getDouble("totalbiaya");
                            break;
                        case "Tambahan":
                            ttlTambahan = ttlTambahan + rs2.getDouble("totalbiaya");
                            Tambahan = Tambahan + rs2.getDouble("totalbiaya");
                            break;
                        case "Potongan":
                            ttlPotongan = ttlPotongan + rs2.getDouble("totalbiaya");
                            Potongan = Potongan + rs2.getDouble("totalbiaya");
                            break;
                        case "Registrasi":
                            ttlRegistrasi = ttlRegistrasi + rs2.getDouble("totalbiaya");
                            Registrasi = Registrasi + rs2.getDouble("totalbiaya");
                            break;
                        case "Operasi":
                            ttlOperasi = ttlOperasi + rs2.getDouble("totalbiaya");
                            Operasi = Operasi + rs2.getDouble("totalbiaya");
                            break;
                    }
                }
                all = all + Operasi + Laborat + Radiologi + Obat + Ralan_Dokter + Ralan_Dokter_paramedis + Ralan_Paramedis + Tambahan + Potongan + Registrasi;

                if ((Laborat + Operasi + Radiologi + Obat + Ralan_Dokter + Ralan_Paramedis + Ralan_Dokter_paramedis + Tambahan + Potongan + Registrasi) > 0) {
                    Keterangan = "Sudah Bayar";
                }

                tabMode.addRow(new Object[]{
                    rs.getString("tgl_registrasi"),
                    Sequel.cariIsi("select no_nota from nota_jalan where no_nota=?", rs.getString("no_nota")),
                    rs.getString("no_rkm_medis") + " " + rs.getString("nm_pasien"),
                    rs.getString("png_jawab"),
                    Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=?", rs.getString("no_rawat")),
                    Valid.SetAngka(Registrasi),
                    Valid.SetAngka(Obat),
                    Valid.SetAngka(Ralan_Dokter + Ralan_Paramedis + Ralan_Dokter_paramedis),
                    Valid.SetAngka(Operasi),
                    Valid.SetAngka(Laborat),
                    Valid.SetAngka(Radiologi),
                    Valid.SetAngka(Tambahan),
                    Valid.SetAngka(Potongan),
                    Valid.SetAngka(Operasi + Laborat + Radiologi + Obat + Ralan_Dokter + Ralan_Paramedis + Ralan_Dokter_paramedis + Tambahan + Potongan + Registrasi),
                    rs.getString("nm_dokter"), Keterangan
                });
            }
            tabMode.addRow(new Object[]{
                ">> Total", ":", "", "", "",
                Valid.SetAngka(ttlRegistrasi),
                Valid.SetAngka(ttlObat),
                Valid.SetAngka(ttlRalan_Dokter + ttlRalan_Paramedis),
                Valid.SetAngka(ttlOperasi),
                Valid.SetAngka(ttlLaborat),
                Valid.SetAngka(ttlRadiologi),
                Valid.SetAngka(ttlTambahan),
                Valid.SetAngka(ttlPotongan),
                Valid.SetAngka(ttlLaborat + ttlRadiologi + ttlObat + ttlRalan_Dokter + ttlRalan_Paramedis
                + ttlTambahan + ttlPotongan + ttlRegistrasi + ttlOperasi), "", ""
            });
            LCount.setText(Valid.SetAngka(all));
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void getData() {
        int row = tbBangsal.getSelectedRow();
        if (row != -1) {
            TKd.setText(tabMode.getValueAt(row, 1).toString());
        }
    }

    public void fokus() {
        kdpenjab.setText("U01");
        nmpenjab.setText("UMUM");
        kdpoli.setText("");
        TPoli.setText("");
        cekSemuaPoli.setSelected(true);
        Tgl1.requestFocus();
    }

}
