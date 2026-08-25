package setting;

import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import laporan.DlgICDOncologyMorphology;
import laporan.DlgICDOncologyTopography;
import rekammedis.DlgAssesmenGiziUlang;
import rekammedis.DlgCPPT;
import rekammedis.DlgCatatanTindakanKeperawatan;
import rekammedis.DlgHistoriIPAddressPetugasERM;
import rekammedis.DlgMasterMaterialOperasi;
import rekammedis.DlgMonevAsuhanGizi;
import rekammedis.RMAsesmenAwalKebidanan1;
import rekammedis.RMAsesmenKeperawatanAnakRanap;
import rekammedis.RMAsesmenKeperawatanDewasaRanap;
import rekammedis.RMAsesmenKeperawatanPerinatologi;
import rekammedis.RMAsesmenKeperawatanPerioperatif;
import rekammedis.RMAsesmenMedikAnakRanap;
import rekammedis.RMAsesmenMedikBedahRanap;
import rekammedis.RMAsesmenMedikDewasaRanap;
import rekammedis.RMAsesmenMedikKebidanan;
import rekammedis.RMAsesmenMedikPerinatologi;
import rekammedis.RMAsesmenPasienTerminal;
import rekammedis.RMAsesmenPraSedasi;
import rekammedis.RMAsesmenPraSedasiKonsepIAR;
import rekammedis.RMAsesmenPreInduksi;
import rekammedis.RMAsesmenRestrain;
import rekammedis.RMAsesmenUlangResikoJatuhAnak;
import rekammedis.RMAsesmenUlangResikoJatuhDewasa;
import rekammedis.RMAsuhanGiziRanap;
import rekammedis.RMCatatanPemakaianObatMaterialOperasi;
import rekammedis.RMCatatanRuangPemulihan;
import rekammedis.RMCatatanSedasiAnestesi;
import rekammedis.RMCeklisKeselamatanOperasi;
import rekammedis.RMCeklisKesiapanAnestesi;
import rekammedis.RMCeklisPraOperasi;
import rekammedis.RMEvaluasiPraAnestesi;
import rekammedis.RMFormulirSiteMarkingOperasi;
import rekammedis.RMGeneralConsent;
import rekammedis.RMInformasiTindakanPembiusan;
import rekammedis.RMInstruksiDokterDNR;
import rekammedis.RMLaporanOperasi;
import rekammedis.RMLembarObservasi;
import rekammedis.RMMasalahKeperawatanBersihanJlnNafas;
import rekammedis.RMMasalahKeperawatanHipotermia;
import rekammedis.RMMasalahKeperawatanHipovolemia;
import rekammedis.RMMasalahKeperawatanKetidakstabilanGlukosaDarah;
import rekammedis.RMMasalahKeperawatanNyeriAkut;
import rekammedis.RMMasalahKeperawatanPerfusiPeriferTdkEfektif;
import rekammedis.RMMonitoringEWSDewasa;
import rekammedis.RMMonitoringEWSObsgyn;
import rekammedis.RMMonitoringPEWSAnak;
import rekammedis.RMObservasiKala1;
import rekammedis.RMPartografPersalinan;
import rekammedis.RMPasienUntukTindakan;
import rekammedis.RMPemantauanHarian24Jam;
import rekammedis.RMPemberianInformasiEdukasi;
import rekammedis.RMPengamatanMenyusui;
import rekammedis.RMPengelolaanTransfusiDarah;
import rekammedis.RMPenilaianAwalKeperawatanIGDrz;
import rekammedis.RMPenilaianAwalKeperawatanKebidanan;
import rekammedis.RMPenilaianAwalKeperawatanRalan;
import rekammedis.RMPenilaianAwalKeperawatanRalanKemoterapi;
import rekammedis.RMPenilaianAwalMedikIGD;
import rekammedis.RMPenilaianAwalMedikObstetriRalan;
import rekammedis.RMPenilaianAwalMedisRalanGeriatri;
import rekammedis.RMPenilaianAwalMedisRalanMata;
import rekammedis.RMPenilaianAwalMedisRalanTHT;
import rekammedis.RMPenilaianTambahanGeriatri;
import rekammedis.RMPerencanaanPulang;
import rekammedis.RMPersetujuanRawatInap;
import rekammedis.RMProtokolKemoterapi;
import rekammedis.RMRegisterCancer;
import rekammedis.RMSerahTerimaBayiPulang;
import rekammedis.RMSerahTerimaPascaOperasi;
import rekammedis.RMSkorApgarDowneCapBayiLuarRS;
import rekammedis.RMSkorApgarDowneCapPerinatologi;
import rekammedis.RMSkriningUlangGizi;
import rekammedis.RMStatusKakiDiabetes;
import rekammedis.RMSuratPenyataanBayarDenda;
import rekammedis.RMSuratPenyataanBukanKLL;
import rekammedis.RMSuratPenyataanNaikKelas;
import rekammedis.RMSuratPenyataanRanapBPJS;
import rekammedis.RMSuratPenyataanRanapNonBPJS;
import rekammedis.RMSuratPernyataanDNR;
import rekammedis.RMTindakanKedokteran;
import rekammedis.RMTransferSerahTerimaIGD;
import rekammedis.RMTriaseIGD;
import rekammedis.RMTriasePediatrik;
import rekammedis.RMTriasePonek;
import simrskhanza.DlgDataCancer;
import simrskhanza.DlgNotepad;
import simrskhanza.DlgRingkasanPulangRalan;
import simrskhanza.DlgRingkasanPulangRanap;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class PanelMenuUtamaB extends javax.swing.JDialog {
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private String versi = "";
    private int jmlmenu = 0, grid = 0, tinggi = 0, i = 0;
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public PanelMenuUtamaB(frmUtama parent, boolean modal) {
        super(parent, modal);
        formUtama = parent;
        initComponents();
        aturTampilanTombol();

        versi = Sequel.cariIsi("select versi_update from history_update ORDER BY tgl_update desc, jam_update desc limit 1");        
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
        internalFrame2 = new widget.InternalFrame();
        jLabel3 = new widget.Label();
        TCari = new widget.TextBox();
        btnClear = new widget.Button();
        ChkInput = new widget.CekBox();
        scrollMenu = new widget.ScrollPane();
        FormMenu = new widget.PanelBiasa();
        btnPersetujuanRanap = new widget.ButtonBig();
        btnGeneralConsent = new widget.ButtonBig();
        btnSuratPernyataanRanapBpjs = new widget.ButtonBig();
        btnSuratPernyataanNaikKelas = new widget.ButtonBig();
        btnSuratPernyataanBukanKLL = new widget.ButtonBig();
        btnSuratPernyataanBayarDenda = new widget.ButtonBig();
        btnSuratPernyataanNonBpjs = new widget.ButtonBig();
        btnStatusKakiDiabetes = new widget.ButtonBig();
        btnHistoryIpAddressPetugasRM = new widget.ButtonBig();
        btnMasterCatatanMaterialOperasi = new widget.ButtonBig();
        btnTriasePonek = new widget.ButtonBig();
        BtnAsesmenMedikKebidanan = new widget.ButtonBig();
        btnPemberianInformasiEdukasi = new widget.ButtonBig();
        btnPerencanaanPulang = new widget.ButtonBig();
        BtnLaporanOperasi = new widget.ButtonBig();
        BtnCatatanRuangPemulihan = new widget.ButtonBig();
        BtnFormulirSiteMarking = new widget.ButtonBig();
        BtnSerahTerimaPascaOperasi = new widget.ButtonBig();
        BtnInformasiTindakanPembiusan = new widget.ButtonBig();
        BtnEvaluasiPraAnestesi = new widget.ButtonBig();
        BtnAsesmenPraSedasiKonsepIAR = new widget.ButtonBig();
        BtnCatatanSedasiAnestesi = new widget.ButtonBig();
        btnAsesmenKeperawatanPerioperatif = new widget.ButtonBig();
        btnCatatanMaterialOperasi = new widget.ButtonBig();
        btnCeklisPraOperasi = new widget.ButtonBig();
        btnCeklisKesiapanAnestesi = new widget.ButtonBig();
        btnCeklisKeselamatanOperasi = new widget.ButtonBig();
        btnTransferSerahTerimaPasien = new widget.ButtonBig();
        BtnMasalahKeperawatanNyeriAkut = new widget.ButtonBig();
        BtnMasalahKeperawatanPerfusiPeriferTdkEfektif = new widget.ButtonBig();
        btnCPPT = new widget.ButtonBig();
        btnTransferPasienTindakan = new widget.ButtonBig();
        btnLembarObservasi = new widget.ButtonBig();
        btnObservasiKala1 = new widget.ButtonBig();
        BtnPartograf = new widget.ButtonBig();
        btnRingkasanPulangRanap = new widget.ButtonBig();
        btnAsesmenPraSedasi = new widget.ButtonBig();
        btnAsesmenPreInduksi = new widget.ButtonBig();
        btnAsesmenMedikBedahRanap = new widget.ButtonBig();
        btnAsesmenMedikDewasaRanap = new widget.ButtonBig();
        btnAsesmenMedikPerinatologi = new widget.ButtonBig();
        btnAsesmenKeperawatanPerinatologi = new widget.ButtonBig();
        btnAsesmenMedikAnakRanap = new widget.ButtonBig();
        btnAsesmenRestrain = new widget.ButtonBig();
        btnPemantauanHarian24Jam = new widget.ButtonBig();
        btnSerahTerimaBayiPulang = new widget.ButtonBig();
        btnLembarBantuanPengamatanMenyusui = new widget.ButtonBig();
        btnSkorApgarDowneCapJariPerinatologi = new widget.ButtonBig();
        btnPersetujuanTindakan = new widget.ButtonBig();
        btnCatatanTindakanKeperawatan = new widget.ButtonBig();
        btnAsuhanGiziRanap = new widget.ButtonBig();
        btnSkriningGiziUlang = new widget.ButtonBig();
        btnAssesmenUlangGizi = new widget.ButtonBig();
        btnMonevAsuhanGizi = new widget.ButtonBig();
        btnMonitoringEWSDewasa = new widget.ButtonBig();
        btnMonitoringPediatricEWS = new widget.ButtonBig();
        btnMonitoringEWSObsgyn = new widget.ButtonBig();
        btnNotepad = new widget.ButtonBig();
        btnPengelolaanTransfusiDarah = new widget.ButtonBig();
        btnAsesmenUlangResikoJatuhDewasa = new widget.ButtonBig();
        btnAsesmenUlangResikoJatuhAnak = new widget.ButtonBig();
        btnProtokolKemoterapi = new widget.ButtonBig();
        btnAsesmenAwalKebidanan = new widget.ButtonBig();
        btnPenilaianAwalMedisRalanMata = new widget.ButtonBig();
        btnAsesmenKeperawatanDewasa = new widget.ButtonBig();
        btnAsesmenKeperawatanAnak = new widget.ButtonBig();
        btnAssesmenMedikIGD = new widget.ButtonBig();
        btnAssesmenKeperawatanIGD = new widget.ButtonBig();
        btnPenilaianAwalMedisRalanTHT = new widget.ButtonBig();
        btnPenilaianAwalKeperawatanRalan = new widget.ButtonBig();
        btnPenilaianAwalKeperawatanRalanKemoterapi = new widget.ButtonBig();
        btnAsesmenMedikObstetriRalan = new widget.ButtonBig();
        btnPenilaianAwalKeperawatanKebidananRalan = new widget.ButtonBig();
        btnTriasePediatrikIGD = new widget.ButtonBig();
        btnDataTriaseIGD = new widget.ButtonBig();
        btnPenilaianTambahanGeriatri = new widget.ButtonBig();
        btnPenilaianAwalMedisRalanGeriatri = new widget.ButtonBig();
        BtnMasalahKeperawatanResikoHipotermia = new widget.ButtonBig();
        btnRingkasanPulangRalan = new widget.ButtonBig();
        BtnMasalahKeperawatanResikoHipovolemia = new widget.ButtonBig();
        BtnMasterICDOtopography = new widget.ButtonBig();
        BtnMasterICDOmorphology = new widget.ButtonBig();
        BtnRegisterCancer = new widget.ButtonBig();
        BtnDataCancer = new widget.ButtonBig();
        BtnMaskepBersihanJalanNafas = new widget.ButtonBig();
        BtnMaskepKetidakstabilanGlukosa = new widget.ButtonBig();
        BtnSuratPernyataanDNR = new widget.ButtonBig();
        BtnInstruksiDokterDNR = new widget.ButtonBig();
        BtnScoreApgarPerinatologiLuar = new widget.ButtonBig();
        BtnAsesmenPasienTerminal = new widget.ButtonBig();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(12, 100));
        internalFrame1.setLayout(new java.awt.BorderLayout());

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(12, 39));
        internalFrame2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Cari Menu B :");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame2.add(jLabel3);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(400, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        internalFrame2.add(TCari);

        btnClear.setForeground(new java.awt.Color(0, 0, 0));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        btnClear.setGlassColor(new java.awt.Color(255, 255, 255));
        btnClear.setMinimumSize(new java.awt.Dimension(28, 23));
        btnClear.setName("btnClear"); // NOI18N
        btnClear.setPreferredSize(new java.awt.Dimension(30, 23));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        internalFrame2.add(btnClear);

        ChkInput.setBorder(null);
        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
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
        ChkInput.setPreferredSize(new java.awt.Dimension(100, 23));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        internalFrame2.add(ChkInput);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.PAGE_START);

        scrollMenu.setName("scrollMenu"); // NOI18N
        scrollMenu.setPreferredSize(new java.awt.Dimension(102, 557));

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(2400, 3240));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 25));

        btnPersetujuanRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnPersetujuanRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnPersetujuanRanap.setText("Persetujuan Rawat Inap");
        btnPersetujuanRanap.setIconTextGap(0);
        btnPersetujuanRanap.setName("btnPersetujuanRanap"); // NOI18N
        btnPersetujuanRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPersetujuanRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersetujuanRanapActionPerformed(evt);
            }
        });
        FormMenu.add(btnPersetujuanRanap);

        btnGeneralConsent.setForeground(new java.awt.Color(0, 0, 0));
        btnGeneralConsent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnGeneralConsent.setText("General Consent");
        btnGeneralConsent.setIconTextGap(0);
        btnGeneralConsent.setName("btnGeneralConsent"); // NOI18N
        btnGeneralConsent.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGeneralConsent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralConsentActionPerformed(evt);
            }
        });
        FormMenu.add(btnGeneralConsent);

        btnSuratPernyataanRanapBpjs.setForeground(new java.awt.Color(0, 0, 0));
        btnSuratPernyataanRanapBpjs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnSuratPernyataanRanapBpjs.setText("<html><div style=\"text-align: center;\">Surat Pernyataan<br>Rawat Inap Peserta BPJS</div></html>");
        btnSuratPernyataanRanapBpjs.setIconTextGap(0);
        btnSuratPernyataanRanapBpjs.setName("btnSuratPernyataanRanapBpjs"); // NOI18N
        btnSuratPernyataanRanapBpjs.setPreferredSize(new java.awt.Dimension(200, 105));
        btnSuratPernyataanRanapBpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuratPernyataanRanapBpjsActionPerformed(evt);
            }
        });
        FormMenu.add(btnSuratPernyataanRanapBpjs);

        btnSuratPernyataanNaikKelas.setForeground(new java.awt.Color(0, 0, 0));
        btnSuratPernyataanNaikKelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnSuratPernyataanNaikKelas.setText("<html><div style=\"text-align: center;\">Surat Pernyataan<br>Naik Kelas Rawat BPJS</div></html>");
        btnSuratPernyataanNaikKelas.setIconTextGap(0);
        btnSuratPernyataanNaikKelas.setName("btnSuratPernyataanNaikKelas"); // NOI18N
        btnSuratPernyataanNaikKelas.setPreferredSize(new java.awt.Dimension(200, 105));
        btnSuratPernyataanNaikKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuratPernyataanNaikKelasActionPerformed(evt);
            }
        });
        FormMenu.add(btnSuratPernyataanNaikKelas);

        btnSuratPernyataanBukanKLL.setForeground(new java.awt.Color(0, 0, 0));
        btnSuratPernyataanBukanKLL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnSuratPernyataanBukanKLL.setText("Surat Pernyataan Bukan KLL");
        btnSuratPernyataanBukanKLL.setIconTextGap(0);
        btnSuratPernyataanBukanKLL.setName("btnSuratPernyataanBukanKLL"); // NOI18N
        btnSuratPernyataanBukanKLL.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSuratPernyataanBukanKLL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuratPernyataanBukanKLLActionPerformed(evt);
            }
        });
        FormMenu.add(btnSuratPernyataanBukanKLL);

        btnSuratPernyataanBayarDenda.setForeground(new java.awt.Color(0, 0, 0));
        btnSuratPernyataanBayarDenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnSuratPernyataanBayarDenda.setText("Surat Pernyataan Bayar Denda");
        btnSuratPernyataanBayarDenda.setIconTextGap(0);
        btnSuratPernyataanBayarDenda.setName("btnSuratPernyataanBayarDenda"); // NOI18N
        btnSuratPernyataanBayarDenda.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSuratPernyataanBayarDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuratPernyataanBayarDendaActionPerformed(evt);
            }
        });
        FormMenu.add(btnSuratPernyataanBayarDenda);

        btnSuratPernyataanNonBpjs.setForeground(new java.awt.Color(0, 0, 0));
        btnSuratPernyataanNonBpjs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnSuratPernyataanNonBpjs.setText("Surat Pernyataan Ranap Non BPJS");
        btnSuratPernyataanNonBpjs.setIconTextGap(0);
        btnSuratPernyataanNonBpjs.setName("btnSuratPernyataanNonBpjs"); // NOI18N
        btnSuratPernyataanNonBpjs.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSuratPernyataanNonBpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuratPernyataanNonBpjsActionPerformed(evt);
            }
        });
        FormMenu.add(btnSuratPernyataanNonBpjs);

        btnStatusKakiDiabetes.setForeground(new java.awt.Color(0, 0, 0));
        btnStatusKakiDiabetes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/diabetic-foot.png"))); // NOI18N
        btnStatusKakiDiabetes.setText("Status Kaki Diabetes");
        btnStatusKakiDiabetes.setIconTextGap(0);
        btnStatusKakiDiabetes.setName("btnStatusKakiDiabetes"); // NOI18N
        btnStatusKakiDiabetes.setPreferredSize(new java.awt.Dimension(200, 90));
        btnStatusKakiDiabetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusKakiDiabetesActionPerformed(evt);
            }
        });
        FormMenu.add(btnStatusKakiDiabetes);

        btnHistoryIpAddressPetugasRM.setForeground(new java.awt.Color(0, 0, 0));
        btnHistoryIpAddressPetugasRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/backup-restore.png"))); // NOI18N
        btnHistoryIpAddressPetugasRM.setText("History IP Address e-RM");
        btnHistoryIpAddressPetugasRM.setIconTextGap(0);
        btnHistoryIpAddressPetugasRM.setName("btnHistoryIpAddressPetugasRM"); // NOI18N
        btnHistoryIpAddressPetugasRM.setPreferredSize(new java.awt.Dimension(200, 90));
        btnHistoryIpAddressPetugasRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryIpAddressPetugasRMActionPerformed(evt);
            }
        });
        FormMenu.add(btnHistoryIpAddressPetugasRM);

        btnMasterCatatanMaterialOperasi.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterCatatanMaterialOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_address-book_285679.png"))); // NOI18N
        btnMasterCatatanMaterialOperasi.setText("Master Catatan Material Operasi");
        btnMasterCatatanMaterialOperasi.setIconTextGap(0);
        btnMasterCatatanMaterialOperasi.setName("btnMasterCatatanMaterialOperasi"); // NOI18N
        btnMasterCatatanMaterialOperasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterCatatanMaterialOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterCatatanMaterialOperasiActionPerformed(evt);
            }
        });
        FormMenu.add(btnMasterCatatanMaterialOperasi);

        btnTriasePonek.setForeground(new java.awt.Color(0, 0, 0));
        btnTriasePonek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/report_icon.png"))); // NOI18N
        btnTriasePonek.setText("Triase Ponek");
        btnTriasePonek.setIconTextGap(0);
        btnTriasePonek.setName("btnTriasePonek"); // NOI18N
        btnTriasePonek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTriasePonek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTriasePonekActionPerformed(evt);
            }
        });
        FormMenu.add(btnTriasePonek);

        BtnAsesmenMedikKebidanan.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenMedikKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mp_viewer.png"))); // NOI18N
        BtnAsesmenMedikKebidanan.setText("Asesmen Medik Kebidanan");
        BtnAsesmenMedikKebidanan.setIconTextGap(0);
        BtnAsesmenMedikKebidanan.setName("BtnAsesmenMedikKebidanan"); // NOI18N
        BtnAsesmenMedikKebidanan.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnAsesmenMedikKebidanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenMedikKebidananActionPerformed(evt);
            }
        });
        FormMenu.add(BtnAsesmenMedikKebidanan);

        btnPemberianInformasiEdukasi.setForeground(new java.awt.Color(0, 0, 0));
        btnPemberianInformasiEdukasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/people.png"))); // NOI18N
        btnPemberianInformasiEdukasi.setText("Pemberian Informasi Dan Edukasi");
        btnPemberianInformasiEdukasi.setIconTextGap(0);
        btnPemberianInformasiEdukasi.setName("btnPemberianInformasiEdukasi"); // NOI18N
        btnPemberianInformasiEdukasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPemberianInformasiEdukasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemberianInformasiEdukasiActionPerformed(evt);
            }
        });
        FormMenu.add(btnPemberianInformasiEdukasi);

        btnPerencanaanPulang.setForeground(new java.awt.Color(0, 0, 0));
        btnPerencanaanPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/folder.png"))); // NOI18N
        btnPerencanaanPulang.setText("<html><div style=\"text-align: center;\">Perencanaan Pulang<br><i>(Discharge Planning)</i></div></html>");
        btnPerencanaanPulang.setIconTextGap(0);
        btnPerencanaanPulang.setName("btnPerencanaanPulang"); // NOI18N
        btnPerencanaanPulang.setPreferredSize(new java.awt.Dimension(200, 105));
        btnPerencanaanPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerencanaanPulangActionPerformed(evt);
            }
        });
        FormMenu.add(btnPerencanaanPulang);

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
        FormMenu.add(BtnLaporanOperasi);

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
        FormMenu.add(BtnCatatanRuangPemulihan);

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
        FormMenu.add(BtnFormulirSiteMarking);

        BtnSerahTerimaPascaOperasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnSerahTerimaPascaOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        BtnSerahTerimaPascaOperasi.setText("Serah Terima Pasca Operasi");
        BtnSerahTerimaPascaOperasi.setIconTextGap(0);
        BtnSerahTerimaPascaOperasi.setName("BtnSerahTerimaPascaOperasi"); // NOI18N
        BtnSerahTerimaPascaOperasi.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnSerahTerimaPascaOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSerahTerimaPascaOperasiActionPerformed(evt);
            }
        });
        FormMenu.add(BtnSerahTerimaPascaOperasi);

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
        FormMenu.add(BtnInformasiTindakanPembiusan);

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
        FormMenu.add(BtnEvaluasiPraAnestesi);

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
        FormMenu.add(BtnAsesmenPraSedasiKonsepIAR);

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
        FormMenu.add(BtnCatatanSedasiAnestesi);

        btnAsesmenKeperawatanPerioperatif.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenKeperawatanPerioperatif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnAsesmenKeperawatanPerioperatif.setText("<html><div style=\"text-align: center;\">Assesmen Keperawatan<br>Perioperatif</div></html>");
        btnAsesmenKeperawatanPerioperatif.setIconTextGap(0);
        btnAsesmenKeperawatanPerioperatif.setName("btnAsesmenKeperawatanPerioperatif"); // NOI18N
        btnAsesmenKeperawatanPerioperatif.setPreferredSize(new java.awt.Dimension(200, 105));
        btnAsesmenKeperawatanPerioperatif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenKeperawatanPerioperatifActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenKeperawatanPerioperatif);

        btnCatatanMaterialOperasi.setForeground(new java.awt.Color(0, 0, 0));
        btnCatatanMaterialOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        btnCatatanMaterialOperasi.setText("<html><div style=\"text-align: center;\">Catatan Pemakaian<br>Obat & Material</div></html>");
        btnCatatanMaterialOperasi.setIconTextGap(0);
        btnCatatanMaterialOperasi.setName("btnCatatanMaterialOperasi"); // NOI18N
        btnCatatanMaterialOperasi.setPreferredSize(new java.awt.Dimension(200, 105));
        btnCatatanMaterialOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatatanMaterialOperasiActionPerformed(evt);
            }
        });
        FormMenu.add(btnCatatanMaterialOperasi);

        btnCeklisPraOperasi.setForeground(new java.awt.Color(0, 0, 0));
        btnCeklisPraOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        btnCeklisPraOperasi.setText("Checklist Pra Operasi");
        btnCeklisPraOperasi.setIconTextGap(0);
        btnCeklisPraOperasi.setName("btnCeklisPraOperasi"); // NOI18N
        btnCeklisPraOperasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCeklisPraOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCeklisPraOperasiActionPerformed(evt);
            }
        });
        FormMenu.add(btnCeklisPraOperasi);

        btnCeklisKesiapanAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        btnCeklisKesiapanAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        btnCeklisKesiapanAnestesi.setText("Checklist Kesiapan Anestesi");
        btnCeklisKesiapanAnestesi.setIconTextGap(0);
        btnCeklisKesiapanAnestesi.setName("btnCeklisKesiapanAnestesi"); // NOI18N
        btnCeklisKesiapanAnestesi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCeklisKesiapanAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCeklisKesiapanAnestesiActionPerformed(evt);
            }
        });
        FormMenu.add(btnCeklisKesiapanAnestesi);

        btnCeklisKeselamatanOperasi.setForeground(new java.awt.Color(0, 0, 0));
        btnCeklisKeselamatanOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        btnCeklisKeselamatanOperasi.setText("Checklist Keselamatan Operasi");
        btnCeklisKeselamatanOperasi.setIconTextGap(0);
        btnCeklisKeselamatanOperasi.setName("btnCeklisKeselamatanOperasi"); // NOI18N
        btnCeklisKeselamatanOperasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCeklisKeselamatanOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCeklisKeselamatanOperasiActionPerformed(evt);
            }
        });
        FormMenu.add(btnCeklisKeselamatanOperasi);

        btnTransferSerahTerimaPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnTransferSerahTerimaPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816189_arrow_down.png"))); // NOI18N
        btnTransferSerahTerimaPasien.setText("Transfer & Serah Terima Pasien");
        btnTransferSerahTerimaPasien.setIconTextGap(0);
        btnTransferSerahTerimaPasien.setName("btnTransferSerahTerimaPasien"); // NOI18N
        btnTransferSerahTerimaPasien.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTransferSerahTerimaPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferSerahTerimaPasienActionPerformed(evt);
            }
        });
        FormMenu.add(btnTransferSerahTerimaPasien);

        BtnMasalahKeperawatanNyeriAkut.setForeground(new java.awt.Color(0, 0, 0));
        BtnMasalahKeperawatanNyeriAkut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMasalahKeperawatanNyeriAkut.setText("MasKep Nyeri Akut");
        BtnMasalahKeperawatanNyeriAkut.setIconTextGap(0);
        BtnMasalahKeperawatanNyeriAkut.setName("BtnMasalahKeperawatanNyeriAkut"); // NOI18N
        BtnMasalahKeperawatanNyeriAkut.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMasalahKeperawatanNyeriAkut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMasalahKeperawatanNyeriAkutActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMasalahKeperawatanNyeriAkut);

        BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.setForeground(new java.awt.Color(0, 0, 0));
        BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.setText("MasKep Perfusi Perifer Tidak Efektif");
        BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.setIconTextGap(0);
        BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.setName("BtnMasalahKeperawatanPerfusiPeriferTdkEfektif"); // NOI18N
        BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMasalahKeperawatanPerfusiPeriferTdkEfektifActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMasalahKeperawatanPerfusiPeriferTdkEfektif);

        btnCPPT.setForeground(new java.awt.Color(0, 0, 0));
        btnCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20.png"))); // NOI18N
        btnCPPT.setText("CPPT");
        btnCPPT.setIconTextGap(0);
        btnCPPT.setName("btnCPPT"); // NOI18N
        btnCPPT.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCPPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPPTActionPerformed(evt);
            }
        });
        FormMenu.add(btnCPPT);

        btnTransferPasienTindakan.setForeground(new java.awt.Color(0, 0, 0));
        btnTransferPasienTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481002123_wheelchair.png"))); // NOI18N
        btnTransferPasienTindakan.setText("Transfer Pasien Untuk Tindakan");
        btnTransferPasienTindakan.setIconTextGap(0);
        btnTransferPasienTindakan.setName("btnTransferPasienTindakan"); // NOI18N
        btnTransferPasienTindakan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTransferPasienTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferPasienTindakanActionPerformed(evt);
            }
        });
        FormMenu.add(btnTransferPasienTindakan);

        btnLembarObservasi.setForeground(new java.awt.Color(0, 0, 0));
        btnLembarObservasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist_pencil-o.png"))); // NOI18N
        btnLembarObservasi.setText("Lembar Observasi Pasien");
        btnLembarObservasi.setIconTextGap(0);
        btnLembarObservasi.setName("btnLembarObservasi"); // NOI18N
        btnLembarObservasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLembarObservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLembarObservasiActionPerformed(evt);
            }
        });
        FormMenu.add(btnLembarObservasi);

        btnObservasiKala1.setForeground(new java.awt.Color(0, 0, 0));
        btnObservasiKala1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist_pencil-o.png"))); // NOI18N
        btnObservasiKala1.setText("Observasi Kala 1 (Kebidanan)");
        btnObservasiKala1.setIconTextGap(0);
        btnObservasiKala1.setName("btnObservasiKala1"); // NOI18N
        btnObservasiKala1.setPreferredSize(new java.awt.Dimension(200, 90));
        btnObservasiKala1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObservasiKala1ActionPerformed(evt);
            }
        });
        FormMenu.add(btnObservasiKala1);

        BtnPartograf.setForeground(new java.awt.Color(0, 0, 0));
        BtnPartograf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/partograph.png"))); // NOI18N
        BtnPartograf.setText("Partograf Persalinan");
        BtnPartograf.setIconTextGap(0);
        BtnPartograf.setName("BtnPartograf"); // NOI18N
        BtnPartograf.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnPartograf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPartografActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPartograf);

        btnRingkasanPulangRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnRingkasanPulangRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        btnRingkasanPulangRanap.setText("Ringkasan Pulang Ranap");
        btnRingkasanPulangRanap.setIconTextGap(0);
        btnRingkasanPulangRanap.setName("btnRingkasanPulangRanap"); // NOI18N
        btnRingkasanPulangRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRingkasanPulangRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRingkasanPulangRanapActionPerformed(evt);
            }
        });
        FormMenu.add(btnRingkasanPulangRanap);

        btnAsesmenPraSedasi.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenPraSedasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/anastesi2.png"))); // NOI18N
        btnAsesmenPraSedasi.setText("Asesmen Pra Sedasi");
        btnAsesmenPraSedasi.setIconTextGap(0);
        btnAsesmenPraSedasi.setName("btnAsesmenPraSedasi"); // NOI18N
        btnAsesmenPraSedasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenPraSedasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenPraSedasiActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenPraSedasi);

        btnAsesmenPreInduksi.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenPreInduksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/anastesi1.png"))); // NOI18N
        btnAsesmenPreInduksi.setText("Asesmen Pre Induksi");
        btnAsesmenPreInduksi.setIconTextGap(0);
        btnAsesmenPreInduksi.setName("btnAsesmenPreInduksi"); // NOI18N
        btnAsesmenPreInduksi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenPreInduksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenPreInduksiActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenPreInduksi);

        btnAsesmenMedikBedahRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenMedikBedahRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mp_viewer.png"))); // NOI18N
        btnAsesmenMedikBedahRanap.setText("Asesmen Medik Bedah Rawat Inap");
        btnAsesmenMedikBedahRanap.setIconTextGap(0);
        btnAsesmenMedikBedahRanap.setName("btnAsesmenMedikBedahRanap"); // NOI18N
        btnAsesmenMedikBedahRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenMedikBedahRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenMedikBedahRanapActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenMedikBedahRanap);

        btnAsesmenMedikDewasaRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenMedikDewasaRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mp_viewer.png"))); // NOI18N
        btnAsesmenMedikDewasaRanap.setText("Asesmen Medik Dewasa Rawat Inap");
        btnAsesmenMedikDewasaRanap.setIconTextGap(0);
        btnAsesmenMedikDewasaRanap.setName("btnAsesmenMedikDewasaRanap"); // NOI18N
        btnAsesmenMedikDewasaRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenMedikDewasaRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenMedikDewasaRanapActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenMedikDewasaRanap);

        btnAsesmenMedikPerinatologi.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenMedikPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mp_viewer.png"))); // NOI18N
        btnAsesmenMedikPerinatologi.setText("Asesmen Medik Perinatologi");
        btnAsesmenMedikPerinatologi.setIconTextGap(0);
        btnAsesmenMedikPerinatologi.setName("btnAsesmenMedikPerinatologi"); // NOI18N
        btnAsesmenMedikPerinatologi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenMedikPerinatologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenMedikPerinatologiActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenMedikPerinatologi);

        btnAsesmenKeperawatanPerinatologi.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenKeperawatanPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mp_viewer.png"))); // NOI18N
        btnAsesmenKeperawatanPerinatologi.setText("Asesmen Keperawatan Perinatologi");
        btnAsesmenKeperawatanPerinatologi.setIconTextGap(0);
        btnAsesmenKeperawatanPerinatologi.setName("btnAsesmenKeperawatanPerinatologi"); // NOI18N
        btnAsesmenKeperawatanPerinatologi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenKeperawatanPerinatologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenKeperawatanPerinatologiActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenKeperawatanPerinatologi);

        btnAsesmenMedikAnakRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenMedikAnakRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mp_viewer.png"))); // NOI18N
        btnAsesmenMedikAnakRanap.setText("Asesmen Medik Anak Rawat Inap");
        btnAsesmenMedikAnakRanap.setIconTextGap(0);
        btnAsesmenMedikAnakRanap.setName("btnAsesmenMedikAnakRanap"); // NOI18N
        btnAsesmenMedikAnakRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenMedikAnakRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenMedikAnakRanapActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenMedikAnakRanap);

        btnAsesmenRestrain.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenRestrain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mp_viewer.png"))); // NOI18N
        btnAsesmenRestrain.setText("Asesmen Restrain Rawat Inap");
        btnAsesmenRestrain.setIconTextGap(0);
        btnAsesmenRestrain.setName("btnAsesmenRestrain"); // NOI18N
        btnAsesmenRestrain.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenRestrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenRestrainActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenRestrain);

        btnPemantauanHarian24Jam.setForeground(new java.awt.Color(0, 0, 0));
        btnPemantauanHarian24Jam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20.png"))); // NOI18N
        btnPemantauanHarian24Jam.setText("Pemantauan Harian Pasien 24 Jam");
        btnPemantauanHarian24Jam.setIconTextGap(0);
        btnPemantauanHarian24Jam.setName("btnPemantauanHarian24Jam"); // NOI18N
        btnPemantauanHarian24Jam.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPemantauanHarian24Jam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemantauanHarian24JamActionPerformed(evt);
            }
        });
        FormMenu.add(btnPemantauanHarian24Jam);

        btnSerahTerimaBayiPulang.setForeground(new java.awt.Color(0, 0, 0));
        btnSerahTerimaBayiPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/baby-boy.png"))); // NOI18N
        btnSerahTerimaBayiPulang.setText("Serah Terima Bayi Pulang");
        btnSerahTerimaBayiPulang.setIconTextGap(0);
        btnSerahTerimaBayiPulang.setName("btnSerahTerimaBayiPulang"); // NOI18N
        btnSerahTerimaBayiPulang.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSerahTerimaBayiPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerahTerimaBayiPulangActionPerformed(evt);
            }
        });
        FormMenu.add(btnSerahTerimaBayiPulang);

        btnLembarBantuanPengamatanMenyusui.setForeground(new java.awt.Color(0, 0, 0));
        btnLembarBantuanPengamatanMenyusui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/ibu_menyusui.png"))); // NOI18N
        btnLembarBantuanPengamatanMenyusui.setText("Bantuan Pengamatan Menyusui");
        btnLembarBantuanPengamatanMenyusui.setIconTextGap(0);
        btnLembarBantuanPengamatanMenyusui.setName("btnLembarBantuanPengamatanMenyusui"); // NOI18N
        btnLembarBantuanPengamatanMenyusui.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLembarBantuanPengamatanMenyusui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLembarBantuanPengamatanMenyusuiActionPerformed(evt);
            }
        });
        FormMenu.add(btnLembarBantuanPengamatanMenyusui);

        btnSkorApgarDowneCapJariPerinatologi.setForeground(new java.awt.Color(0, 0, 0));
        btnSkorApgarDowneCapJariPerinatologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/score_icon.png"))); // NOI18N
        btnSkorApgarDowneCapJariPerinatologi.setText("<html><div style=\"text-align: center;\">Skor Apgar, Downe & Cap Jari<br><b>(Bayi Lahir di RS)</b></div></html>");
        btnSkorApgarDowneCapJariPerinatologi.setIconTextGap(0);
        btnSkorApgarDowneCapJariPerinatologi.setName("btnSkorApgarDowneCapJariPerinatologi"); // NOI18N
        btnSkorApgarDowneCapJariPerinatologi.setPreferredSize(new java.awt.Dimension(200, 105));
        btnSkorApgarDowneCapJariPerinatologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkorApgarDowneCapJariPerinatologiActionPerformed(evt);
            }
        });
        FormMenu.add(btnSkorApgarDowneCapJariPerinatologi);

        btnPersetujuanTindakan.setForeground(new java.awt.Color(0, 0, 0));
        btnPersetujuanTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_basket_8726.png"))); // NOI18N
        btnPersetujuanTindakan.setText("<html><div style=\"text-align: center;\">Persetujuan/Penolakan/<br>Penundaan Tindakan</div></html>");
        btnPersetujuanTindakan.setIconTextGap(0);
        btnPersetujuanTindakan.setName("btnPersetujuanTindakan"); // NOI18N
        btnPersetujuanTindakan.setPreferredSize(new java.awt.Dimension(200, 105));
        btnPersetujuanTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersetujuanTindakanActionPerformed(evt);
            }
        });
        FormMenu.add(btnPersetujuanTindakan);

        btnCatatanTindakanKeperawatan.setForeground(new java.awt.Color(0, 0, 0));
        btnCatatanTindakanKeperawatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist_pencil-o.png"))); // NOI18N
        btnCatatanTindakanKeperawatan.setText("Catatan Tindakan Keperawatan");
        btnCatatanTindakanKeperawatan.setIconTextGap(0);
        btnCatatanTindakanKeperawatan.setName("btnCatatanTindakanKeperawatan"); // NOI18N
        btnCatatanTindakanKeperawatan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCatatanTindakanKeperawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatatanTindakanKeperawatanActionPerformed(evt);
            }
        });
        FormMenu.add(btnCatatanTindakanKeperawatan);

        btnAsuhanGiziRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnAsuhanGiziRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        btnAsuhanGiziRanap.setText("Asuhan Gizi Rawat Inap");
        btnAsuhanGiziRanap.setIconTextGap(0);
        btnAsuhanGiziRanap.setName("btnAsuhanGiziRanap"); // NOI18N
        btnAsuhanGiziRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsuhanGiziRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsuhanGiziRanapActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsuhanGiziRanap);

        btnSkriningGiziUlang.setForeground(new java.awt.Color(0, 0, 0));
        btnSkriningGiziUlang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_order-history_49596.png"))); // NOI18N
        btnSkriningGiziUlang.setText("Skrining Gizi Ulang");
        btnSkriningGiziUlang.setIconTextGap(0);
        btnSkriningGiziUlang.setName("btnSkriningGiziUlang"); // NOI18N
        btnSkriningGiziUlang.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSkriningGiziUlang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkriningGiziUlangActionPerformed(evt);
            }
        });
        FormMenu.add(btnSkriningGiziUlang);

        btnAssesmenUlangGizi.setForeground(new java.awt.Color(0, 0, 0));
        btnAssesmenUlangGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_basket_8726.png"))); // NOI18N
        btnAssesmenUlangGizi.setText("Asesmen Ulang Gizi Rawat Inap");
        btnAssesmenUlangGizi.setIconTextGap(0);
        btnAssesmenUlangGizi.setName("btnAssesmenUlangGizi"); // NOI18N
        btnAssesmenUlangGizi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAssesmenUlangGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssesmenUlangGiziActionPerformed(evt);
            }
        });
        FormMenu.add(btnAssesmenUlangGizi);

        btnMonevAsuhanGizi.setForeground(new java.awt.Color(0, 0, 0));
        btnMonevAsuhanGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_order-history_49596.png"))); // NOI18N
        btnMonevAsuhanGizi.setText("Monitoring & Evaluasi Asuhan Gizi");
        btnMonevAsuhanGizi.setIconTextGap(0);
        btnMonevAsuhanGizi.setName("btnMonevAsuhanGizi"); // NOI18N
        btnMonevAsuhanGizi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMonevAsuhanGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonevAsuhanGiziActionPerformed(evt);
            }
        });
        FormMenu.add(btnMonevAsuhanGizi);

        btnMonitoringEWSDewasa.setForeground(new java.awt.Color(0, 0, 0));
        btnMonitoringEWSDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png"))); // NOI18N
        btnMonitoringEWSDewasa.setText("Monitoring EWS Dewasa");
        btnMonitoringEWSDewasa.setIconTextGap(0);
        btnMonitoringEWSDewasa.setName("btnMonitoringEWSDewasa"); // NOI18N
        btnMonitoringEWSDewasa.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMonitoringEWSDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonitoringEWSDewasaActionPerformed(evt);
            }
        });
        FormMenu.add(btnMonitoringEWSDewasa);

        btnMonitoringPediatricEWS.setForeground(new java.awt.Color(0, 0, 0));
        btnMonitoringPediatricEWS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png"))); // NOI18N
        btnMonitoringPediatricEWS.setText("Monitoring Pediatric EWS");
        btnMonitoringPediatricEWS.setIconTextGap(0);
        btnMonitoringPediatricEWS.setName("btnMonitoringPediatricEWS"); // NOI18N
        btnMonitoringPediatricEWS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMonitoringPediatricEWS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonitoringPediatricEWSActionPerformed(evt);
            }
        });
        FormMenu.add(btnMonitoringPediatricEWS);

        btnMonitoringEWSObsgyn.setForeground(new java.awt.Color(0, 0, 0));
        btnMonitoringEWSObsgyn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_dialog-warning_118940.png"))); // NOI18N
        btnMonitoringEWSObsgyn.setText("Monitoring EWS Obsgyn");
        btnMonitoringEWSObsgyn.setIconTextGap(0);
        btnMonitoringEWSObsgyn.setName("btnMonitoringEWSObsgyn"); // NOI18N
        btnMonitoringEWSObsgyn.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMonitoringEWSObsgyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonitoringEWSObsgynActionPerformed(evt);
            }
        });
        FormMenu.add(btnMonitoringEWSObsgyn);

        btnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        btnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360485642_edit-notes.png"))); // NOI18N
        btnNotepad.setText("Notepad SIMRS");
        btnNotepad.setIconTextGap(0);
        btnNotepad.setName("btnNotepad"); // NOI18N
        btnNotepad.setPreferredSize(new java.awt.Dimension(200, 90));
        btnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotepadActionPerformed(evt);
            }
        });
        FormMenu.add(btnNotepad);

        btnPengelolaanTransfusiDarah.setForeground(new java.awt.Color(0, 0, 0));
        btnPengelolaanTransfusiDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481001585_blood_drop.png"))); // NOI18N
        btnPengelolaanTransfusiDarah.setText("Pengelolaan Transfusi Darah");
        btnPengelolaanTransfusiDarah.setIconTextGap(0);
        btnPengelolaanTransfusiDarah.setName("btnPengelolaanTransfusiDarah"); // NOI18N
        btnPengelolaanTransfusiDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPengelolaanTransfusiDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengelolaanTransfusiDarahActionPerformed(evt);
            }
        });
        FormMenu.add(btnPengelolaanTransfusiDarah);

        btnAsesmenUlangResikoJatuhDewasa.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenUlangResikoJatuhDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/patient (1).png"))); // NOI18N
        btnAsesmenUlangResikoJatuhDewasa.setText("<html><div style=\"text-align: center;\">Asesmen Ulang Resiko<br>Jatuh Dewasa</div></html>");
        btnAsesmenUlangResikoJatuhDewasa.setIconTextGap(0);
        btnAsesmenUlangResikoJatuhDewasa.setName("btnAsesmenUlangResikoJatuhDewasa"); // NOI18N
        btnAsesmenUlangResikoJatuhDewasa.setPreferredSize(new java.awt.Dimension(200, 105));
        btnAsesmenUlangResikoJatuhDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenUlangResikoJatuhDewasaActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenUlangResikoJatuhDewasa);

        btnAsesmenUlangResikoJatuhAnak.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenUlangResikoJatuhAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/patient (1).png"))); // NOI18N
        btnAsesmenUlangResikoJatuhAnak.setText("<html><div style=\"text-align: center;\">Asesmen Ulang Resiko<br>Jatuh Anak</div></html>");
        btnAsesmenUlangResikoJatuhAnak.setIconTextGap(0);
        btnAsesmenUlangResikoJatuhAnak.setName("btnAsesmenUlangResikoJatuhAnak"); // NOI18N
        btnAsesmenUlangResikoJatuhAnak.setPreferredSize(new java.awt.Dimension(200, 105));
        btnAsesmenUlangResikoJatuhAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenUlangResikoJatuhAnakActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenUlangResikoJatuhAnak);

        btnProtokolKemoterapi.setForeground(new java.awt.Color(0, 0, 0));
        btnProtokolKemoterapi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_30-Doctor_5929214.png"))); // NOI18N
        btnProtokolKemoterapi.setText("Protokol Kemoterapi");
        btnProtokolKemoterapi.setIconTextGap(0);
        btnProtokolKemoterapi.setName("btnProtokolKemoterapi"); // NOI18N
        btnProtokolKemoterapi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnProtokolKemoterapi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProtokolKemoterapiActionPerformed(evt);
            }
        });
        FormMenu.add(btnProtokolKemoterapi);

        btnAsesmenAwalKebidanan.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenAwalKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pregnant_woman.png"))); // NOI18N
        btnAsesmenAwalKebidanan.setText("Asesmen Awal Kebidanan");
        btnAsesmenAwalKebidanan.setIconTextGap(0);
        btnAsesmenAwalKebidanan.setName("btnAsesmenAwalKebidanan"); // NOI18N
        btnAsesmenAwalKebidanan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenAwalKebidanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenAwalKebidananActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenAwalKebidanan);

        btnPenilaianAwalMedisRalanMata.setForeground(new java.awt.Color(0, 0, 0));
        btnPenilaianAwalMedisRalanMata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1459441_eye_holidays_anatomy_halloween_icon.png"))); // NOI18N
        btnPenilaianAwalMedisRalanMata.setText("Awal Medis Ralan Mata");
        btnPenilaianAwalMedisRalanMata.setIconTextGap(0);
        btnPenilaianAwalMedisRalanMata.setName("btnPenilaianAwalMedisRalanMata"); // NOI18N
        btnPenilaianAwalMedisRalanMata.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPenilaianAwalMedisRalanMata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenilaianAwalMedisRalanMataActionPerformed(evt);
            }
        });
        FormMenu.add(btnPenilaianAwalMedisRalanMata);

        btnAsesmenKeperawatanDewasa.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenKeperawatanDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_30-Doctor_5929214.png"))); // NOI18N
        btnAsesmenKeperawatanDewasa.setText("Asesmen Keperawatan Dewasa Ranap");
        btnAsesmenKeperawatanDewasa.setIconTextGap(0);
        btnAsesmenKeperawatanDewasa.setName("btnAsesmenKeperawatanDewasa"); // NOI18N
        btnAsesmenKeperawatanDewasa.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenKeperawatanDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenKeperawatanDewasaActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenKeperawatanDewasa);

        btnAsesmenKeperawatanAnak.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenKeperawatanAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_30-Doctor_5929214.png"))); // NOI18N
        btnAsesmenKeperawatanAnak.setText("Asesmen Keperawatan Anak Ranap");
        btnAsesmenKeperawatanAnak.setIconTextGap(0);
        btnAsesmenKeperawatanAnak.setName("btnAsesmenKeperawatanAnak"); // NOI18N
        btnAsesmenKeperawatanAnak.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenKeperawatanAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenKeperawatanAnakActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenKeperawatanAnak);

        btnAssesmenMedikIGD.setForeground(new java.awt.Color(0, 0, 0));
        btnAssesmenMedikIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_AMBULANCE-transport-health_care-transportation-urgency_6007988.png"))); // NOI18N
        btnAssesmenMedikIGD.setText("Assesmen Medik IGD");
        btnAssesmenMedikIGD.setIconTextGap(0);
        btnAssesmenMedikIGD.setName("btnAssesmenMedikIGD"); // NOI18N
        btnAssesmenMedikIGD.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAssesmenMedikIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssesmenMedikIGDActionPerformed(evt);
            }
        });
        FormMenu.add(btnAssesmenMedikIGD);

        btnAssesmenKeperawatanIGD.setForeground(new java.awt.Color(0, 0, 0));
        btnAssesmenKeperawatanIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_AMBULANCE-transport-health_care-transportation-urgency_6007988.png"))); // NOI18N
        btnAssesmenKeperawatanIGD.setText("Assesmen Keperawatan IGD");
        btnAssesmenKeperawatanIGD.setIconTextGap(0);
        btnAssesmenKeperawatanIGD.setName("btnAssesmenKeperawatanIGD"); // NOI18N
        btnAssesmenKeperawatanIGD.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAssesmenKeperawatanIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssesmenKeperawatanIGDActionPerformed(evt);
            }
        });
        FormMenu.add(btnAssesmenKeperawatanIGD);

        btnPenilaianAwalMedisRalanTHT.setForeground(new java.awt.Color(0, 0, 0));
        btnPenilaianAwalMedisRalanTHT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/5868953_coronavirus_covid-19_nose_secretion_snot_icon.png"))); // NOI18N
        btnPenilaianAwalMedisRalanTHT.setText("Penilaian Awal Medis Rawat Jalan THT");
        btnPenilaianAwalMedisRalanTHT.setIconTextGap(0);
        btnPenilaianAwalMedisRalanTHT.setName("btnPenilaianAwalMedisRalanTHT"); // NOI18N
        btnPenilaianAwalMedisRalanTHT.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPenilaianAwalMedisRalanTHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenilaianAwalMedisRalanTHTActionPerformed(evt);
            }
        });
        FormMenu.add(btnPenilaianAwalMedisRalanTHT);

        btnPenilaianAwalKeperawatanRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnPenilaianAwalKeperawatanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_basket_8726.png"))); // NOI18N
        btnPenilaianAwalKeperawatanRalan.setText("Penilaian Awal Keperawatan Ralan");
        btnPenilaianAwalKeperawatanRalan.setIconTextGap(0);
        btnPenilaianAwalKeperawatanRalan.setName("btnPenilaianAwalKeperawatanRalan"); // NOI18N
        btnPenilaianAwalKeperawatanRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPenilaianAwalKeperawatanRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenilaianAwalKeperawatanRalanActionPerformed(evt);
            }
        });
        FormMenu.add(btnPenilaianAwalKeperawatanRalan);

        btnPenilaianAwalKeperawatanRalanKemoterapi.setForeground(new java.awt.Color(0, 0, 0));
        btnPenilaianAwalKeperawatanRalanKemoterapi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_basket_8726.png"))); // NOI18N
        btnPenilaianAwalKeperawatanRalanKemoterapi.setText("Assesmen Keperawatan Kemoterapi Ralan");
        btnPenilaianAwalKeperawatanRalanKemoterapi.setIconTextGap(0);
        btnPenilaianAwalKeperawatanRalanKemoterapi.setName("btnPenilaianAwalKeperawatanRalanKemoterapi"); // NOI18N
        btnPenilaianAwalKeperawatanRalanKemoterapi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPenilaianAwalKeperawatanRalanKemoterapi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenilaianAwalKeperawatanRalanKemoterapiActionPerformed(evt);
            }
        });
        FormMenu.add(btnPenilaianAwalKeperawatanRalanKemoterapi);

        btnAsesmenMedikObstetriRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnAsesmenMedikObstetriRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pregnant_woman.png"))); // NOI18N
        btnAsesmenMedikObstetriRalan.setText("Asesmen Medik Obstetri Ralan");
        btnAsesmenMedikObstetriRalan.setIconTextGap(0);
        btnAsesmenMedikObstetriRalan.setName("btnAsesmenMedikObstetriRalan"); // NOI18N
        btnAsesmenMedikObstetriRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAsesmenMedikObstetriRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsesmenMedikObstetriRalanActionPerformed(evt);
            }
        });
        FormMenu.add(btnAsesmenMedikObstetriRalan);

        btnPenilaianAwalKeperawatanKebidananRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnPenilaianAwalKeperawatanKebidananRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pregnant_woman.png"))); // NOI18N
        btnPenilaianAwalKeperawatanKebidananRalan.setText("<html><div style=\"text-align: center;\">Penilaian Awal Keperawatan<br>Kebidanan Rawat Jalan</div></html>");
        btnPenilaianAwalKeperawatanKebidananRalan.setIconTextGap(0);
        btnPenilaianAwalKeperawatanKebidananRalan.setName("btnPenilaianAwalKeperawatanKebidananRalan"); // NOI18N
        btnPenilaianAwalKeperawatanKebidananRalan.setPreferredSize(new java.awt.Dimension(200, 105));
        btnPenilaianAwalKeperawatanKebidananRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenilaianAwalKeperawatanKebidananRalanActionPerformed(evt);
            }
        });
        FormMenu.add(btnPenilaianAwalKeperawatanKebidananRalan);

        btnTriasePediatrikIGD.setForeground(new java.awt.Color(0, 0, 0));
        btnTriasePediatrikIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pediatric_icon.png"))); // NOI18N
        btnTriasePediatrikIGD.setText("Triase Pediatrik IGD");
        btnTriasePediatrikIGD.setIconTextGap(0);
        btnTriasePediatrikIGD.setName("btnTriasePediatrikIGD"); // NOI18N
        btnTriasePediatrikIGD.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTriasePediatrikIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTriasePediatrikIGDActionPerformed(evt);
            }
        });
        FormMenu.add(btnTriasePediatrikIGD);

        btnDataTriaseIGD.setForeground(new java.awt.Color(0, 0, 0));
        btnDataTriaseIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_h2_19724.png"))); // NOI18N
        btnDataTriaseIGD.setText("Data Triase IGD");
        btnDataTriaseIGD.setIconTextGap(0);
        btnDataTriaseIGD.setName("btnDataTriaseIGD"); // NOI18N
        btnDataTriaseIGD.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataTriaseIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataTriaseIGDActionPerformed(evt);
            }
        });
        FormMenu.add(btnDataTriaseIGD);

        btnPenilaianTambahanGeriatri.setForeground(new java.awt.Color(0, 0, 0));
        btnPenilaianTambahanGeriatri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/6141440_boy_man_old_people_elderly and kid_icon.png"))); // NOI18N
        btnPenilaianTambahanGeriatri.setText("Penilaian Tambahan Pasien Geriatri");
        btnPenilaianTambahanGeriatri.setIconTextGap(0);
        btnPenilaianTambahanGeriatri.setName("btnPenilaianTambahanGeriatri"); // NOI18N
        btnPenilaianTambahanGeriatri.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPenilaianTambahanGeriatri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenilaianTambahanGeriatriActionPerformed(evt);
            }
        });
        FormMenu.add(btnPenilaianTambahanGeriatri);

        btnPenilaianAwalMedisRalanGeriatri.setForeground(new java.awt.Color(0, 0, 0));
        btnPenilaianAwalMedisRalanGeriatri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/5964799_ill_old man_patient_sick_sore throat_icon.png"))); // NOI18N
        btnPenilaianAwalMedisRalanGeriatri.setText("Awal Medis Ralan Geriatri");
        btnPenilaianAwalMedisRalanGeriatri.setIconTextGap(0);
        btnPenilaianAwalMedisRalanGeriatri.setName("btnPenilaianAwalMedisRalanGeriatri"); // NOI18N
        btnPenilaianAwalMedisRalanGeriatri.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPenilaianAwalMedisRalanGeriatri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenilaianAwalMedisRalanGeriatriActionPerformed(evt);
            }
        });
        FormMenu.add(btnPenilaianAwalMedisRalanGeriatri);

        BtnMasalahKeperawatanResikoHipotermia.setForeground(new java.awt.Color(0, 0, 0));
        BtnMasalahKeperawatanResikoHipotermia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMasalahKeperawatanResikoHipotermia.setText("MasKep Resiko Hipotermia");
        BtnMasalahKeperawatanResikoHipotermia.setIconTextGap(0);
        BtnMasalahKeperawatanResikoHipotermia.setName("BtnMasalahKeperawatanResikoHipotermia"); // NOI18N
        BtnMasalahKeperawatanResikoHipotermia.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMasalahKeperawatanResikoHipotermia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMasalahKeperawatanResikoHipotermiaActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMasalahKeperawatanResikoHipotermia);

        btnRingkasanPulangRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnRingkasanPulangRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        btnRingkasanPulangRalan.setText("Ringkasan Pulang Ralan");
        btnRingkasanPulangRalan.setIconTextGap(0);
        btnRingkasanPulangRalan.setName("btnRingkasanPulangRalan"); // NOI18N
        btnRingkasanPulangRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRingkasanPulangRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRingkasanPulangRalanActionPerformed(evt);
            }
        });
        FormMenu.add(btnRingkasanPulangRalan);

        BtnMasalahKeperawatanResikoHipovolemia.setForeground(new java.awt.Color(0, 0, 0));
        BtnMasalahKeperawatanResikoHipovolemia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMasalahKeperawatanResikoHipovolemia.setText("MasKep Resiko Hipovolemia");
        BtnMasalahKeperawatanResikoHipovolemia.setIconTextGap(0);
        BtnMasalahKeperawatanResikoHipovolemia.setName("BtnMasalahKeperawatanResikoHipovolemia"); // NOI18N
        BtnMasalahKeperawatanResikoHipovolemia.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMasalahKeperawatanResikoHipovolemia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMasalahKeperawatanResikoHipovolemiaActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMasalahKeperawatanResikoHipovolemia);

        BtnMasterICDOtopography.setForeground(new java.awt.Color(0, 0, 0));
        BtnMasterICDOtopography.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icd_O.png"))); // NOI18N
        BtnMasterICDOtopography.setText("Master ICD-O Topography");
        BtnMasterICDOtopography.setIconTextGap(0);
        BtnMasterICDOtopography.setName("BtnMasterICDOtopography"); // NOI18N
        BtnMasterICDOtopography.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMasterICDOtopography.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMasterICDOtopographyActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMasterICDOtopography);

        BtnMasterICDOmorphology.setForeground(new java.awt.Color(0, 0, 0));
        BtnMasterICDOmorphology.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icd_O.png"))); // NOI18N
        BtnMasterICDOmorphology.setText("Master ICD-O Morphology");
        BtnMasterICDOmorphology.setIconTextGap(0);
        BtnMasterICDOmorphology.setName("BtnMasterICDOmorphology"); // NOI18N
        BtnMasterICDOmorphology.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMasterICDOmorphology.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMasterICDOmorphologyActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMasterICDOmorphology);

        BtnRegisterCancer.setForeground(new java.awt.Color(0, 0, 0));
        BtnRegisterCancer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/cancer.png"))); // NOI18N
        BtnRegisterCancer.setText("Register Cancer (CanReg)");
        BtnRegisterCancer.setIconTextGap(0);
        BtnRegisterCancer.setName("BtnRegisterCancer"); // NOI18N
        BtnRegisterCancer.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnRegisterCancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegisterCancerActionPerformed(evt);
            }
        });
        FormMenu.add(BtnRegisterCancer);

        BtnDataCancer.setForeground(new java.awt.Color(0, 0, 0));
        BtnDataCancer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/sign-up.png"))); // NOI18N
        BtnDataCancer.setText("Data Cancer");
        BtnDataCancer.setIconTextGap(0);
        BtnDataCancer.setName("BtnDataCancer"); // NOI18N
        BtnDataCancer.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnDataCancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataCancerActionPerformed(evt);
            }
        });
        FormMenu.add(BtnDataCancer);

        BtnMaskepBersihanJalanNafas.setForeground(new java.awt.Color(0, 0, 0));
        BtnMaskepBersihanJalanNafas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMaskepBersihanJalanNafas.setText("<html><div style=\"text-align: center;\">MasKep Bersihan Jalan<br>Nafas Tidak Efektif</div></html>");
        BtnMaskepBersihanJalanNafas.setIconTextGap(0);
        BtnMaskepBersihanJalanNafas.setName("BtnMaskepBersihanJalanNafas"); // NOI18N
        BtnMaskepBersihanJalanNafas.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnMaskepBersihanJalanNafas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaskepBersihanJalanNafasActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMaskepBersihanJalanNafas);

        BtnMaskepKetidakstabilanGlukosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnMaskepKetidakstabilanGlukosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_folder_images_61610.png"))); // NOI18N
        BtnMaskepKetidakstabilanGlukosa.setText("MasKep Ketidakstabilan Glukosa Darah");
        BtnMaskepKetidakstabilanGlukosa.setIconTextGap(0);
        BtnMaskepKetidakstabilanGlukosa.setName("BtnMaskepKetidakstabilanGlukosa"); // NOI18N
        BtnMaskepKetidakstabilanGlukosa.setPreferredSize(new java.awt.Dimension(200, 90));
        BtnMaskepKetidakstabilanGlukosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMaskepKetidakstabilanGlukosaActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMaskepKetidakstabilanGlukosa);

        BtnSuratPernyataanDNR.setForeground(new java.awt.Color(0, 0, 0));
        BtnSuratPernyataanDNR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnSuratPernyataanDNR.setText("<html><div style=\"text-align: center;\">Surat Pernyataan DNR<br>(<i>Do Not Resuscitate</i>)</div></html>");
        BtnSuratPernyataanDNR.setIconTextGap(0);
        BtnSuratPernyataanDNR.setName("BtnSuratPernyataanDNR"); // NOI18N
        BtnSuratPernyataanDNR.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnSuratPernyataanDNR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuratPernyataanDNRActionPerformed(evt);
            }
        });
        FormMenu.add(BtnSuratPernyataanDNR);

        BtnInstruksiDokterDNR.setForeground(new java.awt.Color(0, 0, 0));
        BtnInstruksiDokterDNR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_TextEdit_37595.png"))); // NOI18N
        BtnInstruksiDokterDNR.setText("<html><div style=\"text-align: center;\">Instruksi Dokter Untuk DNR<br>(<i>Do Not Resuscitate</i>)</div></html>");
        BtnInstruksiDokterDNR.setIconTextGap(0);
        BtnInstruksiDokterDNR.setName("BtnInstruksiDokterDNR"); // NOI18N
        BtnInstruksiDokterDNR.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnInstruksiDokterDNR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInstruksiDokterDNRActionPerformed(evt);
            }
        });
        FormMenu.add(BtnInstruksiDokterDNR);

        BtnScoreApgarPerinatologiLuar.setForeground(new java.awt.Color(0, 0, 0));
        BtnScoreApgarPerinatologiLuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/score_icon1.png"))); // NOI18N
        BtnScoreApgarPerinatologiLuar.setText("<html><div style=\"text-align: center;\">Skor Apgar, Downe & Cap Jari<br><b>(Bayi Lahir Dari Luar RS)</b></div></html>");
        BtnScoreApgarPerinatologiLuar.setIconTextGap(0);
        BtnScoreApgarPerinatologiLuar.setName("BtnScoreApgarPerinatologiLuar"); // NOI18N
        BtnScoreApgarPerinatologiLuar.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnScoreApgarPerinatologiLuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnScoreApgarPerinatologiLuarActionPerformed(evt);
            }
        });
        FormMenu.add(BtnScoreApgarPerinatologiLuar);

        BtnAsesmenPasienTerminal.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsesmenPasienTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/medical_record.png"))); // NOI18N
        BtnAsesmenPasienTerminal.setText("<html><div style=\"text-align: center;\">Assesmen Pasien Terminal<br>Dan Keluarganya</div></html>");
        BtnAsesmenPasienTerminal.setIconTextGap(0);
        BtnAsesmenPasienTerminal.setName("BtnAsesmenPasienTerminal"); // NOI18N
        BtnAsesmenPasienTerminal.setPreferredSize(new java.awt.Dimension(200, 105));
        BtnAsesmenPasienTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsesmenPasienTerminalActionPerformed(evt);
            }
        });
        FormMenu.add(BtnAsesmenPasienTerminal);

        scrollMenu.setViewportView(FormMenu);

        internalFrame1.add(scrollMenu, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilkanMenu();
    }//GEN-LAST:event_formWindowOpened

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        akses.setCopyData5("");
        TCari.setText("");
        TCari.requestFocus();        
    }//GEN-LAST:event_btnClearActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        FormMenu.removeAll();

        isTampil();
        aturTampilanTombol();
        aturLayoutMenu();

        javax.swing.SwingUtilities.invokeLater(() -> {
            FormMenu.revalidate();
            FormMenu.repaint();
            scrollMenu.getViewport().revalidate();
            scrollMenu.revalidate();
            scrollMenu.repaint();
            scrollMenu.getVerticalScrollBar().setValue(0);
        });
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkInputActionPerformed(null);
            akses.setCopyData5(TCari.getText());
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void btnPersetujuanRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersetujuanRanapActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPersetujuanRawatInap form = new RMPersetujuanRawatInap(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPersetujuanRanap.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPersetujuanRanapActionPerformed

    private void btnGeneralConsentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralConsentActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMGeneralConsent form = new RMGeneralConsent(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnGeneralConsent.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnGeneralConsentActionPerformed

    private void btnSuratPernyataanRanapBpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuratPernyataanRanapBpjsActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSuratPenyataanRanapBPJS form = new RMSuratPenyataanRanapBPJS(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnSuratPernyataanRanapBpjs.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnSuratPernyataanRanapBpjsActionPerformed

    private void btnSuratPernyataanNaikKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuratPernyataanNaikKelasActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSuratPenyataanNaikKelas form = new RMSuratPenyataanNaikKelas(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnSuratPernyataanNaikKelas.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnSuratPernyataanNaikKelasActionPerformed

    private void btnSuratPernyataanBukanKLLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuratPernyataanBukanKLLActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSuratPenyataanBukanKLL form = new RMSuratPenyataanBukanKLL(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnSuratPernyataanBukanKLL.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnSuratPernyataanBukanKLLActionPerformed

    private void btnSuratPernyataanBayarDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuratPernyataanBayarDendaActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSuratPenyataanBayarDenda form = new RMSuratPenyataanBayarDenda(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnSuratPernyataanBayarDenda.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnSuratPernyataanBayarDendaActionPerformed

    private void btnSuratPernyataanNonBpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuratPernyataanNonBpjsActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSuratPenyataanRanapNonBPJS form = new RMSuratPenyataanRanapNonBPJS(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnSuratPernyataanNonBpjs.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnSuratPernyataanNonBpjsActionPerformed

    private void btnStatusKakiDiabetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusKakiDiabetesActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMStatusKakiDiabetes form = new RMStatusKakiDiabetes(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnStatusKakiDiabetes.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnStatusKakiDiabetesActionPerformed

    private void btnHistoryIpAddressPetugasRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryIpAddressPetugasRMActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgHistoriIPAddressPetugasERM aplikasi = new DlgHistoriIPAddressPetugasERM(formUtama, false);
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnHistoryIpAddressPetugasRM.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnHistoryIpAddressPetugasRMActionPerformed

    private void btnMasterCatatanMaterialOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterCatatanMaterialOperasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgMasterMaterialOperasi material = new DlgMasterMaterialOperasi(formUtama, false);
            material.isCek();
            material.emptTeks();
            material.awalData();
            formUtama.tampilkanDialogDiPanelUtama(material);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnMasterCatatanMaterialOperasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnMasterCatatanMaterialOperasiActionPerformed

    private void btnTriasePonekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTriasePonekActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMTriasePonek form = new RMTriasePonek(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnTriasePonek.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnTriasePonekActionPerformed

    private void BtnAsesmenMedikKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenMedikKebidananActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenMedikKebidanan aplikasi = new RMAsesmenMedikKebidanan(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnAsesmenMedikKebidanan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenMedikKebidananActionPerformed

    private void btnPemberianInformasiEdukasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemberianInformasiEdukasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPemberianInformasiEdukasi form = new RMPemberianInformasiEdukasi(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPemberianInformasiEdukasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPemberianInformasiEdukasiActionPerformed

    private void btnPerencanaanPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerencanaanPulangActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPerencanaanPulang aplikasi = new RMPerencanaanPulang(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPerencanaanPulang.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPerencanaanPulangActionPerformed

    private void BtnLaporanOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLaporanOperasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMLaporanOperasi aplikasi = new RMLaporanOperasi(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnLaporanOperasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnLaporanOperasiActionPerformed

    private void BtnCatatanRuangPemulihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanRuangPemulihanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMCatatanRuangPemulihan aplikasi = new RMCatatanRuangPemulihan(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnCatatanRuangPemulihan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCatatanRuangPemulihanActionPerformed

    private void BtnFormulirSiteMarkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFormulirSiteMarkingActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMFormulirSiteMarkingOperasi aplikasi = new RMFormulirSiteMarkingOperasi(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnFormulirSiteMarking.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnFormulirSiteMarkingActionPerformed

    private void BtnSerahTerimaPascaOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSerahTerimaPascaOperasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSerahTerimaPascaOperasi aplikasi = new RMSerahTerimaPascaOperasi(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnSerahTerimaPascaOperasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSerahTerimaPascaOperasiActionPerformed

    private void BtnInformasiTindakanPembiusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInformasiTindakanPembiusanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMInformasiTindakanPembiusan aplikasi = new RMInformasiTindakanPembiusan(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnInformasiTindakanPembiusan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnInformasiTindakanPembiusanActionPerformed

    private void BtnEvaluasiPraAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEvaluasiPraAnestesiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMEvaluasiPraAnestesi aplikasi = new RMEvaluasiPraAnestesi(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnEvaluasiPraAnestesi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnEvaluasiPraAnestesiActionPerformed

    private void BtnAsesmenPraSedasiKonsepIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenPraSedasiKonsepIARActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenPraSedasiKonsepIAR aplikasi = new RMAsesmenPraSedasiKonsepIAR(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnAsesmenPraSedasiKonsepIAR.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenPraSedasiKonsepIARActionPerformed

    private void BtnCatatanSedasiAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanSedasiAnestesiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMCatatanSedasiAnestesi aplikasi = new RMCatatanSedasiAnestesi(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnCatatanSedasiAnestesi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCatatanSedasiAnestesiActionPerformed

    private void btnAsesmenKeperawatanPerioperatifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenKeperawatanPerioperatifActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenKeperawatanPerioperatif aplikasi = new RMAsesmenKeperawatanPerioperatif(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenKeperawatanPerioperatif.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenKeperawatanPerioperatifActionPerformed

    private void btnCatatanMaterialOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatatanMaterialOperasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMCatatanPemakaianObatMaterialOperasi catatan = new RMCatatanPemakaianObatMaterialOperasi(formUtama, false);
            catatan.isCek();
            catatan.emptTeks();
            catatan.awalData();
            formUtama.tampilkanDialogDiPanelUtama(catatan);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnCatatanMaterialOperasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnCatatanMaterialOperasiActionPerformed

    private void btnCeklisPraOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCeklisPraOperasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMCeklisPraOperasi aplikasi = new RMCeklisPraOperasi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnCeklisPraOperasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnCeklisPraOperasiActionPerformed

    private void btnCeklisKesiapanAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCeklisKesiapanAnestesiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMCeklisKesiapanAnestesi aplikasi = new RMCeklisKesiapanAnestesi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnCeklisKesiapanAnestesi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnCeklisKesiapanAnestesiActionPerformed

    private void btnCeklisKeselamatanOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCeklisKeselamatanOperasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMCeklisKeselamatanOperasi aplikasi = new RMCeklisKeselamatanOperasi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnCeklisKeselamatanOperasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnCeklisKeselamatanOperasiActionPerformed

    private void btnTransferSerahTerimaPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferSerahTerimaPasienActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMTransferSerahTerimaIGD aplikasi = new RMTransferSerahTerimaIGD(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnTransferSerahTerimaPasien.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnTransferSerahTerimaPasienActionPerformed

    private void BtnMasalahKeperawatanNyeriAkutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMasalahKeperawatanNyeriAkutActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMasalahKeperawatanNyeriAkut aplikasi = new RMMasalahKeperawatanNyeriAkut(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnMasalahKeperawatanNyeriAkut.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMasalahKeperawatanNyeriAkutActionPerformed

    private void BtnMasalahKeperawatanPerfusiPeriferTdkEfektifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMasalahKeperawatanPerfusiPeriferTdkEfektifActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMasalahKeperawatanPerfusiPeriferTdkEfektif aplikasi = new RMMasalahKeperawatanPerfusiPeriferTdkEfektif(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMasalahKeperawatanPerfusiPeriferTdkEfektifActionPerformed

    private void btnCPPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPPTActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCPPT aplikasi = new DlgCPPT(formUtama, false);
            aplikasi.isCek();
            aplikasi.setUtama();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnCPPT.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnCPPTActionPerformed

    private void btnTransferPasienTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferPasienTindakanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPasienUntukTindakan aplikasi = new RMPasienUntukTindakan(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeksSebelum();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnTransferPasienTindakan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnTransferPasienTindakanActionPerformed

    private void btnLembarObservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLembarObservasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMLembarObservasi aplikasi = new RMLembarObservasi(formUtama, false);
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnLembarObservasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnLembarObservasiActionPerformed

    private void btnObservasiKala1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObservasiKala1ActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMObservasiKala1 aplikasi = new RMObservasiKala1(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnObservasiKala1.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnObservasiKala1ActionPerformed

    private void BtnPartografActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPartografActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPartografPersalinan aplikasi = new RMPartografPersalinan(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnPartograf.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPartografActionPerformed

    private void btnRingkasanPulangRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRingkasanPulangRanapActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRingkasanPulangRanap aplikasi = new DlgRingkasanPulangRanap(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnRingkasanPulangRanap.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnRingkasanPulangRanapActionPerformed

    private void btnAsesmenPraSedasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenPraSedasiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenPraSedasi aplikasi = new RMAsesmenPraSedasi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenPraSedasi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenPraSedasiActionPerformed

    private void btnAsesmenPreInduksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenPreInduksiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenPreInduksi aplikasi = new RMAsesmenPreInduksi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenPreInduksi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenPreInduksiActionPerformed

    private void btnAsesmenMedikBedahRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenMedikBedahRanapActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenMedikBedahRanap aplikasi = new RMAsesmenMedikBedahRanap(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenMedikBedahRanap.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenMedikBedahRanapActionPerformed

    private void btnAsesmenMedikDewasaRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenMedikDewasaRanapActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenMedikDewasaRanap aplikasi = new RMAsesmenMedikDewasaRanap(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenMedikDewasaRanap.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenMedikDewasaRanapActionPerformed

    private void btnAsesmenMedikPerinatologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenMedikPerinatologiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenMedikPerinatologi aplikasi = new RMAsesmenMedikPerinatologi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenMedikPerinatologi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenMedikPerinatologiActionPerformed

    private void btnAsesmenKeperawatanPerinatologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenKeperawatanPerinatologiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenKeperawatanPerinatologi aplikasi = new RMAsesmenKeperawatanPerinatologi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenKeperawatanPerinatologi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenKeperawatanPerinatologiActionPerformed

    private void btnAsesmenMedikAnakRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenMedikAnakRanapActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenMedikAnakRanap aplikasi = new RMAsesmenMedikAnakRanap(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenMedikAnakRanap.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenMedikAnakRanapActionPerformed

    private void btnAsesmenRestrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenRestrainActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
            RMAsesmenRestrain aplikasi = new RMAsesmenRestrain(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenRestrain.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenRestrainActionPerformed

    private void btnPemantauanHarian24JamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemantauanHarian24JamActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPemantauanHarian24Jam aplikasi = new RMPemantauanHarian24Jam(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPemantauanHarian24Jam.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPemantauanHarian24JamActionPerformed

    private void btnSerahTerimaBayiPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerahTerimaBayiPulangActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSerahTerimaBayiPulang aplikasi = new RMSerahTerimaBayiPulang(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnSerahTerimaBayiPulang.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnSerahTerimaBayiPulangActionPerformed

    private void btnLembarBantuanPengamatanMenyusuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLembarBantuanPengamatanMenyusuiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPengamatanMenyusui aplikasi = new RMPengamatanMenyusui(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnLembarBantuanPengamatanMenyusui.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnLembarBantuanPengamatanMenyusuiActionPerformed

    private void btnSkorApgarDowneCapJariPerinatologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkorApgarDowneCapJariPerinatologiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSkorApgarDowneCapPerinatologi aplikasi = new RMSkorApgarDowneCapPerinatologi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnSkorApgarDowneCapJariPerinatologi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnSkorApgarDowneCapJariPerinatologiActionPerformed

    private void btnPersetujuanTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersetujuanTindakanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMTindakanKedokteran tindakan = new RMTindakanKedokteran(formUtama, false);
            tindakan.emptTeks();
            tindakan.isCek();
            tindakan.awalData();
            formUtama.tampilkanDialogDiPanelUtama(tindakan);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPersetujuanTindakan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPersetujuanTindakanActionPerformed

    private void btnCatatanTindakanKeperawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatatanTindakanKeperawatanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCatatanTindakanKeperawatan aplikasi = new DlgCatatanTindakanKeperawatan(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnCatatanTindakanKeperawatan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnCatatanTindakanKeperawatanActionPerformed

    private void btnAsuhanGiziRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsuhanGiziRanapActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsuhanGiziRanap asesGizi = new RMAsuhanGiziRanap(formUtama, false);
            asesGizi.isCek();
            asesGizi.emptTeks();
            asesGizi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(asesGizi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsuhanGiziRanap.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsuhanGiziRanapActionPerformed

    private void btnSkriningGiziUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkriningGiziUlangActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSkriningUlangGizi gizi = new RMSkriningUlangGizi(formUtama, false);
            gizi.isCek();
            gizi.emptTeks();
            gizi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(gizi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnSkriningGiziUlang.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnSkriningGiziUlangActionPerformed

    private void btnAssesmenUlangGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssesmenUlangGiziActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgAssesmenGiziUlang asesGiziulang = new DlgAssesmenGiziUlang(formUtama, false);
            asesGiziulang.isCek();
            asesGiziulang.emptTeks();
            asesGiziulang.TCari.setText("");
            asesGiziulang.awalData();
            formUtama.tampilkanDialogDiPanelUtama(asesGiziulang);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAssesmenUlangGizi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAssesmenUlangGiziActionPerformed

    private void btnMonevAsuhanGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonevAsuhanGiziActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgMonevAsuhanGizi monevGZ = new DlgMonevAsuhanGizi(formUtama, false);
            monevGZ.isCek();
            monevGZ.emptTeks();
            monevGZ.TCari.setText("");
            monevGZ.awalData();
            formUtama.tampilkanDialogDiPanelUtama(monevGZ);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnMonevAsuhanGizi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnMonevAsuhanGiziActionPerformed

    private void btnMonitoringEWSDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitoringEWSDewasaActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMonitoringEWSDewasa aplikasi = new RMMonitoringEWSDewasa(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnMonitoringEWSDewasa.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnMonitoringEWSDewasaActionPerformed

    private void btnMonitoringPediatricEWSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitoringPediatricEWSActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMonitoringPEWSAnak aplikasi = new RMMonitoringPEWSAnak(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnMonitoringPediatricEWS.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnMonitoringPediatricEWSActionPerformed

    private void btnMonitoringEWSObsgynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitoringEWSObsgynActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMonitoringEWSObsgyn aplikasi = new RMMonitoringEWSObsgyn(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnMonitoringEWSObsgyn.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnMonitoringEWSObsgynActionPerformed

    private void btnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotepadActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgNotepad aplikasi = new DlgNotepad(formUtama, false);
            aplikasi.setData(akses.getkode());
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnNotepad.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnNotepadActionPerformed

    private void btnPengelolaanTransfusiDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengelolaanTransfusiDarahActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPengelolaanTransfusiDarah aplikasi = new RMPengelolaanTransfusiDarah(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPengelolaanTransfusiDarah.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPengelolaanTransfusiDarahActionPerformed

    private void btnAsesmenUlangResikoJatuhDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenUlangResikoJatuhDewasaActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenUlangResikoJatuhDewasa aplikasi = new RMAsesmenUlangResikoJatuhDewasa(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenUlangResikoJatuhDewasa.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenUlangResikoJatuhDewasaActionPerformed

    private void btnAsesmenUlangResikoJatuhAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenUlangResikoJatuhAnakActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenUlangResikoJatuhAnak aplikasi = new RMAsesmenUlangResikoJatuhAnak(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenUlangResikoJatuhAnak.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenUlangResikoJatuhAnakActionPerformed

    private void btnProtokolKemoterapiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProtokolKemoterapiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMProtokolKemoterapi aplikasi = new RMProtokolKemoterapi(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnProtokolKemoterapi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnProtokolKemoterapiActionPerformed

    private void btnAsesmenAwalKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenAwalKebidananActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenAwalKebidanan1 aplikasi = new RMAsesmenAwalKebidanan1(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenAwalKebidanan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenAwalKebidananActionPerformed

    private void btnPenilaianAwalMedisRalanMataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenilaianAwalMedisRalanMataActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalMedisRalanMata aplikasi = new RMPenilaianAwalMedisRalanMata(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPenilaianAwalMedisRalanMata.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPenilaianAwalMedisRalanMataActionPerformed

    private void btnAsesmenKeperawatanDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenKeperawatanDewasaActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenKeperawatanDewasaRanap aplikasi = new RMAsesmenKeperawatanDewasaRanap(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenKeperawatanDewasa.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenKeperawatanDewasaActionPerformed

    private void btnAsesmenKeperawatanAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenKeperawatanAnakActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenKeperawatanAnakRanap aplikasi = new RMAsesmenKeperawatanAnakRanap(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenKeperawatanAnak.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenKeperawatanAnakActionPerformed

    private void btnAssesmenMedikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssesmenMedikIGDActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalMedikIGD aplikasi = new RMPenilaianAwalMedikIGD(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAssesmenMedikIGD.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAssesmenMedikIGDActionPerformed

    private void btnAssesmenKeperawatanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssesmenKeperawatanIGDActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalKeperawatanIGDrz aplikasi = new RMPenilaianAwalKeperawatanIGDrz(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAssesmenKeperawatanIGD.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAssesmenKeperawatanIGDActionPerformed

    private void btnPenilaianAwalMedisRalanTHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenilaianAwalMedisRalanTHTActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalMedisRalanTHT aplikasi = new RMPenilaianAwalMedisRalanTHT(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPenilaianAwalMedisRalanTHT.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPenilaianAwalMedisRalanTHTActionPerformed

    private void btnPenilaianAwalKeperawatanRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenilaianAwalKeperawatanRalanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalKeperawatanRalan aplikasi = new RMPenilaianAwalKeperawatanRalan(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPenilaianAwalKeperawatanRalan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPenilaianAwalKeperawatanRalanActionPerformed

    private void btnPenilaianAwalKeperawatanRalanKemoterapiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenilaianAwalKeperawatanRalanKemoterapiActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalKeperawatanRalanKemoterapi aplikasi = new RMPenilaianAwalKeperawatanRalanKemoterapi(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPenilaianAwalKeperawatanRalanKemoterapi.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPenilaianAwalKeperawatanRalanKemoterapiActionPerformed

    private void btnAsesmenMedikObstetriRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsesmenMedikObstetriRalanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalMedikObstetriRalan aplikasi = new RMPenilaianAwalMedikObstetriRalan(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnAsesmenMedikObstetriRalan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnAsesmenMedikObstetriRalanActionPerformed

    private void btnPenilaianAwalKeperawatanKebidananRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenilaianAwalKeperawatanKebidananRalanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalKeperawatanKebidanan aplikasi = new RMPenilaianAwalKeperawatanKebidanan(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPenilaianAwalKeperawatanKebidananRalan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPenilaianAwalKeperawatanKebidananRalanActionPerformed

    private void btnTriasePediatrikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTriasePediatrikIGDActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMTriasePediatrik form = new RMTriasePediatrik(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnTriasePediatrikIGD.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnTriasePediatrikIGDActionPerformed

    private void btnDataTriaseIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataTriaseIGDActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMTriaseIGD form = new RMTriaseIGD(formUtama, false);
            form.isCek();
            form.emptTeks();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnDataTriaseIGD.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnDataTriaseIGDActionPerformed

    private void btnPenilaianTambahanGeriatriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenilaianTambahanGeriatriActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianTambahanGeriatri aplikasi = new RMPenilaianTambahanGeriatri(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPenilaianTambahanGeriatri.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPenilaianTambahanGeriatriActionPerformed

    private void btnPenilaianAwalMedisRalanGeriatriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenilaianAwalMedisRalanGeriatriActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalMedisRalanGeriatri aplikasi = new RMPenilaianAwalMedisRalanGeriatri(formUtama, false);
            aplikasi.isCek();
            aplikasi.emptTeks();
            aplikasi.setTampil();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnPenilaianAwalMedisRalanGeriatri.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnPenilaianAwalMedisRalanGeriatriActionPerformed

    private void BtnMasalahKeperawatanResikoHipotermiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMasalahKeperawatanResikoHipotermiaActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMasalahKeperawatanHipotermia aplikasi = new RMMasalahKeperawatanHipotermia(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnMasalahKeperawatanNyeriAkut.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMasalahKeperawatanResikoHipotermiaActionPerformed

    private void btnRingkasanPulangRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRingkasanPulangRalanActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRingkasanPulangRalan aplikasi = new DlgRingkasanPulangRalan(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + btnRingkasanPulangRalan.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnRingkasanPulangRalanActionPerformed

    private void BtnMasalahKeperawatanResikoHipovolemiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMasalahKeperawatanResikoHipovolemiaActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMasalahKeperawatanHipovolemia aplikasi = new RMMasalahKeperawatanHipovolemia(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnMasalahKeperawatanNyeriAkut.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMasalahKeperawatanResikoHipovolemiaActionPerformed

    private void BtnMasterICDOtopographyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMasterICDOtopographyActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgICDOncologyTopography aplikasi = new DlgICDOncologyTopography(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.ChkInput.setSelected(true);
            aplikasi.isForm();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnMasterICDOtopography.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMasterICDOtopographyActionPerformed

    private void BtnMasterICDOmorphologyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMasterICDOmorphologyActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgICDOncologyMorphology aplikasi = new DlgICDOncologyMorphology(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.ChkInput.setSelected(true);
            aplikasi.isForm();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnMasterICDOmorphology.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMasterICDOmorphologyActionPerformed

    private void BtnRegisterCancerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegisterCancerActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMRegisterCancer aplikasi = new RMRegisterCancer(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnRegisterCancer.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnRegisterCancerActionPerformed

    private void BtnDataCancerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataCancerActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgDataCancer aplikasi = new DlgDataCancer(formUtama, false);
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnDataCancer.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnDataCancerActionPerformed

    private void BtnMaskepBersihanJalanNafasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaskepBersihanJalanNafasActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMasalahKeperawatanBersihanJlnNafas aplikasi = new RMMasalahKeperawatanBersihanJlnNafas(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnMaskepBersihanJalanNafas.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMaskepBersihanJalanNafasActionPerformed

    private void BtnMaskepKetidakstabilanGlukosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMaskepKetidakstabilanGlukosaActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMMasalahKeperawatanKetidakstabilanGlukosaDarah aplikasi = new RMMasalahKeperawatanKetidakstabilanGlukosaDarah(formUtama, false);
            aplikasi.emptTeks();
            aplikasi.isCek();
            aplikasi.awalData();
            formUtama.tampilkanDialogDiPanelUtama(aplikasi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnMaskepKetidakstabilanGlukosa.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnMaskepKetidakstabilanGlukosaActionPerformed

    private void BtnSuratPernyataanDNRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuratPernyataanDNRActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSuratPernyataanDNR form = new RMSuratPernyataanDNR(formUtama, false);
            form.emptTeks();
            form.isCek();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnSuratPernyataanDNR.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnSuratPernyataanDNRActionPerformed

    private void BtnInstruksiDokterDNRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInstruksiDokterDNRActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMInstruksiDokterDNR form = new RMInstruksiDokterDNR(formUtama, false);
            form.emptTeks();
            form.isCek();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnInstruksiDokterDNR.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnInstruksiDokterDNRActionPerformed

    private void BtnScoreApgarPerinatologiLuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnScoreApgarPerinatologiLuarActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMSkorApgarDowneCapBayiLuarRS form = new RMSkorApgarDowneCapBayiLuarRS(formUtama, false);
            form.emptTeks();
            form.isCek();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnScoreApgarPerinatologiLuar.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnScoreApgarPerinatologiLuarActionPerformed

    private void BtnAsesmenPasienTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsesmenPasienTerminalActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMAsesmenPasienTerminal form = new RMAsesmenPasienTerminal(formUtama, false);
            form.emptTeks();
            form.isCek();
            form.awalData();
            formUtama.tampilkanDialogDiPanelUtama(form);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form " + BtnAsesmenPasienTerminal.getText() + ".\n" + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnAsesmenPasienTerminalActionPerformed

    /**
    * @param args the command line arguments
    */    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.ButtonBig BtnAsesmenMedikKebidanan;
    private widget.ButtonBig BtnAsesmenPasienTerminal;
    private widget.ButtonBig BtnAsesmenPraSedasiKonsepIAR;
    private widget.ButtonBig BtnCatatanRuangPemulihan;
    private widget.ButtonBig BtnCatatanSedasiAnestesi;
    private widget.ButtonBig BtnDataCancer;
    private widget.ButtonBig BtnEvaluasiPraAnestesi;
    private widget.ButtonBig BtnFormulirSiteMarking;
    private widget.ButtonBig BtnInformasiTindakanPembiusan;
    private widget.ButtonBig BtnInstruksiDokterDNR;
    private widget.ButtonBig BtnLaporanOperasi;
    private widget.ButtonBig BtnMasalahKeperawatanNyeriAkut;
    private widget.ButtonBig BtnMasalahKeperawatanPerfusiPeriferTdkEfektif;
    private widget.ButtonBig BtnMasalahKeperawatanResikoHipotermia;
    private widget.ButtonBig BtnMasalahKeperawatanResikoHipovolemia;
    private widget.ButtonBig BtnMaskepBersihanJalanNafas;
    private widget.ButtonBig BtnMaskepKetidakstabilanGlukosa;
    private widget.ButtonBig BtnMasterICDOmorphology;
    private widget.ButtonBig BtnMasterICDOtopography;
    private widget.ButtonBig BtnPartograf;
    private widget.ButtonBig BtnRegisterCancer;
    private widget.ButtonBig BtnScoreApgarPerinatologiLuar;
    private widget.ButtonBig BtnSerahTerimaPascaOperasi;
    private widget.ButtonBig BtnSuratPernyataanDNR;
    private widget.CekBox ChkInput;
    private widget.PanelBiasa FormMenu;
    public widget.TextBox TCari;
    private widget.ButtonBig btnAsesmenAwalKebidanan;
    private widget.ButtonBig btnAsesmenKeperawatanAnak;
    private widget.ButtonBig btnAsesmenKeperawatanDewasa;
    private widget.ButtonBig btnAsesmenKeperawatanPerinatologi;
    private widget.ButtonBig btnAsesmenKeperawatanPerioperatif;
    private widget.ButtonBig btnAsesmenMedikAnakRanap;
    private widget.ButtonBig btnAsesmenMedikBedahRanap;
    private widget.ButtonBig btnAsesmenMedikDewasaRanap;
    private widget.ButtonBig btnAsesmenMedikObstetriRalan;
    private widget.ButtonBig btnAsesmenMedikPerinatologi;
    private widget.ButtonBig btnAsesmenPraSedasi;
    private widget.ButtonBig btnAsesmenPreInduksi;
    private widget.ButtonBig btnAsesmenRestrain;
    private widget.ButtonBig btnAsesmenUlangResikoJatuhAnak;
    private widget.ButtonBig btnAsesmenUlangResikoJatuhDewasa;
    private widget.ButtonBig btnAssesmenKeperawatanIGD;
    private widget.ButtonBig btnAssesmenMedikIGD;
    private widget.ButtonBig btnAssesmenUlangGizi;
    private widget.ButtonBig btnAsuhanGiziRanap;
    private widget.ButtonBig btnCPPT;
    private widget.ButtonBig btnCatatanMaterialOperasi;
    private widget.ButtonBig btnCatatanTindakanKeperawatan;
    private widget.ButtonBig btnCeklisKeselamatanOperasi;
    private widget.ButtonBig btnCeklisKesiapanAnestesi;
    private widget.ButtonBig btnCeklisPraOperasi;
    private widget.Button btnClear;
    private widget.ButtonBig btnDataTriaseIGD;
    private widget.ButtonBig btnGeneralConsent;
    private widget.ButtonBig btnHistoryIpAddressPetugasRM;
    private widget.ButtonBig btnLembarBantuanPengamatanMenyusui;
    private widget.ButtonBig btnLembarObservasi;
    private widget.ButtonBig btnMasterCatatanMaterialOperasi;
    private widget.ButtonBig btnMonevAsuhanGizi;
    private widget.ButtonBig btnMonitoringEWSDewasa;
    private widget.ButtonBig btnMonitoringEWSObsgyn;
    private widget.ButtonBig btnMonitoringPediatricEWS;
    private widget.ButtonBig btnNotepad;
    private widget.ButtonBig btnObservasiKala1;
    private widget.ButtonBig btnPemantauanHarian24Jam;
    private widget.ButtonBig btnPemberianInformasiEdukasi;
    private widget.ButtonBig btnPengelolaanTransfusiDarah;
    private widget.ButtonBig btnPenilaianAwalKeperawatanKebidananRalan;
    private widget.ButtonBig btnPenilaianAwalKeperawatanRalan;
    private widget.ButtonBig btnPenilaianAwalKeperawatanRalanKemoterapi;
    private widget.ButtonBig btnPenilaianAwalMedisRalanGeriatri;
    private widget.ButtonBig btnPenilaianAwalMedisRalanMata;
    private widget.ButtonBig btnPenilaianAwalMedisRalanTHT;
    private widget.ButtonBig btnPenilaianTambahanGeriatri;
    private widget.ButtonBig btnPerencanaanPulang;
    private widget.ButtonBig btnPersetujuanRanap;
    private widget.ButtonBig btnPersetujuanTindakan;
    private widget.ButtonBig btnProtokolKemoterapi;
    private widget.ButtonBig btnRingkasanPulangRalan;
    private widget.ButtonBig btnRingkasanPulangRanap;
    private widget.ButtonBig btnSerahTerimaBayiPulang;
    private widget.ButtonBig btnSkorApgarDowneCapJariPerinatologi;
    private widget.ButtonBig btnSkriningGiziUlang;
    private widget.ButtonBig btnStatusKakiDiabetes;
    private widget.ButtonBig btnSuratPernyataanBayarDenda;
    private widget.ButtonBig btnSuratPernyataanBukanKLL;
    private widget.ButtonBig btnSuratPernyataanNaikKelas;
    private widget.ButtonBig btnSuratPernyataanNonBpjs;
    private widget.ButtonBig btnSuratPernyataanRanapBpjs;
    private widget.ButtonBig btnTransferPasienTindakan;
    private widget.ButtonBig btnTransferSerahTerimaPasien;
    private widget.ButtonBig btnTriasePediatrikIGD;
    private widget.ButtonBig btnTriasePonek;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel3;
    private widget.ScrollPane scrollMenu;
    // End of variables declaration//GEN-END:variables

    private void isTampil() {
        jmlmenu = 0;
        String cari = TCari.getText().toLowerCase().trim();

        if (akses.getbpjs_sep() && btnPersetujuanRanap.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPersetujuanRanap);
            jmlmenu++;
        }
        
        if (akses.getbpjs_sep() && btnGeneralConsent.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnGeneralConsent);
            jmlmenu++;
        }
        
        if (akses.getbpjs_sep() && btnSuratPernyataanRanapBpjs.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnSuratPernyataanRanapBpjs);
            jmlmenu++;
        }
        
        if (akses.getbpjs_sep() && btnSuratPernyataanNaikKelas.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnSuratPernyataanNaikKelas);
            jmlmenu++;
        }
        
        if (akses.getbpjs_sep() && btnSuratPernyataanBukanKLL.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnSuratPernyataanBukanKLL);
            jmlmenu++;
        }
        
        if (akses.getbpjs_sep() && btnSuratPernyataanBayarDenda.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnSuratPernyataanBayarDenda);
            jmlmenu++;
        }
        
        if (akses.getbpjs_sep() && btnSuratPernyataanNonBpjs.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnSuratPernyataanNonBpjs);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_keperawatan_ralan() && btnStatusKakiDiabetes.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnStatusKakiDiabetes);
            jmlmenu++;
        }
        
        if (akses.getadmin() && btnHistoryIpAddressPetugasRM.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnHistoryIpAddressPetugasRM);
            jmlmenu++;
        }
        
        if (akses.getadmin() && btnMasterCatatanMaterialOperasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnMasterCatatanMaterialOperasi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnTriasePonek.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnTriasePonek);
            jmlmenu++;
        }
        
        if (akses.getresep_dokter() && BtnAsesmenMedikKebidanan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnAsesmenMedikKebidanan);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnPemberianInformasiEdukasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPemberianInformasiEdukasi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnPerencanaanPulang.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPerencanaanPulang);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && BtnLaporanOperasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnLaporanOperasi);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && BtnCatatanRuangPemulihan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnCatatanRuangPemulihan);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && BtnFormulirSiteMarking.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnFormulirSiteMarking);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && BtnSerahTerimaPascaOperasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnSerahTerimaPascaOperasi);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && BtnInformasiTindakanPembiusan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnInformasiTindakanPembiusan);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && BtnEvaluasiPraAnestesi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnEvaluasiPraAnestesi);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && BtnAsesmenPraSedasiKonsepIAR.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnAsesmenPraSedasiKonsepIAR);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && BtnCatatanSedasiAnestesi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnCatatanSedasiAnestesi);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && btnAsesmenKeperawatanPerioperatif.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenKeperawatanPerioperatif);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && btnCatatanMaterialOperasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnCatatanMaterialOperasi);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && btnCeklisPraOperasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnCeklisPraOperasi);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && btnCeklisKesiapanAnestesi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnCeklisKesiapanAnestesi);
            jmlmenu++;
        }
        
        if (akses.getkegiatan_operasi() && btnCeklisKeselamatanOperasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnCeklisKeselamatanOperasi);
            jmlmenu++;
        }
        
        if (akses.getpemberian_obat() && btnTransferSerahTerimaPasien.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnTransferSerahTerimaPasien);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMasalahKeperawatanNyeriAkut.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnMasalahKeperawatanNyeriAkut);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMasalahKeperawatanPerfusiPeriferTdkEfektif.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnMasalahKeperawatanPerfusiPeriferTdkEfektif);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMasalahKeperawatanResikoHipotermia.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnMasalahKeperawatanResikoHipotermia);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMasalahKeperawatanResikoHipovolemia.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnMasalahKeperawatanResikoHipovolemia);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnCPPT.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnCPPT);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnTransferPasienTindakan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnTransferPasienTindakan);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnLembarObservasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnLembarObservasi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnObservasiKala1.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnObservasiKala1);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnPartograf.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnPartograf);
            jmlmenu++;
        }
        
        if (akses.getringkasanpulangranap() && btnRingkasanPulangRanap.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnRingkasanPulangRanap);
            jmlmenu++;
        }
        
        if (akses.getringkasanpulangranap() && btnRingkasanPulangRalan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnRingkasanPulangRalan);
            jmlmenu++;
        }
        
        if (akses.getresep_dokter() && btnAsesmenPraSedasi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenPraSedasi);
            jmlmenu++;
        }
        
        if (akses.getresep_dokter() && btnAsesmenPreInduksi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenPreInduksi);
            jmlmenu++;
        }
        
        if (akses.getasesmen_medik_bedah_ranap() && btnAsesmenMedikBedahRanap.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenMedikBedahRanap);
            jmlmenu++;
        }        
        
        if (akses.getasesmen_medik_dewasa_ranap() && btnAsesmenMedikDewasaRanap.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenMedikDewasaRanap);
            jmlmenu++;
        }
        
        if (akses.getasesmen_medik_anak_ranap() && btnAsesmenMedikPerinatologi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenMedikPerinatologi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnAsesmenKeperawatanPerinatologi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenKeperawatanPerinatologi);
            jmlmenu++;
        }
        
        if (akses.getasesmen_medik_anak_ranap() && btnAsesmenMedikAnakRanap.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenMedikAnakRanap);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnAsesmenRestrain.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenRestrain);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnPemantauanHarian24Jam.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPemantauanHarian24Jam);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnSerahTerimaBayiPulang.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnSerahTerimaBayiPulang);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnLembarBantuanPengamatanMenyusui.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnLembarBantuanPengamatanMenyusui);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnSkorApgarDowneCapJariPerinatologi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnSkorApgarDowneCapJariPerinatologi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnScoreApgarPerinatologiLuar.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnScoreApgarPerinatologiLuar);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnPersetujuanTindakan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPersetujuanTindakan);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnCatatanTindakanKeperawatan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnCatatanTindakanKeperawatan);
            jmlmenu++;
        }
        
        if (akses.getassesmen_gizi_harian() && btnAsuhanGiziRanap.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsuhanGiziRanap);
            jmlmenu++;
        }
        
        if (akses.getassesmen_gizi_harian() && btnSkriningGiziUlang.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnSkriningGiziUlang);
            jmlmenu++;
        }
        
        if (akses.getassesmen_gizi_harian() && btnAssesmenUlangGizi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAssesmenUlangGizi);
            jmlmenu++;
        }
        
        if (akses.getmonev_asuhan_gizi() && btnMonevAsuhanGizi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnMonevAsuhanGizi);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnMonitoringEWSDewasa.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnMonitoringEWSDewasa);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnMonitoringPediatricEWS.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnMonitoringPediatricEWS);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnMonitoringEWSObsgyn.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnMonitoringEWSObsgyn);
            jmlmenu++;
        }
        
        if (btnNotepad.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnNotepad);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnPengelolaanTransfusiDarah.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPengelolaanTransfusiDarah);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnAsesmenUlangResikoJatuhDewasa.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenUlangResikoJatuhDewasa);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnAsesmenUlangResikoJatuhAnak.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenUlangResikoJatuhAnak);
            jmlmenu++;
        }
        
        if (akses.getkemoterapi() && btnProtokolKemoterapi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnProtokolKemoterapi);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_keperawatan_kebidanan() && btnAsesmenAwalKebidanan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenAwalKebidanan);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_medis_ralan_mata() && btnPenilaianAwalMedisRalanMata.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPenilaianAwalMedisRalanMata);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnAsesmenKeperawatanDewasa.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenKeperawatanDewasa);
            jmlmenu++;
        }
        
        if (akses.getcppt() && btnAsesmenKeperawatanAnak.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenKeperawatanAnak);
            jmlmenu++;
        }
        
        if (akses.getresep_dokter() && btnAssesmenMedikIGD.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAssesmenMedikIGD);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_keperawatan_ralan() && btnAssesmenKeperawatanIGD.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAssesmenKeperawatanIGD);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_medis_ralan_tht() && btnPenilaianAwalMedisRalanTHT.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPenilaianAwalMedisRalanTHT);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_keperawatan_ralan() && btnPenilaianAwalKeperawatanRalan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPenilaianAwalKeperawatanRalan);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_keperawatan_ralan() && btnPenilaianAwalKeperawatanRalanKemoterapi.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPenilaianAwalKeperawatanRalanKemoterapi);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_medis_ralan_kebidanan() && btnAsesmenMedikObstetriRalan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnAsesmenMedikObstetriRalan);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_awal_keperawatan_kebidanan() && btnPenilaianAwalKeperawatanKebidananRalan.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPenilaianAwalKeperawatanKebidananRalan);
            jmlmenu++;
        }
        
        if (akses.getdata_triase_igd() && btnTriasePediatrikIGD.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnTriasePediatrikIGD);
            jmlmenu++;
        }
        
        if (akses.getdata_triase_igd() && btnDataTriaseIGD.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnDataTriaseIGD);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_pasien_geriatri() && btnPenilaianTambahanGeriatri.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPenilaianTambahanGeriatri);
            jmlmenu++;
        }
        
        if (akses.getpenilaian_pasien_geriatri() && btnPenilaianAwalMedisRalanGeriatri.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(btnPenilaianAwalMedisRalanGeriatri);
            jmlmenu++;
        }
        
        if (akses.getpenyakit() && BtnMasterICDOtopography.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnMasterICDOtopography);
            jmlmenu++;
        }
        
        if (akses.getpenyakit() && BtnMasterICDOmorphology.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnMasterICDOmorphology);
            jmlmenu++;
        }
        
        if (akses.getpenyakit() && BtnRegisterCancer.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnRegisterCancer);
            jmlmenu++;
        }
        
        if (akses.getpenyakit() && BtnDataCancer.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnDataCancer);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMaskepBersihanJalanNafas.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnMaskepBersihanJalanNafas);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnMaskepKetidakstabilanGlukosa.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnMaskepKetidakstabilanGlukosa);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnSuratPernyataanDNR.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnSuratPernyataanDNR);
            jmlmenu++;
        }
        
        if (akses.getresep_dokter() && BtnInstruksiDokterDNR.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnInstruksiDokterDNR);
            jmlmenu++;
        }
        
        if (akses.getcppt() && BtnAsesmenPasienTerminal.getText().toLowerCase().trim().contains(cari)) {
            FormMenu.add(BtnAsesmenPasienTerminal);
            jmlmenu++;
        }
    }
    
    public JPanel getFormMenu() {
        return FormMenu;
    }

    public javax.swing.JPanel getPanelUtama() {
        return internalFrame1;
    }

    public void tampilkanMenu() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ChkInputActionPerformed(null);

            javax.swing.Timer timer
                    = new javax.swing.Timer(100, e -> {

                        ((javax.swing.Timer) e.getSource()).stop();

                        ChkInputActionPerformed(null);

                        scrollMenu.getVerticalScrollBar().setValue(0);
                    });

            timer.setRepeats(false);
            timer.start();
        });
    }

    public void terapkanHakAkses() {
        FormMenu.removeAll();
        jmlmenu = 0;
        FormMenu.add(btnNotepad);
        jmlmenu++;
        
        if (akses.getpenyakit() == true) {
            FormMenu.add(BtnDataCancer);
            jmlmenu++;
        }
        
        if (akses.getpenyakit() == true) {
            FormMenu.add(BtnRegisterCancer);
            jmlmenu++;
        }
        
        if (akses.getpenyakit() == true) {
            FormMenu.add(BtnMasterICDOmorphology);
            jmlmenu++;
        }
        
        if (akses.getpenyakit() == true) {
            FormMenu.add(BtnMasterICDOtopography);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            FormMenu.add(btnPersetujuanRanap);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            FormMenu.add(btnGeneralConsent);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            FormMenu.add(btnSuratPernyataanRanapBpjs);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            FormMenu.add(btnSuratPernyataanNaikKelas);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            FormMenu.add(btnSuratPernyataanBukanKLL);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            FormMenu.add(btnSuratPernyataanBayarDenda);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            FormMenu.add(btnSuratPernyataanNonBpjs);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_keperawatan_ralan() == true) {
            FormMenu.add(btnStatusKakiDiabetes);
            jmlmenu++;
        }

        if (akses.getadmin() == true) {
            FormMenu.add(btnHistoryIpAddressPetugasRM);
            jmlmenu++;
        }

        if (akses.getadmin() == true) {
            FormMenu.add(btnMasterCatatanMaterialOperasi);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnTriasePonek);
            jmlmenu++;
        }
        
        if (akses.getcppt() == true) {
            FormMenu.add(BtnAsesmenPasienTerminal);
            jmlmenu++;
        }

        if (akses.getresep_dokter() == true) {
            FormMenu.add(BtnAsesmenMedikKebidanan);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnPemberianInformasiEdukasi);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnPerencanaanPulang);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(BtnLaporanOperasi);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(BtnCatatanRuangPemulihan);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(BtnFormulirSiteMarking);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(BtnSerahTerimaPascaOperasi);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(BtnInformasiTindakanPembiusan);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(BtnEvaluasiPraAnestesi);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(BtnAsesmenPraSedasiKonsepIAR);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(BtnCatatanSedasiAnestesi);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(btnAsesmenKeperawatanPerioperatif);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(btnCatatanMaterialOperasi);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(btnCeklisPraOperasi);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(btnCeklisKesiapanAnestesi);
            jmlmenu++;
        }

        if (akses.getkegiatan_operasi() == true) {
            FormMenu.add(btnCeklisKeselamatanOperasi);
            jmlmenu++;
        }

        if (akses.getpemberian_obat() == true) {
            FormMenu.add(btnTransferSerahTerimaPasien);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(BtnMasalahKeperawatanNyeriAkut);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(BtnMasalahKeperawatanPerfusiPeriferTdkEfektif);
            jmlmenu++;
        }
        
        if (akses.getcppt() == true) {
            FormMenu.add(BtnMasalahKeperawatanResikoHipotermia);
            jmlmenu++;
        }
        
        if (akses.getcppt() == true) {
            FormMenu.add(BtnMasalahKeperawatanResikoHipovolemia);
            jmlmenu++;
        }
        
        if (akses.getcppt() == true) {
            FormMenu.add(BtnMaskepBersihanJalanNafas);
            jmlmenu++;
        }
        
        if (akses.getcppt() == true) {
            FormMenu.add(BtnMaskepKetidakstabilanGlukosa);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnCPPT);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnTransferPasienTindakan);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnLembarObservasi);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnObservasiKala1);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(BtnPartograf);
            jmlmenu++;
        }

        if (akses.getringkasanpulangranap() == true) {
            FormMenu.add(btnRingkasanPulangRanap);
            jmlmenu++;
        }
        
        if (akses.getringkasanpulangranap() == true) {
            FormMenu.add(btnRingkasanPulangRalan);
            jmlmenu++;
        }

        if (akses.getresep_dokter() == true) {
            FormMenu.add(btnAsesmenPraSedasi);
            jmlmenu++;
        }

        if (akses.getresep_dokter() == true) {
            FormMenu.add(btnAsesmenPreInduksi);
            jmlmenu++;
        }

        if (akses.getasesmen_medik_bedah_ranap() == true) {
            FormMenu.add(btnAsesmenMedikBedahRanap);
            jmlmenu++;
        }

        if (akses.getasesmen_medik_dewasa_ranap() == true) {
            FormMenu.add(btnAsesmenMedikDewasaRanap);
            jmlmenu++;
        }

        if (akses.getasesmen_medik_anak_ranap() == true) {
            FormMenu.add(btnAsesmenMedikPerinatologi);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnAsesmenKeperawatanPerinatologi);
            jmlmenu++;
        }

        if (akses.getasesmen_medik_anak_ranap() == true) {
            FormMenu.add(btnAsesmenMedikAnakRanap);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnAsesmenRestrain);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnPemantauanHarian24Jam);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnSerahTerimaBayiPulang);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnLembarBantuanPengamatanMenyusui);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnSkorApgarDowneCapJariPerinatologi);
            jmlmenu++;
        }
        
        if (akses.getcppt() == true) {
            FormMenu.add(BtnScoreApgarPerinatologiLuar);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnPersetujuanTindakan);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnCatatanTindakanKeperawatan);
            jmlmenu++;
        }

        if (akses.getassesmen_gizi_harian() == true) {
            FormMenu.add(btnAsuhanGiziRanap);
            jmlmenu++;
        }

        if (akses.getassesmen_gizi_harian() == true) {
            FormMenu.add(btnSkriningGiziUlang);
            jmlmenu++;
        }

        if (akses.getassesmen_gizi_ulang() == true) {
            FormMenu.add(btnAssesmenUlangGizi);
            jmlmenu++;
        }

        if (akses.getmonev_asuhan_gizi() == true) {
            FormMenu.add(btnMonevAsuhanGizi);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnMonitoringEWSDewasa);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnMonitoringPediatricEWS);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnMonitoringEWSObsgyn);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnPengelolaanTransfusiDarah);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnAsesmenUlangResikoJatuhDewasa);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnAsesmenUlangResikoJatuhAnak);
            jmlmenu++;
        }

        if (akses.getkemoterapi() == true) {
            FormMenu.add(btnProtokolKemoterapi);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_keperawatan_kebidanan() == true) {
            FormMenu.add(btnAsesmenAwalKebidanan);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_medis_ralan_mata() == true) {
            FormMenu.add(btnPenilaianAwalMedisRalanMata);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnAsesmenKeperawatanDewasa);
            jmlmenu++;
        }

        if (akses.getcppt() == true) {
            FormMenu.add(btnAsesmenKeperawatanAnak);
            jmlmenu++;
        }

        if (akses.getresep_dokter() == true) {
            FormMenu.add(btnAssesmenMedikIGD);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_keperawatan_ralan() == true) {
            FormMenu.add(btnAssesmenKeperawatanIGD);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_medis_ralan_tht() == true) {
            FormMenu.add(btnPenilaianAwalMedisRalanTHT);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_keperawatan_ralan() == true) {
            FormMenu.add(btnPenilaianAwalKeperawatanRalan);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_keperawatan_ralan() == true) {
            FormMenu.add(btnPenilaianAwalKeperawatanRalanKemoterapi);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_medis_ralan_kebidanan() == true) {
            FormMenu.add(btnAsesmenMedikObstetriRalan);
            jmlmenu++;
        }

        if (akses.getpenilaian_awal_keperawatan_kebidanan() == true) {
            FormMenu.add(btnPenilaianAwalKeperawatanKebidananRalan);
            jmlmenu++;
        }
        
        if (akses.getdata_triase_igd() == true) {
            FormMenu.add(btnTriasePediatrikIGD);
            jmlmenu++;
        }

        if (akses.getdata_triase_igd() == true) {
            FormMenu.add(btnDataTriaseIGD);
            jmlmenu++;
        }

        if (akses.getpenilaian_pasien_geriatri() == true) {
            FormMenu.add(btnPenilaianTambahanGeriatri);
            jmlmenu++;
        }

        if (akses.getpenilaian_pasien_geriatri() == true) {
            FormMenu.add(btnPenilaianAwalMedisRalanGeriatri);
            jmlmenu++;
        }
        
        if (akses.getcppt()== true) {
            FormMenu.add(BtnSuratPernyataanDNR);
            jmlmenu++;
        }
        
        if (akses.getresep_dokter()== true) {
            FormMenu.add(BtnInstruksiDokterDNR);
            jmlmenu++;
        }

        aturLayoutMenu();

        FormMenu.revalidate();
        FormMenu.repaint();
    }
    
    private void aturLayoutMenu() {
        int lebarViewport = scrollMenu.getViewport().getExtentSize().width;
        int tinggiViewport = scrollMenu.getViewport().getExtentSize().height;

        if (lebarViewport <= 0) {
            lebarViewport = scrollMenu.getWidth();
        }

        if (tinggiViewport <= 0) {
            tinggiViewport = scrollMenu.getHeight();
        }

        if (lebarViewport <= 0) {
            lebarViewport = internalFrame1.getWidth();
        }

        if (tinggiViewport <= 0) {
            tinggiViewport
                    = internalFrame1.getHeight() - internalFrame2.getHeight();
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

        FormMenu.setLayout(new GridLayout(
                0,
                grid,
                jarakHorizontal,
                jarakVertikal
        ));

        Dimension ukuranMenu = new Dimension(
                Math.max(lebarViewport - 10, 200),
                tinggi
        );

        FormMenu.setPreferredSize(ukuranMenu);
        FormMenu.setMinimumSize(ukuranMenu);

        scrollMenu.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollMenu.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollMenu.getVerticalScrollBar().setUnitIncrement(16);
        scrollMenu.getVerticalScrollBar().setBlockIncrement(120);

        FormMenu.revalidate();
        FormMenu.repaint();

        scrollMenu.getViewport().revalidate();
        scrollMenu.getViewport().repaint();

        scrollMenu.revalidate();
        scrollMenu.repaint();
    }

    private void aturTampilanTombol() {
        java.awt.Component[] komponen = FormMenu.getComponents();

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
}
