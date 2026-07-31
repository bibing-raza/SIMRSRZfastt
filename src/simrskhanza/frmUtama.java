/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package simrskhanza;

import laporan.DlgDataHAIs;
import bridging.AplicareCekReferensiKamar;
import bridging.AplicareKetersediaanKamar;
import bridging.ApotekBPJSCekReferensiDPHO;
import bridging.ApotekBPJSCekReferensiFaskes;
import bridging.ApotekBPJSCekReferensiObat;
import bridging.ApotekBPJSCekReferensiPoli;
import bridging.ApotekBPJSCekReferensiSettingPPK;
import bridging.ApotekBPJSCekReferensiSpesialistik;
import bridging.ApotekBPJSDaftarPelayananObat2;
import bridging.ApotekBPJSKunjunganSEP;
import bridging.ApotekBPJSMapingObat;
import bridging.ApotekBPJSMonitoringKlaim;
import bridging.BPJSCekDataIndukKecelakaan;
import bridging.BPJSCekFingerPrin;
import bridging.BPJSCekKartu;
import bridging.BPJSCekKlaimJasaRaharja;
import bridging.BPJSCekNIK2;
import bridging.BPJSCekReferensiFaskes;
import bridging.BPJSCekReferensiPenyakit;
import bridging.BPJSCekReferensiPoli;
import bridging.BPJSCekNoRujukanPCare;
import bridging.BPJSCekNoRujukanRS;
import bridging.BPJSCekReferensiCaraKeluar;
import bridging.BPJSCekReferensiDiagnosaPRB;
import bridging.BPJSCekReferensiDokter;
import bridging.BPJSCekReferensiDokterDPJP;
import bridging.BPJSCekReferensiDokterHFIS;
import bridging.BPJSCekReferensiJadwalHFIS;
import bridging.BPJSCekReferensiKabupaten;
import bridging.BPJSCekReferensiKecamatan;
import bridging.BPJSCekReferensiKelasRawat;
import bridging.BPJSCekReferensiObatPRB;
import bridging.BPJSCekReferensiPascaPulang;
import bridging.BPJSCekReferensiPoliHFIS;
import bridging.BPJSCekReferensiPropinsi;
import bridging.BPJSCekReferensiProsedur;
import bridging.BPJSCekReferensiSpesialistik;
import bridging.BPJSCekReferensiRuangRawat;
import bridging.BPJSCekRiwayatRujukanPCare;
import bridging.BPJSCekRiwayatRujukanRS;
import bridging.BPJSCekRujukanKartuPCare;
import bridging.BPJSCekRujukanKartuRS;
import bridging.BPJSCekSEPInternal;
import bridging.BPJSCekSuplesiJasaRaharja;
import bridging.BPJSCekTanggalRujukan;
import bridging.BPJSDataNomorSuratKontrol;
import bridging.BPJSDataSEP;
import bridging.BPJSHistoriPelayanan;
import bridging.BPJSListSaranaRujukan;
import bridging.BPJSListSpesialistikRujukan;
import bridging.BPJSMonitoringKlaim;
import bridging.BPJSProgramPRB;
import bridging.BPJSRujukanKeluar;
import bridging.BPJSSuratKontrol;
import bridging.BPJSSPRI;
import bridging.CoronaDiagnosa;
import bridging.CoronaPasien;
import bridging.DlgDataKanker;
import bridging.DlgDataTB;
import bridging.INACBGCariCoderNIK;
import bridging.INACBGCoderNIK;
import bridging.PCareCekReferensiPenyakit;
import bridging.DlgSKDPBPJS;
import bridging.INACBGDaftarKlaim;
import bridging.INACBGPerawatanCorona;
import bridging.INACBGjknBelumDiklaim;
import bridging.KendaliMutuKendaliBiayaJKN;
import bridging.MobileJKNPembatalanPendaftaran;
import bridging.MobileJKNReferensiPendaftaran;
import bridging.PengajuanKlaimINACBGrz;
import bridging.SisruteCekReferensiAlasanRujuk;
import bridging.SisruteCekReferensiDiagnosa;
import bridging.SisruteCekReferensiFaskes;
import bridging.SisruteRujukanKeluar;
import bridging.SisruteRujukanMasukan;
import informasi.InformasiAnalisaKamin;
import laporan.DlgDkkSurveilansRalan;
import laporan.DlgFrekuensiPenyakitRanap;
import laporan.DlgFrekuensiPenyakitRalan;
import laporan.DlgDkkSurveilansRanap;
import laporan.DlgDkkPenyakitTidakMenularRalan;
import laporan.DlgDkkSurveilansPD3I;
import laporan.DlgJumlahMacamDiet;
import laporan.DlgJumlahPorsiDiet;
import laporan.DlgIndikatorNasionalMutu;
import setting.DlgUser;
import setting.DlgSetKamarInap;
import setting.DlgSetOtoLokasi;
import setting.DlgSetTarif;
import setting.DlgSetAplikasi;
import setting.DlgSetPenjabLab;
import setting.DlgSetOtoRalan;
import setting.DlgSetRM;
import setting.DlgSetHarga;
import setting.DlgBiayaSekaliMasuk;
import setting.DlgAdmin;
import setting.DlgBiayaHarian;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import informasi.InformasiJadwal;
import informasi.InformasiKamar;
import informasi.InformasiKamarInap;
import informasi.InformasiTarifLab;
import informasi.InformasiTarifOperasi;
import informasi.InformasiTarifRadiologi;
import informasi.InformasiTarifRalan;
import informasi.InformasiTarifRanap;
import informasi.InformasiTarifINACBG;
import informasi.InformasiTelusurKunjunganPasien;
import inventory.DlgCariPengambilanUTD;
import inventory.DlgCariPenjualan;
import inventory.DlgPenjualan;
import ipsrs.DlgCariPengambilanPenunjangUTD;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import keuangan.DlgAkunBayar;
import keuangan.DlgBayarPemesanan;
import keuangan.DlgBayarPiutang;
import keuangan.DlgBubes;
import keuangan.DlgCashflow;
import keuangan.DlgJnsPerawatanLab;
import keuangan.DlgJnsPerawatanOperasi;
import keuangan.DlgJnsPerawatanRadiologi;
import keuangan.DlgJnsPerawatanUTD;
import keuangan.DlgJurnal;
import keuangan.DlgJurnalHarian;
import keuangan.DlgLabaRugi;
import keuangan.DlgPemasukanLain;
import keuangan.DlgPengaturanRekening;
import keuangan.DlgPengeluaranHarian;
import keuangan.DlgPiutangBelumLunas;
import keuangan.DlgRekening;
import keuangan.DlgRekeningTahun;
import bridging.SatuSehatKirimClinicalImpression;
import bridging.SatuSehatKirimCondition;
import bridging.SatuSehatKirimDiet;
import bridging.SatuSehatKirimEncounter;
import bridging.SatuSehatKirimMedicationRequest;
import bridging.SatuSehatKirimMedicationDispense;
import bridging.SatuSehatKirimObservationTTV;
import bridging.SatuSehatKirimProcedure;
import bridging.SatuSehatKirimVaksin;
import bridging.SatuSehatMapingLokasi;
import bridging.SatuSehatMapingObatAlkes;
import bridging.SatuSehatMapingOrganisasi;
import bridging.SatuSehatMapingVaksin;
import bridging.SatuSehatReferensiPasien;
import bridging.SatuSehatReferensiPraktisi;
import fungsi.BackgroundMusic;
import grafikanalisa.GrafikDemografiRegistrasi;
import grafikanalisa.GrafikKunjunganPerBulan;
import grafikanalisa.GrafikKunjunganPerDokter;
import grafikanalisa.GrafikKunjunganPerPekerjaan;
import grafikanalisa.GrafikKunjunganPerPendidikan;
import grafikanalisa.GrafikKunjunganPerTahun;
import grafikanalisa.GrafikKunjunganPerTanggal;
import grafikanalisa.GrafikKunjunganPoli;
import grafikanalisa.GrafikStatusBatalRegPerBulan;
import grafikanalisa.GrafikStatusBatalRegPerTahun;
import grafikanalisa.GrafikStatusBatalRegPerTanggal;
import grafikanalisa.GrafikStatusRegPerBulan;
import grafikanalisa.GrafikStatusRegPerBulan2;
import grafikanalisa.GrafikStatusRegPerTahun;
import grafikanalisa.GrafikStatusRegPerTahun2;
import grafikanalisa.GrafikStatusRegPerTanggal;
import grafikanalisa.GrafikStatusRegPerTanggal2;
import informasi.InformasiKamarJenazah;
import informasi.InformasiTarifStokDarah;
import inventory.DlgDaftarPermintaanResep;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import keuangan.DlgAkunPiutang;
import keuangan.DlgHutangObatBelumLunas;
import keuangan.DlgJnsPerawatanRalan;
import keuangan.DlgJnsPerawatanRanap;
import keuangan.DlgKamar;
import keuangan.DlgPiutangPercaraBayar;
import keuangan.DlgRincianPiutangPasien;
import laporan.DlgBulananHAIs;
import laporan.DlgBulananHAIsRalan;
import laporan.DlgBulananHAIsRanap;
import laporan.DlgDiagnosaPenyakit;
import laporan.DlgDkkPenyakitMenularRalan;
import laporan.DlgDkkPenyakitMenularRanap;
import laporan.DlgDkkPenyakitTidakMenularRanap;
import laporan.DlgHarianHAIs;
import laporan.DlgHarianHAIsRalan;
import laporan.DlgHarianHAIsRanap;
import laporan.DlgICD9;
import laporan.DlgKunjunganRalan;
import laporan.DlgKunjunganRanap;
import laporan.DlgPelayananApotek;
import laporan.DlgPelayananRalan;
import laporan.DlgPenyakitPd3i;
import laporan.DlgRL4A;
import laporan.DlgRL4ASebab;
import laporan.DlgRL4B;
import laporan.DlgRL4BSebab;
import laporan.DlgRl32;
import laporan.DlgRl33;
import laporan.DlgRl34;
import laporan.DlgRl36;
import laporan.DlgRl37;
import laporan.DlgRl38;
import laporan.DlgSensusHarianPoli;
import permintaan.DlgCariPermintaanLab;
import permintaan.DlgCariPermintaanRadiologi;
import rekammedis.DlgInputKodeICD;
import rekammedis.DlgMasterDTD;
import rekammedis.DlgMasterDiagnosaGizi;
import rekammedis.DlgMasterIndikatorMutu;
import rekammedis.DlgMasterJenisDokumenJangMed;
import rekammedis.DlgMasterKeluhanPsikologis;
import rekammedis.DlgMasterNumdemonINM;
import rekammedis.DlgMasterRencanaTritmenPsikologi;
import rekammedis.DlgRekamPsikologisAnak;
import rekammedis.DlgRekamPsikologisDewasa;
import rekammedis.DlgRekamPsikologisPerkawinan;
import rekammedis.MasterDataDinkes;
import rekammedis.MasterMasalahKeperawatan;
import rekammedis.MasterFaktorResikoIGD;
import rekammedis.MasterResikoDecubitus;
import setting.DlgClosingKasir;
import setting.DlgSetBridging;
import setting.DlgSetEmbalase;
import setting.DlgSetHargaKamar;
import setting.DlgSetHargaObatRalan;
import setting.DlgSetHargaObatRanap;
import setting.DlgSetKeterlambatan;
import setting.DlgSetNota;
import tranfusidarah.UTDCariPenyerahanDarah;
import tranfusidarah.UTDDonor;
import tranfusidarah.UTDMedisRusak;
import tranfusidarah.UTDCekalDarah;
import tranfusidarah.UTDKomponenDarah;
import tranfusidarah.UTDPemisahanDarah;
import tranfusidarah.UTDPenunjangRusak;
import tranfusidarah.UTDPenyerahanDarah;
import tranfusidarah.UTDStokDarah;
import java.net.InetAddress;
import laporan.DlgLaporanIndikatorMutu;
import laporan.DlgQuerySql;
import setting.DlgHistoriLoginUser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import rekammedis.DlgMasterNomorDokumenRM;
import setting.PanelMenuUtamaA;
import setting.PanelMenuUtamaB;
import setting.PanelMenuUtamaC;
import setting.PanelMenuUtamaD;
import setting.PanelMenuUtamaE;
import setting.PanelMenuUtamaF;
import setting.PanelMenuUtamaG;
import setting.PanelMenuUtamaH;
import tranfusidarah.UTDPenyerahanDarahPasienDirawat;

/**
 *
 * @author perpustakaan
 */
public class frmUtama extends javax.swing.JFrame {

    private final Connection koneksi = koneksiDB.condb();
    private final sekuel Sequel = new sekuel();
    private final validasi Valid = new validasi();
    private static frmUtama myInstance;
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private final Properties prop = new Properties();
    private int jmlmenu = 0, grid = 0, tinggi = 0, i = 0;
    private String coder_nik = "", pilihpage = "", judulform = "", host = "", cek = "", cekApt = "", ipKomputer = "", nipLogin = "",
            jamnya = "", menitnya = "", detiknya = "", sttsFileSIMRS = "";
    private final DlgKasirRalan kasirralan = new DlgKasirRalan(this, false);
    private final DlgKamarInap kamarinap = new DlgKamarInap(null, false);
    private final DlgIGD igd = new DlgIGD(this, false);
    private PanelMenuUtamaA menuUtamaA;
    private PanelMenuUtamaB menuUtamaB;
    private PanelMenuUtamaC menuUtamaC;
    private PanelMenuUtamaD menuUtamaD;
    private PanelMenuUtamaE menuUtamaE;
    private PanelMenuUtamaF menuUtamaF;
    private PanelMenuUtamaG menuUtamaG;
    private PanelMenuUtamaH menuUtamaH;
    private BackgroundMusic music;
    private javax.swing.JDialog dialogAktifDiPanel;
    private java.awt.Container isiDialogAktifDiPanel;
    
    /**
     * Creates new form frmUtama
     */
    private frmUtama() {
        super();
        initComponents();        
        setIconImage(new ImageIcon(super.getClass().getResource("/picture/raza_icon.png")).getImage());

        this.setSize(screen.width, screen.height);
        //desktop.setPreferredSize(new Dimension(800,1000));
        //desktop.setAutoscrolls(true);
        edAdmin.setDocument(new batasInput((byte) 100).getKata(edAdmin));
        edPwd.setDocument(new batasInput((byte) 100).getKata(edPwd));
        PassLama.setDocument(new batasInput((byte) 100).getKata(PassLama));
        Passbaru1.setDocument(new batasInput((byte) 100).getKata(Passbaru1));
        PassBaru2.setDocument(new batasInput((byte) 100).getKata(PassBaru2));

        DlgLogin.setSize(344, 201);
        DlgLogin.setVisible(false);
        DlgLogin.setLocationRelativeTo(null);

        WindowInput.setSize(349, 180);
        WindowInput.setVisible(false);
        WindowInput.setLocationRelativeTo(null);

        INACBGCariCoderNIK cariNIK = new INACBGCariCoderNIK(this, false);
        
        lblTgl.setText("Tgl. : " + tanggal.getSelectedItem().toString());
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("Notif Setting : " + e);
        }

        FlayMenu.setVisible(false);
        TCari.setVisible(false);
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    isTampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    isTampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    isTampil();
                }
            });
        }
       
        try {
            if (koneksiDB.SIMRSDEVELOPMENT().equals("Ya")) {
                cekUpdateFileOtomatisDev();
            } else {
                cekUpdateFileOtomatis();
            }
        } catch (Exception e) {
            System.out.println("E : " + e);
            cekUpdateFileOtomatis();
        }
        
        cekApotek();
        cekNotifApotek();
        cekNotifLab();
        cekNotifRad();
        
        otomatisRefreshNotifApt();
        akses.tRefreshNotifApotek.start();
        otomatisRefreshNotifLab();
        akses.tRefreshNotifLab.start();
        otomatisRefreshNotifRad();
        akses.tRefreshNotifRad.start();  
        tampilIpAddress();
    }

    public static frmUtama getInstance() {
        if (myInstance == null) {
            myInstance = new frmUtama();
        }
        return myInstance;        
    }

    //private DlgMenu menu=new DlgMenu(this,false); 
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DlgLogin = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        internalFrame3 = new widget.InternalFrame();
        panelGlass1 = new usu.widget.glass.PanelGlass();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edPwd = new widget.PasswordBox();
        edAdmin = new widget.TextBox();
        jLabel6 = new javax.swing.JLabel();
        BtnLogin = new widget.Button();
        BtnCancel = new widget.Button();
        WindowInput = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        PassLama = new widget.TextBox();
        jLabel9 = new widget.Label();
        BtnClosePass = new widget.Button();
        BtnSimpanPass = new widget.Button();
        jLabel10 = new widget.Label();
        Passbaru1 = new widget.TextBox();
        jLabel12 = new widget.Label();
        PassBaru2 = new widget.TextBox();
        DlgHome = new javax.swing.JDialog();
        panelMenu = new widget.InternalFrame();
        panelisi2 = new widget.panelisi();
        label36 = new widget.Label();
        label35 = new widget.Label();
        cmbMenu = new widget.ComboBox();
        TCari = new widget.TextBox();
        button2 = new widget.Button();
        ChkInput = new widget.CekBox();
        button1 = new widget.Button();
        scrollPane2 = new widget.ScrollPane();
        Panelmenu = new widget.panelGlass();
        btnICD = new widget.ButtonBig();
        btnObatPenyakit = new widget.ButtonBig();
        btnKamar = new widget.ButtonBig();
        btnTindakanRalan = new widget.ButtonBig();
        btnPasien = new widget.ButtonBig();
        btnPasienMati = new widget.ButtonBig();
        btnAdmin = new widget.ButtonBig();
        btnUser = new widget.ButtonBig();
        btnAntrian = new widget.ButtonBig();
        btnSetupHarga = new widget.ButtonBig();
        btnCashFlow = new widget.ButtonBig();
        btnBubes = new widget.ButtonBig();
        btnPostingJurnal = new widget.ButtonBig();
        btnRekeningTahun = new widget.ButtonBig();
        btnRekening = new widget.ButtonBig();
        btnPenjualan = new widget.ButtonBig();
        btnBayarPiutang = new widget.ButtonBig();
        btnLabaRugi = new widget.ButtonBig();
        btnResume = new widget.ButtonBig();
        btnLahir = new widget.ButtonBig();
        btnSetBiayaHarian = new widget.ButtonBig();
        btnSetupAplikasi = new widget.ButtonBig();
        btnSetOtoRalan = new widget.ButtonBig();
        btnSetBiayaMasukSekali = new widget.ButtonBig();
        btnPaketOperasi = new widget.ButtonBig();
        btnFrekuensiRalan = new widget.ButtonBig();
        btnFrekuensiRanap = new widget.ButtonBig();
        btnSetupOtoLokasi = new widget.ButtonBig();
        btnTracker = new widget.ButtonBig();
        btnTindakanRanap = new widget.ButtonBig();
        btnSetupJamInap = new widget.ButtonBig();
        btnTarifLab = new widget.ButtonBig();
        btnSetPenjab = new widget.ButtonBig();
        btnSetupRM = new widget.ButtonBig();
        btnSetupTarif = new widget.ButtonBig();
        btnTarifRadiologi = new widget.ButtonBig();
        btnSetupEmbalase = new widget.ButtonBig();
        btnPengeluaran = new widget.ButtonBig();
        btnSetObatRalan = new widget.ButtonBig();
        btnSetObatRanap = new widget.ButtonBig();
        btnPenyakitPD3I = new widget.ButtonBig();
        btnSurveilansPD3I = new widget.ButtonBig();
        btnSurveilansRalan = new widget.ButtonBig();
        btnDiagnosa = new widget.ButtonBig();
        btnSurveilansRanap = new widget.ButtonBig();
        btnPnyTakMenularRanap = new widget.ButtonBig();
        btnPnyTakMenularRalan = new widget.ButtonBig();
        btnKunjunganRalan = new widget.ButtonBig();
        btnRl32 = new widget.ButtonBig();
        btnRl33 = new widget.ButtonBig();
        btnRl37 = new widget.ButtonBig();
        btnRl38 = new widget.ButtonBig();
        btnSetupNota = new widget.ButtonBig();
        btnRl34 = new widget.ButtonBig();
        btnRl36 = new widget.ButtonBig();
        btnakun_bayar = new widget.ButtonBig();
        btnbayar_pemesanan = new widget.ButtonBig();
        btnPemasukanLain = new widget.ButtonBig();
        btnPengaturanRekening = new widget.ButtonBig();
        btnClosingKasir = new widget.ButtonBig();
        btnKeterlambatanPresensi = new widget.ButtonBig();
        btnSetHargaKamar = new widget.ButtonBig();
        btnCekBPJSNik = new widget.ButtonBig();
        btnCekBPJSKartu = new widget.ButtonBig();
        btnKunjunganRanap = new widget.ButtonBig();
        btnCekBPJSNomorRujukanPCare = new widget.ButtonBig();
        btnICD9 = new widget.ButtonBig();
        btnJurnalHarian = new widget.ButtonBig();
        btnCekBPJSDiagnosa = new widget.ButtonBig();
        btnCekBPJSPoli = new widget.ButtonBig();
        btnPiutangBelumLunas = new widget.ButtonBig();
        btnCekBPJSFaskes = new widget.ButtonBig();
        btnBPJSSEP = new widget.ButtonBig();
        btnTarifUtd = new widget.ButtonBig();
        btnPengambilanUTD2 = new widget.ButtonBig();
        btnUTDMedisRusak = new widget.ButtonBig();
        btnPengambilanPenunjangUTD2 = new widget.ButtonBig();
        btnUTDPenunjangRusak = new widget.ButtonBig();
        btnUTDDonorDarah = new widget.ButtonBig();
        btnMonitoringKlaimBPJS = new widget.ButtonBig();
        btnUTDCekalDarah = new widget.ButtonBig();
        btnUTDKomponenDarah = new widget.ButtonBig();
        btnUTDStokDarah = new widget.ButtonBig();
        btnUTDPemisahanDarah = new widget.ButtonBig();
        btnRincianPiutangPasien = new widget.ButtonBig();
        btnUTDPenyerahanDarah = new widget.ButtonBig();
        btnHutangObat = new widget.ButtonBig();
        btnSensusHarianPoli = new widget.ButtonBig();
        btnRl4a = new widget.ButtonBig();
        btnAplicareReferensiKamar = new widget.ButtonBig();
        btnAplicareKetersediaanKamar = new widget.ButtonBig();
        btnInaCBGCoderNIK = new widget.ButtonBig();
        btnAkunPiutang = new widget.ButtonBig();
        btnPiutangPerCaraBayar = new widget.ButtonBig();
        btnLamaPelayananRalan = new widget.ButtonBig();
        btnCatatanPasien = new widget.ButtonBig();
        btnRl4b = new widget.ButtonBig();
        btnRl4asebab = new widget.ButtonBig();
        btnRl4bsebab = new widget.ButtonBig();
        btnDataHAIs = new widget.ButtonBig();
        btnHarianHAIsRS = new widget.ButtonBig();
        btnBulananHAIsRS = new widget.ButtonBig();
        btnPerusahaan = new widget.ButtonBig();
        btnLamaPelayananApotek = new widget.ButtonBig();
        btnGrafikKunjunganPoli = new widget.ButtonBig();
        btnGrafikKunjunganPerDokter = new widget.ButtonBig();
        btnGrafikKunjunganPerPekerjaan = new widget.ButtonBig();
        btnGrafikKunjunganPerPendidikan = new widget.ButtonBig();
        btnGrafikKunjunganPerTahun = new widget.ButtonBig();
        btnPnyMenularRanap = new widget.ButtonBig();
        btnPnyMenularRalan = new widget.ButtonBig();
        btnGrafikKunjunganPerBulan = new widget.ButtonBig();
        btnGrafikKunjunganPerTanggal = new widget.ButtonBig();
        btnGrafikDemografiRegistrasi = new widget.ButtonBig();
        btnGrafikStatusRegPerTahun = new widget.ButtonBig();
        btnGrafikStatusRegPerTahun2 = new widget.ButtonBig();
        btnGrafikStatusRegPerBulan = new widget.ButtonBig();
        btnGrafikStatusRegPerBulan2 = new widget.ButtonBig();
        btnGrafikStatusRegPerTanggal = new widget.ButtonBig();
        btnGrafikStatusRegPerTanggal2 = new widget.ButtonBig();
        btnGrafikStatusRegBatalPerTahun = new widget.ButtonBig();
        btnGrafikStatusRegBatalPerBulan = new widget.ButtonBig();
        btnCekPCareDiagnosa = new widget.ButtonBig();
        btnGrafikStatusRegBatalPerTanggal = new widget.ButtonBig();
        btnSKDPbpjs = new widget.ButtonBig();
        btnRujukKeluarVclaim = new widget.ButtonBig();
        btnBPJScekRiwayatRujukanPcare = new widget.ButtonBig();
        btnCekBPJSRiwayatRujukanRS = new widget.ButtonBig();
        btnCekBPJSRujukanKartuRS = new widget.ButtonBig();
        btnCekBPJSTanggalRujukan = new widget.ButtonBig();
        btnCekBPJSNomorRujukanRS = new widget.ButtonBig();
        btnCekBPJSRujukanKartuPCare = new widget.ButtonBig();
        btnCekReferensiKelasRawatBPJS = new widget.ButtonBig();
        btnCekReferensiProsedurBPJS = new widget.ButtonBig();
        btnCekReferensiDokterDPJPBPJS = new widget.ButtonBig();
        btnCekReferensiDokterBPJS = new widget.ButtonBig();
        btnCekReferensiSpesialistikBPJS = new widget.ButtonBig();
        btnCekReferensiRuangRawatBPJS = new widget.ButtonBig();
        btnCekReferensiCaraKeluarBPJS = new widget.ButtonBig();
        btnCekReferensiPascaPulangBPJS = new widget.ButtonBig();
        btnCekReferensiPropinsiBPJS = new widget.ButtonBig();
        btnCekReferensiKabupatenBPJS = new widget.ButtonBig();
        btnCekReferensiKecamatanBPJS = new widget.ButtonBig();
        btnJumlahPorsiDiet = new widget.ButtonBig();
        btnJumlahMacamDiet = new widget.ButtonBig();
        btnMasterFaskes = new widget.ButtonBig();
        btnCekSisruteFaskes = new widget.ButtonBig();
        btnCekSisruteAlasanRujuk = new widget.ButtonBig();
        btnCekSisruteDiagnosa = new widget.ButtonBig();
        btnRujukanMasukSisrute = new widget.ButtonBig();
        btnRujukanKeluarSisrute = new widget.ButtonBig();
        btnPasienPonek = new widget.ButtonBig();
        btnHarianHAIsRanap = new widget.ButtonBig();
        btnHarianHAIsRalan = new widget.ButtonBig();
        btnBulananHAIsRanap = new widget.ButtonBig();
        btnBulananHAIsRalan = new widget.ButtonBig();
        btnMasterMasalahKeperawatan = new widget.ButtonBig();
        btnMasterCaraBayar = new widget.ButtonBig();
        btnDataPersalinan = new widget.ButtonBig();
        btnPasienCorona = new widget.ButtonBig();
        btnDiagnosaPasienCorona = new widget.ButtonBig();
        btnPerawatanPasienCorona = new widget.ButtonBig();
        btnRencanaKontrolBPJS = new widget.ButtonBig();
        btnBridgingEklaimINACBG = new widget.ButtonBig();
        btnPengajuanKlaimINACBGrz = new widget.ButtonBig();
        btnINACBGjknBelumDiklaim = new widget.ButtonBig();
        btnInputKodeICD = new widget.ButtonBig();
        btnKendaliMutuKendaliBiayaINACBG = new widget.ButtonBig();
        btnCekSEPInternalBPJS = new widget.ButtonBig();
        btnSPRIbpjsVclaim = new widget.ButtonBig();
        btnCekFingerPrinBPJS = new widget.ButtonBig();
        btnListSpesialistikRujukanBPJS = new widget.ButtonBig();
        btnListSaranaRujukanBPJS = new widget.ButtonBig();
        btnProgramPRBBPJS = new widget.ButtonBig();
        btnCekReferensiDiagnosaPRBBPJS = new widget.ButtonBig();
        btnCekReferensiObatPRBBPJS = new widget.ButtonBig();
        btnDataNomorSuratKontrolBPJS = new widget.ButtonBig();
        btnHistoriPelayananPesertaBPJS = new widget.ButtonBig();
        btnKlaimJaminanJasaRaharja = new widget.ButtonBig();
        btnDataSuplesiJasaRaharja = new widget.ButtonBig();
        btnDataSEPIndukKLLJasaRaharja = new widget.ButtonBig();
        btnCekReferensiPoliHFISBPJS = new widget.ButtonBig();
        btnCekReferensiJadwalHFISBPJS = new widget.ButtonBig();
        btnCekReferensiDokterHFISBPJS = new widget.ButtonBig();
        btnCekReferensiPendaftaranMobileJKNBPJS = new widget.ButtonBig();
        btnCekReferensiBatalDaftarMobileJKNBPJS = new widget.ButtonBig();
        btnKemenkesSITB = new widget.ButtonBig();
        btnMasterDTD = new widget.ButtonBig();
        btnIkhtisarPerawatanHIV = new widget.ButtonBig();
        btnKemenkesKanker = new widget.ButtonBig();
        btnSetingBridging = new widget.ButtonBig();
        btnRekamPsikologisDewasa = new widget.ButtonBig();
        btnMasterKeluhanPsikologis = new widget.ButtonBig();
        btnMasterRencanaTritmenPsikologis = new widget.ButtonBig();
        btnRekamPsikologisAnak = new widget.ButtonBig();
        btnRekamPsikologiPerkawinan = new widget.ButtonBig();
        btnMasterKasusPersalinanDinkes = new widget.ButtonBig();
        btnKasusPersalinanDinkes = new widget.ButtonBig();
        btnMasterFaktorResikoJatuh = new widget.ButtonBig();
        btnSpirometri = new widget.ButtonBig();
        btnDashboardeResepRanap = new widget.ButtonBig();
        btnMasterResikoDecubitus = new widget.ButtonBig();
        btnReferensiDokterSatuSehat = new widget.ButtonBig();
        btnReferensiPasienSatuSehat = new widget.ButtonBig();
        btnMapingOrganisasiSatuSehat = new widget.ButtonBig();
        btnMapingLokasiSatuSehat = new widget.ButtonBig();
        btnMapingVaksinSatuSehat = new widget.ButtonBig();
        btnKirimEncounterSatuSehat = new widget.ButtonBig();
        btnKirimConditionSatuSehat = new widget.ButtonBig();
        btnKirimObservationSatuSehat = new widget.ButtonBig();
        btnKirimProsedurSatuSehat = new widget.ButtonBig();
        btnKirimImunisasiSatuSehat = new widget.ButtonBig();
        btnKirimClinicalSatuSehat = new widget.ButtonBig();
        btnKirimDietSatuSehat = new widget.ButtonBig();
        btnMapingObatSatuSehat = new widget.ButtonBig();
        btnKirimMedicationRequestSatuSehat = new widget.ButtonBig();
        btnKirimMedicationDispenseSatuSehat = new widget.ButtonBig();
        btnMasterJenisDokumenJangMed = new widget.ButtonBig();
        btnMasterDiagnosaGizi = new widget.ButtonBig();
        btnMasterIndikatorMutuLayanan = new widget.ButtonBig();
        btnIndikatorNasionalMutu = new widget.ButtonBig();
        btnMasterNumdenom = new widget.ButtonBig();
        btnPasienBlackList = new widget.ButtonBig();
        btnHistoryLoginUser = new widget.ButtonBig();
        btnQuerySql = new widget.ButtonBig();
        btnLaporanIndikatorMutu = new widget.ButtonBig();
        btnBPJSMapingObatApotek = new widget.ButtonBig();
        btnBPJSReferensiObatDPHO = new widget.ButtonBig();
        btnBPJSReferensiPoliApotek = new widget.ButtonBig();
        btnBPJSReferensiFaskesApotek = new widget.ButtonBig();
        btnBPJSReferensiSpesilistikApotek = new widget.ButtonBig();
        btnBPJSReferensiSetingPPKApotek = new widget.ButtonBig();
        btnBPJSReferensiObatApotek = new widget.ButtonBig();
        btnBPJSPencarianSEPApotek = new widget.ButtonBig();
        btnBPJSMonitoringKlaimApotek = new widget.ButtonBig();
        btnBPJSDataTerkirimApotek = new widget.ButtonBig();
        btnUTDPenyerahanDarahDirawat = new widget.ButtonBig();
        btnMasterNomorDokumenRM = new widget.ButtonBig();
        btnPermintaanLab = new widget.ButtonBig();
        btnLaboratorium = new widget.ButtonBig();
        btnPermintaanRadiologi = new widget.ButtonBig();
        btnPeriksaRadiologi = new widget.ButtonBig();
        btnDashboardeResepRalan = new widget.ButtonBig();
        btnDaftarPermintaanResep = new widget.ButtonBig();
        tanggal = new widget.Tanggal();
        btnDataPenjualan = new widget.ButtonBig();
        btnInputPenjualan = new widget.ButtonBig();
        btnDataPenyerahanDarah = new widget.ButtonBig();
        internalFrame1 = new widget.InternalFrame();
        BtnMenu = new widget.ButtonBig();
        btnGantiPassword = new widget.ButtonBig();
        jSeparator4 = new javax.swing.JSeparator();
        BtnToolReg = new widget.ButtonBig();
        btnToolIGD = new widget.ButtonBig();
        jSeparator5 = new javax.swing.JSeparator();
        btnBridgingEklaim = new widget.ButtonBig();
        btnToolLab = new widget.ButtonBig();
        btnToolRad = new widget.ButtonBig();
        BtnToolJualObat = new widget.ButtonBig();
        BtnDasboard = new widget.ButtonBig();
        jSeparator9 = new javax.swing.JSeparator();
        BtnToolKamnap = new widget.ButtonBig();
        BtnToolKasir = new widget.ButtonBig();
        jSeparator7 = new javax.swing.JSeparator();
        BtnLog = new widget.ButtonBig();
        BtnClose = new widget.ButtonBig();
        internalFrame4 = new widget.InternalFrame();
        lblStts = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        kdUser = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblTgl = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        lblIPaddress = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        footer_lbl_update = new javax.swing.JLabel();
        Tversi = new javax.swing.JLabel();
        PanelUtama = new javax.swing.JPanel();
        scrollPane1 = new widget.ScrollPane();
        PanelWall = new usu.widget.glass.PanelGlass();
        panelJudul = new usu.widget.glass.PanelGlass();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_update = new javax.swing.JLabel();
        Scroll21 = new widget.ScrollPane();
        ket_update = new widget.TextArea();
        jLabel13 = new javax.swing.JLabel();
        FlayMenu = new usu.widget.glass.PanelGlass();
        MenuBar = new widget.MenuBar();
        jMenu1 = new javax.swing.JMenu();
        MnJadwalDokterRalan = new javax.swing.JMenuItem();
        MnPasienRanap = new javax.swing.JMenuItem();
        MnTelusurKunjungan = new javax.swing.JMenuItem();
        MnPasienMeninggal = new javax.swing.JMenuItem();
        MnPenggunaanKamarRanap = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MnTarifLab = new javax.swing.JMenuItem();
        MnTarifRad = new javax.swing.JMenuItem();
        MnTarifUpd = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MnTarifRalan = new javax.swing.JMenuItem();
        MnTarifRanap = new javax.swing.JMenuItem();
        MnTarifKamar = new javax.swing.JMenuItem();
        MnTarifOperasi = new javax.swing.JMenuItem();
        MnTarifINACBG = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        DlgLogin.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgLogin.setName("DlgLogin"); // NOI18N
        DlgLogin.setUndecorated(true);
        DlgLogin.setResizable(false);

        internalFrame2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 120, 40)));
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setWarnaAtas(new java.awt.Color(29, 188, 188));
        internalFrame2.setLayout(null);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Silahkan Anda Login ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaAtas(new java.awt.Color(29, 188, 188));
        internalFrame3.setLayout(null);

        panelGlass1.setBackground(java.awt.Color.lightGray);
        panelGlass1.setOpaqueImage(false);
        panelGlass1.setRound(false);
        panelGlass1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID Admin :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass1.add(jLabel4);
        jLabel4.setBounds(2, 12, 80, 23);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Password :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass1.add(jLabel5);
        jLabel5.setBounds(2, 40, 80, 23);

        edPwd.setForeground(new java.awt.Color(0, 0, 0));
        edPwd.setToolTipText("Silahkan masukkan password");
        edPwd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edPwd.setName("edPwd"); // NOI18N
        edPwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edPwdKeyPressed(evt);
            }
        });
        panelGlass1.add(edPwd);
        edPwd.setBounds(85, 40, 220, 23);

        edAdmin.setForeground(new java.awt.Color(0, 0, 0));
        edAdmin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        edAdmin.setName("edAdmin"); // NOI18N
        edAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edAdminKeyPressed(evt);
            }
        });
        panelGlass1.add(edAdmin);
        edAdmin.setBounds(85, 12, 220, 23);

        internalFrame3.add(panelGlass1);
        panelGlass1.setBounds(-1, 30, 342, 76);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/keys_security3.png"))); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        internalFrame3.add(jLabel6);
        jLabel6.setBounds(120, 5, 135, 145);

        BtnLogin.setForeground(new java.awt.Color(0, 0, 0));
        BtnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/lock.png"))); // NOI18N
        BtnLogin.setText("Log-in");
        BtnLogin.setToolTipText("Alt+Z");
        BtnLogin.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnLogin.setName("BtnLogin"); // NOI18N
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });
        internalFrame3.add(BtnLogin);
        BtnLogin.setBounds(12, 125, 105, 32);

        BtnCancel.setForeground(new java.awt.Color(0, 0, 0));
        BtnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnCancel.setText("Batal");
        BtnCancel.setToolTipText("Alt+Y");
        BtnCancel.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnCancel.setName("BtnCancel"); // NOI18N
        BtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });
        internalFrame3.add(BtnCancel);
        BtnCancel.setBounds(222, 125, 105, 32);

        internalFrame2.add(internalFrame3);
        internalFrame3.setBounds(2, 15, 340, 170);

        DlgLogin.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        WindowInput.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput.setModal(true);
        WindowInput.setName("WindowInput"); // NOI18N
        WindowInput.setUndecorated(true);
        WindowInput.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ubah Password ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(null);

        PassLama.setForeground(new java.awt.Color(0, 0, 0));
        PassLama.setHighlighter(null);
        PassLama.setName("PassLama"); // NOI18N
        internalFrame6.add(PassLama);
        PassLama.setBounds(128, 30, 190, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Password Lama :");
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        internalFrame6.add(jLabel9);
        jLabel9.setBounds(0, 30, 125, 23);

        BtnClosePass.setForeground(new java.awt.Color(0, 0, 0));
        BtnClosePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnClosePass.setText("Tutup");
        BtnClosePass.setToolTipText("Alt+2");
        BtnClosePass.setName("BtnClosePass"); // NOI18N
        BtnClosePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClosePassActionPerformed(evt);
            }
        });
        BtnClosePass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnClosePassKeyPressed(evt);
            }
        });
        internalFrame6.add(BtnClosePass);
        BtnClosePass.setBounds(230, 130, 100, 30);

        BtnSimpanPass.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanPass.setText("Simpan");
        BtnSimpanPass.setToolTipText("Alt+1");
        BtnSimpanPass.setName("BtnSimpanPass"); // NOI18N
        BtnSimpanPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPassActionPerformed(evt);
            }
        });
        BtnSimpanPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanPassKeyPressed(evt);
            }
        });
        internalFrame6.add(BtnSimpanPass);
        BtnSimpanPass.setBounds(20, 130, 100, 30);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Password Baru :");
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        internalFrame6.add(jLabel10);
        jLabel10.setBounds(0, 60, 125, 23);

        Passbaru1.setForeground(new java.awt.Color(0, 0, 0));
        Passbaru1.setHighlighter(null);
        Passbaru1.setName("Passbaru1"); // NOI18N
        internalFrame6.add(Passbaru1);
        Passbaru1.setBounds(128, 60, 190, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Password Baru :");
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame6.add(jLabel12);
        jLabel12.setBounds(0, 90, 125, 23);

        PassBaru2.setForeground(new java.awt.Color(0, 0, 0));
        PassBaru2.setHighlighter(null);
        PassBaru2.setName("PassBaru2"); // NOI18N
        internalFrame6.add(PassBaru2);
        PassBaru2.setBounds(128, 90, 190, 23);

        WindowInput.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        DlgHome.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgHome.setIconImage(null);
        DlgHome.setName("DlgHome"); // NOI18N
        DlgHome.setUndecorated(true);
        DlgHome.setResizable(false);

        panelMenu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 245, 225)), "::[ Menu Utama ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelMenu.setName("panelMenu"); // NOI18N
        panelMenu.setPreferredSize(new java.awt.Dimension(2412, 3653));
        panelMenu.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 245, 225)));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 39));
        panelisi2.setWarnaBawah(new java.awt.Color(252, 252, 248));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(1, 23));
        panelisi2.add(label36);

        label35.setForeground(new java.awt.Color(0, 0, 0));
        label35.setText("Tampilkan Menu :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi2.add(label35);

        cmbMenu.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[A] Registrasi, Tagihan Ranap & Ralan, Pelayanan & Billing Pasien", "[B] Input Data Rekam Medis Elektronik Pasien", "[C] Manajemen & Kepegawaian Rumah Sakit", "[D] Transaksi Inventory Obat, BHP Medis, Alat Kesehatan Pasien", "[E] Transaksi Inventory Barang Non Medis dan Penunjang ( Lab & RO )", "[F] Aset & Inventaris Barang Rumah Sakit", "[G] Surat Menyurat", "[H] Manajemen Keuangan Rumah Sakit", "[I] Olah Data Penyakit, Laporan DKK, Laporal RL & Laporan Internal Rumah Sakit", "[J] Tarif Pelayanan & Keuangan Rumah Sakit", "[K] Bridging SEP, Aplicare, PCare, INACBG, Kemenkes & Pihak Ke 3", "[L] Olah Data Pasien", "[M] Unit Pengelola Darah (UPD)", "[N] Analisa, Dashboard & Info Grafik", "[O] Pengaturan Program Aplikasi HMS" }));
        cmbMenu.setName("cmbMenu"); // NOI18N
        cmbMenu.setPreferredSize(new java.awt.Dimension(470, 23));
        cmbMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMenuItemStateChanged(evt);
            }
        });
        panelisi2.add(cmbMenu);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(470, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);

        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        button2.setGlassColor(new java.awt.Color(255, 255, 255));
        button2.setMinimumSize(new java.awt.Dimension(28, 23));
        button2.setName("button2"); // NOI18N
        button2.setPreferredSize(new java.awt.Dimension(30, 23));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        panelisi2.add(button2);

        ChkInput.setBorder(null);
        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setSelected(true);
        ChkInput.setText("Cari Menu");
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
        panelisi2.add(ChkInput);

        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        button1.setText("Keluar");
        button1.setToolTipText("Alt+K");
        button1.setGlassColor(new java.awt.Color(255, 255, 255));
        button1.setMinimumSize(new java.awt.Dimension(28, 23));
        button1.setName("button1"); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(85, 23));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panelisi2.add(button1);

        panelMenu.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 245, 225)));
        scrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setName("scrollPane2"); // NOI18N

        Panelmenu.setBorder(null);
        Panelmenu.setMinimumSize(new java.awt.Dimension(1975, 2826));
        Panelmenu.setName("Panelmenu"); // NOI18N
        Panelmenu.setLayout(new java.awt.GridLayout(0, 12));

        btnICD.setForeground(new java.awt.Color(0, 0, 0));
        btnICD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icd_10.png"))); // NOI18N
        btnICD.setText("Master ICD 10");
        btnICD.setIconTextGap(0);
        btnICD.setName("btnICD"); // NOI18N
        btnICD.setPreferredSize(new java.awt.Dimension(200, 90));
        btnICD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnICDActionPerformed(evt);
            }
        });
        Panelmenu.add(btnICD);

        btnObatPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        btnObatPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360484848_applications-science.png"))); // NOI18N
        btnObatPenyakit.setText("Obat Penyakit");
        btnObatPenyakit.setIconTextGap(0);
        btnObatPenyakit.setName("btnObatPenyakit"); // NOI18N
        btnObatPenyakit.setPreferredSize(new java.awt.Dimension(200, 90));
        btnObatPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObatPenyakitActionPerformed(evt);
            }
        });
        Panelmenu.add(btnObatPenyakit);

        btnKamar.setForeground(new java.awt.Color(0, 0, 0));
        btnKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/industry.png"))); // NOI18N
        btnKamar.setText("Kamar");
        btnKamar.setIconTextGap(0);
        btnKamar.setName("btnKamar"); // NOI18N
        btnKamar.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamarActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKamar);

        btnTindakanRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnTindakanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/plaster.png"))); // NOI18N
        btnTindakanRalan.setText("Tarif Ralan");
        btnTindakanRalan.setIconTextGap(0);
        btnTindakanRalan.setName("btnTindakanRalan"); // NOI18N
        btnTindakanRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTindakanRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakanRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnTindakanRalan);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/patient.png"))); // NOI18N
        btnPasien.setText("Pasien");
        btnPasien.setIconTextGap(0);
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPasien);

        btnPasienMati.setForeground(new java.awt.Color(0, 0, 0));
        btnPasienMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Ambulance.png"))); // NOI18N
        btnPasienMati.setText("Pasien Meninggal");
        btnPasienMati.setIconTextGap(0);
        btnPasienMati.setName("btnPasienMati"); // NOI18N
        btnPasienMati.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPasienMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienMatiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPasienMati);

        btnAdmin.setForeground(new java.awt.Color(0, 0, 0));
        btnAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/users.png"))); // NOI18N
        btnAdmin.setText("Set Admin/Petugas Khusus/Pejabat");
        btnAdmin.setIconTextGap(0);
        btnAdmin.setName("btnAdmin"); // NOI18N
        btnAdmin.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        Panelmenu.add(btnAdmin);

        btnUser.setForeground(new java.awt.Color(0, 0, 0));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360484978_application-pgp-signature.png"))); // NOI18N
        btnUser.setText("Set User");
        btnUser.setIconTextGap(0);
        btnUser.setName("btnUser"); // NOI18N
        btnUser.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUser);

        btnAntrian.setForeground(new java.awt.Color(0, 0, 0));
        btnAntrian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/demographic.png"))); // NOI18N
        btnAntrian.setText("Set Antrian");
        btnAntrian.setIconTextGap(0);
        btnAntrian.setName("btnAntrian"); // NOI18N
        btnAntrian.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAntrian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAntrianActionPerformed(evt);
            }
        });
        Panelmenu.add(btnAntrian);

        btnSetupHarga.setForeground(new java.awt.Color(0, 0, 0));
        btnSetupHarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360487093_price.png"))); // NOI18N
        btnSetupHarga.setText("Set Harga Obat");
        btnSetupHarga.setIconTextGap(0);
        btnSetupHarga.setName("btnSetupHarga"); // NOI18N
        btnSetupHarga.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetupHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupHargaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetupHarga);

        btnCashFlow.setForeground(new java.awt.Color(0, 0, 0));
        btnCashFlow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486845_23.png"))); // NOI18N
        btnCashFlow.setText("Cash Flow");
        btnCashFlow.setIconTextGap(0);
        btnCashFlow.setName("btnCashFlow"); // NOI18N
        btnCashFlow.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCashFlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashFlowActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCashFlow);

        btnBubes.setForeground(new java.awt.Color(0, 0, 0));
        btnBubes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/applications-office.png"))); // NOI18N
        btnBubes.setText("Buku Besar");
        btnBubes.setIconTextGap(0);
        btnBubes.setName("btnBubes"); // NOI18N
        btnBubes.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBubes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBubesActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBubes);

        btnPostingJurnal.setForeground(new java.awt.Color(0, 0, 0));
        btnPostingJurnal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360485642_edit-notes.png"))); // NOI18N
        btnPostingJurnal.setText("Posting Jurnal");
        btnPostingJurnal.setIconTextGap(0);
        btnPostingJurnal.setName("btnPostingJurnal"); // NOI18N
        btnPostingJurnal.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPostingJurnal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostingJurnalActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPostingJurnal);

        btnRekeningTahun.setForeground(new java.awt.Color(0, 0, 0));
        btnRekeningTahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/money_bag.png"))); // NOI18N
        btnRekeningTahun.setText("Rekening Tahun");
        btnRekeningTahun.setIconTextGap(0);
        btnRekeningTahun.setName("btnRekeningTahun"); // NOI18N
        btnRekeningTahun.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRekeningTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekeningTahunActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRekeningTahun);

        btnRekening.setForeground(new java.awt.Color(0, 0, 0));
        btnRekening.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kwrite.png"))); // NOI18N
        btnRekening.setText("Akun Rekening");
        btnRekening.setIconTextGap(0);
        btnRekening.setName("btnRekening"); // NOI18N
        btnRekening.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRekening.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekeningActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRekening);

        btnPenjualan.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/cashbox.png"))); // NOI18N
        btnPenjualan.setText("Penjualan Obat & BHP");
        btnPenjualan.setIconTextGap(0);
        btnPenjualan.setName("btnPenjualan"); // NOI18N
        btnPenjualan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjualanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPenjualan);

        btnBayarPiutang.setForeground(new java.awt.Color(0, 0, 0));
        btnBayarPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1404046811_money.png"))); // NOI18N
        btnBayarPiutang.setText("Bayar Piutang");
        btnBayarPiutang.setIconTextGap(0);
        btnBayarPiutang.setName("btnBayarPiutang"); // NOI18N
        btnBayarPiutang.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBayarPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarPiutangActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBayarPiutang);

        btnLabaRugi.setForeground(new java.awt.Color(0, 0, 0));
        btnLabaRugi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486822_20.png"))); // NOI18N
        btnLabaRugi.setText("Keuangan");
        btnLabaRugi.setIconTextGap(0);
        btnLabaRugi.setName("btnLabaRugi"); // NOI18N
        btnLabaRugi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLabaRugi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLabaRugiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnLabaRugi);

        btnResume.setForeground(new java.awt.Color(0, 0, 0));
        btnResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        btnResume.setText("Riwayat Perawatan");
        btnResume.setIconTextGap(0);
        btnResume.setName("btnResume"); // NOI18N
        btnResume.setPreferredSize(new java.awt.Dimension(200, 90));
        btnResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumeActionPerformed(evt);
            }
        });
        Panelmenu.add(btnResume);

        btnLahir.setForeground(new java.awt.Color(0, 0, 0));
        btnLahir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/baby-girl.png"))); // NOI18N
        btnLahir.setText("Kelahiran Bayi");
        btnLahir.setIconTextGap(0);
        btnLahir.setName("btnLahir"); // NOI18N
        btnLahir.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLahirActionPerformed(evt);
            }
        });
        Panelmenu.add(btnLahir);

        btnSetBiayaHarian.setForeground(new java.awt.Color(0, 0, 0));
        btnSetBiayaHarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486845_23.png"))); // NOI18N
        btnSetBiayaHarian.setText("Biaya Harian");
        btnSetBiayaHarian.setIconTextGap(0);
        btnSetBiayaHarian.setName("btnSetBiayaHarian"); // NOI18N
        btnSetBiayaHarian.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetBiayaHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetBiayaHarianActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetBiayaHarian);

        btnSetupAplikasi.setForeground(new java.awt.Color(0, 0, 0));
        btnSetupAplikasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/local_network.png"))); // NOI18N
        btnSetupAplikasi.setText("Set Aplikasi");
        btnSetupAplikasi.setIconTextGap(0);
        btnSetupAplikasi.setName("btnSetupAplikasi"); // NOI18N
        btnSetupAplikasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetupAplikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupAplikasiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetupAplikasi);

        btnSetOtoRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnSetOtoRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/stethoscope (1).png"))); // NOI18N
        btnSetOtoRalan.setText("Set Oto Ralan");
        btnSetOtoRalan.setIconTextGap(0);
        btnSetOtoRalan.setName("btnSetOtoRalan"); // NOI18N
        btnSetOtoRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetOtoRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetOtoRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetOtoRalan);

        btnSetBiayaMasukSekali.setForeground(new java.awt.Color(0, 0, 0));
        btnSetBiayaMasukSekali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486845_23.png"))); // NOI18N
        btnSetBiayaMasukSekali.setText("Biaya Masuk Sekali");
        btnSetBiayaMasukSekali.setIconTextGap(0);
        btnSetBiayaMasukSekali.setName("btnSetBiayaMasukSekali"); // NOI18N
        btnSetBiayaMasukSekali.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetBiayaMasukSekali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetBiayaMasukSekaliActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetBiayaMasukSekali);

        btnPaketOperasi.setForeground(new java.awt.Color(0, 0, 0));
        btnPaketOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360487111_stock_paste.png"))); // NOI18N
        btnPaketOperasi.setText("Tarif Operasi/VK");
        btnPaketOperasi.setIconTextGap(0);
        btnPaketOperasi.setName("btnPaketOperasi"); // NOI18N
        btnPaketOperasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPaketOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaketOperasiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPaketOperasi);

        btnFrekuensiRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnFrekuensiRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnFrekuensiRalan.setText("Frekuensi Penyakit Ralan");
        btnFrekuensiRalan.setIconTextGap(0);
        btnFrekuensiRalan.setName("btnFrekuensiRalan"); // NOI18N
        btnFrekuensiRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnFrekuensiRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrekuensiRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnFrekuensiRalan);

        btnFrekuensiRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnFrekuensiRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/applications-office.png"))); // NOI18N
        btnFrekuensiRanap.setText("Frekuensi Penyakit Ranap");
        btnFrekuensiRanap.setIconTextGap(0);
        btnFrekuensiRanap.setName("btnFrekuensiRanap"); // NOI18N
        btnFrekuensiRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnFrekuensiRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrekuensiRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnFrekuensiRanap);

        btnSetupOtoLokasi.setForeground(new java.awt.Color(0, 0, 0));
        btnSetupOtoLokasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/our_process_2.png"))); // NOI18N
        btnSetupOtoLokasi.setText("Set Oto Lokasi");
        btnSetupOtoLokasi.setIconTextGap(0);
        btnSetupOtoLokasi.setName("btnSetupOtoLokasi"); // NOI18N
        btnSetupOtoLokasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetupOtoLokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupOtoLokasiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetupOtoLokasi);

        btnTracker.setForeground(new java.awt.Color(0, 0, 0));
        btnTracker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/receptionist.png"))); // NOI18N
        btnTracker.setText("Tracker Login");
        btnTracker.setIconTextGap(0);
        btnTracker.setName("btnTracker"); // NOI18N
        btnTracker.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTracker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrackerActionPerformed(evt);
            }
        });
        Panelmenu.add(btnTracker);

        btnTindakanRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnTindakanRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/doctor (2).png"))); // NOI18N
        btnTindakanRanap.setText("Tarif Ranap");
        btnTindakanRanap.setIconTextGap(0);
        btnTindakanRanap.setName("btnTindakanRanap"); // NOI18N
        btnTindakanRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTindakanRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakanRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnTindakanRanap);

        btnSetupJamInap.setForeground(new java.awt.Color(0, 0, 0));
        btnSetupJamInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Time.png"))); // NOI18N
        btnSetupJamInap.setText("Set Kamar Inap");
        btnSetupJamInap.setIconTextGap(0);
        btnSetupJamInap.setName("btnSetupJamInap"); // NOI18N
        btnSetupJamInap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetupJamInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupJamInapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetupJamInap);

        btnTarifLab.setForeground(new java.awt.Color(0, 0, 0));
        btnTarifLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/address-book.png"))); // NOI18N
        btnTarifLab.setText("Tarif Lab");
        btnTarifLab.setIconTextGap(0);
        btnTarifLab.setName("btnTarifLab"); // NOI18N
        btnTarifLab.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTarifLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarifLabActionPerformed(evt);
            }
        });
        Panelmenu.add(btnTarifLab);

        btnSetPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnSetPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/user3.png"))); // NOI18N
        btnSetPenjab.setText("Set P.J. Unit Penunjang & Bridgingnya");
        btnSetPenjab.setIconTextGap(0);
        btnSetPenjab.setName("btnSetPenjab"); // NOI18N
        btnSetPenjab.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetPenjabActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetPenjab);

        btnSetupRM.setForeground(new java.awt.Color(0, 0, 0));
        btnSetupRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/patient (1).png"))); // NOI18N
        btnSetupRM.setText("Set Rekam Medis");
        btnSetupRM.setIconTextGap(0);
        btnSetupRM.setName("btnSetupRM"); // NOI18N
        btnSetupRM.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetupRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupRMActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetupRM);

        btnSetupTarif.setForeground(new java.awt.Color(0, 0, 0));
        btnSetupTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/x-office-address-book.png"))); // NOI18N
        btnSetupTarif.setText("Set Penggunaan Tarif");
        btnSetupTarif.setIconTextGap(0);
        btnSetupTarif.setName("btnSetupTarif"); // NOI18N
        btnSetupTarif.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetupTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupTarifActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetupTarif);

        btnTarifRadiologi.setForeground(new java.awt.Color(0, 0, 0));
        btnTarifRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1410153940_radiology.png"))); // NOI18N
        btnTarifRadiologi.setText("Tarif Radiologi");
        btnTarifRadiologi.setIconTextGap(0);
        btnTarifRadiologi.setName("btnTarifRadiologi"); // NOI18N
        btnTarifRadiologi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTarifRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarifRadiologiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnTarifRadiologi);

        btnSetupEmbalase.setForeground(new java.awt.Color(0, 0, 0));
        btnSetupEmbalase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Money.png"))); // NOI18N
        btnSetupEmbalase.setText("Set Embalase & Tuslah");
        btnSetupEmbalase.setIconTextGap(0);
        btnSetupEmbalase.setName("btnSetupEmbalase"); // NOI18N
        btnSetupEmbalase.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetupEmbalase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupEmbalaseActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetupEmbalase);

        btnPengeluaran.setForeground(new java.awt.Color(0, 0, 0));
        btnPengeluaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1404047106_emblem-money.png"))); // NOI18N
        btnPengeluaran.setText("Pengeluaran Harian");
        btnPengeluaran.setIconTextGap(0);
        btnPengeluaran.setName("btnPengeluaran"); // NOI18N
        btnPengeluaran.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengeluaranActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPengeluaran);

        btnSetObatRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnSetObatRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360487093_price.png"))); // NOI18N
        btnSetObatRalan.setText("Set Obat Ralan");
        btnSetObatRalan.setIconTextGap(0);
        btnSetObatRalan.setName("btnSetObatRalan"); // NOI18N
        btnSetObatRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetObatRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetObatRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetObatRalan);

        btnSetObatRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnSetObatRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360487093_price.png"))); // NOI18N
        btnSetObatRanap.setText("Set Obat Ranap");
        btnSetObatRanap.setIconTextGap(0);
        btnSetObatRanap.setName("btnSetObatRanap"); // NOI18N
        btnSetObatRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetObatRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetObatRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetObatRanap);

        btnPenyakitPD3I.setForeground(new java.awt.Color(0, 0, 0));
        btnPenyakitPD3I.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnPenyakitPD3I.setText("Penyakit AFP & PD3I");
        btnPenyakitPD3I.setIconTextGap(0);
        btnPenyakitPD3I.setName("btnPenyakitPD3I"); // NOI18N
        btnPenyakitPD3I.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPenyakitPD3I.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenyakitPD3IActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPenyakitPD3I);

        btnSurveilansPD3I.setForeground(new java.awt.Color(0, 0, 0));
        btnSurveilansPD3I.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnSurveilansPD3I.setText("Surveilans AFP & PD3I");
        btnSurveilansPD3I.setIconTextGap(0);
        btnSurveilansPD3I.setName("btnSurveilansPD3I"); // NOI18N
        btnSurveilansPD3I.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSurveilansPD3I.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSurveilansPD3IActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSurveilansPD3I);

        btnSurveilansRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnSurveilansRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnSurveilansRalan.setText("Surveilans Ralan");
        btnSurveilansRalan.setIconTextGap(0);
        btnSurveilansRalan.setName("btnSurveilansRalan"); // NOI18N
        btnSurveilansRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSurveilansRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSurveilansRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSurveilansRalan);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/health.png"))); // NOI18N
        btnDiagnosa.setText("Diagnosa Pasien");
        btnDiagnosa.setIconTextGap(0);
        btnDiagnosa.setName("btnDiagnosa"); // NOI18N
        btnDiagnosa.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDiagnosa);

        btnSurveilansRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnSurveilansRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnSurveilansRanap.setText("Surveilans Ranap");
        btnSurveilansRanap.setIconTextGap(0);
        btnSurveilansRanap.setName("btnSurveilansRanap"); // NOI18N
        btnSurveilansRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSurveilansRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSurveilansRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSurveilansRanap);

        btnPnyTakMenularRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnPnyTakMenularRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnPnyTakMenularRanap.setText("Pny Tdk Menular Ranap");
        btnPnyTakMenularRanap.setIconTextGap(0);
        btnPnyTakMenularRanap.setName("btnPnyTakMenularRanap"); // NOI18N
        btnPnyTakMenularRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPnyTakMenularRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPnyTakMenularRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPnyTakMenularRanap);

        btnPnyTakMenularRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnPnyTakMenularRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnPnyTakMenularRalan.setText("Pny Tdk Menular Ralan");
        btnPnyTakMenularRalan.setIconTextGap(0);
        btnPnyTakMenularRalan.setName("btnPnyTakMenularRalan"); // NOI18N
        btnPnyTakMenularRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPnyTakMenularRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPnyTakMenularRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPnyTakMenularRalan);

        btnKunjunganRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnKunjunganRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnKunjunganRalan.setText("Kunjungan Ralan");
        btnKunjunganRalan.setIconTextGap(0);
        btnKunjunganRalan.setName("btnKunjunganRalan"); // NOI18N
        btnKunjunganRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKunjunganRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKunjunganRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKunjunganRalan);

        btnRl32.setForeground(new java.awt.Color(0, 0, 0));
        btnRl32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnRl32.setText("RL 3.2 Rawat Darurat");
        btnRl32.setIconTextGap(0);
        btnRl32.setName("btnRl32"); // NOI18N
        btnRl32.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl32ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl32);

        btnRl33.setForeground(new java.awt.Color(0, 0, 0));
        btnRl33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnRl33.setText("RL 3.3 Gigi dan Mulut");
        btnRl33.setIconTextGap(0);
        btnRl33.setName("btnRl33"); // NOI18N
        btnRl33.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl33ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl33);

        btnRl37.setForeground(new java.awt.Color(0, 0, 0));
        btnRl37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnRl37.setText("RL 3.7 Radiologi");
        btnRl37.setIconTextGap(0);
        btnRl37.setName("btnRl37"); // NOI18N
        btnRl37.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl37ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl37);

        btnRl38.setForeground(new java.awt.Color(0, 0, 0));
        btnRl38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnRl38.setText("RL 3.8 Laboratorium");
        btnRl38.setIconTextGap(0);
        btnRl38.setName("btnRl38"); // NOI18N
        btnRl38.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl38ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl38);

        btnSetupNota.setForeground(new java.awt.Color(0, 0, 0));
        btnSetupNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360485642_edit-notes.png"))); // NOI18N
        btnSetupNota.setText("Set Billing / Nota / Kertas");
        btnSetupNota.setIconTextGap(0);
        btnSetupNota.setName("btnSetupNota"); // NOI18N
        btnSetupNota.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetupNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupNotaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetupNota);

        btnRl34.setForeground(new java.awt.Color(0, 0, 0));
        btnRl34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnRl34.setText("RL 3.4 Kebidanan");
        btnRl34.setIconTextGap(0);
        btnRl34.setName("btnRl34"); // NOI18N
        btnRl34.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl34ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl34);

        btnRl36.setForeground(new java.awt.Color(0, 0, 0));
        btnRl36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnRl36.setText("RL 3.6 Pembedahan");
        btnRl36.setIconTextGap(0);
        btnRl36.setName("btnRl36"); // NOI18N
        btnRl36.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl36ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl36);

        btnakun_bayar.setForeground(new java.awt.Color(0, 0, 0));
        btnakun_bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        btnakun_bayar.setText("Akun Bayar");
        btnakun_bayar.setIconTextGap(0);
        btnakun_bayar.setName("btnakun_bayar"); // NOI18N
        btnakun_bayar.setPreferredSize(new java.awt.Dimension(200, 90));
        btnakun_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnakun_bayarActionPerformed(evt);
            }
        });
        Panelmenu.add(btnakun_bayar);

        btnbayar_pemesanan.setForeground(new java.awt.Color(0, 0, 0));
        btnbayar_pemesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/cashbox.png"))); // NOI18N
        btnbayar_pemesanan.setText("Bayar Pesan Obat/BHP");
        btnbayar_pemesanan.setIconTextGap(0);
        btnbayar_pemesanan.setName("btnbayar_pemesanan"); // NOI18N
        btnbayar_pemesanan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnbayar_pemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbayar_pemesananActionPerformed(evt);
            }
        });
        Panelmenu.add(btnbayar_pemesanan);

        btnPemasukanLain.setForeground(new java.awt.Color(0, 0, 0));
        btnPemasukanLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486845_23.png"))); // NOI18N
        btnPemasukanLain.setText("Pemasukan Lain-Lain");
        btnPemasukanLain.setIconTextGap(0);
        btnPemasukanLain.setName("btnPemasukanLain"); // NOI18N
        btnPemasukanLain.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPemasukanLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemasukanLainActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPemasukanLain);

        btnPengaturanRekening.setForeground(new java.awt.Color(0, 0, 0));
        btnPengaturanRekening.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/gtk-stock-book.png"))); // NOI18N
        btnPengaturanRekening.setText("Pengaturan Rekening");
        btnPengaturanRekening.setIconTextGap(0);
        btnPengaturanRekening.setName("btnPengaturanRekening"); // NOI18N
        btnPengaturanRekening.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPengaturanRekening.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengaturanRekeningActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPengaturanRekening);

        btnClosingKasir.setForeground(new java.awt.Color(0, 0, 0));
        btnClosingKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360485865_schedule.png"))); // NOI18N
        btnClosingKasir.setText("Closing Kasir");
        btnClosingKasir.setIconTextGap(0);
        btnClosingKasir.setName("btnClosingKasir"); // NOI18N
        btnClosingKasir.setPreferredSize(new java.awt.Dimension(200, 90));
        btnClosingKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClosingKasirActionPerformed(evt);
            }
        });
        Panelmenu.add(btnClosingKasir);

        btnKeterlambatanPresensi.setForeground(new java.awt.Color(0, 0, 0));
        btnKeterlambatanPresensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Time.png"))); // NOI18N
        btnKeterlambatanPresensi.setText("Set Keterlambatan Presensi");
        btnKeterlambatanPresensi.setIconTextGap(0);
        btnKeterlambatanPresensi.setName("btnKeterlambatanPresensi"); // NOI18N
        btnKeterlambatanPresensi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKeterlambatanPresensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeterlambatanPresensiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKeterlambatanPresensi);

        btnSetHargaKamar.setForeground(new java.awt.Color(0, 0, 0));
        btnSetHargaKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bedroom.png"))); // NOI18N
        btnSetHargaKamar.setText("Set Harga Kamar");
        btnSetHargaKamar.setIconTextGap(0);
        btnSetHargaKamar.setName("btnSetHargaKamar"); // NOI18N
        btnSetHargaKamar.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetHargaKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetHargaKamarActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetHargaKamar);

        btnCekBPJSNik.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSNik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSNik.setText("Cek NIK BPJS VClaim");
        btnCekBPJSNik.setIconTextGap(0);
        btnCekBPJSNik.setName("btnCekBPJSNik"); // NOI18N
        btnCekBPJSNik.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSNik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSNikActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSNik);

        btnCekBPJSKartu.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSKartu.setText("Cek No.Kartu BPJS VClaim");
        btnCekBPJSKartu.setIconTextGap(0);
        btnCekBPJSKartu.setName("btnCekBPJSKartu"); // NOI18N
        btnCekBPJSKartu.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSKartuActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSKartu);

        btnKunjunganRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnKunjunganRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnKunjunganRanap.setText("Kunjungan Ranap");
        btnKunjunganRanap.setIconTextGap(0);
        btnKunjunganRanap.setName("btnKunjunganRanap"); // NOI18N
        btnKunjunganRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKunjunganRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKunjunganRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKunjunganRanap);

        btnCekBPJSNomorRujukanPCare.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSNomorRujukanPCare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pcare.png"))); // NOI18N
        btnCekBPJSNomorRujukanPCare.setText("Cek No. Rujukan PCare di VClaim");
        btnCekBPJSNomorRujukanPCare.setIconTextGap(0);
        btnCekBPJSNomorRujukanPCare.setName("btnCekBPJSNomorRujukanPCare"); // NOI18N
        btnCekBPJSNomorRujukanPCare.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSNomorRujukanPCare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSNomorRujukanPCareActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSNomorRujukanPCare);

        btnICD9.setForeground(new java.awt.Color(0, 0, 0));
        btnICD9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icd_10.png"))); // NOI18N
        btnICD9.setText("Master ICD-9-CM");
        btnICD9.setIconTextGap(0);
        btnICD9.setName("btnICD9"); // NOI18N
        btnICD9.setPreferredSize(new java.awt.Dimension(200, 90));
        btnICD9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnICD9ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnICD9);

        btnJurnalHarian.setForeground(new java.awt.Color(0, 0, 0));
        btnJurnalHarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360485865_schedule.png"))); // NOI18N
        btnJurnalHarian.setText("Jurnal Harian");
        btnJurnalHarian.setIconTextGap(0);
        btnJurnalHarian.setName("btnJurnalHarian"); // NOI18N
        btnJurnalHarian.setPreferredSize(new java.awt.Dimension(200, 90));
        btnJurnalHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJurnalHarianActionPerformed(evt);
            }
        });
        Panelmenu.add(btnJurnalHarian);

        btnCekBPJSDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSDiagnosa.setText("Referensi Diagnosa BPJS VClaim");
        btnCekBPJSDiagnosa.setIconTextGap(0);
        btnCekBPJSDiagnosa.setName("btnCekBPJSDiagnosa"); // NOI18N
        btnCekBPJSDiagnosa.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSDiagnosaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSDiagnosa);

        btnCekBPJSPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSPoli.setText("Referensi Poli BPJS VClaim");
        btnCekBPJSPoli.setIconTextGap(0);
        btnCekBPJSPoli.setName("btnCekBPJSPoli"); // NOI18N
        btnCekBPJSPoli.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSPoliActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSPoli);

        btnPiutangBelumLunas.setForeground(new java.awt.Color(0, 0, 0));
        btnPiutangBelumLunas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist_pencil-o.png"))); // NOI18N
        btnPiutangBelumLunas.setText("Piutang Belum Lunas");
        btnPiutangBelumLunas.setIconTextGap(0);
        btnPiutangBelumLunas.setName("btnPiutangBelumLunas"); // NOI18N
        btnPiutangBelumLunas.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPiutangBelumLunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPiutangBelumLunasActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPiutangBelumLunas);

        btnCekBPJSFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSFaskes.setText("Referensi Faskes BPJS VClaim");
        btnCekBPJSFaskes.setIconTextGap(0);
        btnCekBPJSFaskes.setName("btnCekBPJSFaskes"); // NOI18N
        btnCekBPJSFaskes.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSFaskesActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSFaskes);

        btnBPJSSEP.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnBPJSSEP.setText("Data Bridging SEP BPJS");
        btnBPJSSEP.setIconTextGap(0);
        btnBPJSSEP.setName("btnBPJSSEP"); // NOI18N
        btnBPJSSEP.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSSEPActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSSEP);

        btnTarifUtd.setForeground(new java.awt.Color(0, 0, 0));
        btnTarifUtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481001686_injection_blood.png"))); // NOI18N
        btnTarifUtd.setText("Tarif UTD");
        btnTarifUtd.setIconTextGap(0);
        btnTarifUtd.setName("btnTarifUtd"); // NOI18N
        btnTarifUtd.setPreferredSize(new java.awt.Dimension(200, 90));
        btnTarifUtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarifUtdActionPerformed(evt);
            }
        });
        Panelmenu.add(btnTarifUtd);

        btnPengambilanUTD2.setForeground(new java.awt.Color(0, 0, 0));
        btnPengambilanUTD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360487078_shipping.png"))); // NOI18N
        btnPengambilanUTD2.setText("Pengambilan BHP Medis");
        btnPengambilanUTD2.setIconTextGap(0);
        btnPengambilanUTD2.setName("btnPengambilanUTD2"); // NOI18N
        btnPengambilanUTD2.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPengambilanUTD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengambilanUTD2ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPengambilanUTD2);

        btnUTDMedisRusak.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDMedisRusak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360486858_stock-market.png"))); // NOI18N
        btnUTDMedisRusak.setText("BHP Medis Rusak");
        btnUTDMedisRusak.setIconTextGap(0);
        btnUTDMedisRusak.setName("btnUTDMedisRusak"); // NOI18N
        btnUTDMedisRusak.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDMedisRusak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDMedisRusakActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDMedisRusak);

        btnPengambilanPenunjangUTD2.setForeground(new java.awt.Color(0, 0, 0));
        btnPengambilanPenunjangUTD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481002155_skills.png"))); // NOI18N
        btnPengambilanPenunjangUTD2.setText("Pengambilan BHP Non Medis");
        btnPengambilanPenunjangUTD2.setIconTextGap(0);
        btnPengambilanPenunjangUTD2.setName("btnPengambilanPenunjangUTD2"); // NOI18N
        btnPengambilanPenunjangUTD2.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPengambilanPenunjangUTD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengambilanPenunjangUTD2ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPengambilanPenunjangUTD2);

        btnUTDPenunjangRusak.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDPenunjangRusak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/inventory-maintenance.png"))); // NOI18N
        btnUTDPenunjangRusak.setText("BHP Non Medis Rusak");
        btnUTDPenunjangRusak.setIconTextGap(0);
        btnUTDPenunjangRusak.setName("btnUTDPenunjangRusak"); // NOI18N
        btnUTDPenunjangRusak.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDPenunjangRusak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDPenunjangRusakActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDPenunjangRusak);

        btnUTDDonorDarah.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDDonorDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481001706_heart_beat.png"))); // NOI18N
        btnUTDDonorDarah.setText("Donor Darah");
        btnUTDDonorDarah.setIconTextGap(0);
        btnUTDDonorDarah.setName("btnUTDDonorDarah"); // NOI18N
        btnUTDDonorDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDDonorDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDDonorDarahActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDDonorDarah);

        btnMonitoringKlaimBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnMonitoringKlaimBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnMonitoringKlaimBPJS.setText("Monitoring Klaim BPJS VClaim");
        btnMonitoringKlaimBPJS.setIconTextGap(0);
        btnMonitoringKlaimBPJS.setName("btnMonitoringKlaimBPJS"); // NOI18N
        btnMonitoringKlaimBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMonitoringKlaimBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonitoringKlaimBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMonitoringKlaimBPJS);

        btnUTDCekalDarah.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDCekalDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360487093_price.png"))); // NOI18N
        btnUTDCekalDarah.setText("Pencekalan Darah");
        btnUTDCekalDarah.setIconTextGap(0);
        btnUTDCekalDarah.setName("btnUTDCekalDarah"); // NOI18N
        btnUTDCekalDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDCekalDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDCekalDarahActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDCekalDarah);

        btnUTDKomponenDarah.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDKomponenDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481001686_injection_blood.png"))); // NOI18N
        btnUTDKomponenDarah.setText("Komponen Darah");
        btnUTDKomponenDarah.setIconTextGap(0);
        btnUTDKomponenDarah.setName("btnUTDKomponenDarah"); // NOI18N
        btnUTDKomponenDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDKomponenDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDKomponenDarahActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDKomponenDarah);

        btnUTDStokDarah.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDStokDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481001585_blood_drop.png"))); // NOI18N
        btnUTDStokDarah.setText("Stok Darah");
        btnUTDStokDarah.setIconTextGap(0);
        btnUTDStokDarah.setName("btnUTDStokDarah"); // NOI18N
        btnUTDStokDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDStokDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDStokDarahActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDStokDarah);

        btnUTDPemisahanDarah.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDPemisahanDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360815855_laboratory.png"))); // NOI18N
        btnUTDPemisahanDarah.setText("Pemisahan Darah");
        btnUTDPemisahanDarah.setIconTextGap(0);
        btnUTDPemisahanDarah.setName("btnUTDPemisahanDarah"); // NOI18N
        btnUTDPemisahanDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDPemisahanDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDPemisahanDarahActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDPemisahanDarah);

        btnRincianPiutangPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnRincianPiutangPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist_pencil-o.png"))); // NOI18N
        btnRincianPiutangPasien.setText("Rincian Piutang Pasien");
        btnRincianPiutangPasien.setIconTextGap(0);
        btnRincianPiutangPasien.setName("btnRincianPiutangPasien"); // NOI18N
        btnRincianPiutangPasien.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRincianPiutangPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRincianPiutangPasienActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRincianPiutangPasien);

        btnUTDPenyerahanDarah.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDPenyerahanDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/health.png"))); // NOI18N
        btnUTDPenyerahanDarah.setText("Penyerahan Darah");
        btnUTDPenyerahanDarah.setIconTextGap(0);
        btnUTDPenyerahanDarah.setName("btnUTDPenyerahanDarah"); // NOI18N
        btnUTDPenyerahanDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDPenyerahanDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDPenyerahanDarahActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDPenyerahanDarah);

        btnHutangObat.setForeground(new java.awt.Color(0, 0, 0));
        btnHutangObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/cashbox.png"))); // NOI18N
        btnHutangObat.setText("Hutang Obat & BHP");
        btnHutangObat.setIconTextGap(0);
        btnHutangObat.setName("btnHutangObat"); // NOI18N
        btnHutangObat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnHutangObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHutangObatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnHutangObat);

        btnSensusHarianPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnSensusHarianPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/custom-reports.png"))); // NOI18N
        btnSensusHarianPoli.setText("Sensus Harian Poli");
        btnSensusHarianPoli.setIconTextGap(0);
        btnSensusHarianPoli.setName("btnSensusHarianPoli"); // NOI18N
        btnSensusHarianPoli.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSensusHarianPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSensusHarianPoliActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSensusHarianPoli);

        btnRl4a.setForeground(new java.awt.Color(0, 0, 0));
        btnRl4a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582089_Finance_financial_report.png"))); // NOI18N
        btnRl4a.setText("RL 4A Morbiditas Ranap");
        btnRl4a.setIconTextGap(0);
        btnRl4a.setName("btnRl4a"); // NOI18N
        btnRl4a.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl4a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl4aActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl4a);

        btnAplicareReferensiKamar.setForeground(new java.awt.Color(0, 0, 0));
        btnAplicareReferensiKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bedroom.png"))); // NOI18N
        btnAplicareReferensiKamar.setText("Referensi Kamar Aplicare");
        btnAplicareReferensiKamar.setIconTextGap(0);
        btnAplicareReferensiKamar.setName("btnAplicareReferensiKamar"); // NOI18N
        btnAplicareReferensiKamar.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAplicareReferensiKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicareReferensiKamarActionPerformed(evt);
            }
        });
        Panelmenu.add(btnAplicareReferensiKamar);

        btnAplicareKetersediaanKamar.setForeground(new java.awt.Color(0, 0, 0));
        btnAplicareKetersediaanKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1485357524_Company.png"))); // NOI18N
        btnAplicareKetersediaanKamar.setText("Ketersediaan Kamar Aplicare");
        btnAplicareKetersediaanKamar.setIconTextGap(0);
        btnAplicareKetersediaanKamar.setName("btnAplicareKetersediaanKamar"); // NOI18N
        btnAplicareKetersediaanKamar.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAplicareKetersediaanKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicareKetersediaanKamarActionPerformed(evt);
            }
        });
        Panelmenu.add(btnAplicareKetersediaanKamar);

        btnInaCBGCoderNIK.setForeground(new java.awt.Color(0, 0, 0));
        btnInaCBGCoderNIK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/inacbg_eklaim.png"))); // NOI18N
        btnInaCBGCoderNIK.setText("Coder NIK INACBG");
        btnInaCBGCoderNIK.setIconTextGap(0);
        btnInaCBGCoderNIK.setName("btnInaCBGCoderNIK"); // NOI18N
        btnInaCBGCoderNIK.setPreferredSize(new java.awt.Dimension(200, 90));
        btnInaCBGCoderNIK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInaCBGCoderNIKActionPerformed(evt);
            }
        });
        Panelmenu.add(btnInaCBGCoderNIK);

        btnAkunPiutang.setForeground(new java.awt.Color(0, 0, 0));
        btnAkunPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1404046603_wallet.png"))); // NOI18N
        btnAkunPiutang.setText("Akun Piutang");
        btnAkunPiutang.setIconTextGap(0);
        btnAkunPiutang.setName("btnAkunPiutang"); // NOI18N
        btnAkunPiutang.setPreferredSize(new java.awt.Dimension(200, 90));
        btnAkunPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAkunPiutangActionPerformed(evt);
            }
        });
        Panelmenu.add(btnAkunPiutang);

        btnPiutangPerCaraBayar.setForeground(new java.awt.Color(0, 0, 0));
        btnPiutangPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist_pencil-o.png"))); // NOI18N
        btnPiutangPerCaraBayar.setText("Piutang Per Cara Bayar");
        btnPiutangPerCaraBayar.setIconTextGap(0);
        btnPiutangPerCaraBayar.setName("btnPiutangPerCaraBayar"); // NOI18N
        btnPiutangPerCaraBayar.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPiutangPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPiutangPerCaraBayarActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPiutangPerCaraBayar);

        btnLamaPelayananRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnLamaPelayananRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnLamaPelayananRalan.setText("Durasi Waktu Pelayanan Ralan");
        btnLamaPelayananRalan.setIconTextGap(0);
        btnLamaPelayananRalan.setName("btnLamaPelayananRalan"); // NOI18N
        btnLamaPelayananRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLamaPelayananRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamaPelayananRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnLamaPelayananRalan);

        btnCatatanPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kwrite.png"))); // NOI18N
        btnCatatanPasien.setText("Catatan Pasien");
        btnCatatanPasien.setIconTextGap(0);
        btnCatatanPasien.setName("btnCatatanPasien"); // NOI18N
        btnCatatanPasien.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatatanPasienActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCatatanPasien);

        btnRl4b.setForeground(new java.awt.Color(0, 0, 0));
        btnRl4b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582089_Finance_financial_report.png"))); // NOI18N
        btnRl4b.setText("RL 4B Morbiditas Ralan");
        btnRl4b.setIconTextGap(0);
        btnRl4b.setName("btnRl4b"); // NOI18N
        btnRl4b.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl4b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl4bActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl4b);

        btnRl4asebab.setForeground(new java.awt.Color(0, 0, 0));
        btnRl4asebab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582089_Finance_financial_report.png"))); // NOI18N
        btnRl4asebab.setText("RL 4A Sebab Morbiditas Ralan");
        btnRl4asebab.setIconTextGap(0);
        btnRl4asebab.setName("btnRl4asebab"); // NOI18N
        btnRl4asebab.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl4asebab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl4asebabActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl4asebab);

        btnRl4bsebab.setForeground(new java.awt.Color(0, 0, 0));
        btnRl4bsebab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582089_Finance_financial_report.png"))); // NOI18N
        btnRl4bsebab.setText("RL 4B Sebab Morbiditas Ralan");
        btnRl4bsebab.setIconTextGap(0);
        btnRl4bsebab.setName("btnRl4bsebab"); // NOI18N
        btnRl4bsebab.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRl4bsebab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRl4bsebabActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRl4bsebab);

        btnDataHAIs.setForeground(new java.awt.Color(0, 0, 0));
        btnDataHAIs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360816018_tests.png"))); // NOI18N
        btnDataHAIs.setText("Data HAIs");
        btnDataHAIs.setIconTextGap(0);
        btnDataHAIs.setName("btnDataHAIs"); // NOI18N
        btnDataHAIs.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataHAIs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataHAIsActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDataHAIs);

        btnHarianHAIsRS.setForeground(new java.awt.Color(0, 0, 0));
        btnHarianHAIsRS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/custom-reports.png"))); // NOI18N
        btnHarianHAIsRS.setText("Harian HAIs Rumah Sakit");
        btnHarianHAIsRS.setIconTextGap(0);
        btnHarianHAIsRS.setName("btnHarianHAIsRS"); // NOI18N
        btnHarianHAIsRS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnHarianHAIsRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHarianHAIsRSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnHarianHAIsRS);

        btnBulananHAIsRS.setForeground(new java.awt.Color(0, 0, 0));
        btnBulananHAIsRS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_house_shelf_1378832.png"))); // NOI18N
        btnBulananHAIsRS.setText("Bulanan HAIs Rumah Sakit");
        btnBulananHAIsRS.setIconTextGap(0);
        btnBulananHAIsRS.setName("btnBulananHAIsRS"); // NOI18N
        btnBulananHAIsRS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBulananHAIsRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBulananHAIsRSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBulananHAIsRS);

        btnPerusahaan.setForeground(new java.awt.Color(0, 0, 0));
        btnPerusahaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1485357524_Company.png"))); // NOI18N
        btnPerusahaan.setText("Instansi/Perusahaan Pasien");
        btnPerusahaan.setIconTextGap(0);
        btnPerusahaan.setName("btnPerusahaan"); // NOI18N
        btnPerusahaan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPerusahaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerusahaanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPerusahaan);

        btnLamaPelayananApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnLamaPelayananApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnLamaPelayananApotek.setText("Lama Pelayanan Apotek");
        btnLamaPelayananApotek.setIconTextGap(0);
        btnLamaPelayananApotek.setName("btnLamaPelayananApotek"); // NOI18N
        btnLamaPelayananApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLamaPelayananApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamaPelayananApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnLamaPelayananApotek);

        btnGrafikKunjunganPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikKunjunganPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikKunjunganPoli.setText("Kunjungan Reg Per Poli");
        btnGrafikKunjunganPoli.setIconTextGap(0);
        btnGrafikKunjunganPoli.setName("btnGrafikKunjunganPoli"); // NOI18N
        btnGrafikKunjunganPoli.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikKunjunganPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikKunjunganPoliActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikKunjunganPoli);

        btnGrafikKunjunganPerDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikKunjunganPerDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikKunjunganPerDokter.setText("Kunjungan Reg Per Dokter");
        btnGrafikKunjunganPerDokter.setIconTextGap(0);
        btnGrafikKunjunganPerDokter.setName("btnGrafikKunjunganPerDokter"); // NOI18N
        btnGrafikKunjunganPerDokter.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikKunjunganPerDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikKunjunganPerDokterActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikKunjunganPerDokter);

        btnGrafikKunjunganPerPekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikKunjunganPerPekerjaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikKunjunganPerPekerjaan.setText("Kunjungan Reg Per Pekerjaan");
        btnGrafikKunjunganPerPekerjaan.setIconTextGap(0);
        btnGrafikKunjunganPerPekerjaan.setName("btnGrafikKunjunganPerPekerjaan"); // NOI18N
        btnGrafikKunjunganPerPekerjaan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikKunjunganPerPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikKunjunganPerPekerjaanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikKunjunganPerPekerjaan);

        btnGrafikKunjunganPerPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikKunjunganPerPendidikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582080_6.png"))); // NOI18N
        btnGrafikKunjunganPerPendidikan.setText("Kunjungan Reg Per Pendidikan");
        btnGrafikKunjunganPerPendidikan.setIconTextGap(0);
        btnGrafikKunjunganPerPendidikan.setName("btnGrafikKunjunganPerPendidikan"); // NOI18N
        btnGrafikKunjunganPerPendidikan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikKunjunganPerPendidikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikKunjunganPerPendidikanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikKunjunganPerPendidikan);

        btnGrafikKunjunganPerTahun.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikKunjunganPerTahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikKunjunganPerTahun.setText("Kunjungan Reg Per Tahun");
        btnGrafikKunjunganPerTahun.setIconTextGap(0);
        btnGrafikKunjunganPerTahun.setName("btnGrafikKunjunganPerTahun"); // NOI18N
        btnGrafikKunjunganPerTahun.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikKunjunganPerTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikKunjunganPerTahunActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikKunjunganPerTahun);

        btnPnyMenularRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnPnyMenularRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnPnyMenularRanap.setText("Pny Menular Ranap");
        btnPnyMenularRanap.setIconTextGap(0);
        btnPnyMenularRanap.setName("btnPnyMenularRanap"); // NOI18N
        btnPnyMenularRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPnyMenularRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPnyMenularRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPnyMenularRanap);

        btnPnyMenularRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnPnyMenularRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnPnyMenularRalan.setText("Pny Menular Ralan");
        btnPnyMenularRalan.setIconTextGap(0);
        btnPnyMenularRalan.setName("btnPnyMenularRalan"); // NOI18N
        btnPnyMenularRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPnyMenularRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPnyMenularRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPnyMenularRalan);

        btnGrafikKunjunganPerBulan.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikKunjunganPerBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582080_6.png"))); // NOI18N
        btnGrafikKunjunganPerBulan.setText("Kunjungan Reg Per Bulan");
        btnGrafikKunjunganPerBulan.setIconTextGap(0);
        btnGrafikKunjunganPerBulan.setName("btnGrafikKunjunganPerBulan"); // NOI18N
        btnGrafikKunjunganPerBulan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikKunjunganPerBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikKunjunganPerBulanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikKunjunganPerBulan);

        btnGrafikKunjunganPerTanggal.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikKunjunganPerTanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikKunjunganPerTanggal.setText("Kunjungan Reg Per Tanggal");
        btnGrafikKunjunganPerTanggal.setIconTextGap(0);
        btnGrafikKunjunganPerTanggal.setName("btnGrafikKunjunganPerTanggal"); // NOI18N
        btnGrafikKunjunganPerTanggal.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikKunjunganPerTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikKunjunganPerTanggalActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikKunjunganPerTanggal);

        btnGrafikDemografiRegistrasi.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikDemografiRegistrasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582080_6.png"))); // NOI18N
        btnGrafikDemografiRegistrasi.setText("Demografi Registrasi");
        btnGrafikDemografiRegistrasi.setIconTextGap(0);
        btnGrafikDemografiRegistrasi.setName("btnGrafikDemografiRegistrasi"); // NOI18N
        btnGrafikDemografiRegistrasi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikDemografiRegistrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikDemografiRegistrasiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikDemografiRegistrasi);

        btnGrafikStatusRegPerTahun.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegPerTahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikStatusRegPerTahun.setText("Registrasi Lama Per Tahun");
        btnGrafikStatusRegPerTahun.setIconTextGap(0);
        btnGrafikStatusRegPerTahun.setName("btnGrafikStatusRegPerTahun"); // NOI18N
        btnGrafikStatusRegPerTahun.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegPerTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegPerTahunActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegPerTahun);

        btnGrafikStatusRegPerTahun2.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegPerTahun2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582080_6.png"))); // NOI18N
        btnGrafikStatusRegPerTahun2.setText("Registrasi Baru Per Tahun");
        btnGrafikStatusRegPerTahun2.setIconTextGap(0);
        btnGrafikStatusRegPerTahun2.setName("btnGrafikStatusRegPerTahun2"); // NOI18N
        btnGrafikStatusRegPerTahun2.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegPerTahun2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegPerTahun2ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegPerTahun2);

        btnGrafikStatusRegPerBulan.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegPerBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikStatusRegPerBulan.setText("Registrasi Lama Per Bulan");
        btnGrafikStatusRegPerBulan.setIconTextGap(0);
        btnGrafikStatusRegPerBulan.setName("btnGrafikStatusRegPerBulan"); // NOI18N
        btnGrafikStatusRegPerBulan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegPerBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegPerBulanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegPerBulan);

        btnGrafikStatusRegPerBulan2.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegPerBulan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582080_6.png"))); // NOI18N
        btnGrafikStatusRegPerBulan2.setText("Registrasi Baru Per Bulan");
        btnGrafikStatusRegPerBulan2.setIconTextGap(0);
        btnGrafikStatusRegPerBulan2.setName("btnGrafikStatusRegPerBulan2"); // NOI18N
        btnGrafikStatusRegPerBulan2.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegPerBulan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegPerBulan2ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegPerBulan2);

        btnGrafikStatusRegPerTanggal.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegPerTanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582080_6.png"))); // NOI18N
        btnGrafikStatusRegPerTanggal.setText("Registrasi Lama Per Tanggal");
        btnGrafikStatusRegPerTanggal.setIconTextGap(0);
        btnGrafikStatusRegPerTanggal.setName("btnGrafikStatusRegPerTanggal"); // NOI18N
        btnGrafikStatusRegPerTanggal.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegPerTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegPerTanggalActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegPerTanggal);

        btnGrafikStatusRegPerTanggal2.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegPerTanggal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikStatusRegPerTanggal2.setText("Registrasi Baru Per Tanggal");
        btnGrafikStatusRegPerTanggal2.setIconTextGap(0);
        btnGrafikStatusRegPerTanggal2.setName("btnGrafikStatusRegPerTanggal2"); // NOI18N
        btnGrafikStatusRegPerTanggal2.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegPerTanggal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegPerTanggal2ActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegPerTanggal2);

        btnGrafikStatusRegBatalPerTahun.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegBatalPerTahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582080_6.png"))); // NOI18N
        btnGrafikStatusRegBatalPerTahun.setText("Batal Periksa Per Tahun");
        btnGrafikStatusRegBatalPerTahun.setIconTextGap(0);
        btnGrafikStatusRegBatalPerTahun.setName("btnGrafikStatusRegBatalPerTahun"); // NOI18N
        btnGrafikStatusRegBatalPerTahun.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegBatalPerTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegBatalPerTahunActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegBatalPerTahun);

        btnGrafikStatusRegBatalPerBulan.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegBatalPerBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582015_11.png"))); // NOI18N
        btnGrafikStatusRegBatalPerBulan.setText("Batal Periksa Per Bulan");
        btnGrafikStatusRegBatalPerBulan.setIconTextGap(0);
        btnGrafikStatusRegBatalPerBulan.setName("btnGrafikStatusRegBatalPerBulan"); // NOI18N
        btnGrafikStatusRegBatalPerBulan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegBatalPerBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegBatalPerBulanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegBatalPerBulan);

        btnCekPCareDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnCekPCareDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnCekPCareDiagnosa.setText("Referensi Diagnosa PCare");
        btnCekPCareDiagnosa.setIconTextGap(0);
        btnCekPCareDiagnosa.setName("btnCekPCareDiagnosa"); // NOI18N
        btnCekPCareDiagnosa.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekPCareDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekPCareDiagnosaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekPCareDiagnosa);

        btnGrafikStatusRegBatalPerTanggal.setForeground(new java.awt.Color(0, 0, 0));
        btnGrafikStatusRegBatalPerTanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582080_6.png"))); // NOI18N
        btnGrafikStatusRegBatalPerTanggal.setText("Batal Periksa Per Tanggal");
        btnGrafikStatusRegBatalPerTanggal.setIconTextGap(0);
        btnGrafikStatusRegBatalPerTanggal.setName("btnGrafikStatusRegBatalPerTanggal"); // NOI18N
        btnGrafikStatusRegBatalPerTanggal.setPreferredSize(new java.awt.Dimension(200, 90));
        btnGrafikStatusRegBatalPerTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikStatusRegBatalPerTanggalActionPerformed(evt);
            }
        });
        Panelmenu.add(btnGrafikStatusRegBatalPerTanggal);

        btnSKDPbpjs.setForeground(new java.awt.Color(0, 0, 0));
        btnSKDPbpjs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnSKDPbpjs.setText("SKDP BPJS VClaim");
        btnSKDPbpjs.setIconTextGap(0);
        btnSKDPbpjs.setName("btnSKDPbpjs"); // NOI18N
        btnSKDPbpjs.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSKDPbpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSKDPbpjsActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSKDPbpjs);

        btnRujukKeluarVclaim.setForeground(new java.awt.Color(0, 0, 0));
        btnRujukKeluarVclaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnRujukKeluarVclaim.setText("Rujukan Keluar BPJS VClaim");
        btnRujukKeluarVclaim.setIconTextGap(0);
        btnRujukKeluarVclaim.setName("btnRujukKeluarVclaim"); // NOI18N
        btnRujukKeluarVclaim.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRujukKeluarVclaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRujukKeluarVclaimActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRujukKeluarVclaim);

        btnBPJScekRiwayatRujukanPcare.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJScekRiwayatRujukanPcare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pcare.png"))); // NOI18N
        btnBPJScekRiwayatRujukanPcare.setText("Riwayat Rujukan PCare di VClaim");
        btnBPJScekRiwayatRujukanPcare.setIconTextGap(0);
        btnBPJScekRiwayatRujukanPcare.setName("btnBPJScekRiwayatRujukanPcare"); // NOI18N
        btnBPJScekRiwayatRujukanPcare.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJScekRiwayatRujukanPcare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJScekRiwayatRujukanPcareActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJScekRiwayatRujukanPcare);

        btnCekBPJSRiwayatRujukanRS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSRiwayatRujukanRS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSRiwayatRujukanRS.setText("Riwayat Rujukan RS di VClaim");
        btnCekBPJSRiwayatRujukanRS.setIconTextGap(0);
        btnCekBPJSRiwayatRujukanRS.setName("btnCekBPJSRiwayatRujukanRS"); // NOI18N
        btnCekBPJSRiwayatRujukanRS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSRiwayatRujukanRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSRiwayatRujukanRSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSRiwayatRujukanRS);

        btnCekBPJSRujukanKartuRS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSRujukanKartuRS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSRujukanKartuRS.setText("Cek Rujukan Kartu RS di VClaim");
        btnCekBPJSRujukanKartuRS.setIconTextGap(0);
        btnCekBPJSRujukanKartuRS.setName("btnCekBPJSRujukanKartuRS"); // NOI18N
        btnCekBPJSRujukanKartuRS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSRujukanKartuRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSRujukanKartuRSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSRujukanKartuRS);

        btnCekBPJSTanggalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSTanggalRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSTanggalRujukan.setText("Daftar Rujukan Khusus di VClaim");
        btnCekBPJSTanggalRujukan.setIconTextGap(0);
        btnCekBPJSTanggalRujukan.setName("btnCekBPJSTanggalRujukan"); // NOI18N
        btnCekBPJSTanggalRujukan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSTanggalRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSTanggalRujukanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSTanggalRujukan);

        btnCekBPJSNomorRujukanRS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSNomorRujukanRS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSNomorRujukanRS.setText("Cek No.Rujukan RS di VClaim");
        btnCekBPJSNomorRujukanRS.setIconTextGap(0);
        btnCekBPJSNomorRujukanRS.setName("btnCekBPJSNomorRujukanRS"); // NOI18N
        btnCekBPJSNomorRujukanRS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSNomorRujukanRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSNomorRujukanRSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSNomorRujukanRS);

        btnCekBPJSRujukanKartuPCare.setForeground(new java.awt.Color(0, 0, 0));
        btnCekBPJSRujukanKartuPCare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pcare.png"))); // NOI18N
        btnCekBPJSRujukanKartuPCare.setText("Cek Rujukan Kartu PCare di VClaim");
        btnCekBPJSRujukanKartuPCare.setIconTextGap(0);
        btnCekBPJSRujukanKartuPCare.setName("btnCekBPJSRujukanKartuPCare"); // NOI18N
        btnCekBPJSRujukanKartuPCare.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSRujukanKartuPCare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSRujukanKartuPCareActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekBPJSRujukanKartuPCare);

        btnCekReferensiKelasRawatBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiKelasRawatBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiKelasRawatBPJS.setText("Referensi Kelas Rawat VClaim");
        btnCekReferensiKelasRawatBPJS.setIconTextGap(0);
        btnCekReferensiKelasRawatBPJS.setName("btnCekReferensiKelasRawatBPJS"); // NOI18N
        btnCekReferensiKelasRawatBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiKelasRawatBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiKelasRawatBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiKelasRawatBPJS);

        btnCekReferensiProsedurBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiProsedurBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiProsedurBPJS.setText("Referensi Prosedur VClaim");
        btnCekReferensiProsedurBPJS.setIconTextGap(0);
        btnCekReferensiProsedurBPJS.setName("btnCekReferensiProsedurBPJS"); // NOI18N
        btnCekReferensiProsedurBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiProsedurBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiProsedurBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiProsedurBPJS);

        btnCekReferensiDokterDPJPBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiDokterDPJPBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiDokterDPJPBPJS.setText("Referensi Dokter DPJP VClaim");
        btnCekReferensiDokterDPJPBPJS.setIconTextGap(0);
        btnCekReferensiDokterDPJPBPJS.setName("btnCekReferensiDokterDPJPBPJS"); // NOI18N
        btnCekReferensiDokterDPJPBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiDokterDPJPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiDokterDPJPBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiDokterDPJPBPJS);

        btnCekReferensiDokterBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiDokterBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiDokterBPJS.setText("Referensi Dokter VClaim");
        btnCekReferensiDokterBPJS.setIconTextGap(0);
        btnCekReferensiDokterBPJS.setName("btnCekReferensiDokterBPJS"); // NOI18N
        btnCekReferensiDokterBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiDokterBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiDokterBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiDokterBPJS);

        btnCekReferensiSpesialistikBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiSpesialistikBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiSpesialistikBPJS.setText("Referensi Spesialistik VClaim");
        btnCekReferensiSpesialistikBPJS.setIconTextGap(0);
        btnCekReferensiSpesialistikBPJS.setName("btnCekReferensiSpesialistikBPJS"); // NOI18N
        btnCekReferensiSpesialistikBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiSpesialistikBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiSpesialistikBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiSpesialistikBPJS);

        btnCekReferensiRuangRawatBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiRuangRawatBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiRuangRawatBPJS.setText("Referensi Ruang Rawat VClaim");
        btnCekReferensiRuangRawatBPJS.setIconTextGap(0);
        btnCekReferensiRuangRawatBPJS.setName("btnCekReferensiRuangRawatBPJS"); // NOI18N
        btnCekReferensiRuangRawatBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiRuangRawatBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiRuangRawatBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiRuangRawatBPJS);

        btnCekReferensiCaraKeluarBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiCaraKeluarBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiCaraKeluarBPJS.setText("Referensi Cara Keluar VClaim");
        btnCekReferensiCaraKeluarBPJS.setIconTextGap(0);
        btnCekReferensiCaraKeluarBPJS.setName("btnCekReferensiCaraKeluarBPJS"); // NOI18N
        btnCekReferensiCaraKeluarBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiCaraKeluarBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiCaraKeluarBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiCaraKeluarBPJS);

        btnCekReferensiPascaPulangBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiPascaPulangBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiPascaPulangBPJS.setText("Referensi Pasca Pulang VClaim");
        btnCekReferensiPascaPulangBPJS.setIconTextGap(0);
        btnCekReferensiPascaPulangBPJS.setName("btnCekReferensiPascaPulangBPJS"); // NOI18N
        btnCekReferensiPascaPulangBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiPascaPulangBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiPascaPulangBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiPascaPulangBPJS);

        btnCekReferensiPropinsiBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiPropinsiBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiPropinsiBPJS.setText("Referensi Propinsi VClaim");
        btnCekReferensiPropinsiBPJS.setIconTextGap(0);
        btnCekReferensiPropinsiBPJS.setName("btnCekReferensiPropinsiBPJS"); // NOI18N
        btnCekReferensiPropinsiBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiPropinsiBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiPropinsiBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiPropinsiBPJS);

        btnCekReferensiKabupatenBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiKabupatenBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiKabupatenBPJS.setText("Referensi Kabupaten VClaim");
        btnCekReferensiKabupatenBPJS.setIconTextGap(0);
        btnCekReferensiKabupatenBPJS.setName("btnCekReferensiKabupatenBPJS"); // NOI18N
        btnCekReferensiKabupatenBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiKabupatenBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiKabupatenBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiKabupatenBPJS);

        btnCekReferensiKecamatanBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiKecamatanBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiKecamatanBPJS.setText("Referensi Kecamatan VClaim");
        btnCekReferensiKecamatanBPJS.setIconTextGap(0);
        btnCekReferensiKecamatanBPJS.setName("btnCekReferensiKecamatanBPJS"); // NOI18N
        btnCekReferensiKecamatanBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiKecamatanBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiKecamatanBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiKecamatanBPJS);

        btnJumlahPorsiDiet.setForeground(new java.awt.Color(0, 0, 0));
        btnJumlahPorsiDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_bibimbub_3377053.png"))); // NOI18N
        btnJumlahPorsiDiet.setText("Rekap Bulanan Porsi Diet");
        btnJumlahPorsiDiet.setIconTextGap(0);
        btnJumlahPorsiDiet.setName("btnJumlahPorsiDiet"); // NOI18N
        btnJumlahPorsiDiet.setPreferredSize(new java.awt.Dimension(200, 90));
        btnJumlahPorsiDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJumlahPorsiDietActionPerformed(evt);
            }
        });
        Panelmenu.add(btnJumlahPorsiDiet);

        btnJumlahMacamDiet.setForeground(new java.awt.Color(0, 0, 0));
        btnJumlahMacamDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_ramen_3377055.png"))); // NOI18N
        btnJumlahMacamDiet.setText("Rekap Bulanan Macam Diet");
        btnJumlahMacamDiet.setIconTextGap(0);
        btnJumlahMacamDiet.setName("btnJumlahMacamDiet"); // NOI18N
        btnJumlahMacamDiet.setPreferredSize(new java.awt.Dimension(200, 90));
        btnJumlahMacamDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJumlahMacamDietActionPerformed(evt);
            }
        });
        Panelmenu.add(btnJumlahMacamDiet);

        btnMasterFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360815935_contacts.png"))); // NOI18N
        btnMasterFaskes.setText("Master Data Faskes/Perujuk");
        btnMasterFaskes.setIconTextGap(0);
        btnMasterFaskes.setName("btnMasterFaskes"); // NOI18N
        btnMasterFaskes.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterFaskesActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterFaskes);

        btnCekSisruteFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnCekSisruteFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/industry.png"))); // NOI18N
        btnCekSisruteFaskes.setText("Referensi Faskes Sisrute");
        btnCekSisruteFaskes.setIconTextGap(0);
        btnCekSisruteFaskes.setName("btnCekSisruteFaskes"); // NOI18N
        btnCekSisruteFaskes.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekSisruteFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekSisruteFaskesActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekSisruteFaskes);

        btnCekSisruteAlasanRujuk.setForeground(new java.awt.Color(0, 0, 0));
        btnCekSisruteAlasanRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_todo_list_add_17451.png"))); // NOI18N
        btnCekSisruteAlasanRujuk.setText("Referensi Alasan Rujuk Sisrute");
        btnCekSisruteAlasanRujuk.setIconTextGap(0);
        btnCekSisruteAlasanRujuk.setName("btnCekSisruteAlasanRujuk"); // NOI18N
        btnCekSisruteAlasanRujuk.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekSisruteAlasanRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekSisruteAlasanRujukActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekSisruteAlasanRujuk);

        btnCekSisruteDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnCekSisruteDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnCekSisruteDiagnosa.setText("Referensi Diagnosa Sisrute");
        btnCekSisruteDiagnosa.setIconTextGap(0);
        btnCekSisruteDiagnosa.setName("btnCekSisruteDiagnosa"); // NOI18N
        btnCekSisruteDiagnosa.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekSisruteDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekSisruteDiagnosaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekSisruteDiagnosa);

        btnRujukanMasukSisrute.setForeground(new java.awt.Color(0, 0, 0));
        btnRujukanMasukSisrute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_analysis_60159.png"))); // NOI18N
        btnRujukanMasukSisrute.setText("Rujukan Masuk Sisrute");
        btnRujukanMasukSisrute.setIconTextGap(0);
        btnRujukanMasukSisrute.setName("btnRujukanMasukSisrute"); // NOI18N
        btnRujukanMasukSisrute.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRujukanMasukSisrute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRujukanMasukSisruteActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRujukanMasukSisrute);

        btnRujukanKeluarSisrute.setForeground(new java.awt.Color(0, 0, 0));
        btnRujukanKeluarSisrute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1485357758_Doctor.png"))); // NOI18N
        btnRujukanKeluarSisrute.setText("Rujukan Keluar Sisrute");
        btnRujukanKeluarSisrute.setIconTextGap(0);
        btnRujukanKeluarSisrute.setName("btnRujukanKeluarSisrute"); // NOI18N
        btnRujukanKeluarSisrute.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRujukanKeluarSisrute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRujukanKeluarSisruteActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRujukanKeluarSisrute);

        btnPasienPonek.setForeground(new java.awt.Color(0, 0, 0));
        btnPasienPonek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/checklist.png"))); // NOI18N
        btnPasienPonek.setText("Pasien Ponek (Obgyn)");
        btnPasienPonek.setIconTextGap(0);
        btnPasienPonek.setName("btnPasienPonek"); // NOI18N
        btnPasienPonek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPasienPonek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienPonekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPasienPonek);

        btnHarianHAIsRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnHarianHAIsRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1485357758_Doctor.png"))); // NOI18N
        btnHarianHAIsRanap.setText("Harian HAIs Rawat Inap");
        btnHarianHAIsRanap.setIconTextGap(0);
        btnHarianHAIsRanap.setName("btnHarianHAIsRanap"); // NOI18N
        btnHarianHAIsRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnHarianHAIsRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHarianHAIsRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnHarianHAIsRanap);

        btnHarianHAIsRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnHarianHAIsRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/patient (1).png"))); // NOI18N
        btnHarianHAIsRalan.setText("Harian HAIs Rawat Jalan");
        btnHarianHAIsRalan.setIconTextGap(0);
        btnHarianHAIsRalan.setName("btnHarianHAIsRalan"); // NOI18N
        btnHarianHAIsRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnHarianHAIsRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHarianHAIsRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnHarianHAIsRalan);

        btnBulananHAIsRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnBulananHAIsRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kwrite.png"))); // NOI18N
        btnBulananHAIsRanap.setText("Bulanan HAIs Rawat Inap");
        btnBulananHAIsRanap.setIconTextGap(0);
        btnBulananHAIsRanap.setName("btnBulananHAIsRanap"); // NOI18N
        btnBulananHAIsRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBulananHAIsRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBulananHAIsRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBulananHAIsRanap);

        btnBulananHAIsRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnBulananHAIsRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/stock_task.png"))); // NOI18N
        btnBulananHAIsRalan.setText("Bulanan HAIs Rawat Jalan");
        btnBulananHAIsRalan.setIconTextGap(0);
        btnBulananHAIsRalan.setName("btnBulananHAIsRalan"); // NOI18N
        btnBulananHAIsRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBulananHAIsRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBulananHAIsRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBulananHAIsRalan);

        btnMasterMasalahKeperawatan.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterMasalahKeperawatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_Notebook_3387299.png"))); // NOI18N
        btnMasterMasalahKeperawatan.setText("Master Masalah Keperawatan");
        btnMasterMasalahKeperawatan.setIconTextGap(0);
        btnMasterMasalahKeperawatan.setName("btnMasterMasalahKeperawatan"); // NOI18N
        btnMasterMasalahKeperawatan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterMasalahKeperawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterMasalahKeperawatanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterMasalahKeperawatan);

        btnMasterCaraBayar.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1485357524_Company.png"))); // NOI18N
        btnMasterCaraBayar.setText("Master Penanggung Jawab/Cara Bayar");
        btnMasterCaraBayar.setIconTextGap(0);
        btnMasterCaraBayar.setName("btnMasterCaraBayar"); // NOI18N
        btnMasterCaraBayar.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterCaraBayarActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterCaraBayar);

        btnDataPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        btnDataPersalinan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1481002413_surgeon.png"))); // NOI18N
        btnDataPersalinan.setText("Data Persalinan Pasien");
        btnDataPersalinan.setIconTextGap(0);
        btnDataPersalinan.setName("btnDataPersalinan"); // NOI18N
        btnDataPersalinan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataPersalinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPersalinanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDataPersalinan);

        btnPasienCorona.setForeground(new java.awt.Color(0, 0, 0));
        btnPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_fever-illness-sick-temperature-thermomete_5994873.png"))); // NOI18N
        btnPasienCorona.setText("Pasien Corona");
        btnPasienCorona.setIconTextGap(0);
        btnPasienCorona.setName("btnPasienCorona"); // NOI18N
        btnPasienCorona.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienCoronaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPasienCorona);

        btnDiagnosaPasienCorona.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosaPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_Dna-genetics-genomic-strand-virus_5994869.png"))); // NOI18N
        btnDiagnosaPasienCorona.setText("Diagnosa Pasien Corona");
        btnDiagnosaPasienCorona.setIconTextGap(0);
        btnDiagnosaPasienCorona.setName("btnDiagnosaPasienCorona"); // NOI18N
        btnDiagnosaPasienCorona.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDiagnosaPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaPasienCoronaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDiagnosaPasienCorona);

        btnPerawatanPasienCorona.setForeground(new java.awt.Color(0, 0, 0));
        btnPerawatanPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_Night-sleep-sleeping-health_5994844.png"))); // NOI18N
        btnPerawatanPasienCorona.setText("Perawatan Pasien Corona");
        btnPerawatanPasienCorona.setIconTextGap(0);
        btnPerawatanPasienCorona.setName("btnPerawatanPasienCorona"); // NOI18N
        btnPerawatanPasienCorona.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPerawatanPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerawatanPasienCoronaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPerawatanPasienCorona);

        btnRencanaKontrolBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnRencanaKontrolBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnRencanaKontrolBPJS.setText("Rencana Kontrol BPJS VClaim");
        btnRencanaKontrolBPJS.setIconTextGap(0);
        btnRencanaKontrolBPJS.setName("btnRencanaKontrolBPJS"); // NOI18N
        btnRencanaKontrolBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRencanaKontrolBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRencanaKontrolBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRencanaKontrolBPJS);

        btnBridgingEklaimINACBG.setForeground(new java.awt.Color(0, 0, 0));
        btnBridgingEklaimINACBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/inacbg_eklaim.png"))); // NOI18N
        btnBridgingEklaimINACBG.setText("Bridging Eklaim INACBG");
        btnBridgingEklaimINACBG.setIconTextGap(0);
        btnBridgingEklaimINACBG.setName("btnBridgingEklaimINACBG"); // NOI18N
        btnBridgingEklaimINACBG.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBridgingEklaimINACBG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBridgingEklaimINACBGActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBridgingEklaimINACBG);

        btnPengajuanKlaimINACBGrz.setForeground(new java.awt.Color(0, 0, 0));
        btnPengajuanKlaimINACBGrz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/inacbg_eklaim.png"))); // NOI18N
        btnPengajuanKlaimINACBGrz.setText("Pengajuan Klaim INACBG");
        btnPengajuanKlaimINACBGrz.setIconTextGap(0);
        btnPengajuanKlaimINACBGrz.setName("btnPengajuanKlaimINACBGrz"); // NOI18N
        btnPengajuanKlaimINACBGrz.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPengajuanKlaimINACBGrz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengajuanKlaimINACBGrzActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPengajuanKlaimINACBGrz);

        btnINACBGjknBelumDiklaim.setForeground(new java.awt.Color(0, 0, 0));
        btnINACBGjknBelumDiklaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/inacbg_eklaim.png"))); // NOI18N
        btnINACBGjknBelumDiklaim.setText("Pasien JKN Belum Selesai Proses Klaim");
        btnINACBGjknBelumDiklaim.setIconTextGap(0);
        btnINACBGjknBelumDiklaim.setName("btnINACBGjknBelumDiklaim"); // NOI18N
        btnINACBGjknBelumDiklaim.setPreferredSize(new java.awt.Dimension(200, 90));
        btnINACBGjknBelumDiklaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnINACBGjknBelumDiklaimActionPerformed(evt);
            }
        });
        Panelmenu.add(btnINACBGjknBelumDiklaim);

        btnInputKodeICD.setForeground(new java.awt.Color(0, 0, 0));
        btnInputKodeICD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icd_10.png"))); // NOI18N
        btnInputKodeICD.setText("Input Kode ICD Rawat Jalan");
        btnInputKodeICD.setIconTextGap(0);
        btnInputKodeICD.setName("btnInputKodeICD"); // NOI18N
        btnInputKodeICD.setPreferredSize(new java.awt.Dimension(200, 90));
        btnInputKodeICD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputKodeICDActionPerformed(evt);
            }
        });
        Panelmenu.add(btnInputKodeICD);

        btnKendaliMutuKendaliBiayaINACBG.setForeground(new java.awt.Color(0, 0, 0));
        btnKendaliMutuKendaliBiayaINACBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/inacbg_eklaim.png"))); // NOI18N
        btnKendaliMutuKendaliBiayaINACBG.setText("Kendali Mutu Kendali Biaya INACBG");
        btnKendaliMutuKendaliBiayaINACBG.setIconTextGap(0);
        btnKendaliMutuKendaliBiayaINACBG.setName("btnKendaliMutuKendaliBiayaINACBG"); // NOI18N
        btnKendaliMutuKendaliBiayaINACBG.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKendaliMutuKendaliBiayaINACBG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKendaliMutuKendaliBiayaINACBGActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKendaliMutuKendaliBiayaINACBG);

        btnCekSEPInternalBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekSEPInternalBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekSEPInternalBPJS.setText("Cek SEP Internal BPJS VClaim");
        btnCekSEPInternalBPJS.setIconTextGap(0);
        btnCekSEPInternalBPJS.setName("btnCekSEPInternalBPJS"); // NOI18N
        btnCekSEPInternalBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekSEPInternalBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekSEPInternalBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekSEPInternalBPJS);

        btnSPRIbpjsVclaim.setForeground(new java.awt.Color(0, 0, 0));
        btnSPRIbpjsVclaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnSPRIbpjsVclaim.setText("SPRI BPJS VClaim");
        btnSPRIbpjsVclaim.setIconTextGap(0);
        btnSPRIbpjsVclaim.setName("btnSPRIbpjsVclaim"); // NOI18N
        btnSPRIbpjsVclaim.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSPRIbpjsVclaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPRIbpjsVclaimActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSPRIbpjsVclaim);

        btnCekFingerPrinBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekFingerPrinBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekFingerPrinBPJS.setText("Cek Finger Print BPJS VClaim");
        btnCekFingerPrinBPJS.setIconTextGap(0);
        btnCekFingerPrinBPJS.setName("btnCekFingerPrinBPJS"); // NOI18N
        btnCekFingerPrinBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekFingerPrinBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekFingerPrinBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekFingerPrinBPJS);

        btnListSpesialistikRujukanBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnListSpesialistikRujukanBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnListSpesialistikRujukanBPJS.setText("List Spesialistik Rujukan BPJS VClaim");
        btnListSpesialistikRujukanBPJS.setIconTextGap(0);
        btnListSpesialistikRujukanBPJS.setName("btnListSpesialistikRujukanBPJS"); // NOI18N
        btnListSpesialistikRujukanBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnListSpesialistikRujukanBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListSpesialistikRujukanBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnListSpesialistikRujukanBPJS);

        btnListSaranaRujukanBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnListSaranaRujukanBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnListSaranaRujukanBPJS.setText("List Sarana Rujukan BPJS VClaim");
        btnListSaranaRujukanBPJS.setIconTextGap(0);
        btnListSaranaRujukanBPJS.setName("btnListSaranaRujukanBPJS"); // NOI18N
        btnListSaranaRujukanBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnListSaranaRujukanBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListSaranaRujukanBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnListSaranaRujukanBPJS);

        btnProgramPRBBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnProgramPRBBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnProgramPRBBPJS.setText("Program PRB BPJS VClaim");
        btnProgramPRBBPJS.setIconTextGap(0);
        btnProgramPRBBPJS.setName("btnProgramPRBBPJS"); // NOI18N
        btnProgramPRBBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnProgramPRBBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProgramPRBBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnProgramPRBBPJS);

        btnCekReferensiDiagnosaPRBBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiDiagnosaPRBBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiDiagnosaPRBBPJS.setText("Cek Referensi Diagnosa PRB BPJS VClaim");
        btnCekReferensiDiagnosaPRBBPJS.setIconTextGap(0);
        btnCekReferensiDiagnosaPRBBPJS.setName("btnCekReferensiDiagnosaPRBBPJS"); // NOI18N
        btnCekReferensiDiagnosaPRBBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiDiagnosaPRBBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiDiagnosaPRBBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiDiagnosaPRBBPJS);

        btnCekReferensiObatPRBBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiObatPRBBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiObatPRBBPJS.setText("Cek Referensi Obat PRB BPJS VClaim");
        btnCekReferensiObatPRBBPJS.setIconTextGap(0);
        btnCekReferensiObatPRBBPJS.setName("btnCekReferensiObatPRBBPJS"); // NOI18N
        btnCekReferensiObatPRBBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiObatPRBBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiObatPRBBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiObatPRBBPJS);

        btnDataNomorSuratKontrolBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnDataNomorSuratKontrolBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnDataNomorSuratKontrolBPJS.setText("Data No. Surat Kontrol/SPRI BPJS VClaim");
        btnDataNomorSuratKontrolBPJS.setIconTextGap(0);
        btnDataNomorSuratKontrolBPJS.setName("btnDataNomorSuratKontrolBPJS"); // NOI18N
        btnDataNomorSuratKontrolBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataNomorSuratKontrolBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataNomorSuratKontrolBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDataNomorSuratKontrolBPJS);

        btnHistoriPelayananPesertaBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnHistoriPelayananPesertaBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnHistoriPelayananPesertaBPJS.setText("Histori Pelayanan Peserta BPJS VClaim");
        btnHistoriPelayananPesertaBPJS.setIconTextGap(0);
        btnHistoriPelayananPesertaBPJS.setName("btnHistoriPelayananPesertaBPJS"); // NOI18N
        btnHistoriPelayananPesertaBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnHistoriPelayananPesertaBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriPelayananPesertaBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnHistoriPelayananPesertaBPJS);

        btnKlaimJaminanJasaRaharja.setForeground(new java.awt.Color(0, 0, 0));
        btnKlaimJaminanJasaRaharja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/jasa_raharja.png"))); // NOI18N
        btnKlaimJaminanJasaRaharja.setText("Klaim Jaminan Jasa Raharja VClaim");
        btnKlaimJaminanJasaRaharja.setIconTextGap(0);
        btnKlaimJaminanJasaRaharja.setName("btnKlaimJaminanJasaRaharja"); // NOI18N
        btnKlaimJaminanJasaRaharja.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKlaimJaminanJasaRaharja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKlaimJaminanJasaRaharjaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKlaimJaminanJasaRaharja);

        btnDataSuplesiJasaRaharja.setForeground(new java.awt.Color(0, 0, 0));
        btnDataSuplesiJasaRaharja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/jasa_raharja.png"))); // NOI18N
        btnDataSuplesiJasaRaharja.setText("Data Suplesi Jasa Raharja VClaim");
        btnDataSuplesiJasaRaharja.setIconTextGap(0);
        btnDataSuplesiJasaRaharja.setName("btnDataSuplesiJasaRaharja"); // NOI18N
        btnDataSuplesiJasaRaharja.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataSuplesiJasaRaharja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataSuplesiJasaRaharjaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDataSuplesiJasaRaharja);

        btnDataSEPIndukKLLJasaRaharja.setForeground(new java.awt.Color(0, 0, 0));
        btnDataSEPIndukKLLJasaRaharja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/jasa_raharja.png"))); // NOI18N
        btnDataSEPIndukKLLJasaRaharja.setText("Data SEP Induk KLL Jasa Raharja VClaim");
        btnDataSEPIndukKLLJasaRaharja.setIconTextGap(0);
        btnDataSEPIndukKLLJasaRaharja.setName("btnDataSEPIndukKLLJasaRaharja"); // NOI18N
        btnDataSEPIndukKLLJasaRaharja.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataSEPIndukKLLJasaRaharja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataSEPIndukKLLJasaRaharjaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDataSEPIndukKLLJasaRaharja);

        btnCekReferensiPoliHFISBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiPoliHFISBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiPoliHFISBPJS.setText("Cek Referensi Poliklinik HFIS BPJS VClaim");
        btnCekReferensiPoliHFISBPJS.setIconTextGap(0);
        btnCekReferensiPoliHFISBPJS.setName("btnCekReferensiPoliHFISBPJS"); // NOI18N
        btnCekReferensiPoliHFISBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiPoliHFISBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiPoliHFISBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiPoliHFISBPJS);

        btnCekReferensiJadwalHFISBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiJadwalHFISBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiJadwalHFISBPJS.setText("Cek Referensi Jadwal HFIS BPJS VClaim");
        btnCekReferensiJadwalHFISBPJS.setIconTextGap(0);
        btnCekReferensiJadwalHFISBPJS.setName("btnCekReferensiJadwalHFISBPJS"); // NOI18N
        btnCekReferensiJadwalHFISBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiJadwalHFISBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiJadwalHFISBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiJadwalHFISBPJS);

        btnCekReferensiDokterHFISBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiDokterHFISBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekReferensiDokterHFISBPJS.setText("Cek Referensi Dokter HFIS BPJS VClaim");
        btnCekReferensiDokterHFISBPJS.setIconTextGap(0);
        btnCekReferensiDokterHFISBPJS.setName("btnCekReferensiDokterHFISBPJS"); // NOI18N
        btnCekReferensiDokterHFISBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiDokterHFISBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiDokterHFISBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiDokterHFISBPJS);

        btnCekReferensiPendaftaranMobileJKNBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiPendaftaranMobileJKNBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mobile1.png"))); // NOI18N
        btnCekReferensiPendaftaranMobileJKNBPJS.setText("Cek Referensi Pendaftaran Mobile JKN BPJS VClaim");
        btnCekReferensiPendaftaranMobileJKNBPJS.setIconTextGap(0);
        btnCekReferensiPendaftaranMobileJKNBPJS.setName("btnCekReferensiPendaftaranMobileJKNBPJS"); // NOI18N
        btnCekReferensiPendaftaranMobileJKNBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiPendaftaranMobileJKNBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiPendaftaranMobileJKNBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiPendaftaranMobileJKNBPJS);

        btnCekReferensiBatalDaftarMobileJKNBPJS.setForeground(new java.awt.Color(0, 0, 0));
        btnCekReferensiBatalDaftarMobileJKNBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/mobile2.png"))); // NOI18N
        btnCekReferensiBatalDaftarMobileJKNBPJS.setText("Cek Batal Pendaftaran Mobile JKN BPJS VClaim");
        btnCekReferensiBatalDaftarMobileJKNBPJS.setIconTextGap(0);
        btnCekReferensiBatalDaftarMobileJKNBPJS.setName("btnCekReferensiBatalDaftarMobileJKNBPJS"); // NOI18N
        btnCekReferensiBatalDaftarMobileJKNBPJS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekReferensiBatalDaftarMobileJKNBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekReferensiBatalDaftarMobileJKNBPJSActionPerformed(evt);
            }
        });
        Panelmenu.add(btnCekReferensiBatalDaftarMobileJKNBPJS);

        btnKemenkesSITB.setForeground(new java.awt.Color(0, 0, 0));
        btnKemenkesSITB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kemenkes.png"))); // NOI18N
        btnKemenkesSITB.setText("Kemenkes Data SITB");
        btnKemenkesSITB.setIconTextGap(0);
        btnKemenkesSITB.setName("btnKemenkesSITB"); // NOI18N
        btnKemenkesSITB.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKemenkesSITB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKemenkesSITBActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKemenkesSITB);

        btnMasterDTD.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterDTD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icd_10.png"))); // NOI18N
        btnMasterDTD.setText("Daftar Tabulasi Diagnosa (DTD)");
        btnMasterDTD.setIconTextGap(0);
        btnMasterDTD.setName("btnMasterDTD"); // NOI18N
        btnMasterDTD.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterDTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterDTDActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterDTD);

        btnIkhtisarPerawatanHIV.setForeground(new java.awt.Color(0, 0, 0));
        btnIkhtisarPerawatanHIV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/hiv.png"))); // NOI18N
        btnIkhtisarPerawatanHIV.setText("Ikhtisar Perawatan HIV & Terapi ART");
        btnIkhtisarPerawatanHIV.setIconTextGap(0);
        btnIkhtisarPerawatanHIV.setName("btnIkhtisarPerawatanHIV"); // NOI18N
        btnIkhtisarPerawatanHIV.setPreferredSize(new java.awt.Dimension(200, 90));
        btnIkhtisarPerawatanHIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIkhtisarPerawatanHIVActionPerformed(evt);
            }
        });
        Panelmenu.add(btnIkhtisarPerawatanHIV);

        btnKemenkesKanker.setForeground(new java.awt.Color(0, 0, 0));
        btnKemenkesKanker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kemenkes.png"))); // NOI18N
        btnKemenkesKanker.setText("Kemenkes Data Penyakit Kanker");
        btnKemenkesKanker.setIconTextGap(0);
        btnKemenkesKanker.setName("btnKemenkesKanker"); // NOI18N
        btnKemenkesKanker.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKemenkesKanker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKemenkesKankerActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKemenkesKanker);

        btnSetingBridging.setForeground(new java.awt.Color(0, 0, 0));
        btnSetingBridging.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icons8-settings-64.png"))); // NOI18N
        btnSetingBridging.setText("Set Bridging");
        btnSetingBridging.setIconTextGap(0);
        btnSetingBridging.setName("btnSetingBridging"); // NOI18N
        btnSetingBridging.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSetingBridging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetingBridgingActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSetingBridging);

        btnRekamPsikologisDewasa.setForeground(new java.awt.Color(0, 0, 0));
        btnRekamPsikologisDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/psikologi.png"))); // NOI18N
        btnRekamPsikologisDewasa.setText("Rekam Psikologis Umum/Dewasa");
        btnRekamPsikologisDewasa.setIconTextGap(0);
        btnRekamPsikologisDewasa.setName("btnRekamPsikologisDewasa"); // NOI18N
        btnRekamPsikologisDewasa.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRekamPsikologisDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekamPsikologisDewasaActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRekamPsikologisDewasa);

        btnMasterKeluhanPsikologis.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterKeluhanPsikologis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/psikologi.png"))); // NOI18N
        btnMasterKeluhanPsikologis.setText("Master Keluhan Permasalahan Psikologis");
        btnMasterKeluhanPsikologis.setIconTextGap(0);
        btnMasterKeluhanPsikologis.setName("btnMasterKeluhanPsikologis"); // NOI18N
        btnMasterKeluhanPsikologis.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterKeluhanPsikologis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterKeluhanPsikologisActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterKeluhanPsikologis);

        btnMasterRencanaTritmenPsikologis.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterRencanaTritmenPsikologis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/psikologi.png"))); // NOI18N
        btnMasterRencanaTritmenPsikologis.setText("Master Rencana Tritmen Psikologis");
        btnMasterRencanaTritmenPsikologis.setIconTextGap(0);
        btnMasterRencanaTritmenPsikologis.setName("btnMasterRencanaTritmenPsikologis"); // NOI18N
        btnMasterRencanaTritmenPsikologis.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterRencanaTritmenPsikologis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterRencanaTritmenPsikologisActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterRencanaTritmenPsikologis);

        btnRekamPsikologisAnak.setForeground(new java.awt.Color(0, 0, 0));
        btnRekamPsikologisAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/psikologi.png"))); // NOI18N
        btnRekamPsikologisAnak.setText("Rekam Psikologis Anak/Remaja");
        btnRekamPsikologisAnak.setIconTextGap(0);
        btnRekamPsikologisAnak.setName("btnRekamPsikologisAnak"); // NOI18N
        btnRekamPsikologisAnak.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRekamPsikologisAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekamPsikologisAnakActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRekamPsikologisAnak);

        btnRekamPsikologiPerkawinan.setForeground(new java.awt.Color(0, 0, 0));
        btnRekamPsikologiPerkawinan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/psikologi.png"))); // NOI18N
        btnRekamPsikologiPerkawinan.setText("Rekam Psikologi Perkawinan");
        btnRekamPsikologiPerkawinan.setIconTextGap(0);
        btnRekamPsikologiPerkawinan.setName("btnRekamPsikologiPerkawinan"); // NOI18N
        btnRekamPsikologiPerkawinan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnRekamPsikologiPerkawinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekamPsikologiPerkawinanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnRekamPsikologiPerkawinan);

        btnMasterKasusPersalinanDinkes.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterKasusPersalinanDinkes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kab_banjar.png"))); // NOI18N
        btnMasterKasusPersalinanDinkes.setText("Master Kasus Persalinan (DINKES)");
        btnMasterKasusPersalinanDinkes.setIconTextGap(0);
        btnMasterKasusPersalinanDinkes.setName("btnMasterKasusPersalinanDinkes"); // NOI18N
        btnMasterKasusPersalinanDinkes.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterKasusPersalinanDinkes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterKasusPersalinanDinkesActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterKasusPersalinanDinkes);

        btnKasusPersalinanDinkes.setForeground(new java.awt.Color(0, 0, 0));
        btnKasusPersalinanDinkes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kab_banjar.png"))); // NOI18N
        btnKasusPersalinanDinkes.setText("Data Kasus Persalinan (DINKES)");
        btnKasusPersalinanDinkes.setIconTextGap(0);
        btnKasusPersalinanDinkes.setName("btnKasusPersalinanDinkes"); // NOI18N
        btnKasusPersalinanDinkes.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKasusPersalinanDinkes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKasusPersalinanDinkesActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKasusPersalinanDinkes);

        btnMasterFaktorResikoJatuh.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterFaktorResikoJatuh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_address-book_285679.png"))); // NOI18N
        btnMasterFaktorResikoJatuh.setText("Master Faktor Resiko Jatuh");
        btnMasterFaktorResikoJatuh.setIconTextGap(0);
        btnMasterFaktorResikoJatuh.setName("btnMasterFaktorResikoJatuh"); // NOI18N
        btnMasterFaktorResikoJatuh.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterFaktorResikoJatuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterFaktorResikoJatuhActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterFaktorResikoJatuh);

        btnSpirometri.setForeground(new java.awt.Color(0, 0, 0));
        btnSpirometri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/spirometry.png"))); // NOI18N
        btnSpirometri.setText("Spirometri");
        btnSpirometri.setIconTextGap(0);
        btnSpirometri.setName("btnSpirometri"); // NOI18N
        btnSpirometri.setPreferredSize(new java.awt.Dimension(200, 90));
        btnSpirometri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpirometriActionPerformed(evt);
            }
        });
        Panelmenu.add(btnSpirometri);

        btnDashboardeResepRanap.setForeground(new java.awt.Color(0, 0, 0));
        btnDashboardeResepRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/monitor_hijau.png"))); // NOI18N
        btnDashboardeResepRanap.setText("Dashboard e-Resep Rawat Inap");
        btnDashboardeResepRanap.setIconTextGap(0);
        btnDashboardeResepRanap.setName("btnDashboardeResepRanap"); // NOI18N
        btnDashboardeResepRanap.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDashboardeResepRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardeResepRanapActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDashboardeResepRanap);

        btnMasterResikoDecubitus.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterResikoDecubitus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/decubitus_matters.png"))); // NOI18N
        btnMasterResikoDecubitus.setText("Master Data Resiko Decubitus");
        btnMasterResikoDecubitus.setIconTextGap(0);
        btnMasterResikoDecubitus.setName("btnMasterResikoDecubitus"); // NOI18N
        btnMasterResikoDecubitus.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterResikoDecubitus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterResikoDecubitusActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterResikoDecubitus);

        btnReferensiDokterSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnReferensiDokterSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnReferensiDokterSatuSehat.setText("Referensi Praktisi Satu Sehat");
        btnReferensiDokterSatuSehat.setIconTextGap(0);
        btnReferensiDokterSatuSehat.setName("btnReferensiDokterSatuSehat"); // NOI18N
        btnReferensiDokterSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnReferensiDokterSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReferensiDokterSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnReferensiDokterSatuSehat);

        btnReferensiPasienSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnReferensiPasienSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnReferensiPasienSatuSehat.setText("Referensi Pasien Satu Sehat");
        btnReferensiPasienSatuSehat.setIconTextGap(0);
        btnReferensiPasienSatuSehat.setName("btnReferensiPasienSatuSehat"); // NOI18N
        btnReferensiPasienSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnReferensiPasienSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReferensiPasienSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnReferensiPasienSatuSehat);

        btnMapingOrganisasiSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnMapingOrganisasiSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnMapingOrganisasiSatuSehat.setText("Mapping Organisasi Satu Sehat");
        btnMapingOrganisasiSatuSehat.setIconTextGap(0);
        btnMapingOrganisasiSatuSehat.setName("btnMapingOrganisasiSatuSehat"); // NOI18N
        btnMapingOrganisasiSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMapingOrganisasiSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapingOrganisasiSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMapingOrganisasiSatuSehat);

        btnMapingLokasiSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnMapingLokasiSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnMapingLokasiSatuSehat.setText("Mapping Lokasi Satu Sehat");
        btnMapingLokasiSatuSehat.setIconTextGap(0);
        btnMapingLokasiSatuSehat.setName("btnMapingLokasiSatuSehat"); // NOI18N
        btnMapingLokasiSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMapingLokasiSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapingLokasiSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMapingLokasiSatuSehat);

        btnMapingVaksinSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnMapingVaksinSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnMapingVaksinSatuSehat.setText("Mapping Vaksin Satu Sehat");
        btnMapingVaksinSatuSehat.setIconTextGap(0);
        btnMapingVaksinSatuSehat.setName("btnMapingVaksinSatuSehat"); // NOI18N
        btnMapingVaksinSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMapingVaksinSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapingVaksinSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMapingVaksinSatuSehat);

        btnKirimEncounterSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimEncounterSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimEncounterSatuSehat.setText("Kirim Encounter Satu Sehat");
        btnKirimEncounterSatuSehat.setIconTextGap(0);
        btnKirimEncounterSatuSehat.setName("btnKirimEncounterSatuSehat"); // NOI18N
        btnKirimEncounterSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimEncounterSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimEncounterSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimEncounterSatuSehat);

        btnKirimConditionSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimConditionSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimConditionSatuSehat.setText("Kirim Condition (ICD-10) Satu Sehat");
        btnKirimConditionSatuSehat.setIconTextGap(0);
        btnKirimConditionSatuSehat.setName("btnKirimConditionSatuSehat"); // NOI18N
        btnKirimConditionSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimConditionSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimConditionSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimConditionSatuSehat);

        btnKirimObservationSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimObservationSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimObservationSatuSehat.setText("Kirim Observation-TTV Satu Sehat");
        btnKirimObservationSatuSehat.setIconTextGap(0);
        btnKirimObservationSatuSehat.setName("btnKirimObservationSatuSehat"); // NOI18N
        btnKirimObservationSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimObservationSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimObservationSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimObservationSatuSehat);

        btnKirimProsedurSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimProsedurSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimProsedurSatuSehat.setText("Kirim Prosedure (ICD-9) Satu Sehat");
        btnKirimProsedurSatuSehat.setIconTextGap(0);
        btnKirimProsedurSatuSehat.setName("btnKirimProsedurSatuSehat"); // NOI18N
        btnKirimProsedurSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimProsedurSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimProsedurSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimProsedurSatuSehat);

        btnKirimImunisasiSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimImunisasiSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimImunisasiSatuSehat.setText("Kirim Imunisasi Satu Sehat");
        btnKirimImunisasiSatuSehat.setIconTextGap(0);
        btnKirimImunisasiSatuSehat.setName("btnKirimImunisasiSatuSehat"); // NOI18N
        btnKirimImunisasiSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimImunisasiSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimImunisasiSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimImunisasiSatuSehat);

        btnKirimClinicalSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimClinicalSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimClinicalSatuSehat.setText("Kirim Clinical Impression Satu Sehat");
        btnKirimClinicalSatuSehat.setIconTextGap(0);
        btnKirimClinicalSatuSehat.setName("btnKirimClinicalSatuSehat"); // NOI18N
        btnKirimClinicalSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimClinicalSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimClinicalSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimClinicalSatuSehat);

        btnKirimDietSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimDietSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimDietSatuSehat.setText("Kirim Diet Satu Sehat");
        btnKirimDietSatuSehat.setIconTextGap(0);
        btnKirimDietSatuSehat.setName("btnKirimDietSatuSehat"); // NOI18N
        btnKirimDietSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimDietSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimDietSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimDietSatuSehat);

        btnMapingObatSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnMapingObatSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnMapingObatSatuSehat.setText("Mapping Obat/Alkes Satu Sehat");
        btnMapingObatSatuSehat.setIconTextGap(0);
        btnMapingObatSatuSehat.setName("btnMapingObatSatuSehat"); // NOI18N
        btnMapingObatSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMapingObatSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapingObatSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMapingObatSatuSehat);

        btnKirimMedicationRequestSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimMedicationRequestSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimMedicationRequestSatuSehat.setText("Kirim Medication Request Satu Sehat");
        btnKirimMedicationRequestSatuSehat.setIconTextGap(0);
        btnKirimMedicationRequestSatuSehat.setName("btnKirimMedicationRequestSatuSehat"); // NOI18N
        btnKirimMedicationRequestSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimMedicationRequestSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimMedicationRequestSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimMedicationRequestSatuSehat);

        btnKirimMedicationDispenseSatuSehat.setForeground(new java.awt.Color(0, 0, 0));
        btnKirimMedicationDispenseSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/satusehat.png"))); // NOI18N
        btnKirimMedicationDispenseSatuSehat.setText("Kirim Medication Dispense Satu Sehat");
        btnKirimMedicationDispenseSatuSehat.setIconTextGap(0);
        btnKirimMedicationDispenseSatuSehat.setName("btnKirimMedicationDispenseSatuSehat"); // NOI18N
        btnKirimMedicationDispenseSatuSehat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnKirimMedicationDispenseSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimMedicationDispenseSatuSehatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnKirimMedicationDispenseSatuSehat);

        btnMasterJenisDokumenJangMed.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterJenisDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/applications-office.png"))); // NOI18N
        btnMasterJenisDokumenJangMed.setText("Master Jenis Dokumen JangMed");
        btnMasterJenisDokumenJangMed.setIconTextGap(0);
        btnMasterJenisDokumenJangMed.setName("btnMasterJenisDokumenJangMed"); // NOI18N
        btnMasterJenisDokumenJangMed.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterJenisDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterJenisDokumenJangMedActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterJenisDokumenJangMed);

        btnMasterDiagnosaGizi.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterDiagnosaGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360815935_contacts.png"))); // NOI18N
        btnMasterDiagnosaGizi.setText("Master Diagnosa Gizi");
        btnMasterDiagnosaGizi.setIconTextGap(0);
        btnMasterDiagnosaGizi.setName("btnMasterDiagnosaGizi"); // NOI18N
        btnMasterDiagnosaGizi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterDiagnosaGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterDiagnosaGiziActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterDiagnosaGizi);

        btnMasterIndikatorMutuLayanan.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterIndikatorMutuLayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_address-book_285679.png"))); // NOI18N
        btnMasterIndikatorMutuLayanan.setText("Master Indikator Mutu Layanan");
        btnMasterIndikatorMutuLayanan.setIconTextGap(0);
        btnMasterIndikatorMutuLayanan.setName("btnMasterIndikatorMutuLayanan"); // NOI18N
        btnMasterIndikatorMutuLayanan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterIndikatorMutuLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterIndikatorMutuLayananActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterIndikatorMutuLayanan);

        btnIndikatorNasionalMutu.setForeground(new java.awt.Color(0, 0, 0));
        btnIndikatorNasionalMutu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1491582089_Finance_financial_report.png"))); // NOI18N
        btnIndikatorNasionalMutu.setText("Indikator Mutu Rumah Sakit");
        btnIndikatorNasionalMutu.setIconTextGap(0);
        btnIndikatorNasionalMutu.setName("btnIndikatorNasionalMutu"); // NOI18N
        btnIndikatorNasionalMutu.setPreferredSize(new java.awt.Dimension(200, 90));
        btnIndikatorNasionalMutu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIndikatorNasionalMutuActionPerformed(evt);
            }
        });
        Panelmenu.add(btnIndikatorNasionalMutu);

        btnMasterNumdenom.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterNumdenom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_address-book_285679.png"))); // NOI18N
        btnMasterNumdenom.setText("Master Numerator Denominator Mutu");
        btnMasterNumdenom.setIconTextGap(0);
        btnMasterNumdenom.setName("btnMasterNumdenom"); // NOI18N
        btnMasterNumdenom.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterNumdenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterNumdenomActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterNumdenom);

        btnPasienBlackList.setForeground(new java.awt.Color(0, 0, 0));
        btnPasienBlackList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/iconfinder_Male-User-Warning_49595.png"))); // NOI18N
        btnPasienBlackList.setText("Pasien Black List");
        btnPasienBlackList.setIconTextGap(0);
        btnPasienBlackList.setName("btnPasienBlackList"); // NOI18N
        btnPasienBlackList.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPasienBlackList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienBlackListActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPasienBlackList);

        btnHistoryLoginUser.setForeground(new java.awt.Color(0, 0, 0));
        btnHistoryLoginUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/users.png"))); // NOI18N
        btnHistoryLoginUser.setText("History Login User");
        btnHistoryLoginUser.setIconTextGap(0);
        btnHistoryLoginUser.setName("btnHistoryLoginUser"); // NOI18N
        btnHistoryLoginUser.setPreferredSize(new java.awt.Dimension(200, 90));
        btnHistoryLoginUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryLoginUserActionPerformed(evt);
            }
        });
        Panelmenu.add(btnHistoryLoginUser);

        btnQuerySql.setForeground(new java.awt.Color(0, 0, 0));
        btnQuerySql.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/query_sql.png"))); // NOI18N
        btnQuerySql.setText("Query SQL (Export Data Ke Ms. Excel)");
        btnQuerySql.setIconTextGap(0);
        btnQuerySql.setName("btnQuerySql"); // NOI18N
        btnQuerySql.setPreferredSize(new java.awt.Dimension(200, 90));
        btnQuerySql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuerySqlActionPerformed(evt);
            }
        });
        Panelmenu.add(btnQuerySql);

        btnLaporanIndikatorMutu.setForeground(new java.awt.Color(0, 0, 0));
        btnLaporanIndikatorMutu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/custom-reports.png"))); // NOI18N
        btnLaporanIndikatorMutu.setText("Laporan Indikator Mutu Rumah Sakit");
        btnLaporanIndikatorMutu.setIconTextGap(0);
        btnLaporanIndikatorMutu.setName("btnLaporanIndikatorMutu"); // NOI18N
        btnLaporanIndikatorMutu.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLaporanIndikatorMutu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanIndikatorMutuActionPerformed(evt);
            }
        });
        Panelmenu.add(btnLaporanIndikatorMutu);

        btnBPJSMapingObatApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSMapingObatApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSMapingObatApotek.setText("Mapping Obat Apotek BPJS");
        btnBPJSMapingObatApotek.setIconTextGap(0);
        btnBPJSMapingObatApotek.setName("btnBPJSMapingObatApotek"); // NOI18N
        btnBPJSMapingObatApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSMapingObatApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSMapingObatApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSMapingObatApotek);

        btnBPJSReferensiObatDPHO.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSReferensiObatDPHO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSReferensiObatDPHO.setText("Referensi DPHO Apotek BPJS");
        btnBPJSReferensiObatDPHO.setIconTextGap(0);
        btnBPJSReferensiObatDPHO.setName("btnBPJSReferensiObatDPHO"); // NOI18N
        btnBPJSReferensiObatDPHO.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSReferensiObatDPHO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSReferensiObatDPHOActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSReferensiObatDPHO);

        btnBPJSReferensiPoliApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSReferensiPoliApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSReferensiPoliApotek.setText("Referensi Poliklinik Apotek BPJS");
        btnBPJSReferensiPoliApotek.setIconTextGap(0);
        btnBPJSReferensiPoliApotek.setName("btnBPJSReferensiPoliApotek"); // NOI18N
        btnBPJSReferensiPoliApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSReferensiPoliApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSReferensiPoliApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSReferensiPoliApotek);

        btnBPJSReferensiFaskesApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSReferensiFaskesApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSReferensiFaskesApotek.setText("Referensi Faskes Apotek BPJS");
        btnBPJSReferensiFaskesApotek.setIconTextGap(0);
        btnBPJSReferensiFaskesApotek.setName("btnBPJSReferensiFaskesApotek"); // NOI18N
        btnBPJSReferensiFaskesApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSReferensiFaskesApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSReferensiFaskesApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSReferensiFaskesApotek);

        btnBPJSReferensiSpesilistikApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSReferensiSpesilistikApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSReferensiSpesilistikApotek.setText("Referensi Spesialistik Apotek BPJS");
        btnBPJSReferensiSpesilistikApotek.setIconTextGap(0);
        btnBPJSReferensiSpesilistikApotek.setName("btnBPJSReferensiSpesilistikApotek"); // NOI18N
        btnBPJSReferensiSpesilistikApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSReferensiSpesilistikApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSReferensiSpesilistikApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSReferensiSpesilistikApotek);

        btnBPJSReferensiSetingPPKApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSReferensiSetingPPKApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSReferensiSetingPPKApotek.setText("Referensi Setting PPK Apotek BPJS");
        btnBPJSReferensiSetingPPKApotek.setIconTextGap(0);
        btnBPJSReferensiSetingPPKApotek.setName("btnBPJSReferensiSetingPPKApotek"); // NOI18N
        btnBPJSReferensiSetingPPKApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSReferensiSetingPPKApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSReferensiSetingPPKApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSReferensiSetingPPKApotek);

        btnBPJSReferensiObatApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSReferensiObatApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSReferensiObatApotek.setText("Referensi Obat Apotek BPJS");
        btnBPJSReferensiObatApotek.setIconTextGap(0);
        btnBPJSReferensiObatApotek.setName("btnBPJSReferensiObatApotek"); // NOI18N
        btnBPJSReferensiObatApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSReferensiObatApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSReferensiObatApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSReferensiObatApotek);

        btnBPJSPencarianSEPApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSPencarianSEPApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSPencarianSEPApotek.setText("Pencarian SEP Apotek BPJS");
        btnBPJSPencarianSEPApotek.setIconTextGap(0);
        btnBPJSPencarianSEPApotek.setName("btnBPJSPencarianSEPApotek"); // NOI18N
        btnBPJSPencarianSEPApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSPencarianSEPApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSPencarianSEPApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSPencarianSEPApotek);

        btnBPJSMonitoringKlaimApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSMonitoringKlaimApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSMonitoringKlaimApotek.setText("Monitoring Klaim Apotek BPJS");
        btnBPJSMonitoringKlaimApotek.setIconTextGap(0);
        btnBPJSMonitoringKlaimApotek.setName("btnBPJSMonitoringKlaimApotek"); // NOI18N
        btnBPJSMonitoringKlaimApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSMonitoringKlaimApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSMonitoringKlaimApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSMonitoringKlaimApotek);

        btnBPJSDataTerkirimApotek.setForeground(new java.awt.Color(0, 0, 0));
        btnBPJSDataTerkirimApotek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/bpjs_apotek.png"))); // NOI18N
        btnBPJSDataTerkirimApotek.setText("Data Obat Terkirim Apotek BPJS");
        btnBPJSDataTerkirimApotek.setIconTextGap(0);
        btnBPJSDataTerkirimApotek.setName("btnBPJSDataTerkirimApotek"); // NOI18N
        btnBPJSDataTerkirimApotek.setPreferredSize(new java.awt.Dimension(200, 90));
        btnBPJSDataTerkirimApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPJSDataTerkirimApotekActionPerformed(evt);
            }
        });
        Panelmenu.add(btnBPJSDataTerkirimApotek);

        btnUTDPenyerahanDarahDirawat.setForeground(new java.awt.Color(0, 0, 0));
        btnUTDPenyerahanDarahDirawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/health.png"))); // NOI18N
        btnUTDPenyerahanDarahDirawat.setText("Penyerahan Darah Pasien Dirawat");
        btnUTDPenyerahanDarahDirawat.setIconTextGap(0);
        btnUTDPenyerahanDarahDirawat.setName("btnUTDPenyerahanDarahDirawat"); // NOI18N
        btnUTDPenyerahanDarahDirawat.setPreferredSize(new java.awt.Dimension(200, 90));
        btnUTDPenyerahanDarahDirawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUTDPenyerahanDarahDirawatActionPerformed(evt);
            }
        });
        Panelmenu.add(btnUTDPenyerahanDarahDirawat);

        btnMasterNomorDokumenRM.setForeground(new java.awt.Color(0, 0, 0));
        btnMasterNomorDokumenRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Gnome-X-Office-Address-Book-48.png"))); // NOI18N
        btnMasterNomorDokumenRM.setText("Master No. Dokumen Rekam Medis");
        btnMasterNomorDokumenRM.setIconTextGap(0);
        btnMasterNomorDokumenRM.setName("btnMasterNomorDokumenRM"); // NOI18N
        btnMasterNomorDokumenRM.setPreferredSize(new java.awt.Dimension(200, 90));
        btnMasterNomorDokumenRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasterNomorDokumenRMActionPerformed(evt);
            }
        });
        Panelmenu.add(btnMasterNomorDokumenRM);

        btnPermintaanLab.setForeground(new java.awt.Color(0, 0, 0));
        btnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_laboratory_44676.png"))); // NOI18N
        btnPermintaanLab.setText("Permintaan Lab");
        btnPermintaanLab.setIconTextGap(0);
        btnPermintaanLab.setName("btnPermintaanLab"); // NOI18N
        btnPermintaanLab.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPermintaanLabActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPermintaanLab);

        btnLaboratorium.setForeground(new java.awt.Color(0, 0, 0));
        btnLaboratorium.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/sign-up.png"))); // NOI18N
        btnLaboratorium.setText("Periksa Lab");
        btnLaboratorium.setIconTextGap(0);
        btnLaboratorium.setName("btnLaboratorium"); // NOI18N
        btnLaboratorium.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLaboratorium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaboratoriumActionPerformed(evt);
            }
        });
        Panelmenu.add(btnLaboratorium);

        btnPermintaanRadiologi.setForeground(new java.awt.Color(0, 0, 0));
        btnPermintaanRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/if_Thorax_X-Ray_Black_63791.png"))); // NOI18N
        btnPermintaanRadiologi.setText("Permintaan Radiologi");
        btnPermintaanRadiologi.setIconTextGap(0);
        btnPermintaanRadiologi.setName("btnPermintaanRadiologi"); // NOI18N
        btnPermintaanRadiologi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPermintaanRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPermintaanRadiologiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPermintaanRadiologi);

        btnPeriksaRadiologi.setForeground(new java.awt.Color(0, 0, 0));
        btnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/Print.png"))); // NOI18N
        btnPeriksaRadiologi.setText("Periksa Radiologi");
        btnPeriksaRadiologi.setIconTextGap(0);
        btnPeriksaRadiologi.setName("btnPeriksaRadiologi"); // NOI18N
        btnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriksaRadiologiActionPerformed(evt);
            }
        });
        Panelmenu.add(btnPeriksaRadiologi);

        btnDashboardeResepRalan.setForeground(new java.awt.Color(0, 0, 0));
        btnDashboardeResepRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/monitor.png"))); // NOI18N
        btnDashboardeResepRalan.setText("Dashboard e-Resep Rawat Jalan");
        btnDashboardeResepRalan.setIconTextGap(0);
        btnDashboardeResepRalan.setName("btnDashboardeResepRalan"); // NOI18N
        btnDashboardeResepRalan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDashboardeResepRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardeResepRalanActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDashboardeResepRalan);

        btnDaftarPermintaanResep.setForeground(new java.awt.Color(0, 0, 0));
        btnDaftarPermintaanResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1360485894_add-notes.png"))); // NOI18N
        btnDaftarPermintaanResep.setText("Daftar Resep Dokter");
        btnDaftarPermintaanResep.setIconTextGap(0);
        btnDaftarPermintaanResep.setName("btnDaftarPermintaanResep"); // NOI18N
        btnDaftarPermintaanResep.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDaftarPermintaanResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarPermintaanResepActionPerformed(evt);
            }
        });
        Panelmenu.add(btnDaftarPermintaanResep);

        scrollPane2.setViewportView(Panelmenu);

        panelMenu.add(scrollPane2, java.awt.BorderLayout.CENTER);

        DlgHome.getContentPane().add(panelMenu, java.awt.BorderLayout.CENTER);

        tanggal.setEditable(false);
        tanggal.setForeground(new java.awt.Color(50, 70, 50));
        tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29/07/2026" }));
        tanggal.setDisplayFormat("dd/MM/yyyy");
        tanggal.setName("tanggal"); // NOI18N
        tanggal.setOpaque(false);

        btnDataPenjualan.setForeground(new java.awt.Color(0, 0, 0));
        btnDataPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1485357971_desktop_computer.png"))); // NOI18N
        btnDataPenjualan.setText("Data Penjualan Obat & BHP");
        btnDataPenjualan.setIconTextGap(0);
        btnDataPenjualan.setName("btnDataPenjualan"); // NOI18N
        btnDataPenjualan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPenjualanActionPerformed(evt);
            }
        });

        btnInputPenjualan.setForeground(new java.awt.Color(0, 0, 0));
        btnInputPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/cashbox.png"))); // NOI18N
        btnInputPenjualan.setText("Input Penjualan Obat & BHP");
        btnInputPenjualan.setIconTextGap(0);
        btnInputPenjualan.setName("btnInputPenjualan"); // NOI18N
        btnInputPenjualan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnInputPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputPenjualanActionPerformed(evt);
            }
        });

        btnDataPenyerahanDarah.setForeground(new java.awt.Color(0, 0, 0));
        btnDataPenyerahanDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kwrite.png"))); // NOI18N
        btnDataPenyerahanDarah.setText("Data Penyerahan Darah");
        btnDataPenyerahanDarah.setIconTextGap(0);
        btnDataPenyerahanDarah.setName("btnDataPenyerahanDarah"); // NOI18N
        btnDataPenyerahanDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataPenyerahanDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPenyerahanDarahActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("::[ SIM RSUD Ratu Zalecha ]::");
        setIconImages(null);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 235, 205)));
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(40, 44));
        internalFrame1.setVerifyInputWhenFocusTarget(false);
        internalFrame1.setWarnaAtas(new java.awt.Color(153, 153, 153));
        internalFrame1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 1));

        BtnMenu.setForeground(new java.awt.Color(0, 0, 0));
        BtnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/menu.png"))); // NOI18N
        BtnMenu.setText("Menu");
        BtnMenu.setToolTipText("Alt+M");
        BtnMenu.setEnabled(false);
        BtnMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnMenu.setIconTextGap(3);
        BtnMenu.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnMenu.setName("BtnMenu"); // NOI18N
        BtnMenu.setPreferredSize(new java.awt.Dimension(68, 40));
        BtnMenu.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMenuActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnMenu);

        btnGantiPassword.setForeground(new java.awt.Color(0, 0, 0));
        btnGantiPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/loginorg.png"))); // NOI18N
        btnGantiPassword.setText("Ganti Password");
        btnGantiPassword.setToolTipText("Alt+G");
        btnGantiPassword.setEnabled(false);
        btnGantiPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGantiPassword.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGantiPassword.setIconTextGap(3);
        btnGantiPassword.setMargin(new java.awt.Insets(1, 2, 1, 0));
        btnGantiPassword.setName("btnGantiPassword"); // NOI18N
        btnGantiPassword.setPreferredSize(new java.awt.Dimension(125, 40));
        btnGantiPassword.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGantiPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGantiPasswordActionPerformed(evt);
            }
        });
        internalFrame1.add(btnGantiPassword);

        jSeparator4.setBackground(new java.awt.Color(150, 170, 125));
        jSeparator4.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setName("jSeparator4"); // NOI18N
        jSeparator4.setOpaque(true);
        jSeparator4.setPreferredSize(new java.awt.Dimension(1, 38));
        internalFrame1.add(jSeparator4);

        BtnToolReg.setForeground(new java.awt.Color(0, 0, 0));
        BtnToolReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PatientFile.png"))); // NOI18N
        BtnToolReg.setText("Registrasi");
        BtnToolReg.setToolTipText("Alt+R");
        BtnToolReg.setEnabled(false);
        BtnToolReg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnToolReg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolReg.setIconTextGap(3);
        BtnToolReg.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnToolReg.setName("BtnToolReg"); // NOI18N
        BtnToolReg.setPreferredSize(new java.awt.Dimension(88, 40));
        BtnToolReg.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolRegActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolReg);

        btnToolIGD.setForeground(new java.awt.Color(0, 0, 0));
        btnToolIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Doctor.png"))); // NOI18N
        btnToolIGD.setText("IGD/UGD");
        btnToolIGD.setToolTipText("Alt+D");
        btnToolIGD.setEnabled(false);
        btnToolIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnToolIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolIGD.setIconTextGap(3);
        btnToolIGD.setMargin(new java.awt.Insets(1, 2, 1, 0));
        btnToolIGD.setName("btnToolIGD"); // NOI18N
        btnToolIGD.setPreferredSize(new java.awt.Dimension(84, 40));
        btnToolIGD.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolIGDActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolIGD);

        jSeparator5.setBackground(new java.awt.Color(150, 170, 125));
        jSeparator5.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 38));
        internalFrame1.add(jSeparator5);

        btnBridgingEklaim.setForeground(new java.awt.Color(0, 0, 0));
        btnBridgingEklaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inacbg_eklaim_kecil.png"))); // NOI18N
        btnBridgingEklaim.setText("Eklaim INACBG");
        btnBridgingEklaim.setToolTipText("Alt+E");
        btnBridgingEklaim.setEnabled(false);
        btnBridgingEklaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBridgingEklaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBridgingEklaim.setIconTextGap(3);
        btnBridgingEklaim.setMargin(new java.awt.Insets(1, 2, 1, 0));
        btnBridgingEklaim.setName("btnBridgingEklaim"); // NOI18N
        btnBridgingEklaim.setPreferredSize(new java.awt.Dimension(110, 40));
        btnBridgingEklaim.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBridgingEklaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBridgingEklaimActionPerformed(evt);
            }
        });
        internalFrame1.add(btnBridgingEklaim);

        btnToolLab.setForeground(new java.awt.Color(0, 0, 0));
        btnToolLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        btnToolLab.setText("Laboratorium");
        btnToolLab.setToolTipText("Alt+O");
        btnToolLab.setEnabled(false);
        btnToolLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnToolLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolLab.setIconTextGap(3);
        btnToolLab.setMargin(new java.awt.Insets(1, 2, 1, 0));
        btnToolLab.setName("btnToolLab"); // NOI18N
        btnToolLab.setPreferredSize(new java.awt.Dimension(100, 40));
        btnToolLab.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolLabActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolLab);

        btnToolRad.setForeground(new java.awt.Color(0, 0, 0));
        btnToolRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Surgeon.png"))); // NOI18N
        btnToolRad.setText("Radiologi");
        btnToolRad.setToolTipText("Alt+A");
        btnToolRad.setEnabled(false);
        btnToolRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnToolRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolRad.setIconTextGap(3);
        btnToolRad.setMargin(new java.awt.Insets(1, 2, 1, 0));
        btnToolRad.setName("btnToolRad"); // NOI18N
        btnToolRad.setPreferredSize(new java.awt.Dimension(84, 40));
        btnToolRad.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolRadActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolRad);

        BtnToolJualObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnToolJualObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/shopping-cart-insert24.png"))); // NOI18N
        BtnToolJualObat.setText("Penjualan");
        BtnToolJualObat.setToolTipText("Alt+J");
        BtnToolJualObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnToolJualObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolJualObat.setIconTextGap(3);
        BtnToolJualObat.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnToolJualObat.setName("BtnToolJualObat"); // NOI18N
        BtnToolJualObat.setPreferredSize(new java.awt.Dimension(88, 40));
        BtnToolJualObat.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolJualObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolJualObatActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolJualObat);

        BtnDasboard.setForeground(new java.awt.Color(0, 0, 0));
        BtnDasboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Pills.png"))); // NOI18N
        BtnDasboard.setText("Dashboard e-Resep");
        BtnDasboard.setToolTipText("Alt+S");
        BtnDasboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnDasboard.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnDasboard.setIconTextGap(3);
        BtnDasboard.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnDasboard.setName("BtnDasboard"); // NOI18N
        BtnDasboard.setPreferredSize(new java.awt.Dimension(137, 40));
        BtnDasboard.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnDasboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDasboardActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnDasboard);

        jSeparator9.setBackground(new java.awt.Color(150, 170, 125));
        jSeparator9.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setName("jSeparator9"); // NOI18N
        jSeparator9.setOpaque(true);
        jSeparator9.setPreferredSize(new java.awt.Dimension(1, 38));
        internalFrame1.add(jSeparator9);

        BtnToolKamnap.setForeground(new java.awt.Color(0, 0, 0));
        BtnToolKamnap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/home24.png"))); // NOI18N
        BtnToolKamnap.setText("Rawat Inap");
        BtnToolKamnap.setToolTipText("Alt+P");
        BtnToolKamnap.setEnabled(false);
        BtnToolKamnap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnToolKamnap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolKamnap.setIconTextGap(3);
        BtnToolKamnap.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnToolKamnap.setName("BtnToolKamnap"); // NOI18N
        BtnToolKamnap.setPreferredSize(new java.awt.Dimension(96, 40));
        BtnToolKamnap.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolKamnap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolKamnapActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolKamnap);

        BtnToolKasir.setForeground(new java.awt.Color(0, 0, 0));
        BtnToolKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        BtnToolKasir.setText("Rawat Jalan");
        BtnToolKasir.setToolTipText("Alt+W");
        BtnToolKasir.setEnabled(false);
        BtnToolKasir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnToolKasir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolKasir.setIconTextGap(3);
        BtnToolKasir.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnToolKasir.setName("BtnToolKasir"); // NOI18N
        BtnToolKasir.setPreferredSize(new java.awt.Dimension(94, 40));
        BtnToolKasir.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolKasirActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolKasir);

        jSeparator7.setBackground(new java.awt.Color(150, 170, 125));
        jSeparator7.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setName("jSeparator7"); // NOI18N
        jSeparator7.setOpaque(true);
        jSeparator7.setPreferredSize(new java.awt.Dimension(1, 38));
        internalFrame1.add(jSeparator7);

        BtnLog.setForeground(new java.awt.Color(0, 0, 0));
        BtnLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/login2.png"))); // NOI18N
        BtnLog.setText("Log In");
        BtnLog.setToolTipText("Alt+L");
        BtnLog.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnLog.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnLog.setIconTextGap(3);
        BtnLog.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnLog.setName("BtnLog"); // NOI18N
        BtnLog.setPreferredSize(new java.awt.Dimension(75, 40));
        BtnLog.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnLog);

        BtnClose.setForeground(new java.awt.Color(0, 0, 0));
        BtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Delete.png"))); // NOI18N
        BtnClose.setText("Keluar");
        BtnClose.setToolTipText("Alt+U");
        BtnClose.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnClose.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnClose.setIconTextGap(3);
        BtnClose.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnClose.setName("BtnClose"); // NOI18N
        BtnClose.setPreferredSize(new java.awt.Dimension(80, 40));
        BtnClose.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnClose);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.PAGE_START);

        internalFrame4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 235, 205)));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setPreferredSize(new java.awt.Dimension(330, 25));
        internalFrame4.setWarnaBawah(new java.awt.Color(153, 153, 153));
        internalFrame4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        lblStts.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblStts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStts.setText("Status Admin :");
        lblStts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblStts.setName("lblStts"); // NOI18N
        lblStts.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame4.add(lblStts);

        jSeparator1.setBackground(new java.awt.Color(170, 190, 145));
        jSeparator1.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N
        jSeparator1.setOpaque(true);
        jSeparator1.setPreferredSize(new java.awt.Dimension(1, 20));
        internalFrame4.add(jSeparator1);

        kdUser.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        kdUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        kdUser.setText("Kode Admin :");
        kdUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kdUser.setName("kdUser"); // NOI18N
        kdUser.setPreferredSize(new java.awt.Dimension(120, 23));
        internalFrame4.add(kdUser);

        lblUser.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setText("Log Out");
        lblUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUser.setName("lblUser"); // NOI18N
        lblUser.setPreferredSize(new java.awt.Dimension(250, 23));
        internalFrame4.add(lblUser);

        jSeparator2.setBackground(new java.awt.Color(170, 190, 145));
        jSeparator2.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setName("jSeparator2"); // NOI18N
        jSeparator2.setOpaque(true);
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 20));
        internalFrame4.add(jSeparator2);

        lblTgl.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblTgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTgl.setText("Tanggal");
        lblTgl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTgl.setName("lblTgl"); // NOI18N
        lblTgl.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame4.add(lblTgl);

        jSeparator8.setBackground(new java.awt.Color(170, 190, 145));
        jSeparator8.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator8.setName("jSeparator8"); // NOI18N
        jSeparator8.setOpaque(true);
        jSeparator8.setPreferredSize(new java.awt.Dimension(1, 20));
        internalFrame4.add(jSeparator8);

        lblIPaddress.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblIPaddress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIPaddress.setText("IP Address : localhost");
        lblIPaddress.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblIPaddress.setName("lblIPaddress"); // NOI18N
        lblIPaddress.setPreferredSize(new java.awt.Dimension(140, 23));
        internalFrame4.add(lblIPaddress);

        jSeparator6.setBackground(new java.awt.Color(170, 190, 145));
        jSeparator6.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setName("jSeparator6"); // NOI18N
        jSeparator6.setOpaque(true);
        jSeparator6.setPreferredSize(new java.awt.Dimension(1, 20));
        internalFrame4.add(jSeparator6);

        footer_lbl_update.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        footer_lbl_update.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        footer_lbl_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/file-edit-16x16.png"))); // NOI18N
        footer_lbl_update.setText(" Didesain & dibuat oleh Khanza.Soft Media - Vs.");
        footer_lbl_update.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        footer_lbl_update.setIconTextGap(3);
        footer_lbl_update.setName("footer_lbl_update"); // NOI18N
        footer_lbl_update.setPreferredSize(new java.awt.Dimension(250, 23));
        internalFrame4.add(footer_lbl_update);

        Tversi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Tversi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tversi.setText("Unlimited");
        Tversi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Tversi.setIconTextGap(3);
        Tversi.setName("Tversi"); // NOI18N
        Tversi.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame4.add(Tversi);

        getContentPane().add(internalFrame4, java.awt.BorderLayout.PAGE_END);

        PanelUtama.setName("PanelUtama"); // NOI18N
        PanelUtama.setOpaque(false);
        PanelUtama.setLayout(new java.awt.BorderLayout());

        scrollPane1.setBorder(null);
        scrollPane1.setName("scrollPane1"); // NOI18N

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/wallpaper.jpg"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PanelWallMouseMoved(evt);
            }
        });
        PanelWall.setLayout(new java.awt.BorderLayout());

        panelJudul.setBackground(new java.awt.Color(255, 255, 255));
        panelJudul.setOpaqueImage(false);
        panelJudul.setPreferredSize(new java.awt.Dimension(200, 245));
        panelJudul.setRound(false);
        panelJudul.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Your Businis Solution");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(430, 30));
        panelJudul.add(jLabel11);
        jLabel11.setBounds(210, 180, 680, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/LOGO_RAZA_png.png"))); // NOI18N
        jLabel8.setText("Khanza HMS+, Hospital Management System");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelJudul.add(jLabel8);
        jLabel8.setBounds(120, 140, 820, 100);

        lbl_update.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_update.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_update.setText("-");
        lbl_update.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_update.setName("lbl_update"); // NOI18N
        lbl_update.setPreferredSize(new java.awt.Dimension(430, 30));
        panelJudul.add(lbl_update);
        lbl_update.setBounds(210, 205, 680, 20);

        Scroll21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setPreferredSize(new java.awt.Dimension(172, 130));

        ket_update.setEditable(false);
        ket_update.setColumns(20);
        ket_update.setForeground(new java.awt.Color(0, 0, 153));
        ket_update.setRows(5);
        ket_update.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ket_update.setName("ket_update"); // NOI18N
        ket_update.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ket_updateKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(ket_update);

        panelJudul.add(Scroll21);
        Scroll21.setBounds(25, 10, 720, 135);

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/yaski_icon.png"))); // NOI18N
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelJudul.add(jLabel13);
        jLabel13.setBounds(25, 140, 100, 100);

        PanelWall.add(panelJudul, java.awt.BorderLayout.PAGE_END);

        FlayMenu.setBackground(new java.awt.Color(255, 255, 255));
        FlayMenu.setOpaqueImage(false);
        FlayMenu.setPreferredSize(new java.awt.Dimension(200, 110));
        FlayMenu.setRound(false);
        FlayMenu.setLayout(new java.awt.GridLayout(1, 0, 4, 5));
        PanelWall.add(FlayMenu, java.awt.BorderLayout.PAGE_START);

        scrollPane1.setViewportView(PanelWall);

        PanelUtama.add(scrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelUtama, java.awt.BorderLayout.CENTER);

        MenuBar.setBorder(null);
        MenuBar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        MenuBar.setName("MenuBar"); // NOI18N
        MenuBar.setPreferredSize(new java.awt.Dimension(227, 30));

        jMenu1.setBackground(new java.awt.Color(20, 0, 20));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/barralan.png"))); // NOI18N
        jMenu1.setText("Informasi");
        jMenu1.setToolTipText("Alt+I");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setPreferredSize(new java.awt.Dimension(120, 30));

        MnJadwalDokterRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJadwalDokterRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnJadwalDokterRalan.setText("Jadwal Dokter Rawat Jalan");
        MnJadwalDokterRalan.setName("MnJadwalDokterRalan"); // NOI18N
        MnJadwalDokterRalan.setPreferredSize(new java.awt.Dimension(200, 35));
        MnJadwalDokterRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJadwalDokterRalanActionPerformed(evt);
            }
        });
        jMenu1.add(MnJadwalDokterRalan);

        MnPasienRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPasienRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnPasienRanap.setText("Pasien Kamar Inap");
        MnPasienRanap.setName("MnPasienRanap"); // NOI18N
        MnPasienRanap.setPreferredSize(new java.awt.Dimension(200, 35));
        MnPasienRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPasienRanapActionPerformed(evt);
            }
        });
        jMenu1.add(MnPasienRanap);

        MnTelusurKunjungan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTelusurKunjungan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTelusurKunjungan.setText("Telusur Kunjungan Pasien");
        MnTelusurKunjungan.setName("MnTelusurKunjungan"); // NOI18N
        MnTelusurKunjungan.setPreferredSize(new java.awt.Dimension(200, 35));
        MnTelusurKunjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTelusurKunjunganActionPerformed(evt);
            }
        });
        jMenu1.add(MnTelusurKunjungan);

        MnPasienMeninggal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPasienMeninggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnPasienMeninggal.setText("Pasien Meninggal");
        MnPasienMeninggal.setName("MnPasienMeninggal"); // NOI18N
        MnPasienMeninggal.setPreferredSize(new java.awt.Dimension(200, 35));
        MnPasienMeninggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPasienMeninggalActionPerformed(evt);
            }
        });
        jMenu1.add(MnPasienMeninggal);

        MnPenggunaanKamarRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenggunaanKamarRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnPenggunaanKamarRanap.setText("Penggunaan Kamar Inap");
        MnPenggunaanKamarRanap.setName("MnPenggunaanKamarRanap"); // NOI18N
        MnPenggunaanKamarRanap.setPreferredSize(new java.awt.Dimension(200, 35));
        MnPenggunaanKamarRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenggunaanKamarRanapActionPerformed(evt);
            }
        });
        jMenu1.add(MnPenggunaanKamarRanap);

        MenuBar.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(20, 0, 20));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/search_page.png"))); // NOI18N
        jMenu2.setText("Tarif Penunjang Medis");
        jMenu2.setToolTipText("Alt+I");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(170, 30));

        MnTarifLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarifLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarifLab.setText("Tarif Laboratorium");
        MnTarifLab.setName("MnTarifLab"); // NOI18N
        MnTarifLab.setPreferredSize(new java.awt.Dimension(170, 35));
        MnTarifLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifLabActionPerformed(evt);
            }
        });
        jMenu2.add(MnTarifLab);

        MnTarifRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarifRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarifRad.setText("Tarif Radiologi");
        MnTarifRad.setName("MnTarifRad"); // NOI18N
        MnTarifRad.setPreferredSize(new java.awt.Dimension(170, 35));
        MnTarifRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifRadActionPerformed(evt);
            }
        });
        jMenu2.add(MnTarifRad);

        MnTarifUpd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarifUpd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarifUpd.setText("Tarif & Stok Darah (UPD)");
        MnTarifUpd.setName("MnTarifUpd"); // NOI18N
        MnTarifUpd.setPreferredSize(new java.awt.Dimension(170, 35));
        MnTarifUpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifUpdActionPerformed(evt);
            }
        });
        jMenu2.add(MnTarifUpd);

        MenuBar.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(20, 0, 20));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/search_page.png"))); // NOI18N
        jMenu3.setText("Tarif Pelayanan Kesehatan");
        jMenu3.setToolTipText("Alt+I");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N
        jMenu3.setPreferredSize(new java.awt.Dimension(190, 30));

        MnTarifRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarifRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarifRalan.setText("Tarif Rawat Jalan");
        MnTarifRalan.setName("MnTarifRalan"); // NOI18N
        MnTarifRalan.setPreferredSize(new java.awt.Dimension(190, 35));
        MnTarifRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifRalanActionPerformed(evt);
            }
        });
        jMenu3.add(MnTarifRalan);

        MnTarifRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarifRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarifRanap.setText("Tarif Rawat Inap");
        MnTarifRanap.setName("MnTarifRanap"); // NOI18N
        MnTarifRanap.setPreferredSize(new java.awt.Dimension(190, 35));
        MnTarifRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifRanapActionPerformed(evt);
            }
        });
        jMenu3.add(MnTarifRanap);

        MnTarifKamar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarifKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarifKamar.setText("Tarif Kamar");
        MnTarifKamar.setName("MnTarifKamar"); // NOI18N
        MnTarifKamar.setPreferredSize(new java.awt.Dimension(190, 35));
        MnTarifKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifKamarActionPerformed(evt);
            }
        });
        jMenu3.add(MnTarifKamar);

        MnTarifOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarifOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarifOperasi.setText("Tarif Operasi & VK");
        MnTarifOperasi.setName("MnTarifOperasi"); // NOI18N
        MnTarifOperasi.setPreferredSize(new java.awt.Dimension(190, 35));
        MnTarifOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifOperasiActionPerformed(evt);
            }
        });
        jMenu3.add(MnTarifOperasi);

        MnTarifINACBG.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarifINACBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarifINACBG.setText("Tarif INACBG's Klaim BPJS");
        MnTarifINACBG.setName("MnTarifINACBG"); // NOI18N
        MnTarifINACBG.setPreferredSize(new java.awt.Dimension(190, 35));
        MnTarifINACBG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifINACBGActionPerformed(evt);
            }
        });
        jMenu3.add(MnTarifINACBG);

        MenuBar.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(20, 0, 20));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/63.png"))); // NOI18N
        jMenu4.setText("Tentang Program");
        jMenu4.setToolTipText("Alt+T");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N
        jMenu4.setPreferredSize(new java.awt.Dimension(160, 30));
        jMenu4.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu4MenuSelected(evt);
            }
        });
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        MenuBar.add(jMenu4);

        setJMenuBar(MenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        System.exit(0);        
    }//GEN-LAST:event_formWindowClosed

    private void BtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseActionPerformed
        isTutup();
        DlgHome.dispose();
        int jawab = JOptionPane.showConfirmDialog(null, "Yakin anda mau keluar dari program ini ????", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_BtnCloseActionPerformed

    private void BtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelActionPerformed
        edAdmin.setText("");
        edPwd.setText("");
        DlgLogin.dispose();
    }//GEN-LAST:event_BtnCancelActionPerformed

    private void BtnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLogActionPerformed
        FlayMenu.setVisible(false);
        akses.setpenjualan_obatfalse();
        akses.setpenjualan_obatfalse();
        akses.setutd_penyerahan_darahfalse();
        akses.setresep_dokterfalse();

        switch (BtnLog.getText().trim()) {
            case "Log Out":
                BtnToolReg.setEnabled(false);
                BtnToolKamnap.setEnabled(false);
                BtnToolKasir.setEnabled(false);
                btnToolLab.setEnabled(false);
                btnToolRad.setEnabled(false);
                btnToolIGD.setEnabled(false);
                btnGantiPassword.setEnabled(false);
                btnBridgingEklaim.setEnabled(false);
                edAdmin.setText("");
                edPwd.setText("");
                BtnLog.setText("Log In");
                lblStts.setText("Status Admin : ");
                lblUser.setText("Log Out");
                kdUser.setText("");
                ket_update.setText("");
                lbl_update.setText("Modified by. UNIT SIMRS RAZA - Vs. " + Tversi.getText() + " [Activated]");
                footer_lbl_update.setText(" Didesain & dibuat oleh Khanza.Soft Media - Vs.");
                BtnMenu.setEnabled(false);
                tutupFormAktifDiPanelUtama();
                bersihkanMenuSetelahLogout();
                isTutup();
                break;
            case "Log In":
                DlgLogin.setVisible(true);
                edAdmin.requestFocus();
                break;
        }
    }//GEN-LAST:event_BtnLogActionPerformed

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
        if (edAdmin.getText().trim().equals("")) {
            Valid.textKosong(edAdmin, "ID User");
        } else if (edPwd.getText().trim().equals("")) {
            Valid.textKosong(edPwd, "Password");
        } else {
            nipLogin = "";
            try {
                akses.setData(edAdmin.getText(), edPwd.getText());
                if (edAdmin.getText().equals("admin") && edPwd.getText().equals("satu")) {
                    BtnMenu.setEnabled(true);
                    BtnToolReg.setEnabled(true);
                    BtnToolKamnap.setEnabled(true);
                    BtnToolKasir.setEnabled(true);
                    btnToolLab.setEnabled(true);
                    btnToolIGD.setEnabled(true);
                    btnToolRad.setEnabled(true);
                    btnBridgingEklaim.setEnabled(true);
                    btnGantiPassword.setEnabled(false);

                    DlgLogin.dispose();
                    BtnLog.setText("Log Out");
                    lblStts.setText("Admin : ");
                    lblUser.setText("Admin Utama");
                    kdUser.setText("Kode Utama :");
                    ket_update.setText(Sequel.cariIsi("select concat('Tgl. ',date_format(tgl_update,'%d-%m-%Y'),', Jam : ',DATE_FORMAT(jam_update,'%H:%i %p'),' ',keterangan) ket from history_update order by kode desc limit 1"));
                } else if (akses.getjml1() >= 1) {
                    BtnMenu.setEnabled(true);
                    BtnToolReg.setEnabled(true);
                    BtnToolKamnap.setEnabled(true);
                    BtnToolKasir.setEnabled(true);
                    btnToolLab.setEnabled(true);
                    btnToolIGD.setEnabled(true);
                    btnToolRad.setEnabled(true);
                    btnBridgingEklaim.setEnabled(true);
                    btnGantiPassword.setEnabled(false);

                    DlgLogin.dispose();
                    BtnLog.setText("Log Out");
                    lblStts.setText("Admin : ");
                    lblUser.setText("Admin Utama");
                    kdUser.setText("Kode Utama :");
                    ket_update.setText(Sequel.cariIsi("select concat('Tgl. ',date_format(tgl_update,'%d-%m-%Y'),', Jam : ',DATE_FORMAT(jam_update,'%H:%i %p'),' ',keterangan) ket from history_update order by kode desc limit 1"));
                } else if (akses.getjml2() >= 1) {
                    BtnMenu.setEnabled(true);
                    DlgLogin.dispose();
                    BtnLog.setText("Log Out");
                    lblStts.setText("Admin : ");
                    lblUser.setText(akses.getnamauser());
                    kdUser.setText(akses.getkode());
                    btnGantiPassword.setEnabled(true);
                    BtnToolReg.setEnabled(akses.getregistrasi());
                    BtnToolKamnap.setEnabled(akses.getkamar_inap());
                    BtnToolKasir.setEnabled(akses.getkasir_ralan());
                    btnToolLab.setEnabled(akses.getperiksa_lab());
                    btnToolRad.setEnabled(akses.getperiksa_radiologi());
                    btnToolIGD.setEnabled(akses.getigd());
                    btnBridgingEklaim.setEnabled(akses.getinacbg_klaim_raza());
                    Sequel.menyimpan("tracker", "'" + edAdmin.getText() + "',current_date(),current_time()", "Login");
                    ket_update.setText(Sequel.cariIsi("select concat('Tgl. ',date_format(tgl_update,'%d-%m-%Y'),', Jam : ',DATE_FORMAT(jam_update,'%H:%i %p'),' ',keterangan) ket from history_update order by kode desc limit 1"));
                } else if ((akses.getjml1() == 0) && (akses.getjml2() == 0)) {
                    JOptionPane.showMessageDialog(null, "Maaf, Gagal login. ID User atau password ada yang salah ...!");
                    BtnToolReg.setEnabled(false);
                    BtnToolKamnap.setEnabled(false);
                    BtnToolKasir.setEnabled(false);
                    btnGantiPassword.setEnabled(false);
                    btnToolLab.setEnabled(false);
                    btnToolIGD.setEnabled(false);
                    btnToolRad.setEnabled(false);
                    btnBridgingEklaim.setEnabled(false);
                    edAdmin.setText("");
                    edPwd.setText("");

                    BtnMenu.setEnabled(false);

                    edAdmin.requestFocus();
                    BtnLog.setText("Log In");
                    lblStts.setText("Status Admin : ");
                    lblUser.setText("Log Out");
                    ket_update.setText("");
                }
                
                if (akses.getadmin() == true) {
                    nipLogin = "-";
                } else {
                    nipLogin = akses.getkode();
                }
                
                if (sttsFileSIMRS.equals("file simrs update")) {
                    String versi = Sequel.cariIsi("select versi_update from history_update ORDER BY tgl_update desc, jam_update desc limit 1");
                    Sequel.menyimpanIgnore("history_aplikasi", "'" + ipKomputer + "','" + versi + "','SIMRS',"
                            + "'" + nipLogin + "','" + Sequel.cariIsi("select now()") + "'", "Update versi SIMRS");
                    Valid.bikinFileTxt(versi, Sequel.cariFolderVersi(), "conf_versi.txt");
                    Tversi.setText(versi);
                    lbl_update.setText("Modified by. UNIT SIMRS RAZA - Vs. " + versi + " [Activated]");
                } else {
                    try {
                        if (!koneksiDB.SIMRSDEVELOPMENT().equals("Ya")) {
                            if (Sequel.cariInteger("select count(-1) from setting where auto_restart='ya'") > 0) {
                                int x = JOptionPane.showConfirmDialog(rootPane, "Aplikasi SIMRS belum update dengan versi terbaru, bolehkah komputernya direstart dulu..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                                if (x == JOptionPane.YES_OPTION) {
                                    cekKomputer();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("E : " + e);
                        if (Sequel.cariInteger("select count(-1) from setting where auto_restart='ya'") > 0) {
                            int x = JOptionPane.showConfirmDialog(rootPane, "Aplikasi SIMRS belum update dengan versi terbaru, bolehkah komputernya direstart dulu..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                            if (x == JOptionPane.YES_OPTION) {
                                cekKomputer();
                            }
                        }
                    }
                }
                
                Sequel.queryu("delete from history_aplikasi where date(waktu_update) < DATE_FORMAT(date_sub(now(), interval 10 day),'%Y-%m-%d')");
                Sequel.queryu("delete from history_update where tgl_update < DATE_FORMAT(date_sub(now(), interval 60 day),'%Y-%m-%d')");
                
                if (menuUtamaA == null) {
                    menuUtamaA = new PanelMenuUtamaA(this, false);
                }

                if (menuUtamaB == null) {
                    menuUtamaB = new PanelMenuUtamaB(this, false);
                }
                
                if (menuUtamaC == null) {
                    menuUtamaC = new PanelMenuUtamaC(this, false);
                }
                
                if (menuUtamaD == null) {
                    menuUtamaD = new PanelMenuUtamaD(this, false);
                }
                
                if (menuUtamaE == null) {
                    menuUtamaE = new PanelMenuUtamaE(this, false);
                }
                
                if (menuUtamaF == null) {
                    menuUtamaF = new PanelMenuUtamaF(this, false);
                }
                
                if (menuUtamaG == null) {
                    menuUtamaG = new PanelMenuUtamaG(this, false);
                }
                
                if (menuUtamaH == null) {
                    menuUtamaH = new PanelMenuUtamaH(this, false);
                }
                
                menuUtamaA.terapkanHakAkses();
                menuUtamaB.terapkanHakAkses();
                menuUtamaC.terapkanHakAkses();
                menuUtamaD.terapkanHakAkses();
                menuUtamaE.terapkanHakAkses();
                menuUtamaF.terapkanHakAkses();
                menuUtamaG.terapkanHakAkses();
                menuUtamaH.terapkanHakAkses();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnLoginActionPerformed

    private void BtnToolKamnapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolKamnapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kamarinap.isCek();
        kamarinap.emptTeks();
        kamarinap.setCariKosong();
        kamarinap.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        kamarinap.setLocationRelativeTo(PanelUtama);
        kamarinap.setVisible(true);
        kamarinap.UserValid();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnToolKamnapActionPerformed

private void edPwdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edPwdKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        BtnLoginActionPerformed(null);
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        edAdmin.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        BtnLogin.requestFocus();
    }
}//GEN-LAST:event_edPwdKeyPressed

private void BtnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMenuActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
    isTutup();
//    DlgHome.setSize(PanelUtama.getWidth() - 45, PanelUtama.getHeight() - 45);
    DlgHome.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());    
    DlgHome.setLocationRelativeTo(PanelUtama);
    DlgHome.setVisible(true);
    DlgHome.toFront();
    DlgHome.requestFocus();
    isTampil();

    if (TCari.isVisible() == true) {
        button2.setVisible(true);
        TCari.requestFocus();
    } else {
        button2.setVisible(false);
        cmbMenu.requestFocus();
    }
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnMenuActionPerformed

private void BtnToolKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolKasirActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
    isTutup();
    akses.tRefreshAntrian.start();
    kasirralan.isCek();
    kasirralan.empttext();
    kasirralan.tampilkasir();
    kasirralan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
    kasirralan.setLocationRelativeTo(PanelUtama);
    kasirralan.setVisible(true);
    DlgHome.dispose();
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnToolKasirActionPerformed

private void BtnToolRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolRegActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
    isTutup();    
    DlgReg reg = new DlgReg(null, false);
    reg.emptTeks();
    reg.isCek();
    reg.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
    reg.setLocationRelativeTo(PanelUtama);
    reg.setVisible(true);
    reg.TabRawat.setSelectedIndex(0);
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnToolRegActionPerformed

private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
    if (this.getState() == 1) {
        isTutup();
    }
}//GEN-LAST:event_formWindowStateChanged

private void BtnClosePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClosePassActionPerformed
    WindowInput.dispose();
}//GEN-LAST:event_BtnClosePassActionPerformed

private void BtnClosePassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnClosePassKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        WindowInput.dispose();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        PassBaru2.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        PassLama.requestFocus();
    }
}//GEN-LAST:event_BtnClosePassKeyPressed

private void BtnSimpanPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPassActionPerformed
    if (PassLama.getText().trim().equals("")) {
        Valid.textKosong(PassLama, "Password Lama");
    } else if (Passbaru1.getText().trim().equals("")) {
        Valid.textKosong(Passbaru1, "Password Baru");
    } else if (PassBaru2.getText().trim().equals("")) {
        Valid.textKosong(PassBaru2, "Password Baru");
    } else if (!edPwd.getText().trim().equals(PassLama.getText())) {
        JOptionPane.showMessageDialog(null, "Maaf, Password lama salah...!!!");
        PassLama.requestFocus();
    } else if (!Passbaru1.getText().trim().equals(PassBaru2.getText())) {
        JOptionPane.showMessageDialog(null, "Maaf, Password Baru 1 dan Password Baru 2 tidak sesuai...!!!");
        PassBaru2.requestFocus();
    } else {
        Sequel.queryu("update user set password=AES_ENCRYPT('" + PassBaru2.getText() + "','windi')  where id_user=AES_ENCRYPT('" + kdUser.getText() + "','nur')");
        WindowInput.setVisible(false);
    }
}//GEN-LAST:event_BtnSimpanPassActionPerformed

private void BtnSimpanPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanPassKeyPressed
    Valid.pindah(evt, PassLama, PassBaru2);
}//GEN-LAST:event_BtnSimpanPassKeyPressed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        DlgHome.dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void cmbMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMenuItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            isTampil();
        }
    }//GEN-LAST:event_cmbMenuItemStateChanged

    private void btnICDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnICDActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgCariPenyakit penyakit=new DlgCariPenyakit(null,false);
        penyakit.isCek();
        penyakit.emptTeks();
        penyakit.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        penyakit.setLocationRelativeTo(PanelUtama);
        penyakit.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnICDActionPerformed

    private void btnObatPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObatPenyakitActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgObatPenyakit obatpenyakit = new DlgObatPenyakit(this, false);
        obatpenyakit.isCek();
        obatpenyakit.emptTeks();
        obatpenyakit.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        obatpenyakit.setLocationRelativeTo(PanelUtama);
        obatpenyakit.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnObatPenyakitActionPerformed

    private void btnKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgKamar kamar = new DlgKamar(null, false);
        kamar.emptTeks();
        kamar.isCek();
        kamar.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        kamar.setLocationRelativeTo(PanelUtama);
        kamar.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKamarActionPerformed

    private void btnTindakanRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakanRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanRalan perawatan = new DlgJnsPerawatanRalan(null, false);
        perawatan.emptTeks();
        perawatan.isCek();
        perawatan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        perawatan.setLocationRelativeTo(PanelUtama);
        perawatan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTindakanRalanActionPerformed

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPasien pasien = new DlgPasien(null, false);
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        pasien.setLocationRelativeTo(PanelUtama);
        pasien.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPasienActionPerformed

    private void btnPasienMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienMatiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPasienMati pasienmati = new DlgPasienMati(this, false);
        pasienmati.emptTeks();
        pasienmati.isCek();
        pasienmati.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        pasienmati.setLocationRelativeTo(PanelUtama);
        pasienmati.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPasienMatiActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed

        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgAdmin admin = new DlgAdmin(this, false);
        admin.tampil();
        admin.emptTeks();
        admin.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        admin.setLocationRelativeTo(PanelUtama);
        admin.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgUser user = new DlgUser(this, false);
        user.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        user.setLocationRelativeTo(PanelUtama);
        user.setVisible(true);
        user.emptTeks();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnAntrianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAntrianActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRunTeks runteks = new DlgRunTeks(this, false);
        runteks.emptTeks();
        runteks.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        runteks.setLocationRelativeTo(PanelUtama);
        runteks.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnAntrianActionPerformed

    private void btnSetupHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupHargaActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetHarga setharga = new DlgSetHarga(this, false);
        setharga.emptTeks();
        setharga.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        setharga.setLocationRelativeTo(PanelUtama);
        setharga.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetupHargaActionPerformed

    private void btnCashFlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashFlowActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCashflow bubes = new DlgCashflow(this, false);
        bubes.isCek();
        bubes.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        bubes.setLocationRelativeTo(PanelUtama);
        bubes.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCashFlowActionPerformed

    private void btnBubesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBubesActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBubes bubes = new DlgBubes(this, false);
        bubes.isCek();
        bubes.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        bubes.setLocationRelativeTo(PanelUtama);
        bubes.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBubesActionPerformed

    private void btnPostingJurnalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostingJurnalActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJurnal jurnal = new DlgJurnal(this, false);
        jurnal.tampil();
        jurnal.isCek();
        jurnal.emptTeks();
        jurnal.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        jurnal.setLocationRelativeTo(PanelUtama);
        jurnal.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPostingJurnalActionPerformed

    private void btnRekeningTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekeningTahunActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRekeningTahun rekeningtahun = new DlgRekeningTahun(this, false);
        rekeningtahun.tampil();
        rekeningtahun.isCek();
        rekeningtahun.emptTeks();
        rekeningtahun.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        rekeningtahun.setLocationRelativeTo(PanelUtama);
        rekeningtahun.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRekeningTahunActionPerformed

    private void btnRekeningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekeningActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRekening rekening = new DlgRekening(this, false);
        rekening.tampil();
        rekening.isCek();
        rekening.emptTeks();
        rekening.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        rekening.setLocationRelativeTo(PanelUtama);
        rekening.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRekeningActionPerformed

    private void btnPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjualanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenjualan penjualan = new DlgPenjualan(this, false);
        penjualan.isCek();
        penjualan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        penjualan.setLocationRelativeTo(PanelUtama);
        penjualan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPenjualanActionPerformed

    private void btnBayarPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarPiutangActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBayarPiutang bayarpiutang = new DlgBayarPiutang(this, false);
        bayarpiutang.tampil();
        bayarpiutang.emptTeks();
        bayarpiutang.isCek();
        bayarpiutang.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        bayarpiutang.setLocationRelativeTo(PanelUtama);
        bayarpiutang.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBayarPiutangActionPerformed

    private void btnLabaRugiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLabaRugiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgLabaRugi labrug = new DlgLabaRugi(this, false);
        labrug.isCek();
        labrug.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        labrug.setLocationRelativeTo(PanelUtama);
        labrug.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnLabaRugiActionPerformed

    private void btnResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumeActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgResumePerawatan resume = new DlgResumePerawatan(this, false);
        resume.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        resume.setLocationRelativeTo(PanelUtama);
        resume.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnResumeActionPerformed

    private void btnLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLahirActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIKBBayi lahir = new DlgIKBBayi(this, false);
        lahir.tampil();
        lahir.isCek();
        lahir.emptTeks();
        lahir.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        lahir.setLocationRelativeTo(PanelUtama);
        lahir.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnLahirActionPerformed

    private void btnSetBiayaHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetBiayaHarianActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBiayaHarian biayaharian = new DlgBiayaHarian(this, false);
        biayaharian.emptTeks();
        biayaharian.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        biayaharian.setLocationRelativeTo(PanelUtama);
        biayaharian.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetBiayaHarianActionPerformed

    private void btnSetupAplikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupAplikasiActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetAplikasi aplikasi = new DlgSetAplikasi(this, false);
        aplikasi.emptTeks();
        aplikasi.emptUpdate();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetupAplikasiActionPerformed

    private void btnSetOtoRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetOtoRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetOtoRalan aplikasi = new DlgSetOtoRalan(this, false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetOtoRalanActionPerformed

    private void btnSetBiayaMasukSekaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetBiayaMasukSekaliActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBiayaSekaliMasuk biayaharian = new DlgBiayaSekaliMasuk(this, false);
        biayaharian.emptTeks();
        biayaharian.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        biayaharian.setLocationRelativeTo(PanelUtama);
        biayaharian.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetBiayaMasukSekaliActionPerformed

    private void btnPaketOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaketOperasiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanOperasi produsen = new DlgJnsPerawatanOperasi(this, false);
        produsen.emptTeks();
        produsen.isCek();
        produsen.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        produsen.setLocationRelativeTo(PanelUtama);
        produsen.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPaketOperasiActionPerformed

    private void btnFrekuensiRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrekuensiRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgFrekuensiPenyakitRalan ktginventaris = new DlgFrekuensiPenyakitRalan(this, false);
        ktginventaris.isCek();
        ktginventaris.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        ktginventaris.setLocationRelativeTo(PanelUtama);
        ktginventaris.setVisible(true);
        ktginventaris.UserValid();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnFrekuensiRalanActionPerformed

    private void btnFrekuensiRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrekuensiRanapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgFrekuensiPenyakitRanap ktginventaris = new DlgFrekuensiPenyakitRanap(this, false);
        ktginventaris.isCek();
        ktginventaris.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        ktginventaris.setLocationRelativeTo(PanelUtama);
        ktginventaris.setVisible(true);
        ktginventaris.UserValid();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnFrekuensiRanapActionPerformed

    private void btnSetupOtoLokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupOtoLokasiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetOtoLokasi ktginventaris = new DlgSetOtoLokasi(this, false);
        ktginventaris.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        ktginventaris.setLocationRelativeTo(PanelUtama);
        ktginventaris.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetupOtoLokasiActionPerformed

    private void btnTrackerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrackerActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenelusuranLogin rbpoli = new DlgPenelusuranLogin(this, false);
        rbpoli.isCek();
        rbpoli.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTrackerActionPerformed

    private void btnTindakanRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakanRanapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanRanap perawatan=new DlgJnsPerawatanRanap(null,false);
        perawatan.emptTeks();
        perawatan.isCek();
        perawatan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        perawatan.setLocationRelativeTo(PanelUtama);
        perawatan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTindakanRanapActionPerformed

    private void btnSetupJamInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupJamInapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetKamarInap form = new DlgSetKamarInap(this, false);
        form.tampil();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetupJamInapActionPerformed

    private void btnTarifLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarifLabActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanLab tariflab = new DlgJnsPerawatanLab(this, false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        tariflab.setLocationRelativeTo(PanelUtama);
        tariflab.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTarifLabActionPerformed

    private void btnSetPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetPenjabActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetPenjabLab aplikasi = new DlgSetPenjabLab(this, false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetPenjabActionPerformed

    private void btnSetupRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupRMActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetRM aplikasi = new DlgSetRM(this, false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetupRMActionPerformed

    private void btnSetupTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupTarifActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetTarif aplikasi = new DlgSetTarif(this, false);
        aplikasi.emptTeks();
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetupTarifActionPerformed

    private void btnToolLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolLabActionPerformed
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        isTutup();
        FlayMenu.removeAll();
        FlayMenu.add(btnPermintaanLab);
        FlayMenu.add(btnLaboratorium);        
        FlayMenu.add(btnUTDDonorDarah);
        FlayMenu.add(btnUTDCekalDarah);
        FlayMenu.add(btnUTDPemisahanDarah);
        FlayMenu.add(btnUTDStokDarah);        
        FlayMenu.add(btnUTDPenyerahanDarahDirawat);
        
        btnPermintaanLab.setEnabled(akses.getperiksa_lab());
        btnLaboratorium.setEnabled(akses.getperiksa_lab());        
        btnUTDDonorDarah.setEnabled(akses.getutd_donor());
        btnUTDCekalDarah.setEnabled(akses.getutd_cekal_darah());
        btnUTDPemisahanDarah.setEnabled(akses.getutd_pemisahan_darah());
        btnUTDStokDarah.setEnabled(akses.getutd_stok_darah());        
        btnUTDPenyerahanDarahDirawat.setEnabled(akses.getutd_stok_darah());
        FlayMenu.setVisible(true);
    }//GEN-LAST:event_btnToolLabActionPerformed

    private void MnTarifKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifKamarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiKamar belum = new InformasiKamar(this, true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifKamarActionPerformed

    private void MnPasienRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPasienRanapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiKamarInap informasikamar = new InformasiKamarInap(this, false);
        informasikamar.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        informasikamar.setLocationRelativeTo(PanelUtama);
        informasikamar.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPasienRanapActionPerformed

    private void MnJadwalDokterRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJadwalDokterRalanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiJadwal belum = new InformasiJadwal(this, true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJadwalDokterRalanActionPerformed

    private void MnTarifRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifRalanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiTarifRalan belum = new InformasiTarifRalan(this, true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifRalanActionPerformed

    private void MnTarifLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifLabActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiTarifLab belum = new InformasiTarifLab(this, true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifLabActionPerformed

    private void MnTarifOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifOperasiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiTarifOperasi belum = new InformasiTarifOperasi(this, true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifOperasiActionPerformed

    private void MnTarifRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifRanapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiTarifRanap belum = new InformasiTarifRanap(this, true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifRanapActionPerformed

    private void btnTarifRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarifRadiologiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanRadiologi tarifrad = new DlgJnsPerawatanRadiologi(this, false);
        tarifrad.emptTeks();
        tarifrad.isCek();
        tarifrad.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        tarifrad.setLocationRelativeTo(PanelUtama);
        tarifrad.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTarifRadiologiActionPerformed

    private void MnTarifRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifRadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiTarifRadiologi belum = new InformasiTarifRadiologi(this, false);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifRadActionPerformed

    private void btnToolIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolIGDActionPerformed
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));              
        igd.emptTeks();
        igd.isCek();
        igd.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        igd.setLocationRelativeTo(PanelUtama);
        igd.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnToolIGDActionPerformed

    private void btnSetupEmbalaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupEmbalaseActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetEmbalase ktginventaris = new DlgSetEmbalase(this, false);
        ktginventaris.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        ktginventaris.setLocationRelativeTo(PanelUtama);
        ktginventaris.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetupEmbalaseActionPerformed

    private void btnPengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengeluaranActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPengeluaranHarian pembelian = new DlgPengeluaranHarian(this, false);
        pembelian.emptTeks();
        pembelian.isCek();
        pembelian.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        pembelian.setLocationRelativeTo(PanelUtama);
        pembelian.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPengeluaranActionPerformed

    private void btnToolRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolRadActionPerformed
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        isTutup();
        FlayMenu.removeAll();
        FlayMenu.add(btnPermintaanRadiologi);
        FlayMenu.add(btnPeriksaRadiologi);
        btnPermintaanRadiologi.setEnabled(akses.getperiksa_radiologi());
        btnPeriksaRadiologi.setEnabled(akses.getperiksa_radiologi());
        FlayMenu.setVisible(true);
    }//GEN-LAST:event_btnToolRadActionPerformed

    private void btnSetObatRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetObatRalanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetHargaObatRalan aplikasi = new DlgSetHargaObatRalan(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetObatRalanActionPerformed

    private void btnSetObatRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetObatRanapActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetHargaObatRanap aplikasi = new DlgSetHargaObatRanap(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetObatRanapActionPerformed

    private void btnPenyakitPD3IActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyakitPD3IActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenyakitPd3i aplikasi = new DlgPenyakitPd3i(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPenyakitPD3IActionPerformed

    private void btnSurveilansPD3IActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSurveilansPD3IActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDkkSurveilansPD3I aplikasi = new DlgDkkSurveilansPD3I(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSurveilansPD3IActionPerformed

    private void btnSurveilansRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSurveilansRalanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDkkSurveilansRalan aplikasi = new DlgDkkSurveilansRalan(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSurveilansRalanActionPerformed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDiagnosaPenyakit diagnosa = new DlgDiagnosaPenyakit(null, false);
        diagnosa.tampilDiagStatistik();
        diagnosa.tampilDiagInadrg();
        diagnosa.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        diagnosa.setLocationRelativeTo(PanelUtama);
        diagnosa.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void btnSurveilansRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSurveilansRanapActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDkkSurveilansRanap aplikasi = new DlgDkkSurveilansRanap(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSurveilansRanapActionPerformed

    private void btnPnyTakMenularRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPnyTakMenularRanapActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDkkPenyakitTidakMenularRanap aplikasi = new DlgDkkPenyakitTidakMenularRanap(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPnyTakMenularRanapActionPerformed

    private void btnPnyTakMenularRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPnyTakMenularRalanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDkkPenyakitTidakMenularRalan aplikasi = new DlgDkkPenyakitTidakMenularRalan(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPnyTakMenularRalanActionPerformed

    private void btnKunjunganRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKunjunganRalanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgKunjunganRalan aplikasi = new DlgKunjunganRalan(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        aplikasi.UserValid();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKunjunganRalanActionPerformed

    private void btnRl32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl32ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRl32 aplikasi = new DlgRl32(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl32ActionPerformed

    private void btnRl33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl33ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRl33 aplikasi = new DlgRl33(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl33ActionPerformed

    private void btnRl37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl37ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRl37 aplikasi = new DlgRl37(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl37ActionPerformed

    private void btnRl38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl38ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRl38 aplikasi = new DlgRl38(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl38ActionPerformed

    private void btnBridgingEklaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBridgingEklaimActionPerformed
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        isTutup();
        FlayMenu.removeAll();
        FlayMenu.add(btnBridgingEklaimINACBG);
        FlayMenu.add(btnINACBGjknBelumDiklaim);
        FlayMenu.add(btnKendaliMutuKendaliBiayaINACBG);
        btnBridgingEklaimINACBG.setEnabled(akses.getinacbg_klaim_raza());
        btnINACBGjknBelumDiklaim.setEnabled(akses.getjkn_belum_diproses_klaim());
        btnKendaliMutuKendaliBiayaINACBG.setEnabled(akses.getkendali_mutu_kendali_biaya_inacbg());
        FlayMenu.setVisible(true);
    }//GEN-LAST:event_btnBridgingEklaimActionPerformed

    private void btnSetupNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupNotaActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetNota aplikasi = new DlgSetNota(this, false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetupNotaActionPerformed

    private void MnPenggunaanKamarRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenggunaanKamarRanapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiAnalisaKamin analisakamin = new InformasiAnalisaKamin(this, false);
        analisakamin.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        analisakamin.setLocationRelativeTo(PanelUtama);
        analisakamin.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPenggunaanKamarRanapActionPerformed

    private void btnRl34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl34ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRl34 aplikasi = new DlgRl34(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        aplikasi.fokus();
        aplikasi.tampil();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl34ActionPerformed

    private void btnRl36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl36ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRl36 aplikasi = new DlgRl36(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl36ActionPerformed

    private void btnakun_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnakun_bayarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgAkunBayar feeperiksaralan = new DlgAkunBayar(this, false);
        feeperiksaralan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        feeperiksaralan.setLocationRelativeTo(PanelUtama);
        feeperiksaralan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnakun_bayarActionPerformed

    private void btnbayar_pemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbayar_pemesananActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBayarPemesanan bayarpesan = new DlgBayarPemesanan(this, false);
        bayarpesan.tampil();
        bayarpesan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        bayarpesan.setLocationRelativeTo(PanelUtama);
        bayarpesan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnbayar_pemesananActionPerformed

    private void btnPemasukanLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemasukanLainActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPemasukanLain aplikasi = new DlgPemasukanLain(this, false);
        aplikasi.isCek();
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPemasukanLainActionPerformed

    private void btnPengaturanRekeningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengaturanRekeningActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPengaturanRekening aplikasi = new DlgPengaturanRekening(this, false);
        aplikasi.isCek();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPengaturanRekeningActionPerformed

    private void btnClosingKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClosingKasirActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgClosingKasir ckas = new DlgClosingKasir(this, false);
        ckas.isCek();
        ckas.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        ckas.setLocationRelativeTo(PanelUtama);
        ckas.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnClosingKasirActionPerformed

    private void btnKeterlambatanPresensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeterlambatanPresensiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetKeterlambatan keterlambatan = new DlgSetKeterlambatan(this, false);
        keterlambatan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        keterlambatan.setLocationRelativeTo(PanelUtama);
        keterlambatan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKeterlambatanPresensiActionPerformed

    private void btnSetHargaKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetHargaKamarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetHargaKamar hargakamar = new DlgSetHargaKamar(this, false);
        hargakamar.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        hargakamar.setLocationRelativeTo(PanelUtama);
        hargakamar.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetHargaKamarActionPerformed

    private void btnCekBPJSNikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSNikActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekNIK2 form = new BPJSCekNIK2(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSNikActionPerformed

    private void btnCekBPJSKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSKartuActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekKartu form = new BPJSCekKartu(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSKartuActionPerformed

    private void btnKunjunganRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKunjunganRanapActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgKunjunganRanap aplikasi = new DlgKunjunganRanap(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKunjunganRanapActionPerformed

    private void btnCekBPJSNomorRujukanPCareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSNomorRujukanPCareActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekNoRujukanPCare form = new BPJSCekNoRujukanPCare(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSNomorRujukanPCareActionPerformed

    private void btnICD9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnICD9ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgICD9 aplikasi = new DlgICD9(this, false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnICD9ActionPerformed

    private void btnJurnalHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJurnalHarianActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJurnalHarian jh = new DlgJurnalHarian(this, false);
        jh.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        jh.setLocationRelativeTo(PanelUtama);
        jh.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnJurnalHarianActionPerformed

    private void btnCekBPJSDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSDiagnosaActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiPenyakit form = new BPJSCekReferensiPenyakit(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSDiagnosaActionPerformed

    private void btnCekBPJSPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSPoliActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiPoli form = new BPJSCekReferensiPoli(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSPoliActionPerformed

    private void btnPiutangBelumLunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPiutangBelumLunasActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPiutangBelumLunas rbpaketbhp = new DlgPiutangBelumLunas(this, false);
        rbpaketbhp.tampil();
        rbpaketbhp.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        rbpaketbhp.setLocationRelativeTo(PanelUtama);
        rbpaketbhp.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPiutangBelumLunasActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                DlgHome.setSize(PanelUtama.getWidth() - 45, PanelUtama.getHeight() - 45);
                Panelmenu.repaint();
                DlgHome.setLocationRelativeTo(PanelUtama);
            }
        }
    }//GEN-LAST:event_formComponentResized

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.setLocationRelativeTo(PanelUtama);
                win.toFront();
            }
        }

        setToolbar();
    }//GEN-LAST:event_formComponentMoved

    private void BtnToolJualObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolJualObatActionPerformed
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        isTutup();
        FlayMenu.removeAll();
        FlayMenu.add(btnInputPenjualan);
        FlayMenu.add(btnDataPenjualan);
        FlayMenu.add(btnDataPenyerahanDarah);
        FlayMenu.add(btnUTDPenyerahanDarah);
        FlayMenu.add(btnDaftarPermintaanResep);
        btnInputPenjualan.setEnabled(akses.getpenjualan_obat());
        btnDataPenjualan.setEnabled(akses.getpenjualan_obat());
        btnDataPenyerahanDarah.setEnabled(akses.getutd_penyerahan_darah());
        btnUTDPenyerahanDarah.setEnabled(akses.getpemasukan_lain());
        btnDaftarPermintaanResep.setEnabled(akses.getresep_dokter());
        FlayMenu.setVisible(true);
    }//GEN-LAST:event_BtnToolJualObatActionPerformed

    private void PanelWallMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelWallMouseMoved
        setToolbar();
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.setLocationRelativeTo(PanelUtama);
                win.toFront();
            }
        }

    }//GEN-LAST:event_PanelWallMouseMoved

    private void btnCekBPJSFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSFaskesActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiFaskes form = new BPJSCekReferensiFaskes(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSFaskesActionPerformed

    private void btnBPJSSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSSEPActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSDataSEP form = new BPJSDataSEP(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.emptTeks();
        form.isCek();
        form.cekLAYAN();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSSEPActionPerformed

    private void btnTarifUtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarifUtdActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanUTD tarifutd = new DlgJnsPerawatanUTD(this, false);
        tarifutd.emptTeks();
        tarifutd.isCek();
        tarifutd.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        tarifutd.setLocationRelativeTo(PanelUtama);
        tarifutd.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTarifUtdActionPerformed

    private void btnPengambilanUTD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengambilanUTD2ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPengambilanUTD form = new DlgCariPengambilanUTD(this, false);
        form.setHapus();
        form.tampil();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPengambilanUTD2ActionPerformed

    private void btnUTDMedisRusakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDMedisRusakActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDMedisRusak form = new UTDMedisRusak(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDMedisRusakActionPerformed

    private void btnPengambilanPenunjangUTD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengambilanPenunjangUTD2ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPengambilanPenunjangUTD form = new DlgCariPengambilanPenunjangUTD(this, false);
        form.setHapus();
        form.tampil();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPengambilanPenunjangUTD2ActionPerformed

    private void btnUTDPenunjangRusakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDPenunjangRusakActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDPenunjangRusak form = new UTDPenunjangRusak(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDPenunjangRusakActionPerformed

    private void btnUTDDonorDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDDonorDarahActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDDonor form = new UTDDonor(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDDonorDarahActionPerformed

    private void btnMonitoringKlaimBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitoringKlaimBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSMonitoringKlaim form = new BPJSMonitoringKlaim(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.emptTeks();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMonitoringKlaimBPJSActionPerformed

    private void btnUTDCekalDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDCekalDarahActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDCekalDarah form = new UTDCekalDarah(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDCekalDarahActionPerformed

    private void btnUTDKomponenDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDKomponenDarahActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDKomponenDarah form = new UTDKomponenDarah(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDKomponenDarahActionPerformed

    private void btnUTDStokDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDStokDarahActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDStokDarah form = new UTDStokDarah(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDStokDarahActionPerformed

    private void btnUTDPemisahanDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDPemisahanDarahActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDPemisahanDarah form = new UTDPemisahanDarah(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDPemisahanDarahActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setToolbar();
        cekVersi();
        
//        if (sttsFileSIMRS.equals("file simrs update")) {
//            String versi = Sequel.cariIsi("select versi_update from history_update ORDER BY tgl_update desc, jam_update desc limit 1");
//            Sequel.menyimpanIgnore("history_aplikasi", "'" + ipKomputer + "','" + versi + "','SIMRS',"
//                    + "'" + nipLogin + "','" + Sequel.cariIsi("select now()") + "'", "Update versi SIMRS");
//            Valid.bikinFileTxt(versi, Sequel.cariFolderVersi(), "conf_versi.txt");
//            Tversi.setText(versi);
//            lbl_update.setText("Modified by. UNIT SIMRS RAZA - Vs. " + versi + " [Activated]");
//        } else {
//            lbl_update.setText("Modified by. UNIT SIMRS RAZA - Vs. " + Tversi.getText() + " [Activated]");
//        }
    }//GEN-LAST:event_formWindowOpened

    private void btnRincianPiutangPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRincianPiutangPasienActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRincianPiutangPasien billing = new DlgRincianPiutangPasien(this, false);
        billing.tampil();
        billing.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        billing.setLocationRelativeTo(PanelUtama);
        billing.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRincianPiutangPasienActionPerformed

    private void btnUTDPenyerahanDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDPenyerahanDarahActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDPenyerahanDarah form = new UTDPenyerahanDarah(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDPenyerahanDarahActionPerformed

    private void btnHutangObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHutangObatActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHutangObatBelumLunas form = new DlgHutangObatBelumLunas(this, false);
        form.tampil();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnHutangObatActionPerformed

    private void btnInputPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputPenjualanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenjualan penjualan = new DlgPenjualan(this, false);
        penjualan.isCek();
        penjualan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        penjualan.setLocationRelativeTo(PanelUtama);
        penjualan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnInputPenjualanActionPerformed

    private void btnDataPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenjualanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPenjualan penjualan = new DlgCariPenjualan(this, false);
        penjualan.emptTeks();
        penjualan.isCek();
        penjualan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        penjualan.setLocationRelativeTo(PanelUtama);
        penjualan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataPenjualanActionPerformed

    private void btnDataPenyerahanDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenyerahanDarahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDCariPenyerahanDarah carijual = new UTDCariPenyerahanDarah(null, false);
        carijual.emptTeks();
        carijual.isCek();
        carijual.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        carijual.setLocationRelativeTo(PanelUtama);
        carijual.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataPenyerahanDarahActionPerformed

    private void btnSensusHarianPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSensusHarianPoliActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSensusHarianPoli aplikasi = new DlgSensusHarianPoli(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        aplikasi.UserValid();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSensusHarianPoliActionPerformed

    private void btnRl4aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl4aActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRL4A aplikasi = new DlgRL4A(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl4aActionPerformed

    private void btnAplicareReferensiKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicareReferensiKamarActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        AplicareCekReferensiKamar form = new AplicareCekReferensiKamar(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnAplicareReferensiKamarActionPerformed

    private void btnAplicareKetersediaanKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicareKetersediaanKamarActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        AplicareKetersediaanKamar form = new AplicareKetersediaanKamar(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnAplicareKetersediaanKamarActionPerformed

    private void btnInaCBGCoderNIKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInaCBGCoderNIKActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        INACBGCoderNIK inacbg = new INACBGCoderNIK(this, false);
        inacbg.emptTeks();
        inacbg.isCek();
        inacbg.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        inacbg.setLocationRelativeTo(PanelUtama);
        inacbg.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnInaCBGCoderNIKActionPerformed

    private void btnAkunPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAkunPiutangActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgAkunPiutang form = new DlgAkunPiutang(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnAkunPiutangActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        if (ChkInput.isSelected() == true) {
            cmbMenu.setVisible(true);
            cmbMenu.requestFocus();
            TCari.setVisible(false);
            button2.setVisible(false);
        } else {
            cmbMenu.setVisible(false);
            TCari.setVisible(true);
            button2.setVisible(true);
            TCari.requestFocus();            
        }
        isTampil();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isTampil();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void btnPiutangPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPiutangPerCaraBayarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPiutangPercaraBayar rbpaketbhp = new DlgPiutangPercaraBayar(this, false);
        rbpaketbhp.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        rbpaketbhp.setLocationRelativeTo(PanelUtama);
        rbpaketbhp.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPiutangPerCaraBayarActionPerformed

    private void btnLamaPelayananRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamaPelayananRalanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPelayananRalan aplikasi = new DlgPelayananRalan(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        aplikasi.UserValid();
        aplikasi.tampil();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnLamaPelayananRalanActionPerformed

    private void btnCatatanPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatatanPasienActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgLhtCatatanPasien aplikasi = new DlgLhtCatatanPasien(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCatatanPasienActionPerformed

    private void btnRl4bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl4bActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRL4B aplikasi = new DlgRL4B(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl4bActionPerformed

    private void btnRl4asebabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl4asebabActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRL4ASebab aplikasi = new DlgRL4ASebab(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl4asebabActionPerformed

    private void btnRl4bsebabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRl4bsebabActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRL4BSebab aplikasi = new DlgRL4BSebab(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRl4bsebabActionPerformed

    private void btnDataHAIsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataHAIsActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDataHAIs aplikasi = new DlgDataHAIs(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.isCek();
        aplikasi.tampil();
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataHAIsActionPerformed

    private void btnHarianHAIsRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHarianHAIsRSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHarianHAIs aplikasi = new DlgHarianHAIs(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnHarianHAIsRSActionPerformed

    private void btnBulananHAIsRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBulananHAIsRSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBulananHAIs aplikasi = new DlgBulananHAIs(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBulananHAIsRSActionPerformed

    private void btnPerusahaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerusahaanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPerusahaan aplikasi = new DlgPerusahaan(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.isCek();
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPerusahaanActionPerformed

    private void btnLamaPelayananApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamaPelayananApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPelayananApotek aplikasi = new DlgPelayananApotek(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnLamaPelayananApotekActionPerformed

    private void btnGrafikKunjunganPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikKunjunganPoliActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikKunjunganPoli aplikasi = new GrafikKunjunganPoli(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikKunjunganPoliActionPerformed

    private void btnGrafikKunjunganPerDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikKunjunganPerDokterActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikKunjunganPerDokter aplikasi = new GrafikKunjunganPerDokter(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikKunjunganPerDokterActionPerformed

    private void btnGrafikKunjunganPerPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikKunjunganPerPekerjaanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikKunjunganPerPekerjaan aplikasi = new GrafikKunjunganPerPekerjaan(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikKunjunganPerPekerjaanActionPerformed

    private void btnGrafikKunjunganPerPendidikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikKunjunganPerPendidikanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikKunjunganPerPendidikan aplikasi = new GrafikKunjunganPerPendidikan(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikKunjunganPerPendidikanActionPerformed

    private void btnGrafikKunjunganPerTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikKunjunganPerTahunActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikKunjunganPerTahun aplikasi = new GrafikKunjunganPerTahun(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikKunjunganPerTahunActionPerformed

    private void btnPnyMenularRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPnyMenularRanapActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDkkPenyakitMenularRanap aplikasi = new DlgDkkPenyakitMenularRanap(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPnyMenularRanapActionPerformed

    private void btnPnyMenularRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPnyMenularRalanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDkkPenyakitMenularRalan aplikasi = new DlgDkkPenyakitMenularRalan(this, false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPnyMenularRalanActionPerformed

    private void btnGrafikKunjunganPerBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikKunjunganPerBulanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikKunjunganPerBulan aplikasi = new GrafikKunjunganPerBulan(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikKunjunganPerBulanActionPerformed

    private void btnGrafikKunjunganPerTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikKunjunganPerTanggalActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikKunjunganPerTanggal aplikasi = new GrafikKunjunganPerTanggal(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikKunjunganPerTanggalActionPerformed

    private void btnGrafikDemografiRegistrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikDemografiRegistrasiActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikDemografiRegistrasi aplikasi = new GrafikDemografiRegistrasi(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikDemografiRegistrasiActionPerformed

    private void btnGrafikStatusRegPerTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegPerTahunActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusRegPerTahun aplikasi = new GrafikStatusRegPerTahun(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegPerTahunActionPerformed

    private void btnGrafikStatusRegPerTahun2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegPerTahun2ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusRegPerTahun2 aplikasi = new GrafikStatusRegPerTahun2(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegPerTahun2ActionPerformed

    private void btnGrafikStatusRegPerBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegPerBulanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusRegPerBulan aplikasi = new GrafikStatusRegPerBulan(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegPerBulanActionPerformed

    private void btnGrafikStatusRegPerBulan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegPerBulan2ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusRegPerBulan2 aplikasi = new GrafikStatusRegPerBulan2(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegPerBulan2ActionPerformed

    private void btnGrafikStatusRegPerTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegPerTanggalActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusRegPerTanggal aplikasi = new GrafikStatusRegPerTanggal(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegPerTanggalActionPerformed

    private void btnGrafikStatusRegPerTanggal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegPerTanggal2ActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusRegPerTanggal2 aplikasi = new GrafikStatusRegPerTanggal2(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegPerTanggal2ActionPerformed

    private void btnGrafikStatusRegBatalPerTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegBatalPerTahunActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusBatalRegPerTahun aplikasi = new GrafikStatusBatalRegPerTahun(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegBatalPerTahunActionPerformed

    private void btnGrafikStatusRegBatalPerBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegBatalPerBulanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusBatalRegPerBulan aplikasi = new GrafikStatusBatalRegPerBulan(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegBatalPerBulanActionPerformed

    private void btnCekPCareDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekPCareDiagnosaActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        PCareCekReferensiPenyakit form = new PCareCekReferensiPenyakit(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekPCareDiagnosaActionPerformed

    private void btnGrafikStatusRegBatalPerTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikStatusRegBatalPerTanggalActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        GrafikStatusBatalRegPerTanggal aplikasi = new GrafikStatusBatalRegPerTanggal(this, true);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGrafikStatusRegBatalPerTanggalActionPerformed

    private void btnSKDPbpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSKDPbpjsActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgSKDPBPJS form = new DlgSKDPBPJS(null, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSKDPbpjsActionPerformed

    private void btnRujukKeluarVclaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRujukKeluarVclaimActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        BPJSRujukanKeluar form = new BPJSRujukanKeluar(null, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.tampil();
        form.isCek();
        form.TCari.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRujukKeluarVclaimActionPerformed

    private void btnBPJScekRiwayatRujukanPcareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJScekRiwayatRujukanPcareActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        BPJSCekRiwayatRujukanPCare form = new BPJSCekRiwayatRujukanPCare(null, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJScekRiwayatRujukanPcareActionPerformed

    private void btnCekBPJSRiwayatRujukanRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSRiwayatRujukanRSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekRiwayatRujukanRS form = new BPJSCekRiwayatRujukanRS(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSRiwayatRujukanRSActionPerformed

    private void btnCekBPJSRujukanKartuRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSRujukanKartuRSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekRujukanKartuRS form = new BPJSCekRujukanKartuRS(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSRujukanKartuRSActionPerformed

    private void btnCekBPJSTanggalRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSTanggalRujukanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekTanggalRujukan form = new BPJSCekTanggalRujukan(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSTanggalRujukanActionPerformed

    private void btnCekBPJSNomorRujukanRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSNomorRujukanRSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekNoRujukanRS form = new BPJSCekNoRujukanRS(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSNomorRujukanRSActionPerformed

    private void btnCekBPJSRujukanKartuPCareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSRujukanKartuPCareActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekRujukanKartuPCare form = new BPJSCekRujukanKartuPCare(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSRujukanKartuPCareActionPerformed

    private void btnCekReferensiKelasRawatBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiKelasRawatBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiKelasRawat form = new BPJSCekReferensiKelasRawat(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.Kelas.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiKelasRawatBPJSActionPerformed

    private void btnCekReferensiProsedurBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiProsedurBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiProsedur form = new BPJSCekReferensiProsedur(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiProsedurBPJSActionPerformed

    private void btnCekReferensiDokterDPJPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiDokterDPJPBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiDokterDPJP form = new BPJSCekReferensiDokterDPJP(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiDokterDPJPBPJSActionPerformed

    private void btnCekReferensiDokterBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiDokterBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiDokter form = new BPJSCekReferensiDokter(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiDokterBPJSActionPerformed

    private void btnCekReferensiSpesialistikBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiSpesialistikBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiSpesialistik form = new BPJSCekReferensiSpesialistik(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiSpesialistikBPJSActionPerformed

    private void btnCekReferensiRuangRawatBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiRuangRawatBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiRuangRawat form = new BPJSCekReferensiRuangRawat(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiRuangRawatBPJSActionPerformed

    private void btnCekReferensiCaraKeluarBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiCaraKeluarBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiCaraKeluar form = new BPJSCekReferensiCaraKeluar(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiCaraKeluarBPJSActionPerformed

    private void btnCekReferensiPascaPulangBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiPascaPulangBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiPascaPulang form = new BPJSCekReferensiPascaPulang(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiPascaPulangBPJSActionPerformed

    private void btnCekReferensiPropinsiBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiPropinsiBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiPropinsi form = new BPJSCekReferensiPropinsi(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.fokus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiPropinsiBPJSActionPerformed

    private void btnCekReferensiKabupatenBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiKabupatenBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiKabupaten form = new BPJSCekReferensiKabupaten(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiKabupatenBPJSActionPerformed

    private void btnCekReferensiKecamatanBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiKecamatanBPJSActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiKecamatan form = new BPJSCekReferensiKecamatan(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiKecamatanBPJSActionPerformed

    private void btnJumlahPorsiDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJumlahPorsiDietActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJumlahPorsiDiet aplikasi = new DlgJumlahPorsiDiet(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnJumlahPorsiDietActionPerformed

    private void btnJumlahMacamDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJumlahMacamDietActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJumlahMacamDiet aplikasi = new DlgJumlahMacamDiet(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnJumlahMacamDietActionPerformed

    private void btnMasterFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterFaskesActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterFaskes aplikasi = new DlgMasterFaskes(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.tampil();
        aplikasi.emptTeks();
        aplikasi.TCari.requestFocus();
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterFaskesActionPerformed

    private void MnTarifINACBGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifINACBGActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiTarifINACBG belum = new InformasiTarifINACBG(this, true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifINACBGActionPerformed

    private void MnTelusurKunjunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTelusurKunjunganActionPerformed
        if (akses.getkode().equals("Admin Utama") || (akses.gettelusurpasien())) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
            DlgHome.dispose();
            InformasiTelusurKunjunganPasien telusur = new InformasiTelusurKunjunganPasien(this, true);
            telusur.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
            telusur.setLocationRelativeTo(PanelUtama);
            telusur.setVisible(true);
            telusur.TCari.setText("");
            telusur.TCari.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Hanya untuk petugas yang memiliki hak akses saja bisa membuka fitur ini...!!!!");
        }
    }//GEN-LAST:event_MnTelusurKunjunganActionPerformed

    private void btnCekSisruteFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekSisruteFaskesActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SisruteCekReferensiFaskes form = new SisruteCekReferensiFaskes(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.tcari.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekSisruteFaskesActionPerformed

    private void btnCekSisruteAlasanRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekSisruteAlasanRujukActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SisruteCekReferensiAlasanRujuk form = new SisruteCekReferensiAlasanRujuk(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.tcari.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekSisruteAlasanRujukActionPerformed

    private void btnCekSisruteDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekSisruteDiagnosaActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SisruteCekReferensiDiagnosa form = new SisruteCekReferensiDiagnosa(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.tcari.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekSisruteDiagnosaActionPerformed

    private void btnRujukanMasukSisruteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRujukanMasukSisruteActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SisruteRujukanMasukan form = new SisruteRujukanMasukan(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        form.TCari.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRujukanMasukSisruteActionPerformed

    private void btnRujukanKeluarSisruteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRujukanKeluarSisruteActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SisruteRujukanKeluar form = new SisruteRujukanKeluar(this, false);
        form.isCek();
        form.tutupInput();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRujukanKeluarSisruteActionPerformed

    private void btnPasienPonekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienPonekActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgInputPonek form = new DlgInputPonek(this, false);
        form.emptTeks();
        form.tampil();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPasienPonekActionPerformed

    private void btnHarianHAIsRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHarianHAIsRanapActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHarianHAIsRanap aplikasi = new DlgHarianHAIsRanap(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnHarianHAIsRanapActionPerformed

    private void btnHarianHAIsRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHarianHAIsRalanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHarianHAIsRalan aplikasi = new DlgHarianHAIsRalan(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnHarianHAIsRalanActionPerformed

    private void btnBulananHAIsRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBulananHAIsRanapActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBulananHAIsRanap aplikasi = new DlgBulananHAIsRanap(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBulananHAIsRanapActionPerformed

    private void btnBulananHAIsRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBulananHAIsRalanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBulananHAIsRalan aplikasi = new DlgBulananHAIsRalan(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBulananHAIsRalanActionPerformed

    private void ket_updateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ket_updateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ket_updateKeyPressed

    private void btnMasterMasalahKeperawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterMasalahKeperawatanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterMasalahKeperawatan form = new MasterMasalahKeperawatan(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterMasalahKeperawatanActionPerformed

    private void btnMasterCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterCaraBayarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenanggungJawab form = new DlgPenanggungJawab(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterCaraBayarActionPerformed

    private void btnDataPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPersalinanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPasienPersalinan form = new DlgPasienPersalinan(this, false);
        form.isCek();
        form.emptPersalinan();
        form.ChkInput.setSelected(false);
        form.isForm();
        form.tampil();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataPersalinanActionPerformed

    private void btnPasienCoronaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienCoronaActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        CoronaPasien form = new CoronaPasien(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPasienCoronaActionPerformed

    private void btnDiagnosaPasienCoronaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaPasienCoronaActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        CoronaDiagnosa form = new CoronaDiagnosa(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDiagnosaPasienCoronaActionPerformed

    private void btnPerawatanPasienCoronaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerawatanPasienCoronaActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        INACBGPerawatanCorona form = new INACBGPerawatanCorona(this, false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPerawatanPasienCoronaActionPerformed

    private void btnRencanaKontrolBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRencanaKontrolBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSSuratKontrol surkon = new BPJSSuratKontrol(null, false);
        surkon.isCek();
        surkon.emptTeks();
        surkon.TCari.setText("");
        surkon.ChkInput.setSelected(false);
        surkon.isForm();
        surkon.tampil();
        surkon.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        surkon.setLocationRelativeTo(PanelUtama);
        surkon.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRencanaKontrolBPJSActionPerformed

    private void btnBridgingEklaimINACBGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBridgingEklaimINACBGActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        INACBGDaftarKlaim eklaimRZ = new INACBGDaftarKlaim(this, false);
        eklaimRZ.isCek();
        eklaimRZ.emptTeks();
        eklaimRZ.Chktgl.setSelected(true);
        eklaimRZ.Chktgl.setText("Tgl. Klaim : ");
        eklaimRZ.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        eklaimRZ.setLocationRelativeTo(PanelUtama);
        eklaimRZ.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBridgingEklaimINACBGActionPerformed

    private void btnPengajuanKlaimINACBGrzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengajuanKlaimINACBGrzActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        PengajuanKlaimINACBGrz ajukan = new PengajuanKlaimINACBGrz(this, false);
        ajukan.isCek();
        ajukan.emptTeksJKN();
        ajukan.emptTeksLAINNYA();
        ajukan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        ajukan.setLocationRelativeTo(PanelUtama);
        ajukan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPengajuanKlaimINACBGrzActionPerformed

    private void btnINACBGjknBelumDiklaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnINACBGjknBelumDiklaimActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        INACBGjknBelumDiklaim jknbelum = new INACBGjknBelumDiklaim(this, false);
        jknbelum.isCek();
        jknbelum.emptText();
        jknbelum.tampil();
        jknbelum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        jknbelum.setLocationRelativeTo(PanelUtama);
        jknbelum.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnINACBGjknBelumDiklaimActionPerformed

    private void btnInputKodeICDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputKodeICDActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgInputKodeICD input_kode = new DlgInputKodeICD(this, false);
        input_kode.ChkInput.setSelected(true);
        input_kode.isForm();
        input_kode.isCek();
        input_kode.emptTeks();
        input_kode.tampil();
        input_kode.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        input_kode.setLocationRelativeTo(PanelUtama);
        input_kode.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnInputKodeICDActionPerformed

    private void btnKendaliMutuKendaliBiayaINACBGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKendaliMutuKendaliBiayaINACBGActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        KendaliMutuKendaliBiayaJKN kendaliJKN = new KendaliMutuKendaliBiayaJKN(this, false);
        kendaliJKN.emptTeks();        
        kendaliJKN.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        kendaliJKN.setLocationRelativeTo(PanelUtama);
        kendaliJKN.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKendaliMutuKendaliBiayaINACBGActionPerformed

    private void btnCekSEPInternalBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekSEPInternalBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekSEPInternal sepInternal = new BPJSCekSEPInternal(this, false);
        sepInternal.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        sepInternal.setLocationRelativeTo(PanelUtama);
        sepInternal.setVisible(true);
        sepInternal.NoSEP.setText("");
        sepInternal.NoSEP.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekSEPInternalBPJSActionPerformed

    private void btnSPRIbpjsVclaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPRIbpjsVclaimActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSSPRI spri = new BPJSSPRI(null, false);
        spri.isCek();
        spri.emptTeks();
        spri.TCari.setText("");
        spri.ChkInput.setSelected(false);
        spri.isForm();
        spri.tampil();
        spri.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        spri.setLocationRelativeTo(PanelUtama);
        spri.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSPRIbpjsVclaimActionPerformed

    private void btnCekFingerPrinBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekFingerPrinBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekFingerPrin finger = new BPJSCekFingerPrin(this, false);
        finger.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        finger.setLocationRelativeTo(PanelUtama);
        finger.setVisible(true);
        finger.tglPel.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekFingerPrinBPJSActionPerformed

    private void btnListSpesialistikRujukanBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListSpesialistikRujukanBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSListSpesialistikRujukan list = new BPJSListSpesialistikRujukan(this, false);
        list.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        list.setLocationRelativeTo(PanelUtama);
        list.setVisible(true);
        list.tglRujukan.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnListSpesialistikRujukanBPJSActionPerformed

    private void btnListSaranaRujukanBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListSaranaRujukanBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSListSaranaRujukan sarana = new BPJSListSaranaRujukan(this, false);
        sarana.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        sarana.setLocationRelativeTo(PanelUtama);
        sarana.setVisible(true);
        sarana.btnPPKRujukan.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnListSaranaRujukanBPJSActionPerformed

    private void btnProgramPRBBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgramPRBBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSProgramPRB prb = new BPJSProgramPRB(this, false);
        prb.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        prb.setLocationRelativeTo(PanelUtama);
        prb.setVisible(true);
        prb.isCek();
        prb.ChkInput.setSelected(false);
        prb.isForm();
        prb.emptTeks();
        prb.tampil();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnProgramPRBBPJSActionPerformed

    private void btnCekReferensiDiagnosaPRBBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiDiagnosaPRBBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiDiagnosaPRB diagnosaPRB = new BPJSCekReferensiDiagnosaPRB(this, false);
        diagnosaPRB.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        diagnosaPRB.setLocationRelativeTo(PanelUtama);
        diagnosaPRB.setVisible(true);
        diagnosaPRB.TCari.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiDiagnosaPRBBPJSActionPerformed

    private void btnCekReferensiObatPRBBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiObatPRBBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiObatPRB obatPRB = new BPJSCekReferensiObatPRB(this, false);
        obatPRB.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        obatPRB.setLocationRelativeTo(PanelUtama);
        obatPRB.setVisible(true);
        obatPRB.TCari.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiObatPRBBPJSActionPerformed

    private void btnDataNomorSuratKontrolBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataNomorSuratKontrolBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSDataNomorSuratKontrol nomor = new BPJSDataNomorSuratKontrol(this, false);
        nomor.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        nomor.setLocationRelativeTo(PanelUtama);
        nomor.setVisible(true);
        nomor.isCek();
        nomor.TCari.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataNomorSuratKontrolBPJSActionPerformed

    private void btnHistoriPelayananPesertaBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriPelayananPesertaBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSHistoriPelayanan histori = new BPJSHistoriPelayanan(this, false);
        histori.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        histori.setLocationRelativeTo(PanelUtama);
        histori.setVisible(true);
        histori.NoKartu.requestFocus();
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnHistoriPelayananPesertaBPJSActionPerformed

    private void btnKlaimJaminanJasaRaharjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKlaimJaminanJasaRaharjaActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekKlaimJasaRaharja raharja = new BPJSCekKlaimJasaRaharja(this, false);
        raharja.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        raharja.setLocationRelativeTo(PanelUtama);
        raharja.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKlaimJaminanJasaRaharjaActionPerformed

    private void btnDataSuplesiJasaRaharjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataSuplesiJasaRaharjaActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekSuplesiJasaRaharja suplesi = new BPJSCekSuplesiJasaRaharja(this, false);
        suplesi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        suplesi.setLocationRelativeTo(PanelUtama);
        suplesi.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataSuplesiJasaRaharjaActionPerformed

    private void btnDataSEPIndukKLLJasaRaharjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataSEPIndukKLLJasaRaharjaActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekDataIndukKecelakaan kll = new BPJSCekDataIndukKecelakaan(this, false);
        kll.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        kll.setLocationRelativeTo(PanelUtama);
        kll.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataSEPIndukKLLJasaRaharjaActionPerformed

    private void btnCekReferensiPoliHFISBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiPoliHFISBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiPoliHFIS polihfis = new BPJSCekReferensiPoliHFIS(this, false);
        polihfis.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        polihfis.setLocationRelativeTo(PanelUtama);
        polihfis.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiPoliHFISBPJSActionPerformed

    private void btnCekReferensiJadwalHFISBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiJadwalHFISBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiJadwalHFIS jadwalhfis = new BPJSCekReferensiJadwalHFIS(this, false);
        jadwalhfis.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        jadwalhfis.setLocationRelativeTo(PanelUtama);
        jadwalhfis.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiJadwalHFISBPJSActionPerformed

    private void btnCekReferensiDokterHFISBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiDokterHFISBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiDokterHFIS dokterhfis = new BPJSCekReferensiDokterHFIS(this, false);
        dokterhfis.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        dokterhfis.setLocationRelativeTo(PanelUtama);
        dokterhfis.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiDokterHFISBPJSActionPerformed

    private void btnCekReferensiPendaftaranMobileJKNBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiPendaftaranMobileJKNBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MobileJKNReferensiPendaftaran mobilejkndaftar = new MobileJKNReferensiPendaftaran(this, false);
        mobilejkndaftar.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        mobilejkndaftar.setLocationRelativeTo(PanelUtama);
        mobilejkndaftar.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiPendaftaranMobileJKNBPJSActionPerformed

    private void btnCekReferensiBatalDaftarMobileJKNBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekReferensiBatalDaftarMobileJKNBPJSActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MobileJKNPembatalanPendaftaran mobilejknbatal = new MobileJKNPembatalanPendaftaran(this, false);
        mobilejknbatal.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        mobilejknbatal.setLocationRelativeTo(PanelUtama);
        mobilejknbatal.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekReferensiBatalDaftarMobileJKNBPJSActionPerformed

    private void btnKemenkesSITBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKemenkesSITBActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDataTB aplikasi = new DlgDataTB(this, false);
        aplikasi.emptTeks();
        aplikasi.isCek();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKemenkesSITBActionPerformed

    private void btnMasterDTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterDTDActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterDTD dtd = new DlgMasterDTD(this, false);
        dtd.emptTeks();
        dtd.isCek();
        dtd.ChkInput.setSelected(false);
        dtd.isForm();
        dtd.TabStatus.setSelectedIndex(0);
        dtd.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        dtd.setLocationRelativeTo(PanelUtama);
        dtd.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterDTDActionPerformed

    private void btnIkhtisarPerawatanHIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIkhtisarPerawatanHIVActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIkhtisarPerawatanHIVart hiv = new DlgIkhtisarPerawatanHIVart(this, false);
        hiv.isCek();
        hiv.emptText();
        hiv.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        hiv.setLocationRelativeTo(PanelUtama);
        hiv.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnIkhtisarPerawatanHIVActionPerformed

    private void btnKemenkesKankerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKemenkesKankerActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDataKanker aplikasi = new DlgDataKanker(this, false);
        aplikasi.emptTeks();
        aplikasi.isCek();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKemenkesKankerActionPerformed

    private void btnSetingBridgingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetingBridgingActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetBridging setbrid = new DlgSetBridging(this, false);
        setbrid.tampil();
        setbrid.emptTeks();
        setbrid.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        setbrid.setLocationRelativeTo(PanelUtama);
        setbrid.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSetingBridgingActionPerformed

    private void btnRekamPsikologisDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekamPsikologisDewasaActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRekamPsikologisDewasa aplikasi = new DlgRekamPsikologisDewasa(this, false);
        aplikasi.isCek();
        aplikasi.emptText();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRekamPsikologisDewasaActionPerformed

    private void btnMasterKeluhanPsikologisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterKeluhanPsikologisActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterKeluhanPsikologis psikologis = new DlgMasterKeluhanPsikologis(this, false);
        psikologis.cekKategori.setText("");
        psikologis.isCek();
        psikologis.emptTeks();        
        psikologis.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        psikologis.setLocationRelativeTo(PanelUtama);
        psikologis.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterKeluhanPsikologisActionPerformed

    private void edAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edAdminKeyPressed
        Valid.pindah(evt, BtnCancel, edPwd);
    }//GEN-LAST:event_edAdminKeyPressed

    private void btnMasterRencanaTritmenPsikologisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterRencanaTritmenPsikologisActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterRencanaTritmenPsikologi psikologis = new DlgMasterRencanaTritmenPsikologi(this, false);
        psikologis.isCek();
        psikologis.emptTeks();        
        psikologis.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        psikologis.setLocationRelativeTo(PanelUtama);
        psikologis.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterRencanaTritmenPsikologisActionPerformed

    private void btnRekamPsikologisAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekamPsikologisAnakActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRekamPsikologisAnak aplikasi = new DlgRekamPsikologisAnak(this, false);
        aplikasi.isCek();
        aplikasi.emptText();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRekamPsikologisAnakActionPerformed

    private void btnRekamPsikologiPerkawinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekamPsikologiPerkawinanActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRekamPsikologisPerkawinan aplikasi = new DlgRekamPsikologisPerkawinan(this, false);
        aplikasi.isCek();
        aplikasi.emptText();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnRekamPsikologiPerkawinanActionPerformed

    private void btnMasterKasusPersalinanDinkesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterKasusPersalinanDinkesActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterDataDinkes aplikasi = new MasterDataDinkes(this, false);        
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterKasusPersalinanDinkesActionPerformed

    private void btnKasusPersalinanDinkesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKasusPersalinanDinkesActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDinkesPersalinan aplikasi = new DlgDinkesPersalinan(this, false);        
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.isCek();
        aplikasi.ChkInput.setSelected(false);
        aplikasi.isForm();
        aplikasi.emptteks();
        aplikasi.tampil();
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKasusPersalinanDinkesActionPerformed

    private void btnMasterFaktorResikoJatuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterFaktorResikoJatuhActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterFaktorResikoIGD form = new MasterFaktorResikoIGD(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterFaktorResikoJatuhActionPerformed

    private void btnGantiPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGantiPasswordActionPerformed
        if (btnGantiPassword.isEnabled() == false) {
            PassLama.setText("");
            Passbaru1.setText("");
            PassBaru2.setText("");
        } else {
            isTutup();
            PassLama.setText("");
            Passbaru1.setText("");
            PassBaru2.setText("");
            WindowInput.setVisible(true);
        }
    }//GEN-LAST:event_btnGantiPasswordActionPerformed

    private void btnSpirometriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpirometriActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSpirometri aplikasi = new DlgSpirometri(this, false);
        aplikasi.isCek();
        aplikasi.ChkInput.setSelected(false);
        aplikasi.isForm(); 
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSpirometriActionPerformed

    private void btnDashboardeResepRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardeResepRanapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDashboardEresepRanap eResep = new DlgDashboardEresepRanap(this, false);
        eResep.emptTeks();
        eResep.tampilAwal();
        eResep.tampilAwalAntibiotik();
        eResep.isCek();
        eResep.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        eResep.setLocationRelativeTo(PanelUtama);
        eResep.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDashboardeResepRanapActionPerformed

    private void BtnDasboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDasboardActionPerformed
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        isTutup();
        FlayMenu.removeAll();
        FlayMenu.add(btnDashboardeResepRalan);
        FlayMenu.add(btnDashboardeResepRanap);
        btnDashboardeResepRalan.setEnabled(akses.getdashboard_eResep());
        btnDashboardeResepRanap.setEnabled(akses.getdashboard_eResep());
        FlayMenu.setVisible(true);
    }//GEN-LAST:event_BtnDasboardActionPerformed

    private void jMenu4MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu4MenuSelected
        isTutup();
        DlgHome.dispose();
        DlgAbout About = new DlgAbout(this, true);
        About.setSize(PanelWall.getWidth(), PanelWall.getHeight());
        About.setLocationRelativeTo(PanelWall);
        About.setVisible(true);
    }//GEN-LAST:event_jMenu4MenuSelected

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        isTutup();
        DlgHome.dispose();
        DlgAbout About = new DlgAbout(this, true);
        About.setSize(PanelWall.getWidth(), PanelWall.getHeight());
        About.setLocationRelativeTo(PanelWall);
        About.setVisible(true);        
    }//GEN-LAST:event_jMenu4MouseClicked

    private void btnMasterResikoDecubitusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterResikoDecubitusActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterResikoDecubitus form = new MasterResikoDecubitus(this, false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterResikoDecubitusActionPerformed

    private void btnReferensiDokterSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReferensiDokterSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatReferensiPraktisi aplikasi=new SatuSehatReferensiPraktisi(this,false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnReferensiDokterSatuSehatActionPerformed

    private void btnReferensiPasienSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReferensiPasienSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatReferensiPasien aplikasi = new SatuSehatReferensiPasien(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnReferensiPasienSatuSehatActionPerformed

    private void btnMapingOrganisasiSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapingOrganisasiSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatMapingOrganisasi aplikasi = new SatuSehatMapingOrganisasi(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMapingOrganisasiSatuSehatActionPerformed

    private void btnMapingLokasiSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapingLokasiSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatMapingLokasi aplikasi = new SatuSehatMapingLokasi(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMapingLokasiSatuSehatActionPerformed

    private void btnMapingVaksinSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapingVaksinSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatMapingVaksin aplikasi = new SatuSehatMapingVaksin(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMapingVaksinSatuSehatActionPerformed

    private void btnKirimEncounterSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimEncounterSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimEncounter aplikasi = new SatuSehatKirimEncounter(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimEncounterSatuSehatActionPerformed

    private void btnKirimConditionSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimConditionSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimCondition aplikasi = new SatuSehatKirimCondition(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimConditionSatuSehatActionPerformed

    private void btnKirimObservationSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimObservationSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimObservationTTV aplikasi = new SatuSehatKirimObservationTTV(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimObservationSatuSehatActionPerformed

    private void btnKirimProsedurSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimProsedurSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimProcedure aplikasi = new SatuSehatKirimProcedure(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimProsedurSatuSehatActionPerformed

    private void btnKirimImunisasiSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimImunisasiSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimVaksin aplikasi = new SatuSehatKirimVaksin(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimImunisasiSatuSehatActionPerformed

    private void btnKirimClinicalSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimClinicalSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimClinicalImpression aplikasi = new SatuSehatKirimClinicalImpression(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimClinicalSatuSehatActionPerformed

    private void btnKirimDietSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimDietSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimDiet aplikasi = new SatuSehatKirimDiet(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimDietSatuSehatActionPerformed

    private void btnMapingObatSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapingObatSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatMapingObatAlkes aplikasi = new SatuSehatMapingObatAlkes(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMapingObatSatuSehatActionPerformed

    private void btnKirimMedicationRequestSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimMedicationRequestSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimMedicationRequest aplikasi = new SatuSehatKirimMedicationRequest(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimMedicationRequestSatuSehatActionPerformed

    private void btnKirimMedicationDispenseSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimMedicationDispenseSatuSehatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SatuSehatKirimMedicationDispense aplikasi = new SatuSehatKirimMedicationDispense(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnKirimMedicationDispenseSatuSehatActionPerformed

    private void btnMasterJenisDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterJenisDokumenJangMedActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterJenisDokumenJangMed aplikasi = new DlgMasterJenisDokumenJangMed(this, false);
        aplikasi.isCek();
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterJenisDokumenJangMedActionPerformed

    private void btnMasterDiagnosaGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterDiagnosaGiziActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterDiagnosaGizi aplikasi = new DlgMasterDiagnosaGizi(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.emptTeks();        
        aplikasi.TCari.requestFocus();
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterDiagnosaGiziActionPerformed

    private void btnMasterIndikatorMutuLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterIndikatorMutuLayananActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterIndikatorMutu mutu = new DlgMasterIndikatorMutu(this, false);
        mutu.isCek();
        mutu.emptTeks();        
        mutu.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        mutu.setLocationRelativeTo(PanelUtama);
        mutu.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterIndikatorMutuLayananActionPerformed

    private void btnIndikatorNasionalMutuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIndikatorNasionalMutuActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIndikatorNasionalMutu mutu = new DlgIndikatorNasionalMutu(this, false);
        mutu.emptTeks();        
        mutu.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        mutu.setLocationRelativeTo(PanelUtama);
        mutu.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnIndikatorNasionalMutuActionPerformed

    private void btnMasterNumdenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterNumdenomActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterNumdemonINM mutu = new DlgMasterNumdemonINM(this, false);
        mutu.isCek();
        mutu.emptTeks();        
        mutu.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        mutu.setLocationRelativeTo(PanelUtama);
        mutu.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterNumdenomActionPerformed

    private void btnPasienBlackListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienBlackListActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPasienBlackList aplikasi = new DlgPasienBlackList(this, false);
        aplikasi.isCek();
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPasienBlackListActionPerformed

    private void btnHistoryLoginUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryLoginUserActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHistoriLoginUser aplikasi = new DlgHistoriLoginUser(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnHistoryLoginUserActionPerformed

    private void btnQuerySqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuerySqlActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgQuerySql aplikasi = new DlgQuerySql(this, false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnQuerySqlActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        TCari.setText("");
        TCari.requestFocus();
    }//GEN-LAST:event_button2ActionPerformed

    private void btnLaporanIndikatorMutuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanIndikatorMutuActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgLaporanIndikatorMutu mutu = new DlgLaporanIndikatorMutu(this, false);
        mutu.isCek();
        mutu.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        mutu.setLocationRelativeTo(PanelUtama);
        mutu.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnLaporanIndikatorMutuActionPerformed

    private void btnBPJSMapingObatApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSMapingObatApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSMapingObat form=new ApotekBPJSMapingObat(this,false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSMapingObatApotekActionPerformed

    private void btnBPJSReferensiObatDPHOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSReferensiObatDPHOActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSCekReferensiDPHO form=new ApotekBPJSCekReferensiDPHO(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSReferensiObatDPHOActionPerformed

    private void btnBPJSReferensiPoliApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSReferensiPoliApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSCekReferensiPoli form=new ApotekBPJSCekReferensiPoli(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSReferensiPoliApotekActionPerformed

    private void btnBPJSReferensiFaskesApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSReferensiFaskesApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSCekReferensiFaskes form=new ApotekBPJSCekReferensiFaskes(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSReferensiFaskesApotekActionPerformed

    private void btnBPJSReferensiSpesilistikApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSReferensiSpesilistikApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSCekReferensiSpesialistik form=new ApotekBPJSCekReferensiSpesialistik(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSReferensiSpesilistikApotekActionPerformed

    private void btnBPJSReferensiSetingPPKApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSReferensiSetingPPKApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSCekReferensiSettingPPK form=new ApotekBPJSCekReferensiSettingPPK(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSReferensiSetingPPKApotekActionPerformed

    private void btnBPJSReferensiObatApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSReferensiObatApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSCekReferensiObat form=new ApotekBPJSCekReferensiObat(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSReferensiObatApotekActionPerformed

    private void btnBPJSPencarianSEPApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSPencarianSEPApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSKunjunganSEP form=new ApotekBPJSKunjunganSEP(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSPencarianSEPApotekActionPerformed

    private void btnBPJSMonitoringKlaimApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSMonitoringKlaimApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSMonitoringKlaim form=new ApotekBPJSMonitoringKlaim(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSMonitoringKlaimApotekActionPerformed

    private void btnBPJSDataTerkirimApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPJSDataTerkirimApotekActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSDaftarPelayananObat2 form = new ApotekBPJSDaftarPelayananObat2(null, true);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnBPJSDataTerkirimApotekActionPerformed

    private void btnUTDPenyerahanDarahDirawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUTDPenyerahanDarahDirawatActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDPenyerahanDarahPasienDirawat form = new UTDPenyerahanDarahPasienDirawat(this, false);
        form.isCek();
        form.emptTeks();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnUTDPenyerahanDarahDirawatActionPerformed

    private void MnTarifUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifUpdActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiTarifStokDarah belum = new InformasiTarifStokDarah(this, true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifUpdActionPerformed

    private void btnMasterNomorDokumenRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasterNomorDokumenRMActionPerformed
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMasterNomorDokumenRM form = new DlgMasterNomorDokumenRM(this, false);
        form.isCek();
        form.emptTeks();
        form.tampil();
        form.ChkInput.setSelected(true);
        form.isForm();
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnMasterNomorDokumenRMActionPerformed

    private void MnPasienMeninggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPasienMeninggalActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.bikinFileTxt(Tversi.getText(), Sequel.cariFolderVersi(), "conf_versi.txt");
        DlgHome.dispose();
        InformasiKamarJenazah kamarJenazah = new InformasiKamarJenazah(this, false);
        kamarJenazah.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        kamarJenazah.setLocationRelativeTo(PanelUtama);
        kamarJenazah.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPasienMeninggalActionPerformed

    private void btnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPermintaanLabActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPermintaanLab form=new DlgCariPermintaanLab(this,false);
        form.isCek("");
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPermintaanLabActionPerformed

    private void btnLaboratoriumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratoriumActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPeriksaLab produsen = new DlgCariPeriksaLab(this, false);
        produsen.isCek();
        produsen.ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
        produsen.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        produsen.setLocationRelativeTo(PanelUtama);
        produsen.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnLaboratoriumActionPerformed

    private void btnPermintaanRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPermintaanRadiologiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPermintaanRadiologi form=new DlgCariPermintaanRadiologi(this,false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPermintaanRadiologiActionPerformed

    private void btnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriksaRadiologiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgCariPeriksaRadiologi produsen = new DlgCariPeriksaRadiologi(this, false);
        //produsen.emptTeks();
        produsen.isCek();
        produsen.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        produsen.setLocationRelativeTo(PanelUtama);
        produsen.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPeriksaRadiologiActionPerformed

    private void btnDashboardeResepRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardeResepRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDashboardEresep eResep = new DlgDashboardEresep(this, false);
        eResep.emptTeks();
        eResep.tampil();
        eResep.tampilResepAntibiotik();
        eResep.isCek();
        eResep.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        eResep.setLocationRelativeTo(PanelUtama);
        eResep.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDashboardeResepRalanActionPerformed

    private void btnDaftarPermintaanResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarPermintaanResepActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDaftarPermintaanResep daftar = new DlgDaftarPermintaanResep(null, false);
        daftar.emptTeks();
        daftar.isCek("-");
        daftar.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        daftar.setLocationRelativeTo(PanelUtama);
        daftar.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDaftarPermintaanResepActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new frmUtama().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnCancel;
    private widget.ButtonBig BtnClose;
    private widget.Button BtnClosePass;
    private widget.ButtonBig BtnDasboard;
    private widget.ButtonBig BtnLog;
    private widget.Button BtnLogin;
    private widget.ButtonBig BtnMenu;
    private widget.Button BtnSimpanPass;
    private widget.ButtonBig BtnToolJualObat;
    private widget.ButtonBig BtnToolKamnap;
    private widget.ButtonBig BtnToolKasir;
    private widget.ButtonBig BtnToolReg;
    private widget.CekBox ChkInput;
    private javax.swing.JDialog DlgHome;
    private javax.swing.JDialog DlgLogin;
    private usu.widget.glass.PanelGlass FlayMenu;
    private widget.MenuBar MenuBar;
    private javax.swing.JMenuItem MnJadwalDokterRalan;
    private javax.swing.JMenuItem MnPasienMeninggal;
    private javax.swing.JMenuItem MnPasienRanap;
    private javax.swing.JMenuItem MnPenggunaanKamarRanap;
    private javax.swing.JMenuItem MnTarifINACBG;
    private javax.swing.JMenuItem MnTarifKamar;
    private javax.swing.JMenuItem MnTarifLab;
    private javax.swing.JMenuItem MnTarifOperasi;
    private javax.swing.JMenuItem MnTarifRad;
    private javax.swing.JMenuItem MnTarifRalan;
    private javax.swing.JMenuItem MnTarifRanap;
    private javax.swing.JMenuItem MnTarifUpd;
    private javax.swing.JMenuItem MnTelusurKunjungan;
    private javax.swing.JPanel PanelUtama;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.panelGlass Panelmenu;
    private widget.TextBox PassBaru2;
    private widget.TextBox PassLama;
    private widget.TextBox Passbaru1;
    private widget.ScrollPane Scroll21;
    private widget.TextBox TCari;
    public javax.swing.JLabel Tversi;
    private javax.swing.JDialog WindowInput;
    private widget.ButtonBig btnAdmin;
    private widget.ButtonBig btnAkunPiutang;
    private widget.ButtonBig btnAntrian;
    private widget.ButtonBig btnAplicareKetersediaanKamar;
    private widget.ButtonBig btnAplicareReferensiKamar;
    private widget.ButtonBig btnBPJSDataTerkirimApotek;
    private widget.ButtonBig btnBPJSMapingObatApotek;
    private widget.ButtonBig btnBPJSMonitoringKlaimApotek;
    private widget.ButtonBig btnBPJSPencarianSEPApotek;
    private widget.ButtonBig btnBPJSReferensiFaskesApotek;
    private widget.ButtonBig btnBPJSReferensiObatApotek;
    private widget.ButtonBig btnBPJSReferensiObatDPHO;
    private widget.ButtonBig btnBPJSReferensiPoliApotek;
    private widget.ButtonBig btnBPJSReferensiSetingPPKApotek;
    private widget.ButtonBig btnBPJSReferensiSpesilistikApotek;
    private widget.ButtonBig btnBPJSSEP;
    private widget.ButtonBig btnBPJScekRiwayatRujukanPcare;
    private widget.ButtonBig btnBayarPiutang;
    private widget.ButtonBig btnBridgingEklaim;
    private widget.ButtonBig btnBridgingEklaimINACBG;
    private widget.ButtonBig btnBubes;
    private widget.ButtonBig btnBulananHAIsRS;
    private widget.ButtonBig btnBulananHAIsRalan;
    private widget.ButtonBig btnBulananHAIsRanap;
    private widget.ButtonBig btnCashFlow;
    private widget.ButtonBig btnCatatanPasien;
    private widget.ButtonBig btnCekBPJSDiagnosa;
    private widget.ButtonBig btnCekBPJSFaskes;
    private widget.ButtonBig btnCekBPJSKartu;
    private widget.ButtonBig btnCekBPJSNik;
    private widget.ButtonBig btnCekBPJSNomorRujukanPCare;
    private widget.ButtonBig btnCekBPJSNomorRujukanRS;
    private widget.ButtonBig btnCekBPJSPoli;
    private widget.ButtonBig btnCekBPJSRiwayatRujukanRS;
    private widget.ButtonBig btnCekBPJSRujukanKartuPCare;
    private widget.ButtonBig btnCekBPJSRujukanKartuRS;
    private widget.ButtonBig btnCekBPJSTanggalRujukan;
    private widget.ButtonBig btnCekFingerPrinBPJS;
    private widget.ButtonBig btnCekPCareDiagnosa;
    private widget.ButtonBig btnCekReferensiBatalDaftarMobileJKNBPJS;
    private widget.ButtonBig btnCekReferensiCaraKeluarBPJS;
    private widget.ButtonBig btnCekReferensiDiagnosaPRBBPJS;
    private widget.ButtonBig btnCekReferensiDokterBPJS;
    private widget.ButtonBig btnCekReferensiDokterDPJPBPJS;
    private widget.ButtonBig btnCekReferensiDokterHFISBPJS;
    private widget.ButtonBig btnCekReferensiJadwalHFISBPJS;
    private widget.ButtonBig btnCekReferensiKabupatenBPJS;
    private widget.ButtonBig btnCekReferensiKecamatanBPJS;
    private widget.ButtonBig btnCekReferensiKelasRawatBPJS;
    private widget.ButtonBig btnCekReferensiObatPRBBPJS;
    private widget.ButtonBig btnCekReferensiPascaPulangBPJS;
    private widget.ButtonBig btnCekReferensiPendaftaranMobileJKNBPJS;
    private widget.ButtonBig btnCekReferensiPoliHFISBPJS;
    private widget.ButtonBig btnCekReferensiPropinsiBPJS;
    private widget.ButtonBig btnCekReferensiProsedurBPJS;
    private widget.ButtonBig btnCekReferensiRuangRawatBPJS;
    private widget.ButtonBig btnCekReferensiSpesialistikBPJS;
    private widget.ButtonBig btnCekSEPInternalBPJS;
    private widget.ButtonBig btnCekSisruteAlasanRujuk;
    private widget.ButtonBig btnCekSisruteDiagnosa;
    private widget.ButtonBig btnCekSisruteFaskes;
    private widget.ButtonBig btnClosingKasir;
    private widget.ButtonBig btnDaftarPermintaanResep;
    private widget.ButtonBig btnDashboardeResepRalan;
    private widget.ButtonBig btnDashboardeResepRanap;
    private widget.ButtonBig btnDataHAIs;
    private widget.ButtonBig btnDataNomorSuratKontrolBPJS;
    private widget.ButtonBig btnDataPenjualan;
    private widget.ButtonBig btnDataPenyerahanDarah;
    private widget.ButtonBig btnDataPersalinan;
    private widget.ButtonBig btnDataSEPIndukKLLJasaRaharja;
    private widget.ButtonBig btnDataSuplesiJasaRaharja;
    private widget.ButtonBig btnDiagnosa;
    private widget.ButtonBig btnDiagnosaPasienCorona;
    private widget.ButtonBig btnFrekuensiRalan;
    private widget.ButtonBig btnFrekuensiRanap;
    private widget.ButtonBig btnGantiPassword;
    private widget.ButtonBig btnGrafikDemografiRegistrasi;
    private widget.ButtonBig btnGrafikKunjunganPerBulan;
    private widget.ButtonBig btnGrafikKunjunganPerDokter;
    private widget.ButtonBig btnGrafikKunjunganPerPekerjaan;
    private widget.ButtonBig btnGrafikKunjunganPerPendidikan;
    private widget.ButtonBig btnGrafikKunjunganPerTahun;
    private widget.ButtonBig btnGrafikKunjunganPerTanggal;
    private widget.ButtonBig btnGrafikKunjunganPoli;
    private widget.ButtonBig btnGrafikStatusRegBatalPerBulan;
    private widget.ButtonBig btnGrafikStatusRegBatalPerTahun;
    private widget.ButtonBig btnGrafikStatusRegBatalPerTanggal;
    private widget.ButtonBig btnGrafikStatusRegPerBulan;
    private widget.ButtonBig btnGrafikStatusRegPerBulan2;
    private widget.ButtonBig btnGrafikStatusRegPerTahun;
    private widget.ButtonBig btnGrafikStatusRegPerTahun2;
    private widget.ButtonBig btnGrafikStatusRegPerTanggal;
    private widget.ButtonBig btnGrafikStatusRegPerTanggal2;
    private widget.ButtonBig btnHarianHAIsRS;
    private widget.ButtonBig btnHarianHAIsRalan;
    private widget.ButtonBig btnHarianHAIsRanap;
    private widget.ButtonBig btnHistoriPelayananPesertaBPJS;
    private widget.ButtonBig btnHistoryLoginUser;
    private widget.ButtonBig btnHutangObat;
    private widget.ButtonBig btnICD;
    private widget.ButtonBig btnICD9;
    private widget.ButtonBig btnINACBGjknBelumDiklaim;
    private widget.ButtonBig btnIkhtisarPerawatanHIV;
    private widget.ButtonBig btnInaCBGCoderNIK;
    private widget.ButtonBig btnIndikatorNasionalMutu;
    private widget.ButtonBig btnInputKodeICD;
    private widget.ButtonBig btnInputPenjualan;
    private widget.ButtonBig btnJumlahMacamDiet;
    private widget.ButtonBig btnJumlahPorsiDiet;
    private widget.ButtonBig btnJurnalHarian;
    private widget.ButtonBig btnKamar;
    private widget.ButtonBig btnKasusPersalinanDinkes;
    private widget.ButtonBig btnKemenkesKanker;
    private widget.ButtonBig btnKemenkesSITB;
    private widget.ButtonBig btnKendaliMutuKendaliBiayaINACBG;
    private widget.ButtonBig btnKeterlambatanPresensi;
    private widget.ButtonBig btnKirimClinicalSatuSehat;
    private widget.ButtonBig btnKirimConditionSatuSehat;
    private widget.ButtonBig btnKirimDietSatuSehat;
    private widget.ButtonBig btnKirimEncounterSatuSehat;
    private widget.ButtonBig btnKirimImunisasiSatuSehat;
    private widget.ButtonBig btnKirimMedicationDispenseSatuSehat;
    private widget.ButtonBig btnKirimMedicationRequestSatuSehat;
    private widget.ButtonBig btnKirimObservationSatuSehat;
    private widget.ButtonBig btnKirimProsedurSatuSehat;
    private widget.ButtonBig btnKlaimJaminanJasaRaharja;
    private widget.ButtonBig btnKunjunganRalan;
    private widget.ButtonBig btnKunjunganRanap;
    private widget.ButtonBig btnLabaRugi;
    private widget.ButtonBig btnLaboratorium;
    private widget.ButtonBig btnLahir;
    private widget.ButtonBig btnLamaPelayananApotek;
    private widget.ButtonBig btnLamaPelayananRalan;
    private widget.ButtonBig btnLaporanIndikatorMutu;
    private widget.ButtonBig btnListSaranaRujukanBPJS;
    private widget.ButtonBig btnListSpesialistikRujukanBPJS;
    private widget.ButtonBig btnMapingLokasiSatuSehat;
    private widget.ButtonBig btnMapingObatSatuSehat;
    private widget.ButtonBig btnMapingOrganisasiSatuSehat;
    private widget.ButtonBig btnMapingVaksinSatuSehat;
    private widget.ButtonBig btnMasterCaraBayar;
    private widget.ButtonBig btnMasterDTD;
    private widget.ButtonBig btnMasterDiagnosaGizi;
    private widget.ButtonBig btnMasterFaktorResikoJatuh;
    private widget.ButtonBig btnMasterFaskes;
    private widget.ButtonBig btnMasterIndikatorMutuLayanan;
    private widget.ButtonBig btnMasterJenisDokumenJangMed;
    private widget.ButtonBig btnMasterKasusPersalinanDinkes;
    private widget.ButtonBig btnMasterKeluhanPsikologis;
    private widget.ButtonBig btnMasterMasalahKeperawatan;
    private widget.ButtonBig btnMasterNomorDokumenRM;
    private widget.ButtonBig btnMasterNumdenom;
    private widget.ButtonBig btnMasterRencanaTritmenPsikologis;
    private widget.ButtonBig btnMasterResikoDecubitus;
    private widget.ButtonBig btnMonitoringKlaimBPJS;
    private widget.ButtonBig btnObatPenyakit;
    private widget.ButtonBig btnPaketOperasi;
    private widget.ButtonBig btnPasien;
    private widget.ButtonBig btnPasienBlackList;
    private widget.ButtonBig btnPasienCorona;
    private widget.ButtonBig btnPasienMati;
    private widget.ButtonBig btnPasienPonek;
    private widget.ButtonBig btnPemasukanLain;
    private widget.ButtonBig btnPengajuanKlaimINACBGrz;
    private widget.ButtonBig btnPengambilanPenunjangUTD2;
    private widget.ButtonBig btnPengambilanUTD2;
    private widget.ButtonBig btnPengaturanRekening;
    private widget.ButtonBig btnPengeluaran;
    private widget.ButtonBig btnPenjualan;
    private widget.ButtonBig btnPenyakitPD3I;
    private widget.ButtonBig btnPerawatanPasienCorona;
    private widget.ButtonBig btnPeriksaRadiologi;
    private widget.ButtonBig btnPermintaanLab;
    private widget.ButtonBig btnPermintaanRadiologi;
    private widget.ButtonBig btnPerusahaan;
    private widget.ButtonBig btnPiutangBelumLunas;
    private widget.ButtonBig btnPiutangPerCaraBayar;
    private widget.ButtonBig btnPnyMenularRalan;
    private widget.ButtonBig btnPnyMenularRanap;
    private widget.ButtonBig btnPnyTakMenularRalan;
    private widget.ButtonBig btnPnyTakMenularRanap;
    private widget.ButtonBig btnPostingJurnal;
    private widget.ButtonBig btnProgramPRBBPJS;
    private widget.ButtonBig btnQuerySql;
    private widget.ButtonBig btnReferensiDokterSatuSehat;
    private widget.ButtonBig btnReferensiPasienSatuSehat;
    private widget.ButtonBig btnRekamPsikologiPerkawinan;
    private widget.ButtonBig btnRekamPsikologisAnak;
    private widget.ButtonBig btnRekamPsikologisDewasa;
    private widget.ButtonBig btnRekening;
    private widget.ButtonBig btnRekeningTahun;
    private widget.ButtonBig btnRencanaKontrolBPJS;
    private widget.ButtonBig btnResume;
    private widget.ButtonBig btnRincianPiutangPasien;
    private widget.ButtonBig btnRl32;
    private widget.ButtonBig btnRl33;
    private widget.ButtonBig btnRl34;
    private widget.ButtonBig btnRl36;
    private widget.ButtonBig btnRl37;
    private widget.ButtonBig btnRl38;
    private widget.ButtonBig btnRl4a;
    private widget.ButtonBig btnRl4asebab;
    private widget.ButtonBig btnRl4b;
    private widget.ButtonBig btnRl4bsebab;
    private widget.ButtonBig btnRujukKeluarVclaim;
    private widget.ButtonBig btnRujukanKeluarSisrute;
    private widget.ButtonBig btnRujukanMasukSisrute;
    private widget.ButtonBig btnSKDPbpjs;
    private widget.ButtonBig btnSPRIbpjsVclaim;
    private widget.ButtonBig btnSensusHarianPoli;
    private widget.ButtonBig btnSetBiayaHarian;
    private widget.ButtonBig btnSetBiayaMasukSekali;
    private widget.ButtonBig btnSetHargaKamar;
    private widget.ButtonBig btnSetObatRalan;
    private widget.ButtonBig btnSetObatRanap;
    private widget.ButtonBig btnSetOtoRalan;
    private widget.ButtonBig btnSetPenjab;
    private widget.ButtonBig btnSetingBridging;
    private widget.ButtonBig btnSetupAplikasi;
    private widget.ButtonBig btnSetupEmbalase;
    private widget.ButtonBig btnSetupHarga;
    private widget.ButtonBig btnSetupJamInap;
    private widget.ButtonBig btnSetupNota;
    private widget.ButtonBig btnSetupOtoLokasi;
    private widget.ButtonBig btnSetupRM;
    private widget.ButtonBig btnSetupTarif;
    private widget.ButtonBig btnSpirometri;
    private widget.ButtonBig btnSurveilansPD3I;
    private widget.ButtonBig btnSurveilansRalan;
    private widget.ButtonBig btnSurveilansRanap;
    private widget.ButtonBig btnTarifLab;
    private widget.ButtonBig btnTarifRadiologi;
    private widget.ButtonBig btnTarifUtd;
    private widget.ButtonBig btnTindakanRalan;
    private widget.ButtonBig btnTindakanRanap;
    private widget.ButtonBig btnToolIGD;
    private widget.ButtonBig btnToolLab;
    private widget.ButtonBig btnToolRad;
    private widget.ButtonBig btnTracker;
    private widget.ButtonBig btnUTDCekalDarah;
    private widget.ButtonBig btnUTDDonorDarah;
    private widget.ButtonBig btnUTDKomponenDarah;
    private widget.ButtonBig btnUTDMedisRusak;
    private widget.ButtonBig btnUTDPemisahanDarah;
    private widget.ButtonBig btnUTDPenunjangRusak;
    private widget.ButtonBig btnUTDPenyerahanDarah;
    private widget.ButtonBig btnUTDPenyerahanDarahDirawat;
    private widget.ButtonBig btnUTDStokDarah;
    private widget.ButtonBig btnUser;
    private widget.ButtonBig btnakun_bayar;
    private widget.ButtonBig btnbayar_pemesanan;
    private widget.Button button1;
    private widget.Button button2;
    private widget.ComboBox cmbMenu;
    private widget.TextBox edAdmin;
    private widget.PasswordBox edPwd;
    private javax.swing.JLabel footer_lbl_update;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel10;
    private javax.swing.JLabel jLabel11;
    private widget.Label jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel kdUser;
    private widget.TextArea ket_update;
    private widget.Label label35;
    private widget.Label label36;
    private javax.swing.JLabel lblIPaddress;
    private javax.swing.JLabel lblStts;
    private javax.swing.JLabel lblTgl;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lbl_update;
    private usu.widget.glass.PanelGlass panelGlass1;
    private usu.widget.glass.PanelGlass panelJudul;
    private widget.InternalFrame panelMenu;
    private widget.panelisi panelisi2;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Tanggal tanggal;
    // End of variables declaration//GEN-END:variables

    public void isTampil() {
        Panelmenu.removeAll();
        if (ChkInput.isSelected()) {
            if (cmbMenu.getSelectedIndex() == 0) {
                tampilMenuUtamaA();
                return;
            }

            if (cmbMenu.getSelectedIndex() == 1) {
                tampilMenuUtamaB();
                return;
            }
            
            if (cmbMenu.getSelectedIndex() == 2) {
                tampilMenuUtamaC();
                return;
            }
            
            if (cmbMenu.getSelectedIndex() == 3) {
                tampilMenuUtamaD();
                return;
            }
            
            if (cmbMenu.getSelectedIndex() == 4) {
                tampilMenuUtamaE();
                return;
            }
            
            if (cmbMenu.getSelectedIndex() == 5) {
                tampilMenuUtamaF();
                return;
            }
            
            if (cmbMenu.getSelectedIndex() == 6) {
                tampilMenuUtamaG();
                return;
            }
            
            if (cmbMenu.getSelectedIndex() == 7) {
                tampilMenuUtamaH();
                return;
            }

            isCombo();
        } else {
            if (TCari.getText().trim().isEmpty()) {
                isCariKosong();
            } else {
                isCariIsi();
            }
        }

        aturUkuranPanelMenu();
    }

    public void isWall() {
        try {
            ps = koneksi.prepareStatement("select nama_instansi, alamat_instansi, kabupaten, propinsi, aktifkan, wallpaper,kontak,email,logo from setting");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    jLabel8.setText(rs.getString(1));
                    this.setTitle("SIM " + rs.getString("nama_instansi"));
                    jLabel11.setText(rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + " ");
                    akses.setnamars(rs.getString("nama_instansi"));
                    akses.setalamatrs(rs.getString("alamat_instansi"));
                    akses.setkabupatenrs(rs.getString("kabupaten"));
                    akses.setpropinsirs(rs.getString("propinsi"));
                    akses.setkontakrs(rs.getString("kontak"));
                    akses.setemailrs(rs.getString("email"));

                    if (rs.getString(5).equals("Yes")) {
                        Blob blob = rs.getBlob(6);
                        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(blob.getBytes(1, (int) (blob.length()))));
                        repaint();
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
        } catch (Exception e) {
            System.out.println("Notifikasi : Silahkan Set Aplikasi " + e);
        }
    }

    private void isTutup() {
        if (akses.getform().equals("DlgKasirRalan")) {
            akses.tRefreshAntrian.stop();
            akses.tRefreshPoli.stop();
        }
        
        if (akses.getform().equals("DlgReg")) {
            akses.tRefreshAntri.stop();
        }
        
        if (akses.getform().equals("DlgDashboardEresep")) {
            DlgDashboardEresep dash = new DlgDashboardEresep(this, false);
            dash.tEresep.stop();
        }
        
        FlayMenu.setVisible(false);
        akses.setform("frmUtama");
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.dispose();
            }
        }
    }

    private void setToolbar() {
        setExtendedState(MAXIMIZED_BOTH);
        if (internalFrame1.getWidth() < (BtnMenu.getWidth() + btnGantiPassword.getWidth() + BtnToolReg.getWidth() 
                + btnToolIGD.getWidth() + btnBridgingEklaim.getWidth() +  btnToolLab.getWidth()
                + btnToolRad.getWidth() + BtnToolJualObat.getWidth() + BtnToolKamnap.getWidth()
                + BtnToolKasir.getWidth() + BtnLog.getWidth() + BtnClose.getWidth() + 8)) {
            internalFrame1.setSize(new Dimension(PanelUtama.getWidth(), 90));
        } else {
            internalFrame1.setSize(new Dimension(PanelUtama.getWidth(), 44));
        }
    }

    private void isCombo() {
        if (cmbMenu.getSelectedIndex() == 0) {
            tampilMenuUtamaA();
        } else if (cmbMenu.getSelectedIndex() == 1) {
            tampilMenuUtamaB();
        } else if (cmbMenu.getSelectedIndex() == 2) {
            tampilMenuUtamaC();
        } else if (cmbMenu.getSelectedIndex() == 3) {
            tampilMenuUtamaD();
        } else if (cmbMenu.getSelectedIndex() == 4) {
            tampilMenuUtamaE();
        } else if (cmbMenu.getSelectedIndex() == 5) {
            tampilMenuUtamaF();
        } else if (cmbMenu.getSelectedIndex() == 6) {
            tampilMenuUtamaG();
        } else if (cmbMenu.getSelectedIndex() == 7) {
            tampilMenuUtamaH();
        } else if (cmbMenu.getSelectedIndex() == 8) {
            jmlmenu = 0;
            if (akses.getpenyakit() == true) {
                Panelmenu.add(btnQuerySql);
                jmlmenu++;
            }
            
            if (akses.geticd9() == true) {
                Panelmenu.add(btnICD9);
                jmlmenu++;
            }
            
            if (akses.getrekam_psikologis() == true) {
                Panelmenu.add(btnRekamPsikologisDewasa);
                jmlmenu++;
            }
            
            if (akses.getrekam_psikologis() == true) {
                Panelmenu.add(btnRekamPsikologisAnak);
                jmlmenu++;
            }
            
            if (akses.getrekam_psikologis() == true) {
                Panelmenu.add(btnRekamPsikologiPerkawinan);
                jmlmenu++;
            }

            if (akses.getpenyakit() == true) {
                Panelmenu.add(btnICD);
                jmlmenu++;
            }

            if (akses.getpenyakit() == true) {
                Panelmenu.add(btnMasterDTD);
                jmlmenu++;
            }
            
            if (akses.getrekam_psikologis() == true) {
                Panelmenu.add(btnMasterKeluhanPsikologis);
                jmlmenu++;
            }

            if (akses.getrekam_psikologis() == true) {
                Panelmenu.add(btnMasterRencanaTritmenPsikologis);
                jmlmenu++;
            }
            
            if (akses.getpenyakit_pd3i() == true) {
                Panelmenu.add(btnPenyakitPD3I);
                jmlmenu++;
            }

            if (akses.getsurveilans_pd3i() == true) {
                Panelmenu.add(btnSurveilansPD3I);
                jmlmenu++;
            }

            if (akses.getsurveilans_ralan() == true) {
                Panelmenu.add(btnSurveilansRalan);
                jmlmenu++;
            }

            if (akses.getsurveilans_ranap() == true) {
                Panelmenu.add(btnSurveilansRanap);
                jmlmenu++;
            }

            if (akses.getpny_takmenular_ralan() == true) {
                Panelmenu.add(btnPnyTakMenularRalan);
                jmlmenu++;
            }

            if (akses.getpny_takmenular_ranap() == true) {
                Panelmenu.add(btnPnyTakMenularRanap);
                jmlmenu++;
            }

            if (akses.getpenyakit_menular_ralan() == true) {
                Panelmenu.add(btnPnyMenularRalan);
                jmlmenu++;
            }

            if (akses.getpenyakit_menular_ranap() == true) {
                Panelmenu.add(btnPnyMenularRanap);
                jmlmenu++;
            }

            if (akses.getobat_penyakit() == true) {
                Panelmenu.add(btnObatPenyakit);
                jmlmenu++;
            }

            if (akses.getpenyakit_ralan() == true) {
                Panelmenu.add(btnFrekuensiRalan);
                jmlmenu++;
            }

            if (akses.getpenyakit_ranap() == true) {
                Panelmenu.add(btnFrekuensiRanap);
                jmlmenu++;
            }

            if (akses.getkunjungan_ralan() == true) {
                Panelmenu.add(btnKunjunganRalan);
                jmlmenu++;
            }

            if (akses.getkunjungan_ranap() == true) {
                Panelmenu.add(btnKunjunganRanap);
                jmlmenu++;
            }

            if (akses.getsensus_harian_poli() == true) {
                Panelmenu.add(btnSensusHarianPoli);
                jmlmenu++;
            }

            if (akses.getrl32() == true) {
                Panelmenu.add(btnRl32);
                jmlmenu++;
            }

            if (akses.getrl33() == true) {
                Panelmenu.add(btnRl33);
                jmlmenu++;
            }

            if (akses.getrl34() == true) {
                Panelmenu.add(btnRl34);
                jmlmenu++;
            }

            if (akses.getrl36() == true) {
                Panelmenu.add(btnRl36);
                jmlmenu++;
            }

            if (akses.getrl37() == true) {
                Panelmenu.add(btnRl37);
                jmlmenu++;
            }

            if (akses.getrl38() == true) {
                Panelmenu.add(btnRl38);
                jmlmenu++;
            }

            if (akses.getrl4a() == true) {
                Panelmenu.add(btnRl4a);
                jmlmenu++;
            }

            if (akses.getrl4b() == true) {
                Panelmenu.add(btnRl4b);
                jmlmenu++;
            }

            if (akses.getrl4asebab() == true) {
                Panelmenu.add(btnRl4asebab);
                jmlmenu++;
            }

            if (akses.getrl4bsebab() == true) {
                Panelmenu.add(btnRl4bsebab);
                jmlmenu++;
            }

            if (akses.getlama_pelayanan_ralan() == true) {
                Panelmenu.add(btnLamaPelayananRalan);
                jmlmenu++;
            }

            if (akses.getlama_pelayanan_apotek() == true) {
                Panelmenu.add(btnLamaPelayananApotek);
                jmlmenu++;
            }

            if (akses.getharian_HAIs() == true) {
                Panelmenu.add(btnHarianHAIsRS);
                jmlmenu++;
            }

            if (akses.getharianhaisinap() == true) {
                Panelmenu.add(btnHarianHAIsRanap);
                jmlmenu++;
            }

            if (akses.getharianhaisjalan() == true) {
                Panelmenu.add(btnHarianHAIsRalan);
                jmlmenu++;
            }

            if (akses.getbulanan_HAIs() == true) {
                Panelmenu.add(btnBulananHAIsRS);
                jmlmenu++;
            }

            if (akses.getbulananhaisinap() == true) {
                Panelmenu.add(btnBulananHAIsRanap);
                jmlmenu++;
            }

            if (akses.getbulananhaisjalan() == true) {
                Panelmenu.add(btnBulananHAIsRalan);
                jmlmenu++;
            }

            if (akses.getjumlah_macam_diet() == true) {
                Panelmenu.add(btnJumlahMacamDiet);
                jmlmenu++;
            }

            if (akses.getjumlah_porsi_diet() == true) {
                Panelmenu.add(btnJumlahPorsiDiet);
                jmlmenu++;
            }

            if (akses.getdata_persalinan() == true) {
                Panelmenu.add(btnDataPersalinan);
                jmlmenu++;
            }
        } else if (cmbMenu.getSelectedIndex() == 9) {
            jmlmenu = 0;
            if (akses.getkamar() == true) {
                Panelmenu.add(btnKamar);
                jmlmenu++;
            }

            if (akses.gettarif_ralan() == true) {
                Panelmenu.add(btnTindakanRalan);
                jmlmenu++;
            }

            if (akses.gettarif_ranap() == true) {
                Panelmenu.add(btnTindakanRanap);
                jmlmenu++;
            }

            if (akses.gettarif_lab() == true) {
                Panelmenu.add(btnTarifLab);
                jmlmenu++;
            }

            if (akses.gettarif_radiologi() == true) {
                Panelmenu.add(btnTarifRadiologi);
                jmlmenu++;
            }

            if (akses.gettarif_operasi() == true) {
                Panelmenu.add(btnPaketOperasi);
                jmlmenu++;
            }

            if (akses.gettarif_utd() == true) {
                Panelmenu.add(btnTarifUtd);
                jmlmenu++;
            }

            if (akses.getakun_rekening() == true) {
                Panelmenu.add(btnRekening);
                jmlmenu++;
            }

            if (akses.getrekening_tahun() == true) {
                Panelmenu.add(btnRekeningTahun);
                jmlmenu++;
            }

            if (akses.getakun_bayar() == true) {
                Panelmenu.add(btnakun_bayar);
                jmlmenu++;
            }

            if (akses.getakun_piutang() == true) {
                Panelmenu.add(btnAkunPiutang);
                jmlmenu++;
            }

            if (akses.getpengaturan_rekening() == true) {
                Panelmenu.add(btnPengaturanRekening);
                jmlmenu++;
            }

            if (akses.getpengeluaran() == true) {
                Panelmenu.add(btnPengeluaran);
                jmlmenu++;
            }

            if (akses.getpemasukan_lain() == true) {
                Panelmenu.add(btnPemasukanLain);
                jmlmenu++;
            }

            if (akses.getrincian_piutang_pasien() == true) {
                Panelmenu.add(btnRincianPiutangPasien);
                jmlmenu++;
            }

            if (akses.getpiutang_pasien2() == true) {
                Panelmenu.add(btnPiutangBelumLunas);
                jmlmenu++;
            }

            if (akses.getdetail_piutang_penjab() == true) {
                Panelmenu.add(btnPiutangPerCaraBayar);
                jmlmenu++;
            }

            if (akses.getbayar_piutang() == true) {
                Panelmenu.add(btnBayarPiutang);
                jmlmenu++;
            }

            if (akses.gethutang_obat() == true) {
                Panelmenu.add(btnHutangObat);
                jmlmenu++;
            }

            if (akses.getbayar_pemesanan_obat() == true) {
                Panelmenu.add(btnbayar_pemesanan);
                jmlmenu++;
            }

            if (akses.getposting_jurnal() == true) {
                Panelmenu.add(btnPostingJurnal);
                jmlmenu++;
            }

            if (akses.getjurnal_harian() == true) {
                Panelmenu.add(btnJurnalHarian);
                jmlmenu++;
            }

            if (akses.getbuku_besar() == true) {
                Panelmenu.add(btnBubes);
                jmlmenu++;
            }

            if (akses.getcashflow() == true) {
                Panelmenu.add(btnCashFlow);
                jmlmenu++;
            }

            if (akses.getkeuangan() == true) {
                Panelmenu.add(btnLabaRugi);
                jmlmenu++;
            }

        } else if (cmbMenu.getSelectedIndex() == 10) {
            jmlmenu = 0;
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnReferensiDokterSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnReferensiPasienSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnMapingOrganisasiSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnBPJSReferensiPoliApotek);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnBPJSReferensiObatDPHO);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnBPJSReferensiSpesilistikApotek);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnBPJSReferensiSetingPPKApotek);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnBPJSReferensiObatApotek);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnBPJSPencarianSEPApotek);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnBPJSMonitoringKlaimApotek);
                jmlmenu++;
            }
            
            if (akses.getstok_obat_pasien() == true) {
                Panelmenu.add(btnBPJSDataTerkirimApotek);
                jmlmenu++;
            }
            
            if (akses.getstok_obat_pasien() == true) {
                Panelmenu.add(btnBPJSMapingObatApotek);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnMapingLokasiSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnMapingVaksinSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getstok_obat_pasien() == true) {
                Panelmenu.add(btnMapingObatSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimEncounterSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimConditionSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimObservationSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimProsedurSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimImunisasiSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimClinicalSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimDietSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimMedicationRequestSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsatu_sehat() == true) {
                Panelmenu.add(btnKirimMedicationDispenseSatuSehat);
                jmlmenu++;
            }
            
            if (akses.getsisrute_rujukan_masuk() == true) {
                Panelmenu.add(btnRujukanMasukSisrute);
                jmlmenu++;
            }

            if (akses.getkemenkes_sitt() == true) {
                Panelmenu.add(btnKemenkesSITB);
                jmlmenu++;
            }

            if (akses.getkemenkes_kanker() == true) {
                Panelmenu.add(btnKemenkesKanker);
                jmlmenu++;
            }

            if (akses.getRencanaKontrolJKN() == true) {
                Panelmenu.add(btnRencanaKontrolBPJS);
                jmlmenu++;
            }

            if (akses.getkendali_mutu_kendali_biaya_inacbg() == true) {
                Panelmenu.add(btnKendaliMutuKendaliBiayaINACBG);
                jmlmenu++;
            }

            if (akses.getjkn_belum_diproses_klaim() == true) {
                Panelmenu.add(btnINACBGjknBelumDiklaim);
                jmlmenu++;
            }

            if (akses.getinacbg_klaim_raza() == true) {
                Panelmenu.add(btnBridgingEklaimINACBG);
                jmlmenu++;
            }

            if (akses.getpengajuan_klaim_raza() == true) {
                Panelmenu.add(btnPengajuanKlaimINACBGrz);
                jmlmenu++;
            }

            if (akses.getsisrute_rujukan_keluar() == true) {
                Panelmenu.add(btnRujukanKeluarSisrute);
                jmlmenu++;
            }

            if (akses.getsisrute_referensi_alasanrujuk() == true) {
                Panelmenu.add(btnCekSisruteAlasanRujuk);
                jmlmenu++;
            }

            if (akses.getsisrute_referensi_diagnosa() == true) {
                Panelmenu.add(btnCekSisruteDiagnosa);
                jmlmenu++;
            }

            if (akses.getsisrute_referensi_faskes() == true) {
                Panelmenu.add(btnCekSisruteFaskes);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnListSaranaRujukanBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnCekReferensiPoliHFISBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnCekReferensiJadwalHFISBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnCekReferensiDokterHFISBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnCekReferensiPendaftaranMobileJKNBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnCekReferensiBatalDaftarMobileJKNBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnKlaimJaminanJasaRaharja);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnDataSuplesiJasaRaharja);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnDataSEPIndukKLLJasaRaharja);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnDataNomorSuratKontrolBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnHistoriPelayananPesertaBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnProgramPRBBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnCekReferensiObatPRBBPJS);
                jmlmenu++;
            }

            if (akses.getskdp_bpjs() == true) {
                Panelmenu.add(btnSKDPbpjs);
                jmlmenu++;
            }

            if (akses.getSPRIJKN() == true) {
                Panelmenu.add(btnSPRIbpjsVclaim);
                jmlmenu++;
            }

            if (akses.getbpjs_rujukan_keluar() == true) {
                Panelmenu.add(btnRujukKeluarVclaim);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_tgl_rujukan() == true) {
                Panelmenu.add(btnCekBPJSTanggalRujukan);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_no_rujukan_rs() == true) {
                Panelmenu.add(btnCekBPJSNomorRujukanRS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_rujukan_kartu_pcare() == true) {
                Panelmenu.add(btnCekBPJSRujukanKartuPCare);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_kartu() == true) {
                Panelmenu.add(btnCekFingerPrinBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_kartu() == true) {
                Panelmenu.add(btnCekBPJSKartu);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_nik() == true) {
                Panelmenu.add(btnCekBPJSNik);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnListSpesialistikRujukanBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnCekReferensiDiagnosaPRBBPJS);
                jmlmenu++;
            }

            if (akses.getbpjsSEPinternal() == true) {
                Panelmenu.add(btnCekSEPInternalBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_riwayat_rujukan_pcare() == true) {
                Panelmenu.add(btnBPJScekRiwayatRujukanPcare);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_riwayat_rujukan_rs() == true) {
                Panelmenu.add(btnCekBPJSRiwayatRujukanRS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_rujukan_kartu_rs() == true) {
                Panelmenu.add(btnCekBPJSRujukanKartuRS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_nomor_rujukan() == true) {
                Panelmenu.add(btnCekBPJSNomorRujukanPCare);
                jmlmenu++;
            }

            if (akses.getbpjs_referensi_diagnosa() == true) {
                Panelmenu.add(btnCekBPJSDiagnosa);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_propinsi() == true) {
                Panelmenu.add(btnCekReferensiPropinsiBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_kabupaten() == true) {
                Panelmenu.add(btnCekReferensiKabupatenBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_kecamatan() == true) {
                Panelmenu.add(btnCekReferensiKecamatanBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_pasca_pulang() == true) {
                Panelmenu.add(btnCekReferensiPascaPulangBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_cara_keluar() == true) {
                Panelmenu.add(btnCekReferensiCaraKeluarBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_ruang_rawat() == true) {
                Panelmenu.add(btnCekReferensiRuangRawatBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_spesialistik() == true) {
                Panelmenu.add(btnCekReferensiSpesialistikBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_dpjp() == true) {
                Panelmenu.add(btnCekReferensiDokterDPJPBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_dokter() == true) {
                Panelmenu.add(btnCekReferensiDokterBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_prosedur() == true) {
                Panelmenu.add(btnCekReferensiProsedurBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_cek_referensi_kelas_rawat() == true) {
                Panelmenu.add(btnCekReferensiKelasRawatBPJS);
                jmlmenu++;
            }

            if (akses.getbpjs_referensi_poli() == true) {
                Panelmenu.add(btnCekBPJSPoli);
                jmlmenu++;
            }

            if (akses.getbpjs_referensi_faskes() == true) {
                Panelmenu.add(btnCekBPJSFaskes);
                jmlmenu++;
            }

            if (akses.getbpjs_sep() == true) {
                Panelmenu.add(btnBPJSSEP);
                jmlmenu++;
            }

            if (akses.getbpjs_monitoring_klaim() == true) {
                Panelmenu.add(btnMonitoringKlaimBPJS);
                jmlmenu++;
            }

            if (akses.getaplicare_referensi_kamar() == true) {
                Panelmenu.add(btnAplicareReferensiKamar);
                jmlmenu++;
            }

            if (akses.getaplicare_ketersediaan_kamar() == true) {
                Panelmenu.add(btnAplicareKetersediaanKamar);
                jmlmenu++;
            }

            if (akses.getinacbg_coder_nik() == true) {
                Panelmenu.add(btnInaCBGCoderNIK);
                jmlmenu++;
            }

            if (akses.getpcare_cek_penyakit() == true) {
                Panelmenu.add(btnCekPCareDiagnosa);
                jmlmenu++;
            }

            if (akses.getpasien_corona() == true) {
                Panelmenu.add(btnPasienCorona);
                jmlmenu++;
            }

            if (akses.getdiagnosa_pasien_corona() == true) {
                Panelmenu.add(btnDiagnosaPasienCorona);
                jmlmenu++;
            }

            if (akses.getperawatan_pasien_corona() == true) {
                Panelmenu.add(btnPerawatanPasienCorona);
                jmlmenu++;
            }

        } else if (cmbMenu.getSelectedIndex() == 11) {
            jmlmenu = 0;
            if (akses.getadmin()== true) {
                Panelmenu.add(btnMasterNomorDokumenRM);
                jmlmenu++;
            }
            
            if (akses.getadmin()== true) {
                Panelmenu.add(btnPasienBlackList);
                jmlmenu++;
            }
            
            if (akses.getkemenkes_sitt()== true) {
                Panelmenu.add(btnSpirometri);
                jmlmenu++;
            }
            
            if (akses.getpic_kmkp()== true) {
                Panelmenu.add(btnMasterIndikatorMutuLayanan);
                jmlmenu++;
            }
            
            if (akses.getpic_kmkp()== true) {
                Panelmenu.add(btnMasterNumdenom);
                jmlmenu++;
            }
                    
            if (akses.getadmin()== true) {
                Panelmenu.add(btnMasterJenisDokumenJangMed);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnMasterResikoDecubitus);
                jmlmenu++;
            }
            
            if (akses.getdata_triase_igd() == true) {
                Panelmenu.add(btnMasterFaktorResikoJatuh);
                jmlmenu++;
            }
            
            if (akses.getperusahaan_pasien() == true) {
                Panelmenu.add(btnPerusahaan);
                jmlmenu++;
            }

            if (akses.getpasien() == true) {
                Panelmenu.add(btnPasien);
                jmlmenu++;
            }

            if (akses.getkelahiran_bayi() == true) {
                Panelmenu.add(btnLahir);
                jmlmenu++;
            }

            if (akses.getcatatan_pasien() == true) {
                Panelmenu.add(btnCatatanPasien);
                jmlmenu++;
            }

            if (akses.getdata_ponek() == true) {
                Panelmenu.add(btnPasienPonek);
                jmlmenu++;
            }

            if (akses.getpasien_meninggal() == true) {
                Panelmenu.add(btnPasienMati);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnMasterKasusPersalinanDinkes);
                jmlmenu++;
            }
            
            if (akses.getdata_persalinan() == true) {
                Panelmenu.add(btnKasusPersalinanDinkes);
                jmlmenu++;
            }

            if (akses.getdiagnosa_pasien() == true) {
                Panelmenu.add(btnDiagnosa);
                jmlmenu++;
            }

            if (akses.getinput_kode_icd() == true) {
                Panelmenu.add(btnInputKodeICD);
                jmlmenu++;
            }

            if (akses.getdata_HAIs() == true) {
                Panelmenu.add(btnDataHAIs);
                jmlmenu++;
            }

            if (akses.getresume_pasien() == true) {
                Panelmenu.add(btnResume);
                jmlmenu++;
            }

            if (akses.getmaster_masalah_keperawatan() == true) {
                Panelmenu.add(btnMasterMasalahKeperawatan);
                jmlmenu++;
            }

            if (akses.getikhtisar_perawatan_hiv() == true) {
                Panelmenu.add(btnIkhtisarPerawatanHIV);
                jmlmenu++;
            }

            if (akses.getmaster_faskes() == true) {
                Panelmenu.add(btnMasterFaskes);
                jmlmenu++;
            }
            
            if (akses.getassesmen_gizi_harian() == true) {
                Panelmenu.add(btnMasterDiagnosaGizi);
                jmlmenu++;
            }

            if (akses.getmaster_cara_bayar() == true) {
                Panelmenu.add(btnMasterCaraBayar);
                jmlmenu++;
            }
        } else if (cmbMenu.getSelectedIndex() == 12) {
            jmlmenu = 0;
            if (akses.getutd_medis_rusak() == true) {
                Panelmenu.add(btnUTDMedisRusak);
                jmlmenu++;
            }

            if (akses.getpengambilan_penunjang_utd2() == true) {
                Panelmenu.add(btnPengambilanPenunjangUTD2);
                jmlmenu++;
            }

            if (akses.getutd_penunjang_rusak() == true) {
                Panelmenu.add(btnUTDPenunjangRusak);
                jmlmenu++;
            }

            if (akses.getutd_komponen_darah() == true) {
                Panelmenu.add(btnUTDKomponenDarah);
                jmlmenu++;
            }

            if (akses.getutd_donor() == true) {
                Panelmenu.add(btnUTDDonorDarah);
                jmlmenu++;
            }

            if (akses.getutd_cekal_darah() == true) {
                Panelmenu.add(btnUTDCekalDarah);
                jmlmenu++;
            }

            if (akses.getutd_pemisahan_darah() == true) {
                Panelmenu.add(btnUTDPemisahanDarah);
                jmlmenu++;
            }

            if (akses.getutd_stok_darah() == true) {
                Panelmenu.add(btnUTDStokDarah);
                jmlmenu++;
            }

            if (akses.getpemasukan_lain() == true) {
                Panelmenu.add(btnUTDPenyerahanDarah);
                jmlmenu++;
            }
            
            if (akses.getutd_stok_darah() == true) {
                Panelmenu.add(btnUTDPenyerahanDarahDirawat);
                jmlmenu++;
            }
        } else if (cmbMenu.getSelectedIndex() == 13) {
            jmlmenu = 0;
            Panelmenu.add(btnIndikatorNasionalMutu);
            Panelmenu.add(btnLaporanIndikatorMutu);
            jmlmenu++;
            
            if (akses.getgrafik_kunjungan_poli() == true) {
                Panelmenu.add(btnGrafikKunjunganPoli);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_perdokter() == true) {
                Panelmenu.add(btnGrafikKunjunganPerDokter);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_perpekerjaan() == true) {
                Panelmenu.add(btnGrafikKunjunganPerPekerjaan);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_perpendidikan() == true) {
                Panelmenu.add(btnGrafikKunjunganPerPendidikan);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_pertahun() == true) {
                Panelmenu.add(btnGrafikKunjunganPerTahun);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_perbulan() == true) {
                Panelmenu.add(btnGrafikKunjunganPerBulan);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_pertanggal() == true) {
                Panelmenu.add(btnGrafikKunjunganPerTanggal);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_demografi() == true) {
                Panelmenu.add(btnGrafikDemografiRegistrasi);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusdaftartahun() == true) {
                Panelmenu.add(btnGrafikStatusRegPerTahun);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusdaftartahun2() == true) {
                Panelmenu.add(btnGrafikStatusRegPerTahun2);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusdaftarbulan() == true) {
                Panelmenu.add(btnGrafikStatusRegPerBulan);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusdaftarbulan2() == true) {
                Panelmenu.add(btnGrafikStatusRegPerBulan2);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusdaftartanggal() == true) {
                Panelmenu.add(btnGrafikStatusRegPerTanggal);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusdaftartanggal2() == true) {
                Panelmenu.add(btnGrafikStatusRegPerTanggal2);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusbataltahun() == true) {
                Panelmenu.add(btnGrafikStatusRegBatalPerTahun);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusbatalbulan() == true) {
                Panelmenu.add(btnGrafikStatusRegBatalPerBulan);
                jmlmenu++;
            }

            if (akses.getgrafik_kunjungan_statusbataltanggal() == true) {
                Panelmenu.add(btnGrafikStatusRegBatalPerTanggal);
                jmlmenu++;
            }
        } else if (cmbMenu.getSelectedIndex() == 14) {
            jmlmenu = 0;
            if (akses.getaplikasi() == true) {
                Panelmenu.add(btnSetupAplikasi);
                jmlmenu++;
            }

            if (akses.getadmin() == true) {
                Panelmenu.add(btnAdmin);
                jmlmenu++;
            }

            if (akses.getset_bridging() == true) {
                Panelmenu.add(btnSetingBridging);
                jmlmenu++;
            }

            if (akses.getsetup_pjlab() == true) {
                Panelmenu.add(btnSetPenjab);
                jmlmenu++;
            }
            
            if (akses.getadmin() == true) {
                Panelmenu.add(btnHistoryLoginUser);
                jmlmenu++;
            }

            if (akses.getsetup_otolokasi() == true) {
                Panelmenu.add(btnSetupOtoLokasi);
                jmlmenu++;
            }

            if (akses.getsetup_jam_kamin() == true) {
                Panelmenu.add(btnSetupJamInap);
                jmlmenu++;
            }

            if (akses.getset_harga_kamar() == true) {
                Panelmenu.add(btnSetHargaKamar);
                jmlmenu++;
            }

            if (akses.getsetup_embalase() == true) {
                Panelmenu.add(btnSetupEmbalase);
                jmlmenu++;
            }

            if (akses.getuser() == true) {
                Panelmenu.add(btnUser);
                jmlmenu++;
            }

            if (akses.gettracer_login() == true) {
                Panelmenu.add(btnTracker);
                jmlmenu++;
            }

            if (akses.getdisplay() == true) {
                Panelmenu.add(btnAntrian);
                jmlmenu++;
            }

            if (akses.getset_harga_obat() == true) {
                Panelmenu.add(btnSetupHarga);
                jmlmenu++;
            }

            if (akses.getset_harga_obat_ralan() == true) {
                Panelmenu.add(btnSetObatRalan);
                jmlmenu++;
            }

            if (akses.getset_harga_obat_ranap() == true) {
                Panelmenu.add(btnSetObatRanap);
                jmlmenu++;
            }

            if (akses.getset_penggunaan_tarif() == true) {
                Panelmenu.add(btnSetupTarif);
                jmlmenu++;
            }

            if (akses.getset_oto_ralan() == true) {
                Panelmenu.add(btnSetOtoRalan);
                jmlmenu++;
            }

            if (akses.getbiaya_harian() == true) {
                Panelmenu.add(btnSetBiayaHarian);
                jmlmenu++;
            }

            if (akses.getbiaya_masuk_sekali() == true) {
                Panelmenu.add(btnSetBiayaMasukSekali);
                jmlmenu++;
            }

            if (akses.getset_no_rm() == true) {
                Panelmenu.add(btnSetupRM);
                jmlmenu++;
            }

            if (akses.getset_nota() == true) {
                Panelmenu.add(btnSetupNota);
                jmlmenu++;
            }

            if (akses.getclosing_kasir() == true) {
                Panelmenu.add(btnClosingKasir);
                jmlmenu++;
            }

            if (akses.getketerlambatan_presensi() == true) {
                Panelmenu.add(btnKeterlambatanPresensi);
                jmlmenu++;
            }
        }
    }

    private void isCariKosong() {
        jmlmenu = 0;
        Panelmenu.add(btnIndikatorNasionalMutu);
        Panelmenu.add(btnLaporanIndikatorMutu);
        jmlmenu++;
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnMasterNomorDokumenRM);
            jmlmenu++;
        }

        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnReferensiDokterSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnReferensiPasienSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnMapingOrganisasiSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnMapingLokasiSatuSehat);
            jmlmenu++;
        }

        if (akses.getadmin() == true) {
            Panelmenu.add(btnBPJSReferensiPoliApotek);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnBPJSReferensiObatDPHO);
            jmlmenu++;
        }

        if (akses.getadmin() == true) {
            Panelmenu.add(btnBPJSReferensiSpesilistikApotek);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnBPJSReferensiSetingPPKApotek);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnBPJSReferensiObatApotek);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnBPJSPencarianSEPApotek);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnBPJSMonitoringKlaimApotek);
            jmlmenu++;
        }

        if (akses.getstok_obat_pasien() == true) {
            Panelmenu.add(btnBPJSDataTerkirimApotek);
            jmlmenu++;
        }
        
        if (akses.getstok_obat_pasien() == true) {
            Panelmenu.add(btnBPJSMapingObatApotek);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnMapingVaksinSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getstok_obat_pasien() == true) {
            Panelmenu.add(btnMapingObatSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimEncounterSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimConditionSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimObservationSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimProsedurSatuSehat);
            jmlmenu++;
        }

        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimImunisasiSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimClinicalSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimDietSatuSehat);
            jmlmenu++;
        }

        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimMedicationRequestSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat() == true) {
            Panelmenu.add(btnKirimMedicationDispenseSatuSehat);
            jmlmenu++;
        }
        
        if (akses.getkemenkes_sitt() == true) {
            Panelmenu.add(btnSpirometri);
            jmlmenu++;
        }
        
        if (akses.getsisrute_rujukan_masuk() == true) {
            Panelmenu.add(btnRujukanMasukSisrute);
            jmlmenu++;
        }

        if (akses.getkemenkes_sitt() == true) {
            Panelmenu.add(btnKemenkesSITB);
            jmlmenu++;
        }

        if (akses.getset_bridging() == true) {
            Panelmenu.add(btnSetingBridging);
            jmlmenu++;
        }

        if (akses.getkemenkes_kanker() == true) {
            Panelmenu.add(btnKemenkesKanker);
            jmlmenu++;
        }

        if (akses.getsisrute_rujukan_keluar() == true) {
            Panelmenu.add(btnRujukanKeluarSisrute);
            jmlmenu++;
        }

        if (akses.getsisrute_referensi_alasanrujuk() == true) {
            Panelmenu.add(btnCekSisruteAlasanRujuk);
            jmlmenu++;
        }

        if (akses.getsisrute_referensi_diagnosa() == true) {
            Panelmenu.add(btnCekSisruteDiagnosa);
            jmlmenu++;
        }
        
        if (akses.getrekam_psikologis() == true) {
            Panelmenu.add(btnRekamPsikologisDewasa);
            jmlmenu++;
        }
        
        if (akses.getrekam_psikologis() == true) {
            Panelmenu.add(btnRekamPsikologisAnak);
            jmlmenu++;
        }
        
        if (akses.getrekam_psikologis() == true) {
            Panelmenu.add(btnRekamPsikologiPerkawinan);
            jmlmenu++;
        }

        if (akses.getRencanaKontrolJKN() == true) {
            Panelmenu.add(btnRencanaKontrolBPJS);
            jmlmenu++;
        }

        if (akses.getkendali_mutu_kendali_biaya_inacbg() == true) {
            Panelmenu.add(btnKendaliMutuKendaliBiayaINACBG);
            jmlmenu++;
        }

        if (akses.getjkn_belum_diproses_klaim() == true) {
            Panelmenu.add(btnINACBGjknBelumDiklaim);
            jmlmenu++;
        }

        if (akses.getinacbg_klaim_raza() == true) {
            Panelmenu.add(btnBridgingEklaimINACBG);
            jmlmenu++;
        }

        if (akses.getpengajuan_klaim_raza() == true) {
            Panelmenu.add(btnPengajuanKlaimINACBGrz);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnMasterJenisDokumenJangMed);
            jmlmenu++;
        }
        
        if (akses.getpic_kmkp() == true) {
            Panelmenu.add(btnMasterIndikatorMutuLayanan);
            jmlmenu++;
        }
        
        if (akses.getpic_kmkp() == true) {
            Panelmenu.add(btnMasterNumdenom);
            jmlmenu++;
        }
        
        if (akses.getrekam_psikologis() == true) {
            Panelmenu.add(btnMasterKeluhanPsikologis);
            jmlmenu++;
        }
        
        if (akses.getrekam_psikologis() == true) {
            Panelmenu.add(btnMasterRencanaTritmenPsikologis);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnMasterResikoDecubitus);
            jmlmenu++;
        }
        
        if (akses.getdata_triase_igd() == true) {
            Panelmenu.add(btnMasterFaktorResikoJatuh);
            jmlmenu++;
        }
        
        if (akses.getmaster_masalah_keperawatan() == true) {
            Panelmenu.add(btnMasterMasalahKeperawatan);
            jmlmenu++;
        }

        if (akses.getikhtisar_perawatan_hiv() == true) {
            Panelmenu.add(btnIkhtisarPerawatanHIV);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnMasterKasusPersalinanDinkes);
            jmlmenu++;
        }
        
        if (akses.getdata_persalinan() == true) {
            Panelmenu.add(btnKasusPersalinanDinkes);
            jmlmenu++;
        }
        
        if (akses.getmaster_cara_bayar() == true) {
            Panelmenu.add(btnMasterCaraBayar);
            jmlmenu++;
        }

        if (akses.getsisrute_referensi_faskes() == true) {
            Panelmenu.add(btnCekSisruteFaskes);
            jmlmenu++;
        }
        
        if (akses.getpenyakit() == true) {
            Panelmenu.add(btnQuerySql);
            jmlmenu++;
        }

        if (akses.getinput_kode_icd() == true) {
            Panelmenu.add(btnInputKodeICD);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnProgramPRBBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnCekReferensiPendaftaranMobileJKNBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnCekReferensiBatalDaftarMobileJKNBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnCekReferensiPoliHFISBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnCekReferensiJadwalHFISBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnCekReferensiDokterHFISBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnHistoriPelayananPesertaBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnKlaimJaminanJasaRaharja);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnDataSuplesiJasaRaharja);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnDataSEPIndukKLLJasaRaharja);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnListSaranaRujukanBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnCekReferensiDiagnosaPRBBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnDataNomorSuratKontrolBPJS);
            jmlmenu++;
        }

        if (akses.getbpjsSEPinternal() == true) {
            Panelmenu.add(btnCekSEPInternalBPJS);
            jmlmenu++;
        }

        if (akses.getpermintaan_lab() == true) {
            Panelmenu.add(btnPermintaanLab);
            jmlmenu++;
        }

        if (akses.getpermintaan_radiologi() == true) {
            Panelmenu.add(btnPermintaanRadiologi);
            jmlmenu++;
        }

        if (akses.getdata_ponek() == true) {
            Panelmenu.add(btnPasienPonek);
            jmlmenu++;
        }

        if (akses.getpasien_corona() == true) {
            Panelmenu.add(btnPasienCorona);
            jmlmenu++;
        }

        if (akses.getdiagnosa_pasien_corona() == true) {
            Panelmenu.add(btnDiagnosaPasienCorona);
            jmlmenu++;
        }

        if (akses.getperawatan_pasien_corona() == true) {
            Panelmenu.add(btnPerawatanPasienCorona);
            jmlmenu++;
        }

        if (akses.getharianhaisinap() == true) {
            Panelmenu.add(btnHarianHAIsRanap);
            jmlmenu++;
        }

        if (akses.getharianhaisjalan() == true) {
            Panelmenu.add(btnHarianHAIsRalan);
            jmlmenu++;
        }

        if (akses.getbulananhaisinap() == true) {
            Panelmenu.add(btnBulananHAIsRanap);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnHistoryLoginUser);
            jmlmenu++;
        }

        if (akses.getbulananhaisjalan() == true) {
            Panelmenu.add(btnBulananHAIsRalan);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnListSpesialistikRujukanBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnCekReferensiObatPRBBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_propinsi() == true) {
            Panelmenu.add(btnCekReferensiPropinsiBPJS);
            jmlmenu++;
        }

        if (akses.getjumlah_macam_diet() == true) {
            Panelmenu.add(btnJumlahMacamDiet);
            jmlmenu++;
        }

        if (akses.getjumlah_porsi_diet() == true) {
            Panelmenu.add(btnJumlahPorsiDiet);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_kabupaten() == true) {
            Panelmenu.add(btnCekReferensiKabupatenBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_kecamatan() == true) {
            Panelmenu.add(btnCekReferensiKecamatanBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_pasca_pulang() == true) {
            Panelmenu.add(btnCekReferensiPascaPulangBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_cara_keluar() == true) {
            Panelmenu.add(btnCekReferensiCaraKeluarBPJS);
            jmlmenu++;
        }

        if (akses.getmaster_faskes() == true) {
            Panelmenu.add(btnMasterFaskes);
            jmlmenu++;
        }

        if (akses.getassesmen_gizi_harian() == true) {
            Panelmenu.add(btnMasterDiagnosaGizi);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_kartu() == true) {
            Panelmenu.add(btnCekFingerPrinBPJS);
            jmlmenu++;
        }

        if (akses.getSPRIJKN() == true) {
            Panelmenu.add(btnSPRIbpjsVclaim);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_ruang_rawat() == true) {
            Panelmenu.add(btnCekReferensiRuangRawatBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_spesialistik() == true) {
            Panelmenu.add(btnCekReferensiSpesialistikBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_dokter() == true) {
            Panelmenu.add(btnCekReferensiDokterBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_tgl_rujukan() == true) {
            Panelmenu.add(btnCekBPJSTanggalRujukan);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_dpjp() == true) {
            Panelmenu.add(btnCekReferensiDokterDPJPBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_prosedur() == true) {
            Panelmenu.add(btnCekReferensiProsedurBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_referensi_kelas_rawat() == true) {
            Panelmenu.add(btnCekReferensiKelasRawatBPJS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_no_rujukan_rs() == true) {
            Panelmenu.add(btnCekBPJSNomorRujukanRS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_rujukan_kartu_pcare() == true) {
            Panelmenu.add(btnCekBPJSRujukanKartuPCare);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_riwayat_rujukan_pcare() == true) {
            Panelmenu.add(btnBPJScekRiwayatRujukanPcare);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_riwayat_rujukan_rs() == true) {
            Panelmenu.add(btnCekBPJSRiwayatRujukanRS);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_rujukan_kartu_rs() == true) {
            Panelmenu.add(btnCekBPJSRujukanKartuRS);
            jmlmenu++;
        }

        if (akses.getdata_persalinan() == true) {
            Panelmenu.add(btnDataPersalinan);
            jmlmenu++;
        }

        if (akses.getbpjs_rujukan_keluar() == true) {
            Panelmenu.add(btnRujukKeluarVclaim);
            jmlmenu++;
        }

        if (akses.getperiksa_lab() == true) {
            Panelmenu.add(btnLaboratorium);
            jmlmenu++;
        }

        if (akses.getperiksa_radiologi() == true) {
            Panelmenu.add(btnPeriksaRadiologi);
            jmlmenu++;
        }
        
        if (akses.geticd9() == true) {
            Panelmenu.add(btnICD9);
            jmlmenu++;
        }

        if (akses.getpenyakit() == true) {
            Panelmenu.add(btnICD);
            jmlmenu++;
        }

        if (akses.getpenyakit() == true) {
            Panelmenu.add(btnMasterDTD);
            jmlmenu++;
        }

        if (akses.getpenyakit_pd3i() == true) {
            Panelmenu.add(btnPenyakitPD3I);
            jmlmenu++;
        }

        if (akses.getsurveilans_pd3i() == true) {
            Panelmenu.add(btnSurveilansPD3I);
            jmlmenu++;
        }

        if (akses.getsurveilans_ralan() == true) {
            Panelmenu.add(btnSurveilansRalan);
            jmlmenu++;
        }

        if (akses.getsurveilans_ranap() == true) {
            Panelmenu.add(btnSurveilansRanap);
            jmlmenu++;
        }

        if (akses.getpny_takmenular_ralan() == true) {
            Panelmenu.add(btnPnyTakMenularRalan);
            jmlmenu++;
        }

        if (akses.getpny_takmenular_ranap() == true) {
            Panelmenu.add(btnPnyTakMenularRanap);
            jmlmenu++;
        }

        if (akses.getpenyakit_menular_ralan() == true) {
            Panelmenu.add(btnPnyMenularRalan);
            jmlmenu++;
        }

        if (akses.getpenyakit_menular_ranap() == true) {
            Panelmenu.add(btnPnyMenularRanap);
            jmlmenu++;
        }

        if (akses.getskdp_bpjs() == true) {
            Panelmenu.add(btnSKDPbpjs);
            jmlmenu++;
        }

        if (akses.getobat_penyakit() == true) {
            Panelmenu.add(btnObatPenyakit);
            jmlmenu++;
        }

        if (akses.getpenyakit_ralan() == true) {
            Panelmenu.add(btnFrekuensiRalan);
            jmlmenu++;
        }

        if (akses.getpenyakit_ranap() == true) {
            Panelmenu.add(btnFrekuensiRanap);
            jmlmenu++;
        }

        if (akses.getkunjungan_ralan() == true) {
            Panelmenu.add(btnKunjunganRalan);
            jmlmenu++;
        }

        if (akses.getkunjungan_ranap() == true) {
            Panelmenu.add(btnKunjunganRanap);
            jmlmenu++;
        }

        if (akses.getsensus_harian_poli() == true) {
            Panelmenu.add(btnSensusHarianPoli);
            jmlmenu++;
        }

        if (akses.getrl32() == true) {
            Panelmenu.add(btnRl32);
            jmlmenu++;
        }

        if (akses.getrl33() == true) {
            Panelmenu.add(btnRl33);
            jmlmenu++;
        }

        if (akses.getrl34() == true) {
            Panelmenu.add(btnRl34);
            jmlmenu++;
        }

        if (akses.getrl36() == true) {
            Panelmenu.add(btnRl36);
            jmlmenu++;
        }

        if (akses.getrl37() == true) {
            Panelmenu.add(btnRl37);
            jmlmenu++;
        }

        if (akses.getrl38() == true) {
            Panelmenu.add(btnRl38);
            jmlmenu++;
        }

        if (akses.getrl4a() == true) {
            Panelmenu.add(btnRl4a);
            jmlmenu++;
        }

        if (akses.getrl4b() == true) {
            Panelmenu.add(btnRl4b);
            jmlmenu++;
        }

        if (akses.getrl4asebab() == true) {
            Panelmenu.add(btnRl4asebab);
            jmlmenu++;
        }

        if (akses.getrl4bsebab() == true) {
            Panelmenu.add(btnRl4bsebab);
            jmlmenu++;
        }

        if (akses.getlama_pelayanan_ralan() == true) {
            Panelmenu.add(btnLamaPelayananRalan);
            jmlmenu++;
        }

        if (akses.getlama_pelayanan_apotek() == true) {
            Panelmenu.add(btnLamaPelayananApotek);
            jmlmenu++;
        }

        if (akses.getharian_HAIs() == true) {
            Panelmenu.add(btnHarianHAIsRS);
            jmlmenu++;
        }

        if (akses.getbulanan_HAIs() == true) {
            Panelmenu.add(btnBulananHAIsRS);
            jmlmenu++;
        }

        if (akses.getkamar() == true) {
            Panelmenu.add(btnKamar);
            jmlmenu++;
        }

        if (akses.gettarif_ralan() == true) {
            Panelmenu.add(btnTindakanRalan);
            jmlmenu++;
        }

        if (akses.gettarif_ranap() == true) {
            Panelmenu.add(btnTindakanRanap);
            jmlmenu++;
        }

        if (akses.gettarif_lab() == true) {
            Panelmenu.add(btnTarifLab);
            jmlmenu++;
        }

        if (akses.gettarif_radiologi() == true) {
            Panelmenu.add(btnTarifRadiologi);
            jmlmenu++;
        }

        if (akses.gettarif_operasi() == true) {
            Panelmenu.add(btnPaketOperasi);
            jmlmenu++;
        }

        if (akses.gettarif_utd() == true) {
            Panelmenu.add(btnTarifUtd);
            jmlmenu++;
        }

        if (akses.getakun_rekening() == true) {
            Panelmenu.add(btnRekening);
            jmlmenu++;
        }

        if (akses.getrekening_tahun() == true) {
            Panelmenu.add(btnRekeningTahun);
            jmlmenu++;
        }

        if (akses.getakun_bayar() == true) {
            Panelmenu.add(btnakun_bayar);
            jmlmenu++;
        }

        if (akses.getakun_piutang() == true) {
            Panelmenu.add(btnAkunPiutang);
            jmlmenu++;
        }

        if (akses.getpengaturan_rekening() == true) {
            Panelmenu.add(btnPengaturanRekening);
            jmlmenu++;
        }

        if (akses.getpengeluaran() == true) {
            Panelmenu.add(btnPengeluaran);
            jmlmenu++;
        }

        if (akses.getpemasukan_lain() == true) {
            Panelmenu.add(btnPemasukanLain);
            jmlmenu++;
        }

        if (akses.getrincian_piutang_pasien() == true) {
            Panelmenu.add(btnRincianPiutangPasien);
            jmlmenu++;
        }

        if (akses.getpiutang_pasien2() == true) {
            Panelmenu.add(btnPiutangBelumLunas);
            jmlmenu++;
        }

        if (akses.getdetail_piutang_penjab() == true) {
            Panelmenu.add(btnPiutangPerCaraBayar);
            jmlmenu++;
        }

        if (akses.getbayar_piutang() == true) {
            Panelmenu.add(btnBayarPiutang);
            jmlmenu++;
        }

        if (akses.gethutang_obat() == true) {
            Panelmenu.add(btnHutangObat);
            jmlmenu++;
        }

        if (akses.getbayar_pemesanan_obat() == true) {
            Panelmenu.add(btnbayar_pemesanan);
            jmlmenu++;
        }

        if (akses.getposting_jurnal() == true) {
            Panelmenu.add(btnPostingJurnal);
            jmlmenu++;
        }

        if (akses.getjurnal_harian() == true) {
            Panelmenu.add(btnJurnalHarian);
            jmlmenu++;
        }

        if (akses.getbuku_besar() == true) {
            Panelmenu.add(btnBubes);
            jmlmenu++;
        }

        if (akses.getcashflow() == true) {
            Panelmenu.add(btnCashFlow);
            jmlmenu++;
        }

        if (akses.getkeuangan() == true) {
            Panelmenu.add(btnLabaRugi);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_kartu() == true) {
            Panelmenu.add(btnCekBPJSKartu);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_nik() == true) {
            Panelmenu.add(btnCekBPJSNik);
            jmlmenu++;
        }

        if (akses.getbpjs_cek_nomor_rujukan() == true) {
            Panelmenu.add(btnCekBPJSNomorRujukanPCare);
            jmlmenu++;
        }

        if (akses.getbpjs_referensi_diagnosa() == true) {
            Panelmenu.add(btnCekBPJSDiagnosa);
            jmlmenu++;
        }

        if (akses.getbpjs_referensi_poli() == true) {
            Panelmenu.add(btnCekBPJSPoli);
            jmlmenu++;
        }

        if (akses.getbpjs_referensi_faskes() == true) {
            Panelmenu.add(btnCekBPJSFaskes);
            jmlmenu++;
        }

        if (akses.getbpjs_sep() == true) {
            Panelmenu.add(btnBPJSSEP);
            jmlmenu++;
        }

        if (akses.getbpjs_monitoring_klaim() == true) {
            Panelmenu.add(btnMonitoringKlaimBPJS);
            jmlmenu++;
        }

        if (akses.getaplicare_referensi_kamar() == true) {
            Panelmenu.add(btnAplicareReferensiKamar);
            jmlmenu++;
        }

        if (akses.getaplicare_ketersediaan_kamar() == true) {
            Panelmenu.add(btnAplicareKetersediaanKamar);
            jmlmenu++;
        }

        if (akses.getinacbg_coder_nik() == true) {
            Panelmenu.add(btnInaCBGCoderNIK);
            jmlmenu++;
        }

        if (akses.getpcare_cek_penyakit() == true) {
            Panelmenu.add(btnCekPCareDiagnosa);
            jmlmenu++;
        }

        if (akses.getperusahaan_pasien() == true) {
            Panelmenu.add(btnPerusahaan);
            jmlmenu++;
        }
        
        if (akses.getadmin() == true) {
            Panelmenu.add(btnPasienBlackList);
            jmlmenu++;
        }

        if (akses.getpasien() == true) {
            Panelmenu.add(btnPasien);
            jmlmenu++;
        }

        if (akses.getkelahiran_bayi() == true) {
            Panelmenu.add(btnLahir);
            jmlmenu++;
        }

        if (akses.getcatatan_pasien() == true) {
            Panelmenu.add(btnCatatanPasien);
            jmlmenu++;
        }

        if (akses.getpasien_meninggal() == true) {
            Panelmenu.add(btnPasienMati);
            jmlmenu++;
        }

        if (akses.getdiagnosa_pasien() == true) {
            Panelmenu.add(btnDiagnosa);
            jmlmenu++;
        }

        if (akses.getdata_HAIs() == true) {
            Panelmenu.add(btnDataHAIs);
            jmlmenu++;
        }

        if (akses.getresume_pasien() == true) {
            Panelmenu.add(btnResume);
            jmlmenu++;
        }

        if (akses.getpengambilan_utd2() == true) {
            Panelmenu.add(btnPengambilanUTD2);
            jmlmenu++;
        }

        if (akses.getutd_medis_rusak() == true) {
            Panelmenu.add(btnUTDMedisRusak);
            jmlmenu++;
        }

        if (akses.getpengambilan_penunjang_utd2() == true) {
            Panelmenu.add(btnPengambilanPenunjangUTD2);
            jmlmenu++;
        }

        if (akses.getutd_penunjang_rusak() == true) {
            Panelmenu.add(btnUTDPenunjangRusak);
            jmlmenu++;
        }

        if (akses.getutd_komponen_darah() == true) {
            Panelmenu.add(btnUTDKomponenDarah);
            jmlmenu++;
        }

        if (akses.getutd_donor() == true) {
            Panelmenu.add(btnUTDDonorDarah);
            jmlmenu++;
        }

        if (akses.getutd_cekal_darah() == true) {
            Panelmenu.add(btnUTDCekalDarah);
            jmlmenu++;
        }

        if (akses.getutd_pemisahan_darah() == true) {
            Panelmenu.add(btnUTDPemisahanDarah);
            jmlmenu++;
        }

        if (akses.getutd_stok_darah() == true) {
            Panelmenu.add(btnUTDStokDarah);
            jmlmenu++;
        }

        if (akses.getpemasukan_lain() == true) {
            Panelmenu.add(btnUTDPenyerahanDarah);
            jmlmenu++;
        }
        
        if (akses.getutd_stok_darah() == true) {
            Panelmenu.add(btnUTDPenyerahanDarahDirawat);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_poli() == true) {
            Panelmenu.add(btnGrafikKunjunganPoli);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_perdokter() == true) {
            Panelmenu.add(btnGrafikKunjunganPerDokter);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_perpekerjaan() == true) {
            Panelmenu.add(btnGrafikKunjunganPerPekerjaan);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_perpendidikan() == true) {
            Panelmenu.add(btnGrafikKunjunganPerPendidikan);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_pertahun() == true) {
            Panelmenu.add(btnGrafikKunjunganPerTahun);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_perbulan() == true) {
            Panelmenu.add(btnGrafikKunjunganPerBulan);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_pertanggal() == true) {
            Panelmenu.add(btnGrafikKunjunganPerTanggal);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_demografi() == true) {
            Panelmenu.add(btnGrafikDemografiRegistrasi);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusdaftartahun() == true) {
            Panelmenu.add(btnGrafikStatusRegPerTahun);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusdaftartahun2() == true) {
            Panelmenu.add(btnGrafikStatusRegPerTahun2);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusdaftarbulan() == true) {
            Panelmenu.add(btnGrafikStatusRegPerBulan);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusdaftarbulan2() == true) {
            Panelmenu.add(btnGrafikStatusRegPerBulan2);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusdaftartanggal() == true) {
            Panelmenu.add(btnGrafikStatusRegPerTanggal);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusdaftartanggal2() == true) {
            Panelmenu.add(btnGrafikStatusRegPerTanggal2);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusbataltahun() == true) {
            Panelmenu.add(btnGrafikStatusRegBatalPerTahun);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusbatalbulan() == true) {
            Panelmenu.add(btnGrafikStatusRegBatalPerBulan);
            jmlmenu++;
        }

        if (akses.getgrafik_kunjungan_statusbataltanggal() == true) {
            Panelmenu.add(btnGrafikStatusRegBatalPerTanggal);
            jmlmenu++;
        }

        if (akses.getaplikasi() == true) {
            Panelmenu.add(btnSetupAplikasi);
            jmlmenu++;
        }

        if (akses.getadmin() == true) {
            Panelmenu.add(btnAdmin);
            jmlmenu++;
        }

        if (akses.getsetup_pjlab() == true) {
            Panelmenu.add(btnSetPenjab);
            jmlmenu++;
        }

        if (akses.getsetup_otolokasi() == true) {
            Panelmenu.add(btnSetupOtoLokasi);
            jmlmenu++;
        }

        if (akses.getsetup_jam_kamin() == true) {
            Panelmenu.add(btnSetupJamInap);
            jmlmenu++;
        }

        if (akses.getset_harga_kamar() == true) {
            Panelmenu.add(btnSetHargaKamar);
            jmlmenu++;
        }

        if (akses.getsetup_embalase() == true) {
            Panelmenu.add(btnSetupEmbalase);
            jmlmenu++;
        }

        if (akses.getuser() == true) {
            Panelmenu.add(btnUser);
            jmlmenu++;
        }

        if (akses.gettracer_login() == true) {
            Panelmenu.add(btnTracker);
            jmlmenu++;
        }

        if (akses.getdisplay() == true) {
            Panelmenu.add(btnAntrian);
            jmlmenu++;
        }

        if (akses.getset_harga_obat() == true) {
            Panelmenu.add(btnSetupHarga);
            jmlmenu++;
        }

        if (akses.getset_harga_obat_ralan() == true) {
            Panelmenu.add(btnSetObatRalan);
            jmlmenu++;
        }

        if (akses.getset_harga_obat_ranap() == true) {
            Panelmenu.add(btnSetObatRanap);
            jmlmenu++;
        }

        if (akses.getset_penggunaan_tarif() == true) {
            Panelmenu.add(btnSetupTarif);
            jmlmenu++;
        }

        if (akses.getset_oto_ralan() == true) {
            Panelmenu.add(btnSetOtoRalan);
            jmlmenu++;
        }

        if (akses.getbiaya_harian() == true) {
            Panelmenu.add(btnSetBiayaHarian);
            jmlmenu++;
        }

        if (akses.getbiaya_masuk_sekali() == true) {
            Panelmenu.add(btnSetBiayaMasukSekali);
            jmlmenu++;
        }

        if (akses.getset_no_rm() == true) {
            Panelmenu.add(btnSetupRM);
            jmlmenu++;
        }

        if (akses.getset_nota() == true) {
            Panelmenu.add(btnSetupNota);
            jmlmenu++;
        }

        if (akses.getclosing_kasir() == true) {
            Panelmenu.add(btnClosingKasir);
            jmlmenu++;
        }

        if (akses.getketerlambatan_presensi() == true) {
            Panelmenu.add(btnKeterlambatanPresensi);
            jmlmenu++;
        }
    }

    private void isCariIsi() {
        jmlmenu = 0;
        if (btnMasterNomorDokumenRM.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
            Panelmenu.add(btnMasterNomorDokumenRM);
            jmlmenu++;
        }
        
        if (btnIndikatorNasionalMutu.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
            Panelmenu.add(btnIndikatorNasionalMutu);
            jmlmenu++;
        }
        
        if (btnMasterJenisDokumenJangMed.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
            Panelmenu.add(btnMasterJenisDokumenJangMed);
            jmlmenu++;
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnReferensiDokterSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnReferensiDokterSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnReferensiPasienSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnReferensiPasienSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnMapingOrganisasiSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMapingOrganisasiSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnBPJSReferensiPoliApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSReferensiPoliApotek);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnBPJSReferensiObatDPHO.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSReferensiObatDPHO);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnBPJSReferensiSpesilistikApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSReferensiSpesilistikApotek);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnBPJSReferensiSetingPPKApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSReferensiSetingPPKApotek);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnBPJSReferensiObatApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSReferensiObatApotek);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnBPJSPencarianSEPApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSPencarianSEPApotek);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnBPJSMonitoringKlaimApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSMonitoringKlaimApotek);
                jmlmenu++;
            }
        }
        
        if (akses.getstok_obat_pasien()== true) {
            if (btnBPJSDataTerkirimApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSDataTerkirimApotek);
                jmlmenu++;
            }
        }
        
        if (akses.getstok_obat_pasien()== true) {
            if (btnBPJSMapingObatApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSMapingObatApotek);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnMapingLokasiSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMapingLokasiSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnMapingVaksinSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMapingVaksinSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getstok_obat_pasien()== true) {
            if (btnMapingObatSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMapingObatSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimEncounterSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimEncounterSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimConditionSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimConditionSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimObservationSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimObservationSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimProsedurSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimProsedurSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimImunisasiSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimImunisasiSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimClinicalSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimClinicalSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimDietSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimDietSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimMedicationRequestSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimMedicationRequestSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getsatu_sehat()== true) {
            if (btnKirimMedicationDispenseSatuSehat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKirimMedicationDispenseSatuSehat);
                jmlmenu++;
            }
        }
        
        if (akses.getkemenkes_sitt()== true) {
            if (btnSpirometri.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSpirometri);
                jmlmenu++;
            }
        }
        
        if (akses.getsisrute_referensi_alasanrujuk() == true) {
            if (btnCekSisruteAlasanRujuk.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekSisruteAlasanRujuk);
                jmlmenu++;
            }
        }

        if (akses.getset_bridging() == true) {
            if (btnSetingBridging.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetingBridging);
                jmlmenu++;
            }
        }

        if (akses.getkemenkes_sitt() == true) {
            if (btnKemenkesSITB.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKemenkesSITB);
                jmlmenu++;
            }
        }

        if (akses.getkemenkes_kanker() == true) {
            if (btnKemenkesKanker.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKemenkesKanker);
                jmlmenu++;
            }
        }

        if (akses.getsisrute_referensi_diagnosa() == true) {
            if (btnCekSisruteDiagnosa.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekSisruteDiagnosa);
                jmlmenu++;
            }
        }

        if (akses.getsisrute_referensi_faskes() == true) {
            if (btnCekSisruteFaskes.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekSisruteFaskes);
                jmlmenu++;
            }
        }

        if (akses.getsisrute_rujukan_keluar() == true) {
            if (btnRujukanKeluarSisrute.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRujukanKeluarSisrute);
                jmlmenu++;
            }
        }

        if (akses.getsisrute_rujukan_masuk() == true) {
            if (btnRujukanMasukSisrute.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRujukanMasukSisrute);
                jmlmenu++;
            }
        }
        
        if (akses.getpic_kmkp()== true) {
            if (btnMasterIndikatorMutuLayanan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterIndikatorMutuLayanan);
                jmlmenu++;
            }
        }
        
        if (akses.getpic_kmkp()== true) {
            if (btnMasterNumdenom.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterNumdenom);
                jmlmenu++;
            }
        }
        
        if (btnLaporanIndikatorMutu.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
            Panelmenu.add(btnLaporanIndikatorMutu);
            jmlmenu++;
        }
        
        if (akses.getadmin()== true) {
            if (btnMasterResikoDecubitus.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterResikoDecubitus);
                jmlmenu++;
            }
        }
        
        if (akses.getdata_triase_igd()== true) {
            if (btnMasterFaktorResikoJatuh.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterFaktorResikoJatuh);
                jmlmenu++;
            }
        }
        
        if (akses.getrekam_psikologis() == true) {
            if (btnRekamPsikologisDewasa.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRekamPsikologisDewasa);
                jmlmenu++;
            }
        }
        
        if (akses.getrekam_psikologis() == true) {
            if (btnRekamPsikologisAnak.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRekamPsikologisAnak);
                jmlmenu++;
            }
        }
        
        if (akses.getrekam_psikologis() == true) {
            if (btnRekamPsikologiPerkawinan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRekamPsikologiPerkawinan);
                jmlmenu++;
            }
        }
        
        if (akses.getrekam_psikologis() == true) {
            if (btnMasterKeluhanPsikologis.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterKeluhanPsikologis);
                jmlmenu++;
            }
        }
        
        if (akses.getrekam_psikologis() == true) {
            if (btnMasterRencanaTritmenPsikologis.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterRencanaTritmenPsikologis);
                jmlmenu++;
            }
        }

        if (akses.getRencanaKontrolJKN() == true) {
            if (btnRencanaKontrolBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRencanaKontrolBPJS);
                jmlmenu++;
            }
        }

        if (akses.getjkn_belum_diproses_klaim() == true) {
            if (btnINACBGjknBelumDiklaim.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnINACBGjknBelumDiklaim);
                jmlmenu++;
            }
        }

        if (akses.getbpjsSEPinternal() == true) {
            if (btnCekSEPInternalBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekSEPInternalBPJS);
                jmlmenu++;
            }
        }

        if (akses.getSPRIJKN() == true) {
            if (btnSPRIbpjsVclaim.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSPRIbpjsVclaim);
                jmlmenu++;
            }
        }

        if (akses.getinacbg_klaim_raza() == true) {
            if (btnBridgingEklaimINACBG.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBridgingEklaimINACBG);
                jmlmenu++;
            }
        }

        if (akses.getpengajuan_klaim_raza() == true) {
            if (btnPengajuanKlaimINACBGrz.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPengajuanKlaimINACBGrz);
                jmlmenu++;
            }
        }
        
        if (akses.getdashboard_eResep() == true) {
            if (btnDashboardeResepRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDashboardeResepRalan);
                jmlmenu++;
            }
        }

        if (akses.getdashboard_eResep() == true) {
            if (btnDashboardeResepRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDashboardeResepRanap);
                jmlmenu++;
            }
        }

        if (akses.getikhtisar_perawatan_hiv() == true) {
            if (btnIkhtisarPerawatanHIV.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnIkhtisarPerawatanHIV);
                jmlmenu++;
            }
        }

        if (akses.getadmin() == true) {
            if (btnMasterKasusPersalinanDinkes.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterKasusPersalinanDinkes);
                jmlmenu++;
            }
        }
        
        if (akses.getdata_persalinan() == true) {
            if (btnKasusPersalinanDinkes.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKasusPersalinanDinkes);
                jmlmenu++;
            }
        }

        if (akses.getmaster_masalah_keperawatan() == true) {
            if (btnMasterMasalahKeperawatan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterMasalahKeperawatan);
                jmlmenu++;
            }
        }
        
        if (btnHistoryLoginUser.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
            Panelmenu.add(btnHistoryLoginUser);
            jmlmenu++;
        }

        if (akses.getmaster_cara_bayar() == true) {
            if (btnMasterCaraBayar.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterCaraBayar);
                jmlmenu++;
            }
        }

        if (akses.getdata_persalinan() == true) {
            if (btnDataPersalinan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDataPersalinan);
                jmlmenu++;
            }
        }

        if (akses.getjumlah_macam_diet() == true) {
            if (btnJumlahMacamDiet.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnJumlahMacamDiet);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_kartu() == true) {
            if (btnCekFingerPrinBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekFingerPrinBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnCekReferensiPoliHFISBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiPoliHFISBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnCekReferensiJadwalHFISBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiJadwalHFISBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnCekReferensiDokterHFISBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiDokterHFISBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnCekReferensiPendaftaranMobileJKNBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiPendaftaranMobileJKNBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnCekReferensiBatalDaftarMobileJKNBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiBatalDaftarMobileJKNBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnListSaranaRujukanBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnListSaranaRujukanBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnKlaimJaminanJasaRaharja.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKlaimJaminanJasaRaharja);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnDataSuplesiJasaRaharja.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDataSuplesiJasaRaharja);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnDataSEPIndukKLLJasaRaharja.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDataSEPIndukKLLJasaRaharja);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnHistoriPelayananPesertaBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnHistoriPelayananPesertaBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnDataNomorSuratKontrolBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDataNomorSuratKontrolBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnCekReferensiObatPRBBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiObatPRBBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnCekReferensiDiagnosaPRBBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiDiagnosaPRBBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnProgramPRBBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnProgramPRBBPJS);
                jmlmenu++;
            }
        }

        if (akses.getjumlah_porsi_diet() == true) {
            if (btnJumlahPorsiDiet.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnJumlahPorsiDiet);
                jmlmenu++;
            }
        }

        if (akses.getpermintaan_lab() == true) {
            if (btnPermintaanLab.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPermintaanLab);
                jmlmenu++;
            }
        }

        if (akses.getpermintaan_radiologi() == true) {
            if (btnPermintaanRadiologi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPermintaanRadiologi);
                jmlmenu++;
            }
        }

        if (akses.getkendali_mutu_kendali_biaya_inacbg() == true) {
            if (btnKendaliMutuKendaliBiayaINACBG.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKendaliMutuKendaliBiayaINACBG);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnListSpesialistikRujukanBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnListSpesialistikRujukanBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_propinsi() == true) {
            if (btnCekReferensiPropinsiBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiPropinsiBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_kabupaten() == true) {
            if (btnCekReferensiKabupatenBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiKabupatenBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_kecamatan() == true) {
            if (btnCekReferensiKecamatanBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiKecamatanBPJS);
                jmlmenu++;
            }
        }

        if (akses.getmaster_faskes() == true) {
            if (btnMasterFaskes.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterFaskes);
                jmlmenu++;
            }
        }
        
        if (akses.getassesmen_gizi_harian() == true) {
            if (btnMasterDiagnosaGizi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterDiagnosaGizi);
                jmlmenu++;
            }
        }

        if (akses.getharianhaisinap() == true) {
            if (btnHarianHAIsRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnHarianHAIsRanap);
                jmlmenu++;
            }
        }

        if (akses.getharianhaisjalan() == true) {
            if (btnHarianHAIsRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnHarianHAIsRalan);
                jmlmenu++;
            }
        }

        if (akses.getbulananhaisinap() == true) {
            if (btnBulananHAIsRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBulananHAIsRanap);
                jmlmenu++;
            }
        }

        if (akses.getbulananhaisjalan() == true) {
            if (btnBulananHAIsRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBulananHAIsRalan);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_pasca_pulang() == true) {
            if (btnCekReferensiPascaPulangBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiPascaPulangBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_cara_keluar() == true) {
            if (btnCekReferensiCaraKeluarBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiCaraKeluarBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_ruang_rawat() == true) {
            if (btnCekReferensiRuangRawatBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiRuangRawatBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_spesialistik() == true) {
            if (btnCekReferensiSpesialistikBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiSpesialistikBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_dokter() == true) {
            if (btnCekReferensiDokterBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiDokterBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_prosedur() == true) {
            if (btnCekReferensiProsedurBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiProsedurBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_dpjp() == true) {
            if (btnCekReferensiDokterDPJPBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiDokterDPJPBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_referensi_kelas_rawat() == true) {
            if (btnCekReferensiKelasRawatBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekReferensiKelasRawatBPJS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_tgl_rujukan() == true) {
            if (btnCekBPJSTanggalRujukan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSTanggalRujukan);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_no_rujukan_rs() == true) {
            if (btnCekBPJSNomorRujukanRS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSNomorRujukanRS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_rujukan_kartu_pcare() == true) {
            if (btnCekBPJSRujukanKartuPCare.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSRujukanKartuPCare);
                jmlmenu++;
            }
        }
        
        if (akses.getpenyakit()== true) {
            if (btnQuerySql.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnQuerySql);
                jmlmenu++;
            }
        }

        if (akses.getinput_kode_icd() == true) {
            if (btnInputKodeICD.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnInputKodeICD);
                jmlmenu++;
            }
        }

        if (akses.getdata_ponek() == true) {
            if (btnPasienPonek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPasienPonek);
                jmlmenu++;
            }
        }

        if (akses.getpasien_corona() == true) {
            if (btnPasienCorona.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPasienCorona);
                jmlmenu++;
            }
        }

        if (akses.getdiagnosa_pasien_corona() == true) {
            if (btnDiagnosaPasienCorona.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDiagnosaPasienCorona);
                jmlmenu++;
            }
        }

        if (akses.getperawatan_pasien_corona() == true) {
            if (btnPerawatanPasienCorona.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPerawatanPasienCorona);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_riwayat_rujukan_pcare() == true) {
            if (btnBPJScekRiwayatRujukanPcare.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJScekRiwayatRujukanPcare);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_riwayat_rujukan_rs() == true) {
            if (btnCekBPJSRiwayatRujukanRS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSRiwayatRujukanRS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_rujukan_kartu_rs() == true) {
            if (btnCekBPJSRujukanKartuRS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSRujukanKartuRS);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_rujukan_keluar() == true) {
            if (btnRujukKeluarVclaim.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRujukKeluarVclaim);
                jmlmenu++;
            }
        }

        if (akses.getskdp_bpjs() == true) {
            if (btnSKDPbpjs.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSKDPbpjs);
                jmlmenu++;
            }
        }

        if (akses.getperiksa_lab() == true) {
            if (btnLaboratorium.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnLaboratorium);
                jmlmenu++;
            }
        }

        if (akses.getperiksa_radiologi() == true) {
            if (btnPeriksaRadiologi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPeriksaRadiologi);
                jmlmenu++;
            }
        }

        if (akses.getpenjualan_obat() == true) {
            if (btnPenjualan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPenjualan);
                jmlmenu++;
            }
        }

        if (akses.getresep_dokter() == true) {
            if (btnDaftarPermintaanResep.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDaftarPermintaanResep);
                jmlmenu++;
            }
        }

        if (akses.geticd9() == true) {
            if (btnICD9.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnICD9);
                jmlmenu++;
            }
        }

        if (akses.getpenyakit() == true) {
            if (btnICD.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnICD);
                jmlmenu++;
            }
        }

        if (akses.getpenyakit() == true) {
            if (btnMasterDTD.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMasterDTD);
                jmlmenu++;
            }
        }

        if (akses.getpenyakit_pd3i() == true) {
            if (btnPenyakitPD3I.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPenyakitPD3I);
                jmlmenu++;
            }
        }

        if (akses.getsurveilans_pd3i() == true) {
            if (btnSurveilansPD3I.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSurveilansPD3I);
                jmlmenu++;
            }
        }

        if (akses.getsurveilans_ralan() == true) {
            if (btnSurveilansRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSurveilansRalan);
                jmlmenu++;
            }
        }

        if (akses.getsurveilans_ranap() == true) {
            if (btnSurveilansRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSurveilansRanap);
                jmlmenu++;
            }
        }

        if (akses.getpny_takmenular_ralan() == true) {
            if (btnPnyTakMenularRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPnyTakMenularRalan);
                jmlmenu++;
            }
        }

        if (akses.getpny_takmenular_ranap() == true) {
            if (btnPnyTakMenularRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPnyTakMenularRanap);
                jmlmenu++;
            }
        }

        if (akses.getpenyakit_menular_ralan() == true) {
            if (btnPnyMenularRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPnyMenularRalan);
                jmlmenu++;
            }
        }

        if (akses.getpenyakit_menular_ranap() == true) {
            if (btnPnyMenularRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPnyMenularRanap);
                jmlmenu++;
            }
        }

        if (akses.getobat_penyakit() == true) {
            if (btnObatPenyakit.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnObatPenyakit);
                jmlmenu++;
            }
        }

        if (akses.getpenyakit_ralan() == true) {
            if (btnFrekuensiRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnFrekuensiRalan);
                jmlmenu++;
            }
        }

        if (akses.getpenyakit_ranap() == true) {
            if (btnFrekuensiRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnFrekuensiRanap);
                jmlmenu++;
            }
        }

        if (akses.getkunjungan_ralan() == true) {
            if (btnKunjunganRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKunjunganRalan);
                jmlmenu++;
            }
        }

        if (akses.getkunjungan_ranap() == true) {
            if (btnKunjunganRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKunjunganRanap);
                jmlmenu++;
            }
        }

        if (akses.getsensus_harian_poli() == true) {
            if (btnSensusHarianPoli.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSensusHarianPoli);
                jmlmenu++;
            }
        }

        if (akses.getrl32() == true) {
            if (btnRl32.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl32);
                jmlmenu++;
            }
        }

        if (akses.getrl33() == true) {
            if (btnRl33.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl33);
                jmlmenu++;
            }
        }

        if (akses.getrl34() == true) {
            if (btnRl34.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl34);
                jmlmenu++;
            }
        }

        if (akses.getrl36() == true) {
            if (btnRl36.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl36);
                jmlmenu++;
            }
        }

        if (akses.getrl37() == true) {
            if (btnRl37.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl37);
                jmlmenu++;
            }
        }

        if (akses.getrl38() == true) {
            if (btnRl38.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl38);
                jmlmenu++;
            }
        }

        if (akses.getrl4a() == true) {
            if (btnRl4a.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl4a);
                jmlmenu++;
            }
        }

        if (akses.getrl4b() == true) {
            if (btnRl4b.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl4b);
                jmlmenu++;
            }
        }

        if (akses.getrl4asebab() == true) {
            if (btnRl4asebab.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl4asebab);
                jmlmenu++;
            }
        }

        if (akses.getrl4bsebab() == true) {
            if (btnRl4bsebab.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRl4bsebab);
                jmlmenu++;
            }
        }

        if (akses.getlama_pelayanan_ralan() == true) {
            if (btnLamaPelayananRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnLamaPelayananRalan);
                jmlmenu++;
            }
        }

        if (akses.getlama_pelayanan_apotek() == true) {
            if (btnLamaPelayananApotek.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnLamaPelayananApotek);
                jmlmenu++;
            }
        }

        if (akses.getharian_HAIs() == true) {
            if (btnHarianHAIsRS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnHarianHAIsRS);
                jmlmenu++;
            }
        }

        if (akses.getbulanan_HAIs() == true) {
            if (btnBulananHAIsRS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBulananHAIsRS);
                jmlmenu++;
            }
        }

        if (akses.getkamar() == true) {
            if (btnKamar.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKamar);
                jmlmenu++;
            }
        }

        if (akses.gettarif_ralan() == true) {
            if (btnTindakanRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnTindakanRalan);
                jmlmenu++;
            }
        }

        if (akses.gettarif_ranap() == true) {
            if (btnTindakanRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnTindakanRanap);
                jmlmenu++;
            }
        }

        if (akses.gettarif_lab() == true) {
            if (btnTarifLab.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnTarifLab);
                jmlmenu++;
            }
        }

        if (akses.gettarif_radiologi() == true) {
            if (btnTarifRadiologi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnTarifRadiologi);
                jmlmenu++;
            }
        }

        if (akses.gettarif_operasi() == true) {
            if (btnPaketOperasi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPaketOperasi);
                jmlmenu++;
            }
        }

        if (akses.gettarif_utd() == true) {
            if (btnTarifUtd.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnTarifUtd);
                jmlmenu++;
            }
        }

        if (akses.getakun_rekening() == true) {
            if (btnRekening.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRekening);
                jmlmenu++;
            }
        }

        if (akses.getrekening_tahun() == true) {
            if (btnRekeningTahun.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRekeningTahun);
                jmlmenu++;
            }
        }

        if (akses.getakun_bayar() == true) {
            if (btnakun_bayar.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnakun_bayar);
                jmlmenu++;
            }
        }

        if (akses.getakun_piutang() == true) {
            if (btnAkunPiutang.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnAkunPiutang);
                jmlmenu++;
            }
        }

        if (akses.getpengaturan_rekening() == true) {
            if (btnPengaturanRekening.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPengaturanRekening);
                jmlmenu++;
            }
        }

        if (akses.getpengeluaran() == true) {
            if (btnPengeluaran.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPengeluaran);
                jmlmenu++;
            }
        }

        if (akses.getpemasukan_lain() == true) {
            if (btnPemasukanLain.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPemasukanLain);
                jmlmenu++;
            }
        }

        if (akses.getrincian_piutang_pasien() == true) {
            if (btnRincianPiutangPasien.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnRincianPiutangPasien);
                jmlmenu++;
            }
        }

        if (akses.getpiutang_pasien2() == true) {
            if (btnPiutangBelumLunas.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPiutangBelumLunas);
                jmlmenu++;
            }
        }

        if (akses.getdetail_piutang_penjab() == true) {
            if (btnPiutangPerCaraBayar.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPiutangPerCaraBayar);
                jmlmenu++;
            }
        }

        if (akses.getbayar_piutang() == true) {
            if (btnBayarPiutang.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBayarPiutang);
                jmlmenu++;
            }
        }

        if (akses.gethutang_obat() == true) {
            if (btnHutangObat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnHutangObat);
                jmlmenu++;
            }
        }

        if (akses.getbayar_pemesanan_obat() == true) {
            if (btnbayar_pemesanan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnbayar_pemesanan);
                jmlmenu++;
            }
        }

        if (akses.getposting_jurnal() == true) {
            if (btnPostingJurnal.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPostingJurnal);
                jmlmenu++;
            }
        }

        if (akses.getjurnal_harian() == true) {
            if (btnJurnalHarian.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnJurnalHarian);
                jmlmenu++;
            }
        }

        if (akses.getbuku_besar() == true) {
            if (btnBubes.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBubes);
                jmlmenu++;
            }
        }

        if (akses.getcashflow() == true) {
            if (btnCashFlow.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCashFlow);
                jmlmenu++;
            }
        }

        if (akses.getkeuangan() == true) {
            if (btnLabaRugi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnLabaRugi);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_kartu() == true) {
            if (btnCekBPJSKartu.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSKartu);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_nik() == true) {
            if (btnCekBPJSNik.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSNik);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_cek_nomor_rujukan() == true) {
            if (btnCekBPJSNomorRujukanPCare.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSNomorRujukanPCare);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_referensi_diagnosa() == true) {
            if (btnCekBPJSDiagnosa.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSDiagnosa);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_referensi_poli() == true) {
            if (btnCekBPJSPoli.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSPoli);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_referensi_faskes() == true) {
            if (btnCekBPJSFaskes.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekBPJSFaskes);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_sep() == true) {
            if (btnBPJSSEP.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnBPJSSEP);
                jmlmenu++;
            }
        }

        if (akses.getbpjs_monitoring_klaim() == true) {
            if (btnMonitoringKlaimBPJS.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnMonitoringKlaimBPJS);
                jmlmenu++;
            }
        }

        if (akses.getaplicare_referensi_kamar() == true) {
            if (btnAplicareReferensiKamar.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnAplicareReferensiKamar);
                jmlmenu++;
            }
        }

        if (akses.getaplicare_ketersediaan_kamar() == true) {
            if (btnAplicareKetersediaanKamar.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnAplicareKetersediaanKamar);
                jmlmenu++;
            }
        }

        if (akses.getinacbg_coder_nik() == true) {
            if (btnInaCBGCoderNIK.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnInaCBGCoderNIK);
                jmlmenu++;
            }
        }

        if (akses.getpcare_cek_penyakit() == true) {
            if (btnCekPCareDiagnosa.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCekPCareDiagnosa);
                jmlmenu++;
            }
        }

        if (akses.getperusahaan_pasien() == true) {
            if (btnPerusahaan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPerusahaan);
                jmlmenu++;
            }
        }
        
        if (akses.getadmin()== true) {
            if (btnPasienBlackList.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPasienBlackList);
                jmlmenu++;
            }
        }

        if (akses.getpasien() == true) {
            if (btnPasien.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPasien);
                jmlmenu++;
            }
        }

        if (akses.getkelahiran_bayi() == true) {
            if (btnLahir.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnLahir);
                jmlmenu++;
            }
        }

        if (akses.getcatatan_pasien() == true) {
            if (btnCatatanPasien.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnCatatanPasien);
                jmlmenu++;
            }
        }

        if (akses.getpasien_meninggal() == true) {
            if (btnPasienMati.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPasienMati);
                jmlmenu++;
            }
        }

        if (akses.getdiagnosa_pasien() == true) {
            if (btnDiagnosa.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDiagnosa);
                jmlmenu++;
            }
        }

        if (akses.getdata_HAIs() == true) {
            if (btnDataHAIs.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnDataHAIs);
                jmlmenu++;
            }
        }

        if (akses.getresume_pasien() == true) {
            if (btnResume.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnResume);
                jmlmenu++;
            }
        }

        if (akses.getpengambilan_utd2() == true) {
            if (btnPengambilanUTD2.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPengambilanUTD2);
                jmlmenu++;
            }
        }

        if (akses.getutd_medis_rusak() == true) {
            if (btnUTDMedisRusak.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDMedisRusak);
                jmlmenu++;
            }
        }

        if (akses.getpengambilan_penunjang_utd2() == true) {
            if (btnPengambilanPenunjangUTD2.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnPengambilanPenunjangUTD2);
                jmlmenu++;
            }
        }

        if (akses.getutd_penunjang_rusak() == true) {
            if (btnUTDPenunjangRusak.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDPenunjangRusak);
                jmlmenu++;
            }
        }

        if (akses.getutd_komponen_darah() == true) {
            if (btnUTDKomponenDarah.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDKomponenDarah);
                jmlmenu++;
            }
        }

        if (akses.getutd_donor() == true) {
            if (btnUTDDonorDarah.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDDonorDarah);
                jmlmenu++;
            }
        }

        if (akses.getutd_cekal_darah() == true) {
            if (btnUTDCekalDarah.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDCekalDarah);
                jmlmenu++;
            }
        }

        if (akses.getutd_pemisahan_darah() == true) {
            if (btnUTDPemisahanDarah.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDPemisahanDarah);
                jmlmenu++;
            }
        }

        if (akses.getutd_stok_darah() == true) {
            if (btnUTDStokDarah.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDStokDarah);
                jmlmenu++;
            }
        }

        if (akses.getpemasukan_lain() == true) {
            if (btnUTDPenyerahanDarah.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDPenyerahanDarah);
                jmlmenu++;
            }
        }
        
        if (akses.getutd_stok_darah() == true) {
            if (btnUTDPenyerahanDarahDirawat.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUTDPenyerahanDarahDirawat);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_poli() == true) {
            if (btnGrafikKunjunganPoli.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikKunjunganPoli);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_perdokter() == true) {
            if (btnGrafikKunjunganPerDokter.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikKunjunganPerDokter);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_perpekerjaan() == true) {
            if (btnGrafikKunjunganPerPekerjaan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikKunjunganPerPekerjaan);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_perpendidikan() == true) {
            if (btnGrafikKunjunganPerPendidikan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikKunjunganPerPendidikan);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_pertahun() == true) {
            if (btnGrafikKunjunganPerTahun.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikKunjunganPerTahun);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_perbulan() == true) {
            if (btnGrafikKunjunganPerBulan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikKunjunganPerBulan);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_pertanggal() == true) {
            if (btnGrafikKunjunganPerTanggal.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikKunjunganPerTanggal);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_demografi() == true) {
            if (btnGrafikDemografiRegistrasi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikDemografiRegistrasi);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusdaftartahun() == true) {
            if (btnGrafikStatusRegPerTahun.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegPerTahun);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusdaftartahun2() == true) {
            if (btnGrafikStatusRegPerTahun2.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegPerTahun2);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusdaftarbulan() == true) {
            if (btnGrafikStatusRegPerBulan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegPerBulan);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusdaftarbulan2() == true) {
            if (btnGrafikStatusRegPerBulan2.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegPerBulan2);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusdaftartanggal() == true) {
            if (btnGrafikStatusRegPerTanggal.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegPerTanggal);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusdaftartanggal2() == true) {
            if (btnGrafikStatusRegPerTanggal2.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegPerTanggal2);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusbataltahun() == true) {
            if (btnGrafikStatusRegBatalPerTahun.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegBatalPerTahun);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusbatalbulan() == true) {
            if (btnGrafikStatusRegBatalPerBulan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegBatalPerBulan);
                jmlmenu++;
            }
        }

        if (akses.getgrafik_kunjungan_statusbataltanggal() == true) {
            if (btnGrafikStatusRegBatalPerTanggal.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnGrafikStatusRegBatalPerTanggal);
                jmlmenu++;
            }
        }

        if (akses.getaplikasi() == true) {
            if (btnSetupAplikasi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetupAplikasi);
                jmlmenu++;
            }
        }

        if (akses.getadmin() == true) {
            if (btnAdmin.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnAdmin);
                jmlmenu++;
            }
        }

        if (akses.getsetup_pjlab() == true) {
            if (btnSetPenjab.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetPenjab);
                jmlmenu++;
            }
        }

        if (akses.getsetup_otolokasi() == true) {
            if (btnSetupOtoLokasi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetupOtoLokasi);
                jmlmenu++;
            }
        }

        if (akses.getsetup_jam_kamin() == true) {
            if (btnSetupJamInap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetupJamInap);
                jmlmenu++;
            }
        }

        if (akses.getset_harga_kamar() == true) {
            if (btnSetHargaKamar.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetHargaKamar);
                jmlmenu++;
            }
        }

        if (akses.getsetup_embalase() == true) {
            if (btnSetupEmbalase.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetupEmbalase);
                jmlmenu++;
            }
        }

        if (akses.getuser() == true) {
            if (btnUser.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnUser);
                jmlmenu++;
            }
        }

        if (akses.gettracer_login() == true) {
            if (btnTracker.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnTracker);
                jmlmenu++;
            }
        }

        if (akses.getdisplay() == true) {
            if (btnAntrian.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnAntrian);
                jmlmenu++;
            }
        }

        if (akses.getset_harga_obat() == true) {
            if (btnSetupHarga.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetupHarga);
                jmlmenu++;
            }
        }

        if (akses.getset_harga_obat_ralan() == true) {
            if (btnSetObatRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetObatRalan);
                jmlmenu++;
            }
        }

        if (akses.getset_harga_obat_ranap() == true) {
            if (btnSetObatRanap.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetObatRanap);
                jmlmenu++;
            }
        }

        if (akses.getset_penggunaan_tarif() == true) {
            if (btnSetupTarif.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetupTarif);
                jmlmenu++;
            }
        }

        if (akses.getset_oto_ralan() == true) {
            if (btnSetOtoRalan.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetOtoRalan);
                jmlmenu++;
            }
        }

        if (akses.getbiaya_harian() == true) {
            if (btnSetBiayaHarian.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetBiayaHarian);
                jmlmenu++;
            }
        }

        if (akses.getbiaya_masuk_sekali() == true) {
            if (btnSetBiayaMasukSekali.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetBiayaMasukSekali);
                jmlmenu++;
            }
        }

        if (akses.getset_no_rm() == true) {
            if (btnSetupRM.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetupRM);
                jmlmenu++;
            }
        }

        if (akses.getset_nota() == true) {
            if (btnSetupNota.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnSetupNota);
                jmlmenu++;
            }
        }

        if (akses.getclosing_kasir() == true) {
            if (btnClosingKasir.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnClosingKasir);
                jmlmenu++;
            }
        }

        if (akses.getketerlambatan_presensi() == true) {
            if (btnKeterlambatanPresensi.getText().toLowerCase().trim().contains(TCari.getText().toLowerCase().trim())) {
                Panelmenu.add(btnKeterlambatanPresensi);
                jmlmenu++;
            }
        }
    }
    
    private void notifAlarmLain() {
        try {
            music = new BackgroundMusic("./suara/paman_pentol.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void notifAlarmIGD() {
        try {
            music = new BackgroundMusic("./suara/resep_IGD.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void notifAlarmResepRanap() {
        try {
            music = new BackgroundMusic("./suara/resep_rawat_inap.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void notifAlarmResepRanapCito() {
        try {
            music = new BackgroundMusic("./suara/resep_cito_rawat_inap.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void notifAlarmLab() {
        try {
            music = new BackgroundMusic("./suara/permintaan_periksa_laboratorium_diterima.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void notifAlarmRad() {
        try {
            music = new BackgroundMusic("./suara/permintaan_periksa_radiologi_diterima.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void otomatisRefreshNotifApt() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (akses.getNotifApotek().equals("yes")) {
                    //jika apotek sentral ranap
                    if (akses.getkdbangsal().equals("APT02")) {
                        if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where status='belum' and jenis_resep='CITO' and "
                                + "tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0
                                || Sequel.cariInteger("select count(-1) from catatan_resep_ranap_antibiotik where status='belum' and jenis_resep='CITO' and "
                                        + "tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0) {
                            notifAlarmResepRanapCito();
                        } else if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where status='belum' and "
                                + "tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0
                                || Sequel.cariInteger("select count(-1) from catatan_resep_ranap_antibiotik where status='belum' and "
                                        + "tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0) {
                            notifAlarmResepRanap();
                        }

                        //jika apotek igd
                    } else if (akses.getkdbangsal().equals("APT01")) {
                        if (Sequel.cariInteger("select count(-1) from catatan_resep c inner join reg_periksa r on r.no_rawat=c.no_rawat where "
                                + "r.status_lanjut='Ralan' and c.status='belum' and r.kd_poli='igdk' and "
                                + "c.tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0
                                || Sequel.cariInteger("select count(-1) from catatan_resep_antibiotik c inner join reg_periksa r on r.no_rawat=c.no_rawat where "
                                        + "r.status_lanjut='Ralan' and c.status='belum' and r.kd_poli='igdk' and "
                                        + "c.tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0) {
                            notifAlarmIGD();
                        }

                        if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where status='belum' and jenis_resep='CITO' and "
                                + "tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0
                                || Sequel.cariInteger("select count(-1) from catatan_resep_ranap_antibiotik where status='belum' and jenis_resep='CITO' and "
                                        + "tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0) {
                            notifAlarmResepRanapCito();
                        } else if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where status='belum' and "
                                + "tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0
                                || Sequel.cariInteger("select count(-1) from catatan_resep_ranap_antibiotik where status='belum' and "
                                        + "tgl_perawatan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0) {
                            notifAlarmResepRanap();
                        }
                    }
                }
            }
        };
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        //interval 600000 ms = 10 menit atau setngah menit
        akses.tRefreshNotifApotek = new Timer(600000, taskPerformer);
    }
    
    private void otomatisRefreshNotifLab() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (akses.getNotifLab().equals("yes")) {
                    if (Sequel.cariInteger("SELECT count(-1) FROM permintaan_lab_raza where status_rawat='Ralan' and status_periksa='belum' and "
                            + "tgl_permintaan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0
                            || Sequel.cariInteger("SELECT count(-1) FROM permintaan_lab_raza where status_rawat='Ranap' and status_periksa='belum' and "
                                    + "tgl_permintaan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0) {
                        notifAlarmLab();
                    } 
                }
            }
        };
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        //interval 300000 ms = 5 menit atau setngah menit
        akses.tRefreshNotifLab = new Timer(300000, taskPerformer);
    }
    
    private void otomatisRefreshNotifRad() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (akses.getNotifRad().equals("yes")) {
                    if (Sequel.cariInteger("SELECT count(-1) FROM permintaan_radiologi where status='Belum' and "
                            + "tgl_permintaan between DATE_SUB(DATE_FORMAT(NOW(),'%Y-%m-%d'), INTERVAL 1 DAY) and DATE_FORMAT(NOW(),'%Y-%m-%d')") > 0) {
                        notifAlarmRad();
                    }
                }
            }
        };
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        //interval 300000 ms = 5 menit atau setngah menit
        akses.tRefreshNotifRad = new Timer(300000, taskPerformer);
    }
    
    private void cekNotifApotek() {
        cek = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            cek = prop.getProperty("NOTIFAPOTEK").toString();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
        akses.setNotifApotek(cek);
    }
    
    private void cekNotifLab() {
        cek = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            cek = prop.getProperty("NOTIFLABORATORIUM").toString();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
        akses.setNotifLab(cek);
    }
    
    private void cekNotifRad() {
        cek = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            cek = prop.getProperty("NOTIFRADIOLOGI").toString();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
        akses.setNotifRad(cek);
    }

    private void cekApotek() {
        cekApt = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            cekApt = prop.getProperty("APOTEK").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        akses.setkdbangsal(cekApt);
    }
    
    private void tampilIpAddress() {
        ipKomputer = "";
        String ipAddresKomputer = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            ipAddresKomputer = ip.getHostAddress();
            lblIPaddress.setText("IP Address : " + ipAddresKomputer);
            ipKomputer = ipAddresKomputer;
        } catch (Exception e) {            
            System.out.println("Gagal mendapatkan alamat IP host: " + e.getMessage());
        }
    }
    
    private void autoRestartKomputer() {
        try {
            System.out.println("Menjalankan perintah restart...");

            String os = System.getProperty("os.name").toLowerCase();
            Process process;

            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("cmd /c shutdown -r -t 0");
            } else if (os.contains("linux") || os.contains("unix")) {
                process = new ProcessBuilder("reboot").start();
            } else if (os.contains("mac")) {
                process = new ProcessBuilder("shutdown", "-r", "now").start();
            } else {
                JOptionPane.showMessageDialog(null, "Sistem operasi tidak dikenali: " + os);
                return;
            }

            // Baca output error jika ada
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = errorReader.readLine()) != null) {
                System.out.println("Error restart: " + line);
            }

            process.waitFor();
            System.out.println("Perintah restart telah dijalankan. Komputer akan segera restart.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan dalam proses restart: " + e.getMessage());
        }
    }
    
    private void cekKomputer() {
        String setTgl = "";
        int nilaiJam = 0;
        setTgl = Sequel.cariIsi("select date(now())");
        nilaiJam = Sequel.cariInteger("select time(now()) >='00:05:00'");
        
        try {
            ps1 = koneksi.prepareStatement("select * from setting");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    //eksekusi restart
                    if (rs1.getString("auto_restart").equals("ya") && sttsFileSIMRS.equals("file belum update")) {
                        if (rs1.getString("ip_addres_tertentu").equals("ya")) {
                            if (rs1.getString("ip_eksekusi").equals(ipKomputer)) {
                                if (rs1.getString("periode_restart").equals("Setiap Hari")) {
                                    if (nilaiJam > 0) {
                                        autoRestartKomputer();
                                    }
                                } else if (rs1.getString("periode_restart").equals("Tanggal")) {
                                    if (rs1.getString("tgl_restart").equals(setTgl) && nilaiJam > 0) {
                                        autoRestartKomputer();
                                    }
                                }
                            } else {
                                System.out.println("IP address komputer tujuan restart salah..!!!");
                            }
                        } else {
                            if (rs1.getString("periode_restart").equals("Setiap Hari")) {
                                if (nilaiJam > 0) {
                                    autoRestartKomputer();
                                }
                            } else if (rs1.getString("periode_restart").equals("Tanggal")) {
                                if (rs1.getString("tgl_restart").equals(setTgl) && nilaiJam > 0) {
                                    autoRestartKomputer();
                                }
                            }
                        }
                    }
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

    private void cekVersi() {
        //cek jenis os dulu
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            String filePath = Sequel.cariFolderVersi() + "conf_versi.txt";
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }

                // Tampilkan isi file ke JTextField
                Tversi.setText(content.toString());

                System.out.println("Versi berhasil dibaca dari file : " + filePath);
            } catch (IOException e) {
                System.err.println("Gagal membaca file versi : " + e.getMessage());
                Tversi.setText("-"); // kosongkan jika gagal
            }

        } else if (os.contains("linux") || os.contains("unix") || os.contains("mac")) {
            String filePath = Sequel.cariFolderVersi() + "/conf_versi.txt";
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }

                // Tampilkan isi file ke JTextField
                Tversi.setText(content.toString());

                System.out.println("Versi berhasil dibaca dari file : " + filePath);
            } catch (IOException e) {
                System.err.println("Gagal membaca file versi : " + e.getMessage());
                Tversi.setText("-"); // kosongkan jika gagal
            }
        } else {
            System.out.println("Sistem operasi tidak dikenali: " + os);
        }
    }
    
    private void cekUpdateFileOtomatis() {
        sttsFileSIMRS = "";
        try {
            // Dapatkan folder tempat aplikasi dijalankan
            String currentDir = System.getProperty("user.dir");
            File file = new File(currentDir, "SIMRSKhanza.jar");

//            if (!file.exists()) {
//                System.out.println("File tidak ditemukan di folder aplikasi: " + file.getAbsolutePath());
//                return;
//            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
            String waktu = sdf.format(new Date(file.lastModified()));
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String waktu1 = sdf1.format(new Date(file.lastModified()));
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String hari = sdf2.format(new Date(file.lastModified()));

            if (Sequel.cariIsi("select concat(tgl_update,' ',time_format(jam_update,'%H')) FROM history_update ORDER BY tgl_update desc, jam_update desc limit 1").equals(waktu)) {
                sttsFileSIMRS = "file simrs update";
            } else {
                sttsFileSIMRS = "file belum update";
            }
            System.out.println("File ditemukan di : " + file.getAbsolutePath() + "\n"
                    + "Terakhir update SIMRS pada hari : " + Sequel.hariINDONESIA("SELECT date_format('" + hari + "','%W')") + ", Tgl. " + waktu1 + " Wita");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cekUpdateFileOtomatisDev() {
        sttsFileSIMRS = "";
        try {
            // Dapatkan folder tempat aplikasi dijalankan
            String currentDir = System.getProperty("user.dir");
            File file = new File(currentDir, "SIMRSKhanzaS.jar");

//            if (!file.exists()) {
//                System.out.println("File tidak ditemukan di folder aplikasi: " + file.getAbsolutePath());
//                return;
//            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
            String waktu = sdf.format(new Date(file.lastModified()));
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String waktu1 = sdf1.format(new Date(file.lastModified()));
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String hari = sdf2.format(new Date(file.lastModified()));

            if (Sequel.cariIsi("select concat(tgl_update,' ',time_format(jam_update,'%H')) FROM history_update ORDER BY tgl_update desc, jam_update desc limit 1").equals(waktu)) {
                sttsFileSIMRS = "file simrs update";
            } else {
                sttsFileSIMRS = "file belum update";
            }
            System.out.println("File ditemukan di : " + file.getAbsolutePath() + "\n"
                    + "Terakhir update SIMRS pada hari : " + Sequel.hariINDONESIA("SELECT date_format('" + hari + "','%W')") + ", Tgl. " + waktu1 + " Wita");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void tampilMenuUtamaA() {
        if (menuUtamaA == null) {
            menuUtamaA = new PanelMenuUtamaA(this, false);
        }

        Panelmenu.removeAll();

        // Hapus ukuran lama dari susunan menu frmUtama
        Panelmenu.setPreferredSize(null);
        Panelmenu.setMinimumSize(new Dimension(0, 0));

        Panelmenu.setLayout(new BorderLayout());
        Panelmenu.add(
                menuUtamaA.getPanelUtama(),
                BorderLayout.CENTER
        );

        Panelmenu.revalidate();
        Panelmenu.repaint();

        menuUtamaA.tampilkanMenu();
    }
    
    private void tampilMenuUtamaB() {
        if (menuUtamaB == null) {
            menuUtamaB = new PanelMenuUtamaB(this, false);
        }

        Panelmenu.removeAll();

        // Hapus ukuran lama dari susunan menu frmUtama
        Panelmenu.setPreferredSize(null);
        Panelmenu.setMinimumSize(new Dimension(0, 0));

        Panelmenu.setLayout(new BorderLayout());
        Panelmenu.add(
                menuUtamaB.getPanelUtama(),
                BorderLayout.CENTER
        );

        Panelmenu.revalidate();
        Panelmenu.repaint();

        menuUtamaB.tampilkanMenu();
    }
    
    private void tampilMenuUtamaC() {
        if (menuUtamaC == null) {
            menuUtamaC = new PanelMenuUtamaC(this, false);
        }

        Panelmenu.removeAll();

        // Hapus ukuran lama dari susunan menu frmUtama
        Panelmenu.setPreferredSize(null);
        Panelmenu.setMinimumSize(new Dimension(0, 0));

        Panelmenu.setLayout(new BorderLayout());
        Panelmenu.add(
                menuUtamaC.getPanelUtama(),
                BorderLayout.CENTER
        );

        Panelmenu.revalidate();
        Panelmenu.repaint();

        menuUtamaC.tampilkanMenu();
    }
    
    private void tampilMenuUtamaD() {
        if (menuUtamaD == null) {
            menuUtamaD = new PanelMenuUtamaD(this, false);
        }

        Panelmenu.removeAll();

        // Hapus ukuran lama dari susunan menu frmUtama
        Panelmenu.setPreferredSize(null);
        Panelmenu.setMinimumSize(new Dimension(0, 0));

        Panelmenu.setLayout(new BorderLayout());
        Panelmenu.add(
                menuUtamaD.getPanelUtama(),
                BorderLayout.CENTER
        );

        Panelmenu.revalidate();
        Panelmenu.repaint();

        menuUtamaD.tampilkanMenu();
    }
    
    private void tampilMenuUtamaE() {
        if (menuUtamaE == null) {
            menuUtamaE = new PanelMenuUtamaE(this, false);
        }

        Panelmenu.removeAll();

        // Hapus ukuran lama dari susunan menu frmUtama
        Panelmenu.setPreferredSize(null);
        Panelmenu.setMinimumSize(new Dimension(0, 0));

        Panelmenu.setLayout(new BorderLayout());
        Panelmenu.add(
                menuUtamaE.getPanelUtama(),
                BorderLayout.CENTER
        );

        Panelmenu.revalidate();
        Panelmenu.repaint();

        menuUtamaE.tampilkanMenu();
    }
    
    private void tampilMenuUtamaF() {
        if (menuUtamaF == null) {
            menuUtamaF = new PanelMenuUtamaF(this, false);
        }

        Panelmenu.removeAll();

        // Hapus ukuran lama dari susunan menu frmUtama
        Panelmenu.setPreferredSize(null);
        Panelmenu.setMinimumSize(new Dimension(0, 0));

        Panelmenu.setLayout(new BorderLayout());
        Panelmenu.add(
                menuUtamaF.getPanelUtama(),
                BorderLayout.CENTER
        );

        Panelmenu.revalidate();
        Panelmenu.repaint();

        menuUtamaF.tampilkanMenu();
    }
    
    private void tampilMenuUtamaG() {
        if (menuUtamaG == null) {
            menuUtamaG = new PanelMenuUtamaG(this, false);
        }

        Panelmenu.removeAll();

        // Hapus ukuran lama dari susunan menu frmUtama
        Panelmenu.setPreferredSize(null);
        Panelmenu.setMinimumSize(new Dimension(0, 0));

        Panelmenu.setLayout(new BorderLayout());
        Panelmenu.add(
                menuUtamaG.getPanelUtama(),
                BorderLayout.CENTER
        );

        Panelmenu.revalidate();
        Panelmenu.repaint();

        menuUtamaG.tampilkanMenu();
    }
    
    private void tampilMenuUtamaH() {
        if (menuUtamaH == null) {
            menuUtamaH = new PanelMenuUtamaH(this, false);
        }

        Panelmenu.removeAll();

        // Hapus ukuran lama dari susunan menu frmUtama
        Panelmenu.setPreferredSize(null);
        Panelmenu.setMinimumSize(new Dimension(0, 0));

        Panelmenu.setLayout(new BorderLayout());
        Panelmenu.add(
                menuUtamaH.getPanelUtama(),
                BorderLayout.CENTER
        );

        Panelmenu.revalidate();
        Panelmenu.repaint();

        menuUtamaH.tampilkanMenu();
    }

    public void tampilkanDialogDiPanelUtama(javax.swing.JDialog dialog) {
        try {
            if (dialog == null) {
                throw new IllegalArgumentException("Form dialog tidak boleh null");
            }

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            if (dialog.isVisible()) {
                dialog.setVisible(false);
            }

            // Ambil isi form sebelum dialog ditutup.
            java.awt.Container isiDialog = dialog.getContentPane();

            // Lepaskan isi form dari JDialog.
            dialog.setContentPane(new javax.swing.JPanel());
            if (dialog.isDisplayable()) {
                dialog.dispose();
            }

            // Hapus hanya form yang sebelumnya tertanam.
            if (isiDialogAktifDiPanel != null && isiDialogAktifDiPanel.getParent() == PanelUtama) {
                PanelUtama.remove(isiDialogAktifDiPanel);
            }

            if (dialogAktifDiPanel != null && dialogAktifDiPanel != dialog) {
                dialogAktifDiPanel.setVisible(false);
                dialogAktifDiPanel.dispose();
            }

            dialogAktifDiPanel = dialog;
            isiDialogAktifDiPanel = isiDialog;

            /*
         * Sembunyikan halaman/menu utama.
         * Jangan removeAll().             */
            
            scrollPane1.setVisible(false);
            isiDialogAktifDiPanel.setMinimumSize(new java.awt.Dimension(0, 0));
            isiDialogAktifDiPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
            PanelUtama.setLayout(new java.awt.BorderLayout());
            PanelUtama.add(isiDialogAktifDiPanel, java.awt.BorderLayout.CENTER);
            isiDialogAktifDiPanel.setVisible(true);
            PanelUtama.revalidate();
            PanelUtama.repaint();

            javax.swing.SwingUtilities.invokeLater(() -> {
                if (isiDialogAktifDiPanel != null) {
                    isiDialogAktifDiPanel.revalidate();
                    isiDialogAktifDiPanel.repaint();
                }

                PanelUtama.revalidate();
                PanelUtama.repaint();
            });

            /*
         * Jangan dispose menuUtamaA karena isi menunya sedang
         * dipasang ke Panelmenu milik frmUtama.
             */
            DlgHome.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuka form di panel utama.\n" + "Form : " + (dialog == null ? "-" : dialog.getClass().getSimpleName())
                    + "\nError : " + e.getClass().getSimpleName() + "\nPesan : " + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void tutupDialogDiPanelUtama(javax.swing.JDialog dialog) {
        try {
            if (dialogAktifDiPanel == dialog) {

                if (isiDialogAktifDiPanel != null
                        && isiDialogAktifDiPanel.getParent() == PanelUtama) {

                    PanelUtama.remove(isiDialogAktifDiPanel);
                }

                isiDialogAktifDiPanel = null;
                dialogAktifDiPanel = null;

                // Tampilkan kembali halaman/menu frmUtama.
                scrollPane1.setVisible(true);

                PanelUtama.revalidate();
                PanelUtama.repaint();
            }

            if (dialog != null) {
                dialog.setVisible(false);
                dialog.dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menutup form di panel utama.\n" + e.getMessage());
        }
    }
    
    private void aturUkuranPanelMenu() {
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

        if (jmlmenu <= 20) {
            tinggi = scrollPane2.getHeight() - 5;
        } else if (jmlmenu <= 25) {
            tinggi = scrollPane2.getHeight()
                    + (scrollPane2.getHeight() / 4);
        } else {
            tinggi = 1;

            for (i = 25; i < jmlmenu; i++) {
                if (i % 5 == 0) {
                    tinggi++;
                }
            }

            tinggi = scrollPane2.getHeight()
                    + ((scrollPane2.getHeight() / 4) * tinggi);
        }

        Panelmenu.setLayout(new GridLayout(0, grid, 5, 5));

        Panelmenu.setPreferredSize(new Dimension(
                Math.max(scrollPane2.getWidth() - 10, 200),
                Math.max(tinggi, scrollPane2.getHeight() - 5)
        ));

        Panelmenu.revalidate();
        Panelmenu.repaint();

        scrollPane2.revalidate();
        scrollPane2.repaint();
    }
    
    public void tutupFormAktifDiPanelUtama() {
        try {
            // Hapus hanya isi form yang sedang tertanam.
            if (isiDialogAktifDiPanel != null
                    && isiDialogAktifDiPanel.getParent() == PanelUtama) {

                PanelUtama.remove(isiDialogAktifDiPanel);
            }

            // Tutup objek dialog aktif.
            if (dialogAktifDiPanel != null) {
                dialogAktifDiPanel.setVisible(false);
                dialogAktifDiPanel.dispose();
            }

            isiDialogAktifDiPanel = null;
            dialogAktifDiPanel = null;

            // Tampilkan kembali halaman utama.
            scrollPane1.setVisible(true);

            PanelUtama.revalidate();
            PanelUtama.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menutup form aktif saat logout.\n" + e.getMessage());
        }
    }

    private void bersihkanMenuSetelahLogout() {
        if (menuUtamaA != null) {
            menuUtamaA.getFormMenu().removeAll();
            menuUtamaA.getFormMenu().revalidate();
            menuUtamaA.getFormMenu().repaint();
        }

        if (menuUtamaB != null) {
            menuUtamaB.getFormMenu().removeAll();
            menuUtamaB.getFormMenu().revalidate();
            menuUtamaB.getFormMenu().repaint();
        }
        
        if (menuUtamaC != null) {
            menuUtamaC.getFormMenu().removeAll();
            menuUtamaC.getFormMenu().revalidate();
            menuUtamaC.getFormMenu().repaint();
        }
        
        if (menuUtamaD != null) {
            menuUtamaD.getFormMenu().removeAll();
            menuUtamaD.getFormMenu().revalidate();
            menuUtamaD.getFormMenu().repaint();
        }
        
        if (menuUtamaE != null) {
            menuUtamaE.getFormMenu().removeAll();
            menuUtamaE.getFormMenu().revalidate();
            menuUtamaE.getFormMenu().repaint();
        }
        
        if (menuUtamaF != null) {
            menuUtamaF.getFormMenu().removeAll();
            menuUtamaF.getFormMenu().revalidate();
            menuUtamaF.getFormMenu().repaint();
        }
        
        if (menuUtamaG != null) {
            menuUtamaG.getFormMenu().removeAll();
            menuUtamaG.getFormMenu().revalidate();
            menuUtamaG.getFormMenu().repaint();
        }
        
        if (menuUtamaH != null) {
            menuUtamaH.getFormMenu().removeAll();
            menuUtamaH.getFormMenu().revalidate();
            menuUtamaH.getFormMenu().repaint();
        }

        Panelmenu.removeAll();
        Panelmenu.revalidate();
        Panelmenu.repaint();
    }
}
