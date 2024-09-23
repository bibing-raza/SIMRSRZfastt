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
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;

public class DlgAssesmenGiziUlang extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String mual = "", nyeri = "", diare = "", kesulitan = "", odema = "", konstipasi = "", anoreksia = "", gangguan = "", 
            makanlebih = "", makankurang = "", asupanCukup = "", asupanMenurun = "", asupanRendah = "", asupanTidak = "", alergi = "", 
            pantangan = "",  cekumur = "", ceksttsumur = "", dataKlinis1 = "", dataKlinis2 = "", klinis1 = "",
            klinis2 = "", klinis3 = "", klinis4 = "", klinis5 = "", klinis6 = "", klinis7 = "", klinis8 = "",
            dataRiwayat1 = "", dataRiwayat2 = "", riw1 = "", riw2 = "", riw3 = "", riw4 = "", riw5 = "", riw6 = "",
            riw7 = "", riw8 = "", riw9 = "", bbu = "", pbu = "", bbpb = "", gizianak1 = "", gizianak2 = "", gizianak3 = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgAssesmenGiziUlang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Umur/Jns. Asuhan", "Jns. Kelamin", "Tgl. Asesmen", "Ruang Perawatan", "Nama Ahli Gizi",
            "tgl_assesmen", "ruang_rawat", "bb", "tb", "imt", "lila", "tinggi_lutut", "ulna", "tb_est", "bb_koreksi",
            "bbi", "status_gizi", "biokimia", "mual_muntah", "nyeri_ulu_hati", "diare", "kesulitan_menelan", "oedema", "konstipasi",
            "anoreksia", "gangguan_gigi_geligi", "klinis_lainnya", "makan_lebih_3x", "makan_kurang_3x", "riwayat_gizi_lainnya",
            "alergi_makanan", "ket_alergi_makanan", "pantangan_makan", "ket_pantangan_makan", "asupan_cukup", "asupan_menurun",
            "asupan_rendah", "asupan_tidak_cukup", "hasil_recall", "catatan", "nip_petugas", "waktu_simpan", "umurcek", "sttsumur",
            "klasifikasi_imt", "persentase_cdc", "jenis_asuhan", "indek_bbu", "indek_pbu", "indek_bbpb", "ket_indek_bbu", "ket_indek_pbu", 
            "ket_indek_bbpb", "stts_gizi_bbu", "stts_gizi_pbu", "stts_gizi_bbpb"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmenGZUlang.setModel(tabMode);
        tbAsesmenGZUlang.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmenGZUlang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 59; i++) {
            TableColumn column = tbAsesmenGZUlang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 31) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 32) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 34) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 39) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 40) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 41) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 42) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 43) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 44) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 45) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 46) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 47) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 48) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 49) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 50) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 51) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 52) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 53) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 54) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 55) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 56) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 57) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 58) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAsesmenGZUlang.setDefaultRenderer(Object.class, new WarnaTable());
        
        Tbb.setDocument(new batasInput((int) 5).getKata(Tbb));
        Ttb.setDocument(new batasInput((int) 5).getKata(Ttb));
        Tlila.setDocument(new batasInput((int) 5).getKata(Tlila));
        Tbbi.setDocument(new batasInput((int) 5).getKata(Tbbi));
        Ttl.setDocument(new batasInput((int) 5).getKata(Ttl));
        Tulna.setDocument(new batasInput((int) 5).getKata(Tulna));
        TtbEst.setDocument(new batasInput((int) 20).getKata(TtbEst));
        TbbKoreksi.setDocument(new batasInput((int) 40).getKata(TbbKoreksi));
        Talergi.setDocument(new batasInput((int) 220).getKata(Talergi));
        Tpantangan.setDocument(new batasInput((int) 220).getKata(Tpantangan));
        Tpersentase.setDocument(new batasInput((int) 6).getKata(Tpersentase));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    Tnip.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPetugas.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel25 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel26 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel12 = new widget.Label();
        tglAsesmen = new widget.Tanggal();
        jLabel13 = new widget.Label();
        Tumur = new widget.TextBox();
        Tsttsumur = new widget.Label();
        jLabel66 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel67 = new widget.Label();
        jLabel65 = new widget.Label();
        Tbb = new widget.TextBox();
        label105 = new widget.Label();
        Ttb = new widget.TextBox();
        label107 = new widget.Label();
        BtnNilaiIMT = new widget.Button();
        Timt = new widget.TextBox();
        label108 = new widget.Label();
        Tlila = new widget.TextBox();
        label109 = new widget.Label();
        Tbbi = new widget.TextBox();
        label113 = new widget.Label();
        jLabel68 = new widget.Label();
        Ttl = new widget.TextBox();
        label106 = new widget.Label();
        Tulna = new widget.TextBox();
        label110 = new widget.Label();
        BtnNilaiTB = new widget.Button();
        TtbEst = new widget.TextBox();
        label111 = new widget.Label();
        BtnNilaiBB = new widget.Button();
        TbbKoreksi = new widget.TextBox();
        label112 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tbiokimia = new widget.TextArea();
        jLabel70 = new widget.Label();
        cmbSttsGizi = new widget.ComboBox();
        BtnPasteHasil = new widget.Button();
        jLabel71 = new widget.Label();
        ChkMual = new widget.CekBox();
        ChkOedema = new widget.CekBox();
        jLabel72 = new widget.Label();
        TklinisLain = new widget.TextBox();
        ChkNyeri = new widget.CekBox();
        ChkKonstipasi = new widget.CekBox();
        ChkDiare = new widget.CekBox();
        ChkAnorek = new widget.CekBox();
        ChkKesulitan = new widget.CekBox();
        ChkGangguan = new widget.CekBox();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        jLabel76 = new widget.Label();
        ChkMakanLebih3 = new widget.CekBox();
        ChkAsupanCukup = new widget.CekBox();
        ChkAsupanRendah = new widget.CekBox();
        ChkMakanKurang3 = new widget.CekBox();
        ChkAsupanMenurun = new widget.CekBox();
        ChkAsupanTdkCukup = new widget.CekBox();
        jLabel75 = new widget.Label();
        TriwayatLain = new widget.TextBox();
        jLabel89 = new widget.Label();
        cmbHasilRecal = new widget.ComboBox();
        ChkAlergi = new widget.CekBox();
        Talergi = new widget.TextBox();
        ChkPantangan = new widget.CekBox();
        Tpantangan = new widget.TextBox();
        scrollPane14 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        jLabel77 = new widget.Label();
        jLabel34 = new widget.Label();
        Tnip = new widget.TextBox();
        TnmPetugas = new widget.TextBox();
        chkSaya = new widget.CekBox();
        BtnPetugas = new widget.Button();
        jLabel14 = new widget.Label();
        TtglAsuhan = new widget.TextBox();
        jLabel97 = new widget.Label();
        cmbKlasifikasiIMT = new widget.ComboBox();
        jLabel98 = new widget.Label();
        ChkBbu = new widget.CekBox();
        TketBbu = new widget.TextBox();
        jLabel99 = new widget.Label();
        cmbBbu = new widget.ComboBox();
        ChkPbu = new widget.CekBox();
        TketPbu = new widget.TextBox();
        jLabel100 = new widget.Label();
        cmbPbu = new widget.ComboBox();
        ChkBbpb = new widget.CekBox();
        TketBbpb = new widget.TextBox();
        jLabel101 = new widget.Label();
        cmbBbpb = new widget.ComboBox();
        jLabel78 = new widget.Label();
        cmbAsuhan = new widget.ComboBox();
        Tpersentase = new widget.TextBox();
        label114 = new widget.Label();
        BtnCekSttsGizi = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbAsesmenGZUlang = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel28 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel29 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(195, 26));
        MnHasilPemeriksaanPenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanPenunjangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanPenunjang);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Ulang Gizi Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(1003, 715));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 800));

        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 723));
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

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Ruang Rawat :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 66, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(114, 66, 440, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Assesmen :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        tglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-06-2024" }));
        tglAsesmen.setDisplayFormat("dd-MM-yyyy");
        tglAsesmen.setName("tglAsesmen"); // NOI18N
        tglAsesmen.setOpaque(false);
        tglAsesmen.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglAsesmen);
        tglAsesmen.setBounds(115, 38, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Umur : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(415, 38, 50, 23);

        Tumur.setEditable(false);
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        FormInput.add(Tumur);
        Tumur.setBounds(465, 38, 50, 23);

        Tsttsumur.setForeground(new java.awt.Color(0, 0, 0));
        Tsttsumur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tsttsumur.setText("tahun.");
        Tsttsumur.setName("Tsttsumur"); // NOI18N
        FormInput.add(Tsttsumur);
        Tsttsumur.setBounds(520, 38, 50, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Jenis Kelamin : ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(555, 38, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(645, 38, 90, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Antropometri :");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 94, 110, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Berat Badan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 114, 110, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(115, 114, 50, 23);

        label105.setForeground(new java.awt.Color(0, 0, 0));
        label105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label105.setText("Kg.    TB : ");
        label105.setName("label105"); // NOI18N
        label105.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label105);
        label105.setBounds(170, 114, 50, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(220, 114, 50, 23);

        label107.setForeground(new java.awt.Color(0, 0, 0));
        label107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label107.setText("Cm.");
        label107.setName("label107"); // NOI18N
        label107.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label107);
        label107.setBounds(275, 114, 30, 23);

        BtnNilaiIMT.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiIMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiIMT.setMnemonic('I');
        BtnNilaiIMT.setText("Nilai IMT :");
        BtnNilaiIMT.setToolTipText("Alt+I");
        BtnNilaiIMT.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiIMT.setName("BtnNilaiIMT"); // NOI18N
        BtnNilaiIMT.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiIMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiIMTActionPerformed(evt);
            }
        });
        BtnNilaiIMT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiIMTKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiIMT);
        BtnNilaiIMT.setBounds(310, 114, 93, 23);

        Timt.setEditable(false);
        Timt.setForeground(new java.awt.Color(0, 0, 0));
        Timt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Timt.setName("Timt"); // NOI18N
        FormInput.add(Timt);
        Timt.setBounds(410, 114, 68, 23);

        label108.setForeground(new java.awt.Color(0, 0, 0));
        label108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label108.setText("Kg/Cm3    LILA : ");
        label108.setName("label108"); // NOI18N
        label108.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label108);
        label108.setBounds(483, 114, 80, 23);

        Tlila.setForeground(new java.awt.Color(0, 0, 0));
        Tlila.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tlila.setName("Tlila"); // NOI18N
        Tlila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlilaKeyPressed(evt);
            }
        });
        FormInput.add(Tlila);
        Tlila.setBounds(565, 114, 50, 23);

        label109.setForeground(new java.awt.Color(0, 0, 0));
        label109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label109.setText("Cm.   BBI :");
        label109.setName("label109"); // NOI18N
        label109.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label109);
        label109.setBounds(621, 114, 55, 23);

        Tbbi.setForeground(new java.awt.Color(0, 0, 0));
        Tbbi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbbi.setName("Tbbi"); // NOI18N
        Tbbi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbiKeyPressed(evt);
            }
        });
        FormInput.add(Tbbi);
        Tbbi.setBounds(676, 114, 45, 23);

        label113.setForeground(new java.awt.Color(0, 0, 0));
        label113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label113.setText("Kg.");
        label113.setName("label113"); // NOI18N
        label113.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label113);
        label113.setBounds(725, 114, 20, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tinggi Lutut :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 142, 110, 23);

        Ttl.setForeground(new java.awt.Color(0, 0, 0));
        Ttl.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttl.setName("Ttl"); // NOI18N
        Ttl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtlKeyPressed(evt);
            }
        });
        FormInput.add(Ttl);
        Ttl.setBounds(115, 142, 50, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("Cm.    ULNA : ");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(170, 142, 67, 23);

        Tulna.setForeground(new java.awt.Color(0, 0, 0));
        Tulna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tulna.setName("Tulna"); // NOI18N
        Tulna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TulnaKeyPressed(evt);
            }
        });
        FormInput.add(Tulna);
        Tulna.setBounds(238, 142, 50, 23);

        label110.setForeground(new java.awt.Color(0, 0, 0));
        label110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label110.setText("Cm.");
        label110.setName("label110"); // NOI18N
        label110.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label110);
        label110.setBounds(293, 142, 30, 23);

        BtnNilaiTB.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiTB.setMnemonic('I');
        BtnNilaiTB.setText("Nilai TB est :");
        BtnNilaiTB.setToolTipText("Alt+I");
        BtnNilaiTB.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiTB.setName("BtnNilaiTB"); // NOI18N
        BtnNilaiTB.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiTBActionPerformed(evt);
            }
        });
        BtnNilaiTB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiTBKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiTB);
        BtnNilaiTB.setBounds(325, 142, 105, 23);

        TtbEst.setForeground(new java.awt.Color(0, 0, 0));
        TtbEst.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtbEst.setName("TtbEst"); // NOI18N
        TtbEst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbEstKeyPressed(evt);
            }
        });
        FormInput.add(TtbEst);
        TtbEst.setBounds(435, 142, 70, 23);

        label111.setForeground(new java.awt.Color(0, 0, 0));
        label111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label111.setText("Cm.");
        label111.setName("label111"); // NOI18N
        label111.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label111);
        label111.setBounds(510, 142, 25, 23);

        BtnNilaiBB.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiBB.setMnemonic('I');
        BtnNilaiBB.setText("Nilai BB Koreksi :");
        BtnNilaiBB.setToolTipText("Alt+I");
        BtnNilaiBB.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiBB.setName("BtnNilaiBB"); // NOI18N
        BtnNilaiBB.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiBBActionPerformed(evt);
            }
        });
        BtnNilaiBB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiBBKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiBB);
        BtnNilaiBB.setBounds(535, 142, 130, 23);

        TbbKoreksi.setForeground(new java.awt.Color(0, 0, 0));
        TbbKoreksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbKoreksi.setName("TbbKoreksi"); // NOI18N
        FormInput.add(TbbKoreksi);
        TbbKoreksi.setBounds(668, 142, 75, 23);

        label112.setForeground(new java.awt.Color(0, 0, 0));
        label112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label112.setText("Kg.");
        label112.setName("label112"); // NOI18N
        label112.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label112);
        label112.setBounds(747, 142, 20, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tbiokimia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tbiokimia.setColumns(20);
        Tbiokimia.setRows(5);
        Tbiokimia.setName("Tbiokimia"); // NOI18N
        Tbiokimia.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tbiokimia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbiokimiaKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tbiokimia);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(115, 282, 550, 60);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Biokimia :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 282, 110, 23);

        cmbSttsGizi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsGizi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Buruk", "Kurang", "Normal", "Lebih", "Obesitas", "Baik" }));
        cmbSttsGizi.setName("cmbSttsGizi"); // NOI18N
        cmbSttsGizi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSttsGizi);
        cmbSttsGizi.setBounds(623, 170, 75, 23);

        BtnPasteHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteHasil.setMnemonic('L');
        BtnPasteHasil.setText("Paste");
        BtnPasteHasil.setToolTipText("Alt+L");
        BtnPasteHasil.setName("BtnPasteHasil"); // NOI18N
        BtnPasteHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteHasilActionPerformed(evt);
            }
        });
        FormInput.add(BtnPasteHasil);
        BtnPasteHasil.setBounds(670, 282, 80, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Klinis / Fisik :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 347, 110, 23);

        ChkMual.setBackground(new java.awt.Color(255, 255, 250));
        ChkMual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMual.setForeground(new java.awt.Color(0, 0, 0));
        ChkMual.setText("Mual Muntah");
        ChkMual.setBorderPainted(true);
        ChkMual.setBorderPaintedFlat(true);
        ChkMual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMual.setName("ChkMual"); // NOI18N
        ChkMual.setOpaque(false);
        ChkMual.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMual);
        ChkMual.setBounds(115, 347, 90, 23);

        ChkOedema.setBackground(new java.awt.Color(255, 255, 250));
        ChkOedema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkOedema.setForeground(new java.awt.Color(0, 0, 0));
        ChkOedema.setText("Oedema");
        ChkOedema.setBorderPainted(true);
        ChkOedema.setBorderPaintedFlat(true);
        ChkOedema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkOedema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkOedema.setName("ChkOedema"); // NOI18N
        ChkOedema.setOpaque(false);
        ChkOedema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkOedema);
        ChkOedema.setBounds(250, 347, 72, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Klinis / Fisik Lainnya : ");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(330, 347, 110, 23);

        TklinisLain.setForeground(new java.awt.Color(0, 0, 0));
        TklinisLain.setName("TklinisLain"); // NOI18N
        FormInput.add(TklinisLain);
        TklinisLain.setBounds(440, 347, 294, 23);

        ChkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        ChkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        ChkNyeri.setText("Nyeri Ulu Hati");
        ChkNyeri.setBorderPainted(true);
        ChkNyeri.setBorderPaintedFlat(true);
        ChkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNyeri.setName("ChkNyeri"); // NOI18N
        ChkNyeri.setOpaque(false);
        ChkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNyeri);
        ChkNyeri.setBounds(115, 375, 90, 23);

        ChkKonstipasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkKonstipasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKonstipasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkKonstipasi.setText("Konstipasi");
        ChkKonstipasi.setBorderPainted(true);
        ChkKonstipasi.setBorderPaintedFlat(true);
        ChkKonstipasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKonstipasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKonstipasi.setName("ChkKonstipasi"); // NOI18N
        ChkKonstipasi.setOpaque(false);
        ChkKonstipasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKonstipasi);
        ChkKonstipasi.setBounds(250, 375, 78, 23);

        ChkDiare.setBackground(new java.awt.Color(255, 255, 250));
        ChkDiare.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDiare.setForeground(new java.awt.Color(0, 0, 0));
        ChkDiare.setText("Diare");
        ChkDiare.setBorderPainted(true);
        ChkDiare.setBorderPaintedFlat(true);
        ChkDiare.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDiare.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDiare.setName("ChkDiare"); // NOI18N
        ChkDiare.setOpaque(false);
        ChkDiare.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDiare);
        ChkDiare.setBounds(115, 403, 90, 23);

        ChkAnorek.setBackground(new java.awt.Color(255, 255, 250));
        ChkAnorek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAnorek.setForeground(new java.awt.Color(0, 0, 0));
        ChkAnorek.setText("Anoreksia");
        ChkAnorek.setBorderPainted(true);
        ChkAnorek.setBorderPaintedFlat(true);
        ChkAnorek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAnorek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAnorek.setName("ChkAnorek"); // NOI18N
        ChkAnorek.setOpaque(false);
        ChkAnorek.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAnorek);
        ChkAnorek.setBounds(250, 403, 78, 23);

        ChkKesulitan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKesulitan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKesulitan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKesulitan.setText("Kesulitan Menelan");
        ChkKesulitan.setBorderPainted(true);
        ChkKesulitan.setBorderPaintedFlat(true);
        ChkKesulitan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKesulitan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKesulitan.setName("ChkKesulitan"); // NOI18N
        ChkKesulitan.setOpaque(false);
        ChkKesulitan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKesulitan);
        ChkKesulitan.setBounds(115, 431, 114, 23);

        ChkGangguan.setBackground(new java.awt.Color(255, 255, 250));
        ChkGangguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGangguan.setForeground(new java.awt.Color(0, 0, 0));
        ChkGangguan.setText("Gangguan Gigi Geligi");
        ChkGangguan.setBorderPainted(true);
        ChkGangguan.setBorderPaintedFlat(true);
        ChkGangguan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGangguan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGangguan.setName("ChkGangguan"); // NOI18N
        ChkGangguan.setOpaque(false);
        ChkGangguan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGangguan);
        ChkGangguan.setBounds(250, 431, 125, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Riwayat Gizi :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 454, 110, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Riwayat Makan SMRS :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(115, 454, 120, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Asupan Makan SMRS :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(260, 454, 120, 23);

        ChkMakanLebih3.setBackground(new java.awt.Color(255, 255, 250));
        ChkMakanLebih3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMakanLebih3.setForeground(new java.awt.Color(0, 0, 0));
        ChkMakanLebih3.setText("Makan >= 3 x Sehari");
        ChkMakanLebih3.setBorderPainted(true);
        ChkMakanLebih3.setBorderPaintedFlat(true);
        ChkMakanLebih3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMakanLebih3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMakanLebih3.setName("ChkMakanLebih3"); // NOI18N
        ChkMakanLebih3.setOpaque(false);
        ChkMakanLebih3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMakanLebih3);
        ChkMakanLebih3.setBounds(115, 474, 135, 23);

        ChkAsupanCukup.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanCukup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanCukup.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanCukup.setText("Asupan Cukup & Tidak Ada Perubahan");
        ChkAsupanCukup.setBorderPainted(true);
        ChkAsupanCukup.setBorderPaintedFlat(true);
        ChkAsupanCukup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanCukup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanCukup.setName("ChkAsupanCukup"); // NOI18N
        ChkAsupanCukup.setOpaque(false);
        ChkAsupanCukup.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanCukup);
        ChkAsupanCukup.setBounds(260, 474, 215, 23);

        ChkAsupanRendah.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanRendah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanRendah.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanRendah.setText("Asupan Rendah, Tetapi Ada Peningkatan");
        ChkAsupanRendah.setBorderPainted(true);
        ChkAsupanRendah.setBorderPaintedFlat(true);
        ChkAsupanRendah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanRendah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanRendah.setName("ChkAsupanRendah"); // NOI18N
        ChkAsupanRendah.setOpaque(false);
        ChkAsupanRendah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanRendah);
        ChkAsupanRendah.setBounds(485, 474, 230, 23);

        ChkMakanKurang3.setBackground(new java.awt.Color(255, 255, 250));
        ChkMakanKurang3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMakanKurang3.setForeground(new java.awt.Color(0, 0, 0));
        ChkMakanKurang3.setText("Makan <= 3 x Sehari");
        ChkMakanKurang3.setBorderPainted(true);
        ChkMakanKurang3.setBorderPaintedFlat(true);
        ChkMakanKurang3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMakanKurang3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMakanKurang3.setName("ChkMakanKurang3"); // NOI18N
        ChkMakanKurang3.setOpaque(false);
        ChkMakanKurang3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMakanKurang3);
        ChkMakanKurang3.setBounds(115, 502, 135, 23);

        ChkAsupanMenurun.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanMenurun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanMenurun.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanMenurun.setText("Asupan Menurun Tahap Ringan");
        ChkAsupanMenurun.setBorderPainted(true);
        ChkAsupanMenurun.setBorderPaintedFlat(true);
        ChkAsupanMenurun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanMenurun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanMenurun.setName("ChkAsupanMenurun"); // NOI18N
        ChkAsupanMenurun.setOpaque(false);
        ChkAsupanMenurun.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanMenurun);
        ChkAsupanMenurun.setBounds(260, 502, 215, 23);

        ChkAsupanTdkCukup.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanTdkCukup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanTdkCukup.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanTdkCukup.setText("Asupan Tidak Cukup & Menurun Tahap Berat");
        ChkAsupanTdkCukup.setBorderPainted(true);
        ChkAsupanTdkCukup.setBorderPaintedFlat(true);
        ChkAsupanTdkCukup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanTdkCukup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanTdkCukup.setName("ChkAsupanTdkCukup"); // NOI18N
        ChkAsupanTdkCukup.setOpaque(false);
        ChkAsupanTdkCukup.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanTdkCukup);
        ChkAsupanTdkCukup.setBounds(485, 502, 250, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Riwayat Gizi Lain :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 530, 110, 23);

        TriwayatLain.setForeground(new java.awt.Color(0, 0, 0));
        TriwayatLain.setName("TriwayatLain"); // NOI18N
        FormInput.add(TriwayatLain);
        TriwayatLain.setBounds(115, 530, 420, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Hasil Recall Intake : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(540, 530, 120, 23);

        cmbHasilRecal.setForeground(new java.awt.Color(0, 0, 0));
        cmbHasilRecal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0 %", "5 %", "25 %", "50 %", "75 %", "95 %", "100 %" }));
        cmbHasilRecal.setName("cmbHasilRecal"); // NOI18N
        cmbHasilRecal.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbHasilRecal);
        cmbHasilRecal.setBounds(666, 530, 68, 23);

        ChkAlergi.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergi.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergi.setText("Alergi Makanan");
        ChkAlergi.setBorderPainted(true);
        ChkAlergi.setBorderPaintedFlat(true);
        ChkAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAlergi.setName("ChkAlergi"); // NOI18N
        ChkAlergi.setOpaque(false);
        ChkAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergi);
        ChkAlergi.setBounds(115, 558, 105, 23);

        Talergi.setForeground(new java.awt.Color(0, 0, 0));
        Talergi.setName("Talergi"); // NOI18N
        FormInput.add(Talergi);
        Talergi.setBounds(220, 558, 515, 23);

        ChkPantangan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPantangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPantangan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPantangan.setText("Pantangan Makan");
        ChkPantangan.setBorderPainted(true);
        ChkPantangan.setBorderPaintedFlat(true);
        ChkPantangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPantangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPantangan.setName("ChkPantangan"); // NOI18N
        ChkPantangan.setOpaque(false);
        ChkPantangan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPantangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPantanganActionPerformed(evt);
            }
        });
        FormInput.add(ChkPantangan);
        ChkPantangan.setBounds(115, 586, 115, 23);

        Tpantangan.setForeground(new java.awt.Color(0, 0, 0));
        Tpantangan.setName("Tpantangan"); // NOI18N
        FormInput.add(Tpantangan);
        Tpantangan.setBounds(230, 586, 505, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Tcatatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tcatatan.setColumns(20);
        Tcatatan.setRows(5);
        Tcatatan.setName("Tcatatan"); // NOI18N
        Tcatatan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tcatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tcatatan);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(114, 614, 620, 60);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Catatan Penting : ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 614, 110, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nama Ahli Gizi :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 680, 110, 23);

        Tnip.setEditable(false);
        Tnip.setForeground(new java.awt.Color(0, 0, 0));
        Tnip.setName("Tnip"); // NOI18N
        FormInput.add(Tnip);
        Tnip.setBounds(115, 680, 170, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(289, 680, 390, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        FormInput.add(chkSaya);
        chkSaya.setBounds(715, 680, 87, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(680, 680, 28, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Asuhan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(205, 38, 81, 23);

        TtglAsuhan.setEditable(false);
        TtglAsuhan.setForeground(new java.awt.Color(0, 0, 0));
        TtglAsuhan.setName("TtglAsuhan"); // NOI18N
        FormInput.add(TtglAsuhan);
        TtglAsuhan.setBounds(290, 38, 90, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Klasifikasi IMT :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 170, 110, 23);

        cmbKlasifikasiIMT.setForeground(new java.awt.Color(0, 0, 0));
        cmbKlasifikasiIMT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Berdasarkan Kemenkes RI", "Berdasarkan CDC" }));
        cmbKlasifikasiIMT.setName("cmbKlasifikasiIMT"); // NOI18N
        cmbKlasifikasiIMT.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbKlasifikasiIMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKlasifikasiIMTActionPerformed(evt);
            }
        });
        FormInput.add(cmbKlasifikasiIMT);
        cmbKlasifikasiIMT.setBounds(115, 170, 160, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Persentase CDC :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(275, 170, 105, 23);

        ChkBbu.setBackground(new java.awt.Color(255, 255, 250));
        ChkBbu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBbu.setForeground(new java.awt.Color(0, 0, 0));
        ChkBbu.setText("Indeks (BB/U)");
        ChkBbu.setBorderPainted(true);
        ChkBbu.setBorderPaintedFlat(true);
        ChkBbu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBbu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBbu.setName("ChkBbu"); // NOI18N
        ChkBbu.setOpaque(false);
        ChkBbu.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkBbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBbuActionPerformed(evt);
            }
        });
        FormInput.add(ChkBbu);
        ChkBbu.setBounds(115, 198, 160, 23);

        TketBbu.setForeground(new java.awt.Color(0, 0, 0));
        TketBbu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TketBbu.setName("TketBbu"); // NOI18N
        TketBbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketBbuKeyPressed(evt);
            }
        });
        FormInput.add(TketBbu);
        TketBbu.setBounds(280, 198, 70, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Kategori Status Gizi :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(360, 198, 115, 23);

        cmbBbu.setForeground(new java.awt.Color(0, 0, 0));
        cmbBbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "BB Sangat Kurang", "BB Kurang", "BB Normal", "Resiko BB Lebih" }));
        cmbBbu.setName("cmbBbu"); // NOI18N
        cmbBbu.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBbu);
        cmbBbu.setBounds(480, 198, 120, 23);

        ChkPbu.setBackground(new java.awt.Color(255, 255, 250));
        ChkPbu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPbu.setForeground(new java.awt.Color(0, 0, 0));
        ChkPbu.setText("Indeks (PB/U atau TB/U)");
        ChkPbu.setBorderPainted(true);
        ChkPbu.setBorderPaintedFlat(true);
        ChkPbu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPbu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPbu.setName("ChkPbu"); // NOI18N
        ChkPbu.setOpaque(false);
        ChkPbu.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPbuActionPerformed(evt);
            }
        });
        FormInput.add(ChkPbu);
        ChkPbu.setBounds(115, 226, 160, 23);

        TketPbu.setForeground(new java.awt.Color(0, 0, 0));
        TketPbu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TketPbu.setName("TketPbu"); // NOI18N
        TketPbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketPbuKeyPressed(evt);
            }
        });
        FormInput.add(TketPbu);
        TketPbu.setBounds(280, 226, 70, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Kategori Status Gizi :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(360, 226, 115, 23);

        cmbPbu.setForeground(new java.awt.Color(0, 0, 0));
        cmbPbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sangat Pendek", "Pendek", "Normal", "Tinggi" }));
        cmbPbu.setName("cmbPbu"); // NOI18N
        cmbPbu.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPbu);
        cmbPbu.setBounds(480, 226, 110, 23);

        ChkBbpb.setBackground(new java.awt.Color(255, 255, 250));
        ChkBbpb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBbpb.setForeground(new java.awt.Color(0, 0, 0));
        ChkBbpb.setText("Indeks (BB/PB atau BB/TB)");
        ChkBbpb.setBorderPainted(true);
        ChkBbpb.setBorderPaintedFlat(true);
        ChkBbpb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBbpb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBbpb.setName("ChkBbpb"); // NOI18N
        ChkBbpb.setOpaque(false);
        ChkBbpb.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkBbpb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBbpbActionPerformed(evt);
            }
        });
        FormInput.add(ChkBbpb);
        ChkBbpb.setBounds(115, 254, 160, 23);

        TketBbpb.setForeground(new java.awt.Color(0, 0, 0));
        TketBbpb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TketBbpb.setName("TketBbpb"); // NOI18N
        TketBbpb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketBbpbKeyPressed(evt);
            }
        });
        FormInput.add(TketBbpb);
        TketBbpb.setBounds(280, 254, 70, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Kategori Status Gizi :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(360, 254, 115, 23);

        cmbBbpb.setForeground(new java.awt.Color(0, 0, 0));
        cmbBbpb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Gizi Buruk", "Gizi Kurang", "Gizi Baik (Normal)", "Beresiko Gizi Lebih", "Gizi Lebih", "Obesitas" }));
        cmbBbpb.setName("cmbBbpb"); // NOI18N
        cmbBbpb.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBbpb);
        cmbBbpb.setBounds(480, 254, 130, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Jenis Asuhan : ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(555, 66, 90, 23);

        cmbAsuhan.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dewasa", "Anak" }));
        cmbAsuhan.setName("cmbAsuhan"); // NOI18N
        cmbAsuhan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAsuhan);
        cmbAsuhan.setBounds(645, 66, 75, 23);

        Tpersentase.setForeground(new java.awt.Color(0, 0, 0));
        Tpersentase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tpersentase.setName("Tpersentase"); // NOI18N
        Tpersentase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpersentaseKeyPressed(evt);
            }
        });
        FormInput.add(Tpersentase);
        Tpersentase.setBounds(385, 170, 70, 23);

        label114.setForeground(new java.awt.Color(0, 0, 0));
        label114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label114.setText("%");
        label114.setName("label114"); // NOI18N
        label114.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label114);
        label114.setBounds(460, 170, 20, 23);

        BtnCekSttsGizi.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekSttsGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnCekSttsGizi.setMnemonic('I');
        BtnCekSttsGizi.setText("Cek Status Gizi :");
        BtnCekSttsGizi.setToolTipText("Alt+I");
        BtnCekSttsGizi.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnCekSttsGizi.setName("BtnCekSttsGizi"); // NOI18N
        BtnCekSttsGizi.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCekSttsGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekSttsGiziActionPerformed(evt);
            }
        });
        BtnCekSttsGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCekSttsGiziKeyPressed(evt);
            }
        });
        FormInput.add(BtnCekSttsGizi);
        BtnCekSttsGizi.setBounds(490, 170, 128, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Assesmen", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tgl. Assesmen : ");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(jLabel28);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-06-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari1);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("s.d.");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass10.add(jLabel29);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-06-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari2);

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

        internalFrame3.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Assesmen", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (cmbAsuhan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis asuhan gizinya..!!");
            cmbAsuhan.requestFocus();
        } else if (cmbKlasifikasiIMT.getSelectedIndex() == 2 && Tpersentase.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Angka persentase CDC harus diisi dulu..!!");
            Tpersentase.requestFocus();
        } else {
            cekData();
            if (Sequel.menyimpantf("assesmen_gizi_ulang", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat & Tgl. Asesmen", 50, new String[]{
                        TNoRw.getText(), Valid.SetTgl(tglAsesmen.getSelectedItem() + ""), TrgRawat.getText(), Tbb.getText(), Ttb.getText(), Timt.getText(),
                        Tlila.getText(), Ttl.getText(), Tulna.getText(), TtbEst.getText(), TbbKoreksi.getText(), Tbbi.getText(), cmbSttsGizi.getSelectedItem().toString(),
                        Tbiokimia.getText(), mual, nyeri, diare, kesulitan, odema, konstipasi, anoreksia, gangguan, TklinisLain.getText(), makanlebih, makankurang,
                        TriwayatLain.getText(), alergi, Talergi.getText(), pantangan, Tpantangan.getText(), asupanCukup, asupanMenurun, asupanRendah,
                        asupanTidak, cmbHasilRecal.getSelectedItem().toString(), Tcatatan.getText(), Tnip.getText(), Sequel.cariIsi("select now()"),
                        cmbKlasifikasiIMT.getSelectedItem().toString(), Tpersentase.getText(), cmbAsuhan.getSelectedItem().toString(),
                        bbu, pbu, bbpb, TketBbu.getText(), TketPbu.getText(), TketBbpb.getText(), cmbBbu.getSelectedItem().toString(), cmbPbu.getSelectedItem().toString(),
                        cmbBbpb.getSelectedItem().toString()
                    }) == true) {

                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
                TabRawat.setSelectedIndex(1);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } 
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (cmbAsuhan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis asuhan gizinya..!!");
            cmbAsuhan.requestFocus();
        } else if (cmbKlasifikasiIMT.getSelectedIndex() == 2 && Tpersentase.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Angka persentase CDC harus diisi dulu..!!");
            Tpersentase.requestFocus();
        } else {
            if (tbAsesmenGZUlang.getSelectedRow() > -1) {
                ganti();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
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
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        if (Sequel.cariInteger("select count(-1) from assesmen_gizi_ulang where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from assesmen_gizi_ulang where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsesmenGZUlang.getSelectedRow() > -1) {
            hapus();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        } 
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }
            BtnNilaiIMTActionPerformed(null);
            Ttb.requestFocus();
        }
    }//GEN-LAST:event_TbbKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }

            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }
            BtnNilaiIMTActionPerformed(null);
            Tlila.requestFocus();
        }
    }//GEN-LAST:event_TtbKeyPressed

    private void BtnNilaiIMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiIMTActionPerformed
        hitungIMT();
    }//GEN-LAST:event_BtnNilaiIMTActionPerformed

    private void BtnNilaiIMTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiIMTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiIMTActionPerformed(null);
        }
    }//GEN-LAST:event_BtnNilaiIMTKeyPressed

    private void TlilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlilaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tlila.getText().contains(",") == true) {
                Tlila.setText(Tlila.getText().replaceAll(",", "."));
            }

            BtnNilaiBBActionPerformed(null);
            Tbbi.requestFocus();
        }
    }//GEN-LAST:event_TlilaKeyPressed

    private void TbbiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbiKeyPressed
        Valid.pindah(evt, Tlila, Ttl);
    }//GEN-LAST:event_TbbiKeyPressed

    private void TtlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtlKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Ttl.getText().contains(",") == true) {
                Ttl.setText(Ttl.getText().replaceAll(",", "."));
            }

            BtnNilaiTBActionPerformed(null);
            Tulna.requestFocus();
        }
    }//GEN-LAST:event_TtlKeyPressed

    private void TulnaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TulnaKeyPressed
        Valid.pindah(evt, Ttl, TtbEst);
    }//GEN-LAST:event_TulnaKeyPressed

    private void BtnNilaiTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiTBActionPerformed
        hitungTBest();
    }//GEN-LAST:event_BtnNilaiTBActionPerformed

    private void BtnNilaiTBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiTBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiTBActionPerformed(null);
        }
    }//GEN-LAST:event_BtnNilaiTBKeyPressed

    private void TtbEstKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbEstKeyPressed
        Valid.pindah(evt, Tulna, TbbKoreksi);
    }//GEN-LAST:event_TtbEstKeyPressed

    private void BtnNilaiBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiBBActionPerformed
        hitungBBkoreksi();
    }//GEN-LAST:event_BtnNilaiBBActionPerformed

    private void BtnNilaiBBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiBBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiBBActionPerformed(null);
        }
    }//GEN-LAST:event_BtnNilaiBBKeyPressed

    private void TbiokimiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbiokimiaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkMual.requestFocus();
        }
    }//GEN-LAST:event_TbiokimiaKeyPressed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tbiokimia.getText().equals("")) {
                Tbiokimia.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tbiokimia.setText(Tbiokimia.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void ChkAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiActionPerformed
        Talergi.setText("");
        if (ChkAlergi.isSelected() == true) {
            Talergi.setEnabled(true);
            Talergi.requestFocus();
        } else {
            Talergi.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiActionPerformed

    private void ChkPantanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPantanganActionPerformed
        Tpantangan.setText("");
        if (ChkPantangan.isSelected() == true) {
            Tpantangan.setEnabled(true);
            Tpantangan.requestFocus();
        } else {
            Tpantangan.setEnabled(false);
        }
    }//GEN-LAST:event_ChkPantanganActionPerformed

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnPetugas.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                Tnip.setText("-");
                TnmPetugas.setText("-");
            } else {
                Tnip.setText(akses.getkode());
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + Tnip.getText() + "'"));
            }
        } else {
            Tnip.setText("-");
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("DlgAssesmenGiziUlang");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("DlgAssesmenGiziUlang");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbAsesmenGZUlang.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("tglasesmen", "Tgl. Asessmen : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")));
            param.put("tglasuhan", "Tgl. Asuhan Gizi : " + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_asuhan from asuhan_gizi_ranap where no_rawat='" + TNoRw.getText() + "'")));
            param.put("bb", Tbb.getText() + " Kg.");
            param.put("tb", Ttb.getText() + " Cm.");
            param.put("imt", Timt.getText() + " Kg/Cm3");
            param.put("lila", Tlila.getText() + " Cm.");
            param.put("bbi", Tbbi.getText() + " Kg.");
            param.put("tl", Ttl.getText() + " Cm.");
            param.put("ulna", Tulna.getText() + " Cm.");
            param.put("tbest", TtbEst.getText() + " Cm.");
            param.put("bbkoreksi", TtbEst.getText() + " Kg.");
            param.put("biokimia", Tbiokimia.getText() + "\n");
            
            if (cmbAsuhan.getSelectedIndex() == 1) {
                param.put("statusGZ", cmbSttsGizi.getSelectedItem().toString());
            } else if (cmbAsuhan.getSelectedIndex() == 2) {
                if (ChkBbu.isSelected() == true) {
                    gizianak1 = ChkBbu.getText() + " : " + TketBbu.getText() + ", Kategori Status Gizi : " + cmbBbu.getSelectedItem().toString() + "\n";
                } else {
                    gizianak1 = "";
                }
                
                if (ChkPbu.isSelected() == true) {
                    gizianak2 = ChkPbu.getText() + " : " + TketPbu.getText() + ", Kategori Status Gizi : " + cmbPbu.getSelectedItem().toString() + "\n";
                } else {
                    gizianak2 = "";
                }
                
                if (ChkBbpb.isSelected() == true) {
                    gizianak3 = ChkBbpb.getText() + " : " + TketBbpb.getText() + ", Kategori Status Gizi : " + cmbBbpb.getSelectedItem().toString() + "\n";
                } else {
                    gizianak3 = "";
                }
                param.put("statusGZ", gizianak1 + gizianak2 + gizianak3);
            }

            if (ChkMual.isSelected() == true) {
                klinis1 = ChkMual.getText() + "\n";
            } else {
                klinis1 = "";
            }

            if (ChkNyeri.isSelected() == true) {
                klinis2 = ChkNyeri.getText() + "\n";
            } else {
                klinis2 = "";
            }

            if (ChkDiare.isSelected() == true) {
                klinis3 = ChkDiare.getText() + "\n";
            } else {
                klinis3 = "";
            }

            if (ChkKesulitan.isSelected() == true) {
                klinis4 = ChkKesulitan.getText();
            } else {
                klinis4 = "";
            }
            dataKlinis1 = klinis1 + klinis2 + klinis3 + klinis4;

            if (ChkOedema.isSelected() == true) {
                klinis5 = ChkOedema.getText() + "\n";
            } else {
                klinis5 = "";
            }

            if (ChkKonstipasi.isSelected() == true) {
                klinis6 = ChkKonstipasi.getText() + "\n";
            } else {
                klinis6 = "";
            }

            if (ChkAnorek.isSelected() == true) {
                klinis7 = ChkAnorek.getText() + "\n";
            } else {
                klinis7 = "";
            }

            if (ChkGangguan.isSelected() == true) {
                klinis8 = ChkGangguan.getText() + "\n";
            } else {
                klinis8 = "";
            }
            dataKlinis2 = klinis5 + klinis6 + klinis7 + klinis8 + TklinisLain.getText();

            if (ChkMakanLebih3.isSelected() == true) {
                riw1 = ChkMakanLebih3.getText() + "\n";
            } else {
                riw1 = "";
            }

            if (ChkMakanKurang3.isSelected() == true) {
                riw2 = ChkMakanKurang3.getText() + "\n";
            } else {
                riw2 = "";
            }

            if (TriwayatLain.getText().equals("")) {
                TriwayatLain.setText("");
            } else {
                TriwayatLain.setText(TriwayatLain.getText() + "\n");
            }

            if (ChkAlergi.isSelected() == true) {
                riw3 = ChkAlergi.getText() + " : " + Talergi.getText() + "\n";
            } else {
                riw3 = "";
            }

            if (ChkPantangan.isSelected() == true) {
                riw4 = ChkPantangan.getText() + " : " + Tpantangan.getText();
            } else {
                riw4 = "";
            }
            dataRiwayat1 = "RIWAYAT GIZI \n"
            + "Riwayat Makan SMRS \n" + riw1 + riw2 + TriwayatLain.getText() + riw3 + riw4;

            if (ChkAsupanCukup.isSelected() == true) {
                riw5 = ChkAsupanCukup.getText() + "\n";
            } else {
                riw5 = "";
            }

            if (ChkAsupanMenurun.isSelected() == true) {
                riw6 = ChkAsupanMenurun.getText() + "\n";
            } else {
                riw6 = "";
            }

            if (ChkAsupanRendah.isSelected() == true) {
                riw7 = ChkAsupanRendah.getText() + "\n";
            } else {
                riw7 = "";
            }

            if (ChkAsupanTdkCukup.isSelected() == true) {
                riw8 = ChkAsupanTdkCukup.getText() + "\n";
            } else {
                riw8 = "";
            }
            dataRiwayat2 = "\nAsupan Makan SMRS \n" + riw5 + riw6 + riw7 + riw8 + "Hasil Recall Intake : " + cmbHasilRecal.getSelectedItem().toString();

            param.put("dataklinis1", dataKlinis1);
            param.put("dataklinis2", dataKlinis2);
            param.put("dataRiwayat1", dataRiwayat1);
            param.put("dataRiwayat2", dataRiwayat2);
            param.put("catatan", Tcatatan.getText() + "\n");
            param.put("petugas", TnmPetugas.getText());

            Valid.MyReport("rptCetakAsesmenUlangGiziRanap.jasper", "report", "::[ Asesmen Ulang Gizi Rawat Inap ]::",
                "SELECT now() tanggal", param);

            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbAsesmenGZUlang.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void cmbKlasifikasiIMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKlasifikasiIMTActionPerformed
        Tpersentase.setText("");
        if (cmbKlasifikasiIMT.getSelectedIndex() == 2) {
            Tpersentase.setEnabled(true);
            Tpersentase.requestFocus();
        } else {
            Tpersentase.setEnabled(false);
        }
        cekStatusGizi();
    }//GEN-LAST:event_cmbKlasifikasiIMTActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void ChkBbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBbuActionPerformed
        TketBbu.setText("");
        cmbBbu.setSelectedIndex(0);
        if (ChkBbu.isSelected() == true) {
            TketBbu.setEnabled(true);
            cmbBbu.setEnabled(true);
            TketBbu.requestFocus();
        } else {
            TketBbu.setEnabled(false);
            cmbBbu.setEnabled(false);
        }
    }//GEN-LAST:event_ChkBbuActionPerformed

    private void TketBbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketBbuKeyPressed
        Valid.pindah(evt, TketBbu, cmbBbu);
    }//GEN-LAST:event_TketBbuKeyPressed

    private void ChkPbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPbuActionPerformed
        TketPbu.setText("");
        cmbPbu.setSelectedIndex(0);
        if (ChkPbu.isSelected() == true) {
            TketPbu.setEnabled(true);
            cmbPbu.setEnabled(true);
            TketPbu.requestFocus();
        } else {
            TketPbu.setEnabled(false);
            cmbPbu.setEnabled(false);
        }
    }//GEN-LAST:event_ChkPbuActionPerformed

    private void TketPbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketPbuKeyPressed
        Valid.pindah(evt, TketPbu, cmbPbu);
    }//GEN-LAST:event_TketPbuKeyPressed

    private void ChkBbpbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBbpbActionPerformed
        TketBbpb.setText("");
        cmbBbpb.setSelectedIndex(0);
        if (ChkBbpb.isSelected() == true) {
            TketBbpb.setEnabled(true);
            cmbBbpb.setEnabled(true);
            TketBbpb.requestFocus();
        } else {
            TketBbpb.setEnabled(false);
            cmbBbpb.setEnabled(false);
        }
    }//GEN-LAST:event_ChkBbpbActionPerformed

    private void TketBbpbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketBbpbKeyPressed
        Valid.pindah(evt, TketBbpb, cmbBbpb);
    }//GEN-LAST:event_TketBbpbKeyPressed

    private void TpersentaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpersentaseKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tpersentase.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Angka persentase CDC harus terisi dulu..!!");
                Tpersentase.requestFocus();
            } else {
                if (Tpersentase.getText().trim().contains(",") == true) {
                    Tpersentase.setText(Tpersentase.getText().trim().replaceAll(",", "."));
                }

                if (Tpersentase.getText().trim().contains("%") == true) {
                    Tpersentase.setText(Tpersentase.getText().trim().replaceAll("%", ""));
                }
                cekStatusGizi();
                cmbSttsGizi.requestFocus();
            }
        }
    }//GEN-LAST:event_TpersentaseKeyPressed

    private void BtnCekSttsGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekSttsGiziActionPerformed
        cekStatusGizi();
    }//GEN-LAST:event_BtnCekSttsGiziActionPerformed

    private void BtnCekSttsGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCekSttsGiziKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCekSttsGiziActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCekSttsGiziKeyPressed

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
    private widget.Button BtnCekSttsGizi;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNilaiBB;
    private widget.Button BtnNilaiIMT;
    private widget.Button BtnNilaiTB;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAlergi;
    public widget.CekBox ChkAnorek;
    public widget.CekBox ChkAsupanCukup;
    public widget.CekBox ChkAsupanMenurun;
    public widget.CekBox ChkAsupanRendah;
    public widget.CekBox ChkAsupanTdkCukup;
    public widget.CekBox ChkBbpb;
    public widget.CekBox ChkBbu;
    public widget.CekBox ChkDiare;
    public widget.CekBox ChkGangguan;
    public widget.CekBox ChkKesulitan;
    public widget.CekBox ChkKonstipasi;
    public widget.CekBox ChkMakanKurang3;
    public widget.CekBox ChkMakanLebih3;
    public widget.CekBox ChkMual;
    public widget.CekBox ChkNyeri;
    public widget.CekBox ChkOedema;
    public widget.CekBox ChkPantangan;
    public widget.CekBox ChkPbu;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talergi;
    private widget.TextBox Tbb;
    private widget.TextBox TbbKoreksi;
    private widget.TextBox Tbbi;
    private widget.TextArea Tbiokimia;
    private widget.TextArea Tcatatan;
    private widget.TextBox Timt;
    private widget.TextBox Tjk;
    private widget.TextBox TketBbpb;
    private widget.TextBox TketBbu;
    private widget.TextBox TketPbu;
    private widget.TextBox TklinisLain;
    private widget.TextBox Tlila;
    private widget.TextBox Tnip;
    private widget.TextBox TnmPetugas;
    private widget.TextBox Tpantangan;
    private widget.TextBox Tpersentase;
    private widget.TextBox TrgRawat;
    private widget.TextBox TriwayatLain;
    private widget.Label Tsttsumur;
    private widget.TextBox Ttb;
    private widget.TextBox TtbEst;
    private widget.TextBox TtglAsuhan;
    private widget.TextBox Ttl;
    private widget.TextBox Tulna;
    private widget.TextBox Tumur;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbAsuhan;
    private widget.ComboBox cmbBbpb;
    private widget.ComboBox cmbBbu;
    private widget.ComboBox cmbHasilRecal;
    private widget.ComboBox cmbKlasifikasiIMT;
    private widget.ComboBox cmbPbu;
    private widget.ComboBox cmbSttsGizi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel34;
    private widget.Label jLabel6;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel89;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label105;
    private widget.Label label106;
    private widget.Label label107;
    private widget.Label label108;
    private widget.Label label109;
    private widget.Label label110;
    private widget.Label label111;
    private widget.Label label112;
    private widget.Label label113;
    private widget.Label label114;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.Table tbAsesmenGZUlang;
    private widget.Tanggal tglAsesmen;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pg.nama nmPetugas, date_format(ag.tgl_assesmen,'%d-%m-%Y') tgl, "
                    + "if(p.jk='L','Laki-Laki','Perempuan') jk, rp.umurdaftar, rp.sttsumur, TIMESTAMPDIFF(YEAR,p.tgl_lahir, rp.tgl_registrasi) umurTahun, "
                    + "concat(TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi),' ','Bl') umurBulan, ag.* "
                    + "FROM reg_periksa rp inner join assesmen_gizi_ulang ag on ag.no_rawat=rp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg on pg.nik=ag.nip_petugas WHERE "
                    + "ag.tgl_assesmen BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "ag.tgl_assesmen BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "ag.tgl_assesmen BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "ag.tgl_assesmen BETWEEN ? AND ? AND pg.nama LIKE ? ORDER BY ag.tgl_assesmen");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("umurTahun")) <= 5) {
                        cekumur = rs.getString("umurBulan");
                        ceksttsumur = "";
                    } else {
                        if (rs.getString("sttsumur").equals("Bl") || rs.getString("sttsumur").equals("Hr")) {
                            cekumur = rs.getString("umurdaftar") + " " + rs.getString("sttsumur");
                            ceksttsumur = "";
                        } else {
                            cekumur = rs.getString("umurdaftar");
                            ceksttsumur = "Tahun.";
                        }
                    }
                    
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),                        
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur") + ". (" + rs.getString("jenis_asuhan") + ")",
                        rs.getString("jk"),
                        rs.getString("tgl"),
                        rs.getString("ruang_rawat"),
                        rs.getString("nmPetugas"),
                        rs.getString("tgl_assesmen"),
                        rs.getString("ruang_rawat"),
                        rs.getString("bb"),
                        rs.getString("tb"),
                        rs.getString("imt"),
                        rs.getString("lila"),
                        rs.getString("tinggi_lutut"),
                        rs.getString("ulna"),
                        rs.getString("tb_est"),
                        rs.getString("bb_koreksi"),
                        rs.getString("bbi"),
                        rs.getString("status_gizi"),
                        rs.getString("biokimia"),
                        rs.getString("mual_muntah"),
                        rs.getString("nyeri_ulu_hati"),
                        rs.getString("diare"),
                        rs.getString("kesulitan_menelan"),
                        rs.getString("oedema"),
                        rs.getString("konstipasi"),
                        rs.getString("anoreksia"),
                        rs.getString("gangguan_gigi_geligi"),
                        rs.getString("klinis_lainnya"),
                        rs.getString("makan_lebih_3x"),
                        rs.getString("makan_kurang_3x"),
                        rs.getString("riwayat_gizi_lainnya"),
                        rs.getString("alergi_makanan"),
                        rs.getString("ket_alergi_makanan"),
                        rs.getString("pantangan_makan"),
                        rs.getString("ket_pantangan_makan"),
                        rs.getString("asupan_cukup"),
                        rs.getString("asupan_menurun"),
                        rs.getString("asupan_rendah"),
                        rs.getString("asupan_tidak_cukup"),
                        rs.getString("hasil_recall"),
                        rs.getString("catatan"),
                        rs.getString("nip_petugas"),
                        rs.getString("waktu_simpan"),
                        cekumur,
                        ceksttsumur,
                        rs.getString("klasifikasi_imt"),
                        rs.getString("persentase_cdc"),
                        rs.getString("jenis_asuhan"),
                        rs.getString("indek_bbu"),
                        rs.getString("indek_pbu"),
                        rs.getString("indek_bbpb"),
                        rs.getString("ket_indek_bbu"),
                        rs.getString("ket_indek_pbu"),
                        rs.getString("ket_indek_bbpb"),
                        rs.getString("stts_gizi_bbu"),
                        rs.getString("stts_gizi_pbu"),
                        rs.getString("stts_gizi_bbpb")
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
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {   
        Tbb.setText("0");
        Ttb.setText("0");
        Timt.setText("0");
        Tlila.setText("0");
        TtbEst.setText("0");
        Ttl.setText("0");
        TbbKoreksi.setText("0");
        Tulna.setText("0");        
        Tbbi.setText("0");
        cmbAsuhan.setSelectedIndex(0);
        cmbKlasifikasiIMT.setSelectedIndex(0);
        Tpersentase.setText("");
        Tpersentase.setEnabled(false);
        cmbSttsGizi.setSelectedIndex(0);
        
        bbu = "";
        pbu = "";
        bbpb = "";
        TketBbu.setEnabled(false);
        TketPbu.setEnabled(false);
        TketBbpb.setEnabled(false);
        cmbBbu.setEnabled(false);
        cmbPbu.setEnabled(false);
        cmbBbpb.setEnabled(false);
        
        ChkBbu.setSelected(false);
        ChkPbu.setSelected(false);
        ChkBbpb.setSelected(false);
        TketBbu.setText("");
        TketPbu.setText("");
        TketBbpb.setText("");
        cmbBbu.setSelectedIndex(0);
        cmbPbu.setSelectedIndex(0);
        cmbBbpb.setSelectedIndex(0);
        Tbiokimia.setText("");
        
        mual = "";
        nyeri = "";
        diare = "";
        kesulitan = "";
        odema = "";
        konstipasi = "";
        anoreksia = "";
        gangguan = "";
        ChkMual.setSelected(false);
        ChkNyeri.setSelected(false);
        ChkDiare.setSelected(false);
        ChkKesulitan.setSelected(false);
        ChkOedema.setSelected(false);
        ChkKonstipasi.setSelected(false);
        ChkAnorek.setSelected(false);
        ChkGangguan.setSelected(false);
        TklinisLain.setText("");
        
        makanlebih = "";
        makankurang = "";
        asupanCukup = "";
        asupanMenurun = "";
        asupanRendah = "";
        asupanTidak = "";
        alergi = "";
        pantangan = "";
        ChkMakanLebih3.setSelected(false);
        ChkMakanKurang3.setSelected(false);
        ChkAsupanCukup.setSelected(false);
        ChkAsupanMenurun.setSelected(false);
        ChkAsupanRendah.setSelected(false);
        ChkAsupanTdkCukup.setSelected(false);
        
        cmbHasilRecal.setSelectedIndex(0);
        TriwayatLain.setText("");
        ChkAlergi.setSelected(false);
        Talergi.setText("");
        Talergi.setEnabled(false);
        ChkPantangan.setSelected(false);
        Tpantangan.setText("");
        Tpantangan.setEnabled(false);
        Tcatatan.setText("");
        Tnip.setText("-");
        TnmPetugas.setText("-");
        chkSaya.setSelected(false);
    }

    private void getData() {
        variabelBersih();
        if(tbAsesmenGZUlang.getSelectedRow()!= -1){  
            TNoRw.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 2).toString());
            Valid.SetTgl(tglAsesmen, tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 8).toString());
            Tumur.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 45).toString());
            Tsttsumur.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 46).toString());
            Tjk.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 4).toString());
            TrgRawat.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 9).toString());
            Tbb.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 10).toString());
            Ttb.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 11).toString());
            Timt.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 12).toString());
            Tlila.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 13).toString());
            Tbbi.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 18).toString());
            Ttl.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 14).toString());
            Tulna.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 15).toString());
            TtbEst.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 16).toString());
            TbbKoreksi.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 17).toString());
            cmbSttsGizi.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 19).toString());
            Tbiokimia.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 20).toString());
            mual = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 21).toString();
            nyeri = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 22).toString();
            diare = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 23).toString();
            kesulitan = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 24).toString();
            odema = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 25).toString();
            konstipasi = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 26).toString();
            anoreksia = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 27).toString();
            gangguan = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 28).toString();
            TklinisLain.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 29).toString());
            makanlebih = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 30).toString();
            makankurang = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 31).toString();
            TriwayatLain.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 32).toString());
            alergi = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 33).toString();
            Talergi.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 34).toString());
            pantangan = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 35).toString();
            Tpantangan.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 36).toString());
            asupanCukup = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 37).toString();
            asupanMenurun = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 38).toString();
            asupanRendah = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 39).toString();
            asupanTidak = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 40).toString();
            cmbHasilRecal.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 41).toString());
            Tcatatan.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 42).toString());
            Tnip.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 43).toString());
            TnmPetugas.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 7).toString());
            TtglAsuhan.setText(Sequel.cariIsi("select date_format(tgl_asuhan,'%d-%m-%Y') from asuhan_gizi_ranap where no_rawat='" + TNoRw.getText() + "'"));            
            cmbKlasifikasiIMT.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 47).toString());
            Tpersentase.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 48).toString());            
            cmbAsuhan.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 49).toString());
            bbu = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 50).toString();
            pbu = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 51).toString();
            bbpb = tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 52).toString();
            TketBbu.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 53).toString());
            TketPbu.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 54).toString());
            TketBbpb.setText(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 55).toString());
            cmbBbu.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 56).toString());
            cmbPbu.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 57).toString());
            cmbBbpb.setSelectedItem(tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 58).toString());
            dataCek();
            cekStatusGizi();
        }
    }
    
    public void setData(String norwt, String rgrawat, String gedung) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        TrgRawat.setText(rgrawat);
        TtglAsuhan.setText(Sequel.cariIsi("select date_format(tgl_asuhan,'%d-%m-%Y') from asuhan_gizi_ranap where no_rawat='" + norwt + "'"));
        
        if (gedung.equals("ANAK")) {
            cmbAsuhan.setSelectedIndex(2);
        } else if (gedung.equals("AL-HAKIM/PARU") || gedung.equals("AR-RAUDAH") || gedung.equals("AR-RAUDAH ATAS")
                || gedung.equals("AR-RAUDAH BAWAH") || gedung.equals("AS-SAMI") || gedung.equals("BEDAH")
                || gedung.equals("BERSALIN") || gedung.equals("RKPD") || gedung.equals("ZAAL")) {
            cmbAsuhan.setSelectedIndex(1);
        } else {
            cmbAsuhan.setSelectedIndex(0);
        }
        
        isRawat();
        hitungIMT();
    } 
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getassesmen_gizi_ulang());
        BtnGanti.setEnabled(akses.getassesmen_gizi_ulang());
        BtnHapus.setEnabled(akses.getassesmen_gizi_ulang());
    }
    
    private void isRawat() {
        try {
            ps1 = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-Laki','Perempuan') jk, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.tgl_registrasi, rp.umurdaftar, rp.sttsumur, rp.no_rawat, "
                    + "TIMESTAMPDIFF(YEAR,p.tgl_lahir, rp.tgl_registrasi) umurTahun, "
                    + "concat(TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi),' ','Bl') umurBulan "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "WHERE rp.no_rawat = ?");
            try {
                ps1.setString(1, TNoRw.getText());
                rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    Tjk.setText(rs1.getString("jk"));                    
                    Valid.SetTgl(tglAsesmen, rs1.getString("tgl_registrasi"));
                    DTPCari1.setDate(rs1.getDate("tgl_registrasi"));
                    
                    if (Integer.parseInt(rs1.getString("umurTahun")) <= 5) {
                        Tumur.setText(rs1.getString("umurBulan"));
                        Tsttsumur.setText("");
                        Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'0') from penilaian_awal_keperawatan_anak_ranap "
                                + "where no_rawat='" + rs1.getString("no_rawat") + "'"));
                        Ttb.setText(Sequel.cariIsi("select ifnull(tb,'0') from penilaian_awal_keperawatan_anak_ranap "
                                + "where no_rawat='" + rs1.getString("no_rawat") + "'"));
                    } else {
                        if (rs1.getString("sttsumur").equals("Bl") || rs1.getString("sttsumur").equals("Hr")) {
                            Tumur.setText(rs1.getString("umurdaftar") + " " + rs1.getString("sttsumur"));
                            Tsttsumur.setText("");
                            Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'0') from penilaian_awal_keperawatan_anak_ranap "
                                    + "where no_rawat='" + rs1.getString("no_rawat") + "'"));
                            Ttb.setText(Sequel.cariIsi("select ifnull(tb,'0') from penilaian_awal_keperawatan_anak_ranap "
                                    + "where no_rawat='" + rs1.getString("no_rawat") + "'"));
                        } else {
                            Tumur.setText(rs1.getString("umurdaftar"));
                            Tsttsumur.setText("Tahun.");
                            Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'0') from penilaian_awal_keperawatan_dewasa_ranap "
                                    + "where no_rawat='" + rs1.getString("no_rawat") + "'"));
                            Ttb.setText(Sequel.cariIsi("select ifnull(tb,'0') from penilaian_awal_keperawatan_dewasa_ranap "
                                    + "where no_rawat='" + rs1.getString("no_rawat") + "'"));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
    
    private void hitungIMT() {
        try {
            double A, B, C, D, Total;
            if (Tbb.getText().equals("")) {
                Tbb.setText("0");
            }

            if (Ttb.getText().equals("")) {
                Ttb.setText("0");
            }
            
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }
            
            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }

            A = Double.parseDouble(Tbb.getText());
            B = Double.parseDouble(Ttb.getText());
            C = B / 100;
            D = C * C;

            Total = 0;
            Total = A / D;
            
            if (Valid.SetAngka4(Total).equals("NaN")) {
                Timt.setText("0");
            } else {
                Timt.setText(Valid.SetAngka4(Total));
            }
            cekStatusGizi();
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka berat badan & tinggi badannya,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
            Timt.setText("");
        }
    }
    
    private void hitungTBest() {
        try {
            String umurCek = "";
            double A, B, C, D, E, F, totLaki, totPerempuan;
            if (Ttl.getText().equals("")) {
                Ttl.setText("0");
            }

            if (Ttl.getText().contains(",") == true) {
                Ttl.setText(Ttl.getText().replaceAll(",", "."));
            }
            
            if (Tsttsumur.getText().equals("")) {
                umurCek = "0";
            } else {
                umurCek = Tumur.getText();
                if (umurCek.equals("")) {
                    umurCek = "0";
                }

                if (umurCek.contains(",") == true) {
                    umurCek = umurCek.replaceAll(",", ".");
                }
            }

            A = Double.parseDouble(Ttl.getText());
            B = Double.parseDouble(umurCek);
            
            if (Tjk.getText().equals("Laki-Laki")) {
                // rumus laki-laki : (2,02 x TL)-(0,04 x U) + 64,19
                C = 2.02 * A;
                D = 0.04 * B;
                totLaki = C - D + 64.19;

                if (Valid.SetAngka4(totLaki).equals("NaN")) {
                    TtbEst.setText("0");
                } else {
                    TtbEst.setText(Valid.SetAngka4(totLaki));
                }
            } else {
                // rumus perempuan : (1,83 x TL)-(0,24 x U) + 84,88
                E = 1.83 * A;
                F = 0.24 * B;
                totPerempuan = E - F + 84.88;
                
                if (Valid.SetAngka4(totPerempuan).equals("NaN")) {
                    TtbEst.setText("0");
                } else {
                    TtbEst.setText(Valid.SetAngka4(totPerempuan));
                }
            }
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka tinggi lututnya, jika menggunakan    \n"
                    + "koma, gantilah tanda koma dengan titik sebagai komanya !!");
            TtbEst.setText("");
        }
    }
    
    private void hitungBBkoreksi() {
        try {
            double A, B, totBB;
            if (Tlila.getText().equals("")) {
                Tlila.setText("0");
            }

            if (Tlila.getText().contains(",") == true) {
                Tlila.setText(Tlila.getText().replaceAll(",", "."));
            }

            A = Double.parseDouble(Tlila.getText());
            
            if (Tjk.getText().equals("Laki-Laki")) {
                // rumus laki-laki : (2592 x LILA)-12902
                B = 2592 * A;
                totBB = B - 12902;
            } else {
                // rumus perempuan : (2001 x LILA) - 1223
                B = 2001 * A;
                totBB = B - 1223;
            }

            if (Valid.SetAngka4(totBB).equals("NaN")) {
                TbbKoreksi.setText("0");
            } else {
                TbbKoreksi.setText(Valid.SetAngka4(totBB));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka LILAnya, jika menggunakan koma,    \n"
                    + "gantilah tanda koma dengan titik sebagai komanya !!");
            TbbKoreksi.setText("");
        }
    }
    
    private void cekData() {
        // klinis/fisik
        if (ChkMual.isSelected() == true) {
            mual = "ya";
        } else {
            mual = "tidak";
        }
        
        if (ChkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (ChkDiare.isSelected() == true) {
            diare = "ya";
        } else {
            diare = "tidak";
        }
        
        if (ChkKesulitan.isSelected() == true) {
            kesulitan = "ya";
        } else {
            kesulitan = "tidak";
        }
        
        if (ChkOedema.isSelected() == true) {
            odema = "ya";
        } else {
            odema = "tidak";
        }
        
        if (ChkKonstipasi.isSelected() == true) {
            konstipasi = "ya";
        } else {
            konstipasi = "tidak";
        }
        
        if (ChkAnorek.isSelected() == true) {
            anoreksia = "ya";
        } else {
            anoreksia = "tidak";
        }
        
        if (ChkGangguan.isSelected() == true) {
            gangguan = "ya";
        } else {
            gangguan = "tidak";
        }
        
        //riwayat gizi
        if (ChkMakanLebih3.isSelected() == true) {
            makanlebih = "ya";
        } else {
            makanlebih = "tidak";
        }
        
        if (ChkMakanKurang3.isSelected() == true) {
            makankurang = "ya";
        } else {
            makankurang = "tidak";
        }
        
        if (ChkAsupanCukup.isSelected() == true) {
            asupanCukup = "ya";
        } else {
            asupanCukup = "tidak";
        }
        
        if (ChkAsupanMenurun.isSelected() == true) {
            asupanMenurun = "ya";
        } else {
            asupanMenurun = "tidak";
        }
        
        if (ChkAsupanRendah.isSelected() == true) {
            asupanRendah = "ya";
        } else {
            asupanRendah = "tidak";
        }
        
        if (ChkAsupanTdkCukup.isSelected() == true) {
            asupanTidak = "ya";
        } else {
            asupanTidak = "tidak";
        }
        
        if (ChkAlergi.isSelected() == true) {
            alergi = "ya";
        } else {
            alergi = "tidak";
        }
        
        if (ChkPantangan.isSelected() == true) {
            pantangan = "ya";
        } else {
            pantangan = "tidak";
        }
        
        //indek status gizi
        if (ChkBbu.isSelected() == true) {
            bbu = "ya";
        } else {
            bbu = "tidak";
        }
        
        if (ChkPbu.isSelected() == true) {
            pbu = "ya";
        } else {
            pbu = "tidak";
        }
        
        if (ChkBbpb.isSelected() == true) {
            bbpb = "ya";
        } else {
            bbpb = "tidak";
        }
    }
    
    private void dataCek() {
        //klinis /fisik
        if (mual.equals("ya")) {
            ChkMual.setSelected(true);
        } else {
            ChkMual.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            ChkNyeri.setSelected(true);
        } else {
            ChkNyeri.setSelected(false);
        }
        
        if (diare.equals("ya")) {
            ChkDiare.setSelected(true);
        } else {
            ChkDiare.setSelected(false);
        }
        
        if (kesulitan.equals("ya")) {
            ChkKesulitan.setSelected(true);
        } else {
            ChkKesulitan.setSelected(false);
        }
        
        if (odema.equals("ya")) {
            ChkOedema.setSelected(true);
        } else {
            ChkOedema.setSelected(false);
        }
        
        if (konstipasi.equals("ya")) {
            ChkKonstipasi.setSelected(true);
        } else {
            ChkKonstipasi.setSelected(false);
        }
        
        if (anoreksia.equals("ya")) {
            ChkAnorek.setSelected(true);
        } else {
            ChkAnorek.setSelected(false);
        }
        
        if (gangguan.equals("ya")) {
            ChkGangguan.setSelected(true);
        } else {
            ChkGangguan.setSelected(false);
        }
        
        //riwayat gizi
        if (makanlebih.equals("ya")) {
            ChkMakanLebih3.setSelected(true);
        } else {
            ChkMakanLebih3.setSelected(false);
        }
        
        if (makankurang.equals("ya")) {
            ChkMakanKurang3.setSelected(true);
        } else {
            ChkMakanKurang3.setSelected(false);
        }
        
        if (asupanCukup.equals("ya")) {
            ChkAsupanCukup.setSelected(true);
        } else {
            ChkAsupanCukup.setSelected(false);
        }
        
        if (asupanMenurun.equals("ya")) {
            ChkAsupanMenurun.setSelected(true);
        } else {
            ChkAsupanMenurun.setSelected(false);
        }
        
        if (asupanRendah.equals("ya")) {
            ChkAsupanRendah.setSelected(true);
        } else {
            ChkAsupanRendah.setSelected(false);
        }
        
        if (asupanTidak.equals("ya")) {
            ChkAsupanTdkCukup.setSelected(true);
        } else {
            ChkAsupanTdkCukup.setSelected(false);
        }
        
        if (alergi.equals("ya")) {
            ChkAlergi.setSelected(true);
            Talergi.setEnabled(true);
        } else {
            ChkAlergi.setSelected(false);
            Talergi.setEnabled(false);
        }
        
        if (pantangan.equals("ya")) {
            ChkPantangan.setSelected(true);
            Tpantangan.setEnabled(true);
        } else {
            ChkPantangan.setSelected(false);
            Tpantangan.setEnabled(false);
        }
        
        //indek status gizi
        if (bbu.equals("ya")) {
            ChkBbu.setSelected(true);
            TketBbu.setEnabled(true);
            cmbBbu.setEnabled(true);
        } else {
            ChkBbu.setSelected(false);
            TketBbu.setEnabled(false);
            cmbBbu.setEnabled(false);
        }
        
        if (pbu.equals("ya")) {
            ChkPbu.setSelected(true);
            TketPbu.setEnabled(true);
            cmbPbu.setEnabled(true);
        } else {
            ChkPbu.setSelected(false);
            TketPbu.setEnabled(false);
            cmbPbu.setEnabled(false);
        }
        
        if (bbpb.equals("ya")) {
            ChkBbpb.setSelected(true);
            TketBbpb.setEnabled(true);
            cmbBbpb.setEnabled(true);
        } else {
            ChkBbpb.setSelected(false);
            TketBbpb.setEnabled(false);
            cmbBbpb.setEnabled(false);
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from assesmen_gizi_ulang where waktu_simpan=?", 1, new String[]{
                tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 44).toString()
            }) == true) {
                tampil();
                emptTeks();
                TabRawat.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            tampil();
            emptTeks();
        }
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("assesmen_gizi_ulang", "waktu_simpan=?", "tgl_assesmen=?, ruang_rawat=?, bb=?, tb=?, imt=?, lila=?, "
                + "tinggi_lutut=?, ulna=?, tb_est=?, bb_koreksi=?, bbi=?, status_gizi=?, biokimia=?, mual_muntah=?, nyeri_ulu_hati=?, diare=?, "
                + "kesulitan_menelan=?, oedema=?, konstipasi=?, anoreksia=?, gangguan_gigi_geligi=?, klinis_lainnya=?, makan_lebih_3x=?, "
                + "makan_kurang_3x=?, riwayat_gizi_lainnya=?, alergi_makanan=?, ket_alergi_makanan=?, pantangan_makan=?, ket_pantangan_makan=?, "
                + "asupan_cukup=?, asupan_menurun=?, asupan_rendah=?, asupan_tidak_cukup=?, hasil_recall=?, catatan=?, nip_petugas=?, klasifikasi_imt=?, "
                + "persentase_cdc=?, jenis_asuhan=?, indek_bbu=?, indek_pbu=?, indek_bbpb=?, ket_indek_bbu=?, ket_indek_pbu=?, ket_indek_bbpb=?, "
                + "stts_gizi_bbu=?, stts_gizi_pbu=?, stts_gizi_bbpb=?", 49, new String[]{
                    Valid.SetTgl(tglAsesmen.getSelectedItem() + ""), TrgRawat.getText(), Tbb.getText(), Ttb.getText(), Timt.getText(), Tlila.getText(),
                    Ttl.getText(), Tulna.getText(), TtbEst.getText(), TbbKoreksi.getText(), Tbbi.getText(), cmbSttsGizi.getSelectedItem().toString(),
                    Tbiokimia.getText(), mual, nyeri, diare, kesulitan, odema, konstipasi, anoreksia, gangguan, TklinisLain.getText(), makanlebih,
                    makankurang, TriwayatLain.getText(), alergi, Talergi.getText(), pantangan, Tpantangan.getText(), asupanCukup, asupanMenurun,
                    asupanRendah, asupanTidak, cmbHasilRecal.getSelectedItem().toString(), Tcatatan.getText(), Tnip.getText(), cmbKlasifikasiIMT.getSelectedItem().toString(),
                    Tpersentase.getText(), cmbAsuhan.getSelectedItem().toString(), bbu, pbu, bbpb, TketBbu.getText(), TketPbu.getText(),
                    TketBbpb.getText(), cmbBbu.getSelectedItem().toString(), cmbPbu.getSelectedItem().toString(), cmbBbpb.getSelectedItem().toString(),
                    tbAsesmenGZUlang.getValueAt(tbAsesmenGZUlang.getSelectedRow(), 44).toString()
                }) == true) {

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }
    
    private void variabelBersih() {
        mual = "";
        nyeri = "";
        diare = "";
        kesulitan = "";
        odema = "";
        konstipasi = "";
        anoreksia = "";
        gangguan = "";
        makanlebih = "";
        makankurang = "";
        asupanCukup = "";
        asupanMenurun = "";
        asupanRendah = "";
        asupanTidak = "";
        alergi = "";
        pantangan = "";
        bbu = "";
        pbu = "";
        bbpb = "";
    }
    
    private void cekStatusGizi() {
        if (cmbKlasifikasiIMT.getSelectedIndex() == 1) {
            try {
                double nilaiIMT;
                nilaiIMT = 0;
                nilaiIMT = Double.parseDouble(Timt.getText());

                if (nilaiIMT < 18.5) {
                    //kurang
                    cmbSttsGizi.setSelectedIndex(2);
                } else if (nilaiIMT >= 18.5 && nilaiIMT <= 25) {
                    //normal
                    cmbSttsGizi.setSelectedIndex(3);
                } else if (nilaiIMT > 25) {
                    //obesitas
                    cmbSttsGizi.setSelectedIndex(5);
                } else {
                    //-
                    cmbSttsGizi.setSelectedIndex(0);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                cmbSttsGizi.setSelectedIndex(0);
            }
        } else if (cmbKlasifikasiIMT.getSelectedIndex() == 2) {
            try {
                double nilaiPersen;
                nilaiPersen = 0;

                if (Tpersentase.getText().trim().contains(",") == true) {
                    Tpersentase.setText(Tpersentase.getText().trim().replaceAll(",", "."));
                }

                if (Tpersentase.getText().trim().contains("%") == true) {
                    Tpersentase.setText(Tpersentase.getText().trim().replaceAll("%", ""));
                }

                nilaiPersen = Double.parseDouble(Tpersentase.getText());

                if (nilaiPersen < 69.5) {
                    //gizi baik
                    cmbSttsGizi.setSelectedIndex(6);
                } else if (nilaiPersen >= 69.5 && nilaiPersen <= 89.4) {
                    //gizi kurang
                    cmbSttsGizi.setSelectedIndex(2);
                } else if (nilaiPersen >= 89.5 && nilaiPersen <= 110.4) {
                    //normal
                    cmbSttsGizi.setSelectedIndex(3);
                } else if (nilaiPersen >= 110.5 && nilaiPersen <= 120.4) {
                    //lebih
                    cmbSttsGizi.setSelectedIndex(4);
                } else if (nilaiPersen >= 120.5) {
                    //obesitas
                    cmbSttsGizi.setSelectedIndex(5);
                } else {
                    //-
                    cmbSttsGizi.setSelectedIndex(0);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                cmbSttsGizi.setSelectedIndex(0);
            }

        } else {
            cmbSttsGizi.setSelectedIndex(0);
        }
    }
}
