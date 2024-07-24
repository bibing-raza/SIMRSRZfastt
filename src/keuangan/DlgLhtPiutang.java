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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgPenanggungJawab;

/**
 *
 * @author perpustakaan
 */
public final class DlgLhtPiutang extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private double total = 0, piutang = 0, sisapiutang = 0, Tsisapiutang = 0, belumdibayar = 0, cicilan = 0;
    private PreparedStatement ps, ps1, ps2, psotomatis, psotomatis2;
    private ResultSet rs, rs1, rs2, rskasir;
    private int x = 0, i = 0, jml = 0;
    private boolean[] pilih;
    private String[] kode, caByr, sttsMOU;
    private String koderekening = "", wktsimpan = "", penjabPilih = "", penjabOK = "", bangsal = "", kodekmr = "", kodedok = "",
            sqlpsotomatis = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakandr,jns_perawatan.total_byrdr,jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan.kd_dokter=? and set_otomatis_tindakan_ralan.kd_pj=?",
            sqlpsotomatis2 = "insert into rawat_jl_dr values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatispetugas = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakanpr,jns_perawatan.total_byrpr,jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan_petugas "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan_petugas.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan_petugas.kd_pj=?",
            sqlpsotomatis2petugas = "insert into rawat_jl_pr values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatisdokterpetugas = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.total_byrdrpr,"
            + "jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan_dokterpetugas "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan_dokterpetugas.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan_dokterpetugas.kd_dokter=? and set_otomatis_tindakan_ralan_dokterpetugas.kd_pj=?",
            sqlpsotomatis2dokterpetugas = "insert into rawat_jl_drpr values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgLhtPiutang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        Object[] rowRwJlDr={
            "No.Rawat/No.tagihan", "Tgl.Piutang", "Pasien", "Status", "Total Piutang",
            "Uang Muka", "Dibayar", "Sisa Piutang", "Jatuh Tempo", "Cara Bayar", "tglpiutang",
            "tgltempo", "Penjamin Piutang", "Keterangan Penjamin", "status_lanjut"
        };
        
        tabMode=new DefaultTableModel(null,rowRwJlDr){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbPiutang.setModel(tabMode);
        tbPiutang.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPiutang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbPiutang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setPreferredWidth(170);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPiutang.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Tgl. Bayar", "No. RM", "Pasien", "Cicilan (Rp)", "Keterangan", "No. Tagihan",
            "tglbayar", "waktu_simpan", "Sisa Piutang (Rp)", "Penjamin Piutang", "Keterangan Penjamin"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBayar.setModel(tabMode1);
        tbBayar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBayar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 11; i++) {
            TableColumn column = tbBayar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(55);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(290);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(170);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            }
        }
        tbBayar.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Cek", "Kode", "Jenis Bayar / Penanggung Jawab", "Status MOU"
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
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbPenjab.setModel(tabMode2);
        tbPenjab.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPenjab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbPenjab.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(55);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            }
        }
        tbPenjab.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
        Cicilan.setDocument(new batasInput((byte) 15).getOnlyAngka(Cicilan));
        Keterangan.setDocument(new batasInput((byte) 100).getKata(Keterangan));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
            
            Cicilan.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang));
                    if (!Cicilan.getText().equals("")) {
                        Sisa.setText(Valid.SetAngka(sisapiutang - Double.parseDouble(Cicilan.getText())));
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang));
                    if (!Cicilan.getText().equals("")) {
                        Sisa.setText(Valid.SetAngka(sisapiutang - Double.parseDouble(Cicilan.getText())));
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang));
                    if (!Cicilan.getText().equals("")) {
                        Sisa.setText(Valid.SetAngka(sisapiutang - Double.parseDouble(Cicilan.getText())));
                    }
                }
            });
        }  
        
        Valid.loadCombo(nama_bayar,"nama_bayar","akun_bayar");
        
        try {
            ps1 = koneksi.prepareStatement("select bp.tgl_bayar, bp.no_rkm_medis, p.nm_pasien, bp.besar_cicilan,"
                    + "bp.catatan, bp.no_rawat, date_format(bp.tgl_bayar,'%d/%m/%Y') tglbayar, bp.waktu_simpan, "
                    +"bp.sisa_piutang, pp.penjamin, pp.ket_penjamin from bayar_piutang bp "
                    + "inner join pasien p on bp.no_rkm_medis=p.no_rkm_medis inner join piutang_pasien pp on pp.no_rawat=bp.no_rawat where "
                    + "bp.no_rawat like ? or "
                    + "bp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or "
                    + "pp.penjamin like ? or "
                    + "pp.ket_penjamin like ? or "
                    + "bp.tgl_bayar like ? order by bp.tgl_bayar desc, bp.no_rkm_medis");
        } catch (SQLException e) {
            System.out.println(e);
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

        TKd = new widget.TextBox();
        Popup = new javax.swing.JPopupMenu();
        ppNotaPiutang = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSemuanya = new javax.swing.JMenuItem();
        MnDibatalkan = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnBilling = new javax.swing.JMenuItem();
        MnDetailPiutang = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel12 = new widget.Label();
        cmbPenjamin = new widget.ComboBox();
        label21 = new widget.Label();
        cmbPiutang = new widget.ComboBox();
        BtnPrint = new widget.Button();
        TabPiutang = new javax.swing.JTabbedPane();
        internalFrame20 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPiutang = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label17 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new javax.swing.JLabel();
        LCount = new javax.swing.JLabel();
        BtnKeluar = new widget.Button();
        jLabel13 = new widget.Label();
        LCount1 = new widget.Label();
        panelGlass6 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbPenjab = new widget.Table();
        panelGlass7 = new widget.panelisi();
        label19 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnAll2 = new widget.Button();
        internalFrame21 = new widget.InternalFrame();
        panelisi5 = new widget.panelisi();
        label34 = new widget.Label();
        label32 = new widget.Label();
        NoRawat = new widget.TextBox();
        label36 = new widget.Label();
        Keterangan = new widget.TextBox();
        label35 = new widget.Label();
        Cicilan = new widget.TextBox();
        label16 = new widget.Label();
        TNoRM = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        label38 = new widget.Label();
        Sisa = new widget.TextBox();
        jLabel11 = new widget.Label();
        nama_bayar = new widget.ComboBox();
        label20 = new widget.Label();
        TotalPiutang = new widget.Label();
        ChkPelunasan = new widget.CekBox();
        Scroll1 = new widget.ScrollPane();
        tbBayar = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        label10 = new widget.Label();
        LCountBayar = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint1 = new widget.Button();
        label12 = new widget.Label();
        LTotalBayar = new widget.Label();
        BtnKeluar1 = new widget.Button();
        jLabel23 = new widget.Label();
        tglNota = new widget.Tanggal();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        Popup.setName("Popup"); // NOI18N

        ppNotaPiutang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppNotaPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppNotaPiutang.setText("Nota Bayar Piutang");
        ppNotaPiutang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppNotaPiutang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppNotaPiutang.setIconTextGap(8);
        ppNotaPiutang.setName("ppNotaPiutang"); // NOI18N
        ppNotaPiutang.setPreferredSize(new java.awt.Dimension(160, 25));
        ppNotaPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppNotaPiutangBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppNotaPiutang);

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSemuanya.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuanya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuanya.setText("Conteng Semuanya");
        MnSemuanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuanya.setIconTextGap(5);
        MnSemuanya.setName("MnSemuanya"); // NOI18N
        MnSemuanya.setPreferredSize(new java.awt.Dimension(185, 26));
        MnSemuanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuanyaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSemuanya);

        MnDibatalkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibatalkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDibatalkan.setText("Hilangkan Semua Conteng");
        MnDibatalkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibatalkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDibatalkan.setIconTextGap(5);
        MnDibatalkan.setName("MnDibatalkan"); // NOI18N
        MnDibatalkan.setPreferredSize(new java.awt.Dimension(185, 26));
        MnDibatalkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibatalkanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDibatalkan);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnBilling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBilling.setText("Billing/Pembayaran Pasien");
        MnBilling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBilling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBilling.setIconTextGap(5);
        MnBilling.setName("MnBilling"); // NOI18N
        MnBilling.setPreferredSize(new java.awt.Dimension(180, 26));
        MnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBillingActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnBilling);

        MnDetailPiutang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDetailPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDetailPiutang.setText("Detail Piutang");
        MnDetailPiutang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDetailPiutang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDetailPiutang.setIconTextGap(5);
        MnDetailPiutang.setName("MnDetailPiutang"); // NOI18N
        MnDetailPiutang.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDetailPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetailPiutangActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnDetailPiutang);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Tagihan Piutang Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Tagihan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi4.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(Tgl2);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Penjamin : ");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(jLabel12);

        cmbPenjamin.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenjamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Keluarga Pasien", "Pasien Sendiri", "Manajemen RSRAZA", "Karyawan RSRAZA", "Asuransi/Pihak 3", "Aparat Desa", "Anggota DPRD", "Ulama/Pemuka Agama", "Tokoh Masyarakat", "Pemerintah Daerah", "Dinas Sosial Kabupaten", "Dinas Kesehatan Kabupaten", "Pemerintah Provinsi", "Dinas Sosial Provinsi", "Dinas Kesehatan Provinsi", "Yayasan", "Panti Asuhan", "Panti Sosial", "Lain-lain" }));
        cmbPenjamin.setName("cmbPenjamin"); // NOI18N
        cmbPenjamin.setPreferredSize(new java.awt.Dimension(170, 23));
        cmbPenjamin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbPenjaminMouseReleased(evt);
            }
        });
        panelisi4.add(cmbPenjamin);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Jenis Piutang :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label21);

        cmbPiutang.setForeground(new java.awt.Color(0, 0, 0));
        cmbPiutang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Lunas", "Belum Lunas", "Semua Piutang" }));
        cmbPiutang.setName("cmbPiutang"); // NOI18N
        cmbPiutang.setPreferredSize(new java.awt.Dimension(108, 23));
        cmbPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPiutangActionPerformed(evt);
            }
        });
        cmbPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbPiutangKeyPressed(evt);
            }
        });
        panelisi4.add(cmbPiutang);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelisi4.add(BtnPrint);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        TabPiutang.setBackground(new java.awt.Color(254, 255, 254));
        TabPiutang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPiutang.setName("TabPiutang"); // NOI18N
        TabPiutang.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabPiutang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPiutangMouseClicked(evt);
            }
        });

        internalFrame20.setBorder(null);
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(900, 900));
        internalFrame20.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPiutang.setToolTipText("Silahkan klik untuk memilih data yang mau diperbaiki cara bayarnya");
        tbPiutang.setComponentPopupMenu(jPopupMenu2);
        tbPiutang.setName("tbPiutang"); // NOI18N
        tbPiutang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPiutangMouseClicked(evt);
            }
        });
        tbPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPiutangKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPiutang);

        internalFrame20.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Key Word :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass5.add(label17);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        panelGlass5.add(BtnCari);

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
        panelGlass5.add(BtnAll);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Belum Dibayar :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(jLabel10);

        LCount.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        LCount.setForeground(new java.awt.Color(153, 0, 51));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass5.add(LCount);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 23));
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

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Record :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(jLabel13);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(LCount1);

        internalFrame20.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        panelGlass6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Jenis Bayar / Penanggung Jawab Pasien :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(450, 402));
        panelGlass6.setLayout(new java.awt.BorderLayout());

        Scroll2.setBorder(null);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(380, 402));

        tbPenjab.setToolTipText("Silahkan conteng untuk memilih data yang dipilih");
        tbPenjab.setComponentPopupMenu(jPopupMenu1);
        tbPenjab.setName("tbPenjab"); // NOI18N
        Scroll2.setViewportView(tbPenjab);

        panelGlass6.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Key Word :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass7.add(label19);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(120, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelGlass7.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setToolTipText("Alt+2");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(30, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelGlass7.add(BtnCari2);

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('M');
        BtnAll2.setText("Semua Data");
        BtnAll2.setToolTipText("Alt+M");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        panelGlass7.add(BtnAll2);

        panelGlass6.add(panelGlass7, java.awt.BorderLayout.PAGE_END);

        internalFrame20.add(panelGlass6, java.awt.BorderLayout.EAST);

        TabPiutang.addTab("Data Tagihan", internalFrame20);

        internalFrame21.setBorder(null);
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setLayout(new java.awt.BorderLayout());

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 104));
        panelisi5.setLayout(null);

        label34.setForeground(new java.awt.Color(0, 0, 0));
        label34.setText("No. Rawat :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label34);
        label34.setBounds(0, 10, 77, 23);

        label32.setForeground(new java.awt.Color(0, 0, 0));
        label32.setText("Tanggal :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label32);
        label32.setBounds(0, 40, 77, 23);

        NoRawat.setEditable(false);
        NoRawat.setForeground(new java.awt.Color(0, 0, 0));
        NoRawat.setName("NoRawat"); // NOI18N
        NoRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRawatKeyPressed(evt);
            }
        });
        panelisi5.add(NoRawat);
        NoRawat.setBounds(80, 10, 170, 23);

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setText("Keterangan :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label36);
        label36.setBounds(301, 40, 80, 23);

        Keterangan.setForeground(new java.awt.Color(0, 0, 0));
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        panelisi5.add(Keterangan);
        Keterangan.setBounds(384, 40, 326, 23);

        label35.setForeground(new java.awt.Color(0, 0, 0));
        label35.setText("Cicilan :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label35);
        label35.setBounds(301, 70, 80, 23);

        Cicilan.setForeground(new java.awt.Color(0, 0, 0));
        Cicilan.setName("Cicilan"); // NOI18N
        Cicilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CicilanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CicilanKeyReleased(evt);
            }
        });
        panelisi5.add(Cicilan);
        Cicilan.setBounds(384, 70, 120, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Pasien :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi5.add(label16);
        label16.setBounds(301, 10, 80, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi5.add(TNoRM);
        TNoRM.setBounds(384, 10, 90, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        TNmPasien.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi5.add(TNmPasien);
        TNmPasien.setBounds(476, 10, 233, 23);

        Tanggal.setEditable(false);
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        panelisi5.add(Tanggal);
        Tanggal.setBounds(80, 40, 110, 23);

        label38.setForeground(new java.awt.Color(0, 0, 0));
        label38.setText("Sisa Piutang :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label38);
        label38.setBounds(510, 70, 77, 23);

        Sisa.setEditable(false);
        Sisa.setForeground(new java.awt.Color(0, 0, 0));
        Sisa.setName("Sisa"); // NOI18N
        panelisi5.add(Sisa);
        Sisa.setBounds(590, 70, 120, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Akun Bayar :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi5.add(jLabel11);
        jLabel11.setBounds(0, 70, 77, 23);

        nama_bayar.setForeground(new java.awt.Color(0, 0, 0));
        nama_bayar.setName("nama_bayar"); // NOI18N
        nama_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nama_bayarKeyPressed(evt);
            }
        });
        panelisi5.add(nama_bayar);
        nama_bayar.setBounds(80, 70, 170, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Total Piutang Keseluruhan : Rp.");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi5.add(label20);
        label20.setBounds(720, 10, 160, 23);

        TotalPiutang.setForeground(new java.awt.Color(0, 0, 0));
        TotalPiutang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotalPiutang.setText("t_piutang");
        TotalPiutang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TotalPiutang.setName("TotalPiutang"); // NOI18N
        TotalPiutang.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi5.add(TotalPiutang);
        TotalPiutang.setBounds(882, 10, 180, 23);

        ChkPelunasan.setBorder(null);
        ChkPelunasan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPelunasan.setText("Pelunasan Piutang");
        ChkPelunasan.setBorderPainted(true);
        ChkPelunasan.setBorderPaintedFlat(true);
        ChkPelunasan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPelunasan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPelunasan.setName("ChkPelunasan"); // NOI18N
        ChkPelunasan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPelunasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPelunasanActionPerformed(evt);
            }
        });
        panelisi5.add(ChkPelunasan);
        ChkPelunasan.setBounds(720, 70, 150, 23);

        internalFrame21.add(panelisi5, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbBayar.setAutoCreateRowSorter(true);
        tbBayar.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBayar.setComponentPopupMenu(Popup);
        tbBayar.setName("tbBayar"); // NOI18N
        tbBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBayarMouseClicked(evt);
            }
        });
        tbBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBayarKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbBayar);

        internalFrame21.add(Scroll1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label9);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi3.add(TCari1);

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
        panelisi3.add(BtnCari1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('1');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+1");
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
        panelisi3.add(BtnAll1);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label10);

        LCountBayar.setForeground(new java.awt.Color(0, 0, 0));
        LCountBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountBayar.setText("0");
        LCountBayar.setName("LCountBayar"); // NOI18N
        LCountBayar.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(LCountBayar);

        jPanel1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
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

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrint1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint1KeyPressed(evt);
            }
        });
        panelisi1.add(BtnPrint1);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Total :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(105, 30));
        panelisi1.add(label12);

        LTotalBayar.setForeground(new java.awt.Color(0, 0, 0));
        LTotalBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotalBayar.setText("0");
        LTotalBayar.setName("LTotalBayar"); // NOI18N
        LTotalBayar.setPreferredSize(new java.awt.Dimension(195, 30));
        panelisi1.add(LTotalBayar);

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
        panelisi1.add(BtnKeluar1);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Nota/Kwitansi : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi1.add(jLabel23);

        tglNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2024" }));
        tglNota.setDisplayFormat("dd-MM-yyyy");
        tglNota.setName("tglNota"); // NOI18N
        tglNota.setOpaque(false);
        panelisi1.add(tglNota);

        jPanel1.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame21.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        TabPiutang.addTab("Pembayaran Piutang", internalFrame21);

        internalFrame1.add(TabPiutang, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");            
        } else {
            if (cmbPiutang.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan plih dulu salah satu jenis piutangnya utk. dicetak...!!!!"); 
                cmbPiutang.requestFocus();
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Sequel.AutoComitFalse();
                Sequel.queryu("delete from temporary3");
                int row = tabMode.getRowCount();
                for (int i = 0; i < row; i++) {
                    Sequel.menyimpan("temporary3",
                            "'" + i + "','" + tabMode.getValueAt(i, 0).toString() + "','"
                            + tabMode.getValueAt(i, 1).toString() + "','"
                            + tabMode.getValueAt(i, 2).toString() + "','"
                            + tabMode.getValueAt(i, 3).toString() + "','"
                            + Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i, 4).toString())) + "','"
                            + Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i, 5).toString())) + "','"
                            + Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i, 6).toString())) + "','"
                            + Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i, 7).toString())) + "','"
                            + tabMode.getValueAt(i, 8).toString() + "','"
                            + tabMode.getValueAt(i, 12).toString() + "','"
                            + tabMode.getValueAt(i, 13).toString() + "','"
                            + tabMode.getValueAt(i, 9).toString() + "','"
                            + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Piutang Pasien");
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
                param.put("totalpiutang", LCount.getText());

                if (cmbPiutang.getSelectedIndex() == 1) {
                    param.put("judul", "REKAP TAGIHAN PASIEN (PIUTANG " + cmbPiutang.getSelectedItem().toString().toUpperCase() + ")");
                    Valid.MyReport("rptRPiutangMasuk.jasper", "report", "::[ Rekap Piutang Masuk ]::",
                            "select temp2, temp3, temp4, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13 from temporary3 where "
                            + "temp5='Lunas' order by CONVERT(temp1,int), temp3", param);
                } else if (cmbPiutang.getSelectedIndex() == 2) {
                    param.put("judul", "REKAP TAGIHAN PASIEN (PIUTANG " + cmbPiutang.getSelectedItem().toString().toUpperCase() + ")");
                    Valid.MyReport("rptRPiutangMasuk.jasper", "report", "::[ Rekap Piutang Masuk ]::",
                            "select temp2, temp3, temp4, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13 from temporary3 where "
                            + "temp5='Belum Lunas' order by CONVERT(temp1,int), temp3", param);
                } else if (cmbPiutang.getSelectedIndex() == 3) {
                    param.put("judul", "REKAP TAGIHAN PIUTANG PASIEN");
                    Valid.MyReport("rptRPiutangMasuk.jasper", "report", "::[ Rekap Piutang Masuk ]::",
                            "select temp2, temp3, temp4, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13 from temporary3 order by CONVERT(temp1,int), temp3", param);
                }                
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TKd);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPiutangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPiutangMouseClicked
        if (tabMode.getRowCount() != 0) {
            setDataBayar();
        }
}//GEN-LAST:event_tbPiutangMouseClicked

    private void tbPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPiutangKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                setDataBayar();
            }
        }
}//GEN-LAST:event_tbPiutangKeyPressed

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
        tampil();
    } else {
        Valid.pindah(evt, TKd, BtnAll);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabPiutang.setSelectedIndex(0);
        TabPiutang.setEnabledAt(1, akses.getbayar_piutang());
        tampilPenjab();
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void TabPiutangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPiutangMouseClicked
        if (TabPiutang.getSelectedIndex() == 0) {
            tampilPenjab();
            penjabDiconteng();
            tampil();
            emptTeks();
            TCari1.setText("");
        } else if (TabPiutang.getSelectedIndex() == 1) {
            tampilBayar();
        }
    }//GEN-LAST:event_TabPiutangMouseClicked

    private void NoRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRawatKeyPressed
        Valid.pindah(evt, TCari1, NoRawat);
    }//GEN-LAST:event_NoRawatKeyPressed

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        Valid.pindah(evt, Keterangan, Cicilan);
    }//GEN-LAST:event_KeteranganKeyPressed

    private void CicilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CicilanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sisa.setText(Valid.SetAngka(sisapiutang));
            //            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang - Double.parseDouble(Cicilan.getText())));
                //                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sisa.setText(Valid.SetAngka(sisapiutang));
            //            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang - Double.parseDouble(Cicilan.getText())));
                //                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
            Keterangan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sisa.setText(Valid.SetAngka(sisapiutang));
            //            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang - Double.parseDouble(Cicilan.getText())));
                //                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_CicilanKeyPressed

    private void CicilanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CicilanKeyReleased
        if (Cicilan.getText().equals("")) {
            Cicilan.setText("0");
        }

        Sisa.setText(Valid.SetAngka(sisapiutang));
        //        TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
        if (!Cicilan.getText().equals("")) {
            Sisa.setText(Valid.SetAngka2(sisapiutang - Double.parseDouble(Cicilan.getText())));
            //            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
        }
    }//GEN-LAST:event_CicilanKeyReleased

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,NoRawat,TNoRM);
    }//GEN-LAST:event_TanggalKeyPressed

    private void nama_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nama_bayarKeyPressed
        Valid.pindah(evt,Tanggal,NoRawat);
    }//GEN-LAST:event_nama_bayarKeyPressed

    private void ChkPelunasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPelunasanActionPerformed
        if (ChkPelunasan.isSelected() == true) {
            Keterangan.setText("");
            Cicilan.setText("0");
            cekSisaPiutang();
            
            Keterangan.setText("PELUNASAN PIUTANG SISA TAGIHAN");
            Cicilan.setText(Sisa.getText());
            Sisa.setText("0");
        } else {
            Keterangan.setText("");
            Cicilan.setText("0");
            cekSisaPiutang();
        }
    }//GEN-LAST:event_ChkPelunasanActionPerformed

    private void tbBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBayarMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbBayarMouseClicked

    private void tbBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBayarKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbBayarKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar1.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilBayar();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        tampilBayar();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari1, TCari1);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (NoRawat.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "No.Tagihan/No.Rawat");
        } else if (Cicilan.getText().trim().equals("") || Cicilan.getText().trim().equals("0")) {
            Valid.textKosong(Cicilan, "Besar Cicilan");
        } else if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else {
            if (Sequel.cariIsi("SELECT status FROM piutang_pasien where no_rawat='" + NoRawat.getText() + "'").equals("Lunas")) {
                JOptionPane.showMessageDialog(null, "Tagihan piutang pasien sudah dilunasi...!");
            } else {
                try {
                    koderekening = Sequel.cariIsi("select kd_rek from akun_bayar where nama_bayar=?", nama_bayar.getSelectedItem().toString());
                    Sequel.menyimpan("bayar_piutang", "?,?,?,?,?,?,?,?", 8, new String[]{
                        Valid.SetTgl(Tanggal.getSelectedItem() + ""), TNoRM.getText(), Cicilan.getText(),
                        Keterangan.getText(), NoRawat.getText(), koderekening, Sequel.cariIsi("select now()"), Sisa.getText()
                    });

                    if (Double.parseDouble(Sisa.getText()) <= 1) {
                        Sequel.mengedit("piutang_pasien", "no_rawat='" + NoRawat.getText() + "'", "status='Lunas'");
                    }
                    Sequel.queryu("delete from tampjurnal");
                    Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select Bayar_Piutang_Pasien from set_akun") + "','BAYAR PIUTANG','0','" + Cicilan.getText() + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + koderekening + "','" + nama_bayar.getSelectedItem() + "','" + Cicilan.getText() + "','0'", "Rekening");
                    jur.simpanJurnal(NoRawat.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "BAYAR PIUTANG");

                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada No.Bayar yang sama dimasukkan sebelumnya...!");
                }

                BtnCari1ActionPerformed(evt);
                emptTeks();
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Sisa, BtnKeluar1);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (NoRawat.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "No.Tagihan/No.Rawat");
        } else if (Cicilan.getText().trim().equals("") || Cicilan.getText().trim().equals("0")) {
            Valid.textKosong(Cicilan, "Besar Cicilan");
        } else if (TNmPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data ini mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                try {
                    koderekening = Sequel.cariIsi("select kd_rek from bayar_piutang where "
                        + "tgl_bayar='" + Valid.SetTgl(Tanggal.getSelectedItem() + "") + "' and no_rkm_medis='" + TNoRM.getText() + "' and waktu_simpan='" + wktsimpan + "'");

                    Sequel.queryu("delete from bayar_piutang where tgl_bayar='" + Valid.SetTgl(Tanggal.getSelectedItem() + "") + "' "
                        + "and no_rkm_medis='" + TNoRM.getText() + "' "
                        + "and no_rawat='" + NoRawat.getText() + "' "
                        + "and waktu_simpan='" + wktsimpan + "'");
                    Sequel.mengedit("piutang_pasien", "no_rawat='" + NoRawat.getText() + "'", "status='Belum Lunas'");

                    Sequel.queryu("delete from tampjurnal");
                    Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select Bayar_Piutang_Pasien from set_akun") + "','BAYAR PIUTANG','" + Cicilan.getText() + "','0'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + koderekening + "','" + nama_bayar.getSelectedItem() + "','0','" + Cicilan.getText() + "'", "Rekening");
                    jur.simpanJurnal(NoRawat.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "PEMBATALAN BAYAR PIUTANG");

                    BtnCari1ActionPerformed(evt);
                    emptTeks();
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada No.Bayar yang sama dimasukkan sebelumnya...!");
                }
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar1, BtnAll1);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCari1ActionPerformed(evt);
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnKeluar1.requestFocus();
        } else if (tabMode1.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBayar.jasper", "report", "::[ Bayar Piutang ]::",
                "select bayar_piutang.tgl_bayar, bayar_piutang.no_rkm_medis,pasien.nm_pasien, bayar_piutang.besar_cicilan,"
                + "bayar_piutang.catatan, bayar_piutang.no_rawat from bayar_piutang inner join pasien on bayar_piutang.no_rkm_medis=pasien.no_rkm_medis where "
                + "bayar_piutang.no_rawat like '%" + TCari1.getText() + "%' or "
                + "bayar_piutang.no_rkm_medis like '%" + TCari1.getText() + "%' or "
                + "pasien.nm_pasien like '%" + TCari1.getText() + "%' or "
                + "bayar_piutang.tgl_bayar like '%" + TCari1.getText() + "%' order by bayar_piutang.tgl_bayar,bayar_piutang.no_rkm_medis,bayar_piutang.no_rawat ", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar1);
        }
    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll1, TCari1);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void ppNotaPiutangBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppNotaPiutangBtnPrintActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnKeluar1.requestFocus();
        } else {
            if (tbBayar.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("cara_byr", Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + NoRawat.getText() + "'"));
                param.put("tot_bayar", "Rp. " + tabMode1.getValueAt(tbBayar.getSelectedRow(), 3).toString());
                param.put("tglNota", "Martapura, " + tglNota.getSelectedItem().toString());

                param.put("norawat", tabMode1.getValueAt(tbBayar.getSelectedRow(), 5).toString());
                param.put("tglbayar", tabMode1.getValueAt(tbBayar.getSelectedRow(), 6).toString());
                param.put("norm", tabMode1.getValueAt(tbBayar.getSelectedRow(), 1).toString());
                param.put("nmpasien", tabMode1.getValueAt(tbBayar.getSelectedRow(), 2).toString());
                param.put("keterangan", tabMode1.getValueAt(tbBayar.getSelectedRow(), 4).toString());
                param.put("nilaicicilan", tabMode1.getValueAt(tbBayar.getSelectedRow(), 3).toString());
                param.put("sisapiutang", tabMode1.getValueAt(tbBayar.getSelectedRow(), 8).toString());

                if (akses.getadmin() == true) {
                    param.put("petugas_ksr", "( ................... )");
                } else {
                    param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
                }

                Valid.MyReport("rptNotaBayarPiutang.jasper", "report", "::[ Nota Pembayaran Piutang Pasien ]::",
                    "SELECT now() tgl", param);

                tampilBayar();
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, pilih dulu salah satu datanya yang mau dicetak notanya...!!!!");
                tbBayar.requestFocus();
            }
        }
    }//GEN-LAST:event_ppNotaPiutangBtnPrintActionPerformed

    private void cmbPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPiutangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPiutangKeyPressed

    private void cmbPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPiutangActionPerformed
        tampil();        
    }//GEN-LAST:event_cmbPiutangActionPerformed

    private void cmbPenjaminMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPenjaminMouseReleased
        AutoCompleteDecorator.decorate(cmbPenjamin);
    }//GEN-LAST:event_cmbPenjaminMouseReleased

    private void MnSemuanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuanyaActionPerformed
        tampilPenjab();
        for (i = 0; i < tbPenjab.getRowCount(); i++) {
            tbPenjab.setValueAt(Boolean.TRUE, i, 0);
        }
    }//GEN-LAST:event_MnSemuanyaActionPerformed

    private void MnDibatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDibatalkanActionPerformed
        for (i = 0; i < tbPenjab.getRowCount(); i++) {
            tbPenjab.setValueAt(Boolean.FALSE, i, 0);
        }
        tampilPenjab();
    }//GEN-LAST:event_MnDibatalkanActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilPenjab();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampilPenjab();
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari2.setText("");
        tampilPenjab();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll2ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void MnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingActionPerformed
        if (tbPiutang.getSelectedRow() > -1) {
            if (tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 13).toString().equals("Ranap")) {
                kodekmr = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 0).toString() + "' "
                        + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");

                bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kodekmr));
                if (bangsal.equals("")) {
                    if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                        akses.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kodekmr));
                    } else {
                        akses.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
                    }
                } else {
                    akses.setkdbangsal(bangsal);
                }

                DlgBilingRanap billing = new DlgBilingRanap(null, false);
                billing.TNoRw.setText(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 0).toString());
                billing.isCek(kodekmr);
                billing.cekLR.setSelected(true);
                billing.isRawat();
                billing.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                billing.setLocationRelativeTo(internalFrame1);
                billing.setVisible(true);
            } else {
                if (akses.getbilling_ralan() == true) {
                    otomatisRalan(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 0).toString());
                }

                DlgBilingRalan billing = new DlgBilingRalan(null, false);
                billing.TNoRw.setText(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 0).toString());
                billing.isCek();
                billing.isRawat();
                billing.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                billing.setLocationRelativeTo(internalFrame1);
                billing.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbPiutang.requestFocus();
        }
    }//GEN-LAST:event_MnBillingActionPerformed

    private void MnDetailPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetailPiutangActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data tagihan piutang masih kosong...!!!!");
        } else {
            if (tbPiutang.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgBilingPiutang rincianpiutang = new DlgBilingPiutang(null, false);
                rincianpiutang.isRawat(
                        tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 0).toString(),
                        Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 5).toString()),
                        Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 7).toString()),
                        Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 4).toString()),
                        Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 6).toString())
                );
                rincianpiutang.setSize(this.getWidth() - 40, this.getHeight() - 40);
                rincianpiutang.setLocationRelativeTo(this);
                rincianpiutang.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih & klik datanya terlebih dulu..!!");
                tbPiutang.requestFocus();
            }
        }
    }//GEN-LAST:event_MnDetailPiutangActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLhtPiutang dialog = new DlgLhtPiutang(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll2;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkPelunasan;
    private widget.TextBox Cicilan;
    private widget.TextBox Keterangan;
    private javax.swing.JLabel LCount;
    private widget.Label LCount1;
    private widget.Label LCountBayar;
    private widget.Label LTotalBayar;
    private javax.swing.JMenuItem MnBilling;
    private javax.swing.JMenuItem MnDetailPiutang;
    private javax.swing.JMenuItem MnDibatalkan;
    private javax.swing.JMenuItem MnSemuanya;
    private widget.TextBox NoRawat;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox Sisa;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TKd;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private javax.swing.JTabbedPane TabPiutang;
    private widget.Tanggal Tanggal;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Label TotalPiutang;
    private widget.ComboBox cmbPenjamin;
    private widget.ComboBox cmbPiutang;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private javax.swing.JLabel jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label32;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label38;
    private widget.Label label9;
    private widget.ComboBox nama_bayar;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private javax.swing.JMenuItem ppNotaPiutang;
    private widget.Table tbBayar;
    private widget.Table tbPenjab;
    private widget.Table tbPiutang;
    private widget.Tanggal tglNota;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        penjabDiconteng();
        Valid.tabelKosong(tabMode);
        belumdibayar = 0;
        try {
            if (cmbPiutang.getSelectedIndex() == 0 || cmbPiutang.getSelectedIndex() == 3) {
                ps = koneksi.prepareStatement("select pp.no_rawat, date_format(pp.tgl_piutang,'%d/%m/%Y') tglpiutang, concat(pp.no_rkm_medis,' - ',p.nm_pasien), "
                        + "pp.status, pp.totalpiutang, pp.uangmuka, pp.sisapiutang, date_format(pp.tgltempo,'%d/%m/%Y') tgljatuhtempo, pj.png_jawab, "
                        + "pp.tgl_piutang, pp.tgltempo, pp.penjamin, pp.ket_penjamin, rp.status_lanjut FROM piutang_pasien pp "
                        + "INNER JOIN pasien p on pp.no_rkm_medis = p.no_rkm_medis INNER JOIN reg_periksa rp on pp.no_rawat = rp.no_rawat "
                        + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj where "
                        + "" + penjabOK + " pp.penjamin like ? and pp.no_rawat like ? and pp.tgl_piutang between ? and ? or "
                        + "" + penjabOK + " pp.penjamin like ? and pp.no_rkm_medis like ? and pp.tgl_piutang between ? and ? or "
                        + "" + penjabOK + " pp.penjamin like ? and p.nm_pasien like ? and pp.tgl_piutang between ? and ? order by pp.tgl_piutang");
            } else {
                ps = koneksi.prepareStatement("select pp.no_rawat, date_format(pp.tgl_piutang,'%d/%m/%Y') tglpiutang, concat(pp.no_rkm_medis,' - ',p.nm_pasien), "
                        + "pp.status, pp.totalpiutang, pp.uangmuka, pp.sisapiutang, date_format(pp.tgltempo,'%d/%m/%Y') tgljatuhtempo, pj.png_jawab, "
                        + "pp.tgl_piutang, pp.tgltempo, pp.penjamin, pp.ket_penjamin, rp.status_lanjut FROM piutang_pasien pp "
                        + "INNER JOIN pasien p on pp.no_rkm_medis = p.no_rkm_medis INNER JOIN reg_periksa rp on pp.no_rawat = rp.no_rawat "
                        + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj where "
                        + "pp.status ='" + cmbPiutang.getSelectedItem().toString() + "' and " + penjabOK + " pp.penjamin like ? and "
                        + "pp.no_rawat like ? and pp.tgl_piutang between ? and ? or "
                        + "pp.status ='" + cmbPiutang.getSelectedItem().toString() + "' and " + penjabOK + " pp.penjamin like ? and "
                        + "pp.no_rkm_medis like ? and pp.tgl_piutang between ? and ? or "
                        + "pp.status ='" + cmbPiutang.getSelectedItem().toString() + "' and " + penjabOK + " pp.penjamin like ? and "
                        + "p.nm_pasien like ? and pp.tgl_piutang between ? and ? order by pp.tgl_piutang");
            }
            
            try {                
                ps.setString(1, "%" + cmbPenjamin.getSelectedItem().toString() + "%");
                ps.setString(2, "%" + TCari.getText() + "%");
                ps.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));                
                ps.setString(5, "%" + cmbPenjamin.getSelectedItem().toString() + "%");
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));                
                ps.setString(9, "%" + cmbPenjamin.getSelectedItem().toString() + "%");
                ps.setString(10, "%" + TCari.getText() + "%");
                ps.setString(11, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(12, Valid.SetTgl(Tgl2.getSelectedItem() + ""));                
                rs = ps.executeQuery();
                while (rs.next()) {
                    cicilan = Sequel.cariIsiAngka("SELECT ifnull(SUM(besar_cicilan),0) FROM bayar_piutang where no_rawat=?", rs.getString(1));
                    tabMode.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        cicilan,
                        (rs.getDouble(7) - cicilan),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)
                    });
        
                    if (belumdibayar == 0) {
                        belumdibayar = (rs.getDouble(7) - cicilan);
                    } else {
                        belumdibayar = belumdibayar + (rs.getDouble(7) - cicilan);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            
            LCount.setText(Valid.SetAngka(belumdibayar));
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode.getRowCount());
    }

    public void setNoRm(String norm, Date tgl){
        TCari.setText(norm);
        Tgl1.setDate(tgl);
        TabPiutang.setEnabledAt(1, akses.getbayar_piutang());
    }
    
    private void cekSisaPiutang() {
        sisapiutang = 0;
        sisapiutang = Sequel.cariIsiAngka("SELECT ifnull(SUM(pp.sisapiutang),0) FROM piutang_pasien pp where pp.no_rawat='" + NoRawat.getText() + "' and pp.no_rkm_medis=?", TNoRM.getText())
                + Sequel.cariIsiAngka("SELECT ifnull(SUM(p.sisapiutang),0) FROM piutang p where p.no_rkm_medis=?", TNoRM.getText())
                - Sequel.cariIsiAngka("SELECT ifnull(SUM(bp.besar_cicilan),0) FROM bayar_piutang bp where bp.no_rawat='" + NoRawat.getText() + "' and bp.no_rkm_medis=?", TNoRM.getText());
        Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang, 100)));
        Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang - Double.parseDouble(Cicilan.getText()), 100)));
    }
    
    private void getData() {
        wktsimpan = "";
        int row = tbBayar.getSelectedRow();
        if (row != -1) {
            TNoRM.setText(tbBayar.getValueAt(row, 1).toString());
            TNmPasien.setText(tbBayar.getValueAt(row, 2).toString());
            Cicilan.setText(tbBayar.getValueAt(row, 3).toString());
            Keterangan.setText(tbBayar.getValueAt(row, 4).toString());
            NoRawat.setText(tbBayar.getValueAt(row, 5).toString());
            Valid.SetTgl(Tanggal, tbBayar.getValueAt(row, 0).toString());
            wktsimpan = tbBayar.getValueAt(row, 7).toString();
            Sisa.setText(tbBayar.getValueAt(row, 8).toString());
            ChkPelunasan.setSelected(false);
        }
    }
    
    private void emptTeks() {
        Cicilan.setText("0");
        NoRawat.setText("");
        TNoRM.setText("");
        TNmPasien.setText("");
        Sisa.setText("0");
        TotalPiutang.setText("0");
        Keterangan.setText("");
        ChkPelunasan.setSelected(false);
        Tanggal.setDate(new Date());
        tglNota.setDate(new Date());
        cmbPiutang.setSelectedIndex(0);
        Keterangan.requestFocus();
    }
    
    private void tampilBayar() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1.setString(1, "%" + TCari1.getText() + "%");
            ps1.setString(2, "%" + TCari1.getText() + "%");
            ps1.setString(3, "%" + TCari1.getText() + "%");
            ps1.setString(4, "%" + TCari1.getText() + "%");
            ps1.setString(5, "%" + TCari1.getText() + "%");
            ps1.setString(6, "%" + TCari1.getText() + "%");
            rs1 = ps1.executeQuery();
            total = 0;
            while (rs1.next()) {
                total = total + rs1.getDouble(4);
                tabMode1.addRow(new Object[]{
                    rs1.getString(1),
                    rs1.getString(2),
                    rs1.getString(3),
                    Valid.SetAngka(rs1.getDouble(4)),
                    rs1.getString(5),
                    rs1.getString(6),
                    rs1.getString("tglbayar"),
                    rs1.getString("waktu_simpan"),
                    Valid.SetAngka(rs1.getDouble("sisa_piutang")),
                    rs1.getString("penjamin"),
                    rs1.getString("ket_penjamin")
                });
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCountBayar.setText("" + tabMode1.getRowCount());
        LTotalBayar.setText(Valid.SetAngka(total));
    }
    
    private void setDataBayar() {
        NoRawat.setText(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 0).toString());
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from piutang_pasien where no_rawat='" + NoRawat.getText() + "'"));
        TNmPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TCari1.setText(NoRawat.getText());
        cekSisaPiutang();

