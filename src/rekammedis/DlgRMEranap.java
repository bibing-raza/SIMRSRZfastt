package rekammedis;

import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResepBiasaAntibiotik;
import inventory.DlgPemberianObatPasien;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import permintaan.DlgSuratJawabanKonsul;
import permintaan.DlgSuratKonsulUnit;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgRingkasanPulangRanap;

/**
 *
 * @author dosen
 */
public class DlgRMEranap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0, jmlmenu = 0, grid = 0, tinggi = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String stts = "", kdkamar = "", gedung = "", queryDinamis = "", sqlPembentuk = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgRMEranap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No.", "Dokumen Rekam Medis", "Terisi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRMranap.setModel(tabMode);
        tbRMranap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRMranap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbRMranap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(280);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } 
        }
        tbRMranap.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRMranap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRMranap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "No.", "RM Masalah Keperawatan", "Terisi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRMmaskep.setModel(tabMode1);
        tbRMmaskep.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRMmaskep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbRMmaskep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(280);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } 
        }
        tbRMmaskep.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRMmaskep.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRMmaskep.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "No.", "Dokumen Rekam Medis", "Terisi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRMibs.setModel(tabMode2);
        tbRMibs.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRMibs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbRMibs.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(280);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } 
        }
        tbRMibs.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRMibs.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRMibs.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRMEranap")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        btnDPJP.requestFocus();                        
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
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WindowDPJPranap = new javax.swing.JDialog();
        internalFrame15 = new widget.InternalFrame();
        BtnCloseIn10 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel52 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        btnDPJP = new widget.Button();
        jLabel5 = new widget.Label();
        TCari = new widget.TextBox();
        btnClear = new widget.Button();
        ChkInput = new widget.CekBox();
        internalFrame1 = new widget.InternalFrame();
        internalFrame4 = new widget.InternalFrame();
        jLabel3 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        jLabel13 = new widget.Label();
        nmUnit = new widget.TextBox();
        jLabel4 = new widget.Label();
        TtglMasuk = new widget.TextBox();
        internalFrame2 = new widget.InternalFrame();
        TabRekamMedis = new javax.swing.JTabbedPane();
        internalFrame5 = new widget.InternalFrame();
        scrolMenuRanap = new widget.ScrollPane();
        FormMenuRanap = new widget.PanelBiasa();
        BtnPersetujuanRanap = new widget.ButtonBig();
        BtnGeneralConsent = new widget.ButtonBig();
        BtnSuratPernyataanRanapBpjs = new widget.ButtonBig();
        BtnSuratPernyataanNaikKelas = new widget.ButtonBig();
        BtnSuratPernyataanBukanKLL = new widget.ButtonBig();
        BtnSuratPernyataanBayarDenda = new widget.ButtonBig();
        BtnSuratPernyataanRanapNonBpjs = new widget.ButtonBig();
        BtnAsesmenRestrain = new widget.ButtonBig();
        BtnObservasiRestrain = new widget.ButtonBig();
        BtnAsesmenMedikBedah = new widget.ButtonBig();
        BtnAsesmenMedikDewasa = new widget.ButtonBig();
        BtnAsesmenMedikAnak = new widget.ButtonBig();
        BtnAsesmenMedikPerinatologi = new widget.ButtonBig();
        BtnAsesmenKeperawatanDewasa = new widget.ButtonBig();
        BtnAsesmenKeperawatanAnak = new widget.ButtonBig();
        BtnAsesmenKeperawatanPerinatologi = new widget.ButtonBig();
        BtnCPPT = new widget.ButtonBig();
        BtnResep = new widget.ButtonBig();
        BtnRingkasan = new widget.ButtonBig();
        BtnCTK = new widget.ButtonBig();
        BtnAsesmenUlangRJDewasa = new widget.ButtonBig();
        BtnAsesmenUlangRJAnak = new widget.ButtonBig();
        BtnTransferSerahTerima = new widget.ButtonBig();
        BtnLembarObservasi = new widget.ButtonBig();
        BtnObservasiKala1 = new widget.ButtonBig();
        BtnPengelolaanTranfusiDarah = new widget.ButtonBig();
        BtnMonitoringEWSdewasa = new widget.ButtonBig();
        BtnMonitoringPEWSanak = new widget.ButtonBig();
        BtnMonitoringEWSobsgyn = new widget.ButtonBig();
        BtnScoreApgarPerinatologi = new widget.ButtonBig();
        BtnPengamatanMenyusui = new widget.ButtonBig();
        BtnSerahTerimaBayiPulang = new widget.ButtonBig();
        BtnRekonsiliasiObat = new widget.ButtonBig();
        BtnPemberianInformasiEdukasi = new widget.ButtonBig();
        BtnJadwalObat = new widget.ButtonBig();
        BtnTransferTindakan = new widget.ButtonBig();
        BtnPerencanaanPulang = new widget.ButtonBig();
        BtnKonsul = new widget.ButtonBig();
        BtnJawabKonsul = new widget.ButtonBig();
        BtnPersetujuanTindakan = new widget.ButtonBig();
        BtnPantauHarianPasien = new widget.ButtonBig();
        BtnGrafikPantauHarian = new widget.ButtonBig();
        BtnProtokolKemoterapi = new widget.ButtonBig();
        BtnDokumenJangMed = new widget.ButtonBig();
        BtnSkriningUlangGizi = new widget.ButtonBig();
        BtnAsuhanGizi = new widget.ButtonBig();
        BtnMonevAsuhanGizi = new widget.ButtonBig();
        BtnAsesmenUlangGizi = new widget.ButtonBig();
        BtnSamplingPemanfaatanRM = new widget.ButtonBig();
        internalFrame10 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRMranap = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        BtnRefres = new widget.Button();
        BtnKeluar = new widget.Button();
        internalFrame6 = new widget.InternalFrame();
        scrolMenuMaskep = new widget.ScrollPane();
        FormMasKep = new widget.PanelBiasa();
        BtnMaskepNyeriAkut = new widget.ButtonBig();
        BtnMaskepPerfusiPerifer = new widget.ButtonBig();
        BtnMaskepResikoHipotermia = new widget.ButtonBig();
        BtnMaskepResikoHipovolemia = new widget.ButtonBig();
        BtnMaskepBersihanJalanNafas = new widget.ButtonBig();
        BtnMaskepKetidakstabilanGlukosa = new widget.ButtonBig();
        internalFrame11 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbRMmaskep = new widget.Table();
        internalFrame7 = new widget.InternalFrame();
        jLabel6 = new widget.Label();
        TCari1 = new widget.TextBox();
        btnClear1 = new widget.Button();
        ChkInput1 = new widget.CekBox();
        BtnRefres1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        internalFrame8 = new widget.InternalFrame();
        internalFrame12 = new widget.InternalFrame();
        scrollInput1 = new widget.ScrollPane();
        FormIBS = new widget.PanelBiasa();
        BtnEvaluasiPraAnestesi = new widget.ButtonBig();
        BtnCeklisPraOperasi = new widget.ButtonBig();
        BtnFormulirSiteMarking = new widget.ButtonBig();
        BtnCeklisKesiapanAnestesi = new widget.ButtonBig();
        BtnAsesmenPraSedasiKonsepIAR = new widget.ButtonBig();
        BtnAsesmenPreInduksi = new widget.ButtonBig();
        BtnAsesmenKeperawatanPerioperatif = new widget.ButtonBig();
        BtnCeklisKeselamatanOperasi = new widget.ButtonBig();
        BtnCatatanSedasiAnestesi = new widget.ButtonBig();
        BtnLaporanOperasi = new widget.ButtonBig();
        BtnCatatanMaterial = new widget.ButtonBig();
        BtnCatatanRuangPemulihan = new widget.ButtonBig();
        BtnSerahTerimaPasca = new widget.ButtonBig();
        BtnInformasiTindakanPembiusan = new widget.ButtonBig();
        BtnPersetujuanTindakanIBS = new widget.ButtonBig();
        BtnPemberianInformasiEdukasiIBS = new widget.ButtonBig();
        BtnAsesmenPraSedasi = new widget.ButtonBig();
        BtnTransferTindakanIBS = new widget.ButtonBig();
        internalFrame13 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbRMibs = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        BtnRefres2 = new widget.Button();
        BtnKeluar2 = new widget.Button();

        WindowDPJPranap.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDPJPranap.setName("WindowDPJPranap"); // NOI18N
        WindowDPJPranap.setUndecorated(true);
        WindowDPJPranap.setResizable(false);

        internalFrame15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ DPJP Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(null);

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setMnemonic('U');
        BtnCloseIn10.setText("Tutup");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnCloseIn10);
        BtnCloseIn10.setBounds(480, 60, 100, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnSimpan6);
        BtnSimpan6.setBounds(370, 60, 100, 30);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Nama DPJP :");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame15.add(jLabel52);
        jLabel52.setBounds(0, 32, 77, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        internalFrame15.add(kddpjp);
        kddpjp.setBounds(81, 32, 100, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        internalFrame15.add(nmdpjp);
        nmdpjp.setBounds(183, 32, 380, 23);

        btnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJP.setMnemonic('7');
        btnDPJP.setToolTipText("ALt+7");
        btnDPJP.setName("btnDPJP"); // NOI18N
        btnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPActionPerformed(evt);
            }
        });
        internalFrame15.add(btnDPJP);
        btnDPJP.setBounds(565, 32, 28, 23);

        WindowDPJPranap.getContentPane().add(internalFrame15, java.awt.BorderLayout.CENTER);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Cari Menu :");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 23));

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(280, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });

        btnClear.setForeground(new java.awt.Color(0, 0, 0));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        btnClear.setMinimumSize(new java.awt.Dimension(28, 23));
        btnClear.setName("btnClear"); // NOI18N
        btnClear.setPreferredSize(new java.awt.Dimension(30, 30));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        ChkInput.setBorder(null);
        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('C');
        ChkInput.setSelected(true);
        ChkInput.setText("Pencarian");
        ChkInput.setToolTipText("Alt+C");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setOpaque(false);
        ChkInput.setPreferredSize(new java.awt.Dimension(100, 30));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Rekam Medis Elektronik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout());

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setPreferredSize(new java.awt.Dimension(12, 100));
        internalFrame4.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        internalFrame4.add(jLabel3);
        jLabel3.setBounds(0, 10, 103, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        internalFrame4.add(TNoRW);
        TNoRW.setBounds(107, 10, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        internalFrame4.add(TNoRM);
        TNoRM.setBounds(233, 10, 70, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        internalFrame4.add(TNmPasien);
        TNmPasien.setBounds(308, 10, 291, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Rg. Rawat :");
        jLabel13.setName("jLabel13"); // NOI18N
        internalFrame4.add(jLabel13);
        jLabel13.setBounds(0, 38, 103, 23);

        nmUnit.setEditable(false);
        nmUnit.setForeground(new java.awt.Color(0, 0, 0));
        nmUnit.setName("nmUnit"); // NOI18N
        internalFrame4.add(nmUnit);
        nmUnit.setBounds(107, 38, 492, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tgl. Masuk :");
        jLabel4.setName("jLabel4"); // NOI18N
        internalFrame4.add(jLabel4);
        jLabel4.setBounds(0, 66, 103, 23);

        TtglMasuk.setEditable(false);
        TtglMasuk.setForeground(new java.awt.Color(0, 0, 0));
        TtglMasuk.setName("TtglMasuk"); // NOI18N
        internalFrame4.add(TtglMasuk);
        TtglMasuk.setBounds(107, 66, 210, 23);

        internalFrame1.add(internalFrame4, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(2800, 2800));
        internalFrame2.setLayout(new java.awt.BorderLayout());

        TabRekamMedis.setBackground(new java.awt.Color(255, 255, 254));
        TabRekamMedis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRekamMedis.setName("TabRekamMedis"); // NOI18N
        TabRekamMedis.setPreferredSize(new java.awt.Dimension(270, 106));
        TabRekamMedis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRekamMedisMouseClicked(evt);
            }
        });

        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setPreferredSize(new java.awt.Dimension(12, 44));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        scrolMenuRanap.setName("scrolMenuRanap"); // NOI18N
        scrolMenuRanap.setPreferredSize(new java.awt.Dimension(102, 557));

        FormMenuRanap.setBackground(new java.awt.Color(255, 255, 255));
        FormMenuRanap.setBorder(null);
        FormMenuRanap.setName("FormMenuRanap"); // NOI18N
        FormMenuRanap.setPreferredSize(new java.awt.Dimension(870, 1250));
        FormMenuRanap.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 25));

        BtnPersetujuanRanap.setForeground(new java.awt.Color(0, 0, 0));
        BtnPersetujuanRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnPersetujuanRanap.setText("Persetujuan Rawat Inap");
        BtnPersetujuanRanap.setIconTextGap(0);
        BtnPersetujuanRanap.setName("BtnPersetujuanRanap"); // NOI18N
        BtnPersetujuanRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnPersetujuanRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPersetujuanRanapActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnPersetujuanRanap);

        BtnGeneralConsent.setForeground(new java.awt.Color(0, 0, 0));
        BtnGeneralConsent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnGeneralConsent.setText("General Consent");
        BtnGeneralConsent.setIconTextGap(0);
        BtnGeneralConsent.setName("BtnGeneralConsent"); // NOI18N
        BtnGeneralConsent.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnGeneralConsent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGeneralConsentActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnGeneralConsent);

        BtnSuratPernyataanRanapBpjs.setForeground(new java.awt.Color(0, 0, 0));
        BtnSuratPernyataanRanapBpjs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnSuratPernyataanRanapBpjs.setText("<html><div style=\"text-align: center;\">Surat Pernyataan<br>Rawat Inap Peserta BPJS</div></html>");
        BtnSuratPernyataanRanapBpjs.setIconTextGap(0);
        BtnSuratPernyataanRanapBpjs.setName("BtnSuratPernyataanRanapBpjs"); // NOI18N
        BtnSuratPernyataanRanapBpjs.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnSuratPernyataanRanapBpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuratPernyataanRanapBpjsActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnSuratPernyataanRanapBpjs);

        BtnSuratPernyataanNaikKelas.setForeground(new java.awt.Color(0, 0, 0));
        BtnSuratPernyataanNaikKelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnSuratPernyataanNaikKelas.setText("<html><div style=\"text-align: center;\">Surat Pernyataan<br>Naik Kelas Rawat BPJS</div></html>");
        BtnSuratPernyataanNaikKelas.setIconTextGap(0);
        BtnSuratPernyataanNaikKelas.setName("BtnSuratPernyataanNaikKelas"); // NOI18N
        BtnSuratPernyataanNaikKelas.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnSuratPernyataanNaikKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuratPernyataanNaikKelasActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnSuratPernyataanNaikKelas);

        BtnSuratPernyataanBukanKLL.setForeground(new java.awt.Color(0, 0, 0));
        BtnSuratPernyataanBukanKLL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnSuratPernyataanBukanKLL.setText("Surat Pernyataan Bukan KLL");
        BtnSuratPernyataanBukanKLL.setIconTextGap(0);
        BtnSuratPernyataanBukanKLL.setName("BtnSuratPernyataanBukanKLL"); // NOI18N
        BtnSuratPernyataanBukanKLL.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnSuratPernyataanBukanKLL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuratPernyataanBukanKLLActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnSuratPernyataanBukanKLL);

        BtnSuratPernyataanBayarDenda.setForeground(new java.awt.Color(0, 0, 0));
        BtnSuratPernyataanBayarDenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnSuratPernyataanBayarDenda.setText("Surat Pernyataan Bayar Denda");
        BtnSuratPernyataanBayarDenda.setIconTextGap(0);
        BtnSuratPernyataanBayarDenda.setName("BtnSuratPernyataanBayarDenda"); // NOI18N
        BtnSuratPernyataanBayarDenda.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnSuratPernyataanBayarDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuratPernyataanBayarDendaActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnSuratPernyataanBayarDenda);

        BtnSuratPernyataanRanapNonBpjs.setForeground(new java.awt.Color(0, 0, 0));
        BtnSuratPernyataanRanapNonBpjs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnSuratPernyataanRanapNonBpjs.setText("Surat Pernyataan Non BPJS/Umum");
        BtnSuratPernyataanRanapNonBpjs.setIconTextGap(0);
        BtnSuratPernyataanRanapNonBpjs.setName("BtnSuratPernyataanRanapNonBpjs"); // NOI18N
        BtnSuratPernyataanRanapNonBpjs.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnSuratPernyataanRanapNonBpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuratPernyataanRanapNonBpjsActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnSuratPernyataanRanapNonBpjs);

        BtnAsesmenRestrain.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenRestrain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenRestrain.setText("Asesmen Restrain");
        BtnAsesmenRestrain.setToolTipText("");
        BtnAsesmenRestrain.setIconTextGap(0);
        BtnAsesmenRestrain.setName("BtnAsesmenRestrain"); // NOI18N
        BtnAsesmenRestrain.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenRestrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenRestrainActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenRestrain);

        BtnObservasiRestrain.setForeground(new java.awt.Color(0, 0, 0));
        BtnObservasiRestrain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        BtnObservasiRestrain.setText("Observasi Restrain");
        BtnObservasiRestrain.setToolTipText("");
        BtnObservasiRestrain.setIconTextGap(0);
        BtnObservasiRestrain.setName("BtnObservasiRestrain"); // NOI18N
        BtnObservasiRestrain.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnObservasiRestrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObservasiRestrainActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnObservasiRestrain);

        BtnAsesmenMedikBedah.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenMedikBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenMedikBedah.setText("Asesmen Medik Bedah");
        BtnAsesmenMedikBedah.setToolTipText("");
        BtnAsesmenMedikBedah.setIconTextGap(0);
        BtnAsesmenMedikBedah.setName("BtnAsesmenMedikBedah"); // NOI18N
        BtnAsesmenMedikBedah.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenMedikBedah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenMedikBedahActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenMedikBedah);

        BtnAsesmenMedikDewasa.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenMedikDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenMedikDewasa.setText("Asesmen Medik Dewasa");
        BtnAsesmenMedikDewasa.setToolTipText("");
        BtnAsesmenMedikDewasa.setIconTextGap(0);
        BtnAsesmenMedikDewasa.setName("BtnAsesmenMedikDewasa"); // NOI18N
        BtnAsesmenMedikDewasa.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenMedikDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenMedikDewasaActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenMedikDewasa);

        BtnAsesmenMedikAnak.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenMedikAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenMedikAnak.setText("Asesmen Medik Anak");
        BtnAsesmenMedikAnak.setToolTipText("");
        BtnAsesmenMedikAnak.setIconTextGap(0);
        BtnAsesmenMedikAnak.setName("BtnAsesmenMedikAnak"); // NOI18N
        BtnAsesmenMedikAnak.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenMedikAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenMedikAnakActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenMedikAnak);

        BtnAsesmenMedikPerinatologi.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenMedikPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenMedikPerinatologi.setText("Asesmen Medik Perinatologi");
        BtnAsesmenMedikPerinatologi.setToolTipText("");
        BtnAsesmenMedikPerinatologi.setIconTextGap(0);
        BtnAsesmenMedikPerinatologi.setName("BtnAsesmenMedikPerinatologi"); // NOI18N
        BtnAsesmenMedikPerinatologi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenMedikPerinatologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenMedikPerinatologiActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenMedikPerinatologi);

        BtnAsesmenKeperawatanDewasa.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenKeperawatanDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenKeperawatanDewasa.setText("<html><div style=\"text-align: center;\">Asesmen Keperawatan<br>Dewasa</div></html>");
        BtnAsesmenKeperawatanDewasa.setIconTextGap(0);
        BtnAsesmenKeperawatanDewasa.setName("BtnAsesmenKeperawatanDewasa"); // NOI18N
        BtnAsesmenKeperawatanDewasa.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnAsesmenKeperawatanDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenKeperawatanDewasaActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenKeperawatanDewasa);

        BtnAsesmenKeperawatanAnak.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenKeperawatanAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenKeperawatanAnak.setText("Asesmen Keperawatan Anak");
        BtnAsesmenKeperawatanAnak.setIconTextGap(0);
        BtnAsesmenKeperawatanAnak.setName("BtnAsesmenKeperawatanAnak"); // NOI18N
        BtnAsesmenKeperawatanAnak.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenKeperawatanAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenKeperawatanAnakActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenKeperawatanAnak);

        BtnAsesmenKeperawatanPerinatologi.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenKeperawatanPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenKeperawatanPerinatologi.setText("<html><div style=\"text-align: center;\">Asesmen Keperawatan<br>Perinatologi</div></html>");
        BtnAsesmenKeperawatanPerinatologi.setIconTextGap(0);
        BtnAsesmenKeperawatanPerinatologi.setName("BtnAsesmenKeperawatanPerinatologi"); // NOI18N
        BtnAsesmenKeperawatanPerinatologi.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnAsesmenKeperawatanPerinatologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenKeperawatanPerinatologiActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenKeperawatanPerinatologi);

        BtnCPPT.setForeground(new java.awt.Color(0, 0, 0));
        BtnCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnCPPT.setText("CPPT");
        BtnCPPT.setIconTextGap(0);
        BtnCPPT.setName("BtnCPPT"); // NOI18N
        BtnCPPT.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnCPPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCPPTActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnCPPT);

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_basket_8726.png"))); // NOI18N
        BtnResep.setText("Resep Obat");
        BtnResep.setIconTextGap(0);
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnResep);

        BtnRingkasan.setForeground(new java.awt.Color(0, 0, 0));
        BtnRingkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnRingkasan.setText("Ringkasan Pulang / Resume");
        BtnRingkasan.setIconTextGap(0);
        BtnRingkasan.setName("BtnRingkasan"); // NOI18N
        BtnRingkasan.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnRingkasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRingkasanActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnRingkasan);

        BtnCTK.setForeground(new java.awt.Color(0, 0, 0));
        BtnCTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnCTK.setText("<html><div style=\"text-align: center;\">Catatan Tindakan<br>Keperawatan/Kebidanan</div></html>");
        BtnCTK.setIconTextGap(0);
        BtnCTK.setName("BtnCTK"); // NOI18N
        BtnCTK.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnCTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCTKActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnCTK);

        BtnAsesmenUlangRJDewasa.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenUlangRJDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenUlangRJDewasa.setText("<html><div style=\"text-align: center;\">Asesmen Ulang Resiko<br>Jatuh Dewasa</div></html>");
        BtnAsesmenUlangRJDewasa.setIconTextGap(0);
        BtnAsesmenUlangRJDewasa.setName("BtnAsesmenUlangRJDewasa"); // NOI18N
        BtnAsesmenUlangRJDewasa.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnAsesmenUlangRJDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenUlangRJDewasaActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenUlangRJDewasa);

        BtnAsesmenUlangRJAnak.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenUlangRJAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenUlangRJAnak.setText("<html><div style=\"text-align: center;\">Asesmen Ulang Resiko<br>Jatuh Anak</div></html>");
        BtnAsesmenUlangRJAnak.setIconTextGap(0);
        BtnAsesmenUlangRJAnak.setName("BtnAsesmenUlangRJAnak"); // NOI18N
        BtnAsesmenUlangRJAnak.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnAsesmenUlangRJAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenUlangRJAnakActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenUlangRJAnak);

        BtnTransferSerahTerima.setForeground(new java.awt.Color(0, 0, 0));
        BtnTransferSerahTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnTransferSerahTerima.setText("Transfer & Serah Terima Pasien");
        BtnTransferSerahTerima.setIconTextGap(0);
        BtnTransferSerahTerima.setName("BtnTransferSerahTerima"); // NOI18N
        BtnTransferSerahTerima.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnTransferSerahTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTransferSerahTerimaActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnTransferSerahTerima);

        BtnLembarObservasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnLembarObservasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist_pencil-o.png"))); // NOI18N
        BtnLembarObservasi.setText("Lembar Observasi");
        BtnLembarObservasi.setIconTextGap(0);
        BtnLembarObservasi.setName("BtnLembarObservasi"); // NOI18N
        BtnLembarObservasi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnLembarObservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLembarObservasiActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnLembarObservasi);

        BtnObservasiKala1.setForeground(new java.awt.Color(0, 0, 0));
        BtnObservasiKala1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist_pencil-o.png"))); // NOI18N
        BtnObservasiKala1.setText("Observasi Kala 1 (Kebidanan)");
        BtnObservasiKala1.setIconTextGap(0);
        BtnObservasiKala1.setName("BtnObservasiKala1"); // NOI18N
        BtnObservasiKala1.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnObservasiKala1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObservasiKala1ActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnObservasiKala1);

        BtnPengelolaanTranfusiDarah.setForeground(new java.awt.Color(0, 0, 0));
        BtnPengelolaanTranfusiDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481001585_blood_drop_biru.png"))); // NOI18N
        BtnPengelolaanTranfusiDarah.setText("Pengelolaan Transfusi Darah");
        BtnPengelolaanTranfusiDarah.setIconTextGap(0);
        BtnPengelolaanTranfusiDarah.setName("BtnPengelolaanTranfusiDarah"); // NOI18N
        BtnPengelolaanTranfusiDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnPengelolaanTranfusiDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPengelolaanTranfusiDarahActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnPengelolaanTranfusiDarah);

        BtnMonitoringEWSdewasa.setForeground(new java.awt.Color(0, 0, 0));
        BtnMonitoringEWSdewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png"))); // NOI18N
        BtnMonitoringEWSdewasa.setText("Monitoring EWS Dewasa");
        BtnMonitoringEWSdewasa.setIconTextGap(0);
        BtnMonitoringEWSdewasa.setName("BtnMonitoringEWSdewasa"); // NOI18N
        BtnMonitoringEWSdewasa.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMonitoringEWSdewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMonitoringEWSdewasaActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnMonitoringEWSdewasa);

        BtnMonitoringPEWSanak.setForeground(new java.awt.Color(0, 0, 0));
        BtnMonitoringPEWSanak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png"))); // NOI18N
        BtnMonitoringPEWSanak.setText("Monitoring Pediatric EWS");
        BtnMonitoringPEWSanak.setIconTextGap(0);
        BtnMonitoringPEWSanak.setName("BtnMonitoringPEWSanak"); // NOI18N
        BtnMonitoringPEWSanak.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMonitoringPEWSanak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMonitoringPEWSanakActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnMonitoringPEWSanak);

        BtnMonitoringEWSobsgyn.setForeground(new java.awt.Color(0, 0, 0));
        BtnMonitoringEWSobsgyn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png"))); // NOI18N
        BtnMonitoringEWSobsgyn.setText("Monitoring EWS Obsgyn");
        BtnMonitoringEWSobsgyn.setIconTextGap(0);
        BtnMonitoringEWSobsgyn.setName("BtnMonitoringEWSobsgyn"); // NOI18N
        BtnMonitoringEWSobsgyn.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMonitoringEWSobsgyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMonitoringEWSobsgynActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnMonitoringEWSobsgyn);

        BtnScoreApgarPerinatologi.setForeground(new java.awt.Color(0, 0, 0));
        BtnScoreApgarPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/score_icon.png"))); // NOI18N
        BtnScoreApgarPerinatologi.setText("Skor Apgar, Downe & Cap Jari");
        BtnScoreApgarPerinatologi.setIconTextGap(0);
        BtnScoreApgarPerinatologi.setName("BtnScoreApgarPerinatologi"); // NOI18N
        BtnScoreApgarPerinatologi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnScoreApgarPerinatologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnScoreApgarPerinatologiActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnScoreApgarPerinatologi);

        BtnPengamatanMenyusui.setForeground(new java.awt.Color(0, 0, 0));
        BtnPengamatanMenyusui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/ibu_menyusui.png"))); // NOI18N
        BtnPengamatanMenyusui.setText("Bantuan Pengamatan Menyusui");
        BtnPengamatanMenyusui.setIconTextGap(0);
        BtnPengamatanMenyusui.setName("BtnPengamatanMenyusui"); // NOI18N
        BtnPengamatanMenyusui.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnPengamatanMenyusui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPengamatanMenyusuiActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnPengamatanMenyusui);

        BtnSerahTerimaBayiPulang.setForeground(new java.awt.Color(0, 0, 0));
        BtnSerahTerimaBayiPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/baby-boy.png"))); // NOI18N
        BtnSerahTerimaBayiPulang.setText("Serah Terima Bayi Pulang");
        BtnSerahTerimaBayiPulang.setIconTextGap(0);
        BtnSerahTerimaBayiPulang.setName("BtnSerahTerimaBayiPulang"); // NOI18N
        BtnSerahTerimaBayiPulang.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnSerahTerimaBayiPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSerahTerimaBayiPulangActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnSerahTerimaBayiPulang);

        BtnRekonsiliasiObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnRekonsiliasiObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_pot_pills.png"))); // NOI18N
        BtnRekonsiliasiObat.setText("Rekonsiliasi Obat");
        BtnRekonsiliasiObat.setIconTextGap(0);
        BtnRekonsiliasiObat.setName("BtnRekonsiliasiObat"); // NOI18N
        BtnRekonsiliasiObat.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnRekonsiliasiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRekonsiliasiObatActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnRekonsiliasiObat);

        BtnPemberianInformasiEdukasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPemberianInformasiEdukasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/people.png"))); // NOI18N
        BtnPemberianInformasiEdukasi.setText("Pemberian Informasi Dan Edukasi");
        BtnPemberianInformasiEdukasi.setIconTextGap(0);
        BtnPemberianInformasiEdukasi.setName("BtnPemberianInformasiEdukasi"); // NOI18N
        BtnPemberianInformasiEdukasi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnPemberianInformasiEdukasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPemberianInformasiEdukasiActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnPemberianInformasiEdukasi);

        BtnJadwalObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnJadwalObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1404047834_application-vnd.ms-excel.png"))); // NOI18N
        BtnJadwalObat.setText("Jadwal Beri Obat Oral/Injeksi");
        BtnJadwalObat.setIconTextGap(0);
        BtnJadwalObat.setName("BtnJadwalObat"); // NOI18N
        BtnJadwalObat.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnJadwalObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJadwalObatActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnJadwalObat);

        BtnTransferTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTransferTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481002123_wheelchair.png"))); // NOI18N
        BtnTransferTindakan.setText("Transfer Pasien Untuk Tindakan");
        BtnTransferTindakan.setIconTextGap(0);
        BtnTransferTindakan.setName("BtnTransferTindakan"); // NOI18N
        BtnTransferTindakan.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnTransferTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTransferTindakanActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnTransferTindakan);

        BtnPerencanaanPulang.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerencanaanPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/folder.png"))); // NOI18N
        BtnPerencanaanPulang.setText("<html><div style=\"text-align: center;\">Perencanaan Pulang<br><i>(Discharge Planning)</i></div></html>");
        BtnPerencanaanPulang.setIconTextGap(0);
        BtnPerencanaanPulang.setName("BtnPerencanaanPulang"); // NOI18N
        BtnPerencanaanPulang.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnPerencanaanPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerencanaanPulangActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnPerencanaanPulang);

        BtnKonsul.setForeground(new java.awt.Color(0, 0, 0));
        BtnKonsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/email_open.png"))); // NOI18N
        BtnKonsul.setText("Permintaan Konsul Antar Unit");
        BtnKonsul.setIconTextGap(0);
        BtnKonsul.setName("BtnKonsul"); // NOI18N
        BtnKonsul.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKonsulActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnKonsul);

        BtnJawabKonsul.setForeground(new java.awt.Color(0, 0, 0));
        BtnJawabKonsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kwrite.png"))); // NOI18N
        BtnJawabKonsul.setText("Jawaban Konsul Antar Unit");
        BtnJawabKonsul.setIconTextGap(0);
        BtnJawabKonsul.setName("BtnJawabKonsul"); // NOI18N
        BtnJawabKonsul.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnJawabKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJawabKonsulActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnJawabKonsul);

        BtnPersetujuanTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnPersetujuanTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_todo_list_add_17451.png"))); // NOI18N
        BtnPersetujuanTindakan.setText("<html><div style=\"text-align: center;\">Persetujuan/Penolakan/<br>Penundaan Tindakan</div></html>");
        BtnPersetujuanTindakan.setIconTextGap(0);
        BtnPersetujuanTindakan.setName("BtnPersetujuanTindakan"); // NOI18N
        BtnPersetujuanTindakan.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnPersetujuanTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPersetujuanTindakanActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnPersetujuanTindakan);

        BtnPantauHarianPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnPantauHarianPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20.png"))); // NOI18N
        BtnPantauHarianPasien.setText("Pemantauan Harian Pasien");
        BtnPantauHarianPasien.setIconTextGap(0);
        BtnPantauHarianPasien.setName("BtnPantauHarianPasien"); // NOI18N
        BtnPantauHarianPasien.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnPantauHarianPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPantauHarianPasienActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnPantauHarianPasien);

        BtnGrafikPantauHarian.setForeground(new java.awt.Color(0, 0, 0));
        BtnGrafikPantauHarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20.png"))); // NOI18N
        BtnGrafikPantauHarian.setText("Grafik Pemantauan Harian Pasien");
        BtnGrafikPantauHarian.setIconTextGap(0);
        BtnGrafikPantauHarian.setName("BtnGrafikPantauHarian"); // NOI18N
        BtnGrafikPantauHarian.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnGrafikPantauHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGrafikPantauHarianActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnGrafikPantauHarian);

        BtnProtokolKemoterapi.setForeground(new java.awt.Color(0, 0, 0));
        BtnProtokolKemoterapi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_30-Doctor_5929214.png"))); // NOI18N
        BtnProtokolKemoterapi.setText("Protokol Kemoterapi");
        BtnProtokolKemoterapi.setIconTextGap(0);
        BtnProtokolKemoterapi.setName("BtnProtokolKemoterapi"); // NOI18N
        BtnProtokolKemoterapi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnProtokolKemoterapi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProtokolKemoterapiActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnProtokolKemoterapi);

        BtnDokumenJangMed.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/x-office-address-book.png"))); // NOI18N
        BtnDokumenJangMed.setText("Dokumen Penunjang Medis");
        BtnDokumenJangMed.setIconTextGap(0);
        BtnDokumenJangMed.setName("BtnDokumenJangMed"); // NOI18N
        BtnDokumenJangMed.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokumenJangMedActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnDokumenJangMed);

        BtnSkriningUlangGizi.setForeground(new java.awt.Color(0, 0, 0));
        BtnSkriningUlangGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_order-history_49596.png"))); // NOI18N
        BtnSkriningUlangGizi.setText("Skrining Gizi Ulang");
        BtnSkriningUlangGizi.setIconTextGap(0);
        BtnSkriningUlangGizi.setName("BtnSkriningUlangGizi"); // NOI18N
        BtnSkriningUlangGizi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnSkriningUlangGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSkriningUlangGiziActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnSkriningUlangGizi);

        BtnAsuhanGizi.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsuhanGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsuhanGizi.setText("Asuhan Gizi");
        BtnAsuhanGizi.setIconTextGap(0);
        BtnAsuhanGizi.setName("BtnAsuhanGizi"); // NOI18N
        BtnAsuhanGizi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsuhanGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsuhanGiziActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsuhanGizi);

        BtnMonevAsuhanGizi.setForeground(new java.awt.Color(0, 0, 0));
        BtnMonevAsuhanGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360485642_edit-notes.png"))); // NOI18N
        BtnMonevAsuhanGizi.setText("Monitoring Dan Evaluasi Gizi");
        BtnMonevAsuhanGizi.setIconTextGap(0);
        BtnMonevAsuhanGizi.setName("BtnMonevAsuhanGizi"); // NOI18N
        BtnMonevAsuhanGizi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMonevAsuhanGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMonevAsuhanGiziActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnMonevAsuhanGizi);

        BtnAsesmenUlangGizi.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenUlangGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenUlangGizi.setText("Asesmen Ulang Gizi");
        BtnAsesmenUlangGizi.setIconTextGap(0);
        BtnAsesmenUlangGizi.setName("BtnAsesmenUlangGizi"); // NOI18N
        BtnAsesmenUlangGizi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenUlangGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenUlangGiziActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnAsesmenUlangGizi);

        BtnSamplingPemanfaatanRM.setForeground(new java.awt.Color(0, 0, 0));
        BtnSamplingPemanfaatanRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582089_Finance_financial_report.png"))); // NOI18N
        BtnSamplingPemanfaatanRM.setText("Sampling Pemanfaatan RM");
        BtnSamplingPemanfaatanRM.setIconTextGap(0);
        BtnSamplingPemanfaatanRM.setName("BtnSamplingPemanfaatanRM"); // NOI18N
        BtnSamplingPemanfaatanRM.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnSamplingPemanfaatanRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSamplingPemanfaatanRMActionPerformed(evt);
            }
        });
        FormMenuRanap.add(BtnSamplingPemanfaatanRM);

        scrolMenuRanap.setViewportView(FormMenuRanap);

        internalFrame5.add(scrolMenuRanap, java.awt.BorderLayout.CENTER);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Informasi Rekam Medis Sudah Terisi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame10.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbRMranap.setName("tbRMranap"); // NOI18N
        tbRMranap.getTableHeader().setReorderingAllowed(false);
        Scroll.setViewportView(tbRMranap);

        internalFrame10.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame5.add(internalFrame10, java.awt.BorderLayout.EAST);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setPreferredSize(new java.awt.Dimension(12, 44));
        internalFrame3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 7));

        BtnRefres.setForeground(new java.awt.Color(0, 0, 0));
        BtnRefres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRefres.setMnemonic('R');
        BtnRefres.setText("Refresh Rekam Medis");
        BtnRefres.setToolTipText("Alt+R");
        BtnRefres.setName("BtnRefres"); // NOI18N
        BtnRefres.setPreferredSize(new java.awt.Dimension(186, 30));
        BtnRefres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefresActionPerformed(evt);
            }
        });
        internalFrame3.add(BtnRefres);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        internalFrame3.add(BtnKeluar);

        internalFrame5.add(internalFrame3, java.awt.BorderLayout.PAGE_END);

        TabRekamMedis.addTab("Ruang Rawat Inap", internalFrame5);

        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setPreferredSize(new java.awt.Dimension(12, 44));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        scrolMenuMaskep.setName("scrolMenuMaskep"); // NOI18N
        scrolMenuMaskep.setPreferredSize(new java.awt.Dimension(102, 557));

        FormMasKep.setBackground(new java.awt.Color(255, 255, 255));
        FormMasKep.setBorder(null);
        FormMasKep.setName("FormMasKep"); // NOI18N
        FormMasKep.setPreferredSize(new java.awt.Dimension(870, 834));
        FormMasKep.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 25));

        BtnMaskepNyeriAkut.setForeground(new java.awt.Color(0, 0, 0));
        BtnMaskepNyeriAkut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMaskepNyeriAkut.setText("Nyeri Akut");
        BtnMaskepNyeriAkut.setIconTextGap(0);
        BtnMaskepNyeriAkut.setName("BtnMaskepNyeriAkut"); // NOI18N
        BtnMaskepNyeriAkut.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMaskepNyeriAkut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaskepNyeriAkutActionPerformed(evt);
            }
        });
        FormMasKep.add(BtnMaskepNyeriAkut);

        BtnMaskepPerfusiPerifer.setForeground(new java.awt.Color(0, 0, 0));
        BtnMaskepPerfusiPerifer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMaskepPerfusiPerifer.setText("Perfusi Perifer Tidak Efektif");
        BtnMaskepPerfusiPerifer.setIconTextGap(0);
        BtnMaskepPerfusiPerifer.setName("BtnMaskepPerfusiPerifer"); // NOI18N
        BtnMaskepPerfusiPerifer.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMaskepPerfusiPerifer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaskepPerfusiPeriferActionPerformed(evt);
            }
        });
        FormMasKep.add(BtnMaskepPerfusiPerifer);

        BtnMaskepResikoHipotermia.setForeground(new java.awt.Color(0, 0, 0));
        BtnMaskepResikoHipotermia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMaskepResikoHipotermia.setText("Resiko Hipotermia");
        BtnMaskepResikoHipotermia.setIconTextGap(0);
        BtnMaskepResikoHipotermia.setName("BtnMaskepResikoHipotermia"); // NOI18N
        BtnMaskepResikoHipotermia.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMaskepResikoHipotermia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaskepResikoHipotermiaActionPerformed(evt);
            }
        });
        FormMasKep.add(BtnMaskepResikoHipotermia);

        BtnMaskepResikoHipovolemia.setForeground(new java.awt.Color(0, 0, 0));
        BtnMaskepResikoHipovolemia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMaskepResikoHipovolemia.setText("Resiko Hipovolemia");
        BtnMaskepResikoHipovolemia.setIconTextGap(0);
        BtnMaskepResikoHipovolemia.setName("BtnMaskepResikoHipovolemia"); // NOI18N
        BtnMaskepResikoHipovolemia.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMaskepResikoHipovolemia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaskepResikoHipovolemiaActionPerformed(evt);
            }
        });
        FormMasKep.add(BtnMaskepResikoHipovolemia);

        BtnMaskepBersihanJalanNafas.setForeground(new java.awt.Color(0, 0, 0));
        BtnMaskepBersihanJalanNafas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMaskepBersihanJalanNafas.setText("Bersihan Jalan Nafas Tidak Efektif");
        BtnMaskepBersihanJalanNafas.setIconTextGap(0);
        BtnMaskepBersihanJalanNafas.setName("BtnMaskepBersihanJalanNafas"); // NOI18N
        BtnMaskepBersihanJalanNafas.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMaskepBersihanJalanNafas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaskepBersihanJalanNafasActionPerformed(evt);
            }
        });
        FormMasKep.add(BtnMaskepBersihanJalanNafas);

        BtnMaskepKetidakstabilanGlukosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnMaskepKetidakstabilanGlukosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMaskepKetidakstabilanGlukosa.setText("Ketidakstabilan Glukosa Darah");
        BtnMaskepKetidakstabilanGlukosa.setIconTextGap(0);
        BtnMaskepKetidakstabilanGlukosa.setName("BtnMaskepKetidakstabilanGlukosa"); // NOI18N
        BtnMaskepKetidakstabilanGlukosa.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMaskepKetidakstabilanGlukosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaskepKetidakstabilanGlukosaActionPerformed(evt);
            }
        });
        FormMasKep.add(BtnMaskepKetidakstabilanGlukosa);

        scrolMenuMaskep.setViewportView(FormMasKep);

        internalFrame6.add(scrolMenuMaskep, java.awt.BorderLayout.CENTER);

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Informasi Masalah Keperawatan Pasien Ini ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame11.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        tbRMmaskep.setName("tbRMmaskep"); // NOI18N
        tbRMmaskep.getTableHeader().setReorderingAllowed(false);
        Scroll1.setViewportView(tbRMmaskep);

        internalFrame11.add(Scroll1, java.awt.BorderLayout.CENTER);

        internalFrame6.add(internalFrame11, java.awt.BorderLayout.EAST);

        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setPreferredSize(new java.awt.Dimension(12, 44));
        internalFrame7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 7));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Cari Menu :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame7.add(jLabel6);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(280, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        internalFrame7.add(TCari1);

        btnClear1.setForeground(new java.awt.Color(0, 0, 0));
        btnClear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        btnClear1.setMinimumSize(new java.awt.Dimension(28, 23));
        btnClear1.setName("btnClear1"); // NOI18N
        btnClear1.setPreferredSize(new java.awt.Dimension(30, 30));
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });
        internalFrame7.add(btnClear1);

        ChkInput1.setBorder(null);
        ChkInput1.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setMnemonic('C');
        ChkInput1.setSelected(true);
        ChkInput1.setText("Pencarian");
        ChkInput1.setToolTipText("Alt+C");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setFocusable(false);
        ChkInput1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setOpaque(false);
        ChkInput1.setPreferredSize(new java.awt.Dimension(100, 30));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        internalFrame7.add(ChkInput1);

        BtnRefres1.setForeground(new java.awt.Color(0, 0, 0));
        BtnRefres1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRefres1.setMnemonic('R');
        BtnRefres1.setText("Refresh Rekam Medis");
        BtnRefres1.setToolTipText("Alt+R");
        BtnRefres1.setName("BtnRefres1"); // NOI18N
        BtnRefres1.setPreferredSize(new java.awt.Dimension(186, 30));
        BtnRefres1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefres1ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnRefres1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnKeluar1);

        internalFrame6.add(internalFrame7, java.awt.BorderLayout.PAGE_END);

        TabRekamMedis.addTab("(Diagnosis) Masalah Keperawatan", internalFrame6);

        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setPreferredSize(new java.awt.Dimension(12, 44));
        internalFrame8.setLayout(new java.awt.BorderLayout());

        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setPreferredSize(new java.awt.Dimension(12, 44));
        internalFrame12.setLayout(new java.awt.BorderLayout());

        scrollInput1.setName("scrollInput1"); // NOI18N
        scrollInput1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormIBS.setBackground(new java.awt.Color(255, 255, 255));
        FormIBS.setBorder(null);
        FormIBS.setName("FormIBS"); // NOI18N
        FormIBS.setPreferredSize(new java.awt.Dimension(870, 834));
        FormIBS.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 25));

        BtnEvaluasiPraAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnEvaluasiPraAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        BtnEvaluasiPraAnestesi.setText("Evaluasi Pra Anestesi");
        BtnEvaluasiPraAnestesi.setIconTextGap(0);
        BtnEvaluasiPraAnestesi.setName("BtnEvaluasiPraAnestesi"); // NOI18N
        BtnEvaluasiPraAnestesi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnEvaluasiPraAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEvaluasiPraAnestesiActionPerformed(evt);
            }
        });
        FormIBS.add(BtnEvaluasiPraAnestesi);

        BtnCeklisPraOperasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnCeklisPraOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        BtnCeklisPraOperasi.setText("Checklist Pra Operasi");
        BtnCeklisPraOperasi.setIconTextGap(0);
        BtnCeklisPraOperasi.setName("BtnCeklisPraOperasi"); // NOI18N
        BtnCeklisPraOperasi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnCeklisPraOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCeklisPraOperasiActionPerformed(evt);
            }
        });
        FormIBS.add(BtnCeklisPraOperasi);

        BtnFormulirSiteMarking.setForeground(new java.awt.Color(0, 0, 0));
        BtnFormulirSiteMarking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/sign-up.png"))); // NOI18N
        BtnFormulirSiteMarking.setText("Formulir Site Marking Operasi");
        BtnFormulirSiteMarking.setIconTextGap(0);
        BtnFormulirSiteMarking.setName("BtnFormulirSiteMarking"); // NOI18N
        BtnFormulirSiteMarking.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnFormulirSiteMarking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFormulirSiteMarkingActionPerformed(evt);
            }
        });
        FormIBS.add(BtnFormulirSiteMarking);

        BtnCeklisKesiapanAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnCeklisKesiapanAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        BtnCeklisKesiapanAnestesi.setText("Checklist Kesiapan Anestesi");
        BtnCeklisKesiapanAnestesi.setIconTextGap(0);
        BtnCeklisKesiapanAnestesi.setName("BtnCeklisKesiapanAnestesi"); // NOI18N
        BtnCeklisKesiapanAnestesi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnCeklisKesiapanAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCeklisKesiapanAnestesiActionPerformed(evt);
            }
        });
        FormIBS.add(BtnCeklisKesiapanAnestesi);

        BtnAsesmenPraSedasiKonsepIAR.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenPraSedasiKonsepIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        BtnAsesmenPraSedasiKonsepIAR.setText("Asesmen Pra Sedasi Konsep IAR");
        BtnAsesmenPraSedasiKonsepIAR.setIconTextGap(0);
        BtnAsesmenPraSedasiKonsepIAR.setName("BtnAsesmenPraSedasiKonsepIAR"); // NOI18N
        BtnAsesmenPraSedasiKonsepIAR.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenPraSedasiKonsepIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenPraSedasiKonsepIARActionPerformed(evt);
            }
        });
        FormIBS.add(BtnAsesmenPraSedasiKonsepIAR);

        BtnAsesmenPreInduksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenPreInduksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/anastesi1.png"))); // NOI18N
        BtnAsesmenPreInduksi.setText("Asesmen Pre Induksi");
        BtnAsesmenPreInduksi.setIconTextGap(0);
        BtnAsesmenPreInduksi.setName("BtnAsesmenPreInduksi"); // NOI18N
        BtnAsesmenPreInduksi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenPreInduksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenPreInduksiActionPerformed(evt);
            }
        });
        FormIBS.add(BtnAsesmenPreInduksi);

        BtnAsesmenKeperawatanPerioperatif.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenKeperawatanPerioperatif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnAsesmenKeperawatanPerioperatif.setText("<html><div style=\"text-align: center;\">Assesmen Keperawatan<br>Perioperatif</div></html>");
        BtnAsesmenKeperawatanPerioperatif.setIconTextGap(0);
        BtnAsesmenKeperawatanPerioperatif.setName("BtnAsesmenKeperawatanPerioperatif"); // NOI18N
        BtnAsesmenKeperawatanPerioperatif.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnAsesmenKeperawatanPerioperatif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenKeperawatanPerioperatifActionPerformed(evt);
            }
        });
        FormIBS.add(BtnAsesmenKeperawatanPerioperatif);

        BtnCeklisKeselamatanOperasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnCeklisKeselamatanOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        BtnCeklisKeselamatanOperasi.setText("Checklist Keselamatan Operasi");
        BtnCeklisKeselamatanOperasi.setIconTextGap(0);
        BtnCeklisKeselamatanOperasi.setName("BtnCeklisKeselamatanOperasi"); // NOI18N
        BtnCeklisKeselamatanOperasi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnCeklisKeselamatanOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCeklisKeselamatanOperasiActionPerformed(evt);
            }
        });
        FormIBS.add(BtnCeklisKeselamatanOperasi);

        BtnCatatanSedasiAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnCatatanSedasiAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        BtnCatatanSedasiAnestesi.setText("Catatan Sedasi / Anestesi");
        BtnCatatanSedasiAnestesi.setIconTextGap(0);
        BtnCatatanSedasiAnestesi.setName("BtnCatatanSedasiAnestesi"); // NOI18N
        BtnCatatanSedasiAnestesi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnCatatanSedasiAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatanSedasiAnestesiActionPerformed(evt);
            }
        });
        FormIBS.add(BtnCatatanSedasiAnestesi);

        BtnLaporanOperasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnLaporanOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnLaporanOperasi.setText("Laporan Operasi");
        BtnLaporanOperasi.setIconTextGap(0);
        BtnLaporanOperasi.setName("BtnLaporanOperasi"); // NOI18N
        BtnLaporanOperasi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnLaporanOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLaporanOperasiActionPerformed(evt);
            }
        });
        FormIBS.add(BtnLaporanOperasi);

        BtnCatatanMaterial.setForeground(new java.awt.Color(0, 0, 0));
        BtnCatatanMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        BtnCatatanMaterial.setText("<html><div style=\"text-align: center;\">Catatan Pemakaian<br>Obat & Material</div></html>");
        BtnCatatanMaterial.setIconTextGap(0);
        BtnCatatanMaterial.setName("BtnCatatanMaterial"); // NOI18N
        BtnCatatanMaterial.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnCatatanMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatanMaterialActionPerformed(evt);
            }
        });
        FormIBS.add(BtnCatatanMaterial);

        BtnCatatanRuangPemulihan.setForeground(new java.awt.Color(0, 0, 0));
        BtnCatatanRuangPemulihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnCatatanRuangPemulihan.setText("Catatan Ruang Pemulihan");
        BtnCatatanRuangPemulihan.setIconTextGap(0);
        BtnCatatanRuangPemulihan.setName("BtnCatatanRuangPemulihan"); // NOI18N
        BtnCatatanRuangPemulihan.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnCatatanRuangPemulihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatanRuangPemulihanActionPerformed(evt);
            }
        });
        FormIBS.add(BtnCatatanRuangPemulihan);

        BtnSerahTerimaPasca.setForeground(new java.awt.Color(0, 0, 0));
        BtnSerahTerimaPasca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        BtnSerahTerimaPasca.setText("Serah Terima Pasca Operasi");
        BtnSerahTerimaPasca.setIconTextGap(0);
        BtnSerahTerimaPasca.setName("BtnSerahTerimaPasca"); // NOI18N
        BtnSerahTerimaPasca.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnSerahTerimaPasca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSerahTerimaPascaActionPerformed(evt);
            }
        });
        FormIBS.add(BtnSerahTerimaPasca);

        BtnInformasiTindakanPembiusan.setForeground(new java.awt.Color(0, 0, 0));
        BtnInformasiTindakanPembiusan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        BtnInformasiTindakanPembiusan.setText("Informasi Tindakan Pembiusan");
        BtnInformasiTindakanPembiusan.setIconTextGap(0);
        BtnInformasiTindakanPembiusan.setName("BtnInformasiTindakanPembiusan"); // NOI18N
        BtnInformasiTindakanPembiusan.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnInformasiTindakanPembiusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInformasiTindakanPembiusanActionPerformed(evt);
            }
        });
        FormIBS.add(BtnInformasiTindakanPembiusan);

        BtnPersetujuanTindakanIBS.setForeground(new java.awt.Color(0, 0, 0));
        BtnPersetujuanTindakanIBS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_todo_list_add_17451.png"))); // NOI18N
        BtnPersetujuanTindakanIBS.setText("<html><div style=\"text-align: center;\">Persetujuan/Penolakan/<br>Penundaan Tindakan</div></html>");
        BtnPersetujuanTindakanIBS.setIconTextGap(0);
        BtnPersetujuanTindakanIBS.setName("BtnPersetujuanTindakanIBS"); // NOI18N
        BtnPersetujuanTindakanIBS.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnPersetujuanTindakanIBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPersetujuanTindakanIBSActionPerformed(evt);
            }
        });
        FormIBS.add(BtnPersetujuanTindakanIBS);

        BtnPemberianInformasiEdukasiIBS.setForeground(new java.awt.Color(0, 0, 0));
        BtnPemberianInformasiEdukasiIBS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/people.png"))); // NOI18N
        BtnPemberianInformasiEdukasiIBS.setText("Pemberian Informasi Dan Edukasi");
        BtnPemberianInformasiEdukasiIBS.setIconTextGap(0);
        BtnPemberianInformasiEdukasiIBS.setName("BtnPemberianInformasiEdukasiIBS"); // NOI18N
        BtnPemberianInformasiEdukasiIBS.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnPemberianInformasiEdukasiIBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPemberianInformasiEdukasiIBSActionPerformed(evt);
            }
        });
        FormIBS.add(BtnPemberianInformasiEdukasiIBS);

        BtnAsesmenPraSedasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenPraSedasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/anastesi2.png"))); // NOI18N
        BtnAsesmenPraSedasi.setText("Asesmen Pra Sedasi");
        BtnAsesmenPraSedasi.setIconTextGap(0);
        BtnAsesmenPraSedasi.setName("BtnAsesmenPraSedasi"); // NOI18N
        BtnAsesmenPraSedasi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenPraSedasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenPraSedasiActionPerformed(evt);
            }
        });
        FormIBS.add(BtnAsesmenPraSedasi);

        BtnTransferTindakanIBS.setForeground(new java.awt.Color(0, 0, 0));
        BtnTransferTindakanIBS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481002123_wheelchair.png"))); // NOI18N
        BtnTransferTindakanIBS.setText("Transfer Pasien Untuk Tindakan");
        BtnTransferTindakanIBS.setIconTextGap(0);
        BtnTransferTindakanIBS.setName("BtnTransferTindakanIBS"); // NOI18N
        BtnTransferTindakanIBS.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnTransferTindakanIBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTransferTindakanIBSActionPerformed(evt);
            }
        });
        FormIBS.add(BtnTransferTindakanIBS);

        scrollInput1.setViewportView(FormIBS);

        internalFrame12.add(scrollInput1, java.awt.BorderLayout.CENTER);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Informasi Rekam Medis Sudah Terisi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(600, 402));

        tbRMibs.setName("tbRMibs"); // NOI18N
        tbRMibs.getTableHeader().setReorderingAllowed(false);
        Scroll2.setViewportView(tbRMibs);

        internalFrame13.add(Scroll2, java.awt.BorderLayout.CENTER);

        internalFrame12.add(internalFrame13, java.awt.BorderLayout.EAST);

        internalFrame8.add(internalFrame12, java.awt.BorderLayout.CENTER);

        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setPreferredSize(new java.awt.Dimension(12, 44));
        internalFrame9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 7));

        BtnRefres2.setForeground(new java.awt.Color(0, 0, 0));
        BtnRefres2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRefres2.setText("Refresh Rekam Medis");
        BtnRefres2.setToolTipText("Alt+R");
        BtnRefres2.setName("BtnRefres2"); // NOI18N
        BtnRefres2.setPreferredSize(new java.awt.Dimension(186, 30));
        BtnRefres2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefres2ActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnRefres2);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnKeluar2);

        internalFrame8.add(internalFrame9, java.awt.BorderLayout.PAGE_END);

        TabRekamMedis.addTab("Instalasi Bedah Sentral (IBS)", internalFrame8);

        internalFrame2.add(TabRekamMedis, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowDPJPranap.dispose();
        kddpjp.setText("-");
        nmdpjp.setText("-");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if (kddpjp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu DPJP nya...!");
            btnDPJP.requestFocus();
        } else if (kddpjp.getText().equals("-") || kddpjp.getText().equals("--")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu DPJP nya dengan benar...!");
            btnDPJP.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'") == 0) {
                Sequel.menyimpan("dpjp_ranap", "'" + TNoRW.getText() + "','" + kddpjp.getText() + "'");
            }            

            akses.setform("DlgRMEranap");
            DlgRingkasanPulangRanap ringkasan = new DlgRingkasanPulangRanap(null, false);
            ringkasan.emptTeks();
            ringkasan.isCek();
            ringkasan.setPasien(TNoRW.getText());
            ringkasan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            ringkasan.setLocationRelativeTo(internalFrame1);
            ringkasan.setVisible(true);

            BtnCloseIn10ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void btnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPActionPerformed
        akses.setform("DlgRMEranap");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDPJPActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        isCek();
        tampilRMranap();
        tampilkanMenuMaskep();
    }//GEN-LAST:event_formWindowOpened

    private void BtnAsesmenMedikDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenMedikDewasaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenMedikDewasaRanap form = new RMAsesmenMedikDewasaRanap(null, false);
            form.emptTeks();
            form.isCek();
            form.setNoRm(TNoRW.getText(), kdkamar);
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenMedikDewasaActionPerformed

    private void BtnCPPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCPPTActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            DlgCPPT form = new DlgCPPT(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), stts, gedung, nmUnit.getText(), kdkamar);
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCPPTActionPerformed

    private void BtnRingkasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRingkasanActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'") == 0) {
                x = JOptionPane.showConfirmDialog(rootPane, "DPJP pasien ini belum ditentukan, apakah DPJP nya akan dipilih dulu..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    WindowDPJPranap.setSize(615, 110);
                    WindowDPJPranap.setLocationRelativeTo(internalFrame1);
                    WindowDPJPranap.setVisible(true);
                    kddpjp.setText(Sequel.cariIsi("select ifnull(kd_dokter,'') from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'"));
                    if (kddpjp.getText().equals("")) {
                        nmdpjp.setText("");
                    } else {
                        nmdpjp.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddpjp.getText() + "'"));
                    }
                    btnDPJP.requestFocus();
                } else {
                    BtnKeluarActionPerformed(null);
                }
            } else {
                akses.setform("DlgRMEranap");
                DlgRingkasanPulangRanap ringkasan = new DlgRingkasanPulangRanap(null, false);
                ringkasan.emptTeks();
                ringkasan.isCek();
                ringkasan.setPasien(TNoRW.getText());
                ringkasan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                ringkasan.setLocationRelativeTo(internalFrame1);
                ringkasan.setVisible(true);
            }
        }
    }//GEN-LAST:event_BtnRingkasanActionPerformed

    private void BtnCTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCTKActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            DlgCatatanTindakanKeperawatan form = new DlgCatatanTindakanKeperawatan(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCTKActionPerformed

    private void BtnKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKonsulActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            DlgSuratKonsulUnit form = new DlgSuratKonsulUnit(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnKonsulActionPerformed

    private void BtnJawabKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJawabKonsulActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from surat_konsul_unit_ranap where no_rawat='" + TNoRW.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, tidak ada surat konsul antar unit rawat inap yang akan dijawab...!!!");                
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgRMEranap");
                DlgSuratJawabanKonsul form = new DlgSuratJawabanKonsul(null, false);
                form.emptTeks();
                form.isCek();
                form.setData(TNoRW.getText());
                form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_BtnJawabKonsulActionPerformed

    private void BtnJadwalObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJadwalObatActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            DlgPemberianObatPasien beriObat = new DlgPemberianObatPasien(null, false);
            beriObat.emptTeks();
            beriObat.isCek();
            beriObat.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), stts, nmUnit.getText());
            beriObat.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            beriObat.setLocationRelativeTo(internalFrame1);
            beriObat.setAlwaysOnTop(false);
            beriObat.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnJadwalObatActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            DlgCatatanResepBiasaAntibiotik form = new DlgCatatanResepBiasaAntibiotik(null, false);
            form.isCek();
            form.setData(TNoRW.getText(), stts);
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnResepActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        TNoRW.setText("");
        TNoRM.setText("");
        TNmPasien.setText("");
        nmUnit.setText("");
        stts = "";
        kdkamar = "";        
        gedung = "";
        TtglMasuk.setText("");
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnAsesmenKeperawatanDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenKeperawatanDewasaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenKeperawatanDewasaRanap form = new RMAsesmenKeperawatanDewasaRanap(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), kdkamar);
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenKeperawatanDewasaActionPerformed

    private void BtnRefresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefresActionPerformed
        tombolCek(TNoRW.getText());
        tampilRMranap();
    }//GEN-LAST:event_BtnRefresActionPerformed

    private void BtnPantauHarianPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPantauHarianPasienActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMPemantauanHarian24Jam form = new RMPemantauanHarian24Jam(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPantauHarianPasienActionPerformed

    private void BtnGrafikPantauHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGrafikPantauHarianActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMGrafikPemantauanHarian24Jam form = new RMGrafikPemantauanHarian24Jam(null, false);
            form.setData(TNoRW.getText());
            form.setSize(747, 71);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnGrafikPantauHarianActionPerformed

    private void BtnProtokolKemoterapiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProtokolKemoterapiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMProtokolKemoterapi form = new RMProtokolKemoterapi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), "Ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnProtokolKemoterapiActionPerformed

    private void BtnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokumenJangMedActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnDokumenJangMedActionPerformed

    private void BtnAsesmenUlangRJDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenUlangRJDewasaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenUlangResikoJatuhDewasa form = new RMAsesmenUlangResikoJatuhDewasa(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenUlangRJDewasaActionPerformed

    private void BtnPengelolaanTranfusiDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPengelolaanTranfusiDarahActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMPengelolaanTransfusiDarah form = new RMPengelolaanTransfusiDarah(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPengelolaanTranfusiDarahActionPerformed

    private void BtnMonitoringEWSdewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMonitoringEWSdewasaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMMonitoringEWSDewasa form = new RMMonitoringEWSDewasa(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMonitoringEWSdewasaActionPerformed

    private void BtnTransferSerahTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTransferSerahTerimaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMTransferSerahTerimaIGD form = new RMTransferSerahTerimaIGD(null, false);
            form.emptTeks();
            form.isCek();
            form.setNoRm(TNoRW.getText(), new Date(), "ranap", kdkamar, nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnTransferSerahTerimaActionPerformed

    private void BtnAsesmenKeperawatanAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenKeperawatanAnakActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenKeperawatanAnakRanap form = new RMAsesmenKeperawatanAnakRanap(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), kdkamar);
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenKeperawatanAnakActionPerformed

    private void BtnAsesmenMedikAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenMedikAnakActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenMedikAnakRanap form = new RMAsesmenMedikAnakRanap(null, false);
            form.emptTeks();
            form.isCek();
            form.setNoRm(TNoRW.getText(), kdkamar);
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenMedikAnakActionPerformed

    private void BtnPersetujuanTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPersetujuanTindakanActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMTindakanKedokteran form = new RMTindakanKedokteran(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), "Ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPersetujuanTindakanActionPerformed

    private void BtnAsesmenUlangRJAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenUlangRJAnakActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenUlangResikoJatuhAnak form = new RMAsesmenUlangResikoJatuhAnak(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenUlangRJAnakActionPerformed

    private void BtnMonitoringPEWSanakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMonitoringPEWSanakActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMMonitoringPEWSAnak form = new RMMonitoringPEWSAnak(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMonitoringPEWSanakActionPerformed

    private void BtnAsesmenRestrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenRestrainActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenRestrain form = new RMAsesmenRestrain(null, false);
            form.emptTeks();
            form.isCek();
            form.setNoRm(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenRestrainActionPerformed

    private void BtnObservasiRestrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObservasiRestrainActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from asesmen_restrain where no_rawat='" + TNoRW.getText() + "'") > 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgRMEranap");
                RMObservasiRestrain form = new RMObservasiRestrain(null, false);
                form.emptTeks();
                form.isCek();
                form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
                form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen restrain belum tersimpan untuk pasien ini...!!!");
            }
        }
    }//GEN-LAST:event_BtnObservasiRestrainActionPerformed

    private void BtnSkriningUlangGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSkriningUlangGiziActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSkriningUlangGizi form = new RMSkriningUlangGizi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSkriningUlangGiziActionPerformed

    private void BtnMonevAsuhanGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMonevAsuhanGiziActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + TNoRW.getText() + "'") > 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgRMEranap");
                DlgMonevAsuhanGizi form = new DlgMonevAsuhanGizi(null, false);
                form.emptTeks();
                form.isCek();
                form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
                form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Data asuhan gizi belum tersimpan untuk pasien ini...!!!");
            }
        }
    }//GEN-LAST:event_BtnMonevAsuhanGiziActionPerformed

    private void BtnAsuhanGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsuhanGiziActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsuhanGiziRanap form = new RMAsuhanGiziRanap(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText(), gedung, kdkamar);
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsuhanGiziActionPerformed

    private void BtnAsesmenUlangGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenUlangGiziActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + TNoRW.getText() + "'") > 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgRMEranap");
                DlgAssesmenGiziUlang form = new DlgAssesmenGiziUlang(null, false);
                form.emptTeks();
                form.isCek();
                form.setData(TNoRW.getText(), nmUnit.getText(), gedung);
                form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Data asuhan gizi belum tersimpan untuk pasien ini...!!!");
            }
        }
    }//GEN-LAST:event_BtnAsesmenUlangGiziActionPerformed

    private void BtnLembarObservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLembarObservasiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMLembarObservasi form = new RMLembarObservasi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnLembarObservasiActionPerformed

    private void BtnTransferTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTransferTindakanActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMPasienUntukTindakan form = new RMPasienUntukTindakan(null, false);
            form.emptTeksSebelum();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnTransferTindakanActionPerformed

    private void BtnAsesmenPraSedasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenPraSedasiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenPraSedasi form = new RMAsesmenPraSedasi(null, false);
            form.emptTeks();
            form.isCek();
            form.setNoRm(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText(),
                    Sequel.cariIsi("select ifnull(status_lanjut,'') from reg_periksa where no_rawat='" + TNoRW.getText() + "'"));
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenPraSedasiActionPerformed

    private void BtnCeklisPraOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCeklisPraOperasiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMCeklisPraOperasi form = new RMCeklisPraOperasi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCeklisPraOperasiActionPerformed

    private void BtnCeklisKesiapanAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCeklisKesiapanAnestesiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMCeklisKesiapanAnestesi form = new RMCeklisKesiapanAnestesi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText(), "ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCeklisKesiapanAnestesiActionPerformed

    private void BtnAsesmenPreInduksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenPreInduksiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenPreInduksi form = new RMAsesmenPreInduksi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenPreInduksiActionPerformed

    private void BtnAsesmenKeperawatanPerioperatifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenKeperawatanPerioperatifActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenKeperawatanPerioperatif form = new RMAsesmenKeperawatanPerioperatif(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenKeperawatanPerioperatifActionPerformed

    private void BtnPerencanaanPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerencanaanPulangActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMPerencanaanPulang form = new RMPerencanaanPulang(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPerencanaanPulangActionPerformed

    private void BtnCeklisKeselamatanOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCeklisKeselamatanOperasiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMCeklisKeselamatanOperasi form = new RMCeklisKeselamatanOperasi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCeklisKeselamatanOperasiActionPerformed

    private void BtnCatatanMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanMaterialActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMCatatanPemakaianObatMaterialOperasi form = new RMCatatanPemakaianObatMaterialOperasi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCatatanMaterialActionPerformed

    private void BtnAsesmenMedikBedahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenMedikBedahActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenMedikBedahRanap form = new RMAsesmenMedikBedahRanap(null, false);
            form.emptTeks();
            form.isCek();
            form.setNoRm(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenMedikBedahActionPerformed

    private void BtnAsesmenMedikPerinatologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenMedikPerinatologiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenMedikPerinatologi form = new RMAsesmenMedikPerinatologi(null, false);
            form.emptTeks();
            form.isCek();
            form.setNoRm(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenMedikPerinatologiActionPerformed

    private void BtnAsesmenKeperawatanPerinatologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenKeperawatanPerinatologiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenKeperawatanPerinatologi form = new RMAsesmenKeperawatanPerinatologi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenKeperawatanPerinatologiActionPerformed

    private void BtnScoreApgarPerinatologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnScoreApgarPerinatologiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSkorApgarDowneCapPerinatologi form = new RMSkorApgarDowneCapPerinatologi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnScoreApgarPerinatologiActionPerformed

    private void BtnPengamatanMenyusuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPengamatanMenyusuiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMPengamatanMenyusui form = new RMPengamatanMenyusui(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPengamatanMenyusuiActionPerformed

    private void BtnRekonsiliasiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRekonsiliasiObatActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMRekonsiliasiObat form = new RMRekonsiliasiObat(null, false);
            form.emptTeks();
            form.emptTeksRiwayatIGD();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText(), TNoRM.getText(), TNmPasien.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnRekonsiliasiObatActionPerformed

    private void BtnSerahTerimaBayiPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSerahTerimaBayiPulangActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSerahTerimaBayiPulang form = new RMSerahTerimaBayiPulang(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSerahTerimaBayiPulangActionPerformed

    private void BtnPemberianInformasiEdukasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPemberianInformasiEdukasiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMPemberianInformasiEdukasi form = new RMPemberianInformasiEdukasi(null, false);
            form.emptTeks();
            form.emptTeksPenilaian();
            form.isCek();
            form.setData(TNoRW.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPemberianInformasiEdukasiActionPerformed

    private void BtnMonitoringEWSobsgynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMonitoringEWSobsgynActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from pasien where no_rkm_medis='" + TNoRM.getText() + "' and jk='P'") > 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgRMEranap");
                RMMonitoringEWSObsgyn form = new RMMonitoringEWSObsgyn(null, false);
                form.emptTeks();
                form.isCek();
                form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
                form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Monitoring Early Warning Score (EWS) Obsgyn hanya untuk pasien perempuan...!!!");
            }
        }
    }//GEN-LAST:event_BtnMonitoringEWSobsgynActionPerformed

    private void BtnGeneralConsentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGeneralConsentActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMGeneralConsent form = new RMGeneralConsent(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnGeneralConsentActionPerformed

    private void BtnPersetujuanRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPersetujuanRanapActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMPersetujuanRawatInap form = new RMPersetujuanRawatInap(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPersetujuanRanapActionPerformed

    private void BtnSuratPernyataanRanapBpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuratPernyataanRanapBpjsActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSuratPenyataanRanapBPJS form = new RMSuratPenyataanRanapBPJS(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSuratPernyataanRanapBpjsActionPerformed

    private void BtnSuratPernyataanNaikKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuratPernyataanNaikKelasActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSuratPenyataanNaikKelas form = new RMSuratPenyataanNaikKelas(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSuratPernyataanNaikKelasActionPerformed

    private void BtnSuratPernyataanBukanKLLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuratPernyataanBukanKLLActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSuratPenyataanBukanKLL form = new RMSuratPenyataanBukanKLL(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSuratPernyataanBukanKLLActionPerformed

    private void BtnSuratPernyataanBayarDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuratPernyataanBayarDendaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSuratPenyataanBayarDenda form = new RMSuratPenyataanBayarDenda(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSuratPernyataanBayarDendaActionPerformed

    private void BtnSuratPernyataanRanapNonBpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuratPernyataanRanapNonBpjsActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSuratPenyataanRanapNonBPJS form = new RMSuratPenyataanRanapNonBPJS(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSuratPernyataanRanapNonBpjsActionPerformed

    private void BtnObservasiKala1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObservasiKala1ActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMObservasiKala1 form = new RMObservasiKala1(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnObservasiKala1ActionPerformed

    private void BtnSamplingPemanfaatanRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSamplingPemanfaatanRMActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgRMEranap");
        RMSamplingPemanfaatanRM form = new RMSamplingPemanfaatanRM(null, false);        
        form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnSamplingPemanfaatanRMActionPerformed

    private void BtnLaporanOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLaporanOperasiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMLaporanOperasi form = new RMLaporanOperasi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnLaporanOperasiActionPerformed

    private void BtnCatatanRuangPemulihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanRuangPemulihanActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMCatatanRuangPemulihan form = new RMCatatanRuangPemulihan(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCatatanRuangPemulihanActionPerformed

    private void BtnFormulirSiteMarkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFormulirSiteMarkingActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMFormulirSiteMarkingOperasi form = new RMFormulirSiteMarkingOperasi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnFormulirSiteMarkingActionPerformed

    private void BtnSerahTerimaPascaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSerahTerimaPascaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMSerahTerimaPascaOperasi form = new RMSerahTerimaPascaOperasi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSerahTerimaPascaActionPerformed

    private void BtnInformasiTindakanPembiusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInformasiTindakanPembiusanActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMInformasiTindakanPembiusan form = new RMInformasiTindakanPembiusan(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnInformasiTindakanPembiusanActionPerformed

    private void BtnEvaluasiPraAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEvaluasiPraAnestesiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMEvaluasiPraAnestesi form = new RMEvaluasiPraAnestesi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnEvaluasiPraAnestesiActionPerformed

    private void BtnAsesmenPraSedasiKonsepIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenPraSedasiKonsepIARActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMAsesmenPraSedasiKonsepIAR form = new RMAsesmenPraSedasiKonsepIAR(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenPraSedasiKonsepIARActionPerformed

    private void BtnCatatanSedasiAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanSedasiAnestesiActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMCatatanSedasiAnestesi form = new RMCatatanSedasiAnestesi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText());
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCatatanSedasiAnestesiActionPerformed

    private void BtnPersetujuanTindakanIBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPersetujuanTindakanIBSActionPerformed
        BtnPersetujuanTindakanActionPerformed(null);
    }//GEN-LAST:event_BtnPersetujuanTindakanIBSActionPerformed

    private void BtnTransferTindakanIBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTransferTindakanIBSActionPerformed
        BtnTransferTindakanActionPerformed(null);
    }//GEN-LAST:event_BtnTransferTindakanIBSActionPerformed

    private void BtnPemberianInformasiEdukasiIBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPemberianInformasiEdukasiIBSActionPerformed
        BtnPemberianInformasiEdukasiActionPerformed(null);
    }//GEN-LAST:event_BtnPemberianInformasiEdukasiIBSActionPerformed

    private void BtnMaskepNyeriAkutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaskepNyeriAkutActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMMasalahKeperawatanNyeriAkut form = new RMMasalahKeperawatanNyeriAkut(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText(), "Ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMaskepNyeriAkutActionPerformed

    private void BtnMaskepPerfusiPeriferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaskepPerfusiPeriferActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMMasalahKeperawatanPerfusiPeriferTdkEfektif form = new RMMasalahKeperawatanPerfusiPeriferTdkEfektif(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText(), "Ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMaskepPerfusiPeriferActionPerformed

    private void BtnMaskepResikoHipotermiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaskepResikoHipotermiaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMMasalahKeperawatanHipotermia form = new RMMasalahKeperawatanHipotermia(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText(), "Ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMaskepResikoHipotermiaActionPerformed

    private void BtnMaskepResikoHipovolemiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaskepResikoHipovolemiaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMMasalahKeperawatanHipovolemia form = new RMMasalahKeperawatanHipovolemia(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText(), "Ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMaskepResikoHipovolemiaActionPerformed

    private void BtnMaskepBersihanJalanNafasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaskepBersihanJalanNafasActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMMasalahKeperawatanBersihanJlnNafas form = new RMMasalahKeperawatanBersihanJlnNafas(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText(), "Ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMaskepBersihanJalanNafasActionPerformed

    private void BtnMaskepKetidakstabilanGlukosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaskepKetidakstabilanGlukosaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRMEranap");
            RMMasalahKeperawatanKetidakstabilanGlukosaDarah form = new RMMasalahKeperawatanKetidakstabilanGlukosaDarah(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText(), nmUnit.getText(), "Ranap");
            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMaskepKetidakstabilanGlukosaActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkInputActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        TCari.setText("");
        TCari.requestFocus();
    }//GEN-LAST:event_btnClearActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        FormMenuRanap.removeAll();

        isTampilMenuRanap();
        aturTampilanTombolRanap();
        aturLayoutMenuRanap();

        javax.swing.SwingUtilities.invokeLater(() -> {
            FormMenuRanap.revalidate();
            FormMenuRanap.repaint();
            scrolMenuRanap.getViewport().revalidate();
            scrolMenuRanap.revalidate();
            scrolMenuRanap.repaint();
            scrolMenuRanap.getVerticalScrollBar().setValue(0);
        });
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkInput1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        TCari1.setText("");
        TCari1.requestFocus();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        FormMasKep.removeAll();

        isTampilMenuMaskep();
        aturTampilanTombolMaskep();
        aturLayoutMenuMaskep();

        javax.swing.SwingUtilities.invokeLater(() -> {
            FormMasKep.revalidate();
            FormMasKep.repaint();
            scrolMenuMaskep.getViewport().revalidate();
            scrolMenuMaskep.revalidate();
            scrolMenuMaskep.repaint();
            scrolMenuMaskep.getVerticalScrollBar().setValue(0);
        });
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void TabRekamMedisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRekamMedisMouseClicked
        if (TabRekamMedis.getSelectedIndex() == 0) {
            tampilRMranap();
        } else if (TabRekamMedis.getSelectedIndex() == 1) {
            tampilRMmaskep();
        } else if (TabRekamMedis.getSelectedIndex() == 2) {
            tampilRMibs();
        }
    }//GEN-LAST:event_TabRekamMedisMouseClicked

    private void BtnRefres1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefres1ActionPerformed
        tampilRMmaskep();
    }//GEN-LAST:event_BtnRefres1ActionPerformed

    private void BtnRefres2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefres2ActionPerformed
        tampilRMibs();
    }//GEN-LAST:event_BtnRefres2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRMEranap dialog = new DlgRMEranap(new javax.swing.JFrame(), true);
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
    private widget.ButtonBig BtnAsesmenKeperawatanAnak;
    private widget.ButtonBig BtnAsesmenKeperawatanDewasa;
    private widget.ButtonBig BtnAsesmenKeperawatanPerinatologi;
    private widget.ButtonBig BtnAsesmenKeperawatanPerioperatif;
    private widget.ButtonBig BtnAsesmenMedikAnak;
    private widget.ButtonBig BtnAsesmenMedikBedah;
    private widget.ButtonBig BtnAsesmenMedikDewasa;
    private widget.ButtonBig BtnAsesmenMedikPerinatologi;
    private widget.ButtonBig BtnAsesmenPraSedasi;
    private widget.ButtonBig BtnAsesmenPraSedasiKonsepIAR;
    private widget.ButtonBig BtnAsesmenPreInduksi;
    private widget.ButtonBig BtnAsesmenRestrain;
    private widget.ButtonBig BtnAsesmenUlangGizi;
    private widget.ButtonBig BtnAsesmenUlangRJAnak;
    private widget.ButtonBig BtnAsesmenUlangRJDewasa;
    private widget.ButtonBig BtnAsuhanGizi;
    private widget.ButtonBig BtnCPPT;
    private widget.ButtonBig BtnCTK;
    private widget.ButtonBig BtnCatatanMaterial;
    private widget.ButtonBig BtnCatatanRuangPemulihan;
    private widget.ButtonBig BtnCatatanSedasiAnestesi;
    private widget.ButtonBig BtnCeklisKeselamatanOperasi;
    private widget.ButtonBig BtnCeklisKesiapanAnestesi;
    private widget.ButtonBig BtnCeklisPraOperasi;
    private widget.Button BtnCloseIn10;
    private widget.ButtonBig BtnDokumenJangMed;
    private widget.ButtonBig BtnEvaluasiPraAnestesi;
    private widget.ButtonBig BtnFormulirSiteMarking;
    private widget.ButtonBig BtnGeneralConsent;
    private widget.ButtonBig BtnGrafikPantauHarian;
    private widget.ButtonBig BtnInformasiTindakanPembiusan;
    private widget.ButtonBig BtnJadwalObat;
    private widget.ButtonBig BtnJawabKonsul;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.ButtonBig BtnKonsul;
    private widget.ButtonBig BtnLaporanOperasi;
    private widget.ButtonBig BtnLembarObservasi;
    private widget.ButtonBig BtnMaskepBersihanJalanNafas;
    private widget.ButtonBig BtnMaskepKetidakstabilanGlukosa;
    private widget.ButtonBig BtnMaskepNyeriAkut;
    private widget.ButtonBig BtnMaskepPerfusiPerifer;
    private widget.ButtonBig BtnMaskepResikoHipotermia;
    private widget.ButtonBig BtnMaskepResikoHipovolemia;
    private widget.ButtonBig BtnMonevAsuhanGizi;
    private widget.ButtonBig BtnMonitoringEWSdewasa;
    private widget.ButtonBig BtnMonitoringEWSobsgyn;
    private widget.ButtonBig BtnMonitoringPEWSanak;
    private widget.ButtonBig BtnObservasiKala1;
    private widget.ButtonBig BtnObservasiRestrain;
    private widget.ButtonBig BtnPantauHarianPasien;
    private widget.ButtonBig BtnPemberianInformasiEdukasi;
    private widget.ButtonBig BtnPemberianInformasiEdukasiIBS;
    private widget.ButtonBig BtnPengamatanMenyusui;
    private widget.ButtonBig BtnPengelolaanTranfusiDarah;
    private widget.ButtonBig BtnPerencanaanPulang;
    private widget.ButtonBig BtnPersetujuanRanap;
    private widget.ButtonBig BtnPersetujuanTindakan;
    private widget.ButtonBig BtnPersetujuanTindakanIBS;
    private widget.ButtonBig BtnProtokolKemoterapi;
    private widget.Button BtnRefres;
    private widget.Button BtnRefres1;
    private widget.Button BtnRefres2;
    private widget.ButtonBig BtnRekonsiliasiObat;
    private widget.ButtonBig BtnResep;
    private widget.ButtonBig BtnRingkasan;
    private widget.ButtonBig BtnSamplingPemanfaatanRM;
    private widget.ButtonBig BtnScoreApgarPerinatologi;
    private widget.ButtonBig BtnSerahTerimaBayiPulang;
    private widget.ButtonBig BtnSerahTerimaPasca;
    private widget.Button BtnSimpan6;
    private widget.ButtonBig BtnSkriningUlangGizi;
    private widget.ButtonBig BtnSuratPernyataanBayarDenda;
    private widget.ButtonBig BtnSuratPernyataanBukanKLL;
    private widget.ButtonBig BtnSuratPernyataanNaikKelas;
    private widget.ButtonBig BtnSuratPernyataanRanapBpjs;
    private widget.ButtonBig BtnSuratPernyataanRanapNonBpjs;
    private widget.ButtonBig BtnTransferSerahTerima;
    private widget.ButtonBig BtnTransferTindakan;
    private widget.ButtonBig BtnTransferTindakanIBS;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.PanelBiasa FormIBS;
    private widget.PanelBiasa FormMasKep;
    private widget.PanelBiasa FormMenuRanap;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    public widget.TextBox TCari1;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private javax.swing.JTabbedPane TabRekamMedis;
    private widget.TextBox TtglMasuk;
    private javax.swing.JDialog WindowDPJPranap;
    private widget.Button btnClear;
    private widget.Button btnClear1;
    private widget.Button btnDPJP;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel13;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel52;
    private widget.Label jLabel6;
    private widget.TextBox kddpjp;
    private widget.TextBox nmUnit;
    private widget.TextBox nmdpjp;
    private widget.ScrollPane scrolMenuMaskep;
    private widget.ScrollPane scrolMenuRanap;
    private widget.ScrollPane scrollInput1;
    private widget.Table tbRMibs;
    private widget.Table tbRMmaskep;
    private widget.Table tbRMranap;
    // End of variables declaration//GEN-END:variables

    private void isCek() {
        BtnAsesmenMedikBedah.setEnabled(akses.getasesmen_medik_bedah_ranap());
        BtnAsesmenMedikDewasa.setEnabled(akses.getasesmen_medik_dewasa_ranap());
        BtnAsesmenMedikAnak.setEnabled(akses.getasesmen_medik_anak_ranap());
        BtnAsesmenMedikPerinatologi.setEnabled(akses.getasesmen_medik_anak_ranap());
        BtnAsesmenKeperawatanDewasa.setEnabled(akses.getcppt());
        BtnAsesmenKeperawatanAnak.setEnabled(akses.getcppt());
        BtnAsesmenKeperawatanPerinatologi.setEnabled(akses.getcppt());
        BtnScoreApgarPerinatologi.setEnabled(akses.getcppt());
        BtnPengamatanMenyusui.setEnabled(akses.getcppt());
        BtnSerahTerimaBayiPulang.setEnabled(akses.getcppt());
        BtnAsesmenRestrain.setEnabled(akses.getcppt());
        BtnObservasiRestrain.setEnabled(akses.getcppt());
        BtnCPPT.setEnabled(akses.getcppt());
        BtnResep.setEnabled(akses.getresep_dokter());
        BtnRingkasan.setEnabled(akses.getringkasanpulangranap());
        BtnCTK.setEnabled(akses.getcppt());
        BtnAsesmenUlangRJDewasa.setEnabled(akses.getcppt());
        BtnAsesmenUlangRJAnak.setEnabled(akses.getcppt());
        BtnJadwalObat.setEnabled(akses.getpemberian_obat());        
        BtnKonsul.setEnabled(akses.getpermintaan_lab());
        BtnJawabKonsul.setEnabled(akses.getpermintaan_lab());
        BtnPantauHarianPasien.setEnabled(akses.getcppt());
        BtnGrafikPantauHarian.setEnabled(akses.getcppt());
        BtnProtokolKemoterapi.setEnabled(akses.getkemoterapi());        
        BtnPengelolaanTranfusiDarah.setEnabled(akses.getcppt());
        BtnMonitoringEWSdewasa.setEnabled(akses.getcppt());
        BtnMonitoringPEWSanak.setEnabled(akses.getcppt());
        BtnMonitoringEWSobsgyn.setEnabled(akses.getcppt());
        BtnTransferSerahTerima.setEnabled(akses.getpemberian_obat());
        BtnPersetujuanTindakan.setEnabled(akses.getpemberian_obat());
        BtnSkriningUlangGizi.setEnabled(akses.getassesmen_gizi_harian());
        BtnMonevAsuhanGizi.setEnabled(akses.getmonev_asuhan_gizi());
        BtnAsuhanGizi.setEnabled(akses.getassesmen_gizi_harian());
        BtnAsesmenUlangGizi.setEnabled(akses.getassesmen_gizi_ulang());
        BtnLembarObservasi.setEnabled(akses.getcppt());
        BtnTransferTindakan.setEnabled(akses.getcppt());
        BtnAsesmenPraSedasi.setEnabled(akses.getcppt());
        BtnCeklisPraOperasi.setEnabled(akses.getcppt());
        BtnCeklisKesiapanAnestesi.setEnabled(akses.getcppt());
        BtnCeklisKeselamatanOperasi.setEnabled(akses.getcppt());
        BtnAsesmenPreInduksi.setEnabled(akses.getcppt());
        BtnAsesmenKeperawatanPerioperatif.setEnabled(akses.getcppt());
        BtnPerencanaanPulang.setEnabled(akses.getcppt());
        BtnCatatanMaterial.setEnabled(akses.getcppt());
        BtnRekonsiliasiObat.setEnabled(akses.getberi_obat());
        BtnObservasiKala1.setEnabled(akses.getcppt());
        BtnSamplingPemanfaatanRM.setEnabled(akses.getadmin());        
        BtnLaporanOperasi.setEnabled(akses.getcppt());
        BtnCatatanRuangPemulihan.setEnabled(akses.getcppt());
        BtnFormulirSiteMarking.setEnabled(akses.getcppt());
        BtnSerahTerimaPasca.setEnabled(akses.getcppt());
        BtnInformasiTindakanPembiusan.setEnabled(akses.getcppt());
        BtnEvaluasiPraAnestesi.setEnabled(akses.getcppt());
        BtnAsesmenPraSedasiKonsepIAR.setEnabled(akses.getcppt());
        BtnCatatanSedasiAnestesi.setEnabled(akses.getcppt());
        BtnPersetujuanTindakanIBS.setEnabled(akses.getpemberian_obat());
        BtnTransferTindakanIBS.setEnabled(akses.getcppt());
        BtnMaskepNyeriAkut.setEnabled(akses.getcppt());
        BtnMaskepPerfusiPerifer.setEnabled(akses.getcppt());
        BtnMaskepResikoHipotermia.setEnabled(akses.getcppt());
        BtnMaskepResikoHipovolemia.setEnabled(akses.getcppt());
        BtnMaskepBersihanJalanNafas.setEnabled(akses.getcppt());
        BtnMaskepKetidakstabilanGlukosa.setEnabled(akses.getcppt());
        
        if (akses.getcppt() == true || akses.getbpjs_sep() == true || akses.getadmin()== true) {
            BtnPemberianInformasiEdukasi.setEnabled(true);
            BtnPemberianInformasiEdukasiIBS.setEnabled(true);
        } else {
            BtnPemberianInformasiEdukasi.setEnabled(false);
            BtnPemberianInformasiEdukasiIBS.setEnabled(false);
        }
    }
    
    public void setData(String norw, String norm, String nmpasien,
            String status, String kdkmr, String rgrawat, String nmgedung,
            String tglmsk) {
        
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        TNmPasien.setText(nmpasien);
        nmUnit.setText(rgrawat);
        stts = status;
        kdkamar = kdkmr;        
        gedung = nmgedung;
        TtglMasuk.setText(tglmsk);
        tombolCek(norw);
        
        if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + norw + "' and kd_pj='B01'") > 0) {
            BtnSuratPernyataanRanapBpjs.setEnabled(true);
            BtnSuratPernyataanNaikKelas.setEnabled(true);
            BtnSuratPernyataanBukanKLL.setEnabled(true);
            BtnSuratPernyataanBayarDenda.setEnabled(true);
            BtnSuratPernyataanRanapNonBpjs.setEnabled(false);
        } else {
            BtnSuratPernyataanRanapBpjs.setEnabled(false);
            BtnSuratPernyataanNaikKelas.setEnabled(false);
            BtnSuratPernyataanBukanKLL.setEnabled(false);
            BtnSuratPernyataanBayarDenda.setEnabled(false);
            BtnSuratPernyataanRanapNonBpjs.setEnabled(true);
        }
    }

    private void tombolCek(String norawat) {
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_bedah_ranap where no_rawat='" + norawat + "'") == 0) {
            BtnAsesmenMedikBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenMedikBedah.setToolTipText("Asesmen Medik Bedah BELUM diisi oleh dokter..!!!");
        } else {
            BtnAsesmenMedikBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenMedikBedah.setToolTipText("Asesmen Medik Bedah SUDAH diisi oleh dokter..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_dewasa_ranap where no_rawat='" + norawat + "'") == 0) {
            BtnAsesmenMedikDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenMedikDewasa.setToolTipText("Asesmen Medik Dewasa BELUM diisi oleh dokter..!!!");
        } else {
            BtnAsesmenMedikDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenMedikDewasa.setToolTipText("Asesmen Medik Dewasa SUDAH diisi oleh dokter..!!!");
        }

        if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norawat + "'") == 0) {
            BtnAsesmenKeperawatanDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenKeperawatanDewasa.setToolTipText("Asesmen Keperawatan Dewasa BELUM diisi oleh petugas..!!!");
        } else {
            BtnAsesmenKeperawatanDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenKeperawatanDewasa.setToolTipText("Asesmen Keperawatan Dewasa SUDAH diisi oleh petugas..!!!");
        }

        if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + norawat + "' and tgl_cppt=date(now()) and flag_hapus='tidak'") == 0) {
            BtnCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnCPPT.setToolTipText("CPPT untuk tanggal hari ini BELUM diisi oleh petugas..!!!");
        } else {
            BtnCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnCPPT.setToolTipText("CPPT untuk tanggal hari ini SUDAH diisi oleh petugas..!!!");
        }

        if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where no_rawat='" + norawat + "' and tgl_perawatan=date(now())") == 0) {
            BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_basket_8726_merah.png")));
            BtnResep.setToolTipText("Pada hari ini pasien tersebut BELUM diberi resep oleh dokter..!!!");
        } else {
            BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_basket_8726.png")));
            BtnResep.setToolTipText("Pada hari ini pasien tersebut SUDAH diberi resep oleh dokter..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + norawat + "'") == 0) {
            BtnRingkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnRingkasan.setToolTipText("Ringkasan pulang rawat inap BELUM diisi oleh dokter DPJP..!!!");
        } else {
            BtnRingkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnRingkasan.setToolTipText("Ringkasan pulang rawat inap SUDAH diisi oleh dokter DPJP..!!!");
        }

        if (Sequel.cariInteger("select count(-1) from catatan_tindakan_keperawatan where no_rawat='" + norawat + "' and tanggal=date(now())") == 0) {
            BtnCTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnCTK.setToolTipText("Catatan Tindakan Keperawatan pasien tersebut pada hari ini BELUM diisi oleh petugas..!!!");
        } else {
            BtnCTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnCTK.setToolTipText("Catatan Tindakan Keperawatan pasien tersebut pada hari ini SUDAH diisi oleh petugas..!!!");
        }

        if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + norawat + "' and tgl_pemberian=date(now())") == 0) {
            BtnJadwalObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1404047834_application-vnd.ms-excel_merah.png")));
            BtnJadwalObat.setToolTipText("Jadwal pemberian obat pasien tersebut pada hari ini BELUM dibikinkan oleh petugas..!!!");
        } else {
            BtnJadwalObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1404047834_application-vnd.ms-excel.png")));
            BtnJadwalObat.setToolTipText("Jadwal pemberian obat pasien tersebut pada hari ini SUDAH dibikinkan oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from pemantauan_harian_24jam where no_rawat='" + norawat + "' and tgl_pantau=date(now())") == 0) {
            BtnPantauHarianPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20_biru.png")));
            BtnPantauHarianPasien.setToolTipText("Data pemantauan harian pasien pada hari ini BELUM dibikinkan oleh petugas..!!!");
            BtnGrafikPantauHarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20_biru.png")));
            BtnGrafikPantauHarian.setToolTipText("Grafik hasil pemantauan harian pasien pada hari ini BELUM dibikinkan oleh petugas..!!!");
        } else {
            BtnPantauHarianPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20.png")));
            BtnPantauHarianPasien.setToolTipText("Data pemantauan harian pasien pada hari ini SUDAH dibikinkan oleh petugas..!!!");
            BtnGrafikPantauHarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20.png")));
            BtnGrafikPantauHarian.setToolTipText("Grafik hasil pemantauan harian pasien pada hari ini SUDAH dibikinkan oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from asesmen_ulang_resiko_jatuh where no_rawat='" + norawat + "' and tgl_asesmen=date(now())") == 0) {
            BtnAsesmenUlangRJDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenUlangRJDewasa.setToolTipText("Asesmen Ulang Resiko Jatuh Dewasa BELUM diisi oleh petugas..!!!");
        } else {
            BtnAsesmenUlangRJDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenUlangRJDewasa.setToolTipText("Asesmen Ulang Resiko Jatuh Dewasa SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from pengelolaan_transfusi_darah where no_rawat='" + norawat + "' and tanggal=date(now())") == 0) {
            BtnPengelolaanTranfusiDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481001585_blood_drop.png")));
            BtnPengelolaanTranfusiDarah.setToolTipText("Pengelolaan Pasien Transfusi Darah BELUM diisi oleh petugas..!!!");
        } else {
            BtnPengelolaanTranfusiDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481001585_blood_drop_biru.png")));
            BtnPengelolaanTranfusiDarah.setToolTipText("Pengelolaan Pasien Transfusi Darah SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from monitoring_ews_dewasa where no_rawat='" + norawat + "' and tanggal=date(now())") == 0) {
            BtnMonitoringEWSdewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png")));
            BtnMonitoringEWSdewasa.setToolTipText("Monitoring EWS Dewasa BELUM diisi oleh petugas..!!!");
        } else {
            BtnMonitoringEWSdewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940_biru.png")));
            BtnMonitoringEWSdewasa.setToolTipText("Monitoring EWS Dewasa SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat='" + norawat + "' and kd_kamar_msk<>'IGDK'") == 0) {
            BtnTransferSerahTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnTransferSerahTerima.setToolTipText("Data Transfer & Serah Terima Pasien BELUM diisi oleh petugas..!!!");
        } else {
            BtnTransferSerahTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnTransferSerahTerima.setToolTipText("Data Transfer & Serah Terima Pasien SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + norawat + "'") == 0) {
            BtnAsesmenKeperawatanAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenKeperawatanAnak.setToolTipText("Asesmen Keperawatan Anak BELUM diisi oleh petugas..!!!");
        } else {
            BtnAsesmenKeperawatanAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenKeperawatanAnak.setToolTipText("Asesmen Keperawatan Anak SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from asesmen_keperawatan_perinatologi where no_rawat='" + norawat + "'") == 0) {
            BtnAsesmenKeperawatanPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenKeperawatanPerinatologi.setToolTipText("Asesmen Keperawatan Perinatolgi BELUM diisi oleh petugas..!!!");
        } else {
            BtnAsesmenKeperawatanPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenKeperawatanPerinatologi.setToolTipText("Asesmen Keperawatan Perinatolgi SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_anak_ranap where no_rawat='" + norawat + "'") == 0) {
            BtnAsesmenMedikAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenMedikAnak.setToolTipText("Asesmen Medik Anak BELUM diisi oleh dokter..!!!");
        } else {
            BtnAsesmenMedikAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenMedikAnak.setToolTipText("Asesmen Medik Anak SUDAH diisi oleh dokter..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_perinatologi where no_rawat='" + norawat + "'") == 0) {
            BtnAsesmenMedikPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenMedikPerinatologi.setToolTipText("Asesmen Medik Perinatologi BELUM diisi oleh dokter..!!!");
        } else {
            BtnAsesmenMedikPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenMedikPerinatologi.setToolTipText("Asesmen Medik Perinatologi SUDAH diisi oleh dokter..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from asesmen_ulang_resiko_jatuh_anak where no_rawat='" + norawat + "' and tgl_asesmen=date(now())") == 0) {
            BtnAsesmenUlangRJAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenUlangRJAnak.setToolTipText("Asesmen Ulang Resiko Jatuh Anak BELUM diisi oleh petugas..!!!");
        } else {
            BtnAsesmenUlangRJAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenUlangRJAnak.setToolTipText("Asesmen Ulang Resiko Jatuh Anak SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from monitoring_pews_anak where no_rawat='" + norawat + "' and tanggal=date(now())") == 0) {
            BtnMonitoringPEWSanak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png")));
            BtnMonitoringPEWSanak.setToolTipText("Monitoring Pediatric EWS BELUM diisi oleh petugas..!!!");
        } else {
            BtnMonitoringPEWSanak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940_biru.png")));
            BtnMonitoringPEWSanak.setToolTipText("Monitoring Pediatric EWS SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from asesmen_restrain where no_rawat='" + norawat + "'") == 0) {
            BtnAsesmenRestrain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenRestrain.setToolTipText("Asesmen Restrain BELUM diisi oleh perawat/bidan..!!!");
        } else {
            BtnAsesmenRestrain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenRestrain.setToolTipText("Asesmen Restrain SUDAH diisi oleh perawat/bidan..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + norawat + "'") == 0) {
            BtnAsuhanGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsuhanGizi.setToolTipText("Asuhan Gizi Pasien BELUM diisi oleh petugas..!!!");
        } else {
            BtnAsuhanGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsuhanGizi.setToolTipText("Asuhan Gizi Pasien SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from assesmen_gizi_ulang where no_rawat='" + norawat + "' and tgl_assesmen=date(now())") == 0) {
            BtnAsesmenUlangGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record_merah.png")));
            BtnAsesmenUlangGizi.setToolTipText("Asesmen Ulang Gizi Pasien BELUM diisi oleh petugas..!!!");
        } else {
            BtnAsesmenUlangGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png")));
            BtnAsesmenUlangGizi.setToolTipText("Asesmen Ulang Gizi Pasien SUDAH diisi oleh petugas..!!!");
        }
        
        if (Sequel.cariInteger("select count(-1) from monitoring_ews_obsgyn where no_rawat='" + norawat + "' and tanggal=date(now())") == 0) {
            BtnMonitoringEWSobsgyn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png")));
            BtnMonitoringEWSobsgyn.setToolTipText("Monitoring EWS Obsgyn BELUM diisi oleh petugas..!!!");
        } else {
            BtnMonitoringEWSobsgyn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940_biru.png")));
            BtnMonitoringEWSobsgyn.setToolTipText("Monitoring EWS Obsgyn SUDAH diisi oleh petugas..!!!");
        }
    }
    
    private void isTampilMenuRanap() {
        jmlmenu = 0;
        String cari = TCari.getText().toLowerCase().trim();
        
        if (akses.getasesmen_medik_bedah_ranap() && BtnAsesmenMedikBedah.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenMedikBedah);
            jmlmenu++;
        }
        
        if (akses.getasesmen_medik_dewasa_ranap() && BtnAsesmenMedikDewasa.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenMedikDewasa);
            jmlmenu++;
        }
        
        if (akses.getasesmen_medik_anak_ranap() && BtnAsesmenMedikAnak.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenMedikAnak);
            jmlmenu++;
        }
        
        if (akses.getasesmen_medik_anak_ranap() && BtnAsesmenMedikPerinatologi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenMedikPerinatologi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnAsesmenKeperawatanDewasa.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenKeperawatanDewasa);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnAsesmenKeperawatanAnak.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenKeperawatanAnak);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnAsesmenKeperawatanPerinatologi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenKeperawatanPerinatologi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnScoreApgarPerinatologi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnScoreApgarPerinatologi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnPengamatanMenyusui.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnPengamatanMenyusui);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnSerahTerimaBayiPulang.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnSerahTerimaBayiPulang);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnAsesmenRestrain.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenRestrain);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnObservasiRestrain.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnObservasiRestrain);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnCPPT.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnCPPT);
            jmlmenu++;
        }
        
        if (akses.getresep_dokter() && BtnResep.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnResep);
            jmlmenu++;
        }
        
        if (akses.getringkasanpulangranap() && BtnRingkasan.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnRingkasan);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnCTK.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnCTK);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnAsesmenUlangRJDewasa.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenUlangRJDewasa);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnAsesmenUlangRJAnak.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenUlangRJAnak);
            jmlmenu++;
        }
        
        if (akses.getpemberian_obat() && BtnJadwalObat.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnJadwalObat);
            jmlmenu++;
        }
        
        if (akses.getpermintaan_lab() && BtnKonsul.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnKonsul);
            jmlmenu++;
        }
        
        if (akses.getpermintaan_lab() && BtnJawabKonsul.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnJawabKonsul);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnPantauHarianPasien.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnPantauHarianPasien);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnGrafikPantauHarian.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnGrafikPantauHarian);
            jmlmenu++;
        }
        
        if (akses.getkemoterapi() && BtnProtokolKemoterapi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnProtokolKemoterapi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnPengelolaanTranfusiDarah.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnPengelolaanTranfusiDarah);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMonitoringEWSdewasa.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnMonitoringEWSdewasa);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMonitoringPEWSanak.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnMonitoringPEWSanak);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMonitoringEWSobsgyn.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnMonitoringEWSobsgyn);
            jmlmenu++;
        }
        
        if (akses.getpemberian_obat() && BtnTransferSerahTerima.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnTransferSerahTerima);
            jmlmenu++;
        }
        
        if (akses.getpemberian_obat() && BtnPersetujuanTindakan.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnPersetujuanTindakan);
            jmlmenu++;
        }
        
        if (akses.getassesmen_gizi_harian() && BtnSkriningUlangGizi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnSkriningUlangGizi);
            jmlmenu++;
        }
        
        if (akses.getmonev_asuhan_gizi() && BtnMonevAsuhanGizi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnMonevAsuhanGizi);
            jmlmenu++;
        }
        
        if (akses.getassesmen_gizi_harian() && BtnAsuhanGizi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsuhanGizi);
            jmlmenu++;
        }
        
        if (akses.getassesmen_gizi_ulang() && BtnAsesmenUlangGizi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnAsesmenUlangGizi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnLembarObservasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnLembarObservasi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnTransferTindakan.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnTransferTindakan);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnPerencanaanPulang.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnPerencanaanPulang);
            jmlmenu++;
        }
        
        if (akses.getberi_obat() && BtnRekonsiliasiObat.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnRekonsiliasiObat);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnObservasiKala1.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnObservasiKala1);
            jmlmenu++;
        }
        
        if (akses.getadmin() && BtnSamplingPemanfaatanRM.getText().toLowerCase().trim().contains(cari)) {
            FormMenuRanap.add(BtnSamplingPemanfaatanRM);
            jmlmenu++;
        }
    }
    
    private void isTampilMenuMaskep() {
        jmlmenu = 0;
        String cari = TCari1.getText().toLowerCase().trim();
        
        if (akses.getcppt() && BtnMaskepNyeriAkut.getText().toLowerCase().trim().contains(cari)) {
            FormMasKep.add(BtnMaskepNyeriAkut);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMaskepPerfusiPerifer.getText().toLowerCase().trim().contains(cari)) {
            FormMasKep.add(BtnMaskepPerfusiPerifer);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMaskepResikoHipotermia.getText().toLowerCase().trim().contains(cari)) {
            FormMasKep.add(BtnMaskepResikoHipotermia);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMaskepResikoHipovolemia.getText().toLowerCase().trim().contains(cari)) {
            FormMasKep.add(BtnMaskepResikoHipovolemia);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMaskepBersihanJalanNafas.getText().toLowerCase().trim().contains(cari)) {
            FormMasKep.add(BtnMaskepBersihanJalanNafas);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMaskepKetidakstabilanGlukosa.getText().toLowerCase().trim().contains(cari)) {
            FormMasKep.add(BtnMaskepKetidakstabilanGlukosa);
            jmlmenu++;
        }
    }
    
    private void aturTampilanTombolRanap() {
        java.awt.Component[] komponen = FormMenuRanap.getComponents();

        for (java.awt.Component komponenMenu : komponen) {
            if (komponenMenu instanceof widget.ButtonBig) {
                widget.ButtonBig tombol
                        = (widget.ButtonBig) komponenMenu;

                tombol.setHorizontalAlignment(
                        javax.swing.SwingConstants.CENTER
                );

                tombol.setVerticalAlignment(
                        javax.swing.SwingConstants.CENTER
                );
            }
        }
    }
    
    private void aturTampilanTombolMaskep() {
        java.awt.Component[] komponen = FormMasKep.getComponents();

        for (java.awt.Component komponenMenu : komponen) {
            if (komponenMenu instanceof widget.ButtonBig) {
                widget.ButtonBig tombol
                        = (widget.ButtonBig) komponenMenu;

                tombol.setHorizontalAlignment(
                        javax.swing.SwingConstants.CENTER
                );

                tombol.setVerticalAlignment(
                        javax.swing.SwingConstants.CENTER
                );
            }
        }
    }
    
    private void aturLayoutMenuRanap() {
        int lebarViewport = scrolMenuRanap.getViewport().getExtentSize().width;
        int tinggiViewport = scrolMenuRanap.getViewport().getExtentSize().height;

        if (lebarViewport <= 0) {
            lebarViewport = scrolMenuRanap.getWidth();
        }

        if (tinggiViewport <= 0) {
            tinggiViewport = scrolMenuRanap.getHeight();
        }

        if (lebarViewport <= 0) {
            lebarViewport = internalFrame5.getWidth();
        }

        if (tinggiViewport <= 0) {
            tinggiViewport
                    = internalFrame5.getHeight() - internalFrame2.getHeight();
        }

        /*
     * Sama seperti menu frmUtama:
     * jumlah menu menentukan jumlah kolom.
         */
        if (jmlmenu <= 1) {
            grid = 1;
        } else if (jmlmenu <= 4) {
            grid = 2;
        } else if (jmlmenu <= 9) {
            grid = 3;
        } else if (jmlmenu <= 16) {
            grid = 4;
        } else {
            grid = 5;
        }

        int tinggiTombol = 120;
        int jarakHorizontal = 5;
        int jarakVertikal = 35;

        int jumlahBaris = (int) Math.ceil(
                (double) jmlmenu / grid
        );

        int tinggiIsi
                = (jumlahBaris * tinggiTombol)
                + (Math.max(0, jumlahBaris - 1) * jarakVertikal)
                + 20;

        /*
     * Jika isi lebih tinggi dari viewport,
     * scrollbar otomatis muncul.
         */
        tinggi = Math.max(tinggiViewport, tinggiIsi);

        FormMenuRanap.setLayout(new GridLayout(
                0,
                grid,
                jarakHorizontal,
                jarakVertikal
        ));

        Dimension ukuranMenu = new Dimension(
                Math.max(lebarViewport - 10, 200),
                tinggi
        );

        FormMenuRanap.setPreferredSize(ukuranMenu);
        FormMenuRanap.setMinimumSize(ukuranMenu);

        scrolMenuRanap.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrolMenuRanap.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrolMenuRanap.getVerticalScrollBar().setUnitIncrement(16);
        scrolMenuRanap.getVerticalScrollBar().setBlockIncrement(120);

        FormMenuRanap.revalidate();
        FormMenuRanap.repaint();

        scrolMenuRanap.getViewport().revalidate();
        scrolMenuRanap.getViewport().repaint();

        scrolMenuRanap.revalidate();
        scrolMenuRanap.repaint();
    }
    
    private void aturLayoutMenuMaskep() {
        int lebarViewport = scrolMenuMaskep.getViewport().getExtentSize().width;
        int tinggiViewport = scrolMenuMaskep.getViewport().getExtentSize().height;

        if (lebarViewport <= 0) {
            lebarViewport = scrolMenuMaskep.getWidth();
        }

        if (tinggiViewport <= 0) {
            tinggiViewport = scrolMenuMaskep.getHeight();
        }

        if (lebarViewport <= 0) {
            lebarViewport = internalFrame6.getWidth();
        }

        if (tinggiViewport <= 0) {
            tinggiViewport
                    = internalFrame6.getHeight() - internalFrame2.getHeight();
        }

        /*
     * Sama seperti menu frmUtama:
     * jumlah menu menentukan jumlah kolom.
         */
        if (jmlmenu <= 1) {
            grid = 1;
        } else if (jmlmenu <= 4) {
            grid = 2;
        } else if (jmlmenu <= 9) {
            grid = 3;
        } else if (jmlmenu <= 16) {
            grid = 4;
        } else {
            grid = 8;
        }

        int tinggiTombol = 120;
        int jarakHorizontal = 5;
        int jarakVertikal = 35;

        int jumlahBaris = (int) Math.ceil(
                (double) jmlmenu / grid
        );

        int tinggiIsi
                = (jumlahBaris * tinggiTombol)
                + (Math.max(0, jumlahBaris - 1) * jarakVertikal)
                + 20;

        /*
     * Jika isi lebih tinggi dari viewport,
     * scrollbar otomatis muncul.
         */
        tinggi = Math.max(tinggiViewport, tinggiIsi);

        FormMasKep.setLayout(new GridLayout(
                0,
                grid,
                jarakHorizontal,
                jarakVertikal
        ));

        Dimension ukuranMenu = new Dimension(
                Math.max(lebarViewport - 10, 200),
                tinggi
        );

        FormMasKep.setPreferredSize(ukuranMenu);
        FormMasKep.setMinimumSize(ukuranMenu);

        scrolMenuMaskep.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrolMenuMaskep.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrolMenuMaskep.getVerticalScrollBar().setUnitIncrement(16);
        scrolMenuMaskep.getVerticalScrollBar().setBlockIncrement(120);

        FormMasKep.revalidate();
        FormMasKep.repaint();

        scrolMenuMaskep.getViewport().revalidate();
        scrolMenuMaskep.getViewport().repaint();

        scrolMenuMaskep.revalidate();
        scrolMenuMaskep.repaint();
    }
    
    public void tampilkanMenuMaskep() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ChkInput1ActionPerformed(null);

            javax.swing.Timer timer
                    = new javax.swing.Timer(100, e -> {

                        ((javax.swing.Timer) e.getSource()).stop();

                        ChkInput1ActionPerformed(null);

                        scrolMenuMaskep.getVerticalScrollBar().setValue(0);
                    });

            timer.setRepeats(false);
            timer.start();
        });
    }
    
    private void tampilRMranap() {
        Valid.tabelKosong(tabMode);
        queryDinamis = "";
        sqlPembentuk = "SELECT GROUP_CONCAT("
                + "    CONCAT("
                + "        'SELECT ''', t.nama_rekam_medis, ''' AS rekam_medis, ',"
                + "        '''OK'' AS status ',"
                + "        'FROM `', t.nama_tabel, '` ',"
                + "        'WHERE no_rawat = ', QUOTE(?), ' ',"
                + "        'GROUP BY no_rawat'"
                + "    )"
                + "    ORDER BY t.urutan"
                + "    SEPARATOR ' UNION ALL '"
                + ") AS query_dinamis "
                + "FROM ("
                + "    SELECT 1 AS urutan, 'persetujuan_ranap' AS nama_tabel, 'Persetujuan Rawat Inap' AS nama_rekam_medis "
                + "    UNION ALL SELECT 2, 'general_consent', 'General Consent' "
                + "    UNION ALL SELECT 3, 'surat_pernyataan_ranap_bpjs', 'Surat Pernyataan Rawat Inap Peserta BPJS' "
                + "    UNION ALL SELECT 4, 'surat_pernyataan_naik_kelas_bpjs', 'Surat Pernyataan Naik Kelas Rawat BPJS' "
                + "    UNION ALL SELECT 5, 'surat_pernyataan_bukan_kll', 'Surat Pernyataan Bukan KLL' "
                + "    UNION ALL SELECT 6, 'surat_pernyataan_bayar_denda', 'Surat Pernyataan Bayar Denda' "
                + "    UNION ALL SELECT 7, 'surat_pernyataan_ranap_non_bpjs', 'Surat Pernyataan Rawat Inap Non BPJS' "
                + "    UNION ALL SELECT 8, 'asesmen_restrain', 'Asesmen Restrain' "
                + "    UNION ALL SELECT 9, 'observasi_restrain', 'Observasi Restrain' "
                + "    UNION ALL SELECT 10, 'asesmen_medik_bedah_ranap', 'Asesmen Medik Bedah' "
                + "    UNION ALL SELECT 11, 'asesmen_medik_dewasa_ranap', 'Asesmen Medik Dewasa' "
                + "    UNION ALL SELECT 12, 'asesmen_medik_anak_ranap', 'Asesmen Medik Anak' "
                + "    UNION ALL SELECT 13, 'asesmen_medik_perinatologi', 'Asesmen Medik Perinatologi' "
                + "    UNION ALL SELECT 14, 'penilaian_awal_keperawatan_dewasa_ranap', 'Asesmen Keperawatan Dewasa' "
                + "    UNION ALL SELECT 15, 'penilaian_awal_keperawatan_anak_ranap', 'Asesmen Keperawatan Anak' "
                + "    UNION ALL SELECT 16, 'asesmen_keperawatan_perinatologi', 'Asesmen Keperawatan Perinatologi' "
                + "    UNION ALL SELECT 17, 'cppt', 'Catatan Perkembangan Pasien Terintegrasi (CPPT)' "
                + "    UNION ALL SELECT 18, 'ringkasan_pulang_ranap', 'Ringkasan Pulang Rawat Inap' "
                + "    UNION ALL SELECT 19, 'catatan_tindakan_keperawatan', 'Catatan Tindakan Keperawatan/Kebidanan' "
                + "    UNION ALL SELECT 20, 'asesmen_ulang_resiko_jatuh', 'Asesmen Ulang Risiko Jatuh Dewasa' "
                + "    UNION ALL SELECT 21, 'asesmen_ulang_resiko_jatuh_anak', 'Asesmen Ulang Risiko Jatuh Anak' "
                + "    UNION ALL SELECT 22, 'transfer_serah_terima_pasien_igd', 'Transfer Serah Terima Pasien' "
                + "    UNION ALL SELECT 23, 'lembar_observasi', 'Lembar Observasi' "
                + "    UNION ALL SELECT 24, 'observasi_kala1_kebidanan', 'Observasi Kala I (Kebidanan)' "
                + "    UNION ALL SELECT 25, 'pengelolaan_transfusi_darah', 'Pengelolaan Transfusi Darah' "
                + "    UNION ALL SELECT 26, 'monitoring_ews_dewasa', 'Monitoring EWS Dewasa' "
                + "    UNION ALL SELECT 27, 'monitoring_pews_anak', 'Monitoring PEWS Anak' "
                + "    UNION ALL SELECT 28, 'monitoring_ews_obsgyn', 'Monitoring EWS Obsgyn' "
                + "    UNION ALL SELECT 29, 'skor_apgar_downe_cap_jari_perinatologi', 'Skor APGAR, Downe, dan Cap Jari' "
                + "    UNION ALL SELECT 30, 'pengamatan_menyusui_perinatologi', 'Bantuan Pengamatan Menyusui' "
                + "    UNION ALL SELECT 31, 'serah_terima_bayi_pulang_perinatologi', 'Serah Terima Bayi Pulang' "
                + "    UNION ALL SELECT 32, 'rekonsiliasi_obat', 'Rekonsiliasi Obat' "
                + "    UNION ALL SELECT 33, 'pemberian_informasi_edukasi', 'Pemberian Informasi dan Edukasi' "
                + "    UNION ALL SELECT 34, 'penilaian_informasi_edukasi', 'Penilaian Informasi dan Edukasi' "
                + "    UNION ALL SELECT 35, 'pemberian_obat', 'Jadwal Beri Obat Oral/Injeksi' "
                + "    UNION ALL SELECT 36, 'transfer_sebelum_tindakan', 'Transfer Pasien Untuk Tindakan' "
                + "    UNION ALL SELECT 37, 'perencanaan_pulang_ranap', 'Perencanaan Pulang' "
                + "    UNION ALL SELECT 38, 'surat_konsul_unit_ranap', 'Permintaan Konsul Antar Unit' "
                + "    UNION ALL SELECT 39, 'surat_tindakan_kedokteran', 'Persetujuan/Penolakan/Penundaan Tindakan' "
                + "    UNION ALL SELECT 40, 'pemantauan_harian_24jam', 'Pemantauan Harian Pasien' "
                + "    UNION ALL SELECT 41, 'protokol_kemoterapi', 'Protokol Kemoterapi' "
                + "    UNION ALL SELECT 42, 'skrining_gizi_ulang', 'Skrining Gizi Ulang' "
                + "    UNION ALL SELECT 43, 'asuhan_gizi_ranap', 'Asuhan Gizi Rawat Inap' "
                + "    UNION ALL SELECT 44, 'monev_asuhan_gizi', 'Monitoring dan Evaluasi Asuhan Gizi' "
                + "    UNION ALL SELECT 45, 'assesmen_gizi_ulang', 'Asesmen Gizi Ulang') AS t";

        if (TNoRW.getText().isEmpty()) {
            return;
        }

        try {
            // Mencegah hasil GROUP_CONCAT terpotong.
            try (java.sql.Statement st = koneksi.createStatement()) {
                st.execute("SET SESSION group_concat_max_len = 1000000");
            }

            // Tahap 1: membentuk query UNION ALL.
            try (java.sql.PreparedStatement psPembentuk = koneksi.prepareStatement(sqlPembentuk)) {
                psPembentuk.setString(1, TNoRW.getText());
                try (java.sql.ResultSet rsPembentuk = psPembentuk.executeQuery()) {
                    if (rsPembentuk.next()) {
                        queryDinamis = rsPembentuk.getString("query_dinamis");
                    }
                }
            }

            if (queryDinamis == null || queryDinamis.trim().isEmpty()) {
                return;
            }

            // Tahap 2: menjalankan query dinamis dan memasukkannya ke JTable.
            try (java.sql.PreparedStatement psData = koneksi.prepareStatement(queryDinamis); java.sql.ResultSet rsData = psData.executeQuery()) {
                x = 1;
                while (rsData.next()) {
                    tabMode.addRow(new String[]{
                        x + ".",
                        rsData.getString("rekam_medis"),
                        rsData.getString("status")
                    });
                    x++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRMmaskep() {
        Valid.tabelKosong(tabMode1);
        queryDinamis = "";
        sqlPembentuk = "SELECT GROUP_CONCAT("
                + "    CONCAT("
                + "        'SELECT ''', t.nama_rekam_medis, ''' AS rekam_medis, ',"
                + "        '''OK'' AS status ',"
                + "        'FROM `', t.nama_tabel, '` ',"
                + "        'WHERE no_rawat = ', QUOTE(?), ' ',"
                + "        'GROUP BY no_rawat'"
                + "    )"
                + "    ORDER BY t.urutan"
                + "    SEPARATOR ' UNION ALL '"
                + ") AS query_dinamis "
                + "FROM ("
                + "    SELECT 1 AS urutan, 'masalah_keperawatan_nyeri_akut' AS nama_tabel, 'Nyeri Akut' AS nama_rekam_medis "
                + "    UNION ALL SELECT 2, 'masalah_keperawatan_perfusi_perifer_tdk_efektif', 'Perfusi Perifer Tidak Efektif' "
                + "    UNION ALL SELECT 3, 'masalah_keperawatan_hipotermia', 'Resiko Hipotermia' "
                + "    UNION ALL SELECT 4, 'masalah_keperawatan_hipovolemia', 'Resiko Hipovolemia' "
                + "    UNION ALL SELECT 5, 'masalah_keperawatan_bersihan_jln_nafas_tdk_efektif', 'Bersihan Jalan Nafas Tidak Efektif' "
                + "    UNION ALL SELECT 6, 'masalah_keperawatan_ketidakstabilan_glukosa_darah', 'Ketidakstabilan Glukosa Darah') AS t";

        if (TNoRW.getText().isEmpty()) {
            return;
        }

        try {
            // Mencegah hasil GROUP_CONCAT terpotong.
            try (java.sql.Statement st = koneksi.createStatement()) {
                st.execute("SET SESSION group_concat_max_len = 1000000");
            }

            // Tahap 1: membentuk query UNION ALL.
            try (java.sql.PreparedStatement psPembentuk = koneksi.prepareStatement(sqlPembentuk)) {
                psPembentuk.setString(1, TNoRW.getText());
                try (java.sql.ResultSet rsPembentuk = psPembentuk.executeQuery()) {
                    if (rsPembentuk.next()) {
                        queryDinamis = rsPembentuk.getString("query_dinamis");
                    }
                }
            }

            if (queryDinamis == null || queryDinamis.trim().isEmpty()) {
                return;
            }

            // Tahap 2: menjalankan query dinamis dan memasukkannya ke JTable.
            try (java.sql.PreparedStatement psData = koneksi.prepareStatement(queryDinamis); java.sql.ResultSet rsData = psData.executeQuery()) {
                x = 1;
                while (rsData.next()) {
                    tabMode1.addRow(new String[]{
                        x + ".",
                        rsData.getString("rekam_medis"),
                        rsData.getString("status")
                    });
                    x++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRMibs() {
        Valid.tabelKosong(tabMode2);
        queryDinamis = "";
        sqlPembentuk = "SELECT GROUP_CONCAT("
                + "    CONCAT("
                + "        'SELECT ''', t.nama_rekam_medis, ''' AS rekam_medis, ',"
                + "        '''OK'' AS status ',"
                + "        'FROM `', t.nama_tabel, '` ',"
                + "        'WHERE no_rawat = ', QUOTE(?), ' ',"
                + "        'GROUP BY no_rawat'"
                + "    )"
                + "    ORDER BY t.urutan"
                + "    SEPARATOR ' UNION ALL '"
                + ") AS query_dinamis "
                + "FROM ("
                + "    SELECT 1 AS urutan, 'evaluasi_pra_anestesi_operasi' AS nama_tabel, 'Evaluasi Pra Anestesi' AS nama_rekam_medis "
                + "    UNION ALL SELECT 2, 'ceklis_pra_operasi', 'Checklist Pra Operasi' "
                + "    UNION ALL SELECT 3, 'formulir_site_marking_operasi', 'Formulir Site Marking Operasi' "
                + "    UNION ALL SELECT 4, 'ceklis_kesiapan_anestesi', 'Checklist Kesiapan Anestesi' "
                + "    UNION ALL SELECT 5, 'asesmen_pra_sedasi_konsep_iar', 'Asesmen Pra Sedasi Konsep IAR' "
                + "    UNION ALL SELECT 6, 'asesmen_pre_induksi', 'Asesmen Pre Induksi' "
                + "    UNION ALL SELECT 7, 'asesmen_keperawatan_perioperatif', 'Assesmen Keperawatan Perioperatif' "
                + "    UNION ALL SELECT 8, 'ceklis_keselamatan_operasi1', 'Checklist Keselamatan Operasi' "
                + "    UNION ALL SELECT 9, 'catatan_sedasi_anestesi', 'Catatan Sedasi / Anestesi' "
                + "    UNION ALL SELECT 10, 'laporan_operasi', 'Laporan Operasi' "
                + "    UNION ALL SELECT 11, 'catatan_material_operasi', 'Catatan Pemakaian Obat & Material' "
                + "    UNION ALL SELECT 12, 'catatan_ruang_pemulihan', 'Catatan Ruang Pemulihan' "
                + "    UNION ALL SELECT 13, 'serah_terima_pasien_pasca_operasi', 'Serah Terima Pasca Operasi' "
                + "    UNION ALL SELECT 14, 'informasi_tindakan_pembiusan_operasi', 'Informasi Tindakan Pembiusan' "
                + "    UNION ALL SELECT 15, 'asesmen_pra_sedasi', 'Asesmen Pra Sedasi') AS t";

        if (TNoRW.getText().isEmpty()) {
            return;
        }

        try {
            // Mencegah hasil GROUP_CONCAT terpotong.
            try (java.sql.Statement st = koneksi.createStatement()) {
                st.execute("SET SESSION group_concat_max_len = 1000000");
            }

            // Tahap 1: membentuk query UNION ALL.
            try (java.sql.PreparedStatement psPembentuk = koneksi.prepareStatement(sqlPembentuk)) {
                psPembentuk.setString(1, TNoRW.getText());
                try (java.sql.ResultSet rsPembentuk = psPembentuk.executeQuery()) {
                    if (rsPembentuk.next()) {
                        queryDinamis = rsPembentuk.getString("query_dinamis");
                    }
                }
            }

            if (queryDinamis == null || queryDinamis.trim().isEmpty()) {
                return;
            }

            // Tahap 2: menjalankan query dinamis dan memasukkannya ke JTable.
            try (java.sql.PreparedStatement psData = koneksi.prepareStatement(queryDinamis); java.sql.ResultSet rsData = psData.executeQuery()) {
                x = 1;
                while (rsData.next()) {
                    tabMode2.addRow(new String[]{
                        x + ".",
                        rsData.getString("rekam_medis"),
                        rsData.getString("status")
                    });
                    x++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
