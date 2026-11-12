/*
 * Kontribusi dari Bibing, RSUD Ratu Zalecha
 */
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenUlangHemodialisis extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariDokter dokter;
    private String nipDokter1 = "", nipDokter2 = "", tglReg = "", jamReg = "", sttsrwt = "", cekSesak = "", cekMual = "", cekGatal = "", cekLainKeluhan = "", cekReguler = "",
            cekIreguler = "", cekNormal = "", cekKusmaul = "", cekDispnea = "", cekEdemaParu = "", cekLainRespi = "", cekTdkAnemis = "", cekAnemis = "",
            cekLainKonjung = "", cekTdkEdema = "", cekDehidrasi = "", cekEdema = "", cekEdemaAnas = "", cekPucat = "", cekAcites = "", cekAvFistula = "", cekFemoral = "",
            cekSub = "", cekJugular = "", cekFemoralHD = "", cekKanan = "", cekKiri = "", cekTglGizi = "", cekMst = "", cekTenang = "", cekGelisah = "", cekTakut = "",
            cekTindakanMrh = "", cekMudah = "", cekKelebihan = "", cekGangPerfusi = "", cekGangKeseimbangan = "", cekPenurunan = "", cekNutrisi = "",
            cekKetidakpatuhan = "", cekGangTerhadap = "", cekGangRasa = "", cekMonBerat = "", cekBerikan = "", cekObservasi = "", cekBila = "", cekHentikan = "",
            cekKaji = "", cekPosisikan = "", cekPenkes = "", cekMonTandaInfeksi = "", cekGanti = "", cekMonTandaHipo = "", cekLakukan = "", cekProgram = "",
            cekTransfusi = "", cekKolab = "", cekPemberianCa = "", cekPemberianAntipiretik = "", cekPemberianAnal = "", cekPemberianEry = "", cekPemberianPre = "",
            cekObat = "", cekPemberianAntibiotik = "", cekLainInterval = "", cekInisiasi = "", cekAkut = "", cekRutin = "", cekPreOp = "", cekSled = "",
            cekLainInstruksi = "", cekMasalah = "", cekPerdarahan = "", cekFirst = "", cekSakit = "", cekMualMuntah = "", cekKram = "", cekHiperkalemia = "",
            cekHipo = "", cekHipertensi = "", cekNyeri = "", cekAritmia = "", cekGatalGatal = "", cekDemam = "", cekMenggigil = "", cekLainPenyulit = "";
    private frmUtama formUtama;

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMAsesmenUlangHemodialisis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asesmen", "Jam", "Ruang Perawatan", "Nama Dokter",
            "nmDokter2", "ruang_rawat", "tgl_asesmen", "jam_asesmen", "no_mesin", "hemodialisis_ke", "tipe_dialisiser", "diagnosa_medis", "cc", "riwayat_alergi",
            "ket_riwayat_alergi", "sesak_nafas", "mual_muntah", "gatal", "lain_lain", "ket_lain_lain", "nyeri_nrs", "skala_nyeri", "ket_kronik", "keadaan_umum", "ket_keadaan_umum",
            "kesadaran", "td", "reguler", "ireguler", "nadi_frekuensi", "normal", "kusmaul", "dispnea", "edema_respirasi", "lain_respirasi", "ket_lain_respirasi",
            "respirasi_frekuensi", "tidak_anemis", "anemis", "lain_konjungtiva", "ket_lain_konjungtiva", "tidak_edema", "dehidrasi", "edema_ekstremitas", "edema_anasarka",
            "pucat", "acites", "pre_hd", "post_hd_yang_lalu", "bb_kering", "bb_post_hd", "av_fistula", "femoral_akses", "sub_clavian", "jugular", "femoral_hd", "kanan", "kiri",
            "kes_jatuh_pre_hd", "kes_jatuh_post_hd", "pemeriksaan_penunjang", "cek_tgl_gizi", "tgl_gizi", "mst", "skor_total", "rekomendasi", "kendala_komunikasi", "ket_kendala",
            "yang_merawat", "ket_yang_merawat", "tenang", "gelisah", "takut", "marah", "mudah", "diagnosis_keperawatan", "kelebihan_volume", "gangguan_perfusi",
            "gangguan_keseimbangan", "penurunan", "nutrisi", "ketidakpatuhan", "gangguan_terhadap", "gangguan_rasa", "monitor_berat", "berikan", "observasi", "bila_pasien",
            "hentikan", "kaji", "posisikan", "penkes", "ket_av_shunt", "monitor_tanda_infeksi", "ganti_balutan", "monitor_tanda_hipoglikemi", "lakukan", "program_hd", "pemberian_ery",
            "kolaborasi", "pemberian_pre", "pemberian_ca", "obat_emergency", "pemberian_antipiretik", "pemberian_antibiotik", "pemberian_analgesik", "lainnya_interval",
            "ket_lainnya_interval", "inisiasi", "akut", "rutin", "pre_op", "sled", "lainya_instruksi", "ket_lainnya_instruksi", "hd_jam", "qb", "qd", "uf_goal", "na", "uf",
            "bicarbonat", "dialisat_bicarbonat", "dialisat_conductivity", "dialisat_temperatur", "sirkulasi", "awal", "maintenance", "continues", "intermiten", "lmwh",
            "tanpa_heparin", "program_bilas", "catatan_lain", "nip_dokter1", "masalah_akses", "perdarahan", "first", "sakit_kepala", "penyulit_mual", "kram", "hiperkalemia",
            "hipotensi", "hipertensi", "nyeri_dada", "aritmia", "gatal_gatal", "demam", "menggigil", "lain_penyulit", "evaluasi_hd", "evaluasi_qb", "evaluasi_kt", "discharge_planing",
            "obat", "catatan_medis", "nip_dokter2", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 161; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 59) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 60) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 61) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 62) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 63) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 64) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 65) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 66) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 67) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 68) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 69) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 70) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 71) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 72) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 73) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 74) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 75) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 76) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 77) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 78) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 79) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 80) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 81) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 82) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 83) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 84) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 85) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 86) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 87) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 88) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 89) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 90) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 91) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 92) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 93) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 94) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 95) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 96) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 97) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 98) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 99) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 100) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 101) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 102) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 103) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 104) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 105) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 106) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 107) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 108) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 109) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 110) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 111) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 112) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 113) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 114) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 115) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 116) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 117) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 118) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 119) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 120) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 121) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 122) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 123) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 124) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 125) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 126) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 127) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 128) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 129) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 130) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 131) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 132) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 133) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 134) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 135) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 136) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 137) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 138) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 139) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 140) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 141) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 142) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 143) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 144) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 145) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 146) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 147) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 148) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 149) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 150) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 151) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 152) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 153) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 154) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 155) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 156) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 157) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 158) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 159) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 160) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());

        TnoMesin.setDocument(new batasInput((int) 50).getKata(TnoMesin));
        ThemoKe.setDocument(new batasInput((int) 5).getKata(ThemoKe));
        TtipeDialisiser.setDocument(new batasInput((int) 100).getKata(TtipeDialisiser));
        TdxMedis.setDocument(new batasInput((int) 200).getKata(TdxMedis));
        Tcc.setDocument(new batasInput((int) 10).getKata(Tcc));
        TketRiwAlergiObat.setDocument(new batasInput((int) 200).getKata(TketRiwAlergiObat));
        TlainKeluhan.setDocument(new batasInput((int) 50).getKata(TlainKeluhan));
        Tkronik.setDocument(new batasInput((int) 10).getKata(Tkronik));
        TlainKeadaanUmum.setDocument(new batasInput((int) 50).getKata(TlainKeadaanUmum));
        Ttd.setDocument(new batasInput((int) 10).getKata(Ttd));
        TfrekuensiNadi.setDocument(new batasInput((int) 10).getKata(TfrekuensiNadi));
        TlainRespirasi.setDocument(new batasInput((int) 50).getKata(TlainRespirasi));
        TfrekuensiRespirasi.setDocument(new batasInput((int) 10).getKata(TfrekuensiRespirasi));
        TlainKonjung.setDocument(new batasInput((int) 50).getKata(TlainKonjung));
        TpreHd.setDocument(new batasInput((int) 10).getKata(TpreHd));
        TposHdlalu.setDocument(new batasInput((int) 10).getKata(TposHdlalu));
        TbbKering.setDocument(new batasInput((int) 10).getKata(TbbKering));
        TbbPostHd.setDocument(new batasInput((int) 10).getKata(TbbPostHd));
        Tskor.setDocument(new batasInput((int) 10).getKata(Tskor));
        Trekomendasi.setDocument(new batasInput((int) 200).getKata(Trekomendasi));
        Tkendala.setDocument(new batasInput((int) 200).getKata(Tkendala));
        Tmerawat.setDocument(new batasInput((int) 200).getKata(Tmerawat));
        TdiagnosaKeper.setDocument(new batasInput((int) 200).getKata(TdiagnosaKeper));
        TketAvShunt.setDocument(new batasInput((int) 200).getKata(TketAvShunt));
        TketLainInterval.setDocument(new batasInput((int) 50).getKata(TketLainInterval));
        TketLainInstruksi.setDocument(new batasInput((int) 50).getKata(TketLainInstruksi));
        ThdInstruksi.setDocument(new batasInput((int) 10).getKata(ThdInstruksi));
        Tqb.setDocument(new batasInput((int) 10).getKata(Tqb));
        Tqd.setDocument(new batasInput((int) 10).getKata(Tqd));
        TufGoal.setDocument(new batasInput((int) 10).getKata(TufGoal));
        Tprog.setDocument(new batasInput((int) 50).getKata(Tprog));
        Tuf.setDocument(new batasInput((int) 50).getKata(Tuf));
        Tbicarbonat.setDocument(new batasInput((int) 50).getKata(Tbicarbonat));
        TdialBicar.setDocument(new batasInput((int) 50).getKata(TdialBicar));
        TdialConduc.setDocument(new batasInput((int) 50).getKata(TdialConduc));
        TdialTemp.setDocument(new batasInput((int) 50).getKata(TdialTemp));
        TdosisSirkulasi.setDocument(new batasInput((int) 10).getKata(TdosisSirkulasi));
        TdosisAwal.setDocument(new batasInput((int) 10).getKata(TdosisAwal));
        TdosisMain.setDocument(new batasInput((int) 50).getKata(TdosisMain));
        Tconti.setDocument(new batasInput((int) 10).getKata(Tconti));
        Tintermiten.setDocument(new batasInput((int) 10).getKata(Tintermiten));
        Tlmwh.setDocument(new batasInput((int) 50).getKata(Tlmwh));
        TtanpaHeparin.setDocument(new batasInput((int) 50).getKata(TtanpaHeparin));
        TevaluasiHd.setDocument(new batasInput((int) 10).getKata(TevaluasiHd));
        TevaluasiQb.setDocument(new batasInput((int) 10).getKata(TevaluasiQb));
        Tkt.setDocument(new batasInput((int) 50).getKata(Tkt));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));

        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel68 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel10 = new widget.Label();
        chkSesakNfs = new widget.CekBox();
        chkMual = new widget.CekBox();
        chkGatal = new widget.CekBox();
        chkLainKeluhan = new widget.CekBox();
        Tcc = new widget.TextBox();
        label38 = new widget.Label();
        ThemoKe = new widget.TextBox();
        Ttd = new widget.TextBox();
        label104 = new widget.Label();
        TfrekuensiNadi = new widget.TextBox();
        jLabel12 = new widget.Label();
        Ttanggal = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        TnoMesin = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        TtipeDialisiser = new widget.TextBox();
        jLabel73 = new widget.Label();
        TdxMedis = new widget.TextBox();
        jLabel74 = new widget.Label();
        cmbRiwAlergiObat = new widget.ComboBox();
        TketRiwAlergiObat = new widget.TextBox();
        jLabel75 = new widget.Label();
        TcaraByr = new widget.TextBox();
        jLabel76 = new widget.Label();
        Tpekerjaan = new widget.TextBox();
        jLabel77 = new widget.Label();
        TlainKeluhan = new widget.TextBox();
        jLabel78 = new widget.Label();
        cmbNyeriNrs = new widget.ComboBox();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel79 = new widget.Label();
        cmbSkalaNyeri = new widget.ComboBox();
        jLabel80 = new widget.Label();
        Tkronik = new widget.TextBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        cmbKeadaanUmum = new widget.ComboBox();
        TlainKeadaanUmum = new widget.TextBox();
        jLabel83 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        chkReguler = new widget.CekBox();
        chkIreguler = new widget.CekBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        chkNormal = new widget.CekBox();
        chkKusmaul = new widget.CekBox();
        chkDispnea = new widget.CekBox();
        chkEdemaParu = new widget.CekBox();
        chkLainRespirasi = new widget.CekBox();
        TlainRespirasi = new widget.TextBox();
        jLabel86 = new widget.Label();
        TfrekuensiRespirasi = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel87 = new widget.Label();
        chkTdkAnemis = new widget.CekBox();
        chkAnemis = new widget.CekBox();
        chkLainKonjung = new widget.CekBox();
        TlainKonjung = new widget.TextBox();
        jLabel88 = new widget.Label();
        chkTdkEdema = new widget.CekBox();
        chkDehidrasi = new widget.CekBox();
        chkEdema = new widget.CekBox();
        chkEdemaAna = new widget.CekBox();
        chkPucat = new widget.CekBox();
        chkAcites = new widget.CekBox();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        TpreHd = new widget.TextBox();
        label105 = new widget.Label();
        TposHdlalu = new widget.TextBox();
        label106 = new widget.Label();
        TbbKering = new widget.TextBox();
        label107 = new widget.Label();
        TbbPostHd = new widget.TextBox();
        label108 = new widget.Label();
        jLabel91 = new widget.Label();
        chkAvFistula = new widget.CekBox();
        chkFemoralAkses = new widget.CekBox();
        jLabel92 = new widget.Label();
        chkSubClavian = new widget.CekBox();
        chkJugular = new widget.CekBox();
        chkFemoralHD = new widget.CekBox();
        jLabel93 = new widget.Label();
        chkKanan = new widget.CekBox();
        chkKiri = new widget.CekBox();
        jLabel94 = new widget.Label();
        cmbRJpreHD = new widget.ComboBox();
        jLabel95 = new widget.Label();
        cmbRJpostHD = new widget.ComboBox();
        jLabel96 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        TpemeriksaanPenunjang = new widget.TextArea();
        jLabel97 = new widget.Label();
        chkTglGizi = new widget.CekBox();
        TtglGizi = new widget.Tanggal();
        chkMst = new widget.CekBox();
        Tskor = new widget.TextBox();
        label109 = new widget.Label();
        jLabel98 = new widget.Label();
        Trekomendasi = new widget.TextBox();
        jLabel99 = new widget.Label();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        Tkendala = new widget.TextBox();
        jLabel102 = new widget.Label();
        Tmerawat = new widget.TextBox();
        cmbKendala = new widget.ComboBox();
        jLabel103 = new widget.Label();
        cmbMerawat = new widget.ComboBox();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        chkTenang = new widget.CekBox();
        chkGelisah = new widget.CekBox();
        chkTakut = new widget.CekBox();
        chkTindakanMrh = new widget.CekBox();
        chkMudah = new widget.CekBox();
        TdiagnosaKeper = new widget.TextBox();
        jLabel106 = new widget.Label();
        chkKelebihan = new widget.CekBox();
        chkGangguanPerfusi = new widget.CekBox();
        chkGangguanKeseimbangan = new widget.CekBox();
        chkPenurunan = new widget.CekBox();
        chkNutrisi = new widget.CekBox();
        chkKetidakpatuhan = new widget.CekBox();
        chkGangguanTerhadap = new widget.CekBox();
        chkGangguanRasa = new widget.CekBox();
        jLabel65 = new widget.Label();
        chkMonitorBerat = new widget.CekBox();
        chkBerikan = new widget.CekBox();
        chkObservasi = new widget.CekBox();
        chkBilaPasien = new widget.CekBox();
        chkHentikan = new widget.CekBox();
        chkKaji = new widget.CekBox();
        chkPosisikan = new widget.CekBox();
        chkPenkes = new widget.CekBox();
        chkMonitorTandaInfeksi = new widget.CekBox();
        chkGanti = new widget.CekBox();
        chkMonitorTandaHipo = new widget.CekBox();
        TketAvShunt = new widget.TextBox();
        chkLakukan = new widget.CekBox();
        jLabel66 = new widget.Label();
        chkProgramHD = new widget.CekBox();
        chkTransfusi = new widget.CekBox();
        chkKolaborasi = new widget.CekBox();
        chkPemberianCa = new widget.CekBox();
        chkPemberianAntipiretik = new widget.CekBox();
        chkPemberianAnal = new widget.CekBox();
        chkPemberianEry = new widget.CekBox();
        chkPemberianPre = new widget.CekBox();
        chkObat = new widget.CekBox();
        chkPemberianAntibiotik = new widget.CekBox();
        chkLainyaInterval = new widget.CekBox();
        TketLainInterval = new widget.TextBox();
        jLabel107 = new widget.Label();
        chkInisiasi = new widget.CekBox();
        chkAkut = new widget.CekBox();
        chkRutin = new widget.CekBox();
        chkPreOp = new widget.CekBox();
        chkSled = new widget.CekBox();
        chkLainyaInstruksi = new widget.CekBox();
        TketLainInstruksi = new widget.TextBox();
        jLabel108 = new widget.Label();
        ThdInstruksi = new widget.TextBox();
        label110 = new widget.Label();
        Tqb = new widget.TextBox();
        label111 = new widget.Label();
        Tqd = new widget.TextBox();
        label112 = new widget.Label();
        TufGoal = new widget.TextBox();
        label113 = new widget.Label();
        Tprog = new widget.TextBox();
        jLabel109 = new widget.Label();
        Tuf = new widget.TextBox();
        jLabel110 = new widget.Label();
        Tbicarbonat = new widget.TextBox();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        TdialBicar = new widget.TextBox();
        jLabel113 = new widget.Label();
        TdialConduc = new widget.TextBox();
        jLabel114 = new widget.Label();
        TdialTemp = new widget.TextBox();
        jLabel115 = new widget.Label();
        TdosisSirkulasi = new widget.TextBox();
        label114 = new widget.Label();
        jLabel116 = new widget.Label();
        TdosisAwal = new widget.TextBox();
        label115 = new widget.Label();
        jLabel117 = new widget.Label();
        TdosisMain = new widget.TextBox();
        jLabel118 = new widget.Label();
        Tconti = new widget.TextBox();
        label116 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        Tintermiten = new widget.TextBox();
        Tlmwh = new widget.TextBox();
        TtanpaHeparin = new widget.TextBox();
        label117 = new widget.Label();
        cmbProgram = new widget.ComboBox();
        label118 = new widget.Label();
        TnmDokter1 = new widget.TextBox();
        label119 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        TcatatanLain = new widget.TextArea();
        jLabel123 = new widget.Label();
        chkMasalahAkses = new widget.CekBox();
        chkPerdarahan = new widget.CekBox();
        chkFirst = new widget.CekBox();
        chkSakitKpl = new widget.CekBox();
        chkMualMuntah = new widget.CekBox();
        chkKramOtot = new widget.CekBox();
        chkHiperkalemia = new widget.CekBox();
        chkHipotensi = new widget.CekBox();
        chkHipertensi = new widget.CekBox();
        chkNyeriDada = new widget.CekBox();
        chkAritmia = new widget.CekBox();
        chkGatalGatal = new widget.CekBox();
        chkDemam = new widget.CekBox();
        chkMenggigil = new widget.CekBox();
        chkLainPenyulit = new widget.CekBox();
        jLabel124 = new widget.Label();
        jLabel125 = new widget.Label();
        TevaluasiHd = new widget.TextBox();
        label120 = new widget.Label();
        TevaluasiQb = new widget.TextBox();
        label121 = new widget.Label();
        Tkt = new widget.TextBox();
        jLabel126 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        Tdp = new widget.TextArea();
        scrollPane7 = new widget.ScrollPane();
        Tobat = new widget.TextArea();
        jLabel127 = new widget.Label();
        scrollPane8 = new widget.ScrollPane();
        TcatatanMedis = new widget.TextArea();
        jLabel128 = new widget.Label();
        jLabel129 = new widget.Label();
        TnmDokter2 = new widget.TextBox();
        BtnDokter1 = new widget.Button();
        BtnDokter2 = new widget.Button();
        jLabel47 = new widget.Label();
        BtnPasteHasil = new widget.Button();
        jLabel130 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbAsesmen = new widget.Table();
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

        MnDokumenJangMed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenJangMed.setText("Dokumen Penunjang Medis");
        MnDokumenJangMed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokumenJangMed.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokumenJangMed.setIconTextGap(5);
        MnDokumenJangMed.setName("MnDokumenJangMed"); // NOI18N
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(195, 26));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Ulang Pasien Hemodialisis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Cetak Dalam Bentuk :");
        jLabel68.setName("jLabel68"); // NOI18N
        jLabel68.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel68);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

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

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2366));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 38, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 38, 410, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 38, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 38, 110, 23);

        chkSesakNfs.setBackground(new java.awt.Color(255, 255, 250));
        chkSesakNfs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSesakNfs.setForeground(new java.awt.Color(0, 0, 0));
        chkSesakNfs.setText("Sesak Nafas");
        chkSesakNfs.setBorderPainted(true);
        chkSesakNfs.setBorderPaintedFlat(true);
        chkSesakNfs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSesakNfs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSesakNfs.setName("chkSesakNfs"); // NOI18N
        chkSesakNfs.setOpaque(false);
        chkSesakNfs.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSesakNfs);
        chkSesakNfs.setBounds(115, 318, 90, 23);

        chkMual.setBackground(new java.awt.Color(255, 255, 250));
        chkMual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMual.setForeground(new java.awt.Color(0, 0, 0));
        chkMual.setText("Mual, Muntah");
        chkMual.setBorderPainted(true);
        chkMual.setBorderPaintedFlat(true);
        chkMual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMual.setName("chkMual"); // NOI18N
        chkMual.setOpaque(false);
        chkMual.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMual);
        chkMual.setBounds(215, 318, 100, 23);

        chkGatal.setBackground(new java.awt.Color(255, 255, 250));
        chkGatal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGatal.setForeground(new java.awt.Color(0, 0, 0));
        chkGatal.setText("Gatal");
        chkGatal.setBorderPainted(true);
        chkGatal.setBorderPaintedFlat(true);
        chkGatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGatal.setName("chkGatal"); // NOI18N
        chkGatal.setOpaque(false);
        chkGatal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGatal);
        chkGatal.setBounds(325, 318, 55, 23);

        chkLainKeluhan.setBackground(new java.awt.Color(255, 255, 250));
        chkLainKeluhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        chkLainKeluhan.setText("Lain - lain :");
        chkLainKeluhan.setBorderPainted(true);
        chkLainKeluhan.setBorderPaintedFlat(true);
        chkLainKeluhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainKeluhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainKeluhan.setName("chkLainKeluhan"); // NOI18N
        chkLainKeluhan.setOpaque(false);
        chkLainKeluhan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainKeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainKeluhanActionPerformed(evt);
            }
        });
        FormInput.add(chkLainKeluhan);
        chkLainKeluhan.setBounds(390, 318, 75, 23);

        Tcc.setForeground(new java.awt.Color(0, 0, 0));
        Tcc.setName("Tcc"); // NOI18N
        Tcc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TccKeyPressed(evt);
            }
        });
        FormInput.add(Tcc);
        Tcc.setBounds(660, 178, 70, 23);

        label38.setForeground(new java.awt.Color(0, 0, 0));
        label38.setText("Cc. :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label38);
        label38.setBounds(615, 178, 40, 23);

        ThemoKe.setForeground(new java.awt.Color(0, 0, 0));
        ThemoKe.setName("ThemoKe"); // NOI18N
        ThemoKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThemoKeKeyPressed(evt);
            }
        });
        FormInput.add(ThemoKe);
        ThemoKe.setBounds(425, 122, 60, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(115, 511, 70, 23);

        label104.setForeground(new java.awt.Color(0, 0, 0));
        label104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label104.setText("mmHg    Nadi : ");
        label104.setName("label104"); // NOI18N
        label104.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label104);
        label104.setBounds(190, 511, 75, 23);

        TfrekuensiNadi.setForeground(new java.awt.Color(0, 0, 0));
        TfrekuensiNadi.setName("TfrekuensiNadi"); // NOI18N
        TfrekuensiNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfrekuensiNadiKeyPressed(evt);
            }
        });
        FormInput.add(TfrekuensiNadi);
        TfrekuensiNadi.setBounds(495, 511, 70, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tanggal :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 94, 110, 23);

        Ttanggal.setEditable(false);
        Ttanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2026" }));
        Ttanggal.setDisplayFormat("dd-MM-yyyy");
        Ttanggal.setName("Ttanggal"); // NOI18N
        Ttanggal.setOpaque(false);
        Ttanggal.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(Ttanggal);
        Ttanggal.setBounds(115, 94, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jam :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(205, 94, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(250, 94, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(303, 94, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(356, 94, 45, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 66, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 66, 615, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("PENGKAJIAN KEPERAWATAN");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 262, 180, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tekanan Darah :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 511, 110, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("x/menit");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(570, 511, 50, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Diisi Maksimal 10 Menit Pre-HD");
        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 10, 210, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("No. Mesin :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 122, 110, 23);

        TnoMesin.setForeground(new java.awt.Color(0, 0, 0));
        TnoMesin.setName("TnoMesin"); // NOI18N
        TnoMesin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoMesinKeyPressed(evt);
            }
        });
        FormInput.add(TnoMesin);
        TnoMesin.setBounds(115, 122, 210, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Hemodialisa Ke :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(330, 122, 90, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Tipe Dialisiser N/R :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 150, 110, 23);

        TtipeDialisiser.setForeground(new java.awt.Color(0, 0, 0));
        TtipeDialisiser.setName("TtipeDialisiser"); // NOI18N
        TtipeDialisiser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtipeDialisiserKeyPressed(evt);
            }
        });
        FormInput.add(TtipeDialisiser);
        TtipeDialisiser.setBounds(115, 150, 615, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Dx. Medis :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 178, 110, 23);

        TdxMedis.setForeground(new java.awt.Color(0, 0, 0));
        TdxMedis.setName("TdxMedis"); // NOI18N
        TdxMedis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdxMedisKeyPressed(evt);
            }
        });
        FormInput.add(TdxMedis);
        TdxMedis.setBounds(115, 178, 500, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("<html><div style=\"text-align: right;\">Riwayat Alergi :<br>Obat&nbsp;&nbsp;</div></html>");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 206, 110, 30);

        cmbRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAlergiObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRiwAlergiObat.setName("cmbRiwAlergiObat"); // NOI18N
        cmbRiwAlergiObat.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRiwAlergiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwAlergiObatActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwAlergiObat);
        cmbRiwAlergiObat.setBounds(115, 206, 60, 23);

        TketRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        TketRiwAlergiObat.setName("TketRiwAlergiObat"); // NOI18N
        TketRiwAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRiwAlergiObatKeyPressed(evt);
            }
        });
        FormInput.add(TketRiwAlergiObat);
        TketRiwAlergiObat.setBounds(180, 206, 550, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Cara Bayar :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 234, 110, 23);

        TcaraByr.setEditable(false);
        TcaraByr.setForeground(new java.awt.Color(0, 0, 0));
        TcaraByr.setName("TcaraByr"); // NOI18N
        FormInput.add(TcaraByr);
        TcaraByr.setBounds(115, 234, 300, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Pekerjaan :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(415, 234, 70, 23);

        Tpekerjaan.setEditable(false);
        Tpekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        Tpekerjaan.setName("Tpekerjaan"); // NOI18N
        FormInput.add(Tpekerjaan);
        Tpekerjaan.setBounds(490, 234, 240, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("1. Keluhan Utama");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 290, 180, 23);

        TlainKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        TlainKeluhan.setName("TlainKeluhan"); // NOI18N
        TlainKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKeluhanKeyPressed(evt);
            }
        });
        FormInput.add(TlainKeluhan);
        TlainKeluhan.setBounds(470, 318, 260, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Nyeri (NRS) :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 346, 110, 23);

        cmbNyeriNrs.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeriNrs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbNyeriNrs.setName("cmbNyeriNrs"); // NOI18N
        cmbNyeriNrs.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbNyeriNrs);
        cmbNyeriNrs.setBounds(115, 346, 60, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/skala_nyeri_hd.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(230, 346, 430, 100);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Skala Nyeri :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 374, 110, 23);

        cmbSkalaNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkalaNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Nyeri (0)", "Ringan (1 - 3)", "Sedang (4 - 6)", "Berat (7 - 10)", "Akut (> 10)", "Kronik" }));
        cmbSkalaNyeri.setName("cmbSkalaNyeri"); // NOI18N
        cmbSkalaNyeri.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSkalaNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSkalaNyeriActionPerformed(evt);
            }
        });
        FormInput.add(cmbSkalaNyeri);
        cmbSkalaNyeri.setBounds(115, 374, 105, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Kronik :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 402, 110, 23);

        Tkronik.setForeground(new java.awt.Color(0, 0, 0));
        Tkronik.setName("Tkronik"); // NOI18N
        Tkronik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkronikKeyPressed(evt);
            }
        });
        FormInput.add(Tkronik);
        Tkronik.setBounds(115, 402, 105, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("2. Pemeriksaan Fisik");
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 455, 180, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Keadaan Umum :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 483, 110, 23);

        cmbKeadaanUmum.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeadaanUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Sedang", "Buruk", "Lain-lain" }));
        cmbKeadaanUmum.setName("cmbKeadaanUmum"); // NOI18N
        cmbKeadaanUmum.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbKeadaanUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeadaanUmumActionPerformed(evt);
            }
        });
        FormInput.add(cmbKeadaanUmum);
        cmbKeadaanUmum.setBounds(115, 483, 80, 23);

        TlainKeadaanUmum.setForeground(new java.awt.Color(0, 0, 0));
        TlainKeadaanUmum.setName("TlainKeadaanUmum"); // NOI18N
        TlainKeadaanUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKeadaanUmumKeyPressed(evt);
            }
        });
        FormInput.add(TlainKeadaanUmum);
        TlainKeadaanUmum.setBounds(200, 483, 200, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Kesadaran :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(400, 483, 80, 23);

        cmbKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Compos Mentis", "Apatis", "Delirium", "Somnolen", "Sopor", "Coma" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        cmbKesadaran.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKesadaran);
        cmbKesadaran.setBounds(486, 483, 105, 23);

        chkReguler.setBackground(new java.awt.Color(255, 255, 250));
        chkReguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReguler.setForeground(new java.awt.Color(0, 0, 0));
        chkReguler.setText("Reguler");
        chkReguler.setBorderPainted(true);
        chkReguler.setBorderPaintedFlat(true);
        chkReguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReguler.setName("chkReguler"); // NOI18N
        chkReguler.setOpaque(false);
        chkReguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReguler);
        chkReguler.setBounds(265, 511, 65, 23);

        chkIreguler.setBackground(new java.awt.Color(255, 255, 250));
        chkIreguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIreguler.setForeground(new java.awt.Color(0, 0, 0));
        chkIreguler.setText("Ireguler");
        chkIreguler.setBorderPainted(true);
        chkIreguler.setBorderPaintedFlat(true);
        chkIreguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIreguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIreguler.setName("chkIreguler"); // NOI18N
        chkIreguler.setOpaque(false);
        chkIreguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIreguler);
        chkIreguler.setBounds(340, 511, 75, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Frekuensi :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(420, 511, 70, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Respirasi :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 539, 110, 23);

        chkNormal.setBackground(new java.awt.Color(255, 255, 250));
        chkNormal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNormal.setForeground(new java.awt.Color(0, 0, 0));
        chkNormal.setText("Normal");
        chkNormal.setBorderPainted(true);
        chkNormal.setBorderPaintedFlat(true);
        chkNormal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNormal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNormal.setName("chkNormal"); // NOI18N
        chkNormal.setOpaque(false);
        chkNormal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNormal);
        chkNormal.setBounds(115, 539, 70, 23);

        chkKusmaul.setBackground(new java.awt.Color(255, 255, 250));
        chkKusmaul.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKusmaul.setForeground(new java.awt.Color(0, 0, 0));
        chkKusmaul.setText("Kusmaul");
        chkKusmaul.setBorderPainted(true);
        chkKusmaul.setBorderPaintedFlat(true);
        chkKusmaul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKusmaul.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKusmaul.setName("chkKusmaul"); // NOI18N
        chkKusmaul.setOpaque(false);
        chkKusmaul.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKusmaul);
        chkKusmaul.setBounds(196, 539, 70, 23);

        chkDispnea.setBackground(new java.awt.Color(255, 255, 250));
        chkDispnea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDispnea.setForeground(new java.awt.Color(0, 0, 0));
        chkDispnea.setText("Dispnea");
        chkDispnea.setBorderPainted(true);
        chkDispnea.setBorderPaintedFlat(true);
        chkDispnea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDispnea.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDispnea.setName("chkDispnea"); // NOI18N
        chkDispnea.setOpaque(false);
        chkDispnea.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDispnea);
        chkDispnea.setBounds(277, 539, 70, 23);

        chkEdemaParu.setBackground(new java.awt.Color(255, 255, 250));
        chkEdemaParu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEdemaParu.setForeground(new java.awt.Color(0, 0, 0));
        chkEdemaParu.setText("Edema Paru / Ronchi");
        chkEdemaParu.setBorderPainted(true);
        chkEdemaParu.setBorderPaintedFlat(true);
        chkEdemaParu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEdemaParu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEdemaParu.setName("chkEdemaParu"); // NOI18N
        chkEdemaParu.setOpaque(false);
        chkEdemaParu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEdemaParu);
        chkEdemaParu.setBounds(355, 539, 130, 23);

        chkLainRespirasi.setBackground(new java.awt.Color(255, 255, 250));
        chkLainRespirasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainRespirasi.setForeground(new java.awt.Color(0, 0, 0));
        chkLainRespirasi.setText("Lain - lain :");
        chkLainRespirasi.setBorderPainted(true);
        chkLainRespirasi.setBorderPaintedFlat(true);
        chkLainRespirasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainRespirasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainRespirasi.setName("chkLainRespirasi"); // NOI18N
        chkLainRespirasi.setOpaque(false);
        chkLainRespirasi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainRespirasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainRespirasiActionPerformed(evt);
            }
        });
        FormInput.add(chkLainRespirasi);
        chkLainRespirasi.setBounds(495, 539, 75, 23);

        TlainRespirasi.setForeground(new java.awt.Color(0, 0, 0));
        TlainRespirasi.setName("TlainRespirasi"); // NOI18N
        TlainRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainRespirasiKeyPressed(evt);
            }
        });
        FormInput.add(TlainRespirasi);
        TlainRespirasi.setBounds(570, 539, 160, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Frekuensi :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(730, 539, 70, 23);

        TfrekuensiRespirasi.setForeground(new java.awt.Color(0, 0, 0));
        TfrekuensiRespirasi.setName("TfrekuensiRespirasi"); // NOI18N
        TfrekuensiRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfrekuensiRespirasiKeyPressed(evt);
            }
        });
        FormInput.add(TfrekuensiRespirasi);
        TfrekuensiRespirasi.setBounds(805, 539, 70, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(880, 539, 50, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Konjungtiva :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 567, 110, 23);

        chkTdkAnemis.setBackground(new java.awt.Color(255, 255, 250));
        chkTdkAnemis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTdkAnemis.setForeground(new java.awt.Color(0, 0, 0));
        chkTdkAnemis.setText("Tidak Anemis");
        chkTdkAnemis.setBorderPainted(true);
        chkTdkAnemis.setBorderPaintedFlat(true);
        chkTdkAnemis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTdkAnemis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTdkAnemis.setName("chkTdkAnemis"); // NOI18N
        chkTdkAnemis.setOpaque(false);
        chkTdkAnemis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTdkAnemis);
        chkTdkAnemis.setBounds(115, 567, 90, 23);

        chkAnemis.setBackground(new java.awt.Color(255, 255, 250));
        chkAnemis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAnemis.setForeground(new java.awt.Color(0, 0, 0));
        chkAnemis.setText("Anemis");
        chkAnemis.setBorderPainted(true);
        chkAnemis.setBorderPaintedFlat(true);
        chkAnemis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnemis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnemis.setName("chkAnemis"); // NOI18N
        chkAnemis.setOpaque(false);
        chkAnemis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAnemis);
        chkAnemis.setBounds(215, 567, 65, 23);

        chkLainKonjung.setBackground(new java.awt.Color(255, 255, 250));
        chkLainKonjung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainKonjung.setForeground(new java.awt.Color(0, 0, 0));
        chkLainKonjung.setText("Lain - lain :");
        chkLainKonjung.setBorderPainted(true);
        chkLainKonjung.setBorderPaintedFlat(true);
        chkLainKonjung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainKonjung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainKonjung.setName("chkLainKonjung"); // NOI18N
        chkLainKonjung.setOpaque(false);
        chkLainKonjung.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainKonjung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainKonjungActionPerformed(evt);
            }
        });
        FormInput.add(chkLainKonjung);
        chkLainKonjung.setBounds(290, 567, 75, 23);

        TlainKonjung.setForeground(new java.awt.Color(0, 0, 0));
        TlainKonjung.setName("TlainKonjung"); // NOI18N
        TlainKonjung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKonjungKeyPressed(evt);
            }
        });
        FormInput.add(TlainKonjung);
        TlainKonjung.setBounds(370, 567, 160, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Ekstremitas :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 595, 110, 23);

        chkTdkEdema.setBackground(new java.awt.Color(255, 255, 250));
        chkTdkEdema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTdkEdema.setForeground(new java.awt.Color(0, 0, 0));
        chkTdkEdema.setText("Tidak Edema / Tidak Dehidrasi");
        chkTdkEdema.setBorderPainted(true);
        chkTdkEdema.setBorderPaintedFlat(true);
        chkTdkEdema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTdkEdema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTdkEdema.setName("chkTdkEdema"); // NOI18N
        chkTdkEdema.setOpaque(false);
        chkTdkEdema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTdkEdema);
        chkTdkEdema.setBounds(115, 595, 170, 23);

        chkDehidrasi.setBackground(new java.awt.Color(255, 255, 250));
        chkDehidrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDehidrasi.setForeground(new java.awt.Color(0, 0, 0));
        chkDehidrasi.setText("Dehidrasi");
        chkDehidrasi.setBorderPainted(true);
        chkDehidrasi.setBorderPaintedFlat(true);
        chkDehidrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDehidrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDehidrasi.setName("chkDehidrasi"); // NOI18N
        chkDehidrasi.setOpaque(false);
        chkDehidrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDehidrasi);
        chkDehidrasi.setBounds(295, 595, 75, 23);

        chkEdema.setBackground(new java.awt.Color(255, 255, 250));
        chkEdema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEdema.setForeground(new java.awt.Color(0, 0, 0));
        chkEdema.setText("Edema");
        chkEdema.setBorderPainted(true);
        chkEdema.setBorderPaintedFlat(true);
        chkEdema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEdema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEdema.setName("chkEdema"); // NOI18N
        chkEdema.setOpaque(false);
        chkEdema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEdema);
        chkEdema.setBounds(380, 595, 60, 23);

        chkEdemaAna.setBackground(new java.awt.Color(255, 255, 250));
        chkEdemaAna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEdemaAna.setForeground(new java.awt.Color(0, 0, 0));
        chkEdemaAna.setText("Edema Anasarka");
        chkEdemaAna.setBorderPainted(true);
        chkEdemaAna.setBorderPaintedFlat(true);
        chkEdemaAna.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEdemaAna.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEdemaAna.setName("chkEdemaAna"); // NOI18N
        chkEdemaAna.setOpaque(false);
        chkEdemaAna.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEdemaAna);
        chkEdemaAna.setBounds(450, 595, 110, 23);

        chkPucat.setBackground(new java.awt.Color(255, 255, 250));
        chkPucat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPucat.setForeground(new java.awt.Color(0, 0, 0));
        chkPucat.setText("Pucat & Dingin");
        chkPucat.setBorderPainted(true);
        chkPucat.setBorderPaintedFlat(true);
        chkPucat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPucat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPucat.setName("chkPucat"); // NOI18N
        chkPucat.setOpaque(false);
        chkPucat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPucat);
        chkPucat.setBounds(570, 595, 100, 23);

        chkAcites.setBackground(new java.awt.Color(255, 255, 250));
        chkAcites.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAcites.setForeground(new java.awt.Color(0, 0, 0));
        chkAcites.setText("Acites");
        chkAcites.setBorderPainted(true);
        chkAcites.setBorderPaintedFlat(true);
        chkAcites.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAcites.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAcites.setName("chkAcites"); // NOI18N
        chkAcites.setOpaque(false);
        chkAcites.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAcites);
        chkAcites.setBounds(680, 595, 60, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Berat Badan :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 623, 110, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Pre HD :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(115, 623, 50, 23);

        TpreHd.setForeground(new java.awt.Color(0, 0, 0));
        TpreHd.setName("TpreHd"); // NOI18N
        TpreHd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpreHdKeyPressed(evt);
            }
        });
        FormInput.add(TpreHd);
        TpreHd.setBounds(170, 623, 70, 23);

        label105.setForeground(new java.awt.Color(0, 0, 0));
        label105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label105.setText("Kg.    BB Post HD Yang Lalu :");
        label105.setName("label105"); // NOI18N
        label105.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label105);
        label105.setBounds(246, 623, 140, 23);

        TposHdlalu.setForeground(new java.awt.Color(0, 0, 0));
        TposHdlalu.setName("TposHdlalu"); // NOI18N
        TposHdlalu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TposHdlaluKeyPressed(evt);
            }
        });
        FormInput.add(TposHdlalu);
        TposHdlalu.setBounds(390, 623, 70, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("Kg.    BB Kering :");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(467, 623, 86, 23);

        TbbKering.setForeground(new java.awt.Color(0, 0, 0));
        TbbKering.setName("TbbKering"); // NOI18N
        TbbKering.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeringKeyPressed(evt);
            }
        });
        FormInput.add(TbbKering);
        TbbKering.setBounds(555, 623, 70, 23);

        label107.setForeground(new java.awt.Color(0, 0, 0));
        label107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label107.setText("Kg.    BB Post HD :");
        label107.setName("label107"); // NOI18N
        label107.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label107);
        label107.setBounds(630, 623, 95, 23);

        TbbPostHd.setForeground(new java.awt.Color(0, 0, 0));
        TbbPostHd.setName("TbbPostHd"); // NOI18N
        TbbPostHd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbPostHdKeyPressed(evt);
            }
        });
        FormInput.add(TbbPostHd);
        TbbPostHd.setBounds(726, 623, 70, 23);

        label108.setForeground(new java.awt.Color(0, 0, 0));
        label108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label108.setText("Kg.");
        label108.setName("label108"); // NOI18N
        label108.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label108);
        label108.setBounds(800, 623, 40, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Akses Veskular :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 651, 110, 23);

        chkAvFistula.setBackground(new java.awt.Color(255, 255, 250));
        chkAvFistula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAvFistula.setForeground(new java.awt.Color(0, 0, 0));
        chkAvFistula.setText("AV - Fistula");
        chkAvFistula.setBorderPainted(true);
        chkAvFistula.setBorderPaintedFlat(true);
        chkAvFistula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAvFistula.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAvFistula.setName("chkAvFistula"); // NOI18N
        chkAvFistula.setOpaque(false);
        chkAvFistula.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAvFistula);
        chkAvFistula.setBounds(115, 651, 85, 23);

        chkFemoralAkses.setBackground(new java.awt.Color(255, 255, 250));
        chkFemoralAkses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFemoralAkses.setForeground(new java.awt.Color(0, 0, 0));
        chkFemoralAkses.setText("Femoral");
        chkFemoralAkses.setBorderPainted(true);
        chkFemoralAkses.setBorderPaintedFlat(true);
        chkFemoralAkses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFemoralAkses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFemoralAkses.setName("chkFemoralAkses"); // NOI18N
        chkFemoralAkses.setOpaque(false);
        chkFemoralAkses.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFemoralAkses);
        chkFemoralAkses.setBounds(210, 651, 70, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("HD Kateter :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(290, 651, 80, 23);

        chkSubClavian.setBackground(new java.awt.Color(255, 255, 250));
        chkSubClavian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSubClavian.setForeground(new java.awt.Color(0, 0, 0));
        chkSubClavian.setText("Sub Clavian");
        chkSubClavian.setBorderPainted(true);
        chkSubClavian.setBorderPaintedFlat(true);
        chkSubClavian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSubClavian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSubClavian.setName("chkSubClavian"); // NOI18N
        chkSubClavian.setOpaque(false);
        chkSubClavian.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSubClavian);
        chkSubClavian.setBounds(378, 651, 90, 23);

        chkJugular.setBackground(new java.awt.Color(255, 255, 250));
        chkJugular.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJugular.setForeground(new java.awt.Color(0, 0, 0));
        chkJugular.setText("Jugular");
        chkJugular.setBorderPainted(true);
        chkJugular.setBorderPaintedFlat(true);
        chkJugular.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJugular.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJugular.setName("chkJugular"); // NOI18N
        chkJugular.setOpaque(false);
        chkJugular.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJugular);
        chkJugular.setBounds(475, 651, 65, 23);

        chkFemoralHD.setBackground(new java.awt.Color(255, 255, 250));
        chkFemoralHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFemoralHD.setForeground(new java.awt.Color(0, 0, 0));
        chkFemoralHD.setText("Femoral");
        chkFemoralHD.setBorderPainted(true);
        chkFemoralHD.setBorderPaintedFlat(true);
        chkFemoralHD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFemoralHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFemoralHD.setName("chkFemoralHD"); // NOI18N
        chkFemoralHD.setOpaque(false);
        chkFemoralHD.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFemoralHD);
        chkFemoralHD.setBounds(548, 651, 70, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Lokasi :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(630, 651, 50, 23);

        chkKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkKanan.setText("Kanan");
        chkKanan.setBorderPainted(true);
        chkKanan.setBorderPaintedFlat(true);
        chkKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKanan.setName("chkKanan"); // NOI18N
        chkKanan.setOpaque(false);
        chkKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKanan);
        chkKanan.setBounds(688, 651, 60, 23);

        chkKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkKiri.setText("Kiri");
        chkKiri.setBorderPainted(true);
        chkKiri.setBorderPaintedFlat(true);
        chkKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKiri.setName("chkKiri"); // NOI18N
        chkKiri.setOpaque(false);
        chkKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKiri);
        chkKiri.setBounds(760, 651, 48, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Resiko Jatuh Pre HD : Kesimpulan :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 679, 200, 23);

        cmbRJpreHD.setForeground(new java.awt.Color(0, 0, 0));
        cmbRJpreHD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0 - 24 (Tidak beresiko)", "24 - 45 (Resiko sedang)", "45 (Resiko Tinggi)" }));
        cmbRJpreHD.setName("cmbRJpreHD"); // NOI18N
        cmbRJpreHD.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbRJpreHD);
        cmbRJpreHD.setBounds(208, 679, 150, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Resiko Jatuh Post HD : Kesimpulan :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 707, 200, 23);

        cmbRJpostHD.setForeground(new java.awt.Color(0, 0, 0));
        cmbRJpostHD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0 - 24 (Tidak beresiko)", "24 - 45 (Resiko sedang)", "45 (Resiko Tinggi)" }));
        cmbRJpostHD.setName("cmbRJpostHD"); // NOI18N
        cmbRJpostHD.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbRJpostHD);
        cmbRJpostHD.setBounds(208, 707, 150, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("3. Pemeriksaan Penunjang (Lab. / Radiologi / Lain-lain)");
        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 735, 380, 23);

        scrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane4.setName("scrollPane4"); // NOI18N

        TpemeriksaanPenunjang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpemeriksaanPenunjang.setColumns(20);
        TpemeriksaanPenunjang.setRows(5);
        TpemeriksaanPenunjang.setComponentPopupMenu(jPopupMenu1);
        TpemeriksaanPenunjang.setName("TpemeriksaanPenunjang"); // NOI18N
        TpemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(162, 2000));
        TpemeriksaanPenunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpemeriksaanPenunjangKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TpemeriksaanPenunjang);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(115, 763, 630, 180);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("4. Gizi (Dikaji tiap 3 bulan sekali atau diulangi jika dianggap terjadi perburukan asupan gizi)");
        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 948, 580, 23);

        chkTglGizi.setBackground(new java.awt.Color(255, 255, 250));
        chkTglGizi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglGizi.setForeground(new java.awt.Color(0, 0, 0));
        chkTglGizi.setText("Tanggal :");
        chkTglGizi.setBorderPainted(true);
        chkTglGizi.setBorderPaintedFlat(true);
        chkTglGizi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTglGizi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglGizi.setName("chkTglGizi"); // NOI18N
        chkTglGizi.setOpaque(false);
        chkTglGizi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglGiziActionPerformed(evt);
            }
        });
        FormInput.add(chkTglGizi);
        chkTglGizi.setBounds(115, 976, 70, 23);

        TtglGizi.setEditable(false);
        TtglGizi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2026" }));
        TtglGizi.setDisplayFormat("dd-MM-yyyy");
        TtglGizi.setName("TtglGizi"); // NOI18N
        TtglGizi.setOpaque(false);
        TtglGizi.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglGizi);
        TtglGizi.setBounds(185, 976, 90, 23);

        chkMst.setBackground(new java.awt.Color(255, 255, 250));
        chkMst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMst.setForeground(new java.awt.Color(0, 0, 0));
        chkMst.setText("MST, Score Total :");
        chkMst.setBorderPainted(true);
        chkMst.setBorderPaintedFlat(true);
        chkMst.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMst.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMst.setName("chkMst"); // NOI18N
        chkMst.setOpaque(false);
        chkMst.setPreferredSize(new java.awt.Dimension(175, 23));
        chkMst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMstActionPerformed(evt);
            }
        });
        FormInput.add(chkMst);
        chkMst.setBounds(285, 976, 115, 23);

        Tskor.setForeground(new java.awt.Color(0, 0, 0));
        Tskor.setName("Tskor"); // NOI18N
        Tskor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorKeyPressed(evt);
            }
        });
        FormInput.add(Tskor);
        Tskor.setBounds(400, 976, 50, 23);

        label109.setForeground(new java.awt.Color(0, 0, 0));
        label109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label109.setText("(Bila skor >= 2, Pasien beresiko malnutrisi, konsul ke ahli gizi)");
        label109.setName("label109"); // NOI18N
        label109.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label109);
        label109.setBounds(458, 976, 310, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Rekomendasi :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 1004, 110, 23);

        Trekomendasi.setForeground(new java.awt.Color(0, 0, 0));
        Trekomendasi.setName("Trekomendasi"); // NOI18N
        Trekomendasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrekomendasiKeyPressed(evt);
            }
        });
        FormInput.add(Trekomendasi);
        Trekomendasi.setBounds(115, 1004, 615, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("5. Riwayat Psikososial");
        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 1032, 190, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("- Adakah tradisi / budaya yang berkaitan dengan pelayanan kesehatan yang akan diberikan");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(115, 1060, 510, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("- Kendala Komunikasi :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(115, 1088, 110, 23);

        Tkendala.setForeground(new java.awt.Color(0, 0, 0));
        Tkendala.setName("Tkendala"); // NOI18N
        Tkendala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkendalaKeyPressed(evt);
            }
        });
        FormInput.add(Tkendala);
        Tkendala.setBounds(380, 1088, 360, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("- Yang Merawat Dirumah :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(115, 1116, 130, 23);

        Tmerawat.setForeground(new java.awt.Color(0, 0, 0));
        Tmerawat.setName("Tmerawat"); // NOI18N
        Tmerawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmerawatKeyPressed(evt);
            }
        });
        FormInput.add(Tmerawat);
        Tmerawat.setBounds(400, 1116, 340, 23);

        cmbKendala.setForeground(new java.awt.Color(0, 0, 0));
        cmbKendala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbKendala.setName("cmbKendala"); // NOI18N
        cmbKendala.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbKendala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKendalaActionPerformed(evt);
            }
        });
        FormInput.add(cmbKendala);
        cmbKendala.setBounds(228, 1088, 80, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Jelaskan :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(310, 1088, 65, 23);

        cmbMerawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbMerawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbMerawat.setName("cmbMerawat"); // NOI18N
        cmbMerawat.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMerawatActionPerformed(evt);
            }
        });
        FormInput.add(cmbMerawat);
        cmbMerawat.setBounds(248, 1116, 80, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Jelaskan :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(330, 1116, 65, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("- Kondisi Saat Ini :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(115, 1144, 93, 23);

        chkTenang.setBackground(new java.awt.Color(255, 255, 250));
        chkTenang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTenang.setForeground(new java.awt.Color(0, 0, 0));
        chkTenang.setText("Tenang");
        chkTenang.setBorderPainted(true);
        chkTenang.setBorderPaintedFlat(true);
        chkTenang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTenang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTenang.setName("chkTenang"); // NOI18N
        chkTenang.setOpaque(false);
        chkTenang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTenang);
        chkTenang.setBounds(210, 1144, 65, 23);

        chkGelisah.setBackground(new java.awt.Color(255, 255, 250));
        chkGelisah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGelisah.setForeground(new java.awt.Color(0, 0, 0));
        chkGelisah.setText("Gelisah");
        chkGelisah.setBorderPainted(true);
        chkGelisah.setBorderPaintedFlat(true);
        chkGelisah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGelisah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGelisah.setName("chkGelisah"); // NOI18N
        chkGelisah.setOpaque(false);
        chkGelisah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGelisah);
        chkGelisah.setBounds(286, 1144, 65, 23);

        chkTakut.setBackground(new java.awt.Color(255, 255, 250));
        chkTakut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTakut.setForeground(new java.awt.Color(0, 0, 0));
        chkTakut.setText("Takut");
        chkTakut.setBorderPainted(true);
        chkTakut.setBorderPaintedFlat(true);
        chkTakut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTakut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTakut.setName("chkTakut"); // NOI18N
        chkTakut.setOpaque(false);
        chkTakut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTakut);
        chkTakut.setBounds(360, 1144, 60, 23);

        chkTindakanMrh.setBackground(new java.awt.Color(255, 255, 250));
        chkTindakanMrh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTindakanMrh.setForeground(new java.awt.Color(0, 0, 0));
        chkTindakanMrh.setText("Tindakan Marah");
        chkTindakanMrh.setBorderPainted(true);
        chkTindakanMrh.setBorderPaintedFlat(true);
        chkTindakanMrh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTindakanMrh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTindakanMrh.setName("chkTindakanMrh"); // NOI18N
        chkTindakanMrh.setOpaque(false);
        chkTindakanMrh.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTindakanMrh);
        chkTindakanMrh.setBounds(430, 1144, 105, 23);

        chkMudah.setBackground(new java.awt.Color(255, 255, 250));
        chkMudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMudah.setForeground(new java.awt.Color(0, 0, 0));
        chkMudah.setText("Mudah Tersinggung");
        chkMudah.setBorderPainted(true);
        chkMudah.setBorderPaintedFlat(true);
        chkMudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMudah.setName("chkMudah"); // NOI18N
        chkMudah.setOpaque(false);
        chkMudah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMudah);
        chkMudah.setBounds(547, 1144, 130, 23);

        TdiagnosaKeper.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaKeper.setName("TdiagnosaKeper"); // NOI18N
        TdiagnosaKeper.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeperKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaKeper);
        TdiagnosaKeper.setBounds(115, 1172, 615, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("<html><div style=\"text-align: right;\"><b>Diagnosa :<br>Keperawatan&nbsp;&nbsp;</b></div></html>");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 1172, 110, 30);

        chkKelebihan.setBackground(new java.awt.Color(255, 255, 250));
        chkKelebihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKelebihan.setForeground(new java.awt.Color(0, 0, 0));
        chkKelebihan.setText("1. Kelebihan Volume Cairan");
        chkKelebihan.setBorderPainted(true);
        chkKelebihan.setBorderPaintedFlat(true);
        chkKelebihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKelebihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKelebihan.setName("chkKelebihan"); // NOI18N
        chkKelebihan.setOpaque(false);
        chkKelebihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKelebihan);
        chkKelebihan.setBounds(115, 1200, 160, 23);

        chkGangguanPerfusi.setBackground(new java.awt.Color(255, 255, 250));
        chkGangguanPerfusi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGangguanPerfusi.setForeground(new java.awt.Color(0, 0, 0));
        chkGangguanPerfusi.setText("2. Gangguan Perfusi Jaringan");
        chkGangguanPerfusi.setBorderPainted(true);
        chkGangguanPerfusi.setBorderPaintedFlat(true);
        chkGangguanPerfusi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGangguanPerfusi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGangguanPerfusi.setName("chkGangguanPerfusi"); // NOI18N
        chkGangguanPerfusi.setOpaque(false);
        chkGangguanPerfusi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGangguanPerfusi);
        chkGangguanPerfusi.setBounds(115, 1228, 180, 23);

        chkGangguanKeseimbangan.setBackground(new java.awt.Color(255, 255, 250));
        chkGangguanKeseimbangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGangguanKeseimbangan.setForeground(new java.awt.Color(0, 0, 0));
        chkGangguanKeseimbangan.setText("3. Gangguan Keseimbangan Elektrolit");
        chkGangguanKeseimbangan.setBorderPainted(true);
        chkGangguanKeseimbangan.setBorderPaintedFlat(true);
        chkGangguanKeseimbangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGangguanKeseimbangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGangguanKeseimbangan.setName("chkGangguanKeseimbangan"); // NOI18N
        chkGangguanKeseimbangan.setOpaque(false);
        chkGangguanKeseimbangan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGangguanKeseimbangan);
        chkGangguanKeseimbangan.setBounds(115, 1256, 210, 23);

        chkPenurunan.setBackground(new java.awt.Color(255, 255, 250));
        chkPenurunan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenurunan.setForeground(new java.awt.Color(0, 0, 0));
        chkPenurunan.setText("4. Penurunan Curah Jantung");
        chkPenurunan.setBorderPainted(true);
        chkPenurunan.setBorderPaintedFlat(true);
        chkPenurunan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenurunan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenurunan.setName("chkPenurunan"); // NOI18N
        chkPenurunan.setOpaque(false);
        chkPenurunan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPenurunan);
        chkPenurunan.setBounds(115, 1284, 180, 23);

        chkNutrisi.setBackground(new java.awt.Color(255, 255, 250));
        chkNutrisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNutrisi.setForeground(new java.awt.Color(0, 0, 0));
        chkNutrisi.setText("5. Nutrisi Kurang Dari Kebutuhan Tubuh");
        chkNutrisi.setBorderPainted(true);
        chkNutrisi.setBorderPaintedFlat(true);
        chkNutrisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNutrisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNutrisi.setName("chkNutrisi"); // NOI18N
        chkNutrisi.setOpaque(false);
        chkNutrisi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNutrisi);
        chkNutrisi.setBounds(340, 1200, 230, 23);

        chkKetidakpatuhan.setBackground(new java.awt.Color(255, 255, 250));
        chkKetidakpatuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKetidakpatuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkKetidakpatuhan.setText("6. Ketidakpatuhan Terhadap Diet");
        chkKetidakpatuhan.setBorderPainted(true);
        chkKetidakpatuhan.setBorderPaintedFlat(true);
        chkKetidakpatuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKetidakpatuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKetidakpatuhan.setName("chkKetidakpatuhan"); // NOI18N
        chkKetidakpatuhan.setOpaque(false);
        chkKetidakpatuhan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKetidakpatuhan);
        chkKetidakpatuhan.setBounds(340, 1228, 200, 23);

        chkGangguanTerhadap.setBackground(new java.awt.Color(255, 255, 250));
        chkGangguanTerhadap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGangguanTerhadap.setForeground(new java.awt.Color(0, 0, 0));
        chkGangguanTerhadap.setText("7. Gangguan Terhadap Asam Basa");
        chkGangguanTerhadap.setBorderPainted(true);
        chkGangguanTerhadap.setBorderPaintedFlat(true);
        chkGangguanTerhadap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGangguanTerhadap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGangguanTerhadap.setName("chkGangguanTerhadap"); // NOI18N
        chkGangguanTerhadap.setOpaque(false);
        chkGangguanTerhadap.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGangguanTerhadap);
        chkGangguanTerhadap.setBounds(340, 1256, 210, 23);

        chkGangguanRasa.setBackground(new java.awt.Color(255, 255, 250));
        chkGangguanRasa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGangguanRasa.setForeground(new java.awt.Color(0, 0, 0));
        chkGangguanRasa.setText("8. Gangguan Rasa Nyaman : Nyeri, Gatal");
        chkGangguanRasa.setBorderPainted(true);
        chkGangguanRasa.setBorderPaintedFlat(true);
        chkGangguanRasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGangguanRasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGangguanRasa.setName("chkGangguanRasa"); // NOI18N
        chkGangguanRasa.setOpaque(false);
        chkGangguanRasa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGangguanRasa);
        chkGangguanRasa.setBounds(340, 1284, 230, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("INTERVENSI KEPERAWATAN (Rekapitulasi Pre-Intra dan Post-HD)");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 1312, 400, 23);

        chkMonitorBerat.setBackground(new java.awt.Color(255, 255, 250));
        chkMonitorBerat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMonitorBerat.setForeground(new java.awt.Color(0, 0, 0));
        chkMonitorBerat.setText("Monitor Berat Badan, Intake Output Atur Posisi Pasien Agar Ventilasi Adekuat");
        chkMonitorBerat.setBorderPainted(true);
        chkMonitorBerat.setBorderPaintedFlat(true);
        chkMonitorBerat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMonitorBerat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMonitorBerat.setName("chkMonitorBerat"); // NOI18N
        chkMonitorBerat.setOpaque(false);
        chkMonitorBerat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMonitorBerat);
        chkMonitorBerat.setBounds(115, 1340, 410, 23);

        chkBerikan.setBackground(new java.awt.Color(255, 255, 250));
        chkBerikan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBerikan.setForeground(new java.awt.Color(0, 0, 0));
        chkBerikan.setText("Berikan Pasien Oksigen Sesuai Kebutuhan");
        chkBerikan.setBorderPainted(true);
        chkBerikan.setBorderPaintedFlat(true);
        chkBerikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBerikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBerikan.setName("chkBerikan"); // NOI18N
        chkBerikan.setOpaque(false);
        chkBerikan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBerikan);
        chkBerikan.setBounds(115, 1368, 240, 23);

        chkObservasi.setBackground(new java.awt.Color(255, 255, 250));
        chkObservasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkObservasi.setForeground(new java.awt.Color(0, 0, 0));
        chkObservasi.setText("Observasi Pasien (Monitor Vital Sign) dan Mesin");
        chkObservasi.setBorderPainted(true);
        chkObservasi.setBorderPaintedFlat(true);
        chkObservasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObservasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkObservasi.setName("chkObservasi"); // NOI18N
        chkObservasi.setOpaque(false);
        chkObservasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkObservasi);
        chkObservasi.setBounds(115, 1396, 260, 23);

        chkBilaPasien.setBackground(new java.awt.Color(255, 255, 250));
        chkBilaPasien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBilaPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkBilaPasien.setText("Bila Pasien Mulai Hipotensi (Mual, Muntah, Keringat Dingin, Pusing), Kram Hipoglikemi Berikan Cairan Sesuai SPO");
        chkBilaPasien.setBorderPainted(true);
        chkBilaPasien.setBorderPaintedFlat(true);
        chkBilaPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBilaPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBilaPasien.setName("chkBilaPasien"); // NOI18N
        chkBilaPasien.setOpaque(false);
        chkBilaPasien.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBilaPasien);
        chkBilaPasien.setBounds(115, 1424, 570, 23);

        chkHentikan.setBackground(new java.awt.Color(255, 255, 250));
        chkHentikan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHentikan.setForeground(new java.awt.Color(0, 0, 0));
        chkHentikan.setText("Hentikan HD Sesuai Indikasi");
        chkHentikan.setBorderPainted(true);
        chkHentikan.setBorderPaintedFlat(true);
        chkHentikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHentikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHentikan.setName("chkHentikan"); // NOI18N
        chkHentikan.setOpaque(false);
        chkHentikan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHentikan);
        chkHentikan.setBounds(115, 1452, 170, 23);

        chkKaji.setBackground(new java.awt.Color(255, 255, 250));
        chkKaji.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKaji.setForeground(new java.awt.Color(0, 0, 0));
        chkKaji.setText("Kaji Kemampuan Pasien Mendapatkan Nutrisi Yang Dibutuhkan");
        chkKaji.setBorderPainted(true);
        chkKaji.setBorderPaintedFlat(true);
        chkKaji.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKaji.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKaji.setName("chkKaji"); // NOI18N
        chkKaji.setOpaque(false);
        chkKaji.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKaji);
        chkKaji.setBounds(115, 1480, 330, 23);

        chkPosisikan.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisikan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisikan.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisikan.setText("Posisikan Supinasi Dengan Elevasi Kepala 30 dan Elevasi Kaki");
        chkPosisikan.setBorderPainted(true);
        chkPosisikan.setBorderPaintedFlat(true);
        chkPosisikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisikan.setName("chkPosisikan"); // NOI18N
        chkPosisikan.setOpaque(false);
        chkPosisikan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisikan);
        chkPosisikan.setBounds(700, 1340, 320, 23);

        chkPenkes.setBackground(new java.awt.Color(255, 255, 250));
        chkPenkes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenkes.setForeground(new java.awt.Color(0, 0, 0));
        chkPenkes.setText("PENKES : Diet, AV-Shunt,");
        chkPenkes.setBorderPainted(true);
        chkPenkes.setBorderPaintedFlat(true);
        chkPenkes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenkes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenkes.setName("chkPenkes"); // NOI18N
        chkPenkes.setOpaque(false);
        chkPenkes.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPenkes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPenkesActionPerformed(evt);
            }
        });
        FormInput.add(chkPenkes);
        chkPenkes.setBounds(700, 1368, 150, 23);

        chkMonitorTandaInfeksi.setBackground(new java.awt.Color(255, 255, 250));
        chkMonitorTandaInfeksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMonitorTandaInfeksi.setForeground(new java.awt.Color(0, 0, 0));
        chkMonitorTandaInfeksi.setText("Monitor Tanda dan Gejala Infeksi (Local dan Sistemik)");
        chkMonitorTandaInfeksi.setBorderPainted(true);
        chkMonitorTandaInfeksi.setBorderPaintedFlat(true);
        chkMonitorTandaInfeksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMonitorTandaInfeksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMonitorTandaInfeksi.setName("chkMonitorTandaInfeksi"); // NOI18N
        chkMonitorTandaInfeksi.setOpaque(false);
        chkMonitorTandaInfeksi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMonitorTandaInfeksi);
        chkMonitorTandaInfeksi.setBounds(700, 1396, 290, 23);

        chkGanti.setBackground(new java.awt.Color(255, 255, 250));
        chkGanti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGanti.setForeground(new java.awt.Color(0, 0, 0));
        chkGanti.setText("Ganti Balutan Luka Sesuai Dengan Prosedur");
        chkGanti.setBorderPainted(true);
        chkGanti.setBorderPaintedFlat(true);
        chkGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGanti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGanti.setName("chkGanti"); // NOI18N
        chkGanti.setOpaque(false);
        chkGanti.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGanti);
        chkGanti.setBounds(700, 1424, 240, 23);

        chkMonitorTandaHipo.setBackground(new java.awt.Color(255, 255, 250));
        chkMonitorTandaHipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMonitorTandaHipo.setForeground(new java.awt.Color(0, 0, 0));
        chkMonitorTandaHipo.setText("Monitor Tanda dan Gejala Hipoglikemi");
        chkMonitorTandaHipo.setBorderPainted(true);
        chkMonitorTandaHipo.setBorderPaintedFlat(true);
        chkMonitorTandaHipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMonitorTandaHipo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMonitorTandaHipo.setName("chkMonitorTandaHipo"); // NOI18N
        chkMonitorTandaHipo.setOpaque(false);
        chkMonitorTandaHipo.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMonitorTandaHipo);
        chkMonitorTandaHipo.setBounds(700, 1452, 210, 23);

        TketAvShunt.setForeground(new java.awt.Color(0, 0, 0));
        TketAvShunt.setName("TketAvShunt"); // NOI18N
        TketAvShunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketAvShuntKeyPressed(evt);
            }
        });
        FormInput.add(TketAvShunt);
        TketAvShunt.setBounds(850, 1368, 270, 23);

        chkLakukan.setBackground(new java.awt.Color(255, 255, 250));
        chkLakukan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLakukan.setForeground(new java.awt.Color(0, 0, 0));
        chkLakukan.setText("Lakukan Teknik Distraksi, Relaksasi");
        chkLakukan.setBorderPainted(true);
        chkLakukan.setBorderPaintedFlat(true);
        chkLakukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLakukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLakukan.setName("chkLakukan"); // NOI18N
        chkLakukan.setOpaque(false);
        chkLakukan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLakukan);
        chkLakukan.setBounds(700, 1480, 210, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("INTERVAL KOLABORASI");
        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 1508, 170, 23);

        chkProgramHD.setBackground(new java.awt.Color(255, 255, 250));
        chkProgramHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkProgramHD.setForeground(new java.awt.Color(0, 0, 0));
        chkProgramHD.setText("Program HD");
        chkProgramHD.setBorderPainted(true);
        chkProgramHD.setBorderPaintedFlat(true);
        chkProgramHD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkProgramHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkProgramHD.setName("chkProgramHD"); // NOI18N
        chkProgramHD.setOpaque(false);
        chkProgramHD.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkProgramHD);
        chkProgramHD.setBounds(115, 1536, 90, 23);

        chkTransfusi.setBackground(new java.awt.Color(255, 255, 250));
        chkTransfusi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTransfusi.setForeground(new java.awt.Color(0, 0, 0));
        chkTransfusi.setText("Transfusi Darah");
        chkTransfusi.setBorderPainted(true);
        chkTransfusi.setBorderPaintedFlat(true);
        chkTransfusi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTransfusi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTransfusi.setName("chkTransfusi"); // NOI18N
        chkTransfusi.setOpaque(false);
        chkTransfusi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTransfusi);
        chkTransfusi.setBounds(213, 1536, 110, 23);

        chkKolaborasi.setBackground(new java.awt.Color(255, 255, 250));
        chkKolaborasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKolaborasi.setForeground(new java.awt.Color(0, 0, 0));
        chkKolaborasi.setText("Kolaborasi Diet");
        chkKolaborasi.setBorderPainted(true);
        chkKolaborasi.setBorderPaintedFlat(true);
        chkKolaborasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKolaborasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKolaborasi.setName("chkKolaborasi"); // NOI18N
        chkKolaborasi.setOpaque(false);
        chkKolaborasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKolaborasi);
        chkKolaborasi.setBounds(330, 1536, 100, 23);

        chkPemberianCa.setBackground(new java.awt.Color(255, 255, 250));
        chkPemberianCa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPemberianCa.setForeground(new java.awt.Color(0, 0, 0));
        chkPemberianCa.setText("Pemberian Ca Gluconas");
        chkPemberianCa.setBorderPainted(true);
        chkPemberianCa.setBorderPaintedFlat(true);
        chkPemberianCa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPemberianCa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPemberianCa.setName("chkPemberianCa"); // NOI18N
        chkPemberianCa.setOpaque(false);
        chkPemberianCa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPemberianCa);
        chkPemberianCa.setBounds(440, 1536, 145, 23);

        chkPemberianAntipiretik.setBackground(new java.awt.Color(255, 255, 250));
        chkPemberianAntipiretik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPemberianAntipiretik.setForeground(new java.awt.Color(0, 0, 0));
        chkPemberianAntipiretik.setText("Pemberian Antipiretik");
        chkPemberianAntipiretik.setBorderPainted(true);
        chkPemberianAntipiretik.setBorderPaintedFlat(true);
        chkPemberianAntipiretik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPemberianAntipiretik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPemberianAntipiretik.setName("chkPemberianAntipiretik"); // NOI18N
        chkPemberianAntipiretik.setOpaque(false);
        chkPemberianAntipiretik.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPemberianAntipiretik);
        chkPemberianAntipiretik.setBounds(595, 1536, 130, 23);

        chkPemberianAnal.setBackground(new java.awt.Color(255, 255, 250));
        chkPemberianAnal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPemberianAnal.setForeground(new java.awt.Color(0, 0, 0));
        chkPemberianAnal.setText("Pemberian Analgesik");
        chkPemberianAnal.setBorderPainted(true);
        chkPemberianAnal.setBorderPaintedFlat(true);
        chkPemberianAnal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPemberianAnal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPemberianAnal.setName("chkPemberianAnal"); // NOI18N
        chkPemberianAnal.setOpaque(false);
        chkPemberianAnal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPemberianAnal);
        chkPemberianAnal.setBounds(737, 1536, 130, 23);

        chkPemberianEry.setBackground(new java.awt.Color(255, 255, 250));
        chkPemberianEry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPemberianEry.setForeground(new java.awt.Color(0, 0, 0));
        chkPemberianEry.setText("Pemberian Erytropoetin");
        chkPemberianEry.setBorderPainted(true);
        chkPemberianEry.setBorderPaintedFlat(true);
        chkPemberianEry.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPemberianEry.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPemberianEry.setName("chkPemberianEry"); // NOI18N
        chkPemberianEry.setOpaque(false);
        chkPemberianEry.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPemberianEry);
        chkPemberianEry.setBounds(115, 1564, 150, 23);

        chkPemberianPre.setBackground(new java.awt.Color(255, 255, 250));
        chkPemberianPre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPemberianPre.setForeground(new java.awt.Color(0, 0, 0));
        chkPemberianPre.setText("Pemberian Preparat Besi");
        chkPemberianPre.setBorderPainted(true);
        chkPemberianPre.setBorderPaintedFlat(true);
        chkPemberianPre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPemberianPre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPemberianPre.setName("chkPemberianPre"); // NOI18N
        chkPemberianPre.setOpaque(false);
        chkPemberianPre.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPemberianPre);
        chkPemberianPre.setBounds(276, 1564, 150, 23);

        chkObat.setBackground(new java.awt.Color(255, 255, 250));
        chkObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkObat.setForeground(new java.awt.Color(0, 0, 0));
        chkObat.setText("Obat-obatan Emergensi");
        chkObat.setBorderPainted(true);
        chkObat.setBorderPaintedFlat(true);
        chkObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkObat.setName("chkObat"); // NOI18N
        chkObat.setOpaque(false);
        chkObat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkObat);
        chkObat.setBounds(438, 1564, 150, 23);

        chkPemberianAntibiotik.setBackground(new java.awt.Color(255, 255, 250));
        chkPemberianAntibiotik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPemberianAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        chkPemberianAntibiotik.setText("Pemberian Antibiotik");
        chkPemberianAntibiotik.setBorderPainted(true);
        chkPemberianAntibiotik.setBorderPaintedFlat(true);
        chkPemberianAntibiotik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPemberianAntibiotik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPemberianAntibiotik.setName("chkPemberianAntibiotik"); // NOI18N
        chkPemberianAntibiotik.setOpaque(false);
        chkPemberianAntibiotik.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPemberianAntibiotik);
        chkPemberianAntibiotik.setBounds(595, 1564, 130, 23);

        chkLainyaInterval.setBackground(new java.awt.Color(255, 255, 250));
        chkLainyaInterval.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainyaInterval.setForeground(new java.awt.Color(0, 0, 0));
        chkLainyaInterval.setText("Lainya :");
        chkLainyaInterval.setBorderPainted(true);
        chkLainyaInterval.setBorderPaintedFlat(true);
        chkLainyaInterval.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainyaInterval.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainyaInterval.setName("chkLainyaInterval"); // NOI18N
        chkLainyaInterval.setOpaque(false);
        chkLainyaInterval.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainyaInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainyaIntervalActionPerformed(evt);
            }
        });
        FormInput.add(chkLainyaInterval);
        chkLainyaInterval.setBounds(737, 1564, 65, 23);

        TketLainInterval.setForeground(new java.awt.Color(0, 0, 0));
        TketLainInterval.setName("TketLainInterval"); // NOI18N
        TketLainInterval.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainIntervalKeyPressed(evt);
            }
        });
        FormInput.add(TketLainInterval);
        TketLainInterval.setBounds(805, 1564, 200, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("INSTRUKSI MEDIK");
        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 1592, 140, 23);

        chkInisiasi.setBackground(new java.awt.Color(255, 255, 250));
        chkInisiasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInisiasi.setForeground(new java.awt.Color(0, 0, 0));
        chkInisiasi.setText("Inisiasi");
        chkInisiasi.setBorderPainted(true);
        chkInisiasi.setBorderPaintedFlat(true);
        chkInisiasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInisiasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInisiasi.setName("chkInisiasi"); // NOI18N
        chkInisiasi.setOpaque(false);
        chkInisiasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkInisiasi);
        chkInisiasi.setBounds(115, 1620, 60, 23);

        chkAkut.setBackground(new java.awt.Color(255, 255, 250));
        chkAkut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAkut.setForeground(new java.awt.Color(0, 0, 0));
        chkAkut.setText("Akut");
        chkAkut.setBorderPainted(true);
        chkAkut.setBorderPaintedFlat(true);
        chkAkut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAkut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAkut.setName("chkAkut"); // NOI18N
        chkAkut.setOpaque(false);
        chkAkut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAkut);
        chkAkut.setBounds(188, 1620, 54, 23);

        chkRutin.setBackground(new java.awt.Color(255, 255, 250));
        chkRutin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRutin.setForeground(new java.awt.Color(0, 0, 0));
        chkRutin.setText("Rutin");
        chkRutin.setBorderPainted(true);
        chkRutin.setBorderPaintedFlat(true);
        chkRutin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRutin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRutin.setName("chkRutin"); // NOI18N
        chkRutin.setOpaque(false);
        chkRutin.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRutin);
        chkRutin.setBounds(250, 1620, 55, 23);

        chkPreOp.setBackground(new java.awt.Color(255, 255, 250));
        chkPreOp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPreOp.setForeground(new java.awt.Color(0, 0, 0));
        chkPreOp.setText("Pre-Op");
        chkPreOp.setBorderPainted(true);
        chkPreOp.setBorderPaintedFlat(true);
        chkPreOp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPreOp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPreOp.setName("chkPreOp"); // NOI18N
        chkPreOp.setOpaque(false);
        chkPreOp.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPreOp);
        chkPreOp.setBounds(317, 1620, 65, 23);

        chkSled.setBackground(new java.awt.Color(255, 255, 250));
        chkSled.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSled.setForeground(new java.awt.Color(0, 0, 0));
        chkSled.setText("SLED");
        chkSled.setBorderPainted(true);
        chkSled.setBorderPaintedFlat(true);
        chkSled.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSled.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSled.setName("chkSled"); // NOI18N
        chkSled.setOpaque(false);
        chkSled.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSled);
        chkSled.setBounds(390, 1620, 55, 23);

        chkLainyaInstruksi.setBackground(new java.awt.Color(255, 255, 250));
        chkLainyaInstruksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainyaInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        chkLainyaInstruksi.setText("Lainya :");
        chkLainyaInstruksi.setBorderPainted(true);
        chkLainyaInstruksi.setBorderPaintedFlat(true);
        chkLainyaInstruksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainyaInstruksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainyaInstruksi.setName("chkLainyaInstruksi"); // NOI18N
        chkLainyaInstruksi.setOpaque(false);
        chkLainyaInstruksi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainyaInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainyaInstruksiActionPerformed(evt);
            }
        });
        FormInput.add(chkLainyaInstruksi);
        chkLainyaInstruksi.setBounds(455, 1620, 65, 23);

        TketLainInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        TketLainInstruksi.setName("TketLainInstruksi"); // NOI18N
        TketLainInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainInstruksiKeyPressed(evt);
            }
        });
        FormInput.add(TketLainInstruksi);
        TketLainInstruksi.setBounds(521, 1620, 270, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("HD :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 1648, 110, 23);

        ThdInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        ThdInstruksi.setName("ThdInstruksi"); // NOI18N
        ThdInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThdInstruksiKeyPressed(evt);
            }
        });
        FormInput.add(ThdInstruksi);
        ThdInstruksi.setBounds(115, 1648, 70, 23);

        label110.setForeground(new java.awt.Color(0, 0, 0));
        label110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label110.setText("Jam     QB :");
        label110.setName("label110"); // NOI18N
        label110.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label110);
        label110.setBounds(190, 1648, 60, 23);

        Tqb.setForeground(new java.awt.Color(0, 0, 0));
        Tqb.setName("Tqb"); // NOI18N
        Tqb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqbKeyPressed(evt);
            }
        });
        FormInput.add(Tqb);
        Tqb.setBounds(250, 1648, 70, 23);

        label111.setForeground(new java.awt.Color(0, 0, 0));
        label111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label111.setText("ml/mnt     QD :");
        label111.setName("label111"); // NOI18N
        label111.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label111);
        label111.setBounds(326, 1648, 75, 23);

        Tqd.setForeground(new java.awt.Color(0, 0, 0));
        Tqd.setName("Tqd"); // NOI18N
        Tqd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqdKeyPressed(evt);
            }
        });
        FormInput.add(Tqd);
        Tqd.setBounds(400, 1648, 70, 23);

        label112.setForeground(new java.awt.Color(0, 0, 0));
        label112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label112.setText("ml/mnt     UF Goal :");
        label112.setName("label112"); // NOI18N
        label112.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label112);
        label112.setBounds(477, 1648, 95, 23);

        TufGoal.setForeground(new java.awt.Color(0, 0, 0));
        TufGoal.setName("TufGoal"); // NOI18N
        TufGoal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TufGoalKeyPressed(evt);
            }
        });
        FormInput.add(TufGoal);
        TufGoal.setBounds(576, 1648, 70, 23);

        label113.setForeground(new java.awt.Color(0, 0, 0));
        label113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label113.setText("ml     Prog. Profilling : Na :");
        label113.setName("label113"); // NOI18N
        label113.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label113);
        label113.setBounds(653, 1648, 130, 23);

        Tprog.setForeground(new java.awt.Color(0, 0, 0));
        Tprog.setName("Tprog"); // NOI18N
        Tprog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprogKeyPressed(evt);
            }
        });
        FormInput.add(Tprog);
        Tprog.setBounds(784, 1648, 210, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("UF :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(0, 1676, 110, 23);

        Tuf.setForeground(new java.awt.Color(0, 0, 0));
        Tuf.setName("Tuf"); // NOI18N
        Tuf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TufKeyPressed(evt);
            }
        });
        FormInput.add(Tuf);
        Tuf.setBounds(115, 1676, 210, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Bicarbonat :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(325, 1676, 80, 23);

        Tbicarbonat.setForeground(new java.awt.Color(0, 0, 0));
        Tbicarbonat.setName("Tbicarbonat"); // NOI18N
        Tbicarbonat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbicarbonatKeyPressed(evt);
            }
        });
        FormInput.add(Tbicarbonat);
        Tbicarbonat.setBounds(410, 1676, 210, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Heparinisasi");
        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(0, 1760, 110, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Dialisat : Bicarbonat :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(620, 1676, 120, 23);

        TdialBicar.setForeground(new java.awt.Color(0, 0, 0));
        TdialBicar.setName("TdialBicar"); // NOI18N
        TdialBicar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdialBicarKeyPressed(evt);
            }
        });
        FormInput.add(TdialBicar);
        TdialBicar.setBounds(745, 1676, 210, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Conductivity :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(620, 1704, 120, 23);

        TdialConduc.setForeground(new java.awt.Color(0, 0, 0));
        TdialConduc.setName("TdialConduc"); // NOI18N
        TdialConduc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdialConducKeyPressed(evt);
            }
        });
        FormInput.add(TdialConduc);
        TdialConduc.setBounds(745, 1704, 210, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Temperatur :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(620, 1732, 120, 23);

        TdialTemp.setForeground(new java.awt.Color(0, 0, 0));
        TdialTemp.setName("TdialTemp"); // NOI18N
        TdialTemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdialTempKeyPressed(evt);
            }
        });
        FormInput.add(TdialTemp);
        TdialTemp.setBounds(745, 1732, 210, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Dosis Sirkulasi :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(0, 1788, 110, 23);

        TdosisSirkulasi.setForeground(new java.awt.Color(0, 0, 0));
        TdosisSirkulasi.setName("TdosisSirkulasi"); // NOI18N
        TdosisSirkulasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisSirkulasiKeyPressed(evt);
            }
        });
        FormInput.add(TdosisSirkulasi);
        TdosisSirkulasi.setBounds(115, 1788, 100, 23);

        label114.setForeground(new java.awt.Color(0, 0, 0));
        label114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label114.setText("IU");
        label114.setName("label114"); // NOI18N
        label114.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label114);
        label114.setBounds(220, 1788, 30, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Dosis Awal :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 1816, 110, 23);

        TdosisAwal.setForeground(new java.awt.Color(0, 0, 0));
        TdosisAwal.setName("TdosisAwal"); // NOI18N
        TdosisAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisAwalKeyPressed(evt);
            }
        });
        FormInput.add(TdosisAwal);
        TdosisAwal.setBounds(115, 1816, 100, 23);

        label115.setForeground(new java.awt.Color(0, 0, 0));
        label115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label115.setText("IU");
        label115.setName("label115"); // NOI18N
        label115.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label115);
        label115.setBounds(220, 1816, 30, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Dosis Maintenance :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 1844, 110, 23);

        TdosisMain.setForeground(new java.awt.Color(0, 0, 0));
        TdosisMain.setName("TdosisMain"); // NOI18N
        TdosisMain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisMainKeyPressed(evt);
            }
        });
        FormInput.add(TdosisMain);
        TdosisMain.setBounds(115, 1844, 100, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Continues :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 1872, 110, 23);

        Tconti.setForeground(new java.awt.Color(0, 0, 0));
        Tconti.setName("Tconti"); // NOI18N
        Tconti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcontiKeyPressed(evt);
            }
        });
        FormInput.add(Tconti);
        Tconti.setBounds(115, 1872, 100, 23);

        label116.setForeground(new java.awt.Color(0, 0, 0));
        label116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label116.setText("IU/Jam");
        label116.setName("label116"); // NOI18N
        label116.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label116);
        label116.setBounds(220, 1872, 50, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Intermiten :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(270, 1788, 110, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("LMWH :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(270, 1816, 110, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Tanpa Heparin, Penyebab :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(230, 1844, 150, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Program Bilas NaCl 0,9% 100 cc :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(270, 1872, 180, 23);

        Tintermiten.setForeground(new java.awt.Color(0, 0, 0));
        Tintermiten.setName("Tintermiten"); // NOI18N
        Tintermiten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TintermitenKeyPressed(evt);
            }
        });
        FormInput.add(Tintermiten);
        Tintermiten.setBounds(385, 1788, 100, 23);

        Tlmwh.setForeground(new java.awt.Color(0, 0, 0));
        Tlmwh.setName("Tlmwh"); // NOI18N
        Tlmwh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlmwhKeyPressed(evt);
            }
        });
        FormInput.add(Tlmwh);
        Tlmwh.setBounds(385, 1816, 250, 23);

        TtanpaHeparin.setForeground(new java.awt.Color(0, 0, 0));
        TtanpaHeparin.setName("TtanpaHeparin"); // NOI18N
        TtanpaHeparin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtanpaHeparinKeyPressed(evt);
            }
        });
        FormInput.add(TtanpaHeparin);
        TtanpaHeparin.setBounds(385, 1844, 250, 23);

        label117.setForeground(new java.awt.Color(0, 0, 0));
        label117.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label117.setText("IU/Jam");
        label117.setName("label117"); // NOI18N
        label117.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label117);
        label117.setBounds(490, 1788, 50, 23);

        cmbProgram.setForeground(new java.awt.Color(0, 0, 0));
        cmbProgram.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Per Jam", "Per 1/2 Jam" }));
        cmbProgram.setName("cmbProgram"); // NOI18N
        cmbProgram.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbProgram);
        cmbProgram.setBounds(455, 1872, 90, 23);

        label118.setForeground(new java.awt.Color(0, 0, 0));
        label118.setText("Nama Dokter :");
        label118.setName("label118"); // NOI18N
        label118.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label118);
        label118.setBounds(640, 1788, 90, 23);

        TnmDokter1.setEditable(false);
        TnmDokter1.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter1.setName("TnmDokter1"); // NOI18N
        FormInput.add(TnmDokter1);
        TnmDokter1.setBounds(735, 1788, 330, 23);

        label119.setForeground(new java.awt.Color(0, 0, 0));
        label119.setText("Catatan Lain :");
        label119.setName("label119"); // NOI18N
        label119.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label119);
        label119.setBounds(640, 1816, 90, 23);

        scrollPane5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane5.setName("scrollPane5"); // NOI18N

        TcatatanLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TcatatanLain.setColumns(20);
        TcatatanLain.setRows(5);
        TcatatanLain.setName("TcatatanLain"); // NOI18N
        TcatatanLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        TcatatanLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanLainKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TcatatanLain);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(735, 1816, 330, 80);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Penyulit Selama HD");
        jLabel123.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(0, 1900, 150, 23);

        chkMasalahAkses.setBackground(new java.awt.Color(255, 255, 250));
        chkMasalahAkses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMasalahAkses.setForeground(new java.awt.Color(0, 0, 0));
        chkMasalahAkses.setText("Masalah Akses");
        chkMasalahAkses.setBorderPainted(true);
        chkMasalahAkses.setBorderPaintedFlat(true);
        chkMasalahAkses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMasalahAkses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMasalahAkses.setName("chkMasalahAkses"); // NOI18N
        chkMasalahAkses.setOpaque(false);
        chkMasalahAkses.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMasalahAkses);
        chkMasalahAkses.setBounds(115, 1928, 100, 23);

        chkPerdarahan.setBackground(new java.awt.Color(255, 255, 250));
        chkPerdarahan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerdarahan.setForeground(new java.awt.Color(0, 0, 0));
        chkPerdarahan.setText("Perdarahan");
        chkPerdarahan.setBorderPainted(true);
        chkPerdarahan.setBorderPaintedFlat(true);
        chkPerdarahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerdarahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerdarahan.setName("chkPerdarahan"); // NOI18N
        chkPerdarahan.setOpaque(false);
        chkPerdarahan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerdarahan);
        chkPerdarahan.setBounds(225, 1928, 85, 23);

        chkFirst.setBackground(new java.awt.Color(255, 255, 250));
        chkFirst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFirst.setForeground(new java.awt.Color(0, 0, 0));
        chkFirst.setText("First Use Syndrome");
        chkFirst.setBorderPainted(true);
        chkFirst.setBorderPaintedFlat(true);
        chkFirst.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFirst.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFirst.setName("chkFirst"); // NOI18N
        chkFirst.setOpaque(false);
        chkFirst.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFirst);
        chkFirst.setBounds(320, 1928, 126, 23);

        chkSakitKpl.setBackground(new java.awt.Color(255, 255, 250));
        chkSakitKpl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSakitKpl.setForeground(new java.awt.Color(0, 0, 0));
        chkSakitKpl.setText("Sakit Kepala");
        chkSakitKpl.setBorderPainted(true);
        chkSakitKpl.setBorderPaintedFlat(true);
        chkSakitKpl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSakitKpl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSakitKpl.setName("chkSakitKpl"); // NOI18N
        chkSakitKpl.setOpaque(false);
        chkSakitKpl.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSakitKpl);
        chkSakitKpl.setBounds(455, 1928, 90, 23);

        chkMualMuntah.setBackground(new java.awt.Color(255, 255, 250));
        chkMualMuntah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMualMuntah.setForeground(new java.awt.Color(0, 0, 0));
        chkMualMuntah.setText("Mual & Muntah");
        chkMualMuntah.setBorderPainted(true);
        chkMualMuntah.setBorderPaintedFlat(true);
        chkMualMuntah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMualMuntah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMualMuntah.setName("chkMualMuntah"); // NOI18N
        chkMualMuntah.setOpaque(false);
        chkMualMuntah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMualMuntah);
        chkMualMuntah.setBounds(555, 1928, 105, 23);

        chkKramOtot.setBackground(new java.awt.Color(255, 255, 250));
        chkKramOtot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKramOtot.setForeground(new java.awt.Color(0, 0, 0));
        chkKramOtot.setText("Kram Otot");
        chkKramOtot.setBorderPainted(true);
        chkKramOtot.setBorderPaintedFlat(true);
        chkKramOtot.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKramOtot.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKramOtot.setName("chkKramOtot"); // NOI18N
        chkKramOtot.setOpaque(false);
        chkKramOtot.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKramOtot);
        chkKramOtot.setBounds(670, 1928, 80, 23);

        chkHiperkalemia.setBackground(new java.awt.Color(255, 255, 250));
        chkHiperkalemia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHiperkalemia.setForeground(new java.awt.Color(0, 0, 0));
        chkHiperkalemia.setText("Hiperkalemia");
        chkHiperkalemia.setBorderPainted(true);
        chkHiperkalemia.setBorderPaintedFlat(true);
        chkHiperkalemia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHiperkalemia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHiperkalemia.setName("chkHiperkalemia"); // NOI18N
        chkHiperkalemia.setOpaque(false);
        chkHiperkalemia.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHiperkalemia);
        chkHiperkalemia.setBounds(760, 1928, 90, 23);

        chkHipotensi.setBackground(new java.awt.Color(255, 255, 250));
        chkHipotensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipotensi.setForeground(new java.awt.Color(0, 0, 0));
        chkHipotensi.setText("Hipotensi");
        chkHipotensi.setBorderPainted(true);
        chkHipotensi.setBorderPaintedFlat(true);
        chkHipotensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipotensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipotensi.setName("chkHipotensi"); // NOI18N
        chkHipotensi.setOpaque(false);
        chkHipotensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipotensi);
        chkHipotensi.setBounds(860, 1928, 75, 23);

        chkHipertensi.setBackground(new java.awt.Color(255, 255, 250));
        chkHipertensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipertensi.setForeground(new java.awt.Color(0, 0, 0));
        chkHipertensi.setText("Hipertensi");
        chkHipertensi.setBorderPainted(true);
        chkHipertensi.setBorderPaintedFlat(true);
        chkHipertensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipertensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipertensi.setName("chkHipertensi"); // NOI18N
        chkHipertensi.setOpaque(false);
        chkHipertensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipertensi);
        chkHipertensi.setBounds(946, 1928, 85, 23);

        chkNyeriDada.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeriDada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeriDada.setForeground(new java.awt.Color(0, 0, 0));
        chkNyeriDada.setText("Nyeri Dada");
        chkNyeriDada.setBorderPainted(true);
        chkNyeriDada.setBorderPaintedFlat(true);
        chkNyeriDada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeriDada.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeriDada.setName("chkNyeriDada"); // NOI18N
        chkNyeriDada.setOpaque(false);
        chkNyeriDada.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNyeriDada);
        chkNyeriDada.setBounds(115, 1956, 85, 23);

        chkAritmia.setBackground(new java.awt.Color(255, 255, 250));
        chkAritmia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAritmia.setForeground(new java.awt.Color(0, 0, 0));
        chkAritmia.setText("Aritmia");
        chkAritmia.setBorderPainted(true);
        chkAritmia.setBorderPaintedFlat(true);
        chkAritmia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAritmia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAritmia.setName("chkAritmia"); // NOI18N
        chkAritmia.setOpaque(false);
        chkAritmia.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAritmia);
        chkAritmia.setBounds(210, 1956, 65, 23);

        chkGatalGatal.setBackground(new java.awt.Color(255, 255, 250));
        chkGatalGatal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGatalGatal.setForeground(new java.awt.Color(0, 0, 0));
        chkGatalGatal.setText("Gatal - Gatal");
        chkGatalGatal.setBorderPainted(true);
        chkGatalGatal.setBorderPaintedFlat(true);
        chkGatalGatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGatalGatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGatalGatal.setName("chkGatalGatal"); // NOI18N
        chkGatalGatal.setOpaque(false);
        chkGatalGatal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGatalGatal);
        chkGatalGatal.setBounds(284, 1956, 90, 23);

        chkDemam.setBackground(new java.awt.Color(255, 255, 250));
        chkDemam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDemam.setForeground(new java.awt.Color(0, 0, 0));
        chkDemam.setText("Demam");
        chkDemam.setBorderPainted(true);
        chkDemam.setBorderPaintedFlat(true);
        chkDemam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDemam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDemam.setName("chkDemam"); // NOI18N
        chkDemam.setOpaque(false);
        chkDemam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDemam);
        chkDemam.setBounds(384, 1956, 65, 23);

        chkMenggigil.setBackground(new java.awt.Color(255, 255, 250));
        chkMenggigil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMenggigil.setForeground(new java.awt.Color(0, 0, 0));
        chkMenggigil.setText("Menggigil / Dingin");
        chkMenggigil.setBorderPainted(true);
        chkMenggigil.setBorderPaintedFlat(true);
        chkMenggigil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMenggigil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMenggigil.setName("chkMenggigil"); // NOI18N
        chkMenggigil.setOpaque(false);
        chkMenggigil.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMenggigil);
        chkMenggigil.setBounds(460, 1956, 117, 23);

        chkLainPenyulit.setBackground(new java.awt.Color(255, 255, 250));
        chkLainPenyulit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainPenyulit.setForeground(new java.awt.Color(0, 0, 0));
        chkLainPenyulit.setText("Lain - Lain");
        chkLainPenyulit.setBorderPainted(true);
        chkLainPenyulit.setBorderPaintedFlat(true);
        chkLainPenyulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainPenyulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainPenyulit.setName("chkLainPenyulit"); // NOI18N
        chkLainPenyulit.setOpaque(false);
        chkLainPenyulit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLainPenyulit);
        chkLainPenyulit.setBounds(585, 1956, 80, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("EVALUASI KEPERAWATAN");
        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(0, 1984, 180, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("HD :");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 2012, 110, 23);

        TevaluasiHd.setForeground(new java.awt.Color(0, 0, 0));
        TevaluasiHd.setName("TevaluasiHd"); // NOI18N
        TevaluasiHd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TevaluasiHdKeyPressed(evt);
            }
        });
        FormInput.add(TevaluasiHd);
        TevaluasiHd.setBounds(115, 2012, 70, 23);

        label120.setForeground(new java.awt.Color(0, 0, 0));
        label120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label120.setText("Jam     QB :");
        label120.setName("label120"); // NOI18N
        label120.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label120);
        label120.setBounds(190, 2012, 60, 23);

        TevaluasiQb.setForeground(new java.awt.Color(0, 0, 0));
        TevaluasiQb.setName("TevaluasiQb"); // NOI18N
        TevaluasiQb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TevaluasiQbKeyPressed(evt);
            }
        });
        FormInput.add(TevaluasiQb);
        TevaluasiQb.setBounds(250, 2012, 70, 23);

        label121.setForeground(new java.awt.Color(0, 0, 0));
        label121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label121.setText("ml/mnt     Kt/V :");
        label121.setName("label121"); // NOI18N
        label121.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label121);
        label121.setBounds(326, 2012, 80, 23);

        Tkt.setForeground(new java.awt.Color(0, 0, 0));
        Tkt.setName("Tkt"); // NOI18N
        Tkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TktKeyPressed(evt);
            }
        });
        FormInput.add(Tkt);
        Tkt.setBounds(406, 2012, 260, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("DISCHART PLANNING :");
        jLabel126.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(0, 2040, 180, 23);

        scrollPane6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Tdp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdp.setColumns(20);
        Tdp.setRows(5);
        Tdp.setName("Tdp"); // NOI18N
        Tdp.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tdp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdpKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Tdp);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(187, 2040, 660, 80);

        scrollPane7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane7.setName("scrollPane7"); // NOI18N

        Tobat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tobat.setColumns(20);
        Tobat.setRows(5);
        Tobat.setName("Tobat"); // NOI18N
        Tobat.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tobat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(Tobat);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(187, 2153, 660, 80);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Obat :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(0, 2153, 180, 23);

        scrollPane8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane8.setName("scrollPane8"); // NOI18N

        TcatatanMedis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TcatatanMedis.setColumns(20);
        TcatatanMedis.setRows(5);
        TcatatanMedis.setName("TcatatanMedis"); // NOI18N
        TcatatanMedis.setPreferredSize(new java.awt.Dimension(162, 2000));
        TcatatanMedis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanMedisKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(TcatatanMedis);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(187, 2238, 660, 80);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Catatan Medis :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(0, 2238, 180, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Nama Dokter :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(0, 2323, 180, 23);

        TnmDokter2.setEditable(false);
        TnmDokter2.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter2.setName("TnmDokter2"); // NOI18N
        FormInput.add(TnmDokter2);
        TnmDokter2.setBounds(187, 2323, 370, 23);

        BtnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter1.setMnemonic('2');
        BtnDokter1.setToolTipText("Alt+2");
        BtnDokter1.setName("BtnDokter1"); // NOI18N
        BtnDokter1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter1);
        BtnDokter1.setBounds(1070, 1788, 28, 23);

        BtnDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter2.setMnemonic('2');
        BtnDokter2.setToolTipText("Alt+2");
        BtnDokter2.setName("BtnDokter2"); // NOI18N
        BtnDokter2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter2ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter2);
        BtnDokter2.setBounds(563, 2323, 28, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("(Klik kanan pada halaman ini untuk melihat hasil pemeriksaan penunjang)");
        jLabel47.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(390, 735, 380, 23);

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
        BtnPasteHasil.setBounds(755, 763, 100, 30);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("EVALUASI MEDIK");
        jLabel130.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(0, 2125, 180, 23);

        scrollInput.setViewportView(FormInput);

        TabRawat.addTab("Input Assesmen", scrollInput);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAsesmen.setName("tbAsesmen"); // NOI18N
        tbAsesmen.getTableHeader().setReorderingAllowed(false);
        tbAsesmen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsesmenMouseClicked(evt);
            }
        });
        tbAsesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAsesmenKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAsesmen);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('T');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
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
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Assesmen", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("asesmen_ulang_hemodialisis", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 155, new String[]{
                        TNoRw.getText(), sttsrwt, TrgRawat.getText(), Valid.SetTgl(Ttanggal.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TnoMesin.getText(), ThemoKe.getText(), TtipeDialisiser.getText(), TdxMedis.getText(), Tcc.getText(), cmbRiwAlergiObat.getSelectedItem().toString(),
                        TketRiwAlergiObat.getText(), cekSesak, cekMual, cekGatal, cekLainKeluhan, TlainKeluhan.getText(), cmbNyeriNrs.getSelectedItem().toString(),
                        cmbSkalaNyeri.getSelectedItem().toString(), Tkronik.getText(), cmbKeadaanUmum.getSelectedItem().toString(), TlainKeadaanUmum.getText(),
                        cmbKesadaran.getSelectedItem().toString(), Ttd.getText(), cekReguler, cekIreguler, TfrekuensiNadi.getText(), cekNormal, cekKusmaul, cekDispnea,
                        cekEdemaParu, cekLainRespi, TlainRespirasi.getText(), TfrekuensiRespirasi.getText(), cekTdkAnemis, cekAnemis, cekLainKonjung, TlainKonjung.getText(),
                        cekTdkEdema, cekDehidrasi, cekEdema, cekEdemaAnas, cekPucat, cekAcites, TpreHd.getText(), TposHdlalu.getText(), TbbKering.getText(), TbbPostHd.getText(),
                        cekAvFistula, cekFemoral, cekSub, cekJugular, cekFemoralHD, cekKanan, cekKiri, cmbRJpreHD.getSelectedItem().toString(), cmbRJpostHD.getSelectedItem().toString(),
                        TpemeriksaanPenunjang.getText(), cekTglGizi, Valid.SetTgl(TtglGizi.getSelectedItem() + ""), cekMst, Tskor.getText(), Trekomendasi.getText(),
                        cmbKendala.getSelectedItem().toString(), Tkendala.getText(), cmbMerawat.getSelectedItem().toString(), Tmerawat.getText(), cekTenang, cekGelisah, cekTakut,
                        cekTindakanMrh, cekMudah, TdiagnosaKeper.getText(), cekKelebihan, cekGangPerfusi, cekGangKeseimbangan, cekPenurunan, cekNutrisi, cekKetidakpatuhan,
                        cekGangTerhadap, cekGangRasa, cekMonBerat, cekBerikan, cekObservasi, cekBila, cekHentikan, cekKaji, cekPosisikan, cekPenkes, TketAvShunt.getText(),
                        cekMonTandaInfeksi, cekGanti, cekMonTandaHipo, cekLakukan, cekProgram, cekPemberianEry, cekKolab, cekPemberianPre, cekPemberianCa, cekObat, cekPemberianAntipiretik,
                        cekPemberianAntibiotik, cekPemberianAnal, cekLainInterval, TketLainInterval.getText(), cekInisiasi, cekAkut, cekRutin, cekPreOp, cekSled, cekLainInstruksi,
                        TketLainInstruksi.getText(), ThdInstruksi.getText(), Tqb.getText(), Tqd.getText(), TufGoal.getText(), Tprog.getText(), Tuf.getText(), Tbicarbonat.getText(),
                        TdialBicar.getText(), TdialConduc.getText(), TdialTemp.getText(), TdosisSirkulasi.getText(), TdosisAwal.getText(), TdosisMain.getText(), Tconti.getText(),
                        Tintermiten.getText(), Tlmwh.getText(), TtanpaHeparin.getText(), cmbProgram.getSelectedItem().toString(), TcatatanLain.getText(), nipDokter1, cekMasalah,
                        cekPerdarahan, cekFirst, cekSakit, cekMualMuntah, cekKram, cekHiperkalemia, cekHipo, cekHipertensi, cekNyeri, cekAritmia, cekGatalGatal, cekDemam,
                        cekMenggigil, cekLainPenyulit, TevaluasiHd.getText(), TevaluasiQb.getText(), Tkt.getText(), Tdp.getText(), Tobat.getText(), TcatatanMedis.getText(),
                        nipDokter2, Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Ulang Pasien Hemodialisis", "Simpan");
                TCari.setText(TNoRw.getText());
                TabRawat.setSelectedIndex(1);
                emptTeks();
                tampil();
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
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from asesmen_ulang_hemodialisis where no_rawat=?", 1, new String[]{
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            cekData();
            if (Sequel.mengedittf("asesmen_ulang_hemodialisis", "no_rawat=?", "tgl_asesmen=?, jam_asesmen=?, no_mesin=?, hemodialisis_ke=?, tipe_dialisiser=?, diagnosa_medis=?, "
                    + "cc=?, riwayat_alergi=?, ket_riwayat_alergi=?, sesak_nafas=?, mual_muntah=?, gatal=?, lain_lain=?, ket_lain_lain=?, nyeri_nrs=?, skala_nyeri=?, ket_kronik=?, "
                    + "keadaan_umum=?, ket_keadaan_umum=?, kesadaran=?, td=?, reguler=?, ireguler=?, nadi_frekuensi=?, normal=?, kusmaul=?, dispnea=?, edema_respirasi=?, "
                    + "lain_respirasi=?, ket_lain_respirasi=?, respirasi_frekuensi=?, tidak_anemis=?, anemis=?, lain_konjungtiva=?, ket_lain_konjungtiva=?, tidak_edema=?, dehidrasi=?, "
                    + "edema_ekstremitas=?, edema_anasarka=?, pucat=?, acites=?, pre_hd=?, post_hd_yang_lalu=?, bb_kering=?, bb_post_hd=?, av_fistula=?, femoral_akses=?, sub_clavian=?, "
                    + "jugular=?, femoral_hd=?, kanan=?, kiri=?, kes_jatuh_pre_hd=?, kes_jatuh_post_hd=?, pemeriksaan_penunjang=?, cek_tgl_gizi=?, tgl_gizi=?, mst=?, skor_total=?, "
                    + "rekomendasi=?, kendala_komunikasi=?, ket_kendala=?, yang_merawat=?, ket_yang_merawat=?, tenang=?, gelisah=?, takut=?, marah=?, mudah=?, diagnosis_keperawatan=?, "
                    + "kelebihan_volume=?, gangguan_perfusi=?, gangguan_keseimbangan=?, penurunan=?, nutrisi=?, ketidakpatuhan=?, gangguan_terhadap=?, gangguan_rasa=?, monitor_berat=?, "
                    + "berikan=?, observasi=?, bila_pasien=?, hentikan=?, kaji=?, posisikan=?, penkes=?, ket_av_shunt=?, monitor_tanda_infeksi=?, ganti_balutan=?, "
                    + "monitor_tanda_hipoglikemi=?, lakukan=?, program_hd=?, pemberian_ery=?, kolaborasi=?, pemberian_pre=?, pemberian_ca=?, obat_emergency=?, pemberian_antipiretik=?, "
                    + "pemberian_antibiotik=?, pemberian_analgesik=?, lainnya_interval=?, ket_lainnya_interval=?, inisiasi=?, akut=?, rutin=?, pre_op=?, sled=?, lainya_instruksi=?, "
                    + "ket_lainnya_instruksi=?, hd_jam=?, qb=?, qd=?, uf_goal=?, na=?, uf=?, bicarbonat=?, dialisat_bicarbonat=?, dialisat_conductivity=?, dialisat_temperatur=?, "
                    + "sirkulasi=?, awal=?, maintenance=?, continues=?, intermiten=?, lmwh=?, tanpa_heparin=?, program_bilas=?, catatan_lain=?, nip_dokter1=?, masalah_akses=?, "
                    + "perdarahan=?, first=?, sakit_kepala=?, penyulit_mual=?, kram=?, hiperkalemia=?, hipotensi=?, hipertensi=?, nyeri_dada=?, aritmia=?, gatal_gatal=?, demam=?, "
                    + "menggigil=?, lain_penyulit=?, evaluasi_hd=?, evaluasi_qb=?, evaluasi_kt=?, discharge_planing=?, obat=?, catatan_medis=?, nip_dokter2=?", 152, new String[]{
                        Valid.SetTgl(Ttanggal.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TnoMesin.getText(), ThemoKe.getText(), TtipeDialisiser.getText(), TdxMedis.getText(), Tcc.getText(), cmbRiwAlergiObat.getSelectedItem().toString(),
                        TketRiwAlergiObat.getText(), cekSesak, cekMual, cekGatal, cekLainKeluhan, TlainKeluhan.getText(), cmbNyeriNrs.getSelectedItem().toString(),
                        cmbSkalaNyeri.getSelectedItem().toString(), Tkronik.getText(), cmbKeadaanUmum.getSelectedItem().toString(), TlainKeadaanUmum.getText(),
                        cmbKesadaran.getSelectedItem().toString(), Ttd.getText(), cekReguler, cekIreguler, TfrekuensiNadi.getText(), cekNormal, cekKusmaul, cekDispnea,
                        cekEdemaParu, cekLainRespi, TlainRespirasi.getText(), TfrekuensiRespirasi.getText(), cekTdkAnemis, cekAnemis, cekLainKonjung, TlainKonjung.getText(),
                        cekTdkEdema, cekDehidrasi, cekEdema, cekEdemaAnas, cekPucat, cekAcites, TpreHd.getText(), TposHdlalu.getText(), TbbKering.getText(), TbbPostHd.getText(),
                        cekAvFistula, cekFemoral, cekSub, cekJugular, cekFemoralHD, cekKanan, cekKiri, cmbRJpreHD.getSelectedItem().toString(), cmbRJpostHD.getSelectedItem().toString(),
                        TpemeriksaanPenunjang.getText(), cekTglGizi, Valid.SetTgl(TtglGizi.getSelectedItem() + ""), cekMst, Tskor.getText(), Trekomendasi.getText(),
                        cmbKendala.getSelectedItem().toString(), Tkendala.getText(), cmbMerawat.getSelectedItem().toString(), Tmerawat.getText(), cekTenang, cekGelisah, cekTakut,
                        cekTindakanMrh, cekMudah, TdiagnosaKeper.getText(), cekKelebihan, cekGangPerfusi, cekGangKeseimbangan, cekPenurunan, cekNutrisi, cekKetidakpatuhan,
                        cekGangTerhadap, cekGangRasa, cekMonBerat, cekBerikan, cekObservasi, cekBila, cekHentikan, cekKaji, cekPosisikan, cekPenkes, TketAvShunt.getText(),
                        cekMonTandaInfeksi, cekGanti, cekMonTandaHipo, cekLakukan, cekProgram, cekPemberianEry, cekKolab, cekPemberianPre, cekPemberianCa, cekObat, cekPemberianAntipiretik,
                        cekPemberianAntibiotik, cekPemberianAnal, cekLainInterval, TketLainInterval.getText(), cekInisiasi, cekAkut, cekRutin, cekPreOp, cekSled, cekLainInstruksi,
                        TketLainInstruksi.getText(), ThdInstruksi.getText(), Tqb.getText(), Tqd.getText(), TufGoal.getText(), Tprog.getText(), Tuf.getText(), Tbicarbonat.getText(),
                        TdialBicar.getText(), TdialConduc.getText(), TdialTemp.getText(), TdosisSirkulasi.getText(), TdosisAwal.getText(), TdosisMain.getText(), Tconti.getText(),
                        Tintermiten.getText(), Tlmwh.getText(), TtanpaHeparin.getText(), cmbProgram.getSelectedItem().toString(), TcatatanLain.getText(), nipDokter1, cekMasalah,
                        cekPerdarahan, cekFirst, cekSakit, cekMualMuntah, cekKram, cekHiperkalemia, cekHipo, cekHipertensi, cekNyeri, cekAritmia, cekGatalGatal, cekDemam,
                        cekMenggigil, cekLainPenyulit, TevaluasiHd.getText(), TevaluasiQb.getText(), Tkt.getText(), Tdp.getText(), Tobat.getText(), TcatatanMedis.getText(), nipDokter2,
                        tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Ulang Pasien Hemodialisis", "Ganti");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                TabRawat.setSelectedIndex(1);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("tglJamAsesmen", Valid.SetTglINDONESIA(Valid.SetTgl(Ttanggal.getSelectedItem() + "")) + " / "
                    + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
            
            if (TnoMesin.getText().equals("")) {
                param.put("noMesin", ".................");
            } else {
                param.put("noMesin", TnoMesin.getText());
            }
            
            if (ThemoKe.getText().equals("")) {
                param.put("hemoKe", "........");
            } else {
                param.put("hemoKe", ThemoKe.getText());
            }
            
            if (TtipeDialisiser.getText().equals("")) {
                param.put("tipeDialiser", ".................");
            } else {
                param.put("tipeDialiser", TtipeDialisiser.getText());
            }
            
            if (TdxMedis.getText().equals("")) {
                if (Tcc.getText().equals("")) {
                    param.put("dxMedis", "................. Cc. .............");
                } else {
                    param.put("dxMedis", "................. Cc. " + Tcc.getText());
                }
            } else {
                if (Tcc.getText().equals("")) {
                    param.put("dxMedis", TdxMedis.getText() + " Cc. .............");
                } else {
                    param.put("dxMedis", TdxMedis.getText() + " Cc. " + Tcc.getText());
                }
            }
            
            if (cmbRiwAlergiObat.getSelectedIndex() == 1) {
                if (TketRiwAlergiObat.getText().equals("")) {
                    param.put("riwAlergi", cmbRiwAlergiObat.getSelectedItem().toString() + ", ...................");
                } else {
                    param.put("riwAlergi", cmbRiwAlergiObat.getSelectedItem().toString() + ", " + TketRiwAlergiObat.getText());
                }
            } else {
                param.put("riwAlergi", cmbRiwAlergiObat.getSelectedItem().toString());
            }
            
            param.put("caraByr", TcaraByr.getText());
            param.put("pekerjaan", Tpekerjaan.getText());

            if (chkSesakNfs.isSelected() == true) {
                param.put("sesak", "V");
            } else {
                param.put("sesak", "");
            }

            if (chkMual.isSelected() == true) {
                param.put("mual", "V");
            } else {
                param.put("mual", "");
            }

            if (chkGatal.isSelected() == true) {
                param.put("gatal", "V");
            } else {
                param.put("gatal", "");
            }

            if (chkLainKeluhan.isSelected() == true) {
                param.put("lainKeluhan", "V");
                if (TlainKeluhan.getText().equals("")) {
                    param.put("ketLainKeluhan", "Lain-lain : .................");
                } else {
                    param.put("ketLainKeluhan", "Lain-lain : " + TlainKeluhan.getText());
                }             
            } else {
                param.put("lainKeluhan", "");
                param.put("ketLainKeluhan", "Lain-lain");
            }
            
            param.put("nyeriNrs", cmbNyeriNrs.getSelectedItem().toString());
            
            if (cmbSkalaNyeri.getSelectedIndex() == 6) {
                if (Tkronik.getText().equals("")) {
                    param.put("skalaNyeri", cmbSkalaNyeri.getSelectedItem().toString());
                } else {
                    param.put("skalaNyeri", cmbSkalaNyeri.getSelectedItem().toString() + " " + Tkronik.getText());
                }
            } else {
                param.put("skalaNyeri", cmbSkalaNyeri.getSelectedItem().toString());
            }
            
            if (cmbKeadaanUmum.getSelectedIndex() == 4) {
                if (TlainKeadaanUmum.getText().equals("")) {
                    param.put("keadaanUmum", cmbKeadaanUmum.getSelectedItem().toString());
                } else {
                    param.put("keadaanUmum", cmbKeadaanUmum.getSelectedItem().toString() + " : " + TlainKeadaanUmum.getText());
                }
            } else {
                param.put("keadaanUmum", cmbKeadaanUmum.getSelectedItem().toString());
            }
            
            param.put("kesadaran", cmbKesadaran.getSelectedItem().toString());
            
            if (Ttd.getText().equals("")) {
                param.put("tensi", "..........");
            } else {
                param.put("tensi", Ttd.getText());
            }
            
            if (chkReguler.isSelected() == true) {
                param.put("reguler", "V");
            } else {
                param.put("reguler", "");
            }
            
            if (chkIreguler.isSelected() == true) {
                param.put("ireguler", "V");
            } else {
                param.put("ireguler", "");
            }
            
            if (TfrekuensiNadi.getText().equals("")) {
                param.put("frekNadi", "..........");
            } else {
                param.put("frekNadi", TfrekuensiNadi.getText());
            }
            
            if (chkNormal.isSelected() == true) {
                param.put("normal", "V");
            } else {
                param.put("normal", "");
            }
            
            if (chkKusmaul.isSelected() == true) {
                param.put("kusmaul", "V");
            } else {
                param.put("kusmaul", "");
            }
            
            if (chkDispnea.isSelected() == true) {
                param.put("dispnea", "V");
            } else {
                param.put("dispnea", "");
            }
            
            if (chkEdemaParu.isSelected() == true) {
                param.put("edemaParu", "V");
            } else {
                param.put("edemaParu", "");
            }
            
            if (chkLainRespirasi.isSelected() == true) {
                param.put("lainRespi", "V");
                if (TlainRespirasi.getText().equals("")) {
                    param.put("ketLainRespi", "Lain-lain : .................");
                } else {
                    param.put("ketLainRespi", "Lain-lain : " + TlainRespirasi.getText());
                }                
            } else {
                param.put("lainRespi", "");
                param.put("ketLainRespi", "Lain-lain");
            }
            
            if (TfrekuensiRespirasi.getText().equals("")) {
                param.put("frekRespi", "..........");
            } else {
                param.put("frekRespi", TfrekuensiRespirasi.getText());
            }
            
            if (chkTdkAnemis.isSelected() == true) {
                param.put("tdkAnemis", "V");
            } else {
                param.put("tdkAnemis", "");
            }
            
            if (chkAnemis.isSelected() == true) {
                param.put("anemis", "V");
            } else {
                param.put("anemis", "");
            }
            
            if (chkLainKonjung.isSelected() == true) {
                param.put("lainKonjung", "V");
                if (TlainKonjung.getText().equals("")) {
                    param.put("ketLainKonjung", "Lain-lain : .................");
                } else {
                    param.put("ketLainKonjung", "Lain-lain : " + TlainKonjung.getText());
                }                
            } else {
                param.put("lainKonjung", "");
                param.put("ketLainKonjung", "Lain-lain");
            }
            
            if (chkTdkEdema.isSelected() == true) {
                param.put("tdkEdema", "V");
            } else {
                param.put("tdkEdema", "");
            }
            
            if (chkDehidrasi.isSelected() == true) {
                param.put("dehidrasi", "V");
            } else {
                param.put("dehidrasi", "");
            }
            
            if (chkEdema.isSelected() == true) {
                param.put("edema", "V");
            } else {
                param.put("edema", "");
            }
            
            if (chkEdemaAna.isSelected() == true) {
                param.put("edemaAnas", "V");
            } else {
                param.put("edemaAnas", "");
            }
            
            if (chkPucat.isSelected() == true) {
                param.put("pucat", "V");
            } else {
                param.put("pucat", "");
            }
            
            if (chkAcites.isSelected() == true) {
                param.put("acites", "V");
            } else {
                param.put("acites", "");
            }
            
            if (TpreHd.getText().equals("")) {
                param.put("preHD", ".........");
            } else {
                param.put("preHD", TpreHd.getText());
            }
            
            if (TposHdlalu.getText().equals("")) {
                param.put("postHdlalu", ".........");
            } else {
                param.put("postHdlalu", TposHdlalu.getText());
            }
            
            if (TbbKering.getText().equals("")) {
                param.put("bbKering", ".........");
            } else {
                param.put("bbKering", TbbKering.getText());
            }
            
            if (TbbPostHd.getText().equals("")) {
                param.put("bbPostHD", ".........");
            } else {
                param.put("bbPostHD", TbbPostHd.getText());
            }
            
            if (chkAvFistula.isSelected() == true) {
                param.put("avFistula", "V");
            } else {
                param.put("avFistula", "");
            }
            
            if (chkFemoralAkses.isSelected() == true) {
                param.put("femoAkses", "V");
            } else {
                param.put("femoAkses", "");
            }
            
            if (chkSubClavian.isSelected() == true) {
                param.put("sub", "V");
            } else {
                param.put("sub", "");
            }
            
            if (chkJugular.isSelected() == true) {
                param.put("jugular", "V");
            } else {
                param.put("jugular", "");
            }
            
            if (chkFemoralHD.isSelected() == true) {
                param.put("femoHD", "V");
            } else {
                param.put("femoHD", "");
            }
            
            if (chkKanan.isSelected() == true) {
                param.put("kanan", "V");
            } else {
                param.put("kanan", "");
            }
            
            if (chkKiri.isSelected() == true) {
                param.put("kiri", "V");
            } else {
                param.put("kiri", "");
            }
            
            param.put("resJatuhPreHD", cmbRJpreHD.getSelectedItem().toString());
            param.put("resJatuhPostHD", cmbRJpostHD.getSelectedItem().toString());
            
            if (TpemeriksaanPenunjang.getText().equals("")) {
                param.put("pemeriksaanPenunjang", "....................\n");
            } else {
                param.put("pemeriksaanPenunjang", TpemeriksaanPenunjang.getText() + "\n");
            }

            if (chkTglGizi.isSelected() == true) {
                param.put("tglGizi", Valid.SetTglINDONESIA(Valid.SetTgl(TtglGizi.getSelectedItem() + "")));
                if (chkMst.isSelected() == true) {
                    param.put("mst", "V");
                    if (Tskor.getText().equals("")) {
                        param.put("skorMst", ".......");
                    } else {
                        param.put("skorMst", Tskor.getText());
                    }
                } else {
                    param.put("mst", "");
                }

                if (Trekomendasi.getText().equals("")) {
                    param.put("rekomendasi", "................\n");
                } else {
                    param.put("rekomendasi", Trekomendasi.getText()+"\n");
                }
            } else {
                param.put("tglGizi", "................");
                param.put("mst", "");
                param.put("skorMst", ".......");
                param.put("rekomendasi", "................\n");
            }

            if (cmbKendala.getSelectedIndex() == 1) {
                if (Tkendala.getText().equals("")) {
                    param.put("kendala", cmbKendala.getSelectedItem().toString() + ", Jelaskan .............");
                } else {
                    param.put("kendala", cmbKendala.getSelectedItem().toString() + ", Jelaskan " + Tkendala.getText());
                }
            } else {
                param.put("kendala", cmbKendala.getSelectedItem().toString());
            }
            
            if (cmbMerawat.getSelectedIndex() == 1) {
                if (Tmerawat.getText().equals("")) {
                    param.put("merawat", cmbMerawat.getSelectedItem().toString() + ", Jelaskan .............");
                } else {
                    param.put("merawat", cmbMerawat.getSelectedItem().toString() + ", Jelaskan " + Tmerawat.getText());
                }
            } else {
                param.put("merawat", cmbMerawat.getSelectedItem().toString());
            }
            
            if (chkTenang.isSelected() == true) {
                param.put("tenang", "V");
            } else {
                param.put("tenang", "");
            }
            
            if (chkGelisah.isSelected() == true) {
                param.put("gelisah", "V");
            } else {
                param.put("gelisah", "");
            }
            
            if (chkTakut.isSelected() == true) {
                param.put("takut", "V");
            } else {
                param.put("takut", "");
            }
            
            if (chkTindakanMrh.isSelected() == true) {
                param.put("tindakanMrh", "V");
            } else {
                param.put("tindakanMrh", "");
            }
            
            if (chkMudah.isSelected() == true) {
                param.put("mudah", "V");
            } else {
                param.put("mudah", "");
            }
            
            if (TdiagnosaKeper.getText().equals("")) {
                param.put("diagKeperawatan", "..............");
            } else {
                param.put("diagKeperawatan", TdiagnosaKeper.getText());
            }
            
            if (chkKelebihan.isSelected() == true) {
                param.put("kelebihan", "V");
            } else {
                param.put("kelebihan", "");
            }
            
            if (chkGangguanPerfusi.isSelected() == true) {
                param.put("gangPerfusi", "V");
            } else {
                param.put("gangPerfusi", "");
            }
            
            if (chkGangguanKeseimbangan.isSelected() == true) {
                param.put("gangKeseimbangan", "V");
            } else {
                param.put("gangKeseimbangan", "");
            }
            
            if (chkPenurunan.isSelected() == true) {
                param.put("penurunan", "V");
            } else {
                param.put("penurunan", "");
            }
            
            if (chkNutrisi.isSelected() == true) {
                param.put("nutrisi", "V");
            } else {
                param.put("nutrisi", "");
            }
            
            if (chkKetidakpatuhan.isSelected() == true) {
                param.put("ketidakpatuhan", "V");
            } else {
                param.put("ketidakpatuhan", "");
            }
            
            if (chkGangguanTerhadap.isSelected() == true) {
                param.put("gangTerhadap", "V");
            } else {
                param.put("gangTerhadap", "");
            }
            
            if (chkGangguanRasa.isSelected() == true) {
                param.put("gangRasa", "V");
            } else {
                param.put("gangRasa", "");
            }
            
            if (chkMonitorBerat.isSelected() == true) {
                param.put("monBerat", "V");
            } else {
                param.put("monBerat", "");
            }
            
            if (chkBerikan.isSelected() == true) {
                param.put("berikan", "V");
            } else {
                param.put("berikan", "");
            }
            
            if (chkObservasi.isSelected() == true) {
                param.put("observasi", "V");
            } else {
                param.put("observasi", "");
            }
            
            if (chkBilaPasien.isSelected() == true) {
                param.put("bila", "V");
            } else {
                param.put("bila", "");
            }
            
            if (chkHentikan.isSelected() == true) {
                param.put("hentikan", "V");
            } else {
                param.put("hentikan", "");
            }
            
            if (chkKaji.isSelected() == true) {
                param.put("kaji", "V");
            } else {
                param.put("kaji", "");
            }
            
            if (chkPosisikan.isSelected() == true) {
                param.put("posisikan", "V");
            } else {
                param.put("posisikan", "");
            }
            
            if (chkPenkes.isSelected() == true) {
                param.put("penkes", "V");
                if (TketAvShunt.getText().equals("")) {
                    param.put("ketAvShunt", "PENKES : Diet, AV-Shunt, ................");
                } else {
                    param.put("ketAvShunt", "PENKES : Diet, AV-Shunt, " + TketAvShunt.getText());
                }
            } else {
                param.put("penkes", "");
                param.put("ketAvShunt", "PENKES : Diet, AV-Shunt, ................");
            }
            
            if (chkMonitorTandaInfeksi.isSelected() == true) {
                param.put("montaInfeksi", "V");
            } else {
                param.put("montaInfeksi", "");
            }
            
            if (chkGanti.isSelected() == true) {
                param.put("ganti", "V");
            } else {
                param.put("ganti", "");
            }
            
            if (chkMonitorTandaHipo.isSelected() == true) {
                param.put("montaHipo", "V");
            } else {
                param.put("montaHipo", "");
            }
            
            if (chkLakukan.isSelected() == true) {
                param.put("lakukan", "V");
            } else {
                param.put("lakukan", "");
            }
            
            if (chkProgramHD.isSelected() == true) {
                param.put("programHD", "V");
            } else {
                param.put("programHD", "");
            }
            
            if (chkTransfusi.isSelected() == true) {
                param.put("transfusi", "V");
            } else {
                param.put("transfusi", "");
            }
            
            if (chkKolaborasi.isSelected() == true) {
                param.put("kolab", "V");
            } else {
                param.put("kolab", "");
            }
            
            if (chkPemberianCa.isSelected() == true) {
                param.put("pemberianCa", "V");
            } else {
                param.put("pemberianCa", "");
            }
            
            if (chkPemberianAntipiretik.isSelected() == true) {
                param.put("pemberianAntip", "V");
            } else {
                param.put("pemberianAntip", "");
            }
            
            if (chkPemberianAnal.isSelected() == true) {
                param.put("pemberianAnal", "V");
            } else {
                param.put("pemberianAnal", "");
            }
            
            if (chkPemberianEry.isSelected() == true) {
                param.put("pemberianEry", "V");
            } else {
                param.put("pemberianEry", "");
            }
            
            if (chkPemberianPre.isSelected() == true) {
                param.put("pemberianPre", "V");
            } else {
                param.put("pemberianPre", "");
            }
            
            if (chkObat.isSelected() == true) {
                param.put("obatObatan", "V");
            } else {
                param.put("obatObatan", "");
            }
            
            if (chkPemberianAntibiotik.isSelected() == true) {
                param.put("pemberianAntibio", "V");
            } else {
                param.put("pemberianAntibio", "");
            }
            
            if (chkLainyaInterval.isSelected() == true) {
                param.put("lainInterval", "V");
                if (TketLainInterval.getText().equals("")) {
                    param.put("ketLainInterval", "Lain-lain");
                } else {
                    param.put("ketLainInterval", "Lain-lain : " + TketLainInterval.getText());
                }
            } else {
                param.put("lainInterval", "");
                param.put("ketLainInterval", "Lain-lain");
            }
            
            if (chkInisiasi.isSelected() == true) {
                param.put("inisiasi", "V");
            } else {
                param.put("inisiasi", "");
            }
            
            if (chkAkut.isSelected() == true) {
                param.put("akut", "V");
            } else {
                param.put("akut", "");
            }
            
            if (chkRutin.isSelected() == true) {
                param.put("rutin", "V");
            } else {
                param.put("rutin", "");
            }
            
            if (chkPreOp.isSelected() == true) {
                param.put("preOp", "V");
            } else {
                param.put("preOp", "");
            }
            
            if (chkSled.isSelected() == true) {
                param.put("sled", "V");
            } else {
                param.put("sled", "");
            }
            
            if (chkLainyaInstruksi.isSelected() == true) {
                param.put("lainInstruksi", "V");
                if (TketLainInstruksi.getText().equals("")) {
                    param.put("ketLainInstruksi", "Lain-lain");
                } else {
                    param.put("ketLainInstruksi", "Lain-lain : " + TketLainInstruksi.getText());
                }
            } else {
                param.put("lainInstruksi", "");
                param.put("ketLainInstruksi", "Lain-lain");
            }
            
            if (ThdInstruksi.getText().equals("")) {
                param.put("hdInstruksi", ".........");
            } else {
                param.put("hdInstruksi", ThdInstruksi.getText());
            }
            
            if (Tqb.getText().equals("")) {
                param.put("qb", ".........");
            } else {
                param.put("qb", Tqb.getText());
            }
            
            if (Tqd.getText().equals("")) {
                param.put("qd", ".........");
            } else {
                param.put("qd", Tqd.getText());
            }
            
            if (TufGoal.getText().equals("")) {
                param.put("ufGoal", ".........");
            } else {
                param.put("ufGoal", TufGoal.getText());
            }
            
            if (Tprog.getText().equals("")) {
                param.put("progNa", ".........");
            } else {
                param.put("progNa", Tprog.getText());
            }
            
            if (Tuf.getText().equals("")) {
                param.put("uf", ".........");
            } else {
                param.put("uf", Tuf.getText());
            }
            
            if (Tbicarbonat.getText().equals("")) {
                param.put("bicarbonat", ".........");
            } else {
                param.put("bicarbonat", Tbicarbonat.getText());
            }
            
            if (TdialBicar.getText().equals("")) {
                param.put("dialBicarbonat", ".........");
            } else {
                param.put("dialBicarbonat", TdialBicar.getText());
            }
            
            if (TdialConduc.getText().equals("")) {
                param.put("dialConduc", ".........");
            } else {
                param.put("dialConduc", TdialConduc.getText());
            }
            
            if (TdialTemp.getText().equals("")) {
                param.put("dialTempe", ".........");
            } else {
                param.put("dialTempe", TdialTemp.getText());
            }
            
            if (TdosisSirkulasi.getText().equals("")) {
                param.put("dosisSirkulasi", ".........");
            } else {
                param.put("dosisSirkulasi", TdosisSirkulasi.getText());
            }
            
            if (TdosisAwal.getText().equals("")) {
                param.put("dosisAwal", ".........");
            } else {
                param.put("dosisAwal", TdosisAwal.getText());
            }
            
            if (TdosisMain.getText().equals("")) {
                param.put("dosisMain", ".........");
            } else {
                param.put("dosisMain", TdosisMain.getText());
            }
            
            if (Tconti.getText().equals("")) {
                param.put("conti", ".........");
            } else {
                param.put("conti", Tconti.getText());
            }
            
            if (Tintermiten.getText().equals("")) {
                param.put("intermiten", ".........");
            } else {
                param.put("intermiten", Tintermiten.getText());
            }
            
            if (Tlmwh.getText().equals("")) {
                param.put("lmwh", ".........");
            } else {
                param.put("lmwh", Tlmwh.getText());
            }
            
            if (TtanpaHeparin.getText().equals("")) {
                param.put("tanpa", ".........");
            } else {
                param.put("tanpa", TtanpaHeparin.getText());
            }
            
            param.put("programBilas", cmbProgram.getSelectedItem().toString());
            
            if (TcatatanLain.getText().equals("")) {
                param.put("catatanLain", "\n......................");
            } else {
                param.put("catatanLain", "\n" + TcatatanLain.getText());
            }
            
            param.put("nmDokter1", TnmDokter1.getText());
            
            if (chkMasalahAkses.isSelected() == true) {
                param.put("masalah", "V");
            } else {
                param.put("masalah", "");
            }
            
            if (chkPerdarahan.isSelected() == true) {
                param.put("perdarahan", "V");
            } else {
                param.put("perdarahan", "");
            }
            
            if (chkFirst.isSelected() == true) {
                param.put("first", "V");
            } else {
                param.put("first", "");
            }
            
            if (chkSakitKpl.isSelected() == true) {
                param.put("sakit", "V");
            } else {
                param.put("sakit", "");
            }
            
            if (chkMualMuntah.isSelected() == true) {
                param.put("mualMuntah", "V");
            } else {
                param.put("mualMuntah", "");
            }
            
            if (chkKramOtot.isSelected() == true) {
                param.put("kram", "V");
            } else {
                param.put("kram", "");
            }
            
            if (chkHiperkalemia.isSelected() == true) {
                param.put("hiperkalemia", "V");
            } else {
                param.put("hiperkalemia", "");
            }
            
            if (chkHipotensi.isSelected() == true) {
                param.put("hipotensi", "V");
            } else {
                param.put("hipotensi", "");
            }
            
            if (chkHipertensi.isSelected() == true) {
                param.put("hipertensi", "V");
            } else {
                param.put("hipertensi", "");
            }
            
            if (chkNyeriDada.isSelected() == true) {
                param.put("nyeriDada", "V");
            } else {
                param.put("nyeriDada", "");
            }
            
            if (chkAritmia.isSelected() == true) {
                param.put("aritmia", "V");
            } else {
                param.put("aritmia", "");
            }
            
            if (chkGatalGatal.isSelected() == true) {
                param.put("gatalGatal", "V");
            } else {
                param.put("gatalGatal", "");
            }
            
            if (chkDemam.isSelected() == true) {
                param.put("demam", "V");
            } else {
                param.put("demam", "");
            }
            
            if (chkMenggigil.isSelected() == true) {
                param.put("menggigil", "V");
            } else {
                param.put("menggigil", "");
            }
            
            if (chkLainPenyulit.isSelected() == true) {
                param.put("lainPenyulit", "V");
            } else {
                param.put("lainPenyulit", "");
            }
            
            if (TevaluasiHd.getText().equals("")) {
                param.put("evaHD", "..........");
            } else {
                param.put("evaHD", TevaluasiHd.getText());
            }
            
            if (TevaluasiQb.getText().equals("")) {
                param.put("evaQB", "..........");
            } else {
                param.put("evaQB", TevaluasiQb.getText());
            }
            
            if (Tkt.getText().equals("")) {
                param.put("kt", "..........");
            } else {
                param.put("kt", Tkt.getText());
            }
            
            if (Tdp.getText().equals("")) {
                param.put("dp", ".....................");
            } else {
                param.put("dp", Tdp.getText() + "\n");
            }
            
            if (Tobat.getText().equals("")) {
                param.put("obat", ".....................\n");
            } else {
                param.put("obat", Tobat.getText() + "\n");
            }
            
            if (TcatatanMedis.getText().equals("")) {
                param.put("catatanMedis", ".....................\n");
            } else {
                param.put("catatanMedis", TcatatanMedis.getText() + "\n");
            }
            
            param.put("nmDokter2", TnmDokter2.getText());

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isiDokter1 = "", isiDokter2 = "", tglSimpan = "", jamSimpan = "";
                tglSimpan = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from asesmen_ulang_hemodialisis where "
                        + "no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'");
                jamSimpan = Sequel.cariIsi("select time(waktu_simpan) from asesmen_ulang_hemodialisis where "
                        + "no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'");                
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
               
                //dokter1
                if (nipDokter1.equals("") || nipDokter1.equals("-") || nipDokter1.equals("--")) {
                    param.put("lokasiQrDokter1", "");
                } else {
                    isiDokter1 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Asesmen Ulang Pasien Dengan Hemodialisis", TnmDokter1.getText() + " (Dokter)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiDokter1, Sequel.cariFolderTte(), "QRTteDokter1.jpg", "select logo from setting");
                    param.put("lokasiQrDokter1", Sequel.cariFolderTte() + File.separator + "QRTteDokter1.jpg");
                }
                
                //dokter2
                if (nipDokter2.equals("") || nipDokter2.equals("-") || nipDokter2.equals("--")) {
                    param.put("lokasiQrDokter2", "");
                } else {
                    isiDokter2 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Asesmen Ulang Pasien Dengan Hemodialisis", TnmDokter2.getText() + " (Dokter)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiDokter2, Sequel.cariFolderTte(), "QRTteDokter2.jpg", "select logo from setting");
                    param.put("lokasiQrDokter2", Sequel.cariFolderTte() + File.separator + "QRTteDokter2.jpg");
                }
                
                Valid.MyReport("rptAsesmenUlangHemodialisis2Qr.jasper", "report", "::[ Asesmen Ulang Pasien Dengan Hemodialisis hal.2 ]::",
                        "SELECT date(now()) tgl", param);
                
                Valid.MyReport("rptAsesmenUlangHemodialisis1Qr.jasper", "report", "::[ Asesmen Ulang Pasien Dengan Hemodialisis hal.1 ]::",
                        "SELECT date(now()) tgl", param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                BtnBatalActionPerformed(null);                
            } else {
                Valid.MyReport("rptAsesmenUlangHemodialisis2.jasper", "report", "::[ Asesmen Ulang Pasien Dengan Hemodialisis hal.2 ]::",
                        "SELECT date(now()) tgl", param);
                
                Valid.MyReport("rptAsesmenUlangHemodialisis1.jasper", "report", "::[ Asesmen Ulang Pasien Dengan Hemodialisis hal.1 ]::",
                        "SELECT date(now()) tgl", param);
                BtnBatalActionPerformed(null);
            }

            TabRawat.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu...");
            tampil();
            tbAsesmen.requestFocus();
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
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
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbAsesmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsesmenMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbAsesmen.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbAsesmenMouseClicked

    private void tbAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsesmenKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from asesmen_ulang_hemodialisis where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_ulang_hemodialisis where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkReguler.requestFocus();
        }
    }//GEN-LAST:event_TtdKeyPressed

    private void TfrekuensiNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfrekuensiNadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkNormal.requestFocus();
        }
    }//GEN-LAST:event_TfrekuensiNadiKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TfrekuensiRespirasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfrekuensiRespirasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTdkAnemis.requestFocus();
        }
    }//GEN-LAST:event_TfrekuensiRespirasiKeyPressed

    private void TpreHdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpreHdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TposHdlalu.requestFocus();
        }
    }//GEN-LAST:event_TpreHdKeyPressed

    private void TposHdlaluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TposHdlaluKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbbKering.requestFocus();
        }
    }//GEN-LAST:event_TposHdlaluKeyPressed

    private void TbbKeringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeringKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbbPostHd.requestFocus();
        }
    }//GEN-LAST:event_TbbKeringKeyPressed

    private void TbbPostHdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbPostHdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkAvFistula.requestFocus();
        }
    }//GEN-LAST:event_TbbPostHdKeyPressed

    private void TskorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trekomendasi.requestFocus();
        }
    }//GEN-LAST:event_TskorKeyPressed

    private void ThdInstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThdInstruksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tqb.requestFocus();
        }
    }//GEN-LAST:event_ThdInstruksiKeyPressed

    private void TqbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tqd.requestFocus();
        }
    }//GEN-LAST:event_TqbKeyPressed

    private void TqdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TufGoal.requestFocus();
        }
    }//GEN-LAST:event_TqdKeyPressed

    private void TufGoalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TufGoalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tprog.requestFocus();
        }
    }//GEN-LAST:event_TufGoalKeyPressed

    private void TprogKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprogKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tuf.requestFocus();
        }
    }//GEN-LAST:event_TprogKeyPressed

    private void TufKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TufKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbicarbonat.requestFocus();
        }
    }//GEN-LAST:event_TufKeyPressed

    private void TbicarbonatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbicarbonatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdialBicar.requestFocus();
        }
    }//GEN-LAST:event_TbicarbonatKeyPressed

    private void TdialBicarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdialBicarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdialConduc.requestFocus();
        }
    }//GEN-LAST:event_TdialBicarKeyPressed

    private void TdialConducKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdialConducKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdialTemp.requestFocus();
        }
    }//GEN-LAST:event_TdialConducKeyPressed

    private void TdialTempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdialTempKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdosisSirkulasi.requestFocus();
        }
    }//GEN-LAST:event_TdialTempKeyPressed

    private void TdosisSirkulasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisSirkulasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdosisAwal.requestFocus();
        }
    }//GEN-LAST:event_TdosisSirkulasiKeyPressed

    private void TdosisAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisAwalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdosisMain.requestFocus();
        }
    }//GEN-LAST:event_TdosisAwalKeyPressed

    private void TdosisMainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisMainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tconti.requestFocus();
        }
    }//GEN-LAST:event_TdosisMainKeyPressed

    private void TcontiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcontiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tintermiten.requestFocus();
        }
    }//GEN-LAST:event_TcontiKeyPressed

    private void TintermitenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TintermitenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlmwh.requestFocus();
        }
    }//GEN-LAST:event_TintermitenKeyPressed

    private void TlmwhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlmwhKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtanpaHeparin.requestFocus();
        }
    }//GEN-LAST:event_TlmwhKeyPressed

    private void TtanpaHeparinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtanpaHeparinKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbProgram.requestFocus();
        }
    }//GEN-LAST:event_TtanpaHeparinKeyPressed

    private void TcatatanLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMasalahAkses.requestFocus();
        }
    }//GEN-LAST:event_TcatatanLainKeyPressed

    private void TevaluasiHdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TevaluasiHdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TevaluasiQb.requestFocus();
        }
    }//GEN-LAST:event_TevaluasiHdKeyPressed

    private void TevaluasiQbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TevaluasiQbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkt.requestFocus();
        }
    }//GEN-LAST:event_TevaluasiQbKeyPressed

    private void TktKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TktKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdp.requestFocus();
        }
    }//GEN-LAST:event_TktKeyPressed

    private void TdpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tobat.requestFocus();
        }
    }//GEN-LAST:event_TdpKeyPressed

    private void TobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TcatatanMedis.requestFocus();
        }
    }//GEN-LAST:event_TobatKeyPressed

    private void TcatatanMedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanMedisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter2.requestFocus();
        }
    }//GEN-LAST:event_TcatatanMedisKeyPressed

    private void TnoMesinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoMesinKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ThemoKe.requestFocus();
        }
    }//GEN-LAST:event_TnoMesinKeyPressed

    private void ThemoKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThemoKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtipeDialisiser.requestFocus();
        }
    }//GEN-LAST:event_ThemoKeKeyPressed

    private void TtipeDialisiserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtipeDialisiserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdxMedis.requestFocus();
        }
    }//GEN-LAST:event_TtipeDialisiserKeyPressed

    private void TdxMedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdxMedisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tcc.requestFocus();
        }
    }//GEN-LAST:event_TdxMedisKeyPressed

    private void TccKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TccKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRiwAlergiObat.requestFocus();
        }
    }//GEN-LAST:event_TccKeyPressed

    private void TketRiwAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRiwAlergiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSesakNfs.requestFocus();
        }
    }//GEN-LAST:event_TketRiwAlergiObatKeyPressed

    private void TlainKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbNyeriNrs.requestFocus();
        }
    }//GEN-LAST:event_TlainKeluhanKeyPressed

    private void TkronikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkronikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKeadaanUmum.requestFocus();
        }
    }//GEN-LAST:event_TkronikKeyPressed

    private void TlainKeadaanUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKeadaanUmumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKesadaran.requestFocus();
        }
    }//GEN-LAST:event_TlainKeadaanUmumKeyPressed

    private void TlainRespirasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainRespirasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TfrekuensiRespirasi.requestFocus();
        }
    }//GEN-LAST:event_TlainRespirasiKeyPressed

    private void TlainKonjungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKonjungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTdkEdema.requestFocus();
        }
    }//GEN-LAST:event_TlainKonjungKeyPressed

    private void TpemeriksaanPenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpemeriksaanPenunjangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkTglGizi.requestFocus();
        }
    }//GEN-LAST:event_TpemeriksaanPenunjangKeyPressed

    private void TrekomendasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrekomendasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKendala.requestFocus();
        }
    }//GEN-LAST:event_TrekomendasiKeyPressed

    private void TkendalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkendalaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMerawat.requestFocus();
        }
    }//GEN-LAST:event_TkendalaKeyPressed

    private void TmerawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmerawatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTenang.requestFocus();
        }
    }//GEN-LAST:event_TmerawatKeyPressed

    private void TdiagnosaKeperKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKeperKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkKelebihan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaKeperKeyPressed

    private void TketAvShuntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketAvShuntKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMonitorTandaInfeksi.requestFocus();
        }
    }//GEN-LAST:event_TketAvShuntKeyPressed

    private void TketLainIntervalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainIntervalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkInisiasi.requestFocus();
        }
    }//GEN-LAST:event_TketLainIntervalKeyPressed

    private void TketLainInstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainInstruksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ThdInstruksi.requestFocus();
        }
    }//GEN-LAST:event_TketLainInstruksiKeyPressed

    private void BtnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter1ActionPerformed
        pilihan = 0;
        pilihan = 1;
        initDokter();
        akses.setform("RMAsesmenUlangHemodialisis");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokter1ActionPerformed

    private void BtnDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter2ActionPerformed
        pilihan = 0;
        pilihan = 2;
        initDokter();
        akses.setform("RMAsesmenUlangHemodialisis");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokter2ActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsesmenUlangHemodialisis");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu.");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsesmenUlangHemodialisis");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih.");
        } else {
            if (TpemeriksaanPenunjang.getText().equals("")) {
                TpemeriksaanPenunjang.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TpemeriksaanPenunjang.setText(TpemeriksaanPenunjang.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void cmbRiwAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwAlergiObatActionPerformed
        TketRiwAlergiObat.setText("");
        if (cmbRiwAlergiObat.getSelectedIndex() == 1) {
            TketRiwAlergiObat.setEnabled(true);
            TketRiwAlergiObat.requestFocus();
        } else {
            TketRiwAlergiObat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwAlergiObatActionPerformed

    private void chkLainKeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainKeluhanActionPerformed
        TlainKeluhan.setText("");
        if (chkLainKeluhan.isSelected() == true) {
            TlainKeluhan.setEnabled(true);
            TlainKeluhan.requestFocus();
        } else {
            TlainKeluhan.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainKeluhanActionPerformed

    private void cmbSkalaNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSkalaNyeriActionPerformed
        Tkronik.setText("");
        if (cmbSkalaNyeri.getSelectedIndex() == 6) {
            Tkronik.setEnabled(true);
            Tkronik.requestFocus();
        } else {
            Tkronik.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSkalaNyeriActionPerformed

    private void cmbKeadaanUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeadaanUmumActionPerformed
        TlainKeadaanUmum.setText("");
        if (cmbKeadaanUmum.getSelectedIndex() == 4) {
            TlainKeadaanUmum.setEnabled(true);
            TlainKeadaanUmum.requestFocus();
        } else {
            TlainKeadaanUmum.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKeadaanUmumActionPerformed

    private void chkLainRespirasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainRespirasiActionPerformed
        TlainRespirasi.setText("");
        if (chkLainRespirasi.isSelected() == true) {
            TlainRespirasi.setEnabled(true);
            TlainRespirasi.requestFocus();
        } else {
            TlainRespirasi.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainRespirasiActionPerformed

    private void chkLainKonjungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainKonjungActionPerformed
        TlainKonjung.setText("");
        if (chkLainKonjung.isSelected() == true) {
            TlainKonjung.setEnabled(true);
            TlainKonjung.requestFocus();
        } else {
            TlainKonjung.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainKonjungActionPerformed

    private void chkTglGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglGiziActionPerformed
        TtglGizi.setDate(new Date());
        chkMst.setSelected(false);
        Tskor.setText("");
        Trekomendasi.setText("");
        if (chkTglGizi.isSelected() == true) {
            TtglGizi.setEnabled(true);
            chkMst.setEnabled(true);
            Tskor.setEnabled(false);
            Trekomendasi.setEnabled(true);
            TtglGizi.requestFocus();
        } else {
            TtglGizi.setEnabled(false);
            chkMst.setEnabled(false);
            Tskor.setEnabled(false);
            Trekomendasi.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglGiziActionPerformed

    private void chkMstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMstActionPerformed
        Tskor.setText("");
        if (chkMst.isSelected() == true) {
            Tskor.setEnabled(true);
            Tskor.requestFocus();
        } else {
            Tskor.setEnabled(false);
        }
    }//GEN-LAST:event_chkMstActionPerformed

    private void cmbKendalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKendalaActionPerformed
        Tkendala.setText("");
        if (cmbKendala.getSelectedIndex() == 1) {
            Tkendala.setEnabled(true);
            Tkendala.requestFocus();
        } else {
            Tkendala.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKendalaActionPerformed

    private void cmbMerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMerawatActionPerformed
        Tmerawat.setText("");
        if (cmbMerawat.getSelectedIndex() == 1) {
            Tmerawat.setEnabled(true);
            Tmerawat.requestFocus();
        } else {
            Tmerawat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMerawatActionPerformed

    private void chkPenkesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPenkesActionPerformed
        TketAvShunt.setText("");
        if (chkPenkes.isSelected() == true) {
            TketAvShunt.setEnabled(true);
            TketAvShunt.requestFocus();
        } else {
            TketAvShunt.setEnabled(false);
        }
    }//GEN-LAST:event_chkPenkesActionPerformed

    private void chkLainyaIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainyaIntervalActionPerformed
        TketLainInterval.setText("");
        if (chkLainyaInterval.isSelected() == true) {
            TketLainInterval.setEnabled(true);
            TketLainInterval.requestFocus();
        } else {
            TketLainInterval.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainyaIntervalActionPerformed

    private void chkLainyaInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainyaInstruksiActionPerformed
        TketLainInstruksi.setText("");
        if (chkLainyaInstruksi.isSelected() == true) {
            TketLainInstruksi.setEnabled(true);
            TketLainInstruksi.requestFocus();
        } else {
            TketLainInstruksi.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainyaInstruksiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenUlangHemodialisis dialog = new RMAsesmenUlangHemodialisis(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter1;
    private widget.Button BtnDokter2;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TbbKering;
    private widget.TextBox TbbPostHd;
    private widget.TextBox Tbicarbonat;
    private widget.TextBox TcaraByr;
    private widget.TextArea TcatatanLain;
    private widget.TextArea TcatatanMedis;
    private widget.TextBox Tcc;
    private widget.TextBox Tconti;
    private widget.TextBox TdiagnosaKeper;
    private widget.TextBox TdialBicar;
    private widget.TextBox TdialConduc;
    private widget.TextBox TdialTemp;
    private widget.TextBox TdosisAwal;
    private widget.TextBox TdosisMain;
    private widget.TextBox TdosisSirkulasi;
    private widget.TextArea Tdp;
    private widget.TextBox TdxMedis;
    private widget.TextBox TevaluasiHd;
    private widget.TextBox TevaluasiQb;
    private widget.TextBox TfrekuensiNadi;
    private widget.TextBox TfrekuensiRespirasi;
    private widget.TextBox ThdInstruksi;
    private widget.TextBox ThemoKe;
    private widget.TextBox Tintermiten;
    private widget.TextBox Tkendala;
    private widget.TextBox TketAvShunt;
    private widget.TextBox TketLainInstruksi;
    private widget.TextBox TketLainInterval;
    private widget.TextBox TketRiwAlergiObat;
    private widget.TextBox Tkronik;
    private widget.TextBox Tkt;
    private widget.TextBox TlainKeadaanUmum;
    private widget.TextBox TlainKeluhan;
    private widget.TextBox TlainKonjung;
    private widget.TextBox TlainRespirasi;
    private widget.TextBox Tlmwh;
    private widget.TextBox Tmerawat;
    private widget.TextBox TnmDokter1;
    private widget.TextBox TnmDokter2;
    private widget.TextBox TnoMesin;
    private widget.TextArea Tobat;
    private widget.TextBox Tpekerjaan;
    private widget.TextArea TpemeriksaanPenunjang;
    private widget.TextBox TposHdlalu;
    private widget.TextBox TpreHd;
    private widget.TextBox Tprog;
    private widget.TextBox Tqb;
    private widget.TextBox Tqd;
    private widget.TextBox Trekomendasi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tskor;
    private widget.Tanggal Ttanggal;
    private widget.TextBox TtanpaHeparin;
    private widget.TextBox Ttd;
    private widget.Tanggal TtglGizi;
    private widget.TextBox TtipeDialisiser;
    private widget.TextBox Tuf;
    private widget.TextBox TufGoal;
    public widget.CekBox chkAcites;
    public widget.CekBox chkAkut;
    public widget.CekBox chkAnemis;
    public widget.CekBox chkAritmia;
    public widget.CekBox chkAvFistula;
    public widget.CekBox chkBerikan;
    public widget.CekBox chkBilaPasien;
    public widget.CekBox chkDehidrasi;
    public widget.CekBox chkDemam;
    public widget.CekBox chkDispnea;
    public widget.CekBox chkEdema;
    public widget.CekBox chkEdemaAna;
    public widget.CekBox chkEdemaParu;
    public widget.CekBox chkFemoralAkses;
    public widget.CekBox chkFemoralHD;
    public widget.CekBox chkFirst;
    public widget.CekBox chkGangguanKeseimbangan;
    public widget.CekBox chkGangguanPerfusi;
    public widget.CekBox chkGangguanRasa;
    public widget.CekBox chkGangguanTerhadap;
    public widget.CekBox chkGanti;
    public widget.CekBox chkGatal;
    public widget.CekBox chkGatalGatal;
    public widget.CekBox chkGelisah;
    public widget.CekBox chkHentikan;
    public widget.CekBox chkHiperkalemia;
    public widget.CekBox chkHipertensi;
    public widget.CekBox chkHipotensi;
    public widget.CekBox chkInisiasi;
    public widget.CekBox chkIreguler;
    public widget.CekBox chkJugular;
    public widget.CekBox chkKaji;
    public widget.CekBox chkKanan;
    public widget.CekBox chkKelebihan;
    public widget.CekBox chkKetidakpatuhan;
    public widget.CekBox chkKiri;
    public widget.CekBox chkKolaborasi;
    public widget.CekBox chkKramOtot;
    public widget.CekBox chkKusmaul;
    public widget.CekBox chkLainKeluhan;
    public widget.CekBox chkLainKonjung;
    public widget.CekBox chkLainPenyulit;
    public widget.CekBox chkLainRespirasi;
    public widget.CekBox chkLainyaInstruksi;
    public widget.CekBox chkLainyaInterval;
    public widget.CekBox chkLakukan;
    public widget.CekBox chkMasalahAkses;
    public widget.CekBox chkMenggigil;
    public widget.CekBox chkMonitorBerat;
    public widget.CekBox chkMonitorTandaHipo;
    public widget.CekBox chkMonitorTandaInfeksi;
    public widget.CekBox chkMst;
    public widget.CekBox chkMual;
    public widget.CekBox chkMualMuntah;
    public widget.CekBox chkMudah;
    public widget.CekBox chkNormal;
    public widget.CekBox chkNutrisi;
    public widget.CekBox chkNyeriDada;
    public widget.CekBox chkObat;
    public widget.CekBox chkObservasi;
    public widget.CekBox chkPemberianAnal;
    public widget.CekBox chkPemberianAntibiotik;
    public widget.CekBox chkPemberianAntipiretik;
    public widget.CekBox chkPemberianCa;
    public widget.CekBox chkPemberianEry;
    public widget.CekBox chkPemberianPre;
    public widget.CekBox chkPenkes;
    public widget.CekBox chkPenurunan;
    public widget.CekBox chkPerdarahan;
    public widget.CekBox chkPosisikan;
    public widget.CekBox chkPreOp;
    public widget.CekBox chkProgramHD;
    public widget.CekBox chkPucat;
    public widget.CekBox chkReguler;
    public widget.CekBox chkRutin;
    public widget.CekBox chkSakitKpl;
    public widget.CekBox chkSesakNfs;
    public widget.CekBox chkSled;
    public widget.CekBox chkSubClavian;
    public widget.CekBox chkTakut;
    public widget.CekBox chkTdkAnemis;
    public widget.CekBox chkTdkEdema;
    public widget.CekBox chkTenang;
    public widget.CekBox chkTglGizi;
    public widget.CekBox chkTindakanMrh;
    public widget.CekBox chkTransfusi;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKeadaanUmum;
    private widget.ComboBox cmbKendala;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMerawat;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbNyeriNrs;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbProgram;
    private widget.ComboBox cmbRJpostHD;
    private widget.ComboBox cmbRJpreHD;
    private widget.ComboBox cmbRiwAlergiObat;
    private widget.ComboBox cmbSkalaNyeri;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel47;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
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
    private widget.Label jLabel79;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label104;
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
    private widget.Label label115;
    private widget.Label label116;
    private widget.Label label117;
    private widget.Label label118;
    private widget.Label label119;
    private widget.Label label120;
    private widget.Label label121;
    private widget.Label label38;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbAsesmen;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select au.*, p.no_rkm_medis, p.nm_pasien, date_format(au.tgl_asesmen,'%d-%m-%Y') tglAses, time_format(au.jam_asesmen,'%H:%i Wita') jamAses, "
                    + "pg1.nama nmDokter1, pg2.nama nmDokter2 from asesmen_ulang_hemodialisis au inner join reg_periksa rp on rp.no_rawat=au.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=au.nip_dokter1 inner join pegawai pg2 on pg2.nik=au.nip_dokter2 WHERE "
                    + "au.tgl_asesmen BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "au.tgl_asesmen BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "au.tgl_asesmen BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "au.tgl_asesmen BETWEEN ? AND ? AND pg1.nama LIKE ? ORDER BY au.tgl_asesmen");
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
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglAses"),
                        rs.getString("jamAses"),
                        rs.getString("ruang_rawat"),
                        rs.getString("nmDokter1"),
                        rs.getString("nmDokter2"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("no_mesin"),
                        rs.getString("hemodialisis_ke"),
                        rs.getString("tipe_dialisiser"),
                        rs.getString("diagnosa_medis"),
                        rs.getString("cc"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("ket_riwayat_alergi"),
                        rs.getString("sesak_nafas"),
                        rs.getString("mual_muntah"),
                        rs.getString("gatal"),
                        rs.getString("lain_lain"),
                        rs.getString("ket_lain_lain"),
                        rs.getString("nyeri_nrs"),
                        rs.getString("skala_nyeri"),
                        rs.getString("ket_kronik"),
                        rs.getString("keadaan_umum"),
                        rs.getString("ket_keadaan_umum"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("reguler"),
                        rs.getString("ireguler"),
                        rs.getString("nadi_frekuensi"),
                        rs.getString("normal"),
                        rs.getString("kusmaul"),
                        rs.getString("dispnea"),
                        rs.getString("edema_respirasi"),
                        rs.getString("lain_respirasi"),
                        rs.getString("ket_lain_respirasi"),
                        rs.getString("respirasi_frekuensi"),
                        rs.getString("tidak_anemis"),
                        rs.getString("anemis"),
                        rs.getString("lain_konjungtiva"),
                        rs.getString("ket_lain_konjungtiva"),
                        rs.getString("tidak_edema"),
                        rs.getString("dehidrasi"),
                        rs.getString("edema_ekstremitas"),
                        rs.getString("edema_anasarka"),
                        rs.getString("pucat"),
                        rs.getString("acites"),
                        rs.getString("pre_hd"),
                        rs.getString("post_hd_yang_lalu"),
                        rs.getString("bb_kering"),
                        rs.getString("bb_post_hd"),
                        rs.getString("av_fistula"),
                        rs.getString("femoral_akses"),
                        rs.getString("sub_clavian"),
                        rs.getString("jugular"),
                        rs.getString("femoral_hd"),
                        rs.getString("kanan"),
                        rs.getString("kiri"),
                        rs.getString("kes_jatuh_pre_hd"),
                        rs.getString("kes_jatuh_post_hd"),
                        rs.getString("pemeriksaan_penunjang"),
                        rs.getString("cek_tgl_gizi"),
                        rs.getString("tgl_gizi"),
                        rs.getString("mst"),
                        rs.getString("skor_total"),
                        rs.getString("rekomendasi"),
                        rs.getString("kendala_komunikasi"),
                        rs.getString("ket_kendala"),
                        rs.getString("yang_merawat"),
                        rs.getString("ket_yang_merawat"),
                        rs.getString("tenang"),
                        rs.getString("gelisah"),
                        rs.getString("takut"),
                        rs.getString("marah"),
                        rs.getString("mudah"),
                        rs.getString("diagnosis_keperawatan"),
                        rs.getString("kelebihan_volume"),
                        rs.getString("gangguan_perfusi"),
                        rs.getString("gangguan_keseimbangan"),
                        rs.getString("penurunan"),
                        rs.getString("nutrisi"),
                        rs.getString("ketidakpatuhan"),
                        rs.getString("gangguan_terhadap"),
                        rs.getString("gangguan_rasa"),
                        rs.getString("monitor_berat"),
                        rs.getString("berikan"),
                        rs.getString("observasi"),
                        rs.getString("bila_pasien"),
                        rs.getString("hentikan"),
                        rs.getString("kaji"),
                        rs.getString("posisikan"),
                        rs.getString("penkes"),
                        rs.getString("ket_av_shunt"),
                        rs.getString("monitor_tanda_infeksi"),
                        rs.getString("ganti_balutan"),
                        rs.getString("monitor_tanda_hipoglikemi"),
                        rs.getString("lakukan"),
                        rs.getString("program_hd"),
                        rs.getString("pemberian_ery"),
                        rs.getString("kolaborasi"),
                        rs.getString("pemberian_pre"),
                        rs.getString("pemberian_ca"),
                        rs.getString("obat_emergency"),
                        rs.getString("pemberian_antipiretik"),
                        rs.getString("pemberian_antibiotik"),
                        rs.getString("pemberian_analgesik"),
                        rs.getString("lainnya_interval"),
                        rs.getString("ket_lainnya_interval"),
                        rs.getString("inisiasi"),
                        rs.getString("akut"),
                        rs.getString("rutin"),
                        rs.getString("pre_op"),
                        rs.getString("sled"),
                        rs.getString("lainya_instruksi"),
                        rs.getString("ket_lainnya_instruksi"),
                        rs.getString("hd_jam"),
                        rs.getString("qb"),
                        rs.getString("qd"),
                        rs.getString("uf_goal"),
                        rs.getString("na"),
                        rs.getString("uf"),
                        rs.getString("bicarbonat"),
                        rs.getString("dialisat_bicarbonat"),
                        rs.getString("dialisat_conductivity"),
                        rs.getString("dialisat_temperatur"),
                        rs.getString("sirkulasi"),
                        rs.getString("awal"),
                        rs.getString("maintenance"),
                        rs.getString("continues"),
                        rs.getString("intermiten"),
                        rs.getString("lmwh"),
                        rs.getString("tanpa_heparin"),
                        rs.getString("program_bilas"),
                        rs.getString("catatan_lain"),
                        rs.getString("nip_dokter1"),
                        rs.getString("masalah_akses"),
                        rs.getString("perdarahan"),
                        rs.getString("first"),
                        rs.getString("sakit_kepala"),
                        rs.getString("penyulit_mual"),
                        rs.getString("kram"),
                        rs.getString("hiperkalemia"),
                        rs.getString("hipotensi"),
                        rs.getString("hipertensi"),
                        rs.getString("nyeri_dada"),
                        rs.getString("aritmia"),
                        rs.getString("gatal_gatal"),
                        rs.getString("demam"),
                        rs.getString("menggigil"),
                        rs.getString("lain_penyulit"),
                        rs.getString("evaluasi_hd"),
                        rs.getString("evaluasi_qb"),
                        rs.getString("evaluasi_kt"),
                        rs.getString("discharge_planing"),
                        rs.getString("obat"),
                        rs.getString("catatan_medis"),
                        rs.getString("nip_dokter2"),
                        rs.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        Ttanggal.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        TnoMesin.setText("");
        ThemoKe.setText("");
        TtipeDialisiser.setText("");
        TdxMedis.setText("");
        Tcc.setText("");
        cmbRiwAlergiObat.setSelectedIndex(0);
        TketRiwAlergiObat.setText("");
        TketRiwAlergiObat.setEnabled(false);
        TcaraByr.setText("");
        Tpekerjaan.setText("");
        chkSesakNfs.setSelected(false);
        chkMual.setSelected(false);
        chkGatal.setSelected(false);
        chkLainKeluhan.setSelected(false);
        TlainKeluhan.setText("");
        TlainKeluhan.setEnabled(false);
        cmbNyeriNrs.setSelectedIndex(0);
        cmbSkalaNyeri.setSelectedIndex(0);
        Tkronik.setText("");
        Tkronik.setEnabled(false);
        cmbKeadaanUmum.setSelectedIndex(0);
        TlainKeadaanUmum.setText("");
        TlainKeadaanUmum.setEnabled(false);
        cmbKesadaran.setSelectedIndex(0);
        Ttd.setText("");
        chkReguler.setSelected(false);
        chkIreguler.setSelected(false);
        TfrekuensiNadi.setText("");
        chkNormal.setSelected(false);
        chkKusmaul.setSelected(false);
        chkDispnea.setSelected(false);
        chkEdemaParu.setSelected(false);
        chkLainRespirasi.setSelected(false);
        TlainRespirasi.setText("");
        TlainRespirasi.setEnabled(false);
        TfrekuensiRespirasi.setText("");
        chkTdkAnemis.setSelected(false);
        chkAnemis.setSelected(false);
        chkLainKonjung.setSelected(false);
        TlainKonjung.setText("");
        TlainKonjung.setEnabled(false);
        chkTdkEdema.setSelected(false);
        chkDehidrasi.setSelected(false);
        chkEdema.setSelected(false);
        chkEdemaAna.setSelected(false);
        chkPucat.setSelected(false);
        chkAcites.setSelected(false);
        TpreHd.setText("");
        TposHdlalu.setText("");
        TbbKering.setText("");
        TbbPostHd.setText("");
        chkAvFistula.setSelected(false);
        chkFemoralAkses.setSelected(false);
        chkSubClavian.setSelected(false);
        chkJugular.setSelected(false);
        chkFemoralHD.setSelected(false);
        chkKanan.setSelected(false);
        chkKiri.setSelected(false);
        cmbRJpreHD.setSelectedIndex(0);
        cmbRJpostHD.setSelectedIndex(0);
        TpemeriksaanPenunjang.setText("");
        chkTglGizi.setSelected(false);
        TtglGizi.setDate(new Date());
        chkMst.setSelected(false);
        Tskor.setText("");
        Trekomendasi.setText("");
        TtglGizi.setEnabled(false);
        chkMst.setEnabled(false);
        Tskor.setEnabled(false);
        Trekomendasi.setEnabled(false);
        cmbKendala.setSelectedIndex(0);
        Tkendala.setText("");
        Tkendala.setEnabled(false);
        cmbMerawat.setSelectedIndex(0);
        Tmerawat.setText("");
        Tmerawat.setEnabled(false);
        chkTenang.setSelected(false);
        chkGelisah.setSelected(false);
        chkTakut.setSelected(false);
        chkTindakanMrh.setSelected(false);
        chkMudah.setSelected(false);
        TdiagnosaKeper.setText("");
        chkKelebihan.setSelected(false);
        chkGangguanPerfusi.setSelected(false);
        chkGangguanKeseimbangan.setSelected(false);
        chkPenurunan.setSelected(false);
        chkNutrisi.setSelected(false);
        chkKetidakpatuhan.setSelected(false);
        chkGangguanTerhadap.setSelected(false);
        chkGangguanRasa.setSelected(false);
        chkMonitorBerat.setSelected(false);
        chkBerikan.setSelected(false);
        chkObservasi.setSelected(false);
        chkBilaPasien.setSelected(false);
        chkHentikan.setSelected(false);
        chkKaji.setSelected(false);
        chkPosisikan.setSelected(false);
        chkPenkes.setSelected(false);
        TketAvShunt.setText("");
        TketAvShunt.setEnabled(false);
        chkMonitorTandaInfeksi.setSelected(false);
        chkGanti.setSelected(false);
        chkMonitorTandaHipo.setSelected(false);
        chkLakukan.setSelected(false);
        chkProgramHD.setSelected(false);
        chkTransfusi.setSelected(false);
        chkKolaborasi.setSelected(false);
        chkPemberianCa.setSelected(false);
        chkPemberianAntipiretik.setSelected(false);
        chkPemberianAnal.setSelected(false);
        chkPemberianEry.setSelected(false);
        chkPemberianPre.setSelected(false);
        chkObat.setSelected(false);
        chkPemberianAntibiotik.setSelected(false);
        chkLainyaInterval.setSelected(false);
        TketLainInterval.setText("");
        TketLainInterval.setEnabled(false);
        chkInisiasi.setSelected(false);
        chkAkut.setSelected(false);
        chkRutin.setSelected(false);
        chkPreOp.setSelected(false);
        chkSled.setSelected(false);
        chkLainyaInstruksi.setSelected(false);
        TketLainInstruksi.setText("");
        TketLainInstruksi.setEnabled(false);
        ThdInstruksi.setText("");
        Tqb.setText("");
        Tqd.setText("");
        TufGoal.setText("");
        Tprog.setText("");
        Tuf.setText("");
        Tbicarbonat.setText("");
        TdialBicar.setText("");
        TdialConduc.setText("");
        TdialTemp.setText("");
        TdosisSirkulasi.setText("");
        TdosisAwal.setText("");
        TdosisMain.setText("");
        Tconti.setText("");
        Tintermiten.setText("");
        Tlmwh.setText("");
        TtanpaHeparin.setText("");
        cmbProgram.setSelectedIndex(0);
        nipDokter1 = "-";
        TnmDokter1.setText("-");
        TcatatanLain.setText("");
        chkMasalahAkses.setSelected(false);
        chkPerdarahan.setSelected(false);
        chkFirst.setSelected(false);
        chkSakitKpl.setSelected(false);
        chkMualMuntah.setSelected(false);
        chkKramOtot.setSelected(false);
        chkHiperkalemia.setSelected(false);
        chkHipotensi.setSelected(false);
        chkHipertensi.setSelected(false);
        chkNyeriDada.setSelected(false);
        chkAritmia.setSelected(false);
        chkGatalGatal.setSelected(false);
        chkDemam.setSelected(false);
        chkMenggigil.setSelected(false);
        chkLainPenyulit.setSelected(false);
        TevaluasiHd.setText("");
        TevaluasiQb.setText("");
        Tkt.setText("");
        Tdp.setText("");
        Tobat.setText("");
        TcatatanMedis.setText("");
        nipDokter2 = "-";
        TnmDokter2.setText("-");
    }

    private void getData() {
        variabelBersih();
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            isPasien();
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 5).toString());
            Valid.SetTgl(Ttanggal, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString().substring(6, 8));
            TnoMesin.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString());
            ThemoKe.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString());
            TtipeDialisiser.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString());
            TdxMedis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            Tcc.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString());
            cmbRiwAlergiObat.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            TketRiwAlergiObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            cekSesak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            cekMual = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            cekGatal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString();
            cekLainKeluhan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString();
            TlainKeluhan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            cmbNyeriNrs.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());
            cmbSkalaNyeri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            Tkronik.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            cmbKeadaanUmum.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            TlainKeadaanUmum.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            cmbKesadaran.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            Ttd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString());
            cekReguler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString();
            cekIreguler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString();
            TfrekuensiNadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString());
            cekNormal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString();
            cekKusmaul = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString();
            cekDispnea = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString();
            cekEdemaParu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString();
            cekLainRespi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString();
            TlainRespirasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            TfrekuensiRespirasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            cekTdkAnemis = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString();
            cekAnemis = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString();
            cekLainKonjung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString();
            TlainKonjung.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            cekTdkEdema = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString();
            cekDehidrasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString();
            cekEdema = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString();
            cekEdemaAnas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString();
            cekPucat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString();
            cekAcites = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString();
            TpreHd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());
            TposHdlalu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            TbbKering.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            TbbPostHd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            cekAvFistula = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString();
            cekFemoral = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString();
            cekSub = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            cekJugular = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            cekFemoralHD = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString();
            cekKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString();
            cekKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString();
            cmbRJpreHD.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            cmbRJpostHD.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString());
            TpemeriksaanPenunjang.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString());
            cekTglGizi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString();
            Valid.SetTgl(TtglGizi, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString());
            cekMst = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString();
            Tskor.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString());
            Trekomendasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString());
            cmbKendala.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            Tkendala.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());
            cmbMerawat.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString());
            Tmerawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());
            cekTenang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString();
            cekGelisah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString();
            cekTakut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();
            cekTindakanMrh = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString();
            cekMudah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString();
            TdiagnosaKeper.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString());
            cekKelebihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString();
            cekGangPerfusi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString();
            cekGangKeseimbangan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString();
            cekPenurunan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString();
            cekNutrisi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            cekKetidakpatuhan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString();
            cekGangTerhadap = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString();
            cekGangRasa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString();
            cekMonBerat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString();
            cekBerikan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString();
            cekObservasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString();
            cekBila = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString();
            cekHentikan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString();
            cekKaji = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString();
            cekPosisikan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString();
            cekPenkes = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString();
            TketAvShunt.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());
            cekMonTandaInfeksi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString();
            cekGanti = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString();
            cekMonTandaHipo = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString();
            cekLakukan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString();
            cekProgram = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString();
            cekPemberianEry = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString();
            cekKolab = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString();
            cekPemberianPre = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString();
            cekPemberianCa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString();
            cekObat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString();
            cekPemberianAntipiretik = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString();
            cekPemberianAntibiotik = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString();
            cekPemberianAnal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString();
            cekLainInterval = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString();
            TketLainInterval.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString());
            cekInisiasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString();
            cekAkut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString();
            cekRutin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString();
            cekPreOp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString();            
            cekSled = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString();
            cekLainInstruksi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString();
            TketLainInstruksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString());
            ThdInstruksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString());
            Tqb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString());
            Tqd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString());
            TufGoal.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString());
            Tprog.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString());
            Tuf.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 123).toString());
            Tbicarbonat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString());
            TdialBicar.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString());
            TdialConduc.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString());
            TdialTemp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 127).toString());
            TdosisSirkulasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 128).toString());
            TdosisAwal.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 129).toString());
            TdosisMain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 130).toString());
            Tconti.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString());
            Tintermiten.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 132).toString());
            Tlmwh.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString());
            TtanpaHeparin.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 134).toString());
            cmbProgram.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 137).toString());
            TcatatanLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 136).toString());
            nipDokter1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 137).toString();
            TnmDokter1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString());
            cekMasalah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 138).toString();
            cekPerdarahan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 139).toString();
            cekFirst = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 140).toString();
            cekSakit = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 141).toString();
            cekMualMuntah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 142).toString();
            cekKram = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 143).toString();
            cekHiperkalemia = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 144).toString();
            cekHipo = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 145).toString();
            cekHipertensi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 146).toString();
            cekNyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 147).toString();
            cekAritmia = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 148).toString();
            cekGatalGatal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 149).toString();
            cekDemam = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 150).toString();
            cekMenggigil = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 151).toString();
            cekLainPenyulit = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 152).toString();
            TevaluasiHd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 153).toString());
            TevaluasiQb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 154).toString());
            Tkt.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 155).toString());
            Tdp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 156).toString());
            Tobat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 157).toString());
            TcatatanMedis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 158).toString());
            nipDokter2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 159).toString();
            TnmDokter2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString());
            dataCek();
        }
    }

    public void setData(String norw, String sttsRawat, String unit) {
        TNoRw.setText(norw);
        TrgRawat.setText(unit);
        sttsrwt = sttsRawat;
        isPasien();
        Valid.SetTgl(DTPCari1, tglReg);
        Valid.SetTgl(Ttanggal, tglReg);
        cmbJam.setSelectedItem(jamReg.toString().substring(0, 2));
        cmbMnt.setSelectedItem(jamReg.toString().substring(3, 5));
        cmbDtk.setSelectedItem(jamReg.toString().substring(6, 8));
        TCari.setText(norw);            
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.gethemodialisa());
        BtnHapus.setEnabled(akses.gethemodialisa());
        BtnEdit.setEnabled(akses.gethemodialisa());
    }

    private void initDokter() {
        if (dokter == null) {
            dokter = new DlgCariDokter(null, false);

            dokter.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("RMAsesmenUlangHemodialisis")) {
                        if (pilihan == 1) {
                            if (dokter.getTable().getSelectedRow() != -1) {
                                nipDokter1 = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                                TnmDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            }
                            BtnDokter1.requestFocus();
                        } else if (pilihan == 2) {
                            if (dokter.getTable().getSelectedRow() != -1) {
                                nipDokter2 = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                                TnmDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            }
                            BtnDokter2.requestFocus();
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
    }

    private void cekData() {
        if (chkSesakNfs.isSelected() == true) {
            cekSesak = "ya";
        } else {
            cekSesak = "tidak";
        }

        if (chkMual.isSelected() == true) {
            cekMual = "ya";
        } else {
            cekMual = "tidak";
        }

        if (chkGatal.isSelected() == true) {
            cekGatal = "ya";
        } else {
            cekGatal = "tidak";
        }

        if (chkLainKeluhan.isSelected() == true) {
            cekLainKeluhan = "ya";
        } else {
            cekLainKeluhan = "tidak";
        }

        if (chkReguler.isSelected() == true) {
            cekReguler = "ya";
        } else {
            cekReguler = "tidak";
        }

        if (chkIreguler.isSelected() == true) {
            cekIreguler = "ya";
        } else {
            cekIreguler = "tidak";
        }

        if (chkNormal.isSelected() == true) {
            cekNormal = "ya";
        } else {
            cekNormal = "tidak";
        }

        if (chkKusmaul.isSelected() == true) {
            cekKusmaul = "ya";
        } else {
            cekKusmaul = "tidak";
        }

        if (chkDispnea.isSelected() == true) {
            cekDispnea = "ya";
        } else {
            cekDispnea = "tidak";
        }

        if (chkEdemaParu.isSelected() == true) {
            cekEdemaParu = "ya";
        } else {
            cekEdemaParu = "tidak";
        }

        if (chkLainRespirasi.isSelected() == true) {
            cekLainRespi = "ya";
        } else {
            cekLainRespi = "tidak";
        }

        if (chkTdkAnemis.isSelected() == true) {
            cekTdkAnemis = "ya";
        } else {
            cekTdkAnemis = "tidak";
        }

        if (chkAnemis.isSelected() == true) {
            cekAnemis = "ya";
        } else {
            cekAnemis = "tidak";
        }

        if (chkLainKonjung.isSelected() == true) {
            cekLainKonjung = "ya";
        } else {
            cekLainKonjung = "tidak";
        }

        if (chkTdkEdema.isSelected() == true) {
            cekTdkEdema = "ya";
        } else {
            cekTdkEdema = "tidak";
        }
        
        if (chkDehidrasi.isSelected() == true) {
            cekDehidrasi = "ya";
        } else {
            cekDehidrasi = "tidak";
        }
        
        if (chkEdema.isSelected() == true) {
            cekEdema = "ya";
        } else {
            cekEdema = "tidak";
        }
        
        if (chkEdemaAna.isSelected() == true) {
            cekEdemaAnas = "ya";
        } else {
            cekEdemaAnas = "tidak";
        }
        
        if (chkPucat.isSelected() == true) {
            cekPucat = "ya";
        } else {
            cekPucat = "tidak";
        }
        
        if (chkAcites.isSelected() == true) {
            cekAcites = "ya";
        } else {
            cekAcites = "tidak";
        }
        
        if (chkAvFistula.isSelected() == true) {
            cekAvFistula = "ya";
        } else {
            cekAvFistula = "tidak";
        }
        
        if (chkFemoralAkses.isSelected() == true) {
            cekFemoral = "ya";
        } else {
            cekFemoral = "tidak";
        }
        
        if (chkSubClavian.isSelected() == true) {
            cekSub = "ya";
        } else {
            cekSub = "tidak";
        }
        
        if (chkJugular.isSelected() == true) {
            cekJugular = "ya";
        } else {
            cekJugular = "tidak";
        }
        
        if (chkFemoralHD.isSelected() == true) {
            cekFemoralHD = "ya";
        } else {
            cekFemoralHD = "tidak";
        }
        
        if (chkKanan.isSelected() == true) {
            cekKanan = "ya";
        } else {
            cekKanan = "tidak";
        }
        
        if (chkKiri.isSelected() == true) {
            cekKiri = "ya";
        } else {
            cekKiri = "tidak";
        }
        
        if (chkTglGizi.isSelected() == true) {
            cekTglGizi = "ya";
        } else {
            cekTglGizi = "tidak";
        }
        
        if (chkMst.isSelected() == true) {
            cekMst = "ya";
        } else {
            cekMst = "tidak";
        }
        
        if (chkTenang.isSelected() == true) {
            cekTenang = "ya";
        } else {
            cekTenang = "tidak";
        }
        
        if (chkGelisah.isSelected() == true) {
            cekGelisah = "ya";
        } else {
            cekGelisah = "tidak";
        }
        
        if (chkTakut.isSelected() == true) {
            cekTakut = "ya";
        } else {
            cekTakut = "tidak";
        }
        
        if (chkTindakanMrh.isSelected() == true) {
            cekTindakanMrh = "ya";
        } else {
            cekTindakanMrh = "tidak";
        }
        
        if (chkMudah.isSelected() == true) {
            cekMudah = "ya";
        } else {
            cekMudah = "tidak";
        }
        
        if (chkKelebihan.isSelected() == true) {
            cekKelebihan = "ya";
        } else {
            cekKelebihan = "tidak";
        }
        
        if (chkGangguanPerfusi.isSelected() == true) {
            cekGangPerfusi = "ya";
        } else {
            cekGangPerfusi = "tidak";
        }
        
        if (chkGangguanKeseimbangan.isSelected() == true) {
            cekGangKeseimbangan = "ya";
        } else {
            cekGangKeseimbangan = "tidak";
        }
        
        if (chkPenurunan.isSelected() == true) {
            cekPenurunan = "ya";
        } else {
            cekPenurunan = "tidak";
        }
        
        if (chkNutrisi.isSelected() == true) {
            cekNutrisi = "ya";
        } else {
            cekNutrisi = "tidak";
        }
        
        if (chkKetidakpatuhan.isSelected() == true) {
            cekKetidakpatuhan = "ya";
        } else {
            cekKetidakpatuhan = "tidak";
        }
        
        if (chkGangguanTerhadap.isSelected() == true) {
            cekGangTerhadap = "ya";
        } else {
            cekGangTerhadap = "tidak";
        }
        
        if (chkGangguanRasa.isSelected() == true) {
            cekGangRasa = "ya";
        } else {
            cekGangRasa = "tidak";
        }
        
        if (chkMonitorBerat.isSelected() == true) {
            cekMonBerat = "ya";
        } else {
            cekMonBerat = "tidak";
        }
        
        if (chkBerikan.isSelected() == true) {
            cekBerikan = "ya";
        } else {
            cekBerikan = "tidak";
        }
        
        if (chkObservasi.isSelected() == true) {
            cekObservasi = "ya";
        } else {
            cekObservasi = "tidak";
        }
        
        if (chkBilaPasien.isSelected() == true) {
            cekBila = "ya";
        } else {
            cekBila = "tidak";
        }
        
        if (chkHentikan.isSelected() == true) {
            cekHentikan = "ya";
        } else {
            cekHentikan = "tidak";
        }
        
        if (chkKaji.isSelected() == true) {
            cekKaji = "ya";
        } else {
            cekKaji = "tidak";
        }
        
        if (chkPosisikan.isSelected() == true) {
            cekPosisikan = "ya";
        } else {
            cekPosisikan = "tidak";
        }
        
        if (chkPenkes.isSelected() == true) {
            cekPenkes = "ya";
        } else {
            cekPenkes = "tidak";
        }
        
        if (chkMonitorTandaInfeksi.isSelected() == true) {
            cekMonTandaInfeksi = "ya";
        } else {
            cekMonTandaInfeksi = "tidak";
        }
        
        if (chkGanti.isSelected() == true) {
            cekGanti = "ya";
        } else {
            cekGanti = "tidak";
        }
        
        if (chkMonitorTandaHipo.isSelected() == true) {
            cekMonTandaHipo = "ya";
        } else {
            cekMonTandaHipo = "tidak";
        }
        
        if (chkLakukan.isSelected() == true) {
            cekLakukan = "ya";
        } else {
            cekLakukan = "tidak";
        }
        
        if (chkProgramHD.isSelected() == true) {
            cekProgram = "ya";
        } else {
            cekProgram = "tidak";
        }
        
        if (chkTransfusi.isSelected() == true) {
            cekTransfusi = "ya";
        } else {
            cekTransfusi = "tidak";
        }
        
        if (chkKolaborasi.isSelected() == true) {
            cekKolab = "ya";
        } else {
            cekKolab = "tidak";
        }
        
        if (chkPemberianCa.isSelected() == true) {
            cekPemberianCa = "ya";
        } else {
            cekPemberianCa = "tidak";
        }
        
        if (chkPemberianAntipiretik.isSelected() == true) {
            cekPemberianAntipiretik = "ya";
        } else {
            cekPemberianAntipiretik = "tidak";
        }
        
        if (chkPemberianAnal.isSelected() == true) {
            cekPemberianAnal = "ya";
        } else {
            cekPemberianAnal = "tidak";
        }
        
        if (chkPemberianEry.isSelected() == true) {
            cekPemberianEry = "ya";
        } else {
            cekPemberianEry = "tidak";
        }
        
        if (chkPemberianPre.isSelected() == true) {
            cekPemberianPre = "ya";
        } else {
            cekPemberianPre = "tidak";
        }
        
        if (chkObat.isSelected() == true) {
            cekObat = "ya";
        } else {
            cekObat = "tidak";
        }
        
        if (chkPemberianAntibiotik.isSelected() == true) {
            cekPemberianAntibiotik = "ya";
        } else {
            cekPemberianAntibiotik = "tidak";
        }
        
        if (chkLainyaInterval.isSelected() == true) {
            cekLainInterval = "ya";
        } else {
            cekLainInterval = "tidak";
        }
        
        if (chkInisiasi.isSelected() == true) {
            cekInisiasi = "ya";
        } else {
            cekInisiasi = "tidak";
        }
        
        if (chkAkut.isSelected() == true) {
            cekAkut = "ya";
        } else {
            cekAkut = "tidak";
        }
        
        if (chkRutin.isSelected() == true) {
            cekRutin = "ya";
        } else {
            cekRutin = "tidak";
        }
        
        if (chkPreOp.isSelected() == true) {
            cekPreOp = "ya";
        } else {
            cekPreOp = "tidak";
        }
        
        if (chkSled.isSelected() == true) {
            cekSled = "ya";
        } else {
            cekSled = "tidak";
        }
        
        if (chkLainyaInstruksi.isSelected() == true) {
            cekLainInstruksi = "ya";
        } else {
            cekLainInstruksi = "tidak";
        }
        
        if (chkMasalahAkses.isSelected() == true) {
            cekMasalah = "ya";
        } else {
            cekMasalah = "tidak";
        }
        
        if (chkPerdarahan.isSelected() == true) {
            cekPerdarahan = "ya";
        } else {
            cekPerdarahan = "tidak";
        }
        
        if (chkFirst.isSelected() == true) {
            cekFirst = "ya";
        } else {
            cekFirst = "tidak";
        }
        
        if (chkSakitKpl.isSelected() == true) {
            cekSakit = "ya";
        } else {
            cekSakit = "tidak";
        }
        
        if (chkMualMuntah.isSelected() == true) {
            cekMualMuntah = "ya";
        } else {
            cekMualMuntah = "tidak";
        }
        
        if (chkKramOtot.isSelected() == true) {
            cekKram = "ya";
        } else {
            cekKram = "tidak";
        }
        
        if (chkHiperkalemia.isSelected() == true) {
            cekHiperkalemia = "ya";
        } else {
            cekHiperkalemia = "tidak";
        }
        
        if (chkHipotensi.isSelected() == true) {
            cekHipo = "ya";
        } else {
            cekHipo = "tidak";
        }
        
        if (chkHipertensi.isSelected() == true) {
            cekHipertensi = "ya";
        } else {
            cekHipertensi = "tidak";
        }
        
        if (chkNyeriDada.isSelected() == true) {
            cekNyeri = "ya";
        } else {
            cekNyeri = "tidak";
        }
        
        if (chkAritmia.isSelected() == true) {
            cekAritmia = "ya";
        } else {
            cekAritmia = "tidak";
        }
        
        if (chkGatalGatal.isSelected() == true) {
            cekGatalGatal = "ya";
        } else {
            cekGatalGatal = "tidak";
        }
        
        if (chkDemam.isSelected() == true) {
            cekDemam = "ya";
        } else {
            cekDemam = "tidak";
        }
        
        if (chkMenggigil.isSelected() == true) {
            cekMenggigil = "ya";
        } else {
            cekMenggigil = "tidak";
        }
        
        if (chkLainPenyulit.isSelected() == true) {
            cekLainPenyulit = "ya";
        } else {
            cekLainPenyulit = "tidak";
        }
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select p.*, rp.tgl_registrasi, rp.jam_reg, pj.png_jawab from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    TcaraByr.setText(rs1.getString("png_jawab"));
                    Tpekerjaan.setText(rs1.getString("pekerjaan"));
                    tglReg = rs1.getString("tgl_registrasi");
                    jamReg = rs1.getString("jam_reg");
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
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void variabelBersih() {
        nipDokter1 = "";
        nipDokter2 = "";
        tglReg = "";
        jamReg = "";
        sttsrwt = "";
        cekSesak = "";
        cekMual = "";
        cekGatal = "";
        cekLainKeluhan = "";
        cekReguler = "";
        cekIreguler = "";
        cekNormal = "";
        cekKusmaul = "";
        cekDispnea = "";
        cekEdemaParu = "";
        cekLainRespi = "";
        cekTdkAnemis = "";
        cekAnemis = "";
        cekLainKonjung = "";
        cekTdkEdema = "";
        cekDehidrasi = "";
        cekEdema = "";
        cekEdemaAnas = "";
        cekPucat = "";
        cekAcites = "";
        cekAvFistula = "";
        cekFemoral = "";
        cekSub = "";
        cekJugular = "";
        cekFemoralHD = "";
        cekKanan = "";
        cekKiri = "";
        cekTglGizi = "";
        cekMst = "";
        cekTenang = "";
        cekGelisah = "";
        cekTakut = "";
        cekTindakanMrh = "";
        cekMudah = "";
        cekKelebihan = "";
        cekGangPerfusi = "";
        cekGangKeseimbangan = "";
        cekPenurunan = "";
        cekNutrisi = "";
        cekKetidakpatuhan = "";
        cekGangTerhadap = "";
        cekGangRasa = "";
        cekMonBerat = "";
        cekBerikan = "";
        cekObservasi = "";
        cekBila = "";
        cekHentikan = "";
        cekKaji = "";
        cekPosisikan = "";
        cekPenkes = "";
        cekMonTandaInfeksi = "";
        cekGanti = "";
        cekMonTandaHipo = "";
        cekLakukan = "";
        cekProgram = "";
        cekTransfusi = "";
        cekKolab = "";
        cekPemberianCa = "";
        cekPemberianAntipiretik = "";
        cekPemberianAnal = "";
        cekPemberianEry = "";
        cekPemberianPre = "";
        cekObat = "";
        cekPemberianAntibiotik = "";
        cekLainInterval = "";
        cekInisiasi = "";
        cekAkut = "";
        cekRutin = "";
        cekPreOp = "";
        cekSled = "";
        cekLainInstruksi = "";
        cekMasalah = "";
        cekPerdarahan = "";
        cekFirst = "";
        cekSakit = "";
        cekMualMuntah = "";
        cekKram = "";
        cekHiperkalemia = "";
        cekHipo = "";
        cekHipertensi = "";
        cekNyeri = "";
        cekAritmia = "";
        cekGatalGatal = "";
        cekDemam = "";
        cekMenggigil = "";
        cekLainPenyulit = "";
    }
    
    private void dataCek() {
        if (cmbRiwAlergiObat.getSelectedIndex() == 1) {
            TketRiwAlergiObat.setEnabled(true);
        } else {
            TketRiwAlergiObat.setEnabled(false);
        }
        
        if (cekSesak.equals("ya")) {
            chkSesakNfs.setSelected(true);
        } else {
            chkSesakNfs.setSelected(false);
        }
        
        if (cekMual.equals("ya")) {
            chkMual.setSelected(true);
        } else {
            chkMual.setSelected(false);
        }
        
        if (cekGatal.equals("ya")) {
            chkGatal.setSelected(true);
        } else {
            chkGatal.setSelected(false);
        }
        
        if (cekLainKeluhan.equals("ya")) {
            chkLainKeluhan.setSelected(true);
            TlainKeluhan.setEnabled(true);
        } else {
            chkLainKeluhan.setSelected(false);
            TlainKeluhan.setEnabled(false);
        }
        
        if (cmbSkalaNyeri.getSelectedIndex() == 6) {
            Tkronik.setEnabled(true);
        } else {
            Tkronik.setEnabled(false);
        }
        
        if (cmbKeadaanUmum.getSelectedIndex() == 4) {
            TlainKeadaanUmum.setEnabled(true);
        } else {
            TlainKeadaanUmum.setEnabled(false);
        }
        
        if (cekReguler.equals("ya")) {
            chkReguler.setSelected(true);
        } else {
            chkReguler.setSelected(false);
        }
        
        if (cekIreguler.equals("ya")) {
            chkIreguler.setSelected(true);
        } else {
            chkIreguler.setSelected(false);
        }
        
        if (cekNormal.equals("ya")) {
            chkNormal.setSelected(true);
        } else {
            chkNormal.setSelected(false);
        }
        
        if (cekKusmaul.equals("ya")) {
            chkKusmaul.setSelected(true);
        } else {
            chkKusmaul.setSelected(false);
        }
        
        if (cekDispnea.equals("ya")) {
            chkDispnea.setSelected(true);
        } else {
            chkDispnea.setSelected(false);
        }
        
        if (cekEdemaParu.equals("ya")) {
            chkEdemaParu.setSelected(true);
        } else {
            chkEdemaParu.setSelected(false);
        }
        
        if (cekLainRespi.equals("ya")) {
            chkLainRespirasi.setSelected(true);
            TlainRespirasi.setEnabled(true);
        } else {
            chkLainRespirasi.setSelected(false);
            TlainRespirasi.setEnabled(false);
        }
        
        if (cekTdkAnemis.equals("ya")) {
            chkTdkAnemis.setSelected(true);
        } else {
            chkTdkAnemis.setSelected(false);
        }
        
        if (cekAnemis.equals("ya")) {
            chkAnemis.setSelected(true);
        } else {
            chkAnemis.setSelected(false);
        }
        
        if (cekLainKonjung.equals("ya")) {
            chkLainKonjung.setSelected(true);
            TlainKonjung.setEnabled(true);
        } else {
            chkLainKonjung.setSelected(false);
            TlainKonjung.setEnabled(false);
        }
        
        if (cekTdkEdema.equals("ya")) {
            chkTdkEdema.setSelected(true);
        } else {
            chkTdkEdema.setSelected(false);
        }
        
        if (cekDehidrasi.equals("ya")) {
            chkDehidrasi.setSelected(true);
        } else {
            chkDehidrasi.setSelected(false);
        }
        
        if (cekEdema.equals("ya")) {
            chkEdema.setSelected(true);
        } else {
            chkEdema.setSelected(false);
        }
        
        if (cekEdemaAnas.equals("ya")) {
            chkEdemaAna.setSelected(true);
        } else {
            chkEdemaAna.setSelected(false);
        }
        
        if (cekPucat.equals("ya")) {
            chkPucat.setSelected(true);
        } else {
            chkPucat.setSelected(false);
        }
        
        if (cekAcites.equals("ya")) {
            chkAcites.setSelected(true);
        } else {
            chkAcites.setSelected(false);
        }
        
        if (cekAvFistula.equals("ya")) {
            chkAvFistula.setSelected(true);
        } else {
            chkAvFistula.setSelected(false);
        }
        
        if (cekFemoral.equals("ya")) {
            chkFemoralAkses.setSelected(true);
        } else {
            chkFemoralAkses.setSelected(false);
        }
        
        if (cekSub.equals("ya")) {
            chkSubClavian.setSelected(true);
        } else {
            chkSubClavian.setSelected(false);
        }
        
        if (cekJugular.equals("ya")) {
            chkJugular.setSelected(true);
        } else {
            chkJugular.setSelected(false);
        }
        
        if (cekFemoralHD.equals("ya")) {
            chkFemoralHD.setSelected(true);
        } else {
            chkFemoralHD.setSelected(false);
        }
        
        if (cekKanan.equals("ya")) {
            chkKanan.setSelected(true);
        } else {
            chkKanan.setSelected(false);
        }
        
        if (cekKiri.equals("ya")) {
            chkKiri.setSelected(true);
        } else {
            chkKiri.setSelected(false);
        }
        
        if (cekTglGizi.equals("ya")) {
            chkTglGizi.setSelected(true);
            TtglGizi.setEnabled(true);
            chkMst.setEnabled(true);
            Trekomendasi.setEnabled(true);
            
            if (cekMst.equals("ya")) {
                chkMst.setSelected(true);
                Tskor.setEnabled(true);
            } else {
                chkMst.setSelected(false);
                Tskor.setEnabled(false);
            }
        } else {
            chkTglGizi.setSelected(false);
            TtglGizi.setEnabled(false);
            chkMst.setEnabled(false);
            Tskor.setEnabled(false);
            Trekomendasi.setEnabled(false);
        }
        
        if (cmbKendala.getSelectedIndex() == 1) {
            Tkendala.setEnabled(true);
        } else {
            Tkendala.setEnabled(false);
        }
        
        if (cmbMerawat.getSelectedIndex() == 1) {
            Tmerawat.setEnabled(true);
        } else {
            Tmerawat.setEnabled(false);
        }
        
        if (cekTenang.equals("ya")) {
            chkTenang.setSelected(true);
        } else {
            chkTenang.setSelected(false);
        }
        
        if (cekGelisah.equals("ya")) {
            chkGelisah.setSelected(true);
        } else {
            chkGelisah.setSelected(false);
        }
        
        if (cekTakut.equals("ya")) {
            chkTakut.setSelected(true);
        } else {
            chkTakut.setSelected(false);
        }
        
        if (cekTindakanMrh.equals("ya")) {
            chkTindakanMrh.setSelected(true);
        } else {
            chkTindakanMrh.setSelected(false);
        }
        
        if (cekMudah.equals("ya")) {
            chkMudah.setSelected(true);
        } else {
            chkMudah.setSelected(false);
        }
        
        if (cekKelebihan.equals("ya")) {
            chkKelebihan.setSelected(true);
        } else {
            chkKelebihan.setSelected(false);
        }
        
        if (cekGangPerfusi.equals("ya")) {
            chkGangguanPerfusi.setSelected(true);
        } else {
            chkGangguanPerfusi.setSelected(false);
        }
        
        if (cekGangKeseimbangan.equals("ya")) {
            chkGangguanKeseimbangan.setSelected(true);
        } else {
            chkGangguanKeseimbangan.setSelected(false);
        }
        
        if (cekPenurunan.equals("ya")) {
            chkPenurunan.setSelected(true);
        } else {
            chkPenurunan.setSelected(false);
        }
        
        if (cekNutrisi.equals("ya")) {
            chkNutrisi.setSelected(true);
        } else {
            chkNutrisi.setSelected(false);
        }
        
        if (cekKetidakpatuhan.equals("ya")) {
            chkKetidakpatuhan.setSelected(true);
        } else {
            chkKetidakpatuhan.setSelected(false);
        }
        
        if (cekGangTerhadap.equals("ya")) {
            chkGangguanTerhadap.setSelected(true);
        } else {
            chkGangguanTerhadap.setSelected(false);
        }
        
        if (cekGangRasa.equals("ya")) {
            chkGangguanRasa.setSelected(true);
        } else {
            chkGangguanRasa.setSelected(false);
        }
        
        if (cekMonBerat.equals("ya")) {
            chkMonitorBerat.setSelected(true);
        } else {
            chkMonitorBerat.setSelected(false);
        }
        
        if (cekBerikan.equals("ya")) {
            chkBerikan.setSelected(true);
        } else {
            chkBerikan.setSelected(false);
        }
        
        if (cekObservasi.equals("ya")) {
            chkObservasi.setSelected(true);
        } else {
            chkObservasi.setSelected(false);
        }
        
        if (cekBila.equals("ya")) {
            chkBilaPasien.setSelected(true);
        } else {
            chkBilaPasien.setSelected(false);
        }
        
        if (cekHentikan.equals("ya")) {
            chkHentikan.setSelected(true);
        } else {
            chkHentikan.setSelected(false);
        }
        
        if (cekKaji.equals("ya")) {
            chkKaji.setSelected(true);
        } else {
            chkKaji.setSelected(false);
        }
        
        if (cekPosisikan.equals("ya")) {
            chkPosisikan.setSelected(true);
        } else {
            chkPosisikan.setSelected(false);
        }
        
        if (cekPenkes.equals("ya")) {
            chkPenkes.setSelected(true);
            TketAvShunt.setEnabled(true);
        } else {
            chkPenkes.setSelected(false);
            TketAvShunt.setEnabled(false);
        }
        
        if (cekMonTandaInfeksi.equals("ya")) {
            chkMonitorTandaInfeksi.setSelected(true);
        } else {
            chkMonitorTandaInfeksi.setSelected(false);
        }
        
        if (cekGanti.equals("ya")) {
            chkGanti.setSelected(true);
        } else {
            chkGanti.setSelected(false);
        }
        
        if (cekMonTandaHipo.equals("ya")) {
            chkMonitorTandaHipo.setSelected(true);
        } else {
            chkMonitorTandaHipo.setSelected(false);
        }
        
        if (cekLakukan.equals("ya")) {
            chkLakukan.setSelected(true);
        } else {
            chkLakukan.setSelected(false);
        }
        
        if (cekProgram.equals("ya")) {
            chkProgramHD.setSelected(true);
        } else {
            chkProgramHD.setSelected(false);
        }
        
        if (cekTransfusi.equals("ya")) {
            chkTransfusi.setSelected(true);
        } else {
            chkTransfusi.setSelected(false);
        }
        
        if (cekKolab.equals("ya")) {
            chkKolaborasi.setSelected(true);
        } else {
            chkKolaborasi.setSelected(false);
        }
        
        if (cekPemberianCa.equals("ya")) {
            chkPemberianCa.setSelected(true);
        } else {
            chkPemberianCa.setSelected(false);
        }
        
        if (cekPemberianAntipiretik.equals("ya")) {
            chkPemberianAntipiretik.setSelected(true);
        } else {
            chkPemberianAntipiretik.setSelected(false);
        }
        
        if (cekPemberianAnal.equals("ya")) {
            chkPemberianAnal.setSelected(true);
        } else {
            chkPemberianAnal.setSelected(false);
        }
        
        if (cekPemberianEry.equals("ya")) {
            chkPemberianEry.setSelected(true);
        } else {
            chkPemberianEry.setSelected(false);
        }
        
        if (cekPemberianPre.equals("ya")) {
            chkPemberianPre.setSelected(true);
        } else {
            chkPemberianPre.setSelected(false);
        }
        
        if (cekObat.equals("ya")) {
            chkObat.setSelected(true);
        } else {
            chkObat.setSelected(false);
        }
        
        if (cekPemberianAntibiotik.equals("ya")) {
            chkPemberianAntibiotik.setSelected(true);
        } else {
            chkPemberianAntibiotik.setSelected(false);
        }
        
        if (cekLainInterval.equals("ya")) {
            chkLainyaInterval.setSelected(true);
            TketLainInterval.setEnabled(true);
        } else {
            chkLainyaInterval.setSelected(false);
            TketLainInterval.setEnabled(false);
        }
        
        if (cekInisiasi.equals("ya")) {
            chkInisiasi.setSelected(true);
        } else {
            chkInisiasi.setSelected(false);
        }
        
        if (cekAkut.equals("ya")) {
            chkAkut.setSelected(true);
        } else {
            chkAkut.setSelected(false);
        }
        
        if (cekRutin.equals("ya")) {
            chkRutin.setSelected(true);
        } else {
            chkRutin.setSelected(false);
        }
        
        if (cekPreOp.equals("ya")) {
            chkPreOp.setSelected(true);
        } else {
            chkPreOp.setSelected(false);
        }
        
        if (cekSled.equals("ya")) {
            chkSled.setSelected(true);
        } else {
            chkSled.setSelected(false);
        }
        
        if (cekLainInstruksi.equals("ya")) {
            chkLainyaInstruksi.setSelected(true);
            TketLainInstruksi.setEnabled(true);
        } else {
            chkLainyaInstruksi.setSelected(false);
            TketLainInstruksi.setEnabled(false);
        }
        
        if (cekMasalah.equals("ya")) {
            chkMasalahAkses.setSelected(true);
        } else {
            chkMasalahAkses.setSelected(false);
        }
        
        if (cekPerdarahan.equals("ya")) {
            chkPerdarahan.setSelected(true);
        } else {
            chkPerdarahan.setSelected(false);
        }
        
        if (cekFirst.equals("ya")) {
            chkFirst.setSelected(true);
        } else {
            chkFirst.setSelected(false);
        }
        
        if (cekSakit.equals("ya")) {
            chkSakitKpl.setSelected(true);
        } else {
            chkSakitKpl.setSelected(false);
        }
        
        if (cekMualMuntah.equals("ya")) {
            chkMualMuntah.setSelected(true);
        } else {
            chkMualMuntah.setSelected(false);
        }
        
        if (cekKram.equals("ya")) {
            chkKramOtot.setSelected(true);
        } else {
            chkKramOtot.setSelected(false);
        }
        
        if (cekHiperkalemia.equals("ya")) {
            chkHiperkalemia.setSelected(true);
        } else {
            chkHiperkalemia.setSelected(false);
        }
        
        if (cekHipo.equals("ya")) {
            chkHipotensi.setSelected(true);
        } else {
            chkHipotensi.setSelected(false);
        }
        
        if (cekHipertensi.equals("ya")) {
            chkHipertensi.setSelected(true);
        } else {
            chkHipertensi.setSelected(false);
        }
        
        if (cekNyeri.equals("ya")) {
            chkNyeriDada.setSelected(true);
        } else {
            chkNyeriDada.setSelected(false);
        }
        
        if (cekAritmia.equals("ya")) {
            chkAritmia.setSelected(true);
        } else {
            chkAritmia.setSelected(false);
        }
        
        if (cekGatalGatal.equals("ya")) {
            chkGatalGatal.setSelected(true);
        } else {
            chkGatalGatal.setSelected(false);
        }
        
        if (cekDemam.equals("ya")) {
            chkDemam.setSelected(true);
        } else {
            chkDemam.setSelected(false);
        }
        
        if (cekMenggigil.equals("ya")) {
            chkMenggigil.setSelected(true);
        } else {
            chkMenggigil.setSelected(false);
        }
        
        if (cekLainPenyulit.equals("ya")) {
            chkLainPenyulit.setSelected(true);
        } else {
            chkLainPenyulit.setSelected(false);
        }
    }

    public void awalData() {
        if (Sequel.cariInteger("select count(-1) from asesmen_ulang_hemodialisis where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_ulang_hemodialisis where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }
}