//        Tsisapiutang = Sequel.cariIsiAngka("SELECT ifnull(SUM(pp.sisapiutang),0) FROM piutang_pasien pp where pp.no_rawat=?", NoRawat.getText())
////                + Sequel.cariIsiAngka("SELECT ifnull(SUM(p.sisapiutang),0) FROM piutang p where p.no_rkm_medis=?", TNoRM.getText())
//                - Sequel.cariIsiAngka("SELECT ifnull(SUM(bp.besar_cicilan),0) FROM bayar_piutang bp where bp.no_rawat=?", NoRawat.getText());
        TotalPiutang.setText(Valid.SetAngka(Valid.roundUp(Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 4).toString()), 100)));
        if (!Cicilan.getText().equals("")) {
            Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang - Double.parseDouble(Cicilan.getText()), 100)));
            TotalPiutang.setText(Valid.SetAngka(Valid.roundUp(Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 4).toString()) - Double.parseDouble(Cicilan.getText()), 100)));
        }
    }
    
    private void tampilPenjab() {
        jml = 0;
        for (i = 0; i < tbPenjab.getRowCount(); i++) {
            if (tbPenjab.getValueAt(i, 0).toString().equals("true")) {
                jml++;
            }
        }
        
        pilih = null;
        pilih = new boolean[jml];
        kode = null;
        kode = new String[jml];
        caByr = null;
        caByr = new String[jml];
        sttsMOU = null;
        sttsMOU = new String[jml];
        
        jml = 0;
        for (i = 0; i < tbPenjab.getRowCount(); i++) {
            if (tbPenjab.getValueAt(i, 0).toString().equals("true")) {
                pilih[jml] = true;
                kode[jml] = tbPenjab.getValueAt(i, 1).toString();
                caByr[jml] = tbPenjab.getValueAt(i, 2).toString();
                sttsMOU[jml] = tbPenjab.getValueAt(i, 3).toString();
                jml++;
            }
        }
        
        Valid.tabelKosong(tabMode2);
        for (i = 0; i < jml; i++) {
            tabMode2.addRow(new Object[]{pilih[i], kode[i], caByr[i], sttsMOU[i]});
        }
        
        try {
            ps2 = koneksi.prepareStatement("select *, if(status='1','Kerja Sama','Putus') stts from penjab where "
                    + "kd_pj not in ('-','A43') and png_jawab like '%" + TCari2.getText() + "%' or "
                    + "kd_pj not in ('-','A43') and if(status='1','Kerja Sama','Putus') like '%" + TCari2.getText() + "%' "
                    + "order by png_jawab");
            try {                
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rs2.getString("kd_pj"),
                        rs2.getString("png_jawab"),
                        rs2.getString("stts")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
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
    
    private void penjabDiconteng() {
        x = 0;
        penjabPilih = "";
        penjabOK = "";
        
        for (i = 0; i < tbPenjab.getRowCount(); i++) {
            if (tbPenjab.getValueAt(i, 0).toString().equals("true")) {
                x++;
            }
        }

        if (x == 0) {            
            penjabOK = "";
        } else {
            try {
                for (i = 0; i < tbPenjab.getRowCount(); i++) {
                    if (tbPenjab.getValueAt(i, 0).toString().equals("true")) {
                        if (penjabPilih.equals("")) {
                            penjabPilih = "'" + tbPenjab.getValueAt(i, 1).toString() + "'";
                        } else {
                            penjabPilih = penjabPilih + ",'" + tbPenjab.getValueAt(i, 1).toString() + "'";
                        }
                    }
                }
                penjabOK = "pj.kd_pj in (" + penjabPilih + ") and ";
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
    
    private void otomatisRalan(String norw) {
        kodedok = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + norw + "'");
        if (Sequel.cariRegistrasi(norw) == 0) {
            try {
                psotomatis = koneksi.prepareStatement(sqlpsotomatis);
                try {
                    psotomatis.setString(1, kodedok);
                    psotomatis.setString(2, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norw));
                    rskasir = psotomatis.executeQuery();
                    while (rskasir.next()) {
                        if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_dr where "
                                + "no_rawat='" + norw + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                + "and kd_dokter='" + kodedok + "'") == 0) {
                            psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2);
                            try {
                                psotomatis2.setString(1, norw);
                                psotomatis2.setString(2, rskasir.getString(1));
                                psotomatis2.setString(3, kodedok);
                                psotomatis2.setString(4, Sequel.cariIsi("select current_date()"));
                                psotomatis2.setString(5, Sequel.cariIsi("select current_time()"));
                                psotomatis2.setDouble(6, rskasir.getDouble("material"));
                                psotomatis2.setDouble(7, rskasir.getDouble("bhp"));
                                psotomatis2.setDouble(8, rskasir.getDouble("tarif_tindakandr"));
                                psotomatis2.setDouble(9, rskasir.getDouble("kso"));
                                psotomatis2.setDouble(10, rskasir.getDouble("menejemen"));
                                psotomatis2.setDouble(11, rskasir.getDouble("total_byrdr"));
                                psotomatis2.executeUpdate();
                            } catch (Exception e) {
                                System.out.println("proses input data " + e);
                            } finally {
                                if (psotomatis2 != null) {
                                    psotomatis2.close();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rskasir != null) {
                        rskasir.close();
                    }
                    if (psotomatis != null) {
                        psotomatis.close();
                    }
                }

                if (!akses.getadmin() == true) {
                    psotomatis = koneksi.prepareStatement(sqlpsotomatispetugas);
                    try {
                        psotomatis.setString(1, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norw));
                        rskasir = psotomatis.executeQuery();
                        while (rskasir.next()) {
                            if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_pr where "
                                    + "no_rawat='" + norw + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                    + "and nip='" + akses.getkode() + "'") == 0) {
                                psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2petugas);
                                try {
                                    psotomatis2.setString(1, norw);
                                    psotomatis2.setString(2, rskasir.getString(1));
                                    psotomatis2.setString(3, akses.getkode());
                                    psotomatis2.setString(4, Sequel.cariIsi("select current_date()"));
                                    psotomatis2.setString(5, Sequel.cariIsi("select current_time()"));
                                    psotomatis2.setDouble(6, rskasir.getDouble("material"));
                                    psotomatis2.setDouble(7, rskasir.getDouble("bhp"));
                                    psotomatis2.setDouble(8, rskasir.getDouble("tarif_tindakanpr"));
                                    psotomatis2.setDouble(9, rskasir.getDouble("kso"));
                                    psotomatis2.setDouble(10, rskasir.getDouble("menejemen"));
                                    psotomatis2.setDouble(11, rskasir.getDouble("total_byrpr"));
                                    psotomatis2.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println("proses input data " + e);
                                } finally {
                                    if (psotomatis2 != null) {
                                        psotomatis2.close();
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rskasir != null) {
                            rskasir.close();
                        }
                        if (psotomatis != null) {
                            psotomatis.close();
                        }
                    }
                }

                if (!akses.getadmin() == true) {
                    psotomatis = koneksi.prepareStatement(sqlpsotomatisdokterpetugas);
                    try {
                        psotomatis.setString(1, kodedok);
                        psotomatis.setString(2, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norw));
                        rskasir = psotomatis.executeQuery();
                        while (rskasir.next()) {
                            if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_drpr where "
                                    + "no_rawat='" + norw + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                    + "and kd_dokter='" + kodedok + "'") == 0) {
                                psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2dokterpetugas);
                                try {
                                    psotomatis2.setString(1, norw);
                                    psotomatis2.setString(2, rskasir.getString(1));
                                    psotomatis2.setString(3, kodedok);
                                    psotomatis2.setString(4, akses.getkode());
                                    psotomatis2.setString(5, Sequel.cariIsi("select current_date()"));
                                    psotomatis2.setString(6, Sequel.cariIsi("select current_time()"));
                                    psotomatis2.setDouble(7, rskasir.getDouble("material"));
                                    psotomatis2.setDouble(8, rskasir.getDouble("bhp"));
                                    psotomatis2.setDouble(9, rskasir.getDouble("tarif_tindakandr"));
                                    psotomatis2.setDouble(10, rskasir.getDouble("tarif_tindakanpr"));
                                    psotomatis2.setDouble(11, rskasir.getDouble("kso"));
                                    psotomatis2.setDouble(12, rskasir.getDouble("menejemen"));
                                    psotomatis2.setDouble(13, rskasir.getDouble("total_byrdrpr"));
                                    psotomatis2.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println("proses input data " + e);
                                } finally {
                                    if (psotomatis2 != null) {
                                        psotomatis2.close();
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rskasir != null) {
                            rskasir.close();
                        }
                        if (psotomatis != null) {
                            psotomatis.close();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
}
